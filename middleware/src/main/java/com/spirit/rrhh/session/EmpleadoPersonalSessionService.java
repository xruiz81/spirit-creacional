package com.spirit.rrhh.session;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.rrhh.entity.EmpleadoFamiliaresIf;
import com.spirit.rrhh.entity.EmpleadoFormacionIf;
import com.spirit.rrhh.entity.EmpleadoIdiomasIf;
import com.spirit.rrhh.entity.EmpleadoPersonalIf;
import com.spirit.rrhh.session.generated._EmpleadoPersonalSessionService;

public interface EmpleadoPersonalSessionService extends
		_EmpleadoPersonalSessionService {

	public void procesarEmpleadoPersonal(EmpleadoIf modelEmpleado,
			EmpleadoPersonalIf modelEmpleadoPersonal,
			List<EmpleadoFamiliaresIf> modelEmpleadoFamiliaresList,
			List<EmpleadoFormacionIf> modelEmpleadoFormacionList,
			List<EmpleadoIdiomasIf> modelEmpleadoIdiomasList)
			throws GenericBusinessException;

	public void actualizarEmpleadoPersonal(EmpleadoIf modelEmpleado,
			EmpleadoPersonalIf modelEmpleadoPersonal,
			List<EmpleadoFamiliaresIf> modelEmpleadoFamiliaresList,
			List<EmpleadoFormacionIf> modelEmpleadoFormacionList,
			List<EmpleadoIdiomasIf> modelEmpleadoIdiomasList,
			List<EmpleadoFamiliaresIf> modelEmpleadoFamiliaresRemovidosList,
			List<EmpleadoFormacionIf> modelEmpleadoFormacionRemovidosList,
			List<EmpleadoIdiomasIf> modelEmpleadoIdiomasRemovidosList)
			throws GenericBusinessException;

	public void eliminarEmpleadoPersonal(Long empleadoId)
			throws GenericBusinessException;

	public Collection findEmpleadoPersonalByFechaInicioAndFechaFin(Timestamp fechaInicio,Timestamp fechaFin)
			throws GenericBusinessException;
	
	public Collection findEmpleadoAndEmpleadoPersonalActivos() throws GenericBusinessException;
}
