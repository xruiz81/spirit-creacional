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
public abstract class _VentasConsolidadoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_VentasConsolidadoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.VentasConsolidadoIf addVentasConsolidado(com.spirit.pos.entity.VentasConsolidadoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      VentasConsolidadoEJB value = new VentasConsolidadoEJB();
      try {
      value.setId(model.getId());
      value.setFechaCierre(model.getFechaCierre());
      value.setCajeroNombre(model.getCajeroNombre());
      value.setCajeroCedula(model.getCajeroCedula());
      value.setCajaCodigo(model.getCajaCodigo());
      value.setCajaNombre(model.getCajaNombre());
      value.setValorEfectivo(model.getValorEfectivo());
      value.setValorTarjeta(model.getValorTarjeta());
      value.setValorGiftcards(model.getValorGiftcards());
      value.setValorDevoluciones(model.getValorDevoluciones());
      value.setValorCajaInicial(model.getValorCajaInicial());
      value.setValorCheque(model.getValorCheque());
      value.setValorDonacion(model.getValorDonacion());
      value.setValorCredito(model.getValorCredito());
      value.setValorCajaIngreso(model.getValorCajaIngreso());
      value.setValorCajaEgreso(model.getValorCajaEgreso());
      value.setValorDescuadre(model.getValorDescuadre());
      value.setValorMultas(model.getValorMultas());
      value.setValorDocumentos(model.getValorDocumentos());
      value.setFechaApertura(model.getFechaApertura());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en ventasConsolidado.", e);
			throw new GenericBusinessException(
					"Error al insertar información en ventasConsolidado.");
      }
     
      return getVentasConsolidado(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveVentasConsolidado(com.spirit.pos.entity.VentasConsolidadoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      VentasConsolidadoEJB data = new VentasConsolidadoEJB();
      data.setId(model.getId());
      data.setFechaCierre(model.getFechaCierre());
      data.setCajeroNombre(model.getCajeroNombre());
      data.setCajeroCedula(model.getCajeroCedula());
      data.setCajaCodigo(model.getCajaCodigo());
      data.setCajaNombre(model.getCajaNombre());
      data.setValorEfectivo(model.getValorEfectivo());
      data.setValorTarjeta(model.getValorTarjeta());
      data.setValorGiftcards(model.getValorGiftcards());
      data.setValorDevoluciones(model.getValorDevoluciones());
      data.setValorCajaInicial(model.getValorCajaInicial());
      data.setValorCheque(model.getValorCheque());
      data.setValorDonacion(model.getValorDonacion());
      data.setValorCredito(model.getValorCredito());
      data.setValorCajaIngreso(model.getValorCajaIngreso());
      data.setValorCajaEgreso(model.getValorCajaEgreso());
      data.setValorDescuadre(model.getValorDescuadre());
      data.setValorMultas(model.getValorMultas());
      data.setValorDocumentos(model.getValorDocumentos());
      data.setFechaApertura(model.getFechaApertura());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en ventasConsolidado.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en ventasConsolidado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteVentasConsolidado(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      VentasConsolidadoEJB data = manager.find(VentasConsolidadoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en ventasConsolidado.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en ventasConsolidado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.VentasConsolidadoIf getVentasConsolidado(java.lang.Long id) {
      return (VentasConsolidadoEJB)queryManagerLocal.find(VentasConsolidadoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getVentasConsolidadoList() {
	  return queryManagerLocal.singleClassList(VentasConsolidadoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getVentasConsolidadoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(VentasConsolidadoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getVentasConsolidadoListSize() {
      Query countQuery = manager.createQuery("select count(*) from VentasConsolidadoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByFechaCierre(java.sql.Timestamp fechaCierre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCierre", fechaCierre);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByCajeroNombre(java.lang.String cajeroNombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cajeroNombre", cajeroNombre);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByCajeroCedula(java.lang.String cajeroCedula) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cajeroCedula", cajeroCedula);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByCajaCodigo(java.lang.String cajaCodigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cajaCodigo", cajaCodigo);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByCajaNombre(java.lang.String cajaNombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cajaNombre", cajaNombre);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByValorEfectivo(java.math.BigDecimal valorEfectivo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorEfectivo", valorEfectivo);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByValorTarjeta(java.math.BigDecimal valorTarjeta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorTarjeta", valorTarjeta);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByValorGiftcards(java.math.BigDecimal valorGiftcards) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorGiftcards", valorGiftcards);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByValorDevoluciones(java.math.BigDecimal valorDevoluciones) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDevoluciones", valorDevoluciones);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByValorCajaInicial(java.math.BigDecimal valorCajaInicial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorCajaInicial", valorCajaInicial);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByValorCheque(java.math.BigDecimal valorCheque) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorCheque", valorCheque);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByValorDonacion(java.math.BigDecimal valorDonacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDonacion", valorDonacion);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByValorCredito(java.math.BigDecimal valorCredito) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorCredito", valorCredito);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByValorCajaIngreso(java.math.BigDecimal valorCajaIngreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorCajaIngreso", valorCajaIngreso);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByValorCajaEgreso(java.math.BigDecimal valorCajaEgreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorCajaEgreso", valorCajaEgreso);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByValorDescuadre(java.math.BigDecimal valorDescuadre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDescuadre", valorDescuadre);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByValorMultas(java.math.BigDecimal valorMultas) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorMultas", valorMultas);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByValorDocumentos(java.math.BigDecimal valorDocumentos) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDocumentos", valorDocumentos);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByFechaApertura(java.sql.Timestamp fechaApertura) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaApertura", fechaApertura);
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of VentasConsolidadoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasConsolidadoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(VentasConsolidadoEJB.class, aMap);      
    }

/////////////////
}
