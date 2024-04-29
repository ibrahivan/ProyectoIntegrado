package FarmaSupply.daos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Clase DAO (Data Access Object) que representa la tabla cubetas de la BBDD,
 * ejerce como modelo virtual de la tabla en la aplicación.
 */
@Entity
@Table(name = "cubetas", schema = "fs_logica")
public class Cubeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cubeta", nullable = false)
	private long idCubeta;

	@Column(name = "numero_cubeta", nullable = false)
	private String numeroCubeta;

	@Column(name = "esta_disponible_cubeta", nullable = false)
	private boolean estaDisponible;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido idCubeta_Ped;

	// Constructores
	public Cubeta() {
		super();
	}

	public Cubeta(String numeroCubeta, boolean estaDisponible, Pedido idCubeta_Ped) {
		super();
		this.numeroCubeta = numeroCubeta;
		this.estaDisponible = estaDisponible;
		this.idCubeta_Ped = idCubeta_Ped;
	}

	// Getters y setters
	public long getIdCubeta() {
		return idCubeta;
	}

	public void setIdCubeta(long idCubeta) {
		this.idCubeta = idCubeta;
	}

	public String getNumeroCubeta() {
		return numeroCubeta;
	}

	public void setNumeroCubeta(String numeroCubeta) {
		this.numeroCubeta = numeroCubeta;
	}

	public Pedido getIdCubeta_Ped() {
		return idCubeta_Ped;
	}

	public void setIdCubeta_Ped(Pedido idCubeta_Ped) {
		this.idCubeta_Ped = idCubeta_Ped;
	}

	public boolean isEstaDisponible() {
		return estaDisponible;
	}

	public void setEstaDisponible(boolean estaDisponible) {
		this.estaDisponible = estaDisponible;
	}

}
