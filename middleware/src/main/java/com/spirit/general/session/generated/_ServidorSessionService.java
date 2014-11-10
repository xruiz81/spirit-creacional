package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ServidorSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.ServidorIf addServidor(com.spirit.general.entity.ServidorIf model) throws GenericBusinessException;

   void saveServidor(com.spirit.general.entity.ServidorIf model) throws GenericBusinessException;

   void deleteServidor(java.lang.Long id) throws GenericBusinessException;

   Collection findServidorByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.ServidorIf getServidor(java.lang.Long id) throws GenericBusinessException;

   Collection getServidorList() throws GenericBusinessException;

   Collection getServidorList(int startIndex, int endIndex) throws GenericBusinessException;

   int getServidorListSize() throws GenericBusinessException;
    java.util.Collection findServidorById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findServidorByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findServidorByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findServidorByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
