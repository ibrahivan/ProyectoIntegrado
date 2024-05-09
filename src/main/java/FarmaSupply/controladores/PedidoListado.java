package FarmaSupply.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.dtos.TiendaDTO;
import FarmaSupply.servicios.ITiendaServicio;

@Controller
@RequestMapping("/privada")
public class PedidoListado {

	@Autowired
	private ITiendaServicio tiendaServicio;
	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/listadoPedidos y muestra
	 * la página de administración pedidos de la tienda.
	 *
	 * @param model          Modelo que se utiliza para enviar el listado de pedidos
	 *                       a la vista.
	 * @return La vista de listado de pedidos (listadoPedidos.html)
	 */
	@GetMapping("/listadoPedidos/{id}")
	public String mostrarMisPedidos(@PathVariable Long id, Model model) {
		try {
			//obtengo la tienda actual		
			TiendaDTO tiendaActual = tiendaServicio.buscarPorId(id);
			if (tiendaActual != null) {
				List<PedidoDTO> misPedidos = tiendaActual.getMisPedidos();
				if (misPedidos != null) {
					System.out.println("Número de pedidos para la tienda actual: " + misPedidos.size());
					model.addAttribute("misPedidos", misPedidos);
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
