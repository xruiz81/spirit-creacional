package com.spirit.facturacion.session;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.bpm.campana.ProcesoOrdenTrabajoCreacionalSessionLocal;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.cartera.entity.CarteraAfectaEJB;
import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraData;
import com.spirit.cartera.entity.CarteraDetalleData;
import com.spirit.cartera.entity.CarteraDetalleEJB;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraEJB;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.FormaPagoIf;
import com.spirit.cartera.entity.LogCarteraDetalleEJB;
import com.spirit.cartera.entity.LogCarteraDetalleIf;
import com.spirit.cartera.entity.LogCarteraEJB;
import com.spirit.cartera.entity.LogCarteraIf;
import com.spirit.cartera.session.CarteraAfectaSessionLocal;
import com.spirit.cartera.session.CarteraDetalleSessionLocal;
import com.spirit.cartera.session.FormaPagoSessionLocal;
import com.spirit.cartera.session.LogCarteraDetalleSessionLocal;
import com.spirit.compras.entity.CompraEJB;
import com.spirit.compras.entity.CompraIf;
import com.spirit.contabilidad.entity.AsientoDetalleData;
import com.spirit.contabilidad.entity.AsientoDetalleEJB;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.entity.LogAsientoDetalleEJB;
import com.spirit.contabilidad.entity.LogAsientoDetalleIf;
import com.spirit.contabilidad.entity.LogAsientoEJB;
import com.spirit.contabilidad.entity.LogAsientoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.PlantillaIf;
import com.spirit.contabilidad.session.AsientoDetalleSessionLocal;
import com.spirit.contabilidad.session.AsientoSessionLocal;
import com.spirit.contabilidad.session.CuentaSessionLocal;
import com.spirit.contabilidad.session.EventoContableSessionLocal;
import com.spirit.contabilidad.session.LogAsientoDetalleSessionLocal;
import com.spirit.contabilidad.session.PlanCuentaSessionLocal;
import com.spirit.contabilidad.session.PlantillaSessionLocal;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.ClienteOficinaSessionLocal;
import com.spirit.crm.session.ClienteSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.facturacion.entity.FacturaData;
import com.spirit.facturacion.entity.FacturaDetalleCompraAsociadaEJB;
import com.spirit.facturacion.entity.FacturaDetalleCompraAsociadaIf;
import com.spirit.facturacion.entity.FacturaDetalleData;
import com.spirit.facturacion.entity.FacturaDetalleEJB;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaEJB;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.ListaPrecioIf;
import com.spirit.facturacion.entity.PedidoData;
import com.spirit.facturacion.entity.PedidoDetalleData;
import com.spirit.facturacion.entity.PedidoDetalleIf;
import com.spirit.facturacion.entity.PedidoEJB;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.handler.ComprobanteIngresoPosAsientoAutomaticoHandlerLocal;
import com.spirit.facturacion.handler.ConsultaFacturaVendedor;
import com.spirit.facturacion.handler.DevolucionAsientoAutomaticoHandlerLocal;
import com.spirit.facturacion.handler.FacturaAsientoAutomaticoHandlerLocal;
import com.spirit.facturacion.session.generated._FacturaSession;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.NumeradoresIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.OrigenDocumentoIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.PuntoImpresionIf;
import com.spirit.general.entity.ServidorIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoPagoData;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.mdb.messages.bo.ActualizarPreimpresoMessageLocal;
import com.spirit.general.mdb.messages.bo.DevolucionMessageLocal;
import com.spirit.general.mdb.messages.bo.FacturaPosMessageLocal;
import com.spirit.general.session.CajaSessionLocal;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.MonedaSessionLocal;
import com.spirit.general.session.NumeradoresSessionLocal;
import com.spirit.general.session.OficinaSessionLocal;
import com.spirit.general.session.OrigenDocumentoSessionLocal;
import com.spirit.general.session.ParametroEmpresaSessionLocal;
import com.spirit.general.session.ServidorSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.TipoPagoSessionLocal;
import com.spirit.general.session.UsuarioSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.GiftcardIf;
import com.spirit.inventario.entity.GiftcardMovimientoEJB;
import com.spirit.inventario.entity.GiftcardMovimientoIf;
import com.spirit.inventario.entity.MovimientoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.StockEJB;
import com.spirit.inventario.entity.StockIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.inventario.session.BodegaSessionLocal;
import com.spirit.inventario.session.GenericoSessionLocal;
import com.spirit.inventario.session.GiftcardMovimientoSessionLocal;
import com.spirit.inventario.session.GiftcardSessionLocal;
import com.spirit.inventario.session.MovimientoSessionLocal;
import com.spirit.inventario.session.ProductoSessionLocal;
import com.spirit.inventario.session.TipoProductoSessionLocal;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoFacturacionEJB;
import com.spirit.medios.entity.PresupuestoFacturacionIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.session.PresupuestoDetalleSessionLocal;
import com.spirit.medios.session.PresupuestoFacturacionSessionLocal;
import com.spirit.pos.entity.CajasesionMovimientosIf;
import com.spirit.pos.entity.DonacionTipoproductoIf;
import com.spirit.pos.entity.PagoTarjetaData;
import com.spirit.pos.entity.PagoTarjetaIf;
import com.spirit.pos.entity.TarjetaEJB;
import com.spirit.pos.entity.TarjetaIf;
import com.spirit.pos.entity.TarjetaTipoIf;
import com.spirit.pos.entity.TarjetaTransaccionEJB;
import com.spirit.pos.entity.VentasConsolidadoIf;
import com.spirit.pos.entity.VentasDocumentosData;
import com.spirit.pos.entity.VentasDocumentosEJB;
import com.spirit.pos.entity.VentasDocumentosIf;
import com.spirit.pos.entity.VentasPagosData;
import com.spirit.pos.entity.VentasPagosIf;
import com.spirit.pos.entity.VentasTrackData;
import com.spirit.pos.entity.VentasTrackIf;
import com.spirit.pos.session.DonacionTipoproductoSessionLocal;
import com.spirit.pos.session.PagoTarjetaSessionLocal;
import com.spirit.pos.session.TarjetaSessionLocal;
import com.spirit.pos.session.TarjetaTipoSessionLocal;
import com.spirit.pos.session.TarjetaTransaccionSessionLocal;
import com.spirit.pos.session.VentasConsolidadoSessionLocal;
import com.spirit.pos.session.VentasDocumentosSessionLocal;
import com.spirit.pos.session.VentasPagosSessionLocal;
import com.spirit.pos.session.VentasTrackSessionLocal;
import com.spirit.poscola.entity.PosColaIf;
import com.spirit.poscola.session.PosColaSessionLocal;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.sri.dimm.DimmConstantes;
import com.spirit.util.DeepCopy;
import com.spirit.util.Utilitarios;

/**
 * The <code>FacturaSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.2 $, $Date: 2014/07/04 22:53:49 $
 * 
 */
@Stateless
public class FacturaSessionEJB extends _FacturaSession implements FacturaSessionRemote, FacturaSessionLocal {
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	private static final String MONEDA_DOLAR = "USD";
	private static final String OTRAS_FORMAS_PAGO = "OTR";
	private static final String TIPO_PAGO_GIFTCARD = "GC";
	private static final String TIPO_PAGO_DONACION = "DO";
	private static final String TIPO_PAGO_CREDITO_CLIENTE = "CR";
	private static final String TIPO_PAGO_EFECTIVO_CLIENTE = "EF";
	private static final String TIPO_PAGO_PUNTOS = "PT";
	private static final String TIPO_PAGO_TARJETA_CREDITO = "TA";
	private static final String TIPO_PAGO_CHEQUE = "CH";
	private static final String COBRO_CLIENTE_EFECTIVO = "CCEF";
	private static final String DEVOLUCION = "DEV";
	private static final String COBRO_CLIENTE_TARJETA_CREDITO = "CCTC";
	private static final String COBRO_CLIENTE_CHEQUE = "COCL";
	private static final String COBRO_CLIENTE_GIFTCARD = "CCGC";
	private static final String COBRO_CLIENTE_PUNTOS = "CCPT";
	private static final String ESTADO_ACTIVO = "A";
	private static final String RECIBO_CAJA_EFECTIVO = "RCCE";
	private static final String RECIBO_CAJA_TARJETA_CREDITO = "RCTC";
	private static final String RECIBO_CAJA_CHEQUE = "RCCH";
	private static final String RECIBO_CAJA_GIFTCARD = "RCGC";
	private static final String ESTADO_PRESUPUESTO_FACTURACION_FACTURADO = "F";

	@EJB private NumeradoresSessionLocal numeradoresLocal;
	@EJB private FacturaDetalleSessionLocal facturaDetalleLocal;
	@EJB private ProductoSessionLocal productoLocal;
	@EJB private GenericoSessionLocal genericoLocal;
	@EJB private DonacionTipoproductoSessionLocal donacionTipoProductoLocal;
	@EJB private EmpresaSessionLocal empresaLocal;
	@EJB private DocumentoSessionLocal documentoLocal;
	@EJB private PedidoSessionLocal pedidoLocal;
	@EJB private UsuarioSessionLocal usuarioLocal;
	@EJB private CajaSessionLocal cajaLocal;
	@EJB private OficinaSessionLocal oficinaLocal;
	@EJB private ServidorSessionLocal servidorLocal;
	@EJB private TipoDocumentoSessionLocal tipoDocumentoLocal;
	@EJB private AsientoSessionLocal asientoLocal;
	@EJB private PlanCuentaSessionLocal planCuentaLocal;
	@EJB private EventoContableSessionLocal eventoContableLocal;
	@EJB private PlantillaSessionLocal plantillaLocal;
	@EJB private CuentaSessionLocal cuentaLocal;
	@EJB private LogCarteraDetalleSessionLocal logCarteraDetalleLocal;
	@EJB private LogAsientoDetalleSessionLocal logAsientoDetalleLocal;
	@EJB private MovimientoSessionLocal movimientoSessionLocal;
	@EJB private FacturaAsientoAutomaticoHandlerLocal facturaAsientoAutomaticoHandlerLocal;
	@EJB private DevolucionAsientoAutomaticoHandlerLocal devolucionAsientoAutomaticoHandlerLocal;
	@EJB private CarteraDetalleSessionLocal carteraDetalleLocal;
	@EJB private AsientoDetalleSessionLocal asientoDetalleLocal;
	@EJB private UtilitariosSessionLocal utilitariosLocal;
	@EJB private TipoProductoSessionLocal tipoproductoLocal;
	@EJB private VentasTrackSessionLocal ventastrackLocal;
	@EJB private VentasPagosSessionLocal ventasPagosLocal;
	@EJB private VentasDocumentosSessionLocal ventasDocumentosLocal;
	@EJB private VentasConsolidadoSessionLocal ventasConsolidadoLocal;
	@EJB private TipoPagoSessionLocal tipoPagoLocal;
	@EJB private FormaPagoSessionLocal formaPagoLocal;
	@EJB private GiftcardSessionLocal giftcardLocal;
	@EJB private GiftcardMovimientoSessionLocal giftcardMovimientoLocal;
	@EJB private PagoTarjetaSessionLocal PagoTarjetaLocal;
	@EJB private MonedaSessionLocal monedaLocal;
	@EJB private OrigenDocumentoSessionLocal origenDocumentoLocal;
	@EJB private PedidoDetalleSessionLocal pedidoDetalleLocal;
	@EJB private ListaPrecioSessionLocal listaPrecioSessionLocal;
	@EJB private BodegaSessionLocal bodegaLocal;
	@EJB private ClienteOficinaSessionLocal clienteOficinaSessionLocal;
	@EJB private ClienteSessionLocal clienteSessionLocal;
	@EJB private FacturaPosMessageLocal facturaPosMessageLocal;
	@EJB private ComprobanteIngresoPosAsientoAutomaticoHandlerLocal comprobanteIngresoPosAsientoAutomaticoHandlerLocal;
	@EJB private DevolucionMessageLocal devolucionMessageLocal;
	@EJB private PosColaSessionLocal posColaSessionLocal;
	@EJB private ActualizarPreimpresoMessageLocal actualizarPreimpresoMessageLocal;
	@EJB private CarteraAfectaSessionLocal carteraAfectaLocal;
	@EJB private ParametroEmpresaSessionLocal parametroEmpresaSessionLocal;
	@EJB private TarjetaSessionLocal tarjetaSessionLocal;
	@EJB private TarjetaTransaccionSessionLocal tarjetaTransaccionSessionLocal;
	@EJB private TarjetaTipoSessionLocal tarjetaTipoSessionLocal;
	@EJB private ProcesoOrdenTrabajoCreacionalSessionLocal procesoOrdenTrabajoLocal;
	@EJB private PresupuestoDetalleSessionLocal presupuestoDetalleLocal;
	@EJB private PresupuestoFacturacionSessionLocal presupuestoFacturacionLocal;
	@EJB private FacturaDetalleCompraAsociadaSessionLocal facturaDetalleCompraAsociadaSessionLocal;

	DecimalFormat formatoSerial = new DecimalFormat("00000");
	boolean nuevaCodificacionActiva = true;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(FacturaSessionEJB.class);

	@Resource
	private SessionContext ctx;

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findFacturaByVendedorIdFechas(
			java.lang.Long vendedorId, java.sql.Date fechaInicial, java.sql.Date fechaFinal) {

		String queryString = "from FacturaEJB e where e.vendedorId = :vendedorId ";
		// Add a an order by on all primary keys to assure reproducable results.
		if (fechaInicial != null)
			queryString += " and e.fechaFactura >= :fechaInicial";
		if (fechaFinal != null)
			queryString += " and e.fechaFactura <= :fechaFinal";



		String orderByPart = "";
		orderByPart += " order by e.fechaFactura";
		queryString += orderByPart;


		Query query = manager.createQuery(queryString);
		query.setParameter("vendedorId", vendedorId);
		if (fechaInicial != null) {
			java.sql.Timestamp startDate = utilitariosLocal.resetTimestampStartDate(new java.sql.Timestamp(fechaInicial.getTime()));
			query.setParameter("fechaInicial", startDate);
		}
		if (fechaFinal != null) {
			java.sql.Timestamp endDate = utilitariosLocal.resetTimestampEndDate(new java.sql.Timestamp(fechaFinal.getTime()));
			query.setParameter("fechaFinal", endDate);
		}
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findFacturaByQueryFechas(Map aMap, java.sql.Date fechaInicial, java.sql.Date fechaFinal) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from FacturaEJB " + objectName + " where "
		+ where;

		if (fechaInicial != null)
			queryString += " and e.fechaFactura >= :fechaInicial";
		if (fechaFinal != null)
			queryString += " and e.fechaFactura <= :fechaFinal";


		String orderByPart = "";
		orderByPart += " order by e.fechaFactura desc";
		queryString += orderByPart;

		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		if (fechaInicial != null) {
			java.sql.Timestamp startDate = utilitariosLocal.resetTimestampStartDate(new java.sql.Timestamp(fechaInicial.getTime()));
			query.setParameter("fechaInicial", startDate);
		} if (fechaFinal != null) {
			java.sql.Timestamp endDate = utilitariosLocal.resetTimestampEndDate(new java.sql.Timestamp(fechaFinal.getTime()));
			query.setParameter("fechaFinal", endDate);
		}

		return query.getResultList();

	}

	private Comparator<ConsultaFacturaVendedor> comparadorFacturaFechas=new Comparator<ConsultaFacturaVendedor>(){
		public int compare(ConsultaFacturaVendedor o1, ConsultaFacturaVendedor o2) {
			return	(o1.getFechaEmision().compareTo(o2.getFechaEmision()) * o1.getNumeroFactura().compareTo(o2.getNumeroFactura()) * o1.getCodigoTransaccion().compareTo(o2.getCodigoTransaccion()));
		}};


		public List<ConsultaFacturaVendedor> consultaFacturasSinVendedor(java.sql.Timestamp fechaInicial, java.sql.Timestamp fechaFinal,Long oficinaId) {
			String queryString = "SELECT " +
			"factura.id, " +
			"factura.fechaFactura, " +
			"factura.preimpresoNumero, " +
			"factura.numero, " +
			"factura.observacion, " +
			"factura.valor, " +
			"factura.iva, " +
			"(factura.descuento + factura.descuentoGlobal), "+
			"tipoDocumento.codigo " +
			"from " +
			"FacturaEJB factura, " +
			"TipoDocumentoEJB tipoDocumento "+

			"where " +
			"factura.valor > 0 and ";
			if (oficinaId != null)
				queryString += "factura.oficinaId = :oficinaId and ";

			queryString += "factura.tipodocumentoId=tipoDocumento.id and " +
			"(factura.vendedorId is null) and " +				
			"(factura.fechaFactura >= :fechaInicial or :fechaInicial is null) and " +
			"(factura.fechaFactura <= :fechaFinal or :fechaFinal is null)" +
			" order by factura.fechaFactura asc, factura.preimpresoNumero asc, tipoDocumento.codigo asc";

			Query query = manager.createQuery(queryString);
			if (fechaInicial != null)
				fechaInicial = utilitariosLocal.resetTimestampStartDate(fechaInicial);
			query.setParameter("fechaInicial", fechaInicial);
			if (fechaFinal != null)
				fechaFinal = utilitariosLocal.resetTimestampEndDate(fechaFinal);
			query.setParameter("fechaFinal", fechaFinal);
			if (oficinaId != null)
				query.setParameter("oficinaId", oficinaId);

			List l=query.getResultList();
			Object[] tmp=null;
			List<ConsultaFacturaVendedor> consultaFacturaVendedorList=new ArrayList<ConsultaFacturaVendedor>();
			for(int i=0;i<l.size();i++)
			{
				ConsultaFacturaVendedor consultaFacturaVendedor=new ConsultaFacturaVendedor();
				tmp=(Object[])l.get(i);
				consultaFacturaVendedor.setFacturaId((Long)tmp[0]);
				consultaFacturaVendedor.setFechaEmision(new java.sql.Date(((java.sql.Timestamp)tmp[1]).getTime()));
				consultaFacturaVendedor.setTransaccion((String)tmp[2]);
				consultaFacturaVendedor.setNumeroFactura(tmp[3]!=null?""+((BigDecimal)tmp[3]).intValue():"");
				consultaFacturaVendedor.setObservacion((String)tmp[4]);
				consultaFacturaVendedor.setTotal((((BigDecimal)tmp[5]).add((BigDecimal)tmp[6])).subtract((BigDecimal)tmp[7]));
				consultaFacturaVendedor.setCodigoTransaccion((String)tmp[8]);
				consultaFacturaVendedor.setDescuento((BigDecimal)tmp[7]);			
				consultaFacturaVendedor.setTotalBrutas((BigDecimal)tmp[5]);
				consultaFacturaVendedor.setValorIva((BigDecimal)tmp[6]);
				consultaFacturaVendedor.setVendedor("SIN VENDEDOR");

				consultaFacturaVendedorList.add(consultaFacturaVendedor);
			}
			//Collections.sort(consultaFacturaVendedorList, comparadorFacturaFechas);
			return consultaFacturaVendedorList;
		}


		/*
		public List<ConsultaFacturaVendedor> consultaFacturasSinVendedor(java.sql.Date fechaInicial, java.sql.Date fechaFinal) {
			String queryString = "SELECT " +
			"factura.id, " +
			"factura.fechaFactura, " +
			"factura.preimpresoNumero, " +
			"factura.numero, " +
			"factura.observacion, " +
			"factura.valor, " +
			"factura.iva, " +
			"factura.descuento, "+
			"tipoDocumento.codigo " +
			"from " +
			"FacturaEJB factura, " +
			"TipoDocumentoEJB tipoDocumento "+

			"where " +
			"factura.valor > 0 and " +
			"factura.tipodocumentoId=tipoDocumento.id and " +
			"(factura.vendedorId is null) and " +				
			"(factura.fechaFactura >= :fechaInicial or :fechaInicial is null) and " +
			"(factura.fechaFactura <= :fechaFinal or :fechaFinal is null)";

			Query query = manager.createQuery(queryString);
			query.setParameter("fechaFinal", fechaFinal);
			query.setParameter("fechaInicial", fechaInicial);

			List l=query.getResultList();
			Object[] tmp=null;
			List<ConsultaFacturaVendedor> consultaFacturaVendedorList=new ArrayList<ConsultaFacturaVendedor>();
			for(int i=0;i<l.size();i++)
			{
				ConsultaFacturaVendedor consultaFacturaVendedor=new ConsultaFacturaVendedor();
				tmp=(Object[])l.get(i);
				consultaFacturaVendedor.setFacturaId((Long)tmp[0]);
				consultaFacturaVendedor.setFechaEmision((Date)tmp[1]);
				consultaFacturaVendedor.setTransaccion((String)tmp[2]);
				consultaFacturaVendedor.setNumeroFactura(tmp[3]!=null?""+((BigDecimal)tmp[3]).intValue():"");
				consultaFacturaVendedor.setObservacion((String)tmp[4]);
				consultaFacturaVendedor.setTotal((((BigDecimal)tmp[5]).add((BigDecimal)tmp[6])).subtract((BigDecimal)tmp[7]));
				consultaFacturaVendedor.setCodigoTransaccion((String)tmp[8]);
				consultaFacturaVendedor.setVendedor("SIN VENDEDOR");
				consultaFacturaVendedorList.add(consultaFacturaVendedor);
			}
			Collections.sort(consultaFacturaVendedorList, comparadorFacturaFechas);
			return consultaFacturaVendedorList;
		}
		 */


		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public List<ConsultaFacturaVendedor> consultaFacturas(Long vendedorId, java.sql.Timestamp fechaInicial, java.sql.Timestamp fechaFinal,Long oficinaId) {
			String vendedor="";
			String reestriccion="";
			if(vendedorId!=null)
			{
				vendedor="and factura.vendedorId ="+vendedorId+" and ";				 
			}
			else{ 
				vendedor=" and factura.vendedorId is not null and ";			
			}			

			System.out.println("VENDEDOR!"+vendedor);

			String queryString = "SELECT " +
			"factura.id, " +
			"factura.fechaFactura, " +
			"factura.preimpresoNumero, " +
			"factura.numero, " +
			"factura.observacion, " +
			"factura.valor, " +
			"factura.iva, " +
			"(factura.descuento + factura.descuentoGlobal), "+
			"tipoDocumento.codigo, " +						
			"(SELECT empleado.nombres FROM EmpleadoEJB empleado WHERE empleado.id=factura.vendedorId )," +
			"(SELECT empleado.apellidos FROM EmpleadoEJB empleado WHERE empleado.id=factura.vendedorId ) " +
			"from " +
			"FacturaEJB factura, " +		
			"TipoDocumentoEJB tipoDocumento "+

			"where " +
			"factura.valor > 0 and ";
			if (oficinaId != null)
				queryString += "factura.oficinaId = :oficinaId and ";

			queryString += "factura.tipodocumentoId=tipoDocumento.id " +  vendedor +
			/*"(factura.vendedorId=empleado.id or factura.vendedorId is null) and " +*/
			/*"(factura.vendedorId =:vendedorId or :vendedorId is null) and " +*/				
			"(factura.fechaFactura >= :fechaInicial or :fechaInicial is null) and " +
			"(factura.fechaFactura <= :fechaFinal or :fechaFinal is null)" +
			" order by factura.fechaFactura asc, factura.preimpresoNumero asc, tipoDocumento.codigo asc";

			Query query = manager.createQuery(queryString);
			if (fechaInicial != null)
				fechaInicial = utilitariosLocal.resetTimestampStartDate(fechaInicial);
			query.setParameter("fechaInicial", fechaInicial);
			if (fechaFinal != null)
				fechaFinal = utilitariosLocal.resetTimestampEndDate(fechaFinal);
			query.setParameter("fechaFinal", fechaFinal);
			//if(vendedorId!=null) query.setParameter("vendedorId", vendedorId);

			if (oficinaId != null)
				query.setParameter("oficinaId", oficinaId);

			List l=query.getResultList();
			Object[] tmp=null;
			List<ConsultaFacturaVendedor> consultaFacturaVendedorList=new ArrayList<ConsultaFacturaVendedor>();
			for(int i=0;i<l.size();i++)
			{
				ConsultaFacturaVendedor consultaFacturaVendedor=new ConsultaFacturaVendedor();
				tmp=(Object[])l.get(i);
				consultaFacturaVendedor.setFacturaId((Long)tmp[0]);
				consultaFacturaVendedor.setFechaEmision(new java.sql.Date(((java.sql.Timestamp)tmp[1]).getTime()));
				consultaFacturaVendedor.setTransaccion((String)tmp[2]);
				consultaFacturaVendedor.setNumeroFactura(tmp[3]!=null?""+((BigDecimal)tmp[3]).intValue():"");
				consultaFacturaVendedor.setObservacion((String)tmp[4]);
				consultaFacturaVendedor.setTotal((((BigDecimal)tmp[5]).add((BigDecimal)tmp[6])).subtract((BigDecimal)tmp[7]));
				consultaFacturaVendedor.setCodigoTransaccion((String)tmp[8]);
				consultaFacturaVendedor.setVendedor((String)tmp[9]+"-"+(String)tmp[10]);
				consultaFacturaVendedor.setDescuento((BigDecimal)tmp[7]);			
				consultaFacturaVendedor.setTotalBrutas((BigDecimal)tmp[5]);
				consultaFacturaVendedor.setValorIva((BigDecimal)tmp[6]);
				consultaFacturaVendedorList.add(consultaFacturaVendedor);
			}

			if(vendedorId==null)
				consultaFacturaVendedorList.addAll(consultaFacturasSinVendedor(fechaInicial, fechaFinal,oficinaId));

			//Collections.sort(consultaFacturaVendedorList, comparadorFacturaFechas);

			return consultaFacturaVendedorList;
		}


