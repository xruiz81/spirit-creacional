package com.spirit.compras.session.generated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.compras.entity.CompraRetencionEJB;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _CompraRetencionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CompraRetencionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.CompraRetencionIf addCompraRetencion(com.spirit.compras.entity.CompraRetencionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CompraRetencionEJB value = new CompraRetencionEJB();
      try {
      value.setId(model.getId());
      value.setEstablecimiento(model.getEstablecimiento());
      value.setPuntoEmision(model.getPuntoEmision());
      value.setSecuencial(model.getSecuencial());
      value.setAutorizacion(model.getAutorizacion());
      value.setFechaEmision(model.getFechaEmision());
      value.setCompraId(model.getCompraId());
      value.setEjercicioFiscal(model.getEjercicioFiscal());
      value.setBaseImponible(model.getBaseImponible());
      value.setImpuesto(model.getImpuesto());
      value.setPorcentajeRetencion(model.getPorcentajeRetencion());
      value.setValorRetenido(model.getValorRetenido());
      value.setCodigoImpuesto(model.getCodigoImpuesto());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en compraRetencion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en compraRetencion.");
      }
     
      return getCompraRetencion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCompraRetencion(com.spirit.compras.entity.CompraRetencionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CompraRetencionEJB data = new CompraRetencionEJB();
      data.setId(model.getId());
      data.setEstablecimiento(model.getEstablecimiento());
      data.setPuntoEmision(model.getPuntoEmision());
      data.setSecuencial(model.getSecuencial());
      data.setAutorizacion(model.getAutorizacion());
      data.setFechaEmision(model.getFechaEmision());
      data.setCompraId(model.getCompraId());
      data.setEjercicioFiscal(model.getEjercicioFiscal());
      data.setBaseImponible(model.getBaseImponible());
      data.setImpuesto(model.getImpuesto());
      data.setPorcentajeRetencion(model.getPorcentajeRetencion());
      data.setValorRetenido(model.getValorRetenido());
      data.setCodigoImpuesto(model.getCodigoImpuesto());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en compraRetencion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en compraRetencion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCompraRetencion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CompraRetencionEJB data = manager.find(CompraRetencionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en compraRetencion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en compraRetencion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.CompraRetencionIf getCompraRetencion(java.lang.Long id) {
      return (CompraRetencionEJB)queryManagerLocal.find(CompraRetencionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCompraRetencionList() {
	  return queryManagerLocal.singleClassList(CompraRetencionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCompraRetencionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CompraRetencionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCompraRetencionListSize() {
      Query countQuery = manager.createQuery("select count(*) from CompraRetencionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraRetencionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraRetencionByEstablecimiento(java.lang.String establecimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("establecimiento", establecimiento);
		return queryManagerLocal.singleClassQueryList(CompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraRetencionByPuntoEmision(java.lang.String puntoEmision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntoEmision", puntoEmision);
		return queryManagerLocal.singleClassQueryList(CompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraRetencionBySecuencial(java.lang.String secuencial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("secuencial", secuencial);
		return queryManagerLocal.singleClassQueryList(CompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraRetencionByAutorizacion(java.lang.String autorizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizacion", autorizacion);
		return queryManagerLocal.singleClassQueryList(CompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraRetencionByFechaEmision(java.sql.Date fechaEmision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaEmision", fechaEmision);
		return queryManagerLocal.singleClassQueryList(CompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraRetencionByCompraId(java.lang.Long compraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("compraId", compraId);
		return queryManagerLocal.singleClassQueryList(CompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraRetencionByEjercicioFiscal(java.lang.String ejercicioFiscal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ejercicioFiscal", ejercicioFiscal);
		return queryManagerLocal.singleClassQueryList(CompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraRetencionByBaseImponible(java.math.BigDecimal baseImponible) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("baseImponible", baseImponible);
		return queryManagerLocal.singleClassQueryList(CompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraRetencionByImpuesto(java.lang.String impuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("impuesto", impuesto);
		return queryManagerLocal.singleClassQueryList(CompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraRetencionByPorcentajeRetencion(java.math.BigDecimal porcentajeRetencion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeRetencion", porcentajeRetencion);
		return queryManagerLocal.singleClassQueryList(CompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraRetencionByValorRetenido(java.math.BigDecimal valorRetenido) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorRetenido", valorRetenido);
		return queryManagerLocal.singleClassQueryList(CompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraRetencionByCodigoImpuesto(java.lang.String codigoImpuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigoImpuesto", codigoImpuesto);
		return queryManagerLocal.singleClassQueryList(CompraRetencionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CompraRetencionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCompraRetencionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CompraRetencionEJB.class, aMap);      
    }

/////////////////
}
