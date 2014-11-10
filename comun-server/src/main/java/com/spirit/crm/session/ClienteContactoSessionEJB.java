package com.spirit.crm.session;

import java.sql.Date;
import java.text.DecimalFormat;
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

import com.spirit.crm.entity.ClienteContactoEJB;
import com.spirit.crm.entity.ClienteContactoIf;
import com.spirit.crm.session.generated._ClienteContactoSession;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>ClienteContactoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:20 $
 *
 */
@Stateless
public class ClienteContactoSessionEJB extends _ClienteContactoSession implements ClienteContactoSessionRemote,ClienteContactoSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(ClienteContactoSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   private DecimalFormat formatoEntero = new DecimalFormat("000");
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteContactoList(int startIndex, int endIndex, Long idCliente) {
      if (startIndex < 1) {
         startIndex = 1;
      }
      if ( (endIndex - startIndex) < 0) {
         // Just return an empty list.
         return new ArrayList();
      }
      
      String queryString = "select e from ClienteEJB c, ClienteOficinaEJB co, ClienteContactoEJB e where c.id = co.clienteId and co.id = e.clienteoficinaId and c.id = " + idCliente;
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setFirstResult(startIndex - 1);
      query.setMaxResults(endIndex - startIndex + 1);
      return query.getResultList();
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteContactoList(int startIndex, int endIndex, Map aMap) throws GenericBusinessException {
		if (startIndex < 1) {
			startIndex = 1;
		}
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from ClienteContactoEJB " + objectName + " where " + where;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		query.setFirstResult(startIndex - 1);
		query.setMaxResults(endIndex - startIndex + 1);
		return query.getResultList();
	}

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteContactoList(int startIndex, int endIndex, Map aMap, Long idCliente) throws GenericBusinessException {
		if (startIndex < 1) {
			startIndex = 1;
		}
		if ((endIndex - startIndex) < 0) {
			// Just return an empty list.
			return new ArrayList();
		}
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		
		String queryString = "select e from ClienteContactoEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c where " + where + " and c.id = co.clienteId and co.id = e.clienteoficinaId and c.id = " + idCliente;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		query.setFirstResult(startIndex - 1);
		query.setMaxResults(endIndex - startIndex + 1);
		return query.getResultList();
	}
    	
   public int getClienteContactoListSize(Long idCliente) throws GenericBusinessException {
	   Query countQuery = manager.createQuery("select count(*) from ClienteEJB c, ClienteOficinaEJB co, ClienteContactoEJB e where c.id = co.clienteId and co.id = e.clienteoficinaId and c.id = " + idCliente);
	   List countQueryResult = countQuery.getResultList();
	   Long countResult = (Long) countQueryResult.get(0);
	   log.debug("The list size is: " + countResult.intValue());
	   return countResult.intValue();
	}

	public int getClienteContactoListSize(Map aMap) throws GenericBusinessException {
		String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "select count(*) from ClienteEJB " + objectName + " where " + where;
	 	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}

		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
	    log.debug("The list size is: " + countResult.intValue());
	    return countResult.intValue();
	}

	public int getClienteContactoListSize(Map aMap, Long idCliente) throws GenericBusinessException {
		String objectName = "e";
	 	String where = QueryBuilder.buildWhere(aMap, objectName);
	 	String queryString = "select count(*) from ClienteContactoEJB " + objectName + ", ClienteOficinaEJB co, ClienteEJB c where " + where + " and c.id = co.clienteId and co.id = e.clienteoficinaId and c.id = " + idCliente;
	 	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}

		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
	    log.debug("The list size is: " + countResult.intValue());
	    return countResult.intValue();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ClienteContactoEJB registrarClienteContacto(ClienteContactoIf modelClienteContacto) {
		ClienteContactoEJB clienteContacto = new ClienteContactoEJB();
			
		clienteContacto.setId(modelClienteContacto.getId());
		
		if ( modelClienteContacto.getId() == null ){
			String codigo = getMaximoCodigoClienteContacto(modelClienteContacto.getClienteoficinaId() );
			if(codigo.equals("")){
				codigo = "001";
			}else{
				codigo = formatoEntero.format(Integer.valueOf(codigo) + 1); 
			}
			clienteContacto.setCodigo(codigo);
		} else {
			clienteContacto.setCodigo(modelClienteContacto.getCodigo());
		}
		
		clienteContacto.setTipocontactoId(modelClienteContacto.getTipocontactoId());
		clienteContacto.setClienteoficinaId(modelClienteContacto.getClienteoficinaId());
		clienteContacto.setNombre(modelClienteContacto.getNombre());
		clienteContacto.setDireccion(modelClienteContacto.getDireccion());
		clienteContacto.setTelefonoOfic(modelClienteContacto.getTelefonoOfic());
		clienteContacto.setTelefonoCasa(modelClienteContacto.getTelefonoCasa());
		clienteContacto.setCelular(modelClienteContacto.getCelular());
		clienteContacto.setMail(modelClienteContacto.getMail());
		clienteContacto.setCumpleanos(modelClienteContacto.getCumpleanos());
			
		return clienteContacto;
	}

	public String getMaximoCodigoClienteContacto(Long clienteOficinaId){
		String queryString = "select max(cc.codigo) from ClienteContactoEJB cc where cc.clienteoficinaId = " + clienteOficinaId;
		Query query = manager.createQuery(queryString);
		String codigo = "";
		if(query.getResultList().get(0)!=null){
			codigo = query.getResultList().toString().substring(1).replaceAll("]","");
		}
		return codigo;
	}

}
