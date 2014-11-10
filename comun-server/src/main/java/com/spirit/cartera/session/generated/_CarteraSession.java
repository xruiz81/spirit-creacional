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
public abstract class _CarteraSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CarteraSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.CarteraIf addCartera(com.spirit.cartera.entity.CarteraIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CarteraEJB value = new CarteraEJB();
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
      value.setFechaCreacion(model.getFechaCreacion());
      value.setActivarRetrocompatibilidad(model.getActivarRetrocompatibilidad());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en cartera.", e);
			throw new GenericBusinessException(
					"Error al insertar información en cartera.");
      }
     
      return getCartera(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCartera(com.spirit.cartera.entity.CarteraIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CarteraEJB data = new CarteraEJB();
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
      data.setFechaCreacion(model.getFechaCreacion());
      data.setActivarRetrocompatibilidad(model.getActivarRetrocompatibilidad());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en cartera.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en cartera.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCartera(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CarteraEJB data = manager.find(CarteraEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en cartera.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en cartera.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.CarteraIf getCartera(java.lang.Long id) {
      return (CarteraEJB)queryManagerLocal.find(CarteraEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCarteraList() {
	  return queryManagerLocal.singleClassList(CarteraEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCarteraList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CarteraEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCarteraListSize() {
      Query countQuery = manager.createQuery("select count(*) from CarteraEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByTipo(java.lang.String tipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipo", tipo);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByTipodocumentoId(java.lang.Long tipodocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoId", tipodocumentoId);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByReferenciaId(java.lang.Long referenciaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referenciaId", referenciaId);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByClienteoficinaId(java.lang.Long clienteoficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteoficinaId", clienteoficinaId);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByPreimpreso(java.lang.String preimpreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("preimpreso", preimpreso);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByVendedorId(java.lang.Long vendedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("vendedorId", vendedorId);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByMonedaId(java.lang.Long monedaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("monedaId", monedaId);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByFechaEmision(java.sql.Timestamp fechaEmision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaEmision", fechaEmision);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraBySaldo(java.math.BigDecimal saldo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("saldo", saldo);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByFechaUltimaActualizacion(java.sql.Timestamp fechaUltimaActualizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaUltimaActualizacion", fechaUltimaActualizacion);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByComentario(java.lang.String comentario) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("comentario", comentario);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByAprobado(java.lang.String aprobado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("aprobado", aprobado);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByActivarRetrocompatibilidad(java.lang.String activarRetrocompatibilidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("activarRetrocompatibilidad", activarRetrocompatibilidad);
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CarteraIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CarteraEJB.class, aMap);      
    }

/////////////////
}
