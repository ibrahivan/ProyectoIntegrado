package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.dtos.CubetaDTO;
/**
 * Interfaz del servicio para la gestión de cubetas, donde se declaran los
 * métodos correspondientes que serán implementados.
 */
public interface ICubetaServicio {
	/**
	 * Se registra a una cubeta antes comprobando si ya se encuentra en la BBDD
	 * registrada la cubeta
	 * 
	 * @param cubetaDTO La cubeta a registrar
	 * @return La cubeta registrada
	 */
	public CubetaDTO registrarCubeta(CubetaDTO cubetaDTO);

	/**
	 * Busca a una cubeta por su identificador asignado en la bbdd
	 * 
	 * @param id de la cubetaDTO a buscar
	 * @return La cubeta buscada
	 */
	public CubetaDTO buscarPorId(long id);

	/**
	 * Elimina una cubeta de la base de datos
	 * 
	 * @param id El ID de la cubeta a eliminar
	 */
	public void eliminarCubeta(long id);

	/**
	 * Obtiene la lista de todos las cubetas registradas
	 * 
	 * @return la lista de todos las cubetas DTOS
	 */
	public List<CubetaDTO> obtenerTodas();
}
