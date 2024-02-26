package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.Tienda;
import FarmaSupply.dtos.TiendaDTO;

/**
 * Interface donde se declaran los metodos que son necesarios para el paso de una entidad
 * tienda (DAO) a tiendaDTO
 */
public interface ITiendaToDto {

	/**
	 * Método que convierte campo a campo un objeto entidad tienda a tiendaDTO
	 * @param t , tienda a transformar
	 * @return El DTO de la tienda
	 */
	public TiendaDTO tiendaToDto(Tienda t);
	
	/**
	 * Metodo que convierte todos los objetos entidad tienda DAO a una lista TiendaDTO 
	 * @param listaTienda
	 * @return
	 */
	public List<TiendaDTO> listaTiendaToDto(List<Tienda> listaTienda);
}
