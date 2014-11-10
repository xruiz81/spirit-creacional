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
public abstract class _TarjetaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TarjetaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.TarjetaIf addTarjeta(com.spirit.pos.entity.TarjetaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TarjetaEJB value = new TarjetaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setTipoId(model.getTipoId());
      value.setClienteoficinaId(model.getClienteoficinaId());
      value.setReferidoporId(model.getReferidoporId());
      value.setGarante(model.getGarante());
      value.setFechaValidez(model.getFechaValidez());
      value.setFechaEmision(model.getFechaEmision());
      value.setPuntosAcumulados(model.getPuntosAcumulados());
      value.setPuntosUtilizados(model.getPuntosUtilizados());
      value.setPuntosComprometidos(model.getPuntosComprometidos());
      value.setValor(model.getValor());
      value.setSaldo(model.getSaldo());
      value.setCupo(model.getCupo());
      value.setEstado(model.getEstado());
      value.setValidador(model.getValidador());
      value.setPuntosAcumuladosStatus(model.getPuntosAcumuladosStatus());
      value.setDineroAcumulado(model.getDineroAcumulado());
      value.setDineroUtilizado(model.getDineroUtilizado());
      value.setDineroComprometido(model.getDineroComprometido());
      value.setDineroAcumuladoStatus(model.getDineroAcumuladoStatus());
      value.setEmpresaId(model.getEmpresaId());
      value.setFechaUltimoCambioStatus(model.getFechaUltimoCambioStatus());
      value.setProductoId(model.getProductoId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tarjeta.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tarjeta.");
      }
     
      return getTarjeta(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTarjeta(com.spirit.pos.entity.TarjetaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TarjetaEJB data = new TarjetaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setTipoId(model.getTipoId());
      data.setClienteoficinaId(model.getClienteoficinaId());
      data.setReferidoporId(model.getReferidoporId());
      data.setGarante(model.getGarante());
      data.setFechaValidez(model.getFechaValidez());
      data.setFechaEmision(model.getFechaEmision());
      data.setPuntosAcumulados(model.getPuntosAcumulados());
      data.setPuntosUtilizados(model.getPuntosUtilizados());
      data.setPuntosComprometidos(model.getPuntosComprometidos());
      data.setValor(model.getValor());
      data.setSaldo(model.getSaldo());
      data.setCupo(model.getCupo());
      data.setEstado(model.getEstado());
      data.setValidador(model.getValidador());
      data.setPuntosAcumuladosStatus(model.getPuntosAcumuladosStatus());
      data.setDineroAcumulado(model.getDineroAcumulado());
      data.setDineroUtilizado(model.getDineroUtilizado());
      data.setDineroComprometido(model.getDineroComprometido());
      data.setDineroAcumuladoStatus(model.getDineroAcumuladoStatus());
      data.setEmpresaId(model.getEmpresaId());
      data.setFechaUltimoCambioStatus(model.getFechaUltimoCambioStatus());
      data.setProductoId(model.getProductoId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tarjeta.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tarjeta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTarjeta(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TarjetaEJB data = manager.find(TarjetaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tarjeta.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tarjeta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.TarjetaIf getTarjeta(java.lang.Long id) {
      return (TarjetaEJB)queryManagerLocal.find(TarjetaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTarjetaList() {
	  return queryManagerLocal.singleClassList(TarjetaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTarjetaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TarjetaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTarjetaListSize() {
      Query countQuery = manager.createQuery("select count(*) from TarjetaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByTipoId(java.lang.Long tipoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoId", tipoId);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByClienteoficinaId(java.lang.Long clienteoficinaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteoficinaId", clienteoficinaId);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByReferidoporId(java.lang.Long referidoporId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referidoporId", referidoporId);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByGarante(java.lang.Long garante) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("garante", garante);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByFechaValidez(java.lang.Long fechaValidez) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaValidez", fechaValidez);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByFechaEmision(java.lang.Long fechaEmision) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaEmision", fechaEmision);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByPuntosAcumulados(java.math.BigDecimal puntosAcumulados) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntosAcumulados", puntosAcumulados);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByPuntosUtilizados(java.math.BigDecimal puntosUtilizados) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntosUtilizados", puntosUtilizados);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByPuntosComprometidos(java.math.BigDecimal puntosComprometidos) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntosComprometidos", puntosComprometidos);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaBySaldo(java.math.BigDecimal saldo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("saldo", saldo);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByCupo(java.math.BigDecimal cupo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cupo", cupo);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByValidador(java.lang.String validador) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("validador", validador);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByPuntosAcumuladosStatus(java.math.BigDecimal puntosAcumuladosStatus) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntosAcumuladosStatus", puntosAcumuladosStatus);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByDineroAcumulado(java.math.BigDecimal dineroAcumulado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("dineroAcumulado", dineroAcumulado);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByDineroUtilizado(java.math.BigDecimal dineroUtilizado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("dineroUtilizado", dineroUtilizado);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByDineroComprometido(java.math.BigDecimal dineroComprometido) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("dineroComprometido", dineroComprometido);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByDineroAcumuladoStatus(java.math.BigDecimal dineroAcumuladoStatus) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("dineroAcumuladoStatus", dineroAcumuladoStatus);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByFechaUltimoCambioStatus(java.lang.Long fechaUltimoCambioStatus) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaUltimoCambioStatus", fechaUltimoCambioStatus);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByProductoId(java.lang.Long productoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("productoId", productoId);
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TarjetaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTarjetaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TarjetaEJB.class, aMap);      
    }

/////////////////
}
