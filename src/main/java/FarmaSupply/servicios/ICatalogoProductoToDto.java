package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.CatalogoProducto;
import FarmaSupply.dtos.CatalogoProductoDTO;

public interface ICatalogoProductoToDto {

	/**
	 * Método que convierte campo a campo un objeto entidad CatalogoProducto a catalogoProductoDTO
	 * @param cP , catalogoProducto a transformar
	 * @return El DTO del catalogoProducto
	 */
	public CatalogoProductoDTO catalogoProductoToDto(CatalogoProducto cP);
	
	/**
	 * Metodo que convierte todos los objetos entidad catalogoProducto DAO a una lista CatalogoProductoDTO 
	 * @param listaCatalogoProducto
	 * @return
	 */
	public List<CatalogoProductoDTO> listaCatalogoProductoToDto(List<CatalogoProducto> listaCatalogoProducto);
}
