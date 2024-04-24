package FarmaSupply.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import FarmaSupply.daos.Cubeta;

/**
 * Interfaz que define un repositorio para la entidad {@link Cubeta}. Extiende
 * la interfaz JpaRepository de Spring Data para realizar operaciones CRUD y
 * otras consultas relacionadas con la entidad Cubeta en la base de datos.
 */

public interface CubetaRepositorio extends JpaRepository<Cubeta, Long> {
	/**
	 * Busca la primera cubeta que tiene el numero especificado
	 * 
	 * @param numeroCubeta el numero de cubeta a buscar.
	 * @return La primera cubeta encontrada con el numero especificada o null en
	 *         caso contrario.
	 */
	public Cubeta findByNumeroCubeta(String numeroCubeta);

}
