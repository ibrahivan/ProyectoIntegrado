package FarmaSupply.servicios;

/**
 * Interface donde se declaran los metodos necesarios para la gestión de correos
 * electrónicos.
 */
public interface IEmailServicio {

	/**
	 * Envía un correo electrónico de recuperación de contraseña.
	 * 
	 * @param emailDestino  Dirección de correo electrónico del usuario
	 *                      destinatario.
	 * @param nombreUsuario Nombre del usuario para mostrarlo en el email enviado.
	 * @param token         Token asociado a la recuperación.
	 */
	public void enviarEmailRecuperacion(String emailDestino, String nombreUsuario, String token);

	/**
	 * Envía un correo electrónico de confirmación de nueva cuenta de usuario.
	 * 
	 * @param emailDestino  Dirección de correo electrónico del usuario
	 *                      destinatario.
	 * @param nombreUsuario Nombre del usuario para mostrarlo en el email enviado.
	 * @param token         Token asociado a la confirmación.
	 */
	void enviarEmailConfirmacion(String emailDestino, String nombreUsuario, String token);
}