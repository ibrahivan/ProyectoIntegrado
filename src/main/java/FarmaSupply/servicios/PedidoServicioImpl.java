package FarmaSupply.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FarmaSupply.daos.CatalogoProducto;
import FarmaSupply.daos.DetallePedido;
import FarmaSupply.daos.Pedido;
import FarmaSupply.daos.Tienda;
import FarmaSupply.dtos.CatalogoProductoDTO;
import FarmaSupply.dtos.DetallePedidoDTO;
import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.dtos.TiendaDTO;
import FarmaSupply.repositorios.CatalogoProductoRepositorio;
import FarmaSupply.repositorios.DetallePedidoRepositorio;
import FarmaSupply.repositorios.PedidoRepositorio;
import FarmaSupply.repositorios.TiendaRepositorio;

@Service
public class PedidoServicioImpl implements IPedidoServicio {

  
    @Autowired
    private CatalogoProductoRepositorio productoRepositorio;
    @Autowired
    private DetallePedidoRepositorio detallePedidoRepositorio;
    @Autowired
    private TiendaRepositorio repositorioTienda;
    @Autowired
    private PedidoRepositorio pedidoRepositorio;
    @Autowired
    private IPedidoToDao pedidoToDao;
    @Autowired
    private IDetallePedidoToDao detalleToDao;

    @Transactional
    public DetallePedidoDTO realizarPedido(DetallePedidoDTO detallePedidoDTO, TiendaDTO tiendaActual) {
    	  try {
            //busco la tienda
          	Optional<Tienda> tiendaPropietaria = repositorioTienda.findById(tiendaActual.getId());
          	
          	

          	//genero objeto pedidoDTO y dao
          	PedidoDTO pedidoDTO = new PedidoDTO();
          	Pedido pedidoDao = new Pedido();
          	//genero objetos de detalle
          	DetallePedido detallePedidoDao = new DetallePedido();
          	//creo el precio
              double precioTotal = 0.0;
              List<CatalogoProductoDTO> productosIncluidos = detallePedidoDTO.getProductosSeleccionados();
              for (CatalogoProductoDTO producto : productosIncluidos) { 
            	  //me servira para asignar el producto al detalle
            	  Optional<CatalogoProducto> productoRe = productoRepositorio.findById(tiendaActual.getId());
                  // Calcular el precio parcial del producto 
                  double precioParcial = producto.getPrecioUnitario() * detallePedidoDTO.getCantidadDetalle();
                  detallePedidoDTO.setPrecioDetalle(precioParcial);
                  //guardo el precio parcial y la cantidad de ese producto seleccionado en la bd
                  detallePedidoDao = detalleToDao.detallePedidoToDao(detallePedidoDTO);
                  detallePedidoDao.setIdDet_Cat(productoRe.get());
                  detallePedidoRepositorio.save(detallePedidoDao);
                  //Calculo el precio total del pedido
                  precioTotal += precioParcial;
              }
              
              // Establecer el precio total en el pedidoDTO
              pedidoDTO.setPrecioPedido(precioTotal);
              //Establecer el id de la tienda al pedido
              pedidoDao.setIdPedido_Tie(tiendaPropietaria.get());
              // Convertir el pedidoDTO a la entidad Pedido y guardarlo en la base de datos
              pedidoDao = pedidoToDao.pedidoToDao(pedidoDTO);
              pedidoRepositorio.save(pedidoDao);
              //me sirve para asignar el pedido con el detalle
              Optional<Pedido> pedidoRe = pedidoRepositorio.findById(pedidoDao.getIdPedido());
              detallePedidoDao.setIdDet_Ped(pedidoRe.get());
              //vuelvo a guardar el detalle en la bd
              detallePedidoRepositorio.save(detallePedidoDao);
              //paso los id a los dtos
              pedidoDTO.setIdPedido(pedidoDao.getIdPedido());
              detallePedidoDTO.setIdDetallePedido(detallePedidoDao.getIdDetallePedido());
              
              // Devolver el detallePedidoDTO con la información actualizada
              return detallePedidoDTO;
          } catch (Exception e) {
              // Manejar cualquier excepción y relanzarla como RuntimeException
              throw new RuntimeException("Error al procesar el pedido: " + e.getMessage(), e);
          }
      }


}
