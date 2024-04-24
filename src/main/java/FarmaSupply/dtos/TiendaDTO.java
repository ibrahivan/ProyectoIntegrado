package FarmaSupply.dtos;

import java.util.ArrayList;
import java.util.List;




/**
 * Clase DTO (Data Transfer Object) para pasar información entre capas para la
 * gestión de tiendas
 */
public class TiendaDTO {


	//ATRIBUTOS
	private long id;
	private String nombreTienda;
	private String direccionTienda;
	private String codigopostalTienda;
	private List<PedidoDTO> misPedidos = new ArrayList<>();
	private long idTienda_Usu;
	
	//Constructores

	
	public TiendaDTO() {
		super();
	}


	public TiendaDTO(String nombreTienda, String direccionTienda, String codigopostalTienda, List<PedidoDTO> misPedidos,
			long idTienda_Usu) {
		super();
		this.nombreTienda = nombreTienda;
		this.direccionTienda = direccionTienda;
		this.codigopostalTienda = codigopostalTienda;
		this.misPedidos = misPedidos;
		this.idTienda_Usu = idTienda_Usu;
	}


	//getter y setters

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreTienda() {
		return nombreTienda;
	}

	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}

	public String getDireccionTienda() {
		return direccionTienda;
	}

	public void setDireccionTienda(String direccionTienda) {
		this.direccionTienda = direccionTienda;
	}

	public String getCodigopostalTienda() {
		return codigopostalTienda;
	}
	


	public long getIdTienda_Usu() {
		return idTienda_Usu;
	}


	public void setIdTienda_Usu(long idTienda_Usu) {
		this.idTienda_Usu = idTienda_Usu;
	}


	public void setCodigopostalTienda(String codigopostalTienda) {
		this.codigopostalTienda = codigopostalTienda;
	}

	public List<PedidoDTO> getMisPedidos() {
		return misPedidos;
	}

	public void setMisPedidos(List<PedidoDTO> misPedidos) {
		this.misPedidos = misPedidos;
	}
	
	
}
