package com.spirit.nomina.session;

import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.SalarioMinimoVitalIf;
import com.spirit.nomina.session.generated._SalarioMinimoVitalSession;
import com.spirit.util.FindQuery;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class SalarioMinimoVitalSessionEJB extends _SalarioMinimoVitalSession implements SalarioMinimoVitalSessionRemote,SalarioMinimoVitalSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
 * @throws GenericBusinessException 
    *******************************************************************************************************************/

	
	public SalarioMinimoVitalIf findSalarioMinimoVitalByFechaMedia(Date fechaMedia) throws GenericBusinessException{
		SalarioMinimoVitalIf smv = null;
		String objectName = "e";
		String cadenaQuery = "from SalarioMinimoVitalEJB " + objectName + " where ";
		//Query query = manager.createQuery(queryString);
		String orden = "";
		Map<String,Object> aMap =  new HashMap<String, Object>();
		aMap.put("fechaMedia", fechaMedia);
		Query query = FindQuery.findQueryByDates(aMap, objectName, cadenaQuery, orden, manager);
		
		Collection<SalarioMinimoVitalIf> salarios = query.getResultList();
		if ( salarios.size() == 1 ){
			return salarios.iterator().next();
		} else if ( salarios.size() > 1 ){
			throw new GenericBusinessException("Existe mas un registro de Salarios Minimo Vital donde se cruzan las fechas !!");
		}
		return null;
		
	}

}
