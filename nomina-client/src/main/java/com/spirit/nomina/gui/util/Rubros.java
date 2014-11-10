package com.spirit.nomina.gui.util;

import java.util.Map;

import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.RubroIf;

public class Rubros {
	
	public static RubroIf verificarRubrosEnMapa(Map<Long,RubroIf> mapaRubros,Map<Long,String> mapaTipoRubros,Long idRubro)
	throws GenericBusinessException {
		RubroIf rubroIf = null;
		if ( idRubro != null ){
			if ( !mapaRubros.containsKey(idRubro) ){
				rubroIf = SessionServiceLocator.getRubroSessionService().getRubro(idRubro);
				mapaRubros.put(rubroIf.getId(), rubroIf);
				if ( mapaTipoRubros != null )
					mapaTipoRubros.put(rubroIf.getId(), rubroIf.getTipoRubro());
			} else {
				rubroIf = mapaRubros.get(idRubro);
			}
		}
		return rubroIf;
	}

	
}
