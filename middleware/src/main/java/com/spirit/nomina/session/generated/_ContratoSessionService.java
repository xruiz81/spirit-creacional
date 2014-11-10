package com.spirit.nomina.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ContratoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.ContratoIf addContrato(com.spirit.nomina.entity.ContratoIf model) throws GenericBusinessException;

   void saveContrato(com.spirit.nomina.entity.ContratoIf model) throws GenericBusinessException;

   void deleteContrato(java.lang.Long id) throws GenericBusinessException;

   Collection findContratoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.ContratoIf getContrato(java.lang.Long id) throws GenericBusinessException;

   Collection getContratoList() throws GenericBusinessException;

   Collection getContratoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getContratoListSize() throws GenericBusinessException;
    java.util.Collection findContratoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findContratoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findContratoByFechaElaboracion(java.sql.Date fechaElaboracion) throws GenericBusinessException;
    java.util.Collection findContratoByTipocontratoId(java.lang.Long tipocontratoId) throws GenericBusinessException;
    java.util.Collection findContratoByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findContratoByFechaInicio(java.sql.Date fechaInicio) throws GenericBusinessException;
    java.util.Collection findContratoByFechaFin(java.sql.Date fechaFin) throws GenericBusinessException;
    java.util.Collection findContratoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findContratoByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findContratoByUrl(java.lang.String url) throws GenericBusinessException;
    java.util.Collection findContratoByFondoReservaDiasPrevios(java.lang.Integer fondoReservaDiasPrevios) throws GenericBusinessException;

}
