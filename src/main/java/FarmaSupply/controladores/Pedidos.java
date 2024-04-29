package FarmaSupply.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import FarmaSupply.dtos.CatalogoProductoDTO;
import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.servicios.ICatalogoProductoServicio;
import FarmaSupply.servicios.IPedidoServicio;
import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/privada")
public class Pedidos {

	@Autowired
    private IPedidoServicio pedidoServicio;

    @Autowired
    private ICatalogoProductoServicio productoServicio;
    

    @GetMapping("/realizarPedido/{id}")
    public String mostrarFormularioPedido(@PathVariable Long id, Model model) {
    	try {
        List<CatalogoProductoDTO> misProductos = productoServicio.obtenerTodas();
        model.addAttribute("misPropductos", misProductos);
        model.addAttribute("idTienda", id);
        model.addAttribute("pedidoDTO", new PedidoDTO());
    	}
        return "formularioPedido";
    }
}
