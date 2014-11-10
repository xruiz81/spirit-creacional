package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _LineaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.LineaIf addLinea(com.spirit.general.entity.LineaIf model) throws GenericBusinessException;

   void saveLinea(com.spirit.general.entity.LineaIf model) throws GenericBusinessException;

   void deleteLinea(java.lang.Long id) throws GenericBusinessException;

   Collection findLineaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.LineaIf getLinea(java.lang.Long id) throws GenericBusinessException;

   Collection getLineaList() throws GenericBusinessException;

   Collection getLineaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getLineaListSize() throws GenericBusinessException;
    java.util.Collection findLineaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findLineaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findLineaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findLineaByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findLineaByNivel(java.lang.Integer nivel) throws GenericBusinessException;
    java.util.Collection findLineaByAceptaitem(java.lang.String aceptaitem) throws GenericBusinessException;
    java.util.Collection findLineaByLineaId(java.lang.Long lineaId) throws GenericBusinessException;

}
