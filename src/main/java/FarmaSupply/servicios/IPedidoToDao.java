package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.Pedido;
import FarmaSupply.dtos.PedidoDTO;



public interface IPedidoToDao {

	/**
	 * Metodo que convierte campo a campo un objeto UsuarioDTO a DAO
	 * @param ausuarioDTO el objeto usuarioDTO
	 * @return Usuario convertido a DAO
	 */
	public Pedido pedidoToDao(PedidoDTO pedidoDTO);
	
	/**
	 * Metodo que convierte toda una lista de objetos pedidoDTO a lista de DAOs
	 * @param listaPedidoDTO lista cargadas de objetos pedidoDTO
	 * @return Lista de pedidos DAO
	 */
	public List<Pedido> listPedidoToDao(List<PedidoDTO>listaPedidoDTO);
	

}
