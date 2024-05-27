package FarmaSupply.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import FarmaSupply.daos.CatalogoProducto;
import FarmaSupply.daos.DetallePedido;
import FarmaSupply.daos.Pedido;
import FarmaSupply.dtos.CatalogoProductoDTO;
import FarmaSupply.dtos.DetallePedidoDTO;
import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.dtos.TiendaDTO;
import FarmaSupply.repositorios.PedidoRepositorio;
import FarmaSupply.servicios.ICatalogoProductoServicio;
import FarmaSupply.servicios.IPedidoServicio;
import FarmaSupply.servicios.IPedidoToDto;
import FarmaSupply.servicios.ITiendaServicio;

@Controller
@RequestMapping("/privada")
public class PedidoRegistro {
	@Autowired
	private PedidoRepositorio pedidoRepositorio;
	@Autowired
	private IPedidoToDto pedidoToDto;

	@Autowired
	private IPedidoServicio pedidoServicio;

	@Autowired
	private ITiendaServicio tiendaServicio;

	@Autowired
	private ICatalogoProductoServicio productoServicio;


	@GetMapping("/realizarPedido/{id}")
	public String registrarPedidoGet(@PathVariable Long id, Model model) {
		try {
			// Obtengo la tienda actual
			TiendaDTO tiendaDTO = tiendaServicio.buscarPorId(id);
			// Obtengo todos los productos de bbdd
			List<CatalogoProductoDTO> productos = productoServicio.obtenerTodas();
			// Genero el nuevo detallePedidoDTO
			DetallePedidoDTO detallePedidoDTO = new DetallePedidoDTO();

			// Los llevo al model
			model.addAttribute("misProductos", productos);
			model.addAttribute("tiendaDTO", tiendaDTO);
			model.addAttribute("idTienda", tiendaDTO.getId());
			model.addAttribute("detallePedidoDTO", detallePedidoDTO);

			return "registroPedido";
		} catch (Exception e) {
			model.addAttribute("error", "Error al mostrar el formulario para crear un nuevo pedido");
			return "listadoTiendas";
		}
	}

	@PostMapping("/realizarPedido/{id}")
	public String registrarPedidoPost(@PathVariable Long id, @ModelAttribute DetallePedidoDTO detallePedidoDTO,
			@RequestParam List<Long> productosSeleccionadosIds, @RequestParam("cantidades") List<Double> cantidades,
			Model model) {
		try {
			// Obtener la tienda actual
			TiendaDTO tiendaActual = tiendaServicio.buscarPorId(id);
			// genero objeto pedidoDTO
			PedidoDTO pedidoDTO = new PedidoDTO();
			// Convertir los IDs de productos seleccionados a objetos CatalogoProductoDTO
			List<CatalogoProductoDTO> productosSeleccionados = new ArrayList<>();

			for (Long productoId : productosSeleccionadosIds) {
				CatalogoProductoDTO producto = productoServicio.buscarPorId(productoId);
				productosSeleccionados.add(producto);
			}
			detallePedidoDTO.setProductosSeleccionados(productosSeleccionados);

			// Realizar el pedido que guardaremos los detalles y los asociaremos con el
			// pedido
			DetallePedidoDTO nuevoPedido = pedidoServicio.realizarPedido(detallePedidoDTO, tiendaActual, cantidades);
			// Añadir los detalles al pedido
			List<DetallePedidoDTO> listaDetallesPedidos = pedidoDTO.getMisDetallesPedidos();
			listaDetallesPedidos.add(nuevoPedido);
			pedidoDTO.setMisDetallesPedidos(listaDetallesPedidos);

			// Añadir el pedido a la lista de pedidos de la tienda
			List<PedidoDTO> listaPedidos = tiendaActual.getMisPedidos();
			listaPedidos.add(pedidoDTO);
			tiendaActual.setMisPedidos(listaPedidos);

			// Actualizar el modelo con los pedidos de la tienda
			model.addAttribute("mensajePedidoRealizadoExitoso", "Pedido realizado con éxito");
			model.addAttribute("misPedidos", tiendaActual.getMisPedidos());

			return "listadoTiendas";
		} catch (Exception e) {
			model.addAttribute("error", "Error al procesar el pedido");
			return "registroPedido";
		}
	}

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
