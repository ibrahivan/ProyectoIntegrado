package FarmaSupply.daos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Camion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_camion", nullable = false)
	private long idCamion;
	
	@Column(name = "matricula_camion", nullable = false)
	private String matriculaCamion;
	
	@ManyToOne
	@JoinColumn(name = "id_ruta")
	private Ruta idRuta_Cam;

	
	//Constructores
	public Camion(String matriculaCamion, Ruta idRuta_Cam) {
		super();
		this.matriculaCamion = matriculaCamion;
		this.idRuta_Cam = idRuta_Cam;
	}

	public Camion() {
		super();
	}

	//getters y setters
	public long getIdCamion() {
		return idCamion;
	}

	public void setIdCamion(long idCamion) {
		this.idCamion = idCamion;
	}

	public String getMatriculaCamion() {
		return matriculaCamion;
	}

	public void setMatriculaCamion(String matriculaCamion) {
		this.matriculaCamion = matriculaCamion;
	}

	public Ruta getIdRuta_Cam() {
		return idRuta_Cam;
	}

	public void setIdRuta_Cam(Ruta idRuta_Cam) {
		this.idRuta_Cam = idRuta_Cam;
	}
	
	
}
