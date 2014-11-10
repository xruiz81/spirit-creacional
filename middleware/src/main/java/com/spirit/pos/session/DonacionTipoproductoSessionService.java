package com.spirit.pos.session;



import java.util.Collection;
import java.util.Map;

/**
 * The <code>DonacionTipoproductoSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:30 $
 */
public interface DonacionTipoproductoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   /**
    * Adds a new donacionTipoproducto to the storage.
    *
    * @param model a data object
    * @return DonacionTipoproductoIf a data object with the primary key
    */
   com.spirit.pos.entity.DonacionTipoproductoIf addDonacionTipoproducto(com.spirit.pos.entity.DonacionTipoproductoIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Stores the <code>DonacionTipoproductoIf</code>.
    *
    * @param model the data model to store
    */
   void saveDonacionTipoproducto(com.spirit.pos.entity.DonacionTipoproductoIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Removes a donacionTipoproducto.
    *
    * @param id the unique reference for the donacionTipoproducto
    */
   void deleteDonacionTipoproducto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

    /**
    * Finds by a Map donacionTipoproducto.
    *
    */
   Collection findDonacionTipoproductoByQuery(Map aMap) throws com.spirit.exception.GenericBusinessException;




   /**
    * Retrieves a data object from the storage by its primary key.
    *
    * @param id the unique reference
    * @return DonacionTipoproductoIf the data object
    */
   com.spirit.pos.entity.DonacionTipoproductoIf getDonacionTipoproducto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a collection of all donacionTipoproducto instances.
    *
    * @return a collection of DonacionTipoproductoIf objects.
    */
   Collection getDonacionTipoproductoList() throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a subset of all donacionTipoproducto instances.
    *
    * @param startIndex the start index within the result set (1 = first record);
    * any zero/negative values are regarded as 1, and any values greater than or equal to
    * the total number of donacionTipoproducto instances will simply return an empty set.
    * @param endIndex the end index within the result set (<code>getDonacionTipoproductoListSize()</code> = last record),
    * any values greater than or equal to the total number of donacionTipoproducto instances will cause
    * the full set to be returned.
    * @return a collection of DonacionTipoproductoIf objects, of size <code>(endIndex - startIndex)</code>.
    * throws GenericBusinessException if the chosen underlying data-retrieval mechanism does not support returning partial result sets.
    */
   Collection getDonacionTipoproductoList(int startIndex, int endIndex) throws com.spirit.exception.GenericBusinessException;

   /**
    * Obtains the total number of donacionTipoproducto objects in the storage.
    * <b>NOTE:</b>If this session façade is configured <b>not</b> to use the FastLaneReader,
    * this call will be very computationally wasteful as it will first have to retrieve all entities from
    * the entity bean's <code>findAll</code> method.
    *
    * @return an integer value.
    */
   int getDonacionTipoproductoListSize() throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified id field.
     *
     * @param id the field
     * @return Collection of DonacionTipoproductoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findDonacionTipoproductoById(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified tipoproductoId field.
     *
     * @param tipoproductoId the field
     * @return Collection of DonacionTipoproductoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findDonacionTipoproductoByTipoproductoId(java.lang.Long tipoproductoId) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified valor field.
     *
     * @param valor the field
     * @return Collection of DonacionTipoproductoIf data objects, empty list in case no results were found.
     */
    java.util.Collection findDonacionTipoproductoByValor(java.math.BigDecimal valor) throws com.spirit.exception.GenericBusinessException;

}
