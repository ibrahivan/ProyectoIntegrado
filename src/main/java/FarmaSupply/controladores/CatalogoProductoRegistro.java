package FarmaSupply.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import FarmaSupply.dtos.CatalogoProductoDTO;
import FarmaSupply.servicios.ICatalogoProductoServicio;

/**
 * Clase que ejerce de controlador de la vista de registroProducto para
 * gestionar las solicitudes relacionadas registro de productos.
 */
@Controller
@RequestMapping("/privada")
public class CatalogoProductoRegistro {
	@Autowired
	private ICatalogoProductoServicio productoServicio;

	/**
	 * Gestiona la solicitud HTTP GET para mostrar la página de registro de productos.
	 * 
	 * @param model Modelo que se utiliza para enviar un productoDTO vacio a la vista.
	 * @return La vista de registroProducto (registroProducto.html).
	 */
	@GetMapping("/registrarProducto")
	public String registrarProductoGet(Model model) {
		try {

			CatalogoProductoDTO nuevoProducto = new CatalogoProductoDTO();
			model.addAttribute("productoDTO", nuevoProducto);
			return "registroProducto";

		} catch (Exception e) {
			model.addAttribute("error", "Error al mostrar el formulario para crear un nuevo producto");
			return "home";
		}
	}

	/**
	 * Procesa la solicitud HTTP POST para registro de un nuevo producto.
	 * 
	 * @param motoDTO El objeto ProductoDTO que recibe en el modelo y contiene los
	 *                  datos del nuevo producto.
	 * @param model     Modelo que se utiliza para enviar un productoDTO vacio a la
	 *                  vista.
	 * @return La vista de listado de productos (listadoProductos.html) si fue exitoso el
	 *         registro; de lo contrario, la vista de registro de producto
	 *         (registroProducto.html).
	 */
	@PostMapping("/registrarProducto")
	public String registrarProductoPost(@ModelAttribute CatalogoProductoDTO productoDTO, Model model) {

		try {

			List<CatalogoProductoDTO> misProductos = productoServicio.obtenerTodas();
			CatalogoProductoDTO nuevoProducto = productoServicio.registrarProducto(productoDTO);

			if (nuevoProducto != null && nuevoProducto.getNombreProducto() != null) {
				

				misProductos.add(nuevoProducto);

				model.addAttribute("mensajeRegistroExitoso", "Registro del nuevo producto OK");
				model.addAttribute("misProductos", misProductos);
				
			} 
			return "listadoProductos";
		} catch (

		Exception e) {
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
			return "registroProducto";
		}
	}

}
