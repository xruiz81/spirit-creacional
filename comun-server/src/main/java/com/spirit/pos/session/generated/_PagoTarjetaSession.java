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
public abstract class _PagoTarjetaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PagoTarjetaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.PagoTarjetaIf addPagoTarjeta(com.spirit.pos.entity.PagoTarjetaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PagoTarjetaEJB value = new PagoTarjetaEJB();
      try {
      value.setId(model.getId());
      value.setTipoTarjeta(model.getTipoTarjeta());
      value.setNombreCliente(model.getNombreCliente());
      value.setIdentificacion(model.getIdentificacion());
      value.setTelefono(model.getTelefono());
      value.setNoReferencia(model.getNoReferencia());
      value.setNoVoucher(model.getNoVoucher());
      value.setNoAutorizacion(model.getNoAutorizacion());
      value.setValor(model.getValor());
      value.setFecha(model.getFecha());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en pagoTarjeta.", e);
			throw new GenericBusinessException(
					"Error al insertar información en pagoTarjeta.");
      }
     
      return getPagoTarjeta(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePagoTarjeta(com.spirit.pos.entity.PagoTarjetaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PagoTarjetaEJB data = new PagoTarjetaEJB();
      data.setId(model.getId());
      data.setTipoTarjeta(model.getTipoTarjeta());
      data.setNombreCliente(model.getNombreCliente());
      data.setIdentificacion(model.getIdentificacion());
      data.setTelefono(model.getTelefono());
      data.setNoReferencia(model.getNoReferencia());
      data.setNoVoucher(model.getNoVoucher());
      data.setNoAutorizacion(model.getNoAutorizacion());
      data.setValor(model.getValor());
      data.setFecha(model.getFecha());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en pagoTarjeta.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en pagoTarjeta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePagoTarjeta(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PagoTarjetaEJB data = manager.find(PagoTarjetaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en pagoTarjeta.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en pagoTarjeta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.PagoTarjetaIf getPagoTarjeta(java.lang.Long id) {
      return (PagoTarjetaEJB)queryManagerLocal.find(PagoTarjetaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPagoTarjetaList() {
	  return queryManagerLocal.singleClassList(PagoTarjetaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPagoTarjetaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PagoTarjetaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPagoTarjetaListSize() {
      Query countQuery = manager.createQuery("select count(*) from PagoTarjetaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPagoTarjetaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PagoTarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPagoTarjetaByTipoTarjeta(java.lang.String tipoTarjeta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoTarjeta", tipoTarjeta);
		return queryManagerLocal.singleClassQueryList(PagoTarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPagoTarjetaByNombreCliente(java.lang.String nombreCliente) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombreCliente", nombreCliente);
		return queryManagerLocal.singleClassQueryList(PagoTarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPagoTarjetaByIdentificacion(java.lang.String identificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("identificacion", identificacion);
		return queryManagerLocal.singleClassQueryList(PagoTarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPagoTarjetaByTelefono(java.lang.String telefono) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("telefono", telefono);
		return queryManagerLocal.singleClassQueryList(PagoTarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPagoTarjetaByNoReferencia(java.lang.String noReferencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("noReferencia", noReferencia);
		return queryManagerLocal.singleClassQueryList(PagoTarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPagoTarjetaByNoVoucher(java.lang.String noVoucher) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("noVoucher", noVoucher);
		return queryManagerLocal.singleClassQueryList(PagoTarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPagoTarjetaByNoAutorizacion(java.lang.String noAutorizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("noAutorizacion", noAutorizacion);
		return queryManagerLocal.singleClassQueryList(PagoTarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPagoTarjetaByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(PagoTarjetaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPagoTarjetaByFecha(java.sql.Timestamp fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(PagoTarjetaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PagoTarjetaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPagoTarjetaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PagoTarjetaEJB.class, aMap);      
    }

/////////////////
}
