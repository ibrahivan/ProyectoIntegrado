package FarmaSupply.dtos;

import FarmaSupply.daos.Tienda;
import FarmaSupply.daos.Usuario;

/**
 * Clase DTO (Data Transfer Object) para pasar información entre capas para la
 * gestión de pedidos
 */
public class PedidoDTO {

	private long idPedido;
	private String productoPedido;
	private Usuario idUsuario_Ped;
	private Tienda idPedido_Tie;
	private int cantidad;
	
	//Constructores
	
	public PedidoDTO() {
		super();
	}
	
	public PedidoDTO(String productoPedido, Usuario idUsuario_Ped, Tienda idPedido_Tie, int cantidad) {
		super();
		this.productoPedido = productoPedido;
		this.idUsuario_Ped = idUsuario_Ped;
		this.idPedido_Tie = idPedido_Tie;
		this.cantidad = cantidad;
	}

	//getters y setters
	public long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}
	
	public String getProductoPedido() {
		return productoPedido;
	}

	public void setProductoPedido(String productoPedido) {
		this.productoPedido = productoPedido;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
}


