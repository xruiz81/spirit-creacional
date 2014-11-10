package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CajaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.CajaIf addCaja(com.spirit.general.entity.CajaIf model) throws GenericBusinessException;

   void saveCaja(com.spirit.general.entity.CajaIf model) throws GenericBusinessException;

   void deleteCaja(java.lang.Long id) throws GenericBusinessException;

   Collection findCajaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.CajaIf getCaja(java.lang.Long id) throws GenericBusinessException;

   Collection getCajaList() throws GenericBusinessException;

   Collection getCajaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCajaListSize() throws GenericBusinessException;
    java.util.Collection findCajaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCajaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findCajaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findCajaByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findCajaByTurno(java.lang.String turno) throws GenericBusinessException;
    java.util.Collection findCajaByEstado(java.lang.String estado) throws GenericBusinessException;

}
