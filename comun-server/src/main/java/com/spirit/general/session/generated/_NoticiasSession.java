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
public abstract class _NoticiasSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_NoticiasSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.NoticiasIf addNoticias(com.spirit.general.entity.NoticiasIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      NoticiasEJB value = new NoticiasEJB();
      try {
      value.setId(model.getId());
      value.setEmpresaId(model.getEmpresaId());
      value.setUsuarioId(model.getUsuarioId());
      value.setFechaIni(model.getFechaIni());
      value.setFechaFin(model.getFechaFin());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setStatus(model.getStatus());
      value.setNoticia(model.getNoticia());
      value.setArchivo(model.getArchivo());
      value.setAsunto(model.getAsunto());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en noticias.", e);
			throw new GenericBusinessException(
					"Error al insertar información en noticias.");
      }
     
      return getNoticias(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveNoticias(com.spirit.general.entity.NoticiasIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      NoticiasEJB data = new NoticiasEJB();
      data.setId(model.getId());
      data.setEmpresaId(model.getEmpresaId());
      data.setUsuarioId(model.getUsuarioId());
      data.setFechaIni(model.getFechaIni());
      data.setFechaFin(model.getFechaFin());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setStatus(model.getStatus());
      data.setNoticia(model.getNoticia());
      data.setArchivo(model.getArchivo());
      data.setAsunto(model.getAsunto());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en noticias.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en noticias.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteNoticias(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      NoticiasEJB data = manager.find(NoticiasEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en noticias.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en noticias.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.NoticiasIf getNoticias(java.lang.Long id) {
      return (NoticiasEJB)queryManagerLocal.find(NoticiasEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getNoticiasList() {
	  return queryManagerLocal.singleClassList(NoticiasEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getNoticiasList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(NoticiasEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getNoticiasListSize() {
      Query countQuery = manager.createQuery("select count(*) from NoticiasEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNoticiasById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(NoticiasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNoticiasByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(NoticiasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNoticiasByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(NoticiasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNoticiasByFechaIni(java.sql.Date fechaIni) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaIni", fechaIni);
		return queryManagerLocal.singleClassQueryList(NoticiasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNoticiasByFechaFin(java.sql.Date fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(NoticiasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNoticiasByFechaCreacion(java.sql.Date fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(NoticiasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNoticiasByStatus(java.lang.String status) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("status", status);
		return queryManagerLocal.singleClassQueryList(NoticiasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNoticiasByNoticia(java.lang.String noticia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("noticia", noticia);
		return queryManagerLocal.singleClassQueryList(NoticiasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNoticiasByArchivo(java.lang.String archivo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("archivo", archivo);
		return queryManagerLocal.singleClassQueryList(NoticiasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNoticiasByAsunto(java.lang.String asunto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("asunto", asunto);
		return queryManagerLocal.singleClassQueryList(NoticiasEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of NoticiasIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findNoticiasByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(NoticiasEJB.class, aMap);      
    }

/////////////////
}
