package FarmaSupply.dtos;


import java.util.ArrayList;
import java.util.List;

import FarmaSupply.daos.Camion;
import FarmaSupply.daos.Pedido;


public class RutaDTO {

	
	private long idRuta;
	private String direccionInicio;
	private String direccionFinal;
	private List<Pedido> list_Ped_Ruta = new ArrayList<>();;
	private List<Camion> list_Cam_Ruta = new ArrayList<>(); ;
	
	//Constructores
	public RutaDTO() {
		super();
	}

	public RutaDTO(String direccionInicio, String direccionFinal, List<Pedido> list_Ped_Ruta,
			List<Camion> list_Cam_Ruta) {
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
