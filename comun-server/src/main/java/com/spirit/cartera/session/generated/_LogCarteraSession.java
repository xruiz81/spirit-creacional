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
public abstract class _LogCarteraSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_LogCarteraSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.LogCarteraIf addLogCartera(com.spirit.cartera.entity.LogCarteraIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      LogCarteraEJB value = new LogCarteraEJB();
      try {
      value.setId(model.getId());
      value.setTipo(model.getTipo());
      value.setOficinaId(model.getOficinaId());
      value.setTipodocumentoId(model.getTipodocumentoId());
      value.setCodigo(model.getCodigo());
      value.setReferenciaId(model.getReferenciaId());
      value.setClienteoficinaId(model.getClienteoficinaId());
      value.setPreimpreso(model.getPreimpreso());
      value.setUsuarioId(model.getUsuarioId());
      value.setVendedorId(model.getVendedorId());
      value.setMonedaId(model.getMonedaId());
      value.setFechaEmision(model.getFechaEmision());
      value.setValor(model.getValor());
      value.setSaldo(model.getSaldo());
      value.setFechaUltimaActualizacion(model.getFechaUltimaActualizacion());
      value.setEstado(model.getEstado());
      value.setComentario(model.getComentario());
      value.setAprobado(model.getAprobado());
      value.setLog(model.getLog());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setFechaLog(model.getFechaLog());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en logCartera.", e);
			throw new GenericBusinessException(
					"Error al insertar información en logCartera.");
      }
     
      return getLogCartera(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveLogCartera(com.spirit.cartera.entity.LogCarteraIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      LogCarteraEJB data = new LogCarteraEJB();
      data.setId(model.getId());
      data.setTipo(model.getTipo());
      data.setOficinaId(model.getOficinaId());
      data.setTipodocumentoId(model.getTipodocumentoId());
      data.setCodigo(model.getCodigo());
      data.setReferenciaId(model.getReferenciaId());
      data.setClienteoficinaId(model.getClienteoficinaId());
      data.setPreimpreso(model.getPreimpreso());
      data.setUsuarioId(model.getUsuarioId());
      data.setVendedorId(model.getVendedorId());
      data.setMonedaId(model.getMonedaId());
      data.setFechaEmision(model.getFechaEmision());
      data.setValor(model.getValor());
      data.setSaldo(model.getSaldo());
      data.setFechaUltimaActualizacion(model.getFechaUltimaActualizacion());
      data.setEstado(model.getEstado());
      data.setComentario(model.getComentario());
      data.setAprobado(model.getAprobado());
      data.setLog(model.getLog());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setFechaLog(model.getFechaLog());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en logCartera.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en logCartera.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteLogCartera(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      LogCarteraEJB data = manager.find(LogCarteraEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en logCartera.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en logCartera.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.LogCarteraIf getLogCartera(java.lang.Long id) {
      return (LogCarteraEJB)queryManagerLocal.find(LogCarteraEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getLogCarteraList() {
	  return queryManagerLocal.singleClassList(LogCarteraEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getLogCarteraList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(LogCarteraEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getLogCarteraListSize() {
      Query countQuery = manager.createQuery("select count(*) from LogCarteraEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByTipo(java.lang.String tipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipo", tipo);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByTipodocumentoId(java.lang.Long tipodocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoId", tipodocumentoId);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByReferenciaId(java.lang.Long referenciaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referenciaId", referenciaId);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByClienteoficinaId(java.lang.Long clienteoficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteoficinaId", clienteoficinaId);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByPreimpreso(java.lang.String preimpreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("preimpreso", preimpreso);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByVendedorId(java.lang.Long vendedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("vendedorId", vendedorId);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByMonedaId(java.lang.Long monedaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("monedaId", monedaId);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByFechaEmision(java.sql.Timestamp fechaEmision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaEmision", fechaEmision);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraBySaldo(java.math.BigDecimal saldo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("saldo", saldo);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByFechaUltimaActualizacion(java.sql.Timestamp fechaUltimaActualizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaUltimaActualizacion", fechaUltimaActualizacion);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByComentario(java.lang.String comentario) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("comentario", comentario);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByAprobado(java.lang.String aprobado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("aprobado", aprobado);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByLog(java.lang.String log) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("log", log);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByFechaLog(java.sql.Timestamp fechaLog) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaLog", fechaLog);
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of LogCarteraIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findLogCarteraByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(LogCarteraEJB.class, aMap);      
    }

/////////////////
}
