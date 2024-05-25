package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;

import FarmaSupply.daos.DetallePedido;
import FarmaSupply.dtos.DetallePedidoDTO;

public class DetallePedidoToDaoImpl implements IDetallePedidoToDao{

	@Override
	public DetallePedido detallePedidoToDao(DetallePedidoDTO detallePedidoDTO) {
		try {
		DetallePedido detallePedidoDao = new DetallePedido();
		detallePedidoDao.setIdDetallePedido(detallePedidoDTO.getIdDetallePedido());
		detallePedidoDao.setCantidadDetalle(detallePedidoDTO.getCantidadDetalle());
		detallePedidoDao.setPrecioDetalle(detallePedidoDTO.getPrecioDetalle());
		
		return detallePedidoDao;
		
	} catch (Exception e) {
		System.out.println(
				"\n[ERROR DetallePedidoToDaoImpl - todetallePedidoDao()] - Al convertir detallePedidoDTO a detallePedidoDAO (return null): "
						+ e);
		return null;
	}
	}

	@Override
	public List<DetallePedido> listdetallePedidoToDao(List<DetallePedidoDTO> list) {
		try {
			List<DetallePedido> listaDetallePedidoDao = new ArrayList<>();
			for (DetallePedidoDTO detallePedidoDTO : list) {
				listaDetallePedidoDao.add(detallePedidoToDao(detallePedidoDTO));
			}

			return listaDetallePedidoDao;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR DetallePedidoToDaoImpl - toListDetallePedidoDao()] - Al convertir lista de detallePedidoDTO a lista de detallePedidoDAO (return null): "
							+ e);
		}
		return null;
	}
}


