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
public abstract class _NotaCreditoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_NotaCreditoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.NotaCreditoIf addNotaCredito(com.spirit.cartera.entity.NotaCreditoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      NotaCreditoEJB value = new NotaCreditoEJB();
      try {
      value.setId(model.getId());
      value.setOficinaId(model.getOficinaId());
      value.setTipoDocumentoId(model.getTipoDocumentoId());
      value.setCodigo(model.getCodigo());
      value.setOperadorNegocioId(model.getOperadorNegocioId());
      value.setMonedaId(model.getMonedaId());
      value.setUsuarioId(model.getUsuarioId());
      value.setFechaEmision(model.getFechaEmision());
      value.setFechaVencimiento(model.getFechaVencimiento());
      value.setFechaCaducidad(model.getFechaCaducidad());
      value.setPreimpreso(model.getPreimpreso());
      value.setAutorizacion(model.getAutorizacion());
      value.setReferencia(model.getReferencia());
      value.setValor(model.getValor());
      value.setIva(model.getIva());
      value.setIce(model.getIce());
      value.setOtroImpuesto(model.getOtroImpuesto());
      value.setObservacion(model.getObservacion());
      value.setEstado(model.getEstado());
      value.setTipoCartera(model.getTipoCartera());
      value.setReferenciaId(model.getReferenciaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en notaCredito.", e);
			throw new GenericBusinessException(
					"Error al insertar información en notaCredito.");
      }
     
      return getNotaCredito(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveNotaCredito(com.spirit.cartera.entity.NotaCreditoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      NotaCreditoEJB data = new NotaCreditoEJB();
      data.setId(model.getId());
      data.setOficinaId(model.getOficinaId());
      data.setTipoDocumentoId(model.getTipoDocumentoId());
      data.setCodigo(model.getCodigo());
      data.setOperadorNegocioId(model.getOperadorNegocioId());
      data.setMonedaId(model.getMonedaId());
      data.setUsuarioId(model.getUsuarioId());
      data.setFechaEmision(model.getFechaEmision());
      data.setFechaVencimiento(model.getFechaVencimiento());
      data.setFechaCaducidad(model.getFechaCaducidad());
      data.setPreimpreso(model.getPreimpreso());
      data.setAutorizacion(model.getAutorizacion());
      data.setReferencia(model.getReferencia());
      data.setValor(model.getValor());
      data.setIva(model.getIva());
      data.setIce(model.getIce());
      data.setOtroImpuesto(model.getOtroImpuesto());
      data.setObservacion(model.getObservacion());
      data.setEstado(model.getEstado());
      data.setTipoCartera(model.getTipoCartera());
      data.setReferenciaId(model.getReferenciaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en notaCredito.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en notaCredito.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteNotaCredito(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      NotaCreditoEJB data = manager.find(NotaCreditoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en notaCredito.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en notaCredito.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.NotaCreditoIf getNotaCredito(java.lang.Long id) {
      return (NotaCreditoEJB)queryManagerLocal.find(NotaCreditoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getNotaCreditoList() {
	  return queryManagerLocal.singleClassList(NotaCreditoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getNotaCreditoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(NotaCreditoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getNotaCreditoListSize() {
      Query countQuery = manager.createQuery("select count(*) from NotaCreditoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByTipoDocumentoId(java.lang.Long tipoDocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoDocumentoId", tipoDocumentoId);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByOperadorNegocioId(java.lang.Long operadorNegocioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("operadorNegocioId", operadorNegocioId);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByMonedaId(java.lang.Long monedaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("monedaId", monedaId);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByFechaEmision(java.sql.Timestamp fechaEmision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaEmision", fechaEmision);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByFechaVencimiento(java.sql.Timestamp fechaVencimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaVencimiento", fechaVencimiento);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByFechaCaducidad(java.sql.Timestamp fechaCaducidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCaducidad", fechaCaducidad);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByPreimpreso(java.lang.String preimpreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("preimpreso", preimpreso);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByAutorizacion(java.lang.String autorizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizacion", autorizacion);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByReferencia(java.lang.String referencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referencia", referencia);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByIva(java.math.BigDecimal iva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("iva", iva);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByIce(java.math.BigDecimal ice) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ice", ice);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByOtroImpuesto(java.math.BigDecimal otroImpuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("otroImpuesto", otroImpuesto);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByTipoCartera(java.lang.String tipoCartera) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoCartera", tipoCartera);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByReferenciaId(java.lang.Long referenciaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referenciaId", referenciaId);
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of NotaCreditoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNotaCreditoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(NotaCreditoEJB.class, aMap);      
    }

/////////////////
}
