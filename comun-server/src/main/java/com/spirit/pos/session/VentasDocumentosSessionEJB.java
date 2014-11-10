package com.spirit.pos.session;

import java.util.*;

import javax.ejb.*;
import javax.persistence.*;

import com.spirit.exception.GenericBusinessException;

import com.spirit.pos.entity.*;
import com.spirit.pos.session.generated._VentasDocumentosSession;

import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
/**
 * The <code>VentasDocumentosSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:19 $
 *
 */
@Stateless
public class VentasDocumentosSessionEJB extends _VentasDocumentosSession  implements VentasDocumentosSessionRemote,VentasDocumentosSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(VentasDocumentosSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/



   //johanna
     @TransactionAttribute(TransactionAttributeType.REQUIRED)
     public java.util.Collection findVentasDocumentosByQueryVariosId(Map aMap) {
  	   
  	   
  	   Vector<String> ventastrackId= (Vector)aMap.get("ventastrackId");
  	   aMap.remove("ventastrackId");
  	   
  	String objectName = "e";
  	String where = QueryBuilder.buildWhere(aMap, objectName);
  	
  		
  	String queryString = "from VentasDocumentosEJB " + objectName + " where "
  				+ where;
  	
  	
  	if ( ventastrackId!=null && ventastrackId.size() > 0 ){
  		queryString += "and  (";
  		for ( String estado : ventastrackId ){
  			queryString += (" e.ventastrackId = '"+estado+"' or");
  		}
  		queryString = queryString.substring(0,queryString.length()-3);
  		queryString += " )";
  	}
  	
  	
  	Query query = manager.createQuery(queryString);

  		Set keys = aMap.keySet();
  		Iterator it = keys.iterator();

  		while (it.hasNext()) {
  			String propertyKey = (String) it.next();
  			Object property = aMap.get(propertyKey);
  			query.setParameter(propertyKey, property);

  		}

  		return query.getResultList();
       
     }




 
}
