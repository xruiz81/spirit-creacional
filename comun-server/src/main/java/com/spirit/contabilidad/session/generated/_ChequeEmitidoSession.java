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
public abstract class _ChequeEmitidoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ChequeEmitidoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.ChequeEmitidoIf addChequeEmitido(com.spirit.contabilidad.entity.ChequeEmitidoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ChequeEmitidoEJB value = new ChequeEmitidoEJB();
      try {
      value.setId(model.getId());
      value.setFechaEmision(model.getFechaEmision());
      value.setCuentaBancariaId(model.getCuentaBancariaId());
      value.setNumero(model.getNumero());
      value.setDetalle(model.getDetalle());
      value.setValor(model.getValor());
      value.setEstado(model.getEstado());
      value.setTipoDocumentoId(model.getTipoDocumentoId());
      value.setTransaccionId(model.getTransaccionId());
      value.setBeneficiario(model.getBeneficiario());
      value.setFechaCobro(model.getFechaCobro());
      value.setOrigen(model.getOrigen());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en chequeEmitido.", e);
			throw new GenericBusinessException(
					"Error al insertar información en chequeEmitido.");
      }
     
      return getChequeEmitido(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveChequeEmitido(com.spirit.contabilidad.entity.ChequeEmitidoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ChequeEmitidoEJB data = new ChequeEmitidoEJB();
      data.setId(model.getId());
      data.setFechaEmision(model.getFechaEmision());
      data.setCuentaBancariaId(model.getCuentaBancariaId());
      data.setNumero(model.getNumero());
      data.setDetalle(model.getDetalle());
      data.setValor(model.getValor());
      data.setEstado(model.getEstado());
      data.setTipoDocumentoId(model.getTipoDocumentoId());
      data.setTransaccionId(model.getTransaccionId());
      data.setBeneficiario(model.getBeneficiario());
      data.setFechaCobro(model.getFechaCobro());
      data.setOrigen(model.getOrigen());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en chequeEmitido.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en chequeEmitido.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteChequeEmitido(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ChequeEmitidoEJB data = manager.find(ChequeEmitidoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en chequeEmitido.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en chequeEmitido.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.ChequeEmitidoIf getChequeEmitido(java.lang.Long id) {
      return (ChequeEmitidoEJB)queryManagerLocal.find(ChequeEmitidoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getChequeEmitidoList() {
	  return queryManagerLocal.singleClassList(ChequeEmitidoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getChequeEmitidoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ChequeEmitidoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getChequeEmitidoListSize() {
      Query countQuery = manager.createQuery("select count(*) from ChequeEmitidoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeEmitidoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ChequeEmitidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeEmitidoByFechaEmision(java.sql.Date fechaEmision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaEmision", fechaEmision);
		return queryManagerLocal.singleClassQueryList(ChequeEmitidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeEmitidoByCuentaBancariaId(java.lang.Long cuentaBancariaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaBancariaId", cuentaBancariaId);
		return queryManagerLocal.singleClassQueryList(ChequeEmitidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeEmitidoByNumero(java.lang.String numero) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numero", numero);
		return queryManagerLocal.singleClassQueryList(ChequeEmitidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeEmitidoByDetalle(java.lang.String detalle) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("detalle", detalle);
		return queryManagerLocal.singleClassQueryList(ChequeEmitidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeEmitidoByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(ChequeEmitidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeEmitidoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(ChequeEmitidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeEmitidoByTipoDocumentoId(java.lang.Long tipoDocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoDocumentoId", tipoDocumentoId);
		return queryManagerLocal.singleClassQueryList(ChequeEmitidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeEmitidoByTransaccionId(java.lang.Long transaccionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transaccionId", transaccionId);
		return queryManagerLocal.singleClassQueryList(ChequeEmitidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeEmitidoByBeneficiario(java.lang.String beneficiario) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("beneficiario", beneficiario);
		return queryManagerLocal.singleClassQueryList(ChequeEmitidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeEmitidoByFechaCobro(java.sql.Date fechaCobro) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCobro", fechaCobro);
		return queryManagerLocal.singleClassQueryList(ChequeEmitidoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeEmitidoByOrigen(java.lang.String origen) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("origen", origen);
		return queryManagerLocal.singleClassQueryList(ChequeEmitidoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ChequeEmitidoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findChequeEmitidoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ChequeEmitidoEJB.class, aMap);      
    }

/////////////////
}
