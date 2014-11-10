package com.spirit.medios.session;

import java.util.Collection;

import javax.ejb.*;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.session.generated._TiempoParcialDotDetalleSession;
import com.spirit.medios.session.TiempoParcialDotDetalleSessionLocal;
import com.spirit.medios.session.TiempoParcialDotDetalleSessionRemote;
import com.spirit.medios.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class TiempoParcialDotDetalleSessionEJB extends _TiempoParcialDotDetalleSession implements TiempoParcialDotDetalleSessionRemote,TiempoParcialDotDetalleSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public Long findTiempoTotalByTiempoDotId(java.lang.Long idTiempoParcialDot) 
	throws GenericBusinessException {
		
		try{
			String queryString = "Select sum(detalle.tiempo) from TiempoParcialDotDetalleEJB detalle" +
				" where detalle.idTiempoParcialDot = :tpdId ";
			
			Query query = manager.createQuery(queryString);
			query.setParameter("tpdId", idTiempoParcialDot);
			Collection resultado = query.getResultList();
			Object objeto = resultado.iterator().next();
			Long cantidad = (Long)objeto;
			return cantidad;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al calcular cantidad de detalles !!");
		}
	}

}
