package com.spirit.crm.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CorporacionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.crm.entity.CorporacionIf addCorporacion(com.spirit.crm.entity.CorporacionIf model) throws GenericBusinessException;

   void saveCorporacion(com.spirit.crm.entity.CorporacionIf model) throws GenericBusinessException;

   void deleteCorporacion(java.lang.Long id) throws GenericBusinessException;

   Collection findCorporacionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.crm.entity.CorporacionIf getCorporacion(java.lang.Long id) throws GenericBusinessException;

   Collection getCorporacionList() throws GenericBusinessException;

   Collection getCorporacionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCorporacionListSize() throws GenericBusinessException;
    java.util.Collection findCorporacionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCorporacionByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findCorporacionByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findCorporacionByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findCorporacionByRepresentante(java.lang.String representante) throws GenericBusinessException;
    java.util.Collection findCorporacionByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findCorporacionByEstado(java.lang.String estado) throws GenericBusinessException;

}
