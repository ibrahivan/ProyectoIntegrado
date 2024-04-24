package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.Cubeta;
import FarmaSupply.dtos.CubetaDTO;

/**
 * Interface donde se declaran los metodos que son necesarios para el paso de
 * una entidad cubeta (DAO) a cubetaDTO
 */
public interface ICubetaToDto {

	/**
	 * Método que convierte campo a campo un objeto entidad Cubeta a CubetaDTO
	 * 
	 * @param c La cubeta a transformar
	 * @return El DTO de la cubeta
	 */
	public CubetaDTO cubetaToDto(Cubeta c);

	/**
	 * Metodo que convierte todos los objetos entidad Cubeta a una lista CubetaDTO
	 * 
	 * @param listaCubeta
	 * @return
	 */
	public List<CubetaDTO> listaCubetaToDto(List<Cubeta> listaCubeta);
}
