package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;

import FarmaSupply.daos.Pedido;
import FarmaSupply.dtos.PedidoDTO;


public class PedidoToDtoImpl implements IPedidoToDto {

	@Override
	public PedidoDTO pedidoToDto(Pedido p) {
		
		try {
			PedidoDTO dto = new PedidoDTO();
			
			
			
			return dto;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR PedidoToDtoImpl - pedidoToDto()] - Error al convertir pedidoDAO a pedidoDTO (return null): "
							+ e);
			return null;
		}
	}

	@Override
	public List<PedidoDTO> listaPedidoToDto(List<Pedido> listaPedido) {
		try {
			
			List<PedidoDTO> listaDto = new ArrayList<>();
			for (Pedido p : listaPedido) {
				listaDto.add(this.pedidoToDto((p)));
			}
			return listaDto;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR PedidoToDtoImpl - listauPedidoToDto()] - Error al convertir lista de pedidoDAO a lista de pedidoDTO (return null): "
							+ e);
		}
		return null;
	}

}
