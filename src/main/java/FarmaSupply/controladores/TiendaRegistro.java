package FarmaSupply.controladores;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




import FarmaSupply.dtos.TiendaDTO;

import FarmaSupply.servicios.ITiendaServicio;

/**
 * Clase que ejerce de controlador de la vista de registro y listado para gestionar
 * las solicitudes relacionadas registro y listado de tiendas.
 */
@Controller
@RequestMapping("/auth")
public class TiendaRegistro {

	@Autowired
	private ITiendaServicio tiendaServicio;
	
	/**
	 * Gestiona la solicitud HTTP GET para mostrar la página de registro de tiendas.
	 * 
	 * @param model Modelo que se utiliza para enviar un tiendaDTO vacio a la
	 *              vista.
	 * @return La vista de registroTienda (registrarTienda.html).
	 */
	@GetMapping("/registrarTienda")
	public String registrarTiendaGet(Model model) {
		model.addAttribute("tiendaDTO", new TiendaDTO());
		return "registroTienda";
	}
	/**
	 * Procesa la solicitud HTTP POST para registro de una nuevo tienda.
	 * 
	 * @param usuarioDTO El objeto TiendaDTO que recibe en el modelo y contiene los
	 *                   datos de la nueva tienda.
	 * @return La vista de listado de tiendas (listadoTiendas.html) si fue exitoso el registro;
	 *         de lo contrario, la vista de registro de tienda (registroTienda.html).
	 */
	@PostMapping("/registrarTienda")
	public String registrarTiendaPost(@ModelAttribute TiendaDTO tiendaDTO, Model model) {

		try {
			TiendaDTO nuevaTienda = tiendaServicio.registrar(tiendaDTO);

			
			if (nuevaTienda != null && nuevaTienda.getDireccionTienda() != null) {
				// Si el usuario y el DNI no son null es que el registro se completo
				// correctamente
				model.addAttribute("mensajeRegistroExitoso", "Registro de la nueva tienda OK");
				return "listadoTiendas";
			} else {
				// Se verifica si el DNI ya existe para mostrar error personalizado en la vista
				if (tiendaDTO.getDireccionTienda() == null) {
					model.addAttribute("mensajeErrorDireccion", "Ya existe una tienda en esa dirección");
					return "registroTienda";
				} else {
					model.addAttribute("mensajeErrorNombre", "Ya existe una tienda con ese nombre");
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
