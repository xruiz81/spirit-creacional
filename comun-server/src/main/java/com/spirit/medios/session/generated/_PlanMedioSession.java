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
public abstract class _PlanMedioSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PlanMedioSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PlanMedioIf addPlanMedio(com.spirit.medios.entity.PlanMedioIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PlanMedioEJB value = new PlanMedioEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setConcepto(model.getConcepto());
      value.setOrdenTrabajoDetalleId(model.getOrdenTrabajoDetalleId());
      value.setGrupoObjetivoId(model.getGrupoObjetivoId());
      value.setTipoProveedorId(model.getTipoProveedorId());
      value.setUsuarioId(model.getUsuarioId());
      value.setModificacion(model.getModificacion());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      value.setValorSubtotal(model.getValorSubtotal());
      value.setValorDescuento(model.getValorDescuento());
      value.setPlanMedioOrigenId(model.getPlanMedioOrigenId());
      value.setCiudad1(model.getCiudad1());
      value.setCiudad2(model.getCiudad2());
      value.setCiudad3(model.getCiudad3());
      value.setEstado(model.getEstado());
      value.setCobertura(model.getCobertura());
      value.setConsideraciones(model.getConsideraciones());
      value.setValorIva(model.getValorIva());
      value.setUsuarioActualizacionId(model.getUsuarioActualizacionId());
      value.setFechaActualizacion(model.getFechaActualizacion());
      value.setPorcentajeDescuento(model.getPorcentajeDescuento());
      value.setValorTotal(model.getValorTotal());
      value.setValorDescuentoVenta(model.getValorDescuentoVenta());
      value.setValorComisionAgencia(model.getValorComisionAgencia());
      value.setPlanMedioHermanoId(model.getPlanMedioHermanoId());
      value.setOrdenMedioTipo(model.getOrdenMedioTipo());
      value.setPlanMedioTipo(model.getPlanMedioTipo());
      value.setAutorizacionSap(model.getAutorizacionSap());
      value.setFechaAprobacion(model.getFechaAprobacion());
      value.setRevision(model.getRevision());
      value.setPrepago(model.getPrepago());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en planMedio.", e);
			throw new GenericBusinessException(
					"Error al insertar información en planMedio.");
      }
     
      return getPlanMedio(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePlanMedio(com.spirit.medios.entity.PlanMedioIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PlanMedioEJB data = new PlanMedioEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setConcepto(model.getConcepto());
      data.setOrdenTrabajoDetalleId(model.getOrdenTrabajoDetalleId());
      data.setGrupoObjetivoId(model.getGrupoObjetivoId());
      data.setTipoProveedorId(model.getTipoProveedorId());
      data.setUsuarioId(model.getUsuarioId());
      data.setModificacion(model.getModificacion());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
      data.setValorSubtotal(model.getValorSubtotal());
      data.setValorDescuento(model.getValorDescuento());
      data.setPlanMedioOrigenId(model.getPlanMedioOrigenId());
      data.setCiudad1(model.getCiudad1());
      data.setCiudad2(model.getCiudad2());
      data.setCiudad3(model.getCiudad3());
      data.setEstado(model.getEstado());
      data.setCobertura(model.getCobertura());
      data.setConsideraciones(model.getConsideraciones());
      data.setValorIva(model.getValorIva());
      data.setUsuarioActualizacionId(model.getUsuarioActualizacionId());
      data.setFechaActualizacion(model.getFechaActualizacion());
      data.setPorcentajeDescuento(model.getPorcentajeDescuento());
      data.setValorTotal(model.getValorTotal());
      data.setValorDescuentoVenta(model.getValorDescuentoVenta());
      data.setValorComisionAgencia(model.getValorComisionAgencia());
      data.setPlanMedioHermanoId(model.getPlanMedioHermanoId());
      data.setOrdenMedioTipo(model.getOrdenMedioTipo());
      data.setPlanMedioTipo(model.getPlanMedioTipo());
      data.setAutorizacionSap(model.getAutorizacionSap());
      data.setFechaAprobacion(model.getFechaAprobacion());
      data.setRevision(model.getRevision());
      data.setPrepago(model.getPrepago());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en planMedio.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en planMedio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePlanMedio(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PlanMedioEJB data = manager.find(PlanMedioEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en planMedio.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en planMedio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PlanMedioIf getPlanMedio(java.lang.Long id) {
      return (PlanMedioEJB)queryManagerLocal.find(PlanMedioEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlanMedioList() {
	  return queryManagerLocal.singleClassList(PlanMedioEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlanMedioList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PlanMedioEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPlanMedioListSize() {
      Query countQuery = manager.createQuery("select count(*) from PlanMedioEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByConcepto(java.lang.String concepto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("concepto", concepto);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByOrdenTrabajoDetalleId(java.lang.Long ordenTrabajoDetalleId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordenTrabajoDetalleId", ordenTrabajoDetalleId);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByGrupoObjetivoId(java.lang.Long grupoObjetivoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("grupoObjetivoId", grupoObjetivoId);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByTipoProveedorId(java.lang.Long tipoProveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoProveedorId", tipoProveedorId);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByModificacion(java.lang.Integer modificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("modificacion", modificacion);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByFechaInicio(java.sql.Timestamp fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByFechaFin(java.sql.Timestamp fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByValorSubtotal(java.math.BigDecimal valorSubtotal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorSubtotal", valorSubtotal);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByValorDescuento(java.math.BigDecimal valorDescuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDescuento", valorDescuento);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByPlanMedioOrigenId(java.lang.Long planMedioOrigenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("planMedioOrigenId", planMedioOrigenId);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByCiudad1(java.math.BigDecimal ciudad1) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ciudad1", ciudad1);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByCiudad2(java.math.BigDecimal ciudad2) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ciudad2", ciudad2);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByCiudad3(java.math.BigDecimal ciudad3) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ciudad3", ciudad3);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByCobertura(java.lang.String cobertura) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cobertura", cobertura);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByConsideraciones(java.lang.String consideraciones) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("consideraciones", consideraciones);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByValorIva(java.math.BigDecimal valorIva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorIva", valorIva);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioActualizacionId", usuarioActualizacionId);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByFechaActualizacion(java.sql.Timestamp fechaActualizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaActualizacion", fechaActualizacion);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByPorcentajeDescuento(java.math.BigDecimal porcentajeDescuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuento", porcentajeDescuento);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByValorTotal(java.math.BigDecimal valorTotal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorTotal", valorTotal);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDescuentoVenta", valorDescuentoVenta);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorComisionAgencia", valorComisionAgencia);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByPlanMedioHermanoId(java.lang.Long planMedioHermanoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("planMedioHermanoId", planMedioHermanoId);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByOrdenMedioTipo(java.lang.String ordenMedioTipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordenMedioTipo", ordenMedioTipo);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByPlanMedioTipo(java.lang.String planMedioTipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("planMedioTipo", planMedioTipo);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByAutorizacionSap(java.lang.String autorizacionSap) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("autorizacionSap", autorizacionSap);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByFechaAprobacion(java.sql.Timestamp fechaAprobacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaAprobacion", fechaAprobacion);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByRevision(java.lang.String revision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("revision", revision);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByPrepago(java.lang.String prepago) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("prepago", prepago);
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PlanMedioIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PlanMedioEJB.class, aMap);      
    }

/////////////////
}
