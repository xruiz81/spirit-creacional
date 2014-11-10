package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CarteraAfectaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.CarteraAfectaIf addCarteraAfecta(com.spirit.cartera.entity.CarteraAfectaIf model) throws GenericBusinessException;

   void saveCarteraAfecta(com.spirit.cartera.entity.CarteraAfectaIf model) throws GenericBusinessException;

   void deleteCarteraAfecta(java.lang.Long id) throws GenericBusinessException;

   Collection findCarteraAfectaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.CarteraAfectaIf getCarteraAfecta(java.lang.Long id) throws GenericBusinessException;

   Collection getCarteraAfectaList() throws GenericBusinessException;

   Collection getCarteraAfectaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCarteraAfectaListSize() throws GenericBusinessException;
    java.util.Collection findCarteraAfectaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCarteraAfectaByCarteradetalleId(java.lang.Long carteradetalleId) throws GenericBusinessException;
    java.util.Collection findCarteraAfectaByCarteraafectaId(java.lang.Long carteraafectaId) throws GenericBusinessException;
    java.util.Collection findCarteraAfectaByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findCarteraAfectaByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findCarteraAfectaByFechaCreacion(java.sql.Date fechaCreacion) throws GenericBusinessException;
    java.util.Collection findCarteraAfectaByFechaAplicacion(java.sql.Date fechaAplicacion) throws GenericBusinessException;
    java.util.Collection findCarteraAfectaByCartera(java.lang.String cartera) throws GenericBusinessException;

}
