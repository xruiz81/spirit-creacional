package com.spirit.seguridad.session;



import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.seguridad.entity.RolIf;
import com.spirit.seguridad.entity.RolOpcionIf;
import com.spirit.seguridad.session.generated._RolSessionService;

/**
 * The <code>RolSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:42 $
 */
public interface RolSessionService extends _RolSessionService{

	Collection findRolByQuery(int startIndex,int endIndex,Map aMap) throws com.spirit.exception.GenericBusinessException;
	int getRolListSize(Map aMap) throws com.spirit.exception.GenericBusinessException;
	public void procesarRol(RolIf model, List<RolOpcionIf> modelRolOpcionList) throws GenericBusinessException;
	//public void actualizarRol(RolIf model, DefaultMutableTreeNode nodoRaiz, Map existeNodoFuncionesMapMP) throws GenericBusinessException;
	public void actualizarRol(RolIf model, List<RolOpcionIf> modelRolOpcionList) throws GenericBusinessException;
	public void eliminarRol(Long rolId) throws GenericBusinessException;
	
}
