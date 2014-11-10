package com.spirit.contabilidad.session;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.Stateless;

import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.contabilidad.session.generated._TipoCuentaSession;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class TipoCuentaSessionEJB extends _TipoCuentaSession implements TipoCuentaSessionRemote,TipoCuentaSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public Map mapearTiposCuenta() {
		Map tiposCuentaMap = new HashMap();	
		Iterator tiposCuentaIterator = getTipoCuentaList().iterator();
		while (tiposCuentaIterator.hasNext()) {
			TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaIterator.next();
			tiposCuentaMap.put(tipoCuenta.getId(), tipoCuenta);
		}
		return tiposCuentaMap;
	}
}
