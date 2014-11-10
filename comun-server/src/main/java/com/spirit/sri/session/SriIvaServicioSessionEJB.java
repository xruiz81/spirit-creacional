package com.spirit.sri.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.sri.entity.SriIvaServicioEJB;
import com.spirit.sri.session.generated._SriIvaServicioSession;

/**
 * The <code>SriIvaServicioSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:17 $
 *
 */
@Stateless
public class SriIvaServicioSessionEJB extends _SriIvaServicioSession implements SriIvaServicioSessionRemote  {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(SriIvaServicioSessionEJB.class);
	
	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection getDescripcionPorcentaje() {
		//select distinct descripcion_porcentaje from sri_iva_servicio
		String queryString = "select distinct descripcionPorcentaje from SriIvaServicioEJB";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
}
