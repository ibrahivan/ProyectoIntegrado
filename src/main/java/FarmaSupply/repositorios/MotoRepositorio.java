package FarmaSupply.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import FarmaSupply.daos.Moto;


/**
 * Interfaz que define un repositorio para la entidad {@link Moto}. Extiende
 * la interfaz JpaRepository de Spring Data para realizar operaciones CRUD y
 * otras consultas relacionadas con la entidad Moto en la base de datos.
 */

public interface MotoRepositorio extends JpaRepository<Moto, Long> {
	/**
	 * Busca la primera moto que tiene la matricula especificada
	 * 
	 * @param matriculaMoto La matricula de moto a buscar.
	 * @return La primera moto encontrada con la matricula especificada o null en
	 *         caso contrario.
	 */
	public Moto findByMatriculaMoto(String matriculaMoto);
}
