package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CajaSesionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.CajaSesionIf addCajaSesion(com.spirit.pos.entity.CajaSesionIf model) throws GenericBusinessException;

   void saveCajaSesion(com.spirit.pos.entity.CajaSesionIf model) throws GenericBusinessException;

   void deleteCajaSesion(java.lang.Long id) throws GenericBusinessException;

   Collection findCajaSesionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.CajaSesionIf getCajaSesion(java.lang.Long id) throws GenericBusinessException;

   Collection getCajaSesionList() throws GenericBusinessException;

   Collection getCajaSesionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCajaSesionListSize() throws GenericBusinessException;
    java.util.Collection findCajaSesionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCajaSesionByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findCajaSesionByCajaId(java.lang.Long cajaId) throws GenericBusinessException;
    java.util.Collection findCajaSesionByFechaini(java.sql.Timestamp fechaini) throws GenericBusinessException;
    java.util.Collection findCajaSesionByFechafin(java.sql.Timestamp fechafin) throws GenericBusinessException;
    java.util.Collection findCajaSesionByValorInicial(java.math.BigDecimal valorInicial) throws GenericBusinessException;
    java.util.Collection findCajaSesionByValorFinal(java.math.BigDecimal valorFinal) throws GenericBusinessException;
    java.util.Collection findCajaSesionByDescuadre(java.math.BigDecimal descuadre) throws GenericBusinessException;
    java.util.Collection findCajaSesionByValorMultas(java.math.BigDecimal valorMultas) throws GenericBusinessException;
    java.util.Collection findCajaSesionByValorDocumentos(java.math.BigDecimal valorDocumentos) throws GenericBusinessException;
    java.util.Collection findCajaSesionByTurno(java.lang.String turno) throws GenericBusinessException;
    java.util.Collection findCajaSesionByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findCajaSesionByRevisado(java.lang.String revisado) throws GenericBusinessException;

}
