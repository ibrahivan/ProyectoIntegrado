package FarmaSupply.dtos;

import FarmaSupply.daos.EstadoMoto;
import FarmaSupply.daos.Pedido;

/**
 * Clase DTO (Data Transfer Object) para pasar información entre capas para la
 * gestión de pedidos
 */
public class MotoDTO {

	private long idMoto;
	private String matriculaMoto;
	private String marcaMoto;
	private Pedido idMoto_Ped;
	private EstadoMoto estadoMoto;

	// Constructores
	public MotoDTO() {
		super();
	}







	public MotoDTO(String matriculaMoto, String marcaMoto, Pedido idMoto_Ped, EstadoMoto estadoMoto) {
		super();
		this.matriculaMoto = matriculaMoto;
		this.marcaMoto = marcaMoto;
		this.idMoto_Ped = idMoto_Ped;
		this.estadoMoto = estadoMoto;
	}







	// Getters y setters
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
