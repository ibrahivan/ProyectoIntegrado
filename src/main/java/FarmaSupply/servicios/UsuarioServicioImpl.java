package FarmaSupply.servicios;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.Usuario;
import FarmaSupply.dtos.UsuarioDTO;
import FarmaSupply.repositorios.UsuarioRepositorio;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

/**
 * Servicio que implementa los metodos de la interface {@link IUsuarioServicio}
 * y en esta clase es donde se entra al detalle de la logica de dichos métodos
 * para la gestión de usuarios.
 */
@Service
@Transactional
public class UsuarioServicioImpl implements IUsuarioServicio {

	@Autowired
	private UsuarioRepositorio repositorio;

	@Autowired
	private IUsuarioToDao toDao;

	@Autowired
	private IUsuarioToDto toDto;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IEmailServicio emailServicio;

	@Override
	public UsuarioDTO registrar(UsuarioDTO userDto) {

		try {
			// Comprueba si ya existe un usuario con el email que quiere registrar
			Usuario usuarioDaoByEmail = repositorio.findFirstByEmailUsuario(userDto.getEmailUsuario());

			if (usuarioDaoByEmail != null) {
				return null; // Si no es null es que ya está registrado
			}

			// Ahora se comprueba si hay un usuario por el DNI que quiere registrar
			boolean yaExisteElDNI = repositorio.existsByDniUsuario(userDto.getDniUsuario());

			if (yaExisteElDNI) {
				// Si es que ya hay un usuario con ese dni se setea a null para controlar el
				// error en controlador
				userDto.setDniUsuario(null);
				return userDto;
			}

			// Si llega a esta línea es que no existe el usuario con el email y el dni a
			// registrar
			// Si continua la ejecución es que el email no se encuentra ya registrado
			userDto.setClaveUsuario(passwordEncoder.encode(userDto.getClaveUsuario()));
			Usuario usuarioDao = toDao.usuarioToDao(userDto);
			usuarioDao.setRol("ROLE_USER");
			if (userDto.isCuentaConfirmada()) {
				usuarioDao.setCuentaConfirmada(true);
				repositorio.save(usuarioDao);
			} else {
				usuarioDao.setCuentaConfirmada(false);
				// Generar token de confirmación
				String token = passwordEncoder.encode(RandomStringUtils.random(30));
				usuarioDao.setToken(token);

				// Guardar el usuario en la base de datos
				repositorio.save(usuarioDao);

				// Enviar el correo de confirmación
				String nombreUsuario = usuarioDao.getNombreUsuario() + " " + usuarioDao.getApellidosUsuario();
				emailServicio.enviarEmailConfirmacion(userDto.getEmailUsuario(), nombreUsuario, token);
			}

			return userDto;
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error UsuarioServicioImpl - registrar() ]" + iae.getMessage());
		} catch (Exception e) {
			System.out.println("[Error UsuarioServicioImpl - registrar() ]" + e.getMessage());
		}
		return null;
	}

	/**
	 * Metodo que ejecuta la creacion de un usuario administrador con su rol de
	 * administrador
	 */
	private void inicializarUsuarioAdmin() {
		// Comprueba si ya existe un usuario admin
		if (!repositorio.existsByNombreUsuario("admin")) {
			// Si no existe, crea un nuevo usuario con rol de administrador
			Usuario admin = new Usuario();
			admin.setNombreUsuario("admin");
			admin.setClaveUsuario(passwordEncoder.encode("admin"));
			admin.setDniUsuario("-");
			admin.setEmailUsuario("admin@admin.com");
			admin.setRol("ROLE_ADMIN");
			admin.setCuentaConfirmada(true);
			repositorio.save(admin);
		}
	}

	/**
	 * Metodo que automatiza la creacion de un usuario administrador que se ejecuta
	 * la primera vez que se despliega la aplicacion
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		inicializarUsuarioAdmin();
	}

	@Override
	public boolean iniciarResetPassConEmail(String emailUsuario) {
		try {
			Usuario usuarioExistente = repositorio.findFirstByEmailUsuario(emailUsuario);

			if (usuarioExistente != null) {
				// Generar el token y establece la fecha de expiración
				String token = passwordEncoder.encode(RandomStringUtils.random(30));
				Calendar fechaExpiracion = Calendar.getInstance();
				fechaExpiracion.add(Calendar.MINUTE, 10);
				// Actualizar el usuario con el nuevo token y la fecha de expiración
				usuarioExistente.setToken(token);
				usuarioExistente.setExpiracionToken(fechaExpiracion);

				// Actualizar el usuario en la base de datos
				repositorio.save(usuarioExistente);

				// Enviar el correo de recuperación
				String nombreUsuario = usuarioExistente.getNombreUsuario() + " "
						+ usuarioExistente.getApellidosUsuario();
				emailServicio.enviarEmailRecuperacion(emailUsuario, nombreUsuario, token);

				return true;

			} else {
				System.out.println("[Error UsuarioServicioImpl - iniciarRecuperacionConEmail()] El usuario con email "
						+ emailUsuario + " no existe");
				return false;
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error UsuarioServicioImpl - registrar() ]" + iae.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("[Error UsuarioServicioImpl - iniciarRecuperacionConEmail()]" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean modificarContraseñaConToken(UsuarioDTO usuario) {

		Usuario usuarioExistente = repositorio.findByToken(usuario.getToken());

		if (usuarioExistente != null) {
			String nuevaContraseña = passwordEncoder.encode(usuario.getPassword());
			usuarioExistente.setClaveUsuario(nuevaContraseña);
			usuarioExistente.setToken(null); // Se setea a null para invalidar el token ya consumido al cambiar de
												// password
			repositorio.save(usuarioExistente);

			return true;
		}

		return false;
	}

	@Override
	public boolean confirmarCuenta(String token) {
		try {
			Usuario usuarioExistente = repositorio.findByToken(token);

			if (usuarioExistente != null && !usuarioExistente.isCuentaConfirmada()) {
				// Entra en esta condición si el usuario existe y su cuenta no se ha confirmado
				usuarioExistente.setCuentaConfirmada(true);
				usuarioExistente.setToken(null);
				repositorio.save(usuarioExistente);

				return true;
			} else {
				System.out.println("La cuenta no existe o ya está confirmada");
				return false;
			}
		} catch (IllegalArgumentException iae) {
			System.out.println(
					"[Error UsuarioServicioImpl - confirmarCuenta()] Error al confirmar la cuenta " + iae.getMessage());
			return false;
		} catch (PersistenceException e) {
			System.out.println(
					"[Error UsuarioServicioImpl - confirmarCuenta()] Error de persistencia al confirmar la cuenta"
							+ e.getMessage());
			return false;
		}
	}

	@Override
	public boolean estaLaCuentaConfirmada(String email) {
		try {
			Usuario usuarioExistente = repositorio.findFirstByEmailUsuario(email);
			if (usuarioExistente != null && usuarioExistente.isCuentaConfirmada()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(
					"[Error UsuarioServicioImpl - estaLaCuentaConfirmada()] Error al comprobar si la cuenta ya ha sido confirmada"
							+ e.getMessage());
		}
		return false;
	}

	@Override
	public UsuarioDTO obtenerUsuarioPorToken(String token) {
		Usuario usuarioExistente = repositorio.findByToken(token);

		if (usuarioExistente != null) {
			UsuarioDTO usuario = toDto.usuarioToDto(usuarioExistente);
			return usuario;
		} else {
			System.out.println("No existe el usuario con el token " + token);
			return null;
		}

	}

	@Override
	public Usuario buscarPorEmail(String email) {
		return repositorio.findFirstByEmailUsuario(email);
	}

	@Override
	public void eliminar(long id) {
		try {
			Usuario usuario = repositorio.findById(id).orElse(null);
			if (usuario != null) {
				repositorio.delete(usuario);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println(
					"[Error UsuarioServicioImpl - eliminar()] Al eliminar el usuario por su id " + iae.getMessage());
		}
	}

	@Override
	public void actualizarUsuario(UsuarioDTO usuarioModificado) {

		try {
			Usuario usuarioActual = repositorio.findById(usuarioModificado.getId()).orElse(null);

			usuarioActual.setNombreUsuario(usuarioModificado.getNombreUsuario());
			usuarioActual.setApellidosUsuario(usuarioModificado.getApellidosUsuario());
			usuarioActual.setTlfUsuario(usuarioModificado.getTlfUsuario());
			usuarioActual.setRol(usuarioModificado.getRol());
			usuarioActual.setFoto(usuarioModificado.getFoto());
			repositorio.save(usuarioActual);
		} catch (PersistenceException pe) {
			System.out.println(
					"[Error UsuarioServicioImpl - actualizarUsuario()] Al modificar el usuario " + pe.getMessage());

		}

	}

	public UsuarioDTO buscarPorId(long id) {
		try {
			Usuario usuario = repositorio.findById(id).orElse(null);
			if (usuario != null) {
				return toDto.usuarioToDto(usuario);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println(
					"[Error UsuarioServicioImpl - buscarPorId()] Al buscar el usuario por su id " + iae.getMessage());
		}
		return null;
	}

	@Override
	public boolean buscarPorDni(String dni) {
		return repositorio.existsByDniUsuario(dni);
	}

	@Override
	public List<UsuarioDTO> obtenerTodos() {
		return toDto.listaUsuarioToDto(repositorio.findAll());
	}
}
