package FarmaSupply.dtos;

import FarmaSupply.daos.Ruta;


public class CamionDTO {

	private long idCamion;
	private String matriculaCamion;
	private Ruta idRuta_Cam;
	
	//Constructores
	public CamionDTO() {
		super();
	}


	public CamionDTO(String matriculaCamion, Ruta idRuta_Cam) {
		super();
		this.matriculaCamion = matriculaCamion;
		this.idRuta_Cam = idRuta_Cam;
	}

	//Getters y setters
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
