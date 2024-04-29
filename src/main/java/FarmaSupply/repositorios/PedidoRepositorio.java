package FarmaSupply.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import FarmaSupply.daos.Pedido;

/**
 * Interfaz que define un repositorio para la entidad {@link Pedido}. Extiende
 * la interfaz JpaRepository de Spring Data para realizar operaciones CRUD y
 * otras consultas relacionadas con la entidad Pedido en la base de datos.
 */
public interface PedidoRepositorio extends JpaRepository<Pedido, Long>{

}
