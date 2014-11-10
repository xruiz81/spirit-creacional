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
public abstract class _TipoEmpleadoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TipoEmpleadoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoEmpleadoIf addTipoEmpleado(com.spirit.general.entity.TipoEmpleadoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TipoEmpleadoEJB value = new TipoEmpleadoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      value.setVendedor(model.getVendedor());
      value.setSupervisor(model.getSupervisor());
      value.setAdministrador(model.getAdministrador());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tipoEmpleado.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tipoEmpleado.");
      }
     
      return getTipoEmpleado(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTipoEmpleado(com.spirit.general.entity.TipoEmpleadoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TipoEmpleadoEJB data = new TipoEmpleadoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
      data.setVendedor(model.getVendedor());
      data.setSupervisor(model.getSupervisor());
      data.setAdministrador(model.getAdministrador());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tipoEmpleado.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tipoEmpleado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTipoEmpleado(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TipoEmpleadoEJB data = manager.find(TipoEmpleadoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tipoEmpleado.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tipoEmpleado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoEmpleadoIf getTipoEmpleado(java.lang.Long id) {
      return (TipoEmpleadoEJB)queryManagerLocal.find(TipoEmpleadoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoEmpleadoList() {
	  return queryManagerLocal.singleClassList(TipoEmpleadoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoEmpleadoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TipoEmpleadoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoEmpleadoListSize() {
      Query countQuery = manager.createQuery("select count(*) from TipoEmpleadoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoEmpleadoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoEmpleadoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoEmpleadoByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(TipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoEmpleadoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(TipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoEmpleadoByVendedor(java.lang.String vendedor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("vendedor", vendedor);
		return queryManagerLocal.singleClassQueryList(TipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoEmpleadoBySupervisor(java.lang.String supervisor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("supervisor", supervisor);
		return queryManagerLocal.singleClassQueryList(TipoEmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoEmpleadoByAdministrador(java.lang.String administrador) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("administrador", administrador);
		return queryManagerLocal.singleClassQueryList(TipoEmpleadoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TipoEmpleadoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoEmpleadoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TipoEmpleadoEJB.class, aMap);      
    }

/////////////////
}
