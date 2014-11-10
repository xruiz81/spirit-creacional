package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PropuestaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PropuestaIf addPropuesta(com.spirit.medios.entity.PropuestaIf model) throws GenericBusinessException;

   void savePropuesta(com.spirit.medios.entity.PropuestaIf model) throws GenericBusinessException;

   void deletePropuesta(java.lang.Long id) throws GenericBusinessException;

   Collection findPropuestaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PropuestaIf getPropuesta(java.lang.Long id) throws GenericBusinessException;

   Collection getPropuestaList() throws GenericBusinessException;

   Collection getPropuestaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPropuestaListSize() throws GenericBusinessException;
    java.util.Collection findPropuestaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPropuestaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPropuestaByOrdentrabajoId(java.lang.Long ordentrabajoId) throws GenericBusinessException;
    java.util.Collection findPropuestaByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findPropuestaByFecha(java.sql.Date fecha) throws GenericBusinessException;
    java.util.Collection findPropuestaByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findPropuestaByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findPropuestaByEstado(java.lang.String estado) throws GenericBusinessException;

}
