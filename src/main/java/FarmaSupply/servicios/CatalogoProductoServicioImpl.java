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
	private ICatalogoProductoToDto toDto;

	@Override
	public CatalogoProductoDTO buscarPorId(long id) {
		// TODO Auto-generated method stub
		try {
			CatalogoProducto catalogoProducto = repositorio.findById(id).orElse(null);
			if (catalogoProducto != null) {
				return toDto.catalogoProductoToDto(catalogoProducto);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error CatalogoProductoServicioImpl - buscarPorId()] Al buscar el producto por su id "
					+ iae.getMessage());
		}
		return null;
	}

	@Override
	public void eliminarCatalogoProducto(long id) {
		// TODO Auto-generated method stub
		try {
			CatalogoProducto catalogoProducto = repositorio.findById(id).orElse(null);
			if (catalogoProducto != null) {
				repositorio.delete(catalogoProducto);
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