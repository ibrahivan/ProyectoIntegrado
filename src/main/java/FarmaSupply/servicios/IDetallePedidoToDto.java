package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.DetallePedido;
import FarmaSupply.dtos.DetallePedidoDTO;

/**
 * Interface donde se declaran los metodos que son necesarios para el paso de
 * una entidad DEtallePedido (DAO) a detallePedidoDTO
 */

public interface IDetallePedidoToDto {

	
	/**
	 * Método que convierte campo a campo un objeto entidad DetallePedido a DetallePedidoDTO
	 * 
	 * @param p El detallePedido a transformar
	 * @return El DTO del DetallePedido
	 */
	public DetallePedidoDTO detallePedidoToDto(DetallePedido dP);

	/**
	 * Metodo que convierte todos los objetos entidad Pedido a una lista detallePedidoDTO
	 * 
	 * @param listaDetallePedido
	 * @param b 
	 * @return
	 */
	public List<DetallePedidoDTO> listaDetallePedidoToDto(List<DetallePedido> listaDetallePedido);
}
