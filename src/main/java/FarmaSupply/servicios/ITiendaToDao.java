package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.Tienda;
import FarmaSupply.dtos.TiendaDTO;



/**
 * Interface donde se declaran los metodos necesarios para el paso de un tiendaDTO a DAO
 */
public interface ITiendaToDao {

	/**
	 * Metodo que convierte campo a campo un objeto tiendaDTO a DAO
	 * @param el objeto tiendaDTO
	 * @return Tienda convertido a DAO
	 */
	public Tienda tiendaToDao(TiendaDTO tiendaDTO);

	/**
	 * Metodo que convierte toda una lista de objetos tiendaDTO a lista de DAOs
	 * @param listaTiendaDTO lista cargadas de objetos tiendaDTO
	 * @return Lista de tiendas DAO
	 */
	public List<Tienda> listTiendaToDao(List<TiendaDTO> listaTiendaDTO);


}
