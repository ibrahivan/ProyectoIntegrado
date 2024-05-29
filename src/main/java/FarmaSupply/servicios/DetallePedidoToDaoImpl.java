package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.CatalogoProducto;
import FarmaSupply.daos.DetallePedido;
import FarmaSupply.daos.Pedido;
import FarmaSupply.dtos.DetallePedidoDTO;
import FarmaSupply.repositorios.CatalogoProductoRepositorio;
import FarmaSupply.repositorios.PedidoRepositorio;

@Service
public class DetallePedidoToDaoImpl implements IDetallePedidoToDao{

	@Autowired
	private PedidoRepositorio pedidoRepositorio;
	@Autowired
	private CatalogoProductoRepositorio productoRepositorio;
	@Override
	public DetallePedido detallePedidoToDao(DetallePedidoDTO detallePedidoDTO) {
		try {
		Optional<Pedido> pedidoPropietario = pedidoRepositorio.findById(detallePedidoDTO.getIdDet_Ped());
		Optional<CatalogoProducto> productoPropietario = productoRepositorio.findById(detallePedidoDTO.getIdDet_Cat());
		DetallePedido detallePedidoDao = new DetallePedido();
		detallePedidoDao.setIdDetallePedido(detallePedidoDTO.getIdDetallePedido());
		detallePedidoDao.setCantidadDetalle(detallePedidoDTO.getCantidadDetalle());
		detallePedidoDao.setPrecioDetalle(detallePedidoDTO.getPrecioDetalle());
		detallePedidoDao.setIdDet_Cat(productoPropietario.get());
		detallePedidoDao.setIdDet_Ped(pedidoPropietario.get());
		
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


