package FarmaSupply.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import FarmaSupply.daos.CatalogoProducto;


/**
 * Interfaz que define un repositorio para la entidad {@link CatalogoProducto}. Extiende
 * la interfaz JpaRepository de Spring Data para realizar operaciones CRUD y
 * otras consultas relacionadas con la entidad CatalogoProducto en la base de datos.
 */

public interface CatalogoProductoRepositorio extends JpaRepository<CatalogoProducto, Long> {

}
