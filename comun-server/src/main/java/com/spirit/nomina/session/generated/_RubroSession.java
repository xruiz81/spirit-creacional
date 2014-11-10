package com.spirit.nomina.session.generated;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.RubroEJB;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _RubroSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_RubroSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.RubroIf addRubro(com.spirit.nomina.entity.RubroIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      RubroEJB value = new RubroEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setEmpresaId(model.getEmpresaId());
      value.setFrecuencia(model.getFrecuencia());
      value.setTipoRubro(model.getTipoRubro());
      value.setNombre(model.getNombre());
      value.setTiporolId(model.getTiporolId());
      value.setFecha(model.getFecha());
      value.setPolitica(model.getPolitica());
      value.setModoOperacion(model.getModoOperacion());
      value.setPagoIndividual(model.getPagoIndividual());
      value.setNemonico(model.getNemonico());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en rubro.", e);
			throw new GenericBusinessException(
					"Error al insertar información en rubro.");
      }
     
      return getRubro(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveRubro(com.spirit.nomina.entity.RubroIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      RubroEJB data = new RubroEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setEmpresaId(model.getEmpresaId());
      data.setFrecuencia(model.getFrecuencia());
      data.setTipoRubro(model.getTipoRubro());
      data.setNombre(model.getNombre());
      data.setTiporolId(model.getTiporolId());
      data.setFecha(model.getFecha());
      data.setPolitica(model.getPolitica());
      data.setModoOperacion(model.getModoOperacion());
      data.setPagoIndividual(model.getPagoIndividual());
      data.setNemonico(model.getNemonico());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en rubro.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en rubro.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteRubro(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      RubroEJB data = manager.find(RubroEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en rubro.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en rubro.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.RubroIf getRubro(java.lang.Long id) {
      return (RubroEJB)queryManagerLocal.find(RubroEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRubroList() {
	  return queryManagerLocal.singleClassList(RubroEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRubroList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(RubroEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getRubroListSize() {
      Query countQuery = manager.createQuery("select count(*) from RubroEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(RubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(RubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(RubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroByFrecuencia(java.lang.String frecuencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("frecuencia", frecuencia);
		return queryManagerLocal.singleClassQueryList(RubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroByTipoRubro(java.lang.String tipoRubro) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoRubro", tipoRubro);
		return queryManagerLocal.singleClassQueryList(RubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(RubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroByTiporolId(java.lang.Long tiporolId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tiporolId", tiporolId);
		return queryManagerLocal.singleClassQueryList(RubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(RubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroByPolitica(java.lang.String politica) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("politica", politica);
		return queryManagerLocal.singleClassQueryList(RubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroByModoOperacion(java.lang.String modoOperacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("modoOperacion", modoOperacion);
		return queryManagerLocal.singleClassQueryList(RubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroByPagoIndividual(java.lang.String pagoIndividual) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pagoIndividual", pagoIndividual);
		return queryManagerLocal.singleClassQueryList(RubroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroByNemonico(java.lang.String nemonico) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nemonico", nemonico);
		return queryManagerLocal.singleClassQueryList(RubroEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of RubroIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRubroByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(RubroEJB.class, aMap);      
    }

/////////////////
}
