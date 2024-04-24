package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.Usuario;
import FarmaSupply.dtos.UsuarioDTO;

/**
 * Servicio que implementa los métodos de la interfaz {@link IUsuarioToDao} y en
 * esta clase es donde se entra al detalle de la lógica de dichos métodos para
 * el paso de usuarioDTO a DAO.
 */
@Service
public class UsuarioToDaoImpl implements IUsuarioToDao {

	@Autowired
	private ITiendaToDao tiendaToDao;

	// Inyección de dependencia del servicio de usuario para reutilizar el método
	// convertToByteArray
	@Autowired
	private UsuarioServicioImpl usuarioServicio;

	@Override
	public Usuario usuarioToDao(UsuarioDTO usuarioDTO) {
		try {
			Usuario usuarioDao = new Usuario();
			usuarioDao.setIdUsuario(usuarioDTO.getId());
			usuarioDao.setNombreUsuario(usuarioDTO.getNombreUsuario());
			usuarioDao.setApellidosUsuario(usuarioDTO.getApellidosUsuario());
			usuarioDao.setEmailUsuario(usuarioDTO.getEmailUsuario());
			usuarioDao.setClaveUsuario(usuarioDTO.getClaveUsuario());
			usuarioDao.setTlfUsuario(usuarioDTO.getTlfUsuario());
			usuarioDao.setDniUsuario(usuarioDTO.getDniUsuario());
			usuarioDao.setRol(usuarioDTO.getRol());
			usuarioDao.setFoto(usuarioServicio.convertToByteArray(usuarioDTO.getFoto()));
			if (usuarioDTO.getMisTiendas().size() > 0) {
				usuarioDao.setList_Usu_Tie(tiendaToDao.listTiendaToDao(usuarioDTO.getMisTiendas()));
			}
			return usuarioDao;
		} catch (Exception e) {
			System.out.println(
					"[ERROR UsuarioToDaoImpl - toUsuarioDao()] - Al convertir usuarioDTO a usuarioDAO (return null): "
							+ e);
			return null;
		}
	}

	@Override
	public List<Usuario> listUsuarioToDao(List<UsuarioDTO> listaUsuarioDTO) {
		List<Usuario> listaUsuarioDao = new ArrayList<>();
		try {
			for (UsuarioDTO usuarioDTO : listaUsuarioDTO) {
				listaUsuarioDao.add(usuarioToDao(usuarioDTO));
			}
			return listaUsuarioDao;
		} catch (Exception e) {
			System.out.println(
					"[ERROR UsuarioToDaoImpl - toListUsuarioDao()] - Al convertir lista de usuarioDTO a lista de usuarioDAO (return null): "
							+ e);
			return null;
		}
	}
}
