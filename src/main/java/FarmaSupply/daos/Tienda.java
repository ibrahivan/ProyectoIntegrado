package FarmaSupply.daos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/**
 * Clase DAO (Data Access Object) que representa la tabla tiendas de la BBDD,
 * ejerce como modelo virtual de la tabla en la aplicación.
 */
@Entity
@Table(name = "tiendas", schema = "fs_logica")
public class Tienda {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tienda", nullable = false)
	private long idTienda;

	@Column(name = "nombre_tienda", nullable = false, length = 70)
	private String nombreTienda;

	@Column(name = "direccion_tienda ", nullable = false, length = 70)
	private String direccionTienda;

	@Column(name = "codigopostal_tienda", nullable = false, length = 70)
	private String codigopostalTienda;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario idUsuario_Tie;

	@OneToMany(mappedBy = "idPedido_Tie")
	private List<Pedido> list_Tie_Ped = new ArrayList<>();

	// Constructores

	public Tienda() {
		super();
	}
	
	

	public Tienda(String nombreTienda, String direccionTienda, String codigopostalTienda, Usuario idUsuario_Tie,
			List<Pedido> list_Tie_Ped) {
		super();
		this.nombreTienda = nombreTienda;
		this.direccionTienda = direccionTienda;
		this.codigopostalTienda = codigopostalTienda;
		this.idUsuario_Tie = idUsuario_Tie;
		this.list_Tie_Ped = list_Tie_Ped;
	}



	// getters y setters

	public long getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(long idTienda) {
		this.idTienda = idTienda;
	}

	public String getNombreTienda() {
		return nombreTienda;
	}

	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}

	public String getDireccionTienda() {
		return direccionTienda;
	}

	public void setDireccionTienda(String direccionTienda) {
		this.direccionTienda = direccionTienda;
	}

	public String getCodigopostalTienda() {
		return codigopostalTienda;
	}

	public void setCodigopostalTienda(String codigopostalTienda) {
		this.codigopostalTienda = codigopostalTienda;
	}

	public Usuario getIdUsuario_Tie() {
		return idUsuario_Tie;
	}

	public void setIdUsuario_Tie(Usuario idUsuario_Tie) {
		this.idUsuario_Tie = idUsuario_Tie;
	}



	public List<Pedido> getList_Tie_Ped() {
		return list_Tie_Ped;
	}



	public void setList_Tie_Ped(List<Pedido> list_Tie_Ped) {
		this.list_Tie_Ped = list_Tie_Ped;
	}



}
