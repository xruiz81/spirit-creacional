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
public abstract class _NumeradoresSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_NumeradoresSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.NumeradoresIf addNumeradores(com.spirit.general.entity.NumeradoresIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      NumeradoresEJB value = new NumeradoresEJB();
      try {
      value.setId(model.getId());
      value.setUltimoValor(model.getUltimoValor());
      value.setNombreTabla(model.getNombreTabla());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en numeradores.", e);
			throw new GenericBusinessException(
					"Error al insertar información en numeradores.");
      }
     
      return getNumeradores(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveNumeradores(com.spirit.general.entity.NumeradoresIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      NumeradoresEJB data = new NumeradoresEJB();
      data.setId(model.getId());
      data.setUltimoValor(model.getUltimoValor());
      data.setNombreTabla(model.getNombreTabla());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en numeradores.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en numeradores.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteNumeradores(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      NumeradoresEJB data = manager.find(NumeradoresEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en numeradores.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en numeradores.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.NumeradoresIf getNumeradores(java.lang.Long id) {
      return (NumeradoresEJB)queryManagerLocal.find(NumeradoresEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getNumeradoresList() {
	  return queryManagerLocal.singleClassList(NumeradoresEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getNumeradoresList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(NumeradoresEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getNumeradoresListSize() {
      Query countQuery = manager.createQuery("select count(*) from NumeradoresEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNumeradoresById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(NumeradoresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNumeradoresByUltimoValor(java.math.BigDecimal ultimoValor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ultimoValor", ultimoValor);
		return queryManagerLocal.singleClassQueryList(NumeradoresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNumeradoresByNombreTabla(java.lang.String nombreTabla) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombreTabla", nombreTabla);
		return queryManagerLocal.singleClassQueryList(NumeradoresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNumeradoresByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(NumeradoresEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of NumeradoresIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNumeradoresByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(NumeradoresEJB.class, aMap);      
    }

/////////////////
}
