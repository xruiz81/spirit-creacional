package com.spirit.medios.session;

import java.util.Collection;

import com.spirit.medios.session.generated._MapaComercialSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface MapaComercialSessionService extends _MapaComercialSessionService{

	Collection findMapaComercialByFechas(String fechaInicio, String fechaFinal)throws com.spirit.exception.GenericBusinessException;
	Collection findMapaComercialByPlanMedioDetalleIdAndFechas(Long idPlanMedioDetalle, String fechaInicio, String fechaFinal)throws com.spirit.exception.GenericBusinessException;
}
