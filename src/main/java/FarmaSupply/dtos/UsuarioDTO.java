package FarmaSupply.dtos;

import java.util.Calendar;

/**
 * Clase DTO (Data Transfer Object) para pasar información entre capas 
 * para la gestión de usuarios
 */
public class UsuarioDTO {

	//ATRIBUTOS
	private long id;
	private String nombreUsuario;
	private String apellidosUsuario;
	private String dniUsuario;
	private String tlfUsuario;
	private String emailUsuario;
	private String claveUsuario;
	private String token;
	private String password;
	private String password2;
	private Calendar expiracionToken;
	private boolean cuentaConfirmada;
	private String rol;

	//CONSTRUCTORES
	public UsuarioDTO() {
	}



	public UsuarioDTO(String nombreUsuario, String apellidosUsuario, String dniUsuario, String tlfUsuario,
			String emailUsuario, String claveUsuario) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.apellidosUsuario = apellidosUsuario;
		this.dniUsuario = dniUsuario;
		this.tlfUsuario = tlfUsuario;
		this.emailUsuario = emailUsuario;
		this.claveUsuario = claveUsuario;
	}



	//GETTERS Y SETTERS
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public boolean isCuentaConfirmada() {
		return cuentaConfirmada;
	}



	public String getRol() {
		return rol;
	}



	public void setRol(String rol) {
		this.rol = rol;
	}



	public void setCuentaConfirmada(boolean cuentaConfirmada) {
		this.cuentaConfirmada = cuentaConfirmada;
	}



	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellidosUsuario() {
		return apellidosUsuario;
	}

	public void setApellidosUsuario(String apellidosUsuario) {
		this.apellidosUsuario = apellidosUsuario;
	}

	public String getTlfUsuario() {
		return tlfUsuario;
	}

	public void setTlfUsuario(String tlfUsuario) {
		this.tlfUsuario = tlfUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getClaveUsuario() {
		return claveUsuario;
	}

	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	public Calendar getExpiracionToken() {
		return expiracionToken;
	}

	public void setExpiracionToken(Calendar expiracionToken) {
		this.expiracionToken = expiracionToken;
	}

	//tostring

	@Override
	public String toString() {
		return "UsuarioDTO [nombreUsuario=" + nombreUsuario + ", apellidosUsuario=" + apellidosUsuario + ", dniUsuario="
				+ dniUsuario + ", tlfUsuario=" + tlfUsuario + ", emailUsuario=" + emailUsuario + ", claveUsuario="
				+ claveUsuario + ", token=" + token + ", password=" + password + ", password2=" + password2
				+ ", expiracionToken=" + expiracionToken + "]";
	}
    
	
}