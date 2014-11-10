package com.spirit.rrhh.session;



import java.util.Collection;
import java.util.Map;

import com.spirit.rrhh.entity.EmpleadoFormacionEJB;
import com.spirit.rrhh.entity.EmpleadoFormacionIf;
import com.spirit.rrhh.session.generated._EmpleadoFormacionSessionService;

/**
 * The <code>EmpleadoFormacionSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:43 $
 */
public interface EmpleadoFormacionSessionService extends _EmpleadoFormacionSessionService{

	public EmpleadoFormacionEJB registrarEmpleadoFormacion(EmpleadoFormacionIf modelEmpleadoFormacion) throws com.spirit.exception.GenericBusinessException;
	
}
