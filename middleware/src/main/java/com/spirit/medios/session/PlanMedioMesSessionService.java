package com.spirit.medios.session;



import java.util.*;

/**
 * The <code>PlanMedioMesSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:41 $
 */
public interface PlanMedioMesSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   /**
    * Adds a new planMedioMes to the storage.
    *
    * @param model a data object
    * @return PlanMedioMesIf a data object with the primary key
    */
   com.spirit.medios.entity.PlanMedioMesIf addPlanMedioMes(com.spirit.medios.entity.PlanMedioMesIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Stores the <code>PlanMedioMesIf</code>.
    *
    * @param model the data model to store
    */
   void savePlanMedioMes(com.spirit.medios.entity.PlanMedioMesIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Removes a planMedioMes.
    *
    * @param id the unique reference for the planMedioMes
    */
   void deletePlanMedioMes(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

    /**
    * Finds by a Map planMedioMes.
    *
    */
   Collection findPlanMedioMesByQuery(Map aMap) throws com.spirit.exception.GenericBusinessException;




   /**
    * Retrieves a data object from the storage by its primary key.
    *
    * @param id the unique reference
    * @return PlanMedioMesIf the data object
    */
   com.spirit.medios.entity.PlanMedioMesIf getPlanMedioMes(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a collection of all planMedioMes instances.
    *
    * @return a collection of PlanMedioMesIf objects.
    */
   Collection getPlanMedioMesList() throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a subset of all planMedioMes instances.
    *
    * @param startIndex the start index within the result set (1 = first record);
    * any zero/negative values are regarded as 1, and any values greater than or equal to
    * the total number of planMedioMes instances will simply return an empty set.
    * @param endIndex the end index within the result set (<code>getPlanMedioMesListSize()</code> = last record),
    * any values greater than or equal to the total number of planMedioMes instances will cause
    * the full set to be returned.
    * @return a collection of PlanMedioMesIf objects, of size <code>(endIndex - startIndex)</code>.
    * throws GenericBusinessException if the chosen underlying data-retrieval mechanism does not support returning partial result sets.
    */
   Collection getPlanMedioMesList(int startIndex, int endIndex) throws com.spirit.exception.GenericBusinessException;

   /**
    * Obtains the total number of planMedioMes objects in the storage.
    * <b>NOTE:</b>If this session façade is configured <b>not</b> to use the FastLaneReader,
    * this call will be very computationally wasteful as it will first have to retrieve all entities from
    * the entity bean's <code>findAll</code> method.
    *
    * @return an integer value.
    */
   int getPlanMedioMesListSize() throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified id field.
     *
     * @param id the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPlanMedioMesById(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified planmedioId field.
     *
     * @param planmedioId the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPlanMedioMesByPlanmedioId(java.lang.Long planmedioId) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified fechaini field.
     *
     * @param fechaini the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPlanMedioMesByFechaini(java.sql.Date fechaini) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified fechafin field.
     *
     * @param fechafin the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPlanMedioMesByFechafin(java.sql.Date fechafin) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified valorTarifa field.
     *
     * @param valorTarifa the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPlanMedioMesByValorTarifa(java.math.BigDecimal valorTarifa) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified valorDescuento field.
     *
     * @param valorDescuento the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPlanMedioMesByValorDescuento(java.math.BigDecimal valorDescuento) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified tipo field.
     *
     * @param tipo the field
     * @return Collection of PlanMedioMesIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPlanMedioMesByTipo(java.lang.String tipo) throws com.spirit.exception.GenericBusinessException;

}
