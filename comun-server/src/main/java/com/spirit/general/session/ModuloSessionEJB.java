package com.spirit.general.session;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.Stateless;

import com.spirit.general.entity.ModuloIf;
import com.spirit.general.session.generated._ModuloSession;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class ModuloSessionEJB extends _ModuloSession implements ModuloSessionRemote,ModuloSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public Map mapearModulos() {
		Map modulosMap = new HashMap();
		Iterator modulosIterator = getModuloList().iterator();
		while (modulosIterator.hasNext()) {
			ModuloIf modulo = (ModuloIf) modulosIterator.next();
			modulosMap.put(modulo.getId(), modulo);
		}
		return modulosMap;
	}
}
