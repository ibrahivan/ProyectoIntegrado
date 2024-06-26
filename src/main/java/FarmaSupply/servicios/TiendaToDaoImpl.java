package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.Tienda;
import FarmaSupply.daos.Usuario;
import FarmaSupply.dtos.TiendaDTO;
import FarmaSupply.repositorios.UsuarioRepositorio;

/**
 * Servicio que implementa los metodos de la interface {@link ITiendaServicio} y
 * en esta clase es donde se entra al detalle de la logica de dichos métodos
 * para la gestión de las tiendas.
 */
@Service
public class TiendaToDaoImpl implements ITiendaToDao {

	@Autowired
	private IPedidoToDao pedidoToDao;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public Tienda tiendaToDao(TiendaDTO tiendaDTO) {

		try {
			Optional<Usuario> usuarioPropietario = usuarioRepositorio.findById(tiendaDTO.getIdTienda_Usu());
			Tienda tiendaDao = new Tienda();
			tiendaDao.setIdTienda(tiendaDTO.getId());
			tiendaDao.setNombreTienda(tiendaDTO.getNombreTienda());
			tiendaDao.setDireccionTienda(tiendaDTO.getDireccionTienda());
			tiendaDao.setCodigopostalTienda(tiendaDTO.getCodigopostalTienda());
			tiendaDao.setIdTienda_Usu(usuarioPropietario.get());
			
			
			if (tiendaDTO.getMisPedidos().size() > 0) {
				tiendaDao.setList_Tie_Ped(pedidoToDao.listPedidoToDao(tiendaDTO.getMisPedidos()));
			}
			return tiendaDao;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR TiendaToDaoImpl - toTiendaDao()] - Al convertir tiendaDTO a tiendaDAO (return null): "
							+ e);
			return null;
		}

	}

	@Override
	public List<Tienda> listTiendaToDao(List<TiendaDTO> listaTiendaDTO) {

		try {
			List<Tienda> listaTiendaDao = new ArrayList<>();
			for (TiendaDTO usuarioDTO : listaTiendaDTO) {
				listaTiendaDao.add(tiendaToDao(usuarioDTO));
			}

			return listaTiendaDao;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR TiendaToDaoImpl - toTiendaDao()] - Al convertir lista de tiendaDTO a lista de tiendaDAO (return null): "
							+ e);
		}
		return null;
	}
}
