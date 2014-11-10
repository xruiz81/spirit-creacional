package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CampanaDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.CampanaDetalleIf addCampanaDetalle(com.spirit.medios.entity.CampanaDetalleIf model) throws GenericBusinessException;

   void saveCampanaDetalle(com.spirit.medios.entity.CampanaDetalleIf model) throws GenericBusinessException;

   void deleteCampanaDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findCampanaDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.CampanaDetalleIf getCampanaDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getCampanaDetalleList() throws GenericBusinessException;

   Collection getCampanaDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCampanaDetalleListSize() throws GenericBusinessException;
    java.util.Collection findCampanaDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCampanaDetalleByClienteZonaId(java.lang.Long clienteZonaId) throws GenericBusinessException;
    java.util.Collection findCampanaDetalleByCampanaId(java.lang.Long campanaId) throws GenericBusinessException;
    java.util.Collection findCampanaDetalleByParticipacion(java.math.BigDecimal participacion) throws GenericBusinessException;
    java.util.Collection findCampanaDetalleByDescripcion(java.lang.String descripcion) throws GenericBusinessException;

}
