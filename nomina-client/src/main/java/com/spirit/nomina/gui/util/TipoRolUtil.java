package com.spirit.nomina.gui.util;

import java.util.Map;

import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.TipoRolIf;

public class TipoRolUtil {

	
	public static synchronized TipoRolIf verificarMapaTipoRol(Map<Long,TipoRolIf> mapaTiposRol,Long tipoRolId) throws GenericBusinessException{
		
		TipoRolIf tipoRol = mapaTiposRol.get(tipoRolId);
		if ( tipoRol == null ){
			tipoRol = SessionServiceLocator.getTipoRolSessionService().getTipoRol(tipoRolId);
			mapaTiposRol.put(tipoRol.getId(),tipoRol);
		}
		return tipoRol;
		
	}

}
