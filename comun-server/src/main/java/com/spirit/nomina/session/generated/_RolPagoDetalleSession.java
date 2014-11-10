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
public abstract class _RolPagoDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_RolPagoDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.RolPagoDetalleIf addRolPagoDetalle(com.spirit.nomina.entity.RolPagoDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      RolPagoDetalleEJB value = new RolPagoDetalleEJB();
      try {
      value.setId(model.getId());
      value.setRolpagoId(model.getRolpagoId());
      value.setContratoId(model.getContratoId());
      value.setRubroId(model.getRubroId());
      value.setRubroEventualId(model.getRubroEventualId());
      value.setValor(model.getValor());
      value.setObservacion(model.getObservacion());
      value.setEstado(model.getEstado());
      value.setFechaPago(model.getFechaPago());
      value.setTipoPagoId(model.getTipoPagoId());
      value.setCuentaBancariaId(model.getCuentaBancariaId());
      value.setPreimpreso(model.getPreimpreso());
      value.setAsientoId(model.getAsientoId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en rolPagoDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en rolPagoDetalle.");
      }
     
      return getRolPagoDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveRolPagoDetalle(com.spirit.nomina.entity.RolPagoDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      RolPagoDetalleEJB data = new RolPagoDetalleEJB();
      data.setId(model.getId());
      data.setRolpagoId(model.getRolpagoId());
      data.setContratoId(model.getContratoId());
      data.setRubroId(model.getRubroId());
      data.setRubroEventualId(model.getRubroEventualId());
      data.setValor(model.getValor());
      data.setObservacion(model.getObservacion());
      data.setEstado(model.getEstado());
      data.setFechaPago(model.getFechaPago());
      data.setTipoPagoId(model.getTipoPagoId());
      data.setCuentaBancariaId(model.getCuentaBancariaId());
      data.setPreimpreso(model.getPreimpreso());
      data.setAsientoId(model.getAsientoId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en rolPagoDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en rolPagoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteRolPagoDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      RolPagoDetalleEJB data = manager.find(RolPagoDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en rolPagoDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en rolPagoDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.RolPagoDetalleIf getRolPagoDetalle(java.lang.Long id) {
      return (RolPagoDetalleEJB)queryManagerLocal.find(RolPagoDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRolPagoDetalleList() {
	  return queryManagerLocal.singleClassList(RolPagoDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRolPagoDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(RolPagoDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getRolPagoDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from RolPagoDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleByRolpagoId(java.lang.Long rolpagoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rolpagoId", rolpagoId);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleByContratoId(java.lang.Long contratoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("contratoId", contratoId);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleByRubroId(java.lang.Long rubroId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rubroId", rubroId);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleByRubroEventualId(java.lang.Long rubroEventualId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rubroEventualId", rubroEventualId);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleByFechaPago(java.sql.Date fechaPago) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaPago", fechaPago);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleByTipoPagoId(java.lang.Long tipoPagoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoPagoId", tipoPagoId);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleByCuentaBancariaId(java.lang.Long cuentaBancariaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaBancariaId", cuentaBancariaId);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleByPreimpreso(java.lang.String preimpreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("preimpreso", preimpreso);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleByAsientoId(java.lang.Long asientoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("asientoId", asientoId);
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of RolPagoDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(RolPagoDetalleEJB.class, aMap);      
    }

/////////////////
}
