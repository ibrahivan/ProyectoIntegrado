package FarmaSupply.daos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Clase DAO (Data Access Object) que representa la tabla usuarios de la BBDD,
 * ejerce como modelo virtual de la tabla en la aplicación.
 */
@Entity
@Table(name = "usuarios", schema = "fs_gestion")
public class Usuario {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", nullable = false)
	private long idUsuario;

	@Column(name = "nombre_usuario", nullable = false, length = 70)
	private String nombreUsuario;

	@Column(name = "apellidos_usuario", nullable = true, length = 100)
	private String apellidosUsuario;

	@Column(name = "dni_usuario", nullable = false, unique = true)
	private String dniUsuario;

	@Column(name = "tlf_usuario", nullable = true )
	private String tlfUsuario;

	@Column(name = "email_usuario", nullable = false, unique = true, length = 100)
	private String emailUsuario;

	@Column(name = "clave_usuario", nullable = false, length = 100)
	private String claveUsuario;

	@Column(name = "token_recuperacion", nullable = true, length = 100)
	private String token;

	@Column(name = "expiracion_token", nullable = true, length = 100)
	private Calendar expiracionToken;

	@Column(name = "rol_usuario", nullable = true, length = 20)
	private String rol;

	@Column(name = "cuenta_confirmada", nullable = false, columnDefinition = "boolean default false")
	private boolean cuentaConfirmada;
	
	
	@Column(name = "foto_usuario")
	private byte[] foto;

;

	@OneToMany(mappedBy = "idUsuario_Tie")
	private List<Tienda> list_Usu_Tie = new ArrayList<>();

	// CONSTRUCTORES

	public Usuario() {
		super();
	}
	
	

	public Usuario(String nombreUsuario, String apellidosUsuario, String dniUsuario, String tlfUsuario,
			String emailUsuario, String claveUsuario, String token, Calendar expiracionToken, String rol,
			boolean cuentaConfirmada, byte[] foto, List<Tienda> list_Usu_Tie) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.apellidosUsuario = apellidosUsuario;
		this.dniUsuario = dniUsuario;
		this.tlfUsuario = tlfUsuario;
		this.emailUsuario = emailUsuario;
		this.claveUsuario = claveUsuario;
		this.token = token;
		this.expiracionToken = expiracionToken;
		this.rol = rol;
		this.cuentaConfirmada = cuentaConfirmada;
		this.foto = foto;
	
		this.list_Usu_Tie = list_Usu_Tie;
	}



	// GETTERS Y SETTERS
	public long getIdUsuario() {
		return idUsuario;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
		
	}

	public byte[] getFoto() {
		return foto;
	}



	public void setFoto(byte[] foto) {
		this.foto = foto;
	}



	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
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

	public String getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
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

	public Calendar getExpiracionToken() {
		return expiracionToken;
	}

	public void setExpiracionToken(Calendar expiracionToken) {
		this.expiracionToken = expiracionToken;
	}

	public boolean isCuentaConfirmada() {
		return cuentaConfirmada;
	}

	public void setCuentaConfirmada(boolean cuentaConfirmada) {
		this.cuentaConfirmada = cuentaConfirmada;
	}




	public List<Tienda> getList_Usu_Tie() {
		return list_Usu_Tie;
	}

	public void setList_Usu_Tie(List<Tienda> list_Usu_Tie) {
		this.list_Usu_Tie = list_Usu_Tie;
	}



}
