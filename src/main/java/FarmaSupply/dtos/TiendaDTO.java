package FarmaSupply.dtos;

import java.util.ArrayList;
import java.util.List;

import FarmaSupply.daos.Usuario;


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
	private List<PedidoDTO> list_Tie_Ped = new ArrayList<>();
	private Usuario idUsuario_Tie;
	
	//Constructores

	
	public TiendaDTO() {
		super();
	}

	public TiendaDTO(String nombreTienda, String direccionTienda, String codigopostalTienda,
			List<PedidoDTO> list_Tie_Ped, Usuario idUsuario_Tie) {
		super();
		this.nombreTienda = nombreTienda;
		this.direccionTienda = direccionTienda;
		this.codigopostalTienda = codigopostalTienda;
		this.list_Tie_Ped = list_Tie_Ped;
		this.idUsuario_Tie = idUsuario_Tie;
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
	

	public Usuario getIdUsuario_Tie() {
		return idUsuario_Tie;
	}

	public void setIdUsuario_Tie(Usuario idUsuario_Tie) {
		this.idUsuario_Tie = idUsuario_Tie;
	}

	public void setCodigopostalTienda(String codigopostalTienda) {
		this.codigopostalTienda = codigopostalTienda;
	}

	public List<PedidoDTO> getList_Tie_Ped() {
		return list_Tie_Ped;
	}

	public void setList_Tie_Ped(List<PedidoDTO> list_Tie_Ped) {
		this.list_Tie_Ped = list_Tie_Ped;
	}
	
	
}
