package com.spirit.contabilidad.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.contabilidad.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _PlantillaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PlantillaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.PlantillaIf addPlantilla(com.spirit.contabilidad.entity.PlantillaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PlantillaEJB value = new PlantillaEJB();
      try {
      value.setId(model.getId());
      value.setEventocontableId(model.getEventocontableId());
      value.setCuentaId(model.getCuentaId());
      value.setDebehaber(model.getDebehaber());
      value.setReferencia(model.getReferencia());
      value.setGlosa(model.getGlosa());
      value.setNemonico(model.getNemonico());
      value.setFormula(model.getFormula());
      value.setTipoEntidad(model.getTipoEntidad());
      value.setCuentaPredeterminadaId(model.getCuentaPredeterminadaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en plantilla.", e);
			throw new GenericBusinessException(
					"Error al insertar información en plantilla.");
      }
     
      return getPlantilla(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePlantilla(com.spirit.contabilidad.entity.PlantillaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PlantillaEJB data = new PlantillaEJB();
      data.setId(model.getId());
      data.setEventocontableId(model.getEventocontableId());
      data.setCuentaId(model.getCuentaId());
      data.setDebehaber(model.getDebehaber());
      data.setReferencia(model.getReferencia());
      data.setGlosa(model.getGlosa());
      data.setNemonico(model.getNemonico());
      data.setFormula(model.getFormula());
      data.setTipoEntidad(model.getTipoEntidad());
      data.setCuentaPredeterminadaId(model.getCuentaPredeterminadaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en plantilla.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en plantilla.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePlantilla(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PlantillaEJB data = manager.find(PlantillaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en plantilla.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en plantilla.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.PlantillaIf getPlantilla(java.lang.Long id) {
      return (PlantillaEJB)queryManagerLocal.find(PlantillaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlantillaList() {
	  return queryManagerLocal.singleClassList(PlantillaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPlantillaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PlantillaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPlantillaListSize() {
      Query countQuery = manager.createQuery("select count(*) from PlantillaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlantillaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PlantillaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlantillaByEventocontableId(java.lang.Long eventocontableId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("eventocontableId", eventocontableId);
		return queryManagerLocal.singleClassQueryList(PlantillaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlantillaByCuentaId(java.lang.Long cuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaId", cuentaId);
		return queryManagerLocal.singleClassQueryList(PlantillaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlantillaByDebehaber(java.lang.String debehaber) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("debehaber", debehaber);
		return queryManagerLocal.singleClassQueryList(PlantillaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlantillaByReferencia(java.lang.String referencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referencia", referencia);
		return queryManagerLocal.singleClassQueryList(PlantillaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlantillaByGlosa(java.lang.String glosa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("glosa", glosa);
		return queryManagerLocal.singleClassQueryList(PlantillaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlantillaByNemonico(java.lang.String nemonico) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nemonico", nemonico);
		return queryManagerLocal.singleClassQueryList(PlantillaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlantillaByFormula(java.lang.String formula) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("formula", formula);
		return queryManagerLocal.singleClassQueryList(PlantillaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlantillaByTipoEntidad(java.lang.String tipoEntidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoEntidad", tipoEntidad);
		return queryManagerLocal.singleClassQueryList(PlantillaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlantillaByCuentaPredeterminadaId(java.lang.Long cuentaPredeterminadaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaPredeterminadaId", cuentaPredeterminadaId);
		return queryManagerLocal.singleClassQueryList(PlantillaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PlantillaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPlantillaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PlantillaEJB.class, aMap);      
    }

/////////////////
}
