package FarmaSupply.daos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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


	@OneToMany(mappedBy = "idDet_Ped")
	private List<DetallePedido> list_Ped_Det = new ArrayList<>();

	@Column(name = "precio_pedido", nullable = false)
	private double precioPedido;
	
	@Column(name = "estado_pedido")
	private int estado_pedido;

	@ManyToOne
	@JoinColumn(name = "id_tienda")
	private Tienda idPedido_Tie;

	@OneToMany(mappedBy = "idMoto_Ped")
	private List<Moto> list_Ped_Moto = new ArrayList<>();

	@OneToMany(mappedBy = "idCubeta_Ped")
	private List<Cubeta> list_Ped_Cub = new ArrayList<>();

	// Constructores

	public Pedido() {
		super();
	}




	public Pedido(List<DetallePedido> list_Ped_Det, double precioPedido, int estado_pedido, Tienda idPedido_Tie,
			List<Moto> list_Ped_Moto, List<Cubeta> list_Ped_Cub) {
		super();
		this.list_Ped_Det = list_Ped_Det;
		this.precioPedido = precioPedido;
		this.estado_pedido = estado_pedido;
		this.idPedido_Tie = idPedido_Tie;
		this.list_Ped_Moto = list_Ped_Moto;
		this.list_Ped_Cub = list_Ped_Cub;
	}




	// getters y setter

	public Pedido(Tienda idPedido_Tie) {
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


	public double getPrecioPedido() {
		return precioPedido;
	}

	public void setPrecioPedido(double precioPedido) {
		this.precioPedido = precioPedido;
	}

	public List<Moto> getList_Ped_Moto() {
		return list_Ped_Moto;
	}

	public void setList_Ped_Moto(List<Moto> list_Ped_Moto) {
		this.list_Ped_Moto = list_Ped_Moto;
	}

	public List<Cubeta> getList_Ped_Cub() {
		return list_Ped_Cub;
	}

	public void setList_Ped_Cub(List<Cubeta> list_Ped_Cub) {
		this.list_Ped_Cub = list_Ped_Cub;
	}

	public int getEstado_pedido() {
		return estado_pedido;
	}

	public void setEstado_pedido(int estado_pedido) {
		this.estado_pedido = estado_pedido;
	}



	public List<DetallePedido> getList_Ped_Det() {
		return list_Ped_Det;
	}




	public void setList_Ped_Det(List<DetallePedido> list_Ped_Det) {
		this.list_Ped_Det = list_Ped_Det;
	}

	

}
