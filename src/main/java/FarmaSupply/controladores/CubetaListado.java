package FarmaSupply.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import FarmaSupply.dtos.CubetaDTO;
import FarmaSupply.servicios.ICubetaServicio;

/**
 * Clase que ejerce de controlador de la vista de listadoCubetas para gestionar
 * las solicitudes relacionadas con el listado de cubetas.
 */
@Controller
@RequestMapping("/privada")
public class CubetaListado {
	@Autowired
	private ICubetaServicio cubetaServicio;

	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/listadoCubetas y muestra
	 * la página de administración cubetas
	 *
	 * @param model Modelo que se utiliza para enviar el listado de cubetas a la
	 *              vista.
	 * @return La vista de listado de cubetas (listadoCubetas.html)
	 */
	@GetMapping("/listadoCubetas")
	public String mostrarMisCubetas(Model model) {
		try {

			List<CubetaDTO> misCubetas = cubetaServicio.obtenerTodas();
			if (misCubetas != null) {
				System.out.println("Número de cubetas para actual: " + misCubetas.size());
				model.addAttribute("misCubetas", misCubetas);
			} else {
				System.out.println("La lista de cubetas actual es nula.");
			}

			return "listadoCubetas";
		} catch (Exception e) {
			System.out.println("Error al obtener la lista de cubetas: " + e.getMessage());
			model.addAttribute("error", "Error al obtener la lista de cubetas");
			return "listadoCubetas";
		}
	}

	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/eliminar-cubeta/{id} para
	 * la eliminación de una cubeta.
	 * 
	 * @param id    El ID de la cubeta a eliminar.
	 * @param model modelos que se utiliza para enviar el listado de cubetas y
	 *              mensajes
	 * 
	 * @return La vista de listadoCubetas.html con el listado de cubetas actualizado
	 */
	@GetMapping("/eliminar-cubeta/{id}")
	public String eliminarCubeta(@PathVariable Long id, Model model) {
		try {
			CubetaDTO cubeta = cubetaServicio.buscarPorId(id);
			if (cubeta != null) {
				cubetaServicio.eliminarCubeta(id);
				List<CubetaDTO> misCubetas = cubetaServicio.obtenerTodas();
				model.addAttribute("misCubetas", misCubetas);
				model.addAttribute("eliminacionCorrecta", "La cubeta se ha eliminado correctamente");
				return "listadoCubetas";
			}
			return "listadoCubetas";

		} catch (Exception e) {
			model.addAttribute("Error", "Ocurrió un error al eliminar la cubeta");
			return "listadoCubetas";
		}

	}
}
