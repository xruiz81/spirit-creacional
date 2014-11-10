package com.spirit.inventario.session;




import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.session.generated._TipoProductoSessionService;

/**
 * The <code>TipoProductoSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:44 $
 */
public interface TipoProductoSessionService extends _TipoProductoSessionService{
	
	
	//ADD 21 JULIO
	public Collection findTipoProductoByMedioProduccion(String medioProduccion) throws GenericBusinessException;

}
