package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.Pedido;
import FarmaSupply.dtos.DetallePedidoDTO;
import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.dtos.TiendaDTO;

/**
 * Interfaz del servicio para la gestión de pedidos, donde se declaran los
 * métodos correspondientes que serán implementados.
 */
public interface IPedidoServicio {
	/**
	 * Se registra a un pedido 
	 * El pedido registrado
	 * @param cantidades 
	 * 
	 * @return El pedido realizado
	 */
	public List<DetallePedidoDTO> realizarPedido(DetallePedidoDTO detallePedidoDTO, TiendaDTO tiendaDTO, List<Double> cantidades);
	/**
	 * Obtiene la lista de todos los pedidos que estan en el estado Pendiente
	 * 
	 * @return la lista de todos los pedidos DTOS
	 */
	 public List<Pedido> obtenerPedidosPendientes() ;
	 
	 /**
	 *  Asigna el pedido seleccionado a la moto seleccionada
	 */
	 public void asignarPedidoAMoto(Long idPedido, Long idMoto);
	 
	 
	 /**
	 *  Confirma la entrega de pedidos
	 */
	 public void confirmarEntrega(List<Long> idPedido);
	 /**
		 * Busca a un pedido por su identificador asignado en la bbdd
		 * 
		 * @param id del pedidoDTO a buscar
		 * @return El pedidoDTO buscado
		 */
		public PedidoDTO buscarPorId(long id);
}
