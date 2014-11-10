package com.spirit.rrhh.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.rrhh.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _EmpleadoFamiliaresSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_EmpleadoFamiliaresSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.rrhh.entity.EmpleadoFamiliaresIf addEmpleadoFamiliares(com.spirit.rrhh.entity.EmpleadoFamiliaresIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      EmpleadoFamiliaresEJB value = new EmpleadoFamiliaresEJB();
      try {
      value.setId(model.getId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setTipo(model.getTipo());
      value.setApellidos(model.getApellidos());
      value.setNombres(model.getNombres());
      value.setFechaNacimiento(model.getFechaNacimiento());
      value.setCedulaIdentidad(model.getCedulaIdentidad());
      value.setOcupacion(model.getOcupacion());
      value.setNivelEstudios(model.getNivelEstudios());
      value.setTrabaja(model.getTrabaja());
      value.setNombreInstitucion(model.getNombreInstitucion());
      value.setEmbarazo(model.getEmbarazo());
      value.setFechaParto(model.getFechaParto());
      value.setUltimoAnio(model.getUltimoAnio());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en empleadoFamiliares.", e);
			throw new GenericBusinessException(
					"Error al insertar información en empleadoFamiliares.");
      }
     
      return getEmpleadoFamiliares(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveEmpleadoFamiliares(com.spirit.rrhh.entity.EmpleadoFamiliaresIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      EmpleadoFamiliaresEJB data = new EmpleadoFamiliaresEJB();
      data.setId(model.getId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setTipo(model.getTipo());
      data.setApellidos(model.getApellidos());
      data.setNombres(model.getNombres());
      data.setFechaNacimiento(model.getFechaNacimiento());
      data.setCedulaIdentidad(model.getCedulaIdentidad());
      data.setOcupacion(model.getOcupacion());
      data.setNivelEstudios(model.getNivelEstudios());
      data.setTrabaja(model.getTrabaja());
      data.setNombreInstitucion(model.getNombreInstitucion());
      data.setEmbarazo(model.getEmbarazo());
      data.setFechaParto(model.getFechaParto());
      data.setUltimoAnio(model.getUltimoAnio());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en empleadoFamiliares.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en empleadoFamiliares.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteEmpleadoFamiliares(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      EmpleadoFamiliaresEJB data = manager.find(EmpleadoFamiliaresEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en empleadoFamiliares.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en empleadoFamiliares.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.rrhh.entity.EmpleadoFamiliaresIf getEmpleadoFamiliares(java.lang.Long id) {
      return (EmpleadoFamiliaresEJB)queryManagerLocal.find(EmpleadoFamiliaresEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoFamiliaresList() {
	  return queryManagerLocal.singleClassList(EmpleadoFamiliaresEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoFamiliaresList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(EmpleadoFamiliaresEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getEmpleadoFamiliaresListSize() {
      Query countQuery = manager.createQuery("select count(*) from EmpleadoFamiliaresEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresByTipo(java.lang.String tipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipo", tipo);
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresByApellidos(java.lang.String apellidos) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("apellidos", apellidos);
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresByNombres(java.lang.String nombres) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombres", nombres);
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresByFechaNacimiento(java.sql.Timestamp fechaNacimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaNacimiento", fechaNacimiento);
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresByCedulaIdentidad(java.lang.String cedulaIdentidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cedulaIdentidad", cedulaIdentidad);
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresByOcupacion(java.lang.String ocupacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ocupacion", ocupacion);
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresByNivelEstudios(java.lang.String nivelEstudios) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nivelEstudios", nivelEstudios);
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresByTrabaja(java.lang.String trabaja) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("trabaja", trabaja);
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresByNombreInstitucion(java.lang.String nombreInstitucion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombreInstitucion", nombreInstitucion);
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresByEmbarazo(java.lang.String embarazo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("embarazo", embarazo);
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresByFechaParto(java.sql.Timestamp fechaParto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaParto", fechaParto);
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresByUltimoAnio(java.lang.String ultimoAnio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ultimoAnio", ultimoAnio);
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of EmpleadoFamiliaresIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFamiliaresByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(EmpleadoFamiliaresEJB.class, aMap);      
    }

/////////////////
}
