package FarmaSupply.dtos;



public class MotoDTO {

	private long idMoto;
	private String matriculaMoto;
	private long idMoto_Ped;
	
	//Constructores
	public MotoDTO() {
		super();
	}



	public MotoDTO(String matriculaMoto, long idMoto_Ped) {
		super();
		this.matriculaMoto = matriculaMoto;
		this.idMoto_Ped = idMoto_Ped;
	}


	//Getters y setters
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



	public long getIdMoto_Ped() {
		return idMoto_Ped;
	}



	public void setIdMoto_Ped(long idMoto_Ped) {
		this.idMoto_Ped = idMoto_Ped;
	}






}
