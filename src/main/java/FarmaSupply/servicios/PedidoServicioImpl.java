package FarmaSupply.servicios;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FarmaSupply.daos.CatalogoProducto;
import FarmaSupply.daos.DetallePedido;
import FarmaSupply.daos.EstadoMoto;
import FarmaSupply.daos.EstadoPedido;
import FarmaSupply.daos.Moto;
import FarmaSupply.daos.Pedido;
import FarmaSupply.daos.Tienda;
import FarmaSupply.dtos.CatalogoProductoDTO;
import FarmaSupply.dtos.DetallePedidoDTO;
import FarmaSupply.dtos.MotoDTO;
import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.dtos.TiendaDTO;
import FarmaSupply.repositorios.CatalogoProductoRepositorio;
import FarmaSupply.repositorios.DetallePedidoRepositorio;
import FarmaSupply.repositorios.MotoRepositorio;
import FarmaSupply.repositorios.PedidoRepositorio;
import FarmaSupply.repositorios.TiendaRepositorio;

@Service
public class PedidoServicioImpl implements IPedidoServicio {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;
    @Autowired
    private MotoRepositorio motoRepositorio;
    @Autowired
    private DetallePedidoRepositorio detallePedidoRepositorio;
    @Autowired
    private TiendaRepositorio repositorioTienda;
    @Autowired
    private CatalogoProductoRepositorio productoRepositorio;
   @Autowired
   private IPedidoToDto pedidoToDto;
   @Autowired
   private IMotoToDto motoToDto;
   @Autowired
   private IMotoServicio motoServicio;
   @Autowired
   private IPedidoToDao pedidoToDao;
   @Autowired
   private IMotoToDao motoToDao;
    
    @Autowired
    private IDetallePedidoToDao detalleToDao;
    private static  String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static  int ID_LENGTH = 8;

    @Transactional
    public List<DetallePedidoDTO> realizarPedido(DetallePedidoDTO detallePedidoDTO, TiendaDTO tiendaActual,  List<Double> cantidades) {
    	  try {
            //busco la tienda
          	Optional<Tienda> tiendaPropietaria = repositorioTienda.findById(tiendaActual.getId());
          	 if (!tiendaPropietaria.isPresent()) {
                 throw new RuntimeException("Tienda no encontrada");
             }
          	
          	 //lista de detalles
          	 List<DetallePedidoDTO>listaDetallesDTO = new ArrayList<DetallePedidoDTO>();
          	//genero objeto pedidoDTO y dao
          	PedidoDTO pedidoDTO = new PedidoDTO();
          
          	Pedido pedidoDao = new Pedido();
          //Establecer el id de la tienda al pedido
            pedidoDao.setIdPedido_Tie(tiendaPropietaria.get());
            //guardo el pedido en la bdd
            pedidoDao= pedidoRepositorio.save(pedidoDao);
          	//genero objetos de detalle
          	DetallePedido detallePedidoDao = new DetallePedido();
          	//creo el precio
              double precioTotal = 0.0;
              List<CatalogoProductoDTO> productosIncluidos = detallePedidoDTO.getProductosSeleccionados();
              if (productosIncluidos.size() != cantidades.size()) {
                  throw new RuntimeException("El número de productos seleccionados no coincide con el número de cantidades");
              }
              
              for (int i = 0; i < productosIncluidos.size(); i++) {
            	  
            	  CatalogoProductoDTO producto = productosIncluidos.get(i);
                  double cantidad = cantidades.get(i); // Obtener la cantidad correspondiente al producto

            	  
            	  //me servira para asignar el producto al detalle
            	  Optional<CatalogoProducto> productoRe = productoRepositorio.findById(producto.getIdCatalogoProducto());
            	   //me sirve para asignar el pedido con el detalle
                  Optional<Pedido> pedidoRe = pedidoRepositorio.findById(pedidoDao.getIdPedido());
            	
                  // Calcular el precio parcial del producto 
            	  double precioParcial = producto.getPrecioUnitario() * cantidad;
            	   detallePedidoDTO.setPrecioDetalle(precioParcial);
                  //guardo el precio parcial y la cantidad de ese producto seleccionado en la bd
                  detallePedidoDTO.setCantidadDetalle(cantidad);
                  detallePedidoDTO.setCantidades(cantidades);
               
                  //añado los id de los dtos
                  detallePedidoDTO.setIdDet_Cat(productoRe.get().getIdCatalogoProducto());
                  detallePedidoDTO.setIdDet_Ped(pedidoRe.get().getIdPedido());
                  //Añado el dto a la lista
                  listaDetallesDTO.add(detallePedidoDTO);
                  
                  //paso el pedido a dao
                  detallePedidoDao = detalleToDao.detallePedidoToDao(detallePedidoDTO);
                  

                //guardo el detalle en la bd
                  detallePedidoRepositorio.save(detallePedidoDao);
                  //Calculo el precio total del pedido
                  precioTotal += precioParcial;
              }
              
              // Establecer el precio total en el pedidoDTO
              pedidoDTO.setPrecioPedido(precioTotal);
              pedidoDTO.setEstadoPedido(EstadoPedido.PENDIENTE);
              //genero un identificador para el pedido
              pedidoDTO.setIdentificadorPedido(generarIdentificador());
              pedidoDao.setIdentificadorPedido(pedidoDTO.getIdentificadorPedido());
              // Convertir el pedidoDTO a la entidad Pedido y guardarlo en la base de datos
              pedidoDao.setPrecioPedido(pedidoDTO.getPrecioPedido());
              pedidoDao.setEstadoPedido(pedidoDTO.getEstadoPedido());
              pedidoRepositorio.save(pedidoDao);
            
              
              //paso los id a los dtos
              pedidoDTO.setIdPedido(pedidoDao.getIdPedido());
              detallePedidoDTO.setIdDetallePedido(detallePedidoDao.getIdDetallePedido());
              
              // Devolver el detallePedidoDTO con la información actualizada
              return listaDetallesDTO;
          } catch (Exception e) {
              // Manejar cualquier excepción y relanzarla como RuntimeException
              throw new RuntimeException("Error al procesar el pedido: " + e.getMessage(), e);
          }
      }

