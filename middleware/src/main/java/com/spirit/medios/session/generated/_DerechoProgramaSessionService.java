package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _DerechoProgramaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.DerechoProgramaIf addDerechoPrograma(com.spirit.medios.entity.DerechoProgramaIf model) throws GenericBusinessException;

   void saveDerechoPrograma(com.spirit.medios.entity.DerechoProgramaIf model) throws GenericBusinessException;

   void deleteDerechoPrograma(java.lang.Long id) throws GenericBusinessException;

   Collection findDerechoProgramaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.DerechoProgramaIf getDerechoPrograma(java.lang.Long id) throws GenericBusinessException;

   Collection getDerechoProgramaList() throws GenericBusinessException;

   Collection getDerechoProgramaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getDerechoProgramaListSize() throws GenericBusinessException;
    java.util.Collection findDerechoProgramaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findDerechoProgramaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findDerechoProgramaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findDerechoProgramaByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
