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
public abstract class _CuentaBancariaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CuentaBancariaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.CuentaBancariaIf addCuentaBancaria(com.spirit.general.entity.CuentaBancariaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CuentaBancariaEJB value = new CuentaBancariaEJB();
      try {
      value.setId(model.getId());
      value.setEmpresaId(model.getEmpresaId());
      value.setBancoId(model.getBancoId());
      value.setCuenta(model.getCuenta());
      value.setTipocuenta(model.getTipocuenta());
      value.setNumeroCheque(model.getNumeroCheque());
      value.setCuentaCliente(model.getCuentaCliente());
      value.setClienteId(model.getClienteId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en cuentaBancaria.", e);
			throw new GenericBusinessException(
					"Error al insertar información en cuentaBancaria.");
      }
     
      return getCuentaBancaria(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCuentaBancaria(com.spirit.general.entity.CuentaBancariaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CuentaBancariaEJB data = new CuentaBancariaEJB();
      data.setId(model.getId());
      data.setEmpresaId(model.getEmpresaId());
      data.setBancoId(model.getBancoId());
      data.setCuenta(model.getCuenta());
      data.setTipocuenta(model.getTipocuenta());
      data.setNumeroCheque(model.getNumeroCheque());
      data.setCuentaCliente(model.getCuentaCliente());
      data.setClienteId(model.getClienteId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en cuentaBancaria.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en cuentaBancaria.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCuentaBancaria(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CuentaBancariaEJB data = manager.find(CuentaBancariaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en cuentaBancaria.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en cuentaBancaria.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.CuentaBancariaIf getCuentaBancaria(java.lang.Long id) {
      return (CuentaBancariaEJB)queryManagerLocal.find(CuentaBancariaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCuentaBancariaList() {
	  return queryManagerLocal.singleClassList(CuentaBancariaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCuentaBancariaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CuentaBancariaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCuentaBancariaListSize() {
      Query countQuery = manager.createQuery("select count(*) from CuentaBancariaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaBancariaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CuentaBancariaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaBancariaByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(CuentaBancariaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaBancariaByBancoId(java.lang.Long bancoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bancoId", bancoId);
		return queryManagerLocal.singleClassQueryList(CuentaBancariaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaBancariaByCuenta(java.lang.String cuenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuenta", cuenta);
		return queryManagerLocal.singleClassQueryList(CuentaBancariaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaBancariaByTipocuenta(java.lang.String tipocuenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipocuenta", tipocuenta);
		return queryManagerLocal.singleClassQueryList(CuentaBancariaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaBancariaByNumeroCheque(java.lang.String numeroCheque) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numeroCheque", numeroCheque);
		return queryManagerLocal.singleClassQueryList(CuentaBancariaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaBancariaByCuentaCliente(java.lang.String cuentaCliente) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaCliente", cuentaCliente);
		return queryManagerLocal.singleClassQueryList(CuentaBancariaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaBancariaByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(CuentaBancariaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CuentaBancariaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaBancariaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CuentaBancariaEJB.class, aMap);      
    }

/////////////////
}
