package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _OrdenMedioDetalleMapaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.OrdenMedioDetalleMapaIf addOrdenMedioDetalleMapa(com.spirit.medios.entity.OrdenMedioDetalleMapaIf model) throws GenericBusinessException;

   void saveOrdenMedioDetalleMapa(com.spirit.medios.entity.OrdenMedioDetalleMapaIf model) throws GenericBusinessException;

   void deleteOrdenMedioDetalleMapa(java.lang.Long id) throws GenericBusinessException;

   Collection findOrdenMedioDetalleMapaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.OrdenMedioDetalleMapaIf getOrdenMedioDetalleMapa(java.lang.Long id) throws GenericBusinessException;

   Collection getOrdenMedioDetalleMapaList() throws GenericBusinessException;

   Collection getOrdenMedioDetalleMapaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getOrdenMedioDetalleMapaListSize() throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleMapaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleMapaByOrdenMedioDetalleId(java.lang.Long ordenMedioDetalleId) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleMapaByFecha(java.sql.Timestamp fecha) throws GenericBusinessException;
    java.util.Collection findOrdenMedioDetalleMapaByFrecuencia(java.lang.Integer frecuencia) throws GenericBusinessException;

}
