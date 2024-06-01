package FarmaSupply.dtos;

import java.util.ArrayList;
import java.util.List;

import FarmaSupply.daos.EstadoPedido;



/**
 * Clase DTO (Data Transfer Object) para pasar información entre capas para la
 * gestión de pedidos
 */
public class PedidoDTO {

	private long idPedido;
	private double precioPedido;
	private long idPedido_Tie;
	private List<DetallePedidoDTO> misDetallesPedidos = new ArrayList<>();
	private Long idPedido_Moto ;
	private EstadoPedido estadoPedido;
	private String identificadorPedido;
	// Constructores

	public PedidoDTO() {
		super();
	}




	public PedidoDTO(double precioPedido, long idPedido_Tie, List<DetallePedidoDTO> misDetallesPedidos,
			Long idPedido_Moto, EstadoPedido estadoPedido, String identificadorPedido) {
		super();
		this.precioPedido = precioPedido;
		this.idPedido_Tie = idPedido_Tie;
		this.misDetallesPedidos = misDetallesPedidos;
		this.idPedido_Moto = idPedido_Moto;
		this.estadoPedido = estadoPedido;
		this.identificadorPedido = identificadorPedido;
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

	public long getIdPedido_Tie() {
		return idPedido_Tie;
	}

	public void setIdPedido_Tie(long idPedido_Tie) {
		this.idPedido_Tie = idPedido_Tie;
	}

	public List<DetallePedidoDTO> getMisDetallesPedidos() {
		return misDetallesPedidos;
	}


	public void setMisDetallesPedidos(List<DetallePedidoDTO> misDetallesPedidos) {
		this.misDetallesPedidos = misDetallesPedidos;
	}


	public Long getIdPedido_Moto() {
		return idPedido_Moto;
	}



	public void setIdPedido_Moto(Long idPedido_Moto) {
		this.idPedido_Moto = idPedido_Moto;
	}



	public EstadoPedido getEstadoPedido() {
		return estadoPedido;
	}


	public void setEstadoPedido(EstadoPedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}




	public String getIdentificadorPedido() {
		return identificadorPedido;
	}




	public void setIdentificadorPedido(String identificadorPedido) {
		this.identificadorPedido = identificadorPedido;
	}




}
