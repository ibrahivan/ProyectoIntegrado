package FarmaSupply.dtos;


public class CubetaDTO {

	private long idCubeta;
	private String numeroCubeta;
	private long idCubeta_Ped;
	
	//Constructores
	public CubetaDTO() {
		super();
	}

	public CubetaDTO(String numeroCubeta, long idCubeta_Ped) {
		super();
		this.numeroCubeta = numeroCubeta;
		this.idCubeta_Ped = idCubeta_Ped;
	}


	//getters y setters
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

	public long getIdCubeta_Ped() {
		return idCubeta_Ped;
	}

	public void setIdCubeta_Ped(long idCubeta_Ped) {
		this.idCubeta_Ped = idCubeta_Ped;
	}






	
	
}
