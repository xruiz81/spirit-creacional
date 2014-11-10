package com.spirit.crm.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoIndicadorSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.crm.entity.TipoIndicadorIf addTipoIndicador(com.spirit.crm.entity.TipoIndicadorIf model) throws GenericBusinessException;

   void saveTipoIndicador(com.spirit.crm.entity.TipoIndicadorIf model) throws GenericBusinessException;

   void deleteTipoIndicador(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoIndicadorByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.crm.entity.TipoIndicadorIf getTipoIndicador(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoIndicadorList() throws GenericBusinessException;

   Collection getTipoIndicadorList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoIndicadorListSize() throws GenericBusinessException;
    java.util.Collection findTipoIndicadorById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoIndicadorByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoIndicadorByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoIndicadorByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findTipoIndicadorByAcumulativo(java.lang.String acumulativo) throws GenericBusinessException;

}
