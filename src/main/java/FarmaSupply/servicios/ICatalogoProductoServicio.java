package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.dtos.CatalogoProductoDTO;

/**
 * Interfaz del servicio para la gestión de cubetas, donde se declaran los
 * métodos correspondientes que serán implementados.
 */
public interface ICatalogoProductoServicio {
	/**
	 * Busca a un producto por su identificador asignado en la bbdd
	 * 
	 * @param id del productoDTO a buscar
	 * @return El producto buscado
	 */
	public CatalogoProductoDTO buscarPorId(long id);

	/**
	 * Elimina un producto de la base de datos
	 * 
	 * @param id El ID del producto a eliminar
	 */
	public void eliminarCatalogoProducto(long id);

	/**
	 * Obtiene la lista de todos los CatalogoProductos registradas
	 * 
	 * @return la lista de todos las CatalogoProductos DTOS
	 */
	public List<CatalogoProductoDTO> obtenerTodas();
}
