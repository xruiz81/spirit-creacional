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
public abstract class _CarteraAfectaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CarteraAfectaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.CarteraAfectaIf addCarteraAfecta(com.spirit.cartera.entity.CarteraAfectaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CarteraAfectaEJB value = new CarteraAfectaEJB();
      try {
      value.setId(model.getId());
      value.setCarteradetalleId(model.getCarteradetalleId());
      value.setCarteraafectaId(model.getCarteraafectaId());
      value.setUsuarioId(model.getUsuarioId());
      value.setValor(model.getValor());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setFechaAplicacion(model.getFechaAplicacion());
      value.setCartera(model.getCartera());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en carteraAfecta.", e);
			throw new GenericBusinessException(
					"Error al insertar información en carteraAfecta.");
      }
     
      return getCarteraAfecta(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCarteraAfecta(com.spirit.cartera.entity.CarteraAfectaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CarteraAfectaEJB data = new CarteraAfectaEJB();
      data.setId(model.getId());
      data.setCarteradetalleId(model.getCarteradetalleId());
      data.setCarteraafectaId(model.getCarteraafectaId());
      data.setUsuarioId(model.getUsuarioId());
      data.setValor(model.getValor());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setFechaAplicacion(model.getFechaAplicacion());
      data.setCartera(model.getCartera());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en carteraAfecta.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en carteraAfecta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCarteraAfecta(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CarteraAfectaEJB data = manager.find(CarteraAfectaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en carteraAfecta.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en carteraAfecta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.CarteraAfectaIf getCarteraAfecta(java.lang.Long id) {
      return (CarteraAfectaEJB)queryManagerLocal.find(CarteraAfectaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCarteraAfectaList() {
	  return queryManagerLocal.singleClassList(CarteraAfectaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCarteraAfectaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CarteraAfectaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCarteraAfectaListSize() {
      Query countQuery = manager.createQuery("select count(*) from CarteraAfectaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraAfectaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CarteraAfectaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraAfectaByCarteradetalleId(java.lang.Long carteradetalleId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("carteradetalleId", carteradetalleId);
		return queryManagerLocal.singleClassQueryList(CarteraAfectaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraAfectaByCarteraafectaId(java.lang.Long carteraafectaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("carteraafectaId", carteraafectaId);
		return queryManagerLocal.singleClassQueryList(CarteraAfectaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraAfectaByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(CarteraAfectaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraAfectaByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(CarteraAfectaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraAfectaByFechaCreacion(java.sql.Date fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(CarteraAfectaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraAfectaByFechaAplicacion(java.sql.Date fechaAplicacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaAplicacion", fechaAplicacion);
		return queryManagerLocal.singleClassQueryList(CarteraAfectaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraAfectaByCartera(java.lang.String cartera) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cartera", cartera);
		return queryManagerLocal.singleClassQueryList(CarteraAfectaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CarteraAfectaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCarteraAfectaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CarteraAfectaEJB.class, aMap);      
    }

/////////////////
}
