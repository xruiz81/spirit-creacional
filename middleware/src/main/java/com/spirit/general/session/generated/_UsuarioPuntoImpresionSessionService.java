package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _UsuarioPuntoImpresionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.UsuarioPuntoImpresionIf addUsuarioPuntoImpresion(com.spirit.general.entity.UsuarioPuntoImpresionIf model) throws GenericBusinessException;

   void saveUsuarioPuntoImpresion(com.spirit.general.entity.UsuarioPuntoImpresionIf model) throws GenericBusinessException;

   void deleteUsuarioPuntoImpresion(java.lang.Long id) throws GenericBusinessException;

   Collection findUsuarioPuntoImpresionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.UsuarioPuntoImpresionIf getUsuarioPuntoImpresion(java.lang.Long id) throws GenericBusinessException;

   Collection getUsuarioPuntoImpresionList() throws GenericBusinessException;

   Collection getUsuarioPuntoImpresionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getUsuarioPuntoImpresionListSize() throws GenericBusinessException;
    java.util.Collection findUsuarioPuntoImpresionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findUsuarioPuntoImpresionByPuntoimpresionId(java.lang.Long puntoimpresionId) throws GenericBusinessException;
    java.util.Collection findUsuarioPuntoImpresionByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;

}
