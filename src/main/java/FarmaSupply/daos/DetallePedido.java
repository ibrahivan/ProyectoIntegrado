package FarmaSupply.daos;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
/**
 * Clase DAO (Data Access Object) que representa la tabla detallePedido de
 * la BBDD, ejerce como modelo virtual de la tabla en la aplicación.
 */
@Entity
@Table(name = "detallePedido", schema = "fs_logica")
public class DetallePedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_pedido", nullable = false)
	private long idDetallePedido;

	@Column(name = "cantidad_detalle")
	private Double cantidadDetalle;
	
	@Column(name = "precio_detalle_pedido", nullable = false)
	private double precioDetalle;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido idDet_Ped;
	
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private CatalogoProducto idDet_Cat;
	
	
	public DetallePedido() {
		super();
	}




	public DetallePedido(Double cantidadDetalle, double precioDetalle, Pedido idDet_Ped, CatalogoProducto idDet_Cat) {
		super();
		this.cantidadDetalle = cantidadDetalle;
		this.precioDetalle = precioDetalle;
		this.idDet_Ped = idDet_Ped;
		this.idDet_Cat = idDet_Cat;
	}




	public long getIdDetallePedido() {
		return idDetallePedido;
	}


	public void setIdDetallePedido(long idDetallePedido) {
		this.idDetallePedido = idDetallePedido;
	}



	public double getPrecioDetalle() {
		return precioDetalle;
	}




	public void setPrecioDetalle(double precioDetalle) {
		this.precioDetalle = precioDetalle;
	}




	public Double getCantidadDetalle() {
		return cantidadDetalle;
	}


	public void setCantidadDetalle(Double cantidadDetalle) {
		this.cantidadDetalle = cantidadDetalle;
	}


	public Pedido getIdDet_Ped() {
		return idDet_Ped;
	}


	public void setIdDet_Ped(Pedido idDet_Ped) {
		this.idDet_Ped = idDet_Ped;
	}


	public CatalogoProducto getIdDet_Cat() {
		return idDet_Cat;
	}


	public void setIdDet_Cat(CatalogoProducto idDet_Cat) {
		this.idDet_Cat = idDet_Cat;
	}
	
	
	
}
