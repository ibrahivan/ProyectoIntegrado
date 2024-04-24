package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.Cubeta;
import FarmaSupply.dtos.CubetaDTO;

/**
 * Interface donde se declaran los metodos necesarios para el paso de un CubetaDTO a DAO
 */
public interface ICubetaToDao {

	/**
	 * Metodo que convierte campo a campo un objeto CubetaDTO a DAO
	 * @param c cubeta el objeto cubetaDTO
	 * @return Cubeta convertido a DAO
	 */
	public Cubeta cubetaToDao(CubetaDTO cubetaDTO);
	
	/**
	 * Metodo que convierte toda una lista de objetos CubetaDTO a lista de DAOs
	 * @param listaCubetaDTO lista cargadas de objetos CubetaDTO
	 * @return Lista de cubetas DAO
	 */
	public List<Cubeta> listaCubetaToDao(List<CubetaDTO>listaCubetaDTO);
	
}
