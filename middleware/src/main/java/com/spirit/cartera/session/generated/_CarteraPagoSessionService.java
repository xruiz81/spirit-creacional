package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CarteraPagoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.CarteraPagoIf addCarteraPago(com.spirit.cartera.entity.CarteraPagoIf model) throws GenericBusinessException;

   void saveCarteraPago(com.spirit.cartera.entity.CarteraPagoIf model) throws GenericBusinessException;

   void deleteCarteraPago(java.lang.Long id) throws GenericBusinessException;

   Collection findCarteraPagoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.CarteraPagoIf getCarteraPago(java.lang.Long id) throws GenericBusinessException;

   Collection getCarteraPagoList() throws GenericBusinessException;

   Collection getCarteraPagoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCarteraPagoListSize() throws GenericBusinessException;
    java.util.Collection findCarteraPagoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCarteraPagoByCarteraPagoId(java.lang.Long carteraPagoId) throws GenericBusinessException;
    java.util.Collection findCarteraPagoByFechaAprobacion(java.sql.Timestamp fechaAprobacion) throws GenericBusinessException;
    java.util.Collection findCarteraPagoByUsuarioAprobacionId(java.lang.Long usuarioAprobacionId) throws GenericBusinessException;
    java.util.Collection findCarteraPagoByFechaPago(java.sql.Timestamp fechaPago) throws GenericBusinessException;
    java.util.Collection findCarteraPagoByUsuarioPagoId(java.lang.Long usuarioPagoId) throws GenericBusinessException;
    java.util.Collection findCarteraPagoBySecuencialMulticash(java.lang.String secuencialMulticash) throws GenericBusinessException;
    java.util.Collection findCarteraPagoByArchivoMulticash(java.lang.String archivoMulticash) throws GenericBusinessException;
    java.util.Collection findCarteraPagoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findCarteraPagoByFechaEmision(java.sql.Timestamp fechaEmision) throws GenericBusinessException;
    java.util.Collection findCarteraPagoByUsuarioEmisionId(java.lang.Long usuarioEmisionId) throws GenericBusinessException;
    java.util.Collection findCarteraPagoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
