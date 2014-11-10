package com.spirit.cartera.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.cartera.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _FormaPagoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_FormaPagoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.FormaPagoIf addFormaPago(com.spirit.cartera.entity.FormaPagoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      FormaPagoEJB value = new FormaPagoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      value.setDiasInicio(model.getDiasInicio());
      value.setDiasFin(model.getDiasFin());
      value.setDescuentoVenta(model.getDescuentoVenta());
      value.setDescuentoCartera(model.getDescuentoCartera());
      value.setInteres(model.getInteres());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en formaPago.", e);
			throw new GenericBusinessException(
					"Error al insertar información en formaPago.");
      }
     
      return getFormaPago(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveFormaPago(com.spirit.cartera.entity.FormaPagoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      FormaPagoEJB data = new FormaPagoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
      data.setDiasInicio(model.getDiasInicio());
      data.setDiasFin(model.getDiasFin());
      data.setDescuentoVenta(model.getDescuentoVenta());
      data.setDescuentoCartera(model.getDescuentoCartera());
      data.setInteres(model.getInteres());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en formaPago.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en formaPago.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteFormaPago(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      FormaPagoEJB data = manager.find(FormaPagoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en formaPago.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en formaPago.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.FormaPagoIf getFormaPago(java.lang.Long id) {
      return (FormaPagoEJB)queryManagerLocal.find(FormaPagoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFormaPagoList() {
	  return queryManagerLocal.singleClassList(FormaPagoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFormaPagoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(FormaPagoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getFormaPagoListSize() {
      Query countQuery = manager.createQuery("select count(*) from FormaPagoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFormaPagoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(FormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFormaPagoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(FormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFormaPagoByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(FormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFormaPagoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(FormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFormaPagoByDiasInicio(java.lang.Integer diasInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("diasInicio", diasInicio);
		return queryManagerLocal.singleClassQueryList(FormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFormaPagoByDiasFin(java.lang.Integer diasFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("diasFin", diasFin);
		return queryManagerLocal.singleClassQueryList(FormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFormaPagoByDescuentoVenta(java.math.BigDecimal descuentoVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuentoVenta", descuentoVenta);
		return queryManagerLocal.singleClassQueryList(FormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFormaPagoByDescuentoCartera(java.math.BigDecimal descuentoCartera) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuentoCartera", descuentoCartera);
		return queryManagerLocal.singleClassQueryList(FormaPagoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFormaPagoByInteres(java.math.BigDecimal interes) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("interes", interes);
		return queryManagerLocal.singleClassQueryList(FormaPagoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of FormaPagoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFormaPagoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(FormaPagoEJB.class, aMap);      
    }

/////////////////
}
