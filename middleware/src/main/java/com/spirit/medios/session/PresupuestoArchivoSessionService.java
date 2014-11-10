package com.spirit.medios.session;

import java.util.*;
import com.spirit.medios.entity.PresupuestoArchivoEJB;
import com.spirit.medios.entity.PresupuestoArchivoIf;

/**
 * The <code>PresupuestoArchivoSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:41 $
 */
public interface PresupuestoArchivoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public PresupuestoArchivoEJB registrarPresupuestoArchivo(PresupuestoArchivoIf modelPresupuestoArchivo, String urlCarpetaSevidor)  throws com.spirit.exception.GenericBusinessException;

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   /**
    * Adds a new presupuestoArchivo to the storage.
    *
    * @param model a data object
    * @return PresupuestoArchivoIf a data object with the primary key
    */
   com.spirit.medios.entity.PresupuestoArchivoIf addPresupuestoArchivo(com.spirit.medios.entity.PresupuestoArchivoIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Stores the <code>PresupuestoArchivoIf</code>.
    *
    * @param model the data model to store
    */
   void savePresupuestoArchivo(com.spirit.medios.entity.PresupuestoArchivoIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Removes a presupuestoArchivo.
    *
    * @param id the unique reference for the presupuestoArchivo
    */
   void deletePresupuestoArchivo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

    /**
    * Finds by a Map presupuestoArchivo.
    *
    */
   Collection findPresupuestoArchivoByQuery(Map aMap) throws com.spirit.exception.GenericBusinessException;




   /**
    * Retrieves a data object from the storage by its primary key.
    *
    * @param id the unique reference
    * @return PresupuestoArchivoIf the data object
    */
   com.spirit.medios.entity.PresupuestoArchivoIf getPresupuestoArchivo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a collection of all presupuestoArchivo instances.
    *
    * @return a collection of PresupuestoArchivoIf objects.
    */
   Collection getPresupuestoArchivoList() throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a subset of all presupuestoArchivo instances.
    *
    * @param startIndex the start index within the result set (1 = first record);
    * any zero/negative values are regarded as 1, and any values greater than or equal to
    * the total number of presupuestoArchivo instances will simply return an empty set.
    * @param endIndex the end index within the result set (<code>getPresupuestoArchivoListSize()</code> = last record),
    * any values greater than or equal to the total number of presupuestoArchivo instances will cause
    * the full set to be returned.
    * @return a collection of PresupuestoArchivoIf objects, of size <code>(endIndex - startIndex)</code>.
    * throws GenericBusinessException if the chosen underlying data-retrieval mechanism does not support returning partial result sets.
    */
   Collection getPresupuestoArchivoList(int startIndex, int endIndex) throws com.spirit.exception.GenericBusinessException;

   /**
    * Obtains the total number of presupuestoArchivo objects in the storage.
    * <b>NOTE:</b>If this session façade is configured <b>not</b> to use the FastLaneReader,
    * this call will be very computationally wasteful as it will first have to retrieve all entities from
    * the entity bean's <code>findAll</code> method.
    *
    * @return an integer value.
    */
   int getPresupuestoArchivoListSize() throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified id field.
     *
     * @param id the field
     * @return Collection of PresupuestoArchivoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPresupuestoArchivoById(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified presupuestoId field.
     *
     * @param presupuestoId the field
     * @return Collection of PresupuestoArchivoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPresupuestoArchivoByPresupuestoId(java.lang.Long presupuestoId) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified tipoArchivoId field.
     *
     * @param tipoArchivoId the field
     * @return Collection of PresupuestoArchivoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPresupuestoArchivoByTipoArchivoId(java.lang.Long tipoArchivoId) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified url field.
     *
     * @param url the field
     * @return Collection of PresupuestoArchivoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPresupuestoArchivoByUrl(java.lang.String url) throws com.spirit.exception.GenericBusinessException;

}
