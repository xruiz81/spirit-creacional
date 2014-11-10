package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TeclasConfiguracionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.TeclasConfiguracionIf addTeclasConfiguracion(com.spirit.pos.entity.TeclasConfiguracionIf model) throws GenericBusinessException;

   void saveTeclasConfiguracion(com.spirit.pos.entity.TeclasConfiguracionIf model) throws GenericBusinessException;

   void deleteTeclasConfiguracion(java.lang.Long id) throws GenericBusinessException;

   Collection findTeclasConfiguracionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.TeclasConfiguracionIf getTeclasConfiguracion(java.lang.Long id) throws GenericBusinessException;

   Collection getTeclasConfiguracionList() throws GenericBusinessException;

   Collection getTeclasConfiguracionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTeclasConfiguracionListSize() throws GenericBusinessException;
    java.util.Collection findTeclasConfiguracionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTeclasConfiguracionByTeclasNombre(java.lang.String teclasNombre) throws GenericBusinessException;
    java.util.Collection findTeclasConfiguracionByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findTeclasConfiguracionByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTeclasConfiguracionByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findTeclasConfiguracionByMascara(java.lang.String mascara) throws GenericBusinessException;

}
