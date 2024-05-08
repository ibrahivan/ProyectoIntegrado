package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.Pedido;
import FarmaSupply.dtos.PedidoDTO;

/**
 * Servicio que implementa los metodos de la interface {@link IUPedidoToDao} y
 * en esta clase es donde se entra al detalle de la logica de dichos métodos
 * para el paso de pedidoDTO a DAO
 */
@Service
public class PedidoToDaoImpl implements IPedidoToDao {

	@Autowired
	private ICatalogoProductoToDao catalogoToDao;

	@Autowired
	private IMotoToDao motoToDao;
	@Autowired
	private ICubetaToDao cubetaToDao;
	@Override
	public Pedido pedidoToDao(PedidoDTO pedidoDTO) {

		try {
			Pedido pedidoDao = new Pedido();
			pedidoDao.setIdPedido(pedidoDTO.getIdPedido());
			pedidoDao.setPrecioPedido(pedidoDTO.getPrecioPedido());
			pedidoDao.setEstado_pedido(pedidoDTO.getEstadoPedido());

			if (pedidoDTO.getMisCatalogoProducto().size() > 0) {
				pedidoDao
						.setList_Ped_Cat((catalogoToDao.listCatalogoProductoToDao(pedidoDTO.getMisCatalogoProducto())));

			}

			if (pedidoDTO.getMisCubetas().size() > 0) {
				pedidoDao.setList_Ped_Cub((cubetaToDao.listaCubetaToDao(pedidoDTO.getMisCubetas())));
			}

			if (pedidoDTO.getMisMotos().size() > 0) {
				pedidoDao.setList_Ped_Moto((motoToDao.listaMotoToDao(pedidoDTO.getMisMotos())));
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
