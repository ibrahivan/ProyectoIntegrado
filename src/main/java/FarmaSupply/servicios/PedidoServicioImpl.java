package FarmaSupply.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.Pedido;
import FarmaSupply.daos.Tienda;
import FarmaSupply.dtos.CatalogoProductoDTO;
import FarmaSupply.dtos.PedidoDTO;
import FarmaSupply.dtos.TiendaDTO;
import FarmaSupply.repositorios.PedidoRepositorio;
import FarmaSupply.repositorios.TiendaRepositorio;

@Service
public class PedidoServicioImpl implements IPedidoServicio {

	@Override
	public PedidoDTO realizarPedido(PedidoDTO pedidoDTO, TiendaDTO tiendaActual) {
		// TODO Auto-generated method stub
		return null;
	}

}



