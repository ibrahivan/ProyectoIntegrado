package FarmaSupply.servicios;

import java.util.List;


import FarmaSupply.dtos.UsuarioDTO;

/**
 * Interfaz del servicio para la gestión de usuarios, donde se declaran los
 * métodos correspondientes que serán implementados.
 */
public interface IUsuarioServicio {

	/**
	 * Se registra a un usuario antes comprobando si ya se encuentra en la BBDD
	 * registrado el usuario
	 * 
	 * @param userDTO El usuario a registrar
	 * @return El usuario registrado
	 */
	public UsuarioDTO registrar(UsuarioDTO userDTO);

	/**
	 * Busca a un usuario por su identificador asignado en la bbdd
	 * 
	 * @param id del usuarioDTO a buscar
	 * @return El usuario buscado
	 */
	public UsuarioDTO buscarPorId(long id);

	/**
	 * Busca a un usuario por su dirección de email registrada
	 * 
	 * @param email del usario que se quiere encontrar
	 * @return El usuario buscado
	 */
	public UsuarioDTO buscarPorEmail(String email);

	/**
	 * Busca a un usuario por su dni
	 * 
	 * @param dni del usuario que se quiere encontrar
	 * @return true si el usuario existe, false en caso contrario
	 */
	public boolean buscarPorDni(String dni);

	/**
	 * Busca un usuario por su token de recuperación.
	 * 
	 * @param token que identifica al usuario recibió un correo con la url y dicho
	 *              token
	 * @return el usuario buscado
	 */
	public UsuarioDTO obtenerUsuarioPorToken(String token);

	/**
	 * Metodo que controla que el usuario existe y no esta su cuenta confirmada
	 * 
	 * @param emailUsuario El email del usuario a confirmar
	 * @return true si el proceso se ha realizado correctamente, false en caso
	 *         contrario
	 */
	boolean confirmarCuenta(String emailUsuario);

	/**
	 * Comprueba si el usuario existe y si su cuenta ha sido confirmada
	 * 
	 * @param email El email del usuario
	 * @return true si el usuario existe y su cuenta ha sido confirmada, false en
	 *         caso contrario
	 */
	public boolean estaLaCuentaConfirmada(String email);

	/**
	 * Inicia el proceso de recuperacion (generando token y vencimiento) con el
	 * email del usuario
	 * 
	 * @param emailUsuario El email del usuario a recuperar la contraseña
	 * @return true si el proceso se ha iniciado correctamente, false en caso
	 *         contrario
	 */
	public boolean iniciarResetPassConEmail(String emailUsuario);

	/**
	 * Establece la nueva contraseña del usuario con el token
	 * 
	 * @param usuario El usuario al que se le establecera la nueva contraseña
	 * @return true si el proceso se ha realizado correctamente, false en caso
	 *         contrario
	 */
	public boolean modificarContraseñaConToken(UsuarioDTO usuario);

	/**
	 * Elimina un usuario por su identificador
	 * 
	 * @param id del usuario
	 */
	public void eliminar(long id);

	/**
	 * Modifica un usuario en la bbdd
	 * 
	 * @param usuario el usuario con los datos modificados
	 */
	public void actualizarUsuario(UsuarioDTO usuarioModificado);

	/**
	 * Obtiene la lista de todos los usuarios registrados
	 * 
	 * @return la lista de todos los usuarios DTO
	 */
	public List<UsuarioDTO> obtenerTodos();

	/**
	 * Convierte una imagen en un string
	 * @param data
	 * @return Un string que representa la imagen convertida
	 */
	public String convertToBase64(byte[] data);

	/**
	 * Convierte un string de base64 en un array de bytes, representando la imagen.
	 * @param base64String
	 * @return Un array de bytes que representa la imagen convertida.
	 */
	public byte[] convertToByteArray(String base64String);

}