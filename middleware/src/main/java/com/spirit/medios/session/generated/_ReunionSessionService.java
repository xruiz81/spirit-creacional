package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ReunionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.ReunionIf addReunion(com.spirit.medios.entity.ReunionIf model) throws GenericBusinessException;

   void saveReunion(com.spirit.medios.entity.ReunionIf model) throws GenericBusinessException;

   void deleteReunion(java.lang.Long id) throws GenericBusinessException;

   Collection findReunionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.ReunionIf getReunion(java.lang.Long id) throws GenericBusinessException;

   Collection getReunionList() throws GenericBusinessException;

   Collection getReunionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getReunionListSize() throws GenericBusinessException;
    java.util.Collection findReunionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findReunionByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findReunionByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findReunionByProspectocliente(java.lang.String prospectocliente) throws GenericBusinessException;
    java.util.Collection findReunionByFecha(java.sql.Date fecha) throws GenericBusinessException;
    java.util.Collection findReunionByFechaEnvio(java.sql.Date fechaEnvio) throws GenericBusinessException;
    java.util.Collection findReunionByNumEnviados(java.lang.Integer numEnviados) throws GenericBusinessException;
    java.util.Collection findReunionByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findReunionByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findReunionByUsuarioCreacionId(java.lang.Long usuarioCreacionId) throws GenericBusinessException;
    java.util.Collection findReunionByFechaCreacion(java.sql.Date fechaCreacion) throws GenericBusinessException;
    java.util.Collection findReunionByUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) throws GenericBusinessException;
    java.util.Collection findReunionByFechaActualizacion(java.sql.Date fechaActualizacion) throws GenericBusinessException;
    java.util.Collection findReunionByEjecutivoId(java.lang.Long ejecutivoId) throws GenericBusinessException;
    java.util.Collection findReunionByConCopia(java.lang.String conCopia) throws GenericBusinessException;
    java.util.Collection findReunionByLugarReunion(java.lang.String lugarReunion) throws GenericBusinessException;

}
