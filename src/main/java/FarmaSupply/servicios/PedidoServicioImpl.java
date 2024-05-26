package FarmaSupply.servicios;

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
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private DetallePedidoRepositorio detallePedidoRepositorio;
    @Autowired
    private TiendaRepositorio repositorioTienda;
    @Autowired
    private CatalogoProductoRepositorio productoRepositorio;
    @Autowired
    private ICatalogoProductoServicio productoServicio;
    
    @Autowired
    private IDetallePedidoToDao toDao;

    @Transactional
    public DetallePedidoDTO realizarPedido(DetallePedidoDTO detallePedidoDTO, TiendaDTO tiendaActual) {
    	try {
    	Optional<Tienda> tiendaPropietaria = repositorioTienda.findById(tiendaActual.getId());
        Pedido pedidoDao = new Pedido();
       //guardo el id de la tienda en el pedido y lo guardo en la bdd
        pedidoDao.setIdPedido_Tie(tiendaPropietaria.get());
        pedidoRepositorio.save(pedidoDao);
        //Genero DTo productos
        CatalogoProductoDTO producto = new CatalogoProductoDTO();
        //Dao detalles
        DetallePedido detallePedidoDao = new DetallePedido();
        //Optional de pedido y Catalogo
        Optional<Pedido> pedidoRe = pedidoRepositorio.findById(pedidoDao.getIdPedido());
        
        double precioTotalPedido = 0.0;

        // Recorrer los productos seleccionados y sus cantidades
        for (int i = 0; i < detallePedidoDTO.getProductosSeleccionadosIds().size(); i++) {
        	//cojo los id de producto y cantidades
            Long productoId = detallePedidoDTO.getProductosSeleccionadosIds().get(i);
            Double cantidad = detallePedidoDTO.getCantidades().get(i);

            //busco el producto
            producto = productoServicio.buscarPorId(productoId);
            Optional<CatalogoProducto> productoRe = productoRepositorio.findById(producto.getIdCatalogoProducto());

            //recojo los dtos y cambio a dao
            detallePedidoDTO.setCantidadDetalle(cantidad);
            double precioDetalle = cantidad * producto.getPrecioUnitario();
            detallePedidoDTO.setPrecioDetalle(precioDetalle);
            detallePedidoDao = toDao.detallePedidoToDao(detallePedidoDTO);
            //asigno los id de pedido y catalogo al detalle
            detallePedidoDao.setIdDet_Cat(productoRe.get());
            detallePedidoDao.setIdDet_Ped(pedidoRe.get());
            //guardo el detalledao en la bbdd
            detallePedidoRepositorio.save(detallePedidoDao);
            //calculo precio total pedido
            precioTotalPedido += precioDetalle;
        }
        //asigno precio total
        pedidoDao.setPrecioPedido(precioTotalPedido);
        //vuelvo a guardar el pedido
        pedidoRepositorio.save(pedidoDao);

        return detallePedidoDTO;
        
    } catch (Exception e) {
        throw new RuntimeException("Error al procesar el pedido: " + e.getMessage(), e);
    }
    }
}
