package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ReunionAsistenteSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.ReunionAsistenteIf addReunionAsistente(com.spirit.medios.entity.ReunionAsistenteIf model) throws GenericBusinessException;

   void saveReunionAsistente(com.spirit.medios.entity.ReunionAsistenteIf model) throws GenericBusinessException;

   void deleteReunionAsistente(java.lang.Long id) throws GenericBusinessException;

   Collection findReunionAsistenteByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.ReunionAsistenteIf getReunionAsistente(java.lang.Long id) throws GenericBusinessException;

   Collection getReunionAsistenteList() throws GenericBusinessException;

   Collection getReunionAsistenteList(int startIndex, int endIndex) throws GenericBusinessException;

   int getReunionAsistenteListSize() throws GenericBusinessException;
    java.util.Collection findReunionAsistenteById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findReunionAsistenteByReunionId(java.lang.Long reunionId) throws GenericBusinessException;
    java.util.Collection findReunionAsistenteByClienteContactoId(java.lang.Long clienteContactoId) throws GenericBusinessException;
    java.util.Collection findReunionAsistenteByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findReunionAsistenteByClienteContacto(java.lang.String clienteContacto) throws GenericBusinessException;

}
