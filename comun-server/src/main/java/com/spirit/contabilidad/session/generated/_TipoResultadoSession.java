package com.spirit.contabilidad.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.contabilidad.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _TipoResultadoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TipoResultadoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.TipoResultadoIf addTipoResultado(com.spirit.contabilidad.entity.TipoResultadoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TipoResultadoEJB value = new TipoResultadoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setOrden(model.getOrden());
      value.setUtilidad(model.getUtilidad());
      value.setLeyendaResultado(model.getLeyendaResultado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tipoResultado.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tipoResultado.");
      }
     
      return getTipoResultado(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTipoResultado(com.spirit.contabilidad.entity.TipoResultadoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TipoResultadoEJB data = new TipoResultadoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setOrden(model.getOrden());
      data.setUtilidad(model.getUtilidad());
      data.setLeyendaResultado(model.getLeyendaResultado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tipoResultado.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tipoResultado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTipoResultado(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TipoResultadoEJB data = manager.find(TipoResultadoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tipoResultado.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tipoResultado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.TipoResultadoIf getTipoResultado(java.lang.Long id) {
      return (TipoResultadoEJB)queryManagerLocal.find(TipoResultadoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoResultadoList() {
	  return queryManagerLocal.singleClassList(TipoResultadoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoResultadoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TipoResultadoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoResultadoListSize() {
      Query countQuery = manager.createQuery("select count(*) from TipoResultadoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoResultadoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TipoResultadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoResultadoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TipoResultadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoResultadoByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(TipoResultadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoResultadoByOrden(java.lang.Long orden) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("orden", orden);
		return queryManagerLocal.singleClassQueryList(TipoResultadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoResultadoByUtilidad(java.lang.String utilidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("utilidad", utilidad);
		return queryManagerLocal.singleClassQueryList(TipoResultadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoResultadoByLeyendaResultado(java.lang.String leyendaResultado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("leyendaResultado", leyendaResultado);
		return queryManagerLocal.singleClassQueryList(TipoResultadoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TipoResultadoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoResultadoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TipoResultadoEJB.class, aMap);      
    }

/////////////////
}
