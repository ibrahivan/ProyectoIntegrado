package FarmaSupply.servicios;

import java.util.List;

import FarmaSupply.dtos.TiendaDTO;


/**
 * Interfaz del servicio para la gestión de tiendas, donde se declaran los
 * métodos correspondientes que serán implementados.
 */
public interface ITiendaServicio {

	/**
	 * Se registra a una tienda antes comprobando si ya se encuentra en la BBDD
	 * registrada la tienda 
	 * 
	 * @param tiendaDTO La tienda a registrar
	 * @return El usuario registrado
	 */
	public TiendaDTO registrarTienda(TiendaDTO tiendaDTO);

	/**
	 * Busca a una tienda por su identificador asignado en la bbdd
	 * 
	 * @param id de la tiendaDTO a buscar
	 * @return La tienda buscada
	 */
	public TiendaDTO buscarPorId(long id);


	/**
	 * Elimina una tienda de la base de datos
	 * @param id El ID de la tienda a eliminar
	 */
	public void eliminarTienda(long id);
	/**
	 * Obtiene la lista de todos las tiendas registradas
	 * 
	 * @return la lista de todos las tiendas DTOS
	 */
	public List<TiendaDTO> obtenerTodas();
	
}
