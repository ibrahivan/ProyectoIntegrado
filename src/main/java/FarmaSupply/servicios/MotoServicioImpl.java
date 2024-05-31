package FarmaSupply.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.EstadoMoto;
import FarmaSupply.daos.Moto;
import FarmaSupply.daos.Pedido;
import FarmaSupply.dtos.MotoDTO;
import FarmaSupply.repositorios.MotoRepositorio;
import FarmaSupply.repositorios.PedidoRepositorio;
@Service
public class MotoServicioImpl implements IMotoServicio {

	@Autowired
	private MotoRepositorio repositorio;
	@Autowired
	private PedidoRepositorio pedidoRepositorio;

	@Autowired
	private IMotoToDao toDao;

	@Autowired
	private IMotoToDto toDto;

	@Override
	public MotoDTO registrarMoto(MotoDTO motoDTO) {

		// TODO Auto-generated method stub
		// Comprueba si ya existe una moto con la matricula que quiere registrar
		try {
			Moto motoDaoMatricula = repositorio.findByMatriculaMoto(motoDTO.getMatriculaMoto());

			if (motoDaoMatricula != null) {
				motoDTO.setMatriculaMoto(null); //seteamos la matricula para el mensaje de error
				return null; // Si no es null es que ya está registrada
			}
			motoDTO.setEstadoMoto(EstadoMoto.LIBRE);
			
			Moto motoDao = toDao.motoToDao(motoDTO);
			// Guardar la moto en la base de datos
			
			repositorio.save(motoDao);
			motoDTO.setIdMoto(motoDao.getIdMoto());
			return motoDTO;

		} catch (IllegalArgumentException iae) {
			System.out.println("[Error MotoServicioImpl - registrar() ]" + iae.getMessage());
		} catch (Exception e) {
			System.out.println("[Error MotoServicioImpl - registrar() ]" + e.getMessage());
		}
		return null;
	}

	@Override
	public MotoDTO buscarPorId(long id) {
		// TODO Auto-generated method stub
		try {
			Moto moto = repositorio.findById(id).orElse(null);
			if (moto != null) {
				return toDto.motoToDto(moto);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println(
					"[Error MotoServicioImpl - buscarPorId()] Al buscar la moto por su id " + iae.getMessage());
		}
		return null;
	}
	
	@Override
	public MotoDTO buscarMotoPorPedido(long id) {
		// TODO Auto-generated method stub
		try {
			Pedido pedido = pedidoRepositorio.findById(id).orElse(null);
			if (pedido != null) {
				return toDto.motoToDto(pedido.getList_Ped_Moto());
			}
		} catch (IllegalArgumentException iae) {
			System.out.println(
					"[Error MotoServicioImpl - buscarPorId()] Al buscar la moto por su id " + iae.getMessage());
		}
		return null;
	}

	@Override
	public void eliminarMoto(long id) {
		// TODO Auto-generated method stub
		try {
			Moto moto = repositorio.findById(id).orElse(null);
			if (moto != null) {
				repositorio.delete(moto);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println(
					"[Error MotoServicioImpl - eliminarMoto()] Al eliminar una moto por su id " + iae.getMessage());
		}

	}

	@Override
	public List<MotoDTO> obtenerTodas() {
		// TODO Auto-generated method stub
		return toDto.listaMotoToDto(repositorio.findAll());
	}
	
	@Override
	public List<Moto> obtenerMotosLibres() {
        return repositorio.findAll()
                             .stream()
                             .filter(moto -> moto.getEstadoMoto() == EstadoMoto.LIBRE)
                             .collect(Collectors.toList());
    }

}
