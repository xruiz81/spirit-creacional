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
public abstract class _EmpresaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_EmpresaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.EmpresaIf addEmpresa(com.spirit.general.entity.EmpresaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      EmpresaEJB value = new EmpresaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setLogo(model.getLogo());
      value.setRuc(model.getRuc());
      value.setWeb(model.getWeb());
      value.setEmailContador(model.getEmailContador());
      value.setTipoIdRepresentante(model.getTipoIdRepresentante());
      value.setNumeroIdentificacion(model.getNumeroIdentificacion());
      value.setRucContador(model.getRucContador());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en empresa.", e);
			throw new GenericBusinessException(
					"Error al insertar información en empresa.");
      }
     
      return getEmpresa(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveEmpresa(com.spirit.general.entity.EmpresaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      EmpresaEJB data = new EmpresaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setLogo(model.getLogo());
      data.setRuc(model.getRuc());
      data.setWeb(model.getWeb());
      data.setEmailContador(model.getEmailContador());
      data.setTipoIdRepresentante(model.getTipoIdRepresentante());
      data.setNumeroIdentificacion(model.getNumeroIdentificacion());
      data.setRucContador(model.getRucContador());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en empresa.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en empresa.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteEmpresa(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      EmpresaEJB data = manager.find(EmpresaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en empresa.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en empresa.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.EmpresaIf getEmpresa(java.lang.Long id) {
      return (EmpresaEJB)queryManagerLocal.find(EmpresaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpresaList() {
	  return queryManagerLocal.singleClassList(EmpresaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpresaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(EmpresaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getEmpresaListSize() {
      Query countQuery = manager.createQuery("select count(*) from EmpresaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpresaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(EmpresaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpresaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(EmpresaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpresaByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(EmpresaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpresaByLogo(java.lang.String logo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("logo", logo);
		return queryManagerLocal.singleClassQueryList(EmpresaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpresaByRuc(java.lang.String ruc) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ruc", ruc);
		return queryManagerLocal.singleClassQueryList(EmpresaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpresaByWeb(java.lang.String web) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("web", web);
		return queryManagerLocal.singleClassQueryList(EmpresaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpresaByEmailContador(java.lang.String emailContador) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("emailContador", emailContador);
		return queryManagerLocal.singleClassQueryList(EmpresaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpresaByTipoIdRepresentante(java.lang.Long tipoIdRepresentante) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoIdRepresentante", tipoIdRepresentante);
		return queryManagerLocal.singleClassQueryList(EmpresaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpresaByNumeroIdentificacion(java.lang.String numeroIdentificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numeroIdentificacion", numeroIdentificacion);
		return queryManagerLocal.singleClassQueryList(EmpresaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpresaByRucContador(java.lang.String rucContador) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rucContador", rucContador);
		return queryManagerLocal.singleClassQueryList(EmpresaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of EmpresaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpresaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(EmpresaEJB.class, aMap);      
    }

/////////////////
}
