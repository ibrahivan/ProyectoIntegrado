package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.Pedido;
import FarmaSupply.daos.Tienda;
import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.repositorios.TiendaRepositorio;

/**
 * Servicio que implementa los metodos de la interface {@link IUPedidoToDao} y
 * en esta clase es donde se entra al detalle de la logica de dichos métodos
 * para el paso de pedidoDTO a DAO
 */
@Service
public class PedidoToDaoImpl implements IPedidoToDao {

	@Autowired
	private IDetallePedidoToDao detallePedidoToDao;


	@Autowired
	private TiendaRepositorio tiendaRepositorio;

	@Override
	public Pedido pedidoToDao(PedidoDTO pedidoDTO) {

		try {
			Optional<Tienda> tiendaPropietaria = tiendaRepositorio.findById(pedidoDTO.getIdPedido_Tie());

			Pedido pedidoDao = new Pedido();
			pedidoDao.setIdPedido(pedidoDTO.getIdPedido());
			pedidoDao.setPrecioPedido(pedidoDTO.getPrecioPedido());
			pedidoDao.setEstadoPedido(pedidoDTO.getEstadoPedido());
			pedidoDao.setIdPedido_Tie(tiendaPropietaria.get());
			pedidoDao.setIdentificadorPedido(pedidoDTO.getIdentificadorPedido());
			if (pedidoDTO.getMisDetallesPedidos().size() > 0) {
				pedidoDao.setList_Ped_Det((detallePedidoToDao.listdetallePedidoToDao(pedidoDTO.getMisDetallesPedidos())));

			}

		
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
