package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.CatalogoProducto;

import FarmaSupply.dtos.CatalogoProductoDTO;

/**
 * Servicio que implementa los metodos de la interface
 * {@link ICatalogoProductoServicio} y en esta clase es donde se entra al
 * detalle de la logica de dichos métodos para la gestión del CatalogoProductos.
 */
@Service
public class CatalogoProductoToDaoImpl implements ICatalogoProductoToDao {

	@Autowired
	private IPedidoToDao pedidoToDao;

	@Override
	public CatalogoProducto catalogoProductoToDao(CatalogoProductoDTO catalogoProductoDTO) {

		try {
			CatalogoProducto catalogoProductoDao = new CatalogoProducto();
			catalogoProductoDao.setIdCatalogoProducto(catalogoProductoDTO.getIdCatalogoProducto());
			catalogoProductoDao.setNombreProducto(catalogoProductoDTO.getNombreProducto());
			catalogoProductoDao.setPrecioUnitario(catalogoProductoDTO.getPrecioUnitario());
			catalogoProductoDao.setCantidad(catalogoProductoDTO.getCantidad());
			catalogoProductoDao.setDescripcion(catalogoProductoDTO.getDescripcion());
			if (catalogoProductoDao.getList_Cat_Ped().size() > 0) {
				catalogoProductoDao.setList_Cat_Ped(pedidoToDao.listPedidoToDao(catalogoProductoDTO.getmisPedidos()));
			}

			return catalogoProductoDao;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR CatalogoProductoToDaoImpl - toCatalogoProductoToDao()] - Al convertir CatalogoProductoDTO a CatalogoProductoDAO (return null): "
							+ e);
			return null;
		}

	}

	@Override
	public List<CatalogoProducto> listCatalogoProductoToDao(List<CatalogoProductoDTO> listaCatalogoProductoDTO) {
		List<CatalogoProducto> listaCatalogoProductoDao = new ArrayList<>();

		try {
			for (CatalogoProductoDTO catalogoProductoDTO : listaCatalogoProductoDTO) {
				listaCatalogoProductoDao.add(catalogoProductoToDao(catalogoProductoDTO));
			}

			return listaCatalogoProductoDao;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR CatalogoProductoToDaoImpl - toListCatalogoProductoDao()] - Al convertir lista de CatalogoProductoDTO a lista de CatalogoProductoDAO (return null): "
							+ e);
		}
		return null;
	}

}
