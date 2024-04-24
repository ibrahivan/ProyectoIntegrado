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
 * Clase DAO (Data Access Object) que representa la tabla motos de la BBDD,
 * ejerce como modelo virtual de la tabla en la aplicación.
 */
@Entity
@Table(name = "motos", schema = "fs_logica")
public class Moto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_moto", nullable = false)
	private long idMoto;
	
	@Column(name = "matricula_moto", nullable = false)
	private String matriculaMoto;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido idMoto_Ped;

	
	//Constructores
	

	public Moto() {
		super();
	}


	public Moto(String matriculaMoto, Pedido idPedido_Moto) {
		super();
		this.matriculaMoto = matriculaMoto;
		this.idMoto_Ped = idPedido_Moto;
	}


	public long getIdMoto() {
		return idMoto;
	}


	public void setIdMoto(long idMoto) {
		this.idMoto = idMoto;
	}


	public String getMatriculaMoto() {
		return matriculaMoto;
	}


	public void setMatriculaMoto(String matriculaMoto) {
		this.matriculaMoto = matriculaMoto;
	}


	public Pedido getIdMoto_Ped() {
		return idMoto_Ped;
	}


	public void setIdMoto_Ped(Pedido idMoto_Ped) {
		this.idMoto_Ped = idMoto_Ped;
	}

	//getters y setters

	
	
}
