package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CruceDocumentoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.CruceDocumentoIf addCruceDocumento(com.spirit.general.entity.CruceDocumentoIf model) throws GenericBusinessException;

   void saveCruceDocumento(com.spirit.general.entity.CruceDocumentoIf model) throws GenericBusinessException;

   void deleteCruceDocumento(java.lang.Long id) throws GenericBusinessException;

   Collection findCruceDocumentoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.CruceDocumentoIf getCruceDocumento(java.lang.Long id) throws GenericBusinessException;

   Collection getCruceDocumentoList() throws GenericBusinessException;

   Collection getCruceDocumentoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCruceDocumentoListSize() throws GenericBusinessException;
    java.util.Collection findCruceDocumentoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCruceDocumentoByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findCruceDocumentoByDocumentoaplId(java.lang.Long documentoaplId) throws GenericBusinessException;
    java.util.Collection findCruceDocumentoByValidoAlGuardar(java.lang.String validoAlGuardar) throws GenericBusinessException;
    java.util.Collection findCruceDocumentoByValidoAlActualizar(java.lang.String validoAlActualizar) throws GenericBusinessException;

}
