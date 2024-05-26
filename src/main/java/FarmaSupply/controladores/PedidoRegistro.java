package FarmaSupply.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import FarmaSupply.dtos.CatalogoProductoDTO;
import FarmaSupply.dtos.DetallePedidoDTO;
import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.dtos.TiendaDTO;
import FarmaSupply.servicios.ICatalogoProductoServicio;
import FarmaSupply.servicios.IPedidoServicio;
import FarmaSupply.servicios.ITiendaServicio;

@Controller
@RequestMapping("/privada")
public class PedidoRegistro {

    @Autowired
    private IPedidoServicio pedidoServicio;

    @Autowired
    private ITiendaServicio tiendaServicio;

    @Autowired
    private ICatalogoProductoServicio productoServicio;

    @GetMapping("/realizarPedido/{id}")
    public String registrarPedidoGet(@PathVariable Long id, Model model) {
        try {
            // Obtengo la tienda actual
            TiendaDTO tiendaActual = tiendaServicio.buscarPorId(id);
            // Obtengo todos los productos de bbdd
            List<CatalogoProductoDTO> productos = productoServicio.obtenerTodas();
            // Genero el nuevo detallePedidoDTO
            DetallePedidoDTO detallePedidoDTO = new DetallePedidoDTO();

            // Los llevo al model
            model.addAttribute("misProductos", productos);
            model.addAttribute("tiendaDTO", tiendaActual);
            model.addAttribute("idTienda", tiendaActual.getId());
            model.addAttribute("detallePedidoDTO", detallePedidoDTO);

            return "registroPedido";
        } catch (Exception e) {
            model.addAttribute("error", "Error al mostrar el formulario para crear un nuevo pedido");
            return "listadoTiendas";
        }
    }

    @PostMapping("/realizarPedido/{id}")
    public String registrarPedidoPost(@PathVariable Long id, @ModelAttribute DetallePedidoDTO detallePedidoDTO, @RequestParam List<Long> productosSeleccionadosIds ,Model model) {
    	try {
    		// Obtener la tienda actual
            TiendaDTO tiendaActual = tiendaServicio.buscarPorId(id);
            //genero objeto pedidoDTO
            PedidoDTO pedidoDTO = new PedidoDTO();
            // Convertir los IDs de productos seleccionados a objetos CatalogoProductoDTO
	        List<CatalogoProductoDTO> productosSeleccionados = new ArrayList<>();
	        for(Long productoId : productosSeleccionadosIds) {
	            CatalogoProductoDTO producto = productoServicio.buscarPorId(productoId); // Método para obtener el producto por su ID
	            productosSeleccionados.add(producto);
	        }
	        
	        detallePedidoDTO.setProductosSeleccionados(productosSeleccionados);
            // Realizar el pedido que guardaremos los detalles y los asociaremos con el pedido
            DetallePedidoDTO nuevoPedido = pedidoServicio.realizarPedido(detallePedidoDTO, tiendaActual);
            //Añadir los detalles al pedido
            List<DetallePedidoDTO> listaDetallesPedidos = pedidoDTO.getMisDetallesPedidos();
            listaDetallesPedidos.add(nuevoPedido);
            pedidoDTO.setMisDetallesPedidos(listaDetallesPedidos);
            
            // Añadir el pedido a la lista de pedidos de la tienda
            List<PedidoDTO> listaPedidos = tiendaActual.getMisPedidos();
            listaPedidos.add(pedidoDTO);
            tiendaActual.setMisPedidos(listaPedidos);

            // Actualizar el modelo con los pedidos de la tienda
            model.addAttribute("mensajePedidoRealizadoExitoso", "Pedido realizado con éxito");
            model.addAttribute("misPedidos", tiendaActual.getMisPedidos());

            return "listadoPedidos";
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar el pedido");
            return "registroPedido";
        }
    }
}
