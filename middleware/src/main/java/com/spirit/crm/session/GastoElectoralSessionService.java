package com.spirit.crm.session;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.spirit.crm.entity.GastoElectoralAbonoIf;
import com.spirit.crm.entity.GastoElectoralIf;
import com.spirit.exception.GenericBusinessException;

/**
 * The <code>GastoElectoralSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:45 $
 */
public interface GastoElectoralSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public void procesarGastoElectoral(List<GastoElectoralIf> gastoElectoralColeccion, List<GastoElectoralIf> gastoElectoralEliminados, List<GastoElectoralAbonoIf> gastoElectoralIngresosColeccion, List<GastoElectoralAbonoIf> gastoElectoralIngresosEliminados) throws GenericBusinessException; 
	public Collection findGastoElectoralByQueryByFechaInicioAndByFechaFinOrderBy(Map aMap, Date fechaInicio, Date fechaFin, String orderBy) throws com.spirit.exception.GenericBusinessException;
	
   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   /**
    * Adds a new gastoElectoral to the storage.
    *
    * @param model a data object
    * @return GastoElectoralIf a data object with the primary key
    */
   com.spirit.crm.entity.GastoElectoralIf addGastoElectoral(com.spirit.crm.entity.GastoElectoralIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Stores the <code>GastoElectoralIf</code>.
    *
    * @param model the data model to store
    */
   void saveGastoElectoral(com.spirit.crm.entity.GastoElectoralIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Removes a gastoElectoral.
    *
    * @param id the unique reference for the gastoElectoral
    */
   void deleteGastoElectoral(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

    /**
    * Finds by a Map gastoElectoral.
    *
    */
   Collection findGastoElectoralByQuery(Map aMap) throws com.spirit.exception.GenericBusinessException;




   /**
    * Retrieves a data object from the storage by its primary key.
    *
    * @param id the unique reference
    * @return GastoElectoralIf the data object
    */
   com.spirit.crm.entity.GastoElectoralIf getGastoElectoral(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a collection of all gastoElectoral instances.
    *
    * @return a collection of GastoElectoralIf objects.
    */
   Collection getGastoElectoralList() throws com.spirit.exception.GenericBusinessException;

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
    * throws GenericBusinessException if the chosen underlying data-retrieval mechanism does not support returning partial result sets.
    */
   Collection getGastoElectoralList(int startIndex, int endIndex) throws com.spirit.exception.GenericBusinessException;

   /**
    * Obtains the total number of gastoElectoral objects in the storage.
    * <b>NOTE:</b>If this session façade is configured <b>not</b> to use the FastLaneReader,
    * this call will be very computationally wasteful as it will first have to retrieve all entities from
    * the entity bean's <code>findAll</code> method.
    *
    * @return an integer value.
    */
   int getGastoElectoralListSize() throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified id field.
     *
     * @param id the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralById(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified campana field.
     *
     * @param campana the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralByCampana(java.lang.String campana) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified fecha field.
     *
     * @param fecha the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralByFecha(java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified tipo field.
     *
     * @param tipo the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralByTipo(java.lang.String tipo) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified producto field.
     *
     * @param producto the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralByProducto(java.lang.String producto) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified proveedor field.
     *
     * @param proveedor the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralByProveedor(java.lang.String proveedor) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified descripcion field.
     *
     * @param descripcion the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralByDescripcion(java.lang.String descripcion) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified tamano field.
     *
     * @param tamano the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralByTamano(java.lang.String tamano) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified cantidad field.
     *
     * @param cantidad the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralByCantidad(java.math.BigDecimal cantidad) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified costoUnitario field.
     *
     * @param costoUnitario the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralByCostoUnitario(java.math.BigDecimal costoUnitario) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified inversionConFactura field.
     *
     * @param inversionConFactura the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralByInversionConFactura(java.math.BigDecimal inversionConFactura) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified inversionSinFactura field.
     *
     * @param inversionSinFactura the field
     * @return Collection of GastoElectoralIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralByInversionSinFactura(java.math.BigDecimal inversionSinFactura) throws com.spirit.exception.GenericBusinessException;

}
