package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _BodegaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.BodegaIf addBodega(com.spirit.inventario.entity.BodegaIf model) throws GenericBusinessException;

   void saveBodega(com.spirit.inventario.entity.BodegaIf model) throws GenericBusinessException;

   void deleteBodega(java.lang.Long id) throws GenericBusinessException;

   Collection findBodegaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.BodegaIf getBodega(java.lang.Long id) throws GenericBusinessException;

   Collection getBodegaList() throws GenericBusinessException;

   Collection getBodegaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getBodegaListSize() throws GenericBusinessException;
    java.util.Collection findBodegaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findBodegaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findBodegaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findBodegaByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findBodegaByFuncionBodegaId(java.lang.Long funcionBodegaId) throws GenericBusinessException;
    java.util.Collection findBodegaByTipoBodegaId(java.lang.Long tipoBodegaId) throws GenericBusinessException;
    java.util.Collection findBodegaByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findBodegaByEstado(java.lang.String estado) throws GenericBusinessException;

}
