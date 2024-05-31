package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import FarmaSupply.daos.Moto;
import FarmaSupply.dtos.MotoDTO;

@Service
public class MotoToDtoImpl implements IMotoToDto {

	@Override
	public MotoDTO motoToDto(Moto m) {
		// TODO Auto-generated method stub
		try {
			MotoDTO dto = new MotoDTO();

			dto.setIdMoto(m.getIdMoto());
			dto.setMatriculaMoto(m.getMatriculaMoto());
			dto.setIdMoto_Ped(m.getIdMoto_Ped().getIdPedido());
			dto.setMarcaMoto(m.getMarcaMoto());
			dto.setEstadoMoto(m.getEstadoMoto());
			return dto;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR MotoToDtoImpl - motoToDto()] - Error al convertir motoDAO a motoDTO (return null): " + e);
			return null;
		}
	}

	@Override
	public List<MotoDTO> listaMotoToDto(List<Moto> listaMoto) {
		// TODO Auto-generated method stub
		try {

			List<MotoDTO> listaDto = new ArrayList<>();
			for (Moto m : listaMoto) {
				listaDto.add(this.motoToDto(m));
			}
			return listaDto;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR MotoToDtoImpl - listaMotoToDto()] - Error al convertir lista de motoDAO a lista de motoDTO (return null): "
							+ e);
		}
		return null;
	}
}
