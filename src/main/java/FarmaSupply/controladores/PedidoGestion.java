package FarmaSupply.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import FarmaSupply.daos.Moto;
import FarmaSupply.daos.Pedido;
import FarmaSupply.dtos.MotoDTO;
import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.dtos.UsuarioDTO;
import FarmaSupply.servicios.IMotoServicio;
import FarmaSupply.servicios.IMotoToDto;
import FarmaSupply.servicios.IPedidoServicio;
import FarmaSupply.servicios.IPedidoToDto;
import FarmaSupply.servicios.IUsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/privada")
public class PedidoGestion {
	@Autowired
	private IPedidoServicio pedidoServicio;
	@Autowired
	private IPedidoToDto pedidoToDto;
	@Autowired
	private IMotoToDto motoToDto;
	@Autowired
	private IMotoServicio motoServicio;
	@Autowired
	private IUsuarioServicio usuarioServicio;
	@GetMapping("/listadoGestionPedidos")
	public String mostrarMotosyPedidos(Model model, HttpServletRequest request, Authentication authentication) {
		try {
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
			List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
		if (request.isUserInRole("ROLE_USER")) {
			model.addAttribute("noAdmin", "No tiene los permisos suficientes para acceder al recurso");
			model.addAttribute("usuarios", usuarios);
			model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

			return "home";

		}else {
			// Creamos los dtos
			List<PedidoDTO> pedidosPendientesDTO = new ArrayList<PedidoDTO>();
			List<MotoDTO> motosLibresDTO = new ArrayList<MotoDTO>();
			// Nos traemos los datos
			List<Pedido> pedidosPendientes = pedidoServicio.obtenerPedidosPendientes();
			List<Moto> motosLibres = motoServicio.obtenerMotosLibres();
			// los convertimos a dtos
			pedidosPendientesDTO = pedidoToDto.listaPedidoToDto(pedidosPendientes);
			motosLibresDTO = motoToDto.listaMotoToDto(motosLibres);

			// lo llevo al model
			model.addAttribute("pedidosPendientes", pedidosPendientesDTO);
			model.addAttribute("motosLibres", motosLibresDTO);
		}
			return "listadoGestionPedidos";
		} catch (Exception e) {
			model.addAttribute("error", "Error al mostrar el formulario para crear un nuevo pedido");
			return "home";
		}
	}

	@PostMapping("/asignarPedidoMoto" )
	public String asignarPedidoMoto(@RequestParam Long idPedido, @RequestParam Long idMoto, Model model,HttpServletRequest request, Authentication authentication) {
		try {
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
			List<UsuarioDTO> usuarios = usuarioServicio.obtenerTodos();
			if (request.isUserInRole("ROLE_USER")) {
				model.addAttribute("noAdmin", "No tiene los permisos suficientes para acceder al recurso");
				model.addAttribute("usuarios", usuarios);
				model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

				return "home";

			}else {
			if (idPedido == null)
			{
				model.addAttribute("noHayPedidos", "No puedes asignar moto si no hay pedido");
				model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

				return "home";
			}else if (idMoto==null)
			{
				model.addAttribute("noHayMotos", "No puedes asignar un pedido si no hay motos disponiles");
				model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

				return "home";
			}
			pedidoServicio.asignarPedidoAMoto(idPedido, idMoto);
			model.addAttribute("mensajeAsignacionRealizadaExito", "Asignacio entre el pedido y la moto realizada con éxito");
			model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

			}
			return "home";
		} catch (Exception e) {
			model.addAttribute("error", "Error al asignar el pedido");
			return "home";
		}
	}
	
	@PostMapping("/entregarPedidos")
    public String entregarPedidos(@RequestParam("idPedido") List<Long> listaId, Model model, Authentication authentication) {
        try {
			UsuarioDTO usuario = usuarioServicio.buscarPorEmail(authentication.getName());
  
        	pedidoServicio.confirmarEntrega(listaId);
			model.addAttribute("mensajeEntregaRealizadaExito", "Entrega del pedido realizada con éxito");
			model.addAttribute("nombreUsuario", usuario.getNombreUsuario());

	        return "home";
        	
        } catch (Exception e) {
            model.addAttribute("error", "Error al marca como entregado el pedido.");
            return "home";
        }
       
    }
}
