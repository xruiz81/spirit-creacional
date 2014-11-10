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
public abstract class _PlanMedioDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PlanMedioDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PlanMedioDetalleIf addPlanMedioDetalle(com.spirit.medios.entity.PlanMedioDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PlanMedioDetalleEJB value = new PlanMedioDetalleEJB();
      try {
      value.setId(model.getId());
      value.setPlanMedioMesId(model.getPlanMedioMesId());
      value.setPrograma(model.getPrograma());
      value.setGeneroProgramaId(model.getGeneroProgramaId());
      value.setComercialId(model.getComercialId());
      value.setProveedorId(model.getProveedorId());
      value.setHoraInicio(model.getHoraInicio());
      value.setRaiting1(model.getRaiting1());
      value.setRaiting2(model.getRaiting2());
      value.setRaitingPonderado(model.getRaitingPonderado());
      value.setAudiencia(model.getAudiencia());
      value.setValorTarifa(model.getValorTarifa());
      value.setValorTotal(model.getValorTotal());
      value.setSeccion(model.getSeccion());
      value.setUbicacion(model.getUbicacion());
      value.setTamanio(model.getTamanio());
      value.setColor(model.getColor());
      value.setTotalCunias(model.getTotalCunias());
      value.setComercial(model.getComercial());
      value.setValorDescuento(model.getValorDescuento());
      value.setProductoProveedorId(model.getProductoProveedorId());
      value.setValorSubtotal(model.getValorSubtotal());
      value.setPorcentajeDescuento(model.getPorcentajeDescuento());
      value.setValorIva(model.getValorIva());
      value.setValorDescuentoVenta(model.getValorDescuentoVenta());
      value.setValorComisionAgencia(model.getValorComisionAgencia());
      value.setPorcentajeDescuentoVenta(model.getPorcentajeDescuentoVenta());
      value.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
      value.setProductoClienteId(model.getProductoClienteId());
      value.setPauta(model.getPauta());
      value.setAuspicioDescripcion(model.getAuspicioDescripcion());
      value.setAuspicioPadre(model.getAuspicioPadre());
      value.setCampanaProductoVersionId(model.getCampanaProductoVersionId());
      value.setPorcentajeBonificacionCompra(model.getPorcentajeBonificacionCompra());
      value.setPorcentajeBonificacionVenta(model.getPorcentajeBonificacionVenta());
      value.setNegociacionComision(model.getNegociacionComision());
      value.setNumeroOrdenAgrupacion(model.getNumeroOrdenAgrupacion());
      value.setVersion(model.getVersion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en planMedioDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en planMedioDetalle.");
      }
     
      return getPlanMedioDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePlanMedioDetalle(com.spirit.medios.entity.PlanMedioDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PlanMedioDetalleEJB data = new PlanMedioDetalleEJB();
      data.setId(model.getId());
      data.setPlanMedioMesId(model.getPlanMedioMesId());
      data.setPrograma(model.getPrograma());
      data.setGeneroProgramaId(model.getGeneroProgramaId());
      data.setComercialId(model.getComercialId());
      data.setProveedorId(model.getProveedorId());
      data.setHoraInicio(model.getHoraInicio());
      data.setRaiting1(model.getRaiting1());
      data.setRaiting2(model.getRaiting2());
      data.setRaitingPonderado(model.getRaitingPonderado());
      data.setAudiencia(model.getAudiencia());
      data.setValorTarifa(model.getValorTarifa());
      data.setValorTotal(model.getValorTotal());
      data.setSeccion(model.getSeccion());
      data.setUbicacion(model.getUbicacion());
      data.setTamanio(model.getTamanio());
      data.setColor(model.getColor());
      data.setTotalCunias(model.getTotalCunias());
      data.setComercial(model.getComercial());
      data.setValorDescuento(model.getValorDescuento());
      data.setProductoProveedorId(model.getProductoProveedorId());
      data.setValorSubtotal(model.getValorSubtotal());
      data.setPorcentajeDescuento(model.getPorcentajeDescuento());
      data.setValorIva(model.getValorIva());
      data.setValorDescuentoVenta(model.getValorDescuentoVenta());
      data.setValorComisionAgencia(model.getValorComisionAgencia());
      data.setPorcentajeDescuentoVenta(model.getPorcentajeDescuentoVenta());
      data.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
      data.setProductoClienteId(model.getProductoClienteId());
      data.setPauta(model.getPauta());
      data.setAuspicioDescripcion(model.getAuspicioDescripcion());
      data.setAuspicioPadre(model.getAuspicioPadre());
      data.setCampanaProductoVersionId(model.getCampanaProductoVersionId());
      data.setPorcentajeBonificacionCompra(model.getPorcentajeBonificacionCompra());
      data.setPorcentajeBonificacionVenta(model.getPorcentajeBonificacionVenta());
      data.setNegociacionComision(model.getNegociacionComision());
      data.setNumeroOrdenAgrupacion(model.getNumeroOrdenAgrupacion());
      data.setVersion(model.getVersion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en planMedioDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en planMedioDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePlanMedioDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PlanMedioDetalleEJB data = manager.find(PlanMedioDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en planMedioDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en planMedioDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PlanMedioDetalleIf getPlanMedioDetalle(java.lang.Long id) {
      return (PlanMedioDetalleEJB)queryManagerLocal.find(PlanMedioDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlanMedioDetalleList() {
	  return queryManagerLocal.singleClassList(PlanMedioDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlanMedioDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PlanMedioDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPlanMedioDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from PlanMedioDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByPlanMedioMesId(java.lang.Long planMedioMesId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("planMedioMesId", planMedioMesId);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByPrograma(java.lang.String programa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("programa", programa);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByGeneroProgramaId(java.lang.Long generoProgramaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("generoProgramaId", generoProgramaId);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByComercialId(java.lang.Long comercialId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("comercialId", comercialId);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByProveedorId(java.lang.Long proveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("proveedorId", proveedorId);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByHoraInicio(java.lang.String horaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("horaInicio", horaInicio);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByRaiting1(java.math.BigDecimal raiting1) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("raiting1", raiting1);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByRaiting2(java.math.BigDecimal raiting2) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("raiting2", raiting2);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByRaitingPonderado(java.math.BigDecimal raitingPonderado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("raitingPonderado", raitingPonderado);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByAudiencia(java.math.BigDecimal audiencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("audiencia", audiencia);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByValorTarifa(java.math.BigDecimal valorTarifa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorTarifa", valorTarifa);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByValorTotal(java.math.BigDecimal valorTotal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorTotal", valorTotal);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleBySeccion(java.lang.String seccion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("seccion", seccion);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByUbicacion(java.lang.String ubicacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ubicacion", ubicacion);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByTamanio(java.lang.String tamanio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tamanio", tamanio);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByColor(java.lang.String color) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("color", color);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByTotalCunias(java.lang.Integer totalCunias) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("totalCunias", totalCunias);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByComercial(java.lang.String comercial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("comercial", comercial);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByValorDescuento(java.math.BigDecimal valorDescuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDescuento", valorDescuento);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByProductoProveedorId(java.lang.Long productoProveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoProveedorId", productoProveedorId);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByValorSubtotal(java.math.BigDecimal valorSubtotal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorSubtotal", valorSubtotal);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByPorcentajeDescuento(java.math.BigDecimal porcentajeDescuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuento", porcentajeDescuento);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByValorIva(java.math.BigDecimal valorIva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorIva", valorIva);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDescuentoVenta", valorDescuentoVenta);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorComisionAgencia", valorComisionAgencia);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByPorcentajeDescuentoVenta(java.math.BigDecimal porcentajeDescuentoVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentoVenta", porcentajeDescuentoVenta);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeComisionAgencia", porcentajeComisionAgencia);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByProductoClienteId(java.lang.Long productoClienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoClienteId", productoClienteId);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByPauta(java.lang.String pauta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pauta", pauta);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByAuspicioDescripcion(java.lang.String auspicioDescripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("auspicioDescripcion", auspicioDescripcion);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByAuspicioPadre(java.lang.Long auspicioPadre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("auspicioPadre", auspicioPadre);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("campanaProductoVersionId", campanaProductoVersionId);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByPorcentajeBonificacionCompra(java.math.BigDecimal porcentajeBonificacionCompra) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeBonificacionCompra", porcentajeBonificacionCompra);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByPorcentajeBonificacionVenta(java.math.BigDecimal porcentajeBonificacionVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeBonificacionVenta", porcentajeBonificacionVenta);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByNegociacionComision(java.lang.String negociacionComision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("negociacionComision", negociacionComision);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByNumeroOrdenAgrupacion(java.lang.Integer numeroOrdenAgrupacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numeroOrdenAgrupacion", numeroOrdenAgrupacion);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByVersion(java.lang.String version) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("version", version);
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PlanMedioDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlanMedioDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PlanMedioDetalleEJB.class, aMap);      
    }

/////////////////
}
