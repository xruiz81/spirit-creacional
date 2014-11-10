package com.spirit.facturacion.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.facturacion.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _PedidoEnvioSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PedidoEnvioSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PedidoEnvioIf addPedidoEnvio(com.spirit.facturacion.entity.PedidoEnvioIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PedidoEnvioEJB value = new PedidoEnvioEJB();
      try {
      value.setId(model.getId());
      value.setPedidoId(model.getPedidoId());
      value.setMetodoEnvio(model.getMetodoEnvio());
      value.setValorEnvio(model.getValorEnvio());
      value.setNombresFacturacion(model.getNombresFacturacion());
      value.setApellidosFacturacion(model.getApellidosFacturacion());
      value.setDireccionFacturacion(model.getDireccionFacturacion());
      value.setCiudadFacturacion(model.getCiudadFacturacion());
      value.setRegionFacturacion(model.getRegionFacturacion());
      value.setZipFacturacion(model.getZipFacturacion());
      value.setCodigoPaisFacturacion(model.getCodigoPaisFacturacion());
      value.setTelefonoFacturacion(model.getTelefonoFacturacion());
      value.setCelularFacturacion(model.getCelularFacturacion());
      value.setNombresEnvio(model.getNombresEnvio());
      value.setApellidosEnvio(model.getApellidosEnvio());
      value.setDireccionEnvio(model.getDireccionEnvio());
      value.setCiudadEnvio(model.getCiudadEnvio());
      value.setRegionEnvio(model.getRegionEnvio());
      value.setZipEnvio(model.getZipEnvio());
      value.setCodigoPaisEnvio(model.getCodigoPaisEnvio());
      value.setTelefonoEnvio(model.getTelefonoEnvio());
      value.setCelularEnvio(model.getCelularEnvio());
      value.setCorreoCliente(model.getCorreoCliente());
      value.setNombresCliente(model.getNombresCliente());
      value.setApellidosCliente(model.getApellidosCliente());
      value.setNombreEmpresaFacturacion(model.getNombreEmpresaFacturacion());
      value.setNombreEmpresaEnvio(model.getNombreEmpresaEnvio());
      value.setOrdenExternaId(model.getOrdenExternaId());
      value.setFechaActualizacion(model.getFechaActualizacion());
      value.setIdentificacionFundacion(model.getIdentificacionFundacion());
      value.setValorDescuento(model.getValorDescuento());
      value.setValorSubtotal(model.getValorSubtotal());
      value.setValorTotal(model.getValorTotal());
      value.setValorImpuesto(model.getValorImpuesto());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en pedidoEnvio.", e);
			throw new GenericBusinessException(
					"Error al insertar información en pedidoEnvio.");
      }
     
      return getPedidoEnvio(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePedidoEnvio(com.spirit.facturacion.entity.PedidoEnvioIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PedidoEnvioEJB data = new PedidoEnvioEJB();
      data.setId(model.getId());
      data.setPedidoId(model.getPedidoId());
      data.setMetodoEnvio(model.getMetodoEnvio());
      data.setValorEnvio(model.getValorEnvio());
      data.setNombresFacturacion(model.getNombresFacturacion());
      data.setApellidosFacturacion(model.getApellidosFacturacion());
      data.setDireccionFacturacion(model.getDireccionFacturacion());
      data.setCiudadFacturacion(model.getCiudadFacturacion());
      data.setRegionFacturacion(model.getRegionFacturacion());
      data.setZipFacturacion(model.getZipFacturacion());
      data.setCodigoPaisFacturacion(model.getCodigoPaisFacturacion());
      data.setTelefonoFacturacion(model.getTelefonoFacturacion());
      data.setCelularFacturacion(model.getCelularFacturacion());
      data.setNombresEnvio(model.getNombresEnvio());
      data.setApellidosEnvio(model.getApellidosEnvio());
      data.setDireccionEnvio(model.getDireccionEnvio());
      data.setCiudadEnvio(model.getCiudadEnvio());
      data.setRegionEnvio(model.getRegionEnvio());
      data.setZipEnvio(model.getZipEnvio());
      data.setCodigoPaisEnvio(model.getCodigoPaisEnvio());
      data.setTelefonoEnvio(model.getTelefonoEnvio());
      data.setCelularEnvio(model.getCelularEnvio());
      data.setCorreoCliente(model.getCorreoCliente());
      data.setNombresCliente(model.getNombresCliente());
      data.setApellidosCliente(model.getApellidosCliente());
      data.setNombreEmpresaFacturacion(model.getNombreEmpresaFacturacion());
      data.setNombreEmpresaEnvio(model.getNombreEmpresaEnvio());
      data.setOrdenExternaId(model.getOrdenExternaId());
      data.setFechaActualizacion(model.getFechaActualizacion());
      data.setIdentificacionFundacion(model.getIdentificacionFundacion());
      data.setValorDescuento(model.getValorDescuento());
      data.setValorSubtotal(model.getValorSubtotal());
      data.setValorTotal(model.getValorTotal());
      data.setValorImpuesto(model.getValorImpuesto());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en pedidoEnvio.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en pedidoEnvio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePedidoEnvio(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PedidoEnvioEJB data = manager.find(PedidoEnvioEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en pedidoEnvio.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en pedidoEnvio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PedidoEnvioIf getPedidoEnvio(java.lang.Long id) {
      return (PedidoEnvioEJB)queryManagerLocal.find(PedidoEnvioEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPedidoEnvioList() {
	  return queryManagerLocal.singleClassList(PedidoEnvioEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPedidoEnvioList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PedidoEnvioEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPedidoEnvioListSize() {
      Query countQuery = manager.createQuery("select count(*) from PedidoEnvioEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByPedidoId(java.lang.Long pedidoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pedidoId", pedidoId);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByMetodoEnvio(java.lang.String metodoEnvio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("metodoEnvio", metodoEnvio);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByValorEnvio(java.math.BigDecimal valorEnvio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorEnvio", valorEnvio);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByNombresFacturacion(java.lang.String nombresFacturacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombresFacturacion", nombresFacturacion);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByApellidosFacturacion(java.lang.String apellidosFacturacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("apellidosFacturacion", apellidosFacturacion);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByDireccionFacturacion(java.lang.String direccionFacturacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("direccionFacturacion", direccionFacturacion);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByCiudadFacturacion(java.lang.String ciudadFacturacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ciudadFacturacion", ciudadFacturacion);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByRegionFacturacion(java.lang.String regionFacturacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("regionFacturacion", regionFacturacion);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByZipFacturacion(java.lang.String zipFacturacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("zipFacturacion", zipFacturacion);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByCodigoPaisFacturacion(java.lang.String codigoPaisFacturacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigoPaisFacturacion", codigoPaisFacturacion);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByTelefonoFacturacion(java.lang.String telefonoFacturacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("telefonoFacturacion", telefonoFacturacion);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByCelularFacturacion(java.lang.String celularFacturacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("celularFacturacion", celularFacturacion);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByNombresEnvio(java.lang.String nombresEnvio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombresEnvio", nombresEnvio);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByApellidosEnvio(java.lang.String apellidosEnvio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("apellidosEnvio", apellidosEnvio);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByDireccionEnvio(java.lang.String direccionEnvio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("direccionEnvio", direccionEnvio);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByCiudadEnvio(java.lang.String ciudadEnvio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ciudadEnvio", ciudadEnvio);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByRegionEnvio(java.lang.String regionEnvio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("regionEnvio", regionEnvio);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByZipEnvio(java.lang.String zipEnvio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("zipEnvio", zipEnvio);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByCodigoPaisEnvio(java.lang.String codigoPaisEnvio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigoPaisEnvio", codigoPaisEnvio);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByTelefonoEnvio(java.lang.String telefonoEnvio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("telefonoEnvio", telefonoEnvio);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByCelularEnvio(java.lang.String celularEnvio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("celularEnvio", celularEnvio);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByCorreoCliente(java.lang.String correoCliente) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("correoCliente", correoCliente);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByNombresCliente(java.lang.String nombresCliente) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombresCliente", nombresCliente);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByApellidosCliente(java.lang.String apellidosCliente) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("apellidosCliente", apellidosCliente);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByNombreEmpresaFacturacion(java.lang.String nombreEmpresaFacturacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombreEmpresaFacturacion", nombreEmpresaFacturacion);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByNombreEmpresaEnvio(java.lang.String nombreEmpresaEnvio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombreEmpresaEnvio", nombreEmpresaEnvio);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByOrdenExternaId(java.lang.String ordenExternaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordenExternaId", ordenExternaId);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByFechaActualizacion(java.lang.String fechaActualizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaActualizacion", fechaActualizacion);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByIdentificacionFundacion(java.lang.String identificacionFundacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("identificacionFundacion", identificacionFundacion);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByValorDescuento(java.math.BigDecimal valorDescuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDescuento", valorDescuento);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByValorSubtotal(java.math.BigDecimal valorSubtotal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorSubtotal", valorSubtotal);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByValorTotal(java.math.BigDecimal valorTotal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorTotal", valorTotal);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByValorImpuesto(java.math.BigDecimal valorImpuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorImpuesto", valorImpuesto);
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PedidoEnvioIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPedidoEnvioByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PedidoEnvioEJB.class, aMap);      
    }

/////////////////
}
