package com.spirit.seguridad.session;



import java.util.Collection;
import java.util.Map;

import com.spirit.seguridad.entity.RolOpcionEJB;
import com.spirit.seguridad.entity.RolOpcionIf;
import com.spirit.seguridad.session.generated._RolOpcionSessionService;

/**
 * The <code>RolOpcionSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:42 $
 */
public interface RolOpcionSessionService extends _RolOpcionSessionService {
	
	java.util.Collection findRolOpcionByMenuCodigoAndByRolId(java.lang.String codigo, java.lang.Long idRol) throws com.spirit.exception.GenericBusinessException;
	public RolOpcionEJB registrarRolOpcion(RolOpcionIf modelRolOpcion) throws com.spirit.exception.GenericBusinessException;
	public Collection findRolOpcionAndMenuList() throws com.spirit.exception.GenericBusinessException;
	public Collection findRolOpcionByMenuAndUsuarioId(Long usuarioId,Long menuId);
	
}
