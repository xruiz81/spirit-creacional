package com.spirit.sri.session;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
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
import com.spirit.sri.session.generated._SriIvaSession;

/**
 * The <code>SriIvaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:17 $
 *
 */
@Stateless
public class SriIvaSessionEJB extends _SriIvaSession implements SriIvaSessionRemote  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(SriIvaSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection getIvaByFecha(Date fecha) 
	throws com.spirit.exception.GenericBusinessException{
		try{
			String objectName = "e";
			String queryString = "select distinct e from SriIvaEJB " + objectName + " " ;
			if (fecha!=null){
				 queryString += " where :fecha >= e.fechaInicio "
				 	+" and ( e.fechaFin is null or :fecha <= e.fechaFin )";
			 }
			
			Query query = manager.createQuery(queryString);
			query.setParameter("fecha",fecha);
			
			return query.getResultList();
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al consultar IVA por fecha");
		}
	} 
   
    
}
