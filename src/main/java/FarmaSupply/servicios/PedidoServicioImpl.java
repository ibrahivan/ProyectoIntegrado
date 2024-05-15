package FarmaSupply.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.Pedido;
import FarmaSupply.dtos.CatalogoProductoDTO;
import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.repositorios.PedidoRepositorio;

@Service
public class PedidoServicioImpl implements IPedidoServicio {

    @Autowired
    private PedidoRepositorio repositorio;

    @Autowired
    private IPedidoToDao toDao;
    
    @Override
    public PedidoDTO realizarPedido(PedidoDTO pedidoDTO) {
        try {
            // Calcular el precio total del pedido
            double precioTotal = 0.0;
            List<CatalogoProductoDTO> productosIncluidos = pedidoDTO.getMisCatalogoProducto();
            for (CatalogoProductoDTO producto : productosIncluidos) {
                // Calcular el precio parcial del producto y agregarlo al precio total
                double precioParcial = producto.getPrecioUnitario() * pedidoDTO.getCantidadPedido();
                precioTotal += precioParcial;
            }
            
            // Establecer el precio total en el pedidoDTO
            pedidoDTO.setPrecioPedido(precioTotal);

            // Convertir el pedidoDTO a la entidad Pedido y guardarlo en la base de datos
            Pedido pedidoDao = toDao.pedidoToDao(pedidoDTO);
            repositorio.save(pedidoDao);
            
            // Devolver el pedidoDTO con la información actualizada
            return pedidoDTO;
        } catch (Exception e) {
            // Manejar cualquier excepción y relanzarla como RuntimeException
            throw new RuntimeException("Error al procesar el pedido: " + e.getMessage(), e);
        }
    }
}



