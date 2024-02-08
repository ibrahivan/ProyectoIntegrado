package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.Pedido;
import FarmaSupply.dtos.PedidoDTO;

/**
 * Interface donde se declaran los metodos que son necesarios para el paso de una entidad
 * Pedido (DAO) a PedidoDTO
 */

public interface IPedidoToDto {

	/**
	 * Método que convierte campo a campo un objeto entidad Pedido a PedidoDTO
	 * @param p El pedido a transformar
	 * @return El DTO del pedido
	 */
	public PedidoDTO pedidoToDto(Pedido p);
	
	/**
	 * Metodo que convierte todos los objetos entidad Pedido a una lista PedidoDTO 
	 * @param listaPedido
	 * @return
	 */
	public List<PedidoDTO> listaPedidoToDto(List<Pedido> listaPedido);
}
