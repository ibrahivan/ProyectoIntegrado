package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.dtos.CatalogoProductoDTO;
import FarmaSupply.dtos.PedidoDTO;

/**
 * Interfaz del servicio para la gestión de pedidos, donde se declaran los
 * métodos correspondientes que serán implementados.
 */
public interface IPedidoServicio {
	/**
	 * Se registra a un pedido 
	 * El pedido registrado
	 * 
	 * @return El pedido realizado
	 */
	public PedidoDTO realizarPedido(PedidoDTO pedidoDTO);

}
