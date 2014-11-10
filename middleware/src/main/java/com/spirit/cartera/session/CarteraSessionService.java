package com.spirit.cartera.session;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraEJB;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.CarteraRelacionadaEJB;
import com.spirit.cartera.entity.NotaCreditoDetalleIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.cartera.handler.CrossingWalletDetailData;
import com.spirit.cartera.handler.WalletData;
import com.spirit.cartera.handler.WalletDetailData;
import com.spirit.cartera.session.generated._CarteraSessionService;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.compras.handler.OrderData;
import com.spirit.comun.util.Retencion;
import com.spirit.comun.util.RetencionProveedorData;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaEJB;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.UsuarioIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CarteraSessionService extends _CarteraSessionService{

	public Collection getClienteConCarteraCarteradetallebyFechaInicioFechaFin(Long idEmpresa, Date fechaInicio, Date fechaFin, Map aMap) throws GenericBusinessException;
	public java.util.Collection findCarteraCreditoDisponible(Map aMap, Long idEmpresa) ;
	public Collection findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdByAprobadoAndByCartera(String tipo, String codigoTipoDocumento, Long idEmpresa, String aprobado, String cartera) throws com.spirit.exception.GenericBusinessException;
	public Collection findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdByAprobadoByCarteraAndByClienteOficinaId(String tipo, String codigoTipoDocumento, Long idEmpresa, String aprobado, String cartera, Long idClienteOficina) throws com.spirit.exception.GenericBusinessException;
	public Collection findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdAndByAprobado(String tipo, String codigoTipoDocumento, Long idEmpresa, String aprobado) throws com.spirit.exception.GenericBusinessException;
	public Collection findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdByAprobadoAndByClienteOficinaId(String tipo, String codigoTipoDocumento, Long idEmpresa, String aprobado, Long idClienteOficina) throws com.spirit.exception.GenericBusinessException;
	public Collection findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdAndByReferenciaId(String tipo, String codigoTipoDocumento, Long idEmpresa, Long idReferencia) throws com.spirit.exception.GenericBusinessException;
	public Collection findCarteraByTipoBySaldoByModuloCodigoAndByEmpresaId(String tipo, String codigoModulo, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public Collection findCarteraByTipoBySaldoByModuloCodigoByEmpresaIdAndByClienteOficinaId(String tipo, String codigoModulo, Long idEmpresa, Long idClienteOficina) throws com.spirit.exception.GenericBusinessException;
	public Collection getClienteConCarterabyFechaInicio_FechaFin(Long idEmpresa,Date fechaInicio, Date fechaFin,Map aMap) throws GenericBusinessException;
	public Collection getClienteConCarterabyFechaInicioFechaFin(Long idEmpresa,Date fechaInicio, Date fechaFin,Map aMap) throws GenericBusinessException;
	public Collection<CarteraIf> getCarteraByDocumento_Cliente_FechaInicio_FechaFin(String codigoDocumento,Long clienteId,Date fechaInicio,Date fechaFin, java.lang.Long idEmpresa)throws GenericBusinessException;
	public Collection<CarteraIf> getCarteraByMap_FechaInicio_FechaFin(Map aMap,Date fechaInicio,Date fechaFin, java.lang.Long idEmpresa) throws GenericBusinessException;
	
	public Collection<CarteraIf> getCarteraByMap_FechaInicio_FechaFin(Map aMap,java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin, java.lang.Long idEmpresa) throws GenericBusinessException;
	
	public CarteraIf procesarAprobacionPago(Map<CarteraIf, Map<List<CarteraDetalleIf>, List<CarteraIf>>> carteraCarteraDetalleCarteraCompraMap, Long idEmpresa) throws GenericBusinessException;
	public CarteraIf procesarCartera(CarteraIf model, List<CarteraDetalleIf> modelDetalleList, CarteraIf modelCarteraCompra,Long idEmpresa) throws GenericBusinessException;
	public CarteraIf procesarCartera(CarteraIf model, List<CarteraDetalleIf> modelDetalleList, Map registrosAfecta, Map registrosAfectaToDetalleCarteraMap, Map<String,Object> parametrosEmpresa, String anticipoONivelacion) throws GenericBusinessException;
	public Vector<CarteraIf> procesarPagoProveedor(List<CarteraIf> carteraList, List<CarteraDetalleIf> carteraDetalleList, List<CarteraIf> carteraCompraList, Long idEmpresa, Map<Long,Integer> numerosChequeMap) throws GenericBusinessException, SaldoCuentaNegativoException;
	public void actualizarCarteraPagosDiferidos(List<CarteraIf> carteraList, List<CarteraDetalleIf> carteraDetalleList, Long idEmpresa) throws GenericBusinessException, SaldoCuentaNegativoException;
	public boolean actualizarCartera(CarteraIf model,List<CarteraDetalleIf> modelDetalleList,Map registrosAfecta,Map registrosAfectaToDetalleCartera,List<CarteraDetalleIf> carteraDetalleEliminadoColeccion,List<CarteraIf> carteraActualizadaColeccion,List<CarteraAfectaIf> carteraAfectaEliminarColeccion,Map<String,Object> parametrosEmpresa)throws GenericBusinessException;
	public boolean eliminarCartera(Long carteraId,String usuario) throws GenericBusinessException;
	Collection getCarteraList(int startIndex,int endIndex,Map aMap, java.lang.Long idCliente, java.lang.Long idModulo, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public Collection getCarteraTransferibleList(int startIndex, int endIndex, Map aMap, java.lang.Long idModulo, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int getCarteraListSize(Map aMap, java.lang.Long idCliente, java.lang.Long idModulo, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int getCarteraTransferibleListSize(Map aMap, java.lang.Long idModulo, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findEstadoCuentaByQueryByTipoFiltroByFiltroIdAndEmpresaId(Map aMap, java.lang.String tipoFiltro, java.lang.Long idFiltro, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	Collection findCarteraByQuery(Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public Collection findCarteraByEmpresaIdByFechaInicioAndFechaFin(Long idEmpresa, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws GenericBusinessException;
	public List<Object> procesarRetencionProveedores(List<CompraIf> comprasColeccion, Map<Long,ArrayList<Retencion>> retencionesMap,Map fechasRetencionesMap, Map<String,Object> parametrosEmpresa,Map proveedoresMap,Map proveedoresOficinasMap) throws GenericBusinessException;
	public List<RetencionProveedorData> procesarFixRetencionProveedores(
			List<CompraIf> comprasColeccion,
			Map<Long, ArrayList<Retencion>> retencionesMap,
			Map fechasRetencionesMap, Map<String, Object> parametrosEmpresa,
			Map proveedoresMap, Map proveedoresOficinasMap)
			throws GenericBusinessException;
	public void procesarAnularFactura(FacturaIf model,Vector<FacturaDetalleIf> modelDetalleList,Map mapaAsiento,UsuarioIf usuario) throws GenericBusinessException;
	public void procesarAnularNotaCredito(NotaCreditoIf model,Vector<NotaCreditoDetalleIf> modelDetalleList,Map mapaAsiento,String usuario) throws GenericBusinessException;
	public void desaprobarPagos(List<CarteraIf> carteraList) throws GenericBusinessException;
	public void eliminarFactura(FacturaIf factura, String usuario) throws GenericBusinessException;
	public List getObservacionesPago(Long clienteOficinaId);
	/******* COMPRA ******/
	public boolean actualizarCompra(CompraIf model, List<CompraDetalleIf> modelDetalleList, List<CompraDetalleIf> modelDetalleRemovidoList, long idTarea,Vector<CompraRetencionIf> listaRetenciones, Vector<CompraRetencionIf> listaRetencionesEliminadas,Long idEmpresa,Long idOficina,UsuarioIf usuario,Vector<OrderData> ordenesVector) throws GenericBusinessException;
	public boolean eliminarCompra(Long compraId) throws GenericBusinessException;
	public void procesarAnularCompra( CompraIf compraIf,String usuario) throws GenericBusinessException;
	public void procesarAnularRetencion( CompraIf compraIf,String usuario) throws GenericBusinessException;
	public Collection findCuentasPorPagar(Long idEmpresa, Long idProveedor, Long idTipoProveedor, Date fechaInicial, Date fechaFinal, boolean orderByTipoProveedor) throws GenericBusinessException;
	public Collection findCuentasPorPagar(Long idEmpresa, Long idProveedor, Long idTipoProveedor, Date fechaCorte, boolean orderByTipoProveedor) throws GenericBusinessException;
	public Collection findCuentasPorPagaryCartera(Long idEmpresa, Long idProveedor, Long idTipoProveedor, Date fechaCorte, boolean orderByTipoProveedor) throws GenericBusinessException;
	public Collection findCuentasPorPagarAdicionales(Long idEmpresa, Long idProveedor, Long idTipoProveedor, Long idModulo, Date fechaInicial, Date fechaFinal) throws GenericBusinessException;
	public Collection findCuentasPorCobrar(Long idEmpresa, Long idCliente, Date fechaInicial, Date fechaFinal) throws GenericBusinessException;
	public Collection findCuentasPorCobrar(Long idEmpresa, Long idCliente, Date fechaCorte) throws GenericBusinessException;
	public Collection findCuentasPorCobrarAdicionales(Long idEmpresa, Long idCliente, Long idModulo, Date fechaInicial, Date fechaFinal) throws GenericBusinessException;
	public java.util.Collection findCarteraByFechaInicioFechaFinListaTipoDocumento(List<Long> tiposDocumentos,Date fechaInicio,Date fechaFin, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findCarteraByFechaInicioFechaFin(Date fechaInicio, Date fechaFin, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public void procesarCobrosDiferidos(Collection<CarteraDetalleIf> carteraDetalleCollection,Map<String,Object> parametrosEmpresa) throws GenericBusinessException;
	public CarteraEJB registrarCarteraFactura(Long idEmpresa, Long idOficina, FacturaEJB factura) throws GenericBusinessException;
	public Collection findMovimientosPositivosCartera(Long idEmpresa, String tipo, Long idOperadorNegocio, java.sql.Date fechaInicial, java.sql.Date fechaFinal) throws com.spirit.exception.GenericBusinessException;
	public Collection findMovimientosPositivosCarteraAfectados(Long idEmpresa, String tipo, Long idOperadorNegocio, java.sql.Date fechaInicial, java.sql.Date fechaFinal) throws com.spirit.exception.GenericBusinessException;
	public Collection findMovimientosNegativosCartera(Long idEmpresa, String tipo, Long idOperadorNegocio, java.sql.Date fechaInicial, java.sql.Date fechaFinal) throws com.spirit.exception.GenericBusinessException;
	public int getCarteraParaCruceNotaCreditoListSize(Map aMap, Long documentoAplicaId) throws com.spirit.exception.GenericBusinessException;
	public Collection getCarteraParaCruceNotaCreditoList(int startIndex, int endIndex, Map aMap, Long documentoAplicaId) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findCompraByCarteraRetencionId(Long idCarteraRetencion) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findFacturaByCarteraRetencionId(Long idCarteraRetencion) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findCarteraDetalleByFacturaIdAndDocumentoId(Long idFactura, Long idDocumento) throws com.spirit.exception.GenericBusinessException;
	public void procesarCruceCuentas(List<CarteraIf> cuentasPorCobrarSeleccionadas, CarteraIf cuentaPorPagar, Map valoresAplica, Map parameterMap) throws GenericBusinessException;
	public Collection findAnticiposClientePorCruzar(Long idEmpresa, Long idCliente, Date fechaCorte) throws GenericBusinessException;
	public void procesarCruceAnticipoCliente(List<CarteraIf> cuentasPorCobrarSeleccionadas, CarteraIf anticipo, Map valoresAplica, Map<String, Object> parameterMap) throws GenericBusinessException;
		
	public Collection findCarteraByCarteraDetalleId(Long idCarteraDetalle);
	public Collection findCarterasCarterasAfectaQueCruzanReferenciaId(Long idReferencia, Long idTipoDocumento) throws GenericBusinessException ;
	public Long generarReciboCajaPOS(Vector<Vector> detallesPagos, Map<String, Object>parametros, boolean procesandoPrincipal);
	public void actualizarPreimpreso(CarteraIf cartera, String preimpreso) throws GenericBusinessException;
	public void transferirComprobante(CarteraIf comprobanteOriginal, Map<String,Object> parametrosEmpresa, OficinaIf oficinaOrigen, OficinaIf oficinaDestino, boolean procesandoPrincipal) throws GenericBusinessException;
	public void enviarReciboCajaPos(Vector<Vector> detallesPagos, Map<String, Object> parametros, boolean procesandoPrincipal,CarteraIf cartera, String preimpreso);
	public void fixAsientosAnticipos() throws GenericBusinessException;
	
	public WalletData processWallet(WalletData wallet, Vector<WalletDetailData> walletDetailDataVector, Vector<CrossingWalletDetailData> crossingWalletDetailsVector, Vector<WalletDetailData> deletedWalletDetailDataVector, boolean update, boolean updateOriginalSaveModeAccountingEntry, DocumentoIf levelingDocument, DocumentoIf advancePaymentDocument, CarteraRelacionadaEJB relatedWallet) throws Exception, GenericBusinessException;
	public void nullifyWallet(WalletData walletData, Vector<WalletDetailData> walletDetailDataVector) throws Exception, GenericBusinessException;
	public Collection findPendingAccountDetailDataByWalletDetailId(Long walletDetailId);
	public Vector<WalletDetailData> reverseCrossingDocuments(WalletData walletData, CrossingWalletDetailData crossingWalletDetailData, Vector<WalletDetailData> applyingDocumentsVector) throws Exception, GenericBusinessException;
	public Collection<CarteraDetalleIf> findPendingAccountApprovedPayments(CarteraIf pendingAccountWallet) throws GenericBusinessException;
	
	public Collection<Object[]> findTransaccionesAnuladasConciliacionBancaria(Long empresaId, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws GenericBusinessException;
	public java.util.Collection findCarteraByTipoDocumentoByEmpresaIdByFechaInicialByFechaFinalAndByCuentaBancariaId(String codigoTipoDocumento, Long idEmpresa, java.sql.Date fechaInicial, java.sql.Date fechaFinal, Long idCuentaBancaria) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findCarteraByEmpresaIdByFechaInicialByFechaFinalByClienteOficinaIdByTipoProveedorIdAndByTipoDocumentoId(java.lang.Long idEmpresa, Date fechaInicial, Date fechaFinal, java.lang.Long idClienteOficina, java.lang.Long idTipoProveedor, java.lang.Long idTipoDocumento, java.lang.Long idTipoProducto)	throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findCarteraAfectadaByCarteraId(Long carteraId) throws com.spirit.exception.GenericBusinessException;
  }
