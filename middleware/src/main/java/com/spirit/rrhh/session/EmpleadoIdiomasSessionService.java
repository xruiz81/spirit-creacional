package com.spirit.rrhh.session;

import com.spirit.rrhh.entity.EmpleadoIdiomasEJB;
import com.spirit.rrhh.entity.EmpleadoIdiomasIf;
import com.spirit.rrhh.session.generated._EmpleadoIdiomasSessionService;

public interface EmpleadoIdiomasSessionService extends _EmpleadoIdiomasSessionService{

	public EmpleadoIdiomasEJB registrarEmpleadoIdiomas(EmpleadoIdiomasIf modelEmpleadoIdiomas) throws com.spirit.exception.GenericBusinessException;
	
}
