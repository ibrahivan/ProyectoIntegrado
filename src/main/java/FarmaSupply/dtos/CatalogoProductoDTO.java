package FarmaSupply.dtos;

import java.util.ArrayList;
import java.util.List;




/**
 * Clase DTO (Data Transfer Object) para pasar información entre capas para la
 * gestión del catalogo producto
 */

public class CatalogoProductoDTO {


	private long idCatalogoProducto;
	private int cantidad;
	private String descripcion;
	private String nombreProducto;
	private int precioUnitario;
	private List<PedidoDTO> misPedidos = new ArrayList<>();
	
	//Constructores
	
	public CatalogoProductoDTO() {
		super();
	}

	public CatalogoProductoDTO(int cantidad, String descripcion, String nombreProducto, int precioUnitario,
			List<PedidoDTO> misPedidos) {
		super();
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.nombreProducto = nombreProducto;
		this.precioUnitario = precioUnitario;
		this.misPedidos = misPedidos;
	}

	//getters y setters
	public long getIdCatalogoProducto() {
		return idCatalogoProducto;
	}

	public void setIdCatalogoProducto(long idCatalogoProducto) {
		this.idCatalogoProducto = idCatalogoProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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

	public int getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public List<PedidoDTO> getmisPedidos() {
		return misPedidos;
	}

	public void setmisPedidos(List<PedidoDTO> misPedidos) {
		this.misPedidos = misPedidos;
	}

	

	
}
