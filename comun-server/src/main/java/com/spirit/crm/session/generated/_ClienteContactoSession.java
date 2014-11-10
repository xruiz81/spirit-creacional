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
public abstract class _ClienteContactoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ClienteContactoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.ClienteContactoIf addClienteContacto(com.spirit.crm.entity.ClienteContactoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ClienteContactoEJB value = new ClienteContactoEJB();
      try {
      value.setId(model.getId());
      value.setTipocontactoId(model.getTipocontactoId());
      value.setClienteoficinaId(model.getClienteoficinaId());
      value.setNombre(model.getNombre());
      value.setDireccion(model.getDireccion());
      value.setTelefonoOfic(model.getTelefonoOfic());
      value.setTelefonoCasa(model.getTelefonoCasa());
      value.setCelular(model.getCelular());
      value.setMail(model.getMail());
      value.setCumpleanos(model.getCumpleanos());
      value.setCodigo(model.getCodigo());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en clienteContacto.", e);
			throw new GenericBusinessException(
					"Error al insertar información en clienteContacto.");
      }
     
      return getClienteContacto(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveClienteContacto(com.spirit.crm.entity.ClienteContactoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ClienteContactoEJB data = new ClienteContactoEJB();
      data.setId(model.getId());
      data.setTipocontactoId(model.getTipocontactoId());
      data.setClienteoficinaId(model.getClienteoficinaId());
      data.setNombre(model.getNombre());
      data.setDireccion(model.getDireccion());
      data.setTelefonoOfic(model.getTelefonoOfic());
      data.setTelefonoCasa(model.getTelefonoCasa());
      data.setCelular(model.getCelular());
      data.setMail(model.getMail());
      data.setCumpleanos(model.getCumpleanos());
      data.setCodigo(model.getCodigo());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en clienteContacto.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en clienteContacto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteClienteContacto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ClienteContactoEJB data = manager.find(ClienteContactoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en clienteContacto.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en clienteContacto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.ClienteContactoIf getClienteContacto(java.lang.Long id) {
      return (ClienteContactoEJB)queryManagerLocal.find(ClienteContactoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteContactoList() {
	  return queryManagerLocal.singleClassList(ClienteContactoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteContactoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ClienteContactoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getClienteContactoListSize() {
      Query countQuery = manager.createQuery("select count(*) from ClienteContactoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteContactoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ClienteContactoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteContactoByTipocontactoId(java.lang.Long tipocontactoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipocontactoId", tipocontactoId);
		return queryManagerLocal.singleClassQueryList(ClienteContactoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteContactoByClienteoficinaId(java.lang.Long clienteoficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteoficinaId", clienteoficinaId);
		return queryManagerLocal.singleClassQueryList(ClienteContactoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteContactoByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(ClienteContactoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteContactoByDireccion(java.lang.String direccion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("direccion", direccion);
		return queryManagerLocal.singleClassQueryList(ClienteContactoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteContactoByTelefonoOfic(java.lang.String telefonoOfic) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("telefonoOfic", telefonoOfic);
		return queryManagerLocal.singleClassQueryList(ClienteContactoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteContactoByTelefonoCasa(java.lang.String telefonoCasa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("telefonoCasa", telefonoCasa);
		return queryManagerLocal.singleClassQueryList(ClienteContactoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteContactoByCelular(java.lang.String celular) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("celular", celular);
		return queryManagerLocal.singleClassQueryList(ClienteContactoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteContactoByMail(java.lang.String mail) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("mail", mail);
		return queryManagerLocal.singleClassQueryList(ClienteContactoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteContactoByCumpleanos(java.sql.Timestamp cumpleanos) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cumpleanos", cumpleanos);
		return queryManagerLocal.singleClassQueryList(ClienteContactoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteContactoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ClienteContactoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ClienteContactoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteContactoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ClienteContactoEJB.class, aMap);      
    }

/////////////////
}
