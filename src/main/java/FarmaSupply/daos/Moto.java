package FarmaSupply.daos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

	@Column(name = "marca_moto", nullable = false)
	private String marcaMoto;

	@Column(name = "matricula_moto", nullable = false)
	private String matriculaMoto;

	@OneToMany(mappedBy = "idPed_Moto")
	private List<Pedido> list_Moto_ped = new ArrayList<>();
	
	private EstadoMoto estadoMoto;

	// Constructores

	public Moto() {
		super();
	}


	public Moto(String marcaMoto, String matriculaMoto, List<Pedido> list_Moto_ped, EstadoMoto estadoMoto) {
		super();
		this.marcaMoto = marcaMoto;
		this.matriculaMoto = matriculaMoto;
		this.list_Moto_ped = list_Moto_ped;
		this.estadoMoto = estadoMoto;
	}



	// getters y setters



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


	public List<Pedido> getList_Moto_ped() {
		return list_Moto_ped;
	}


	public void setList_Moto_ped(List<Pedido> list_Moto_ped) {
		this.list_Moto_ped = list_Moto_ped;
	}


	public String getMarcaMoto() {
		return marcaMoto;
	}

	public void setMarcaMoto(String marcaMoto) {
		this.marcaMoto = marcaMoto;
	}



	public EstadoMoto getEstadoMoto() {
		return estadoMoto;
	}



	public void setEstadoMoto(EstadoMoto estadoMoto) {
		this.estadoMoto = estadoMoto;
	}

}
