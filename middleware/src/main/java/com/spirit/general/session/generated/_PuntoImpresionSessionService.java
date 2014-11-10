package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PuntoImpresionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.PuntoImpresionIf addPuntoImpresion(com.spirit.general.entity.PuntoImpresionIf model) throws GenericBusinessException;

   void savePuntoImpresion(com.spirit.general.entity.PuntoImpresionIf model) throws GenericBusinessException;

   void deletePuntoImpresion(java.lang.Long id) throws GenericBusinessException;

   Collection findPuntoImpresionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.PuntoImpresionIf getPuntoImpresion(java.lang.Long id) throws GenericBusinessException;

   Collection getPuntoImpresionList() throws GenericBusinessException;

   Collection getPuntoImpresionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPuntoImpresionListSize() throws GenericBusinessException;
    java.util.Collection findPuntoImpresionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPuntoImpresionByTipodocumentoId(java.lang.Long tipodocumentoId) throws GenericBusinessException;
    java.util.Collection findPuntoImpresionByCajaId(java.lang.Long cajaId) throws GenericBusinessException;
    java.util.Collection findPuntoImpresionBySerie(java.lang.String serie) throws GenericBusinessException;
    java.util.Collection findPuntoImpresionByImpresora(java.lang.String impresora) throws GenericBusinessException;
    java.util.Collection findPuntoImpresionByEstado(java.lang.String estado) throws GenericBusinessException;

}
