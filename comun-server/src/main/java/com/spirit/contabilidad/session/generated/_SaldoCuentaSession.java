package com.spirit.contabilidad.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.contabilidad.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _SaldoCuentaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SaldoCuentaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.SaldoCuentaIf addSaldoCuenta(com.spirit.contabilidad.entity.SaldoCuentaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SaldoCuentaEJB value = new SaldoCuentaEJB();
      try {
      value.setId(model.getId());
      value.setCuentaId(model.getCuentaId());
      value.setPeriodoId(model.getPeriodoId());
      value.setAnio(model.getAnio());
      value.setMes(model.getMes());
      value.setValordebe(model.getValordebe());
      value.setValorhaber(model.getValorhaber());
      value.setValor(model.getValor());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en saldoCuenta.", e);
			throw new GenericBusinessException(
					"Error al insertar información en saldoCuenta.");
      }
     
      return getSaldoCuenta(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSaldoCuenta(com.spirit.contabilidad.entity.SaldoCuentaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SaldoCuentaEJB data = new SaldoCuentaEJB();
      data.setId(model.getId());
      data.setCuentaId(model.getCuentaId());
      data.setPeriodoId(model.getPeriodoId());
      data.setAnio(model.getAnio());
      data.setMes(model.getMes());
      data.setValordebe(model.getValordebe());
      data.setValorhaber(model.getValorhaber());
      data.setValor(model.getValor());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en saldoCuenta.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en saldoCuenta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSaldoCuenta(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SaldoCuentaEJB data = manager.find(SaldoCuentaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en saldoCuenta.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en saldoCuenta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.SaldoCuentaIf getSaldoCuenta(java.lang.Long id) {
      return (SaldoCuentaEJB)queryManagerLocal.find(SaldoCuentaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSaldoCuentaList() {
	  return queryManagerLocal.singleClassList(SaldoCuentaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSaldoCuentaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SaldoCuentaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSaldoCuentaListSize() {
      Query countQuery = manager.createQuery("select count(*) from SaldoCuentaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSaldoCuentaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SaldoCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSaldoCuentaByCuentaId(java.lang.Long cuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaId", cuentaId);
		return queryManagerLocal.singleClassQueryList(SaldoCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSaldoCuentaByPeriodoId(java.lang.Long periodoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("periodoId", periodoId);
		return queryManagerLocal.singleClassQueryList(SaldoCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSaldoCuentaByAnio(java.lang.String anio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("anio", anio);
		return queryManagerLocal.singleClassQueryList(SaldoCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSaldoCuentaByMes(java.lang.String mes) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("mes", mes);
		return queryManagerLocal.singleClassQueryList(SaldoCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSaldoCuentaByValordebe(java.math.BigDecimal valordebe) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valordebe", valordebe);
		return queryManagerLocal.singleClassQueryList(SaldoCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSaldoCuentaByValorhaber(java.math.BigDecimal valorhaber) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorhaber", valorhaber);
		return queryManagerLocal.singleClassQueryList(SaldoCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSaldoCuentaByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(SaldoCuentaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SaldoCuentaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSaldoCuentaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SaldoCuentaEJB.class, aMap);      
    }

/////////////////
}
