package FarmaSupply.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import FarmaSupply.daos.DetallePedido;

/**
 * Interfaz que define un repositorio para la entidad {@link DetallePedido}. Extiende
 * la interfaz JpaRepository de Spring Data para realizar operaciones CRUD y
 * otras consultas relacionadas con la entidad CatalogoProducto en la base de datos.
 */

public interface DetallePedidoRepositorio  extends JpaRepository<DetallePedido, Long> {

}
