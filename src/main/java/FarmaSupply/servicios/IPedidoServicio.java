package FarmaSupply.servicios;

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
	 * @param pedidoDTO El pedido a realizar
	 * @return El pedido realizado
	 */
	public PedidoDTO realizarPedido(PedidoDTO pedidoDTO);

}
