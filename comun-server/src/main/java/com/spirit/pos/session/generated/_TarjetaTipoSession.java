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
public abstract class _TarjetaTipoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TarjetaTipoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.TarjetaTipoIf addTarjetaTipo(com.spirit.pos.entity.TarjetaTipoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TarjetaTipoEJB value = new TarjetaTipoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setPadreId(model.getPadreId());
      value.setPuntosDinero(model.getPuntosDinero());
      value.setDsctoReferido(model.getDsctoReferido());
      value.setDsctoPropietario(model.getDsctoPropietario());
      value.setPorcentajeDineroReferido(model.getPorcentajeDineroReferido());
      value.setPorcentajeDineroPropietario(model.getPorcentajeDineroPropietario());
      value.setStatusSiguiente(model.getStatusSiguiente());
      value.setStatusAnterior(model.getStatusAnterior());
      value.setPuntosSubirStatus(model.getPuntosSubirStatus());
      value.setDineroSubirStatus(model.getDineroSubirStatus());
      value.setPuntosMantenerStatus(model.getPuntosMantenerStatus());
      value.setDineroMantenerStatus(model.getDineroMantenerStatus());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tarjetaTipo.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tarjetaTipo.");
      }
     
      return getTarjetaTipo(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTarjetaTipo(com.spirit.pos.entity.TarjetaTipoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TarjetaTipoEJB data = new TarjetaTipoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setPadreId(model.getPadreId());
      data.setPuntosDinero(model.getPuntosDinero());
      data.setDsctoReferido(model.getDsctoReferido());
      data.setDsctoPropietario(model.getDsctoPropietario());
      data.setPorcentajeDineroReferido(model.getPorcentajeDineroReferido());
      data.setPorcentajeDineroPropietario(model.getPorcentajeDineroPropietario());
      data.setStatusSiguiente(model.getStatusSiguiente());
      data.setStatusAnterior(model.getStatusAnterior());
      data.setPuntosSubirStatus(model.getPuntosSubirStatus());
      data.setDineroSubirStatus(model.getDineroSubirStatus());
      data.setPuntosMantenerStatus(model.getPuntosMantenerStatus());
      data.setDineroMantenerStatus(model.getDineroMantenerStatus());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tarjetaTipo.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tarjetaTipo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTarjetaTipo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TarjetaTipoEJB data = manager.find(TarjetaTipoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tarjetaTipo.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tarjetaTipo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.TarjetaTipoIf getTarjetaTipo(java.lang.Long id) {
      return (TarjetaTipoEJB)queryManagerLocal.find(TarjetaTipoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTarjetaTipoList() {
	  return queryManagerLocal.singleClassList(TarjetaTipoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTarjetaTipoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TarjetaTipoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTarjetaTipoListSize() {
      Query countQuery = manager.createQuery("select count(*) from TarjetaTipoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByPadreId(java.lang.Long padreId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("padreId", padreId);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByPuntosDinero(java.lang.String puntosDinero) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntosDinero", puntosDinero);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByDsctoReferido(java.math.BigDecimal dsctoReferido) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("dsctoReferido", dsctoReferido);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByDsctoPropietario(java.math.BigDecimal dsctoPropietario) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("dsctoPropietario", dsctoPropietario);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByPorcentajeDineroReferido(java.math.BigDecimal porcentajeDineroReferido) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDineroReferido", porcentajeDineroReferido);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByPorcentajeDineroPropietario(java.math.BigDecimal porcentajeDineroPropietario) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("porcentajeDineroPropietario", porcentajeDineroPropietario);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByStatusSiguiente(java.lang.Long statusSiguiente) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("statusSiguiente", statusSiguiente);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByStatusAnterior(java.lang.Long statusAnterior) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("statusAnterior", statusAnterior);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByPuntosSubirStatus(java.math.BigDecimal puntosSubirStatus) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntosSubirStatus", puntosSubirStatus);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByDineroSubirStatus(java.math.BigDecimal dineroSubirStatus) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("dineroSubirStatus", dineroSubirStatus);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByPuntosMantenerStatus(java.math.BigDecimal puntosMantenerStatus) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntosMantenerStatus", puntosMantenerStatus);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByDineroMantenerStatus(java.math.BigDecimal dineroMantenerStatus) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("dineroMantenerStatus", dineroMantenerStatus);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TarjetaTipoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaTipoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TarjetaTipoEJB.class, aMap);      
    }

/////////////////
}
