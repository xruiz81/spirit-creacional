package com.spirit.inventario.session;



import java.util.Collection;
import java.util.Map;

import com.spirit.inventario.session.generated._ProductoRetencionSessionService;

/**
 * The <code>ProductoRetencionSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:44 $
 */
public interface ProductoRetencionSessionService extends _ProductoRetencionSessionService{

	java.util.Collection findProductoRetencionByEmpresaId(java.lang.Long empresaId) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findProductoRetencionByQueryAndFecha(Map aMap, java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException;

}
