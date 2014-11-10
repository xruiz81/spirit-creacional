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
public abstract class _ClienteOficinaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ClienteOficinaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.ClienteOficinaIf addClienteOficina(com.spirit.crm.entity.ClienteOficinaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ClienteOficinaEJB value = new ClienteOficinaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setClienteId(model.getClienteId());
      value.setCiudadId(model.getCiudadId());
      value.setDireccion(model.getDireccion());
      value.setTelefono(model.getTelefono());
      value.setFax(model.getFax());
      value.setEmail(model.getEmail());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setMontoCredito(model.getMontoCredito());
      value.setMontoGarantia(model.getMontoGarantia());
      value.setCalificacion(model.getCalificacion());
      value.setObservacion(model.getObservacion());
      value.setEstado(model.getEstado());
      value.setDescripcion(model.getDescripcion());
      value.setCodigoProveedorAuto(model.getCodigoProveedorAuto());
      value.setVendedorId(model.getVendedorId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en clienteOficina.", e);
			throw new GenericBusinessException(
					"Error al insertar información en clienteOficina.");
      }
     
      return getClienteOficina(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveClienteOficina(com.spirit.crm.entity.ClienteOficinaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ClienteOficinaEJB data = new ClienteOficinaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setClienteId(model.getClienteId());
      data.setCiudadId(model.getCiudadId());
      data.setDireccion(model.getDireccion());
      data.setTelefono(model.getTelefono());
      data.setFax(model.getFax());
      data.setEmail(model.getEmail());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setMontoCredito(model.getMontoCredito());
      data.setMontoGarantia(model.getMontoGarantia());
      data.setCalificacion(model.getCalificacion());
      data.setObservacion(model.getObservacion());
      data.setEstado(model.getEstado());
      data.setDescripcion(model.getDescripcion());
      data.setCodigoProveedorAuto(model.getCodigoProveedorAuto());
      data.setVendedorId(model.getVendedorId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en clienteOficina.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en clienteOficina.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteClienteOficina(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ClienteOficinaEJB data = manager.find(ClienteOficinaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en clienteOficina.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en clienteOficina.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.ClienteOficinaIf getClienteOficina(java.lang.Long id) {
      return (ClienteOficinaEJB)queryManagerLocal.find(ClienteOficinaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteOficinaList() {
	  return queryManagerLocal.singleClassList(ClienteOficinaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getClienteOficinaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ClienteOficinaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getClienteOficinaListSize() {
      Query countQuery = manager.createQuery("select count(*) from ClienteOficinaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByCiudadId(java.lang.Long ciudadId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ciudadId", ciudadId);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByDireccion(java.lang.String direccion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("direccion", direccion);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByTelefono(java.lang.String telefono) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("telefono", telefono);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByFax(java.lang.String fax) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fax", fax);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByEmail(java.lang.String email) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("email", email);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByMontoCredito(java.math.BigDecimal montoCredito) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("montoCredito", montoCredito);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByMontoGarantia(java.math.BigDecimal montoGarantia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("montoGarantia", montoGarantia);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByCalificacion(java.lang.String calificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("calificacion", calificacion);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByCodigoProveedorAuto(java.lang.String codigoProveedorAuto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigoProveedorAuto", codigoProveedorAuto);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByVendedorId(java.lang.Long vendedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("vendedorId", vendedorId);
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ClienteOficinaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findClienteOficinaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ClienteOficinaEJB.class, aMap);      
    }

/////////////////
}
