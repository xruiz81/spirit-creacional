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
public abstract class _ContratoPlantillaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ContratoPlantillaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.ContratoPlantillaIf addContratoPlantilla(com.spirit.nomina.entity.ContratoPlantillaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ContratoPlantillaEJB value = new ContratoPlantillaEJB();
      try {
      value.setId(model.getId());
      value.setTipoContratoId(model.getTipoContratoId());
      value.setCodigo(model.getCodigo());
      value.setObservacion(model.getObservacion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en contratoPlantilla.", e);
			throw new GenericBusinessException(
					"Error al insertar información en contratoPlantilla.");
      }
     
      return getContratoPlantilla(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveContratoPlantilla(com.spirit.nomina.entity.ContratoPlantillaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ContratoPlantillaEJB data = new ContratoPlantillaEJB();
      data.setId(model.getId());
      data.setTipoContratoId(model.getTipoContratoId());
      data.setCodigo(model.getCodigo());
      data.setObservacion(model.getObservacion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en contratoPlantilla.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en contratoPlantilla.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteContratoPlantilla(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ContratoPlantillaEJB data = manager.find(ContratoPlantillaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en contratoPlantilla.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en contratoPlantilla.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.ContratoPlantillaIf getContratoPlantilla(java.lang.Long id) {
      return (ContratoPlantillaEJB)queryManagerLocal.find(ContratoPlantillaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getContratoPlantillaList() {
	  return queryManagerLocal.singleClassList(ContratoPlantillaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getContratoPlantillaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ContratoPlantillaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getContratoPlantillaListSize() {
      Query countQuery = manager.createQuery("select count(*) from ContratoPlantillaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoPlantillaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ContratoPlantillaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoPlantillaByTipoContratoId(java.lang.Long tipoContratoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoContratoId", tipoContratoId);
		return queryManagerLocal.singleClassQueryList(ContratoPlantillaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoPlantillaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ContratoPlantillaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoPlantillaByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(ContratoPlantillaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ContratoPlantillaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoPlantillaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ContratoPlantillaEJB.class, aMap);      
    }

/////////////////
}
