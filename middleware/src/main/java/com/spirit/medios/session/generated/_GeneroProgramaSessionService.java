package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _GeneroProgramaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.GeneroProgramaIf addGeneroPrograma(com.spirit.medios.entity.GeneroProgramaIf model) throws GenericBusinessException;

   void saveGeneroPrograma(com.spirit.medios.entity.GeneroProgramaIf model) throws GenericBusinessException;

   void deleteGeneroPrograma(java.lang.Long id) throws GenericBusinessException;

   Collection findGeneroProgramaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.GeneroProgramaIf getGeneroPrograma(java.lang.Long id) throws GenericBusinessException;

   Collection getGeneroProgramaList() throws GenericBusinessException;

   Collection getGeneroProgramaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getGeneroProgramaListSize() throws GenericBusinessException;
    java.util.Collection findGeneroProgramaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findGeneroProgramaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findGeneroProgramaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findGeneroProgramaByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