		//johanna
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Collection consultaFacturasCantidadProducto(java.sql.Timestamp fechaInicial, java.sql.Timestamp fechaFinal,
				Long color,Long modelo,Long talla,Long tipoproducto){

			String queryString = "FROM FacturastipoproductodetalleEJB d " + "where d.id is not null ";		   	
			//if (fundacionid != null)	queryString += " and (d.fundacionid = :fundacionid or d.fundaciondevolucionid= :fundacionid)";		   
			if (color != null)			queryString += " and d.colorId = :color ";
			if (modelo != null)			queryString += " and d.modeloId = :modelo ";
			if (talla != null)			queryString += " and d.tallaId = :talla";
			if (tipoproducto != null)	queryString += " and d.tipoproducto = :tipoproducto ";


			if (fechaInicial != null) {
				fechaInicial = utilitariosLocal.resetTimestampStartDate(fechaInicial);
				queryString += " and d.fecha >= :fechaInicial";
			}
			if (fechaFinal != null)	{
				fechaFinal = utilitariosLocal.resetTimestampEndDate(fechaFinal);
				queryString += " and d.fecha <= :fechaFinal";
			}

			queryString += " order by d.modeloId,d.fecha";	

			System.out.println("QWERUR>>>"+queryString);
			Query query = manager.createQuery(queryString);



			if (fechaInicial != null)		query.setParameter("fechaInicial", fechaInicial);
			if (fechaFinal != null)			query.setParameter("fechaFinal", fechaFinal);
			if (color != null)				query.setParameter("color", color);
			if (modelo != null)				query.setParameter("modelo", modelo);
			if (talla != null)				query.setParameter("talla", talla);
			if (tipoproducto != null)		query.setParameter("tipoproducto", tipoproducto);
			if (fechaInicial != null)		query.setParameter("fechaInicial", fechaInicial);
			if (fechaFinal != null)			query.setParameter("fechaFinal", fechaFinal);			
			return query.getResultList();		   

		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Collection getFacturaListFechas(java.sql.Date fechaInicial, java.sql.Date fechaFinal) {
			String queryString = "from FacturaEJB e where e.valor>0 ";
			// Add a an order by on all primary keys to assure reproducable results.
			if (fechaInicial != null)
				queryString += " and e.fechaFactura >= :fechaInicial";
			if (fechaFinal != null)
				queryString += " and e.fechaFactura <= :fechaFinal";

			String orderByPart = "";
			orderByPart += " order by e.fechaFactura desc";
			queryString += orderByPart;

			Query query = manager.createQuery(queryString);

			if (fechaInicial != null) {
				java.sql.Timestamp startDate = utilitariosLocal.resetTimestampStartDate(new java.sql.Timestamp(fechaInicial.getTime()));
				query.setParameter("fechaInicial", startDate);
			} if (fechaFinal != null) {
				java.sql.Timestamp endDate = utilitariosLocal.resetTimestampEndDate(new java.sql.Timestamp(fechaFinal.getTime()));
				query.setParameter("fechaFinal", endDate);
			}

			return query.getResultList();
		}

		public java.util.Collection findFacturaByPreimpresoNumero(
				String preimpresoNumero,Long idOficina,Long tipoDocumento) {

			String queryString = "from FacturaEJB e where " +
			"e.preimpresoNumero = :preimpresoNumero and " +
			"e.tipodocumentoId = :tipodocumentoId and " +
			"e.oficinaId =:oficinaId";

			// Add a an order by on all primary keys to assure reproducable results.
			String orderByPart = "";
			orderByPart += " order by e.id";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			query.setParameter("preimpresoNumero", preimpresoNumero);
			query.setParameter("tipodocumentoId", tipoDocumento);
			query.setParameter("oficinaId", idOficina);
			return query.getResultList();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public java.util.Collection findFacturaByOficinaIdAndByFechaFactura(
				Long idOficina, Date fechaFactura) {
			String objectName = "f";
			String queryString = "select  f, c  from FacturaEJB "
				+ objectName
				+ ", ClienteEJB c, ClienteOficinaEJB co where ";
			if (idOficina != null)
				queryString +=  " f.oficinaId = " + idOficina + " and ";
			queryString += "f.clienteoficinaId = co.id and co.clienteId = c.id and f.estado <> 'A' and f.preimpresoNumero is null";
			// String queryString = "select distinct f, c from FacturaEJB " +
			// objectName + ", ClienteEJB c, ClienteOficinaEJB co where f.oficinaId
			// = " + idOficina + " and f.clienteoficinaId = co.id and co.clienteId =
			// c.id and f.estado <> 'A'";

			if (fechaFactura != null)
				queryString += " and f.fechaFactura >= :fechaFactura";

			queryString += " order by f.preimpresoNumero asc, f.fechaFactura asc";
			Query query = manager.createQuery(queryString);

			if (fechaFactura != null)
				query.setParameter("fechaFactura", fechaFactura);

			return query.getResultList();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public java.util.Collection findFacturaByQuery(Map aMap,
				java.lang.Long idEmpresa) {
			String objectName = "e";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			String queryString = "select distinct e from FacturaEJB " + objectName
			+ ", TipoDocumentoEJB td where ";
			if (where != null && !"".equals(where.trim()))
				queryString += (where + " and ");
			queryString += " e.tipodocumentoId = td.id and td.empresaId = "
				+ idEmpresa;
			Query query = manager.createQuery(queryString);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);

			}
			return query.getResultList();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Collection<FacturaIf> getFacturaByMap_FechaInicio_FechaFin(
				Map<String, Object> aMap, Date fechaInicio, Date fechaFin,
				java.lang.Long idEmpresa) throws GenericBusinessException {
			try {
				String codigoDocumento = (String) aMap.get("codigoDocumento");
				Long clienteId = (Long) aMap.get("clienteId");
				String estado = (String) aMap.get("estado");
				String queryString = "select e from FacturaEJB e,TipoDocumentoEJB td,ClienteOficinaEJB co, ClienteEJB c where ";
				queryString += "e.tipodocumentoId = td.id and e.clienteoficinaId=co.id and co.clienteId = c.id ";
				queryString += " and td.codigo='" + codigoDocumento
				+ "' and td.empresaId=" + idEmpresa;
				queryString += " and e.estado='" + estado + "' ";
				if (clienteId != null && !clienteId.equals(""))
					queryString += " and c.id=" + clienteId;
				if (fechaInicio != null && fechaFin != null) {
					queryString += " and e.fechaFactura >= :fechaInicio and e.fechaFactura <= :fechaFin";
				}
				Query query = manager.createQuery(queryString);
				if (fechaInicio != null && fechaFin != null) {
					query.setParameter("fechaInicio", fechaInicio);
					query.setParameter("fechaFin", fechaFin);
				}
				return query.getResultList();
			} catch (Exception e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(
				"Se ha producido un error al consultar Venta");
			}
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public java.util.Collection getFacturaNoAnuladaList(int startIndex,
				int endIndex, Map aMap, java.lang.Long idEmpresa) {
			if ((endIndex - startIndex) < 0) {
				return new ArrayList();
			}
			String objectName = "e";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			String queryString = "select distinct e from FacturaEJB " + objectName
			+ ", TipoDocumentoEJB td where " + where;
			queryString += " and e.tipodocumentoId = td.id and td.empresaId = "
				+ idEmpresa + " and e.estado <> 'A'";
			queryString += " order by e.numero desc";
			Query query = manager.createQuery(queryString);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);

			}
			query.setFirstResult(startIndex);
			query.setMaxResults(endIndex - startIndex);
			return query.getResultList();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public java.util.Collection getFacturaList(int startIndex, int endIndex,
				Map aMap, java.lang.Long idEmpresa) {
			if ((endIndex - startIndex) < 0) {
				return new ArrayList();
			}
			String queryStringPreimpreso = "";
			if (aMap.get("preimpresoNumero") != null) {
				queryStringPreimpreso = " e.preimpresoNumero like '"
					+ aMap.get("preimpresoNumero") + "%' ";
				aMap.remove("preimpresoNumero");
			}

			String objectName = "e";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			String queryString = "select distinct e from FacturaEJB " + objectName
			+ ", TipoDocumentoEJB td where " + queryStringPreimpreso;
			if (!queryStringPreimpreso.equals(""))
				queryString += " and ";
			queryString += "e.tipodocumentoId = td.id and td.empresaId = "
				+ idEmpresa;
			if (where != null && !where.trim().equals(""))
				queryString += " and " + where;
			queryString += " order by e.preimpresoNumero desc";
			Query query = manager.createQuery(queryString);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);

			}
			query.setFirstResult(startIndex);
			query.setMaxResults(endIndex - startIndex);
			return query.getResultList();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public int getFacturaNoAnuladaListSize(Map aMap, java.lang.Long idEmpresa) {
			String objectName = "e";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			String queryString = "select distinct count(*) from FacturaEJB "
				+ objectName + ", TipoDocumentoEJB td where " + where;
			queryString += " and e.tipodocumentoId = td.id and td.empresaId = "
				+ idEmpresa + " and e.estado <> 'A'";
			Query query = manager.createQuery(queryString);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}
			List countQueryResult = query.getResultList();
			Long countResult = (Long) countQueryResult.get(0);
			log.debug("The list size is: " + countResult.intValue());
			return countResult.intValue();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public int getFacturaListSize(Map aMap, java.lang.Long idEmpresa) {
			String queryStringPreimpreso = "";
			if (aMap.get("preimpresoNumero") != null) {
				queryStringPreimpreso = " e.preimpresoNumero like '"
					+ aMap.get("preimpresoNumero") + "%' ";
				aMap.remove("preimpresoNumero");
			}

			String objectName = "e";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			String queryString = "select distinct count(*) from FacturaEJB "
				+ objectName + ", TipoDocumentoEJB td where "
				+ queryStringPreimpreso;
			if (!queryStringPreimpreso.equals(""))
				queryString += " and ";
			queryString += "e.tipodocumentoId = td.id and td.empresaId = "
				+ idEmpresa;
			if (where != null && !where.trim().equals(""))
				queryString += " and " + where;
			Query query = manager.createQuery(queryString);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);

			}
			List countQueryResult = query.getResultList();
			Long countResult = (Long) countQueryResult.get(0);
			log.debug("The list size is: " + countResult.intValue());
			return countResult.intValue();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Long procesarDevolucion(FacturaIf devolucionModel, Vector<FacturaDetalleIf> devolucionModelDetalleList, Map<String, Object> parametros) throws GenericBusinessException {
			Long idEmpresa = (Long) parametros.get("ID_EMPRESA");
			Long idOficina = (Long) parametros.get("ID_OFICINA");
			EmpleadoIf empleado = (EmpleadoIf) parametros.get("EMPLEADO");
			ClienteOficinaIf clienteOficina = (ClienteOficinaIf) parametros.get("CLIENTE_OFICINA");
			UsuarioIf usuario = (UsuarioIf) parametros.get("USUARIO");
			Long idDevolucion = 0L;
			Vector<FacturaDetalleIf> devolucionDetalleColeccion = new Vector<FacturaDetalleIf>();

			try {
				/*
				 * (1) Registramos la devolución
				 * 
				 */

				TipoDocumentoIf tipoDocumentoDevolucion = tipoDocumentoLocal.getTipoDocumento(devolucionModel.getTipodocumentoId());
				//Las devoluciones se registran en la tabla de factura
				FacturaEJB devolucion = registrarFactura(devolucionModel);
				manager.persist(devolucion);
				idDevolucion = devolucion.getPrimaryKey();

				/*
				 * (2) Registramos los detalles de la devolución y actualizamos
				 * 
				 */
				for (FacturaDetalleIf modelDetalle : devolucionModelDetalleList) {
					FacturaDetalleEJB devolucionDetalle = registrarDevolucionDetalle(modelDetalle, tipoDocumentoDevolucion);
					devolucionDetalle.setFacturaId(devolucion.getPrimaryKey());
					devolucionDetalleColeccion.add(devolucionDetalle);
					manager.merge(devolucionDetalle);
				}

				/*
				 * (3) Registramos el crédito al cliente (cartera de devolución)
				 * 
				 */

				//Registramos la cabecera de la cartera de devolución	 		
				CarteraData modelCarteraDevolucion = new CarteraData();
				modelCarteraDevolucion.setReferenciaId(devolucion.getPrimaryKey());
				nuevaCodificacionActiva = (devolucion.getFechaFactura().getYear() + 1900 <= 2008) ? false: true;
				String unNumeroCartera = getNumeroCartera(devolucion.getFechaFactura(), tipoDocumentoDevolucion.getCodigo(), idEmpresa, idOficina);
				modelCarteraDevolucion.setCodigo(unNumeroCartera);
				modelCarteraDevolucion.setCodigo(modelCarteraDevolucion.getCodigo()+ formatoSerial.format(getMaximoNumeroCartera(modelCarteraDevolucion)));
				modelCarteraDevolucion.setUsuarioId(usuario.getId());
				CarteraEJB carteraDevolucion = registrarCarteraIgualacion(modelCarteraDevolucion, null, devolucion, true, idOficina, tipoDocumentoDevolucion.getCodigo());
				manager.persist(carteraDevolucion);

				//Registramos los detalles de la cartera de devolución			
				CarteraDetalleEJB carteraDetalle = new CarteraDetalleEJB();
				BigDecimal totalSaldo = devolucion.getValor().add(devolucion.getIva());
				totalSaldo = totalSaldo.subtract(devolucion.getDescuento());
				totalSaldo = totalSaldo.subtract(devolucion.getDescuentoGlobal());
				carteraDetalle = registrarCarteraDetalleIgualacion(totalSaldo.toString(), totalSaldo.toString(), buscarTipoPagoByCodigo(TIPO_PAGO_CREDITO_CLIENTE).getId().toString(), tipoDocumentoDevolucion.getCodigo(), "", "", true);
				carteraDetalle.setSecuencial(1);
				carteraDetalle.setCarteraId(carteraDevolucion.getId());
				manager.persist(carteraDetalle);

				/*
				 *  (4) Generamos el asiento automático de la devolución
				 *  
				 */

				AsientoIf asiento = generarAsientoDevolucion(devolucion, devolucionDetalleColeccion, null, false, usuario, "");
			} catch (GenericBusinessException e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(e.getMessage());
			} catch (Exception e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(
				"Error general al insertar informaci\u00f3n en Factura-FacturaDetalle");
			}

			return idDevolucion;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Long procesarFactura(FacturaIf model, Vector<PedidoDetalleIf> modelDetalleList, Long idEmpresa, Long idOficina, 
				Vector<PresupuestoIf> presupuestos, Vector<PresupuestoFacturacionIf> presupuestoFacturacionList, String tipoReferencia, UsuarioIf usuario, boolean donacionActivada, 
				Long idFundacion,Tarea tarea)throws GenericBusinessException {
				Long idFactura = procesarFactura(model, modelDetalleList, idEmpresa, idOficina, presupuestos, presupuestoFacturacionList, tipoReferencia, usuario);
				try {
					FacturaIf factura = getFactura(idFactura);
					VentasTrackData ventasTrack = new VentasTrackData();
					ventasTrack.setCajasesionId(0L);
					double valorFactura = factura.getValor().doubleValue();
					double descuentoFactura = factura.getDescuento().doubleValue();
					double descuentoGlobalFactura = factura.getDescuentoGlobal().doubleValue();
					double descuentoTotalFactura = descuentoFactura + descuentoGlobalFactura;
					double porcentajeComision = factura.getPorcentajeComisionAgencia().doubleValue();
					double comision = ((valorFactura - descuentoTotalFactura) * porcentajeComision) / 100D;
					double ivaFactura = factura.getIva().doubleValue();
					double iceFactura = factura.getIce().doubleValue();
					double otroImpuesto = factura.getOtroImpuesto().doubleValue();
					double impuestoTotalFactura = ivaFactura + iceFactura + otroImpuesto;
					double totalFactura = valorFactura - descuentoTotalFactura + impuestoTotalFactura + comision;
					ventasTrack.setValorTotal(BigDecimal.valueOf(totalFactura));
					ventasTrack.setFechaVenta(new java.sql.Timestamp(model.getFechaFactura().getTime()));
					Long idVentasTrack = ventastrackLocal.procesarVentasTrack(ventasTrack);

					if (donacionActivada) {
						Iterator it = facturaDetalleLocal.findFacturaDetalleByFacturaId(idFactura).iterator();
						double donacion = 0D;
						while (it.hasNext()) {
							FacturaDetalleIf facturaDetalle = (FacturaDetalleIf) it.next();
							ProductoIf producto = productoLocal.getProducto(facturaDetalle.getProductoId());
							GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
							double cantidad = facturaDetalle.getCantidad().doubleValue();
							Iterator donacionIterator = donacionTipoProductoLocal.findDonacionTipoproductoByTipoproductoId(generico.getTipoproductoId()).iterator();
							if (donacionIterator.hasNext()) {
								DonacionTipoproductoIf donacionTipoProducto = (DonacionTipoproductoIf) donacionIterator.next();
								donacion += (cantidad * donacionTipoProducto.getValor().doubleValue());
							}
						}

						if (donacion > 0D) {
							VentasPagosData ventasPagos = new VentasPagosData();
							ventasPagos.setVentastrackId(idVentasTrack);
							ventasPagos.setReferencia("DONACION");
							ventasPagos.setReferenciaId(new Long(idFundacion));
							ventasPagos.setTipo(buscarTipoPagoByCodigo(TIPO_PAGO_DONACION).getId());					
							ventasPagos.setValor(BigDecimal.valueOf(donacion));
							ventasPagosLocal.addVentasPagos(ventasPagos);
						}
					}

					VentasDocumentosData ventasDocumentos = new VentasDocumentosData();
					ventasDocumentos.setVentastrackId(idVentasTrack);
					ventasDocumentos.setTablaNombre("FACTURA");
					ventasDocumentos.setTablaId(idFactura);
					ventasDocumentosLocal.addVentasDocumentos(ventasDocumentos);

					Long pedidoId = factura.getPedidoId();
					if ( pedidoId != null ){
						PedidoIf pedido = pedidoLocal.getPedido(pedidoId);
						if ( pedido != null )
							procesoOrdenTrabajoLocal.procesarPedido(pedido, true, tarea);
					}

				} catch (GenericBusinessException e) {
					ctx.setRollbackOnly();
					e.printStackTrace();
					throw new GenericBusinessException(e.getMessage());
				} catch (Exception e) {
					ctx.setRollbackOnly();
					e.printStackTrace();
					throw new GenericBusinessException("Error general al insertar informaci\u00f3n en Factura-FacturaDetalle");
				}
			return idFactura;
			
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Long procesarFactura(FacturaIf model, Vector<PedidoDetalleIf> modelDetalleList, Long idEmpresa, Long idOficina, Vector<PresupuestoIf> presupuestos, Vector<PresupuestoFacturacionIf> presupuestoFacturacionList, String tipoReferencia, UsuarioIf usuario) throws GenericBusinessException {
			String ESTADO_INCOMPLETO = "I";
			String ESTADO_COMPLETO = "C";
			String ESTADO_FACTURADO = "F";
			String ESTADO_APROBADO= "A";
			String OPCION_NO = "N";
			int contadorDetallesFacturados = 0;
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			Long idFactura = 0L;
			boolean esFacturaReembolso = false;

			try {

				/*
				 * (1) Actualizamos los numeradores
				 * 
				 */
				NumeradoresIf numeradorFactura = (NumeradoresIf) numeradoresLocal.findNumeradoresByNombreTablaAndByEmpresaId("FACTURA", idEmpresa).iterator().next();
				Long numeroFactura = numeradorFactura.getUltimoValor().longValue() + 1L;
				NumeradoresIf numeradorPreimpreso = (NumeradoresIf) numeradoresLocal.findNumeradoresByNombreTablaAndByEmpresaId("PRE IMPRESO", idEmpresa).iterator().next();
				Long numeroPreimpreso = numeradorPreimpreso.getUltimoValor().longValue() + 1L;
				NumeradoresIf numeradorCartera = (NumeradoresIf) numeradoresLocal.findNumeradoresByNombreTablaAndByEmpresaId("CARTERA",	idEmpresa).iterator().next();
				Long numeroCartera = numeradorCartera.getUltimoValor().longValue() + 1L;
				numeradorFactura.setUltimoValor(BigDecimal.valueOf(numeroFactura));
				numeradorPreimpreso.setUltimoValor(BigDecimal.valueOf(numeroPreimpreso));
				numeradorCartera.setUltimoValor(BigDecimal.valueOf(numeroCartera));
				
				PedidoIf pedido = pedidoLocal.getPedido(model.getPedidoId());
				TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(pedido.getTipodocumentoId());
				
				/*
				 * (2)
				 * 
				 */
				
				Iterator modelDetalleListIterator = modelDetalleList.iterator();
				while (modelDetalleListIterator.hasNext()) {
					PedidoDetalleIf pedidoDetalle = verificarStockPorLote((PedidoDetalleIf) modelDetalleListIterator.next(), model.getBodegaId());
					if (pedidoDetalle.getCantidad().doubleValue() > 0.0)
						contadorDetallesFacturados++;
					else
						modelDetalleListIterator.remove();
				}

				if (tipoDocumento.getReembolso().equals("S")) {
					esFacturaReembolso = true;
				} else {
					esFacturaReembolso = false;
				}
				
				if (contadorDetallesFacturados > 0) {

					/*
					 * (3) Registramos la factura a ser generada
					 * 
					 */

					model.setNumero(BigDecimal.valueOf(numeroFactura));
					FacturaEJB factura = registrarFactura(model, modelDetalleList, esFacturaReembolso);

					/*
					 * (4) Guardamos en la base de datos la factura y los
					 * numeradores actualizados
					 * 
					 */
					manager.persist(factura);
					idFactura = factura.getPrimaryKey();
					manager.merge(numeradorFactura);
					manager.merge(numeradorPreimpreso);
					manager.merge(numeradorCartera);

					/*
					 * (5) Actualizamos el presupuesto
					 * 
					 */
					if (presupuestos.size() > 0) {
						/*
						 * (5.1) Actualizamos el estado del presupuesto a FACTURADO
						 * 
						 */
						//modelPresupuesto.setEstado(ESTADO_FACTURADO);
						//manager.merge(modelPresupuesto);

						/*
						 * (5.2) Si el presupuesto tiene Negociacion Directa o Comision Pura
						 *       ingresamos los datos en la tabla PRESUPUESTO_FACTURACION
						 */
						for(PresupuestoFacturacionIf presupuestoFacturacionData : presupuestoFacturacionList){
							PresupuestoFacturacionEJB presupuestoFacturacion = new PresupuestoFacturacionEJB();
							presupuestoFacturacion.setEstado(presupuestoFacturacionData.getEstado());
							presupuestoFacturacion.setFacturaId(idFactura);
							presupuestoFacturacion.setPresupuestoDetalleId(presupuestoFacturacionData.getPresupuestoDetalleId());
							presupuestoFacturacion.setTipo(presupuestoFacturacionData.getTipo());
							manager.persist(presupuestoFacturacion);
						}

						// Reviso si ya han sido facturados todos los detalles del presupuesto
						// para cambiar su estado a FACTURADO, sino sigue como APROBADO
						// Si la facturacion fue Normal tambien cambia el estado a FACTURADO
						for(PresupuestoIf modelPresupuesto : presupuestos){
							if(presupuestoFacturacionList.size() > 0 
									&& (presupuestoFacturacionList.get(0).getTipo().equals("N") || 
									revisarPresupuestoFacturacion(modelPresupuesto, presupuestoFacturacionList))){
								modelPresupuesto.setEstado(ESTADO_FACTURADO);
								manager.merge(modelPresupuesto);
							}else{
								modelPresupuesto.setEstado(ESTADO_APROBADO);
								manager.merge(modelPresupuesto);
							}
						}						
					}

					/*
					 * (6) Registramos los detalles de la factura y actualizamos el
					 * pedido y sus detalles
					 * 
					 */
					for (PedidoDetalleIf modelDetalle : modelDetalleList) {
						/*
						 * (6.1) Registramos y guardamos los detalles de la factura
						 * 
						 */
						FacturaDetalleData modelFacturaDetalle = new FacturaDetalleData();
						modelFacturaDetalle.setFacturaId(factura.getPrimaryKey());

						PresupuestoIf modelPresupuesto = presupuestos.size() > 0 ? presupuestos.get(0) : null;
						
						FacturaDetalleEJB facturaDetalle = registrarFacturaDetalle(
								modelFacturaDetalle, modelDetalle, factura
								.getPorcentajeComisionAgencia().doubleValue(), modelPresupuesto,
								(tipoDocumento.getCodigo().equals("FCO")) ? true : false);

						manager.merge(facturaDetalle);

						/*
						 * (6.2) Actualizamos el estado del pedido y sus detalles
						 * 
						 */
						PedidoIf pedidoIf = pedidoLocal.getPedido(factura.getPedidoId());

						/*
						 * Si la cantidad facturada es menor a la cantidad pedida,
						 * es decir si el restante por facturar es mayor que 0
						 * entonces el estado del pedido a partir del cual se genera
						 * la factura pasará a INCOMPLETO, caso contrario, es decir,
						 * si el stock satisface completamente la cantidad pedida de
						 * producto, el pedido pasará a estado COMPLETO.
						 * 
						 */
						Double restante = modelDetalle.getCantidadpedida().doubleValue() - modelDetalle.getCantidad().doubleValue();

						if (restante > 0.0)
							pedidoIf.setEstado(ESTADO_INCOMPLETO);
						else if (pedidoIf.getEstado().compareTo(ESTADO_INCOMPLETO) != 0)
							pedidoIf.setEstado(ESTADO_COMPLETO);

						manager.merge(pedidoLocal.registrarPedido(pedidoIf));
						modelDetalle.setPedidoId(pedidoIf.getId());
						manager.merge(pedidoLocal.registrarPedidoDetalle(modelDetalle));
					}

					/*
					 * (7) Registramos la cartera
					 * 
					 */
					CarteraData modelCartera = new CarteraData();
					modelCartera.setReferenciaId(factura.getPrimaryKey());
					nuevaCodificacionActiva = (factura.getFechaFactura().getYear() + 1900 <= 2008) ? false: true;
					String unNumeroCartera = getNumeroCartera(model.getFechaFactura(), "FAC", idEmpresa, idOficina);
					modelCartera.setCodigo(unNumeroCartera);
					modelCartera.setCodigo(modelCartera.getCodigo()+ formatoSerial.format(getMaximoNumeroCartera(modelCartera)));
					// } else
					// modelCartera.setCodigo(factura.getFechaFactura().getYear() +
					// 1900 + "-" +
					// formatoSerial.format(numeroCartera.doubleValue()));
					CarteraEJB cartera = registrarCartera(modelCartera, null,factura, true, idOficina);
					manager.persist(cartera);
					List<DocumentoIf> modelDocumentoList = (List) documentoLocal.findDocumentoByTipoDocumentoId(factura.getTipodocumentoId());

					int count = 0;
					Vector<FacturaDetalleIf> facturaDetalleColeccion = new Vector<FacturaDetalleIf>();
					double valorFacturas = 0.0;
					for (DocumentoIf modelDocumento : modelDocumentoList) {
						Collection facturas = facturaDetalleLocal.findFacturaDetalleByDocumentoIdAndByFacturaId(modelDocumento.getId(), factura.getPrimaryKey());
						Iterator itFacturas = facturas.iterator();
						CarteraDetalleData carteraDetalleModel = new CarteraDetalleData();
						CarteraDetalleEJB carteraDetalle = new CarteraDetalleEJB();
						boolean registrarCartera = false;
						FacturaDetalleIf facturaDetalle = null;
						while (itFacturas.hasNext()) {
							facturaDetalle = (FacturaDetalleIf) itFacturas.next();
							facturaDetalleColeccion.add(facturaDetalle);
							DocumentoIf documento = null;
							documento = documentoLocal.getDocumento(facturaDetalle.getDocumentoId());

							// Si el documento del detalle de la factura que leo es
							// de bonificación para ese no genero ninguna cartera
							if (documento.getBonificacion().equals(OPCION_NO)) {
								if(tipoDocumento.getCodigo().equals("FCO")){
									double valorFacturaDetalle = facturaDetalle.getValor().doubleValue();
									double ivaFacturaDetalle = facturaDetalle.getIva().doubleValue();
									double valorCarteraDetalle = valorFacturaDetalle + ivaFacturaDetalle;
									valorFacturas += valorCarteraDetalle;
									carteraDetalleModel.setCarteraId(cartera.getPrimaryKey());
									registrarCartera = true;
								}else{
									double valorFacturaDetalle = facturaDetalle.getValor().doubleValue();
									double descuentoFacturaDetalle = facturaDetalle.getDescuento().doubleValue();
									double porcentajeDescuentosVarios = facturaDetalle.getPorcentajeDescuentosVarios().doubleValue() / 100;
									double descuentosVarios = valorFacturaDetalle * porcentajeDescuentosVarios;
									double descuentoGlobalFacturaDetalle = facturaDetalle.getDescuentoGlobal().doubleValue();
									double descuentoTotalFacturaDetalle = descuentoFacturaDetalle + descuentosVarios + descuentoGlobalFacturaDetalle;	
									double ivaFacturaDetalle = facturaDetalle.getIva().doubleValue();
									double iceFacturaDetalle = facturaDetalle.getIce().doubleValue();
									double otroImpuestoDetalle = facturaDetalle.getOtroImpuesto().doubleValue();
									double impuestoTotalFacturaDetalle = ivaFacturaDetalle + iceFacturaDetalle + otroImpuestoDetalle;
									double porcentajeComision = factura.getPorcentajeComisionAgencia().doubleValue();
									double comision = ((valorFacturaDetalle - descuentoTotalFacturaDetalle) * porcentajeComision) / 100D;
									double valorCarteraDetalle = 0D;

									if(tipoDocumento.getCodigo().equals("FCO")){
										valorCarteraDetalle = valorFacturaDetalle + impuestoTotalFacturaDetalle;
									}else{
										valorCarteraDetalle = valorFacturaDetalle - descuentoTotalFacturaDetalle + comision+ impuestoTotalFacturaDetalle;
									}

									valorFacturas += valorCarteraDetalle;
									carteraDetalleModel.setCarteraId(cartera.getPrimaryKey());
									registrarCartera = true;
								}								
							}	
						}

						valorFacturas=utilitariosLocal.redondeoValor(valorFacturas);

						if (registrarCartera && facturaDetalle != null) {
							carteraDetalle = registrarCarteraDetalle(carteraDetalleModel, facturaDetalle, factura, valorFacturas);
						}
						// si el detalle de la cartera posee el id de la cartera
						// queire decir que ha salido de la iteracion
						// por lo tanto aumento en uno el secuencial con el cual se
						// generan los detalles
						// de cartera, esto se valida porque puede darse el caso de
						// que para determinado documento no exista ningun detalle
						// de factura
						if (carteraDetalle.getCarteraId() != null) {
							count++;
							carteraDetalle.setSecuencial(count);
							manager.merge(carteraDetalle);
						}
					}

					/*
					 * (8) Registramos los movimientos
					 * 
					 */

					//TODO: VALIDAR QUE SE HAGA CUANDO NO ES DE SERVICIO
					List<FacturaDetalleIf> listaDetallefactura = (List<FacturaDetalleIf>) facturaDetalleLocal.findFacturaDetalleByFacturaId(factura.getId());
					movimientoSessionLocal.procesarFactura(factura, listaDetallefactura, usuario);

					/*
					 * (9) Si cabe, actualizamos los stocks de productos
					 * 
					 */

					// SE PASO ESTE CODIGO A verificarStockPorLote(PedidoDetalleIf
					// pedidoDetalle, Long idBodega)
					/*
					 * for (StockIf modelStock : stockIfVector) { StockEJB stock =
					 * registrarStock(modelStock); manager.merge(stock); }
					 */
					
					/*
					 * (10) Registrar compras asociadas
					 */
					
					Iterator<FacturaDetalleIf> detallesIt = listaDetallefactura.iterator();
					while (detallesIt.hasNext()) {
						FacturaDetalleIf facturaDetalle = detallesIt.next();
						ProductoIf producto = productoLocal.getProducto(facturaDetalle.getProductoId());
						String comprasReembolsoAsociadas = facturaDetalle.getComprasReembolsoAsociadas() != null?facturaDetalle.getComprasReembolsoAsociadas():"";
						String[] preimpresosCompras = comprasReembolsoAsociadas.split(", ");
						int len = preimpresosCompras.length;
						for (int i=0; i<len; i++) {
							if (preimpresosCompras[i].length() > 0) {
								Map queryMap = new HashMap();
								queryMap.put("proveedorId", producto.getProveedorId());
								queryMap.put("preimpreso", preimpresosCompras[i]);
								
								CompraIf compra = null;
								if(findCompraAsociadaByQuery(queryMap).size() > 0){
									compra = (CompraIf) findCompraAsociadaByQuery(queryMap).iterator().next();
								}
								
								if(compra != null){
									FacturaDetalleCompraAsociadaEJB data = new FacturaDetalleCompraAsociadaEJB();
									data.setCompraId(compra.getId());
									data.setFacturaDetalleId(facturaDetalle.getId());
									manager.merge(data);
								}							
							}
						}
					}
					
					/*
					 * (11) Generar asiento contable
					 */

					// ASIENTO
					AsientoIf asiento = null;
					int yearFactura = factura.getFechaFactura().getYear() + 1900;
					int monthFactura = factura.getFechaFactura().getMonth() + 1;
					// if (yearFactura >= 2008 && monthFactura >= 9)
					Iterator it = facturaDetalleColeccion.iterator();
					while (it.hasNext()) {
						FacturaDetalleIf detail = (FacturaDetalleIf) it.next();
						DocumentoIf documento = documentoLocal.getDocumento(detail.getDocumentoId());
						if (documento.getBonificacion().equals("S"))
							it.remove();
					}
					asiento = generarAsientoFactura(factura,
							facturaDetalleColeccion, null, false, usuario,
							tipoReferencia);

					// VERIFICACION DE VALORES CON EL ASIENTO
					// valorFacturas =
					// utilitariosLocal.redondeoValor(valorFacturas);
					double valor = factura.getValor().doubleValue();
					//double descuento = utilitariosLocal.redondeoValor(factura.getDescuento().doubleValue());
					//double descuentoGlobal = utilitariosLocal.redondeoValor(factura.getDescuentoGlobal().doubleValue());
					double descuento = factura.getDescuento().doubleValue();
					double descuentosVarios = factura.getDescuentosVarios().doubleValue();
					double descuentoGlobal = factura.getDescuentoGlobal().doubleValue();
					double iva = factura.getIva().doubleValue();
					double ice = factura.getIce().doubleValue();
					double otroImpuesto = factura.getOtroImpuesto().doubleValue();
					double porcentajeComision = factura.getPorcentajeComisionAgencia().doubleValue();
					//double comision = utilitariosLocal.redondeoValor(((valor - descuento - descuentoGlobal) * porcentajeComision) / 100D);
					double comision = ((valor - descuento - descuentosVarios - descuentoGlobal) * porcentajeComision) / 100D;
					double totalFactura = utilitariosLocal.redondeoValor(valor + iva + ice + otroImpuesto - descuento - descuentosVarios - descuentoGlobal + comision);

					if (totalFactura != valorFacturas && (totalFactura - valorFacturas) > 0.02)
						throw new GenericBusinessException("Error al guardar la Factura No. " + factura.getNumero()	+ ", el valor de la cabecera de la Factura no coincide con el Detalle");
					double valorCartera = utilitariosLocal.redondeoValor(cartera.getValor().doubleValue());
					if (valorCartera != valorFacturas && (valorCartera - valorFacturas) > 0.02 /*&& ((valorCartera-utilitariosLocal.redondeoValor(valorFacturas) >= 0.01) || (utilitariosLocal.redondeoValor(valorFacturas)-valorCartera >= 0.01))*/)
						throw new GenericBusinessException("Error al guardar la Factura No. " + factura.getNumero()
								+ ", el valor de la Cartera no coincide con los valores del Detalle de la Factura");
					if (asiento != null) {
						double debe = 0.0;
						double haber = 0.0;
						Collection<AsientoDetalleIf> asientosDetalle = asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getId());
						for (Iterator itAsientos = asientosDetalle.iterator(); itAsientos.hasNext();) {
							AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) itAsientos.next();
							debe += asientoDetalle.getDebe().doubleValue();
							haber += asientoDetalle.getHaber().doubleValue();
						}
						debe = utilitariosLocal.redondeoValor(debe);
						haber = utilitariosLocal.redondeoValor(haber);
						/*
						 * if ( debe != haber ) throw new
						 * GenericBusinessException("Error al guardar la Factura No.
						 * "+factura.getNumero()+", el valor del Debe y Haber no
						 * coinciden en el Asiento"); else if ( debe !=
						 * utilitariosLocal.redondeoValor(valorFacturas) ) throw new
						 * GenericBusinessException("Error al guardar la Factura No.
						 * "+factura.getNumero()+", el valor del Debe y Haber no
						 * coinciden con el detalle de la Factura");
						 */
					} else {
						// if (yearFactura >= 2008 && monthFactura >= 9)

						throw new GenericBusinessException("No se gener\u00f3 el asiento de la Factura No. " + factura.getNumero());
					}
				}
			} catch (GenericBusinessException e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(e.getMessage());
			} catch (Exception e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(
				"Error general al insertar informaci\u00f3n en Factura-FacturaDetalle");
			}

			return idFactura;
		}
		
