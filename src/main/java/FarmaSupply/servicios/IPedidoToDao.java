package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.Pedido;
import FarmaSupply.dtos.PedidoDTO;

/**
 * Interface donde se declaran los metodos necesarios para el paso de un
 * pedidoDTO a DAO
 */
public interface IPedidoToDao {

	/**
	 * Metodo que convierte campo a campo un objeto UsuarioDTO a DAO
	 * 
	 * @param pedidoDTO objeto a convertir a Dao
	 * @return Pedido convertido a DAO
	 */
	public Pedido pedidoToDao(PedidoDTO pedidoDTO);

	/**
	 * Metodo que convierte toda una lista de objetos pedidoDTO a lista de DAOs
	 * 
	 * @param list lista cargadas de objetos pedidoDTO
	 * @return Lista de pedidos DAO
	 */
	public List<Pedido> listPedidoToDao(List<PedidoDTO> list);

}
