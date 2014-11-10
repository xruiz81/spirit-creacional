package com.spirit.cartera.session;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.bpm.compras.exception.ComprasBpmException;
import com.spirit.cartera.data.ObservacionesPago;
import com.spirit.cartera.entity.CarteraAfectaData;
import com.spirit.cartera.entity.CarteraAfectaEJB;
import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraData;
import com.spirit.cartera.entity.CarteraDetalleData;
import com.spirit.cartera.entity.CarteraDetalleEJB;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraEJB;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.CarteraPagoEJB;
import com.spirit.cartera.entity.CarteraRelacionadaEJB;
import com.spirit.cartera.entity.CarteraRelacionadaIf;
import com.spirit.cartera.entity.LogCarteraDetalleEJB;
import com.spirit.cartera.entity.LogCarteraEJB;
import com.spirit.cartera.entity.LogCarteraIf;
import com.spirit.cartera.entity.NotaCreditoDetalleIf;
import com.spirit.cartera.entity.NotaCreditoEJB;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.cartera.handler.AnticipoCompraReembolsoAsientoAutomaticoHandlerLocal;
import com.spirit.cartera.handler.AnticipoProveedorAsientoAutomaticoHandlerLocal;
import com.spirit.cartera.handler.AutomaticAccountingEntryHandlerLocal;
import com.spirit.cartera.handler.ComprobanteIngresoAsientoAutomaticoHandlerLocal;
import com.spirit.cartera.handler.CrossingWalletDetailData;
import com.spirit.cartera.handler.DescuentoProntoPagoAsientoAutomaticoHandlerLocal;
import com.spirit.cartera.handler.NotaCreditoAnticipoClienteAsientoAutomaticoHandlerLocal;
import com.spirit.cartera.handler.NotaCreditoProveedorAsientoAutomaticoHandlerLocal;
import com.spirit.cartera.handler.NotaInternaCreditoClienteAsientoAutomaticoHandlerLocal;
import com.spirit.cartera.handler.NotaInternaCreditoProveedorAsientoAutomaticoHandlerLocal;
import com.spirit.cartera.handler.PagoProveedorAsientoAutomaticoHandler;
import com.spirit.cartera.handler.PagoProveedorAsientoAutomaticoHandlerLocal;
import com.spirit.cartera.handler.PendingAccountData;
import com.spirit.cartera.handler.ReciboCajaAsientoAutomaticoHandlerLocal;
import com.spirit.cartera.handler.RetencionProveedorAsientoAutomaticoHandlerLocal;
import com.spirit.cartera.handler.TransferenciaDocumentoAsientoAutomaticoHandlerLocal;
import com.spirit.cartera.handler.WalletData;
import com.spirit.cartera.handler.WalletDetailData;
import com.spirit.cartera.session.generated._CarteraSession;
import com.spirit.client.SpiritConstants;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraEJB;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionData;
import com.spirit.compras.entity.CompraRetencionEJB;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.compras.entity.LogCompraRetencionEJB;
import com.spirit.compras.entity.OrdenAsociadaEJB;
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.compras.handler.CompraAsientoAutomaticoHandlerLocal;
import com.spirit.compras.handler.OrderData;
import com.spirit.compras.session.CompraDetalleSessionLocal;
import com.spirit.compras.session.CompraRetencionSessionLocal;
import com.spirit.compras.session.CompraSessionLocal;
import com.spirit.compras.session.LogCompraRetencionSessionLocal;
import com.spirit.compras.session.OrdenAsociadaSessionLocal;
import com.spirit.compras.session.OrdenCompraSessionLocal;
import com.spirit.compras.session.SolicitudCompraSessionLocal;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.comun.util.Retencion;
import com.spirit.comun.util.RetencionProveedorData;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoEJB;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.ChequeEmitidoEJB;
import com.spirit.contabilidad.entity.ChequeEmitidoIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.handler.ChequeDatos;
import com.spirit.contabilidad.handler.EstadoChequeEmitido;
import com.spirit.contabilidad.handler.OrigenCheque;
import com.spirit.contabilidad.session.AsientoDetalleSessionLocal;
import com.spirit.contabilidad.session.AsientoSessionLocal;
import com.spirit.contabilidad.session.ChequeEmitidoSessionLocal;
import com.spirit.contabilidad.session.EventoContableSessionLocal;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.ClienteOficinaSessionLocal;
import com.spirit.crm.session.ClienteSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.facturacion.entity.FacturaDetalleEJB;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaEJB;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoDetalleEJB;
import com.spirit.facturacion.entity.PedidoDetalleIf;
import com.spirit.facturacion.entity.PedidoEJB;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.session.FacturaDetalleSessionLocal;
import com.spirit.facturacion.session.FacturaSessionLocal;
import com.spirit.facturacion.session.PedidoDetalleSessionLocal;
import com.spirit.facturacion.session.PedidoSessionLocal;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.NumeradoresIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.PuntoImpresionIf;
import com.spirit.general.entity.ServidorIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.mdb.messages.bo.ActualizarPreimpresoCarteraMessageLocal;
import com.spirit.general.mdb.messages.bo.ReciboCajaPOSMessageLocal;
import com.spirit.general.mdb.messages.bo.TransferirDocPosMessageLocal;
import com.spirit.general.session.CajaSessionLocal;
import com.spirit.general.session.CiudadSessionLocal;
import com.spirit.general.session.CuentaBancariaSessionLocal;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.MonedaSessionLocal;
import com.spirit.general.session.NumeradoresSessionLocal;
import com.spirit.general.session.OficinaSessionLocal;
import com.spirit.general.session.ServidorSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.UsuarioSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.MovimientoSessionLocal;
import com.spirit.inventario.session.ProductoSessionLocal;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioFacturacionIf;
import com.spirit.medios.entity.PlanMedioFormaPagoIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoFacturacionIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.session.OrdenMedioSessionLocal;
import com.spirit.medios.session.PlanMedioFacturacionSessionLocal;
import com.spirit.medios.session.PlanMedioFormaPagoSessionLocal;
import com.spirit.medios.session.PlanMedioSessionLocal;
import com.spirit.medios.session.PresupuestoDetalleSessionLocal;
import com.spirit.medios.session.PresupuestoFacturacionSessionLocal;
import com.spirit.medios.session.PresupuestoSessionLocal;
import com.spirit.performance.session.PerformanceInterceptor;
import com.spirit.poscola.session.PosColaSessionLocal;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.sri.entity.SriClienteRetencionIf;
import com.spirit.sri.session.SriClienteRetencionSessionLocal;
import com.spirit.util.DeepCopy;
import com.spirit.util.Utilitarios;

/**
 * The <code>CarteraSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.6 $, $Date: 2014/07/14 23:32:37 $
 * 
 */
@Stateless
@Interceptors( { PerformanceInterceptor.class })
public class CarteraSessionEJB extends _CarteraSession implements
		CarteraSessionRemote, CarteraSessionLocal {
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	@EJB
	private EmpresaSessionLocal empresaLocal;
	@EJB
	private CarteraDetalleSessionLocal carteraDetalleLocal;
	@EJB
	private SolicitudCompraSessionLocal solicitudCompraLocal;
	@EJB
	private CarteraAfectaSessionLocal carteraAfectaLocal;
	@EJB
	private NumeradoresSessionLocal numeradoresLocal;
	@EJB
	private DocumentoSessionLocal documentoLocal;
	@EJB
	private TipoDocumentoSessionLocal tipoDocumentoLocal;
	@EJB
	private EventoContableSessionLocal eventoContableLocal;
	@EJB
	private AsientoSessionLocal asientoLocal;
	@EJB
	private AsientoDetalleSessionLocal asientoDetalleLocal;
	@EJB
	private MovimientoSessionLocal movimientoLocal;
	@EJB
	private PagoProveedorAsientoAutomaticoHandlerLocal pagoProveedorAsientoAutomaticoHandlerLocal;
	@EJB
	private UtilitariosSessionLocal utilitariosLocal;
	@EJB
	private ClienteOficinaSessionLocal clienteOficinaLocal;
	@EJB
	private ClienteSessionLocal clienteLocal;
	@EJB
	private ProductoSessionLocal productoLocal;
	@EJB
	private MonedaSessionLocal monedaLocal;
	@EJB
	private AutomaticAccountingEntryHandlerLocal automaticAccountingEntryHandlerLocal;
	@EJB
	private ComprobanteIngresoAsientoAutomaticoHandlerLocal comprobanteIngresoAsientoAutomaticoHandlerLocal;
	@EJB
	private AnticipoProveedorAsientoAutomaticoHandlerLocal anticipoProveedorAsientoAutomaticoHandlerLocal;
	@EJB
	private AnticipoCompraReembolsoAsientoAutomaticoHandlerLocal anticipoCompraReembolsoAsientoAutomaticoHandlerLocal;
	@EJB
	private DescuentoProntoPagoAsientoAutomaticoHandlerLocal descuentoProntoPagoAsientoAutomaticoHandlerLocal;
	@EJB
	private RetencionProveedorAsientoAutomaticoHandlerLocal retencionProveedorAsientoAutomaticoHandlerLocal;
	@EJB
	private NotaCreditoProveedorAsientoAutomaticoHandlerLocal notaCreditoProveedorAsientoAutomaticoHandlerLocal;
	@EJB
	private NotaInternaCreditoProveedorAsientoAutomaticoHandlerLocal notaInternaCreditoProveedorAsientoAutomaticoHandlerLocal;
	@EJB
	private NotaInternaCreditoClienteAsientoAutomaticoHandlerLocal notaInternaCreditoClienteAsientoAutomaticoHandlerLocal;
	@EJB
	private NotaCreditoAnticipoClienteAsientoAutomaticoHandlerLocal notaCreditoAnticipoClienteAsientoAutomaticoHandlerLocal;
	@EJB
	private ReciboCajaAsientoAutomaticoHandlerLocal reciboCajaAsientoAutomaticoHandlerLocal;
	@EJB
	private TransferenciaDocumentoAsientoAutomaticoHandlerLocal transferenciaDocumentoAsientoAutomaticoHandlerLocal;
	@EJB
	private SriClienteRetencionSessionLocal sriClienteRetencionLocal;
	@EJB
	private PedidoSessionLocal pedidoLocal;
	@EJB
	private FacturaDetalleSessionLocal facturaDetalleLocal;
	@EJB
	private CompraDetalleSessionLocal compraDetalleLocal;
	@EJB
	private CompraRetencionSessionLocal compraRetencionLocal;
	@EJB
	private FacturaSessionLocal facturaLocal;
	@EJB
	private CompraSessionLocal compraLocal;
	@EJB
	private NotaCreditoSessionLocal notaCreditoLocal;
	@EJB
	private CompraAsientoAutomaticoHandlerLocal compraAsientoAutomaticoHandlerLocal;
	//@EJB
	//private ProcesoPrincipalCompraLocal procesoPrincipalCompraLocal;
	@EJB
	private UsuarioSessionLocal usuarioLocal;
	@EJB
	private CajaSessionLocal cajaLocal;
	@EJB
	private OficinaSessionLocal oficinaLocal;
	@EJB
	private ServidorSessionLocal servidorLocal;
	@EJB
	private LogCarteraSessionLocal LogCarteraLocal;
	@EJB
	private LogCarteraDetalleSessionLocal LogCarteraDetalleLocal;
	@EJB
	private CuentaBancariaSessionLocal cuentaBancariaLocal;
	@EJB
	private ChequeEmitidoSessionLocal chequeEmitidoLocal;
	@EJB
	private LogCompraRetencionSessionLocal LogCompraRetencionLocal;
	@EJB
	private PedidoDetalleSessionLocal pedidoDetalleLocal;
	@EJB
	private JPAManagerLocal jpaManagerLocal;
	@EJB
	private CiudadSessionLocal ciudadLocal;
	@EJB
	private FormaPagoSessionLocal formaPagoLocal;
	@EJB private CarteraRelacionadaSessionLocal relatedWalletSessionLocal;
	//@EJB private ActualizarPreimpresoMessageLocal actualizarPreimpresoMessageLocal;
	@EJB private ReciboCajaPOSMessageLocal reciboCajaPOSMessageLocal;
	@EJB private PosColaSessionLocal posColaSessionLocal;
	@EJB private TransferirDocPosMessageLocal transferirDocPosMessageLocal;
	@EJB private ActualizarPreimpresoCarteraMessageLocal actualizarPreimpresoCarteraMessageLocal;
	@EJB private PresupuestoSessionLocal presupuestoLocal;
	@EJB private OrdenMedioSessionLocal ordenMedioLocal;
	@EJB private PresupuestoFacturacionSessionLocal presupuestoFacturacionLocal;
	@EJB private OrdenAsociadaSessionLocal ordenAsociadaSessionLocal;
	@EJB private OrdenCompraSessionLocal ordenCompraSessionLocal;
	@EJB private OrdenMedioSessionLocal ordenMedioSessionLocal;
	@EJB private PlanMedioSessionLocal planMedioSessionLocal;
	@EJB private PlanMedioFacturacionSessionLocal planMedioFacturacionSessionLocal;
	@EJB private PlanMedioFormaPagoSessionLocal planMedioFormaPagoSessionLocal;
	
	@EJB private PresupuestoDetalleSessionLocal presupuestoDetalleLocal;
	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(CarteraSessionEJB.class);

	private DecimalFormat formatoSerial = new DecimalFormat("00000");
	private DecimalFormat formatoSerialMes = new DecimalFormat("00");
	private static final String TIPO_FILTRO_CORPORACION = "CORP";
	private static final String TIPO_FILTRO_CLIENTE = "CLIE";
	private static final String TIPO_FILTRO_CLIENTE_OFICINA = "CLOF";
	private static final String CARTERA_DETALLE_DIFERIDA = "D";
	private static final String NOMBRE_ESTADO_ANULADO = "ANULADO";
	private static final String ESTADO_ANULADO = NOMBRE_ESTADO_ANULADO.substring(0, 1);
	private static final String ESTADO_TRANSFERIDO = "T";
	private static final String ESTADO_NORMAL = "N";
	private static final String ESTADO_PRESUPUESTO_FACTURACION_FACTURADO = "F";
	private static final String ESTADO_APROBADO = "A";
	private static final String ESTADO_PLAN_MEDIO_PEDIDO = "D";
	private static final String ESTADO_PLAN_MEDIO_FACTURADO = "F";
	private static final String ESTADO_PLAN_MEDIO_EN_PROCESO = "N";
	private static final String ESTADO_PLAN_MEDIO_PENDIENTE = "P";

	private enum TipoLogCartera {
		COMPRA, FACTURA, RETENCION, FACTURA_ELIMINADA, NOTA_CREDITO
	}

	boolean nuevaCodificacionActiva = true;

	@Resource
	private SessionContext ctx;

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 * 
	 * @throws GenericBusinessException
	 **************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getClienteConCarteraCarteradetallebyFechaInicioFechaFin(Long idEmpresa, Date fechaInicio, Date fechaFin, Map aMap) throws GenericBusinessException {
		try {
			String queryString = "select distinct ca,cd from CarteraEJB ca, CarteraDetalleEJB cd, ClienteEJB c, ClienteOficinaEJB co," 
				+ "TipoDocumentoEJB td where ";
			//,DocumentoEJB d 
			//queryString += " cd.documentoId = d.id and d.codigo not like 'NCCG' and ca.id=cd.carteraId and";
			queryString += " ca.id=cd.carteraId and";
			queryString += " co.clienteId=c.id and ca.clienteoficinaId=co.id and td.empresaId = "+ idEmpresa;

			if (fechaInicio != null && fechaFin != null) {
				queryString += " and ca.fechaEmision >= :fechaInicio and ca.fechaEmision <= :fechaFin";
			}

			String where = QueryBuilder.buildWhere(aMap, "ca");
			queryString += (" and " + where);
			Query query = manager.createQuery(queryString);
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
			"Se ha producido un error al consultar Clientes que tienen Ventas");
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<CarteraIf> getClienteConCarterabyFechaInicioFechaFin(
			Long idEmpresa, Date fechaInicio, Date fechaFin, Map aMap)
			throws GenericBusinessException {
		try {
			String queryString = "select distinct ca from CarteraEJB ca, ClienteEJB c, ClienteOficinaEJB co,TipoDocumentoEJB td where ";
			queryString += " co.clienteId=c.id and ca.clienteoficinaId=co.id and td.empresaId = "+ idEmpresa;
			if (fechaInicio != null && fechaFin != null) {
				queryString += " and ca.fechaEmision >= :fechaInicio and ca.fechaEmision <= :fechaFin";
			}
			String where = QueryBuilder.buildWhere(aMap, "ca");
			queryString += (" and " + where);
			Query query = manager.createQuery(queryString);
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
			"Se ha producido un error al consultar Clientes que tienen Ventas");
		}
	}

	// busca el credito del cliente pero solo aquellas que tienen vigencia (1
	// mes)
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCarteraCreditoDisponible(Map aMap,
			Long idEmpresa) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e from CarteraEJB " + objectName
		+ ", TipoDocumentoEJB td where " + where;

		queryString += " and e.tipodocumentoId = td.id and td.empresaId = "
			+ idEmpresa;

		Date fechaServidor = null;
		try {
			fechaServidor = utilitariosLocal.getServerDateSql();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		queryString += " and e.id in (SELECT h.id from CarteraEJB h WHERE DATEDIFF('"
			+ fechaServidor + "',e.fechaEmision)<32)";

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
	public Collection findCarteraByCarteraDetalleId(Long idCarteraDetalle) {
		// select cd.* from cartera c, cartera_detalle cd, tipo_documento td
		// where c.TIPODOCUMENTO_ID = td.ID and c.TIPO = 'P' and c.SALDO > 0 and
		// td.CODIGO = 'COM' and td.EMPRESA_ID = 1
		String queryString = "select c from CarteraEJB c, CarteraDetalleEJB cd, TipoDocumentoEJB td where cd.carteraId = c.id and c.tipodocumentoId = td.id  and c.estado <> 'A' and cd.id="
			+ idCarteraDetalle;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCarterasCarterasAfectaQueCruzanReferenciaId(Long idReferencia, Long idTipoDocumento) throws GenericBusinessException  {
		//select cx.*, ca.* from CARTERA c, CARTERA_DETALLE cd, CARTERA_AFECTA ca, CARTERA_DETALLE cdx, CARTERA cx where
		//c.REFERENCIA_ID = 24842 and c.TIPODOCUMENTO_ID = 4 and c.ID = cd.CARTERA_ID and cd.ID = ca.CARTERAAFECTA_ID 
		//and ca.CARTERADETALLE_ID = cdx.id and cdx.CARTERA_ID = cx.ID 
		
		String queryString = "select cx, ca, cdx from CarteraEJB c, CarteraDetalleEJB cd, CarteraAfectaEJB ca, CarteraDetalleEJB cdx, CarteraEJB cx where " +
						"c.referenciaId = " + idReferencia + " and c.tipodocumentoId = " + idTipoDocumento + " and c.id = cd.carteraId and cd.id = ca.carteraafectaId " +
						"and ca.carteradetalleId = cdx.id and cdx.carteraId = cx.id";
		
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdByAprobadoAndByCartera(
			String tipo, String codigoTipoDocumento, Long idEmpresa,
			String aprobado, String cartera) throws GenericBusinessException {
		try {
			Date fechaServidor = null;
			try {
				fechaServidor = utilitariosLocal.getServerDateSql();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			String queryString = "select distinct c, cl from CarteraEJB c, ClienteEJB cl, ClienteOficinaEJB clo, CarteraDetalleEJB cd, TipoDocumentoEJB td "
				+ "where c.tipodocumentoId = td.id and c.id = cd.carteraId and c.clienteoficinaId = clo.id and clo.clienteId = cl.id and cd.diferido = 'D' and "
				+ "c.tipo = '" + tipo + "' and ";

			if (aprobado != null)
				queryString += ("c.aprobado = '" + aprobado + "'");
			else
				queryString += ("c.aprobado is null ");

			queryString += (" and td.codigo = '" + codigoTipoDocumento
					+ "' and td.empresaId = " + idEmpresa);
			queryString += (" and cd.cartera = '" + cartera + "'");

			if (fechaServidor != null)
				queryString += " and cd.fechaCartera <= :fechaActual";

			queryString += " order by cl.nombreLegal asc";
			Query query = manager.createQuery(queryString);

			if (fechaServidor != null)
				query.setParameter("fechaActual", fechaServidor);

			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al consultar carteras");
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdByAprobadoByCarteraAndByClienteOficinaId(
			String tipo, String codigoTipoDocumento, Long idEmpresa,
			String aprobado, String cartera, Long idClienteOficina)
	throws GenericBusinessException {
		try {
			Date fechaServidor = null;
			try {
				fechaServidor = utilitariosLocal.getServerDateSql();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			String queryString = "select distinct c, cl from CarteraEJB c, ClienteEJB cl, ClienteOficinaEJB clo, CarteraDetalleEJB cd, TipoDocumentoEJB td where c.tipodocumentoId = td.id and c.id = cd.carteraId and c.clienteoficinaId = clo.id and clo.clienteId = cl.id and cd.diferido = 'D' and c.tipo = '"
				+ tipo + "'";

			if (aprobado != null)
				queryString += (" and c.aprobado = '" + aprobado + "' ");
			else
				queryString += (" and c.aprobado is null ");

			queryString += (" and td.codigo = '" + codigoTipoDocumento
					+ "' and td.empresaId = " + idEmpresa
					+ " and cd.cartera = '" + cartera
					+ "' and c.clienteoficinaId = " + idClienteOficina);

			if (fechaServidor != null)
				queryString += " and cd.fechaCartera <= :fechaActual";

			queryString += (" order by cl.nombreLegal asc");

			Query query = manager.createQuery(queryString);
			if (fechaServidor != null)
				query.setParameter("fechaActual", fechaServidor);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al consultar carteras");
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdAndByAprobado(
			String tipo, String codigoTipoDocumento, Long idEmpresa,
			String aprobado) {
		// select c.* from cartera c, tipo_documento td where c.TIPODOCUMENTO_ID
		// = td.ID and c.TIPO = 'P' and c.SALDO > 0 and td.CODIGO = 'COM' and
		// td.EMPRESA_ID = 1
		String queryString = "select c from CarteraEJB c, TipoDocumentoEJB td where c.tipodocumentoId = td.id and c.tipo = '"
			+ tipo
			+ "' and c.saldo > 0.01 and c.aprobado = '"
			+ aprobado
			+ "' and td.codigo = '"
			+ codigoTipoDocumento
			+ "' and td.empresaId = "
			+ idEmpresa
			+ " and c.comentario is null";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdByAprobadoAndByClienteOficinaId(
			String tipo, String codigoTipoDocumento, Long idEmpresa,
			String aprobado, Long idClienteOficina) {
		// select c.* from cartera c, tipo_documento td where c.TIPODOCUMENTO_ID
		// = td.ID and c.TIPO = 'P' and c.SALDO > 0 and td.CODIGO = 'COM' and
		// td.EMPRESA_ID = 1 and c.CLIENTEOFICINA_ID = 1
		String queryString = "select c from CarteraEJB c, TipoDocumentoEJB td where c.tipodocumentoId = td.id and c.tipo = '"
			+ tipo
			+ "' and c.saldo > 0.01 and c.aprobado = '"
			+ aprobado
			+ "' and td.codigo = '"
			+ codigoTipoDocumento
			+ "' and td.empresaId = "
			+ idEmpresa
			+ " and c.clienteoficinaId = "
			+ idClienteOficina
			+ " and c.comentario is null";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCarteraByTipoBySaldoByTipoDocumentoCodigoByEmpresaIdAndByReferenciaId(
			String tipo, String codigoTipoDocumento, Long idEmpresa,
			Long idReferencia) {
		// select c.* from cartera c, tipo_documento td where c.TIPODOCUMENTO_ID
		// = td.ID and c.TIPO = 'P' and c.SALDO > 0 and c.REFERENCIA_ID = 900
		// and td.CODIGO = 'COM' and td.EMPRESA_ID = 1
		String queryString = "select c from CarteraEJB c, TipoDocumentoEJB td where c.tipodocumentoId = td.id and c.tipo = '"
			+ tipo
			+ "' and c.saldo > 0.01 and c.referenciaId = "
			+ idReferencia
			+ " and td.codigo = '"
			+ codigoTipoDocumento
			+ "' and td.empresaId = " + idEmpresa + "";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCarteraByTipoBySaldoByModuloCodigoAndByEmpresaId(
			String tipo, String codigoModulo, Long idEmpresa) {
		// select c.* from cartera c, tipo_documento td where c.TIPODOCUMENTO_ID
		// = td.ID and c.TIPO = 'P' and c.SALDO > 0 and td.CODIGO = 'COM' and
		// td.EMPRESA_ID = 1
		String queryString = "select c, cl, co from CarteraEJB c, ClienteEJB cl, ClienteOficinaEJB clo, TipoDocumentoEJB td, ModuloEJB m, CompraEJB co where c.tipodocumentoId = td.id and c.clienteoficinaId = clo.id and clo.clienteId = cl.id and c.tipo = '"
			+ tipo
			+ "' and c.saldo > 0.0 and td.moduloId = m.id and m.codigo = '"
			+ codigoModulo
			+ "' and td.empresaId = "
			+ idEmpresa
			+ " and c.referenciaId = co.id order by cl.razonSocial asc, co.preimpreso asc";
		// String queryString = "select c, cl from CarteraEJB c, ClienteEJB cl,
		// ClienteOficinaEJB clo, TipoDocumentoEJB td where c.tipodocumentoId =
		// td.id and c.clienteoficinaId = clo.id and clo.clienteId = cl.id and
		// c.tipo = '" + tipo + "' and c.saldo > 0.0 and td.empresaId = " +
		// idEmpresa + " order by cl.razonSocial asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCarteraByTipoBySaldoByModuloCodigoByEmpresaIdAndByClienteOficinaId(
			String tipo, String codigoModulo, Long idEmpresa,
			Long idClienteOficina) {
		// select c.* from cartera c, tipo_documento td where c.TIPODOCUMENTO_ID
		// = td.ID and c.TIPO = 'P' and c.SALDO > 0 and td.CODIGO = 'COM' and
		// td.EMPRESA_ID = 1 and c.CLIENTEOFICINA_ID = 273
		String queryString = "select distinct c, cl, co from CarteraEJB c, ClienteEJB cl, ClienteOficinaEJB clo, TipoDocumentoEJB td, ModuloEJB m, CompraEJB co where c.tipodocumentoId = td.id and c.clienteoficinaId = clo.id and clo.clienteId = cl.id and c.tipo = '"
			+ tipo
			+ "' and c.saldo > 0.0 and td.moduloId = m.id and m.codigo = '"
			+ codigoModulo
			+ "' and td.empresaId = "
			+ idEmpresa
			+ " and c.clienteoficinaId = "
			+ idClienteOficina
			+ " and c.referenciaId = co.id order by cl.razonSocial asc, co.preimpreso asc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<ClienteIf> getClienteConCarterabyFechaInicio_FechaFin(
			Long idEmpresa, Date fechaInicio, Date fechaFin, Map aMap)
			throws GenericBusinessException {
		try {
			String queryString = "select distinct c from CarteraEJB ca, ClienteEJB c, ClienteOficinaEJB co,TipoDocumentoEJB td where ";
			queryString += " co.clienteId=c.id and ca.clienteoficinaId=co.id and td.empresaId = "
				+ idEmpresa;
			if (fechaInicio != null && fechaFin != null) {
				queryString += " and ca.fechaEmision >= :fechaInicio and ca.fechaEmision <= :fechaFin";
			}
			String where = QueryBuilder.buildWhere(aMap, "ca");
			queryString += (" and " + where);
			Query query = manager.createQuery(queryString);
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);
			}
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
			"Se ha producido un error al consultar Clientes que tienen Ventas");
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<CarteraIf> getCarteraByDocumento_Cliente_FechaInicio_FechaFin(
			String codigoDocumento, Long clienteId, Date fechaInicio,
			Date fechaFin, java.lang.Long idEmpresa)
			throws GenericBusinessException {
		try {
			String queryString = "";
			if (clienteId != null){
				queryString = "select distinct e from CarteraEJB e,TipoDocumentoEJB td,ClienteOficinaEJB co, ClienteEJB c where ";
				queryString += "e.tipodocumentoId = td.id and e.clienteoficinaId=co.id and co.clienteId = c.id ";
				queryString += "and td.codigo='" + codigoDocumento + "' "+ "and td.empresaId=" + idEmpresa;
				queryString += "and c.id = :clienteId ";
			} else {
				queryString = "select distinct e from CarteraEJB e,TipoDocumentoEJB td ";
				queryString += "where e.tipodocumentoId = td.id ";
				queryString += " and td.codigo='" + codigoDocumento + "' "+ "and td.empresaId=" + idEmpresa;
			}

			queryString += " and e.estado = :estado ";

			if (fechaInicio != null && fechaFin != null) {
				queryString += " and e.fechaEmision >= :fechaInicio and e.fechaEmision <= :fechaFin";
			}
			Query query = manager.createQuery(queryString);
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);
			query.setParameter("estado", "N");
			if ( clienteId != null )
				query.setParameter("clienteId", clienteId);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al consultar Venta");
		}
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<CarteraIf> getCarteraByMap_FechaInicio_FechaFin(Map aMap,
			Date fechaInicio, Date fechaFin, java.lang.Long idEmpresa)
			throws GenericBusinessException {
		try {
			Long tipoDocumentoId = (Long) aMap.get("tipodocumentoId");
			Long clienteId = (Long) aMap.get("clienteId");
			String tipo = (String) aMap.get("tipo");

			String queryString = "select e from CarteraEJB e,TipoDocumentoEJB td,ClienteOficinaEJB co, ClienteEJB c where ";
			queryString += " e.clienteoficinaId = co.id and co.clienteId = c.id and e.tipodocumentoId = td.id ";
			queryString += " and td.id=" + tipoDocumentoId + " and c.id="
			+ clienteId + " and td.empresaId=" + idEmpresa
			+ " and e.tipo='" + tipo + "'";
			if (fechaInicio != null && fechaFin != null) {
				queryString += " and e.fechaEmision >= :fechaInicio and e.fechaEmision <= :fechaFin";
			}
			Query query = manager.createQuery(queryString);
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
			"Se ha producido un error al consultar Venta");
		}
	}


	public Collection<CarteraIf> getCarteraByMap_FechaInicio_FechaFin(Map aMap 
			, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin, java.lang.Long idEmpresa)
			throws GenericBusinessException {
		try {
			/*
			Long tipoDocumentoId = (Long) aMap.get("tipodocumentoId");
			Long clienteId = (Long) aMap.get("clienteId");
			String tipo = (String) aMap.get("tipo");*/

			
			String objectName = "e";
			String where = "";
			if (!aMap.isEmpty()) {
				where = QueryBuilder.buildWhere(aMap, objectName) + " and";
			}


			String queryString = "select distinct e,cd from CarteraEJB e,CarteraDetalleEJB cd,TipoDocumentoEJB td,ClienteOficinaEJB co, ClienteEJB c where "+ where;
			queryString += " e.clienteoficinaId = co.id and co.clienteId = c.id and e.tipodocumentoId = td.id and e.id = cd.carteraId";			
			queryString += " and td.empresaId = " + idEmpresa ;


			if (fechaInicio != null && fechaFin != null) {
				//queryString += " and e.fechaEmision >= :fechaInicio and e.fechaEmision <= :fechaFin";
				queryString += " and e.fechaEmision >= :fechaInicio and e.fechaEmision <= :fechaFin ";
				
			}
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
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
			"Se ha producido un error al consultar Venta");
		}
	}



	public List getObservacionesPago(Long clienteOficinaId) {
		ArrayList<ObservacionesPago> listaOservaciones = new ArrayList<ObservacionesPago>();

		String queryString="SELECT cartera,tipoDoc FROM CarteraEJB cartera,TipoDocumentoEJB tipoDoc where " +
		"cartera.tipodocumentoId=tipoDoc.id and " +
		"cartera.clienteoficinaId=:idCliente and " +
		"cartera.tipo='P' and " +
		"cartera.saldo > 0 and " +
		"tipoDoc.codigo not in ('CEG') and " +
		"tipoDoc.signocartera='-' and " +
		"cartera.estado='N'";
		Query q=manager.createQuery(queryString);
		q.setParameter("idCliente", clienteOficinaId);
		List listaResultado=q.getResultList();
		Object[] tmp=null;
		CarteraIf carteraIf=null;
		TipoDocumentoIf tipoDocumentoIf=null;
		for(int i=0;i<listaResultado.size();i++)
		{
			ObservacionesPago observacionesPago=new ObservacionesPago();
			tmp=(Object[])listaResultado.get(i);
			carteraIf=(CarteraIf)tmp[0];
			tipoDocumentoIf=(TipoDocumentoIf)tmp[1];
			observacionesPago.setFecha(carteraIf.getFechaEmision());
			observacionesPago.setCodigo(carteraIf.getCodigo());
			observacionesPago.setObservacion(carteraIf.getComentario());
			observacionesPago.setSaldo(carteraIf.getSaldo());
			observacionesPago.setTipoDocumento(tipoDocumentoIf.getCodigo()+" "+tipoDocumentoIf.getNombre());
			listaOservaciones.add(observacionesPago);
		}

		return listaOservaciones;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CarteraIf procesarAprobacionPago(Map<CarteraIf, Map<List<CarteraDetalleIf>, List<CarteraIf>>> carteraCarteraDetalleCarteraCompraMap, Long idEmpresa)
	throws GenericBusinessException {
		CarteraEJB cartera = null;
		CarteraPagoEJB carteraPago = null;

		try {
			Iterator carteraCarteraDetalleCarteraCompraMapIt = carteraCarteraDetalleCarteraCompraMap.keySet().iterator();
			while(carteraCarteraDetalleCarteraCompraMapIt.hasNext()){
				CarteraIf model = (CarteraIf) carteraCarteraDetalleCarteraCompraMapIt.next();
				Map<List<CarteraDetalleIf>, List<CarteraIf>> carteraDetalleCarteraCompraMap = carteraCarteraDetalleCarteraCompraMap.get(model);

				Iterator carteraDetalleCarteraCompraMapIt = carteraDetalleCarteraCompraMap.keySet().iterator();
				while(carteraDetalleCarteraCompraMapIt.hasNext()){
					List<CarteraDetalleIf> modelDetalleList = (List<CarteraDetalleIf>) carteraDetalleCarteraCompraMapIt.next();
					List<CarteraIf> modelCarteraCompraList = (List<CarteraIf>) carteraDetalleCarteraCompraMap.get(modelDetalleList);

					cartera = registrarCartera(model);
					manager.persist(cartera);
					
					//guardo registro de cartera pago
					carteraPago = registrarCarteraPago(cartera, idEmpresa);
					manager.persist(carteraPago);

					Iterator carteraCompraListIterator = modelCarteraCompraList.iterator();

					for (CarteraDetalleIf modelDetalle : modelDetalleList) {
						modelDetalle.setCarteraId(cartera.getPrimaryKey());
						if (modelDetalle.getCartera().equals("S")) {
							cartera.setSaldo(utilitariosLocal.redondeoValor(cartera.getSaldo().subtract(modelDetalle.getValor())));
							modelDetalle.setSaldo(BigDecimal.ZERO);
							manager.merge(cartera);
						}
						CarteraDetalleEJB carteraDetalle = registrarCarteraDetalle(modelDetalle, true);
						manager.persist(carteraDetalle);
						CarteraIf modelCarteraCompra = (CarteraIf) carteraCompraListIterator.next();
						Iterator carteraCompraDetalleIt = carteraDetalleLocal.findCarteraDetalleByCarteraId(modelCarteraCompra.getId()).iterator();
						CarteraDetalleIf modelCarteraDetalleCompra = null;

						while (carteraCompraDetalleIt.hasNext()) {
							modelCarteraDetalleCompra = (CarteraDetalleIf) carteraCompraDetalleIt.next();
							if (modelDetalle.getCartera().equals("S")) {
								modelCarteraCompra.setSaldo(utilitariosLocal.redondeoValor(modelCarteraCompra.getSaldo().subtract(modelDetalle.getValor())));
								modelCarteraDetalleCompra.setSaldo(utilitariosLocal.redondeoValor(modelCarteraDetalleCompra.getSaldo().subtract(modelDetalle.getValor())));
								CarteraEJB carteraCompra = registrarCartera(modelCarteraCompra);
								manager.merge(carteraCompra);
								CarteraDetalleEJB carteraDetalleCompra = registrarCarteraDetalle(modelCarteraDetalleCompra, true);
								manager.merge(carteraDetalleCompra);
							}

							if (carteraDetalle.getCartera().equals("S")) {
								CarteraAfectaIf modelCarteraAfecta = new CarteraAfectaEJB();
								modelCarteraAfecta.setCarteradetalleId(carteraDetalle.getPrimaryKey());
								modelCarteraAfecta.setCarteraafectaId(modelCarteraDetalleCompra.getPrimaryKey());
								modelCarteraAfecta.setUsuarioId(cartera.getUsuarioId());
								modelCarteraAfecta.setValor(utilitariosLocal.redondeoValor(carteraDetalle.getValor()));
								modelCarteraAfecta.setFechaCreacion(carteraDetalle.getFechaCreacion());
								modelCarteraAfecta.setFechaAplicacion(carteraDetalle.getFechaCartera());
								modelCarteraAfecta.setCartera(carteraDetalle.getCartera());
								CarteraAfectaEJB carteraAfecta = registrarCarteraAfecta(modelCarteraAfecta);
								manager.merge(carteraAfecta);
							}
						}
					}
				}				
			}
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al guardar información en CarteraEJB.", e);
			throw new GenericBusinessException(
			"Se ha producido un error al guardar los datos de Cartera");
		}

		return cartera;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CarteraIf procesarCartera(CarteraIf model,
			List<CarteraDetalleIf> modelDetalleList,
			Map mapaRegistrosSeleccionadosMap,
			Map registrosAfectaToDetalleCarteraMap,
			Map<String, Object> parametrosEmpresa, String nivelacionOanticipo)
	throws GenericBusinessException {
		CarteraEJB cartera = null;
		CarteraEJB carteraNivelacionOanticipo = null;
		CarteraEJB carteraAnticipo = null;
		List<CarteraDetalleIf> carteraDetalleNivelacionOanticipoList = new ArrayList();
		List<CarteraDetalleIf> carteraDetalleAnticipoList = new ArrayList();
		Map<Long, DocumentoIf> mapaDocumentos = new HashMap<Long, DocumentoIf>();
		List<CarteraDetalleIf> carteraDetalleList = new ArrayList<CarteraDetalleIf>();

		try {

			model.setCodigo(model.getCodigo()
					+ formatoSerial.format(getMaximoNumeroCartera(model.getCodigo())));
			/*
			 * String numero = getMaximoNumeroCartera(model.getCodigo()); int
			 * numeroCartera = 1; if (!numero.equals("[null]")) { numero =
			 * numero.substring(1, numero.length()).replaceAll("]", "");
			 * numeroCartera =
			 * Integer.parseInt(numero.split(model.getCodigo())[1]) + 1; }
			 * model.setCodigo(model.getCodigo() +
			 * formatoSerial.format(numeroCartera));
			 */
			Long idOficina = (Long) parametrosEmpresa.get("idOficina");
			OficinaIf oficina = oficinaLocal.getOficina(idOficina);
			ServidorIf servidor = (oficina.getServidorId() != null) ? servidorLocal
					.getServidor(oficina.getServidorId())
					: null;
					if (nivelacionOanticipo.equals("NP")
							|| nivelacionOanticipo.equals("AC")) {
						String nivelacionCIN = "NCI";
						carteraNivelacionOanticipo = registrarCarteraNivelacionOanticipo(
								model, nivelacionOanticipo, nivelacionCIN, servidor);
						manager.persist(carteraNivelacionOanticipo);
						if (nivelacionOanticipo.equals("AC")) {
							String anticipoPorSaldo = "ANS";
							carteraAnticipo = registrarCarteraNivelacionOanticipo(
									model, nivelacionOanticipo, anticipoPorSaldo,
									servidor);
							manager.persist(carteraAnticipo);
						}
						model.setSaldo(new BigDecimal(0));
					}
					cartera = registrarCartera(model);
					manager.persist(cartera);
					if ((carteraNivelacionOanticipo != null)
							&& (carteraAnticipo != null)) {
						carteraNivelacionOanticipo.setReferenciaId(cartera
								.getPrimaryKey());
						carteraAnticipo.setReferenciaId(cartera.getPrimaryKey());
						manager.merge(carteraNivelacionOanticipo);
						manager.merge(carteraAnticipo);
					}

					TipoDocumentoIf tipoDocumentoIf = tipoDocumentoLocal
					.getTipoDocumento(model.getTipodocumentoId());

					Iterator iteratorMapaRegistrosSeleccionadosMap = mapaRegistrosSeleccionadosMap
					.keySet().iterator();
					while (iteratorMapaRegistrosSeleccionadosMap.hasNext()) {
						Long index = (Long) iteratorMapaRegistrosSeleccionadosMap
						.next();
						Map mapaRegistrosAfecta = (Map) mapaRegistrosSeleccionadosMap
						.get(index);
						Iterator iteratorMapaRegistrosAfecta = mapaRegistrosAfecta
						.keySet().iterator();
						while (iteratorMapaRegistrosAfecta.hasNext()) {
							Integer indexAfecta = (Integer) iteratorMapaRegistrosAfecta
							.next();
							CarteraDetalleIf modelCarteraDetallePositiva = (CarteraDetalleIf) mapaRegistrosAfecta
							.get(indexAfecta);
							CarteraDetalleEJB carteraDetallePositiva = registrarCarteraDetalle(
									modelCarteraDetallePositiva, true);
							manager.merge(carteraDetallePositiva);
						}
					}

					int i = 0;
					boolean esCartera = true;
					double valorCarteraDetalle = 0.0;
					double saldoCarteraDetalle = 0.0;
					Map<String, ChequeDatos> mapaChequesEmitidos = new HashMap<String, ChequeDatos>();




					for (CarteraDetalleIf modelDetalle : modelDetalleList) {
						modelDetalle.setCarteraId(cartera.getPrimaryKey());
						esCartera = (modelDetalle.getCartera().equals("N") ? false
								: true);

						CarteraDetalleEJB carteraDetalleNivelacionOanticipo = null;
						CarteraDetalleEJB carteraDetalleAnticipo = null;
						BigDecimal saldoDetalle = new BigDecimal(0);
						if (modelDetalle.getSaldo().compareTo(new BigDecimal(0)) == 1) {

							if (nivelacionOanticipo.equals("NP")
									|| nivelacionOanticipo.equals("AC")) {
								String nivelacionCIN = "NCI";
								Long carteraNivelacionOanticipoId = carteraNivelacionOanticipo
								.getPrimaryKey();
								Long carteraId = cartera.getPrimaryKey();
								carteraDetalleNivelacionOanticipo = registrarCarteraDetalleNivelacionOanticipo(
										modelDetalle, esCartera, nivelacionOanticipo,
										carteraNivelacionOanticipoId, null,
										nivelacionCIN, carteraId);
								manager.persist(carteraDetalleNivelacionOanticipo);
								carteraDetalleNivelacionOanticipoList
								.add(carteraDetalleNivelacionOanticipo);
								saldoDetalle = modelDetalle.getSaldo();
								if (nivelacionOanticipo.equals("AC")) {
									Long carteraAnticipoId = carteraAnticipo
									.getPrimaryKey();
									String anticipoPorSaldo = "ANS";
									carteraDetalleAnticipo = registrarCarteraDetalleNivelacionOanticipo(
											modelDetalle, esCartera,
											nivelacionOanticipo,
											carteraNivelacionOanticipoId,
											carteraAnticipoId, anticipoPorSaldo,
											carteraId);
									manager.persist(carteraDetalleAnticipo);
									carteraDetalleAnticipoList
									.add(carteraDetalleAnticipo);
								}
								modelDetalle.setSaldo(new BigDecimal(0));
							}
						} 



						CarteraDetalleEJB carteraDetalle = registrarCarteraDetalle(modelDetalle, esCartera);
						manager.persist(carteraDetalle);
						carteraDetalleList.add(carteraDetalle);

						valorCarteraDetalle += carteraDetalle.getValor().doubleValue();
						saldoCarteraDetalle += carteraDetalle.getSaldo().doubleValue();

						Vector detallesAfectaToDetalleCarteraColeccion = (Vector) registrosAfectaToDetalleCarteraMap.get(i);

						if (detallesAfectaToDetalleCarteraColeccion != null) {
							int contadorNivPositivasAfecta = 0;
							for (int j = 0; j < detallesAfectaToDetalleCarteraColeccion
							.size(); j++) {
								CarteraAfectaIf carteraAfectaTemp = (CarteraAfectaIf) detallesAfectaToDetalleCarteraColeccion
								.get(j);
								CarteraDetalleIf modelCarteraDetallePositiva = (CarteraDetalleIf) carteraDetalleLocal
								.getCarteraDetalle(carteraAfectaTemp
										.getCarteraafectaId());
								BigDecimal saldoCarteraDetallePositiva = BigDecimal.ZERO;

								// Para el caso de cheques diferidos, AUNQUE ESTE CASO
								// YA NO EXISTE PORQUE AHORA SE BAJA CARTERA ENSEGUIDA Y
								// EN COBRO DIFERIDO SOLO SE REALIZA UN ASIENTO
								if (modelDetalle.getCartera().equals("N")) {
									carteraAfectaTemp.setCartera("N");
									cartera.setSaldo(utilitariosLocal
											.redondeoValor(cartera.getSaldo().add(
													carteraAfectaTemp.getValor())));
									saldoCarteraDetallePositiva = carteraAfectaTemp
									.getValor();

									modelCarteraDetallePositiva
									.setSaldo(utilitariosLocal
											.redondeoValor(modelCarteraDetallePositiva
													.getSaldo()
													.add(
															saldoCarteraDetallePositiva)));
									CarteraDetalleEJB carteraDetallePositiva = registrarCarteraDetalle(
											modelCarteraDetallePositiva, true);
									manager.merge(carteraDetallePositiva);

								} else {

									CarteraIf modelCarteraAfectada = getCartera(modelCarteraDetallePositiva.getCarteraId());							
									modelCarteraAfectada.setSaldo(utilitariosLocal.redondeoValor(modelCarteraAfectada.getSaldo().subtract(carteraAfectaTemp.getValor())));


									CarteraEJB carteraAfectada = registrarCartera(modelCarteraAfectada);
									manager.merge(carteraAfectada);
								}

								carteraAfectaTemp.setCarteradetalleId(carteraDetalle.getId());
								carteraAfectaTemp.setFechaAplicacion(carteraDetalle.getFechaCartera());

								if ((saldoDetalle.compareTo(new BigDecimal(0)) == 1) && (contadorNivPositivasAfecta == 0)) {
									if (nivelacionOanticipo.equals("NP") || nivelacionOanticipo.equals("AC")) {
										Long carteraAfectaId = carteraDetalleNivelacionOanticipo.getPrimaryKey();
										CarteraAfectaEJB carteraAfectaNivelacionOanticipo = registrarCarteraAfectaNivelacionOanticipo(carteraAfectaTemp, nivelacionOanticipo, carteraAfectaId, saldoDetalle);
										manager.persist(carteraAfectaNivelacionOanticipo);								
										contadorNivPositivasAfecta++;
									}
								}
								CarteraAfectaEJB carteraAfecta = registrarCarteraAfecta(carteraAfectaTemp);
								manager.merge(carteraAfecta);
							}
							//Si no existen carteras afecta pero se esta generando un anticipo por saldo y el saldo de la cartera es cero, entonces se debe crear la cartera afecta del cruce entre el CIN y el NCI
						}else if((model.getSaldo().compareTo(new BigDecimal(0)) == 0) && nivelacionOanticipo.equals("AC")){
							CarteraAfectaEJB carteraAfectaNCI = new CarteraAfectaEJB();
							carteraAfectaNCI.setCartera("S");
							carteraAfectaNCI.setCarteradetalleId(carteraDetalle.getId());
							carteraAfectaNCI.setFechaAplicacion(carteraDetalle.getFechaCartera());
							Long carteraAfectaId = carteraDetalleNivelacionOanticipo.getPrimaryKey();
							carteraAfectaNCI.setUsuarioId(model.getUsuarioId());
							CarteraAfectaEJB carteraAfectaNivelacionOanticipo = registrarCarteraAfectaNivelacionOanticipo(carteraAfectaNCI, nivelacionOanticipo, carteraAfectaId, saldoDetalle);
							manager.persist(carteraAfectaNivelacionOanticipo);
						}
						i++;

						DocumentoIf documento = verificarDocumento(mapaDocumentos,
								carteraDetalle.getDocumentoId());
						if (tipoDocumentoIf.getNombre().contains("ANTICIPO")
								&& tipoDocumentoIf.getNombre().contains("PROVEEDOR")
								&& carteraDetalle.getCuentaBancariaId() != null
								&& carteraDetalle.getPreimpreso() != null
								&& !"".equals(carteraDetalle.getPreimpreso().trim())
								&& "S".equals(documento.getCheque())) {
							registrarChequeEmitido(mapaChequesEmitidos, cartera,
									carteraDetalle);
						}
					}



					valorCarteraDetalle = utilitariosLocal.redondeoValor(valorCarteraDetalle);
					saldoCarteraDetalle = utilitariosLocal.redondeoValor(saldoCarteraDetalle);

					manager.merge(cartera);

					// Aumento los cheques a la tabla de cheques
					for (String numeroCheque : mapaChequesEmitidos.keySet()) {
						ChequeDatos chequeDatos = mapaChequesEmitidos.get(numeroCheque);
						ChequeEmitidoIf chequeEmitido = chequeDatos.getChequeEmitido();
						if (chequeEmitido.getValor().doubleValue() > 0D)
							chequeEmitidoLocal.procesarChequeEmitido(chequeDatos);
					}


					AsientoIf asiento = generarAsientosAutomaticos(cartera,carteraDetalleList, parametrosEmpresa, false);

					if (nivelacionOanticipo.equals("NP")) {
						generarAsientosAutomaticos(carteraNivelacionOanticipo,
								carteraDetalleNivelacionOanticipoList,
								parametrosEmpresa, false);
					} else if (nivelacionOanticipo.equals("AC")) {
						generarAsientosAutomaticos(carteraAnticipo,
								carteraDetalleAnticipoList, parametrosEmpresa, false);
					}

					/*
					 * else if (nivelacionOanticipo.equals("NC") &&
					 * cartera.getValor().doubleValue() >
					 * cartera.getSaldo().doubleValue()) {
					 * generarAsientosAutomaticos(cartera, carteraDetalleList,
					 * parametrosEmpresa, true); }
					 */


					// VERIFICACION DE VALORES
					double valorCartera = utilitariosLocal.redondeoValor(cartera.getValor().doubleValue());
					double saldoCartera = utilitariosLocal.redondeoValor(cartera.getSaldo().doubleValue());
					double valorCarteraConDiferido = 0D;
					TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(model.getTipodocumentoId());
					Collection carteraDetalleColeccion = carteraDetalleLocal.findCarteraDetalleByCarteraId(cartera.getId());			
					Iterator carteraDetalleColeccionIt = carteraDetalleColeccion.iterator();

					while (carteraDetalleColeccionIt.hasNext()) {
						CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) carteraDetalleColeccionIt.next();

						Collection carteraAfectaColeccion = carteraAfectaLocal.findCarteraAfectaByCarteradetalleId(carteraDetalle.getId());
						Iterator carteraAfectaColeccionIt = carteraAfectaColeccion.iterator();

						while (carteraAfectaColeccionIt.hasNext()) {
							CarteraAfectaIf carteraAfecta = (CarteraAfectaIf) carteraAfectaColeccionIt.next();
							valorCarteraConDiferido = valorCarteraConDiferido + carteraAfecta.getValor().doubleValue();
						}
					}

					if (valorCartera != valorCarteraDetalle)
						throw new GenericBusinessException("Error, el valor de la cabecera no coincide con el Detalle.");

					if (saldoCartera != saldoCarteraDetalle)
						throw new GenericBusinessException("Error, el saldo de la cabecera no coincide con el Detalle.");

					if (asiento != null) {
						double debe = 0.0;
						double haber = 0.0;
						Collection<AsientoDetalleIf> asientosDetalle = asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getPrimaryKey());
						for (Iterator itAsientos = asientosDetalle.iterator(); itAsientos.hasNext();) {
							AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) itAsientos.next();
							debe += asientoDetalle.getDebe().doubleValue();
							haber += asientoDetalle.getHaber().doubleValue();
						}
						debe = utilitariosLocal.redondeoValor(debe);
						haber = utilitariosLocal.redondeoValor(haber);
						if (debe != haber) {
							throw new GenericBusinessException("Error, el valor del Debe y Haber no coinciden en el Asiento");
						}/*
						 * else if ((debe !=
						 * utilitariosLocal.redondeoValor(valorCarteraConDiferido)) &&
						 * !tipoDocumento.getCodigo().equals("NPC") &&
						 * !tipoDocumento.getCodigo().equals("ANS") &&
						 * !tipoDocumento.getCodigo().equals("ANP")){ throw new
						 * GenericBusinessException("Error, el valor del Debe y
						 * Haber no coinciden con el detalle de la Cartera"); }
						 */
					}
		} catch (GenericBusinessException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al guardar informaci\u00f3n en CarteraEJB.", e);
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al guardar informaci\u00f3n en CarteraEJB.", e);
			throw new GenericBusinessException(
			"Se ha producido un error al guardar la Cartera");
		}
		return cartera;
	}



	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CarteraIf procesarCartera(CarteraIf model,
			List<CarteraDetalleIf> modelDetalleList,
			CarteraIf modelCarteraCompra, Long idEmpresa)
	throws GenericBusinessException {
		CarteraEJB cartera = null;

		try {
			model.setCodigo(model.getCodigo()
					+ formatoSerial.format(getMaximoNumeroCartera(model.getCodigo())));
			cartera = registrarCartera(model);
			manager.persist(cartera);
			// manager.merge(numerador);

			for (CarteraDetalleIf modelDetalle : modelDetalleList) {
				modelDetalle.setCarteraId(cartera.getPrimaryKey());
				if (modelDetalle.getCartera().equals("S")) {
					cartera.setSaldo(utilitariosLocal.redondeoValor(cartera
							.getSaldo().subtract(modelDetalle.getValor())));
					modelDetalle.setSaldo(BigDecimal.ZERO);
					manager.merge(cartera);
				}
				CarteraDetalleEJB carteraDetalle = registrarCarteraDetalle(
						modelDetalle, true);
				manager.persist(carteraDetalle);
				Iterator carteraCompraDetalleIt = carteraDetalleLocal
				.findCarteraDetalleByCarteraId(
						modelCarteraCompra.getId()).iterator();
				CarteraDetalleIf modelCarteraDetalleCompra = null;
				while (carteraCompraDetalleIt.hasNext()) {
					modelCarteraDetalleCompra = (CarteraDetalleIf) carteraCompraDetalleIt
					.next();
					if (modelDetalle.getCartera().equals("S")) {
						modelCarteraCompra.setSaldo(utilitariosLocal
								.redondeoValor(modelCarteraCompra.getSaldo()
										.subtract(modelDetalle.getValor())));
						modelCarteraDetalleCompra.setSaldo(utilitariosLocal
								.redondeoValor(modelCarteraDetalleCompra
										.getSaldo().subtract(
												modelDetalle.getValor())));
						CarteraEJB carteraCompra = registrarCartera(modelCarteraCompra);
						manager.merge(carteraCompra);
						CarteraDetalleEJB carteraDetalleCompra = registrarCarteraDetalle(
								modelCarteraDetalleCompra, true);
						manager.merge(carteraDetalleCompra);
					}

					if (carteraDetalle.getCartera().equals("S")) {
						CarteraAfectaIf modelCarteraAfecta = new CarteraAfectaEJB();
						modelCarteraAfecta.setCarteradetalleId(carteraDetalle
								.getPrimaryKey());
						modelCarteraAfecta
						.setCarteraafectaId(modelCarteraDetalleCompra
								.getPrimaryKey());
						modelCarteraAfecta.setUsuarioId(cartera.getUsuarioId());
						modelCarteraAfecta.setValor(utilitariosLocal
								.redondeoValor(carteraDetalle.getValor()));
						modelCarteraAfecta.setFechaCreacion(carteraDetalle
								.getFechaCreacion());
						modelCarteraAfecta.setFechaAplicacion(carteraDetalle
								.getFechaCartera());
						modelCarteraAfecta.setCartera(carteraDetalle
								.getCartera());
						CarteraAfectaEJB carteraAfecta = registrarCarteraAfecta(modelCarteraAfecta);
						manager.merge(carteraAfecta);
					}
				}
			}
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al guardar información en CarteraEJB.", e);
			throw new GenericBusinessException(
			"Se ha producido un error al guardar los datos de Cartera");
		}

		return cartera;
	}

	private int getMaximoNumeroCartera(String codigoParcial) {
		String queryString = "select max(codigo) from CarteraEJB c where c.codigo like '"
			+ codigoParcial + "%'";
		Query query = manager.createQuery(queryString);
		String maxCodigoCartera = query.getResultList().toString();
		queryString = "select max(codigo) from LogCarteraEJB lc where lc.codigo like '"
			+ codigoParcial + "%'";
		query = manager.createQuery(queryString);
		String maxCodigoLog = query.getResultList().toString();
		String codigo = (maxCodigoCartera.compareTo(maxCodigoLog) >= 0 || maxCodigoLog
				.equals("[null]")) ? maxCodigoCartera : maxCodigoLog;
		int codigoCartera = 1;
		if (!codigo.equals("[null]")) {
			codigo = codigo.substring(1, codigo.length()).replaceAll("]", "");
			codigoCartera = Integer.parseInt(codigo.split(codigoParcial)[1]) + 1;
		}
		return codigoCartera;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarCarteraPagosDiferidos(List<CarteraIf> carteraList,
			List<CarteraDetalleIf> carteraDetalleList, Long idEmpresa)
	throws GenericBusinessException, SaldoCuentaNegativoException {
		boolean generoNuevoAsiento = false;

		for (CarteraIf cartera : carteraList) {
			for (CarteraDetalleIf carteraDetalle : carteraDetalleList) {
				if (carteraDetalle.getCarteraId().compareTo(cartera.getId()) == 0) {
					manager.merge(carteraDetalle);
				}
			}

			// Asientos Automaticos
			int i = 0;
			PagoProveedorAsientoAutomaticoHandler
			.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
			Iterator carteraDetalleForAsientoIterator = carteraDetalleList
			.iterator();
			generoNuevoAsiento = false;
			Long etapa = new Long(2);
			while (carteraDetalleForAsientoIterator.hasNext()) {
				CarteraDetalleIf carteraDetalleForAsiento = (CarteraDetalleIf) carteraDetalleForAsientoIterator
				.next();
				if (carteraDetalleForAsiento.getCarteraId().compareTo(
						cartera.getId()) == 0) {
					generarAsientoAutomaticoComprobanteEgresoDiferido(cartera,
							carteraDetalleForAsiento, true, etapa);
					generoNuevoAsiento = true;
					i++;
				}
			}

			if (generoNuevoAsiento) {
				Map asientoMap = PagoProveedorAsientoAutomaticoHandler
				.getAsientoMap();
				AsientoIf asiento = (AsientoIf) asientoMap.get("ASIENTO");
				List<AsientoDetalleIf> asientoDetalleList = (List<AsientoDetalleIf>) asientoMap
				.get("DETALLES");
				if ((asientoDetalleList != null)
						&& (asientoDetalleList.size() > 0)) {
					asientoLocal.procesarAsiento(asiento, asientoDetalleList, true);
				}
			}
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Vector<CarteraIf> procesarPagoProveedor(List<CarteraIf> carteraList,
			List<CarteraDetalleIf> carteraDetalleList,
			List<CarteraIf> carteraCompraList, Long idEmpresa,
			Map<Long, Integer> numerosChequeMap)
			throws GenericBusinessException {
		try {
			Vector<CarteraDetalleIf> carteraDetalleColeccion = new Vector<CarteraDetalleIf>();
			Vector<CarteraIf> carteraColeccion = new Vector<CarteraIf>();
			Vector<CarteraIf> carteraColeccionReporte = new Vector<CarteraIf>();
			boolean generoNuevoAsiento = false;
			Map<String, ChequeDatos> mapaChequesEmitidos = new HashMap<String, ChequeDatos>();
			Map<Long, DocumentoIf> mapaDocumentos = new HashMap<Long, DocumentoIf>();
			Map<Long, CuentaBancariaIf> mapaCuentasBancarias = new HashMap<Long, CuentaBancariaIf>();
			// Actualizo los numeros de cheque en tabla cuenta bancaria
			if (numerosChequeMap != null) {
				Iterator numerosChequeMapIt = numerosChequeMap.keySet().iterator();
				while (numerosChequeMapIt.hasNext()) {
					Long numerosChequeMapKey = (Long) numerosChequeMapIt.next();
					CuentaBancariaIf cuentaBancariaIf = cuentaBancariaLocal.getCuentaBancaria(numerosChequeMapKey);
					cuentaBancariaIf.setNumeroCheque(numerosChequeMap.get(numerosChequeMapKey).toString());
					manager.merge(cuentaBancariaIf);
					mapaCuentasBancarias.put(cuentaBancariaIf.getId(), cuentaBancariaIf);
				}
			}

			// Actualizo las carteras y carteras detalle de compras y egresos
			for (CarteraIf model : carteraList) {
				model.setCodigo(model.getCodigo() + formatoSerial.format(getMaximoNumeroCartera(model.getCodigo())));
				CarteraIf cartera = registrarCartera(model);
				cartera.setActivarRetrocompatibilidad("N");
				manager.merge(cartera);
				TipoDocumentoIf tipoDocumentoIf = tipoDocumentoLocal.getTipoDocumento(model.getTipodocumentoId());
				carteraDetalleColeccion.clear();
				for (CarteraDetalleIf modelDetalle : carteraDetalleList) {
					if (modelDetalle.getCarteraId().compareTo(cartera.getId()) == 0) {
						modelDetalle.setCarteraId(cartera.getPrimaryKey());
						if (modelDetalle.getCartera().equals("S")) {
							cartera.setSaldo(utilitariosLocal.redondeoValor(cartera.getSaldo().subtract(modelDetalle.getValor())));
							modelDetalle.setSaldo(BigDecimal.ZERO);
							manager.merge(cartera);
						}
						CarteraDetalleEJB carteraDetalle = registrarCarteraDetalle(modelDetalle, true);
						DocumentoIf documento = verificarDocumento(mapaDocumentos, carteraDetalle.getDocumentoId());
						if (documento.getCheque().equals("S")) {
							CuentaBancariaIf cuentaBancaria = verificarCuentaBancaria(mapaCuentasBancarias, carteraDetalle.getCuentaBancariaId());
							carteraDetalle.setChequeBancoId(cuentaBancaria.getBancoId());
							carteraDetalle.setChequeCuentaBancariaId(carteraDetalle.getCuentaBancariaId());
							carteraDetalle.setChequeNumero(carteraDetalle.getPreimpreso());
						} else if (documento.getDebitoBancario().equals("S")) {
							CuentaBancariaIf cuentaBancaria = verificarCuentaBancaria(mapaCuentasBancarias, carteraDetalle.getCuentaBancariaId());
							carteraDetalle.setDebitoBancoId(cuentaBancaria.getBancoId());
							carteraDetalle.setDebitoCuentaBancariaId(carteraDetalle.getCuentaBancariaId());
							carteraDetalle.setDebitoReferencia("D/B");
						}
						manager.merge(carteraDetalle);
						if (tipoDocumentoIf.getNombre().contains("COMPROBANTE")
								&& tipoDocumentoIf.getNombre().contains("EGRESO")
								&& carteraDetalle.getCuentaBancariaId() != null
								&& carteraDetalle.getPreimpreso() != null
								&& !"".equals(carteraDetalle.getPreimpreso().trim())
								&& "S".equals(documento.getCheque())) {
							registrarChequeEmitido(mapaChequesEmitidos, model, carteraDetalle);
						}
						carteraDetalleColeccion.add(carteraDetalle);
					}
				}

				carteraColeccion.add(cartera);
				carteraColeccionReporte.add(cartera);

				for (CarteraIf modelCarteraCompra : carteraCompraList) {
					Iterator carteraCompraDetalleIt = carteraDetalleLocal.findCarteraDetalleByCarteraId(modelCarteraCompra.getId()).iterator();
					CarteraDetalleIf modelCarteraDetalleCompra = null;
					while (carteraCompraDetalleIt.hasNext()) {
						modelCarteraDetalleCompra = (CarteraDetalleIf) carteraCompraDetalleIt.next();
						for (CarteraDetalleIf carteraDetalle : carteraDetalleColeccion) {
							if ((carteraDetalle.getCarteraId().compareTo(cartera.getId()) == 0)	&& (modelCarteraCompra.getId().toString().compareTo(carteraDetalle.getReferencia()) == 0) && carteraDetalle.getCartera().equals("S")) {
								modelCarteraCompra.setSaldo(utilitariosLocal.redondeoValor(modelCarteraCompra.getSaldo().subtract(carteraDetalle.getValor())));
								modelCarteraDetalleCompra.setSaldo(utilitariosLocal.redondeoValor(modelCarteraDetalleCompra.getSaldo().subtract(carteraDetalle.getValor())));

								CarteraEJB carteraCompra = registrarCartera(modelCarteraCompra);
								manager.merge(carteraCompra);

								CarteraDetalleEJB carteraDetalleCompra = registrarCarteraDetalle(modelCarteraDetalleCompra, true);
								manager.merge(carteraDetalleCompra);

								CarteraAfectaIf modelCarteraAfecta = new CarteraAfectaEJB();
								modelCarteraAfecta.setCarteradetalleId(carteraDetalle.getPrimaryKey());
								modelCarteraAfecta.setCarteraafectaId(modelCarteraDetalleCompra.getPrimaryKey());
								modelCarteraAfecta.setUsuarioId(cartera.getUsuarioId());
								modelCarteraAfecta.setValor(utilitariosLocal.redondeoValor(carteraDetalle.getValor()));
								modelCarteraAfecta.setFechaCreacion(carteraDetalle.getFechaCreacion());
								modelCarteraAfecta.setFechaAplicacion(carteraDetalle.getFechaCartera());
								modelCarteraAfecta.setCartera(carteraDetalle.getCartera());
								CarteraAfectaEJB carteraAfecta = registrarCarteraAfecta(modelCarteraAfecta);
								manager.merge(carteraAfecta);
							}
						}
					}
				}

				// Asientos Automaticos
				int i = 0;
				PagoProveedorAsientoAutomaticoHandler.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("carteraId", cartera.getId());
				parameterMap.put("cartera", "S");
				List carteraDetalleForAsientoList = (List) carteraDetalleLocal.findCarteraDetalleByQuery(parameterMap);
				Iterator carteraDetalleForAsientoIterator = carteraDetalleForAsientoList.iterator();
				generoNuevoAsiento = false;
				Long etapa = new Long(1);
				boolean diferido = false;
				while (carteraDetalleForAsientoIterator.hasNext()) {
					CarteraDetalleIf carteraDetalleForAsiento = (CarteraDetalleIf) carteraDetalleForAsientoIterator.next();
					if (i != carteraDetalleForAsientoList.size() - 1) {
						if ((carteraDetalleForAsiento.getDiferido() != null) && carteraDetalleForAsiento.getDiferido().equals(CARTERA_DETALLE_DIFERIDA)) {
							diferido = true;
							generarAsientoAutomaticoComprobanteEgresoDiferido(cartera, carteraDetalleForAsiento, false,	etapa);
						} else {
							generarAsientoAutomaticoComprobanteEgreso(cartera, carteraDetalleForAsiento, false);
						}
					} else if (i == carteraDetalleForAsientoList.size() - 1) {
						if ((carteraDetalleForAsiento.getDiferido() != null) && carteraDetalleForAsiento.getDiferido().equals(CARTERA_DETALLE_DIFERIDA)) {
							diferido = true;
							generarAsientoAutomaticoComprobanteEgresoDiferido(cartera, carteraDetalleForAsiento, true, etapa);
						} else {
							generarAsientoAutomaticoComprobanteEgreso(cartera, carteraDetalleForAsiento, true);
						}
						generoNuevoAsiento = true;
					}
					i++;
				}

				//if (generoNuevoAsiento && !diferido) {
				if (generoNuevoAsiento) {
					Map asientoMap = PagoProveedorAsientoAutomaticoHandler.getAsientoMap();
					AsientoIf asiento = (AsientoIf) asientoMap.get("ASIENTO");
					asiento.setStatus("P");
					List<AsientoDetalleIf> asientoDetalleList = (List<AsientoDetalleIf>) asientoMap.get("DETALLES");
					if ((asientoDetalleList != null) && (asientoDetalleList.size() > 0)) {
						asientoLocal.procesarAsiento(asiento, asientoDetalleList, true);
					}
				}
			}

			for (String numeroCheque : mapaChequesEmitidos.keySet()) {
				ChequeDatos chequeDatos = mapaChequesEmitidos.get(numeroCheque);
				ChequeEmitidoIf chequeEmitido = chequeDatos.getChequeEmitido();
				if (chequeEmitido.getValor().doubleValue() > 0D)
					chequeEmitidoLocal.procesarChequeEmitido(chequeDatos);
			}

			// Creo una cartera nueva para los detalles que no se pagaron
			for (CarteraIf cartera : carteraColeccion) {
				Collection<CarteraDetalleIf> carterasDetalleNoPagadasColeccion = carteraDetalleLocal.findCarteraDetalleByCarteraId(cartera.getId());
				Vector<CarteraDetalleIf> carteraDetalleNoPagadaColeccion = new Vector<CarteraDetalleIf>();
				BigDecimal valorSaldoNuevo = new BigDecimal(0);
				for (CarteraDetalleIf carteraDetalleNoPagada : carterasDetalleNoPagadasColeccion) {
					if (carteraDetalleNoPagada.getDocumentoId() == null) {
						// Resto del valor y saldo de la cartera, los valores de
						// carteras detalle que no se pagaron
						cartera.setValor(utilitariosLocal.redondeoValor(cartera.getValor().subtract(carteraDetalleNoPagada.getValor())));
						cartera.setSaldo(utilitariosLocal.redondeoValor(cartera.getSaldo().subtract(carteraDetalleNoPagada.getValor())));
						valorSaldoNuevo = new BigDecimal(valorSaldoNuevo.doubleValue() + carteraDetalleNoPagada.getValor().doubleValue());
						carteraDetalleNoPagadaColeccion.add(carteraDetalleNoPagada);
					} else {
						CarteraIf carteraPagada = getCartera(carteraDetalleNoPagada.getCarteraId());
						carteraColeccionReporte.add(carteraPagada);
					}
				}
				if (valorSaldoNuevo.compareTo(new BigDecimal(0)) == 1) {
					manager.merge(cartera);
					int ultimoIndiceGuion = cartera.getCodigo().lastIndexOf('-');
					//----------String unNumeroCartera = cartera.getCodigo().substring(0, ultimoIndiceGuion + 1);
					//----------cartera.setCodigo(unNumeroCartera);
					// Cartera nueva para los detalles que no se pagaron
					CarteraIf carteraNueva = cartera;
					carteraNueva.setId(null);
					//----------carteraNueva.setCodigo(unNumeroCartera + formatoSerial.format(getMaximoNumeroCartera(cartera)));
					carteraNueva.setCodigo("");
					carteraNueva.setComentario(null);
					carteraNueva.setValor(utilitariosLocal.redondeoValor(valorSaldoNuevo));
					carteraNueva.setSaldo(utilitariosLocal.redondeoValor(valorSaldoNuevo));
					manager.persist(carteraNueva);
					int nuevoSecuencial = 1;
					for (CarteraDetalleIf carteraDetalleNuevaCartera : carteraDetalleNoPagadaColeccion) {
						carteraDetalleNuevaCartera.setCarteraId(carteraNueva.getPrimaryKey());
						carteraDetalleNuevaCartera.setSecuencial(nuevoSecuencial);
						nuevoSecuencial++;
						manager.merge(carteraDetalleNuevaCartera);
					}
				}
			}
			carteraColeccionReporte = utilitariosLocal.removerCarterasRepetidas(carteraColeccionReporte);

			return carteraColeccionReporte;

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException(
			"Error general en la actualizacion de Cartera !!");
		}
	}

	private DocumentoIf verificarDocumento(Map<Long, DocumentoIf> mapaDocumentos, Long documentoId) throws GenericBusinessException {
		DocumentoIf documento = null;
		if (!mapaDocumentos.containsKey(documentoId)) {
			documento = documentoLocal.getDocumento(documentoId);
			mapaDocumentos.put(documentoId, documento);
		} else {
			documento = mapaDocumentos.get(documentoId);
		}
		return documento;
	}
	
	private CuentaBancariaIf verificarCuentaBancaria(Map<Long, CuentaBancariaIf> mapaCuentasBancarias, Long cuentaBancariaId) throws GenericBusinessException {
		CuentaBancariaIf cuentaBancaria = null;
		if (!mapaCuentasBancarias.containsKey(cuentaBancariaId)) {
			cuentaBancaria = cuentaBancariaLocal.getCuentaBancaria(cuentaBancariaId);
			mapaCuentasBancarias.put(cuentaBancariaId, cuentaBancaria);
		} else {
			cuentaBancaria = mapaCuentasBancarias.get(cuentaBancariaId);
		}
		return cuentaBancaria;
	}
	
	/*private FormaPagoIf verificarFormaPago(Map<Long, FormaPagoIf> mapaFormasPago, Long formaPagoId) throws GenericBusinessException {
		FormaPagoIf formaPago = null;
		if (!mapaFormasPago.containsKey(formaPagoId)) {
			formaPago = formaPagoLocal.getFormaPago(formaPagoId);
			mapaFormasPago.put(formaPagoId, formaPago);
		} else {
			formaPago = mapaFormasPago.get(formaPagoId);
		}
		return formaPago;
	}*/

	private void registrarChequeEmitido(
			Map<String, ChequeDatos> mapaChequesEmitidos, CarteraIf model,
			CarteraDetalleIf carteraDetalle) throws GenericBusinessException {

		// Para el caso en que se hace un debito y no se emite cheque
		if (carteraDetalle.getPreimpreso() == null
				|| "".equals(carteraDetalle.getPreimpreso().trim()))
			return;
		ClienteOficinaIf clienteOficina = clienteOficinaLocal
		.getClienteOficina(model.getClienteoficinaId());
		ClienteIf cliente = clienteLocal.getCliente(clienteOficina
				.getClienteId());
		ChequeDatos chequeDatos = null;
		if (!mapaChequesEmitidos.containsKey(carteraDetalle.getPreimpreso())) {
			ChequeEmitidoIf chequeEmitidoIf = new ChequeEmitidoEJB();
			chequeEmitidoIf.setFechaEmision(carteraDetalle.getFechaCartera());
			chequeEmitidoIf.setCuentaBancariaId(carteraDetalle
					.getCuentaBancariaId());
			chequeEmitidoIf.setNumero(carteraDetalle.getPreimpreso());
			chequeEmitidoIf.setDetalle(model.getComentario());
			chequeEmitidoIf.setValor(utilitariosLocal
					.redondeoValor(carteraDetalle.getValor()));
			chequeEmitidoIf.setEstado(chequeEmitidoLocal
					.getLetraEstadoChequeEmitido(EstadoChequeEmitido.EMITIDO));
			chequeEmitidoIf.setTipoDocumentoId(model.getTipodocumentoId());
			chequeEmitidoIf.setTransaccionId(model.getId() != null ? model
					.getId() : model.getPrimaryKey());
			chequeEmitidoIf.setBeneficiario(cliente.getRazonSocial());
			//chequeEmitidoIf.setOrigen(OrigenCheque
			//		.getLetraOrigenCheque(OrigenCheque.CARTERA));
			chequeEmitidoIf.setOrigen(OrigenCheque.CARTERA.getLetra());

			chequeDatos = new ChequeDatos();
			chequeDatos.setChequeEmitido(chequeEmitidoIf);
			//chequeDatos.setOrigen(OrigenCheque
			//		.getLetraOrigenCheque(OrigenCheque.CARTERA));
			chequeDatos.setOrigen(OrigenCheque.CARTERA.getLetra());
			chequeDatos.getTransaccionesIds().add(
					model.getId() != null ? model.getId() : model
							.getPrimaryKey());

			mapaChequesEmitidos
			.put(carteraDetalle.getPreimpreso(), chequeDatos);
		} else {
			chequeDatos = mapaChequesEmitidos.get(carteraDetalle
					.getPreimpreso());
			ChequeEmitidoIf chequeEmitidoIf = chequeDatos.getChequeEmitido();
			BigDecimal valor = chequeEmitidoIf.getValor();
			valor = valor.add(carteraDetalle.getValor());
			chequeEmitidoIf.setValor(utilitariosLocal.redondeoValor(valor));
			chequeDatos.setChequeEmitido(chequeEmitidoIf);
			chequeDatos.getTransaccionesIds().add(
					model.getId() != null ? model.getId() : model
							.getPrimaryKey());
			mapaChequesEmitidos
			.put(carteraDetalle.getPreimpreso(), chequeDatos);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean actualizarCartera(CarteraIf model,
			List<CarteraDetalleIf> modelDetalleList,
			Map mapaRegistrosSeleccionadosMap,
			Map registrosAfectaToDetalleCartera,
			List<CarteraDetalleIf> carteraDetalleEliminadoColeccion,
			List<CarteraIf> carteraActualizadaColeccion,
			List<CarteraAfectaIf> carteraAfectaEliminarColeccion,
			Map<String, Object> parametrosEmpresa)
	throws GenericBusinessException {
		boolean carteraActualizada = false;
		List<CarteraDetalleIf> carteraDetalleList = new ArrayList<CarteraDetalleIf>();
		try {
			if (model.getEstado().equals(ESTADO_ANULADO)) {
				TipoDocumentoIf tipoDocumentoCIN = tipoDocumentoLocal
				.getTipoDocumento(model.getTipodocumentoId());
				if (tipoDocumentoCIN.getCodigo().equals("CIN")) {
					anularCartera(model, modelDetalleList, parametrosEmpresa);
					if (tipoDocumentoLocal.findTipoDocumentoByCodigo("ANS").size() > 0) {
						TipoDocumentoIf tipoDocumentoANS = (TipoDocumentoIf) tipoDocumentoLocal
						.findTipoDocumentoByCodigo("ANS").iterator().next();
						Map aMapANS = new HashMap();
						aMapANS.put("referenciaId", model.getId());
						aMapANS.put("tipodocumentoId", tipoDocumentoANS.getId());
						aMapANS.put("oficinaId", model.getOficinaId());
						if (findCarteraByQuery(aMapANS).size() > 0) {
							CarteraIf carteraANS = (CarteraIf) findCarteraByQuery(
									aMapANS).iterator().next();
							ArrayList modelDetalleListANS = (ArrayList) carteraDetalleLocal
							.findCarteraDetalleByCarteraId(carteraANS.getId());
							carteraANS.setEstado("A");
							anularCartera(carteraANS, modelDetalleListANS,
									parametrosEmpresa);
						}
					}
					if (tipoDocumentoLocal.findTipoDocumentoByCodigo("NCI").size() > 0) {
						TipoDocumentoIf tipoDocumentoNCI = (TipoDocumentoIf) tipoDocumentoLocal
						.findTipoDocumentoByCodigo("NCI").iterator().next();
						Map aMapNCI = new HashMap();
						aMapNCI.put("referenciaId", model.getId());
						aMapNCI.put("tipodocumentoId", tipoDocumentoNCI
								.getId());
						aMapNCI.put("oficinaId", model.getOficinaId());
						if (findCarteraByQuery(aMapNCI).size() > 0) {
							CarteraIf carteraNCI = 
								(CarteraIf) findCarteraByQuery(aMapNCI).iterator().next();
							ArrayList modelDetalleListNCI = (ArrayList) carteraDetalleLocal
							.findCarteraDetalleByCarteraId(carteraNCI.getId());
							carteraNCI.setEstado("A");
							anularCartera(carteraNCI, modelDetalleListNCI,parametrosEmpresa);
						}
					}

				} else {
					anularCartera(model, modelDetalleList, parametrosEmpresa);
				}
			} else {
				CarteraIf cartera = registrarCartera(model);
				manager.merge(cartera);
				Iterator iteratorMapaRegistrosSeleccionadosMap = mapaRegistrosSeleccionadosMap
				.keySet().iterator();
				while (iteratorMapaRegistrosSeleccionadosMap.hasNext()) {
					Long index = (Long) iteratorMapaRegistrosSeleccionadosMap.next();
					Map mapaRegistrosAfecta = (Map) mapaRegistrosSeleccionadosMap.get(index);
					Iterator iteratorMapaRegistrosAfecta = mapaRegistrosAfecta.keySet().iterator();
					while (iteratorMapaRegistrosAfecta.hasNext()) {
						Integer indexAfecta = (Integer) iteratorMapaRegistrosAfecta.next();
						CarteraDetalleIf modelCarteraDetallePositiva = (CarteraDetalleIf) mapaRegistrosAfecta.get(indexAfecta);
						CarteraDetalleEJB carteraDetallePositiva = registrarCarteraDetalle(modelCarteraDetallePositiva, true);
						manager.merge(carteraDetallePositiva);
					}
				}

				int i = 0;
				double valorCarteraDetalle = 0.0;
				double saldoCarteraDetalle = 0.0;
				TipoDocumentoIf tipoDocumentoIf = tipoDocumentoLocal.getTipoDocumento(cartera.getTipodocumentoId());
				Map<String, ChequeDatos> mapaChequesEmitidos = new HashMap<String, ChequeDatos>();
				Map<String,ChequeDatos> mapaChequesEliminados = new HashMap<String, ChequeDatos>();

				for (CarteraDetalleIf modelDetalle : modelDetalleList) {

					modelDetalle.setCarteraId(cartera.getPrimaryKey());
					// Si se esta actualizando un anticipo por saldo "ANS", se
					// debe cambiar las fechas en la cartera detalle a
					// la fecha actual donde se esta haciendo el cruce de
					// anticipo, la unica fecha que no cambia es la de creación.
					if (tipoDocumentoIf.getCodigo().equals("ANS")) {
						modelDetalle.setFechaCartera(utilitariosLocal.fechaHoy());
						modelDetalle.setFechaUltimaActualizacion(utilitariosLocal.fechaHoy());
						modelDetalle.setFechaVencimiento(utilitariosLocal.fechaHoy());
					}

					//Reviso si hay un detalle con cheque para eliminarlo en caso de cambio
					if ( modelDetalle.getId() != null ) {
						CarteraDetalleIf detalleExistente = carteraDetalleLocal.getCarteraDetalle(modelDetalle.getId());
						DocumentoIf documentoDetalleExistente = documentoLocal.getDocumento(detalleExistente.getDocumentoId());
						if ( "S".equals(documentoDetalleExistente.getCheque()) 
								&& tipoDocumentoIf.getNombre().contains("ANTICIPO")
								&& tipoDocumentoIf.getNombre().contains("PROVEEDOR")
								&& detalleExistente.getCuentaBancariaId() != null
								&& detalleExistente.getPreimpreso() != null
								&& !"".equals(detalleExistente.getPreimpreso().trim())) {
							registrarChequeEmitido(mapaChequesEliminados, cartera,
									detalleExistente);
						}
					}

					CarteraDetalleIf carteraDetalle = registrarCarteraDetalle(modelDetalle, true);
					if (carteraDetalle.getId() == null) {
						manager.persist(carteraDetalle);
					} else {
						manager.merge(carteraDetalle);
					}

					carteraDetalleList.add(carteraDetalle);
					valorCarteraDetalle += carteraDetalle.getValor().doubleValue();
					saldoCarteraDetalle += carteraDetalle.getSaldo().doubleValue();
					Vector detallesAfectaToDetalleCarteraColeccion = (Vector) registrosAfectaToDetalleCartera.get(i);

					if (detallesAfectaToDetalleCarteraColeccion != null) {

						for (int j = 0; j < detallesAfectaToDetalleCarteraColeccion.size(); j++) {

							CarteraAfectaIf carteraAfectaTemp = (CarteraAfectaIf) detallesAfectaToDetalleCarteraColeccion.get(j);

							if (carteraAfectaTemp.getId() != null) {
								CarteraAfectaEJB carteraAfecta = registrarCarteraAfecta(carteraAfectaTemp);
								manager.merge(carteraAfecta);

								CarteraDetalleIf carteraDetalleTemp = carteraDetalleLocal
								.getCarteraDetalle(carteraAfecta.getCarteraafectaId());
								CarteraIf modelCarteraAfectada = getCartera(carteraDetalleTemp.getCarteraId());
								modelCarteraAfectada.setSaldo(utilitariosLocal
										.redondeoValor(modelCarteraAfectada.getValor().subtract(carteraAfecta.getValor())));
								CarteraEJB carteraAfectada = registrarCartera(modelCarteraAfectada);
								manager.merge(carteraAfectada);
							} else {

								if (modelDetalle.getCartera().equals("N")) {

									carteraAfectaTemp.setCartera("N");
									CarteraDetalleIf modelCarteraDetalleTemp = (CarteraDetalleIf) carteraDetalleLocal
									.getCarteraDetalle(carteraAfectaTemp.getCarteraafectaId());

									BigDecimal valorDiferido = new BigDecimal(0.00);
									Collection carteraAfectaColeccion = carteraAfectaLocal
									.findCarteraAfectaByCarteraAfectaIdAndByCartera(modelCarteraDetalleTemp.getId(), "N");
									Iterator itCarteraAfectaColeccion = carteraAfectaColeccion.iterator();

									while (itCarteraAfectaColeccion.hasNext()) {
										CarteraAfectaIf carteraAfectaIt = (CarteraAfectaIf) itCarteraAfectaColeccion.next();
										valorDiferido = valorDiferido.add(carteraAfectaIt.getValor());
									}
									modelCarteraDetalleTemp.setSaldo(
											utilitariosLocal.redondeoValor(modelCarteraDetalleTemp.getSaldo().subtract(
													carteraAfectaTemp.getValor()).subtract(valorDiferido)));
									carteraDetalleLocal.saveCarteraDetalle(modelCarteraDetalleTemp);
								}

								carteraAfectaTemp.setCarteradetalleId(carteraDetalle.getId());
								// Cuando se crea una cartera afecta se setea
								// como fecha de aplicacion la misma fecha que
								// en eL detalle,
								// pero cuando es un anticipo por saldo que se
								// esta cruzando, la fecha de aplicación debe
								// ser la fecha actual,
								// la cual ya viene desde el cliente, entonces
								// no se modifica y por esto no entra cuando es
								// "ANS".
								if (!tipoDocumentoIf.getCodigo().equals("ANS")) {
									carteraAfectaTemp.setFechaAplicacion(carteraDetalle.getFechaCartera());
								}

								CarteraAfectaEJB carteraAfecta = registrarCarteraAfecta(carteraAfectaTemp);
								if (carteraAfecta.getId() != null) {
									manager.merge(carteraAfecta);
								} else {
									manager.persist(carteraAfecta);
								}

								CarteraDetalleIf carteraDetalleTemp = carteraDetalleLocal
								.getCarteraDetalle(carteraAfecta.getCarteraafectaId());
								CarteraIf modelCarteraAfectada = getCartera(carteraDetalleTemp.getCarteraId());
								if (modelCarteraAfectada.getSaldo().compareTo(BigDecimal.ZERO) == 1) {
									modelCarteraAfectada.setSaldo(
											utilitariosLocal.redondeoValor(modelCarteraAfectada.getSaldo().subtract(
													carteraAfecta.getValor())));
									CarteraEJB carteraAfectada = registrarCartera(modelCarteraAfectada);
									manager.merge(carteraAfectada);
								}
							}
						}
					}
					i++;

					DocumentoIf documentoIf = documentoLocal.getDocumento(modelDetalle.getDocumentoId());
					if ( "S".equals(documentoIf.getCheque()) ){

						if (  tipoDocumentoIf.getNombre().contains("ANTICIPO")
								&& tipoDocumentoIf.getNombre().contains("PROVEEDOR")
								&& carteraDetalle.getCuentaBancariaId() != null
								&& carteraDetalle.getPreimpreso() != null
								&& !"".equals(carteraDetalle.getPreimpreso().trim())) {
							registrarChequeEmitido(mapaChequesEmitidos, cartera,
									carteraDetalle);
						}
					}

				}
				valorCarteraDetalle = utilitariosLocal.redondeoValor(valorCarteraDetalle);
				saldoCarteraDetalle = utilitariosLocal.redondeoValor(saldoCarteraDetalle);

				// Aumento los cheques a la tabla de cheques
				// for ( String numeroCheque : mapaChequesEmitidos.keySet() ){
				// ChequeDatos chequeDatos =
				// mapaChequesEmitidos.get(numeroCheque);
				// ChequeEmitidoIf chequeEmitido=
				// chequeDatos.getChequeEmitido();
				// if ( chequeEmitido.getValor().doubleValue() > 0D )
				chequeEmitidoLocal.actualizarChequeEmitido(cartera.getId(),
						cartera.getTipodocumentoId(), mapaChequesEmitidos,
						OrigenCheque.CARTERA.getLetra(),mapaChequesEliminados);
				// }

				for (CarteraIf modelActualizar : carteraActualizadaColeccion) {
					CarteraIf modelActualizado = registrarCartera(modelActualizar);
					manager.merge(modelActualizado);
				}

				for (CarteraAfectaIf modelAfectaEliminar : carteraAfectaEliminarColeccion) {
					CarteraAfectaIf modelAfectaEliminado = carteraAfectaLocal
					.getCarteraAfecta(modelAfectaEliminar.getId());
					manager.remove(modelAfectaEliminado);
				}

				for (CarteraDetalleIf modelDetalle : carteraDetalleEliminadoColeccion) {
					CarteraDetalleIf modelDetalleEliminado = carteraDetalleLocal
					.getCarteraDetalle(modelDetalle.getId());
					manager.remove(modelDetalleEliminado);
				}

				TipoDocumentoIf tipoDocumento = tipoDocumentoLocal
				.getTipoDocumento(cartera.getTipodocumentoId());
				if (tipoDocumento.getCodigo().equals("ANS")
						|| tipoDocumento.getCodigo().equals("ANP")) {
					// cambio la fecha de emision de la cartera solo para que el
					// asiento del anticipo por saldo salga con la
					// fecha correcta, que es la fecha actual que es cuando se
					// esta cruzando el anticipo.
					cartera.setFechaEmision(utilitariosLocal.fromUtilDateToTimestamp(utilitariosLocal.fechaHoy()));
					AsientoIf asiento = generarAsientosAutomaticos(cartera,
							carteraDetalleList, parametrosEmpresa, true);
				} /*
				 * else if (tipoDocumento.getCodigo().equals("NCC")) {
				 * AsientoIf asiento = generarAsientosAutomaticos(cartera,
				 * carteraDetalleList, parametrosEmpresa, true); }
				 */

				else if (tipoDocumento.getCodigo().equals("RCA")) {
					parametrosEmpresa.put("OFICINA_ID", (Long)parametrosEmpresa.get("idEmpresa"));
					parametrosEmpresa.put("EMPRESA_ID", (Long)parametrosEmpresa.get("idOficina"));
					parametrosEmpresa.put("ETAPA", 2L);
					AsientoIf asiento = generarAsientosAutomaticos(cartera, carteraDetalleList, parametrosEmpresa, true);
				}

				// VERIFICACION DE VALORES
				double valorCartera = utilitariosLocal.redondeoValor(cartera
						.getValor().doubleValue());
				double saldoCartera = utilitariosLocal.redondeoValor(cartera
						.getSaldo().doubleValue());
				if (valorCartera != valorCarteraDetalle)
					throw new GenericBusinessException(
					"Error, el valor de la cabecera no coincide con el Detalle.");
				if (saldoCartera != saldoCarteraDetalle)
					throw new GenericBusinessException(
					"Error, el saldo de la cabecera no coincide con el Detalle.");
				/*
				 * if ( asiento != null ){ double debe = 0.0; double haber =
				 * 0.0; Collection<AsientoDetalleIf> asientosDetalle =
				 * asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getPrimaryKey());
				 * for ( Iterator itAsientos = asientosDetalle.iterator() ;
				 * itAsientos.hasNext() ; ){ AsientoDetalleIf asientoDetalle =
				 * (AsientoDetalleIf) itAsientos.next(); debe +=
				 * asientoDetalle.getDebe().doubleValue(); haber +=
				 * asientoDetalle.getHaber().doubleValue(); } debe =
				 * utilitariosLocal.redondeoValor(debe); haber =
				 * utilitariosLocal.redondeoValor(haber); if ( debe != haber )
				 * throw new GenericBusinessException("Error, el valor del Debe
				 * y Haber no coinciden en el Asiento"); else if ( debe !=
				 * valorCartera ) throw new GenericBusinessException("Error, el
				 * valor del Debe y Haber no coinciden con el detalle de la
				 * Compra"); }
				 */

				carteraActualizada = true;

				manager.flush();

			}
		} catch (SaldoCuentaNegativoException se) {
			se.printStackTrace();
		} catch (GenericBusinessException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al actualizar información en CarteraEJB.", e);
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al actualizar información en CarteraEJB.", e);
			throw new GenericBusinessException(
			"Se ha producido un error al actualizar la Cartera");
		}

		return carteraActualizada;
	}

	private void anularCartera(CarteraIf model,
			List<CarteraDetalleIf> modelDetalleList,
			Map<String, Object> parametrosEmpresa)
	throws GenericBusinessException, SaldoCuentaNegativoException {
		Vector<CarteraIf> carterasAnuladas = new Vector<CarteraIf>();

		model.setSaldo(model.getValor());
		CarteraIf cartera = registrarCartera(model);
		manager.merge(cartera);
		carterasAnuladas.add(cartera);

		TipoDocumentoIf tipoDocumentoNPC = (TipoDocumentoIf) tipoDocumentoLocal
		.findTipoDocumentoByCodigo("NPC").iterator().next();
		TipoDocumentoIf tipoDocumentoNCI = (TipoDocumentoIf) tipoDocumentoLocal
		.findTipoDocumentoByCodigo("NCI").iterator().next();

		for (CarteraDetalleIf carterasDetalleEliminar : modelDetalleList) {
			Collection carterasAfectaEliminar = carteraAfectaLocal
			.findCarteraAfectaByCarteradetalleId(carterasDetalleEliminar
					.getId());
			Iterator carterasAfectaEliminarIt = carterasAfectaEliminar
			.iterator();
			while (carterasAfectaEliminarIt.hasNext()) {
				CarteraAfectaIf carteraAfectaEliminar = (CarteraAfectaIf) carterasAfectaEliminarIt
				.next();
				CarteraDetalleIf carteraDetalleCompraReversar = carteraDetalleLocal
				.getCarteraDetalle(carteraAfectaEliminar
						.getCarteraafectaId());
				CarteraIf carteraCompraReversar = getCartera(carteraDetalleCompraReversar
						.getCarteraId());
				carteraCompraReversar.setSaldo(carteraCompraReversar.getSaldo()
						.add(carteraAfectaEliminar.getValor()));
				// Si estoy anulando un comp. de ingreso que tiene nivelacion
				// positiva, tambien tengo que poner el estado de la nivelacion
				// como anulado.
				if ((carteraCompraReversar.getTipodocumentoId().compareTo(
						tipoDocumentoNPC.getId()) == 0)
						|| (carteraCompraReversar.getTipodocumentoId()
								.compareTo(tipoDocumentoNCI.getId()) == 0)) {
					carteraCompraReversar.setEstado("A");
					carterasAnuladas.add(carteraCompraReversar);
				}
				carteraDetalleCompraReversar
				.setSaldo(carteraDetalleCompraReversar.getSaldo().add(
						carteraAfectaEliminar.getValor()));
				manager.merge(carteraCompraReversar);
				manager.merge(carteraDetalleCompraReversar);
				manager.remove(carteraAfectaEliminar);
			}
			// mando false al metodo registrarCarteraDetalle para que vuelva a
			// poner saldo = valor
			CarteraDetalleIf carteraDetalleComprobanteReversar = registrarCarteraDetalle(
					carterasDetalleEliminar, false);
			manager.merge(carteraDetalleComprobanteReversar);
		}

		// Como un comp. de ingreso pudo generar una nivelacion o anticipo
		// entonces debo pasar el vector de estas carteras
		// para tambien eliminar sus asientos
		for (CarteraIf carteraAnulada : carterasAnuladas) {
			Map<String, Long> mapa = new HashMap<String, Long>();
			TipoDocumentoIf tipoDocumentoCarteraAnulada = tipoDocumentoLocal.getTipoDocumento(carteraAnulada.getTipodocumentoId());
			mapa.put("tipoDocumentoId", tipoDocumentoCarteraAnulada.getId());
			if (tipoDocumentoCarteraAnulada.getCodigo().equals("NCC") || tipoDocumentoCarteraAnulada.getCodigo().equals("NCP"))
				mapa.put("transaccionId", carteraAnulada.getReferenciaId());
			else
				mapa.put("transaccionId", carteraAnulada.getId());
			Collection<AsientoIf> asientos = asientoLocal
			.findAsientoByQuery(mapa);
			String usuario = (String) parametrosEmpresa.get("usuario");
			if (asientos.size() > 0) {
				for (AsientoIf asientoIf : asientos) {
					String log = "ASIENTO_ID: " + asientoIf.getId()
					+ ", ELIMINADO POR ELIMINACION CARTERA COD. # "
					+ carteraAnulada.getCodigo();
					asientoLocal.procesarEliminacionAsiento(asientoIf, usuario,
							log, false);
				}
			}

			//Anulación N/C
			if (tipoDocumentoCarteraAnulada.getCodigo().equals("NCC") || tipoDocumentoCarteraAnulada.getCodigo().equals("NCP")) {
				mapa.clear();
				mapa.put("tipoDocumentoId", tipoDocumentoCarteraAnulada.getId());
				mapa.put("id", carteraAnulada.getReferenciaId());
				Iterator it = notaCreditoLocal.findNotaCreditoByQuery(mapa).iterator();
				if (it.hasNext()) {
					NotaCreditoIf notaCredito = (NotaCreditoIf) it.next();
					notaCredito.setEstado("N");
					manager.merge(notaCredito);
				}
			}
		}

		chequeEmitidoLocal.anularChequesEmitidos(model.getId(), model
				.getTipodocumentoId(), null);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean eliminarCartera(Long carteraId, String usuario)
	throws GenericBusinessException {
		try {
			CarteraIf carteraIf = getCartera(carteraId);
			if (carteraIf != null) {
				// Saco los detalles de la cartera
				Collection<CarteraDetalleIf> carteraDetalles = carteraDetalleLocal
				.findCarteraDetalleByCarteraId(carteraId);
				for (CarteraDetalleIf carteraDetalleIf : carteraDetalles) {
					// Saco los registro de CarteraAfecta por detalle
					Collection<CarteraAfectaIf> carteraAfectas = carteraAfectaLocal
					.findCarteraAfectaByCarteradetalleId(carteraDetalleIf
							.getId());
					for (CarteraAfectaIf carteraAfectaIf : carteraAfectas) {
						// Busco el detalle y la cabecera a sumar el valor de
						// carteraAfecta
						CarteraDetalleIf carteraDetalleIfReversar = carteraDetalleLocal
						.getCarteraDetalle(carteraAfectaIf
								.getCarteraafectaId());
						if (carteraDetalleIfReversar != null) {
							BigDecimal valor = carteraAfectaIf.getValor();
							BigDecimal saldoDetalle = carteraDetalleIfReversar
							.getSaldo();
							// Le sumo al detalle
							carteraDetalleIfReversar.setSaldo(saldoDetalle
									.add(valor));
							// Le sumo a la cabecera
							CarteraIf carteraIfReversar = getCartera(carteraDetalleIfReversar
									.getCarteraId());
							BigDecimal saldoCabecera = carteraIfReversar
							.getSaldo();
							carteraIfReversar
							.setSaldo(saldoCabecera.add(valor));
						}
						// borro la cartera afecta
						carteraAfectaLocal.deleteCarteraAfecta(carteraAfectaIf
								.getId());
					}
				}
				// borro los detalles de la cartera
				for (CarteraDetalleIf carteraDetalleIf : carteraDetalles) {
					carteraDetalleLocal.deleteCarteraDetalle(carteraDetalleIf
							.getId());
				}
				// Preparo los datos para borrar los asientos y reversar los
				// valores
				Map<String, Long> mapa = new HashMap<String, Long>();
				mapa.put("tipoDocumentoId", carteraIf.getTipodocumentoId());
				mapa.put("transaccionId", carteraIf.getId());
				Collection<AsientoIf> asientos = asientoLocal
				.findAsientoByQuery(mapa);
				if (asientos.size() == 1) {
					for (AsientoIf asientoIf : asientos) {
						String log = "ASIENTO_ID: " + asientoIf.getId()
						+ ", ELIMINADO POR ELIMINACION CARTERA COD. # "
						+ carteraIf.getCodigo();
						asientoLocal.procesarEliminacionAsiento(asientoIf,
								usuario, log, false);
					}
				} else if (asientos.size() < 1)
					throw new GenericBusinessException(
					"Cartera no tiene asientos asociados");
				else
					throw new GenericBusinessException(
					"Cartera tiene mas de 1 registro de asientos asociados");

				// borro la cartera
				deleteCartera(carteraIf.getId());

			} else
				throw new GenericBusinessException("Cartera no existe");

			return true;
			/*
			 * CarteraEJB data = manager.find(CarteraEJB.class, carteraId);
			 * CarteraIf modelCartera = getCartera(carteraId); if
			 * (modelCartera.getValor().compareTo(modelCartera.getSaldo()) == 0) {
			 * Collection<CarteraDetalleIf> modelDetalleList =
			 * carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraId); for
			 * (CarteraDetalleIf modelDetalle : modelDetalleList) { Collection<CarteraAfectaIf>
			 * modelAfectaList =
			 * carteraAfectaLocal.findCarteraAfectaByCarteradetalleId(modelDetalle.getId());
			 * for (CarteraAfectaIf modelAfecta : modelAfectaList) {
			 * manager.remove(modelAfecta); } manager.remove(modelDetalle); }
			 * manager.remove(data); manager.flush(); carteraEliminada = true; }
			 */
		} catch (GenericBusinessException e) {
			ctx.setRollbackOnly();
			log.error("Error al eliminar información en CarteraEJB.", e);
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al eliminar información en CarteraEJB.", e);
			throw new GenericBusinessException(
			"Error al eliminar información en Cartera");
		}
	}

	private AsientoIf generarAsientosAutomaticos(CarteraIf cartera,
			List<CarteraDetalleIf> modelDetalleList,
			Map<String, Object> parametrosEmpresa, boolean isUpdate)
	throws GenericBusinessException {
		AsientoIf asiento = null;
		// ASIENTOS
		comprobanteIngresoAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
		anticipoProveedorAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
		descuentoProntoPagoAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
		notaCreditoProveedorAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
		// notaCreditoClienteAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
		retencionProveedorAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
		anticipoCompraReembolsoAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
		notaInternaCreditoClienteAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
		notaInternaCreditoProveedorAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
		notaCreditoAnticipoClienteAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
		reciboCajaAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());

		int i = 0;
		for (CarteraDetalleIf carteraDetalle : modelDetalleList) {

			if (i != modelDetalleList.size() - 1) {
				asiento = generarAsientoAutomaticoGeneral(cartera,
						carteraDetalle, false, parametrosEmpresa, isUpdate);
			} else if (i == modelDetalleList.size() - 1) {
				asiento = generarAsientoAutomaticoGeneral(cartera,
						carteraDetalle, true, parametrosEmpresa, isUpdate);
			}
			i++;
		}


		return asiento;
	}

	/*
	 * private AsientoIf
	 * generarAsientoAutomaticoCruceNotaCreditoAnticipoCliente(CarteraIf
	 * cartera,List<CarteraDetalleIf> modelDetalleList,Map<String,Object>
	 * parametrosEmpresa,boolean isUpdate) throws GenericBusinessException{
	 * AsientoIf asiento = null;
	 * notaCreditoAnticipoClienteAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new
	 * ArrayList<AsientoDetalleIf>());
	 * 
	 * int i=0; for (CarteraDetalleIf carteraDetalle : modelDetalleList) {
	 * Collection afectaList =
	 * carteraAfectaLocal.findCarteraAfectaByCarteradetalleId(carteraDetalle.getId());
	 * if (i != afectaList.size() - 1){ asiento =
	 * generarAsientoAutomaticoGeneral(cartera, carteraDetalle, false,
	 * parametrosEmpresa, isUpdate); }else if (i == afectaList.size() - 1){
	 * asiento = generarAsientoAutomaticoGeneral(cartera, carteraDetalle, true,
	 * parametrosEmpresa, isUpdate); } i++; } return asiento; }
	 */

	public CarteraEJB registrarCarteraNivelacionOanticipo(CarteraIf model,
			String nivelacionOanticipo, String anticipo, ServidorIf servidor) {
		CarteraEJB carteraNivelacionOanticipo = new CarteraEJB();
		try {
			carteraNivelacionOanticipo.setId(model.getId());
			carteraNivelacionOanticipo.setTipo(model.getTipo());
			carteraNivelacionOanticipo.setOficinaId(model.getOficinaId());

			if (nivelacionOanticipo.equals("NP")) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tipoDocumentoLocal
				.findTipoDocumentoByCodigo("NPC").iterator().next();
				carteraNivelacionOanticipo.setTipodocumentoId(tipoDocumento
						.getId());
				String[] codigoPartes = model.getCodigo().split("-");
				if (servidor != null) {
					if (model.getCodigo().length() >= 20)
						carteraNivelacionOanticipo
						.setCodigo(codigoPartes[0] + "-"
								+ codigoPartes[1] + "-NPC-"
								+ codigoPartes[3] + "-"
								+ codigoPartes[4] + "-");
					else
						carteraNivelacionOanticipo.setCodigo(codigoPartes[0]
						                                                  + "-" + codigoPartes[1] + "-NPC-"
						                                                  + codigoPartes[3] + "-");
				} else {
					if (model.getCodigo().length() >= 20)
						carteraNivelacionOanticipo.setCodigo(codigoPartes[0]
						                                                  + "-NPC-" + codigoPartes[2] + "-"
						                                                  + codigoPartes[3] + "-");
					else
						carteraNivelacionOanticipo.setCodigo(codigoPartes[0]
						                                                  + "-NPC-" + codigoPartes[2] + "-");
				}
				carteraNivelacionOanticipo
				.setCodigo(carteraNivelacionOanticipo.getCodigo()
						+ formatoSerial
						.format(getMaximoNumeroCartera(carteraNivelacionOanticipo.getCodigo())));
				carteraNivelacionOanticipo.setValor(utilitariosLocal
						.redondeoValor(model.getSaldo()));
				carteraNivelacionOanticipo.setSaldo(new BigDecimal(0));
			} else if (nivelacionOanticipo.equals("AC")) {
				String[] codigoPartes = model.getCodigo().split("-");
				if (servidor != null) {
					if (anticipo.equals("NCI")) {
						TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tipoDocumentoLocal
						.findTipoDocumentoByCodigo("NCI").iterator()
						.next();
						carteraNivelacionOanticipo
						.setTipodocumentoId(tipoDocumento.getId());
						if (model.getCodigo().length() >= 20)
							carteraNivelacionOanticipo
							.setCodigo(codigoPartes[0] + "-"
									+ codigoPartes[1] + "-NCI-"
									+ codigoPartes[3] + "-"
									+ codigoPartes[4] + "-");
						else
							carteraNivelacionOanticipo
							.setCodigo(codigoPartes[0] + "-"
									+ codigoPartes[1] + "-NCI-"
									+ codigoPartes[3] + "-");
						carteraNivelacionOanticipo.setSaldo(new BigDecimal(0));
					} else if (anticipo.equals("ANS")) {
						TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tipoDocumentoLocal
						.findTipoDocumentoByCodigo("ANS").iterator()
						.next();
						carteraNivelacionOanticipo
						.setTipodocumentoId(tipoDocumento.getId());
						if (model.getCodigo().length() >= 20)
							carteraNivelacionOanticipo
							.setCodigo(codigoPartes[0] + "-"
									+ codigoPartes[1] + "-ANS-"
									+ codigoPartes[3] + "-"
									+ codigoPartes[4] + "-");
						else
							carteraNivelacionOanticipo
							.setCodigo(codigoPartes[0] + "-"
									+ codigoPartes[1] + "-ANS-"
									+ codigoPartes[3] + "-");
						carteraNivelacionOanticipo.setSaldo(utilitariosLocal
								.redondeoValor(model.getSaldo()));
					}
				} else {
					if (anticipo.equals("NCI")) {
						TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tipoDocumentoLocal
						.findTipoDocumentoByCodigo("NCI").iterator()
						.next();
						carteraNivelacionOanticipo
						.setTipodocumentoId(tipoDocumento.getId());
						if (model.getCodigo().length() >= 20)
							carteraNivelacionOanticipo
							.setCodigo(codigoPartes[0] + "-NCI-"
									+ codigoPartes[2] + "-"
									+ codigoPartes[3] + "-");
						else
							carteraNivelacionOanticipo
							.setCodigo(codigoPartes[0] + "-NCI-"
									+ codigoPartes[2] + "-");
						carteraNivelacionOanticipo.setSaldo(new BigDecimal(0));
					} else if (anticipo.equals("ANS")) {
						TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tipoDocumentoLocal
						.findTipoDocumentoByCodigo("ANS").iterator()
						.next();
						carteraNivelacionOanticipo
						.setTipodocumentoId(tipoDocumento.getId());
						if (model.getCodigo().length() >= 20)
							carteraNivelacionOanticipo
							.setCodigo(codigoPartes[0] + "-ANS-"
									+ codigoPartes[2] + "-"
									+ codigoPartes[3] + "-");
						else
							carteraNivelacionOanticipo
							.setCodigo(codigoPartes[0] + "-ANS-"
									+ codigoPartes[2] + "-");
						carteraNivelacionOanticipo.setSaldo(utilitariosLocal
								.redondeoValor(model.getSaldo()));
					}
				}

				carteraNivelacionOanticipo
				.setCodigo(carteraNivelacionOanticipo.getCodigo()
						+ formatoSerial
						.format(getMaximoNumeroCartera(carteraNivelacionOanticipo.getCodigo())));
				carteraNivelacionOanticipo.setValor(utilitariosLocal
						.redondeoValor(model.getSaldo()));
			}

			carteraNivelacionOanticipo.setReferenciaId(model.getReferenciaId());
			carteraNivelacionOanticipo.setClienteoficinaId(model
					.getClienteoficinaId());
			carteraNivelacionOanticipo.setPreimpreso(model.getPreimpreso());
			carteraNivelacionOanticipo.setUsuarioId(model.getUsuarioId());
			carteraNivelacionOanticipo.setVendedorId(model.getVendedorId());
			carteraNivelacionOanticipo.setMonedaId(model.getMonedaId());
			carteraNivelacionOanticipo.setFechaEmision(model.getFechaEmision());
			carteraNivelacionOanticipo.setFechaUltimaActualizacion(model.getFechaUltimaActualizacion());
			carteraNivelacionOanticipo.setEstado(model.getEstado());
			carteraNivelacionOanticipo.setComentario(model.getComentario());
			carteraNivelacionOanticipo.setAprobado(model.getAprobado());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return carteraNivelacionOanticipo;
	}

	public CarteraEJB registrarCartera(CarteraIf model) {
		CarteraEJB cartera = new CarteraEJB();

		cartera.setId(model.getId());
		cartera.setTipo(model.getTipo());
		cartera.setOficinaId(model.getOficinaId());
		cartera.setTipodocumentoId(model.getTipodocumentoId());
		cartera.setCodigo(model.getCodigo());
		cartera.setReferenciaId(model.getReferenciaId());
		cartera.setClienteoficinaId(model.getClienteoficinaId());
		cartera.setPreimpreso(model.getPreimpreso());
		cartera.setUsuarioId(model.getUsuarioId());
		cartera.setVendedorId(model.getVendedorId());
		cartera.setMonedaId(model.getMonedaId());
		try {
			if (model.getFechaEmision() != null)
				cartera.setFechaEmision(model.getFechaEmision());
			else
				cartera.setFechaEmision(utilitariosLocal.fromUtilDateToTimestamp(utilitariosLocal.fechaHoy()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cartera.setValor(utilitariosLocal.redondeoValor(model.getValor()));
		cartera.setSaldo(utilitariosLocal.redondeoValor(model.getSaldo()));
		cartera.setFechaUltimaActualizacion(model.getFechaUltimaActualizacion());
		cartera.setEstado(model.getEstado());
		cartera.setComentario(model.getComentario());
		cartera.setAprobado(model.getAprobado());

		return cartera;
	}
	
	public CarteraPagoEJB registrarCarteraPago(CarteraIf cartera, Long idEmpresa) {
		CarteraPagoEJB carteraPago = new CarteraPagoEJB();
		carteraPago.setCarteraPagoId(cartera.getId());
		carteraPago.setFechaEmision(cartera.getFechaEmision());
		carteraPago.setUsuarioEmisionId(cartera.getUsuarioId());
		carteraPago.setEstado("E"); //EMITIDO (estado 0)
		carteraPago.setEmpresaId(idEmpresa);

		return carteraPago;
	}

	public CarteraEJB registrarCarteraFactura(Long idEmpresa, Long idOficina,
			FacturaEJB factura) {
		CarteraData modelCartera = new CarteraData();
		modelCartera.setReferenciaId(factura.getPrimaryKey());
		modelCartera.setCodigo(getNumeroCartera(new java.sql.Date(factura.getFechaFactura().getTime()), "FAC", idEmpresa, idOficina));
		modelCartera.setCodigo(modelCartera.getCodigo() + formatoSerial.format(getMaximoNumeroCartera(modelCartera.getCodigo())));
		CarteraEJB cartera = registrarCartera(modelCartera, factura);
		return cartera;
	}

	private CarteraEJB registrarCartera(CarteraIf modelCartera,
			FacturaIf modelFactura) {
		CarteraEJB cartera = new CarteraEJB();
		String TIPO_CARTERA = "C";
		String ESTADO_CARTERA = "N";

		try {
			Long usuarioId = ((UsuarioIf) usuarioLocal.findUsuarioById(
					modelFactura.getUsuarioId()).iterator().next()).getId();
			CajaIf cajaIf = (CajaIf) cajaLocal.findCajaByUsuarioId(usuarioId)
			.iterator().next();
			OficinaIf oficinaIf = oficinaLocal.getOficina(modelFactura
					.getOficinaId());

			if (cajaIf != null) {
				modelCartera.setPreimpreso(oficinaIf.getCodigo() + "-"
						+ cajaIf.getCodigo() + "-"
						+ modelFactura.getPreimpresoNumero());
			}

			cartera.setId(modelCartera.getId());
			cartera.setTipo(TIPO_CARTERA);
			cartera.setOficinaId(modelFactura.getOficinaId());
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal
			.getTipoDocumento(modelFactura.getTipodocumentoId());
			cartera.setTipodocumentoId(tipoDocumento.getId());
			cartera.setCodigo(modelCartera.getCodigo());
			cartera.setReferenciaId(modelFactura.getId());
			cartera.setClienteoficinaId(modelFactura.getClienteoficinaId());
			cartera.setPreimpreso(modelCartera.getPreimpreso());
			cartera.setUsuarioId(modelFactura.getUsuarioId());
			cartera.setVendedorId(modelFactura.getVendedorId());
			cartera.setMonedaId(modelFactura.getMonedaId());
			cartera.setFechaEmision(new java.sql.Timestamp(modelFactura.getFechaCreacion().getTime()));
			double valorFactura = modelFactura.getValor().doubleValue();
			double descuentoFactura = modelFactura.getDescuento().doubleValue();
			double descuentoGlobalFactura = modelFactura.getDescuentoGlobal()
			.doubleValue();
			double descuentoTotalFactura = descuentoFactura
			+ descuentoGlobalFactura;
			double ivaFactura = modelFactura.getIva().doubleValue();
			double iceFactura = modelFactura.getIce().doubleValue();
			double otroImpuesto = modelFactura.getOtroImpuesto().doubleValue();
			double impuestoTotalFactura = ivaFactura + iceFactura
			+ otroImpuesto;
			double valorCartera = valorFactura - descuentoTotalFactura
			+ impuestoTotalFactura;
			cartera.setValor(utilitariosLocal.redondeoValor(BigDecimal
					.valueOf(valorCartera)));
			cartera.setSaldo(utilitariosLocal.redondeoValor(BigDecimal
					.valueOf(valorCartera)));
			cartera.setFechaUltimaActualizacion(modelCartera.getFechaUltimaActualizacion());
			cartera.setEstado(ESTADO_CARTERA);
			cartera.setComentario(modelFactura.getObservacion());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}

		return cartera;
	}

	private CarteraDetalleEJB registrarCarteraDetalleNivelacionOanticipo(
			CarteraDetalleIf modelDetalle, boolean esCartera,
			String nivelacionOanticipo, Long carteraNivelacionOanticipoId,
			Long carteraAnticipoId, String anticipo, Long carteraId) {
		CarteraDetalleEJB carteraDetalleNivelacionOanticipo = new CarteraDetalleEJB();
		try {
			carteraDetalleNivelacionOanticipo.setId(modelDetalle.getId());
			carteraDetalleNivelacionOanticipo.setCuentaBancariaId(modelDetalle
					.getCuentaBancariaId());
			carteraDetalleNivelacionOanticipo.setReferencia(carteraId
					.toString());

			if (nivelacionOanticipo.equals("NP")) {
				carteraDetalleNivelacionOanticipo
				.setCarteraId(carteraNivelacionOanticipoId);

				DocumentoIf documento = (DocumentoIf) documentoLocal
				.findDocumentoByCodigo("CLNP").iterator().next();
				carteraDetalleNivelacionOanticipo.setDocumentoId(documento
						.getId());

				carteraDetalleNivelacionOanticipo.setPreimpreso("");

				carteraDetalleNivelacionOanticipo.setValor(utilitariosLocal
						.redondeoValor(modelDetalle.getSaldo()));
				if (esCartera) {
					carteraDetalleNivelacionOanticipo
					.setSaldo(new BigDecimal(0));
				} else {
					carteraDetalleNivelacionOanticipo.setSaldo(utilitariosLocal
							.redondeoValor(modelDetalle.getSaldo()));
				}

			} else if (nivelacionOanticipo.equals("AC")) {
				if (anticipo.equals("NCI")) {
					carteraDetalleNivelacionOanticipo
					.setCarteraId(carteraNivelacionOanticipoId);

					DocumentoIf documento = (DocumentoIf) documentoLocal
					.findDocumentoByCodigo("NCIN").iterator().next();
					carteraDetalleNivelacionOanticipo.setDocumentoId(documento
							.getId());

					if (esCartera) {
						carteraDetalleNivelacionOanticipo
						.setSaldo(new BigDecimal(0));
					} else {
						carteraDetalleNivelacionOanticipo
						.setSaldo(utilitariosLocal
								.redondeoValor(modelDetalle.getSaldo()));
					}
				} else if (anticipo.equals("ANS")) {
					carteraDetalleNivelacionOanticipo
					.setCarteraId(carteraAnticipoId);

					DocumentoIf documento = (DocumentoIf) documentoLocal
					.findDocumentoByCodigo("ANSA").iterator().next();
					carteraDetalleNivelacionOanticipo.setDocumentoId(documento
							.getId());

					carteraDetalleNivelacionOanticipo.setSaldo(utilitariosLocal
							.redondeoValor(modelDetalle.getSaldo()));
				}

				carteraDetalleNivelacionOanticipo.setPreimpreso(modelDetalle
						.getPreimpreso());
				carteraDetalleNivelacionOanticipo.setValor(utilitariosLocal
						.redondeoValor(modelDetalle.getSaldo()));
			}

			carteraDetalleNivelacionOanticipo.setFormaPagoId(modelDetalle
					.getFormaPagoId());
			carteraDetalleNivelacionOanticipo.setSecuencial(modelDetalle
					.getSecuencial());
			carteraDetalleNivelacionOanticipo.setLineaId(modelDetalle
					.getLineaId());
			carteraDetalleNivelacionOanticipo.setDepositoId(modelDetalle
					.getDepositoId());
			carteraDetalleNivelacionOanticipo.setFechaCreacion(modelDetalle
					.getFechaCreacion());
			carteraDetalleNivelacionOanticipo.setFechaCartera(modelDetalle
					.getFechaCartera());
			carteraDetalleNivelacionOanticipo.setFechaVencimiento(modelDetalle
					.getFechaVencimiento());
			carteraDetalleNivelacionOanticipo
			.setFechaUltimaActualizacion(modelDetalle.getFechaUltimaActualizacion());

			if (modelDetalle.getCotizacion() != null) {
				carteraDetalleNivelacionOanticipo
				.setCotizacion(utilitariosLocal
						.redondeoValor(modelDetalle.getCotizacion()));
			}

			carteraDetalleNivelacionOanticipo.setCartera(modelDetalle
					.getCartera());
			carteraDetalleNivelacionOanticipo.setAutorizacion(modelDetalle
					.getAutorizacion());
			carteraDetalleNivelacionOanticipo
			.setSriSustentoTributarioId(modelDetalle
					.getSriSustentoTributarioId());
			carteraDetalleNivelacionOanticipo.setDiferido(modelDetalle
					.getDiferido());
			carteraDetalleNivelacionOanticipo.setObservacion(modelDetalle
					.getObservacion());

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return carteraDetalleNivelacionOanticipo;
	}

	private CarteraDetalleEJB registrarCarteraDetalle(CarteraDetalleIf modelDetalle, boolean esCartera) throws GenericBusinessException {
		CarteraDetalleEJB carteraDetalle = new CarteraDetalleEJB();

		carteraDetalle.setId(modelDetalle.getId());
		carteraDetalle.setCarteraId(modelDetalle.getCarteraId());
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
		if (esCartera) {
			carteraDetalle.setSaldo(utilitariosLocal.redondeoValor(modelDetalle.getSaldo()));
		} else {
			carteraDetalle.setSaldo(utilitariosLocal.redondeoValor(modelDetalle.getValor()));
		}

		if (modelDetalle.getCotizacion() != null) {
			carteraDetalle.setCotizacion(utilitariosLocal.redondeoValor(modelDetalle.getCotizacion()));
		}
		carteraDetalle.setCartera(modelDetalle.getCartera());
		carteraDetalle.setAutorizacion(modelDetalle.getAutorizacion());
		carteraDetalle.setSriSustentoTributarioId(modelDetalle.getSriSustentoTributarioId());
		carteraDetalle.setDiferido(modelDetalle.getDiferido());
		carteraDetalle.setObservacion(modelDetalle.getObservacion());

		return carteraDetalle;
	}

	private CarteraAfectaEJB registrarCarteraAfectaNivelacionOanticipo(
			CarteraAfectaIf modelAfecta, String nivelacionOanticipo,
			Long carteraAfectaId, BigDecimal saldoDetalle) {
		CarteraAfectaEJB carteraAfectaNivelacionOanticipo = new CarteraAfectaEJB();

		carteraAfectaNivelacionOanticipo.setId(modelAfecta.getId());
		carteraAfectaNivelacionOanticipo.setCarteradetalleId(modelAfecta
				.getCarteradetalleId());
		carteraAfectaNivelacionOanticipo.setCarteraafectaId(carteraAfectaId);
		carteraAfectaNivelacionOanticipo.setUsuarioId(modelAfecta
				.getUsuarioId());
		carteraAfectaNivelacionOanticipo.setValor(utilitariosLocal
				.redondeoValor(saldoDetalle));
		carteraAfectaNivelacionOanticipo.setFechaCreacion(modelAfecta
				.getFechaAplicacion());
		carteraAfectaNivelacionOanticipo.setFechaAplicacion(modelAfecta
				.getFechaAplicacion());
		carteraAfectaNivelacionOanticipo.setCartera(modelAfecta.getCartera());

		return carteraAfectaNivelacionOanticipo;
	}

	private CarteraAfectaEJB registrarCarteraAfecta(CarteraAfectaIf modelAfecta) {
		CarteraAfectaEJB carteraAfecta = new CarteraAfectaEJB();

		carteraAfecta.setId(modelAfecta.getId());
		carteraAfecta.setCarteradetalleId(modelAfecta.getCarteradetalleId());
		carteraAfecta.setCarteraafectaId(modelAfecta.getCarteraafectaId());
		carteraAfecta.setUsuarioId(modelAfecta.getUsuarioId());
		carteraAfecta.setValor(utilitariosLocal.redondeoValor(modelAfecta
				.getValor()));
		carteraAfecta.setFechaCreacion(modelAfecta.getFechaCreacion());
		carteraAfecta.setFechaAplicacion(modelAfecta.getFechaAplicacion());
		carteraAfecta.setCartera(modelAfecta.getCartera());

		return carteraAfecta;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection getCarteraList(int startIndex, int endIndex, Map aMap, java.lang.Long idCliente, java.lang.Long idModulo, java.lang.Long idEmpresa) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}

		Long documentoId = (Long) aMap.get("documentoId");
		String preimpreso = (String) aMap.get("preimpreso");
		aMap.remove("documentoId");
		aMap.remove("preimpreso");

		String objectName = "c";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct c from CarteraEJB "
			+ objectName
			+ ", CarteraDetalleEJB cd, ClienteOficinaEJB co, TipoDocumentoEJB td where "
			+ where;

		queryString += " and c.clienteoficinaId = co.id and c.tipodocumentoId = td.id and c.id = cd.carteraId and cd.documentoId is not null";

		if (idCliente != 0L)
			queryString += " and co.clienteId = " + idCliente;

		if (idModulo != 0L)
			queryString += " and td.moduloId = " + idModulo;

		if (idEmpresa != 0L)
			queryString += " and td.empresaId = " + idEmpresa;

		if ((documentoId != null) && (documentoId != 0L))
			queryString += " and cd.documentoId = " + documentoId;

		if ((preimpreso != null) && !preimpreso.equals(""))
			queryString += " and cd.preimpreso like '" + preimpreso + "'";

		queryString += " order by c.fechaEmision desc, c.codigo desc";

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
	public Collection getCarteraTransferibleList(int startIndex, int endIndex, Map aMap, java.lang.Long idModulo, java.lang.Long idEmpresa) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}

		String objectName = "c";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct c from CarteraEJB " + objectName + ", TipoDocumentoEJB td where " + where;

		queryString += " and c.tipodocumentoId = td.id and td.transferible = 'S'";

		if (idModulo != 0L)
			queryString += " and td.moduloId = " + idModulo;

		if (idEmpresa != 0L)
			queryString += " and td.empresaId = " + idEmpresa;

		queryString += " order by c.id";

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
	public int getCarteraListSize(Map aMap, java.lang.Long idCliente, java.lang.Long idModulo, java.lang.Long idEmpresa) {
		Long documentoId = (Long) aMap.get("documentoId");
		String preimpreso = (String) aMap.get("preimpreso");
		aMap.remove("documentoId");
		aMap.remove("preimpreso");

		String objectName = "c";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct c from CarteraEJB "
			+ objectName
			+ ", CarteraDetalleEJB cd, ClienteOficinaEJB co, TipoDocumentoEJB td where "
			+ where;

		queryString += " and c.clienteoficinaId = co.id and c.tipodocumentoId = td.id and c.id = cd.carteraId and cd.documentoId is not null";

		if (idCliente != 0L)
			queryString += " and co.clienteId = " + idCliente;

		if (idModulo != 0L)
			queryString += " and td.moduloId = " + idModulo;

		if (idEmpresa != 0L)
			queryString += " and td.empresaId = " + idEmpresa;

		if ((documentoId != null) && (documentoId != 0L))
			queryString += " and cd.documentoId = " + documentoId;

		if ((preimpreso != null) && !preimpreso.equals(""))
			queryString += " and cd.preimpreso like '" + preimpreso + "'";

		queryString += " order by c.id";

		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}
		return query.getResultList().size();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getCarteraTransferibleListSize(Map aMap, java.lang.Long idModulo, java.lang.Long idEmpresa) {
		String objectName = "c";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct c from CarteraEJB " + objectName + ", TipoDocumentoEJB td where " + where;

		queryString += " and c.tipodocumentoId = td.id and td.transferible = 'S'";

		if (idModulo != 0L)
			queryString += " and td.moduloId = " + idModulo;

		if (idEmpresa != 0L)
			queryString += " and td.empresaId = " + idEmpresa;

		queryString += " order by c.id";

		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		List countQueryResult = query.getResultList();
		return countQueryResult.size();
	}

	public int getCarteraParaCruceNotaCreditoListSize(Map aMap,	Long documentoAplicaId) throws com.spirit.exception.GenericBusinessException {
		
		Vector<Long> facturasAsociadasId = new Vector<Long>();
		if(aMap.get("facturasAsociadasId") != null){
			facturasAsociadasId = (Vector<Long>)aMap.get("facturasAsociadasId");
			aMap.remove("facturasAsociadasId");
		}
		
		Long clienteId = 0L;
		if(aMap.get("clienteoficinaId") != null){
			Long clienteoficinaId = (Long)aMap.get("clienteoficinaId");
			ClienteOficinaIf clienteOficinaIf = clienteOficinaLocal.getClienteOficina(clienteoficinaId);
			ClienteIf clienteIf = clienteLocal.getCliente(clienteOficinaIf.getClienteId());
			clienteId = clienteIf.getId();
			aMap.remove("clienteoficinaId");
		}
		
		String objectName = "c";
		String where = !aMap.isEmpty()? QueryBuilder.buildWhere(aMap, objectName) + " and " : "";
		String queryString = "select distinct c from CarteraEJB " + objectName
		+ ", CarteraDetalleEJB cd, ClienteOficinaEJB co, ClienteEJB cl where " + where;

		queryString += " c.id = cd.carteraId and c.clienteoficinaId = co.id and co.clienteId = cl.id and cl.id = " + clienteId + " and c.saldo > 0 and cd.documentoId = "
			+ documentoAplicaId;
		
		for(int i=0; i<facturasAsociadasId.size(); i++){
			if(facturasAsociadasId.size() == 1){
				queryString += " and c.referenciaId = " + facturasAsociadasId.get(i) + "";
			}else if(i == 0){
				queryString += " and (c.referenciaId = " + facturasAsociadasId.get(i) + "";
			}else if (i != (facturasAsociadasId.size() - 1)){
				queryString += " or c.referenciaId = " + facturasAsociadasId.get(i) + "";
			}else{
				queryString += " or c.referenciaId = " + facturasAsociadasId.get(i) + ")";
			}
		}
		
		queryString += " order by c.preimpreso";
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);
		}
		List countQueryResult = query.getResultList();
		return countQueryResult.size();
	}

	public Collection getCarteraParaCruceNotaCreditoList(int startIndex, int endIndex, Map aMap, Long documentoAplicaId) throws com.spirit.exception.GenericBusinessException {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}
		
		Vector<Long> facturasAsociadasId = new Vector<Long>();
		if(aMap.get("facturasAsociadasId") != null){
			facturasAsociadasId = (Vector<Long>)aMap.get("facturasAsociadasId");
			aMap.remove("facturasAsociadasId");
		}

		Long clienteId = 0L;
		if(aMap.get("clienteoficinaId") != null){
			Long clienteoficinaId = (Long)aMap.get("clienteoficinaId");
			ClienteOficinaIf clienteOficinaIf = clienteOficinaLocal.getClienteOficina(clienteoficinaId);
			ClienteIf clienteIf = clienteLocal.getCliente(clienteOficinaIf.getClienteId());
			clienteId = clienteIf.getId();
			aMap.remove("clienteoficinaId");
		}

		
		String objectName = "c";
		String where = !aMap.isEmpty()? QueryBuilder.buildWhere(aMap, objectName) + " and " : "";
		String queryString = "select distinct c from CarteraEJB " + objectName
		+ ", CarteraDetalleEJB cd, ClienteOficinaEJB co, ClienteEJB cl where " + where;

		queryString += " c.id = cd.carteraId and c.clienteoficinaId = co.id and co.clienteId = cl.id and cl.id = " + clienteId + " and c.saldo > 0 and cd.documentoId = "
			+ documentoAplicaId;
		
		for(int i=0; i<facturasAsociadasId.size(); i++){
			if(facturasAsociadasId.size() == 1){
				queryString += " and c.referenciaId = " + facturasAsociadasId.get(i) + "";
			}else if(i == 0){
				queryString += " and (c.referenciaId = " + facturasAsociadasId.get(i) + "";
			}else if (i != (facturasAsociadasId.size() - 1)){
				queryString += " or c.referenciaId = " + facturasAsociadasId.get(i) + "";
			}else{
				queryString += " or c.referenciaId = " + facturasAsociadasId.get(i) + ")";
			}
		}
		
		queryString += " order by c.preimpreso";
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
	public java.util.Collection findEstadoCuentaByQueryByTipoFiltroByFiltroIdAndEmpresaId(
			Map aMap, java.lang.String tipoFiltro, java.lang.Long idFiltro,
			java.lang.Long idEmpresa)
	throws com.spirit.exception.GenericBusinessException {
		String tipo = (String) aMap.get("tipo");
		java.sql.Date fechaCorte = null;
		if (aMap.get("fechaCorte") != null)
			fechaCorte = (java.sql.Date) aMap.get("fechaCorte");
		String queryString = "select distinct c, cd from CarteraEJB c, CarteraDetalleEJB cd, ClienteOficinaEJB co, ClienteEJB cl, TipoDocumentoEJB td";
		String where = " where cd.carteraId = c.id and c.clienteoficinaId = co.id and co.clienteId = cl.id and c.tipodocumentoId = td.id and td.empresaId = "
			+ idEmpresa + " and c.tipo = '" + tipo + "'";
		if (tipoFiltro.equals(TIPO_FILTRO_CORPORACION))
			where += " and cl.corporacionId = " + idFiltro;
		else if (tipoFiltro.equals(TIPO_FILTRO_CLIENTE))
			where += " and cl.id = " + idFiltro;
		else if (tipoFiltro.equals(TIPO_FILTRO_CLIENTE_OFICINA))
			where += " and co.id = " + idFiltro;

		if (fechaCorte != null)
			where += " and c.fechaEmision <= :fechaCorte";

		queryString += where + " order by c.fechaEmision asc, c.codigo asc";

		Query query = manager.createQuery(queryString);

		if (fechaCorte != null) {
			java.sql.Timestamp closingDate = utilitariosLocal.resetTimestampEndDate(new java.sql.Timestamp(fechaCorte.getTime()));
			query.setParameter("fechaCorte", closingDate);
		}

		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCarteraByQuery(Map aMap, Long idEmpresa) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select distinct e from CarteraEJB " + objectName
		+ ", TipoDocumentoEJB td where " + where;

		queryString += " and e.tipodocumentoId = td.id and td.empresaId = "
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

	private void generarAsientoAutomaticoComprobanteEgreso(CarteraIf cartera, CarteraDetalleIf carteraDetalle, boolean procesarAsiento) throws GenericBusinessException {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		DocumentoIf documento = documentoLocal.getDocumento(carteraDetalle.getDocumentoId());
		Iterator carteraAfectaForAsientoIterator = carteraAfectaLocal.findCarteraAfectaByCarteradetalleId(carteraDetalle.getId()).iterator();
		while (carteraAfectaForAsientoIterator.hasNext()) {
			CarteraAfectaIf carteraAfecta = (CarteraAfectaIf) carteraAfectaForAsientoIterator.next();
			CarteraDetalleIf carteraDetalleAfecta = (CarteraDetalleIf) carteraDetalleLocal.getCarteraDetalle(carteraAfecta.getCarteraafectaId());
			CarteraIf carteraCompra = (CarteraIf) getCartera(carteraDetalleAfecta.getCarteraId());
			TipoDocumentoIf tipoDocumentoCompra = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(carteraCompra.getTipodocumentoId());
			if (documento != null) {
				parameterMap.clear();
				parameterMap.put("documentoId", documento.getId());
				Long etapa = 1L;
				if (tipoDocumentoCompra.getCodigo().equals("LIC"))
					etapa = 2L;
				else if (tipoDocumentoCompra.getCodigo().equals("COI"))
					etapa = 3L;

				parameterMap.put("etapa", etapa);
				Iterator eventoContableIterator = eventoContableLocal.findEventoContableByQuery(parameterMap).iterator();
				if (eventoContableIterator.hasNext()) {
					EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();;
					if (eventoContable != null) {
						parameterMap.clear();
						parameterMap.put("OFICINA_ID", cartera.getOficinaId());
						parameterMap.put("BEAN", cartera); // El bean debería ser cartera
						parameterMap.put("OBSERVACION", cartera.getComentario());
						parameterMap.put("CTAXPAG", carteraAfecta.getValor());
						if (documento.getCheque().equals("S") || documento.getDebitoBancario().equals("S")) {
							parameterMap.put("BANCO", carteraAfecta.getValor());
							parameterMap.put("CUENTA_BANCARIA_ID", carteraDetalle.getCuentaBancariaId());
						} else {
							parameterMap.put("CAJA", carteraAfecta.getValor());
							parameterMap.put("PREST_ACC", carteraAfecta.getValor());
						}

						parameterMap.put("TIPO_DOCUMENTO_ID", cartera.getTipodocumentoId()); // El tipo de documento debería ser el de la cartera
						pagoProveedorAsientoAutomaticoHandlerLocal.procesarAsientoAutomatico(eventoContable, parameterMap, procesarAsiento);
					}
				}
			}
		}
	}

	private void generarAsientoAutomaticoComprobanteEgresoDiferido(
			CarteraIf cartera, CarteraDetalleIf carteraDetalle,
			boolean procesarAsiento, Long etapa)
	throws GenericBusinessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		DocumentoIf documento = documentoLocal.getDocumento(carteraDetalle
				.getDocumentoId());
		Map eventoContableMap = new HashMap();
		eventoContableMap.put("documentoId", documento.getId());
		eventoContableMap.put("etapa", etapa);
		if (documento != null) {
			Iterator eventoContableIterator = eventoContableLocal
			.findEventoContableByQuery(eventoContableMap).iterator();
			if (eventoContableIterator.hasNext()) {
				EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator
				.next();
				;
				if (eventoContable != null) {
					parameterMap.clear();
					parameterMap.put("OFICINA_ID", cartera.getOficinaId());
					parameterMap.put("BEAN", cartera); // El bean debería ser
					// cartera
					parameterMap.put("OBSERVACION", cartera.getComentario());
					parameterMap.put("PROVTRANSITO", carteraDetalle.getValor());

					if (etapa.compareTo(new Long(1)) == 0) {
						parameterMap.put("CTAXPAG", carteraDetalle.getValor());
					} else if (etapa.compareTo(new Long(2)) == 0) {
						if (documento.getCheque().equals("S") || documento.getDebitoBancario().equals("S") || documento.getTransferenciaBancaria().equals("S")) {
							parameterMap.put("BANCO", carteraDetalle.getValor());
							parameterMap.put("CUENTA_BANCARIA_ID", carteraDetalle.getCuentaBancariaId());
						} else
							parameterMap.put("CAJA", carteraDetalle.getValor());
					}

					parameterMap.put("TIPO_DOCUMENTO_ID", cartera.getTipodocumentoId()); // El tipo de documento
					// debería ser el de la
					// cartera
					pagoProveedorAsientoAutomaticoHandlerLocal.procesarAsientoAutomatico(eventoContable,parameterMap, procesarAsiento);
				}
			}
		}
	}

	private AsientoIf generarAsientoAutomaticoAnticipoProveedor(CarteraIf cartera, CarteraDetalleIf carteraDetalle, boolean procesarAsiento, boolean isUpdate) throws GenericBusinessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		AsientoIf asientoRetornar = null;
		DocumentoIf documento = documentoLocal.getDocumento(carteraDetalle.getDocumentoId());

		if (documento != null) {
			parameterMap.put("documentoId", documento.getId());
			parameterMap.put("etapa", (!isUpdate) ? 1L : 2L);		
			// NO ESTA SALIENDO EL ASIENTO EN LA 2DA ETAPA
			Iterator eventoContableIterator = eventoContableLocal.findEventoContableByQuery(parameterMap).iterator();
			if (eventoContableIterator.hasNext()) {
				EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();
				if (eventoContable != null) {
					// SI ES UNA ACTUALIZACIÓN, BUSCAR SI EXISTE ASIENTO CORRESPONDIENTE A 2DA. ETAPA DE ANTICIPO A PROVEEDOR
					boolean existeAsiento = false;
					if (isUpdate) {
						Map queryMap = new HashMap();
						queryMap.put("tipoDocumentoId", cartera.getTipodocumentoId());
						queryMap.put("transaccionId", cartera.getId());
						queryMap.put("eventoContableId", eventoContable.getId());
						Iterator asientoIterator = asientoLocal.findAsientoByQuery(queryMap).iterator();
						if (asientoIterator.hasNext())
							existeAsiento = true;
					}
					if (!existeAsiento) {
						parameterMap.clear();
						parameterMap.put("OFICINA_ID", cartera.getOficinaId());
						parameterMap.put("BEAN", cartera); // El bean debería ser cartera
						parameterMap.put("OBSERVACION", cartera.getComentario());
						parameterMap.put("CTAXPAG", carteraDetalle.getValor());
						parameterMap.put("GASTO", carteraDetalle.getValor()); // Este valor debería ser el valor afectado al momento no el valor total del detalle pero ¿cómo hacerlo? es muy complicado
						parameterMap.put("GASTO_ANTICIPADO", carteraDetalle.getValor());
						parameterMap.put("PREST_PERS_NAT", carteraDetalle.getValor());
						if (documento.getCheque().equals("S")) {
							parameterMap.put("BANCO", carteraDetalle.getValor());
							parameterMap.put("CUENTA_BANCARIA_ID", carteraDetalle.getCuentaBancariaId());
						} else
							parameterMap.put("CAJA", carteraDetalle.getValor());

						parameterMap.put("TIPO_DOCUMENTO_ID", cartera.getTipodocumentoId()); // El tipo de documento debería ser el de la cartera
						// new PagoProveedorAsientoAutomaticoHandlerGato(eventoContable, parameterMap, procesarAsiento);
						asientoRetornar = anticipoProveedorAsientoAutomaticoHandlerLocal.procesarAsientoAutomatico(eventoContable,parameterMap, procesarAsiento);
					}
				}
			}

		}

		return asientoRetornar;
	}

	public void fixAsientosAnticipos() throws GenericBusinessException {
		Long tipoDocumentoAnticipo = 120L;
		String queryString = "select a from AsientoEJB a where a.tipoDocumentoId = " + tipoDocumentoAnticipo + " order by a.observacion asc";
		Query query = manager.createQuery(queryString);
		Iterator it = query.getResultList().iterator();
		while (it.hasNext()) {
			AsientoEJB asiento = (AsientoEJB) it.next();
			String numero = asiento.getNumero();
			if (!numero.equals("AXS-PC-02-2010-00159") &&
					!numero.equals("AXS-PC-01-2010-00300") &&
					!numero.equals("AXS-PC-06-2010-00344") &&
					!numero.equals("AXS-PC-05-2010-00443") &&
					!numero.equals("AXS-PC-06-2010-00350") &&
					!numero.equals("AXS-PC-05-2010-00445") &&
					!numero.equals("AXS-PC-06-2010-00351") &&
					!numero.equals("AXS-PC-06-2010-00400") &&
					!numero.equals("AXS-PC-06-2010-00404")) {
				Map aMap = new HashMap();
				aMap.put("tipodocumentoId", asiento.getTipoDocumentoId());
				aMap.put("id", asiento.getTransaccionId());
				Iterator carteraIt = findCarteraByQuery(aMap).iterator();
				if (it.hasNext()) {
					System.out.println("PROCESANDO ASIENTO >>>>>>>>> " + asiento.getNumero());
					CarteraIf cartera = (CarteraIf) carteraIt.next();
					Iterator carteraDetalleIt = carteraDetalleLocal.findCarteraDetalleByCarteraId(cartera.getId()).iterator();
					if (it.hasNext()) {
						CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) carteraDetalleIt.next();
						generarAsientoAutomaticoAnticipoProveedor(cartera, carteraDetalle, true, false);
					}
					System.out.println("PROCESADO EXITOSAMENTE >>>>>>>>> " + asiento.getNumero());
				}
			}
		}
	}

	private AsientoIf generarAsientoAutomaticoAnticipoCompraReembolso(
			CarteraIf cartera, CarteraDetalleIf carteraDetalle,
			boolean procesarAsiento) throws GenericBusinessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		AsientoIf asientoRetornar = null;
		DocumentoIf documento = documentoLocal.getDocumento(carteraDetalle
				.getDocumentoId());

		if (documento != null) {
			Iterator eventoContableIterator = eventoContableLocal
			.findEventoContableByDocumentoId(documento.getId())
			.iterator();
			if (eventoContableIterator.hasNext()) {
				EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator
				.next();
				;
				if (eventoContable != null) {
					parameterMap.clear();
					parameterMap.put("OFICINA_ID", cartera.getOficinaId());
					parameterMap.put("BEAN", cartera); // El bean debería ser
					// cartera
					parameterMap.put("OBSERVACION", cartera.getComentario());
					parameterMap.put("CTAXPAG", carteraDetalle.getValor());
					parameterMap.put("REEMBOLSO", carteraDetalle.getValor());
					parameterMap.put("TIPO_DOCUMENTO_ID", cartera
							.getTipodocumentoId()); // El tipo de documento
					// debería ser el de la
					// cartera
					// new
					// PagoProveedorAsientoAutomaticoHandlerGato(eventoContable,
					// parameterMap, procesarAsiento);
					asientoRetornar = anticipoCompraReembolsoAsientoAutomaticoHandlerLocal
					.procesarAsientoAutomatico(eventoContable,
							parameterMap, procesarAsiento);
				}
			}
		}

		return asientoRetornar;
	}

	private AsientoIf generarAsientoAutomaticoNotaInternaCreditoCliente(
			CarteraIf cartera, CarteraDetalleIf carteraDetalle,
			boolean procesarAsiento, Map parametrosEmpresaMap)
	throws GenericBusinessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		AsientoIf asientoRetornar = null;
		DocumentoIf documento = documentoLocal.getDocumento(carteraDetalle
				.getDocumentoId());

		if (documento != null) {
			Iterator eventoContableIterator = eventoContableLocal
			.findEventoContableByDocumentoId(documento.getId())
			.iterator();
			if (eventoContableIterator.hasNext()) {
				EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator
				.next();
				;
				if (eventoContable != null) {
					parameterMap.clear();
					parameterMap.put("OFICINA_ID", cartera.getOficinaId());
					parameterMap.put("BEAN_CARTERA", cartera); // El bean
					// debería ser
					// cartera
					parameterMap.put("BEAN_CARTERA_DETALLE", carteraDetalle);
					// double valorAfecta = ((Double)
					// parametrosEmpresaMap.get(String.valueOf(carteraDetalle.getId()))).doubleValue();
					parameterMap.put("CTAXCOB", Double.valueOf(carteraDetalle
							.getValor().doubleValue()));
					parameterMap.put("AUXILIAR", Double.valueOf(carteraDetalle
							.getValor().doubleValue()));
					asientoRetornar = notaInternaCreditoClienteAsientoAutomaticoHandlerLocal
					.generarNotaInternaCreditoClienteAsientoAutomaticoHandler(
							eventoContable, parameterMap,
							procesarAsiento);
				}
			}
		}

		return asientoRetornar;
	}

	private AsientoIf generarAsientoAutomaticoNotaInternaCreditoProveedor(
			CarteraIf cartera, CarteraDetalleIf carteraDetalle,
			boolean procesarAsiento, Map parametrosEmpresaMap)
	throws GenericBusinessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		AsientoIf asientoRetornar = null;
		DocumentoIf documento = documentoLocal.getDocumento(carteraDetalle
				.getDocumentoId());

		if (documento != null) {
			Iterator eventoContableIterator = eventoContableLocal
			.findEventoContableByDocumentoId(documento.getId())
			.iterator();
			if (eventoContableIterator.hasNext()) {
				EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator
				.next();
				;
				if (eventoContable != null) {
					parameterMap.clear();
					parameterMap.put("OFICINA_ID", cartera.getOficinaId());
					parameterMap.put("BEAN_CARTERA", cartera); // El bean
					// debería ser
					// cartera
					parameterMap.put("BEAN_CARTERA_DETALLE", carteraDetalle);
					double valorAfecta = ((Double) parametrosEmpresaMap
							.get(String.valueOf(carteraDetalle.getPrimaryKey())))
							.doubleValue();
					parameterMap.put("CTAXPAG", Double.valueOf(valorAfecta));
					parameterMap.put("AUXILIAR", Double.valueOf(valorAfecta));
					asientoRetornar = notaInternaCreditoProveedorAsientoAutomaticoHandlerLocal
					.generarNotaInternaCreditoProveedorAsientoAutomaticoHandler(
							eventoContable, parameterMap,
							procesarAsiento);
				}
			}
		}

		return asientoRetornar;
	}

	public Collection findCarteraByEmpresaIdByFechaInicioAndFechaFin(
			Long idEmpresa, java.sql.Date fechaInicio, java.sql.Date fechaFin)
	throws GenericBusinessException {
		String queryString = "select distinct c from CarteraEJB c, TipoDocumentoEJB td where c.tipodocumentoId = td.id and td.empresaId = "
			+ idEmpresa
			+ " and c.fechaEmision >= :fechaInicio and c.fechaEmision <= :fechaFin";
		Query query = manager.createQuery(queryString);
		java.sql.Timestamp startDate = utilitariosLocal.resetTimestampStartDate(new java.sql.Timestamp(fechaInicio.getTime()));
		query.setParameter("fechaInicio", startDate);
		java.sql.Timestamp endDate = utilitariosLocal.resetTimestampEndDate(new java.sql.Timestamp(fechaFin.getTime()));
		query.setParameter("fechaFin", endDate);
		return query.getResultList();// ///////////////////////////////////
	}


	//johanna: se debe modificar la busqueda de findCarteraDetalleByCarteraId por findCarteraDetalleByID
	private AsientoIf generarAsientoAutomaticoGeneral(CarteraIf cartera,
			CarteraDetalleIf carteraDetalle, boolean procesarAsiento,
			Map<String, Object> parametrosEmpresa, boolean isUpdate)
	throws GenericBusinessException {
		TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(cartera.getTipodocumentoId());

		AsientoIf asientoRetornar = null;
		if (cartera.getTipo().equals("C")) {
			if (tipoDocumento.getCodigo().equals("CIN") || tipoDocumento.getCodigo().equals("NNC")
					|| tipoDocumento.getCodigo().equals("NPC")	|| tipoDocumento.getCodigo().equals("ANS")) {
				Long etapa = 0L;
				DocumentoIf documento = documentoLocal
				.getDocumento(carteraDetalle.getDocumentoId());
				if (documento.getCodigo().equals("CHPO")) {
					etapa = 1L;
				} else if (documento.getCodigo().equals("ANSA")
						&& (carteraDetalle.getSaldo().compareTo(
								new BigDecimal(0)) == 1)) {
					etapa = 1L;
				} else if (documento.getCodigo().equals("ANSA")
						&& (carteraDetalle.getSaldo().compareTo(
								new BigDecimal(0)) == 0)) {
					etapa = 2L;
				}
				asientoRetornar = generarAsientoAutomaticoComprobanteIngreso(
						cartera, carteraDetalle, procesarAsiento,
						parametrosEmpresa, etapa);
			}
			if (tipoDocumento.getCodigo().equals("RCA")) {
				asientoRetornar = generarAsientoAutomaticoReciboCaja(cartera, carteraDetalle, procesarAsiento, parametrosEmpresa);
			}
			if (tipoDocumento.getCodigo().equals("NCC")) {
				Iterator carteraDetalleColeccionIt = carteraDetalleLocal
				.findCarteraDetalleByCarteraId(cartera.getId())
				.iterator();
				while (carteraDetalleColeccionIt.hasNext()) {
					CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) carteraDetalleColeccionIt
					.next();

					if (carteraDetalleIf.getPreimpreso().equals(
							carteraDetalle.getPreimpreso())
							&& (carteraDetalleIf.getAutorizacion()
									.equals(carteraDetalle.getAutorizacion()))) {
						DocumentoIf documento = documentoLocal
						.getDocumento(carteraDetalle.getDocumentoId());
						if (documento.getCodigo().equals("DEPP"))
							asientoRetornar = generarAsientoAutomaticoDescuentoProntoPago(
									cartera, carteraDetalleIf, procesarAsiento,
									parametrosEmpresa);
						if (documento.getCodigo().equals("NCAC"))
							if (!isUpdate)
								asientoRetornar = generarAsientoAutomaticoNotaCreditoAnticipoCliente(
										cartera, carteraDetalleIf,
										procesarAsiento, parametrosEmpresa, 1L);
							else
								asientoRetornar = generarAsientoAutomaticoNotaCreditoAnticipoCliente(
										cartera, carteraDetalleIf,
										procesarAsiento, parametrosEmpresa, 2L);
						break;
					}
				}
			}
			if (tipoDocumento.getCodigo().equals("ICC")) {
				asientoRetornar = generarAsientoAutomaticoNotaInternaCreditoCliente(
						cartera, carteraDetalle, procesarAsiento,
						parametrosEmpresa);
			}
		} else if (cartera.getTipo().equals("P")) {
			if (tipoDocumento.getCodigo().equals("NCP")
					|| ((tipoDocumento.getCodigo().equals("ANP") && cartera
							.getValor().compareTo(cartera.getSaldo()) == 0) && !isUpdate)
							|| (tipoDocumento.getCodigo().equals("ANP") && isUpdate)) {

				//ERROR: la busqueda se debe hacer por el id del detalle no de la cabecera, porq sino el detalle
				//Iterator carteraDetalleColeccionIt = carteraDetalleLocal.findCarteraDetalleByCarteraId(cartera.getId()).iterator();

				Iterator carteraDetalleColeccionIt = carteraDetalleLocal.findCarteraDetalleById(carteraDetalle.getId()).iterator();

				while (carteraDetalleColeccionIt.hasNext()) {
					CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) carteraDetalleColeccionIt.next();

					//if (carteraDetalleIf.getPreimpreso().equals(carteraDetalle.getPreimpreso()) && (carteraDetalleIf.getAutorizacion().equals(carteraDetalle.getAutorizacion()))) {
					if (carteraDetalleIf.getId().compareTo(carteraDetalle.getId()) == 0) {

						DocumentoIf documento = documentoLocal.getDocumento(carteraDetalle.getDocumentoId());

						if (documento.getCodigo().equals("NCPR"))
							asientoRetornar = generarAsientoAutomaticoNotaCreditoProveedor(
									cartera, carteraDetalleIf, procesarAsiento,
									parametrosEmpresa);
						// if (!isUpdate &&
						// (documento.getCodigo().equals("APCH") ||
						// documento.getCodigo().equals("APEF") ||
						// documento.getCodigo().equals("APPA")))
						if (documento.getCodigo().equals("APCH")
								|| documento.getCodigo().equals("APEF")
								|| documento.getCodigo().equals("APPA"))
							asientoRetornar = generarAsientoAutomaticoAnticipoProveedor(
									cartera, carteraDetalleIf, procesarAsiento,
									isUpdate);

						//revisar						
						if (!isUpdate && documento.getCodigo().equals("ACR"))
							asientoRetornar = generarAsientoAutomaticoAnticipoCompraReembolso(
									cartera, carteraDetalleIf, procesarAsiento);
						break;
					}
				}

			}

			if (tipoDocumento.getCodigo().equals("ICP")) {
				asientoRetornar = generarAsientoAutomaticoNotaInternaCreditoProveedor(
						cartera, carteraDetalle, procesarAsiento,
						parametrosEmpresa);
			}
		}
		return asientoRetornar;
	}

	private AsientoIf generarAsientoAutomaticoComprobanteIngreso(
			CarteraIf cartera, CarteraDetalleIf carteraDetalle,
			boolean procesarAsiento, Map<String, Object> parametrosEmpresa,
			Long etapa) throws GenericBusinessException {
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
					if(documentoIf.getCodigo().equals("COEM") || documentoIf.getCodigo().equals("CCTC") || documentoIf.getCodigo().equals("CCPA") || documentoIf.getCodigo().equals("CCEF") || documentoIf.getCodigo().equals("COCL") || documentoIf.getCodigo().equals("CCLD") || documentoIf.getCodigo().equals("CLNN") || 
							documentoIf.getCodigo().equals("REIC") || documentoIf.getCodigo().equals("RERC") || (documentoIf.getCodigo().equals("CHPO") && etapa.compareTo(1L) == 0)){
						parameterMap.put("CTAXCOB", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue());
					}
					if(documentoIf.getCodigo().equals("CHPO")) {
						if (etapa.compareTo(2L) == 0) {
							parameterMap.put("CHPOSTFECH", carteraDetalle.getValor().doubleValue());
						} else
							parameterMap.put("CHPOSTFECH", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue());
					}
					if(documentoIf.getCodigo().equals("COEM")){
						parameterMap.put("EMPLEADO", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue());
					}
					if(documentoIf.getRetencionRenta().equals("S") && documentoIf.getCodigo().equals("RERC")) {
						SriClienteRetencionIf sriClienteRetencion = sriClienteRetencionLocal.getSriClienteRetencion(carteraDetalle.getSriClienteRetencionId());
						parameterMap.put("RETERENTA", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue());
						parameterMap.put("ID_CUENTA_RETERENTA", sriClienteRetencion.getCuentaId());
					}
					if(documentoIf.getRetencionIva().equals("S") && documentoIf.getCodigo().equals("REIC")) {
						SriClienteRetencionIf sriClienteRetencion = sriClienteRetencionLocal.getSriClienteRetencion(carteraDetalle.getSriClienteRetencionId());
						parameterMap.put("RETEIVA", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue());
						parameterMap.put("ID_CUENTA_RETEIVA", sriClienteRetencion.getCuentaId());
					}				
					if(documentoIf.getCodigo().equals("CLNP")){
						parameterMap.put("NIVPOS", carteraDetalle.getValor().doubleValue());
						parameterMap.put("CTAXCOB", carteraDetalle.getValor().doubleValue());
					}					
					if(documentoIf.getCodigo().equals("CLNN")){
						parameterMap.put("NIVNEG", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue());
					}					
					if(documentoIf.getCodigo().equals("ANSA")){
						parameterMap.put("ANTICIPOCL", carteraDetalle.getValor().doubleValue());
						parameterMap.put("CTAXCOB", carteraDetalle.getValor().doubleValue());
					}					

					if(documentoIf.getCodigo().equals("COCL") || documentoIf.getCodigo().equals("COEM") || (documentoIf.getCodigo().equals("CHPO") && etapa.compareTo(2L) == 0)){
						if (documentoIf.getCodigo().equals("CHPO")) {
							parameterMap.put("BANCO", carteraDetalle.getValor().doubleValue());
						} else
							parameterMap.put("BANCO", carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue());

						parameterMap.put("CUENTA_BANCARIA_ID", carteraDetalle.getCuentaBancariaId());
					}

					if(documentoIf.getCodigo().equals("CCEF")){
						parameterMap.put("CAJA", carteraDetalle.getValor().doubleValue());
						parameterMap.put("GIFTCARD", 0D);
					}

					if(documentoIf.getCodigo().equals("CCTC")){
						parameterMap.put("TAR/CRE", carteraDetalle.getValor().doubleValue());
						parameterMap.put("GIFTCARD", 0D);
					}

					if(documentoIf.getCodigo().equals("CCPA")){
						parameterMap.put("PAYPAL", carteraDetalle.getValor().doubleValue());
						parameterMap.put("GIFTCARD", 0D);
					}

					if (documentoIf.getCodigo().equals("COEM")) {
						parameterMap.put("CTAXCOBXEMP", carteraDetalle.getValor().doubleValue());
					}

					parameterMap.put("TIPO_DOCUMENTO_ID", cartera.getTipodocumentoId());
					parameterMap.put("CARTERA_DETALLE", carteraDetalle);
					asientoRetornar = comprobanteIngresoAsientoAutomaticoHandlerLocal.generarComprobanteIngresoAsientoAutomaticoHandler(eventoContable, parameterMap, procesarAsiento, parametrosEmpresa, etapa);
				}
			}
		}
		return asientoRetornar;
	}

	private AsientoIf generarAsientoAutomaticoReciboCaja(CarteraIf cartera, CarteraDetalleIf carteraDetalle, boolean procesarAsiento, Map<String, Object> parametrosEmpresa) throws GenericBusinessException {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		AsientoIf asientoRetornar = null;
		DocumentoIf documentoIf = documentoLocal.getDocumento(carteraDetalle.getDocumentoId());

		if (documentoIf != null) {
			Map aMap = new HashMap();
			aMap.put("documentoId", documentoIf.getId());
			aMap.put("etapa", (Long) parametrosEmpresa.get("ETAPA"));
			Iterator eventoContableIterator = eventoContableLocal.findEventoContableByQuery(aMap).iterator();
			if (eventoContableIterator.hasNext()) {
				EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();
				if (eventoContable != null) {
					parameterMap.clear();
					parameterMap.put("BEAN", cartera);
					parameterMap.put("OBSERVACION", "CARTERA");
					parameterMap.put("CTAXCOB", carteraDetalle.getValor().doubleValue());

					if(documentoIf.getCodigo().equals("RCCE")){
						parameterMap.put("CAJA", carteraDetalle.getValor().doubleValue());
					}

					if(documentoIf.getCodigo().equals("RCTC")){
						parameterMap.put("TAR/CRE", carteraDetalle.getValor().doubleValue());
					}

					parameterMap.put("TIPO_DOCUMENTO_ID", cartera.getTipodocumentoId());
					parameterMap.put("CARTERA_DETALLE", carteraDetalle);
					asientoRetornar = reciboCajaAsientoAutomaticoHandlerLocal.generarReciboCajaAsientoAutomaticoHandler(eventoContable, parameterMap, procesarAsiento, parametrosEmpresa);
				}
			}
		}
		return asientoRetornar;
	}

	private AsientoIf generarAsientoAutomaticoTransferenciaDocumento(CarteraIf cartera, CarteraDetalleIf carteraDetalle, CarteraIf transferencia, boolean procesarAsiento, Map<String, Object> parametrosEmpresa) throws GenericBusinessException {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		AsientoIf asientoRetornar = null;
		DocumentoIf documentoIf = documentoLocal.getDocumento(carteraDetalle.getDocumentoId());

		if (documentoIf != null) {
			Map aMap = new HashMap();
			aMap.put("documentoId", documentoIf.getId());
			aMap.put("etapa", 9L);
			Iterator eventoContableIterator = eventoContableLocal.findEventoContableByQuery(aMap).iterator();
			if (eventoContableIterator.hasNext()) {
				EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator.next();
				if (eventoContable != null) {
					parameterMap.clear();
					parameterMap.put("BEAN", cartera);
					parameterMap.put("OBSERVACION", "CARTERA");
					if (cartera.getTipo().equals("C"))
						parameterMap.put("CTAXCOB", carteraDetalle.getValor().doubleValue());
					else
						parameterMap.put("CTAXPAG", carteraDetalle.getValor().doubleValue());

					if(documentoIf.getCodigo().equals("RCCE")){
						parameterMap.put("CAJA", carteraDetalle.getValor().doubleValue());
					}

					if(documentoIf.getCodigo().equals("RCTC")){
						parameterMap.put("TAR/CRE", carteraDetalle.getValor().doubleValue());
					}

					parameterMap.put("TIPO_DOCUMENTO_ID", transferencia.getTipodocumentoId());
					parameterMap.put("CARTERA_DETALLE", carteraDetalle);
					parameterMap.put("TRANSFERENCIA", transferencia);
					asientoRetornar = transferenciaDocumentoAsientoAutomaticoHandlerLocal.generarTransferenciaDocumentoAsientoAutomaticoHandler(eventoContable, parameterMap, procesarAsiento, parametrosEmpresa);
				}
			}
		}
		return asientoRetornar;
	}

	private AsientoIf generarAsientoAutomaticoNotaCreditoAnticipoCliente(
			CarteraIf cartera, CarteraDetalleIf carteraDetalle,
			boolean procesarAsiento, Map<String, Object> parametrosEmpresa,
			Long etapa) throws GenericBusinessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		AsientoIf asientoRetornar = null;
		DocumentoIf documentoIf = documentoLocal.getDocumento(carteraDetalle
				.getDocumentoId());
		if (documentoIf != null) {
			Map aMap = new HashMap();
			aMap.put("documentoId", documentoIf.getId());
			if (etapa.compareTo(0L) != 0)
				aMap.put("etapa", etapa);
			Iterator eventoContableIterator = eventoContableLocal
			.findEventoContableByQuery(aMap).iterator();
			if (eventoContableIterator.hasNext()) {
				EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator
				.next();
				if (eventoContable != null) {
					parameterMap.clear();
					parameterMap.put("BEAN", cartera);
					parameterMap.put("OBSERVACION", "CARTERA");
					double valor = carteraDetalle.getValor().doubleValue();
					parameterMap.put("INGRESO", utilitariosLocal
							.redondeoValor(valor / 1.12D));
					parameterMap.put("IVA", utilitariosLocal
							.redondeoValor((valor / 1.12D) * 0.12D));
					parameterMap.put("ANTICIPOCL", carteraDetalle.getValor()
							.doubleValue());
					parameterMap.put("CTAXCOB", carteraDetalle.getValor()
							.doubleValue());
					parameterMap.put("TIPO_DOCUMENTO_ID", cartera
							.getTipodocumentoId());
					parameterMap.put("CARTERA_DETALLE", carteraDetalle);
					asientoRetornar = notaCreditoAnticipoClienteAsientoAutomaticoHandlerLocal
					.generarNotaCreditoAnticipoClienteAsientoAutomaticoHandler(
							eventoContable, parameterMap,
							procesarAsiento, parametrosEmpresa, etapa);
				}
			}
		}
		return asientoRetornar;
	}

	private AsientoIf generarAsientoAutomaticoCruceNotaCreditoAnticipoCliente(
			CarteraIf cartera, CarteraDetalleIf carteraDetalle,
			double valorAplica, boolean procesarAsiento,
			Map<String, Object> parametrosEmpresa, Long etapa)
	throws GenericBusinessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		AsientoIf asientoRetornar = null;
		DocumentoIf documentoIf = documentoLocal.getDocumento(carteraDetalle
				.getDocumentoId());
		if (documentoIf != null) {
			Map aMap = new HashMap();
			aMap.put("documentoId", documentoIf.getId());
			if (etapa.compareTo(0L) != 0)
				aMap.put("etapa", etapa);
			Iterator eventoContableIterator = eventoContableLocal
			.findEventoContableByQuery(aMap).iterator();
			if (eventoContableIterator.hasNext()) {
				EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator
				.next();
				if (eventoContable != null) {
					parameterMap.clear();
					parameterMap.put("BEAN", cartera);
					parameterMap.put("OBSERVACION", "CARTERA");
					parameterMap.put("ANTICIPOCL", valorAplica);
					parameterMap.put("CTAXCOB", valorAplica);
					parameterMap.put("TIPO_DOCUMENTO_ID", cartera
							.getTipodocumentoId());
					parameterMap.put("CARTERA_DETALLE", carteraDetalle);
					asientoRetornar = notaCreditoAnticipoClienteAsientoAutomaticoHandlerLocal
					.generarNotaCreditoAnticipoClienteAsientoAutomaticoHandler(
							eventoContable, parameterMap,
							procesarAsiento, parametrosEmpresa, etapa);
				}
			}
		}
		return asientoRetornar;
	}

	private AsientoIf generarAsientoAutomaticoDescuentoProntoPago(
			CarteraIf cartera, CarteraDetalleIf carteraDetalle,
			boolean procesarAsiento, Map<String, Object> parametrosEmpresa)
	throws GenericBusinessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		AsientoIf asientoRetornar = null;
		DocumentoIf documentoIf = documentoLocal.getDocumento(carteraDetalle
				.getDocumentoId());
		if (documentoIf != null) {
			Iterator carteraAfectaColeccionIt = carteraAfectaLocal
			.findCarteraAfectaByCarteradetalleId(carteraDetalle.getId())
			.iterator();
			while (carteraAfectaColeccionIt.hasNext()) {
				CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf) carteraAfectaColeccionIt
				.next();
				CarteraDetalleIf carteraDetalleIf = carteraDetalleLocal
				.getCarteraDetalle(carteraAfectaIf.getCarteraafectaId());
				CarteraIf carteraIf = getCartera(carteraDetalleIf
						.getCarteraId());
				FacturaIf factura = facturaLocal.getFactura(carteraIf
						.getReferenciaId());
				PedidoIf pedido = pedidoLocal.getPedido(factura.getPedidoId());
				Iterator facturaDetalleColeccionIt = facturaDetalleLocal
				.findFacturaDetalleByFacturaId(factura.getId())
				.iterator();
				descuentoProntoPagoAsientoAutomaticoHandlerLocal
				.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
				while (facturaDetalleColeccionIt.hasNext()) {
					FacturaDetalleIf facturaDetalle = (FacturaDetalleIf) facturaDetalleColeccionIt
					.next();
					Iterator eventoContableIterator = eventoContableLocal
					.findEventoContableByDocumentoId(
							documentoIf.getId()).iterator();
					if (eventoContableIterator.hasNext()) {
						EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator
						.next();
						if (eventoContable != null) {
							TipoDocumentoIf tipoDocumentoFactura = (TipoDocumentoIf) tipoDocumentoLocal
							.getTipoDocumento(factura
									.getTipodocumentoId());
							Double valor = carteraDetalle.getValor()
							.doubleValue()
							- carteraDetalle.getSaldo().doubleValue();
							Double iva = valor
							* ((Double) parametrosEmpresa.get("IVA") / 100);
							Double total = valor + iva;
							parameterMap.clear();
							parameterMap.put("BEAN_CARTERA", cartera);
							parameterMap.put("BEAN_CARTERA_DETALLE",
									carteraDetalle);
							parameterMap.put("BEAN_FACTURA", factura);
							parameterMap.put("CTAXCOB", total);
							parameterMap.put("IVA", iva);
							parameterMap.put("DESCUENTO", valor);
							parameterMap.put("PRODUCTO_ID", facturaDetalle
									.getProductoId());
							ProductoIf producto = productoLocal
							.getProducto(facturaDetalle.getProductoId());
							parameterMap.put("PROVEEDOR_ID", producto
									.getProveedorId());
							parameterMap.put("TIPO_DOCUMENTO_ID", cartera
									.getTipodocumentoId());
							asientoRetornar = descuentoProntoPagoAsientoAutomaticoHandlerLocal
							.generarDescuentoProntoPagoAsientoAutomaticoHandler(
									eventoContable, parameterMap,
									tipoDocumentoFactura, pedido
									.getTiporeferencia(),
									procesarAsiento, parametrosEmpresa);
						}
					}
				}
			}
		}
		return asientoRetornar;
	}

	private AsientoIf generarAsientoAutomaticoNotaCreditoProveedor(
			CarteraIf cartera, CarteraDetalleIf carteraDetalle,
			boolean procesarAsiento, Map<String, Object> parametrosEmpresa)
	throws GenericBusinessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		AsientoIf asientoRetornar = null;
		DocumentoIf documentoIf = documentoLocal.getDocumento(carteraDetalle
				.getDocumentoId());
		if (documentoIf != null) {
			Iterator carteraAfectaColeccionIt = carteraAfectaLocal
			.findCarteraAfectaByCarteradetalleId(carteraDetalle.getId())
			.iterator();
			while (carteraAfectaColeccionIt.hasNext()) {
				CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf) carteraAfectaColeccionIt
				.next();
				CarteraDetalleIf carteraDetalleIf = carteraDetalleLocal
				.getCarteraDetalle(carteraAfectaIf.getCarteraafectaId());
				CarteraIf carteraIf = getCartera(carteraDetalleIf
						.getCarteraId());
				CompraIf compra = compraLocal.getCompra(carteraIf
						.getReferenciaId());
				Iterator compraDetalleColeccionIt = compraDetalleLocal
				.findCompraDetalleByCompraId(compra.getId()).iterator();

				notaCreditoProveedorAsientoAutomaticoHandlerLocal
				.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());

				while (compraDetalleColeccionIt.hasNext()) {
					CompraDetalleIf compraDetalle = (CompraDetalleIf) compraDetalleColeccionIt
					.next();
					Iterator eventoContableIterator = eventoContableLocal
					.findEventoContableByDocumentoId(
							documentoIf.getId()).iterator();
					if (eventoContableIterator.hasNext()) {
						EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator
						.next();
						if (eventoContable != null) {
							TipoDocumentoIf tipoDocumentoCompra = (TipoDocumentoIf) tipoDocumentoLocal
							.getTipoDocumento(compra
									.getTipodocumentoId());
							Double valor = carteraDetalle.getValor()
							.doubleValue()
							- carteraDetalle.getSaldo().doubleValue();
							Double iva = valor
							* ((Double) parametrosEmpresa.get("IVA") / 100);
							Double total = valor + iva;
							parameterMap.clear();
							parameterMap.put("BEAN_CARTERA", cartera);
							parameterMap.put("BEAN_CARTERA_DETALLE",
									carteraDetalle);
							parameterMap.put("BEAN_COMPRA", compra);
							parameterMap.put("CTAXPAG", total);
							parameterMap.put("IVA", iva);
							parameterMap.put("COSTO", valor);
							parameterMap.put("PRODUCTO_ID", compraDetalle
									.getProductoId());
							ProductoIf producto = productoLocal
							.getProducto(compraDetalle.getProductoId());
							parameterMap.put("PROVEEDOR_ID", producto
									.getProveedorId());
							parameterMap.put("TIPO_DOCUMENTO_ID", cartera
									.getTipodocumentoId());
							asientoRetornar = notaCreditoProveedorAsientoAutomaticoHandlerLocal
							.generarNotaCreditoProveedorAsientoAutomaticoHandler(
									eventoContable, parameterMap,
									tipoDocumentoCompra,
									procesarAsiento, parametrosEmpresa);
						}
					}
				}
			}
		}
		return asientoRetornar;
	}

	/** ******************* RETENCION PROVEEDORES ******************* */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Object> procesarRetencionProveedores(
			List<CompraIf> comprasColeccion,
			Map<Long, ArrayList<Retencion>> retencionesMap,
			Map fechasRetencionesMap, Map<String, Object> parametrosEmpresa,
			Map proveedoresMap, Map proveedoresOficinasMap)
			throws GenericBusinessException {
		
		//Se cambio el metodo de procesar Retencion para que traiga además el asiento automatico generado y poder mostrar el reporte
		List<Object> retencionReportListYcarteraRetencion = new ArrayList<Object>();
		List<RetencionProveedorData> retencionReportList = new ArrayList<RetencionProveedorData>();
		AsientoIf asiento = null;
		
		try {
			// 1) Agrupar detalles de la tabla de retenciones de acuerdo a
			// preimpreso y autorización
			Iterator comprasIterator = comprasColeccion.iterator();
			List<Retencion> retencionList = new ArrayList<Retencion>();
			Vector<CarteraDetalleIf> carteraDetalleColeccion = new Vector<CarteraDetalleIf>();

			Long idEmpresa = (Long) parametrosEmpresa.get("idEmpresa");
			while (comprasIterator.hasNext()) {
				CompraIf compra = (CompraIf) comprasIterator.next();
				ArrayList<Retencion> retencionVector = (ArrayList<Retencion>) retencionesMap
				.get(compra.getId());
				if (retencionVector != null) {
					// Vector<Retencion> retencionVectorDesagrupado =
					// (Vector<Retencion>)(ObjectCloner.deepCopy(retencionVector));
					ArrayList<Retencion> retencionVectorDesagrupado = (ArrayList<Retencion>) (DeepCopy
							.copy(retencionVector));
					retencionList = agruparRetencionesPorComprobante(retencionVectorDesagrupado);
					// agruparRetencionesPorComprobante(retencionList,retencionVectorDesagrupado);
					Iterator<Retencion> retencionListIt = retencionList
					.iterator();
					java.sql.Date fechaEmision = (java.sql.Date) fechasRetencionesMap
					.get(compra.getId());
					CarteraIf cartera = registrarCartera(compra, retencionList,
							fechaEmision, parametrosEmpresa, proveedoresMap,
							proveedoresOficinasMap);
					carteraDetalleColeccion.clear();
					// 2) Guardar los datos en tabla Compra_Retencion
					while (retencionListIt.hasNext()) {
						Retencion retencion = (Retencion) retencionListIt
						.next();
						CompraRetencionData compraRetencion = new CompraRetencionData();
						compraRetencion.setEstablecimiento(retencion
								.getEstablecimiento());
						compraRetencion.setPuntoEmision(retencion
								.getPuntoEmision());
						compraRetencion
						.setSecuencial(retencion.getSecuencial());
						compraRetencion.setAutorizacion(retencion
								.getAutorizacion());
						// java.util.Date fechaEmision = new java.util.Date();
						compraRetencion.setFechaEmision(fechaEmision);
						compraRetencion.setCompraId(compra.getId());
						compraRetencion.setEjercicioFiscal(String
								.valueOf(retencion.getEjercicio()));
						compraRetencion.setBaseImponible(BigDecimal
								.valueOf(utilitariosLocal.redondeoValor(retencion.getBaseImponible())));
						compraRetencion.setImpuesto(retencion.getImpuesto()
								.substring(0, 1));
						compraRetencion.setCodigoImpuesto(retencion
								.getCodigoImpuesto());
						compraRetencion.setPorcentajeRetencion(BigDecimal
								.valueOf(retencion.getPorcentajeRetencion()));
						compraRetencion.setValorRetenido(BigDecimal
								.valueOf(utilitariosLocal.redondeoValor(retencion.getValorRetenido())));
						compraRetencionLocal
						.saveCompraRetencion(compraRetencion);
						// 3) Generar cartera por concepto de retención
						CarteraDetalleIf carteraDetalle = registrarCarteraDetalle(
								cartera, retencion, parametrosEmpresa);
						if (carteraDetalle.getValor().doubleValue() > 0D)
							carteraDetalleColeccion.add(carteraDetalle);
						
						generarRetencionReportList(retencionReportList, compra,
								retencion, compraRetencion);
					}

					int year = compra.getFecha().getYear() + 1900;
					int month = compra.getFecha().getMonth() + 1;
					String codigoCompra = compra.getCodigo();
					Map parameterMap = new HashMap();
					parameterMap.put("tipodocumentoId", compra
							.getTipodocumentoId());
					parameterMap.put("referenciaId", compra.getId());
					Iterator it = findCarteraByQuery(parameterMap, idEmpresa)
					.iterator();
					CarteraIf carteraCompra = null;
					if (it.hasNext())
						carteraCompra = (CarteraIf) it.next();
					// String codigoCarteraAfecta = "COM-" +
					// String.valueOf(year) + "-" + codigoCompra;
					/*
					 * EmpresaIf empresa = empresaLocal.getEmpresa(idEmpresa);
					 * String codigoCarteraAfecta = empresa.getCodigo() +
					 * "-COM-"; if (year > 2008) codigoCarteraAfecta +=
					 * formatoSerialMes.format(Double.parseDouble(String.valueOf(month))) +
					 * "-"; codigoCarteraAfecta += codigoCompra;
					 */
					if (carteraCompra != null) {
						CarteraIf carteraIf = procesarCartera(cartera,
								carteraDetalleColeccion, carteraCompra,
								idEmpresa);

						// 4) Generar asiento automático
						retencionProveedorAsientoAutomaticoHandlerLocal
						.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
						TipoDocumentoIf tipoDocumentoCompra = tipoDocumentoLocal
						.getTipoDocumento(compra.getTipodocumentoId());
						Long etapa = 1L;
						if (tipoDocumentoCompra.getCodigo().equals("LIC"))
							etapa = 2L;
						else if (tipoDocumentoCompra.getCodigo().equals("COI"))
							etapa = 3L;
						for (int i = 0; i < retencionVector.size(); i++) {
							Retencion retencion = (Retencion) retencionVector
							.get(i);
							if (i != retencionVector.size() - 1)
								asiento = generarAsientoAutomaticoRetencionCompra(
										compra, carteraIf, retencion, false,
										parametrosEmpresa, etapa);
							else if (i == retencionVector.size() - 1)
								asiento = generarAsientoAutomaticoRetencionCompra(
										compra, carteraIf, retencion, true,
										parametrosEmpresa, etapa);
							
						}
					} else {
						throw new GenericBusinessException(
						"No se ha podido generar la retención");
					}
				}
			}
		} catch (GenericBusinessException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al guardar información en CarteraEJB.", e);
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al guardar información en CarteraEJB.", e);
			throw new GenericBusinessException(
			"Se ha producido un error al guardar los datos de Cartera");
		}
		retencionReportListYcarteraRetencion.add(retencionReportList);
		retencionReportListYcarteraRetencion.add(asiento);		

		return retencionReportListYcarteraRetencion;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<RetencionProveedorData> procesarFixRetencionProveedores(
			List<CompraIf> comprasColeccion,
			Map<Long, ArrayList<Retencion>> retencionesMap,
			Map fechasRetencionesMap, Map<String, Object> parametrosEmpresa,
			Map proveedoresMap, Map proveedoresOficinasMap)
			throws GenericBusinessException {
		List<RetencionProveedorData> retencionReportList = new ArrayList<RetencionProveedorData>();

		try {
			// 1) Agrupar detalles de la tabla de retenciones de acuerdo a
			// preimpreso y autorización
			Iterator comprasIterator = comprasColeccion.iterator();
			List<Retencion> retencionList = new ArrayList<Retencion>();
			Vector<CarteraDetalleIf> carteraDetalleColeccion = new Vector<CarteraDetalleIf>();

			Long idEmpresa = (Long) parametrosEmpresa.get("idEmpresa");
			while (comprasIterator.hasNext()) {
				CompraIf compra = (CompraIf) comprasIterator.next();
				ArrayList<Retencion> retencionVector = (ArrayList<Retencion>) retencionesMap
				.get(compra.getId());
				if (retencionVector != null) {


					int year = compra.getFecha().getYear() + 1900;
					int month = compra.getFecha().getMonth() + 1;
					String codigoCompra = compra.getCodigo();
					Map parameterMap = new HashMap();
					parameterMap.put("tipodocumentoId", compra
							.getTipodocumentoId());
					parameterMap.put("referenciaId", compra.getId());
					Iterator it = findCarteraByQuery(parameterMap, idEmpresa)
					.iterator();
					CarteraIf carteraCompra = null;
					if (it.hasNext())
						carteraCompra = (CarteraIf) it.next();

					if (carteraCompra != null) {
						ClienteOficinaIf clienteOficinaIf = clienteOficinaLocal.getClienteOficina(compra.getProveedorId());
						ClienteIf clienteIf = clienteLocal.getCliente(clienteOficinaIf.getClienteId());
						Map<String, Object> mapaCompra =  new HashMap<String, Object>();
						mapaCompra.put("tipodocumentoId", new Long(5));
						mapaCompra.put("comentario", "RETENC%"+clienteIf.getIdentificacion()+"%"+compra.getPreimpreso());
						mapaCompra.put("tipo", "P");
						Collection<CarteraIf> carteras = findCarteraByQuery(mapaCompra);
						if ( carteras.size() == 0 || carteras.size() > 1 ){
							throw new GenericBusinessException("No existe cartera o Mas de una cartera con los mismo datos !!");
						}
						CarteraIf carteraIf = carteras.iterator().next();
						//CarteraIf carteraIf = procesarCartera(cartera,
						//		carteraDetalleColeccion, carteraCompra,
						//		idEmpresa);

						// 4) Generar asiento automático
						AsientoIf asiento = null;
						retencionProveedorAsientoAutomaticoHandlerLocal
						.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
						TipoDocumentoIf tipoDocumentoCompra = tipoDocumentoLocal
						.getTipoDocumento(compra.getTipodocumentoId());
						Long etapa = 1L;
						if (tipoDocumentoCompra.getCodigo().equals("LIC"))
							etapa = 2L;
						else if (tipoDocumentoCompra.getCodigo().equals("COI"))
							etapa = 3L;
						for (int i = 0; i < retencionVector.size(); i++) {
							Retencion retencion = (Retencion) retencionVector
							.get(i);
							if (i != retencionVector.size() - 1)
								asiento = generarAsientoAutomaticoRetencionCompra(
										compra, carteraIf, retencion, false,
										parametrosEmpresa, etapa);
							else if (i == retencionVector.size() - 1)
								asiento = generarAsientoAutomaticoRetencionCompra(
										compra, carteraIf, retencion, true,
										parametrosEmpresa, etapa);
						}
					} else {
						throw new GenericBusinessException(
						"No se ha podido generar la retención");
					}
				}
			}
		} catch (GenericBusinessException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al guardar información en CarteraEJB.", e);
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al guardar información en CarteraEJB.", e);
			throw new GenericBusinessException(
			"Se ha producido un error al guardar los datos de Cartera");
		}

		return retencionReportList;

	}

	private void generarRetencionReportList(
			List<RetencionProveedorData> retencionReportList, CompraIf compra,
			Retencion retencion, CompraRetencionData compraRetencion)
	throws GenericBusinessException {
		if (retencion.getValorRetenido() > 0D) {
			RetencionProveedorData retencionProveedorData = new RetencionProveedorData();
			ClienteOficinaIf clienteOficina = clienteOficinaLocal
			.getClienteOficina(compra.getProveedorId());
			ClienteIf cliente = clienteLocal.getCliente(clienteOficina
					.getClienteId());
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal
			.getTipoDocumento(compra.getTipodocumentoId());
			retencionProveedorData.setBaseImponible(String.valueOf(retencion
					.getBaseImponible()));
			retencionProveedorData.setCodigoImpuesto(retencion.getCodigoImpuesto());
			
			CiudadIf ciudad = ciudadLocal.getCiudad(clienteOficina.getCiudadId());
			retencionProveedorData.setDireccion(ciudad.getNombre()+", "+clienteOficina.getDireccion());
			//retencionProveedorData.setDireccion(clienteOficina.getDireccion());
			
			retencionProveedorData.setEjercicioFiscal(String.valueOf(retencion
					.getEjercicio()));
			java.sql.Date fechaEmision = compraRetencion.getFechaEmision();
			int year = fechaEmision.getYear() + 1900;
			int month = fechaEmision.getMonth() + 1;
			int day = fechaEmision.getDate();
			String mes = utilitariosLocal.getNombreMes(month);
			String fechaRetencion = String.valueOf(year)
			+ "-"
			+ mes.substring(0, 3)
			+ "-"
			+ formatoSerialMes.format(Double.parseDouble(String
					.valueOf(day)));
			// retencionProveedorData.setFechaEmision(String.valueOf(compraRetencion.getFechaEmision()));
			retencionProveedorData.setFechaEmision(fechaRetencion);
			retencionProveedorData.setImpuesto(retencion.getImpuesto());
			retencionProveedorData.setNumComprobanteRetencion(compraRetencion
					.getEstablecimiento()
					+ compraRetencion.getPuntoEmision()
					+ compraRetencion.getSecuencial());
			retencionProveedorData.setNumComprobanteVenta(compra.getPreimpreso());
			retencionProveedorData.setPorcentajeRetencion(String.valueOf(retencion
					.getPorcentajeRetencion()));
			retencionProveedorData.setProveedor(cliente.getRazonSocial());
			retencionProveedorData.setRuc(cliente.getIdentificacion());
			retencionProveedorData.setTelefono((clienteOficina.getTelefono() != null && clienteOficina.getTelefono().length() >= 7)?clienteOficina.getTelefono():"");
			if (tipoDocumento.getCodigo().equals("COM")
					|| tipoDocumento.getCodigo().equals("COR")
					|| tipoDocumento.getCodigo().equals("COI")) {
				retencionProveedorData.setTipoComprobante("FACTURA");
			} else if (tipoDocumento.getCodigo().equals("CNV")) {
				retencionProveedorData.setTipoComprobante("NOTA DE VENTA");
			} else {
				retencionProveedorData
				.setTipoComprobante(tipoDocumento.getNombre());
			}
			retencionProveedorData.setValor(String.valueOf(retencion
					.getValorRetenido()));
			retencionReportList.add(retencionProveedorData);
		}
	}

	private AsientoIf generarAsientoAutomaticoRetencionCompra(CompraIf compra,
			CarteraIf cartera, Retencion retencion, boolean procesarAsiento,
			Map<String, Object> parametrosEmpresa, Long etapa)
	throws GenericBusinessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String codigoDocumento = "";
		AsientoIf asientoRetornar = null;
		if (retencion.getImpuesto().equals("RENTA"))
			codigoDocumento = "RERP";
		else
			codigoDocumento = "REIP";

		parameterMap.put("codigo", codigoDocumento);
		Long idEmpresa = (Long) parametrosEmpresa.get("idEmpresa");
		Iterator documentoIt = documentoLocal.findDocumentoByQueryAndEmpresaId(
				parameterMap, idEmpresa).iterator();

		if (documentoIt.hasNext()) {
			DocumentoIf documento = (DocumentoIf) documentoIt.next();

			if (documento != null) {
				parameterMap.clear();
				parameterMap.put("documentoId", documento.getId());
				parameterMap.put("etapa", etapa);
				Iterator eventoContableIterator = eventoContableLocal
				.findEventoContableByQuery(parameterMap).iterator();
				if (eventoContableIterator.hasNext()) {
					EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator
					.next();
					;
					if (eventoContable != null) {
						parameterMap.clear();
						parameterMap.put("COMPRA_BEAN", compra);
						parameterMap.put("CARTERA_BEAN", cartera);
						parameterMap.put("RETENCION_BEAN", retencion);
						parameterMap
						.put("OBSERVACION", compra.getObservacion());
						parameterMap.put("CTAXPAG", retencion
								.getValorRetenido());
						if (retencion.getImpuesto().equals("RENTA")) {
							parameterMap.put("RETERENTA", retencion
									.getValorRetenido());
							parameterMap.put("ID_CUENTA_RETERENTA", retencion
									.getCuentaId());
						} else {
							parameterMap.put("RETEIVA", retencion
									.getValorRetenido());
							parameterMap.put("ID_CUENTA_RETEIVA", retencion
									.getCuentaId());
						}
						parameterMap.put("TIPO_DOCUMENTO_ID", cartera
								.getTipodocumentoId());
						asientoRetornar = retencionProveedorAsientoAutomaticoHandlerLocal
						.generarRetencionProveedorAsientoAutomaticoHandler(
								eventoContable, parameterMap,
								procesarAsiento, parametrosEmpresa);
					}
				}
			}
		}
		return asientoRetornar;
	}

	private List<Retencion> agruparRetencionesPorComprobante(
			List<Retencion> retencionesColeccion) {
		List<Retencion> retencionesAgrupadas = new ArrayList<Retencion>();
		Iterator retencionesIterator = retencionesColeccion.iterator();

		while (retencionesIterator.hasNext()) {
			Retencion retencion = (Retencion) retencionesIterator.next();
			Iterator retencionesAgrupadasIterator = retencionesAgrupadas
			.iterator();
			boolean agrupada = false;
			while (retencionesAgrupadasIterator.hasNext()) {
				Retencion retencionAgrupada = (Retencion) retencionesAgrupadasIterator
				.next();
				if (retencion.getEstablecimiento().equals(
						retencionAgrupada.getEstablecimiento())
						&& retencion.getPuntoEmision().equals(
								retencionAgrupada.getPuntoEmision())
								&& retencion.getSecuencial().equals(
										retencionAgrupada.getSecuencial())
										&& retencion.getAutorizacion().equals(
												retencionAgrupada.getAutorizacion())
												&& retencion.getImpuesto().equals(
														retencionAgrupada.getImpuesto())
														&& retencion.getCodigoImpuesto().equals(
																retencionAgrupada.getCodigoImpuesto())) {
					double baseImponible = 0D;
					double valorRetenido = 0D;
					baseImponible = Double.valueOf(retencionAgrupada
							.getBaseImponible());
					baseImponible += Double.valueOf(retencion
							.getBaseImponible());
					valorRetenido = Double.valueOf(retencionAgrupada
							.getValorRetenido());
					valorRetenido += Double.valueOf(retencion
							.getValorRetenido());

					if (valorRetenido >= 0.0) {
						retencionAgrupada.setBaseImponible(baseImponible);
						retencionAgrupada.setValorRetenido(valorRetenido);
					}

					agrupada = true;
				}
			}

			if (!agrupada)
				retencionesAgrupadas.add(retencion);
		}
		return retencionesAgrupadas;
		// retencionesColeccion = retencionesAgrupadas;
		// return retencionesColeccion;
	}

	private CarteraIf registrarCartera(CompraIf compra, List retencionList,
			java.sql.Date fechaEmision, Map<String, Object> parametrosEmpresa,
			Map proveedoresMap, Map proveedoresOficinasMap)
	throws GenericBusinessException {
		CarteraData cartera = new CarteraData();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		Iterator it;
		Long idOficina = (Long) parametrosEmpresa.get("idOficina");
		Long idEmpresa = (Long) parametrosEmpresa.get("idEmpresa");
		Long idUsuario = (Long) parametrosEmpresa.get("idUsuario");
		String codMoneda = (String) parametrosEmpresa.get("codMoneda");
		try {
			cartera.setTipo("P");
			cartera.setOficinaId(idOficina);
			parameterMap.put("codigo", "CRE");
			parameterMap.put("empresaId", idEmpresa);
			parameterMap.put("estado", "A");
			it = tipoDocumentoLocal.findTipoDocumentoByQuery(parameterMap)
			.iterator();
			if (it.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) it.next();
				cartera.setTipodocumentoId(tipoDocumento.getId());
				String unNumeroCartera = getNumeroCartera(fechaEmision,
						tipoDocumento.getCodigo(), idEmpresa, idOficina);
				cartera.setCodigo(unNumeroCartera);
			}

			cartera.setClienteoficinaId(compra.getProveedorId());
			cartera.setUsuarioId(idUsuario);
			it = monedaLocal.findMonedaByCodigo(codMoneda).iterator();
			if (it.hasNext()) {
				MonedaIf moneda = (MonedaIf) it.next();
				cartera.setMonedaId(moneda.getId());
			}
			cartera.setFechaEmision(utilitariosLocal.fromSqlDateToTimestamp(fechaEmision));
			double valorRetenido = 0D;
			for (int i = 0; i < retencionList.size(); i++) {
				Retencion retencion = (Retencion) retencionList.get(i);
				valorRetenido += retencion.getValorRetenido();
			}
			cartera.setValor(BigDecimal.valueOf(utilitariosLocal.redondeoValor(valorRetenido)));
			cartera.setSaldo(BigDecimal.valueOf(utilitariosLocal.redondeoValor(valorRetenido)));
			cartera.setFechaUltimaActualizacion(utilitariosLocal.fromSqlDateToTimestamp(fechaEmision));
			cartera.setEstado("N");
			ClienteOficinaIf proveedorOficina = (ClienteOficinaIf) proveedoresOficinasMap
			.get(compra.getProveedorId());
			ClienteIf proveedor = (ClienteIf) proveedoresMap
			.get(proveedorOficina.getClienteId());
			String comentario = "RETENCIÓN AL PROVEEDOR: "
				+ proveedor.getNombreLegal() + " RUC: "
				+ proveedor.getIdentificacion();
			cartera.setComentario(comentario + " FACT. # "
					+ compra.getPreimpreso());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(
			"Se ha producido un error al registrar la cartera!!!");
		}

		return cartera;
	}

	private String getNumeroCartera(java.sql.Date fechaCartera,
			String codigoTipoDocumento, Long idEmpresa, Long idOficina) {
		String codigo = "";

		try {
			EmpresaIf empresa = empresaLocal.getEmpresa(idEmpresa);
			String monthCartera = utilitariosLocal
			.getMonthFromDate(fechaCartera);
			String anioCartera = utilitariosLocal.getYearFromDate(fechaCartera);
			codigo = empresa.getCodigo() + "-";
			OficinaIf oficina = oficinaLocal.getOficina(idOficina);
			ServidorIf servidor = (oficina.getServidorId() != null) ? servidorLocal
					.getServidor(oficina.getServidorId())
					: null;
					if (servidor != null)
						codigo += servidor.getCodigo() + "-";
					codigo += codigoTipoDocumento + "-";
					nuevaCodificacionActiva = (Double.parseDouble(anioCartera) <= 2008) ? false
							: true;
					if (nuevaCodificacionActiva)
						codigo += monthCartera + "-";
					codigo += anioCartera + "-";
					return codigo;
		} catch (GenericBusinessException e1) {
			e1.printStackTrace();
		}

		return null;
	}

	private CarteraDetalleIf registrarCarteraDetalle(CarteraIf cartera,
			Retencion retencion, Map<String, Object> parametrosEmpresa)
	throws GenericBusinessException {
		CarteraDetalleData carteraDetalle = new CarteraDetalleData();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String codigoDocumento = "";
		Long idEmpresa = (Long) parametrosEmpresa.get("idEmpresa");

		try {
			/*
			 * if (retencion.getImpuesto().equals("RENTA")) codigoDocumento =
			 * "REFP"; else codigoDocumento = "REIP";
			 */
			if (retencion.getImpuesto().equals("RENTA"))
				codigoDocumento = "RERP";
			else
				codigoDocumento = "REIP";

			parameterMap.put("codigo", codigoDocumento);
			Iterator documentoIt = documentoLocal
			.findDocumentoByQueryAndEmpresaId(parameterMap, idEmpresa)
			.iterator();

			if (documentoIt.hasNext()) {
				DocumentoIf documento = (DocumentoIf) documentoIt.next();
				carteraDetalle.setDocumentoId(documento.getId());
			}

			// carteraDetalle.setSecuencial(); //Secuenciador de los detalles de
			// la cartera
			// carteraDetalle.setLineaId(); //La misma linea
			// carteraDetalle.setTipopagoId(); //Pago al contado
			// carteraDetalle.setCuentaBancariaId();
			// carteraDetalle.setReferencia();
			carteraDetalle.setPreimpreso(retencion.getEstablecimiento() + "-"
					+ retencion.getPuntoEmision() + "-"
					+ retencion.getSecuencial());
			carteraDetalle.setAutorizacion(retencion.getAutorizacion());
			// carteraDetalle.setDepositoId();
			carteraDetalle.setFechaCreacion(utilitariosLocal.fromTimestampToSqlDate(cartera.getFechaEmision()));
			carteraDetalle.setFechaCartera(utilitariosLocal.fromTimestampToSqlDate(cartera.getFechaEmision()));
			carteraDetalle.setValor(BigDecimal.valueOf(utilitariosLocal.redondeoValor(retencion
					.getValorRetenido())));
			carteraDetalle.setSaldo(BigDecimal.valueOf(utilitariosLocal.redondeoValor(retencion
					.getValorRetenido())));
			carteraDetalle.setCartera("S");
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(
			"Se ha producido un error al registrar el detalle de la cartera!!!");
		}

		return carteraDetalle;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findMovimientosPositivosCartera(Long idEmpresa,
			String tipo, Long idOperadorNegocio, java.sql.Date fechaInicial,
			java.sql.Date fechaFinal)
	throws com.spirit.exception.GenericBusinessException {
		// select distinct c.ID from cartera c, cliente_oficina co, cliente cl,
		// tipo_documento td where c.TIPO = 'C' and c.CLIENTEOFICINA_ID = co.ID
		// and co.CLIENTE_ID = cl.ID and c.TIPODOCUMENTO_ID = td.ID and
		// td.SIGNOCARTERA = '+' and c.ESTADO <> 'A' and cl.ID = &a
		
		String queryString = "select distinct c, u from CarteraEJB c, ClienteOficinaEJB co, ClienteEJB cl, TipoDocumentoEJB td, UsuarioEJB u where u.id = c.usuarioId and c.clienteoficinaId = co.id and co.clienteId = cl.id and c.tipodocumentoId = td.id and td.signocartera = '+' and c.estado <> 'A' and td.empresaId = "
			+ idEmpresa
			+ " and c.tipo = '"
			+ tipo + "'"; 
		if (idOperadorNegocio != null)
			queryString += " and cl.id = " + idOperadorNegocio;
		if (fechaInicial != null)
			queryString += " and c.fechaEmision >= :fechaInicial";
		if (fechaFinal != null)
			queryString += " and c.fechaEmision <= :fechaFinal";
		queryString += " order by c.fechaEmision asc, c.codigo asc";  
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCarteraByTipoDocumentoByEmpresaIdByFechaInicialByFechaFinalAndByCuentaBancariaId(String codigoTipoDocumento, Long idEmpresa,
			java.sql.Date fechaInicial,	java.sql.Date fechaFinal, Long idCuentaBancaria)
	throws com.spirit.exception.GenericBusinessException {
		
		String queryString = "select c, cd from CarteraEJB c, CarteraDetalleEJB cd, TipoDocumentoEJB td " +
				"where c.id = cd.carteraId and c.tipodocumentoId = td.id and td.codigo = '" + codigoTipoDocumento + "' " +
				"and td.empresaId = " + idEmpresa + " and (cd.depositoCuentaBancariaId = " + idCuentaBancaria + " or cd.transferenciaCuentaDestinoId = " + idCuentaBancaria + ")"; 
		
		if (fechaInicial != null)
			queryString += " and c.fechaEmision >= :fechaInicial";
		if (fechaFinal != null)
			queryString += " and c.fechaEmision <= :fechaFinal";
		
		queryString += " order by c.fechaEmision asc, c.codigo asc";  
		
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCarteraAfectadaByCarteraId(Long carteraId)
	throws com.spirit.exception.GenericBusinessException {
			
		String queryString = "select car from CarteraEJB c, CarteraDetalleEJB cd, CarteraAfectaEJB ca, CarteraEJB car, CarteraDetalleEJB cad " +
				"where c.id = cd.carteraId and cd.id = ca.carteradetalleId and ca.carteraafectaId = cad.id and cad.carteraId = car.id " +
				"and c.id = " + carteraId; 
		
		/*for(int i = 0; i < tiposDocumento.length; i++){
			if(i == 0 && tiposDocumento.length == 1){
				queryString += " and td.codigo = '" + tiposDocumento[i] + "'";
			}else if (i == 0 && tiposDocumento.length > 1){
				queryString += " and (td.codigo = '" + tiposDocumento[i] + "'";
			}else if (i > 0 && i <= (tiposDocumento.length -2)){
				queryString += " or td.codigo = '" + tiposDocumento[i] + "'";
			}else if (i > 0 && i == (tiposDocumento.length -1)){
				queryString += " or td.codigo = '" + tiposDocumento[i] + "')";
			}
		}*/
		
		queryString += " order by car.codigo asc";  
		
		Query query = manager.createQuery(queryString);
		
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findMovimientosPositivosCarteraAfectados(
			Long idEmpresa, String tipo, Long idOperadorNegocio,
			java.sql.Date fechaInicial, java.sql.Date fechaFinal)
	throws com.spirit.exception.GenericBusinessException {
		// QUIEN AFECTO EN CUANTO A QUIEN
		// select distinct ca.CARTERADETALLE_ID, ca.VALOR, car.* from
		// cartera_afecta ca, cartera car, cartera_detalle cardet where
		// cardet.CARTERA_ID = car.ID and cardet.ID = ca.CARTERAAFECTA_ID and
		// ca.CARTERAAFECTA_ID in (select distinct cd.ID from cartera c,
		// cartera_detalle cd, cliente_oficina co, cliente cl, tipo_documento td
		// where cd.CARTERA_ID = c.ID and c.TIPO = 'C' and c.CLIENTEOFICINA_ID =
		// co.ID and co.CLIENTE_ID = cl.ID and c.TIPODOCUMENTO_ID = td.ID and
		// td.SIGNOCARTERA = '+' and c.ESTADO <> 'A' and cl.ID = &a) and
		// car.ESTADO <> 'A' order by car.FECHA_EMISION asc, car.CODIGO asc
		String queryString = "select distinct ca, car from CarteraAfectaEJB ca, CarteraEJB car, CarteraDetalleEJB cardet where cardet.carteraId = car.id and cardet.id = ca.carteraafectaId and ca.carteraafectaId in (select distinct cd.id from CarteraEJB c, CarteraDetalleEJB cd, ClienteOficinaEJB co, ClienteEJB cl, TipoDocumentoEJB td where cd.carteraId = c.id and c.clienteoficinaId = co.id and co.clienteId = cl.id and c.tipodocumentoId = td.id and td.signocartera = '+' and c.estado <> 'A' and td.empresaId = "
			+ idEmpresa
			+ " and c.tipo = '"
			+ tipo + "'"; 
		if (idOperadorNegocio != null)
			queryString += " and cl.id = " + idOperadorNegocio;
		if (fechaInicial != null)
			queryString += " and c.fechaEmision >= :fechaInicial";
		if (fechaFinal != null)
			queryString += " and c.fechaEmision <= :fechaFinal";
		queryString += ") and car.estado <> 'A' order by car.fechaEmision asc, car.codigo asc";
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

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findMovimientosNegativosCartera(Long idEmpresa,
			String tipo, Long idOperadorNegocio, java.sql.Date fechaInicial,
			java.sql.Date fechaFinal)
	throws com.spirit.exception.GenericBusinessException {
		// QUIEN AFECTO CON CUAL TRANSACCION DE QUE TIPO
		// select distinct cardet.* , car.*, d.* from cartera car,
		// cartera_detalle cardet, documento d where cardet.CARTERA_ID = car.ID
		// and cardet.DOCUMENTO_ID = d.ID and cardet.ID in (select
		// ca.CARTERADETALLE_ID from cartera_afecta ca where ca.CARTERAAFECTA_ID
		// in (select distinct cd.ID from cartera c, cartera_detalle cd,
		// cliente_oficina co, cliente cl, tipo_documento td where cd.CARTERA_ID
		// = c.ID and c.TIPO = 'C' and c.CLIENTEOFICINA_ID = co.ID and
		// co.CLIENTE_ID = cl.ID and c.TIPODOCUMENTO_ID = td.ID and
		// td.SIGNOCARTERA = '+' and c.ESTADO <> 'A' and cl.ID = &a)) and
		// car.ESTADO <> 'A' order by car.FECHA_EMISION asc, car.CODIGO asc
		String queryString = "select distinct cardet, car, d, u from CarteraEJB car, CarteraDetalleEJB cardet, DocumentoEJB d, UsuarioEJB u where u.id = car.usuarioId and cardet.carteraId = car.id and cardet.documentoId = d.id and cardet.id in (select ca.carteradetalleId from CarteraAfectaEJB ca where ca.carteraafectaId in (select distinct cd.id from CarteraEJB c, CarteraDetalleEJB cd, ClienteOficinaEJB co, ClienteEJB cl, TipoDocumentoEJB td where cd.carteraId = c.id and c.clienteoficinaId = co.id and co.clienteId = cl.id and c.tipodocumentoId = td.id and td.signocartera = '+' and c.estado <> 'A' and td.empresaId = "
			+ idEmpresa
			+ " and c.tipo = '"
			+ tipo + "'";
		if (idOperadorNegocio != null)
			queryString += " and cl.id = " + idOperadorNegocio;
		if (fechaInicial != null)
			queryString += " and c.fechaEmision >= :fechaInicial";
		if (fechaFinal != null)
			queryString += " and c.fechaEmision <= :fechaFinal";
		queryString += ")) and car.estado <> 'A' order by car.fechaEmision asc, car.codigo asc";
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

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCompraByCarteraRetencionId(
			Long idCarteraRetencion)
	throws com.spirit.exception.GenericBusinessException {
		String queryString = "select distinct co from CompraEJB co, CarteraEJB car, CarteraDetalleEJB cardet where co.tipodocumentoId = car.tipodocumentoId and co.id = car.referenciaId and car.id = cardet.carteraId and cardet.id in (select ca.carteraafectaId from CarteraEJB c, CarteraDetalleEJB cd, CarteraAfectaEJB ca where c.id = cd.carteraId and cd.id = ca.carteradetalleId and c.id = "
			+ idCarteraRetencion + ")";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findFacturaByCarteraRetencionId(
			Long idCarteraRetencion)
	throws com.spirit.exception.GenericBusinessException {
		String queryString = "select distinct f from FacturaEJB f, CarteraEJB car, CarteraDetalleEJB cardet where f.tipodocumentoId = car.tipodocumentoId and f.id = car.referenciaId and car.id = cardet.carteraId and cardet.id in (select ca.carteraafectaId from CarteraEJB c, CarteraDetalleEJB cd, CarteraAfectaEJB ca where c.id = cd.carteraId and cd.id = ca.carteradetalleId and c.id = "
			+ idCarteraRetencion + ")";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCarteraDetalleByFacturaIdAndDocumentoId(
			Long idFactura, Long idDocumento)
	throws com.spirit.exception.GenericBusinessException {
		// select distinct cardet.* from cartera_detalle cardet where cardet.ID
		// in (select distinct ca.CARTERADETALLE_ID from factura f, cartera c,
		// cartera_detalle cd, cartera_afecta ca where f.TIPODOCUMENTO_ID =
		// c.TIPODOCUMENTO_ID and f.ID = c.REFERENCIA_ID and cd.CARTERA_ID =
		// c.ID and ca.CARTERAAFECTA_ID = cd.ID and f.ID = 3645) and
		// cardet.DOCUMENTO_ID = 135
		String queryString = "select distinct cardet from CarteraDetalleEJB cardet where cardet.id in (select distinct ca.carteradetalleId from FacturaEJB f, CarteraEJB c, CarteraDetalleEJB cd, CarteraAfectaEJB ca where f.tipodocumentoId = c.tipodocumentoId and f.id = c.referenciaId and cd.carteraId = c.id and ca.carteraafectaId = cd.id and f.id = "
			+ idFactura + ") and cardet.documentoId = " + idDocumento;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	/** ******************* FACTURACION **************************** */

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void procesarAnularFactura(FacturaIf model,
			Vector<FacturaDetalleIf> modelDetalleList, Map mapaAsiento,
			UsuarioIf usuario) throws GenericBusinessException {
		try {
			List<FacturaDetalleIf> listaDetallefactura = (List<FacturaDetalleIf>) facturaDetalleLocal.findFacturaDetalleByFacturaId(model.getId());
			movimientoLocal.procesarAnulacionFactura(model, listaDetallefactura, usuario);
			
			FacturaEJB factura = registrarFactura(model);
			manager.merge(factura);

			PedidoIf pedidoAnulado = pedidoLocal.getPedido(factura.getPedidoId());
			pedidoAnulado.setEstado(ESTADO_ANULADO);
			manager.merge(pedidoAnulado);

			Map parameterMap = new HashMap();
			parameterMap.put("referenciaId", factura.getId());
			parameterMap.put("tipodocumentoId", factura.getTipodocumentoId());
			Collection<CarteraIf> modelCarteraList = findCarteraByQuery(parameterMap);
			for (CarteraIf cartera : modelCarteraList) {
				CarteraEJB dataCartera = manager.find(CarteraEJB.class, cartera.getId());
				if (!utilitariosLocal.redondeoValor(dataCartera.getSaldo()).equals(utilitariosLocal.redondeoValor(dataCartera.getValor())))
					throw new GenericBusinessException(
					"Factura ya ha sido pagada total o parcialmente, no se la puede anular !!");
				LogCarteraEJB logCartera = registrarLogCartera(cartera,
						usuario.getUsuario(), TipoLogCartera.FACTURA, factura.getNumero()
						.toString());
				manager.persist(logCartera);
				manager.flush();
				Collection<CarteraDetalleIf> modelCarteraDetalleList = carteraDetalleLocal
				.findCarteraDetalleByCarteraId(cartera.getId());
				for (CarteraDetalleIf carteraDetalle : modelCarteraDetalleList) {
					LogCarteraDetalleEJB logCarteraDetalle = registrarLogCarteraDetalle(
							carteraDetalle, usuario.getUsuario(), TipoLogCartera.FACTURA,
							factura.getNumero().toString(), dataCartera.getId());
					logCarteraDetalle.setLogCarteraId(logCartera
							.getPrimaryKey());
					manager.merge(logCarteraDetalle);
					CarteraDetalleEJB dataCarteraDetalle = manager.find(
							CarteraDetalleEJB.class, carteraDetalle.getId());
					manager.remove(dataCarteraDetalle);
					manager.flush();
				}
				manager.remove(dataCartera);
				manager.flush();
			}

			Collection<AsientoIf> modelAsientoList = asientoLocal
			.findAsientoByQuery(mapaAsiento);
			for (AsientoIf asiento : modelAsientoList) {
				String log = "ASIENTO_ID: " + asiento.getId()
				+ ", ELIMINADO POR ANULACION DE FACTURA # "
				+ factura.getNumero();
				asientoLocal.procesarEliminacionAsiento(asiento, usuario.getUsuario(), log, false);
			}
			
			//Al anular una factura cambio el estado del presupuesto asociado a ACEPTADO (si es que existe)
			//y seteo con estado ANULADO (A) los resgistros de la tabla PRESUPUESTO_FACTURACION
			if(pedidoAnulado.getTiporeferencia() != null && pedidoAnulado.getTiporeferencia().equals("P") && pedidoAnulado.getReferencia() != null){
				
				if(pedidoAnulado.getReferencia().equals("M")){
					Map<Long,PresupuestoIf> presupuestosMapa = new HashMap<Long,PresupuestoIf>();
					
					Map presupuestoFacturacionMap = new HashMap();
					//busco los registros solo con estado Facturado
					presupuestoFacturacionMap.put("estado", ESTADO_PRESUPUESTO_FACTURACION_FACTURADO);
					presupuestoFacturacionMap.put("facturaId", factura.getId());
					Collection presupuestosFacturacion = presupuestoFacturacionLocal.findPresupuestoFacturacionByQuery(presupuestoFacturacionMap);
					Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
					while(presupuestosFacturacionIt.hasNext()){
						PresupuestoFacturacionIf presupuestoFacturacion = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
						presupuestoFacturacion.setEstado(ESTADO_ANULADO);
						presupuestoFacturacionLocal.savePresupuestoFacturacion(presupuestoFacturacion);
						
						PresupuestoDetalleIf presupuestoDetalleIf = presupuestoDetalleLocal.getPresupuestoDetalle(presupuestoFacturacion.getPresupuestoDetalleId());
						PresupuestoIf presupuestoIf = presupuestoLocal.getPresupuesto(presupuestoDetalleIf.getPresupuestoId());
						presupuestosMapa.put(presupuestoIf.getId(), presupuestoIf);
						//presupuestoIf.setEstado("A");
						//presupuestoLocal.savePresupuesto(presupuestoIf);
					}
					//cambio el estado a Aprobado a los presupuestos
					Iterator presupuestosMapaIt = presupuestosMapa.keySet().iterator();
					while(presupuestosMapaIt.hasNext()){
						Long presupuestoId = (Long)presupuestosMapaIt.next();
						PresupuestoIf presupuestoIf = presupuestosMapa.get(presupuestoId);
						presupuestoIf.setEstado(ESTADO_APROBADO);
						presupuestoLocal.savePresupuesto(presupuestoIf);
					}
					
				}else{
					PresupuestoIf presupuestoIf = (PresupuestoIf)presupuestoLocal.findPresupuestoByCodigo(pedidoAnulado.getReferencia()).iterator().next();
					presupuestoIf.setEstado(ESTADO_APROBADO);
					presupuestoLocal.savePresupuesto(presupuestoIf);
					
					Map presupuestoFacturacionMap = new HashMap();
					//busco los registros solo con estado Facturado
					presupuestoFacturacionMap.put("estado", ESTADO_PRESUPUESTO_FACTURACION_FACTURADO);
					presupuestoFacturacionMap.put("facturaId", factura.getId());
					Collection presupuestosFacturacion = presupuestoFacturacionLocal.findPresupuestoFacturacionByQuery(presupuestoFacturacionMap);
					Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
					while(presupuestosFacturacionIt.hasNext()){
						PresupuestoFacturacionIf presupuestoFacturacion = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
						presupuestoFacturacion.setEstado(ESTADO_ANULADO);
						presupuestoFacturacionLocal.savePresupuestoFacturacion(presupuestoFacturacion);
					}
				}		
			}
			
			//////PLAN DE MEDIOS/////////////////////////////////////////////////////////////////
			else if(pedidoAnulado.getTiporeferencia() != null && pedidoAnulado.getTiporeferencia().equals("I") && pedidoAnulado.getReferencia() != null){
				
				//elimino la facturacion asociada (se cambio el codigo solo a poner los registros como anulados)
				Collection planMedioFacturacionColl = planMedioFacturacionSessionLocal.findPlanMedioFacturacionByPedidoId(pedidoAnulado.getId());
				Iterator planMedioFacturacionCollIt = planMedioFacturacionColl.iterator();
				while(planMedioFacturacionCollIt.hasNext()){
					PlanMedioFacturacionIf planMedioFacturacionIf = (PlanMedioFacturacionIf)planMedioFacturacionCollIt.next();
					//planMedioFacturacionSessionLocal.deletePlanMedioFacturacion(planMedioFacturacionIf.getId());
					planMedioFacturacionIf.setEstado(ESTADO_ANULADO);
					planMedioFacturacionSessionLocal.savePlanMedioFacturacion(planMedioFacturacionIf);
				}
				
				//elimino las forma de pago asociadas		
				Collection planMedioFormaPagoColl = planMedioFormaPagoSessionLocal.findPlanMedioFormaPagoByPedidoId(pedidoAnulado.getId());
				Iterator planMedioFormaPagoCollIt = planMedioFormaPagoColl.iterator();
				while(planMedioFormaPagoCollIt.hasNext()){
					PlanMedioFormaPagoIf planMedioFormaPagoIf = (PlanMedioFormaPagoIf)planMedioFormaPagoCollIt.next();
					//planMedioFormaPagoSessionLocal.deletePlanMedioFormaPago(planMedioFormaPagoIf.getId());
					planMedioFormaPagoIf.setEstado(ESTADO_ANULADO);
					planMedioFormaPagoSessionLocal.savePlanMedioFormaPago(planMedioFormaPagoIf);
					
					
					PlanMedioIf planMedioIf = (PlanMedioIf)planMedioSessionLocal.getPlanMedio(planMedioFormaPagoIf.getPlanMedioId());
					Map aMap = new HashMap();
					aMap.put("planMedioId",planMedioIf.getId());
					aMap.put("estado",ESTADO_PLAN_MEDIO_FACTURADO);
					//Collection facturasDelPlanMedio = planMedioFacturacionSessionLocal.findPlanMedioFacturacionByPlanMedioId(planMedioIf.getId());
					Collection facturasDelPlanMedio = planMedioFacturacionSessionLocal.findPlanMedioFacturacionByQuery(aMap);
					if(facturasDelPlanMedio.size() > 0){
						planMedioIf.setEstado(ESTADO_PLAN_MEDIO_PEDIDO);
					}else{
						planMedioIf.setEstado(ESTADO_APROBADO);
					}				
					planMedioSessionLocal.savePlanMedio(planMedioIf);
				}
				
			}
			
			else if(pedidoAnulado.getTiporeferencia() != null && pedidoAnulado.getTiporeferencia().equals("C") && pedidoAnulado.getReferencia() != null){
				//PRESUPUESTO
				Map<Long,PresupuestoIf> presupuestosMapa = new HashMap<Long,PresupuestoIf>();
				
				Map presupuestoFacturacionMap = new HashMap();
				//busco los registros solo con estado Facturado
				presupuestoFacturacionMap.put("estado", ESTADO_PRESUPUESTO_FACTURACION_FACTURADO);
				presupuestoFacturacionMap.put("facturaId", factura.getId());
				Collection presupuestosFacturacion = presupuestoFacturacionLocal.findPresupuestoFacturacionByQuery(presupuestoFacturacionMap);
				Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
				while(presupuestosFacturacionIt.hasNext()){
					PresupuestoFacturacionIf presupuestoFacturacion = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
					presupuestoFacturacion.setEstado(ESTADO_ANULADO);
					presupuestoFacturacionLocal.savePresupuestoFacturacion(presupuestoFacturacion);
					
					PresupuestoDetalleIf presupuestoDetalleIf = presupuestoDetalleLocal.getPresupuestoDetalle(presupuestoFacturacion.getPresupuestoDetalleId());
					PresupuestoIf presupuestoIf = presupuestoLocal.getPresupuesto(presupuestoDetalleIf.getPresupuestoId());
					presupuestosMapa.put(presupuestoIf.getId(), presupuestoIf);
					//presupuestoIf.setEstado("A");
					//presupuestoLocal.savePresupuesto(presupuestoIf);
				}
				//cambio el estado a Aprobado a los presupuestos
				Iterator presupuestosMapaIt = presupuestosMapa.keySet().iterator();
				while(presupuestosMapaIt.hasNext()){
					Long presupuestoId = (Long)presupuestosMapaIt.next();
					PresupuestoIf presupuestoIf = presupuestosMapa.get(presupuestoId);
					presupuestoIf.setEstado(ESTADO_APROBADO);
					presupuestoLocal.savePresupuesto(presupuestoIf);
				}
				
				//PLAN DE MEDIOS
				//elimino la facturacion asociada (se cambio el codigo solo a poner los registros como anulados)
				Collection planMedioFacturacionColl = planMedioFacturacionSessionLocal.findPlanMedioFacturacionByPedidoId(pedidoAnulado.getId());
				Iterator planMedioFacturacionCollIt = planMedioFacturacionColl.iterator();
				while(planMedioFacturacionCollIt.hasNext()){
					PlanMedioFacturacionIf planMedioFacturacionIf = (PlanMedioFacturacionIf)planMedioFacturacionCollIt.next();
					//planMedioFacturacionSessionLocal.deletePlanMedioFacturacion(planMedioFacturacionIf.getId());
					planMedioFacturacionIf.setEstado(ESTADO_ANULADO);
					planMedioFacturacionSessionLocal.savePlanMedioFacturacion(planMedioFacturacionIf);
				}
				
				//elimino las forma de pago asociadas		
				Collection planMedioFormaPagoColl = planMedioFormaPagoSessionLocal.findPlanMedioFormaPagoByPedidoId(pedidoAnulado.getId());
				Iterator planMedioFormaPagoCollIt = planMedioFormaPagoColl.iterator();
				while(planMedioFormaPagoCollIt.hasNext()){
					PlanMedioFormaPagoIf planMedioFormaPagoIf = (PlanMedioFormaPagoIf)planMedioFormaPagoCollIt.next();
					//planMedioFormaPagoSessionLocal.deletePlanMedioFormaPago(planMedioFormaPagoIf.getId());
					planMedioFormaPagoIf.setEstado(ESTADO_ANULADO);
					planMedioFormaPagoSessionLocal.savePlanMedioFormaPago(planMedioFormaPagoIf);
					
					
					PlanMedioIf planMedioIf = (PlanMedioIf)planMedioSessionLocal.getPlanMedio(planMedioFormaPagoIf.getPlanMedioId());
					Map aMap = new HashMap();
					aMap.put("planMedioId",planMedioIf.getId());
					aMap.put("estado",ESTADO_PLAN_MEDIO_FACTURADO);
					//Collection facturasDelPlanMedio = planMedioFacturacionSessionLocal.findPlanMedioFacturacionByPlanMedioId(planMedioIf.getId());
					Collection facturasDelPlanMedio = planMedioFacturacionSessionLocal.findPlanMedioFacturacionByQuery(aMap);
					if(facturasDelPlanMedio.size() > 0){
						planMedioIf.setEstado(ESTADO_PLAN_MEDIO_PEDIDO);
					}else{
						planMedioIf.setEstado(ESTADO_APROBADO);
					}				
					planMedioSessionLocal.savePlanMedio(planMedioIf);
				}
			}
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void procesarAnularNotaCredito(NotaCreditoIf model,
			Vector<NotaCreditoDetalleIf> modelDetalleList, Map mapaAsiento,
			String usuario) throws GenericBusinessException {
		try {
			NotaCreditoEJB notaCredito = registrarNotaCredito(model);
			manager.merge(notaCredito);
			Map parameterMap = new HashMap();
			parameterMap.put("referenciaId", notaCredito.getId());
			parameterMap.put("tipodocumentoId", notaCredito.getTipoDocumentoId());
			Collection<CarteraIf> modelCarteraList = findCarteraByQuery(parameterMap);
			for (CarteraIf cartera : modelCarteraList) {
				CarteraEJB dataCartera = manager.find(CarteraEJB.class, cartera.getId());
				/*if (!dataCartera.getSaldo().equals(dataCartera.getValor()))
					throw new GenericBusinessException(
							"Nota de crédito ya ha sido cruzada total o parcialmente, no se la puede anular !!");*/
				LogCarteraEJB logCartera = registrarLogCartera(cartera, usuario, TipoLogCartera.NOTA_CREDITO, notaCredito.getCodigo().toString());
				manager.persist(logCartera);
				manager.flush();
				Collection<CarteraDetalleIf> modelCarteraDetalleList = carteraDetalleLocal.findCarteraDetalleByCarteraId(cartera.getId());
				for (CarteraDetalleIf carteraDetalle : modelCarteraDetalleList) {
					LogCarteraDetalleEJB logCarteraDetalle = registrarLogCarteraDetalle(carteraDetalle, usuario, TipoLogCartera.NOTA_CREDITO, notaCredito.getCodigo().toString(), dataCartera.getId());
					logCarteraDetalle.setLogCarteraId(logCartera.getPrimaryKey());
					manager.merge(logCarteraDetalle);
					CarteraDetalleEJB dataCarteraDetalle = manager.find(CarteraDetalleEJB.class, carteraDetalle.getId());
					Collection<CarteraAfectaIf> carteraAfectaList = carteraAfectaLocal.findCarteraAfectaByCarteradetalleId(carteraDetalle.getId());
					for (CarteraAfectaIf carteraAfecta: carteraAfectaList) {
						CarteraDetalleEJB carteraDetalleAfectada = manager.find(CarteraDetalleEJB.class, carteraAfecta.getCarteraafectaId());
						carteraDetalleAfectada.setSaldo(carteraDetalleAfectada.getSaldo().add(carteraAfecta.getValor()));
						manager.merge(carteraDetalleAfectada);
						CarteraEJB carteraAfectada = manager.find(CarteraEJB.class, carteraDetalleAfectada.getCarteraId());
						carteraAfectada.setSaldo(carteraAfectada.getSaldo().add(carteraAfecta.getValor()));
						manager.merge(carteraAfectada);
					}
					manager.remove(dataCarteraDetalle);
					manager.flush();
				}
				manager.remove(dataCartera);
				manager.flush();
			}

			Collection<AsientoIf> modelAsientoList = asientoLocal.findAsientoByQuery(mapaAsiento);
			for (AsientoIf asiento : modelAsientoList) {
				String log = "ASIENTO_ID: " + asiento.getId() + ", ELIMINADO POR ANULACION DE N/C # " + notaCredito.getCodigo();
				asientoLocal.procesarEliminacionAsiento(asiento, usuario, log, false);
			}
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		}
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
		factura.setIva(model.getIva());
		factura.setIce(model.getIce());
		factura.setOtroImpuesto(model.getOtroImpuesto());
		factura.setCosto(model.getCosto());
		factura.setObservacion(model.getObservacion());
		factura.setEstado(model.getEstado());
		factura.setFacturaaplId(model.getFacturaaplId());
		factura.setDescuentoGlobal(model.getDescuentoGlobal());
		factura.setPorcentajeComisionAgencia(model.getPorcentajeComisionAgencia());
		factura.setAutorizacionSap(model.getAutorizacionSap());
		factura.setEquipoId(model.getEquipoId());
		factura.setDescuentosVarios(model.getDescuentosVarios());

		return factura;
	}

	private NotaCreditoEJB registrarNotaCredito(NotaCreditoIf model) {
		NotaCreditoEJB notaCredito = new NotaCreditoEJB();
		notaCredito.setId(model.getId());
		notaCredito.setOficinaId(model.getOficinaId());
		notaCredito.setTipoDocumentoId(model.getTipoDocumentoId());
		notaCredito.setOperadorNegocioId(model.getOperadorNegocioId());		
		notaCredito.setMonedaId(model.getMonedaId());
		notaCredito.setUsuarioId(model.getUsuarioId());
		notaCredito.setFechaEmision(model.getFechaEmision());
		notaCredito.setFechaVencimiento(model.getFechaVencimiento());
		notaCredito.setFechaCaducidad(model.getFechaCaducidad());
		notaCredito.setValor(model.getValor());
		notaCredito.setIva(model.getIva());
		notaCredito.setIce(model.getIce());
		notaCredito.setOtroImpuesto(model.getOtroImpuesto());
		notaCredito.setEstado(model.getEstado());
		notaCredito.setTipoCartera(model.getTipoCartera());
		notaCredito.setObservacion(model.getObservacion());
		notaCredito.setAutorizacion(model.getAutorizacion());
		notaCredito.setPreimpreso(model.getPreimpreso());
		notaCredito.setReferencia(model.getReferencia());
		notaCredito.setCodigo(model.getCodigo());

		return notaCredito;
	}

	private LogCarteraEJB registrarLogCartera(CarteraIf modelCartera,
			String usuario, TipoLogCartera tipoLogCartera, String numero)
	throws GenericBusinessException {
		LogCarteraEJB logCartera = new LogCarteraEJB();
		logCartera.setTipo(modelCartera.getTipo());
		logCartera.setOficinaId(modelCartera.getOficinaId());
		logCartera.setTipodocumentoId(modelCartera.getTipodocumentoId());
		logCartera.setCodigo(modelCartera.getCodigo());
		logCartera.setReferenciaId(modelCartera.getReferenciaId());
		logCartera.setClienteoficinaId(modelCartera.getClienteoficinaId());
		logCartera.setPreimpreso(modelCartera.getPreimpreso());
		logCartera.setUsuarioId(modelCartera.getUsuarioId());
		logCartera.setVendedorId(modelCartera.getVendedorId());
		logCartera.setMonedaId(modelCartera.getMonedaId());
		logCartera.setFechaEmision(utilitariosLocal.fromTimestampToSqlDate(modelCartera.getFechaEmision()));
		logCartera.setValor(utilitariosLocal.redondeoValor(modelCartera.getValor()));
		logCartera.setSaldo(utilitariosLocal.redondeoValor(modelCartera.getSaldo()));
		if(modelCartera.getFechaUltimaActualizacion() != null){
			logCartera.setFechaUltimaActualizacion(utilitariosLocal.fromTimestampToSqlDate(modelCartera.getFechaUltimaActualizacion()));
		}
		logCartera.setEstado(modelCartera.getEstado());
		logCartera.setComentario(modelCartera.getComentario());
		try {
			if (tipoLogCartera == TipoLogCartera.FACTURA)
				logCartera.setLog("CARTERA_ID: "
						+ modelCartera.getId()
						+ ", ELIMINADA POR ANULACION DE FACTURA # "
						+ numero
						+ ", REALIZADO POR: "
						+ usuario
						+ ", FECHA: "
						+ utilitariosLocal.getFechaUppercase(utilitariosLocal
								.dateHoy()));
			else if (tipoLogCartera == TipoLogCartera.COMPRA)
				logCartera.setLog("CARTERA_ID: "
						+ modelCartera.getId()
						+ ", ELIMINADA POR ANULACION DE COMPRA CÓDIGO # "
						+ numero
						+ ", REALIZADO POR: "
						+ usuario
						+ ", FECHA: "
						+ utilitariosLocal.getFechaUppercase(utilitariosLocal
								.dateHoy()));
			else if (tipoLogCartera == TipoLogCartera.RETENCION)
				logCartera.setLog("CARTERA_ID: "
						+ modelCartera.getId()
						+ ", ELIMINADA POR ANULACION DE RETENCION CÓDIGO # "
						+ numero
						+ ", REALIZADO POR: "
						+ usuario
						+ ", FECHA: "
						+ utilitariosLocal.getFechaUppercase(utilitariosLocal
								.dateHoy()));
			else if (tipoLogCartera == TipoLogCartera.FACTURA_ELIMINADA)
				logCartera.setLog("CARTERA_ID: "
						+ modelCartera.getId()
						+ ", ELIMINADA POR ELIMINACION DE FACTURA # "
						+ numero
						+ ", REALIZADO POR: "
						+ usuario
						+ ", FECHA: "
						+ utilitariosLocal.getFechaUppercase(utilitariosLocal
								.dateHoy()));
			else if (tipoLogCartera == TipoLogCartera.NOTA_CREDITO)
				logCartera.setLog("CARTERA_ID: "
						+ modelCartera.getId()
						+ ", POR ANULACION DE NOTA DE CREDITO # "
						+ numero
						+ ", REALIZADO POR: "
						+ usuario
						+ ", FECHA: "
						+ utilitariosLocal.getFechaUppercase(utilitariosLocal
								.dateHoy()));
			else
				throw new GenericBusinessException(
				"Tipo de Documento no soportado para registro de Log");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return logCartera;
	}

	private LogCompraRetencionEJB registrarLogCompraRetencion(
			CompraRetencionIf compraRetencion, String usuario)
	throws GenericBusinessException {
		LogCompraRetencionEJB logCompraRetencion = new LogCompraRetencionEJB();
		logCompraRetencion.setEstablecimiento(compraRetencion
				.getEstablecimiento());
		logCompraRetencion.setPuntoEmision(compraRetencion.getPuntoEmision());
		logCompraRetencion.setSecuencial(compraRetencion.getSecuencial());
		logCompraRetencion.setAutorizacion(compraRetencion.getAutorizacion());
		logCompraRetencion.setFechaEmision(compraRetencion.getFechaEmision());
		logCompraRetencion.setCompraId(compraRetencion.getCompraId());
		logCompraRetencion.setEjercicioFiscal(compraRetencion
				.getEjercicioFiscal());
		logCompraRetencion.setBaseImponible(compraRetencion.getBaseImponible());
		logCompraRetencion.setImpuesto(compraRetencion.getImpuesto());
		logCompraRetencion.setPorcentajeRetencion(compraRetencion
				.getPorcentajeRetencion());
		logCompraRetencion.setValorRetenido(compraRetencion.getValorRetenido());
		logCompraRetencion.setCodigoImpuesto(compraRetencion
				.getCodigoImpuesto());
		try {
			logCompraRetencion.setLog("COMPRA_RETENCION_ID: "
					+ compraRetencion.getId()
					+ ", ELIMINADA POR ANULACION DE RETENCION, REALIZADO POR: "
					+ usuario
					+ ", FECHA: "
					+ utilitariosLocal.getFechaUppercase(utilitariosLocal
							.dateHoy()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return logCompraRetencion;
	}

	private LogCarteraDetalleEJB registrarLogCarteraDetalle(
			CarteraDetalleIf carteraDetalleModel, String usuario,
			TipoLogCartera tipoLogCartera, String numero, Long dataCarteraId)
	throws GenericBusinessException {
		LogCarteraDetalleEJB logCarteraDetalle = new LogCarteraDetalleEJB();
		// logCarteraDetalle.setLogCarteraId(carteraDetalleModel.getCarteraId());
		logCarteraDetalle.setDocumentoId(carteraDetalleModel.getDocumentoId());
		logCarteraDetalle.setLineaId(carteraDetalleModel.getLineaId());
		logCarteraDetalle.setPreimpreso(carteraDetalleModel.getPreimpreso());
		logCarteraDetalle.setFechaCreacion(carteraDetalleModel
				.getFechaCreacion());
		logCarteraDetalle
		.setFechaCartera(carteraDetalleModel.getFechaCartera());
		logCarteraDetalle.setFechaVencimiento(carteraDetalleModel
				.getFechaVencimiento());
		logCarteraDetalle.setFechaUltimaActualizacion(carteraDetalleModel
				.getFechaUltimaActualizacion());
		logCarteraDetalle.setValor(utilitariosLocal
				.redondeoValor(carteraDetalleModel.getValor()));
		logCarteraDetalle.setSaldo(utilitariosLocal
				.redondeoValor(carteraDetalleModel.getSaldo()));
		logCarteraDetalle.setCartera(carteraDetalleModel.getCartera());
		try {
			if (tipoLogCartera == TipoLogCartera.FACTURA)
				logCarteraDetalle.setLog("CARTERA_DETALLE_ID: "
						+ carteraDetalleModel.getId()
						+ ", DE LA CARTERA_ID: "
						+ dataCarteraId
						+ ", ELIMINADA POR ANULACION DE FACTURA # "
						+ numero
						+ ", REALIZADO POR: "
						+ usuario
						+ ", FECHA: "
						+ utilitariosLocal.getFechaUppercase(utilitariosLocal
								.dateHoy()));
			else if (tipoLogCartera == TipoLogCartera.COMPRA)
				logCarteraDetalle.setLog("CARTERA_DETALLE_ID: "
						+ carteraDetalleModel.getId()
						+ ", DE LA CARTERA_ID: "
						+ dataCarteraId
						+ ", ELIMINADA POR ANULACION DE COMPRA CON CÓDIGO # "
						+ numero
						+ ", REALIZADO POR: "
						+ usuario
						+ ", FECHA: "
						+ utilitariosLocal.getFechaUppercase(utilitariosLocal
								.dateHoy()));
			else if (tipoLogCartera == TipoLogCartera.RETENCION)
				logCarteraDetalle
				.setLog("CARTERA_DETALLE_ID: "
						+ carteraDetalleModel.getId()
						+ ", DE LA CARTERA_ID: "
						+ dataCarteraId
						+ ", ELIMINADA POR ANULACION DE RETENCION CON CÓDIGO # "
						+ numero
						+ ", REALIZADO POR: "
						+ usuario
						+ ", FECHA: "
						+ utilitariosLocal
						.getFechaUppercase(utilitariosLocal
								.dateHoy()));
			else if (tipoLogCartera == TipoLogCartera.FACTURA_ELIMINADA)
				logCarteraDetalle.setLog("CARTERA_DETALLE_ID: "
						+ carteraDetalleModel.getId()
						+ ", DE LA CARTERA_ID: "
						+ dataCarteraId
						+ ", ELIMINADA POR ELIMINACION DE FACTURA # "
						+ numero
						+ ", REALIZADO POR: "
						+ usuario
						+ ", FECHA: "
						+ utilitariosLocal.getFechaUppercase(utilitariosLocal
								.dateHoy()));
			else if (tipoLogCartera == TipoLogCartera.NOTA_CREDITO)
				logCarteraDetalle.setLog("CARTERA_DETALLE_ID: "
						+ carteraDetalleModel.getId()
						+ ", DE LA CARTERA_ID: "
						+ dataCarteraId
						+ ", POR ANULACION DE NOTA DE CREDITO # "
						+ numero
						+ ", REALIZADO POR: "
						+ usuario
						+ ", FECHA: "
						+ utilitariosLocal.getFechaUppercase(utilitariosLocal
								.dateHoy()));
			else
				throw new GenericBusinessException(
				"Tipo de Documento no soportado para registro de Log");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return logCarteraDetalle;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarFactura(FacturaIf factura, String usuario)
	throws GenericBusinessException {
		try {
			// Elimino asiento y asientos detalle de la factura
			Map aMapAsiento = new HashMap();
			aMapAsiento.put("oficinaId", factura.getOficinaId());
			aMapAsiento.put("tipoDocumentoId", factura.getTipodocumentoId());
			aMapAsiento.put("transaccionId", factura.getId());
			AsientoIf asiento = (AsientoIf) asientoLocal.findAsientoByQuery(
					aMapAsiento).iterator().next();
			String log = "ASIENTO_ID: " + asiento.getId()
			+ ", ELIMINADO POR ELIMINACION DE FACTURA REPETIDA, ID: "
			+ factura.getId();
			asientoLocal.procesarEliminacionAsiento(asiento, usuario, log, false);

			// Elimino cartera y carteras detalle de la factura
			Map aMapCartera = new HashMap();
			aMapCartera.put("oficinaId", factura.getOficinaId());
			aMapCartera.put("tipodocumentoId", factura.getTipodocumentoId());
			aMapCartera.put("referenciaId", factura.getId());
			CarteraIf cartera = (CarteraIf) findCarteraByQuery(aMapCartera)
			.iterator().next();
			CarteraEJB carteraEliminada = manager.find(CarteraEJB.class,
					cartera.getId());
			LogCarteraEJB logCartera = registrarLogCartera(cartera, usuario,
					TipoLogCartera.FACTURA_ELIMINADA, factura.getNumero()
					.toString());
			manager.persist(logCartera);
			List<CarteraDetalleIf> carteraDetalleList = (ArrayList<CarteraDetalleIf>) carteraDetalleLocal
			.findCarteraDetalleByCarteraId(cartera.getId());
			for (CarteraDetalleIf carteraDetalle : carteraDetalleList) {
				LogCarteraDetalleEJB logCarteraDetalle = registrarLogCarteraDetalle(
						carteraDetalle, usuario,
						TipoLogCartera.FACTURA_ELIMINADA, factura.getNumero()
						.toString(), carteraEliminada.getId());
				logCarteraDetalle.setLogCarteraId(logCartera.getPrimaryKey());
				manager.merge(logCarteraDetalle);
				CarteraDetalleEJB dataDetalle = manager.find(
						CarteraDetalleEJB.class, carteraDetalle.getId());
				manager.remove(dataDetalle);
			}
			manager.remove(carteraEliminada);

			PedidoIf pedido = pedidoLocal.getPedido(factura.getPedidoId());

			// Elimino factura y facturas detalle
			List<FacturaDetalleIf> facturaDetalleList = (ArrayList<FacturaDetalleIf>) facturaDetalleLocal
			.findFacturaDetalleByFacturaId(factura.getId());
			for (FacturaDetalleIf facturaDetalle : facturaDetalleList) {
				FacturaDetalleEJB dataDetalle = manager.find(
						FacturaDetalleEJB.class, facturaDetalle.getId());
				manager.remove(dataDetalle);
			}
			FacturaEJB facturaEliminada = manager.find(FacturaEJB.class,
					factura.getId());
			manager.remove(facturaEliminada);

			// Elimino pedidos y pedidos detalle
			List<PedidoDetalleIf> pedidoDetalleList = (ArrayList<PedidoDetalleIf>) pedidoDetalleLocal
			.findPedidoDetalleByPedidoId(pedido.getId());
			for (PedidoDetalleIf pedidoDetalle : pedidoDetalleList) {
				PedidoDetalleEJB dataDetalle = manager.find(
						PedidoDetalleEJB.class, pedidoDetalle.getId());
				manager.remove(dataDetalle);
			}
			PedidoEJB pedidoEliminado = manager.find(PedidoEJB.class, pedido
					.getId());
			manager.remove(pedidoEliminado);
			manager.flush();

		} catch (SaldoCuentaNegativoException e) {
			e.printStackTrace();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	/** ************************* COMPRAS ********************* */

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean actualizarCompra(CompraIf model,
			List<CompraDetalleIf> modelDetalleList,
			List<CompraDetalleIf> modelDetalleRemovidoList, long idTarea,
			Vector<CompraRetencionIf> listaRetenciones,
			Vector<CompraRetencionIf> listaRetencionesEliminadas,
			Long idEmpresa, Long idOficina, UsuarioIf usuario, Vector<OrderData> ordenesVector)
	throws GenericBusinessException {
		boolean compraActualizada = false;
		boolean compraEstadoL = false;

		try {
			CompraEJB compra = compraLocal.registrarCompra(model);
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("tipodocumentoId", model.getTipodocumentoId());
			mapa.put("referenciaId", model.getId());
			CarteraIf modelCartera = null;
			Iterator<OrdenAsociadaIf> ordenesAsociadasIterator = ordenAsociadaSessionLocal.findOrdenAsociadaByCompraId(compra.getId()).iterator();
			while (ordenesAsociadasIterator.hasNext()) {
				OrdenAsociadaIf ordenAsociada = ordenesAsociadasIterator.next();
				if (ordenAsociada.getTipoOrden().equals("OC")) {
					OrdenCompraIf ordenCompra = ordenCompraSessionLocal.getOrdenCompra(ordenAsociada.getOrdenId());
					ordenCompra.setEstado("E");
					manager.merge(ordenCompra);
				} else if (ordenAsociada.getTipoOrden().equals("OM")) {
					OrdenMedioIf ordenMedio = ordenMedioSessionLocal.getOrdenMedio(ordenAsociada.getOrdenId());
					ordenMedio.setEstado("E");
					manager.merge(ordenMedio);
				}
				manager.remove(ordenAsociada);
			}
			ClienteIf cliente = null;
			for (int i=0; i<ordenesVector.size(); i++) {
				OrderData orden = ordenesVector.get(i);
				OrdenAsociadaEJB ordenAsociada = new OrdenAsociadaEJB();
				ordenAsociada.setCompraId(compra.getPrimaryKey());
				ordenAsociada.setTipoOrden(orden.getOrderType());
				if (orden.getOrderType().equals("OC")) {
					ordenAsociada.setOrdenId(orden.getPurchaseOrder().getId());
					if (cliente == null) {
						SolicitudCompraIf solicitudCompra = solicitudCompraLocal.getSolicitudCompra(orden.getPurchaseOrder().getSolicitudcompraId());
						if (solicitudCompra.getTipoReferencia().equals("P")) {
							Map<String, Object> queryMap = new HashMap<String, Object>();
							queryMap.put("codigo", solicitudCompra.getReferencia());
							Iterator<PresupuestoIf> it = presupuestoLocal.findPresupuestoByQuery(queryMap, idEmpresa).iterator();
							if (it.hasNext()) {
								PresupuestoIf presupuesto = it.next();
								cliente = (ClienteIf) clienteLocal.findClienteByPresupuestoId(presupuesto.getId()).iterator().next();
							}
						} else if (solicitudCompra.getTipoReferencia().equals("M")) {
							Map<String, Object> queryMap = new HashMap<String, Object>();
							queryMap.put("codigo", solicitudCompra.getReferencia());
							Iterator<OrdenMedioIf> it = ordenMedioLocal.findOrdenMedioByQuery(queryMap, idEmpresa).iterator();
							if (it.hasNext()) {
								OrdenMedioIf ordenMedio = it.next();
								cliente = (ClienteIf) clienteLocal.findClienteByOrdenMedioId(ordenMedio.getId()).iterator().next();
							}
						}
					}
				} else {
					ordenAsociada.setOrdenId(orden.getMediaOrder().getId());
					if (cliente == null)
						cliente = (ClienteIf) clienteLocal.findClienteByOrdenMedioId(orden.getMediaOrder().getId()).iterator().next();
				}
				ordenAsociadaSessionLocal.addOrdenAsociada((OrdenAsociadaIf) ordenAsociada);
			}
			AsientoIf asiento = null;
			int year = compra.getFecha().getYear() + 1900;
			int month = compra.getFecha().getMonth() + 1;
			if ((year == 2008 && month >= 9) || (year > 2008)) {
				if (compra.getEstado() != null
						&& compra.getEstado().equalsIgnoreCase("L")) {
					compraAsientoAutomaticoHandlerLocal
					.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
					compraAsientoAutomaticoHandlerLocal.setIva(0D);
					compraAsientoAutomaticoHandlerLocal.setCtaxpag(0D);
					for (int i = 0; i < modelDetalleList.size(); i++) {
						CompraDetalleIf compraDetalle = (CompraDetalleIf) modelDetalleList
						.get(i);
						if (i != modelDetalleList.size() - 1)
							asiento = compraAsientoAutomaticoHandlerLocal.generarAsientoAutomatico(compra,
									compraDetalle, null, false, false,
									usuario, cliente);
						else if (i == modelDetalleList.size() - 1)
							asiento = compraAsientoAutomaticoHandlerLocal.generarAsientoAutomatico(compra,
									compraDetalle, null, true, false,
									usuario, cliente);
					}
				}
			}

			double valorComprasDetalle = 0.0;
			double valorCarteraDetalle = 0.0;
			if (compra.getEstado().equals("L")) {

				// SUMATORIA DEL DETALLE DE LA COMPRA
				for (CompraDetalleIf modelDetalle : modelDetalleList) {
					double valorCompra = modelDetalle.getValor().doubleValue();
					double ivaCompra = modelDetalle.getIva().doubleValue();
					double iceCompra = modelDetalle.getIce().doubleValue();
					double otrosImpuestosCompra = modelDetalle
					.getOtroImpuesto().doubleValue();
					double descuentoCompra = modelDetalle.getDescuento()
					.doubleValue();
					double valorCartera = valorCompra + ivaCompra + iceCompra
					+ otrosImpuestosCompra - descuentoCompra;
					valorComprasDetalle += valorCartera;
				}
				valorComprasDetalle = utilitariosLocal
				.redondeoValor(valorComprasDetalle);

				modelCartera = crearCartera(compra, modelDetalleList, idTarea,
						idEmpresa, idOficina);
				// SUMATORIA DEL DETALLE DE LA CARTERA
				Collection carteraDetalleColeccion = carteraDetalleLocal
				.findCarteraDetalleByCarteraId(modelCartera.getId());
				Iterator itCarteraDetalle = carteraDetalleColeccion.iterator();
				while (itCarteraDetalle.hasNext()) {
					CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) itCarteraDetalle
					.next();
					valorCarteraDetalle += carteraDetalleIf.getValor()
					.doubleValue();
				}
				valorCarteraDetalle = utilitariosLocal
				.redondeoValor(valorCarteraDetalle);
				compraEstadoL = true;
			}
			compra.setEstado("A");

			Collection carteras = findCarteraByQuery(mapa);
			if (carteras.size() > 0)
				modelCartera = (CarteraIf) carteras.iterator().next();

			if (modelCartera.getValor().compareTo(modelCartera.getSaldo()) == 0) {
				manager.merge(compra);

				for (CompraDetalleIf modelDetalle : modelDetalleList) {
					modelDetalle.setCompraId(compra.getPrimaryKey());
					CompraDetalleIf compraDetalle = compraLocal
					.registrarCompraDetalle(modelDetalle);
					manager.merge(compraDetalle);
				}

				for (CompraDetalleIf modelDetalleEliminado : modelDetalleRemovidoList) {
					CompraDetalleIf data = compraDetalleLocal
					.getCompraDetalle(modelDetalleEliminado.getId());
					manager.remove(data);
				}

				for (CompraRetencionIf modelRetencion : listaRetenciones) {
					modelRetencion.setCompraId(compra.getPrimaryKey());
					CompraRetencionEJB compraRetencion = compraLocal
					.registrarCompraRetencion(modelRetencion);
					manager.merge(compraRetencion);
				}

				for (CompraRetencionIf modelRetencionEliminada : listaRetencionesEliminadas) {
					CompraRetencionIf compraRetencion = compraRetencionLocal
					.getCompraRetencion(modelRetencionEliminada.getId());
					manager.remove(compraRetencion);
				}
				
				Double valorComprasDetalleRedondeoTotal = 0.0;
				Double valorComprasDetalleRedondeoParcial = 0.0;

				// CarteraEJB cartera = null;
				if (!compraEstadoL) {
					Map<String, Object> parameterMap = new HashMap<String, Object>();
					parameterMap.put("tipodocumentoId", compra
							.getTipodocumentoId());
					parameterMap.put("referenciaId", compra.getId());
					Iterator carteraIterator = findCarteraByQuery(parameterMap)
					.iterator();

					if (carteraIterator.hasNext()) {
						CarteraIf carteraTmp = (CarteraIf) carteraIterator
						.next();
						Collection carteraDetalleColeccion = carteraDetalleLocal
						.findCarteraDetalleByCarteraId(carteraTmp
								.getId());
						Iterator itCarteraDetalle = carteraDetalleColeccion
						.iterator();

						while (itCarteraDetalle.hasNext()) {
							CarteraDetalleIf carteraDetalleIf = (CarteraDetalleIf) itCarteraDetalle
							.next();
							manager.remove(carteraDetalleIf);
						}

						manager.remove(carteraTmp);
					}

					modelCartera = compraLocal.registrarCartera(compra, null,
							true, idOficina);
					manager.persist(modelCartera);

					Collection documentos = documentoLocal
					.findDocumentoByTipoDocumentoId(compra
							.getTipodocumentoId());
					Iterator itDocumentos = documentos.iterator();
					int count = 0;
					Double valorCompras = 0.0;
					Double valorComprasSinRedondeo = 0.0;

					while (itDocumentos.hasNext()) {
						DocumentoIf documento = (DocumentoIf) itDocumentos
						.next();
						Double valorCartera = 0.00;

						for (CompraDetalleIf modelDetalle : modelDetalleList) {
							if (modelDetalle.getDocumentoId().compareTo(
									documento.getId()) == 0
									&& documento.getBonificacion().equals("N")) {
								double cantidadCompra = modelDetalle
								.getCantidad().doubleValue();
								double valorCompra = modelDetalle.getValor()
								.doubleValue();
								double ivaCompra = modelDetalle.getIva()
								.doubleValue();
								double iceCompra = modelDetalle.getIce()
								.doubleValue();
								double otrosImpuestosCompra = modelDetalle
								.getOtroImpuesto().doubleValue();
								double descuentoCompra = modelDetalle
								.getDescuento().doubleValue();
								valorCartera = (cantidadCompra * valorCompra)
								+ ivaCompra + iceCompra
								+ otrosImpuestosCompra
								- descuentoCompra;
								valorCompras += utilitariosLocal
								.redondeoValor(valorCartera);
								valorComprasSinRedondeo += valorCartera;
							}
						}

						if (valorCompras > 0.0 && valorCartera > 0.0
								&& valorComprasSinRedondeo > 0.0) {
							CarteraDetalleData modelCarteraDetalle = new CarteraDetalleData();
							modelCarteraDetalle.setCarteraId(modelCartera
									.getPrimaryKey());
							modelCarteraDetalle.setDocumentoId(documento
									.getId());
							// carteraDetalle.setLineaId(lineaIf.getId());
							modelCarteraDetalle.setPreimpreso(compra
									.getPreimpreso());
							java.util.Date fechaHoy = new java.util.Date();
							modelCarteraDetalle
							.setFechaCreacion(new java.sql.Date(
									fechaHoy.getYear(), fechaHoy
									.getMonth(), fechaHoy
									.getDate()));
							modelCarteraDetalle
							.setFechaCartera(new java.sql.Date(fechaHoy
									.getYear(), fechaHoy.getMonth(),
									fechaHoy.getDate()));
							modelCarteraDetalle.setFechaVencimiento(compra
									.getFechaVencimiento());
							modelCarteraDetalle
							.setFechaUltimaActualizacion(new java.sql.Date(
									fechaHoy.getYear(), fechaHoy
									.getMonth(), fechaHoy
									.getDate()));
							/*
							 * modelCarteraDetalle.setValor(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorCompras)));
							 * modelCarteraDetalle.setSaldo(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorCompras)));
							 */
							modelCarteraDetalle.setValor(utilitariosLocal
									.redondeoValor(BigDecimal
											.valueOf(valorCartera)));
							modelCarteraDetalle.setSaldo(utilitariosLocal
									.redondeoValor(BigDecimal
											.valueOf(valorCartera)));
							modelCarteraDetalle.setCartera("S");
							modelCarteraDetalle.setAutorizacion(compra
									.getAutorizacion());
							modelCarteraDetalle
							.setSriSustentoTributarioId(compra
									.getIdSriSustentoTributario());
							modelCarteraDetalle.setSecuencial(++count);
							CarteraDetalleEJB carteraDetalle = registrarCarteraDetalle(
									modelCarteraDetalle, true);
							// valorComprasDetalleRedondeoTotal =
							// modelCarteraDetalle.getValor().doubleValue();
							valorComprasDetalleRedondeoTotal = modelCartera
							.getValor().doubleValue();
							valorComprasDetalleRedondeoParcial = utilitariosLocal
							.redondeoValor(valorComprasSinRedondeo);
							manager.merge(carteraDetalle);
						}
					}
				}

				HashMap<String, String> parametros = new HashMap<String, String>();
				parametros.put("tipoDocumentoActual", String.valueOf(model
						.getTipodocumentoId()));
				parametros.put("referenciaActual", model.getObservacion());

				/*if (idTarea != 0L)
					procesoPrincipalCompraLocal.actualizarParametrosProceso(
							idTarea, "", parametros);
				else {
					if (compra.getId() != null)
						procesoPrincipalCompraLocal
						.actualizarParametrosProceso(compra.getId(),
								"idCompra", parametros);
					else
						throw new GenericBusinessException(
						"No hay Id de Solicitud de Compra para actualizacion en BPM");
				}*/

				// VERIFICACION DE VALORES
				/*
				 * double totalCompra = utilitariosLocal.redondeoValor(
				 * compra.getValor().doubleValue() +
				 * compra.getIva().doubleValue() + compra.getIce().doubleValue() +
				 * compra.getOtroImpuesto().doubleValue() -
				 * compra.getDescuento().doubleValue() ); if ( totalCompra !=
				 * valorComprasDetalle ) throw new
				 * GenericBusinessException("Error al guardar la compra, el
				 * valor de la Compra no coincide con el Detalle");
				 * 
				 * double valorCartera = utilitariosLocal.redondeoValor(
				 * modelCartera.getValor().doubleValue() ); if ( valorCartera !=
				 * valorComprasDetalle ) throw new
				 * GenericBusinessException("Error al guardar la compra, el
				 * valor de la Cartera no coincide con los valores del Detalle
				 * de la Compra");
				 * 
				 * if ( valorCartera != valorCarteraDetalle ){ throw new
				 * GenericBusinessException("Error al guardar la compra, el
				 * valor de la Cartera no coincide con los valores de su
				 * Detalle"); }
				 */

				double totalCompra = utilitariosLocal.redondeoValor(compra
						.getValor().doubleValue()
						+ compra.getIva().doubleValue()
						+ compra.getIce().doubleValue()
						+ compra.getOtroImpuesto().doubleValue()
						- compra.getDescuento().doubleValue());
				if (totalCompra != valorComprasDetalleRedondeoTotal
						&& totalCompra != valorComprasDetalleRedondeoParcial)
					throw new GenericBusinessException(
					"Error al guardar la compra, el valor de la Compra no coincide con el Detalle");

				if (modelCartera.getValor().doubleValue() != valorComprasDetalleRedondeoTotal
						&& modelCartera.getValor().doubleValue() != valorComprasDetalleRedondeoParcial)
					throw new GenericBusinessException(
					"Error al guardar la compra, el valor de la Cartera no coincide con los valores del Detalle de la Compra");

				/*
				 * if ( asiento != null ){ double debe = 0.0; double haber =
				 * 0.0; Collection<AsientoDetalleIf> asientosDetalle =
				 * asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getPrimaryKey());
				 * for ( Iterator itAsientos = asientosDetalle.iterator() ;
				 * itAsientos.hasNext() ; ){ AsientoDetalleIf asientoDetalle =
				 * (AsientoDetalleIf) itAsientos.next(); debe +=
				 * asientoDetalle.getDebe().doubleValue(); haber +=
				 * asientoDetalle.getHaber().doubleValue(); } debe =
				 * utilitariosLocal.redondeoValor(debe); haber =
				 * utilitariosLocal.redondeoValor(haber); if ( debe != haber )
				 * throw new GenericBusinessException("Error al guardar la
				 * compra, el valor del Debe y Haber no coinciden en el
				 * Asiento"); else if ( debe != valorComprasDetalle ) throw new
				 * GenericBusinessException("Error al guardar la compra, el
				 * valor del Debe y Haber no coinciden con el detalle de la
				 * Compra"); } else if ((year == 2008 && month >= 9) || (year >
				 * 2008)) throw new GenericBusinessException("No se gener\u00f3
				 * el asiento de la Compra");
				 */
				if (asiento != null) {
					double debe = 0.0;
					double haber = 0.0;
					Collection<AsientoDetalleIf> asientosDetalle = asientoDetalleLocal
					.findAsientoDetalleByAsientoId(asiento
							.getPrimaryKey());
					for (Iterator itAsientos = asientosDetalle.iterator(); itAsientos
					.hasNext();) {
						AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) itAsientos
						.next();
						debe += asientoDetalle.getDebe().doubleValue();
						haber += asientoDetalle.getHaber().doubleValue();
					}
					debe = utilitariosLocal.redondeoValor(debe);
					haber = utilitariosLocal.redondeoValor(haber);
					if (debe != haber)
						throw new GenericBusinessException(
								"Error al guardar la compra, el valor del Debe y Haber no coinciden en el Asiento");
					else if (debe != valorComprasDetalleRedondeoTotal
							&& debe != valorComprasDetalleRedondeoParcial)
						throw new GenericBusinessException(
						"Error al guardar la compra, el valor del Debe y Haber no coinciden con el detalle de la Compra");
				} else {
					year = compra.getFecha().getYear() + 1900;
					month = compra.getFecha().getMonth() + 1;
					TipoDocumentoIf tipoDocumentoCompra = (TipoDocumentoIf) tipoDocumentoLocal
					.getTipoDocumento(compra.getTipodocumentoId());
					if (((year == 2008 && month >= 9) || (year > 2008))
							&& (!tipoDocumentoCompra.getCodigo().equals("GCI")))
						throw new GenericBusinessException(
						"No se gener\u00f3 el asiento de la Compra");
				}

				compraActualizada = true;
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException(
			"Se ha producido un error al actualizar la Compra");
		}

		return compraActualizada;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean eliminarCompra(Long compraId)
	throws GenericBusinessException {

		boolean compraEliminada = false;

		try {
			CompraEJB data = manager.find(CompraEJB.class, compraId);

			CarteraIf modelCartera = (CarteraIf) findCarteraByReferenciaId(
					compraId).iterator().next();

			if (modelCartera.getValor().compareTo(modelCartera.getSaldo()) == 0) {

				Collection<CarteraDetalleIf> modelCarteraDetalleList = carteraDetalleLocal
				.findCarteraDetalleByCarteraId(modelCartera.getId());
				for (CarteraDetalleIf modelCarteraDetalle : modelCarteraDetalleList) {
					manager.remove(modelCarteraDetalle);
				}

				manager.remove(modelCartera);

				Collection<CompraDetalleIf> modelDetalleList = compraDetalleLocal
				.findCompraDetalleByCompraId(compraId);
				for (CompraDetalleIf modelDetalle : modelDetalleList) {
					manager.remove(modelDetalle);
				}

				Collection<CompraRetencionIf> modelRetencionList = compraRetencionLocal
				.findCompraRetencionByCompraId(compraId);
				for (CompraRetencionIf modelRetencion : modelRetencionList) {
					manager.remove(modelRetencion);
				}

				manager.remove(data);
				manager.flush();

				compraEliminada = true;
			}

		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al eliminar información en CompraEJB.", e);
			throw new GenericBusinessException(
			"Error al eliminar información en Compra");
		}

		return compraEliminada;
	}

	public void procesarAnularCompra(CompraIf compraIf, String usuario)
	throws GenericBusinessException {
		try {
			compraLocal.saveCompra(compraIf);

			// PARTE DE LA CARTERA
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("tipodocumentoId", compraIf.getTipodocumentoId());
			mapa.put("referenciaId", compraIf.getId());

			Collection<CarteraIf> carteras = findCarteraByQuery(mapa);
			if (carteras.size() == 0)
				throw new GenericBusinessException(
				"Compra no tiene cartera asociada !!");
			else if (carteras.size() > 1)
				throw new GenericBusinessException(
				"Compra tiene más de una cartera asociada !!");

			CarteraIf carteraIf = carteras.iterator().next();
			if (!utilitariosLocal.redondeoValor(carteraIf.getValor()).equals(
					utilitariosLocal.redondeoValor(carteraIf.getSaldo())))
				throw new GenericBusinessException(
				"Compra ya ha sido pagada total o parcialmente, no se la puede anular !!");
			LogCarteraEJB logCartera = registrarLogCartera(carteraIf, usuario,
					TipoLogCartera.COMPRA, compraIf.getCodigo());
			LogCarteraLocal.addLogCartera(logCartera);
			Collection<CarteraDetalleIf> detalles = carteraDetalleLocal
			.findCarteraDetalleByCarteraId(carteraIf.getId());
			for (CarteraDetalleIf carteraDetalleIf : detalles) {
				LogCarteraDetalleEJB logCarteraDetalle = registrarLogCarteraDetalle(
						carteraDetalleIf, usuario, TipoLogCartera.COMPRA,
						compraIf.getCodigo(), carteraIf.getId());
				logCarteraDetalle.setLogCarteraId(logCartera.getPrimaryKey());
				LogCarteraDetalleLocal.addLogCarteraDetalle(logCarteraDetalle);
				carteraDetalleLocal.deleteCarteraDetalle(carteraDetalleIf
						.getId());
			}
			deleteCartera(carteraIf.getId());

			// PARTE DE LOS ASIENTOS
			mapa.clear();
			mapa.put("tipoDocumentoId", compraIf.getTipodocumentoId());
			mapa.put("transaccionId", compraIf.getId());
			Collection<AsientoIf> asientos = asientoLocal
			.findAsientoByQuery(mapa);
			if (asientos.size() == 0)
				throw new GenericBusinessException(
						"Compra no tiene asiento asociado !!");
			else if (asientos.size() > 1)
				throw new GenericBusinessException(
				"Compra tiene más de una cabecera de asiento asociados !!");
			for (AsientoIf asiento : asientos) {
				String log = "ASIENTO_ID: " + asiento.getId()
				+ ", ELIMINADO POR ANULACIÓN DE COMPRA CON CÓDIGO # "
				+ compraIf.getCodigo();
				asientoLocal.procesarEliminacionAsiento(asiento, usuario, log, false);
			}

		} catch (GenericBusinessException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(
			"Error general en la anulación de la compra !!");
		}
	}

	public void procesarAnularRetencion(CompraIf compraIf, String usuario)
	throws GenericBusinessException {
		try {
			// PARTE DE LA CARTERA
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("tipodocumentoId", compraIf.getTipodocumentoId());
			mapa.put("referenciaId", compraIf.getId());

			Collection<CarteraIf> carteras = findCarteraByQuery(mapa);
			if (carteras.size() == 0)
				throw new GenericBusinessException(
				"Compra no tiene cartera asociada !!");
			else if (carteras.size() > 1)
				throw new GenericBusinessException(
				"Compra tiene más de una cartera asociada !!");

			Iterator it = carteras.iterator();
			CarteraIf carteraCompra = (CarteraIf) it.next();
			it = findCarteraRetencionByCompra(compraIf).iterator();

			while (it.hasNext()) {
				CarteraIf carteraRetencion = (CarteraIf) it.next();
				LogCarteraEJB logCartera = registrarLogCartera(
						carteraRetencion, usuario, TipoLogCartera.RETENCION,
						carteraRetencion.getCodigo());
				LogCarteraIf logCarteraRetencion = LogCarteraLocal
				.addLogCartera(logCartera);
				Collection<CarteraDetalleIf> detalles = carteraDetalleLocal
				.findCarteraDetalleByCarteraId(carteraRetencion.getId());
				for (CarteraDetalleIf carteraDetalleRetencion : detalles) {
					LogCarteraDetalleEJB logCarteraDetalle = registrarLogCarteraDetalle(
							carteraDetalleRetencion, usuario,
							TipoLogCartera.RETENCION, carteraRetencion
							.getCodigo(), carteraRetencion.getId());
					logCarteraDetalle.setLogCarteraId(logCarteraRetencion
							.getPrimaryKey());
					LogCarteraDetalleLocal
					.addLogCarteraDetalle(logCarteraDetalle);
					Iterator carteraAfectaIterator = carteraAfectaLocal
					.findCarteraAfectaByCarteradetalleId(
							carteraDetalleRetencion.getId()).iterator();
					while (carteraAfectaIterator.hasNext()) {
						CarteraAfectaIf carteraAfecta = (CarteraAfectaIf) carteraAfectaIterator
						.next();
						CarteraDetalleIf carteraDetalleCompra = (CarteraDetalleIf) carteraDetalleLocal
						.getCarteraDetalle(carteraAfecta
								.getCarteraafectaId());
						double saldo = carteraDetalleCompra.getSaldo()
						.doubleValue();
						carteraDetalleCompra.setSaldo(BigDecimal.valueOf(saldo
								+ carteraAfecta.getValor().doubleValue()));
						saldo = carteraCompra.getSaldo().doubleValue();
						carteraCompra.setSaldo(BigDecimal.valueOf(saldo
								+ carteraAfecta.getValor().doubleValue()));
						carteraAfectaLocal.deleteCarteraAfecta(carteraAfecta
								.getId());
					}
					carteraDetalleLocal
					.deleteCarteraDetalle(carteraDetalleRetencion
							.getId());
				}
				deleteCartera(carteraRetencion.getId());

				// PARTE DE LOS ASIENTOS
				mapa.clear();
				mapa.put("tipoDocumentoId", carteraRetencion
						.getTipodocumentoId());
				mapa.put("transaccionId", carteraRetencion.getId());
				Collection<AsientoIf> asientos = asientoLocal
				.findAsientoByQuery(mapa);
				if (asientos.size() == 0)
					throw new GenericBusinessException(
							"Retención no tiene asiento asociado !!");
				else if (asientos.size() > 1)
					throw new GenericBusinessException(
					"Retención tiene más de una cabecera de asiento asociados !!");
				for (AsientoIf asiento : asientos) {
					String log = "ASIENTO_ID: " + asiento.getId()
					+ ", ELIMINADO POR ANULACIÓN DE RETENCIÓN";
					asientoLocal.procesarEliminacionAsiento(asiento, usuario,
							log, false);
				}
			}

			it = compraRetencionLocal.findCompraRetencionByCompraId(
					compraIf.getId()).iterator();
			while (it.hasNext()) {
				CompraRetencionIf compraRetencion = (CompraRetencionIf) it
				.next();
				LogCompraRetencionEJB logCompraRetencion = registrarLogCompraRetencion(
						compraRetencion, usuario);
				LogCompraRetencionLocal
				.addLogCompraRetencion(logCompraRetencion);
				compraRetencionLocal.deleteCompraRetencion(compraRetencion
						.getId());
			}
		} catch (GenericBusinessException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(
			"Error general en la anulación de la retención !!");
		}
	}

	private Collection findCarteraRetencionByCompra(CompraIf compra)
	throws GenericBusinessException {
		// select c.* from cartera c, cartera_detalle cd, tipo_documento td
		// where cd.CARTERA_ID = c.ID and c.TIPODOCUMENTO_ID = td.ID and
		// td.CODIGO = 'CRE' and cd.ID in (select caraf.CARTERADETALLE_ID from
		// cartera_afecta caraf where caraf.CARTERAAFECTA_ID in (select
		// cardet.ID from cartera car, cartera_detalle cardet where
		// cardet.CARTERA_ID = car.ID and (car.REFERENCIA_ID = 4311 and
		// car.TIPODOCUMENTO_ID = 3)))
		try {
			String queryString = "select distinct c from CarteraEJB c, CarteraDetalleEJB cd, TipoDocumentoEJB td where cd.carteraId = c.id and c.tipodocumentoId = td.id and td.codigo = 'CRE'"
				+ " and cd.id in (select ca.carteradetalleId from CarteraAfectaEJB ca where ca.carteraafectaId in (select cardet.id from CarteraEJB car, CarteraDetalleEJB cardet where cardet.carteraId = car.id"
				+ " and car.referenciaId = "
				+ compra.getId()
				+ " and car.tipodocumentoId = "
				+ compra.getTipodocumentoId() + "))";
			Query query = manager.createQuery(queryString);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al consultar carteras");
		}
	}

	private CarteraEJB crearCartera(CompraEJB compra,
			List<CompraDetalleIf> modelDetalleList, long idTarea,
			Long idEmpresa, Long idOficina) throws GenericBusinessException,
			ComprasBpmException {
		CarteraEJB cartera = compraLocal.registrarCartera(compra, null, true,
				idOficina);
		manager.persist(cartera);

		NumeradoresIf numerador = (NumeradoresIf) numeradoresLocal
		.findNumeradoresByNombreTablaAndByEmpresaId("CARTERA",
				idEmpresa).iterator().next();
		int numeroCartera = numerador.getUltimoValor().intValue() + 1;
		numerador.setUltimoValor(new BigDecimal(numeroCartera));
		manager.merge(numerador);

		Collection documentos = documentoLocal
		.findDocumentoByTipoDocumentoId(compra.getTipodocumentoId());
		Iterator itDocumentos = documentos.iterator();
		int count = 0;

		while (itDocumentos.hasNext()) {
			DocumentoIf documento = (DocumentoIf) itDocumentos.next();
			Double valorCompras = 0.00;

			for (CompraDetalleIf modelDetalle : modelDetalleList) {
				if (modelDetalle.getDocumentoId().compareTo(documento.getId()) == 0
						&& documento.getBonificacion().equals("N")) {
					double cantidadCompra = modelDetalle.getCantidad()
					.doubleValue();
					double valorCompra = modelDetalle.getValor().doubleValue();
					double ivaCompra = modelDetalle.getIva().doubleValue();
					double iceCompra = modelDetalle.getIce().doubleValue();
					double otrosImpuestosCompra = modelDetalle
					.getOtroImpuesto().doubleValue();
					double descuentoCompra = modelDetalle.getDescuento()
					.doubleValue();
					double valorCartera = (cantidadCompra * valorCompra)
					+ ivaCompra + iceCompra + otrosImpuestosCompra
					- descuentoCompra;
					valorCompras += valorCartera;
				}
			}

			if (valorCompras > 0.0) {
				CarteraDetalleData modelCarteraDetalle = new CarteraDetalleData();
				modelCarteraDetalle.setCarteraId(cartera.getPrimaryKey());
				modelCarteraDetalle.setDocumentoId(documento.getId());
				// carteraDetalle.setLineaId(lineaIf.getId());
				modelCarteraDetalle.setPreimpreso(compra.getPreimpreso());
				modelCarteraDetalle.setFechaCreacion(compra.getFecha());
				modelCarteraDetalle.setFechaCartera(compra.getFecha());
				modelCarteraDetalle.setFechaVencimiento(compra
						.getFechaVencimiento());
				modelCarteraDetalle.setFechaUltimaActualizacion(compra.getFecha());
				modelCarteraDetalle.setValor(utilitariosLocal
						.redondeoValor(BigDecimal.valueOf(valorCompras)));
				modelCarteraDetalle.setSaldo(utilitariosLocal
						.redondeoValor(BigDecimal.valueOf(valorCompras)));
				modelCarteraDetalle.setCartera("S");
				modelCarteraDetalle.setAutorizacion(compra.getAutorizacion());
				modelCarteraDetalle.setSriSustentoTributarioId(compra
						.getIdSriSustentoTributario());
				modelCarteraDetalle.setSecuencial(++count);
				CarteraDetalleEJB carteraDetalle = registrarCarteraDetalle(
						modelCarteraDetalle, true);
				manager.merge(carteraDetalle);
			}
		}
		return cartera;
	}

	/*public Collection findCuentasPorPagar(Long idEmpresa, Long idProveedor,
			Long idTipoProveedor, Date fechaInicial, Date fechaFinal)
			throws GenericBusinessException {
		// select distinct cl.ID as ID_CLIENTE, cl.RAZON_SOCIAL,
		// cl.IDENTIFICACION, co.PREIMPRESO, c.VALOR, co.RETENCION, c.SALDO,
		// co.FECHA, tp.NOMBRE as TIPO_PROVEEDOR
		// from cartera c, compra co, cliente cl, cliente_oficina clo,
		// tipo_proveedor tp, tipo_documento td
		// where c.REFERENCIA_ID = co.ID and c.CLIENTEOFICINA_ID = clo.ID and
		// clo.CLIENTE_ID = cl.ID and cl.TIPOPROVEEDOR_ID = tp.ID and
		// c.TIPODOCUMENTO_ID = td.ID and td.EMPRESA_ID = 1 and c.SALDO > 0.0
		// and c.CODIGO like 'CRE-COM-%' and co.FECHA <=
		// TO_Date('2008-01-31','YYYY-MM-DD') and cl.ID = 542
		// order by TIPO_PROVEEDOR asc, cl.RAZON_SOCIAL asc
		EmpresaIf empresa = empresaLocal.getEmpresa(idEmpresa);
		String codigo = empresa.getCodigo() + "-%COM-%";
		String selectPart = "select distinct c, co, cl, tp";
		String fromPart = "from CarteraEJB c, CompraEJB co, ClienteEJB cl, ClienteOficinaEJB clo, TipoProveedorEJB tp, TipoDocumentoEJB td";
		String wherePart = "where c.referenciaId = co.id and c.clienteoficinaId = clo.id and clo.clienteId = cl.id and cl.tipoproveedorId = tp.id and c.tipodocumentoId = td.id and td.empresaId = "
				+ idEmpresa
				+ " and c.codigo like '"
				+ codigo
				+ "' and c.estado = 'N'";

		if (fechaInicial != null)
			wherePart += " and co.fecha >= :fechaInicial";
		if (fechaFinal != null)
			wherePart += " and co.fecha <= :fechaFinal";

		if (idProveedor != 0L)
			wherePart += " and cl.id = " + idProveedor;

		if (idTipoProveedor != 0L)
			wherePart += " and tp.id = " + idTipoProveedor;

		String orderByPart = "order by tp.nombre asc, cl.razonSocial asc, co.proveedorId asc, co.fecha asc, co.preimpreso asc";
		String queryString = selectPart + " " + fromPart + " " + wherePart
				+ " " + orderByPart;

		Query query = manager.createQuery(queryString);
		if (fechaInicial != null)
			query.setParameter("fechaInicial", fechaInicial);
		if (fechaFinal != null)
			query.setParameter("fechaFinal", fechaFinal);
		return query.getResultList();
	}*/

	public Collection findCuentasPorPagar(Long idEmpresa, Long idProveedor, Long idTipoProveedor, Date fechaInicial, Date fechaFinal, boolean orderByTipoProveedor)
	throws GenericBusinessException {
		// select distinct cl.ID as ID_CLIENTE, cl.RAZON_SOCIAL,
		// cl.IDENTIFICACION, co.PREIMPRESO, c.VALOR, co.RETENCION, c.SALDO,
		// co.FECHA, tp.NOMBRE as TIPO_PROVEEDOR
		// from cartera c, compra co, cliente cl, cliente_oficina clo,
		// tipo_proveedor tp, tipo_documento td
		// where c.REFERENCIA_ID = co.ID and c.CLIENTEOFICINA_ID = clo.ID and
		// clo.CLIENTE_ID = cl.ID and cl.TIPOPROVEEDOR_ID = tp.ID and
		// c.TIPODOCUMENTO_ID = td.ID and td.EMPRESA_ID = 1 and c.SALDO > 0.0
		// and c.CODIGO like 'CRE-COM-%' and co.FECHA <=
		// TO_Date('2008-01-31','YYYY-MM-DD') and cl.ID = 542
		// order by TIPO_PROVEEDOR asc, cl.RAZON_SOCIAL asc
		EmpresaIf empresa = empresaLocal.getEmpresa(idEmpresa);
		String codigo = empresa.getCodigo() + "-%COM-%";
		String selectPart = "select distinct cpp";
		String fromPart = "from CuentasPorPagarEJB cpp, TipoDocumentoEJB td";
		String wherePart = "where cpp.tipodocumentoId = td.id and td.empresaId = "
			+ idEmpresa
			+ " and cpp.codigo like '"
			+ codigo
			+ "'";

		if (fechaInicial != null)
			wherePart += " and cpp.fechaEmision >= :fechaInicial";
		if (fechaFinal != null)
			wherePart += " and cpp.fechaEmision <= :fechaFinal";

		if (idProveedor != 0L)
			wherePart += " and cpp.proveedorId = " + idProveedor;

		if (idTipoProveedor != 0L)
			wherePart += " and cpp.tipoProveedorId = " + idTipoProveedor;

		String orderByPart = (orderByTipoProveedor)?"order by cpp.tipoProveedor asc, cpp.razonSocial asc, cpp.proveedorId asc, cpp.fechaCompra asc, cpp.preimpreso asc":
			"order by cpp.razonSocial asc, cpp.proveedorId asc, cpp.fechaEmision asc, cpp.preimpreso asc";
		String queryString = selectPart + " " + fromPart + " " + wherePart
		+ " " + orderByPart;

		Query query = manager.createQuery(queryString);
		if (fechaInicial != null)
			query.setParameter("fechaInicial", fechaInicial);
		if (fechaFinal != null)
			query.setParameter("fechaFinal", fechaFinal);
		return query.getResultList();
	}

	public Collection findCuentasPorPagar(Long idEmpresa, Long idProveedor, Long idTipoProveedor, Date fechaCorte, boolean orderByTipoProveedor)
	throws GenericBusinessException {
		// select distinct cl.ID as ID_CLIENTE, cl.RAZON_SOCIAL,
		// cl.IDENTIFICACION, co.PREIMPRESO, c.VALOR, co.RETENCION, c.SALDO,
		// co.FECHA, tp.NOMBRE as TIPO_PROVEEDOR
		// from cartera c, compra co, cliente cl, cliente_oficina clo,
		// tipo_proveedor tp, tipo_documento td
		// where c.REFERENCIA_ID = co.ID and c.CLIENTEOFICINA_ID = clo.ID and
		// clo.CLIENTE_ID = cl.ID and cl.TIPOPROVEEDOR_ID = tp.ID and
		// c.TIPODOCUMENTO_ID = td.ID and td.EMPRESA_ID = 1 and c.SALDO > 0.0
		// and c.CODIGO like 'CRE-COM-%' and co.FECHA <=
		// TO_Date('2008-01-31','YYYY-MM-DD') and cl.ID = 542
		// order by TIPO_PROVEEDOR asc, cl.RAZON_SOCIAL asc
		EmpresaIf empresa = empresaLocal.getEmpresa(idEmpresa);
		String codigoCompra = empresa.getCodigo() + "-%COM-%";
		String codigoNotaInternaDebito = empresa.getCodigo() + "-%NID-%";
		String selectPart = "select distinct cpp";
		String fromPart = "from CuentasPorPagarEJB cpp, TipoDocumentoEJB td";
		String wherePart = "where cpp.tipodocumentoId = td.id and td.empresaId = "
			+ idEmpresa
			+ " and (cpp.codigo like '"
			+ codigoCompra 
			+ "' or cpp.codigo like '"
			+ codigoNotaInternaDebito
			+"')";

		if (fechaCorte != null)
			wherePart += " and cpp.fechaEmision <= :fechaCorte";

		if (idProveedor != 0L)
			wherePart += " and cpp.proveedorId = " + idProveedor;

		if (idTipoProveedor != 0L)
			wherePart += " and cpp.tipoProveedorId = " + idTipoProveedor;

		String orderByPart = (orderByTipoProveedor)?"order by cpp.tipoProveedor asc, cpp.razonSocial asc, cpp.proveedorId asc, cpp.fechaCompra asc, cpp.preimpreso asc":
			"order by cpp.razonSocial asc, cpp.proveedorId asc, cpp.fechaEmision asc, cpp.preimpreso asc";
		String queryString = selectPart + " " + fromPart + " " + wherePart
		+ " " + orderByPart;

		Query query = manager.createQuery(queryString);
		if (fechaCorte != null)
			query.setParameter("fechaCorte", fechaCorte);
		return query.getResultList();
	}

	public Collection findCuentasPorPagaryCartera(Long idEmpresa, Long idProveedor, Long idTipoProveedor, Date fechaCorte, boolean orderByTipoProveedor)
	throws GenericBusinessException {
		EmpresaIf empresa = empresaLocal.getEmpresa(idEmpresa);
		String codigo = empresa.getCodigo() + "-%COM-%";
		String selectPart = "select distinct cpp, c";
		String fromPart = "from CuentasPorPagarEJB cpp, TipoDocumentoEJB td, CarteraEJB c";
		String wherePart = "where cpp.carteraId = c.id and cpp.tipodocumentoId = td.id and td.empresaId = "
			+ idEmpresa
			+ " and cpp.codigo like '"
			+ codigo
			+ "'";

		if (fechaCorte != null)
			wherePart += " and cpp.fechaEmision <= :fechaCorte";

		if (idProveedor != 0L)
			wherePart += " and cpp.proveedorId = " + idProveedor;

		if (idTipoProveedor != 0L)
			wherePart += " and cpp.tipoProveedorId = " + idTipoProveedor;

		String orderByPart = (orderByTipoProveedor)?"order by cpp.tipoProveedor asc, cpp.razonSocial asc, cpp.proveedorId asc, cpp.fechaCompra asc, cpp.preimpreso asc":
			"order by cpp.razonSocial asc, cpp.proveedorId asc, cpp.fechaEmision asc, cpp.preimpreso asc";
		String queryString = selectPart + " " + fromPart + " " + wherePart
		+ " " + orderByPart;

		Query query = manager.createQuery(queryString);
		if (fechaCorte != null)
			query.setParameter("fechaCorte", fechaCorte);
		return query.getResultList();
	}

	public Collection findCuentasPorPagarAdicionales(Long idEmpresa,
			Long idProveedor, Long idTipoProveedor, Long idModulo,
			Date fechaInicial, Date fechaFinal) throws GenericBusinessException {
		String selectPart = "select distinct c, cl, tp";
		String fromPart = "from CarteraEJB c, ClienteEJB cl, ClienteOficinaEJB clo, TipoProveedorEJB tp, TipoDocumentoEJB td";
		String wherePart = "where c.clienteoficinaId = clo.id and clo.clienteId = cl.id and cl.tipoproveedorId = tp.id and c.tipodocumentoId = td.id and td.empresaId = "
			+ idEmpresa
			+ " and td.signocartera = '+' and td.tipocartera = 'P' and td.moduloId = "
			+ idModulo + " and c.estado = 'N'";

		if (fechaInicial != null)
			wherePart += " and c.fechaEmision >= :fechaInicial";
		if (fechaFinal != null)
			wherePart += " and c.fechaEmision <= :fechaFinal";

		if (idProveedor != 0L)
			wherePart += " and cl.id = " + idProveedor;

		if (idTipoProveedor != 0L)
			wherePart += " and tp.id = " + idTipoProveedor;

		String orderByPart = "order by tp.nombre asc, cl.razonSocial asc, c.fechaEmision asc, c.codigo asc";
		String queryString = selectPart + " " + fromPart + " " + wherePart
		+ " " + orderByPart;

		Query query = manager.createQuery(queryString);
		if (fechaInicial != null)
			query.setParameter("fechaInicial", fechaInicial);
		if (fechaFinal != null)
			query.setParameter("fechaFinal", fechaFinal);
		return query.getResultList();
	}

	/*public Collection findCuentasPorCobrar(Long idEmpresa, Long idCliente, Date fechaInicial, Date fechaFinal) throws GenericBusinessException {
		//select distinct c.*, f.*, cl.* from CARTERA c LEFT OUTER JOIN FACTURA f ON c.REFERENCIA_ID = f.ID, CLIENTE cl, CLIENTE_OFICINA clo, TIPO_DOCUMENTO td where c.CODIGO like 'AXS-%FAC-%' and c.CLIENTEOFICINA_ID = clo.ID and clo.CLIENTE_ID = cl.ID and c.TIPODOCUMENTO_ID = td.ID and td.EMPRESA_ID = 40 and c.CODIGO like 'AXS-%FAC-%' and c.ESTADO = 'N'
		EmpresaIf empresa = empresaLocal.getEmpresa(idEmpresa);
		String codigo = empresa.getCodigo() + "-%FAC-%";
		String selectPart = "select distinct c, f, cl";
		String fromPart = "from CarteraEJB c left outer join fetch FacturaEJB f on c.referenciaId = f.id, ClienteEJB cl, ClienteOficinaEJB clo, TipoDocumentoEJB td";
		String wherePart = "where c.clienteoficinaId = clo.id and clo.clienteId = cl.id and c.tipodocumentoId = td.id and td.empresaId = "
				+ idEmpresa
				+ " and c.codigo like '"
				+ codigo
				+ "' and c.estado = 'N'";

		if (fechaInicial != null)
			wherePart += " and (f.fechaFactura >= :fechaInicial)";
		if (fechaFinal != null)
			wherePart += " and (f.fechaFactura <= :fechaFinal)";

		if (idCliente != 0L)
			wherePart += " and cl.id = " + idCliente;

		String orderByPart = "order by cl.razonSocial asc, f.fechaFactura asc, f.preimpresoNumero asc";
		String queryString = selectPart + " " + fromPart + " " + wherePart
				+ " " + orderByPart;

		Query query = manager.createQuery(queryString);
		if (fechaInicial != null)
			query.setParameter("fechaInicial", fechaInicial);
		if (fechaFinal != null)
			query.setParameter("fechaFinal", fechaFinal);
		return query.getResultList();
	}*/

	public Collection findCuentasPorCobrar(Long idEmpresa, Long idCliente, Date fechaInicial, Date fechaFinal) throws GenericBusinessException {
		//select distinct c.*, f.*, cl.* from CARTERA c LEFT OUTER JOIN FACTURA f ON c.REFERENCIA_ID = f.ID, CLIENTE cl, CLIENTE_OFICINA clo, TIPO_DOCUMENTO td where c.CODIGO like 'AXS-%FAC-%' and c.CLIENTEOFICINA_ID = clo.ID and clo.CLIENTE_ID = cl.ID and c.TIPODOCUMENTO_ID = td.ID and td.EMPRESA_ID = 40 and c.CODIGO like 'AXS-%FAC-%' and c.ESTADO = 'N'
		EmpresaIf empresa = empresaLocal.getEmpresa(idEmpresa);
		String codigo = empresa.getCodigo() + "-%FAC-%";
		String selectPart = "select distinct cpc";
		String fromPart = "from CuentasPorCobrarEJB cpc, TipoDocumentoEJB td";
		String wherePart = "where cpc.tipodocumentoId = td.id and td.empresaId = "
			+ idEmpresa
			+ " and cpc.codigo like '"
			+ codigo + "'";

		if (fechaInicial != null)
			wherePart += " and (cpc.fechaEmision >= :fechaInicial)";
		if (fechaFinal != null)
			wherePart += " and (cpc.fechaEmision <= :fechaFinal)";

		if (idCliente != 0L)
			wherePart += " and cpc.clienteId = " + idCliente;

		String orderByPart = "order by cpc.razonSocial asc, cpc.fechaEmision asc, cpc.preimpreso asc";
		String queryString = selectPart + " " + fromPart + " " + wherePart
		+ " " + orderByPart;

		Query query = manager.createQuery(queryString);
		if (fechaInicial != null)
			query.setParameter("fechaInicial", fechaInicial);
		if (fechaFinal != null)
			query.setParameter("fechaFinal", fechaFinal);
		return query.getResultList();
	}

	public Collection findCuentasPorCobrar(Long idEmpresa, Long idCliente, Date fechaCorte) throws GenericBusinessException {
		EmpresaIf empresa = empresaLocal.getEmpresa(idEmpresa);
		String codigo = empresa.getCodigo() + "-%FAC-%";
		String selectPart = "select distinct cpc";
		String fromPart = "from CuentasPorCobrarEJB cpc, TipoDocumentoEJB td";
		String wherePart = "where cpc.tipodocumentoId = td.id and td.empresaId = "
			+ idEmpresa
			+ " and cpc.codigo like '"
			+ codigo
			+ "'";

		if (fechaCorte != null)
			wherePart += " and (cpc.fechaEmision <= :fechaCorte)";

		if (idCliente != 0L)
			wherePart += " and cpc.clienteId = " + idCliente;

		String orderByPart = "order by cpc.razonSocial asc, cpc.fechaEmision asc, cpc.preimpreso asc";
		String queryString = selectPart + " " + fromPart + " " + wherePart
		+ " " + orderByPart;

		Query query = manager.createQuery(queryString);
		if (fechaCorte != null)
			query.setParameter("fechaCorte", fechaCorte);
		return query.getResultList();
	}

	public Collection findCuentasPorCobrarAdicionales(Long idEmpresa,
			Long idCliente, Long idModulo, Date fechaInicial, Date fechaFinal)
	throws GenericBusinessException {
		EmpresaIf empresa = empresaLocal.getEmpresa(idEmpresa);
		String codigo = empresa.getCodigo() + "-%NCI-%";
		String selectPart = "select distinct c, cl";
		String fromPart = "from CarteraEJB c, ClienteEJB cl, ClienteOficinaEJB clo, TipoDocumentoEJB td";
		String wherePart = "where c.clienteoficinaId = clo.id and clo.clienteId = cl.id and c.tipodocumentoId = td.id and td.empresaId = "
			+ idEmpresa
			+ " and td.signocartera = '+' and td.tipocartera = 'C' and td.moduloId = "
			+ idModulo + " and c.estado = 'N'";

		if (fechaInicial != null)
			wherePart += " and c.fechaEmision >= :fechaInicial";
		if (fechaFinal != null)
			wherePart += " and c.fechaEmision <= :fechaFinal";

		if (idCliente != 0L)
			wherePart += " and cl.id = " + idCliente;

		wherePart += " and c.codigo not in(select distinct ca.codigo from CarteraEJB ca, TipoDocumentoEJB tdo where ca.tipodocumentoId = tdo.id and tdo.empresaId = "
			//+ idEmpresa + " and (ca.codigo like '" + codigo + "' or c.referenciaId is null))";
				+ idEmpresa + " and ca.codigo like '" + codigo + "')";
		String orderByPart = "order by cl.razonSocial asc, c.fechaEmision asc, c.codigo asc";
		String queryString = selectPart + " " + fromPart + " " + wherePart
		+ " " + orderByPart;

		Query query = manager.createQuery(queryString);
		if (fechaInicial != null)
			query.setParameter("fechaInicial", fechaInicial);
		if (fechaFinal != null)
			query.setParameter("fechaFinal", fechaFinal);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCarteraByFechaInicioFechaFinListaTipoDocumento(
			List<Long> tiposDocumentos, Date fechaInicio, Date fechaFin,
			java.lang.Long idEmpresa)
	throws com.spirit.exception.GenericBusinessException {
		try {
			if (tiposDocumentos == null || tiposDocumentos.size() == 0) {
				throw new GenericBusinessException(
				"Especificar los tipos de documentos de cartera");
			}
			String objectName = "ca";
			String queryString = "select distinct ca from CarteraEJB "
				+ objectName + ", TipoDocumentoEJB td where ";
			queryString += " ca.tipodocumentoId = td.id and td.empresaId = "
				+ idEmpresa;
			if (fechaInicio != null && fechaFin != null) {
				queryString += " and ca.fechaEmision >= :fechaInicio and ca.fechaEmision <= :fechaFin";
			}
			for (Long idTipoDocumento : tiposDocumentos) {
				queryString += " and ca.tipodocumentoId = " + idTipoDocumento;
			}
			//el orden debe ser por codigo para que funcione bien el panel Auditoria de Comprobantes
			queryString += " order by ca.codigo asc";
			Query query = manager.createQuery(queryString);
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);
			return query.getResultList();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
			"Se ha producido un error al consultar Carteras por fecha ");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCarteraByFechaInicioFechaFin(Date fechaInicio, Date fechaFin, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException {
		try {
			String objectName = "ca";
			String queryString = "select distinct ca from CarteraEJB " + objectName + ", TipoDocumentoEJB td where ";
			queryString += " ca.tipodocumentoId = td.id and td.empresaId = " + idEmpresa;
			
			if (fechaInicio != null) {
				queryString += " and ca.fechaEmision >= :fechaInicio ";
			}
			
			if (fechaFin != null) {
				queryString += " and ca.fechaEmision <= :fechaFin ";
			}
			
			queryString += " order by ca.codigo asc";
			Query query = manager.createQuery(queryString);
			
			if (fechaInicio != null) {
				query.setParameter("fechaInicio", fechaInicio);
			}
			
			if (fechaFin != null) {
				query.setParameter("fechaFin", fechaFin);
			}
			
			return query.getResultList();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
			"Se ha producido un error al consultar Carteras por fecha ");
		}
	}

	/***************************************************************************
	 * COBROS DIFERIDOS **********************
	 * 
	 * @throws GenericBusinessException
	 */

	public void procesarCobrosDiferidos(
			Collection<CarteraDetalleIf> carteraDetalleCollection,
			Map<String, Object> parametrosEmpresa)
	throws GenericBusinessException {
		try {
			/*
			 * for ( CarteraDetalleIf carteraDetalleTmp :
			 * carteraDetalleCollection ){ CarteraDetalleIf carteraDetalle =
			 * carteraDetalleLocal.getCarteraDetalle(carteraDetalleTmp.getId());
			 * carteraDetalle.setCuentaBancariaId(carteraDetalleTmp.getCuentaBancariaId());
			 * Collection<CarteraAfectaIf> carteraAfectas =
			 * carteraAfectaLocal.findCarteraAfectaByCarteradetalleId(carteraDetalle.getId());
			 * for ( CarteraAfectaIf carteraAfectaIf :carteraAfectas ){ double
			 * valorAfecta = utilitariosLocal.redondeoValor(
			 * carteraAfectaIf.getValor().doubleValue() );
			 * 
			 * CarteraIf cartera = null; //Se le resta al saldo de la cartera
			 * que esta afectando a otra { //detalle double saldoAfectador =
			 * utilitariosLocal.redondeoValor(carteraDetalle.getSaldo().doubleValue());
			 * double restaSaldo = utilitariosLocal.redondeoValor(saldoAfectador -
			 * valorAfecta); if ( restaSaldo >= 0.00 )
			 * carteraDetalle.setSaldo(BigDecimal.valueOf(restaSaldo)); else
			 * throw new GenericBusinessException("El saldo del detalle se hace
			 * negativo"); //cabecera cartera = getCartera(
			 * carteraDetalle.getCarteraId() ); saldoAfectador =
			 * utilitariosLocal.redondeoValor( cartera.getSaldo().doubleValue() );
			 * restaSaldo = utilitariosLocal.redondeoValor(saldoAfectador -
			 * valorAfecta); if ( restaSaldo >= 0.00 )
			 * cartera.setSaldo(BigDecimal.valueOf(restaSaldo)); else throw new
			 * GenericBusinessException("El saldo de la cabecera se hace
			 * negativo"); } //Verificacion de saldo cabecera con la suma de
			 * saldos de detalles if ( cartera != null ){ Collection<CarteraDetalleIf>
			 * detalles =
			 * carteraDetalleLocal.findCarteraDetalleByCarteraId(cartera.getId());
			 * double sumaSaldoDetalles = 0.0; for ( CarteraDetalleIf cdi :
			 * detalles ){ sumaSaldoDetalles += utilitariosLocal.redondeoValor(
			 * cdi.getSaldo().doubleValue() ); } sumaSaldoDetalles =
			 * utilitariosLocal.redondeoValor( sumaSaldoDetalles ); double
			 * saldoCartera = utilitariosLocal.redondeoValor(
			 * cartera.getSaldo().doubleValue() ); if ( sumaSaldoDetalles !=
			 * saldoCartera ) throw new GenericBusinessException("En Cartera que
			 * afecta Saldo-Cabecera y Saldo-Detalles no coinciden"); }
			 * 
			 * 
			 * CarteraIf carteraAfectada = null; //Se le resta al saldo de la
			 * cartera afectada { //detalle CarteraDetalleIf
			 * carteraDetalleAfectada = carteraDetalleLocal.getCarteraDetalle(
			 * carteraAfectaIf.getCarteraafectaId() ); double saldoAfectado =
			 * utilitariosLocal.redondeoValor(carteraDetalleAfectada.getSaldo().doubleValue());
			 * double restaSaldo = utilitariosLocal.redondeoValor(saldoAfectado -
			 * valorAfecta); if ( restaSaldo >= 0.00 )
			 * carteraDetalleAfectada.setSaldo(BigDecimal.valueOf(restaSaldo));
			 * else throw new GenericBusinessException("El saldo del detalle
			 * afectado se hace negativo"); //cabecera carteraAfectada =
			 * getCartera( carteraDetalleAfectada.getCarteraId() );
			 * saldoAfectado = utilitariosLocal.redondeoValor(
			 * carteraAfectada.getSaldo().doubleValue() ); restaSaldo =
			 * utilitariosLocal.redondeoValor(saldoAfectado - valorAfecta); if (
			 * restaSaldo >= 0.00 )
			 * carteraAfectada.setSaldo(BigDecimal.valueOf(restaSaldo)); else
			 * throw new GenericBusinessException("El saldo de la cabecera
			 * afectada se hace negativo"); } if ( carteraAfectada != null ){
			 * Collection<CarteraDetalleIf> detalles =
			 * carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraAfectada.getId());
			 * double sumaSaldoDetalles = 0.0; for ( CarteraDetalleIf cdi :
			 * detalles ){ sumaSaldoDetalles += utilitariosLocal.redondeoValor(
			 * cdi.getSaldo().doubleValue() ); } sumaSaldoDetalles =
			 * utilitariosLocal.redondeoValor( sumaSaldoDetalles ); double
			 * saldoCartera = utilitariosLocal.redondeoValor(
			 * carteraAfectada.getSaldo().doubleValue() ); if (
			 * sumaSaldoDetalles != saldoCartera ) throw new
			 * GenericBusinessException("En Cartera que afectada Saldo-Cabecera
			 * y Saldo-Detalles no coinciden"); }
			 * 
			 * carteraAfectaIf.setCartera("S"); }
			 * 
			 * carteraDetalle.setCartera("S"); }
			 */

			for (CarteraDetalleIf carteraDetalleTmp : carteraDetalleCollection) {
				CarteraDetalleIf carteraDetalle = carteraDetalleLocal
				.getCarteraDetalle(carteraDetalleTmp.getId());
				carteraDetalle.setDiferido("");
			}

			Map detallesByCarteraMap = mapearDetallesPorCartera(carteraDetalleCollection);
			Iterator detallesByCarteraMapIt = detallesByCarteraMap.keySet()
			.iterator();
			while (detallesByCarteraMapIt.hasNext()) {
				comprobanteIngresoAsientoAutomaticoHandlerLocal
				.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
				int i = 0;
				List<CarteraDetalleIf> carteraDetalleList = (List<CarteraDetalleIf>) detallesByCarteraMap
				.get(detallesByCarteraMapIt.next());
				for (CarteraDetalleIf carteraDetalle : carteraDetalleList) {
					CarteraIf cartera = getCartera(carteraDetalle
							.getCarteraId());
					if (i != carteraDetalleList.size() - 1) {
						generarAsientoAutomaticoComprobanteIngreso(cartera,
								carteraDetalle, false, parametrosEmpresa, 2L);
					} else if (i == carteraDetalleList.size() - 1) {
						generarAsientoAutomaticoComprobanteIngreso(cartera,
								carteraDetalle, true, parametrosEmpresa, 2L);
					}
					i++;
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
			"Error general al actualizar Cobros");
		}
	}

	private Map mapearDetallesPorCartera(
			Collection<CarteraDetalleIf> carteraDetalleColeccion) {
		Map detallesByCarteraMap = new HashMap();
		Iterator carteraDetalleIt = carteraDetalleColeccion.iterator();
		while (carteraDetalleIt.hasNext()) {
			CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) carteraDetalleIt
			.next();
			List<CarteraDetalleIf> carteraDetalleList = (List<CarteraDetalleIf>) detallesByCarteraMap
			.get(carteraDetalle.getCarteraId());
			if (carteraDetalleList == null || carteraDetalleList.size() <= 0)
				carteraDetalleList = new ArrayList<CarteraDetalleIf>();
			carteraDetalleList.add(carteraDetalle);
			detallesByCarteraMap.put(carteraDetalle.getCarteraId(),
					carteraDetalleList);
		}

		return detallesByCarteraMap;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void desaprobarPagos(List<CarteraIf> carteraList)
	throws GenericBusinessException {
		try {
			for (CarteraIf carteraDesaprobada : carteraList) {
				Collection<CarteraDetalleIf> modelCarteraDetalleList = carteraDetalleLocal
				.findCarteraDetalleByCarteraId(carteraDesaprobada
						.getId());
				for (CarteraDetalleIf carteraDetalleDesaprobada : modelCarteraDetalleList) {
					manager.remove(carteraDetalleDesaprobada);
				}
				CarteraEJB carteraDesaprobadaEJB = manager.find(
						CarteraEJB.class, carteraDesaprobada.getId());
				manager.remove(carteraDesaprobadaEJB);
			}

			manager.flush();

		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al eliminar información en CompraEJB.", e);
			throw new GenericBusinessException(
			"Error al eliminar información en Compra");
		}
	}

	public void procesarCruceCuentas(
			List<CarteraIf> cuentasPorCobrarSeleccionadas,
			CarteraIf cuentaPorPagar, Map valoresAplica, Map parameterMap)
	throws GenericBusinessException {
		try {
			long empresaId = ((Long)parameterMap.get("EMPRESA_ID")).longValue();
			long oficinaId = ((Long) parameterMap.get("OFICINA_ID")).longValue();
			long usuarioId = ((Long) parameterMap.get("USUARIO_ID")).longValue();
			long monedaId = ((Long) parameterMap.get("MONEDA_ID")).longValue();
			long operadorNegocioOficinaId = ((Long) parameterMap.get("OPERADOR_NEGOCIO_OFICINA_ID")).longValue();
			ClienteIf operadorNegocio = (ClienteIf) parameterMap.get("OPERADOR_NEGOCIO");
			java.sql.Date fecha = (java.sql.Date) parameterMap.get("FECHA_CRUCE");
			Long lineaId = null;
			if ((parameterMap.get("LINEA_ID")) != null)
				lineaId = ((Long) parameterMap.get("LINEA_ID")).longValue();
			double valorAfecta = ((Double) parameterMap.get("VALOR_AFECTA")).doubleValue();
			Iterator it = tipoDocumentoLocal.findTipoDocumentoByCodigo("ICC").iterator();
			TipoDocumentoIf tipoDocumentoNotaInternaCreditoCliente = (it.hasNext())?(TipoDocumentoIf) it.next():null;
			it = tipoDocumentoLocal.findTipoDocumentoByCodigo("ICP").iterator();
			TipoDocumentoIf tipoDocumentoNotaInternaCreditoProveedor = (it.hasNext())?(TipoDocumentoIf) it.next():null;
			it = documentoLocal.findDocumentoByCodigo("NICC").iterator();
			DocumentoIf documentoNotaInternaCreditoCliente = (it.hasNext())?(DocumentoIf) it.next():null;
			it = documentoLocal.findDocumentoByCodigo("NICP").iterator();
			DocumentoIf documentoNotaInternaCreditoProveedor = (it.hasNext())?(DocumentoIf) it.next():null;

			double saldoCuentaPorPagar = cuentaPorPagar.getSaldo().doubleValue();
			CarteraEJB carteraCuentaPorPagar = registrarCartera(cuentaPorPagar);
			carteraCuentaPorPagar.setSaldo(BigDecimal.valueOf(saldoCuentaPorPagar - valorAfecta));
			manager.merge(carteraCuentaPorPagar);
			it = carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraCuentaPorPagar.getId()).iterator();
			CarteraDetalleIf detalleCuentaPorPagar = (it.hasNext())?(CarteraDetalleIf) it.next():null;
			detalleCuentaPorPagar.setSaldo(BigDecimal.valueOf(saldoCuentaPorPagar - valorAfecta));
			CarteraDetalleEJB carteraDetalleCuentaPorPagar = registrarCarteraDetalle(detalleCuentaPorPagar, true);
			manager.merge(carteraDetalleCuentaPorPagar);

			//Creación de nota interna de crédito de cliente
			CarteraData data = new CarteraData();
			data.setTipo("C");
			data.setOficinaId(oficinaId);
			data.setTipodocumentoId(tipoDocumentoNotaInternaCreditoCliente.getId());
			String unNumeroCartera = getNumeroCartera(fecha, tipoDocumentoNotaInternaCreditoCliente.getCodigo(), empresaId, oficinaId);
			data.setCodigo(unNumeroCartera);
			data.setCodigo(data.getCodigo() + formatoSerial.format(getMaximoNumeroCartera(data.getCodigo())));
			data.setClienteoficinaId(operadorNegocioOficinaId);
			data.setUsuarioId(usuarioId);
			data.setMonedaId(monedaId);
			data.setFechaEmision(utilitariosLocal.fromSqlDateToTimestamp(fecha));
			data.setValor(BigDecimal.valueOf(valorAfecta));
			data.setSaldo(BigDecimal.ZERO);
			data.setEstado("N");
			data.setComentario("N/C INTERNA: " + data.getCodigo() + " " + operadorNegocio.getRazonSocial());
			CarteraEJB carteraNotaInternaCreditoCliente = registrarCartera(data);
			manager.persist(carteraNotaInternaCreditoCliente);

			//Creación de nota interna de crédito de proveedor
			data = new CarteraData();
			data.setTipo("P");
			data.setOficinaId(oficinaId);
			data.setTipodocumentoId(tipoDocumentoNotaInternaCreditoProveedor.getId());
			unNumeroCartera = getNumeroCartera(fecha, tipoDocumentoNotaInternaCreditoProveedor.getCodigo(), empresaId, oficinaId);
			data.setCodigo(unNumeroCartera);
			data.setCodigo(data.getCodigo() + formatoSerial.format(getMaximoNumeroCartera(data.getCodigo())));
			data.setClienteoficinaId(cuentaPorPagar.getClienteoficinaId());
			data.setUsuarioId(usuarioId);
			data.setMonedaId(monedaId);
			data.setFechaEmision(utilitariosLocal.fromSqlDateToTimestamp(fecha));
			data.setValor(BigDecimal.valueOf(valorAfecta));
			data.setSaldo(BigDecimal.ZERO);
			data.setEstado("N");
			data.setComentario("N/C INTERNA: " + data.getCodigo() + " " + operadorNegocio.getRazonSocial());
			CarteraEJB carteraNotaInternaCreditoProveedor = registrarCartera(data);
			manager.persist(carteraNotaInternaCreditoProveedor);

			//Creación detalle nota interna de crédito de proveedor
			CarteraDetalleData detailData = new CarteraDetalleData();
			detailData = new CarteraDetalleData();
			detailData.setCarteraId(carteraNotaInternaCreditoProveedor.getPrimaryKey());
			detailData.setDocumentoId(documentoNotaInternaCreditoProveedor.getId());
			detailData.setSecuencial(1);
			detailData.setLineaId(lineaId);
			detailData.setFechaCreacion(fecha);
			detailData.setFechaCartera(fecha);
			detailData.setValor(BigDecimal.valueOf(valorAfecta));
			detailData.setSaldo(BigDecimal.ZERO);
			detailData.setCartera("S");
			String observacion = "CANC. ";
			TipoDocumentoIf tipoDocumentoCuentaPorPagar = tipoDocumentoLocal.getTipoDocumento(cuentaPorPagar.getTipodocumentoId());
			if (tipoDocumentoCuentaPorPagar.getLiquidacionCompras().equals("S"))
				observacion += "L: ";
			else if (tipoDocumentoCuentaPorPagar.getFactura().equals("S"))
				observacion += "F: ";
			else if (tipoDocumentoCuentaPorPagar.getNotaDebito().equals("S"))
				observacion += "N/D: ";

			observacion += cuentaPorPagar.getPreimpreso()!=null?cuentaPorPagar.getPreimpreso().substring(8,15):"S/N" + " CON N/C INT. " + carteraNotaInternaCreditoProveedor.getCodigo() + " " + operadorNegocio.getRazonSocial();
			detailData.setObservacion(observacion);
			CarteraDetalleEJB carteraDetalleNotaInternaCreditoProveedor = registrarCarteraDetalle(detailData, true);
			manager.persist(carteraDetalleNotaInternaCreditoProveedor);

			//Generar Asiento Nota Interna de Credito Proveedor
			parameterMap = new HashMap();
			parameterMap.put(String.valueOf(carteraDetalleNotaInternaCreditoProveedor.getPrimaryKey().longValue()), Double.valueOf(valorAfecta));
			List<CarteraDetalleIf> carteraDetalleList = (List<CarteraDetalleIf>) carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraNotaInternaCreditoProveedor.getId());
			generarAsientosAutomaticos(carteraNotaInternaCreditoProveedor, carteraDetalleList, parameterMap, false);

			// Cruce de nota interna de crédito de proveedor con cuenta por pagar (creación de cartera afecta)
			CarteraAfectaData afectaData = new CarteraAfectaData();
			afectaData = new CarteraAfectaData();
			afectaData.setCarteradetalleId(carteraDetalleNotaInternaCreditoProveedor.getId());
			afectaData.setCarteraafectaId(carteraDetalleCuentaPorPagar.getId());
			afectaData.setUsuarioId(usuarioId);
			afectaData.setValor(BigDecimal.valueOf(valorAfecta));
			afectaData.setFechaCreacion(fecha);
			afectaData.setFechaAplicacion(fecha);
			afectaData.setCartera("S");
			CarteraAfectaEJB carteraAfectaNotaInternaCreditoProveedor = registrarCarteraAfecta(afectaData);
			manager.merge(carteraAfectaNotaInternaCreditoProveedor);
			parameterMap = new HashMap(); 
			for (int i=0; i<cuentasPorCobrarSeleccionadas.size(); i++) {
				CarteraIf cuentaPorCobrar = (CarteraIf) cuentasPorCobrarSeleccionadas.get(i);
				//valorAfecta = cuentaPorCobrar.getSaldo().doubleValue();
				valorAfecta = ((Double) valoresAplica.get(cuentaPorCobrar.getId())).doubleValue();
				double saldoCuentaPorCobrar = cuentaPorCobrar.getSaldo().doubleValue();
				saldoCuentaPorPagar = cuentaPorPagar.getSaldo().doubleValue();
				if (saldoCuentaPorPagar > 0D) {
					if (saldoCuentaPorPagar - valorAfecta < 0)
						valorAfecta = saldoCuentaPorPagar;
					cuentaPorCobrar.setSaldo(BigDecimal.valueOf(saldoCuentaPorCobrar - valorAfecta));
					CarteraEJB carteraCuentaPorCobrar = registrarCartera(cuentaPorCobrar);
					manager.merge(carteraCuentaPorCobrar);
					cuentaPorPagar.setSaldo(BigDecimal.valueOf(saldoCuentaPorPagar - valorAfecta));

					it = carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraCuentaPorCobrar.getId()).iterator();
					CarteraDetalleIf detalleCuentaPorCobrar = (it.hasNext())?(CarteraDetalleIf) it.next():null;
					detalleCuentaPorCobrar.setSaldo(BigDecimal.valueOf(saldoCuentaPorCobrar - valorAfecta));
					CarteraDetalleEJB carteraDetalleCuentaPorCobrar = registrarCarteraDetalle(detalleCuentaPorCobrar, true);
					manager.merge(carteraDetalleCuentaPorCobrar);

					// Creación detalle nota interna de crédito de cliente
					detailData = new CarteraDetalleData();
					detailData.setCarteraId(carteraNotaInternaCreditoCliente.getPrimaryKey());
					detailData.setDocumentoId(documentoNotaInternaCreditoCliente.getId());
					detailData.setSecuencial(1);
					detailData.setLineaId(lineaId);
					detailData.setFechaCreacion(fecha);
					detailData.setFechaCartera(fecha);
					detailData.setValor(BigDecimal.valueOf(valorAfecta));
					detailData.setSaldo(BigDecimal.ZERO);
					detailData.setCartera("S");
					observacion = "CANC. ";
					TipoDocumentoIf tipoDocumentoCuentaPorCobrar = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(cuentaPorCobrar.getTipodocumentoId());
					if (tipoDocumentoCuentaPorCobrar.getNotaVenta().equals("S"))
						observacion += "N/V: ";
					else if (tipoDocumentoCuentaPorCobrar.getFactura().equals("S"))
						observacion += "F: ";

					observacion += cuentaPorCobrar.getPreimpreso() != null?cuentaPorCobrar.getPreimpreso().substring(8,15):"S/N" + " CON N/C INT. " + carteraNotaInternaCreditoCliente.getCodigo() + " " + operadorNegocio.getRazonSocial();
					detailData.setObservacion(observacion);
					CarteraDetalleEJB carteraDetalleNotaInternaCreditoCliente = registrarCarteraDetalle(detailData, true);
					manager.persist(carteraDetalleNotaInternaCreditoCliente);
					parameterMap.put(String.valueOf(carteraDetalleNotaInternaCreditoCliente.getPrimaryKey().longValue()), Double.valueOf(valorAfecta));

					// Cruce de nota interna de credito de cliente con cuenta por cobrar (creación de cartera afecta)
					afectaData = new CarteraAfectaData();
					afectaData.setCarteradetalleId(carteraDetalleNotaInternaCreditoCliente.getId());
					afectaData.setCarteraafectaId(carteraDetalleCuentaPorCobrar.getId());
					afectaData.setUsuarioId(usuarioId);
					afectaData.setValor(BigDecimal.valueOf(valorAfecta));
					afectaData.setFechaCreacion(fecha);
					afectaData.setFechaAplicacion(fecha);
					afectaData.setCartera("S");
					CarteraAfectaEJB carteraAfectaNotaInternaCreditoCliente = registrarCarteraAfecta(afectaData);
					manager.merge(carteraAfectaNotaInternaCreditoCliente);

				} else
					break;
			}

			//Generar Asiento Nota Interna de Credito Cliente
			carteraDetalleList = (List<CarteraDetalleIf>) carteraDetalleLocal.findCarteraDetalleByCarteraId(carteraNotaInternaCreditoCliente.getId());
			generarAsientosAutomaticos(carteraNotaInternaCreditoCliente, carteraDetalleList, parameterMap, false);
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al guardar información de cruce de cuentas.", e);
			throw new GenericBusinessException("Se ha producido un error al realizar cruce de cuentas");
		}
	}

	public void procesarCruceAnticipoCliente(
			List<CarteraIf> cuentasPorCobrarSeleccionadas, CarteraIf anticipo,
			Map valoresAplica, Map<String, Object> parameterMap)
	throws GenericBusinessException {
		try {
			long empresaId = ((Long) parameterMap.get("idEmpresa")).longValue();
			long oficinaId = ((Long) parameterMap.get("idOficina")).longValue();
			long usuarioId = ((Long) parameterMap.get("USUARIO_ID"))
			.longValue();
			long monedaId = ((Long) parameterMap.get("MONEDA_ID")).longValue();
			long operadorNegocioOficinaId = ((Long) parameterMap
					.get("OPERADOR_NEGOCIO_OFICINA_ID")).longValue();
			ClienteIf operadorNegocio = (ClienteIf) parameterMap
			.get("OPERADOR_NEGOCIO");
			java.sql.Date fecha = (java.sql.Date) parameterMap
			.get("FECHA_CRUCE");
			long lineaId = ((Long) parameterMap.get("LINEA_ID")).longValue();
			double valorAplica = ((Double) parameterMap.get("VALOR_AFECTA"))
			.doubleValue();
			double saldoAnticipo = anticipo.getSaldo().doubleValue();
			CarteraEJB carteraAnticipo = registrarCartera(anticipo);
			carteraAnticipo.setSaldo(utilitariosLocal.redondeoValor(BigDecimal
					.valueOf(saldoAnticipo - valorAplica)));
			manager.merge(carteraAnticipo);
			Iterator it = carteraDetalleLocal.findCarteraDetalleByCarteraId(
					carteraAnticipo.getId()).iterator();
			CarteraDetalleIf detalleAnticipo = (it.hasNext()) ? (CarteraDetalleIf) it
					.next()
					: null;
					detalleAnticipo.setSaldo(utilitariosLocal.redondeoValor(BigDecimal
							.valueOf(saldoAnticipo - valorAplica)));
					CarteraDetalleEJB carteraDetalleAnticipo = registrarCarteraDetalle(
							detalleAnticipo, true);
					manager.merge(carteraDetalleAnticipo);

					for (int i = 0; i < cuentasPorCobrarSeleccionadas.size(); i++) {
						CarteraIf cuentaPorCobrar = (CarteraIf) cuentasPorCobrarSeleccionadas
						.get(i);
						// valorAfecta = cuentaPorCobrar.getSaldo().doubleValue();
						double valorAfecta = ((Double) valoresAplica
								.get(cuentaPorCobrar.getId())).doubleValue();
						double saldoCuentaPorCobrar = cuentaPorCobrar.getSaldo()
						.doubleValue();
						saldoAnticipo = anticipo.getSaldo().doubleValue();
						if (saldoAnticipo > 0D) {
							if (saldoAnticipo - valorAfecta < 0)
								valorAfecta = saldoAnticipo;
							cuentaPorCobrar.setSaldo(BigDecimal
									.valueOf(saldoCuentaPorCobrar - valorAfecta));
							CarteraEJB carteraCuentaPorCobrar = registrarCartera(cuentaPorCobrar);
							manager.merge(carteraCuentaPorCobrar);
							anticipo.setSaldo(BigDecimal.valueOf(saldoAnticipo
									- valorAfecta));

							it = carteraDetalleLocal.findCarteraDetalleByCarteraId(
									carteraCuentaPorCobrar.getId()).iterator();
							CarteraDetalleIf detalleCuentaPorCobrar = (it.hasNext()) ? (CarteraDetalleIf) it
									.next()
									: null;
									detalleCuentaPorCobrar.setSaldo(BigDecimal
											.valueOf(saldoCuentaPorCobrar - valorAfecta));
									CarteraDetalleEJB carteraDetalleCuentaPorCobrar = registrarCarteraDetalle(
											detalleCuentaPorCobrar, true);
									manager.merge(carteraDetalleCuentaPorCobrar);

									// Cruce de nota interna de credito de cliente con cuenta
									// por cobrar (creación de cartera afecta)
									CarteraAfectaData afectaData = new CarteraAfectaData();
									afectaData.setCarteradetalleId(carteraDetalleAnticipo
											.getId());
									afectaData.setCarteraafectaId(carteraDetalleCuentaPorCobrar
											.getId());
									afectaData.setUsuarioId(usuarioId);
									afectaData.setValor(BigDecimal.valueOf(valorAfecta));
									afectaData.setFechaCreacion(fecha);
									afectaData.setFechaAplicacion(fecha);
									afectaData.setCartera("S");
									CarteraAfectaEJB carteraAfectaAnticipo = registrarCarteraAfecta(afectaData);
									manager.merge(carteraAfectaAnticipo);

						} else
							break;
					}

					// Generar Asiento Cruce Nota Crédito Anticipo Cliente
					Long etapa = 2L;
					notaCreditoAnticipoClienteAsientoAutomaticoHandlerLocal
					.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
					generarAsientoAutomaticoCruceNotaCreditoAnticipoCliente(anticipo,
							carteraDetalleAnticipo, valorAplica, true, parameterMap,
							etapa);
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al guardar información de cruce de cuentas.", e);
			throw new GenericBusinessException(
			"Se ha producido un error al realizar cruce de cuentas");
		}
	}

	public Collection findAnticiposClientePorCruzar(Long idEmpresa,
			Long idCliente, Date fechaCorte) throws GenericBusinessException {
		String selectPart = "select distinct c";
		String fromPart = "from CarteraEJB c, CarteraDetalleEJB cd, ClienteEJB cl, ClienteOficinaEJB clo, TipoDocumentoEJB td, DocumentoEJB d";
		String wherePart = "where c.id = cd.carteraId and c.clienteoficinaId = clo.id and clo.clienteId = cl.id and cl.id = "
			+ idCliente
			+ " and cd.documentoId = d.id and (d.codigo = 'NCAC' or d.codigo = 'ANSA') and c.tipodocumentoId = td.id and td.empresaId = "
			+ idEmpresa + " and c.saldo > 0 and c.estado = 'N'";

		if (fechaCorte != null)
			wherePart += " and c.fechaEmision <= :fechaCorte";

		String orderByPart = "order by c.fechaEmision asc";
		String queryString = selectPart + " " + fromPart + " " + wherePart
		+ " " + orderByPart;

		Query query = manager.createQuery(queryString);
		if (fechaCorte != null)
			query.setParameter("fechaCorte", fechaCorte);
		return query.getResultList();
	}

	public Long generarReciboCajaPOS(Vector<Vector> detallesPagos, Map<String, Object> parametros, boolean procesandoPrincipal) {
		List<CarteraDetalleIf> reciboCajaDetalles = facturaLocal.generarDetalleCobroVector(detallesPagos, "RCA", 0D);
		CarteraIf carteraComprobanteIngreso = null;
		String TIPO_CARTERA_CLIENTE = "C";
		String ESTADO_CARTERA_NORMAL = "N";
		String TIPO_CLIENTE = "C";
		EmpleadoIf empleado = (EmpleadoIf) parametros.get("EMPLEADO");
		ClienteIf cliente = (ClienteIf) parametros.get("CLIENTE");
		ClienteOficinaIf clienteOficina = (ClienteOficinaIf) parametros.get("CLIENTE_OFICINA");
		PuntoImpresionIf puntoImpresion = (PuntoImpresionIf) parametros.get("PUNTO_IMPRESION");
		Long idEmpresa = (Long) parametros.get("EMPRESA_ID");
		Long idOficina = (Long) parametros.get("OFICINA_ID");
		UsuarioIf usuario = (UsuarioIf) parametros.get("USUARIO");
		MonedaIf moneda = (MonedaIf) parametros.get("MONEDA");
		String referencia = (String) parametros.get("REFERENCIA");
		CarteraEJB reciboCaja = new CarteraEJB();
		Long idCartera = 0L;
		try {
			java.sql.Timestamp now = new java.sql.Timestamp(utilitariosLocal.getServerDateSql().getTime());
			String unNumeroCartera = getNumeroCartera(new java.sql.Date(now.getTime()), "RCA", idEmpresa,idOficina);
			reciboCaja.setCodigo(unNumeroCartera);
			reciboCaja.setCodigo(reciboCaja.getCodigo()+ formatoSerial.format(getMaximoNumeroCartera(reciboCaja.getCodigo())));
			reciboCaja.setTipo(TIPO_CLIENTE);
			CajaIf caja = (CajaIf) cajaLocal.findCajaByUsuarioId(usuario.getId()).iterator().next();
			OficinaIf oficina = oficinaLocal.getOficina(idOficina);
			/*if (caja != null)
				modelCartera.setPreimpreso(modelFactura.getPreimpresoNumero());*/
			reciboCaja.setOficinaId(idOficina);
			Map parameterMap = new HashMap();
			parameterMap.put("codigo", "RCA");
			parameterMap.put("empresaId", idEmpresa);
			TipoDocumentoIf tipoDocumentoIf = (TipoDocumentoIf)tipoDocumentoLocal.findTipoDocumentoByQuery(parameterMap).iterator().next();			
			reciboCaja.setTipodocumentoId(tipoDocumentoIf.getId());
			reciboCaja.setClienteoficinaId(clienteOficina.getId());
			reciboCaja.setUsuarioId(usuario.getId());
			reciboCaja.setVendedorId(empleado.getId());
			reciboCaja.setMonedaId(moneda.getId());
			reciboCaja.setFechaEmision(now);
			double valorCartera = sumarValoresCarteraDetalleList(reciboCajaDetalles);
			reciboCaja.setValor(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorCartera)));
			reciboCaja.setSaldo(utilitariosLocal.redondeoValor(BigDecimal.valueOf(valorCartera)));
			reciboCaja.setEstado(ESTADO_CARTERA_NORMAL);
			String comentario = "RCA/ " + cliente.getNombreLegal() + " REF.: " + referencia;
			reciboCaja.setComentario(comentario);
			CarteraIf cartera = addCartera(reciboCaja);
			if (!procesandoPrincipal) {
				cartera.setReferenciaId(cartera.getPrimaryKey());
			} else {
				Long referenciaId = (Long) parametros.get("REFERENCIA_ID");
				cartera.setReferenciaId(referenciaId);
			}
			saveCartera(cartera);
			if (cartera != null)
				idCartera = cartera.getId();
			List<CarteraDetalleIf> carteraDetalleList = new ArrayList<CarteraDetalleIf>();
			Iterator it = reciboCajaDetalles.iterator();
			while (it.hasNext()) {
				CarteraDetalleIf reciboCajaDetalle = (CarteraDetalleIf) it.next();
				reciboCajaDetalle.setCarteraId(cartera.getId());
				reciboCajaDetalle.setSaldo(reciboCajaDetalle.getValor());
				reciboCajaDetalle.setObservacion("");
				carteraDetalleList.add(carteraDetalleLocal.addCarteraDetalle(reciboCajaDetalle));							
			}

			parametros.put("ETAPA", 1L);
			AsientoIf asiento = generarAsientosAutomaticos(cartera, carteraDetalleList, parametros, false);
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
		}

		return idCartera;
	}

	private double sumarValoresCarteraDetalleList(List<CarteraDetalleIf> carteraDetalleList) {
		Iterator it = carteraDetalleList.iterator();
		double total = 0D;
		while (it.hasNext()) {
			CarteraDetalleIf detalle = (CarteraDetalleIf) it.next();
			total += detalle.getValor().doubleValue();
		}

		return total;
	}

	public void enviarReciboCajaPos(Vector<Vector> detallesPagos, Map<String, Object> parametros, boolean procesandoPrincipal,CarteraIf cartera, String preimpreso)
	{
		try {
			System.out.println("En enviarReciboCajaPos "+detallesPagos.size());
			System.out.println("preimpreso" + preimpreso);
			System.out.println("cartera valor" + cartera.getValor());
			reciboCajaPOSMessageLocal.setCartera(cartera);
			reciboCajaPOSMessageLocal.setDetallesPagos(detallesPagos);
			reciboCajaPOSMessageLocal.setParametros(parametros);
			reciboCajaPOSMessageLocal.setPreimpreso(preimpreso);
			reciboCajaPOSMessageLocal.sendToPrincipalIfPos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actualizarPreimpreso(CarteraIf cartera, String preimpreso) throws GenericBusinessException {
		try {
			cartera.setPreimpreso(preimpreso);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>> PREIMPRESO >>>>>>>>>>>>>>>>>>>" + preimpreso);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			manager.merge(cartera);
			Map parameterMap = new HashMap();
			parameterMap.put("tipoDocumentoId", cartera.getTipodocumentoId());
			parameterMap.put("transaccionId", cartera.getId());
			Iterator it = asientoLocal.findAsientoByQuery(parameterMap).iterator();
			List<AsientoDetalleIf> asientoDetallesList = null;
			AsientoIf asiento = null;
			if (it.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(cartera.getTipodocumentoId());
				asiento = (AsientoIf) it.next();
				String observacion = tipoDocumento.getCodigo() + ": ";
				asiento.setObservacion(observacion + cartera.getPreimpreso() + " " + cartera.getComentario());
				manager.merge(asiento);
				asientoDetallesList = (ArrayList<AsientoDetalleIf>) asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getId());
				Iterator itDetail = asientoDetallesList.iterator();
				while (itDetail.hasNext()) {
					AsientoDetalleIf detail = (AsientoDetalleIf) itDetail.next();
					detail.setGlosa(observacion + cartera.getPreimpreso() + " " + cartera.getComentario());
					manager.merge(detail);
				}
			}

			actualizarPreimpresoCarteraMessageLocal.setData(
					cartera.getOficinaId(), 
					cartera.getId(), 
					preimpreso,
					cartera.getTipodocumentoId());
			try {
				actualizarPreimpresoCarteraMessageLocal.sendToPrincipalIfPos();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (GenericBusinessException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		}
	}

	public void transferirComprobante(CarteraIf comprobanteOriginal, Map<String,Object> parametrosEmpresa, OficinaIf oficinaOrigen, OficinaIf oficinaDestino, boolean procesandoPrincipal) throws GenericBusinessException {
		try {
			Long idEmpresa = (Long) parametrosEmpresa.get("EMPRESA_ID");
			UsuarioIf usuario = (UsuarioIf) parametrosEmpresa.get("USUARIO");
			TipoDocumentoIf tipoDocumentoTransferencia = null;
			DocumentoIf documentoTransferencia = null;

			// COMPROBANTE DE TRANSFERENCIA / CABECERA
			TipoDocumentoIf tipoDocumentoOriginal = (TipoDocumentoIf) tipoDocumentoLocal.getTipoDocumento(comprobanteOriginal.getTipodocumentoId());
			CarteraEJB comprobanteTransferencia = new CarteraEJB();
			comprobanteTransferencia.setClienteoficinaId(comprobanteOriginal.getClienteoficinaId());
			java.sql.Timestamp now = new java.sql.Timestamp(utilitariosLocal.getServerDateSql().getTime());
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", idEmpresa);
			if (tipoDocumentoOriginal.getTipocartera().equals("C"))
				parameterMap.put("codigo", "TDC");
			else
				parameterMap.put("codigo", "TDP");

			Iterator it = tipoDocumentoLocal.findTipoDocumentoByQuery(parameterMap).iterator();
			if (it.hasNext()) {
				tipoDocumentoTransferencia = (TipoDocumentoIf) it.next();
				comprobanteTransferencia.setTipodocumentoId(tipoDocumentoTransferencia.getId());
			}
			String unNumeroCartera = getNumeroCartera(new java.sql.Date(now.getTime()), tipoDocumentoTransferencia.getCodigo(), idEmpresa, oficinaOrigen.getId());
			comprobanteTransferencia.setCodigo(unNumeroCartera);
			comprobanteTransferencia.setCodigo(comprobanteTransferencia.getCodigo()+ formatoSerial.format(getMaximoNumeroCartera(comprobanteTransferencia.getCodigo())));
			String preimpresoCodigo = (comprobanteOriginal.getPreimpreso() != null && !comprobanteOriginal.getPreimpreso().equals(""))?comprobanteOriginal.getPreimpreso():comprobanteOriginal.getCodigo();
			comprobanteTransferencia.setComentario("TRANSF. " + tipoDocumentoOriginal.getCodigo() + ": " + preimpresoCodigo + " DESDE " + oficinaOrigen.getNombre() + " HACIA " + oficinaDestino.getNombre());
			comprobanteTransferencia.setEstado("N");
			comprobanteTransferencia.setFechaEmision(now);
			comprobanteTransferencia.setMonedaId(comprobanteOriginal.getMonedaId());
			comprobanteTransferencia.setOficinaId(oficinaOrigen.getId());
			comprobanteTransferencia.setTipo(comprobanteOriginal.getTipo());
			comprobanteTransferencia.setUsuarioId(usuario.getId());
			comprobanteTransferencia.setValor(comprobanteOriginal.getValor());
			comprobanteTransferencia.setSaldo(BigDecimal.ZERO);
			CarteraIf transferencia = addCartera(comprobanteTransferencia);

			// COMPROBANTE DE TRANSFERENCIA / DETALLE
			CarteraDetalleEJB comprobanteTransferenciaDetalle = new CarteraDetalleEJB();
			comprobanteTransferenciaDetalle.setCartera("S");
			comprobanteTransferenciaDetalle.setCarteraId(transferencia.getId());
			parameterMap = new HashMap();
			parameterMap.put("tipodocumentoId", tipoDocumentoTransferencia.getId());
			if (tipoDocumentoOriginal.getTipocartera().equals("C"))
				parameterMap.put("codigo", "TRDC");
			else
				parameterMap.put("codigo", "TRDP");

			it = documentoLocal.findDocumentoByQuery(parameterMap).iterator();
			if (it.hasNext()) {
				documentoTransferencia = (DocumentoIf) it.next();
				comprobanteTransferenciaDetalle.setDocumentoId(documentoTransferencia.getId());
			}
			comprobanteTransferenciaDetalle.setFechaCartera(utilitariosLocal.fromTimestampToSqlDate(comprobanteTransferencia.getFechaEmision()));
			comprobanteTransferenciaDetalle.setFechaCreacion(utilitariosLocal.fromTimestampToSqlDate(comprobanteTransferencia.getFechaEmision()));
			comprobanteTransferenciaDetalle.setSecuencial(1);
			comprobanteTransferenciaDetalle.setValor(comprobanteTransferencia.getValor());
			comprobanteTransferenciaDetalle.setSaldo(BigDecimal.ZERO);
			CarteraDetalleIf transferenciaDetalle = carteraDetalleLocal.addCarteraDetalle(comprobanteTransferenciaDetalle);

			List<CarteraDetalleIf> comprobanteOriginalDetalleList = (ArrayList<CarteraDetalleIf>) carteraDetalleLocal.findCarteraDetalleByCarteraId(comprobanteOriginal.getId());
			it = comprobanteOriginalDetalleList.iterator();
			while (it.hasNext()) {
				CarteraDetalleIf comprobanteOriginalDetalle = (CarteraDetalleIf) it.next();
				CarteraAfectaEJB transferenciaAfecta = new CarteraAfectaEJB();
				transferenciaAfecta.setCarteradetalleId(comprobanteOriginalDetalle.getId());
				transferenciaAfecta.setCarteraafectaId(transferenciaDetalle.getId());
				transferenciaAfecta.setUsuarioId(usuario.getId());
				transferenciaAfecta.setValor(comprobanteOriginalDetalle.getSaldo());
				transferenciaAfecta.setFechaCreacion(utilitariosLocal.fromTimestampToSqlDate(comprobanteTransferencia.getFechaEmision()));
				transferenciaAfecta.setFechaAplicacion(utilitariosLocal.fromTimestampToSqlDate(comprobanteTransferencia.getFechaEmision()));
				transferenciaAfecta.setCartera("S");
				carteraAfectaLocal.addCarteraAfecta(transferenciaAfecta);
				comprobanteOriginalDetalle.setSaldo(BigDecimal.ZERO);
				carteraDetalleLocal.saveCarteraDetalle(comprobanteOriginalDetalle);
			}

			comprobanteOriginal.setEstado(ESTADO_TRANSFERIDO);
			comprobanteOriginal.setSaldo(BigDecimal.ZERO);
			saveCartera(comprobanteOriginal);

			// REVERSAR ASIENTO COMPROBANTE ORIGINAL
			transferenciaDocumentoAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
			AsientoIf asiento = null;
			int i = 0;
			parametrosEmpresa.put("OFICINA_ID", oficinaOrigen.getId());
			for (CarteraDetalleIf comprobanteDetalle : comprobanteOriginalDetalleList) {

				if (i != comprobanteOriginalDetalleList.size() - 1) {
					asiento = generarAsientoAutomaticoTransferenciaDocumento(comprobanteOriginal, comprobanteDetalle, transferencia, false, parametrosEmpresa);
				} else if (i == comprobanteOriginalDetalleList.size() - 1) {
					asiento = generarAsientoAutomaticoTransferenciaDocumento(comprobanteOriginal, comprobanteDetalle, transferencia, true, parametrosEmpresa);
				}
				i++;
			}

			if (procesandoPrincipal) {
				// NUEVO COMPROBANTE
				CarteraIf comprobanteNuevo = registrarCartera(comprobanteOriginal);
				comprobanteNuevo.setId(null);
				now = new java.sql.Timestamp(utilitariosLocal.getServerDateSql().getTime());
				unNumeroCartera = getNumeroCartera(new java.sql.Date(now.getTime()), "RCA", idEmpresa, oficinaDestino.getId());
				comprobanteNuevo.setCodigo(unNumeroCartera);
				comprobanteNuevo.setCodigo(comprobanteNuevo.getCodigo()+ formatoSerial.format(getMaximoNumeroCartera(comprobanteNuevo.getCodigo())));
				comprobanteNuevo.setFechaEmision(comprobanteTransferencia.getFechaEmision());
				comprobanteNuevo.setOficinaId(oficinaDestino.getId());
				comprobanteNuevo.setEstado(ESTADO_NORMAL);
				comprobanteNuevo.setSaldo(comprobanteNuevo.getValor());
				CarteraIf comprobante = addCartera(comprobanteNuevo);
				List<CarteraDetalleIf> comprobanteDetalleList = new ArrayList<CarteraDetalleIf>();
				it = comprobanteOriginalDetalleList.iterator();
				while (it.hasNext()) {
					CarteraDetalleIf comprobanteOriginalDetalle = registrarCarteraDetalle((CarteraDetalleIf) it.next(), false);
					comprobanteOriginalDetalle.setId(null);
					comprobanteOriginalDetalle.setCarteraId(comprobante.getId());
					comprobanteOriginalDetalle.setFechaCreacion(new java.sql.Date(now.getTime()));
					comprobanteOriginalDetalle.setFechaCartera(utilitariosLocal.fromTimestampToSqlDate(comprobante.getFechaEmision()));
					comprobanteOriginalDetalle.setFechaVencimiento(utilitariosLocal.fromTimestampToSqlDate(comprobante.getFechaEmision()));
					comprobanteOriginalDetalle.setFechaUltimaActualizacion(utilitariosLocal.fromTimestampToSqlDate(comprobante.getFechaEmision()));
					comprobanteDetalleList.add(carteraDetalleLocal.addCarteraDetalle(comprobanteOriginalDetalle));
				}

				// PROCESAR NUEVO COMPROBANTE: MENSAJERÍA, ASIENTO
				reciboCajaAsientoAutomaticoHandlerLocal.setAsientoDetalleColeccion(new ArrayList<AsientoDetalleIf>());
				asiento = null;
				i = 0;
				parametrosEmpresa.put("OFICINA_ID", oficinaDestino.getId());
				for (CarteraDetalleIf comprobanteDetalle : comprobanteDetalleList) {

					if (i != comprobanteDetalleList.size() - 1) {
						asiento = generarAsientoAutomaticoReciboCaja(comprobante, comprobanteDetalle, false, parametrosEmpresa);
					} else if (i == comprobanteDetalleList.size() - 1) {
						asiento = generarAsientoAutomaticoReciboCaja(comprobante, comprobanteDetalle, true, parametrosEmpresa);
					}
					i++;
				}

				actualizarPreimpreso(comprobante, comprobanteOriginal.getPreimpreso());
			} else {
				transferirDocPosMessageLocal.setComprobanteOriginal(comprobanteOriginal);
				transferirDocPosMessageLocal.setParametrosEmpresa(parametrosEmpresa);
				transferirDocPosMessageLocal.setOficinaOrigen(oficinaOrigen);
				transferirDocPosMessageLocal.setOficinaDestino(oficinaDestino);
				transferirDocPosMessageLocal.sendToPrincipalIfPos();
			}
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
		}
	}
	
	public WalletData processWallet(WalletData walletData, Vector<WalletDetailData> walletDetailDataVector, Vector<CrossingWalletDetailData> crossingWalletDetailsVector, Vector<WalletDetailData> deletedWalletDetailDataVector, boolean update, boolean updateOriginalSaveModeAccountingEntry, DocumentoIf levelingDocument, DocumentoIf advancePaymentDocument, CarteraRelacionadaEJB relatedWallet) throws Exception, GenericBusinessException {
		try {
			CarteraEJB wallet = registerWallet(walletData);
			try {
				if (update) {
					wallet.setId(walletData.getWalletId());
					manager.merge(wallet);
				} else {
					wallet.setCodigo(getWalletCodeNumber(walletData));
					manager.persist(wallet);
				}
				walletData.setWalletId(wallet.getPrimaryKey());
				walletData.setWalletCodeNumber(wallet.getCodigo());
				/*if (update && walletData.getWalletType().equals(SpiritConstants.getProviderWalletType().substring(0,1))) {
					Map<String, Object> queryMap = new HashMap<String, Object>();
					queryMap.put("transaccionId", walletData.getWalletId());
					queryMap.put("origen", SpiritConstants.getIssuedCheckWalletOrigin().substring(0,1));
					Iterator<ChequeEmitidoIf> issuedCheckIterator = chequeEmitidoLocal.findChequeEmitidoByQuery(queryMap).iterator();
					while (issuedCheckIterator.hasNext()) {
						ChequeEmitidoIf issuedCheck = issuedCheckIterator.next();
						if (isUpdatedIssuedCheck(issuedCheck, walletDetailDataVector))
							chequeEmitidoLocal.deleteIssuedCheck(issuedCheck, false);
					}
				}*/
				if (update && walletData.getWalletType().equals(SpiritConstants.getProviderWalletType().substring(0,1))) {
					Map<String, Object> queryMap = new HashMap<String, Object>();
					queryMap.put("transaccionId", walletData.getWalletId());
					queryMap.put("origen", SpiritConstants.getIssuedCheckWalletOrigin().substring(0,1));
					Iterator<ChequeEmitidoIf> issuedCheckIterator = chequeEmitidoLocal.findChequeEmitidoByQuery(queryMap).iterator();
					while (issuedCheckIterator.hasNext()) {
						ChequeEmitidoIf issuedCheck = issuedCheckIterator.next();
						if (!issuedCheck.getEstado().equals(SpiritConstants.getCanceledCheck().substring(0,1)))
							chequeEmitidoLocal.deleteIssuedCheck(issuedCheck, true);
					}
				}
				for (int i=0; i<walletDetailDataVector.size(); i++) {
					WalletDetailData walletDetailData = walletDetailDataVector.get(i);
					CarteraDetalleEJB walletDetail = carteraDetalleLocal.registerWalletDetail(walletDetailData);
					walletDetail.setCarteraId(wallet.getPrimaryKey());
					if (update) {
						walletDetail.setId(walletDetailData.getWalletDetailId());
						manager.merge(walletDetail);
					} else {
						manager.persist(walletDetail);
					}
					walletDetailData.setWalletDetailId(walletDetail.getPrimaryKey());
					if (walletDetailData.getDocument().getCheque().equals(SpiritConstants.getOptionYes().substring(0,1)) && walletData.getWalletType().equals(SpiritConstants.getProviderWalletType().substring(0,1)))
						chequeEmitidoLocal.processIssuedCheck(walletData, walletDetailData, update);
				}
				for (int i=0; i<crossingWalletDetailsVector.size(); i++) {
					CrossingWalletDetailData crossingWalletDetailData = crossingWalletDetailsVector.get(i);
					Map<String, Object> queryMap = new HashMap<String, Object>();
					queryMap.put("carteraId", wallet.getPrimaryKey());
					queryMap.put("secuencial", crossingWalletDetailData.getWalletDetailData().getSequentialNumber());
					Iterator<CarteraDetalleEJB> it = carteraDetalleLocal.findCarteraDetalleByQuery(queryMap).iterator();
					if (it.hasNext()) {
						CarteraDetalleEJB walletDetail = it.next();
						java.sql.Date applyingDate = utilitariosLocal.fromUtilDateToSqlDate(crossingWalletDetailData.getApplyingDate());
						BigDecimal valueToApply = crossingWalletDetailData.getValueToApply();
						if (valueToApply.doubleValue() > 0D) {
							PendingAccountData pendingAccountDetailData = crossingWalletDetailData.getPendingAccountDetailData();
							CarteraEJB pendingAccountWallet = manager.find(CarteraEJB.class, pendingAccountDetailData.getPendingAccountWallet().getId());
							pendingAccountWallet.setSaldo(pendingAccountWallet.getSaldo().add(valueToApply.negate()));
							manager.merge(pendingAccountWallet);
							CarteraDetalleEJB pendingAccountWalletDetail = manager.find(CarteraDetalleEJB.class, pendingAccountDetailData.getPendingAccountWalletDetail().getId());
							pendingAccountWalletDetail.setSaldo(pendingAccountWalletDetail.getSaldo().add(valueToApply.negate()));
							manager.merge(pendingAccountWalletDetail);
							CarteraAfectaEJB crossingData = new CarteraAfectaEJB();
							DocumentoIf walletDetailDocument = documentoLocal.getDocumento(walletDetail.getDocumentoId());
							if (!walletDetailDocument.getNivelacion().equals(SpiritConstants.getOptionYes().substring(0,1))) {
								crossingData.setCarteraafectaId(pendingAccountWalletDetail.getPrimaryKey());
								crossingData.setCarteradetalleId(walletDetail.getPrimaryKey());
							} else {
								crossingData.setCarteraafectaId(walletDetail.getPrimaryKey());
								crossingData.setCarteradetalleId(pendingAccountWalletDetail.getPrimaryKey());
							}
							crossingData.setUsuarioId(wallet.getUsuarioId());
							crossingData.setValor(valueToApply);
							crossingData.setFechaCreacion(utilitariosLocal.fromTimestampToSqlDate(wallet.getFechaCreacion()));
							crossingData.setFechaAplicacion(applyingDate);
							crossingData.setCartera(SpiritConstants.getOptionYes().substring(0,1));
							manager.persist(crossingData);
						}
					}
				}
				for (int i=0; i<deletedWalletDetailDataVector.size(); i++) {
					WalletDetailData deletedWalletDetailData = deletedWalletDetailDataVector.get(i);
					CarteraDetalleIf deletedWalletDetail = manager.find(CarteraDetalleEJB.class, deletedWalletDetailData.getWalletDetailId());
					manager.remove(deletedWalletDetail);
					/*if (walletData.getWalletType().equals(SpiritConstants.getProviderWalletType().substring(0,1)) && deletedWalletDetailData.getDocument().getCheque().equals(SpiritConstants.getOptionYes().substring(0,1)))
						chequeEmitidoLocal.deleteIssuedCheck(walletData, deletedWalletDetailData, true);*/
				}
			} catch (GenericBusinessException gbe) {
				throw new GenericBusinessException(gbe.getMessage());
			}
			AsientoIf accountingEntry = null;
			automaticAccountingEntryHandlerLocal.setAccountingEntryDetailList(new ArrayList<AsientoDetalleIf>());
			java.util.Date originalWalletEmissionDate = walletData.getEmissionDate();
			for (int i=0; i<walletDetailDataVector.size(); i++) {
				WalletDetailData walletDetailData = walletDetailDataVector.get(i);
				if (i != walletDetailDataVector.size() - 1)
					accountingEntry = automaticAccountingEntryHandlerLocal.generateAutomaticAccountingEntry(walletData, walletDetailData, crossingWalletDetailsVector, false, false, update);
				else if (i == walletDetailDataVector.size() - 1)
					accountingEntry = automaticAccountingEntryHandlerLocal.generateAutomaticAccountingEntry(walletData, walletDetailData, crossingWalletDetailsVector, true, true, update);
			}
			walletData.setEmissionDate(originalWalletEmissionDate);
			if (updateOriginalSaveModeAccountingEntry) {
				Map<String, Object> queryMap = new HashMap<String, Object>();
				queryMap.put("tipoDocumentoId", walletData.getDocumentType().getId());
				queryMap.put("transaccionId", walletData.getWalletId());
				Iterator<AsientoIf> originalAccountingEntryIterator = asientoLocal.findAsientoByQuery(queryMap).iterator();
				AsientoIf originalAccountingEntry = null;
				List<AsientoDetalleIf> originalAccountingEntryDetailList = new ArrayList<AsientoDetalleIf>();
				if (originalAccountingEntryIterator.hasNext()) {
					originalAccountingEntry = originalAccountingEntryIterator.next();
					EventoContableIf originalAccountingEvent = eventoContableLocal.getEventoContable(originalAccountingEntry.getEventoContableId());
					if (originalAccountingEvent.getValidoAlGuardar().equals(SpiritConstants.getOptionYes().substring(0,1)))
						update = false;
					else if	(originalAccountingEvent.getValidoAlActualizar().equals(SpiritConstants.getOptionYes().substring(0,1)))
						update = true;
					originalAccountingEntryDetailList = (ArrayList<AsientoDetalleIf>) asientoDetalleLocal.findAsientoDetalleByAsientoId(originalAccountingEntry.getId());
					AsientoIf updatedAccountingEntry = null;
					automaticAccountingEntryHandlerLocal.setAccountingEntryDetailList(new ArrayList<AsientoDetalleIf>());
					for (int i=0; i<walletDetailDataVector.size(); i++) {
						WalletDetailData walletDetailData = walletDetailDataVector.get(i);
						updatedAccountingEntry = automaticAccountingEntryHandlerLocal.generateAutomaticAccountingEntry(walletData, walletDetailData, crossingWalletDetailsVector, true, false, update);
					}
					updatedAccountingEntry.setId(originalAccountingEntry.getId());
					boolean reverseAccountBalances = (originalAccountingEntry != null && originalAccountingEntry.getStatus().equals(SpiritConstants.getAuthorizedAccountingEntry().substring(0,1)))?true:false;
					boolean updateAccountingEntryNumber = true;
					java.sql.Date originalAccountingEntryDate = new java.sql.Date(originalAccountingEntry.getFecha().getTime());
					int originalAccountingEntryYearDate = originalAccountingEntryDate.getYear() + 1900;
					int originalAccountingEntryMonthDate = originalAccountingEntryDate.getMonth() + 1;
					java.sql.Date updatedAccountingEntryDate = new java.sql.Date(updatedAccountingEntry.getFecha().getTime());
					int updatedAccountingEntryYearDate = updatedAccountingEntryDate.getYear() + 1900;
					int updatedAccountingEntryMonthDate = updatedAccountingEntryDate.getMonth() + 1;
					if (originalAccountingEntryYearDate == updatedAccountingEntryYearDate && originalAccountingEntryMonthDate == updatedAccountingEntryMonthDate) {
						updatedAccountingEntry.setNumero(originalAccountingEntry.getNumero());
						updateAccountingEntryNumber = false;
					}
					asientoLocal.actualizarAsiento(updatedAccountingEntry, automaticAccountingEntryHandlerLocal.getAccountingEntryDetailList(), originalAccountingEntry, originalAccountingEntryDetailList, originalAccountingEntryDetailList, reverseAccountBalances, updateAccountingEntryNumber, walletData.getUser(), new HashMap(), new HashMap(), new HashMap(), new HashMap(), false);
				}
			}
			if (relatedWallet != SpiritConstants.getNullValue()) {
				relatedWallet.setCarteraRelacionadaId(wallet.getPrimaryKey());
				manager.persist(relatedWallet);
			}
			if (levelingDocument != SpiritConstants.getNullValue())
				processLevelingWallet(walletData, walletDetailDataVector, levelingDocument, wallet);
			if (advancePaymentDocument != SpiritConstants.getNullValue())
				processAdvancePaymentWallet(walletData, walletDetailDataVector, advancePaymentDocument, wallet);
			if (levelingDocument != SpiritConstants.getNullValue())
				walletData.setBalance(BigDecimal.ZERO);
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		return walletData;
	}
	
	private boolean isUpdatedIssuedCheck(ChequeEmitidoIf issuedCheck, Vector<WalletDetailData> walletDetailDataVector) {
		for (int i=0; i<walletDetailDataVector.size(); i++) {
			WalletDetailData walletDetailData = walletDetailDataVector.get(i);
			if (issuedCheck.getCuentaBancariaId().compareTo(walletDetailData.getCheckAccount().getId()) == 0 && issuedCheck.getNumero().equals(walletDetailData.getCheckNumber()) && issuedCheck.getValor().doubleValue() == walletDetailData.getValue().doubleValue())
				return false;
		}		
		return true;
	}

	private void processLevelingWallet(WalletData walletData, Vector<WalletDetailData> walletDetailDataVector, DocumentoIf levelingDocument, CarteraEJB wallet) throws GenericBusinessException, Exception {
		WalletData levelingWalletData = new WalletData();
		levelingWalletData.setEnterprise(walletData.getEnterprise());
		levelingWalletData.setOffice(walletData.getOffice());
		levelingWalletData.setUser(walletData.getUser());
		levelingWalletData.setWalletType(walletData.getWalletType());
		levelingWalletData.setBusinessOperator(walletData.getBusinessOperator());
		levelingWalletData.setBusinessOperatorOffice(walletData.getBusinessOperatorOffice());
		levelingWalletData.setEmissionDate(walletData.getEmissionDate());
		levelingWalletData.setCreationDate(walletData.getCreationDate());
		levelingWalletData.setLastUpdateDate(walletData.getLastUpdateDate());
		levelingWalletData.setDocumentType(tipoDocumentoLocal.getTipoDocumento(levelingDocument.getTipoDocumentoId()));
		levelingWalletData.setCurrency(walletData.getCurrency());
		levelingWalletData.setStatus(walletData.getStatus());
		levelingWalletData.setComment(walletData.getComment());
		levelingWalletData.setTotal(walletData.getBalance());
		levelingWalletData.setBalance(BigDecimal.ZERO);
		levelingWalletData.setActivateRetrocompatibility(SpiritConstants.getOptionNo().substring(0,1));
		
		Vector<WalletDetailData> levelingWalletDetailDataVector = new Vector<WalletDetailData>();
		WalletDetailData levelingWalletDetailData = new WalletDetailData();
		levelingWalletDetailData.setSequentialNumber(BigDecimal.ONE.intValue());
		levelingWalletDetailData.setDocument(levelingDocument);
		levelingWalletDetailData.setValue(walletData.getBalance());
		levelingWalletDetailData.setBalance(BigDecimal.ZERO);
		levelingWalletDetailData.setComment(levelingDocument.getAbreviado() + SpiritConstants.getBlankSpaceCharacter() + "REF." + SpiritConstants.getBlankSpaceCharacter() + walletData.getWalletCodeNumber());
		levelingWalletDetailDataVector.add(levelingWalletDetailData);
		
		Vector<CrossingWalletDetailData> levelingCrossingWalletDetailDataVector = new Vector<CrossingWalletDetailData>();
		for (int i=0; i<walletDetailDataVector.size(); i++) {
			WalletDetailData walletDetailData = walletDetailDataVector.get(i);
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("carteraId", wallet.getPrimaryKey());
			queryMap.put("secuencial", walletDetailData.getSequentialNumber());
			Iterator<CarteraDetalleEJB> it = carteraDetalleLocal.findCarteraDetalleByQuery(queryMap).iterator();
			CarteraDetalleIf walletDetail = it.next();
			if (walletDetail.getSaldo().doubleValue() > 0D) {
				CrossingWalletDetailData levelingCrossingWalletDetailData = new CrossingWalletDetailData();
				levelingCrossingWalletDetailData.setApplyingDate(walletData.getEmissionDate());
				PendingAccountData levelingPendingAccountDetailData = new PendingAccountData();
				levelingPendingAccountDetailData.setBusinessOperator(walletData.getBusinessOperator());
				levelingPendingAccountDetailData.setDocument(walletDetailData.getDocument());
				levelingPendingAccountDetailData.setDocumentType(walletData.getDocumentType());
				levelingPendingAccountDetailData.setPendingAccountWallet(wallet);
				levelingPendingAccountDetailData.setPendingAccountWalletDetail(walletDetail);
				levelingCrossingWalletDetailData.setPendingAccountDetailData(levelingPendingAccountDetailData);
				levelingCrossingWalletDetailData.setValueToApply(walletData.getBalance());
				levelingCrossingWalletDetailData.setWalletDetailData(levelingWalletDetailData);
				levelingCrossingWalletDetailDataVector.add(levelingCrossingWalletDetailData);
			}
		}
		
		CarteraRelacionadaEJB relatedWallet = new CarteraRelacionadaEJB();
		relatedWallet.setCarteraOrigenId(wallet.getPrimaryKey());
		processWallet(levelingWalletData, levelingWalletDetailDataVector, levelingCrossingWalletDetailDataVector, new Vector<WalletDetailData>(), false, false, (DocumentoIf) SpiritConstants.getNullValue(), (DocumentoIf) SpiritConstants.getNullValue(), relatedWallet);
	}
	
	private void processAdvancePaymentWallet(WalletData walletData, Vector<WalletDetailData> walletDetailDataVector, DocumentoIf advancePaymentDocument, CarteraEJB wallet) throws GenericBusinessException, Exception {
		WalletData advancePaymentWalletData = new WalletData();
		advancePaymentWalletData.setEnterprise(walletData.getEnterprise());
		advancePaymentWalletData.setOffice(walletData.getOffice());
		advancePaymentWalletData.setUser(walletData.getUser());
		advancePaymentWalletData.setWalletType(walletData.getWalletType());
		advancePaymentWalletData.setBusinessOperator(walletData.getBusinessOperator());
		advancePaymentWalletData.setBusinessOperatorOffice(walletData.getBusinessOperatorOffice());
		advancePaymentWalletData.setEmissionDate(walletData.getEmissionDate());
		advancePaymentWalletData.setCreationDate(walletData.getCreationDate());
		advancePaymentWalletData.setLastUpdateDate(walletData.getLastUpdateDate());
		advancePaymentWalletData.setDocumentType(tipoDocumentoLocal.getTipoDocumento(advancePaymentDocument.getTipoDocumentoId()));
		advancePaymentWalletData.setCurrency(walletData.getCurrency());
		advancePaymentWalletData.setStatus(walletData.getStatus());
		advancePaymentWalletData.setComment(walletData.getComment());
		advancePaymentWalletData.setTotal(walletData.getBalance());
		advancePaymentWalletData.setBalance(walletData.getBalance());
		advancePaymentWalletData.setActivateRetrocompatibility(SpiritConstants.getOptionNo().substring(0,1));
		
		Vector<WalletDetailData> advancePaymentWalletDetailDataVector = new Vector<WalletDetailData>();
		WalletDetailData advancePaymentWalletDetailData = new WalletDetailData();
		advancePaymentWalletDetailData.setSequentialNumber(BigDecimal.ONE.intValue());
		advancePaymentWalletDetailData.setDocument(advancePaymentDocument);
		advancePaymentWalletDetailData.setValue(walletData.getBalance());
		advancePaymentWalletDetailData.setBalance(walletData.getBalance());
		advancePaymentWalletDetailData.setComment(advancePaymentDocument.getAbreviado() + SpiritConstants.getBlankSpaceCharacter() + "REF." + SpiritConstants.getBlankSpaceCharacter() + walletData.getWalletCodeNumber());
		advancePaymentWalletDetailDataVector.add(advancePaymentWalletDetailData);
		
		CarteraRelacionadaEJB relatedWallet = new CarteraRelacionadaEJB();
		relatedWallet.setCarteraOrigenId(wallet.getPrimaryKey());
		processWallet(advancePaymentWalletData, advancePaymentWalletDetailDataVector, new Vector<CrossingWalletDetailData>(), new Vector<WalletDetailData>(), false, false, (DocumentoIf) SpiritConstants.getNullValue(), (DocumentoIf) SpiritConstants.getNullValue(), relatedWallet);
	}

	public void nullifyWallet(WalletData walletData, Vector<WalletDetailData> walletDetailDataVector) throws Exception, GenericBusinessException {
		Iterator<CarteraRelacionadaIf> relatedWalletIterator = relatedWalletSessionLocal.findCarteraRelacionadaByCarteraOrigenId(walletData.getWalletId()).iterator();
		while (relatedWalletIterator.hasNext()) {
			CarteraRelacionadaIf relatedWallet = relatedWalletIterator.next();
			CarteraIf related = getCartera(relatedWallet.getCarteraRelacionadaId());
			WalletData relatedWalletData = new WalletData();
			relatedWalletData.setWalletId(related.getId());
			relatedWalletData.setWalletCodeNumber(related.getCodigo());
			relatedWalletData.setBusinessOperator(walletData.getBusinessOperator());
			relatedWalletData.setBusinessOperatorOffice(walletData.getBusinessOperatorOffice());
			relatedWalletData.setWalletType(related.getTipo());
			relatedWalletData.setEmissionDate(related.getFechaEmision());
			relatedWalletData.setCreationDate(related.getFechaCreacion());
			relatedWalletData.setLastUpdateDate(related.getFechaUltimaActualizacion());
			relatedWalletData.setEnterprise(walletData.getEnterprise());
			relatedWalletData.setOffice(walletData.getOffice());
			relatedWalletData.setUser(walletData.getUser());
			relatedWalletData.setStatus(walletData.getStatus());
			
			relatedWalletData.setDocumentType(tipoDocumentoLocal.getTipoDocumento(related.getTipodocumentoId()));
			relatedWalletData.setCurrency(walletData.getCurrency());
			relatedWalletData.setComment(related.getComentario());
			relatedWalletData.setTotal(related.getValor());
			relatedWalletData.setBalance(related.getSaldo());
			Iterator<CarteraDetalleIf> relatedDetailIterator = carteraDetalleLocal.findCarteraDetalleByCarteraId(related.getId()).iterator();
			Vector<WalletDetailData> relatedWalletDetailDataVector = new Vector<WalletDetailData>();
			while (relatedDetailIterator.hasNext()) {
				CarteraDetalleIf relatedDetail = relatedDetailIterator.next();
				WalletDetailData relatedWalletDetailData = new WalletDetailData();
				relatedWalletDetailData.setWalletDetailId(relatedDetail.getId());
				relatedWalletDetailData.setDocument(documentoLocal.getDocumento(relatedDetail.getDocumentoId()));
				relatedWalletDetailData.setSequentialNumber(relatedDetail.getSecuencial());
				relatedWalletDetailData.setValue(relatedDetail.getValor());
				relatedWalletDetailData.setBalance(relatedDetail.getSaldo());
				relatedWalletDetailDataVector.add(relatedWalletDetailData);
			}
			nullifyWallet(relatedWalletData, relatedWalletDetailDataVector);
		}
		CarteraEJB wallet = registerWallet(walletData);
		wallet.setId(walletData.getWalletId());
		wallet.setSaldo(wallet.getValor());
		manager.merge(wallet);
		List<CarteraDetalleIf> walletDetailList = new ArrayList<CarteraDetalleIf>();
		for (int i=0; i<walletDetailDataVector.size(); i++) {
			WalletDetailData walletDetailData = walletDetailDataVector.get(i);
			CarteraDetalleEJB walletDetail = carteraDetalleLocal.registerWalletDetail(walletDetailData);
			walletDetail.setId(walletDetailDataVector.get(i).getWalletDetailId());
			walletDetail.setCarteraId(wallet.getPrimaryKey());
			walletDetail.setSaldo(walletDetail.getValor());
			manager.merge(walletDetail);
			//TRAP
			walletDetailList.add(walletDetail);
			List<CarteraAfectaIf> crossingWalletDetailList = new ArrayList<CarteraAfectaIf>();
			if (walletData.getDocumentType().getSignocartera().equals(SpiritConstants.getMinusOperator()))
				crossingWalletDetailList = (ArrayList<CarteraAfectaIf>) carteraAfectaLocal.findCarteraAfectaByCarteradetalleId(walletDetail.getId());
			else
				crossingWalletDetailList = (ArrayList<CarteraAfectaIf>) carteraAfectaLocal.findCarteraAfectaByCarteraafectaId(walletDetail.getId());
			for (int j=0; j<crossingWalletDetailList.size(); j++) {
				CarteraAfectaIf crossingWalletDetail = crossingWalletDetailList.get(j);
				CarteraDetalleIf applyingWalletDetail = (walletData.getDocumentType().getSignocartera().equals(SpiritConstants.getMinusOperator()))?carteraDetalleLocal.getCarteraDetalle(crossingWalletDetail.getCarteraafectaId()):carteraDetalleLocal.getCarteraDetalle(crossingWalletDetail.getCarteradetalleId());
				applyingWalletDetail.setSaldo(applyingWalletDetail.getSaldo().add(crossingWalletDetail.getValor()));
				manager.merge(applyingWalletDetail);
				CarteraIf applyingWallet = getCartera(applyingWalletDetail.getCarteraId());
				applyingWallet.setSaldo(applyingWallet.getSaldo().add(crossingWalletDetail.getValor()));
				manager.merge(applyingWallet);
				manager.remove(crossingWalletDetail);
			}
			if (walletData.getWalletType().equals(SpiritConstants.getProviderWalletType().substring(0,1)) && walletDetailData.getDocument().getCheque().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				Map<String, Object> queryMap = new HashMap<String, Object>();
				queryMap.put("cuentaBancariaId", walletDetailData.getCheckAccount().getId());
				queryMap.put("numero", walletDetailData.getCheckNumber());
				queryMap.put("transaccionId", walletData.getWalletId());
				queryMap.put("origen", SpiritConstants.getIssuedCheckWalletOrigin().substring(0,1));
				Iterator<ChequeEmitidoIf> issuedCheckIterator = chequeEmitidoLocal.findChequeEmitidoByQuery(queryMap).iterator();
				while (issuedCheckIterator.hasNext()) {
					ChequeEmitidoIf issuedCheck = issuedCheckIterator.next();
					issuedCheck.setEstado(walletData.getStatus());
					manager.merge(issuedCheck);
				}
			}
		}
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("tipoDocumentoId", walletData.getDocumentType().getId());
		if (walletData.getDocumentType().getNotaCredito().equals("S")) {
			if (walletData.getReferenceId() != SpiritConstants.getNullValue()) {
				queryMap.put("id", walletData.getReferenceId());
				Iterator<NotaCreditoIf> creditMemoIterator = notaCreditoLocal.findNotaCreditoByQuery(queryMap).iterator();
				if (creditMemoIterator.hasNext()) {
					NotaCreditoIf creditMemo = creditMemoIterator.next();
					creditMemo.setEstado(NOMBRE_ESTADO_ANULADO.substring(1,2));
					manager.merge(creditMemo);
					wallet.setPreimpreso(creditMemo.getPreimpreso());
					manager.merge(wallet);
				}
				queryMap.remove("id");
				queryMap.put("transaccionId", walletData.getReferenceId());
			} else
				// ONLY IN SPECIAL CASE OF INTERNAL CREDIT MEMO
				queryMap.put("transaccionId", walletData.getWalletId());
		} else
			queryMap.put("transaccionId", walletData.getWalletId());
		Iterator<AsientoIf> originalAccountingEntryIterator = asientoLocal.findAsientoByQuery(queryMap).iterator();
		boolean update = true;
		while (originalAccountingEntryIterator.hasNext()) {
			AsientoIf originalAccountingEntry = originalAccountingEntryIterator.next();
			EventoContableIf originalAccountingEvent = eventoContableLocal.getEventoContable(originalAccountingEntry.getEventoContableId());
			if (originalAccountingEvent.getValidoAlGuardar().equals(SpiritConstants.getOptionYes().substring(0,1)))
				update = false;
			else if	(originalAccountingEvent.getValidoAlActualizar().equals(SpiritConstants.getOptionYes().substring(0,1)))
				update = true;
			List<AsientoDetalleIf> originalAccountingEntryDetailList = (ArrayList<AsientoDetalleIf>) asientoDetalleLocal.findAsientoDetalleByAsientoId(originalAccountingEntry.getId());
			AsientoIf updatedAccountingEntry = (AsientoIf) DeepCopy.copy(originalAccountingEntry);
			List<AsientoDetalleIf> updatedAccountingEntryDetailList = (ArrayList<AsientoDetalleIf>) DeepCopy.copy(originalAccountingEntryDetailList);		
			for (int i=0; i<originalAccountingEntryDetailList.size(); i++) {
				AsientoDetalleIf reverseUpdatedAccountingEntryDetail = (AsientoDetalleIf) DeepCopy.copy(originalAccountingEntryDetailList.get(i));
				reverseUpdatedAccountingEntryDetail.setId((Long) SpiritConstants.getNullValue());
				double debit = reverseUpdatedAccountingEntryDetail.getDebe().doubleValue();
				double credit = reverseUpdatedAccountingEntryDetail.getHaber().doubleValue();
				reverseUpdatedAccountingEntryDetail.setDebe(BigDecimal.valueOf(credit));
				reverseUpdatedAccountingEntryDetail.setHaber(BigDecimal.valueOf(debit));
				reverseUpdatedAccountingEntryDetail.setGlosa("POR ANULACIÓN DE COMPROBANTE: " + walletData.getWalletCodeNumber() + SpiritConstants.getBlankSpaceCharacter() + walletData.getBusinessOperator().getRazonSocial());
				updatedAccountingEntryDetailList.add(reverseUpdatedAccountingEntryDetail);
			}
			boolean reverseAccountBalances = (originalAccountingEntry != null && originalAccountingEntry.getStatus().equals(SpiritConstants.getAuthorizedAccountingEntry().substring(0,1)))?true:false;
			boolean updateAccountingEntryNumber = false;
			asientoLocal.actualizarAsiento(updatedAccountingEntry, updatedAccountingEntryDetailList, originalAccountingEntry, originalAccountingEntryDetailList, new ArrayList<AsientoDetalleIf>(), reverseAccountBalances, updateAccountingEntryNumber, walletData.getUser(), new HashMap(), new HashMap(), new HashMap(), new HashMap(), false);
		}
		
		manager.flush();
	}
	
	
	
	private CarteraEJB registerWallet(WalletData walletData) {
		CarteraEJB wallet = new CarteraEJB();
		//wallet.setId(id);
		wallet.setTipo(walletData.getWalletType());
		wallet.setCodigo(walletData.getWalletCodeNumber());
		wallet.setFechaEmision(utilitariosLocal.fromUtilDateToTimestamp(walletData.getEmissionDate()));
		wallet.setFechaCreacion(utilitariosLocal.fromUtilDateToTimestamp(walletData.getCreationDate()));
		wallet.setFechaUltimaActualizacion((walletData.getLastUpdateDate()!=null)?utilitariosLocal.fromUtilDateToTimestamp(walletData.getLastUpdateDate()):null);
		wallet.setOficinaId(walletData.getOffice().getId());
		wallet.setUsuarioId(walletData.getUser().getId());
		//wallet.setVendedorId(vendedorId);
		wallet.setTipodocumentoId(walletData.getDocumentType().getId());
		wallet.setReferenciaId(walletData.getReferenceId());
		wallet.setClienteoficinaId(walletData.getBusinessOperatorOffice().getId());
		wallet.setMonedaId(walletData.getCurrency().getId());
		wallet.setEstado(walletData.getStatus());
		wallet.setComentario(walletData.getComment());
		//wallet.setAprobado(aprobado);
		wallet.setValor(utilitariosLocal.redondeoValor(walletData.getTotal()));
		wallet.setSaldo(utilitariosLocal.redondeoValor(walletData.getBalance()));
		wallet.setActivarRetrocompatibilidad(walletData.getActivateRetrocompatibility());
		return wallet;
	}
	
	private String getWalletCodeNumber(WalletData walletData) throws GenericBusinessException {
		String walletCodeNumber = SpiritConstants.getEmptyCharacter();
		EmpresaIf enterprise = walletData.getEnterprise();
		String month = Utilitarios.getMonthFromDate(walletData.getEmissionDate());
		String year = Utilitarios.getYearFromDate(walletData.getEmissionDate());
		walletCodeNumber = enterprise.getCodigo() + SpiritConstants.getPlaceholderCharacter();
		OficinaIf office = walletData.getOffice();
		ServidorIf server = (office.getServidorId()!=null)?servidorLocal.getServidor(office.getServidorId()):null;
		if (server != null)
			walletCodeNumber += server.getCodigo() + SpiritConstants.getPlaceholderCharacter();
		walletCodeNumber += walletData.getDocumentType().getCodigo() + SpiritConstants.getPlaceholderCharacter();
		walletCodeNumber += month + SpiritConstants.getPlaceholderCharacter();
		walletCodeNumber += year + SpiritConstants.getPlaceholderCharacter();
		walletCodeNumber += formatoSerial.format(getMaximoNumeroCartera(walletCodeNumber));
		return walletCodeNumber;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findPendingAccountDetailDataByWalletDetailId(Long walletDetailId) {
		//select distinct c.*, cd.*, ca.*, td.*, d.* from CARTERA c, CARTERA_DETALLE cd, CARTERA_AFECTA ca, TIPO_DOCUMENTO td, DOCUMENTO d where c.ID = cd.CARTERA_ID and cd.ID = ca.CARTERAAFECTA_ID and c.TIPODOCUMENTO_ID = td.ID and cd.DOCUMENTO_ID = d.ID and ca.CARTERADETALLE_ID = 3340
		String select = "select distinct c, cd, ca, td, d";
		String from = "from CarteraEJB c, CarteraDetalleEJB cd, CarteraAfectaEJB ca, TipoDocumentoEJB td, DocumentoEJB d";
		String where = "where c.id = cd.carteraId and cd.id = ca.carteraafectaId and c.tipodocumentoId = td.id and cd.documentoId = d.id and ca.carteradetalleId = :walletDetailId";
		String queryString = select + SpiritConstants.getBlankSpaceCharacter() + from + SpiritConstants.getBlankSpaceCharacter() + where;
		Query query = manager.createQuery(queryString);
		query.setParameter("walletDetailId", walletDetailId);
		return query.getResultList();
	}
	
	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Vector<WalletDetailData> reverseCrossingDocuments(WalletData walletData, CrossingWalletDetailData crossingWalletDetailData, Vector<WalletDetailData> applyingDocumentsVector) throws Exception, GenericBusinessException {
		try {
			BigDecimal valueToApply = crossingWalletDetailData.getValueToApply();
			PendingAccountData pendingAccountData = crossingWalletDetailData.getPendingAccountDetailData();
			CarteraIf pendingAccountWallet = pendingAccountData.getPendingAccountWallet();
			pendingAccountWallet.setSaldo(pendingAccountWallet.getSaldo().add(valueToApply));
			saveCartera(pendingAccountWallet);
			CarteraDetalleIf pendingAccountWalletDetail = pendingAccountData.getPendingAccountWalletDetail();
			pendingAccountWalletDetail.setSaldo(pendingAccountWalletDetail.getSaldo().add(valueToApply));
			carteraDetalleLocal.saveCarteraDetalle(pendingAccountWalletDetail);
			CarteraDetalleIf applyingDocument = carteraDetalleLocal.getCarteraDetalle(crossingWalletDetailData.getWalletDetailData().getWalletDetailId());
			applyingDocument.setSaldo(applyingDocument.getSaldo().add(valueToApply));
			carteraDetalleLocal.saveCarteraDetalle(applyingDocument);
			for (int j=0; j<applyingDocumentsVector.size(); j++) {
				WalletDetailData selectedApplyingDocument = applyingDocumentsVector.get(j);
				if (applyingDocument.getId().compareTo(selectedApplyingDocument.getWalletDetailId()) == 0) {
					selectedApplyingDocument.setBalance(selectedApplyingDocument.getBalance().add(valueToApply));
					applyingDocumentsVector.set(j, selectedApplyingDocument);
					break;
				}
			}
			CarteraIf applyingWallet = getCartera(applyingDocument.getCarteraId());
			applyingWallet.setSaldo(applyingWallet.getSaldo().add(valueToApply));
			saveCartera(applyingWallet);
			carteraAfectaLocal.deleteCarteraAfecta(crossingWalletDetailData.getCrossingWalletDetailId());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new Exception("Se ha producido un error al realizar la operación");
		}
		return applyingDocumentsVector;
	}*/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Vector<WalletDetailData> reverseCrossingDocuments(WalletData walletData, CrossingWalletDetailData crossingWalletDetailData, Vector<WalletDetailData> applyingDocumentsVector) throws Exception, GenericBusinessException {
		try {
			BigDecimal valueToApply = crossingWalletDetailData.getValueToApply();
			PendingAccountData pendingAccountData = crossingWalletDetailData.getPendingAccountDetailData();
			CarteraIf pendingAccountWallet = pendingAccountData.getPendingAccountWallet();
			pendingAccountWallet.setSaldo(pendingAccountWallet.getSaldo().add(valueToApply));
			saveCartera(pendingAccountWallet);
			CarteraDetalleIf pendingAccountWalletDetail = pendingAccountData.getPendingAccountWalletDetail();
			pendingAccountWalletDetail.setSaldo(pendingAccountWalletDetail.getSaldo().add(valueToApply));
			carteraDetalleLocal.saveCarteraDetalle(pendingAccountWalletDetail);
			CarteraDetalleIf applyingDocument = carteraDetalleLocal.getCarteraDetalle(crossingWalletDetailData.getWalletDetailData().getWalletDetailId());
			applyingDocument.setSaldo(applyingDocument.getSaldo().add(valueToApply));
			carteraDetalleLocal.saveCarteraDetalle(applyingDocument);
			for (int j=0; j<applyingDocumentsVector.size(); j++) {
				WalletDetailData selectedApplyingDocument = applyingDocumentsVector.get(j);
				if (applyingDocument.getId().compareTo(selectedApplyingDocument.getWalletDetailId()) == 0) {
					selectedApplyingDocument.setBalance(selectedApplyingDocument.getBalance().add(valueToApply));
					applyingDocumentsVector.set(j, selectedApplyingDocument);
					break;
				}
			}
			CarteraIf applyingWallet = getCartera(applyingDocument.getCarteraId());
			applyingWallet.setSaldo(applyingWallet.getSaldo().add(valueToApply));
			saveCartera(applyingWallet);
			carteraAfectaLocal.deleteCarteraAfecta(crossingWalletDetailData.getCrossingWalletDetailId());
			//Update accounting entries
			/*Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("tipoDocumentoId", walletData.getDocumentType().getId());
			queryMap.put("transaccionId", walletData.getWalletId());
			Iterator<AsientoIf> accountingEntriesIterator = asientoLocal.findAsientoByQuery(queryMap).iterator();
			while (accountingEntriesIterator.hasNext()) {
				AsientoIf accountingEntry = accountingEntriesIterator.next();
				
			}*/
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new Exception("Se ha producido un error al realizar la operación");
		}
		return applyingDocumentsVector;
	}
	
	public Collection<CarteraDetalleIf> findPendingAccountApprovedPayments(CarteraIf pendingAccountWallet) throws GenericBusinessException {
		//select distinct cd.* from CARTERA c, CARTERA_DETALLE cd where cd.CARTERA_ID = c.ID and cd.REFERENCIA = 2108 and c.CLIENTEOFICINA_ID = 14 and cd.DOCUMENTO_ID is null and c.APROBADO = 'S'
		String select = "select distinct cd";
		String from = "from CarteraEJB c, CarteraDetalleEJB cd";
		String where = "where cd.carteraId = c.id and cd.referencia = :pendingAccountWalletId and c.clienteoficinaId = :businessOperatorOfficeId and cd.documentoId is null and c.aprobado = :yes";
		String queryString = select + SpiritConstants.getBlankSpaceCharacter() + from + SpiritConstants.getBlankSpaceCharacter() + where;
		Query query = manager.createQuery(queryString);
		query.setParameter("pendingAccountWalletId", String.valueOf(pendingAccountWallet.getId()));
		query.setParameter("businessOperatorOfficeId", pendingAccountWallet.getClienteoficinaId());
		query.setParameter("yes", SpiritConstants.getOptionYes().substring(0,1));
		return query.getResultList();
	}
	
	/*public Collection<Object[]> findTransaccionesConciliacionBancaria(Long cuentaId, java.sql.Date fechaInicio, java.sql.Date fechaFin, String tipo) throws GenericBusinessException {
		String select = "select distinct a.id, c.codigo, cl.razonSocial, cd.preimpreso, sum(ad.debe), sum(ad.haber), c.fechaEmision";
		String from = "from AsientoEJB a, AsientoDetalleEJB ad, CarteraEJB c, CarteraDetalleEJB cd, ClienteEJB cl, ClienteOficinaEJB co";
		String where = "where a.id = ad.asientoId and a.tipoDocumentoId = c.tipodocumentoId and a.transaccionId = c.id and c.id = cd.carteraId and a.status = 'A' and c.estado = 'N' and c.clienteoficinaId = co.id and co.clienteId = cl.id and a.fecha >= :fechaInicio and a.fecha <= :fechaFin and ad.cuentaId = :cuentaId";
		if (tipo.equals("I"))
			where += " and ad.debe > 0.0";
		else
			where += " and ad.haber > 0.0";
		String groupBy = "group by a.id";
		String orderBy = "order by cd.preimpreso ";
		String queryString = select + " " + from + " " + where + " " + groupBy + " " + orderBy;
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio",fechaInicio );
		query.setParameter("fechaFin",fechaFin );
		query.setParameter("cuentaId", cuentaId);

		List<Object[]> lista = query.getResultList();
		return lista;
	}*/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<Object[]> findTransaccionesAnuladasConciliacionBancaria(Long empresaId, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws GenericBusinessException {
		String select = "select distinct c.codigo, c.fechaEmision, cl.razonSocial, cd.preimpreso, cd.saldo, cd.valor, c.tipodocumentoId, c.id";
		String from = "from CarteraEJB c, CarteraDetalleEJB cd, ClienteOficinaEJB co, ClienteEJB cl, TipoDocumentoEJB td";
		String where = "where c.id = cd.carteraId and c.clienteoficinaId = co.id and co.clienteId = cl.id and c.fechaEmision >= :fechaInicio and c.fechaEmision <= :fechaFin and c.estado = 'A' and c.tipodocumentoId = td.id and td.empresaId = :empresaId";
		String groupBy = "group by c.codigo, cd.preimpreso";
		String orderBy = "order by cd.preimpreso, c.codigo";
		String queryString = select + " " + from + " " + where + " " + groupBy + " " + orderBy;
		Query query = manager.createQuery(queryString);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	   public java.util.Collection findCarteraByEmpresaIdByFechaInicialByFechaFinalByClienteOficinaIdByTipoProveedorIdAndByTipoDocumentoId(java.lang.Long idEmpresa, Date fechaInicial, Date fechaFinal, java.lang.Long idClienteOficina, java.lang.Long idTipoProveedor, java.lang.Long idTipoDocumento, java.lang.Long idTipoProducto) 
		throws com.spirit.exception.GenericBusinessException{
			try{
				String objectName = "c";
				String queryString = "select distinct c, cd from CarteraEJB " + objectName + ", CarteraDetalleEJB cd, FacturaEJB f, FacturaDetalleEJB fd, ProductoEJB pr, ClienteOficinaEJB provof, ClienteEJB prov, TipoProveedorEJB tp, GenericoEJB g, TipoProductoEJB tpr where " ;
				queryString += " c.id = cd.carteraId and c.referenciaId = f.id and f.id = fd.facturaId and fd.productoId = pr.id and pr.proveedorId = provof.id and provof.clienteId = prov.id and prov.tipoproveedorId = tp.id and pr.genericoId = g.id and g.tipoproductoId = tpr.id ";
				queryString += " and f.clienteoficinaId = " + idClienteOficina + " and tp.empresaId = " + idEmpresa;
						
				if (idTipoProveedor!=null)
					 queryString += " and tp.id = " + idTipoProveedor + "";
				
				if (idTipoProducto!=null)
					 queryString += " and tpr.id = " + idTipoProducto + "";
				
				if (idTipoDocumento!=null)
					 queryString += " and f.tipodocumentoId = " + idTipoDocumento + "";
				
				//no se usa fecha inicial porque se quiere saber todas las facturas pendientes de pago hasta la fecha
				/*if (fechaInicial!=null)
					 queryString += " and f.fechaFactura >= :fechaInicial";*/
				
				if (fechaFinal!=null)
					 queryString += " and f.fechaFactura <= :fechaFinal";
				 			
				Query query = manager.createQuery(queryString);
				 
				if (fechaFinal!=null)
					query.setParameter("fechaFinal",fechaFinal);
				
					
				return query.getResultList();
				
			} catch(Exception e){
				e.printStackTrace();
				throw new GenericBusinessException("Se ha producido un error al consultar CarteraAfecta por fecha ");
			}
	 }
}
