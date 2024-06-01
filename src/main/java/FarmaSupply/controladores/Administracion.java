package FarmaSupply.controladores;

import java.util.ArrayList;
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
import FarmaSupply.servicios.UsuarioServicioImpl;
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
	public String listadoUsuarios(@RequestParam(value = "busquedaUser", required = false) String busquedaUser,
			@RequestParam(value = "filtro", required = false) String filtro, Model model, HttpServletRequest request,
			Authentication authentication) {
		try {

			List<UsuarioDTO> usuarios = new ArrayList<>();
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
			if (busquedaUser != null && !busquedaUser.isEmpty()) {
				if (filtro.equals("email_usuario")) {
					usuarios = usuarioServicio.buscarPorCoincidenciaEnEmail(busquedaUser);
				} else {
					usuarios = usuarioServicio.buscarPorCoincidenciaEnNombre(busquedaUser);
				}
				if (usuarios.size() > 0) {
					model.addAttribute("usuarios", usuarios);
				} else {
					model.addAttribute("usuarioNoEncontrado", "No se encontraron usuarios por la busqueda introducida");
					model.addAttribute("usuarios", usuarioServicio.obtenerTodos());
				}
			} else {
				model.addAttribute("usuarios", usuarioServicio.obtenerTodos());
			}

			if (request.isUserInRole("ROLE_ADMIN")) {
				return "listado";
			}

			model.addAttribute("noAdmin", "No eres admin");
			model.addAttribute("nombreUsuario", usuario.getNombreUsuario());
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
		try {
		UsuarioDTO usuario = usuarioServicio.buscarPorId(id);
		List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
		String emailUsuarioActual = request.getUserPrincipal().getName();

		if (request.isUserInRole("ROLE_USER")) {
			model.addAttribute("noAdmin", "No tiene los permisos suficientes para acceder al recurso");
			model.addAttribute("usuarios", usuarios);
			model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

			return "home";

		} else if (emailUsuarioActual.equals(usuario.getEmailUsuario())) {
			model.addAttribute("noTePuedesEliminar", "No puedes eliminarte a ti mismo como administrador");
			model.addAttribute("usuarios", usuarios);
			return "listado";

		} else {
			
			int adminsRestantes = usuarioServicio.contarUsuariosPorRol("ROLE_ADMIN");
			if (usuario.getRol().equals("ROLE_ADMIN") && adminsRestantes == 1) {
				model.addAttribute("noSePuedeEliminarUltimoAdmin", "No se puede eliminar al último admin");
				model.addAttribute("usuarios", usuarios);
				return "listado";
			}

			if (usuario.getMisTiendas().size() > 0) {
				model.addAttribute("elUsuarioTieneTiendas", "No se puede eliminar un usuario con tiendas");
				model.addAttribute("usuarios", usuarios);
				return "listado";
			}

			usuarioServicio.eliminar(id);
			model.addAttribute("eliminacionCorrecta", "El usuario se ha eliminado correctamente");
			model.addAttribute("usuarios", usuarioServicio.obtenerTodos());
			return "listado";
		} 
		}
		catch (Exception e) {
						model.addAttribute("Error", "Ocurrió un error al eliminar el usuario");
						return "home";
	
	}
		
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
	public String mostrarFormularioEdicion(@PathVariable Long id, Model model, HttpServletRequest requestm,  HttpServletRequest request,Authentication authentication) {
		try {
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
			List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
			if (request.isUserInRole("ROLE_USER")) {
				model.addAttribute("noAdmin", "No tiene los permisos suficientes para acceder al recurso");
				model.addAttribute("usuarios", usuarios);
				model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

				return "home";

			}else {
			UsuarioDTO usuarioDTO = usuarioServicio.buscarPorId(id);
			if (usuarioDTO == null) {
				return "listado";
				}
			model.addAttribute("usuarioDTO", usuarioDTO);
			model.addAttribute("idUsuario", usuarioDTO.getId());
			}
			

			return "editarUsuario";

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
	public String procesarFormularioEdicion(@ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO, Model model,
			@RequestParam("file") MultipartFile imagen, HttpServletRequest request, Authentication authentication) {
		try {
			// si sube una imagen la enviamos a la bbdd sino que actualice y ya esta

			if (!imagen.isEmpty()) {

				UsuarioServicioImpl impU = new UsuarioServicioImpl();
				String convertedImage = impU.convertToBase64(imagen.getBytes());
				usuarioDTO.setFoto(convertedImage);

			} else {
				// Si no se selecciona una nueva imagen, asegúrate de no sobrescribir el valor
				// existente
				UsuarioDTO usuarioDTOexistente = usuarioServicio.buscarPorId(usuarioDTO.getId());
				if (usuarioDTOexistente != null) {
					usuarioDTO.setFoto(usuarioDTOexistente.getFoto());
				}
			}
			usuarioServicio.actualizarUsuario(usuarioDTO);
			model.addAttribute("edicionCorrecta", "El Usuario se ha editado correctamente");
			String email = authentication.getName();
			if (request.isUserInRole("ROLE_ADMIN")) {
				List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
				
				model.addAttribute("usuarios", usuarios);

			} else if (request.isUserInRole("ROLE_USER")) {
				usuarioDTO = usuarioServicio.buscarPorEmail(email);
				model.addAttribute("usuarios", usuarioDTO);
			}

			return "listado";

		} catch (Exception e) {
			model.addAttribute("Error", "Ocurrió un error al editar el usuario");
			return "home";
		}
	}
}
