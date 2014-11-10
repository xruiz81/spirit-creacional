package com.spirit.nomina.session;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.ContratoPlantillaDetalleIf;
import com.spirit.nomina.entity.ContratoPlantillaIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.session.generated._ContratoPlantillaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ContratoPlantillaSessionService extends _ContratoPlantillaSessionService{
	
	public void procesarContratoPlantilla(ContratoPlantillaIf model,Map<Long,BigDecimal> mapaRubrosContrato) throws GenericBusinessException;
	public void actualizarContratoPlantilla(ContratoPlantillaIf model,Collection<ContratoPlantillaDetalleIf>  contratosRubroColeccion, Collection<RubroIf> rubrosContratoRemovidos)throws GenericBusinessException;
	public void eliminarContratoPlantilla(Long contratoId) throws GenericBusinessException ;
	
	public java.util.Collection findContratoPlantillaByQuery(int startIndex, int endIndex,Map aMap) throws GenericBusinessException;
	public int getContratoPlantillaListSize(Map aMap) throws GenericBusinessException;
	
	
}
