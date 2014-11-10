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
public abstract class _HerramientasMediosSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_HerramientasMediosSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.HerramientasMediosIf addHerramientasMedios(com.spirit.medios.entity.HerramientasMediosIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      HerramientasMediosEJB value = new HerramientasMediosEJB();
      try {
      value.setId(model.getId());
      value.setClienteOficinaId(model.getClienteOficinaId());
      value.setProveedorOficinaId(model.getProveedorOficinaId());
      value.setFrecuencia(model.getFrecuencia());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en herramientasMedios.", e);
			throw new GenericBusinessException(
					"Error al insertar información en herramientasMedios.");
      }
     
      return getHerramientasMedios(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveHerramientasMedios(com.spirit.medios.entity.HerramientasMediosIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      HerramientasMediosEJB data = new HerramientasMediosEJB();
      data.setId(model.getId());
      data.setClienteOficinaId(model.getClienteOficinaId());
      data.setProveedorOficinaId(model.getProveedorOficinaId());
      data.setFrecuencia(model.getFrecuencia());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en herramientasMedios.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en herramientasMedios.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteHerramientasMedios(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      HerramientasMediosEJB data = manager.find(HerramientasMediosEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en herramientasMedios.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en herramientasMedios.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.HerramientasMediosIf getHerramientasMedios(java.lang.Long id) {
      return (HerramientasMediosEJB)queryManagerLocal.find(HerramientasMediosEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getHerramientasMediosList() {
	  return queryManagerLocal.singleClassList(HerramientasMediosEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getHerramientasMediosList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(HerramientasMediosEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getHerramientasMediosListSize() {
      Query countQuery = manager.createQuery("select count(*) from HerramientasMediosEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findHerramientasMediosById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(HerramientasMediosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findHerramientasMediosByClienteOficinaId(java.lang.Long clienteOficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteOficinaId", clienteOficinaId);
		return queryManagerLocal.singleClassQueryList(HerramientasMediosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findHerramientasMediosByProveedorOficinaId(java.lang.Long proveedorOficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("proveedorOficinaId", proveedorOficinaId);
		return queryManagerLocal.singleClassQueryList(HerramientasMediosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findHerramientasMediosByFrecuencia(java.lang.String frecuencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("frecuencia", frecuencia);
		return queryManagerLocal.singleClassQueryList(HerramientasMediosEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of HerramientasMediosIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findHerramientasMediosByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(HerramientasMediosEJB.class, aMap);      
    }

/////////////////
}
