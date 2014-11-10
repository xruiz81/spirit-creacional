package com.spirit.general.session;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.Stateless;

import com.spirit.general.entity.TipoTroqueladoIf;
import com.spirit.general.session.generated._TipoTroqueladoSession;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class TipoTroqueladoSessionEJB extends _TipoTroqueladoSession implements TipoTroqueladoSessionRemote{

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


	public Map mapearTiposTroquelado() {
		Map tiposTroqueladoMap = new HashMap();
		Iterator tiposTroqueladoIterator = getTipoTroqueladoList().iterator();
		while (tiposTroqueladoIterator.hasNext()) {
			TipoTroqueladoIf tipoTroquelado = (TipoTroqueladoIf) tiposTroqueladoIterator.next();
			tiposTroqueladoMap.put(tipoTroquelado.getId(), tipoTroquelado);
		}
		return tiposTroqueladoMap;
	}
}
