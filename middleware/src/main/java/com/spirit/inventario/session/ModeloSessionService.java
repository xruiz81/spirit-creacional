package com.spirit.inventario.session;



import java.util.Collection;
import java.util.Map;

import com.spirit.inventario.session.generated._ModeloSessionService;

/**
 * The <code>ModeloSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:44 $
 */
public interface ModeloSessionService extends _ModeloSessionService {

	java.util.Collection findModeloByEmpresaId(java.lang.Long empresaId) throws com.spirit.exception.GenericBusinessException;
	
	Collection findModeloByEmpresaId(int startIndex, int endIndex, Map aMap) throws com.spirit.exception.GenericBusinessException;
	
}
