package com.spirit.pos.gui.model;
//com.spirit.contabilidad.session;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.FormaPagoIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaData;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.ClienteRetencionIf;
import com.spirit.crm.entity.ClienteZonaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleData;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.ListaPrecioIf;
import com.spirit.facturacion.entity.PedidoData;
import com.spirit.facturacion.entity.PedidoDetalleData;
import com.spirit.facturacion.entity.PedidoDetalleIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.entity.PrecioIf;
import com.spirit.facturacion.gui.model.IngresarPreimpresoModel;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoData;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.OrigenDocumentoIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.PuntoImpresionIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.entity.UsuarioPuntoImpresionIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.general.util.DateHelperClient;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.ColorIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.GiftcardIf;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.pos.client.HotKeyComponentPOS;
import com.spirit.pos.entity.CajaSesionData;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.entity.DonacionTipoproductoIf;
import com.spirit.pos.entity.PuntosTipoProductoIf;
import com.spirit.pos.entity.TarjetaIf;
import com.spirit.pos.entity.TarjetaTipoIf;
import com.spirit.pos.entity.TeclasConfiguracionIf;
import com.spirit.pos.entity.VentasDocumentosIf;
import com.spirit.pos.entity.VentasPagosIf;
import com.spirit.pos.entity.VentasTrackData;
import com.spirit.pos.entity.VentasTrackIf;
import com.spirit.pos.gui.criteria.PedidoCriteria;
import com.spirit.pos.gui.panel.JPPos;
import com.spirit.pos.gui.poshwimpl.Cajon;
import com.spirit.pos.util.JComponentTableCellRenderer;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.DeepCopy;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;
import com.spirit.util.ValidarIdentificacion;





public class PosModel extends JPPos {
	private static final int MAX_LONGITUD_CODIGO = 4;
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DecimalFormat formatoEntero = new DecimalFormat("###0");

	private static final int MAX_LONGITUD_PREIMPRESO = 15;
	private static final int MAX_LONGITUD_BARCODE = 13;
	private static final int MAX_LONGITUD_CEDULA = 10;
	private static final int MAX_LONGITUD_RUC = 13;
	private static final int MAX_LONGITUD_TELEFONO = 10;
	private String si = "Si";
	private String no = "No";
	private FundacionDonarModel jdElegirFundacion;
	private CreditoClienteDevModel jdCredClienteDev;
	private PagoCreditoClienteModel jdPagoCreditoCliente;
	private PagoTarjetaAfiliacionReferidoModel jdPagoTarjetaAfiliacionReferidoModel;

	private JTable tableModelOriginal;
	private PagoEfectivoModel jdPagoEfectivo;
	private PagoTarjetaCreditoTemporalModel jdPagoTarjetaCredito;
	private PagoGiftCardsModel jdPagoGiftCard;
	private ResumenVentasModel jdResumenVentas;
	private NuevoClientePosModel jdNuevoClientePos;
	private PagoChequeModel jdPagoChequeModel;
	private CantidadModel jdCantidadProducto;
	DefaultListModel modelProducto = new DefaultListModel();
	private JDPopupFinderModel popupFinder;
	private JDPopupFinderModel popupFinderPedido;
	Vector<ProductoIf> productoVector = new Vector<ProductoIf>();
	Vector<PedidoDetalleIf> ProductosidReunionColeccion = new Vector<PedidoDetalleIf>();
	Vector<PedidoDetalleIf> ProductosidReunionColeccion_eliminados = new Vector<PedidoDetalleIf>();
	Vector<PedidoDetalleIf> ProductosidReunionColeccion_proceso = new Vector<PedidoDetalleIf>();
	Vector<String> PagosCollection = new Vector<String>();
	private Vector<Vector> PagosCollection_detalles = new Vector<Vector>();

	Vector<String> TarjetasCollection = new Vector<String>();
	Vector<Vector> TarjetasCollection_detalles = new Vector<Vector>();

	Vector<FacturaDetalleIf> ProductosidReunionColeccion_DEVOLUCIONES = new Vector<FacturaDetalleIf>();
	private Vector<PedidoDetalleIf> FacturaDetalleTempColeccion = new Vector<PedidoDetalleIf>();
	private Map tiposDocumentoByCodigoMap = new HashMap();
	private Map tiposDocumentoByIdMap = new HashMap();
	private Map documentosByCodigoMap = new HashMap();
	private Map documentosByIdMap = new HashMap();
	private Map teclasConfiguracionMap = new HashMap();
	private Map listasPreciosMap = new HashMap();
	private Map tiposProductoByCodigoMap = new HashMap();
	private Map tiposProductoByIdMap = new HashMap();
	private Map genericosMap = new HashMap();
	private Map modelosMap = new HashMap();
	private Map coloresMap = new HashMap();
	private Map presentacionesMap = new HashMap();
	private Map donacionesTipoProductoMap = new HashMap();
	private Map documentosByTipoDocumentoAndUsuarioMap = new HashMap();
	private Map documentosMap = new HashMap();
	private Map lotesMap = new HashMap();
	private Map tiposIdentificacionMap = new HashMap();
	private Map oficinasMap = new HashMap();
	private Map ciudadesMap = new HashMap();
	private Map productosMap = new HashMap();

	private Map preciosMap = new HashMap();


	private EmpresaIf empresa;
	private BodegaIf bodegaPOS;
	private MonedaIf monedaUSD;
	private EmpleadoIf empleadoCajero;
	private ArrayList vendedores = new ArrayList();
	private ParametroEmpresaIf acumularPuntosDinero;
	private ParametroEmpresaIf acumulacionTipoProductoTipoTarjeta;

	private ProductoCriteria productoCriteria;
	private ProductoIf productoIf;
	private long sumatotal = 0;
	private ClienteCriteria clienteCriteria;
	private PedidoCriteria pedidoCriteria;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	protected ClienteIf clienteIf;
	Long cajaAbiertaID = new Long("0");
	protected Double IVA_CERO = 0D;
	protected CajaIf cajaIf;
	protected EmpleadoIf empleado;
	protected PuntoImpresionIf puntoImpresionIf;
	private TipoDocumentoIf tipoDocumentoTransaccion;
	protected DocumentoIf documentoIf;
	// List cajas;
	// /List origenes;
	protected CorporacionIf corporacionIf;
	protected ClienteOficinaIf clienteOficinaIf;
	private static final String NOMBRE_ESTADO_COMPLETO = "COMPLETO";
	private static final String ESTADO_COMPLETO = NOMBRE_ESTADO_COMPLETO.substring(0, 1);
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String ESTADO_PENDIENTE = NOMBRE_ESTADO_PENDIENTE.substring(0, 1);
	private static final String NOMBRE_ESTADO_COTIZACION = "COTIZACION";
	private static final String ESTADO_COTIZACION = NOMBRE_ESTADO_COTIZACION.substring(2, 3);
	private static final String NOMBRE_ESTADO_CANCELADO = "CANCELADO";
	private static final String ESTADO_CANCELADO = NOMBRE_ESTADO_CANCELADO.substring(1, 2);
	private static final String MONEDA_DOLAR = "USD";
	protected Double IVA = Parametros.getIVA() / 100;
	protected ClienteOficinaIf proveedorIf;
	private Long idPedidoGuardado = 0L;
	protected PedidoIf pedido;
	private static Date fechaPedido;
	protected OficinaIf oficinaIf;
	protected Double porcentajeComisionAgencia = 0D, comisionAgenciaTotal = 0D;
	protected Double subTotal = 0D, descuentoTotal = 0D,
	descuentoGlobalTotal = 0D, ivaTotal = 0D, total = 0D, totalSinTarjeta = 0D,
	valorBruto = 0D, ivaTemp = 0D;
	protected PresupuestoIf presupuestoIf;
	private DecimalFormat formatoSerial = new DecimalFormat("0000000");
	private String numfacgeneral = null;
	private String referenciaReciboCaja = null;
	protected CajaSesionIf cajaSesion;
	private String idfundacion = null;
	Long idFactura = 0L;
	Long idprincipal = 0L;
	Vector<PedidoDetalleIf> ProductosidReunionColeccion_GIFT = new Vector<PedidoDetalleIf>();// GENERA COMPROBANTE CAJA
	Vector<TarjetaIf> ProductosidReunionColeccion_TA = new Vector<TarjetaIf>();
	Vector<PedidoDetalleIf> ProductosidReunionColeccion_FAC = new Vector<PedidoDetalleIf>();// GENERA FACT
	Vector<PedidoDetalleIf> ProductosidReunionColeccion_NV = new Vector<PedidoDetalleIf>();// GENERA N/V
	Vector<PedidoDetalleIf> ProductosidReunionColeccion_PERS_WEB = new Vector<PedidoDetalleIf>();// GENERA COMPROBANTE DE


	Vector<String> cantidadProductoRegalo = new Vector<String>();// GENERA COMPROBANTE DE

	boolean escogioFundacion=false;	// CAJA
	boolean bloqueado=false;

	HashMap<String,Object> parametrosMapPropietario = new HashMap<String,Object>();
	HashMap<String,Object> parametrosMapReferido = new HashMap<String,Object>();

	public PosModel() {	 
		loadMaps();
		loadObjects();
		clean();
		initKeyListeners();
		initListeners();
		getTblVentaDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		new HotKeyComponentPOS(this);
	}

