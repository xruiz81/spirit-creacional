package com.spirit.rrhh.session;



import java.util.Collection;
import java.util.Map;

import com.spirit.rrhh.entity.EmpleadoFamiliaresEJB;
import com.spirit.rrhh.entity.EmpleadoFamiliaresIf;
import com.spirit.rrhh.session.generated._EmpleadoFamiliaresSessionService;


public interface EmpleadoFamiliaresSessionService extends _EmpleadoFamiliaresSessionService{

	public EmpleadoFamiliaresEJB registrarEmpleadoFamiliares(EmpleadoFamiliaresIf modelEmpleadoFamiliares) throws com.spirit.exception.GenericBusinessException;
	
}
