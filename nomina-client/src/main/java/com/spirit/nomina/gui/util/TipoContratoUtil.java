package com.spirit.nomina.gui.util;

import java.util.Map;

import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.TipoContratoIf;

public class TipoContratoUtil {

	
	public synchronized static TipoContratoIf verificarMapaTipoContrato(Map<Long,TipoContratoIf> mapaTiposContrato,Long tipoContratoId) throws GenericBusinessException{
		
		TipoContratoIf tipoContrato = mapaTiposContrato.get(tipoContratoId);
		if ( tipoContrato == null ){
			tipoContrato = SessionServiceLocator.getTipoContratoSessionService().getTipoContrato(tipoContratoId);
			mapaTiposContrato.put(tipoContrato.getId(),tipoContrato);
		}
		return tipoContrato;
		
	}

}
