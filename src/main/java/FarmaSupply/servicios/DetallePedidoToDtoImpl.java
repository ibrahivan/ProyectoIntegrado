package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import FarmaSupply.daos.DetallePedido;
import FarmaSupply.dtos.DetallePedidoDTO;

@Service
public class DetallePedidoToDtoImpl implements IDetallePedidoToDto{

	@Override
	public DetallePedidoDTO detallePedidoToDto(DetallePedido dP) {
		try {
		DetallePedidoDTO dto = new DetallePedidoDTO();

		dto.setIdDetallePedido(dP.getIdDetallePedido());
		dto.setCantidadDetalle(dP.getCantidadDetalle());
		dto.setPrecioDetalle(dP.getPrecioDetalle());	
		return dto;
	} catch (Exception e) {
		System.out.println(
				"\n[ERROR PedidoToDtoImpl - DetallePedidoToDto()] - Error al convertir detallePedidoDAO a detallePedidoDTO (return null): "
						+ e);
		return null;
}
}

	@Override
	public List<DetallePedidoDTO> listaDetallePedidoToDto(List<DetallePedido> listaDetallePedido) {
		try {

			List<DetallePedidoDTO> listaDto = new ArrayList<>();
			for (DetallePedido dP : listaDetallePedido) {
				listaDto.add(this.detallePedidoToDto((dP)));
			}
			return listaDto;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR detallePedidoToDtoImpl - listaDetallePedidoToDto()] - Error al convertir lista de detallePedidoDAO a lista de detallePedidoDTO (return null): "
							+ e);
		}
		return null;
	}
}


