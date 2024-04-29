package FarmaSupply.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.Cubeta;
import FarmaSupply.dtos.CubetaDTO;
import FarmaSupply.repositorios.CubetaRepositorio;
@Service
public class CubetaServicioImpl implements ICubetaServicio {
	@Autowired
	private CubetaRepositorio repositorio;

	@Autowired
	private ICubetaToDao toDao;

	@Autowired
	private ICubetaToDto toDto;

	@Override
	public CubetaDTO registrarCubeta(CubetaDTO cubetaDTO) {

		// TODO Auto-generated method stub
		// Comprueba si ya existe una cubeta con el numero que quiere registrar
		try {
			Cubeta cubetaDaoNumero = repositorio.findByNumeroCubeta(cubetaDTO.getNumeroCubeta());

			if (cubetaDaoNumero != null) {
				return null; // Si no es null es que ya está registrada
			}
			cubetaDTO.setEstaDisponible(true);
			Cubeta cubetaDao = toDao.cubetaToDao(cubetaDTO);
			// Guardar la cubeta en la base de datos
			repositorio.save(cubetaDao);
			cubetaDTO.setIdCubeta(cubetaDao.getIdCubeta());
			return cubetaDTO;

		} catch (IllegalArgumentException iae) {
			System.out.println("[Error CubetaServicioImpl - registrar() ]" + iae.getMessage());
		} catch (Exception e) {
			System.out.println("[Error CubetaServicioImpl - registrar() ]" + e.getMessage());
		}
		return null;
	}

	@Override
	public CubetaDTO buscarPorId(long id) {
		// TODO Auto-generated method stub
		try {
			Cubeta cubeta = repositorio.findById(id).orElse(null);
			if (cubeta != null) {
				return toDto.cubetaToDto(cubeta);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println(
					"[Error CubetaServicioImpl - buscarPorId()] Al buscar la cubeta por su id " + iae.getMessage());
		}
		return null;
	}

	@Override
	public void eliminarCubeta(long id) {
		// TODO Auto-generated method stub
		try {
			Cubeta cubeta = repositorio.findById(id).orElse(null);
			if (cubeta != null) {
				repositorio.delete(cubeta);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error CubetaServicioImpl - eliminarCubeta()] Al eliminar una cubeta por su id "
					+ iae.getMessage());
		}

	}

	@Override
	public List<CubetaDTO> obtenerTodas() {
		// TODO Auto-generated method stub
		return toDto.listaCubetaToDto(repositorio.findAll());
	}
}
