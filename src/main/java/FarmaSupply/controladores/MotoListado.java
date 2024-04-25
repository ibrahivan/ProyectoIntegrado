package FarmaSupply.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import FarmaSupply.dtos.MotoDTO;

import FarmaSupply.servicios.IMotoServicio;

/**
 * Clase que ejerce de controlador de la vista de listadoMotos para gestionar
 * las solicitudes relacionadas con el listado de motos.
 */
@Controller
@RequestMapping("/privada")
public class MotoListado {

	@Autowired
	private IMotoServicio motoServicio;

	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/listadoMotos y muestra la
	 * página de administración motos
	 *
	 * @param model Modelo que se utiliza para enviar el listado de motos a la
	 *              vista.
	 * @return La vista de listado de motos (listadoMotos.html)
	 */
	@GetMapping("/listadoMotos")
	public String mostrarMisMotos(Model model) {
		try {

			List<MotoDTO> misMotos = motoServicio.obtenerTodas();
			if (misMotos != null) {
				System.out.println("Número de motos para actual: " + misMotos.size());
				model.addAttribute("misMotos", misMotos);
			} else {
				System.out.println("La lista de motos actual es nula.");
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
	public String eliminarMoto(@PathVariable Long id, Model model) {
		try {
			MotoDTO moto = motoServicio.buscarPorId(id);
			if (moto != null) {
				motoServicio.eliminarMoto(id);
				List<MotoDTO> misMotos = motoServicio.obtenerTodas();
				model.addAttribute("misMotos", misMotos);
				model.addAttribute("eliminacionCorrecta", "La moto se ha eliminado correctamente");
				return "listadoMotos";
			}
			return "listadoMotos";

		} catch (Exception e) {
			model.addAttribute("Error", "Ocurrió un error al eliminar la moto");
			return "listadoTiendas";
		}

	}
}
