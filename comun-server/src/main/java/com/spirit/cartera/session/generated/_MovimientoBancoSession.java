package com.spirit.cartera.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.cartera.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _MovimientoBancoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_MovimientoBancoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.MovimientoBancoIf addMovimientoBanco(com.spirit.cartera.entity.MovimientoBancoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      MovimientoBancoEJB value = new MovimientoBancoEJB();
      try {
      value.setId(model.getId());
      value.setCuentaId(model.getCuentaId());
      value.setDocumentoId(model.getDocumentoId());
      value.setReferencia(model.getReferencia());
      value.setFecha(model.getFecha());
      value.setValor(model.getValor());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en movimientoBanco.", e);
			throw new GenericBusinessException(
					"Error al insertar información en movimientoBanco.");
      }
     
      return getMovimientoBanco(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveMovimientoBanco(com.spirit.cartera.entity.MovimientoBancoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      MovimientoBancoEJB data = new MovimientoBancoEJB();
      data.setId(model.getId());
      data.setCuentaId(model.getCuentaId());
      data.setDocumentoId(model.getDocumentoId());
      data.setReferencia(model.getReferencia());
      data.setFecha(model.getFecha());
      data.setValor(model.getValor());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en movimientoBanco.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en movimientoBanco.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteMovimientoBanco(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      MovimientoBancoEJB data = manager.find(MovimientoBancoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en movimientoBanco.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en movimientoBanco.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.cartera.entity.MovimientoBancoIf getMovimientoBanco(java.lang.Long id) {
      return (MovimientoBancoEJB)queryManagerLocal.find(MovimientoBancoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMovimientoBancoList() {
	  return queryManagerLocal.singleClassList(MovimientoBancoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMovimientoBancoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(MovimientoBancoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getMovimientoBancoListSize() {
      Query countQuery = manager.createQuery("select count(*) from MovimientoBancoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoBancoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(MovimientoBancoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoBancoByCuentaId(java.lang.Long cuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaId", cuentaId);
		return queryManagerLocal.singleClassQueryList(MovimientoBancoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoBancoByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(MovimientoBancoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoBancoByReferencia(java.lang.String referencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referencia", referencia);
		return queryManagerLocal.singleClassQueryList(MovimientoBancoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoBancoByFecha(java.sql.Timestamp fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(MovimientoBancoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoBancoByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(MovimientoBancoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of MovimientoBancoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMovimientoBancoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(MovimientoBancoEJB.class, aMap);      
    }

/////////////////
}
