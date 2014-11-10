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
public abstract class _TipoDocumentoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TipoDocumentoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoDocumentoIf addTipoDocumento(com.spirit.general.entity.TipoDocumentoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TipoDocumentoEJB value = new TipoDocumentoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setModuloId(model.getModuloId());
      value.setMascara(model.getMascara());
      value.setEmpresaId(model.getEmpresaId());
      value.setNumerolineas(model.getNumerolineas());
      value.setAfectacartera(model.getAfectacartera());
      value.setAfectastock(model.getAfectastock());
      value.setAfectaventa(model.getAfectaventa());
      value.setExigemotivo(model.getExigemotivo());
      value.setEstado(model.getEstado());
      value.setFormapago(model.getFormapago());
      value.setCliente(model.getCliente());
      value.setCaja(model.getCaja());
      value.setPermiteeliminacion(model.getPermiteeliminacion());
      value.setReembolso(model.getReembolso());
      value.setSignocartera(model.getSignocartera());
      value.setSignostock(model.getSignostock());
      value.setSignoventa(model.getSignoventa());
      value.setDescuentoespecial(model.getDescuentoespecial());
      value.setTipocartera(model.getTipocartera());
      value.setIdSriTipoComprobante(model.getIdSriTipoComprobante());
      value.setTipoTroqueladoId(model.getTipoTroqueladoId());
      value.setTransferible(model.getTransferible());
      value.setFactura(model.getFactura());
      value.setNotaVenta(model.getNotaVenta());
      value.setNotaCredito(model.getNotaCredito());
      value.setNotaDebito(model.getNotaDebito());
      value.setLiquidacionCompras(model.getLiquidacionCompras());
      value.setAbreviatura(model.getAbreviatura());
      value.setEgreso(model.getEgreso());
      value.setAnticipo(model.getAnticipo());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tipoDocumento.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tipoDocumento.");
      }
     
      return getTipoDocumento(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTipoDocumento(com.spirit.general.entity.TipoDocumentoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TipoDocumentoEJB data = new TipoDocumentoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setModuloId(model.getModuloId());
      data.setMascara(model.getMascara());
      data.setEmpresaId(model.getEmpresaId());
      data.setNumerolineas(model.getNumerolineas());
      data.setAfectacartera(model.getAfectacartera());
      data.setAfectastock(model.getAfectastock());
      data.setAfectaventa(model.getAfectaventa());
      data.setExigemotivo(model.getExigemotivo());
      data.setEstado(model.getEstado());
      data.setFormapago(model.getFormapago());
      data.setCliente(model.getCliente());
      data.setCaja(model.getCaja());
      data.setPermiteeliminacion(model.getPermiteeliminacion());
      data.setReembolso(model.getReembolso());
      data.setSignocartera(model.getSignocartera());
      data.setSignostock(model.getSignostock());
      data.setSignoventa(model.getSignoventa());
      data.setDescuentoespecial(model.getDescuentoespecial());
      data.setTipocartera(model.getTipocartera());
      data.setIdSriTipoComprobante(model.getIdSriTipoComprobante());
      data.setTipoTroqueladoId(model.getTipoTroqueladoId());
      data.setTransferible(model.getTransferible());
      data.setFactura(model.getFactura());
      data.setNotaVenta(model.getNotaVenta());
      data.setNotaCredito(model.getNotaCredito());
      data.setNotaDebito(model.getNotaDebito());
      data.setLiquidacionCompras(model.getLiquidacionCompras());
      data.setAbreviatura(model.getAbreviatura());
      data.setEgreso(model.getEgreso());
      data.setAnticipo(model.getAnticipo());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tipoDocumento.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tipoDocumento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTipoDocumento(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TipoDocumentoEJB data = manager.find(TipoDocumentoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tipoDocumento.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tipoDocumento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoDocumentoIf getTipoDocumento(java.lang.Long id) {
      return (TipoDocumentoEJB)queryManagerLocal.find(TipoDocumentoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoDocumentoList() {
	  return queryManagerLocal.singleClassList(TipoDocumentoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoDocumentoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TipoDocumentoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoDocumentoListSize() {
      Query countQuery = manager.createQuery("select count(*) from TipoDocumentoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByModuloId(java.lang.Long moduloId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("moduloId", moduloId);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByMascara(java.lang.String mascara) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("mascara", mascara);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByNumerolineas(java.lang.Integer numerolineas) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numerolineas", numerolineas);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByAfectacartera(java.lang.String afectacartera) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("afectacartera", afectacartera);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByAfectastock(java.lang.String afectastock) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("afectastock", afectastock);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByAfectaventa(java.lang.String afectaventa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("afectaventa", afectaventa);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByExigemotivo(java.lang.String exigemotivo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("exigemotivo", exigemotivo);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByFormapago(java.lang.String formapago) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("formapago", formapago);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByCliente(java.lang.String cliente) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cliente", cliente);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByCaja(java.lang.String caja) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("caja", caja);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByPermiteeliminacion(java.lang.String permiteeliminacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("permiteeliminacion", permiteeliminacion);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByReembolso(java.lang.String reembolso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("reembolso", reembolso);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoBySignocartera(java.lang.String signocartera) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("signocartera", signocartera);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoBySignostock(java.lang.String signostock) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("signostock", signostock);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoBySignoventa(java.lang.String signoventa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("signoventa", signoventa);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByDescuentoespecial(java.lang.String descuentoespecial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuentoespecial", descuentoespecial);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByTipocartera(java.lang.String tipocartera) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipocartera", tipocartera);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByIdSriTipoComprobante(java.lang.Long idSriTipoComprobante) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("idSriTipoComprobante", idSriTipoComprobante);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByTipoTroqueladoId(java.lang.Long tipoTroqueladoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoTroqueladoId", tipoTroqueladoId);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByTransferible(java.lang.String transferible) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("transferible", transferible);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByFactura(java.lang.String factura) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("factura", factura);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByNotaVenta(java.lang.String notaVenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("notaVenta", notaVenta);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByNotaCredito(java.lang.String notaCredito) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("notaCredito", notaCredito);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByNotaDebito(java.lang.String notaDebito) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("notaDebito", notaDebito);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByLiquidacionCompras(java.lang.String liquidacionCompras) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("liquidacionCompras", liquidacionCompras);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByAbreviatura(java.lang.String abreviatura) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("abreviatura", abreviatura);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByEgreso(java.lang.String egreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("egreso", egreso);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByAnticipo(java.lang.String anticipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("anticipo", anticipo);
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TipoDocumentoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoDocumentoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TipoDocumentoEJB.class, aMap);      
    }

/////////////////
}
