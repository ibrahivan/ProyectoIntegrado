package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.Usuario;
import FarmaSupply.dtos.UsuarioDTO;

/**
 * Servicio que implementa los metodos de la interface {@link IUsuarioToDto} y
 * en esta clase es donde se entra al detalle de la logica de dichos métodos
 * para el paso de la entidad usuario (DAO) a usuarioDTO
 */
@Service
public class UsuarioToDtoImpl implements IUsuarioToDto {

	@Autowired
	private ITiendaToDto tiendaToDto;

	@Override
	public UsuarioDTO usuarioToDto(Usuario u) {

		try {
			UsuarioServicioImpl impU = new UsuarioServicioImpl();
			UsuarioDTO dto = new UsuarioDTO();
			dto.setNombreUsuario(u.getNombreUsuario());
			dto.setApellidosUsuario(u.getApellidosUsuario());
			dto.setDniUsuario(u.getDniUsuario());
			dto.setTlfUsuario(u.getTlfUsuario());
			dto.setEmailUsuario(u.getEmailUsuario());
			dto.setClaveUsuario(u.getClaveUsuario());
			dto.setToken(u.getToken());
			dto.setExpiracionToken(u.getExpiracionToken());
			dto.setId(u.getIdUsuario());
			dto.setRol(u.getRol());
			dto.setFoto(impU.convertToBase64(u.getFoto()));
			if (u.getList_Usu_Tie().size() > 0) {
				dto.setMisTiendas(tiendaToDto.listaTiendaToDto(u.getList_Usu_Tie()));

			}

			return dto;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR UsuarioToDtoImpl - usuarioToDto()] - Error al convertir usuario DAO a usuarioDTO (return null): "
							+ e);
			return null;
		}
	}

	@Override
	public List<UsuarioDTO> listaUsuarioToDto(List<Usuario> listaUsuario) {
		try {

			List<UsuarioDTO> listaDto = new ArrayList<>();
			for (Usuario u : listaUsuario) {
				listaDto.add(this.usuarioToDto(u));
			}
			return listaDto;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR UsuarioToDtoImpl - listauUsuarioToDto()] - Error al convertir lista de usuario DAO a lista de usuarioDTO (return null): "
							+ e);
		}
		return null;
	}

}
