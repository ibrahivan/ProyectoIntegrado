package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.daos.Moto;
import FarmaSupply.dtos.MotoDTO;

/**
 * Interfaz del servicio para la gestión de motos, donde se declaran los
 * métodos correspondientes que serán implementados.
 */
public interface IMotoServicio {
	/**
	 * Se registra a una moto antes comprobando si ya se encuentra en la BBDD
	 * registrada la moto
	 * 
	 * @param motoDTO La moto a registrar
	 * @return La moto registrada
	 */
	public MotoDTO registrarMoto(MotoDTO motoDTO);

	/**
	 * Busca a una moto por su identificador asignado en la bbdd
	 * 
	 * @param id de la motoDTO a buscar
	 * @return La moto buscada
	 */
	public MotoDTO buscarPorId(long id);

	/**
	 * Elimina una moto de la base de datos
	 * 
	 * @param id El ID de la moto a eliminar
	 */
	public void eliminarMoto(long id);

	/**
	 * Obtiene la lista de todos las motos registradas
	 * 
	 * @return la lista de todos las motos DTOS
	 */
	public List<MotoDTO> obtenerTodas();
	/**
	 * Obtiene la lista de todos las motos que estan libres
	 * 
	 * @return la lista de todos las motos DTOS
	 */

	public List<Moto> obtenerMotosLibres();
}
