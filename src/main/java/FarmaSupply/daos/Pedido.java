package FarmaSupply.daos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
/**
 * Clase DAO (Data Access Object) que representa la tabla pedidos de la BBDD,
 * ejerce como modelo virtual de la tabla en la aplicación.
 */
@Entity
@Table(name = "pedidos", schema = "fs_logica")
public class Pedido {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido", nullable = false)
	private long idPedido;

	@ManyToMany
	@JoinTable(name = "rel_ped_cat", schema="fs_logica",
			   joinColumns = @JoinColumn(name = "id_pedido"), 
			   inverseJoinColumns = @JoinColumn(name = "id_catalogo_producto"))
	private List<CatalogoProducto> list_Ped_Cat = new ArrayList<>();
	
	@Column(name = "precio_pedido", nullable = false)
	private int precioPedido;

	@ManyToOne
	@JoinColumn(name = "id_tienda")
	private Tienda idPedido_Tie;
	
	@ManyToOne
	@JoinColumn(name = "id_ruta")
	private Ruta idRuta_Ped;

	// Constructores


	public Pedido() {
		super();
	}

	public Pedido( Tienda idPedido_Tie) {
		super();
		

		this.idPedido_Tie = idPedido_Tie;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
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

	public int getPrecioPedido() {
		return precioPedido;
	}

	public void setPrecioPedido(int precioPedido) {
		this.precioPedido = precioPedido;
	}

}
