package com.spirit.poscola.session;



import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.poscola.entity.PosColaIf;

/**
 * The <code>PosColaSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:24 $
 */
public interface PosColaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	public PosColaIf obtenerInfoColaYO() throws GenericBusinessException;

	public java.util.Collection findTodosMenosPrincipalYoParametro(
			java.lang.String posName);
   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   /**
    * Adds a new posCola to the storage.
    *
    * @param model a data object
    * @return PosColaIf a data object with the primary key
    */
   com.spirit.poscola.entity.PosColaIf addPosCola(com.spirit.poscola.entity.PosColaIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Stores the <code>PosColaIf</code>.
    *
    * @param model the data model to store
    */
   void savePosCola(com.spirit.poscola.entity.PosColaIf model) throws com.spirit.exception.GenericBusinessException;

   /**
    * Removes a posCola.
    *
    * @param id the unique reference for the posCola
    */
   void deletePosCola(java.lang.Integer id) throws com.spirit.exception.GenericBusinessException;

    /**
    * Finds by a Map posCola.
    *
    */
   Collection findPosColaByQuery(Map aMap) throws com.spirit.exception.GenericBusinessException;




   /**
    * Retrieves a data object from the storage by its primary key.
    *
    * @param id the unique reference
    * @return PosColaIf the data object
    */
   com.spirit.poscola.entity.PosColaIf getPosCola(java.lang.Integer id) throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a collection of all posCola instances.
    *
    * @return a collection of PosColaIf objects.
    */
   Collection getPosColaList() throws com.spirit.exception.GenericBusinessException;

   /**
    * Returns a subset of all posCola instances.
    *
    * @param startIndex the start index within the result set (1 = first record);
    * any zero/negative values are regarded as 1, and any values greater than or equal to
    * the total number of posCola instances will simply return an empty set.
    * @param endIndex the end index within the result set (<code>getPosColaListSize()</code> = last record),
    * any values greater than or equal to the total number of posCola instances will cause
    * the full set to be returned.
    * @return a collection of PosColaIf objects, of size <code>(endIndex - startIndex)</code>.
    * throws GenericBusinessException if the chosen underlying data-retrieval mechanism does not support returning partial result sets.
    */
   Collection getPosColaList(int startIndex, int endIndex) throws com.spirit.exception.GenericBusinessException;

   /**
    * Obtains the total number of posCola objects in the storage.
    * <b>NOTE:</b>If this session façade is configured <b>not</b> to use the FastLaneReader,
    * this call will be very computationally wasteful as it will first have to retrieve all entities from
    * the entity bean's <code>findAll</code> method.
    *
    * @return an integer value.
    */
   int getPosColaListSize() throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified id field.
     *
     * @param id the field
     * @return Collection of PosColaIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPosColaById(java.lang.Integer id) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified direccionIp field.
     *
     * @param direccionIp the field
     * @return Collection of PosColaIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPosColaByDireccionIp(java.lang.String direccionIp) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified hostName field.
     *
     * @param hostName the field
     * @return Collection of PosColaIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPosColaByHostName(java.lang.String hostName) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified port field.
     *
     * @param port the field
     * @return Collection of PosColaIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPosColaByPort(java.lang.String port) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified factory field.
     *
     * @param factory the field
     * @return Collection of PosColaIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPosColaByFactory(java.lang.String factory) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified qname field.
     *
     * @param qname the field
     * @return Collection of PosColaIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPosColaByQname(java.lang.String qname) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified posName field.
     *
     * @param posName the field
     * @return Collection of PosColaIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPosColaByPosName(java.lang.String posName) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified bodegaId field.
     *
     * @param bodegaId the field
     * @return Collection of PosColaIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPosColaByBodegaId(java.lang.Long bodegaId) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified oficinaId field.
     *
     * @param oficinaId the field
     * @return Collection of PosColaIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPosColaByOficinaId(java.lang.Long oficinaId) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified tipoServer field.
     *
     * @param tipoServer the field
     * @return Collection of PosColaIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPosColaByTipoServer(java.lang.String tipoServer) throws com.spirit.exception.GenericBusinessException;

    /**
     *
     * Retrieves a list of data object for the specified me field.
     *
     * @param me the field
     * @return Collection of PosColaIf data objects, empty list in case no results were found.
     */
    java.util.Collection findPosColaByMe(java.lang.String me) throws com.spirit.exception.GenericBusinessException;

}
