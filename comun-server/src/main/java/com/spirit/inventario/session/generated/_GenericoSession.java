package com.spirit.inventario.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.inventario.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _GenericoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_GenericoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.GenericoIf addGenerico(com.spirit.inventario.entity.GenericoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      GenericoEJB value = new GenericoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setAbreviado(model.getAbreviado());
      value.setNombreGenerico(model.getNombreGenerico());
      value.setCambioDescripcion(model.getCambioDescripcion());
      value.setEmpresaId(model.getEmpresaId());
      value.setTipoproductoId(model.getTipoproductoId());
      value.setMedidaId(model.getMedidaId());
      value.setPartidaArancelaria(model.getPartidaArancelaria());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setReferencia(model.getReferencia());
      value.setUsaLote(model.getUsaLote());
      value.setLineaId(model.getLineaId());
      value.setIva(model.getIva());
      value.setIce(model.getIce());
      value.setOtroImpuesto(model.getOtroImpuesto());
      value.setServicio(model.getServicio());
      value.setFamiliaGenericoId(model.getFamiliaGenericoId());
      value.setSerie(model.getSerie());
      value.setAfectastock(model.getAfectastock());
      value.setEstado(model.getEstado());
      value.setCobraIva(model.getCobraIva());
      value.setCobraIce(model.getCobraIce());
      value.setMediosProduccion(model.getMediosProduccion());
      value.setLlevaInventario(model.getLlevaInventario());
      value.setAceptaDescuento(model.getAceptaDescuento());
      value.setCobraIvaCliente(model.getCobraIvaCliente());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en generico.", e);
			throw new GenericBusinessException(
					"Error al insertar información en generico.");
      }
     
      return getGenerico(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveGenerico(com.spirit.inventario.entity.GenericoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      GenericoEJB data = new GenericoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setAbreviado(model.getAbreviado());
      data.setNombreGenerico(model.getNombreGenerico());
      data.setCambioDescripcion(model.getCambioDescripcion());
      data.setEmpresaId(model.getEmpresaId());
      data.setTipoproductoId(model.getTipoproductoId());
      data.setMedidaId(model.getMedidaId());
      data.setPartidaArancelaria(model.getPartidaArancelaria());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setReferencia(model.getReferencia());
      data.setUsaLote(model.getUsaLote());
      data.setLineaId(model.getLineaId());
      data.setIva(model.getIva());
      data.setIce(model.getIce());
      data.setOtroImpuesto(model.getOtroImpuesto());
      data.setServicio(model.getServicio());
      data.setFamiliaGenericoId(model.getFamiliaGenericoId());
      data.setSerie(model.getSerie());
      data.setAfectastock(model.getAfectastock());
      data.setEstado(model.getEstado());
      data.setCobraIva(model.getCobraIva());
      data.setCobraIce(model.getCobraIce());
      data.setMediosProduccion(model.getMediosProduccion());
      data.setLlevaInventario(model.getLlevaInventario());
      data.setAceptaDescuento(model.getAceptaDescuento());
      data.setCobraIvaCliente(model.getCobraIvaCliente());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en generico.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en generico.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteGenerico(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      GenericoEJB data = manager.find(GenericoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en generico.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en generico.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.GenericoIf getGenerico(java.lang.Long id) {
      return (GenericoEJB)queryManagerLocal.find(GenericoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGenericoList() {
	  return queryManagerLocal.singleClassList(GenericoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGenericoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(GenericoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getGenericoListSize() {
      Query countQuery = manager.createQuery("select count(*) from GenericoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByAbreviado(java.lang.String abreviado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("abreviado", abreviado);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByNombreGenerico(java.lang.String nombreGenerico) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombreGenerico", nombreGenerico);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByCambioDescripcion(java.lang.String cambioDescripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cambioDescripcion", cambioDescripcion);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByTipoproductoId(java.lang.Long tipoproductoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoproductoId", tipoproductoId);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByMedidaId(java.lang.Long medidaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("medidaId", medidaId);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByPartidaArancelaria(java.lang.String partidaArancelaria) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("partidaArancelaria", partidaArancelaria);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByReferencia(java.lang.String referencia) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referencia", referencia);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByUsaLote(java.lang.String usaLote) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usaLote", usaLote);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByLineaId(java.lang.Long lineaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("lineaId", lineaId);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByIva(java.math.BigDecimal iva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("iva", iva);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByIce(java.math.BigDecimal ice) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ice", ice);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByOtroImpuesto(java.math.BigDecimal otroImpuesto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("otroImpuesto", otroImpuesto);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByServicio(java.lang.String servicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("servicio", servicio);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByFamiliaGenericoId(java.lang.Long familiaGenericoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("familiaGenericoId", familiaGenericoId);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoBySerie(java.lang.String serie) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("serie", serie);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByAfectastock(java.lang.String afectastock) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("afectastock", afectastock);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByCobraIva(java.lang.String cobraIva) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cobraIva", cobraIva);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByCobraIce(java.lang.String cobraIce) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cobraIce", cobraIce);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByMediosProduccion(java.lang.String mediosProduccion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("mediosProduccion", mediosProduccion);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByLlevaInventario(java.lang.String llevaInventario) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("llevaInventario", llevaInventario);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByAceptaDescuento(java.lang.String aceptaDescuento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("aceptaDescuento", aceptaDescuento);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByCobraIvaCliente(java.lang.String cobraIvaCliente) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cobraIvaCliente", cobraIvaCliente);
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of GenericoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGenericoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(GenericoEJB.class, aMap);      
    }

/////////////////
}
