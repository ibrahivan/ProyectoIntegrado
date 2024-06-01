package FarmaSupply.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import FarmaSupply.dtos.CatalogoProductoDTO;
import FarmaSupply.dtos.DetallePedidoDTO;
import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.dtos.TiendaDTO;
import FarmaSupply.servicios.ICatalogoProductoServicio;
import FarmaSupply.servicios.ITiendaServicio;

@Controller
@RequestMapping("/privada")
public class PedidoListado {
	
	@Autowired
	private ITiendaServicio tiendaServicio;

	@Autowired
	private ICatalogoProductoServicio productoServicio;

	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/listadoPedidos y muestra
	 * la página de administración pedidos de la tienda.
	 *
	 * @param model Modelo que se utiliza para enviar el listado de pedidos a la
	 *              vista.
	 * @return La vista de listado de pedidos (listadoPedidos.html)
	 */
	@GetMapping("/listadoPedidos/{id}")
	public String mostrarMisPedidos(@PathVariable Long id, Model model) {
	    try {
	        // Obtener la tienda actual
	        TiendaDTO tiendaActual = tiendaServicio.buscarPorId(id);

	        if (tiendaActual != null) {
	            List<PedidoDTO> misPedidos = tiendaActual.getMisPedidos();
	            List<DetallePedidoDTO> misDetallesPedidos = new ArrayList<>();

	            // Recorrer los pedidos para obtener sus detalles
	            for (PedidoDTO pedido : misPedidos) {
	                List<DetallePedidoDTO> detallesPedido = pedido.getMisDetallesPedidos();
	                for (DetallePedidoDTO detalle : detallesPedido) {
	                    // Crear una lista de productos seleccionados para este detalle
	                    List<CatalogoProductoDTO> productosSeleccionados = new ArrayList<>();

	                    // Obtener el ID del catálogo del producto desde el detalle
	                    Long idProducto = detalle.getIdDet_Cat();
	                    if (idProducto != null) {
	                        // Buscar el producto por ID
	                        CatalogoProductoDTO producto = productoServicio.buscarPorId(idProducto);
	                        if (producto != null) {
	                            productosSeleccionados.add(producto);
	                        }
	                    }

	                    // Asignar la lista de productos seleccionados al detalle
	                    detalle.setProductosSeleccionados(productosSeleccionados);

	                    // Agregar el detalle a la lista de detalles
	                    misDetallesPedidos.add(detalle);
	                }
	            }

	            if (!misPedidos.isEmpty()) {
	                System.out.println("Número de pedidos para la tienda actual: " + misPedidos.size());
	                // Agregar las listas al modelo con nombres distintos
	                model.addAttribute("misPedidos", misPedidos);
	                model.addAttribute("misDetallesPedidos", misDetallesPedidos);
	            } else {
	                System.out.println("La lista de pedidos para el usuario actual es nula.");
	            }
	        }
	        return "listadoPedidos";
	    } catch (Exception e) {
	        System.out.println("Error al obtener la lista de pedidos: " + e.getMessage());
	        model.addAttribute("error", "Error al obtener la lista de pedidos");
	        return "listadoPedidos";
	    }
	}
}
