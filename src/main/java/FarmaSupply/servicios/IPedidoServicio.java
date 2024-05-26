package FarmaSupply.servicios;

import FarmaSupply.dtos.DetallePedidoDTO;
import FarmaSupply.dtos.TiendaDTO;

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
	public DetallePedidoDTO realizarPedido(DetallePedidoDTO detallePedidoDTO, TiendaDTO tiendaDTO);

}
