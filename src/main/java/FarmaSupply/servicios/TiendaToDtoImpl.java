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
 * Servicio que implementa los metodos de la interface {@link ITiendaToDto} y en
 * esta clase es donde se entra al detalle de la logica de dichos métodos para
 * el paso de la entidad tienda (DAO) a tiendaDTO
 */
@Service
public class TiendaToDtoImpl implements ITiendaToDto {

	@Autowired
	private IPedidoToDto pedidoToDto;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Override
	public TiendaDTO tiendaToDto(Tienda t) {
		try {
			
			TiendaDTO dto = new TiendaDTO();

			dto.setId(t.getIdTienda());
			dto.setNombreTienda(t.getNombreTienda());
			dto.setDireccionTienda(t.getDireccionTienda());
			dto.setCodigopostalTienda(t.getCodigopostalTienda());
			dto.setIdTienda_Usu(t.getIdTienda_Usu().getIdUsuario());
			
			if (t.getList_Tie_Ped().size() > 0) {
				dto.setMisPedidos(pedidoToDto.listaPedidoToDto(t.getList_Tie_Ped()));
			}

			return dto;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR TiendaToDtoImpl - tiendaToDto()] - Error al convertir tiendaDAO a tiendaDTO (return null): "
							+ e);
			return null;
		}
	}

	@Override
	public List<TiendaDTO> listaTiendaToDto(List<Tienda> listaTienda) {
		try {

			List<TiendaDTO> listaDto = new ArrayList<>();
			for (Tienda t : listaTienda) {
				listaDto.add(this.tiendaToDto(t));
			}
			return listaDto;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR TiendaToDtoImpl - listaTiendaToDto()] - Error al convertir lista de tiendaDAO a lista de tiendaDTO (return null): "
							+ e);
		}
		return null;
	}

}
