package FarmaSupply.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import FarmaSupply.dtos.CatalogoProductoDTO;
import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.servicios.ICatalogoProductoServicio;
import FarmaSupply.servicios.IPedidoServicio;

@Controller
@RequestMapping("/privada")
public class PedidoRegistro {
	/**
    @Autowired
    private IPedidoServicio pedidoServicio;

    @Autowired
   private ICatalogoProductoServicio productoServicio;

    @GetMapping("/realizarPedido/{id}")
    public String mostrarFormularioPedido(@PathVariable Long id, Model model) {
        List<CatalogoProductoDTO> productos = productoServicio.obtenerTodas();
        model.addAttribute("misProductos", productos);
        model.addAttribute("idTienda", id);
        model.addAttribute("pedidoDTO", new PedidoDTO());
        return "formularioPedido";
    }

    @PostMapping("/realizarPedido")
    public String procesarPedido(@ModelAttribute PedidoDTO pedidoDTO, Model model) {
        try {
            PedidoDTO pedidoCreado = pedidoServicio.realizarPedido(pedidoDTO);
            model.addAttribute("mensaje", "Pedido realizado con éxito");
            return "exitoPedido";
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar el pedido");
            return "formularioPedido";
        }
    }
**/
    }

