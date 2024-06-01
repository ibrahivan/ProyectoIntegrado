package FarmaSupply.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import FarmaSupply.dtos.CatalogoProductoDTO;
import FarmaSupply.dtos.UsuarioDTO;
import FarmaSupply.servicios.ICatalogoProductoServicio;
import FarmaSupply.servicios.IUsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Clase que ejerce de controlador de la vista de listadoProductos para
 * gestionar las solicitudes relacionadas con el listado de productos.
 */
@Controller
@RequestMapping("/privada")
public class CatalogoProductoListado {
	@Autowired
	private ICatalogoProductoServicio productoServicio;
	@Autowired
	private IUsuarioServicio usuarioServicio;
	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/listadoProductos y
	 * muestra la página de administración productos
	 *
	 * @param model Modelo que se utiliza para enviar el listado de productos a la
	 *              vista.
	 * @return La vista de listado de productos (listadoProductos.html)
	 */
	@GetMapping("/listadoProductos")
	public String mostrarMisProductos(Model model, HttpServletRequest request, Authentication authentication) {
		try {
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
			List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
			if (request.isUserInRole("ROLE_USER")) {
				model.addAttribute("noAdmin", "No tiene los permisos suficientes para acceder al recurso");
				model.addAttribute("usuarios", usuarios);
				model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

				return "home";

			}else {
			List<CatalogoProductoDTO> misProductos = productoServicio.obtenerTodas();
			if (misProductos != null) {
				System.out.println("Número de productos para actual: " + misProductos.size());
				model.addAttribute("misProductos", misProductos);
			} else {
				System.out.println("La lista de productos actual es nula.");
			}
			}
			return "listadoProductos";
		} catch (Exception e) {
			System.out.println("Error al obtener la lista de productos: " + e.getMessage());
			model.addAttribute("error", "Error al obtener la lista de productos");
			return "listadoProductos";
		}
	}

	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/eliminar-producto/{id}
	 * para la eliminación de un producto.
	 * 
	 * @param id    El ID del producto a eliminar.
	 * @param model modelos que se utiliza para enviar el listado de productos y
	 *              mensajes
	 * 
	 * @return La vista de listadoProductos.html con el listado de productos
	 *         actualizado
	 */
	@GetMapping("/eliminar-producto/{id}")
	public String eliminarProducto(@PathVariable Long id, Model model, HttpServletRequest request, Authentication authentication) {
		try {
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
			List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
			if (request.isUserInRole("ROLE_USER")) {
				model.addAttribute("noAdmin", "No tiene los permisos suficientes para acceder al recurso");
				model.addAttribute("usuarios", usuarios);
				model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

				return "home";

			}else {
			CatalogoProductoDTO producto = productoServicio.buscarPorId(id);
			if (producto != null) {
				productoServicio.eliminarProducto(id);
				List<CatalogoProductoDTO> misProductos = productoServicio.obtenerTodas();
				model.addAttribute("misProductos", misProductos);
				model.addAttribute("eliminacionCorrecta", "El producto se ha eliminado correctamente");
				return "listadoProductos";
			}
			}
			return "listadoProductos";

		} catch (Exception e) {
			model.addAttribute("Error", "Ocurrió un error al eliminar la moto");
			return "listadoProductos";
		}

	}
}
