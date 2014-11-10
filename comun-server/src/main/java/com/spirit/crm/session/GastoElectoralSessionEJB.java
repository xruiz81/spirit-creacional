package com.spirit.crm.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.crm.entity.GastoElectoralAbonoEJB;
import com.spirit.crm.entity.GastoElectoralAbonoIf;
import com.spirit.crm.entity.GastoElectoralEJB;
import com.spirit.crm.entity.GastoElectoralIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>GastoElectoralSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:20 $
 *
 */
@Stateless
public class GastoElectoralSessionEJB implements GastoElectoralSessionRemote  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;
   @Resource private SessionContext ctx; 

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(GastoElectoralSessionEJB.class);

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   public void procesarGastoElectoral(List<GastoElectoralIf> gastoElectoralColeccion, List<GastoElectoralIf> gastoElectoralEliminados, List<GastoElectoralAbonoIf> gastoElectoralIngresosColeccion, List<GastoElectoralAbonoIf> gastoElectoralIngresosEliminados) throws GenericBusinessException{
	   try {
		   for(GastoElectoralIf modelEliminado : gastoElectoralEliminados){
			   GastoElectoralEJB data = manager.find(GastoElectoralEJB.class, modelEliminado.getId());
			   manager.remove(data);
		   }		   
		   for(GastoElectoralIf model : gastoElectoralColeccion){
			   if(model.getId() == null){
				   GastoElectoralIf gastoElectoral = registrarGastoElectoral(model);
				   manager.persist(gastoElectoral);	
			   }else{
				   GastoElectoralIf gastoElectoral = registrarGastoElectoral(model);
				   manager.merge(gastoElectoral);	
			   }			  			
		   }
		   
		   for(GastoElectoralAbonoIf modelIngresoEliminado : gastoElectoralIngresosEliminados){
			   GastoElectoralAbonoEJB dataIngreso = manager.find(GastoElectoralAbonoEJB.class, modelIngresoEliminado.getId());
			   manager.remove(dataIngreso);
		   }		   
		   for(GastoElectoralAbonoIf modelIngreso : gastoElectoralIngresosColeccion){
			   if(modelIngreso.getId() == null){
				   GastoElectoralAbonoIf gastoElectoralIngreso = registrarGastoElectoralIngreso(modelIngreso);
				   manager.persist(gastoElectoralIngreso);	
			   }else{
				   GastoElectoralAbonoIf gastoElectoralIngreso = registrarGastoElectoralIngreso(modelIngreso);
				   manager.merge(gastoElectoralIngreso);	
			   }			  			
		   }
		   
		   manager.flush();
									
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		}
   }
   
   public GastoElectoralIf registrarGastoElectoral(GastoElectoralIf model){
	   GastoElectoralEJB gastoElectoral = new GastoElectoralEJB();
	   gastoElectoral.setCampana(model.getCampana());
	   gastoElectoral.setCantidad(model.getCantidad());
	   gastoElectoral.setCostoUnitario(model.getCostoUnitario());
	   gastoElectoral.setDescripcion(model.getDescripcion());
	   gastoElectoral.setFecha(model.getFecha());
	   gastoElectoral.setId(model.getId());
	   gastoElectoral.setInversionConFactura(model.getInversionConFactura());
	   gastoElectoral.setInversionSinFactura(model.getInversionSinFactura());
	   gastoElectoral.setProducto(model.getProducto());
	   gastoElectoral.setProveedor(model.getProveedor());
	   gastoElectoral.setTamano(model.getTamano());
	   gastoElectoral.setTipo(model.getTipo());
	   return gastoElectoral;
   }
   
   public GastoElectoralAbonoIf registrarGastoElectoralIngreso(GastoElectoralAbonoIf modelIngreso){
	   GastoElectoralAbonoEJB gastoElectoralAbono = new GastoElectoralAbonoEJB();
	   gastoElectoralAbono.setCampana(modelIngreso.getCampana());
	   gastoElectoralAbono.setFecha(modelIngreso.getFecha());
	   gastoElectoralAbono.setId(modelIngreso.getId());
	   gastoElectoralAbono.setEntregadoPor(modelIngreso.getEntregadoPor());
	   gastoElectoralAbono.setValor(modelIngreso.getValor());
	   return gastoElectoralAbono;
   }
   
   public Collection findGastoElectoralByQueryByFechaInicioAndByFechaFinOrderBy(Map aMap, Date fechaInicio, Date fechaFin, String orderBy) throws com.spirit.exception.GenericBusinessException{
       String objectName = "ge";
       String where = "";
       if(!aMap.isEmpty()){
    	   where = QueryBuilder.buildWhere(aMap, objectName);
       }
       String queryString = "";
       if(fechaInicio != null && fechaFin != null){
    	   queryString = "select ge from GastoElectoralEJB " + objectName + " where " + where + " and ge.fecha between :fechaInicio and :fechaFin";
       }else{
    	   queryString = "select ge from GastoElectoralEJB " + objectName + " where " + where + "";
       }
       String orderByPart = "";
 	   if(orderBy.equals("tipo")){
 		   orderByPart += " order by ge.campana asc, ge.tipo asc, ge.fecha asc"; 
 	   }else{
 		  orderByPart += " order by ge.campana asc, ge.fecha asc";
 	   }
 	   queryString += orderByPart;
 	   Query query = manager.createQuery(queryString);
 	   if(fechaInicio != null && fechaFin != null){
 		   query.setParameter("fechaInicio",fechaInicio);
 		   query.setParameter("fechaFin",fechaFin);
 	   } 	   
 	   
 	   Set keys = aMap.keySet();
 	   Iterator it = keys.iterator();

 	   while (it.hasNext()) {
 		   String propertyKey = (String) it.next();
 		   Object property = aMap.get(propertyKey);
 		   query.setParameter(propertyKey, property);
 	   }
 	   
 	   return query.getResultList();
   }

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   /**
    * Adds a new gastoElectoral to the database.
    *
    * @param model a data object
    * @return GastoElectoralIf a data object with the primary key
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.GastoElectoralIf addGastoElectoral(com.spirit.crm.entity.GastoElectoralIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      GastoElectoralEJB value = new GastoElectoralEJB();
      try {
      value.setId(model.getId());
      value.setCampana(model.getCampana());
      value.setFecha(model.getFecha());
      value.setTipo(model.getTipo());
      value.setProducto(model.getProducto());
      value.setProveedor(model.getProveedor());
      value.setDescripcion(model.getDescripcion());
      value.setTamano(model.getTamano());
      value.setCantidad(model.getCantidad());
      value.setCostoUnitario(model.getCostoUnitario());
      value.setInversionConFactura(model.getInversionConFactura());
      value.setInversionSinFactura(model.getInversionSinFactura());
      manager.persist(value);
      manager.flush();
      } catch (Exception e) {
        log.error("Error al insertar información en gastoElectoral.", e);
			throw new GenericBusinessException(
					"Error al insertar información en gastoElectoral.");
      }
     
      return getGastoElectoral(value.getPrimaryKey());
   }

   /**
    * Stores the <code>GastoElectoralIf</code> in the database.
    *
    * @param model the data model to store
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveGastoElectoral(com.spirit.crm.entity.GastoElectoralIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      GastoElectoralEJB data = new GastoElectoralEJB();
      data.setId(model.getId());
      data.setCampana(model.getCampana());
      data.setFecha(model.getFecha());
      data.setTipo(model.getTipo());
      data.setProducto(model.getProducto());
      data.setProveedor(model.getProveedor());
      data.setDescripcion(model.getDescripcion());
      data.setTamano(model.getTamano());
      data.setCantidad(model.getCantidad());
      data.setCostoUnitario(model.getCostoUnitario());
      data.setInversionConFactura(model.getInversionConFactura());
      data.setInversionSinFactura(model.getInversionSinFactura());
       manager.merge(data);
       manager.flush();
      } catch (Exception e) {
        log.error("Error al actualizar información en gastoElectoral.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en gastoElectoral.");
      }

   }

   /**
    * Removes a gastoElectoral.
    *
    * @param id the unique reference for the gastoElectoral
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteGastoElectoral(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      GastoElectoralEJB data = manager.find(GastoElectoralEJB.class, id);
      manager.remove(data);
      manager.flush();

      } catch (Exception e) {
        log.error("Error al eliminar información en gastoElectoral.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en gastoElectoral.");
      }

   }

   /**
    * Retrieves a data object from the database by its primary key.
    *
    * @param id the unique reference
    * @return GastoElectoralIf the data object
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.GastoElectoralIf getGastoElectoral(java.lang.Long id) {
      return manager.find(GastoElectoralEJB.class, id);
   }

   /**
    * Returns a collection of all gastoElectoral instances.
    *
    * @return a collection of GastoElectoralIf objects.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGastoElectoralList() {
      String queryString = "from GastoElectoralEJB e";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      return query.getResultList();
   }

   /**
    * Returns a subset of all gastoElectoral instances.
    *
    * @param startIndex the start index within the result set (1 = first record);
    * any zero/negative values are regarded as 1, and any values greater than or equal to
    * the total number of gastoElectoral instances will simply return an empty set.
    * @param endIndex the end index within the result set (<code>getGastoElectoralListSize()</code> = last record),
    * any values greater than or equal to the total number of gastoElectoral instances will cause
    * the full set to be returned.
    * @return a collection of GastoElectoralIf objects, of size <code>(endIndex - startIndex)</code>.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGastoElectoralList(int startIndex, int endIndex) {
      if (startIndex < 1) {
         startIndex = 1;
      }
      if ( (endIndex - startIndex) < 0) {
         // Just return an empty list.
         return new ArrayList();
      }
      String queryString = "from GastoElectoralEJB e";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setFirstResult(startIndex - 1);
      query.setMaxResults(endIndex - startIndex + 1);
      return query.getResultList();
   }

   /**
    * Obtains the total number of gastoElectoral objects in the database.
    *
    * @return an integer value.
    */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getGastoElectoralListSize() {
      Query countQuery = manager.createQuery("select count(*) from GastoElectoralEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }


    /**
     *
     * Retrieves a list of data object for the specified id field.
     *
     * @param id the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralById(java.lang.Long id) {

      String queryString = "from GastoElectoralEJB e where e.id = :id ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("id", id);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified campana field.
     *
     * @param campana the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralByCampana(java.lang.String campana) {

      String queryString = "from GastoElectoralEJB e where e.campana = :campana ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("campana", campana);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified fecha field.
     *
     * @param fecha the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralByFecha(java.sql.Date fecha) {

      String queryString = "from GastoElectoralEJB e where e.fecha = :fecha ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("fecha", fecha);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified tipo field.
     *
     * @param tipo the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralByTipo(java.lang.String tipo) {

      String queryString = "from GastoElectoralEJB e where e.tipo = :tipo ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("tipo", tipo);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified producto field.
     *
     * @param producto the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralByProducto(java.lang.String producto) {

      String queryString = "from GastoElectoralEJB e where e.producto = :producto ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("producto", producto);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified proveedor field.
     *
     * @param proveedor the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralByProveedor(java.lang.String proveedor) {

      String queryString = "from GastoElectoralEJB e where e.proveedor = :proveedor ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("proveedor", proveedor);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified descripcion field.
     *
     * @param descripcion the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralByDescripcion(java.lang.String descripcion) {

      String queryString = "from GastoElectoralEJB e where e.descripcion = :descripcion ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("descripcion", descripcion);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified tamano field.
     *
     * @param tamano the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralByTamano(java.lang.String tamano) {

      String queryString = "from GastoElectoralEJB e where e.tamano = :tamano ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("tamano", tamano);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified cantidad field.
     *
     * @param cantidad the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralByCantidad(java.math.BigDecimal cantidad) {

      String queryString = "from GastoElectoralEJB e where e.cantidad = :cantidad ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("cantidad", cantidad);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified costoUnitario field.
     *
     * @param costoUnitario the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralByCostoUnitario(java.math.BigDecimal costoUnitario) {

      String queryString = "from GastoElectoralEJB e where e.costoUnitario = :costoUnitario ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("costoUnitario", costoUnitario);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified inversionConFactura field.
     *
     * @param inversionConFactura the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralByInversionConFactura(java.math.BigDecimal inversionConFactura) {

      String queryString = "from GastoElectoralEJB e where e.inversionConFactura = :inversionConFactura ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("inversionConFactura", inversionConFactura);
      return query.getResultList();
    }

    /**
     *
     * Retrieves a list of data object for the specified inversionSinFactura field.
     *
     * @param inversionSinFactura the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralByInversionSinFactura(java.math.BigDecimal inversionSinFactura) {

      String queryString = "from GastoElectoralEJB e where e.inversionSinFactura = :inversionSinFactura ";
      // Add a an order by on all primary keys to assure reproducable results.
      String orderByPart = "";
      orderByPart += " order by e.id";
      queryString += orderByPart;
      Query query = manager.createQuery(queryString);
      query.setParameter("inversionSinFactura", inversionSinFactura);
      return query.getResultList();
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoElectoralByQuery(Map aMap) {
 	String objectName = "e";
 	String where = QueryBuilder.buildWhere(aMap, objectName);
 	String queryString = "from GastoElectoralEJB " + objectName + " where "
				+ where;
 	Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		return query.getResultList();
      
    }

/////////////////
}
