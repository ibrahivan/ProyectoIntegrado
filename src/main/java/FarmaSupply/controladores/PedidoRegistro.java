package FarmaSupply.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import FarmaSupply.dtos.CatalogoProductoDTO;
import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.dtos.TiendaDTO;
import FarmaSupply.servicios.ICatalogoProductoServicio;
import FarmaSupply.servicios.IPedidoServicio;
import FarmaSupply.servicios.ITiendaServicio;

@Controller
@RequestMapping("/privada")
public class PedidoRegistro {

	@Autowired
	private IPedidoServicio pedidoServicio;

	@Autowired
	private ITiendaServicio tiendaServicio;
	@Autowired
	private ICatalogoProductoServicio productoServicio;
	/**
	 * Gestiona la solicitud HTTP GET para mostrar la página de registro de pedidos.
	 * 
	 * @param model          Modelo que se utiliza para enviar un pedidoDTO vacio a
	 *                       la vista.
	 * @return La vista de registroPedido (registroPedido.html).
	 */

	@GetMapping("/realizarPedido/{id}")
	public String registrarPedidoGet(@PathVariable Long id, Model model) {
		try {
		//obtengo la tienda actual		
		TiendaDTO tiendaActual = tiendaServicio.buscarPorId(id);
		//obtengo todos los productos de bbdd
		List<CatalogoProductoDTO> productos = productoServicio.obtenerTodas();
		//genero el nuevo pedido
		PedidoDTO nuevoPedido = new PedidoDTO();
		nuevoPedido.setIdPedido_Tie(tiendaActual.getId());
		//los llevo al model
		model.addAttribute("misProductos", productos);
		model.addAttribute("idTienda", tiendaActual.getId());
		model.addAttribute("pedidoDTO", nuevoPedido);
		return "registroPedido";
		
	} catch (Exception e) {
		model.addAttribute("error", "Error al mostrar el formulario para crear un nuevo pedido");
		return "listadoTiendas";
	}
	}
	/**
	 * Procesa la solicitud HTTP POST para registro de una nuevo pedido.
	 * 
	 * @param tiendaDTO      El objeto TiendaDTO que recibe en el modelo y contiene
	 *                       los datos de la nueva tienda.
	 * @param model          Modelo que se utiliza para enviar un pedidoDTO vacio a
	 *                       la vista.
	 * @return La vista de listado de pedidos (listadoPedidos.html) si fue exitoso
	 *         el registro; de lo contrario, la vista de registro de pedidos
	 *         (registroPedido.html).
	 */
	@PostMapping("/realizarPedido/{id}")
	public String registrarPedidoPost(@PathVariable Long id,@ModelAttribute PedidoDTO pedidoDTO, Model model) {
		try {
			//obtengo la tienda actual		
			TiendaDTO tiendaActual = tiendaServicio.buscarPorId(id);
			//obtengo todos los productos de bbdd
			List<CatalogoProductoDTO> productos = productoServicio.obtenerTodas();
			//realizo el pedido para ellevarlo al listado
			List<PedidoDTO> listaPedidos = tiendaActual.getMisPedidos();
			PedidoDTO nuevoPedido = pedidoServicio.realizarPedido(pedidoDTO, productos);
			//añado el pedido a la lista
			listaPedidos.add(nuevoPedido);
			tiendaActual.setMisPedidos(listaPedidos);	
			model.addAttribute("mensajePedidoRealizadoExitoso", "Pedido realizado con éxito");
			return "listadoPedidos";
		} catch (Exception e) {
			model.addAttribute("error", "Error al procesar el pedido");
			return "registroPedido";
		}
	}

}
