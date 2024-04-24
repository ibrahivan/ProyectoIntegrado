package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import FarmaSupply.daos.Cubeta;
import FarmaSupply.dtos.CubetaDTO;

@Service
public class CubetaToDtoImpl implements ICubetaToDto {

	@Override
	public CubetaDTO cubetaToDto(Cubeta c) {
		// TODO Auto-generated method stub
		try {
			CubetaDTO dto = new CubetaDTO();

			dto.setIdCubeta(c.getIdCubeta());
			dto.setNumeroCubeta(c.getNumeroCubeta());
			dto.setIdCubeta_Ped(c.getIdCubeta_Ped());

			return dto;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR CubetaToDtoImpl - cubetaToDto()] - Error al convertir cubetaDAO a cubetaDTO (return null): "
							+ e);
			return null;
		}
	}

	@Override
	public List<CubetaDTO> listaCubetaToDto(List<Cubeta> listaCubeta) {
		// TODO Auto-generated method stub
		try {

			List<CubetaDTO> listaDto = new ArrayList<>();
			for (Cubeta c : listaCubeta) {
				listaDto.add(this.cubetaToDto(c));
			}
			return listaDto;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR MotoToDtoImpl - listaCubetaToDto()] - Error al convertir lista de cubetaDAO a lista de cubetaDTO (return null): "
							+ e);
		}
		return null;
	}

}
