package FarmaSupply.dtos;

import java.util.List;

import FarmaSupply.daos.CatalogoProducto;
import FarmaSupply.daos.Tienda;
import FarmaSupply.daos.Usuario;

/**
 * Clase DTO (Data Transfer Object) para pasar información entre capas para la
 * gestión de pedidos
 */
public class PedidoDTO {

	private long idPedido;
	private int precioPedido;
	private Tienda idPedido_Tie;
	private List<CatalogoProducto> list_Ped_Cat;
	
	//Constructores
	
	public PedidoDTO() {
		super();
	}
	

	public PedidoDTO(int precioPedido, Tienda idPedido_Tie, List<CatalogoProducto> list_Ped_Cat) {
		super();
		this.precioPedido = precioPedido;
		this.idPedido_Tie = idPedido_Tie;
		this.list_Ped_Cat = list_Ped_Cat;
	}


	//getters y setters
	public long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}
	
	public int getPrecioPedido() {
		return precioPedido;
	}

	public void setPrecioPedido(int precioPedido) {
		this.precioPedido = precioPedido;
	}


	public Tienda getIdPedido_Tie() {
		return idPedido_Tie;
	}
	public void setIdPedido_Tie(Tienda idPedido_Tie) {
		this.idPedido_Tie = idPedido_Tie;
	}


	public List<CatalogoProducto> getList_Ped_Cat() {
		return list_Ped_Cat;
	}


	public void setList_Ped_Cat(List<CatalogoProducto> list_Ped_Cat) {
		this.list_Ped_Cat = list_Ped_Cat;
	}
	
}





