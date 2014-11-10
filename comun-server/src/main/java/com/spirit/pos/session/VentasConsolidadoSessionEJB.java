package com.spirit.pos.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import com.spirit.pos.entity.VentasConsolidadoEJB;
import com.spirit.pos.entity.VentasConsolidadoIf;
import com.spirit.pos.session.generated._VentasConsolidadoSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>VentasConsolidadoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:19 $
 *
 */
@Stateless
public class VentasConsolidadoSessionEJB extends _VentasConsolidadoSession implements VentasConsolidadoSessionRemote, VentasConsolidadoSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(VentasConsolidadoSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
  
   /**
   *
   * Retrieves a list of data object for the specified fechaApertura field.
   *
   * @param fechaApertura the field
   * @return Collection of VentasConsolidadoIf data objects, empty list in case no results were found.
   */
 @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public java.util.Collection findVentasConsolidadoByFechaAperturaVarioscajaCodigo(java.sql.Timestamp fechaApertura,String cajaCodigo) {

	 String queryString = "from VentasConsolidadoEJB e where e.fechaApertura  >= :fechaApertura ";
    // Add a an order by on all primary keys to assure reproducable results.    
    if (cajaCodigo != null)	queryString += " and e.cajaCodigo = :cajaCodigo";
    
    String orderByPart = "";
    orderByPart += " order by e.fechaApertura desc";
    queryString += orderByPart;
    
    System.out.println("QUERYSTRING>>>"+queryString);
    
    Query query = manager.createQuery(queryString);
    query.setParameter("fechaApertura", fechaApertura);
    query.setParameter("cajaCodigo", cajaCodigo);
    return query.getResultList();
  }
 
   public VentasConsolidadoIf getVentasConsolidado(String cajaCodigo,String identificacionEmpleado) throws Exception
   {
	   String queryString="Select ventasConsolidado from VentasConsolidadoEJB ventasConsolidado " +
	   		"where " +
	   		"ventasConsolidado.cajaCodigo=:cajaCodigo and " +
	   		"ventasConsolidado.cajeroCedula=:identificacionEmpleado and " +
	   		"ventasConsolidado.fechaCierre is null";
	   
	   Query query=manager.createQuery(queryString);
	   query.setParameter("cajaCodigo", cajaCodigo);
	   query.setParameter("identificacionEmpleado", identificacionEmpleado);
	   //query.setParameter("fechaApertura", fechaApertura);
	   List result=query.getResultList();
	   if(result==null || result.size()<=0)
	   {
		   return null;
	   }
	   
	   return (VentasConsolidadoIf)result.get(0);
   }

   public java.util.Collection getVentasConsolidadoFechaApertura(String cajaCodigo,String fechaApertura) throws Exception
   {
	   String queryString="Select ventasConsolidado from VentasConsolidadoEJB ventasConsolidado " +
	   		"where " +
	   		"ventasConsolidado.cajaCodigo=:cajaCodigo and " +	   		
	   		"ventasConsolidado.fechaApertura>='"+fechaApertura+"' and ventasConsolidado.fechaApertura<='"+fechaApertura+" 23:59:59' order by ventasConsolidado.fechaApertura asc";
	   
	   
	   Query query=manager.createQuery(queryString);
	   query.setParameter("cajaCodigo", cajaCodigo);
	   //query.setParameter("fechaCierre", fechaCierre);
	   //query.setParameter("fechaApertura", fechaApertura);
	   List result=query.getResultList();
	   /*if(result==null || result.size()<=0)
	   {
		   return null;
	   }*/
	   
	   System.out.println("result132:"+result.size());
	   
	   return query.getResultList();
   }
   
    
}
