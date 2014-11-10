package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _Timetracker2DetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.Timetracker2DetalleIf addTimetracker2Detalle(com.spirit.medios.entity.Timetracker2DetalleIf model) throws GenericBusinessException;

   void saveTimetracker2Detalle(com.spirit.medios.entity.Timetracker2DetalleIf model) throws GenericBusinessException;

   void deleteTimetracker2Detalle(java.lang.Long id) throws GenericBusinessException;

   Collection findTimetracker2DetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.Timetracker2DetalleIf getTimetracker2Detalle(java.lang.Long id) throws GenericBusinessException;

   Collection getTimetracker2DetalleList() throws GenericBusinessException;

   Collection getTimetracker2DetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTimetracker2DetalleListSize() throws GenericBusinessException;
    java.util.Collection findTimetracker2DetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTimetracker2DetalleByTimetracker2EmpleadoId(java.lang.Long timetracker2EmpleadoId) throws GenericBusinessException;
    java.util.Collection findTimetracker2DetalleByFecha(java.sql.Timestamp fecha) throws GenericBusinessException;
    java.util.Collection findTimetracker2DetalleByTiempo(java.lang.Float tiempo) throws GenericBusinessException;
    java.util.Collection findTimetracker2DetalleByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findTimetracker2DetalleByClienteOficinaId(java.lang.Long clienteOficinaId) throws GenericBusinessException;
    java.util.Collection findTimetracker2DetalleByTiempoDesignado(java.lang.Integer tiempoDesignado) throws GenericBusinessException;

}
