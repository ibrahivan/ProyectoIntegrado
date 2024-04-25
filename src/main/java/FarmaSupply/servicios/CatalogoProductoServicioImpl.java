package FarmaSupply.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.CatalogoProducto;
import FarmaSupply.dtos.CatalogoProductoDTO;
import FarmaSupply.repositorios.CatalogoProductoRepositorio;

@Service
public class CatalogoProductoServicioImpl implements ICatalogoProductoServicio {

	@Autowired
	private CatalogoProductoRepositorio repositorio;

	@Autowired
	private ICatalogoProductoToDao toDao;
	@Autowired
	private ICatalogoProductoToDto toDto;

	@Override
	public CatalogoProductoDTO registrarProducto(CatalogoProductoDTO productoDTO) {

		// TODO Auto-generated method stub

		try {
			CatalogoProducto productoDao = toDao.catalogoProductoToDao(productoDTO);
			// Guardar el producto en la base de datos
			repositorio.save(productoDao);
			productoDTO.setIdCatalogoProducto(productoDao.getIdCatalogoProducto());
			return productoDTO;

		} catch (IllegalArgumentException iae) {
			System.out.println("[Error CubetaServicioImpl - registrar() ]" + iae.getMessage());
		} catch (Exception e) {
			System.out.println("[Error CubetaServicioImpl - registrar() ]" + e.getMessage());
		}
		return null;
	}

	@Override
	public CatalogoProductoDTO buscarPorId(long id) {
		// TODO Auto-generated method stub
		try {
			CatalogoProducto producto = repositorio.findById(id).orElse(null);
			if (producto != null) {
				return toDto.catalogoProductoToDto(producto);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error CatalogoProductoServicioImpl - buscarPorId()] Al buscar el producto por su id "
					+ iae.getMessage());
		}
		return null;
	}

	@Override
	public void eliminarProducto(long id) {
		// TODO Auto-generated method stub
		try {
			CatalogoProducto producto = repositorio.findById(id).orElse(null);
			if (producto != null) {
				repositorio.delete(producto);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println(
					"[Error CatalogoProductoServicioImpl - eliminarProducto()] Al eliminar un producto por su id "
							+ iae.getMessage());
		}

	}

	@Override
	public List<CatalogoProductoDTO> obtenerTodas() {
		// TODO Auto-generated method stub
		return toDto.listaCatalogoProductoToDto(repositorio.findAll());
	}
}