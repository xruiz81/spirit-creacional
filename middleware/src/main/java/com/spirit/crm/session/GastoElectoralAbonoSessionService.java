package com.spirit.crm.session;



import java.util.*;

/**
 * The <code>GastoElectoralAbonoSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:45 $
 */
public interface GastoElectoralAbonoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public Collection findGastoElectoralIngresoByQueryByFechaInicioAndByFechaFin(Map aMap, Date fechaInicio, Date fechaFin) throws com.spirit.exception.GenericBusinessException;

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   /**
    * Adds a new gastoElectoralAbono to the storage.
    *
    * @param model a data object
    * @return GastoElectoralAbonoIf a data object with the primary key
    */
   com.spirit.crm.entity.GastoElectoralAbonoIf addGastoElectoralAbono(com.spirit.crm.entity.GastoElectoralAbonoIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Stores the <code>GastoElectoralAbonoIf</code>.
    *
    * @param model the data model to store
    */
   void saveGastoElectoralAbono(com.spirit.crm.entity.GastoElectoralAbonoIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Removes a gastoElectoralAbono.
    *
    * @param id the unique reference for the gastoElectoralAbono
    */
   void deleteGastoElectoralAbono(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

    /**
    * Finds by a Map gastoElectoralAbono.
    *
    */
   Collection findGastoElectoralAbonoByQuery(Map aMap) throws com.spirit.exception.GenericBusinessException;




   /**
    * Retrieves a data object from the storage by its primary key.
    *
    * @param id the unique reference
    * @return GastoElectoralAbonoIf the data object
    */
   com.spirit.crm.entity.GastoElectoralAbonoIf getGastoElectoralAbono(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a collection of all gastoElectoralAbono instances.
    *
    * @return a collection of GastoElectoralAbonoIf objects.
    */
   Collection getGastoElectoralAbonoList() throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a subset of all gastoElectoralAbono instances.
    *
    * @param startIndex the start index within the result set (1 = first record);
    * any zero/negative values are regarded as 1, and any values greater than or equal to
    * the total number of gastoElectoralAbono instances will simply return an empty set.
    * @param endIndex the end index within the result set (<code>getGastoElectoralAbonoListSize()</code> = last record),
    * any values greater than or equal to the total number of gastoElectoralAbono instances will cause
    * the full set to be returned.
    * @return a collection of GastoElectoralAbonoIf objects, of size <code>(endIndex - startIndex)</code>.
    * throws GenericBusinessException if the chosen underlying data-retrieval mechanism does not support returning partial result sets.
    */
   Collection getGastoElectoralAbonoList(int startIndex, int endIndex) throws com.spirit.exception.GenericBusinessException;

   /**
    * Obtains the total number of gastoElectoralAbono objects in the storage.
    * <b>NOTE:</b>If this session façade is configured <b>not</b> to use the FastLaneReader,
    * this call will be very computationally wasteful as it will first have to retrieve all entities from
    * the entity bean's <code>findAll</code> method.
    *
    * @return an integer value.
    */
   int getGastoElectoralAbonoListSize() throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified id field.
     *
     * @param id the field
     * @return Collection of GastoElectoralAbonoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralAbonoById(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified fecha field.
     *
     * @param fecha the field
     * @return Collection of GastoElectoralAbonoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralAbonoByFecha(java.sql.Date fecha) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified entregadoPor field.
     *
     * @param entregadoPor the field
     * @return Collection of GastoElectoralAbonoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralAbonoByEntregadoPor(java.lang.String entregadoPor) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified valor field.
     *
     * @param valor the field
     * @return Collection of GastoElectoralAbonoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralAbonoByValor(java.math.BigDecimal valor) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified campana field.
     *
     * @param campana the field
     * @return Collection of GastoElectoralAbonoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findGastoElectoralAbonoByCampana(java.lang.String campana) throws com.spirit.exception.GenericBusinessException;

}