    public List<Pedido> obtenerPedidosPendientes() {
        return pedidoRepositorio.findAll()
                               .stream()
                               .filter(pedido -> pedido.getEstadoPedido() == EstadoPedido.PENDIENTE)
                               .collect(Collectors.toList());
    }

	@Override
	public void asignarPedidoAMoto(Long idPedido, Long idMoto) {
		// Obtener el pedido y la moto por sus respectivos IDs
		Optional<Pedido> pedidoRe = pedidoRepositorio.findById(idPedido);
		Optional<Moto> motoRe = motoRepositorio.findById(idMoto);
		
		//Creo los dtos y los daos
		MotoDTO motoDTO = new MotoDTO();
		Moto motoDao = new Moto();
		PedidoDTO pedidoDTO = new PedidoDTO();
		Pedido pedidoDao = new Pedido();
		List<PedidoDTO>listaPedidosDTO = new ArrayList<PedidoDTO>();
		
		//Transformo los optional a dtos
		motoDTO = motoToDto.motoToDto(motoRe.get());
		pedidoDTO = pedidoToDto.pedidoToDto(pedidoRe.get());
		
		
		//añado el pedido a la lista
		listaPedidosDTO.add(pedidoDTO);
		motoDTO.setMisPedidos(listaPedidosDTO);
		
		
		//cambio los estados
		motoDTO.setEstadoMoto(EstadoMoto.OCUPADA);
		pedidoDTO.setEstadoPedido(EstadoPedido.CAMINO);
		//paso a dao todo los datos
		pedidoDao = pedidoToDao.pedidoToDao(pedidoDTO);
		motoDao = motoToDao.motoToDao(motoDTO);
		//asigno el id de la moto al pedido
		pedidoDTO.setIdPedido_Moto(motoRe.get().getIdMoto());
		pedidoDao.setIdPed_Moto(motoRe.get());
		//actualizamos la bbdd
		
		pedidoRepositorio.save(pedidoDao);
		motoRepositorio.save(motoDao);
		
		
	}
	@Override
	public PedidoDTO buscarPorId(long id) {
		// TODO Auto-generated method stub
		try {
			Pedido pedido = pedidoRepositorio.findById(id).orElse(null);
			if (pedido != null) {
				return pedidoToDto.pedidoToDto(pedido);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println(
					"[Error PedidoServicioImpl - buscarPorId()] Al buscar el pedido por su id " + iae.getMessage());
		}
		return null;
	}
	@Override
	public void confirmarEntrega(List<Long> ListIdPedido) {
		// TODO Auto-generated method stub
		PedidoDTO pedidoDTO =  new PedidoDTO();
		Pedido pedidoDao = new Pedido();
	    Moto motoDao = new Moto();
	    MotoDTO motoDTO = new MotoDTO();
		for (Long idPedido : ListIdPedido) {
            pedidoDTO = buscarPorId(idPedido);
            if (pedidoDTO != null && pedidoDTO.getEstadoPedido().equals(EstadoPedido.CAMINO))
            {
            	//asigno el nuevo estado al pedido
                pedidoDTO.setEstadoPedido(EstadoPedido.ENTREGADO);
               
                //Hago lo mismo con la moto
                motoDTO = motoServicio.buscarMotoPorPedido(idPedido);
                motoDTO.setEstadoMoto(EstadoMoto.LIBRE);
                
                //los paso a daos
                pedidoDao = pedidoToDao.pedidoToDao(pedidoDTO);
                motoDao = motoToDao.motoToDao(motoDTO);
                //seteo el id de la moto en el pedido a null para que pueda volver a usar la moto
                pedidoDTO.setIdPedido_Moto(null);
                pedidoDao.setIdPed_Moto(null);
                //actualizo la bbdd
                pedidoRepositorio.save(pedidoDao);
                motoRepositorio.save(motoDao);
            }
        }
	}

	@Override
	public String generarIdentificador() {
		// TODO Auto-generated method stub
		  StringBuilder sb = new StringBuilder();
	        Random random = new Random();
	        for (int i = 0; i < ID_LENGTH; i++) {
	            int index = random.nextInt(CHARACTERS.length());
	            sb.append(CHARACTERS.charAt(index));
	        }
	        return sb.toString();
	    }
	}
	
      

