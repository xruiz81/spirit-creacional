package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _OrdenTrabajoDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.OrdenTrabajoDetalleIf addOrdenTrabajoDetalle(com.spirit.medios.entity.OrdenTrabajoDetalleIf model) throws GenericBusinessException;

   void saveOrdenTrabajoDetalle(com.spirit.medios.entity.OrdenTrabajoDetalleIf model) throws GenericBusinessException;

   void deleteOrdenTrabajoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findOrdenTrabajoDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.OrdenTrabajoDetalleIf getOrdenTrabajoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getOrdenTrabajoDetalleList() throws GenericBusinessException;

   Collection getOrdenTrabajoDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getOrdenTrabajoDetalleListSize() throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoDetalleByOrdenId(java.lang.Long ordenId) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoDetalleBySubtipoId(java.lang.Long subtipoId) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoDetalleByEquipoId(java.lang.Long equipoId) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoDetalleByAsignadoaId(java.lang.Long asignadoaId) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoDetalleByFechalimite(java.sql.Date fechalimite) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoDetalleByFechaentrega(java.sql.Date fechaentrega) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoDetalleByUrlDescripcion(java.lang.String urlDescripcion) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoDetalleByUrlPropuesta(java.lang.String urlPropuesta) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoDetalleByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoDetalleByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoDetalleByFecha(java.sql.Timestamp fecha) throws GenericBusinessException;

}
