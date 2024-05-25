package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.DetallePedido;
import FarmaSupply.dtos.DetallePedidoDTO;

/**
 * Interface donde se declaran los metodos necesarios para el paso de un
 * detallePedidoDTO a DAO
 */
public interface IDetallePedidoToDao {

	/**
	 * Metodo que convierte campo a campo un objetodetallePedidoDTO a DAO
	 * 
	 * @param detallePedidoDTO objeto a convertir a Dao
	 * @return detalleEPedido convertido a DAO
	 */
	public DetallePedido detallePedidoToDao(DetallePedidoDTO detallePedidoDTO);

	/**
	 * Metodo que convierte toda una lista de objetos detallePedidoDTO a lista de DAOs
	 * 
	 * @param list lista cargadas de objetos detallePedidoDTO
	 * @return Lista de detallesPedidos DAO
	 */
	public List<DetallePedido> listdetallePedidoToDao(List<DetallePedidoDTO> list);
}
