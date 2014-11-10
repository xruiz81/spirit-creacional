package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PagoTarjetaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.PagoTarjetaIf addPagoTarjeta(com.spirit.pos.entity.PagoTarjetaIf model) throws GenericBusinessException;

   void savePagoTarjeta(com.spirit.pos.entity.PagoTarjetaIf model) throws GenericBusinessException;

   void deletePagoTarjeta(java.lang.Long id) throws GenericBusinessException;

   Collection findPagoTarjetaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.PagoTarjetaIf getPagoTarjeta(java.lang.Long id) throws GenericBusinessException;

   Collection getPagoTarjetaList() throws GenericBusinessException;

   Collection getPagoTarjetaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPagoTarjetaListSize() throws GenericBusinessException;
    java.util.Collection findPagoTarjetaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPagoTarjetaByTipoTarjeta(java.lang.String tipoTarjeta) throws GenericBusinessException;
    java.util.Collection findPagoTarjetaByNombreCliente(java.lang.String nombreCliente) throws GenericBusinessException;
    java.util.Collection findPagoTarjetaByIdentificacion(java.lang.String identificacion) throws GenericBusinessException;
    java.util.Collection findPagoTarjetaByTelefono(java.lang.String telefono) throws GenericBusinessException;
    java.util.Collection findPagoTarjetaByNoReferencia(java.lang.String noReferencia) throws GenericBusinessException;
    java.util.Collection findPagoTarjetaByNoVoucher(java.lang.String noVoucher) throws GenericBusinessException;
    java.util.Collection findPagoTarjetaByNoAutorizacion(java.lang.String noAutorizacion) throws GenericBusinessException;
    java.util.Collection findPagoTarjetaByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findPagoTarjetaByFecha(java.sql.Timestamp fecha) throws GenericBusinessException;

}
