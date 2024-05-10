package FarmaSupply.servicios;

import java.util.ArrayList;
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
    public PedidoDTO realizarPedido(PedidoDTO pedidoDTO, List<CatalogoProductoDTO> productos) {
        try {
            // Aquí puedes implementar la lógica necesaria para procesar el pedido
            // Crear una nueva instancia de Pedido y asignar los valores del PedidoDTO
            
            
            // Calcular el precio total del pedido
            double precioTotal = 0.0;
            List<CatalogoProductoDTO>listAux = new ArrayList<CatalogoProductoDTO>();
            for (CatalogoProductoDTO productoDTO : productos) {
            	listAux.add(productoDTO);
                pedidoDTO.setMisCatalogoProducto(listAux);

                if (pedidoDTO.getMisCatalogoProducto().contains(productoDTO)) {
                    // Obtener la cantidad del producto seleccionado por el usuario
                    double cantidad = pedidoDTO.getCantidadPedido();
                    // Verificar que la cantidad sea mayor que cero antes de agregar el producto al pedido
                    if (cantidad <= 0) {
                        throw new IllegalArgumentException("La cantidad debe ser mayor que cero para el producto con ID: " + productoDTO.getIdCatalogoProducto());
                    }   
                    // Calcular el precio parcial del producto y agregarlo al precio total
                    double precioParcial = cantidad * productoDTO.getPrecioUnitario();
                    precioTotal += precioParcial;
                }
            }
            //seteo el precio total
            pedidoDTO.setPrecioPedido(precioTotal);
            

            Pedido pedidoDao = toDao.pedidoToDao(pedidoDTO);
            repositorio.save(pedidoDao);
        	pedidoDTO.setIdPedido(pedidoDao.getIdPedido());
            // Devolver el pedido con la información actualizada
            return pedidoDTO;
            
        } catch (Exception e) {
            // Manejar cualquier excepción y relanzarla como RuntimeException
            throw new RuntimeException("Error al procesar el pedido: " + e.getMessage(), e);
        }
    }


}
