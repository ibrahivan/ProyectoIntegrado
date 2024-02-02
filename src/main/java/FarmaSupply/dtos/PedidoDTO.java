package FarmaSupply.dtos;

import FarmaSupply.daos.Tienda;
import FarmaSupply.daos.Usuario;

/**
 * Clase DTO (Data Transfer Object) para pasar información entre capas para la
 * gestión de pedidos
 */
public class PedidoDTO {

	private long idPedido;
	private String descripcionPedido;
	private Usuario idUsuario_Ped;
	private Tienda idPedido_Tie;
	
	//Constructores
	public PedidoDTO(String descripcionPedido, Usuario idUsuario_Ped, Tienda idPedido_Tie) {
		super();
		this.descripcionPedido = descripcionPedido;
		this.idUsuario_Ped = idUsuario_Ped;
		this.idPedido_Tie = idPedido_Tie;
	}
	public PedidoDTO() {
		super();
	}
	
	//getters y setters
	public long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}
	public String getDescripcionPedido() {
		return descripcionPedido;
	}
	public void setDescripcionPedido(String descripcionPedido) {
		this.descripcionPedido = descripcionPedido;
	}
	public Usuario getIdUsuario_Ped() {
		return idUsuario_Ped;
	}
	public void setIdUsuario_Ped(Usuario idUsuario_Ped) {
		this.idUsuario_Ped = idUsuario_Ped;
	}
	public Tienda getIdPedido_Tie() {
		return idPedido_Tie;
	}
	public void setIdPedido_Tie(Tienda idPedido_Tie) {
		this.idPedido_Tie = idPedido_Tie;
	}
	
	
	
	
}


