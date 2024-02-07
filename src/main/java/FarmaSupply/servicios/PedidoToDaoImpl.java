package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;

import FarmaSupply.daos.Pedido;
import FarmaSupply.dtos.PedidoDTO;


public class PedidoToDaoImpl implements IPedidoToDao {

	@Override
	public Pedido pedidoToDao(PedidoDTO pedidoDTO) {
		

		try {
			Pedido pedidoDao = new Pedido();
			
		
			
			return pedidoDao;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR PedidoToDaoImpl - toPedidoDao()] - Al convertir pedidoDTO a pedidoDAO (return null): "
							+ e);
			return null;
		}
	}

	@Override
	public List<Pedido> listPedidoToDao(List<PedidoDTO> listaPedidoDTO) {
		

		try {
			List<Pedido> listaPedidoDao = new ArrayList<>();
			for (PedidoDTO pedidoDTO : listaPedidoDTO) {
				listaPedidoDao.add(pedidoToDao(pedidoDTO));
			}

			return listaPedidoDao;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR PedidoToDaoImpl - toListPedidoDao()] - Al convertir lista de pedidoDTO a lista de pedidoDAO (return null): "
							+ e);
		}
		return null;
	}

}
