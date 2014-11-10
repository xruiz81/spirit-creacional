package com.spirit.nomina.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.nomina.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ContratoPlantillaDetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ContratoPlantillaDetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.ContratoPlantillaDetalleIf addContratoPlantillaDetalle(com.spirit.nomina.entity.ContratoPlantillaDetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ContratoPlantillaDetalleEJB value = new ContratoPlantillaDetalleEJB();
      try {
      value.setId(model.getId());
      value.setContratoPlantillaId(model.getContratoPlantillaId());
      value.setRubroId(model.getRubroId());
      value.setValor(model.getValor());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en contratoPlantillaDetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en contratoPlantillaDetalle.");
      }
     
      return getContratoPlantillaDetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveContratoPlantillaDetalle(com.spirit.nomina.entity.ContratoPlantillaDetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ContratoPlantillaDetalleEJB data = new ContratoPlantillaDetalleEJB();
      data.setId(model.getId());
      data.setContratoPlantillaId(model.getContratoPlantillaId());
      data.setRubroId(model.getRubroId());
      data.setValor(model.getValor());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en contratoPlantillaDetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en contratoPlantillaDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteContratoPlantillaDetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ContratoPlantillaDetalleEJB data = manager.find(ContratoPlantillaDetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en contratoPlantillaDetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en contratoPlantillaDetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.ContratoPlantillaDetalleIf getContratoPlantillaDetalle(java.lang.Long id) {
      return (ContratoPlantillaDetalleEJB)queryManagerLocal.find(ContratoPlantillaDetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getContratoPlantillaDetalleList() {
	  return queryManagerLocal.singleClassList(ContratoPlantillaDetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getContratoPlantillaDetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ContratoPlantillaDetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getContratoPlantillaDetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from ContratoPlantillaDetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoPlantillaDetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ContratoPlantillaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoPlantillaDetalleByContratoPlantillaId(java.lang.Long contratoPlantillaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("contratoPlantillaId", contratoPlantillaId);
		return queryManagerLocal.singleClassQueryList(ContratoPlantillaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoPlantillaDetalleByRubroId(java.lang.Long rubroId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rubroId", rubroId);
		return queryManagerLocal.singleClassQueryList(ContratoPlantillaDetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoPlantillaDetalleByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(ContratoPlantillaDetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ContratoPlantillaDetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoPlantillaDetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ContratoPlantillaDetalleEJB.class, aMap);      
    }

/////////////////
}
