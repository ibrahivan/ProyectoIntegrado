package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import FarmaSupply.daos.Cubeta;
import FarmaSupply.dtos.CubetaDTO;

@Service
public class CubetaToDaoImpl implements ICubetaToDao {

	@Override
	public Cubeta cubetaToDao(CubetaDTO cubetaDTO) {
		// TODO Auto-generated method stub

		try {
			Cubeta cubetaDao = new Cubeta();
			cubetaDao.setIdCubeta(cubetaDTO.getIdCubeta());
			cubetaDao.setNumeroCubeta(cubetaDTO.getNumeroCubeta());
			cubetaDao.setIdCubeta_Ped(cubetaDTO.getIdCubeta_Ped());

			return cubetaDao;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR CubetaToDaoImpl - toCubetaDao()] - Al convertir CubetaDTO a CubetaDAO (return null): "
							+ e);
			return null;
		}
	}

	@Override
	public List<Cubeta> listaCubetaToDao(List<CubetaDTO> listaCubetaDTO) {
		// TODO Auto-generated method stub
		try {
			List<Cubeta> listaCubetaDao = new ArrayList<>();
			for (CubetaDTO cubetaDTO : listaCubetaDTO) {
				listaCubetaDao.add(cubetaToDao(cubetaDTO));
			}

			return listaCubetaDao;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR CubetaToDaoImpl - toCubetaDao()] - Al convertir lista de cubetaDTO a lista de cubetaDAO (return null): "
							+ e);
		}
		return null;
	}

}
