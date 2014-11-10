package com.spirit.nomina.session.generated;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.ContratoRubroEJB;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ContratoRubroSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ContratoRubroSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.ContratoRubroIf addContratoRubro(com.spirit.nomina.entity.ContratoRubroIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ContratoRubroEJB value = new ContratoRubroEJB();
      try {
      value.setId(model.getId());
      value.setFechaFin(model.getFechaFin());
      value.setEstado(model.getEstado());
      value.setFechaInicio(model.getFechaInicio());
      value.setValor(model.getValor());
      value.setContratoId(model.getContratoId());
      value.setRubroId(model.getRubroId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en contratoRubro.", e);
			throw new GenericBusinessException(
					"Error al insertar información en contratoRubro.");
      }
     
      return getContratoRubro(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveContratoRubro(com.spirit.nomina.entity.ContratoRubroIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ContratoRubroEJB data = new ContratoRubroEJB();
      data.setId(model.getId());
      data.setFechaFin(model.getFechaFin());
      data.setEstado(model.getEstado());
      data.setFechaInicio(model.getFechaInicio());
      data.setValor(model.getValor());
      data.setContratoId(model.getContratoId());
      data.setRubroId(model.getRubroId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en contratoRubro.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en contratoRubro.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteContratoRubro(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ContratoRubroEJB data = manager.find(ContratoRubroEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en contratoRubro.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en contratoRubro.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.ContratoRubroIf getContratoRubro(java.lang.Long id) {
      return (ContratoRubroEJB)queryManagerLocal.find(ContratoRubroEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getContratoRubroList() {
	  return queryManagerLocal.singleClassList(ContratoRubroEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getContratoRubroList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ContratoRubroEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getContratoRubroListSize() {
      Query countQuery = manager.createQuery("select count(*) from ContratoRubroEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoRubroById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ContratoRubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoRubroByFechaFin(java.sql.Date fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(ContratoRubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoRubroByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(ContratoRubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoRubroByFechaInicio(java.sql.Date fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(ContratoRubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoRubroByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(ContratoRubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoRubroByContratoId(java.lang.Long contratoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("contratoId", contratoId);
		return queryManagerLocal.singleClassQueryList(ContratoRubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoRubroByRubroId(java.lang.Long rubroId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rubroId", rubroId);
		return queryManagerLocal.singleClassQueryList(ContratoRubroEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ContratoRubroIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findContratoRubroByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ContratoRubroEJB.class, aMap);      
    }

/////////////////
}
