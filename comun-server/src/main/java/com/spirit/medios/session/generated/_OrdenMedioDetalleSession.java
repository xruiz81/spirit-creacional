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
public abstract class _OrdenMedioDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_OrdenMedioDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.OrdenMedioDetalleIf addOrdenMedioDetalle(com.spirit.medios.entity.OrdenMedioDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      OrdenMedioDetalleEJB value = new OrdenMedioDetalleEJB();
      try {
      value.setId(model.getId());
      value.setOrdenMedioId(model.getOrdenMedioId());
      value.setComercialId(model.getComercialId());
      value.setPrograma(model.getPrograma());
      value.setHora(model.getHora());
      value.setComercial(model.getComercial());
      value.setValorTotal(model.getValorTotal());
      value.setPorcentajeDescuento(model.getPorcentajeDescuento());
      value.setValorIva(model.getValorIva());
      value.setValorTarifa(model.getValorTarifa());
      value.setValorSubtotal(model.getValorSubtotal());
      value.setValorDescuento(model.getValorDescuento());
      value.setPorcentajeDescuentoVenta(model.getPorcentajeDescuentoVenta());
      value.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
      value.setValorDescuentoVenta(model.getValorDescuentoVenta());
      value.setValorComisionAgencia(model.getValorComisionAgencia());
      value.setTotalCunias(model.getTotalCunias());
      value.setProductoProveedorId(model.getProductoProveedorId());
      value.setProductoClienteId(model.getProductoClienteId());
      value.setPauta(model.getPauta());
      value.setAuspicioDescripcion(model.getAuspicioDescripcion());
      value.setAuspicioPadre(model.getAuspicioPadre());
      value.setCampanaProductoVersionId(model.getCampanaProductoVersionId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en ordenMedioDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en ordenMedioDetalle.");
      }
     
      return getOrdenMedioDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveOrdenMedioDetalle(com.spirit.medios.entity.OrdenMedioDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      OrdenMedioDetalleEJB data = new OrdenMedioDetalleEJB();
      data.setId(model.getId());
      data.setOrdenMedioId(model.getOrdenMedioId());
      data.setComercialId(model.getComercialId());
      data.setPrograma(model.getPrograma());
      data.setHora(model.getHora());
      data.setComercial(model.getComercial());
      data.setValorTotal(model.getValorTotal());
      data.setPorcentajeDescuento(model.getPorcentajeDescuento());
      data.setValorIva(model.getValorIva());
      data.setValorTarifa(model.getValorTarifa());
      data.setValorSubtotal(model.getValorSubtotal());
      data.setValorDescuento(model.getValorDescuento());
      data.setPorcentajeDescuentoVenta(model.getPorcentajeDescuentoVenta());
      data.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
      data.setValorDescuentoVenta(model.getValorDescuentoVenta());
      data.setValorComisionAgencia(model.getValorComisionAgencia());
      data.setTotalCunias(model.getTotalCunias());
      data.setProductoProveedorId(model.getProductoProveedorId());
      data.setProductoClienteId(model.getProductoClienteId());
      data.setPauta(model.getPauta());
      data.setAuspicioDescripcion(model.getAuspicioDescripcion());
      data.setAuspicioPadre(model.getAuspicioPadre());
      data.setCampanaProductoVersionId(model.getCampanaProductoVersionId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en ordenMedioDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en ordenMedioDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteOrdenMedioDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      OrdenMedioDetalleEJB data = manager.find(OrdenMedioDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en ordenMedioDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en ordenMedioDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.OrdenMedioDetalleIf getOrdenMedioDetalle(java.lang.Long id) {
      return (OrdenMedioDetalleEJB)queryManagerLocal.find(OrdenMedioDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenMedioDetalleList() {
	  return queryManagerLocal.singleClassList(OrdenMedioDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getOrdenMedioDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(OrdenMedioDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getOrdenMedioDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from OrdenMedioDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByOrdenMedioId(java.lang.Long ordenMedioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordenMedioId", ordenMedioId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByComercialId(java.lang.Long comercialId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("comercialId", comercialId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByPrograma(java.lang.String programa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("programa", programa);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByHora(java.lang.String hora) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("hora", hora);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByComercial(java.lang.String comercial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("comercial", comercial);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByValorTotal(java.math.BigDecimal valorTotal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorTotal", valorTotal);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByPorcentajeDescuento(java.math.BigDecimal porcentajeDescuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuento", porcentajeDescuento);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByValorIva(java.math.BigDecimal valorIva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorIva", valorIva);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByValorTarifa(java.math.BigDecimal valorTarifa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorTarifa", valorTarifa);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByValorSubtotal(java.math.BigDecimal valorSubtotal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorSubtotal", valorSubtotal);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByValorDescuento(java.math.BigDecimal valorDescuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDescuento", valorDescuento);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByPorcentajeDescuentoVenta(java.math.BigDecimal porcentajeDescuentoVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDescuentoVenta", porcentajeDescuentoVenta);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeComisionAgencia", porcentajeComisionAgencia);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDescuentoVenta", valorDescuentoVenta);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByValorComisionAgencia(java.math.BigDecimal valorComisionAgencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorComisionAgencia", valorComisionAgencia);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByTotalCunias(java.lang.Integer totalCunias) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("totalCunias", totalCunias);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByProductoProveedorId(java.lang.Long productoProveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoProveedorId", productoProveedorId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByProductoClienteId(java.lang.Long productoClienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoClienteId", productoClienteId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByPauta(java.lang.String pauta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pauta", pauta);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByAuspicioDescripcion(java.lang.String auspicioDescripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("auspicioDescripcion", auspicioDescripcion);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByAuspicioPadre(java.lang.Long auspicioPadre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("auspicioPadre", auspicioPadre);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("campanaProductoVersionId", campanaProductoVersionId);
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of OrdenMedioDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findOrdenMedioDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(OrdenMedioDetalleEJB.class, aMap);      
    }

/////////////////
}
