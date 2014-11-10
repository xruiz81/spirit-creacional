package com.spirit.facturacion.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.facturacion.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _MotivoDocumentoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_MotivoDocumentoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.MotivoDocumentoIf addMotivoDocumento(com.spirit.facturacion.entity.MotivoDocumentoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      MotivoDocumentoEJB value = new MotivoDocumentoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      value.setMulta(model.getMulta());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en motivoDocumento.", e);
			throw new GenericBusinessException(
					"Error al insertar información en motivoDocumento.");
      }
     
      return getMotivoDocumento(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveMotivoDocumento(com.spirit.facturacion.entity.MotivoDocumentoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      MotivoDocumentoEJB data = new MotivoDocumentoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
      data.setMulta(model.getMulta());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en motivoDocumento.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en motivoDocumento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteMotivoDocumento(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      MotivoDocumentoEJB data = manager.find(MotivoDocumentoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en motivoDocumento.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en motivoDocumento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.MotivoDocumentoIf getMotivoDocumento(java.lang.Long id) {
      return (MotivoDocumentoEJB)queryManagerLocal.find(MotivoDocumentoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMotivoDocumentoList() {
	  return queryManagerLocal.singleClassList(MotivoDocumentoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMotivoDocumentoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(MotivoDocumentoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getMotivoDocumentoListSize() {
      Query countQuery = manager.createQuery("select count(*) from MotivoDocumentoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMotivoDocumentoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(MotivoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMotivoDocumentoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(MotivoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMotivoDocumentoByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(MotivoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMotivoDocumentoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(MotivoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMotivoDocumentoByMulta(java.lang.String multa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("multa", multa);
		return queryManagerLocal.singleClassQueryList(MotivoDocumentoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of MotivoDocumentoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMotivoDocumentoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(MotivoDocumentoEJB.class, aMap);      
    }

/////////////////
}
