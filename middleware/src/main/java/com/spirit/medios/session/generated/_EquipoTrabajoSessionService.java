package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _EquipoTrabajoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.EquipoTrabajoIf addEquipoTrabajo(com.spirit.medios.entity.EquipoTrabajoIf model) throws GenericBusinessException;

   void saveEquipoTrabajo(com.spirit.medios.entity.EquipoTrabajoIf model) throws GenericBusinessException;

   void deleteEquipoTrabajo(java.lang.Long id) throws GenericBusinessException;

   Collection findEquipoTrabajoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.EquipoTrabajoIf getEquipoTrabajo(java.lang.Long id) throws GenericBusinessException;

   Collection getEquipoTrabajoList() throws GenericBusinessException;

   Collection getEquipoTrabajoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getEquipoTrabajoListSize() throws GenericBusinessException;
    java.util.Collection findEquipoTrabajoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findEquipoTrabajoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findEquipoTrabajoByTipoordenId(java.lang.Long tipoordenId) throws GenericBusinessException;
    java.util.Collection findEquipoTrabajoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findEquipoTrabajoByFechaini(java.sql.Date fechaini) throws GenericBusinessException;
    java.util.Collection findEquipoTrabajoByFechafin(java.sql.Date fechafin) throws GenericBusinessException;
    java.util.Collection findEquipoTrabajoByEstado(java.lang.String estado) throws GenericBusinessException;

}
