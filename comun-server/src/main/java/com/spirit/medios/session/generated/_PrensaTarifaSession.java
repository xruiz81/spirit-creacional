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
public abstract class _PrensaTarifaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PrensaTarifaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PrensaTarifaIf addPrensaTarifa(com.spirit.medios.entity.PrensaTarifaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PrensaTarifaEJB value = new PrensaTarifaEJB();
      try {
      value.setId(model.getId());
      value.setClienteId(model.getClienteId());
      value.setPrensaSeccionId(model.getPrensaSeccionId());
      value.setPrensaUbicacionId(model.getPrensaUbicacionId());
      value.setPrensaFormatoId(model.getPrensaFormatoId());
      value.setCodigo(model.getCodigo());
      value.setColor(model.getColor());
      value.setDia(model.getDia());
      value.setTarifaCalculada(model.getTarifaCalculada());
      value.setTarifa(model.getTarifa());
      value.setRecargo(model.getRecargo());
      value.setOperacion(model.getOperacion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en prensaTarifa.", e);
			throw new GenericBusinessException(
					"Error al insertar información en prensaTarifa.");
      }
     
      return getPrensaTarifa(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePrensaTarifa(com.spirit.medios.entity.PrensaTarifaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PrensaTarifaEJB data = new PrensaTarifaEJB();
      data.setId(model.getId());
      data.setClienteId(model.getClienteId());
      data.setPrensaSeccionId(model.getPrensaSeccionId());
      data.setPrensaUbicacionId(model.getPrensaUbicacionId());
      data.setPrensaFormatoId(model.getPrensaFormatoId());
      data.setCodigo(model.getCodigo());
      data.setColor(model.getColor());
      data.setDia(model.getDia());
      data.setTarifaCalculada(model.getTarifaCalculada());
      data.setTarifa(model.getTarifa());
      data.setRecargo(model.getRecargo());
      data.setOperacion(model.getOperacion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en prensaTarifa.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en prensaTarifa.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePrensaTarifa(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PrensaTarifaEJB data = manager.find(PrensaTarifaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en prensaTarifa.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en prensaTarifa.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PrensaTarifaIf getPrensaTarifa(java.lang.Long id) {
      return (PrensaTarifaEJB)queryManagerLocal.find(PrensaTarifaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPrensaTarifaList() {
	  return queryManagerLocal.singleClassList(PrensaTarifaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPrensaTarifaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PrensaTarifaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPrensaTarifaListSize() {
      Query countQuery = manager.createQuery("select count(*) from PrensaTarifaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaTarifaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PrensaTarifaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaTarifaByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(PrensaTarifaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaTarifaByPrensaSeccionId(java.lang.Long prensaSeccionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("prensaSeccionId", prensaSeccionId);
		return queryManagerLocal.singleClassQueryList(PrensaTarifaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaTarifaByPrensaUbicacionId(java.lang.Long prensaUbicacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("prensaUbicacionId", prensaUbicacionId);
		return queryManagerLocal.singleClassQueryList(PrensaTarifaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaTarifaByPrensaFormatoId(java.lang.Long prensaFormatoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("prensaFormatoId", prensaFormatoId);
		return queryManagerLocal.singleClassQueryList(PrensaTarifaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaTarifaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PrensaTarifaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaTarifaByColor(java.lang.String color) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("color", color);
		return queryManagerLocal.singleClassQueryList(PrensaTarifaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaTarifaByDia(java.lang.String dia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("dia", dia);
		return queryManagerLocal.singleClassQueryList(PrensaTarifaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaTarifaByTarifaCalculada(java.lang.String tarifaCalculada) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tarifaCalculada", tarifaCalculada);
		return queryManagerLocal.singleClassQueryList(PrensaTarifaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaTarifaByTarifa(java.math.BigDecimal tarifa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tarifa", tarifa);
		return queryManagerLocal.singleClassQueryList(PrensaTarifaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaTarifaByRecargo(java.math.BigDecimal recargo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("recargo", recargo);
		return queryManagerLocal.singleClassQueryList(PrensaTarifaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaTarifaByOperacion(java.lang.String operacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("operacion", operacion);
		return queryManagerLocal.singleClassQueryList(PrensaTarifaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PrensaTarifaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaTarifaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PrensaTarifaEJB.class, aMap);      
    }

/////////////////
}
