package com.spirit.nomina.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ImpuestoRentaPersNatSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.ImpuestoRentaPersNatIf addImpuestoRentaPersNat(com.spirit.nomina.entity.ImpuestoRentaPersNatIf model) throws GenericBusinessException;

   void saveImpuestoRentaPersNat(com.spirit.nomina.entity.ImpuestoRentaPersNatIf model) throws GenericBusinessException;

   void deleteImpuestoRentaPersNat(java.lang.Long id) throws GenericBusinessException;

   Collection findImpuestoRentaPersNatByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.ImpuestoRentaPersNatIf getImpuestoRentaPersNat(java.lang.Long id) throws GenericBusinessException;

   Collection getImpuestoRentaPersNatList() throws GenericBusinessException;

   Collection getImpuestoRentaPersNatList(int startIndex, int endIndex) throws GenericBusinessException;

   int getImpuestoRentaPersNatListSize() throws GenericBusinessException;
    java.util.Collection findImpuestoRentaPersNatById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findImpuestoRentaPersNatByFraccionBasica(java.math.BigDecimal fraccionBasica) throws GenericBusinessException;
    java.util.Collection findImpuestoRentaPersNatByExcesoHasta(java.math.BigDecimal excesoHasta) throws GenericBusinessException;
    java.util.Collection findImpuestoRentaPersNatByImpFraccionBasica(java.math.BigDecimal impFraccionBasica) throws GenericBusinessException;
    java.util.Collection findImpuestoRentaPersNatByPorcentajeImpFraccionBasica(java.math.BigDecimal porcentajeImpFraccionBasica) throws GenericBusinessException;
    java.util.Collection findImpuestoRentaPersNatByFechaInicio(java.sql.Date fechaInicio) throws GenericBusinessException;
    java.util.Collection findImpuestoRentaPersNatByFechaFin(java.sql.Date fechaFin) throws GenericBusinessException;

}
