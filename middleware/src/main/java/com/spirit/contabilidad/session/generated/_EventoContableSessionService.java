package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _EventoContableSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.EventoContableIf addEventoContable(com.spirit.contabilidad.entity.EventoContableIf model) throws GenericBusinessException;

   void saveEventoContable(com.spirit.contabilidad.entity.EventoContableIf model) throws GenericBusinessException;

   void deleteEventoContable(java.lang.Long id) throws GenericBusinessException;

   Collection findEventoContableByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.EventoContableIf getEventoContable(java.lang.Long id) throws GenericBusinessException;

   Collection getEventoContableList() throws GenericBusinessException;

   Collection getEventoContableList(int startIndex, int endIndex) throws GenericBusinessException;

   int getEventoContableListSize() throws GenericBusinessException;
    java.util.Collection findEventoContableById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findEventoContableByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findEventoContableByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findEventoContableByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findEventoContableByModuloId(java.lang.Long moduloId) throws GenericBusinessException;
    java.util.Collection findEventoContableByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findEventoContableByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findEventoContableByLineaId(java.lang.Long lineaId) throws GenericBusinessException;
    java.util.Collection findEventoContableByPlanCuentaId(java.lang.Long planCuentaId) throws GenericBusinessException;
    java.util.Collection findEventoContableByEtapa(java.lang.Long etapa) throws GenericBusinessException;
    java.util.Collection findEventoContableByAutorizacionRequerida(java.lang.String autorizacionRequerida) throws GenericBusinessException;
    java.util.Collection findEventoContableByAgruparDetalles(java.lang.String agruparDetalles) throws GenericBusinessException;
    java.util.Collection findEventoContableByUsarDetalleDescripcion(java.lang.String usarDetalleDescripcion) throws GenericBusinessException;
    java.util.Collection findEventoContableByValidoAlGuardar(java.lang.String validoAlGuardar) throws GenericBusinessException;
    java.util.Collection findEventoContableByValidoAlActualizar(java.lang.String validoAlActualizar) throws GenericBusinessException;
    java.util.Collection findEventoContableBySubtipoAsientoId(java.lang.Long subtipoAsientoId) throws GenericBusinessException;

}
