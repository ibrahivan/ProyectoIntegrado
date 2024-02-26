package FarmaSupply.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




import FarmaSupply.dtos.TiendaDTO;
import FarmaSupply.dtos.UsuarioDTO;
import FarmaSupply.servicios.ITiendaServicio;
import FarmaSupply.servicios.IUsuarioServicio;

/**
 * Clase que ejerce de controlador de la vista de registro y listado para gestionar
 * las solicitudes relacionadas registro y listado de tiendas.
 */
@Controller
@RequestMapping("/privada")
public class TiendaRegistro {

	@Autowired
	private IUsuarioServicio usuarioServicio;
	@Autowired
	private ITiendaServicio tiendaServicio;
	
	/**
	 * Gestiona la solicitud HTTP GET para mostrar la página de registro de tiendas.
	 * 
	 * @param model Modelo que se utiliza para enviar un tiendaDTO vacio a la
	 *              vista.
	 * @param authentication Objeto Authentication que contiene el nombre de usuario.
	 * @return La vista de registroTienda (registrarTienda.html).
	 */
	@GetMapping("/registrarTienda")
	public String registrarTiendaGet(Model model, Authentication authentication) {
		try {
		 UsuarioDTO usuarioSesionActual = usuarioServicio.buscarPorEmail(authentication.getName());
         TiendaDTO nuevaTienda = new TiendaDTO();
         nuevaTienda.setIdUsuario_Tie(usuarioSesionActual.getId());
         model.addAttribute("tiendaDTO", nuevaTienda);
         
         return "registroTienda";
         
     } catch (Exception e) {
         model.addAttribute("error", "Error al mostrar el formulario para crear una nueva tienda");
         return "home";
     }
}

	/**
	 * Procesa la solicitud HTTP POST para registro de una nuevo tienda.
	 * 
	 * @param tiendaDTO El objeto TiendaDTO que recibe en el modelo y contiene los
	 *                   datos de la nueva tienda.
	 * @param model Modelo que se utiliza para enviar un tiendaDTO vacio a la
	 *              vista.
	 * @param authentication Objeto Authentication que contiene el nombre de usuario.
	 * @return La vista de listado de tiendas (listadoTiendas.html) si fue exitoso el registro;
	 *         de lo contrario, la vista de registro de tienda (registroTienda.html).
	 */
	@PostMapping("/registrarTienda")
	public String registrarTiendaPost(@ModelAttribute TiendaDTO tiendaDTO, Model model,Authentication authentication) {

		try {
			
			 UsuarioDTO usuarioSesionActual = usuarioServicio.buscarPorEmail(authentication.getName());
			 tiendaDTO.setIdUsuario_Tie(usuarioSesionActual.getId()); // Establecer el ID de usuario en el TiendaDTO
			TiendaDTO nuevaTienda = tiendaServicio.registrarTienda(tiendaDTO);
			
			
			if (nuevaTienda != null && nuevaTienda.getDireccionTienda() != null) {
				// Si el usuario y el DNI no son null es que el registro se completo
				// correctamente
				model.addAttribute("mensajeRegistroExitoso", "Registro de la nueva tienda OK");
				model.addAttribute("misTiendas", usuarioSesionActual.getMisTiendas());
				return "listadoTiendas";
			} else {
				// Se verifica si el DNI ya existe para mostrar error personalizado en la vista
				if (tiendaDTO.getDireccionTienda() == null) {
					model.addAttribute("mensajeErrorDireccion", "Ya existe una tienda en esa dirección");
					model.addAttribute("misTiendas", usuarioSesionActual.getMisTiendas());
					return "registroTienda";
				} else {
					model.addAttribute("mensajeErrorNombre", "Ya existe una tienda con ese nombre");
					model.addAttribute("misTiendas", usuarioSesionActual.getMisTiendas());
					return "registroTienda";
				}
			}
		} catch (

		Exception e) {
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
			return "registro";
		}
	}

}
