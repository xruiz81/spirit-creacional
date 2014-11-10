package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TarjetaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.TarjetaIf addTarjeta(com.spirit.pos.entity.TarjetaIf model) throws GenericBusinessException;

   void saveTarjeta(com.spirit.pos.entity.TarjetaIf model) throws GenericBusinessException;

   void deleteTarjeta(java.lang.Long id) throws GenericBusinessException;

   Collection findTarjetaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.TarjetaIf getTarjeta(java.lang.Long id) throws GenericBusinessException;

   Collection getTarjetaList() throws GenericBusinessException;

   Collection getTarjetaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTarjetaListSize() throws GenericBusinessException;
    java.util.Collection findTarjetaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTarjetaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTarjetaByTipoId(java.lang.Long tipoId) throws GenericBusinessException;
    java.util.Collection findTarjetaByClienteoficinaId(java.lang.Long clienteoficinaId) throws GenericBusinessException;
    java.util.Collection findTarjetaByReferidoporId(java.lang.Long referidoporId) throws GenericBusinessException;
    java.util.Collection findTarjetaByGarante(java.lang.Long garante) throws GenericBusinessException;
    java.util.Collection findTarjetaByFechaValidez(java.lang.Long fechaValidez) throws GenericBusinessException;
    java.util.Collection findTarjetaByFechaEmision(java.lang.Long fechaEmision) throws GenericBusinessException;
    java.util.Collection findTarjetaByPuntosAcumulados(java.math.BigDecimal puntosAcumulados) throws GenericBusinessException;
    java.util.Collection findTarjetaByPuntosUtilizados(java.math.BigDecimal puntosUtilizados) throws GenericBusinessException;
    java.util.Collection findTarjetaByPuntosComprometidos(java.math.BigDecimal puntosComprometidos) throws GenericBusinessException;
    java.util.Collection findTarjetaByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findTarjetaBySaldo(java.math.BigDecimal saldo) throws GenericBusinessException;
    java.util.Collection findTarjetaByCupo(java.math.BigDecimal cupo) throws GenericBusinessException;
    java.util.Collection findTarjetaByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findTarjetaByValidador(java.lang.String validador) throws GenericBusinessException;
    java.util.Collection findTarjetaByPuntosAcumuladosStatus(java.math.BigDecimal puntosAcumuladosStatus) throws GenericBusinessException;
    java.util.Collection findTarjetaByDineroAcumulado(java.math.BigDecimal dineroAcumulado) throws GenericBusinessException;
    java.util.Collection findTarjetaByDineroUtilizado(java.math.BigDecimal dineroUtilizado) throws GenericBusinessException;
    java.util.Collection findTarjetaByDineroComprometido(java.math.BigDecimal dineroComprometido) throws GenericBusinessException;
    java.util.Collection findTarjetaByDineroAcumuladoStatus(java.math.BigDecimal dineroAcumuladoStatus) throws GenericBusinessException;
    java.util.Collection findTarjetaByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findTarjetaByFechaUltimoCambioStatus(java.lang.Long fechaUltimoCambioStatus) throws GenericBusinessException;
    java.util.Collection findTarjetaByProductoId(java.lang.Long productoId) throws GenericBusinessException;

}
