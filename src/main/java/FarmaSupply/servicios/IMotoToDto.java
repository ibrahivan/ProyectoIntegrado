package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.Moto;
import FarmaSupply.dtos.MotoDTO;

/**
 * Interface donde se declaran los metodos que son necesarios para el paso de una entidad
 * moto (DAO) a motoDTO
 */
public interface IMotoToDto {

	/**
	 * Método que convierte campo a campo un objeto entidad Moto a MotoDTO
	 * @param m El moto a transformar
	 * @return El DTO de la moto
	 */
	public MotoDTO motoToDto(Moto m);
	
	/**
	 * Metodo que convierte todos los objetos entidad Moto a una lista MotoDTO 
	 * @param listaMoto
	 * @return
	 */
	public List<MotoDTO> listaMotoToDto(List<Moto> listaMoto);
}