		//Solo si todos los presupuestos detalle han sido facturados devuelve verdadero.
		public boolean revisarPresupuestoFacturacion(PresupuestoIf presupuestoIf, Vector<PresupuestoFacturacionIf> presupuestoFacturacionList){
			try {
				Collection presupuestosDetalle = presupuestoDetalleLocal.findPresupuestoDetalleByPresupuestoId(presupuestoIf.getId());
				Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
				while(presupuestosDetalleIt.hasNext()){
					PresupuestoDetalleIf presupuestoDetalle = (PresupuestoDetalleIf)presupuestosDetalleIt.next();
					
					if(presupuestoDetalle.getReporte().equals("N") && presupuestoDetalle.getPrecioventa().compareTo(new BigDecimal(0)) == 1){
						
						boolean facturadoCliente = true;
						boolean facturadoNegociacionDirecta = true;
						boolean facturadoComisionPura = true;
						boolean facturadoParcial = true;
						
						// si en un detalle un proveedor tiene Negociacion Directa y Comision Pura, tiene mas peso la Comision Pura
						// y ese porcentaje es el que se le cobrará, por eso no entra este primer if pero si al segundo.
						if(presupuestoDetalle.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(0)) == 1
								&& (presupuestoDetalle.getPorcentajeComisionPura() == null || presupuestoDetalle.getPorcentajeComisionPura().compareTo(new BigDecimal(0)) <= 0)){
							facturadoNegociacionDirecta = false;
						}

						if((presupuestoDetalle.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(0)) == 1
								&& presupuestoDetalle.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(100)) == -1)
							|| (presupuestoDetalle.getPorcentajeComisionPura().compareTo(new BigDecimal(0)) == 1
								&& presupuestoDetalle.getPorcentajeComisionPura().compareTo(new BigDecimal(100)) == -1)){
							facturadoCliente = false;
							facturadoParcial = false;
						}
						
						if(presupuestoDetalle.getPorcentajeComisionPura().compareTo(new BigDecimal(0)) == 1){
							facturadoComisionPura = false;
						}
						
						Map presupuestoFacturacionMap = new HashMap();
						//busco los registros solo con estado Facturado
						presupuestoFacturacionMap.put("estado", ESTADO_PRESUPUESTO_FACTURACION_FACTURADO);
						presupuestoFacturacionMap.put("presupuestoDetalleId", presupuestoDetalle.getId());
						Collection presupuestosFacturacion = presupuestoFacturacionLocal.findPresupuestoFacturacionByQuery(presupuestoFacturacionMap);
						//presupuestosFacturacion.addAll(presupuestoFacturacionList);
						
						if(presupuestosFacturacion.size() > 0){
							Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
							while(presupuestosFacturacionIt.hasNext()){
								PresupuestoFacturacionIf presupuestoFacturacion = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
								
								if(presupuestoFacturacion.getId() != null){
									if(!facturadoCliente && presupuestoFacturacion.getTipo().equals("C")){
										facturadoCliente = true;
									}
									
									if(!facturadoParcial && presupuestoFacturacion.getTipo().equals("R")){
										facturadoParcial = true;
									}
									
									if(!facturadoNegociacionDirecta && presupuestoFacturacion.getTipo().equals("D")){
										facturadoNegociacionDirecta = true;
									}
									
									if(!facturadoComisionPura && presupuestoFacturacion.getTipo().equals("P")){
										facturadoComisionPura = true;
									}
								}
							}
							
							if((!facturadoCliente && !facturadoParcial) || !facturadoNegociacionDirecta || !facturadoComisionPura){
								return false;
							}
							
						}else{
							return false;
						}
					}												
				}
			
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
			
			return true;
		}

		public PagoTarjetaIf registrarPagoTarjetaCredito(String nombreCliente,String telefono,String identificacion,String noAutorizacion,String noVoucher,String tipoTarjeta,String valor,String noReferencia) throws GenericBusinessException {
			PagoTarjetaData movipos = new PagoTarjetaData();
			movipos.setNombreCliente(nombreCliente);
			movipos.setTelefono(telefono);
			movipos.setIdentificacion(identificacion);
			movipos.setNoAutorizacion(noAutorizacion.replaceAll("Autoriz:",""));
			movipos.setNoVoucher(noVoucher);
			movipos.setTipoTarjeta(tipoTarjeta);
			movipos.setValor(new BigDecimal(valor));
			movipos.setNoReferencia(noReferencia);
			movipos.setFecha(utilitariosLocal.getServerDateTimeStamp());
			return movipos;
		}

		public VentasDocumentosIf registrarVentasDocumentos(Long idprincipal, Long idDocumento, String referenciaNombre) {
			VentasDocumentosIf movipos = new VentasDocumentosEJB();
			movipos.setVentastrackId(idprincipal);
			movipos.setTablaNombre(referenciaNombre);
			movipos.setTablaId(idDocumento);
			return movipos;
		}

		private TipoPagoIf buscarTipoPagoByCodigo(String codigo) {
			TipoPagoIf tipoPago = new TipoPagoData();
			try {
				Iterator it = tipoPagoLocal.findTipoPagoByCodigo(codigo).iterator();
				if (it.hasNext())
					tipoPago = (TipoPagoIf) it.next();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
			return tipoPago;
		}

		public GiftcardMovimientoIf registrarMovimientoGiftcard(BigDecimal saldoAnterior, BigDecimal valor, Long giftcardId, Long transaccionId, Long tipoDocumentoId) {
			GiftcardMovimientoIf giftcardMovimiento = new GiftcardMovimientoEJB();
			giftcardMovimiento.setSaldoAnterior(saldoAnterior);
			giftcardMovimiento.setValor(valor);
			giftcardMovimiento.setFechaMovimiento(utilitariosLocal.getServerDateTimeStamp());
			giftcardMovimiento.setTransaccionId(transaccionId);
			giftcardMovimiento.setGiftcardId(giftcardId);
			giftcardMovimiento.setTipoDocumentoId(tipoDocumentoId);
			return (giftcardMovimiento);
		}

		public CarteraEJB procesarVentasGift(Long idprincipal,Vector<PedidoDetalleIf> detalles_GC,Long idClienteOficina, EmpleadoIf empleado, Long idOficina, Long idEmpresa, UsuarioIf usuario){
			CarteraEJB reciboCajaGiftcard = null;
			double totalGiftcard = 0D;

			try {
				if (detalles_GC.size() != 0) {
					for (int l = 0; l < detalles_GC.size(); l++) {
						PedidoDetalleData temporal = (PedidoDetalleData) detalles_GC.get(l);
						GiftcardIf giftcard = giftcardLocal.findGiftcardByIdWebService(idEmpresa, temporal.getGiftcardId());
						giftcard.setEstado(ESTADO_ACTIVO);
						manager.merge(giftcard);
						//giftcardLocal.saveGiftcard(giftcardData);
						GiftcardMovimientoIf giftcardMovimiento = registrarMovimientoGiftcard(giftcard.getValor(),giftcard.getSaldo(), giftcard.getId(), null, null);
						manager.merge(giftcardMovimiento);
						//Agrego a ventas documentos para tener registro que en esa transacción se compro un gift card.
						ProductoIf producto = productoLocal.getProducto(giftcard.getProductoId());
						VentasDocumentosIf pagosDocumentos =  registrarVentasDocumentos(idprincipal,producto.getId(),"PRODUCTO");				
						manager.merge(pagosDocumentos);
						totalGiftcard += temporal.getValor().doubleValue();
					}
				}

				if (totalGiftcard > 0D) {
					reciboCajaGiftcard = generarReciboCajaVentaGiftcard(idClienteOficina, empleado, idOficina, idEmpresa, usuario, totalGiftcard);
				}
			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return reciboCajaGiftcard;
		}

		public void procesarVentasTarjetaAfiliacion(Vector<TarjetaIf> detalles_TA, Long idClienteOficina, Long idEmpresa){
			try {
				if (detalles_TA.size() != 0) {
					for (int l = 0; l < detalles_TA.size(); l++) {
						TarjetaEJB temporal = (TarjetaEJB) detalles_TA.get(l);
						System.out.println("ID TARJETA ><<<<<<<<<<<<<<<<< " + temporal.getId());
						TarjetaIf tarjeta = tarjetaSessionLocal.findTarjetaByIdWebService(idEmpresa, temporal.getId());
						tarjeta.setClienteoficinaId(idClienteOficina);
						tarjeta.setFechaEmision(utilitariosLocal.getServerDateSql().getTime());
						tarjeta.setFechaUltimoCambioStatus(utilitariosLocal.getServerDateSql().getTime());
						//tarjeta.setReferidoporId(idReferido);
						tarjeta.setEstado(ESTADO_ACTIVO);
						manager.merge(tarjeta);
					}
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}

		private CarteraEJB generarReciboCajaVentaGiftcard(Long idClienteOficina, EmpleadoIf empleado, Long idOficina, Long idEmpresa, UsuarioIf usuario, double totalGiftcard) {
			//CREAR RECIBO DE CAJA GIFTCARD
			CarteraEJB reciboCajaGiftcard = new CarteraEJB();
			try {
				java.sql.Timestamp now = new java.sql.Timestamp(utilitariosLocal.getServerDateSql().getTime());
				String unNumeroCartera = getNumeroCartera(new java.sql.Date(now.getTime()), "RCG", idEmpresa,idOficina);
				reciboCajaGiftcard.setCodigo(unNumeroCartera);
				reciboCajaGiftcard.setCodigo(reciboCajaGiftcard.getCodigo()+ formatoSerial.format(getMaximoNumeroCartera(reciboCajaGiftcard)));
				reciboCajaGiftcard.setTipo("C");
				reciboCajaGiftcard.setClienteoficinaId(idClienteOficina);
				Map parameterMap = new HashMap();
				parameterMap.put("codigo", "RCG");
				parameterMap.put("empresaId", idEmpresa);
				Iterator it = tipoDocumentoLocal.findTipoDocumentoByQuery(parameterMap).iterator();
				if (it.hasNext()) {
					TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) it.next();
					reciboCajaGiftcard.setTipodocumentoId(tipoDocumento.getId());
				}
				reciboCajaGiftcard.setComentario("VENTA GIFTCARDS");
				reciboCajaGiftcard.setEstado("N");
				reciboCajaGiftcard.setFechaEmision(utilitariosLocal.getServerDateTimeStamp());
				it = monedaLocal.findMonedaByCodigo(MONEDA_DOLAR).iterator();
				if (it.hasNext()) {
					MonedaIf moneda = (MonedaIf) it.next();
					reciboCajaGiftcard.setMonedaId(moneda.getId());
				}
				reciboCajaGiftcard.setOficinaId(idOficina);
				reciboCajaGiftcard.setUsuarioId(usuario.getId());
				reciboCajaGiftcard.setVendedorId(empleado.getId());
				reciboCajaGiftcard.setValor(BigDecimal.valueOf(totalGiftcard));
				reciboCajaGiftcard.setSaldo(BigDecimal.ZERO);
				manager.persist(reciboCajaGiftcard);
				/*reciboCajaGiftcard.setPreimpreso(String.valueOf(reciboCajaGiftcard.getPrimaryKey()));
				manager.merge(reciboCajaGiftcard);*/

				CarteraDetalleIf detalleReciboCajaGiftcard = new CarteraDetalleEJB();
				detalleReciboCajaGiftcard.setCartera("S");
				detalleReciboCajaGiftcard.setCarteraId(reciboCajaGiftcard.getPrimaryKey());
				parameterMap.put("codigo", "RCVG");
				parameterMap.remove("empresaId");
				it = documentoLocal.findDocumentoByQueryAndEmpresaId(parameterMap, idEmpresa).iterator();
				if (it.hasNext()) {
					DocumentoIf documento = (DocumentoIf) it.next();
					detalleReciboCajaGiftcard.setDocumentoId(documento.getId());
				}
				detalleReciboCajaGiftcard.setFechaCartera(utilitariosLocal.fromTimestampToSqlDate(reciboCajaGiftcard.getFechaEmision()));
				detalleReciboCajaGiftcard.setFechaCreacion(utilitariosLocal.fromTimestampToSqlDate(reciboCajaGiftcard.getFechaEmision()));
				detalleReciboCajaGiftcard.setValor(BigDecimal.valueOf(totalGiftcard));
				detalleReciboCajaGiftcard.setSaldo(BigDecimal.ZERO);
				detalleReciboCajaGiftcard.setSecuencial(1);
				manager.persist(detalleReciboCajaGiftcard);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			return reciboCajaGiftcard;
		}

		private String getNumeroCartera(java.sql.Date fechaCartera, String codigoTipoDocumento, Long idEmpresa, Long idOficina) {
			String codigo = "";
			try {
				EmpresaIf empresa = empresaLocal.getEmpresa(idEmpresa);
				String monthCartera = utilitariosLocal.getMonthFromDate(fechaCartera);
				String anioCartera = utilitariosLocal.getYearFromDate(fechaCartera);
				codigo = empresa.getCodigo() + "-";
				OficinaIf oficina = oficinaLocal.getOficina(idOficina);
				ServidorIf servidor = (oficina.getServidorId() != null) ? servidorLocal.getServidor(oficina.getServidorId()): null;
				if (servidor != null)
					codigo += servidor.getCodigo() + "-";
				codigo += codigoTipoDocumento + "-";
				codigo += monthCartera + "-";
				codigo += anioCartera + "-";
				return codigo;
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			return null;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public java.util.Collection findCarteraByReferenciaId(java.lang.Long referenciaId) {

			String queryString = "from CarteraEJB e where e.referenciaId = :referenciaId ";
			// Add a an order by on all primary keys to assure reproducable results.
			String orderByPart = "";
			orderByPart += " order by e.id";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			query.setParameter("referenciaId", referenciaId);
			return query.getResultList();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public java.util.Collection findCarteraDetalleByCarteraId(java.lang.Long carteraId) {

			String queryString = "from CarteraDetalleEJB e where e.carteraId = :carteraId ";
			// Add a an order by on all primary keys to assure reproducable results.
			String orderByPart = "";
			orderByPart += " order by e.id";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			query.setParameter("carteraId", carteraId);
			return query.getResultList();
		}


		public Map procesarCobros(String codigo,Long idFactura,Vector<Vector> PagosCollection_detalles,Map<String, Object> parametrosEmpresa, Map<String, Object> parametros, Map valoresAfectaDevoluciones) throws GenericBusinessException {
			//aqui se registra la cabecera CARTERA del comprobante de ingreso (pagos)
			Map objectMap = new HashMap();
			CarteraIf carteraComprobanteIngreso = null;
			Long idEmpresa = (Long) parametrosEmpresa.get("EMPRESA_ID");
			Long idOficina = (Long) parametrosEmpresa.get("OFICINA_ID");
			FacturaIf factu = (idFactura!=null)?getFactura(idFactura):null;				
			CarteraData modelCartera = new CarteraData();
			if (factu != null) {
				modelCartera.setReferenciaId(factu.getPrimaryKey());
				nuevaCodificacionActiva = (factu.getFechaFactura().getYear() + 1900 <= 2008) ? false: true;
			}

			String unNumeroCartera = getNumeroCartera((factu!=null)?factu.getFechaFactura():utilitariosLocal.getServerDateTimeStamp(), "CIN", idEmpresa,idOficina);
			modelCartera.setCodigo(unNumeroCartera);
			modelCartera.setCodigo(modelCartera.getCodigo()+ formatoSerial.format(getMaximoNumeroCartera(modelCartera)));

			CarteraEJB cartera_pagos = null;
			if (factu!=null)
				cartera_pagos = registrarCarteraIgualacion(modelCartera, null, factu, true, idOficina,"CIN");
			else
				cartera_pagos = registrarCarteraIgualacion(modelCartera, "CIN", parametros);
			//aqui se registra CARTERA_DETALLE, con los diferentes tipos de pagos 
			List<CarteraDetalleIf> carteraDetalleColeccion = new ArrayList<CarteraDetalleIf>();carteraDetalleColeccion.clear();				
			carteraDetalleColeccion = generarDetalleCobroVector(PagosCollection_detalles, "CIN", cartera_pagos.getValor().doubleValue());
			//actualiza cartera, cartera detalle (factura)
			try {

				Iterator carteraIt;
				Long carteraDetallePrincipal_id=0L;
				double valorFactura = 0D;
				if (factu != null) {
					Map parameterMap = new HashMap();
					parameterMap.put("tipodocumentoId", factu.getTipodocumentoId());
					parameterMap.put("referenciaId", idFactura);
					carteraIt = findCarteraByQuery(parameterMap).iterator();
					if (carteraIt.hasNext())					{
						//actualiza a cero el saldo de la CARTERA generada por la factura NO la cartera generada por los pagos						 
						CarteraIf cartera = (CarteraIf) carteraIt.next();
						cartera.setSaldo(new BigDecimal("0.00"));
						saveCartera(cartera);
						/////////////////////////////////////////						
						Iterator carteraDetalleIt;
						carteraDetalleIt=findCarteraDetalleByCarteraId(cartera.getId()).iterator();
						if (carteraDetalleIt.hasNext()){
							//aqui se actualiza EL saldo de la CARTERA_DETALLE(solo un detalle)
							CarteraDetalleIf detalle = (CarteraDetalleIf) carteraDetalleIt.next();
							detalle.setSaldo(new BigDecimal("0.00"));
							saveCarteraDetalle(detalle);							
							//guardo del id de la CARTERA detalle para usarlo en CARTERA_AFECTA
							carteraDetallePrincipal_id = detalle.getId();
							valorFactura = detalle.getValor().doubleValue();
						}
					}
				}

				//grabo los datos de: id cartera genarada por factura, CABECERA de pagos, DETALLES cartera pero de pagos, Y CARTERA AFECTA 
				//aqui revisar si uso como pago CREDITO CLIENTE! entonces actualizo!
				Map<String, Object> resultMap = procesarCartera(carteraDetallePrincipal_id, valorFactura, cartera_pagos, carteraDetalleColeccion, idEmpresa, valoresAfectaDevoluciones);
				objectMap.put("CARTERA", (CarteraIf) resultMap.get("CARTERA"));
				objectMap.put("CARTERA_DETALLE", (List<CarteraDetalleIf>) resultMap.get("DETALLES"));
				objectMap.put("FACTURA", factu);
				objectMap.put("VALORES_AFECTA_MAP", (Map) resultMap.get("VALORES_AFECTA_MAP")); 
			} catch (Exception e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(e.getMessage());
			}

			return objectMap;
		}
		//AFILIACION
		//Long clienteOficinaId--> clienteoficina dueño de la factura
		public void saveTarjetaTransacciones(Vector<Vector> TarjetasCollection_detalles,Long idFactura,Long clienteOficinaId, String apd, String atptt, Long idEmpresa) throws GenericBusinessException {

			try {
				System.out.println("TarjetasCollection_detalles.size() >>>>>>>>>>> " + TarjetasCollection_detalles.size());
				if(TarjetasCollection_detalles.size()>0)  
				{	
					System.out.println("In 1/3 >>>>>>>>>>> ");
					String sumaPuntosDineroGanado = ((Vector) TarjetasCollection_detalles.get(0)).get(6).toString();					
					String identificacionDuenoTarjeta = ((Vector) TarjetasCollection_detalles.get(0)).get(2).toString();					
					Iterator clienteOficinaDuenoTarjetaIt;								
					//clienteOficinaDuenoTarjetaIt = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByEmail(emailDuenoTarjeta).iterator();
					clienteOficinaDuenoTarjetaIt = clienteOficinaSessionLocal.findClienteOficinaByIdentificacionAndEmpresaId(identificacionDuenoTarjeta, idEmpresa).iterator();
					if (clienteOficinaDuenoTarjetaIt.hasNext()) {
						System.out.println("In 2/3 >>>>>>>>>>> ");
						ClienteOficinaIf clienteOficTarjetaIf;
						clienteOficTarjetaIf = (ClienteOficinaIf) clienteOficinaDuenoTarjetaIt.next();
						Iterator tarjetaIt=tarjetaSessionLocal.findTarjetaByClienteoficinaId(clienteOficTarjetaIf.getId()).iterator();
						while(tarjetaIt.hasNext()){
							System.out.println("In 3/3 >>>>>>>>>>> ");
							TarjetaIf tarjeta;
							tarjeta= (TarjetaIf) tarjetaIt.next();
							Long tarjetaId=tarjeta.getId();
							BigDecimal puntosDineroAcumulado = (apd.equals("D"))?tarjeta.getDineroAcumulado():tarjeta.getPuntosAcumulados();
							puntosDineroAcumulado = puntosDineroAcumulado.add(new BigDecimal(sumaPuntosDineroGanado));										
							BigDecimal puntosDineroUtilizado = new BigDecimal(((Vector) TarjetasCollection_detalles.get(0)).get(5).toString());
							System.out.println(">>>>>>> PUNTOS DINERO UTILIZADO >>>>>>>> " + String.valueOf(puntosDineroUtilizado.doubleValue()));
							BigDecimal puntosDineroUtilizadoAntes = (apd.equals("D"))?tarjeta.getDineroUtilizado():tarjeta.getPuntosUtilizados();
							if (apd.equals("D")) {
								tarjeta.setDineroUtilizado(puntosDineroUtilizadoAntes.add(puntosDineroUtilizado));
								System.out.println(">>>>>>> DINERO UTILIZADO >>>>>>>> " + String.valueOf(tarjeta.getDineroUtilizado().doubleValue()));
								tarjeta.setDineroAcumulado(puntosDineroAcumulado);
								System.out.println(">>>>>>> DINERO ACUMULADO >>>>>>>> " + String.valueOf(tarjeta.getDineroAcumulado().doubleValue()));
								tarjeta.setDineroAcumuladoStatus(puntosDineroAcumulado);
								System.out.println(">>>>>>> DINERO ACUMULADO STATUS >>>>>>>> " + String.valueOf(tarjeta.getDineroAcumuladoStatus().doubleValue()));
							} else {
								tarjeta.setPuntosUtilizados(puntosDineroUtilizadoAntes.add(puntosDineroUtilizado));
								System.out.println(">>>>>>> PUNTOS UTILIZADOS >>>>>>>> " + String.valueOf(tarjeta.getPuntosUtilizados().doubleValue()));
								tarjeta.setPuntosAcumulados(puntosDineroAcumulado);
								System.out.println(">>>>>>> PUNTOS ACUMULADOS >>>>>>>> " + String.valueOf(tarjeta.getPuntosAcumulados().doubleValue()));
								tarjeta.setPuntosAcumuladosStatus(puntosDineroAcumulado);
								System.out.println(">>>>>>> PUNTOS ACUMULADOS STATUS >>>>>>>> " + String.valueOf(tarjeta.getPuntosAcumuladosStatus().doubleValue()));
							}

							tarjetaSessionLocal.saveTarjeta(tarjeta);

							//grabar tarjeta transacciones
							TarjetaTransaccionEJB tranx= null;							 						
							tranx= registrarTarjetaTx(TarjetasCollection_detalles,tarjetaId, sumaPuntosDineroGanado, puntosDineroUtilizado, idFactura,clienteOficinaId);
							tarjetaTransaccionSessionLocal.saveTarjetaTransaccion(tranx);

						}
					}
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				throw new GenericBusinessException(e.getMessage());
			}

		}

		//AFILIACION  
		public TarjetaTransaccionEJB registrarTarjetaTx(Vector<Vector> TarjetasCollection_detalles,Long tarjetaId,String sumaPuntosGanados,BigDecimal puntosUtilizados,Long idFactura,Long clienteOficinaId){
			TarjetaTransaccionEJB tranx = new TarjetaTransaccionEJB();
			tranx.setTarjetaId(tarjetaId);
			String referido=((Vector) TarjetasCollection_detalles.get(0)).get(3).toString();
			tranx.setReferido(referido);	
			//AQUI GUARDA EL DUEÑO DE LA TRANSACCION, no importa si es referido o no...	//si referido es N--> referidoPor esta el dueño de la tarjeta
			tranx.setReferidoPor(clienteOficinaId);
			tranx.setPuntosGanados(new BigDecimal(sumaPuntosGanados.toString()));
			tranx.setPuntosUtilizados(puntosUtilizados);
			tranx.setFacturaId(idFactura);

			return tranx;
		}

		//AFILIACION
		public Vector<String> propietarioAfiliado(String nombreDuenoTarjeta,String identificacionDuenoTarjeta,Long empresaId) throws GenericBusinessException {
			Vector<String> datosDetalles= new Vector<String>();
			datosDetalles.clear();

			try {	 
				BigDecimal acum=BigDecimal.ZERO;
				BigDecimal util=BigDecimal.ZERO;
				BigDecimal comp=BigDecimal.ZERO;

				Long idClienteOficinaTransaccion=0L;
				ClienteOficinaIf clienteOficinaIf=null;
				Collection<ClienteOficinaIf> oficinasFactura = clienteOficinaSessionLocal.findClienteOficinaByIdentificacionAndEmpresaId(identificacionDuenoTarjeta, empresaId);
				if (oficinasFactura.size() > 0) {
					clienteOficinaIf = oficinasFactura.iterator().next();
					idClienteOficinaTransaccion = clienteOficinaIf.getId();
				}

				ClienteIf clienteTransaccion = clienteSessionLocal.getCliente(clienteOficinaIf.getClienteId());

				//Iterator tarjetaIt=tarjetaSessionLocal.findTarjetaByClienteoficinaId(idClienteOficinaTransaccion).iterator();
				TarjetaIf tarjeta = tarjetaSessionLocal.findTarjetaWebService(clienteTransaccion.getIdentificacion(), clienteOficinaIf.getCodigo(), empresaId);
				ParametroEmpresaIf acumularPuntosDinero = null;
				if(tarjeta != null) {
					datosDetalles.add(0,"S");//SI ES PROPIETARIO
					datosDetalles.add(1,nombreDuenoTarjeta);
					datosDetalles.add(2,identificacionDuenoTarjeta);
					datosDetalles.add(3,"");//tipoDuenoTarjeta
					datosDetalles.add(4,"");//puntosdinero
					datosDetalles.add(5,"");//referido s o n
					datosDetalles.add(6,"");//acum
					datosDetalles.add(7,"");//util
					datosDetalles.add(8,"");//comprom
					datosDetalles.add(9,"0");//dscto tipo

					Map parameterMap = new HashMap();
					parameterMap.put("empresaId", empresaId);
					parameterMap.put("codigo", "APD");
					Iterator it = parametroEmpresaSessionLocal.findParametroEmpresaByQuery(parameterMap).iterator();
					if (it.hasNext())
						acumularPuntosDinero = (ParametroEmpresaIf) it.next();

					if (acumularPuntosDinero != null) {
						if (acumularPuntosDinero.getValor().equals("P")) {
							if(tarjeta.getPuntosAcumulados()!=null)
								acum=tarjeta.getPuntosAcumulados();
							if(tarjeta.getPuntosUtilizados()!=null)
								util=tarjeta.getPuntosUtilizados();
							if(tarjeta.getPuntosComprometidos()!=null)
								comp=tarjeta.getPuntosComprometidos();
						} else {
							if (tarjeta.getDineroAcumulado()!=null)
								acum=tarjeta.getDineroAcumulado();
							if (tarjeta.getDineroUtilizado()!=null)
								util=tarjeta.getDineroUtilizado();
							if (tarjeta.getDineroComprometido()!=null)
								comp=tarjeta.getDineroComprometido();
						}
					}


					Long tarjetaTipoId=tarjeta.getTipoId();
					Iterator tarjetaTipoIt=tarjetaTipoSessionLocal.findTarjetaTipoById(new Long(tarjetaTipoId)).iterator();
					if (tarjetaTipoIt.hasNext()) {
						TarjetaTipoIf tarjetatipo = (TarjetaTipoIf) tarjetaTipoIt.next();
						String tipoDuenoTarjeta ="";
						tipoDuenoTarjeta=tarjetaTipoId.toString();								
						datosDetalles.add(3,tipoDuenoTarjeta);
						String puntosdinero="0/0";
						if(tarjetatipo.getPuntosDinero()!=null) puntosdinero=tarjetatipo.getPuntosDinero();
						datosDetalles.add(4,puntosdinero);
						datosDetalles.add(5,"S");	
						datosDetalles.add(6,acum.toString());
						datosDetalles.add(7,util.toString());
						datosDetalles.add(8,comp.toString());
						datosDetalles.add(9,tarjetatipo.getDsctoPropietario().toString());
					}
				} else {
					datosDetalles.add(0,"N");//no ES PROPIETARIO					 
				}			 
			} catch (GenericBusinessException e1) {
				e1.printStackTrace();
				throw new GenericBusinessException(e1.getMessage());
			}
			return datosDetalles;
		}

		//AFILIACION
		public Vector<String> referirAfiliado(String identificacionFactura,String identificacionDuenoTarjeta,Long empresaId){
			Vector<String> datosDetalles= new Vector<String>();
			try {			
				//Verificamos si tiene tarjeta	el dueño de la transacción
				ClienteOficinaIf clienteOficinaIf;
				clienteOficinaIf = null;
				Long idClienteOficinaTransaccion=0L;
				TarjetaIf tarjeta = null;
				if (identificacionFactura!=null && !identificacionFactura.trim().equals("")) {
					Collection<ClienteOficinaIf> oficinasFactura = clienteOficinaSessionLocal.findClienteOficinaByIdentificacionAndEmpresaId(identificacionFactura, empresaId);			 
					if (oficinasFactura.size() > 0) {
						clienteOficinaIf = oficinasFactura.iterator().next();
						idClienteOficinaTransaccion = clienteOficinaIf.getId();
					}
					//emailFactura
					ClienteIf clienteTransaccion = clienteSessionLocal.getCliente(clienteOficinaIf.getClienteId());

					//Iterator tarjetaIt=tarjetaSessionLocal.findTarjetaByClienteoficinaId(idClienteOficinaTransaccion).iterator();
					tarjeta = tarjetaSessionLocal.findTarjetaWebService(clienteTransaccion.getIdentificacion(), clienteOficinaIf.getCodigo(), empresaId);
				}
				if(tarjeta != null){
					datosDetalles.add(0,"N");//El cliente posee tarjeta de afiliación. Debe usar la opción Propietario no puede Referir
					datosDetalles.add(1,"");//nombre del cliente
					datosDetalles.add(2,"");//tipoDueñoTarjeta
					datosDetalles.add(3,"");//dsctotarjetatipo
				}	
				else{
					//email= el que escribió en el cuadro de texto
					Collection<ClienteOficinaIf> oficinas = clienteOficinaSessionLocal.findClienteOficinaByIdentificacionAndEmpresaId(identificacionDuenoTarjeta, empresaId);
					if (oficinas.size() > 0) {
						clienteOficinaIf = oficinas.iterator().next();
						Long idClienteOficina = clienteOficinaIf.getId();
						Collection<ClienteIf> cliente= clienteSessionLocal.findClienteByClienteOficinaId(idClienteOficina);
						ClienteIf clienteIf = null;
						System.out.println(">>>> ID CLIENTE OFICINA : " + String.valueOf(idClienteOficina));
						if (cliente.size() > 0) {
							System.out.println(">>>>>>>>>>>> SE ENCONTRO CLIENTE >>>>>>>>>>>");
							clienteIf = cliente.iterator().next();
							String clienteNombre="";
							if(clienteIf.getNombreLegal()!=null) {
								clienteNombre=clienteIf.getNombreLegal();
								datosDetalles.add(0, "S");
								datosDetalles.add(1, clienteNombre);
								datosDetalles.add(2,"");//tipoDueñoTarjeta
								datosDetalles.add(3,"0");//dsctotarjetatipos
							}
						} else {
							System.out.println(">>>>>>>>>>>> NO SE ENCONTRO CLIENTE >>>>>>>>>>>");
						}

						//Collection<TarjetaIf> tarjetaIf =tarjetaSessionLocal.findTarjetaByClienteoficinaId(idClienteOficina);
						//if(tarjetaIf.size()>0)
						tarjeta = tarjetaSessionLocal.findTarjetaWebService(
								clienteIf.getIdentificacion(), clienteOficinaIf.getCodigo(), empresaId);
						if(tarjeta != null)
						{
							//TarjetaIf tar = tarjetaIf.iterator().next();
							Long idTarjetaIf=tarjeta.getId();							
							Long tipotar=tarjeta.getTipoId();
							String tipoDuenoTarjeta ="";
							tipoDuenoTarjeta=tipotar.toString();
							datosDetalles.add(2,tipoDuenoTarjeta);							
							datosDetalles.add(3,"0");//dsctotarjetatipo
							//AQUI SE OBTIENE EL TIPO DE TARJETA Y SE BUSCA EL %DE DSCTO EN ESTE CASO AL USARLA UN REFERIDO
							Iterator tarjetaTipoIt=tarjetaTipoSessionLocal.findTarjetaTipoById(new Long(tipotar)).iterator();
							if(tarjetaTipoIt.hasNext()){
								TarjetaTipoIf tarjetatipo;
								tarjetatipo= (TarjetaTipoIf) tarjetaTipoIt.next();
								BigDecimal dsctoTipoTarjeta =new BigDecimal("0");
								dsctoTipoTarjeta=tarjetatipo.getDsctoReferido();
								datosDetalles.add(3,dsctoTipoTarjeta.toString());
							}
						}


					}
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			return datosDetalles;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Map generarFacturaPOS(
				Vector<PedidoDetalleIf> giftcardVector,
				Vector<TarjetaIf> tarjetaAfiliacionVector,
				Vector<PedidoDetalleIf> pedidoDetalleFacturaVector,
				Vector<PedidoDetalleIf> pedidoDetalleNotaVentaVector,
				Vector<PedidoDetalleIf> pedidoDetallePersonalizacionVector,
				VentasTrackIf ventasTrack, 
				Vector<Vector> pagoDetalleVector,
				String donacion, 
				String idfundacion, 
				PedidoIf pedidoAnterior,
				Vector<PedidoDetalleIf> pedidoDetalleEliminadosVector,
				Vector<PedidoDetalleIf> pedidoDetalleProcesoVector,
				Vector<Vector> tarjetasCollectionDetalles,
				Map<String,Object> parametros,
				boolean isMatriz) throws GenericBusinessException {

			// Contenido de mapa de parámetros
			EmpleadoIf empleado = (EmpleadoIf) parametros.get("EMPLEADO");
			ClienteIf cliente = (ClienteIf) parametros.get("CLIENTE");
			ClienteOficinaIf clienteOficina = (ClienteOficinaIf) parametros.get("CLIENTE_OFICINA");
			PuntoImpresionIf puntoImpresion = (PuntoImpresionIf) parametros.get("PUNTO_IMPRESION");
			Long idOficina = (Long) parametros.get("OFICINA_ID");
			Long idEmpresa = (Long) parametros.get("EMPRESA_ID");
			UsuarioIf usuario = (UsuarioIf) parametros.get("USUARIO");
			double porcentajeIva = ((Double) parametros.get("PORCENTAJE_IVA")).doubleValue();
			BodegaIf bodega = (BodegaIf) parametros.get("BODEGA");
			String apd = (String) parametros.get("APD");
			String atptt = (String) parametros.get("ATPTT");
			java.sql.Timestamp fechaFactura = (java.sql.Timestamp) parametros.get("FECHA_FACTURA");

			Map<String, Object> parametrosEmpresa = new HashMap<String, Object>();
			parametrosEmpresa.put("EMPRESA_ID", idEmpresa);
			parametrosEmpresa.put("OFICINA_ID", idOficina);
			parametrosEmpresa.put("PORCENTAJE_IVA", porcentajeIva);
			Long idFactura = 0L;
			Long idReciboCaja = 0L;
			Map valoresAfectaDevoluciones = new HashMap();
			java.sql.Timestamp now = new java.sql.Timestamp(utilitariosLocal.getServerDateSql().getTime());

			try {
				Long idprincipal = 0L;			
				if (pedidoDetalleFacturaVector.size() > 0 || giftcardVector.size() > 0 || pedidoDetalleNotaVentaVector.size() > 0) {
					try {
						// 1) REGISTRAMOS EN VENTAS TRACK
						if (!isMatriz)
							idprincipal = ventastrackLocal.procesarVentasTrack(ventasTrack);
						else {
							ventasTrack.setCajasesionId(new Long("0"));
							idprincipal = ventastrackLocal.procesarVentasTrack(ventasTrack);
						}
					} catch (GenericBusinessException e1) {
						e1.printStackTrace();
						throw new GenericBusinessException(e1.getMessage());
					}
				}	 

				PedidoData pedido = new PedidoData();

				//-----------------------------------------

				String codigo = getCodigoPedido(now);
				pedido.setCodigo(codigo);
				if (fechaFactura==null)
					pedido.setFechaPedido(now);
				else
					pedido.setFechaPedido(fechaFactura);

				pedido.setFechaCreacion(now);
				pedido.setOficinaId(idOficina);
				pedido.setClienteoficinaId(clienteOficina.getId());
				pedido.setTipoidentificacionId(cliente.getTipoidentificacionId());

				Map parameterMap = new HashMap();
				parameterMap.put("empresaId", idEmpresa);
				parameterMap.put("codigo", OTRAS_FORMAS_PAGO);
				Iterator it = formaPagoLocal.findFormaPagoByQuery(parameterMap).iterator();
				if (it.hasNext()) {
					FormaPagoIf formaPago = (FormaPagoIf) it.next();
					pedido.setFormapagoId(formaPago.getId());
				}
				it = monedaLocal.findMonedaByCodigo(MONEDA_DOLAR).iterator();
				if (it.hasNext()) {
					MonedaIf moneda = (MonedaIf) it.next();
					pedido.setMonedaId(moneda.getId());
					parametros.put("MONEDA", moneda);
				}
				if (puntoImpresion != null)
					pedido.setPuntoimpresionId(puntoImpresion.getId());


				//parameterMap.put("codigo", "CLI");
				parameterMap.remove("codigo");
				parameterMap.put("oficinaId", idOficina);
				it = origenDocumentoLocal.findOrigenDocumentoByQuery(parameterMap).iterator();
				if (it.hasNext()) {
					OrigenDocumentoIf origenDocumento = (OrigenDocumentoIf) it.next();
					pedido.setOrigendocumentoId(origenDocumento.getId());
				}
				if(empleado!=null)
					pedido.setVendedorId(empleado.getId());

				pedido.setBodegaId(getIdBodega_POS(idOficina));
				pedido.setListaprecioId(getIdListaPrecio_POS(idEmpresa));
				pedido.setUsuarioId(usuario.getId());
				pedido.setDiasvalidez(1);
				pedido.setTiporeferencia("N");
				pedido.setReferencia("");
				pedido.setEstado("P");
				pedido.setDireccion(clienteOficina.getDireccion());
				pedido.setIdentificacion(cliente.getIdentificacion());
				pedido.setTelefono(clienteOficina.getTelefono());
				pedido.setContacto("");
				pedido.setPorcentajeComisionAgencia(BigDecimal.ZERO);

				//-----------------------------------------

				CarteraIf carteraComprobanteIngreso = null;
				Map objectMap = new HashMap();

				//GUARDAR DATOS DE FACTURA			
				if (pedidoDetalleFacturaVector.size() > 0 || (giftcardVector.size() > 0 && pedidoDetalleNotaVentaVector.size() <= 0)) {
					parameterMap = new HashMap();
					parameterMap.put("empresaId", idEmpresa);
					parameterMap.put("codigo", "FAC");
					it = tipoDocumentoLocal.findTipoDocumentoByQuery(parameterMap).iterator();
					if (it.hasNext()) {
						TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) it.next();
						pedido.setTipodocumentoId(tipoDocumento.getId());
					}
					pedido.setObservacion("F/ " + cliente.getRazonSocial());

					if (pedidoDetalleFacturaVector.size() > 0) {
						idFactura = savePagoPedidoFactura(idprincipal, pedidoDetalleFacturaVector, pedidoAnterior, pedidoDetalleProcesoVector, pedidoDetalleEliminadosVector, pedido, idEmpresa, usuario,isMatriz);
						//grabamos datos en tarjeta_Transaccion(ya con el id de la factura generada en este momento)
						//actualizamos datos en tarjetaid (acumulados, utilizados)
						// TOMAMOS EL ID DE VENTAS TRACK Y GRABAMOS EL DOCUMENTO QUE GUARDO	// : FACTURA
						VentasDocumentosIf pagosDocumentos = registrarVentasDocumentos(idprincipal, idFactura, "FACTURA");
						// TOMAMOS EL ID DE VENTAS TRACK Y GRABAMOS EL tipo de pago y valor
						ventasDocumentosLocal.saveVentasDocumentos(pagosDocumentos);
					}
					objectMap = procesarCobros("FAC",idFactura,pagoDetalleVector,parametrosEmpresa,parametros,valoresAfectaDevoluciones);
				} else if (pedidoDetalleNotaVentaVector.size() > 0 || (giftcardVector.size() > 0 && pedidoDetalleFacturaVector.size() <= 0)) {
					parameterMap = new HashMap();
					parameterMap.put("empresaId", idEmpresa);
					parameterMap.put("codigo", "VTA");
					it = tipoDocumentoLocal.findTipoDocumentoByQuery(parameterMap).iterator();
					if (it.hasNext()) {
						TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) it.next();
						pedido.setTipodocumentoId(tipoDocumento.getId());
					}
					pedido.setObservacion("NV/ " + cliente.getRazonSocial());
					if (pedidoDetalleNotaVentaVector.size() > 0) {
						idFactura=savePagoPedidoFactura(idprincipal, pedidoDetalleNotaVentaVector, pedidoAnterior, pedidoDetalleProcesoVector, pedidoDetalleEliminadosVector, pedido, idEmpresa, usuario,isMatriz);
						// TOMAMOS EL ID DE VENTAS TRACK Y GRABAMOS EL DOCUMENTO QUE GUARDO	// : FACTURA
						VentasDocumentosIf pagosDocumentos = registrarVentasDocumentos(idprincipal, idFactura, "FACTURA");
						// TOMAMOS EL ID DE VENTAS TRACK Y GRABAMOS EL tipo de pago y valor
						ventasDocumentosLocal.saveVentasDocumentos(pagosDocumentos);
					}
					objectMap = procesarCobros("VTA",idFactura,pagoDetalleVector,parametrosEmpresa,parametros,valoresAfectaDevoluciones);
				}

				CarteraEJB reciboCajaGiftcard = null;
				carteraComprobanteIngreso = (CarteraIf) objectMap.get("CARTERA");
				List<CarteraDetalleIf> carteraDetalleColeccion = (List<CarteraDetalleIf>) objectMap.get("CARTERA_DETALLE");
				valoresAfectaDevoluciones = (Map) objectMap.get("VALORES_AFECTA_MAP");
				List<CarteraDetalleIf> creditosDetalleColeccion = obtenerCreditosDetalleColeccion(carteraDetalleColeccion);
				FacturaIf factura = (FacturaIf) objectMap.get("FACTURA");

				if(giftcardVector.size()>0) {
					reciboCajaGiftcard = procesarVentasGift(idprincipal,giftcardVector,clienteOficina.getId(),empleado,idOficina,idEmpresa,usuario);
					idReciboCaja = reciboCajaGiftcard.getPrimaryKey();
					Iterator itDetalleReciboCajaGiftcard = carteraDetalleLocal.findCarteraDetalleByCarteraId(reciboCajaGiftcard.getPrimaryKey()).iterator();
					CarteraDetalleIf detalleReciboCajaGiftcard = null;
					if (itDetalleReciboCajaGiftcard.hasNext())
						detalleReciboCajaGiftcard = (CarteraDetalleIf) itDetalleReciboCajaGiftcard.next();
					List<CarteraDetalleIf> listDetallesComprobanteIngreso = (List<CarteraDetalleIf>) carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraComprobanteIngreso.getPrimaryKey());
					Iterator itDetalleComprobanteIngreso = listDetallesComprobanteIngreso.iterator();
					carteraDetalleColeccion = new ArrayList<CarteraDetalleIf>();
					if (creditosDetalleColeccion.size() > 0) {
						listDetallesComprobanteIngreso.addAll(creditosDetalleColeccion);
						itDetalleComprobanteIngreso = listDetallesComprobanteIngreso.iterator();
					}

					double valorReciboCaja = detalleReciboCajaGiftcard.getValor().doubleValue();
					//while (itDetalleComprobanteIngreso.hasNext() && valorReciboCaja > 0D) {
					while (itDetalleComprobanteIngreso.hasNext()) {
						CarteraDetalleIf detalleComprobanteIngreso = (CarteraDetalleIf) itDetalleComprobanteIngreso.next();
						if (valorReciboCaja > 0D) {
							double valorAfecta = valorReciboCaja;
							if (valorAfecta > detalleComprobanteIngreso.getSaldo().doubleValue()) {
								valorAfecta = detalleComprobanteIngreso.getSaldo().doubleValue();
								valorReciboCaja -= valorAfecta;
							} else {
								valorReciboCaja = 0D;
							}
							double valorAfectaMap = (valoresAfectaDevoluciones.get(detalleComprobanteIngreso.getId()) == null)?0D:((Double) valoresAfectaDevoluciones.get(detalleComprobanteIngreso.getId())).doubleValue();
							valoresAfectaDevoluciones.put(detalleComprobanteIngreso.getId(), (valorAfecta + valorAfectaMap));
							if (detalleComprobanteIngreso.getSaldo().doubleValue() > 0D && valorAfecta > 0D) {
								CarteraAfectaIf modelCarteraAfecta = new CarteraAfectaEJB();
								modelCarteraAfecta.setCarteradetalleId(detalleComprobanteIngreso.getPrimaryKey());
								modelCarteraAfecta.setCarteraafectaId(detalleReciboCajaGiftcard.getPrimaryKey());
								modelCarteraAfecta.setUsuarioId(reciboCajaGiftcard.getUsuarioId());
								//modelCarteraAfecta.setValor(utilitariosLocal.redondeoValor(detalleComprobanteIngreso.getSaldo()));
								modelCarteraAfecta.setValor(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorAfecta)));
								modelCarteraAfecta.setFechaCreacion(detalleComprobanteIngreso.getFechaCreacion());
								modelCarteraAfecta.setFechaAplicacion(detalleComprobanteIngreso.getFechaCartera());
								modelCarteraAfecta.setCartera(detalleComprobanteIngreso.getCartera());
								CarteraAfectaEJB carteraAfecta = registrarCarteraAfecta(modelCarteraAfecta);
								manager.merge(carteraAfecta);
								detalleComprobanteIngreso.setSaldo(detalleComprobanteIngreso.getSaldo().subtract(BigDecimal.valueOf(valorAfecta)));
								manager.merge(detalleComprobanteIngreso);
								CarteraIf comprobanteIngreso = (CarteraIf) findCarteraById(detalleComprobanteIngreso.getCarteraId()).iterator().next();
								TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(comprobanteIngreso.getTipodocumentoId());
								if (tipoDocumento.getCodigo().equals(DEVOLUCION))
									comprobanteIngreso.setSaldo(comprobanteIngreso.getSaldo().subtract(BigDecimal.valueOf(valorAfecta)));
								else
									comprobanteIngreso.setSaldo(BigDecimal.ZERO);
								manager.merge(comprobanteIngreso);
							}
						}
						carteraDetalleColeccion.add(detalleComprobanteIngreso);
					}

					movimientoSessionLocal.procesarMovimientoVentaGiftcard(reciboCajaGiftcard, giftcardVector, usuario, bodega);
				}

				if (tarjetaAfiliacionVector.size() > 0) {
					System.out.println(">>>>>>>>>>>>>>>>PROCESANDO VENTA TARJETAS AFILIACION>>>>>>>>>>>>>>>>>>>><<<<<<<");
					procesarVentasTarjetaAfiliacion(tarjetaAfiliacionVector, clienteOficina.getId(), idEmpresa);
				}

				double totalGiftcard = (reciboCajaGiftcard != null)?reciboCajaGiftcard.getValor().doubleValue():0D;
				AsientoIf asiento = generarAsientosAutomaticos(carteraComprobanteIngreso, carteraDetalleColeccion, parametrosEmpresa, false, factura, totalGiftcard, valoresAfectaDevoluciones);

				// EN LA PARTE SUPERIOR SE PROCESARON LOS PRODUCTOS O ITEMS DEL POS
				// Y EN CADA FUNCION SE GRABA VENTAS DOCUMENTOS
				// AHORA PROCESAMOS LAS FORMAS DE PAGO.. VENTAS_PAGOS para mostrar estos datos en el arqueo y cierre de caja
				try {
					Vector<VentasPagosIf> ventasPagosVarios = new Vector<VentasPagosIf>();
					ventasPagosVarios.clear();

					if (pagoDetalleVector.size() != 0) {
						for (int l = 0; l < pagoDetalleVector.size(); l++) {

							String total_pago = ((Vector) pagoDetalleVector.get(l)).get(1).toString();
							String nombre_tc = ((Vector) pagoDetalleVector.get(l)).get(2).toString();
							String tipoPago_tc = ((Vector) pagoDetalleVector.get(l)).get(3).toString();
							String tipo_pago = ((Vector) pagoDetalleVector.get(l)).get(4).toString();

							VentasPagosData movipos = new VentasPagosData();
							movipos.setVentastrackId(idprincipal);
							movipos.setReferencia(nombre_tc + "/" + tipoPago_tc+ "");// egresos
							movipos.setTipo(buscarTipoPagoByCodigo(tipo_pago).getId());// en ingresos	// caja siempre
							movipos.setValor(new BigDecimal(total_pago));			

							if (isMatriz) {
								if (tipo_pago.equals(buscarTipoPagoByCodigo(TIPO_PAGO_GIFTCARD).getCodigo())) {
									// ACTUALIZO EL SALDO_FINAL EN EL GIFT MOVIMIENTO
									String saldo_ini = ((Vector) pagoDetalleVector.get(l)).get(5).toString();
									String saldo_fin = ((Vector) pagoDetalleVector.get(l)).get(6).toString();
									String giftcard_id = ((Vector) pagoDetalleVector.get(l)).get(7).toString();
									String cod_gc = ((Vector) pagoDetalleVector.get(l)).get(0).toString();
									int posicion = cod_gc.indexOf("(Código:");
									if (posicion != -1) {
										String substring = cod_gc.substring(posicion);
										if (substring == null)
											substring = "";
										movipos.setReferencia(substring + "/"+ nombre_tc + "/" + tipoPago_tc + "");// egresos
									}

									GiftcardIf giftcard = giftcardLocal.getGiftcard(Long.parseLong(giftcard_id));
									giftcard.setSaldo(BigDecimal.valueOf(Double.parseDouble(saldo_fin)));
									giftcardLocal.saveGiftcard(giftcard);
									GiftcardMovimientoIf giftcardMovimiento = null;
									giftcardMovimiento = registrarMovimientoGiftcard(new BigDecimal(saldo_ini), new BigDecimal(saldo_ini).subtract(new BigDecimal(saldo_fin)), new Long(giftcard_id), carteraComprobanteIngreso.getId(), carteraComprobanteIngreso.getTipodocumentoId());
									giftcardMovimientoLocal.saveGiftcardMovimiento(giftcardMovimiento);
								}
							}
							//si es pago ocn tarjeta de crédito guardo datos en t:PAGO_TARJETA y guardo la referencia
							if (tipo_pago.equals(buscarTipoPagoByCodigo(TIPO_PAGO_TARJETA_CREDITO).getCodigo())) {
								Long idpagotarjeta=0L;			
								String nombre_cl = ((Vector) pagoDetalleVector.get(l)).get(5).toString();
								String cedula_cl = ((Vector) pagoDetalleVector.get(l)).get(6).toString();
								String telefono_cl = ((Vector) pagoDetalleVector.get(l)).get(7).toString();
								String cod_autoriza = ((Vector) pagoDetalleVector.get(l)).get(3).toString();
								String nombre_tarjeta= ((Vector) pagoDetalleVector.get(l)).get(2).toString();
								String valor_tarjeta= ((Vector) pagoDetalleVector.get(l)).get(1).toString();
								String no_referencia= ((Vector) pagoDetalleVector.get(l)).get(8).toString();
								String noVoucher="";
								int posicion = cod_autoriza.indexOf("/");
								if (posicion != -1) {
									String substring = cod_autoriza.substring(posicion+1,cod_autoriza.length());
									cod_autoriza = cod_autoriza.substring(0, posicion);
									noVoucher = (substring!=null)?substring:"";
								}				

								PagoTarjetaIf pagotarjetaData = registrarPagoTarjetaCredito(nombre_cl,telefono_cl,cedula_cl,cod_autoriza,noVoucher,nombre_tarjeta,valor_tarjeta,no_referencia);
								idpagotarjeta=PagoTarjetaLocal.procesarPagoTarjetaCredito(pagotarjetaData);
								movipos.setReferenciaId(idpagotarjeta);
								movipos.setNumDoc(no_referencia);
							}
							if (tipo_pago.equals(buscarTipoPagoByCodigo(TIPO_PAGO_CHEQUE).getCodigo())) {
								String noCtaCheque = ((Vector) pagoDetalleVector.get(l)).get(7).toString();							
								movipos.setNumDoc(noCtaCheque);
							}

							ventasPagosVarios.add(movipos);
						}
					}
					// GUARDAR donacion en pagos
					double donacionTransaccion = Double.parseDouble(donacion);
					if (donacionTransaccion != 0D) {
						VentasPagosData movipos = new VentasPagosData();
						movipos.setVentastrackId(idprincipal);
						movipos.setReferencia("DONACION");
						movipos.setReferenciaId(new Long(idfundacion));
						movipos.setTipo(buscarTipoPagoByCodigo(TIPO_PAGO_DONACION).getId());					
						movipos.setValor(BigDecimal.valueOf(donacionTransaccion));
						ventasPagosVarios.add(movipos);
					}

					if (ventasPagosVarios.size() > 0)
						ventasPagosLocal.procesarVentasPagosVarios(ventasPagosVarios);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					throw new GenericBusinessException(e.getMessage());
				}
			} catch (Exception e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException("Se ha producido un error al grabar la Venta");
			}

			System.out.println(">>>>>>>>>>>----------------------------------------------------------------<<<<<<<<<<<<<<<<<<<");
			System.out.println(">>>>>>>>>>>------------- Tamaño tarjetas colección detalles = " + tarjetasCollectionDetalles.size() + "------------------<<<<<<<<<<<<<<<<<<<");
			System.out.println(">>>>>>>>>>>----------------------------------------------------------------<<<<<<<<<<<<<<<<<<<");

			if(isMatriz && tarjetasCollectionDetalles.size()>0) {
				//if(tarjetasCollectionDetalles.size()>0)
				System.out.println(">>>>>>>>>>>----------------------------------------------------------------<<<<<<<<<<<<<<<<<<<");
				System.out.println(">>>>>>>>>>>------------- Guardando TARJETA TRANSACCIONES ------------------<<<<<<<<<<<<<<<<<<<");
				System.out.println(">>>>>>>>>>>----------------------------------------------------------------<<<<<<<<<<<<<<<<<<<");
				saveTarjetaTransacciones(tarjetasCollectionDetalles, idFactura, clienteOficina.getId(), apd, atptt, idEmpresa);
			}


			if(!isMatriz){
				parametros.put("FECHA_FACTURA", now);
				facturaPosMessageLocal.setData(giftcardVector, 
						tarjetaAfiliacionVector,
						pedidoDetalleFacturaVector, 
						pedidoDetalleNotaVentaVector, 
						pedidoDetallePersonalizacionVector, 
						ventasTrack,
						pagoDetalleVector, 
						donacion, 
						idfundacion, 
						pedidoAnterior, 
						pedidoDetalleEliminadosVector, 
						pedidoDetalleProcesoVector,
						tarjetasCollectionDetalles,
						parametros,idFactura,idReciboCaja,cliente,clienteOficina);

				try {
					facturaPosMessageLocal.sendToPrincipalIfPos();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			Map resultMap = new HashMap();
			resultMap.put("FACTURA_ID", idFactura);
			resultMap.put("RECIBO_CAJA_ID", idReciboCaja);
			return resultMap;
		}

		private List<CarteraDetalleIf> obtenerCreditosDetalleColeccion(List<CarteraDetalleIf> carteraDetalleColeccion) throws GenericBusinessException {
			List<CarteraDetalleIf> creditosDetalleList = new ArrayList<CarteraDetalleIf>();
			Iterator it = carteraDetalleColeccion.iterator();
			while (it.hasNext()) {
				CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) it.next();
				DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(carteraDetalle.getDocumentoId());
				if (documento.getCodigo().equals(DEVOLUCION)) {
					creditosDetalleList.add((CarteraDetalleIf) DeepCopy.copy(carteraDetalle));
					/*CarteraIf cartera = (CarteraIf) findCarteraById(carteraDetalle.getCarteraId()).iterator().next();
					cartera.setSaldo(carteraDetalle.getSaldo());
					manager.merge(cartera);*/
				}
			}
			return creditosDetalleList;
		}

		private AsientoIf generarAsientosAutomaticos(CarteraIf cartera,List<CarteraDetalleIf> modelDetalleList,Map<String,Object> parametrosEmpresa,boolean isUpdate, FacturaIf factura, double totalGiftcard, Map valoresAfectaDevoluciones) throws GenericBusinessException{
			AsientoIf asiento = null;
			//ASIENTOS
			comprobanteIngresoPosAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
			int i=0;
			for (CarteraDetalleIf carteraDetalle : modelDetalleList) {
				DocumentoIf documentoComprobanteIngreso = (DocumentoIf) documentoLocal.getDocumento(carteraDetalle.getDocumentoId());
				Long etapa = (documentoComprobanteIngreso.getCodigo().equals(DEVOLUCION))?2L:0L;
				double disponible = carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue();
				double valorGiftcard = totalGiftcard;
				double valorAfectaDevolucion = 0D;
				if (documentoComprobanteIngreso.getCodigo().equals(DEVOLUCION)) {
					valorAfectaDevolucion = ((Double) valoresAfectaDevoluciones.get(carteraDetalle.getId())).doubleValue();
					disponible = valorAfectaDevolucion;
				}
				if (disponible < valorGiftcard)
					valorGiftcard = disponible;

				if (i != modelDetalleList.size() - 1){
					asiento = generarAsientoAutomaticoComprobanteIngreso(cartera, carteraDetalle, false, parametrosEmpresa, etapa, factura, valorGiftcard, valorAfectaDevolucion);
				}else if (i == modelDetalleList.size() - 1){
					asiento = generarAsientoAutomaticoComprobanteIngreso(cartera, carteraDetalle, true, parametrosEmpresa, etapa, factura, valorGiftcard, valorAfectaDevolucion);
				}

				totalGiftcard -= valorGiftcard;
				i++;
			}
			return asiento;

		}

		private AsientoIf generarAsientoAutomaticoComprobanteIngreso(CarteraIf cartera, CarteraDetalleIf carteraDetalle, boolean procesarAsiento,Map<String,Object> parametrosEmpresa, Long etapa, FacturaIf factura, double totalGiftcard, double valorAfectaDevolucion) throws GenericBusinessException {
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			AsientoIf asientoRetornar = null;
			DocumentoIf documentoIf = documentoLocal.getDocumento(carteraDetalle.getDocumentoId());
			if (documentoIf != null) {
				Map aMap = new HashMap();
				aMap.put("documentoId", documentoIf.getId());
				if (etapa.compareTo(0L) != 0)
					aMap.put("etapa", etapa);
				Iterator eventoContableIterator = eventoContableLocal.findEventoContableByQuery(aMap).iterator();
				if (eventoContableIterator.hasNext()) {
					EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();
					if (eventoContable != null) {
						parameterMap.clear();
						parameterMap.put("BEAN", cartera);
						parameterMap.put("OBSERVACION", "CARTERA");
						parameterMap.put("CTAXCOB", Double.valueOf(0.0));
						parameterMap.put("CTAXCOBDIF", Double.valueOf(0.0));
						parameterMap.put("ANTICIPOCL", 0D);
						if(documentoIf.getCodigo().equals(COBRO_CLIENTE_CHEQUE) || documentoIf.getCodigo().equals("CCLD") || documentoIf.getCodigo().equals("CLNN") || 
								documentoIf.getCodigo().equals("REIC") || documentoIf.getCodigo().equals("RERC") || (documentoIf.getCodigo().equals("CHPO") && etapa.compareTo(1L) == 0)){
							parameterMap.put("CTAXCOB", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue());
						}
						if(documentoIf.getCodigo().equals("CHPO")) {
							if (etapa.compareTo(2L) == 0) {
								parameterMap.put("CHPOSTFECH", carteraDetalle.getValor().doubleValue());
							} else
								parameterMap.put("CHPOSTFECH", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue());
						}
						if(documentoIf.getCodigo().equals(COBRO_CLIENTE_CHEQUE) || documentoIf.getCodigo().equals("COEM") || (documentoIf.getCodigo().equals("CHPO") && etapa.compareTo(2L) == 0)){
							if (documentoIf.getCodigo().equals("CHPO")) {
								parameterMap.put("BANCO", carteraDetalle.getValor().doubleValue());
							} else
								parameterMap.put("BANCO", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue());

							parameterMap.put("CUENTA_BANCARIA_ID", carteraDetalle.getCuentaBancariaId());
						}

						if(documentoIf.getCodigo().equals(COBRO_CLIENTE_TARJETA_CREDITO)) {
							parameterMap.put("TAR/CRE", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue());
							parameterMap.put("CTAXCOB", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue() - totalGiftcard);
							parameterMap.put("GIFTCARD", totalGiftcard);
						}

						if(documentoIf.getCodigo().equals(COBRO_CLIENTE_EFECTIVO)) {
							parameterMap.put("CAJA", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue());
							parameterMap.put("CTAXCOB", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue() - totalGiftcard);
							parameterMap.put("GIFTCARD", totalGiftcard);
						}

						if(documentoIf.getCodigo().equals(COBRO_CLIENTE_GIFTCARD)) {
							parameterMap.put("CTAXPAG", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue());
							parameterMap.put("CTAXCOB", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue() - totalGiftcard);
							parameterMap.put("GIFTCARD", totalGiftcard);
						}

						if(documentoIf.getCodigo().equals(COBRO_CLIENTE_PUNTOS)) {
							parameterMap.put("CTAXPAG", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue());
							parameterMap.put("CTAXCOB", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue() - totalGiftcard);
							parameterMap.put("GIFTCARD", totalGiftcard);
						}

						if (documentoIf.getCodigo().equals(DEVOLUCION)) {
							parameterMap.put("CTAXPAG", valorAfectaDevolucion);
							parameterMap.put("CTAXCOB", valorAfectaDevolucion - totalGiftcard);
							parameterMap.put("GIFTCARD", totalGiftcard);
						}

						parameterMap.put("TIPO_DOCUMENTO_ID", cartera.getTipodocumentoId());
						parameterMap.put("CARTERA_DETALLE", carteraDetalle);
						if (factura != null)
							parameterMap.put("FACTURA_PREIMPRESO", factura.getPreimpresoNumero());
						asientoRetornar = comprobanteIngresoPosAsientoAutomaticoHandlerLocal.generarComprobanteIngresoAsientoAutomaticoHandler(eventoContable, parameterMap, procesarAsiento, parametrosEmpresa, etapa);
					}
				}
			}
			return asientoRetornar;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void saveCarteraDetalle(com.spirit.cartera.entity.CarteraDetalleIf model)
		throws com.spirit.exception.GenericBusinessException 
		{

			try
			{
				CarteraDetalleEJB data = new CarteraDetalleEJB();
				data.setId(model.getId());
				data.setCarteraId(model.getCarteraId());
				data.setDocumentoId(model.getDocumentoId());
				data.setSecuencial(model.getSecuencial());
				data.setLineaId(model.getLineaId());
				data.setFormaPagoId(model.getFormaPagoId());
				data.setCuentaBancariaId(model.getCuentaBancariaId());
				data.setReferencia(model.getReferencia());
				data.setPreimpreso(model.getPreimpreso());
				data.setDepositoId(model.getDepositoId());
				data.setFechaCreacion(model.getFechaCreacion());
				data.setFechaCartera(model.getFechaCartera());
				data.setFechaVencimiento(model.getFechaVencimiento());
				data.setFechaUltimaActualizacion(model.getFechaUltimaActualizacion());
				data.setValor(model.getValor());
				data.setSaldo(model.getSaldo());
				data.setCotizacion(model.getCotizacion());
				data.setCartera(model.getCartera());
				data.setAutorizacion(model.getAutorizacion());
				data.setSriSustentoTributarioId(model.getSriSustentoTributarioId());
				manager.merge(data);
				manager.flush();
			} catch (Exception e) {
				log.error("Error al actualizar información en carteraDetalle.", e);
				throw new GenericBusinessException(
				"Error al actualizar información en carteraDetalle.");
			}

		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void saveCartera(com.spirit.cartera.entity.CarteraIf model)
		throws com.spirit.exception.GenericBusinessException 
		{

			try
			{
				CarteraEJB data = new CarteraEJB();
				data.setId(model.getId());
				data.setTipo(model.getTipo());
				data.setOficinaId(model.getOficinaId());
				data.setTipodocumentoId(model.getTipodocumentoId());
				data.setCodigo(model.getCodigo());
				data.setReferenciaId(model.getReferenciaId());
				data.setClienteoficinaId(model.getClienteoficinaId());
				data.setPreimpreso(model.getPreimpreso());
				data.setUsuarioId(model.getUsuarioId());
				data.setVendedorId(model.getVendedorId());
				data.setMonedaId(model.getMonedaId());
				data.setFechaEmision(model.getFechaEmision());
				data.setValor(model.getValor());
				data.setSaldo(model.getSaldo());
				data.setFechaUltimaActualizacion(model.getFechaUltimaActualizacion());
				data.setEstado(model.getEstado());
				data.setComentario(model.getComentario());
				data.setAprobado(model.getAprobado());
				manager.merge(data);
				manager.flush();
			} catch (Exception e) {
				log.error("Error al actualizar información en cartera.", e);
				throw new GenericBusinessException(
				"Error al actualizar información en cartera.");
			}

		}

		private CarteraDetalleEJB registrarCarteraDetalle(CarteraDetalleIf modelDetalle, boolean esCartera) {
			CarteraDetalleEJB carteraDetalle = new CarteraDetalleEJB();

			carteraDetalle.setId(modelDetalle.getId());
			carteraDetalle.setCarteraId(modelDetalle.getCarteraId());
			//COCL
			carteraDetalle.setDocumentoId(modelDetalle.getDocumentoId());
			carteraDetalle.setSriClienteRetencionId(modelDetalle.getSriClienteRetencionId());
			carteraDetalle.setSecuencial(modelDetalle.getSecuencial());
			carteraDetalle.setLineaId(modelDetalle.getLineaId());
			carteraDetalle.setFormaPagoId(modelDetalle.getFormaPagoId());
			carteraDetalle.setCuentaBancariaId(modelDetalle.getCuentaBancariaId());
			carteraDetalle.setReferencia(modelDetalle.getReferencia());
			carteraDetalle.setPreimpreso(modelDetalle.getPreimpreso());
			carteraDetalle.setDepositoId(modelDetalle.getDepositoId());
			carteraDetalle.setFechaCreacion(modelDetalle.getFechaCreacion());
			carteraDetalle.setFechaCartera(modelDetalle.getFechaCartera());
			carteraDetalle.setFechaVencimiento(modelDetalle.getFechaVencimiento());
			carteraDetalle.setFechaUltimaActualizacion(modelDetalle.getFechaUltimaActualizacion());
			carteraDetalle.setValor(utilitariosLocal.redondeoValor(modelDetalle.getValor()));
			if(esCartera){
				carteraDetalle.setSaldo(utilitariosLocal.redondeoValor(modelDetalle.getSaldo()));
			}else{
				carteraDetalle.setSaldo(utilitariosLocal.redondeoValor(modelDetalle.getValor()));
			}

			if(modelDetalle.getCotizacion() != null){
				carteraDetalle.setCotizacion(utilitariosLocal.redondeoValor(modelDetalle.getCotizacion()));
			}
			carteraDetalle.setCartera(modelDetalle.getCartera());
			carteraDetalle.setAutorizacion(modelDetalle.getAutorizacion());
			carteraDetalle.setSriSustentoTributarioId(modelDetalle.getSriSustentoTributarioId());
			carteraDetalle.setDiferido(modelDetalle.getDiferido());
			carteraDetalle.setObservacion(modelDetalle.getObservacion());

			return carteraDetalle;
		}

		public Map<String, Object> procesarCartera(Long idcarteraprincipal, double valorFactura, CarteraIf model, List<CarteraDetalleIf> modelDetalleList, Long idEmpresa, Map valoresAfectaDevoluciones) throws GenericBusinessException {
			Map<String, Object> objectMap = new HashMap();
			CarteraEJB cartera = null;
			List<CarteraDetalleIf> creditosDetallesList = new ArrayList<CarteraDetalleIf>();
			BigDecimal valorSoloCC=new BigDecimal("0.00");
			try {			

				boolean pagoCCunico=true;
				for (CarteraDetalleIf modelDetalle2 : modelDetalleList) {				
					if(modelDetalle2.getFormaPagoId().equals(buscarTipoPagoByCodigo(TIPO_PAGO_CREDITO_CLIENTE).getId()))
					{
						valorSoloCC=valorSoloCC.add(modelDetalle2.getValor());
						if(pagoCCunico)pagoCCunico=true;

					}
					else
					{pagoCCunico=false;

					}
				}



				cartera = registrarCartera(model);
				cartera.setValor(BigDecimal.valueOf(calcularTotalCarteraCobros(modelDetalleList, pagoCCunico)));
				cartera.setSaldo(BigDecimal.ZERO);
				if(!pagoCCunico)
				{				
					manager.persist(cartera);
				}

				Iterator it = modelDetalleList.iterator();
				while (it.hasNext()) {
					CarteraDetalleIf modelDetalle = (CarteraDetalleIf) it.next();
					CarteraDetalleEJB carteraDetalle = registrarCarteraDetalle(modelDetalle, true);

					//el pago se lo hace con el crédito que tenia el cliente
					if(carteraDetalle.getFormaPagoId().equals(buscarTipoPagoByCodigo(TIPO_PAGO_CREDITO_CLIENTE).getId())) { 
						HashMap<String, Object> mapa = new HashMap<String, Object>();
						mapa.clear();
						mapa.put("tipodocumentoId",findTipoDocumentoByCodigo(DEVOLUCION).getId());
						mapa.put("clienteoficinaId",cartera.getClienteoficinaId());						
						BigDecimal valor_t=carteraDetalle.getValor();
						Iterator carterasIt;
						Vector<Vector> detalles_id_saldo =new Vector<Vector>();	
						//carterasIt = findCarteraByQuery(mapa).iterator();
						carterasIt = findCarteraCreditoDisponible(mapa,idEmpresa).iterator();					
						//BigDecimal restando=carteraDetalle.getValor();
						System.out.println(valor_t+"valor_t-----------------------***valor factura en cc:--->"+valorFactura);
						System.out.println(carteraDetalle.getValor()+"valor*---- saldo*--"+carteraDetalle.getSaldo());
						//valorFactura -= carteraDetalle2.getValor().subtract(carteraDetalle2.getSaldo()).doubleValue();

						BigDecimal restando=BigDecimal.valueOf(valorFactura);
						//johanna
						restando=valorSoloCC;
						it.remove();
						//while (carterasIt.hasNext() && !restando.equals(BigDecimal.ZERO)) 
						//while (carterasIt.hasNext() && valorFactura > 0D)
						while (carterasIt.hasNext())
						{
							CarteraIf carteraCabec= (CarteraIf) carterasIt.next();
							BigDecimal valor_deta=carteraCabec.getSaldo();
							if(valor_deta.compareTo(BigDecimal.ZERO)==1)//es mayor que cero
							{
								int flag=valor_deta.compareTo(restando);//-1 menor, 0 igual, 1 mayor								
								if(flag==-1){//restando tiene valor todavia
									valorFactura -= valor_deta.doubleValue();
									restando=restando.subtract(valor_deta);
									Vector<String> id_saldo = new Vector<String>();
									id_saldo.add(0,carteraCabec.getId().toString());
									id_saldo.add(1,"0.00");
									id_saldo.add(2,valor_deta.toString());
									detalles_id_saldo.add(id_saldo);									
								}
								if(flag==0){//es igual al valor
									Vector<String> id_saldo = new Vector<String>();
									id_saldo.add(0,carteraCabec.getId().toString());
									id_saldo.add(1,"0.00");
									id_saldo.add(2,valor_deta.toString());
									detalles_id_saldo.add(id_saldo);
									restando=BigDecimal.ZERO;
									valorFactura = 0D;
								}
								if(flag==1){//restando es cero... pero carteradet se queda con algo en saldo
									Vector<String> id_saldo = new Vector<String>();
									id_saldo.add(0,carteraCabec.getId().toString());
									id_saldo.add(1,valor_deta.subtract(restando).toString());
									id_saldo.add(2,restando.toString());
									detalles_id_saldo.add(id_saldo);
									restando=BigDecimal.ZERO;
									valorFactura = 0D;
								}	

							}
						}

						if (detalles_id_saldo.size() != 0) {
							for (int h = 0; h < detalles_id_saldo.size(); h++) {
								String id=((Vector)detalles_id_saldo.get(h)).get(0).toString();
								String valorahora=((Vector)detalles_id_saldo.get(h)).get(1).toString();
								String valorrestado=((Vector)detalles_id_saldo.get(h)).get(2).toString();

								Iterator carteraCABECIt;
								carteraCABECIt=findCarteraById(new Long(id)).iterator();
								if (carteraCABECIt.hasNext()){
									CarteraIf cabecera_actualiz = (CarteraIf) carteraCABECIt.next();
									cabecera_actualiz.setSaldo(new BigDecimal(valorahora));
									//actualiza el detalle
									manager.persist(cabecera_actualiz);
								}

								Iterator carteraDetalle_act;
								carteraDetalle_act=findCarteraDetalleByCarteraId(new Long(id)).iterator();
								if (carteraDetalle_act.hasNext()){			
									CarteraDetalleIf deta_actualiz = (CarteraDetalleIf) carteraDetalle_act.next();
									deta_actualiz.setSaldo(new BigDecimal(valorahora));
									//actualiza el detalle
									modelDetalle.setCarteraId(deta_actualiz.getCarteraId());
									modelDetalle.setId(deta_actualiz.getId());
									manager.merge(deta_actualiz);
									creditosDetallesList.add(deta_actualiz);

									//crea cartera detalle por cada credito q se uso									
									//if (deta_actualiz.getCartera().equals("S") && idcarteraprincipal.compareTo(0L) != 0 && valorFactura > 0D) {
									if (deta_actualiz.getCartera().equals("S") && idcarteraprincipal.compareTo(0L) != 0 && Double.parseDouble(utilitariosLocal.removeDecimalFormat(valorrestado)) > 0D) {
										CarteraAfectaIf modelCarteraAfecta = new CarteraAfectaEJB();
										modelCarteraAfecta.setCarteradetalleId(deta_actualiz.getPrimaryKey());
										modelCarteraAfecta.setCarteraafectaId(idcarteraprincipal);
										modelCarteraAfecta.setUsuarioId(cartera.getUsuarioId());
										modelCarteraAfecta.setValor(new BigDecimal(valorrestado));//utilitariosLocal.redondeoValor(deta_actualiz.getValor()));
										modelCarteraAfecta.setFechaCreacion(deta_actualiz.getFechaCreacion());
										modelCarteraAfecta.setFechaAplicacion(deta_actualiz.getFechaCartera());
										modelCarteraAfecta.setCartera(deta_actualiz.getCartera());
										CarteraAfectaEJB carteraAfecta = registrarCarteraAfecta(modelCarteraAfecta);
										double valorAfectaMap = (valoresAfectaDevoluciones.get(deta_actualiz.getPrimaryKey()) == null)?0D:((Double) valoresAfectaDevoluciones.get(deta_actualiz.getPrimaryKey())).doubleValue();										
										valoresAfectaDevoluciones.put(deta_actualiz.getPrimaryKey(), Double.parseDouble(utilitariosLocal.removeDecimalFormat(valorrestado)) + valorAfectaMap);
										manager.merge(carteraAfecta);
									}
								}
							}
						}
						//aqui actualizo no creo un detalle por el tipo de pago CR	
					}
					else{

						System.out.println("valor factura en EFCT:--->"+valorFactura);

						modelDetalle.setCarteraId(cartera.getPrimaryKey());
						CarteraDetalleEJB carteraDetalle2 = registrarCarteraDetalle(modelDetalle, true);

						manager.persist(carteraDetalle2);

						if (carteraDetalle2.getCartera().equals("S") && idcarteraprincipal.compareTo(0L) != 0 && valorFactura > 0D && (carteraDetalle2.getValor().subtract(carteraDetalle2.getSaldo())).doubleValue() > 0D) {
							//if (carteraDetalle2.getCartera().equals("S") && idcarteraprincipal.compareTo(0L) != 0) {
							CarteraAfectaIf modelCarteraAfecta = new CarteraAfectaEJB();
							modelCarteraAfecta.setCarteradetalleId(carteraDetalle2.getPrimaryKey());
							modelCarteraAfecta.setCarteraafectaId(idcarteraprincipal);
							modelCarteraAfecta.setUsuarioId(cartera.getUsuarioId());
							modelCarteraAfecta.setValor(utilitariosLocal.redondeoValor(carteraDetalle2.getValor().subtract(carteraDetalle2.getSaldo())));
							modelCarteraAfecta.setFechaCreacion(carteraDetalle2.getFechaCreacion());
							modelCarteraAfecta.setFechaAplicacion(carteraDetalle2.getFechaCartera());
							modelCarteraAfecta.setCartera(carteraDetalle2.getCartera());
							CarteraAfectaEJB carteraAfecta = registrarCarteraAfecta(modelCarteraAfecta);
							manager.merge(carteraAfecta);
						}

						valorFactura -= carteraDetalle2.getValor().subtract(carteraDetalle2.getSaldo()).doubleValue();
					}				
				}
			} catch (Exception e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				log.error("Error al guardar información en CarteraEJB.", e);
				throw new GenericBusinessException("Se ha producido un error al guardar los datos de Cartera");
			}

			modelDetalleList.addAll(creditosDetallesList);
			objectMap.put("CARTERA", cartera);
			objectMap.put("DETALLES", modelDetalleList);
			objectMap.put("VALORES_AFECTA_MAP", valoresAfectaDevoluciones);
			return objectMap;
		}

		private double calcularTotalCarteraCobros(List<CarteraDetalleIf> modelDetalleList, boolean pagoUnico) throws GenericBusinessException {
			double total = 0D;
			try {
				for (CarteraDetalleIf detalle : modelDetalleList) {
					DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(detalle.getDocumentoId());
					if (pagoUnico || (!pagoUnico && !documento.getCodigo().equals(DEVOLUCION)))
						total += detalle.getValor().doubleValue();
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				log.error("Error al guardar información en CarteraEJB.", e);
				throw new GenericBusinessException("Se ha producido al calcular total de cobros");
			}

			return total;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public java.util.Collection findCarteraById(java.lang.Long id) {

			String queryString = "from CarteraEJB e where e.id = :id ";
			// Add a an order by on all primary keys to assure reproducable results.
			String orderByPart = "";
			orderByPart += " order by e.id";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			query.setParameter("id", id);
			return query.getResultList();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public java.util.Collection findCarteraDetalleById(java.lang.Long id) {

			String queryString = "from CarteraDetalleEJB e where e.id = :id ";
			// Add a an order by on all primary keys to assure reproducable results.
			String orderByPart = "";
			orderByPart += " order by e.id";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			query.setParameter("id", id);
			return query.getResultList();
		}

		public List<CarteraDetalleIf> generarDetalleCobroVector(Vector<Vector> detalleCobroVector, String codigoComprobante, double valorFactura) {
			List<CarteraDetalleIf> carteraDetalleColeccion = new ArrayList<CarteraDetalleIf>();		

			if (detalleCobroVector.size() != 0) {
				for (int i = 0; i < detalleCobroVector.size(); i++) {
					String pagoTotal = ((Vector) detalleCobroVector.get(i)).get(1).toString();
					String nombreTarjetaCredito = ((Vector) detalleCobroVector.get(i)).get(2).toString();
					String tipoPagoTarjetaCredito = ((Vector) detalleCobroVector.get(i)).get(3).toString();
					String tipoPago = ((Vector) detalleCobroVector.get(i)).get(4).toString();
					String numeroCheque = ((Vector) detalleCobroVector.get(i)).get(5).toString();
					String banco = ((Vector) detalleCobroVector.get(i)).get(6).toString();
					String codigo = "";
					if (codigoComprobante.equals("CIN")) {
						if (tipoPago.equals(TIPO_PAGO_CHEQUE)) 	codigo = COBRO_CLIENTE_CHEQUE;
						else if (tipoPago.equals(TIPO_PAGO_TARJETA_CREDITO)) codigo = COBRO_CLIENTE_TARJETA_CREDITO;
						else if (tipoPago.equals(TIPO_PAGO_CREDITO_CLIENTE)) codigo = DEVOLUCION;
						else if (tipoPago.equals(TIPO_PAGO_EFECTIVO_CLIENTE))codigo = COBRO_CLIENTE_EFECTIVO;
						else if (tipoPago.equals(TIPO_PAGO_GIFTCARD)) codigo = COBRO_CLIENTE_GIFTCARD;
						else if (tipoPago.equals(TIPO_PAGO_PUNTOS)) codigo = COBRO_CLIENTE_PUNTOS; 
					} else if (codigoComprobante.equals("RCA")) {
						if (tipoPago.equals(TIPO_PAGO_CHEQUE)) 	codigo=RECIBO_CAJA_CHEQUE;
						else if (tipoPago.equals(TIPO_PAGO_TARJETA_CREDITO)) codigo=RECIBO_CAJA_TARJETA_CREDITO;
						else if (tipoPago.equals(TIPO_PAGO_EFECTIVO_CLIENTE))codigo=RECIBO_CAJA_EFECTIVO;
						else if (tipoPago.equals(TIPO_PAGO_GIFTCARD)) codigo=RECIBO_CAJA_GIFTCARD;
					}

					if(codigo.equals(COBRO_CLIENTE_CHEQUE) || codigo.equals(COBRO_CLIENTE_TARJETA_CREDITO) || codigo.equals(COBRO_CLIENTE_EFECTIVO) || codigo.equals(DEVOLUCION) || codigo.equals(COBRO_CLIENTE_GIFTCARD)
							|| codigo.equals(RECIBO_CAJA_CHEQUE) || codigo.equals(RECIBO_CAJA_TARJETA_CREDITO) || codigo.equals(RECIBO_CAJA_EFECTIVO) || codigo.equals(RECIBO_CAJA_GIFTCARD) || codigo.equals(COBRO_CLIENTE_PUNTOS))
					{
						double saldo = 0D;
						if (valorFactura <= Double.parseDouble(utilitariosLocal.removeDecimalFormat(pagoTotal))) {
							saldo = Double.parseDouble(utilitariosLocal.removeDecimalFormat(pagoTotal)) - valorFactura;
							valorFactura = 0D;
						} else if (valorFactura > Double.parseDouble(utilitariosLocal.removeDecimalFormat(pagoTotal))) {
							valorFactura -= Double.parseDouble(utilitariosLocal.removeDecimalFormat(pagoTotal));
						}

						CarteraDetalleIf carteraDetalleIf = registrarCarteraDetalleIgualacion(pagoTotal,String.valueOf(saldo),tipoPago,codigo,numeroCheque,banco,false);
						carteraDetalleIf.setSecuencial(i+1);
						carteraDetalleColeccion.add(carteraDetalleIf);	
					}

				}
			}			

			return carteraDetalleColeccion;
		}

		private CarteraDetalleEJB registrarCarteraDetalleIgualacion(String valor, String saldo, String tipo_pago, String codigo, String numcheque, String banco, boolean esDevolucion) {
			CarteraDetalleEJB carteraDetalle = new CarteraDetalleEJB();
			try {	

				DocumentoIf documentoIf = null;

				Iterator iterDocumento = documentoLocal.findDocumentoByCodigo(codigo).iterator();
				if (iterDocumento.hasNext()) {
					documentoIf=(DocumentoIf)iterDocumento.next();
					carteraDetalle.setDocumentoId(documentoIf.getId());
				}		

				carteraDetalle.setFormaPagoId(buscarTipoPagoByCodigo(tipo_pago).getId());
				java.util.Date fechaHoy = new java.util.Date();
				carteraDetalle.setFechaCreacion(new java.sql.Date(fechaHoy.getYear(), fechaHoy.getMonth(), fechaHoy.getDate()));
				carteraDetalle.setFechaCartera(new java.sql.Date(fechaHoy.getYear(), fechaHoy.getMonth(), fechaHoy.getDate()));
				carteraDetalle.setFechaVencimiento(new java.sql.Date(fechaHoy.getYear(), fechaHoy.getMonth(), fechaHoy.getDate()));
				carteraDetalle.setFechaUltimaActualizacion(new java.sql.Date(fechaHoy.getYear(), fechaHoy.getMonth(), fechaHoy.getDate()));
				carteraDetalle.setValor(utilitariosLocal.redondeoValor(new BigDecimal(valor)));
				//este saldo no deberia ser igual a cero sino al valor - total factura
				carteraDetalle.setSaldo(utilitariosLocal.redondeoValor(new BigDecimal(saldo)));
				carteraDetalle.setCartera("S");

				//el pago se lo esta haciendo con tarjeta de crédito
				if(codigo.equals(COBRO_CLIENTE_CHEQUE))
				{
					carteraDetalle.setReferencia(banco);
					carteraDetalle.setPreimpreso(numcheque);
				}

				//carteraDetalle.setObservacion("");
			} catch (GenericBusinessException e) {
				e.printStackTrace();

			} 
			return carteraDetalle;
		}

		private CarteraEJB registrarCarteraIgualacion(CarteraIf modelCartera,
				CarteraIf carteraAnterior, FacturaIf modelFactura,
				boolean actualizarCodigoCartera, Long idOficina,String codigoTipoDoc) {
			CarteraEJB cartera = new CarteraEJB();
			String TIPO_CARTERA_CLIENTE = "C";
			String ESTADO_CARTERA_NORMAL = "N";
			String TIPO_CLIENTE = "C";
			cartera.setTipo(TIPO_CLIENTE);

			try {
				Long usuarioId = ((UsuarioIf) usuarioLocal.findUsuarioById(modelFactura.getUsuarioId()).iterator().next()).getId();
				CajaIf cajaIf = (CajaIf) cajaLocal.findCajaByUsuarioId(usuarioId).iterator().next();
				OficinaIf oficinaIf = oficinaLocal.getOficina(modelFactura.getOficinaId());
				if (cajaIf != null)
					modelCartera.setPreimpreso(modelFactura.getPreimpresoNumero());
				cartera.setId(modelCartera.getId());
				cartera.setOficinaId(modelFactura.getOficinaId());
				TipoDocumentoIf tipoDocumentoIf = (TipoDocumentoIf)tipoDocumentoLocal.findTipoDocumentoByCodigo(codigoTipoDoc).iterator().next();			
				cartera.setTipodocumentoId(tipoDocumentoIf.getId());
				EmpresaIf empresa = empresaLocal.getEmpresa(tipoDocumentoIf.getEmpresaId());
				nuevaCodificacionActiva = (modelFactura.getFechaFactura().getYear() + 1900 <= 2008)?false:true;
				if (nuevaCodificacionActiva && actualizarCodigoCartera) {
					String unNumeroCartera = getNumeroCartera(modelFactura.getFechaFactura(), codigoTipoDoc, empresa.getId(), idOficina);
					cartera.setCodigo(unNumeroCartera);
					cartera.setCodigo(cartera.getCodigo() + formatoSerial.format(getMaximoNumeroCartera(cartera)));
				} else if (nuevaCodificacionActiva && !actualizarCodigoCartera) {
					cartera.setCodigo(carteraAnterior!=null?carteraAnterior.getCodigo():"");
				} else if (!nuevaCodificacionActiva && actualizarCodigoCartera) {
					String unNumeroCartera = getNumeroCartera(modelFactura.getFechaFactura(), codigoTipoDoc, empresa.getId(), idOficina);
					cartera.setCodigo(unNumeroCartera);
					cartera.setCodigo(cartera.getCodigo() + formatoSerial.format(getMaximoNumeroCartera(cartera)));
				} else if (!nuevaCodificacionActiva && !actualizarCodigoCartera)
					cartera.setCodigo(carteraAnterior != null ? carteraAnterior.getCodigo() : "");

				cartera.setReferenciaId(modelFactura.getId());
				cartera.setClienteoficinaId(modelFactura.getClienteoficinaId());
				cartera.setPreimpreso(modelCartera.getPreimpreso());
				cartera.setUsuarioId(modelFactura.getUsuarioId());
				cartera.setVendedorId(modelFactura.getVendedorId());
				cartera.setMonedaId(modelFactura.getMonedaId());
				cartera.setFechaEmision(modelFactura.getFechaFactura());
				double valorFactura = modelFactura.getValor().doubleValue();
				double descuentoFactura = modelFactura.getDescuento().doubleValue();
				double descuentoGlobalFactura = modelFactura.getDescuentoGlobal().doubleValue();
				double descuentoTotalFactura = descuentoFactura	+ descuentoGlobalFactura;
				double porcentajeComision = modelFactura.getPorcentajeComisionAgencia().doubleValue();
				double comision = ((valorFactura - descuentoTotalFactura) * porcentajeComision) / 100D;
				double ivaFactura = modelFactura.getIva().doubleValue();
				double iceFactura = modelFactura.getIce().doubleValue();
				double otroImpuesto = modelFactura.getOtroImpuesto().doubleValue();
				double impuestoTotalFactura = ivaFactura + iceFactura	+ otroImpuesto;
				double valorCartera = valorFactura - descuentoTotalFactura	+ impuestoTotalFactura + comision;
				cartera.setValor(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorCartera)));
				cartera.setSaldo(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorCartera)));
				cartera.setFechaUltimaActualizacion(modelCartera.getFechaUltimaActualizacion());
				cartera.setEstado(ESTADO_CARTERA_NORMAL);
				if(codigoTipoDoc.equals(DEVOLUCION))
					cartera.setComentario(modelFactura.getObservacion());
				else {
					String comentario = "";
					if(cartera.getCodigo().length() >= 20) {
						String codigoTipoDocumento = "I: ";
						if (!codigoTipoDoc.equals("CIN"))
							codigoTipoDocumento = codigoTipoDoc + ": ";
						comentario = codigoTipoDocumento + cartera.getCodigo().substring(12,25) + " " + modelFactura.getObservacion();
					} else {
						String codigoTipoDocumento = "I: ";
						if (!codigoTipoDoc.equals("CIN"))
							codigoTipoDocumento = codigoTipoDoc + ": ";
						comentario = codigoTipoDocumento + cartera.getCodigo() + " " + modelFactura.getObservacion();
					}
					cartera.setComentario(comentario);
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			return cartera;
		}

		private CarteraEJB registrarCarteraIgualacion(CarteraIf modelCartera, String codigoTipoDoc, Map<String, Object> parametros) {
			CarteraEJB cartera = new CarteraEJB();
			String TIPO_CARTERA_CLIENTE = "C";
			String ESTADO_CARTERA_NORMAL = "N";
			String TIPO_CLIENTE = "C";
			cartera.setTipo(TIPO_CLIENTE);
			EmpleadoIf empleado = (EmpleadoIf) parametros.get("EMPLEADO");
			ClienteIf cliente = (ClienteIf) parametros.get("CLIENTE");
			ClienteOficinaIf clienteOficina = (ClienteOficinaIf) parametros.get("CLIENTE_OFICINA");
			PuntoImpresionIf puntoImpresion = (PuntoImpresionIf) parametros.get("PUNTO_IMPRESION");
			Long idOficina = (Long) parametros.get("OFICINA_ID");
			Long idEmpresa = (Long) parametros.get("EMPRESA_ID");
			UsuarioIf usuario = (UsuarioIf) parametros.get("USUARIO");
			MonedaIf moneda = (MonedaIf) parametros.get("MONEDA");

			try {
				Long usuarioId = ((UsuarioIf) usuarioLocal.findUsuarioById(usuario.getId()).iterator().next()).getId();
				CajaIf cajaIf = (CajaIf) cajaLocal.findCajaByUsuarioId(usuarioId).iterator().next();
				OficinaIf oficinaIf = oficinaLocal.getOficina(idOficina);
				cartera.setId(modelCartera.getId());
				cartera.setOficinaId(idOficina);
				TipoDocumentoIf tipoDocumentoIf = (TipoDocumentoIf)tipoDocumentoLocal.findTipoDocumentoByCodigo(codigoTipoDoc).iterator().next();			
				cartera.setTipodocumentoId(tipoDocumentoIf.getId());
				EmpresaIf empresa = empresaLocal.getEmpresa(tipoDocumentoIf.getEmpresaId());
				cartera.setCodigo(modelCartera.getCodigo());
				cartera.setClienteoficinaId(clienteOficina.getId());
				cartera.setPreimpreso(modelCartera.getPreimpreso());
				cartera.setUsuarioId(usuario.getId());
				cartera.setVendedorId(empleado.getId());
				cartera.setMonedaId(moneda.getId());
				java.sql.Date now = new java.sql.Date(utilitariosLocal.getServerDateSql().getTime());
				cartera.setFechaEmision(utilitariosLocal.fromSqlDateToTimestamp(now));
				cartera.setValor(utilitariosLocal.redondeoValor(BigDecimal.ZERO));
				cartera.setSaldo(utilitariosLocal.redondeoValor(BigDecimal.ZERO));
				cartera.setFechaUltimaActualizacion(modelCartera.getFechaUltimaActualizacion());
				cartera.setEstado(ESTADO_CARTERA_NORMAL);
				String comentario = "";
				if(cartera.getCodigo().length() >= 20) {
					String codigoTipoDocumento = "I: ";
					if (!codigoTipoDoc.equals("CIN"))
						codigoTipoDocumento = codigoTipoDoc + ": ";
					comentario = codigoTipoDocumento + cartera.getCodigo().substring(12,25);
				} else {
					String codigoTipoDocumento = "I: ";
					if (!codigoTipoDoc.equals("CIN"))
						codigoTipoDocumento = codigoTipoDoc + ": ";
					comentario = codigoTipoDocumento + cartera.getCodigo();
				}
				cartera.setComentario(comentario);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			return cartera;
		}

		/*private CarteraIf registrarCartera(String valorfinal,Long clienteOficinaId,FacturaIf modelFactura){
		CarteraData data = new CarteraData();
		try {		
			String TIPO_CLIENTE="C";
			data.setTipo(TIPO_CLIENTE);
			//data.setClienteoficinaId(idProveedorOficina);
			data.setOficinaId(Parametros.getIdOficina());
			TipoDocumentoIf tipoDocumentoIf = (TipoDocumentoIf)tipoDocumentoLocal.findTipoDocumentoByCodigo("CIN").iterator().next();			
			data.setTipodocumentoId(tipoDocumentoIf.getId());
			data.setClienteoficinaId(clienteOficinaId);
			//PREIMPRESO = null
			data.setUsuarioId(((UsuarioIf)Parametros.getUsuarioIf()).getId());


			CarteraData modelCartera = new CarteraData();

			modelCartera.setReferenciaId(modelFactura.getPrimaryKey());
			nuevaCodificacionActiva = (modelFactura.getFechaFactura().getYear() + 1900 <= 2008) ? false: true;
			String unNumeroCartera = getNumeroCartera(modelFactura.getFechaFactura(), "FAC", modelFactura.get, idOficina);
			modelCartera.setCodigo(unNumeroCartera);
			modelCartera.setCodigo(modelCartera.getCodigo()+ formatoSerial.format(getMaximoNumeroCartera(modelCartera)));


			cartera.setVendedorId(factura.getVendedorId());
			//VENDEDOR_ID = null
			String codigoMonedaLocal = Parametros.getCodMoneda();
			Iterator monedaLocalIterator = monedaLocal.findMonedaByCodigo(codigoMonedaLocal).iterator();
			if (monedaLocalIterator.hasNext()) {
				MonedaIf monedaLocal = (MonedaIf) monedaLocalIterator.next();
				data.setMonedaId(monedaLocal.getId());
			}
			java.sql.Date fechaEmision = new java.sql.Date(Utilitarios.dateHoy().getTime());
			data.setFechaEmision(fechaEmision);
		 	data.setCodigo(modelCartera.getCodigo());

			double valor = 0D;
			valor=new Double(valorfinal).doubleValue();			
			data.setValor(BigDecimal.valueOf(valor));
			data.setSaldo(BigDecimal.valueOf(valor));
			//data.setSaldo(new BigDecimal("0.00"));
			data.setComentario("Guardada automáticamente POS (ajustes)");
			data.setEstado("N");
			//COMENTARIO = null
			data.setAprobado("S");
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al registrar cartera!", SpiritAlert.ERROR);
		} catch (ParseException ev) {
			ev.printStackTrace();
		}
		return data;
	}*/

		//nuevo
		public Long savePagoPedidoFactura(Long idprincipal,
				Vector<PedidoDetalleIf> detalles, PedidoIf pedidoAnterior,
				Vector<PedidoDetalleIf> ProductosidReunionColeccion_proceso,
				Vector<PedidoDetalleIf> ProductosidReunionColeccion_eliminados,
				PedidoIf pedido, Long idEmpresa, UsuarioIf usuario,boolean isMatriz) {

			Long idPedidoGuardado = new Long("0");		
			Long idfacturatotal = 0L;
			try {
				if (pedidoAnterior != null) 
					idPedidoGuardado = pedidoAnterior.getId();
				else	
					idPedidoGuardado = 0L;

				if(!isMatriz){
					if (pedidoAnterior == null) {				
						idPedidoGuardado = savePedido("", detalles, pedido, idEmpresa);
					} else {
						idPedidoGuardado = pedidoAnterior.getId();
						if (pedidoAnterior.getEstado().equals("T"))
							actualizarPedido(pedidoAnterior, "actualizar1",ProductosidReunionColeccion_proceso,ProductosidReunionColeccion_eliminados, pedido);// actualiza el estado y el detalle
						if (pedidoAnterior.getEstado().equals("A"))
							actualizarPedido(pedidoAnterior, "actualizar2",ProductosidReunionColeccion_proceso,ProductosidReunionColeccion_eliminados, pedido);// actualiza el estado y el detalle
					}
				}
				else{
					if (pedidoAnterior != null) {
						idPedidoGuardado = savePedido("", detalles, pedido, idEmpresa);				
					}	
					else{
						idPedidoGuardado = savePedido("", detalles, pedido, idEmpresa);					
					}
				}			

				pedidoAnterior = pedidoLocal.getPedido(idPedidoGuardado);
				idfacturatotal = facturar(pedidoAnterior, idEmpresa, usuario);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
			return idfacturatotal;
		}

		/* anterior: 06 agosto
	public Long savePagoPedidoFactura(Long idprincipal,
			Vector<PedidoDetalleIf> detalles, PedidoIf pedidoAnterior,
			Vector<PedidoDetalleIf> ProductosidReunionColeccion_proceso,
			Vector<PedidoDetalleIf> ProductosidReunionColeccion_eliminados,
			PedidoIf pedido, Long idEmpresa, UsuarioIf usuario) {

		Long idPedidoGuardado = new Long("0");		
		Long idfacturatotal = 0L;
		try {
			if (pedidoAnterior != null) 
				idPedidoGuardado = pedidoAnterior.getId();
			else	
				idPedidoGuardado = 0L;

			if (pedidoAnterior == null) {				
				idPedidoGuardado = savePedido("", detalles, pedido, idEmpresa);
			} else {
				idPedidoGuardado = pedidoAnterior.getId();
				if (pedidoAnterior.getEstado().equals("T"))
					actualizarPedido(pedidoAnterior, "actualizar1", 
							ProductosidReunionColeccion_proceso,
							ProductosidReunionColeccion_eliminados, pedido);// actualiza el estado y el detalle
				if (pedidoAnterior.getEstado().equals("A"))
					actualizarPedido(pedidoAnterior, "actualizar2",
							ProductosidReunionColeccion_proceso,
							ProductosidReunionColeccion_eliminados, pedido);// actualiza el estado y el detalle
			}

			pedidoAnterior = pedidoLocal.getPedido(idPedidoGuardado);
			idfacturatotal = facturar(pedidoAnterior, idEmpresa, usuario);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return idfacturatotal;
	}*/

		private Long facturar(PedidoIf pedido, Long idEmpresa, UsuarioIf usuario) {
			Long idFactura = 0L;
			try {		
				if (pedido.getEstado().equals("P") || pedido.getEstado().equals("T")) {
					TipoDocumentoIf tipoDocumentoIf = tipoDocumentoLocal.getTipoDocumento(pedido.getTipodocumentoId());				
					if (tipoDocumentoIf != null	&& ("FAC".equalsIgnoreCase(tipoDocumentoIf.getCodigo()) || "VTA".equalsIgnoreCase(tipoDocumentoIf.getCodigo())))
						idFactura = generarFactura(pedido, idEmpresa, usuario);					
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
			return idFactura;
		}

		public Long generarFactura(PedidoIf pedido, Long idEmpresa, UsuarioIf usuario) {
			Long idFactura = 0L;
			try {
				FacturaData factura = new FacturaData();
				factura.setOficinaId(pedido.getOficinaId());
				factura.setTipodocumentoId(pedido.getTipodocumentoId());
				factura.setFormapagoId(pedido.getFormapagoId());
				factura.setMonedaId(pedido.getMonedaId());
				factura.setClienteoficinaId(pedido.getClienteoficinaId());
				factura.setTipoidentificacionId(pedido.getTipoidentificacionId());
				factura.setIdentificacion(pedido.getIdentificacion());
				factura.setPuntoImpresionId(pedido.getPuntoimpresionId());
				factura.setOrigendocumentoId(pedido.getOrigendocumentoId());
				factura.setVendedorId(pedido.getVendedorId());
				factura.setBodegaId(pedido.getBodegaId());
				factura.setListaPrecioId(pedido.getListaprecioId());
				factura.setPedidoId(pedido.getId());
				java.util.Date fechaCreacion = new java.util.Date();
				factura.setFechaCreacion(pedido.getFechaCreacion());
				factura.setFechaFactura(pedido.getFechaPedido());
				factura.setFechaVencimiento(pedido.getFechaCreacion());
				factura.setUsuarioId(pedido.getUsuarioId());
				factura.setContacto("");
				factura.setDireccion(pedido.getDireccion());
				factura.setTelefono(pedido.getTelefono());
				factura.setObservacion(pedido.getObservacion());
				Collection detallePedidoColeccion = pedidoDetalleLocal.findPedidoDetalleByPedidoId(pedido.getId());
				Vector<PedidoDetalleIf> detalleFacturaColeccion = new Vector<PedidoDetalleIf>();
				Iterator it = detallePedidoColeccion.iterator();
				Double subTotal = 0D, descuentoTotal = 0D, descuentoGlobalTotal = 0D, ivaTotal = 0D, total = 0D, valorBruto = 0D, ivaTemp = 0D;
				while (it.hasNext()) {
					PedidoDetalleIf pedidoDetalleIf = (PedidoDetalleIf) it.next();				
					total += pedidoDetalleIf.getValor().doubleValue();
					descuentoTotal += pedidoDetalleIf.getDescuento().doubleValue() + pedidoDetalleIf.getDescuentoGlobal().doubleValue();
					ivaTotal = ivaTotal + pedidoDetalleIf.getIva().doubleValue();
					detalleFacturaColeccion.add(pedidoDetalleIf);
				}
				factura.setValor(BigDecimal.valueOf(subTotal - descuentoTotal - descuentoGlobalTotal));
				factura.setDescuento(BigDecimal.valueOf(descuentoTotal));
				factura.setDescuentoGlobal(BigDecimal.valueOf(descuentoGlobalTotal));
				factura.setIva(BigDecimal.valueOf(ivaTotal));
				factura.setIce(BigDecimal.valueOf(0.0));
				factura.setOtroImpuesto(BigDecimal.valueOf(0.0));
				factura.setCosto(BigDecimal.valueOf(0.0));
				factura.setEstado("C");
				factura.setPorcentajeComisionAgencia(BigDecimal.ZERO);
				UsuarioIf usuarioif = usuarioLocal.getUsuario(pedido.getUsuarioId());
				idFactura = procesarFactura(factura, detalleFacturaColeccion, idEmpresa, pedido.getOficinaId(), null, null, "", usuario);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return idFactura;
		}



		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Long saveDevolucion(
				Long idFacturaAfectada,
				EmpleadoIf empleado,
				ClienteOficinaIf clienteOficinaIf, 
				ClienteIf clienteIf,
				PuntoImpresionIf puntoImpresionIf,
				Vector<FacturaDetalleIf> detalleDevolucionVector,
				Vector<Vector> TarjetasCollection_detalles,
				Double ivaTotal,
				Double subTotal, 
				Double descuentoTotal, 
				Double descuentoGlobalTotal,
				VentasTrackIf ventasTrack,
				Long idempresa,
				Long idoficina,
				UsuarioIf usuario,
				java.sql.Timestamp fechaDevolucion,
				String apd,
				String atptt,
				boolean isMatriz) throws GenericBusinessException {		

			// Registramos la devolución en la tabla de factura.
			// El campo FACTURAAPLICA_ID establece el id de la factura afectada
			System.out.println("---------------------getFactura");
			FacturaIf facturaAfectada = getFactura(idFacturaAfectada);
			System.out.println("---------------------registrarDevolucion");
			FacturaIf devolucion = registrarDevolucion(facturaAfectada, empleado, clienteOficinaIf, clienteIf, puntoImpresionIf, ivaTotal, subTotal, descuentoTotal, descuentoGlobalTotal, idempresa, idoficina, usuario);
			Long idDevolucion=0L;	
			try {	

				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("ID_EMPRESA", idempresa);
				parametros.put("ID_OFICINA", idoficina);
				parametros.put("EMPLEADO", empleado);
				parametros.put("CLIENTE_OFICINA", clienteOficinaIf);
				parametros.put("USUARIO", usuario);
				System.out.println("---------------------procesarDevolucion");
				idDevolucion = procesarDevolucion(devolucion,detalleDevolucionVector, parametros);	
				devolucion.setId(idDevolucion);
				System.out.println("---------------------obtenerInfoColaYO");
				PosColaIf posColaYO = posColaSessionLocal.obtenerInfoColaYO();
				// SI NO SOY PRINCIPAL

				System.out.println("col--->"+posColaYO.getTipoServer().equalsIgnoreCase("P"));
				if (idDevolucion!=null && !posColaYO.getTipoServer().equalsIgnoreCase("P")) {
					//1) Registramos en VENTAS_TRACK
					System.out.println("---------------------procesarVentasTrack");
					Long transaccion_id=ventastrackLocal.procesarVentasTrack(ventasTrack);
					//2) Registramos en VENTAS_DOCUMENTOS					 
					System.out.println("---------------------registrarVentasDocumentos");
					VentasDocumentosIf pagosDocumentos =  registrarVentasDocumentos(transaccion_id,idDevolucion,"DEVOLUCION");
					System.out.println("---------------------saveVentasDocumentos");
					ventasDocumentosLocal.saveVentasDocumentos(pagosDocumentos);
				}
				System.out.println("---------------------procesarDevolucion");
				movimientoSessionLocal.procesarDevolucion(devolucion, (List<FacturaDetalleIf>)facturaDetalleLocal.findFacturaDetalleByFacturaId(idDevolucion), usuario);
				System.out.println("---------------------setData");
				devolucionMessageLocal.setData(
						facturaAfectada.getPreimpresoNumero(), 
						empleado, 
						clienteOficinaIf, 
						clienteIf, 
						puntoImpresionIf, 
						detalleDevolucionVector,
						TarjetasCollection_detalles,
						ivaTotal, 
						subTotal, 
						descuentoTotal, 
						descuentoGlobalTotal, 
						ventasTrack, 
						idempresa, 
						idoficina,
						usuario,
						devolucion.getFechaFactura().getTime(),
						idDevolucion,
						facturaAfectada.getTipodocumentoId(),
						apd,
						atptt);

				if(isMatriz && TarjetasCollection_detalles.size()>0) {
					//if(tarjetasCollectionDetalles.size()>0)
					System.out.println(">>>>>>>>>>>----------------------------------------------------------------<<<<<<<<<<<<<<<<<<<");
					System.out.println(">>>>>>>>>>>------------- Guardando TARJETA TRANSACCIONES ------------------<<<<<<<<<<<<<<<<<<<");
					System.out.println(">>>>>>>>>>>----------------------------------------------------------------<<<<<<<<<<<<<<<<<<<");
					saveTarjetaTransacciones(TarjetasCollection_detalles, idDevolucion, clienteOficinaIf.getId(), apd, atptt, idempresa);
				}

				try {
					System.out.println("---------------------sendToPrincipalIfPos");
					devolucionMessageLocal.sendToPrincipalIfPos();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (GenericBusinessException e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(e.getMessage());
			}	

			return idDevolucion;
		}

		private FacturaIf registrarDevolucion(FacturaIf facturaAfectada, EmpleadoIf empleado, ClienteOficinaIf clienteOficina, ClienteIf cliente,
				PuntoImpresionIf puntoImpresion, Double ivaTotal,
				Double subTotal, Double descuentoTotal, Double descuentoGlobalTotal, Long idEmpresa, Long idOficina, UsuarioIf usuario) {

			FacturaData data = new FacturaData();
			try {
				data.setOficinaId(idOficina);
				Iterator it = tipoDocumentoLocal.findTipoDocumentoByCodigo(DEVOLUCION).iterator();
				if (it.hasNext()) {
					TipoDocumentoIf tipoDocumentoDevolucion = (TipoDocumentoIf) it.next();
					data.setTipodocumentoId(tipoDocumentoDevolucion.getId());
				} else {
					throw new GenericBusinessException("Se ha producido un error");
				}
				it = formaPagoLocal.findFormaPagoByCodigo(OTRAS_FORMAS_PAGO).iterator();
				if (it.hasNext()) {
					FormaPagoIf formaPago = (FormaPagoIf) it.next();
					data.setFormapagoId(formaPago.getId());	
				} else {
					throw new GenericBusinessException("Se ha producido un error");
				}
				it = monedaLocal.findMonedaByCodigo(MONEDA_DOLAR).iterator();
				if (it.hasNext()) {
					MonedaIf moneda = (MonedaIf) it.next();
					data.setMonedaId(moneda.getId());
				} else {
					throw new GenericBusinessException("Se ha producido un error");
				}
				data.setClienteoficinaId(clienteOficina.getId());
				data.setTipoidentificacionId(cliente.getTipoidentificacionId());
				data.setIdentificacion(cliente.getIdentificacion());
				data.setPuntoImpresionId(puntoImpresion.getId());
				it = origenDocumentoLocal.findOrigenDocumentoByOficinaId(idOficina).iterator();
				if (it.hasNext()) {
					OrigenDocumentoIf origenDocumento = (OrigenDocumentoIf) it.next();
					data.setOrigendocumentoId(origenDocumento.getId());
				} else {
					throw new GenericBusinessException("Se ha producido un error");
				}

				//data.setVendedorId(empleado.getId());
				data.setVendedorId(facturaAfectada.getVendedorId());
				data.setBodegaId(getIdBodega_POS(idOficina));
				data.setListaPrecioId(getIdListaPrecio_POS(idEmpresa));
				java.util.Date fechaCreacion = new java.util.Date();
				data.setFechaCreacion(new java.sql.Timestamp(fechaCreacion.getTime()));
				data.setFechaFactura(new java.sql.Timestamp(fechaCreacion.getTime()));
				data.setFechaVencimiento(new java.sql.Timestamp(fechaCreacion.getTime()));
				data.setUsuarioId(usuario.getId());
				data.setContacto("");
				data.setDireccion(clienteOficina.getDireccion());
				data.setTelefono(clienteOficina.getTelefono());
				String observacion = (facturaAfectada.getPreimpresoNumero()!=null)?facturaAfectada.getPreimpresoNumero():formatoSerial.format(Double.parseDouble(facturaAfectada.getNumero().toString()));
				TipoDocumentoIf tipoDocumentoAfectado = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(facturaAfectada.getTipodocumentoId());
				if (tipoDocumentoAfectado.getCodigo().equals("FAC"))
					observacion = "DEV/F: " + observacion;
				else if (tipoDocumentoAfectado.getCodigo().equals("VTA"))
					observacion = "DEV/NV: " + observacion;
				else
					observacion = "DEV/: " + observacion;
				data.setObservacion(observacion + " " + cliente.getRazonSocial());
				//data.setValor(BigDecimal.valueOf(subTotal - descuentoTotal	- descuentoGlobalTotal));
				data.setValor(BigDecimal.valueOf(subTotal));
				data.setDescuento(BigDecimal.valueOf(descuentoTotal));
				data.setDescuentoGlobal(BigDecimal.valueOf(descuentoGlobalTotal));
				data.setIva(BigDecimal.valueOf(ivaTotal));
				data.setIce(BigDecimal.valueOf(0.0));
				data.setOtroImpuesto(BigDecimal.valueOf(0.0));
				data.setCosto(BigDecimal.valueOf(0.0));
				data.setFacturaaplId(facturaAfectada.getId());
				data.setEstado("C");
				data.setPorcentajeComisionAgencia(BigDecimal.ZERO);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			return data;
		}

		private Long getIdListaPrecio_POS(Long idempresa) {
			Long idListaPrecios = 0L;
			ListaPrecioIf listaPrecioIf = null;
			Iterator it2;
			try {
				it2 = listaPrecioSessionLocal.findListaPrecioByEmpresaId(idempresa).iterator();
				listaPrecioIf = (it2.hasNext()) ? (ListaPrecioIf) it2.next() : null;
			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (listaPrecioIf != null)
				idListaPrecios = listaPrecioIf.getId();
			return (idListaPrecios);
		}

		private Long getIdBodega_POS(Long idoficina) {
			Long idBodega = 0L;
			BodegaIf bodegaIf = null;
			Iterator it2;
			try {
				it2 = bodegaLocal.findBodegaByOficinaId(idoficina)
				.iterator();
				bodegaIf = (it2.hasNext()) ? (BodegaIf) it2.next() : null;
			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (bodegaIf != null)
				idBodega = bodegaIf.getId();
			return (idBodega);
		}

		// Actualiza el pedido(estado y detalles), si es que viene de una
		// transacción cancelada o cotizada, en la cual se guardan datos en la tabla
		// Pedido y pedido detalle
		private boolean actualizarPedido(PedidoIf pedidoAnterior, String tipo,
				Vector<PedidoDetalleIf> ProductosidReunionColeccion_nuevos,
				Vector<PedidoDetalleIf> ProductosidReunionColeccion_eliminados, PedidoIf pedidoModel) throws GenericBusinessException {

			PedidoIf pedido = registrarPedido(pedidoModel, tipo, pedidoAnterior.getCodigo());
			System.out.println("pedido-"+pedido.getId());
			System.out.println("AAA");
			pedidoLocal.actualizarPedido(pedido, ProductosidReunionColeccion_nuevos, pedidoAnterior, ProductosidReunionColeccion_eliminados,null);
			return true;
		}

		public Long savePedido(String cancelar, Vector<PedidoDetalleIf> ProductosidReunionColeccion_proceso, PedidoIf pedidoModel, Long idEmpresa) throws GenericBusinessException {
			PedidoIf pedido = registrarPedido(pedidoModel, cancelar, "");
			// ProductosidReunionColeccion tomo toda la coleccion porque no guardo
			// las formas de pago sino detalles de productos.
			// y en los detalles no guardo los gift cards, porq eso varia de el
			// serial number que haya en el mostrador en ese momento
			// este caso aplica cuando es cotizacion o cancelacion de venta.
			if (cancelar.equals("cotizacion") || cancelar.equals("cancelar")) {
				if (ProductosidReunionColeccion_proceso.size() != 0) {
					for (int l = 0; l < ProductosidReunionColeccion_proceso.size(); l++) {
						PedidoDetalleIf temporal = (PedidoDetalleIf) ProductosidReunionColeccion_proceso.get(l);
						Long id_prod = temporal.getProductoId();
						if (id_prod.equals(getId_tipoproducto("GIFT CARD").toString()))
							ProductosidReunionColeccion_proceso.remove(l);
					}
				}

			}
			Long idPedidoGuardado = pedidoLocal.procesarPedido(pedido, ProductosidReunionColeccion_proceso, idEmpresa,null);	
			return idPedidoGuardado;
		}

		public Long savePedido(PedidoIf pedidoModel, Vector<PedidoDetalleIf> pedidoDetalleModelVector, Long idEmpresa) throws GenericBusinessException {
			Long idPedidoGuardado = pedidoLocal.procesarPedido(pedidoModel, pedidoDetalleModelVector, idEmpresa,null);	
			return idPedidoGuardado;
		}

		private PedidoIf registrarPedido(PedidoIf pedido, String tipo, String cod_anterior) {
			java.util.Date fechaPedido = new java.util.Date();
			if (tipo.equals("actualizar") || tipo.equals("actualizar2")) {
				pedido.setCodigo(cod_anterior);
			} else {
				String codigo = getCodigoPedido(new java.sql.Timestamp(fechaPedido.getTime()));
				pedido.setCodigo(codigo);
			}

			return pedido;
		}

		private String getCodigoPedido(java.sql.Timestamp fechaPedido) {
			String codigo = "";
			String anioPedido = Utilitarios.getYearFromDate(fechaPedido);
			codigo += anioPedido + "-";
			return codigo;
		}

		public Long tipo_producto(Long producto_id) {
			Long id_tipoproducto = 0L;
			ProductoIf productoIf2;
			try {
				productoIf2 = productoLocal.getProducto(producto_id);
				GenericoIf generico = genericoLocal.getGenerico(productoIf2
						.getGenericoId());
				id_tipoproducto = generico.getTipoproductoId();
			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return id_tipoproducto;
		}


		public Long getId_tipoproducto(String tipoproducto) {
			// String nombre_gc="GIFT CARD";/="PERSONALIZACION WEB";
			String nombre_gc = tipoproducto;
			Long id_giftcard = 0L;
			Map aMap = new HashMap();
			aMap.clear();
			aMap.put("nombre", nombre_gc);
			TipoProductoIf tipoProductoIf = null;
			try {			

				Iterator itTipo=null;
				itTipo = tipoproductoLocal.findTipoProductoByQuery(aMap).iterator();		
				if (itTipo.hasNext()) {
					tipoProductoIf = (TipoProductoIf)itTipo.next();
					id_giftcard=tipoProductoIf.getId();
				} 

			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			return (id_giftcard);

		}

		private TipoDocumentoIf findTipoDocumentoByCodigo(String codigo) throws GenericBusinessException {
			TipoDocumentoIf tipoDocumento = null;
			Iterator it = tipoDocumentoLocal.findTipoDocumentoByCodigo(codigo).iterator();
			if (it.hasNext())
				tipoDocumento = (TipoDocumentoIf) it.next();
			return tipoDocumento;
		}

		private AsientoIf generarAsientoFactura(FacturaEJB factura,
				Vector<FacturaDetalleIf> facturaDetalleColeccion,
				Long tipoDocumentoAnteriorId, boolean actualizar,
				UsuarioIf usuario, String tipoReferencia)
		throws GenericBusinessException {
			AsientoIf asiento = null;
			facturaAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", usuario.getEmpresaId());
			parameterMap.put("codigo", "CDF");
			Iterator it = parametroEmpresaSessionLocal.findParametroEmpresaByQuery(parameterMap).iterator();
			boolean contabilizarDescuento = false;
			if (it.hasNext()) {
				ParametroEmpresaIf parametro = (ParametroEmpresaIf) it.next();
				if (parametro.getValor().equals("S"))
					contabilizarDescuento = true;
			}

			for (int i = 0; i < facturaDetalleColeccion.size(); i++) {
				FacturaDetalleIf facturaDetalle = (FacturaDetalleIf) facturaDetalleColeccion.get(i);
				if (i != facturaDetalleColeccion.size() - 1)
					asiento = facturaAsientoAutomaticoHandlerLocal.generarAsientoAutomatico(factura, facturaDetalle,tipoDocumentoAnteriorId, false, actualizar,usuario, tipoReferencia, contabilizarDescuento);
				else if (i == facturaDetalleColeccion.size() - 1)
					asiento = facturaAsientoAutomaticoHandlerLocal.generarAsientoAutomatico(factura, facturaDetalle,tipoDocumentoAnteriorId, true, actualizar,usuario, tipoReferencia, contabilizarDescuento);
			}
			return asiento;
		}

		private AsientoIf generarAsientoDevolucion(FacturaEJB factura,
				Vector<FacturaDetalleIf> facturaDetalleColeccion,
				Long tipoDocumentoAnteriorId, boolean actualizar,
				UsuarioIf usuario, String tipoReferencia)
		throws GenericBusinessException {
			AsientoIf asiento = null;
			devolucionAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", usuario.getEmpresaId());
			parameterMap.put("codigo", "CDF");
			Iterator it = parametroEmpresaSessionLocal.findParametroEmpresaByQuery(parameterMap).iterator();
			boolean contabilizarDescuento = false;
			if (it.hasNext()) {
				ParametroEmpresaIf parametro = (ParametroEmpresaIf) it.next();
				if (parametro.getValor().equals("S"))
					contabilizarDescuento = true;
			}
			for (int i = 0; i < facturaDetalleColeccion.size(); i++) {
				FacturaDetalleIf facturaDetalle = (FacturaDetalleIf) facturaDetalleColeccion.get(i);
				if (i != facturaDetalleColeccion.size() - 1)
					asiento = devolucionAsientoAutomaticoHandlerLocal.generarAsientoAutomatico(factura, facturaDetalle,tipoDocumentoAnteriorId, false, actualizar,usuario, tipoReferencia, contabilizarDescuento);
				else if (i == facturaDetalleColeccion.size() - 1)
					asiento = devolucionAsientoAutomaticoHandlerLocal.generarAsientoAutomatico(factura, facturaDetalle,tipoDocumentoAnteriorId, true, actualizar,usuario, tipoReferencia, contabilizarDescuento);
			}
			return asiento;
		}

		private String getNumeroCartera(java.sql.Timestamp fechaCartera, String codigoTipoDocumento, Long idEmpresa, Long idOficina) {
			String codigo = "";
			try {
				EmpresaIf empresa = empresaLocal.getEmpresa(idEmpresa);
				OficinaIf oficina = oficinaLocal.getOficina(idOficina);
				ServidorIf servidor = (oficina.getServidorId() != null)?servidorLocal.getServidor(oficina.getServidorId()):null;
				String monthCartera = utilitariosLocal.getMonthFromDate(fechaCartera);
				String anioCartera = utilitariosLocal.getYearFromDate(fechaCartera);
				codigo = empresa.getCodigo() + "-";
				if (servidor != null)
					codigo += servidor.getCodigo() + "-";
				codigo += codigoTipoDocumento + "-";
				nuevaCodificacionActiva = (Double.parseDouble(anioCartera) <= 2008) ? false	: true;
				if (nuevaCodificacionActiva)	codigo += monthCartera + "-";
				codigo += anioCartera + "-";
				return codigo;
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			return null;
		}

		private int getMaximoNumeroCartera(CarteraIf modelCartera) {
			String queryString = "select max(codigo) from CarteraEJB c where c.codigo like '"
				+ modelCartera.getCodigo() + "%'";
			Query query = manager.createQuery(queryString);
			String maxCodigoCartera = query.getResultList().toString();
			queryString = "select max(codigo) from LogCarteraEJB lc where lc.codigo like '"
				+ modelCartera.getCodigo() + "%'";
			query = manager.createQuery(queryString);
			String maxCodigoLog = query.getResultList().toString();
			String codigo = (maxCodigoCartera.compareTo(maxCodigoLog) >= 0 || maxCodigoLog
					.equals("[null]")) ? maxCodigoCartera : maxCodigoLog;
			int codigoCartera = 1;
			if (!codigo.equals("[null]")) {
				codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
				codigoCartera = Integer.parseInt(codigo.split(modelCartera
						.getCodigo())[1]) + 1;
			}
			return codigoCartera;
		}

		private Vector<FacturaDetalleIf> collectionToVector(Collection coleccion) {
			Iterator it = coleccion.iterator();
			Vector<FacturaDetalleIf> vector = new Vector<FacturaDetalleIf>();
			while (it.hasNext()) {
				FacturaDetalleIf detalleFactura = (FacturaDetalleIf) it.next();
				if (detalleFactura != null)
					vector.add(detalleFactura);
			}

			return vector;
		}

		/*
		 * @TransactionAttribute(TransactionAttributeType.REQUIRED) public Map
		 * procesarAnularFactura(FacturaIf model, Vector<FacturaDetalleIf>
		 * modelDetalleList, Long idEmpresa) throws GenericBusinessException { Map
		 * beansMap = new HashMap(); String ESTADO_ANULADO = "A";
		 * 
		 * try { model.setEstado(ESTADO_ANULADO); FacturaEJB factura =
		 * registrarFactura(model); manager.merge(factura);
		 * 
		 * //(3) Registramos la cartera de la nota de crédito por anulación de
		 * factura CarteraData modelCarteraNotaCredito = new CarteraData();
		 * NumeradoresIf numeradorCartera = (NumeradoresIf)
		 * numeradoresLocal.findNumeradoresByNombreTablaAndByEmpresaId("CARTERA",
		 * idEmpresa).iterator().next(); int numeroCartera =
		 * numeradorCartera.getUltimoValor().intValue() + 1;
		 * numeradorCartera.setUltimoValor(new BigDecimal(numeroCartera));
		 * manager.merge(numeradorCartera); Calendar now = Calendar.getInstance();
		 * int year = now.get(Calendar.YEAR);
		 * modelCarteraNotaCredito.setCodigo("CAR-" + String.valueOf(year) + "-" +
		 * formatoSerial.format(numeroCartera));
		 * modelCarteraNotaCredito.setFechaEmision(new
		 * java.sql.Date(now.getTimeInMillis())); CarteraEJB cartera =
		 * registrarCarteraNotaCredito(modelCarteraNotaCredito, factura);
		 * manager.persist(cartera); beansMap.put("CARTERA", cartera);
		 * 
		 * int count = 1; CarteraDetalleData carteraDetalleModel = new
		 * CarteraDetalleData();
		 * carteraDetalleModel.setCarteraId(cartera.getPrimaryKey());
		 * CarteraDetalleEJB carteraDetalle =
		 * registrarCarteraDetalleNotaCredito(carteraDetalleModel, cartera, model);
		 * carteraDetalle.setSecuencial(count); manager.persist(carteraDetalle);
		 * beansMap.put("CARTERA_DETALLE", carteraDetalle);
		 * 
		 * Map parameterMap = new HashMap(); parameterMap.put("tipodocumentoId",
		 * model.getTipodocumentoId()); parameterMap.put("referenciaId",
		 * model.getId()); Iterator carteraFacturaIterator =
		 * carteraLocal.findCarteraByQuery(parameterMap).iterator(); if
		 * (carteraFacturaIterator.hasNext()) { CarteraIf carteraFacturaModel =
		 * (CarteraIf) carteraFacturaIterator.next(); Iterator
		 * carteraDetalleFacturaIterator =
		 * carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraFacturaModel.getId()).iterator();
		 * while (carteraDetalleFacturaIterator.hasNext()) { CarteraDetalleIf
		 * carteraDetalleFacturaModel = (CarteraDetalleIf)
		 * carteraDetalleFacturaIterator.next(); CarteraAfectaData
		 * carteraAfectaModel = new CarteraAfectaData();
		 * carteraAfectaModel.setCarteradetalleId(carteraDetalle.getId());
		 * carteraAfectaModel.setCarteraafectaId(carteraDetalleFacturaModel.getId());
		 * carteraAfectaModel.setValor(carteraDetalleFacturaModel.getValor());
		 * carteraAfectaModel.setUsuarioId(model.getUsuarioId()); CarteraAfectaEJB
		 * carteraAfecta = registrarCarteraAfecta(carteraAfectaModel);
		 * manager.merge(carteraAfecta);
		 * carteraDetalleFacturaModel.setSaldo(BigDecimal.ZERO); CarteraDetalleEJB
		 * carteraDetalleFactura =
		 * registrarCarteraDetalle(carteraDetalleFacturaModel);
		 * manager.merge(carteraDetalleFactura); }
		 * 
		 * carteraFacturaModel.setSaldo(BigDecimal.ZERO); CarteraEJB carteraFactura =
		 * registrarCartera(carteraFacturaModel); manager.merge(carteraFactura); } }
		 * catch (Exception e) { ctx.setRollbackOnly(); e.printStackTrace(); throw
		 * new GenericBusinessException("Error al insertar información en
		 * Factura-FacturaDetalle"); } return beansMap; }
		 */

		private PedidoDetalleIf verificarStockPorLote(
				PedidoDetalleIf pedidoDetalle, Long idBodega)
		throws GenericBusinessException {
			ProductoIf producto = productoLocal.getProducto(pedidoDetalle
					.getProductoId());
			GenericoIf generico = genericoLocal.getGenerico(producto
					.getGenericoId());
			// DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date fechaHoy = new java.util.Date();
			// String fecha = dateFormatter.format(fechaHoy);
			int restante = pedidoDetalle.getCantidadpedida().intValue();
			Long idProducto = producto.getId();
			Vector<StockIf> stockIfVector = new Vector<StockIf>();

			/*
			 * if (generico.getServicio().equals("N")) { LoteIf loteIf = null; int
			 * cantidadStock = 0; Collection lotes;
			 * 
			 * if (pedidoDetalle.getLoteId() == null) lotes =
			 * loteLocal.findLoteByProductoIdAndEstadoAndFecha(idProducto, new
			 * Date(fechaHoy.getTime())); else lotes =
			 * loteLocal.findLoteById(pedidoDetalle.getLoteId());
			 * 
			 * Iterator itLotes = lotes.iterator();
			 * 
			 * stockIfVector.clear();
			 * 
			 * while (itLotes.hasNext()) { loteIf = (LoteIf) itLotes.next();
			 * Collection stocks =
			 * stockLocal.findStockByIdLoteAndIdBodega(loteIf.getId(),idBodega);
			 * Iterator itStocks = stocks.iterator();
			 * 
			 * while (itStocks.hasNext()) { StockIf stockIf = (StockIf)
			 * itStocks.next(); // valido si el stock de cual estoy leyendo los
			 * datos tiene cantidades mayores a cero if
			 * (stockIf.getCantidad().intValue() > 0) { // a cantidad stock le
			 * agrego la cantidad que posee el stock cantidadStock =
			 * stockIf.getCantidad().intValue(); // disminuyo la cantidad restante
			 * que aun falta por facturar restante = restante - cantidadStock; //
			 * esto se da si el stock satisface la cantidad pedida if (restante <=
			 * 0) { Double cantidadRestante =
			 * Double.parseDouble(String.valueOf(restante));
			 * stockIf.setCantidad(BigDecimal.valueOf(cantidadRestante).abs());
			 * break; } else stockIf.setCantidad(BigDecimal.ZERO);
			 * 
			 * stockIfVector.add(stockIf); } // si el restante es menor a 0
			 * significa que lo que tenía en el stock era suficiente // para poder
			 * facturar por lo tanto salgo del while de los lotes if (restante <= 0)
			 * break; }
			 * 
			 * if (restante <= 0) { restante = 0; break; } } } else
			 */
			restante = 0;
			Double cantidadFacturada = pedidoDetalle.getCantidadpedida()
			.doubleValue()
			- restante;
			pedidoDetalle.setCantidad(BigDecimal.valueOf(cantidadFacturada));

			if (pedidoDetalle.getCantidad().doubleValue() > 0.0) {
				for (StockIf modelStock : stockIfVector) {
					StockEJB stock = registrarStock(modelStock);
					manager.merge(stock);
				}
			}

			return pedidoDetalle;
		}

		private FacturaEJB registrarFactura(FacturaIf model,
				List<PedidoDetalleIf> modelDetalleList, boolean esFacturaReembolso) {
			FacturaEJB factura = new FacturaEJB();
			// Double iva = 0.12;
			Double valorFactura = sumarValoresParcialesPedidoDetalle(modelDetalleList);
			Double descuentoFactura = sumarDescuentosParcialesPedidoDetalle(modelDetalleList);
			Double descuentoGlobalFactura = sumarDescuentosGlobalesParcialesPedidoDetalle(modelDetalleList);
			Double ivaFactura = 0.0;

			if (!esFacturaReembolso)
				ivaFactura = sumarIvaParcialesPedidoDetalle(modelDetalleList);

			factura.setId(model.getId());
			factura.setOficinaId(model.getOficinaId());
			factura.setTipodocumentoId(model.getTipodocumentoId());
			factura.setNumero(model.getNumero());
			factura.setClienteoficinaId(model.getClienteoficinaId());
			factura.setTipoidentificacionId(model.getTipoidentificacionId());
			factura.setIdentificacion(model.getIdentificacion());
			factura.setFormapagoId(model.getFormapagoId());
			factura.setMonedaId(model.getMonedaId());
			factura.setPuntoImpresionId(model.getPuntoImpresionId());
			factura.setOrigendocumentoId(model.getOrigendocumentoId());
			factura.setVendedorId(model.getVendedorId());
			factura.setBodegaId(model.getBodegaId());
			factura.setPedidoId(model.getPedidoId());
			factura.setListaPrecioId(model.getListaPrecioId());
			factura.setFechaCreacion(model.getFechaCreacion());
			factura.setFechaFactura(model.getFechaFactura());
			factura.setFechaVencimiento(model.getFechaVencimiento());
			factura.setUsuarioId(model.getUsuarioId());
			factura.setContacto(model.getContacto());
			factura.setDireccion(model.getDireccion());
			factura.setTelefono(model.getTelefono());
			factura.setPreimpresoNumero(model.getPreimpresoNumero());
			factura.setValor(BigDecimal.valueOf(valorFactura));
			factura.setDescuento(BigDecimal.valueOf(descuentoFactura));
			factura.setIva(BigDecimal.valueOf(ivaFactura));
			factura.setIce(model.getIce());
			factura.setOtroImpuesto(model.getOtroImpuesto());
			factura.setCosto(model.getCosto());
			factura.setObservacion(model.getObservacion());
			factura.setEstado(model.getEstado());
			factura.setFacturaaplId(model.getFacturaaplId());
			factura.setDescuentoGlobal(BigDecimal.valueOf(descuentoGlobalFactura));
			factura.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
			factura.setEquipoId(model.getEquipoId());
			factura.setClienteNegociacionId(model.getClienteNegociacionId());
			factura.setTipoNegociacion(model.getTipoNegociacion());
			factura.setPorcentajeNegociacionDirecta(model.getPorcentajeNegociacionDirecta());
			factura.setPorcentajeDescuentoNegociacion(model.getPorcentajeDescuentoNegociacion());
			factura.setAutorizacionSap(model.getAutorizacionSap());
			factura.setDescuentosVarios(model.getDescuentosVarios());
			
			return factura;
		}

		private FacturaEJB registrarFactura(FacturaIf model) {
			FacturaEJB factura = new FacturaEJB();
			factura.setId(model.getId());
			factura.setOficinaId(model.getOficinaId());
			factura.setTipodocumentoId(model.getTipodocumentoId());
			factura.setNumero(model.getNumero());
			factura.setClienteoficinaId(model.getClienteoficinaId());
			factura.setTipoidentificacionId(model.getTipoidentificacionId());
			factura.setIdentificacion(model.getIdentificacion());
			factura.setFormapagoId(model.getFormapagoId());
			factura.setMonedaId(model.getMonedaId());
			factura.setPuntoImpresionId(model.getPuntoImpresionId());
			factura.setOrigendocumentoId(model.getOrigendocumentoId());
			factura.setVendedorId(model.getVendedorId());
			factura.setBodegaId(model.getBodegaId());
			factura.setPedidoId(model.getPedidoId());
			factura.setListaPrecioId(model.getListaPrecioId());
			factura.setFechaCreacion(model.getFechaCreacion());
			factura.setFechaFactura(model.getFechaFactura());
			factura.setFechaVencimiento(model.getFechaVencimiento());
			factura.setUsuarioId(model.getUsuarioId());
			factura.setContacto(model.getContacto());
			factura.setDireccion(model.getDireccion());
			factura.setTelefono(model.getTelefono());
			factura.setPreimpresoNumero(model.getPreimpresoNumero());
			factura.setValor(model.getValor());
			factura.setDescuento(model.getDescuento());

			try {
				TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(model.getTipodocumentoId());
				if ((tipoDocumento.getReembolso() != null) && tipoDocumento.getReembolso().equals("S")) {
					factura.setIva(new BigDecimal(0));
				} else {
					factura.setIva(model.getIva());
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			factura.setIce(model.getIce());
			factura.setOtroImpuesto(model.getOtroImpuesto());
			factura.setCosto(model.getCosto());
			factura.setObservacion(model.getObservacion());
			factura.setEstado(model.getEstado());
			factura.setFacturaaplId(model.getFacturaaplId());
			factura.setDescuentoGlobal(model.getDescuentoGlobal());
			factura.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
			factura.setEquipoId(model.getEquipoId());
			factura.setClienteNegociacionId(model.getClienteNegociacionId());
			factura.setTipoNegociacion(model.getTipoNegociacion());
			factura.setPorcentajeNegociacionDirecta(model.getPorcentajeNegociacionDirecta());
			factura.setPorcentajeDescuentoNegociacion(model.getPorcentajeDescuentoNegociacion());
			factura.setAutorizacionSap(model.getAutorizacionSap());
			factura.setDescuentosVarios(model.getDescuentosVarios());
			
			return factura;
		}
		
		/*private PresupuestoFacturacionEJB registrarPresupuestoFacturacion(PresupuestoFacturacionIf model) {
			PresupuestoFacturacionEJB presupuestoFacturacion = new PresupuestoFacturacionEJB();
			presupuestoFacturacion.setEstado(estado)
			presupuestoFacturacion.setIva(iva)
			presupuestoFacturacion.setPedidoId(pedidoId)
			presupuestoFacturacion.setPorcentajeComisionPura(porcentajeComisionPura)
			presupuestoFacturacion.setPorcentajeNegociacionDirecta(porcentajeNegociacionDirecta)
			presupuestoFacturacion.setPorcentajeDescuento(porcentajeDescuento)
			presupuestoFacturacion.setPresupuestoDetalleId(presupuestoDetalleId)
			presupuestoFacturacion.setPresupuestoId(presupuestoId)
			presupuestoFacturacion.setSubtotal(subtotal)
			presupuestoFacturacion.setTotal(total)
						
			return presupuestoFacturacion;
		}*/

		private FacturaDetalleEJB registrarDevolucionDetalle(FacturaDetalleIf modelDetalle, TipoDocumentoIf tipoDocumentoDevolucion) {
			FacturaDetalleEJB facturaDetalle = new FacturaDetalleEJB();
			try {
				facturaDetalle.setId(modelDetalle.getId());
				Iterator it = documentoLocal.findDocumentoByTipoDocumentoId(tipoDocumentoDevolucion.getId()).iterator();
				if (it.hasNext()) {
					DocumentoIf documentoDevolucion = (DocumentoIf) it.next();
					facturaDetalle.setDocumentoId(documentoDevolucion.getId());
				} else {
					throw new GenericBusinessException("Error al guardar la devolución");
				}
				facturaDetalle.setFacturaId(modelDetalle.getFacturaId());
				facturaDetalle.setProductoId(modelDetalle.getProductoId());
				facturaDetalle.setDescripcion(modelDetalle.getDescripcion());
				facturaDetalle.setMotivodocumentoId(modelDetalle.getMotivodocumentoId());
				facturaDetalle.setPrecio(modelDetalle.getPrecio());
				facturaDetalle.setPrecioReal(modelDetalle.getPrecioReal());
				facturaDetalle.setIce(modelDetalle.getIce());
				facturaDetalle.setOtroImpuesto(modelDetalle.getOtroImpuesto());
				facturaDetalle.setCantidadDevuelta(modelDetalle.getCantidadDevuelta());
				ProductoIf producto = productoLocal.getProducto(modelDetalle.getProductoId());
				GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
				facturaDetalle.setCosto(producto.getCosto());
				facturaDetalle.setLineaId(generico.getLineaId());
				Double ivaDetalle = 0.0;
				Double precioReal = modelDetalle.getPrecioReal().doubleValue();
				Double subTotal = modelDetalle.getCantidadDevuelta().doubleValue()* modelDetalle.getPrecioReal().doubleValue();
				Double cantidadFacturada = modelDetalle.getCantidadDevuelta().doubleValue();
				ivaDetalle = modelDetalle.getIva().doubleValue();
				facturaDetalle.setValor(BigDecimal.valueOf(subTotal.doubleValue()));
				facturaDetalle.setIva(BigDecimal.valueOf(ivaDetalle.doubleValue()));
				facturaDetalle.setDescuento(modelDetalle.getDescuento());
				facturaDetalle.setDescuentoGlobal(modelDetalle.getDescuentoGlobal());
				facturaDetalle.setCantidad(BigDecimal.valueOf(cantidadFacturada));
				facturaDetalle.setLoteId(modelDetalle.getLoteId());
				facturaDetalle.setComprasReembolsoAsociadas(modelDetalle.getComprasReembolsoAsociadas());
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			return facturaDetalle;
		}

		private FacturaDetalleEJB registrarFacturaDetalle(FacturaDetalleIf modelDetalle, PedidoDetalleIf modelPedidoDetalle, 
				double porcentajeComision, PresupuestoIf presupuesto, boolean facturaComision) {
			FacturaDetalleEJB facturaDetalle = new FacturaDetalleEJB();
			Double iva = 0.12;
			facturaDetalle.setId(modelDetalle.getId());
			facturaDetalle.setDocumentoId(modelPedidoDetalle.getDocumentoId());
			facturaDetalle.setFacturaId(modelDetalle.getFacturaId());
			facturaDetalle.setProductoId(modelPedidoDetalle.getProductoId());
			facturaDetalle.setDescripcion(modelPedidoDetalle.getDescripcion());
			facturaDetalle.setMotivodocumentoId(modelPedidoDetalle.getMotivodocumentoId());
			facturaDetalle.setPrecio(modelPedidoDetalle.getPrecio());
			facturaDetalle.setPrecioReal(modelPedidoDetalle.getPrecioreal());
			facturaDetalle.setIce(modelPedidoDetalle.getIce());
			facturaDetalle.setOtroImpuesto(modelPedidoDetalle.getOtroimpuesto());
			facturaDetalle.setCantidadDevuelta(BigDecimal.ZERO);

			try {
				ProductoIf producto = productoLocal.getProducto(modelPedidoDetalle.getProductoId());
				GenericoIf generico = genericoLocal.getGenerico(producto.getGenericoId());
				facturaDetalle.setCosto(producto.getCosto());
				facturaDetalle.setLineaId(generico.getLineaId());
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			Double ivaDetalle = 0.0;
			Double precioReal = modelPedidoDetalle.getPrecioreal().doubleValue();
			Double cantidadPedida = modelPedidoDetalle.getCantidadpedida().doubleValue() * modelPedidoDetalle.getPrecioreal().doubleValue();
			Double cantidadFacturada = modelPedidoDetalle.getCantidad().doubleValue();
			Double subTotal = cantidadFacturada * precioReal;
			Double descuentoPor = 0.0;
			Double descuentosVariosPor = 0.0;
			Double descuentoGlobalPor = 0.0;
			if (cantidadPedida != 0) {
				descuentoPor = (modelPedidoDetalle.getDescuento().doubleValue() * 100) / (cantidadPedida);
				descuentosVariosPor = modelPedidoDetalle.getPorcentajeDescuentosVarios().doubleValue();
				descuentoGlobalPor = (modelPedidoDetalle.getDescuentoGlobal().doubleValue() * 100) / (cantidadPedida);
			}

			Double descuento = 0D;
			Double descuentosVarios = 0D;
			Double descuentoGlobal = 0D;
			Double comision = 0D;
			
			if(facturaComision){
				descuento = 0D;
				descuentoGlobal = 0D;
				comision = 0D;
			}else{
				descuento = (presupuesto == null || (presupuesto != null && !facturaComision)) ? subTotal * (descuentoPor / 100) : presupuesto.getDescuento().doubleValue();
				descuentosVarios = subTotal * (descuentosVariosPor / 100);
				descuentoGlobal = (presupuesto == null || (presupuesto != null && !facturaComision)) ? subTotal * (descuentoGlobalPor / 100)	: 0D;
				comision = (presupuesto == null || (presupuesto != null && !facturaComision)) ? ((subTotal - descuento - descuentosVarios - descuentoGlobal) * porcentajeComision) / 100D : 0D;
			}
			
			if (modelPedidoDetalle.getIva().doubleValue() > 0)
				ivaDetalle = (presupuesto == null || (presupuesto != null && !facturaComision)) ? ((subTotal - descuento - descuentosVarios - descuentoGlobal + comision) * iva) : modelPedidoDetalle.getIva().doubleValue();
			else
				ivaDetalle = 0.0;

			facturaDetalle.setValor(BigDecimal.valueOf(subTotal.doubleValue()));
			facturaDetalle.setIva(BigDecimal.valueOf(ivaDetalle.doubleValue()));
			facturaDetalle.setDescuento(BigDecimal.valueOf(descuento.doubleValue()));
			facturaDetalle.setDescuentoGlobal(BigDecimal.valueOf(descuentoGlobal.doubleValue()));
			facturaDetalle.setCantidad(BigDecimal.valueOf(cantidadFacturada));
			facturaDetalle.setLoteId(modelPedidoDetalle.getLoteId());
			facturaDetalle.setComprasReembolsoAsociadas(modelPedidoDetalle.getComprasReembolsoAsociadas());
			facturaDetalle.setPorcentajeDescuentosVarios(modelPedidoDetalle.getPorcentajeDescuentosVarios());

			return facturaDetalle;
		}

		private CarteraEJB registrarCartera(CarteraIf modelCartera) {
			CarteraEJB cartera = new CarteraEJB();
			cartera.setId(modelCartera.getId());
			cartera.setTipo(modelCartera.getTipo());
			cartera.setOficinaId(modelCartera.getOficinaId());
			cartera.setTipodocumentoId(modelCartera.getTipodocumentoId());
			cartera.setCodigo(modelCartera.getCodigo());
			cartera.setReferenciaId(modelCartera.getReferenciaId());
			cartera.setClienteoficinaId(modelCartera.getClienteoficinaId());
			cartera.setPreimpreso(modelCartera.getPreimpreso());
			cartera.setUsuarioId(modelCartera.getUsuarioId());
			cartera.setVendedorId(modelCartera.getVendedorId());
			cartera.setMonedaId(modelCartera.getMonedaId());
			cartera.setFechaEmision(modelCartera.getFechaEmision());
			cartera.setValor(utilitariosLocal.redondeoValor(modelCartera.getValor()));
			cartera.setSaldo(utilitariosLocal.redondeoValor(modelCartera.getSaldo()));
			cartera.setFechaUltimaActualizacion(modelCartera.getFechaUltimaActualizacion());
			cartera.setEstado(modelCartera.getEstado());
			cartera.setComentario(modelCartera.getComentario());

			return cartera;
		}

		private CarteraEJB registrarCartera(LogCarteraIf modelCartera) {
			CarteraEJB cartera = new CarteraEJB();
			cartera.setTipo(modelCartera.getTipo());
			cartera.setOficinaId(modelCartera.getOficinaId());
			cartera.setTipodocumentoId(modelCartera.getTipodocumentoId());
			cartera.setCodigo(modelCartera.getCodigo());
			cartera.setReferenciaId(modelCartera.getReferenciaId());
			cartera.setClienteoficinaId(modelCartera.getClienteoficinaId());
			cartera.setPreimpreso(modelCartera.getPreimpreso());
			cartera.setUsuarioId(modelCartera.getUsuarioId());
			cartera.setVendedorId(modelCartera.getVendedorId());
			cartera.setMonedaId(modelCartera.getMonedaId());
			cartera.setFechaEmision(utilitariosLocal.fromSqlDateToTimestamp(modelCartera.getFechaEmision()));
			cartera.setValor(utilitariosLocal.redondeoValor(modelCartera.getValor()));
			cartera.setSaldo(utilitariosLocal.redondeoValor(modelCartera.getSaldo()));
			if(modelCartera.getFechaUltimaActualizacion() != null)
				cartera.setFechaUltimaActualizacion(utilitariosLocal.fromSqlDateToTimestamp(modelCartera.getFechaUltimaActualizacion()));
			cartera.setEstado(modelCartera.getEstado());
			cartera.setComentario(modelCartera.getComentario());

			return cartera;
		}

		private AsientoDetalleEJB registrarAsientoDetalle(
				LogAsientoDetalleIf modelDetalle) {
			AsientoDetalleEJB asientoDetalle = new AsientoDetalleEJB();
			//se tiene que setear el id como null para que cree nuevos detalles
			//caso contrario reescribira asientos detalle guardados dando un gran error.
			asientoDetalle.setId(null);
			asientoDetalle.setCuentaId(modelDetalle.getCuentaId());
			asientoDetalle.setAsientoId(modelDetalle.getLogAsientoId());
			asientoDetalle.setReferencia(modelDetalle.getReferencia());
			asientoDetalle.setGlosa(modelDetalle.getGlosa());
			asientoDetalle.setCentrogastoId(modelDetalle.getCentrogastoId());
			asientoDetalle.setEmpleadoId(modelDetalle.getEmpleadoId());
			asientoDetalle.setDepartamentoId(modelDetalle.getDepartamentoId());
			asientoDetalle.setLineaId(modelDetalle.getLineaId());
			asientoDetalle.setClienteId(modelDetalle.getClienteId());
			asientoDetalle.setDebe(modelDetalle
					.getDebe());
			asientoDetalle.setHaber(modelDetalle
					.getHaber());
			return asientoDetalle;
		}

		private CarteraEJB registrarCartera(CarteraIf modelCartera,
				CarteraIf carteraAnterior, FacturaIf modelFactura,
				boolean actualizarCodigoCartera, Long idOficina) {
			CarteraEJB cartera = new CarteraEJB();
			String TIPO_CARTERA = "C";
			String ESTADO_CARTERA = "N";

			try {
				Long usuarioId = ((UsuarioIf) usuarioLocal.findUsuarioById(modelFactura.getUsuarioId()).iterator().next()).getId();
				CajaIf cajaIf = (CajaIf) cajaLocal.findCajaByUsuarioId(usuarioId).iterator().next();
				OficinaIf oficinaIf = oficinaLocal.getOficina(modelFactura.getOficinaId());

				if (cajaIf != null) {
					modelCartera.setPreimpreso(modelFactura.getPreimpresoNumero());
				}

				cartera.setId(modelCartera.getId());
				cartera.setTipo(TIPO_CARTERA);
				cartera.setOficinaId(modelFactura.getOficinaId());
				TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(modelFactura.getTipodocumentoId());
				cartera.setTipodocumentoId(tipoDocumento.getId());
				EmpresaIf empresa = empresaLocal.getEmpresa(tipoDocumento.getEmpresaId());
				nuevaCodificacionActiva = (modelFactura.getFechaFactura().getYear() + 1900 <= 2008) ? false	: true;
				if (nuevaCodificacionActiva && actualizarCodigoCartera) {
					String unNumeroCartera = getNumeroCartera(modelFactura.getFechaFactura(), "FAC", empresa.getId(), idOficina);
					cartera.setCodigo(unNumeroCartera);
					cartera.setCodigo(cartera.getCodigo() + formatoSerial.format(getMaximoNumeroCartera(cartera)));
				} else if (nuevaCodificacionActiva && !actualizarCodigoCartera) {
					cartera.setCodigo(carteraAnterior != null ? carteraAnterior.getCodigo() : "");
				} else if (!nuevaCodificacionActiva && actualizarCodigoCartera) {
					String unNumeroCartera = getNumeroCartera(modelFactura.getFechaFactura(), "FAC", empresa.getId(), idOficina);
					cartera.setCodigo(unNumeroCartera);
					cartera.setCodigo(cartera.getCodigo() + formatoSerial.format(getMaximoNumeroCartera(cartera)));
				} else if (!nuevaCodificacionActiva && !actualizarCodigoCartera)
					cartera.setCodigo(carteraAnterior != null ? carteraAnterior.getCodigo() : "");

				cartera.setReferenciaId(modelFactura.getId());
				cartera.setClienteoficinaId(modelFactura.getClienteoficinaId());
				cartera.setPreimpreso(modelCartera.getPreimpreso());
				cartera.setUsuarioId(modelFactura.getUsuarioId());
				cartera.setVendedorId(modelFactura.getVendedorId());
				cartera.setMonedaId(modelFactura.getMonedaId());
				cartera.setFechaEmision(modelFactura.getFechaFactura());

				double valorFactura = modelFactura.getValor().doubleValue();
				double descuentoFactura = modelFactura.getDescuento().doubleValue();
				double descuentosVarios = modelFactura.getDescuentosVarios().doubleValue();
				double descuentoGlobalFactura = modelFactura.getDescuentoGlobal().doubleValue();
				double descuentoTotalFactura = descuentoFactura + descuentosVarios + descuentoGlobalFactura;
				double porcentajeComision = modelFactura.getPorcentajeComisionAgencia().doubleValue();
				double comision = ((valorFactura - descuentoTotalFactura) * porcentajeComision) / 100D;
				double ivaFactura = modelFactura.getIva().doubleValue();
				double iceFactura = modelFactura.getIce().doubleValue();
				double otroImpuesto = modelFactura.getOtroImpuesto().doubleValue();
				double impuestoTotalFactura = ivaFactura + iceFactura + otroImpuesto;
				double valorCartera = valorFactura - descuentoTotalFactura + impuestoTotalFactura + comision;
				cartera.setValor(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorCartera)));
				cartera.setSaldo(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorCartera)));

				cartera.setFechaUltimaActualizacion(modelCartera.getFechaUltimaActualizacion());
				cartera.setEstado(ESTADO_CARTERA);
				cartera.setComentario(modelFactura.getObservacion());
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			return cartera;
		}

		private CarteraDetalleEJB registrarCarteraDetalle(
				CarteraDetalleIf carteraDetalleModel) {
			CarteraDetalleEJB carteraDetalle = new CarteraDetalleEJB();
			carteraDetalle.setId(carteraDetalleModel.getId());
			carteraDetalle.setCarteraId(carteraDetalleModel.getCarteraId());
			carteraDetalle.setDocumentoId(carteraDetalleModel.getDocumentoId());
			carteraDetalle.setLineaId(carteraDetalleModel.getLineaId());
			carteraDetalle.setPreimpreso(carteraDetalleModel.getPreimpreso());
			carteraDetalle.setFechaCreacion(carteraDetalleModel.getFechaCreacion());
			carteraDetalle.setFechaCartera(carteraDetalleModel.getFechaCartera());
			carteraDetalle.setFechaVencimiento(carteraDetalleModel
					.getFechaVencimiento());
			carteraDetalle.setFechaUltimaActualizacion(carteraDetalleModel
					.getFechaUltimaActualizacion());
			carteraDetalle.setValor(utilitariosLocal
					.redondeoValor(carteraDetalleModel.getValor()));
			carteraDetalle.setSaldo(utilitariosLocal
					.redondeoValor(carteraDetalleModel.getSaldo()));
			carteraDetalle.setCartera(carteraDetalleModel.getCartera());
			return carteraDetalle;
		}

		private CarteraDetalleEJB registrarCarteraDetalle(LogCarteraDetalleIf carteraDetalleModel) {
			CarteraDetalleEJB carteraDetalle = new CarteraDetalleEJB();
			carteraDetalle.setId(carteraDetalleModel.getId());
			carteraDetalle.setCarteraId(carteraDetalleModel.getLogCarteraId());
			carteraDetalle.setDocumentoId(carteraDetalleModel.getDocumentoId());
			carteraDetalle.setLineaId(carteraDetalleModel.getLineaId());
			carteraDetalle.setPreimpreso(carteraDetalleModel.getPreimpreso());
			carteraDetalle.setFechaCreacion(carteraDetalleModel.getFechaCreacion());
			carteraDetalle.setFechaCartera(carteraDetalleModel.getFechaCartera());
			carteraDetalle.setFechaVencimiento(carteraDetalleModel.getFechaVencimiento());
			carteraDetalle.setFechaUltimaActualizacion(carteraDetalleModel.getFechaUltimaActualizacion());
			carteraDetalle.setValor(utilitariosLocal.redondeoValor(carteraDetalleModel.getValor()));
			carteraDetalle.setSaldo(utilitariosLocal.redondeoValor(carteraDetalleModel.getSaldo()));
			carteraDetalle.setCartera(carteraDetalleModel.getCartera());
			return carteraDetalle;
		}

		private CarteraDetalleEJB registrarCarteraDetalle(
				CarteraDetalleIf carteraDetalleModel,
				FacturaDetalleIf facturaDetalleModel, FacturaIf facturaModel,
				Double valorFacturas) {
			CarteraDetalleEJB carteraDetalle = new CarteraDetalleEJB();
			String OPCION_SI = "S";

			try {
				Long usuarioId = ((UsuarioIf) usuarioLocal.findUsuarioById(facturaModel.getUsuarioId()).iterator().next()).getId();
				CajaIf cajaIf = (CajaIf) cajaLocal.findCajaByUsuarioId(usuarioId).iterator().next();
				OficinaIf oficinaIf = oficinaLocal.getOficina(facturaModel.getOficinaId());

				if (cajaIf != null) {
					carteraDetalleModel.setPreimpreso(facturaModel.getPreimpresoNumero());
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			carteraDetalle.setId(carteraDetalleModel.getId());
			carteraDetalle.setCarteraId(carteraDetalleModel.getCarteraId());
			carteraDetalle.setDocumentoId(facturaDetalleModel.getDocumentoId());
			carteraDetalle.setLineaId(facturaDetalleModel.getLineaId());
			carteraDetalle.setPreimpreso(carteraDetalleModel.getPreimpreso());
			java.util.Date fechaHoy = new java.util.Date();
			carteraDetalle.setFechaCreacion(new java.sql.Date(facturaModel.getFechaFactura().getTime()));
			carteraDetalle.setFechaCartera(new java.sql.Date(facturaModel.getFechaFactura().getTime()));
			carteraDetalle.setFechaVencimiento(new java.sql.Date(facturaModel.getFechaVencimiento().getTime()));
			carteraDetalle.setFechaUltimaActualizacion(new java.sql.Date(fechaHoy.getYear(), fechaHoy.getMonth(), fechaHoy.getDate()));
			carteraDetalle.setValor(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorFacturas)));
			carteraDetalle.setSaldo(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorFacturas)));
			carteraDetalle.setCartera(OPCION_SI);

			return carteraDetalle;
		}

		private CarteraDetalleEJB registrarCarteraDetalleNotaCredito(
				CarteraDetalleIf carteraDetalleModel, CarteraIf carteraModel,
				FacturaIf facturaModel) {
			CarteraDetalleEJB carteraDetalle = new CarteraDetalleEJB();
			String OPCION_SI = "S";
			DocumentoIf documento = null;

			try {
				/*
				 * Long usuarioId = ((UsuarioIf)
				 * usuarioLocal.findUsuarioById(facturaModel.getUsuarioId()).iterator().next()).getId();
				 * CajaIf cajaIf = (CajaIf)
				 * cajaLocal.findCajaByUsuarioId(usuarioId).iterator().next();
				 * OficinaIf oficinaIf =
				 * oficinaLocal.getOficina(facturaModel.getOficinaId());
				 * 
				 * if (cajaIf != null) { // Este preimpreso debería ser el de la
				 * nota de Crédito
				 * carteraDetalleModel.setPreimpreso(oficinaIf.getCodigo() + "-" +
				 * cajaIf.getCodigo() + "-" + facturaModel.getPreimpresoNumero()); }
				 */

				carteraDetalle.setId(carteraDetalleModel.getId());
				carteraDetalle.setCarteraId(carteraDetalleModel.getCarteraId());
				Iterator documentoIterator = documentoLocal.findDocumentoByCodigo(
				"NCAF").iterator();

				if (documentoIterator.hasNext())
					documento = (DocumentoIf) documentoIterator.next();

				carteraDetalle.setDocumentoId(documento.getId());
				carteraDetalle.setSecuencial(carteraDetalleModel.getSecuencial());
				carteraDetalle.setLineaId(carteraDetalleModel.getLineaId());
				// Aquí debería ir el preimpreso de la nota de crédito
				carteraDetalle.setPreimpreso(facturaModel.getPreimpresoNumero());
				carteraDetalle.setAutorizacion(carteraDetalleModel
						.getAutorizacion());
				java.util.Date fechaHoy = new java.util.Date();
				carteraDetalle.setFechaCreacion(new java.sql.Date(fechaHoy
						.getYear(), fechaHoy.getMonth(), fechaHoy.getDate()));
				carteraDetalle
				.setFechaCartera(new java.sql.Date(fechaHoy.getYear(),
						fechaHoy.getMonth(), fechaHoy.getDate()));
				carteraDetalle.setFechaVencimiento(new java.sql.Date(fechaHoy
						.getYear(), fechaHoy.getMonth(), fechaHoy.getDate()));
				carteraDetalle.setFechaUltimaActualizacion(carteraDetalleModel
						.getFechaUltimaActualizacion());
				carteraDetalle.setValor(utilitariosLocal.redondeoValor(carteraModel
						.getValor()));
				carteraDetalle.setSaldo(BigDecimal.ZERO);
				carteraDetalle.setCartera(OPCION_SI);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			return carteraDetalle;
		}

		private CarteraAfectaEJB registrarCarteraAfecta(
				CarteraAfectaIf carteraAfectaModel) {
			CarteraAfectaEJB carteraAfecta = new CarteraAfectaEJB();
			carteraAfecta.setId(carteraAfectaModel.getId());
			carteraAfecta.setCarteradetalleId(carteraAfectaModel
					.getCarteradetalleId());
			carteraAfecta.setCarteraafectaId(carteraAfectaModel
					.getCarteraafectaId());
			carteraAfecta.setUsuarioId(carteraAfectaModel.getUsuarioId());
			carteraAfecta.setValor(utilitariosLocal
					.redondeoValor(carteraAfectaModel.getValor()));
			java.util.Date fechaHoy = new java.util.Date();
			carteraAfecta.setFechaCreacion(new java.sql.Date(fechaHoy.getYear(),
					fechaHoy.getMonth(), fechaHoy.getDate()));
			carteraAfecta.setFechaAplicacion(new java.sql.Date(fechaHoy.getYear(),
					fechaHoy.getMonth(), fechaHoy.getDate()));
			carteraAfecta.setCartera("S");
			return carteraAfecta;
		}

		private Double sumarValoresParcialesPedidoDetalle(
				List<PedidoDetalleIf> modelDetalleList) {
			Double valor = 0.0;
			Double precioReal = 0.0;
			Double cantidadFacturada = 0.0;
			Double subTotal = 0.0;

			for (PedidoDetalleIf modelDetalle : modelDetalleList) {
				precioReal = modelDetalle.getPrecioreal().doubleValue();
				cantidadFacturada = modelDetalle.getCantidad().doubleValue();
				subTotal = cantidadFacturada * precioReal;
				valor += subTotal;
			}

			return valor;
		}

		private Double sumarDescuentosParcialesPedidoDetalle(
				List<PedidoDetalleIf> modelDetalleList) {
			Double descuento = 0.0;
			Double precioReal = 0.0;
			Double cantidadPedida = 0.0;
			Double cantidadFacturada = 0.0;
			Double subTotal = 0.0;
			Double descuentoPor = 0.0;

			for (PedidoDetalleIf modelDetalle : modelDetalleList) {
				/*
				 * precioReal = modelDetalle.getPrecioreal().doubleValue();
				 * cantidadPedida = modelDetalle.getCantidadpedida().doubleValue() *
				 * modelDetalle.getPrecioreal().doubleValue(); cantidadFacturada =
				 * modelDetalle.getCantidad().doubleValue(); subTotal =
				 * cantidadFacturada * precioReal; if ( cantidadPedida != 0 )
				 * descuentoPor = (modelDetalle.getDescuento().doubleValue() * 100) /
				 * (cantidadPedida); descuento +=
				 * utilitariosLocal.redondeoValor((subTotal * (descuentoPor /
				 * 100)));
				 */
				descuento += modelDetalle.getDescuento().doubleValue();
			}

			return descuento;
		}

		private Double sumarDescuentosGlobalesParcialesPedidoDetalle(
				List<PedidoDetalleIf> modelDetalleList) {
			Double descuentoGlobal = 0.0;
			Double precioReal = 0.0;
			Double cantidadPedida = 0.0;
			Double cantidadFacturada = 0.0;
			Double subTotal = 0.0;
			Double descuentoGlobalPor = 0.0;

			for (PedidoDetalleIf modelDetalle : modelDetalleList) {
				/*
				 * precioReal = modelDetalle.getPrecioreal().doubleValue();
				 * cantidadPedida = modelDetalle.getCantidadpedida().doubleValue() *
				 * modelDetalle.getPrecioreal().doubleValue(); cantidadFacturada =
				 * modelDetalle.getCantidad().doubleValue(); subTotal =
				 * cantidadFacturada * precioReal; if ( cantidadPedida != 0 )
				 * descuentoGlobalPor =
				 * (modelDetalle.getDescuentoGlobal().doubleValue() * 100) /
				 * (cantidadPedida); descuentoGlobal +=
				 * utilitariosLocal.redondeoValor((subTotal * (descuentoGlobalPor /
				 * 100) ));
				 */
				descuentoGlobal += modelDetalle.getDescuentoGlobal().doubleValue();
			}

			return descuentoGlobal;
		}

		private Double sumarIvaParcialesPedidoDetalle(
				List<PedidoDetalleIf> modelDetalleList) {
			Double ivaTotal = 0.0;

			for (PedidoDetalleIf modelDetalle : modelDetalleList) {
				ivaTotal += modelDetalle.getIva().doubleValue();
			}

			return ivaTotal;
		}

		private StockEJB registrarStock(StockIf modelStock) {
			StockEJB stock = new StockEJB();

			stock.setId(modelStock.getId());
			stock.setBodegaId(modelStock.getBodegaId());
			stock.setProductoId(modelStock.getProductoId());
			stock.setLoteId(modelStock.getLoteId());
			stock.setAnio(modelStock.getAnio());
			stock.setMes(modelStock.getMes());
			stock.setCantidad(utilitariosLocal.redondeoValor(modelStock
					.getCantidad()));
			stock.setReserva(modelStock.getReserva());
			stock.setTransito(modelStock.getTransito());

			return stock;
		}

		public Collection findFacturaByEmpresaIdByFechaInicioAndFechaFin(
				Long idEmpresa, java.sql.Date fechaInicio, java.sql.Date fechaFin)
		throws GenericBusinessException {
			String queryString = "select distinct f from FacturaEJB f, TipoDocumentoEJB td where f.tipodocumentoId = td.id and td.empresaId = "
				+ idEmpresa
				+ " and f.fechaFactura >= :fechaInicio and f.fechaFactura <= :fechaFin";
			Query query = manager.createQuery(queryString);
			java.sql.Timestamp startDate = utilitariosLocal.resetTimestampStartDate(new java.sql.Timestamp(fechaInicio.getTime()));
			query.setParameter("fechaInicio", startDate);
			java.sql.Timestamp endDate = utilitariosLocal.resetTimestampEndDate(new java.sql.Timestamp(fechaFin.getTime()));
			query.setParameter("fechaFin", endDate);
			return query.getResultList();
		}

		public Collection findFacturaReembolsoParaReversarByQueryByEmpresaIdByFechaDesdeAndFechaHasta(
				Map aMap, Long idEmpresa, java.sql.Date fechaInicio,
				java.sql.Date fechaFin) throws GenericBusinessException {
			String objectName = "f";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			String queryString = "select distinct f, a from FacturaEJB f, TipoDocumentoEJB td, AsientoEJB a where ";
			if (where != null && !"".equals(where.trim()))
				queryString += (where + " and ");

			queryString += " f.tipodocumentoId = td.id and a.tipoDocumentoId = td.id and a.transaccionId = f.id and td.reembolso = 'S' and td.empresaId = "
				+ idEmpresa
				+ " and f.fechaFactura >= :fechaInicio and f.fechaFactura <= :fechaFin and f.estado <> 'A'";
			Query query = manager.createQuery(queryString);
			java.sql.Timestamp startDate = utilitariosLocal.resetTimestampStartDate(new java.sql.Timestamp(fechaInicio.getTime()));
			query.setParameter("fechaInicio", startDate);
			java.sql.Timestamp endDate = utilitariosLocal.resetTimestampEndDate(new java.sql.Timestamp(fechaFin.getTime()));
			query.setParameter("fechaFin", endDate);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);

			}

			return query.getResultList();
		}

		public void procesarReversacionFacturasReembolso(FacturaIf factura,
				AsientoIf asiento, DocumentoIf documentoFacturaReembolso,
				UsuarioIf usuario) throws GenericBusinessException,
				SaldoCuentaNegativoException {
			try {
				List<AsientoDetalleIf> asientoDetalleList = new ArrayList<AsientoDetalleIf>();
				List<AsientoDetalleIf> asientoDetalleAnteriorList = (List) asientoDetalleLocal
				.findAsientoDetalleByAsientoId(asiento.getId());
				AsientoIf asientoAnterior = asientoLocal.registrarAsiento(asiento);
				AsientoIf asientoFacturaReembolso = asientoLocal
				.registrarAsiento(asiento);
				boolean reversarSaldos = false;
				if (asientoAnterior.getStatus().equals("A"))
					reversarSaldos = true;

				asiento.setId(asientoAnterior.getId());
				asiento.setPrimaryKey(asientoAnterior.getPrimaryKey());
				Iterator eventoContableIterator = eventoContableLocal
				.findEventoContableByDocumentoId(
						documentoFacturaReembolso.getId()).iterator();
				if (eventoContableIterator.hasNext()) {
					EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator
					.next();
					;
					if (eventoContable != null) {
						Double descuento = Double.valueOf(factura.getDescuento()
								.doubleValue());
						Double descuentoGlobal = Double.valueOf(factura
								.getDescuentoGlobal().doubleValue());
						Double iva = Double.valueOf(factura.getIva().doubleValue());
						Double valor = Double.valueOf(factura.getValor()
								.doubleValue());
						Double total = valor - descuento - descuentoGlobal;
						Map parameterMap = new HashMap();
						parameterMap.put("CTAXCOB", total + iva);
						parameterMap.put("REEMBOLSO", total);
						asientoDetalleList = generarAsientoDetallesReversacion(
								eventoContable, parameterMap);
						asientoLocal.actualizarAsiento(asientoFacturaReembolso,
								asientoDetalleList, asientoAnterior,
								asientoDetalleAnteriorList,
								asientoDetalleAnteriorList, reversarSaldos, false,
								usuario, new HashMap(), new HashMap(), new HashMap(), new HashMap(), false);
					}
				}
			} catch (GenericBusinessException e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(e.getMessage());
			} catch (SaldoCuentaNegativoException e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new SaldoCuentaNegativoException(e.getMessage());
			} catch (Exception e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(
				"Se ha producido un error al reversar la Factura de Reembolso");
			}
		}

		private List<AsientoDetalleIf> generarAsientoDetallesReversacion(
				EventoContableIf eventoContable, Map parameterMap)
				throws GenericBusinessException {
			List<AsientoDetalleIf> asientoDetallesReversacionList = new ArrayList<AsientoDetalleIf>();
			PlanCuentaIf planCuenta = planCuentaLocal.getPlanCuenta(eventoContable
					.getPlanCuentaId());
			Collection plantillas = plantillaLocal
			.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable
					.getId(), planCuenta.getId());
			Iterator plantillasIterator = plantillas.iterator();
			CuentaIf cuenta = null;

			while (plantillasIterator.hasNext()) {
				PlantillaIf plantilla = (PlantillaIf) plantillasIterator.next();
				cuenta = cuentaLocal.getCuenta(plantilla.getCuentaId());

				if (cuenta != null) {
					AsientoDetalleIf asientoDetalle = new AsientoDetalleData();
					asientoDetalle.setCuentaId(cuenta.getId());

					double valor = ((Double) parameterMap.get(plantilla
							.getNemonico())).doubleValue();
					if (plantilla.getDebehaber().equals("D")) {
						asientoDetalle.setDebe(BigDecimal.valueOf(valor));
						asientoDetalle.setHaber(BigDecimal.ZERO);
					} else if (plantilla.getDebehaber().equals("H")) {
						asientoDetalle.setHaber(BigDecimal.valueOf(valor));
						asientoDetalle.setDebe(BigDecimal.ZERO);
					}

					if (valor > 0.0)
						asientoDetallesReversacionList.add(asientoDetalle);
				}
			}

			return asientoDetallesReversacionList;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Collection findFacturaParaActualizarSaldosByQueryAndEmpresaId(
				Map aMap, Long idEmpresa) throws GenericBusinessException {
			String objectName = "f";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			String queryString = "select distinct f, ca from FacturaEJB "
				+ objectName
				+ ", CarteraEJB ca, OficinaEJB o, EmpresaEJB e where "
				+ where
				+ " and f.tipodocumentoId = ca.tipodocumentoId and f.id = ca.referenciaId and f.oficinaId = o.id and o.empresaId = e.id and e.id = "
				+ idEmpresa + " order by f.preimpresoNumero asc";

			System.out.println("QUERY -> " + queryString);
			Query query = manager.createQuery(queryString);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}

			return query.getResultList();
		}

		public void procesarActualizacionSaldosFacturas(FacturaIf factura,
				CarteraIf cartera) throws GenericBusinessException {
			try {
				// Iterator facturaDetalleIterator =
				// facturaDetalleLocal.findFacturaDetalleByFacturaId(factura.getId()).iterator();
				Iterator carteraDetalleIterator = carteraDetalleLocal
				.findCarteraDetalleByCarteraId(cartera.getId()).iterator();
				// List<AsientoDetalleIf> asientoDetalleList = new
				// ArrayList<AsientoDetalleIf>();
				// List<AsientoDetalleIf> asientoDetalleAnteriorList = (List)
				// asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getId());
				// factura.setTipodocumentoId(tipoDocumentoCompraPorReembolso.getId());
				// factura.setEstado("C");
				// FacturaEJB facturaParaActualizar = registrarFactura(factura);
				// manager.merge(facturaParaActualizar);
				/*
				 * while (facturaDetalleIterator.hasNext()) { CompraDetalleIf
				 * compraDetalleModel = (CompraDetalleIf)
				 * compraDetalleIterator.next();
				 * compraDetalleModel.setDocumentoId(documentoCompraPorReembolso.getId());
				 * CompraDetalleEJB compraDetalle =
				 * registrarCompraDetalle(compraDetalleModel);
				 * manager.merge(compraDetalle); }
				 */

				// cartera.setTipodocumentoId(tipoDocumentoCompraPorReembolso.getId());
				double valor = factura.getValor().doubleValue();
				double descuento = factura.getDescuento().doubleValue();
				double descuentoGlobal = factura.getDescuentoGlobal().doubleValue();
				double iva = factura.getIva().doubleValue();
				double porcentajeComision = factura.getPorcentajeComisionAgencia()
				.doubleValue();
				double comision = ((valor - descuento - descuentoGlobal) * porcentajeComision) / 100D;
				cartera.setValor(BigDecimal.valueOf(valor - descuento
						- descuentoGlobal + comision + iva));
				cartera.setSaldo(BigDecimal.valueOf(valor - descuento
						- descuentoGlobal + comision + iva));
				CarteraEJB carteraFacturaParaActualizar = registrarCartera(cartera);
				manager.merge(carteraFacturaParaActualizar);
				while (carteraDetalleIterator.hasNext()) {
					CarteraDetalleIf carteraDetalleModel = (CarteraDetalleIf) carteraDetalleIterator
					.next();
					carteraDetalleModel.setValor(BigDecimal.valueOf(valor
							- descuento - descuentoGlobal + comision + iva));
					carteraDetalleModel.setSaldo(BigDecimal.valueOf(valor
							- descuento - descuentoGlobal + comision + iva));
					CarteraDetalleEJB carteraDetalle = registrarCarteraDetalle(carteraDetalleModel);
					manager.merge(carteraDetalle);
				}

				/*
				 * AsientoEJB asientoAnterior =
				 * asientoLocal.registrarAsiento(asiento);
				 * asiento.setTipoDocumentoId(tipoDocumentoCompraPorReembolso.getId());
				 * AsientoEJB asientoCompraPorReembolso =
				 * asientoLocal.registrarAsiento(asiento); boolean reversarSaldos =
				 * false; if (asientoAnterior.getStatus().equals("A"))
				 * reversarSaldos = true;
				 * 
				 * asiento.setId(asientoAnterior.getId());
				 * asiento.setPrimaryKey(asientoAnterior.getPrimaryKey()); Iterator
				 * eventoContableIterator =
				 * eventoContableLocal.findEventoContableByDocumentoId(documentoCompraPorReembolso.getId()).iterator();
				 * if (eventoContableIterator.hasNext()) { EventoContableIf
				 * eventoContable = (EventoContableIf)
				 * eventoContableIterator.next();; if (eventoContable != null) {
				 * Double descuento =
				 * Double.valueOf(compra.getDescuento().doubleValue()); Double iva =
				 * Double.valueOf(compra.getIva().doubleValue()); Double subtotal =
				 * Double.valueOf(compra.getValor().doubleValue()); double
				 * otroImpuesto =
				 * Double.valueOf(compra.getOtroImpuesto().doubleValue()); Double
				 * total = subtotal + iva + otroImpuesto - descuento; Map
				 * parameterMap = new HashMap(); parameterMap.put("REEMBOLSO",
				 * total); parameterMap.put("CTAXPAG", total);
				 * parameterMap.put("PROVEEDOR_ID", compra.getProveedorId());
				 * asientoDetalleList =
				 * generarAsientoDetallesReversacion(eventoContable, parameterMap);
				 * asientoLocal.actualizarAsiento(asientoCompraPorReembolso,
				 * asientoDetalleList, asientoAnterior, asientoDetalleAnteriorList,
				 * asientoDetalleAnteriorList, reversarSaldos); } }
				 */
			} catch (GenericBusinessException e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(e.getMessage());
			} catch (Exception e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(
				"Se ha producido un error al procesar las facturas");
			}
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public boolean actualizarFactura(PedidoIf pedido, FacturaIf factura,
				List<PedidoDetalleIf> pedidoDetalleList, long idEmpresa,
				long idOficina, UsuarioIf usuario) throws GenericBusinessException {
			boolean exito = false;

			try {
				List<FacturaIf> facturaList = (ArrayList<FacturaIf>) findFacturaByPedidoId(pedido.getId());
				if (facturaList.size() == 1) {
					FacturaIf facturaAnterior = (FacturaIf) facturaList.get(0);
					Map parameterMap = new HashMap();
					parameterMap.put("tipodocumentoId", facturaAnterior.getTipodocumentoId());
					parameterMap.put("referenciaId", facturaAnterior.getId());
					Iterator carteraIt = findCarteraByQuery(parameterMap).iterator();
					if (carteraIt.hasNext()) {
						CarteraIf carteraAnterior = (CarteraIf) carteraIt.next();
						if (utilitariosLocal.redondeoValor(carteraAnterior.getValor().doubleValue()) == utilitariosLocal.redondeoValor(carteraAnterior.getSaldo().doubleValue())) {
							exito = actualizarFactura(factura, pedidoDetalleList,facturaAnterior, carteraAnterior, pedido.getTiporeferencia(), idEmpresa,idOficina, usuario);
						} else
							exito = false;
					}
				}
			} catch (GenericBusinessException e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(e.getMessage());
			} catch (Exception e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(
				"Se ha producido un error al actualizar el pedido/factura");
			}

			return exito;
		}

		public boolean actualizarFactura(FacturaIf model,
				List<PedidoDetalleIf> pedidoDetalleList, FacturaIf facturaAnterior,
				CarteraIf carteraAnterior, String tipoReferencia, Long idEmpresa,
				Long idOficina, UsuarioIf usuario) throws GenericBusinessException {
			FacturaEJB factura;
			Vector<FacturaDetalleIf> facturaDetalleColeccion = new Vector<FacturaDetalleIf>();
			try {
				int contadorDetallesFacturados = 0;
				Iterator pedidoDetalleListIterator = pedidoDetalleList.iterator();
				while (pedidoDetalleListIterator.hasNext()) {
					PedidoDetalleIf modelDetalle = (PedidoDetalleIf) pedidoDetalleListIterator.next();
					modelDetalle = verificarStockPorLote(modelDetalle, model.getBodegaId());
					if (modelDetalle.getCantidad().doubleValue() > 0.0)
						contadorDetallesFacturados++;
					else
						pedidoDetalleListIterator.remove();
				}
				
				if (contadorDetallesFacturados > 0) {
					Iterator it = facturaDetalleLocal.findFacturaDetalleByFacturaId(facturaAnterior.getId()).iterator();
					while (it.hasNext()) {
						FacturaDetalleIf facturaDetalle = (FacturaDetalleIf) it.next();
						Iterator detallesIt = facturaDetalleCompraAsociadaSessionLocal.findFacturaDetalleCompraAsociadaByFacturaDetalleId(facturaDetalle.getId()).iterator();
						while (detallesIt.hasNext()) {
							FacturaDetalleCompraAsociadaIf fdca = (FacturaDetalleCompraAsociadaIf) detallesIt.next();
							manager.remove(fdca);
						}
						manager.remove(facturaDetalle);
					}
					boolean actualizarCodigoCartera = true;
					java.sql.Timestamp fechaFacturaAnterior = facturaAnterior.getFechaFactura();
					int yearFacturaAnterior = fechaFacturaAnterior.getYear() + 1900;
					int monthFacturaAnterior = fechaFacturaAnterior.getMonth() + 1;
					java.sql.Timestamp fechaFactura = model.getFechaFactura();
					int yearFactura = fechaFactura.getYear() + 1900;
					int monthFactura = fechaFactura.getMonth() + 1;
					TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(model.getTipodocumentoId());
					if (yearFacturaAnterior == yearFactura && monthFacturaAnterior == monthFactura) {
						actualizarCodigoCartera = false;
					}
					//factura = registrarFactura(model);

					boolean esFacturaReembolso = false;
					if (tipoDocumento.getReembolso().equals("S")) {
						esFacturaReembolso = true;
					} else {
						esFacturaReembolso = false;
					}

					factura = registrarFactura(model, pedidoDetalleList, esFacturaReembolso);
					factura.setId(facturaAnterior.getId());
					factura.setNumero(facturaAnterior.getNumero());
					factura.setPreimpresoNumero(facturaAnterior.getPreimpresoNumero());
					long tipoDocumentoAnteriorId = facturaAnterior.getTipodocumentoId();
					manager.merge(factura);
					pedidoDetalleListIterator = pedidoDetalleList.iterator();
					while (pedidoDetalleListIterator.hasNext()) {
						PedidoDetalleIf modelDetalle = (PedidoDetalleIf) pedidoDetalleListIterator.next();
						FacturaDetalleData modelFacturaDetalle = new FacturaDetalleData();
						modelFacturaDetalle.setFacturaId(factura.getPrimaryKey());
						FacturaDetalleEJB facturaDetalle = registrarFacturaDetalle(
								modelFacturaDetalle, modelDetalle, factura
								.getPorcentajeComisionAgencia().doubleValue(), 
								null, (tipoDocumento.getCodigo().equals("FCO")) ? true
										: false);
						manager.merge(facturaDetalle);
						facturaDetalleColeccion.add(facturaDetalle);
					}
					long carteraAnteriorId = carteraAnterior.getId();
					it = carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraAnteriorId).iterator();
					while (it.hasNext()) {
						CarteraDetalleIf carteraDetalleAnterior = (CarteraDetalleIf) it.next();
						manager.remove(carteraDetalleAnterior);
					}

					CarteraData modelCartera = new CarteraData();
					CarteraEJB carteraFactura = registrarCartera(modelCartera,
							carteraAnterior, factura, actualizarCodigoCartera,
							idOficina);
					carteraFactura.setId(carteraAnteriorId);
					// carteraFactura.setCodigo(carteraAnterior.getCodigo());
					manager.merge(carteraFactura);

					List<DocumentoIf> modelDocumentoList = (List) documentoLocal
							.findDocumentoByTipoDocumentoId(factura
									.getTipodocumentoId());

					int count = 0;
					facturaDetalleColeccion = new Vector<FacturaDetalleIf>();
					double valorFacturas = 0.0;
					for (DocumentoIf modelDocumento : modelDocumentoList) {
						Collection facturas = facturaDetalleLocal.findFacturaDetalleByDocumentoIdAndByFacturaId(modelDocumento.getId(), factura.getPrimaryKey());
						Iterator itFacturas = facturas.iterator();
						CarteraDetalleData carteraDetalleModel = new CarteraDetalleData();
						CarteraDetalleEJB carteraDetalle = new CarteraDetalleEJB();
						boolean registrarCartera = false;
						FacturaDetalleIf facturaDetalle = null;
						while (itFacturas.hasNext()) {
							facturaDetalle = (FacturaDetalleIf) itFacturas.next();
							facturaDetalleColeccion.add(facturaDetalle);
							DocumentoIf documento = null;
							documento = documentoLocal.getDocumento(facturaDetalle.getDocumentoId());
							// Si el documento del detalle de la factura que leo es de
							// bonificación para ese no genero ninguna cartera
							if (documento.getBonificacion().equals("N")) {
								double valorFacturaDetalle = facturaDetalle.getValor().doubleValue();
								double descuentoFacturaDetalle = facturaDetalle.getDescuento().doubleValue();
								double porcentajeDescuentosVarios = facturaDetalle.getPorcentajeDescuentosVarios().doubleValue() / 100;
								double descuentosVarios = valorFacturaDetalle * porcentajeDescuentosVarios;
								double descuentoGlobalFacturaDetalle = facturaDetalle.getDescuentoGlobal().doubleValue();
								double descuentoTotalFacturaDetalle = descuentoFacturaDetalle  + descuentosVarios + descuentoGlobalFacturaDetalle;
								double ivaFacturaDetalle = facturaDetalle.getIva().doubleValue();
								double iceFacturaDetalle = facturaDetalle.getIce().doubleValue();
								double otroImpuestoDetalle = facturaDetalle.getOtroImpuesto().doubleValue();
								double impuestoTotalFacturaDetalle = ivaFacturaDetalle + iceFacturaDetalle + otroImpuestoDetalle;
								double porcentajeComision = factura.getPorcentajeComisionAgencia().doubleValue();
								double comision = ((valorFacturaDetalle - descuentoTotalFacturaDetalle) * porcentajeComision) / 100D;
								double valorCarteraDetalle = valorFacturaDetalle - descuentoTotalFacturaDetalle + comision + impuestoTotalFacturaDetalle;
								valorFacturas += valorCarteraDetalle;
								carteraDetalleModel.setCarteraId(carteraFactura.getPrimaryKey());
								registrarCartera = true;
							}

							ProductoIf producto = productoLocal.getProducto(facturaDetalle.getProductoId());
							String comprasReembolsoAsociadas = facturaDetalle.getComprasReembolsoAsociadas() != null?facturaDetalle.getComprasReembolsoAsociadas():"";
							String[] preimpresosCompras = comprasReembolsoAsociadas.split(", ");
							int len = preimpresosCompras.length;
							for (int i=0; i<len; i++) {
								if (preimpresosCompras[i].length() > 0) {
									Map queryMap = new HashMap();
									queryMap.put("proveedorId", producto.getProveedorId());
									queryMap.put("preimpreso", preimpresosCompras[i]);
									CompraIf compra = (CompraIf) findCompraAsociadaByQuery(queryMap).iterator().next();
									FacturaDetalleCompraAsociadaEJB data = new FacturaDetalleCompraAsociadaEJB();
									data.setCompraId(compra.getId());
									data.setFacturaDetalleId(facturaDetalle.getId());
									manager.merge(data);
								}
							}
						}
						if (registrarCartera && facturaDetalle != null) {
							carteraDetalle = registrarCarteraDetalle(
									carteraDetalleModel, facturaDetalle, factura,
									valorFacturas);
						}
						// si el detalle de la cartera posee el id de la cartera queire
						// decir que ha salido de la iteracion
						// por lo tanto aumento en uno el secuencial con el cual se
						// generan los detalles
						// de cartera, esto se valida porque puede darse el caso de que
						// para determinado documento no exista ningun detalle de
						// factura
						if (carteraDetalle.getCarteraId() != null) {
							count++;
							carteraDetalle.setSecuencial(count);
							manager.merge(carteraDetalle);
						}
					}

					AsientoIf asiento = null;
					yearFactura = factura.getFechaFactura().getYear() + 1900;
					monthFactura = factura.getFechaFactura().getMonth() + 1;
					// if (yearFactura >= 2008 && monthFactura >= 9)
					asiento = generarAsientoFactura(factura, facturaDetalleColeccion, tipoDocumentoAnteriorId, true, usuario, tipoReferencia);
				}
			} catch (GenericBusinessException e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(e.getMessage());
			} catch (Exception e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(
				"Se ha producido un error al actualizar factura");
			}

			return true;
		}

		public FacturaIf getFacturaByPreimpresoAndOficina(Long idOficina,Long idFacturaOrigen)
		{
			String queryString="Select factura from FacturaEJB factura where factura.preimpresoNumero=:preimpreso and factura.oficinaId=:oficinaId";
			Query query=manager.createQuery(queryString);
			query.setParameter("preimpreso", String.valueOf(idFacturaOrigen));
			query.setParameter("oficinaId", idOficina);
			return ((List<FacturaIf>)query.getResultList()).get(0);
		}

		public void actualizarPreimpreso(FacturaIf factura, String preimpreso, boolean propagarMensaje)
		throws GenericBusinessException {
			try {
				factura.setPreimpresoNumero(preimpreso);
				manager.merge(factura);
				Map parameterMap = new HashMap();
				parameterMap.put("tipoDocumentoId", factura.getTipodocumentoId());
				parameterMap.put("transaccionId", factura.getId());
				Iterator it = asientoLocal.findAsientoByQuery(parameterMap).iterator();
				if (it.hasNext()) {
					TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(factura.getTipodocumentoId());
					AsientoIf asiento = (AsientoIf) it.next();
					String observacion = "";
					if (tipoDocumento.getFactura().equals("S"))
						observacion = "F# ";
					else if (tipoDocumento.getNotaVenta().equals("S"))
						observacion = "N/V# ";
					else if (tipoDocumento.getNotaCredito().equals("S"))
						observacion = "N/C# ";					
					asiento.setObservacion(observacion + factura.getPreimpresoNumero() + " " + factura.getObservacion());
					manager.merge(asiento);
					Iterator itDetail = asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getId()).iterator();
					while (itDetail.hasNext()) {
						AsientoDetalleIf detail = (AsientoDetalleIf) itDetail.next();
						detail.setGlosa(observacion + factura.getPreimpresoNumero() + " " + factura.getObservacion());
						manager.merge(detail);
					}

					parameterMap.clear();
					parameterMap.put("tipodocumentoOrigenId", factura.getTipodocumentoId());
					parameterMap.put("referenciaId", factura.getId());
					it = movimientoSessionLocal.findMovimientoByQuery(parameterMap).iterator();
					if (it.hasNext()) {
						MovimientoIf movimiento = (MovimientoIf) it.next();
						TipoDocumentoIf tipoDocumentoMovimiento = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(movimiento.getTipodocumentoId());
						parameterMap.clear();
						parameterMap.put("tipoDocumentoId", movimiento.getTipodocumentoId());
						parameterMap.put("transaccionId", movimiento.getId());
						it = asientoLocal.findAsientoByQuery(parameterMap).iterator();
						if (it.hasNext()) {
							AsientoIf asientoMovimiento = (AsientoIf) it.next();
							observacion = tipoDocumentoMovimiento.getCodigo() + ": " + movimiento.getCodigo() + " ";
							if (tipoDocumento.getCodigo().equals("FAC"))
								observacion += "F# ";
							else if (tipoDocumento.getCodigo().equals("VTA"))
								observacion += "N/V# ";
							else if (tipoDocumento.getCodigo().equals("DEV"))
								observacion += "N/C# ";

							observacion += factura.getPreimpresoNumero() + " " + factura.getObservacion();
							asientoMovimiento.setObservacion(observacion);
							manager.merge(asientoMovimiento);
							movimiento.setObservacion(observacion);
							manager.merge(movimiento);
							itDetail = asientoDetalleLocal.findAsientoDetalleByAsientoId(asientoMovimiento.getId()).iterator();
							while (itDetail.hasNext()) {
								AsientoDetalleIf detail = (AsientoDetalleIf) itDetail.next();
								detail.setGlosa(observacion);
								manager.merge(detail);
							}
						}
					}
				}

				parameterMap.clear();
				parameterMap.put("tipodocumentoId", factura.getTipodocumentoId());
				parameterMap.put("referenciaId", factura.getId());
				it = findCarteraByQuery(parameterMap).iterator();
				if (it.hasNext()) {
					CarteraIf cartera = (CarteraIf) it.next();
					cartera.setPreimpreso(factura.getPreimpresoNumero());
					manager.merge(cartera);
				}

				if (propagarMensaje) {
					actualizarPreimpresoMessageLocal.setData(
							factura.getOficinaId(), 
							factura.getId(), 
							preimpreso,
							propagarMensaje);

					try {
						actualizarPreimpresoMessageLocal.sendToPrincipalIfPos();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (GenericBusinessException e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(e.getMessage());
			}
		}

		public java.util.Collection findCarteraCreditoDisponible(Map aMap, Long idEmpresa) {
			String objectName = "e";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			String queryString = "select distinct e from CarteraEJB " + objectName + ", TipoDocumentoEJB td where " + where;

			queryString += " and e.tipodocumentoId = td.id and td.empresaId = " + idEmpresa;

			Date fechaServidor = null;
			try {
				fechaServidor = utilitariosLocal.getServerDateSql();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}	
			queryString += " and e.id in (SELECT h.id from CarteraEJB h WHERE DATEDIFF('"+fechaServidor+"',e.fechaEmision)<32)";
			queryString += " order by e.preimpreso asc";

			Query query = manager.createQuery(queryString);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);

			}

			return query.getResultList();		
		}

		public java.util.Collection findCarteraByQuery(Map aMap) {
			String objectName = "e";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			String queryString = "from CarteraEJB " + objectName + " where "
			+ where;
			Query query = manager.createQuery(queryString);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);

			}

			return query.getResultList();

		}


		public Collection findFacturasByQueryByFechaInicioAndByFechaFin(Map aMap,
				java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin) throws GenericBusinessException {
			
			String objectName = "f";
			String where = "";
			if (!aMap.isEmpty()) {
				where = QueryBuilder.buildWhere(aMap, objectName) + " and";
			}
			
			//si no hay filtro por cliente oficina, veo si hay filtro por cliente
			Long clienteId = 0L;
			if(aMap.get("clienteoficinaId") == null){
				if(aMap.get("clienteId") != null){
					clienteId = (Long)aMap.get("clienteId");
				}
			}
			
			String queryString = "select f from FacturaEJB "
				+ objectName
				+ ", ClienteOficinaEJB clo, ClienteEJB cl where "
				+ where
				+ " f.clienteoficinaId = clo.id and clo.clienteId = cl.id and f.fechaFactura >= :fechaInicio and f.fechaFactura <= :fechaFin";
			
			//si existe filtro por cliente
			if(clienteId != 0L){
				queryString = queryString + " and cl.id = " + clienteId + "";
			}		
			
			String orderByPart = "";
			orderByPart += " order by cl.nombreLegal asc";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			fechaInicio = utilitariosLocal.resetTimestampStartDate(fechaInicio);
			query.setParameter("fechaInicio", fechaInicio);
			fechaFin = utilitariosLocal.resetTimestampEndDate(fechaFin);
			query.setParameter("fechaFin", fechaFin);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}

			return query.getResultList();
		} 
		
		public Collection findFacturasPedidosByQueryByFechaInicioAndByFechaFin(Map aMap,
				java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin) throws GenericBusinessException {
			
			//si no hay filtro por cliente oficina, veo si hay filtro por cliente
			Long clienteId = 0L;
			if(aMap.get("clienteoficinaId") == null){
				if(aMap.get("clienteId") != null){
					clienteId = (Long)aMap.get("clienteId");
					aMap.remove("clienteId");
				}
			}
			
			String objectName = "f";
			String where = "";
			if (!aMap.isEmpty()) {
				where = QueryBuilder.buildWhere(aMap, objectName) + " and";
			}		
			
			String queryString = "select f, p from FacturaEJB "
				+ objectName
				+ ", PedidoEJB p, ClienteOficinaEJB clo, ClienteEJB cl where "
				+ where
				+ " f.pedidoId = p.id and f.clienteoficinaId = clo.id and clo.clienteId = cl.id and f.fechaFactura >= :fechaInicio and f.fechaFactura <= :fechaFin";
			
			//si existe filtro por cliente
			if(clienteId != 0L){
				queryString = queryString + " and cl.id = " + clienteId + "";
			}		
			
			String orderByPart = "";
			orderByPart += " order by cl.nombreLegal asc";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			fechaInicio = utilitariosLocal.resetTimestampStartDate(fechaInicio);
			query.setParameter("fechaInicio", fechaInicio);
			fechaFin = utilitariosLocal.resetTimestampEndDate(fechaFin);
			query.setParameter("fechaFin", fechaFin);
			//query.setParameter("clienteId", clienteId);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}

			return query.getResultList();
		} 
		
		public Collection findFacturasPedidosByQueryByFechaInicioAndByFechaFinByProveedorIdByProveedorOficinaId(Map aMap,
				java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin, Long idProveedor, Long idProveedorOficina) throws GenericBusinessException {
			
			//si no hay filtro por cliente oficina, veo si hay filtro por cliente
			Long clienteId = 0L;
			if(aMap.get("clienteoficinaId") == null){
				if(aMap.get("clienteId") != null){
					clienteId = (Long)aMap.get("clienteId");
					aMap.remove("clienteId");
				}
			}
			
			String objectName = "f";
			String where = "";
			if (!aMap.isEmpty()) {
				where = QueryBuilder.buildWhere(aMap, objectName) + " and";
			}		
			
			String queryString = "select distinct f, p, fd from FacturaEJB "
				+ objectName
				+ ", PedidoEJB p, ClienteOficinaEJB clo, ClienteEJB cl, FacturaDetalleEJB fd, ProductoEJB pr, ClienteOficinaEJB pof, ClienteEJB pro where "
				+ where
				+ " f.pedidoId = p.id and f.clienteoficinaId = clo.id and clo.clienteId = cl.id and f.id = fd.facturaId and fd.productoId = pr.id " +
						"and pr.proveedorId = pof.id and pof.clienteId = pro.id and f.fechaFactura >= :fechaInicio and f.fechaFactura <= :fechaFin";
			
			//si existe filtro por cliente
			if(clienteId != 0L){
				queryString = queryString + " and cl.id = " + clienteId + "";
			}		
			
			//si existe filtro por proveedorOficinaId o proveedorId
			if(idProveedorOficina != null && idProveedorOficina != 0L){
				queryString = queryString + " and pof.id = " + idProveedorOficina + "";
			}else if(idProveedor != null && idProveedor != 0L){
				queryString = queryString + " and pro.id = " + idProveedor + "";
			}
			
			//queryString = queryString + " and (f.observacion like '%JINGLE%' or f.observacion like '%CUÑA%' or fd.descripcion like '%JINGLE%' or fd.descripcion like '%CUÑA%') ";
			
			String orderByPart = "";
			orderByPart += " order by cl.nombreLegal asc";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			fechaInicio = utilitariosLocal.resetTimestampStartDate(fechaInicio);
			query.setParameter("fechaInicio", fechaInicio);
			fechaFin = utilitariosLocal.resetTimestampEndDate(fechaFin);
			query.setParameter("fechaFin", fechaFin);
			//query.setParameter("clienteId", clienteId);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}

			return query.getResultList();
		} 


		public Collection findFacturasByQueryByFechaInicioAndByFechaFinEmpresaId(Map aMap,
				java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin,Long empresaId) throws GenericBusinessException {
			String objectName = "f";
			String where = "";
			if (!aMap.isEmpty()) {
				where = QueryBuilder.buildWhere(aMap, objectName) + " and";
			}

			String queryString = "select f from FacturaEJB "
				+ objectName
				+ ", ClienteOficinaEJB clo, TipoDocumentoEJB td where "
				+ where
				+ " f.clienteoficinaId = clo.id and f.tipodocumentoId=td.id and f.fechaFactura >= :fechaInicio and f.fechaFactura <= :fechaFin" +
				" and td.empresaId='"+empresaId+"' ";
			String orderByPart = "";
			orderByPart += " order by f.preimpresoNumero asc";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			fechaInicio = utilitariosLocal.resetTimestampStartDate(fechaInicio);
			query.setParameter("fechaInicio", fechaInicio);
			fechaFin = utilitariosLocal.resetTimestampEndDate(fechaFin);
			query.setParameter("fechaFin", fechaFin);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}

			return query.getResultList();
		}

		public Collection findFacturasAndFacturasDetalleByQueryByFechaInicioAndByFechaFin(
				Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin, boolean usarNombreComercial)
		throws GenericBusinessException {
			String objectName = "f";
			String where = "";
			if (!aMap.isEmpty()) {
				where = QueryBuilder.buildWhere(aMap, objectName) + " and";
			}
			String queryString = "select f, fd from FacturaEJB "
				+ objectName
				+ ", FacturaDetalleEJB fd, "
				+ "ClienteOficinaEJB clo, ClienteEJB cl where "
				+ where
				+ " f.id = fd.facturaId and f.clienteoficinaId = clo.id "
				+ "and clo.clienteId = cl.id and f.estado <> 'A' and f.fechaFactura >= :fechaInicio and f.fechaFactura <= :fechaFin";
			
			String orderByPart = "";
			if(usarNombreComercial){
				orderByPart += " order by clo.descripcion asc";
			}else{
				orderByPart += " order by cl.nombreLegal asc";
			}
			
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			fechaInicio = utilitariosLocal.resetTimestampStartDate(fechaInicio);
			query.setParameter("fechaInicio", fechaInicio);
			fechaFin = utilitariosLocal.resetTimestampEndDate(fechaFin);
			query.setParameter("fechaFin", fechaFin);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}

			return query.getResultList();
		}

		public Collection findFacturasAndFacturasDetalleByQueryByFechaInicioAndByFechaFinEmpresaId(
				Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin,Long empresaId, boolean usarNombreComercial)
		throws GenericBusinessException {
			String objectName = "f";
			String where = "";
			if (!aMap.isEmpty()) {
				where = QueryBuilder.buildWhere(aMap, objectName) + " and";
			}
			String queryString = "select f, fd from FacturaEJB "
				+ objectName
				+ ", FacturaDetalleEJB fd, "
				+ "ClienteOficinaEJB clo, ClienteEJB cl,TipoDocumentoEJB td where "
				+ where
				+ " f.id = fd.facturaId and f.clienteoficinaId = clo.id and f.tipodocumentoId=td.id "
				+ "and td.empresaId='"+empresaId+"' "
				+ "and clo.clienteId = cl.id and f.estado <> 'A' and f.fechaFactura >= :fechaInicio and f.fechaFactura <= :fechaFin";
			
			String orderByPart = "";
			
			if(usarNombreComercial){
				orderByPart += " order by clo.descripcion asc";
			}else{
				orderByPart += " order by cl.nombreLegal asc";
			}
			
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			fechaInicio = utilitariosLocal.resetTimestampStartDate(fechaInicio);
			query.setParameter("fechaInicio", fechaInicio);
			fechaFin = utilitariosLocal.resetTimestampEndDate(fechaFin);
			query.setParameter("fechaFin", fechaFin);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}

			return query.getResultList();
		}


		public Collection findFacturasCarterasAndCarterasDetalleByQueryByFechaInicioAndByFechaFinEmpresaId(
				Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin,Long empresaId)
		throws GenericBusinessException {
			String objectName = "f";
			String where = "";
			if (!aMap.isEmpty()) {
				where = QueryBuilder.buildWhere(aMap, objectName) + " and";
			}
			String queryString = "select f, ca, cd from FacturaEJB "
				+ objectName
				+ ", CarteraEJB ca, CarteraDetalleEJB cd, "
				+ "ClienteOficinaEJB clo, ClienteEJB cl,TipoDocumentoEJB td where "
				+ where
				+ " f.id = ca.referenciaId and f.tipodocumentoId = ca.tipodocumentoId "
				//+ " and ca.id = cd.carteraId and f.clienteoficinaId = clo.id and clo.clienteId = cl.id and f.fechaFactura >= :fechaInicio and f.fechaFactura <= :fechaFin";
				+ " and ca.id = cd.carteraId and f.clienteoficinaId = clo.id and f.tipodocumentoId=td.id and td.empresaId='"+empresaId+"'and clo.clienteId = cl.id and" 
				+ " ca.fechaEmision >= :fechaInicio and ca.fechaEmision <= :fechaFin";
			String orderByPart = "";
			orderByPart += " order by cl.nombreLegal asc";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			fechaInicio = utilitariosLocal.resetTimestampStartDate(fechaInicio);
			query.setParameter("fechaInicio", fechaInicio);
			fechaFin = utilitariosLocal.resetTimestampEndDate(fechaFin);
			query.setParameter("fechaFin", fechaFin);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}

			return query.getResultList();
		}
		
		/*public Collection findFacturasCarterasAndCarterasDetalleByQueryByFechaInicioAndByFechaFinEmpresaId(
				Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin,Long empresaId)
		throws GenericBusinessException {
			String objectName = "f";
			String where = "";
			if (!aMap.isEmpty()) {
				where = QueryBuilder.buildWhere(aMap, objectName) + " and";
			}
			String queryString = "select f, ca, cd from FacturaEJB "
				+ objectName
				+ ", CarteraEJB ca, CarteraDetalleEJB cd, "
				+ "ClienteOficinaEJB clo, ClienteEJB cl,TipoDocumentoEJB td where "
				+ where
				+ " f.id = ca.referenciaId and f.tipodocumentoId = ca.tipodocumentoId "
				+ " and ca.id = cd.carteraId and f.clienteoficinaId = clo.id and f.tipodocumentoId=td.id and td.empresaId='"+empresaId+"'and clo.clienteId = cl.id and" 
				+ " ca.fechaEmision >= :fechaInicio and ca.fechaEmision <= :fechaFin";
			String orderByPart = "";
			orderByPart += " order by cl.nombreLegal asc";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			fechaInicio = utilitariosLocal.resetTimestampStartDate(fechaInicio);
			query.setParameter("fechaInicio", fechaInicio);
			fechaFin = utilitariosLocal.resetTimestampEndDate(fechaFin);
			query.setParameter("fechaFin", fechaFin);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}

			return query.getResultList();
		}*/
		
		public Collection findFacturasCarterasAndCarterasDetalleByQueryByFechaInicioAndByFechaFinConsolidado(
				Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin)
		throws GenericBusinessException {
			String objectName = "f";
			String where = "";
			if (!aMap.isEmpty()) {
				where = QueryBuilder.buildWhere(aMap, objectName) + " and";
			}
			String queryString = "select f, ca, cd from FacturaEJB "
				+ objectName
				+ ", CarteraEJB ca, CarteraDetalleEJB cd, "
				+ "ClienteOficinaEJB clo, ClienteEJB cl where "
				+ where
				+ " f.id = ca.referenciaId and f.tipodocumentoId = ca.tipodocumentoId"
				+ " and ca.id = cd.carteraId and f.clienteoficinaId = clo.id and clo.clienteId = cl.id and " +
				"ca.fechaEmision >= :fechaInicio and ca.fechaEmision <= :fechaFin";
			String orderByPart = "";
			orderByPart += " order by cl.nombreLegal asc";
			queryString += orderByPart;
			Query query = manager.createQuery(queryString);
			fechaInicio = utilitariosLocal.resetTimestampStartDate(fechaInicio);
			query.setParameter("fechaInicio", fechaInicio);
			fechaFin = utilitariosLocal.resetTimestampEndDate(fechaFin);
			query.setParameter("fechaFin", fechaFin);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}

			return query.getResultList();
		}



		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public java.util.Collection findFacturaByCarteraDetalleComprobante(
				Long idCarteraDetalleComprobante)
		throws com.spirit.exception.GenericBusinessException {
			// select distinct f.* from factura f, cartera car, cartera_detalle
			// cardet where car.TIPODOCUMENTO_ID = f.TIPODOCUMENTO_ID and
			// car.REFERENCIA_ID = f.ID and cardet.CARTERA_ID = car.ID and cardet.ID
			// in (select distinct ca.CARTERAAFECTA_ID from cartera_detalle cd,
			// cartera_afecta ca where cd.ID = ca.CARTERADETALLE_ID and cd.ID =
			// 20002)

			/*String queryString = "select distinct f from FacturaEJB f, CarteraEJB car, CarteraDetalleEJB cardet " +
					"where car.tipodocumentoId = f.tipodocumentoId and car.referenciaId = f.id and cardet.carteraId = car.id " +
					"and cardet.id in (select distinct ca.carteraafectaId from CarteraDetalleEJB cd, CarteraAfectaEJB ca " +
					"where cd.id = ca.carteradetalleId and cd.id = " + idCarteraDetalleRetencion + ")";*/

			/*String queryString = "select distinct f from FacturaEJB f, CarteraEJB car, CarteraDetalleEJB cardet " +
					"where car.tipodocumentoId = f.tipodocumentoId and car.referenciaId = f.id and cardet.carteraId = car.id " +
					"and cardet.id in (select distinct caf.carteraafectaId from CarteraAfectaEJB caf " +
					"where caf.carteradetalleId = " + idCarteraDetalleRetencion + ")";*/

			/*String queryString = "select distinct f from FacturaEJB f, CarteraEJB car, CarteraDetalleEJB cardet " +
			"where car.tipodocumentoId = f.tipodocumentoId and car.referenciaId = f.id and cardet.carteraId = car.id " +
			"and cardet.id = " + idCarteraDetalleRetencion + ")";*/ 
			
			String queryString = "select distinct f, p from FacturaEJB f, PedidoEJB p, CarteraEJB car, CarteraDetalleEJB cardet where f.pedidoId = p.id and car.tipodocumentoId = f.tipodocumentoId and car.referenciaId = f.id and cardet.carteraId = car.id and cardet.id in (select distinct ca.carteraafectaId from CarteraDetalleEJB cd, CarteraAfectaEJB ca where cd.id = ca.carteradetalleId and cd.id = " + idCarteraDetalleComprobante + ")";

			Query query = manager.createQuery(queryString);
			return query.getResultList();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Collection findFacturaAnuladaParaReactivarByQueryAndEmpresaId(
				Map aMap, Long idEmpresa) throws GenericBusinessException {
			String objectName = "f";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			String queryString = "select distinct f, lc, la from FacturaEJB "
				+ objectName
				+ ", LogCarteraEJB lc, LogAsientoEJB la, OficinaEJB o, EmpresaEJB e where "
				+ where
				+ " and f.tipodocumentoId = lc.tipodocumentoId and f.id = lc.referenciaId and f.oficinaId = o.id and o.empresaId = e.id and la.transaccionId = f.id and la.tipoDocumentoId = f.tipodocumentoId and e.id = "
				+ idEmpresa
				+ " order by f.fechaFactura asc, f.preimpresoNumero asc, lc.id desc, la.id desc";

			System.out.println("QUERY -> " + queryString);
			Query query = manager.createQuery(queryString);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}

			return query.getResultList();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Collection findLogCarteraFacturaAnulada(FacturaIf factura) throws GenericBusinessException {
			String queryString = "select distinct lc from LogCarteraEJB lc where lc.tipodocumentoId = " + factura.getTipodocumentoId() + " and lc.referenciaId = " + factura.getId();
			System.out.println("QUERY -> " + queryString);
			Query query = manager.createQuery(queryString);
			return query.getResultList();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Collection findLogAsientoFacturaAnulada(FacturaIf factura) throws GenericBusinessException {
			String queryString = "select distinct la from LogAsientoEJB la where la.tipoDocumentoId = " + factura.getTipodocumentoId() + " and la.transaccionId = " + factura.getId();
			System.out.println("QUERY -> " + queryString);
			Query query = manager.createQuery(queryString);
			return query.getResultList();
		}

		public void procesarReactivacionFacturasAnuladas(FacturaIf factura,LogCarteraIf logCartera, LogAsientoIf logAsiento, UsuarioIf usuario)
		throws GenericBusinessException {
			try {
				Iterator logCarteraDetalleIterator = logCarteraDetalleLocal.findLogCarteraDetalleByLogCarteraId(logCartera.getId()).iterator();
				Iterator logAsientoDetalleIterator = logAsientoDetalleLocal.findLogAsientoDetalleByLogAsientoId(logAsiento.getId()).iterator();
				FacturaEJB facturaLocal = registrarFactura(factura);
				facturaLocal.setEstado("C");
				manager.merge(facturaLocal);
				PedidoEJB pedido = manager.find(PedidoEJB.class, factura.getPedidoId());
				pedido.setEstado("C");
				manager.merge(pedido);
				CarteraEJB carteraFactura = registrarCartera(logCartera);
				manager.persist(carteraFactura);
				while (logCarteraDetalleIterator.hasNext()) {
					LogCarteraDetalleIf logCarteraDetalleModel = (LogCarteraDetalleIf) logCarteraDetalleIterator.next();
					CarteraDetalleEJB carteraDetalle = registrarCarteraDetalle(logCarteraDetalleModel);
					carteraDetalle.setId(null);
					carteraDetalle.setCarteraId(carteraFactura.getPrimaryKey());
					manager.merge(carteraDetalle);
					LogCarteraDetalleEJB logCarteraDetalle = manager.find(LogCarteraDetalleEJB.class, logCarteraDetalleModel.getId());
					manager.remove(logCarteraDetalle);
				}

				LogCarteraEJB logCarteraLocal = manager.find(LogCarteraEJB.class,logCartera.getId());
				manager.remove(logCarteraLocal);

				AsientoIf asientoFactura = asientoLocal.registrarAsiento(logAsiento);
				asientoFactura.setStatus("P");
				manager.persist(asientoFactura);

				while (logAsientoDetalleIterator.hasNext()) {
					LogAsientoDetalleIf logAsientoDetalleModel = (LogAsientoDetalleIf) logAsientoDetalleIterator.next();
					AsientoDetalleEJB asientoDetalle = registrarAsientoDetalle(logAsientoDetalleModel);
					asientoDetalle.setAsientoId(asientoFactura.getPrimaryKey());
					manager.merge(asientoDetalle);
					LogAsientoDetalleEJB logAsientoDetalle = manager.find(LogAsientoDetalleEJB.class, logAsientoDetalleModel.getId());
					manager.remove(logAsientoDetalle);
				}

				LogAsientoEJB logAsientoLocal = manager.find(LogAsientoEJB.class,logAsiento.getId());
				manager.remove(logAsientoLocal);
			} catch (GenericBusinessException e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(e.getMessage());
			} catch (Exception e) {
				ctx.setRollbackOnly();
				e.printStackTrace();
				throw new GenericBusinessException(
				"Se ha producido un error al guardar la Factura");
			}
		}

		public Collection<ClienteIf> getClienteConFacturaByMap_FechaInicio_FechaFin( Map<String, Object>
		mapaFacturas, Date fechaInicio, Date fechaFin) throws GenericBusinessException {

			String objectName = "f";
			String where = QueryBuilder.buildWhere(mapaFacturas, objectName);
			String queryString = "select distinct c " +
			" from FacturaEJB f, ClienteOficinaEJB co, ClienteEJB c " +
			" where f.clienteoficinaId = co.id and co.clienteId = c.id " +
			" and " + where+
			" and f.fechaFactura >= :fechaInicio and f.fechaFactura <= :fechaFin ";

			/*if (idDocumentos == null || idDocumentos.size() == 0){
				throw new GenericBusinessException("Tipos de documentos no establecidos para la busqueda !!"); 
			}
			queryString += " and ( ";
			for ( Long id : idDocumentos ){
				queryString += (" f.tipodocumentoId = "+id+" or");
			}
			queryString = queryString.substring(0,queryString.length()-2);
			queryString += " ) ";*/

			Query query = manager.createQuery(queryString);
			Set keys = mapaFacturas.keySet();
			Iterator it = keys.iterator();
			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = mapaFacturas.get(propertyKey);
				query.setParameter(propertyKey, property);
			}

			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);

			return query.getResultList();


		}

		public Collection getRetencionesDeFacturas( Set<Long> documentosId, Map<String,Object> mapaDatos,
				Date fechaInicio, Date fechaFin, int tipoResultado) throws GenericBusinessException {

			String estadoFactura = (String) mapaDatos.get("estadoFactura");
			if ( estadoFactura == null )
				throw new GenericBusinessException("Estado de Factura no definido !!");
			Long clienteId = (Long) mapaDatos.get("clienteId");
			Long facturaId = (Long) mapaDatos.get("facturaId");

			String queryString = "";
			if (DimmConstantes.TIPO_RESULTADO_RETENCION_CLIENTES == tipoResultado ||
					DimmConstantes.TIPO_RESULTADO_RETENCION_CLIENTES_FACTURAS_PASADAS == tipoResultado) {
				queryString += "select distinct cl ";
			} else if (DimmConstantes.TIPO_RESULTADO_RETENCION_FACTURAS == tipoResultado){
				queryString += "select distinct f ";
			} else if (DimmConstantes.TIPO_RESULTADO_RETENCION_RETENCIONES == tipoResultado ||
					DimmConstantes.TIPO_RESULTADO_RETENCION_RETENCIONES_FACTURAS_PASADAS == tipoResultado){
				queryString += "select distinct cd1 ";
			}

			queryString += " from CarteraEJB car1,CarteraDetalleEJB cd1, CarteraAfectaEJB ca, CarteraDetalleEJB cd2,CarteraEJB car, FacturaEJB f, ClienteEJB cl, ClienteOficinaEJB co" +
			" where car1.id = cd1.carteraId and cd1.id = ca.carteradetalleId and ca.carteraafectaId = cd2.id " +
			" and f.clienteoficinaId = co.id and co.clienteId = cl.id" +
			" and cd2.carteraId = car.id and car.referenciaId = f.id and car.tipodocumentoId = f.tipodocumentoId" +
			//" and cd1.fechaCartera >= :fechaInicio and cd1.fechaCartera <= :fechaFin " +
			" and car1.fechaEmision >= :fechaInicio and car1.fechaEmision <= :fechaFin " +
			" and f.estado = :estadoFactura ";

			/*if ( UtilesDimm.TIPO_RESULTADO_RETENCION_RETENCIONES == tipoResultado )
				queryString += " and f.fechaFactura >= :fechaInicio and f.fechaFactura <= :fechaFin ";
			else */
			if ( DimmConstantes.TIPO_RESULTADO_RETENCION_RETENCIONES_FACTURAS_PASADAS == tipoResultado ||
					DimmConstantes.TIPO_RESULTADO_RETENCION_CLIENTES_FACTURAS_PASADAS  == tipoResultado )
				queryString += " and f.fechaFactura < :fechaInicio ";

			if (DimmConstantes.TIPO_RESULTADO_RETENCION_FACTURAS == tipoResultado){
				if ( clienteId == null  )
					throw new GenericBusinessException("Id de cliente no definido para busqueda de Retenciones !!");
				queryString += " and cl.id = :clienteId ";
			} else if (DimmConstantes.TIPO_RESULTADO_RETENCION_RETENCIONES == tipoResultado ||
					DimmConstantes.TIPO_RESULTADO_RETENCION_RETENCIONES_FACTURAS_PASADAS == tipoResultado){
				if ( facturaId == null  )
					throw new GenericBusinessException("Id de Factura no definido para busqueda de Retenciones !!");
				queryString += " and f.id = :facturaId ";
			} 

			if (documentosId == null || documentosId.size() == 0){
				throw new GenericBusinessException("Tipos de documentos no establecidos para la busqueda !!"); 
			}
			queryString += " and ( ";
			for ( Long id : documentosId ){
				queryString += (" cd1.documentoId = "+id+" or");
			}
			queryString = queryString.substring(0,queryString.length()-2);
			queryString += " ) ";

			Query query = manager.createQuery(queryString);
			
			java.sql.Timestamp timeInicio = new java.sql.Timestamp(fechaInicio.getTime());
			timeInicio = utilitariosLocal.resetTimestampStartDate(timeInicio);
			fechaInicio = new java.sql.Date(timeInicio.getTime());
			query.setParameter("fechaInicio", fechaInicio);
			
			java.sql.Timestamp timeFin = new java.sql.Timestamp(fechaFin.getTime());
			timeFin = utilitariosLocal.resetTimestampEndDate(timeFin);
			fechaFin = new java.sql.Date(timeFin.getTime());
			query.setParameter("fechaFin", fechaFin);
			
			query.setParameter("estadoFactura", estadoFactura);
			if (DimmConstantes.TIPO_RESULTADO_RETENCION_FACTURAS == tipoResultado && clienteId != null ){
				query.setParameter("clienteId", clienteId);
			} else if ( (DimmConstantes.TIPO_RESULTADO_RETENCION_RETENCIONES == tipoResultado ||
					DimmConstantes.TIPO_RESULTADO_RETENCION_RETENCIONES_FACTURAS_PASADAS == tipoResultado) && facturaId != null  ){
				query.setParameter("facturaId", facturaId);
			}

			return query.getResultList();
		}

		public void procesarFixFechasFacturas(Long idEmpresa, UsuarioIf usuario)  throws GenericBusinessException {
			try {
				Iterator facturasIt = findFacturaByEstado("X").iterator();
				while (facturasIt.hasNext()) {
					FacturaIf factura = (FacturaIf) facturasIt.next();
					System.out.println("Procesando Factura # " + factura.getPreimpresoNumero() + " ...");
					// 0) Cambiar estado de factura
					factura.setEstado("C");
					saveFactura(factura);
					System.out.println("Factura corregida... OK");
					// 1) Recuperar pedido y corregir fecha del pedido
					if (factura.getPedidoId() != null) {
						PedidoIf pedido = pedidoLocal.getPedido(factura.getPedidoId());
						pedido.setFechaPedido(factura.getFechaFactura());
						pedidoLocal.savePedido(pedido);
						System.out.println("Pedido corregido... OK");
					}
					// 2) Recuperar asiento de factura y corregir fecha de asiento
					Map parameterMap = new HashMap();
					parameterMap.put("tipoDocumentoId", factura.getTipodocumentoId());
					parameterMap.put("transaccionId", factura.getId());
					AsientoIf asientoFactura = (AsientoIf) asientoLocal.findAsientoByQuery(parameterMap).iterator().next();			
					if (asientoFactura.getStatus().equals("P"))
						asientoFactura.setStatus("X");
					else
						asientoFactura.setStatus("Y");
					asientoLocal.saveAsiento(asientoFactura);
					System.out.println("Asiento factura corregido... OK");
					// 3) Recuperar cartera de factura y corregir fecha de cartera
					parameterMap = new HashMap();
					parameterMap.put("tipodocumentoId", factura.getTipodocumentoId());
					parameterMap.put("referenciaId", factura.getId());
					CarteraIf carteraFactura = (CarteraIf) findCarteraByQuery(parameterMap).iterator().next();
					carteraFactura.setFechaEmision(factura.getFechaFactura());
					carteraFactura.setEstado("X");
					saveCartera(carteraFactura);
					System.out.println("Cartera factura corregida... OK");
					// 4) Recuperar cartera_detalle de factura y corregir fecha de cartera
					Iterator carteraDetalleFacturaIt = carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraFactura.getId()).iterator();
					while (carteraDetalleFacturaIt.hasNext()) {
						CarteraDetalleIf carteraDetalleFactura = (CarteraDetalleIf) carteraDetalleFacturaIt.next();
						carteraDetalleFactura.setFechaCartera(new java.sql.Date(factura.getFechaFactura().getTime()));
						carteraDetalleLocal.saveCarteraDetalle(carteraDetalleFactura);
						System.out.println("Cartera detalle factura corregido... OK");
						// 5) Recuperar cartera_afecta y corregir fecha de aplicación
						Collection carteraAfectaCollection = carteraAfectaLocal.findCarteraAfectaByCarteraafectaId(carteraDetalleFactura.getId());
						Iterator carteraAfectaIterator = carteraAfectaCollection.iterator();
						while (carteraAfectaIterator.hasNext()) {
							CarteraAfectaIf carteraAfecta = (CarteraAfectaIf) carteraAfectaIterator.next();
							carteraAfecta.setFechaAplicacion(new java.sql.Date(factura.getFechaFactura().getTime()));
							carteraAfectaLocal.saveCarteraAfecta(carteraAfecta);
							System.out.println("Cartera afecta corregida... OK");
							// 6) Recuperar cartera_detalle de comprobantes de pago y corregir fecha de cartera
							CarteraDetalleIf carteraDetalleComprobanteIngreso = carteraDetalleLocal.getCarteraDetalle(carteraAfecta.getCarteradetalleId());
							carteraDetalleComprobanteIngreso.setFechaCartera(new java.sql.Date(factura.getFechaFactura().getTime()));
							carteraDetalleLocal.saveCarteraDetalle(carteraDetalleComprobanteIngreso);
							System.out.println("Cartera detalle comprobante ingreso corregido... OK");
							// 7) Recuperar cartera de comprobante de pago y corregir fecha de cartera
							CarteraIf carteraComprobanteIngreso = (CarteraIf) findCarteraById(carteraDetalleComprobanteIngreso.getCarteraId()).iterator().next();
							carteraComprobanteIngreso.setFechaEmision(factura.getFechaFactura());
							carteraComprobanteIngreso.setEstado("X");
							saveCartera(carteraComprobanteIngreso);
							System.out.println("Cartera comprobante ingreso corregida... OK");
							//8) Recuperar asiento de comprobante de pago y corregir fecha de asiento
							parameterMap = new HashMap();
							parameterMap.put("tipoDocumentoId", carteraComprobanteIngreso.getTipodocumentoId());
							parameterMap.put("transaccionId", carteraComprobanteIngreso.getId());
							AsientoIf asientoComprobanteIngreso = (AsientoIf) asientoLocal.findAsientoByQuery(parameterMap).iterator().next();
							if (asientoComprobanteIngreso.getStatus().equals("P"))
								asientoComprobanteIngreso.setStatus("X");
							else
								asientoComprobanteIngreso.setStatus("Y");
							asientoLocal.saveAsiento(asientoComprobanteIngreso);
							System.out.println("Asiento comprobante ingreso corregido... OK");
						}
					}
					//9) Recuperar movimiento y cambiar fecha de movimiento
					/*TipoDocumentoIf tipoDocumentoFactura = tipoDocumentoLocal.getTipoDocumento(factura.getTipodocumentoId());
					TipoDocumentoIf tipoDocumentoMovimiento = null;
					parameterMap = new HashMap();
					parameterMap.put("empresaId", idEmpresa);
					if (tipoDocumentoFactura.getCodigo().equals("FAC") || tipoDocumentoFactura.getCodigo().equals("VTA"))
						parameterMap.put("codigo", "EGM");
					else if (tipoDocumentoFactura.getCodigo().equals("DEV"))
						parameterMap.put("codigo", "INM");
					tipoDocumentoMovimiento = (TipoDocumentoIf) tipoDocumentoLocal.findTipoDocumentoByQuery(parameterMap).iterator().next();*/
					parameterMap = new HashMap();
					parameterMap.put("tipodocumentoOrigenId", factura.getTipodocumentoId());
					parameterMap.put("referenciaId", factura.getId());
					Iterator movimientoIterator = movimientoSessionLocal.findMovimientoByQuery(parameterMap).iterator();
					while (movimientoIterator.hasNext()) {
						MovimientoIf movimiento = (MovimientoIf) movimientoIterator.next();
						movimiento.setFechaDocumento(factura.getFechaFactura());
						movimientoSessionLocal.saveMovimiento(movimiento);
						System.out.println("Movimiento de inventario corregido... OK");
						//10) Recuperar asiento de movimiento y corregir fecha de asiento
						parameterMap = new HashMap();
						parameterMap.put("tipoDocumentoId", movimiento.getTipodocumentoId());
						parameterMap.put("transaccionId", movimiento.getId());
						AsientoIf asientoMovimiento = (AsientoIf) asientoLocal.findAsientoByQuery(parameterMap).iterator().next();
						if (asientoMovimiento.getStatus().equals("P"))
							asientoMovimiento.setStatus("X");
						else
							asientoMovimiento.setStatus("Y");
						asientoLocal.saveAsiento(asientoMovimiento);
						System.out.println("Asiento movimiento de inventario corregido... OK");
					}
					System.out.println("Proceso Factura # " + factura.getPreimpresoNumero() + " FINALIZADO");
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}

		public void procesarFixTotalesFacturas()  throws GenericBusinessException {
				//SELECT F.ID, SUM(FD.VALOR), SUM(FD.DESCUENTO), SUM(FD.IVA) FROM FACTURA F, FACTURA_DETALLE FD WHERE FD.FACTURA_ID = F.ID GROUP BY F.ID ORDER BY ID
				String queryString = "select distinct f, sum(fd.valor), sum(fd.descuento), sum(fd.iva) from FacturaEJB f, FacturaDetalleEJB fd where fd.facturaId = f.id group by f.id order by f.id";
				Query query = manager.createQuery(queryString);
				Iterator it = query.getResultList().iterator();
				while (it.hasNext()) {
					Object[] object = (Object[]) it.next();
					FacturaIf factura = (FacturaIf) object[0];
					BigDecimal valor = (BigDecimal) object[1];
					BigDecimal descuento = (BigDecimal) object[2];
					BigDecimal iva = (BigDecimal) object[3];
					factura.setValor(valor);
					factura.setDescuento(descuento);
					factura.setIva(iva);
					saveFactura(factura);
				}
		}
		
		public void procesarFixCaja()  throws GenericBusinessException {
			FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter("c:/prueba.txt");
	            pw = new PrintWriter(fichero);

	           

	        
			
			// SELECT * FROM VENTAS_CONSOLIDADO ORDER BY FECHA_CIERRE ASC
			String queryString = "select vc from VentasConsolidadoEJB vc order by vc.fechaCierre asc";
			Query queryVentasConsolidado = manager.createQuery(queryString);
			Iterator<VentasConsolidadoIf> ventasConsolidadoIterador = queryVentasConsolidado.getResultList().iterator();
			double valorCajaInicial = 0D;
			int i = 0;
			while (ventasConsolidadoIterador.hasNext()) {
				VentasConsolidadoIf ventasConsolidado = ventasConsolidadoIterador.next();
				double valorCajaFinal = 0D;
				if (ventasConsolidado.getFechaCierre() != null) {
					if (i==0) {
						valorCajaInicial = ventasConsolidado.getValorCajaInicial().doubleValue();
						i++;
					}
					valorCajaFinal = valorCajaInicial;
					ventasConsolidado.setValorCajaInicial(BigDecimal.valueOf(valorCajaInicial));
					pw.println(ventasConsolidado.getFechaApertura().toString() + "\t\t\t SALDO INICIAL = \t" + utilitariosLocal.redondeoValor(valorCajaInicial));
					//SELECT DISTINCT CARDET.ID, DOC.NOMBRE, CARDET.VALOR FROM CARTERA_DETALLE CARDET, DOCUMENTO DOC WHERE CARDET.ID IN (SELECT DISTINCT CA.CARTERADETALLE_ID FROM CARTERA_AFECTA CA WHERE CA.CARTERAAFECTA_ID IN (SELECT DISTINCT CD.ID FROM FACTURA F, CARTERA C, CARTERA_DETALLE CD WHERE F.FECHA_FACTURA <= '2010-05-20 17:20:13' AND F.FECHA_FACTURA >= '2010-05-20 09:21:59' AND F.TIPODOCUMENTO_ID = C.TIPODOCUMENTO_ID AND F.ID = C.REFERENCIA_ID AND C.ID = CD.CARTERA_ID)) AND CARDET.DOCUMENTO_ID = DOC.ID AND DOC.EFECTIVO = 'S'
					//SELECT DISTINCT CARDET.ID, DOC.NOMBRE, CARDET.VALOR FROM CARTERA_DETALLE CARDET, DOCUMENTO DOC WHERE CARDET.ID IN (SELECT DISTINCT CA.CARTERADETALLE_ID FROM CARTERA_AFECTA CA WHERE CA.CARTERAAFECTA_ID IN (SELECT DISTINCT CD.ID FROM FACTURA F, CARTERA C, CARTERA_DETALLE CD, VENTAS_DOCUMENTOS VD, VENTAS_TRACK VT WHERE VD.TABLA_ID = F.ID AND VT.ID = VD.VENTASTRACK_ID AND VT.FECHA_VENTA <= '2009-06-24 21:15:57' AND VT.FECHA_VENTA >= '2009-06-24 13:11:26' AND F.TIPODOCUMENTO_ID = C.TIPODOCUMENTO_ID AND F.ID = C.REFERENCIA_ID AND C.ID = CD.CARTERA_ID)) AND CARDET.DOCUMENTO_ID = DOC.ID AND DOC.EFECTIVO = 'S'
					//SELECT DISTINCT VP.* FROM FACTURA F, VENTAS_DOCUMENTOS VD, VENTAS_TRACK VT, VENTAS_PAGOS VP, TIPO_PAGO TP WHERE VD.TABLA_ID = F.ID AND VT.ID = VD.VENTASTRACK_ID AND VP.VENTASTRACK_ID = VT.ID AND VT.FECHA_VENTA <= '2009-06-24 21:15:57' AND VT.FECHA_VENTA >= '2009-06-24 13:11:26' AND VP.TIPO = TP.ID AND TP.CODIGO = 'EF'
					//SELECT DISTINCT VP.* FROM VENTAS_DOCUMENTOS VD, VENTAS_TRACK VT, VENTAS_PAGOS VP, TIPO_PAGO TP WHERE VT.ID = VD.VENTASTRACK_ID AND VP.VENTASTRACK_ID = VT.ID AND VT.FECHA_VENTA < '2010-12-24 00:00:00' AND VT.FECHA_VENTA >= '2010-12-23 00:00:00' AND VP.TIPO = TP.ID AND TP.CODIGO = 'EF'
					//queryString = "select distinct cardet, doc from CarteraDetalleEJB cardet, DocumentoEJB doc where cardet.id in (select distinct ca.carteradetalleId from CarteraAfectaEJB ca where ca.carteraafectaId in (select distinct cd.id from FacturaEJB f, CarteraEJB c, CarteraDetalleEJB cd, VentasDocumentosEJB vd, VentasTrackEJB vt where vd.tablaId = f.id and vt.id = vd.ventastrackId and vt.fechaVenta <= :fechaCierre and vt.fechaVenta >= :fechaApertura and f.tipodocumentoId = c.tipodocumentoId and f.id = c.referenciaId and c.id = cd.carteraId)) and cardet.documentoId = doc.id and doc.efectivo = 'S'";
					//queryString = "select distinct vp from FacturaEJB f, VentasDocumentosEJB vd, VentasTrackEJB vt, VentasPagosEJB vp, TipoPagoEJB tp where vd.tablaId = f.id and vt.id = vd.ventastrackId and vp.ventastrackId = vt.id and vt.fechaVenta <= :fechaCierre and vt.fechaVenta >= :fechaApertura and vp.tipo = tp.id and tp.codigo = 'EF'";
					queryString = "select distinct vp from VentasDocumentosEJB vd, VentasTrackEJB vt, VentasPagosEJB vp, TipoPagoEJB tp where vt.id = vd.ventastrackId and vp.ventastrackId = vt.id and vt.fechaVenta <= :fechaCierre and vt.fechaVenta >= :fechaApertura and vp.tipo = tp.id and tp.codigo = 'EF'";
					Query query = manager.createQuery(queryString);
					query.setParameter("fechaCierre", ventasConsolidado.getFechaCierre());
					query.setParameter("fechaApertura", ventasConsolidado.getFechaApertura());
					//Iterator<Object[]> it = query.getResultList().iterator();
					Iterator<VentasPagosIf> it = query.getResultList().iterator();
					double valorEfectivo = 0D;	
					while (it.hasNext()) {
						//Object[] objeto = it.next();
						//CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) objeto[0];
						VentasPagosIf ventasPagos = it.next();
						valorEfectivo += ventasPagos.getValor().doubleValue();
						pw.println("\t\t\t\t +" + utilitariosLocal.redondeoValor(ventasPagos.getValor().doubleValue()));
					}
					ventasConsolidado.setValorEfectivo(BigDecimal.valueOf(valorEfectivo));
					valorCajaFinal += valorEfectivo;
					queryString = "select distinct csm from CajasesionMovimientosEJB csm where csm.fecha <= :fechaCierre and csm.fecha >= :fechaApertura";
					query = manager.createQuery(queryString);
					query.setParameter("fechaCierre", ventasConsolidado.getFechaCierre());
					query.setParameter("fechaApertura", ventasConsolidado.getFechaApertura());
					Iterator<CajasesionMovimientosIf> cajaSesionMovimientosIterador = query.getResultList().iterator();
					double valorEgresos = 0D;
					double valorIngresos = 0D;
					while (cajaSesionMovimientosIterador.hasNext()) {
						CajasesionMovimientosIf csm = cajaSesionMovimientosIterador.next();
						if (csm.getTipomovimiento().equals("E")) {
							valorEgresos += csm.getValor().doubleValue();
							pw.println("\t\t\t\t " + utilitariosLocal.redondeoValor(csm.getValor().negate().doubleValue()));
						} else if (csm.getTipomovimiento().equals("I"))
							valorIngresos += csm.getValor().doubleValue();
					}
					ventasConsolidado.setValorCajaEgreso(BigDecimal.valueOf(valorEgresos));
					ventasConsolidado.setValorCajaIngreso(BigDecimal.valueOf(valorIngresos));
					valorCajaFinal += valorIngresos;
					valorCajaFinal -= valorEgresos;
					ventasConsolidadoLocal.saveVentasConsolidado(ventasConsolidado);
					valorCajaInicial = valorCajaFinal;
					pw.print("\n");
					pw.println(ventasConsolidado.getFechaCierre().toString() + "\t\t\t SALDO FINAL = \t" + utilitariosLocal.redondeoValor(valorCajaFinal) + "\n");
				}
			}
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           // Nuevamente aprovechamos el finally para 
	           // asegurarnos que se cierra el fichero.
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
		}
		
		public java.util.Collection<FacturaIf> findFacturasParaCalculoTotalVentas(Long idEmpresa, java.sql.Date firstDate, java.sql.Date lastDate) throws GenericBusinessException {
			//SELECT DISTINCT SUM(F.VALOR-F.DESCUENTO) FROM FACTURA F, TIPO_DOCUMENTO TD WHERE F.ESTADO <> 'A' AND TD.ID = F.TIPODOCUMENTO_ID AND TD.SIGNOCARTERA = '+'AND F.FECHA_FACTURA >= '2011-07-01' AND F.FECHA_FACTURA <= '2011-07-21' AND EMPRESA = 1
			String queryString = "select distinct f from FacturaEJB f, TipoDocumentoEJB td where f.estado <> 'A' and td.id = f.tipodocumentoId and td.signocartera = '+' and f.fechaFactura >= :fechaInicial and f.fechaFactura <= :fechaFinal and td.empresaId = :idEmpresa";
			Query query = manager.createQuery(queryString);
			query.setParameter("fechaInicial", firstDate);
			query.setParameter("fechaFinal", lastDate);
			query.setParameter("idEmpresa", idEmpresa);
			return query.getResultList();
		}
		
		public java.util.Collection<FacturaIf> findDevolucionesParaCalculoTotalVentas(Long idEmpresa, java.sql.Date firstDate, java.sql.Date lastDate) throws GenericBusinessException {
			//SELECT DISTINCT SUM(F.VALOR-F.DESCUENTO) FROM FACTURA F, TIPO_DOCUMENTO TD WHERE F.ESTADO <> 'A' AND TD.ID = F.TIPODOCUMENTO_ID AND TD.SIGNOCARTERA = '-'AND F.FECHA_FACTURA >= '2011-07-01' AND F.FECHA_FACTURA <= '2011-07-21' AND EMPRESA = 1
			String queryString = "select distinct f from FacturaEJB f, TipoDocumentoEJB td where f.estado <> 'A' and td.id = f.tipodocumentoId and td.signocartera = '-' and f.fechaFactura >= :fechaInicial and f.fechaFactura <= :fechaFinal and td.empresaId = :idEmpresa";
			Query query = manager.createQuery(queryString);
			query.setParameter("fechaInicial", firstDate);
			query.setParameter("fechaFinal", lastDate);
			query.setParameter("idEmpresa", idEmpresa);
			return query.getResultList();
		}
		
		public java.util.Collection<FacturaIf> findFacturaPreimpresoDuplicadoByQuery(Map queryMap) throws GenericBusinessException {
			String queryString = "select distinct f from FacturaEJB f, TipoDocumentoEJB td where f.tipodocumentoId = td.id and td.factura = :tipoDocumentoFactura and td.notaVenta = :tipoDocumentoNotaVenta and td.notaCredito = :tipoDocumentoNotaCredito and td.empresaId = :idEmpresa and f.preimpresoNumero = :preimpresoNumero";
			Query query = manager.createQuery(queryString);
			query.setParameter("idEmpresa", (Long) queryMap.get("idEmpresa"));
			query.setParameter("tipoDocumentoFactura", (String) queryMap.get("tipoDocumentoFactura"));
			query.setParameter("tipoDocumentoNotaVenta", (String) queryMap.get("tipoDocumentoNotaVenta"));
			query.setParameter("tipoDocumentoNotaCredito", (String) queryMap.get("tipoDocumentoNotaCredito"));
			query.setParameter("preimpresoNumero", (String) queryMap.get("preimpresoNumero"));
			return query.getResultList();
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public java.util.Collection findCompraAsociadaByQuery(Map aMap) {
			return queryManagerLocal.singleClassQueryList(CompraEJB.class, aMap);      
		}
}
