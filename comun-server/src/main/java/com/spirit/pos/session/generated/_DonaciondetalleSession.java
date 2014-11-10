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
public abstract class _DonaciondetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_DonaciondetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.DonaciondetalleIf addDonaciondetalle(com.spirit.pos.entity.DonaciondetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      DonaciondetalleEJB value = new DonaciondetalleEJB();
      try {
      value.setId(model.getId());
      value.setDescripcion(model.getDescripcion());
      value.setFecha(model.getFecha());
      value.setCant(model.getCant());
      value.setDev(model.getDev());
      value.setCantfinal(model.getCantfinal());
      value.setValortipo(model.getValortipo());
      value.setTotal(model.getTotal());
      value.setFundacion(model.getFundacion());
      value.setFundacionid(model.getFundacionid());
      value.setModeloId(model.getModeloId());
      value.setColorId(model.getColorId());
      value.setTipoproducto(model.getTipoproducto());
      value.setTallaId(model.getTallaId());
      value.setNombrecliente(model.getNombrecliente());
      value.setClienteoficinaid(model.getClienteoficinaid());
      value.setPreimpreso(model.getPreimpreso());
      value.setFacturaaplId(model.getFacturaaplId());
      value.setFundaciondevolucionid(model.getFundaciondevolucionid());
      value.setNombrefundaciondevuelta(model.getNombrefundaciondevuelta());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en donaciondetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en donaciondetalle.");
      }
     
      return getDonaciondetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveDonaciondetalle(com.spirit.pos.entity.DonaciondetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      DonaciondetalleEJB data = new DonaciondetalleEJB();
      data.setId(model.getId());
      data.setDescripcion(model.getDescripcion());
      data.setFecha(model.getFecha());
      data.setCant(model.getCant());
      data.setDev(model.getDev());
      data.setCantfinal(model.getCantfinal());
      data.setValortipo(model.getValortipo());
      data.setTotal(model.getTotal());
      data.setFundacion(model.getFundacion());
      data.setFundacionid(model.getFundacionid());
      data.setModeloId(model.getModeloId());
      data.setColorId(model.getColorId());
      data.setTipoproducto(model.getTipoproducto());
      data.setTallaId(model.getTallaId());
      data.setNombrecliente(model.getNombrecliente());
      data.setClienteoficinaid(model.getClienteoficinaid());
      data.setPreimpreso(model.getPreimpreso());
      data.setFacturaaplId(model.getFacturaaplId());
      data.setFundaciondevolucionid(model.getFundaciondevolucionid());
      data.setNombrefundaciondevuelta(model.getNombrefundaciondevuelta());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en donaciondetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en donaciondetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteDonaciondetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      DonaciondetalleEJB data = manager.find(DonaciondetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en donaciondetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en donaciondetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.DonaciondetalleIf getDonaciondetalle(java.lang.Long id) {
      return (DonaciondetalleEJB)queryManagerLocal.find(DonaciondetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getDonaciondetalleList() {
	  return queryManagerLocal.singleClassList(DonaciondetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getDonaciondetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(DonaciondetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getDonaciondetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from DonaciondetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByFecha(java.sql.Timestamp fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByCant(java.math.BigDecimal cant) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cant", cant);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByDev(java.math.BigDecimal dev) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("dev", dev);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByCantfinal(java.math.BigDecimal cantfinal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantfinal", cantfinal);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByValortipo(java.math.BigDecimal valortipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valortipo", valortipo);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByTotal(java.math.BigDecimal total) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("total", total);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByFundacion(java.lang.String fundacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fundacion", fundacion);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByFundacionid(java.lang.Long fundacionid) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fundacionid", fundacionid);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByModeloId(java.lang.Long modeloId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("modeloId", modeloId);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByColorId(java.lang.Long colorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("colorId", colorId);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByTipoproducto(java.lang.Long tipoproducto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoproducto", tipoproducto);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByTallaId(java.lang.Long tallaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tallaId", tallaId);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByNombrecliente(java.lang.String nombrecliente) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombrecliente", nombrecliente);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByClienteoficinaid(java.lang.Long clienteoficinaid) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteoficinaid", clienteoficinaid);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByPreimpreso(java.lang.String preimpreso) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("preimpreso", preimpreso);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByFacturaaplId(java.lang.Long facturaaplId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("facturaaplId", facturaaplId);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByFundaciondevolucionid(java.lang.Long fundaciondevolucionid) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fundaciondevolucionid", fundaciondevolucionid);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByNombrefundaciondevuelta(java.lang.String nombrefundaciondevuelta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombrefundaciondevuelta", nombrefundaciondevuelta);
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of DonaciondetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findDonaciondetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(DonaciondetalleEJB.class, aMap);      
    }

/////////////////
}
