package com.spirit.medios.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.medios.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _EquipoTrabajoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_EquipoTrabajoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.EquipoTrabajoIf addEquipoTrabajo(com.spirit.medios.entity.EquipoTrabajoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      EquipoTrabajoEJB value = new EquipoTrabajoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setTipoordenId(model.getTipoordenId());
      value.setEmpresaId(model.getEmpresaId());
      value.setFechaini(model.getFechaini());
      value.setFechafin(model.getFechafin());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en equipoTrabajo.", e);
			throw new GenericBusinessException(
					"Error al insertar información en equipoTrabajo.");
      }
     
      return getEquipoTrabajo(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveEquipoTrabajo(com.spirit.medios.entity.EquipoTrabajoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      EquipoTrabajoEJB data = new EquipoTrabajoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setTipoordenId(model.getTipoordenId());
      data.setEmpresaId(model.getEmpresaId());
      data.setFechaini(model.getFechaini());
      data.setFechafin(model.getFechafin());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en equipoTrabajo.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en equipoTrabajo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteEquipoTrabajo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      EquipoTrabajoEJB data = manager.find(EquipoTrabajoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en equipoTrabajo.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en equipoTrabajo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.EquipoTrabajoIf getEquipoTrabajo(java.lang.Long id) {
      return (EquipoTrabajoEJB)queryManagerLocal.find(EquipoTrabajoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEquipoTrabajoList() {
	  return queryManagerLocal.singleClassList(EquipoTrabajoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEquipoTrabajoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(EquipoTrabajoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getEquipoTrabajoListSize() {
      Query countQuery = manager.createQuery("select count(*) from EquipoTrabajoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEquipoTrabajoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(EquipoTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEquipoTrabajoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(EquipoTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEquipoTrabajoByTipoordenId(java.lang.Long tipoordenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoordenId", tipoordenId);
		return queryManagerLocal.singleClassQueryList(EquipoTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEquipoTrabajoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(EquipoTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEquipoTrabajoByFechaini(java.sql.Date fechaini) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaini", fechaini);
		return queryManagerLocal.singleClassQueryList(EquipoTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEquipoTrabajoByFechafin(java.sql.Date fechafin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechafin", fechafin);
		return queryManagerLocal.singleClassQueryList(EquipoTrabajoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEquipoTrabajoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(EquipoTrabajoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of EquipoTrabajoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEquipoTrabajoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(EquipoTrabajoEJB.class, aMap);      
    }

/////////////////
}
