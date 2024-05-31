package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.Pedido;

import FarmaSupply.dtos.PedidoDTO;

/**
 * Servicio que implementa los metodos de la interface {@link IPedidoToDto} y en
 * esta clase es donde se entra al detalle de la logica de dichos métodos para
 * el paso de la entidad pedido (DAO) a pedidoDTO
 */
@Service
public class PedidoToDtoImpl implements IPedidoToDto {

	@Autowired
	private IDetallePedidoToDto detallePedidoToDto;




	@Override
	public PedidoDTO pedidoToDto(Pedido p) {

		try {
			PedidoDTO dto = new PedidoDTO();

			dto.setIdPedido(p.getIdPedido());
			dto.setPrecioPedido(p.getPrecioPedido());
			dto.setEstadoPedido(p.getEstadoPedido());
			dto.setIdPedido_Tie(p.getIdPedido_Tie().getIdTienda());
			dto.setIdPedido_Moto(p.getIdPed_Moto().getIdMoto());
			if (p.getList_Ped_Det().size() > 0) {
				dto.setMisDetallesPedidos(detallePedidoToDto.listaDetallePedidoToDto(p.getList_Ped_Det()));
			}

		

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
