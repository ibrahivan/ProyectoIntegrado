package FarmaSupply.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase DTO (Data Transfer Object) para pasar información entre capas para la
 * gestión del catalogo producto
 */

public class CatalogoProductoDTO {

	private long idCatalogoProducto;
	private String descripcion;
	private String nombreProducto;
	private double precioUnitario;
	private List<DetallePedidoDTO> misDetallesPedidos = new ArrayList<>();

	// Constructores

	public CatalogoProductoDTO() {
		super();
	}

	



	public CatalogoProductoDTO(String descripcion, String nombreProducto, double precioUnitario,
			List<DetallePedidoDTO> misDetallesPedidos) {
		super();
		this.descripcion = descripcion;
		this.nombreProducto = nombreProducto;
		this.precioUnitario = precioUnitario;
		this.misDetallesPedidos = misDetallesPedidos;
	}





	// getters y setters
	public long getIdCatalogoProducto() {
		return idCatalogoProducto;
	}

	public void setIdCatalogoProducto(long idCatalogoProducto) {
		this.idCatalogoProducto = idCatalogoProducto;
	}



	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double d) {
		this.precioUnitario = d;
	}





	public List<DetallePedidoDTO> getMisDetallesPedidos() {
		return misDetallesPedidos;
	}





	public void setMisDetallesPedidos(List<DetallePedidoDTO> misDetallesPedidos) {
		this.misDetallesPedidos = misDetallesPedidos;
	}


}
