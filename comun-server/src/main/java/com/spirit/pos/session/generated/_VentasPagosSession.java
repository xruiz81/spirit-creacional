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
public abstract class _VentasPagosSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_VentasPagosSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.VentasPagosIf addVentasPagos(com.spirit.pos.entity.VentasPagosIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      VentasPagosEJB value = new VentasPagosEJB();
      try {
      value.setId(model.getId());
      value.setValor(model.getValor());
      value.setTipo(model.getTipo());
      value.setReferencia(model.getReferencia());
      value.setReferenciaId(model.getReferenciaId());
      value.setVentastrackId(model.getVentastrackId());
      value.setRevisado(model.getRevisado());
      value.setNumDoc(model.getNumDoc());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en ventasPagos.", e);
			throw new GenericBusinessException(
					"Error al insertar información en ventasPagos.");
      }
     
      return getVentasPagos(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveVentasPagos(com.spirit.pos.entity.VentasPagosIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      VentasPagosEJB data = new VentasPagosEJB();
      data.setId(model.getId());
      data.setValor(model.getValor());
      data.setTipo(model.getTipo());
      data.setReferencia(model.getReferencia());
      data.setReferenciaId(model.getReferenciaId());
      data.setVentastrackId(model.getVentastrackId());
      data.setRevisado(model.getRevisado());
      data.setNumDoc(model.getNumDoc());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en ventasPagos.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en ventasPagos.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteVentasPagos(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      VentasPagosEJB data = manager.find(VentasPagosEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en ventasPagos.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en ventasPagos.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.VentasPagosIf getVentasPagos(java.lang.Long id) {
      return (VentasPagosEJB)queryManagerLocal.find(VentasPagosEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getVentasPagosList() {
	  return queryManagerLocal.singleClassList(VentasPagosEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getVentasPagosList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(VentasPagosEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getVentasPagosListSize() {
      Query countQuery = manager.createQuery("select count(*) from VentasPagosEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasPagosById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(VentasPagosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasPagosByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(VentasPagosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasPagosByTipo(java.lang.Long tipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipo", tipo);
		return queryManagerLocal.singleClassQueryList(VentasPagosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasPagosByReferencia(java.lang.String referencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referencia", referencia);
		return queryManagerLocal.singleClassQueryList(VentasPagosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasPagosByReferenciaId(java.lang.Long referenciaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referenciaId", referenciaId);
		return queryManagerLocal.singleClassQueryList(VentasPagosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasPagosByVentastrackId(java.lang.Long ventastrackId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ventastrackId", ventastrackId);
		return queryManagerLocal.singleClassQueryList(VentasPagosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasPagosByRevisado(java.lang.String revisado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("revisado", revisado);
		return queryManagerLocal.singleClassQueryList(VentasPagosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasPagosByNumDoc(java.lang.String numDoc) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numDoc", numDoc);
		return queryManagerLocal.singleClassQueryList(VentasPagosEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of VentasPagosIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findVentasPagosByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(VentasPagosEJB.class, aMap);      
    }

/////////////////
}
