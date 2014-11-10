package com.spirit.inventario.session;






import java.util.Collection;
import java.util.Map;

import com.spirit.inventario.session.generated._BodegaSessionService;

/**
 * The <code>BodegaSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:44 $
 */
public interface BodegaSessionService extends _BodegaSessionService{

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	
	Collection findBodegaByEmpresaId(Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
    Collection findBodegaByQuery(Map aMap,Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
    Collection getBodegaList(int startIndex,int endIndex,Map aMap,Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
    int getBodegaListSize(Map aMap,Long idEmpresa) throws com.spirit.exception.GenericBusinessException;

}
