package FarmaSupply.dtos;

import java.util.List;

/**
 * Clase DTO (Data Transfer Object) para pasar información entre capas para la
 * gestión de detallesPedido
 */
public class DetallePedidoDTO {
	
	private long idDetallePedido;
	private Double cantidadDetalle;
	private double precioDetalle;
	private long idDet_Ped;
	private long idDet_Cat;

    private List<CatalogoProductoDTO> productosSeleccionados;
    private List<Double> cantidades;


	public DetallePedidoDTO() {
		super();
	}
	public DetallePedidoDTO(Double cantidadDetalle, double precioDetalle, long idDet_Ped, long idDet_Cat) {
		super();
		this.cantidadDetalle = cantidadDetalle;
		this.precioDetalle = precioDetalle;
		this.idDet_Ped = idDet_Ped;
		this.idDet_Cat = idDet_Cat;
		
	}
	
    // Getters and setters


    public List<Double> getCantidades() {
        return cantidades;
    }

    public List<CatalogoProductoDTO> getProductosSeleccionados() {
		return productosSeleccionados;
	}
	public void setProductosSeleccionados(List<CatalogoProductoDTO> productosSeleccionados) {
		this.productosSeleccionados = productosSeleccionados;
	}
	public void setCantidades(List<Double> cantidades) {
        this.cantidades = cantidades;
    }
	public long getIdDetallePedido() {
		return idDetallePedido;
	}
	public void setIdDetallePedido(long idDetallePedido) {
		this.idDetallePedido = idDetallePedido;
	}
	public Double getCantidadDetalle() {
		return cantidadDetalle;
	}
	public void setCantidadDetalle(Double cantidadDetalle) {
		this.cantidadDetalle = cantidadDetalle;
	}
	public double getPrecioDetalle() {
		return precioDetalle;
	}
	public void setPrecioDetalle(double precioDetalle) {
		this.precioDetalle = precioDetalle;
	}
	public long getIdDet_Ped() {
		return idDet_Ped;
	}
	public void setIdDet_Ped(long idDet_Ped) {
		this.idDet_Ped = idDet_Ped;
	}
	public long getIdDet_Cat() {
		return idDet_Cat;
	}
	public void setIdDet_Cat(long idDet_Cat) {
		this.idDet_Cat = idDet_Cat;
	}
	
	
}
