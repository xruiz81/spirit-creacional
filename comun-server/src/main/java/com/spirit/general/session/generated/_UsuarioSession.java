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
public abstract class _UsuarioSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_UsuarioSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.UsuarioIf addUsuario(com.spirit.general.entity.UsuarioIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      UsuarioEJB value = new UsuarioEJB();
      try {
      value.setId(model.getId());
      value.setUsuario(model.getUsuario());
      value.setClave(model.getClave());
      value.setTipousuario(model.getTipousuario());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setEmpresaId(model.getEmpresaId());
      value.setTipousuarioTimetracker(model.getTipousuarioTimetracker());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en usuario.", e);
			throw new GenericBusinessException(
					"Error al insertar información en usuario.");
      }
     
      return getUsuario(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveUsuario(com.spirit.general.entity.UsuarioIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      UsuarioEJB data = new UsuarioEJB();
      data.setId(model.getId());
      data.setUsuario(model.getUsuario());
      data.setClave(model.getClave());
      data.setTipousuario(model.getTipousuario());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setEmpresaId(model.getEmpresaId());
      data.setTipousuarioTimetracker(model.getTipousuarioTimetracker());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en usuario.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en usuario.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteUsuario(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      UsuarioEJB data = manager.find(UsuarioEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en usuario.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en usuario.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.UsuarioIf getUsuario(java.lang.Long id) {
      return (UsuarioEJB)queryManagerLocal.find(UsuarioEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getUsuarioList() {
	  return queryManagerLocal.singleClassList(UsuarioEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getUsuarioList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(UsuarioEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getUsuarioListSize() {
      Query countQuery = manager.createQuery("select count(*) from UsuarioEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(UsuarioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioByUsuario(java.lang.String usuario) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuario", usuario);
		return queryManagerLocal.singleClassQueryList(UsuarioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioByClave(java.lang.String clave) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clave", clave);
		return queryManagerLocal.singleClassQueryList(UsuarioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioByTipousuario(java.lang.String tipousuario) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipousuario", tipousuario);
		return queryManagerLocal.singleClassQueryList(UsuarioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(UsuarioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(UsuarioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioByTipousuarioTimetracker(java.lang.String tipousuarioTimetracker) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipousuarioTimetracker", tipousuarioTimetracker);
		return queryManagerLocal.singleClassQueryList(UsuarioEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of UsuarioIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(UsuarioEJB.class, aMap);      
    }

/////////////////
}
