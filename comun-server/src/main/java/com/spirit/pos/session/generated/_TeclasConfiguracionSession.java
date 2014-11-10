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
public abstract class _TeclasConfiguracionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TeclasConfiguracionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.TeclasConfiguracionIf addTeclasConfiguracion(com.spirit.pos.entity.TeclasConfiguracionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TeclasConfiguracionEJB value = new TeclasConfiguracionEJB();
      try {
      value.setId(model.getId());
      value.setTeclasNombre(model.getTeclasNombre());
      value.setDescripcion(model.getDescripcion());
      value.setCodigo(model.getCodigo());
      value.setEstado(model.getEstado());
      value.setMascara(model.getMascara());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en teclasConfiguracion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en teclasConfiguracion.");
      }
     
      return getTeclasConfiguracion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTeclasConfiguracion(com.spirit.pos.entity.TeclasConfiguracionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TeclasConfiguracionEJB data = new TeclasConfiguracionEJB();
      data.setId(model.getId());
      data.setTeclasNombre(model.getTeclasNombre());
      data.setDescripcion(model.getDescripcion());
      data.setCodigo(model.getCodigo());
      data.setEstado(model.getEstado());
      data.setMascara(model.getMascara());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en teclasConfiguracion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en teclasConfiguracion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTeclasConfiguracion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TeclasConfiguracionEJB data = manager.find(TeclasConfiguracionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en teclasConfiguracion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en teclasConfiguracion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.TeclasConfiguracionIf getTeclasConfiguracion(java.lang.Long id) {
      return (TeclasConfiguracionEJB)queryManagerLocal.find(TeclasConfiguracionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTeclasConfiguracionList() {
	  return queryManagerLocal.singleClassList(TeclasConfiguracionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTeclasConfiguracionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TeclasConfiguracionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTeclasConfiguracionListSize() {
      Query countQuery = manager.createQuery("select count(*) from TeclasConfiguracionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTeclasConfiguracionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TeclasConfiguracionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTeclasConfiguracionByTeclasNombre(java.lang.String teclasNombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("teclasNombre", teclasNombre);
		return queryManagerLocal.singleClassQueryList(TeclasConfiguracionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTeclasConfiguracionByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(TeclasConfiguracionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTeclasConfiguracionByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TeclasConfiguracionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTeclasConfiguracionByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(TeclasConfiguracionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTeclasConfiguracionByMascara(java.lang.String mascara) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("mascara", mascara);
		return queryManagerLocal.singleClassQueryList(TeclasConfiguracionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TeclasConfiguracionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTeclasConfiguracionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TeclasConfiguracionEJB.class, aMap);      
    }

/////////////////
}
