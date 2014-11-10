package com.spirit.facturacion.session;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.spirit.bpm.process.elements.Tarea;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.LogCarteraIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.LogAsientoIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoDetalleIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.handler.ConsultaFacturaVendedor;
import com.spirit.facturacion.session.generated._FacturaSessionService;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.PuntoImpresionIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.medios.entity.PresupuestoFacturacionIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.pos.entity.TarjetaIf;
import com.spirit.pos.entity.VentasTrackIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface FacturaSessionService extends _FacturaSessionService{

	public Collection findFacturaByOficinaIdAndByFechaFactura(Long idOficina,Date fechaCreacion) throws com.spirit.exception.GenericBusinessException;
	Collection findFacturaByQuery(Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public Collection<FacturaIf> getFacturaByMap_FechaInicio_FechaFin(Map<String,Object> aMap,Date fechaInicio,Date fechaFin, java.lang.Long idEmpresa) throws GenericBusinessException;
	Collection getFacturaList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	Collection getFacturaNoAnuladaList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int getFacturaListSize(Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int getFacturaNoAnuladaListSize(Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public Long procesarFactura(FacturaIf model, Vector<PedidoDetalleIf> modelDetalleList, Long idEmpresa, Long idOficina, Vector<PresupuestoIf> presupuestos, Vector<PresupuestoFacturacionIf> presupuestoFacturacionList, String tipoReferencia, UsuarioIf usuario, boolean donacionActivada, Long idFundacion,Tarea tarea)throws GenericBusinessException;
	public Long procesarFactura(FacturaIf model, Vector<PedidoDetalleIf> modelDetalleList, Long idEmpresa, Long idOficina, Vector<PresupuestoIf> presupuestos, Vector<PresupuestoFacturacionIf> presupuestoFacturacionList, String tipoReferencia, UsuarioIf usuario) throws GenericBusinessException;
	public Collection findFacturaByEmpresaIdByFechaInicioAndFechaFin(Long idEmpresa, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws GenericBusinessException;
	public Collection findFacturaReembolsoParaReversarByQueryByEmpresaIdByFechaDesdeAndFechaHasta(Map aMap, Long idEmpresa, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws GenericBusinessException;
	public void procesarReversacionFacturasReembolso(FacturaIf factura, AsientoIf asiento, DocumentoIf documentoFacturaReembolso, UsuarioIf usuario) throws GenericBusinessException, SaldoCuentaNegativoException;
	public Collection findFacturaParaActualizarSaldosByQueryAndEmpresaId(Map aMap, Long idEmpresa) throws GenericBusinessException;
	public void procesarActualizacionSaldosFacturas(FacturaIf factura, CarteraIf cartera) throws GenericBusinessException;
	public void actualizarPreimpreso(FacturaIf factura, String preimpreso, boolean propagarMensaje) throws GenericBusinessException;
	public Collection findFacturasByQueryByFechaInicioAndByFechaFin(Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin) throws com.spirit.exception.GenericBusinessException;
	public Collection findFacturasPedidosByQueryByFechaInicioAndByFechaFin(Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin) throws com.spirit.exception.GenericBusinessException;
	public Collection findFacturasPedidosByQueryByFechaInicioAndByFechaFinByProveedorIdByProveedorOficinaId(Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin, Long idProveedor, Long idProveedorOficina) throws com.spirit.exception.GenericBusinessException;
	public Collection findFacturasByQueryByFechaInicioAndByFechaFinEmpresaId(Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin,Long empresaId) throws com.spirit.exception.GenericBusinessException;
	
	public Collection findFacturasAndFacturasDetalleByQueryByFechaInicioAndByFechaFin(Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin, boolean usarNombreComercial) throws com.spirit.exception.GenericBusinessException;
	public Collection findFacturasAndFacturasDetalleByQueryByFechaInicioAndByFechaFinEmpresaId(Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin,Long empresaId, boolean usarNombreComercial) throws com.spirit.exception.GenericBusinessException;
	
	public boolean actualizarFactura(PedidoIf pedido, FacturaIf factura, List<PedidoDetalleIf> pedidoDetalleList, long idEmpresa, long idOficina, UsuarioIf usuario) throws GenericBusinessException;
	//public Collection findFacturasCarterasAndCarterasDetalleByQueryByFechaInicioAndByFechaFin(Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin) throws com.spirit.exception.GenericBusinessException;
	public Collection findFacturasCarterasAndCarterasDetalleByQueryByFechaInicioAndByFechaFinEmpresaId(Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin,Long empresaId) throws com.spirit.exception.GenericBusinessException;
	
	public Collection findFacturasCarterasAndCarterasDetalleByQueryByFechaInicioAndByFechaFinConsolidado(Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findFacturaByCarteraDetalleComprobante(Long idCarteraDetalleRetencion) throws com.spirit.exception.GenericBusinessException;
	public Collection findLogCarteraFacturaAnulada(FacturaIf factura) throws GenericBusinessException;
	public Collection findLogAsientoFacturaAnulada(FacturaIf factura) throws GenericBusinessException;
	public Collection findFacturaAnuladaParaReactivarByQueryAndEmpresaId(Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public void procesarReactivacionFacturasAnuladas(FacturaIf factura, LogCarteraIf logCartera, LogAsientoIf logAsiento, UsuarioIf usuario) throws GenericBusinessException;
	public Long procesarDevolucion(FacturaIf model, Vector<FacturaDetalleIf> modelDetalleList, Map<String, Object> parametros)  throws GenericBusinessException;

	//public Long generarFacturaPOS(FacturaIf model, Vector<PedidoDetalleIf> modelDetalleList, Long idEmpresa, Long idOficina, PresupuestoIf modelPresupuesto, String tipoReferencia, UsuarioIf usuario) throws GenericBusinessException;
	//public boolean generarFacturaPOS(Vector<PedidoDetalleIf> ProductosidReunionColeccion,String tipo_uno,Long documento,JTable tableModelOriginaluno) throws GenericBusinessException;
	public Map generarFacturaPOS(Vector<PedidoDetalleIf> ProductosidReunionColeccion_GIFT,Vector<TarjetaIf> ProductosidReunionColeccion_TA,Vector<PedidoDetalleIf> ProductosidReunionColeccion_FAC,Vector<PedidoDetalleIf> ProductosidReunionColeccion_NV,Vector<PedidoDetalleIf> ProductosidReunionColeccion_PERS_WEB,VentasTrackIf ventasTrack,Vector<Vector> PagosCollection_detalles,String donacion,String idfundacion,PedidoIf pedido,Vector<PedidoDetalleIf> ProductosidReunionColeccion_eliminados,Vector<PedidoDetalleIf> ProductosidReunionColeccion_proceso,Vector<Vector> TarjetasCollection_detalles, Map<String,Object> parametros,boolean procesandoPricipal) throws GenericBusinessException ;
	public Long savePedido(PedidoIf pedido, Vector<PedidoDetalleIf> pedidoDetalleVector, Long idempresa) throws GenericBusinessException;
	public Long saveDevolucion(Long idFacturaAfectada,EmpleadoIf empleado,ClienteOficinaIf clienteOficinaIf, ClienteIf clienteIf,PuntoImpresionIf puntoImpresionIf,Vector<FacturaDetalleIf> ProductosidReunionColeccion_DEVOLUCIONES, Vector<Vector> TarjetasCollection_detalles, Double ivaTotal,Double subTotal, Double descuentoTotal, Double descuentoGlobalTotal,VentasTrackIf ventasTrack,Long idempresa,Long idoficina,UsuarioIf usuario,java.sql.Timestamp fechaDevolucion,String apd,String atptt,boolean isMatriz) throws GenericBusinessException;
	public FacturaIf getFacturaByPreimpresoAndOficina(Long idOficina,Long idFacturaOrigen);
	public java.util.Collection findFacturaByVendedorIdFechas(java.lang.Long vendedorId, java.sql.Date fechaInicial, java.sql.Date fechaFinal);
	public java.util.Collection findFacturaByQueryFechas(Map aMap, java.sql.Date fechaInicial, java.sql.Date fechaFinal);
	public Collection getFacturaListFechas(java.sql.Date fechaInicial, java.sql.Date fechaFinal);
	public java.util.Collection findFacturaByPreimpresoNumero(String preimpresoNumero,Long idOficina,Long tipoDocumento);
	public List<ConsultaFacturaVendedor> consultaFacturas(Long vendedorId, java.sql.Timestamp fechaInicial, java.sql.Timestamp fechaFinal,Long oficinaId);
	public List<ConsultaFacturaVendedor> consultaFacturasSinVendedor(java.sql.Timestamp fechaInicial, java.sql.Timestamp fechaFinal,Long oficinaId);	
	public Collection consultaFacturasCantidadProducto(java.sql.Timestamp fechaInicial, java.sql.Timestamp fechaFinal,Long color,Long modelo,Long talla,Long tipoproducto);

	public Vector<String> referirAfiliado(String identificacionFactura,String identificacionDuenoTarjeta,Long empresaId)throws GenericBusinessException;
	public Vector<String> propietarioAfiliado(String nombreDuenoTarjeta,String identificacionDuenoTarjeta,Long empresaId)throws GenericBusinessException;
	
	public void saveTarjetaTransacciones(Vector<Vector> TarjetasCollection_detalles,Long idFactura,Long clienteOficinaId, String apd, String atptt, Long idEmpresa) throws GenericBusinessException ;
	//public TarjetaTransaccionIf registrarTarjetaTx(Vector<Vector> TarjetasCollection_detalles,Long tarjetaId,String sumaPuntosGanados,BigDecimal puntosUtilizados,Long idFactura,Long clienteOficinaId) throws GenericBusinessException ;
	
	public Collection<ClienteIf> getClienteConFacturaByMap_FechaInicio_FechaFin( Map<String, Object>
	mapaFacturas, Date fechaInicio, Date fechaFin ) throws GenericBusinessException;
	
	public Collection getRetencionesDeFacturas( Set<Long> documentosId, Map<String,Object> mapaDatos,
			Date fechaInicio, Date fechaFin, int tipoResultado) throws GenericBusinessException;
	
	public void procesarFixFechasFacturas(Long idEmpresa, UsuarioIf usuario) throws GenericBusinessException;
	public void procesarFixTotalesFacturas()  throws GenericBusinessException;
	public void procesarFixCaja()  throws GenericBusinessException;
	public List<CarteraDetalleIf> generarDetalleCobroVector(Vector<Vector> detalleCobroVector, String codigoComprobante, double valorFactura);
	public java.util.Collection<FacturaIf> findFacturasParaCalculoTotalVentas(Long idEmpresa, java.sql.Date firstDate, java.sql.Date lastDate) throws GenericBusinessException;
	public java.util.Collection<FacturaIf> findDevolucionesParaCalculoTotalVentas(Long idEmpresa, java.sql.Date firstDate, java.sql.Date lastDate) throws GenericBusinessException;
	public java.util.Collection<FacturaIf> findFacturaPreimpresoDuplicadoByQuery(Map queryMap) throws GenericBusinessException;
}
