package com.spirit.crm.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.crm.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ClienteSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ClienteSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.ClienteIf addCliente(com.spirit.crm.entity.ClienteIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ClienteEJB value = new ClienteEJB();
      try {
      value.setId(model.getId());
      value.setTipoidentificacionId(model.getTipoidentificacionId());
      value.setIdentificacion(model.getIdentificacion());
      value.setNombreLegal(model.getNombreLegal());
      value.setRazonSocial(model.getRazonSocial());
      value.setRepresentante(model.getRepresentante());
      value.setCorporacionId(model.getCorporacionId());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setEstado(model.getEstado());
      value.setTipoclienteId(model.getTipoclienteId());
      value.setTipoproveedorId(model.getTipoproveedorId());
      value.setTiponegocioId(model.getTiponegocioId());
      value.setCuentaId(model.getCuentaId());
      value.setObservacion(model.getObservacion());
      value.setUsuariofinal(model.getUsuariofinal());
      value.setContribuyenteEspecial(model.getContribuyenteEspecial());
      value.setTipoPersona(model.getTipoPersona());
      value.setLlevaContabilidad(model.getLlevaContabilidad());
      value.setInformacionAdc(model.getInformacionAdc());
      value.setRequiereSap(model.getRequiereSap());
      value.setBancoId(model.getBancoId());
      value.setTipoCuenta(model.getTipoCuenta());
      value.setNumeroCuenta(model.getNumeroCuenta());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en cliente.", e);
			throw new GenericBusinessException(
					"Error al insertar información en cliente.");
      }
     
      return getCliente(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCliente(com.spirit.crm.entity.ClienteIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ClienteEJB data = new ClienteEJB();
      data.setId(model.getId());
      data.setTipoidentificacionId(model.getTipoidentificacionId());
      data.setIdentificacion(model.getIdentificacion());
      data.setNombreLegal(model.getNombreLegal());
      data.setRazonSocial(model.getRazonSocial());
      data.setRepresentante(model.getRepresentante());
      data.setCorporacionId(model.getCorporacionId());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setEstado(model.getEstado());
      data.setTipoclienteId(model.getTipoclienteId());
      data.setTipoproveedorId(model.getTipoproveedorId());
      data.setTiponegocioId(model.getTiponegocioId());
      data.setCuentaId(model.getCuentaId());
      data.setObservacion(model.getObservacion());
      data.setUsuariofinal(model.getUsuariofinal());
      data.setContribuyenteEspecial(model.getContribuyenteEspecial());
      data.setTipoPersona(model.getTipoPersona());
      data.setLlevaContabilidad(model.getLlevaContabilidad());
      data.setInformacionAdc(model.getInformacionAdc());
      data.setRequiereSap(model.getRequiereSap());
      data.setBancoId(model.getBancoId());
      data.setTipoCuenta(model.getTipoCuenta());
      data.setNumeroCuenta(model.getNumeroCuenta());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en cliente.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en cliente.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCliente(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ClienteEJB data = manager.find(ClienteEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en cliente.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en cliente.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.ClienteIf getCliente(java.lang.Long id) {
      return (ClienteEJB)queryManagerLocal.find(ClienteEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteList() {
	  return queryManagerLocal.singleClassList(ClienteEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ClienteEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getClienteListSize() {
      Query countQuery = manager.createQuery("select count(*) from ClienteEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByTipoidentificacionId(java.lang.Long tipoidentificacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoidentificacionId", tipoidentificacionId);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByIdentificacion(java.lang.String identificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("identificacion", identificacion);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByNombreLegal(java.lang.String nombreLegal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombreLegal", nombreLegal);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByRazonSocial(java.lang.String razonSocial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("razonSocial", razonSocial);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByRepresentante(java.lang.String representante) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("representante", representante);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByCorporacionId(java.lang.Long corporacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("corporacionId", corporacionId);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByTipoclienteId(java.lang.Long tipoclienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoclienteId", tipoclienteId);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByTipoproveedorId(java.lang.Long tipoproveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoproveedorId", tipoproveedorId);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByTiponegocioId(java.lang.Long tiponegocioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tiponegocioId", tiponegocioId);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByCuentaId(java.lang.Long cuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaId", cuentaId);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByUsuariofinal(java.lang.String usuariofinal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuariofinal", usuariofinal);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByContribuyenteEspecial(java.lang.String contribuyenteEspecial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("contribuyenteEspecial", contribuyenteEspecial);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByTipoPersona(java.lang.String tipoPersona) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoPersona", tipoPersona);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByLlevaContabilidad(java.lang.String llevaContabilidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("llevaContabilidad", llevaContabilidad);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByInformacionAdc(java.lang.String informacionAdc) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("informacionAdc", informacionAdc);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByRequiereSap(java.lang.String requiereSap) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("requiereSap", requiereSap);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByBancoId(java.lang.Long bancoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bancoId", bancoId);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByTipoCuenta(java.lang.String tipoCuenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoCuenta", tipoCuenta);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByNumeroCuenta(java.lang.String numeroCuenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numeroCuenta", numeroCuenta);
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ClienteIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ClienteEJB.class, aMap);      
    }

/////////////////
}
