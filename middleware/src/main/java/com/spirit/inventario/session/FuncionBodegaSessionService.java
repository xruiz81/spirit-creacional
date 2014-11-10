package com.spirit.inventario.session;




import java.util.Collection;
import java.util.Map;

import com.spirit.inventario.session.generated._FuncionBodegaSessionService;

/**
 * The <code>FuncionBodegaSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:44 $
 */
public interface FuncionBodegaSessionService extends _FuncionBodegaSessionService{

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	
	Collection findFuncionBodegaByQueryAndByIdEmpresa(Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	Collection getFuncionBodegaList(int startIndex,int endIndex,Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int getFuncionBodegaListSize(Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;

}
