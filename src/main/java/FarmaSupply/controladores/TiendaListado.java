package FarmaSupply.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import FarmaSupply.dtos.TiendaDTO;
import FarmaSupply.dtos.UsuarioDTO;
import FarmaSupply.servicios.ITiendaServicio;
import FarmaSupply.servicios.IUsuarioServicio;
/**
 * Clase que ejerce de controlador de la vista de listado para
 * gestionar las solicitudes relacionadas con el  listado de motos.
 */
@Controller
@RequestMapping("/privada")
public class TiendaListado {

	@Autowired
	private IUsuarioServicio usuarioServicio;
	@Autowired
	private ITiendaServicio tiendaServicio;

	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/listadoTiendas y muestra
	 * la página de administración tiendas del usuario.
	 *
	 * @param model          Modelo que se utiliza para enviar el listado de tiendas
	 *                       a la vista.
	 * @param authentication Objeto Authentication que contiene el nombre de
	 *                       usuario.
	 * @return La vista de listado de tiendas (listadoTiendas.html)
	 */
	@GetMapping("/listadoTiendas")
	public String mostrarMisTiendas(Authentication authentication, Model model) {
		try {
			String name= authentication.getName();
			System.out.println(name);
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(name);
			
			if (usuario != null) {
				List<TiendaDTO> misTiendas = usuario.getMisTiendas();
				if (misTiendas != null) {
					System.out.println("Número de tiendas para el usuario actual: " + misTiendas.size());
					model.addAttribute("misTiendas", misTiendas);
				} else {
					System.out.println("La lista de tiendas para el usuario actual es nula.");
				}
			}
			return "listadoTiendas";
		} catch (Exception e) {
			System.out.println("Error al obtener la lista de tiendas: " + e.getMessage());
			model.addAttribute("error", "Error al obtener la lista de tiendas");
			return "listadoTiendas";
		}
	}

	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/eliminar-tienda/{id} para
	 * la eliminación de una tienda.
	 * 
	 * @param id             El ID de la tienda a eliminar.
	 * @param model          modelos que se utiliza para enviar el listado de
	 *                       tiendas y mensajes
	 * @param authentication Objeto Authentication que contiene datos sobre el
	 *                       usuario de la sesión.
	 * @return La vista de listadoTiendas.html con el listado de tiendas actualizado
	 */
	@GetMapping("/eliminar-tienda/{id}")
	public String eliminarTienda(@PathVariable Long id, Model model, Authentication authentication) {
		try {
			TiendaDTO tienda = tiendaServicio.buscarPorId(id);
			if (tienda != null && tienda.getMisPedidos().isEmpty()) {
				tiendaServicio.eliminarTienda(id);
				UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());

				model.addAttribute("eliminacionCorrecta", "La tienda se ha eliminado correctamente");
				model.addAttribute("misTiendas", usuario.getMisTiendas());
				return "listadoTiendas";
			}else
			{
				UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());

				model.addAttribute("noPuedeEliminarTiendaConPedidos", "No puedes eliminar una tienda con pedidos");
				model.addAttribute("misTiendas", usuario.getMisTiendas());
				return "listadoTiendas";
			}
			

		} catch (Exception e) {
			model.addAttribute("Error", "Ocurrió un error al eliminar la tienda");
			return "listadoTiendas";
		}

	}
}
