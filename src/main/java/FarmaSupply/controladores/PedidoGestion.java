package FarmaSupply.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import FarmaSupply.servicios.IMotoServicio;
import FarmaSupply.servicios.IMotoToDto;
import FarmaSupply.servicios.IPedidoServicio;
import FarmaSupply.servicios.IPedidoToDto;

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

	@GetMapping("/listadoGestionPedidos")
	public String mostrarMotosyPedidos(Model model) {
		try {
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
			
			return "listadoGestionPedidos";
		} catch (Exception e) {
			model.addAttribute("error", "Error al mostrar el formulario para crear un nuevo pedido");
			return "home";
		}
	}

	@PostMapping("/asignarPedidoMoto")
	public String asignarPedidoMoto(@RequestParam Long idPedido, @RequestParam Long idMoto, Model model) {
		try {

			if (idPedido == null)
			{
				model.addAttribute("noHayPedidos", "No puedes asignar moto si no hay pedido");
				return "home";
			}else if (idMoto==null)
			{
				model.addAttribute("noHayMotos", "No puedes asignar un pedido si no hay motos disponiles");
				return "home";
			}
			pedidoServicio.asignarPedidoAMoto(idPedido, idMoto);
			model.addAttribute("mensajeAsignacionRealizadaExito", "Asignacio entre el pedido y la moto realizada con éxito");
			return "home";
		} catch (Exception e) {
			model.addAttribute("error", "Error al mostrar el formulario para crear un nuevo pedido");
			return "home";
		}
	}
	
	@PostMapping("/entregarPedidos")
    public String entregarPedidos(@RequestParam("idPedido") List<Long> listaId, Model model) {
        try {
        	
        	pedidoServicio.
            for (Long pedidoId : selectedPedidos) {
                Pedido pedido = pedidoService.findById(pedidoId);
                if (pedido != null && pedido.getEstadoPedido().equals("En camino")) {
                    pedido.setEstadoPedido("Entregado");
                    pedidoService.save(pedido);

                    Moto moto = motoService.findByPedido(pedido);
                    if (moto != null) {
                        moto.setEstado("Libre");
                        motoService.save(moto);
                    }
                }
            }
            model.addAttribute("successMessage", "Pedidos marcados como entregados.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ocurrió un error en el servidor.");
        }
        return "redirect:/privada/listadoPedidos";
    }
}
