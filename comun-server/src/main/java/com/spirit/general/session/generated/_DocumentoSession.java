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
public abstract class _DocumentoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_DocumentoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.DocumentoIf addDocumento(com.spirit.general.entity.DocumentoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      DocumentoEJB value = new DocumentoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setAbreviado(model.getAbreviado());
      value.setTipoDocumentoId(model.getTipoDocumentoId());
      value.setPideAutorizacion(model.getPideAutorizacion());
      value.setEstado(model.getEstado());
      value.setDiferido(model.getDiferido());
      value.setBonificacion(model.getBonificacion());
      value.setPrecioEspecial(model.getPrecioEspecial());
      value.setDescuentoEspecial(model.getDescuentoEspecial());
      value.setMulta(model.getMulta());
      value.setInteres(model.getInteres());
      value.setProtesto(model.getProtesto());
      value.setCheque(model.getCheque());
      value.setRetencionRenta(model.getRetencionRenta());
      value.setRetencionIva(model.getRetencionIva());
      value.setEfectivo(model.getEfectivo());
      value.setDebitoBancario(model.getDebitoBancario());
      value.setTarjetaCredito(model.getTarjetaCredito());
      value.setTransaccionElectronica(model.getTransaccionElectronica());
      value.setTransferenciaBancaria(model.getTransferenciaBancaria());
      value.setNivelacion(model.getNivelacion());
      value.setAnticipo(model.getAnticipo());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en documento.", e);
			throw new GenericBusinessException(
					"Error al insertar información en documento.");
      }
     
      return getDocumento(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveDocumento(com.spirit.general.entity.DocumentoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      DocumentoEJB data = new DocumentoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setAbreviado(model.getAbreviado());
      data.setTipoDocumentoId(model.getTipoDocumentoId());
      data.setPideAutorizacion(model.getPideAutorizacion());
      data.setEstado(model.getEstado());
      data.setDiferido(model.getDiferido());
      data.setBonificacion(model.getBonificacion());
      data.setPrecioEspecial(model.getPrecioEspecial());
      data.setDescuentoEspecial(model.getDescuentoEspecial());
      data.setMulta(model.getMulta());
      data.setInteres(model.getInteres());
      data.setProtesto(model.getProtesto());
      data.setCheque(model.getCheque());
      data.setRetencionRenta(model.getRetencionRenta());
      data.setRetencionIva(model.getRetencionIva());
      data.setEfectivo(model.getEfectivo());
      data.setDebitoBancario(model.getDebitoBancario());
      data.setTarjetaCredito(model.getTarjetaCredito());
      data.setTransaccionElectronica(model.getTransaccionElectronica());
      data.setTransferenciaBancaria(model.getTransferenciaBancaria());
      data.setNivelacion(model.getNivelacion());
      data.setAnticipo(model.getAnticipo());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en documento.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en documento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteDocumento(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      DocumentoEJB data = manager.find(DocumentoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en documento.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en documento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.DocumentoIf getDocumento(java.lang.Long id) {
      return (DocumentoEJB)queryManagerLocal.find(DocumentoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getDocumentoList() {
	  return queryManagerLocal.singleClassList(DocumentoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getDocumentoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(DocumentoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getDocumentoListSize() {
      Query countQuery = manager.createQuery("select count(*) from DocumentoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByAbreviado(java.lang.String abreviado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("abreviado", abreviado);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByTipoDocumentoId(java.lang.Long tipoDocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoDocumentoId", tipoDocumentoId);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByPideAutorizacion(java.lang.String pideAutorizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pideAutorizacion", pideAutorizacion);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByDiferido(java.lang.String diferido) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("diferido", diferido);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByBonificacion(java.lang.String bonificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bonificacion", bonificacion);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByPrecioEspecial(java.lang.String precioEspecial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("precioEspecial", precioEspecial);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByDescuentoEspecial(java.lang.String descuentoEspecial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuentoEspecial", descuentoEspecial);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByMulta(java.lang.String multa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("multa", multa);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByInteres(java.lang.String interes) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("interes", interes);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByProtesto(java.lang.String protesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("protesto", protesto);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByCheque(java.lang.String cheque) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cheque", cheque);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByRetencionRenta(java.lang.String retencionRenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("retencionRenta", retencionRenta);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByRetencionIva(java.lang.String retencionIva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("retencionIva", retencionIva);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByEfectivo(java.lang.String efectivo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("efectivo", efectivo);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByDebitoBancario(java.lang.String debitoBancario) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("debitoBancario", debitoBancario);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByTarjetaCredito(java.lang.String tarjetaCredito) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tarjetaCredito", tarjetaCredito);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByTransaccionElectronica(java.lang.String transaccionElectronica) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transaccionElectronica", transaccionElectronica);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByTransferenciaBancaria(java.lang.String transferenciaBancaria) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transferenciaBancaria", transferenciaBancaria);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByNivelacion(java.lang.String nivelacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nivelacion", nivelacion);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByAnticipo(java.lang.String anticipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("anticipo", anticipo);
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of DocumentoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDocumentoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(DocumentoEJB.class, aMap);      
    }

/////////////////
}
