package com.spirit.medios.session;




import java.util.Collection;
import java.util.Map;

/**
 * The <code>TipoBriefSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:41 $
 */
public interface TipoBriefSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   Collection findTipoBriefByQuery(Map aMap,  Long idEmpresa) throws com.spirit.exception.GenericBusinessException;

	   
   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/

   /**
    * Adds a new tipoBrief to the storage.
    *
    * @param model a data object
    * @return TipoBriefIf a data object with the primary key
    */
   com.spirit.medios.entity.TipoBriefIf addTipoBrief(com.spirit.medios.entity.TipoBriefIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Stores the <code>TipoBriefIf</code>.
    *
    * @param model the data model to store
    */
   void saveTipoBrief(com.spirit.medios.entity.TipoBriefIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Removes a tipoBrief.
    *
    * @param id the unique reference for the tipoBrief
    */
   void deleteTipoBrief(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;
   
   Collection findTipoBriefByQuery(Map aMap) throws com.spirit.exception.GenericBusinessException;

   /**
    * Retrieves a data object from the storage by its primary key.
    *
    * @param id the unique reference
    * @return TipoBriefIf the data object
    */
   com.spirit.medios.entity.TipoBriefIf getTipoBrief(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a collection of all tipoBrief instances.
    *
    * @return a collection of TipoBriefIf objects.
    */
   Collection getTipoBriefList() throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a subset of all tipoBrief instances.
    *
    * @param startIndex the start index within the result set (1 = first record);
    * any zero/negative values are regarded as 1, and any values greater than or equal to
    * the total number of tipoBrief instances will simply return an empty set.
    * @param endIndex the end index within the result set (<code>getTipoBriefListSize()</code> = last record),
    * any values greater than or equal to the total number of tipoBrief instances will cause
    * the full set to be returned.
    * @return a collection of TipoBriefIf objects, of size <code>(endIndex - startIndex)</code>.
    * throws GenericBusinessException if the chosen underlying data-retrieval mechanism does not support returning partial result sets.
    */
   Collection getTipoBriefList(int startIndex, int endIndex) throws com.spirit.exception.GenericBusinessException;

   /**
    * Obtains the total number of tipoBrief objects in the storage.
    * <b>NOTE:</b>If this session façade is configured <b>not</b> to use the FastLaneReader,
    * this call will be very computationally wasteful as it will first have to retrieve all entities from
    * the entity bean's <code>findAll</code> method.
    *
    * @return an integer value.
    */
   int getTipoBriefListSize() throws com.spirit.exception.GenericBusinessException;

   /**
     *
     * Retrieves a list of data object for the specified id field.
     *
     * @param id the field
     * @return Collection of TipoBriefIf data objects, empty list in case no results were found.
     */
    java.util.Collection findTipoBriefById(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified codigo field.
     *
     * @param codigo the field
     * @return Collection of TipoBriefIf data objects, empty list in case no results were found.
     */
    java.util.Collection findTipoBriefByCodigo(java.lang.String codigo) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified nombre field.
     *
     * @param nombre the field
     * @return Collection of TipoBriefIf data objects, empty list in case no results were found.
     */
    java.util.Collection findTipoBriefByNombre(java.lang.String nombre) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified empresaId field.
     *
     * @param empresaId the field
     * @return Collection of TipoBriefIf data objects, empty list in case no results were found.
     */
    java.util.Collection findTipoBriefByEmpresaId(java.lang.Long empresaId) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified obligatorio field.
     *
     * @param obligatorio the field
     * @return Collection of TipoBriefIf data objects, empty list in case no results were found.
     */
    java.util.Collection findTipoBriefByObligatorio(java.lang.String obligatorio) throws com.spirit.exception.GenericBusinessException;

}
