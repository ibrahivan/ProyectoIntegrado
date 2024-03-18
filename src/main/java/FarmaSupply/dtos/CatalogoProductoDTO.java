package FarmaSupply.dtos;

import java.util.ArrayList;
import java.util.List;

import FarmaSupply.daos.Pedido;


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
	private List<Pedido> list_Cat_Ped = new ArrayList<>();
	
	//Constructores
	
	public CatalogoProductoDTO() {
		super();
	}

	public CatalogoProductoDTO(int cantidad, String descripcion, String nombreProducto, int precioUnitario,
			List<Pedido> list_Cat_Ped) {
		super();
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.nombreProducto = nombreProducto;
		this.precioUnitario = precioUnitario;
		this.list_Cat_Ped = list_Cat_Ped;
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

	public List<Pedido> getList_Cat_Ped() {
		return list_Cat_Ped;
	}

	public void setList_Cat_Ped(List<Pedido> list_Cat_Ped) {
		this.list_Cat_Ped = list_Cat_Ped;
	}

	

	
}
