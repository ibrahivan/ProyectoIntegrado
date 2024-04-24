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
import jakarta.persistence.OneToMany;
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

	@Column(name = "estado_pedido", nullable = false)
	private int estado_pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_tienda")
	private Tienda idPedido_Tie;
	
	@OneToMany(mappedBy = "idMoto_Ped")
	private List<Tienda> list_Ped_Moto = new ArrayList<>();
	
	@OneToMany(mappedBy = "idCubeta_Ped")
	private List<Tienda> list_Ped_Cub = new ArrayList<>();
	
	

	// Constructores


	public Pedido() {
		super();
	}

	
	
	public Pedido(List<CatalogoProducto> list_Ped_Cat, int precioPedido, int estado_pedido, Tienda idPedido_Tie,
			List<Tienda> list_Ped_Moto, List<Tienda> list_Ped_Cub) {
		super();
		this.list_Ped_Cat = list_Ped_Cat;
		this.precioPedido = precioPedido;
		this.estado_pedido = estado_pedido;
		this.idPedido_Tie = idPedido_Tie;
		this.list_Ped_Moto = list_Ped_Moto;
		this.list_Ped_Cub = list_Ped_Cub;
	}

	//getters y setter


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

	public List<Tienda> getList_Ped_Moto() {
		return list_Ped_Moto;
	}

	public void setList_Ped_Moto(List<Tienda> list_Ped_Moto) {
		this.list_Ped_Moto = list_Ped_Moto;
	}

	public List<Tienda> getList_Ped_Cub() {
		return list_Ped_Cub;
	}

	public void setList_Ped_Cub(List<Tienda> list_Ped_Cub) {
		this.list_Ped_Cub = list_Ped_Cub;
	}

	public int getEstado_pedido() {
		return estado_pedido;
	}

	public void setEstado_pedido(int estado_pedido) {
		this.estado_pedido = estado_pedido;
	}

	
}
