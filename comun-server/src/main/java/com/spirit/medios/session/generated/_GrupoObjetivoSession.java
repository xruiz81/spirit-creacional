package com.spirit.medios.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.medios.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _GrupoObjetivoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_GrupoObjetivoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.GrupoObjetivoIf addGrupoObjetivo(com.spirit.medios.entity.GrupoObjetivoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      GrupoObjetivoEJB value = new GrupoObjetivoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      value.setNivelSocioEconomico(model.getNivelSocioEconomico());
      value.setCiudad1(model.getCiudad1());
      value.setCiudad2(model.getCiudad2());
      value.setCiudad3(model.getCiudad3());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en grupoObjetivo.", e);
			throw new GenericBusinessException(
					"Error al insertar información en grupoObjetivo.");
      }
     
      return getGrupoObjetivo(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveGrupoObjetivo(com.spirit.medios.entity.GrupoObjetivoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      GrupoObjetivoEJB data = new GrupoObjetivoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
      data.setNivelSocioEconomico(model.getNivelSocioEconomico());
      data.setCiudad1(model.getCiudad1());
      data.setCiudad2(model.getCiudad2());
      data.setCiudad3(model.getCiudad3());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en grupoObjetivo.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en grupoObjetivo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteGrupoObjetivo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      GrupoObjetivoEJB data = manager.find(GrupoObjetivoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en grupoObjetivo.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en grupoObjetivo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.GrupoObjetivoIf getGrupoObjetivo(java.lang.Long id) {
      return (GrupoObjetivoEJB)queryManagerLocal.find(GrupoObjetivoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGrupoObjetivoList() {
	  return queryManagerLocal.singleClassList(GrupoObjetivoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGrupoObjetivoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(GrupoObjetivoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getGrupoObjetivoListSize() {
      Query countQuery = manager.createQuery("select count(*) from GrupoObjetivoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGrupoObjetivoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(GrupoObjetivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGrupoObjetivoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(GrupoObjetivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGrupoObjetivoByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(GrupoObjetivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGrupoObjetivoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(GrupoObjetivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGrupoObjetivoByNivelSocioEconomico(java.lang.String nivelSocioEconomico) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nivelSocioEconomico", nivelSocioEconomico);
		return queryManagerLocal.singleClassQueryList(GrupoObjetivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGrupoObjetivoByCiudad1(java.math.BigDecimal ciudad1) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ciudad1", ciudad1);
		return queryManagerLocal.singleClassQueryList(GrupoObjetivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGrupoObjetivoByCiudad2(java.math.BigDecimal ciudad2) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ciudad2", ciudad2);
		return queryManagerLocal.singleClassQueryList(GrupoObjetivoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGrupoObjetivoByCiudad3(java.math.BigDecimal ciudad3) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ciudad3", ciudad3);
		return queryManagerLocal.singleClassQueryList(GrupoObjetivoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of GrupoObjetivoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGrupoObjetivoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(GrupoObjetivoEJB.class, aMap);      
    }

/////////////////
}
