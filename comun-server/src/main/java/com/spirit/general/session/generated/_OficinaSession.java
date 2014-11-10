package com.spirit.general.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.general.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _OficinaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_OficinaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.OficinaIf addOficina(com.spirit.general.entity.OficinaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      OficinaEJB value = new OficinaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      value.setCiudadId(model.getCiudadId());
      value.setAdministradorId(model.getAdministradorId());
      value.setDireccion(model.getDireccion());
      value.setTelefono(model.getTelefono());
      value.setFax(model.getFax());
      value.setPreimpresoSerie(model.getPreimpresoSerie());
      value.setServidorId(model.getServidorId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en oficina.", e);
			throw new GenericBusinessException(
					"Error al insertar información en oficina.");
      }
     
      return getOficina(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveOficina(com.spirit.general.entity.OficinaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      OficinaEJB data = new OficinaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
      data.setCiudadId(model.getCiudadId());
      data.setAdministradorId(model.getAdministradorId());
      data.setDireccion(model.getDireccion());
      data.setTelefono(model.getTelefono());
      data.setFax(model.getFax());
      data.setPreimpresoSerie(model.getPreimpresoSerie());
      data.setServidorId(model.getServidorId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en oficina.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en oficina.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteOficina(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      OficinaEJB data = manager.find(OficinaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en oficina.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en oficina.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.OficinaIf getOficina(java.lang.Long id) {
      return (OficinaEJB)queryManagerLocal.find(OficinaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOficinaList() {
	  return queryManagerLocal.singleClassList(OficinaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOficinaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(OficinaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getOficinaListSize() {
      Query countQuery = manager.createQuery("select count(*) from OficinaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOficinaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(OficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOficinaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(OficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOficinaByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(OficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOficinaByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(OficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOficinaByCiudadId(java.lang.Long ciudadId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ciudadId", ciudadId);
		return queryManagerLocal.singleClassQueryList(OficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOficinaByAdministradorId(java.lang.Long administradorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("administradorId", administradorId);
		return queryManagerLocal.singleClassQueryList(OficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOficinaByDireccion(java.lang.String direccion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("direccion", direccion);
		return queryManagerLocal.singleClassQueryList(OficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOficinaByTelefono(java.lang.String telefono) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("telefono", telefono);
		return queryManagerLocal.singleClassQueryList(OficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOficinaByFax(java.lang.String fax) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fax", fax);
		return queryManagerLocal.singleClassQueryList(OficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOficinaByPreimpresoSerie(java.lang.String preimpresoSerie) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("preimpresoSerie", preimpresoSerie);
		return queryManagerLocal.singleClassQueryList(OficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOficinaByServidorId(java.lang.Long servidorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("servidorId", servidorId);
		return queryManagerLocal.singleClassQueryList(OficinaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of OficinaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOficinaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(OficinaEJB.class, aMap);      
    }

/////////////////
}
