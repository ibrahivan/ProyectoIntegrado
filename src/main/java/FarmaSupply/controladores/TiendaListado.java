package FarmaSupply.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import FarmaSupply.dtos.UsuarioDTO;
import FarmaSupply.servicios.ITiendaServicio;
import FarmaSupply.servicios.IUsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/privada")
public class TiendaListado {
	


	/**
	 * Gestiona la solicitud HTTP GET para la url /privada/listadoTiendas y muestra la
	 * página de administración tiendas del usuario.
	 *
	 * @param model          Modelo que se utiliza para enviar el listado de
	 *                       tiendas a la vista.
	 * @return La vista de listado de tiendas (listadoTiendas.html) 
	 */
	
}
