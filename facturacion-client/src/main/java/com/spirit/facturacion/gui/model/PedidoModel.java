package com.spirit.facturacion.gui.model;

import java.awt.Color;
import java.awt.Component;
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
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.bpm.process.gui.BpmPanel;
import com.spirit.cartera.entity.FormaPagoIf;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.gui.criteria.CompraCriteria;
import com.spirit.compras.handler.PurchaseData;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.gui.data.AutorizarAsientoData;
import com.spirit.crm.entity.ClienteContactoIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaData;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaData;
import com.spirit.facturacion.entity.FacturaDetalleData;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaDetalleIvaIncluidoData;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.ListaPrecioIf;
import com.spirit.facturacion.entity.MotivoDocumentoIf;
import com.spirit.facturacion.entity.PedidoData;
import com.spirit.facturacion.entity.PedidoDetalleData;
import com.spirit.facturacion.entity.PedidoDetalleIf;
import com.spirit.facturacion.entity.PedidoEnvioIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.entity.PrecioData;
import com.spirit.facturacion.entity.PrecioIf;
import com.spirit.facturacion.gui.controller.FacturacionFinder;
import com.spirit.facturacion.gui.criteria.PedidoCriteria;
import com.spirit.facturacion.gui.panel.JPPedido;
import com.spirit.facturacion.handler.EstadoPedido;
import com.spirit.facturacion.handler.TipoReferenciaPedido;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.LineaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.NumeradoresIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.OrigenDocumentoIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.PuntoImpresionIf;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.entity.TipoTroqueladoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.entity.UsuarioPuntoImpresionIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.general.util.Numeradores;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.ColorIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.StockIf;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.CampanaProductoVersionIf;
import com.spirit.medios.entity.ComercialIf;
import com.spirit.medios.entity.DerechoProgramaIf;
import com.spirit.medios.entity.EquipoEmpleadoIf;
import com.spirit.medios.entity.EquipoTrabajoIf;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioDetalleMapaIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.OrdenTrabajoProductoIf;
import com.spirit.medios.entity.PlanMedioDetalleIf;
import com.spirit.medios.entity.PlanMedioFacturacionData;
import com.spirit.medios.entity.PlanMedioFacturacionIf;
import com.spirit.medios.entity.PlanMedioFormaPagoData;
import com.spirit.medios.entity.PlanMedioFormaPagoIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PlanMedioMesIf;
import com.spirit.medios.entity.PresupuestoDetalleData;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoFacturacionData;
import com.spirit.medios.entity.PresupuestoFacturacionIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.PresupuestoProductoIf;
import com.spirit.medios.entity.ProductoClienteData;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.gui.criteria.PlanMedioCriteria;
import com.spirit.medios.gui.criteria.PresupuestoCriteria;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.ApplicationLauncher;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.DeepCopy;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class PedidoModel extends JPPedido implements BpmPanel {
	private static final long serialVersionUID = 8197092119009772730L;
	private   CorporacionCriteria corporacionCriteria;
	private   ClienteOficinaCriteria clienteOficinaCriteria;
	private   ClienteOficinaCriteria clienteOficinaNegociacionCriteria;
	private   ClienteCriteria clienteCriteria;
	private   DefaultTableModel modelPedidoDetalle;
	protected ClienteOficinaIf proveedorIf;
	protected CorporacionIf corporacionIf;
	protected ClienteOficinaIf clienteOficinaIf;
	protected ClienteOficinaIf clienteOficinaNegociacionIf;
	protected ClienteIf clienteIf;
	protected ClienteIf fundacion;
	protected PedidoIf pedido;
	protected FacturaIf facturaGenerada;
	protected OficinaIf oficinaIf;
	protected PedidoDetalleIf pedidoDetalle;
	protected ProductoIf productoIf;
	protected TipoDocumentoIf tipoDocumentoIf;
	protected PresupuestoIf presupuestoIf;
	protected PlanMedioIf planMedioIf;
	protected Vector<PlanMedioIf> planesMedioIf = new Vector<PlanMedioIf>();
	protected Vector<PresupuestoIf> presupuestosIf = new Vector<PresupuestoIf>();
	protected PuntoImpresionIf puntoImpresionIf;
	protected LineaIf lineaIf;
	protected TipoIdentificacionIf tipoIdentificacionIf;
	protected CajaIf cajaIf;
	private PedidoEnvioIf pedidoEnvioIf;
	private Long idPedidoGuardado = 0L;
	private TipoReferenciaPedido tipoReferencia = null;
	private int filaSeleccionada = -1;
	private Map modelosMap = new HashMap();
	private Map coloresMap = new HashMap();
	private Map presentacionesMap = new HashMap();
	private static final String REPORTE_PRESUPUESTO_SI = "S";
	private static final String REPORTE_PRESUPUESTO_NO = "N";
	private static final String ESTADO_PRESUPUESTO_FACTURACION_FACTURADO = "F";
	private static final String TIPO_PRESUPUESTO_FACTURACION_NORMAL = "N";
	private static final String TIPO_PRESUPUESTO_FACTURACION_CLIENTE = "C";
	private static final String TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA = "D";
	private static final String TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA = "P";
	private static final String TIPO_PRESUPUESTO_FACTURACION_PARCIAL = "R";
	private static final String OPCION_NO = "N";
	private static final String OPCION_SI = "S";
	private static final String CODIGO_TIPO_PRODUCTO_MEDIOS = "M";
	private static final String CODIGO_TIPO_PRODUCTO_PRODUCCION = "P";
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String CODIGO_TIPO_DOCUMENTO_FACTURA_COMISION = "FCO";
	private static final String BUSCAR_PRODUCTO_POR_REFERENCIA = "R";
	private static final int MAX_LONGITUD_DIAS_VALIDEZ = 3;
	private static final int MAX_LONGITUD_OBSERVACION  = 100;
	private static final int MAX_LONGITUD_IDENTIFICACION = 20;
	private static final int MAX_LONGITUD_CONTACTO     = 40;
	private static final int MAX_LONGITUD_TELEFONO     = 30;
	private static final int MAX_LONGITUD_DIRECCION    = 150;
	private static final int MAX_LONGITUD_REFERENCIA   = 20;
	private static final int MAX_LONGITUD_DESCRIPCION  = 3000;
	private static final int MAX_LONGITUD_CANTIDAD     = 22;
	private static final int MAX_LONGITUD_DESCUENTO    = 22;
	private static final int MAX_LONGITUD_PORCENTAJE_COMISION = 5;
	private static final int MAX_LONGITUD_AUTORIZACION_SAP = 15;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DecimalFormat formatoEntero  = new DecimalFormat("###0");
	private DecimalFormat formatoSerial = new DecimalFormat("0000000");
	protected Double subTotal = 0D, totalIvaCero = 0D, descuentoTotal = 0D, descuentoGlobalTotal = 0D, 
	ivaTotal = 0D, total = 0D, valorBruto = 0D, ivaTemp = 0D, descuentosVariosTotal = 0D;
	protected Double porcentajeComisionAgencia = 0D, comisionAgenciaTotal = 0D;
	protected Double IVA = Parametros.getIVA() / 100;
	protected Double IVA_CERO = 0D;
	protected Double montoDescuento = 0.00;
	private java.util.Date fechaCreacion = new java.util.Date();
	private String campanaPeriodo = "";
	private Double ivaFinalReporte = 0D;
	private Double subtotalFinalReporte = 0D;
	private JMenuItem itemEliminarPedidoDetalle;
	private JMenuItem itemVerPersonalizacionPedidoDetalle;
	final   JPopupMenu popupMenuPedidoDetalle = new JPopupMenu();
	private DefaultTableModel modelPresupuestoDetalleP;
	
	private static Date fechaPedido;
	private Vector<PedidoDetalleIf> pedidoDetalleColeccion      = new Vector<PedidoDetalleIf>();
	private Vector<PresupuestoDetalleIf> presupuestoDetalleFacturadoColeccion = new Vector<PresupuestoDetalleIf>();
	private Vector<PresupuestoFacturacionIf> presupuestoFacturacionColeccion = new Vector<PresupuestoFacturacionIf>();
	private Vector<PedidoDetalleIf> facturaDetalleTempColeccion = new Vector<PedidoDetalleIf>();
	private Vector<PedidoDetalleIf> pedidoDetalleEliminadas     = new Vector<PedidoDetalleIf>();
	private Long idModulo  = 0L;
	boolean detalleCargado = false;
	private Calendar now   = Calendar.getInstance();
	private static final int MAX_LONGITUD_PREIMPRESO = 15;
	private boolean facturarComision   = false;
	boolean cargandoDetallesReferencia = false;
	private NuevoClientePosModel jdNuevoClientePos;
	private String activarVisorDetallePersonalizacion = "N";
	private String URLVisor = "";
	private Vector<PresupuestoDetalleIf> presupuestoDetalleColeccionP = new Vector<PresupuestoDetalleIf>();	
	protected ProductoIf productoIfP;
	private PresupuestoDetalleIf presupuestoDetalleIfP;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no};
	private TipoDocumentoIf tipoDocumento = null;
	private String tipoPresupuestoFacturacion = TIPO_PRESUPUESTO_FACTURACION_NORMAL;
	private Date fechaInicioPlanMedioPedido = new Date();
	private Date fechaFinPlanMedioPedido    = new Date();
	private int  formaFacturacion = 1;
	private ArrayList<OrdenMedioIf> listaOrdenMedioTotal = new ArrayList<OrdenMedioIf>();
	private ArrayList<PlanMedioFacturacionIf> listaPlanMedioFacturacionEscogido = new ArrayList<PlanMedioFacturacionIf>();// listaPlanMedio
	private PlanMedioFormaPagoData planMedioFormaPagoIf = new PlanMedioFormaPagoData();
	private Date fechaInicioPlanMedioFormaPago = new Date();
	private Date fechaFinPlanMedioFormaPago    = new Date();
	private static final String TIPO_FORMA_PAGO_COMPLETO = "C";
	private static final String TIPO_FORMA_PAGO_PARCIAL = "R"; 
	private static final String TIPO_FORMA_PAGO_POR_PRODUCTO = "P";
	private static final String TIPO_FORMA_PAGO_COMISION_MEDIO = "M";
	private static final String TIPO_FORMA_PAGO_CANAL = "L";
	private static final String TIPO_FORMA_PAGO_POR_PRODUCTO_VERSION = "V";
	private String tipoFormaPago = "";
	private Long formaPagoId = 0L;
	//private Long idPlanMedioFormaPagoGuardado = 0L;
	//protected PlanMedioFormaPagoIf planMedioFormaPagoIfGuardado; 
	private Long idProductoClientePlanMedioPedido = new Long(0);

	// Cuando es por Producto Cliente Version
	private CampanaProductoVersionIf formaPagoVersion;
	
	private CampanaProductoVersionIf versionProductoClientePlanMedioPedido;
	private static final String ORDEN_MEDIO_TIPO_CANAL   = "C";
	private static final String ORDEN_MEDIO_TIPO_PRODUCTO_COMERCIAL = "P";
	private static final String ORDEN_MEDIO_TIPO_VERSION  = "V";
	private String preimpresoFacturaReembolso = "";
	private static final int JTP_PEDIDO_DETALLE_TAB = 1;
	String ordenesSinFacturar = "";
	Vector<PurchaseData> purchaseDataVector = new Vector<PurchaseData>();
	private Map<Integer, Vector<PurchaseData>> purchaseDataMap = new HashMap<Integer, Vector<PurchaseData>>();
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long, ClienteIf> mapaCliente = new HashMap<Long, ClienteIf>();
	private Map<Long, ProductoIf> mapaProductoProveedor = new HashMap<Long, ProductoIf>();
	private Map<Long, GenericoIf> mapaGenerico = new HashMap<Long, GenericoIf>();
	private Map<Long, Long> mapaOrdenesMedioFacturadas = new HashMap<Long, Long>();
	private Map<Long, Long> mapaPresupuestosDetalleParaFacturar = new HashMap<Long, Long>();
	private Map<Long, TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long, TipoDocumentoIf>();
	private Map<Long, DocumentoIf> mapaDocumento = new HashMap<Long, DocumentoIf>();
	private Map<Long, LineaIf> mapaLinea = new HashMap<Long, LineaIf>();
	private Map<Long, TipoIdentificacionIf> mapaTipoIdentificacion = new HashMap<Long, TipoIdentificacionIf>();
	private Map<Long, CampanaProductoVersionIf> mapaCampanaProductoVersion = new HashMap<Long, CampanaProductoVersionIf>();
	
	
	public PedidoModel() {
		iniciarConstructor();
	}

	private void iniciarConstructor() {
		
		//este combo se debe cargar en el constructor porque si se carga en el showsavemode
		//se lo llama mas de una vez y se resetea la oficina seleccionada.
		cargarComboOficina(null);
		
		initKeyListeners();
		iniciarComponentes();
		inicializarPopup();
		clean();
		cargarComboTipoReferencia();
		cargarMapas();
		oficinaIf = (OficinaIf) Parametros.getOficina();
		initListeners();
		getLblReferencia().setVisible(false);
		getTxtReferencia().setVisible(false);
		getTblPedidoDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblPresupuestoDetalleP().addMouseListener(oMouseAdapterTblPresupuestoDetalleP);
		showSaveMode();
		new HotKeyComponent(this);
		getTxtObservacion().addKeyListener(kl);
		
	}
	
	public PedidoModel(PedidoIf pedido){
		iniciarConstructor();
		try {
			this.pedido = pedido;
			getSelectedObject();
		} catch (Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al abrir Pedido desde existente !!", SpiritAlert.ERROR);
		}
	}

	public PedidoModel(PresupuestoIf presupuesto){
		iniciarConstructor();
		try {
			nuevoConstructor(presupuesto);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al abrir Pedido desde Presupuesto !!", SpiritAlert.ERROR);
		}
	}
	
	KeyListener kl = new KeyListener(){

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_F8){
				PresupuestoIf p;
				try {
					p = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(2840L);
					nuevoConstructor(p);
				} catch (GenericBusinessException e1) {
					e1.printStackTrace();
				}
			}
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};

	private void nuevoConstructor(PresupuestoIf presupuesto) throws GenericBusinessException {
		OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuesto.getOrdentrabajodetId());
		OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
		clienteOficinaIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenTrabajo.getClienteoficinaId());
		seleccionarClienteOficina();
		getCmbTipoReferencia().setSelectedItem(TipoReferenciaPedido.PRESUPUESTO);
		presupuestoIf = presupuesto;
		seleccionarPresupuesto(tipoPresupuestoFacturacion);
	}
	
	public void showSaveMode() {
		planMedioIf = null;
		planesMedioIf = new Vector<PlanMedioIf>();
		getTxtCodigo().setBackground(getBackground());
		getTxtContacto().setBackground(Parametros.saveUpdateColor);
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		getCmbTipoReferencia().setBackground(Parametros.saveUpdateColor);
		getCmbFechaPedido().setBackground(Parametros.saveUpdateColor);
		getTxtClienteOficina().setBackground(getBackground());
		getCmbTipoDocumento().setBackground(Parametros.saveUpdateColor);
		getCmbVendedor().setBackground(Parametros.saveUpdateColor);
		getTxtFacturaProveedor().setBackground(getBackground());
		getTxtCodigoProducto().setEditable(false);
		corporacionIf = null;
		clienteIf = null;
		clienteOficinaIf = null;
						
		setSaveMode();
		pedido = null;
		fechaPedido = fechaCreacion;
		cargarCombos();
		inicializarPanelShowSaveMode();
		inicializarObjetosShowSaveMode();
		refresh();
	}

	public void showFindMode() {
		getTxtCodigo().setBackground(Parametros.findColor);
		getTxtContacto().setBackground(Parametros.findColor);
		getCmbEstado().setBackground(Parametros.findColor);
		getCmbTipoReferencia().setBackground(Parametros.findColor);
		getCmbFechaPedido().setBackground(Parametros.findColor);
		getTxtClienteOficina().setBackground(Parametros.findColor);
		getCmbTipoDocumento().setBackground(Parametros.findColor);
		getTxtFacturaProveedor().setBackground(Parametros.findColor);
		getCmbVendedor().setBackground(Parametros.findColor);
		Calendar calendar = new GregorianCalendar(1900, 1, 1);
		getCmbFechaPedido().getDateModel().setMinDate(calendar);
		getCmbFechaVencimiento().getDateModel().setMinDate(calendar);
		
		inicializarPanelFindMode();
		cargarComboEstado();
		cargarComboVendedor();
		this.getTxtCodigo().grabFocus();
	}

	public void showUpdateMode() {
		getTxtCodigo().setBackground(getBackground());
		getTxtContacto().setBackground(Parametros.saveUpdateColor);
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		getCmbTipoReferencia().setBackground(Parametros.saveUpdateColor);
		getCmbFechaPedido().setBackground(Parametros.saveUpdateColor);
		getTxtClienteOficina().setBackground(getBackground());
		getCmbTipoDocumento().setBackground(Parametros.saveUpdateColor);
		getCmbVendedor().setBackground(Parametros.saveUpdateColor);
		getTxtFacturaProveedor().setBackground(getBackground());
		setUpdateMode();
		inicializarPanelUpdateMode();
		this.getBtnGenerarFactura().setEnabled(true);
	}

	public void save() {
		if (validateFields()) {
			try {
				if (getCmbFundacion().getSelectedItem() != null)
					fundacion = (ClienteIf) getCmbFundacion().getSelectedItem();
				if (savePedido()) {
					SpiritAlert.createAlert("Pedido guardado con éxito",SpiritAlert.INFORMATION);
					
					//////Plan de Medios//////
					PedidoIf pedidoIf = SessionServiceLocator.getPedidoSessionService().getPedido(idPedidoGuardado);
					/*Collection pedidoColl = SessionServiceLocator.getPedidoSessionService().findPedidoById(idPedidoGuardado);
					Iterator pedidoCollIt = pedidoColl.iterator();
					PedidoData pedidoData = new PedidoData();
					PedidoIf pedidoIf = pedidoData;
					if(pedidoCollIt.hasNext()){
						pedidoIf = (PedidoIf)pedidoCollIt.next();
					}*/
					
					String letraTipoReferencia = pedidoIf.getTiporeferencia();
					TipoReferenciaPedido tr = TipoReferenciaPedido.getTipoReferenciaPedidoByLetra(letraTipoReferencia);
					
					if (TipoReferenciaPedido.PLAN_MEDIOS == tr || TipoReferenciaPedido.COMBINADO == tr){
						
						//guardo registros de plan medio facturacion
						for(int i=0; i<listaPlanMedioFacturacionEscogido.size(); i++){
							listaPlanMedioFacturacionEscogido.get(i).setEstado("P");
							listaPlanMedioFacturacionEscogido.get(i).setPedidoId(idPedidoGuardado);
						}
						SessionServiceLocator.getPlanMedioFacturacionSessionService().procesarPlanMedioFacturacionList(listaPlanMedioFacturacionEscogido);
						
						//cuando es multiple
						if(planesMedioIf.size() > 0){
							for(PlanMedioIf planMedio : planesMedioIf){
								formaPagoId = planMedio.getId();
								planMedioFormaPagoIf = crearPlanMedioFormaPagoIf(idPedidoGuardado,planMedio.getId(),planMedio.getFechaInicio(),planMedio.getFechaFin(),tipoFormaPago, formaPagoId, formaPagoVersion,"P");
								SessionServiceLocator.getPlanMedioFormaPagoSessionService().procesarPlanMedioFormaPago(planMedioFormaPagoIf);
								
								if (planMedio.getEstado().trim().compareTo("F") != 0){
									// se cambia el estado del Plan de Medio a PEDIDO
									planMedio.setEstado("D"); 
									SessionServiceLocator.getPlanMedioSessionService().savePlanMedio(planMedio);
								}
							}							
						}
						//cuando es normal
						else if (planMedioIf != null){
							planMedioFormaPagoIf = crearPlanMedioFormaPagoIf(idPedidoGuardado,planMedioIf.getId(),fechaInicioPlanMedioFormaPago,fechaFinPlanMedioFormaPago,tipoFormaPago, formaPagoId, formaPagoVersion,"P");
							//idPlanMedioFormaPagoGuardado = 
							SessionServiceLocator.getPlanMedioFormaPagoSessionService().procesarPlanMedioFormaPago(planMedioFormaPagoIf);
							
							if (planMedioIf.getEstado().trim().compareTo("F") != 0){
								// se cambia el estado del Plan de Medio a PEDIDO
								planMedioIf.setEstado("D"); 
								SessionServiceLocator.getPlanMedioSessionService().savePlanMedio(planMedioIf);
							}
						}
					}
					// ///////////////////////////////

					pedidoDetalleColeccion.clear();
					this.clean();
					this.showSaveMode();
					String si = "Si"; 
	    	        String no = "No"; 
	    	        Object[] options = {si,no}; 
	    			int opcion = JOptionPane.YES_OPTION;
	    			if (!facturarComision)
	    				opcion = JOptionPane.showOptionDialog(null, "¿Desea generar la Factura?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
	    			
					if (opcion == JOptionPane.YES_OPTION) {
						pedido = SessionServiceLocator.getPedidoSessionService().getPedido(idPedidoGuardado);
						//planMedioFormaPagoIfGuardado = SessionServiceLocator.getPlanMedioFormaPagoSessionService().getPlanMedioFormaPago(idPlanMedioFormaPagoGuardado);
						getSelectedObject();
						facturar();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Ocurrió un error al guardar el Pedido",SpiritAlert.ERROR);
			}
		}
	}

	public void update() {
		if (validateFields()) {
			try {
				PedidoIf pedidoAnterior = registrarPedidoAnterior();
				cargandoDetallesReferencia = true;
				if (verificarDetallesPedido(pedidoDetalleColeccion)) {
					// pedido =
					// getPedidoSessionService().actualizarPedido(pedido,
					// PedidoDetalleColeccion, pedidoAnterior,
					// pedidoDetalleEliminadas);
					if (getCmbFundacion().getSelectedItem() != null)
						fundacion = (ClienteIf) getCmbFundacion().getSelectedItem();
					// if (pedidoAnterior.getEstado().equals(ESTADO_COMPLETO) ||
					// pedidoAnterior.getEstado().equals(ESTADO_INCOMPLETO)) {
					String estadoLetra = pedidoAnterior.getEstado();
					EstadoPedido estado = EstadoPedido.getEstadoOrdenTrabajoByLetra(estadoLetra);
					if (estado == EstadoPedido.COMPLETO || estado == EstadoPedido.INCOMPLETO) {
						FacturaIf factura = registrarFactura();
						OficinaIf oficina = (OficinaIf)getCmbOficinaEmpresa().getSelectedItem();
						boolean exito = SessionServiceLocator.getFacturaSessionService().actualizarFactura(pedido, factura, pedidoDetalleColeccion, Parametros.getIdEmpresa(), oficina.getId(), (UsuarioIf) Parametros.getUsuarioIf());
						if (exito) {
							PedidoIf pedido = registrarPedidoForUpdate();
							pedido = SessionServiceLocator.getPedidoSessionService().actualizarPedido(pedido, pedidoDetalleColeccion, pedidoAnterior, pedidoDetalleEliminadas,tarea);
							SpiritAlert.createAlert("Factura actualizada con éxito", SpiritAlert.INFORMATION);
						} else {
							SpiritAlert.createAlert("No se ha podido actualizar la factura. La factura puede estar cancelada parcial o totalmente", SpiritAlert.WARNING);
						}
					} else if (estado == EstadoPedido.PENDIENTE && verificarDocumentoDeDetalle() ){
						PedidoIf pedido = registrarPedidoForUpdate();
						pedido = SessionServiceLocator.getPedidoSessionService().actualizarPedido(pedido, pedidoDetalleColeccion, pedidoAnterior, pedidoDetalleEliminadas,tarea);
						SpiritAlert.createAlert("Pedido actualizado con éxito", SpiritAlert.INFORMATION);
					}

					// Para guardar cambios en el reporte de presupuesto detalle
					// if(idPedidoGuardado != null &&
					// pedido.getTiporeferencia().equals(REFERENCIA_PRESUPUESTO)
					// &&
					// pedido.getReferencia() != null){
					if(idPedidoGuardado != null && 
							pedido.getTiporeferencia().equals(TipoReferenciaPedido.PRESUPUESTO.getLetra()) && 
							pedido.getReferencia() != null){
						for(PresupuestoDetalleIf presupuestoDetalle : presupuestoDetalleColeccionP){
							SessionServiceLocator.getPresupuestoDetalleSessionService().savePresupuestoDetalle(presupuestoDetalle);
						}				
					}
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error general al actualizar pedido/factura",SpiritAlert.ERROR);
			}
			cargandoDetallesReferencia = false;
			clean();
			showSaveMode();
		}
	}

	private boolean verificarDocumentoDeDetalle(){
		int contador = 0;
		for ( PedidoDetalleIf pd : pedidoDetalleColeccion ){
			contador++;
			if ( pd.getDocumentoId() == null ){
				SpiritAlert.createAlert("Detalle en fila "+contador+" no tiene documento seleccionado !!",
						SpiritAlert.ERROR);
				return false;
			}
		}
		return true;
	}
	
	public void delete() {
		String si = "Si"; 
        String no = "No"; 
        Object[] options ={si,no}; 
		int anularPedido = JOptionPane.showOptionDialog(this, "Los pedidos no pueden ser eliminados. ¿Desea cambiar el estado del Pedido actual a anulado?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
		// int anularPedido = JOptionPane.showConfirmDialog(this, "Los pedidos
		// no pueden ser eliminados. ¿Desea cambiar el estado del Pedido actual
		// a anulado?","Confirmación", JOptionPane.YES_NO_OPTION,
		// JOptionPane.QUESTION_MESSAGE);

		if (anularPedido == JOptionPane.YES_OPTION) {
			// getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ANULADO);
			getCmbEstado().setSelectedItem(EstadoPedido.ANULADO);
			update();
		} else {
			this.clean();
			this.showSaveMode();
		}
	}

	public void report() {
		try {
			Double subtotalReporte = 0D;
			Double ivaReporte = 0D;
			ivaFinalReporte = 0D;
			subtotalFinalReporte = 0D;
			if (facturaDetalleTempColeccion.size() > 0) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir la Factura?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
				if (opcion == JOptionPane.YES_OPTION) {
					
					FacturaIf factura = facturaGenerada;
					Long idParametro = factura.getId();
					TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(factura.getTipodocumentoId());
									
					PedidoIf pedidoReportePresupuesto = SessionServiceLocator.getPedidoSessionService().getPedido(factura.getPedidoId());
					
					boolean resumirConcepto = false;
					
					boolean facturaReembolsoNormal = false;
					boolean facturaReembolsoIvaEnTotal = false;
					boolean facturaReembolsoIvaEnDetalles = false;
					if(tipoDocumento.getReembolso().equals("S") && tipoDocumento.getCodigo().equals("FAR") ){
						FacturaReembolsoFormatoModel jdFacturacionPresupuesto = new FacturaReembolsoFormatoModel(Parametros.getMainFrame());
						if(jdFacturacionPresupuesto.getRbFormatoNormal().isSelected()){
							facturaReembolsoNormal = true;
						}else if(jdFacturacionPresupuesto.getRbFormatoIVAenTotal().isSelected()){
							facturaReembolsoIvaEnTotal = true;
						}else if(jdFacturacionPresupuesto.getRbFormatoIVAenDetalles().isSelected()){
							facturaReembolsoIvaEnDetalles = true;
						}
					}else if(pedidoReportePresupuesto.getTiporeferencia().equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())){
						int opcionDetalle = JOptionPane.showOptionDialog(null, "¿Desea resumir el detalle de cada proveedor?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
						if (opcionDetalle == JOptionPane.YES_OPTION) {
							resumirConcepto = true;
						}
					}
										
					String codigoReferencia = "";
					PlanMedioFormaPagoIf planMedioFormaPagoReporte = null;
					
					if(pedidoReportePresupuesto.getReferencia() != null){
						if(pedidoReportePresupuesto.getTiporeferencia().equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())){
							/*PresupuestoIf presupuestoReporte = (PresupuestoIf)SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(pedidoReportePresupuesto.getReferencia()).iterator().next();
							Map aMap = new HashMap();
							aMap.put("presupuestoId", presupuestoReporte.getId());
							aMap.put("reporte", REPORTE_PRESUPUESTO_SI);
							Collection PresupuestoDetalleReporte = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByQuery(aMap);
							if(PresupuestoDetalleReporte.size() > 0)*/
								codigoReferencia = pedidoReportePresupuesto.getReferencia();
						
						}else if(pedidoReportePresupuesto.getTiporeferencia().equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra())){
							/*PlanMedioIf planMedioReporte = (PlanMedioIf)SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigo(pedidoReportePresupuesto.getReferencia()).iterator().next();
							PlanMedioMesIf planMedioMesReporte = (PlanMedioMesIf)SessionServiceLocator.getPlanMedioMesSessionService().findPlanMedioMesByPlanmedioId(planMedioReporte.getId()).iterator().next();
							Collection planMedioDetalleReporte = SessionServiceLocator.getPlanMedioDetalleSessionService().findPlanMedioDetalleByPlanMedioMesId(planMedioMesReporte.getId());
							
							if(planMedioDetalleReporte.size() > 0)*/
								codigoReferencia = pedidoReportePresupuesto.getReferencia();
														
							Collection planMedioFormaPagoColl = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(factura.getPedidoId());
							if (planMedioFormaPagoColl.size() > 0){
								Iterator planMedioFormaPagoIt = planMedioFormaPagoColl.iterator();
								planMedioFormaPagoReporte = (PlanMedioFormaPagoIf)planMedioFormaPagoIt.next();
							}
						}
					}
					
					
					
					// Inicializa los datos del reporte que viene del pedido
					Collection dataSourceCollection = initializeBeanCollection(planMedioFormaPagoReporte,idParametro, false, pedidoReportePresupuesto, tipoDocumento, tipoPresupuestoFacturacion, facturaReembolsoIvaEnDetalles, facturaReembolsoNormal, resumirConcepto);
					Map proveedoresMap = getProveedoresMap(factura);
					Map productosMap = getProductosMap(factura);
					JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(dataSourceCollection);					
					dataSourceCollection = initializeBeanCollection(planMedioFormaPagoReporte,idParametro, true, pedidoReportePresupuesto, tipoDocumento, tipoPresupuestoFacturacion, facturaReembolsoIvaEnDetalles, facturaReembolsoNormal, resumirConcepto);
					
					// SAP y Producto Cliente
					String autorizacionSAP = "";
					String productoCliente = "";
					
					if(factura.getAutorizacionSap() != null && !factura.getAutorizacionSap().equals("")){
						autorizacionSAP = factura.getAutorizacionSap();
					}
					
					// busco el producto cliente
					if (pedido.getTiporeferencia() != null 
							&& pedido.getTiporeferencia().equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())){
						
						Collection presupuestos = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigoAndEmpresaId(pedido.getReferencia(), Parametros.getIdEmpresa());
						Iterator presupuestosIt = presupuestos.iterator();
						while(presupuestosIt.hasNext()){
							PresupuestoIf presupuesto = (PresupuestoIf)presupuestosIt.next();
							Collection presupuestoProductos = SessionServiceLocator.getPresupuestoProductoSessionService().findPresupuestoProductoByPresupuestoId(presupuesto.getId());
							Iterator presupuestoProductosIt = presupuestoProductos.iterator();
							while(presupuestoProductosIt.hasNext()){
								PresupuestoProductoIf presupuestoProductoIf = (PresupuestoProductoIf)presupuestoProductosIt.next();
								ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(presupuestoProductoIf.getProductoClienteId());
								productoCliente = productoClienteIf.getNombre();
							}
						}						
					}
					
					else if (pedido.getTiporeferencia() != null 
							&& pedido.getTiporeferencia().equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra())){
						
						String referencia = pedido.getReferencia();
						//si es caso multiple (varios presupuestos en una misma factura)
						if(pedido.getReferencia().equals("M")){
							//busco en plan medio forma de pago (tiene muchos menos registros que plan medio facturacion)
							Collection planesMedioFormaPago = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(pedido.getId());
							Iterator planesMedioFormaPagoIt = planesMedioFormaPago.iterator();
							while(planesMedioFormaPagoIt.hasNext()){
								PlanMedioFormaPagoIf planMedioFormaPago = (PlanMedioFormaPagoIf)planesMedioFormaPagoIt.next();
								PlanMedioIf planMedio = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(planMedioFormaPago.getPlanMedioId());
								referencia = planMedio.getCodigo(); 
							}
						}
						
						PlanMedioIf planMedioReporte = (PlanMedioIf)SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndEstadosAprobadoPedido(referencia, Parametros.getIdEmpresa()).iterator().next();
						OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(planMedioReporte.getOrdenTrabajoDetalleId());
						OrdenTrabajoIf ordenTrabajoIf = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalleIf.getOrdenId());
						Collection ordenTrabajoProductos = SessionServiceLocator.getOrdenTrabajoProductoSessionService().findOrdenTrabajoProductoByOrdenTrabajoId(ordenTrabajoIf.getId());
						Iterator ordenTrabajoProductosIt = ordenTrabajoProductos.iterator();
						while(ordenTrabajoProductosIt.hasNext()){
							OrdenTrabajoProductoIf ordenTrabajoProducto = (OrdenTrabajoProductoIf)ordenTrabajoProductosIt.next();
							ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(ordenTrabajoProducto.getProductoClienteId());
							productoCliente = productoClienteIf.getNombre();
						}
					}				
					
					HashMap parametrosMap = new HashMap();
										
					if (tipoDocumento.getReembolso().equals("S")){
						parametrosMap.put("reembolso", "S");
						parametrosMap.put("comision", "N");
						if(!autorizacionSAP.equals("") && !productoCliente.equals("")){
							parametrosMap.put("reembolsoGasto", "SAP: " + autorizacionSAP + "  |  PRODUCTO: " + productoCliente +  "\nREEMBOLSO DE GASTO\n");
						}else if(!autorizacionSAP.equals("")){
							parametrosMap.put("reembolsoGasto", "SAP: " + autorizacionSAP + "\nREEMBOLSO DE GASTO\n");
						}else{
							parametrosMap.put("reembolsoGasto", "REEMBOLSO DE GASTO\n");
						}
					}
					else if (tipoDocumento.getCodigo().equals("FCO")){
						parametrosMap.put("reembolso", "N");
						parametrosMap.put("comision", "S");
						parametrosMap.put("reembolsoGasto", "COMISION DE AGENCIA \n SOBRE F: " + preimpresoFacturaReembolso + "\n");
						// recien ahora limpio la variable porque si lo hago en
						// clean() se borra antes de tiempo
						preimpresoFacturaReembolso = "";
					}
					else{
						parametrosMap.put("reembolso", "N");
						parametrosMap.put("comision", "N");
						// parametrosMap.put("reembolsoGasto", "");
						
						if(!autorizacionSAP.equals("")){
							parametrosMap.put("reembolsoGasto", "SAP: " + autorizacionSAP + "\n");
						}else{
							parametrosMap.put("reembolsoGasto", "");
						}
					}
										
					parametrosMap.put("campanaPeriodo", campanaPeriodo);
					parametrosMap.put("dataSourceDetail", dataSourceDetail);
					parametrosMap.put("proveedoresMap", proveedoresMap);
					parametrosMap.put("productosMap", productosMap);
					parametrosMap.put("numeroFactura", formatoSerial.format(factura.getNumero().doubleValue()));
					String fecha = factura.getFechaFactura().toString();
					String year = fecha.substring(0,4);
					String month = fecha.substring(5,7);
					String day = fecha.substring(8,10);					
					//OficinaIf oficina = SessionServiceLocator.getOficinaSessionService().getOficina(factura.getOficinaId());
					OficinaIf oficina = SessionServiceLocator.getOficinaSessionService().getOficina(Parametros.getIdOficina());
					CiudadIf ciudad = SessionServiceLocator.getCiudadSessionService().getCiudad(oficina.getCiudadId());
					String nombreCiudad = ciudad.getNombre();
					String fechaFactura = nombreCiudad + ", " + day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + year;
					parametrosMap.put("fechaCreacion", fechaFactura);
					
					String fechaVencimiento = "";
					if(getCmbFechaVencimiento().getCalendar() != null){
						fechaVencimiento = Utilitarios.getFechaUppercase(getCmbFechaVencimiento().getDate());
					}
					parametrosMap.put("fechaVencimiento", fechaVencimiento);
					
					parametrosMap.put("nombreOficina", oficina.getNombre());
					parametrosMap.put("direccionOficina", (!oficina.getDireccion().equals("S/D") && !oficina.getDireccion().equals("S/N") && !oficina.getDireccion().trim().equals(""))?oficina.getDireccion():"");
					parametrosMap.put("telefonoOficina", (!oficina.getTelefono().equals("S/D") && !oficina.getTelefono().equals("S/N") && !oficina.getTelefono().trim().equals(""))?oficina.getTelefono():"");
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("razonSocialEmpresa", empresa.getNombre());
					parametrosMap.put("rucEmpresa", empresa.getRuc());
					ClienteOficinaIf clienteOficina = mapaClienteOficina.get(factura.getClienteoficinaId());
					ClienteIf cliente = mapaCliente.get(clienteOficina.getClienteId());
					parametrosMap.put("razonSocialCliente", cliente.getRazonSocial());
					TipoReferenciaPedido tr = (TipoReferenciaPedido) getCmbTipoReferencia().getSelectedItem();
					String presupuestoReferencia = SpiritConstants.getEmptyCharacter();
					if (getCbMostrarReferenciaFactura().isSelected()) {
						if ( TipoReferenciaPedido.PRESUPUESTO == tr )
							presupuestoReferencia = presupuestoIf.getCodigo();
						if ( TipoReferenciaPedido.PLAN_MEDIOS == tr )
							presupuestoReferencia = planMedioIf.getCodigo();
					}
					parametrosMap.put("presupuesto", presupuestoReferencia);
					parametrosMap.put("descuentoTotal", factura.getDescuento().add(factura.getDescuentoGlobal()).doubleValue());
					parametrosMap.put("descuentosVariosTotal", factura.getDescuentosVarios().doubleValue());
					parametrosMap.put("porcentajeComision", factura.getPorcentajeComisionAgencia());
					parametrosMap.put("otroImpuestoTotal", factura.getOtroImpuesto());
					Double valor = factura.getValor().doubleValue();
					Double descuento =  factura.getDescuento().doubleValue() + factura.getDescuentoGlobal().doubleValue();
					Double descuentosVarios = factura.getDescuentosVarios().doubleValue();
					Double porcentajeComision = factura.getPorcentajeComisionAgencia().doubleValue();
					Double comision = ((valor - descuento - descuentosVarios) * porcentajeComision) / 100D;
					Double iva = factura.getIva().doubleValue();
					Double otroImpuesto = factura.getOtroImpuesto().doubleValue();
					Double grandTotal = valor - descuento - descuentosVarios + comision + otroImpuesto + iva;
					parametrosMap.put("grandTotal", Utilitarios.redondeoValor(grandTotal));
					String totalFactura = formatoDecimal.format( Utilitarios.redondeoValor(grandTotal));
					FormaPagoIf formaPago = SessionServiceLocator.getFormaPagoSessionService().getFormaPago(factura.getFormapagoId());
					String detallePagos = formaPago.getNombre() + ": " + "$ " + totalFactura;
					parametrosMap.put("detallePagos", detallePagos);
					String parteDecimal = totalFactura.substring(totalFactura.indexOf('.'), totalFactura.length());
					Double dParteDecimal = 0.0;
					
					if (!parteDecimal.isEmpty())
						dParteDecimal = Double.valueOf(parteDecimal);
					
					MonedaIf moneda = SessionServiceLocator.getMonedaSessionService().getMoneda(factura.getMonedaId());
					String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(totalFactura, dParteDecimal, new int[] { 200 }, false, moneda);
					String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
					parametrosMap.put("cantidadEnLetras", cantidadLetrasPrimeraLinea);
					parametrosMap.put("valorComision",  Utilitarios.redondeoValor(comision));
							 
					String cantidadLetrasSegundaLinea =" ";
					
					if(cantidadLetrasPrimeraLinea.length()>75)
					{
						String letras=cantidadLetrasPrimeraLinea;
						cantidadLetrasPrimeraLinea=letras.substring(0,75);
						cantidadLetrasSegundaLinea=letras.substring(75,letras.length());
					}
						
					parametrosMap.put("cantidadEnLetras", cantidadLetrasPrimeraLinea);
					parametrosMap.put("cantidadEnLetras2", cantidadLetrasSegundaLinea);
										
					if(facturaReembolsoIvaEnTotal){
						//parametrosMap.put("reembolso", "N");
						parametrosMap.put("valorTotal", subtotalFinalReporte);
						parametrosMap.put("totalIvaCero", totalIvaCero);
						parametrosMap.put("ivaTotal", ivaFinalReporte);
						parametrosMap.put("mostrarIVA", "S");
						parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
						
					}else if(facturaReembolsoNormal){
						//parametrosMap.put("reembolso", "N");
						subtotalReporte =  Utilitarios.redondeoValor(factura.getValor().doubleValue());
						ivaReporte =  Utilitarios.redondeoValor(factura.getIva().doubleValue());
						parametrosMap.put("valorTotal", subtotalReporte);
						parametrosMap.put("totalIvaCero", totalIvaCero);
						parametrosMap.put("ivaTotal", ivaReporte);
						parametrosMap.put("mostrarIVA", "N");
						parametrosMap.put("porcentajeIVA", formatoEntero.format(IVA_CERO.doubleValue()));					
					}
					else if(facturaReembolsoIvaEnDetalles){
						subtotalReporte =  Utilitarios.redondeoValor(factura.getValor().doubleValue());
						ivaReporte =  Utilitarios.redondeoValor(factura.getIva().doubleValue());
						parametrosMap.put("valorTotal", subtotalReporte);
						parametrosMap.put("totalIvaCero", totalIvaCero);
						parametrosMap.put("ivaTotal", ivaReporte);
						parametrosMap.put("mostrarIVA", "N");
						parametrosMap.put("porcentajeIVA", formatoEntero.format(IVA_CERO.doubleValue()));					
					}
					else{	
						subtotalReporte =  Utilitarios.redondeoValor(factura.getValor().doubleValue());
						ivaReporte =  Utilitarios.redondeoValor(factura.getIva().doubleValue());
						parametrosMap.put("valorTotal", subtotalReporte);
						parametrosMap.put("totalIvaCero", totalIvaCero);
						parametrosMap.put("ivaTotal", ivaReporte);
						parametrosMap.put("mostrarIVA", "S");
						parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
					}
					
					parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
					parametrosMap.put("ivaPorcentaje", String.valueOf(Parametros.getIVA()));
					
					String fileName = "jaspers/facturacion/RPFactura.jasper";					
					if(tipoDocumento.getCodigo().equals("VTA")){
						fileName = "jaspers/facturacion/RPNotaVenta.jasper";
					}
					
					/*
					 * if(bonificacion > 0 &&
					 * !tipoDocumento.getCodigo().equals("VTA")) fileName =
					 * "jaspers/facturacion/RPFacturaConBonificacion.jasper";
					 */
					if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) ){
						// parametrosMap.put("pathsubreport",
						// Parametros.getRutaCarpetaReportes()+"/"+(tipoDocumento.getCodigo().equals("VTA")?"jaspers/facturacion/RPNotaVentaDetalle.jasper":"jaspers/facturacion/RPFacturaDetalle.jasper"));
						if(facturaReembolsoIvaEnDetalles){
							fileName = "jaspers/facturacion/RPFacturaReembolsoIvaIncluidoDetalle.jasper";
							parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPFacturaDetalleIvaIncluido.jasper");
						}else if(tipoDocumento.getCodigo().equals("VTA")){
							parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPNotaVentaDetalle.jasper");
						}else if (tipoDocumento.getReembolso().equals("S")){
							fileName = "jaspers/facturacion/RPFacturaReembolso.jasper";
							parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPFacturaReembolsoDetalle.jasper");
						} else {
							parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/facturacion/RPFacturaDetalle.jasper");
						}
					} else {
						throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
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
	
	private void iniciarComponentes(){
		getBtnGenerarFactura().setIcon(SpiritResourceManager.getImageIcon("images/icons/toolbars/facturacion.png"));
		getBtnGenerarFactura().setToolTipText("Generar Factura");
		getBtnActualizarTotales().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarTotales().setText("Actualizar Totales");
		getBtnBuscarCorporacion().setToolTipText("Buscar Corporación");
		getBtnBuscarCorporacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnEncerarCliente().setToolTipText("Limpiar datos cliente");
		getBtnEncerarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/cancelar.png"));
		getBtnBuscarCliente().setToolTipText("Buscar Cliente");
		getBtnBuscarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarClienteOficina().setToolTipText("Buscar Oficina de Cliente");
		getBtnBuscarClienteOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarProducto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnAgregarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarDetalle().setToolTipText("Agregar detalle al pedido");
		getBtnActualizarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarDetalle().setToolTipText("Actualizar detalle del pedido");
		getBtnEliminarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarDetalle().setToolTipText("Eliminar detalle del pedido");
		getBtnAgregarDetalleP().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarDetalleP().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnEliminarDetalleP().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnVerArchivoAdjunto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnVerArchivoAdjunto().setToolTipText("Ver Archivo");
		getBtnNuevoCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnNuevoCliente().setToolTipText("Nuevo cliente");
		getBtnEscojaReferencia().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnEscojaReferencia().setToolTipText("Busqueda de Referencia");
		getBtnAgregarDetalle().setText("");
		getBtnActualizarDetalle().setText("");
		getBtnEliminarDetalle().setText("");
		getBtnAgregarDetalleP().setText("");
		getBtnActualizarDetalleP().setText("");
		getBtnEliminarDetalleP().setText("");
		getBtnFacturaProveedor().setToolTipText("Buscar Compra");
		getBtnFacturaProveedor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarClienteNegociacion().setToolTipText("Buscar Cliente Negociación");
		getBtnBuscarClienteNegociacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		
		getTblPedidoDetalle().setDefaultRenderer(Object.class, cellRenderer);
		/*
		 * TableCellRendererHorizontalRightAlignment tableCellRenderer = new
		 * TableCellRendererHorizontalRightAlignment();
		 * getTblPedidoDetalle().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		 */
		
		getBtnInformacionEnvio().setText("");
		getBtnInformacionEnvio().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/enviar.png"));
		getBtnInformacionEnvio().setToolTipText("Visualizar Informacion de Envio");
		
		getBtnNuevoCliente().setToolTipText("Nuevo Cliente");
		
		Map parameterMap = new HashMap();
		try {
			// Buscar parámetro ACTIVAR VISOR DETALLE PEDIDO
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			parameterMap.put("codigo", "AVDP");
			Iterator it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
			if (it.hasNext()) {
				activarVisorDetallePersonalizacion = ((ParametroEmpresaIf) it.next()).getValor();
				// Buscar parámetro URL Visor
				parameterMap.put("codigo", "UV");
				it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
				if (it.hasNext())
					URLVisor = ((ParametroEmpresaIf) it.next()).getValor();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}

	WindowListener wl = new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent e) {
			jdNuevoClientePos = null;
			System.gc();
		}
	};
	
	 
	public ClienteIf NuevoCliente() {

		jdNuevoClientePos = new NuevoClientePosModel(Parametros.getMainFrame(),"");
		jdNuevoClientePos.addWindowListener(wl);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
		jdNuevoClientePos.setLocation(x, y);
		jdNuevoClientePos.pack();
		jdNuevoClientePos.setModal(true);
		jdNuevoClientePos.setVisible(true);
		
		return jdNuevoClientePos.getCliente();
	}
	
	public void refresh() {
		cargarCombos();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpPedido().getSelectedIndex();
		int tabCount = this.getJtpPedido().getTabCount();
		selectedTab++;
		if (selectedTab >= tabCount)
			selectedTab = 0;
		this.getJtpPedido().setSelectedIndex(selectedTab);
	}

	public void addDetail() {
		if (this.getJtpPedido().getSelectedIndex() == 1)
			agregarDetallePedido();
	}

	public void updateDetail() {
		if (this.getJtpPedido().getSelectedIndex() == 1)
			setFilaSeleccionada(getTblPedidoDetalle().getSelectedRow());
		
		actualizarDetallePedido(false);
	}
	
	public void deleteDetail() {
		if (this.getJtpPedido().getSelectedIndex() == 1)
			eliminarDetallePedido();
	}

	/*
	 * Inicializa los datos del Reporte
	 */
	private Collection initializeBeanCollection(PlanMedioFormaPagoIf planMedioFormaPagoIf, Long idParametro, 
			boolean isHeader, PedidoIf pedido, TipoDocumentoIf tipoDocumento, 
			String tipoFacturacion, boolean facturaReembolsoIvaEnDetalles, boolean facturaReembolsoNormal,
			boolean resumirConcepto) {
		
		ArrayList reportRows = new ArrayList();
		Collection rowCollection = null;
		BigDecimal descuentoPlanMedios = new BigDecimal(0);
		String tipoReferencia = pedido.getTiporeferencia();
		try {
			
			if(tipoReferencia.equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra())){
				
				String referencia = pedido.getReferencia();
				//si es caso multiple (varios presupuestos en una misma factura)
				if(pedido.getReferencia().equals("M")){
					//busco en plan medio forma de pago (tiene muchos menos registros que plan medio facturacion)
					Collection planesMedioFormaPago = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(pedido.getId());
					Iterator planesMedioFormaPagoIt = planesMedioFormaPago.iterator();
					while(planesMedioFormaPagoIt.hasNext()){
						PlanMedioFormaPagoIf planMedioFormaPago = (PlanMedioFormaPagoIf)planesMedioFormaPagoIt.next();
						PlanMedioIf planMedio = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(planMedioFormaPago.getPlanMedioId());
						referencia = planMedio.getCodigo(); 
					}
				}
				
				PlanMedioIf planMedio = (PlanMedioIf)SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndEstadosAprobadoPedido(referencia, Parametros.getIdEmpresa()).iterator().next();
				OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(planMedio.getOrdenTrabajoDetalleId());
				OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
				CampanaIf campana = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajo.getCampanaId());

				Calendar fechaInicio = GregorianCalendar.getInstance();
				Calendar fechaFin    = GregorianCalendar.getInstance();
				fechaInicio.setTime(planMedioFormaPagoIf.getFechaInicio());
				fechaFin.setTime(planMedioFormaPagoIf.getFechaFin());

				int dayInicio = fechaInicio.get(Calendar.DATE);
				int dayFin    = fechaFin.get(Calendar.DATE);
				int month    = fechaFin.get(Calendar.MONTH) + 1;

				String mesS = Utilitarios.getNombreMes(month);
				String anioS = Utilitarios.getYearFromDate(planMedioFormaPagoIf.getFechaFin());
				campanaPeriodo = "CAMPAÑA: " + campana.getNombre() + "\nPERIODO: DEL " + dayInicio + " AL " + dayFin + " DE " + mesS + " DE " + anioS + "\n";
			}/*else if(tipoReferencia.equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())){
				PresupuestoIf presupuesto = (PresupuestoIf)SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigoAndEmpresaId(codigoReferencia, Parametros.getIdEmpresa()).iterator().next();
				OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuesto.getOrdentrabajodetId());
				OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
				CampanaIf campana = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajo.getCampanaId());

				Calendar fecha = GregorianCalendar.getInstance();
				fecha.setTime(presupuesto.getFecha());
				
				int month    = fecha.get(Calendar.MONTH) + 1;
				String mesS = Utilitarios.getNombreMes(month);
				String anioS = Utilitarios.getYearFromDate(presupuesto.getFecha());
				campanaPeriodo = "CAMPAÑA: " + campana.getNombre() + "\nMES: " + mesS + " - " + anioS + "\n";
			}*/
			
			if((tipoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA) || tipoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA)) && !isHeader){
				Collection facturasNegociacionDirecta = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId());
				Iterator facturasNegociacionDirectaIt = facturasNegociacionDirecta.iterator();
				while(facturasNegociacionDirectaIt.hasNext()){
					FacturaIf facturaNegociacionDirecta = (FacturaIf)facturasNegociacionDirectaIt.next();
					
					Collection facturasDetalleNegociacionDirecta = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(facturaNegociacionDirecta.getId());
					Iterator facturasDetalleNegociacionDirectaIt = facturasDetalleNegociacionDirecta.iterator();
					while(facturasDetalleNegociacionDirectaIt.hasNext()){
						FacturaDetalleIf facturaDetalleNegociacionDirecta = (FacturaDetalleIf)facturasDetalleNegociacionDirectaIt.next();
						
						FacturaDetalleData bean = new FacturaDetalleData();
						bean.setDescripcion(facturaDetalleNegociacionDirecta.getDescripcion());
						bean.setCantidad(facturaDetalleNegociacionDirecta.getCantidad());
						BigDecimal precio = facturaDetalleNegociacionDirecta.getPrecioReal();
						bean.setPrecioReal(precio);
						
						bean.setDescuento(facturaDetalleNegociacionDirecta.getDescuento());
						if(precio.multiply(facturaDetalleNegociacionDirecta.getCantidad()).compareTo(new BigDecimal(0)) == 1){
							bean.setValor(precio.multiply(facturaDetalleNegociacionDirecta.getCantidad()));
						}else{
							// para que salga en blanco en el reporte (o sea en
							// la factura)
							bean.setValor(null);
						}
						bean.setIva(facturaDetalleNegociacionDirecta.getIva());
						bean.setDescuentoGlobal(null);
						bean.setProductoId(facturaDetalleNegociacionDirecta.getProductoId());
						bean.setId(facturaDetalleNegociacionDirecta.getId());
						
						if(precio.compareTo(new BigDecimal(0)) == 1)
							reportRows.add(bean);
					}
				}
				return reportRows;
			}
			
			// No entra si es reembolso porque los detalles en caso de reembolso
			// deben venir de factura detalle
			else if (tipoDocumento.getReembolso().equals("N") 
					&& !tipoDocumento.getCodigo().equals("FCO") 
					&& !isHeader && pedido.getReferencia() != null && !pedido.getReferencia().equals("")
					&& tipoReferencia.equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())
					&& (tipoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_NORMAL) || tipoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_CLIENTE))){
				
				//si es facturación multiple
				if(pedido.getReferencia().equals("M")){
					Map<Long,Long> presupuestosDetalleMap = new HashMap<Long,Long>();
					Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId());
					Iterator facturasIt = facturas.iterator();
					while(facturasIt.hasNext()){
						FacturaIf factura = (FacturaIf)facturasIt.next();
						Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByFacturaId(factura.getId());
						Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
						while(presupuestosFacturacionIt.hasNext()){
							PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
							presupuestosDetalleMap.put(presupuestoFacturacionIf.getPresupuestoDetalleId(), presupuestoFacturacionIf.getPresupuestoDetalleId());
						}
					}
					ArrayList presupuestosDetalleIf = new ArrayList();
					Iterator presupuestosDetalleMapIt = presupuestosDetalleMap.keySet().iterator();
					while(presupuestosDetalleMapIt.hasNext()){
						Long presupuestoDetalleId = (Long)presupuestosDetalleMapIt.next();
						PresupuestoDetalleIf presupuestoDetalleIf = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoDetalleId);
						presupuestosDetalleIf.add(presupuestoDetalleIf);						
					}
					rowCollection = presupuestosDetalleIf;
				}else{
					PresupuestoIf presupuestoReporte = (PresupuestoIf)SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(pedido.getReferencia()).iterator().next();
					Map aMap = new HashMap();
					aMap.put("presupuestoId", presupuestoReporte.getId());
					// Solo si la facturacion del presupuesto es normal
					// (completa) se puede usar los detalles tipo reporte
					// caso contrario no coincidirian la suma de los detalles
					// con el total
					aMap.put("reporte", REPORTE_PRESUPUESTO_SI);
					rowCollection = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByQuery(aMap);
				}		
			}	
						
			// PLAN DE MEDIO, FACTURACION NORMAL (POR SERVICIO)
			else if (tipoDocumento.getReembolso().equals("N") 
					&& !tipoDocumento.getCodigo().equals("FCO") 
					&& !isHeader && pedido.getReferencia() != null && !pedido.getReferencia().equals("")
					&& tipoReferencia.equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra())){
				
				try{					
					/*Calendar calendarFechaInicio = GregorianCalendar.getInstance();
					Calendar calendarFechaFin    = GregorianCalendar.getInstance();
					calendarFechaInicio.setTime(planMedioFormaPagoIf.getFechaInicio());
					calendarFechaFin.setTime(planMedioFormaPagoIf.getFechaFin());
					
					int diaInicio = calendarFechaInicio.get(Calendar.DATE);
					int diaFin    = calendarFechaFin.get(Calendar.DATE);
					int mesInt    = calendarFechaFin.get(Calendar.MONTH) + 1;*/
					
					//String mes = Utilitarios.getNombreMes(mesInt);
					//String anio = Utilitarios.getYearFromDate(planMedioFormaPagoIf.getFechaFin());
				
					Collection rowCollectionTmp = null;
					
					ArrayList<FacturaDetalleIf> listaTmp = new ArrayList<FacturaDetalleIf>();
					
					FacturaDetalleData facturaDetalleIf1 = new FacturaDetalleData();
					FacturaDetalleData facturaDetalleIf2 = new FacturaDetalleData();
					FacturaDetalleData facturaDetalleIf3 = new FacturaDetalleData();
					FacturaDetalleData facturaDetalleIf4 = new FacturaDetalleData();
					
					// Forma de Facturacion segun el Plan de Medio Forma de Pago
					String formaFacturacioPMFP = planMedioFormaPagoIf.getTipoFormaPago();
					
					// COMPLETO "C"
					if(formaFacturacioPMFP.compareTo("C") == 0 ){
						//facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
						//facturaDetalleIf2.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
						// VERIFICAR SI ES TV-RADIO-PRENSA
						facturaDetalleIf3.setDescripcion("PAUTA EN LOS SIGUIENTES MEDIOS:\n");
						//listaTmp.add(facturaDetalleIf1);
						//listaTmp.add(facturaDetalleIf2);
						listaTmp.add(facturaDetalleIf3);
					
					}else {
						PlanMedioIf planMedioReporte = (PlanMedioIf)SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndEstadosAprobadoPedido(pedido.getReferencia(), Parametros.getIdEmpresa()).iterator().next();
						
						CampanaProductoVersionIf version = null;
						
						FacturaIf facturaIf = SessionServiceLocator.getFacturaSessionService().getFactura(idParametro);
											
						descuentoPlanMedios = facturaIf.getDescuentoGlobal();
						
						Map aMapPlanMedioFacturacion = new HashMap();
						aMapPlanMedioFacturacion.put("planMedioId", planMedioReporte.getId());
						aMapPlanMedioFacturacion.put("pedidoId",facturaIf.getPedidoId());
						
						Collection planMedioFacturacionColl = SessionServiceLocator.getPlanMedioFacturacionSessionService().findPlanMedioFacturacionByQuery(aMapPlanMedioFacturacion);
						Iterator planMedioFacturacionCollIt = planMedioFacturacionColl.iterator();
						PlanMedioFacturacionIf planMedioFacturacionIf = (PlanMedioFacturacionIf)planMedioFacturacionCollIt.next();
											
						// POR CANAL
						if(formaFacturacioPMFP.compareTo("L") == 0 ){
							
							ClienteOficinaIf clienteProveedor = mapaClienteOficina.get(planMedioFacturacionIf.getProveedorId());
							String proveedor = clienteProveedor.getDescripcion();
							
							//facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
							facturaDetalleIf2.setDescripcion("PROVEEDOR: "+ proveedor);
							//facturaDetalleIf3.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
							// VERIFICAR SI ES TV-RADIO-PRENSA
							facturaDetalleIf4.setDescripcion("PAUTA DE LOS SIGUIENTES PRODUCTOS:\n");
							//listaTmp.add(facturaDetalleIf1);
							listaTmp.add(facturaDetalleIf2);
							//listaTmp.add(facturaDetalleIf3);
							listaTmp.add(facturaDetalleIf4);	
						
							
						// PRODUCTO "P"
						}else if(formaFacturacioPMFP.compareTo("P") == 0 ){
							
							String producto  = "";
													
							if(planMedioFacturacionIf.getCampanaProductoVersionId() != null && planMedioFacturacionIf.getCampanaProductoVersionId().compareTo(new Long(0))==0){
								
								ArrayList<CampanaProductoVersionIf> listCampanaProductoVersion = new ArrayList<CampanaProductoVersionIf>();
								Collection ordenMedioColl = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planMedioReporte.getId());
								
								Iterator ordenMedioCollIt = ordenMedioColl.iterator();						
								while(ordenMedioCollIt.hasNext()){
									OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenMedioCollIt.next();
									Collection ordenMedioDetalleColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
									Iterator ordenMedioDetalleCollIt = ordenMedioDetalleColl.iterator();
									while(ordenMedioDetalleCollIt.hasNext()){
										OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleCollIt.next();
										CampanaProductoVersionIf campanaProductoVersionIf = mapaCampanaProductoVersion.get(ordenMedioDetalleIf.getCampanaProductoVersionId());
										int tmp = 0;
										for(CampanaProductoVersionIf com: listCampanaProductoVersion){
											if(com.getId().compareTo(campanaProductoVersionIf.getId())!=0){
												tmp++;
											}
										}
										if(tmp==listCampanaProductoVersion.size()){
											listCampanaProductoVersion.add(campanaProductoVersionIf);
										}
									}							
								}
								
								for(CampanaProductoVersionIf campanaProductoVersionIf : listCampanaProductoVersion ){
									producto = producto.concat(campanaProductoVersionIf.getNombre());
									producto = producto.concat(", ");
								}									
								
							}else{
								ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(planMedioFacturacionIf.getProductoClienteId());
								
								if(planMedioFacturacionIf.getCampanaProductoVersionId() != null){
									version = mapaCampanaProductoVersion.get(planMedioFacturacionIf.getCampanaProductoVersionId());
									producto = productoClienteIf.getNombre()+ " (" + version + ")";
								}else{
									producto = productoClienteIf.getNombre();
								}
							}						
							
							//facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
							facturaDetalleIf2.setDescripcion("PRODUCTO: "+ producto);
							//facturaDetalleIf3.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
							// VERIFICAR SI ES TV-RADIO-PRENSA
							facturaDetalleIf4.setDescripcion("PAUTA EN LOS SIGUIENTES MEDIOS:\n");
							//listaTmp.add(facturaDetalleIf1);
							listaTmp.add(facturaDetalleIf2);
							//listaTmp.add(facturaDetalleIf3);
							listaTmp.add(facturaDetalleIf4);
						
							
						// VERSION "V"
						}else if(formaFacturacioPMFP.compareTo("V") == 0 ){
							//facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
							version = mapaCampanaProductoVersion.get(planMedioFacturacionIf.getCampanaProductoVersionId());
							facturaDetalleIf2.setDescripcion("VERSION: "+ version.getNombre());
							//facturaDetalleIf3.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
							// VERIFICAR SI ES TV-RADIO-PRENSA
							facturaDetalleIf4.setDescripcion("PAUTA EN LOS SIGUIENTES MEDIOS:\n");
							//listaTmp.add(facturaDetalleIf1);
							listaTmp.add(facturaDetalleIf2);
							//listaTmp.add(facturaDetalleIf3);
							listaTmp.add(facturaDetalleIf4);
						
						// ES POR COMISIONES DEL MEDIO "M"
						}else if(formaFacturacioPMFP.compareTo("M") == 0 ){
							//facturaDetalleIf1.setDescripcion("CAMPAÑA: "+campanaIf.getNombre());
							//facturaDetalleIf2.setDescripcion("PERIODO: DEL " + diaInicio + " AL " + diaFin + " DE " + mes + " DE " + anio);
							facturaDetalleIf3.setDescripcion("COMISIÓN DE AGENCIA POR MUTUO CLIENTE:\n");
							//listaTmp.add(facturaDetalleIf1);
							//listaTmp.add(facturaDetalleIf2);
							listaTmp.add(facturaDetalleIf3);
						}
					}

					rowCollectionTmp = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(idParametro);
					listaTmp.addAll(rowCollectionTmp);
					
					rowCollection = listaTmp;
				}
				catch (Exception e){
					e.printStackTrace();
				}				
			}					
			else if(!isHeader)
				rowCollection = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(idParametro);
			else
				rowCollection = SessionServiceLocator.getFacturaSessionService().findFacturaById(idParametro);
							
			
			Iterator itRows = rowCollection.iterator();

			while (itRows.hasNext()) {
				// No entra si es reembolso porque los detalles en caso de reembolso
				// deben venir de factura detalle
				if (tipoDocumento.getReembolso().equals("N") 
						&& !tipoDocumento.getCodigo().equals("FCO") 
						&& !isHeader && pedido.getReferencia() != null && !pedido.getReferencia().equals("")
						&& tipoReferencia.equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())
						&& (tipoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_NORMAL) || tipoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_CLIENTE))){
						
						//&& (!tipoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_PARCIAL) || mapaPresupuestosDetalleParaFacturar.get(presupuestoDetalle.getId()) != null)){
					
					PresupuestoDetalleIf presupuestoDetalle = (PresupuestoDetalleIf) itRows.next();
					
					// si no es una facturacion parcial se añade todos los detalles
					// si es un facturacion parcial se añaden solo los detalles del mapa
					FacturaDetalleData bean = new FacturaDetalleData();
					
					String concepto = presupuestoDetalle.getConcepto();
											
					if(presupuestoDetalle.getFechaPublicacion() != null){
						String fechaCompleta = Utilitarios.getFechaDiaSemanaDiaMesAnioUppercase(presupuestoDetalle.getFechaPublicacion());
						concepto = concepto + "\n\nPUBLICACIÓN: " + fechaCompleta + "\n";
					}
					
					String proveedor = "";
					String producto = "";
					String publicacion = "";
					if(resumirConcepto){
						if(presupuestoDetalle.getProveedorId() != null){
							ClienteOficinaIf proveedorDetalle = mapaClienteOficina.get(presupuestoDetalle.getProveedorId());
							proveedor = proveedorDetalle.getDescripcion();									
						}
						
						if(presupuestoDetalle.getProductoId() != null){
							ProductoIf productoDetalle = mapaProductoProveedor.get(presupuestoDetalle.getProductoId());
							GenericoIf generico = mapaGenerico.get(productoDetalle.getGenericoId());
							producto = generico.getNombre();
							
							//si el proveedor es null porque el detalle es tipo reporte entonces lo busco a partir del producto
							if(presupuestoDetalle.getProveedorId() == null){
								ClienteOficinaIf proveedorDetalle = mapaClienteOficina.get(productoDetalle.getProveedorId());
								proveedor = proveedorDetalle.getDescripcion();
							}
						}
						
						
						if(presupuestoDetalle.getFechaPublicacion() != null){
							String fechaCompleta = Utilitarios.getFechaDiaSemanaDiaMesAnioUppercase(presupuestoDetalle.getFechaPublicacion());
							publicacion = "PUBLICACIÓN: " + fechaCompleta;
						}
						
						concepto = proveedor + "\n" + producto + "\n" + publicacion + "\n";
					}
					
					bean.setDescripcion(concepto);
					bean.setCantidad(presupuestoDetalle.getCantidad());
					
					BigDecimal precio = presupuestoDetalle.getPrecioventa();
					
					double descuentoEspecial = 0D;
					if(presupuestoDetalle.getPorcentajeDescuentoEspecialVenta() != null){
						descuentoEspecial = precio.doubleValue() * (presupuestoDetalle.getPorcentajeDescuentoEspecialVenta().doubleValue() / 100);
					}
					precio = BigDecimal.valueOf(precio.doubleValue() - descuentoEspecial);				
					
					// Si exite negociacion directa se le resta al
					// precio el porcentaje de negociacion
					if(presupuestoDetalle.getPorcentajeNegociacionDirecta() != null && presupuestoDetalle.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(0)) == 1){
						BigDecimal valorCanje = precio.multiply((presupuestoDetalle.getPorcentajeNegociacionDirecta().divide(new BigDecimal(100))));
						precio = precio.subtract(valorCanje);
					}
					
					// seteo el precio
					bean.setPrecioReal(precio);
					
					bean.setDescuento(presupuestoDetalle.getPorcentajeDescuentoAgenciaVenta());
					if(precio.multiply(presupuestoDetalle.getCantidad()).compareTo(new BigDecimal(0)) == 1){
						bean.setValor(precio.multiply(presupuestoDetalle.getCantidad()));
					}else{
						// para que salga en blanco en el reporte (o sea
						// en la factura)
						bean.setValor(null);
					}
					bean.setIva(presupuestoDetalle.getIva());
					bean.setDescuentoGlobal(presupuestoDetalle.getIva());
					bean.setProductoId(presupuestoDetalle.getProductoId());
					bean.setId(presupuestoDetalle.getId());
					
					if(precio.compareTo(new BigDecimal(0)) == 1)
						reportRows.add(bean);
				}
				
				else if (tipoDocumento.getReembolso().equals("N") && !tipoDocumento.getCodigo().equals("FCO") 
						&& !isHeader && pedido.getReferencia() != null && !pedido.getReferencia().equals("") 
						&& tipoReferencia.equals(TipoReferenciaPedido.PRESUPUESTO.getLetra()) &&
						tipoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_PARCIAL)){
					
					FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
					
					String concepto = bean.getDescripcion();
					String proveedor = "";
					String producto = "";
					String publicacion = "";
					if(resumirConcepto){
						if(bean.getProductoId() != null){	
							ProductoIf productoDetalle = mapaProductoProveedor.get(bean.getProductoId());
							GenericoIf generico = mapaGenerico.get(productoDetalle.getGenericoId());
							producto = generico.getNombre();
							ClienteOficinaIf proveedorDetalle = mapaClienteOficina.get(productoDetalle.getProveedorId());
							proveedor = proveedorDetalle.getDescripcion();
						}						
						
						concepto = proveedor + "\n" + producto + "\n" + publicacion + "\n";
					}
					bean.setDescripcion(concepto);
					
					reportRows.add(bean);
					
				}
				
				else if (tipoDocumento.getReembolso().equals("N") 
						&& !tipoDocumento.getCodigo().equals("FCO") 
						&& !isHeader && pedido.getReferencia() != null && !pedido.getReferencia().equals("")
						&& tipoReferencia.equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra())){
					
					FacturaDetalleIf facturaDetalleIf = (FacturaDetalleIf) itRows.next();
					
					FacturaDetalleData bean = new FacturaDetalleData();				
					
					bean.setDescripcion(facturaDetalleIf.getDescripcion());
					bean.setCantidad(facturaDetalleIf.getCantidad());
					bean.setPrecioReal(facturaDetalleIf.getPrecioReal());
					bean.setDescuento(facturaDetalleIf.getDescuento());
					bean.setValor(facturaDetalleIf.getValor());
					bean.setIva(facturaDetalleIf.getIva());
					bean.setDescuentoGlobal(descuentoPlanMedios);
					bean.setProductoId(facturaDetalleIf.getProductoId());
					bean.setId(facturaDetalleIf.getId());
					reportRows.add(bean);
				}
				else if (tipoDocumento.getReembolso().equals("S") && tipoDocumento.getCodigo().equals("FAR") && !isHeader && pedido.getReferencia() != null && !pedido.getReferencia().equals("")
						&& tipoReferencia.equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra()) && !facturaReembolsoIvaEnDetalles){
					
					FacturaDetalleIf facturaDetalleIf = (FacturaDetalleIf) itRows.next();					
					
					FacturaDetalleData bean = new FacturaDetalleData();

					bean.setDescripcion(facturaDetalleIf.getDescripcion());
					bean.setCantidad(facturaDetalleIf.getCantidad());
					bean.setPrecioReal(facturaDetalleIf.getPrecioReal());
					bean.setDescuento(facturaDetalleIf.getDescuento());
					bean.setValor(facturaDetalleIf.getValor());
					bean.setIva(facturaDetalleIf.getIva());
					bean.setDescuentoGlobal(descuentoPlanMedios);
					bean.setProductoId(facturaDetalleIf.getProductoId());
					bean.setId(facturaDetalleIf.getId());
					//GenericoIf generico = (GenericoIf) SessionServiceLocator.getGenericoSessionService().findGenericoByProductoId(facturaDetalleIf.getProductoId()).iterator().next();
					ProductoIf producto = mapaProductoProveedor.get(facturaDetalleIf.getProductoId());
					GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
					
					if (generico.getCobraIva().equals("S")) {
						BigDecimal ivaPorcentaje = BigDecimal.valueOf(Parametros.IVA).divide(new BigDecimal(100));
						Double valor = facturaDetalleIf.getValor().doubleValue() / (new BigDecimal(1).add(ivaPorcentaje)).doubleValue();
						Double iva = BigDecimal.valueOf(valor).multiply(ivaPorcentaje).doubleValue();
						bean.setValor(BigDecimal.valueOf(valor));
						bean.setIva(BigDecimal.valueOf(iva));
						ivaFinalReporte += BigDecimal.valueOf(valor).multiply(ivaPorcentaje).doubleValue();
					} else {
						BigDecimal ivaPorcentaje = BigDecimal.valueOf(Parametros.IVA).divide(new BigDecimal(100));
						Double valor = facturaDetalleIf.getValor().doubleValue() / (new BigDecimal(1).add(ivaPorcentaje)).doubleValue();
						Double iva = BigDecimal.valueOf(valor).multiply(ivaPorcentaje).doubleValue();
						bean.setValor(BigDecimal.valueOf(valor + iva));
						bean.setIva(BigDecimal.ZERO);
					}
					subtotalFinalReporte += bean.getValor().doubleValue();
					bean.setComprasReembolsoAsociadas(facturaDetalleIf.getComprasReembolsoAsociadas());
					reportRows.add(bean);				
				}
				
				else if (tipoDocumento.getReembolso().equals("S") && tipoDocumento.getCodigo().equals("FAR") && !isHeader /*
																															 * &&
																										 * !codigoReferencia.equals("") &&
																															 * tipoReferencia.equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())
																															 */ && facturaReembolsoIvaEnDetalles){				
					FacturaDetalleIf facturaDetalleIf = (FacturaDetalleIf) itRows.next();
					
					FacturaDetalleIvaIncluidoData bean = new FacturaDetalleIvaIncluidoData();				
					
					// se setea vacio porque lo unico que se necesita son los
					// datos de proveedor
					// y esos datos vienen de un mapa que se envia al reporte
					bean.setDescripcion("");
					
					// iva = 0.12
					BigDecimal ivaPorcentaje = BigDecimal.valueOf(Parametros.IVA).divide(new BigDecimal(100));
					BigDecimal valorTotalDetalle = facturaDetalleIf.getValor();
					// subtotal = total / 1.12
					
					//GenericoIf generico = (GenericoIf) SessionServiceLocator.getGenericoSessionService().findGenericoByProductoId(facturaDetalleIf.getProductoId()).iterator().next();
					ProductoIf producto = mapaProductoProveedor.get(facturaDetalleIf.getProductoId());
					GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
					
					double valorSubtotal = 0D;
					BigDecimal valorSubTotalDetalle = BigDecimal.ZERO;
					BigDecimal ivaSubTotalDetalle = BigDecimal.ZERO;
					if (generico.getCobraIva().equals("S")) {
						valorSubtotal = facturaDetalleIf.getValor().doubleValue() / (new BigDecimal(1).add(ivaPorcentaje)).doubleValue();
						valorSubTotalDetalle = BigDecimal.valueOf(valorSubtotal);
						ivaSubTotalDetalle = valorSubTotalDetalle.multiply(ivaPorcentaje);
					} else {
						valorSubtotal = facturaDetalleIf.getValor().doubleValue();
						valorSubTotalDetalle = BigDecimal.valueOf(valorSubtotal);
					}
					
					String subtotal = formatoDecimal.format(valorSubTotalDetalle);
					String iva = formatoDecimal.format(ivaSubTotalDetalle);
					String total = formatoDecimal.format(valorTotalDetalle);
					
					// bean.setValores(valorSubTotalDetalleFormato + "\n" +
					// ivaSubTotalDetalleFormato + "\n__________\n" +
					// valorTotalDetalleFormato);
					bean.setSubtotal(subtotal);
					bean.setIva(iva);
					bean.setTotal(total);			
					bean.setId(facturaDetalleIf.getId());
					reportRows.add(bean);					
				}
				
				// para todos los demas casos como reembolso de presupuesto que
				// no tiene sap.
				else if (!isHeader) {
					FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
					
					//GenericoIf generico = (GenericoIf) SessionServiceLocator.getGenericoSessionService().findGenericoByProductoId(bean.getProductoId()).iterator().next();
					ProductoIf producto = mapaProductoProveedor.get(bean.getProductoId());
					GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
					
					if (generico.getCobraIva().equals("S")) {
						BigDecimal ivaPorcentaje = BigDecimal.valueOf(Parametros.IVA).divide(new BigDecimal(100));
						Double valor = bean.getValor().doubleValue() / (new BigDecimal(1).add(ivaPorcentaje)).doubleValue();
						Double iva = BigDecimal.valueOf(valor).multiply(ivaPorcentaje).doubleValue();
						if (!facturaReembolsoNormal && tipoDocumento.getReembolso().equals("S"))
							bean.setValor(BigDecimal.valueOf(valor));
						else
							bean.setValor(BigDecimal.valueOf(valor + iva));
						ivaFinalReporte += BigDecimal.valueOf(valor).multiply(ivaPorcentaje).doubleValue();
						subtotalFinalReporte += valor;
					}
					reportRows.add(bean);
				} else {
					FacturaIf bean = (FacturaIf) itRows.next();
					reportRows.add(bean);
				}
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}		

		return reportRows;
	}
	
	/*
	 * Metodo getProveedoresMap llamado por report()
	 */
	private Map getProveedoresMap(FacturaIf factura) {
		Map proveedoresMap = new HashMap();
		Collection rowCollection = null;
		try {
			rowCollection = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(factura.getId());
			Iterator itRows = rowCollection.iterator();
			TipoDocumentoIf tipoDocumentoFactura = mapaTipoDocumento.get(factura.getTipodocumentoId());
			while (itRows.hasNext()) {
				FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
				ProductoIf producto = mapaProductoProveedor.get(bean.getProductoId());
				ClienteOficinaIf proveedorOficina = mapaClienteOficina.get(producto.getProveedorId());
				String facturaReembolso = "";
				String facturaComision = "";
				if (tipoDocumentoFactura.getCodigo().equals("FAR")) {
					Iterator<CompraIf> it = SessionServiceLocator.getCompraSessionService().findCompraByFacturaDetalleId(bean.getId()).iterator();
					while (it.hasNext()) {
						CompraIf compraReembolso = it.next();
						if (!facturaReembolso.equals(""))
							facturaReembolso += ", ";
						else
							facturaReembolso += "F: ";
						facturaReembolso += compraReembolso.getPreimpreso();
					}
				}
				ClienteIf proveedor = mapaCliente.get(proveedorOficina.getClienteId());
				TipoIdentificacionIf tipoIdentificacion = mapaTipoIdentificacion.get(proveedor.getTipoidentificacionId());
				if(tipoDocumentoFactura.getCodigo().equals("FCO")){
					proveedoresMap.put(bean.getId(), facturaComision);
				}else{
					proveedoresMap.put(bean.getId(), proveedor.getRazonSocial() + "\n" + tipoIdentificacion.getNombre() + ": " + proveedor.getIdentificacion() + "\n" + facturaReembolso);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}

		return proveedoresMap;
	}
	
	/*
	 * Metodo getProductosMap llamado por report()
	 */
	private Map getProductosMap(FacturaIf factura) {
		Map productosMap = new HashMap();
		Collection rowCollection = null;
		try {
			rowCollection = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(factura.getId());
			Iterator itRows = rowCollection.iterator();
			while (itRows.hasNext()) {
				FacturaDetalleIf bean = (FacturaDetalleIf) itRows.next();
				ProductoIf producto = mapaProductoProveedor.get(bean.getProductoId());
				String codigo = (producto.getCodigoBarras()!=null && !producto.getCodigoBarras().trim().equals(""))?producto.getCodigoBarras():producto.getCodigo();
				productosMap.put(bean.getId(), codigo);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}

		return productosMap;
	}

	/*
	 * Metodo savePedido llamado por save()
	 */
	private boolean savePedido() throws GenericBusinessException {
		PedidoIf pedido = registrarPedido();
		cargandoDetallesReferencia = true;
		if (verificarDetallesPedido(pedidoDetalleColeccion)) {
			
			if (!tipoDocumentoIf.getCodigo().equals("FCO"))
				actualizarTotales(pedidoDetalleColeccion, true);
			
			idPedidoGuardado = SessionServiceLocator.getPedidoSessionService().procesarPedido(pedido, pedidoDetalleColeccion, Parametros.getIdEmpresa(),tarea);
		
			// Para guardar cambios en el reporte de presupuesto detalle
			/*if(!tipoDocumentoIf.getCodigo().equals("FCO") && idPedidoGuardado != null && 
				pedido.getTiporeferencia().equals(TipoReferenciaPedido.PRESUPUESTO.getLetra()) && 
				pedido.getReferencia() != null){
				for(PresupuestoDetalleIf presupuestoDetalle : presupuestoDetalleColeccionP){
					SessionServiceLocator.getPresupuestoDetalleSessionService().savePresupuestoDetalle(presupuestoDetalle);
				}				
			}*/
			
		} else {
			cargandoDetallesReferencia = false;
			return false;
		}
		
		cargandoDetallesReferencia = false;
		return true;
	}

	/*
	 * Metodo verificarDetallesPedido llamado por getProveedoresMap()
	 */
	public boolean verificarDetallesPedido(Vector PedidoDetalleColeccion) {
		Iterator detallesIterator = PedidoDetalleColeccion.iterator();
		int i = 0;
		
		while (detallesIterator.hasNext()) {
			PedidoDetalleIf pedidoDetalle = (PedidoDetalleIf) detallesIterator.next();
			getCmbDocumento().setSelectedItem(null);
			setPedidoDetalle(pedidoDetalle);
			if (!validateFieldsDetalle()) {
				getTblPedidoDetalle().getSelectionModel().setSelectionInterval(i, i);
				return false;
			}			
			i++;
		}		
		return true;
	}
	
	public boolean actualizarTotales(Vector PedidoDetalleColeccion, boolean encerarComisionTotal) {
		Iterator detallesIterator = PedidoDetalleColeccion.iterator();
		double descuentoGlobalValor = 0D;
		double descuentoGlobalPorcentaje = 0D;
		int i = 0;
		
		if (getRbDescuentoGlobalPorcentaje().isSelected()) {
			if (!getTxtDescuentoGlobalPorcentaje().getText().equals("")) {
				descuentoGlobalPorcentaje = Double.valueOf(Utilitarios.removeDecimalFormat(getTxtDescuentoGlobalPorcentaje().getText()));
				if (descuentoGlobalPorcentaje <= 100D) {
					descuentoGlobalValor =  (subTotal * descuentoGlobalPorcentaje) / 100;
					getTxtDescuentoGlobalValor().setText(String.valueOf(descuentoGlobalValor));
				} else {
					descuentoGlobalValor = 0D;
					descuentoGlobalPorcentaje = 0D;
					getTxtDescuentoGlobalValor().setText("0");
					getTxtDescuentoGlobalPorcentaje().setText("0");
					SpiritAlert.createAlert("No se ha podido aplicar el descuento.\nEl porcentaje de descuento global no debe exceder el 100%", SpiritAlert.WARNING);
				}
			} else
				getTxtDescuentoGlobalPorcentaje().setText("0");
		} else if (getRbDescuentoGlobalValor().isSelected()) {
			if (!getTxtDescuentoGlobalValor().getText().equals("")) {
				descuentoGlobalValor = Double.valueOf(Utilitarios.removeDecimalFormat(getTxtDescuentoGlobalValor().getText()));
				if (descuentoGlobalValor <= subTotal) {
					if (descuentoGlobalValor > 0D)
						descuentoGlobalPorcentaje =  (descuentoGlobalValor * 100) / subTotal;
					getTxtDescuentoGlobalPorcentaje().setText(String.valueOf(descuentoGlobalPorcentaje));
				} else {
					descuentoGlobalValor = 0D;
					descuentoGlobalPorcentaje = 0D;
					getTxtDescuentoGlobalValor().setText("0");
					getTxtDescuentoGlobalPorcentaje().setText("0");
					SpiritAlert.createAlert("No se ha podido aplicar el descuento.\nEl valor del descuento global no debe exceder el subtotal", SpiritAlert.WARNING);
				}
			} else
				getTxtDescuentoGlobalValor().setText("0");
		}
					
		subTotal = 0D;
		totalIvaCero = 0D;
		
		if (encerarComisionTotal){
			comisionAgenciaTotal = 0D;
		}
		
		while (detallesIterator.hasNext()) {
			getCmbDocumento().setSelectedItem(null);
			PedidoDetalleIf pedidoDetalle = (PedidoDetalleIf) detallesIterator.next();
			getTblPedidoDetalle().getSelectionModel().setSelectionInterval(i,i);
			setPedidoDetalle(pedidoDetalle);
			if (getTblPedidoDetalle().getRowCount() > i)
				actualizarDetallePedido(true);
			i++;
		}
		
		getTxtDescuentoGlobalValor().setText(String.valueOf(this.descuentoGlobalTotal));
		return true;
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if (getCmbTipoDocumento().getSelectedItem() != null)
			aMap.put("tipodocumentoId", ((TipoDocumentoIf)getCmbTipoDocumento().getSelectedItem()).getId());	
		
		if ("".equals(getTxtCodigo().getText()) == false)
			aMap.put("codigo", getTxtCodigo().getText()+"%");
		
		if ("".equals(getTxtContacto().getText()) == false)
			aMap.put("contacto", getTxtContacto().getText()+"%");
		
		EstadoPedido estado = (EstadoPedido) getCmbEstado().getSelectedItem();
		if ( estado != null )
			aMap.put("estado", estado.getLetra());
		/*
		 * if (getCmbEstado().getSelectedItem() != null) { if
		 * (NOMBRE_ESTADO_COMPLETO.equals(getCmbEstado().getSelectedItem().toString()))
		 * aMap.put("estado", ESTADO_COMPLETO); else if
		 * (NOMBRE_ESTADO_INCOMPLETO.equals(getCmbEstado().getSelectedItem().toString()))
		 * aMap.put("estado", ESTADO_INCOMPLETO); else if
		 * (NOMBRE_ESTADO_PENDIENTE.equals(getCmbEstado().getSelectedItem().toString()))
		 * aMap.put("estado", ESTADO_PENDIENTE); else if
		 * (NOMBRE_ESTADO_ANULADO.equals(getCmbEstado().getSelectedItem().toString()))
		 * aMap.put("estado", ESTADO_ANULADO); else if
		 * (NOMBRE_ESTADO_COTIZACION.equals(getCmbEstado().getSelectedItem().toString()))
		 * aMap.put("estado", ESTADO_COTIZACION); }
		 */
		
		if (getCmbTipoReferencia().getSelectedItem() != null)
			aMap.put("tiporeferencia", getCmbTipoReferencia().getSelectedItem().toString().substring(0, 1));
		
		if (fechaPedidoSeleccionada())
			aMap.put("fechaPedido", new java.sql.Date(getCmbFechaPedido().getDate().getYear(),getCmbFechaPedido().getDate().getMonth(),getCmbFechaPedido().getDate().getDate()));
		
		if (clienteOficinaIf != null)
			aMap.put("clienteoficinaId", clienteOficinaIf.getId());
		
		if (getCmbVendedor().getSelectedItem() != null)
			aMap.put("vendedorId", ((EmpleadoIf)getCmbVendedor().getSelectedItem()).getId());
		
		return aMap;
	}
	
	public void find() {
		try {
			Map mapa = buildQuery();
			int tamanoLista = SessionServiceLocator.getPedidoSessionService().getPedidoListSize(mapa, Parametros.getIdEmpresa());
			if (tamanoLista > 0) {
				PedidoCriteria pedidoCriteria = new PedidoCriteria();
				pedidoCriteria.setResultListSize(tamanoLista);
				System.out.println("Tamaño de lista: " + tamanoLista);
				pedidoCriteria.setQueryBuilded(mapa);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), pedidoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if (popupFinder.getElementoSeleccionado() != null)
					pedido = (PedidoIf) popupFinder.getElementoSeleccionado();
					if(pedido != null){
						getSelectedObject();
					}				
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
				if (getMode() == SpiritMode.FIND_MODE) {
					this.getCmbFechaPedido().setSelectedItem(null);
					// this.getCmbEstado().setSelectedItem(null);
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		}
	}
	
	private Map buildQueryCompra() {
		Map aMap = new HashMap();
		Long proveedorId = 0L;
		ClienteOficinaIf proveedor = null;
		try {
			if ((productoIf != null) && (productoIf.getProveedorId() != null)) {
				proveedor = (ClienteOficinaIf) mapaClienteOficina.get(productoIf.getProveedorId());
			} else
				getTxtProveedor().setText("");
			
			if (proveedor != null) {
				proveedorId = proveedor.getId();
				aMap.put("proveedorId", proveedorId);
			}
			
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo("COR").iterator().next();
			aMap.put("tipodocumentoId", tipoDocumento.getId());
			aMap.put("estado", "A");
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return aMap;
	}
	 
	public void findCompra() {
		try {
			Map mapa = buildQueryCompra();
			int tamanoLista = SessionServiceLocator.getCompraSessionService().getCompraByQueryListSize(mapa, Parametros.getIdEmpresa());
			if (tamanoLista > 0) {
				CompraCriteria compraCriteria = new CompraCriteria(false);
				compraCriteria.setResultListSize(tamanoLista);
				compraCriteria.setQueryBuilded(mapa);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(60);
				anchoColumnas.add(100);
				anchoColumnas.add(150);
				anchoColumnas.add(60);
				anchoColumnas.add(90);
				anchoColumnas.add(50);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), compraCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null)
					getTxtFacturaProveedor().setText(((CompraIf) popupFinder.getElementoSeleccionado()).getPreimpreso());
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		} catch (Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en la búsqueda de información", SpiritAlert.ERROR);
		}
	} 
	 
	 
	private boolean fechaPedidoSeleccionada() {
		return getCmbFechaPedido().getSelectedItem() != null;
	}

	private ArrayList getModel(ArrayList listaFactura) {
		ArrayList data = new ArrayList();
		Iterator it = listaFactura.iterator();
		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			PedidoIf bean = (PedidoIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(bean.getContacto());
			String estadoLetra = bean.getEstado();
			try {
				EstadoPedido estado = (EstadoPedido) EstadoPedido.getEstadoOrdenTrabajoByLetra(estadoLetra);
				fila.add(estado.getLetra());
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				fila.add("");
			}
			/*
			 * if (ESTADO_PENDIENTE.equals(bean.getEstado()))
			 * fila.add(NOMBRE_ESTADO_PENDIENTE); else if
			 * (ESTADO_COMPLETO.equals(bean.getEstado()))
			 * fila.add(NOMBRE_ESTADO_COMPLETO); else if
			 * (ESTADO_INCOMPLETO.equals(bean.getEstado()))
			 * fila.add(NOMBRE_ESTADO_INCOMPLETO); else if
			 * (ESTADO_COTIZACION.equals(bean.getEstado()))
			 * fila.add(NOMBRE_ESTADO_COTIZACION); else if
			 * (ESTADO_ANULADO.equals(bean.getEstado()))
			 * fila.add(NOMBRE_ESTADO_ANULADO);
			 */
			data.add(fila);
		}
		
		return data;
	}

	private void inicializarPopup() {
		if (activarVisorDetallePersonalizacion.equals("S")) {
			itemVerPersonalizacionPedidoDetalle = new JMenuItem("Ver detalle personalización");
			itemVerPersonalizacionPedidoDetalle.addActionListener(oActionListenerVerDetallePersonalizacion);
			popupMenuPedidoDetalle.add(itemVerPersonalizacionPedidoDetalle);
		}
		itemEliminarPedidoDetalle = new JMenuItem("Eliminar");
		itemEliminarPedidoDetalle.addActionListener(oActionListenerEliminarPedidoDetalle);
		popupMenuPedidoDetalle.add(itemEliminarPedidoDetalle);
		this.getTblPedidoDetalle().addMouseListener(oMouseAdapterTblFacturaDetalle);
		this.getTblPedidoDetalle().addKeyListener(oKeyAdapterTblFacturaDetalle);
	}

	private void initKeyListeners() {
		getLblArchivoAdjunto().setVisible(false);
		getTxtArchivoAdjunto().setVisible(false);
		getBtnVerArchivoAdjunto().setVisible(false);
		
		getCmbFechaPedido().setLocale(Utilitarios.esLocale);
		getCmbFechaVencimiento().setLocale(Utilitarios.esLocale);
		getTxtDiasValidez().addKeyListener(new TextChecker(MAX_LONGITUD_DIAS_VALIDEZ));
		getTxtDiasValidez().addKeyListener(new NumberTextField());
		getTxtAutorizacionSAP().addKeyListener(new TextChecker(MAX_LONGITUD_AUTORIZACION_SAP));
		getTxtObservacion().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACION));
		getTxtIdentificacion().addKeyListener(new TextChecker(MAX_LONGITUD_IDENTIFICACION));
		getTxtContacto().addKeyListener(new TextChecker(MAX_LONGITUD_CONTACTO));
		getTxtTelefono().addKeyListener(new TextChecker(MAX_LONGITUD_TELEFONO));
		getTxtDireccion().addKeyListener(new TextChecker(MAX_LONGITUD_DIRECCION));
		getTxtReferencia().addKeyListener(new TextChecker(MAX_LONGITUD_REFERENCIA));
		getTxtDescuentoGlobalPorcentaje().addKeyListener(new TextChecker(MAX_LONGITUD_DESCUENTO));
		getTxtDescuentoGlobalPorcentaje().addKeyListener(new NumberTextFieldDecimal());
		getTxtDescuentoGlobalValor().addKeyListener(new TextChecker(MAX_LONGITUD_DESCUENTO));
		getTxtDescuentoGlobalValor().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeComision().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_COMISION));
		getTxtPorcentajeComision().addKeyListener(new NumberTextFieldDecimal());
		getTxtDescripcion().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION, true));
		getTxtCantidad().addKeyListener(new TextChecker(MAX_LONGITUD_CANTIDAD));
		getTxtCantidad().addKeyListener(new NumberTextField());
		getTxtCantidadPedida().addKeyListener(new TextChecker(MAX_LONGITUD_CANTIDAD));
		getTxtCantidadPedida().addKeyListener(new NumberTextField());
		getTxtPrecio().addKeyListener(new TextChecker(MAX_LONGITUD_CANTIDAD));
		getTxtPrecio().addKeyListener(new NumberTextFieldDecimal());
		getTxtPrecioReal().addKeyListener(new TextChecker(MAX_LONGITUD_CANTIDAD));
		getTxtPrecioReal().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeDescuentoAgencia().addKeyListener(new TextChecker(MAX_LONGITUD_DESCUENTO));
		getTxtPorcentajeDescuentoAgencia().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeDescuentosVarios().addKeyListener(new TextChecker(MAX_LONGITUD_DESCUENTO));
		getTxtPorcentajeDescuentosVarios().addKeyListener(new NumberTextFieldDecimal());
		getCmbFechaPedido().setShowNoneButton(false);
		getTxtProveedor().setEditable(false);
		getTxtFacturaProveedor().setEditable(false);
		getTxtFacturaProveedor().addKeyListener(new TextChecker(MAX_LONGITUD_PREIMPRESO));
		getBtnFacturaProveedor().setEnabled(false);
		getTxtDsctoCompraPorcentaje().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_COMISION));
		getTxtDsctoCompraPorcentaje().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeNegociacionDirecta().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_COMISION));
		getTxtPorcentajeNegociacionDirecta().addKeyListener(new NumberTextFieldDecimal());
	}
	
	ActionListener oActionListenerBtnFacturaProveedor = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			showJDAsociarFacturaProveedor();
		}
	};
	
	private void showJDAsociarFacturaProveedor() {
		if (purchaseDataVector == SpiritConstants.getNullValue() || purchaseDataVector.size() == 0)
			purchaseDataVector = new Vector<PurchaseData>();
		AsociarCompraModel acm = new AsociarCompraModel(Parametros.getMainFrame(), purchaseDataVector, proveedorIf);
		purchaseDataVector = acm.getAssociatedPurchasesDataVector();
		String facturasProveedor = SpiritConstants.getEmptyCharacter();
		for (int i=0; i<purchaseDataVector.size(); i++) {
			PurchaseData purchaseData = purchaseDataVector.get(i);
			if (!facturasProveedor.equals(SpiritConstants.getEmptyCharacter()))
				facturasProveedor += ", ";
			facturasProveedor += purchaseData.getPurchase().getPreimpreso();
		}
		getTxtFacturaProveedor().setText(facturasProveedor);
	}
	
	ActionListener oActionListenerBtnNuevoCliente = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			clienteIf = NuevoCliente();
			setCliente(false);
		}
	};

	private void initListeners() {
		addActionListeners();
		getBtnAgregarDetalle().addActionListener(new AgregarDetalleListener());
		getBtnActualizarDetalle().addActionListener(new ActualizarDetalleListener());
		getCmbTipoReferencia().addActionListener(oActionListenerCmbTipoReferencia);
		
		// Selecciona el Presupuesto para cotizar(creo) o Plan de Medio de
		// Referencia para facturar(segura)
		getBtnEscojaReferencia().addActionListener(oActionListenerBtnEscojaReferencia);
		
		getBtnGenerarFactura().addActionListener(oActionListenerGenerarFactura);
		getBtnActualizarTotales().addActionListener(oActionListenerActualizarTotales);
		getBtnFacturaProveedor().addActionListener(oActionListenerBtnFacturaProveedor);
		getBtnNuevoCliente().addActionListener(oActionListenerBtnNuevoCliente);
		getBtnActualizarDetalleP().addActionListener(oActionListenerBtnActualizarPresupuestoDetalleP);
		getCmbTipoDocumento().addActionListener(oActionListenerCmbTipoReferencia);
		getCmbVendedor().addActionListener(oActionListenerCmbEjecutivo);
		
		getBtnEliminarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetallePedido();
			}
		});
	 		
		getBtnBuscarProducto().addActionListener(new BuscarProductoListener());
		
		getBtnEncerarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				corporacionIf = null;
				clienteIf = null;
				clienteOficinaIf = null;
				getTxtCorporacion().setText("");
				getTxtCliente().setText("");
				getTxtInformacionAdc().setText("");
				getTxtClienteOficina().setText("");
				getTxtIdentificacion().setText("");				
				getTxtContacto().setText("");
				getTxtTelefono().setText("");
				getTxtDireccion().setText("");
				// getCmbTipoReferencia().setSelectedItem(NOMBRE_REFERENCIA_NINGUNO);
				getCmbTipoReferencia().setSelectedItem(TipoReferenciaPedido.NINGUNO);
				getCmbTipoReferencia().setEnabled(false);
			}
		});

		getBtnBuscarCorporacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(500);				
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null ){
					corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
					getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
					clienteIf = null;
					clienteOficinaIf = null;
					getTxtCliente().setText("");
					getTxtInformacionAdc().setText("");
					getTxtClienteOficina().setText("");
					getTxtIdentificacion().setText("");
					getTxtContacto().setText("");
					getTxtTelefono().setText("");
					getTxtDireccion().setText("");
					// getCmbTipoReferencia().setSelectedItem(NOMBRE_REFERENCIA_NINGUNO);
					getCmbTipoReferencia().setSelectedItem(TipoReferenciaPedido.NINGUNO);
					getCmbTipoReferencia().setEnabled(false);
				}
			}
		});

		getBtnBuscarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				if (corporacionIf != null)
					idCorporacion = corporacionIf.getId();

				clienteCriteria = new ClienteCriteria("Clientes", idCorporacion, CODIGO_TIPO_CLIENTE);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null ){
					clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
					setCliente(false);
				}
			}
		});

		getBtnBuscarClienteOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tituloVentanaBusqueda = "Oficinas del Cliente";
				
				if (corporacionIf != null)
					idCorporacion = corporacionIf.getId();
				
				if (clienteIf != null)
					idCliente = clienteIf.getId();
				
				clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_CLIENTE, "", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					seleccionarClienteOficina();
				}
			}			
		});
		
		getRbDescuentoGlobalPorcentaje().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				TipoReferenciaPedido tr = (TipoReferenciaPedido) getCmbTipoReferencia().getSelectedItem();
				// if (getRbDescuentoGlobalPorcentaje().isSelected() &&
				// NOMBRE_REFERENCIA_NINGUNO.equals(getCmbTipoReferencia().getSelectedItem()))
				// {
					if (getRbDescuentoGlobalPorcentaje().isSelected() && 
						TipoReferenciaPedido.NINGUNO == tr ) {
					getTxtDescuentoGlobalPorcentaje().setEditable(true);
					getTxtDescuentoGlobalPorcentaje().setEnabled(true);
					getTxtDescuentoGlobalValor().setEditable(false);
					getTxtDescuentoGlobalValor().setEnabled(false);
					getTxtDescuentoGlobalValor().setText("0");
				}
			}
		});
		
		getRbDescuentoGlobalValor().addChangeListener(new ChangeListener() {
			TipoReferenciaPedido tr = (TipoReferenciaPedido) getCmbTipoReferencia().getSelectedItem();
			
			public void stateChanged(ChangeEvent e) {
				// if (getRbDescuentoGlobalValor().isSelected() &&
				// NOMBRE_REFERENCIA_NINGUNO.equals(getCmbTipoReferencia().getSelectedItem()))
				// {
				if (getRbDescuentoGlobalValor().isSelected() && 
					TipoReferenciaPedido.NINGUNO == tr ) {
					getTxtDescuentoGlobalPorcentaje().setEditable(false);
					getTxtDescuentoGlobalPorcentaje().setEnabled(false);
					getTxtDescuentoGlobalValor().setEditable(true);
					getTxtDescuentoGlobalValor().setEnabled(true);
					getTxtDescuentoGlobalPorcentaje().setText("0");
				}
			}
		});
		
		getTxtPorcentajeComision().addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				TipoReferenciaPedido tr = (TipoReferenciaPedido) getCmbTipoReferencia().getSelectedItem(); 
				if (tipoDocumentoIf!=null && !tipoDocumentoIf.getCodigo().equals("FCO") && TipoReferenciaPedido.PLAN_MEDIOS != tr){
					calcularComision();
					getTxtPorcentajeComision().grabFocus();
				}
			}
		});
		
		getTxtPrecio().addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				getTxtPrecioReal().setText(getTxtPrecio().getText());
				getTxtPrecio().grabFocus();
			}
		});
		
		getBtnInformacionEnvio().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				mostrarInformacionEnvio();
			}
		});
		
		getBtnMostrarPanelNegociacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (getJpNegociacion().isVisible() && !getCbNegociacionDirecta().isSelected() && !getCbComisionPura().isSelected()) {
					getJpNegociacion().setVisible(false);
					getBtnMostrarPanelNegociacion().setText("Negociación | >>");
				} else {
					getJpNegociacion().setVisible(true);
					getBtnMostrarPanelNegociacion().setText("Negociación | <<");
				}
			}			
		});
		
		getBtnBuscarClienteNegociacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tituloVentanaBusqueda = "Oficinas del Cliente";
				
				clienteOficinaNegociacionCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_CLIENTE, "", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaNegociacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					clienteOficinaNegociacionIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					getTxtClienteNegociacion().setText(clienteOficinaNegociacionIf.getCodigo() + " - " + clienteOficinaNegociacionIf.getDescripcion());
				}
			}			
		});
		
		getCbNegociacionDirecta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbNegociacionDirecta().isSelected()){
					getTxtPorcentajeNegociacionDirecta().setEditable(true);
					getTxtPorcentajeNegociacionDirecta().setText("0");					
					getTxtDsctoCompraPorcentaje().setEditable(true);
					getTxtClienteNegociacion().setEditable(true);
					getBtnBuscarClienteNegociacion().setEnabled(true);
					getCbComisionPura().setSelected(false);
				}else if(!getCbComisionPura().isSelected()){
					getTxtPorcentajeNegociacionDirecta().setEditable(false);
					getTxtPorcentajeNegociacionDirecta().setText("0");					
					getTxtDsctoCompraPorcentaje().setEditable(false);
					getTxtClienteNegociacion().setEditable(false);
					getBtnBuscarClienteNegociacion().setEnabled(false);
				}else{
					getTxtPorcentajeNegociacionDirecta().setEditable(false);
					getTxtPorcentajeNegociacionDirecta().setText("0");
				}
			}
		});
		
		getCbComisionPura().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbComisionPura().isSelected()){
					getTxtDsctoCompraPorcentaje().setEditable(true);
					getTxtClienteNegociacion().setEditable(true);
					getBtnBuscarClienteNegociacion().setEnabled(true);
					getCbNegociacionDirecta().setSelected(false);
					getTxtPorcentajeNegociacionDirecta().setEditable(false);
					getTxtPorcentajeNegociacionDirecta().setText("0");
				}else if(!getCbNegociacionDirecta().isSelected()){
					getTxtDsctoCompraPorcentaje().setEditable(false);
					getTxtClienteNegociacion().setEditable(false);
					getBtnBuscarClienteNegociacion().setEnabled(false);
				}
			}
		});
	}
	
	ActionListener oActionListenerCmbEjecutivo = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			try {
				if(getCmbVendedor().getSelectedItem() != null){
					EmpleadoIf ejecutivo = (EmpleadoIf)getCmbVendedor().getSelectedItem();
					// busco en que equipos esta el ejecutivo
					Collection equiposEjecutivo = SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByEmpleadoId(ejecutivo.getId());
					Iterator equiposEjecutivoIt = equiposEjecutivo.iterator();
					HashMap directorEquipoMap = new HashMap();
					while(equiposEjecutivoIt.hasNext()){
						EquipoEmpleadoIf ejecutivoEquipo = (EquipoEmpleadoIf)equiposEjecutivoIt.next();
						// armo un listado de todos los directores del ejecutivo
						// (uno por equipo)
						HashMap buscarDirectorMap = new HashMap();
						buscarDirectorMap.put("equipoId", ejecutivoEquipo.getEquipoId());
						buscarDirectorMap.put("rol", "DCU");
						Collection directorEquipos = SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByQuery(buscarDirectorMap);
						if(directorEquipos.size() > 0){
							EquipoEmpleadoIf directorEquipo = (EquipoEmpleadoIf)directorEquipos.iterator().next();
							EmpleadoIf director = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(directorEquipo.getEmpleadoId());
							EquipoTrabajoIf equipoTrabajoDirector = SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajo(directorEquipo.getEquipoId());
							directorEquipoMap.put(equipoTrabajoDirector.getCodigo(), director);
						}						
					}
					
					getCmbDirectorCuentas().removeAllItems();
					Iterator directorEquipoMapIt = directorEquipoMap.keySet().iterator();
					while(directorEquipoMapIt.hasNext()){
						String equipoCodigo = (String)directorEquipoMapIt.next();
						EmpleadoIf director = (EmpleadoIf)directorEquipoMap.get(equipoCodigo);
						getCmbDirectorCuentas().addItem(equipoCodigo + " - " + director);
					}					
				}				
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}
	};
	
	private void seleccionarClienteOficina() {
		if (clienteIf == null) {
			try {
				clienteIf = mapaCliente.get(clienteOficinaIf.getClienteId());
				getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
				
				if(clienteIf.getInformacionAdc() != null && !clienteIf.getInformacionAdc().equals("")){
					SpiritAlert.createAlert("INFORMACIÓN: \n" + clienteIf.getInformacionAdc(),SpiritAlert.INFORMATION);
					getTxtInformacionAdc().setText(clienteIf.getInformacionAdc());
				}
				
				if (corporacionIf == null) {
					corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
					getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
		setClienteOficina();
	}
	
	private void mostrarInformacionEnvio(){
		InformacionEnvioModel iem = new InformacionEnvioModel(Parametros.getMainFrame(),pedidoEnvioIf);
		iem = null;
	}
	
	private void calcularComision() {
		double porcentajeComision = 0D;
		
		if (!getTxtPorcentajeComision().getText().equals(""))
			porcentajeComision = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeComision().getText()));
		
		if (porcentajeComision != porcentajeComisionAgencia) {
			if(getCbMultipleFacturacion().isSelected()){
				double subtotal = (subTotal - descuentoTotal - descuentosVariosTotal - descuentoGlobalTotal);
				double comisionAgenciaPor100 = comisionAgenciaTotal * 100D;
				if(subtotal > 0D){
					porcentajeComisionAgencia = comisionAgenciaPor100 / subtotal;
					//getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
				}				
			}else{
				porcentajeComisionAgencia = porcentajeComision;
				comisionAgenciaTotal =  ((subTotal - descuentoTotal - descuentosVariosTotal - descuentoGlobalTotal) * porcentajeComisionAgencia) / 100D;
			}
			
			if (getTblPedidoDetalle().getRowCount() > 0) {
				actualizarTotales(pedidoDetalleColeccion, false);
				getTblPedidoDetalle().getSelectionModel().removeSelectionInterval(getTblPedidoDetalle().getRowCount() - 1, getTblPedidoDetalle().getRowCount() -1);
			}
			
			getTxtPorcentajeComision().grabFocus();
		}
	}
	
	private void setCliente(boolean desdePresupuesto) {
		if(clienteIf != null){
			getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
			
			if(clienteIf.getInformacionAdc() != null && !clienteIf.getInformacionAdc().equals("")){
				SpiritAlert.createAlert("INFORMACIÓN: \n" + clienteIf.getInformacionAdc(),SpiritAlert.INFORMATION);
				getTxtInformacionAdc().setText(clienteIf.getInformacionAdc());
			}
						
			if (corporacionIf == null) {
				try {
					corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
					getTxtCorporacion().setText(corporacionIf.getCodigo() + " - "+ corporacionIf.getNombre());
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error al consultar la Coroporación del cliente", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}	

			getTxtClienteOficina().setText("");
			getCmbFormaPago().setEnabled(false);
			getCmbFormaPago().removeAllItems();
			getCmbTipoIdentificacion().setEnabled(false);
			getTxtIdentificacion().setText("");
			getTxtContacto().setText("");
			getTxtTelefono().setText("");
			getTxtDireccion().setText("");
			getCmbTipoReferencia().setEnabled(false);
			getCmbFormaPago().setEnabled(true);
			getCmbFormaPago().removeAllItems();
			Collection formaPagoCollection = null;
			try {
				// formaPagoCollection =
				// getFormaPagoSessionService().findFormaPagoByClienteId(clienteIf.getId());
				formaPagoCollection = SessionServiceLocator.getFormaPagoSessionService().findFormaPagoByEmpresaId(Parametros.getIdEmpresa());
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			Iterator itFormaPagoCollection = formaPagoCollection.iterator();

			while (itFormaPagoCollection.hasNext()) {
				FormaPagoIf formaPagoIf = (FormaPagoIf) itFormaPagoCollection.next();
				getCmbFormaPago().addItem(formaPagoIf);
			}

			if (OPCION_SI.equals(clienteIf.getUsuariofinal())) {
				getCmbTipoIdentificacion().setEnabled(true);
				getTxtIdentificacion().setEnabled(true);
				getTxtIdentificacion().setEditable(true);
			} else {
				getCmbTipoIdentificacion().setEnabled(false);
				getTxtIdentificacion().setEnabled(false);
				getTxtIdentificacion().setEditable(false);
			}
			
			// Se setea el cliente oficina
			try {
				if(desdePresupuesto){
					setClienteOficina();
				}else{
					Collection clienteOficinaCollection = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(clienteIf.getId());
					if (clienteOficinaCollection.size() == 1) {
						clienteOficinaIf = (ClienteOficinaIf) clienteOficinaCollection.iterator().next();
						setClienteOficina();
					}
				}				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			}
		}
	}
	
	private void actualizarTotales() {
		Iterator detallesIterator = pedidoDetalleColeccion.iterator();
		subTotal = 0D; 
		totalIvaCero = 0D; 
		descuentoTotal = 0D;
		descuentosVariosTotal = 0D; 
		descuentoGlobalTotal = 0D; 
		ivaTotal = 0D; 
		total = 0D; 
		valorBruto = 0D; 
		ivaTemp = 0D;
		int i = 0;
		
		if (!getTxtPorcentajeComision().getText().trim().equals(""))
			porcentajeComisionAgencia = Double.valueOf(Utilitarios.removeDecimalFormat(getTxtPorcentajeComision().getText()));
		else
			porcentajeComisionAgencia = 0D;
		
		while (detallesIterator.hasNext()) {
			PedidoDetalleIf pedidoDetalle = (PedidoDetalleIf) detallesIterator.next();
			getTblPedidoDetalle().getSelectionModel().setSelectionInterval(i,i);
			setPedidoDetalle(pedidoDetalle);
			actualizarDetallePedido(true);
			i++;
		}
	}
	
	private void setClienteOficina() {
		
		//este vector lo encero aqui porque si lo hago en el clean no graba valores al guardar la factura
		presupuestoDetalleFacturadoColeccion = new Vector<PresupuestoDetalleIf>();
		
		
		getTxtClienteOficina().setText(clienteOficinaIf.getCodigo() + " - " + clienteOficinaIf.getDescripcion());
		getTxtIdentificacion().setText("");
		getTxtContacto().setText("");
		getTxtTelefono().setText("");
		getTxtDireccion().setText("");
		getCmbTipoReferencia().setEnabled(false);

		if (getMode() != SpiritMode.FIND_MODE) {
			getCmbTipoReferencia().setEnabled(true);
			getBtnGenerarFactura().setEnabled(true);
			try {
				TipoIdentificacionIf tipoIdentificacionBean = mapaTipoIdentificacion.get(clienteIf.getTipoidentificacionId());

				if (tipoIdentificacionBean != null) {
					tipoIdentificacionIf = tipoIdentificacionBean;
					getCmbTipoIdentificacion().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoIdentificacion(), tipoIdentificacionIf.getId()));
					getCmbTipoIdentificacion().repaint();
					getTxtIdentificacion().setText(clienteIf.getIdentificacion());
				}

				Collection clienteContactoColeccion = SessionServiceLocator.getClienteContactoSessionService().findClienteContactoByClienteoficinaId(clienteOficinaIf.getId());
				Iterator itClienteContacto = clienteContactoColeccion.iterator();

				while (itClienteContacto.hasNext()) {
					ClienteContactoIf clienteContactoIf = (ClienteContactoIf) itClienteContacto.next();

					if (clienteContactoIf.getClienteoficinaId().compareTo(clienteOficinaIf.getId()) == 0)
							getTxtContacto().setText(clienteContactoIf.getNombre());
				}

			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
			
			getTxtDireccion().setText((!clienteOficinaIf.getDireccion().trim().equals(""))?clienteOficinaIf.getDireccion():"S/D");
			getTxtTelefono().setText((!clienteOficinaIf.getTelefono().trim().equals(""))?clienteOficinaIf.getTelefono():"S/N");
		}
	}

	private void actualizarNumerador(int codigo) throws GenericBusinessException {
		NumeradoresIf numerador = Numeradores.getNumeradorId("PEDIDO", Parametros.getIdEmpresa());
		numerador.setUltimoValor(BigDecimal.valueOf(codigo));
		SessionServiceLocator.getNumeradoresSessionService().saveNumeradores(numerador);
	}

	public boolean isEmpty() {
		if (/*"".equals(this.getTxtOficinaEmpresa().getText())
			&&*/ "".equals(this.getTxtIdentificacion().getText())
			&& "".equals(this.getTxtLinea().getText())
			&& "".equals(this.getTxtCantidad().getText())
			&& "".equals(this.getTxtDireccion().getText())
			&& "".equals(this.getTxtTelefono().getText())
			&& "".equals(this.getTxtContacto().getText())
			&& "".equals(this.getTxtIVAFinal().getText())
			&& "".equals(this.getTxtICEFinal().getText())
			&& "".equals(this.getTxtOtroImpuestoFinal().getText())
			&& "".equals(this.getTxtValorFinal().getText())
			&& "".equals(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentoAgencia().getText()))
			&& "".equals(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentosVarios().getText()))
			&& (this.getCmbTipoIdentificacion().getSelectedItem() == null)
			&& (this.getCmbTipoDocumento().getSelectedItem() == null)
			&& (this.getCmbFechaPedido().getSelectedItem() == null)
			&& (this.getCmbTipoReferencia().getSelectedItem() == null)
			&& (this.getCmbMoneda().getSelectedItem() == null)
			&& (this.getCmbFormaPago().getSelectedItem() == null)
			&& (this.getCmbListaPrecio().getSelectedItem() == null)
			&& (this.getCmbCaja().getSelectedItem() == null)
			&& (this.getCmbVendedor().getSelectedItem() == null)
			&& (this.getCmbBodega().getSelectedItem() == null)
			&& (this.getCmbBodega().getSelectedItem() == null)
			&& (this.getCmbDocumento().getSelectedItem() == null)
			&& (this.getCmbMotivo().getSelectedItem() == null)
			&& (this.getCmbEstado().getSelectedItem() == null)
			&& (this.clienteOficinaIf == null)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateFields() {
		String strDiasValidez = this.getTxtDiasValidez().getText();
		String strDireccion = this.getTxtDireccion().getText();
		String strTelefono = this.getTxtTelefono().getText();
		TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) this.getCmbTipoDocumento().getSelectedItem();
		FormaPagoIf formaPago = (FormaPagoIf) this.getCmbFormaPago().getSelectedItem();
		ListaPrecioIf listaPrecio = (ListaPrecioIf) this.getCmbListaPrecio().getSelectedItem();
		OrigenDocumentoIf origenDocumento = (OrigenDocumentoIf) this.getCmbOrigenDocumento().getSelectedItem();
		ClienteIf fundacion = (ClienteIf) this.getCmbFundacion().getSelectedItem();
		EmpleadoIf vendedor = (EmpleadoIf) this.getCmbVendedor().getSelectedItem();
		BodegaIf bodega = (BodegaIf) this.getCmbBodega().getSelectedItem();
		CajaIf caja = (CajaIf) this.getCmbCaja().getSelectedItem();

		if (clienteIf == null) {
			SpiritAlert.createAlert("Debe seleccionar un Cliente!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getBtnBuscarCliente().grabFocus();
			return false;
		}
		
		if (getMode() == SpiritMode.SAVE_MODE) {
			// if
			// (!(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PENDIENTE)
			// ||
			// getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_COTIZACION)))
			// {
			EstadoPedido estado = (EstadoPedido) getCmbEstado().getSelectedItem();
			if (!(estado == EstadoPedido.PENDIENTE || estado == EstadoPedido.COTIZACION)) {
				SpiritAlert.createAlert("Sólo pueden guardarse pedidos con estado Pendiente o Cotización !!", SpiritAlert.WARNING);
				this.getJtpPedido().setSelectedIndex(0);
				this.getCmbEstado().grabFocus();
				return false;
			}
		}

		if (tipoDocumento == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de documento !!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getCmbTipoDocumento().grabFocus();
			return false;
		}
		
		if (clienteIf.getUsuariofinal().equals("S") && !tipoDocumento.getCodigo().equals("VTA")) {
			SpiritAlert.createAlert("TIPO DE DOCUMENTO seleccionado no es válido.\nSólo pueden emitirse NOTAS DE VENTA a nombre de consumidor final.", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getCmbTipoDocumento().grabFocus();
			return false;
		}
		
		if ( getCmbMoneda().getSelectedItem() == null ){
			SpiritAlert.createAlert("Debe seleccionar una moneda !!", SpiritAlert.WARNING);
			this.getCmbMoneda().setSelectedIndex(0);
			this.getCmbMoneda().grabFocus();
			return false;
		}

		if (caja == null) {
			SpiritAlert.createAlert("Debe seleccionar una caja !!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getCmbCaja().grabFocus();
			return false;
		}
		
		if (clienteOficinaIf == null) {
			SpiritAlert.createAlert("Debe seleccionar la oficina del cliente !!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getBtnBuscarClienteOficina().grabFocus();
			return false;
		}
		
		if (getCmbTipoReferencia().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de referencia para el pedido !!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getCmbTipoReferencia().grabFocus();
			return false;
		}
		
		TipoReferenciaPedido tr = (TipoReferenciaPedido) getCmbTipoReferencia().getSelectedItem();
		if ( TipoReferenciaPedido.PRESUPUESTO == tr && presupuestoIf == null ) {
			SpiritAlert.createAlert("Debe seleccionar una Referencia!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getBtnEscojaReferencia().grabFocus();
			return false;
		}
		
		if(clienteIf.getRequiereSap() != null && clienteIf.getRequiereSap().equals(OPCION_SI)
				&& getTxtAutorizacionSAP().getText().equals(SpiritConstants.getEmptyCharacter())) {
			SpiritAlert.createAlert("Para facturar este Cliente debe ingresar la Autorización SAP.", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getTxtAutorizacionSAP().grabFocus();
			return false;
		}

		if ((("".equals(strTelefono)) || (strTelefono == null))) {
			SpiritAlert.createAlert("Debe ingresar el teléfono de la oficina del cliente !!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getTxtTelefono().grabFocus();
			return false;
		}

		if ((("".equals(strDireccion)) || (strDireccion == null))) {
			SpiritAlert.createAlert("Debe ingresar la dirección de la oficina del cliente !!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getTxtDireccion().grabFocus();
			return false;
		}
		
		if (formaPago == null) {
			SpiritAlert.createAlert("Debe seleccionar la forma de pago para el pedido !!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getCmbFormaPago().grabFocus();
			return false;
		}
		
		if (listaPrecio == null) {
			SpiritAlert.createAlert("Debe seleccionar una lista de precio !!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getCmbListaPrecio().grabFocus();
			return false;
		}
		
		if ((("".equals(strDiasValidez)) || (strDiasValidez == null))) {
			SpiritAlert.createAlert("Debe ingresar los días de validez del pedido !!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getTxtDiasValidez().grabFocus();
			return false;
		}
		
		if (getTxtObservacion().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar una observacion para el pedido !!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getTxtObservacion().grabFocus();
			return false;
		}
		
		if (getCmbFundacion().getSelectedItem() == null) {
			ParametroEmpresaIf fundacionRequerida = buscarParametroActivarDonacion();
			
			if (fundacionRequerida != null && fundacionRequerida.getValor().equals("S")) {
				SpiritAlert.createAlert("Debe seleccionar una fundación !!", SpiritAlert.WARNING);
				this.getJtpPedido().setSelectedIndex(0);
				this.getCmbFundacion().grabFocus();
				return false;
			}
		}
		
		if ( getCmbOrigenDocumento().getSelectedItem() == null ){
			SpiritAlert.createAlert("Debe seleccionar un Origen de Documento !!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getCmbOrigenDocumento().grabFocus();
			return false;
		}

		if (vendedor == null && !origenDocumento.getCodigo().equals("INT") ) {
			SpiritAlert.createAlert("Debe seleccionar el vendedor !!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getCmbVendedor().grabFocus();
			return false;
		}

		if (bodega == null) {
			SpiritAlert.createAlert("Debe seleccionar una bodega !!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getCmbBodega().grabFocus();
			return false;
		}
		
		if ( "".equalsIgnoreCase(getTxtPuntoImpresion().getText()) ){
			SpiritAlert.createAlert("No se ha definido punto de impresión para la caja seleccionada!!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(0);
			this.getCmbCaja().grabFocus();
			return false;
		}

		if (pedidoDetalleColeccion.size() == 0) {
			SpiritAlert.createAlert("Debe ingresar el detalle del pedido !!", SpiritAlert.WARNING);
			this.getJtpPedido().setSelectedIndex(1);
			this.getCmbDocumento().grabFocus();
			return false;
		}
		
		for (int i=0; i<pedidoDetalleColeccion.size(); i++) {
			PedidoDetalleIf pedidoDetalle = (PedidoDetalleIf) pedidoDetalleColeccion.get(i);
			if (pedidoDetalle.getDocumentoId() != null) {
				DocumentoIf documento = mapaDocumento.get(pedidoDetalle.getDocumentoId());
				if (documento.getTipoDocumentoId().compareTo(tipoDocumento.getId()) != 0) {
					SpiritAlert.createAlert("Documento no corresponde con tipo de documento seleccionado !!", SpiritAlert.WARNING);
					this.getJtpPedido().setSelectedIndex(1);
					this.getTblPedidoDetalle().getSelectionModel().setSelectionInterval(i,i);
					return false;
				}
			}
		}
		
		if (tipoDocumento.getReembolso().equals("S")) {
			boolean pass = true;
			/*
			 * if
			 * (!ordenesSinFacturar.equals(SpiritConstants.getEmptyCharacter()))
			 * pass = false;
			 */ 
			for (int i=0; i<pedidoDetalleColeccion.size(); i++) {
				PedidoDetalleIf pedidoDetalle = (PedidoDetalleIf) pedidoDetalleColeccion.get(i);
				if (pedidoDetalle.getComprasReembolsoAsociadas().equals("")) {
					pass = false;
					break;
				}
			}
			if (!pass) {
				int opcion = JOptionPane.NO_OPTION;
				if (TipoReferenciaPedido.NINGUNO == tr) {
					opcion = JOptionPane.showOptionDialog(null, "No se han seleccionado todas las facturas de reembolso asociadas a esta factura\n" +
																"Si elige continuar no se registrarán los datos de las facturas de reembolso asociadas\n" +
																"¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				} else {
					opcion = JOptionPane.showOptionDialog(null, "No se han podido recuperar los datos de todas las facturas de reembolso asociadas a este presupuesto\n" +
																"Esto puede deberse a que no se han ingresado las facturas de proveedores correspondientes\n" +
																"Si elige continuar no se registrarán los datos de las facturas de reembolso asociadas\n" +
																"¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				}
				if (opcion == JOptionPane.YES_OPTION) {
					return true;
				} else {
					return false;
				}
			}
		}		

		if (getMode() == SpiritMode.UPDATE_MODE) {
			/*
			 * boolean facturado = false;
			 * 
			 * try {
			 * 
			 * Iterator facturaIterator =
			 * FacturaModel.getFacturaSessionService().findFacturaByPedidoId(pedido.getId()).iterator();
			 * 
			 * if (facturaIterator.hasNext()) facturado = true; } catch
			 * (GenericBusinessException e1) { e1.printStackTrace(); }
			 * 
			 * if (facturado == true) { SpiritAlert.createAlert("El Pedido ya ha
			 * sido facturado y no puede ser actualizado!");
			 * this.getJtpPedido().setSelectedIndex(0); return false; }
			 */
			String estadoLetra = pedido.getEstado();
			try {
				EstadoPedido estadoPedido = EstadoPedido.getEstadoOrdenTrabajoByLetra(estadoLetra);
				EstadoPedido estadoCombo = (EstadoPedido) getCmbEstado().getSelectedItem();
				// if (ESTADO_ANULADO.equals(pedido.getEstado()) &&
				// getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ANULADO))
				// {
				if (estadoPedido == EstadoPedido.ANULADO && estadoCombo == EstadoPedido.ANULADO) {
					SpiritAlert.createAlert("No se puede actualizar el Pedido, ha sido anulado !!", SpiritAlert.WARNING);
					this.getJtpPedido().setSelectedIndex(0);
					return false;
				}
	
				// if (getMode() != SpiritMode.UPDATE_MODE &&
				// ESTADO_COMPLETO.equals(pedido.getEstado())) {
				if (getMode() != SpiritMode.UPDATE_MODE && estadoPedido == EstadoPedido.COMPLETO) {
					SpiritAlert.createAlert("No se puede actualizar el Pedido ya generó una factura !!", SpiritAlert.WARNING);
					this.getJtpPedido().setSelectedIndex(0);
					return false;
				}
	
				// if (ESTADO_INCOMPLETO.equals(pedido.getEstado())) {
				if (estadoPedido == EstadoPedido.INCOMPLETO) {
					SpiritAlert.createAlert("No se puede actualizar el Pedido ya generó una factura !!", SpiritAlert.WARNING);
					this.getJtpPedido().setSelectedIndex(0);
					return false;
				}
			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(getCbNegociacionDirecta().isSelected()){
			if(getTxtPorcentajeNegociacionDirecta().getText().equals("")){
				SpiritAlert.createAlert("¡Debe ingresar un Porcentaje de Negociación!",SpiritAlert.WARNING);
				getJtpPedido().setSelectedIndex(0);
				getTxtPorcentajeNegociacionDirecta().grabFocus();
				return false;
			}else if(Double.valueOf(getTxtPorcentajeNegociacionDirecta().getText().replaceAll(",", "")) <= 0){
				SpiritAlert.createAlert("¡El Porcentaje de Negociación debe ser mayor que cero!",SpiritAlert.WARNING);
				getJtpPedido().setSelectedIndex(0);
				getTxtPorcentajeNegociacionDirecta().grabFocus();
				return false;
			}else if(Double.valueOf(getTxtPorcentajeNegociacionDirecta().getText().replaceAll(",", "")) > 100){
				SpiritAlert.createAlert("¡El Porcentaje de Negociación no puede ser mayor que 100%!",SpiritAlert.WARNING);
				getJtpPedido().setSelectedIndex(0);
				getTxtPorcentajeNegociacionDirecta().grabFocus();
				return false;
			}else if(getTxtDsctoCompraPorcentaje().getText().equals("")){
				SpiritAlert.createAlert("¡Debe ingresar un Porcentaje de Descuento de Compra!",SpiritAlert.WARNING);
				getJtpPedido().setSelectedIndex(0);
				getTxtDsctoCompraPorcentaje().grabFocus();
				return false;
			}else if(Double.valueOf(getTxtDsctoCompraPorcentaje().getText().replaceAll(",", "")) <= 0){
				SpiritAlert.createAlert("¡El Porcentaje de Descuento de Compra debe ser mayor que cero!",SpiritAlert.WARNING);
				getJtpPedido().setSelectedIndex(0);
				getTxtDsctoCompraPorcentaje().grabFocus();
				return false;
			}else if(Double.valueOf(getTxtDsctoCompraPorcentaje().getText().replaceAll(",", "")) > 100){
				SpiritAlert.createAlert("¡El Porcentaje de Descuento de Compra no puede ser mayor que 100%!",SpiritAlert.WARNING);
				getJtpPedido().setSelectedIndex(0);
				getTxtDsctoCompraPorcentaje().grabFocus();
				return false;
			}else if(clienteOficinaNegociacionIf == null){
				SpiritAlert.createAlert("¡Debe elegir un Cliente para la Negociación!",SpiritAlert.WARNING);
				getJtpPedido().setSelectedIndex(0);
				getBtnBuscarClienteNegociacion().grabFocus();
				return false;
			}
		}else{
			getTxtPorcentajeNegociacionDirecta().setText("0");
		}
		
		if(getCbComisionPura().isSelected()){
			if(getTxtDsctoCompraPorcentaje().getText().equals("")){
				SpiritAlert.createAlert("¡Debe ingresar un Porcentaje de Descuento de Compra!",SpiritAlert.WARNING);
				getJtpPedido().setSelectedIndex(0);
				getTxtDsctoCompraPorcentaje().grabFocus();
				return false;
			}else if(Double.valueOf(getTxtDsctoCompraPorcentaje().getText().replaceAll(",", "")) <= 0){
				SpiritAlert.createAlert("¡El Porcentaje de Descuento de Compra debe ser mayor que cero!",SpiritAlert.WARNING);
				getJtpPedido().setSelectedIndex(0);
				getTxtDsctoCompraPorcentaje().grabFocus();
				return false;
			}else if(Double.valueOf(getTxtDsctoCompraPorcentaje().getText().replaceAll(",", "")) > 100){
				SpiritAlert.createAlert("¡El Porcentaje de Descuento de Compra no puede ser mayor que 100%!",SpiritAlert.WARNING);
				getJtpPedido().setSelectedIndex(0);
				getTxtDsctoCompraPorcentaje().grabFocus();
				return false;
			}else if(clienteOficinaNegociacionIf == null){
				SpiritAlert.createAlert("¡Debe elegir un Cliente para la Negociación!",SpiritAlert.WARNING);
				getJtpPedido().setSelectedIndex(0);
				getBtnBuscarClienteNegociacion().grabFocus();
				return false;
			}
		}

		return true;
	}

	private ParametroEmpresaIf buscarParametroActivarDonacion() {
		ParametroEmpresaIf fundacionRequerida = null;
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("codigo", "ACTIVARDONACION");
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			Iterator it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
			if (it.hasNext())
				fundacionRequerida = (ParametroEmpresaIf) it.next();
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return fundacionRequerida;
	}

	public boolean validateFieldsDetalle() {
		String strPrecio = getTxtPrecio().getText();
		String strPrecioReal = getTxtPrecioReal().getText();
		String strCantidadPedida = getTxtCantidadPedida().getText();
		String strDescuento = Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoAgencia().getText());
		String strDescuentosVarios = Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentosVarios().getText());
		DocumentoIf documento = (getCmbDocumento().getSelectedItem() != null)?((DocumentoIf) getCmbDocumento().getSelectedItem()):null;
		
		if(productoIf != null) {
			try {
				Map aMap = new HashMap();
				aMap.put("nemonico", "INGRESO");
				Long idOrigenDocumento = ((OrigenDocumentoIf) getCmbOrigenDocumento().getSelectedItem()).getId();
				aMap.put("entidadId", idOrigenDocumento);
				aMap.put("tipoEntidad", "N");
				
				OficinaIf oficina = (OficinaIf)getCmbOficinaEmpresa().getSelectedItem();
				aMap.put("oficinaId", oficina.getId());
				
				Iterator cuentaEntidadIterator = SessionServiceLocator.getCuentaEntidadSessionService().findCuentaEntidadByQuery(aMap).iterator();
				GenericoIf generico = mapaGenerico.get(productoIf.getGenericoId());
				if (!cuentaEntidadIterator.hasNext() || generico.getMediosProduccion().equals("G") || (generico.getMediosProduccion().equals("O"))) {
					Long idProducto = productoIf.getId();
					aMap.put("entidadId", idProducto);
					aMap.put("tipoEntidad", "I");
					cuentaEntidadIterator = SessionServiceLocator.getCuentaEntidadSessionService().findCuentaEntidadByQuery(aMap).iterator();
					if (!cuentaEntidadIterator.hasNext()) {
						Long idOficina = ((OficinaIf) Parametros.getOficina()).getId();	
						aMap.put("entidadId", idOficina);
						aMap.put("tipoEntidad", "O");
						cuentaEntidadIterator = SessionServiceLocator.getCuentaEntidadSessionService().findCuentaEntidadByQuery(aMap).iterator();
						if (!cuentaEntidadIterator.hasNext()) {
							aMap.put("entidadId", generico.getId());
							aMap.put("tipoEntidad", "G");
							cuentaEntidadIterator = SessionServiceLocator.getCuentaEntidadSessionService().findCuentaEntidadByQuery(aMap).iterator();
						}
					}

					if (!cuentaEntidadIterator.hasNext()) {
						SpiritAlert.createAlert("El Producto no tiene Cuenta Contable asociada, corrija esto por favor!", SpiritAlert.WARNING);
						getTxtCodigoProducto().grabFocus();
						return false;
					}
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error al validar los datos", SpiritAlert.ERROR);
			}
		}

		if (("".equals(strPrecio)) || (strPrecio == null)) {
			SpiritAlert.createAlert("Debe ingresar el precio de venta !!", SpiritAlert.WARNING);
			getTxtPrecio().requestFocusInWindow();
			return false;
		}

		if (("".equals(strPrecioReal)) || (strPrecioReal == null)) {
			SpiritAlert.createAlert("Debe ingresar el precio real !!", SpiritAlert.WARNING);
			getTxtPrecioReal().requestFocusInWindow();
			return false;
		}

		if (Double.parseDouble(Utilitarios.removeDecimalFormat(strPrecioReal)) <= 0D && documento.getBonificacion().equals("N")
				&& !tipoDocumento.getCodigo().equals(CODIGO_TIPO_DOCUMENTO_FACTURA_COMISION)) {
			SpiritAlert.createAlert("El precio real debe ser mayor a cero !!", SpiritAlert.WARNING);
			getTxtPrecioReal().requestFocusInWindow();
			return false;
		}
		
		if (("".equals(strDescuento)) || (strDescuento == null)) {
			getTxtPorcentajeDescuentoAgencia().setText("0");
		}
		
		if (("".equals(strDescuentosVarios)) || (strDescuentosVarios == null)) {
			getTxtPorcentajeDescuentosVarios().setText("0");
		}
		
		GenericoIf generico = mapaGenerico.get(productoIf.getGenericoId());
		double descuentoAgencia = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoAgencia().getText()));
		if (generico.getAceptaDescuento().equals("N") && descuentoAgencia != 0D) {
			SpiritAlert.createAlert("El item seleccionado no acepta descuentos", SpiritAlert.WARNING);
			getTxtPorcentajeDescuentoAgencia().grabFocus();
			return false;
		}

		if (("".equals(strCantidadPedida)) || (strCantidadPedida == null)) {
			SpiritAlert.createAlert("Debe ingresar la cantidad pedida !!", SpiritAlert.WARNING);
			getTxtCantidadPedida().requestFocusInWindow();
			return false;
		}
		
		Double precio = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecio().getText()));
		Double precioReal = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecioReal().getText()));
		Double descuento = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoAgencia().getText()));
		Double descuentosVarios = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentosVarios().getText()));
						
		if ("0.0".equals(strCantidadPedida) || "0".equals(strCantidadPedida)) {
			SpiritAlert.createAlert("La cantidad pedida debe ser mayor que 0 !!", SpiritAlert.WARNING);
			getTxtCantidadPedida().requestFocusInWindow();
			return false;
		}

		if (documento == null && !cargandoDetallesReferencia) {
			SpiritAlert.createAlert("Debe seleccionar el documento !!", SpiritAlert.WARNING);
			this.getCmbDocumento().requestFocusInWindow();
			return false;
		}
		
		if (((TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem()).getExigemotivo().equals(OPCION_SI) && (getCmbMotivo().getSelectedItem() == null)) {
			SpiritAlert.createAlert("El Tipo de Documento que seleccionó exige que escoja un motivo", SpiritAlert.WARNING);
			getCmbMotivo().grabFocus();
			return false;
		}

		if ("".equals(getTxtCantidad().getText()))
			getTxtCantidad().setText("0");

		if ((precio > precioReal) && OPCION_NO.equals(productoIf.getAceptapromocion())) {
			SpiritAlert.createAlert("El precio real no puede ser menor al precio", SpiritAlert.WARNING);
			getTxtPrecioReal().grabFocus();
			return false;
		}

		if (descuento > 100) {
			SpiritAlert.createAlert("El porcentaje de descuento no debe ser mayor al 100%", SpiritAlert.WARNING);
			getTxtPorcentajeDescuentoAgencia().grabFocus();
			return false;
		}
		
		if (descuentosVarios > 100) {
			SpiritAlert.createAlert("El porcentaje de descuentos varios no debe ser mayor al 100%", SpiritAlert.WARNING);
			getTxtPorcentajeDescuentosVarios().grabFocus();
			return false;
		}

		if (descuento > montoDescuento) {
			SpiritAlert.createAlert("La forma de pago seleccionada no permite descuentos mayores al " + montoDescuento + "%", SpiritAlert.WARNING);
			getTxtPorcentajeDescuentoAgencia().grabFocus();
			return false;
		}
		
		if (!cargandoDetallesReferencia && documento.getCodigo().equals("FACR") && getTxtFacturaProveedor().getText().equals("")) {
			String si = "Si"; 
	        String no = "No"; 
	        Object[] options = {si,no}; 
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea seleccionar la(s) factura(s) de proveedor asociadas antes de agregar el detalle?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				getBtnFacturaProveedor().grabFocus();
				return false;
			}
		}

		return true;
	}

	public void clean() {
		//no se puede limpiar vectores aqui porque se borran datos necesarios para llenar tablas luego de guardar factura		
		//limpiarVectores();
		planesMedioIf = new Vector<PlanMedioIf>();
		campanaPeriodo = "";
		DefaultTableModel model = (DefaultTableModel) this.getTblPedidoDetalle().getModel();
		limpiarTabla(getTblPedidoDetalle());
		tarea = null;		
		pedidoDetalleColeccion = new Vector();
		pedidoDetalleEliminadas = new Vector();
		purchaseDataMap = new HashMap<Integer, Vector<PurchaseData>>();
		purchaseDataVector = new Vector<PurchaseData>();
		subTotal = 0.00;
		totalIvaCero = 0.00;
		totalIvaCero = 0.00;
		valorBruto = 0.00;
		descuentoTotal = 0.00;
		descuentosVariosTotal = 0.00;
		descuentoGlobalTotal = 0.00;
		comisionAgenciaTotal = 0.00;
		porcentajeComisionAgencia = 0.00;
		ivaTotal = 0.00;
		total = 0.00;
		montoDescuento = 0.00;
		productoIf = null;
		idProductoClientePlanMedioPedido = new Long(0);
		listaPlanMedioFacturacionEscogido.clear();
		versionProductoClientePlanMedioPedido = null;
		fechaInicioPlanMedioPedido = new Date();
		fechaFinPlanMedioPedido = new Date();
		ordenesSinFacturar = "";
		inicializacionTextFieldsPedido();
		inicializacionComboBoxesPedido();
		inicializacionDateComboBoxPedido();
		mostrarInformacionEnvio(false);
		this.repaint();
	}

	private void mostrarInformacionEnvio(boolean mostrar){
		getLblInformacionPedido().setVisible(mostrar);
		getBtnInformacionEnvio().setVisible(mostrar);
		getBtnInformacionEnvio().setEnabled(mostrar);
	}
	
	private void inicializacionDateComboBoxPedido() {
		// si esta en modo find seteo la fecha del pedido a null
		this.getCmbFechaPedido().setFormat(Utilitarios.setFechaUppercase());
		if (getMode() == SpiritMode.FIND_MODE)
			this.getCmbFechaPedido().setSelectedItem(null);

		// si esta en modo save seteo las fechas a la del sistema
		if (getMode() == SpiritMode.SAVE_MODE) {
			// seteo los combos de fecha en orden a la fecha actual
			Calendar calendarInicio = new GregorianCalendar();
			calendarInicio.setTime(now.getTime());
			this.getCmbFechaPedido().setCalendar(calendarInicio);
			// this.getCmbFechaPedido().getDateModel().setMinDate(now);
		}
	}

	private void inicializacionComboBoxesPedido() {
		this.getCmbTipoDocumento().setSelectedItem(null);
		this.getCmbFormaPago().setSelectedItem(null);
		this.getCmbMoneda().setSelectedItem(null);
		this.getCmbListaPrecio().setSelectedItem(null);
		this.getCmbEstado().setSelectedItem(null);
		this.getCmbVendedor().setSelectedItem(null);
		this.getCmbDirectorCuentas().setSelectedItem(null);
		this.getCmbOrigenDocumento().setSelectedItem(null);
		this.getCmbFundacion().setSelectedItem(null);
		this.getCmbTipoIdentificacion().setSelectedItem(null);
		this.getCmbDocumento().setSelectedItem(null);
		this.getCmbMotivo().setSelectedItem(null);
		this.getCmbLote().setSelectedItem(null);
	}

	private void inicializacionTextFieldsPedido() {
		this.getTxtDescuentoGlobalPorcentaje().setText("0");
		this.getTxtDescuentoGlobalValor().setText("0");
		this.getTxtCodigo().setText("");
		//this.getTxtOficinaEmpresa().setText("");
		this.getTxtDiasValidez().setText("");
		this.getTxtObservacion().setText("");
		this.getTxtCorporacion().setText("");
		this.getTxtCliente().setText("");
		this.getTxtInformacionAdc().setText("");
		this.getTxtClienteOficina().setText("");
		this.getTxtIdentificacion().setText("");
		this.getTxtTelefono().setText("");
		this.getTxtContacto().setText("");
		this.getTxtDireccion().setText("");
		this.getTxtReferencia().setText("");
		this.getTxtPuntoImpresion().setText("");
		this.getTxtEscojaReferencia().setText("");
		this.getTxtAutorizacionSAP().setText("");
		cleanDetail();
		this.getTxtValorFinal().setText("");
		this.getTxtDescuentoFinal().setText("");
		this.getTxtDescuentosVariosTotal().setText("");
		this.getTxtIVAFinal().setText("");
		this.getTxtICEFinal().setText("");
		this.getTxtOtroImpuestoFinal().setText("");
		this.getTxtTotalFinal().setText("");
		this.getTxtPorcentajeComision().setText("");
		this.getTxtValorComision().setText("");
		this.getCbNegociacionDirecta().setSelected(false);
		this.getCbComisionPura().setSelected(false);
		this.getTxtPorcentajeNegociacionDirecta().setText("");
		this.getTxtDsctoCompraPorcentaje().setText("");
		this.getTxtClienteNegociacion().setText("");
		this.clienteOficinaNegociacionIf = null;
	}

	private void cleanDetail() {
		this.getTxtCodigoProducto().setText("");
		this.getTxtDescripcion().setText("");
		this.getTxtProveedor().setText("");
		this.getTxtLinea().setText("");
		this.getTxtCantidadPedida().setText("");
		this.getTxtCantidad().setText("");
		this.getTxtPrecio().setText("");
		this.getTxtPrecioReal().setText("");
		this.getTxtPorcentajeDescuentoAgencia().setText("");
		this.getTxtPorcentajeDescuentosVarios().setText("");
	}
	
	public void cleanTablaPresupuesto() {
		DefaultTableModel model = (DefaultTableModel) this.getTblPresupuestoDetalleP().getModel();
		for (int i = this.getTblPresupuestoDetalleP().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}

	public void cleanTabla() {
		DefaultTableModel model = (DefaultTableModel) this.getTblPedidoDetalle().getModel();
		for (int i = this.getTblPedidoDetalle().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);

		pedidoDetalleColeccion = new Vector();
		subTotal = 0.00;
		totalIvaCero = 0.00;
		valorBruto = 0.00;
		descuentoTotal = 0.00;
		descuentosVariosTotal = 0.00;
		ivaTotal = 0.00;
		total = 0.00;
		porcentajeComisionAgencia = 0.00;
		comisionAgenciaTotal = 0.00;
		productoIf = null;
		// inicializo los campos de texto de pedido detalle
		this.getTxtCodigoProducto().setText("");
		this.getTxtDescripcion().setText("");
		this.getTxtLinea().setText("");
		this.getTxtCantidadPedida().setText("0");
		this.getTxtCantidad().setText("0");
		this.getTxtPrecio().setText("");
		this.getTxtPrecioReal().setText("");
		this.getTxtPorcentajeDescuentoAgencia().setText("0");
		this.getTxtPorcentajeDescuentosVarios().setText("0");
		this.getTxtValorFinal().setText("");
		this.getTxtDescuentoFinal().setText("");
		this.getTxtDescuentosVariosTotal().setText("");
		this.getTxtIVAFinal().setText("");
		this.getTxtICEFinal().setText("");
		this.getTxtOtroImpuestoFinal().setText("");
		this.getTxtTotalFinal().setText("");
		this.getTxtPorcentajeComision().setText("");
		this.getTxtValorComision().setText("");
		this.repaint();
	}

	private void inicializacionComboBoxFindMode() {
		// this.getCmbTipoReferencia().removeActionListener(oActionListenerCmbTipoReferencia);
		// cargo solo los combos estáticos para buscar
		/*
		 * this.getCmbEstado().addItem(NOMBRE_ESTADO_PENDIENTE);
		 * this.getCmbEstado().addItem(NOMBRE_ESTADO_COMPLETO);
		 * this.getCmbEstado().addItem(NOMBRE_ESTADO_INCOMPLETO);
		 * this.getCmbEstado().addItem(NOMBRE_ESTADO_ANULADO);
		 * this.getCmbEstado().addItem(NOMBRE_ESTADO_COTIZACION);
		 */
		cargarComboEstado();
		this.getCmbEstado().setSelectedItem(null);
		/*
		 * this.getCmbTipoReferencia().addItem(NOMBRE_REFERENCIA_NINGUNO);
		 * this.getCmbTipoReferencia().addItem(NOMBRE_REFERENCIA_PRESUPUESTO);
		 * this.getCmbTipoReferencia().addItem(NOMBRE_REFERENCIA_PLAN_MEDIOS);
		 */
		this.getCmbTipoReferencia().setSelectedItem(null);
		this.getCmbTipoReferencia().repaint();
		this.getCmbFechaPedido().setSelectedItem(null);
	}

	private void inicializarPanelFindMode() {
		this.getTxtCodigo().setEditable(true);
		//this.getTxtOficinaEmpresa().setEnabled(false);
		this.getTxtDiasValidez().setEnabled(false);
		this.getTxtObservacion().setEnabled(false);
		this.getTxtIdentificacion().setEnabled(false);
		this.getTxtTelefono().setEnabled(false);
		this.getTxtContacto().setEnabled(true);
		this.getTxtDireccion().setEnabled(false);
		this.getTxtReferencia().setEnabled(false);
		this.getTxtPuntoImpresion().setEnabled(false);
		this.getTxtLinea().setEnabled(false);
		this.getCmbLote().setEnabled(false);
		this.getTxtDescripcion().setEnabled(false);
		this.getTxtCantidadPedida().setEnabled(false);
		this.getTxtCantidad().setEnabled(false);
		this.getTxtPrecio().setEnabled(false);
		this.getTxtPrecioReal().setEnabled(false);
		this.getTxtPorcentajeDescuentoAgencia().setEnabled(false);
		this.getTxtPorcentajeDescuentosVarios().setEnabled(false);
		this.getTxtValorFinal().setEnabled(false);
		this.getTxtDescuentoFinal().setEnabled(false);
		this.getTxtDescuentosVariosTotal().setEnabled(false);
		this.getTxtIVAFinal().setEnabled(false);
		this.getTxtICEFinal().setEnabled(false);
		this.getTxtPorcentajeComision().setEditable(false);
		this.getTxtValorComision().setEditable(false);
		this.getTxtOtroImpuestoFinal().setEnabled(false);
		this.getTxtTotalFinal().setEnabled(false);
		this.getCmbFechaPedido().setEnabled(true);
		this.getCmbFormaPago().setEnabled(false);
		this.getCmbMoneda().setEnabled(false);
		this.getCmbListaPrecio().setEnabled(false);
		this.getCmbCaja().setEnabled(false);
		this.getCmbEstado().setEnabled(true);
		this.getCmbTipoReferencia().setEnabled(true);
		this.getBtnEscojaReferencia().setEnabled(false);
		this.getCmbVendedor().setEnabled(false);
		this.getCmbBodega().setEnabled(false);
		this.getCmbOrigenDocumento().setEnabled(false);
		this.getCmbVendedor().setEnabled(true);
		
		ParametroEmpresaIf fundacionRequerida = buscarParametroActivarDonacion();
		if (fundacionRequerida != null && fundacionRequerida.getValor().equals("S")) {
			this.getLblFundacion().setVisible(true);
			this.getCmbFundacion().setVisible(true);
			this.getCmbFundacion().setEnabled(false);
		} else {
			this.getLblFundacion().setVisible(false);
			this.getCmbFundacion().setVisible(false);
		}
		this.getBtnBuscarCorporacion().setEnabled(true);
		this.getBtnBuscarCliente().setEnabled(true);
		this.getBtnBuscarClienteOficina().setEnabled(true);
		this.getCmbTipoIdentificacion().setEnabled(false);
		this.getCmbDocumento().setEnabled(false);
		this.getCmbMotivo().setEnabled(false);
		this.getBtnGenerarFactura().setEnabled(false);
		this.getCmbTipoReferencia().setSelectedItem(null);
		this.getTxtEscojaReferencia().setEditable(false);
		corporacionIf = null;
		clienteIf = null;
		clienteOficinaIf = null;
		
		inicializarSeccionReporte();
	}

	private void addActionListeners() {
		getCmbFormaPago().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if ((getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE) && getCmbFormaPago().getSelectedItem() != null)
					montoDescuento = ((FormaPagoIf) getCmbFormaPago().getSelectedItem()).getDescuentoVenta().doubleValue();
			}
		});

		getCmbTipoDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					if (getCmbTipoDocumento().getSelectedItem() != null) {
						tipoDocumentoIf = (TipoDocumentoIf) ((JComboBox) evento.getSource()).getSelectedItem();
						
						if (getMode() == SpiritMode.FIND_MODE) {
							if(tipoDocumentoIf.getCodigo().equals("FAR")){
								getBtnFacturaProveedor().setEnabled(true);
							}else{
								getBtnFacturaProveedor().setEnabled(false);
							}
						}
						
						if ((getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE)) {
							getCmbDocumento().removeAllItems();
							setearPuntoImpresion();
							
							Long usuarioId = ((UsuarioIf) SessionServiceLocator.getUsuarioSessionService().findUsuarioByUsuario(Parametros.getUsuario().toLowerCase()).iterator().next()).getId();
							SpiritComboBoxModel cmbModelDocumento = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipodocumentoIdAndUsuarioId(tipoDocumentoIf.getId(), usuarioId));
							getCmbDocumento().setModel(cmbModelDocumento);
							if (getCmbDocumento().getItemCount() > 0) {
								getCmbDocumento().setEnabled(true);
								getCmbDocumento().setSelectedIndex(0);
							} else
								getCmbDocumento().setEnabled(false);

							if (OPCION_NO.equals(tipoDocumentoIf.getExigemotivo())) {
								getCmbMotivo().setSelectedItem(null);
								getCmbMotivo().setEnabled(false);
							} else
								getCmbMotivo().setEnabled(true);

							if (OPCION_SI.equals(tipoDocumentoIf.getDescuentoespecial())) {
								getRbDescuentoGlobalPorcentaje().setEnabled(true);
								getRbDescuentoGlobalValor().setEnabled(true);
								
								if (getRbDescuentoGlobalPorcentaje().isSelected()) {
									getTxtDescuentoGlobalPorcentaje().setEditable(true);
									getTxtDescuentoGlobalPorcentaje().setEnabled(true);
									getTxtDescuentoGlobalValor().setEditable(false);
									getTxtDescuentoGlobalValor().setEnabled(false);
								} else if (getRbDescuentoGlobalValor().isSelected()) {
									getTxtDescuentoGlobalPorcentaje().setEditable(false);
									getTxtDescuentoGlobalPorcentaje().setEnabled(false);
									getTxtDescuentoGlobalValor().setEditable(true);
									getTxtDescuentoGlobalValor().setEnabled(true);
								}
							} else {
								getRbDescuentoGlobalPorcentaje().setEnabled(false);
								getRbDescuentoGlobalValor().setEnabled(false);
								getTxtDescuentoGlobalPorcentaje().setEditable(false);
								getTxtDescuentoGlobalPorcentaje().setEnabled(false);
								getTxtDescuentoGlobalValor().setEditable(false);
								getTxtDescuentoGlobalValor().setEnabled(false);
								getTxtDescuentoGlobalPorcentaje().setText("0");
								getTxtDescuentoGlobalValor().setText("0");
							}
						}
					}
				} catch (GenericBusinessException e1) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e1.printStackTrace();
				}
			}
		});

		getCmbDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					if (getCmbDocumento().getSelectedItem() != null) {						
						if ((getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE)) {							
							inicializarTextFieldsPrecio();
							
							DocumentoIf documento = (DocumentoIf)getCmbDocumento().getSelectedItem();
							if(documento.getCodigo().equals("FACR")){
								getBtnFacturaProveedor().setEnabled(true);
							}else{
								getBtnFacturaProveedor().setEnabled(false);
							}
							
							if (documento.getCodigo().equals("FACI"))
								IVA = IVA_CERO;
							else
								IVA = Parametros.getIVA() / 100;
						}
					}													
				} catch (GenericBusinessException e1) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e1.printStackTrace();
				}
			}
		});

		getCmbListaPrecio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if ((getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE)) {
					if (!("".equals(getTxtCodigoProducto().getText()))) {
						ListaPrecioIf listaPrecio = null;
						if (getCmbListaPrecio().getSelectedItem() != null)
							listaPrecio = (ListaPrecioIf) getCmbListaPrecio().getSelectedItem();
						Long productoId = productoIf.getId();
						Collection precios = FacturacionFinder.findPrecio(listaPrecio.getId(), productoId);
						BigDecimal precio = BigDecimal.ZERO;

						if (precios.size() > 0)
							precio = ((PrecioIf) precios.iterator().next()).getPrecio();

						getTxtPrecio().setText(precio.toString());
						getTxtPrecioReal().setText(precio.toString());

						try {
							inicializarTextFieldsPrecio();
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					if ( getCmbListaPrecio().getSelectedItem() != null )
						getBtnBuscarProducto().setEnabled(true);
				}
			}
		});
		
		getCmbCaja().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento) {
				setearCaja();
				setearPuntoImpresion();
			}
		});
	}

	private void inicializarTextFieldsPrecio() throws GenericBusinessException {
		DocumentoIf documentoIf = (DocumentoIf) getCmbDocumento().getSelectedItem();
		if ( documentoIf != null ){
			ListaPrecioIf listaPrecioIf = (ListaPrecioIf) getCmbListaPrecio().getSelectedItem();
			if ( listaPrecioIf != null ){
				Collection precios = SessionServiceLocator.getPrecioSessionService().findPrecioByListaprecioId(listaPrecioIf.getId());
				Iterator preciosIterator = precios.iterator();
				PrecioIf precioIf = null;
				boolean flag = true;
		
				while (preciosIterator.hasNext() && flag) {
					precioIf = (PrecioIf) preciosIterator.next();
					if (productoIf != null)
						if (precioIf.getProductoId().compareTo(productoIf.getId()) == 0)
							flag = false;
				}
				
				String esBonificacion = ((DocumentoIf) SessionServiceLocator.getDocumentoSessionService().findDocumentoByNombre(documentoIf.getNombre()).iterator().next()).getBonificacion();
				if (OPCION_SI.equals(esBonificacion)) {
					getTxtPrecio().setEnabled(false);
					getTxtPrecio().setEditable(false);
					getTxtPrecio().setText("0.00");
					getTxtPrecioReal().setEnabled(false);
					getTxtPrecioReal().setEditable(false);
					getTxtPrecioReal().setText("0.00");
				} else if (precioIf != null) { 
					// if (OPCION_SI.equals(precioIf.getCambiarPrecio()) &&
					// tipoReferencia.equals(REFERENCIA_NINGUNO)) {
					if (OPCION_SI.equals(precioIf.getCambiarPrecio()) && tipoReferencia == TipoReferenciaPedido.NINGUNO) {
						getTxtPrecio().setEnabled(true);
						getTxtPrecio().setEditable(true);
						getTxtPrecioReal().setEnabled(true);
						getTxtPrecioReal().setEditable(true);
					}
				}
			}
		}
	}

	private void inicializarObjetosShowSaveMode() {
		setearMontoDescuento();
		setearTipoDocumento();
		setearCaja();
		setearPuntoImpresion();
	}

	private void setearPuntoImpresion() {
		try {
			if (getCmbTipoDocumento().getSelectedItem() != null && getCmbCaja().getSelectedItem() != null) {
				Collection puntoI = SessionServiceLocator.getPuntoImpresionSessionService().findPuntoImpresionByTipoDocumentoAndByCajaId(tipoDocumentoIf.getId(), cajaIf.getId());
				if (puntoI.size() != 0) {
					PuntoImpresionIf puntoImpresion = (PuntoImpresionIf) puntoI.iterator().next();
					Collection usuarioPuntoI = SessionServiceLocator.getUsuarioPuntoImpresionSessionService().findUsuarioPuntoImpresionByPuntoImpresionAndByUsuarioId(puntoImpresion.getId(),((UsuarioIf) Parametros.getUsuarioIf()).getId());
					if (usuarioPuntoI.size() != 0) {
						UsuarioPuntoImpresionIf usuarioPuntoImpresion = (UsuarioPuntoImpresionIf) usuarioPuntoI.iterator().next();
						puntoImpresionIf = SessionServiceLocator.getPuntoImpresionSessionService().getPuntoImpresion(usuarioPuntoImpresion.getPuntoimpresionId());
						getTxtPuntoImpresion().setText(puntoImpresionIf.getSerie());
					}
				} else {
					puntoImpresionIf = null;
					getTxtPuntoImpresion().setText("");
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void setearCaja() {
		if (getCmbCaja().getSelectedItem() != null)
			cajaIf = ((CajaIf) getCmbCaja().getSelectedItem());
	}

	private void setearTipoDocumento() {
		if (getCmbTipoDocumento().getSelectedItem() != null)
			tipoDocumentoIf = ((TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem());
	}

	private void setearMontoDescuento() {
		if (getCmbFormaPago().getSelectedItem() != null)
			montoDescuento = ((FormaPagoIf) getCmbFormaPago().getSelectedItem()).getDescuentoVenta().doubleValue();
	}

	private void inicializarPanelShowSaveMode() {
		getTxtCodigo().setEditable(false);
		//getTxtOficinaEmpresa().setEnabled(true);
		//getTxtOficinaEmpresa().setText(Parametros.getNombreEmpresa() + " - " + Parametros.getNombreOficina());
		//getTxtOficinaEmpresa().setEditable(false);
		getTxtDiasValidez().setEnabled(true);
		getTxtDiasValidez().setText("1");
		getTxtDiasValidez().grabFocus();
		getTxtEscojaReferencia().setEditable(false);
		getTxtObservacion().setEnabled(true);
		getTxtCorporacion().setEditable(false);
		getTxtCliente().setEditable(false);
		getTxtInformacionAdc().setEditable(false);
		getTxtClienteOficina().setEditable(false);
		getTxtIdentificacion().setEnabled(true);
		getTxtTelefono().setEnabled(true);
		getTxtContacto().setEnabled(true);
		getTxtDireccion().setEnabled(true);
		getTxtReferencia().setEnabled(true);
		getTxtPuntoImpresion().setEnabled(true);
		getTxtPuntoImpresion().setEditable(false);
		getTxtLinea().setEnabled(true);
		getTxtLinea().setEditable(false);
		getCmbLote().setEnabled(true);
		getTxtDescripcion().setEnabled(true);
		getTxtCantidadPedida().setEditable(false);
		getTxtCantidadPedida().setText("0");
		getTxtCantidad().setEditable(false);
		getTxtCantidad().setText("0");
		getTxtPrecio().setEnabled(true);
		getTxtPrecioReal().setEnabled(true);
		getTxtPorcentajeDescuentoAgencia().setEnabled(true);
		getTxtPorcentajeDescuentosVarios().setEnabled(true);
		getTxtPorcentajeDescuentoAgencia().setText("0");
		getTxtPorcentajeDescuentosVarios().setText("0");
		getTxtValorFinal().setEnabled(true);
		getTxtValorFinal().setEditable(false);
		getTxtDescuentoFinal().setEnabled(true);
		getTxtDescuentoFinal().setEditable(false);
		getTxtDescuentosVariosTotal().setEnabled(true);
		getTxtDescuentosVariosTotal().setEditable(false);
		getTxtIVAFinal().setEnabled(true);
		getTxtIVAFinal().setEditable(false);
		getTxtICEFinal().setEnabled(true);
		getTxtICEFinal().setEditable(false);
		getTxtOtroImpuestoFinal().setEnabled(true);
		getTxtOtroImpuestoFinal().setEditable(false);
		getTxtTotalFinal().setEditable(false);
		getTxtTotalFinal().setEnabled(true);
		getCmbTipoDocumento().setEnabled(true);
		getCmbFechaPedido().setEnabled(true);
		getCmbFechaPedido().setEditable(false);
		getCmbFechaVencimiento().setEditable(false);
		getTxtPorcentajeComision().setEditable(false);
		getTxtValorComision().setEditable(false);
		getCmbFormaPago().setEnabled(true);
		getCmbMoneda().setEnabled(true);
		getCmbListaPrecio().setEnabled(true);
		getCmbCaja().setEnabled(true);
		getCmbEstado().setEnabled(true);
		getCmbTipoReferencia().setEnabled(false);
		getBtnEscojaReferencia().setEnabled(false);
		getCmbVendedor().setEnabled(true);
		getCmbBodega().setEnabled(true);
		
		ParametroEmpresaIf fundacionRequerida = buscarParametroActivarDonacion();
		
		if (fundacionRequerida != null && fundacionRequerida.getValor().equals("S")) {
			this.getLblFundacion().setVisible(true);
			this.getCmbFundacion().setVisible(true);
			this.getCmbFundacion().setEnabled(true);
		} else {
			this.getLblFundacion().setVisible(false);
			this.getCmbFundacion().setVisible(false);
		}
		
		getCmbOrigenDocumento().setEnabled(true);
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getBtnBuscarClienteOficina().setEnabled(true);
		getCmbTipoIdentificacion().setEnabled(false);
		getCmbDocumento().setEnabled(true);
		getCmbMotivo().setEnabled(false);
		getCmbLote().setSelectedItem(null);
		getCmbLote().removeAllItems();
		getCmbLote().setEnabled(true);
		getBtnGenerarFactura().setEnabled(false);
		getBtnAgregarDetalle().setEnabled(true);
		
		if ( getCmbListaPrecio().getSelectedItem() != null )
			getBtnBuscarProducto().setEnabled(true);
		else
			getBtnBuscarProducto().setEnabled(false);
		
		getJtpPedido().setSelectedIndex(0);
		
		if (tipoDocumentoIf != null && tipoDocumentoIf.getDescuentoespecial().equals(OPCION_SI)) {
			getRbDescuentoGlobalPorcentaje().setSelected(true);
			getTxtDescuentoGlobalPorcentaje().grabFocus();
		}
		
		TipoReferenciaPedido tr = (TipoReferenciaPedido) getCmbTipoReferencia().getSelectedItem(); 
		
		if (tr == TipoReferenciaPedido.NINGUNO){
			getTxtPorcentajeComision().setEditable(true);
		}
		else{
			getTxtPorcentajeComision().setEditable(false);
		}
		
		getTxtFacturaProveedor().setText("");
		
		getJpNegociacion().setVisible(false);
		getTxtPorcentajeNegociacionDirecta().setEditable(false);
		getTxtDsctoCompraPorcentaje().setEditable(false);
		getTxtClienteNegociacion().setEditable(false);
		getBtnBuscarClienteNegociacion().setEnabled(false);
		
		inicializarSeccionReporte();
	}

	private void inicializarSeccionReporte() {
		// reporte
		presupuestoDetalleColeccionP = null;
		presupuestoDetalleColeccionP = new Vector<PresupuestoDetalleIf>();
		getTxtProveedorP().setText("");
		getTxtProductoP().setText("");
		getTxtConceptoPresupuestoDetalleP().setText("");
		getTxtPrecioVentaP().setText("");
		getTxtCantidadP().setText("");
		cleanTablaPresupuesto();
	}

	ActionListener oActionListenerCmbFechaPedido = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			fechaPedido = (Date) ((DateComboBox) eventoInicio.getSource()).getDate();
			java.util.Date fechaHoy = new java.util.Date();

			if ((fechaPedido.getDate() < fechaHoy.getDate()) && (fechaPedido.getMonth() < fechaHoy.getMonth()) && (fechaPedido.getYear() < fechaHoy.getYear())) {
				SpiritAlert.createAlert("Por favor seleccione una fecha de inicio valida!", SpiritAlert.INFORMATION);
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(fechaHoy);
				fechaPedido = fechaHoy;
				getCmbFechaPedido().setCalendar(calendar);
			}
		}
	};

	ActionListener oActionListenerCmbFechaPedidoUpdate = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			fechaPedido = (Date) ((DateComboBox) eventoInicio.getSource()).getDate();
		}
	};

	ActionListener oActionListenerCmbCaja = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			cajaIf = (CajaIf) getCmbCaja().getModel().getSelectedItem();
			if (getCmbCaja().getSelectedItem() != null && getCmbTipoDocumento().getSelectedItem() != null) {
				setearPuntoImpresion();
			}
		}
	};
	
	ActionListener oActionListenerCmbTipoDocumento = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			if (tipoReferencia != null && getTblPedidoDetalle().getRowCount() > 0 && !tipoReferencia.equals(getCmbTipoReferencia().getSelectedItem().toString())) {
				int opcion = 0;
				opcion = JOptionPane.showOptionDialog(null, "Si cambia el Tipo de Documento, se eliminarán todos los detalles del pedido.\n¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION)
					limpiarDetallesPedido();
			}
		}
	};

	ActionListener oActionListenerCmbTipoReferencia = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			// si escogio como tipo de referencia presupuesto, mando a cargar
			// todos los presupuestos
			// del cliente oficina que escogio, con estado pendiente
			int opcion = 0;
			if (tipoReferencia != null && getTblPedidoDetalle().getRowCount() > 0 && !tipoReferencia.equals(getCmbTipoReferencia().getSelectedItem().toString())) {
				opcion = JOptionPane.showOptionDialog(null, "Si cambia el Tipo de Referencia o de Documento, se eliminarán todos los detalles.\n¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION)
					limpiarDetallesPedido();
				else
					getCmbTipoReferencia().setSelectedItem(tipoReferencia);
			}
			
			if (opcion == JOptionPane.YES_OPTION || tipoReferencia.equals("")) {
				getTxtEscojaReferencia().setText("");
				getTxtReferencia().setText("");
				comisionAgenciaTotal = 0D;
				getTxtValorComision().setText("0.00");
				porcentajeComisionAgencia = 0D;
				getTxtPorcentajeComision().setText("0");
				
				// proveedorIf = null;
				presupuestoIf = null;
				planMedioIf = null;
				
				TipoReferenciaPedido tr = (TipoReferenciaPedido) getCmbTipoReferencia().getSelectedItem();
				if ( TipoReferenciaPedido.PRESUPUESTO == tr || 
						TipoReferenciaPedido.PLAN_MEDIOS == tr || 
						TipoReferenciaPedido.COMBINADO == tr) {
					getBtnEscojaReferencia().setEnabled(true);
					getBtnBuscarProducto().setEnabled(false);
					getBtnAgregarDetalle().setEnabled(false);
					getBtnEliminarDetalle().setEnabled(false);
					getTxtCantidad().setEditable(false);
					getTxtCantidadPedida().setEditable(false);
					getTxtPrecio().setEditable(false);
					getTxtPrecioReal().setEditable(false);
					getTxtPorcentajeDescuentoAgencia().setEditable(false);
					getTxtPorcentajeDescuentosVarios().setEditable(false);
					getCmbLote().setEnabled(false);
					getTxtDescuentoGlobalPorcentaje().setEditable(false);
					getTxtDescuentoGlobalValor().setEditable(false);
					getRbDescuentoGlobalPorcentaje().setEnabled(false);
					getRbDescuentoGlobalValor().setEnabled(false);
					getBtnActualizarTotales().setEnabled(false);
					getTxtPorcentajeComision().setEditable(false);
					tipoReferencia = tr;
				}				
				// si no escogio ninguno limpio la tabla y los objetos
				else if ( TipoReferenciaPedido.NINGUNO == tr ) {
					getBtnEscojaReferencia().setEnabled(false);
					getTxtReferencia().setText("");
					getBtnBuscarProducto().setEnabled(true);
					getBtnAgregarDetalle().setEnabled(true);
					getBtnEliminarDetalle().setEnabled(true);
					getTxtCantidad().setEnabled(true);
					getTxtCantidad().setEditable(true);
					getTxtCantidadPedida().setEnabled(true);
					getTxtCantidadPedida().setEditable(true);
					getTxtPrecio().setEnabled(true);
					getTxtPrecio().setEditable(true);
					getTxtPrecioReal().setEnabled(true);
					getTxtPrecioReal().setEditable(true);
					getTxtPorcentajeDescuentoAgencia().setEnabled(true);
					getTxtPorcentajeDescuentoAgencia().setEditable(true);
					getTxtPorcentajeDescuentosVarios().setEnabled(true);
					getTxtPorcentajeDescuentosVarios().setEditable(true);
					getCmbLote().setEnabled(true);
					getTxtDescuentoGlobalPorcentaje().setEnabled(true);
					getTxtDescuentoGlobalPorcentaje().setEditable(true);
					getTxtDescuentoGlobalValor().setEnabled(true);
					getTxtDescuentoGlobalValor().setEditable(true);
					getRbDescuentoGlobalPorcentaje().setEnabled(true);
					getRbDescuentoGlobalValor().setEnabled(true);
					getBtnActualizarTotales().setEnabled(true);
					getTxtPorcentajeComision().setEditable(true);
					tipoReferencia = tr;
				}
			}
		}
	};
	
	ActionListener oActionListenerBtnEscojaReferencia = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			try {
				limpiarVectores();
				TipoReferenciaPedido tr = (TipoReferenciaPedido) getCmbTipoReferencia().getSelectedItem(); 
				
				/*if(TipoReferenciaPedido.PRESUPUESTO == tr){
					//cuando se eligen varios planes para facturar
					if(getCbMultipleFacturacion().isSelected()){
						FacturacionMultipleModel jdFacturacionMultipleModel = new FacturacionMultipleModel(Parametros.getMainFrame(), clienteOficinaIf.getId(), "P");
						ArrayList<Object> presupuestosSeleccionados = jdFacturacionMultipleModel.getPresupuestosSeleccionados();
						
						ArrayList<Object> presupuestosSeleccionadosFiltrados = new ArrayList<Object>();
						
						for(int i=0; i<presupuestosSeleccionados.size(); i++){
							if(presupuestosSeleccionados.get(i).getClass().toString().equals("class com.spirit.medios.entity.PresupuestoEJB")){
								PresupuestoIf presupuestoSeleccionadoIf = (PresupuestoIf)presupuestosSeleccionados.get(i);
								
								Collection presupuestosDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(presupuestoSeleccionadoIf.getId());
								Collection presupuestosDetalleFacturacion = (Collection)DeepCopy.copy(presupuestosDetalle);
											
								Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
								HashMap<Long,ClienteOficinaIf> proveedoresNegociacionDirectaMap = new HashMap<Long,ClienteOficinaIf>();
								HashMap<Long,ClienteOficinaIf> proveedoresComisionPuraMap = new HashMap<Long,ClienteOficinaIf>();
								
								Map<Long,Long> presupuestoDetallesFacturadosMap = new HashMap<Long,Long>();			
								boolean habilitarFacturacionCliente = true;
								
								habilitarFacturacionCliente = cargarOpcionesFacturacion(
										presupuestosDetalleFacturacion, presupuestosDetalleIt,
										proveedoresNegociacionDirectaMap,
										proveedoresComisionPuraMap,
										presupuestoDetallesFacturadosMap,
										habilitarFacturacionCliente);
								
								if(habilitarFacturacionCliente){
									presupuestosSeleccionadosFiltrados.add(presupuestoSeleccionadoIf);
								}
							}else if(presupuestosSeleccionados.get(i).getClass().toString().equals("class com.spirit.medios.entity.PlanMedioEJB")){
								PlanMedioIf planMedioIf = (PlanMedioIf)presupuestosSeleccionados.get(i);
								presupuestosSeleccionadosFiltrados.add(planMedioIf);
							}
						}						
						
						if(presupuestosSeleccionadosFiltrados.size() > 0){
							//limpiarVectores();
							facturarVariosPresupuestosCompletos(presupuestosSeleccionadosFiltrados);
						}
					}
					//caso normal de elegir un solo presupuesto
					else{
						buscarPresupuesto();
					}					
				}
				else if(TipoReferenciaPedido.PLAN_MEDIOS == tr){
					//cuando se eligen varios planes para facturar
					if(getCbMultipleFacturacion().isSelected()){
						FacturacionMultipleModel jdFacturacionMultipleModel = new FacturacionMultipleModel(Parametros.getMainFrame(), clienteOficinaIf.getId(), "M");
						ArrayList<Object> presupuestosSeleccionados = jdFacturacionMultipleModel.getPresupuestosSeleccionados();
						
						if(presupuestosSeleccionados.size() > 0){
							//limpiarVectores();
							facturarVariosPresupuestosCompletos(presupuestosSeleccionados);
						}
					}
					//caso normal de elegir un solo plan
					else{
						buscarPlanMedios();
					}
				}
				else if(TipoReferenciaPedido.COMBINADO == tr){
					//en el caso combinado siempre se deben elegir minimo dos presupuestos o planes de medios
					FacturacionMultipleModel jdFacturacionMultipleModel = new FacturacionMultipleModel(Parametros.getMainFrame(), clienteOficinaIf.getId(), "C");
					ArrayList<Object> presupuestosSeleccionados = jdFacturacionMultipleModel.getPresupuestosSeleccionados();
					
					if(presupuestosSeleccionados.size() > 0){
						//limpiarVectores();
						facturarVariosPresupuestosCompletos(presupuestosSeleccionados);
					}
				}*/
				
				if(getCbMultipleFacturacion().isSelected() || TipoReferenciaPedido.COMBINADO == tr){
					
					String tipoReferencia = "C"; //combinada
					if(TipoReferenciaPedido.PRESUPUESTO == tr){
						tipoReferencia = "P"; //presupuesto
					}
					else if(TipoReferenciaPedido.PLAN_MEDIOS == tr){
						tipoReferencia = "M"; //plan de medios
					}
					
					FacturacionMultipleModel jdFacturacionMultipleModel = new FacturacionMultipleModel(Parametros.getMainFrame(), clienteOficinaIf.getId(), tipoReferencia);
					ArrayList<Object> presupuestosSeleccionados = jdFacturacionMultipleModel.getPresupuestosSeleccionados();
					
					ArrayList<Object> presupuestosSeleccionadosFiltrados = new ArrayList<Object>();
					
					for(int i=0; i<presupuestosSeleccionados.size(); i++){
						if(presupuestosSeleccionados.get(i).getClass().toString().equals("class com.spirit.medios.entity.PresupuestoEJB")){
							PresupuestoIf presupuestoSeleccionadoIf = (PresupuestoIf)presupuestosSeleccionados.get(i);
							
							Collection presupuestosDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(presupuestoSeleccionadoIf.getId());
							Collection presupuestosDetalleFacturacion = (Collection)DeepCopy.copy(presupuestosDetalle);
										
							Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
							HashMap<Long,ClienteOficinaIf> proveedoresNegociacionDirectaMap = new HashMap<Long,ClienteOficinaIf>();
							HashMap<Long,ClienteOficinaIf> proveedoresComisionPuraMap = new HashMap<Long,ClienteOficinaIf>();
							
							Map<Long,Long> presupuestoDetallesFacturadosMap = new HashMap<Long,Long>();			
							boolean habilitarFacturacionCliente = true;
							
							habilitarFacturacionCliente = cargarOpcionesFacturacion(
									presupuestosDetalleFacturacion, presupuestosDetalleIt,
									proveedoresNegociacionDirectaMap,
									proveedoresComisionPuraMap,
									presupuestoDetallesFacturadosMap,
									habilitarFacturacionCliente);
							
							if(habilitarFacturacionCliente){
								presupuestosSeleccionadosFiltrados.add(presupuestoSeleccionadoIf);
							}
						}else if(presupuestosSeleccionados.get(i).getClass().toString().equals("class com.spirit.medios.entity.PlanMedioEJB")){
							PlanMedioIf planMedioIf = (PlanMedioIf)presupuestosSeleccionados.get(i);
							presupuestosSeleccionadosFiltrados.add(planMedioIf);
						}
					}						
					
					if(presupuestosSeleccionadosFiltrados.size() > 0){
						//limpiarVectores();
						facturarVariosPresupuestosCompletos(presupuestosSeleccionadosFiltrados);
					}
				}
				else if(TipoReferenciaPedido.PRESUPUESTO == tr){
					buscarPresupuesto();
				}
				else if(TipoReferenciaPedido.PLAN_MEDIOS == tr){
					buscarPlanMedios();
				}
				
				
				getJtpPedido().setSelectedIndex(JTP_PEDIDO_DETALLE_TAB);
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
			}
		}
	};
	
	public void limpiarVectores(){
		cleanTabla();
		cleanTablaPresupuesto();
		modelPedidoDetalle = (DefaultTableModel) getTblPedidoDetalle().getModel();
		modelPresupuestoDetalleP = (DefaultTableModel) getTblPresupuestoDetalleP().getModel();
		planesMedioIf = new Vector<PlanMedioIf>();
		presupuestosIf = new Vector<PresupuestoIf>();
		pedidoDetalleColeccion = new Vector<PedidoDetalleIf>();
		presupuestoDetalleFacturadoColeccion = new Vector<PresupuestoDetalleIf>();
		presupuestoFacturacionColeccion = new Vector<PresupuestoFacturacionIf>();
		facturaDetalleTempColeccion = new Vector<PedidoDetalleIf>();
		pedidoDetalleEliminadas = new Vector<PedidoDetalleIf>();
		listaOrdenMedioTotal = new ArrayList<OrdenMedioIf>();
		listaPlanMedioFacturacionEscogido = new ArrayList<PlanMedioFacturacionIf>();
		presupuestoDetalleColeccionP = new Vector<PresupuestoDetalleIf>();
	}
	
	private void facturarVariosPresupuestosCompletos(ArrayList<Object> presupuestosSeleccionados) {
		//divido el arreglo de presupuestos en dos arreglos, uno de presupuestos y otro de planes e medio
		ArrayList<PlanMedioIf> presupuestosSeleccionadosPlanMedios = new ArrayList<PlanMedioIf>();
		ArrayList<PresupuestoIf> presupuestosSeleccionadosPresupuesto = new ArrayList<PresupuestoIf>();
		
		for(int i=0; i<presupuestosSeleccionados.size(); i++){
			if(presupuestosSeleccionados.get(i).getClass().toString().equals("class com.spirit.medios.entity.PlanMedioEJB")){
				PlanMedioIf planMedioIf = (PlanMedioIf)presupuestosSeleccionados.get(i);
				presupuestosSeleccionadosPlanMedios.add(planMedioIf);
			}else if(presupuestosSeleccionados.get(i).getClass().toString().equals("class com.spirit.medios.entity.PresupuestoEJB")){
				PresupuestoIf presupuestoIf = (PresupuestoIf)presupuestosSeleccionados.get(i);
				presupuestosSeleccionadosPresupuesto.add(presupuestoIf);
			}
		}
		
		if(presupuestosSeleccionadosPlanMedios.size() > 0){
			facturarVariosPresupuestosCompletosPlanMedios(presupuestosSeleccionadosPlanMedios);
		}
		
		if(presupuestosSeleccionadosPresupuesto.size() > 0){
			//importante setear este tipo de presupuesto para que del lado del servidor se revise si hay negociaciones que facturar a proveedores
			tipoPresupuestoFacturacion = TIPO_PRESUPUESTO_FACTURACION_CLIENTE;
			facturarVariosPresupuestosCompletosPresupuesto(presupuestosSeleccionadosPresupuesto);
		}
	}
	
	private void facturarVariosPresupuestosCompletosPlanMedios(ArrayList<PlanMedioIf> presupuestosSeleccionados) {
		try {
			//seteo uno de los varios escojidos solo para los datos del reporte
			planMedioIf = (PlanMedioIf)presupuestosSeleccionados.iterator().next();				
				
			planesMedioIf = new Vector<PlanMedioIf>();
			ArrayList<OrdenMedioIf> listaOrdenMedioIf = null; 
			String referencia = "";
			String observacion = "";
			
			for(int i=0; i<presupuestosSeleccionados.size(); i++){
				PlanMedioIf planMedio = (PlanMedioIf)presupuestosSeleccionados.get(i);
				
				// Se busca la lista de ordenes de medio x planMedioId
				ArrayList<OrdenMedioIf> listaOrdenMedio = (ArrayList<OrdenMedioIf>)SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planMedio.getId());
				
				if(listaOrdenMedioIf != null){
					listaOrdenMedioIf.addAll(listaOrdenMedio);
				}else{
					listaOrdenMedioIf = listaOrdenMedio;
				}
				
				if(listaPlanMedioFacturacionEscogido.isEmpty()){
					listaPlanMedioFacturacionEscogido = SessionServiceLocator.getPlanMedioSessionService().getPlanMedioFacturacionTotal(planMedio, true);
				}else{
					ArrayList<PlanMedioFacturacionIf> listaPlanMedioFacturacionEscogidoTemp = SessionServiceLocator.getPlanMedioSessionService().getPlanMedioFacturacionTotal(planMedio, true);
					listaPlanMedioFacturacionEscogido.addAll(listaPlanMedioFacturacionEscogidoTemp);
				}
				planesMedioIf.add(planMedio);
				
				if(referencia.equals("")){
					referencia = planMedio.getCodigo();
				}else{
					referencia = referencia + ";" + planMedio.getCodigo();
				}
				
				if(observacion.equals("")){
					observacion = planMedio.getConcepto();
				}else{
					observacion = observacion + ";" + planMedio.getConcepto();
				}
			}
			
			//referencia y observacion
			getTxtEscojaReferencia().setText(referencia);
			getTxtObservacion().setText(observacion);
			
			// Selecciono el vendedor segun el ejecutivo que hizo la orden 
			OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(planMedioIf.getOrdenTrabajoDetalleId());
			OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
			EmpleadoIf vendedor = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenTrabajo.getEmpleadoId());
			getCmbVendedor().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbVendedor(), vendedor.getId()));
			
						
			if(listaOrdenMedioIf != null){
				// Se crea una lista que contendra a todos los
				// proveedores del Plan de Medio seleccionado
				ArrayList<String> listaProveedores = new ArrayList<String>();
				
				Iterator ordenMedioTmpIt = listaOrdenMedioIf.iterator();
				while(ordenMedioTmpIt.hasNext()){
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenMedioTmpIt.next();
					
					int tmp = 0;
					
					// for que verifica si un proveedor ya se encuentra en la lista de proveedores
					// debido a que puede varias ordenes de medio para un mismo proveedor
					for (String proveedorAndProducto:listaProveedores) {
						String[] key = proveedorAndProducto.split("-");
						Long proveedorId = Long.parseLong(key[0]);
						Long productoId = Long.parseLong(key[1]);
						
						if (proveedorId.compareTo(ordenMedioIf.getProveedorId()) != 0 || (proveedorId.compareTo(ordenMedioIf.getProveedorId()) == 0 && productoId.compareTo(ordenMedioIf.getProductoProveedorId()) != 0)) {
							tmp++;
						}
					}
					if (tmp == listaProveedores.size()) {
						listaProveedores.add(String.valueOf(ordenMedioIf.getProveedorId()) + "-" + String.valueOf(ordenMedioIf.getProductoProveedorId()));
					}
				}
				
				formaFacturacion = 1;
				tipoFormaPago = TIPO_FORMA_PAGO_COMPLETO;
				
				cargarDetallesPlanMedioCompleto(listaOrdenMedioIf, listaProveedores);
			}			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void facturarVariosPresupuestosCompletosPresupuesto(ArrayList<PresupuestoIf> presupuestosSeleccionados) {
		try {
			
			TipoReferenciaPedido tr = (TipoReferenciaPedido) getCmbTipoReferencia().getSelectedItem(); 
			if (TipoReferenciaPedido.COMBINADO != tr && pedidoDetalleColeccion.size() != 0) {
				Iterator it = pedidoDetalleColeccion.iterator();
				while (it.hasNext()) {
					PedidoDetalleIf detalleRemovido = (PedidoDetalleIf) it.next();
					if (detalleRemovido.getId() != null)
						pedidoDetalleEliminadas.add(detalleRemovido);
				}
				cleanTabla();
				pedidoDetalleColeccion.clear();
			}
			
			//seteo uno de los varios escojidos solo para los datos del reporte
			presupuestoIf = (PresupuestoIf)presupuestosSeleccionados.iterator().next();
							
			String referencia = TipoReferenciaPedido.COMBINADO != tr?"":getTxtEscojaReferencia().getText();
			String observacion = TipoReferenciaPedido.COMBINADO != tr?"":getTxtObservacion().getText();
			
			for(int i=0; i<presupuestosSeleccionados.size(); i++){
				PresupuestoIf presupuesto = (PresupuestoIf)presupuestosSeleccionados.get(i);
				
				if(referencia.equals("")){
					referencia = presupuesto.getCodigo();
				}else{
					referencia = referencia + ";" + presupuesto.getCodigo();
				}
				
				if(observacion.equals("")){
					observacion = presupuesto.getConcepto();
				}else{
					observacion = observacion + ";" + presupuesto.getConcepto();
				}
			}		

			getTxtEscojaReferencia().setText(referencia);
			getTxtObservacion().setText(observacion);
			
			getCmbFormaPago().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbFormaPago(), presupuestoIf.getFormaPagoId()));
			getTxtDiasValidez().setText(presupuestoIf.getDiasValidez().toString());
			
			// sap
			if(presupuestoIf.getAutorizacionSap() != null && !presupuestoIf.getAutorizacionSap().equals("")){
				getTxtAutorizacionSAP().setText(presupuestoIf.getAutorizacionSap());
			}
			
			// Selecciono el vendedor segun el ejecutivo que hizo la orden de
			// trabajo de donde salio el presupuesto
			OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuestoIf.getOrdentrabajodetId());
			OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
			EmpleadoIf vendedor = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenTrabajo.getEmpleadoId());
			getCmbVendedor().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbVendedor(), vendedor.getId()));
			
			// Selecciono el equipo y director del ejecutivo
			HashMap buscarDirectorMap = new HashMap();
			buscarDirectorMap.put("equipoId", ordenTrabajo.getEquipoId());
			buscarDirectorMap.put("rol", "DCU");
			Collection directorEquipos = SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByQuery(buscarDirectorMap);
			if(directorEquipos.size() > 0){
				EquipoEmpleadoIf directorEquipo = (EquipoEmpleadoIf)directorEquipos.iterator().next();
				EmpleadoIf director = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(directorEquipo.getEmpleadoId());
				EquipoTrabajoIf equipoTrabajoDirector = SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajo(directorEquipo.getEquipoId());
				getCmbDirectorCuentas().setSelectedItem(equipoTrabajoDirector.getCodigo() + " - " + director);
			}
			
			
			//cargo los detalles de cada uno de los prepuestos
			//aqui conecta con la facturación normal de un presupuesto ya sea por Servicio, Reembolso, Comisión, Exterior, etc.
			for(int i=0; i<presupuestosSeleccionados.size(); i++){
				presupuestoIf = (PresupuestoIf)presupuestosSeleccionados.get(i);
				cargarDetallesPresupuesto(null);
				presupuestosIf.add(presupuestoIf);
			}
			
			
			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	// listener para generar factura
	ActionListener oActionListenerGenerarFactura = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			if (validateFields()) {
				PedidoIf pedidoAnterior = registrarPedidoAnterior();
				PedidoIf pedido = registrarPedidoForUpdate();
				try {
					pedido = SessionServiceLocator.getPedidoSessionService().actualizarPedido(pedido, pedidoDetalleColeccion, pedidoAnterior, pedidoDetalleEliminadas,tarea);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				}
				facturar();
			}
		}
	};
	
	ActionListener oActionListenerActualizarTotales = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			actualizarTotales(pedidoDetalleColeccion, true);
			getTxtPorcentajeComision().setText("0");
		}
	};
	
	private void limpiarDetallesPedido() {
		while (getTblPedidoDetalle().getRowCount() > 0) {
			getTblPedidoDetalle().getSelectionModel().setSelectionInterval(0,0);
			eliminarDetallePedido();
		}
		
		getTxtDescuentoGlobalPorcentaje().setText("0");
		getTxtDescuentoGlobalValor().setText("0");
	}
						
	/*
	 * Compara cada uno de los PlanMedioFacturacion Total generados por los
	 * Comerciales y Ordenes de Medio Orden de Medio Detalle y
	 * OrdenMedioDetalleMapa del Plan de Medio a ser Pedido o Facturado con los
	 * PlanMedioFacturacion Guardados en la DB
	 */
	private ArrayList<PlanMedioFacturacionIf> getPlanMedioFacturacionNoGuardado(ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacion){
		ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacionNoGuardado = new ArrayList<PlanMedioFacturacionIf>();
		try{
			// collection de PlanesMedioFacturados existentes en la base de
			// datos
			Map aMap = new HashMap();
			aMap.put("planMedioId", planMedioIf.getId());
			String[] estados = {"P", "F"}; // PENDIENTE, FACTURADO
			Collection planMedioFacturacionGuardColl = SessionServiceLocator.getPlanMedioFacturacionSessionService().findPlanMedioFacturacionByQueryAndByEstados(aMap, estados);
			
			// Se recorre cada PlanMedioFacturacion de la lista Total de
			// PlanMedioFacturacion generado por todos los Comerciales
			// con sus respectivas ordenes de Medio del Plan de Medio a ser
			// pedido o facturado
			for(PlanMedioFacturacionIf planMedioFacturacion: listPlanMedioFacturacion){
				
				boolean agregar = true;
				Iterator planMedioFacturacionGuardCollIt = planMedioFacturacionGuardColl.iterator();
				// se recorre la collection de los Planes de Medio Facturados
				// existentes en la base
				while(planMedioFacturacionGuardCollIt.hasNext()){
					PlanMedioFacturacionIf planMedioFacturacionG = (PlanMedioFacturacionIf)planMedioFacturacionGuardCollIt.next();
					
					// se compara el PlanMedioFacturacion de la lista de
					// PlanesMedioFacturacion Total con
					// un PlanMedioFacturacion de la Collection obtenida de la
					// tabla PLAN_MEDIO_FACTURACION de la DB
					if(planMedioFacturacion.getCanje().compareTo(planMedioFacturacionG.getCanje())==0 
							&& planMedioFacturacion.getProductoClienteId().compareTo(planMedioFacturacionG.getProductoClienteId())==0
							&& (planMedioFacturacionG.getCampanaProductoVersionId() == null || planMedioFacturacion.getCampanaProductoVersionId().compareTo(planMedioFacturacionG.getCampanaProductoVersionId()) == 0) 
							&& planMedioFacturacion.getOrdenMedioId().compareTo(planMedioFacturacionG.getOrdenMedioId())==0 
							// &&
							// planMedioFacturacion.getPorcentajeCanje().compareTo(planMedioFacturacionG.getPorcentajeCanje())==0
							&& planMedioFacturacion.getProveedorId().compareTo(planMedioFacturacionG.getProveedorId())==0
							&& planMedioFacturacion.getOrdenMedioDetalleId().compareTo(planMedioFacturacionG.getOrdenMedioDetalleId()) == 0
							&& planMedioFacturacion.getOrdenMedioDetalleMapaId().compareTo(planMedioFacturacionG.getOrdenMedioDetalleMapaId()) == 0){
					
						agregar = false;
					}
				}
				// si tienen los mismos datos quiere decir que esta facturada
				// entonces no se la debe agregar
				// de lo contrario se la agraga a la lista de
				// listPlanMedioFacturacionNoGuardado
				if(agregar){
					listPlanMedioFacturacionNoGuardado.add(planMedioFacturacion);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listPlanMedioFacturacionNoGuardado;
	}					
	
	public void obtenerPeriodoPlanMedioFacturacion(PlanMedioFacturacionIf planMedioFacturacionIf){
		java.sql.Date fechaInicio = Utilitarios.fromTimestampToSqlDate(planMedioFacturacionIf.getFechaInicio());
		java.sql.Date fechaFin = Utilitarios.fromTimestampToSqlDate(planMedioFacturacionIf.getFechaFin());
		int diaInicio = 0;
		int diaFin = 0;
		
		
		if(diaInicio>=1 && diaInicio<=20 && diaFin<=20 && diaInicio<=diaFin){
			
		}else if(diaInicio>=20 && diaInicio<=31 && diaFin<=31 && diaInicio<=diaFin){
			
		}else{
			
		}
		Utilitarios.compararFechas(fechaInicio,fechaFin);
		// if();
	}
	
	private void buscarPlanMedios() throws GenericBusinessException {
		Long clienteId = 0L, clienteOficinaId = 0L;
		
		if (clienteIf != null)
			clienteId = clienteIf.getId();
		
		if (clienteOficinaIf != null)
			clienteOficinaId = clienteOficinaIf.getId();
		
		Map mapa = new HashMap();		
		
		//se busca los Planes de Medio x cliente id		
		//ESTADO APROBADO Y PEDIDO
		int tamanoLista = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByQueryAndByIdClienteSize(mapa, clienteOficinaId, Parametros.getIdEmpresa(), "A","D");
		
		if (tamanoLista > 0) {
			PlanMedioCriteria planMedioCriteria = new PlanMedioCriteria(clienteOficinaId,"A","D"); 
			planMedioCriteria.setResultListSize(tamanoLista);
			planMedioCriteria.setQueryBuilded(mapa);
			
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.add(70);
			anchoColumnas.add(70);	
			anchoColumnas.add(270);
			anchoColumnas.add(400);
			
			JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),planMedioCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
			
			if (popupFinder.getElementoSeleccionado() != null) {
				planMedioIf = (PlanMedioIf) popupFinder.getElementoSeleccionado();
				if (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE) {
					if (planMedioIf != null) {
						getTxtEscojaReferencia().setText(planMedioIf.toString());
						limpiarVectores();
						manejarPlanMedioIf();
					}
				}
			}
		} else {
			SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.INFORMATION);
		}
	}

	private void manejarPlanMedioIf() throws GenericBusinessException {
		
		if (pedidoDetalleColeccion.size() != 0) {
			Iterator it = pedidoDetalleColeccion.iterator();
			while (it.hasNext()) {
				PedidoDetalleIf detalleRemovido = (PedidoDetalleIf) it.next();
				if (detalleRemovido.getId() != null)
					pedidoDetalleEliminadas.add(detalleRemovido);
			}
			cleanTabla();
			pedidoDetalleColeccion.clear();
		}

		getTxtObservacion().setText(planMedioIf.getConcepto());
		
		// sap
		if(planMedioIf.getAutorizacionSap() != null && !planMedioIf.getAutorizacionSap().equals("")){
			getTxtAutorizacionSAP().setText(planMedioIf.getAutorizacionSap());
		}
		
		// Selecciono el vendedor segun el ejecutivo que hizo la
		// orden de trabajo de donde salio el presupuesto
		OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(planMedioIf.getOrdenTrabajoDetalleId());
		OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
		EmpleadoIf vendedor = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenTrabajo.getEmpleadoId());
		getCmbVendedor().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbVendedor(), vendedor.getId()));
		
		// Se crea una lista que contendra a todos los
		// proveedores del Plan de Medio seleccionado
		ArrayList<String> listaProveedores = new ArrayList<String>();
		
		// Se busca la lista de ordenes de medio x planMedioId
		Collection<OrdenMedioIf> listaOrdenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planMedioIf.getId());

		Iterator ordenMedioTmpIt = listaOrdenMedioIf.iterator();
		while(ordenMedioTmpIt.hasNext()){
			OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenMedioTmpIt.next();
			
			int tmp = 0;
			
			// for que verifica si un proveedor ya se encuentra en la lista de proveedores
			// debido a que puede varias ordenes de medio para un mismo proveedor
			for (String proveedorAndProducto:listaProveedores) {
				String[] key = proveedorAndProducto.split("-");
				Long proveedorId = Long.parseLong(key[0]);
				Long productoId = Long.parseLong(key[1]);
				
				if (proveedorId.compareTo(ordenMedioIf.getProveedorId()) != 0 || (proveedorId.compareTo(ordenMedioIf.getProveedorId()) == 0 && productoId.compareTo(ordenMedioIf.getProductoProveedorId()) != 0)) {
					tmp++;
				}
			}
			if (tmp == listaProveedores.size()) {
				listaProveedores.add(String.valueOf(ordenMedioIf.getProveedorId()) + "-" + String.valueOf(ordenMedioIf.getProductoProveedorId()));
			}
		}
		
		// setear valores
		fechaInicioPlanMedioPedido = planMedioIf.getFechaInicio();
		fechaFinPlanMedioPedido    = planMedioIf.getFechaFin();
								
		// mapa que contendra todos los comericales(depende de
		// ProductoCliente y Derecho de programa)
		// con sus respectivas Ordenes de Medio de un Plan de
		// Medio
		// el nombre de esta variable seria mejor
		// mapaComercialesOrdenMedio
		// mapa de Comerciales del Plan de Medio con su
		// respectivas ordenes de medio
		// Map<ComercialIf,ArrayList<OrdenMedioIf>>
		// mapaComercialesOrdenMedioDetalle =
		// cargarMapaProductos(listaProveedores);
		
		Map<ProductoClienteIf,Map<Long,Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>>> mapaProductosClienteVersionOrdenMedioDetalleMapa = SessionServiceLocator.getPlanMedioSessionService().cargarMapaProductosClienteVersionesDetalle(planMedioIf);
		
		// lista de ID de todos los comerciales facturados
		ArrayList<Long> listaComercialesFacturados = new ArrayList<Long>(); 						
		// comercialesFacturadosColl es una collection de
		// PlanMedioFacturados de un Plan de Medio
		Map aMap = new HashMap();
		aMap.put("planMedioId", planMedioIf.getId());
		String[] estados = {"P", "F"}; // PENDIENTE, FACTURADO
		Collection comercialesFacturadosColl = SessionServiceLocator.getPlanMedioFacturacionSessionService().findPlanMedioFacturacionByQueryAndByEstados(aMap, estados);
		Iterator comercialesFacturadosCollIt = comercialesFacturadosColl.iterator();
		// cuando no hay comerciales facturados no se realiza y
		// si si los
		// hay llena la lista de comerciales facturados
		while(comercialesFacturadosCollIt.hasNext()){
			PlanMedioFacturacionIf planMedioFacturacionIf = (PlanMedioFacturacionIf)comercialesFacturadosCollIt.next();
			listaComercialesFacturados.add(planMedioFacturacionIf.getComercialId());
			// Ver de que fecha a que fecha esta facturado el
			// comercial
		}
		
		Map<ProductoClienteIf,ArrayList<Long>> mapaProductosClienteListVersion = new LinkedHashMap<ProductoClienteIf, ArrayList<Long>>();
		ArrayList<Long> listProductosClienteVersion;
		
		ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacionCanje   = new ArrayList<PlanMedioFacturacionIf>(); 
		ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacionNoCanje = new ArrayList<PlanMedioFacturacionIf>(); 
		
		Iterator mapaProductosClienteVersionesOrdenMedioDetalleMapaIt1 = mapaProductosClienteVersionOrdenMedioDetalleMapa.keySet().iterator();
		
		// Se obtiene los planes de medio de forma de pago segun
		// el planMedioId
		Collection<PlanMedioFormaPagoIf> planMedioFormaPagoColl = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByQueryAndByEstados(aMap, estados);
		
		listaOrdenMedioTotal = new ArrayList<OrdenMedioIf>();
														
		while(mapaProductosClienteVersionesOrdenMedioDetalleMapaIt1.hasNext()){ 
			ProductoClienteIf productoClienteIf = (ProductoClienteIf)mapaProductosClienteVersionesOrdenMedioDetalleMapaIt1.next();
			listProductosClienteVersion = new ArrayList<Long>();
			
			Map mapaVersionesOrdenMedioDetalleMapa = mapaProductosClienteVersionOrdenMedioDetalleMapa.get(productoClienteIf);
			Iterator mapaVersionesOrdenMedioDetalleMapaIt = mapaVersionesOrdenMedioDetalleMapa.keySet().iterator();
			while(mapaVersionesOrdenMedioDetalleMapaIt.hasNext()){
				Long productoClienteVersionId = (Long) mapaVersionesOrdenMedioDetalleMapaIt.next();
				
				// Map mapaOrdenesMedioTotal =
				// mapaComercialesOrdenMedioDetalleMapa.get(comercialIf);
				// mapa de Ordenes Medio con Mapa de Ordenes de
				// Medio Detalle con Ordenes Medio Detalle Mapa
				// Map mapaOrdenesMedioTotal =
				// mapaProductosClienteOrdenMedioDetalleMapa.get(productoClienteIf);
				// COMENTED 7 SEPTIEMBRE
				// ADD 7 SEPTIEMBRE
				Map mapaOrdenesMedioTotal = (Map) mapaVersionesOrdenMedioDetalleMapa.get(productoClienteVersionId);
				
				Iterator ordenMedioIt = mapaOrdenesMedioTotal.keySet().iterator();
				// START WHILE DE ORDENES DE MEDIO
				while(ordenMedioIt.hasNext()){
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenMedioIt.next();
					// mapa de Orden Medio Detalle con Ordenes
					// Medio Detalle Mapa
					Map mapaOrdenesMedioDetalleTotal = (Map)mapaOrdenesMedioTotal.get(ordenMedioIf);
					Iterator ordenMedioDetalleIt = mapaOrdenesMedioDetalleTotal.keySet().iterator();
					// START WHILE DE ORDENES DE MEDIO DETALLE
					while(ordenMedioDetalleIt.hasNext()){
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
						// ArrayList de las Ordenes de Medio
						// Detalle Mapa del Comercial
						ArrayList<OrdenMedioDetalleMapaIf> listOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>) mapaOrdenesMedioDetalleTotal.get(ordenMedioDetalleIf);
						// START FOR ORDEN MEDIO DETALLE DATA
						for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf: listOrdenMedioDetalleMapa){
							
							if(ordenMedioIf.getPorcentajeCanje().compareTo(new BigDecimal(100))==0){
								BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje(); 
								PlanMedioFacturacionIf planMedioFacturacionIfC = crearPlanMedioFacturacionIf(fechaInicioPlanMedioPedido,fechaFinPlanMedioPedido,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,productoClienteVersionId,true,porcentajeCanje);
								listPlanMedioFacturacionCanje.add(planMedioFacturacionIfC);
							
							}else if(ordenMedioIf.getPorcentajeCanje().compareTo(new BigDecimal(0))==0 && ordenMedioIf.getComisionPura().equals("N")){
								BigDecimal porcentaje = new BigDecimal(100); 
								PlanMedioFacturacionIf planMedioFacturacionIfNC = crearPlanMedioFacturacionIf(fechaInicioPlanMedioPedido,fechaFinPlanMedioPedido,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,productoClienteVersionId,false,porcentaje);
								listPlanMedioFacturacionNoCanje.add(planMedioFacturacionIfNC);
							
							}else{
								BigDecimal porcentajeCien = new BigDecimal(100); 
								BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje(); 
								BigDecimal porcentaje = porcentajeCien.subtract(porcentajeCanje); 
								PlanMedioFacturacionIf planMedioFacturacionIfNC = crearPlanMedioFacturacionIf(fechaInicioPlanMedioPedido,fechaFinPlanMedioPedido,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,productoClienteVersionId,false,porcentaje);
								PlanMedioFacturacionIf planMedioFacturacionIfC = crearPlanMedioFacturacionIf(fechaInicioPlanMedioPedido,fechaFinPlanMedioPedido,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,productoClienteVersionId,true,porcentajeCanje);
								listPlanMedioFacturacionNoCanje.add(planMedioFacturacionIfNC);
								listPlanMedioFacturacionCanje.add(planMedioFacturacionIfC);
							}
						}// END FOR listOrdenMedio
					}// END WHILE DE ORDENES DE MEDIO DETALLE
					listaOrdenMedioTotal.add(ordenMedioIf);
				}// END WHILE DE ORDENES DE MEDIO
				
				// ADD 7 SEPTIEMBRE
				listProductosClienteVersion.add(productoClienteVersionId);
			}// END WHILE DE VERSIONES DEL PRODUCTO CLIENTE
			
			mapaProductosClienteListVersion.put(productoClienteIf,listProductosClienteVersion);
			
		}// END WHILE DE PRODUCTOS
		
		// tamaño de las listas totales de planes de medio
		// facturadas con o sin canje
		int tamListPlanMedioFacturacionNoCanje = listPlanMedioFacturacionNoCanje.size();
		int tamListPlanMedioFacturacionCanje   = listPlanMedioFacturacionCanje.size();
		boolean op1 = false;
		
		// Se Obtiene las listas de Planes de Medios Facturados
		// con Canje o sin Canje no almacenados en la DB
		// Compara cada uno de los PlanMedioFacturacion Total generados 
		// por los Comerciales y Ordenes de Medio del Plan de Medio a ser
		// Pedido o Facturado con los PlanMedioFacturacion Guardados en la DB
		listPlanMedioFacturacionNoCanje = getPlanMedioFacturacionNoGuardado(listPlanMedioFacturacionNoCanje);
		listPlanMedioFacturacionCanje   = getPlanMedioFacturacionNoGuardado(listPlanMedioFacturacionCanje);
		
		if(tamListPlanMedioFacturacionCanje==0 && tamListPlanMedioFacturacionNoCanje==listPlanMedioFacturacionNoCanje.size()){
			op1 = true;
		}
		
		Map<ProductoClienteIf,Map<Long,ArrayList<PlanMedioFacturacionIf>>> listPlanMedioFacturacionCanjeMap = new LinkedHashMap<ProductoClienteIf,Map<Long,ArrayList<PlanMedioFacturacionIf>>>();
		
		for(ProductoClienteIf productoClienteIf : mapaProductosClienteListVersion.keySet() ){
			ArrayList<Long> versionesList = mapaProductosClienteListVersion.get(productoClienteIf);
			Map<Long,ArrayList<PlanMedioFacturacionIf>> mapaVersion = new LinkedHashMap<Long, ArrayList<PlanMedioFacturacionIf>>();
			for(Long versionId : versionesList){
				ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacion = new ArrayList<PlanMedioFacturacionIf>(); 
				for(PlanMedioFacturacionIf planMedioFacturacionIf : listPlanMedioFacturacionCanje){
					if(planMedioFacturacionIf.getProductoClienteId().compareTo(productoClienteIf.getId()) == 0 && 
					   planMedioFacturacionIf.getCampanaProductoVersionId().compareTo(versionId) == 0){
						listPlanMedioFacturacion.add(planMedioFacturacionIf);
					}
				}
				mapaVersion.put(versionId, listPlanMedioFacturacion);
			}
			listPlanMedioFacturacionCanjeMap.put(productoClienteIf, mapaVersion);
		}
		
		Map<ClienteOficinaIf,ArrayList<PlanMedioFacturacionIf>> listPlanMedioFacturacionCanjeByProveedorMap = new LinkedHashMap<ClienteOficinaIf, ArrayList<PlanMedioFacturacionIf>>();
		for (String proveedorAndProducto: listaProveedores) {
			String[] key = proveedorAndProducto.split("-");
			Long proveedorId = Long.parseLong(key[0]);
			ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacion = new ArrayList<PlanMedioFacturacionIf>();
			for (PlanMedioFacturacionIf planMedioFacturacionIf : listPlanMedioFacturacionCanje ) {
				if (planMedioFacturacionIf.getProveedorId().compareTo(proveedorId) == 0) {
					listPlanMedioFacturacion.add(planMedioFacturacionIf);
				}
			}
			if (listPlanMedioFacturacion.size()>0) {
				ClienteOficinaIf proveedor = mapaClienteOficina.get(proveedorId);
				listPlanMedioFacturacionCanjeByProveedorMap.put(proveedor, listPlanMedioFacturacion);
			}
		}
		
		// mapa (clienteOficinaIf , listaPMFacturacion sin
		// canje) no Guardados en la DB
		Map<ClienteOficinaIf,ArrayList<PlanMedioFacturacionIf>> listPlanMedioFacturacionNoCanjeByProveedorMap = new LinkedHashMap<ClienteOficinaIf, ArrayList<PlanMedioFacturacionIf>>();
		for(String proveedorAndProducto: listaProveedores){
			String[] key = proveedorAndProducto.split("-");
			Long proveedorId = Long.parseLong(key[0]);
			ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacion = new ArrayList<PlanMedioFacturacionIf>();
			for(PlanMedioFacturacionIf planMedioFacturacionIf : listPlanMedioFacturacionNoCanje ){
				if(planMedioFacturacionIf.getProveedorId().compareTo(proveedorId)==0){
					listPlanMedioFacturacion.add(planMedioFacturacionIf);
				}
			}
			if(listPlanMedioFacturacion.size()>0){
				ClienteOficinaIf proveedor = mapaClienteOficina.get(proveedorId);
				listPlanMedioFacturacionNoCanjeByProveedorMap.put(proveedor, listPlanMedioFacturacion);
			}
		}						
		
		Map<ProductoClienteIf,Map<Long,ArrayList<PlanMedioFacturacionIf>>> listPlanMedioFacturacionNoCanjeMap = new LinkedHashMap<ProductoClienteIf,Map<Long,ArrayList<PlanMedioFacturacionIf>>>();
		
		for(ProductoClienteIf productoClienteIf : mapaProductosClienteListVersion.keySet() ){
			ArrayList<Long> versionesList = mapaProductosClienteListVersion.get(productoClienteIf);
			Map<Long,ArrayList<PlanMedioFacturacionIf>> mapaVersion = new LinkedHashMap<Long, ArrayList<PlanMedioFacturacionIf>>();
			for(Long versionId : versionesList){							
				ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacion = new ArrayList<PlanMedioFacturacionIf>(); 
				for(PlanMedioFacturacionIf planMedioFacturacionIf : listPlanMedioFacturacionNoCanje ){
					if(planMedioFacturacionIf.getProductoClienteId().compareTo(productoClienteIf.getId()) == 0 && 
					   planMedioFacturacionIf.getCampanaProductoVersionId().compareTo(versionId) == 0){											
						listPlanMedioFacturacion.add(planMedioFacturacionIf);
					}
				}
				mapaVersion.put(versionId, listPlanMedioFacturacion);
			}
			listPlanMedioFacturacionNoCanjeMap.put(productoClienteIf, mapaVersion);
		}
														
		// lista de Comerciales No Canje que no han sido
		// facturados
		Map<ProductoClienteIf,ArrayList<Long>> mapaProductosClienteVersionNoCanje = new LinkedHashMap<ProductoClienteIf, ArrayList<Long>>();
		ArrayList<Long>listProductosClienteVersionNoCanje = new ArrayList<Long>();
		
		Iterator listPlanMedioFacturacionNoCanjeMapIt = listPlanMedioFacturacionNoCanjeMap.keySet().iterator();
								
		while(listPlanMedioFacturacionNoCanjeMapIt.hasNext()){
			ProductoClienteIf productoClienteIf = (ProductoClienteIf)listPlanMedioFacturacionNoCanjeMapIt.next();
			listProductosClienteVersionNoCanje = new ArrayList<Long>();
			Map mapVersion = listPlanMedioFacturacionNoCanjeMap.get(productoClienteIf);
			Iterator mapVersionIt = mapVersion.keySet().iterator();
			while(mapVersionIt.hasNext()){
				Long versionId = (Long) mapVersionIt.next();
			
				ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacion = (ArrayList<PlanMedioFacturacionIf>) mapVersion.get(versionId);
				// Si la lista de PlanMedioFacturadosNoCanje no
				// almacenados de un Comercial es mayor a 0
				// entonces al Comercial se lo agrega como
				// Comercial NO CANJE sin tar facturados en la
				// DB
				if(listPlanMedioFacturacion.size() > 0){
					listProductosClienteVersionNoCanje.add(versionId);
				}	
			}
			mapaProductosClienteVersionNoCanje.put(productoClienteIf, listProductosClienteVersionNoCanje);								
		}						

		if(listPlanMedioFacturacionNoCanje.size()==0 && listPlanMedioFacturacionCanje.size()==0){
			SpiritAlert.createAlert("Todos los registros del Plan de Medio ya fueron Pedidos, por favor genere las facturas de este Plan de Medio", SpiritAlert.INFORMATION);
		}
		
		else{
			boolean op2 = true; // version
			boolean op3 = true; // canje
			boolean op4 = true; // proveedor de medio
			boolean op5 = true; // producto comercial
			
			if(listPlanMedioFacturacionNoCanje.size()==0){
				op2 = false;
				op4 = false;
				op5 = false;
			}
			if(listPlanMedioFacturacionCanje.size()==0){
				op3 = false;
			}
										
			// para saber si ya se facturo al cliente por medio,
			// por version o por producto, si es asi ya no puede
			// haber factura tipo completo.
			// tambien me sirve para ver que ordenes del plan ya
			// facture asi no vuelven a aparecer en la
			// facturación parcial
			boolean facturaAcliente = false;
			Map<Long, Long> ordenesFacturadas = new HashMap<Long, Long>();
					
			Collection planesMedioFacturacion = SessionServiceLocator.getPlanMedioFacturacionSessionService().findPlanMedioFacturacionByQueryAndByEstados(aMap, estados);
			Iterator planesMedioFacturacionIt = planesMedioFacturacion.iterator();
			while(planesMedioFacturacionIt.hasNext()){
				PlanMedioFacturacionIf planMedioFacturacion = (PlanMedioFacturacionIf)planesMedioFacturacionIt.next();
				if(planMedioFacturacion.getCanje().equals("N")){
					facturaAcliente = true;
				}
				ordenesFacturadas.put(planMedioFacturacion.getOrdenMedioId(), planMedioFacturacion.getOrdenMedioId());
			}
			
			// Se llama a la sub-pantalla donde se dan las
			// opciones para facturar un plan de medios
			boolean reembolso = false;
			if (getCmbTipoDocumento().getSelectedItem() != null)
				reembolso = ((TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem()).getReembolso().equals("S");
			
			FacturacionPlanMedioModel jdFacturacionPlanMedio = new FacturacionPlanMedioModel(Parametros.getMainFrame(), planMedioIf.getId(), 
					mapaProductosClienteVersionNoCanje, listPlanMedioFacturacionCanjeByProveedorMap, listPlanMedioFacturacionNoCanjeByProveedorMap, 
					planMedioFormaPagoColl, fechaInicioPlanMedioPedido, fechaFinPlanMedioPedido, 
					op1,op2,op3,op4,op5, facturaAcliente, reembolso, ordenesFacturadas, mapaClienteOficina, mapaGenerico, mapaProductoProveedor);
										
			Long idProductoCliente = new Long(0);
			Long campanaProductoVersionId;
		
			int posicionEscogida = -1;
			Long idProveedorEscogido = new Long(0); 
			PlanMedioFacturacionIf planMedioFacturacionIfEscogido;
			
			if(jdFacturacionPlanMedio.getRbCompleto().isSelected()){
				formaFacturacion = 1;
				// Llena la listas de PlanMedioFacturacion Total
				// con los Comerciales con sus respectivas
				// Ordenes de Medio
				// de un Plan de Medio Total a ser Pedido o
				// Facturado
				
				//listaPlanMedioFacturacionEscogido = getPlanMedioFacturacionTotal(planMedioIf, true);
				listaPlanMedioFacturacionEscogido = SessionServiceLocator.getPlanMedioSessionService().getPlanMedioFacturacionTotal(planMedioIf, true);
												
				// setea fecha inicio, fecha fin, el tipo de
				// Forma de Pago y la forma de Pago ID para el
				// PlanMedioFormaPago
				fechaInicioPlanMedioFormaPago = fechaInicioPlanMedioPedido;
				fechaFinPlanMedioFormaPago    = fechaFinPlanMedioPedido;
				tipoFormaPago = TIPO_FORMA_PAGO_COMPLETO;
				// Cuando es completo es el planMedioId
				formaPagoId   = planMedioIf.getId(); 
				cargarDetallesPlanMedioCompleto(listaOrdenMedioIf, listaProveedores);						
			}
			
			else if(jdFacturacionPlanMedio.getRbParcial().isSelected()){
				// si existen detalles seleccionados para la facturacion parcial
				if(jdFacturacionPlanMedio.getPresupuestoDetallesSeleccionados().size() > 0){																	
					tipoFormaPago = TIPO_FORMA_PAGO_PARCIAL;
					formaPagoId   = planMedioIf.getId();
					cargarOrdenesMedioParaFacturacionParcial(jdFacturacionPlanMedio.getPresupuestoDetallesSeleccionados());
				}
			}
			
			else if (jdFacturacionPlanMedio.getRbPorProveedor().isSelected()) {
				formaFacturacion = 4;
				int cont = 0;
				ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacionNoCanjeTmp = new ArrayList<PlanMedioFacturacionIf>();
				// posicion del item seleccionado
				posicionEscogida = jdFacturacionPlanMedio.getPositionEscogida();
				Iterator planMedioFacturacionNoCanjeByProveedorMapIt = listPlanMedioFacturacionNoCanjeByProveedorMap.keySet().iterator();
				
				// AQUII OBTENER DE LA LISTA SOLO LOS A FACTURAR
				// POR EL PERIODO ESTABLECIDO SI TA ACTIVO CHECK
				// DEL PERIODO
				
				ClienteOficinaData clienteTmp = new ClienteOficinaData();
				// proveedor a quien creacional le va a cobrar
				// la factura por el canje que un cliente de
				// creacional le dio
				// directamente al canal o cliente proveedor
				ClienteOficinaIf proveedor = clienteTmp;
				// Se obtien la lista de planes de medio de
				// facturacion con canje del proveedor
				// seleccionado
				while(planMedioFacturacionNoCanjeByProveedorMapIt.hasNext()){
					ClienteOficinaIf clienteOficinaIf2 = (ClienteOficinaIf)planMedioFacturacionNoCanjeByProveedorMapIt.next();
					if(posicionEscogida == cont){
						listPlanMedioFacturacionNoCanjeTmp = listPlanMedioFacturacionNoCanjeByProveedorMap.get(clienteOficinaIf2);
						proveedor = clienteOficinaIf2;
					}
					cont++;
				}
										
				// setea el tipo de Forma de Pago y la forma de Pago ID
				tipoFormaPago = TIPO_FORMA_PAGO_CANAL;
				// Cuando es por Comision del Medio es el clieneteoOficinaId
				formaPagoId   = proveedor.getId(); 
											
				if (jdFacturacionPlanMedio.isCheckPeriodoSelected()){
					// setea fecha inicio, fecha fin para el
					// PlanMedioFormaPago
					fechaInicioPlanMedioFormaPago = jdFacturacionPlanMedio.getPeriodoFechaInicio();
					fechaFinPlanMedioFormaPago    = jdFacturacionPlanMedio.getPeriodoFechaFin();
					cargarDetallesPlanMedioByProveedorNoCanjeByProveedor(proveedor, listPlanMedioFacturacionNoCanjeTmp, mapaProductosClienteListVersion,jdFacturacionPlanMedio.getPeriodoFechaInicio(),jdFacturacionPlanMedio.getPeriodoFechaFin());
				}else{
					// setea fecha inicio, fecha fin para el
					// PlanMedioFormaPago
					fechaInicioPlanMedioFormaPago = fechaInicioPlanMedioPedido;
					fechaFinPlanMedioFormaPago    = fechaFinPlanMedioPedido;
					cargarDetallesPlanMedioByProveedorNoCanjeByProveedor(proveedor, listPlanMedioFacturacionNoCanjeTmp, mapaProductosClienteListVersion,fechaInicioPlanMedioPedido,fechaFinPlanMedioPedido);
				}
			}							
			
			else if (jdFacturacionPlanMedio.getRbPorProductoComercial().isSelected()) {
				formaFacturacion = 5;
				idProductoCliente = jdFacturacionPlanMedio.getIdProductoElegido();
				ProductoClienteData proTmp = new ProductoClienteData();
				ProductoClienteIf pro = proTmp;
				
				ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacionNoCanjeTmp = new ArrayList<PlanMedioFacturacionIf>();
				Iterator listPlanMedioFacturacionNoCanjeMapIt2 = listPlanMedioFacturacionNoCanjeMap.keySet().iterator();
				
				// AQUII OBTENER DE LA LISTA SOLO LOS A FACTURAR
				// POR EL PERIODO ESTABLECIDO SI TA ACTIVO CHECK
				// DEL PERIODO
				while(listPlanMedioFacturacionNoCanjeMapIt2.hasNext()){
					ProductoClienteIf productoClienteIf = (ProductoClienteIf)listPlanMedioFacturacionNoCanjeMapIt2.next();
					
					Map mapVersiones = listPlanMedioFacturacionNoCanjeMap.get(productoClienteIf);
					Iterator mapVersionesIt = mapVersiones.keySet().iterator();
					while(mapVersionesIt.hasNext()){
						Long versionId = (Long) mapVersionesIt.next();
						
						if(productoClienteIf.getId().compareTo(idProductoCliente) == 0){
							pro = productoClienteIf;
							ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacionNoCanjeTmp2 = (ArrayList<PlanMedioFacturacionIf>) mapVersiones.get(versionId);
							listPlanMedioFacturacionNoCanjeTmp.addAll(listPlanMedioFacturacionNoCanjeTmp2);
						}										
					}									
				}
				// setea el tipo de Forma de Pago y la forma de
				// Pago ID
				tipoFormaPago = TIPO_FORMA_PAGO_POR_PRODUCTO;
				formaPagoId = pro.getId();
				
				// ESTA LISTA DE ESCOGIDO NO VA HACER LA MISMA
				// PORQUE SE VA HACER POR PERIODO
				if (jdFacturacionPlanMedio.isCheckPeriodoSelected()){
					// setea fecha inicio, fecha fin para el
					// PlanMedioFormaPago
					fechaInicioPlanMedioFormaPago = jdFacturacionPlanMedio.getPeriodoFechaInicio();
					fechaFinPlanMedioFormaPago    = jdFacturacionPlanMedio.getPeriodoFechaFin();
					cargarDetallesPlanMedioByProducto(pro,listPlanMedioFacturacionNoCanjeTmp, listaProveedores,jdFacturacionPlanMedio.getPeriodoFechaInicio(),jdFacturacionPlanMedio.getPeriodoFechaFin());
				}else{
					fechaInicioPlanMedioFormaPago = fechaInicioPlanMedioPedido;
					fechaFinPlanMedioFormaPago    = fechaFinPlanMedioPedido;
					cargarDetallesPlanMedioByProducto(pro,listPlanMedioFacturacionNoCanjeTmp, listaProveedores,fechaInicioPlanMedioPedido,fechaFinPlanMedioPedido);
				}
			}	
				
			// x PRODUCTO CLIENTE VERSION
			else if(jdFacturacionPlanMedio.getRbPorVersion().isSelected()){ 
				formaFacturacion = 2;
				idProductoCliente = jdFacturacionPlanMedio.getIdProductoElegido();
				campanaProductoVersionId = jdFacturacionPlanMedio.getVersionProductoElegidoId();								
				ProductoClienteData proTmp = new ProductoClienteData();
				ProductoClienteIf pro = proTmp;
				CampanaProductoVersionIf versionTmp = null;
				
				ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacionNoCanjeTmp = new ArrayList<PlanMedioFacturacionIf>();
				Iterator listPlanMedioFacturacionNoCanjeMapIt2 = listPlanMedioFacturacionNoCanjeMap.keySet().iterator();
				/*
				 * AQUII OBTENER DE LA LISTA SOLO LOS A FACTURAR
				 * POR EL PERIODO ESTABLECIDO SI TA ACTIVO CHECK
				 * DEL PERIODO
				 */
				while(listPlanMedioFacturacionNoCanjeMapIt2.hasNext()){
					ProductoClienteIf productoClienteIf = (ProductoClienteIf)listPlanMedioFacturacionNoCanjeMapIt2.next();
					
					Map mapVersiones = listPlanMedioFacturacionNoCanjeMap.get(productoClienteIf);
					Iterator mapVersionesIt = mapVersiones.keySet().iterator();
					while(mapVersionesIt.hasNext()){
						Long versionId = (Long) mapVersionesIt.next();
						
						if(productoClienteIf.getId().compareTo(idProductoCliente) == 0 &&
						   versionId.compareTo(campanaProductoVersionId) == 0 ){
							pro = productoClienteIf;
							versionTmp = mapaCampanaProductoVersion.get(versionId);
							listPlanMedioFacturacionNoCanjeTmp = (ArrayList<PlanMedioFacturacionIf>) mapVersiones.get(versionId);
						}
						
					}									
				}
				
				// setea el tipo de Forma de Pago y la forma de Pago ID
				tipoFormaPago = TIPO_FORMA_PAGO_POR_PRODUCTO_VERSION;
				// Cuando es por version se setea el id de campaña producto versión.
				formaPagoId   = versionTmp.getId(); 
				// Cuando es por producto Cliente y Version
				formaPagoVersion = versionTmp; 
				
				// ESTA LISTA DE ESCOGIDO NO VA HACER LA MISMA
				// PORQUE SE VA HACER POR PERIODO
				if (jdFacturacionPlanMedio.isCheckPeriodoSelected()){
					// setea fecha inicio, fecha fin para el
					// PlanMedioFormaPago
					fechaInicioPlanMedioFormaPago = jdFacturacionPlanMedio.getPeriodoFechaInicio();
					fechaFinPlanMedioFormaPago    = jdFacturacionPlanMedio.getPeriodoFechaFin();
					cargarDetallesPlanMedioByVersion(pro,versionTmp, listPlanMedioFacturacionNoCanjeTmp, listaProveedores,jdFacturacionPlanMedio.getPeriodoFechaInicio(),jdFacturacionPlanMedio.getPeriodoFechaFin());
				}else{
					fechaInicioPlanMedioFormaPago = fechaInicioPlanMedioPedido;
					fechaFinPlanMedioFormaPago    = fechaFinPlanMedioPedido;
					cargarDetallesPlanMedioByVersion(pro,versionTmp,listPlanMedioFacturacionNoCanjeTmp, listaProveedores,fechaInicioPlanMedioPedido,fechaFinPlanMedioPedido);
				}
			}
			
			else if (jdFacturacionPlanMedio.getRbPorComisionMedio().isSelected()) {             
				formaFacturacion = 3;
				int cont = 0;
				ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacionCanjeTmp = new ArrayList<PlanMedioFacturacionIf>();
				// posicion del item seleccionado
				posicionEscogida = jdFacturacionPlanMedio.getPositionEscogida();
				Iterator planMedioFacturacionCanjeByProveedorMapIt = listPlanMedioFacturacionCanjeByProveedorMap.keySet().iterator();
				/*
				 * AQUII OBTENER DE LA LISTA SOLO LOS A FACTURAR
				 * POR EL PERIODO ESTABLECIDO SI TA ACTIVO CHECK
				 * DEL PERIODO
				 */
				ClienteOficinaData clienteTmp = new ClienteOficinaData();
				// proveedor a quien creacional le va a cobrar
				// la factura por el canje que un cliente de
				// creacional le dio
				// directamente al canal o cliente proveedor
				ClienteOficinaIf proveedor = clienteTmp;
				// Se obtien la lista de planes de medio de
				// facturacion con canje del proveedor
				// seleccionado
				while(planMedioFacturacionCanjeByProveedorMapIt.hasNext()){
					ClienteOficinaIf clienteOficinaIf2 = (ClienteOficinaIf)planMedioFacturacionCanjeByProveedorMapIt.next();
					if(posicionEscogida == cont){
						listPlanMedioFacturacionCanjeTmp = listPlanMedioFacturacionCanjeByProveedorMap.get(clienteOficinaIf2);
						proveedor = clienteOficinaIf2;
					}
					cont++;
				}
												
				// setea el tipo de Forma de Pago y la forma de Pago ID
				tipoFormaPago = TIPO_FORMA_PAGO_COMISION_MEDIO;
				
				// Cuando es por Comision del Medio es el clieneteoOficinaId
				formaPagoId   = proveedor.getId(); 
				
				if (jdFacturacionPlanMedio.isCheckPeriodoSelected()){
					// setea fecha inicio, fecha fin para el
					// PlanMedioFormaPago
					fechaInicioPlanMedioFormaPago = jdFacturacionPlanMedio.getPeriodoFechaInicio();
					fechaFinPlanMedioFormaPago    = jdFacturacionPlanMedio.getPeriodoFechaFin();
					cargarDetallesPlanMedioByProveedorCanjeByProducto(proveedor, listPlanMedioFacturacionCanjeTmp, mapaProductosClienteListVersion,jdFacturacionPlanMedio.getPeriodoFechaInicio(),jdFacturacionPlanMedio.getPeriodoFechaFin());
				}else{
					// setea fecha inicio, fecha fin para el
					// PlanMedioFormaPago
					fechaInicioPlanMedioFormaPago = fechaInicioPlanMedioPedido;
					fechaFinPlanMedioFormaPago    = fechaFinPlanMedioPedido;
					cargarDetallesPlanMedioByProveedorCanjeByProducto(proveedor, listPlanMedioFacturacionCanjeTmp, mapaProductosClienteListVersion,fechaInicioPlanMedioPedido,fechaFinPlanMedioPedido);
				}				
			}
		}
	}
		
	
	public void imprimirPlanMedioFacturacion(PlanMedioFacturacionIf pmf){
		try{
			ComercialIf comercialIf = SessionServiceLocator.getComercialSessionService().getComercial(pmf.getComercialId());
			Long derechoId = comercialIf.getDerechoprogramaId();
			Collection derechoProgramaColl = SessionServiceLocator.getDerechoProgramaSessionService().findDerechoProgramaById(derechoId);
			Iterator derechoProgramaCollIt = derechoProgramaColl.iterator(); 
			DerechoProgramaIf derechoProgramaIf = (DerechoProgramaIf)derechoProgramaCollIt.next();
			String item = comercialIf.getNombre()+" ("+derechoProgramaIf.getNombre()+")";// -"+comercialIt.getVersion()+"-";
			System.out.println(pmf.getEstado()+"-"+comercialIf.getNombre()+" ("+derechoProgramaIf.getNombre()+")-"+comercialIf.getVersion()+"-"+pmf.getProveedorId()+"-"+pmf.getCanje()+"-"+pmf.getPorcentajeCanje());
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/*
	 * Llena la listas de PlanMedioFacturacion Total que deberian ser generadas
	 * por un Plan Medio con los campos de Comercial, Ordenes de Medio Orden
	 * Medio Detalle, Orden Medio DetalleMapa
	 */
	/*private ArrayList<PlanMedioFacturacionIf> getPlanMedioFacturacionTotal(PlanMedioIf planMedioFacturado, boolean esFacturacionCompleta){
		// lista de Planes de MedioFacturacion
		ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacion = new ArrayList<PlanMedioFacturacionIf>(); 
		try{
			ArrayList<ClienteOficinaIf> listaProveedores = new ArrayList<ClienteOficinaIf>();
			// Lista de todas las Ordenes de Medio del Plan de Medio a ser
			// Pedido o Facturado
			Collection listaOrdenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planMedioFacturado.getId());
	
			Iterator ordenMedioTmpIt = listaOrdenMedioIf.iterator();
			// Se recorre la listaOrdenMedio para llenar la lista de Proveedores
			while(ordenMedioTmpIt.hasNext()){
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenMedioTmpIt.next();
				// se obtiene al cliente proveedor de la orden de Medio
				ClienteOficinaIf proveedor = mapaClienteOficina.get(ordenMedioIf.getProveedorId());
				int tmp = 0;	
				// for que llena la lista de proveedores
				for(ClienteOficinaIf clienteOficinaIf2:listaProveedores){
					if(clienteOficinaIf2.getId().compareTo(proveedor.getId())!=0){
						tmp++;
					}
				}
				// agrega a los proveedores de las ordenes de medio que no se
				// encuentran en la lista de proveedores
				if(tmp==listaProveedores.size()){
					listaProveedores.add(proveedor);
				}
			}
			
			Map<ProductoClienteIf,Map<Long,Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>>> mapaProductosClienteVersionOrdenMedioDetalleMapa = cargarMapaProductosClienteVersionesDetalle();
			Iterator mapaProductosClienteVersionOrdenMedioDetalleMapaIt1 = mapaProductosClienteVersionOrdenMedioDetalleMapa.keySet().iterator();
			
			// Llena la listas de Planes de Medio de Facturacion con o sin canje
			// con los
			// Producto Cliente Version con sus respectivas Ordenes de Medio
			// hasta el DetalleMapa
			
			// START WHILE DE PRODUCTOS CLIENTE VERSIONES
			while(mapaProductosClienteVersionOrdenMedioDetalleMapaIt1.hasNext()){
				ProductoClienteIf productoClienteIf = (ProductoClienteIf)mapaProductosClienteVersionOrdenMedioDetalleMapaIt1.next(); // ADD
																																		// 8
																																		// SEPTIEMBRE
				
				Map mapaVersionOrdenesMedioTotal = mapaProductosClienteVersionOrdenMedioDetalleMapa.get(productoClienteIf);
				Iterator mapaVersionOrdenesMedioTotalIt = mapaVersionOrdenesMedioTotal.keySet().iterator();
				while(mapaVersionOrdenesMedioTotalIt.hasNext()){
					Long productoClienteVersionId = (Long) mapaVersionOrdenesMedioTotalIt.next();
					
					Map mapaOrdenesMedioTotal = (Map) mapaVersionOrdenesMedioTotal.get(productoClienteVersionId);
					Iterator ordenMedioIt = mapaOrdenesMedioTotal.keySet().iterator();
					
					// START WHILE DE ORDENES DE MEDIO
					while(ordenMedioIt.hasNext()){
						OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenMedioIt.next();
						// mapa de Orden Medio Detalle con Ordenes Medio Detalle
						// Mapa
						Map mapaOrdenesMedioDetalleTotal = (Map)mapaOrdenesMedioTotal.get(ordenMedioIf);
						Iterator ordenMedioDetalleIt = mapaOrdenesMedioDetalleTotal.keySet().iterator();
						// START WHILE DE ORDENES DE MEDIO DETALLE
						while(ordenMedioDetalleIt.hasNext()){
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
							// ArrayList de las Ordenes de Medio Detalle Mapa
							// del Comercial
							ArrayList<OrdenMedioDetalleMapaIf> listOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>) mapaOrdenesMedioDetalleTotal.get(ordenMedioDetalleIf);
							// START FOR ORDEN MEDIO DETALLE DATA
							for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf: listOrdenMedioDetalleMapa){
									
								if(ordenMedioIf.getPorcentajeCanje().compareTo(new BigDecimal(100))==0){
									BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje(); 
									PlanMedioFacturacionIf planMedioFacturacionIfC = crearPlanMedioFacturacionIf(fechaInicioPlanMedioPedido,fechaFinPlanMedioPedido,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,productoClienteVersionId,true,porcentajeCanje);
									listPlanMedioFacturacion.add(planMedioFacturacionIfC);
								}else if(esFacturacionCompleta || (ordenMedioIf.getPorcentajeCanje().compareTo(new BigDecimal(0))==0 && ordenMedioIf.getComisionPura().equals("N"))){
									BigDecimal porcentaje = new BigDecimal(0); 
									PlanMedioFacturacionIf planMedioFacturacionIfNC = crearPlanMedioFacturacionIf(fechaInicioPlanMedioPedido,fechaFinPlanMedioPedido,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,productoClienteVersionId,false,porcentaje);
									listPlanMedioFacturacion.add(planMedioFacturacionIfNC);
								}else{
									BigDecimal porcentajeCien = new BigDecimal(100); 
									BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje(); 
									// porcentaje que no es canjeado
									BigDecimal porcentaje = porcentajeCien.subtract(porcentajeCanje);
									PlanMedioFacturacionIf planMedioFacturacionIfNC = crearPlanMedioFacturacionIf(fechaInicioPlanMedioPedido,fechaFinPlanMedioPedido,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,productoClienteVersionId,false,porcentaje);
									PlanMedioFacturacionIf planMedioFacturacionIfC = crearPlanMedioFacturacionIf(fechaInicioPlanMedioPedido,fechaFinPlanMedioPedido,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,productoClienteVersionId,true,porcentajeCanje);
									listPlanMedioFacturacion.add(planMedioFacturacionIfNC);
									listPlanMedioFacturacion.add(planMedioFacturacionIfC);
								}
							}
						}
					}
				} 
			}
			return listPlanMedioFacturacion;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listPlanMedioFacturacion;
	}*/
	
	/*
	 * crea el Plan de Medio de Facturacion con los datos del Producto Cliente,
	 * Version del Producto, la OrdenMedio, el Comercial, la Orden MedioDetalle
	 * y OrdenMedioDetallaMapa del Plan de Medio a ser Pedido o Facturado
	 */
	private PlanMedioFacturacionData crearPlanMedioFacturacionIf(Date fechaInicio,Date fechaFin, OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf, OrdenMedioDetalleIf ordenMedioDetalleIf, OrdenMedioIf ordenMedioIf,ProductoClienteIf productoClienteIf,Long versionId,boolean canje, BigDecimal porcentaje){
		PlanMedioFacturacionData planMedioFacturacionData = new PlanMedioFacturacionData();
		planMedioFacturacionData.setOrdenMedioDetalleMapaId(ordenMedioDetalleMapaIf.getId());
		planMedioFacturacionData.setOrdenMedioDetalleId(ordenMedioDetalleIf.getId());
		planMedioFacturacionData.setOrdenMedioId(ordenMedioIf.getId());
		planMedioFacturacionData.setPlanMedioId(planMedioIf.getId());
		planMedioFacturacionData.setProductoClienteId(productoClienteIf.getId());
		planMedioFacturacionData.setCampanaProductoVersionId(versionId);
		planMedioFacturacionData.setFechaInicio(new Timestamp(fechaInicio.getTime()));
		planMedioFacturacionData.setFechaFin(new Timestamp(fechaFin.getTime()));
		
		if(canje){
			planMedioFacturacionData.setCanje("S");// S = si ; N = no
		}else{
			planMedioFacturacionData.setCanje("N");// S = si ; N = no
		}
		
		planMedioFacturacionData.setProveedorId(ordenMedioIf.getProveedorId());
		planMedioFacturacionData.setPorcentajeCanje(porcentaje);
		planMedioFacturacionData.setEstado("-");// F=facturado, P=pedido,
		
		return planMedioFacturacionData;
	}
	
	/*
	 * crea el Plan de Medio de Facturacion con los datos de la OrdenMedio, el
	 * Comercial la Orden MedioDetalle y OrdenMedioDetallaMapa del Plan de Medio
	 * a ser Pedido o Facturado
	 */
	private PlanMedioFacturacionData crearPlanMedioFacturacionIf(Date fechaInicio,Date fechaFin, OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf, OrdenMedioDetalleIf ordenMedioDetalleIf, OrdenMedioIf ordenMedioIf,ProductoClienteIf productoClienteIf,boolean canje, BigDecimal porcentaje){
	// private PlanMedioFacturacionData crearPlanMedioFacturacionIf(Date
	// fechaInicio,Date fechaFin, OrdenMedioDetalleMapaIf
	// ordenMedioDetalleMapaIf, OrdenMedioDetalleIf ordenMedioDetalleIf,
	// OrdenMedioIf ordenMedioIf,ComercialIf comercialIf,boolean canje,
	// BigDecimal porcentaje){
		PlanMedioFacturacionData planMedioFacturacionData = new PlanMedioFacturacionData();
		planMedioFacturacionData.setOrdenMedioDetalleMapaId(ordenMedioDetalleMapaIf.getId());
		planMedioFacturacionData.setOrdenMedioDetalleId(ordenMedioDetalleIf.getId());
		planMedioFacturacionData.setOrdenMedioId(ordenMedioIf.getId());
		planMedioFacturacionData.setPlanMedioId(planMedioIf.getId());
		// MODIFIED 12 MAYO
		// planMedioFacturacionData.setComercialId(comercialIf.getId());
		// ADD 12 MAYO
		planMedioFacturacionData.setProductoClienteId(productoClienteIf.getId());
		// planMedioFacturacionData.setFechaInicio(planMedioIf.getFechaInicio());
		// planMedioFacturacionData.setFechaFin(planMedioIf.getFechaFin());
		planMedioFacturacionData.setFechaInicio(new Timestamp(fechaInicio.getTime()));
		planMedioFacturacionData.setFechaFin(new Timestamp(fechaFin.getTime()));
		if(canje){
			planMedioFacturacionData.setCanje("S");// S = si ; N = no
		}else{
			planMedioFacturacionData.setCanje("N");// S = si ; N = no
		}
		planMedioFacturacionData.setProveedorId(ordenMedioIf.getProveedorId());
		planMedioFacturacionData.setPorcentajeCanje(porcentaje);
		planMedioFacturacionData.setEstado("-");// F=facturado, P=pedido,
		return planMedioFacturacionData;
	}
	
	/*
	 * crea el Plan de Medio Forma de Pago con los datos del Pedido, el Plan
	 * Medio, la Forma de Pago y el Id de la Forma de Pago
	 */
	private PlanMedioFormaPagoData crearPlanMedioFormaPagoIf(Long pedidoId,Long planMedioId, Date fechaInicio, Date fechaFin, String tipoFormaPago, Long formaPagoId, CampanaProductoVersionIf formaPagoVersion, String estado){
		PlanMedioFormaPagoData PlanMedioFormaPagoData = new PlanMedioFormaPagoData();
		PlanMedioFormaPagoData.setPedidoId(pedidoId);
		PlanMedioFormaPagoData.setPlanMedioId(planMedioId);
		PlanMedioFormaPagoData.setFechaInicio(new Timestamp(fechaInicio.getTime()));
		PlanMedioFormaPagoData.setFechaFin(new Timestamp(fechaFin.getTime()));
		PlanMedioFormaPagoData.setTipoFormaPago(tipoFormaPago);
		PlanMedioFormaPagoData.setFormaPagoId(formaPagoId);
		PlanMedioFormaPagoData.setEstado(estado);
		
		// Cuando la facturacion es por Version
		if (formaPagoVersion != null){
			PlanMedioFormaPagoData.setFormaPagoCampanaProductoVersionId(formaPagoVersion.getId());
		}
		
		return PlanMedioFormaPagoData;
	}
	
	private void buscarPresupuesto() throws GenericBusinessException {
		
		Long clienteId = 0L, clienteOficinaId = 0L;
		
		if (clienteIf != null)
			clienteId = clienteIf.getId();
		
		if (clienteOficinaIf != null)
			clienteOficinaId = clienteOficinaIf.getId();
		
		Map mapa = new HashMap();
		mapa.put("estado", "A");
		int tamanoLista = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByFilteredQuerySize(mapa, clienteId, clienteOficinaId, Parametros.getIdEmpresa());
		
		if (tamanoLista > 0) {
			PresupuestoCriteria presupuestoCriteria = new PresupuestoCriteria(clienteId, clienteOficinaId);
			presupuestoCriteria.setResultListSize(tamanoLista);
			presupuestoCriteria.setQueryBuilded(mapa);
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.add(70);
			anchoColumnas.add(70);	
			anchoColumnas.add(270);
			anchoColumnas.add(400);
			JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), presupuestoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, null);
			if ( popupFinder.getElementoSeleccionado() != null ) {
				presupuestoIf = (PresupuestoIf) popupFinder.getElementoSeleccionado();
				if (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE) {
					limpiarVectores();
					presupuestosIf.add(presupuestoIf);
					formaFacturacionPresupuesto();
				}
			}
		} else {
			SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.INFORMATION);
		}
	}
	
	private boolean existeNegociacionComision(){
		boolean existeNegociacionComision = false;
		try {
			Collection presupuestoDetalleColeccion = presupuestoDetalleColeccion = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(presupuestoIf.getId());
			Iterator presupuestoDetalleColeccionIt = presupuestoDetalleColeccion.iterator();
			while(presupuestoDetalleColeccionIt.hasNext()){
				PresupuestoDetalleIf presupuestoDetalle = (PresupuestoDetalleIf)presupuestoDetalleColeccionIt.next();
				if(presupuestoDetalle.getReporte().equals("N")){
					if((presupuestoDetalle.getPorcentajeNegociacionDirecta() != null && presupuestoDetalle.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(0)) == 1)
							|| (presupuestoDetalle.getPorcentajeComisionPura() != null && presupuestoDetalle.getPorcentajeComisionPura().compareTo(new BigDecimal(0)) == 1)){
						existeNegociacionComision = true;
					}
				}				
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		return existeNegociacionComision;
	}
	
	private void formaFacturacionPresupuesto(){
		try {
			Collection presupuestosDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(presupuestoIf.getId());
			Collection presupuestosDetalleFacturacion = (Collection)DeepCopy.copy(presupuestosDetalle);
						
			Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
			HashMap<Long,ClienteOficinaIf> proveedoresNegociacionDirectaMap = new HashMap<Long,ClienteOficinaIf>();
			HashMap<Long,ClienteOficinaIf> proveedoresComisionPuraMap = new HashMap<Long,ClienteOficinaIf>();
			
			Map<Long,Long> presupuestoDetallesFacturadosMap = new HashMap<Long,Long>();			
			boolean habilitarFacturacionCliente = true;
			
			habilitarFacturacionCliente = cargarOpcionesFacturacion(
					presupuestosDetalleFacturacion, presupuestosDetalleIt,
					proveedoresNegociacionDirectaMap,
					proveedoresComisionPuraMap,
					presupuestoDetallesFacturadosMap,
					habilitarFacturacionCliente);
			
			tipoPresupuestoFacturacion = TIPO_PRESUPUESTO_FACTURACION_NORMAL;
			FacturacionPresupuestoModel jdFacturacionPresupuesto = new FacturacionPresupuestoModel(Parametros.getMainFrame(), presupuestoIf.getId(), proveedoresNegociacionDirectaMap, proveedoresComisionPuraMap, 
					habilitarFacturacionCliente, presupuestoDetallesFacturadosMap, mapaClienteOficina, mapaGenerico, mapaProductoProveedor);			
			
			// CRITERIO DE FACTURACIÓN
			// Si se elije facturar al Cliente
			if(jdFacturacionPresupuesto.getRbFacturarCliente().isSelected()){
				tipoPresupuestoFacturacion = TIPO_PRESUPUESTO_FACTURACION_CLIENTE;
				seleccionarPresupuesto(tipoPresupuestoFacturacion);
				
			}else if(jdFacturacionPresupuesto.getRbFacturacionParcial().isSelected()){
				tipoPresupuestoFacturacion = TIPO_PRESUPUESTO_FACTURACION_PARCIAL;
				mapaPresupuestosDetalleParaFacturar.clear();
				// si existen detalles seleccionados para la facturacion parcial
				if(jdFacturacionPresupuesto.getPresupuestoDetallesSeleccionados().size() > 0){
					
					for(int i=0; i<jdFacturacionPresupuesto.getPresupuestoDetallesSeleccionados().size(); i++){
						PresupuestoDetalleIf presupuestoDetalle = (PresupuestoDetalleIf)jdFacturacionPresupuesto.getPresupuestoDetallesSeleccionados().get(i);
						mapaPresupuestosDetalleParaFacturar.put(presupuestoDetalle.getId(), presupuestoDetalle.getId());
					}
					
					seleccionarPresupuesto(tipoPresupuestoFacturacion);
				}
						
			// si se elije facturar al proveedor el porcentaje que corresponde
			// en Negociación Directa
			}else if(jdFacturacionPresupuesto.getRbFacturarNegociacionDirecta().isSelected()){				
				tipoPresupuestoFacturacion = TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA;
				clienteOficinaIf = (ClienteOficinaIf)jdFacturacionPresupuesto.getCmbMedioNegociacionDirecta().getSelectedItem();
				clienteIf = mapaCliente.get(clienteOficinaIf.getClienteId());
				setCliente(true);
				seleccionarPresupuesto(tipoPresupuestoFacturacion);
				
			// Si se elije facturar la Comisión Pura
			}else if(jdFacturacionPresupuesto.getRbFacturarComisionPura().isSelected()){
				tipoPresupuestoFacturacion = TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA;
				clienteOficinaIf = (ClienteOficinaIf)jdFacturacionPresupuesto.getCmbMedioComisionPura().getSelectedItem();
				clienteIf = mapaCliente.get(clienteOficinaIf.getClienteId());
				setCliente(true);
				seleccionarPresupuesto(tipoPresupuestoFacturacion);
			}
			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private boolean cargarOpcionesFacturacion(
			Collection presupuestosDetalleFacturacion,
			Iterator presupuestosDetalleIt,
			HashMap<Long, ClienteOficinaIf> proveedoresNegociacionDirectaMap,
			HashMap<Long, ClienteOficinaIf> proveedoresComisionPuraMap,
			Map<Long, Long> presupuestoDetallesFacturadosMap,
			boolean habilitarFacturacionCliente)
			throws GenericBusinessException {
		// En este while lleno los arreglos
		while(presupuestosDetalleIt.hasNext()){
			PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf)presupuestosDetalleIt.next();
			
			// lleno arreglo de proveedores que tienen negociación directa
			// si en un detalle un proveedor tiene Negociacion Directa y
			// Comision Pura, tiene mas peso la Comision Pura
			// y ese porcentaje es el que se le cobrará, por eso no entra
			// este primer if pero si al segundo.
			if(presupuestoDetalleIf.getReporte().equals(REPORTE_PRESUPUESTO_NO) && presupuestoDetalleIf.getPorcentajeNegociacionDirecta() != null && presupuestoDetalleIf.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(0)) == 1
					&& (presupuestoDetalleIf.getPorcentajeComisionPura() == null || presupuestoDetalleIf.getPorcentajeComisionPura().compareTo(new BigDecimal(0)) <= 0)){
				ClienteOficinaIf proveedorNegociacionDirecta = mapaClienteOficina.get(presupuestoDetalleIf.getProveedorId());
				proveedoresNegociacionDirectaMap.put(proveedorNegociacionDirecta.getId(), proveedorNegociacionDirecta);
			}
			
			// llego arreglo de proveedores que tienen comisión pura
			if(presupuestoDetalleIf.getReporte().equals(REPORTE_PRESUPUESTO_NO) && presupuestoDetalleIf.getPorcentajeComisionPura() != null && presupuestoDetalleIf.getPorcentajeComisionPura().compareTo(new BigDecimal(0)) == 1){
				ClienteOficinaIf proveedorComisionPura = mapaClienteOficina.get(presupuestoDetalleIf.getProveedorId());
				proveedoresComisionPuraMap.put(proveedorComisionPura.getId(), proveedorComisionPura);
			}
		}
		
		// Reviso que opciones de facturacion debo habilitar dependiendo que
		// porcentajes existen en el presupuesto detalle
		// y que presupuestos detalles ya han sido facturados completa o
		// parcialmente
		
		Iterator presupuestosDetalleFacturacionIt = presupuestosDetalleFacturacion.iterator();
		while(presupuestosDetalleFacturacionIt.hasNext()){
			PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf)presupuestosDetalleFacturacionIt.next();
			
			if(presupuestoDetalleIf.getReporte().equals(REPORTE_PRESUPUESTO_NO)){
								
				// si la negociacion es de 100% no se le debe facturar nada
				// al cliente
				if(presupuestoDetalleIf.getPorcentajeNegociacionDirecta() != null && 
						presupuestoDetalleIf.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(100)) == 0){
					habilitarFacturacionCliente = false;
				}
				
				Map presupuestoFacturacionMap = new HashMap();
				// busco los registros solo con estado Facturado
				presupuestoFacturacionMap.put("estado", ESTADO_PRESUPUESTO_FACTURACION_FACTURADO);
				presupuestoFacturacionMap.put("presupuestoDetalleId", presupuestoDetalleIf.getId());
				Collection presupuestosDetalleFacturados = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByQuery(presupuestoFacturacionMap);
				Iterator presupuestosDetalleFacturadosIt = presupuestosDetalleFacturados.iterator();
				while(presupuestosDetalleFacturadosIt.hasNext()){
					PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosDetalleFacturadosIt.next();
					
					// mapa me sirve para que ya no aparezcan estos
					// presupuestos detalles en la facturación parcial
					//if(presupuestoFacturacionIf.getTipo().equals(TIPO_PRESUPUESTO_FACTURACION_PARCIAL))
						presupuestoDetallesFacturadosMap.put(presupuestoFacturacionIf.getPresupuestoDetalleId(), presupuestoFacturacionIf.getPresupuestoDetalleId());
											
					// CLIENTE
					if(presupuestoFacturacionIf.getTipo().equals(TIPO_PRESUPUESTO_FACTURACION_CLIENTE) || 
							presupuestoFacturacionIf.getTipo().equals(TIPO_PRESUPUESTO_FACTURACION_PARCIAL)){
						habilitarFacturacionCliente = false;
					}
					
					// NEGOCIACION DIRECTA
					// Si el mapa de proveedores con negociacion directa
					// esta vacio entonces no habilito la opcion
					if(!proveedoresNegociacionDirectaMap.isEmpty()){
						ArrayList<Long> proveedoresIdNegociacionDirectaFacturados = new ArrayList<Long>();
						// si existen proveedores con negociacion directa
						// recorro el mapa
						Iterator proveedoresNegociacionDirectaMapIt = proveedoresNegociacionDirectaMap.keySet().iterator();
						while(proveedoresNegociacionDirectaMapIt.hasNext()){
							Long proveedorNegociacionDirectaId = (Long)proveedoresNegociacionDirectaMapIt.next();
							
							// comparo uno por uno los proveedores para
							// contar cuantos de ellos ya se les facturo la
							// negociacion directa
							//PresupuestoDetalleIf presupuestoDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoFacturacionIf.getPresupuestoDetalleId());
							if(presupuestoDetalleIf.getProveedorId().compareTo(proveedorNegociacionDirectaId) == 0 && presupuestoFacturacionIf.getTipo().equals("D")){
								// guardo el id del proveedor facturado
								proveedoresIdNegociacionDirectaFacturados.add(proveedorNegociacionDirectaId);
							}
						}							
						// por ultimo remuevo del mapa de proveedores los
						// que ya han sido facturados para que no aparezcan
						// en el combo.
						for(Long proveedorIdNegociacionDirectaFacturado : proveedoresIdNegociacionDirectaFacturados){
							proveedoresNegociacionDirectaMap.remove(proveedorIdNegociacionDirectaFacturado);
						}							
					}
					
					// COMISION PURA
					// Si el mapa de proveedores con comision pura esta
					// vacio entonces no habilito la opcion
					if(!proveedoresComisionPuraMap.isEmpty()){
						ArrayList<Long> proveedoresIdComisionPuraFacturados = new ArrayList<Long>();
						// si existen proveedores con negociacion directa
						// recorro el mapa
						Iterator proveedoresComisionPuraMapIt = proveedoresComisionPuraMap.keySet().iterator();
						while(proveedoresComisionPuraMapIt.hasNext()){
							Long proveedorComisionPuraId = (Long)proveedoresComisionPuraMapIt.next();
							
							// comparo uno por uno los proveedores para
							// contar cuantos de ellos ya se les facturo la
							// comision pura
							//PresupuestoDetalleIf presupuestoDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoFacturacionIf.getPresupuestoDetalleId());
							if(presupuestoDetalleIf.getProveedorId().compareTo(proveedorComisionPuraId) == 0 && presupuestoFacturacionIf.getTipo().equals("P")){
								// guardo el id del proveedor facturado
								proveedoresIdComisionPuraFacturados.add(proveedorComisionPuraId);
							}
						}							
						// por ultimo remuevo del mapa de proveedores los
						// que ya han sido facturados para que no aparezcan
						// en el combo.
						for(Long proveedorIdComisionPuraFacturado : proveedoresIdComisionPuraFacturados){
							proveedoresComisionPuraMap.remove(proveedorIdComisionPuraFacturado);
						}							
					}
				}
			}	
		}
		return habilitarFacturacionCliente;
	}

	private void seleccionarPresupuesto(String tipoPresupuestoFacturacion) throws GenericBusinessException {
		if (presupuestoIf != null) {
			getTxtEscojaReferencia().setText(presupuestoIf.toString());
			if (pedidoDetalleColeccion.size() != 0) {
				Iterator it = pedidoDetalleColeccion.iterator();
				while (it.hasNext()) {
					PedidoDetalleIf detalleRemovido = (PedidoDetalleIf) it.next();
					if (detalleRemovido.getId() != null)
						pedidoDetalleEliminadas.add(detalleRemovido);
				}
				cleanTabla();
				pedidoDetalleColeccion.clear();
			}

			getTxtObservacion().setText(presupuestoIf.getConcepto());
			getCmbFormaPago().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbFormaPago(), presupuestoIf.getFormaPagoId()));
			getTxtDiasValidez().setText(presupuestoIf.getDiasValidez().toString());
			
			// sap
			if(presupuestoIf.getAutorizacionSap() != null && !presupuestoIf.getAutorizacionSap().equals("")){
				getTxtAutorizacionSAP().setText(presupuestoIf.getAutorizacionSap());
			}
			
			// Selecciono el vendedor segun el ejecutivo que hizo la orden de
			// trabajo de donde salio el presupuesto
			OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuestoIf.getOrdentrabajodetId());
			OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
			EmpleadoIf vendedor = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenTrabajo.getEmpleadoId());
			getCmbVendedor().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbVendedor(), vendedor.getId()));
			
			// Selecciono el equipo y director del ejecutivo
			HashMap buscarDirectorMap = new HashMap();
			buscarDirectorMap.put("equipoId", ordenTrabajo.getEquipoId());
			buscarDirectorMap.put("rol", "DCU");
			Collection directorEquipos = SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByQuery(buscarDirectorMap);
			if(directorEquipos.size() > 0){
				EquipoEmpleadoIf directorEquipo = (EquipoEmpleadoIf)directorEquipos.iterator().next();
				EmpleadoIf director = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(directorEquipo.getEmpleadoId());
				EquipoTrabajoIf equipoTrabajoDirector = SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajo(directorEquipo.getEquipoId());
				getCmbDirectorCuentas().setSelectedItem(equipoTrabajoDirector.getCodigo() + " - " + director);
			}
			
			// Segun el tipo de facturacion cargo los detalles en el pedido con
			// los valores correctos
			ClienteOficinaIf clienteOficina = mapaClienteOficina.get(ordenTrabajo.getClienteoficinaId());
			if(tipoPresupuestoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA)){
				cargarDetallesPresupuestoTipoFacturacion(TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA, clienteOficina.getDescripcion());
			}else if(tipoPresupuestoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA)){
				cargarDetallesPresupuestoTipoFacturacion(TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA, clienteOficina.getDescripcion());
			}else{
				cargarDetallesPresupuesto(null);
			}
		}
	}

	// CASO FACTURA DE COMISION INCONCLUSA
	private boolean existeFacturaComision(){
		try {
			final String CODIGO_TIPO_DOCUMENTO_FACTURA_REEMBOLSO = "FAR";
			
			// si existe el tipo documento Factura de Reembolso
			Collection tipoDocumentoFacturaReembolsoList = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo(CODIGO_TIPO_DOCUMENTO_FACTURA_REEMBOLSO);
			
			if(tipoDocumentoFacturaReembolsoList.size() > 0){
				TipoDocumentoIf tipoDocumentoFacturaReembolso = (TipoDocumentoIf)tipoDocumentoFacturaReembolsoList.iterator().next();
				
				// Si el pedido esta completo, es de reembolso y tiene
				// referencia
				if(pedido.getEstado().equals(EstadoPedido.COMPLETO.getLetra()) 
						&& pedido.getTipodocumentoId().compareTo(tipoDocumentoFacturaReembolso.getId()) == 0 
						&& pedido.getTiporeferencia() != null && !pedido.getTiporeferencia().equals("")){
					
					// Si existe el tipo documento Factura de Comisión
					Collection tipoDocumentoFacturaComisionList = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo(CODIGO_TIPO_DOCUMENTO_FACTURA_COMISION);
					if(tipoDocumentoFacturaComisionList.size() > 0){
						TipoDocumentoIf tipoDocumentoFacturaComision = (TipoDocumentoIf)tipoDocumentoFacturaComisionList.iterator().next();
						
						HashMap aMapPedidoComision = new HashMap();
						aMapPedidoComision.put("estado", EstadoPedido.COMPLETO.getLetra());
						aMapPedidoComision.put("tipodocumentoId", tipoDocumentoFacturaComision.getId());
						aMapPedidoComision.put("tiporeferencia", pedido.getTiporeferencia());
						aMapPedidoComision.put("referencia", pedido.getReferencia());
						
						// Si no existe el pedido de comision, SE DE LA OPCION
						// DE CREARLO
						Collection pedidComisionList = SessionServiceLocator.getPedidoSessionService().findPedidoByQuery(aMapPedidoComision);
						if(pedidComisionList.size() > 0){	
							return true;						
						}
					}
				}					
			}
			
		}catch (GenericBusinessException e) {
			e.printStackTrace();
		}			
		
		return false;
	}

	private void crearPedidoFacturaComision() throws GenericBusinessException {
		// Busco el preimpreso de la factura de reembolso y si existe doy la
		// opcion de crear la factura de comisión
		HashMap aMapFacturaReembolso = new HashMap();
		aMapFacturaReembolso.put("estado", EstadoPedido.COMPLETO.getLetra());
		aMapFacturaReembolso.put("pedidoId", pedido.getId());
		Collection facturaReembolsoList = SessionServiceLocator.getFacturaSessionService().findFacturaByQuery(aMapFacturaReembolso);
		if(facturaReembolsoList.size() > 0){
			FacturaIf facturaReembolso = (FacturaIf)facturaReembolsoList.iterator().next();
			
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar la Factura de Comisión?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				facturarComision = generarPedidoFacturaComision(facturaReembolso.getPreimpresoNumero());
				if(facturarComision){
					savePedido();
					SpiritAlert.createAlert("Pedido guardado con éxito",SpiritAlert.INFORMATION);
					pedido = SessionServiceLocator.getPedidoSessionService().getPedido(idPedidoGuardado);
					facturar(pedido.getId());
					facturaDetalleTempColeccion.clear();
					this.clean();
					this.showSaveMode();
				}else{
					SpiritAlert.createAlert("No es posible crear la Factura de Comisión porque su valor Total es $0.00", SpiritAlert.WARNING);
				}						
			} 
		}
	}

	private void facturar() {
		try {
			if (pedido != null && getMode() == SpiritMode.UPDATE_MODE) {
				String estadoLetra = pedido.getEstado();
				EstadoPedido estado = EstadoPedido.getEstadoOrdenTrabajoByLetra(estadoLetra);
								
				if (estado == EstadoPedido.PENDIENTE) {
					if ( tipoDocumentoIf != null && ("Fac".equalsIgnoreCase(tipoDocumentoIf.getCodigo()) || "Far".equalsIgnoreCase(tipoDocumentoIf.getCodigo()) || "Fae".equalsIgnoreCase(tipoDocumentoIf.getCodigo()) || "Fco".equalsIgnoreCase(tipoDocumentoIf.getCodigo()) || "Vta".equalsIgnoreCase(tipoDocumentoIf.getCodigo()))) {
						facturar(pedido.getId());
						facturaDetalleTempColeccion.clear();
						this.clean();
						this.showSaveMode();
					} else
						SpiritAlert.createAlert("Debe seleccionar un Tipo Documento Factura !!", SpiritAlert.WARNING);
				
				} else if(estado == EstadoPedido.COMPLETO && !existeFacturaComision()){
					crearPedidoFacturaComision();
				}else if (estado == EstadoPedido.COMPLETO || estado == EstadoPedido.INCOMPLETO)
					SpiritAlert.createAlert("El pedido ya ha generado una factura !", SpiritAlert.WARNING);
				else if (estado == EstadoPedido.ANULADO)
					SpiritAlert.createAlert("El pedido ha sido anulado !", SpiritAlert.WARNING);
				else if (estado == EstadoPedido.COTIZACION)
					SpiritAlert.createAlert("El pedido pertenece a una cotización !", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}

	private void facturar(Long idPedido) throws GenericBusinessException {
		if (getCmbTipoDocumento().getSelectedItem() != null) {
			Long tipoDocumentoId = ((TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem()).getId();
			TipoDocumentoIf tipoDocumentoIf = mapaTipoDocumento.get(tipoDocumentoId);
			int numLineas = tipoDocumentoIf.getNumerolineas();

			int numPag = 0;
			if (pedidoDetalleColeccion.size() % numLineas == 0) {
				numPag = (pedidoDetalleColeccion.size() / numLineas);
			}
			if (pedidoDetalleColeccion.size() % numLineas != 0) {
				numPag = (pedidoDetalleColeccion.size() / numLineas) + 1;
			}

			if (numPag > 1) {
				String si = "Si"; 
    	        String no = "No"; 
    	        Object[] options = {si,no}; 
    			int opcion = JOptionPane.YES_OPTION;
   				opcion = JOptionPane.showOptionDialog(null, "Este pedido generará (" + String.valueOf(numPag) + ") facturas. [RECOMENDADO]\n" +
   															"Si desea generar una sola factura, el reporte de factura podría no visualizarse adecuadamente.\n" + 
   															"¿Desea generar una sola factura?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
   				if (opcion == JOptionPane.YES_OPTION) {
   					numPag = 1;
   					numLineas = 1000;
   				}
			}
			for (int n = 0; n < numPag; n++) {
				// seteo los valores iniciales de la factura a cero inicialmente
				// para poder generar cada factura según las lineas con los
				// valores calculados aqui
				total = 0D;
				descuentoTotal = 0D;
				descuentosVariosTotal = 0D;
				ivaTotal = 0D;

				int j = 0;
				int i = n * numLineas;
				facturaDetalleTempColeccion = new Vector<PedidoDetalleIf>();

				if (pedidoDetalleColeccion.size() < numLineas) {
					numLineas = pedidoDetalleColeccion.size();
				}
				// genero una coleccion temporal que va a mantener los items
				// seleccionados de la colección original según el numero de
				// lineas que tiene el tipo de documento<PedidoDetalleIf>
				while (i < ((n + 1) * numLineas)) {
					if (pedidoDetalleColeccion.size() > i) {
						PedidoDetalleIf pedidoDetalleIf = (PedidoDetalleIf) pedidoDetalleColeccion.get(i); 
						facturaDetalleTempColeccion.add(pedidoDetalleIf);
						total += pedidoDetalleIf.getValor().doubleValue();
						ProductoIf producto = mapaProductoProveedor.get(pedidoDetalleIf.getProductoId());
						GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
						
						if (generico.getAceptaDescuento().equals("S")) {
							descuentoTotal +=  pedidoDetalleIf.getDescuento().doubleValue() + pedidoDetalleIf.getDescuentoGlobal().doubleValue();
							double porcentajeDescuentosVarios = pedidoDetalleIf.getPorcentajeDescuentosVarios().doubleValue() / 100;
							double descuentosVarios = pedidoDetalleIf.getValor().doubleValue() * porcentajeDescuentosVarios;
							descuentosVariosTotal += descuentosVarios;
						}
						
						ivaTotal = ivaTotal + pedidoDetalleIf.getIva().doubleValue();
						i++;
						j++;
						
					} else {
						break;
					}
				}

				generarFactura(idPedido);
			}
		}
	}

	public void generarFactura(Long idPedido) {
		try {
			// Setea todos los atributos de FacturaData en factura
			FacturaIf factura = registrarFactura();
			// Tipo Referencia = N No se que significa
			String tipoReferenciaPedido = pedido.getTiporeferencia();
			ParametroEmpresaIf fundacionRequerida = buscarParametroActivarDonacion();
			boolean donacionActiva = false;
			if (fundacionRequerida != null && fundacionRequerida.getValor().equals("S"))
				donacionActiva = true;			
			Long idFundacion = null;
			// Comobo de Fundacion en la pestania de Pedido
			if (getCmbFundacion().getSelectedItem() != null)
				fundacion = (ClienteIf) getCmbFundacion().getSelectedItem();
			if (donacionActiva && fundacion != null) {
				idFundacion = fundacion.getId();
			}
			
			// Creo los registros de la tabla PRESUPUESTO_FACTURACION
			presupuestoFacturacionColeccion = new Vector<PresupuestoFacturacionIf>();
			for(PresupuestoDetalleIf presupuestoDetalleFacturado : presupuestoDetalleFacturadoColeccion){
				PresupuestoFacturacionData presupuestoFacturacionData = new PresupuestoFacturacionData();
				presupuestoFacturacionData.setEstado(ESTADO_PRESUPUESTO_FACTURACION_FACTURADO);
				presupuestoFacturacionData.setPresupuestoDetalleId(presupuestoDetalleFacturado.getId());
				presupuestoFacturacionData.setTipo(tipoPresupuestoFacturacion);
				presupuestoFacturacionColeccion.add(presupuestoFacturacionData);
			}
			
			OficinaIf oficina = (OficinaIf)getCmbOficinaEmpresa().getSelectedItem();
			Long idFactura = SessionServiceLocator.getFacturaSessionService().procesarFactura(factura, facturaDetalleTempColeccion, 
					Parametros.getIdEmpresa(), oficina.getId(), presupuestosIf, presupuestoFacturacionColeccion, tipoReferenciaPedido, 
					(UsuarioIf) Parametros.getUsuarioIf(), donacionActiva, idFundacion, tarea);
			
			fundacion = null;
			facturaGenerada = SessionServiceLocator.getFacturaSessionService().getFactura(idFactura);
			
			int numeroProductos = verificarCantidadesDetalleFactura();
			
			if ( facturaGenerada != null ) {
				String mensajeExito = "Factura generada con \u00e9xito";
				
				
				////PLAN MEDIO////				
				//caso multiple
				if(planesMedioIf.size() > 0){
					for(PlanMedioIf planMedio : planesMedioIf){
						actualizarPlanMedioFacturacion(planMedio);
					}
				}
				//caso normal
				else if(planMedioIf != null){
					actualizarPlanMedioFacturacion(planMedioIf);
				}
				////FIN PLAN MEDIO////
				
				
				if ( numeroProductos != facturaDetalleTempColeccion.size() )
					SpiritAlert.createAlert(mensajeExito, SpiritAlert.INFORMATION);
				else
					SpiritAlert.createAlert(mensajeExito, SpiritAlert.INFORMATION);

				// genero el reporte
				report();
				
				// doy la opcion de generar el preimpreso (en este metodo se
				// actualiza el asiento con el preimpreso)
				String preimpreso = null;
				final FacturaIf facturaPreimpreso = facturaGenerada;
				IngresarPreimpresoModel jdIngresarPreimpreso = new IngresarPreimpresoModel(Parametros.getMainFrame(), facturaPreimpreso);
				preimpreso = jdIngresarPreimpreso.getPreimpreso();
				
				// opcion de generar el asiento de la factura
				generarReporteAsiento(facturaPreimpreso);
								
				TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(factura.getTipodocumentoId());
				if (tipoDocumento.getReembolso().equals("S") && (presupuestoIf != null || planMedioIf != null)) {
					int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar la Factura de Comisión?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if (opcion == JOptionPane.YES_OPTION) {
						facturarComision = generarPedidoFacturaComision(preimpreso);
						if(facturarComision){
							save();
							facturarComision = false;
						}else{
							SpiritAlert.createAlert("No es posible crear la Factura de Comisión porque su valor Total es $0.00", SpiritAlert.WARNING);
						}						
					}
				}
				
				// recien puedo limpiar el mapa una vez facturado todo
				mapaPresupuestosDetalleParaFacturar.clear();
								
			} else {    
				String mensajeError1 = "Revisar la(s) cantidad(es) disponible(s) de producto(s)";
				String mensajeError2 = "No se pudo Generar Factura";
				if ( numeroProductos == 0 )
					SpiritAlert.createAlert(mensajeError1, SpiritAlert.INFORMATION);
				else
					SpiritAlert.createAlert(mensajeError2, SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Ocurri\u00f3 un error al generar la Factura", SpiritAlert.ERROR);
		}
	}

	private void actualizarPlanMedioFacturacion(PlanMedioIf planMedio) {
		try {
			Long facturaGeneradaPedidoId = facturaGenerada.getPedidoId();
			
			Map aMap = new HashMap();
			aMap.put("pedidoId", facturaGenerada.getPedidoId());
			aMap.put("estado", "P"); // PEDIDO
			Collection planMedioFormaPagoColl = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByQuery(aMap);
			
			if (planMedioFormaPagoColl.size() > 0){
				Iterator planMedioFormaPagoCollIt = planMedioFormaPagoColl.iterator();
				PlanMedioFormaPagoIf planMedioFormaPagoIf = (PlanMedioFormaPagoIf)planMedioFormaPagoCollIt.next();
				planMedioFormaPagoIf.setEstado("F");
				SessionServiceLocator.getPlanMedioFormaPagoSessionService().actualizarPlanMedioFormaPago(planMedioFormaPagoIf);
			}
			
			ArrayList<PlanMedioFacturacionIf> planMedioFacturacionColl = (ArrayList<PlanMedioFacturacionIf>)SessionServiceLocator.getPlanMedioFacturacionSessionService().findPlanMedioFacturacionByQuery(aMap);
			
			for(int i=0; i<planMedioFacturacionColl.size(); i++){
				planMedioFacturacionColl.get(i).setEstado("F");
			}
			
			SessionServiceLocator.getPlanMedioFacturacionSessionService().actualizarPlanMedioFacturacionList(planMedioFacturacionColl);
			
			// Se obtiene todos los PlanesMedioFacturacion del Plan de Medio existentes en la DB
			Map pmfMapa = new HashMap();
			pmfMapa.put("planMedioId", planMedio.getId());
			String[] estados = {"P", "F"}; // PENDIENTE, FACTURADO
			Collection planMedioFacturacionGColl = SessionServiceLocator.getPlanMedioFacturacionSessionService().findPlanMedioFacturacionByQueryAndByEstados(pmfMapa, estados);
			
			// Llena la listas de PlanMedioFacturacion Total que
			// deberian ser generadas por un Plan Medio
			// con los campos de Comercial, Ordenes de Medio Orden Medio
			// Detalle, Orden Medio DetalleMapa
			
			ArrayList<PlanMedioFacturacionIf> planMedioFacturacionListTotal = SessionServiceLocator.getPlanMedioSessionService().getPlanMedioFacturacionTotal(planMedio,false);
			
			if(planMedioFacturacionListTotal.size()==planMedioFacturacionGColl.size()){
				Iterator planMedioFacturacionGCollIt = planMedioFacturacionGColl.iterator();
				int tmp = 0;
				while(planMedioFacturacionGCollIt.hasNext()){
					PlanMedioFacturacionIf planMedioFacturacionIf = (PlanMedioFacturacionIf)planMedioFacturacionGCollIt.next();
					if(planMedioFacturacionIf.getEstado().compareTo("F")==0){
						tmp++;
					}
				}
				if(tmp == planMedioFacturacionGColl.size()){
					planMedio.setEstado("F");
					SessionServiceLocator.getPlanMedioSessionService().savePlanMedio(planMedio);
				}
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private boolean generarPedidoFacturaComision(String preimpreso) {
		PedidoIf pedido = (PedidoIf) DeepCopy.copy(this.pedido);
		// Vector<PedidoDetalleIf> pedidoDetalleColeccion =
		// (Vector<PedidoDetalleIf>) DeepCopy.copy(this.PedidoDetalleColeccion);
		this.pedido = registrarPedidoFacturaComision(pedido);
		cleanTabla();
		pedidoDetalleColeccion.clear();
		
		if(presupuestoIf != null){
			cargarDetallesPresupuesto(preimpreso);
			if(total <= 0.1D){
				return false;
			}
		}else if (planMedioIf != null){
			cargarDetallesPlanMedios(preimpreso);
			if(total <= 0.1D){
				return false;
			}
		}
		return true;
	}

	private PedidoIf registrarPedidoFacturaComision(PedidoIf pedido) {
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			parameterMap.put("codigo", CODIGO_TIPO_DOCUMENTO_FACTURA_COMISION);
			Iterator it = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQuery(parameterMap).iterator();
			tipoDocumento = (it.hasNext())?(TipoDocumentoIf) it.next():null;
			getCmbTipoDocumento().removeActionListener(oActionListenerCmbTipoReferencia);
			getCmbTipoDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoDocumento(), tipoDocumento.getId()));
			getCmbTipoDocumento().addActionListener(oActionListenerCmbTipoReferencia);
			java.sql.Timestamp fechaPedido = new java.sql.Timestamp(getCmbFechaPedido().getDate().getTime());
			pedido.setId(null);
			String codigo = getCodigoPedido(new java.sql.Date(fechaPedido.getYear(), fechaPedido.getMonth(), fechaPedido.getDate()));
			pedido.setCodigo(codigo);
			pedido.setFechaPedido(fechaPedido);
			
			if(getCmbFechaVencimiento().getDate() != null){
				pedido.setFechaVencimiento(new java.sql.Timestamp(getCmbFechaVencimiento().getDate().getTime()));
			}
			
			java.util.Date fechaCreacion = new java.util.Date();
			pedido.setFechaCreacion(new java.sql.Timestamp(fechaCreacion.getTime()));
			
			OficinaIf oficina = (OficinaIf)getCmbOficinaEmpresa().getSelectedItem();
			pedido.setOficinaId(oficina.getId());
			
			pedido.setTipodocumentoId(tipoDocumento.getId());
			pedido.setClienteoficinaId(clienteOficinaIf.getId());
			pedido.setTipoidentificacionId(((TipoIdentificacionIf) getCmbTipoIdentificacion().getSelectedItem()).getId());
			pedido.setFormapagoId(((FormaPagoIf) this.getCmbFormaPago().getSelectedItem()).getId());
			pedido.setMonedaId(((MonedaIf) this.getCmbMoneda().getSelectedItem()).getId());
			
			if (puntoImpresionIf != null)
				pedido.setPuntoimpresionId(puntoImpresionIf.getId());
			
			pedido.setOrigendocumentoId(((OrigenDocumentoIf) this.getCmbOrigenDocumento().getSelectedItem()).getId());
			pedido.setVendedorId(((EmpleadoIf) this.getCmbVendedor().getSelectedItem()).getId());
			pedido.setBodegaId(((BodegaIf) this.getCmbBodega().getSelectedItem()).getId());
			pedido.setListaprecioId(((ListaPrecioIf) this.getCmbListaPrecio().getSelectedItem()).getId());
			pedido.setUsuarioId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
			pedido.setDiasvalidez(Double.valueOf(this.getTxtDiasValidez().getText()).intValue());
			
			TipoReferenciaPedido tr = (TipoReferenciaPedido) getCmbTipoReferencia().getSelectedItem(); 
			pedido.setTiporeferencia(tr.getLetra());
			/*
			 * if(this.getCmbTipoReferencia().getSelectedItem().toString().equals(NOMBRE_REFERENCIA_NINGUNO))
			 * pedido.setTiporeferencia(REFERENCIA_NINGUNO); else
			 * if(this.getCmbTipoReferencia().getSelectedItem().toString().equals(NOMBRE_REFERENCIA_PRESUPUESTO))
			 * pedido.setTiporeferencia(REFERENCIA_PRESUPUESTO); else
			 * if(this.getCmbTipoReferencia().getSelectedItem().toString().equals(NOMBRE_REFERENCIA_PLAN_MEDIOS))
			 * pedido.setTiporeferencia(REFERENCIA_PLAN_MEDIOS);
			 */
			
			// pedido.setEstado(ESTADO_PENDIENTE);
			pedido.setEstado(EstadoPedido.PENDIENTE.getLetra());
			pedido.setDireccion(getTxtDireccion().getText());
			pedido.setObservacion(getTxtObservacion().getText());
			pedido.setIdentificacion(getTxtIdentificacion().getText());
			
			// if
			// (getCmbTipoReferencia().getSelectedItem().equals(NOMBRE_REFERENCIA_PRESUPUESTO)
			// && presupuestoIf != null)
			if (tr == TipoReferenciaPedido.PRESUPUESTO && presupuestoIf != null)
				pedido.setReferencia(presupuestoIf.getCodigo());
			// else if
			// (getCmbTipoReferencia().getSelectedItem().equals(NOMBRE_REFERENCIA_PLAN_MEDIOS)
			// && planMedioIf != null)
			else if (tr == TipoReferenciaPedido.PLAN_MEDIOS && planMedioIf != null)
				pedido.setReferencia(planMedioIf.getCodigo());
			else
				pedido.setReferencia(getTxtReferencia().getText());
			
			pedido.setTelefono(getTxtTelefono().getText());
			if ( !"".equalsIgnoreCase(getTxtContacto().getText()) )
				pedido.setContacto(getTxtContacto().getText());
			else
				pedido.setContacto(" ");
			
			pedido.setPorcentajeComisionAgencia(BigDecimal.valueOf(porcentajeComisionAgencia));
			
			try {
				// para setear el equipo del ejecutivo
				String equipoDirector = (String)getCmbDirectorCuentas().getSelectedItem();
				String codigoEquipo = equipoDirector.split(" - ")[0];		
				EquipoTrabajoIf equipoSeleccionado = (EquipoTrabajoIf)SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByCodigo(codigoEquipo).iterator().next();
				pedido.setEquipoId(equipoSeleccionado.getId());
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return pedido;
	}
	
	private void generarReporteAsiento(FacturaIf facturaPreimpreso) {
		try {
			if(facturaPreimpreso != null){
				HashMap asientoCompraMap = new HashMap();
				asientoCompraMap.put("tipoDocumentoId", facturaPreimpreso.getTipodocumentoId());
				asientoCompraMap.put("transaccionId", facturaPreimpreso.getId());
				Collection asientosCompra = SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(asientoCompraMap);
				if(asientosCompra.size() > 0){
					int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte del Asiento?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if(opcion == JOptionPane.YES_OPTION) {
						
						AsientoIf asientoCompra = (AsientoIf)asientosCompra.iterator().next();
						ArrayList<AsientoDetalleIf> asientoDetalleColeccion = (ArrayList<AsientoDetalleIf>)SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByAsientoId(asientoCompra.getId());
						
						Map cuentasMap = SessionServiceLocator.getCuentaSessionService().mapearCuentas(Parametros.getIdEmpresa());
						Map usuariosMap = SessionServiceLocator.getUsuarioSessionService().mapearUsuarios(Parametros.getIdEmpresa());
						Map empleadosMap = SessionServiceLocator.getEmpleadoSessionService().mapearEmpleados(Parametros.getIdEmpresa());
						double totalDebe = 0D;
						double totalHaber = 0D;
						List<AutorizarAsientoData> autorizarAsientoDataColeccion = new ArrayList<AutorizarAsientoData>();
						for(AsientoDetalleIf asientoDetalle : asientoDetalleColeccion){
							AutorizarAsientoData data = new AutorizarAsientoData();
							CuentaIf cuenta = (CuentaIf) cuentasMap.get(asientoDetalle.getCuentaId());
							data.setCuenta(cuenta.getCodigo());
							data.setFechaMovimiento(asientoCompra.getFecha().toString());
							data.setGlosa((asientoDetalle.getGlosa().length()>70?asientoDetalle.getGlosa().substring(0,70):asientoDetalle.getGlosa()));
							data.setDebe(formatoDecimal.format(asientoDetalle.getDebe()));
							data.setHaber(formatoDecimal.format(asientoDetalle.getHaber()));
							data.setDebe(formatoDecimal.format(asientoDetalle.getDebe()));
							data.setHaber(formatoDecimal.format(asientoDetalle.getHaber()));
							totalDebe += asientoDetalle.getDebe().doubleValue();
							data.setTotalDebe(formatoDecimal.format(totalDebe));
							totalHaber += asientoDetalle.getHaber().doubleValue();
							data.setTotalHaber(formatoDecimal.format(totalHaber));
							data.setMes(Utilitarios.getNombreMes(asientoCompra.getFecha().getMonth() + 1).substring(0,3));
							data.setNombreCuenta((cuenta.getNombre().length()>70?cuenta.getNombre().substring(0,70):cuenta.getNombre()));
							data.setNumero(asientoCompra.getNumero());
							if (asientoCompra.getElaboradoPorId() != null) {
								UsuarioIf elaboradoPor = (UsuarioIf) usuariosMap.get(asientoCompra.getElaboradoPorId());
								EmpleadoIf empleado = (EmpleadoIf) empleadosMap.get(elaboradoPor.getEmpleadoId());
								data.setElaboradoPor(empleado.getNombres() + " " + empleado.getApellidos());
							}
							
							if (asientoCompra.getAutorizadoPorId() != null) {
								UsuarioIf autorizadoPor = ((UsuarioIf) Parametros.getUsuarioIf());
								EmpleadoIf empleado = (EmpleadoIf) empleadosMap.get(autorizadoPor.getEmpleadoId());
								data.setAutorizadoPor(empleado.getNombres() + " " + empleado.getApellidos());
							}
							data.setTipoDocumentoId((asientoCompra.getTipoDocumentoId()!=null)?asientoCompra.getTipoDocumentoId():null);
							data.setTransaccionId((asientoCompra.getTransaccionId()!=null)?asientoCompra.getTransaccionId():null);
							autorizarAsientoDataColeccion.add(data);
						}
						
						String fileName = "jaspers/contabilidad/RPAutorizacionAsiento.jasper";
						int seccionesHoja = 1;
						Map tiposTroqueladoMap = SessionServiceLocator.getTipoTroqueladoSessionService().mapearTiposTroquelado();
						if (asientoCompra.getTipoDocumentoId() != null) {
							TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(asientoCompra.getTipoDocumentoId());
							if (tipoDocumento.getTipoTroqueladoId() != null) {
								TipoTroqueladoIf tipoTroquelado = (TipoTroqueladoIf) tiposTroqueladoMap.get(tipoDocumento.getTipoTroqueladoId());
								if (tipoTroquelado.getNumeroSeccionesHoja() > seccionesHoja)
									seccionesHoja = tipoTroquelado.getNumeroSeccionesHoja();
							}
						}
						
						if (seccionesHoja == 2)
							fileName = "jaspers/contabilidad/RPAutorizacionAsientoDoble.jasper";
						else if (seccionesHoja == 4)
							fileName = "jaspers/contabilidad/RPAutorizacionAsientoCuadruple.jasper";
						
						HashMap parametrosMap = new HashMap();
						MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("ASIENTO").iterator().next();
						
						parametrosMap.put("codigoReporte", menu.getCodigo());
						EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
						parametrosMap.put("empresa", empresa.getNombre());
						parametrosMap.put("ruc", empresa.getRuc());
						OficinaIf oficina = (OficinaIf) Parametros.getOficina();
						CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
						parametrosMap.put("ciudad", ciudad.getNombre());
						parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
						parametrosMap.put("usuario", Parametros.getUsuario().toLowerCase());
						parametrosMap.put("emitido", Utilitarios.dateHoraHoy());
						ReportModelImpl.processReport(fileName, parametrosMap, autorizarAsientoDataColeccion, true);
					}
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}
	
	private int verificarCantidadesDetalleFactura(){
		Iterator it = facturaDetalleTempColeccion.iterator();
		int contadorProductosConCantidadDisponibles = 0;
		while(it.hasNext()){
			PedidoDetalleIf pedidoDetalleIf = (PedidoDetalleIf) it.next();
			if ( pedidoDetalleIf.getCantidad().compareTo(BigDecimal.ZERO) != 0  )
				contadorProductosConCantidadDisponibles++;
		}
		return contadorProductosConCantidadDisponibles;
	}
	
	private FacturaIf registrarFactura() {
		
		FacturaData data = new FacturaData();
		
		OficinaIf oficina = (OficinaIf)getCmbOficinaEmpresa().getSelectedItem();
		data.setOficinaId(oficina.getId());
		data.setTipodocumentoId(((TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem()).getId());
		data.setClienteoficinaId(clienteOficinaIf.getId());
		data.setTipoidentificacionId(tipoIdentificacionIf.getId());
		data.setIdentificacion(getTxtIdentificacion().getText());
		data.setFormapagoId(((FormaPagoIf) getCmbFormaPago().getSelectedItem()).getId());
		data.setMonedaId(((MonedaIf) getCmbMoneda().getSelectedItem()).getId());
		
		if (puntoImpresionIf != null)
			data.setPuntoImpresionId(puntoImpresionIf.getId());
		
		data.setOrigendocumentoId(((OrigenDocumentoIf) getCmbOrigenDocumento().getSelectedItem()).getId());
		
		if (getCmbVendedor().getSelectedItem() != null)
			data.setVendedorId(((EmpleadoIf) getCmbVendedor().getSelectedItem()).getId());
		
		data.setBodegaId(((BodegaIf) getCmbBodega().getSelectedItem()).getId());
		data.setPedidoId(pedido.getId());
		data.setListaPrecioId(((ListaPrecioIf) getCmbListaPrecio().getSelectedItem()).getId());
		data.setFechaCreacion(new java.sql.Timestamp(fechaCreacion.getTime()));
		java.sql.Timestamp fechaFactura = new java.sql.Timestamp(getCmbFechaPedido().getDate().getTime());
		data.setFechaFactura(fechaFactura);
		
		if(getCmbFechaVencimiento().getDate() != null){
			data.setFechaVencimiento(new java.sql.Timestamp(getCmbFechaVencimiento().getDate().getTime()));
		}else{
			data.setFechaVencimiento(new java.sql.Timestamp(fechaPedido.getTime()));
		}
		
		data.setUsuarioId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
		
		if ( !"".equalsIgnoreCase(getTxtContacto().getText()) )
			data.setContacto(getTxtContacto().getText());
		else
			data.setContacto("");
		
		data.setDireccion(getTxtDireccion().getText());
		data.setTelefono(getTxtTelefono().getText());
		data.setValor(BigDecimal.valueOf(subTotal - descuentoTotal - descuentosVariosTotal - descuentoGlobalTotal));
		data.setDescuento(BigDecimal.valueOf(descuentoTotal));
		data.setDescuentosVarios(BigDecimal.valueOf(descuentosVariosTotal));
		data.setDescuentoGlobal(BigDecimal.valueOf(descuentoGlobalTotal));
		data.setIva(BigDecimal.valueOf(ivaTotal));
		data.setIce(BigDecimal.valueOf(0.0));
		data.setOtroImpuesto(BigDecimal.valueOf(0.0));
		data.setCosto(BigDecimal.valueOf(0.0));
		data.setObservacion(getTxtObservacion().getText());
		data.setEstado(EstadoPedido.COMPLETO.getLetra());
		data.setPorcentajeComisionAgencia(BigDecimal.valueOf(porcentajeComisionAgencia));
		
		try {
			// para setear el equipo del ejecutivo
			if (getCmbDirectorCuentas().getItemCount() > 0) {
				String equipoDirector = (String)getCmbDirectorCuentas().getSelectedItem();
				String codigoEquipo = equipoDirector.split(" - ")[0];		
				EquipoTrabajoIf equipoSeleccionado = (EquipoTrabajoIf)SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByCodigo(codigoEquipo).iterator().next();
				data.setEquipoId(equipoSeleccionado.getId());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		// Seteo datos de Negociación si la hubiera.
		if(getCbNegociacionDirecta().isSelected()){
			data.setClienteNegociacionId(clienteOficinaNegociacionIf.getId());
			data.setTipoNegociacion(TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA);
			BigDecimal porcentajeNegociacionDirecta = new BigDecimal(getTxtPorcentajeNegociacionDirecta().getText().replaceAll(",", ""));
			data.setPorcentajeNegociacionDirecta(porcentajeNegociacionDirecta);
			BigDecimal porcentajeDescuentoNegociacion = new BigDecimal(getTxtDsctoCompraPorcentaje().getText().replaceAll(",", ""));
			data.setPorcentajeDescuentoNegociacion(porcentajeDescuentoNegociacion);			
		}else if(getCbComisionPura().isSelected()){
			data.setPorcentajeNegociacionDirecta(null);
			data.setClienteNegociacionId(clienteOficinaNegociacionIf.getId());
			data.setTipoNegociacion(TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA);
			BigDecimal porcentajeDescuentoNegociacion = new BigDecimal(getTxtDsctoCompraPorcentaje().getText().replaceAll(",", ""));
			data.setPorcentajeDescuentoNegociacion(porcentajeDescuentoNegociacion);			
		}
		
		if(getTxtAutorizacionSAP().getText() != null && !getTxtAutorizacionSAP().getText().equals("")){
			data.setAutorizacionSap(getTxtAutorizacionSAP().getText());
		}
		
		return data;
	}

	private PedidoIf obtenerPedido(Long idPedido) {
		// si pedido es distinto de null es por que se mando a generar un
		// factura sin haber guardado el pedido primero
		PedidoIf pedidoIf = null;

		if (idPedido != null) {
			try {
				pedidoIf = SessionServiceLocator.getPedidoSessionService().getPedido(idPedido);
			} catch (GenericBusinessException e1) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e1.printStackTrace();
			}
		}// caso contrario el pedido es el mismo que el usuario escogio al
			// hacer find
		else
			pedidoIf = pedido;
		return pedidoIf;
	}

	public void  getSelectedObject() {
		
		getCmbTipoReferencia().removeActionListener(oActionListenerCmbTipoReferencia);
		setUpdateMode();
		cargarCombos();
		cargarComboOficina(pedido.getOficinaId());
		try {
			
			establecerPedidoEnvio(pedido.getId());
			porcentajeComisionAgencia = pedido.getPorcentajeComisionAgencia().doubleValue();
			getCmbTipoDocumento().removeActionListener(oActionListenerCmbTipoReferencia);
			getCmbTipoDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoDocumento(), pedido.getTipodocumentoId()));
			getCmbTipoDocumento().addActionListener(oActionListenerCmbTipoReferencia);
			
			Calendar calendarFechaPedido = new GregorianCalendar();
			calendarFechaPedido.setTime(pedido.getFechaPedido());
			getCmbFechaPedido().setCalendar(calendarFechaPedido);
			fechaPedido = pedido.getFechaPedido();
			
			if(pedido.getFechaVencimiento() != null){
				Calendar calendarFechaVencimiento = new GregorianCalendar();
				calendarFechaVencimiento.setTime(pedido.getFechaVencimiento());
				getCmbFechaVencimiento().setCalendar(calendarFechaVencimiento);
			}else{
				getCmbFechaVencimiento().setCalendar(null);
			}
			
			getTxtCodigo().setText(pedido.getCodigo());
			oficinaIf = SessionServiceLocator.getOficinaSessionService().getOficina(pedido.getOficinaId());
			//getTxtOficinaEmpresa().setText(Parametros.getNombreEmpresa() + " - " + oficinaIf.getNombre());
			if ( pedido.getFormapagoId() != null )
				getCmbFormaPago().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbFormaPago(), pedido.getFormapagoId()));
			else 
				getCmbFormaPago().setSelectedItem(null);
			getCmbMoneda().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbMoneda(),pedido.getMonedaId()));
			getCmbListaPrecio().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbListaPrecio(), pedido.getListaprecioId()));

			if (pedido.getDiasvalidez() != null)
				getTxtDiasValidez().setText(pedido.getDiasvalidez().toString());

			// SAP
			if(pedido.getAutorizacionSap() != null){
				getTxtAutorizacionSAP().setText(pedido.getAutorizacionSap());
			}
			
			String estadoLetra = pedido.getEstado();
			EstadoPedido estado = EstadoPedido.getEstadoOrdenTrabajoByLetra(estadoLetra);
			getCmbEstado().setSelectedItem(estado);
			/*
			 * if (ESTADO_COMPLETO.equals(pedido.getEstado()))
			 * getCmbEstado().setSelectedItem(NOMBRE_ESTADO_COMPLETO); else if
			 * (ESTADO_INCOMPLETO.equals(pedido.getEstado()))
			 * getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INCOMPLETO); else if
			 * (ESTADO_PENDIENTE.equals(pedido.getEstado()))
			 * getCmbEstado().setSelectedItem(NOMBRE_ESTADO_PENDIENTE); else if
			 * (ESTADO_ANULADO.equals(pedido.getEstado()))
			 * getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ANULADO); else if
			 * (ESTADO_COTIZACION.equals(pedido.getEstado()))
			 * getCmbEstado().setSelectedItem(NOMBRE_ESTADO_COTIZACION);
			 */
			
			getTxtObservacion().setText(pedido.getObservacion());
			
			Collection<PedidoEnvioIf> pedidosEnvio = SessionServiceLocator.getPedidoEnvioSessionService()
				.findPedidoEnvioByPedidoId(pedido.getId());
			if ( pedidosEnvio.size() > 1 ){
				throw new GenericBusinessException("Existe más de una compra por Internet con este Pedido !!");
			// } else if ( pedidosEnvio.size() == 1 &&
			// ESTADO_PENDIENTE.equals(pedido.getEstado()) ) {
			} else if ( pedidosEnvio.size() == 1 && estado == EstadoPedido.PENDIENTE ) {
				PedidoEnvioIf pedidoeEnvioIf = pedidosEnvio.iterator().next();
				String correoCliente = pedidoeEnvioIf.getCorreoCliente();
				Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByEmail(correoCliente);
				if ( oficinas.size() == 1 ){
					clienteOficinaIf = oficinas.iterator().next();
					getTxtClienteOficina().setText(clienteOficinaIf.getCodigo() + " - " + clienteOficinaIf.getDescripcion());
					clienteIf = mapaCliente.get(clienteOficinaIf.getClienteId());
					getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
					corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
					getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
					tipoIdentificacionIf = mapaTipoIdentificacion.get(pedido.getTipoidentificacionId());
					getCmbTipoIdentificacion().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoIdentificacion(),tipoIdentificacionIf.getId()));
					getTxtIdentificacion().setText(pedido.getIdentificacion());
					getTxtTelefono().setText(pedido.getTelefono());
					getTxtDireccion().setText(pedido.getDireccion());
					getTxtContacto().setText(pedido.getContacto());
					getTxtInformacionAdc().setText(clienteIf.getInformacionAdc());
					
				} else if ( oficinas.size() > 1 ) {
					SpiritAlert.createAlert("Existe más de un cliente con el mismo correo !!",SpiritAlert.WARNING);
				} else {
					llenarDatosCliente();
					getTxtDireccion().setText( pedidoeEnvioIf.getDireccionFacturacion() );
				}
			} else {
				llenarDatosCliente();
			}
			
			// if (!REFERENCIA_NINGUNO.equals(pedido.getTiporeferencia()))
			if (!TipoReferenciaPedido.NINGUNO.getLetra().equals(pedido.getTiporeferencia()))
				getBtnEscojaReferencia().setEnabled(true);
			else
				getBtnEscojaReferencia().setEnabled(false);

			String letraTipoReferencia = pedido.getTiporeferencia();
			TipoReferenciaPedido tr = TipoReferenciaPedido.getTipoReferenciaPedidoByLetra(letraTipoReferencia);
			getCmbTipoReferencia().setSelectedItem(tr);
			tipoReferencia = tr;
			/*
			 * if (REFERENCIA_PRESUPUESTO.equals(pedido.getTiporeferencia())) {
			 * getCmbTipoReferencia().setSelectedItem(NOMBRE_REFERENCIA_PRESUPUESTO);
			 * tipoReferencia = NOMBRE_REFERENCIA_PRESUPUESTO; } else if
			 * (REFERENCIA_PLAN_MEDIOS.equals(pedido.getTiporeferencia())) {
			 * getCmbTipoReferencia().setSelectedItem(NOMBRE_REFERENCIA_PLAN_MEDIOS);
			 * tipoReferencia = NOMBRE_REFERENCIA_PLAN_MEDIOS; } else if
			 * (REFERENCIA_NINGUNO.equals(pedido.getTiporeferencia())) {
			 * getCmbTipoReferencia().setSelectedItem(NOMBRE_REFERENCIA_NINGUNO);
			 * tipoReferencia = NOMBRE_REFERENCIA_NINGUNO; }
			 */

			getTxtReferencia().setText(pedido.getReferencia());
			Map parameterMap = new HashMap();
			if (pedido.getReferencia() != null)
				parameterMap.put("codigo", pedido.getReferencia());
			Long idEmpresa = Parametros.getIdEmpresa();
			
			
			// if (REFERENCIA_PRESUPUESTO.equals(pedido.getTiporeferencia())) {
			if (TipoReferenciaPedido.PRESUPUESTO == tr) {
				cleanTablaPresupuesto();
				modelPresupuestoDetalleP = (DefaultTableModel) getTblPresupuestoDetalleP().getModel();
				
				if(pedido.getReferencia().equals("M")){
					Map<Long,PresupuestoIf> presupuestosMap = new HashMap<Long,PresupuestoIf>();
					Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId());
					Iterator facturasIt = facturas.iterator();
					while(facturasIt.hasNext()){
						FacturaIf factura = (FacturaIf)facturasIt.next();
						Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByFacturaId(factura.getId());
						Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
						while(presupuestosFacturacionIt.hasNext()){
							PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
							PresupuestoDetalleIf presupuestoDetalleIf = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoFacturacionIf.getPresupuestoDetalleId());
							PresupuestoIf presupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalleIf.getPresupuestoId());
							presupuestosMap.put(presupuestoIf.getId(), presupuestoIf);
						}
					}
					String referencia = "";
					Iterator presupuestosMapIt = presupuestosMap.keySet().iterator();
					while(presupuestosMapIt.hasNext()){
						Long presupuestoId = (Long)presupuestosMapIt.next();
						presupuestoIf = presupuestosMap.get(presupuestoId);			
						
						if(referencia.equals("")){
							referencia = presupuestoIf.getCodigo();
						}else{
							referencia = referencia + ";" + presupuestoIf.getCodigo();
						}
						cargarDetallesReportePresupuesto();
					}
					getTxtEscojaReferencia().setText(referencia);
															
				}else{
					Iterator it = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByQuery(parameterMap, idEmpresa).iterator();
					if (it.hasNext()) {
						presupuestoIf = (PresupuestoIf) it.next();
						getTxtEscojaReferencia().setText(presupuestoIf.getCodigo());
					}
					cargarDetallesReportePresupuesto();
				}			
			} 
			
			// si es plan de medios el plan de debe estar como Aprobado "A", o
			// como Pedido "D"
			else if (TipoReferenciaPedido.PLAN_MEDIOS == tr) {
				
				if(pedido.getReferencia().equals("M")){
					planesMedioIf.clear();
					String referencia = "";
					Collection planesMedioFormaPago = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(pedido.getId());
					Iterator planesMedioFormaPagoIt = planesMedioFormaPago.iterator();
					while(planesMedioFormaPagoIt.hasNext()){
						PlanMedioFormaPagoIf planMedioFormaPago = (PlanMedioFormaPagoIf)planesMedioFormaPagoIt.next();
						PlanMedioIf planMedio = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(planMedioFormaPago.getPlanMedioId());
						if(referencia.equals("")){
							referencia = planMedio.getCodigo();
						}else{
							referencia = referencia + ";" + planMedio.getCodigo();
						}
						planesMedioIf.add(planMedio);
					}
					getTxtEscojaReferencia().setText(referencia);
					
				}else{
					Iterator it = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndByEstados(pedido.getReferencia(), "A", "D").iterator();
					if (it.hasNext()) {
						planMedioIf = (PlanMedioIf) it.next();
						getTxtEscojaReferencia().setText(planMedioIf.getCodigo());
					}
				}							
			} 
			else if (TipoReferenciaPedido.COMBINADO == tr) {
				
				if(pedido.getReferencia().equals("M")){
					
					//PARA PLAN DE MEDIOS
					planesMedioIf.clear();
					String referencia = "";
					Collection planesMedioFormaPago = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPedidoId(pedido.getId());
					Iterator planesMedioFormaPagoIt = planesMedioFormaPago.iterator();
					while(planesMedioFormaPagoIt.hasNext()){
						PlanMedioFormaPagoIf planMedioFormaPago = (PlanMedioFormaPagoIf)planesMedioFormaPagoIt.next();
						PlanMedioIf planMedio = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(planMedioFormaPago.getPlanMedioId());
						if(referencia.equals("")){
							referencia = planMedio.getCodigo();
						}else{
							referencia = referencia + ";" + planMedio.getCodigo();
						}
						planesMedioIf.add(planMedio);
					}
					getTxtEscojaReferencia().setText(referencia);
					
					//PARA PRESUPUESTO
					cleanTablaPresupuesto();
					modelPresupuestoDetalleP = (DefaultTableModel) getTblPresupuestoDetalleP().getModel();
					
					Map<Long,PresupuestoIf> presupuestosMap = new HashMap<Long,PresupuestoIf>();
					Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId());
					Iterator facturasIt = facturas.iterator();
					while(facturasIt.hasNext()){
						FacturaIf factura = (FacturaIf)facturasIt.next();
						Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByFacturaId(factura.getId());
						Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
						while(presupuestosFacturacionIt.hasNext()){
							PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
							PresupuestoDetalleIf presupuestoDetalleIf = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoFacturacionIf.getPresupuestoDetalleId());
							PresupuestoIf presupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalleIf.getPresupuestoId());
							presupuestosMap.put(presupuestoIf.getId(), presupuestoIf);
						}
					}
					
					Iterator presupuestosMapIt = presupuestosMap.keySet().iterator();
					while(presupuestosMapIt.hasNext()){
						Long presupuestoId = (Long)presupuestosMapIt.next();
						presupuestoIf = presupuestosMap.get(presupuestoId);			
						
						if(referencia.equals("")){
							referencia = presupuestoIf.getCodigo();
						}else{
							referencia = referencia + ";" + presupuestoIf.getCodigo();
						}
						cargarDetallesReportePresupuesto();
					}
					getTxtEscojaReferencia().setText(referencia);					
				}						
			}

			if ( pedido.getVendedorId() != null ){
				getCmbVendedor().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbVendedor(), pedido.getVendedorId()));
				
				// Selecciono el equipo y director del ejecutivo
				HashMap buscarDirectorMap = new HashMap();
				buscarDirectorMap.put("equipoId", pedido.getEquipoId());
				buscarDirectorMap.put("rol", "DCU");
				Collection directorEquipos = SessionServiceLocator.getEquipoEmpleadoSessionService().findEquipoEmpleadoByQuery(buscarDirectorMap);
				if(directorEquipos.size() > 0){
					EquipoEmpleadoIf directorEquipo = (EquipoEmpleadoIf)directorEquipos.iterator().next();
					EmpleadoIf director = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(directorEquipo.getEmpleadoId());
					EquipoTrabajoIf equipoTrabajoDirector = SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajo(directorEquipo.getEquipoId());
					getCmbDirectorCuentas().setSelectedItem(equipoTrabajoDirector.getCodigo() + " - " + director);
				}
			}else
				getCmbVendedor().setSelectedItem(null);
							
			getCmbBodega().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbBodega(), pedido.getBodegaId()));
			getCmbOrigenDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbOrigenDocumento(), pedido.getOrigendocumentoId()));

			if (pedido.getPuntoimpresionId() != null) {
				puntoImpresionIf = SessionServiceLocator.getPuntoImpresionSessionService().getPuntoImpresion(pedido.getPuntoimpresionId());
				getTxtPuntoImpresion().setText(puntoImpresionIf.getSerie());
				cajaIf = SessionServiceLocator.getCajaSessionService().getCaja(puntoImpresionIf.getCajaId());
				getCmbCaja().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbCaja(), cajaIf.getId()));
			} else if (getCmbCaja().getSelectedItem() != null) {
				cajaIf = ((CajaIf) getCmbCaja().getSelectedItem());	
			}
			
			// seteo datos de negociación si la hubiera.
			if(pedido.getTipoNegociacion() != null){
				getJpNegociacion().setVisible(true);
				getBtnBuscarClienteNegociacion().setEnabled(true);
				if(pedido.getTipoNegociacion().equals(TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA)){
					getCbNegociacionDirecta().setSelected(true);
					getTxtPorcentajeNegociacionDirecta().setEditable(true);
					getTxtPorcentajeNegociacionDirecta().setText(formatoDecimal.format(pedido.getPorcentajeNegociacionDirecta()));
					getTxtDsctoCompraPorcentaje().setEditable(true);
					getTxtDsctoCompraPorcentaje().setText(formatoDecimal.format(pedido.getPorcentajeDescuentoNegociacion()));
					clienteOficinaNegociacionIf = mapaClienteOficina.get(pedido.getClienteNegociacionId());
					getTxtClienteNegociacion().setText(clienteOficinaNegociacionIf.getCodigo() + " - " + clienteOficinaNegociacionIf.getDescripcion());
				}else if(pedido.getTipoNegociacion().equals(TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA)){
					getCbComisionPura().setSelected(true);
					getTxtDsctoCompraPorcentaje().setEditable(true);
					getTxtDsctoCompraPorcentaje().setText(formatoDecimal.format(pedido.getPorcentajeDescuentoNegociacion()));
					clienteOficinaNegociacionIf = mapaClienteOficina.get(pedido.getClienteNegociacionId());
					getTxtClienteNegociacion().setText(clienteOficinaNegociacionIf.getCodigo() + " - " + clienteOficinaNegociacionIf.getDescripcion());
				}
			}
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
		
		pedidoDetalleColeccion.clear();

		try {
			modelPedidoDetalle = (DefaultTableModel) getTblPedidoDetalle().getModel();
			Collection listaPlantillaDetalle = SessionServiceLocator.getPedidoDetalleSessionService().findPedidoDetalleByPedidoId(pedido.getId());
			Iterator it = listaPlantillaDetalle.iterator();
			getTxtPorcentajeComision().setText(String.valueOf(porcentajeComisionAgencia));
			
			while (it.hasNext()) {
				Double subTotalTemp = 0.00;
				PedidoDetalleIf pedidoDetalleIf = (PedidoDetalleIf) it.next();
				Vector<String> filaPlantillaDetalle = new Vector<String>();
				pedidoDetalleColeccion.add(pedidoDetalleIf);
				DocumentoIf documento = null;
				if (pedidoDetalleIf.getDocumentoId() != null) {
					getCmbDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocumento(), pedidoDetalleIf.getDocumentoId()));
					documento = mapaDocumento.get(pedidoDetalleIf.getDocumentoId());
				}
				
				Double cantidadBase = pedidoDetalleIf.getCantidadpedida().doubleValue();
				filaPlantillaDetalle.add(pedidoDetalleIf.getDescripcion());
				filaPlantillaDetalle.add(String.valueOf(cantidadBase.intValue()));
				filaPlantillaDetalle.add(String.valueOf(pedidoDetalleIf.getPrecioreal().doubleValue()));
				
				// calculo el detalle del pedido con la cantidad que al final
				// fue facturada
				Double cantidad = cantidadBase * pedidoDetalleIf.getPrecioreal().doubleValue();
				Double porcentajeDescuentoAgencia = 0D;
				Double porcentajeDescuentosVarios = 0D;
				Double porcentajeDescuentoGlobal = 0D;
				ProductoIf producto = mapaProductoProveedor.get(pedidoDetalleIf.getProductoId());
				GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
				if (generico.getAceptaDescuento().equals("S")) {
					if ( cantidad != 0 ) {
						porcentajeDescuentoAgencia =  (pedidoDetalleIf.getDescuento().doubleValue() * 100) / (cantidad);
						porcentajeDescuentosVarios = pedidoDetalleIf.getPorcentajeDescuentosVarios().doubleValue();
						porcentajeDescuentoGlobal =  (pedidoDetalleIf.getDescuentoGlobal().doubleValue() * 100) / (cantidad);
					}
				}
				
				getTxtDescuentoGlobalPorcentaje().setText(String.valueOf(porcentajeDescuentoGlobal));
				filaPlantillaDetalle.add(String.valueOf(porcentajeDescuentoAgencia) + " % ");
				filaPlantillaDetalle.add(String.valueOf(porcentajeDescuentoGlobal.doubleValue()) + " % ");
				
				if (pedidoDetalleIf.getIva().doubleValue() > 0D)
					filaPlantillaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
				else
					filaPlantillaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");
				
				filaPlantillaDetalle.add(String.valueOf(porcentajeDescuentosVarios) + " % ");
				
				double descuento = 0D;
				double descuentosVarios = 0D;
				double descuentoGlobal = 0D;
				
				// calculo los valores del pedido segun lo leido de los detalles
				// del pedido
				if (generico.getAceptaDescuento().equals("S")) {
					descuento =  pedidoDetalleIf.getDescuento().doubleValue();
					descuentosVarios = cantidad * (porcentajeDescuentosVarios / 100);
					descuentoGlobal =  pedidoDetalleIf.getDescuentoGlobal().doubleValue();
				}
				
				Double ivaT = pedidoDetalleIf.getIva().doubleValue();
				subTotalTemp = pedidoDetalleIf.getPrecioreal().doubleValue() * cantidadBase;
				Double comisionAgencia =  ((subTotalTemp - descuento - descuentosVarios - descuentoGlobal) * porcentajeComisionAgencia) / 100D;
				
				if (documento != null && documento.getBonificacion().equals(OPCION_NO)) {
					subTotal += subTotalTemp;
					
					if (generico.getCobraIvaCliente().equals("N"))
						totalIvaCero += subTotalTemp;
					
					this.getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					this.getTxtICEFinal().setText("0");
					this.getTxtOtroImpuestoFinal().setText("0");
					descuentoGlobalTotal += descuentoGlobal;
					descuentoTotal += descuento;
					descuentosVariosTotal += descuentosVarios;
					this.getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal + descuentoGlobalTotal)));
					this.getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));
					comisionAgenciaTotal += comisionAgencia;
					ivaTotal += ivaT;
					this.getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
					getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
					total = subTotal - descuentoTotal - descuentosVariosTotal - descuentoGlobalTotal + comisionAgenciaTotal + ivaTotal;
					this.getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
				}
				
				modelPedidoDetalle.addRow(filaPlantillaDetalle);
			}
			
			getTxtDescuentoGlobalValor().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoGlobalTotal)));
		
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}

		showUpdateMode();
		getTxtPrecio().setText("");
		getTxtPrecioReal().setText("");
		TipoReferenciaPedido trSeleccionado = (TipoReferenciaPedido) getCmbTipoReferencia().getSelectedItem();
		
		if (trSeleccionado == TipoReferenciaPedido.NINGUNO ){
			getTxtPorcentajeComision().setEditable(true);			
		}
		else{
			getTxtPorcentajeComision().setEditable(false);			
		}
		
		porcentajeComisionAgencia = 0D;
				
		if (!tipoDocumentoIf.getCodigo().equals("FCO")){
			calcularComision();			
		}
		
		getCmbTipoReferencia().addActionListener(oActionListenerCmbTipoReferencia);
		
		// //////////////////////////////PLAN MEDIOS//
		// //////////////////////////////////////////
		/*
		 * if(pedido.getTiporeferencia().equals("I")){
		 * getCmbTipoReferencia().setEnabled(false);
		 * getBtnEscojaReferencia().setEnabled(false); }
		 */
		// ////////////////////////////////////////////////////////////////////////////////////
	}
	
	private void establecerPedidoEnvio(Long pedidoId) throws GenericBusinessException{
		Collection<PedidoEnvioIf> envios = SessionServiceLocator.getPedidoEnvioSessionService().findPedidoEnvioByPedidoId(pedidoId);
		if ( envios.size() > 1 ){
			throw new GenericBusinessException("Existe mas de un envio con este Pedido !!");
		} else if ( envios.size() == 1 ){
			pedidoEnvioIf = envios.iterator().next();
			mostrarInformacionEnvio(true);
		}
			
	}

	private void llenarDatosCliente() throws GenericBusinessException {
		clienteOficinaIf = mapaClienteOficina.get(pedido.getClienteoficinaId());
		getTxtClienteOficina().setText(clienteOficinaIf.getCodigo() + " - " + clienteOficinaIf.getDescripcion());
		clienteIf = mapaCliente.get(clienteOficinaIf.getClienteId());
		getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
		
		if(clienteIf.getInformacionAdc()!=null)
			getTxtInformacionAdc().setText(clienteIf.getInformacionAdc());
		
		corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
		getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
		tipoIdentificacionIf = mapaTipoIdentificacion.get(pedido.getTipoidentificacionId());
		getCmbTipoIdentificacion().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoIdentificacion(),tipoIdentificacionIf.getId()));
		getTxtIdentificacion().setText(pedido.getIdentificacion());
		getTxtTelefono().setText(pedido.getTelefono());
		getTxtDireccion().setText(pedido.getDireccion());
		getTxtContacto().setText(pedido.getContacto());
	}

	// Listeners en la tabla de Detalle de Pedido
	MouseListener oMouseAdapterTblFacturaDetalle = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblPedidoDetalle().getModel().getRowCount() > 0) {
				if (getTblPedidoDetalle().getSelectedRow() != -1) {
					pedidoDetalle = (PedidoDetalleIf) pedidoDetalleColeccion.get(((JTable) evt.getSource()).getSelectedRow());
					if (activarVisorDetallePersonalizacion.equals("S")) {
						if (pedidoDetalle.getCodigoPersonalizacion() != null)
							itemVerPersonalizacionPedidoDetalle.setVisible(true);
						else
							itemVerPersonalizacionPedidoDetalle.setVisible(false);
					}
					
					popupMenuPedidoDetalle.show(evt.getComponent(), evt.getX(), evt.getY());
				}
			} else {
				setSelectDetail(evt);
			}
		}
	};
	
	KeyListener oKeyAdapterTblFacturaDetalle = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			setSelectDetail(evt);
		}
	};
	
	private void setSelectDetail(ComponentEvent evt) {
		if (getTblPedidoDetalle().getSelectedRow() != -1) {
			// seteo la variable global de pedido detalle con el item que
			// selecciono
			// eso me sirve para comparar cuando quiera actualizar o quiera
			// eliminar
			pedidoDetalle = (PedidoDetalleIf) pedidoDetalleColeccion.get(((JTable) evt.getSource()).getSelectedRow());
			// seteo los campos de texto pedido detalle segun el item que se
			// escogio
			purchaseDataVector = purchaseDataMap.get(getTblPedidoDetalle().getSelectedRow());
			setPedidoDetalle(pedidoDetalle);
		}
	}
	
	private void setFilaSeleccionada(int filaSeleccionada) {
		this.filaSeleccionada = filaSeleccionada;
	}
	
	private int getFilaSeleccionada() {
		return this.filaSeleccionada;
	}
	
	/*
	 * Metodo que setea los campos de texto de Detalle Pedido segun el item
	 * seleccionado de la tabla de Detalle de Pedido
	 */
	private void setPedidoDetalle(PedidoDetalleIf pedidoDetalle) {
		String tipoProducto = "";
		if (pedidoDetalle.getProductoId() != null) {
			productoIf = mapaProductoProveedor.get(pedidoDetalle.getProductoId());
			GenericoIf generico = mapaGenerico.get(productoIf.getGenericoId());
			if (productoIf.getProveedorId() != null) {
				proveedorIf = mapaClienteOficina.get(productoIf.getProveedorId());
				ClienteIf clienteProveedor = mapaCliente.get(proveedorIf.getClienteId());
				getTxtProveedor().setText(proveedorIf.getCodigo() + " - " + clienteProveedor.getNombreLegal());
				if (pedidoDetalle.getComprasReembolsoAsociadas() != null && !pedidoDetalle.getComprasReembolsoAsociadas().equals("")) {
					getTxtFacturaProveedor().setText(pedidoDetalle.getComprasReembolsoAsociadas());
				} else {
					getTxtFacturaProveedor().setText("");
				}
			}

			String codigo = (productoIf.getCodigoBarras()!=null && !productoIf.getCodigoBarras().trim().equals(""))?productoIf.getCodigoBarras():productoIf.getCodigo();
			getTxtCodigoProducto().setText(codigo);
			tipoProducto = generico.getServicio();

			if ("N".equals(tipoProducto)) {
				cargarComboLote();
				if (pedidoDetalle.getLoteId() != null)
					getCmbLote().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbLote(), pedidoDetalle.getLoteId()));
				getCmbLote().setEnabled(true);
			} else {
				getCmbLote().setSelectedItem(null);
				getCmbLote().removeAllItems();
				getCmbLote().setEnabled(false);
			}

			getTxtDescripcion().setText(pedidoDetalle.getDescripcion());
			if (generico.getLineaId() != null) {
				LineaIf lineaIf = mapaLinea.get(generico.getLineaId());
				getTxtLinea().setText(lineaIf.getCodigo() + " - " + lineaIf.getNombre());
			}
		}

		if (pedidoDetalle.getMotivodocumentoId() != null)
			getCmbMotivo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbMotivo(), pedidoDetalle.getMotivodocumentoId()));

		if (pedidoDetalle.getDocumentoId() != null){
			getCmbDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocumento(), pedidoDetalle.getDocumentoId()));
		}else{
			getCmbDocumento().setEnabled(true);
			getCmbDocumento().setSelectedIndex(-1);
		}
		// mando hacer un repaint de este combo para que cada vez que de
		// click sobre un item de la tabla este tambien se actualize segun
		// el item escogido
		getCmbMotivo().repaint();
		getCmbDocumento().repaint();
		getTxtCantidad().setText(String.valueOf(Double.valueOf(pedidoDetalle.getCantidad().doubleValue()).intValue()));
		Double cant = 0D;

		if (pedidoDetalle.getCantidadpedida() != null) {
			getTxtCantidadPedida().setText(String.valueOf(Double.valueOf(pedidoDetalle.getCantidadpedida().doubleValue()).intValue()));
			cant = pedidoDetalle.getCantidadpedida().doubleValue();
		}
		// AKI formatoDecimal.format(valorSubtotal)
		// MODIFIED 19 ABRIL
		// getTxtPrecio().setText(String.valueOf(formatoDecimal.format(pedidoDetalle.getPrecio().doubleValue())));
		// getTxtPrecioReal().setText(String.valueOf(formatoDecimal.format(pedidoDetalle.getPrecioreal().doubleValue())));
		
		// SE LE QUITO EL FORMATO DECIMAL PORQUE ESTABA REDONDEANDO VALORES
		// CON TRES DECIMALES QUE SI SON NECESARIOS
		// YA QUE VIENEN DE PRESUPUESTOS, EJEMPLO: 50 TRIPTICOS A 3.078 CADA
		// UNO, SI SE REDONDEA EL PRESUPUESTO
		// NO CUADRA CON LA FACTURA
		getTxtPrecio().setText(String.valueOf(pedidoDetalle.getPrecio().doubleValue()));
		getTxtPrecioReal().setText(String.valueOf(pedidoDetalle.getPrecioreal().doubleValue()));
		
		
		// END MODIFIED 19 ABRIL
		// getTxtPrecio().setText(String.valueOf(pedidoDetalle.getPrecio().doubleValue()));
		// getTxtPrecioReal().setText(String.valueOf(pedidoDetalle.getPrecioreal().doubleValue()));
		Double cantidad = cant * pedidoDetalle.getPrecioreal().doubleValue();
		Double descuento = 0D;
		Double descuentosVarios = 0D;
		GenericoIf generico = mapaGenerico.get(productoIf.getGenericoId());
		if (generico.getAceptaDescuento().equals("S")) {
			if ( cantidad > 0D ){
				descuento = (pedidoDetalle.getDescuento().doubleValue() * 100) / cantidad;
				descuentosVarios = pedidoDetalle.getPorcentajeDescuentosVarios().doubleValue();
			}
			
		}

		String strDescuento = formatoDecimal.format(Utilitarios.redondeoValor(descuento));
		String strDescuentosVarios = formatoDecimal.format(Utilitarios.redondeoValor(descuentosVarios));
		getTxtPorcentajeDescuentoAgencia().setText(strDescuento);
		getTxtPorcentajeDescuentosVarios().setText(strDescuentosVarios);

		TipoReferenciaPedido tr = (TipoReferenciaPedido) getCmbTipoReferencia().getSelectedItem();
		
		if ( !(TipoReferenciaPedido.NINGUNO == tr) ) {
			getBtnBuscarProducto().setEnabled(false);
			getBtnAgregarDetalle().setEnabled(false);
			getBtnEliminarDetalle().setEnabled(false);
			getTxtCantidad().setEditable(false);
			getTxtCantidadPedida().setEditable(false);
			getTxtPrecio().setEditable(false);
			getTxtPrecioReal().setEditable(false);
			getTxtPorcentajeDescuentoAgencia().setEditable(false);
			getTxtPorcentajeDescuentosVarios().setEditable(false);
			getCmbLote().setEnabled(false);
			getTxtDescuentoGlobalPorcentaje().setEditable(false);
			getTxtDescuentoGlobalValor().setEditable(false);
			getRbDescuentoGlobalPorcentaje().setEnabled(false);
			getRbDescuentoGlobalValor().setEnabled(false);
			getBtnActualizarTotales().setEnabled(false);
		}
	}
	
	private void cargarComboLote() {
		try {
			List lotes = (List)SessionServiceLocator.getLoteSessionService().findLoteByProductoId(productoIf.getId());
			refreshCombo(getCmbLote(),lotes);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void inicializarPanelUpdateMode() {
		this.getTxtCodigo().setEditable(false);
		//this.getTxtOficinaEmpresa().setEnabled(true);
		this.getTxtDiasValidez().setEnabled(true);
		this.getTxtObservacion().setEnabled(true);

		if (clienteIf != null) {
			if (OPCION_SI.equals(clienteIf.getUsuariofinal())) {
				getCmbTipoIdentificacion().setEnabled(true);
				getTxtIdentificacion().setEnabled(true);
				getTxtIdentificacion().setEditable(true);
			} else {
				getCmbTipoIdentificacion().setEnabled(false);
				getTxtIdentificacion().setEnabled(false);
				getTxtIdentificacion().setEditable(false);
			}
		}

		this.getTxtTelefono().setEnabled(true);
		this.getTxtContacto().setEnabled(true);
		this.getTxtDireccion().setEnabled(true);
		this.getTxtReferencia().setEnabled(true);
		this.getTxtPuntoImpresion().setEnabled(true);
		this.getTxtLinea().setEnabled(true);
		this.getTxtDescripcion().setEnabled(true);
		this.getTxtCantidadPedida().setEnabled(true);
		this.getTxtCantidad().setEnabled(true);
		this.getTxtPrecio().setEnabled(true);
		this.getTxtPrecioReal().setEnabled(true);
		this.getTxtPorcentajeDescuentoAgencia().setEnabled(true);
		this.getTxtPorcentajeDescuentosVarios().setEnabled(true);
		this.getTxtValorFinal().setEnabled(true);
		this.getTxtDescuentoFinal().setEnabled(true);
		this.getTxtDescuentosVariosTotal().setEnabled(true);
		this.getTxtIVAFinal().setEnabled(true);
		this.getTxtICEFinal().setEnabled(true);
		this.getTxtPorcentajeComision().setEditable(false);
		this.getTxtValorComision().setEditable(false);
		this.getTxtOtroImpuestoFinal().setEnabled(true);
		this.getTxtTotalFinal().setEnabled(true);
		this.getCmbTipoDocumento().setEnabled(true);
		this.getCmbFechaPedido().setEnabled(true);
		this.getCmbFormaPago().setEnabled(true);
		this.getCmbMoneda().setEnabled(true);
		this.getCmbListaPrecio().setEnabled(true);
		this.getCmbCaja().setEnabled(false);
		this.getCmbEstado().setEnabled(true);
		this.getCmbTipoReferencia().setEnabled(true);
		this.getCmbVendedor().setEnabled(true);
		this.getCmbBodega().setEnabled(true);
		ParametroEmpresaIf fundacionRequerida = buscarParametroActivarDonacion();
		
		if (fundacionRequerida != null && fundacionRequerida.getValor().equals("S")) {
			this.getLblFundacion().setVisible(true);
			this.getCmbFundacion().setVisible(true);
			this.getCmbFundacion().setEnabled(true);
		} else {
			this.getLblFundacion().setVisible(false);
			this.getCmbFundacion().setVisible(false);
		}
		
		this.getCmbOrigenDocumento().setEnabled(true);
		this.getBtnBuscarCorporacion().setEnabled(true);
		this.getBtnBuscarCliente().setEnabled(true);
		this.getBtnBuscarClienteOficina().setEnabled(true);
		this.getCmbDocumento().setEnabled(true);
		this.getCmbMotivo().setEnabled(true);
		this.getTxtEscojaReferencia().setEditable(false);
	}

	public void agregarDetalle() {
		try {
			modelPedidoDetalle = (DefaultTableModel) getTblPedidoDetalle().getModel();
			Vector<String> filaDetalle = new Vector<String>();

			if (validateFieldsDetalle()) {
				Double cantidadPedida = 0D, precioReal = 0D, subtotal = 0D, iva = 0D, 
				porcentajeDescuento = 0D, porcentajeDescuentosVarios = 0D, descuento = 0D, descuentosVarios = 0D;
				PedidoDetalleData subData = new PedidoDetalleData();
				
				filaDetalle.add(getTxtDescripcion().getText());
				filaDetalle.add(String.valueOf(Double.valueOf(getTxtCantidadPedida().getText()).intValue()));
				filaDetalle.add(String.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecioReal().getText()))));
				filaDetalle.add(getTxtPorcentajeDescuentoAgencia().getText() + " % ");
				filaDetalle.add("0 %");
				
				GenericoIf generico = mapaGenerico.get(productoIf.getGenericoId());
				cantidadPedida = Double.valueOf(this.getTxtCantidadPedida().getText());
				precioReal = Double.valueOf(Utilitarios.removeDecimalFormat(this.getTxtPrecioReal().getText()));
				subtotal =  (cantidadPedida * precioReal);
				
				if (generico.getAceptaDescuento().equals("S")) {
					porcentajeDescuento = Double.valueOf(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentoAgencia().getText()));
					porcentajeDescuentosVarios = Double.valueOf(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentosVarios().getText()));
					descuento =  subtotal * (porcentajeDescuento / 100);
					descuentosVarios = subtotal * (porcentajeDescuentosVarios / 100);
				}
				
				if (OPCION_NO.equals(tipoDocumentoIf.getReembolso()) && generico.getCobraIva().equals("S")) {
					iva = (subtotal - descuento - descuentosVarios) * IVA;
					if (((DocumentoIf) getCmbDocumento().getSelectedItem()).getId() != null && !((DocumentoIf) getCmbDocumento().getSelectedItem()).getBonificacion().equals(OPCION_SI))
						ivaTotal += iva;
					filaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
				} else {
					iva = 0D;
					filaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");
				}

				filaDetalle.add(getTxtPorcentajeDescuentosVarios().getText() + " % ");
				
				subData.setDocumentoId(((DocumentoIf) this.getCmbDocumento().getSelectedItem()).getId());

				if (this.getCmbMotivo().getSelectedItem() != null)
					subData.setMotivodocumentoId(((MotivoDocumentoIf) this.getCmbMotivo().getSelectedItem()).getId());

				subData.setProductoId(productoIf.getId());
				subData.setCantidadpedida(BigDecimal.valueOf(Double.parseDouble(this.getTxtCantidadPedida().getText())));

				if (this.getTxtCantidad().getText().equals(""))
					subData.setCantidad(BigDecimal.valueOf(0.0));
				else
					subData.setCantidad(BigDecimal.valueOf(Double.parseDouble(this.getTxtCantidad().getText())));

				subData.setDescripcion(getTxtDescripcion().getText());
				subData.setPrecio(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecio().getText()))));
				subData.setPrecioreal(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecioReal().getText()))));
				subData.setValor(BigDecimal.valueOf(subtotal));
				subData.setIva(BigDecimal.valueOf(iva));
				subData.setIce(BigDecimal.valueOf(0.0));
				subData.setOtroimpuesto(BigDecimal.valueOf(0.0));
				subData.setDescuento(BigDecimal.valueOf(descuento));
				subData.setPorcentajeDescuentosVarios(BigDecimal.valueOf(porcentajeDescuentosVarios));
				subData.setDescuentoGlobal(BigDecimal.ZERO);
				subData.setComprasReembolsoAsociadas(getTxtFacturaProveedor().getText());
				String tipoProducto = generico.getServicio();

				if (getCmbLote().getSelectedItem() != null)
					subData.setLoteId(((LoteIf) this.getCmbLote().getSelectedItem()).getId());

				pedidoDetalleColeccion.add(subData);
				modelPedidoDetalle.addRow(filaDetalle);
				
				if (((DocumentoIf) getCmbDocumento().getSelectedItem()).getId() != null && !((DocumentoIf) getCmbDocumento().getSelectedItem()).getBonificacion().equals(OPCION_SI)) {
					boolean aceptaDescuento = (generico.getAceptaDescuento().equals("S"))?true:false; 
					calcularDetalleSinDescuentoGlobal(aceptaDescuento, (generico!=null && generico.getCobraIvaCliente().equals("S"))?true:false);
				}
				
				cleanDetalle();
				actualizarTotales(pedidoDetalleColeccion, true);
				purchaseDataMap.put(getTblPedidoDetalle().getRowCount(), (Vector<PurchaseData>) DeepCopy.copy(purchaseDataVector));
				purchaseDataVector = new Vector<PurchaseData>();
			}
			
			getTblPedidoDetalle().validate();
			getTblPedidoDetalle().repaint();
			
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se pudo ingresar el Detalle del Pedido a la lista !!!", SpiritAlert.ERROR);
		}
	}

	public void calcularDetalleConDescuentoGlobal(boolean aceptaDescuento, boolean cobraIvaCliente) {
		Double cantidadPedida = 0D, precioReal = 0D, subtotal = 0D, porcentajeDescuento = 0D, porcentajeDescuentoGlobal = 0D;
		Double descuento = 0D, descuentoGlobal = 0D, comisionAgencia = 0D, descuentosVarios = 0D, porcentajeDescuentosVarios = 0D;
		
		cantidadPedida = Double.parseDouble(getTxtCantidadPedida().getText());
		precioReal = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecioReal().getText()));
		subtotal =  cantidadPedida * precioReal;
		this.subTotal += subtotal;
		
		if (!cobraIvaCliente)
			this.totalIvaCero += subtotal;
		
		if (tipoDocumentoIf.getReembolso().equals("N")) {
			getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(this.subTotal)));
			getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(this.ivaTotal)));
		} else {
			getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(this.subTotal)));
			getTxtIVAFinal().setText("0");
		}
			
		if (aceptaDescuento) {
			if (!"".equals(getTxtPorcentajeDescuentoAgencia().getText())){
				porcentajeDescuento =  Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoAgencia().getText()));
			}
			descuento =  subtotal * (porcentajeDescuento / 100);
			
			if (!"".equals(getTxtPorcentajeDescuentosVarios().getText())){
				porcentajeDescuentosVarios =  Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentosVarios().getText()));
			}
			descuentosVarios =  subtotal * (porcentajeDescuentosVarios / 100);
			
			if (!"".equals(getTxtDescuentoGlobalPorcentaje())){
				porcentajeDescuentoGlobal = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtDescuentoGlobalPorcentaje().getText()));
			}
			descuentoGlobal =  subtotal * (porcentajeDescuentoGlobal/100);
		}
		
		comisionAgencia = (subtotal - descuento - descuentosVarios - descuentoGlobal) * (this.porcentajeComisionAgencia / 100D);
		
		this.comisionAgenciaTotal += comisionAgencia;
		this.descuentoTotal += descuento;
		this.descuentosVariosTotal += descuentosVarios;
		this.descuentoGlobalTotal += descuentoGlobal;
		getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(this.descuentoTotal + this.descuentoGlobalTotal)));
		getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(this.descuentosVariosTotal)));
		getTxtICEFinal().setText("0");
		getTxtOtroImpuestoFinal().setText("0");
		this.total = this.subTotal - this.descuentoTotal - this.descuentosVariosTotal - this.descuentoGlobalTotal + this.comisionAgenciaTotal;
		
		if (tipoDocumentoIf.getReembolso().equals("N")){
			this.total += this.ivaTotal;
		}
		
		getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(this.comisionAgenciaTotal)));
		getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(this.total)));
	}
	
	public void calcularDetalleSinDescuentoGlobal(boolean aceptaDescuento, boolean cobraIvaCliente) {
		Double cantidadPedida = 0D, precioReal = 0D, subtotal= 0D, porcentajeDescuento = 0D;
		Double descuento = 0D, comisionAgencia = 0D, descuentosVarios = 0D, porcentajeDescuentosVarios = 0D;
		cantidadPedida = Double.parseDouble(getTxtCantidadPedida().getText());
		precioReal = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecioReal().getText()));
		subtotal = cantidadPedida * precioReal;
		this.subTotal += subtotal;
		
		if (!cobraIvaCliente)
			this.totalIvaCero += subtotal;
		
		if (tipoDocumentoIf.getReembolso().equals("N")) {
			getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(this.subTotal)));
			getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(this.ivaTotal)));
		} else {
			getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(this.subTotal)));
			getTxtIVAFinal().setText("0");
		}
		
		if (aceptaDescuento) {
			if (!"".equals(getTxtPorcentajeDescuentoAgencia().getText()))
				porcentajeDescuento = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoAgencia().getText()));
			
			descuento =  subtotal * (porcentajeDescuento/100D);
			
			if (!"".equals(getTxtPorcentajeDescuentosVarios().getText()))
				porcentajeDescuentosVarios = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentosVarios().getText()));
			
			descuentosVarios =  subtotal * (porcentajeDescuentosVarios/100D);
		}

		comisionAgencia = (subtotal - descuento - descuentosVarios) * (this.porcentajeComisionAgencia / 100D);
			
		this.comisionAgenciaTotal += comisionAgencia;
		this.descuentoTotal += descuento;
		this.descuentosVariosTotal += descuentosVarios;
		getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(this.descuentoTotal)));
		getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(this.descuentosVariosTotal)));
		getTxtICEFinal().setText("0");
		getTxtOtroImpuestoFinal().setText("0");
		this.total = this.subTotal - this.descuentoTotal - this.descuentosVariosTotal + this.comisionAgenciaTotal;
		
		if (tipoDocumentoIf.getReembolso().equals("N"))
			this.total += this.ivaTotal;
		
		getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(this.comisionAgenciaTotal)));
		getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(this.total)));
	}

	ActionListener oActionListenerEliminarPedidoDetalle = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			eliminarDetallePedido();
		}
	};
	
	ActionListener oActionListenerVerDetallePersonalizacion = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			// CODIGO PARA LANZAR NAVEGADOR WEB
			if (getTblPedidoDetalle().getSelectedRow() != -1) {
				int filaSeleccionada = getTblPedidoDetalle().getSelectedRow();
				PedidoDetalleIf pedidoDetalleIf = (PedidoDetalleIf) pedidoDetalleColeccion.get(filaSeleccionada);
				String codigo = pedidoDetalleIf.getCodigoPersonalizacion();
				try {
					if (codigo != null)
						// new
						// SpiritBrowser("http://www.khomo.com/visor_personalizacion/?cod="
						// + java.net.URLEncoder.encode(codigo, "UTF-8"));
						ApplicationLauncher.launchURL(URLVisor + java.net.URLEncoder.encode(codigo, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				}
			}
		}
	};

	private final class BuscarProductoListener implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			String tipoProducto = "";
			Long idReferencia = 0L;

			if (presupuestoIf != null) {
				tipoProducto = CODIGO_TIPO_PRODUCTO_PRODUCCION;
				idReferencia = presupuestoIf.getId();
			} else if (planMedioIf != null) {
				tipoProducto = CODIGO_TIPO_PRODUCTO_MEDIOS;
				idReferencia = planMedioIf.getId();
			}
			String mmpg = "G";
			ProductoCriteria productoCriteria = new ProductoCriteria("Producto", idReferencia, tipoProducto, BUSCAR_PRODUCTO_POR_REFERENCIA, "A", true, mmpg); 
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.add(100);
			anchoColumnas.add(200);
			anchoColumnas.add(100);
			anchoColumnas.add(150);
			anchoColumnas.add(150);
			anchoColumnas.add(150);
			JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), productoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
			if ( popupFinder.getElementoSeleccionado() != null ){
				boolean productoRepetido = false;
				productoIf = (ProductoIf) popupFinder.getElementoSeleccionado();
				if (pedidoDetalleColeccion.size() > 0) {
					for (int i = 0; i < pedidoDetalleColeccion.size(); i++) {
						if (productoIf.getId().compareTo(((PedidoDetalleIf) pedidoDetalleColeccion.get(i)).getProductoId()) == 0)
							productoRepetido = true;
					}
				}
		
				if (!productoRepetido) {
					tipoProducto = "";
					try {
						String codigo = (productoIf.getCodigoBarras()!=null && !productoIf.getCodigoBarras().trim().equals(""))?productoIf.getCodigoBarras():productoIf.getCodigo();
						getTxtCodigoProducto().setText(codigo);
						getTxtDescripcion().setText("");
						GenericoIf generico = mapaGenerico.get(productoIf.getGenericoId());
						// MODELO
						Long modeloId = productoIf.getModeloId();
						ModeloIf modelo = (modeloId!=null)?(ModeloIf) modelosMap.get(modeloId):null;
						if (modelo != null)
							getTxtDescripcion().setText(modelo.getNombre());
						// COLOR
						Long colorId = productoIf.getColorId();
						ColorIf color = (colorId!=null)?(ColorIf) coloresMap.get(colorId):null;
						if (color != null)
							getTxtDescripcion().setText(getTxtDescripcion().getText().toString() + " - " + color.getNombre());
						// TALLA
						Long presentacionId = productoIf.getPresentacionId();
						PresentacionIf presentacion = (presentacionId!=null)?(PresentacionIf) presentacionesMap.get(presentacionId):null;
						if (presentacion != null)
							getTxtDescripcion().setText(getTxtDescripcion().getText().toString() + " (" + presentacion.getNombre() + ")");
						if (generico.getMediosProduccion().equals("O") || getTxtDescripcion().getText().equals(""))
							getTxtDescripcion().setText(generico.getNombre());
		
						if (OPCION_SI.equals(generico.getCambioDescripcion())) {
							getTxtDescripcion().setEditable(true);
						} else {
							getTxtDescripcion().setEditable(false);
						}
		
						if (productoIf.getProveedorId() != null) {
							ClienteOficinaIf proveedor = (ClienteOficinaIf) mapaClienteOficina.get(productoIf.getProveedorId());
							proveedorIf = proveedor;
							getTxtProveedor().setText(proveedor.getDescripcion());
						} else
							// getTxtProveedor().setText("NO TIENE PROVEEDOR");
							getTxtProveedor().setText("");
						
						if (generico.getLineaId() != null) {
							LineaIf lineaIf = mapaLinea.get(generico.getLineaId());
							getTxtLinea().setText(lineaIf.getCodigo()+ " - " + lineaIf.getNombre());
						}
						tipoProducto = SessionServiceLocator.getTipoProductoSessionService().getTipoProducto(generico.getTipoproductoId()).getCodigo();
						ListaPrecioIf listaPrecioIf = (ListaPrecioIf) getCmbListaPrecio().getSelectedItem();
						PrecioIf precioIf = null;
						try {
							precioIf = (PrecioIf) FacturacionFinder.findPrecio(listaPrecioIf.getId(),productoIf.getId()).iterator().next();
		
						} catch (java.util.NoSuchElementException e) {
							// SpiritAlert.createAlert("La lista de precio
							// seleccionada en el pedido no contiene información
							// para el producto seleccionado",
							// SpiritAlert.WARNING);
							precioIf = (PrecioIf) new PrecioData();
							precioIf.setPrecio(BigDecimal.ZERO);
						}
		
						getTxtCantidadPedida().setEnabled(true);
						getTxtPrecio().setText(String.valueOf(precioIf.getPrecio()));
						getTxtPrecioReal().setText(String.valueOf(precioIf.getPrecio()));
						inicializarTextFieldsPrecio();
						getTxtPorcentajeDescuentoAgencia().setText("0");
						getTxtPorcentajeDescuentosVarios().setText("0");
						java.util.Date today = new java.util.Date();
		
						if (generico.getServicio().equals(OPCION_NO)) {
							getCmbLote().setEnabled(true);
							// Crear query para consultar lote por producto,
							// fecha_elaboracion, fecha_vencimiento y estado
							// activo
							List lotes = (List)SessionServiceLocator.getLoteSessionService().findLoteByProductoId(productoIf.getId());
							refreshCombo(getCmbLote(), lotes);
							if ( lotes.size() == 0 )
								getCmbLote().setEnabled(false);
						} else {
							getCmbLote().setSelectedItem(null);
							getCmbLote().setEnabled(false);
						}
						
						getTxtCantidad().selectAll();
						getTxtCantidad().grabFocus();
						getTxtCantidad().validate();
						getTxtCantidad().repaint();
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				} else {
					SpiritAlert.createAlert("El producto seleccionado ya ha sido agregado previamente al detalle!!!", SpiritAlert.WARNING);
				}
			}
		}
	}

	private final class AgregarDetalleListener implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			agregarDetallePedido();
			porcentajeComisionAgencia = 0D;
			calcularComision();
		}
	}
	
	private final class ActualizarDetalleListener implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			setFilaSeleccionada(getTblPedidoDetalle().getSelectedRow());
			actualizarDetallePedido(false);
			porcentajeComisionAgencia = 0D;
			calcularComision();
		}
	}
	
	private void agregarDetallePedido() {
		// Si no ha escogido un producto, genero el mensaje de aviso, puede
		// suceder que el campo de producto
		// código o descripcion esten llenos pero no pertenezcan a un producto
		// válido que este en la base
		// por lo tanto el objeto de producto esta vacio
		if (productoIf == null) {
			SpiritAlert.createAlert("Por favor ingrese un producto válido al detalle !!!",SpiritAlert.WARNING);
			getTxtCodigoProducto().setText("");
			getTxtDescripcion().setText("");
		} else {
			agregarDetalle();
		}
	}

	private void cleanDetalle() {
		// seteo los campos de texto y el objeto de producto a null para que
		// sean llenados otra vez por el usuario
		getTxtLinea().setText("");
		getTxtProveedor().setText("");
		getTxtCantidadPedida().setText("0");
		getTxtCantidad().setText("0");
		getTxtPrecio().setText("");
		getTxtPrecioReal().setText("");
		// seteo con el monto de descuento permitido al usuario según el tipo de
		// pago que escogio
		getTxtPorcentajeDescuentoAgencia().setText(montoDescuento.toString());
		getTxtPorcentajeDescuentosVarios().setText("0");
		getTxtCodigoProducto().setText("");
		getTxtDescripcion().setText("");
		getTxtProveedor().setText("");
		getTxtFacturaProveedor().setText("");
		getTxtCodigoProducto().grabFocus();
		getTxtDescripcion().grabFocus();
		// seteo el objeto de producto a null, para que se escoja uno nuevo
		productoIf = null;
	}

	private void actualizarDetallePedido(boolean calcularDescuentoGlobal) {
		if (getTblPedidoDetalle().getSelectedRow() != -1) {
			int filaSeleccionada = getTblPedidoDetalle().getSelectedRow();
			PedidoDetalleIf pedidoDetalleIf = (PedidoDetalleIf) pedidoDetalleColeccion.get(filaSeleccionada);
			DocumentoIf documento = null;
			
			if (productoIf != null) {
				cargandoDetallesReferencia = true;
				if (validateFieldsDetalle()) {
					if (pedidoDetalleIf.getDocumentoId() != null)
						documento = mapaDocumento.get(pedidoDetalleIf.getDocumentoId());
					
					// ---------------ESTA ES LA SECCION CONFLICTIVA: NO
					// BORRAR------------------------
					if (documento == null || (documento != null && documento.getBonificacion().equals(OPCION_NO)))
					// if (documento != null &&
					// documento.getBonificacion().equals(OPCION_NO))
						reversarTotales(pedidoDetalleIf);
					// --------------------------------------------------------------------------------
					
					Double subtotal = 0D, descuento = 0D, descuentosVarios = 0D, descuentoGlobal = 0D, iva = 0D, comisionAgencia = 0D;
					String strCantidadPedida = getTxtCantidadPedida().getText();
					String strCantidad = getTxtCantidad().getText();
					String strPrecio = Utilitarios.removeDecimalFormat(getTxtPrecio().getText());
					String strPrecioReal = Utilitarios.removeDecimalFormat(getTxtPrecioReal().getText());
					String strDescuento = getTxtPorcentajeDescuentoAgencia().getText();
					String strDescuentosVarios = getTxtPorcentajeDescuentosVarios().getText();
					String strDescuentoGlobalPorcentaje = "0";
					GenericoIf generico = mapaGenerico.get(productoIf.getGenericoId());
					
					if (!getTxtDescuentoGlobalPorcentaje().getText().equals("") && generico.getAceptaDescuento().equals("S"))
						strDescuentoGlobalPorcentaje = Utilitarios.removeDecimalFormat(getTxtDescuentoGlobalPorcentaje().getText());
					
					modelPedidoDetalle.setValueAt(getTxtDescripcion().getText(), getTblPedidoDetalle().getSelectedRow(), 0);
					modelPedidoDetalle.setValueAt(strCantidadPedida, getTblPedidoDetalle().getSelectedRow(), 1);
					modelPedidoDetalle.setValueAt(String.valueOf(Double.parseDouble(strPrecioReal)), getTblPedidoDetalle().getSelectedRow(), 2);
					strDescuento = formatoDecimal.format( Double.valueOf(strDescuento));
					strDescuentosVarios = formatoDecimal.format( Double.valueOf(strDescuentosVarios));
					modelPedidoDetalle.setValueAt(String.valueOf(Double.valueOf(strDescuento)) + " % ", getTblPedidoDetalle().getSelectedRow(), 3);
					
					if (calcularDescuentoGlobal)
						modelPedidoDetalle.setValueAt(String.valueOf(Double.valueOf(strDescuentoGlobalPorcentaje)) + " % ", getTblPedidoDetalle().getSelectedRow(), 4);
					else
						modelPedidoDetalle.setValueAt("0 % ", getTblPedidoDetalle().getSelectedRow(), 4);
					
					subtotal = Double.valueOf(strCantidadPedida) * Double.valueOf(strPrecioReal);
					descuento =  subtotal * (Double.valueOf(strDescuento) / 100);
					descuentosVarios =  subtotal * (Double.valueOf(strDescuentosVarios) / 100);
					
					if (calcularDescuentoGlobal) {
						subTotal += subtotal;
						
						if (generico.getCobraIvaCliente().equals("N"))
							totalIvaCero += subtotal;
						
						descuentoGlobal =  (subtotal * (Double.valueOf(strDescuentoGlobalPorcentaje) / 100));
					}
					
					comisionAgencia =  ((subtotal - descuento - descuentosVarios - descuentoGlobal) * porcentajeComisionAgencia) / 100D;
											
					if (generico.getCobraIvaCliente().equals("S"))
						iva = (subtotal - descuento - descuentosVarios - descuentoGlobal + comisionAgencia) * IVA;
											
					ivaTotal = ivaTotal + iva;
					
					if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N") && generico.getCobraIva().equals("S")) {
						if (cargandoDetallesReferencia || (((DocumentoIf) getCmbDocumento().getSelectedItem()).getId() != null && !((DocumentoIf) getCmbDocumento().getSelectedItem()).getBonificacion().equals(OPCION_SI)))
							modelPedidoDetalle.setValueAt(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %", getTblPedidoDetalle().getSelectedRow(), 5);
					
					} else {
						modelPedidoDetalle.setValueAt(String.valueOf(IVA_CERO.intValue()) + " %", getTblPedidoDetalle().getSelectedRow(), 5);
					}
					
					modelPedidoDetalle.setValueAt(String.valueOf(Double.valueOf(strDescuentosVarios)) + " % ", getTblPedidoDetalle().getSelectedRow(), 6);
											
					PedidoDetalleData subData = new PedidoDetalleData();
					
					if (pedidoDetalleIf.getId() != null)
						subData.setId(pedidoDetalleIf.getId());
					
					subData.setProductoId(productoIf.getId());
					subData.setCantidadpedida(BigDecimal.valueOf(Double.parseDouble(strCantidadPedida)));
					subData.setCantidad(BigDecimal.valueOf(Double.parseDouble(strCantidad)));
					subData.setPrecio(BigDecimal.valueOf(Double.parseDouble(strPrecio))); 
					subData.setPrecioreal(BigDecimal.valueOf(Double.parseDouble(strPrecioReal)));
					subData.setValor(BigDecimal.valueOf(subtotal));
					
					if (tipoDocumentoIf.getReembolso().equals("N"))
						subData.setIva(BigDecimal.valueOf(iva));
					else
						subData.setIva(BigDecimal.valueOf(0D));
					
					subData.setIce(BigDecimal.valueOf(0.0));
					subData.setOtroimpuesto(BigDecimal.valueOf(0.0));
					subData.setDescuento(BigDecimal.valueOf(descuento));
					subData.setPorcentajeDescuentosVarios(BigDecimal.valueOf(Double.valueOf(strDescuentosVarios)));
					subData.setDescuentoGlobal(BigDecimal.valueOf(descuentoGlobal));
					
					if(getTxtDescripcion().getText().equals("")){
						subData.setDescripcion("\n");
					}else{
						subData.setDescripcion(getTxtDescripcion().getText());
					}						
					
					if (!cargandoDetallesReferencia || getCmbDocumento().getSelectedItem() != null)
						subData.setDocumentoId(((DocumentoIf) getCmbDocumento().getSelectedItem()).getId());
					
					subData.setComprasReembolsoAsociadas(getTxtFacturaProveedor().getText());
					
					if (getCmbMotivo().getSelectedItem() != null && tipoDocumentoIf.getExigemotivo().equals(OPCION_SI))
						subData.setMotivodocumentoId(((MotivoDocumentoIf) getCmbMotivo().getSelectedItem()).getId());
					
					if (getCmbLote().getSelectedItem() != null)
						subData.setLoteId(((LoteIf) getCmbLote().getSelectedItem()).getId());
					else
						subData.setLoteId(null);
					
					subData.setCodigoPersonalizacion(pedidoDetalleIf.getCodigoPersonalizacion());
					
					pedidoDetalleColeccion.set(filaSeleccionada, subData);
					
					boolean aceptaDescuento = (generico.getAceptaDescuento().equals("S"))?true:false;
					
					if (calcularDescuentoGlobal)
						calcularDetalleConDescuentoGlobal(aceptaDescuento, (generico!=null && generico.getCobraIvaCliente().equals("S"))?true:false);
					else
						calcularDetalleSinDescuentoGlobal(aceptaDescuento, (generico!=null && generico.getCobraIvaCliente().equals("S"))?true:false);
					subData.setComprasReembolsoAsociadas(getTxtFacturaProveedor().getText());
					getTxtCodigoProducto().setText("");
					getTxtDescripcion().setText("");
					getTxtCantidadPedida().setText("");
					getTxtCantidad().setText("");
					getTxtPrecio().setText("");
					getTxtProveedor().setText("");
					getTxtFacturaProveedor().setText("");
					getTxtPrecioReal().setText("");
					getTxtLinea().setText("");
					getTxtPorcentajeDescuentoAgencia().setText("");
					getTxtPorcentajeDescuentosVarios().setText("");
					productoIf = null;
				}
				cargandoDetallesReferencia = false;
				purchaseDataMap.put(getFilaSeleccionada(), (Vector<PurchaseData>) DeepCopy.copy(purchaseDataVector));
				purchaseDataVector = new Vector<PurchaseData>();
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar el detalle a actualizar !!!", SpiritAlert.WARNING);
		}
		
		getTblPedidoDetalle().validate();
		getTblPedidoDetalle().repaint();
	}
	
	private void eliminarDetallePedido() {
		if (getTblPedidoDetalle().getSelectedRow() != -1) {
			int filaSeleccionada = getTblPedidoDetalle().getSelectedRow();
			PedidoDetalleIf pedidoDetalleIf = (PedidoDetalleIf) pedidoDetalleColeccion.get(filaSeleccionada);
			DocumentoIf documento = null;
			
			if (pedidoDetalleIf.getDocumentoId() != null)
				documento = mapaDocumento.get(pedidoDetalleIf.getDocumentoId());
			
			if (documento == null || (documento != null && documento.getBonificacion().equals(OPCION_NO)))
				reversarTotales(pedidoDetalleIf);
			
			PedidoDetalleIf detalleRemovido = (PedidoDetalleIf) pedidoDetalleColeccion.get(filaSeleccionada); 
			
			if (detalleRemovido.getId() != null)
				pedidoDetalleEliminadas.add(detalleRemovido);
			
			pedidoDetalleColeccion.remove(filaSeleccionada);
			modelPedidoDetalle.removeRow(filaSeleccionada);
			
			getTxtCodigoProducto().setText("");
			getTxtDescripcion().setText("");
			getTxtCantidadPedida().setText("");
			getTxtCantidad().setText("");
			getTxtPrecio().setText("");
			getTxtLinea().setText("");
			getTxtPrecioReal().setText("");
			getTxtProveedor().setText("");
			getTxtPorcentajeDescuentoAgencia().setText("");
			getTxtPorcentajeDescuentosVarios().setText("");
			productoIf = null;
			purchaseDataMap.remove(getFilaSeleccionada());
			purchaseDataVector = new Vector<PurchaseData>();
		} else
			SpiritAlert.createAlert("Debe seleccionar el detalle a eliminar !!!", SpiritAlert.WARNING);
		
		getTblPedidoDetalle().validate();
		getTblPedidoDetalle().repaint();
	}
	
	private void reversarTotales(PedidoDetalleIf pedidoDetalle) {
		TipoDocumentoIf tipoDocumento = (getCmbTipoDocumento().getSelectedItem() != null)?(TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem():null;
		
		ProductoIf producto = mapaProductoProveedor.get(pedidoDetalle.getProductoId());
		GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
		Double precioReal = pedidoDetalle.getPrecioreal().doubleValue();
		Double cantidadPedida = pedidoDetalle.getCantidadpedida().doubleValue();
		Double descuento =  pedidoDetalle.getDescuento().doubleValue();
		double porcentajeDescuentosVarios = pedidoDetalle.getPorcentajeDescuentosVarios().doubleValue() / 100;
		double descuentosVarios = (precioReal * cantidadPedida) * porcentajeDescuentosVarios;
		Double descuentoGlobal =  pedidoDetalle.getDescuentoGlobal().doubleValue();
		Double iva = (pedidoDetalle.getIva() != null)?pedidoDetalle.getIva().doubleValue():0D;
		Double comisionAgencia =  (((precioReal * cantidadPedida) - descuento - descuentosVarios - descuentoGlobal) * porcentajeComisionAgencia) / 100D;
		subTotal = subTotal - (precioReal * cantidadPedida);
		
		if (generico.getCobraIvaCliente().equals("N"))
			totalIvaCero = totalIvaCero - (precioReal * cantidadPedida);
		
		descuentoTotal -= descuento;
		descuentosVariosTotal -= descuentosVarios;
		descuentoGlobalTotal -= descuentoGlobal;
		comisionAgenciaTotal -= comisionAgencia;
		
		descuentoTotal = descuentoTotal >= 0 ? descuentoTotal : 0;
		descuentosVariosTotal = descuentosVariosTotal >= 0 ? descuentosVariosTotal : 0;
		descuentoGlobalTotal = descuentoGlobalTotal >= 0 ? descuentoGlobalTotal : 0;
		comisionAgenciaTotal = comisionAgenciaTotal >= 0 ? comisionAgenciaTotal : 0;
		
		ivaTotal = ivaTotal - iva;
		total = subTotal - descuentoTotal - descuentosVariosTotal - descuentoGlobalTotal + comisionAgenciaTotal;
		
		if (tipoDocumento == null || tipoDocumento.getReembolso().equals("N")) {
			total += ivaTotal;
			getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
			getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
			getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
		} else {
			getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
			getTxtIVAFinal().setText("0");
			getTxtValorComision().setText("0");				
		}
		
		getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal + descuentoGlobalTotal)));
		getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));
		getTxtICEFinal().setText("0");
		getTxtOtroImpuestoFinal().setText("0");
		getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
	}
	
	private void cargarMapas() {
		mapearModelos();
		mapearColores();
		mapearPresentaciones();
		
		try {
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while (clientesOficinaIt.hasNext()) {
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clientesOficinaIt.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
			
			mapaCliente.clear();
			Collection clientes = SessionServiceLocator.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesIt = clientes.iterator();
			while (clientesIt.hasNext()) {
				ClienteIf cliente = (ClienteIf) clientesIt.next();
				mapaCliente.put(cliente.getId(), cliente);
			}
			
			mapaProductoProveedor.clear();
			Collection productos = SessionServiceLocator.getProductoSessionService().findProductoByEmpresaId(Parametros.getIdEmpresa());
			Iterator productosIt = productos.iterator();
			while (productosIt.hasNext()) {
				ProductoIf productoIf = (ProductoIf) productosIt.next();
				mapaProductoProveedor.put(productoIf.getId(), productoIf);
			}
			
			mapaGenerico.clear();
			Collection genericos = SessionServiceLocator.getGenericoSessionService().findGenericoByEmpresaId(Parametros.getIdEmpresa());
			Iterator genericosIt = genericos.iterator();
			while (genericosIt.hasNext()) {
				GenericoIf genericoIf = (GenericoIf) genericosIt.next();
				mapaGenerico.put(genericoIf.getId(), genericoIf);
			}
			
			mapaTipoDocumento.clear();
			Collection tiposDocumento = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator tiposDocumentoIt = tiposDocumento.iterator();
			while (tiposDocumentoIt.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoIt.next();
				mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
			}
			
			mapaDocumento.clear();
			Collection documentos = SessionServiceLocator.getDocumentoSessionService().findDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator documentosIt = documentos.iterator();
			while (documentosIt.hasNext()) {
				DocumentoIf documento = (DocumentoIf) documentosIt.next();
				mapaDocumento.put(documento.getId(), documento);
			}
			
			mapaLinea.clear();
			Collection lineas = SessionServiceLocator.getLineaSessionService().findLineaByEmpresaId(Parametros.getIdEmpresa());
			Iterator lineasIt = lineas.iterator();
			while (lineasIt.hasNext()) {
				LineaIf linea = (LineaIf) lineasIt.next();
				mapaLinea.put(linea.getId(), linea);
			}
			
			mapaTipoIdentificacion.clear();
			Collection tiposIdentificacion = SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacionList();
			Iterator tiposIdentificacionIt = tiposIdentificacion.iterator();
			while (tiposIdentificacionIt.hasNext()) {
				TipoIdentificacionIf tipoIdentificacionIf = (TipoIdentificacionIf) tiposIdentificacionIt.next();
				mapaTipoIdentificacion.put(tipoIdentificacionIf.getId(), tipoIdentificacionIf);
			}
			
			mapaCampanaProductoVersion.clear();
			Collection campanaProductoVersiones = SessionServiceLocator.getCampanaProductoVersionSessionService().findCampanaProductoVersionByEmpresaId(Parametros.getIdEmpresa());
			Iterator campanaProductoVersionesIt = campanaProductoVersiones.iterator();
			while (campanaProductoVersionesIt.hasNext()) {
				CampanaProductoVersionIf campanaProductoVersionIf = (CampanaProductoVersionIf) campanaProductoVersionesIt.next();
				mapaCampanaProductoVersion.put(campanaProductoVersionIf.getId(), campanaProductoVersionIf);
			}
			
		}catch (GenericBusinessException e) {
			e.printStackTrace();
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

	public void cargarCombos() {
		getCmbFechaPedido().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaVencimiento().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaVencimiento().setDate(null);
		
		try {
			idModulo = ((ModuloIf) SessionServiceLocator.getModuloSessionService().findModuloByCodigo("FACT").iterator().next()).getId();
		} catch (GenericBusinessException e2) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e2.printStackTrace();
		}

		cargarComboListaPrecio();
		cargarComboTipoDocumento();
		cargarComboFormaPago();
		cargarComboMoneda();
		cargarComboCaja();
		cargarComboVendedor();
		cargarComboBodega();
		cargarComboOrigenDocumento();
		cargarComboFundacion();
		cargarComboTipoIdentificacion();
		if (getCmbTipoDocumento().getSelectedItem() != null)
			tipoDocumentoIf = ((TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem());

		cargarComboDocumento();
		cargarComboMotivo();
		cargarComboEstado();
		if (getCmbTipoReferencia().getItemCount()>0)
			getCmbTipoReferencia().setSelectedIndex(0);	
	}
	
	public void cargarComboFundacion() {
		try { 
			Iterator it  = SessionServiceLocator.getTipoClienteSessionService().findTipoClienteByCodigo("FU").iterator();			 
			if (it.hasNext()) {
				TipoClienteIf tipoClienteFundacion = (TipoClienteIf) it.next();					
				List fundaciones = (List) SessionServiceLocator.getClienteSessionService().findClienteByTipoclienteId(tipoClienteFundacion.getId());
				refreshCombo(getCmbFundacion(), fundaciones);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	private void cargarComboEstado() {
		// String estadoSeleccionado = getCmbEstado().getSelectedItem() !=
		// null?getCmbEstado().getSelectedItem().toString():"";
		EstadoPedido estadoCombo = (EstadoPedido) getCmbEstado().getSelectedItem();
		getCmbEstado().removeAllItems();
		if (getMode() == SpiritMode.UPDATE_MODE && pedido != null) {
			try {
				String estadoLetra = pedido.getEstado();
				EstadoPedido estado = EstadoPedido.getEstadoOrdenTrabajoByLetra(estadoLetra);
				
				// if (ESTADO_PENDIENTE.equals(pedido.getEstado()) ||
				// ESTADO_COTIZACION.equals(pedido.getEstado())) {
				if (estado == EstadoPedido.PENDIENTE || estado == EstadoPedido.COTIZACION) {
					getCmbEstado().addItem(EstadoPedido.PENDIENTE);
					getCmbEstado().addItem(EstadoPedido.ANULADO);
					getCmbEstado().addItem(EstadoPedido.COTIZACION);
					/*
					 * getCmbEstado().addItem(NOMBRE_ESTADO_PENDIENTE);
					 * getCmbEstado().addItem(NOMBRE_ESTADO_ANULADO);
					 * getCmbEstado().addItem(NOMBRE_ESTADO_COTIZACION);
					 */
				}
	
				// if (ESTADO_COMPLETO.equals(pedido.getEstado())) {
				if (estado == EstadoPedido.COMPLETO) {
					getCmbEstado().addItem(EstadoPedido.COMPLETO);
					// getCmbEstado().addItem(NOMBRE_ESTADO_COMPLETO);
				}
	
				// if (ESTADO_INCOMPLETO.equals(pedido.getEstado())) {
				if (estado == EstadoPedido.INCOMPLETO) {
					getCmbEstado().addItem(EstadoPedido.INCOMPLETO);
					// getCmbEstado().addItem(NOMBRE_ESTADO_INCOMPLETO);
				}
	
				// if (ESTADO_ANULADO.equals(pedido.getEstado())) {
				if (estado == EstadoPedido.ANULADO) {
					getCmbEstado().addItem(EstadoPedido.ANULADO);
					// getCmbEstado().addItem(NOMBRE_ESTADO_ANULADO);
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			}

		} else if (getMode() == SpiritMode.SAVE_MODE) {
			getCmbEstado().addItem(EstadoPedido.PENDIENTE);
			getCmbEstado().addItem(EstadoPedido.COTIZACION);
			/*
			 * getCmbEstado().addItem(NOMBRE_ESTADO_PENDIENTE);
			 * getCmbEstado().addItem(NOMBRE_ESTADO_COTIZACION);
			 */
		} else if (getMode() == SpiritMode.FIND_MODE) {
			DefaultComboBoxModel modelo = new DefaultComboBoxModel(EstadoPedido.values());
			getCmbEstado().setModel(modelo);
			/*
			 * getCmbEstado().addItem(NOMBRE_ESTADO_PENDIENTE);
			 * getCmbEstado().addItem(NOMBRE_ESTADO_ANULADO);
			 * getCmbEstado().addItem(NOMBRE_ESTADO_COTIZACION);
			 * getCmbEstado().addItem(NOMBRE_ESTADO_COMPLETO);
			 * getCmbEstado().addItem(NOMBRE_ESTADO_INCOMPLETO);
			 */
		} 
		
		if (getCmbEstado().getItemCount() >  0) {
			if (estadoCombo != null)
				getCmbEstado().setSelectedItem(estadoCombo);
			else
				getCmbEstado().setSelectedIndex(0);
			getCmbEstado().validate();
		}
		/*
		 * if (getCmbEstado().getItemCount() > 0) { if
		 * (!estadoSeleccionado.equals(""))
		 * getCmbEstado().setSelectedItem(estadoSeleccionado); else
		 * getCmbEstado().setSelectedIndex(0); getCmbEstado().validate(); }
		 */
	}
	
	private void cargarComboTipoReferencia() {
		/*
		 * getCmbTipoReferencia().addItem(NOMBRE_REFERENCIA_NINGUNO);
		 * getCmbTipoReferencia().addItem(NOMBRE_REFERENCIA_PRESUPUESTO);
		 * getCmbTipoReferencia().addItem(NOMBRE_REFERENCIA_PLAN_MEDIOS);
		 * getCmbTipoReferencia().setSelectedItem(NOMBRE_REFERENCIA_NINGUNO);
		 */
		DefaultComboBoxModel modelo = new DefaultComboBoxModel(TipoReferenciaPedido.values());
		getCmbTipoReferencia().setModel(modelo);
	}
	
	private void cargarComboListaPrecio(){
		try {
			List listaPrecios = (List) SessionServiceLocator.getListaPrecioSessionService().findListaPrecioByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbListaPrecio(),listaPrecios);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboTipoDocumento(){
		// List tiposDocumento = (List)
		// GeneralFinder.findTipoDocumento(Parametros.getIdEmpresa(), idModulo);
		Map parameterMap = new HashMap();
		parameterMap.put("empresaId", Parametros.getIdEmpresa());
		parameterMap.put("moduloId", idModulo);
		Long idUsuario = ((UsuarioIf) Parametros.getUsuarioIf()).getId();
		try {
			List tiposDocumento = (List) SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQueryAndUsuarioId(parameterMap, idUsuario);
			Iterator it = tiposDocumento.iterator();
			while (it.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) it.next();
				if (!tipoDocumento.getCodigo().equals("FAC") && !tipoDocumento.getCodigo().equals("FAE") && !tipoDocumento.getCodigo().equals("FCO") && !tipoDocumento.getCodigo().equals("FAR") && !tipoDocumento.getCodigo().equals("VTA"))
					it.remove();
			}
			refreshCombo(getCmbTipoDocumento(),tiposDocumento);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.INFORMATION);
		}
	}
	
	private void cargarComboFormaPago(){
		try {
			System.out.println("forma de pago:"+Parametros.getIdEmpresa());
			
			List formasPago = (List) SessionServiceLocator.getFormaPagoSessionService().findFormaPagoByEmpresaId(Parametros.getIdEmpresa());
			System.out.println("FORMAS PAGO:"+formasPago);
			refreshCombo(getCmbFormaPago(), formasPago);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboMoneda(){
		try {
			List monedas = (List) SessionServiceLocator.getMonedaSessionService().getMonedaList();
			refreshCombo(getCmbMoneda(), monedas);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboCaja(){
		try {
			Long usuarioId = ((UsuarioIf) SessionServiceLocator.getUsuarioSessionService().findUsuarioByUsuario(Parametros.getUsuario().toLowerCase()).iterator().next()).getId();
			List cajas = (List) SessionServiceLocator.getCajaSessionService().findCajaByUsuarioId(usuarioId);
			refreshCombo(getCmbCaja(),cajas);

			if (getCmbCaja().getItemCount() > 0)
				getCmbCaja().setEnabled(true);
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	Comparator<EmpleadoIf> ordenadorArrayListPorNombre = new Comparator<EmpleadoIf>(){
		public int compare(EmpleadoIf o1, EmpleadoIf o2) {
			return (o1.getNombres().compareTo(o2.getNombres()));
		}		
	};
	
	private void cargarComboVendedor(){
		try {
			List<EmpleadoIf> vendedores = new ArrayList<EmpleadoIf>();
			
			// jgf:
			/*
			 * EmpleadoData empvacio = new EmpleadoData();
			 * empvacio.setApellidos("VENDEDOR"); empvacio.setNombres("SIN");
			 * empvacio.setIdentificacion("-"); empvacio.setCodigo("-");
			 * vendedores.add(0,empvacio);
			 */
			
			/*HashMap<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("empresaId", Parametros.getIdEmpresa());
			mapa.put("vendedor","S");
			Iterator it = SessionServiceLocator.getTipoEmpleadoSessionService().findTipoEmpleadoByQuery(mapa).iterator();
			while (it.hasNext()) {
				TipoEmpleadoIf tipoEmpleado = (TipoEmpleadoIf) it.next();
				mapa.clear();
				mapa.put("tipoempleadoId" , tipoEmpleado.getId() );
				// mapa.put("oficinaId", Parametros.getIdOficina());
				mapa.put("estado" , "A");
				Iterator vendedoresIt = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByQuery(mapa).iterator();
				while (vendedoresIt.hasNext()) {
					EmpleadoIf empleado = (EmpleadoIf) vendedoresIt.next();
					vendedores.add(empleado);
				}
			}*/
			
			Iterator vendedoresIt = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaIdAndTipoEmpleadoVendedor(Parametros.getIdEmpresa(), "S").iterator();
			while (vendedoresIt.hasNext()) {
				EmpleadoIf empleado = (EmpleadoIf) vendedoresIt.next();
				vendedores.add(empleado);
			}
			
			// TODO LUEGO DE INGRESAR LOS DATOS INICIALES, CAMBIAR A -1 EL VALOR
			// DE CERO
			// jgf:
			Collections.sort((ArrayList)vendedores,ordenadorArrayListPorNombre);
			refreshComboByIndex(getCmbVendedor(),vendedores,-1);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboBodega(){
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("estado", "A");
			parameterMap.put("oficinaId", Parametros.getIdOficina());
			List bodegas = (List)SessionServiceLocator.getBodegaSessionService().findBodegaByQuery(parameterMap);
			refreshCombo(getCmbBodega(),bodegas);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboOficina(Long idOficina){
		try {
			Long oficinaId = idOficina;
			List<OficinaIf> oficinas = (ArrayList<OficinaIf>)SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbOficinaEmpresa(),oficinas);
			
			//si la oficinaId es null, seteo por default la oficina del usuario.
			if(oficinaId == null){
				OficinaIf oficinaUsuario = (OficinaIf)Parametros.getOficina();
				oficinaId = oficinaUsuario.getId();				
			}
			
			int indice = 0;
			for(int i=0; i<oficinas.size(); i++){
				OficinaIf oficinaIf = oficinas.get(i);
				if(oficinaIf.getId().compareTo(oficinaId) == 0){
					indice = i;
				}
			}
			
			getCmbOficinaEmpresa().setSelectedIndex(indice);
			getCmbOficinaEmpresa().repaint();
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarComboOrigenDocumento(){
		try {
			List origenes = (List)SessionServiceLocator.getOrigenDocumentoSessionService().findOrigenDocumentoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbOrigenDocumento(),origenes);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboTipoIdentificacion(){
		try {
			List tiposIdentificacion = (List)SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacionList();
			refreshCombo(getCmbTipoIdentificacion(),tiposIdentificacion);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboDocumento(){
		try {
			Long usuarioId = ((UsuarioIf) SessionServiceLocator.getUsuarioSessionService().findUsuarioByUsuario(Parametros.getUsuario().toLowerCase()).iterator().next()).getId();
			if (tipoDocumentoIf != null && usuarioId!=null) {
				List documentos = (List) SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipodocumentoIdAndUsuarioId(tipoDocumentoIf.getId(), usuarioId);
				refreshCombo(getCmbDocumento(),documentos);
				if (getCmbDocumento().getItemCount() > 0)
					inicializarTextFieldsPrecio();
			}

		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboMotivo(){
		try {
			List motivos = (List) SessionServiceLocator.getMotivoDocumentoSessionService().findMotivoDocumentoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbMotivo(),motivos);

			if (tipoDocumentoIf != null) {
				if (OPCION_SI.equals(tipoDocumentoIf.getExigemotivo())) {
					getCmbMotivo().setEnabled(true);
				} else {
					getCmbMotivo().setSelectedItem(null);
					getCmbMotivo().setEnabled(false);
				}
			}

		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	ActionListener oActionListenerBtnActualizarPresupuestoDetalleP = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			actualizarDetallePresupuestoP();
		}
	};
	
	private void actualizarDetallePresupuestoP() {
		int filaSeleccionada = getTblPresupuestoDetalleP().getSelectedRow();
		if (filaSeleccionada != -1) {
			if (getMode() != SpiritMode.FIND_MODE) {
				presupuestoDetalleIfP = (PresupuestoDetalleIf) presupuestoDetalleColeccionP.get(filaSeleccionada);
				PresupuestoDetalleData data = new PresupuestoDetalleData();
														
				data.setId(presupuestoDetalleIfP.getId());
				data.setPresupuestoId(presupuestoDetalleIfP.getPresupuestoId());
				data.setProductoId(productoIfP.getId());
				data.setConcepto(getTxtConceptoPresupuestoDetalleP().getText());
				data.setCantidad(BigDecimal.valueOf(Double.parseDouble(getTxtCantidadP().getText())));
				data.setPrecioventa(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecioVentaP().getText()))));
				data.setReporte(REPORTE_PRESUPUESTO_SI);
				presupuestoDetalleColeccionP.set(filaSeleccionada, data);
				// Actualizo en la tabla
				modelPresupuestoDetalleP.setValueAt(getTxtConceptoPresupuestoDetalleP().getText(),getTblPresupuestoDetalleP().getSelectedRow(), 0);
				modelPresupuestoDetalleP.setValueAt(getTxtCantidadP().getText(), getTblPresupuestoDetalleP().getSelectedRow(), 1);
				modelPresupuestoDetalleP.setValueAt(Utilitarios.removeDecimalFormat(getTxtPrecioVentaP().getText()),getTblPresupuestoDetalleP().getSelectedRow(), 3);
				
				getTxtConceptoPresupuestoDetalleP().setText("");
				getTxtCantidadP().setText("");
				getTxtPrecioVentaP().setText("");
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar el Detalle a actualizar !!!",SpiritAlert.WARNING);
		}
	}
	
	MouseListener oMouseAdapterTblPresupuestoDetalleP = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			if (getTblPresupuestoDetalleP().getSelectedRow() != -1) {
				PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf) presupuestoDetalleColeccionP.get(((JTable) evt.getSource()).getSelectedRow());
				setPresupuestoDetalleP(presupuestoDetalleIf);
			}
		}
	};
	
	private void setPresupuestoDetalleP(PresupuestoDetalleIf presupuestoDetalleIfP) {
		// Para el reporte
		if (presupuestoDetalleIfP.getProductoId() != null) {
			productoIfP = mapaProductoProveedor.get(presupuestoDetalleIfP.getProductoId());
			ClienteOficinaIf proveedorIfP = mapaClienteOficina.get(productoIfP.getProveedorId());
			ClienteIf cliente = mapaCliente.get(proveedorIfP.getClienteId());
			GenericoIf generico = mapaGenerico.get(productoIfP.getGenericoId());
			
			getTxtProveedorP().setText(proveedorIfP.getCodigo() + " - " + cliente.getNombreLegal());
			getTxtProductoP().setText(generico.getNombre());
		}
		
		getTxtConceptoPresupuestoDetalleP().setText(presupuestoDetalleIfP.getConcepto());
		getTxtCantidadP().setText(String.valueOf(Double.valueOf(presupuestoDetalleIfP.getCantidad().toString()).intValue()));
		getTxtPrecioVentaP().setText(String.valueOf(presupuestoDetalleIfP.getPrecioventa().doubleValue()));			
	}

	public void cargarDetallesPresupuesto(String preimpreso) {
		try {
			//cleanTablaPresupuesto();
			tipoDocumento = (getCmbTipoDocumento().getSelectedItem() != null)?(TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem():null;
			DocumentoIf documento = (getCmbDocumento().getSelectedItem() != null)?(DocumentoIf) getCmbDocumento().getSelectedItem():null;
			//modelPedidoDetalle = (DefaultTableModel) getTblPedidoDetalle().getModel();
			//modelPresupuestoDetalleP = (DefaultTableModel) getTblPresupuestoDetalleP().getModel();
			Collection listaPresupuestoDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(presupuestoIf.getId());
			Iterator presupuestoDetalleIterator = listaPresupuestoDetalle.iterator();
			//presupuestoDetalleFacturadoColeccion = new Vector();
			
			if (tipoDocumento != null && (tipoDocumento.getCodigo().equals("FAC") || tipoDocumento.getCodigo().equals("FAE"))) {
				while (presupuestoDetalleIterator.hasNext()) {
					PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf) presupuestoDetalleIterator.next();
					
					// si el mapa esta vacio es porque es facturacion cliente o
					// completa, caso contrario es facturacion parcial
					// y solo se factura los detalles de presupuesto
					// seleccionados (los que estan en el mapa)
					if(mapaPresupuestosDetalleParaFacturar.isEmpty() || mapaPresupuestosDetalleParaFacturar.get(presupuestoDetalleIf.getId()) != null){
						
						if((presupuestoDetalleIf.getReporte() == null) || presupuestoDetalleIf.getReporte().equals(REPORTE_PRESUPUESTO_NO)){
							Double subtotal = 0D, descuento = 0D, iva = 0D, valor = 0D, comisionAgencia = 0D, descuentosVarios = 0D;
							PedidoDetalleData data = new PedidoDetalleData();
							double precio = 0D;
							
							if (tipoDocumento == null || tipoDocumento.getReembolso().equals("N")){
								precio = presupuestoDetalleIf.getPrecioventa().doubleValue();
							}else{
								precio = presupuestoDetalleIf.getPrecioagencia().doubleValue();
							}
							
							// Si exite Negociacion Directa, el precio seria el
							// porcentaje restante de la negociación.
							if(presupuestoDetalleIf.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(0)) == 1){
								precio = precio * (1 - (presupuestoDetalleIf.getPorcentajeNegociacionDirecta().doubleValue()/100));
							}
							
							ProductoIf productoIf = mapaProductoProveedor.get(presupuestoDetalleIf.getProductoId());
							GenericoIf generico = mapaGenerico.get(productoIf.getGenericoId());
													
							double descuentoEspecial = precio * (presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta().doubleValue() / 100);
							precio = precio - descuentoEspecial;
							
							subtotal = precio * presupuestoDetalleIf.getCantidad().doubleValue();
							
							descuento = (tipoDocumento.getReembolso().equals("S"))? 0D : subtotal * (presupuestoDetalleIf.getPorcentajeDescuentoAgenciaVenta().doubleValue() / 100);
							descuentosVarios = (tipoDocumento.getReembolso().equals("S"))? 0D : subtotal * (presupuestoDetalleIf.getPorcentajeDescuentosVariosVenta().doubleValue() / 100);
							
							porcentajeComisionAgencia = (tipoDocumento.getReembolso().equals("N"))?presupuestoIf.getPorcentajeComisionAgencia().doubleValue():0D;
							comisionAgencia = ((subtotal - descuento - descuentosVarios) * porcentajeComisionAgencia) / 100D;
							iva =  (subtotal - descuento - descuentosVarios + comisionAgencia) * IVA;
							
							if (tipoDocumento.getReembolso().equals("S"))
								precio += iva/presupuestoDetalleIf.getCantidad().doubleValue();
							
							valor = subtotal - descuento - descuentosVarios;
							
							Vector<String> filaPlantillaDetalle = new Vector<String>();
							
							String concepto = presupuestoDetalleIf.getConcepto();
							if(presupuestoDetalleIf.getFechaPublicacion() != null){
								String fechaCompleta = Utilitarios.getFechaDiaSemanaDiaMesAnioUppercase(presupuestoDetalleIf.getFechaPublicacion());
								concepto = concepto + "\n\nPUBLICACIÓN: " + fechaCompleta + "\n";
							}
							
							filaPlantillaDetalle.add(concepto);
							filaPlantillaDetalle.add(String.valueOf(Double.valueOf(presupuestoDetalleIf.getCantidad().doubleValue()).intValue()));
							
							if (tipoDocumento == null || tipoDocumento.getReembolso().equals("N"))
								filaPlantillaDetalle.add(String.valueOf(presupuestoDetalleIf.getPrecioventa().doubleValue()));
							else
								filaPlantillaDetalle.add(String.valueOf(precio));
							
							filaPlantillaDetalle.add(formatoDecimal.format(presupuestoDetalleIf.getPorcentajeDescuentoAgenciaVenta().doubleValue()) + " % ");
							filaPlantillaDetalle.add("0 % ");
							
							if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N")) {
								if (documento == null || (documento != null && !documento.getBonificacion().equals(OPCION_SI)))
									ivaTotal = ivaTotal + iva;
								
								filaPlantillaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
							
							} else {
								valor += iva;
								ivaTotal = ivaTotal + iva;
								subTotal += iva;
								filaPlantillaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");
							}
							
							filaPlantillaDetalle.add(formatoDecimal.format(presupuestoDetalleIf.getPorcentajeDescuentosVariosVenta().doubleValue()) + " % ");
							
							if (subtotal > 0D) {
								data.setProductoId(presupuestoDetalleIf.getProductoId());
								data.setCantidadpedida(presupuestoDetalleIf.getCantidad());
								data.setCantidad(BigDecimal.valueOf(0.0));
								
								if(presupuestoDetalleIf.getConcepto() != null){
									data.setDescripcion(concepto);
								}else{
									data.setDescripcion("\n");
								}
								
								data.setPrecio(presupuestoDetalleIf.getPrecioagencia());
								data.setPrecioreal(BigDecimal.valueOf(precio));
								data.setValor(BigDecimal.valueOf(valor));
								
								if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N"))
									data.setIva(BigDecimal.valueOf(iva));
								else
									data.setIva(BigDecimal.valueOf(0.0));
								
								data.setIce(BigDecimal.valueOf(0.0));
								data.setOtroimpuesto(BigDecimal.valueOf(0.0));
								data.setDescuento(BigDecimal.valueOf(descuento));
								data.setPorcentajeDescuentosVarios(presupuestoDetalleIf.getPorcentajeDescuentosVariosVenta());
								data.setDescuentoGlobal(BigDecimal.ZERO);
								
								// Para que no se tenga que actualizar el documento uno a uno en los detalles
								documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipoDocumentoId(tipoDocumentoIf.getId()).iterator().next();
														
								data.setDocumentoId((documento!=null)?documento.getId():null);
								
								pedidoDetalleColeccion.add(data);
								presupuestoDetalleFacturadoColeccion.add(presupuestoDetalleIf);
								
								modelPedidoDetalle.addRow(filaPlantillaDetalle);
								subTotal = subTotal + subtotal;
								
								if (generico.getCobraIvaCliente().equals("N"))
									totalIvaCero += subtotal;
								
								if (tipoDocumento.getReembolso().equals("N")) {
									getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
									getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
								} else {
									getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
									getTxtIVAFinal().setText("0");
								}
								
								descuentoTotal += descuento;
								descuentosVariosTotal += descuentosVarios;
								comisionAgenciaTotal += comisionAgencia;
								getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
								getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));
								getTxtICEFinal().setText("0");
								getTxtOtroImpuestoFinal().setText("0");
								
								if (tipoDocumento.getReembolso().equals("N"))
									total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal + ivaTotal;
								else
									total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal;
								
								cargandoDetallesReferencia = true;
								getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
								cargandoDetallesReferencia = false;
								getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
								getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
							}
						
						}else if(presupuestoDetalleIf.getReporte().equals(REPORTE_PRESUPUESTO_SI)){
							Vector<String> filaPlantillaDetalleP = new Vector<String>();
							presupuestoDetalleColeccionP.add(presupuestoDetalleIf);
							filaPlantillaDetalleP.add(presupuestoDetalleIf.getConcepto());
							filaPlantillaDetalleP.add(String.valueOf(Double.valueOf(presupuestoDetalleIf.getCantidad().toString()).intValue()));
							filaPlantillaDetalleP.add("");
							filaPlantillaDetalleP.add(String.valueOf(presupuestoDetalleIf.getPrecioventa().doubleValue()));
							modelPresupuestoDetalleP.addRow(filaPlantillaDetalleP);
						}
					}				
					
				}
			} else if (tipoDocumento != null && tipoDocumento.getCodigo().equals("FAR") && tipoDocumento.getReembolso().equals("S")) {
				
				ordenesSinFacturar = SpiritConstants.getEmptyCharacter();
				
				while (presupuestoDetalleIterator.hasNext()) {
					PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf) presupuestoDetalleIterator.next();
					
					String facturasProveedor = SpiritConstants.getEmptyCharacter();
										
					// si el mapa esta vacio es porque es facturacion cliente o
					// completa, caso contrario es facturacion parcial
					// y solo se factura los detalles de presupuesto
					// seleccionados (los que estan en el mapa)
					if(mapaPresupuestosDetalleParaFacturar.isEmpty() || mapaPresupuestosDetalleParaFacturar.get(presupuestoDetalleIf.getId()) != null){
						
						if((presupuestoDetalleIf.getReporte() == null) || presupuestoDetalleIf.getReporte().equals(REPORTE_PRESUPUESTO_NO)){
							Map queryMap = new HashMap();
							queryMap.put("tipoOrden", "OC");
							queryMap.put("ordenId", presupuestoDetalleIf.getOrdenCompraId());
							String facturasAsociadas = getComprasAsociadasPorOrden(queryMap);
							if (presupuestoDetalleIf.getOrdenCompraId() != null && facturasAsociadas.equals(SpiritConstants.getEmptyCharacter())) {
								OrdenCompraIf ordenCompra = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(presupuestoDetalleIf.getOrdenCompraId());
								ordenesSinFacturar += ordenCompra.getCodigo() + "\n";
							}
							if (facturasProveedor.contains(facturasAsociadas))
								facturasAsociadas = SpiritConstants.getEmptyCharacter();
							if (!facturasProveedor.equals(SpiritConstants.getEmptyCharacter()) && !facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
								facturasProveedor += ", ";
							facturasProveedor += facturasAsociadas;
							Double subtotal = 0D, descuento = 0D, iva = 0D, valor = 0D, comisionAgencia = 0D, descuentosVarios = 0D;
							double precio = 0D;
							
							PedidoDetalleData data = new PedidoDetalleData();
							
							ProductoIf productoIf = mapaProductoProveedor.get(presupuestoDetalleIf.getProductoId());
							GenericoIf generico = mapaGenerico.get(productoIf.getGenericoId());
													
							precio = presupuestoDetalleIf.getPrecioagencia().doubleValue();
							
							// Si exite Negociacion Directa, el precio seria el
							// porcentaje restante de la negociación.
							if(presupuestoDetalleIf.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(0)) == 1){
								precio = precio * (1 - (presupuestoDetalleIf.getPorcentajeNegociacionDirecta().doubleValue()/100));
							}
							
							double descuentoEspecial = precio * (presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta().doubleValue() / 100);
							precio = precio - descuentoEspecial;
							
							subtotal = precio * presupuestoDetalleIf.getCantidad().doubleValue();
							descuento = subtotal * presupuestoDetalleIf.getPorcentajeDescuentoAgenciaCompra().doubleValue() / 100;
							descuentosVarios = subtotal * presupuestoDetalleIf.getPorcentajeDescuentosVariosVenta().doubleValue() / 100;
												
							iva =  (subtotal - descuento - descuentosVarios) * IVA;
							
							if(generico.getCobraIva().equals("S")){
								subtotal = subtotal - descuento - descuentosVarios + iva;
							}else{
								subtotal = subtotal - descuento - descuentosVarios;
							}
							valor = subtotal;
							descuento = 0D;
							descuentosVarios = 0D;
							iva = 0D;
							
							precio = subtotal / presupuestoDetalleIf.getCantidad().doubleValue();				
							
							Vector<String> filaPlantillaDetalle = new Vector<String>();
							
							String concepto = presupuestoDetalleIf.getConcepto();
							if(presupuestoDetalleIf.getFechaPublicacion() != null){
								String fechaCompleta = Utilitarios.getFechaDiaSemanaDiaMesAnioUppercase(presupuestoDetalleIf.getFechaPublicacion());
								concepto = concepto + "\n\nPUBLICACIÓN: " + fechaCompleta + "\n";
							}
							
							filaPlantillaDetalle.add(concepto);
							filaPlantillaDetalle.add(String.valueOf(Double.valueOf(presupuestoDetalleIf.getCantidad().doubleValue()).intValue()));
							
							if (tipoDocumento.getReembolso().equals("S")){
								filaPlantillaDetalle.add(String.valueOf(precio));
								filaPlantillaDetalle.add("0 % ");
								filaPlantillaDetalle.add("0 % ");
							}else{
								filaPlantillaDetalle.add(String.valueOf(presupuestoDetalleIf.getPrecioventa().doubleValue()));
								filaPlantillaDetalle.add(formatoDecimal.format(presupuestoDetalleIf.getPorcentajeDescuentoAgenciaVenta().doubleValue()) + " % ");
								filaPlantillaDetalle.add("0 % ");
							}					
							
							if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N")) {
								if (documento == null || (documento != null && !documento.getBonificacion().equals(OPCION_SI)))
									ivaTotal = ivaTotal + iva;
								filaPlantillaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
							} else {
								filaPlantillaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");
							}
							
							if (tipoDocumento.getReembolso().equals("S")){
								filaPlantillaDetalle.add("0 % ");
							}else{
								filaPlantillaDetalle.add(formatoDecimal.format(presupuestoDetalleIf.getPorcentajeDescuentosVariosVenta().doubleValue()) + " % ");
							}
							
							if (subtotal > 0D) {
								data.setProductoId(presupuestoDetalleIf.getProductoId());
								data.setCantidadpedida(presupuestoDetalleIf.getCantidad());
								data.setCantidad(BigDecimal.valueOf(0.0));
								
								if(presupuestoDetalleIf.getConcepto() != null){
									data.setDescripcion(concepto);
								}else{
									data.setDescripcion("\n");
								}
														
								data.setPrecio(BigDecimal.valueOf(precio));
								data.setPrecioreal(BigDecimal.valueOf(precio));
								data.setValor(BigDecimal.valueOf(valor));
								
								if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N"))
									data.setIva(BigDecimal.valueOf(iva));
								else
									data.setIva(BigDecimal.valueOf(0.0));
								
								data.setIce(BigDecimal.valueOf(0.0));
								data.setOtroimpuesto(BigDecimal.valueOf(0.0));
								data.setDescuento(BigDecimal.valueOf(descuento));
								data.setPorcentajeDescuentosVarios(BigDecimal.valueOf(descuentosVarios));
								data.setDescuentoGlobal(BigDecimal.ZERO);
								data.setComprasReembolsoAsociadas(facturasProveedor);
								
								// Para que no se tenga que actualizar el
								// documento
								// uno a uno en los detalles
								documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipoDocumentoId(tipoDocumentoIf.getId()).iterator().next();
								data.setDocumentoId((documento!=null)?documento.getId():null);
								
								pedidoDetalleColeccion.add(data);
								presupuestoDetalleFacturadoColeccion.add(presupuestoDetalleIf);
								modelPedidoDetalle.addRow(filaPlantillaDetalle);
								subTotal = subTotal + subtotal;
								
								if (generico.getCobraIvaCliente().equals("N"))
									totalIvaCero += subtotal;
								
								if (tipoDocumento.getReembolso().equals("N")) {
									getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
									getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
								} else {
									getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
									getTxtIVAFinal().setText("0");
								}
								
								descuentoTotal += descuento;
								descuentosVariosTotal += descuentosVarios;
								comisionAgenciaTotal += comisionAgencia;
								getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
								getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));
								getTxtICEFinal().setText("0");
								getTxtOtroImpuestoFinal().setText("0");
								
								if (tipoDocumento.getReembolso().equals("N"))
									total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal + ivaTotal;
								else
									total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal;
								
								cargandoDetallesReferencia = true;
								getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
								cargandoDetallesReferencia = false;
								getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
								getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
							}
						}else if(presupuestoDetalleIf.getReporte().equals(REPORTE_PRESUPUESTO_SI)){
							Vector<String> filaPlantillaDetalleP = new Vector<String>();
							presupuestoDetalleColeccionP.add(presupuestoDetalleIf);
							filaPlantillaDetalleP.add(presupuestoDetalleIf.getConcepto());
							filaPlantillaDetalleP.add(String.valueOf(Double.valueOf(presupuestoDetalleIf.getCantidad().toString()).intValue()));
							filaPlantillaDetalleP.add("");
							filaPlantillaDetalleP.add(String.valueOf(presupuestoDetalleIf.getPrecioventa().doubleValue()));
							modelPresupuestoDetalleP.addRow(filaPlantillaDetalleP);
						}
					}					
					
				}
				if (subTotal > 0D && !ordenesSinFacturar.equals(SpiritConstants.getEmptyCharacter()))
					SpiritAlert.createAlert("Se han cargado los detalles del presupuesto correctamente, aunque se presentó un problema\nal cargar datos de las facturas de proveedores asociadas.\n\nLas facturas de las siguientes órdenes no han sido ingresadas al sistema:\n\n" + ordenesSinFacturar, SpiritAlert.WARNING);
			
			}else if (tipoDocumento.getCodigo().equals(CODIGO_TIPO_DOCUMENTO_FACTURA_COMISION)) {
				
				Long productoComisionId = 0L;
				while (presupuestoDetalleIterator.hasNext()) {
					PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf) presupuestoDetalleIterator.next();
					
					// si el mapa esta vacio es porque es facturacion cliente o
					// completa, caso contrario es facturacion parcial
					// y solo se factura los detalles de presupuesto
					// seleccionados (los que estan en el mapa)
					if(mapaPresupuestosDetalleParaFacturar.isEmpty() || mapaPresupuestosDetalleParaFacturar.get(presupuestoDetalleIf.getId()) != null){
						
						if((presupuestoDetalleIf.getReporte() == null) || presupuestoDetalleIf.getReporte().equals(REPORTE_PRESUPUESTO_NO)){
							Double subtotal = 0D, subtotalAgencia = 0D, subtotalVenta = 0D, 
							descuentoCompra = 0D, descuentoVenta = 0D, comisionAgencia = 0D, iva = 0D;
							
							subtotalAgencia = presupuestoDetalleIf.getPrecioagencia().doubleValue() * presupuestoDetalleIf.getCantidad().doubleValue();
							subtotalVenta = presupuestoDetalleIf.getPrecioventa().doubleValue() * presupuestoDetalleIf.getCantidad().doubleValue();
							
							double descuentoEspecialAgencia = subtotalAgencia * (presupuestoDetalleIf.getPorcentajeDescuentoEspecialCompra().doubleValue() / 100);
							double descuentoEspecialVenta = subtotalVenta * (presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta().doubleValue() / 100);
													
							descuentoCompra = (subtotalAgencia - descuentoEspecialAgencia) * (presupuestoDetalleIf.getPorcentajeDescuentoAgenciaCompra().doubleValue() / 100);
							descuentoVenta = (subtotalVenta - descuentoEspecialVenta) * (presupuestoDetalleIf.getPorcentajeDescuentoAgenciaVenta().doubleValue() / 100);
							
							comisionAgencia = (subtotalVenta - descuentoVenta - descuentoEspecialVenta) * (presupuestoIf.getPorcentajeComisionAgencia().doubleValue() / 100);
							subtotal = (descuentoCompra + descuentoEspecialAgencia) - (descuentoVenta + descuentoEspecialVenta) + comisionAgencia;
							
							// Si exite Negociacion Directa, el subtotal seria
							// el porcentaje restante de la negociación.
							if(presupuestoDetalleIf.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(0)) == 1){
								subtotal = subtotal * (1 - (presupuestoDetalleIf.getPorcentajeNegociacionDirecta().doubleValue()/100));
							}
							
							// Para no guardar detalles con valor 0 hago la
							// siguiente validación.
							if(subtotal > 0D){
								iva = subtotal * IVA;
								ivaTotal = ivaTotal + iva;
								
								if (subtotal > 0D) {
									subTotal = subTotal + subtotal;
									total = subTotal + ivaTotal;
								}
								
								productoComisionId = presupuestoDetalleIf.getProductoId();
								
								// CREAR DETALLE COMISION
								// ACTUALIZAR TEXTFIELDS TOTALES
								PedidoDetalleData data = new PedidoDetalleData();
										
								data.setProductoId(productoComisionId);							
								data.setDocumentoId((documento!=null)?documento.getId():null);
								data.setCantidadpedida(BigDecimal.valueOf(1D));
								data.setCantidad(BigDecimal.valueOf(1D));
								
								if(preimpreso != null){
									// data.setDescripcion("SOBRE F: " +
									// preimpreso);
									preimpresoFacturaReembolso = preimpreso;
									ClienteOficinaIf proveedorOficina = mapaClienteOficina.get(presupuestoDetalleIf.getProveedorId());
									ClienteIf proveedor = mapaCliente.get(proveedorOficina.getClienteId());
									data.setDescripcion(proveedor.getNombreLegal());
								}else{
									data.setDescripcion("\n");
								}
								
								data.setPrecio(BigDecimal.valueOf(subtotal));
								data.setPrecioreal(BigDecimal.valueOf(subtotal));
								data.setValor(BigDecimal.valueOf(subtotal));
								data.setIva(BigDecimal.valueOf(iva));
								data.setIce(BigDecimal.valueOf(0.0));
								data.setOtroimpuesto(BigDecimal.valueOf(0.0));
								data.setDescuento(BigDecimal.ZERO);
								data.setPorcentajeDescuentosVarios(BigDecimal.ZERO);
								data.setDescuentoGlobal(BigDecimal.ZERO);
								pedidoDetalleColeccion.add(data);
								presupuestoDetalleFacturadoColeccion.add(presupuestoDetalleIf);
								Vector<String> filaPlantillaDetalle = new Vector<String>();
								filaPlantillaDetalle.add("COMISION DE AGENCIA");
								filaPlantillaDetalle.add("1");
								filaPlantillaDetalle.add(String.valueOf(subtotal));
								filaPlantillaDetalle.add("0 % ");
								filaPlantillaDetalle.add(formatoDecimal.format(IVA * 100) + " %");
								filaPlantillaDetalle.add("0 % ");
								modelPedidoDetalle.addRow(filaPlantillaDetalle);
							}					
						}
					}					
					
				}
				
				getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
				getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(BigDecimal.ZERO)));
				getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(BigDecimal.ZERO)));
				getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
				getTxtICEFinal().setText("0");
				getTxtOtroImpuestoFinal().setText("0");
				getTxtPorcentajeComision().setText("0");
				getTxtValorComision().setText("0");
				getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
			}
						
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	public void cargarDetallesPresupuestoTipoFacturacion(String tipoFacturacion, String clienteNombre) {
		try {
			tipoDocumento = (getCmbTipoDocumento().getSelectedItem() != null)?(TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem():null;
					
			if(tipoDocumento != null && tipoDocumento.getCodigo().equals("FAC")){
				DocumentoIf documento = null;
				Collection documentoFactura = SessionServiceLocator.getDocumentoSessionService().findDocumentoByCodigo("FACT");
				Iterator documentoFacturaIt = documentoFactura.iterator();
				while(documentoFacturaIt.hasNext()){
					documento = (DocumentoIf)documentoFacturaIt.next();
				}
				
				if(documento != null && documento.getCodigo().equals("FACT")){
					modelPedidoDetalle = (DefaultTableModel) getTblPedidoDetalle().getModel();
					modelPresupuestoDetalleP = (DefaultTableModel) getTblPresupuestoDetalleP().getModel();
					Collection listaPresupuestoDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(presupuestoIf.getId());
					Iterator presupuestoDetalleIterator = listaPresupuestoDetalle.iterator();
					presupuestoDetalleFacturadoColeccion = new Vector();
					
					while (presupuestoDetalleIterator.hasNext()) {
						PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf) presupuestoDetalleIterator.next();
						
						String concepto = presupuestoDetalleIf.getConcepto();
						
						if(presupuestoDetalleIf.getReporte().equals("N") && clienteOficinaIf != null && presupuestoDetalleIf.getProveedorId().compareTo(clienteOficinaIf.getId()) == 0){
							if((presupuestoDetalleIf.getReporte() == null) || presupuestoDetalleIf.getReporte().equals(REPORTE_PRESUPUESTO_NO)){
								Double subtotal = 0D, iva = 0D, valor = 0D;
								PedidoDetalleData data = new PedidoDetalleData();
								double precio = 0D;
								
								if(tipoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA) && presupuestoDetalleIf.getPorcentajeComisionPura().compareTo(new BigDecimal(0)) <= 0){
									// double descuentoCompra =
									// presupuestoDetalleIf.getPorcentajeDescuentoAgenciaCompra().doubleValue()/100;
									// double descuentosVarios =
									// presupuestoDetalleIf.getPorcentajeDescuentosVariosCompra().doubleValue()/100;
									double descuentoTotal = (presupuestoDetalleIf.getPorcentajeDescuentoAgenciaCompra().doubleValue() + presupuestoDetalleIf.getPorcentajeDescuentosVariosCompra().doubleValue()) / 100;
									double negociacionDirecta = presupuestoDetalleIf.getPorcentajeNegociacionDirecta().doubleValue()/100;
									
									double descuentoEspecialCompra = presupuestoDetalleIf.getPrecioagencia().doubleValue() * (presupuestoDetalleIf.getPorcentajeDescuentoEspecialCompra().doubleValue() / 100);
																		
									precio = (presupuestoDetalleIf.getPrecioagencia().doubleValue() - descuentoEspecialCompra) * descuentoTotal * negociacionDirecta;
									concepto = "COMISIÓN DE AGENCIA POR MUTUO CLIENTE: " + "\n" + clienteNombre + "\n\n" + concepto;
								}
								else if(tipoFacturacion.equals(TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA)){
									double descuentoEspecialCompra = presupuestoDetalleIf.getPrecioagencia().doubleValue() * (presupuestoDetalleIf.getPorcentajeDescuentoEspecialCompra().doubleValue() / 100);
									double comisionPura = presupuestoDetalleIf.getPorcentajeComisionPura().doubleValue()/100;
									precio = (presupuestoDetalleIf.getPrecioagencia().doubleValue() - descuentoEspecialCompra) * comisionPura;
									concepto = "COMISIÓN DE AGENCIA POR MUTUO CLIENTE: " + "\n" + clienteNombre + "\n\n" + concepto;
								}						
									
								ProductoIf productoIf = mapaProductoProveedor.get(presupuestoDetalleIf.getProductoId());
								GenericoIf generico = mapaGenerico.get(productoIf.getGenericoId());
								subtotal = precio * presupuestoDetalleIf.getCantidad().doubleValue();
								iva =  subtotal * IVA;
								
								if (tipoDocumento.getReembolso().equals("S"))
									precio += iva/presupuestoDetalleIf.getCantidad().doubleValue();
								
								valor = subtotal;
								
								Vector<String> filaPlantillaDetalle = new Vector<String>();
								filaPlantillaDetalle.add(concepto);
								filaPlantillaDetalle.add(String.valueOf(Double.valueOf(presupuestoDetalleIf.getCantidad().doubleValue()).intValue()));
								filaPlantillaDetalle.add(String.valueOf(precio));								
								filaPlantillaDetalle.add(formatoDecimal.format(presupuestoDetalleIf.getPorcentajeDescuentoAgenciaVenta().doubleValue()) + " % ");
								filaPlantillaDetalle.add("0 % ");
								
								if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N")) {
									if (documento == null || (documento != null && !documento.getBonificacion().equals(OPCION_SI)))
										ivaTotal = ivaTotal + iva;
									
									filaPlantillaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
								
								} else {
									valor += iva;
									ivaTotal = ivaTotal + iva;
									subTotal += iva;
									filaPlantillaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");
								}
								
								filaPlantillaDetalle.add(formatoDecimal.format(presupuestoDetalleIf.getPorcentajeDescuentosVariosVenta().doubleValue()) + " % ");
								
								if (subtotal > 0D) {
									data.setProductoId(presupuestoDetalleIf.getProductoId());
									data.setCantidadpedida(presupuestoDetalleIf.getCantidad());
									data.setCantidad(BigDecimal.valueOf(0.0));
									
									if(presupuestoDetalleIf.getConcepto() != null){
										data.setDescripcion(concepto);
									}else{
										data.setDescripcion("\n");
									}
									
									data.setPrecio(presupuestoDetalleIf.getPrecioagencia());
									data.setPrecioreal(BigDecimal.valueOf(precio));
									data.setValor(BigDecimal.valueOf(valor));
									
									if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N"))
										data.setIva(BigDecimal.valueOf(iva));
									else
										data.setIva(BigDecimal.valueOf(0.0));
									
									data.setIce(BigDecimal.valueOf(0.0));
									data.setOtroimpuesto(BigDecimal.valueOf(0.0));
									data.setDescuento(BigDecimal.valueOf(0.0));
									data.setPorcentajeDescuentosVarios(BigDecimal.valueOf(0.0));
									data.setDescuentoGlobal(BigDecimal.ZERO);
									
									// Para que no se tenga que actualizar el
									// documento
									// uno a uno en los detalles
									documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipoDocumentoId(tipoDocumentoIf.getId()).iterator().next();
									data.setDocumentoId((documento!=null)?documento.getId():null);
									
									pedidoDetalleColeccion.add(data);
									presupuestoDetalleFacturadoColeccion.add(presupuestoDetalleIf);
									
									modelPedidoDetalle.addRow(filaPlantillaDetalle);
									subTotal = subTotal + subtotal;
									
									if (generico.getCobraIvaCliente().equals("N"))
										totalIvaCero += subtotal;
									
									if (tipoDocumento.getReembolso().equals("N")) {
										getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
										getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
									} else {
										getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
										getTxtIVAFinal().setText("0");
									}
									
									getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
									getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));
									
									getTxtICEFinal().setText("0");
									getTxtOtroImpuestoFinal().setText("0");
									
									if (tipoDocumento.getReembolso().equals("N"))
										total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal + ivaTotal;
									else
										total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal;
									
									cargandoDetallesReferencia = true;
									getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
									cargandoDetallesReferencia = false;
									getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
									getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
								}
							
							}else if(presupuestoDetalleIf.getReporte().equals(REPORTE_PRESUPUESTO_SI)){
								Vector<String> filaPlantillaDetalleP = new Vector<String>();
								presupuestoDetalleColeccionP.add(presupuestoDetalleIf);
								filaPlantillaDetalleP.add(concepto);
								filaPlantillaDetalleP.add(String.valueOf(Double.valueOf(presupuestoDetalleIf.getCantidad().toString()).intValue()));
								filaPlantillaDetalleP.add("");
								filaPlantillaDetalleP.add(String.valueOf(presupuestoDetalleIf.getPrecioventa().doubleValue()));
								modelPresupuestoDetalleP.addRow(filaPlantillaDetalleP);
							}
						}						
					}
				}else{
					SpiritAlert.createAlert("Para facturar a proveedores el Documento debe ser FACTURA.", SpiritAlert.WARNING);
					clean();
					showSaveMode();
				}
				
			}else{
				SpiritAlert.createAlert("Para facturar a proveedores el Tipo de Documento debe ser FACTURA.", SpiritAlert.WARNING);
				clean();
				showSaveMode();
			}					
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	public void cargarDetallesReportePresupuesto() {
		try {
			//cleanTablaPresupuesto();
			//modelPresupuestoDetalleP = (DefaultTableModel) getTblPresupuestoDetalleP().getModel();
			Collection listaPresupuestoDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(presupuestoIf.getId());
			Iterator presupuestoDetalleIterator = listaPresupuestoDetalle.iterator();

			while (presupuestoDetalleIterator.hasNext()) {
				PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf) presupuestoDetalleIterator.next();
				
				if((presupuestoDetalleIf.getReporte() != null) && presupuestoDetalleIf.getReporte().equals(REPORTE_PRESUPUESTO_SI)){
					Vector<String> filaPlantillaDetalleP = new Vector<String>();
					presupuestoDetalleColeccionP.add(presupuestoDetalleIf);
					filaPlantillaDetalleP.add(presupuestoDetalleIf.getConcepto());
					filaPlantillaDetalleP.add(String.valueOf(Double.valueOf(presupuestoDetalleIf.getCantidad().toString()).intValue()));
					filaPlantillaDetalleP.add("");
					filaPlantillaDetalleP.add(formatoDecimal.format(presupuestoDetalleIf.getPrecioventa().doubleValue()));
					modelPresupuestoDetalleP.addRow(filaPlantillaDetalleP);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	public String obtenerComercial(String comercial){
		String tipoComercial = "";
		
		
		return tipoComercial;
	}
	
	/*
	 * Comerciales del Plan de Medio con su respectivas ordenes de medio Del
	 * PlanMedioMes se obtiene la lista de PlanesMedioDetalle para obtener la
	 * lista de Comerciales cada comercial de esa lista es comparado con el
	 * comercial de cada OrdenMedioDetalle de la lista de Ordenes de Medio para
	 * formar el Mapa de Comerciales con sus respectivas oredenes de medio
	 */
	public Map<ComercialIf,ArrayList<OrdenMedioIf>> cargarMapaProductos(ArrayList<ClienteOficinaIf>listaProveedores){
		Map<ComercialIf,ArrayList<OrdenMedioIf>> mapaComercialesOrdenMedioDetalle = new LinkedHashMap<ComercialIf, ArrayList<OrdenMedioIf>>();
		try{
			ArrayList<Long> listaIdComerciales = new ArrayList<Long>();
			ArrayList<ComercialIf> listaComerciales = new ArrayList<ComercialIf>();// lista
																					// de
																					// comerciales
																					// del
																					// plan
																					// de
																					// medios
			// Se obtiene el Plan de Medio Mes del Plan de Medio a ser Pedido o
			// Facturado
			PlanMedioMesIf planMedioMesIf = (PlanMedioMesIf)SessionServiceLocator.getPlanMedioMesSessionService().findPlanMedioMesByPlanmedioId(planMedioIf.getId()).iterator().next();
			// se obitene las listas de Planes Medio Detalles del Plan Medio Mes
			Collection listaPlanMedioDetalle = SessionServiceLocator.getPlanMedioDetalleSessionService().findPlanMedioDetalleByPlanMedioMesId(planMedioMesIf.getId());
			Iterator listaPlanMedioDetalleIt = listaPlanMedioDetalle.iterator();
			// de cada Plan Medio Detalle se obtiene el Comercial y se lo agrega
			// a la lista de Comerciales
			while(listaPlanMedioDetalleIt.hasNext()){
				boolean agregar = true;
				PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf)listaPlanMedioDetalleIt.next();
				long idComercial = planMedioDetalleIf.getComercialId();
				// for que llena la lista de id de comerciales
				for(long id : listaIdComerciales){
					if(id == idComercial)
						agregar = false;
				}
				// Se llena las lista de id de Comerciales y la lista de
				// Comerciales
				// obtenido la lista de Planes Medios Detalle del Plan de Medio
				// Mes
				if(agregar){
					listaIdComerciales.add(idComercial);
					ComercialIf comercialIf = SessionServiceLocator.getComercialSessionService().getComercial(idComercial);
					listaComerciales.add(comercialIf);
				}
			}

			// Se obtiene todas las Ordenes de Medio del PlanMedio a ser Pedido
			// o Facturado
			Collection listaOrdenMedio = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planMedioIf.getId());
			// for que recorre cada Comercial de la lista de Comerciales del
			// Plan Medio Mes
			// para comparar con el id Comercial de cada OrdenMedioDetalle de la
			// listaOrdenMedio
			for(ComercialIf com : listaComerciales){
				long idComercial = com.getId();
				ArrayList<OrdenMedioIf> listaOrdenMedioTmp = new ArrayList<OrdenMedioIf>(); 
				Iterator listaOrdenMedioIt = listaOrdenMedio.iterator();
				// Se recorre la lista de Ordenes de Medio
				while(listaOrdenMedioIt.hasNext()){
					// Se obtiene la siguiente OrdenMedio
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf)listaOrdenMedioIt.next();
					// Se obtiene una colleccion de OrdenMedioDetalle de la
					// OrdenMedio
					Collection<OrdenMedioDetalleIf> listaOrdenMedioDetalleIf = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
					Iterator ordenMedioDetalleIt = listaOrdenMedioDetalleIf.iterator();
					if(ordenMedioDetalleIt.hasNext()){
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
						// de una OrdenMedioDetalle se compara el id del
						// Comercial con el id del Comercial del for com
						if(ordenMedioDetalleIf.getComercialId().compareTo(idComercial)==0){
							// agrega a la orden Medio a una lista de Ordenes de
							// Medio temporal
							listaOrdenMedioTmp.add(ordenMedioIf);
						}
					}
				}
				// agrega el comercial com con su respectiva lista de Ordenes de
				// Medio
				mapaComercialesOrdenMedioDetalle.put(com,listaOrdenMedioTmp);
			}

		}catch (Exception e) {
			// TODO: handle exception
		}
		// mapa que contiene a todos los comerciales con sus respectivas ordenes
		// de Medio
		return mapaComercialesOrdenMedioDetalle;
	}
	
	// ADD 6 SEPTIEMBRE
	/*
	 * Productos Clientes del Plan de Medio con su respectivas versiones,
	 * ordenes de medio, ordenes de medio con sus respectivas ordenes medio
	 * detalle, ordenes medio detalle con sus respectivas ordenes medio detalle
	 * mapa Del PlanMedioMes se obtiene la lista de PlanesMedioDetalle para
	 * obtener la lista de Productos cliente cada producto de esa lista es
	 * comparado con el producto de cada OrdenMedioDetalle de la lista de
	 * Ordenes de Medio para formar el Mapa de Comerciales con sus respectivas
	 * ordenes de medio totales con sus OrdenMedioDetalle y
	 * OrdenMedioDetalleMapa
	 * 
	 * Se agrego codigo para que el sistema soporte Ordenes de Medio agrupadas x
	 * Canal, de esta manera se enlista a los productos de las Ordenes, con las
	 * Ordenes de Medio que tienen en sus Ordenes de Medio Detalle a un producto
	 * en particular y de esta Ordenes de Medio Detalle se agregan las Ordenes
	 * Medio Detalle Mapa
	 */
	/*public Map<ProductoClienteIf,Map<Long,Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>>> cargarMapaProductosClienteVersionesDetalle() {
		// mapa de productos con sus respectivas OrdenMedio estas con sus
		// OrdenMedioDetalle y estas con sus respectivas OrdenesMedioDetalleMapa
		Map<ProductoClienteIf,Map<Long,Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>>> mapaProductosClienteVersionesOrdenMedioDetalleMapa = new LinkedHashMap<ProductoClienteIf, Map<Long,Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>>>();
			
		try{
			Map<Long,ArrayList<CampanaProductoVersionIf>> mapaIdProductosClienteListVersion = new LinkedHashMap<Long, ArrayList<CampanaProductoVersionIf>>();
			ArrayList<CampanaProductoVersionIf> listVersiones;
				
			// lista de productos clientes del plan de medios
			Map<ProductoClienteIf,ArrayList<CampanaProductoVersionIf>> mapaProductosClienteListVersion = new LinkedHashMap<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>>();
			ArrayList<CampanaProductoVersionIf> listaVersionesProductosCliente;
			
			// Se obtiene el Plan de Medio Mes del Plan de Medio a ser Pedido o
			// Facturado
			PlanMedioMesIf planMedioMesIf = (PlanMedioMesIf)SessionServiceLocator.getPlanMedioMesSessionService().findPlanMedioMesByPlanmedioId(planMedioIf.getId()).iterator().next();
			// se obitene las listas de Planes Medio Detalles del Plan Medio Mes
			
			Collection listaPlanMedioDetalle = SessionServiceLocator.getPlanMedioDetalleSessionService().findPlanMedioDetalleByPlanMedioMesId(planMedioMesIf.getId());
			Iterator listaPlanMedioDetalleIt = listaPlanMedioDetalle.iterator();
			// de cada Plan Medio Detalle se obtiene el Comercial y se lo agrega
			// a la lista de Comerciales
			
			Map campanaProductoVersionMap = new HashMap();
			while(listaPlanMedioDetalleIt.hasNext()){
				PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf)listaPlanMedioDetalleIt.next();
				CampanaProductoVersionIf campanaProductoVersion = SessionServiceLocator.getCampanaProductoVersionSessionService().getCampanaProductoVersion(planMedioDetalleIf.getCampanaProductoVersionId());
				campanaProductoVersionMap.put(planMedioDetalleIf.getCampanaProductoVersionId(), campanaProductoVersion);
			}
			Map<Long,ProductoClienteIf> campanaProductoProductoClienteMap = new HashMap<Long,ProductoClienteIf>();
			Iterator campanaProductoVersionMapIt = campanaProductoVersionMap.keySet().iterator();
			while(campanaProductoVersionMapIt.hasNext()){
				Long campanaProductoVersionId = (Long)campanaProductoVersionMapIt.next();
				CampanaProductoVersionIf campanaProductoVersion = (CampanaProductoVersionIf)campanaProductoVersionMap.get(campanaProductoVersionId);
				CampanaProductoIf campanaProducto = SessionServiceLocator.getCampanaProductoSessionService().getCampanaProducto(campanaProductoVersion.getCampanaProductoId());
				ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(campanaProducto.getProductoClienteId());
				campanaProductoProductoClienteMap.put(campanaProducto.getId(), productoCliente);
			}
			Iterator campanaProductoProductoClienteMapIt = campanaProductoProductoClienteMap.keySet().iterator();
			while(campanaProductoProductoClienteMapIt.hasNext()){
				Long campanaProductoId = (Long)campanaProductoProductoClienteMapIt.next();
				ArrayList<CampanaProductoVersionIf> campanaProductoVersiones = (ArrayList<CampanaProductoVersionIf> )SessionServiceLocator.getCampanaProductoVersionSessionService().findCampanaProductoVersionByCampanaProductoId(campanaProductoId);
				ProductoClienteIf productoCliente = campanaProductoProductoClienteMap.get(campanaProductoId);
				if(mapaProductosClienteListVersion.get(productoCliente) == null){
					mapaProductosClienteListVersion.put(productoCliente, campanaProductoVersiones);
				}else{
					campanaProductoVersiones.addAll(mapaProductosClienteListVersion.get(productoCliente));
					mapaProductosClienteListVersion.put(productoCliente, campanaProductoVersiones);
				}
			}
			// Se obtiene todas las Ordenes de Medio del PlanMedio a ser Pedido
			// o Facturado
			Collection listaOrdenMedio = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planMedioIf.getId());
			for(ProductoClienteIf proc : mapaProductosClienteListVersion.keySet()){ 
				long idProductoCliente = proc.getId();
				Map<Long,Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>> mapVersionOrdenesMedioDetallesMapaList = new LinkedHashMap<Long, Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>>();
				for (CampanaProductoVersionIf version : mapaProductosClienteListVersion.get(proc)){
				
					ArrayList<OrdenMedioIf> listaOrdenMedioTmp = new ArrayList<OrdenMedioIf>(); 
					Iterator listaOrdenMedioIt = listaOrdenMedio.iterator();
					// Se instancia el Mapa de las Ordenes de Medio del
					// ProductoCliente con sus OrdenMedioDetalle y con sus
					// OrdenMedioDetalleMapa
					Map <OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioTotales = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
					// Se recorre la lista de Ordenes de Medio
					while(listaOrdenMedioIt.hasNext()){
						OrdenMedioIf ordenMedioIf = (OrdenMedioIf)listaOrdenMedioIt.next();
						// agregar la orden de medio o no
						boolean agregar = false;
						// Se instancia el mapa de las Ordenes Medio Detalle con
						// sus respectivas Ordenes Medio Detalle Mapa
						Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleTotales = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
						// Se obtiene una colleccion de OrdenMedioDetalle de la
						// OrdenMedio
						Collection<OrdenMedioDetalleIf> listaOrdenMedioDetalleIf = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
						Iterator ordenMedioDetalleIt = listaOrdenMedioDetalleIf.iterator();
						// PARA ORDENES DE MEDIO X PRODUCTO
						if (planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) !=0 ){ 
							if (ordenMedioIf.getProductoClienteId().compareTo(proc.getId())==0){
								while(ordenMedioDetalleIt.hasNext()){
									OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
									Collection<OrdenMedioDetalleMapaIf> listaOrdenMedioDetalleMapaIf = SessionServiceLocator.getOrdenMedioDetalleMapaSessionService().findOrdenMedioDetalleMapaByOrdenMedioDetalleId(ordenMedioDetalleIf.getId());
									Iterator ordenMedioDetalleMapaIt = listaOrdenMedioDetalleMapaIf.iterator();
									
									// Se instancia el arraylist de las Ordenes
									// Medio Detalle Mapa
									ArrayList<OrdenMedioDetalleMapaIf> ordenesMedioDetalleMapaTotales = new ArrayList<OrdenMedioDetalleMapaIf>();		
									
									if(ordenMedioDetalleIf.getCampanaProductoVersionId().compareTo(version.getId()) == 0){
										while(ordenMedioDetalleMapaIt.hasNext()){
											OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf = (OrdenMedioDetalleMapaIf)ordenMedioDetalleMapaIt.next();
											ordenesMedioDetalleMapaTotales.add(ordenMedioDetalleMapaIf);
										}
										// agrega la ordenMedio com con su
										// respectiva lista de Ordenes de Medio
										// Detalle Totales
										ordenesMedioDetalleTotales.put(ordenMedioDetalleIf,ordenesMedioDetalleMapaTotales);
										agregar = true;
									}
								}
								if (agregar){
									// agrega la ordenMedio com con su
									// respectiva lista de Ordenes de Medio
									// Detalle Totales
									ordenesMedioTotales.put(ordenMedioIf,ordenesMedioDetalleTotales);
								}
							}							
							
						}else{
						// PARA ORDENES DE MEDIO X CANAL O PROVEEDOR
							while(ordenMedioDetalleIt.hasNext()){
								OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
								
								if (ordenMedioDetalleIf.getProductoClienteId().compareTo(proc.getId()) == 0 && ordenMedioDetalleIf.getCampanaProductoVersionId().compareTo(version.getId()) == 0	){
									
									Collection<OrdenMedioDetalleMapaIf> listaOrdenMedioDetalleMapaIf = SessionServiceLocator.getOrdenMedioDetalleMapaSessionService().findOrdenMedioDetalleMapaByOrdenMedioDetalleId(ordenMedioDetalleIf.getId()); 
									Iterator ordenMedioDetalleMapaIt = listaOrdenMedioDetalleMapaIf.iterator();
									// Se instancia el arraylist de las Ordenes
									// Medio Detalle Mapa
									ArrayList<OrdenMedioDetalleMapaIf> ordenesMedioDetalleMapaTotales = new ArrayList<OrdenMedioDetalleMapaIf>();	
									while(ordenMedioDetalleMapaIt.hasNext()){
										// Se obtiene la siguiente
										// OrdenMedioDetalle
										OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf = (OrdenMedioDetalleMapaIf)ordenMedioDetalleMapaIt.next();
										ordenesMedioDetalleMapaTotales.add(ordenMedioDetalleMapaIf);
									}
									// agrega la ordenMedio com con su
									// respectiva lista de Ordenes de Medio
									// Detalle Totales
									ordenesMedioDetalleTotales.put(ordenMedioDetalleIf,ordenesMedioDetalleMapaTotales);
									agregar = true;
								}
							}
							if (agregar){
								// ESTAS ORDENES DE MEDIO NO TIENEN LOS VALORES
								// SUBTOTALES VERDADEROS XQ CORRESPONDEN A TODOS
								// LOS DATOS Y LO Q SE QUIERE ES POR PRODUCTO 24
								// MAYO GIOMY!!!!
								// agrega la ordenMedio com con su respectiva
								// lista de Ordenes de Medio Detalle Totales
								ordenesMedioTotales.put(ordenMedioIf,ordenesMedioDetalleTotales);
							}
							
						}
					}
					mapVersionOrdenesMedioDetallesMapaList.put(version.getId(), ordenesMedioTotales);
					
				}
				// agrega el comercial com con su respectiva lista de Ordenes de
				// Medio Totales
				mapaProductosClienteVersionesOrdenMedioDetalleMapa.put(proc,mapVersionOrdenesMedioDetallesMapaList);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		// mapa que contiene a todos los comerciales con sus respectivas ordenes
		// de Medio Totales
		return mapaProductosClienteVersionesOrdenMedioDetalleMapa;
	}*/
			
		
	public void cargarDetallesPlanMedioByVersion(ProductoClienteIf pro, CampanaProductoVersionIf version, ArrayList<PlanMedioFacturacionIf>listPlanMedioFacturacion, ArrayList<String> listaProveedores,Date periodoFechaInicio, Date periodoFechaFin) {
		ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacionPorPeriodo = new ArrayList<PlanMedioFacturacionIf>();
		try {
			ArrayList<OrdenMedioIf> listaOrdenMedioIf = new ArrayList<OrdenMedioIf>(); // lista
																						// de
																						// ordenes
																						// de
																						// medio
																						// no
																						// facturados
			for(PlanMedioFacturacionIf planMedioFacturacion:listPlanMedioFacturacion){
				for(OrdenMedioIf ordenM :listaOrdenMedioTotal){
					int tmp = 0;
					if(ordenM.getId().compareTo(planMedioFacturacion.getOrdenMedioId())==0){
						// llena la lista con las ordenes de medio aun no
						// facturadas
						// For que verifica que la OrdenMedio No se encuentre en
						// la lista
						for(OrdenMedioIf ordenMedioIf: listaOrdenMedioIf){
							if (ordenMedioIf.getId().compareTo(ordenM.getId())!=0) {
								tmp++;
							}
						}
						if (tmp==listaOrdenMedioIf.size()) {
							listaOrdenMedioIf.add(ordenM);
						}
					}
				}
			}

			TipoDocumentoIf tipoDocumento = (getCmbTipoDocumento().getSelectedItem() != null)?(TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem():null;
			DocumentoIf documento = (getCmbDocumento().getSelectedItem() != null)?(DocumentoIf) getCmbDocumento().getSelectedItem():null;
			// setear los valores del detalle escogido
			// idComercialPlanMedioPedido = com.getId();
			// ADD 12 MAYO
			idProductoClientePlanMedioPedido = pro.getId();
			// ADD 8 SEPTIEMBRE
			versionProductoClientePlanMedioPedido = version;
			modelPedidoDetalle = (DefaultTableModel) getTblPedidoDetalle().getModel();
			ordenesSinFacturar = SpiritConstants.getEmptyCharacter();
			if (tipoDocumento != null && (tipoDocumento.getCodigo().equals("FAC") || tipoDocumento.getCodigo().equals("FAE"))) {
				// cargarDetallesPlanMedios lista de Proveedores con ordnes de
				// medio por facturar
				Map<String,ArrayList<OrdenMedioIf>> listaProveedoresOrdenesMedio = new LinkedHashMap<String, ArrayList<OrdenMedioIf>>();
				for (String proveedorAndProducto : listaProveedores) {
					String[] key = proveedorAndProducto.split("-");
					Long proveedorId = Long.parseLong(key[0]);
					Long productoProveedorId = Long.parseLong(key[1]);
					ArrayList<OrdenMedioIf>listOrdenesMediosByProveedor = new ArrayList<OrdenMedioIf>();
					for (OrdenMedioIf ordenMedioTmp:listaOrdenMedioIf) {
						if (ordenMedioTmp.getProveedorId().compareTo(proveedorId) == 0 && ordenMedioTmp.getProductoProveedorId().compareTo(productoProveedorId) == 0) {
							listOrdenesMediosByProveedor.add(ordenMedioTmp);
						}	
					}
					if (listOrdenesMediosByProveedor.size() > 0) {
						listaProveedoresOrdenesMedio.put(String.valueOf(proveedorId) + "-" + String.valueOf(productoProveedorId),listOrdenesMediosByProveedor);
					}
				}

				Iterator listaProveedoresOrdenesMedioIt = listaProveedoresOrdenesMedio.keySet().iterator();
				while(listaProveedoresOrdenesMedioIt.hasNext()){
					String[] key = ((String) listaProveedoresOrdenesMedioIt.next()).split("-");
					Long proveedorId = Long.parseLong(key[0]);
					Long productoId = Long.parseLong(key[1]);
					ClienteOficinaIf clienteOficinaIf2 = mapaClienteOficina.get(proveedorId);
					Vector<String> filaPlantillaDetalle = new Vector<String>();
					PedidoDetalleData data = new PedidoDetalleData();
					// Lista de Ordenes de Medio x Cliente Proveedor
					ArrayList<OrdenMedioIf> listaOrdenMedio = (ArrayList<OrdenMedioIf>)listaProveedoresOrdenesMedio.get(String.valueOf(proveedorId) + "-" + String.valueOf(productoId));
					// listaOrdenMedioSegunPeriodo -> Lista de Ordenes de Medio
					// segun el periodo establecido para facturar
					Map<OrdenMedioIf,Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>> mapOrdenMedioSegunPeriodo;
					Double valorSubtotal = 0D;
					Double porcentajeDescuento = 0D;
					Double porcentajeDescuentosVarios = 0D;
					Double iva = 0D;
					Double comisionAgencia = 0D;
					Double descuentosVarios = 0D;
					Double descuento = 0D;
					Double valor = 0D;
					Long productoProveedorId = new Long(0);
					
					//mapOrdenMedioSegunPeriodo = getOrdenesMedioSegunPeriodoAFacturar(listaOrdenMedio,listPlanMedioFacturacion,periodoFechaInicio,periodoFechaFin);
					mapOrdenMedioSegunPeriodo = SessionServiceLocator.getPlanMedioSessionService().getOrdenesMedioSegunPeriodoAFacturar(listaOrdenMedio,listPlanMedioFacturacion,periodoFechaInicio,periodoFechaFin);
					
					BigDecimal porcentaje = new BigDecimal(0);
					String facturasProveedor = SpiritConstants.getEmptyCharacter();
					Iterator ordenMedioIt = mapOrdenMedioSegunPeriodo.keySet().iterator();
					// START WHILE DE ORDENES DE MEDIO por Cliente Proveedor
					// Segun Periodo
					while (ordenMedioIt.hasNext()) {
						OrdenMedioIf ordenMedioIf  = (OrdenMedioIf)ordenMedioIt.next();
						// mapa de Orden Medio Detalle con Ordenes Medio Detalle
						// Mapa
						Map mapaOrdenesMedioDetalleTotal = (Map)mapOrdenMedioSegunPeriodo.get(ordenMedioIf);
						Iterator ordenMedioDetalleIt = mapaOrdenesMedioDetalleTotal.keySet().iterator();
						BigDecimal porcentaje100 = new BigDecimal(100);
						BigDecimal porcentajeCliente = porcentaje100.subtract(ordenMedioIf.getPorcentajeCanje());
						Double valorSubtotal100OrdenxPeriodo  = 0D;
						Collection productoClienteIfColl = SessionServiceLocator.getProductoClienteSessionService().findProductoClienteById(ordenMedioIf.getProductoClienteId());
						Iterator productoClienteIfCollIt = productoClienteIfColl.iterator();
						if (planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) != 0) {
							ProductoClienteIf productoClienteIf = (ProductoClienteIf)productoClienteIfCollIt.next(); 
							// START WHILE DE ORDENES DE MEDIO DETALLE
							while(ordenMedioDetalleIt.hasNext()){
								OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
								Long productoClienteVersionId = ordenMedioDetalleIf.getCampanaProductoVersionId();
								Double valorSubTotal100DetallexPeriodo = 0D;		
								if(ordenMedioDetalleIf.getCampanaProductoVersionId().compareTo(version.getId()) == 0){
									ArrayList<OrdenMedioDetalleMapaIf> listOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>) mapaOrdenesMedioDetalleTotal.get(ordenMedioDetalleIf);
									// START FOR ORDEN MEDIO DETALLE DATA
									for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf: listOrdenMedioDetalleMapa){
										// Se Crea un PlanMedioFacturacion para
										// setearle los valores y agregarlo a la
										// lista
										PlanMedioFacturacionIf planMedioFacturacionIf = crearPlanMedioFacturacionIf(periodoFechaInicio,periodoFechaFin,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,productoClienteVersionId,false,porcentaje);
										listPlanMedioFacturacionPorPeriodo.add(planMedioFacturacionIf);
										BigDecimal cantidadCunias = new BigDecimal(ordenMedioDetalleMapaIf.getFrecuencia());
										BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(cantidadCunias);
										valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
									}									
								}
								
								
								//BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(BigDecimal.valueOf(ordenMedioDetalleIf.getTotalCunias()));
								//valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
								
								
								valorSubtotal100OrdenxPeriodo = valorSubtotal100OrdenxPeriodo + valorSubTotal100DetallexPeriodo.doubleValue();
							}
						} else { // CUANDO LAS ORDENES DE MEDIO SON AGRUPADAS
									// POR CANAL
							// START WHILE DE ORDENES DE MEDIO DETALLE AGRUPADAS
							// FOR CANAL
							while(ordenMedioDetalleIt.hasNext()){
								OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
								Double valorSubTotal100DetallexPeriodo = 0D;		
								if (ordenMedioDetalleIf.getProductoClienteId().compareTo(pro.getId()) == 0 && ordenMedioDetalleIf.getCampanaProductoVersionId().compareTo(version.getId()) == 0) {
									ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(ordenMedioDetalleIf.getProductoClienteId());
									Long productoClienteVersionId = ordenMedioDetalleIf.getCampanaProductoVersionId();
									ArrayList<OrdenMedioDetalleMapaIf> listOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>) mapaOrdenesMedioDetalleTotal.get(ordenMedioDetalleIf);
									// START FOR ORDEN MEDIO DETALLE DATA
									for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf: listOrdenMedioDetalleMapa){
										// Se Crea un PlanMedioFacturacion para
										// setearle los valores y agregarlo a la
										// lista
										PlanMedioFacturacionIf planMedioFacturacionIf = crearPlanMedioFacturacionIf(periodoFechaInicio,periodoFechaFin,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,productoClienteVersionId,false,porcentaje);
										listPlanMedioFacturacionPorPeriodo.add(planMedioFacturacionIf);
										BigDecimal cantidadCunias = new BigDecimal(ordenMedioDetalleMapaIf.getFrecuencia());
										BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(cantidadCunias);
										valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
									}
								}
								
								
								
								//BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(BigDecimal.valueOf(ordenMedioDetalleIf.getTotalCunias()));
								//valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
								
								
								valorSubtotal100OrdenxPeriodo = valorSubtotal100OrdenxPeriodo + valorSubTotal100DetallexPeriodo.doubleValue();
							}
						}

						Double valorSubtotalCanje = (valorSubtotal100OrdenxPeriodo*porcentajeCliente.doubleValue())/100;
						// Si es factura del exterior y viene de un plan de
						// medios le sumo el IVA al subtotal
						// ya que va sin iva la factura.
						if (tipoDocumento.getCodigo().equals("FAE")) {
							Double ivaAgregado = 1D + (Parametros.IVA / 100); 
							valorSubtotalCanje = valorSubtotalCanje * ivaAgregado;
							valorSubtotal = valorSubtotal + valorSubtotalCanje;
						} else {
							valorSubtotal = valorSubtotal + valorSubtotalCanje;
						}

						porcentajeDescuento = ordenMedioIf.getPorcentajeDescuentoVenta().doubleValue();
						Double valorDescuento100 = valorSubtotalCanje * (porcentajeDescuento / 100);
						descuento = descuento + valorDescuento100;

						porcentajeDescuentosVarios = ordenMedioIf.getPorcentajeBonificacionVenta().doubleValue();
						Double valorBonificacion100 = (valorSubtotalCanje - valorDescuento100) * (porcentajeDescuentosVarios / 100);
						descuentosVarios = descuentosVarios + valorBonificacion100;

						Double valorComision100 = (valorSubtotalCanje - valorDescuento100 - valorBonificacion100) * (ordenMedioIf.getPorcentajeComisionAgencia().doubleValue() / 100);
						comisionAgencia = comisionAgencia + valorComision100;

						productoProveedorId = ordenMedioIf.getProductoProveedorId();
						porcentajeComisionAgencia = ordenMedioIf.getPorcentajeComisionAgencia().doubleValue();
						Map queryMap = new HashMap();
						queryMap.put("tipoOrden", "OM");
						queryMap.put("ordenId", ordenMedioIf.getId());
						String facturasAsociadas = getComprasAsociadasPorOrden(queryMap);
						if (facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
							ordenesSinFacturar += ordenMedioIf.getCodigo() + "\n";
						if (facturasProveedor.contains(facturasAsociadas))
							facturasAsociadas = SpiritConstants.getEmptyCharacter();
						if (!facturasProveedor.equals(SpiritConstants.getEmptyCharacter()) && !facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
							facturasProveedor += ", ";
						facturasProveedor += facturasAsociadas;
					}

					valor = valorSubtotal - descuento - descuentosVarios + comisionAgencia;
					filaPlantillaDetalle.add(clienteOficinaIf2.getDescripcion());
					filaPlantillaDetalle.add("");
					filaPlantillaDetalle.add(formatoDecimal.format(valorSubtotal));
					filaPlantillaDetalle.add("0%");
					filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuento)+"%");

					if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N")) {
						if (documento == null || (documento != null && !documento.getBonificacion().equals(OPCION_SI)))
							iva = (valor.doubleValue()*(IVA));
						ivaTotal = ivaTotal + iva;
						filaPlantillaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
					} else {
						iva = (valor*IVA_CERO)/100;
						valor = valor + iva;
						filaPlantillaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");
					}

					filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuentosVarios)+"%");

					if (valorSubtotal > 0D) {
						data.setCantidad(new BigDecimal(0));
						data.setCantidadpedida(new BigDecimal(1));
						data.setDescripcion(clienteOficinaIf2.getDescripcion());
						data.setPrecio(BigDecimal.valueOf(valorSubtotal));
						data.setPrecioreal(BigDecimal.valueOf(valorSubtotal));
						data.setIce(new BigDecimal(0));
						data.setOtroimpuesto(new BigDecimal(0));
						data.setDescuento(BigDecimal.valueOf(descuento));
						data.setPorcentajeDescuentosVarios(BigDecimal.valueOf(porcentajeDescuentosVarios));
						data.setDescuentoGlobal(new BigDecimal(0));
						data.setProductoId(productoProveedorId);
						data.setValor(BigDecimal.valueOf(valor));
						data.setComprasReembolsoAsociadas(facturasProveedor);
						ProductoIf producto = mapaProductoProveedor.get(productoProveedorId);
						GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
						if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N"))
							data.setIva(BigDecimal.valueOf(iva));
						else
							data.setIva(BigDecimal.valueOf(0.0));
						// Para que no se tenga que actualizar el documento uno
						// a uno en los detalles
						documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipoDocumentoId(tipoDocumentoIf.getId()).iterator().next();
						data.setDocumentoId((documento!=null)?documento.getId():null);
						pedidoDetalleColeccion.add(data);
						modelPedidoDetalle.addRow(filaPlantillaDetalle);
						subTotal = subTotal + valorSubtotal;
						if (generico.getCobraIvaCliente().equals("N"))
							totalIvaCero += valorSubtotal;
						descuentoTotal = descuentoTotal + descuento;
						descuentosVariosTotal = descuentosVariosTotal + descuentosVarios;
						comisionAgenciaTotal = comisionAgenciaTotal + comisionAgencia;
						if (tipoDocumento.getReembolso().equals("N"))
							total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal + ivaTotal;
						else
							total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal;

						// cargandoDetallesReferencia = true;

						cargandoDetallesReferencia = false;
					}
				}
				// agrega la lista de PlanesMedioFacturacion
				listaPlanMedioFacturacionEscogido = listPlanMedioFacturacionPorPeriodo;
				// creo que aki deberia mostrar el mensaje de que no existe
				// datos para presentar en esas fechas 20 ABRIL
				if (subTotal <= 0D){
					SpiritAlert.createAlert("No existen datos para mostrar en ese Periodo en esta Forma de Facturación!!!", SpiritAlert.INFORMATION);
				}
				getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
				if (tipoDocumento.getReembolso().equals("N")) {
					getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
				} else {
					getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAFinal().setText("0");
				}
				getTxtICEFinal().setText("0");
				getTxtOtroImpuestoFinal().setText("0");
				getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
				getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));				
				getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
				getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
				/*
				 * if (subTotal > 0D &&
				 * !ordenesSinFacturar.equals(SpiritConstants.getEmptyCharacter()))
				 * SpiritAlert.createAlert("Se han cargado los detalles del
				 * presupuesto correctamente, aunque se presentó un problema\nal
				 * cargar datos de las facturas de proveedores asociadas.\n\nLas
				 * facturas de las siguientes órdenes no han sido ingresadas al
				 * sistema:\n\n" + ordenesSinFacturar, SpiritAlert.WARNING);
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cargarDetallesPlanMedioByProductoComercial(ProductoClienteIf pro, ArrayList<PlanMedioFacturacionIf>listPlanMedioFacturacion, ArrayList<ClienteOficinaIf> listaProveedores,Date periodoFechaInicio, Date periodoFechaFin){
		
		ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacionPorPeriodo = new ArrayList<PlanMedioFacturacionIf>();
			
		try {
			ArrayList<OrdenMedioIf> listaOrdenMedioIf = new ArrayList<OrdenMedioIf>(); // lista
																						// de
																						// ordenes
																						// de
																						// medio
																						// no
																						// facturados
			for(PlanMedioFacturacionIf planMedioFacturacion:listPlanMedioFacturacion){
				for(OrdenMedioIf ordenM :listaOrdenMedioTotal){
					int tmp = 0;
					if(ordenM.getId().compareTo(planMedioFacturacion.getOrdenMedioId())==0){
						
						// llena la lista con las ordenes de medio aun no
						// facturadas
						// For que verifica que la OrdenMedio No se encuentre en
						// la lista
						for(OrdenMedioIf ordenMedioIf: listaOrdenMedioIf){
							if(ordenMedioIf.getId().compareTo(ordenM.getId())!=0){
								tmp++;
							}
						}
						if(tmp==listaOrdenMedioIf.size()){
							listaOrdenMedioIf.add(ordenM);
						}
					}
				}
			}
		
			TipoDocumentoIf tipoDocumento = (getCmbTipoDocumento().getSelectedItem() != null)?(TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem():null;
			DocumentoIf documento = (getCmbDocumento().getSelectedItem() != null)?(DocumentoIf) getCmbDocumento().getSelectedItem():null;
						
			idProductoClientePlanMedioPedido = pro.getId();
			
			modelPedidoDetalle = (DefaultTableModel) getTblPedidoDetalle().getModel();
			ordenesSinFacturar = SpiritConstants.getEmptyCharacter();
			if (tipoDocumento != null && (tipoDocumento.getCodigo().equals("FAC") || tipoDocumento.getCodigo().equals("FAE"))) {
				
				// cargarDetallesPlanMedios
				// lista de Proveedores con ordnes de medio por facturar
				Map<ClienteOficinaIf,ArrayList<OrdenMedioIf>> listaProveedoresOrdenesMedio = new LinkedHashMap<ClienteOficinaIf, ArrayList<OrdenMedioIf>>();
				for(ClienteOficinaIf proveedor : listaProveedores){
					ArrayList<OrdenMedioIf>listOrdenesMediosByProveedor = new ArrayList<OrdenMedioIf>();
					for(OrdenMedioIf ordenMedioTmp:listaOrdenMedioIf){
						if(ordenMedioTmp.getProveedorId().compareTo(proveedor.getId())==0){
							listOrdenesMediosByProveedor.add(ordenMedioTmp);
						}	
					}
					if(listOrdenesMediosByProveedor.size()>0){
						listaProveedoresOrdenesMedio.put(proveedor,listOrdenesMediosByProveedor);
					}
				}
				
				Iterator listaProveedoresOrdenesMedioIt = listaProveedoresOrdenesMedio.keySet().iterator();
				while(listaProveedoresOrdenesMedioIt.hasNext()){
					ClienteOficinaIf clienteOficinaIf2 = (ClienteOficinaIf)listaProveedoresOrdenesMedioIt.next();
					Vector<String> filaPlantillaDetalle = new Vector<String>();
					PedidoDetalleData data = new PedidoDetalleData();
					// Lista de Ordenes de Medio x Cliente Proveedor
					ArrayList<OrdenMedioIf> listaOrdenMedio = (ArrayList<OrdenMedioIf>)listaProveedoresOrdenesMedio.get(clienteOficinaIf2);
					// listaOrdenMedioSegunPeriodo -> Lista de Ordenes de Medio
					// segun el periodo establecido para facturar
					Map<OrdenMedioIf,Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>> mapOrdenMedioSegunPeriodo;
					
					Double valorSubtotal = 0D;
					double porcentajeDescuento = 0D;
					double porcentajeDescuentosVarios = 0D;
					Double iva = 0D;
					Double comisionAgencia = 0D;
					Double descuentosVarios = 0D;
					Double descuento = 0D;
					Double valor = 0D;
					Long productoProveedorId = new Long(0);
					
					
					//mapOrdenMedioSegunPeriodo = getOrdenesMedioSegunPeriodoAFacturar(listaOrdenMedio,listPlanMedioFacturacion,periodoFechaInicio,periodoFechaFin);
					mapOrdenMedioSegunPeriodo = SessionServiceLocator.getPlanMedioSessionService().getOrdenesMedioSegunPeriodoAFacturar(listaOrdenMedio,listPlanMedioFacturacion,periodoFechaInicio,periodoFechaFin);
					
					BigDecimal porcentaje = new BigDecimal(0);
					Double valorSubtotal100OrdenxPeriodo  = 0D;
					Iterator ordenMedioIt = mapOrdenMedioSegunPeriodo.keySet().iterator();
					String facturasProveedor = SpiritConstants.getEmptyCharacter();
					// START WHILE DE ORDENES DE MEDIO por Cliente Proveedor
					// Segun Periodo
					while(ordenMedioIt.hasNext()){
						OrdenMedioIf ordenMedioIf  = (OrdenMedioIf)ordenMedioIt.next();
							
						// mapa de Orden Medio Detalle con Ordenes Medio Detalle
						// Mapa
						Map mapaOrdenesMedioDetalleTotal = (Map)mapOrdenMedioSegunPeriodo.get(ordenMedioIf);
						Iterator ordenMedioDetalleIt = mapaOrdenesMedioDetalleTotal.keySet().iterator();
														
						BigDecimal porcentaje100 = new BigDecimal(100);
						BigDecimal porcentajeCliente = porcentaje100.subtract(ordenMedioIf.getPorcentajeCanje());
												
						Collection productoClienteIfColl = SessionServiceLocator.getProductoClienteSessionService().findProductoClienteById(ordenMedioIf.getProductoClienteId());
						Iterator productoClienteIfCollIt = productoClienteIfColl.iterator();
						
						if (planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) != 0){
							ProductoClienteIf productoClienteIf = (ProductoClienteIf)productoClienteIfCollIt.next(); 
						
							// START WHILE DE ORDENES DE MEDIO DETALLE
							while(ordenMedioDetalleIt.hasNext()){
								OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
								Double valorSubTotal100DetallexPeriodo = 0D;		
							
								ArrayList<OrdenMedioDetalleMapaIf> listOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>) mapaOrdenesMedioDetalleTotal.get(ordenMedioDetalleIf);
								
								// START FOR ORDEN MEDIO DETALLE DATA
								for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf: listOrdenMedioDetalleMapa){
									// Se Crea un PlanMedioFacturacion para
									// setearle los valores y agregarlo a la
									// lista
									PlanMedioFacturacionIf planMedioFacturacionIf = crearPlanMedioFacturacionIf(periodoFechaInicio,periodoFechaFin,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,null,false,porcentaje);
									
									listPlanMedioFacturacionPorPeriodo.add(planMedioFacturacionIf);
									BigDecimal cantidadCunias = new BigDecimal(ordenMedioDetalleMapaIf.getFrecuencia());
									BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(cantidadCunias);
									valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
								}
								valorSubtotal100OrdenxPeriodo = valorSubtotal100OrdenxPeriodo + valorSubTotal100DetallexPeriodo.doubleValue();
							}
						}else{// CUANDO LAS ORDENES DE MEDIO SON AGRUPADAS POR
								// CANAL
							
							// START WHILE DE ORDENES DE MEDIO DETALLE AGRUPADAS
							// FOR CANAL
								while(ordenMedioDetalleIt.hasNext()){
									OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
									Double valorSubTotal100DetallexPeriodo = 0D;		
									
									if (ordenMedioDetalleIf.getProductoClienteId().compareTo(pro.getId()) == 0){
										ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(ordenMedioDetalleIf.getProductoClienteId());
										
										ArrayList<OrdenMedioDetalleMapaIf> listOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>) mapaOrdenesMedioDetalleTotal.get(ordenMedioDetalleIf);
										
										// START FOR ORDEN MEDIO DETALLE DATA
										for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf: listOrdenMedioDetalleMapa){
											// Se Crea un PlanMedioFacturacion
											// para setearle los valores y
											// agregarlo a la lista
											PlanMedioFacturacionIf planMedioFacturacionIf = crearPlanMedioFacturacionIf(periodoFechaInicio,periodoFechaFin,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,null,false,porcentaje);
											
											listPlanMedioFacturacionPorPeriodo.add(planMedioFacturacionIf);
											BigDecimal cantidadCunias = new BigDecimal(ordenMedioDetalleMapaIf.getFrecuencia());
											BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(cantidadCunias);
											valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
										}
									}
									valorSubtotal100OrdenxPeriodo = valorSubtotal100OrdenxPeriodo + valorSubTotal100DetallexPeriodo.doubleValue();
								}
						}
											
						Double valorSubtotalCanje = (valorSubtotal100OrdenxPeriodo*porcentajeCliente.doubleValue())/100;
						valorSubtotal = valorSubtotal + valorSubtotalCanje;
							
						porcentajeDescuento = ordenMedioIf.getPorcentajeDescuentoVenta().doubleValue();
						Double valorDescuento100 = ordenMedioIf.getValorDescuentoVenta().doubleValue();
						Double valorDescuentoCanje = (valorDescuento100*porcentajeCliente.doubleValue())/100;
						descuento = descuento + valorDescuentoCanje;
										
						porcentajeDescuentosVarios = ordenMedioIf.getPorcentajeBonificacionVenta().doubleValue();
						Double valorBonificacion100 = (valorSubtotalCanje - valorDescuentoCanje) * (porcentajeDescuentosVarios / 100);
						descuentosVarios = descuentosVarios + valorBonificacion100;
						
						Double valorComision100 = ordenMedioIf.getValorComisionAgencia().doubleValue();
						Double valorComisionCanje = (valorComision100*porcentajeCliente.doubleValue())/100;
						comisionAgencia = comisionAgencia + valorComisionCanje;
													
						productoProveedorId = ordenMedioIf.getProductoProveedorId();
						porcentajeComisionAgencia = ordenMedioIf.getPorcentajeComisionAgencia().doubleValue();
						Map queryMap = new HashMap();
						queryMap.put("tipoOrden", "OM");
						queryMap.put("ordenId", ordenMedioIf.getId());
						String facturasAsociadas = getComprasAsociadasPorOrden(queryMap);
						if (facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
							ordenesSinFacturar += ordenMedioIf.getCodigo() + "\n";
						if (facturasProveedor.contains(facturasAsociadas))
							facturasAsociadas = SpiritConstants.getEmptyCharacter();
						if (!facturasProveedor.equals(SpiritConstants.getEmptyCharacter()) && !facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
							facturasProveedor += ", ";
						facturasProveedor += facturasAsociadas;
					}
					
					valor = valorSubtotal - descuento - descuentosVarios + comisionAgencia;
					filaPlantillaDetalle.add(clienteOficinaIf2.getDescripcion());
					filaPlantillaDetalle.add("");
					filaPlantillaDetalle.add(formatoDecimal.format(valorSubtotal));
					filaPlantillaDetalle.add("0%");
					filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuento)+"%");
					
					if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N")) {
						if (documento == null || (documento != null && !documento.getBonificacion().equals(OPCION_SI)))
							iva = (valor.doubleValue()*(IVA));
							ivaTotal = ivaTotal + iva;
						filaPlantillaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
					} else {
						iva = (valor*IVA_CERO)/100;
						valor = valor + iva;
						filaPlantillaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");
					}
					
					filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuentosVarios)+"%");

					if (valorSubtotal > 0D) {
						data.setCantidad(new BigDecimal(0));
						data.setCantidadpedida(new BigDecimal(1));
						data.setDescripcion(clienteOficinaIf2.getDescripcion());
						data.setPrecio(BigDecimal.valueOf(valorSubtotal));
						data.setPrecioreal(BigDecimal.valueOf(valorSubtotal));
						data.setIce(new BigDecimal(0));
						data.setOtroimpuesto(new BigDecimal(0));
						data.setDescuento(BigDecimal.valueOf(descuento));
						data.setPorcentajeDescuentosVarios(BigDecimal.valueOf(porcentajeDescuentosVarios));
						data.setDescuentoGlobal(new BigDecimal(0));
						data.setProductoId(productoProveedorId);
						data.setValor(BigDecimal.valueOf(valor));
						data.setComprasReembolsoAsociadas(facturasProveedor);
						
						ProductoIf producto = mapaProductoProveedor.get(productoProveedorId);
						GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
	
						if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N"))
							data.setIva(BigDecimal.valueOf(iva));
						else
							data.setIva(BigDecimal.valueOf(0.0));
										
						// Para que no se tenga que actualizar el documento uno
						// a uno en los detalles
						documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipoDocumentoId(tipoDocumentoIf.getId()).iterator().next();
						data.setDocumentoId((documento!=null)?documento.getId():null);
						
						pedidoDetalleColeccion.add(data);
						modelPedidoDetalle.addRow(filaPlantillaDetalle);
						subTotal = subTotal + valorSubtotal;
						
						if (generico.getCobraIvaCliente().equals("N"))
							totalIvaCero += valorSubtotal;

						descuentoTotal = descuentoTotal + descuento;
						descuentosVariosTotal = descuentosVariosTotal + descuentosVarios;
						comisionAgenciaTotal = comisionAgenciaTotal + comisionAgencia;
						
						if (tipoDocumento.getReembolso().equals("N"))
							total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal + ivaTotal;
						else
							total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal;
						
						// cargandoDetallesReferencia = true;
						cargandoDetallesReferencia = false;
					}
					
				}
				// agrega la lista de PlanesMedioFacturacion
				listaPlanMedioFacturacionEscogido = listPlanMedioFacturacionPorPeriodo;
				
				// creo que aki deberia mostrar el mensaje de que no existe
				// datos para presentar en esas fechas 20 ABRIL
				if (subTotal <= 0D){
					SpiritAlert.createAlert("No existen datos para mostrar en ese Periodo en esta Forma de Facturación!!!", SpiritAlert.INFORMATION);
				}

				getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
				
				if (tipoDocumento.getReembolso().equals("N")) {
					getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
				} else {
					getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAFinal().setText("0");
				}
				
				getTxtICEFinal().setText("0");
				getTxtOtroImpuestoFinal().setText("0");
				getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
				getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));
				getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
				getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
				/*
				 * if (subTotal > 0D &&
				 * !ordenesSinFacturar.equals(SpiritConstants.getEmptyCharacter()))
				 * SpiritAlert.createAlert("Se han cargado los detalles del
				 * presupuesto correctamente, aunque se presentó un problema\nal
				 * cargar datos de las facturas de proveedores asociadas.\n\nLas
				 * facturas de las siguientes órdenes no han sido ingresadas al
				 * sistema:\n\n" + ordenesSinFacturar, SpiritAlert.WARNING);
				 */
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void cargarDetallesPlanMedioByProducto(ProductoClienteIf pro, ArrayList<PlanMedioFacturacionIf>listPlanMedioFacturacion, ArrayList<String> listaProveedores,Date periodoFechaInicio, Date periodoFechaFin){
		ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacionPorPeriodo = new ArrayList<PlanMedioFacturacionIf>();
		try {
			// lista de ordenes de medio no facturados
			ArrayList<OrdenMedioIf> listaOrdenMedioIf = new ArrayList<OrdenMedioIf>(); 
			for (PlanMedioFacturacionIf planMedioFacturacion:listPlanMedioFacturacion) {
				for (OrdenMedioIf ordenM :listaOrdenMedioTotal) {
					int tmp = 0;
					if (ordenM.getId().compareTo(planMedioFacturacion.getOrdenMedioId()) == 0) {
						
						for(OrdenMedioIf ordenMedioIf: listaOrdenMedioIf){
							if(ordenMedioIf.getId().compareTo(ordenM.getId())!=0){
								tmp++;
							}
						}
						if (tmp==listaOrdenMedioIf.size()) {
							listaOrdenMedioIf.add(ordenM);
						}
					}
				}
			}		
			TipoDocumentoIf tipoDocumento = (getCmbTipoDocumento().getSelectedItem() != null)?(TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem():null;
			DocumentoIf documento = (getCmbDocumento().getSelectedItem() != null)?(DocumentoIf) getCmbDocumento().getSelectedItem():null;
			// setear los valores del detalle escogido
			idProductoClientePlanMedioPedido = pro.getId();
			modelPedidoDetalle = (DefaultTableModel) getTblPedidoDetalle().getModel();
			ordenesSinFacturar = SpiritConstants.getEmptyCharacter();
			if (tipoDocumento != null && (tipoDocumento.getCodigo().equals("FAC") || tipoDocumento.getCodigo().equals("FAE"))) {
				// cargarDetallesPlanMedios
				// lista de Proveedores con ordnes de medio por facturar
				Map<String,ArrayList<OrdenMedioIf>> listaProveedoresOrdenesMedio = new LinkedHashMap<String, ArrayList<OrdenMedioIf>>();
				for (String proveedorAndProducto : listaProveedores) {
					String[] key = proveedorAndProducto.split("-");
					Long proveedorId = Long.parseLong(key[0]);
					Long productoProveedorId = Long.parseLong(key[1]);
					ArrayList<OrdenMedioIf>listOrdenesMediosByProveedor = new ArrayList<OrdenMedioIf>();
					for(OrdenMedioIf ordenMedioTmp:listaOrdenMedioIf){
						if(ordenMedioTmp.getProveedorId().compareTo(proveedorId) == 0 && ordenMedioTmp.getProductoProveedorId().compareTo(productoProveedorId) == 0) {
							listOrdenesMediosByProveedor.add(ordenMedioTmp);
						}	
					}
					if (listOrdenesMediosByProveedor.size() > 0) {
						listaProveedoresOrdenesMedio.put(String.valueOf(proveedorId) + "-" + String.valueOf(productoProveedorId), listOrdenesMediosByProveedor);
					}
				}
				
				Iterator listaProveedoresOrdenesMedioIt = listaProveedoresOrdenesMedio.keySet().iterator();
				while (listaProveedoresOrdenesMedioIt.hasNext()) {
					String[] key = ((String) listaProveedoresOrdenesMedioIt.next()).split("-");
					Long proveedorId = Long.parseLong(key[0]);
					Long productoId = Long.parseLong(key[1]);
					ClienteOficinaIf clienteOficinaIf2 = mapaClienteOficina.get(proveedorId);
					Vector<String> filaPlantillaDetalle = new Vector<String>();
					PedidoDetalleData data = new PedidoDetalleData();
					// Lista de Ordenes de Medio x Cliente Proveedor
					ArrayList<OrdenMedioIf> listaOrdenMedio = (ArrayList<OrdenMedioIf>)listaProveedoresOrdenesMedio.get(String.valueOf(proveedorId) + "-" + String.valueOf(productoId));
					Map<OrdenMedioIf,Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>> mapOrdenMedioSegunPeriodo;
					
					Double valorSubtotal = 0D;
					double porcentajeDescuento = 0D;
					double porcentajeDescuentosVarios = 0D;
					Double iva = 0D;
					Double comisionAgencia = 0D;
					Double descuentosVarios = 0D;
					Double descuento = 0D;
					Double valor = 0D;
					Long productoProveedorId = new Long(0);
					
					// Cliente Proveedor
					//mapOrdenMedioSegunPeriodo = getOrdenesMedioSegunPeriodoAFacturar(listaOrdenMedio,listPlanMedioFacturacion,periodoFechaInicio,periodoFechaFin);
					mapOrdenMedioSegunPeriodo = SessionServiceLocator.getPlanMedioSessionService().getOrdenesMedioSegunPeriodoAFacturar(listaOrdenMedio,listPlanMedioFacturacion,periodoFechaInicio,periodoFechaFin);					
					
					BigDecimal porcentaje = new BigDecimal(0);
					Iterator ordenMedioIt = mapOrdenMedioSegunPeriodo.keySet().iterator();
					String facturasProveedor = SpiritConstants.getEmptyCharacter();
					// START WHILE DE ORDENES DE MEDIO por Cliente Proveedor
					// Segun Periodo
					while(ordenMedioIt.hasNext()){
						OrdenMedioIf ordenMedioIf  = (OrdenMedioIf)ordenMedioIt.next();
						
						// mapa de Orden Medio Detalle con Ordenes Medio Detalle
						// Mapa
						Map mapaOrdenesMedioDetalleTotal = (Map)mapOrdenMedioSegunPeriodo.get(ordenMedioIf);
						Iterator ordenMedioDetalleIt = mapaOrdenesMedioDetalleTotal.keySet().iterator();
														
						BigDecimal porcentaje100 = new BigDecimal(100);
						BigDecimal porcentajeCliente = porcentaje100.subtract(ordenMedioIf.getPorcentajeCanje());
						Double valorSubtotal100OrdenxPeriodo  = 0D;
						
						Collection productoClienteIfColl = SessionServiceLocator.getProductoClienteSessionService().findProductoClienteById(ordenMedioIf.getProductoClienteId());
						Iterator productoClienteIfCollIt = productoClienteIfColl.iterator();
						
						// POR PRODUCTO CLIENTE
						if (planMedioIf.getOrdenMedioTipo().compareTo("P") == 0) {
							
							ProductoClienteIf productoClienteIf = (ProductoClienteIf)productoClienteIfCollIt.next(); 
							
							// START WHILE DE ORDENES DE MEDIO DETALLE
							while(ordenMedioDetalleIt.hasNext()){
								OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
								Double valorSubTotal100DetallexPeriodo = 0D;		
							
								// ArrayList de las Ordenes de Medio Detalle
								// Mapa del Comercial
								ArrayList<OrdenMedioDetalleMapaIf> listOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>) mapaOrdenesMedioDetalleTotal.get(ordenMedioDetalleIf);
														
								// START FOR ORDEN MEDIO DETALLE DATA
								for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf: listOrdenMedioDetalleMapa){
									// Se Crea un PlanMedioFacturacion para
									// setearle los valores y agregarlo a la
									// lista PlanMedioFacturacionIf
									PlanMedioFacturacionIf planMedioFacturacionIf = crearPlanMedioFacturacionIf(periodoFechaInicio,periodoFechaFin,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,false,porcentaje);
									listPlanMedioFacturacionPorPeriodo.add(planMedioFacturacionIf);
									BigDecimal cantidadCunias = new BigDecimal(ordenMedioDetalleMapaIf.getFrecuencia());
									BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(cantidadCunias);
									valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
								}
								
								
								//BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(BigDecimal.valueOf(ordenMedioDetalleIf.getTotalCunias()));
								//valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
								
								
								
								valorSubtotal100OrdenxPeriodo = valorSubtotal100OrdenxPeriodo + valorSubTotal100DetallexPeriodo.doubleValue();
							}
						} else {
							// START WHILE DE ORDENES DE MEDIO DETALLE AGRUPADAS
							// FOR CANAL
							while(ordenMedioDetalleIt.hasNext()){
								OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
								Double valorSubTotal100DetallexPeriodo = 0D;		

								if (ordenMedioDetalleIf.getProductoClienteId().compareTo(pro.getId()) ==0){
									ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(ordenMedioDetalleIf.getProductoClienteId());
									ArrayList<OrdenMedioDetalleMapaIf> listOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>) mapaOrdenesMedioDetalleTotal.get(ordenMedioDetalleIf);

									// START FOR ORDEN MEDIO DETALLE DATA
									for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf: listOrdenMedioDetalleMapa){
										// Se Crea un PlanMedioFacturacion para
										// setearle los valores y agregarlo a la
										// lista PlanMedioFacturacionIf
										PlanMedioFacturacionIf planMedioFacturacionIf = crearPlanMedioFacturacionIf(periodoFechaInicio,periodoFechaFin,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,false,porcentaje);
										listPlanMedioFacturacionPorPeriodo.add(planMedioFacturacionIf);
										BigDecimal cantidadCunias = new BigDecimal(ordenMedioDetalleMapaIf.getFrecuencia());
										BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(cantidadCunias);
										valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
									}
								}
								
								
								//BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(BigDecimal.valueOf(ordenMedioDetalleIf.getTotalCunias()));
								//valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
								
								
								
								valorSubtotal100OrdenxPeriodo = valorSubtotal100OrdenxPeriodo + valorSubTotal100DetallexPeriodo.doubleValue();
							}
						}
										
						Double valorSubtotalCanje = (valorSubtotal100OrdenxPeriodo*porcentajeCliente.doubleValue())/100;
						
						// Si es factura del exterior y viene de un plan de
						// medios le sumo el IVA al subtotal
						// ya que va sin iva la factura.
						if (tipoDocumento.getCodigo().equals("FAE")) {
							Double ivaAgregado = 1D + (Parametros.IVA / 100); 
							valorSubtotalCanje = valorSubtotalCanje * ivaAgregado;
							valorSubtotal = valorSubtotal + valorSubtotalCanje;
						} else {
							valorSubtotal = valorSubtotal + valorSubtotalCanje;
						}
							
						porcentajeDescuento = ordenMedioIf.getPorcentajeDescuentoVenta().doubleValue();
						Double valorDescuento100 = valorSubtotalCanje * (porcentajeDescuento / 100);
						descuento = descuento + valorDescuento100;
							
						porcentajeDescuentosVarios = ordenMedioIf.getPorcentajeBonificacionVenta().doubleValue();
						Double valorBonificacion100 = (valorSubtotalCanje - valorDescuento100) * (porcentajeDescuentosVarios / 100);
						descuentosVarios = descuentosVarios + valorBonificacion100;
												
						Double valorComision100 = (valorSubtotalCanje - valorDescuento100 - valorBonificacion100) * (ordenMedioIf.getPorcentajeComisionAgencia().doubleValue()/100);
						comisionAgencia = comisionAgencia + valorComision100;
													
						productoProveedorId = ordenMedioIf.getProductoProveedorId();
						porcentajeComisionAgencia = ordenMedioIf.getPorcentajeComisionAgencia().doubleValue();
						Map queryMap = new HashMap();
						queryMap.put("tipoOrden", "OM");
						queryMap.put("ordenId", ordenMedioIf.getId());
						String facturasAsociadas = getComprasAsociadasPorOrden(queryMap);
						if (facturasProveedor.contains(facturasAsociadas))
							facturasAsociadas = SpiritConstants.getEmptyCharacter();
						if (!facturasProveedor.equals(SpiritConstants.getEmptyCharacter()) && !facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
							facturasProveedor += ", ";
						facturasProveedor += facturasAsociadas;
					}
					filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuento)+"%");
					
					if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N")) {
						if (documento == null || (documento != null && !documento.getBonificacion().equals(OPCION_SI))){
							double subtotalIva = valorSubtotal - descuento + comisionAgencia;
							// iva = (valor.doubleValue()*(IVA));
							iva = subtotalIva * IVA;
						}
							ivaTotal = ivaTotal + iva;
						filaPlantillaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
					} else {
						iva = (valor*IVA_CERO)/100;
						valor = valor + iva;
						filaPlantillaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");
					}
					
					filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuentosVarios)+"%");

					if (valorSubtotal > 0D) {
						data.setCantidad(new BigDecimal(0));
						data.setCantidadpedida(new BigDecimal(1));
						data.setDescripcion(clienteOficinaIf2.getDescripcion());
						data.setPrecio(BigDecimal.valueOf(valorSubtotal));
						data.setPrecioreal(BigDecimal.valueOf(valorSubtotal));
						data.setIce(new BigDecimal(0));
						data.setOtroimpuesto(new BigDecimal(0));
						data.setDescuento(BigDecimal.valueOf(descuento));
						data.setPorcentajeDescuentosVarios(BigDecimal.valueOf(porcentajeDescuentosVarios));
						data.setDescuentoGlobal(new BigDecimal(0));
						data.setProductoId(productoProveedorId);
						data.setValor(BigDecimal.valueOf(valor));
						data.setComprasReembolsoAsociadas(facturasProveedor);
										
						ProductoIf producto = mapaProductoProveedor.get(productoProveedorId);
						GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
	
						if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N"))
							data.setIva(BigDecimal.valueOf(iva));
						else
							data.setIva(BigDecimal.valueOf(0.0));
										
						// Para que no se tenga que actualizar el documento uno
						// a uno en los detalles
						documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipoDocumentoId(tipoDocumentoIf.getId()).iterator().next();
						data.setDocumentoId((documento!=null)?documento.getId():null);
						
						pedidoDetalleColeccion.add(data);
						modelPedidoDetalle.addRow(filaPlantillaDetalle);
						subTotal = subTotal + valorSubtotal;
						
						if (generico.getCobraIvaCliente().equals("N"))
							totalIvaCero += valorSubtotal;

						descuentoTotal = descuentoTotal + descuento;
						descuentosVariosTotal = descuentosVariosTotal + descuentosVarios;
						comisionAgenciaTotal = comisionAgenciaTotal + comisionAgencia;
						
						if (tipoDocumento.getReembolso().equals("N"))
							total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal + ivaTotal;
						else
							total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal;
						
						// cargandoDetallesReferencia = true;
						cargandoDetallesReferencia = false;
					}
					
				}
				// agrega la lista de PlanesMedioFacturacion
				listaPlanMedioFacturacionEscogido = listPlanMedioFacturacionPorPeriodo;
				
				// creo que aki deberia mostrar el mensaje de que no existe
				// datos para presentar en esas fechas 20 ABRIL
				if (subTotal <= 0D){
					SpiritAlert.createAlert("No existen datos para mostrar en ese Periodo en esta Forma de Facturación!!!", SpiritAlert.INFORMATION);
				}

				getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
				
				if (tipoDocumento.getReembolso().equals("N")) {
					getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
				} else {
					getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAFinal().setText("0");
				}
				
				getTxtICEFinal().setText("0");
				getTxtOtroImpuestoFinal().setText("0");
				getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
				getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));
				getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
				getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
				/*
				 * if (subTotal > 0D &&
				 * !ordenesSinFacturar.equals(SpiritConstants.getEmptyCharacter()))
				 * SpiritAlert.createAlert("Se han cargado los detalles del
				 * presupuesto correctamente, aunque se presentó un problema\nal
				 * cargar datos de las facturas de proveedores asociadas.\n\nLas
				 * facturas de las siguientes órdenes no han sido ingresadas al
				 * sistema:\n\n" + ordenesSinFacturar, SpiritAlert.WARNING);
				 */
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public Map<OrdenMedioIf,Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>  getOrdenesMedioSegunPeriodoAFacturar(ArrayList<OrdenMedioIf> listaOrdenMedio,ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacion,Date periodoFechaInicio, Date periodoFechaFin ) throws GenericBusinessException{
		ArrayList<OrdenMedioIf> listaOrdenMedioSegunPeriodo = new ArrayList<OrdenMedioIf>();
		Map <OrdenMedioIf,Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioTotales = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
		
		// listaOrdenMedio Lista de Ordenes de Medio x Cliente Proveedor
		for(OrdenMedioIf ordenMedioIf: listaOrdenMedio){
			// mapa de OrdenesMedioDetalle con sus respectivas listas de
			// OrdenesMedioDetalleMapa
			Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleTotales = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
			Collection<OrdenMedioDetalleIf>ordenMedioDetalleColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
			Iterator ordenMedioDetalleIt = ordenMedioDetalleColl.iterator();
			
			while(ordenMedioDetalleIt.hasNext()){
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
				ArrayList<OrdenMedioDetalleMapaIf>ordenMedioDetalleMapaList = new ArrayList<OrdenMedioDetalleMapaIf>();
				
				Collection<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaColl = SessionServiceLocator.getOrdenMedioDetalleMapaSessionService().findOrdenMedioDetalleMapaByOrdenMedioDetalleId(ordenMedioDetalleIf.getId());
				Iterator ordenMedioDetalleMapaIt = ordenMedioDetalleMapaColl.iterator();
				while(ordenMedioDetalleMapaIt.hasNext()){
					OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf = (OrdenMedioDetalleMapaIf)ordenMedioDetalleMapaIt.next();
					boolean agregar = false;
					for (PlanMedioFacturacionIf planMedioFacturacionIf: listPlanMedioFacturacion ){
						if (planMedioFacturacionIf.getOrdenMedioId().compareTo(ordenMedioIf.getId()) == 0 
							&& planMedioFacturacionIf.getOrdenMedioDetalleId().compareTo(ordenMedioDetalleIf.getId()) == 0 
							&& planMedioFacturacionIf.getOrdenMedioDetalleMapaId().compareTo(ordenMedioDetalleMapaIf.getId()) == 0){
							agregar = true;
						}
					}
					
					// DIFERENTE DEL DETALLE MAPA DE LISTA DE PLAN MEDIO
					// FACTURACION
					if (agregar){
						Date fechaOrdenMedioDetalleMapa = ordenMedioDetalleMapaIf.getFecha();
						Calendar calendar = GregorianCalendar.getInstance();
						calendar.setTime(fechaOrdenMedioDetalleMapa);
						int dia  = calendar.get(Calendar.DATE);
						int mes  = calendar.get(Calendar.MONTH);
						int anio = calendar.get(Calendar.YEAR);
						
						Calendar calendarFechaInicio =  GregorianCalendar.getInstance();
						calendarFechaInicio.setTime(periodoFechaInicio);
						Calendar calendarFechaFin =  GregorianCalendar.getInstance();
						calendarFechaFin.setTime(periodoFechaFin);
						
						Calendar calendarTemp = GregorianCalendar.getInstance();
						// Setea los datos del periodoFechaInicio pa que si
						// concuerdan
						// no haya conflictos con los datos locales y solo se
						// riga al dia, mes y anio
						calendarTemp.setTime(periodoFechaInicio);
						calendarTemp.set(Calendar.DATE, dia);
						calendarTemp.set(Calendar.MONTH, mes);
						calendarTemp.set(Calendar.YEAR, anio);
					
						if ( calendarTemp.compareTo(calendarFechaInicio) >= 0 && calendarTemp.compareTo(calendarFechaFin) <= 0){
							ordenMedioDetalleMapaList.add(ordenMedioDetalleMapaIf);
						}
					}
				}
				ordenesMedioDetalleTotales.put(ordenMedioDetalleIf,ordenMedioDetalleMapaList);//
			}				
			ordenesMedioTotales.put(ordenMedioIf,ordenesMedioDetalleTotales);//
		}
		// System.out.println(ordenesMedioTotales.size());
		return ordenesMedioTotales;
	}*/
	
	// Proveedor se convierte en el Cliente ahora. X PRODUCTO CLIENTE VERSION
	public void cargarDetallesPlanMedioByProveedorCanjeByProducto(ClienteOficinaIf proveedor, ArrayList<PlanMedioFacturacionIf>listPlanMedioFacturacion, Map<ProductoClienteIf,ArrayList<Long>> mapaProductosClienteListVersion,Date periodoFechaInicio, Date periodoFechaFin){
		try {
			ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacionPorPeriodo = new ArrayList<PlanMedioFacturacionIf>();
			ArrayList<OrdenMedioIf> listaOrdenMedioIf = new ArrayList<OrdenMedioIf>();
						
			for(PlanMedioFacturacionIf planMedioFacturacion : listPlanMedioFacturacion){
				for(OrdenMedioIf ordenM :listaOrdenMedioTotal){
					int tmp = 0;
					if(ordenM.getId().compareTo(planMedioFacturacion.getOrdenMedioId())==0){
						// llena la lista con las ordenes de medio aun no
						// facturadas
						// For que verifica que la OrdenMedio No se encuentre en
						// la lista
						for(OrdenMedioIf ordenMedioIf: listaOrdenMedioIf){
							if(ordenMedioIf.getId().compareTo(ordenM.getId())!=0){
								tmp++;
							}
						}
						if(tmp==listaOrdenMedioIf.size()){
							listaOrdenMedioIf.add(ordenM);
						}
					}
				}
			}
			
			TipoDocumentoIf tipoDocumento = (getCmbTipoDocumento().getSelectedItem() != null)?(TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem():null;
			DocumentoIf documento = (getCmbDocumento().getSelectedItem() != null)?(DocumentoIf) getCmbDocumento().getSelectedItem():null;
			
			modelPedidoDetalle = (DefaultTableModel) getTblPedidoDetalle().getModel();
			ordenesSinFacturar = SpiritConstants.getEmptyCharacter();
			if (tipoDocumento != null && (tipoDocumento.getCodigo().equals("FAC") || tipoDocumento.getCodigo().equals("FAE"))) {
				// Recorrer la lista listaProductosVersionesOrdenesMedio con sus
				// respectivas ordenes de medio
				Map<ProductoClienteIf,Map<Long,ArrayList<OrdenMedioIf>>> listaProductosVersionesOrdenesMedio = new LinkedHashMap<ProductoClienteIf,Map<Long,ArrayList<OrdenMedioIf>>>();
				Map<Long,ArrayList<OrdenMedioIf>> mapVersionesOrdenesMedio;
				for(ProductoClienteIf productoClienteIf : mapaProductosClienteListVersion.keySet()){	
					ArrayList<Long> listVersiones = mapaProductosClienteListVersion.get(productoClienteIf);
					mapVersionesOrdenesMedio = new LinkedHashMap<Long, ArrayList<OrdenMedioIf>>();
					for (Long versionId : listVersiones) {
						ArrayList<OrdenMedioIf>listOrdenesMediosByProductoCliente = new ArrayList<OrdenMedioIf>();
						for(OrdenMedioIf ordenMedioTmp : listaOrdenMedioIf){
							if (planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) != 0){
								Long productoClienteId = new Long(0);
								Long productoClienteVersionId = 0L;
								Collection ordenesMedioDetalleColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioTmp.getId());
								productoClienteId = ordenMedioTmp.getProductoClienteId();
								OrdenMedioDetalleIf ordenMedioDetalleTmp = (OrdenMedioDetalleIf) ordenesMedioDetalleColl.iterator().next();
								productoClienteVersionId = ordenMedioDetalleTmp.getCampanaProductoVersionId();
								if(productoClienteId.compareTo(productoClienteIf.getId()) == 0 && 
								   productoClienteVersionId.compareTo(versionId) == 0 ){
									listOrdenesMediosByProductoCliente.add(ordenMedioTmp);
								}
							} else {// CUANDO LAS ORDENES DE MEDIO SON AGRUPADAS
									// POR CANAL
								Long productoClienteId = new Long(0);
								Long productoClienteVersionId = 0L;
								Collection<OrdenMedioDetalleIf> ordenMedioDetalleIfColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioTmp.getId());
								Iterator ordenMedioDetalleIfCollIt = ordenMedioDetalleIfColl.iterator();
								while(ordenMedioDetalleIfCollIt.hasNext()){
									OrdenMedioDetalleIf ordenMedioDetalleIftemp = (OrdenMedioDetalleIf) ordenMedioDetalleIfCollIt.next();
									productoClienteId = ordenMedioDetalleIftemp.getProductoClienteId();
									productoClienteVersionId = ordenMedioDetalleIftemp.getCampanaProductoVersionId();
									if(productoClienteId.compareTo(productoClienteIf.getId()) == 0 && 
									   productoClienteVersionId.compareTo(versionId) == 0	){
										listOrdenesMediosByProductoCliente.add(ordenMedioTmp);
									}
								}
							}						
						}
						
						if (listaProductosVersionesOrdenesMedio.containsKey(productoClienteIf)) {
							mapVersionesOrdenesMedio = listaProductosVersionesOrdenesMedio.get(productoClienteIf);
							mapVersionesOrdenesMedio.put(versionId,listOrdenesMediosByProductoCliente);
						} else {
							if (listOrdenesMediosByProductoCliente.size() > 0) {
								mapVersionesOrdenesMedio.put(versionId,listOrdenesMediosByProductoCliente);
							}
						}						
					}					
					listaProductosVersionesOrdenesMedio.put(productoClienteIf,mapVersionesOrdenesMedio);
				}
				
				// Se obtiene al proveedor que se convirtio en cliente al quien
				// se le debe generar la factura
				// pero antes guardo el cliente oficina original para poder
				// setearlo en la descripción.
				ClienteOficinaIf clienteOficinaReal = clienteOficinaIf;
				clienteOficinaIf = proveedor;
				clienteIf = mapaCliente.get(clienteOficinaIf.getClienteId());
				
				// Setea los datos del cliente en la pestaña Pedido
				setCliente(true);
				
				Vector<String> filaPlantillaDetalle = new Vector<String>();
				PedidoDetalleData data = new PedidoDetalleData();
				String clienteOficinaRealDescripcion = "";
				Double subtotalDetalles = 0D;
				// Poner el código de comisión directa...
				Long productoProveedorId = new Long(150);
				Double valorDetalles = 0D;
				Double ivaDetalles = 0D;
				String facturasProveedor = SpiritConstants.getEmptyCharacter();
				Iterator listaProductosVersionesOrdenesMedioIt = listaProductosVersionesOrdenesMedio.keySet().iterator();
				while(listaProductosVersionesOrdenesMedioIt.hasNext()){				
					ProductoClienteIf productoClienteIf = (ProductoClienteIf)listaProductosVersionesOrdenesMedioIt.next();
					Map mapaVersionesOrdenesMedio = listaProductosVersionesOrdenesMedio.get(productoClienteIf);
					Iterator mapaVersionesOrdenesMedioIt = mapaVersionesOrdenesMedio.keySet().iterator();
					while(mapaVersionesOrdenesMedioIt.hasNext()){
						Long versionId = (Long) mapaVersionesOrdenesMedioIt.next();
																	
						// lista de ordenes de medio
						ArrayList<OrdenMedioIf> listaOrdenMedio = (ArrayList<OrdenMedioIf>)mapaVersionesOrdenesMedio.get(versionId);
						
						Double valorSubtotal = 0D;
						double porcentajeDescuento = 0D;
						double porcentajeDescuentosVarios = 0D;
						Double iva = 0D;
						Double comisionAgencia = 0D;
						Double descuentosVarios = 0D;
						Double descuento = 0D;
						Double valor = 0D;
						// Poner el código de comisión directa...
						BigDecimal porcentajeDescuentoProveedor = new BigDecimal(0);
						
						Map<OrdenMedioIf,Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>> mapOrdenMedioSegunPeriodo;
												
						//mapOrdenMedioSegunPeriodo = getOrdenesMedioSegunPeriodoAFacturar(listaOrdenMedio,listPlanMedioFacturacion,periodoFechaInicio,periodoFechaFin);
						mapOrdenMedioSegunPeriodo = SessionServiceLocator.getPlanMedioSessionService().getOrdenesMedioSegunPeriodoAFacturar(listaOrdenMedio,listPlanMedioFacturacion,periodoFechaInicio,periodoFechaFin);						
						
						Iterator ordenMedioIt = mapOrdenMedioSegunPeriodo.keySet().iterator();
						// START WHILE DE ORDENES DE MEDIO por Comercial
						// Proveedor Segun Periodo
						while(ordenMedioIt.hasNext()){
							OrdenMedioIf ordenMedioIf  = (OrdenMedioIf)ordenMedioIt.next();
							// mapa de Orden Medio Detalle con Ordenes Medio
							// Detalle Mapa
							Map mapaOrdenesMedioDetalleTotal = (Map)mapOrdenMedioSegunPeriodo.get(ordenMedioIf);
							Iterator ordenMedioDetalleIt = mapaOrdenesMedioDetalleTotal.keySet().iterator();
															
							BigDecimal porcentajeCanje = ordenMedioIf.getComisionPura().equals("N")? ordenMedioIf.getPorcentajeCanje() : new BigDecimal(0); 
							Double valorSubtotal100OrdenxPeriodo  = 0D;
							
							// Se utiliza el porcentaje de descuento de
							// Negociacion Comision ya que el de descuento de la
							// orden esta en cero y si hay negociación.
							porcentajeDescuentoProveedor = ordenMedioIf.getPorcentajeNegociacionComision();
															
							// START WHILE DE ORDENES DE MEDIO DETALLE
							while(ordenMedioDetalleIt.hasNext()){
								OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
								Double valorSubTotal100DetallexPeriodo = 0D;
								
								if (planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) != 0){
									ArrayList<OrdenMedioDetalleMapaIf> listOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>) mapaOrdenesMedioDetalleTotal.get(ordenMedioDetalleIf);
																
									// START FOR ORDEN MEDIO DETALLE DATA
									for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf: listOrdenMedioDetalleMapa){
										// Se Crea un PlanMedioFacturacion para
										// setearle los valores y agregarlo a la
										// lista
										// PlanMedioFacturacionIf
										// planMedioFacturacionIf =
										// crearPlanMedioFacturacionIf(periodoFechaInicio,periodoFechaFin,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,comercialIf,true,porcentajeCanje);
										PlanMedioFacturacionIf planMedioFacturacionIf = crearPlanMedioFacturacionIf(periodoFechaInicio,periodoFechaFin,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,versionId,true,porcentajeCanje);
										
										listPlanMedioFacturacionPorPeriodo.add(planMedioFacturacionIf);
										BigDecimal cantidadCunias = new BigDecimal(ordenMedioDetalleMapaIf.getFrecuencia());
										BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(cantidadCunias);
										valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
									}
								}else{// CUANDO LAS ORDENES DE MEDIO SON
										// AGRUPADAS POR CANAL
									ArrayList<OrdenMedioDetalleMapaIf> listOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>) mapaOrdenesMedioDetalleTotal.get(ordenMedioDetalleIf);
									if (ordenMedioDetalleIf.getProductoClienteId().compareTo(productoClienteIf.getId()) == 0 && 
										ordenMedioDetalleIf.getCampanaProductoVersionId().compareTo(versionId) == 0	){
										// START FOR ORDEN MEDIO DETALLE DATA
										for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf: listOrdenMedioDetalleMapa){
											// Se Crea un PlanMedioFacturacion
											// para setearle los valores y
											// agregarlo a la lista
											PlanMedioFacturacionIf planMedioFacturacionIf = crearPlanMedioFacturacionIf(periodoFechaInicio,periodoFechaFin,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,versionId,true,porcentajeCanje);
											
											listPlanMedioFacturacionPorPeriodo.add(planMedioFacturacionIf);
											BigDecimal cantidadCunias = new BigDecimal(ordenMedioDetalleMapaIf.getFrecuencia());
											BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(cantidadCunias);
											valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
										}
									}
								}
								
								//BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(BigDecimal.valueOf(ordenMedioDetalleIf.getTotalCunias()));
								//valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
								
								valorSubtotal100OrdenxPeriodo = valorSubtotal100OrdenxPeriodo + valorSubTotal100DetallexPeriodo.doubleValue();
							}
							
							Double valorSubtotal100 = valorSubtotal100OrdenxPeriodo;
							Double valorSubtotalCanje = 0D;
							if(porcentajeCanje.compareTo(new BigDecimal(0)) == 1){
								valorSubtotalCanje = (valorSubtotal100*porcentajeCanje.doubleValue())/100; 
							}else{
								valorSubtotalCanje = valorSubtotal100;
							}
							
							// valor de descuento a cobrar al proveedor por cada
							// orden de medio
							Double valorSubtotalDescuento = (valorSubtotalCanje * porcentajeDescuentoProveedor.doubleValue())/100; 
							valorSubtotal = valorSubtotal + valorSubtotalDescuento;
													
							porcentajeDescuento = 0D;
							descuento = 0D;
							comisionAgencia = 0D;
							descuentosVarios = 0D;
							porcentajeComisionAgencia = 0D;
							productoProveedorId = ordenMedioIf.getProductoProveedorId();
							Map queryMap = new HashMap();
							queryMap.put("tipoOrden", "OM");
							queryMap.put("ordenId", ordenMedioIf.getId());
							String facturasAsociadas = getComprasAsociadasPorOrden(queryMap);
							if (facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
								ordenesSinFacturar += ordenMedioIf.getCodigo() + "\n";
							if (facturasProveedor.contains(facturasAsociadas))
								facturasAsociadas = SpiritConstants.getEmptyCharacter();
							if (!facturasProveedor.equals(SpiritConstants.getEmptyCharacter()) && !facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
								facturasProveedor += ", ";
							facturasProveedor += facturasAsociadas;
							
						}// END WHILE DE ORDENES DE MEDIO por Comercial
							// Proveedor Segun Periodo
						
						// END 11 ABRIL
						
						valor = valorSubtotal - descuento - descuentosVarios + comisionAgencia;
						CampanaProductoVersionIf version = mapaCampanaProductoVersion.get(versionId);
						clienteOficinaRealDescripcion = clienteOficinaReal.getDescripcion();
						subtotalDetalles = subtotalDetalles + valorSubtotal;
												
						if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N")) {
							
							if (documento == null || (documento != null && !documento.getBonificacion().equals(OPCION_SI)))
								iva = (valor.doubleValue()*(IVA));
								
							ivaTotal = ivaTotal + iva;
							
						} else {
							iva = (valor*IVA_CERO)/100;
							valor = valor + iva;							
						}
						
						if (valorSubtotal > 0D) {							
							valorDetalles = valorDetalles + valor;
					
							ProductoIf producto = mapaProductoProveedor.get(productoProveedorId);
							GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
		
							ivaDetalles = ivaDetalles + iva;
											
							// Para que no se tenga que actualizar el documento
							// uno a uno en los detalles
							documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipoDocumentoId(tipoDocumentoIf.getId()).iterator().next();
							// data.setDocumentoId((documento!=null)?documento.getId():null);
							
							// pedidoDetalleColeccion.add(data);
							// modelPedidoDetalle.addRow(filaPlantillaDetalle);
							subTotal = subTotal + valorSubtotal;
							
							if (generico.getCobraIvaCliente().equals("N"))
								totalIvaCero += valorSubtotal;
	
							descuentoTotal = descuentoTotal + descuento;
							descuentosVariosTotal = descuentosVariosTotal + descuentosVarios;
							comisionAgenciaTotal = comisionAgenciaTotal + comisionAgencia;
							
							if (tipoDocumento.getReembolso().equals("N"))
								total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal + ivaTotal;
							else
								total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal;
													
							cargandoDetallesReferencia = true;
						}					
					}// END WHILE MAPA VERSIONES ADD 8 SEPTIEMBRE
				} // END WHILE LISTA PRODUCTOS CLIENTE
				
				// En lugar de agregar un detalle por cada version, creo un solo
				// detalle donde aparece la comision total que se esta cobrando
				// al medio.
				filaPlantillaDetalle.add(clienteOficinaRealDescripcion);
				filaPlantillaDetalle.add("");
				filaPlantillaDetalle.add(formatoDecimal.format(subtotalDetalles));
				filaPlantillaDetalle.add("0%");// descuento
				filaPlantillaDetalle.add("0%");// descuento
				filaPlantillaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
				filaPlantillaDetalle.add("0%");
				modelPedidoDetalle.addRow(filaPlantillaDetalle);
								
				data.setCantidad(new BigDecimal(0));
				data.setCantidadpedida(new BigDecimal(1));
				data.setDescripcion(clienteOficinaRealDescripcion);
				data.setPrecio(BigDecimal.valueOf(subtotalDetalles));
				data.setPrecioreal(BigDecimal.valueOf(subtotalDetalles));
				data.setIce(new BigDecimal(0));
				data.setOtroimpuesto(new BigDecimal(0));
				data.setDescuento(new BigDecimal(0));
				data.setPorcentajeDescuentosVarios(new BigDecimal(0));
				data.setDescuentoGlobal(new BigDecimal(0));// Preguntar
				data.setProductoId(productoProveedorId);
				data.setValor(BigDecimal.valueOf(valorDetalles));
				data.setIva(BigDecimal.valueOf(ivaDetalles));
				data.setDocumentoId((documento!=null)?documento.getId():null);
				data.setComprasReembolsoAsociadas(facturasProveedor);
				pedidoDetalleColeccion.add(data);
				
				// agrega la lista de PlanesMedioFacturacion
				listaPlanMedioFacturacionEscogido = listPlanMedioFacturacionPorPeriodo;
				
				// creo que aki deberia mostrar el mensaje de que no existe
				// datos para presentar en esas fechas 20 ABRIL
				if (subTotal <= 0D){
					SpiritAlert.createAlert("No existen datos para mostrar en ese Periodo en esta Forma de Facturación!!!", SpiritAlert.INFORMATION);
				}
				
				getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
				
				if (tipoDocumento.getReembolso().equals("N")) {
					getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
				} else {
					getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAFinal().setText("0");
				}
				
				getTxtICEFinal().setText("0");
				getTxtOtroImpuestoFinal().setText("0");
				getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
				getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));
				getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
				getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
				/*
				 * if (subTotal > 0D &&
				 * !ordenesSinFacturar.equals(SpiritConstants.getEmptyCharacter()))
				 * SpiritAlert.createAlert("Se han cargado los detalles del
				 * presupuesto correctamente, aunque se presentó un problema\nal
				 * cargar datos de las facturas de proveedores asociadas.\n\nLas
				 * facturas de las siguientes órdenes no han sido ingresadas al
				 * sistema:\n\n" + ordenesSinFacturar, SpiritAlert.WARNING);
				 */
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
		
	public void cargarDetallesPlanMedioByProveedorNoCanjeByProveedor(ClienteOficinaIf proveedor, ArrayList<PlanMedioFacturacionIf>listPlanMedioFacturacion,Map<ProductoClienteIf,ArrayList<Long>> mapaProductosClienteListVersion,Date periodoFechaInicio, Date periodoFechaFin){
		try {
			ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacionPorPeriodo = new ArrayList<PlanMedioFacturacionIf>();
			// lista de ordenes de medio de la lista total que concuerdan con la
			// ordenes de medio de la listPlanMedioFacturacion
			ArrayList<OrdenMedioIf> listaOrdenMedioIf = new ArrayList<OrdenMedioIf>();
			
			for(PlanMedioFacturacionIf planMedioFacturacion:listPlanMedioFacturacion){
				for(OrdenMedioIf ordenM :listaOrdenMedioTotal){
					int tmp = 0;
					if(ordenM.getId().compareTo(planMedioFacturacion.getOrdenMedioId())==0){
						
						// llena la lista con las ordenes de medio aun no
						// facturadas
						// For que verifica que la OrdenMedio No se encuentre en
						// la lista
						for(OrdenMedioIf ordenMedioIf: listaOrdenMedioIf){
							if(ordenMedioIf.getId().compareTo(ordenM.getId())!=0){
								tmp++;
							}
						}
						if(tmp==listaOrdenMedioIf.size()){
							listaOrdenMedioIf.add(ordenM);
						}
					}
				}
			}
			
			TipoDocumentoIf tipoDocumento = (getCmbTipoDocumento().getSelectedItem() != null)?(TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem():null;
			DocumentoIf documento = (getCmbDocumento().getSelectedItem() != null)?(DocumentoIf) getCmbDocumento().getSelectedItem():null;
			modelPedidoDetalle = (DefaultTableModel) getTblPedidoDetalle().getModel();
			ordenesSinFacturar = SpiritConstants.getEmptyCharacter();
			if (tipoDocumento != null && (tipoDocumento.getCodigo().equals("FAC") || tipoDocumento.getCodigo().equals("FAE"))) {
				
				Map<ProductoClienteIf,Map<Long,ArrayList<OrdenMedioIf>>> listaProductosOrdenesMedio = new LinkedHashMap<ProductoClienteIf,Map<Long,ArrayList<OrdenMedioIf>>>();
				Map<Long,ArrayList<OrdenMedioIf>> mapaVersionesOrdenesMedio;// =
																			// new
																			// LinkedHashMap<String,
																			// ArrayList<OrdenMedioIf>>();
				
				for(ProductoClienteIf productoClienteIf : mapaProductosClienteListVersion.keySet()){
					mapaVersionesOrdenesMedio = new LinkedHashMap<Long, ArrayList<OrdenMedioIf>>();
					for(Long versionId : mapaProductosClienteListVersion.get(productoClienteIf)){
												
						ArrayList<OrdenMedioIf>listOrdenesMediosByProducto = new ArrayList<OrdenMedioIf>();
						
						for(OrdenMedioIf ordenMedioTmp : listaOrdenMedioIf){
							Long productoClienteId = new Long(0);
							Long productoClienteVersionId = 0L;
							
							if (planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) != 0){									
								productoClienteId = ordenMedioTmp.getProductoClienteId();
								Collection ordenesMedioDetalleColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioTmp.getId());
								OrdenMedioDetalleIf ordenMedioDetalleIdTmp = (OrdenMedioDetalleIf) ordenesMedioDetalleColl.iterator().next();
								productoClienteVersionId = ordenMedioDetalleIdTmp.getCampanaProductoVersionId();
								
								if(productoClienteId.compareTo(productoClienteIf.getId()) == 0 &&
									productoClienteVersionId.compareTo(versionId) == 0	){
									listOrdenesMediosByProducto.add(ordenMedioTmp);
								}
								
							}else{// CUANDO LAS ORDENES DE MEDIO SON AGRUPADAS
									// X CANAL
								Collection ordenMedioDetalleColl = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioTmp.getId());
								Iterator ordenMedioDetalleCollIt = ordenMedioDetalleColl.iterator();
								while(ordenMedioDetalleCollIt.hasNext()){
									OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleCollIt.next();
									
									if (ordenMedioDetalleIf.getProductoClienteId().compareTo(productoClienteIf.getId()) == 0 && 
										ordenMedioDetalleIf.getCampanaProductoVersionId().compareTo(versionId) == 0	){
										listOrdenesMediosByProducto.add(ordenMedioTmp);
									}
								}
							}						
						}
						
						if (listaProductosOrdenesMedio.containsKey(productoClienteIf)){
							mapaVersionesOrdenesMedio = listaProductosOrdenesMedio.get(productoClienteIf);
							
							mapaVersionesOrdenesMedio.put(versionId,listOrdenesMediosByProducto);
							
						}else{
							if(listOrdenesMediosByProducto.size() > 0){
								mapaVersionesOrdenesMedio.put(versionId,listOrdenesMediosByProducto);
							}
						}					
					}
					listaProductosOrdenesMedio.put(productoClienteIf,mapaVersionesOrdenesMedio); 
				}
				
			    Iterator listaProductosOrdenesMedioIt = listaProductosOrdenesMedio.keySet().iterator();
				while(listaProductosOrdenesMedioIt.hasNext()){
					ProductoClienteIf productoClienteIf = (ProductoClienteIf)listaProductosOrdenesMedioIt.next();
					
					Map mapaVersiones = listaProductosOrdenesMedio.get(productoClienteIf);
					Iterator mapaVersionesIt = mapaVersiones.keySet().iterator();
					
					while(mapaVersionesIt.hasNext()){
						Long versionId = (Long) mapaVersionesIt.next();
					
						// Se obtiene al proveedor que se convirtio en cliente
						// al quien se le debe generar la factura
						
						Vector<String> filaPlantillaDetalle = new Vector<String>();
						PedidoDetalleData data = new PedidoDetalleData();
						ArrayList<OrdenMedioIf> listaOrdenMedio = (ArrayList<OrdenMedioIf>)mapaVersiones.get(versionId);
						
						Double valorSubtotal = 0D;
						double porcentajeDescuento = 0D;
						double porcentajeDescuentosVarios = 0D;
						Double iva = 0D;
						Double comisionAgencia = 0D;
						Double descuentosVarios = 0D;
						Double descuento = 0D;
						Double valor = 0D;
						// Poner el código de comisión directa...
						Long productoProveedorId = new Long(150);
						// BigDecimal porcentajeDescuentoProveedor = new
						// BigDecimal(0);
						
						Map<OrdenMedioIf,Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>> mapOrdenMedioSegunPeriodo;
											
						//mapOrdenMedioSegunPeriodo = getOrdenesMedioSegunPeriodoAFacturar(listaOrdenMedio,listPlanMedioFacturacion,periodoFechaInicio,periodoFechaFin);
						mapOrdenMedioSegunPeriodo = SessionServiceLocator.getPlanMedioSessionService().getOrdenesMedioSegunPeriodoAFacturar(listaOrdenMedio,listPlanMedioFacturacion,periodoFechaInicio,periodoFechaFin);
						
						Iterator ordenMedioIt = mapOrdenMedioSegunPeriodo.keySet().iterator();
						String facturasProveedor = SpiritConstants.getEmptyCharacter();
						// START WHILE DE ORDENES DE MEDIO por Comercial
						// Proveedor Segun Periodo
						while(ordenMedioIt.hasNext()){
							OrdenMedioIf ordenMedioIf  = (OrdenMedioIf)ordenMedioIt.next();
							// mapa de Orden Medio Detalle con Ordenes Medio
							// Detalle Mapa
							Map mapaOrdenesMedioDetalleTotal = (Map)mapOrdenMedioSegunPeriodo.get(ordenMedioIf);
							Iterator ordenMedioDetalleIt = mapaOrdenesMedioDetalleTotal.keySet().iterator();
															
							BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje(); 
							Double valorSubtotal100OrdenxPeriodo  = 0D;
							descuento = 0D;
								
							// START WHILE DE ORDENES DE MEDIO DETALLE
							while(ordenMedioDetalleIt.hasNext()){
								OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
								Double valorSubTotal100DetallexPeriodo = 0D;
													
								if (planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) == 0){
									
									ArrayList<OrdenMedioDetalleMapaIf> listOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>) mapaOrdenesMedioDetalleTotal.get(ordenMedioDetalleIf);
									
									if (ordenMedioDetalleIf.getProductoClienteId().compareTo(productoClienteIf.getId()) == 0 &&
										ordenMedioDetalleIf.getCampanaProductoVersionId().compareTo(versionId) == 0	){
										// START FOR ORDEN MEDIO DETALLE DATA
										for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf : listOrdenMedioDetalleMapa){
											// Se Crea un PlanMedioFacturacion
											// para setearle los valores y
											// agregarlo a la lista
											PlanMedioFacturacionIf planMedioFacturacionIf = crearPlanMedioFacturacionIf(periodoFechaInicio,periodoFechaFin,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,versionId,false,porcentajeCanje);
											listPlanMedioFacturacionPorPeriodo.add(planMedioFacturacionIf);
											BigDecimal cantidadCunias = new BigDecimal(ordenMedioDetalleMapaIf.getFrecuencia());
											BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(cantidadCunias);
											valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
										}
									}
									
								}else{
									ArrayList<OrdenMedioDetalleMapaIf> listOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>) mapaOrdenesMedioDetalleTotal.get(ordenMedioDetalleIf);
									
									// START FOR ORDEN MEDIO DETALLE DATA
									for(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf: listOrdenMedioDetalleMapa){
										// Se Crea un PlanMedioFacturacion para
										// setearle los valores y agregarlo a la
										// lista
										PlanMedioFacturacionIf planMedioFacturacionIf = crearPlanMedioFacturacionIf(periodoFechaInicio,periodoFechaFin,ordenMedioDetalleMapaIf,ordenMedioDetalleIf,ordenMedioIf,productoClienteIf,versionId,false,porcentajeCanje);
										
										listPlanMedioFacturacionPorPeriodo.add(planMedioFacturacionIf);
										BigDecimal cantidadCunias = new BigDecimal(ordenMedioDetalleMapaIf.getFrecuencia());
										BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(cantidadCunias);
										valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
									}	
								}
																
								//BigDecimal valorTotalOrdenMedioDetalleMapa = ordenMedioDetalleIf.getValorTarifa().multiply(BigDecimal.valueOf(ordenMedioDetalleIf.getTotalCunias()));
								//valorSubTotal100DetallexPeriodo = valorSubTotal100DetallexPeriodo + valorTotalOrdenMedioDetalleMapa.doubleValue();
																
								valorSubtotal100OrdenxPeriodo = valorSubtotal100OrdenxPeriodo + valorSubTotal100DetallexPeriodo.doubleValue();
							}
							
							BigDecimal porcentaje100 = new BigDecimal(100);
							BigDecimal porcentajeCliente = porcentaje100.subtract(ordenMedioIf.getPorcentajeCanje());
							
							Double valorSubtotal100 = valorSubtotal100OrdenxPeriodo;
							Double valorSubtotalCanje = (valorSubtotal100*porcentajeCliente.doubleValue())/100;
							
							// Si es factura del exterior y viene de un plan de
							// medios le sumo el IVA al subtotal
							// ya que va sin iva la factura.
							if(tipoDocumento.getCodigo().equals("FAE")){
								Double ivaAgregado = 1D + (Parametros.IVA / 100); 
								valorSubtotalCanje = valorSubtotalCanje * ivaAgregado;
								valorSubtotal = valorSubtotal + valorSubtotalCanje;
							}else{
								valorSubtotal = valorSubtotal + valorSubtotalCanje;
							}
							
							porcentajeDescuento = ordenMedioIf.getPorcentajeDescuentoVenta().doubleValue();
							
							Double valorDescuento100 = 0D;
							valorDescuento100 = valorSubtotalCanje * (porcentajeDescuento/100);						
							descuento = descuento + valorDescuento100;
							
							porcentajeDescuentosVarios = ordenMedioIf.getPorcentajeBonificacionVenta().doubleValue();
							Double valorBonificacion100 = (valorSubtotalCanje - valorDescuento100) * (porcentajeDescuentosVarios / 100);
							descuentosVarios = descuentosVarios + valorBonificacion100;
														
							Double valorComision100 = (valorSubtotalCanje - valorDescuento100 - valorBonificacion100) * (ordenMedioIf.getPorcentajeComisionAgencia().doubleValue()/100);
							comisionAgencia = comisionAgencia + valorComision100;
														
							productoProveedorId = ordenMedioIf.getProductoProveedorId();
							porcentajeComisionAgencia = ordenMedioIf.getPorcentajeComisionAgencia().doubleValue();
							Map queryMap = new HashMap();
							queryMap.put("tipoOrden", "OM");
							queryMap.put("ordenId", ordenMedioIf.getId());
							String facturasAsociadas = getComprasAsociadasPorOrden(queryMap);
							if (facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
								ordenesSinFacturar += ordenMedioIf.getCodigo() + "\n";
							if (facturasProveedor.contains(facturasAsociadas))
								facturasAsociadas = SpiritConstants.getEmptyCharacter();
							if (!facturasProveedor.equals(SpiritConstants.getEmptyCharacter()) && !facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
								facturasProveedor += ", ";
							facturasProveedor += facturasAsociadas;
						}
						
						valor = valorSubtotal - descuento  - descuentosVarios + comisionAgencia;
						CampanaProductoVersionIf version = mapaCampanaProductoVersion.get(versionId);
						
						if(planMedioIf.getOrdenMedioTipo().equals("P"))
							filaPlantillaDetalle.add(productoClienteIf.getNombre());
						else
							filaPlantillaDetalle.add(productoClienteIf.getNombre()+"("+ version.getNombre() + ")");
						
						filaPlantillaDetalle.add("");
						filaPlantillaDetalle.add(formatoDecimal.format(valorSubtotal));
						filaPlantillaDetalle.add("0%");
						filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuento)+"%");
												
						if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N")) {
							if (documento == null || (documento != null && !documento.getBonificacion().equals(OPCION_SI)))
								iva = (valor.doubleValue()*(IVA));
								ivaTotal = ivaTotal + iva;
							filaPlantillaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
						} else {
							iva = (valor*IVA_CERO)/100;
							valor = valor + iva;
							filaPlantillaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");
						}
						
						filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuentosVarios)+"%");
	
						if (valorSubtotal > 0D) {
							
							data.setCantidad(new BigDecimal(0));
							data.setCantidadpedida(new BigDecimal(1));
							
							if(planMedioIf.getOrdenMedioTipo().equals("P"))						
								data.setDescripcion(productoClienteIf.getNombre());
							else
								data.setDescripcion(productoClienteIf.getNombre()+"("+ version+")");
								
							data.setPrecio(BigDecimal.valueOf(valorSubtotal));
							data.setPrecioreal(BigDecimal.valueOf(valorSubtotal));
							data.setIce(new BigDecimal(0));
							data.setOtroimpuesto(new BigDecimal(0));
							data.setDescuento(BigDecimal.valueOf(descuento));
							data.setPorcentajeDescuentosVarios(BigDecimal.valueOf(porcentajeDescuentosVarios));
							data.setDescuentoGlobal(new BigDecimal(0));
							data.setProductoId(productoProveedorId);
							data.setValor(BigDecimal.valueOf(valor));
							data.setComprasReembolsoAsociadas(facturasProveedor);
							ProductoIf producto = mapaProductoProveedor.get(productoProveedorId);
							GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
							if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N"))
								data.setIva(BigDecimal.valueOf(iva));
							else
								data.setIva(BigDecimal.valueOf(0.0));
											
							// Para que no se tenga que actualizar el documento
							// uno a uno en los detalles
							documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipoDocumentoId(tipoDocumentoIf.getId()).iterator().next();
							data.setDocumentoId((documento!=null)?documento.getId():null);
							
							pedidoDetalleColeccion.add(data);
							modelPedidoDetalle.addRow(filaPlantillaDetalle);
							subTotal = subTotal + valorSubtotal;
							
							if (generico.getCobraIvaCliente().equals("N"))
								totalIvaCero += valorSubtotal;
	
							descuentoTotal = descuentoTotal + descuento;
							descuentosVariosTotal = descuentosVariosTotal + descuentosVarios;
							comisionAgenciaTotal = comisionAgenciaTotal + comisionAgencia;
							
							if (tipoDocumento.getReembolso().equals("N"))
								total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal + ivaTotal;
							else
								total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal;
													
							cargandoDetallesReferencia = true;
						}
					}
				}
				
				// agrega la lista de PlanesMedioFacturacion
				listaPlanMedioFacturacionEscogido = listPlanMedioFacturacionPorPeriodo;
				
				// creo que aki deberia mostrar el mensaje de que no existe
				// datos para presentar en esas fechas 20 ABRIL
				if (subTotal <= 0D){
					SpiritAlert.createAlert("No existen datos para mostrar en ese Periodo en esta Forma de Facturación!!!", SpiritAlert.INFORMATION);
				}
				
				getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
				
				if (tipoDocumento.getReembolso().equals("N")) {
					getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
				} else {
					getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAFinal().setText("0");
				}
				
				getTxtICEFinal().setText("0");
				getTxtOtroImpuestoFinal().setText("0");
				getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
				getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));
				getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
				getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
				/*
				 * if (subTotal > 0D &&
				 * !ordenesSinFacturar.equals(SpiritConstants.getEmptyCharacter()))
				 * SpiritAlert.createAlert("Se han cargado los detalles del
				 * presupuesto correctamente, aunque se presentó un problema\nal
				 * cargar datos de las facturas de proveedores asociadas.\n\nLas
				 * facturas de las siguientes órdenes no han sido ingresadas al
				 * sistema:\n\n" + ordenesSinFacturar, SpiritAlert.WARNING);
				 */
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cargarDetallesPlanMedioCompleto(Collection<OrdenMedioIf> ordenMedioIfColl,ArrayList<String> listaProveedores) {
		try {
			TipoDocumentoIf tipoDocumento = (getCmbTipoDocumento().getSelectedItem() != null)?(TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem():null;
			DocumentoIf documento = (getCmbDocumento().getSelectedItem() != null)?(DocumentoIf) getCmbDocumento().getSelectedItem():null;
			
			modelPedidoDetalle = (DefaultTableModel) getTblPedidoDetalle().getModel();
			
			if (tipoDocumento != null && (tipoDocumento.getCodigo().equals("FAC") || tipoDocumento.getCodigo().equals("FAE"))) {
				// cargarDetallesPlanMedios
				Map<String,ArrayList<OrdenMedioIf>> listaProveedoresOrdenesMedio = new LinkedHashMap<String, ArrayList<OrdenMedioIf>>();
				for (String proveedorAndProducto : listaProveedores) {
					String[] key = proveedorAndProducto.split("-");
					Long proveedorId = Long.parseLong(key[0]);
					Long productoProveedorId = Long.parseLong(key[1]);
					ArrayList<OrdenMedioIf>listOrdenesMediosByProveedor = new ArrayList<OrdenMedioIf>();
					Iterator ordenMedioIfCollIt = ordenMedioIfColl.iterator();
					while (ordenMedioIfCollIt.hasNext()) {
						OrdenMedioIf ordenMedioTmp = (OrdenMedioIf)ordenMedioIfCollIt.next();
						if (ordenMedioTmp.getProveedorId().compareTo(proveedorId) == 0 && ordenMedioTmp.getProductoProveedorId().compareTo(productoProveedorId) == 0) {
							listOrdenesMediosByProveedor.add(ordenMedioTmp);
						}
					}
					if (listOrdenesMediosByProveedor.size() > 0) {
						// proveedores con su lista de ordenes de medio
						listaProveedoresOrdenesMedio.put(String.valueOf(proveedorId) + "-" + String.valueOf(productoProveedorId),listOrdenesMediosByProveedor);
					}
				}
				
				Iterator listaProveedoresOrdenesMedioIt = listaProveedoresOrdenesMedio.keySet().iterator();
				ordenesSinFacturar = SpiritConstants.getEmptyCharacter();
				while (listaProveedoresOrdenesMedioIt.hasNext()) {
					String[] key = ((String) listaProveedoresOrdenesMedioIt.next()).split("-");
					Long proveedorId = Long.parseLong(key[0]);
					Long productoId = Long.parseLong(key[1]);
					ClienteOficinaIf clienteOficinaIf2 = mapaClienteOficina.get(proveedorId);
					Vector<String> filaPlantillaDetalle = new Vector<String>();
					PedidoDetalleData data = new PedidoDetalleData();
					ArrayList<OrdenMedioIf> listaOrdenMedio = (ArrayList<OrdenMedioIf>)listaProveedoresOrdenesMedio.get(String.valueOf(proveedorId) + "-" + String.valueOf(productoId));
					Double valorSubtotal = 0D;
					double porcentajeDescuento = 0D;
					double porcentajeDescuentosVarios = 0D;
					Double iva = 0D;
					Double comisionAgencia = 0D;
					Double descuentosVarios = 0D;
					Double descuento = 0D;
					Double valor = 0D;
					Long productoProveedorId = new Long(0);
					String facturasProveedor = SpiritConstants.getEmptyCharacter();
					// Se recorre cada orden de Medio del Proveedor y se obtiene
					// el total de los valores en el for
					for (OrdenMedioIf ordenMedioIf: listaOrdenMedio) {
						BigDecimal porcentaje100 = new BigDecimal(100);
						BigDecimal porcentajeCliente = porcentaje100.subtract(ordenMedioIf.getPorcentajeCanje());
						Double valorSubTotalOrden = (ordenMedioIf.getValorSubtotal().doubleValue()*porcentajeCliente.doubleValue())/100;
						// Si es factura del exterior y viene de un plan de
						// medios le sumo el IVA al subtotal ya que va sin iva
						// la factura.
						if (tipoDocumento.getCodigo().equals("FAE")) {
							Double ivaAgregado = 1D + (Parametros.IVA / 100); 
							valorSubTotalOrden = valorSubTotalOrden * ivaAgregado;
							valorSubtotal = valorSubtotal + valorSubTotalOrden;
						} else {
							valorSubtotal = valorSubtotal + valorSubTotalOrden;
						}
						
						porcentajeDescuento = ordenMedioIf.getPorcentajeDescuentoVenta().doubleValue();
						Double descuentoOrden = valorSubTotalOrden * (porcentajeDescuento / 100);
						descuento = descuento + descuentoOrden;
						porcentajeDescuentosVarios = ordenMedioIf.getPorcentajeBonificacionVenta().doubleValue();
						double descuentosVariosTemp = (valorSubTotalOrden - descuentoOrden) * (porcentajeDescuentosVarios / 100);
						descuentosVarios = descuentosVarios + descuentosVariosTemp;
						Double comisionAgenciaTemp = ((valorSubTotalOrden - descuentoOrden - descuentosVariosTemp) * (ordenMedioIf.getPorcentajeComisionAgencia().doubleValue() / 100));
						comisionAgencia = comisionAgencia + comisionAgenciaTemp;
						productoProveedorId = ordenMedioIf.getProductoProveedorId();
						porcentajeComisionAgencia = ordenMedioIf.getPorcentajeComisionAgencia().doubleValue();
						Map queryMap = new HashMap();
						queryMap.put("tipoOrden", "OM");
						queryMap.put("ordenId", ordenMedioIf.getId());
						String facturasAsociadas = getComprasAsociadasPorOrden(queryMap);
						if (facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
							ordenesSinFacturar += ordenMedioIf.getCodigo() + "\n";
						if (facturasProveedor.contains(facturasAsociadas))
							facturasAsociadas = SpiritConstants.getEmptyCharacter();
						if (!facturasProveedor.equals(SpiritConstants.getEmptyCharacter()) && !facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
							facturasProveedor += ", ";
						facturasProveedor += facturasAsociadas;
					}
					
					// valor entre todas las ordenes de medio del proveedor
					valor = valorSubtotal - descuento - descuentosVarios + comisionAgencia;
					filaPlantillaDetalle.add(clienteOficinaIf2.getDescripcion());
					filaPlantillaDetalle.add("");
					filaPlantillaDetalle.add(formatoDecimal.format(valorSubtotal));
					filaPlantillaDetalle.add("0%");// descuento
					filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuento)+"%");// descuento
					// tipoDocumento diferente de null y sin rembolso
					if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N")) {
						if (documento == null || (documento != null && !documento.getBonificacion().equals(OPCION_SI)))
							iva = (valor.doubleValue()*(IVA));
							ivaTotal = ivaTotal + iva;
						filaPlantillaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
					} else {
						iva = (valor*IVA_CERO)/100;
						valor = valor + iva;
						filaPlantillaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");
					}
					
					filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuentosVarios)+"%");

					if (valorSubtotal > 0D) {
						data.setCantidad(new BigDecimal(0));
						data.setCantidadpedida(new BigDecimal(1));
						data.setDescripcion(clienteOficinaIf2.getDescripcion());
						data.setPrecio(BigDecimal.valueOf(valorSubtotal));
						data.setPrecioreal(BigDecimal.valueOf(valorSubtotal));
						data.setIce(new BigDecimal(0));
						data.setOtroimpuesto(new BigDecimal(0));
						data.setDescuento(BigDecimal.valueOf(descuento));
						data.setPorcentajeDescuentosVarios(BigDecimal.valueOf(porcentajeDescuentosVarios));
						data.setDescuentoGlobal(new BigDecimal(0));// Preguntar
						data.setProductoId(productoProveedorId);
						data.setValor(BigDecimal.valueOf(valor));
						data.setComprasReembolsoAsociadas(facturasProveedor);
										
						ProductoIf producto = mapaProductoProveedor.get(productoProveedorId);
						GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
	
						if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N"))
							data.setIva(BigDecimal.valueOf(iva));
						else
							data.setIva(BigDecimal.valueOf(0.0));
										
						// Para que no se tenga que actualizar el documento uno
						// a uno en los detalles
						documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipoDocumentoId(tipoDocumentoIf.getId()).iterator().next();
						data.setDocumentoId((documento!=null)?documento.getId():null);
						
						pedidoDetalleColeccion.add(data);
						modelPedidoDetalle.addRow(filaPlantillaDetalle);
						subTotal = subTotal + valorSubtotal;
						
						if (generico.getCobraIvaCliente().equals("N"))
							totalIvaCero += valorSubtotal;

						descuentoTotal = descuentoTotal + descuento;
						descuentosVariosTotal = descuentosVariosTotal + descuentosVarios;
						comisionAgenciaTotal = comisionAgenciaTotal + comisionAgencia;
						
						if (tipoDocumento.getReembolso().equals("N"))
							total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal + ivaTotal;
						else
							total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal;
						
						// cargandoDetallesReferencia = true;
						cargandoDetallesReferencia = false;
					}
				}
				
				getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
				
				if (tipoDocumento.getReembolso().equals("N")) {
					getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
				} else {
					getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAFinal().setText("0");
				}
				
				getTxtICEFinal().setText("0");
				getTxtOtroImpuestoFinal().setText("0");
				getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
				getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));
				getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
				getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
				/*
				 * if (subTotal > 0D &&
				 * !ordenesSinFacturar.equals(SpiritConstants.getEmptyCharacter()))
				 * SpiritAlert.createAlert("Se han cargado los detalles del
				 * presupuesto correctamente, aunque se presentó un problema\nal
				 * cargar datos de las facturas de proveedores asociadas.\n\nLas
				 * facturas de las siguientes órdenes no han sido ingresadas al
				 * sistema:\n\n" + ordenesSinFacturar, SpiritAlert.WARNING);
				 */
			} else if (tipoDocumento != null && tipoDocumento.getCodigo().equals("FAR") && tipoDocumento.getReembolso().equals("S")) {
				// cargarDetallesPlanMedios
				Map<String,ArrayList<OrdenMedioIf>> listaProveedoresOrdenesMedio = new LinkedHashMap<String, ArrayList<OrdenMedioIf>>();
				for (String proveedorAndProducto : listaProveedores) {
					String[] key = proveedorAndProducto.split("-");
					Long proveedorId = Long.parseLong(key[0]);
					Long productoProveedorId = Long.parseLong(key[1]);
					ArrayList<OrdenMedioIf>listOrdenesMediosByProveedor = new ArrayList<OrdenMedioIf>();
					Iterator ordenMedioIfCollIt = ordenMedioIfColl.iterator();
					while(ordenMedioIfCollIt.hasNext()){
						OrdenMedioIf ordenMedioTmp = (OrdenMedioIf)ordenMedioIfCollIt.next();
						if (ordenMedioTmp.getProveedorId().compareTo(proveedorId) == 0 && ordenMedioTmp.getProductoProveedorId().compareTo(productoProveedorId) == 0) {
							listOrdenesMediosByProveedor.add(ordenMedioTmp);
						}
					}
					if (listOrdenesMediosByProveedor.size() > 0) {
						// proveedores con su lista de ordenes de medio
						listaProveedoresOrdenesMedio.put(String.valueOf(proveedorId) + "-" + String.valueOf(productoProveedorId), listOrdenesMediosByProveedor);
					}
				}
				
				ordenesSinFacturar = SpiritConstants.getEmptyCharacter();
				Iterator listaProveedoresOrdenesMedioIt = listaProveedoresOrdenesMedio.keySet().iterator();
				while(listaProveedoresOrdenesMedioIt.hasNext()){
					String[] key = ((String) listaProveedoresOrdenesMedioIt.next()).split("-");
					Long proveedorId = Long.parseLong(key[0]);
					Long productoId = Long.parseLong(key[1]);
					ClienteOficinaIf clienteOficinaIf2 = mapaClienteOficina.get(proveedorId);
					Vector<String> filaPlantillaDetalle = new Vector<String>();
					PedidoDetalleData data = new PedidoDetalleData();
					ArrayList<OrdenMedioIf> listaOrdenMedio = (ArrayList<OrdenMedioIf>)listaProveedoresOrdenesMedio.get(String.valueOf(proveedorId) + "-" + String.valueOf(productoId));
					Double valorSubtotal = 0D;
					double porcentajeDescuento = 0D;
					double porcentajeDescuentosVarios = 0D;
					Double iva = 0D;
					Double comisionAgencia = 0D;
					Double descuentosVarios = 0D;
					Double descuento = 0D;
					Double valor = 0D;
					Long productoProveedorId = new Long(0);
					String facturasProveedor = SpiritConstants.getEmptyCharacter();
					// Se recorre cada orden de Medio del Proveedor y
					// se obtiene el total de los valores en el for
					for(OrdenMedioIf ordenMedioIf: listaOrdenMedio){
						Double valorSubTotalOrden = ordenMedioIf.getValorSubtotal().doubleValue();
						valorSubtotal = valorSubtotal + valorSubTotalOrden;
						Double descuentoOrden = ordenMedioIf.getValorDescuento().doubleValue();
						descuento = descuento + descuentoOrden;
						double descuentosVariosTemp = (valorSubTotalOrden - descuentoOrden) * (ordenMedioIf.getPorcentajeBonificacionCompra().doubleValue() / 100);
						descuentosVarios = descuentosVarios + descuentosVariosTemp;
						productoProveedorId = ordenMedioIf.getProductoProveedorId();
						Map queryMap = new HashMap();
						queryMap.put("tipoOrden", "OM");
						queryMap.put("ordenId", ordenMedioIf.getId());
						String facturasAsociadas = getComprasAsociadasPorOrden(queryMap);
						if (facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
							ordenesSinFacturar += ordenMedioIf.getCodigo() + "\n";
						if (facturasProveedor.contains(facturasAsociadas))
							facturasAsociadas = SpiritConstants.getEmptyCharacter();
						if (!facturasProveedor.equals(SpiritConstants.getEmptyCharacter()) && !facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
							facturasProveedor += ", ";
						facturasProveedor += facturasAsociadas;
					}
					
					// valor entre todas las ordenes de medio del proveedor
					valorSubtotal = valorSubtotal - descuento - descuentosVarios + iva;
					ProductoIf producto = mapaProductoProveedor.get(productoProveedorId);
					GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
					iva = generico.getCobraIva().equals("S")?(valorSubtotal.doubleValue()*(IVA)):0D;
					valorSubtotal = valorSubtotal + iva;
					valor = valorSubtotal;
					descuento = 0D;
					descuentosVarios = 0D;
					
					filaPlantillaDetalle.add(clienteOficinaIf2.getDescripcion());
					filaPlantillaDetalle.add("");
					filaPlantillaDetalle.add(formatoDecimal.format(valorSubtotal));
					filaPlantillaDetalle.add("0%");// descuento
					filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuento)+"%");// descuento
					
					// tipoDocumento diferente de null y sin rembolso
					if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N")) {
						if (documento == null || (documento != null && !documento.getBonificacion().equals(OPCION_SI)))
							iva = (valor.doubleValue()*(IVA));
						ivaTotal = ivaTotal + iva;
						filaPlantillaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
					} else {
						iva = (valor*IVA_CERO)/100;
						valor = valor + iva;
						filaPlantillaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");
					}
					
					filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuentosVarios)+"%");

					if (valorSubtotal > 0D) {
						data.setCantidad(new BigDecimal(0));
						data.setCantidadpedida(new BigDecimal(1));
						data.setDescripcion(clienteOficinaIf2.getDescripcion());
						data.setPrecio(BigDecimal.valueOf(valorSubtotal));
						data.setPrecioreal(BigDecimal.valueOf(valorSubtotal));
						data.setIce(new BigDecimal(0));
						data.setOtroimpuesto(new BigDecimal(0));
						data.setDescuento(BigDecimal.valueOf(descuento));
						data.setPorcentajeDescuentosVarios(BigDecimal.valueOf(porcentajeDescuentosVarios));
						data.setDescuentoGlobal(new BigDecimal(0));// Preguntar
						data.setProductoId(productoProveedorId);
						data.setValor(BigDecimal.valueOf(valor));
						data.setComprasReembolsoAsociadas(facturasProveedor);
						// Para que no se tenga que actualizar el documento uno
						// a uno en los detalles
						documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipoDocumentoId(tipoDocumentoIf.getId()).iterator().next();
						data.setDocumentoId((documento!=null)?documento.getId():null);
						
						pedidoDetalleColeccion.add(data);
						modelPedidoDetalle.addRow(filaPlantillaDetalle);
						subTotal = subTotal + valorSubtotal;
						
						if (generico.getCobraIvaCliente().equals("N"))
							totalIvaCero += valorSubtotal;

						descuentoTotal = descuentoTotal + descuento;
						descuentosVariosTotal = descuentosVariosTotal + descuentosVarios;
						comisionAgenciaTotal = comisionAgenciaTotal + comisionAgencia;
						
						if (tipoDocumento.getReembolso().equals("N"))
							total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal + ivaTotal;
						else
							total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal;
						
						// cargandoDetallesReferencia = true;
						cargandoDetallesReferencia = false;
					}
				}
				getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
				if (tipoDocumento.getReembolso().equals("N")) {
					getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
				} else {
					getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAFinal().setText("0");
				}
				getTxtICEFinal().setText("0");
				getTxtOtroImpuestoFinal().setText("0");
				getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
				getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));
				getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
				getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
				if (subTotal > 0D && !ordenesSinFacturar.equals(SpiritConstants.getEmptyCharacter()))
					SpiritAlert.createAlert("Se han cargado los detalles del presupuesto correctamente, aunque se presentó un problema\nal cargar datos de las facturas de proveedores asociadas.\n\nLas facturas de las siguientes órdenes no han sido ingresadas al sistema:\n\n" + ordenesSinFacturar, SpiritAlert.WARNING);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cargarOrdenesMedioParaFacturacionParcial(ArrayList<Object> listaOrdenMedio) {
		try {
			TipoDocumentoIf tipoDocumento = (getCmbTipoDocumento().getSelectedItem() != null)?(TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem():null;
			DocumentoIf documento = (getCmbDocumento().getSelectedItem() != null)?(DocumentoIf) getCmbDocumento().getSelectedItem():null;
			
			modelPedidoDetalle = (DefaultTableModel) getTblPedidoDetalle().getModel();
			listaPlanMedioFacturacionEscogido.clear();
			
			// este mapa lo uso en el metodo: crearRegistrosPlanMedioFacturacion
			mapaOrdenesMedioFacturadas = new HashMap<Long, Long>();
			
			if (tipoDocumento != null && (tipoDocumento.getCodigo().equals("FAC") || tipoDocumento.getCodigo().equals("FAE"))) {
							
				// Se recorre cada orden de Medio del Proveedor y se obtiene el
				// total de los valores en el for
				for (int i = 0; i < listaOrdenMedio.size(); i++) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) listaOrdenMedio.get(i);
					
					String facturasProveedor = SpiritConstants.getEmptyCharacter();
										
					Vector<String> filaPlantillaDetalle = new Vector<String>();
					PedidoDetalleData data = new PedidoDetalleData();
										
					BigDecimal porcentaje100 = new BigDecimal(100);
					BigDecimal porcentajeCliente = porcentaje100.subtract(ordenMedioIf.getPorcentajeCanje());
					Double valorSubTotalOrden = ordenMedioIf.getValorSubtotal().doubleValue()*(porcentajeCliente.doubleValue()/100D);
					
					// Si es factura del exterior y viene de un plan de medios
					// le sumo el IVA al subtotal ya que va sin iva la factura.
					if (tipoDocumento.getCodigo().equals("FAE")) {
						Double ivaAgregado = 1D + (Parametros.IVA / 100); 
						valorSubTotalOrden = valorSubTotalOrden * ivaAgregado;						
					}
					
					double porcentajeDescuento = ordenMedioIf.getPorcentajeDescuentoVenta().doubleValue();
					double descuento = valorSubTotalOrden * (porcentajeDescuento / 100D);
					double porcentajeDescuentosVarios = ordenMedioIf.getPorcentajeBonificacionVenta().doubleValue();
					double descuentosVarios = (valorSubTotalOrden - descuento) * (porcentajeDescuentosVarios / 100);
					double comisionAgencia = (valorSubTotalOrden - descuento - descuentosVarios) * (ordenMedioIf.getPorcentajeComisionAgencia().doubleValue() / 100D);
					Long productoProveedorId = ordenMedioIf.getProductoProveedorId();
					porcentajeComisionAgencia = ordenMedioIf.getPorcentajeComisionAgencia().doubleValue();
					Map queryMap = new HashMap();
					queryMap.put("tipoOrden", "OM");
					queryMap.put("ordenId", ordenMedioIf.getId());
					String facturasAsociadas = getComprasAsociadasPorOrden(queryMap);
					if (facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
						ordenesSinFacturar += ordenMedioIf.getCodigo() + "\n";
					if (facturasProveedor.contains(facturasAsociadas))
						facturasAsociadas = SpiritConstants.getEmptyCharacter();
					if (!facturasProveedor.equals(SpiritConstants.getEmptyCharacter()) && !facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
						facturasProveedor += ", ";
					facturasProveedor += facturasAsociadas;
					
					
					
					// valor entre todas las ordenes de medio del proveedor
					double valor = valorSubTotalOrden - descuento - descuentosVarios + comisionAgencia;
					ClienteOficinaIf proveedor = mapaClienteOficina.get(ordenMedioIf.getProveedorId());
					
					filaPlantillaDetalle.add(proveedor.getDescripcion());
					filaPlantillaDetalle.add("");
					filaPlantillaDetalle.add(formatoDecimal.format(valorSubTotalOrden));
					filaPlantillaDetalle.add("0%");// descuento
					filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuento)+"%");// descuento
					
					// tipoDocumento diferente de null y sin rembolso
					double iva = 0D;
					if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N")) {
						if (documento == null || (documento != null && !documento.getBonificacion().equals(OPCION_SI)))
							iva = valor * IVA;
							
						ivaTotal = ivaTotal + iva;
						filaPlantillaDetalle.add(String.valueOf(Double.valueOf(IVA * 100).intValue()) + " %");
						
					} else {
						iva = IVA_CERO;
						filaPlantillaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");
					}
					
					filaPlantillaDetalle.add(formatoDecimal.format(porcentajeDescuentosVarios)+"%");

					if (valorSubTotalOrden > 0D) {
						data.setCantidad(new BigDecimal(0));
						data.setCantidadpedida(new BigDecimal(1));
						data.setDescripcion(proveedor.getDescripcion());
						data.setPrecio(BigDecimal.valueOf(valorSubTotalOrden));
						data.setPrecioreal(BigDecimal.valueOf(valorSubTotalOrden));
						data.setIce(new BigDecimal(0));
						data.setOtroimpuesto(new BigDecimal(0));
						data.setDescuento(BigDecimal.valueOf(descuento));
						data.setPorcentajeDescuentosVarios(BigDecimal.valueOf(porcentajeDescuentosVarios));
						data.setDescuentoGlobal(new BigDecimal(0));// Preguntar
						data.setProductoId(productoProveedorId);
						data.setValor(BigDecimal.valueOf(valor));
						data.setComprasReembolsoAsociadas(facturasProveedor);
										
						ProductoIf producto = mapaProductoProveedor.get(productoProveedorId);
						GenericoIf generico = mapaGenerico.get(producto.getGenericoId());

						if (tipoDocumentoIf != null && tipoDocumentoIf.getReembolso().equals("N"))
							data.setIva(BigDecimal.valueOf(iva));
						else
							data.setIva(BigDecimal.valueOf(0.0));
										
						// Para que no se tenga que actualizar el documento uno
						// a uno en los detalles
						documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipoDocumentoId(tipoDocumentoIf.getId()).iterator().next();
						data.setDocumentoId((documento!=null)?documento.getId():null);
						
						pedidoDetalleColeccion.add(data);
						modelPedidoDetalle.addRow(filaPlantillaDetalle);
						subTotal = subTotal + valorSubTotalOrden;
						
						if (generico.getCobraIvaCliente().equals("N"))
							totalIvaCero += valorSubTotalOrden;

						descuentoTotal = descuentoTotal + descuento;
						descuentosVariosTotal = descuentosVariosTotal + descuentosVarios;
						comisionAgenciaTotal = comisionAgenciaTotal + comisionAgencia;
						
						if (tipoDocumento.getReembolso().equals("N"))
							total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal + ivaTotal;
						else
							total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal;
						
						cargandoDetallesReferencia = false;					
					}
					
					getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
					
					if (tipoDocumento.getReembolso().equals("N")) {
						getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
						getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
					} else {
						getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
						getTxtIVAFinal().setText("0");
					}
					
					getTxtICEFinal().setText("0");
					getTxtOtroImpuestoFinal().setText("0");
					getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
					getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));
					getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
					getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
					
					
					// Por cada orden medio detalle mapa creo un registro para
					// la tabla PlanMedioFacturacion
					// esto me sirve para ir registrando que se ha facturado del
					// plan y que falta por facturar
					crearRegistrosPlanMedioFacturacion(ordenMedioIf);
				}
				
				
			} else if (tipoDocumento != null && tipoDocumento.getCodigo().equals("FAR") && tipoDocumento.getReembolso().equals("S")) {
												
				// Se recorre cada orden de Medio del Proveedor y
				// se obtiene el total de los valores en el for
				for (int i = 0; i < listaOrdenMedio.size(); i++) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) listaOrdenMedio.get(i);
					
					String facturasProveedor = SpiritConstants.getEmptyCharacter();
										
					double valorSubTotalOrden = ordenMedioIf.getValorSubtotal().doubleValue();
					double descuento = ordenMedioIf.getValorDescuento().doubleValue();
					double descuentosVarios = (valorSubTotalOrden - descuento) * (ordenMedioIf.getPorcentajeBonificacionCompra().doubleValue() / 100D);
					Long productoProveedorId = ordenMedioIf.getProductoProveedorId();
					Map queryMap = new HashMap();
					queryMap.put("tipoOrden", "OM");
					queryMap.put("ordenId", ordenMedioIf.getId());
					
					String facturasAsociadas = getComprasAsociadasPorOrden(queryMap);
					
					if (facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
						ordenesSinFacturar += ordenMedioIf.getCodigo() + "\n";
					if (facturasProveedor.contains(facturasAsociadas))
						facturasAsociadas = SpiritConstants.getEmptyCharacter();
					if (!facturasProveedor.equals(SpiritConstants.getEmptyCharacter()) && !facturasAsociadas.equals(SpiritConstants.getEmptyCharacter()))
						facturasProveedor += ", ";
					
					facturasProveedor += facturasAsociadas;
					
					// valor entre todas las ordenes de medio del proveedor
					double valorSubtotal = valorSubTotalOrden - descuento - descuentosVarios;
					ProductoIf producto = mapaProductoProveedor.get(productoProveedorId);
					GenericoIf generico = mapaGenerico.get(producto.getGenericoId());
					double iva = generico.getCobraIva().equals("S")?(valorSubtotal*(IVA)):0D;
					valorSubtotal = valorSubtotal + iva;
					
					// tipoDocumento rembolso
					descuento = 0D;
					descuentosVarios = 0D;
					iva = 0D;					
					
					Vector<String> filaPlantillaDetalle = new Vector<String>();
										
					ClienteOficinaIf proveedor = mapaClienteOficina.get(ordenMedioIf.getProveedorId());
					filaPlantillaDetalle.add(proveedor.getDescripcion());
					filaPlantillaDetalle.add("");
					filaPlantillaDetalle.add(formatoDecimal.format(valorSubtotal));
					filaPlantillaDetalle.add("0%");// descuento
					filaPlantillaDetalle.add("0%");// porcentaje descuento
					filaPlantillaDetalle.add(String.valueOf(IVA_CERO.intValue()) + " %");				
					filaPlantillaDetalle.add("0%"); // porcentaje descuentos
													// varios

					if (valorSubtotal > 0D) {
						PedidoDetalleData data = new PedidoDetalleData();						
						data.setCantidad(new BigDecimal(0));
						data.setCantidadpedida(new BigDecimal(1));
						data.setDescripcion(proveedor.getDescripcion());
						data.setPrecio(BigDecimal.valueOf(valorSubtotal));
						data.setPrecioreal(BigDecimal.valueOf(valorSubtotal));
						data.setIce(new BigDecimal(0));
						data.setOtroimpuesto(new BigDecimal(0));
						data.setDescuento(BigDecimal.valueOf(descuento));
						data.setPorcentajeDescuentosVarios(BigDecimal.valueOf(0));
						data.setDescuentoGlobal(new BigDecimal(0));// Preguntar
						data.setProductoId(productoProveedorId);
						data.setValor(BigDecimal.valueOf(valorSubtotal));
						data.setComprasReembolsoAsociadas(facturasProveedor);
						// Para que no se tenga que actualizar el documento uno
						// a uno en los detalles
						documento = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipoDocumentoId(tipoDocumentoIf.getId()).iterator().next();
						data.setDocumentoId((documento!=null)?documento.getId():null);
						
						pedidoDetalleColeccion.add(data);
						modelPedidoDetalle.addRow(filaPlantillaDetalle);
						subTotal = subTotal + valorSubtotal;
						
						if (generico.getCobraIvaCliente().equals("N"))
							totalIvaCero += valorSubtotal;

						descuentoTotal = descuentoTotal + descuento;
						descuentosVariosTotal = descuentosVariosTotal + descuentosVarios;
						// comisionAgenciaTotal = comisionAgenciaTotal +
						// comisionAgencia;
						
						if (tipoDocumento.getReembolso().equals("N"))
							total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal + ivaTotal;
						else
							total = subTotal - descuentoTotal - descuentosVariosTotal + comisionAgenciaTotal;
						
						// cargandoDetallesReferencia = true;
						cargandoDetallesReferencia = false;
					}
					
					getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
					
					if (tipoDocumento.getReembolso().equals("N")) {
						getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
						getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
					} else {
						getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
						getTxtIVAFinal().setText("0");
					}
					
					getTxtICEFinal().setText("0");
					getTxtOtroImpuestoFinal().setText("0");
					getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
					getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosTotal)));
					getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgenciaTotal)));
					getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
					
					// Por cada orden medio detalle mapa creo un registro para
					// la tabla PlanMedioFacturacion
					// esto me sirve para ir registrando que se ha facturado del
					// plan y que falta por facturar
					crearRegistrosPlanMedioFacturacion(ordenMedioIf);
										
					if (subTotal > 0D && !ordenesSinFacturar.equals(SpiritConstants.getEmptyCharacter()))
						SpiritAlert.createAlert("Se han cargado los detalles del presupuesto correctamente, aunque se presentó un problema \n al cargar datos de las facturas de proveedores asociadas.\n\nLas facturas de las siguientes órdenes no han sido ingresadas al sistema:\n\n" + ordenesSinFacturar, SpiritAlert.WARNING);
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void crearRegistrosPlanMedioFacturacion(OrdenMedioIf ordenMedioIf)
			throws GenericBusinessException {
		Collection ordenesDetalle = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
		Iterator ordenesDetalleIt = ordenesDetalle.iterator();
		while(ordenesDetalleIt.hasNext()){
			OrdenMedioDetalleIf ordenDetalle = (OrdenMedioDetalleIf)ordenesDetalleIt.next();
			Collection ordenesDetalleMapa = SessionServiceLocator.getOrdenMedioDetalleMapaSessionService().findOrdenMedioDetalleMapaByOrdenMedioDetalleId(ordenDetalle.getId());
			Iterator ordenesDetalleMapaIt = ordenesDetalleMapa.iterator();
			while(ordenesDetalleMapaIt.hasNext()){
				OrdenMedioDetalleMapaIf ordenDetalleMapa = (OrdenMedioDetalleMapaIf)ordenesDetalleMapaIt.next();
				
				PlanMedioFacturacionData planMedioFacturacionData = new PlanMedioFacturacionData();
				planMedioFacturacionData.setOrdenMedioDetalleMapaId(ordenDetalleMapa.getId());
				planMedioFacturacionData.setOrdenMedioDetalleId(ordenDetalle.getId());
				planMedioFacturacionData.setOrdenMedioId(ordenMedioIf.getId());
				planMedioFacturacionData.setPlanMedioId(planMedioIf.getId());
				
				planMedioFacturacionData.setProductoClienteId(ordenDetalle.getProductoClienteId());
				planMedioFacturacionData.setCampanaProductoVersionId(ordenDetalle.getCampanaProductoVersionId());
				
				planMedioFacturacionData.setFechaInicio(new Timestamp(fechaInicioPlanMedioFormaPago.getTime()));
				planMedioFacturacionData.setFechaFin(new Timestamp(fechaFinPlanMedioFormaPago.getTime()));
				planMedioFacturacionData.setCanje("N");// S = si ; N = no
				planMedioFacturacionData.setProveedorId(ordenMedioIf.getProveedorId());
				planMedioFacturacionData.setPorcentajeCanje(new BigDecimal(0));
				planMedioFacturacionData.setEstado("-");
				listaPlanMedioFacturacionEscogido.add(planMedioFacturacionData);
			}
		}
		
		// pongo en esta lista las ordenes facturadas para en caso de haber
		// factura de comision
		// solo se tome en cuenta estas ordenes y no el plan completo
		mapaOrdenesMedioFacturadas.put(ordenMedioIf.getId(), ordenMedioIf.getId());
	}

	private String getComprasAsociadasPorOrden(Map queryMap) throws GenericBusinessException {
		String facturasProveedor = SpiritConstants.getEmptyCharacter();
		Iterator<CompraIf> it = SessionServiceLocator.getCompraSessionService().findCompraByOrdenAsociadaQuery(queryMap).iterator();
		if (it.hasNext()) {
			CompraIf facturaProveedor = it.next();
			if (!facturasProveedor.equals(SpiritConstants.getEmptyCharacter()))
				facturasProveedor += SpiritConstants.getCommaCharacter() + SpiritConstants.getBlankSpaceCharacter();
			facturasProveedor += facturaProveedor.getPreimpreso();
		}
		
		return facturasProveedor;
	}
	
	public void cargarDetallesPlanMedios(String preimpreso) {
		try {
			TipoDocumentoIf tipoDocumento = (getCmbTipoDocumento().getSelectedItem() != null)?(TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem():null;
			DocumentoIf documento = (getCmbDocumento().getSelectedItem() != null)?(DocumentoIf) getCmbDocumento().getSelectedItem():null;
			
			modelPedidoDetalle = (DefaultTableModel) getTblPedidoDetalle().getModel();
			
			Collection listaOrdenesMedio = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planMedioIf.getId());
			Iterator listaOrdenesMedioIt = listaOrdenesMedio.iterator();
			
			if(preimpreso != null){
				preimpresoFacturaReembolso = preimpreso;
			}

			if (tipoDocumento.getCodigo().equals("FCO")) {					
				while (listaOrdenesMedioIt.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) listaOrdenesMedioIt.next();
										
					// si el mapa tiene registros entonces es facturacion
					// parcial y se debe tomar encuenta solo las ordenes
					// facturadas
					if(mapaOrdenesMedioFacturadas.isEmpty() || mapaOrdenesMedioFacturadas.get(ordenMedioIf.getId()) != null){
						double subtotal = 0D, descuentoCompra = 0D, descuentoVenta = 0D, comisionAgencia = 0D, iva = 0D;
						double bonificacionCompra = 0D, bonificacionVenta= 0D;					
						subtotal = ordenMedioIf.getValorSubtotal().doubleValue();
						// calculo todos los porcentajes por seguridad, en lugar
						// de utilizar los valores ya calculados de la base.
						descuentoCompra = ordenMedioIf.getValorDescuento().doubleValue();
						bonificacionCompra = (subtotal-descuentoCompra) * (ordenMedioIf.getPorcentajeBonificacionCompra().doubleValue()/100D);
						descuentoVenta = subtotal * (ordenMedioIf.getPorcentajeDescuentoVenta().doubleValue()/100D);
						comisionAgencia = (subtotal-descuentoVenta) * (ordenMedioIf.getPorcentajeComisionAgencia().doubleValue()/100D);
						bonificacionVenta = (subtotal-descuentoVenta+comisionAgencia) * (ordenMedioIf.getPorcentajeBonificacionVenta().doubleValue()/100D);
						
						subtotal = descuentoCompra + bonificacionCompra - descuentoVenta + comisionAgencia - bonificacionVenta;
						iva = subtotal * IVA;
						ivaTotal = ivaTotal + iva;
						
						if (subtotal > 0D) {
							subTotal = subTotal + subtotal;
							total = subTotal + ivaTotal;
						}
						PedidoDetalleData data = new PedidoDetalleData();					
						Map parameterMap = new HashMap();
						parameterMap.put("empresaId", Parametros.getIdEmpresa());
						parameterMap.put("codigo", "COM");
						Iterator it = SessionServiceLocator.getGenericoSessionService().findGenericoByQuery(parameterMap).iterator();
						GenericoIf genericoComision = (it.hasNext())?(GenericoIf) it.next():null;
						it = SessionServiceLocator.getProductoSessionService().findProductoByGenericoId(genericoComision.getId()).iterator();
						ProductoIf productoComision = (it.hasNext())?(ProductoIf) it.next():null;
						data.setProductoId(productoComision.getId());
						
						data.setProductoId(ordenMedioIf.getProductoProveedorId());				
						data.setDocumentoId((documento!=null)?documento.getId():null);
						data.setCantidadpedida(BigDecimal.valueOf(1D));
						data.setCantidad(BigDecimal.valueOf(1D));
						
						ClienteOficinaIf proveedor = mapaClienteOficina.get(ordenMedioIf.getProveedorId());
						data.setDescripcion(proveedor.getDescripcion());
											
						data.setPrecio(BigDecimal.valueOf(subtotal));
						data.setPrecioreal(BigDecimal.valueOf(subtotal));
						data.setValor(BigDecimal.valueOf(subtotal));
						data.setIva(BigDecimal.valueOf(iva));
						data.setIce(BigDecimal.valueOf(0.0));
						data.setOtroimpuesto(BigDecimal.valueOf(0.0));
						data.setDescuento(BigDecimal.ZERO);
						data.setPorcentajeDescuentosVarios(BigDecimal.ZERO);
						data.setDescuentoGlobal(BigDecimal.ZERO);
						pedidoDetalleColeccion.add(data);
						
						Vector<String> filaPlantillaDetalle = new Vector<String>();
						filaPlantillaDetalle.add("COMISION DE AGENCIA");
						filaPlantillaDetalle.add("1");
						filaPlantillaDetalle.add(formatoDecimal.format(Utilitarios.redondeoValor(subtotal)));
						filaPlantillaDetalle.add("0 % ");
						filaPlantillaDetalle.add("0 % ");
						filaPlantillaDetalle.add(formatoDecimal.format(Utilitarios.redondeoValor(IVA * 100)) + " %");
						filaPlantillaDetalle.add("0 % ");
						modelPedidoDetalle.addRow(filaPlantillaDetalle);
					}					
				}
				
				// CREAR DETALLE COMISION
				// ACTUALIZAR TEXTFIELDS TOTALES
				getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
				getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(BigDecimal.ZERO)));
				getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(BigDecimal.ZERO)));
				getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
				getTxtICEFinal().setText("0");
				getTxtOtroImpuestoFinal().setText("0");
				getTxtPorcentajeComision().setText("0");
				getTxtValorComision().setText("0");
				getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
				
				// recien puedo limpiar el mapa
				mapaOrdenesMedioFacturadas.clear();
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	// basado en plan medio detalle, no esta correcto, el actual esta basado en
	// ordenes de medio
	public void cargarDetallesPlanMedios2(String preimpreso) {
		try {
			TipoDocumentoIf tipoDocumento = (getCmbTipoDocumento().getSelectedItem() != null)?(TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem():null;
			DocumentoIf documento = (getCmbDocumento().getSelectedItem() != null)?(DocumentoIf) getCmbDocumento().getSelectedItem():null;
			
			modelPedidoDetalle = (DefaultTableModel) getTblPedidoDetalle().getModel();
			
			PlanMedioMesIf planMedioMesIf = (PlanMedioMesIf)SessionServiceLocator.getPlanMedioMesSessionService().findPlanMedioMesByPlanmedioId(planMedioIf.getId()).iterator().next();
			
			Collection listaPlanMedioDetalle = SessionServiceLocator.getPlanMedioDetalleSessionService().findPlanMedioDetalleByPlanMedioMesId(planMedioMesIf.getId());
			Iterator listaPlanMedioDetalleIt = listaPlanMedioDetalle.iterator();
			
			// Long productoId = 0L;

			if (tipoDocumento.getCodigo().equals("FCO")) {					
				while (listaPlanMedioDetalleIt.hasNext()) {
					PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) listaPlanMedioDetalleIt.next();
										
					double subtotal = 0D, descuentoCompra = 0D, descuentoVenta = 0D, comisionAgencia = 0D, iva = 0D;
					double bonificacionCompra = 0D, bonificacionVenta= 0D;					
					subtotal = planMedioDetalleIf.getValorSubtotal().doubleValue();
					// calculo todos los porcentajes por seguridad, en lugar de
					// utilizar los valores ya calculados de la base.
					descuentoCompra = subtotal * (planMedioDetalleIf.getPorcentajeDescuento().doubleValue()/100D);
					bonificacionCompra = (subtotal-descuentoCompra) * (planMedioDetalleIf.getPorcentajeBonificacionCompra().doubleValue()/100D);
					descuentoVenta = subtotal * (planMedioDetalleIf.getPorcentajeDescuentoVenta().doubleValue()/100D);
					comisionAgencia = (subtotal-descuentoVenta) * (planMedioDetalleIf.getPorcentajeComisionAgencia().doubleValue()/100D);
					bonificacionVenta = (subtotal-descuentoVenta+comisionAgencia) * (planMedioDetalleIf.getPorcentajeBonificacionVenta().doubleValue()/100D);
					
					subtotal = descuentoCompra + bonificacionCompra - descuentoVenta + comisionAgencia - bonificacionVenta;
					iva = subtotal * IVA;
					ivaTotal = ivaTotal + iva;
					
					if (subtotal > 0D) {
						subTotal = subTotal + subtotal;
						total = subTotal + ivaTotal;
					}
					PedidoDetalleData data = new PedidoDetalleData();					
					Map parameterMap = new HashMap();
					parameterMap.put("empresaId", Parametros.getIdEmpresa());
					parameterMap.put("codigo", "COM");
					Iterator it = SessionServiceLocator.getGenericoSessionService().findGenericoByQuery(parameterMap).iterator();
					GenericoIf genericoComision = (it.hasNext())?(GenericoIf) it.next():null;
					it = SessionServiceLocator.getProductoSessionService().findProductoByGenericoId(genericoComision.getId()).iterator();
					ProductoIf productoComision = (it.hasNext())?(ProductoIf) it.next():null;
					data.setProductoId(productoComision.getId());
					
					data.setProductoId(planMedioDetalleIf.getProductoProveedorId());				
					data.setDocumentoId((documento!=null)?documento.getId():null);
					data.setCantidadpedida(BigDecimal.valueOf(1D));
					data.setCantidad(BigDecimal.valueOf(1D));
					
					if(preimpreso != null){
						data.setDescripcion("SOBRE F: " + preimpreso);
					}else{
						data.setDescripcion("\n");
					}
					
					data.setPrecio(BigDecimal.valueOf(subtotal));
					data.setPrecioreal(BigDecimal.valueOf(subtotal));
					data.setValor(BigDecimal.valueOf(subtotal));
					data.setIva(BigDecimal.valueOf(iva));
					data.setIce(BigDecimal.valueOf(0.0));
					data.setOtroimpuesto(BigDecimal.valueOf(0.0));
					data.setDescuento(BigDecimal.ZERO);
					data.setPorcentajeDescuentosVarios(BigDecimal.ZERO);
					data.setDescuentoGlobal(BigDecimal.ZERO);
					pedidoDetalleColeccion.add(data);
					
					Vector<String> filaPlantillaDetalle = new Vector<String>();
					filaPlantillaDetalle.add("COMISION DE AGENCIA");
					filaPlantillaDetalle.add("1");
					filaPlantillaDetalle.add(formatoDecimal.format(Utilitarios.redondeoValor(subtotal)));
					filaPlantillaDetalle.add("0 % ");
					filaPlantillaDetalle.add("0 % ");
					filaPlantillaDetalle.add(formatoDecimal.format(Utilitarios.redondeoValor(IVA * 100)) + " %");
					filaPlantillaDetalle.add("0 % ");
					modelPedidoDetalle.addRow(filaPlantillaDetalle);
				}
				
				// CREAR DETALLE COMISION
				// ACTUALIZAR TEXTFIELDS TOTALES
				getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
				getTxtDescuentoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(BigDecimal.ZERO)));
				getTxtDescuentosVariosTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(BigDecimal.ZERO)));
				getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
				getTxtICEFinal().setText("0");
				getTxtOtroImpuestoFinal().setText("0");
				getTxtPorcentajeComision().setText("0");
				getTxtValorComision().setText("0");
				getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}	

	// método que me permite poder verificar si por lo menos un item de los
	// pedidos detalle
	// posee lote, caso contrario si ninguno tiene lote, esta funcion devuelve
	// un valor false
	// lo que no permitira generar una factura de ese pedido
	public boolean verificarLotes() {
		boolean bandera = false;

		for (int j = 0; j < pedidoDetalleColeccion.size(); j++) {
			PedidoDetalleIf pedidoDetalleIf = (PedidoDetalleIf) pedidoDetalleColeccion.get(j);

			try {
				ProductoIf productoIf = mapaProductoProveedor.get(pedidoDetalleIf.getProductoId());
				GenericoIf genericoIf = mapaGenerico.get(productoIf.getGenericoId());
				String tipoProducto = genericoIf.getServicio();

				if ("S".equals(tipoProducto)) {
					DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
					int restante = pedidoDetalleIf.getCantidadpedida().intValue();
					Long idProducto = pedidoDetalleIf.getProductoId();
					Long idBodega = ((BodegaIf) getCmbBodega().getSelectedItem()).getId();
					LoteIf loteIf = null;
					int cantidadStock = 0;
					Collection lotes;
					if (pedidoDetalleIf.getLoteId() == null)
						lotes = SessionServiceLocator.getLoteSessionService().findLoteByProductoIdAndEstadoAndFecha(idProducto, new java.sql.Date(fechaCreacion.getTime()));
					else
						lotes = SessionServiceLocator.getLoteSessionService().findLoteById(pedidoDetalleIf.getLoteId());

					Iterator itLotes = lotes.iterator();

					if (itLotes.hasNext() == false)
						bandera = false;
					else
						bandera = true;

					if (bandera) {
						while (itLotes.hasNext()) {
							// inicalizo la variable sumatoria, contiene el
							// valor inicial de la cantidad de cada stock
							// se va añadiendo a medida que se vaya recorriendo
							// el numero de stocks
							int sumatoria = 0;
							loteIf = (LoteIf) itLotes.next();
							Collection stocks = SessionServiceLocator.getStockSessionService().findStockByIdLoteAndIdBodega(loteIf.getId(), idBodega);
							Iterator itStocks = stocks.iterator();

							while (itStocks.hasNext()) {
								StockIf stockIf = (StockIf) itStocks.next();
								// valido si el stock de cual estoy leyendo los
								// datos tiene cantidades mayores a cero
								if (stockIf.getCantidad().intValue() > 0) {
									// a sumatoria le agrego la cantidad del
									// stock leido
									sumatoria = sumatoria + stockIf.getCantidad().intValue();
									// a cantidad stock le agrego la cantidad
									// que posee el stock
									cantidadStock = stockIf.getCantidad().intValue();
									// dismunyo la cantidad restante que aun
									// falta por facturar
									restante = restante - cantidadStock;
									// esto se da si lo que obtuve del stock es
									// igual a lo que yo queria
									if (restante == 0) {
										break;
									}
									// esto se da si lo que obtuve del stock aun
									// no me alcanza para terminar de facturar
									else if (restante > 0) {

									}
									// esto se da en caso de que logre obtener
									// todo lo que necesitaba para facturar
									else {
										break;
									}
								}
							}
							// una vez leido de los lotes, procedo a generar el
							// detalle de la factura realizo los calculos para
							// poder generar el detalle con los nuevos valores
							// si el valor de sumatoria es menor a lo que
							// necesitaba genero el detalle con el valor de
							// sumatoria caso contrario obtengo la diferencia
							// entre sumatoria y el restante que
							// vendria a ser lo que me faltaba por facturar si
							// el restante es menor a 0 significa que lo que
							// tenie en el stock era suficiente para poder
							// facturar por lo tanto salgo del while de los
							// lotes
							if (restante <= 0)
								break;
						}
					}
				} else {
					bandera = true;
				}
			} catch (GenericBusinessException e) {

			}
		}

		if (!bandera)
			SpiritAlert.createAlert("La Factura no se generará , el item o items que seleccionó no posee Lotes!", SpiritAlert.WARNING);

		return bandera;
	}

	private PedidoIf registrarPedido() {
		PedidoData data = new PedidoData();
		java.sql.Timestamp fechaPedido = new java.sql.Timestamp(getCmbFechaPedido().getDate().getTime());
		String codigo = getCodigoPedido(new java.sql.Date(fechaPedido.getYear(), fechaPedido.getMonth(), fechaPedido.getDate()));
		data.setCodigo(codigo);
		data.setFechaPedido(fechaPedido);
		java.util.Date fechaCreacion = new java.util.Date();
		data.setFechaCreacion(new java.sql.Timestamp(fechaCreacion.getTime()));
		
		if(getCmbFechaVencimiento().getDate() != null){
			data.setFechaVencimiento(new java.sql.Timestamp(getCmbFechaVencimiento().getDate().getTime()));
		}
		
		OficinaIf oficina = (OficinaIf)getCmbOficinaEmpresa().getSelectedItem();
		data.setOficinaId(oficina.getId());
		
		data.setTipodocumentoId(((TipoDocumentoIf) this.getCmbTipoDocumento().getSelectedItem()).getId());
		data.setClienteoficinaId(clienteOficinaIf.getId());
		data.setTipoidentificacionId(((TipoIdentificacionIf) getCmbTipoIdentificacion().getSelectedItem()).getId());
		data.setFormapagoId(((FormaPagoIf) this.getCmbFormaPago().getSelectedItem()).getId());
		data.setMonedaId(((MonedaIf) this.getCmbMoneda().getSelectedItem()).getId());

		if (puntoImpresionIf != null)
			data.setPuntoimpresionId(puntoImpresionIf.getId());

		data.setOrigendocumentoId(((OrigenDocumentoIf) this.getCmbOrigenDocumento().getSelectedItem()).getId());
		
		if ( this.getCmbVendedor().getSelectedItem() != null )
			data.setVendedorId(((EmpleadoIf)this.getCmbVendedor().getSelectedItem() ).getId());
		
		data.setBodegaId(((BodegaIf) this.getCmbBodega().getSelectedItem()).getId());
		data.setListaprecioId(((ListaPrecioIf) this.getCmbListaPrecio().getSelectedItem()).getId());
		data.setUsuarioId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
		data.setDiasvalidez(Double.valueOf(this.getTxtDiasValidez().getText()).intValue());
		
		
		TipoReferenciaPedido tr = (TipoReferenciaPedido) getCmbTipoReferencia().getSelectedItem();
		data.setTiporeferencia(tr.getLetra());
		/*
		 * if(this.getCmbTipoReferencia().getSelectedItem().toString().equals(NOMBRE_REFERENCIA_NINGUNO))
		 * data.setTiporeferencia(REFERENCIA_NINGUNO); else
		 * if(this.getCmbTipoReferencia().getSelectedItem().toString().equals(NOMBRE_REFERENCIA_PRESUPUESTO))
		 * data.setTiporeferencia(REFERENCIA_PRESUPUESTO); else
		 * if(this.getCmbTipoReferencia().getSelectedItem().toString().equals(NOMBRE_REFERENCIA_PLAN_MEDIOS))
		 * data.setTiporeferencia(REFERENCIA_PLAN_MEDIOS);
		 */
				
		// data.setTiporeferencia(this.getCmbTipoReferencia().getSelectedItem().toString().substring(0,
		// 1));
		EstadoPedido estado = (EstadoPedido) getCmbEstado().getSelectedItem();
		data.setEstado(estado.getLetra());
		/*
		 * if
		 * (NOMBRE_ESTADO_COMPLETO.equals(this.getCmbEstado().getSelectedItem().toString()))
		 * data.setEstado(ESTADO_COMPLETO); else if
		 * (NOMBRE_ESTADO_INCOMPLETO.equals(this.getCmbEstado().getSelectedItem().toString()))
		 * data.setEstado(ESTADO_INCOMPLETO); else if
		 * (NOMBRE_ESTADO_PENDIENTE.equals(this.getCmbEstado().getSelectedItem().toString()))
		 * data.setEstado(ESTADO_PENDIENTE); else if
		 * (NOMBRE_ESTADO_ANULADO.equals(this.getCmbEstado().getSelectedItem().toString()))
		 * data.setEstado(ESTADO_ANULADO); else if
		 * (NOMBRE_ESTADO_COTIZACION.equals(this.getCmbEstado().getSelectedItem().toString()))
		 * data.setEstado(ESTADO_COTIZACION);
		 */

		data.setDireccion(getTxtDireccion().getText());
		data.setObservacion(getTxtObservacion().getText());
		data.setIdentificacion(getTxtIdentificacion().getText());
		
		if (tr == TipoReferenciaPedido.PRESUPUESTO && presupuestoIf != null){
			if(presupuestosIf.size() > 1){
				//facturación multiple
				data.setReferencia("M");
			}else{
				data.setReferencia(presupuestoIf.getCodigo());
			}
		}
		else if (tr == TipoReferenciaPedido.PLAN_MEDIOS){
			//multiple
			if(planesMedioIf.size() > 0){
				//la letra M me va a indicar que es Multiple y tengo que buscar referencia en la
				//tablas Plan_Medio_Facturacion
				data.setReferencia("M");
			}
			//normal
			else if(planMedioIf != null){
				data.setReferencia(planMedioIf.getCodigo());
			}
		}
		else if (tr == TipoReferenciaPedido.COMBINADO){
			data.setReferencia("M");
		}
		else{
			data.setReferencia(getTxtReferencia().getText());
		}
		
		data.setTelefono(getTxtTelefono().getText());
		if ( !"".equalsIgnoreCase(getTxtContacto().getText()) ){
			data.setContacto(getTxtContacto().getText());
		}
		else{
			data.setContacto(" ");
		}
		
		data.setPorcentajeComisionAgencia(BigDecimal.valueOf(porcentajeComisionAgencia));
		
		try {
			// para setear el equipo del ejecutivo
			if (getCmbDirectorCuentas().getItemCount() > 0) {
				String equipoDirector = (String)getCmbDirectorCuentas().getSelectedItem();
				String codigoEquipo = equipoDirector.split(" - ")[0];
				EquipoTrabajoIf equipoSeleccionado = (EquipoTrabajoIf)SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByCodigo(codigoEquipo).iterator().next();
				data.setEquipoId(equipoSeleccionado.getId());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		// Seteo datos de Negociación si la hubiera.
		if(getCbNegociacionDirecta().isSelected()){
			data.setClienteNegociacionId(clienteOficinaNegociacionIf.getId());
			data.setTipoNegociacion(TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA);
			BigDecimal porcentajeNegociacionDirecta = new BigDecimal(getTxtPorcentajeNegociacionDirecta().getText().replaceAll(",", ""));
			data.setPorcentajeNegociacionDirecta(porcentajeNegociacionDirecta);
			BigDecimal porcentajeDescuentoNegociacion = new BigDecimal(getTxtDsctoCompraPorcentaje().getText().replaceAll(",", ""));
			data.setPorcentajeDescuentoNegociacion(porcentajeDescuentoNegociacion);			
		}else if(getCbComisionPura().isSelected()){
			data.setClienteNegociacionId(clienteOficinaNegociacionIf.getId());
			data.setTipoNegociacion(TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA);
			BigDecimal porcentajeDescuentoNegociacion = new BigDecimal(getTxtDsctoCompraPorcentaje().getText().replaceAll(",", ""));
			data.setPorcentajeDescuentoNegociacion(porcentajeDescuentoNegociacion);			
		}
		
		if(getTxtAutorizacionSAP().getText() != null && !getTxtAutorizacionSAP().getText().equals("")){
			data.setAutorizacionSap(getTxtAutorizacionSAP().getText());
		}

		return data;
	}
	
	private String getCodigoPedido(java.sql.Date fechaPedido) {
		String codigo = "";
		String anioPedido = Utilitarios.getYearFromDate(fechaPedido);
		codigo += anioPedido + "-";
		return codigo;
	}

	private PedidoIf registrarPedidoForUpdate() {
		fechaPedido = getCmbFechaPedido().getDate();
		pedido.setFechaPedido(new java.sql.Timestamp(fechaPedido.getTime()));
		
		if(getCmbFechaVencimiento().getDate() != null){
			pedido.setFechaVencimiento(new java.sql.Timestamp(getCmbFechaVencimiento().getDate().getTime()));
		}
		
		OficinaIf oficina = (OficinaIf)getCmbOficinaEmpresa().getSelectedItem();
		pedido.setOficinaId(oficina.getId());
		
		pedido.setTipodocumentoId(((TipoDocumentoIf) this.getCmbTipoDocumento().getSelectedItem()).getId());
		pedido.setClienteoficinaId(clienteOficinaIf.getId());
		pedido.setTipoidentificacionId(((TipoIdentificacionIf) getCmbTipoIdentificacion().getSelectedItem()).getId());
		pedido.setFormapagoId(((FormaPagoIf) this.getCmbFormaPago().getSelectedItem()).getId());
		pedido.setMonedaId(((MonedaIf) this.getCmbMoneda().getSelectedItem()).getId());
		if (puntoImpresionIf != null)
			pedido.setPuntoimpresionId(puntoImpresionIf.getId());
		pedido.setOrigendocumentoId(((OrigenDocumentoIf) this.getCmbOrigenDocumento().getSelectedItem()).getId());
		if (getCmbVendedor().getSelectedItem() != null)
			pedido.setVendedorId(((EmpleadoIf) this.getCmbVendedor().getSelectedItem()).getId());
		pedido.setBodegaId(((BodegaIf) this.getCmbBodega().getSelectedItem()).getId());
		pedido.setListaprecioId(((ListaPrecioIf) this.getCmbListaPrecio().getSelectedItem()).getId());
		pedido.setDiasvalidez(Double.valueOf(this.getTxtDiasValidez().getText()).intValue());
		
		TipoReferenciaPedido tr = (TipoReferenciaPedido) getCmbTipoReferencia().getSelectedItem();
		pedido.setTiporeferencia(tr.getLetra());
		/*
		 * if(this.getCmbTipoReferencia().getSelectedItem().toString().equals(NOMBRE_REFERENCIA_NINGUNO))
		 * pedido.setTiporeferencia(REFERENCIA_NINGUNO); else
		 * if(this.getCmbTipoReferencia().getSelectedItem().toString().equals(NOMBRE_REFERENCIA_PRESUPUESTO))
		 * pedido.setTiporeferencia(REFERENCIA_PRESUPUESTO); else
		 * if(this.getCmbTipoReferencia().getSelectedItem().toString().equals(NOMBRE_REFERENCIA_PLAN_MEDIOS))
		 * pedido.setTiporeferencia(REFERENCIA_PLAN_MEDIOS);
		 */
		// pedido.setTiporeferencia(this.getCmbTipoReferencia().getSelectedItem().toString().substring(0,
		// 1));
		EstadoPedido estado = (EstadoPedido) getCmbEstado().getSelectedItem();
		pedido.setEstado(estado.getLetra());
		/*
		 * if
		 * (NOMBRE_ESTADO_COMPLETO.equals(this.getCmbEstado().getSelectedItem().toString()))
		 * pedido.setEstado(ESTADO_COMPLETO); else if
		 * (NOMBRE_ESTADO_INCOMPLETO.equals(this.getCmbEstado().getSelectedItem().toString()))
		 * pedido.setEstado(ESTADO_INCOMPLETO); else if
		 * (NOMBRE_ESTADO_PENDIENTE.equals(this.getCmbEstado().getSelectedItem().toString()))
		 * pedido.setEstado(ESTADO_PENDIENTE); else if
		 * (NOMBRE_ESTADO_ANULADO.equals(this.getCmbEstado().getSelectedItem().toString()))
		 * pedido.setEstado(ESTADO_ANULADO); else if
		 * (NOMBRE_ESTADO_COTIZACION.equals(this.getCmbEstado().getSelectedItem().toString()))
		 * pedido.setEstado(ESTADO_COTIZACION);
		 */

		pedido.setDireccion(this.getTxtDireccion().getText());
		pedido.setObservacion(this.getTxtObservacion().getText());
		pedido.setIdentificacion(this.getTxtIdentificacion().getText());
		// if
		// (getCmbTipoReferencia().getSelectedItem().equals(NOMBRE_REFERENCIA_PRESUPUESTO)
		// && presupuestoIf != null)
		if (tr == TipoReferenciaPedido.PRESUPUESTO && presupuestoIf != null)
			pedido.setReferencia(presupuestoIf.getCodigo());
		// else if
		// (getCmbTipoReferencia().getSelectedItem().equals(NOMBRE_REFERENCIA_PLAN_MEDIOS)
		// && planMedioIf != null)
		else if (tr == TipoReferenciaPedido.PLAN_MEDIOS && planMedioIf != null)
			pedido.setReferencia(planMedioIf.getCodigo());
		else
			pedido.setReferencia(getTxtReferencia().getText());
		pedido.setTelefono(this.getTxtTelefono().getText());
		if ( !"".equalsIgnoreCase(this.getTxtContacto().getText()) )
			pedido.setContacto(this.getTxtContacto().getText());
		else
			pedido.setContacto("");
		
		pedido.setPorcentajeComisionAgencia(BigDecimal.valueOf(porcentajeComisionAgencia));
		
		try {
			// para setear el equipo del ejecutivo
			if (getCmbDirectorCuentas().getItemCount() > 0) {
				String equipoDirector = (String)getCmbDirectorCuentas().getSelectedItem();
				String codigoEquipo = equipoDirector.split(" - ")[0];		
				EquipoTrabajoIf equipoSeleccionado = (EquipoTrabajoIf)SessionServiceLocator.getEquipoTrabajoSessionService().findEquipoTrabajoByCodigo(codigoEquipo).iterator().next();
				pedido.setEquipoId(equipoSeleccionado.getId());
			}
			
			// Seteo datos de Negociación si la hubiera.
			if(getCbNegociacionDirecta().isSelected()){
				pedido.setClienteNegociacionId(clienteOficinaNegociacionIf.getId());
				pedido.setTipoNegociacion(TIPO_PRESUPUESTO_FACTURACION_NEGOCIACION_DIRECTA);
				BigDecimal porcentajeNegociacionDirecta = new BigDecimal(getTxtPorcentajeNegociacionDirecta().getText().replaceAll(",", ""));
				pedido.setPorcentajeNegociacionDirecta(porcentajeNegociacionDirecta);
				BigDecimal porcentajeDescuentoNegociacion = new BigDecimal(getTxtDsctoCompraPorcentaje().getText().replaceAll(",", ""));
				pedido.setPorcentajeDescuentoNegociacion(porcentajeDescuentoNegociacion);			
			}else if(getCbComisionPura().isSelected()){
				pedido.setPorcentajeNegociacionDirecta(null);
				pedido.setClienteNegociacionId(clienteOficinaNegociacionIf.getId());
				pedido.setTipoNegociacion(TIPO_PRESUPUESTO_FACTURACION_COMISION_PURA);
				BigDecimal porcentajeDescuentoNegociacion = new BigDecimal(getTxtDsctoCompraPorcentaje().getText().replaceAll(",", ""));
				pedido.setPorcentajeDescuentoNegociacion(porcentajeDescuentoNegociacion);			
			}
			
			if(getTxtAutorizacionSAP().getText() != null && !getTxtAutorizacionSAP().getText().equals("")){
				pedido.setAutorizacionSap(getTxtAutorizacionSAP().getText());
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}

		return pedido;
	}
	
	private PedidoIf registrarPedidoAnterior() {
		PedidoData pedidoAnterior = new PedidoData();
		pedidoAnterior.setId(pedido.getId());
		pedidoAnterior.setOficinaId(pedido.getOficinaId());
		pedidoAnterior.setTipodocumentoId(pedido.getTipodocumentoId());
		pedidoAnterior.setCodigo(pedido.getCodigo());
		pedidoAnterior.setClienteoficinaId(pedido.getClienteoficinaId());
		pedidoAnterior.setTipoidentificacionId(pedido.getTipoidentificacionId());
		pedidoAnterior.setIdentificacion(pedido.getIdentificacion());
		pedidoAnterior.setFormapagoId(pedido.getFormapagoId());
		pedidoAnterior.setMonedaId(pedido.getMonedaId());
		pedidoAnterior.setPuntoimpresionId(pedido.getPuntoimpresionId());
		pedidoAnterior.setOrigendocumentoId(pedido.getOrigendocumentoId());
		pedidoAnterior.setVendedorId(pedido.getVendedorId());
		pedidoAnterior.setBodegaId(pedido.getBodegaId());
		pedidoAnterior.setListaprecioId(pedido.getListaprecioId());
		pedidoAnterior.setFechaPedido(pedido.getFechaPedido());
		pedidoAnterior.setFechaVencimiento(pedido.getFechaVencimiento());
		pedidoAnterior.setFechaCreacion(pedido.getFechaCreacion());
		pedidoAnterior.setUsuarioId(pedido.getUsuarioId());
		pedidoAnterior.setContacto(pedido.getContacto());
		pedidoAnterior.setDireccion(pedido.getDireccion());
		pedidoAnterior.setTelefono(pedido.getTelefono());
		pedidoAnterior.setTiporeferencia(pedido.getTiporeferencia());
		pedidoAnterior.setReferencia(pedido.getReferencia());
		pedidoAnterior.setDiasvalidez(pedido.getDiasvalidez());
		pedidoAnterior.setObservacion(pedido.getObservacion());
		pedidoAnterior.setEstado(pedido.getEstado());
		pedidoAnterior.setPedidoaplId(pedido.getPedidoaplId());
		pedidoAnterior.setPorcentajeComisionAgencia(pedido.getPorcentajeComisionAgencia());
		pedidoAnterior.setEquipoId(pedido.getEquipoId());
		pedidoAnterior.setClienteNegociacionId(pedido.getClienteNegociacionId());
		pedidoAnterior.setTipoNegociacion(pedido.getTipoNegociacion());
		pedidoAnterior.setPorcentajeNegociacionDirecta(pedido.getPorcentajeNegociacionDirecta());
		pedidoAnterior.setPorcentajeDescuentoNegociacion(pedido.getPorcentajeDescuentoNegociacion());
		pedidoAnterior.setAutorizacionSap(pedido.getAutorizacionSap());
		
		return pedidoAnterior;
	}
	
	private DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			PedidoDetalleIf pedidoDetalleIf = (PedidoDetalleIf) pedidoDetalleColeccion.get(row);
			if (activarVisorDetallePersonalizacion.equals("S") && pedidoDetalleIf.getCodigoPersonalizacion() != null)
				setBackground(Color.CYAN);
			else
				setBackground(null);
			if (column == 0)
				setHorizontalAlignment(SwingConstants.LEFT);
			else
				setHorizontalAlignment(SwingConstants.RIGHT);
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	};
	
	private Tarea tarea;

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
}
