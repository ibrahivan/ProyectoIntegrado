package FarmaSupply.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import FarmaSupply.dtos.CubetaDTO;
import FarmaSupply.servicios.ICubetaServicio;

/**
 * Clase que ejerce de controlador de la vista de registroCubeta para
 * gestionar las solicitudes relacionadas registro de cubetas.
 */
@Controller
@RequestMapping("/privada")
public class CubetaRegistro {
	@Autowired
	private ICubetaServicio cubetaServicio;

	/**
	 * Gestiona la solicitud HTTP GET para mostrar la página de registro de cubetas.
	 * 
	 * @param model Modelo que se utiliza para enviar un cubetaDTO vacio a la vista.
	 * @return La vista de registroCubeta (registroCubeta.html).
	 */
	@GetMapping("/registrarCubeta")
	public String registrarCubetaGet(Model model) {
		try {

			CubetaDTO nuevaCubeta = new CubetaDTO();
			model.addAttribute("cubetaDTO", nuevaCubeta);
			return "registroCubeta";

		} catch (Exception e) {
			model.addAttribute("error", "Error al mostrar el formulario para crear una nueva cubeta");
			return "home";
		}
	}

	/**
	 * Procesa la solicitud HTTP POST para registro de una nueva cubeta.
	 * 
	 * @param cubetaDTO El objeto CubetaDTO que recibe en el modelo y contiene los
	 *                  datos de la nueva cubeta.
	 * @param model     Modelo que se utiliza para enviar un cubetaDTO vacio a la
	 *                  vista.
	 * @return La vista de listado de motos (listadoCubetas.html) si fue exitoso el
	 *         registro; de lo contrario, la vista de registro de cubeta
	 *         (registroCubeta.html).
	 */
	@PostMapping("/registrarCubeta")
	public String registrarCubetaPost(@ModelAttribute CubetaDTO cubetaDTO, Model model) {

		try {

			List<CubetaDTO> misCubetas = cubetaServicio.obtenerTodas();
			CubetaDTO nuevaCubeta = cubetaServicio.registrarCubeta(cubetaDTO);

			if (nuevaCubeta != null && nuevaCubeta.getNumeroCubeta() != null) {
				// Si la cubeta y el numero de cubeta no son null es que el registro se completo
				// correctamente

				misCubetas.add(nuevaCubeta);

				model.addAttribute("mensajeRegistroExitoso", "Registro de la nueva cubeta OK");
				model.addAttribute("misCubetas", misCubetas);
				return "listadoCubetas";
			} else if  (cubetaDTO.getNumeroCubeta() == null) 
				// Se verifica si el numero de cubeta ya existe para mostrar error
				// personalizado en la vista
				
					model.addAttribute("mensajeErrorNumeroCubeta", "Ya existe una cubeta con esa numero identificatorio");
					model.addAttribute("misCubetas", misCubetas);
					return "registroCubeta";
				
			
		} catch (

		Exception e) {
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
			return "registroCubeta";
		}
	}
}
