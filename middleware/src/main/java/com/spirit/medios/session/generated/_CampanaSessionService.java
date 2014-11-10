package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CampanaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.CampanaIf addCampana(com.spirit.medios.entity.CampanaIf model) throws GenericBusinessException;

   void saveCampana(com.spirit.medios.entity.CampanaIf model) throws GenericBusinessException;

   void deleteCampana(java.lang.Long id) throws GenericBusinessException;

   Collection findCampanaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.CampanaIf getCampana(java.lang.Long id) throws GenericBusinessException;

   Collection getCampanaList() throws GenericBusinessException;

   Collection getCampanaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCampanaListSize() throws GenericBusinessException;
    java.util.Collection findCampanaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCampanaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findCampanaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findCampanaByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findCampanaByFechaini(java.sql.Date fechaini) throws GenericBusinessException;
    java.util.Collection findCampanaByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findCampanaByPublicoObjetivo(java.lang.String publicoObjetivo) throws GenericBusinessException;
    java.util.Collection findCampanaByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findCampanaByUsuarioCreacionId(java.lang.Long usuarioCreacionId) throws GenericBusinessException;
    java.util.Collection findCampanaByFechaCreacion(java.sql.Date fechaCreacion) throws GenericBusinessException;
    java.util.Collection findCampanaByUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) throws GenericBusinessException;
    java.util.Collection findCampanaByFechaActualizacion(java.sql.Date fechaActualizacion) throws GenericBusinessException;

}
