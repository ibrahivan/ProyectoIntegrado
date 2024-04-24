package FarmaSupply.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import FarmaSupply.daos.Tienda;


/**
 * Interfaz que define un repositorio para la entidad {@link Tienda}. Extiende
 * la interfaz JpaRepository de Spring Data para realizar operaciones CRUD y
 * otras consultas relacionadas con la entidad Tienda en la base de datos.
 */

public interface TiendaRepositorio  extends JpaRepository<Tienda, Long> {
	/**
	 * Busca la primera tienda que tieneel nombre especificado
	 * @param nombreTienda El nombre de la tienda a buscar.
	 * @return La primera tienda encontrada con el nombre 
	 *         especificada o null en caso contrario.
	 */
	public Tienda findByNombreTienda(String nombreTienda);
	
	/**
	 * Checkea si existe una tienda con la direccion de la tienda especificado.
	 * @param direccionTienda LA direccion de la tienda a buscar.
	 * @return true si existe una tienda con la direccion de tienda especificado, false en caso contrario.
	 */
	public boolean existsByDireccionTienda(String direccionTienda);

}
