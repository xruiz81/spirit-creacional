package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoParametroSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.TipoParametroIf addTipoParametro(com.spirit.general.entity.TipoParametroIf model) throws GenericBusinessException;

   void saveTipoParametro(com.spirit.general.entity.TipoParametroIf model) throws GenericBusinessException;

   void deleteTipoParametro(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoParametroByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.TipoParametroIf getTipoParametro(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoParametroList() throws GenericBusinessException;

   Collection getTipoParametroList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoParametroListSize() throws GenericBusinessException;
    java.util.Collection findTipoParametroById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoParametroByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoParametroByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoParametroByTipo(java.lang.String tipo) throws GenericBusinessException;
    java.util.Collection findTipoParametroByMascara(java.lang.String mascara) throws GenericBusinessException;
    java.util.Collection findTipoParametroByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
