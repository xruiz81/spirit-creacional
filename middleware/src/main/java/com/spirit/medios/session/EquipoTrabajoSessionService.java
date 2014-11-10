package com.spirit.medios.session;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.EquipoEmpleadoIf;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.session.generated._EquipoTrabajoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EquipoTrabajoSessionService extends _EquipoTrabajoSessionService{

	public void procesarEquipoTrabajo(EquipoTrabajoIf model,List<EquipoEmpleadoIf> modelDetalleList) throws GenericBusinessException;
    public void actualizarEquipoTrabajo(EquipoTrabajoIf model,List<EquipoEmpleadoIf> modelDetalleList)throws GenericBusinessException;
    public void eliminarEquipoTrabajo(Long equipoTrabajoId) throws GenericBusinessException;
    Collection getEquipoTrabajoList(int startIndex, int endIndex,Map aMap) throws com.spirit.exception.GenericBusinessException;
    int getEquipoTrabajoListSize(Map aMap)  throws com.spirit.exception.GenericBusinessException;
    public String getMaximoCodigoEquipoTrabajo(Long empresaId, String tipoOrden) throws GenericBusinessException;
    public java.util.Collection findEquipoTrabajoByEmpresaIdAndTipoOrdenId(java.lang.Long empresaId, java.lang.Long tipoOrdenId) throws GenericBusinessException;
    public java.util.Collection findEquipoTrabajoByQueryAndByEmpleadoId(Map aMap, java.lang.Long empleadoId) throws GenericBusinessException; 
    
}
