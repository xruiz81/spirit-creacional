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
public abstract class _EmpleadoPersonalSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_EmpleadoPersonalSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.rrhh.entity.EmpleadoPersonalIf addEmpleadoPersonal(com.spirit.rrhh.entity.EmpleadoPersonalIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      EmpleadoPersonalEJB value = new EmpleadoPersonalEJB();
      try {
      value.setId(model.getId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setTitulo(model.getTitulo());
      value.setCedulaIdentidad(model.getCedulaIdentidad());
      value.setAfiliacionIess(model.getAfiliacionIess());
      value.setLibretaMilitar(model.getLibretaMilitar());
      value.setSexo(model.getSexo());
      value.setTipoSangre(model.getTipoSangre());
      value.setEstadoCivil(model.getEstadoCivil());
      value.setFechaNacimiento(model.getFechaNacimiento());
      value.setCiudadId(model.getCiudadId());
      value.setCanton(model.getCanton());
      value.setParroquia(model.getParroquia());
      value.setCasoEmergencia(model.getCasoEmergencia());
      value.setTelefonoEmergencia(model.getTelefonoEmergencia());
      value.setDireccionEmergencia(model.getDireccionEmergencia());
      value.setCiudadEmergenciaId(model.getCiudadEmergenciaId());
      value.setTallaCamisa(model.getTallaCamisa());
      value.setTallaPantalon(model.getTallaPantalon());
      value.setNumeroCalzado(model.getNumeroCalzado());
      value.setEstatura(model.getEstatura());
      value.setPeso(model.getPeso());
      value.setColorPelo(model.getColorPelo());
      value.setColorOjos(model.getColorOjos());
      value.setColorPiel(model.getColorPiel());
      value.setSenasParticulares(model.getSenasParticulares());
      value.setExestudianteCtt(model.getExestudianteCtt());
      value.setPaisId(model.getPaisId());
      value.setProvinciaId(model.getProvinciaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en empleadoPersonal.", e);
			throw new GenericBusinessException(
					"Error al insertar información en empleadoPersonal.");
      }
     
      return getEmpleadoPersonal(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveEmpleadoPersonal(com.spirit.rrhh.entity.EmpleadoPersonalIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      EmpleadoPersonalEJB data = new EmpleadoPersonalEJB();
      data.setId(model.getId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setTitulo(model.getTitulo());
      data.setCedulaIdentidad(model.getCedulaIdentidad());
      data.setAfiliacionIess(model.getAfiliacionIess());
      data.setLibretaMilitar(model.getLibretaMilitar());
      data.setSexo(model.getSexo());
      data.setTipoSangre(model.getTipoSangre());
      data.setEstadoCivil(model.getEstadoCivil());
      data.setFechaNacimiento(model.getFechaNacimiento());
      data.setCiudadId(model.getCiudadId());
      data.setCanton(model.getCanton());
      data.setParroquia(model.getParroquia());
      data.setCasoEmergencia(model.getCasoEmergencia());
      data.setTelefonoEmergencia(model.getTelefonoEmergencia());
      data.setDireccionEmergencia(model.getDireccionEmergencia());
      data.setCiudadEmergenciaId(model.getCiudadEmergenciaId());
      data.setTallaCamisa(model.getTallaCamisa());
      data.setTallaPantalon(model.getTallaPantalon());
      data.setNumeroCalzado(model.getNumeroCalzado());
      data.setEstatura(model.getEstatura());
      data.setPeso(model.getPeso());
      data.setColorPelo(model.getColorPelo());
      data.setColorOjos(model.getColorOjos());
      data.setColorPiel(model.getColorPiel());
      data.setSenasParticulares(model.getSenasParticulares());
      data.setExestudianteCtt(model.getExestudianteCtt());
      data.setPaisId(model.getPaisId());
      data.setProvinciaId(model.getProvinciaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en empleadoPersonal.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en empleadoPersonal.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteEmpleadoPersonal(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      EmpleadoPersonalEJB data = manager.find(EmpleadoPersonalEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en empleadoPersonal.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en empleadoPersonal.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.rrhh.entity.EmpleadoPersonalIf getEmpleadoPersonal(java.lang.Long id) {
      return (EmpleadoPersonalEJB)queryManagerLocal.find(EmpleadoPersonalEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoPersonalList() {
	  return queryManagerLocal.singleClassList(EmpleadoPersonalEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoPersonalList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(EmpleadoPersonalEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getEmpleadoPersonalListSize() {
      Query countQuery = manager.createQuery("select count(*) from EmpleadoPersonalEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByTitulo(java.lang.String titulo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("titulo", titulo);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByCedulaIdentidad(java.lang.String cedulaIdentidad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cedulaIdentidad", cedulaIdentidad);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByAfiliacionIess(java.lang.String afiliacionIess) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("afiliacionIess", afiliacionIess);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByLibretaMilitar(java.lang.String libretaMilitar) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("libretaMilitar", libretaMilitar);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalBySexo(java.lang.String sexo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("sexo", sexo);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByTipoSangre(java.lang.String tipoSangre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoSangre", tipoSangre);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByEstadoCivil(java.lang.String estadoCivil) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estadoCivil", estadoCivil);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByFechaNacimiento(java.sql.Timestamp fechaNacimiento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaNacimiento", fechaNacimiento);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByCiudadId(java.lang.Long ciudadId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ciudadId", ciudadId);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByCanton(java.lang.String canton) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("canton", canton);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByParroquia(java.lang.String parroquia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("parroquia", parroquia);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByCasoEmergencia(java.lang.String casoEmergencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("casoEmergencia", casoEmergencia);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByTelefonoEmergencia(java.lang.String telefonoEmergencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("telefonoEmergencia", telefonoEmergencia);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByDireccionEmergencia(java.lang.String direccionEmergencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("direccionEmergencia", direccionEmergencia);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByCiudadEmergenciaId(java.lang.Long ciudadEmergenciaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ciudadEmergenciaId", ciudadEmergenciaId);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByTallaCamisa(java.lang.String tallaCamisa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tallaCamisa", tallaCamisa);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByTallaPantalon(java.lang.String tallaPantalon) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tallaPantalon", tallaPantalon);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByNumeroCalzado(java.lang.String numeroCalzado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numeroCalzado", numeroCalzado);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByEstatura(java.lang.String estatura) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estatura", estatura);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByPeso(java.lang.String peso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("peso", peso);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByColorPelo(java.lang.String colorPelo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("colorPelo", colorPelo);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByColorOjos(java.lang.String colorOjos) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("colorOjos", colorOjos);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByColorPiel(java.lang.String colorPiel) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("colorPiel", colorPiel);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalBySenasParticulares(java.lang.String senasParticulares) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("senasParticulares", senasParticulares);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByExestudianteCtt(java.lang.String exestudianteCtt) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("exestudianteCtt", exestudianteCtt);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByPaisId(java.lang.Long paisId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("paisId", paisId);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByProvinciaId(java.lang.Long provinciaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("provinciaId", provinciaId);
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of EmpleadoPersonalIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoPersonalByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(EmpleadoPersonalEJB.class, aMap);      
    }

/////////////////
}
