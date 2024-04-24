package FarmaSupply.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import FarmaSupply.dtos.UsuarioDTO;
import FarmaSupply.servicios.IUsuarioServicio;

/**
 * Clase que ejerce de controlador de la vista de login/registro para gestionar
 * las solicitudes relacionadas con la autenticación y registro de usuarios.
 */
@Controller
public class Login {

	@Autowired
	private IUsuarioServicio usuarioServicio;

	/**
	 * Gestiona la solicitud HTTP GET para /auth/login y muestra la página de inicio
	 * de sesión
	 * 
	 * @param model Modelo que se utiliza para enviar un usuarioDTO vacio a la
	 *              vista.
	 * @return La vista de inicio de sesión (login.html).
	 */
	@GetMapping("/auth/login")
	public String login(Model model) {
		// Se agrega un nuevo objeto UsuarioDTO al modelo para el formulario de login
		model.addAttribute("usuarioDTO", new UsuarioDTO());
		return "login";
	}

	/**
	 * Gestiona la solicitud HTTP GET para mostrar la página de registro.
	 * 
	 * @param model Modelo que se utiliza para enviar un usuarioDTO vacio a la
	 *              vista.
	 * @return La vista de registro de usuario (registrar.html).
	 */
	@GetMapping("/auth/registrar")
	public String registrarGet(Model model) {
		model.addAttribute("usuarioDTO", new UsuarioDTO());
		return "registro";
	}

	/**
	 * Procesa la solicitud HTTP POST para registro de un nuevo usuario.
	 * 
	 * @param usuarioDTO El objeto UsuarioDTO que recibe en el modelo y contiene los
	 *                   datos del nuevo usuario.
	 * @return La vista de inicio de sesión (login.html) si fue exitoso el registro;
	 *         de lo contrario, la vista de registro de usuario (registro.html).
	 */
	@PostMapping("/auth/registrar")
	public String registrarPost(@ModelAttribute UsuarioDTO usuarioDTO, Model model, Authentication authentication) {

		try {
			UsuarioDTO nuevoUsuario = usuarioServicio.registrar(usuarioDTO);

			String rolDelUsuario = "";
			if (authentication != null && authentication.isAuthenticated()) {
				rolDelUsuario = authentication.getAuthorities().iterator().next().getAuthority();
			}
			if (nuevoUsuario != null && rolDelUsuario.equals("ROLE_ADMIN")) {
				model.addAttribute("mensajeRegistroExitoso", "Registro del nuevo usuario OK");
				model.addAttribute("usuarios", usuarioServicio.obtenerTodos());
				return "listado";
			} else if (nuevoUsuario != null && nuevoUsuario.getDniUsuario() != null) {
				// Si el usuario y el DNI no son null es que el registro se completo
				// correctamente
				model.addAttribute("mensajeRegistroExitoso", "Registro del nuevo usuario OK");
				return "login";
			} else {
				// Se verifica si el DNI ya existe para mostrar error personalizado en la vista
				if (usuarioDTO.getDniUsuario() == null) {
					model.addAttribute("mensajeErrorDni", "Ya existe un usuario con ese DNI");
					return "registro";
				} else {
					model.addAttribute("mensajeErrorMail", "Ya existe un usuario con ese email");
					return "registro";
				}
			}
		} catch (

		Exception e) {
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
			return "registro";
		}
	}

	/**
	 * Gestiona la solicitud HTTP GET para la url /auth/confirmar-cuenta y realiza
	 * la confirmación de la cuenta.
	 *
	 * @param model Modelo que se utiliza para enviar mensajes a la vista.
	 * @param token Token de confirmación enviado al usuario.
	 * @return La vista de confirmación de cuenta.
	 */
	@GetMapping("/auth/confirmar-cuenta")
	public String confirmarCuenta(Model model, @RequestParam("token") String token) {
		try {
			boolean confirmacionExitosa = usuarioServicio.confirmarCuenta(token);

			if (confirmacionExitosa) {
				model.addAttribute("cuentaVerificada", "Su dirección de correo ha sido confirmada correctamente");
			} else {
				model.addAttribute("cuentaNoVerificada", "Error al confirmar su email");
			}

			return "login";
		} catch (Exception e) {
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
			return "login";
		}
	}

	/**
	 * Gestiona la solicitud HTTP GET para llevar a la página de home una vez
	 * logeado con exito.
	 * 
	 * @return La vista de home.html
	 */
	@GetMapping("/privada/home")
	public String loginCorrecto(Model model, Authentication authentication) {
		try {
			boolean cuentaConfirmada = usuarioServicio.estaLaCuentaConfirmada(authentication.getName());
			System.out.println(authentication.getAuthorities());
			if (cuentaConfirmada) {
				model.addAttribute("nombreUsuario", authentication.getName());
				return "home";
			} else {
				model.addAttribute("cuentaNoVerificada", "Error al confirmar su email");
				return "login";
			}
		} catch (Exception e) {
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
			return "login";
		}
	}

}
