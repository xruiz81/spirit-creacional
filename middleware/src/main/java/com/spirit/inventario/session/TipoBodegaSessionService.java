package com.spirit.inventario.session;




import java.util.Collection;
import java.util.Map;

import com.spirit.inventario.session.generated._TipoBodegaSessionService;

/**
 * The <code>TipoBodegaSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:44 $
 */
public interface TipoBodegaSessionService extends _TipoBodegaSessionService{

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	java.util.Collection getTipoBodegaList(int startIndex,int endindex,Map aMap) throws com.spirit.exception.GenericBusinessException;
	int getTipoBodegaListSize(Map aMap) throws com.spirit.exception.GenericBusinessException;

}
