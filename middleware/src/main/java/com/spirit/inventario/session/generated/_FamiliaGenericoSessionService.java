package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _FamiliaGenericoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.FamiliaGenericoIf addFamiliaGenerico(com.spirit.inventario.entity.FamiliaGenericoIf model) throws GenericBusinessException;

   void saveFamiliaGenerico(com.spirit.inventario.entity.FamiliaGenericoIf model) throws GenericBusinessException;

   void deleteFamiliaGenerico(java.lang.Long id) throws GenericBusinessException;

   Collection findFamiliaGenericoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.FamiliaGenericoIf getFamiliaGenerico(java.lang.Long id) throws GenericBusinessException;

   Collection getFamiliaGenericoList() throws GenericBusinessException;

   Collection getFamiliaGenericoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getFamiliaGenericoListSize() throws GenericBusinessException;
    java.util.Collection findFamiliaGenericoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findFamiliaGenericoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findFamiliaGenericoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findFamiliaGenericoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
