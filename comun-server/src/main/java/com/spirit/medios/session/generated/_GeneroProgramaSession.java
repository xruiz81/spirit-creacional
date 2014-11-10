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
public abstract class _GeneroProgramaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_GeneroProgramaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.GeneroProgramaIf addGeneroPrograma(com.spirit.medios.entity.GeneroProgramaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      GeneroProgramaEJB value = new GeneroProgramaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en generoPrograma.", e);
			throw new GenericBusinessException(
					"Error al insertar información en generoPrograma.");
      }
     
      return getGeneroPrograma(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveGeneroPrograma(com.spirit.medios.entity.GeneroProgramaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      GeneroProgramaEJB data = new GeneroProgramaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en generoPrograma.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en generoPrograma.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteGeneroPrograma(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      GeneroProgramaEJB data = manager.find(GeneroProgramaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en generoPrograma.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en generoPrograma.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.GeneroProgramaIf getGeneroPrograma(java.lang.Long id) {
      return (GeneroProgramaEJB)queryManagerLocal.find(GeneroProgramaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGeneroProgramaList() {
	  return queryManagerLocal.singleClassList(GeneroProgramaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGeneroProgramaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(GeneroProgramaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getGeneroProgramaListSize() {
      Query countQuery = manager.createQuery("select count(*) from GeneroProgramaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGeneroProgramaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(GeneroProgramaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGeneroProgramaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(GeneroProgramaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGeneroProgramaByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(GeneroProgramaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGeneroProgramaByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(GeneroProgramaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of GeneroProgramaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGeneroProgramaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(GeneroProgramaEJB.class, aMap);      
    }

/////////////////
}
