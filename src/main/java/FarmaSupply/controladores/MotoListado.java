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

import FarmaSupply.daos.EstadoMoto;
import FarmaSupply.dtos.MotoDTO;
import FarmaSupply.dtos.UsuarioDTO;
import FarmaSupply.servicios.IMotoServicio;
import FarmaSupply.servicios.IUsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Clase que ejerce de controlador de la vista de listadoMotos para gestionar
 * las solicitudes relacionadas con el listado de motos.
 */
@Controller
@RequestMapping("/privada")
public class MotoListado {

	@Autowired
	private IMotoServicio motoServicio;
	@Autowired
	private IUsuarioServicio usuarioServicio;
	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/listadoMotos y muestra la
	 * página de administración motos
	 *
	 * @param model Modelo que se utiliza para enviar el listado de motos a la
	 *              vista.
	 * @return La vista de listado de motos (listadoMotos.html)
	 */
	@GetMapping("/listadoMotos")
	public String mostrarMisMotos(Model model, HttpServletRequest request, Authentication authentication) {
		try {
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
			List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
		if (request.isUserInRole("ROLE_USER")) {
			model.addAttribute("noAdmin", "No tiene los permisos suficientes para acceder al recurso");
			model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

			model.addAttribute("usuarios", usuarios);
			return "home";

		}else {

			List<MotoDTO> misMotos = motoServicio.obtenerTodas();
			if (misMotos != null) {
				System.out.println("Número de motos para actual: " + misMotos.size());
				model.addAttribute("misMotos", misMotos);
			} else {
				System.out.println("La lista de motos actual es nula.");
			}
		}

			return "listadoMotos";
		} catch (Exception e) {
			System.out.println("Error al obtener la lista de motos: " + e.getMessage());
			model.addAttribute("error", "Error al obtener la lista de motos");
			return "listadoMotos";
		}
	}

	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/eliminar-moto/{id} para
	 * la eliminación de una moto.
	 * 
	 * @param id    El ID de la moto a eliminar.
	 * @param model modelos que se utiliza para enviar el listado de motos y
	 *              mensajes
	 * 
	 * @return La vista de listadoMotos.html con el listado de motos actualizado
	 */
	@GetMapping("/eliminar-moto/{id}")
	public String eliminarMoto(@PathVariable Long id, Model model, HttpServletRequest request, Authentication authentication) {
		try {
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
			List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
			if (request.isUserInRole("ROLE_USER")) {
				model.addAttribute("noAdmin", "No tiene los permisos suficientes para acceder al recurso");
				model.addAttribute("usuarios", usuarios);
				model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

				return "home";

			}else {
			MotoDTO moto = motoServicio.buscarPorId(id);
			List<MotoDTO> misMotos = new ArrayList<MotoDTO>();
			if (moto != null && moto.getEstadoMoto() == EstadoMoto.LIBRE) {
				motoServicio.eliminarMoto(id);
				misMotos = motoServicio.obtenerTodas();
				model.addAttribute("misMotos", misMotos);
				model.addAttribute("eliminacionCorrecta", "La moto se ha eliminado correctamente");
				return "listadoMotos";
			} else {
				misMotos = motoServicio.obtenerTodas();
				model.addAttribute("noPuedeEliminarMotoOcupada", "No puedes eliminar una moto que esta ocupada");
				model.addAttribute("misMotos", misMotos);
			}
				return "listadoMotos";
			}
		

		} catch (Exception e) {
			model.addAttribute("Error", "Ocurrió un error al eliminar la moto");
			return "listadoTiendas";
		}

	}
}
