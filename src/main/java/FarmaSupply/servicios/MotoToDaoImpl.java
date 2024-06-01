package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.Moto;
import FarmaSupply.dtos.MotoDTO;

@Service
public class MotoToDaoImpl implements IMotoToDao {
	
	@Autowired
	private IPedidoToDao pedidoToDao;
	@Override
	public Moto motoToDao(MotoDTO motoDTO) {
		// TODO Auto-generated method stub

		
		try {

			Moto motoDao = new Moto();
			motoDao.setIdMoto(motoDTO.getIdMoto());
			motoDao.setMatriculaMoto(motoDTO.getMatriculaMoto());
			motoDao.setMarcaMoto(motoDTO.getMarcaMoto());
			motoDao.setEstadoMoto(motoDTO.getEstadoMoto());
			

			if (motoDTO.getMisPedidos().size() > 0) {
				motoDao.setList_Moto_ped(pedidoToDao.listPedidoToDao(motoDTO.getMisPedidos()));
			}
			return motoDao;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR MotoToDaoImpl - toMotoDao()] - Al convertir motoDTO a motoDAO (return null): " + e);
			return null;
		}

	}

	@Override
	public List<Moto> listaMotoToDao(List<MotoDTO> listaMotoDTO) {
		// TODO Auto-generated method stub
		try {
			List<Moto> listaMotoDao = new ArrayList<>();
			for (MotoDTO motoDTO : listaMotoDTO) {
				listaMotoDao.add(motoToDao(motoDTO));
			}

			return listaMotoDao;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR MotoToDaoImpl - toMotoDao()] - Al convertir lista de motoDTO a lista de motoDAO (return null): "
							+ e);
		}
		return null;
	}
}
