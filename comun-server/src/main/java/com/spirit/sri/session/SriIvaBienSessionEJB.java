package com.spirit.sri.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.sri.session.generated._SriIvaBienSession;

/**
 * The <code>SriIvaBienSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:17 $
 *
 */
@Stateless
public class SriIvaBienSessionEJB extends _SriIvaBienSession implements SriIvaBienSessionRemote  {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(SriIvaBienSessionEJB.class);
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection getDescripcionPorcentaje() {
		//select distinct descripcion_porcentaje from sri_iva_servicio
		String queryString = "select distinct descripcionPorcentaje from SriIvaBienEJB";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
}
