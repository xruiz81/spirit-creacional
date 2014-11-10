package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TiempoParcialDotDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.TiempoParcialDotDetalleIf addTiempoParcialDotDetalle(com.spirit.medios.entity.TiempoParcialDotDetalleIf model) throws GenericBusinessException;

   void saveTiempoParcialDotDetalle(com.spirit.medios.entity.TiempoParcialDotDetalleIf model) throws GenericBusinessException;

   void deleteTiempoParcialDotDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findTiempoParcialDotDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.TiempoParcialDotDetalleIf getTiempoParcialDotDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getTiempoParcialDotDetalleList() throws GenericBusinessException;

   Collection getTiempoParcialDotDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTiempoParcialDotDetalleListSize() throws GenericBusinessException;
    java.util.Collection findTiempoParcialDotDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTiempoParcialDotDetalleByFecha(java.lang.Long fecha) throws GenericBusinessException;
    java.util.Collection findTiempoParcialDotDetalleByHoraInicio(java.lang.Long horaInicio) throws GenericBusinessException;
    java.util.Collection findTiempoParcialDotDetalleByHoraFin(java.lang.Long horaFin) throws GenericBusinessException;
    java.util.Collection findTiempoParcialDotDetalleByTiempo(java.lang.Long tiempo) throws GenericBusinessException;
    java.util.Collection findTiempoParcialDotDetalleByIdTiempoParcialDot(java.lang.Long idTiempoParcialDot) throws GenericBusinessException;

}
