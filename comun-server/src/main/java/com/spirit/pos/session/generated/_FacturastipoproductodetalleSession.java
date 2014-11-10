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
public abstract class _FacturastipoproductodetalleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_FacturastipoproductodetalleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.FacturastipoproductodetalleIf addFacturastipoproductodetalle(com.spirit.pos.entity.FacturastipoproductodetalleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      FacturastipoproductodetalleEJB value = new FacturastipoproductodetalleEJB();
      try {
      value.setId(model.getId());
      value.setModelo(model.getModelo());
      value.setColor(model.getColor());
      value.setTalla(model.getTalla());
      value.setTipo(model.getTipo());
      value.setProducto(model.getProducto());
      value.setFecha(model.getFecha());
      value.setCant(model.getCant());
      value.setDev(model.getDev());
      value.setCantfinal(model.getCantfinal());
      value.setPreciouni(model.getPreciouni());
      value.setValorsinivaventas(model.getValorsinivaventas());
      value.setDescuentoventas(model.getDescuentoventas());
      value.setIvaventas(model.getIvaventas());
      value.setTotalventas(model.getTotalventas());
      value.setValorsinivadev(model.getValorsinivadev());
      value.setIvadev(model.getIvadev());
      value.setTotaldev(model.getTotaldev());
      value.setModeloId(model.getModeloId());
      value.setColorId(model.getColorId());
      value.setTipoproducto(model.getTipoproducto());
      value.setTallaId(model.getTallaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en facturastipoproductodetalle.", e);
			throw new GenericBusinessException(
					"Error al insertar información en facturastipoproductodetalle.");
      }
     
      return getFacturastipoproductodetalle(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveFacturastipoproductodetalle(com.spirit.pos.entity.FacturastipoproductodetalleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      FacturastipoproductodetalleEJB data = new FacturastipoproductodetalleEJB();
      data.setId(model.getId());
      data.setModelo(model.getModelo());
      data.setColor(model.getColor());
      data.setTalla(model.getTalla());
      data.setTipo(model.getTipo());
      data.setProducto(model.getProducto());
      data.setFecha(model.getFecha());
      data.setCant(model.getCant());
      data.setDev(model.getDev());
      data.setCantfinal(model.getCantfinal());
      data.setPreciouni(model.getPreciouni());
      data.setValorsinivaventas(model.getValorsinivaventas());
      data.setDescuentoventas(model.getDescuentoventas());
      data.setIvaventas(model.getIvaventas());
      data.setTotalventas(model.getTotalventas());
      data.setValorsinivadev(model.getValorsinivadev());
      data.setIvadev(model.getIvadev());
      data.setTotaldev(model.getTotaldev());
      data.setModeloId(model.getModeloId());
      data.setColorId(model.getColorId());
      data.setTipoproducto(model.getTipoproducto());
      data.setTallaId(model.getTallaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en facturastipoproductodetalle.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en facturastipoproductodetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteFacturastipoproductodetalle(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      FacturastipoproductodetalleEJB data = manager.find(FacturastipoproductodetalleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en facturastipoproductodetalle.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en facturastipoproductodetalle.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.FacturastipoproductodetalleIf getFacturastipoproductodetalle(java.lang.Long id) {
      return (FacturastipoproductodetalleEJB)queryManagerLocal.find(FacturastipoproductodetalleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFacturastipoproductodetalleList() {
	  return queryManagerLocal.singleClassList(FacturastipoproductodetalleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFacturastipoproductodetalleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(FacturastipoproductodetalleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getFacturastipoproductodetalleListSize() {
      Query countQuery = manager.createQuery("select count(*) from FacturastipoproductodetalleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByModelo(java.lang.String modelo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("modelo", modelo);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByColor(java.lang.String color) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("color", color);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByTalla(java.lang.String talla) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("talla", talla);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByTipo(java.lang.String tipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipo", tipo);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByProducto(java.lang.Long producto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("producto", producto);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByFecha(java.sql.Timestamp fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByCant(java.math.BigDecimal cant) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cant", cant);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByDev(java.math.BigDecimal dev) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("dev", dev);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByCantfinal(java.math.BigDecimal cantfinal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cantfinal", cantfinal);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByPreciouni(java.math.BigDecimal preciouni) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("preciouni", preciouni);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByValorsinivaventas(java.math.BigDecimal valorsinivaventas) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorsinivaventas", valorsinivaventas);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByDescuentoventas(java.math.BigDecimal descuentoventas) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descuentoventas", descuentoventas);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByIvaventas(java.math.BigDecimal ivaventas) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ivaventas", ivaventas);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByTotalventas(java.math.BigDecimal totalventas) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("totalventas", totalventas);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByValorsinivadev(java.math.BigDecimal valorsinivadev) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorsinivadev", valorsinivadev);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByIvadev(java.math.BigDecimal ivadev) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ivadev", ivadev);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByTotaldev(java.math.BigDecimal totaldev) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("totaldev", totaldev);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByModeloId(java.lang.Long modeloId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("modeloId", modeloId);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByColorId(java.lang.Long colorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("colorId", colorId);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByTipoproducto(java.lang.Long tipoproducto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoproducto", tipoproducto);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByTallaId(java.lang.Long tallaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tallaId", tallaId);
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of FacturastipoproductodetalleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFacturastipoproductodetalleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(FacturastipoproductodetalleEJB.class, aMap);      
    }

/////////////////
}
