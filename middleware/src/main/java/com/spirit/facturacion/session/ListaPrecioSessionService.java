package com.spirit.facturacion.session;

import java.util.List;

import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.ListaPrecioIf;
import com.spirit.facturacion.entity.PrecioIf;
import com.spirit.facturacion.session.generated._ListaPrecioSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ListaPrecioSessionService extends _ListaPrecioSessionService{

	public void procesarListaPrecio(ListaPrecioIf model,List<PrecioIf> modelPrecio) throws GenericBusinessException;
	public void actualizarListaPrecio(ListaPrecioIf model,List<PrecioIf> modelPrecio,List<PrecioIf> modelPreciosRemovidos)throws GenericBusinessException;
	public void eliminarListaPrecio(Long listaPrecioId) throws GenericBusinessException ;
}