	private void initListeners() {

		getBtnBuscarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarProducto();
			}
		});
		getBtnActualizarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarCliente();
			}
		});
		getBtnBuscar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarCliente();
			}
		});

		getBtnResumenCobrosPagos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				mostrar_resumen();
			}
		});

		// FORMAS DE PAGO
		getBtnEfectivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cobroEfectivoDialog();
			}
		});
		getBtnGiftcard().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				giftCardDialog();
			}
		});
		getBtnTarjetaCredito().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tarjetaCreditoDialog();
			}
		});
		getBtnCheque().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cobroCheque();
			}
		});
		getBtnDebito().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cobroTarjetaDebito();
			}
		});
		getBtnCredito().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cobroCredito();
			}
		});
		getBtnBorrar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				clean_formapagos();
			}
		});

		getBtnAfiliacionReferido().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cobroAfiliacionReferido();
			}
		});





		// /grupo de acciones
		getBtnHistorial().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				historialVentas();
			}
		});
		getBtnDonacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				donacionElegir();
			}
		});
		getBtnTransferirTarjetaAfiliacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				transferirTarjetaAfiliacion();
			}
		});
		getBtnCaja().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				bloquearCaja();
			}
		});
		getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cancelarVenta();
			}
		});
		getBtnAcreditar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				creditoCliente();
			}
		});

		// tipo de transacciones
		getBtnFactura().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipoDocumentoTransaccion = findTipoDocumentoByCodigo("FAC");
				setearPuntoImpresion("FAC");
				t_facturar();
			}
		});



		getBtnNotaVenta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipoDocumentoTransaccion = findTipoDocumentoByCodigo("VTA");
				setearPuntoImpresion("VTA");
				t_nventa();
			}
		});

		getBtnDevolucion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipoDocumentoTransaccion = findTipoDocumentoByCodigo("DEV");
				setearPuntoImpresion("DEV");
				t_devolucion();
			}
		});

		getBtnAnticipo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipoDocumentoTransaccion = findTipoDocumentoByCodigo("RCA");
				setearPuntoImpresion("RCA");
				t_anticipo();
			}
		});

		// ADICIONALES ABRIR CAJON
		getBtnAbrir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				abrirCajon();
			}
		});
		// ELIMINAR DETALLE LISTA
		getBtnEliminar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				borrarItem();
			}
		});

	}

	private void loadMaps() {
		mapearTiposDocumento();
		mapearTeclasConfiguracion();
		mapearListasPrecios();
		mapearDocumentos();
		//mapearDocumentosByModuloAndUsuario("FACT", ((UsuarioIf) Parametros.getUsuarioIf()).getId());
		mapearTiposProducto();
		mapearGenericos();
		mapearModelos();
		mapearColores();
		mapearPresentaciones();
		mapearDonacionesTipoProducto();
		mapearLotesByProducto();
		mapearTiposIdentificacion();
		mapearOficinas();
		mapearCiudades();
		mapearProductos();
	}

	private void loadObjects() {
		empresa = (EmpresaIf) Parametros.getEmpresa();
		bodegaPOS = getBodegaPOS(Parametros.getIdOficina());
		monedaUSD = getMonedaUSD("USD");
		empleadoCajero = getEmpleadoCajero(((UsuarioIf)Parametros.getUsuarioIf()).getEmpleadoId());		
		vendedores = (ArrayList) SessionServiceLocator.getEmpleadoSessionService().findVendedoresByOficinaId(Parametros.getIdOficina());
		try {
		Map parameterMap = new HashMap();
		parameterMap.put("empresaId", empresa.getId());
		parameterMap.put("codigo", "APD");
		Iterator it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
		if (it.hasNext())
			acumularPuntosDinero = (ParametroEmpresaIf) it.next();
		parameterMap.put("codigo", "ATPTT");
		it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
		if (it.hasNext())
			acumulacionTipoProductoTipoTarjeta = (ParametroEmpresaIf) it.next();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}

	private BodegaIf getBodegaPOS(Long idOficina) {
		BodegaIf bodegaPOS = null;
		try {
			Iterator it = SessionServiceLocator.getBodegaSessionService().findBodegaByOficinaId(idOficina).iterator();
			if (it.hasNext())
				bodegaPOS = (BodegaIf) it.next();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar datos de bodega", SpiritAlert.ERROR);
		}
		return bodegaPOS;
	}

	private MonedaIf getMonedaUSD(String codigo) {
		MonedaIf monedaUSD = null;
		try {
			Iterator it = SessionServiceLocator.getMonedaSessionService().findMonedaByCodigo(codigo).iterator();
			if (it.hasNext())
				monedaUSD = (MonedaIf) it.next();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar datos de moneda", SpiritAlert.ERROR);
		}
		return monedaUSD;
	}

	private Vector<String> getTiposEmpleadoVendedorIdVector() {
		Vector<String> v = new Vector<String>();
		try {
			Iterator it = SessionServiceLocator.getTipoEmpleadoSessionService().findTipoEmpleadoByVendedor("S").iterator();
			if (it.hasNext())
				v.add(((TipoEmpleadoIf) it.next()).getId().toString());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar datos de tipo de empleado", SpiritAlert.ERROR);
		}
		return v;
	}

	private EmpleadoIf getEmpleadoCajero(Long idEmpleado) {
		EmpleadoIf empleadoCajero = null;
		try {
			empleadoCajero = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(idEmpleado);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar datos de cajero", SpiritAlert.ERROR);
		}
		return empleadoCajero;
	}

	private void mapearTiposDocumento() {
		try {
			Iterator it = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) it.next();
				tiposDocumentoByCodigoMap.put(tipoDocumento.getCodigo(), tipoDocumento);
				tiposDocumentoByIdMap.put(tipoDocumento.getId(), tipoDocumento);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear tipos de documento", SpiritAlert.ERROR);
		}
	}

	private void mapearDocumentos() {
		try {
			Iterator it = SessionServiceLocator.getDocumentoSessionService().findDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				DocumentoIf documento = (DocumentoIf) it.next();
				documentosByCodigoMap.put(documento.getCodigo(), documento);

			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear tipos de documento", SpiritAlert.ERROR);
		}
	}


	private void mapearTeclasConfiguracion() {
		try {
			Iterator it = SessionServiceLocator.getTeclasConfiguracionSessionService().getTeclasConfiguracionList().iterator();
			while (it.hasNext()) {
				TeclasConfiguracionIf teclasConfiguracion = (TeclasConfiguracionIf) it.next();
				teclasConfiguracionMap.put(teclasConfiguracion.getCodigo(), teclasConfiguracion);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear teclas de configuración", SpiritAlert.ERROR);
		}
	}

	private void mapearListasPrecios() {
		try {
			Iterator it = SessionServiceLocator.getListaPrecioSessionService().findListaPrecioByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				ListaPrecioIf listaPrecio = (ListaPrecioIf) it.next();
				listasPreciosMap.put(listaPrecio.getCodigo(), listaPrecio);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear teclas de configuración", SpiritAlert.ERROR);
		}
	}

	private void mapearTiposProducto() {
		try {
			Iterator it = SessionServiceLocator.getTipoProductoSessionService().findTipoProductoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				TipoProductoIf tipoProducto = (TipoProductoIf) it.next();
				tiposProductoByCodigoMap.put(tipoProducto.getCodigo(), tipoProducto);
				tiposProductoByIdMap.put(tipoProducto.getId(), tipoProducto);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear tipos de producto", SpiritAlert.ERROR);
		}
	}

	private void mapearGenericos() {
		Long idEmpresa = Parametros.getIdEmpresa();
		try {
			Iterator it = SessionServiceLocator.getGenericoSessionService().findGenericoByEmpresaId(idEmpresa).iterator();
			while (it.hasNext()) {
				GenericoIf generico = (GenericoIf) it.next();
				genericosMap.put(generico.getId(), generico);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear genéricos", SpiritAlert.ERROR);
		}
	}

	private void mapearModelos() {
		try {
			Iterator it = SessionServiceLocator.getModeloSessionService().getModeloList().iterator();
			while (it.hasNext()) {
				ModeloIf modelo = (ModeloIf) it.next();
				modelosMap.put(modelo.getId(), modelo);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear modelos", SpiritAlert.ERROR);
		}
	}

	private void mapearPresentaciones() {
		try {
			Iterator it = SessionServiceLocator.getPresentacionSessionService().getPresentacionList().iterator();
			while (it.hasNext()) {
				PresentacionIf presentacion = (PresentacionIf) it.next();
				presentacionesMap.put(presentacion.getId(), presentacion);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear presentaciones", SpiritAlert.ERROR);
		}
	}

	private void mapearColores() {
		try {
			Iterator it = SessionServiceLocator.getColorSessionService().getColorList().iterator();
			while (it.hasNext()) {
				ColorIf color = (ColorIf) it.next();
				coloresMap.put(color.getId(), color);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear colores", SpiritAlert.ERROR);
		}
	}

	private void mapearDonacionesTipoProducto() {
		try {
			Iterator it = SessionServiceLocator.getDonacionTipoproductoSessionService().getDonacionTipoproductoList().iterator();
			while (it.hasNext()) {
				DonacionTipoproductoIf donacionTipoProducto = (DonacionTipoproductoIf) it.next();
				donacionesTipoProductoMap.put(donacionTipoProducto.getTipoproductoId(), donacionTipoProducto);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear donaciones", SpiritAlert.ERROR);
		}
	}

	private void mapearLotesByProducto() {
		try {
			Iterator it = SessionServiceLocator.getLoteSessionService().getLoteList().iterator();
			while (it.hasNext()) {
				LoteIf lote = (LoteIf) it.next();
				lotesMap.put(lote.getProductoId(), lote);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear lotes por producto", SpiritAlert.ERROR);
		}
	}

	private void mapearTiposIdentificacion() {
		try {
			Iterator it = SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacionList().iterator();
			while (it.hasNext()) {
				TipoIdentificacionIf tipoIdentificacion = (TipoIdentificacionIf) it.next();
				tiposIdentificacionMap.put(tipoIdentificacion.getId(), tipoIdentificacion);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear tipos de identificación", SpiritAlert.ERROR);
		}
	}

	private void mapearOficinas() {
		try {
			Iterator it = SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				OficinaIf oficina = (OficinaIf) it.next();
				oficinasMap.put(oficina.getId(), oficina);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear oficinas", SpiritAlert.ERROR);
		}
	}

	private void mapearCiudades() {
		try {
			Iterator it = SessionServiceLocator.getCiudadSessionService().getCiudadList().iterator();
			while (it.hasNext()) {
				CiudadIf ciudad = (CiudadIf) it.next();
				ciudadesMap.put(ciudad.getId(), ciudad);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear ciudades", SpiritAlert.ERROR);
		}
	}

	private void mapearProductos() {
		Map parameterMap = new HashMap();
		parameterMap.put("estado", "A");
		parameterMap.put("permiteventa", "S");
		try {
			Iterator it = SessionServiceLocator.getProductoSessionService().findProductoByEmpresaIdAndByQuery(Parametros.getIdEmpresa(), parameterMap).iterator();
			while (it.hasNext()) {
				ProductoIf producto = (ProductoIf) it.next();
				productosMap.put(producto.getId(), producto);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}

	private TipoDocumentoIf findTipoDocumentoByCodigo(String codigoTipoDocumento) {
		TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoByCodigoMap.get(codigoTipoDocumento);
		return tipoDocumento;
	}

	private TipoDocumentoIf findTipoDocumentoById(Long idTipoDocumento) {
		TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoByIdMap.get(idTipoDocumento);
		return tipoDocumento;
	}

	private DocumentoIf findDocumentoByCodigo(String codigoDocumento) {
		DocumentoIf documento = (DocumentoIf) documentosByCodigoMap.get(codigoDocumento);
		return documento;
	}

	private DocumentoIf findDocumentoById(Long idDocumento) {
		DocumentoIf documento = (DocumentoIf) documentosByIdMap.get(idDocumento);
		return documento;
	}

	private TeclasConfiguracionIf findTeclasConfiguracionByCodigo(String codigoTeclaConfiguracion) {
		TeclasConfiguracionIf teclaConfiguracion = (TeclasConfiguracionIf) teclasConfiguracionMap.get(codigoTeclaConfiguracion);
		return teclaConfiguracion;
	}

	public void initKeyListeners() {

		Border xy = null;
		getTxtvacio().setBorder(xy);
		getTxtTotalFinal().setBorder(xy);
		getTxtIdProducto().setVisible(false);
		getTxtIdGiftcard().setVisible(false);
		getTxtLoteid().setVisible(false);
		getTxtTipoProducto().setVisible(false);
		getLblVisible().setVisible(false);
		getLblVisible().setText("si");
		getPnResumenPagos().setVisible(true);
		getLblTipoDocumentoSeleccionado().setVisible(false);
		getLblTipoDocumentoSeleccionado().setText("");

		clean_producto();
		// ////
		getTblVentaDetalle().addMouseListener(oMouseAdapterTblFactura);
		getTblVentaDetalle().addKeyListener(oKeyAdapterTblFactura);
		/////////PANEL DE TRANSACCIONES///////
		getBtnFactura().addKeyListener(oKeyAdapterBtnFactura);
		getBtnNotaVenta().addKeyListener(oKeyAdapterBtnNotaVenta);
		getBtnDevolucion().addKeyListener(oKeyAdapterBtnDevolucion);

		///////PANEL DE ACCIONES
		getBtnDonacion().addKeyListener(oKeyAdapterBtnDonacion);
		getBtnAcreditar().addKeyListener(oKeyAdapterBtnAcreditar);


		///////PANEL DE PAGOS
		getBtnEfectivo().addKeyListener(oKeyAdapterBtnPagoEfectivo);
		getBtnTarjetaCredito().addKeyListener(oKeyAdapterBtnPagoTarjetaCredito);
		getBtnGiftcard().addKeyListener(oKeyAdapterBtnPagoGiftCard);
		getBtnCredito().addKeyListener(oKeyAdapterBtnPagoCreditoCliente);
		getBtnCheque().addKeyListener(oKeyAdapterBtnPagoCheque);
		getBtnBorrar().addKeyListener(oKeyAdapterBtnBorrarPagos);

		getBtnBuscar().addKeyListener(oKeyAdapterBtnBuscarCliente);

		getBtnActualizarCliente().addKeyListener(oKeyAdapterBtnActualizarCliente);

		TableColumn anchoColumna = getTblVentaDetalle().getColumnModel().getColumn(0);
		anchoColumna.setMinWidth(35);
		anchoColumna = getTblVentaDetalle().getColumnModel().getColumn(1);
		anchoColumna.setMinWidth(220);
		anchoColumna = getTblVentaDetalle().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(35);
		getTblVentaDetalle().getColumnModel().getColumn(3).setCellRenderer(new getCR_ejem_DEV());		
		getTblVentaDetalle().getColumnModel().getColumn(3).setWidth(0);
		getTblVentaDetalle().getColumnModel().getColumn(3).setPreferredWidth(0);
		getTblVentaDetalle().getColumnModel().getColumn(3).setMinWidth(0);
		getTblVentaDetalle().getColumnModel().getColumn(3).setMaxWidth(0);
		anchoColumna = getTblVentaDetalle().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(35);
		anchoColumna = getTblVentaDetalle().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(35);
		anchoColumna = getTblVentaDetalle().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(35);
		getTblVentaDetalle().getColumnModel().getColumn(8).setWidth(0);
		getTblVentaDetalle().getColumnModel().getColumn(8).setPreferredWidth(0);
		getTblVentaDetalle().getColumnModel().getColumn(8).setMinWidth(0);
		getTblVentaDetalle().getColumnModel().getColumn(8).setMaxWidth(0);


		getTxtCedula().addKeyListener(new NumberTextField());
		getTxtCedula().addKeyListener(new TextChecker(MAX_LONGITUD_RUC));
		getTxtCedula().addKeyListener(oKeyAdapterSetearCliente);

		getTxtTelefono().addKeyListener(new NumberTextField());
		getTxtTelefono().addKeyListener(new TextChecker(MAX_LONGITUD_TELEFONO));
		getTxtTelefono().addKeyListener(oKeyAdapterSetearClienteTelefono);

		getCmbVendedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(getCmbVendedor().getSelectedItem() == null)) {
					if (getCmbVendedor().getSelectedIndex() != -1) {
						Border compound = null;
						getPnVendedor().setBorder(compound);
					}
				}
			}
		});

		getCmbVendedor().setNextFocusableComponent(getTxtCodigoBarras());
		getTxtCodigoBarras().setNextFocusableComponent(getTxtCedula());
		getTxtCedula().setNextFocusableComponent(getTxtDireccion());		
		getBtnFactura().setNextFocusableComponent(getBtnNotaVenta());
		getBtnNotaVenta().setNextFocusableComponent(getBtnAnticipo());
		getBtnAnticipo().setNextFocusableComponent(getBtnDevolucion());
		getBtnDevolucion().setNextFocusableComponent(getBtnEfectivo());
		getTxtNumDocumento().setNextFocusableComponent(getTblVentaDetalle());

		getTxtNumDocumento().addKeyListener(new TextChecker(MAX_LONGITUD_PREIMPRESO));
		getTxtNumDocumento().addKeyListener(oKeyAdapterListadoDevoluciones);

		getTxtCodigoBarras().addKeyListener(new TextChecker(MAX_LONGITUD_BARCODE));
		getTxtCodigoBarras().addKeyListener(oKeyAdapterAnadirProductoUPC);

		getTxtCantidad().addKeyListener(new NumberTextField());
		getTxtCantidad().addKeyListener(onKeyCantidad);


		getTxtCantidadDetalle().setText("");	
		getTxtCantidadDetalle().addKeyListener(new NumberTextField());
		getTxtCantidadDetalle().addKeyListener(onKeyCantidadIngreso);

		getTxtCantidadDevuelta().addKeyListener(new NumberTextField());
		getTxtCantidadDevuelta().addKeyListener(onKeyCantidadDev);

		getTxtPorcentajeDescuento().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeDescuento().addKeyListener(onKeyDescuentoGlobal);

		getTxtImporte().addKeyListener(new NumberTextFieldDecimal());
		getBtnBuscar().setToolTipText("Buscar Cliente");
		getBtnBuscarProducto().setToolTipText("Buscar producto");

		getLblCodigoBarras().setText("");

		Calculator2 calculator2 = new Calculator2();
		getPnCalculadora().add(calculator2.getmainPanel(), "Center");

		// ******************
		getBtnAbrir().setIcon(
				SpiritResourceManager.getImageIcon("images/pos/cajon.png"));
		getBtnAbrir().setText(tecla_pantalla("ABRIRCAJON") + " - ABRIR");

		// //PANEL DEL CLIENTE
		getBtnActualizarCliente().setIcon(
				SpiritResourceManager.getImageIcon("images/pos/contact.png"));
		getBtnBuscar().setIcon(
				SpiritResourceManager.getImageIcon("images/pos/ViewUser.png"));

		// ///PANEL DE ACCIONES//////////////////////////
		getBtnHistorial().setIcon(
				SpiritResourceManager
				.getImageIcon("images/pos/FindDocument.png"));
		getBtnHistorial().setText(
				tecla_pantalla("HISTORIALVENTAS") + " - HIST.");

		getBtnTransferirTarjetaAfiliacion().setIcon(
				SpiritResourceManager
				.getImageIcon("images/pos/tarjetaDebito.png"));
		getBtnTransferirTarjetaAfiliacion().setText(
				tecla_pantalla("TARJAFILI") + " - TRANSF. T/A");

		getBtnCancelar().setIcon(
				SpiritResourceManager.getImageIcon("images/pos/cancelar.png"));
		getBtnCancelar().setText(tecla_pantalla("CANCELARVENTA") + " - C/C");

		getBtnDonacion().setIcon(
				SpiritResourceManager.getImageIcon("images/pos/Handshake.png"));
		getBtnDonacion().setText(tecla_pantalla("DONACION") + " - DON.");

		getBtnCaja().setIcon(
				SpiritResourceManager.getImageIcon("images/pos/locked.png"));
		getBtnCaja().setText(tecla_pantalla("BLOQUEARCAJA") + " - CAJA");

		getBtnAcreditar().setIcon(
				SpiritResourceManager
				.getImageIcon("images/pos/creditoCliente.png"));
		getBtnAcreditar()
		.setText(tecla_pantalla("CREDCLIENTE") + "- ACREDITAR");
		getBtnAcreditar().setEnabled(false);

		// //PANEL DE TRANSACCIONES
		getBtnFactura().setIcon(
				SpiritResourceManager.getImageIcon("images/pos/invoice.png"));
		getBtnFactura().setText(tecla_pantalla("FACTURAR") + " - FAC");

		getBtnAnticipo().setIcon(
				SpiritResourceManager
				.getImageIcon("images/pos/downloadfromWeb.png"));
		getBtnAnticipo().setText(tecla_pantalla("ANTICIPO") + " - ANT");

		getBtnNotaVenta().setIcon(
				SpiritResourceManager.getImageIcon("images/pos/notaventa.png"));
		getBtnNotaVenta().setText(tecla_pantalla("NVENTA") + " - N/V");

		getBtnDevolucion()
		.setIcon(
				SpiritResourceManager
				.getImageIcon("images/pos/devolucion.png"));
		getBtnDevolucion().setText(tecla_pantalla("DEV") + " - DEV");

		// /FORMAS DE PAGO
		getBtnEfectivo().setIcon(
				SpiritResourceManager.getImageIcon("images/pos/pEfectivo.png"));
		getBtnEfectivo().setText(tecla_pantalla("PEFECTIVO") + " - EFE.");

		getBtnGiftcard().setIcon(
				SpiritResourceManager.getImageIcon("images/pos/pGiftc.png"));
		getBtnGiftcard().setText(tecla_pantalla("PGIFTCARD") + " - G/C");

		getBtnTarjetaCredito().setIcon(
				SpiritResourceManager
				.getImageIcon("images/pos/pCreditCards.png"));
		getBtnTarjetaCredito().setText(tecla_pantalla("PTARJCRED") + " - T/C");

		getBtnCheque().setIcon(
				SpiritResourceManager.getImageIcon("images/pos/cheque.jpeg"));
		getBtnCheque().setText(tecla_pantalla("PCHEQUE") + " - CH");

		getBtnDebito().setIcon(
				SpiritResourceManager
				.getImageIcon("images/pos/tarjetaDebito.png"));
		getBtnDebito().setText(tecla_pantalla("PTARJDEB") + " - DEB.");
		getBtnDebito().setEnabled(false);

		getBtnBorrar().setIcon(
				SpiritResourceManager
				.getImageIcon("images/pos/borrarPagos.png"));
		getBtnBorrar().setText(tecla_pantalla("BORRARPAGOS") + " - BORRAR");

		getBtnCredito().setIcon(
				SpiritResourceManager
				.getImageIcon("images/pos/pCredCliente.png"));
		getBtnCredito().setText(tecla_pantalla("PCREDCLIENTE") + " - CRED.");

		// /CODIGO DE BARRAS
		getLblCodigoBarras().setIcon(
				SpiritResourceManager.getImageIcon("images/pos/Dibujo.gif"));
		getTxtCodigoBarras().setToolTipText("Código de barras del producto");

		// //DETALLES
		getLblBuscarDocumento().setIcon(
				SpiritResourceManager
				.getImageIcon("images/pos/searchInvoice.png"));
		// //F3 ELIMINAR DETALLE
		getBtnEliminar()
		.setIcon(
				SpiritResourceManager
				.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminar().setText(tecla_pantalla("BORRARITEM") + " - Eliminar");

		getBtnBuscarProducto().setIcon(
				SpiritResourceManager
				.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarProducto().setText("Buscar Producto");

		// ****************

		getTxtCantidadDetalle().setToolTipText("CANTIDAD");


		getSplitPane1().setPreferredSize(new Dimension(310, 289));		
		getSplitPane1().setOneTouchExpandable(true);
		getSplitPane1().setDividerLocation(300);
		getSplitPane1().setDividerSize(10);


		getBtnAfiliacionReferido().setEnabled(true);
	}

	public String tecla_pantalla(String codigoFuncion) {
		TeclasConfiguracionIf tecla_sel = findTeclasConfiguracionByCodigo(codigoFuncion);
		String nombre_tecla = (tecla_sel!=null)?tecla_sel.getMascara():"";
		return nombre_tecla;
	}

	public void clean_formapagos() {

		TarjetasCollection_detalles.clear();
		PagosCollection_detalles.clear();
		getTxtCreditoCliente().setText("0.00");
		getTxtCheque().setText("0.00");
		getTxtEfectivo().setText("0.00");
		getTxtGiftcard().setText("0.00");
		getTxtTarjetaCredito().setText("0.00");
		getTxtDebitoBancario().setText("0.00");
		getTxtPuntos().setText("0.00");

		getTxtVuelto().setText("0.00");
		getTxtDeuda().setText(getTxtTotalFinal().getText());
	}

	public void clean() {
		getLblEsteItem().setHorizontalAlignment(0);
		getLblEsteItem().setText("Este item:");
		getLblCantidadDevuelta().setVisible(false);
		getTxtCantidadDevuelta().setVisible(false);

		getTblVentaDetalle().getColumnModel().getColumn(3).setMinWidth(0);
		getTblVentaDetalle().getColumnModel().getColumn(3).setMaxWidth(0);
		getTblVentaDetalle().getColumnModel().getColumn(3).setWidth(0);
		getTblVentaDetalle().getColumnModel().getColumn(3).setPreferredWidth(0);

		getTxtDescuento().setText("");
		getTxtPorcentajeDescuento().setText("");
		getTxtTotalFinal().setText("0.00");
		getTxtSubtotalFinal().setText("0.00");
		getTxtDescuentoFinal().setText("0.00");
		getTxtImpuestosFinal().setText("0.00");
		getTxtCodigoBarras().setText("");

		getTxtItem().setText("");
		getTxtEsteItem().setText("0.00");
		getTxtDonacion().setText("0.00");
		getTxtDeuda().setText("0.00");
		getTxtVuelto().setText("0.00");

		clienteOficinaIf = null;
		clean_producto();
		clean_cliente();
		getTxtNombre().setText("");
		// getLblNombreVendedor().setText("");

		DefaultTableModel model = (DefaultTableModel) getTblVentaDetalle().getModel();
		for (int i = this.getTblVentaDetalle().getRowCount(); i > 0; --i) model.removeRow(i - 1);

		ProductosidReunionColeccion.clear();
		ProductosidReunionColeccion_proceso.clear();
		ProductosidReunionColeccion_DEVOLUCIONES.clear();
		ProductosidReunionColeccion_eliminados.clear();
		ProductosidReunionColeccion_TA.clear();
		PagosCollection_detalles.clear();
		TarjetasCollection_detalles.clear();

		idFactura = 0L;
		idprincipal = 0L;

		getTxtCheque().setText("0.00");
		getTxtEfectivo().setText("0.00");
		getTxtGiftcard().setText("0.00");
		getTxtTarjetaCredito().setText("0.00");
		getTxtDebitoBancario().setText("0.00");
		getTxtCreditoCliente().setText("0.00");
		getTxtPuntos().setText("0.00");

		cargarComboVendedores();
		if (getCmbVendedor().getSelectedObjects().length > 0)	getCmbVendedor().setSelectedIndex(0);

		getLblTipoDocumentoSeleccionado().setText("");// me indica que transacción se escogio.

		desmarcarCuadroBoton(getBtnFactura());
		desmarcarCuadroBoton(getBtnNotaVenta());
		desmarcarCuadroBoton(getBtnAnticipo());
		desmarcarCuadroBoton(getBtnDevolucion());
		getTxtCedula().setText("");

		// dejar los Bordes con valores default
		Border compound = null;
		getPnVendedor().setBorder(compound);
		getPnCliente().setBorder(
				new TitledBorder(new SoftBevelBorder(BevelBorder.LOWERED,
						new Color(153, 51, 0), null, new Color(153, 51, 0),
						null), "Datos del cliente", TitledBorder.LEADING,
						TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12)));

		getPnTransaccion().setBorder(
				new TitledBorder(new BevelBorder(BevelBorder.LOWERED,
						new Color(153, 51, 0), null, new Color(153, 51, 0),
						null), "Tipo de Transacci\u00f3n",
						TitledBorder.LEADING, TitledBorder.TOP, new Font(
								"Tahoma", Font.BOLD, 12)));
		// ///////////////////////
		getTxtNumDocumento().setText("");
		activarBotonesDevolucion(false);

		getCmbVendedor().grabFocus();

		Caja_abierta_id("A");

		getBtnAcreditar().setEnabled(false);		
		//getBtnCheque().setEnabled(false);
		getBtnDebito().setEnabled(false);
		getBtnTransferirTarjetaAfiliacion().setEnabled(true);

		pedido = null;
		clienteIf = null;

		idfundacion=null;
		escogioFundacion=false;
		totalSinTarjeta = 0D;
		bloqueado=false; 
	}

	public void NuevoCliente() {

		jdNuevoClientePos = new NuevoClientePosModel(Parametros.getMainFrame(),getTxtCedula().getText());
		jdNuevoClientePos.addWindowListener(wl);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
		jdNuevoClientePos.setLocation(x, y);
		jdNuevoClientePos.pack();
		jdNuevoClientePos.setModal(true);
		jdNuevoClientePos.setVisible(true);
		if (jdNuevoClientePos.isAceptar()) { // TODO: setear en alguna
			// Coleccion los datos para una vez q mande a procesar guarde todo (pedido, factura,asietnos, moviemnto pago_pos
			Long clientenuevoId = 0L;
			String clientenuevoIden = "";
			clientenuevoIden = jdNuevoClientePos.getIdentificacion();
			getTxtCedula().setText(clientenuevoIden);
			boolean creoCliente = setearCliente("0");
			if (creoCliente)
				getPnCliente().setBorder(
						new TitledBorder(new SoftBevelBorder(
								BevelBorder.LOWERED, new Color(153, 51, 0),
								null, new Color(153, 51, 0), null),
								"Datos del cliente", TitledBorder.LEADING,
								TitledBorder.TOP, new Font("Tahoma", Font.BOLD,
										12)));

		} else {
			clean_cliente();
		}
	}

	public void limpiar_tabla_devoluciones() {
		if (ProductosidReunionColeccion_DEVOLUCIONES.size() > 0) {
			DefaultTableModel model = (DefaultTableModel) getTblVentaDetalle()
			.getModel();
			for (int i = this.getTblVentaDetalle().getRowCount(); i > 0; --i)
				model.removeRow(i - 1);
			ProductosidReunionColeccion_DEVOLUCIONES.clear();
		}
	}

	public void clean_factura() {
		getTxtNombre().setText("");
		getTxtOficina().setText("");
		getTxtCorporacion().setText("");
		getTxtDescuento().setText("");
		getTxtTotalFinal().setText("0.00");
		getTxtSubtotalFinal().setText("0.00");
		getTxtDescuentoFinal().setText("0.00");
		getTxtImpuestosFinal().setText("0.00");
		getTxtCodigoBarras().setText("");
		clean_producto();
		DefaultTableModel model = (DefaultTableModel) getTblVentaDetalle().getModel();
		for (int i = this.getTblVentaDetalle().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
		ProductosidReunionColeccion.clear();
		getBtnEliminar().setText("");

		getTxtEsteItem().setText("");
		getTxtDonacion().setText("");
	}

	public void clean_producto() {
		getTxtCodigoBarras().setText("");
		getTxtIdProducto().setText("");
		//getTxtIdGiftcard().setText(null);
		getTxtCodigoProducto().setText("");
		getTxtPVP().setText("");
		getTxtCantidad().setText("");
		// getTxtInventario().setText("");
		getTxtDescripcion().setText("");
		getTxtDescuento().setText("");
		getTxtImporte().setText("");
		getTxtIVA().setText("");
		getTxtCantidad().setText("");
		getTxtCantidadDetalle().setText("");
		getTxtLoteid().setText("");

		getTxtIdProducto().setVisible(false);
		getTxtIdGiftcard().setVisible(false);
		getTxtCodigoProducto().setVisible(false);
		getTxtPVP().setVisible(false);
		getTxtCantidad().setVisible(false);
		// getTxtInventario().setText("");
		getTxtDescripcion().setVisible(false);
		getTxtDescuento().setVisible(false);
		getTxtImporte().setVisible(false);
		getTxtIVA().setVisible(false);
		getTxtCantidad().setVisible(false);
		getTxtCantidadDetalle().setVisible(true);
		getTxtLoteid().setVisible(false);
	}

	public void clean_cliente() {
		getTxtNombre().setText("");
		getTxtDireccion().setText("");
		getTxtEmail().setText("");
		getTxtOficina().setText("");
		getTxtCorporacion().setText("");
		getTxtTelefono().setText("");
	}

	// /////////////////////////// CLASES ADICIONALES //////////////////


	private boolean setearCliente(String cliente_default) {
		boolean find = false;

		String cedulaCliente = getTxtCedula().getText();
		if (cliente_default.equals("1"))// ingresar cliente default
		{
			cedulaCliente = "9999999999";
			getTxtCedula().setText(cedulaCliente);
			getPnCliente()
			.setBorder(
					new TitledBorder(new SoftBevelBorder(
							BevelBorder.LOWERED, new Color(153, 51, 0),
							null, new Color(153, 51, 0), null),
							"Datos del cliente", TitledBorder.LEADING,
							TitledBorder.TOP, new Font("Tahoma",
									Font.BOLD, 12)));
		}
		corporacionIf = null;
		Iterator clienteIt;
		try {
			clienteIt = SessionServiceLocator.getClienteSessionService().findClienteByIdentificacion(
					cedulaCliente).iterator();
			if (clienteIt.hasNext()) {
				clienteIf = (ClienteIf) clienteIt.next();
				getTxtNombre().setText(clienteIf.getNombreLegal());
				find = true;
				try {
					corporacionIf = SessionServiceLocator.getCorporacionSessionService()
					.getCorporacion(clienteIf.getCorporacionId());
					getTxtCorporacion().setText(
							corporacionIf.getCodigo() + " - "
							+ corporacionIf.getNombre());
				} catch (GenericBusinessException e) {
					SpiritAlert
					.createAlert(
							"Se ha producido un error al consultar la Coroporación del cliente",
							SpiritAlert.ERROR);
					e.printStackTrace();
				}
				clienteOficinaIf = null;
				try {
					Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(clienteIf.getId());

					if (oficinas.size() >0) {
						clienteOficinaIf = oficinas.iterator().next();
						getTxtDireccion().setText(
								clienteOficinaIf.getDireccion());
						String telefono = clienteOficinaIf.getTelefono();
						String email = clienteOficinaIf.getEmail();
						if (telefono == null)
							telefono = "";
						if (email == null)
							email = "";
						getTxtEmail().setText(email);
						getTxtTelefono().setText(telefono);
						getTxtOficina().setText(
								clienteOficinaIf.getCodigo() + " - "
								+ clienteOficinaIf.getDescripcion());
					}
				} catch (Exception e) {
					e.printStackTrace();
					SpiritAlert
					.createAlert(
							"Se ha producido un error al consultar la oficina del cliente",
							SpiritAlert.ERROR);
				}
			}
		} catch (GenericBusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return find;
	}

	private boolean setearClienteporOficinaId(Long clienteOficinaId) {
		boolean find = false;
		clienteOficinaIf = null;
		try {
			System.out.println("id-->"+clienteOficinaId);
			Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaById(clienteOficinaId);
			System.out.println("OFIZINA"+oficinas.size());
			if (oficinas.size() == 1) {
				clienteOficinaIf = oficinas.iterator().next();
				getTxtDireccion().setText(clienteOficinaIf.getDireccion());
				String telefono = clienteOficinaIf.getTelefono();
				String email = clienteOficinaIf.getEmail();
				if (telefono == null)		telefono = "";
				if (email == null)			email = "";
				getTxtEmail().setText(email);
				getTxtTelefono().setText(telefono);
				getTxtOficina().setText(clienteOficinaIf.getCodigo() + " - "+ clienteOficinaIf.getDescripcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert
			.createAlert(
					"Se ha producido un error al consultar la oficina del cliente",
					SpiritAlert.ERROR);
		}


		System.out.println("clietne OFICINA!-<"+clienteOficinaIf.getTelefono());
		corporacionIf = null;
		Iterator clienteIt;
		try {
			clienteIt = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(clienteOficinaId).iterator();
			if (clienteIt.hasNext()) {
				clienteIf = (ClienteIf) clienteIt.next();
				getTxtNombre().setText(clienteIf.getNombreLegal());
				find = true;
				getTxtCedula().setText(clienteIf.getIdentificacion());
				try {
					corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
					getTxtCorporacion().setText(corporacionIf.getCodigo() + " - "+ corporacionIf.getNombre());
				} catch (GenericBusinessException e) {
					SpiritAlert
					.createAlert(
							"Se ha producido un error al consultar la Coroporación del cliente",
							SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		} catch (GenericBusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return find;
	}

	private boolean setearClientePorId(Long idcliente) {
		boolean find = false;

		String cedulaCliente = getTxtCedula().getText();
		Iterator clienteIt;
		try {
			clienteIt = SessionServiceLocator.getClienteSessionService().findClienteById(idcliente)
			.iterator();
			if (clienteIt.hasNext()) {
				clienteIf = (ClienteIf) clienteIt.next();
				getTxtNombre().setText(clienteIf.getNombreLegal());
				getTxtCedula().setText(clienteIf.getIdentificacion());
				find = true;
				try {
					corporacionIf = SessionServiceLocator.getCorporacionSessionService()
					.getCorporacion(clienteIf.getCorporacionId());
					getTxtCorporacion().setText(
							corporacionIf.getCodigo() + " - "
							+ corporacionIf.getNombre());
				} catch (GenericBusinessException e) {
					SpiritAlert
					.createAlert(
							"Se ha producido un error al consultar la Coroporación del cliente",
							SpiritAlert.ERROR);
					e.printStackTrace();
				}
				clienteOficinaIf = null;
				try {
					Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService()
					.findClienteOficinaByClienteId(clienteIf.getId());
					if (oficinas.size() == 1) {
						clienteOficinaIf = oficinas.iterator().next();
						getTxtDireccion().setText(
								clienteOficinaIf.getDireccion());
						String telefono = clienteOficinaIf.getTelefono();
						String email = clienteOficinaIf.getEmail();
						if (telefono == null)
							telefono = "";
						if (email == null)
							email = "";
						getTxtEmail().setText(email);
						getTxtTelefono().setText(telefono);
						getTxtOficina().setText(
								clienteOficinaIf.getCodigo() + " - "
								+ clienteOficinaIf.getDescripcion());
					}
				} catch (Exception e) {
					e.printStackTrace();
					SpiritAlert
					.createAlert(
							"Se ha producido un error al consultar la oficina del cliente",
							SpiritAlert.ERROR);
				}
			}

		} catch (GenericBusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return find;
	}

	private void setearDocumento(String tipo) {
		desmarcarCuadroBoton(getBtnFactura());
		desmarcarCuadroBoton(getBtnNotaVenta());
		desmarcarCuadroBoton(getBtnAnticipo());
		desmarcarCuadroBoton(getBtnDevolucion());
		if (getLblTipoDocumentoSeleccionado().getText().equals("devolucion"))
			clean();

		if (tipo.equals("facturar")) {
			activarBotonesDevolucion(false);
			if (getLblTipoDocumentoSeleccionado().getText().equals("facturar")) {
				getLblTipoDocumentoSeleccionado().setText("");
			} else {
				getLblTipoDocumentoSeleccionado().setText("facturar");
				marcarCuadroBoton(getBtnFactura());
				if (getTxtNombre().getText().equals("")) {
					SpiritAlert.createAlert("Debe ingresar datos del cliente",SpiritAlert.INFORMATION);
					marcarPanel(getPnCliente());
					getTxtCedula().grabFocus();
				} else {
					if (getTxtCedula().getText().equals("9999999999")) {
						getTxtCedula().setText("");
						clean_cliente();
						getTxtCedula().grabFocus();
					}
				}
			}
		}
		if (tipo.equals("notaventa")) {
			activarBotonesDevolucion(false);
			if (getLblTipoDocumentoSeleccionado().getText().equals("notaventa")) {
				getLblTipoDocumentoSeleccionado().setText("");
			} else {
				marcarCuadroBoton(getBtnNotaVenta());
				getLblTipoDocumentoSeleccionado().setText("notaventa");

				if (getTxtNombre().getText().equals("")) {
					SpiritAlert.createAlert("Datos llenados automaticamente",
							SpiritAlert.INFORMATION);
					boolean find = setearCliente("1");// 1= indica que es
					// consumidor final

				}

			}
		}
		if (tipo.equals("anticipo")) {
			activarBotonesDevolucion(false);
			if (getLblTipoDocumentoSeleccionado().getText().equals("anticipo")) {

				getLblTipoDocumentoSeleccionado().setText("");
			} else {
				marcarCuadroBoton(getBtnAnticipo());
				getLblTipoDocumentoSeleccionado().setText("anticipo");
			}
		}
		if (tipo.equals("devolucion")) {

			if (getLblTipoDocumentoSeleccionado().getText().equals("devolucion")) {
				clean();
				activarBotonesDevolucion(false);
				getTxtNumDocumento().setText("");
				getLblTipoDocumentoSeleccionado().setText("");

				getLblCantidadDevuelta().setVisible(false);
				getTxtCantidadDevuelta().setVisible(false);
			} else {
				clean();
				marcarCuadroBoton(getBtnDevolucion());
				getLblTipoDocumentoSeleccionado().setText("devolucion");
				getLblPreImpreso().setText("PreImpreso:");
				getTxtDonacion().setText("0.00");

				getLblCantidadDevuelta().setVisible(true);
				getTxtCantidadDevuelta().setVisible(true);

				getTxtCantidad().setEnabled(false);
				getTxtCantidadDetalle().setEnabled(false);

				TableColumn anchoColumna = getTblVentaDetalle()
				.getColumnModel().getColumn(1);
				anchoColumna.setWidth(150);
				anchoColumna.setMinWidth(150);

				anchoColumna = getTblVentaDetalle().getColumnModel().getColumn(
						2);
				anchoColumna.setWidth(30);
				anchoColumna.setMinWidth(30);

				anchoColumna = getTblVentaDetalle().getColumnModel().getColumn(
						3);
				anchoColumna.setMinWidth(45);
				anchoColumna.setMaxWidth(45);
				TableCellRenderer renderer = new JComponentTableCellRenderer();
				Border headerBorder = UIManager
				.getBorder("TableHeader.cellBorder");
				JLabel headerLabel1 = new JLabel("DEV", JLabel.CENTER);
				headerLabel1.setBorder(headerBorder);
				anchoColumna.setHeaderRenderer(renderer);
				anchoColumna.setHeaderValue(headerLabel1);

				anchoColumna.setMinWidth(45);
				anchoColumna.setWidth(45);
				anchoColumna.setPreferredWidth(45);

				activarBotonesDevolucion(true);
				//getTxtNumDocumento().grabFocus();
				getCmbTipoDocDevolucion().grabFocus();
			}
		}
		/*
		 * else{ limpiar_tabla_devoluciones(); }
		 */

		if (!getLblTipoDocumentoSeleccionado().getText().equals(""))
			getPnTransaccion().setBorder(
					new TitledBorder(new BevelBorder(BevelBorder.LOWERED,
							new Color(153, 51, 0), null, new Color(153, 51, 0),
							null), "Tipo de Transacci\u00f3n",
							TitledBorder.LEADING, TitledBorder.TOP, new Font(
									"Tahoma", Font.BOLD, 12)));

	}

	public void desmarcarCuadroBoton(JButton cmp) {

		Border redline2 = BorderFactory.createMatteBorder(0, 0, 0, 0,Color.BLACK);
		cmp.setBorder(redline2);

	}

	public void activarBotonesDevolucion(boolean visible) {

		getBtnBuscar().setEnabled(!visible);
		getTxtCodigoBarras().setEnabled(!visible);
		getBtnBuscar().setEnabled(!visible);
		getBtnActualizarCliente().setEnabled(!visible);
		getBtnDonacion().setEnabled(!visible);
		getBtnBuscarProducto().setVisible(!visible);
		getPnFormasPago().setEnabled(!visible);

		getTxtCedula().setEnabled(!visible);
		getTxtTelefono().setEnabled(!visible);

		getCmbVendedor().setEnabled(!visible);

		// botones de pago
		getBtnEfectivo().setEnabled(!visible);
		getBtnGiftcard().setEnabled(!visible);

		getBtnTarjetaCredito().setEnabled(!visible);
		getBtnCredito().setEnabled(!visible);
		getBtnBorrar().setEnabled(!visible);
		getBtnDebito().setEnabled(!visible);
		getBtnDebito().setEnabled(false);
		getBtnCheque().setEnabled(!visible);
		//getBtnCheque().setEnabled(false);

		getLblBuscarDocumento().setVisible(visible);
		// getLabel15().setVisible(visible);
		getTxtNumDocumento().setVisible(visible);
		getBtnAcreditar().setEnabled(visible);
		getPnNumeroDocumento().setVisible(visible);

		getBtnHistorial().setEnabled(!visible);
		getBtnTransferirTarjetaAfiliacion().setEnabled(!visible);
		getBtnCancelar().setEnabled(!visible);

		getTxtNumDocumento().setVisible(visible);

		getBtnFactura().setEnabled(!visible);
		getBtnNotaVenta().setEnabled(!visible);
		getBtnAnticipo().setEnabled(!visible);

	}

	public void marcarCuadroBoton(JButton cmp) {
		Border compound = null;
		Border raisedbevel, loweredbevel;
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border redline2 = BorderFactory.createMatteBorder(2, 2, 2, 2,Color.BLUE);
		compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		// Add a red outline to the frame.
		compound = BorderFactory.createCompoundBorder(redline2, compound);
		cmp.setBorder(compound);
	}

	private void cargarComboVendedores() {
		getCmbVendedor().removeAllItems();
		ArrayList vendedores = (ArrayList) DeepCopy.copy(this.vendedores);
		SpiritComboBoxModel cmbModelVendedores;
		cmbModelVendedores = new SpiritComboBoxModel(vendedores);
		//EmpleadoEJB sinVendedor = new EmpleadoEJB();

		EmpleadoData sinVendedor = new EmpleadoData();
		sinVendedor.setApellidos("VENDEDOR");
		sinVendedor.setNombres("SIN");
		sinVendedor.setIdentificacion("-");
		sinVendedor.setCodigo("-");
		cmbModelVendedores.addElement(sinVendedor);
		getCmbVendedor().setModel(cmbModelVendedores);
		getCmbVendedor().repaint();
		getCmbVendedor().validate();
	}

	//verifica que el usuario tiene asociada un punto de impresion, caja
	private void setearCajaCajero() {
		try {
			UsuarioIf usergeneral = (UsuarioIf) Parametros.getUsuarioIf();
			Long usuarioId = usergeneral.getId();
			List cajas = (List) SessionServiceLocator.getCajaSessionService().findCajaByUsuarioId(usuarioId);
			if (cajas.size() > 0) cajaIf = ((CajaIf) cajas.get(0));
			if (cajaIf == null) {
				SpiritAlert.createAlert("Usuario no tiene asociado punto de impresión", SpiritAlert.ERROR);
			} else {
				empleado = (EmpleadoIf) DeepCopy.copy(empleadoCajero);
				getTxtCajero().setText(empleado.getApellidos() + " " + empleado.getNombres());
				getTxtCaja().setText(cajaIf.getNombre());
			}
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}



	// va seteando el valor final de la Deuda, cuando esta llega a 0.00 entonces
	// automáticamente muestra una ventana con el resumen de la venta
	private void setDeudaFinal(String deuda) {
		deuda=Utilitarios.removeDecimalFormat(deuda);
		String valor = getTxtTotalFinal().getText();
		getTxtDeuda().setText(formatoDecimal.format(Utilitarios.redondeoValor(new Double(deuda).doubleValue())));
		if (!valor.equals("0.00") && deuda.equals("0.00"))		resumen_Dialog("A");

	}

	// Elimina los datos de la tabla, hay que tomar en cuenta que tipo de transacción esta escogida.
	private void eliminarDetalleFactura(String actualizar) {
		if (getTblVentaDetalle().getSelectedRow() != -1) {
			String devolucion = "";
			if (getLblTipoDocumentoSeleccionado().getText()	.equals("devolucion"))
				devolucion = "dev";
			int filaSeleccionada = getTblVentaDetalle().getSelectedRow();

			if (actualizar.equals("S"))// borro del valor final la cantidad
				// original
			{
				FacturaDetalleData detalleRemovido = (FacturaDetalleData) ProductosidReunionColeccion_DEVOLUCIONES.get(filaSeleccionada);
				ProductosidReunionColeccion_DEVOLUCIONES.remove(filaSeleccionada);

			} else {

				ProductosidReunionColeccion_DEVOLUCIONES.remove(filaSeleccionada);
				//viejo:llenar_totalGeneral("R", "");
				llenar_totalGeneral();

				clean_producto();
			}

			tableModel.removeRow(filaSeleccionada);			
			//getTblVentaDetalle().remove(filaSeleccionada);


			productoIf = null;
		} else
			SpiritAlert.createAlert(
					"Debe seleccionar el detalle a eliminar !!!",
					SpiritAlert.WARNING);
	}


	private void eliminarDetallePedido(String actualizar, String bandera) {
		if (getTblVentaDetalle().getSelectedRow() != -1) {
			String devolucion = "";
			if (getLblTipoDocumentoSeleccionado().getText().equals("devolucion"))	devolucion = "dev";

			int filaSeleccionada = getTblVentaDetalle().getSelectedRow();

			if (actualizar.equals("S")) {
				PedidoDetalleIf detalleRemovido = (PedidoDetalleIf) ProductosidReunionColeccion.get(filaSeleccionada);
				ProductosidReunionColeccion.remove(filaSeleccionada);

			} else {

				if (devolucion.equals("dev")) {
					ProductosidReunionColeccion_DEVOLUCIONES.remove(filaSeleccionada);
					getTxtDonacion().setText("0");

				} else {
					// voy almacenando que items he eliminado, me sirve al momento de actualizar el pedido

					PedidoDetalleIf detalleRemovido2 = (PedidoDetalleIf) ProductosidReunionColeccion.get(filaSeleccionada);
					ProductosidReunionColeccion_eliminados.add(detalleRemovido2);
					ProductosidReunionColeccion.remove(filaSeleccionada);
					ProductoIf producto = (ProductoIf) productosMap.get(detalleRemovido2.getProductoId());
					GenericoIf generico = (GenericoIf) genericosMap.get(producto.getGenericoId());
					TipoProductoIf tipoProducto = (TipoProductoIf) tiposProductoByIdMap.get(generico.getTipoproductoId());
					if (tipoProducto.getCodigo().equals("TA"))
						ProductosidReunionColeccion_TA.clear();

				}				
				llenar_totalGeneral();
				clean_producto();
			}

			tableModel.removeRow(filaSeleccionada);
			llenar_totalGeneral();

			getTxtItem().setText("");
			productoIf = null;
		} else

			if(bandera.equals(""))
				SpiritAlert.createAlert(
						"Debe seleccionar el detalle a eliminar !!!",
						SpiritAlert.WARNING);
	}


	// verifica que la caja no haya sido cerrado, ni bloqueado.
	public void Caja_abierta_id(String estado) { // cual es el ID de la caja, de este usuario(cajero)

		Long caja_id = new Long("0");
		Long usuario_id = ((UsuarioIf) Parametros.getUsuarioIf()).getId();
		Map aMap = new HashMap();
		aMap.clear();
		aMap.put("usuarioId", usuario_id);
		aMap.put("estado", estado);
		aMap.put("fechafin", null);
		Iterator cajavalorIt;
		try {
			cajavalorIt = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();
			if (cajavalorIt.hasNext()) {
				CajaSesionIf valor_actual = (CajaSesionIf) cajavalorIt.next();
				BigDecimal valor = valor_actual.getValorInicial();
				caja_id = valor_actual.getId();
				getBtnEfectivo().setEnabled(true);
				getBtnBuscarProducto().setEnabled(true);
				getBtnBuscar().setEnabled(true);
				getTxtCodigoBarras().setEnabled(true);			 
				setearCajaCajero();
				DESBloquearPantalla();
			} else {
				BloquearPantalla();
				SpiritAlert.createAlert("Debe tener una caja abierta",SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cajaAbiertaID = caja_id;
	}

	public void BloquearPantalla() {

		// panel de cobrar
		getBtnEfectivo().setEnabled(false);
		getBtnDebito().setEnabled(false);
		getBtnTarjetaCredito().setEnabled(false);
		getBtnGiftcard().setEnabled(false);

		//getBtnCheque().setEnabled(true);
		getBtnCheque().setEnabled(false);
		getBtnCredito().setEnabled(false);
		getBtnBorrar().setEnabled(false);
		// panel acciones
		getBtnHistorial().setEnabled(true);
		getBtnTransferirTarjetaAfiliacion().setEnabled(false);
		getBtnCancelar().setEnabled(false);
		getBtnDonacion().setEnabled(false);
		getBtnCaja().setEnabled(false);
		getBtnAcreditar().setEnabled(false);
		// tipo de transaccion
		getBtnFactura().setEnabled(false);
		getBtnNotaVenta().setEnabled(false);
		getBtnAnticipo().setEnabled(false);
		getBtnDevolucion().setEnabled(false);
		// panel clientes
		getBtnBuscar().setEnabled(false);
		getBtnActualizarCliente().setEnabled(false);
		getTxtCedula().setEnabled(false);
		getTxtTelefono().setEnabled(false);
		// ingreso de codigo de barras
		getTxtCodigoBarras().setEnabled(false);
		// bloquear botones
		getBtnAbrir().setEnabled(false);
		getBtnEliminar().setEnabled(false);
		getBtnBuscarProducto().setEnabled(false);

		getTxtCantidadDetalle().setEnabled(false);

		getCmbVendedor().setEnabled(false);

		getTblVentaDetalle().setEnabled(false);
	}

	public void DESBloquearPantalla() {

		// panel de cobrar
		getBtnEfectivo().setEnabled(true);
		getBtnDebito().setEnabled(false);
		getBtnTarjetaCredito().setEnabled(true);
		getBtnGiftcard().setEnabled(true);

		getBtnCheque().setEnabled(true);
		//getBtnCheque().setEnabled(false);
		// panel acciones
		getBtnHistorial().setEnabled(true);
		getBtnTransferirTarjetaAfiliacion().setEnabled(true);
		getBtnCancelar().setEnabled(true);
		getBtnDonacion().setEnabled(true);
		getBtnCaja().setEnabled(true);
		getBtnAcreditar().setEnabled(false);
		// tipo de transaccion
		getBtnFactura().setEnabled(true);
		getBtnNotaVenta().setEnabled(true);
		getBtnAnticipo().setEnabled(true);
		getBtnDevolucion().setEnabled(true);
		// panel clientes
		getBtnBuscar().setEnabled(true);
		getBtnActualizarCliente().setEnabled(true);
		getTxtCedula().setEnabled(true);
		// ingreso de codigo de barras
		getTxtCodigoBarras().setEnabled(true);
		// bloquear botones

		getBtnAbrir().setEnabled(true);
		getBtnEliminar().setEnabled(true);
		getBtnBuscarProducto().setEnabled(true);

		getTxtCantidadDetalle().setEnabled(true);

		getTblVentaDetalle().setEnabled(true);
	}

	private void setearPuntoImpresion(String codigo) {
		try {
			// if (getCmbTipoDocumento().getSelectedItem() != null &&
			// cajas.size()>0) {
			// cambios: se comento la linea de arriba
			TipoDocumentoIf tipoDocumentoIf = findTipoDocumentoByCodigo(codigo);

			Collection puntoI = SessionServiceLocator.getPuntoImpresionSessionService()
			.findPuntoImpresionByTipoDocumentoAndByCajaId(
					tipoDocumentoIf.getId(), cajaIf.getId());
			if (puntoI.size() != 0) {
				PuntoImpresionIf puntoImpresion = (PuntoImpresionIf) puntoI
				.iterator().next();
				Collection usuarioPuntoI = SessionServiceLocator
				.getUsuarioPuntoImpresionSessionService()
				.findUsuarioPuntoImpresionByPuntoImpresionAndByUsuarioId(
						puntoImpresion.getId(),
						((UsuarioIf) Parametros.getUsuarioIf()).getId());
				if (usuarioPuntoI.size() != 0) {
					UsuarioPuntoImpresionIf usuarioPuntoImpresion = (UsuarioPuntoImpresionIf) usuarioPuntoI
					.iterator().next();
					puntoImpresionIf = SessionServiceLocator
					.getPuntoImpresionSessionService()
					.getPuntoImpresion(
							usuarioPuntoImpresion.getPuntoimpresionId());
					// getTxtPuntoImpresion().setText(puntoImpresionIf.getSerie());
				}
			} else {
				puntoImpresionIf = null;
				// getTxtPuntoImpresion().setText("");
			}
			// }
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

//	dialgo para las Formas de pago:
	// ////////////////////////// DIALOGOS ///////////////////////////////////

	private boolean validar_datos_llenos(String estado) {
		boolean todook = true;
		boolean isSale = isSale();

		Caja_abierta_id("A");

		if (cajaAbiertaID.equals(new Long("0"))) {
			SpiritAlert.createAlert("Debe tener una caja abierta",SpiritAlert.INFORMATION);
			getBtnEfectivo().setEnabled(false);		
			todook = false;
		} else {
			if (getTxtTotalFinal().getText().equals("0")|| getTxtTotalFinal().getText().equals("0.00")) {
				SpiritAlert.createAlert("Debe tener productos agregados o con PRECIO mayor que cero",SpiritAlert.INFORMATION);
				getTxtCodigoBarras().grabFocus();
				todook = false;
			}

			if (getLblTipoDocumentoSeleccionado().getText().equals("")) {
				if (estado.equals("C"))// cancelo la venta
				{
					if (getTxtNombre().getText().equals(""))
						setearCliente("1");
					setearDocumento("notaventa");
				} else if (isSale) {
					SpiritAlert.createAlert("Debe escoger un tipo de transacción",SpiritAlert.INFORMATION);
					marcarPanel(getPnTransaccion());
					todook = false;
				}
			}
			
			if (getTxtNombre().getText().equals("")) {
				if (estado.equals("C"))// cancelo la venta
				{
					setearCliente("1");
				} else {
					SpiritAlert.createAlert("Debe ingresar datos del cliente",SpiritAlert.INFORMATION);
					marcarPanel(getPnCliente());
					getTxtCedula().grabFocus();
					todook = false;
				}
			}

			if (getCmbVendedor().getSelectedItem() == null) {
				if (estado.equals("C"))// cancelo la venta
				{
					UsuarioIf usergeneral = (UsuarioIf) Parametros.getUsuarioIf();
					Long empleadoId = usergeneral.getEmpleadoId();					
					getCmbVendedor().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbVendedor(), empleadoId));
				} else {
					todook = false;
					SpiritAlert.createAlert("Debe escoger a un Vendedor",SpiritAlert.INFORMATION);
					marcarPanel(getPnVendedor());
					getCmbVendedor().grabFocus();
				}
			} else {
				Border compound = null;
				getPnVendedor().setBorder(compound);
			}

			if (idfundacion == null && isSale && !tipoDocumentoTransaccion.getCodigo().equals("RCA")) {
				if (!estado.equals("C")) {
					todook = false;
					donacion_Dialog();
				}
			}
			// falta: poner lo de la donacion
			
			String identificacion = clienteIf.getIdentificacion();
			String codigoOficina = clienteOficinaIf.getCodigo();
			try {
				TarjetaIf tarjeta = SessionServiceLocator.getTarjetaSessionService().findTarjetaWebService(identificacion, codigoOficina, Parametros.getIdEmpresa());
				if (tarjeta != null) {
					/*if (getLblTipoDocumentoSeleccionado().getText().equals("facturar")) {
						int size = ProductosidReunionColeccion_FAC.size();
						for (int i=0; i<size; i++) {
							PedidoDetalleIf detalle = ProductosidReunionColeccion_FAC.get(i);
							ProductoIf producto = (ProductoIf) productosMap.get(detalle.getProductoId());
							GenericoIf generico = (GenericoIf) genericosMap.get(producto.getGenericoId());
							TipoProductoIf tipoProducto = (TipoProductoIf) tiposProductoByIdMap.get(generico.getTipoproductoId());
							if (tipoProducto.getCodigo().equals("TA")) {
								todook = false;
								SpiritAlert.createAlert("Cliente seleccionado tiene actualmente tarjeta de afiliación,\nno puede adquirir este producto otra vez.",SpiritAlert.INFORMATION);
							}
						}
					}*/
					if (ProductosidReunionColeccion_TA.size() > 0) {
						todook = false;
						SpiritAlert.createAlert("Cliente seleccionado tiene actualmente tarjeta de afiliación,\nno puede adquirir este producto otra vez.",SpiritAlert.INFORMATION);
					}
					
					if (ProductosidReunionColeccion_TA.size() > 1) {
						todook = false;
						SpiritAlert.createAlert("Sólo se puede adquirir una tarjeta de afiliación.",SpiritAlert.INFORMATION);
					}
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			} 
			
		}
		return todook;
	}
	
	private boolean isSale() {
		Iterator it = ProductosidReunionColeccion.iterator();
		while (it.hasNext()) {
			PedidoDetalleIf detalle = (PedidoDetalleIf) it.next();
			if (detalle.getGiftcardId() == null)
				return true;
		}
		
		return false;
	}

	//AFILIACION
	private void cobroAfiliacionReferidoDialog() {

		boolean todolleno = false;
		todolleno = validar_datos_llenos("A");
		if (todolleno) {


			if (!getTxtCedula().getText().equals("9999999999")) {



				BigDecimal totalPuntos = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtPuntos().getText()));
				BigDecimal deuda_disponible = valores_cancelado();
				deuda_disponible = deuda_disponible.add(totalPuntos);

				String nombreCliente = clienteIf.getNombreLegal();
				String identificacionCliente = clienteIf.getIdentificacion();
				//String emailCliente = clienteOficinaIf.getEmail();

				//System.out.println("emailcliente"+emailCliente);


				if(clienteIf.getIdentificacion()!=null) identificacionCliente=clienteIf.getIdentificacion();

				jdPagoTarjetaAfiliacionReferidoModel = new PagoTarjetaAfiliacionReferidoModel(Parametros
						.getMainFrame(), deuda_disponible.toString(),
						nombreCliente, clienteOficinaIf.getId().toString(),Parametros.getIdEmpresa(),identificacionCliente,PagosCollection_detalles);

				jdPagoTarjetaAfiliacionReferidoModel.addWindowListener(wl);
				int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
				int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
				jdPagoTarjetaAfiliacionReferidoModel.setLocation(x, y);
				jdPagoTarjetaAfiliacionReferidoModel.pack();
				jdPagoTarjetaAfiliacionReferidoModel.setModal(true);
				jdPagoTarjetaAfiliacionReferidoModel.setVisible(true);

				if (jdPagoTarjetaAfiliacionReferidoModel.isAceptar()) {
					BigDecimal cc_valor = jdPagoTarjetaAfiliacionReferidoModel.getMonto();

					getTxtPuntos().setText(formatoDecimal.format(jdPagoTarjetaAfiliacionReferidoModel.getMonto()));

					if (PagosCollection_detalles.size() != 0) {
						for (int l = 0; l < PagosCollection_detalles.size(); l++) {
							String tipoPuntos = ((Vector) PagosCollection_detalles.get(l)).get(0).toString();
							if (tipoPuntos.equals("PT"))PagosCollection_detalles.remove(l);
						}
					}

					boolean aplicoPuntos=false;
					if(jdPagoTarjetaAfiliacionReferidoModel.getReferido().equals("N"))//la usa el propietario
					{

						if(!jdPagoTarjetaAfiliacionReferidoModel.getMonto().toString().equals("0.00") && !jdPagoTarjetaAfiliacionReferidoModel.getMonto().toString().equals("0"))
						{
							PagosCollection = new Vector<String>();
							PagosCollection.add(0, "T/Afiliac.");
							PagosCollection.add(1, jdPagoTarjetaAfiliacionReferidoModel.getMonto().toString());//PUNTOS EN DINERO
							PagosCollection.add(2, identificacionCliente);
							PagosCollection.add(3, identificacionCliente);
							PagosCollection.add(4, "PT");// TIPO_PAGO 2:crédito
							PagosCollection.add(5, jdPagoTarjetaAfiliacionReferidoModel.getAplicarPuntosDinero().toString());//CUANTOS PUNTOS USO
							PagosCollection.add(6, "");
							PagosCollection.add(7, "");
							PagosCollection_detalles.add(PagosCollection);

							System.out.println(jdPagoTarjetaAfiliacionReferidoModel.getMonto()+"<<<akp!>"+jdPagoTarjetaAfiliacionReferidoModel.getBalance());
							System.out.println(jdPagoTarjetaAfiliacionReferidoModel.getMonto()+"<<<akp!>"+jdPagoTarjetaAfiliacionReferidoModel.getBalance());
							aplicoPuntos=true;
							setDeudaFinal(formatoDecimal.format(jdPagoTarjetaAfiliacionReferidoModel.getBalance()));	
						}
					}

					//se aplica el descuento por tipo de tarjeta ya sea Propietario o Referido
					if(jdPagoTarjetaAfiliacionReferidoModel.getDescuentoTipoTarjeta()!=null)
					{
						String dsctoTipoTarjeta=jdPagoTarjetaAfiliacionReferidoModel.getDescuentoTipoTarjeta();
						getTxtPorcentajeDescuento().setText(dsctoTipoTarjeta);
						aplicarDescuento();						
					}					
					//se lo hace aca... para que sea despues del descuento por tipo de tarjeta 
					if(aplicoPuntos)
						setDeudaFinal(formatoDecimal.format(jdPagoTarjetaAfiliacionReferidoModel.getBalance()));					

					//AQUI GUARDANDO DATOS PARA TARJETA_TRANSACCION: AFILIACION			 			 
					TarjetasCollection = new Vector<String>();
					TarjetasCollection.add(0,"Referido/Propietario");
					TarjetasCollection.add(1,jdPagoTarjetaAfiliacionReferidoModel.getCedulaDuenoTarjeta());
					TarjetasCollection.add(2,jdPagoTarjetaAfiliacionReferidoModel.getIdentificacionDuenoTarjeta());//identificacion del dueño de la tarjeta
					TarjetasCollection.add(3,jdPagoTarjetaAfiliacionReferidoModel.getReferido());
					TarjetasCollection.add(4,jdPagoTarjetaAfiliacionReferidoModel.getUsadorporClienteOficinaId());// 
					TarjetasCollection.add(5,jdPagoTarjetaAfiliacionReferidoModel.getAplicarPuntosDinero().toString());							
					TarjetasCollection.add(6,"");//actualizar puntos ganados
					TarjetasCollection.add(7,"");//actualiza a facturaId
					TarjetasCollection.add(8,jdPagoTarjetaAfiliacionReferidoModel.getTipoDuenoTarjeta());//tarjetaTIPO: ta-gold , ta-platinium
					TarjetasCollection_detalles.add(0,TarjetasCollection);						

				} else {
					TarjetasCollection_detalles.clear();
				}
			} else {
				SpiritAlert.createAlert("Para poder usar datos de Referido o Tarjeta de Afiliación debe ingresar datos del cliente",SpiritAlert.INFORMATION);
				getTxtCedula().grabFocus();
			}
		}
	}

	private void cobroCreditoDialog() {

		boolean todolleno = false;
		todolleno = validar_datos_llenos("A");
		if (todolleno) {
			if (!getTxtCedula().getText().equals("9999999999")) {
				BigDecimal total_CC = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtCreditoCliente().getText()));
				BigDecimal deuda_disponible = valores_cancelado();
				deuda_disponible = deuda_disponible.add(total_CC);
				String nombre_cliente = clienteIf.getNombreLegal();
				String cedula_cliente = clienteIf.getIdentificacion();
				jdPagoCreditoCliente = new PagoCreditoClienteModel(Parametros
						.getMainFrame(), deuda_disponible.toString(),nombre_cliente, clienteOficinaIf.getId().toString(),Parametros.getIdEmpresa());
				jdPagoCreditoCliente.addWindowListener(wl);
				int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
				int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
				jdPagoCreditoCliente.setLocation(x, y);
				jdPagoCreditoCliente.pack();
				jdPagoCreditoCliente.setModal(true);
				jdPagoCreditoCliente.setVisible(true);
				if (jdPagoCreditoCliente.isAceptar()) {
					BigDecimal cc_valor = jdPagoCreditoCliente.getTotal_monto();
					getTxtCreditoCliente().setText(
							formatoDecimal.format(jdPagoCreditoCliente
									.getTotal_monto()));
					if (PagosCollection_detalles.size() != 0) {
						for (int l = 0; l < PagosCollection_detalles.size(); l++) {
							String tipo_cc = ((Vector) PagosCollection_detalles
									.get(l)).get(0).toString();
							if (tipo_cc.equals("Crédito Cliente"))
								PagosCollection_detalles.remove(l);
						}
					}
					PagosCollection = new Vector<String>();
					PagosCollection.add(0, "Crédito Cliente");
					PagosCollection.add(1, jdPagoCreditoCliente.getTotal_monto().toString());
					PagosCollection.add(2, nombre_cliente);
					PagosCollection.add(3, cedula_cliente);
					PagosCollection.add(4, "CR");// TIPO_PAGO 2:crédito
					PagosCollection.add(5, "");
					PagosCollection.add(6, "");
					PagosCollection.add(7, "");
					PagosCollection_detalles.add(PagosCollection);
					setDeudaFinal(formatoDecimal.format(jdPagoCreditoCliente.getBalance()));
				}
			} else {
				SpiritAlert
				.createAlert(
						"Para poder usar el Crédito del Cliente debe ingresar datos del cliente",
						SpiritAlert.INFORMATION);
				getTxtCedula().grabFocus();
			}
		}
	}

	private void giftCardDialog() {
		boolean todolleno = false;
		todolleno = validar_datos_llenos("A");
		if (todolleno) {
			BigDecimal total_GC = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtGiftcard().getText()));
			BigDecimal deuda_disponible = valores_cancelado();
			jdPagoGiftCard = new PagoGiftCardsModel(Parametros.getMainFrame(), deuda_disponible.toString(), PagosCollection_detalles);
			jdPagoGiftCard.addWindowListener(wl);
			int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
			int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
			jdPagoGiftCard.setLocation(x, y);
			jdPagoGiftCard.pack();
			jdPagoGiftCard.setModal(true);
			jdPagoGiftCard.setVisible(true);
			if (jdPagoGiftCard.isAceptar()) {
				BigDecimal gc_valor = jdPagoGiftCard.getTotal_monto();
				boolean giftcardId_repetido = false;
				giftcardId_repetido = jdPagoGiftCard.isBorrar_repetido();
				if (giftcardId_repetido) {
					BigDecimal valor_anterior = jdPagoGiftCard.getValorAnterior();
					total_GC = total_GC.subtract(valor_anterior);
					if (PagosCollection_detalles.size() != 0) {
						for (int l = 0; l < PagosCollection_detalles.size(); l++) {
							String tipoPagoGiftcard = ((Vector) PagosCollection_detalles.get(l)).get(4).toString();
							String producto_gc = ((Vector) PagosCollection_detalles.get(l)).get(7).toString();
							if (tipoPagoGiftcard.equals("GC") && producto_gc.equals(jdPagoGiftCard.getGiftcardId().toString()))
								PagosCollection_detalles.remove(l);
						}
					}
				}

				BigDecimal Acumular_gc_valor = total_GC.add(gc_valor);
				getTxtGiftcard().setText(formatoDecimal.format(Acumular_gc_valor));
				PagosCollection = new Vector<String>();
				PagosCollection.add(0, "Gift Card (Código: "+ jdPagoGiftCard.getCodigoBarras() + ")");
				PagosCollection.add(1, jdPagoGiftCard.getTotal_monto().toString());
				PagosCollection.add(2, "saldo inicial: $"+ jdPagoGiftCard.getSaldoInicial().toString());
				PagosCollection.add(3, "saldo final: $"	+ jdPagoGiftCard.getSaldoFinal().toString());
				PagosCollection.add(4, "GC");// TIPO_PAGO 6:gift card
				PagosCollection.add(5, jdPagoGiftCard.getSaldoInicial().toString());
				PagosCollection.add(6, jdPagoGiftCard.getSaldoFinal().toString());
				PagosCollection.add(7, jdPagoGiftCard.getGiftcardId().toString());
				PagosCollection_detalles.add(PagosCollection);
				setDeudaFinal(formatoDecimal.format(jdPagoGiftCard.getBalance()));
			}
		}
	}

	private void cobroChequeDialog() {
		boolean todolleno = false;
		todolleno = validar_datos_llenos("A");

		if (todolleno) { // /
			BigDecimal total_cheque = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtCheque().getText()));
			BigDecimal deuda_disponible = valores_cancelado();
			// deuda_disponible=deuda_disponible.add(total_cred);

			jdPagoChequeModel = new PagoChequeModel(Parametros.getMainFrame(),
					deuda_disponible.toString());
			jdPagoChequeModel.addWindowListener(wl);
			int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
			int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
			jdPagoChequeModel.setLocation(x, y);
			jdPagoChequeModel.pack();
			jdPagoChequeModel.setModal(true);
			jdPagoChequeModel.setVisible(true);

			if (jdPagoChequeModel.isAceptar()) {
				BigDecimal total_CH = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtCheque().getText()));
				BigDecimal tcH_valor = jdPagoChequeModel.getMonto();
				BigDecimal Acumular_ch_valor = total_CH.add(tcH_valor);
				getTxtCheque()
				.setText(formatoDecimal.format(Acumular_ch_valor));

				PagosCollection = new Vector<String>();
				PagosCollection.add(0, "Cheque");
				PagosCollection.add(1, jdPagoChequeModel.getMonto().toString());
				PagosCollection.add(2, jdPagoChequeModel.getBanco());
				PagosCollection.add(3, "No. Cheque:"+ jdPagoChequeModel.getNumcheque());
				PagosCollection.add(4, "CH");// TIPO_PAGO 4:cheque
				PagosCollection.add(5, jdPagoChequeModel.getNumcheque());
				PagosCollection.add(6, jdPagoChequeModel.getBanco());
				PagosCollection.add(7, jdPagoChequeModel.getNumcta());
				PagosCollection_detalles.add(PagosCollection);

				setDeudaFinal(formatoDecimal.format(jdPagoChequeModel
						.getBalance()));
			}
		}
	}

	private void tarjetaCreditoDialog() {
		boolean todolleno = false;
		todolleno = validar_datos_llenos("A");
		String xu = "a";

		if (todolleno) { // /
			BigDecimal total_cred = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtTarjetaCredito().getText()));
			BigDecimal deuda_disponible = valores_cancelado();
			// deuda_disponible=deuda_disponible.add(total_cred);

			jdPagoTarjetaCredito = new PagoTarjetaCreditoTemporalModel(
					Parametros.getMainFrame(), deuda_disponible.toString(),getTxtCedula().getText());
			jdPagoTarjetaCredito.addWindowListener(wl);
			int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
			int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
			jdPagoTarjetaCredito.setLocation(x, y);
			jdPagoTarjetaCredito.pack();
			jdPagoTarjetaCredito.setModal(true);
			jdPagoTarjetaCredito.setVisible(true);

			if (jdPagoTarjetaCredito.isAceptar()) {
				BigDecimal total_TC = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtTarjetaCredito().getText()));
				BigDecimal tc_valor = jdPagoTarjetaCredito.getTotal_monto();
				BigDecimal Acumular_tc_valor = total_TC.add(tc_valor);
				getTxtTarjetaCredito().setText(formatoDecimal.format(Acumular_tc_valor));


				if(!jdPagoTarjetaCredito.getTotal_monto().toString().equals("0.00") && !jdPagoTarjetaCredito.getTotal_monto().toString().equals("0")){
					PagosCollection = new Vector<String>();
					PagosCollection.add(0, "T/C");
					PagosCollection.add(1, jdPagoTarjetaCredito.getTotal_monto().toString());
					PagosCollection.add(2, jdPagoTarjetaCredito.getNombre_tarjeta());
					PagosCollection.add(3, "Autoriz:"+ jdPagoTarjetaCredito.getCodAutorizacion() + "/"	+ jdPagoTarjetaCredito.getNoVoucher());
					PagosCollection.add(4, "TA");// TIPO_PAGO 3:tarjeta
					PagosCollection.add(5, jdPagoTarjetaCredito.getNombre_cliente());
					PagosCollection.add(6, jdPagoTarjetaCredito.getCedula_cliente());
					PagosCollection.add(7, jdPagoTarjetaCredito.getTelefono());
					PagosCollection.add(8, jdPagoTarjetaCredito.getNoReferencia());
					PagosCollection_detalles.add(PagosCollection);
					bloqueado=true;
					setDeudaFinal(formatoDecimal.format(jdPagoTarjetaCredito.getBalance()));

					String referenciaTC=jdPagoTarjetaCredito.getNoReferencia();					
				}
			}
		}
	}

	public BigDecimal valores_cancelado() {
		BigDecimal valorDeudaActual = new BigDecimal("0.00");
		BigDecimal total_fac = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtTotalFinal().getText()));
		BigDecimal pagado_efec = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtEfectivo().getText()));
		BigDecimal pagado_gc = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtGiftcard().getText()));
		BigDecimal pagado_tc = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtTarjetaCredito().getText()));
		BigDecimal pagado_td = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDebitoBancario().getText()));
		BigDecimal pagado_ch = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtCheque().getText()));
		BigDecimal pagado_cc = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtCreditoCliente().getText()));
		BigDecimal pagado_pts = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtPuntos().getText()));
		valorDeudaActual = total_fac.subtract(pagado_efec).subtract(pagado_gc)
		.subtract(pagado_tc).subtract(pagado_td).subtract(pagado_ch)
		.subtract(pagado_cc).subtract(pagado_pts);
		return valorDeudaActual;
	}

	// cuando se escoje cobrar en efectivo
	private void cobroEfectivoDialog() {
		boolean todolleno = false;
		todolleno = validar_datos_llenos("A");
		if (todolleno) {

			BigDecimal total_efec = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtEfectivo().getText()));
			BigDecimal deuda_disponible = valores_cancelado();
			deuda_disponible = deuda_disponible.add(total_efec);

			jdPagoEfectivo = new PagoEfectivoModel(Parametros.getMainFrame(),deuda_disponible.toString());
			jdPagoEfectivo.addWindowListener(wl);
			int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
			int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
			jdPagoEfectivo.setLocation(x, y);
			jdPagoEfectivo.pack();
			jdPagoEfectivo.setModal(true);
			jdPagoEfectivo.setVisible(true);

			if (jdPagoEfectivo.isAceptar()) { // TODO: setear en alguna
				// Coleccion los datos para una
				// vez q mande a procesar guarde
				// todo (pedido, factura,
				// asietnos, moviemnto pago_pos
				getTxtEfectivo()
				.setText(
						formatoDecimal.format(jdPagoEfectivo
								.getTotal_pagado()));
				if (PagosCollection_detalles.size() != 0) {
					for (int l = 0; l < PagosCollection_detalles.size(); l++) {
						String tipo_efectivo = ((Vector) PagosCollection_detalles
								.get(l)).get(0).toString();
						if (tipo_efectivo.equals("Efectivo"))
							PagosCollection_detalles.remove(l);
					}
				}
				PagosCollection = new Vector<String>();
				PagosCollection.add(0, "Efectivo");
				PagosCollection.add(1, jdPagoEfectivo.getTotal_pagado()
						.toString());
				PagosCollection.add(2, "  -  ");
				PagosCollection.add(3, "  -  ");
				PagosCollection.add(4, "EF");// TIPO_PAGO 1:efectivo
				PagosCollection.add(5, "");
				PagosCollection.add(6, "");
				PagosCollection.add(7, "");
				PagosCollection_detalles.add(PagosCollection);

				getTxtVuelto().setText(
						formatoDecimal.format(jdPagoEfectivo.getCambio()));
				setDeudaFinal(formatoDecimal
						.format(jdPagoEfectivo.getBalance()));
			}
		}
	}

	public Long getidFundacionNinosConFuturo(){
		Long id=0L;

		Iterator clienteIt;
		try {
			clienteIt = SessionServiceLocator.getClienteSessionService().findClienteByNombreLegal("FUNDACION NIÑOS CON FUTURO").iterator();
			if (clienteIt.hasNext()) {
				ClienteIf fundacionNF = (ClienteIf) clienteIt.next();
				id=fundacionNF.getId();
			}
		}
		catch (Exception e) {

		}


		return id;
	}

	private void donacion_Dialog() {

		if(escogioFundacion)
			idfundacion=idfundacion;
		else
			idfundacion=getidFundacionNinosConFuturo().toString();
		jdElegirFundacion = new FundacionDonarModel(Parametros.getMainFrame(),idfundacion);
		jdElegirFundacion.addWindowListener(wl);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
		jdElegirFundacion.setLocation(x, y);
		jdElegirFundacion.pack();
		jdElegirFundacion.setModal(true);
		jdElegirFundacion.setVisible(true);
		idfundacion = jdElegirFundacion.getIdfundacion();
		if(idfundacion!=null)
			escogioFundacion=true;

	}

	//esto si se lo hace a nivel de sucursal.
	public BigDecimal valorTipoProductoTipoTarjeta(String tipoRP,String acumularPuntosDinero,String acumularTipoProductoTipoTarjeta,String tipoProducto,String tarjetatipoId){
		BigDecimal valor=BigDecimal.ZERO;
		try {
			if(tipoRP.equals("S")) {//refirió
				if (acumularTipoProductoTipoTarjeta.equals("P")) {
					String valorTipoProducto=(String)parametrosMapReferido.get(tipoProducto);
					if (valorTipoProducto==null) {			
						Map parameterMap = new HashMap();
						parameterMap.put("tipoProductoId",Long.parseLong(tipoProducto));
						parameterMap.put("tarjetaTipoId",Long.parseLong(tarjetatipoId));				 
						Iterator it;
						it = SessionServiceLocator.getPuntosTipoProductoSessionService().findPuntosTipoProductoByQuery(parameterMap).iterator();				
						if (it.hasNext()) {
							PuntosTipoProductoIf refer = (PuntosTipoProductoIf) it.next();
							if (acumularPuntosDinero.equals("P")) {
								String puntosReferidos=refer.getPuntosReferido().toString();
								parametrosMapReferido.put(tipoProducto, puntosReferidos);
								valor=new BigDecimal(puntosReferidos);
							} else if (acumularPuntosDinero.equals("D")) {
								String porcentajeDineroReferido = refer.getPorcentajeDineroReferido().toString();
								parametrosMapReferido.put(tipoProducto, porcentajeDineroReferido);
								valor=new BigDecimal(porcentajeDineroReferido);
							}
						}
					} else {
						valor=new BigDecimal(valorTipoProducto);
					}	
				} else if (acumularTipoProductoTipoTarjeta.equals("T")) {
					String valorTipoTarjeta = (String)parametrosMapReferido.get(tarjetatipoId);
					if (valorTipoTarjeta==null) {
						if (acumularPuntosDinero.equals("D")) {
							TarjetaTipoIf tarjetaTipo = SessionServiceLocator.getTarjetaTipoSessionService().getTarjetaTipo(Long.parseLong(tarjetatipoId));
							String porcentajeDineroReferido = tarjetaTipo.getPorcentajeDineroReferido().toString();
							parametrosMapReferido.put(tarjetatipoId, porcentajeDineroReferido);
							valor=new BigDecimal(porcentajeDineroReferido);
						} else if (acumularPuntosDinero.equals("P")) {
							// ESTO HAY QUE PENSARLO BIEN PARA DEFINIR COMO FUNCIONARIA
						}
					} else {
						valor=new BigDecimal(valorTipoTarjeta);
					}
				}
			} else {//propietario gana puntos	//valor N= propietario
				if (acumularTipoProductoTipoTarjeta.equals("P")) {
					String valorTipoProducto=(String)parametrosMapPropietario.get(tipoProducto);			
					if(valorTipoProducto==null){			
						Map parameterMap = new HashMap();
						parameterMap.put("tipoProductoId",new Long(tipoProducto));
						parameterMap.put("tarjetaTipoId",new Long(tarjetatipoId));					
						Iterator it;
						it = SessionServiceLocator.getPuntosTipoProductoSessionService().findPuntosTipoProductoByQuery(parameterMap).iterator();					
						if (it.hasNext()) {
							PuntosTipoProductoIf propietario = (PuntosTipoProductoIf) it.next();
							if (acumularPuntosDinero.equals("P")) {
								String puntosPropietario=propietario.getPuntosCompras().toString();
								parametrosMapPropietario.put(tipoProducto, puntosPropietario);
								valor=new BigDecimal(puntosPropietario);
							} else if (acumularPuntosDinero.equals("D")) {
								String porcentajeDineroPropietario = propietario.getPorcentajeDineroCompras().toString();
								parametrosMapPropietario.put(tipoProducto, porcentajeDineroPropietario);
								valor=new BigDecimal(porcentajeDineroPropietario);
							}
						}
					} else {
						valor=new BigDecimal(valorTipoProducto);
					}
				} else if (acumularTipoProductoTipoTarjeta.equals("T")) {
					String valorTipoTarjeta=(String)parametrosMapPropietario.get(tarjetatipoId);
					if (valorTipoTarjeta == null) {
						if (acumularPuntosDinero.equals("D")) {
							TarjetaTipoIf tarjetaTipo = SessionServiceLocator.getTarjetaTipoSessionService().getTarjetaTipo(Long.parseLong(tarjetatipoId));
							String porcentajeDineroPropietario = tarjetaTipo.getPorcentajeDineroPropietario().toString();
							parametrosMapPropietario.put(tarjetatipoId, porcentajeDineroPropietario);
							valor=new BigDecimal(porcentajeDineroPropietario);
						} else if (acumularPuntosDinero.equals("P")) {
							// ESTO HAY QUE PENSARLO BIEN PARA DEFINIR COMO FUNCIONARIA
						}
					} else {
						valor = new BigDecimal(valorTipoTarjeta);
					}
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return valor;
	}


	private String obtenerTipoTarjeta(String nombreDuenoTarjeta,String identificacionFactura) {
		boolean find = false;
		String tipoTarjeta="";		
		try {	
			BigDecimal acum=BigDecimal.ZERO;
			BigDecimal util=BigDecimal.ZERO;
			BigDecimal comp=BigDecimal.ZERO;
			Vector<String> datosPropietario = new Vector<String>();

			datosPropietario=SessionServiceLocator.getFacturaSessionService().propietarioAfiliado(nombreDuenoTarjeta,identificacionFactura,Parametros.getIdEmpresa());	

			System.out.println("datosPropietario sewettt-->"+datosPropietario.size());

			if(datosPropietario.size()>0)
			{
				if(datosPropietario.get(0).equals("N"))
				{	
					tipoTarjeta="";
					//SpiritAlert.createAlert("El cliente no posee Tarjeta de Afiliación. Muchas gracias. ",SpiritAlert.INFORMATION);
				}
				else{ 		 
					if(datosPropietario.get(3)!=null){
						tipoTarjeta=datosPropietario.get(3);						 						 
					}	
				}
			}
			else{
				tipoTarjeta="";
				// SpiritAlert.createAlert("El cliente no posee Tarjeta de Afiliación. Muchas gracias. ",SpiritAlert.INFORMATION);
			}



		} catch (GenericBusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return tipoTarjeta;
	}

	private void resumen_Dialog(String estado) {

		boolean todolleno = false;
		todolleno = validar_datos_llenos(estado);
		if (todolleno) {
			if (!isSale() && !getLblTipoDocumentoSeleccionado().getText().equals("anticipo")) {
				tipoDocumentoTransaccion = findTipoDocumentoByCodigo("FAC");
				setearPuntoImpresion("FAC");
				t_facturar();
			}
			Vector<String> totales = new Vector<String>();
			totales.add(0, getTxtSubtotalFinal().getText());
			totales.add(1, getTxtDescuentoFinal().getText());
			totales.add(2, getTxtImpuestosFinal().getText());
			totales.add(3, getTxtTotalFinal().getText());

			Vector<String> pagos = new Vector<String>();
			pagos.add(0, getTxtEfectivo().getText());// efectivo
			pagos.add(1, getTxtTarjetaCredito().getText());// t/c
			pagos.add(2, getTxtGiftcard().getText());// gc
			pagos.add(3, getTxtCheque().getText());// cheque
			pagos.add(4, getTxtDebitoBancario().getText());// t/d
			pagos.add(5, getTxtPuntos().getText());// puntos/dinero t.afiliación

			if (!estado.equals("C"))
				estado = "A";

			String tipo_uno = getLblTipoDocumentoSeleccionado().getText();

			//AFILIACION
			Double sumaPuntosDineroGanado=0D;
			String tipoRP="";
			String tipoTarjeta="";
			if(TarjetasCollection_detalles.size()>0) {			
				tipoRP=((Vector) TarjetasCollection_detalles.get(0)).get(3).toString();
				tipoTarjeta=((Vector) TarjetasCollection_detalles.get(0)).get(8).toString();
				if (ProductosidReunionColeccion.size() != 0) {
					if(tipoTarjeta!=null && !tipoTarjeta.equals("")) {
						for (int l = 0; l < ProductosidReunionColeccion.size(); l++) {
							PedidoDetalleIf temporal = (PedidoDetalleIf) ProductosidReunionColeccion.get(l);
							String tipo_producto = (String) getTblVentaDetalle().getModel().getValueAt(l, 8);
							TipoProductoIf tipoProducto = (TipoProductoIf) tiposProductoByIdMap.get(Long.parseLong(tipo_producto));
							if (!tipoProducto.getCodigo().equals("GC")) {
								//AFILIACION: sacando el valor de los puntos/dinero ganado dependiendo si es referido o propietario
								if (acumularPuntosDinero.getValor().equals("P")) {
									BigDecimal cantidad = new BigDecimal((String)tableModel.getValueAt(l,2));
									BigDecimal puntos = valorTipoProductoTipoTarjeta(tipoRP,acumularPuntosDinero.getValor(),acumulacionTipoProductoTipoTarjeta.getValor(),tipo_producto,tipoTarjeta);							
									puntos = cantidad.multiply(puntos);
									sumaPuntosDineroGanado += puntos.doubleValue();
								} else if (acumularPuntosDinero.getValor().equals("D")) {
									BigDecimal valor = new BigDecimal((String)tableModel.getValueAt(l,7));								
									BigDecimal dinero = valorTipoProductoTipoTarjeta(tipoRP,acumularPuntosDinero.getValor(),acumulacionTipoProductoTipoTarjeta.getValor(),tipo_producto,tipoTarjeta);							
									dinero = valor.multiply(dinero).divide(new BigDecimal(100D));
									sumaPuntosDineroGanado += dinero.doubleValue();
								}
							}
						}
					}
				}
				System.out.println("[EN IF] TARJETAS COLLECTION DETALLES SIZE >>>>>>>>>> " + TarjetasCollection_detalles.size() + " >>>>>> PUNTOS/DINERO >>>>> " + getTxtPuntos().getText());
				((Vector) TarjetasCollection_detalles.get(0)).set(5,Double.parseDouble(getTxtPuntos().getText()));
				((Vector) TarjetasCollection_detalles.get(0)).set(6,sumaPuntosDineroGanado);
			} else {
				System.out.println("[EN ELSE] TARJETAS COLLECTION DETALLES SIZE >>>>>>>>>> " + TarjetasCollection_detalles.size() + " >>>>>> PUNTOS/DINERO >>>>> " + getTxtPuntos().getText());
				//si es que no ha escogio el tipo de pago tarjeta de afiliacion o referido
				//igual busca al cliente si tiene tarjeta de afiliacion para acreditarle puntos por su compra				
				tipoTarjeta= obtenerTipoTarjeta("", getTxtCedula().getText());
				if(tipoTarjeta!=null && !tipoTarjeta.equals("")){
					if (ProductosidReunionColeccion.size() != 0) {
						for (int l = 0; l < ProductosidReunionColeccion.size(); l++) {
							PedidoDetalleIf temporal = (PedidoDetalleIf) ProductosidReunionColeccion.get(l);
							String tipo_producto = (String) getTblVentaDetalle().getModel().getValueAt(l, 8);	
							TipoProductoIf tipoProducto = (TipoProductoIf) tiposProductoByIdMap.get(Long.parseLong(tipo_producto));
							if (!tipoProducto.getCodigo().equals("GC")) {
								//AFILIACION: sacando el valor de los puntos ganados dependiendo si es referido o propietario
								if (acumularPuntosDinero.getValor().equals("P")) {
									BigDecimal cantidad = new BigDecimal((String)tableModel.getValueAt(l,2));								
									BigDecimal puntos=valorTipoProductoTipoTarjeta("N",acumularPuntosDinero.getValor(),acumulacionTipoProductoTipoTarjeta.getValor(),tipo_producto,tipoTarjeta);							
									puntos=cantidad.multiply(puntos);
									sumaPuntosDineroGanado+=puntos.doubleValue();						
								} else if (acumularPuntosDinero.getValor().equals("D")) {
									BigDecimal valor = new BigDecimal((String)tableModel.getValueAt(l,7));								
									BigDecimal dinero = valorTipoProductoTipoTarjeta("N",acumularPuntosDinero.getValor(),acumulacionTipoProductoTipoTarjeta.getValor(),tipo_producto,tipoTarjeta);							
									dinero = valor.multiply(dinero).divide(new BigDecimal(100D));
									sumaPuntosDineroGanado += dinero.doubleValue();
								}
							}
						}
					}	
					TarjetasCollection = new Vector<String>();
					TarjetasCollection.add(0,"Referido/Propietario");
					TarjetasCollection.add(1,getTxtCedula().getText());
					TarjetasCollection.add(2,getTxtCedula().getText());//identificacion del dueño de la tarjeta
					TarjetasCollection.add(3,"N");
					TarjetasCollection.add(4,"");// 
					TarjetasCollection.add(5,getTxtPuntos().getText());							
					TarjetasCollection.add(6,sumaPuntosDineroGanado.toString());//actualizar puntos ganados
					TarjetasCollection.add(7,"");//actualiza a facturaId
					TarjetasCollection.add(8,tipoTarjeta);//tarjetaTIPO: ta-gold , ta-platinium
					TarjetasCollection_detalles.add(0,TarjetasCollection);
					tipoRP="N";
				}				
			}

			EmpleadoIf empleado_vendedor = ((EmpleadoIf) this.getCmbVendedor().getSelectedItem());
			jdResumenVentas = new ResumenVentasModel(Parametros.getMainFrame(),
					ProductosidReunionColeccion, getTblVentaDetalle(), totales,
					PagosCollection_detalles, estado, tipo_uno,bloqueado,tipoRP,sumaPuntosDineroGanado);

			jdResumenVentas.addWindowListener(wl);
			int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
			int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
			jdResumenVentas.setLocation(x, y);
			jdResumenVentas.pack();
			jdResumenVentas.setModal(true);
			jdResumenVentas.setVisible(true);
			if (jdResumenVentas.isEjecutar())// que si
				// procese(pedido,factura,asiento,inventario)
				// , o que Cancele Venta y
				// guarde solo pedido
			{
				if (estado.equals("C")) {
					// GENERA SOLO PEEDIDO PEDIDO DETALLE CON ESTADO--->
					// COTIZACION
					// EN PAGOS POS GRABA---> ESTADO: A(ANULADO) , DOCUMENTO=
					// GUARDA ID PEDIDO
					// TIPO: POR CADA TIPO DE PAGO 1:EFECTIVO 3:T/C 4:CHEQUE
					// 5:DEBITO 6:GIFT CARD
					String si = "Sí";
					String no = "No";
					Object[] options = { si, no };
					String codigo_t = "FAC";
					if (getLblTipoDocumentoSeleccionado().getText().equals("notaventa"))		codigo_t = "VTA";
					if (getLblTipoDocumentoSeleccionado().getText().equals("facturar"))			codigo_t = "FAC";
					TipoDocumentoIf tipoDocumentoIf;
					tipoDocumentoIf = findTipoDocumentoByCodigo(codigo_t);
					tipoDocumentoTransaccion = findTipoDocumentoByCodigo(codigo_t);
					setearPuntoImpresion(codigo_t);

					int opcion = JOptionPane.showOptionDialog(null, "¿Desea guardar la cotización?", "Confirmación",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if (opcion == JOptionPane.YES_OPTION) {
						savePedido(ESTADO_COTIZACION, true);
						report_cotizacion();
						this.clean();
					} else {
						savePedido(ESTADO_CANCELADO, true);
						SpiritAlert.createAlert("Pedido cancelado y guardado con éxito en Historial de Ventas", SpiritAlert.INFORMATION);
					}
				} else {
					// RECORRO LA COLECCION PARA DIVIDIR CADA UNA POR EL TIPO DE
					// "DOCUMENTO": factura o n/v, comprobante de caja gift,
					// compr. caja personalizacion
					// esa division la hago por el tipo de producto(tabla:
					// tipo_producto)
					// tipo1= general, //gift_card, perso web
					Long usuarioId;
					Long documento = null;
					usuarioId = ((UsuarioIf) Parametros.getUsuarioIf()).getId();
					String codigoDocumento = "FACT";
					if (getLblTipoDocumentoSeleccionado().getText().equals("notaventa"))
						codigoDocumento = "NVEN";
					documento = findDocumentoByCodigo(codigoDocumento).getId();
					ProductosidReunionColeccion_FAC.clear();
					ProductosidReunionColeccion_GIFT.clear();
					ProductosidReunionColeccion_NV.clear();
					ProductosidReunionColeccion_PERS_WEB.clear();


					if (ProductosidReunionColeccion.size() != 0) {
						for (int l = 0; l < ProductosidReunionColeccion.size(); l++) {
							PedidoDetalleIf temporal = (PedidoDetalleIf) ProductosidReunionColeccion.get(l);
							String tipo_producto = (String) getTblVentaDetalle().getModel().getValueAt(l, 8);


							temporal.setDocumentoId(documento);
							PedidoDetalleIf subData = temporal;
							if (!tipo_producto.equals(findTipoProductoByCodigo("GC").getId().toString())&& !tipo_producto.equals(findTipoProductoByCodigo("PW").getId().toString())) {
								if (tipo_uno.equals("facturar"))
								{									
									BigDecimal valorFinal = BigDecimal.ZERO;
									valorFinal=new BigDecimal((Utilitarios.removeDecimalFormat((String)tableModel.getValueAt(l,7))));		
									BigDecimal descue = BigDecimal.ZERO;
									descue = new BigDecimal((Utilitarios.removeDecimalFormat((String)tableModel.getValueAt(l,5))));									
									BigDecimal valoriva = BigDecimal.ZERO;
									valoriva=new BigDecimal((Utilitarios.removeDecimalFormat((String)tableModel.getValueAt(l,6))));

									/*
									BigDecimal precioDosDecimales = BigDecimal.ZERO;
									precioDosDecimales=new BigDecimal(new Double(Utilitarios.redondeoValor(new Double((Utilitarios.removeDecimalFormat((String)tableModel.getValueAt(l,4)))).doubleValue())).toString());
									subData.setPrecio(precioDosDecimales);
									subData.setPrecioreal(precioDosDecimales);*/


									if(valorFinal.doubleValue()== 0D)
									{
										Long documentoRegaloEmpaque=0L;									
										documentoRegaloEmpaque = findDocumentoByCodigo("FACB").getId();
										subData.setDocumentoId(documentoRegaloEmpaque);
										//subData.setDocumentoId(new Long("1101"));
									}


									subData.setValor(valorFinal);
									subData.setDescuento(descue);
									subData.setIva(valoriva);									
									ProductosidReunionColeccion_FAC.add(subData);									
								}

								if (tipo_uno.equals("notaventa"))
								{									
									BigDecimal valorFinal = BigDecimal.ZERO;
									valorFinal=new BigDecimal((Utilitarios.removeDecimalFormat((String)tableModel.getValueAt(l,7))));									
									BigDecimal descue = BigDecimal.ZERO;
									descue = new BigDecimal((Utilitarios.removeDecimalFormat((String)tableModel.getValueAt(l,5))));									
									BigDecimal valoriva = BigDecimal.ZERO;
									valoriva=new BigDecimal((Utilitarios.removeDecimalFormat((String)tableModel.getValueAt(l,6))));									
									subData.setValor(valorFinal);
									subData.setDescuento(descue);
									subData.setIva(valoriva);

									if(valorFinal.doubleValue()== 0D)
									{
										Long documentoRegaloEmpaque=0L;									
										documentoRegaloEmpaque = findDocumentoByCodigo("NVEB").getId();
										subData.setDocumentoId(documentoRegaloEmpaque);
										//subData.setDocumentoId(new Long("1103"));
									}


									/*
									BigDecimal precioDosDecimales = BigDecimal.ZERO;
									precioDosDecimales=new BigDecimal(new Double(Utilitarios.redondeoValor(new Double((Utilitarios.removeDecimalFormat((String)tableModel.getValueAt(l,4)))).doubleValue())).toString());
									subData.setPrecio(precioDosDecimales);
									subData.setPrecioreal(precioDosDecimales);*/

									ProductosidReunionColeccion_NV.add(subData);									
								}
							}
							if (tipo_producto.equals(findTipoProductoByCodigo("GC").getId().toString()))
								ProductosidReunionColeccion_GIFT.add(subData);
							if (tipo_producto.equals(findTipoProductoByCodigo("PW").getId().toString()))
								ProductosidReunionColeccion_PERS_WEB.add(subData);
						}


						if (ProductosidReunionColeccion_FAC.size() > 0
								|| ProductosidReunionColeccion_GIFT.size() > 0
								|| ProductosidReunionColeccion_NV.size() > 0) {
							try {
								VentasTrackIf ventasTrack = registrarVentasTrack();
								Map<String, Object> parametros = new HashMap<String, Object>();
								parametros.put("EMPLEADO", empleado_vendedor);
								parametros.put("CLIENTE", clienteIf);
								parametros.put("CLIENTE_OFICINA", clienteOficinaIf);
								parametros.put("PUNTO_IMPRESION", puntoImpresionIf);
								parametros.put("OFICINA_ID", Parametros.getIdOficina());
								parametros.put("EMPRESA_ID", Parametros.getIdEmpresa());
								parametros.put("USUARIO", (UsuarioIf) Parametros.getUsuarioIf());
								parametros.put("PORCENTAJE_IVA", Parametros.getIVA());
								parametros.put("BODEGA", bodegaPOS);
								parametros.put("APD", acumularPuntosDinero.getValor());
								parametros.put("ATPTT", acumulacionTipoProductoTipoTarjeta.getValor());

								String detallePagos="";

								if (PagosCollection_detalles.size() != 0) {
									for (int l = 0; l < PagosCollection_detalles.size(); l++) {
										String tipoPago = ((Vector) PagosCollection_detalles.get(l)).get(0).toString();
										String valorPago = "$ "+((Vector) PagosCollection_detalles.get(l)).get(1).toString();										
										String codigoPago = ((Vector) PagosCollection_detalles.get(l)).get(4).toString();										
										if(codigoPago.equals(new String("CH")) || codigoPago.equals(new String("TA")))
										{
											String nombreBancoTarjeta = ((Vector) PagosCollection_detalles.get(l)).get(2).toString();
											valorPago=nombreBancoTarjeta+" "+valorPago;
										}
										///


										System.out.println("--"+tipoPago+"****"+valorPago);
										detallePagos=" "+tipoPago+":"+valorPago+" ,"+detallePagos;

									}
								} 

								if(detallePagos.length()>1)
									detallePagos=detallePagos.substring(0,detallePagos.length()-1).concat(".");

								Map resultMap = SessionServiceLocator.getFacturaSessionService().generarFacturaPOS(
										ProductosidReunionColeccion_GIFT,
										ProductosidReunionColeccion_TA,
										ProductosidReunionColeccion_FAC,
										ProductosidReunionColeccion_NV,
										ProductosidReunionColeccion_PERS_WEB,
										ventasTrack,
										PagosCollection_detalles,
										getTxtDonacion().getText(),
										idfundacion,
										pedido,
										ProductosidReunionColeccion_eliminados,
										ProductosidReunionColeccion_proceso,
										TarjetasCollection_detalles,
										parametros, false);

								Long vacio = 0L;
								Long idFactura = (Long) resultMap.get("FACTURA_ID");
								Long idReciboCaja = (Long) resultMap.get("RECIBO_CAJA_ID");
								if ((idFactura.equals(vacio) && (ProductosidReunionColeccion_FAC.size() > 0 || ProductosidReunionColeccion_NV.size() > 0)) || (idReciboCaja.equals(vacio) && ProductosidReunionColeccion_GIFT.size() > 0)) {
									SpiritAlert.createAlert("Se ha producido un error al guardar la transacción", SpiritAlert.ERROR);
								} else {
									SpiritAlert.createAlert("Transacción guardada con éxito", SpiritAlert.INFORMATION);
									reportVenta(idFactura,detallePagos);
									final Long facturaId=idFactura;
									final Long reciboCajaId=idReciboCaja;
									new com.spirit.client.model.SwingWorker()
									{
										@Override
										public Object construct() {
											FacturaIf factura;
											CarteraIf reciboCaja;
											try {
												if (facturaId.compareTo(0L) != 0) {
													factura = SessionServiceLocator.getFacturaSessionService().getFactura(facturaId);
													IngresarPreimpresoModel jdIngresarPreimpreso = new IngresarPreimpresoModel(Parametros.getMainFrame(), factura);
												}
												if (reciboCajaId.compareTo(0L) != 0) {
													reciboCaja = SessionServiceLocator.getCarteraSessionService().getCartera(reciboCajaId);
													IngresarPreimpresoReciboCajaModel jdIngresarPreimpresoReciboCaja = new IngresarPreimpresoReciboCajaModel(Parametros.getMainFrame(), reciboCaja);
												}
											} catch (GenericBusinessException e) {
												e.printStackTrace();
												SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
											}

											return null;
										}
									};

								}

							} catch (GenericBusinessException e) {
								e.printStackTrace();
								SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							} 
						}
					} else if (getLblTipoDocumentoSeleccionado().getText().equals("anticipo")) {
						//Generar anticipo
						final Map<String, Object> parametros = new HashMap<String, Object>();
						parametros.put("EMPLEADO", empleado_vendedor);
						parametros.put("CLIENTE", clienteIf);
						parametros.put("CLIENTE_OFICINA", clienteOficinaIf);
						parametros.put("PUNTO_IMPRESION", puntoImpresionIf);
						parametros.put("OFICINA_ID", Parametros.getIdOficina());
						parametros.put("EMPRESA_ID", Parametros.getIdEmpresa());
						parametros.put("USUARIO", (UsuarioIf) Parametros.getUsuarioIf());
						parametros.put("MONEDA", monedaUSD);
						parametros.put("REFERENCIA", referenciaReciboCaja);
						final Vector<Vector> detallesPagos = (Vector<Vector>) DeepCopy.copy(PagosCollection_detalles);
						System.out.println("Antes de : "+PagosCollection_detalles.size());
						Long id_registrada = SessionServiceLocator.getCarteraSessionService().generarReciboCajaPOS(PagosCollection_detalles, parametros, false);
						System.out.println("Despues de : "+PagosCollection_detalles.size());
						if (id_registrada.equals(0L)) {
							SpiritAlert.createAlert("Ocurrió un error al guardar la transacción", SpiritAlert.ERROR);
						} else {
							SpiritAlert.createAlert("Transacción guardada con éxito", SpiritAlert.INFORMATION);
							//reportVenta(id_registrada,detallePagos);
							final Long id=id_registrada;
							new com.spirit.client.model.SwingWorker()
							{
								@Override
								public Object construct() {
									CarteraIf cartera;
									try {
										cartera = SessionServiceLocator.getCarteraSessionService().getCartera(id);
										IngresarPreimpresoReciboCajaModel jdIngresarPreimpreso = new IngresarPreimpresoReciboCajaModel(Parametros.getMainFrame(), cartera);
										String preimpreso=jdIngresarPreimpreso.getPreimpreso();
										jdIngresarPreimpreso.dispose();
										parametros.put("REFERENCIA_ID", id);
										SessionServiceLocator.getCarteraSessionService().enviarReciboCajaPos(detallesPagos, parametros, false, cartera, preimpreso);
									} catch (GenericBusinessException e) {
										e.printStackTrace();
										SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
									}

									return null;
								}
							};
						}
					}
				}
				clean();
			} else {
				clean_formapagos();
				getTxtPorcentajeDescuento().setText("0");
				aplicarDescuento();						
			}
		}
	}

	/*public TarjetaTransaccionIf registrarTarjetaTx(Long tarjetaId,Double sumaPuntosGanados,BigDecimal puntosUtilizados,Long idRegistrada){
		TarjetaTransaccionData tranx = new TarjetaTransaccionData();	


		tranx.setTarjetaId(tarjetaId);
		String referido=((Vector) TarjetasCollection_detalles.get(0)).get(3).toString();
		tranx.setReferido(referido);	
		//AQUI GUARDA EL DUEÑO DE LA TRANSACCION, no importa si es referido o no...
		//si referido es N--> referidoPor esta el dueño de la tarjeta
		tranx.setReferidoPor(clienteOficinaIf.getId());
		tranx.setPuntosGanados(new BigDecimal(sumaPuntosGanados.toString()));
		tranx.setPuntosUtilizados(puntosUtilizados);
		tranx.setFacturaId(idRegistrada);

		return tranx;
	}*/


	public VentasTrackIf registrarVentasTrack() {
		VentasTrackData ventasTrack = new VentasTrackData();
		ventasTrack.setCajasesionId(cajaAbiertaID);
		ventasTrack.setValorTotal(new BigDecimal(Utilitarios.removeDecimalFormat(getTxtTotalFinal().getText())));
		//ventasTrack.setFechaVenta(new java.sql.Timestamp(new Timestamp().getDateTime()));
		ventasTrack.setFechaVenta(new java.sql.Timestamp(new java.util.Date().getTime()));
		return ventasTrack;
	}

	public TipoProductoIf findTipoProductoByCodigo(String codigoTipoProducto) {
		// TIPO PRODUCTO: GIFT CARD" = "GC";/ "PERSONALIZACION WEB" = "PW";
		TipoProductoIf tipoProducto = (TipoProductoIf) tiposProductoByCodigoMap.get(codigoTipoProducto);
		return tipoProducto;
	}

	public void savePedido(String estadoPedido, boolean actualizar) {
		// tipoPedido -> A: CANCELADO, T: COTIZACION
		if (validateFields()) {
			try {
				PedidoIf pedido = registrarPedido(estadoPedido, actualizar);
				ProductosidReunionColeccion_proceso = ProductosidReunionColeccion;
				if (estadoPedido.equals(ESTADO_COTIZACION)
						|| estadoPedido.equals(ESTADO_CANCELADO)) {
					if (ProductosidReunionColeccion_proceso.size() != 0) {
						for (int i = 0; i < ProductosidReunionColeccion_proceso.size(); i++) {
							PedidoDetalleIf temporal = (PedidoDetalleIf) ProductosidReunionColeccion_proceso.get(i);
							Long id_prod = temporal.getProductoId();
							if (id_prod.equals(findTipoProductoByCodigo("GC").getId().toString()))
								ProductosidReunionColeccion_proceso.remove(i);
						}
					}

				}
				Vector<PedidoDetalleIf> pedidoDetalleVector = ProductosidReunionColeccion_proceso;
				idPedidoGuardado = SessionServiceLocator.getFacturaSessionService().savePedido(pedido, pedidoDetalleVector,Parametros.getIdEmpresa());
				if (idPedidoGuardado != null && idPedidoGuardado != 0L)
					SpiritAlert.createAlert("Pedido grabado en Historial de Ventas", SpiritAlert.INFORMATION);
			} catch (Exception e) {
				SpiritAlert.createAlert("Ocurrió un error al guardar el Pedido", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}

	private PedidoIf registrarPedido(String tipoPedido, boolean actualizar) {
		PedidoData data = new PedidoData();
		try {
			String codigo = (!actualizar) ? getCodigoPedido(new java.sql.Date(fechaPedido.getYear(), fechaPedido.getMonth(), fechaPedido.getDate())) : "";
			data.setCodigo(codigo);
			java.util.Date fechaPedido = new java.util.Date();
			data.setFechaPedido(new java.sql.Timestamp(fechaPedido.getTime()));
			java.util.Date fechaCreacion = new java.util.Date();
			data.setFechaCreacion(new java.sql.Timestamp(fechaCreacion.getTime()));
			data.setOficinaId(Parametros.getIdOficina());
			data.setTipodocumentoId(tipoDocumentoTransaccion.getId());
			data.setClienteoficinaId(clienteOficinaIf.getId());
			data.setTipoidentificacionId(clienteIf.getTipoidentificacionId());
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			parameterMap.put("codigo", "OTRA");
			Iterator it = SessionServiceLocator.getFormaPagoSessionService().findFormaPagoByQuery(parameterMap).iterator();
			if (it.hasNext()) {
				FormaPagoIf formaPago = (FormaPagoIf) it.next();
				data.setFormapagoId(formaPago.getId());
			}
			data.setMonedaId(monedaUSD.getId());
			data.setPuntoimpresionId(puntoImpresionIf.getId());
			parameterMap.put("codigo", "CLI");
			it = SessionServiceLocator.getOrigenDocumentoSessionService().findOrigenDocumentoByQuery(parameterMap).iterator();
			if (it.hasNext()) {
				OrigenDocumentoIf origenDocumento = (OrigenDocumentoIf) it.next();
				data.setOrigendocumentoId(origenDocumento.getId());
			}
			data.setVendedorId(empleado.getId());
			data.setBodegaId(bodegaPOS.getId());
			ListaPrecioIf listaPrecio = (ListaPrecioIf) findListaPrecioByCodigo("OFI");
			data.setListaprecioId(listaPrecio.getId());
			data.setUsuarioId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
			data.setDiasvalidez(1);
			data.setTiporeferencia("N");
			data.setReferencia("");
			data.setEstado("P");
			data.setDireccion(clienteOficinaIf.getDireccion());
			String observacion = "P";
			data.setObservacion("");
			if (tipoPedido.equals(ESTADO_CANCELADO) && actualizar) {
				data.setObservacion("CANCELADO");
				data.setEstado(ESTADO_CANCELADO);
			}
			if (tipoPedido.equals(ESTADO_COTIZACION) && actualizar) {
				data.setObservacion("COTIZACION");
				data.setEstado(ESTADO_COTIZACION);
			}
			if (tipoPedido.equals(ESTADO_COTIZACION) && !actualizar) {
				data.setObservacion("COTIZACION");
				data.setEstado("P");
			}
			if (tipoPedido.equals(ESTADO_CANCELADO) && !actualizar) {
				data.setObservacion("CANCELADO");
				data.setEstado("P");
			}

			data.setIdentificacion(clienteIf.getIdentificacion());
			data.setTelefono(clienteOficinaIf.getTelefono());
			data.setContacto("");
			data.setPorcentajeComisionAgencia(BigDecimal.ZERO);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return data;
	}

	private String getCodigoPedido(java.sql.Date fechaPedido) {
		String codigo = "";
		String anioPedido = Utilitarios.getYearFromDate(fechaPedido);
		codigo += anioPedido + "-";
		return codigo;
	}

	// devuelve el valor de la cantidad de la ventana de Dialogo

	private Vector<String> Cantidad_Dialog(boolean isGiftcard) {
		jdCantidadProducto = new CantidadModel(Parametros.getMainFrame(),
				getTxtCodigoBarras().getText(), getTxtIdProducto().getText(),
				bodegaPOS.getId(), isGiftcard);

		jdCantidadProducto.addWindowListener(wl);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
		jdCantidadProducto.setLocation(x, y);
		jdCantidadProducto.pack();
		jdCantidadProducto.setModal(true);
		jdCantidadProducto.setVisible(true);
		String cant = "";
		cant = jdCantidadProducto.getCantiSeleccionada();
		if (cant == null || cant.equals(""))	cant = "0";

		String productoRegalo="N";
		productoRegalo = jdCantidadProducto.getProductoRegalo();		 
		cantidadProductoRegalo = new Vector<String>();
		cantidadProductoRegalo.add(0,cant);
		cantidadProductoRegalo.add(1,productoRegalo);




		return cantidadProductoRegalo;
	}

	private double redondeoValor4Decimales(double valor){
		double valorR = 0.0;
		Double decimales = 4D;
		valorR = BigDecimal.valueOf(valor).setScale(decimales.intValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
		return valorR;
	}


	// /////////////////////////////// BOTONES ///////////////////////////
	//lo llama cuando escribe codigo en CodigoBarras
	public void anadir_producto_UPC() {
		String codigoBarras=getTxtCodigoBarras().getText();
		if(codigoBarras==null) codigoBarras="";
		Map aMap = new HashMap();
		aMap.clear();
		aMap.put("codigoBarras", codigoBarras);
		aMap.put("estado", "A");
		aMap.put("permiteventa", "S");
		Iterator cajavalorIt;

		if (!codigoBarras.equals("")) {			
			try {
				cajavalorIt = SessionServiceLocator.getProductoSessionService().findProductoByQuery(aMap).iterator();
				if (cajavalorIt.hasNext()) {
					ProductoIf producto = (ProductoIf) cajavalorIt.next();
					getTxtIdProducto().setText(producto.getId().toString());
					getTxtIdGiftcard().setText(null);
					getTxtDescripcion().setText("");

					GenericoIf generico = (GenericoIf) genericosMap.get(producto.getGenericoId());
					getTxtTipoProducto().setText(generico.getTipoproductoId().toString());
					getTxtIVA().setText(generico.getCobraIva());
					// MODELO
					Long modeloId = producto.getModeloId();
					ModeloIf modelo = (modeloId!=null)?(ModeloIf) modelosMap.get(modeloId):null;
					if (modelo != null && !modelo.getNombre().equals("NINGUNO"))
						getTxtDescripcion().setText((modelo!=null)?modelo.getNombre():"");
					if (getTxtDescripcion().getText().equals(""))
						getTxtDescripcion().setText(generico.getNombreGenerico());
					// COLOR
					Long colorId = producto.getColorId();
					ColorIf color = (colorId!=null)?(ColorIf) coloresMap.get(colorId):null;
					if (color != null && !color.getNombre().equals("NINGUNO"))
						getTxtDescripcion().setText(getTxtDescripcion().getText().toString() + " - " + ((color!=null)?color.getNombre():""));
					// TALLA
					Long presentacionId = producto.getPresentacionId();
					PresentacionIf presentacion = (presentacionId!=null)?(PresentacionIf) presentacionesMap.get(producto.getPresentacionId()):null;
					if (presentacion != null && !presentacion.getNombre().equals("NINGUNA"))
						getTxtDescripcion().setText(getTxtDescripcion().getText().toString() + " ("+ ((presentacion!=null)?presentacion.getNombre():"") +")");

					getTxtCodigoProducto().setText(producto.getCodigo());
					getTxtCantidad().setText("1");
					getTxtDescuento().setText("0");
					////////////////PRECIO
					Map aMap4 = new HashMap();
					aMap4.clear();
					aMap4.put("listaprecioId", findListaPrecioByCodigo("OFI").getId());
					aMap4.put("productoId", producto.getId());
					aMap4.put("estado", "A");
					Iterator cajavalorIt4 = SessionServiceLocator.getPrecioSessionService().findPrecioByQuery(aMap4).iterator();
					if (cajavalorIt4.hasNext()) {
						PrecioIf precioif = (PrecioIf) cajavalorIt4.next();
						getTxtPVP().setText(precioif.getPrecio().toString());
						//getTxtPVP().setText(formatoDecimal.format(precioif.getPrecio()).toString());
					} else {
						getTxtPVP().setText("0.00");
					}



					// LOTE
					LoteIf lote = (LoteIf) lotesMap.get(producto.getId());
					if (lote!=null)
						getTxtLoteid().setText(lote.getId().toString());
					else
						getTxtLoteid().setText("");
					//////////////////////
					//le envio "1" para q tome la cantidad por el cuadro de dialogo.
					anadirProducto("1");
				} else {
					aMap.clear();
					aMap.put("codigoBarras", codigoBarras);
					aMap.put("estado", "I");
					//cajavalorIt = SessionServiceLocator.getGiftcardSessionService().findGiftcardByQuery(aMap).iterator();
					cajavalorIt = SessionServiceLocator.getGiftcardSessionService().findGiftcardByQueryWebService(Parametros.getIdEmpresa(),codigoBarras,"I").iterator();
					if (cajavalorIt.hasNext()) {
						GiftcardIf giftcard = (GiftcardIf) cajavalorIt.next();
						ProductoIf producto = (ProductoIf) productosMap.get(giftcard.getProductoId());
						getTxtIdProducto().setText(producto.getId().toString());
						getTxtIdGiftcard().setText(giftcard.getId().toString());
						GenericoIf generico = (GenericoIf) genericosMap.get(producto.getGenericoId());
						getTxtDescripcion().setText(generico.getNombre());
						getTxtTipoProducto().setText(generico.getTipoproductoId().toString());
						getTxtIVA().setText(generico.getCobraIva());
						getTxtCodigoProducto().setText(producto.getCodigo());
						getTxtCantidad().setText("1");
						getTxtDescuento().setText("0");
						////////////////PRECIO
						Map aMap4 = new HashMap();
						aMap4.clear();
						aMap4.put("listaprecioId", findListaPrecioByCodigo("OFI").getId());
						aMap4.put("productoId", producto.getId());
						aMap4.put("estado", "A");
						Iterator cajavalorIt4 = SessionServiceLocator.getPrecioSessionService().findPrecioByQuery(aMap4).iterator();
						if (cajavalorIt4.hasNext()) {
							PrecioIf precioif = (PrecioIf) cajavalorIt4.next();
							getTxtPVP().setText(precioif.getPrecio().toString());
							//getTxtPVP().setText(formatoDecimal.format(precioif.getPrecio()).toString());
						} else {
							getTxtPVP().setText("0.00");
						}
						// LOTE
						LoteIf lote = (LoteIf) lotesMap.get(producto.getId());
						if (lote!=null)
							getTxtLoteid().setText(lote.getId().toString());
						else
							getTxtLoteid().setText("");
						//////////////////////
						//le envio "1" para q tome la cantidad por el cuadro de dialogo.
						anadirProducto("1");
					} else {
						boolean haveLoyaltyCard = false;
						if (clienteOficinaIf != null) {
							TarjetaIf tarjeta = SessionServiceLocator.getTarjetaSessionService().findTarjetaWebService(clienteIf.getIdentificacion(), clienteOficinaIf.getCodigo(), Parametros.getIdEmpresa());
							if (tarjeta != null)
								haveLoyaltyCard = true;
						}
						if (!haveLoyaltyCard && (ProductosidReunionColeccion_TA == null || ProductosidReunionColeccion_TA.size() <= 0)) {
							cajavalorIt = SessionServiceLocator.getTarjetaSessionService().findTarjetaByEmpresaIdByCodigoBarrasAndEstadoWebService(Parametros.getIdEmpresa(),codigoBarras,"I").iterator();
							if (cajavalorIt.hasNext()) {
								TarjetaIf tarjeta = (TarjetaIf) cajavalorIt.next();
								if (tarjeta != null) {
									ProductosidReunionColeccion_TA.add(tarjeta);
									System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
									System.out.println(">>>>>>>>>>>>TARJETA.GETPRODUCTOID()>>>>>>>>>> " + tarjeta.getProductoId());
									System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
									ProductoIf producto = (ProductoIf) productosMap.get(tarjeta.getProductoId());
									getTxtIdProducto().setText(producto.getId().toString());
									getTxtIdGiftcard().setText(null);
									GenericoIf generico = (GenericoIf) genericosMap.get(producto.getGenericoId());
									getTxtDescripcion().setText(generico.getNombre());
									getTxtTipoProducto().setText(generico.getTipoproductoId().toString());
									getTxtIVA().setText(generico.getCobraIva());
									getTxtCodigoProducto().setText(producto.getCodigo());
									getTxtCantidad().setText("1");
									getTxtDescuento().setText("0");
									Map parameterMap = new HashMap();
									parameterMap.put("codigo", "UFLC");
									parameterMap.put("empresaId", Parametros.getIdEmpresa());
									Iterator it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
									double uflc = -1D;
									while (it.hasNext()) {
										ParametroEmpresaIf umbralFreeLoyaltyCard = (ParametroEmpresaIf) it.next();
										uflc = Double.parseDouble(umbralFreeLoyaltyCard.getValor());
									}
									if (totalSinTarjeta >= 0D && totalSinTarjeta < uflc) {
										////////////////PRECIO
										Map aMap4 = new HashMap();
										aMap4.clear();
										aMap4.put("listaprecioId", findListaPrecioByCodigo("OFI").getId());
										aMap4.put("productoId", producto.getId());
										aMap4.put("estado", "A");
										Iterator cajavalorIt4 = SessionServiceLocator.getPrecioSessionService().findPrecioByQuery(aMap4).iterator();
										if (cajavalorIt4.hasNext()) {
											PrecioIf precioif = (PrecioIf) cajavalorIt4.next();
											getTxtPVP().setText(precioif.getPrecio().toString());
											//getTxtPVP().setText(formatoDecimal.format(precioif.getPrecio()).toString());
										} else {
											getTxtPVP().setText("0.00");
										}
									} else {
										getTxtPVP().setText("0.00");
									}
									// LOTE
									LoteIf lote = (LoteIf) lotesMap.get(producto.getId());
									if (lote!=null)
										getTxtLoteid().setText(lote.getId().toString());
									else
										getTxtLoteid().setText("");
									//////////////////////
									//le envio "1" para q tome la cantidad por el cuadro de dialogo.
									anadirProducto("1");
								} else {
									getTxtCodigoBarras().setText("");
									SpiritAlert.createAlert("No se hallaron coincidencias de productos para código de barra ingresado", SpiritAlert.INFORMATION);
									getTxtCodigoBarras().grabFocus();
								}
							} else {
								getTxtCodigoBarras().setText("");
								SpiritAlert.createAlert("No se hallaron coincidencias de productos para código de barra ingresado", SpiritAlert.INFORMATION);
								getTxtCodigoBarras().grabFocus();
							}
						} else {
							getTxtCodigoBarras().setText("");
							SpiritAlert.createAlert("Cliente seleccionado tiene actualmente tarjeta de afiliación o se encuentra en la lista,\nno puede adquirir este producto otra vez.", SpiritAlert.INFORMATION);
							getTxtCodigoBarras().grabFocus();
						}
					}
				}
			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			SpiritAlert.createAlert("Debe ingresar el código de Barras del Producto", SpiritAlert.INFORMATION);
		}
	}

	//lo uso desde el codigoBarras, desde buscar producto
	private void anadirProducto(String UPC) {
		tableModel = (DefaultTableModel) getTblVentaDetalle().getModel();

		if (Utilitarios.removeDecimalFormat(getTxtDescuento().getText()).equals(""))		getTxtDescuento().setText("0");

		//Long usuarioId;
		Long documento = null;
		try {
			//usuarioId = ((UsuarioIf) Parametros.getUsuarioIf()).getId();
			documento = findDocumentoByCodigo("FACT").getId();
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al buscar documento por usuario !!", SpiritAlert.ERROR);
		}
		Vector<String> fila = new Vector<String>();
		boolean isExisteProducto = false;
		if (ProductosidReunionColeccion.size() != 0) {
			// Recorro la coleccion de producto
			for (int l = 0; l < ProductosidReunionColeccion.size(); l++) {
				PedidoDetalleIf temporal = (PedidoDetalleIf) ProductosidReunionColeccion.get(l);
				ProductoIf p = (ProductoIf) productosMap.get(Long.parseLong(getTxtIdProducto().getText()));
				GenericoIf g = (GenericoIf) genericosMap.get(p.getGenericoId());
				TipoProductoIf tp = (TipoProductoIf) tiposProductoByIdMap.get(g.getTipoproductoId());
				String productoTemp = (temporal.getGiftcardId() == null)?new Long(temporal.getProductoId()).toString():new Long(temporal.getGiftcardId()).toString();
				// Si el producto cliente ingresado ya esta en lista, entonces muestro un mensaje de error
				if (temporal.getGiftcardId() == null) {
					if (productoTemp.equals(getTxtIdProducto().getText().trim())) {
						isExisteProducto = true;
						getTxtCodigoBarras().setText("");
						SpiritAlert.createAlert("El registro ya se encuentra agregado !!",SpiritAlert.INFORMATION);
						getTxtCodigoBarras().setText("");
						return;
					}
				} else if (!tp.getCodigo().equals("TA")) {
					if (productoTemp.equals(getTxtIdGiftcard().getText().trim())) {
						isExisteProducto = true;
						getTxtCodigoBarras().setText("");
						SpiritAlert.createAlert("El registro ya se encuentra agregado !!",SpiritAlert.INFORMATION);
						getTxtCodigoBarras().setText("");
						return;
					}
				}
			}
		}

		// Si el producto no existe lo inserto en la lista
		if (isExisteProducto == false) {
			if (UPC.equals("1")) {// si añado el producto tomo la cantidad del JDCantidad

				Vector<String> cantidadProductoRegalo = new Vector<String>();
				if (!getTxtIdGiftcard().getText().trim().equals(""))
					cantidadProductoRegalo = Cantidad_Dialog(true);
				else
					cantidadProductoRegalo = Cantidad_Dialog(false);
				String cantidadVentana = cantidadProductoRegalo.get(0);

				String productoRegalo="N";
				if(cantidadProductoRegalo.get(1)!=null) productoRegalo=cantidadProductoRegalo.get(1);

				if(productoRegalo.equals("S")) getTxtPVP().setText("0.00");

				//String cantidadVentana = Cantidad_Dialog();
				//cantidadProductoRelago
				getTxtCantidad().setText(cantidadVentana);
				getTxtDescuento().setText("0");
			}
			if (UPC.equals("0")) {				
				String cantidad_ventana = "1";
				getTxtCantidad().setText(cantidad_ventana);
				getTxtCantidadDetalle().setText(cantidad_ventana);				
				getTxtDescuento().setText("0");
			}

			BigDecimal valCant = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtPVP().getText())).multiply(new BigDecimal(getTxtCantidad().getText()));

			if(getTxtPorcentajeDescuento().getText()!=null && !getTxtPorcentajeDescuento().getText().equals("0") && !getTxtPorcentajeDescuento().getText().toString().equals("")){
				BigDecimal tempo = new BigDecimal(getTxtPorcentajeDescuento().getText());
				tempo = valCant.multiply(tempo);					
				tempo = tempo.divide(new BigDecimal("100"));				
				//tempo =new BigDecimal(new Double(Utilitarios.redondeoValor(tempo.doubleValue())).toString());					
				tempo =new BigDecimal(new Double(tempo.doubleValue()).toString());
				if(tempo!=null)  tempo=tempo.setScale(2, BigDecimal.ROUND_HALF_UP);				
				getTxtDescuento().setText(tempo.toString());
			}

			if (!getTxtCantidad().getText().equals("0")) {
				BigDecimal cantidad = new BigDecimal(getTxtCantidad().getText());
				if(getTxtPVP().getText()=="") getTxtPVP().setText("0"); 
				String precio=getTxtPVP().getText();
				if(precio==null) precio="0";
				if(precio.equals("")) precio="0";
				BigDecimal prec = new BigDecimal(precio);
				BigDecimal val_cant = cantidad.multiply(prec);

				String si_no_IVA = getTxtIVA().getText();
				if (si_no_IVA == null)	si_no_IVA = "N";
				BigDecimal iva1 = new BigDecimal("0");


				if (si_no_IVA.equals("S")) {
					val_cant=val_cant.subtract(new BigDecimal(getTxtDescuento().getText()));
					iva1 = val_cant.multiply(BigDecimal.valueOf(IVA));
					//iva1 = new BigDecimal(Utilitarios.redondeoValor(iva1.doubleValue()));
					//getTxtIVA().setText(formatoDecimal.format(iva1).toString());
					getTxtIVA().setText(iva1.toString());
				} else {
					getTxtIVA().setText("0.00");
					iva1 = new BigDecimal("0");
				}

				// Agregar a la coleccion de ProductosidReunionColeccion para manejar los repetidos
				PedidoDetalleData subData = new PedidoDetalleData();
				subData.setDocumentoId(documento);
				subData.setProductoId(new Long(getTxtIdProducto().getText().trim()));
				subData.setGiftcardId((getTxtIdGiftcard().getText() != null && !getTxtIdGiftcard().getText().equals(""))?new Long(getTxtIdGiftcard().getText().trim()):null);
				subData.setCantidadpedida(BigDecimal.valueOf(Double.parseDouble(this.getTxtCantidad().getText().trim())));
				subData.setCantidad(BigDecimal.valueOf(Double.parseDouble(this.getTxtCantidad().getText().trim())));
				if (getTxtLoteid().getText() != null && !getTxtLoteid().getText().equals(""))			subData.setLoteId(new Long(getTxtLoteid().getText()));
				subData.setDescripcion(this.getTxtDescripcion().getText());
				subData.setPrecio(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPVP().getText()))));
				subData.setPrecioreal(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPVP().getText()))));
				BigDecimal subtotal = subData.getPrecio().multiply(subData.getCantidad());
				subData.setValor(subtotal);
				subData.setIva(iva1);
				subData.setIce(BigDecimal.valueOf(0.0));
				subData.setOtroimpuesto(BigDecimal.valueOf(0.0));
				subData.setDescuento(new BigDecimal(getTxtDescuento().getText()));
				subData.setDescuentoGlobal(BigDecimal.ZERO);
				//subData.setCompraId(null);

				subtotal=subtotal.subtract(new BigDecimal(getTxtDescuento().getText()));
				subtotal=subtotal.add(subData.getIva());				
				getTxtImporte().setText(formatoDecimal.format(subtotal));

				ProductosidReunionColeccion.add(subData);
				// añado valores a la tabla
				fila.add(getTxtCodigoProducto().getText());
				fila.add(getTxtDescripcion().getText());
				fila.add(getTxtCantidad().getText());
				fila.add(getTxtCantidad().getText());
				fila.add(getTxtPVP().getText());
				fila.add(getTxtDescuento().getText());
				fila.add(getTxtIVA().getText());
				fila.add(getTxtImporte().getText());
				fila.add(getTxtTipoProducto().getText());

				tableModel.addRow(fila);

				llenar_totalGeneral();

				recalcularDonacion("NORMAL");


			}
			clean_producto();
		} else if (getTxtIdProducto().getText().equals("")) {
			SpiritAlert.createAlert(
					"Ingrese el producto para agregar a la lista !!!",
					SpiritAlert.INFORMATION);
			getTxtCodigoBarras().grabFocus();
		} else if (getTxtImporte().getText().equals("")) {
			SpiritAlert.createAlert(
					"No puede estar en blanco el Valor Final del producto !!!",
					SpiritAlert.INFORMATION);
			getTxtCodigoBarras().grabFocus();
		} else if (getTxtCantidad().getText().equals("")) {
			SpiritAlert.createAlert("La cantidad debe ser mayor que 1 !!!",
					SpiritAlert.INFORMATION);
			getTxtCodigoBarras().grabFocus();
		}
		clean_producto();
	}

//	String tipo_producto= (String)tableModelOriginal.getModel().getValueAt(l,8);	


	public void recalcularDonacion(String tipo){
		BigDecimal donacion=new BigDecimal("0.00");
		if(tipo.equals("NORMAL"))
		{
			tableModel = (DefaultTableModel) getTblVentaDetalle().getModel();			
			if (ProductosidReunionColeccion.size() != 0) {
				for (int l = 0; l < ProductosidReunionColeccion.size(); l++) {
					String tipo_producto= (String)tableModel.getValueAt(l,8);	
					String cantidad= (String)tableModel.getValueAt(l,2);
					donacion=donacion.add(valorDonacion(new Long(tipo_producto),new BigDecimal(cantidad)));
				}
			}
		}
		else{

		}
		getTxtDonacion().setText(donacion.toString());
	}


	public BigDecimal valorDonacion(Long tipoProducto,BigDecimal cantidad) {
		BigDecimal valor = BigDecimal.ZERO;
		DonacionTipoproductoIf donacion = (DonacionTipoproductoIf) donacionesTipoProductoMap.get(tipoProducto);
		valor = (donacion!=null)?donacion.getValor():BigDecimal.ZERO;
		valor = valor.multiply(cantidad);
		return valor;		
	}
	// //////////

	/*
	 * public Component prepareRenderer(TableCellRenderer renderer, int row, int
	 * column){ Component returnComp = super.prepareRenderer(renderer, row,
	 * column); String tipo= getValueAt(row,3).toString();
	 * if(tipo.compareTo("0")==0) returnComp.setForeground(Color.BLUE); else
	 * returnComp.setForeground(Color.RED); return returnComp; }
	 */

	private class getCR_ejem_DEV implements TableCellRenderer {
		private final Color AZUL = new Color(0xB8, 0xCF, 0xE5); // #B8CFE5

		public Component getTableCellRendererComponent(JTable table2,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			JTextPane celda = new JTextPane();
			celda.setText((String) value);

			celda.setBackground(new Color(205, 230, 23));
			celda.setFont(new Font("Arial Narrow", Font.BOLD, 16));

			// Que la altura de la celda sea la altura del JTextArea
			int alturaTextArea = (int) celda.getPreferredSize().getHeight(); // <---

			table2.setRowHeight(row, alturaTextArea); // <---
			return celda;
		}
	}

	// poner una lista que recorra la tabla y actualice, ya sea la lista de productos por facturar o productos por devoluciones 
	private void llenar_totalGeneral() {

		DefaultTableModel model = (DefaultTableModel) getTblVentaDetalle().getModel();		
		BigDecimal totalValorFinal = BigDecimal.ZERO;
		BigDecimal totalValorFinalSinTarjetaAfiliacion = BigDecimal.ZERO;
		BigDecimal totalSubtotal = BigDecimal.ZERO;
		BigDecimal totalDesctotal = BigDecimal.ZERO;
		BigDecimal totalImptotal = BigDecimal.ZERO;
		totalSinTarjeta = 0D;

		for (int i = 0; i < ProductosidReunionColeccion.size(); i++) {
			String cantidad = (String) getTblVentaDetalle().getModel().getValueAt(i, 2);
			String precio = (String) getTblVentaDetalle().getModel().getValueAt(i, 4);
			String desc = (String) getTblVentaDetalle().getModel().getValueAt(i, 5);
			String impuestos = (String) getTblVentaDetalle().getModel().getValueAt(i, 6);
			String valorLinea = (String) getTblVentaDetalle().getModel().getValueAt(i, 7);
			String tipo_producto = (String) getTblVentaDetalle().getModel().getValueAt(i, 8);	
			TipoProductoIf tipoProducto = (TipoProductoIf) tiposProductoByIdMap.get(Long.parseLong(tipo_producto));

			if(valorLinea!=null && !valorLinea.equals(""))	valorLinea=Utilitarios.removeDecimalFormat(valorLinea);
			if(impuestos!=null && !impuestos.equals(""))	impuestos=Utilitarios.removeDecimalFormat(impuestos);
			if(desc!=null && !desc.equals(""))	desc=Utilitarios.removeDecimalFormat(desc);
			if(precio!=null && !precio.equals(""))	precio=Utilitarios.removeDecimalFormat(precio);

			//precio=new Double(Utilitarios.redondeoValor(new Double(precio).doubleValue())).toString();
			BigDecimal valCant=new BigDecimal(cantidad).multiply(new BigDecimal(precio));

			totalSubtotal=totalSubtotal.add(valCant);
			totalDesctotal=totalDesctotal.add(new BigDecimal(desc));
			totalImptotal=totalImptotal.add(new BigDecimal(impuestos));
			totalValorFinal=totalValorFinal.add(new BigDecimal(valorLinea));
			if (!tipoProducto.getCodigo().equals("TA"))
				totalSinTarjeta+=Double.parseDouble(valorLinea);
			BigDecimal valorFinal = BigDecimal.ZERO;			valorFinal=new BigDecimal(valorLinea);
			BigDecimal descue = BigDecimal.ZERO;				descue = new BigDecimal(desc);			
			BigDecimal valoriva = BigDecimal.ZERO;				valoriva=new BigDecimal(impuestos);			
			ProductosidReunionColeccion.get(i).setValor(valorFinal);
			ProductosidReunionColeccion.get(i).setDescuento(descue);
			ProductosidReunionColeccion.get(i).setIva(valoriva);			
		}

		if(ProductosidReunionColeccion_DEVOLUCIONES.size()!=0){
			for (int i = 0; i < ProductosidReunionColeccion_DEVOLUCIONES.size(); i++) {				
				String cantidad = (String) getTblVentaDetalle().getModel().getValueAt(i, 3);
				String precio = (String) getTblVentaDetalle().getModel().getValueAt(i, 4);
				String desc = (String) getTblVentaDetalle().getModel().getValueAt(i, 5);
				String impuestos = (String) getTblVentaDetalle().getModel().getValueAt(i, 6);				
				String valorLinea = (String) getTblVentaDetalle().getModel().getValueAt(i, 7);
				String tipo_producto = (String) getTblVentaDetalle().getModel().getValueAt(i, 8);	
				TipoProductoIf tipoProducto = (TipoProductoIf) tiposProductoByIdMap.get(Long.parseLong(tipo_producto));

				if(valorLinea!=null && !valorLinea.equals(""))	valorLinea=Utilitarios.removeDecimalFormat(valorLinea);
				if(impuestos!=null && !impuestos.equals(""))	impuestos=Utilitarios.removeDecimalFormat(impuestos);
				if(desc!=null && !desc.equals(""))	desc=Utilitarios.removeDecimalFormat(desc);
				if(precio!=null && !precio.equals(""))	precio=Utilitarios.removeDecimalFormat(precio);

				//precio=new Double(Utilitarios.redondeoValor(new Double(precio).doubleValue())).toString();

				BigDecimal valCant=new BigDecimal(cantidad).multiply(new BigDecimal(precio));				
				totalSubtotal=totalSubtotal.add(valCant);
				totalDesctotal=totalDesctotal.add(new BigDecimal(desc));
				totalImptotal=totalImptotal.add(new BigDecimal(impuestos));
				totalValorFinal=totalValorFinal.add(new BigDecimal(valorLinea));
				if (!tipoProducto.getCodigo().equals("TA"))
					totalSinTarjeta+=Double.parseDouble(valorLinea);
				BigDecimal valorFinal = BigDecimal.ZERO;			valorFinal=new BigDecimal(valorLinea);
				BigDecimal descue = BigDecimal.ZERO;				descue = new BigDecimal(desc);			
				BigDecimal valoriva = BigDecimal.ZERO;				valoriva=new BigDecimal(impuestos);				
				ProductosidReunionColeccion_DEVOLUCIONES.get(i).setValor(valorFinal);
				ProductosidReunionColeccion_DEVOLUCIONES.get(i).setDescuento(descue);
				ProductosidReunionColeccion_DEVOLUCIONES.get(i).setIva(valoriva);			
			}
		}

		getTxtTotalFinal().setText(formatoDecimal.format(totalValorFinal));
		getTxtSubtotalFinal().setText(formatoDecimal.format(totalSubtotal));
		getTxtDescuentoFinal().setText(formatoDecimal.format(totalDesctotal));
		getTxtImpuestosFinal().setText(formatoDecimal.format(totalImptotal));

		setDeudaFinal(Utilitarios.removeDecimalFormat(getTxtTotalFinal().getText())); 
	}


	private void buscarCliente() {
		clean_cliente();
		Long idCorporacion = 0L;
		if (corporacionIf != null)
			idCorporacion = corporacionIf.getId();

		clienteCriteria = new ClienteCriteria("Clientes", idCorporacion,CODIGO_TIPO_CLIENTE);
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.add(80);
		anchoColumnas.add(300);
		anchoColumnas.add(300);
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria,JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);

		if (popupFinder.getElementoSeleccionado() != null) {
			getPnCliente().setBorder(new TitledBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 51, 0), null, new Color(153, 51, 0), null),
							"Datos del cliente", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12)));

			clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
			getTxtNombre().setText(clienteIf.getNombreLegal());
			getTxtCedula().setText(clienteIf.getIdentificacion());

			try {
				corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
				getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error al consultar la Coroporación del cliente", SpiritAlert.ERROR);
				e.printStackTrace();
			}

			clienteOficinaIf = null;
			getTxtOficina().setText("");

			try {
				Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(clienteIf.getId());

				if (oficinas.size() > 0) {
					clienteOficinaIf = oficinas.iterator().next();
					getTxtDireccion().setText(clienteOficinaIf.getDireccion());
					String telefono = clienteOficinaIf.getTelefono();
					String email = clienteOficinaIf.getEmail();

					if (telefono == null)
						telefono = "";
					if (email == null)
						email = "";

					getTxtEmail().setText(email);
					getTxtTelefono().setText(telefono);
					getTxtOficina().setText(clienteOficinaIf.getCodigo() + " - " + clienteOficinaIf.getDescripcion());
				}
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error al consultar la oficina del cliente", SpiritAlert.ERROR);
			}

			try {
				Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(clienteIf.getId());
				if (oficinas.size() == 1) {
					clienteOficinaIf = oficinas.iterator().next();
					getTxtOficina().setText(clienteOficinaIf.getCodigo() + " - " + clienteOficinaIf.getDescripcion());
				}
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error al consultar la oficina del cliente", SpiritAlert.ERROR);
			}
		}
	}

	private void mostrar_resumen() {
		String visible = getLblVisible().getText();
		if (visible.equals("no")) {
			getPnResumenPagos().setVisible(true);
			getLblVisible().setText("si");
		}
		if (visible.equals("si")) {
			getPnResumenPagos().setVisible(false);
			getLblVisible().setText("no");
		}
	}

	private void buscarProducto() {
		String mmpg = "MG";
		Long idCorporacion = 0L;
		BigDecimal donacionValor = BigDecimal.ZERO;
		productoCriteria = new ProductoCriteria("Producto", idCorporacion, "","", "", false, mmpg);
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),productoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if (popupFinder.getElementoSeleccionado() != null) {
			productoIf = (ProductoIf) popupFinder.getElementoSeleccionado();
			GenericoIf generico = (GenericoIf) genericosMap.get(productoIf.getGenericoId());
			if (!generico.getNombreGenerico().contains("GIFTCARD") && !generico.getCodigo().equals("TA")) {
				getTxtIdProducto().setText(productoIf.getId().toString());
				getTxtCodigoProducto().setText(productoIf.getCodigo());
				getTxtDescripcion().setText("");
				// getTxtPVP().setText(productoIf.getCosto().toString());
				getTxtTipoProducto().setText(generico.getTipoproductoId().toString());
				getTxtIVA().setText(generico.getCobraIva());
				///////DONACION
				DonacionTipoproductoIf donacion = (DonacionTipoproductoIf) donacionesTipoProductoMap.get(generico.getTipoproductoId());
				donacionValor = (donacion!=null)?donacion.getValor():BigDecimal.ZERO;
				// MODELO
				Long modeloId = productoIf.getModeloId();
				ModeloIf modelo = (modeloId!=null)?(ModeloIf) modelosMap.get(modeloId):null;
				if (modelo != null && !modelo.getNombre().equals("NINGUNO"))
					getTxtDescripcion().setText((modelo!=null)?modelo.getNombre():"");
				if (getTxtDescripcion().getText().equals(""))
					getTxtDescripcion().setText(generico.getNombreGenerico());
				// COLOR
				Long colorId = productoIf.getColorId();
				getTxtIdProducto().setText(productoIf.getId().toString());
				getTxtIdGiftcard().setText(null);
				ColorIf color = (colorId!=null)?(ColorIf) coloresMap.get(colorId):null;
				if (color != null && !color.getNombre().equals("NINGUNO"))
					getTxtDescripcion().setText(getTxtDescripcion().getText().toString() + " - " + ((color!=null)?color.getNombre():""));
				// TALLA
				Long presentacionId = productoIf.getPresentacionId();
				PresentacionIf presentacion = (presentacionId!=null)?(PresentacionIf) presentacionesMap.get(presentacionId):null;
				if (presentacion != null && !presentacion.getNombre().equals("NINGUNA"))
					getTxtDescripcion().setText(getTxtDescripcion().getText().toString() + " (" + ((presentacion!=null)?presentacion.getNombre():"") + ")");
				// PRECIO
				Map aMap4 = new HashMap();
				aMap4.clear();
				aMap4.put("listaprecioId", findListaPrecioByCodigo("OFI").getId());
				aMap4.put("productoId", productoIf.getId());
				aMap4.put("estado", "A");
				Iterator cajavalorIt4 = null;
				try {
					cajavalorIt4 = SessionServiceLocator.getPrecioSessionService().findPrecioByQuery(aMap4).iterator();
				} catch (GenericBusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (cajavalorIt4.hasNext()) {
					PrecioIf precioif = (PrecioIf) cajavalorIt4.next();
					getTxtPVP().setText(precioif.getPrecio().toString());

				} else {
					getTxtPVP().setText("0.00");
				}

				// LOTE
				LoteIf lote = (LoteIf) lotesMap.get(productoIf.getId());
				if (lote!=null)
					getTxtLoteid().setText(lote.getId().toString());
				else
					getTxtLoteid().setText("");

				getTxtCantidad().setText("1");
				getTxtDescuento().setText("0");
				anadirProducto("0");
			} else {
				SpiritAlert.createAlert("Este producto sólo puede ser agregado mediante código de barras", SpiritAlert.WARNING);
				getTxtCodigoBarras().grabFocus();
			}
		}
	}

	// //////////////////////LISTENERS INICIO/////////////////


	private void actualizarProducto(String ingreso) {

		tableModel = (DefaultTableModel) getTblVentaDetalle().getModel();
		TipoDocumentoIf tipoDocumento = null;
		Long usuarioId;
		Long documento = null;
		usuarioId = ((UsuarioIf) Parametros.getUsuarioIf()).getId();

		if (Utilitarios.removeDecimalFormat(getTxtDescuento().getText()).equals(""))	getTxtDescuento().setText("0");

		recalcular_item("BORRAR");
		String cantidad = "0";

		if (ingreso.equals("S")) {
			cantidad = getTxtCantidadDetalle().getText();
			getTxtCantidad().setText(cantidad);
		} else {
			cantidad = getTxtCantidad().getText();
			getTxtCantidadDetalle().setText(cantidad);
		}
		if (cantidad.equals("")) {
			SpiritAlert.createAlert("La cantidad debe ser mayor que 1 !!!",SpiritAlert.INFORMATION);
		} else if (!getTxtCantidad().getText().equals("0")) {
			Vector<String> fila = new Vector<String>();
			boolean isExisteProducto = false;
			boolean isGiftcardOrLoyaltyCard = false;
			if (ProductosidReunionColeccion.size() != 0) {
				// Recorro la coleccion de producto
				for (int l = 0; l < ProductosidReunionColeccion.size(); l++) {
					PedidoDetalleIf temporal = (PedidoDetalleIf) ProductosidReunionColeccion.get(l);
					String productoTemp = (temporal.getGiftcardId() == null)?new Long(temporal.getProductoId()).toString():new Long(temporal.getGiftcardId()).toString();
					ProductoIf producto = (ProductoIf) productosMap.get(temporal.getProductoId());
					GenericoIf generico = (GenericoIf) genericosMap.get(producto.getGenericoId());
					TipoProductoIf tipoProducto = (TipoProductoIf) tiposProductoByIdMap.get(generico.getTipoproductoId());
					// Si el producto cliente ingresado ya esta en lista, entons muestro un mensaje
					if (temporal.getGiftcardId() == null && !tipoProducto.getCodigo().equals("TA")) {
						if (productoTemp.equals(getTxtIdProducto().getText().trim())) {
							eliminarDetallePedido("S", ""); // elimino valores del total general y la fila
							isGiftcardOrLoyaltyCard = false;
							break;
						}
					} else {
						/*if (productoTemp.equals(getTxtIdGiftcard().getText().trim())) {
							eliminarDetallePedido("S", ""); // elimino valores del total general y la fila
						}*/
						isGiftcardOrLoyaltyCard = true;
					}
				}
			}

			if (!isGiftcardOrLoyaltyCard) {
				PedidoDetalleData subData = new PedidoDetalleData();

				subData.setDocumentoId(documento);
				subData.setProductoId(new Long(getTxtIdProducto().getText().trim()));
				subData.setGiftcardId((getTxtIdGiftcard().getText() != null && !getTxtIdGiftcard().getText().equals(""))?new Long(getTxtIdGiftcard().getText().trim()):null);
				subData.setCantidadpedida(BigDecimal.valueOf(Double.parseDouble(cantidad.trim())));
				subData.setCantidad(BigDecimal.valueOf(Double.parseDouble(cantidad.trim())));
				subData.setDescripcion(this.getTxtDescripcion().getText());
				subData.setPrecio(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPVP().getText()))));
				subData.setPrecioreal(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPVP().getText()))));
				BigDecimal subtotal = subData.getPrecio().multiply(subData.getCantidad());			
				if (getTxtLoteid().getText() != null
						&& !getTxtLoteid().getText().equals(""))
					subData.setLoteId(new Long(getTxtLoteid().getText()));

				subData.setValor(subtotal);
				subData.setIce(BigDecimal.valueOf(0.0));
				subData.setOtroimpuesto(BigDecimal.valueOf(0.0));
				subData.setDescuentoGlobal(BigDecimal.ZERO);
				//subData.setCompraId(null);
				BigDecimal descu = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDescuento().getText()));// este
				System.out.println("DESCU ACTUALIZANDO"+descu);
				subData.setDescuento(descu);
				BigDecimal iva = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtIVA().getText()));// este
				subData.setIva(iva);


				System.out.println("iva fuera de RECALCULANDO"+getTxtIVA().getText());

				BigDecimal valorFinal = subtotal.subtract(descu);
				valorFinal=valorFinal.add(iva);
				// getTxtImporte().setText(new
				// BigDecimal(Utilitarios.redondeoValor(valorFinal.doubleValue())).toString());
				getTxtImporte().setText(formatoDecimal.format(Utilitarios.redondeoValor(valorFinal.doubleValue())));

				ProductosidReunionColeccion.add(subData);

				fila.add(getTxtCodigoProducto().getText());
				fila.add(getTxtDescripcion().getText());
				fila.add(getTxtCantidad().getText());
				fila.add(getTxtCantidad().getText());
				fila.add(getTxtPVP().getText());
				//johanna
				fila.add(descu.toString());
				fila.add(getTxtIVA().getText());
				fila.add(getTxtImporte().getText());
				fila.add(getTxtTipoProducto().getText());

				tableModel.addRow(fila);

				recalcularDonacion("NORMAL");

				llenar_totalGeneral();
			} else {
				SpiritAlert.createAlert("Acción no válida para producto seleccionado", SpiritAlert.WARNING);
			}
		} else {
			System.out.println("");
		}
		clean_producto();
	}

	private void actualizarProductoDevolucion() {

		tableModel = (DefaultTableModel) getTblVentaDetalle().getModel();
		TipoDocumentoIf tipoDocumento = null;
		Long usuarioId;
		Long documento = null;
		usuarioId = ((UsuarioIf) Parametros.getUsuarioIf()).getId();
		documento = findDocumentoByCodigo("DEV").getId();
		if (getTxtDescuento().getText().equals("")) {
			getTxtDescuento().setText("0");
			recalcular_item("borrar");
		}
		String cantidad = "0";
		cantidad = getTxtCantidadDevuelta().getText();

		System.out.println("CANTIDA DEV:"+cantidad);
		System.out.println("CANTIDA ORI anterior:"+getTxtCantidad().getText());


		if (cantidad.equals("")) {
			SpiritAlert.createAlert("La cantidad debe ser mayor que 1 !!!",SpiritAlert.INFORMATION);
		} else if (!getTxtCantidadDevuelta().getText().equals("0")) {
			Vector<String> fila = new Vector<String>();
			boolean isExisteProducto = false;
			if (ProductosidReunionColeccion_DEVOLUCIONES.size() != 0) {
				// Recorro la coleccion de producto
				for (int l = 0; l < ProductosidReunionColeccion_DEVOLUCIONES.size(); l++) {
					FacturaDetalleData temporal = (FacturaDetalleData) ProductosidReunionColeccion_DEVOLUCIONES.get(l);
					String productoTemp = new Long(temporal.getProductoId()).toString();
					// Si el producto cliente ingresado ya esta en lista, entonces muestro un mensaje
					if (productoTemp.equals(getTxtIdProducto().getText().trim())) {		
						eliminarDetalleFactura("S"); // elimino valores del total geeral y la fila
					}
				}
			}


			FacturaDetalleData subData = new FacturaDetalleData();
			subData.setDocumentoId(documento);
			subData.setProductoId(new Long(getTxtIdProducto().getText().trim()));
			subData.setCantidad(BigDecimal.valueOf(Double.parseDouble(getTxtCantidadDetalle().getText().trim())));
			subData.setDescripcion(this.getTxtDescripcion().getText());
			subData.setPrecio(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPVP().getText()))));
			subData.setPrecioReal(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPVP().getText()))));


			if (getTxtLoteid().getText() != null && !getTxtLoteid().getText().equals(""))
				subData.setLoteId(new Long(getTxtLoteid().getText()));

			subData.setCantidadDevuelta(BigDecimal.valueOf(Double.parseDouble(cantidad.trim())));
			BigDecimal subtotal = subData.getPrecio().multiply(subData.getCantidadDevuelta());
			subData.setValor(subtotal);
			// subData.setIva(new BigDecimal(getTxtIVA().getText()));
			subData.setIce(BigDecimal.valueOf(0.0));
			subData.setOtroImpuesto(BigDecimal.valueOf(0.0));
			subData.setDescuentoGlobal(BigDecimal.ZERO);
			//subData.setCompraId(null);

			BigDecimal descuvalor = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDescuento().getText()));// este
			BigDecimal canOri = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtCantidad().getText()));// este
			System.out.println("descuvalor"+descuvalor);
			System.out.println("canOri"+canOri);
			System.out.println("subData.getPrecio1213123aaaaaa()"+subData.getPrecio());


			BigDecimal porcen= descuvalor.multiply(new BigDecimal(100));
			BigDecimal precioCanori= canOri.multiply(subData.getPrecio());
			Double pp=0.00;
			BigDecimal descuentoPorcentaje=BigDecimal.ZERO;

			System.out.println("PORCEN>"+porcen);
			System.out.println("precioCanori>"+precioCanori);

			if(porcen.doubleValue() != 0D)
			{
				pp=(porcen.doubleValue())/precioCanori.doubleValue();	
				System.out.println("pp>"+pp);
				descuentoPorcentaje= new BigDecimal(pp);
				descuentoPorcentaje=descuentoPorcentaje.setScale(2, BigDecimal.ROUND_HALF_UP);									
			}

			System.out.println("descuentoPorcentaje>"+descuentoPorcentaje);
			if(descuentoPorcentaje.doubleValue() != 0D){
				BigDecimal canVal=subData.getCantidadDevuelta().multiply(subData.getPrecio());
				BigDecimal descuvalorNew = canVal.multiply(descuentoPorcentaje);
				descuvalorNew = descuvalorNew.divide(new BigDecimal(100));
				//descuvalorNew = new BigDecimal(Utilitarios.redondeoValor(descuvalorNew.doubleValue()));
				descuvalorNew = new BigDecimal(descuvalorNew.doubleValue());
				subData.setDescuento(descuvalorNew);// en valor, no en %
				getTxtDescuento().setText(descuvalorNew.toString());
				System.out.println("descuvalorNew>"+descuvalorNew);

			}
			else{
				getTxtDescuento().setText("0.00");
				subData.setDescuento(BigDecimal.ZERO);
			}


			System.out.println("getTxtDescuento>"+getTxtDescuento().getText());

			subtotal = subtotal.subtract(new BigDecimal(getTxtDescuento().getText()));


			BigDecimal iva = subtotal.multiply(new BigDecimal(12));
			iva = iva.divide(new BigDecimal(100));

			getTxtIVA().setText(iva.toString());
			System.out.println("iva>><"+iva);

			subData.setIva(iva);
			BigDecimal valorFinal = subtotal.add(iva);
			getTxtImporte().setText(formatoDecimal.format(Utilitarios.redondeoValor(valorFinal.doubleValue())));


			ProductosidReunionColeccion_DEVOLUCIONES.add(subData);

			fila.add(getTxtCodigoProducto().getText());
			fila.add(getTxtDescripcion().getText());
			fila.add(getTxtCantidad().getText());
			fila.add(getTxtCantidadDevuelta().getText());
			fila.add(getTxtPVP().getText());
			fila.add(getTxtDescuento().getText());
			fila.add(getTxtIVA().getText());
			fila.add(getTxtImporte().getText());
			fila.add(getTxtTipoProducto().getText());
			tableModel.addRow(fila);

			//recalcularDonacion("DEV");

			//viejo:llenar_totalGeneral("S", "dev");
			llenar_totalGeneral();

		} else {
			System.out.println("");
		}
		clean_producto();
	}

	KeyListener onKeyDescuentoGlobal = new KeyAdapter() {
		//public void keyReleased(KeyEvent evt) {// para q vaya recalculando el
		public void keyTyped(KeyEvent e) {//
			if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB) {

				aplicarDescuento();

			}
		}
	};


	public void aplicarDescuento(){
		tableModel = (DefaultTableModel) getTblVentaDetalle().getModel();	
		for (int l = 0; l < ProductosidReunionColeccion.size(); l++) {				
			PedidoDetalleIf temporal = (PedidoDetalleIf) ProductosidReunionColeccion.get(l);
			if (temporal.getGiftcardId() == null) {
				String val=temporal.getValor().toString();
				String codigo = (String) getTblVentaDetalle().getModel().getValueAt(l, 0);
				String tipo_prod = (String) getTblVentaDetalle().getModel().getValueAt(l, 8);				
				String descAnt = (String) getTblVentaDetalle().getModel().getValueAt(l, 5);
				String valorivaAnt = (String) getTblVentaDetalle().getModel().getValueAt(l, 6);
				String valorAnt = (String) getTblVentaDetalle().getModel().getValueAt(l, 7);
				getTxtCantidadDetalle().setText(temporal.getCantidad().toString());
				getTxtCantidad().setText(temporal.getCantidad().toString());
				getTxtPVP().setText(temporal.getPrecio().toString());
				getTxtIdProducto().setText(temporal.getProductoId().toString());
				getTxtIdGiftcard().setText((temporal.getGiftcardId() != null)?temporal.getGiftcardId().toString():null);
				getTxtCodigoProducto().setText(codigo);// ahi pongo el codigo del
				getTxtTipoProducto().setText(tipo_prod);				
				getTxtDescripcion().setText(temporal.getDescripcion());
				//valores importantes
				getTxtIVA().setText(valorivaAnt);
				getTxtImporte().setText(valorAnt);
				getTxtDescuento().setText(descAnt);
				String cant = getTxtCantidadDetalle().getText();
				if (cant.equals("")) 
					cant = "0";
				BigDecimal cantidad = new BigDecimal(cant);
				if (getTxtDescuento().getText().equals(""))	
					getTxtDescuento().setText("0");

				String dglobalS=getTxtPorcentajeDescuento().getText();
				if(dglobalS==null) 
					dglobalS="0";
				BigDecimal dglobal=new BigDecimal(dglobalS);
				getTxtDescuento().setText(dglobal.toString());
				// cantidad mayor que cero
				if (cantidad.compareTo(new BigDecimal("0")) == 1) {				
					recalcular_item("OK");										
					tableModel.setValueAt(getTxtDescuento().getText(), l, 5);
					tableModel.setValueAt(getTxtIVA().getText(), l, 6);
					tableModel.setValueAt(getTxtImporte().getText(), l, 7);
					//VIEJO:llenar_totalGeneral("S", "");
					llenar_totalGeneral();
				}
			}
		}
	}

	KeyListener onKeyCantidadDev = new KeyAdapter() {
		// si da tab o enter, el foco pase a descuento
		public void keyTyped(KeyEvent e) {//
			if (e.getKeyChar() == KeyEvent.VK_ENTER	|| e.getKeyChar() == KeyEvent.VK_TAB) {
				String cantdev = getTxtCantidadDevuelta().getText();
				String cantoriginal = getTxtCantidadDetalle().getText();

				if (cantdev.equals("")) cantdev = "0";
				if (cantoriginal.equals("")) cantoriginal = "0";

				BigDecimal cantidaddev = new BigDecimal(cantdev);

				if (getTxtDescuento().getText().equals(""))  getTxtDescuento().setText("0");

				BigDecimal cantidadOrig = new BigDecimal(cantoriginal);
				BigDecimal diferencia = new BigDecimal("0");
				diferencia = cantidadOrig.subtract(cantidaddev);

				// -1 negativo
				// 0 zero
				// 1 positivo
				int tipo = diferencia.signum();
				if (tipo == -1) {
					getTxtCantidadDevuelta().setText("");
					SpiritAlert
					.createAlert(
							"Debe ingresar un valor menor o igual a la cantidad de la factura",
							SpiritAlert.INFORMATION);
				} else {
					if (cantidaddev.compareTo(new BigDecimal("0")) == 1) {
						getTxtCantidadDevuelta().setText(cantidaddev.toString());
						//llenar_totalGeneral();
						//recalcular_item_devolucion();

						actualizarProductoDevolucion();
						llenar_totalGeneral();
						getTxtItem().setText("");
					} else {
						SpiritAlert
						.createAlert(
								"Debe ingresar un valor mayor a cero o <Eliminar> de la lista ese detalle",
								SpiritAlert.INFORMATION);
					}
				}
			}
		}
	};

	KeyListener onKeyCantidadIngreso = new KeyAdapter() {
		// si da tab o enter, el foco pase a descuento
		public void keyTyped(KeyEvent e) {//
			if (e.getKeyChar() == KeyEvent.VK_ENTER
					|| e.getKeyChar() == KeyEvent.VK_TAB) {
				// getTxtPorcentajeDescuento().grabFocus();
				boolean existe = false;
				for (int l = 0; l < ProductosidReunionColeccion.size(); l++) {
					PedidoDetalleIf temporal = (PedidoDetalleIf) ProductosidReunionColeccion.get(l);
					String productoTemp = (temporal.getGiftcardId() == null)?new Long(temporal.getProductoId()).toString():new Long(temporal.getGiftcardId()).toString();
					String cant = getTxtCantidadDetalle().getText();
					if (cant.equals("")) cant = "0";
					getTxtCantidad().setText(cant);

					if (temporal.getGiftcardId() == null) {
						if (productoTemp.equals(getTxtIdProducto().getText().trim())) {
							existe = true;
							Object[] options = { si, no };
							int opcion = JOptionPane
							.showOptionDialog(
									null,
									"El producto ya existe en la lista... Desea Actualizar?",
									"Confirmación",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									options, no);
							if (opcion == JOptionPane.YES_OPTION) {
								actualizarProducto("N");
							}
						}
					} else {
						if (productoTemp.equals(getTxtIdGiftcard().getText().trim())) {
							existe = true;
							Object[] options = { si, no };
							int opcion = JOptionPane
							.showOptionDialog(
									null,
									"El producto ya existe en la lista... Desea Actualizar?",
									"Confirmación",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									options, no);
							if (opcion == JOptionPane.YES_OPTION) {
								actualizarProducto("N");
							}
						}
					}
				}
				if (!existe)
					anadirProducto("0");
			}
		}
	};

	KeyListener onKeyCantidad = new KeyAdapter() {
		// si da tab o enter, el foco pase a descuento
		public void keyTyped(KeyEvent e) {//
			if (e.getKeyChar() == KeyEvent.VK_ENTER
					|| e.getKeyChar() == KeyEvent.VK_TAB) {
				// getTxtDescuento().grabFocus();
				boolean existe = false;
				for (int l = 0; l < ProductosidReunionColeccion.size(); l++) {
					PedidoDetalleData temporal = (PedidoDetalleData) ProductosidReunionColeccion
					.get(l);

					String productoTemp = (temporal.getGiftcardId() == null)?new Long(temporal.getProductoId()).toString():new Long(temporal.getGiftcardId()).toString();
					if (temporal.getGiftcardId() == null) {
						if (productoTemp.equals(getTxtIdProducto().getText().trim())) {
							existe = true;
							Object[] options = { si, no };
							int opcion = JOptionPane
							.showOptionDialog(
									null,
									"El producto ya existe en la lista... Desea Actualizar?",
									"Confirmación",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									options, no);
							if (opcion == JOptionPane.YES_OPTION) {
								actualizarProducto("N");// esta en s.. para que tome
								// la cantidad... de
								// txtCantidadIngreso y
								// descuento de txtDescLocal
							}
						}
					} else {
						if (productoTemp.equals(getTxtIdGiftcard().getText().trim())) {
							existe = true;
							Object[] options = { si, no };
							int opcion = JOptionPane
							.showOptionDialog(
									null,
									"El producto ya existe en la lista... Desea Actualizar?",
									"Confirmación",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									options, no);
							if (opcion == JOptionPane.YES_OPTION) {
								actualizarProducto("N");// esta en s.. para que tome
								// la cantidad... de
								// txtCantidadIngreso y
								// descuento de txtDescLocal
							}
						}
					}
				}
				if (!existe)
					anadirProducto("0");
			}
		}
	};

	/*KeyListener onKeyDescuento = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			// si da tab entonces que verifique q el producto exista para
			// actualizar sin q l foco vaya añadir
			if (evt.getKeyChar() == KeyEvent.VK_TAB) {
				boolean existe = false;
				for (int l = 0; l < ProductosidReunionColeccion.size(); l++) {
					PedidoDetalleData temporal = (PedidoDetalleData) ProductosidReunionColeccion
					.get(l);

					String productoTemp = new Long(temporal.getProductoId())
					.toString();
					if (productoTemp
							.equals(getTxtIdProducto().getText().trim())) {
						existe = true;
						Object[] options = { si, no };
						int opcion = JOptionPane
						.showOptionDialog(
								null,
								"El producto ya existe en la lista... Desea Actualizar?",
								"Confirmación",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null,
								options, no);
						if (opcion == JOptionPane.YES_OPTION) {
							actualizarProducto("N");// esta en s.. para que tome
							// la cantidad... de
							// txtCantidadIngreso y
							// descuento de txtDescLocal
						}
					}
				}
				if (!existe)
					anadirProducto("0");
			}
		}

		public void keyReleased(KeyEvent evt) {
			String cant = getTxtCantidad().getText();
			if (cant.equals(""))
				cant = "0";
			BigDecimal cantidad = new BigDecimal(cant);
			if (getTxtDescuento().getText().equals(""))
				getTxtDescuento().setText("0");

			if (cantidad.compareTo(new BigDecimal("0")) == 1) {
				getTxtPorcentajeDescuento()
				.setText(getTxtDescuento().getText());
				getTxtCantidadDetalle().setText(cantidad.toString());
				recalcular_item();
			} else
				getTxtImporte().setText("0.00");

		}

		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER
					|| e.getKeyChar() == KeyEvent.VK_TAB) {
				boolean existe = false;
				for (int l = 0; l < ProductosidReunionColeccion.size(); l++) {
					PedidoDetalleData temporal = (PedidoDetalleData) ProductosidReunionColeccion
					.get(l);
					String productoTemp = new Long(temporal.getProductoId())
					.toString();
					if (productoTemp
							.equals(getTxtIdProducto().getText().trim())) {
						existe = true;
						Object[] options = { si, no };
						int opcion = JOptionPane
						.showOptionDialog(
								null,
								"El producto ya existe en la lista... Desea Actualizar?",
								"Confirmación",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null,
								options, no);
						if (opcion == JOptionPane.YES_OPTION) {
							actualizarProducto("N");// esta en s.. para que tome
							// la cantidad... de
							// txtCantidadIngreso y
							// descuento de txtDescLocal
						}
					}
				}
				if (!existe)
					anadirProducto("0");
			}
		}
	};*/

	public void recalcular_item(String borrar) {
		String cant = getTxtCantidad().getText();
		BigDecimal cantidad = new BigDecimal(cant);
		String descuento = Utilitarios.removeDecimalFormat(getTxtDescuento().getText());
		BigDecimal desc = new BigDecimal(descuento);

		BigDecimal val_cant = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtPVP().getText())).multiply(cantidad);

		if(getTxtPorcentajeDescuento().getText()!=null && !getTxtPorcentajeDescuento().getText().equals("0") && !getTxtPorcentajeDescuento().getText().toString().equals("")){
			BigDecimal tempo = new BigDecimal(getTxtPorcentajeDescuento().getText());
			tempo = val_cant.multiply(tempo);
			System.out.println("val_cant>"+val_cant);
			tempo = tempo.divide(new BigDecimal("100"));						
			tempo = new BigDecimal(new Double(tempo.doubleValue()).toString());			
			//if(tempo!=null)  tempo=tempo.setScale(2, BigDecimal.ROUND_HALF_UP);
			System.out.println("TEMPO!!!!>"+tempo);
			getTxtDescuento().setText(tempo.toString());
			desc=tempo;			

		}
		BigDecimal valorFinal = val_cant.subtract(desc);
		BigDecimal iva1 = (getTxtIdGiftcard().getText().equals(""))?valorFinal.multiply(BigDecimal.valueOf(IVA)):BigDecimal.ZERO;
		//iva1 = new BigDecimal(new Double(Utilitarios.redondeoValor(iva1.doubleValue())).toString());
		//if(iva1!=null)  iva1=iva1.setScale(2, BigDecimal.ROUND_HALF_UP);

		//getTxtIVA().setText(formatoDecimal.format(iva1));
		getTxtIVA().setText(iva1.toString());
		valorFinal = valorFinal.add(iva1);		
		//valorFinal = new BigDecimal(new Double(Utilitarios.redondeoValor(valorFinal.doubleValue())).toString());
		//if(valorFinal!=null)  valorFinal=valorFinal.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println("VA FINAL222>"+valorFinal);

		//getTxtImporte().setText(formatoDecimal.format(valorFinal));
		getTxtImporte().setText(formatoDecimal.format(new Double(Utilitarios.redondeoValor(valorFinal.doubleValue()))));
		getTxtEsteItem().setText(formatoDecimal.format(new Double(Utilitarios.redondeoValor(valorFinal.doubleValue()))));



	}

	public void actualizarCliente(){
		// ****		
		try {			
			String nombreC=getTxtNombre().getText();
			if (nombreC==null) nombreC="";
			if(!nombreC.equals("")){	 

				ClienteIf clienteActualizar = ((ClienteIf) SessionServiceLocator.getClienteSessionService().findClienteByIdentificacion(getTxtCedula().getText()).iterator().next());
				ClienteOficinaIf clienteOficina = ((ClienteOficinaIf) SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(clienteActualizar.getId()).iterator().next());

				Vector<ClienteOficinaIf> detalleOficinaClienteColeccion = new Vector<ClienteOficinaIf>();
				Vector<ClienteZonaIf> clienteZonaColeccion = new Vector<ClienteZonaIf>();
				Vector<ClienteRetencionIf> clienteRetencionColeccion = new Vector<ClienteRetencionIf>();

				ClienteOficinaData data = new ClienteOficinaData();
				//String codigo = "001";
				data.setCodigo(clienteOficina.getCodigo());				 
				data.setFechaCreacion(DateHelperClient.getTimeStampNow());
				data.setEstado("A");
				data.setDescripcion(clienteOficina.getDescripcion());
				data.setDireccion(getTxtDireccion().getText());
				data.setCiudadId(clienteOficina.getCiudadId());
				data.setTelefono(getTxtTelefono().getText());
				data.setEmail(getTxtEmail().getText());
				data.setCalificacion("A");
				data.setClienteId(clienteActualizar.getId());
				detalleOficinaClienteColeccion.add(data);


				Map detalleContactoOficinaClienteMap = new HashMap();
				Map detalleIndicadorOficinaClienteMap = new HashMap();

				String identificacion=getTxtCedula().getText();
				Map detalleContactoOficinaClienteMap2 = new HashMap();
				Map detalleIndicadorOficinaClienteMap2 = new HashMap();
				Vector<ClienteOficinaIf> detalleOficina = new Vector<ClienteOficinaIf>();

				SessionServiceLocator.getClienteSessionService().actualizarCliente(clienteActualizar, clienteZonaColeccion, clienteRetencionColeccion, detalleOficinaClienteColeccion, detalleContactoOficinaClienteMap, detalleIndicadorOficinaClienteMap, clienteZonaColeccion, clienteRetencionColeccion, detalleOficina, detalleContactoOficinaClienteMap2, detalleIndicadorOficinaClienteMap2, false);

				SpiritAlert.createAlert("Cliente Actualizado con éxito", SpiritAlert.INFORMATION);

			}
			else{
				SpiritAlert.createAlert("No ha escogido ningún cliente"+nombreC, SpiritAlert.INFORMATION);
			}
		} catch (Exception e) {
			e.printStackTrace();

			SpiritAlert.createAlert("Ocurrió un error al guardar el Operador de Negocio", SpiritAlert.ERROR);
		}
	}


	MouseListener oMouseAdapterTblFactura = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt, "");
		}
	};

	KeyListener oKeyAdapterTblFactura = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt, "");

			if (evt.getKeyChar() == KeyEvent.VK_TAB) {
				getSplitPane1().setDividerLocation(5);
				if (getLblTipoDocumentoSeleccionado().getText().equals(
				"devolucion"))
					getTxtCantidadDevuelta().grabFocus();
				else
					getTxtCantidadDetalle().grabFocus();

			}

		}
	};

	KeyListener oKeyAdapterBtnBuscarCliente = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				buscarCliente();
			}
		}
	};
	KeyListener oKeyAdapterBtnNuevoCliente = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				NuevoCliente();
			}
		}
	};

	KeyListener oKeyAdapterBtnActualizarCliente = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				actualizarCliente();
			}
		}
	};

	KeyListener oKeyAdapterBtnFactura = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {				
				tipoDocumentoTransaccion = findTipoDocumentoByCodigo("FAC");
				setearPuntoImpresion("FAC");
				t_facturar();
			}
		}
	};

	KeyListener oKeyAdapterBtnNotaVenta = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {				
				tipoDocumentoTransaccion = findTipoDocumentoByCodigo("VTA");
				setearPuntoImpresion("VTA");
				t_nventa();
			}
		}
	};


	KeyListener oKeyAdapterBtnDevolucion = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {				
				tipoDocumentoTransaccion = findTipoDocumentoByCodigo("DEV");
				setearPuntoImpresion("DEV");
				t_devolucion();
			}
		}
	};


	KeyListener oKeyAdapterBtnDonacion = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {				
				donacion_Dialog();
			}
		}
	};

	KeyListener oKeyAdapterBtnAcreditar = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {		   
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {				
				creditoCliente();
			}
		}
	};

	KeyListener oKeyAdapterBtnPagoEfectivo = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {				
				cobroEfectivoDialog();
			}
		}
	};

	KeyListener oKeyAdapterBtnPagoTarjetaCredito = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {				
				tarjetaCreditoDialog();
			}
		}
	};


	KeyListener oKeyAdapterBtnPagoGiftCard = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {				
				giftCardDialog();
			}
		}
	};


	KeyListener oKeyAdapterBtnPagoCreditoCliente = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {	
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {				
				cobroCredito();
			}
		}
	};


	KeyListener oKeyAdapterBtnPagoCheque = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {			
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {				
				cobroCheque();
			}
		}
	};

	KeyListener oKeyAdapterBtnBorrarPagos = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {				
				clean_formapagos();
			}
		}
	};


	private void enableSelectedRowForUpdate(ComponentEvent evt,String devolucion) {
		try {
		if (getTblVentaDetalle().getSelectedRow() != -1) {
			int selectrow = getTblVentaDetalle().getSelectedRow();
			String codigo = (String) getTblVentaDetalle().getModel().getValueAt(selectrow, 0);
			String tipo_prod = (String) getTblVentaDetalle().getModel().getValueAt(selectrow, 8);

			getTxtTipoProducto().setText(tipo_prod);
			getTxtCodigoProducto().setText(codigo);// ahi pongo el codigo del producto
			if (getLblTipoDocumentoSeleccionado().getText().equals("devolucion")) {				
				String cantAnteriorDevuelta = (String) getTblVentaDetalle().getModel().getValueAt(selectrow, 3);				
				getTxtCantidadDevuelta().setText("");
				ProductosidReunionColeccion.clear();
				FacturaDetalleData reunionCompromisoTemp = null;
				reunionCompromisoTemp = (FacturaDetalleData) ProductosidReunionColeccion_DEVOLUCIONES.get(selectrow);
				getTxtIdProducto().setText(new Long(reunionCompromisoTemp.getProductoId()).toString());
				ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(reunionCompromisoTemp.getProductoId());
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(producto.getGenericoId());
				getTxtPVP().setText(reunionCompromisoTemp.getPrecio().toString());
				getTxtCantidad().setText(cantAnteriorDevuelta);
				if (generico.getUsaLote().equals("S"))
					getTxtLoteid().setText(reunionCompromisoTemp.getLoteId().toString());	
				//getTxtIVA().setText(formatoDecimal.format(Utilitarios.redondeoValor(reunionCompromisoTemp.getIva().doubleValue())).toString());
				getTxtIVA().setText(reunionCompromisoTemp.getIva().toString());
				BigDecimal val_cant = reunionCompromisoTemp.getValor();
				BigDecimal valorFinal = val_cant.subtract(reunionCompromisoTemp.getDescuento());
				valorFinal = valorFinal.add(reunionCompromisoTemp.getIva());				
				getTxtImporte().setText(valorFinal.toString());
				//getTxtImporte().setText(formatoDecimal.format(Utilitarios.redondeoValor(valorFinal.doubleValue())).toString());
				getTxtDescripcion().setText(reunionCompromisoTemp.getDescripcion());
				getTxtCantidadDetalle().setEnabled(false);				
				getTxtCantidadDetalle().setText(new Integer(reunionCompromisoTemp.getCantidad().toBigIntegerExact().intValue()).toString());
				getTxtItem().setText(getTxtDescripcion().getText());
				BigDecimal desc_porc = new BigDecimal("0");								
				String descTable = (String) getTblVentaDetalle().getModel().getValueAt(selectrow, 5);
				getTxtDescuento().setText(descTable.toString());
				getTxtEsteItem().setText(getTxtImporte().getText());
			} else {
				ProductosidReunionColeccion_DEVOLUCIONES.clear();
				PedidoDetalleIf reunionCompromisoTemp = null;
				reunionCompromisoTemp = (PedidoDetalleIf) ProductosidReunionColeccion.get(selectrow);
				getTxtIdProducto().setText(new Long(reunionCompromisoTemp.getProductoId()).toString());
				getTxtIdGiftcard().setText((reunionCompromisoTemp.getGiftcardId() != null)?new Long(reunionCompromisoTemp.getGiftcardId()).toString():null);
				ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(reunionCompromisoTemp.getProductoId());
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(producto.getGenericoId());
				getTxtPVP().setText(reunionCompromisoTemp.getPrecio().toString());				
				getTxtCantidad().setText(new Integer(reunionCompromisoTemp.getCantidad().toBigIntegerExact().intValue()).toString());	
				if (generico.getUsaLote().equals("S"))
					getTxtLoteid().setText(reunionCompromisoTemp.getLoteId().toString());				
				//getTxtIVA().setText(formatoDecimal.format(Utilitarios.redondeoValor(reunionCompromisoTemp.getIva().doubleValue())).toString());
				getTxtIVA().setText(reunionCompromisoTemp.getIva().toString());

				BigDecimal val_cant = reunionCompromisoTemp.getValor();
				BigDecimal valorFinal = val_cant.subtract(reunionCompromisoTemp.getDescuento());
				valorFinal = valorFinal.add(reunionCompromisoTemp.getIva());

				getTxtImporte().setText(valorFinal.toString());
				//getTxtImporte().setText(formatoDecimal.format(Utilitarios.redondeoValor(valorFinal.doubleValue())).toString());
				getTxtDescripcion().setText(reunionCompromisoTemp.getDescripcion());
				getTxtCantidadDetalle().setText(getTxtCantidad().getText());
				getTxtItem().setText(getTxtDescripcion().getText());
				BigDecimal desc_porc = new BigDecimal("0");				
				String descTable = (String) getTblVentaDetalle().getModel().getValueAt(selectrow, 5);				
				getTxtDescuento().setText(descTable.toString());
				getTxtEsteItem().setText(getTxtImporte().getText());
			}
		}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}


	KeyListener oKeyAdapterSetearCliente = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			if (getTxtCedula().getText().length() == MAX_LONGITUD_CEDULA) {
				boolean find = setearCliente("0");
				if (find) {		
					//clean_cliente();
				}
				else{
					clean_cliente();			
				}				
			} else {
				if (getTxtCedula().getText().length() == MAX_LONGITUD_RUC) {

				} else {
					boolean find = setearCliente("0");
					if (find && getTxtNombre().getText()!=null && !getTxtNombre().getText().equals("")) {
						//clean_cliente();
						getPnCliente().setBorder(
								new TitledBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 51,0), null,
										new Color(153, 51, 0), null),
										"Datos del cliente",TitledBorder.LEADING, TitledBorder.TOP,
										new Font("Tahoma", Font.BOLD, 12)));
					} 
					if (!find && getTxtNombre().getText()!=null && !getTxtNombre().getText().equals("")) {					
						marcarPanel(getPnCliente());
						clean_cliente();						
					}
				}
			}
		}

		public void keyTyped(KeyEvent e) {

			if (e.getKeyChar() == KeyEvent.VK_TAB) {
				if (getTxtNombre().getText().equals("")) {
					getTxtCedula().setNextFocusableComponent(getTxtDireccion());
					getBtnBuscar().setNextFocusableComponent(
							getBtnActualizarCliente());
				}
			}
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				if (getTxtCedula().getText().length() == MAX_LONGITUD_CEDULA) {
					if (getLblTipoDocumentoSeleccionado().getText().equals("facturar")) {
						if (getTxtCedula().getText().equals("9999999999")) {
							SpiritAlert.createAlert("No se puede ingresar empleado Consumidor Final, TIPO TRANSACCION= FACTURA",SpiritAlert.INFORMATION);
							getTxtCedula().setText("");
							clean_cliente();							
						}
					}
					if (!ValidarIdentificacion.esNumeroIdentificacionValido("CE", getTxtCedula().getText())) {
						SpiritAlert.createAlert("La identificación ingresada no es válida!!!", SpiritAlert.WARNING);
						getTxtCedula().grabFocus();
					}
					else{
						boolean find = setearCliente("0");
						if (find) {
							getPnCliente().setBorder(
									new TitledBorder(new SoftBevelBorder(
											BevelBorder.LOWERED, new Color(153, 51,
													0), null,
													new Color(153, 51, 0), null),
													"Datos del cliente",
													TitledBorder.LEADING, TitledBorder.TOP,
													new Font("Tahoma", Font.BOLD, 12)));
						} else {
							marcarPanel(getPnCliente());						
							getTxtCedula().grabFocus();
							NuevoCliente();
						}
					}
				} else {

					if (getTxtCedula().getText().length() == MAX_LONGITUD_RUC) {
						if (!ValidarIdentificacion.esNumeroIdentificacionValido("RU", getTxtCedula().getText())) {
							SpiritAlert.createAlert("La identificación ingresada no es válida!!!", SpiritAlert.WARNING);
							getTxtCedula().grabFocus();
						}
						else{
							boolean find = setearCliente("0");
							if (find) {
								getPnCliente().setBorder(
										new TitledBorder(new SoftBevelBorder(
												BevelBorder.LOWERED, new Color(153,
														51, 0), null, new Color(
																153, 51, 0), null),
																"Datos del cliente",
																TitledBorder.LEADING,
																TitledBorder.TOP, new Font(
																		"Tahoma", Font.BOLD, 12)));
							} else {
								SpiritAlert
								.createAlert(
										"No existe Cliente asociado a ese RUC",
										SpiritAlert.INFORMATION);
								NuevoCliente();
								// clean_cliente();
								marcarPanel(getPnCliente());
								getTxtCedula().grabFocus();
							}
						}

					} else {

						boolean find = setearCliente("0");
						if (find) {
							getPnCliente().setBorder(
									new TitledBorder(new SoftBevelBorder(
											BevelBorder.LOWERED, new Color(153,
													51, 0), null, new Color(
															153, 51, 0), null),
															"Datos del cliente",
															TitledBorder.LEADING,
															TitledBorder.TOP, new Font(
																	"Tahoma", Font.BOLD, 12)));
						} else {
							marcarPanel(getPnCliente());
							// clean_cliente();
							getTxtCedula().grabFocus();
							NuevoCliente();
						}

					}

				}
			}
		}

	};

	KeyListener oKeyAdapterSetearClienteTelefono = new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {

				if (!getTxtTelefono().getText().equals("")) {
					boolean find = setearClienteporTelefono();
					if (find) {
						getPnCliente().setBorder(
								new TitledBorder(new SoftBevelBorder(
										BevelBorder.LOWERED, new Color(153, 51,
												0), null,
												new Color(153, 51, 0), null),
												"Datos del cliente",
												TitledBorder.LEADING, TitledBorder.TOP,
												new Font("Tahoma", Font.BOLD, 12)));
					} else {
						SpiritAlert
						.createAlert(
								"No existe Cliente asociado a ese número de telefono",
								SpiritAlert.INFORMATION);
						marcarPanel(getPnCliente());
						getTxtTelefono().grabFocus();
						clean_cliente();
					}
				} else {
					SpiritAlert.createAlert(
							"Debe el número de telefono del cliente",
							SpiritAlert.INFORMATION);
					marcarPanel(getPnCliente());
					getTxtTelefono().grabFocus();
					clean_cliente();
				}

			}
		}

	};

	private boolean setearClienteporTelefono() {
		boolean find = false;
		clienteOficinaIf = null;
		Long clienteId = 0L;
		String telefono = getTxtTelefono().getText();
		try {
			Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService()
			.findClienteOficinaByTelefono(telefono);
			if (oficinas.size() == 1) {
				clienteOficinaIf = oficinas.iterator().next();

				clienteId = clienteOficinaIf.getClienteId();

				getTxtDireccion().setText(clienteOficinaIf.getDireccion());
				String email = clienteOficinaIf.getEmail();
				if (telefono == null)
					telefono = "";
				if (email == null)
					email = "";
				getTxtEmail().setText(email);
				getTxtTelefono().setText(telefono);
				getTxtOficina().setText(
						clienteOficinaIf.getCodigo() + " - "
						+ clienteOficinaIf.getDescripcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert
			.createAlert(
					"Se ha producido un error al consultar la oficina del cliente",
					SpiritAlert.ERROR);
		}
		corporacionIf = null;
		Iterator clienteIt;
		try {
			clienteIt = SessionServiceLocator.getClienteSessionService().findClienteById(clienteId)
			.iterator();
			if (clienteIt.hasNext()) {
				clienteIf = (ClienteIf) clienteIt.next();
				getTxtNombre().setText(clienteIf.getNombreLegal());
				find = true;
				getTxtCedula().setText(clienteIf.getIdentificacion());
				try {
					corporacionIf = SessionServiceLocator.getCorporacionSessionService()
					.getCorporacion(clienteIf.getCorporacionId());
					getTxtCorporacion().setText(
							corporacionIf.getCodigo() + " - "
							+ corporacionIf.getNombre());
				} catch (GenericBusinessException e) {
					SpiritAlert
					.createAlert(
							"Se ha producido un error al consultar la Coroporación del cliente",
							SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		} catch (GenericBusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return find;
	}

	public void marcarPanel(JPanel cmp) {
		Border compound = null;
		Border raisedbevel, loweredbevel;
		Border redline = BorderFactory.createLineBorder(Color.red);
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border redline2 = BorderFactory
		.createMatteBorder(3, 3, 3, 3, Color.red);
		compound = BorderFactory
		.createCompoundBorder(raisedbevel, loweredbevel);
		// Add a red outline to the frame.
		compound = BorderFactory.createCompoundBorder(redline2, compound);
		cmp.setBorder(compound);

	}

	KeyListener oKeyAdapterAnadirProductoUPC = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {			
			if (getTxtCodigoBarras().getText().length() == MAX_LONGITUD_BARCODE) {
				anadir_producto_UPC();
			}
			if (evt.getKeyChar() == KeyEvent.VK_TAB) {
				getTblVentaDetalle().grabFocus();
			}
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {				
				anadir_producto_UPC();
			}
			if (e.getKeyChar() == KeyEvent.VK_TAB) {
				getTblVentaDetalle().grabFocus();
			}
		}

	};

	private TipoPagoIf buscarTipoPagoByCodigo(String codigo) {
		TipoPagoIf tipoPago = null;
		Map parameterMap = new HashMap();
		parameterMap.put("empresaId", Parametros.getIdEmpresa());
		parameterMap.put("codigo", codigo);
		try {
			Iterator it = SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByQuery(parameterMap).iterator();
			if (it.hasNext()) {
				tipoPago = (TipoPagoIf) it.next();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return tipoPago;
	}

	private boolean llenar_datos_DOCUMENTO(String tipo_documento,String preimpreso, String donde) {
		tableModel = (DefaultTableModel) getTblVentaDetalle().getModel();
		for(int i= this.getTblVentaDetalle().getRowCount();i>0;--i)		tableModel.removeRow(i-1);		 
		boolean find = false;
		limpiarTabla(getTblVentaDetalle());

		if (donde.equals("devolucion"))		getTblVentaDetalle().getColumnModel().getColumn(3).setCellRenderer(new getCR_ejem_DEV());

		corporacionIf = null;
		Iterator facturaIt;
		boolean caducada=false;
		try {
			if (tipo_documento.equals("FAC")) {
				if (preimpreso.equals(""))	preimpreso = "0";
				// BUSCA LOS DATOS DE LA FACTURA... CABECERA
				if(donde.equals("devolucion")){
					getCmbTipoDocDevolucion().setEnabled(true);
					String tipoDoc = ((String) this.getCmbTipoDocDevolucion().getSelectedItem());
					if(tipoDoc.equals("FAC."))	tipoDocumentoTransaccion = findTipoDocumentoByCodigo("FAC");
					if(tipoDoc.equals("N/V."))	tipoDocumentoTransaccion = findTipoDocumentoByCodigo("VTA");					

					HashMap<String, Object> mapa5 = new HashMap<String, Object>();
					mapa5.clear();
					mapa5.put("preimpresoNumero", preimpreso);
					mapa5.put("tipodocumentoId", tipoDocumentoTransaccion.getId());					
					facturaIt = SessionServiceLocator.getFacturaSessionService().findFacturaByQuery(mapa5).iterator();
					Iterator facturaItuno;
					facturaItuno=facturaIt;
					FacturaIf facturaIfuno = null;
					if (facturaItuno.hasNext()) {
						facturaIfuno = (FacturaIf) facturaItuno.next();
						Date fechaFactura=facturaIfuno.getFechaFactura();
						Calendar fechaActual = new GregorianCalendar();
						java.sql.Date hoy=new java.sql.Date(fechaActual.getTime().getTime());
						java.util.Date y=fechaFactura;
						java.sql.Date x=new java.sql.Date(y.getTime());
						Long diferenciasDias=Utilitarios.obtenerDiferenciaDias(hoy,x);
						System.out.println("****************************"+hoy+"/"+x);
						Map aMap = new HashMap();
						aMap.put("empresaId", Parametros.getIdEmpresa());
						aMap.put("codigo", "DVDPOS");
						Iterator it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(aMap).iterator();
						Long diasVencimiento = 0L; 
						if (it.hasNext())
							diasVencimiento = Long.parseLong(((ParametroEmpresaIf) it.next()).getValor());
						if(diferenciasDias>diasVencimiento) {
							SpiritAlert.createAlert("No se aceptan devoluciones de facturas con más de " + diasVencimiento + " días de vencimiento.\nLa factura tiene: "+diferenciasDias+" día(s) vencida.",SpiritAlert.INFORMATION);
							caducada=true;
							clean();
						} else {
							System.out.println("facturaIfuno.getDescuento()->"+facturaIfuno.getDescuento());
							System.out.println("facturaIfuno.BOOLEAN()->"+facturaIfuno.getDescuento().equals(BigDecimal.ZERO));

							/*if(facturaIfuno.getDescuento()!=null){
								if(facturaIfuno.getDescuento().doubleValue() != 0D)
								{
									caducada=true;//para q no muestre el mensaje de que factura no existe
									SpiritAlert.createAlert("No puede hacer devoluciones de Facturas con promociones de Descuento",SpiritAlert.INFORMATION);
									clean();
								}
								else{
									facturaIt = SessionServiceLocator.getFacturaSessionService().findFacturaByQuery(mapa5).iterator();
								}
							}
							else{*/
								facturaIt = SessionServiceLocator.getFacturaSessionService().findFacturaByQuery(mapa5).iterator();
							//}

						}
					}	 
				}
				else{
					facturaIt = SessionServiceLocator.getFacturaSessionService().findFacturaByPreimpresoNumero(preimpreso).iterator();	
				}

				Long idTrackVentas = 0L;
				FacturaIf facturaIf = null;
				if (facturaIt.hasNext()) {
					facturaIf = (FacturaIf) facturaIt.next();
					idFactura = facturaIf.getId();
					// BUSCA EL IDTRACK_VENTAS DE ESA FACTURA
					Iterator documentosVentaIt;
					VentasDocumentosIf ventasDocumentosIf = null;
					documentosVentaIt = SessionServiceLocator.getVentasDocumentosSessionService().findVentasDocumentosByTablaId(idFactura).iterator();
					if (documentosVentaIt.hasNext()) {
						ventasDocumentosIf = (VentasDocumentosIf) documentosVentaIt.next();
						if (ventasDocumentosIf.getTablaNombre().equals("FACTURA"))
							idTrackVentas = ventasDocumentosIf.getVentastrackId();
					}

					ProductosidReunionColeccion.clear();
					ProductosidReunionColeccion_DEVOLUCIONES.clear();

					limpiarTabla(getTblVentaDetalle());
					// BUSCO LOS PAGOS/cobros -- DONACION CON EL IDTRACK_VENTAS
					Iterator pagosVentaIt;
					pagosVentaIt = SessionServiceLocator.getVentasPagosSessionService().findVentasPagosByVentastrackId(idTrackVentas).iterator();					
					System.out.println("IDtrack ventas>"+idTrackVentas);
					VentasPagosIf ventasPagosIf = null;
					BigDecimal efe = BigDecimal.ZERO;
					BigDecimal gc = BigDecimal.ZERO;
					BigDecimal tc = BigDecimal.ZERO;
					BigDecimal td = BigDecimal.ZERO;
					BigDecimal che = BigDecimal.ZERO;
					BigDecimal donaci = BigDecimal.ZERO;
					getPnResumenPagos().setVisible(true);
					while (pagosVentaIt.hasNext()) {
						ventasPagosIf = (VentasPagosIf) pagosVentaIt.next();//						
						if (ventasPagosIf.getTipo().equals(buscarTipoPagoByCodigo("EF").getId()))	efe = ventasPagosIf.getValor();
						if (ventasPagosIf.getTipo().equals(buscarTipoPagoByCodigo("CH").getId()))	che = che.add(ventasPagosIf.getValor());
						if (ventasPagosIf.getTipo().equals(buscarTipoPagoByCodigo("TA").getId())) 	tc = tc.add(ventasPagosIf.getValor());
						if (ventasPagosIf.getTipo().equals(buscarTipoPagoByCodigo("GC").getId()))	gc = gc.add(ventasPagosIf.getValor());
						if (ventasPagosIf.getTipo().equals(buscarTipoPagoByCodigo("DB").getId()))	td = td.add(ventasPagosIf.getValor());
						if (ventasPagosIf.getTipo().equals(buscarTipoPagoByCodigo("DO").getId()))	donaci = donaci.add(ventasPagosIf.getValor());
					}

					getTxtEfectivo().setText(efe.toString());
					getTxtCheque().setText(che.toString());
					getTxtTarjetaCredito().setText(tc.toString());
					getTxtGiftcard().setText(gc.toString());
					getTxtDebitoBancario().setText(td.toString());
					getTxtDonacion().setText(donaci.toString());

					BigDecimal tot = new BigDecimal("0");
					BigDecimal subt = new BigDecimal("0");
					BigDecimal desc = new BigDecimal("0");
					BigDecimal iva = new BigDecimal("0");

					// BUSCO TODOS LOS DOCUMENTOS QUE TENGAN ESE ID TRACK VENTAS
					documentosVentaIt = SessionServiceLocator.getVentasDocumentosSessionService().findVentasDocumentosByVentastrackId(idTrackVentas).iterator();
					while (documentosVentaIt.hasNext()) {
						ventasDocumentosIf = (VentasDocumentosIf) documentosVentaIt.next();
						if (ventasDocumentosIf.getTablaNombre().equals("PERSWEB")) {
							// CARGAR DETALLES DE LA PERSONALIZACION no olvidar sumar los subtotales, totales y descuento, de la parte inferior.
						}
						if (!donde.equals("devolucion")) {
							if (ventasDocumentosIf.getTablaNombre().equals("PRODUCTO")) {// AQUI ESTA EL PRODUCTO GIFT CARD COMPRADO puede
								// haber varios BUSCA POR EL ID EN LA TABLA PRODUCTO								
								Vector<String> fila = new Vector<String>();
								productoIf = (ProductoIf) productosMap.get(ventasDocumentosIf.getTablaId());
								GenericoIf generico = (GenericoIf) genericosMap.get(productoIf.getGenericoId());								
								Map aMap4 = new HashMap();
								aMap4.clear();
								aMap4.put("listaprecioId", findListaPrecioByCodigo("OFI").getId());
								aMap4.put("productoId", productoIf.getId());
								Iterator cajavalorIt4 = SessionServiceLocator.getPrecioSessionService()
								.findPrecioByQuery(aMap4).iterator();
								BigDecimal precioGiftProducto = BigDecimal.ZERO;
								if (cajavalorIt4.hasNext()) {
									PrecioIf precioif = (PrecioIf) cajavalorIt4.next();
									precioGiftProducto = precioif.getPrecio();
								}
								fila.add(productoIf.getCodigo());
								fila.add(generico.getNombre());
								fila.add("1");
								fila.add(precioGiftProducto.toString());
								fila.add(precioGiftProducto.toString());
								fila.add("0.00");
								fila.add("0.00");
								fila.add(precioGiftProducto.toString());
								fila.add(generico.getTipoproductoId().toString());
								tableModel.addRow(fila);
								tot = tot.add(precioGiftProducto);
								subt = subt.add(precioGiftProducto);// el subototal del gift es 1*precio=precio
							}
						}


						if (ventasDocumentosIf.getTablaNombre().equals("FACTURA")) {
							facturaIt = null;
							SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo("FAC").iterator().next();
							HashMap<String, Object> mapa2 = new HashMap<String, Object>();
							mapa2.clear();
							mapa2.put("id", ventasDocumentosIf.getTablaId());
							facturaIt = SessionServiceLocator.getFacturaSessionService().findFacturaByQuery(mapa2).iterator();
							if (facturaIt.hasNext()) {								
								FacturaIf cabeceraFactura = null;
								cabeceraFactura = ((FacturaIf) facturaIt.next());
								Long clienteOficinaId = cabeceraFactura.getClienteoficinaId();

								System.out.println("!!!!!!!!!!!!!!!----"+clienteOficinaId);
								setearClienteporOficinaId(clienteOficinaId);

								Iterator facturaDetalleIt=null;
								Long idCabeceraFactura = 0L;
								idCabeceraFactura = cabeceraFactura.getId();
								facturaDetalleIt = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(idCabeceraFactura).iterator();
								boolean tienedetalles = false;

								while (facturaDetalleIt.hasNext()) {
									FacturaDetalleIf facturaDetalle = (FacturaDetalleIf) facturaDetalleIt.next();
									FacturaDetalleData subData_FAC = new FacturaDetalleData();

									subData_FAC.setDocumentoId(facturaDetalle.getDocumentoId());									
									subData_FAC.setProductoId(facturaDetalle.getProductoId());
									subData_FAC.setCantidad(facturaDetalle.getCantidad());
									subData_FAC.setCantidadDevuelta(facturaDetalle.getCantidad());
									subData_FAC.setLoteId(facturaDetalle.getLoteId());								 
									Iterator facturaIt_dev;
									facturaIt_dev = SessionServiceLocator.getFacturaSessionService().findFacturaByFacturaaplId(idCabeceraFactura).iterator();
									BigDecimal cant_original = facturaDetalle.getCantidad();
									BigDecimal cant_dev = new BigDecimal("0");
									/**/									
									BigDecimal porcen= facturaDetalle.getDescuento().multiply(new BigDecimal(100));

									/*
				                    BigDecimal precioMasDecimales=new BigDecimal(formatoDecimal.format(facturaDetalle.getPrecio()).toString());	                     	                     
				                    precioMasDecimales =new BigDecimal(Utilitarios.redondeoValor(precioMasDecimales.doubleValue() * 1.12));	                     	                     
				                    precioMasDecimales =new BigDecimal(precioMasDecimales.doubleValue() / 1.12);
				                    precioMasDecimales =new BigDecimal(new Double(redondeoValor4Decimales(precioMasDecimales.doubleValue())).toString());*/
									//precioMasDecimales =new BigDecimal(redondeoValor4Decimales(precioMasDecimales.doubleValue()));
									//facturaDetalle.setPrecio(precioMasDecimales);

									BigDecimal precioCanori= cant_original.multiply(facturaDetalle.getPrecio());
									Double pp=0.00;
									BigDecimal descuentoPorcentaje=BigDecimal.ZERO;									
									if(porcen.doubleValue() != 0D)
									{
										pp=(porcen.doubleValue())/precioCanori.doubleValue();										
										descuentoPorcentaje= new BigDecimal(pp);
										descuentoPorcentaje=descuentoPorcentaje.setScale(2, BigDecimal.ROUND_HALF_UP);									
									}
									/**/
									boolean devuelto = false;
									while (facturaIt_dev.hasNext()) {
										Long fact_devId = 0L;
										devuelto = true;
										FacturaIf factura_dev = (FacturaIf) facturaIt_dev.next();
										fact_devId = factura_dev.getId();										
										Iterator facturaDet_dev = 
											SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaIdAndByProductoId(fact_devId,facturaDetalle.getProductoId()).iterator();
										if (facturaDet_dev.hasNext()) {
											while (facturaDet_dev.hasNext()) {
												FacturaDetalleIf facturaDetalle_dev = (FacturaDetalleIf) facturaDet_dev.next();												
												cant_dev = cant_dev.add(facturaDetalle_dev.getCantidadDevuelta());				
											}
										}
									}

									boolean agregar = true;

									if (devuelto) {
										subData_FAC.setCantidad(cant_original.subtract(cant_dev));
										subData_FAC.setCantidadDevuelta(cant_original.subtract(cant_dev));

										if(descuentoPorcentaje.doubleValue() != 0D){

											/*BigDecimal precioMasDecimales2=new BigDecimal(formatoDecimal.format(facturaDetalle.getPrecio()).toString());	                     	                     
						                    precioMasDecimales2 =new BigDecimal(Utilitarios.redondeoValor(precioMasDecimales2.doubleValue() * 1.12));	                     	                     
						                    precioMasDecimales2 =new BigDecimal(precioMasDecimales2.doubleValue() / 1.12);
						                    //precioMasDecimales2 =new BigDecimal(redondeoValor4Decimales(precioMasDecimales2.doubleValue()));
						                    precioMasDecimales2 =new BigDecimal(new Double(redondeoValor4Decimales(precioMasDecimales2.doubleValue())).toString());
						                    facturaDetalle.setPrecio(precioMasDecimales2);
											 */


											BigDecimal canVal=subData_FAC.getCantidad().multiply(facturaDetalle.getPrecio());											
											BigDecimal descuvalorNew = canVal.multiply(descuentoPorcentaje);
											descuvalorNew = descuvalorNew.divide(new BigDecimal(100));
											//descuvalorNew = new BigDecimal(Utilitarios.redondeoValor(descuvalorNew.doubleValue()));
											descuvalorNew = new BigDecimal(descuvalorNew.doubleValue());
											facturaDetalle.setDescuento(descuvalorNew);// en valor, no en %
											getTxtDescuento().setText(descuvalorNew.toString());
										}
										else{
											getTxtDescuento().setText("0.00");
										}

										BigDecimal diferencia = cant_original.subtract(cant_dev);
										if (diferencia.doubleValue() == 0D)
											agregar = false;
									}

									if (agregar) {
										subData_FAC.setDescripcion(facturaDetalle.getDescripcion());
										subData_FAC.setPrecio(facturaDetalle.getPrecio());
										subData_FAC.setPrecioReal(facturaDetalle.getPrecioReal());
										BigDecimal val_cant = subData_FAC.getCantidad().multiply(subData_FAC.getPrecio());
										BigDecimal iva1 = new BigDecimal("0");
										boolean ivabool = tieneIVA(facturaDetalle.getProductoId());
										if (ivabool) {
											iva1 = (val_cant.subtract(facturaDetalle.getDescuento())).multiply(BigDecimal.valueOf(IVA));
											//iva1 = new BigDecimal(Utilitarios.redondeoValor(iva1.doubleValue()));
											getTxtIVA().setText(formatoDecimal.format(iva1));
										} else {
											getTxtIVA().setText("0.00");
											iva1 = new BigDecimal("0");
										}
										subData_FAC.setIva(iva1);
										subData_FAC.setIce(facturaDetalle.getIce());
										subData_FAC.setOtroImpuesto(facturaDetalle.getOtroImpuesto());
										subData_FAC.setDescuento(facturaDetalle.getDescuento());
										subData_FAC.setDescuentoGlobal(facturaDetalle.getDescuentoGlobal());
										//subData_FAC.setCompraId(facturaDetalle.getCompraId());
										subData_FAC.setLoteId(facturaDetalle.getLoteId());

										String preciostr="";																	
										//preciostr=new Double(Utilitarios.redondeoValor(facturaDetalle.getPrecio().doubleValue())).toString();										
										BigDecimal CANvALOR = (subData_FAC.getCantidad()).multiply(facturaDetalle.getPrecio());

										//BigDecimal CANvALOR = (subData_FAC.getCantidad()).multiply(facturaDetalle.getPrecio());
										subData_FAC.setValor(CANvALOR);
										subt = subt.add(CANvALOR);
										desc = desc.add(facturaDetalle.getDescuento());
										iva = iva.add(subData_FAC.getIva());
										BigDecimal suma = (CANvALOR.add(subData_FAC.getIva())).subtract(facturaDetalle.getDescuento());
										tot = tot.add(suma);

										if(facturaDetalle.getPrecio()!=null && facturaDetalle.getPrecio().doubleValue() != 0D)

											ProductosidReunionColeccion_DEVOLUCIONES.add(subData_FAC);

										Vector<String> fila = new Vector<String>();
										productoIf = SessionServiceLocator.getProductoSessionService().getProducto(facturaDetalle.getProductoId());
										GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
										fila.add(productoIf.getCodigo());
										fila.add(facturaDetalle.getDescripcion());// (generico.getNombre());
										fila.add(subData_FAC.getCantidad().toString());
										fila.add(subData_FAC.getCantidadDevuelta().toString());
										fila.add(subData_FAC.getPrecio().toString());
										fila.add(subData_FAC.getDescuento().toString());
										fila.add(subData_FAC.getIva().toString());
										fila.add(formatoDecimal.format(suma));
										fila.add(generico.getTipoproductoId().toString());

										if(subData_FAC.getPrecio()!=null && subData_FAC.getPrecio().doubleValue() != 0D)
											tableModel.addRow(fila);

										tienedetalles = true;
									} else {
										// YA NO PUEDE AGREGARSE ESE DETALLE
									}

								}

								if (!tienedetalles)
									SpiritAlert
									.createAlert(
											"Todos los detalles de esta factura han sido devueltos.",
											SpiritAlert.INFORMATION);

							} else {
								SpiritAlert
								.createAlert(
										"No existe esta Factura ó N/V revise el número.",
										SpiritAlert.INFORMATION);
							}
						}


						System.out.println("---<"+subt);
						System.out.println("---<"+subt);

						getTxtSubtotalFinal().setText(
								formatoDecimal.format(Utilitarios
										.redondeoValor(subt.doubleValue())));
						getTxtDescuentoFinal().setText(
								formatoDecimal.format(Utilitarios
										.redondeoValor(desc.doubleValue())));
						getTxtImpuestosFinal().setText(
								formatoDecimal.format(Utilitarios
										.redondeoValor(iva.doubleValue())));
						getTxtTotalFinal().setText(
								formatoDecimal.format(Utilitarios
										.redondeoValor(tot.doubleValue())));
					}

				} else {

					clean();
					if(!caducada)
						SpiritAlert.createAlert(
								"No existe esta Factura ó N/V revise el número.",
								SpiritAlert.INFORMATION);
				}
			}

		} catch (GenericBusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return find;
	}	

	public boolean tieneIVA(Long productoId) {
		boolean iva = false;
		ProductoIf producto = (ProductoIf) productosMap.get(productoId);
		if (producto!=null) {
			GenericoIf generico = (GenericoIf) genericosMap.get(producto.getGenericoId());
			getTxtIVA().setText(generico.getCobraIva());
			String si_no_IVA = getTxtIVA().getText();
			if (si_no_IVA == null)
				si_no_IVA = "N";
			if (si_no_IVA.equals("S"))
				iva = true;
		}
		return iva;
	}

	KeyListener oKeyAdapterListadoDevoluciones = new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				String preimpreso = getTxtNumDocumento().getText();
				llenar_datos_DOCUMENTO("FAC", preimpreso, "devolucion");
			}
			if (e.getKeyChar() == KeyEvent.VK_TAB) {
				getTblVentaDetalle().grabFocus();
			}
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {

				String numDocumento = getTxtNumDocumento().getText();
				llenar_datos_DOCUMENTO("FAC", numDocumento, "devolucion");
			}
			if (e.getKeyChar() == KeyEvent.VK_TAB) {
				getTblVentaDetalle().grabFocus();
			}
		}

	};

	WindowListener wl = new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent e) {
			jdPagoEfectivo = null;
			System.gc();
		}
	};

	private ListaPrecioIf findListaPrecioByCodigo(String codigo) {
		ListaPrecioIf listaPrecio = (ListaPrecioIf) listasPreciosMap.get(codigo);
		return listaPrecio;
	}

	public void findPedido() {
		Long idCorporacion = 0L;
		pedidoCriteria = new PedidoCriteria("Ventas Realizadas", idCorporacion, "");
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.add(80);
		anchoColumnas.add(80);
		anchoColumnas.add(150);
		anchoColumnas.add(150);
		anchoColumnas.add(80);
		anchoColumnas.add(80);
		anchoColumnas.add(80);
		anchoColumnas.add(80);
		anchoColumnas.add(80);

		popupFinderPedido = new JDPopupFinderModel(Parametros.getMainFrame(), pedidoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
		if (popupFinderPedido.getElementoSeleccionado() != null) {
			pedido = (PedidoIf) popupFinderPedido.getElementoSeleccionado();
			// //setear valores de cliente////////////////////////////
			setearClienteporOficinaId(pedido.getClienteoficinaId());
			// setear el vendedor
			if (pedido.getVendedorId() != null)
				getCmbVendedor().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbVendedor(), pedido.getVendedorId()));
			if (pedido.getEstado().equals("C")) {
				BloquearPantalla();
				clean_factura();
				getBtnBuscarProducto().setVisible(false);
				tableModel = (DefaultTableModel) getTblVentaDetalle().getModel();
				for(int i= this.getTblVentaDetalle().getRowCount();i>0;--i)
					tableModel.removeRow(i-1);
				tableModel = (DefaultTableModel) getTblVentaDetalle().getModel();
				try {
					Iterator facturaIt = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId()).iterator();
					FacturaIf facturaIf = null;
					if (facturaIt.hasNext()) {
						{
							facturaIf = (FacturaIf) facturaIt.next();
							String fecha_docu = facturaIf.getFechaFactura().toString();
							TipoDocumentoIf tipoDocumento = findTipoDocumentoById(facturaIf.getTipodocumentoId());
							getCmbTipoDocDevolucion().setEditable(false);
							getCmbTipoDocDevolucion().setEnabled(false);
							if(tipoDocumento.getCodigo().equals("FAC"))
								getCmbTipoDocDevolucion().setSelectedIndex(0);
							if(tipoDocumento.getCodigo().equals("VTA"))
								getCmbTipoDocDevolucion().setSelectedIndex(1);
							getPnNumeroDocumento().setVisible(true);
							getTxtNumDocumento().setVisible(true);
							int posic = fecha_docu.indexOf(" ");
							getLblPreImpreso().setText("");
							getTxtNumDocumento().addKeyListener(new TextChecker(40));
							getTxtNumDocumento().setText("FECHA:" + fecha_docu.substring(0, 10)+ " #:"+ facturaIf.getPreimpresoNumero());
							llenar_datos_DOCUMENTO("FAC", facturaIf.getPreimpresoNumero().toString(),"historialventas");
						}
					}
				} catch (GenericBusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// modificar los totales
			if (pedido.getEstado().equals("P") || pedido.getEstado().equals("T") || pedido.getEstado().equals("A"))// COTIZACION SI SE PUEDE modificar LOS DATOS
			{
				DESBloquearPantalla();
				getLblPreImpreso().setText("PreImpreso:");
				getPnNumeroDocumento().setVisible(false);
				getTxtNumDocumento().setText("");
				getTxtNumDocumento().setVisible(false);
				try {
					tableModel = (DefaultTableModel) getTblVentaDetalle().getModel();
					for(int i= this.getTblVentaDetalle().getRowCount();i>0;--i)	tableModel.removeRow(i-1);
					BigDecimal subt = new BigDecimal("0");
					BigDecimal desc = new BigDecimal("0");
					BigDecimal iva = new BigDecimal("0");
					BigDecimal tot = new BigDecimal("0");
					// llena detalles////////////			
					Collection listaPlantillaDetalle = SessionServiceLocator.getPedidoDetalleSessionService().findPedidoDetalleByPedidoId(pedido.getId());
					Iterator it = listaPlantillaDetalle.iterator();
					while (it.hasNext()) {
						PedidoDetalleIf pedidoDetalleIf = (PedidoDetalleIf) it.next();
						BigDecimal CANvALOR = (pedidoDetalleIf.getCantidad()).multiply(pedidoDetalleIf.getPrecio());
						subt = subt.add(CANvALOR);
						desc = desc.add(pedidoDetalleIf.getDescuento());
						iva = iva.add(pedidoDetalleIf.getIva());
						BigDecimal suma = (CANvALOR.add(pedidoDetalleIf.getIva())).subtract(pedidoDetalleIf.getDescuento());
						tot = tot.add(suma);
						ProductosidReunionColeccion.add(pedidoDetalleIf);
						ProductoIf producto = (ProductoIf) productosMap.get(pedidoDetalleIf.getProductoId());
						Vector<String> fila = new Vector<String>();
						fila.add(producto.getCodigo());
						fila.add(pedidoDetalleIf.getDescripcion());
						fila.add(pedidoDetalleIf.getCantidad().toString());
						fila.add(pedidoDetalleIf.getCantidad().toString());
						fila.add(pedidoDetalleIf.getPrecio().toString());
						fila.add(pedidoDetalleIf.getDescuento().toString());
						fila.add(pedidoDetalleIf.getIva().toString());
						BigDecimal subtotal = pedidoDetalleIf.getPrecio().multiply(pedidoDetalleIf.getCantidad());
						subtotal = subtotal.add(pedidoDetalleIf.getIva());
						subtotal = subtotal.subtract(pedidoDetalleIf.getDescuento());
						fila.add(subtotal.toString());
						GenericoIf generico = (GenericoIf) genericosMap.get(producto.getGenericoId());
						fila.add(generico.getTipoproductoId().toString());
						tableModel.addRow(fila);
					}

					getTxtSubtotalFinal().setText(formatoDecimal.format(subt));
					getTxtDescuentoFinal().setText(formatoDecimal.format(desc));
					getTxtImpuestosFinal().setText(formatoDecimal.format(iva));
					getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(tot.doubleValue())));
					getTxtCheque().setText("0.00");
					getTxtEfectivo().setText("0.00");
					getTxtGiftcard().setText("0.00");
					getTxtTarjetaCredito().setText("0.00");
					getTxtDebitoBancario().setText("0.00");
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		}
	}

	// ////////////////ESTABLECIDAS POR DEFAULT: clean, refresh, delete, save,
	public void refresh() {
		// TODO Auto-generated method stub
		Caja_abierta_id("A");
		getTblVentaDetalle().grabFocus();

		if (getLblTipoDocumentoSeleccionado().getText().equals("devolucion")) {
			activarBotonesDevolucion(true);
		}
	}

	// ////////////////////////////////////////TECLAS FUNCIONALES
	// panel de tipo de transacciones
	public void t_facturar() {
		setearDocumento("facturar");
	}

	public void t_anticipo() {
		setearDocumento("anticipo");
		ReciboCajaModel jdReciboCaja = new ReciboCajaModel(Parametros.getMainFrame());
		getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(jdReciboCaja.getTotalReciboCaja())));
		referenciaReciboCaja = jdReciboCaja.getReferencia();
		
		if (jdReciboCaja.getTotalReciboCaja() == 0D) {
			desmarcarCuadroBoton(getBtnAnticipo());
			getLblTipoDocumentoSeleccionado().setText("");
		}
	}

	public void t_nventa() {
		setearDocumento("notaventa");
	}

	public void t_devolucion() {
		setearDocumento("devolucion");
	}

	// prsionamos F4
	public void pingresardatos() {
		if (getTxtCodigoBarras().isEnabled())
			getTxtCodigoBarras().grabFocus();

	}

	// prsionamos F6
	public void cobroEfectivo() {
		if (getBtnEfectivo().isEnabled())
			cobroEfectivoDialog();

	}

	// prsionamos F7
	public void cobroGiftCard() {
		if (getBtnGiftcard().isEnabled())
			giftCardDialog();
	}

	// prsionamos F8
	public void cobroTarjetaCredito() {
		if (getBtnTarjetaCredito().isEnabled())
			tarjetaCreditoDialog();
	}

	// prsionamos F21
	public void cobroTarjetaDebito() {

	}

	// prsionamos F22
	public void cobroCredito() {
		if (getBtnCredito().isEnabled())
			cobroCreditoDialog();
	}

	// prsionamos F20
	public void cobroCheque() {
		if (getBtnCheque().isEnabled())
			cobroChequeDialog();
	}

	public void graficar_ventas() {

	}

	// prsionamos F22
	public void cobroAfiliacionReferido() {
		if (getBtnAfiliacionReferido().isEnabled())
			cobroAfiliacionReferidoDialog();
	}

	// ///////////////////

	// prsionamos F3
	public void borrarItem() {
		// BORRA TODA LA FILA
		if (getBtnEliminar().isEnabled())
			eliminarDetallePedido("N", "");

	}

	// presionamos F9
	public void historialVentas() {
		if (getBtnHistorial().isEnabled())
			findPedido();
	}

	// presionamos F10
	public void donacionElegir() {
		if (getBtnDonacion().isEnabled())
			donacion_Dialog();
	}

	public void transferirTarjetaAfiliacion() {
		try {
			String si = "Sí";
			String no = "No";
			Object[] options = { si, no };
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea transferir T/A desde bodega virtual?", "Confirmación",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				SessionServiceLocator.getMovimientoSessionService().procesarTransferenciaTarjetaAfiliacionBodegaVirtual((OficinaIf)Parametros.getOficina(), (UsuarioIf) Parametros.getUsuarioIf());
				SpiritAlert.createAlert("Transferencia realizada con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}

	// presionamos F13
	public void cancelarVenta() {
		if (getBtnCancelar().isEnabled())
			resumen_Dialog("C");
	}

	public void creditoCliente() {
		if (getBtnAcreditar().isEnabled())
			aplicarCreditoCliente_devolucion();
	}

	public void abrirCajon() {

		try {
			new Cajon("CashDrawer1").abrirCajaExpress();
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
	}

	public void bloquearCaja() {
		Map aMap = new HashMap();
		aMap.clear();
		Long usuario_id = ((UsuarioIf) Parametros.getUsuarioIf()).getId();
		aMap.put("usuarioId", usuario_id);
		aMap.put("estado", "A");

		try {
			Iterator cajavalorIt = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();
			if (cajavalorIt.hasNext()) {

				//CajaSesionIf valor_actual = (CajaSesionIf) cajavalorIt.next();
				CajaSesionData valor_actual = (CajaSesionData) cajavalorIt.next();
				valor_actual.setEstado("B");
				SessionServiceLocator.getCajaSesionSessionService().saveCajaSesion(valor_actual);
				SpiritAlert.createAlert("Caja BLOQUEADA con EXITO!!", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se produjo un error al bloquear la caja", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		// TODO Auto-generated method stub

	}

	public void save() {
		// TODO Auto-generated method stub

	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public void duplicate() {
		// TODO Auto-generated method stub

	}

	public void find() {
		// TODO Auto-generated method stub

	}

	public void aplicarCreditoCliente_devolucion() {
		if (clienteIf != null) {
			//AFILIACION
			Double sumaPuntosDineroPerdido = 0D;
			String tipoRP = "N";
			String tipoTarjeta = obtenerTipoTarjeta("", getTxtCedula().getText());
			if(tipoTarjeta!=null && !tipoTarjeta.equals("")){
				if (ProductosidReunionColeccion_DEVOLUCIONES.size() != 0) {
					for (int l = 0; l < ProductosidReunionColeccion_DEVOLUCIONES.size(); l++) {
						//FacturaDetalleIf temporal = (FacturaDetalleIf) ProductosidReunionColeccion_DEVOLUCIONES.get(l);
						String tipo_producto = (String) getTblVentaDetalle().getModel().getValueAt(l, 8);	
						TipoProductoIf tipoProducto = (TipoProductoIf) tiposProductoByIdMap.get(Long.parseLong(tipo_producto));
						if (!tipoProducto.getCodigo().equals("GC")) {
							//AFILIACION: sacando el valor de los puntos ganados dependiendo si es referido o propietario
							if (acumularPuntosDinero.getValor().equals("P")) {
								BigDecimal cantidad = new BigDecimal((String)tableModel.getValueAt(l,2));								
								BigDecimal puntos = valorTipoProductoTipoTarjeta(tipoRP,acumularPuntosDinero.getValor(),acumulacionTipoProductoTipoTarjeta.getValor(),tipo_producto,tipoTarjeta);							
								puntos = cantidad.multiply(puntos);
								sumaPuntosDineroPerdido += puntos.doubleValue();						
							} else if (acumularPuntosDinero.getValor().equals("D")) {
								BigDecimal valor = new BigDecimal((String)tableModel.getValueAt(l,7));								
								BigDecimal dinero = valorTipoProductoTipoTarjeta(tipoRP,acumularPuntosDinero.getValor(),acumulacionTipoProductoTipoTarjeta.getValor(),tipo_producto,tipoTarjeta);							
								dinero = valor.multiply(dinero).divide(new BigDecimal(100D));
								sumaPuntosDineroPerdido += dinero.doubleValue();
							}
						}
					}
				}	
				TarjetasCollection = new Vector<String>();
				TarjetasCollection.add(0,"Referido/Propietario");
				TarjetasCollection.add(1,getTxtCedula().getText());
				TarjetasCollection.add(2,getTxtCedula().getText());//identificacion del dueño de la tarjeta
				TarjetasCollection.add(3,"N");
				TarjetasCollection.add(4,"");// 
				TarjetasCollection.add(5,sumaPuntosDineroPerdido.toString());							
				TarjetasCollection.add(6,"0.0");//actualizar puntos ganados
				TarjetasCollection.add(7,"");//actualiza a facturaId
				TarjetasCollection.add(8,tipoTarjeta);//tarjetaTIPO: ta-gold , ta-platinium
				TarjetasCollection_detalles.add(0,TarjetasCollection);
			}

			String datos_cliente = " " + clienteIf.getIdentificacion() + "/"+ clienteIf.getNombreLegal();

			if(clienteIf.getIdentificacion().equals("9999999999"))
			{
				SpiritAlert.createAlert("No puede aplicar crédito si es Consumidor Final. Ingrese datos del Cliente",SpiritAlert.INFORMATION);

				getBtnBuscar().setEnabled(true);
				getBtnActualizarCliente().setEnabled(true);
				getTxtCedula().setEnabled(true);
				clean_cliente();
				getTxtCedula().grabFocus();


			}
			else{
				if (!getTxtTotalFinal().getText().equals("0.00")) {
					jdCredClienteDev = new CreditoClienteDevModel(Parametros
							.getMainFrame(), getTxtTotalFinal().getText(),
							datos_cliente);
					jdCredClienteDev.addWindowListener(wl);
					int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
					int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
					jdCredClienteDev.setLocation(x, y);
					jdCredClienteDev.pack();
					jdCredClienteDev.setModal(true);
					jdCredClienteDev.setVisible(true);
					if (jdCredClienteDev.isAplicarcreditodev()) {

						//subTotal = new Double(Utilitarios.removeDecimalFormat(getTxtTotalFinal().getText()));						
						ivaTotal = new Double(Utilitarios.removeDecimalFormat(getTxtImpuestosFinal().getText()));
						subTotal = new Double(Utilitarios.removeDecimalFormat(getTxtSubtotalFinal().getText()));
						descuentoTotal = new Double(Utilitarios.removeDecimalFormat(getTxtDescuentoFinal().getText()));
						try {
							UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
							VentasTrackIf ventasTrack = registrarVentasTrack();

							System.out.println(empleadoCajero+" "+clienteOficinaIf+" "+idFactura+"poniendo el subtotal fuera!"+subTotal);

							Long idDevolucion = SessionServiceLocator.getFacturaSessionService().saveDevolucion(
									idFactura,
									empleadoCajero,
									clienteOficinaIf,
									clienteIf,
									puntoImpresionIf,
									ProductosidReunionColeccion_DEVOLUCIONES,
									TarjetasCollection_detalles,
									ivaTotal, subTotal, descuentoTotal,
									descuentoGlobalTotal, ventasTrack,
									Parametros.getIdEmpresa(),
									Parametros.getIdOficina(), usuario,null,acumularPuntosDinero.getValor(), acumulacionTipoProductoTipoTarjeta.getValor(), false);

							SpiritAlert.createAlert(
									"Devolución realizada con éxito",
									SpiritAlert.INFORMATION);
							FacturaIf devolucion = SessionServiceLocator.getFacturaSessionService().getFactura(idDevolucion);
							reporteNotaCreditoDevolucion(devolucion);
							IngresarPreimpresoModel jdIngresarPreimpreso = new IngresarPreimpresoModel(
									Parametros.getMainFrame(), devolucion);
							clean();
							clean_cliente();
							clean_factura();
							clean_producto();
							// TODO: setear en alguna Coleccion los datos para una
							// vez q mande a procesar guarde todo (pedido, factura,
							// asietnos, moviemnto pago_pos
						} catch (GenericBusinessException e) {
							e.printStackTrace();
							SpiritAlert.createAlert(
									"No se ha podido efectuar la devolución",
									SpiritAlert.ERROR);
						}
					} else {
						SpiritAlert.createAlert("Crédito NO aplicado al Cliente",
								SpiritAlert.INFORMATION);
					}
				} else
					SpiritAlert.createAlert("No se puede aplicar un credito de Cero.",SpiritAlert.INFORMATION);
			}
		} else
			SpiritAlert.createAlert("Debe tener datos del CLIENTE al cual se le aplicará el crédito.",SpiritAlert.INFORMATION);
	}


	private void reporteNotaCreditoDevolucion(FacturaIf notaCredito) {
		Long idNotaCredito = notaCredito.getId();
		try {
			BigDecimal subtotalReporte = new BigDecimal(0);
			BigDecimal ivaReporte = new BigDecimal(0);
			if (notaCredito != null) {
				TipoDocumentoIf tipoDocumento = findTipoDocumentoById(notaCredito.getTipodocumentoId());
				Collection facturaDetalle = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(idNotaCredito);
				if (facturaDetalle.size() > 0) {
					Long idParametro = notaCredito.getId();
					Collection dataSourceCollection = initializeBeanCollection(idParametro, false);
					Map proveedoresMap = getProveedoresMap(notaCredito);
					Map productosMap = getProductosMap(notaCredito);
					JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(dataSourceCollection);
					String fileName = "jaspers/cartera/RPNotaCredito.jasper";
					dataSourceCollection = initializeBeanCollection(idParametro, true);
					HashMap parametrosMap = new HashMap();

					if (Parametros.getRutaCarpetaReportes() != null && !"".equals(Parametros.getRutaCarpetaReportes()))
						parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes() + "/" + "jaspers/cartera/RPNotaCreditoDetalle.jasper");
					else
						throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");

					parametrosMap.put("reembolso", "N");
					parametrosMap.put("reembolsoGasto", "");
					parametrosMap.put("dataSourceDetail", dataSourceDetail);
					parametrosMap.put("proveedoresMap", proveedoresMap);
					parametrosMap.put("productosMap", productosMap);
					parametrosMap.put("numeroFactura", "");
					String fecha = notaCredito.getFechaFactura().toString();
					String year = fecha.substring(0, 4);
					String month = fecha.substring(5, 7);
					String day = fecha.substring(8, 10);
					OficinaIf oficina = (OficinaIf) oficinasMap.get(notaCredito.getOficinaId());
					CiudadIf ciudad = (CiudadIf) ciudadesMap.get(oficina.getCiudadId());
					String nombreCiudad = ciudad.getNombre();
					String fechaNotaCredito = nombreCiudad + ", " + day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + year;
					parametrosMap.put("fechaCreacion", fechaNotaCredito);
					parametrosMap.put("nombreOficina", oficina.getNombre());
					parametrosMap.put("direccionOficina", (!oficina.getDireccion().equals("S/D") && !oficina.getDireccion().equals("S/N") && !oficina.getDireccion().trim().equals(""))?oficina.getDireccion():"");
					parametrosMap.put("telefonoOficina", (!oficina.getTelefono().equals("S/D") && !oficina.getTelefono().equals("S/N") && !oficina.getTelefono().trim().equals(""))?oficina.getTelefono():"");
					parametrosMap.put("razonSocialEmpresa", empresa.getNombre());
					parametrosMap.put("rucEmpresa", empresa.getRuc());
					ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(notaCredito.getClienteoficinaId());
					ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
					parametrosMap.put("razonSocialCliente", cliente.getRazonSocial());
					parametrosMap.put("descuentoTotal", notaCredito.getDescuento().add(notaCredito.getDescuentoGlobal()).doubleValue());
					parametrosMap.put("porcentajeComision", notaCredito.getPorcentajeComisionAgencia());
					parametrosMap.put("otroImpuestoTotal", notaCredito.getOtroImpuesto());
					Double valor = notaCredito.getValor().doubleValue();
					//Double descuento = Utilitarios.redondeoValor(notaCredito.getDescuento().doubleValue() + notaCredito.getDescuentoGlobal().doubleValue());
					Double descuento = notaCredito.getDescuento().doubleValue() + notaCredito.getDescuentoGlobal().doubleValue();
					Double porcentajeComision = notaCredito.getPorcentajeComisionAgencia().doubleValue();
					//Double comision = Utilitarios.redondeoValor(((valor - descuento) * porcentajeComision) / 100D);
					Double comision = ((valor - descuento) * porcentajeComision) / 100D;
					Double iva = notaCredito.getIva().doubleValue();
					Double otroImpuesto = notaCredito.getOtroImpuesto().doubleValue();
					Double grandTotal = valor + iva + otroImpuesto - descuento + comision;
					parametrosMap.put("grandTotal", Utilitarios.redondeoValor(grandTotal.doubleValue()));
					String totalNotaCredito = formatoDecimal.format(Utilitarios.redondeoValor(grandTotal));
					String parteDecimal = totalNotaCredito.substring(totalNotaCredito.indexOf('.'), totalNotaCredito.length());
					Double dParteDecimal = 0.0;
					if (!parteDecimal.isEmpty())
						dParteDecimal = Double.valueOf(parteDecimal);
					MonedaIf moneda = SessionServiceLocator.getMonedaSessionService().getMoneda(notaCredito.getMonedaId());
					String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(totalNotaCredito, dParteDecimal, new int[] { 200 }, false, moneda);
					String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ", "");
					parametrosMap.put("cantidadEnLetras", cantidadLetrasPrimeraLinea);
					parametrosMap.put("valorComision", Utilitarios.redondeoValor(comision.doubleValue()));
					String si = "Si";
					String no = "No";
					Object[] options = {si,no};
					subtotalReporte = new BigDecimal(Utilitarios.redondeoValor(notaCredito.getValor().doubleValue()));
					ivaReporte = new BigDecimal(Utilitarios.redondeoValor(notaCredito.getIva().doubleValue()));
					parametrosMap.put("valorTotal", subtotalReporte.doubleValue());
					parametrosMap.put("ivaTotal", ivaReporte.doubleValue());
					parametrosMap.put("mostrarIVA", "S");
					parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
					FacturaIf factura = SessionServiceLocator.getFacturaSessionService().getFactura(notaCredito.getFacturaaplId());
					parametrosMap.put("observacion", notaCredito.getObservacion());

					ReportModelImpl.processReport(fileName, parametrosMap, dataSourceCollection, true);


				} else {
					SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!!!", SpiritAlert.ERROR);
		}
	}

	public void report_cotizacion() {
		BigDecimal subtotalReporte = new BigDecimal(0);
		BigDecimal ivaReporte = new BigDecimal(0);
		try {
			if (ProductosidReunionColeccion_proceso.size() > 0) {
				PedidoIf pedido = (PedidoIf) SessionServiceLocator.getPedidoSessionService().findPedidoById(idPedidoGuardado).iterator().next();
				Long idParametro = pedido.getId();
				Collection dataSourceCollection = initializeBeanCollection_Cotizacion(idParametro, false);
				Map proveedoresMapCotiz = getProveedoresMapCotizacion(pedido);
				JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(dataSourceCollection);
				String fileName = "jaspers/facturacion/RPCotizacion.jasper";
				dataSourceCollection = initializeBeanCollection_Cotizacion(idParametro, true);
				HashMap parametrosMap = new HashMap();
				parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes() + "/jaspers/facturacion/RPCotizacionDetalle.jasper");
				TipoDocumentoIf tipoDocumento = findTipoDocumentoById(pedido.getTipodocumentoId());
				parametrosMap.put("reembolso", "N");
				parametrosMap.put("reembolsoGasto", "");
				parametrosMap.put("dataSourceDetail", dataSourceDetail);
				parametrosMap.put("proveedoresMap", proveedoresMapCotiz);
				Double dd_numero = new Double("48");
				parametrosMap.put("numeroFactura", formatoSerial.format(dd_numero.doubleValue()));
				String fecha = pedido.getFechaPedido().toString();
				String year = fecha.substring(0, 4);
				String month = fecha.substring(5, 7);
				String day = fecha.substring(8, 10);
				OficinaIf oficina = (OficinaIf) oficinasMap.get(pedido.getOficinaId());
				CiudadIf ciudad = (CiudadIf) ciudadesMap.get(oficina.getCiudadId());
				String nombreCiudad = ciudad.getNombre();
				String fechaFactura = nombreCiudad + ", " + Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
				parametrosMap.put("fechaCreacion", fechaFactura);
				parametrosMap.put("nombreOficina", oficina.getNombre());
				parametrosMap.put("direccionOficina", (!oficina.getDireccion().equals("S/D") && !oficina.getDireccion().equals("S/N") && !oficina.getDireccion().trim().equals(""))?oficina.getDireccion():"");
				parametrosMap.put("telefonoOficina", (!oficina.getTelefono().equals("S/D") && !oficina.getTelefono().equals("S/N") && !oficina.getTelefono().trim().equals(""))?oficina.getTelefono():"");
				parametrosMap.put("razonSocialEmpresa", empresa.getNombre());
				parametrosMap.put("rucEmpresa", empresa.getRuc());
				ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(pedido.getClienteoficinaId());
				ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
				parametrosMap.put("razonSocialCliente", cliente.getRazonSocial());
				BigDecimal cero = new BigDecimal("0");
				double descuentot = new Double(getTxtDescuentoFinal().getText());
				parametrosMap.put("descuentoTotal", cero);
				parametrosMap.put("porcentajeComision", cero);
				parametrosMap.put("otroImpuestoTotal", cero);
				Double valor = new Double(getTxtTotalFinal().getText());
				//Double descuento = Utilitarios.redondeoValor(descuentot);
				Double descuento = descuentot;
				Double porcentajeComision = descuentot;
				Double comision = descuentot;
				Double iva = new Double(Utilitarios.removeDecimalFormat(getTxtImpuestosFinal().getText()));
				Double otroImpuesto = descuentot;
				Double grandTotal = valor + iva + otroImpuesto - descuento + comision;
				parametrosMap.put("grandTotal", Utilitarios.redondeoValor(grandTotal.doubleValue()));
				String totalFactura = formatoDecimal.format(Utilitarios.redondeoValor(grandTotal));
				String parteDecimal = totalFactura.substring(totalFactura.indexOf('.'), totalFactura.length());
				Double dParteDecimal = 0.0;
				if (!parteDecimal.isEmpty())
					dParteDecimal = Double.valueOf(parteDecimal);
				MonedaIf moneda = SessionServiceLocator.getMonedaSessionService().getMoneda(pedido.getMonedaId());
				String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(totalFactura, dParteDecimal, new int[] { 200 }, false, moneda);
				String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ", "");
				parametrosMap.put("cantidadEnLetras", cantidadLetrasPrimeraLinea);
				parametrosMap.put("valorComision", Utilitarios.redondeoValor(comision.doubleValue()));
				subtotalReporte = new BigDecimal(Utilitarios.redondeoValor(valor));
				ivaReporte = new BigDecimal(Utilitarios.redondeoValor(iva));
				parametrosMap.put("valorTotal", subtotalReporte.doubleValue());
				parametrosMap.put("ivaTotal", ivaReporte.doubleValue());
				parametrosMap.put("mostrarIVA", "S");
				parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
				ReportModelImpl.processReport(fileName, parametrosMap, dataSourceCollection, true);
			} else {
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private Map getProveedoresMapCotizacion(PedidoIf pedido) {
		Map proveedoresMap = new HashMap();

		Collection rowCollection = null;
		try {
			rowCollection = SessionServiceLocator.getPedidoDetalleSessionService()
			.findPedidoDetalleByPedidoId(pedido.getId());
			Iterator itRows = rowCollection.iterator();
			TipoDocumentoIf tipoDocumentoFactura = findTipoDocumentoById(pedido.getTipodocumentoId());
			while (itRows.hasNext()) {
				PedidoDetalleIf bean = (PedidoDetalleIf) itRows.next();
				ProductoIf producto = (ProductoIf) productosMap.get(bean.getProductoId());
				ClienteOficinaIf proveedorOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(producto.getProveedorId());
				String facturaReembolso = "";
				ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
				TipoIdentificacionIf tipoIdentificacion = (TipoIdentificacionIf) tiposIdentificacionMap.get(proveedor.getTipoidentificacionId());
				proveedoresMap.put(bean.getId(), proveedor.getRazonSocial() + "\n" + tipoIdentificacion.getNombre() + ": " + proveedor.getIdentificacion() + facturaReembolso);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}

		return proveedoresMap;
	}

	private Collection initializeBeanCollection_Cotizacion(Long idParametro,
			boolean isHeader) {
		ArrayList reportRows = new ArrayList();
		Collection rowCollection = null;
		try {
			if (!isHeader)
				rowCollection = SessionServiceLocator.getPedidoDetalleSessionService()
				.findPedidoDetalleByPedidoId(idParametro);
			else
				rowCollection = SessionServiceLocator.getPedidoSessionService()
				.findPedidoById(idParametro);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator itRows = rowCollection.iterator();

		while (itRows.hasNext()) {
			if (!isHeader) {
				PedidoDetalleIf bean = (PedidoDetalleIf) itRows.next();
				reportRows.add(bean);
			} else {
				PedidoIf bean = (PedidoIf) itRows.next();
				reportRows.add(bean);
			}
		}
		return reportRows;
	}

	public void reportVenta(Long idfactura,String detallePagos) {
		try {
			BigDecimal subtotalReporte = new BigDecimal(0);
			BigDecimal ivaReporte = new BigDecimal(0);
			FacturaIf factura = SessionServiceLocator.getFacturaSessionService().getFactura(idfactura);
			if (factura != null) {
				TipoDocumentoIf tipoDocumento = findTipoDocumentoById(factura.getTipodocumentoId());
				Collection facturaDetalle = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(idfactura);
				if (facturaDetalle.size() > 0) {
					Long idParametro = factura.getId();
					Collection dataSourceCollection = initializeBeanCollection(idParametro, false);
					Map proveedoresMap = getProveedoresMap(factura);
					Map productosMap = getProductosMap(factura);
					JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(dataSourceCollection);
					//String fileName = (tipoDocumento.getCodigo().equals("VTA")) ? "jaspers/facturacion/RPNotaVenta.jasper" : "jaspers/facturacion/RPFactura.jasper";
					String fileName = (tipoDocumento.getCodigo().equals("VTA")) ? "jaspers/pos/RPNotaVenta.jasper" : "jaspers/pos/RPFactura.jasper";
					dataSourceCollection = initializeBeanCollection(idParametro, true);
					HashMap parametrosMap = new HashMap();

					if (Parametros.getRutaCarpetaReportes() != null && !"".equals(Parametros.getRutaCarpetaReportes()))
						parametrosMap.put("pathsubreport",Parametros.getRutaCarpetaReportes() + "/" + (tipoDocumento.getCodigo().equals("VTA") ? "jaspers/facturacion/RPNotaVentaDetalle.jasper" : "jaspers/facturacion/RPFacturaDetalle.jasper"));
					else
						throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");

					if (tipoDocumento.getReembolso().equals("S")) {
						parametrosMap.put("reembolso", "S");
						parametrosMap.put("reembolsoGasto", "REEMBOLSO DE GASTO");
					} else {
						parametrosMap.put("reembolso", "N");
						parametrosMap.put("reembolsoGasto", "");
					}
					parametrosMap.put("dataSourceDetail", dataSourceDetail);
					parametrosMap.put("proveedoresMap", proveedoresMap);
					parametrosMap.put("productosMap", productosMap);
					parametrosMap.put("numeroFactura", formatoSerial.format(factura.getNumero().doubleValue()));
					parametrosMap.put("detallePagos",detallePagos);
					String fecha = factura.getFechaFactura().toString();
					String year = fecha.substring(0, 4);
					String month = fecha.substring(5, 7);
					String day = fecha.substring(8, 10);
					OficinaIf oficina = (OficinaIf) oficinasMap.get(factura.getOficinaId());
					CiudadIf ciudad = (CiudadIf) ciudadesMap.get(oficina.getCiudadId());
					String nombreCiudad = ciudad.getNombre();
					String fechaFactura = nombreCiudad + ", " + day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + year;
					parametrosMap.put("fechaCreacion", fechaFactura);
					parametrosMap.put("nombreOficina", oficina.getNombre());
					parametrosMap.put("direccionOficina", (!oficina.getDireccion().equals("S/D") && !oficina.getDireccion().equals("S/N") && !oficina.getDireccion().trim().equals(""))?oficina.getDireccion():"");
					parametrosMap.put("telefonoOficina", (!oficina.getTelefono().equals("S/D") && !oficina.getTelefono().equals("S/N") && !oficina.getTelefono().trim().equals(""))?oficina.getTelefono():"");
					parametrosMap.put("razonSocialEmpresa", empresa.getNombre());
					parametrosMap.put("rucEmpresa", empresa.getRuc());
					ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(factura.getClienteoficinaId());
					ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
					parametrosMap.put("razonSocialCliente", cliente.getRazonSocial());
					parametrosMap.put("descuentoTotal", factura.getDescuento().add(factura.getDescuentoGlobal()).doubleValue());
					parametrosMap.put("porcentajeComision", factura.getPorcentajeComisionAgencia());
					parametrosMap.put("otroImpuestoTotal", factura.getOtroImpuesto());
					Double valor = factura.getValor().doubleValue();
					//Double descuento = Utilitarios.redondeoValor(factura.getDescuento().doubleValue() + factura.getDescuentoGlobal().doubleValue());
					Double descuento = factura.getDescuento().doubleValue() + factura.getDescuentoGlobal().doubleValue();
					Double porcentajeComision = factura.getPorcentajeComisionAgencia().doubleValue();
					//Double comision = Utilitarios.redondeoValor(((valor - descuento) * porcentajeComision) / 100D);
					Double comision = ((valor - descuento) * porcentajeComision) / 100D;
					Double iva = factura.getIva().doubleValue();
					Double otroImpuesto = factura.getOtroImpuesto().doubleValue();
					Double grandTotal = valor + iva + otroImpuesto - descuento + comision;
					parametrosMap.put("grandTotal", Utilitarios.redondeoValor(grandTotal.doubleValue()));
					String totalFactura = formatoDecimal.format(Utilitarios.redondeoValor(grandTotal));
					String parteDecimal = totalFactura.substring(totalFactura.indexOf('.'), totalFactura.length());
					Double dParteDecimal = 0.0;
					if (!parteDecimal.isEmpty())
						dParteDecimal = Double.valueOf(parteDecimal);
					MonedaIf moneda = SessionServiceLocator.getMonedaSessionService().getMoneda(factura.getMonedaId());
					String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(totalFactura, dParteDecimal, new int[] { 200 }, false, moneda);
					String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ", "");
					parametrosMap.put("cantidadEnLetras", cantidadLetrasPrimeraLinea);
					parametrosMap.put("valorComision", Utilitarios.redondeoValor(comision.doubleValue()));



					String si = "Si"; 
					String no = "No"; 
					Object[] options ={si,no};
					if (tipoDocumento.getReembolso().equals("S")) {
						int opcion = JOptionPane.showOptionDialog(null, "¿Desea presentar el IVA en la Factura de Reembolso?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
						if (opcion == JOptionPane.YES_OPTION) {
							subtotalReporte = new BigDecimal(Utilitarios.redondeoValor(factura.getValor().doubleValue() / 1.12));
							ivaReporte = new BigDecimal(Utilitarios.redondeoValor((factura.getValor().doubleValue() / 1.12) * 0.12));
							parametrosMap.put("valorTotal", subtotalReporte.doubleValue());
							parametrosMap.put("ivaTotal", ivaReporte.doubleValue());
							parametrosMap.put("mostrarIVA", "S");
							parametrosMap.put("porcentajeIVA",formatoEntero.format(Parametros.getIVA()));
						} else {
							subtotalReporte = new BigDecimal(Utilitarios.redondeoValor(factura.getValor().doubleValue()));
							ivaReporte = new BigDecimal(Utilitarios.redondeoValor(factura.getIva().doubleValue()));
							parametrosMap.put("valorTotal", subtotalReporte.doubleValue());
							parametrosMap.put("ivaTotal", ivaReporte.doubleValue());
							parametrosMap.put("mostrarIVA", "N");
							parametrosMap.put("porcentajeIVA",formatoEntero.format(IVA_CERO.doubleValue()));
						}
					} else {
						subtotalReporte = new BigDecimal(Utilitarios.redondeoValor(factura.getValor().doubleValue()));
						ivaReporte = new BigDecimal(Utilitarios.redondeoValor(factura.getIva().doubleValue()));
						parametrosMap.put("valorTotal", subtotalReporte.doubleValue());
						parametrosMap.put("ivaTotal", ivaReporte.doubleValue());
						parametrosMap.put("mostrarIVA", "S");
						parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
					}

					ReportModelImpl.processReport(fileName, parametrosMap, dataSourceCollection, true);
				} else {
					SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			e.printStackTrace();
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void report() {
		try {
			BigDecimal subtotalReporte = new BigDecimal(0);
			BigDecimal ivaReporte = new BigDecimal(0);
			if (FacturaDetalleTempColeccion.size() > 0) {
				String si = "Si";
				String no = "No";
				Object[] options = { si, no };
				int opcion = JOptionPane.showOptionDialog(null,
						"¿Desea generar el reporte para imprimir la Factura?",
						"Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, no);

				if (opcion == JOptionPane.YES_OPTION) {
					FacturaIf factura = (FacturaIf) SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId()).iterator().next();
					Long idParametro = factura.getId();

					Collection dataSourceCollection = initializeBeanCollection(idParametro, false);
					Map proveedoresMap = getProveedoresMap(factura);
					JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(dataSourceCollection);
					String fileName = "jaspers/facturacion/RPFactura.jasper";
					dataSourceCollection = initializeBeanCollection(idParametro, true);
					HashMap parametrosMap = new HashMap();
					parametrosMap.put("pathsubreport",
					"jaspers/facturacion/RPFacturaDetalle.jasper");
					TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) findTipoDocumentoById(factura.getTipodocumentoId());
					if (tipoDocumento.getReembolso().equals("S")) {
						parametrosMap.put("reembolso", "S");
						parametrosMap.put("reembolsoGasto", "REEMBOLSO DE GASTO");
					} else {
						parametrosMap.put("reembolso", "N");
						parametrosMap.put("reembolsoGasto", "");
					}
					parametrosMap.put("dataSourceDetail", dataSourceDetail);
					parametrosMap.put("proveedoresMap", proveedoresMap);
					parametrosMap.put("numeroFactura", formatoSerial.format(factura.getNumero().doubleValue()));
					String fecha = factura.getFechaFactura().toString();
					String year = fecha.substring(0, 4);
					String month = fecha.substring(5, 7);
					String day = fecha.substring(8, 10);
					OficinaIf oficina = (OficinaIf) oficinasMap.get(factura.getOficinaId());
					CiudadIf ciudad = (CiudadIf) ciudadesMap.get(oficina.getCiudadId());
					String nombreCiudad = ciudad.getNombre();
					String fechaFactura = nombreCiudad + ", " + Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					parametrosMap.put("fechaCreacion", fechaFactura);
					parametrosMap.put("nombreOficina", oficina.getNombre());
					parametrosMap.put("direccionOficina", (!oficina.getDireccion().equals("S/D") && !oficina.getDireccion().equals("S/N") && !oficina.getDireccion().trim().equals(""))?oficina.getDireccion():"");
					parametrosMap.put("telefonoOficina", (!oficina.getTelefono().equals("S/D") && !oficina.getTelefono().equals("S/N") && !oficina.getTelefono().trim().equals(""))?oficina.getTelefono():"");
					parametrosMap.put("razonSocialEmpresa", empresa.getNombre());
					parametrosMap.put("rucEmpresa", empresa.getRuc());
					ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(factura.getClienteoficinaId());
					ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
					parametrosMap.put("razonSocialCliente", cliente.getRazonSocial());
					parametrosMap.put("descuentoTotal", factura.getDescuento().add(factura.getDescuentoGlobal()));
					parametrosMap.put("porcentajeComision", factura.getPorcentajeComisionAgencia());
					parametrosMap.put("otroImpuestoTotal", factura.getOtroImpuesto());
					Double valor = factura.getValor().doubleValue();
					//Double descuento = Utilitarios.redondeoValor(factura.getDescuento().doubleValue() + factura.getDescuentoGlobal().doubleValue());
					Double descuento = factura.getDescuento().doubleValue() + factura.getDescuentoGlobal().doubleValue();
					Double porcentajeComision = factura.getPorcentajeComisionAgencia().doubleValue();
					//Double comision = Utilitarios.redondeoValor(((valor - descuento) * porcentajeComision) / 100D);
					Double comision = ((valor - descuento) * porcentajeComision) / 100D;
					Double iva = factura.getIva().doubleValue();
					Double otroImpuesto = factura.getOtroImpuesto().doubleValue();
					Double grandTotal = valor + iva + otroImpuesto - descuento + comision;
					parametrosMap.put("grandTotal", Utilitarios.redondeoValor(grandTotal.doubleValue()));
					String totalFactura = formatoDecimal.format(Utilitarios.redondeoValor(grandTotal));
					String parteDecimal = totalFactura.substring(totalFactura.indexOf('.'), totalFactura.length());
					Double dParteDecimal = 0.0;
					if (!parteDecimal.isEmpty())
						dParteDecimal = Double.valueOf(parteDecimal);
					MonedaIf moneda = SessionServiceLocator.getMonedaSessionService().getMoneda(factura.getMonedaId());
					String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(totalFactura, dParteDecimal, new int[] { 200 }, false, moneda);
					String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ", "");
					parametrosMap.put("cantidadEnLetras", cantidadLetrasPrimeraLinea);
					parametrosMap.put("valorComision", Utilitarios.redondeoValor(comision.doubleValue()));

					if (tipoDocumento.getReembolso().equals("S")) {
						opcion = JOptionPane.showOptionDialog(null, "¿Desea presentar el IVA en la Factura de Reembolso?", "Confirmación",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
						if (opcion == JOptionPane.YES_OPTION) {
							subtotalReporte = new BigDecimal(Utilitarios.redondeoValor(factura.getValor().doubleValue() / 1.12));
							ivaReporte = new BigDecimal(Utilitarios.redondeoValor((factura.getValor().doubleValue() / 1.12) * 0.12));
							parametrosMap.put("valorTotal", subtotalReporte.doubleValue());
							parametrosMap.put("ivaTotal", ivaReporte.doubleValue());
							parametrosMap.put("mostrarIVA", "S");
							parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
						} else {
							subtotalReporte = new BigDecimal(Utilitarios.redondeoValor(factura.getValor().doubleValue()));
							ivaReporte = new BigDecimal(Utilitarios.redondeoValor(factura.getIva().doubleValue()));
							parametrosMap.put("valorTotal", subtotalReporte.doubleValue());
							parametrosMap.put("ivaTotal", ivaReporte.doubleValue());
							parametrosMap.put("mostrarIVA", "N");
							parametrosMap.put("porcentajeIVA", formatoEntero.format(IVA_CERO.doubleValue()));
						}
					} else {
						subtotalReporte = new BigDecimal(Utilitarios.redondeoValor(factura.getValor().doubleValue()));
						ivaReporte = new BigDecimal(Utilitarios.redondeoValor(factura.getIva().doubleValue()));
						parametrosMap.put("valorTotal", subtotalReporte.doubleValue());
						parametrosMap.put("ivaTotal", ivaReporte.doubleValue());
						parametrosMap.put("mostrarIVA", "S");
						parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
					}

					ReportModelImpl.processReport(fileName, parametrosMap, dataSourceCollection, true);
				}
			} else {
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private Collection initializeBeanCollection(Long idParametro, boolean isHeader) {
		ArrayList reportRows = new ArrayList();
		Collection rowCollection = null;
		try {
			if (!isHeader)
				rowCollection = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(idParametro);
			else
				rowCollection = SessionServiceLocator.getFacturaSessionService().findFacturaById(idParametro);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator itRows = rowCollection.iterator();

		while (itRows.hasNext()) {
			if (!isHeader) {
				FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
				reportRows.add(bean);
			} else {
				FacturaIf bean = (FacturaIf) itRows.next();
				reportRows.add(bean);
			}
		}

		return reportRows;
	}

	private CompraIf compraReembolso;

	private Map getProveedoresMap(FacturaIf factura) {
		Map proveedoresMap = new HashMap();
		Collection rowCollection = null;
		try {
			rowCollection = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(factura.getId());
			Iterator itRows = rowCollection.iterator();
			TipoDocumentoIf tipoDocumentoFactura = findTipoDocumentoById(factura.getTipodocumentoId());
			while (itRows.hasNext()) {
				FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
				ProductoIf producto = (ProductoIf) productosMap.get(bean.getProductoId());
				ClienteOficinaIf proveedorOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(producto.getProveedorId());
				String facturaReembolso = "";
				ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
				TipoIdentificacionIf tipoIdentificacion = (TipoIdentificacionIf) tiposIdentificacionMap.get(proveedor.getTipoidentificacionId());
				proveedoresMap.put(bean.getId(), proveedor.getRazonSocial() + "\n" + tipoIdentificacion.getNombre() + ": " + proveedor.getIdentificacion() + facturaReembolso);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}

		return proveedoresMap;
	}

	private Map getProductosMap(FacturaIf factura) {
		Map productosMap = new HashMap();
		Collection rowCollection = null;
		try {
			rowCollection = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(factura.getId());
			Iterator itRows = rowCollection.iterator();
			while (itRows.hasNext()) {
				FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
				ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(bean.getProductoId());
				String codigo = (producto.getCodigoBarras()!=null && !producto.getCodigoBarras().trim().equals(""))?producto.getCodigoBarras():producto.getCodigo();
				productosMap.put(bean.getId(), codigo);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return productosMap;
	}

	public void addDetail() {
		// TODO Auto-generated method stub

	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showSaveMode() {
		//getBtnAnticipo().setVisible(false);
	}

	public void showFindMode() {
		// TODO Auto-generated method stub

	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub

	}

	public void updateDetail() {
		// TODO Auto-generated method stub

	}
	
	public void deleteDetail() {
		
	}

	public void setNumFactura(String numfac) {
		numfacgeneral = numfac;
	}

	public String getNumFactura() {
		return numfacgeneral;
	}

	public boolean validateFields() {
		for (int i = 0; i < ProductosidReunionColeccion.size(); i++) {
			PedidoDetalleIf pedidoDetalle = (PedidoDetalleIf) ProductosidReunionColeccion.get(i);
			if (pedidoDetalle.getDocumentoId() != null) {
				DocumentoIf documento = findDocumentoById(pedidoDetalle.getDocumentoId());
				TipoDocumentoIf tipoDocumentoIf = findTipoDocumentoByCodigo("FAC");
				if (documento.getTipoDocumentoId().compareTo(tipoDocumentoIf.getId()) != 0) {
					SpiritAlert.createAlert("Documento no corresponde con tipo de documento seleccionado !!",SpiritAlert.WARNING);
					return false;
				}
			}
		}

		return true;
	}

	public boolean validateFields_varios(String cod) {
		setearPuntoImpresion(cod);
		for (int i = 0; i < ProductosidReunionColeccion.size(); i++) {
			PedidoDetalleIf pedidoDetalle = (PedidoDetalleIf) ProductosidReunionColeccion.get(i);
			if (pedidoDetalle.getDocumentoId() != null) {
				DocumentoIf documento = findDocumentoById(pedidoDetalle.getDocumentoId());
				TipoDocumentoIf tipoDocumentoIf = findTipoDocumentoByCodigo(cod);
				if (documento.getTipoDocumentoId().compareTo(tipoDocumentoIf.getId()) != 0) {
					SpiritAlert.createAlert("Documento no corresponde con tipo de documento seleccionado !!",SpiritAlert.WARNING);
					return false;
				}
			}
		}
		return true;
	}

	// LLAMADAS A ESTOS SESSION DE CLIENTE; OFICINA; CORPORACION; TIPO PAGO; AUN POR REVISAR COMO AFECTAN AL RENDIMIENTO


}
