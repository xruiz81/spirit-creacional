package com.spirit.pos.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.pos.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _TarjetaTransaccionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TarjetaTransaccionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.TarjetaTransaccionIf addTarjetaTransaccion(com.spirit.pos.entity.TarjetaTransaccionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TarjetaTransaccionEJB value = new TarjetaTransaccionEJB();
      try {
      value.setId(model.getId());
      value.setTipoDocumentoId(model.getTipoDocumentoId());
      value.setDocumentoId(model.getDocumentoId());
      value.setTarjetaId(model.getTarjetaId());
      value.setFecha(model.getFecha());
      value.setReferido(model.getReferido());
      value.setReferidoPor(model.getReferidoPor());
      value.setFacturaId(model.getFacturaId());
      value.setPuntosGanados(model.getPuntosGanados());
      value.setPuntosUtilizados(model.getPuntosUtilizados());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tarjetaTransaccion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tarjetaTransaccion.");
      }
     
      return getTarjetaTransaccion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTarjetaTransaccion(com.spirit.pos.entity.TarjetaTransaccionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TarjetaTransaccionEJB data = new TarjetaTransaccionEJB();
      data.setId(model.getId());
      data.setTipoDocumentoId(model.getTipoDocumentoId());
      data.setDocumentoId(model.getDocumentoId());
      data.setTarjetaId(model.getTarjetaId());
      data.setFecha(model.getFecha());
      data.setReferido(model.getReferido());
      data.setReferidoPor(model.getReferidoPor());
      data.setFacturaId(model.getFacturaId());
      data.setPuntosGanados(model.getPuntosGanados());
      data.setPuntosUtilizados(model.getPuntosUtilizados());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tarjetaTransaccion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tarjetaTransaccion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTarjetaTransaccion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TarjetaTransaccionEJB data = manager.find(TarjetaTransaccionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tarjetaTransaccion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tarjetaTransaccion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.TarjetaTransaccionIf getTarjetaTransaccion(java.lang.Long id) {
      return (TarjetaTransaccionEJB)queryManagerLocal.find(TarjetaTransaccionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTarjetaTransaccionList() {
	  return queryManagerLocal.singleClassList(TarjetaTransaccionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTarjetaTransaccionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TarjetaTransaccionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTarjetaTransaccionListSize() {
      Query countQuery = manager.createQuery("select count(*) from TarjetaTransaccionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTransaccionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TarjetaTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTransaccionByTipoDocumentoId(java.lang.Long tipoDocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoDocumentoId", tipoDocumentoId);
		return queryManagerLocal.singleClassQueryList(TarjetaTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTransaccionByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(TarjetaTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTransaccionByTarjetaId(java.lang.Long tarjetaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tarjetaId", tarjetaId);
		return queryManagerLocal.singleClassQueryList(TarjetaTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTransaccionByFecha(java.sql.Timestamp fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(TarjetaTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTransaccionByReferido(java.lang.String referido) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referido", referido);
		return queryManagerLocal.singleClassQueryList(TarjetaTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTransaccionByReferidoPor(java.lang.Long referidoPor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referidoPor", referidoPor);
		return queryManagerLocal.singleClassQueryList(TarjetaTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTransaccionByFacturaId(java.lang.Long facturaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("facturaId", facturaId);
		return queryManagerLocal.singleClassQueryList(TarjetaTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTransaccionByPuntosGanados(java.math.BigDecimal puntosGanados) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntosGanados", puntosGanados);
		return queryManagerLocal.singleClassQueryList(TarjetaTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTransaccionByPuntosUtilizados(java.math.BigDecimal puntosUtilizados) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntosUtilizados", puntosUtilizados);
		return queryManagerLocal.singleClassQueryList(TarjetaTransaccionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TarjetaTransaccionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTransaccionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TarjetaTransaccionEJB.class, aMap);      
    }

/////////////////
}
