package FarmaSupply.daos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Ruta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ruta", nullable = false)
	private long idRuta;


	@Column(name = "direccion_inicio", nullable = false)
	private String direccionInicio;
	
	@Column(name = "direccion_final", nullable = false)
	private String direccionFinal;
	
	@OneToMany(mappedBy = "id_pedido")
	private List<Pedido> list_Ped_Ruta = new ArrayList<>();
	
	@OneToMany(mappedBy = "id_camion")
	private List<Camion> list_Cam_Ruta = new ArrayList<>();
	
	//Constructores
	
	public Ruta() {
		super();
	}



	public Ruta(String direccionInicio, String direccionFinal, List<Pedido> list_Ped_Ruta, List<Camion> list_Cam_Ruta) {
		super();
		this.direccionInicio = direccionInicio;
		this.direccionFinal = direccionFinal;
		this.list_Ped_Ruta = list_Ped_Ruta;
		this.list_Cam_Ruta = list_Cam_Ruta;
	}


	//getters y setters
	
	public long getIdRuta() {
		return idRuta;
	}



	public void setIdRuta(long idRuta) {
		this.idRuta = idRuta;
	}



	public String getDireccionInicio() {
		return direccionInicio;
	}



	public void setDireccionInicio(String direccionInicio) {
		this.direccionInicio = direccionInicio;
	}



	public String getDireccionFinal() {
		return direccionFinal;
	}



	public void setDireccionFinal(String direccionFinal) {
		this.direccionFinal = direccionFinal;
	}



	public List<Pedido> getList_Ped_Ruta() {
		return list_Ped_Ruta;
	}



	public void setList_Ped_Ruta(List<Pedido> list_Ped_Ruta) {
		this.list_Ped_Ruta = list_Ped_Ruta;
	}



	public List<Camion> getList_Cam_Ruta() {
		return list_Cam_Ruta;
	}



	public void setList_Cam_Ruta(List<Camion> list_Cam_Ruta) {
		this.list_Cam_Ruta = list_Cam_Ruta;
	}
	
	
}
