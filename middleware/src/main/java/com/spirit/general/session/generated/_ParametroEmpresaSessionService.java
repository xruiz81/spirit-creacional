package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ParametroEmpresaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.ParametroEmpresaIf addParametroEmpresa(com.spirit.general.entity.ParametroEmpresaIf model) throws GenericBusinessException;

   void saveParametroEmpresa(com.spirit.general.entity.ParametroEmpresaIf model) throws GenericBusinessException;

   void deleteParametroEmpresa(java.lang.Long id) throws GenericBusinessException;

   Collection findParametroEmpresaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.ParametroEmpresaIf getParametroEmpresa(java.lang.Long id) throws GenericBusinessException;

   Collection getParametroEmpresaList() throws GenericBusinessException;

   Collection getParametroEmpresaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getParametroEmpresaListSize() throws GenericBusinessException;
    java.util.Collection findParametroEmpresaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findParametroEmpresaByTipoparametroId(java.lang.Long tipoparametroId) throws GenericBusinessException;
    java.util.Collection findParametroEmpresaByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findParametroEmpresaByValor(java.lang.String valor) throws GenericBusinessException;
    java.util.Collection findParametroEmpresaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findParametroEmpresaByDescripcion(java.lang.String descripcion) throws GenericBusinessException;

}
