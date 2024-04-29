package FarmaSupply.dtos;

import java.util.ArrayList;
import java.util.List;

import FarmaSupply.daos.Tienda;

/**
 * Clase DTO (Data Transfer Object) para pasar información entre capas para la
 * gestión de pedidos
 */
public class PedidoDTO {

	private long idPedido;
	private double precioPedido;
	private Tienda idPedido_Tie;
	private int estadoPedido;
	private double cantidad;
	private List<CatalogoProductoDTO> misCatalogoProducto = new ArrayList<>();
	private List<MotoDTO> misMotos = new ArrayList<>();
	private List<CubetaDTO> misCubetas = new ArrayList<>();
	// Constructores

	public PedidoDTO() {
		super();
	}


	public PedidoDTO(double precioPedido, Tienda idPedido_Tie, int estadoPedido, double cantidad,
			List<CatalogoProductoDTO> misCatalogoProducto, List<MotoDTO> misMotos, List<CubetaDTO> misCubetas) {
		super();
		this.precioPedido = precioPedido;
		this.idPedido_Tie = idPedido_Tie;
		this.estadoPedido = estadoPedido;
		this.cantidad = cantidad;
		this.misCatalogoProducto = misCatalogoProducto;
		this.misMotos = misMotos;
		this.misCubetas = misCubetas;
	}


	// getters y setters
	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public double getPrecioPedido() {
		return precioPedido;
	}

	public void setPrecioPedido(double precioPedido) {
		this.precioPedido = precioPedido;
	}

	public Tienda getIdPedido_Tie() {
		return idPedido_Tie;
	}

	public void setIdPedido_Tie(Tienda idPedido_Tie) {
		this.idPedido_Tie = idPedido_Tie;
	}

	public List<CatalogoProductoDTO> getMisCatalogoProducto() {
		return misCatalogoProducto;
	}

	public void setMisCatalogoProducto(List<CatalogoProductoDTO> misCatalogoProducto) {
		this.misCatalogoProducto = misCatalogoProducto;
	}

	public List<MotoDTO> getMisMotos() {
		return misMotos;
	}

	public void setMisMotos(List<MotoDTO> misMotos) {
		this.misMotos = misMotos;
	}

	public List<CubetaDTO> getMisCubetas() {
		return misCubetas;
	}

	public void setMisCubetas(List<CubetaDTO> misCubetas) {
		this.misCubetas = misCubetas;
	}

	public int getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(int estadoPedido) {
		this.estadoPedido = estadoPedido;
	}


	public double getCantidad() {
		return cantidad;
	}


	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	

}
