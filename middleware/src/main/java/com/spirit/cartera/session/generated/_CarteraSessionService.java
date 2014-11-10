package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CarteraSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.CarteraIf addCartera(com.spirit.cartera.entity.CarteraIf model) throws GenericBusinessException;

   void saveCartera(com.spirit.cartera.entity.CarteraIf model) throws GenericBusinessException;

   void deleteCartera(java.lang.Long id) throws GenericBusinessException;

   Collection findCarteraByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.CarteraIf getCartera(java.lang.Long id) throws GenericBusinessException;

   Collection getCarteraList() throws GenericBusinessException;

   Collection getCarteraList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCarteraListSize() throws GenericBusinessException;
    java.util.Collection findCarteraById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCarteraByTipo(java.lang.String tipo) throws GenericBusinessException;
    java.util.Collection findCarteraByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findCarteraByTipodocumentoId(java.lang.Long tipodocumentoId) throws GenericBusinessException;
    java.util.Collection findCarteraByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findCarteraByReferenciaId(java.lang.Long referenciaId) throws GenericBusinessException;
    java.util.Collection findCarteraByClienteoficinaId(java.lang.Long clienteoficinaId) throws GenericBusinessException;
    java.util.Collection findCarteraByPreimpreso(java.lang.String preimpreso) throws GenericBusinessException;
    java.util.Collection findCarteraByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findCarteraByVendedorId(java.lang.Long vendedorId) throws GenericBusinessException;
    java.util.Collection findCarteraByMonedaId(java.lang.Long monedaId) throws GenericBusinessException;
    java.util.Collection findCarteraByFechaEmision(java.sql.Timestamp fechaEmision) throws GenericBusinessException;
    java.util.Collection findCarteraByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findCarteraBySaldo(java.math.BigDecimal saldo) throws GenericBusinessException;
    java.util.Collection findCarteraByFechaUltimaActualizacion(java.sql.Timestamp fechaUltimaActualizacion) throws GenericBusinessException;
    java.util.Collection findCarteraByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findCarteraByComentario(java.lang.String comentario) throws GenericBusinessException;
    java.util.Collection findCarteraByAprobado(java.lang.String aprobado) throws GenericBusinessException;
    java.util.Collection findCarteraByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findCarteraByActivarRetrocompatibilidad(java.lang.String activarRetrocompatibilidad) throws GenericBusinessException;

}
