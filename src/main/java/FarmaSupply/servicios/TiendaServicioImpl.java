package FarmaSupply.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.Tienda;
import FarmaSupply.daos.Usuario;
import FarmaSupply.dtos.TiendaDTO;
import FarmaSupply.repositorios.TiendaRepositorio;
import FarmaSupply.repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;

/**
 * Servicio que implementa los metodos de la interface {@link ITiendaServicio} y
 * en esta clase es donde se entra al detalle de la logica de dichos métodos
 * para la gestión de usuarios.
 */
@Service
@Transactional
public class TiendaServicioImpl implements ITiendaServicio {

	@Autowired
	private TiendaRepositorio repositorio;

	@Autowired
	private ITiendaToDao toDao;

	@Autowired
	private ITiendaToDto toDto;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public TiendaDTO registrarTienda(TiendaDTO tiendaDTO) {
		// TODO Auto-generated method stub
		try {
			Optional<Usuario> usuarioPropietario = usuarioRepositorio.findById(tiendaDTO.getIdTienda_Usu());
			// Comprueba si ya existe una tienda con el nombre que quiere registrar
			Tienda tiendaDaoNombre = repositorio.findByNombreTienda(tiendaDTO.getNombreTienda());

			if (tiendaDaoNombre != null) {
				return null; // Si no es null es que ya está registrada
			}

			// Ahora se comprueba si hay una tienda por la direccion que quiere registrar
			boolean yaExisteDireccion = repositorio.existsByDireccionTienda(tiendaDTO.getDireccionTienda());

			if (yaExisteDireccion) {
				// Si es que ya hay una tienda con esa direccion se setea a null para controlar
				// el
				// error en controlador
				tiendaDTO.setDireccionTienda(null);
				return tiendaDTO;
			}

			// Si llega a esta línea es que no existe la tienda con el nombre y direccion
			// que se quiere
			// registrar
			
			Tienda tiendaDao = toDao.tiendaToDao(tiendaDTO);
			if (usuarioPropietario.isPresent()) {
				tiendaDao.setIdTienda_Usu(usuarioPropietario.get());

			}

			// Guardar la tienda en la base de datos
			repositorio.save(tiendaDao);
			tiendaDTO.setId(tiendaDao.getIdTienda());

			return tiendaDTO;

		} catch (IllegalArgumentException iae) {
			System.out.println("[Error TiendaServicioImpl - registrar() ]" + iae.getMessage());
		} catch (Exception e) {
			System.out.println("[Error TiendaServicioImpl - registrar() ]" + e.getMessage());
		}
		return null;
	}

	@Override
	public TiendaDTO buscarPorId(long id) {
		// TODO Auto-generated method stub
		try {
			Tienda tienda = repositorio.findById(id).orElse(null);
			if (tienda != null) {
				return toDto.tiendaToDto(tienda);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println(
					"[Error TiendaServicioImpl - buscarPorId()] Al buscar la tienda por su id " + iae.getMessage());
		}
		return null;
	}

	@Override
	public List<TiendaDTO> obtenerTodas() {
		// TODO Auto-generated method stub
		return toDto.listaTiendaToDto(repositorio.findAll());
	}

	@Override
	public void eliminarTienda(long id) {
		// TODO Auto-generated method stub
		try {
			Tienda tienda = repositorio.findById(id).orElse(null);
			if (tienda != null) {
				repositorio.delete(tienda);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error TiendaServicioImpl - eliminarTienda()] Al eliminar una tienda por su id "
					+ iae.getMessage());
		}

	}

}
