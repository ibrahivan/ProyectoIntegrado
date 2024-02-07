package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.CatalogoProducto;
import FarmaSupply.dtos.CatalogoProductoDTO;


public interface ICatalogoProductoToDao {
	/**
	 * Metodo que convierte campo a campo un objeto catalogoProductoDTO a DAO
	 * @param el objeto catalogoProductoDTO
	 * @return CatalogoProducto convertido a DAO
	 */
	public CatalogoProducto catalogoProductoToDao(CatalogoProductoDTO catalogoProductoDTO);

	/**
	 * Metodo que convierte toda una lista de objetos catalogoProductoDTO a lista de DAOs
	 * 
	 * @param listaCatalogoProducto lista cargadas de objetos catalogoProductoDTO
	 * @return Lista de CatalogoProducto DAO
	 */
	public List<CatalogoProducto> listCatalogoProductoToDao(List<CatalogoProductoDTO> listaCatalogoProductoDTO);

}
