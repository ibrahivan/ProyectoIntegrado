package FarmaSupply.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaSupply.daos.CatalogoProducto;

import FarmaSupply.dtos.CatalogoProductoDTO;

/**
 * Servicio que implementa los metodos de la interface {@link ICatalogoProductoToDto} 
 * y en esta clase es donde se entra al detalle de la logica de dichos métodos
 * para el paso de la entidad CatalogoProducto (DAO) a CatalogoProductoDTO
 */
@Service
public class CatalogoProductoToDtoImpl implements ICatalogoProductoToDto {
	@Autowired
	private IPedidoToDto pedidoToDto;
	@Override
	public CatalogoProductoDTO catalogoProductoToDto(CatalogoProducto cP) {
		try {
			CatalogoProductoDTO dto = new CatalogoProductoDTO();
			dto.setIdCatalogoProducto(cP.getIdCatalogoProducto());
			dto.setPrecioUnitario(cP.getPrecioUnitario());
			dto.setNombreProducto(cP.getNombreProducto());
			dto.setCantidad(cP.getCantidad());
			dto.setDescripcion(cP.getDescripcion());
			if(cP.getList_Cat_Ped().size()>0)
			{
				dto.setmisPedidos(pedidoToDto.listaPedidoToDto(cP.getList_Cat_Ped()));
			}
			
			return dto;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR CatalogoProductoToDTOImpl - catalogoProductoToDto()] - Error al convertir catalogoProductoDAO a catalogoProductoDTO (return null): "
							+ e);
			return null;
		}
	}

	@Override
	public List<CatalogoProductoDTO> listaCatalogoProductoToDto(List<CatalogoProducto> listaCatalogoProducto) {
		try {
			
			List<CatalogoProductoDTO> listaDto = new ArrayList<>();
			for (CatalogoProducto cP : listaCatalogoProducto) {
				listaDto.add(this.catalogoProductoToDto(cP));
			}
			return listaDto;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR CatalogoProductoToDTOImpl - listauCatalogoProductoToDto()] - Error al convertir lista de catalogoProductoDAO a lista de catalogoProductoDTO (return null): "
							+ e);
		}
		return null;
	}

}
