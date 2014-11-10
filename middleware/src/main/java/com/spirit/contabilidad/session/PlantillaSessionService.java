package com.spirit.contabilidad.session;

import java.util.Collection;

import com.spirit.contabilidad.session.generated._PlantillaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PlantillaSessionService extends _PlantillaSessionService{

	java.util.Collection getPlantillaByPlanCuentaIdList(int startIndex, int endIndex, java.lang.Long idPlanCuenta) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findPlantillaByEventoContableIdAndByPlanCuentaId(java.lang.Long idEventoContable, java.lang.Long idPlanCuenta) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection getPlantillaByEventoContableIdAndByPlanCuentaIdList(int startIndex, int endIndex, java.lang.Long idEventoContable, java.lang.Long idPlanCuenta) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection getPlantillaByEventocontableIdList(int startIndex, int endIndex, java.lang.Long eventocontableId) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findPlantillaByEventoContableIdAndPlanCuentaId(Long eventoContableId, Long planCuentaId) throws com.spirit.exception.GenericBusinessException;
	Collection findNemonicosDePlantillas();
}
