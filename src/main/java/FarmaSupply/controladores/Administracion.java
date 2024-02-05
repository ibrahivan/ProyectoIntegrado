package FarmaSupply.controladores;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import FarmaSupply.dtos.UsuarioDTO;
import FarmaSupply.servicios.IUsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class Administracion {

	@Autowired
	private IUsuarioServicio usuarioServicio;

	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/listado y muestra la
	 * página de administración de usuarios con el listado de usuarios.
	 *
	 * @param model          Modelo que se utiliza para enviar el listado de
	 *                       usuarios a la vista.
	 * @param request        HttpServletRequest para comprobar el rol del usuario.
	 * @param authentication Objeto Authentication que contiene el username/email.
	 * @return La vista de administración de usuarios (listado.html) si es rol admin
	 *         o la vista del panelUsuarios si el usuario no es rol admin.
	 */
	@GetMapping("/privada/listado")
	public String listadoUsuarios(Model model, HttpServletRequest request, Authentication authentication) {
		try {
			List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
			model.addAttribute("usuarios", usuarios);

			if (request.isUserInRole("ROLE_ADMIN")) {
				return "listado";
			}

			model.addAttribute("noAdmin", "No eres admin");
			model.addAttribute("nombreUsuario", authentication.getName());
			return "home";
		} catch (Exception e) {
			model.addAttribute("Error", "Ocurrió un error al obtener la lista de usuarios");
			return "home";
		}
	}

	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/eliminar/{id} y elimina
	 * al usuario con el ID proporcionado.
	 *
	 * @param id      ID del usuario a eliminar.
	 * @param model   Modelo que se utiliza para enviar mensajes y el listado
	 *                actualizado a la vista.
	 * @param request HttpServletRequest para comprobar el rol del usuario.
	 * @return La vista de administración de usuarios con el resultado de la
	 *         eliminación.
	 */
	@GetMapping("/privada/eliminar/{id}")
	public String eliminarUsuario(@PathVariable Long id, Model model, HttpServletRequest request) {
		UsuarioDTO usuario = usuarioServicio.buscarPorId(id);
		List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
		if (request.isUserInRole("ROLE_ADMIN") && usuario.getRol().equals("ROLE_ADMIN")) {
			model.addAttribute("noSePuedeEliminar", "No se puede eliminar a un admin");
			model.addAttribute("usuarios", usuarios);
			return "listado";
		}
		usuarioServicio.eliminar(id);
		return "redirect:/privada/listado";

	}

	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/editar-usuario/{id} y
	 * muestra el formulario de edición del usuario con el ID proporcionado.
	 *
	 * @param id    ID del usuario a editar.
	 * @param model Modelo que se utiliza para enviar el usuario a editar a la
	 *              vista.
	 * @return La vista de editarUsuario con el formulario de edición.
	 */
	@GetMapping("/privada/editar-usuario/{id}")
	public String mostrarFormularioEdicion(@PathVariable Long id, Model model, HttpServletRequest request) {
		try {
			if (request.isUserInRole("ROLE_USER")) {
				model.addAttribute("noAdmin", "No tiene permiso para realizar esta accion");
				return "home";
			} else {
				UsuarioDTO usuarioDTO = usuarioServicio.buscarPorId(id);
				if (usuarioDTO == null) {
					return "listado";
				}
				model.addAttribute("usuarioDTO", usuarioDTO);
				return "editarUsuario";
			}
		} catch (Exception e) {
			model.addAttribute("Error", "Ocurrió un error al obtener el usuario para editar");
			return "home";
		}
	}

	/**
	 * Gestiona la solicitud HTTP POST para la url /privada/procesar-editar y
	 * procesa el formulario de edición del usuario.
	 *
	 * @param usuarioDTO UsuarioDTO con los datos editados.
	 * @param model      Modelo que se utiliza para enviar mensajes y el listado
	 *                   actualizado a la vista.
	 * @return La vista de administración de usuarios con el resultado de la
	 *         edición.
	 */
	@PostMapping("/privada/procesar-editar")
	public String procesarFormularioEdicion(@ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO, Model model, @RequestParam(file) MultipartFile imagen) {
		try {
			usuarioServicio.actualizarUsuario(usuarioDTO);
			model.addAttribute("edicionCorrecta", "El Usuario se ha editado correctamente");
			model.addAttribute("usuarios", usuarioServicio.obtenerTodos());
			return "listado";
		} catch (Exception e) {
			model.addAttribute("Error", "Ocurrió un error al editar el usuario");
			return "home";
		}
	}
}
