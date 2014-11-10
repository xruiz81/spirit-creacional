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
public abstract class _PuntoImpresionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PuntoImpresionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.PuntoImpresionIf addPuntoImpresion(com.spirit.general.entity.PuntoImpresionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PuntoImpresionEJB value = new PuntoImpresionEJB();
      try {
      value.setId(model.getId());
      value.setTipodocumentoId(model.getTipodocumentoId());
      value.setCajaId(model.getCajaId());
      value.setSerie(model.getSerie());
      value.setImpresora(model.getImpresora());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en puntoImpresion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en puntoImpresion.");
      }
     
      return getPuntoImpresion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePuntoImpresion(com.spirit.general.entity.PuntoImpresionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PuntoImpresionEJB data = new PuntoImpresionEJB();
      data.setId(model.getId());
      data.setTipodocumentoId(model.getTipodocumentoId());
      data.setCajaId(model.getCajaId());
      data.setSerie(model.getSerie());
      data.setImpresora(model.getImpresora());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en puntoImpresion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en puntoImpresion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePuntoImpresion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PuntoImpresionEJB data = manager.find(PuntoImpresionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en puntoImpresion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en puntoImpresion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.PuntoImpresionIf getPuntoImpresion(java.lang.Long id) {
      return (PuntoImpresionEJB)queryManagerLocal.find(PuntoImpresionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPuntoImpresionList() {
	  return queryManagerLocal.singleClassList(PuntoImpresionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPuntoImpresionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PuntoImpresionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPuntoImpresionListSize() {
      Query countQuery = manager.createQuery("select count(*) from PuntoImpresionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntoImpresionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PuntoImpresionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntoImpresionByTipodocumentoId(java.lang.Long tipodocumentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipodocumentoId", tipodocumentoId);
		return queryManagerLocal.singleClassQueryList(PuntoImpresionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntoImpresionByCajaId(java.lang.Long cajaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cajaId", cajaId);
		return queryManagerLocal.singleClassQueryList(PuntoImpresionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntoImpresionBySerie(java.lang.String serie) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("serie", serie);
		return queryManagerLocal.singleClassQueryList(PuntoImpresionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntoImpresionByImpresora(java.lang.String impresora) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("impresora", impresora);
		return queryManagerLocal.singleClassQueryList(PuntoImpresionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntoImpresionByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(PuntoImpresionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PuntoImpresionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPuntoImpresionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PuntoImpresionEJB.class, aMap);      
    }

/////////////////
}
