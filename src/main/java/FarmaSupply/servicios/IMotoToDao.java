package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.Moto;
import FarmaSupply.dtos.MotoDTO;

/**
 * Interface donde se declaran los metodos necesarios para el paso de un MotoDTO
 * a DAO
 */
public interface IMotoToDao {

	/**
	 * Metodo que convierte campo a campo un objeto MotoDTO a DAO
	 * 
	 * @param a motoDTO el objeto motoDTO
	 * @return Moto convertido a DAO
	 */
	public Moto motoToDao(MotoDTO motoDTO);

	/**
	 * Metodo que convierte toda una lista de objetos MotoDTO a lista de DAOs
	 * 
	 * @param listaPedidoDTO lista cargadas de objetos MotoDTO
	 * @return Lista de motos DAO
	 */
	public List<Moto> listaMotoToDao(List<MotoDTO> listaMotoDTO);

}
