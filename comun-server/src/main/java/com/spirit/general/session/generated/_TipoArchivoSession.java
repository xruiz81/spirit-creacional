package com.spirit.general.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.general.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _TipoArchivoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TipoArchivoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoArchivoIf addTipoArchivo(com.spirit.general.entity.TipoArchivoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TipoArchivoEJB value = new TipoArchivoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tipoArchivo.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tipoArchivo.");
      }
     
      return getTipoArchivo(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTipoArchivo(com.spirit.general.entity.TipoArchivoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TipoArchivoEJB data = new TipoArchivoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tipoArchivo.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tipoArchivo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTipoArchivo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TipoArchivoEJB data = manager.find(TipoArchivoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tipoArchivo.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tipoArchivo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoArchivoIf getTipoArchivo(java.lang.Long id) {
      return (TipoArchivoEJB)queryManagerLocal.find(TipoArchivoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoArchivoList() {
	  return queryManagerLocal.singleClassList(TipoArchivoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoArchivoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TipoArchivoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoArchivoListSize() {
      Query countQuery = manager.createQuery("select count(*) from TipoArchivoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoArchivoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TipoArchivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoArchivoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TipoArchivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoArchivoByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(TipoArchivoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TipoArchivoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoArchivoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TipoArchivoEJB.class, aMap);      
    }

/////////////////
}
