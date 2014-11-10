package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PresupuestoFacturacionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PresupuestoFacturacionIf addPresupuestoFacturacion(com.spirit.medios.entity.PresupuestoFacturacionIf model) throws GenericBusinessException;

   void savePresupuestoFacturacion(com.spirit.medios.entity.PresupuestoFacturacionIf model) throws GenericBusinessException;

   void deletePresupuestoFacturacion(java.lang.Long id) throws GenericBusinessException;

   Collection findPresupuestoFacturacionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PresupuestoFacturacionIf getPresupuestoFacturacion(java.lang.Long id) throws GenericBusinessException;

   Collection getPresupuestoFacturacionList() throws GenericBusinessException;

   Collection getPresupuestoFacturacionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPresupuestoFacturacionListSize() throws GenericBusinessException;
    java.util.Collection findPresupuestoFacturacionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPresupuestoFacturacionByPresupuestoDetalleId(java.lang.Long presupuestoDetalleId) throws GenericBusinessException;
    java.util.Collection findPresupuestoFacturacionByFacturaId(java.lang.Long facturaId) throws GenericBusinessException;
    java.util.Collection findPresupuestoFacturacionByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findPresupuestoFacturacionByTipo(java.lang.String tipo) throws GenericBusinessException;

}
