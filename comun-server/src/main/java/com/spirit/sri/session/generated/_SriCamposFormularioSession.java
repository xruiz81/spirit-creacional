package com.spirit.sri.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.sri.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _SriCamposFormularioSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SriCamposFormularioSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriCamposFormularioIf addSriCamposFormulario(com.spirit.sri.entity.SriCamposFormularioIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SriCamposFormularioEJB value = new SriCamposFormularioEJB();
      try {
      value.setId(model.getId());
      value.setImpuesto(model.getImpuesto());
      value.setCodigo(model.getCodigo());
      value.setConcepto(model.getConcepto());
      value.setObservacion(model.getObservacion());
      value.setEstado(model.getEstado());
      value.setValor(model.getValor());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en sriCamposFormulario.", e);
			throw new GenericBusinessException(
					"Error al insertar información en sriCamposFormulario.");
      }
     
      return getSriCamposFormulario(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSriCamposFormulario(com.spirit.sri.entity.SriCamposFormularioIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SriCamposFormularioEJB data = new SriCamposFormularioEJB();
      data.setId(model.getId());
      data.setImpuesto(model.getImpuesto());
      data.setCodigo(model.getCodigo());
      data.setConcepto(model.getConcepto());
      data.setObservacion(model.getObservacion());
      data.setEstado(model.getEstado());
      data.setValor(model.getValor());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en sriCamposFormulario.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en sriCamposFormulario.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSriCamposFormulario(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SriCamposFormularioEJB data = manager.find(SriCamposFormularioEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en sriCamposFormulario.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en sriCamposFormulario.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriCamposFormularioIf getSriCamposFormulario(java.lang.Long id) {
      return (SriCamposFormularioEJB)queryManagerLocal.find(SriCamposFormularioEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriCamposFormularioList() {
	  return queryManagerLocal.singleClassList(SriCamposFormularioEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriCamposFormularioList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SriCamposFormularioEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSriCamposFormularioListSize() {
      Query countQuery = manager.createQuery("select count(*) from SriCamposFormularioEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriCamposFormularioById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SriCamposFormularioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriCamposFormularioByImpuesto(java.lang.String impuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("impuesto", impuesto);
		return queryManagerLocal.singleClassQueryList(SriCamposFormularioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriCamposFormularioByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(SriCamposFormularioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriCamposFormularioByConcepto(java.lang.String concepto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("concepto", concepto);
		return queryManagerLocal.singleClassQueryList(SriCamposFormularioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriCamposFormularioByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(SriCamposFormularioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriCamposFormularioByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(SriCamposFormularioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriCamposFormularioByValor(java.lang.String valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(SriCamposFormularioEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SriCamposFormularioIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriCamposFormularioByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SriCamposFormularioEJB.class, aMap);      
    }

/////////////////
}
