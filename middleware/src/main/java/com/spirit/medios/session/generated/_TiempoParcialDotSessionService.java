package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TiempoParcialDotSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.TiempoParcialDotIf addTiempoParcialDot(com.spirit.medios.entity.TiempoParcialDotIf model) throws GenericBusinessException;

   void saveTiempoParcialDot(com.spirit.medios.entity.TiempoParcialDotIf model) throws GenericBusinessException;

   void deleteTiempoParcialDot(java.lang.Long id) throws GenericBusinessException;

   Collection findTiempoParcialDotByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.TiempoParcialDotIf getTiempoParcialDot(java.lang.Long id) throws GenericBusinessException;

   Collection getTiempoParcialDotList() throws GenericBusinessException;

   Collection getTiempoParcialDotList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTiempoParcialDotListSize() throws GenericBusinessException;
    java.util.Collection findTiempoParcialDotById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTiempoParcialDotByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findTiempoParcialDotByFechaInicio(java.lang.Long fechaInicio) throws GenericBusinessException;
    java.util.Collection findTiempoParcialDotByFechaFin(java.lang.Long fechaFin) throws GenericBusinessException;
    java.util.Collection findTiempoParcialDotByTiempo(java.lang.Long tiempo) throws GenericBusinessException;
    java.util.Collection findTiempoParcialDotByIdOrdenTrabajoDetalle(java.lang.Long idOrdenTrabajoDetalle) throws GenericBusinessException;

}
