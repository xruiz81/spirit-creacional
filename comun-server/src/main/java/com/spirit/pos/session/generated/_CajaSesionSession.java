package com.spirit.pos.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.pos.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _CajaSesionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CajaSesionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.CajaSesionIf addCajaSesion(com.spirit.pos.entity.CajaSesionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CajaSesionEJB value = new CajaSesionEJB();
      try {
      value.setId(model.getId());
      value.setUsuarioId(model.getUsuarioId());
      value.setCajaId(model.getCajaId());
      value.setFechaini(model.getFechaini());
      value.setFechafin(model.getFechafin());
      value.setValorInicial(model.getValorInicial());
      value.setValorFinal(model.getValorFinal());
      value.setDescuadre(model.getDescuadre());
      value.setValorMultas(model.getValorMultas());
      value.setValorDocumentos(model.getValorDocumentos());
      value.setTurno(model.getTurno());
      value.setEstado(model.getEstado());
      value.setRevisado(model.getRevisado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en cajaSesion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en cajaSesion.");
      }
     
      return getCajaSesion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCajaSesion(com.spirit.pos.entity.CajaSesionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CajaSesionEJB data = new CajaSesionEJB();
      data.setId(model.getId());
      data.setUsuarioId(model.getUsuarioId());
      data.setCajaId(model.getCajaId());
      data.setFechaini(model.getFechaini());
      data.setFechafin(model.getFechafin());
      data.setValorInicial(model.getValorInicial());
      data.setValorFinal(model.getValorFinal());
      data.setDescuadre(model.getDescuadre());
      data.setValorMultas(model.getValorMultas());
      data.setValorDocumentos(model.getValorDocumentos());
      data.setTurno(model.getTurno());
      data.setEstado(model.getEstado());
      data.setRevisado(model.getRevisado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en cajaSesion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en cajaSesion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCajaSesion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CajaSesionEJB data = manager.find(CajaSesionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en cajaSesion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en cajaSesion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.CajaSesionIf getCajaSesion(java.lang.Long id) {
      return (CajaSesionEJB)queryManagerLocal.find(CajaSesionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCajaSesionList() {
	  return queryManagerLocal.singleClassList(CajaSesionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCajaSesionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CajaSesionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCajaSesionListSize() {
      Query countQuery = manager.createQuery("select count(*) from CajaSesionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajaSesionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CajaSesionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajaSesionByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(CajaSesionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajaSesionByCajaId(java.lang.Long cajaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cajaId", cajaId);
		return queryManagerLocal.singleClassQueryList(CajaSesionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajaSesionByFechaini(java.sql.Timestamp fechaini) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaini", fechaini);
		return queryManagerLocal.singleClassQueryList(CajaSesionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajaSesionByFechafin(java.sql.Timestamp fechafin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechafin", fechafin);
		return queryManagerLocal.singleClassQueryList(CajaSesionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajaSesionByValorInicial(java.math.BigDecimal valorInicial) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorInicial", valorInicial);
		return queryManagerLocal.singleClassQueryList(CajaSesionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajaSesionByValorFinal(java.math.BigDecimal valorFinal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorFinal", valorFinal);
		return queryManagerLocal.singleClassQueryList(CajaSesionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajaSesionByDescuadre(java.math.BigDecimal descuadre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuadre", descuadre);
		return queryManagerLocal.singleClassQueryList(CajaSesionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajaSesionByValorMultas(java.math.BigDecimal valorMultas) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorMultas", valorMultas);
		return queryManagerLocal.singleClassQueryList(CajaSesionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajaSesionByValorDocumentos(java.math.BigDecimal valorDocumentos) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorDocumentos", valorDocumentos);
		return queryManagerLocal.singleClassQueryList(CajaSesionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajaSesionByTurno(java.lang.String turno) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("turno", turno);
		return queryManagerLocal.singleClassQueryList(CajaSesionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajaSesionByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(CajaSesionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajaSesionByRevisado(java.lang.String revisado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("revisado", revisado);
		return queryManagerLocal.singleClassQueryList(CajaSesionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CajaSesionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCajaSesionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CajaSesionEJB.class, aMap);      
    }

/////////////////
}
