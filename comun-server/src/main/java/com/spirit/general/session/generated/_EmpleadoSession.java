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
public abstract class _EmpleadoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_EmpleadoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.EmpleadoIf addEmpleado(com.spirit.general.entity.EmpleadoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      EmpleadoEJB value = new EmpleadoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombres(model.getNombres());
      value.setApellidos(model.getApellidos());
      value.setTipoidentificacionId(model.getTipoidentificacionId());
      value.setIdentificacion(model.getIdentificacion());
      value.setEmpresaId(model.getEmpresaId());
      value.setProfesion(model.getProfesion());
      value.setDireccionDomicilio(model.getDireccionDomicilio());
      value.setTelefonoDomicilio(model.getTelefonoDomicilio());
      value.setCelular(model.getCelular());
      value.setEmailOficina(model.getEmailOficina());
      value.setDepartamentoId(model.getDepartamentoId());
      value.setJefeId(model.getJefeId());
      value.setTipoempleadoId(model.getTipoempleadoId());
      value.setExtensionOficina(model.getExtensionOficina());
      value.setNivel(model.getNivel());
      value.setEstado(model.getEstado());
      value.setOficinaId(model.getOficinaId());
      value.setTipocontratoId(model.getTipocontratoId());
      value.setBancoId(model.getBancoId());
      value.setTipoCuenta(model.getTipoCuenta());
      value.setNumeroCuenta(model.getNumeroCuenta());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en empleado.", e);
			throw new GenericBusinessException(
					"Error al insertar información en empleado.");
      }
     
      return getEmpleado(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveEmpleado(com.spirit.general.entity.EmpleadoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      EmpleadoEJB data = new EmpleadoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombres(model.getNombres());
      data.setApellidos(model.getApellidos());
      data.setTipoidentificacionId(model.getTipoidentificacionId());
      data.setIdentificacion(model.getIdentificacion());
      data.setEmpresaId(model.getEmpresaId());
      data.setProfesion(model.getProfesion());
      data.setDireccionDomicilio(model.getDireccionDomicilio());
      data.setTelefonoDomicilio(model.getTelefonoDomicilio());
      data.setCelular(model.getCelular());
      data.setEmailOficina(model.getEmailOficina());
      data.setDepartamentoId(model.getDepartamentoId());
      data.setJefeId(model.getJefeId());
      data.setTipoempleadoId(model.getTipoempleadoId());
      data.setExtensionOficina(model.getExtensionOficina());
      data.setNivel(model.getNivel());
      data.setEstado(model.getEstado());
      data.setOficinaId(model.getOficinaId());
      data.setTipocontratoId(model.getTipocontratoId());
      data.setBancoId(model.getBancoId());
      data.setTipoCuenta(model.getTipoCuenta());
      data.setNumeroCuenta(model.getNumeroCuenta());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en empleado.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en empleado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteEmpleado(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      EmpleadoEJB data = manager.find(EmpleadoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en empleado.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en empleado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.EmpleadoIf getEmpleado(java.lang.Long id) {
      return (EmpleadoEJB)queryManagerLocal.find(EmpleadoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoList() {
	  return queryManagerLocal.singleClassList(EmpleadoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(EmpleadoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getEmpleadoListSize() {
      Query countQuery = manager.createQuery("select count(*) from EmpleadoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByNombres(java.lang.String nombres) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombres", nombres);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByApellidos(java.lang.String apellidos) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("apellidos", apellidos);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByTipoidentificacionId(java.lang.Long tipoidentificacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoidentificacionId", tipoidentificacionId);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByIdentificacion(java.lang.String identificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("identificacion", identificacion);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByProfesion(java.lang.String profesion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("profesion", profesion);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByDireccionDomicilio(java.lang.String direccionDomicilio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("direccionDomicilio", direccionDomicilio);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByTelefonoDomicilio(java.lang.String telefonoDomicilio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("telefonoDomicilio", telefonoDomicilio);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByCelular(java.lang.String celular) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("celular", celular);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByEmailOficina(java.lang.String emailOficina) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("emailOficina", emailOficina);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByDepartamentoId(java.lang.Long departamentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("departamentoId", departamentoId);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByJefeId(java.lang.Long jefeId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("jefeId", jefeId);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByTipoempleadoId(java.lang.Long tipoempleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoempleadoId", tipoempleadoId);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByExtensionOficina(java.lang.String extensionOficina) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("extensionOficina", extensionOficina);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByNivel(java.lang.Integer nivel) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nivel", nivel);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByOficinaId(java.lang.Long oficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("oficinaId", oficinaId);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByTipocontratoId(java.lang.Long tipocontratoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipocontratoId", tipocontratoId);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByBancoId(java.lang.Long bancoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("bancoId", bancoId);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByTipoCuenta(java.lang.String tipoCuenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoCuenta", tipoCuenta);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByNumeroCuenta(java.lang.String numeroCuenta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numeroCuenta", numeroCuenta);
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of EmpleadoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(EmpleadoEJB.class, aMap);      
    }

/////////////////
}
