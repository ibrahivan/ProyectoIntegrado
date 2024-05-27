package FarmaSupply.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import FarmaSupply.daos.Pedido;
import FarmaSupply.dtos.DetallePedidoDTO;
import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.dtos.TiendaDTO;
import FarmaSupply.repositorios.PedidoRepositorio;
import FarmaSupply.servicios.IPedidoToDto;
import FarmaSupply.servicios.ITiendaServicio;

@Controller
@RequestMapping("/privada")
public class PedidoListado {


	

}