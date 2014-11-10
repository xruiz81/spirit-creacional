package com.spirit.rrhh.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _EmpleadoIdiomasSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.rrhh.entity.EmpleadoIdiomasIf addEmpleadoIdiomas(com.spirit.rrhh.entity.EmpleadoIdiomasIf model) throws GenericBusinessException;

   void saveEmpleadoIdiomas(com.spirit.rrhh.entity.EmpleadoIdiomasIf model) throws GenericBusinessException;

   void deleteEmpleadoIdiomas(java.lang.Long id) throws GenericBusinessException;

   Collection findEmpleadoIdiomasByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.rrhh.entity.EmpleadoIdiomasIf getEmpleadoIdiomas(java.lang.Long id) throws GenericBusinessException;

   Collection getEmpleadoIdiomasList() throws GenericBusinessException;

   Collection getEmpleadoIdiomasList(int startIndex, int endIndex) throws GenericBusinessException;

   int getEmpleadoIdiomasListSize() throws GenericBusinessException;
    java.util.Collection findEmpleadoIdiomasById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findEmpleadoIdiomasByEmpleadoId(java.lang.Long empleadoId) throws GenericBusinessException;
    java.util.Collection findEmpleadoIdiomasByIdiomaId(java.lang.Long idiomaId) throws GenericBusinessException;
    java.util.Collection findEmpleadoIdiomasByHabla(java.lang.String habla) throws GenericBusinessException;
    java.util.Collection findEmpleadoIdiomasByComprende(java.lang.String comprende) throws GenericBusinessException;
    java.util.Collection findEmpleadoIdiomasByLee(java.lang.String lee) throws GenericBusinessException;
    java.util.Collection findEmpleadoIdiomasByEscribe(java.lang.String escribe) throws GenericBusinessException;

}
