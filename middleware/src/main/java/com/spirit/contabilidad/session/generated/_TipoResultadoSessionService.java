package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoResultadoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.TipoResultadoIf addTipoResultado(com.spirit.contabilidad.entity.TipoResultadoIf model) throws GenericBusinessException;

   void saveTipoResultado(com.spirit.contabilidad.entity.TipoResultadoIf model) throws GenericBusinessException;

   void deleteTipoResultado(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoResultadoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.TipoResultadoIf getTipoResultado(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoResultadoList() throws GenericBusinessException;

   Collection getTipoResultadoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoResultadoListSize() throws GenericBusinessException;
    java.util.Collection findTipoResultadoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoResultadoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoResultadoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoResultadoByOrden(java.lang.Long orden) throws GenericBusinessException;
    java.util.Collection findTipoResultadoByUtilidad(java.lang.String utilidad) throws GenericBusinessException;
    java.util.Collection findTipoResultadoByLeyendaResultado(java.lang.String leyendaResultado) throws GenericBusinessException;

}
