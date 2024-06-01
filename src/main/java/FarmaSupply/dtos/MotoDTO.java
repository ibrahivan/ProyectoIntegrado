package FarmaSupply.dtos;

import java.util.ArrayList;
import java.util.List;

import FarmaSupply.daos.EstadoMoto;

/**
 * Clase DTO (Data Transfer Object) para pasar información entre capas para la
 * gestión de pedidos
 */
public class MotoDTO {

	private long idMoto;
	private String matriculaMoto;
	private String marcaMoto;
	private List<PedidoDTO> misPedidos = new ArrayList<>();
	private EstadoMoto estadoMoto;

	// Constructores
	public MotoDTO() {
		super();
	}



	public MotoDTO(String matriculaMoto, String marcaMoto, List<PedidoDTO> misPedidos, EstadoMoto estadoMoto) {
		super();
		this.matriculaMoto = matriculaMoto;
		this.marcaMoto = marcaMoto;
		this.misPedidos = misPedidos;
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



	public List<PedidoDTO> getMisPedidos() {
		return misPedidos;
	}



	public void setMisPedidos(List<PedidoDTO> misPedidos) {
		this.misPedidos = misPedidos;
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
