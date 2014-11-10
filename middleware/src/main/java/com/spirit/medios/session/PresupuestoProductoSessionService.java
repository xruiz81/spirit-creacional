package com.spirit.medios.session;



import java.util.*;

/**
 * The <code>PresupuestoProductoSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:40 $
 */
public interface PresupuestoProductoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   /**
    * Adds a new presupuestoProducto to the storage.
    *
    * @param model a data object
    * @return PresupuestoProductoIf a data object with the primary key
    */
   com.spirit.medios.entity.PresupuestoProductoIf addPresupuestoProducto(com.spirit.medios.entity.PresupuestoProductoIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Stores the <code>PresupuestoProductoIf</code>.
    *
    * @param model the data model to store
    */
   void savePresupuestoProducto(com.spirit.medios.entity.PresupuestoProductoIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Removes a presupuestoProducto.
    *
    * @param id the unique reference for the presupuestoProducto
    */
   void deletePresupuestoProducto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

    /**
    * Finds by a Map presupuestoProducto.
    *
    */
   Collection findPresupuestoProductoByQuery(Map aMap) throws com.spirit.exception.GenericBusinessException;




   /**
    * Retrieves a data object from the storage by its primary key.
    *
    * @param id the unique reference
    * @return PresupuestoProductoIf the data object
    */
   com.spirit.medios.entity.PresupuestoProductoIf getPresupuestoProducto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a collection of all presupuestoProducto instances.
    *
    * @return a collection of PresupuestoProductoIf objects.
    */
   Collection getPresupuestoProductoList() throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a subset of all presupuestoProducto instances.
    *
    * @param startIndex the start index within the result set (1 = first record);
    * any zero/negative values are regarded as 1, and any values greater than or equal to
    * the total number of presupuestoProducto instances will simply return an empty set.
    * @param endIndex the end index within the result set (<code>getPresupuestoProductoListSize()</code> = last record),
    * any values greater than or equal to the total number of presupuestoProducto instances will cause
    * the full set to be returned.
    * @return a collection of PresupuestoProductoIf objects, of size <code>(endIndex - startIndex)</code>.
    * throws GenericBusinessException if the chosen underlying data-retrieval mechanism does not support returning partial result sets.
    */
   Collection getPresupuestoProductoList(int startIndex, int endIndex) throws com.spirit.exception.GenericBusinessException;

   /**
    * Obtains the total number of presupuestoProducto objects in the storage.
    * <b>NOTE:</b>If this session façade is configured <b>not</b> to use the FastLaneReader,
    * this call will be very computationally wasteful as it will first have to retrieve all entities from
    * the entity bean's <code>findAll</code> method.
    *
    * @return an integer value.
    */
   int getPresupuestoProductoListSize() throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified id field.
     *
     * @param id the field
     * @return Collection of PresupuestoProductoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPresupuestoProductoById(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified productoClienteId field.
     *
     * @param productoClienteId the field
     * @return Collection of PresupuestoProductoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPresupuestoProductoByProductoClienteId(java.lang.Long productoClienteId) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified presupuestoId field.
     *
     * @param presupuestoId the field
     * @return Collection of PresupuestoProductoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPresupuestoProductoByPresupuestoId(java.lang.Long presupuestoId) throws com.spirit.exception.GenericBusinessException;

}
