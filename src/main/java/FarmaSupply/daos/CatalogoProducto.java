package FarmaSupply.daos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Clase DAO (Data Access Object) que representa la tabla catalogoProductos de
 * la BBDD, ejerce como modelo virtual de la tabla en la aplicación.
 */
@Entity
@Table(name = "catalogoProductos", schema = "fs_logica")

public class CatalogoProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_catalogo_producto", nullable = false)
	private long idCatalogoProducto;

	@Column(name = "nombre_producto ", nullable = false)
	private String nombreProducto;

	@Column(name = "precio_unitario_producto ", nullable = false)
	private double precioUnitario;
	
	@Column(name = "descripcion_producto ", nullable = false)
	private String descripcion;
	
	
	@OneToMany(mappedBy = "idDet_Cat")
	private List<DetallePedido> list_Cat_Det = new ArrayList<>();

	public CatalogoProducto() {
		super();
	}

	public CatalogoProducto(String nombreProducto, double precioUnitario, String descripcion,
			List<DetallePedido> list_Cat_Det) {
		super();
		this.nombreProducto = nombreProducto;
		this.precioUnitario = precioUnitario;
		this.descripcion = descripcion;
		this.list_Cat_Det = list_Cat_Det;
	}

	public long getIdCatalogoProducto() {
		return idCatalogoProducto;
	}

	public void setIdCatalogoProducto(long idCatalogoProducto) {
		this.idCatalogoProducto = idCatalogoProducto;
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

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<DetallePedido> getList_Cat_Det() {
		return list_Cat_Det;
	}

	public void setList_Cat_Det(List<DetallePedido> list_Cat_Det) {
		this.list_Cat_Det = list_Cat_Det;
	}

	

}
