package com.spirit.compras.session;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.spirit.cartera.entity.CarteraEJB;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.compras.entity.CompraDetalleEJB;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraEJB;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionEJB;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.compras.gastos.CompraGastoClase;
import com.spirit.compras.handler.OrderData;
import com.spirit.compras.session.generated._CompraSessionService;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CompraSessionService extends _CompraSessionService{

	java.util.Collection findCompraByQueryByFechaInicioAndByFechaFin(Map aMap, Date fechaInicio, Date fechaFin) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findCompraByQueryAndEmpresaId(Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findCompraByEmpresaId(Long idEmpresa) throws GenericBusinessException;
	public java.util.Collection getCompraByMapFechaInicioFechaFin(Map aMap,Date fechaInicio,Date fechaFin, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection getCompraByQuery(Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	Collection getCompraByQueryList(int startIndex,int endIndex, Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	
	Collection<Long> getCompraByQueryListSizeReestriccionStartEnd(Long idEmpresa) throws com.spirit.exception.GenericBusinessException;	
	public java.util.Collection getCompraByQueryListStartEnd(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa,Collection<Long> notin) throws com.spirit.exception.GenericBusinessException;
	
	int getCompraByQueryListSize(Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	
	
	Collection findCompraByQueryList(int startIndex,int endIndex, Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int findCompraByQueryListSize(Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection getCompraByMapList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa);
	public int getCompraByMapSize(Map aMap, java.lang.Long idEmpresa);
	public CompraIf procesarCompra(CompraIf model,List<CompraDetalleIf> modelDetalleList,Long idEmpresa,Long idOficina,long idTarea,Vector<CompraRetencionIf> listaRetenciones,Long tipoDocumentoIfId, UsuarioIf usuario,CompraGastoClase compraGastoClase, Vector<OrderData> ordenesAsociadasVector) throws GenericBusinessException;
	public boolean actualizarCompra(CompraIf model, List<CompraDetalleIf> modelDetalleList, CompraIf compraAnterior, CarteraIf carteraAnterior, List<CompraDetalleIf> modelDetalleEliminadoList, Long idEmpresa, Long idOficina, long idTarea, UsuarioIf usuario, boolean actualizarCodigoCartera,CompraGastoClase compraGastoClase, Vector<OrderData> ordenesAsociadasVector) throws GenericBusinessException;
	Collection findCompraByFechaInicioByFechaFinByOficinaIdAndByProveedorId(Date fechaInicio, Date fechaFin, Long idOficina, Long idProveedor) throws com.spirit.exception.GenericBusinessException;
	
	//COMENTED RATICO 19 JULIO DESCOMENTED!!!!
	Collection findCompraByFechaInicioByFechaFinByOficinaIdAndByOrdenCompraId(Date fechaInicio, Date fechaFin, Long idOficina, Long idOrdenCompra) throws com.spirit.exception.GenericBusinessException;
	
	Collection findComprasPorPagarByEmpresaId(Long empresaId, Boolean diferido) throws com.spirit.exception.GenericBusinessException;
	public Collection findCompraByEmpresaIdByFechaInicioAndFechaFin(Long idEmpresa, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws GenericBusinessException;
	public CarteraEJB registrarCartera(CompraIf compraIf, CarteraIf carteraAnterior, boolean actualizarCodigoCartera, Long idOficina);
	public CompraEJB registrarCompra(CompraIf model);
	public CompraDetalleEJB registrarCompraDetalle(CompraDetalleIf modelDetalle);
	public CompraRetencionEJB registrarCompraRetencion(CompraRetencionIf modelRetencion);
	java.util.Collection findCompraReembolsoParaReversarByQueryAndEmpresaId(Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public void procesarReversacionCompraPorReembolso(CompraIf compra, CompraIf compraAnterior, CarteraIf cartera, TipoDocumentoIf tipoDocumentoCompraPorReembolso, DocumentoIf documentoCompraPorReembolso, UsuarioIf usuario, Vector<OrderData> ordenesAsociadasVector) throws GenericBusinessException, SaldoCuentaNegativoException;
	public void procesarReversacionCompra(CompraIf compra, CompraIf compraAnterior, CarteraIf cartera, TipoDocumentoIf tipoDocumentoCompra, DocumentoIf documentoCompraLocal, UsuarioIf usuario, Vector<OrderData> ordenesAsociadasVector) throws GenericBusinessException, SaldoCuentaNegativoException;
	public Boolean verificarPreimpreso(Long proveedorId,String preimpreso,String autorizacion) throws GenericBusinessException;
	public Boolean verificarPreimpreso(Long proveedorId,String preimpreso,String autorizacion,Long tipoDocumentoId) throws GenericBusinessException;
	public Collection findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFin(Map aMap, Date fechaInicio, Date fechaFin, boolean ordenarPorSecuencialRetencion,Long idIva,Long idRenta,String retencionNumero) throws com.spirit.exception.GenericBusinessException;
	public Collection findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFinEmpresaId(Map aMap, Date fechaInicio, Date fechaFin, boolean ordenarPorSecuencialRetencion,Long idIva,Long idRenta,String retencionNumero,Long empresaId) throws com.spirit.exception.GenericBusinessException;
	public Collection findComprasAndCompraDetalleByQueryByFechaInicioAndByFechaFinEmpresaId(Map aMap, Date fechaInicio, Date fechaFin, Long empresaId) throws GenericBusinessException;
	
	public AsientoIf fixCompra(CompraIf model, List<CompraDetalleIf> modelDetalleList, Long idEmpresa, Long idOficina, UsuarioIf usuario) throws GenericBusinessException;
	public CarteraIf procesarDatosRetenciones(CompraIf compra, Vector<CompraRetencionIf> compraRetencionVector) throws GenericBusinessException;
	
	public Collection findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFinRetencionesAnuladas(Map aMap, Date fechaInicio, Date fechaFin, boolean ordenarPorSecuencialRetencion,Long idIva,Long idRenta,String retencionNumero) throws com.spirit.exception.GenericBusinessException;
	public Collection findComprasAndCompraDetalleAndCompraRetencionByQueryByFechaInicioAndByFechaFinRetencionesAnuladasEmpresaId(Map aMap, Date fechaInicio, Date fechaFin, boolean ordenarPorSecuencialRetencion,Long idIva,Long idRenta,String retencionNumero,Long empresaId) throws com.spirit.exception.GenericBusinessException;	
	
	
	public Collection findCompraFixByQueryAndEmpresaId(Map aMap, Long idEmpresa) throws GenericBusinessException;
	public java.util.Collection findCompraByCarteraDetalleComprobante(Long idCarteraDetalleRetencion) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findCompraByOrdenAsociadaQuery(Map aMap) throws GenericBusinessException;
	public java.util.Collection findCompraByFacturaDetalleId(Long facturaDetalleId) throws GenericBusinessException;
	public java.util.Collection findPurchases(Long providerId) throws GenericBusinessException;
	
	public Collection findOrdenCompraPresupuestoClienteOficinaClienteByCompraId(Long compraId) throws GenericBusinessException;
	public Collection findOrdenMedioClienteOficinaClienteByCompraId(Long compraId) throws GenericBusinessException;
}
