package com.spirit.crm.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.crm.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ClienteWebSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ClienteWebSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.ClienteWebIf addClienteWeb(com.spirit.crm.entity.ClienteWebIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ClienteWebEJB value = new ClienteWebEJB();
      try {
      value.setId(model.getId());
      value.setIdExterno(model.getIdExterno());
      value.setNombres(model.getNombres());
      value.setApellidos(model.getApellidos());
      value.setEmail(model.getEmail());
      value.setPais(model.getPais());
      value.setCiudad(model.getCiudad());
      value.setDireccion(model.getDireccion());
      value.setTelefono(model.getTelefono());
      value.setCelular(model.getCelular());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en clienteWeb.", e);
			throw new GenericBusinessException(
					"Error al insertar información en clienteWeb.");
      }
     
      return getClienteWeb(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveClienteWeb(com.spirit.crm.entity.ClienteWebIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ClienteWebEJB data = new ClienteWebEJB();
      data.setId(model.getId());
      data.setIdExterno(model.getIdExterno());
      data.setNombres(model.getNombres());
      data.setApellidos(model.getApellidos());
      data.setEmail(model.getEmail());
      data.setPais(model.getPais());
      data.setCiudad(model.getCiudad());
      data.setDireccion(model.getDireccion());
      data.setTelefono(model.getTelefono());
      data.setCelular(model.getCelular());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en clienteWeb.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en clienteWeb.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteClienteWeb(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ClienteWebEJB data = manager.find(ClienteWebEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en clienteWeb.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en clienteWeb.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.ClienteWebIf getClienteWeb(java.lang.Long id) {
      return (ClienteWebEJB)queryManagerLocal.find(ClienteWebEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteWebList() {
	  return queryManagerLocal.singleClassList(ClienteWebEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteWebList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ClienteWebEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getClienteWebListSize() {
      Query countQuery = manager.createQuery("select count(*) from ClienteWebEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteWebById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ClienteWebEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteWebByIdExterno(java.lang.String idExterno) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("idExterno", idExterno);
		return queryManagerLocal.singleClassQueryList(ClienteWebEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteWebByNombres(java.lang.String nombres) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombres", nombres);
		return queryManagerLocal.singleClassQueryList(ClienteWebEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteWebByApellidos(java.lang.String apellidos) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("apellidos", apellidos);
		return queryManagerLocal.singleClassQueryList(ClienteWebEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteWebByEmail(java.lang.String email) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("email", email);
		return queryManagerLocal.singleClassQueryList(ClienteWebEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteWebByPais(java.lang.String pais) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pais", pais);
		return queryManagerLocal.singleClassQueryList(ClienteWebEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteWebByCiudad(java.lang.String ciudad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ciudad", ciudad);
		return queryManagerLocal.singleClassQueryList(ClienteWebEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteWebByDireccion(java.lang.String direccion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("direccion", direccion);
		return queryManagerLocal.singleClassQueryList(ClienteWebEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteWebByTelefono(java.lang.String telefono) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("telefono", telefono);
		return queryManagerLocal.singleClassQueryList(ClienteWebEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteWebByCelular(java.lang.String celular) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("celular", celular);
		return queryManagerLocal.singleClassQueryList(ClienteWebEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ClienteWebIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteWebByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ClienteWebEJB.class, aMap);      
    }

/////////////////
}
