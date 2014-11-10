package com.spirit.cartera.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.NotaCreditoData;
import com.spirit.cartera.entity.NotaCreditoDetalleData;
import com.spirit.cartera.entity.NotaCreditoDetalleIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.cartera.gui.criteria.NotaCreditoCriteria;
import com.spirit.cartera.gui.panel.JPNotaCredito;
import com.spirit.cartera.handler.TipoReferenciaNotaCredito;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.compras.gui.criteria.CompraCriteria;
import com.spirit.compras.gui.criteria.OrdenCompraCriteria;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.gui.data.AutorizarAsientoData;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.ServiceInstantiationException;
import com.spirit.exception.UnknownServiceException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.gui.criteria.FacturaCriteria;
import com.spirit.facturacion.handler.TipoReferenciaPedido;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoTroqueladoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioFacturacionIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoFacturacionIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.gui.criteria.OrdenMedioCriteria;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class NotaCreditoModel extends JPNotaCredito {
	
	private static final long serialVersionUID = -8459413652603920876L;
	
	private static final String PRODUCTO = "P";
	private static final String NOMBRE_ESTADO_INACTIVA = "INACTIVA";
	private static final String ESTADO_INACTIVA = NOMBRE_ESTADO_INACTIVA.substring(0, 1);
	private static final String NOMBRE_ESTADO_ACTIVA = "ACTIVA";
	private static final int MAX_LONGITUD_CODIGO = 10;
	private static final int MAX_LONGITUD_OBSERVACION = 100;
	private static final int MAX_LONGITUD_REFERENCIA = 20;
	//private static final int MAX_LONGITUD_PREIMPRESO = 15;
	//private static final int MAX_LONGITUD_AUTORIZACION = 37;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DecimalFormat formatoEntero = new DecimalFormat("###0");
	private double totalValor = 0.00;
	private double totalIVA = 0.00;
	private double totalICE = 0.00;
	private double totalOtrosImpuestos = 0.00;
	private double grandTotal = 0.00;
	
	private double subtotal12_T = 0.00;
	private double subtotal0_T = 0.00;
	private double descuento_T = 0.00;
	private double subtotal_T = 0.00;
	private double iva12_T = 0.00;
	private double valortotal_T = 0.00;	
	
	protected Double IVA = Parametros.getIVA() / 100;
	private JDPopupFinderModel popupFinder;
	private ProductoCriteria productoCriteria;
	ArrayList lista;
	private List<NotaCreditoDetalleIf> notaCreditoDetalleColeccion = new ArrayList<NotaCreditoDetalleIf>();
	private List<NotaCreditoDetalleIf> notaCreditoDetalleEliminadas = new ArrayList<NotaCreditoDetalleIf>();
	private NotaCreditoDetalleIf notaCreditoDetalleForUpdate;
	private ClienteOficinaIf operadorNegocio;
	private Long referenciaId = null;
	private ProductoIf productoIf;
	private GenericoIf genericoIf;
	private TipoDocumentoIf tipoDocumento;
	private DocumentoIf documento;
	private Map productosLocal = new HashMap();
	private NotaCreditoIf notaCredito;
	java.sql.Date fechaCreacionCartera;
	Long idProductoTemp = 0L;
	String codigoProductoTemp = "";
	String strTipoReferencia = "";
	private static final int MAX_LONGITUD_CANTIDAD = 8;
	private static final int MAX_LONGITUD_VALOR = 22;
	private static final int MAX_LONGITUD_OTRO_IMPUESTO = 3;
	private static final int MAX_LONGITUD_DESCRIPCION = 300;
	protected JMenuItem menuItem;
	protected JPopupMenu popup = new JPopupMenu();
	private static final String TIPO_CARTERA_CLIENTE = "C";
	private static final String TIPO_CARTERA_PROVEEDOR = "P";
	String tipoCartera = TIPO_CARTERA_CLIENTE;
	private String si = "Si"; 
	private  String no = "No"; 
	private  Object[] options ={si,no}; 
	protected FacturaIf facturaIf;
	protected CompraIf compraIf;
	protected OrdenMedioIf ordenMedioIf;
	protected OrdenCompraIf ordenCompraIf;
	protected String tipoPresupuesto = TipoReferenciaPedido.NINGUNO.getLetra();
	protected PresupuestoIf presupuestoIf = null;
	protected PlanMedioIf planMedioIf = null;
	protected Long ordenId = null;
	
	
	public NotaCreditoModel() {
		iniciarComponentes();
		initKeyListeners();
		setSorterTable(getTblNotaCreditoDetalle());
		addPopupMenu();
		initListeners();
		cargarCombos();
		setSorterTable(getTblNotaCreditoDetalle());
		getTblNotaCreditoDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.clean();
		this.showSaveMode();
		new HotKeyComponent(this);
	}
		
	private void iniciarComponentes(){
		getBtnBuscarOperadorNegocio().setToolTipText("Buscar operador de negocio");
		getBtnBuscarOperadorNegocio().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnReferencia().setToolTipText("Buscar Referencia");
		getBtnReferencia().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarProducto().setToolTipText("Buscar producto");
		getBtnBuscarProducto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnAgregarDetalle().setText("");
		getBtnAgregarDetalle().setToolTipText("Agregar detalle");
		getBtnAgregarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarDetalle().setText("");
		getBtnActualizarDetalle().setToolTipText("Actualizar detalle");
		getBtnActualizarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnEliminarDetalle().setText("");
		getBtnEliminarDetalle().setToolTipText("Eliminar detalle");
		getBtnEliminarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		
		getBtnEscojaReferencia().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnEscojaReferencia().setToolTipText("Busqueda de Referencia");
		getBtnEscojaOrden().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnEscojaOrden().setToolTipText("Busqueda de Orden");
		
		getBtnLimpiarEscojaReferencia().setToolTipText("Limpiar Referencia");
		getBtnLimpiarEscojaReferencia().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/cancelar.png"));
		getBtnLimpiarEscojaOrden().setToolTipText("Limpiar Referencia");
		getBtnLimpiarEscojaOrden().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/cancelar.png"));
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblNotaCreditoDetalle().getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
		getTblNotaCreditoDetalle().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		getTblNotaCreditoDetalle().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		getTblNotaCreditoDetalle().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		getTblNotaCreditoDetalle().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblNotaCreditoDetalle().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
	}
	
	private void initKeyListeners() {	
		getLblICEFinal().setVisible(false);
		getTxtICEFinal().setVisible(false);		
		
		//Calculo y seteo la maxima longitud del preimpreso segun lo fijado en Parametros Empresa
		int maxlongPreimpEstablecimiento = Parametros.getMaximaLongitudPreimpresoEstablecimiento();
		int maxlongPreimpPuntoEmision = Parametros.getMaximaLongitudPreimpresoPuntoEmision();
		int maxlongPreimpSecuencial = Parametros.getMaximaLongitudPreimpresoSecuencial();
		int guionesSeparadores = 2;
		int longitudPreimpreso = maxlongPreimpEstablecimiento + maxlongPreimpPuntoEmision + maxlongPreimpSecuencial + guionesSeparadores;
		
		getTxtPreimpreso().addKeyListener(new TextChecker(longitudPreimpreso));
		//getTxtPreimpreso().addKeyListener(new TextChecker(MAX_LONGITUD_PREIMPRESO));
				
		getTxtObservacion().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACION));
		getTxtReferencia().addKeyListener(new TextChecker(MAX_LONGITUD_REFERENCIA));
		getTxtAutorizacion().addKeyListener(new TextChecker(Parametros.getMaximaLongitudPreimpresoAutorizacion()));
		getTxtAutorizacion().addKeyListener(new NumberTextField());
		getTxtCantidad().addKeyListener(new TextChecker(MAX_LONGITUD_CANTIDAD));
		getTxtCantidad().addKeyListener(new NumberTextField());
		getTxtValor().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtValor().addKeyListener(new NumberTextFieldDecimal());
		getTxtOtroImpuesto().addKeyListener(new TextChecker(MAX_LONGITUD_OTRO_IMPUESTO));
		getTxtOtroImpuesto().addKeyListener(new NumberTextField());
		getTxtDescripcion().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION, true));
		getTxtObservacionDetalle().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION, true));
		getCmbFechaEmision().setLocale(Utilitarios.esLocale);
		getCmbFechaVencimiento().setLocale(Utilitarios.esLocale);
		getCmbFechaCaducidad().setLocale(Utilitarios.esLocale);
		getCmbFechaEmision().setEditable(false);
		getCmbFechaVencimiento().setEditable(false);
		getCmbFechaCaducidad().setEditable(false);
		getCmbFechaEmision().setShowNoneButton(false);
		getCmbFechaVencimiento().setShowNoneButton(false);
		getCmbFechaCaducidad().setShowNoneButton(false);
	}
	
	private void addPopupMenu() {
		// agregando items de elimar al popupmenu
		menuItem = new JMenuItem("<html><font color=red>Eliminar detalle</font></html>");
		menuItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				eliminarNotaCreditoDetalle();
			}
		});
		popup.add(menuItem);
		
		// agregando el popupmenu a label y su escuchador de ratón
		getTblNotaCreditoDetalle().add(popup);
		getTblNotaCreditoDetalle().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON3)
					if (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE)
						popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private void cargarCombos() {
		// Combos estáticos
		getCmbEstado().addItem(NOMBRE_ESTADO_ACTIVA);
		getCmbEstado().addItem(NOMBRE_ESTADO_INACTIVA);
		// DateCombos
		getCmbFechaEmision().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaVencimiento().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaCaducidad().setFormat(Utilitarios.setFechaUppercase());
		if (getMode() == SpiritMode.FIND_MODE) {
			getCmbFechaEmision().setSelectedItem(null);
			getCmbFechaVencimiento().setSelectedItem(null);
			getCmbFechaCaducidad().setSelectedItem(null);
		}
		
		cargarComboTipoDocumento();
		cargarComboMonedas();
		cargarComboTipoReferencia();
	}
	
	private void cargarComboTipoDocumento() {
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", Long.valueOf(Parametros.getIdEmpresa()));
			if (tipoCartera.equals(TIPO_CARTERA_PROVEEDOR))
				parameterMap.put("codigo", "NCP");
			else
				parameterMap.put("codigo", "NCC");
			List tiposDocumentos = (List)SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQuery(parameterMap); 
			refreshCombo(getCmbTipoDocumento(), tiposDocumentos);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboTipoReferencia() {
		DefaultComboBoxModel modelo = new DefaultComboBoxModel(TipoReferenciaNotaCredito.values());
		getCmbTipoReferencia().setModel(modelo);
	}
	
	ActionListener oActionListenerBtnEscojaOrden = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			try {
				
				//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
				//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'
				
				TipoReferenciaNotaCredito tr = (TipoReferenciaNotaCredito) getCmbTipoReferencia().getSelectedItem(); 
				
				if ( TipoReferenciaNotaCredito.FACTURA == tr ){
					if (TipoReferenciaPedido.PRESUPUESTO.getLetra().equals(tipoPresupuesto) && presupuestoIf != null)
						buscarOrdenCompra();				
					if (TipoReferenciaPedido.PLAN_MEDIOS.getLetra().equals(tipoPresupuesto) && planMedioIf != null)
						buscarOrdenMedio();
				}
				
				if ( TipoReferenciaNotaCredito.COMPRA == tr ){
					if (TipoReferenciaPedido.PRESUPUESTO.getLetra().equals(tipoPresupuesto) && presupuestoIf != null)
						buscarOrdenCompra();				
					if (TipoReferenciaPedido.PLAN_MEDIOS.getLetra().equals(tipoPresupuesto) && planMedioIf != null)
						buscarOrdenMedio();
				}
				
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
			}
		}
	};
	
	private void buscarOrdenCompra() throws GenericBusinessException {
		
		Long clienteOficinaId = 0L;
		
		/*if (operadorNegocio != null)
			clienteOficinaId = operadorNegocio.getId();*/
		
		String[] estados = {"P", "E", "G"}; //PENDIENTE, ENVIADA, INGRESADA
		
		String codigo = "%";
		if ("".equals(getTxtEscojaOrden().getText()) == false)
			codigo = getTxtEscojaOrden().getText() + "%";
		
		Map aMap = new HashMap();
		aMap.put("codigo", codigo);
				
		int tamanoLista = SessionServiceLocator.getOrdenCompraSessionService().findOrdenCompraByQueryByPresupuestoIdAndByEstadosSize(aMap, presupuestoIf.getId(), estados);
		
		if (tamanoLista > 0) {
			OrdenCompraCriteria ordenCompraCriteria = new OrdenCompraCriteria(Parametros.getIdEmpresa(), false);
			ordenCompraCriteria.setResultListSize(tamanoLista);
			ordenCompraCriteria.setQueryBuilded(aMap);
			ordenCompraCriteria.setEstados(estados);
			ordenCompraCriteria.setPresupuestoId(presupuestoIf.getId());
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.add(50);
			anchoColumnas.add(180);
			anchoColumnas.add(50);
			anchoColumnas.add(50);
			anchoColumnas.add(40);
			anchoColumnas.add(150);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), ordenCompraCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas,	null);
			if (popupFinder.getElementoSeleccionado() != null) {
				ordenCompraIf = (OrdenCompraIf) popupFinder.getElementoSeleccionado();
				ordenId = ordenCompraIf.getId();
				getTxtEscojaOrden().setBackground(Parametros.saveUpdateColor);
				getTxtEscojaOrden().setText(ordenCompraIf.getCodigo());
				getTxtEscojaOrden().setEditable(false);
				getBtnEscojaOrden().setEnabled(false);
			}
		} else {
			SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.INFORMATION);
		}
	}
	
	private void buscarOrdenMedio() throws GenericBusinessException {
		Long clienteOficinaId = 0L;
		
		/*if (operadorNegocio != null)
			clienteOficinaId = operadorNegocio.getId();*/
		
		String[] estados = {"E", "I"}; //ENVIADA, INGRESADA
		
		String codigo = "%";
		if ("".equals(getTxtEscojaOrden().getText()) == false)
			codigo = getTxtEscojaOrden().getText() + "%";
		
		Map mapa = new HashMap();
		mapa.put("codigo", codigo);
		mapa.put("planMedioId", planMedioIf.getId());
				
		int tamanoLista = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQueryAndByEstadosSize(mapa, estados);
		
		if (tamanoLista > 0) {
			OrdenMedioCriteria ordenMedioCriteria = new OrdenMedioCriteria(Parametros.getIdEmpresa());
			ordenMedioCriteria.setResultListSize(tamanoLista);
			ordenMedioCriteria.setQueryBuilded(mapa);
			ordenMedioCriteria.setEstados(estados);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), ordenMedioCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
			
			if (popupFinder.getElementoSeleccionado() != null) {
				ordenMedioIf = (OrdenMedioIf) popupFinder.getElementoSeleccionado();
				ordenId = ordenMedioIf.getId();
				getTxtEscojaOrden().setBackground(Parametros.saveUpdateColor);
				getTxtEscojaOrden().setText(ordenMedioIf.getCodigo());
				getTxtEscojaOrden().setEditable(false);
				getBtnEscojaOrden().setEnabled(false);
				//getBtnLimpiarEscojaOrden().setEnabled(true);
			}
		} else {
			SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.INFORMATION);
		}
	}
	
	ActionListener oActionListenerBtnEscojaReferencia = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			try {
				
				//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
				//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'
				
				TipoReferenciaNotaCredito tr = (TipoReferenciaNotaCredito) getCmbTipoReferencia().getSelectedItem(); 
				
				if ( TipoReferenciaNotaCredito.FACTURA == tr )
					buscarFactura();				
				if ( TipoReferenciaNotaCredito.COMPRA == tr )
					buscarCompra();
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
			}
		}
	};
	
	ActionListener oActionListenerBtnReferencia = new ActionListener() {
		public void actionPerformed(ActionEvent eventoInicio) {
			try {
				
				String tipoCartera = (String)getCmbTipoCartera().getSelectedItem(); 
				
				if(operadorNegocio != null){
					if (tipoCartera.equals("CLIENTE"))
						buscarReferenciaFactura();				
					else if (tipoCartera.equals("PROVEEDOR"))
						buscarReferenciaCompra();
				}else{
					SpiritAlert.createAlert("Primero debe seleccionar un Cliente/Proveedor", SpiritAlert.INFORMATION);
				}
				
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
			}
		}
	};
	
	private void buscarFactura() throws GenericBusinessException {
		
		Map aMap = new HashMap();
		
		if (operadorNegocio != null){
			aMap.put("clienteoficinaId", operadorNegocio.getId());
		}
		
		try {
			if ("".equals(getTxtEscojaReferencia().getText()) == false)
				aMap.put("preimpresoNumero", this.getTxtEscojaReferencia().getText());
		} catch (java.lang.NumberFormatException e) {
			SpiritAlert.createAlert("El Preimpreso de factura ingresado no es válido", SpiritAlert.ERROR);
		}
				
		int tamanoLista = SessionServiceLocator.getFacturaSessionService().getFacturaListSize(aMap, Parametros.getIdEmpresa());
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.addElement(110);
		anchoColumnas.addElement(70);
		anchoColumnas.addElement(220);
		anchoColumnas.addElement(220);
		anchoColumnas.addElement(90);
		Map alineacionColumnas = new HashMap();
		alineacionColumnas.put(0, SwingConstants.CENTER);
		alineacionColumnas.put(1, SwingConstants.CENTER);
		alineacionColumnas.put(4, SwingConstants.RIGHT);
		if (tamanoLista > 0) {
			FacturaCriteria facturaCriteria = new FacturaCriteria();
			facturaCriteria.setResultListSize(tamanoLista);
			facturaCriteria.setQueryBuilded(aMap);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), facturaCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, alineacionColumnas);
			if ( popupFinder.getElementoSeleccionado() != null ) {
				facturaIf = (FacturaIf) popupFinder.getElementoSeleccionado();
				getTxtEscojaReferencia().setBackground(Parametros.saveUpdateColor);
				getTxtEscojaReferencia().setText(facturaIf.getPreimpresoNumero());
				getTxtEscojaReferencia().setEditable(false);
				getBtnEscojaReferencia().setEnabled(false);
				habilitarEscojaOrden();
				
				//busco si salio de un presupuesto o de un plan de medio
				//y registor tipo de presupuesto y id del presupuesto/plan
				tipoPresupuesto = TipoReferenciaNotaCredito.NINGUNO.getLetra();
				presupuestoIf = null;
				planMedioIf = null;
				ordenId = null;
						
				//para presupuesto (busco por dos caminos)
				Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByFacturaId(facturaIf.getId());
				if(presupuestosFacturacion.size() > 0){
					PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturacion.iterator().next();
					tipoPresupuesto = TipoReferenciaPedido.PRESUPUESTO.getLetra();
					PresupuestoDetalleIf presupuestoDetalleIf = SessionServiceLocator.getPresupuestoDetalleSessionService().getPresupuestoDetalle(presupuestoFacturacionIf.getPresupuestoDetalleId());
					presupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalleIf.getPresupuestoId());
				}else{
					PedidoIf pedido = SessionServiceLocator.getPedidoSessionService().getPedido(facturaIf.getPedidoId());
					if(pedido.getTiporeferencia().equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())){
						tipoPresupuesto = TipoReferenciaPedido.PRESUPUESTO.getLetra();
						Collection presupuestos = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(pedido.getReferencia());
						if(presupuestos.size() > 0){
							presupuestoIf = (PresupuestoIf)presupuestos.iterator().next();
						}
					}
				}
				
				//para plan de medios (si no fue presupuesto)
				if(tipoPresupuesto.equals(TipoReferenciaNotaCredito.NINGUNO.getLetra())){
					Collection planMedioFacturacion = SessionServiceLocator.getPlanMedioFacturacionSessionService().findPlanMedioFacturacionByPedidoId(facturaIf.getPedidoId());
					if(planMedioFacturacion.size() > 0){
						PlanMedioFacturacionIf planMedioFacturacionIf = (PlanMedioFacturacionIf)planMedioFacturacion.iterator().next();
						tipoPresupuesto = TipoReferenciaPedido.PLAN_MEDIOS.getLetra();
						planMedioIf = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(planMedioFacturacionIf.getPlanMedioId());
					}else{
						PedidoIf pedido = SessionServiceLocator.getPedidoSessionService().getPedido(facturaIf.getPedidoId());
						if(pedido.getTiporeferencia().equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra())){
							tipoPresupuesto = TipoReferenciaPedido.PLAN_MEDIOS.getLetra();
							Collection planesMedio = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByCodigoAndByEstados(pedido.getReferencia(), "N","P","A","D","F");
							if(planesMedio.size() > 0){
								planMedioIf = (PlanMedioIf)planesMedio.iterator().next();
							}
						}
					}
				}
				
			}				
		} else {
			SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
		}
	}
	
	private void buscarReferenciaFactura() throws GenericBusinessException {
		
		Map aMap = new HashMap();
		
		if (operadorNegocio != null){
			aMap.put("clienteoficinaId", operadorNegocio.getId());
		}
		
		int tamanoLista = SessionServiceLocator.getFacturaSessionService().getFacturaListSize(aMap, Parametros.getIdEmpresa());
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.addElement(110);
		anchoColumnas.addElement(70);
		anchoColumnas.addElement(220);
		anchoColumnas.addElement(220);
		anchoColumnas.addElement(90);
		Map alineacionColumnas = new HashMap();
		alineacionColumnas.put(0, SwingConstants.CENTER);
		alineacionColumnas.put(1, SwingConstants.CENTER);
		alineacionColumnas.put(4, SwingConstants.RIGHT);
		if (tamanoLista > 0) {
			FacturaCriteria facturaCriteria = new FacturaCriteria();
			facturaCriteria.setResultListSize(tamanoLista);
			facturaCriteria.setQueryBuilded(aMap);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), facturaCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, alineacionColumnas);
			if ( popupFinder.getElementoSeleccionado() != null ) {
				facturaIf = (FacturaIf) popupFinder.getElementoSeleccionado();
				referenciaId = facturaIf.getId();
				getTxtReferencia().setText(facturaIf.getPreimpresoNumero());		
			}				
		} else {
			SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
		}
	}
	
	/*private void buscarPresupuesto() throws GenericBusinessException {
		
		Long clienteId = 0L, clienteOficinaId = 0L;
		
		if (operadorNegocio != null)
			clienteOficinaId = operadorNegocio.getId();
		
		String[] estados = {"T", "P", "A", "F"};
		
		String codigo = "%";
		if ("".equals(getTxtEscojaReferencia().getText()) == false)
			codigo = getTxtEscojaReferencia().getText() + "%";
		
		int tamanoLista = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigoByClienteByEmpresaAndByEstadosSize(codigo, clienteId, clienteOficinaId, Parametros.getIdEmpresa(), estados);
		
		if (tamanoLista > 0) {
			PresupuestoCriteria presupuestoCriteria = new PresupuestoCriteria(clienteId, clienteOficinaId);
			presupuestoCriteria.setResultListSize(tamanoLista);
			presupuestoCriteria.setCodigo(codigo);
			presupuestoCriteria.setEstados(estados);
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.add(70);
			anchoColumnas.add(70);	
			anchoColumnas.add(270);
			anchoColumnas.add(400);
			JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), presupuestoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, null);
			if ( popupFinder.getElementoSeleccionado() != null ) {
				presupuestoIf = (PresupuestoIf) popupFinder.getElementoSeleccionado();
				getTxtEscojaReferencia().setBackground(Parametros.saveUpdateColor);
				getTxtEscojaReferencia().setText(presupuestoIf.getCodigo());
				getTxtEscojaReferencia().setEditable(false);
				getBtnEscojaReferencia().setEnabled(false);
			}
		} else {
			SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.INFORMATION);
		}
	}*/
	
	private void buscarCompra() throws GenericBusinessException {
		try {
			Map mapa = new HashMap();
			
			if (operadorNegocio != null)
				mapa.put("proveedorId", operadorNegocio.getId());
			
			if ( getTxtEscojaReferencia().getText()!=null && !"".equals(getTxtEscojaReferencia().getText()))
				mapa.put("preimpreso", getTxtEscojaReferencia().getText().trim() );
						
			int tamanoLista = SessionServiceLocator.getCompraSessionService().findCompraByQueryListSize(mapa, Parametros.getIdEmpresa());
			
			if (tamanoLista > 0) {
				CompraCriteria compraCriteria = new CompraCriteria(true);
				compraCriteria.setResultListSize(tamanoLista);
				compraCriteria.setQueryBuilded(mapa);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), compraCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if (popupFinder.getElementoSeleccionado() != null) {
					compraIf = (CompraIf) popupFinder.getElementoSeleccionado();
					getTxtEscojaReferencia().setBackground(Parametros.saveUpdateColor);
					getTxtEscojaReferencia().setText(compraIf.getPreimpreso());
					getTxtEscojaReferencia().setEditable(false);
					getBtnEscojaReferencia().setEnabled(false);
					habilitarEscojaOrden();
					
					tipoPresupuesto = TipoReferenciaNotaCredito.NINGUNO.getLetra();
					presupuestoIf = null;
					planMedioIf = null;
					ordenId = null;
					//busco si la compra salio de una orden de compra o de medio
					Collection ordenesAsociadas = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(compraIf.getId());
					if(ordenesAsociadas.size() > 0){
						OrdenAsociadaIf ordenAsociada = (OrdenAsociadaIf)ordenesAsociadas.iterator().next();
						if(ordenAsociada.getTipoOrden().equals("OC")){
							tipoPresupuesto = TipoReferenciaPedido.PRESUPUESTO.getLetra();
							ordenId = ordenAsociada.getOrdenId();
							OrdenCompraIf ordenCompraIf = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(ordenId);
							SolicitudCompraIf solicitudCompraIf = SessionServiceLocator.getSolicitudCompraSessionService().getSolicitudCompra(ordenCompraIf.getSolicitudcompraId());
							if(solicitudCompraIf.getTipoReferencia().equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())){
								Collection presupuestos = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(solicitudCompraIf.getReferencia());
								if(presupuestos.size() > 0){
									presupuestoIf = (PresupuestoIf)presupuestos.iterator().next();
								}
							}
						}else if(ordenAsociada.getTipoOrden().equals("OM")){
							tipoPresupuesto = TipoReferenciaPedido.PLAN_MEDIOS.getLetra();
							ordenId = ordenAsociada.getOrdenId();
							OrdenMedioIf ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenId);
							planMedioIf = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(ordenMedioIf.getPlanMedioId());
						}						
					}
				}					
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
	
	private void buscarReferenciaCompra() throws GenericBusinessException {
		try {
			Map mapa = new HashMap();
			
			if (operadorNegocio != null)
				mapa.put("proveedorId", operadorNegocio.getId());
			
			int tamanoLista = SessionServiceLocator.getCompraSessionService().findCompraByQueryListSize(mapa, Parametros.getIdEmpresa());
			
			if (tamanoLista > 0) {
				CompraCriteria compraCriteria = new CompraCriteria(true);
				compraCriteria.setResultListSize(tamanoLista);
				compraCriteria.setQueryBuilded(mapa);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), compraCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if (popupFinder.getElementoSeleccionado() != null) {
					compraIf = (CompraIf) popupFinder.getElementoSeleccionado();
					referenciaId = compraIf.getId();
					getTxtReferencia().setText(compraIf.getPreimpreso());
				}					
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
	
	/*private void buscarPlanMedios() throws GenericBusinessException {
		Long clienteOficinaId = 0L;
		
		if (operadorNegocio != null)
			clienteOficinaId = operadorNegocio.getId();
		
		String codigo = "%";
		if ("".equals(getTxtEscojaReferencia().getText()) == false)
			codigo = getTxtEscojaReferencia().getText() + "%";
		
		Map mapa = new HashMap();
		mapa.put("codigo", codigo);
				
		int tamanoLista = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByQueryAndByIdClienteSize(mapa, clienteOficinaId, Parametros.getIdEmpresa(), "N","P","A","D","F");
		
		if (tamanoLista > 0) {
			PlanMedioCriteria planMedioCriteria = new PlanMedioCriteria(clienteOficinaId, "N","P","A","D","F"); //TODOS MENOS HISTORICO
			planMedioCriteria.setResultListSize(tamanoLista);
			planMedioCriteria.setQueryBuilded(mapa);
			
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.add(70);
			anchoColumnas.add(70);	
			anchoColumnas.add(270);
			anchoColumnas.add(400);
			
			// Lista de los planes de medio x version

			JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),planMedioCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
			
			if (popupFinder.getElementoSeleccionado() != null) {
				planMedioIf = (PlanMedioIf) popupFinder.getElementoSeleccionado();
				getTxtEscojaReferencia().setBackground(Parametros.saveUpdateColor);
				getTxtEscojaReferencia().setText(planMedioIf.getCodigo());
				getTxtEscojaReferencia().setEditable(false);
				getBtnEscojaReferencia().setEnabled(false);
			}
		} else {
			SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.INFORMATION);
		}
	}*/
			
	private void cargarComboMonedas() {
		try {
			List monedas = (List)SessionServiceLocator.getMonedaSessionService().getMonedaList();
			refreshCombo(getCmbMoneda(),monedas);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void initListeners() {
		getBtnBuscarOperadorNegocio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarOperadorNegocio();
			}
		});
		
		getBtnBuscarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarProducto();
			}
		});
		
		getBtnReferencia().addActionListener(oActionListenerBtnReferencia);
		getBtnEscojaReferencia().addActionListener(oActionListenerBtnEscojaReferencia);
		getBtnEscojaOrden().addActionListener(oActionListenerBtnEscojaOrden);
		
		getBtnLimpiarEscojaReferencia().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Seguro desea limpiar la referencia?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					getTxtEscojaReferencia().setText("");
					getTxtEscojaReferencia().setBackground(Parametros.findColor);
					getTxtEscojaReferencia().setEditable(true);
					getBtnEscojaReferencia().setEnabled(true);
					
					getTxtEscojaOrden().setText("");
					getTxtEscojaOrden().setBackground(Parametros.findColor);
					getTxtEscojaOrden().setEditable(true);
					getTxtEscojaOrden().setEnabled(true);
					
					facturaIf = null;
					compraIf = null;
					tipoPresupuesto = TipoReferenciaNotaCredito.NINGUNO.getLetra();
					presupuestoIf = null;
					planMedioIf = null;
					ordenId = null;
					
					getTxtEscojaReferencia().grabFocus();
				}
			}
		});
		
		getBtnLimpiarEscojaOrden().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Seguro desea limpiar la orden?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					getTxtEscojaOrden().setText("");
					getTxtEscojaOrden().setBackground(Parametros.findColor);
					getTxtEscojaOrden().setEditable(true);
					getTxtEscojaOrden().setEnabled(true);
					getBtnEscojaOrden().setEnabled(true);
					
					ordenId = null;
					
					getTxtEscojaReferencia().grabFocus();
				}
			}
		});
		
		getCmbTipoReferencia().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCmbTipoReferencia().getSelectedItem().equals(TipoReferenciaNotaCredito.FACTURA)){
					if(compraIf != null){
						compraIf = null;
						ordenId = null;
					}
				}
				else if(getCmbTipoReferencia().getSelectedItem().equals(TipoReferenciaNotaCredito.COMPRA)){
					if(facturaIf != null){
						facturaIf = null;
						ordenId = null;
					}
				}
				else if(getCmbTipoReferencia().getSelectedItem().equals(TipoReferenciaNotaCredito.NINGUNO)){
					compraIf = null;
					facturaIf = null;
					ordenId = null;
					presupuestoIf = null;
					planMedioIf = null;
				}
				
				getTxtEscojaReferencia().setText("");
				getTxtEscojaReferencia().setBackground(Parametros.findColor);
				getTxtEscojaReferencia().setEditable(true);
				getBtnEscojaReferencia().setEnabled(true);
				
				getTxtEscojaOrden().setText("");
				getTxtEscojaOrden().setBackground(Parametros.findColor);
				getTxtEscojaOrden().setEditable(true);
				getBtnEscojaOrden().setEnabled(true);
			}
		});
		
		/*getBtnLimpiarEscojaOrden().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Seguro desea limpiar la orden?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					getTxtEscojaOrden().setText("");
					getTxtEscojaOrden().setBackground(Parametros.findColor);
					getTxtEscojaOrden().setEditable(true);
					getBtnEscojaOrden().setEnabled(true);
					ordenCompraIf = null;
					ordenMedioIf = null;
					getTxtEscojaOrden().grabFocus();
				}
			}
		});*/
		
		getBtnAgregarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarNotaCreditoDetalle();
			}
		});
		
		getBtnActualizarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarNotaCreditoDetalle();
			}
		});
		
		getBtnEliminarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarNotaCreditoDetalle();
			}
		});
		
		getTblNotaCreditoDetalle().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				selectRow();
			}
		});
		
		getTblNotaCreditoDetalle().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				selectRow();
			}
		});
				
		getBtnVerificarPreimpreso().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				verificarPreimpreso();
			}			
		});
		
		getCmbTipoDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoDocumento().getSelectedItem() != null) {
					tipoDocumento = (TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem();
					
					if (tipoDocumento != null) {
						try {
							getCmbDocumento().removeAllItems();
							SpiritComboBoxModel cmbModelDocumento = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipodocumentoIdAndUsuarioId(tipoDocumento.getId(), ((UsuarioIf) Parametros.getUsuarioIf()).getId()));
							getCmbDocumento().setModel(cmbModelDocumento);
							getCmbDocumento().validate();
							getCmbDocumento().repaint();
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					
					refresh();
				}
			}
		});
		
		getCmbDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbDocumento().getSelectedItem() != null) {
					documento = (DocumentoIf) getCmbDocumento().getSelectedItem();
				}
			}
		});
		
		getCmbTipoCartera().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipoCartera = getCmbTipoCartera().getSelectedItem().toString().substring(0,1);
				operadorNegocio = null;
				getTxtOperadorNegocio().setText("");
				limpiarReferencia();
				
				referenciaId = null;
				getTxtReferencia().setText("");
				
				if (tipoCartera.equals(TIPO_CARTERA_PROVEEDOR)){
					cleanTableDetalle();
					getCmbTipoReferencia().setSelectedItem(TipoReferenciaNotaCredito.COMPRA);
				}else{
					getCmbTipoReferencia().setSelectedItem(TipoReferenciaNotaCredito.FACTURA);
				}
				
				getCmbTipoReferencia().setEnabled(false);
				cargarComboTipoDocumento();
			}
		});
	}
	
	private void verificarPreimpreso() {
		String preimpreso = getTxtPreimpreso().getText();
		String autorizacion = getTxtAutorizacion().getText();
		if ( preimpreso!= null && !preimpreso.equals("") ){
			if ( operadorNegocio == null ){
				String tipoOperador = "proveedor";
				if (tipoCartera.equals(TIPO_CARTERA_CLIENTE))
					tipoOperador = "cliente";
				
				SpiritAlert.createAlert("Debe elegir un " + tipoOperador + " primero !!",SpiritAlert.INFORMATION);
				return;
			}
			if ( autorizacion == null || autorizacion.equals("") ){
				SpiritAlert.createAlert("Debe ingresar la autorización primero !!",SpiritAlert.INFORMATION);
				return;
			}
			if ( validatePreimpresoValido() ){
				try {
					boolean existePreimpreso = SessionServiceLocator.getNotaCreditoSessionService().verificarPreimpreso(operadorNegocio.getId(),preimpreso,autorizacion, tipoCartera);
					if ( existePreimpreso ){
						Collection<NotaCreditoIf> notasCredito = SessionServiceLocator.getNotaCreditoSessionService().findNotaCreditoByPreimpreso(preimpreso);
						NotaCreditoIf notaCredito = notasCredito.iterator().next();
						SpiritAlert.createAlert("Preimpreso ya existe en nota de crédito con código "+notaCredito.getCodigo()+" !!",SpiritAlert.WARNING);
					} else 
						SpiritAlert.createAlert("El Preimpreso es válido !!",SpiritAlert.INFORMATION);
				} catch (GenericBusinessException e1) {
					e1.printStackTrace();
					SpiritAlert.createAlert(e1.getMessage(),SpiritAlert.ERROR);
				}
			}
		}
	}
	
	KeyListener oKeyListenerTxtCodigo = new TextChecker(MAX_LONGITUD_CODIGO);
		
	private void selectRow() {
		if (getTblNotaCreditoDetalle().getSelectedRow() != -1) {
			int filaSeleccionada = getTblNotaCreditoDetalle().convertRowIndexToModel(getTblNotaCreditoDetalle().getSelectedRow() );
			notaCreditoDetalleForUpdate = (NotaCreditoDetalleIf) notaCreditoDetalleColeccion.get(filaSeleccionada);
			enableNotaCreditoDetalleForUpdate(notaCreditoDetalleForUpdate);
		}
	}
				
	public void getSelectedObject(Object notaCreditoSeleccionada) {
		notaCredito = (NotaCreditoIf) notaCreditoSeleccionada;
		this.showUpdateMode();
		cleanTableDetalle();
		
		try {
			operadorNegocio = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(notaCredito.getOperadorNegocioId());
			ClienteIf operador = SessionServiceLocator.getClienteSessionService().getCliente(operadorNegocio.getClienteId());
				
			getTxtCodigo().setText(notaCredito.getCodigo());
			Calendar calendarFecha = new GregorianCalendar();
			calendarFecha.setTime(notaCredito.getFechaEmision());
			getCmbFechaEmision().setCalendar(calendarFecha);
			getCmbFechaEmision().repaint();
			Calendar calendarFechaVencimiento = new GregorianCalendar();
			calendarFechaVencimiento.setTime(notaCredito.getFechaVencimiento());
			getCmbFechaVencimiento().setCalendar(calendarFechaVencimiento);
			getCmbFechaVencimiento().repaint();
			Calendar calendarFechaCaducidad = new GregorianCalendar();
			calendarFechaCaducidad.setTime(notaCredito.getFechaCaducidad());
			getCmbFechaCaducidad().setCalendar(calendarFechaCaducidad);
			getCmbFechaCaducidad().repaint();
			getTxtOperadorNegocio().setText(operador.getIdentificacion() + " - " + operadorNegocio.getDescripcion()); 
			getTxtPreimpreso().setText(notaCredito.getPreimpreso());
			getTxtAutorizacion().setText(notaCredito.getAutorizacion());
			getCmbMoneda().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbMoneda(), notaCredito.getMonedaId()));
			
			if (ESTADO_INACTIVA.equals(notaCredito.getEstado()))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVA);
			else
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVA);
						
			getTxtObservacion().setText(notaCredito.getObservacion());
			
			referenciaId = notaCredito.getReferenciaId();
			getTxtReferencia().setText(notaCredito.getReferencia());			
			
			DefaultTableModel tableModel = (DefaultTableModel) getTblNotaCreditoDetalle().getModel();
			notaCreditoDetalleColeccion = (List) SessionServiceLocator.getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByNotaCreditoId(notaCredito.getId());
			Iterator it = notaCreditoDetalleColeccion.iterator();
			
			while (it.hasNext()) {
				NotaCreditoDetalleIf notaCreditoDetalle = (NotaCreditoDetalleIf) it.next();
				ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(notaCreditoDetalle.getProductoId());
				productoIf = producto;
				Vector<String> fila = new Vector<String>();
				double cantidad = Double.parseDouble(notaCreditoDetalle.getCantidad().toString());
				double valor = Double.parseDouble(notaCreditoDetalle.getValor().toString());
				double subtotal = cantidad * valor;
				double otroImpuestoSubtotal = Double.parseDouble(notaCreditoDetalle.getOtroImpuesto().toString());
				double iva = Double.parseDouble(notaCreditoDetalle.getIva().toString());
				double ice = Double.parseDouble(notaCreditoDetalle.getIce().toString());
				subtotal = subtotal + iva + ice + otroImpuestoSubtotal;
				
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(producto.getGenericoId());
				PresentacionIf presentacion = null;
				
				if (producto.getPresentacionId() != null)
					presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(producto.getPresentacionId());
				
				String productoNombre = producto.getCodigo() + " - " + generico.getNombre();
				
				if (presentacion != null)
					productoNombre += " - " + presentacion.getNombre();
				
				fila.add(productoNombre);
				fila.add(String.valueOf(Double.valueOf(cantidad).intValue()));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(valor)));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(iva)));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(ice)));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(otroImpuestoSubtotal)));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(subtotal)));
				tableModel.addRow(fila);
			}
			
			actualizarTotales();
			
			habilitarEscojaReferencia();
			habilitarEscojaOrden();
			
		} catch (UnknownServiceException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		} catch (ServiceInstantiationException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void buscarOperadorNegocio() {
		Long idCorporacion = 0L;
		Long idCliente = 0L;
		String tipoCliente = "PR";
		String tituloVentanaBusqueda = "Proveedores";
		if (tipoCartera.equals(TIPO_CARTERA_CLIENTE)) {
			tipoCliente = "CL";
			tituloVentanaBusqueda = "Clientes";
		}
		ClienteOficinaCriteria clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente,"", false);
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.addElement(70);
		anchoColumnas.addElement(200);
		anchoColumnas.addElement(80);
		anchoColumnas.addElement(230);
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
		if (popupFinder.getElementoSeleccionado() != null)
			setOperadorNegocio();
	}
	
	private void buscarProducto() {
		String mmpg = "";
		if (tipoCartera.equals(TIPO_CARTERA_CLIENTE)) {
			productoCriteria = new ProductoCriteria("Producto", 0L, "", "R", "A", true, mmpg);
		} else
			productoCriteria = new ProductoCriteria("Producto", operadorNegocio.getId(), "A", "P", "A", false, mmpg);
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), productoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if (popupFinder.getElementoSeleccionado() != null) {
			productoIf = (ProductoIf) popupFinder.getElementoSeleccionado();
			getTxtProducto().setText(productoIf.getCodigo());
			
			try {
				genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
				if ("S".equals(genericoIf.getUsaLote())) {
					PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
					getTxtProducto().setText(productoIf.getCodigo() + " - " + genericoIf.getNombre()+ " - " + presentacion.getNombre());
				} else
					getTxtProducto().setText(productoIf.getCodigo() + " - " + genericoIf.getNombre());
				
				getTxtCantidad().setText("0");
				getTxtCantidad().setEnabled(true);
				getTxtCantidad().setEditable(true);
				getTxtValor().setText("0");
				getTxtValor().setEnabled(true);
				getTxtValor().setEditable(true);
				getTxtOtroImpuesto().setText("0");
				getTxtOtroImpuesto().setEnabled(true);
				getTxtOtroImpuesto().setEditable(true);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	
	}
	
	private void setOperadorNegocio() {
		operadorNegocio = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
		try {
			ClienteIf operador = SessionServiceLocator.getClienteSessionService().getCliente(operadorNegocio.getClienteId());
			if(operador != null){
				getTxtOperadorNegocio().setText(operador.getIdentificacion() + " - " + operadorNegocio.getDescripcion()); // " - " + proveedor.getNombreLegal() + 
				getBtnBuscarProducto().setEnabled(true);
			} else
				SpiritAlert.createAlert("No existe el Proveedor", SpiritAlert.ERROR);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error en consulta de Proveedor", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		if (operadorNegocio != null) {
			referenciaId = null;
			getTxtReferencia().setText("");
			getTxtProducto().setText("");
			getBtnBuscarProducto().setEnabled(true);
			cleanTableDetalle();
			
			limpiarReferencia();
			habilitarEscojaReferencia();
		}
	}

	private void habilitarEscojaReferencia() {
		getCmbTipoReferencia().setEnabled(true);
		getTxtEscojaReferencia().setEnabled(true);
		getTxtEscojaReferencia().setEditable(true);
		getTxtEscojaReferencia().setBackground(Parametros.findColor);
		getBtnEscojaReferencia().setEnabled(true);
		getBtnLimpiarEscojaReferencia().setEnabled(true);
		getTxtEscojaReferencia().grabFocus();
	}
	
	private void habilitarEscojaOrden() {
		getTxtEscojaOrden().setEnabled(true);
		getTxtEscojaOrden().setEditable(true);
		getTxtEscojaOrden().setBackground(Parametros.findColor);
		getTxtEscojaOrden().setEnabled(true);
		getBtnEscojaOrden().setEnabled(true);
		getBtnLimpiarEscojaOrden().setEnabled(true);
		getTxtEscojaOrden().grabFocus();
	}
	
	/*private void habilitarEscojaOrden() {
		getCmbTipoReferencia().setEnabled(true);
		getTxtEscojaOrden().setEnabled(true);
		getTxtEscojaOrden().setEditable(true);
		getTxtEscojaOrden().setBackground(Parametros.findColor);
		getBtnEscojaOrden().setEnabled(true);
		getBtnLimpiarEscojaOrden().setEnabled(true);
		getTxtEscojaOrden().grabFocus();
	}*/
			
	private void agregarNotaCreditoDetalle() {
		try {
			Vector<String> fila = new Vector<String>();
			String strCantidad = getTxtCantidad().getText().trim();
			String strValor = Utilitarios.removeDecimalFormat(getTxtValor().getText()).trim();
			String strOtroImpuesto = Utilitarios.removeDecimalFormat(getTxtOtroImpuesto().getText()).trim();
			
			if ("".equals(strOtroImpuesto))
				strOtroImpuesto = "0";
			
			if (getMode() == SpiritMode.SAVE_MODE && validarNotaCreditoDetalle()) {
				double cantidad = Double.parseDouble(strCantidad);
				double valor = Double.parseDouble(strValor);
				double otroImpuesto = Double.parseDouble(strOtroImpuesto) / 100D;
				double subtotal = cantidad * valor;
				double otroImpuestoSubtotal = subtotal * otroImpuesto;
				double iva = (documento.getCodigo().equals("NCFE") || documento.getCodigo().equals("NCFR"))?0D:calcularIVA(subtotal);
				double ice = calcularICE(subtotal);
				subtotal = subtotal + iva + ice + otroImpuestoSubtotal;
								
				NotaCreditoDetalleData data = new NotaCreditoDetalleData();
				data.setDocumentoId(documento.getId());
				data.setProductoId(productoIf.getId());
				data.setCantidad(BigDecimal.valueOf(cantidad));
				data.setValor(BigDecimal.valueOf(Double.valueOf(valor)));
				data.setIva(BigDecimal.valueOf(Double.valueOf(iva)));
				data.setIce(BigDecimal.valueOf(Double.valueOf(ice)));
				data.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf(otroImpuestoSubtotal)));
										
				if(getTxtDescripcion().getText() != null)
					data.setDescripcion(getTxtDescripcion().getText());
				
				TipoReferenciaNotaCredito tr = (TipoReferenciaNotaCredito) getCmbTipoReferencia().getSelectedItem(); 
				data.setTipoReferencia(tr.getLetra());
				
				if(tr == TipoReferenciaNotaCredito.FACTURA){
					data.setReferenciaId(facturaIf.getId());				
					data.setTipoPresupuesto(tipoPresupuesto);
					data.setOrdenId(ordenId);
					
					if(presupuestoIf != null)
						data.setPresupuestoId(presupuestoIf.getId());
					else if(planMedioIf != null)
						data.setPresupuestoId(planMedioIf.getId());
				}
				
				else if(tr == TipoReferenciaNotaCredito.COMPRA){
					data.setReferenciaId(compraIf.getId());
					data.setTipoPresupuesto(tipoPresupuesto);
					data.setOrdenId(ordenId);
					
					if(presupuestoIf != null)
						data.setPresupuestoId(presupuestoIf.getId());
					else if(planMedioIf != null)
						data.setPresupuestoId(planMedioIf.getId());
				}
				
				else if(tr == TipoReferenciaNotaCredito.NINGUNO){
					facturaIf = null;
					compraIf = null;
					tipoPresupuesto = TipoReferenciaNotaCredito.NINGUNO.getLetra();
					data.setTipoPresupuesto(tipoPresupuesto);
					presupuestoIf = null;
					planMedioIf = null;
					ordenId = null;					
				}
				
				String tipoNota = (String)getCmbTipoNotaDetalle().getSelectedItem();
				data.setTipoNota(tipoNota.substring(0,1));
				data.setObservacion(getTxtObservacionDetalle().getText());				
				
				// Si es así ingreso el detalle de la compra en la tabla
				notaCreditoDetalleColeccion.add(data);
				
				// Agrega información a la tabla visual para el usuario.
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
				PresentacionIf presentacion = null;
				
				if (productoIf.getPresentacionId() != null)
					presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
				
				String producto = productoIf.getCodigo() + " - " + generico.getNombre();
				
				if (presentacion != null)
					producto += " - " + presentacion.getNombre();
				
				fila.add(producto);
				fila.add(getTxtCantidad().getText());
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(valor)));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(iva)));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(ice)));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(otroImpuestoSubtotal)));
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(subtotal)));
				productosLocal.put(productoIf.getId(), PRODUCTO);
				
				DefaultTableModel tableModel = (DefaultTableModel) getTblNotaCreditoDetalle().getModel();
				tableModel.addRow(fila);
				cleanNotaCreditoDetalle();
			}
			else if (getMode() == SpiritMode.UPDATE_MODE){
				SpiritAlert.createAlert("No es posible agregar detalles en la actualización.\nSolo se puede actualizar la referencia y tipo de nota de cada detalle.", SpiritAlert.WARNING);
			}
			
			actualizarTotales();
			
		} catch (Exception e) {
			SpiritAlert.createAlert("Ocurrió un error al agregar el detalle de la Nota de Crédito !!!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private double calcularIVA(double subtotal) {
		double iva = 0.0;
		
		if ("S".equals(genericoIf.getCobraIva()))
			iva = subtotal * IVA;
		else
			iva = 0.0;
		
		return iva;
	}
	
	private double calcularICE(double subtotal) {
		double ice = 0.0;
		
		if ("S".equals(genericoIf.getCobraIce()))
			ice = (subtotal) * (genericoIf.getIce().doubleValue() / 100D);
		else
			ice = 0.0;
		
		return ice;
	}
	
	private void actualizarNotaCreditoDetalle() {
		DefaultTableModel tableModel = (DefaultTableModel) getTblNotaCreditoDetalle().getModel();
		try {
			if (getTblNotaCreditoDetalle().getSelectedRow() == -1) {
				SpiritAlert.createAlert("Por favor, elija la información que desea actualizar.", SpiritAlert.WARNING);
			} else {
				
				if (getMode() == SpiritMode.SAVE_MODE && validarNotaCreditoDetalle()) {
					
					// Copio la referencia que se debe actualizar
					NotaCreditoDetalleIf data = notaCreditoDetalleForUpdate;
					String strCantidad = getTxtCantidad().getText();
					String strValor = Utilitarios.removeDecimalFormat(getTxtValor().getText());
					String strOtroImpuesto = Utilitarios.removeDecimalFormat(getTxtOtroImpuesto().getText());
					double cantidad = Double.parseDouble(strCantidad);
					double valor = Double.parseDouble(strValor);
					double otroImpuesto = Double.parseDouble(strOtroImpuesto) / 100D;
					double subtotal = cantidad * valor;
					double otroImpuestoSubtotal = subtotal * otroImpuesto;
					double iva = (documento.getCodigo().equals("NCFE") || documento.getCodigo().equals("NCFR"))?0D:calcularIVA(subtotal);
					double ice = calcularICE(subtotal);
					
					subtotal = subtotal + iva + ice + otroImpuestoSubtotal;
					data.setDocumentoId(documento.getId());
					data.setProductoId(productoIf.getId());
					data.setCantidad(BigDecimal.valueOf(cantidad));
					data.setValor(BigDecimal.valueOf(Double.valueOf(valor)));
					data.setIva(BigDecimal.valueOf(Double.valueOf(iva)));
					data.setIce(BigDecimal.valueOf(Double.valueOf(ice)));
					data.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf(otroImpuestoSubtotal)));
					
					if(getTxtDescripcion().getText() != null)
						data.setDescripcion(getTxtDescripcion().getText());
					
					TipoReferenciaNotaCredito tr = (TipoReferenciaNotaCredito) getCmbTipoReferencia().getSelectedItem(); 
					data.setTipoReferencia(tr.getLetra());
					
					if(tr == TipoReferenciaNotaCredito.FACTURA){
						data.setReferenciaId(facturaIf.getId());				
						data.setTipoPresupuesto(tipoPresupuesto);
						data.setOrdenId(ordenId);
						if(presupuestoIf != null)
							data.setPresupuestoId(presupuestoIf.getId());
						else if(planMedioIf != null)
							data.setPresupuestoId(planMedioIf.getId());				
					}
					else if(tr == TipoReferenciaNotaCredito.COMPRA){
						data.setReferenciaId(compraIf.getId());
						data.setTipoPresupuesto(tipoPresupuesto);
						data.setOrdenId(ordenId);
						if(presupuestoIf != null)
							data.setPresupuestoId(presupuestoIf.getId());
						else if(planMedioIf != null)
							data.setPresupuestoId(planMedioIf.getId());
					}
					else if(tr == TipoReferenciaNotaCredito.NINGUNO){
						facturaIf = null;
						compraIf = null;
						tipoPresupuesto = TipoReferenciaNotaCredito.NINGUNO.getLetra();
						data.setTipoPresupuesto(tipoPresupuesto);
						presupuestoIf = null;
						planMedioIf = null;
						ordenId = null;					
					}
					
					String tipoNota = (String)getCmbTipoNotaDetalle().getSelectedItem();
					data.setTipoNota(tipoNota.substring(0,1));
					data.setObservacion(getTxtObservacionDetalle().getText());
					
					int filaSeleccionada = getTblNotaCreditoDetalle().convertRowIndexToModel(getTblNotaCreditoDetalle().getSelectedRow() );
					
					notaCreditoDetalleColeccion.set(filaSeleccionada, data);
					
					// actualizo informacion a la tabla visual para el usuario.
					GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
					PresentacionIf presentacion = null;
					
					if (productoIf.getPresentacionId() != null)
						presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
					
					String producto = productoIf.getCodigo() + " - " + generico.getNombre();
					
					if (presentacion != null)
						producto += " - " + presentacion.getNombre();
					
					tableModel.setValueAt(producto, filaSeleccionada, 0);
					tableModel.setValueAt(strCantidad, filaSeleccionada, 1);
					tableModel.setValueAt(formatoDecimal.format(Utilitarios.redondeoValor(valor)), filaSeleccionada, 2);
					tableModel.setValueAt(formatoDecimal.format(Utilitarios.redondeoValor(iva)), filaSeleccionada, 3);
					tableModel.setValueAt(formatoDecimal.format(Utilitarios.redondeoValor(ice)), filaSeleccionada, 4);
					tableModel.setValueAt(formatoDecimal.format(Utilitarios.redondeoValor(otroImpuestoSubtotal)), filaSeleccionada, 5);
					tableModel.setValueAt(formatoDecimal.format(Utilitarios.redondeoValor(subtotal)), filaSeleccionada, 6);
					
					if (productosLocal.get(productoIf.getId()) == null) {
						productosLocal.remove(idProductoTemp);
						productosLocal.put(productoIf.getId(), "P");
					}
					
					actualizarTotales();
				}
				
				//al actualizar solo se puede actualizar la referencia, tipo de nota y observación.
				else if (getMode() == SpiritMode.UPDATE_MODE && validarNotaCreditoDetalle()) {
					
					NotaCreditoDetalleIf data = notaCreditoDetalleForUpdate;
										
					TipoReferenciaNotaCredito tr = (TipoReferenciaNotaCredito) getCmbTipoReferencia().getSelectedItem(); 
					data.setTipoReferencia(tr.getLetra());
					
					if(tr == TipoReferenciaNotaCredito.FACTURA){
						data.setReferenciaId(facturaIf.getId());				
						data.setTipoPresupuesto(tipoPresupuesto);
						data.setOrdenId(ordenId);
						
						if(presupuestoIf != null)
							data.setPresupuestoId(presupuestoIf.getId());
						else if(planMedioIf != null)
							data.setPresupuestoId(planMedioIf.getId());			
					}
					
					else if(tr == TipoReferenciaNotaCredito.COMPRA){
						data.setReferenciaId(compraIf.getId());
						data.setTipoPresupuesto(tipoPresupuesto);
						data.setOrdenId(ordenId);
						
						if(presupuestoIf != null)
							data.setPresupuestoId(presupuestoIf.getId());
						else if(planMedioIf != null)
							data.setPresupuestoId(planMedioIf.getId());
					}
					
					else if(tr == TipoReferenciaNotaCredito.NINGUNO){
						facturaIf = null;
						compraIf = null;
						tipoPresupuesto = TipoReferenciaNotaCredito.NINGUNO.getLetra();
						data.setTipoPresupuesto(tipoPresupuesto);
						presupuestoIf = null;
						planMedioIf = null;
						ordenId = null;					
					}
					
					String tipoNota = (String)getCmbTipoNotaDetalle().getSelectedItem();
					data.setTipoNota(tipoNota.substring(0,1));
					data.setObservacion(getTxtObservacionDetalle().getText());
					
					int filaSeleccionada = getTblNotaCreditoDetalle().convertRowIndexToModel(getTblNotaCreditoDetalle().getSelectedRow() );
					
					notaCreditoDetalleColeccion.set(filaSeleccionada, data);
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private boolean validarNotaCreditoDetalle() {		
		if (documento == null) {
			SpiritAlert.createAlert("Debe seleccionar el documento !!!", SpiritAlert.WARNING);
			getJtpNotaCredito().grabFocus();
			getCmbDocumento().grabFocus();
			return false;
			
		}
		
		if ("".equals(getTxtProducto().getText().trim())) {
			SpiritAlert.createAlert("Debe ingresar un producto primero !!!", SpiritAlert.WARNING);
			getJtpNotaCredito().setSelectedIndex(1);
			getBtnBuscarProducto().grabFocus();
			return false;
		}
				
		if ("".equals(getTxtCantidad().getText().trim())) {
			SpiritAlert.createAlert("Debe ingresar la cantidad !!!", SpiritAlert.WARNING);
			getJtpNotaCredito().setSelectedIndex(1);
			getTxtCantidad().grabFocus();
			return false;
		} 
		
		if (Double.parseDouble(getTxtCantidad().getText()) <= 0D) {
			SpiritAlert.createAlert("La cantidad debe ser mayor que 0 !!!", SpiritAlert.WARNING);
			getJtpNotaCredito().setSelectedIndex(1);
			getTxtCantidad().grabFocus();
			return false;
		} 
		
		if ("".equals(Utilitarios.removeDecimalFormat(getTxtValor().getText()).trim())) {
			SpiritAlert.createAlert("Debe ingresar el valor !!!", SpiritAlert.WARNING);
			getJtpNotaCredito().setSelectedIndex(1);
			getTxtValor().grabFocus();
			return false;
		} 
		
		if (Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtValor().getText())) <= 0D) {
			SpiritAlert.createAlert("El valor debe ser mayor que 0 !!!", SpiritAlert.WARNING);
			getJtpNotaCredito().setSelectedIndex(1);
			getTxtValor().grabFocus();
			return false;
		}
		
		TipoReferenciaNotaCredito tr = (TipoReferenciaNotaCredito) getCmbTipoReferencia().getSelectedItem();
		if(tipoCartera.equals(TIPO_CARTERA_CLIENTE) && tr != TipoReferenciaNotaCredito.NINGUNO){
			if(getCmbTipoReferencia().getSelectedItem().equals(TipoReferenciaNotaCredito.COMPRA)){
				SpiritAlert.createAlert("Una cartera tipo Cliente no puede tener de referencia una Compra.", SpiritAlert.WARNING);
				getCmbTipoReferencia().grabFocus();
				return false;
			}
			if(facturaIf == null){
				SpiritAlert.createAlert("Por favor seleccione la factura.", SpiritAlert.WARNING);
				getTxtEscojaReferencia().grabFocus();
				return false;
			}
		}else if(tipoCartera.equals(TIPO_CARTERA_PROVEEDOR) && tr != TipoReferenciaNotaCredito.NINGUNO){
			if(getCmbTipoReferencia().getSelectedItem().equals(TipoReferenciaNotaCredito.FACTURA)){
				SpiritAlert.createAlert("Una cartera tipo Proveedor no puede tener de referencia una Factura.", SpiritAlert.WARNING);
				getCmbTipoReferencia().grabFocus();
				return false;
			}
			if(compraIf == null){
				SpiritAlert.createAlert("Por favor seleccione la compra.", SpiritAlert.WARNING);
				getTxtEscojaReferencia().grabFocus();
				return false;
			}
		}
		
				
		return true;
	}
	
	private void enableNotaCreditoDetalleForUpdate(NotaCreditoDetalleIf notaCreditoDetalle) {
		try {
			productoIf = SessionServiceLocator.getProductoSessionService().getProducto(notaCreditoDetalle.getProductoId());
			genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
			idProductoTemp = productoIf.getId();
			codigoProductoTemp = productoIf.getCodigo();
			
			if(notaCreditoDetalle.getDocumentoId() != null){
				getCmbDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocumento(),notaCreditoDetalle.getDocumentoId()));
				getCmbDocumento().validate();
				getCmbDocumento().repaint();
			}
			
			if ("S".equals(genericoIf.getUsaLote())) {
				PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
				getTxtProducto().setText(productoIf.getCodigo() + " - " + genericoIf.getNombre()+ " - " + presentacion.getNombre());
			} else
				getTxtProducto().setText(productoIf.getCodigo() + " - " + genericoIf.getNombre());
			
			double cantidad = notaCreditoDetalle.getCantidad().doubleValue();
			getTxtCantidad().setText(notaCreditoDetalle.getCantidad().toString());
			double valor = notaCreditoDetalle.getValor().doubleValue();
			getTxtValor().setText(formatoDecimal.format(Utilitarios.redondeoValor(valor)));
			String strOtroImpuesto = notaCreditoDetalle.getOtroImpuesto().toString();
			double otroImpuesto = (Double.parseDouble(strOtroImpuesto) * 100D / (valor * cantidad));
			getTxtOtroImpuesto().setText(String.valueOf(otroImpuesto));
			
			if(notaCreditoDetalle.getDescripcion() != null)
				getTxtDescripcion().setText(notaCreditoDetalle.getDescripcion());
			else
				getTxtDescripcion().setText("");
			
			
			if(notaCreditoDetalle.getTipoReferencia().equals(TipoReferenciaNotaCredito.NINGUNO.getLetra())){
				facturaIf = null;
				compraIf = null;
				tipoPresupuesto = TipoReferenciaNotaCredito.NINGUNO.getLetra();
				presupuestoIf = null;
				planMedioIf = null;
				ordenId = null;
				getCmbTipoReferencia().setSelectedItem(TipoReferenciaNotaCredito.NINGUNO);
				getTxtEscojaReferencia().setText("");
				getTxtEscojaReferencia().setBackground(Parametros.findColor);
				getTxtEscojaReferencia().setEditable(true);
				getBtnEscojaReferencia().setEnabled(true);
				
				getTxtEscojaOrden().setText("");
				getTxtEscojaOrden().setBackground(Parametros.findColor);
				getTxtEscojaOrden().setEditable(true);
				getBtnEscojaOrden().setEnabled(true);
				
			}else if(notaCreditoDetalle.getTipoReferencia().equals(TipoReferenciaNotaCredito.FACTURA.getLetra())){
				getCmbTipoReferencia().setSelectedItem(TipoReferenciaNotaCredito.FACTURA);
				if(notaCreditoDetalle.getReferenciaId() != null){
					facturaIf = SessionServiceLocator.getFacturaSessionService().getFactura(notaCreditoDetalle.getReferenciaId());
					getTxtEscojaReferencia().setText(facturaIf.getPreimpresoNumero());
					getTxtEscojaReferencia().setBackground(Parametros.saveUpdateColor);
					getTxtEscojaReferencia().setEditable(false);
					getBtnEscojaReferencia().setEnabled(false);
					
					tipoPresupuesto = notaCreditoDetalle.getTipoPresupuesto();
					
					if(notaCreditoDetalle.getTipoPresupuesto().equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())){
						presupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(notaCreditoDetalle.getPresupuestoId());
						if(notaCreditoDetalle.getOrdenId() != null){
							ordenCompraIf = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(notaCreditoDetalle.getOrdenId());
							getTxtEscojaOrden().setText(ordenCompraIf.getCodigo());
							getTxtEscojaOrden().setBackground(Parametros.saveUpdateColor);
							getTxtEscojaOrden().setEditable(false);
							getBtnEscojaOrden().setEnabled(false);
						}
					}
					else if(notaCreditoDetalle.getTipoPresupuesto().equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra())){
						planMedioIf = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(notaCreditoDetalle.getPresupuestoId());
						if(notaCreditoDetalle.getOrdenId() != null){
							ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(notaCreditoDetalle.getOrdenId());
							getTxtEscojaOrden().setText(ordenMedioIf.getCodigo());
							getTxtEscojaOrden().setBackground(Parametros.saveUpdateColor);
							getTxtEscojaOrden().setEditable(false);
							getBtnEscojaOrden().setEnabled(false);
						}
					}
										
				}				
				
			}else if(notaCreditoDetalle.getTipoReferencia().equals(TipoReferenciaNotaCredito.COMPRA.getLetra())){
				getCmbTipoReferencia().setSelectedItem(TipoReferenciaNotaCredito.COMPRA);
				if(notaCreditoDetalle.getReferenciaId() != null){
					compraIf = SessionServiceLocator.getCompraSessionService().getCompra(notaCreditoDetalle.getReferenciaId());
					getTxtEscojaReferencia().setText(compraIf.getPreimpreso());
					getTxtEscojaReferencia().setBackground(Parametros.saveUpdateColor);
					getTxtEscojaReferencia().setEditable(false);
					getBtnEscojaReferencia().setEnabled(false);
					
					tipoPresupuesto = notaCreditoDetalle.getTipoPresupuesto();
					ordenId = notaCreditoDetalle.getOrdenId();
					
					if(notaCreditoDetalle.getTipoPresupuesto().equals(TipoReferenciaPedido.PRESUPUESTO.getLetra())){
						presupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(notaCreditoDetalle.getPresupuestoId());
						if(notaCreditoDetalle.getOrdenId() != null){
							ordenCompraIf = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(notaCreditoDetalle.getOrdenId());
							getTxtEscojaOrden().setText(ordenCompraIf.getCodigo());
							getTxtEscojaOrden().setBackground(Parametros.saveUpdateColor);
							getTxtEscojaOrden().setEditable(false);
							getBtnEscojaOrden().setEnabled(false);
						}
					}
					else if(notaCreditoDetalle.getTipoPresupuesto().equals(TipoReferenciaPedido.PLAN_MEDIOS.getLetra())){
						planMedioIf = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(notaCreditoDetalle.getPresupuestoId());
						if(notaCreditoDetalle.getOrdenId() != null){
							ordenMedioIf = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(notaCreditoDetalle.getOrdenId());
							getTxtEscojaOrden().setText(ordenMedioIf.getCodigo());
							getTxtEscojaOrden().setBackground(Parametros.saveUpdateColor);
							getTxtEscojaOrden().setEditable(false);
							getBtnEscojaOrden().setEnabled(false);
						}
					}					
				}
			}
			
			if(notaCreditoDetalle.getTipoNota().equals("E")){
				getCmbTipoNotaDetalle().setSelectedItem("ERROR");
			}else if(notaCreditoDetalle.getTipoNota().equals("A")){
				getCmbTipoNotaDetalle().setSelectedItem("ANULACION");
			}else if(notaCreditoDetalle.getTipoNota().equals("G")){
				getCmbTipoNotaDetalle().setSelectedItem("GANANCIA");
			}else if(notaCreditoDetalle.getTipoNota().equals("O")){
				getCmbTipoNotaDetalle().setSelectedItem("OTRO");
			}
			
			getTxtObservacionDetalle().setText(notaCreditoDetalle.getObservacion());
						
			getTxtCantidad().setEnabled(true);
			getTxtCantidad().setEditable(true);
			getTxtValor().setEnabled(true);
			getTxtValor().setEditable(true);
			getTxtOtroImpuesto().setEnabled(true);
			getTxtOtroImpuesto().setEditable(true);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);			
		}
	}
	
	private void eliminarNotaCreditoDetalle() {
		if (getTblNotaCreditoDetalle().getSelectedRow() != -1) {
			DefaultTableModel model = (DefaultTableModel) getTblNotaCreditoDetalle().getModel();
			
			try {
				if(getMode() == SpiritMode.SAVE_MODE){
					int filaSeleccionada = getTblNotaCreditoDetalle().convertRowIndexToModel(getTblNotaCreditoDetalle().getSelectedRow() );
					NotaCreditoDetalleIf notaCreditoDetalleTemp = (NotaCreditoDetalleIf) notaCreditoDetalleColeccion.get(filaSeleccionada);
					
					if (notaCreditoDetalleTemp.getId() != null) {
						// Elimino el registro de la coleccion y de la Tabla
						notaCreditoDetalleColeccion.remove(filaSeleccionada);
						SessionServiceLocator.getNotaCreditoDetalleSessionService().deleteNotaCreditoDetalle(notaCreditoDetalleTemp.getId());
					} else {
						notaCreditoDetalleColeccion.remove(filaSeleccionada);
					}
					model.removeRow(filaSeleccionada);
				}else if (getMode() == SpiritMode.UPDATE_MODE){
					SpiritAlert.createAlert("No es posible agregar detalles en la actualización.\nSolo se puede actualizar la referencia y tipo de nota de cada detalle.", SpiritAlert.WARNING);
				}
				
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			SpiritAlert.createAlert("Debe elegir el detalle de la compra a eliminar !!!", SpiritAlert.WARNING);
		}
		
		cleanNotaCreditoDetalle();
		actualizarTotales();
	}
	
	private void cleanNotaCreditoDetalle() {
		getTxtProducto().setText("");
		getTxtCantidad().setText("");
		getTxtCantidad().setEnabled(false);
		getTxtCantidad().setEditable(false);
		getTxtValor().setText("");
		getTxtValor().setEnabled(false);
		getTxtValor().setEditable(false);
		getTxtOtroImpuesto().setText("");
		getTxtDescripcion().setText("");
		getTxtOtroImpuesto().setEnabled(false);
		getTxtOtroImpuesto().setEditable(false);
		getTxtObservacionDetalle().setText("");
	}
	
	private void actualizarTotales() {
		DefaultTableModel tableModel = (DefaultTableModel) getTblNotaCreditoDetalle().getModel();
		
		double totalValor = 0.00;
		double totalIVA = 0.00;
		double totalICE = 0.00;
		double totalOtrosImpuestos = 0.00;
		double grandTotal = 0.00;
		
		try {
			for (int fila = 0; fila < getTblNotaCreditoDetalle().getRowCount(); fila++) {
				String strCantidad = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 1).toString().trim());
				String strValor = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 2).toString().trim());
				String strIVA = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 3).toString().trim());
				String strICE = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 4).toString().trim());
				String strOtrosImpuestos = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 5).toString().trim());
				String strGrandTotal = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 6).toString().trim());
				
				if ((!strValor.equals("0")) && (!strValor.equals("0.00")) && (!strValor.equals("")) && (strValor != null))
					totalValor += Double.parseDouble(strCantidad) * Double.parseDouble(strValor);
				if ((!strIVA.equals("0")) && (!strIVA.equals("0.00")) && (!strIVA.equals("")) && (strIVA != null))
					//totalIVA += Double.parseDouble(strIVA);
					totalIVA = totalValor * IVA;
				if ((!strICE.equals("0")) && (!strICE.equals("0.00")) && (!strICE.equals("")) && (strICE != null))
					totalICE += Double.parseDouble(strICE);
				if ((!strOtrosImpuestos.equals("0")) && (!strOtrosImpuestos.equals("0.00")) && (!strOtrosImpuestos.equals("")) && (strOtrosImpuestos != null))
					totalOtrosImpuestos += Double.parseDouble(strOtrosImpuestos);
				if ((!strGrandTotal.equals("0")) && (!strGrandTotal.equals("0.00")) && (!strGrandTotal.equals("")) && (strGrandTotal != null))
					grandTotal = totalValor + totalIVA + totalICE + totalOtrosImpuestos;
			}
			
			getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalValor)));
			getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalIVA)));
			getTxtICEFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalICE)));
			getTxtOtroImpuestoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalOtrosImpuestos)));
			getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(grandTotal)));
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("¡ Ocurrió un error al actualizar los totales de la Nota de Crédito !", SpiritAlert.ERROR);
		}
	}
	 
	
	private String obtenerFacturaQmodifica(Long idNotaCredito,Long tipodocumentoId) {

		String preimpresoFactura="";
		
		try {		
		
		Map parameterMap = new HashMap();
		//parameterMap.put("empresaId", Long.valueOf(Parametros.getIdEmpresa()));
		parameterMap.put("referenciaId",idNotaCredito);
		parameterMap.put("tipodocumentoId", tipodocumentoId);
		
		List<CarteraIf> carteraColeccion = new ArrayList<CarteraIf>();		
		carteraColeccion = (List) SessionServiceLocator.getCarteraSessionService().findCarteraByQuery(parameterMap);
		Iterator it = carteraColeccion.iterator();		
			if (it.hasNext()) {
				CarteraIf carteraif= (CarteraIf) it.next();				
				List<CarteraDetalleIf> carteraDetalleColeccion = new ArrayList<CarteraDetalleIf>();	
				carteraDetalleColeccion = (List) SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(carteraif.getId());
				if(carteraDetalleColeccion!=null){
					Iterator it2 = carteraDetalleColeccion.iterator();
					if (it2.hasNext()) {
						CarteraDetalleIf carteraDetalleif= (CarteraDetalleIf) it2.next();	
						List<CarteraAfectaIf> carteraafectaColeccion = new ArrayList<CarteraAfectaIf>();
						carteraafectaColeccion = (List) SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(carteraDetalleif.getId());
						if(carteraafectaColeccion!=null){
							Iterator it3 = carteraafectaColeccion.iterator();
							if (it3.hasNext()) {						
								CarteraAfectaIf carteraafectaif= (CarteraAfectaIf) it3.next();						
								System.out.println("cartera afecta... : detalle cartera:"+carteraafectaif.getCarteraafectaId());
								Long carteradetalle_factura=carteraafectaif.getCarteraafectaId();
								if(carteradetalle_factura!=null){
									List<CarteraDetalleIf> carteraDetalleColeccion2 = new ArrayList<CarteraDetalleIf>();
									carteraDetalleColeccion2 = (List)	SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleById(carteradetalle_factura);
									if(carteraDetalleColeccion2!=null){
										Iterator it4 = carteraDetalleColeccion2.iterator();
										if(it4.hasNext()){
											CarteraDetalleIf cartdet= (CarteraDetalleIf) it4.next();						
											System.out.println("cartera cartdet... : detalle cartera:"+cartdet.getCarteraId());
											Long carteraid_factura=cartdet.getCarteraId();
											if(carteraid_factura!=null){
												List<CarteraIf> carte = new ArrayList<CarteraIf>();
												carte = (List)	SessionServiceLocator.getCarteraSessionService().findCarteraById(carteraid_factura);
												if(carte!=null){
													Iterator it5 = carte.iterator();
													if(it5.hasNext()){
														CarteraIf carteraFactura= (CarteraIf) it5.next();
														preimpresoFactura=carteraFactura.getPreimpreso();														
													}
												}
											}
											
										}	
									}
								}
							}
						}					
					}
				}				
			}
		
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("PREIMPRESO:->"+preimpresoFactura);
		return preimpresoFactura;
		
	}
		
		
	private void datosFinales_Totales() {
		
		//notaCreditoDetalleColeccion = (List) SessionServiceLocator.getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByNotaCreditoId(notaCredito.getId());
		
		DefaultTableModel tableModel = (DefaultTableModel) getTblNotaCreditoDetalle().getModel();
		double subtotal = 0D;
		 
		double subtotal12_T = 0.00;
		double subtotal0_T = 0.00;
		double descuento_T = 0.00;
		double subtotal_T = 0.00;
		double iva12_T = 0.00;
		double valortotal_T = 0.00;
		
		try {
			for (int fila = 0; fila < getTblNotaCreditoDetalle().getRowCount(); fila++) {
				String strCantidad = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 1).toString().trim());
				String strValor = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 2).toString().trim());
				String strIVA = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 3).toString().trim());
				String strICE = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 4).toString().trim());
				String strOtrosImpuestos = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 5).toString().trim());
				String strGrandTotal = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 6).toString().trim());
				
				if ((!strValor.equals("0")) && (!strValor.equals("0.00")) && (!strValor.equals("")) && (strValor != null))
					subtotal = Double.parseDouble(strCantidad) * Double.parseDouble(strValor);
				
				if ((!strIVA.equals("0")) && (!strIVA.equals("0.00")) && (!strIVA.equals("")) && (strIVA != null)) {
					subtotal12_T += subtotal;
					iva12_T += (subtotal * IVA);
				} else {
					subtotal0_T += subtotal;
				}
				
				subtotal_T = subtotal0_T + subtotal12_T;
				//iva12_T = totalIVA;
				valortotal_T = subtotal_T + iva12_T;
				
				/*
				if ((!strICE.equals("0")) && (!strICE.equals("0.00")) && (!strICE.equals("")) && (strICE != null))
					totalICE += Double.parseDouble(strICE);
				if ((!strOtrosImpuestos.equals("0")) && (!strOtrosImpuestos.equals("0.00")) && (!strOtrosImpuestos.equals("")) && (strOtrosImpuestos != null))
					totalOtrosImpuestos += Double.parseDouble(strOtrosImpuestos);
				
				if ((!strGrandTotal.equals("0")) && (!strGrandTotal.equals("0.00")) && (!strGrandTotal.equals("")) && (strGrandTotal != null))
					grandTotal = totalValor + totalIVA + totalICE + totalOtrosImpuestos;
				*/
				
			}
			
			 this.subtotal12_T = subtotal12_T;
			 this.subtotal0_T = subtotal0_T;
			 this.descuento_T = descuento_T;
			 this.subtotal_T = subtotal_T;
			 this.iva12_T = iva12_T;
			 this.valortotal_T = valortotal_T;
		  
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("¡ Ocurrió un error al actualizar los totales de la Nota de Crédito !", SpiritAlert.ERROR);
		}
	}
	
	public void cleanTableDetalle() {
		DefaultTableModel model = (DefaultTableModel) this.getTblNotaCreditoDetalle().getModel();
		for (int i = this.getTblNotaCreditoDetalle().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
		
		notaCreditoDetalleColeccion.clear();
		actualizarTotales();
		cleanNotaCreditoDetalle();
		this.repaint();
	}
		
	public void save() {
		if (validateFields()) {
			try {
				NotaCreditoIf notaCredito = registrarNotaCredito();
				notaCredito = SessionServiceLocator.getNotaCreditoSessionService().procesarNotaCredito(notaCredito, notaCreditoDetalleColeccion, Parametros.getIdEmpresa(), Parametros.getIdOficina());
				SpiritAlert.createAlert("Nota de Cr\u00e9dito guardada con \u00e9xito", SpiritAlert.INFORMATION);
				
				if (tipoCartera.equals(TIPO_CARTERA_CLIENTE))
					generarReporte(notaCredito);
				
				//para imprimir el asiento
				Map asientoMapa = new HashMap();
				asientoMapa.put("oficinaId", notaCredito.getOficinaId());
				asientoMapa.put("tipoDocumentoId", notaCredito.getTipoDocumentoId());
				asientoMapa.put("transaccionId", notaCredito.getId());
				Collection asientos = SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(asientoMapa);
				if(asientos.size() > 0){
					int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte del Asiento?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if(opcion == JOptionPane.YES_OPTION) {
						AsientoIf asientoReporte = (AsientoIf)asientos.iterator().next();
						generarReporteAsiento(asientoReporte);
					}
				}				
				
				clean();
				showSaveMode();
				getJtpNotaCredito().setSelectedIndex(0);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);				
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error general al guardar la Nota de Crédito",SpiritAlert.ERROR);				
			}
		}
	}
	
	private void generarReporteAsiento(AsientoIf asientoReporte) {
		try {
			ArrayList<AsientoDetalleIf> asientoDetalleColeccion = (ArrayList<AsientoDetalleIf>)SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByAsientoId(asientoReporte.getId());
			
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
				data.setFechaMovimiento(asientoReporte.getFecha().toString());
				data.setGlosa((asientoDetalle.getGlosa().length()>70?asientoDetalle.getGlosa().substring(0,70):asientoDetalle.getGlosa()));
				data.setDebe(formatoDecimal.format(asientoDetalle.getDebe()));
				data.setHaber(formatoDecimal.format(asientoDetalle.getHaber()));
				totalDebe += asientoDetalle.getDebe().doubleValue();
				data.setTotalDebe(formatoDecimal.format(totalDebe));
				totalHaber += asientoDetalle.getHaber().doubleValue();
				data.setTotalHaber(formatoDecimal.format(totalHaber));
				data.setMes(Utilitarios.getNombreMes(asientoReporte.getFecha().getMonth() + 1).substring(0,3));
				data.setNombreCuenta((cuenta.getNombre().length()>70?cuenta.getNombre().substring(0,70):cuenta.getNombre()));
				data.setNumero(asientoReporte.getNumero());
				if (asientoReporte.getElaboradoPorId() != null) {
					UsuarioIf elaboradoPor = (UsuarioIf) usuariosMap.get(asientoReporte.getElaboradoPorId());
					EmpleadoIf empleado = (EmpleadoIf) empleadosMap.get(elaboradoPor.getEmpleadoId());
					data.setElaboradoPor(empleado.getNombres() + " " + empleado.getApellidos());
				}
				
				if (asientoReporte.getAutorizadoPorId() != null) {
					UsuarioIf autorizadoPor = ((UsuarioIf) Parametros.getUsuarioIf());
					EmpleadoIf empleado = (EmpleadoIf) empleadosMap.get(autorizadoPor.getEmpleadoId());
					data.setAutorizadoPor(empleado.getNombres() + " " + empleado.getApellidos());
				}
				data.setTipoDocumentoId((asientoReporte.getTipoDocumentoId()!=null)?asientoReporte.getTipoDocumentoId():null);
				data.setTransaccionId((asientoReporte.getTransaccionId()!=null)?asientoReporte.getTransaccionId():null);
				autorizarAsientoDataColeccion.add(data);
			}
			
			String fileName = "jaspers/contabilidad/RPAutorizacionAsiento.jasper";
			int seccionesHoja = 1;
			Map tiposDocumentoMap = SessionServiceLocator.getTipoDocumentoSessionService().mapearTiposDocumento(Parametros.getIdEmpresa());
			Map tiposTroqueladoMap = SessionServiceLocator.getTipoTroqueladoSessionService().mapearTiposTroquelado();
			if (asientoReporte.getTipoDocumentoId() != null) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get(asientoReporte.getTipoDocumentoId());
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
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}
	
	public void update() {
		try {
			int opcion = JOptionPane.showOptionDialog(null, "Sólo se actualizará el Estado y la Observación de la Nota de Crédito. \nY la referencia y tipo de nota de cada detalle previamente guardado. ¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			
			if (opcion == JOptionPane.YES_OPTION) {
				
				notaCredito.setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
				notaCredito.setObservacion(getTxtObservacion().getText());
				notaCredito.setReferenciaId(referenciaId);
				notaCredito.setReferencia(getTxtReferencia().getText());
				
				//SessionServiceLocator.getNotaCreditoSessionService().saveNotaCredito(notaCredito);				
				SessionServiceLocator.getNotaCreditoSessionService().actualizarNotaCredito(notaCredito, notaCreditoDetalleColeccion);
				SpiritAlert.createAlert("Nota de Crédito actualizada con éxito", SpiritAlert.INFORMATION);
				
				this.clean();
				this.showSaveMode();
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void delete() {
		/*try {
			if (getNotaCreditoSessionService().eliminarNotaCredito(notaCredito.getId())) {
				SpiritAlert.createAlert("Nota de Crédito eliminada con éxito", SpiritAlert.INFORMATION);
				this.clean();
				this.showSaveMode();
			} else
				SpiritAlert.createAlert("No se ha podido eliminar la nota de crédito", SpiritAlert.WARNING);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al eliminar la Nota de Crédito!", SpiritAlert.ERROR);
		}*/
		SpiritAlert.createAlert("Eliminación de N/C no está permitida\nPara anular una N/C utilice la opción correspondiente en el menú Cartera -> Transacciones", SpiritAlert.INFORMATION);
	}
	
	public void report() {
		if (getMode() == SpiritMode.UPDATE_MODE) {
			generarReporte(notaCredito);
		}
	}

	private void generarReporte(NotaCreditoIf notaCredito) {
		try {
			if (notaCreditoDetalleColeccion.size() > 0) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir la Nota de Credito?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					Long idParametro = notaCredito.getId();
					Collection dataSourceCollection = initializeBeanCollection(idParametro, false);
					JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(dataSourceCollection);
					String fileName = "jaspers/cartera/RPNotaCredito.jasper";
					dataSourceCollection = initializeBeanCollection(idParametro, true);
					HashMap parametrosMap = new HashMap();
					
					if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) ) {
						parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/cartera/RPNotaCreditoDetalle.jasper");
					}else 
						throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
					
					TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(notaCredito.getTipoDocumentoId());
					parametrosMap.put("dataSourceDetail", dataSourceDetail);
					String fecha = notaCredito.getFechaEmision().toString();
					String year = fecha.substring(0,4);
					String month = fecha.substring(5,7);
					String day = fecha.substring(8,10);
					OficinaIf oficina = SessionServiceLocator.getOficinaSessionService().getOficina(notaCredito.getOficinaId());
					CiudadIf ciudad = SessionServiceLocator.getCiudadSessionService().getCiudad(oficina.getCiudadId());
					String nombreCiudad = ciudad.getNombre();
					//String fechaNotaCredito = nombreCiudad + ", " + Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					String fechaNotaCredito = day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + year;
					parametrosMap.put("fechaEmision", fechaNotaCredito);
					parametrosMap.put("fechaCreacion", fechaNotaCredito);
					
					 
					ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(notaCredito.getOperadorNegocioId());
					
					
					parametrosMap.put("telefono", clienteOficina.getTelefono());
					
					int maxLongitudClienteDireccionNotaCredito = Parametros.getMaxLongitudClienteDireccionNotaCredito();
					
					String direccion = clienteOficina.getDireccion();
					String direccionPrimeraLinea = direccion;
					String direccionSegundaLinea = "";
					if (direccion.length() >= maxLongitudClienteDireccionNotaCredito) {
						direccionPrimeraLinea = direccion.substring(0, maxLongitudClienteDireccionNotaCredito);
						direccionSegundaLinea = direccion.substring(maxLongitudClienteDireccionNotaCredito, direccion.length());
					}
					parametrosMap.put("direccionPrimeraLinea", direccionPrimeraLinea);
					parametrosMap.put("direccionSegundaLinea", direccionSegundaLinea);
					ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
					String razonSocial = cliente.getRazonSocial();
					String razonSocialPrimeraLinea = razonSocial;
					String razonSocialSegundaLinea = "";
					if (razonSocial.length() >= maxLongitudClienteDireccionNotaCredito) {
						razonSocialPrimeraLinea = razonSocial.substring(0, maxLongitudClienteDireccionNotaCredito);
						razonSocialSegundaLinea = razonSocial.substring(maxLongitudClienteDireccionNotaCredito, razonSocial.length());
					}
					parametrosMap.put("razonSocialClientePrimeraLinea", razonSocialPrimeraLinea);
					parametrosMap.put("razonSocialClienteSegundaLinea", razonSocialSegundaLinea);
					parametrosMap.put("identificacion", cliente.getIdentificacion());
					Double subtotal = notaCredito.getValor().doubleValue();
					Double iva = notaCredito.getIva().doubleValue();
					Double total = subtotal + iva;
					String totalNotaCredito = formatoDecimal.format(Utilitarios.redondeoValor(total));
					String parteDecimal = totalNotaCredito.substring(totalNotaCredito.indexOf('.'), totalNotaCredito.length());
					Double dParteDecimal = 0.0;
					if (!parteDecimal.isEmpty())
						dParteDecimal = Double.valueOf(parteDecimal);
					String[] cantidadLetras = Utilitarios.obtenerCantidadEnLetras(totalNotaCredito, dParteDecimal, new int[] { 200 }, false, (MonedaIf) getCmbMoneda().getSelectedItem());
					String cantidadLetrasPrimeraLinea = cantidadLetras[0].replaceAll("  ","");
					parametrosMap.put("cantidadEnLetras", cantidadLetrasPrimeraLinea);
					parametrosMap.put("subtotal", BigDecimal.valueOf(subtotal));
					parametrosMap.put("iva", BigDecimal.valueOf(iva));
					parametrosMap.put("total", BigDecimal.valueOf(total));
					parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
					
					totalValor = 0.00;
					totalIVA = 0.00;
					totalICE = 0.00;
					totalOtrosImpuestos = 0.00;
					grandTotal = 0.00;
					datosFinales_Totales();
					
					String preimpreso = "";
					preimpreso = obtenerFacturaQmodifica(idParametro,tipoDocumento.getId());
					parametrosMap.put("preimpreso", preimpreso);
					
					parametrosMap.put("subtotal12_T", BigDecimal.valueOf(subtotal12_T));
					parametrosMap.put("subtotIvaCero_T", BigDecimal.valueOf(subtotal0_T));
					parametrosMap.put("descuento_T", BigDecimal.valueOf(descuento_T));
					parametrosMap.put("subtotal_T", BigDecimal.valueOf(subtotal_T));
					parametrosMap.put("iva12_T", BigDecimal.valueOf(iva12_T));
					parametrosMap.put("valortotal_T", BigDecimal.valueOf(valortotal_T));
					 
				 
					ReportModelImpl.processReport(fileName, parametrosMap, dataSourceCollection, true);
				}
			} else {
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!!!", SpiritAlert.ERROR);
		}
	}
	
	private Collection initializeBeanCollection(Long idParametro, boolean isHeader) {
		ArrayList reportRows = new ArrayList();
		Collection rowCollection = null;
		try {
			if (!isHeader)
				rowCollection = SessionServiceLocator.getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByNotaCreditoId(idParametro);
			else
				rowCollection = SessionServiceLocator.getNotaCreditoSessionService().findNotaCreditoById(idParametro);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator itRows = rowCollection.iterator();

		while (itRows.hasNext()) {
			if (!isHeader) {
				NotaCreditoDetalleIf bean = (NotaCreditoDetalleIf) itRows.next();
				reportRows.add(bean);
			} else {
				NotaCreditoIf bean = (NotaCreditoIf) itRows.next();
				reportRows.add(bean);
			}
		}

		return reportRows;
	}
	
	public void refresh() {
		cargarComboTipoDocumento();
		cargarComboMonedas();
	}
	
	public void duplicate() {
	
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpNotaCredito().getSelectedIndex();
		int tabCount = this.getJtpNotaCredito().getTabCount();
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpNotaCredito().setSelectedIndex(selectedTab);
	}
	
	public void addDetail() {
		if (getJtpNotaCredito().getSelectedIndex() == 1)
			agregarNotaCreditoDetalle();
	}
	
	public void updateDetail() {
		if (getJtpNotaCredito().getSelectedIndex() == 1)
			actualizarNotaCreditoDetalle();
	}
	
	public void deleteDetail() {
		if (getJtpNotaCredito().getSelectedIndex() == 1)
			eliminarNotaCreditoDetalle();
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		Long operadorNegocioId = 0L;
		
		aMap.put("tipoCartera", tipoCartera);
		
		if (getTxtCodigo().getText().equals("") == false)
			aMap.put("codigo", getTxtCodigo().getText() + "%");
		else
			aMap.put("codigo", "%");
		
		if (operadorNegocio != null) {
			operadorNegocioId = operadorNegocio.getId();
			aMap.put("operadorNegocioId", operadorNegocioId);
		}
				
		if ( getCmbEstado().getSelectedItem()!=null )
			aMap.put("estado", getCmbEstado().getSelectedItem().toString().substring(0, 1));
		
		if ( getTxtPreimpreso().getText()!=null && !"".equals(getTxtPreimpreso().getText()))
			aMap.put("preimpreso", getTxtPreimpreso().getText().trim() );
		
		return aMap;
	}
	
	public void find() {
		try {
			Map mapa = buildQuery();
			int tamanoLista = SessionServiceLocator.getNotaCreditoSessionService().getNotaCreditoByQueryListSize(mapa, Parametros.getIdEmpresa());
			if (tamanoLista > 0) {
				NotaCreditoCriteria notaCreditoCriteria = new NotaCreditoCriteria();
				notaCreditoCriteria.setResultListSize(tamanoLista);
				notaCreditoCriteria.setQueryBuilded(mapa);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), notaCreditoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if (popupFinder.getElementoSeleccionado() != null)
					getSelectedObject(popupFinder.getElementoSeleccionado());
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		} catch (Exception e){
			SpiritAlert.createAlert("Error general en la búsqueda de información", SpiritAlert.ERROR);
		}
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public void clean() {
		notaCreditoDetalleColeccion = null;
		notaCreditoDetalleColeccion = new ArrayList<NotaCreditoDetalleIf>();
		notaCreditoDetalleEliminadas = null;
		notaCreditoDetalleEliminadas = new ArrayList<NotaCreditoDetalleIf>();		
		limpiarReferencia();
	}

	private void limpiarReferencia() {
		facturaIf = null;
		compraIf = null;
		tipoPresupuesto = TipoReferenciaNotaCredito.NINGUNO.getLetra();
		presupuestoIf = null;
		planMedioIf = null;
		ordenId = null;
		getCmbTipoReferencia().setEnabled(false);
		getTxtEscojaReferencia().setText("");
		getTxtEscojaReferencia().setBackground(Parametros.findColor);
		getTxtEscojaReferencia().setEnabled(false);
		getBtnEscojaReferencia().setEnabled(false);
		getBtnLimpiarEscojaReferencia().setEnabled(false);
		
		getTxtEscojaOrden().setText("");
		getTxtEscojaOrden().setBackground(Parametros.findColor);
		getTxtEscojaOrden().setEnabled(false);
		getBtnEscojaOrden().setEnabled(false);
		getBtnLimpiarEscojaOrden().setEnabled(false);
	}
	
	public void showFindMode() {
		clean();
		cleanTableDetalle();
		getCmbTipoCartera().setBackground(Parametros.findColor);
		getCmbEstado().setBackground(Parametros.findColor);
		getTxtCodigo().setBackground(Parametros.findColor);
		getTxtOperadorNegocio().setBackground(Parametros.findColor);
		getTxtCodigo().addKeyListener(oKeyListenerTxtCodigo);
		getTxtPreimpreso().setBackground(Parametros.findColor);
		
		operadorNegocio = null;
		getTxtCodigo().setText("");
		getTxtCodigo().setEditable(true);
		getCmbFechaEmision().setSelectedItem(null);
		getCmbFechaEmision().setEnabled(false);
		getCmbFechaVencimiento().setSelectedItem(null);
		getCmbFechaVencimiento().setEnabled(false);
		getCmbFechaCaducidad().setSelectedItem(null);
		getCmbFechaCaducidad().setEnabled(false);
		getTxtOficina().setEnabled(false);
		getTxtOperadorNegocio().setText("");
		getTxtOperadorNegocio().setEditable(false);
		getBtnBuscarOperadorNegocio().setEnabled(true);
		getCmbMoneda().setEnabled(false);
		getCmbEstado().setEnabled(true);
		getTxtObservacion().setText("");
		getTxtObservacion().setEnabled(false);
		getTxtPreimpreso().setText("");
		getTxtPreimpreso().setEnabled(true);
		getTxtAutorizacion().setText("");
		getTxtAutorizacion().setEnabled(false);
		getTxtUsuario().setEnabled(false);
		getBtnBuscarProducto().setEnabled(false);
		getTxtProducto().setEditable(false);
		getTxtCantidad().setEnabled(false);
		getTxtValor().setEnabled(false);
		getTxtOtroImpuesto().setEnabled(false);
		getBtnAgregarDetalle().setEnabled(false);
		getBtnActualizarDetalle().setEnabled(false);
		getBtnEliminarDetalle().setEnabled(false);
		getCmbMoneda().setSelectedItem(null);
		referenciaId = null;
		getTxtReferencia().setText("");
		getTxtCodigo().grabFocus();
	}
	
	public void showSaveMode() {
		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVA);
		getCmbTipoCartera().setBackground(Parametros.saveUpdateColor);
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().setBackground(getBackground());
		getTxtPreimpreso().setBackground(Parametros.saveUpdateColor);
		getTxtOperadorNegocio().setBackground(getBackground());
		getTxtCodigo().removeKeyListener(oKeyListenerTxtCodigo);
		getCmbFechaEmision().setEditable(false);		
		setSaveMode();
		operadorNegocio = null;
		getTxtCodigo().setText("");
		getTxtCodigo().setEditable(false);
		getCmbFechaEmision().setEnabled(true);
		getCmbFechaVencimiento().setEnabled(true);
		getCmbFechaCaducidad().setEnabled(true);
		getTxtOficina().setText(Parametros.getNombreOficina());
		getTxtOficina().setEnabled(true);
		getTxtUsuario().setText(Parametros.getUsuario());
		getTxtUsuario().setEnabled(true);
		getTxtObservacion().setText("");
		getTxtObservacion().setEnabled(true);
		getTxtPreimpreso().setText("");
		getTxtPreimpreso().setEnabled(true);
		getTxtAutorizacion().setText("");
		getTxtAutorizacion().setEnabled(true);
		getTxtReferencia().setText("");
		referenciaId = null;
		getTxtOperadorNegocio().setText("");
		getTxtOperadorNegocio().setEditable(false);
		getCmbMoneda().setEnabled(true);
		if (getCmbMoneda().getItemCount() > 0)
			getCmbMoneda().setSelectedIndex(0);
		getCmbEstado().setEnabled(true);
		getTxtProducto().setText("");
		getTxtProducto().setEditable(false);
		getBtnBuscarProducto().setEnabled(false);
		getTxtCantidad().setText("");
		getTxtCantidad().setEnabled(false);
		getTxtCantidad().setEditable(false);
		getTxtValor().setText("");
		getTxtValor().setEnabled(false);
		getTxtValor().setEditable(false);
		getTxtOtroImpuesto().setText("");
		getTxtOtroImpuesto().setEnabled(false);
		getTxtOtroImpuesto().setEditable(false);
		totalValor = 0.00;
		totalIVA = 0.00;
		totalICE = 0.00;
		totalOtrosImpuestos = 0.00;
		grandTotal = 0.00;
		getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalValor)));
		getTxtValorFinal().setEditable(false);
		getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalIVA)));
		getTxtIVAFinal().setEditable(false);
		getTxtICEFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalICE)));
		getTxtICEFinal().setEditable(false);
		getTxtOtroImpuestoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalOtrosImpuestos)));
		getTxtOtroImpuestoFinal().setEditable(false);
		getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(grandTotal)));
		getTxtTotalFinal().setEditable(false);
		getBtnAgregarDetalle().setEnabled(true);
		getBtnActualizarDetalle().setEnabled(true);
		getBtnEliminarDetalle().setEnabled(true);
		
		java.util.Date fechaHoy = new java.util.Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fechaHoy);
		getCmbFechaEmision().setCalendar(calendar);
		getCmbFechaVencimiento().setCalendar(calendar);
		getCmbFechaCaducidad().setCalendar(calendar);
		getCmbFechaEmision().setCalendar(calendar);
				
		clean();
		cleanTableDetalle();

		this.getJtpNotaCredito().setSelectedIndex(0);
		getBtnBuscarOperadorNegocio().grabFocus();
	}
	
	public void showUpdateMode() {
		getCmbTipoCartera().setBackground(Parametros.saveUpdateColor);
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().setBackground(getBackground());
		getTxtOperadorNegocio().setBackground(getBackground());
		getTxtPreimpreso().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().removeKeyListener(oKeyListenerTxtCodigo);
		
		setUpdateMode();
		getTxtCodigo().setText("");
		getTxtCodigo().setEditable(false);
		getCmbFechaEmision().setEnabled(true);
		getCmbFechaVencimiento().setEnabled(true);
		getCmbFechaCaducidad().setEnabled(true);
		getTxtOficina().setEnabled(true);
		getTxtOperadorNegocio().setEditable(false);
		getBtnBuscarOperadorNegocio().setEnabled(true);
		getCmbMoneda().setEnabled(true);
		getCmbEstado().setEnabled(true);
		getTxtObservacion().setText("");
		getTxtObservacion().setEnabled(true);
		getTxtPreimpreso().setText("");
		getTxtPreimpreso().setEnabled(true);
		getTxtAutorizacion().setText("");
		getTxtAutorizacion().setEnabled(true);
		getTxtReferencia().setText("");
		referenciaId = null;
		getTxtUsuario().setEnabled(true);
		getBtnBuscarProducto().setEnabled(true);
		getTxtProducto().setEditable(false);
		getTxtCantidad().setEnabled(true);
		getTxtValor().setEnabled(true);
		getTxtOtroImpuesto().setEnabled(true);
		getBtnAgregarDetalle().setEnabled(true);
		getBtnActualizarDetalle().setEnabled(true);
		getBtnEliminarDetalle().setEnabled(true);
		getTxtObservacion().grabFocus();
	}
	
	public boolean validateFields() {
		String strObservacion = getTxtObservacion().getText();
		String strAutorizacion = getTxtAutorizacion().getText();
		Date fechaNotaCredito = getCmbFechaEmision().getDate();
		Date fechaVencimiento = getCmbFechaVencimiento().getDate();
		Date fechaCaducidad = getCmbFechaCaducidad().getDate();
		String strTotalValor = getTxtValorFinal().getText();
		
		if (tipoDocumento == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de documento !!", SpiritAlert.INFORMATION);
			getJtpNotaCredito().setSelectedIndex(0);
			getCmbTipoDocumento().grabFocus();
			return false;
		}
						
		if (fechaNotaCredito != null) {
			if (fechaVencimiento.before(fechaNotaCredito)) {
				SpiritAlert.createAlert("La fecha de vencimiento debe ser posterior a la fecha de emisión !!", SpiritAlert.WARNING);
				getJtpNotaCredito().setSelectedIndex(0);
				getCmbFechaVencimiento().grabFocus();
				return false;
			}
			
			if (fechaCaducidad.before(fechaNotaCredito)) {
				SpiritAlert.createAlert("La fecha de caducidad debe ser posterior a la fecha de emisión !!", SpiritAlert.WARNING);
				getJtpNotaCredito().setSelectedIndex(0);
				getCmbFechaCaducidad().grabFocus();
				return false;
			}
		} else {
			SpiritAlert.createAlert("La fecha de la Nota de Crédito debe ser ingresada !!", SpiritAlert.WARNING);
			getJtpNotaCredito().setSelectedIndex(0);
			this.getCmbFechaEmision().grabFocus();
			return false;
		}
		
		if (operadorNegocio == null) {
			String tipoOperador = "proveedor";
			if (tipoCartera.equals(TIPO_CARTERA_CLIENTE))
				tipoOperador = "cliente";
			SpiritAlert.createAlert("Debe seleccionar un " + tipoOperador + " !!", SpiritAlert.WARNING);
			getJtpNotaCredito().setSelectedIndex(0);
			getBtnBuscarOperadorNegocio().grabFocus();
			return false;
		}
		
		if ("".equals(strObservacion)) {
			SpiritAlert.createAlert("Debe ingresar una observación !!", SpiritAlert.WARNING);
			getJtpNotaCredito().setSelectedIndex(0);
			getTxtObservacion().grabFocus();
			return false;
		}
				
		if ( !validatePreimpresoValido() )
			return false;
		
		if ("".equals(strAutorizacion)) {
			SpiritAlert.createAlert("Debe ingresar la autorización !!", SpiritAlert.WARNING);
			getJtpNotaCredito().setSelectedIndex(0);
			getTxtAutorizacion().grabFocus();
			return false;
		}
		
		if (getTblNotaCreditoDetalle().getRowCount() == 0) {
			SpiritAlert.createAlert("Debe existir al menos un detalle para guardar la Nota de Crédito !!", SpiritAlert.WARNING);
			getJtpNotaCredito().setSelectedIndex(1);
			getBtnBuscarProducto().grabFocus();
			return false;
		}
		
		if (Double.parseDouble(Utilitarios.removeDecimalFormat(strTotalValor)) <= 0) {
			SpiritAlert.createAlert("El valor total debe ser mayor que 0 !!", SpiritAlert.WARNING);
			getJtpNotaCredito().setSelectedIndex(1);
			getBtnBuscarProducto().grabFocus();
			return false;
		}
		
		return true;
	}
	
	private boolean validatePreimpresoValido(){
		String strPreimpreso = getTxtPreimpreso().getText().trim();
		if ("".equals(strPreimpreso)) {
			SpiritAlert.createAlert("Debe ingresar el preimpreso !!", SpiritAlert.WARNING);
			getJtpNotaCredito().setSelectedIndex(0);
			getTxtPreimpreso().grabFocus();
			return false;
		} else {
			//Pattern patron = Pattern.compile("[\\d]{3}-[\\d]{3}-[\\d]{7}");
			Pattern patron = Pattern.compile(Parametros.getPatronPreimpreso());
			Matcher matcher = patron.matcher(strPreimpreso);
			//boolean encontrado = matcher.find(); 
			boolean encontrado = matcher.matches(); 
			if ( !encontrado ||
					( encontrado && matcher.end()!=strPreimpreso.length() )	){
				SpiritAlert.createAlert("El formato del preimpreso tiene que ser ###-###-####### !!", SpiritAlert.WARNING);
				getJtpNotaCredito().setSelectedIndex(0);
				getTxtPreimpreso().grabFocus();
				return false;
			}
		}
		return true;
	}
	
	private NotaCreditoIf registrarNotaCredito() throws GenericBusinessException {
		NotaCreditoData data = new NotaCreditoData();
		try {
			java.sql.Timestamp fechaCreacion = new java.sql.Timestamp(getCmbFechaEmision().getDate().getTime());
			String codigo = getCodigoNotaCredito(new java.sql.Date(fechaCreacion.getYear(), fechaCreacion.getMonth(), fechaCreacion.getDate()));
			data.setCodigo(codigo);
			data.setOficinaId(Parametros.getIdOficina());
			data.setTipoDocumentoId(tipoDocumento.getId());
			data.setOperadorNegocioId(operadorNegocio.getId());
			data.setMonedaId(((MonedaIf) getCmbMoneda().getSelectedItem()).getId());
			Long idUsuario = ((UsuarioIf) (SessionServiceLocator.getUsuarioSessionService().findUsuarioByUsuario(Parametros.getUsuario().toLowerCase()).iterator().next())).getId();
			data.setUsuarioId(idUsuario);
			data.setFechaEmision(fechaCreacion);
			data.setFechaVencimiento(new java.sql.Timestamp(getCmbFechaVencimiento().getDate().getTime()));
			data.setFechaCaducidad(new java.sql.Timestamp(getCmbFechaCaducidad().getDate().getTime()));
			data.setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
			data.setReferencia(getTxtReferencia().getText());
			data.setObservacion(getTxtObservacion().getText());
			data.setPreimpreso(getTxtPreimpreso().getText());
			data.setAutorizacion(getTxtAutorizacion().getText());
			Double valor = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtValorFinal().getText()));
			data.setValor(BigDecimal.valueOf(valor));
			Double iva = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtIVAFinal().getText()));
			data.setIva(BigDecimal.valueOf(iva));
			Double ice = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtICEFinal().getText()));
			data.setIce(BigDecimal.valueOf(ice));
			Double otroImpuesto = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtOtroImpuestoFinal().getText()));
			data.setOtroImpuesto(BigDecimal.valueOf(otroImpuesto));
			data.setTipoCartera(tipoCartera);
			data.setReferenciaId(referenciaId);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!!", SpiritAlert.ERROR);
		}
		return data;
	}
	
	private String getCodigoNotaCredito(java.sql.Date fechaNotaCredito) {
		String codigo = "";
		String anioNotaCredito = Utilitarios.getYearFromDate(fechaNotaCredito);
		codigo += anioNotaCredito + "-";
		return codigo;
	}
	
	private NotaCreditoIf registrarNotaCreditoForUpdate() {
		notaCredito.setOficinaId(Parametros.getIdOficina());
		notaCredito.setTipoDocumentoId(tipoDocumento.getId());
		notaCredito.setOperadorNegocioId(operadorNegocio.getId());
		notaCredito.setMonedaId(((MonedaIf) getCmbMoneda().getSelectedItem()).getId());
		Long idUsuario = 0L;
		try {
			idUsuario = ((UsuarioIf) (SessionServiceLocator.getUsuarioSessionService().findUsuarioByUsuario(Parametros.getUsuario().toLowerCase()).iterator().next())).getId();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		notaCredito.setUsuarioId(idUsuario);
		notaCredito.setFechaEmision(new java.sql.Timestamp(getCmbFechaEmision().getDate().getTime()));
		notaCredito.setFechaVencimiento(new java.sql.Timestamp(getCmbFechaVencimiento().getDate().getTime()));
		notaCredito.setFechaCaducidad(new java.sql.Timestamp(getCmbFechaCaducidad().getDate().getTime()));
		if ( notaCredito.getEstado()!=null )
			notaCredito.setEstado(getCmbEstado().getSelectedItem().toString().substring(0, 1));
		notaCredito.setReferencia(getTxtReferencia().getText());
		notaCredito.setObservacion(getTxtObservacion().getText());
		notaCredito.setPreimpreso(getTxtPreimpreso().getText());
		notaCredito.setAutorizacion(getTxtAutorizacion().getText());
		notaCredito.setValor(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtValorFinal().getText()))));
		notaCredito.setIva(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtIVAFinal().getText()))));
		notaCredito.setIce(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtICEFinal().getText()))));
		notaCredito.setOtroImpuesto(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtOtroImpuestoFinal().getText()))));
		notaCredito.setTipoCartera(tipoCartera);
		return notaCredito;
	}
}