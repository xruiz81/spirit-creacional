package com.spirit.contabilidad.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.contabilidad.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _CuentaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CuentaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.CuentaIf addCuenta(com.spirit.contabilidad.entity.CuentaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CuentaEJB value = new CuentaEJB();
      try {
      value.setId(model.getId());
      value.setPlancuentaId(model.getPlancuentaId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setNombreCorto(model.getNombreCorto());
      value.setTipocuentaId(model.getTipocuentaId());
      value.setPadreId(model.getPadreId());
      value.setRelacionada(model.getRelacionada());
      value.setImputable(model.getImputable());
      value.setNivel(model.getNivel());
      value.setTiporesultadoId(model.getTiporesultadoId());
      value.setEfectivo(model.getEfectivo());
      value.setActivofijo(model.getActivofijo());
      value.setDepartamento(model.getDepartamento());
      value.setLinea(model.getLinea());
      value.setEmpleado(model.getEmpleado());
      value.setCentrogasto(model.getCentrogasto());
      value.setCliente(model.getCliente());
      value.setGasto(model.getGasto());
      value.setEstado(model.getEstado());
      value.setCuentaBanco(model.getCuentaBanco());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en cuenta.", e);
			throw new GenericBusinessException(
					"Error al insertar información en cuenta.");
      }
     
      return getCuenta(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCuenta(com.spirit.contabilidad.entity.CuentaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CuentaEJB data = new CuentaEJB();
      data.setId(model.getId());
      data.setPlancuentaId(model.getPlancuentaId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setNombreCorto(model.getNombreCorto());
      data.setTipocuentaId(model.getTipocuentaId());
      data.setPadreId(model.getPadreId());
      data.setRelacionada(model.getRelacionada());
      data.setImputable(model.getImputable());
      data.setNivel(model.getNivel());
      data.setTiporesultadoId(model.getTiporesultadoId());
      data.setEfectivo(model.getEfectivo());
      data.setActivofijo(model.getActivofijo());
      data.setDepartamento(model.getDepartamento());
      data.setLinea(model.getLinea());
      data.setEmpleado(model.getEmpleado());
      data.setCentrogasto(model.getCentrogasto());
      data.setCliente(model.getCliente());
      data.setGasto(model.getGasto());
      data.setEstado(model.getEstado());
      data.setCuentaBanco(model.getCuentaBanco());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en cuenta.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en cuenta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCuenta(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CuentaEJB data = manager.find(CuentaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en cuenta.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en cuenta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.CuentaIf getCuenta(java.lang.Long id) {
      return (CuentaEJB)queryManagerLocal.find(CuentaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCuentaList() {
	  return queryManagerLocal.singleClassList(CuentaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCuentaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CuentaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCuentaListSize() {
      Query countQuery = manager.createQuery("select count(*) from CuentaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByPlancuentaId(java.lang.Long plancuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("plancuentaId", plancuentaId);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByNombreCorto(java.lang.String nombreCorto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombreCorto", nombreCorto);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByTipocuentaId(java.lang.Long tipocuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipocuentaId", tipocuentaId);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByPadreId(java.lang.Long padreId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("padreId", padreId);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByRelacionada(java.lang.Long relacionada) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("relacionada", relacionada);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByImputable(java.lang.String imputable) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("imputable", imputable);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByNivel(java.lang.Integer nivel) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nivel", nivel);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByTiporesultadoId(java.lang.Long tiporesultadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tiporesultadoId", tiporesultadoId);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByEfectivo(java.lang.String efectivo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("efectivo", efectivo);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByActivofijo(java.lang.String activofijo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("activofijo", activofijo);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByDepartamento(java.lang.String departamento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("departamento", departamento);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByLinea(java.lang.String linea) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("linea", linea);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByEmpleado(java.lang.String empleado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleado", empleado);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByCentrogasto(java.lang.String centrogasto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("centrogasto", centrogasto);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByCliente(java.lang.String cliente) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cliente", cliente);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByGasto(java.lang.String gasto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("gasto", gasto);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByCuentaBanco(java.lang.String cuentaBanco) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaBanco", cuentaBanco);
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CuentaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCuentaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CuentaEJB.class, aMap);      
    }

/////////////////
}
