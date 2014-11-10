package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PropuestaDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PropuestaDetalleIf addPropuestaDetalle(com.spirit.medios.entity.PropuestaDetalleIf model) throws GenericBusinessException;

   void savePropuestaDetalle(com.spirit.medios.entity.PropuestaDetalleIf model) throws GenericBusinessException;

   void deletePropuestaDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findPropuestaDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PropuestaDetalleIf getPropuestaDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getPropuestaDetalleList() throws GenericBusinessException;

   Collection getPropuestaDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPropuestaDetalleListSize() throws GenericBusinessException;
    java.util.Collection findPropuestaDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPropuestaDetalleByPropuestaId(java.lang.Long propuestaId) throws GenericBusinessException;
    java.util.Collection findPropuestaDetalleByPresupuestoId(java.lang.Long presupuestoId) throws GenericBusinessException;
    java.util.Collection findPropuestaDetalleByPlanmedioId(java.lang.Long planmedioId) throws GenericBusinessException;

}
