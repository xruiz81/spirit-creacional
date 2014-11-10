package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TarjetaTipoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.TarjetaTipoIf addTarjetaTipo(com.spirit.pos.entity.TarjetaTipoIf model) throws GenericBusinessException;

   void saveTarjetaTipo(com.spirit.pos.entity.TarjetaTipoIf model) throws GenericBusinessException;

   void deleteTarjetaTipo(java.lang.Long id) throws GenericBusinessException;

   Collection findTarjetaTipoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.TarjetaTipoIf getTarjetaTipo(java.lang.Long id) throws GenericBusinessException;

   Collection getTarjetaTipoList() throws GenericBusinessException;

   Collection getTarjetaTipoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTarjetaTipoListSize() throws GenericBusinessException;
    java.util.Collection findTarjetaTipoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByPadreId(java.lang.Long padreId) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByPuntosDinero(java.lang.String puntosDinero) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByDsctoReferido(java.math.BigDecimal dsctoReferido) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByDsctoPropietario(java.math.BigDecimal dsctoPropietario) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByPorcentajeDineroReferido(java.math.BigDecimal porcentajeDineroReferido) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByPorcentajeDineroPropietario(java.math.BigDecimal porcentajeDineroPropietario) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByStatusSiguiente(java.lang.Long statusSiguiente) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByStatusAnterior(java.lang.Long statusAnterior) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByPuntosSubirStatus(java.math.BigDecimal puntosSubirStatus) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByDineroSubirStatus(java.math.BigDecimal dineroSubirStatus) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByPuntosMantenerStatus(java.math.BigDecimal puntosMantenerStatus) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByDineroMantenerStatus(java.math.BigDecimal dineroMantenerStatus) throws GenericBusinessException;
    java.util.Collection findTarjetaTipoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
