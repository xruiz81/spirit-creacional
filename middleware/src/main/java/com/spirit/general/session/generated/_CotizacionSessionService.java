package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CotizacionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.CotizacionIf addCotizacion(com.spirit.general.entity.CotizacionIf model) throws GenericBusinessException;

   void saveCotizacion(com.spirit.general.entity.CotizacionIf model) throws GenericBusinessException;

   void deleteCotizacion(java.lang.Long id) throws GenericBusinessException;

   Collection findCotizacionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.CotizacionIf getCotizacion(java.lang.Long id) throws GenericBusinessException;

   Collection getCotizacionList() throws GenericBusinessException;

   Collection getCotizacionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCotizacionListSize() throws GenericBusinessException;
    java.util.Collection findCotizacionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCotizacionByMonedaId(java.lang.Long monedaId) throws GenericBusinessException;
    java.util.Collection findCotizacionByMonedaequivId(java.lang.Long monedaequivId) throws GenericBusinessException;
    java.util.Collection findCotizacionByFecha(java.sql.Date fecha) throws GenericBusinessException;
    java.util.Collection findCotizacionByCotizacion(java.math.BigDecimal cotizacion) throws GenericBusinessException;

}
