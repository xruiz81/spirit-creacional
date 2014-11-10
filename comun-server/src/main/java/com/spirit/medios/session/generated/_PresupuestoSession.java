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
public abstract class _PresupuestoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PresupuestoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PresupuestoIf addPresupuesto(com.spirit.medios.entity.PresupuestoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PresupuestoEJB value = new PresupuestoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setOrdentrabajodetId(model.getOrdentrabajodetId());
      value.setClienteCondicionId(model.getClienteCondicionId());
      value.setPlanmedioId(model.getPlanmedioId());
      value.setConcepto(model.getConcepto());
      value.setModificacion(model.getModificacion());
      value.setFecha(model.getFecha());
      value.setFechaValidez(model.getFechaValidez());
      value.setFechaEnvio(model.getFechaEnvio());
      value.setFechaCancelacion(model.getFechaCancelacion());
      value.setFechaAceptacion(model.getFechaAceptacion());
      value.setValorbruto(model.getValorbruto());
      value.setDescuento(model.getDescuento());
      value.setValor(model.getValor());
      value.setIva(model.getIva());
      value.setCabecera(model.getCabecera());
      value.setEstado(model.getEstado());
      value.setFormaPagoId(model.getFormaPagoId());
      value.setDiasValidez(model.getDiasValidez());
      value.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
      value.setUsuarioCreacionId(model.getUsuarioCreacionId());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setUsuarioActualizacionId(model.getUsuarioActualizacionId());
      value.setFechaActualizacion(model.getFechaActualizacion());
      value.setTemaCampana(model.getTemaCampana());
      value.setAutorizacionSap(model.getAutorizacionSap());
      value.setDescuentosVarios(model.getDescuentosVarios());
      value.setDescuentoEspecial(model.getDescuentoEspecial());
      value.setClienteOficinaId(model.getClienteOficinaId());
      value.setPrepago(model.getPrepago());
      value.setReferenciaId(model.getReferenciaId());
      value.setTipoReferencia(model.getTipoReferencia());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en presupuesto.", e);
			throw new GenericBusinessException(
					"Error al insertar información en presupuesto.");
      }
     
      return getPresupuesto(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePresupuesto(com.spirit.medios.entity.PresupuestoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PresupuestoEJB data = new PresupuestoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setOrdentrabajodetId(model.getOrdentrabajodetId());
      data.setClienteCondicionId(model.getClienteCondicionId());
      data.setPlanmedioId(model.getPlanmedioId());
      data.setConcepto(model.getConcepto());
      data.setModificacion(model.getModificacion());
      data.setFecha(model.getFecha());
      data.setFechaValidez(model.getFechaValidez());
      data.setFechaEnvio(model.getFechaEnvio());
      data.setFechaCancelacion(model.getFechaCancelacion());
      data.setFechaAceptacion(model.getFechaAceptacion());
      data.setValorbruto(model.getValorbruto());
      data.setDescuento(model.getDescuento());
      data.setValor(model.getValor());
      data.setIva(model.getIva());
      data.setCabecera(model.getCabecera());
      data.setEstado(model.getEstado());
      data.setFormaPagoId(model.getFormaPagoId());
      data.setDiasValidez(model.getDiasValidez());
      data.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
      data.setUsuarioCreacionId(model.getUsuarioCreacionId());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setUsuarioActualizacionId(model.getUsuarioActualizacionId());
      data.setFechaActualizacion(model.getFechaActualizacion());
      data.setTemaCampana(model.getTemaCampana());
      data.setAutorizacionSap(model.getAutorizacionSap());
      data.setDescuentosVarios(model.getDescuentosVarios());
      data.setDescuentoEspecial(model.getDescuentoEspecial());
      data.setClienteOficinaId(model.getClienteOficinaId());
      data.setPrepago(model.getPrepago());
      data.setReferenciaId(model.getReferenciaId());
      data.setTipoReferencia(model.getTipoReferencia());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en presupuesto.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en presupuesto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePresupuesto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PresupuestoEJB data = manager.find(PresupuestoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en presupuesto.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en presupuesto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PresupuestoIf getPresupuesto(java.lang.Long id) {
      return (PresupuestoEJB)queryManagerLocal.find(PresupuestoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPresupuestoList() {
	  return queryManagerLocal.singleClassList(PresupuestoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPresupuestoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PresupuestoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPresupuestoListSize() {
      Query countQuery = manager.createQuery("select count(*) from PresupuestoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByOrdentrabajodetId(java.lang.Long ordentrabajodetId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordentrabajodetId", ordentrabajodetId);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByClienteCondicionId(java.lang.Long clienteCondicionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteCondicionId", clienteCondicionId);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByPlanmedioId(java.lang.Long planmedioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("planmedioId", planmedioId);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByConcepto(java.lang.String concepto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("concepto", concepto);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByModificacion(java.lang.Integer modificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("modificacion", modificacion);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByFecha(java.sql.Timestamp fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByFechaValidez(java.sql.Timestamp fechaValidez) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaValidez", fechaValidez);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByFechaEnvio(java.sql.Timestamp fechaEnvio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaEnvio", fechaEnvio);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByFechaCancelacion(java.sql.Timestamp fechaCancelacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCancelacion", fechaCancelacion);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByFechaAceptacion(java.sql.Timestamp fechaAceptacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaAceptacion", fechaAceptacion);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByValorbruto(java.math.BigDecimal valorbruto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorbruto", valorbruto);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByDescuento(java.math.BigDecimal descuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuento", descuento);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByIva(java.math.BigDecimal iva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("iva", iva);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByCabecera(java.lang.String cabecera) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cabecera", cabecera);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByFormaPagoId(java.lang.Long formaPagoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("formaPagoId", formaPagoId);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByDiasValidez(java.lang.Integer diasValidez) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("diasValidez", diasValidez);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeComisionAgencia", porcentajeComisionAgencia);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByUsuarioCreacionId(java.lang.Long usuarioCreacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioCreacionId", usuarioCreacionId);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioActualizacionId", usuarioActualizacionId);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByFechaActualizacion(java.sql.Timestamp fechaActualizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaActualizacion", fechaActualizacion);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByTemaCampana(java.lang.String temaCampana) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("temaCampana", temaCampana);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByAutorizacionSap(java.lang.String autorizacionSap) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizacionSap", autorizacionSap);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByDescuentosVarios(java.math.BigDecimal descuentosVarios) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuentosVarios", descuentosVarios);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByDescuentoEspecial(java.math.BigDecimal descuentoEspecial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuentoEspecial", descuentoEspecial);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByClienteOficinaId(java.lang.Long clienteOficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteOficinaId", clienteOficinaId);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByPrepago(java.lang.String prepago) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("prepago", prepago);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByReferenciaId(java.lang.Long referenciaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referenciaId", referenciaId);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByTipoReferencia(java.lang.String tipoReferencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoReferencia", tipoReferencia);
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PresupuestoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPresupuestoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PresupuestoEJB.class, aMap);      
    }

/////////////////
}
