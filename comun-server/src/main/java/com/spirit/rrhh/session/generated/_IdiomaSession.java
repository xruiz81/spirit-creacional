package com.spirit.rrhh.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.rrhh.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _IdiomaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_IdiomaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.rrhh.entity.IdiomaIf addIdioma(com.spirit.rrhh.entity.IdiomaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      IdiomaEJB value = new IdiomaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en idioma.", e);
			throw new GenericBusinessException(
					"Error al insertar información en idioma.");
      }
     
      return getIdioma(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveIdioma(com.spirit.rrhh.entity.IdiomaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      IdiomaEJB data = new IdiomaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en idioma.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en idioma.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteIdioma(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      IdiomaEJB data = manager.find(IdiomaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en idioma.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en idioma.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.rrhh.entity.IdiomaIf getIdioma(java.lang.Long id) {
      return (IdiomaEJB)queryManagerLocal.find(IdiomaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getIdiomaList() {
	  return queryManagerLocal.singleClassList(IdiomaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getIdiomaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(IdiomaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getIdiomaListSize() {
      Query countQuery = manager.createQuery("select count(*) from IdiomaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findIdiomaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(IdiomaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findIdiomaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(IdiomaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findIdiomaByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(IdiomaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of IdiomaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findIdiomaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(IdiomaEJB.class, aMap);      
    }

/////////////////
}
