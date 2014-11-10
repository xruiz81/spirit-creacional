package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _Timetracker2SessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.Timetracker2If addTimetracker2(com.spirit.medios.entity.Timetracker2If model) throws GenericBusinessException;

   void saveTimetracker2(com.spirit.medios.entity.Timetracker2If model) throws GenericBusinessException;

   void deleteTimetracker2(java.lang.Long id) throws GenericBusinessException;

   Collection findTimetracker2ByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.Timetracker2If getTimetracker2(java.lang.Long id) throws GenericBusinessException;

   Collection getTimetracker2List() throws GenericBusinessException;

   Collection getTimetracker2List(int startIndex, int endIndex) throws GenericBusinessException;

   int getTimetracker2ListSize() throws GenericBusinessException;
    java.util.Collection findTimetracker2ById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTimetracker2ByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTimetracker2ByActividad(java.lang.String actividad) throws GenericBusinessException;
    java.util.Collection findTimetracker2ByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findTimetracker2ByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
