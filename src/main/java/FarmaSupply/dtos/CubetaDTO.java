package FarmaSupply.dtos;

import FarmaSupply.daos.Pedido;

/**
 * Clase DTO (Data Transfer Object) para pasar información entre capas para la
 * gestión de pedidos
 */
public class CubetaDTO {

	private long idCubeta;
	private String numeroCubeta;
	private Pedido idCubeta_Ped;
	private boolean estaDisponible;
	// Constructores
	public CubetaDTO() {
		super();
	}

	
	public CubetaDTO(String numeroCubeta, Pedido idCubeta_Ped, boolean estaDisponible) {
		super();
		this.numeroCubeta = numeroCubeta;
		this.idCubeta_Ped = idCubeta_Ped;
		this.estaDisponible = estaDisponible;
	}


	// getters y setters
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
