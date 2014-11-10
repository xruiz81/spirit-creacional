package com.spirit.contabilidad.session;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.cartera.handler.WalletData;
import com.spirit.cartera.handler.WalletDetailData;
import com.spirit.contabilidad.entity.ChequeEmitidoIf;
import com.spirit.contabilidad.handler.ChequeDatos;
import com.spirit.contabilidad.handler.EstadoChequeEmitido;
import com.spirit.contabilidad.session.generated._ChequeEmitidoSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ChequeEmitidoSessionService extends _ChequeEmitidoSessionService{

	public void procesarChequeEmitido(ChequeDatos chequeDatos) throws GenericBusinessException;
	public void actualizarChequeEmitido(Long transaccionId,Long tipoDocumentoId,Map<String, ChequeDatos> mapachequesEmitidos,String origen,Map<String,ChequeDatos> mapaChequesEliminados) throws GenericBusinessException;
	public void anularChequesEmitidos(Long transaccionIdExistente,Long tipoDocumentoId,Long transaccionIdNueva) throws GenericBusinessException;
	public void anularChequesEmitidos(String numeroCheque,Long cuentaBancariaId,Long transaccionIdNueva) throws GenericBusinessException;
	public void processIssuedCheck(WalletData walletData, WalletDetailData walletDetailData, boolean update) throws GenericBusinessException;
	public void deleteIssuedCheck(WalletData walletData, WalletDetailData walletDetailData, boolean deleteTransactionCheck) throws GenericBusinessException;
	public void deleteIssuedCheck(ChequeEmitidoIf issuedCheck, boolean deleteTransactionCheck) throws GenericBusinessException;
	public Collection<ChequeEmitidoIf> getChequesEmitidosQueryByFechaInicioByFechaFin( Map<String,Object> mapa, Date fechaInicio, Date fechaFin );
	public Collection<Object[]> getChequesEmitidosDesdeAsientosByQueryByFechaInicioByFechaFin( Map<String,Object> mapa, Date fechaInicio, Date fechaFin ) throws GenericBusinessException;
	public Collection<Object[]> getChequesEmitidosDesdeNominaByQueryByFechaInicioByFechaFin(Map<String, Object> mapa, Date fechaInicio, Date fechaFin) throws GenericBusinessException;
	public Collection<ChequeEmitidoIf> getChequesAnuladosByQueryByFechaInicioByFechaFin( Map<String,Object> mapa, Date fechaIni, Date fechaFin ) throws GenericBusinessException;
	public Collection<Object[]> getChequesEmitidosDesdeCarteraByQueryByFechaInicioByFechaFin( Map<String,Object> mapa, Date fechaInicio, Date fechaFin );
	public Collection<ChequeEmitidoIf> getChequesEmitidosSinCarteraSinAsientoByQueryByFechaInicioByFechaFin( Map<String,Object> mapa, Date fechaInicio, Date fechaFin );
	public String getLetraEstadoChequeEmitido(EstadoChequeEmitido estado) throws GenericBusinessException;
	public Collection findChequeEmitidoByQueryByFechaInicioAndByFechaFin(Map aMap, Date fechaInicio, Date fechaFin) throws com.spirit.exception.GenericBusinessException;
	public void cambiarEstadoCheques(List<ChequeEmitidoIf> chequesSeleccionadosColeccion) throws GenericBusinessException;
	public Collection<Object[]> findChequesEmitidosConciliacionBancaria(Long cuentaBancariaId, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws GenericBusinessException;
}
