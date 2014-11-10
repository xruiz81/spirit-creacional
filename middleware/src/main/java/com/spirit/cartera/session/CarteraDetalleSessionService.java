package com.spirit.cartera.session;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.spirit.cartera.entity.CarteraDetalleEJB;
import com.spirit.cartera.entity.CarteraEJB;
import com.spirit.cartera.handler.WalletDetailData;
import com.spirit.cartera.session.generated._CarteraDetalleSessionService;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaEJB;
import com.spirit.general.entity.DocumentoIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CarteraDetalleSessionService extends _CarteraDetalleSessionService{

	java.util.Collection findCarteraDetalleByCarteraAndBySignoCarteraAndByFechaInicioAndByFechaFin(java.lang.String cartera,java.lang.String signoCartera, java.sql.Date fechaInicio, java.sql.Date fechaFin, java.lang.Long idEmpresa)  throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findCarteraDetalleByClienteAndByTipoDocumentoAndByTipoCarteraAndBySignoCarteraSaldoPositivo(java.lang.Long idCliente, java.lang.Long idTipoDocumento, String tipoCartera, String signoCartera, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findCarteraDetalleByClienteAndByTipoDocumentoAndByTipoCarteraAndBySignoCarteraSaldoPositivo(java.lang.Long idCliente, java.lang.Long idTipoDocumento, String tipoCartera, String signoCartera, int startIndex, int endIndex, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findCarteraDetalleByClienteAndByTipoDocumentoAndByTipoCarteraAndBySignoCartera(java.lang.Long idCliente, java.lang.Long idTipoDocumento, String tipoCartera, String signoCartera, java.lang.Long idEmpresa, Long carteraUpdateId) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findPendingAccountsByWalletTypeByBusinessOperatorOfficeAndWalletSign(String walletType, ClienteOficinaIf businessOperatorOffice, String walletSign) throws GenericBusinessException;
	java.util.Collection findCarteraDetalleByClienteAndByTipoDocumentoAndByTipoCarteraAndBySignoCartera(java.lang.Long idCliente, java.lang.Long idTipoDocumento, String tipoCartera, String signoCartera, int startIndex, int endIndex, java.lang.Long idEmpresa, Long carteraUpdateId) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findCarteraDetalleByFechas(Long idCartera, java.sql.Date fechaInicio, java.sql.Date fechaFinal) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findCarteraDetalleByFechaCarteraFinal(Long idCartera, java.sql.Date fechaInicio) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findCarteraDetalleByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdAndByReferencia(String tipo, String codigoTipoDocumento, Long idEmpresa, String referencia) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findCarteraDetalleByTipoBySaldoByTipoDocumentoCodigoAndEmpresaId(String tipo, String codigoTipoDocumento, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	
	
	
	public Collection findCarteraDetalleByFechaInicialByFechaFinalAndEmpresaId_AFECTA(java.sql.Date fechaInicial, java.sql.Date fechaFinal, Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	
	java.util.Collection findCarteraDetalleByTipoBySaldoAndEmpresaId(String tipo, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findCarteraDetalleByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdByAprobadoAndByCartera(String tipo, String codigoTipoDocumento, Long idEmpresa, String aprobado, String cartera)  throws com.spirit.exception.GenericBusinessException;
	public Collection findCarteraDetalleByFechaInicialByFechaFinalAndEmpresaId(java.sql.Date fechaInicial, java.sql.Date fechaFinal, Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	
	public Map<Long,Long[]> datosMapa(java.sql.Date fechaInicial, java.sql.Date fechaFinal, Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	
	public Collection findCarteraDetalleByFechaInicialByFechaFinalAndEmpresaId_DOS(java.sql.Date fechaFinal)throws com.spirit.exception.GenericBusinessException;
		
	public java.util.Collection findCarteraDetalleByQueryByFechaCarteraMenorFechaActual(Map aMap) throws GenericBusinessException;
	public Double registrarCarteraDetalleFactura(FacturaEJB factura, CarteraEJB cartera, List<DocumentoIf> modelDocumentoList, int count, Vector<FacturaDetalleIf> facturaDetalleColeccion, Double valorFacturas) throws GenericBusinessException;
	java.util.Collection findCarteraDetalleByReferenciaByDocumentoNullAndByCarteraNo(String referencia) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findCarteraDetalleNegativos() throws com.spirit.exception.GenericBusinessException;
	public CarteraDetalleEJB registerWalletDetail(WalletDetailData walletDetailData);
	public java.util.Collection findWalletReceiptRowDataByWalletId(Long walletId) throws com.spirit.exception.GenericBusinessException;
}
