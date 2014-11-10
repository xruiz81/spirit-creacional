package com.spirit.rrhh.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _EmpleadoOrganizacionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.rrhh.entity.EmpleadoOrganizacionIf addEmpleadoOrganizacion(com.spirit.rrhh.entity.EmpleadoOrganizacionIf model) throws GenericBusinessException;

   void saveEmpleadoOrganizacion(com.spirit.rrhh.entity.EmpleadoOrganizacionIf model) throws GenericBusinessException;

   void deleteEmpleadoOrganizacion(java.lang.Long id) throws GenericBusinessException;

   Collection findEmpleadoOrganizacionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.rrhh.entity.EmpleadoOrganizacionIf getEmpleadoOrganizacion(java.lang.Long id) throws GenericBusinessException;

   Collection getEmpleadoOrganizacionList() throws GenericBusinessException;

   Collection getEmpleadoOrganizacionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getEmpleadoOrganizacionListSize() throws GenericBusinessException;
    java.util.Collection findEmpleadoOrganizacionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findEmpleadoOrganizacionByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findEmpleadoOrganizacionByDepartamento(java.lang.Long departamento) throws GenericBusinessException;
    java.util.Collection findEmpleadoOrganizacionByTipoEmpleadoId(java.lang.Long tipoEmpleadoId) throws GenericBusinessException;
    java.util.Collection findEmpleadoOrganizacionByFechaInicio(java.sql.Timestamp fechaInicio) throws GenericBusinessException;
    java.util.Collection findEmpleadoOrganizacionByFechaFin(java.sql.Timestamp fechaFin) throws GenericBusinessException;
    java.util.Collection findEmpleadoOrganizacionByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findEmpleadoOrganizacionByArchivoAdjunto(java.lang.String archivoAdjunto) throws GenericBusinessException;

}
