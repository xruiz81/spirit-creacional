package com.spirit.cartera.session;

import java.sql.Date;
import java.util.Map;

import com.spirit.cartera.session.generated._CarteraAfectaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CarteraAfectaSessionService extends _CarteraAfectaSessionService{

	java.util.Collection findCarteraAfectaByCarteraAfectaIdAndByCartera(java.lang.Long carteraafectaId,String esCartera) throws com.spirit.exception.GenericBusinessException;
    public java.util.Collection findCarteraAfectaByQueryByFechaInicialByFechaFinalByEmpresaIdAndCuentaBancariaId(Map aMap, Date fechaInicial, Date fechaFinal, java.lang.Long idEmpresa, java.lang.Long idCuentaBancaria) throws com.spirit.exception.GenericBusinessException;
    public java.util.Collection findCarteraAfectaByEmpresaIdByFechaInicialByFechaFinalByClienteOficinaIdByTipoProveedorIdAndByTipoDocumentoId(java.lang.Long idEmpresa, Date fechaInicial, Date fechaFinal, java.lang.Long idClienteOficina, java.lang.Long idTipoProveedor, java.lang.Long idTipoDocumento, java.lang.Long idCuentaBancariaDeposito, java.lang.Long idTipoProducto) throws com.spirit.exception.GenericBusinessException;
    public Map<Long,Object[]> findCarteraAfectaSaldoByQueryByFechaInicialByFechaFinalAndEmpresaId(Map aMap, Date fechaInicial, Date fechaFinal, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
    public java.util.Collection findCarteraAfectaByQueryByFechas(Map aMap, Date fechaInicial, Date fechaFinal, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
    public java.util.Collection findCarteraAfectaByQueryByFechasdos(Map aMap, Date fechaInicial, Date fechaFinal, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
    public java.util.Collection findCarteraAfectaCarteraIngresoDetalleByTipoCarteraByReferenciaAfectadaIdAndByPreimpreso(String tipoCartera, java.lang.Long idReferenciaAfectada, String preimpreso) throws com.spirit.exception.GenericBusinessException;
    public java.util.Collection findCarteraAfectaByCarteraDetalleAfectadaIdSiLaAfectacionNoEsUnaRetencion(java.lang.Long idCarteraDetalleAfectada) throws com.spirit.exception.GenericBusinessException;
    
}
