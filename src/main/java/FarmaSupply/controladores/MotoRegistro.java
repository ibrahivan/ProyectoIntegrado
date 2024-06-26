package FarmaSupply.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import FarmaSupply.dtos.MotoDTO;
import FarmaSupply.dtos.UsuarioDTO;
import FarmaSupply.servicios.IMotoServicio;
import FarmaSupply.servicios.IUsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Clase que ejerce de controlador de la vista de registroMoto para
 * gestionar las solicitudes relacionadas registro de motos.
 */
@Controller
@RequestMapping("/privada")
public class MotoRegistro {
	@Autowired
	private IMotoServicio motoServicio;
	@Autowired
	private IUsuarioServicio usuarioServicio;
	/**
	 * Gestiona la solicitud HTTP GET para mostrar la página de registro de motos.
	 * 
	 * @param model Modelo que se utiliza para enviar un motoDTO vacio a la vista.
	 * @return La vista de registroMoto (registrarMoto.html).
	 */
	@GetMapping("/registrarMoto")
	public String registrarMotoGet(Model model, HttpServletRequest request, Authentication authentication) {
		try {
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());

			List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
			if (request.isUserInRole("ROLE_USER")) {
				model.addAttribute("noAdmin", "No tiene los permisos suficientes para acceder al recurso");
				model.addAttribute("usuarios", usuarios);
				model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

				return "home";

			}else {
			MotoDTO nuevaMoto = new MotoDTO();
			model.addAttribute("motoDTO", nuevaMoto);
			}
			return "registroMoto";

		} catch (Exception e) {
			model.addAttribute("error", "Error al mostrar el formulario para crear una nueva moto");
			return "home";
		}
	}

	/**
	 * Procesa la solicitud HTTP POST para registro de una nueva moto.
	 * 
	 * @param motoDTO El objeto MotoDTO que recibe en el modelo y contiene los
	 *                  datos de la nueva moto.
	 * @param model     Modelo que se utiliza para enviar un motoDTO vacio a la
	 *                  vista.
	 * @return La vista de listado de motos (listadoMotos.html) si fue exitoso el
	 *         registro; de lo contrario, la vista de registro de moto
	 *         (registroMoto.html).
	 */
	@PostMapping("/registrarMoto")
	public String registrarMotoPost(@ModelAttribute MotoDTO motoDTO, Model model,HttpServletRequest request, Authentication authentication) {

		try {
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
			List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
			if (request.isUserInRole("ROLE_USER")) {
				model.addAttribute("noAdmin", "No tiene los permisos suficientes para acceder al recurso");
				model.addAttribute("usuarios", usuarios);
				model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

				return "home";

			}else {
			List<MotoDTO> misMotos = motoServicio.obtenerTodas();
			MotoDTO nuevaMoto = motoServicio.registrarMoto(motoDTO);

			if (nuevaMoto != null && nuevaMoto.getMatriculaMoto() != null) {
				// Si la matricula y la moto no son null es que el registro se completo
				// correctamente

				misMotos.add(nuevaMoto);

				model.addAttribute("mensajeRegistroExitoso", "Registro de la nueva moto OK");
				model.addAttribute("misMotos", misMotos);
				
			} else if  (motoDTO.getMatriculaMoto() == null) {
				// Se verifica si el numero de matricula ya existe para mostrar error
				// personalizado en la vista
				
					model.addAttribute("mensajeErrorMatricula", "Ya existe una moto con esa matricula");
					model.addAttribute("misMotos", misMotos);
					return "registroMoto";
			}
			}
			return "listadoMotos";
		} catch (

		Exception e) {
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
			return "registroMoto";
		}
	}

}
