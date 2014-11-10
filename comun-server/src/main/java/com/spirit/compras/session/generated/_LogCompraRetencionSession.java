package com.spirit.compras.session.generated;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.compras.entity.LogCompraRetencionEJB;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _LogCompraRetencionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_LogCompraRetencionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.LogCompraRetencionIf addLogCompraRetencion(com.spirit.compras.entity.LogCompraRetencionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      LogCompraRetencionEJB value = new LogCompraRetencionEJB();
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
      value.setLog(model.getLog());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en logCompraRetencion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en logCompraRetencion.");
      }
     
      return getLogCompraRetencion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveLogCompraRetencion(com.spirit.compras.entity.LogCompraRetencionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      LogCompraRetencionEJB data = new LogCompraRetencionEJB();
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
      data.setLog(model.getLog());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en logCompraRetencion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en logCompraRetencion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteLogCompraRetencion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      LogCompraRetencionEJB data = manager.find(LogCompraRetencionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en logCompraRetencion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en logCompraRetencion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.compras.entity.LogCompraRetencionIf getLogCompraRetencion(java.lang.Long id) {
      return (LogCompraRetencionEJB)queryManagerLocal.find(LogCompraRetencionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getLogCompraRetencionList() {
	  return queryManagerLocal.singleClassList(LogCompraRetencionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getLogCompraRetencionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(LogCompraRetencionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getLogCompraRetencionListSize() {
      Query countQuery = manager.createQuery("select count(*) from LogCompraRetencionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionByEstablecimiento(java.lang.String establecimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("establecimiento", establecimiento);
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionByPuntoEmision(java.lang.String puntoEmision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntoEmision", puntoEmision);
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionBySecuencial(java.lang.String secuencial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("secuencial", secuencial);
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionByAutorizacion(java.lang.String autorizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizacion", autorizacion);
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionByFechaEmision(java.sql.Date fechaEmision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaEmision", fechaEmision);
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionByCompraId(java.lang.Long compraId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("compraId", compraId);
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionByEjercicioFiscal(java.lang.String ejercicioFiscal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ejercicioFiscal", ejercicioFiscal);
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionByBaseImponible(java.math.BigDecimal baseImponible) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("baseImponible", baseImponible);
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionByImpuesto(java.lang.String impuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("impuesto", impuesto);
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionByPorcentajeRetencion(java.math.BigDecimal porcentajeRetencion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeRetencion", porcentajeRetencion);
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionByValorRetenido(java.math.BigDecimal valorRetenido) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorRetenido", valorRetenido);
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionByCodigoImpuesto(java.lang.String codigoImpuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigoImpuesto", codigoImpuesto);
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionByLog(java.lang.String log) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("log", log);
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of LogCompraRetencionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCompraRetencionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(LogCompraRetencionEJB.class, aMap);      
    }

/////////////////
}
