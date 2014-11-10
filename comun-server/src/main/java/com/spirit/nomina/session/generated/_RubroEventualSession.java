package com.spirit.nomina.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.nomina.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _RubroEventualSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_RubroEventualSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.RubroEventualIf addRubroEventual(com.spirit.nomina.entity.RubroEventualIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      RubroEventualEJB value = new RubroEventualEJB();
      try {
      value.setId(model.getId());
      value.setContratoId(model.getContratoId());
      value.setRubroId(model.getRubroId());
      value.setTipoRolIdCobro(model.getTipoRolIdCobro());
      value.setValor(model.getValor());
      value.setFechaCobro(model.getFechaCobro());
      value.setEstado(model.getEstado());
      value.setObservacion(model.getObservacion());
      value.setTipoRolIdPago(model.getTipoRolIdPago());
      value.setFechaPago(model.getFechaPago());
      value.setIdentificacion(model.getIdentificacion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en rubroEventual.", e);
			throw new GenericBusinessException(
					"Error al insertar información en rubroEventual.");
      }
     
      return getRubroEventual(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveRubroEventual(com.spirit.nomina.entity.RubroEventualIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      RubroEventualEJB data = new RubroEventualEJB();
      data.setId(model.getId());
      data.setContratoId(model.getContratoId());
      data.setRubroId(model.getRubroId());
      data.setTipoRolIdCobro(model.getTipoRolIdCobro());
      data.setValor(model.getValor());
      data.setFechaCobro(model.getFechaCobro());
      data.setEstado(model.getEstado());
      data.setObservacion(model.getObservacion());
      data.setTipoRolIdPago(model.getTipoRolIdPago());
      data.setFechaPago(model.getFechaPago());
      data.setIdentificacion(model.getIdentificacion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en rubroEventual.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en rubroEventual.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteRubroEventual(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      RubroEventualEJB data = manager.find(RubroEventualEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en rubroEventual.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en rubroEventual.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.RubroEventualIf getRubroEventual(java.lang.Long id) {
      return (RubroEventualEJB)queryManagerLocal.find(RubroEventualEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRubroEventualList() {
	  return queryManagerLocal.singleClassList(RubroEventualEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRubroEventualList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(RubroEventualEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getRubroEventualListSize() {
      Query countQuery = manager.createQuery("select count(*) from RubroEventualEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroEventualById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(RubroEventualEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroEventualByContratoId(java.lang.Long contratoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("contratoId", contratoId);
		return queryManagerLocal.singleClassQueryList(RubroEventualEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroEventualByRubroId(java.lang.Long rubroId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rubroId", rubroId);
		return queryManagerLocal.singleClassQueryList(RubroEventualEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroEventualByTipoRolIdCobro(java.lang.Long tipoRolIdCobro) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoRolIdCobro", tipoRolIdCobro);
		return queryManagerLocal.singleClassQueryList(RubroEventualEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroEventualByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(RubroEventualEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroEventualByFechaCobro(java.sql.Date fechaCobro) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCobro", fechaCobro);
		return queryManagerLocal.singleClassQueryList(RubroEventualEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroEventualByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(RubroEventualEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroEventualByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(RubroEventualEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroEventualByTipoRolIdPago(java.lang.Long tipoRolIdPago) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoRolIdPago", tipoRolIdPago);
		return queryManagerLocal.singleClassQueryList(RubroEventualEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroEventualByFechaPago(java.sql.Date fechaPago) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaPago", fechaPago);
		return queryManagerLocal.singleClassQueryList(RubroEventualEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroEventualByIdentificacion(java.lang.Long identificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("identificacion", identificacion);
		return queryManagerLocal.singleClassQueryList(RubroEventualEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of RubroEventualIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroEventualByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(RubroEventualEJB.class, aMap);      
    }

/////////////////
}
