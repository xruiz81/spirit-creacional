package com.spirit.inventario.session;



import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.generated._GenericoSessionService;

/**
 * The <code>GenericoSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:44 $
 */
public interface GenericoSessionService extends _GenericoSessionService{
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	Collection getGenericoList(int startIndex, int endIndex, Map aMap) throws com.spirit.exception.GenericBusinessException;
	int getGenericoListSize(Map aMap)throws com.spirit.exception.GenericBusinessException;	
	public void procesarGenerico(GenericoIf model, List<ProductoIf> modelProductoList) throws com.spirit.exception.GenericBusinessException;
	public void actualizarGenerico(GenericoIf model, List<ProductoIf> modelProductoList, List<ProductoIf> detalleProductoRemovidoList) throws com.spirit.exception.GenericBusinessException;
	public void eliminarGenerico(Long genericoId) throws com.spirit.exception.GenericBusinessException;
	public void regenerarCodigoBarras() throws GenericBusinessException;
	Collection findGenericoPautaRegular(Long clienteId, String tipoProductoCodigo, Long empresaId, String cobraIvaProveedor, String cobraIvaCliente) throws GenericBusinessException;
	Collection findGenericoByProductoId(Long productoId) throws GenericBusinessException;
}
