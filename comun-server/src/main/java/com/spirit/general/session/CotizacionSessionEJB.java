package com.spirit.general.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.general.session.generated._CotizacionSession;

/**
 * The <code>CotizacionSession</code> session bean, which acts as a facade to
 * the underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:16 $
 * 
 */
@Stateless
public class CotizacionSessionEJB extends _CotizacionSession implements CotizacionSessionRemote {

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCotizacionByMonedaAndByMonedaEquivalente(
			java.lang.Long monedaId, java.lang.Long monedaEquivId) {

		String queryString = "from CotizacionEJB e where e.monedaId = "
				+ monedaId + " and e.monedaequivId = " + monedaEquivId;
		// Add a an order by on all primary keys to assure reproducable results.
		String orderByPart = "";
		orderByPart += " order by e.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);

		return query.getResultList();
	}
	
}
