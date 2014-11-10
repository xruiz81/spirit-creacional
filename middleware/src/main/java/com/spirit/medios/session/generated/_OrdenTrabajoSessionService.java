package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _OrdenTrabajoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.OrdenTrabajoIf addOrdenTrabajo(com.spirit.medios.entity.OrdenTrabajoIf model) throws GenericBusinessException;

   void saveOrdenTrabajo(com.spirit.medios.entity.OrdenTrabajoIf model) throws GenericBusinessException;

   void deleteOrdenTrabajo(java.lang.Long id) throws GenericBusinessException;

   Collection findOrdenTrabajoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.OrdenTrabajoIf getOrdenTrabajo(java.lang.Long id) throws GenericBusinessException;

   Collection getOrdenTrabajoList() throws GenericBusinessException;

   Collection getOrdenTrabajoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getOrdenTrabajoListSize() throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByClienteoficinaId(java.lang.Long clienteoficinaId) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByCampanaId(java.lang.Long campanaId) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByFecha(java.sql.Timestamp fecha) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByFechalimite(java.sql.Timestamp fechalimite) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByFechaentrega(java.sql.Timestamp fechaentrega) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByUrlPropuesta(java.lang.String urlPropuesta) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByUsuarioCreacionId(java.lang.Long usuarioCreacionId) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByFechaActualizacion(java.sql.Timestamp fechaActualizacion) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByEquipoId(java.lang.Long equipoId) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByTimetracker(java.lang.String timetracker) throws GenericBusinessException;

}
