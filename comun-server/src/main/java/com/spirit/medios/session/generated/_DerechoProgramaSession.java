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
public abstract class _DerechoProgramaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_DerechoProgramaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.DerechoProgramaIf addDerechoPrograma(com.spirit.medios.entity.DerechoProgramaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      DerechoProgramaEJB value = new DerechoProgramaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en derechoPrograma.", e);
			throw new GenericBusinessException(
					"Error al insertar información en derechoPrograma.");
      }
     
      return getDerechoPrograma(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveDerechoPrograma(com.spirit.medios.entity.DerechoProgramaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      DerechoProgramaEJB data = new DerechoProgramaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en derechoPrograma.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en derechoPrograma.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteDerechoPrograma(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      DerechoProgramaEJB data = manager.find(DerechoProgramaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en derechoPrograma.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en derechoPrograma.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.DerechoProgramaIf getDerechoPrograma(java.lang.Long id) {
      return (DerechoProgramaEJB)queryManagerLocal.find(DerechoProgramaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getDerechoProgramaList() {
	  return queryManagerLocal.singleClassList(DerechoProgramaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getDerechoProgramaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(DerechoProgramaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getDerechoProgramaListSize() {
      Query countQuery = manager.createQuery("select count(*) from DerechoProgramaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDerechoProgramaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(DerechoProgramaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDerechoProgramaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(DerechoProgramaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDerechoProgramaByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(DerechoProgramaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDerechoProgramaByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(DerechoProgramaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of DerechoProgramaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDerechoProgramaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(DerechoProgramaEJB.class, aMap);      
    }

/////////////////
}
