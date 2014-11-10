package com.spirit.compras.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.bpm.process.elements.Tarea;
import com.spirit.bpm.process.gui.BpmPanel;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.MainFrameModel;
import com.spirit.client.NumberCellRenderer;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritModel;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraAsociadaGastoIf;
import com.spirit.compras.entity.CompraData;
import com.spirit.compras.entity.CompraDetalleData;
import com.spirit.compras.entity.CompraDetalleGastoIf;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraGastoData;
import com.spirit.compras.entity.CompraGastoIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.compras.entity.GastoIf;
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.entity.OrdenCompraDetalleIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.gastos.CompraAsociadaGastoClase;
import com.spirit.compras.gastos.CompraDetalleGastoClase;
import com.spirit.compras.gastos.CompraGastoClase;
import com.spirit.compras.gui.criteria.CompraCriteria;
import com.spirit.compras.gui.criteria.ProductoCompraCriteria;
import com.spirit.compras.gui.panel.JPCompra;
import com.spirit.compras.gui.util.TextFieldCellEditor;
import com.spirit.compras.handler.OrderData;
import com.spirit.comun.util.CompraDetalleLiquidacionData;
import com.spirit.comun.util.RetencionProveedorData;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.gui.data.AutorizarAsientoData;
import com.spirit.contabilidad.gui.model.AsientoModel;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.ClienteRetencionIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.ServiceInstantiationException;
import com.spirit.exception.UnknownServiceException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.LineaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoTroqueladoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.controller.PanelHandler;
import com.spirit.general.gui.enums.TipoGasto;
import com.spirit.inventario.entity.ColorIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.ComercialIf;
import com.spirit.medios.entity.DerechoProgramaIf;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.sri.entity.SriSustentoTributarioIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class CompraModel extends JPCompra implements BpmPanel{
	
	private static final long serialVersionUID = -8459413652603920876L;
	
	private static final String PRODUCTO = "P";
	private static final String DOCUMENTO_COMPRA_LOCAL = "COML";
	private static final String NOMBRE_ESTADO_INACTIVA = "INACTIVA";
	private static final String ESTADO_INACTIVA = NOMBRE_ESTADO_INACTIVA.substring(0, 1);
	private static final String NOMBRE_ESTADO_ACTIVA = "ACTIVA";
	private static final String ESTADO_ACTIVA = NOMBRE_ESTADO_ACTIVA.substring(0, 1);
	private static final String NOMBRE_TIPO_COMPRA_LOCAL = "LOCAL";
	private static final String NOMBRE_ESTADO_NORMAL_REEMBOLSO = "NORMAL A REEMBOLSO";
	private static final String ESTADO_NORMAL_REEMBOLSO = "X";
	private static final String NOMBRE_ESTADO_REEMBOLSO_NORMAL = "REEMBOLSO A NORMAL";
	private static final String ESTADO_REEMBOLSO_NORMAL = "Y";
	private static final String ESTADO_RIESGO = "R";
	private static final String TIPO_COMPRA_LOCAL = NOMBRE_TIPO_COMPRA_LOCAL.substring(0, 1);
	private static final String NOMBRE_TIPO_COMPRA_IMPORTACION = "IMPORTACIÓN";
	private static final String OPERACION_AGREGAR = "AGREGAR";
	private static final String AGREGAR = OPERACION_AGREGAR.substring(1, 2);
	private static final String OPERACION_ACTUALIZAR = "ACTUALIZAR";
	private static final String ACTUALIZAR = OPERACION_ACTUALIZAR.substring(1,2);
	private static final int MAX_LONGITUD_CODIGO = 10;
	private static final int MAX_LONGITUD_OBSERVACION = 3000;
	private static final int MAX_LONGITUD_REFERENCIA = 20;
	private static final int MAX_LONGITUD_PRODUCTO = 100;
	private DecimalFormat formatoSerialMes = new DecimalFormat("00");
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private double totalValor = 0.00;
	private double totalDescuentoEspecial = 0.00;
	private double totalDescuento = 0.00;
	private double totalBonificacion = 0.00;
	private double totalIVA = 0.00;
	private double totalRetencion = 0.00;
	private double totalICE = 0.00;
	private double totalOtrosImpuestos = 0.00;
	private double grandTotal = 0.00;
	protected Double IVA = Parametros.getIVA() / 100;
	private JDPopupFinderModel popupFinder;
	private ProductoCompraCriteria productoCompraCriteria;
	private ProductoCriteria productoCriteria;
	ArrayList lista;
	private List<CompraDetalleIf> compraDetalleColeccion = new ArrayList<CompraDetalleIf>();
	private List<CompraDetalleIf> compraDetalleEliminadas = new ArrayList<CompraDetalleIf>();
	private CompraDetalleIf compraDetalleForUpdate;
	private TipoDocumentoIf tipoDocumentoIf;
	private DocumentoIf documentoIf;
	private ClienteOficinaIf proveedorIf;
	private ProductoIf productoIf;
	private GenericoIf genericoIf;
	private Map productosLocal = new HashMap();
	protected PresupuestoIf presupuestoIf;
	private CompraIf compra;
	java.sql.Date fechaCreacionCartera;
	Long idProductoTemp = 0L;
	String codigoProductoTemp = "";
	String strTipoReferencia = "";
	private static final int MAX_LONGITUD_CANTIDAD = 8;
	private static final int MAX_LONGITUD_VALOR = 22;
	private static final int MAX_LONGITUD_DESCUENTO = 5;
	private static final int MAX_LONGITUD_OTRO_IMPUESTO = 3;
	private static final int MAX_LONGITUD_DESCRIPCION = 3000;
	private static final int MAX_LONGITUD_ORDEN_COMPRA = 10;
	private long idTareaCompra = 0L;
	private Vector<CompraRetencionIf> compraRetencionColeccion = new Vector<CompraRetencionIf>();
	private Vector<CompraRetencionIf> compraRetencionEliminadaColeccion = new Vector<CompraRetencionIf>();
	private DefaultTableModel modelTblRetenciones;
	private CompraRetencionIf compraRetencion  = null;
	private int filaSeleccionadaRetencion = -1;
	protected JMenuItem menuItem;
	protected JPopupMenu popup = new JPopupMenu();
	private static final String IMPUESTO_RENTA = "RENTA";
	private static final String IMPUESTO_IVA = "IVA";
	private List<RetencionProveedorData> retencionReportList = new ArrayList<RetencionProveedorData>();
	private List<CompraDetalleLiquidacionData> compraDetalleLiquidacionList = new ArrayList<CompraDetalleLiquidacionData>();
	private boolean ordenRequerida = true;
	private static Map panels;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no}; 	
	public static final int COLUMNA_GASTO_NOMBRE_GASTO = 0;
	final int COLUMNA_GASTO_TIPO_GASTO = 1;
	final int COLUMNA_GASTO_DETALLE = 3;
	final int COLUMNA_GASTO_COMPRA_ASOCIADA = 4;
	final int COLUMNA_GASTO_VALOR = 2;
	final int MAPA_COMPRA_GASTO = 0;
	final int MAPA_COMPRA_ASOCIADA_GASTO = 1;
	private Map<Long, GastoIf> mapaGastosPorId = null;
	private Map<String, GastoIf> mapaGastosPorNombre = null;
	private Map<Long, String> mapaProductoNombre = null; 
	private CompraGastoClase compraGastoClase = null;
	CompraDetalleGastoModel cdgm = null;
	TextFieldCellEditor tfce = new TextFieldCellEditor(TextFieldCellEditor.CABECERA,TextFieldCellEditor.NUMERICO,10);
	private String ESTADO_ANTERIOR = "";
	private Map<Long,GenericoIf> genericosMap = new HashMap<Long,GenericoIf>();
	private Map<Long,ProductoIf> productosMap = new HashMap<Long,ProductoIf>();
	private Map<Long,PresentacionIf> presentacionesMap = new HashMap<Long,PresentacionIf>();
	private Map<Long,ModeloIf> modelosMap = new HashMap<Long,ModeloIf>();
	private Map<Long,LineaIf> lineasMap = new HashMap<Long,LineaIf>();
	private Map<Long,ColorIf> coloresMap = new HashMap<Long,ColorIf>();
	private static final String TIPO_ORDEN_COMPRA = "C";
	private static final String TIPO_ORDEN_MEDIO  = "M";
	private String tipoOrden = TIPO_ORDEN_COMPRA;		
	private static final String TIPO_REFERENCIA_PRESUPUESTO = "P";
	private Vector<OrderData> ordenesDataVector = new Vector<OrderData>();
	
	public CompraModel() {
		
		//este combo se debe cargar en el constructor porque si se carga en el showsavemode
		//se lo llama mas de una vez y se resetea la oficina seleccionada.
		cargarComboOficina(null);
		
		panels = MainFrameModel.get_panels();
		iniciarComponentes();
		initKeyListeners();
		setSorterTable(getTblCompraDetalle());
		addPopupMenu();
		cargarCombos();
		initListeners();
		setSorterTable(getTblCompraDetalle());
		getTblCompraDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarMapas();
		this.clean();
		this.showSaveMode();
		new HotKeyComponent(this);
		preseleccionarCompraLocal();
	}
	
	public CompraModel(OrdenCompraIf ordenCompra, long idTareaCompra) {
		
		//este combo se debe cargar en el constructor porque si se carga en el showsavemode
		//se lo llama mas de una vez y se resetea la oficina seleccionada.
		cargarComboOficina(null);
		
		panels = MainFrameModel.get_panels();
		iniciarComponentes();
		initKeyListeners();
		setSorterTable(getTblCompraDetalle());
		addPopupMenu();
		cargarCombos();
		initListeners();
		setSorterTable(getTblCompraDetalle());
		getTblCompraDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarMapas();
		this.clean();
		this.showSaveMode();
		setDatosCompraDesdeOrdenCompra(ordenCompra);
		agregarCompraDetalleDesdeOrdenCompra(ordenCompra);
		new HotKeyComponent(this);
		this.idTareaCompra = idTareaCompra;
		preseleccionarCompraLocal();
	}
	
	public CompraModel(Object compraSeleccionada,long idTareaCompra) {
		
		//este combo se debe cargar en el constructor porque si se carga en el showsavemode
		//se lo llama mas de una vez y se resetea la oficina seleccionada.
		cargarComboOficina(null);
		
		panels = MainFrameModel.get_panels();
		iniciarComponentes();
		initKeyListeners();
		setSorterTable(getTblCompraDetalle());
		addPopupMenu();
		cargarCombos();
		initListeners();
		setSorterTable(getTblCompraDetalle());
		getTblCompraDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarMapas();
		this.clean();
		getSelectedObject(compraSeleccionada);
		new HotKeyComponent(this);
		this.idTareaCompra = idTareaCompra;
		preseleccionarCompraLocal();
	}
	
	private void cargarComboOficina(Long idOficina){
		try {
			Long oficinaId = idOficina;
			List<OficinaIf> oficinas = (ArrayList<OficinaIf>)SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbOficina(),oficinas);
			
			//si oficinaId es null, seteo por default la oficina del usuario.
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
			
			getCmbOficina().setSelectedIndex(indice);
			getCmbOficina().repaint();
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void iniciarComponentes(){
		getLblFechaCaducidad().setVisible(false);
		getCmbFechaCaducidad().setVisible(false);
		getBtnBuscarProveedor().setToolTipText("Buscar proveedor");
		getBtnBuscarProveedor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnManejarOrdenes().setToolTipText("Manejar órdenes");
		getBtnManejarOrdenes().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
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
		getBtnActualizarRetencion().setText("");
		getBtnActualizarRetencion().setToolTipText("Actualizar retención");
		getBtnActualizarRetencion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnAnularRetenciones().setToolTipText("Anular retenciones");
		getBtnAnularRetenciones().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnActivarBusquedaOrdenes().setToolTipText("Desactivar requerimiento de orden de compra");
		getBtnActivarBusquedaOrdenes().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/lightOn.png"));
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblCompraDetalle().getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(8).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(9).setCellRenderer(tableCellRenderer);
		getTblCompraDetalle().getColumnModel().getColumn(10).setCellRenderer(tableCellRenderer);
		
		getTblRetenciones().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		getTblRetenciones().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblRetenciones().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		getTblRetenciones().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		
		
		//--- TABLA GASTO ---
		NumberCellRenderer ncr = new NumberCellRenderer("######0.00",NumberCellRenderer.DERECHA);
		getTblGasto().getColumnModel().getColumn(COLUMNA_GASTO_VALOR).setCellRenderer(ncr);
		getTblGasto().getColumnModel().getColumn(COLUMNA_GASTO_VALOR).setCellEditor(tfce);
		
		getTblRetenciones().getTableHeader().setReorderingAllowed(false);
		TableColumn anchoColumna = getTblRetenciones().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblRetenciones().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblRetenciones().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblRetenciones().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblRetenciones().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblRetenciones().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblRetenciones().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblRetenciones().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(95);
		
		anchoColumna = getTblGasto().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(270);
		anchoColumna = getTblGasto().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblGasto().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblGasto().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblGasto().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(100);
		
		getCmbFecha().setLocale(Utilitarios.esLocale);
		getCmbFechaVencimiento().setLocale(Utilitarios.esLocale);
		getCmbFechaCaducidad().setLocale(Utilitarios.esLocale);
		getCmbFecha().setEditable(false);
		getCmbFechaVencimiento().setEditable(false);
		getCmbFechaCaducidad().setEditable(false);
		getCmbFecha().setShowNoneButton(false);
		getCmbFechaVencimiento().setShowNoneButton(false);
		getCmbFechaCaducidad().setShowNoneButton(false);
		getCmbFechaEmision().setLocale(Utilitarios.esLocale);
		getCmbFechaEmision().setEditable(false);
		getCmbFechaEmision().setShowNoneButton(false);
		getCmbFechaEmision().setFormat(Utilitarios.setFechaUppercase());
	}
	
	private void initKeyListeners() {
		
		//Calculo y seteo la maxima longitud del preimpreso segun lo fijado en Parametros Empresa
		int maxlongPreimpEstablecimiento = Parametros.getMaximaLongitudPreimpresoEstablecimiento();
		int maxlongPreimpPuntoEmision = Parametros.getMaximaLongitudPreimpresoPuntoEmision();
		int maxlongPreimpSecuencial = Parametros.getMaximaLongitudPreimpresoSecuencial();
		int guionesSeparadores = 2;
		int longitudPreimpreso = maxlongPreimpEstablecimiento + maxlongPreimpPuntoEmision + maxlongPreimpSecuencial + guionesSeparadores;
		getTxtPreimpreso().addKeyListener(new TextChecker(longitudPreimpreso));
		
		//Seteo la maxima longitud de la autorizacion segun lo fijado en Parametros Empresa
		getTxtAutorizacion().addKeyListener(new TextChecker(Parametros.getMaximaLongitudPreimpresoAutorizacion()));
			
		getTxtObservacion().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACION));
		getTxtReferencia().addKeyListener(new TextChecker(MAX_LONGITUD_REFERENCIA));
		getTxtAutorizacion().addKeyListener(new NumberTextField());
		getTxtProducto().addKeyListener(new TextChecker(MAX_LONGITUD_PRODUCTO));
		getTxtCantidad().addKeyListener(new TextChecker(MAX_LONGITUD_CANTIDAD));
		getTxtCantidad().addKeyListener(new NumberTextField());
		getTxtValor().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtValor().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeDescuentoEspecial().addKeyListener(new TextChecker(MAX_LONGITUD_DESCUENTO));
		getTxtPorcentajeDescuentoEspecial().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeDescuentoAgencia().addKeyListener(new TextChecker(MAX_LONGITUD_DESCUENTO));
		getTxtPorcentajeDescuentoAgencia().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeDescuentosVarios().addKeyListener(new TextChecker(MAX_LONGITUD_DESCUENTO));
		getTxtPorcentajeDescuentosVarios().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeOtroImpuesto().addKeyListener(new TextChecker(MAX_LONGITUD_OTRO_IMPUESTO));
		getTxtPorcentajeOtroImpuesto().addKeyListener(new NumberTextField());
		getTxtDescripcion().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION, true, 0));
	}
	
	private void addPopupMenu() {
		// agregando items de elimar al popupmenu
		menuItem = new JMenuItem("<html><font color=red>Eliminar detalle</font></html>");
		menuItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				eliminarCompraDetalle();
			}
		});
		popup.add(menuItem);
		
		// agregando el popupmenu a label y su escuchador de ratón
		getTblCompraDetalle().add(popup);
		getTblCompraDetalle().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON3)
					if (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE)
						popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private void cargarCombos() {
		// Combos estáticos
		getCmbEstado().addItem(CompraModel.NOMBRE_ESTADO_ACTIVA);
		getCmbEstado().addItem(CompraModel.NOMBRE_ESTADO_INACTIVA);
		getCmbEstado().addItem(CompraModel.NOMBRE_ESTADO_NORMAL_REEMBOLSO);
		getCmbEstado().addItem(CompraModel.NOMBRE_ESTADO_REEMBOLSO_NORMAL);
		getCmbEstado().addItem("L");
		getCmbTipoCompra().addItem(CompraModel.NOMBRE_TIPO_COMPRA_LOCAL);
		getCmbTipoCompra().addItem(CompraModel.NOMBRE_TIPO_COMPRA_IMPORTACION);
		// DateCombos
		getCmbFecha().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaVencimiento().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaCaducidad().setFormat(Utilitarios.setFechaUppercase());
		if (getMode() == SpiritMode.FIND_MODE) {
			getCmbFecha().setSelectedItem(null);
			getCmbFechaVencimiento().setSelectedItem(null);
			getCmbFechaCaducidad().setSelectedItem(null);
		}
		
		cargarComboTipoDocumento();
		cargarComboMonedas();
		cargarComboTipoSustentoTributario();
	}
	
	private void cargarComboTipoDocumento() {
		Long idModulo;
		Collection collection = null;
		try {
			idModulo = ((ModuloIf) SessionServiceLocator.getModuloSessionService().findModuloByNombre("COMPRAS").iterator().next()).getId();
			Map parameterMap = new HashMap();
			parameterMap.put("moduloId", idModulo);
			parameterMap.put("empresaId", Long.valueOf(Parametros.getIdEmpresa()));
			List tiposDocumentos = (List)SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQueryAndUsuarioId(parameterMap, ((UsuarioIf)Parametros.getUsuarioIf()).getId()); 
			refreshCombo(getCmbTipoDocumento(), tiposDocumentos);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void preseleccionarCompraLocal() {
		for (int i=0; i<getCmbTipoDocumento().getItemCount(); i++) {
			TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) getCmbTipoDocumento().getItemAt(i);
			if (tipoDocumento.getCodigo().equals("COM")) {
				getCmbTipoDocumento().setSelectedIndex(i);
				getCmbTipoDocumento().validate();
				getCmbTipoDocumento().repaint();
				//this.tipoDocumentoIf = (TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem();
				break;
			}
		}
	}
	
	private void cargarComboMonedas() {
		try {
			List monedas = (List)SessionServiceLocator.getMonedaSessionService().getMonedaList();
			refreshCombo(getCmbMoneda(),monedas);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboTipoSustentoTributario() {
		try {
			List sustentosTributarios = (List) SessionServiceLocator.getSriSustentoTributarioSessionService().getSriSustentoTributarioList();
			refreshCombo(getCmbTipoSustentoTributario(),sustentosTributarios);
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private Comparator<SriAirIf> sriAirCodigoComparador = new Comparator<SriAirIf>(){
		public int compare(SriAirIf o1, SriAirIf o2) {
			return o1.getCodigo().compareTo(o2.getCodigo());
		}
	};
	
	private Comparator<SriIvaRetencionIf> sriIvaRetencionCodigoComparador = new Comparator<SriIvaRetencionIf>(){
		public int compare(SriIvaRetencionIf o1, SriIvaRetencionIf o2) {
			return o1.getCodigo().compareTo(o2.getCodigo());
		}
	};
	
	/*private void cargarComboRetencion() {
		try {
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			GenericoIf generico = getGenericoSessionService().getGenerico(productoIf.getGenericoId());
			
			if (generico.getServicio().equals("S"))
				parameterMap.put("bienServicio", "S");
			else if (generico.getServicio().equals("N"))
				parameterMap.put("bienServicio", "B"); 
			
			ClienteOficinaIf proveedorOficina = getClienteOficinaSessionService().getClienteOficina(productoIf.getProveedorId());
			ClienteIf proveedor = getClienteSessionService().getCliente(proveedorOficina.getClienteId());
			parameterMap.put("tipoPersona", proveedor.getTipoPersona());
			parameterMap.put("llevaContabilidad", proveedor.getLlevaContabilidad());
			parameterMap.put("estado", "A");
			java.sql.Date fechaCompra = new java.sql.Date(getCmbFecha().getDate().getTime());
			Iterator<SriProveedorRetencionIf> sriProveedorRetencionIt = getSriProveedorRetencionSessionService().findSriProveedorRetencionByQueryAndFecha(parameterMap, fechaCompra).iterator();
			List<SriAirIf> retencionesRenta = new ArrayList<SriAirIf>();
			List<SriIvaRetencionIf> retencionesIva = new ArrayList<SriIvaRetencionIf>();
			while (sriProveedorRetencionIt.hasNext()) {
				boolean itemExiste = false;
				SriProveedorRetencionIf spr =  sriProveedorRetencionIt.next();
				parameterMap.clear();
				parameterMap.put("porcentaje",spr.getRetefuente());
				Iterator<SriAirIf> retencionesRentaIt = getSriAirSessionService().findSriAirByQueryAndFecha(parameterMap, fechaCompra).iterator();
				while (retencionesRentaIt.hasNext()) {
					SriAirIf sriAir =  retencionesRentaIt.next();
					itemExiste = retencionAgregada(retencionesRenta, "RENTA", sriAir.getId());
					if (!itemExiste)
						retencionesRenta.add(sriAir);
				}
				parameterMap.clear();
				parameterMap.put("porcentaje",spr.getReteiva());
				Iterator<SriIvaRetencionIf> retencionesIvaIt = getSriIvaRetencionSessionService().findSriIvaRetencionByQueryAndFecha(parameterMap, fechaCompra).iterator();
				itemExiste = false;
				while (retencionesIvaIt.hasNext()) {
					SriIvaRetencionIf sriIvaRetencion = retencionesIvaIt.next();
					itemExiste = retencionAgregada(retencionesIva, "IVA", sriIvaRetencion.getId());
					if (!itemExiste)
						retencionesIva.add(sriIvaRetencion);
				}
			}
			parameterMap.clear();
			parameterMap.put("porcentaje", BigDecimal.ZERO);
			Iterator<SriAirIf> retencionesRentaIt = getSriAirSessionService().findSriAirByQueryAndFecha(parameterMap, fechaCompra).iterator();
			while (retencionesRentaIt.hasNext()) {
				SriAirIf sriAir =  retencionesRentaIt.next();
				retencionesRenta.add(sriAir);
			}
			Collections.sort(retencionesRenta,sriAirCodigoComparador);
			refreshCombo(getCmbRetencionRenta(), retencionesRenta);
			Collections.sort(retencionesIva,sriIvaRetencionCodigoComparador);
			refreshCombo(getCmbRetencionIva(), retencionesIva);
			if (getCmbRetencionIva().getItemCount() <= 0)
				getCmbRetencionIva().setEnabled(false);
			else
				getCmbRetencionIva().setEnabled(true);
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}*/
	
	private void cargarComboRetencion() {
		try {
			java.sql.Date fechaCompra = new java.sql.Date(getCmbFecha().getDate().getTime());
			List<SriAirIf> retencionesRenta = new ArrayList<SriAirIf>();
			List<SriIvaRetencionIf> retencionesIva = new ArrayList<SriIvaRetencionIf>();
			Iterator<SriAirIf> retencionesRentaIt = SessionServiceLocator.getSriAirSessionService().findSriAirByFecha(fechaCompra).iterator();
			while (retencionesRentaIt.hasNext()) {
				SriAirIf sriAir =  retencionesRentaIt.next();
				retencionesRenta.add(sriAir);
			}

			Iterator<SriIvaRetencionIf> retencionesIvaIt = SessionServiceLocator.getSriIvaRetencionSessionService().findSriIvaRetencionByFecha(fechaCompra).iterator();
			while (retencionesIvaIt.hasNext()) {
				SriIvaRetencionIf sriIvaRetencion = retencionesIvaIt.next();
				retencionesIva.add(sriIvaRetencion);
			}
			Collections.sort(retencionesRenta,sriAirCodigoComparador);
			refreshCombo(getCmbRetencionRenta(), retencionesRenta);
			Collections.sort(retencionesIva,sriIvaRetencionCodigoComparador);
			refreshCombo(getCmbRetencionIva(), retencionesIva);
			if (getCmbRetencionIva().getItemCount() <= 0)
				getCmbRetencionIva().setEnabled(false);
			else
				getCmbRetencionIva().setEnabled(true);
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private boolean retencionAgregada(List retencionesList, String tipo, Long itemId) {
		boolean agregada = false;
		Iterator it = retencionesList.iterator();
		while (it.hasNext()) {
			if (tipo.equals("RENTA")) {
				SriAirIf sriAir = (SriAirIf) it.next();
				if (sriAir.getId().compareTo(itemId) == 0) {
					agregada = true;
					break;
				}
			} else if (tipo.equals("IVA")) {
				SriIvaRetencionIf sriIvaRetencion = (SriIvaRetencionIf) it.next();
				if (sriIvaRetencion.getId().compareTo(itemId) == 0) {
					agregada = true;
					break;
				}
			}
		}
		
		return agregada;
	}
	
	private void initListeners() {
		getCmbFecha().addActionListener(oActionListenerCmbFechaEmision);
		
		getBtnActivarBusquedaOrdenes().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				ordenRequerida = !ordenRequerida;
				manejarBotonActivar();
				getBtnActivarBusquedaOrdenes().validate();
				getBtnActivarBusquedaOrdenes().repaint();
				setProveedor(null);
			}
		});
		
		getBtnBuscarProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarProveedor();
			}
		});
		
		getBtnBuscarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarProducto(JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, false, null, 0);
				if (productoIf != null)
					cargarComboRetencion();
			}
		});
		
		getBtnAgregarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarCompraDetalle();
			}
		});
		
		getBtnActualizarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarCompraDetalle();
			}
		});
		
		getBtnEliminarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarCompraDetalle();
			}
		});
		
		getCmbTipoDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				TipoDocumentoIf td = (TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem();
				if ( td!= null) {
					boolean cambiarDocumento = verificarEliminacionGastosOComprasAsociadasIngresados(
						true,true,null,td,compraGastoClase,MAPA_COMPRA_GASTO); 
					if ( cambiarDocumento ){
						cambiarDocumento = verificarEliminacionGastosOComprasAsociadasIngresados(
								true,true,null,td,compraGastoClase,MAPA_COMPRA_ASOCIADA_GASTO);
						if ( cambiarDocumento ) 
							llenarCmbTipoDocumento();
						else {
							getCmbTipoDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoDocumento(), tipoDocumentoIf.getId()));
							getCmbTipoDocumento().repaint();
						}
					} else {
						getCmbTipoDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoDocumento(), tipoDocumentoIf.getId()));
						getCmbTipoDocumento().repaint();
					}
				}
			}			
		});
		
		getCmbDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbDocumento().getSelectedItem() != null) {
					documentoIf = (DocumentoIf) getCmbDocumento().getSelectedItem();
				}
			}
		});
		
		getTblCompraDetalle().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				selectRow();
			}
		});
		
		getTblCompraDetalle().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				selectRow();
			}
		});
			
		getBtnManejarOrdenes().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				manejarOrdenes();
			}
		});
		
		getBtnActualizarRetencion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarRetencion();
			}
		});
				
		getTblRetenciones().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				enableCompraRetencionForUpdate();
			}
		});
		
		getTblRetenciones().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				enableCompraRetencionForUpdate();
			}
		});
		
		getBtnVerificarPreimpreso().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				verificarPreimpreso();
			}			
		});
		
		getBtnAnularRetenciones().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anularRetenciones();
			}
		});
		
		getTblGasto().addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent arg0) {
				int filaSeleccionada = getTblGasto().getSelectedRow();
				if (filaSeleccionada >= 0){
					filaSeleccionada = getTblGasto().convertRowIndexToModel(filaSeleccionada);
					String nombreGasto = (String)getTblGasto().getValueAt(filaSeleccionada, COLUMNA_GASTO_NOMBRE_GASTO);
					GastoIf gasto = mapaGastosPorNombre.get(nombreGasto);
					int columnaSeleccionada = getTblGasto().getSelectedColumn();
					if ( columnaSeleccionada == COLUMNA_GASTO_DETALLE ){
						Double valor = (Double) getTblGasto().getValueAt(filaSeleccionada, COLUMNA_GASTO_VALOR);
						presentarVentanaDetalleGasto(gasto,valor);
					} else if ( columnaSeleccionada == COLUMNA_GASTO_COMPRA_ASOCIADA ){
						presentarVentanaCompraAsociada(gasto);
					}
				}
			}
		});
		
		getBtnGuardarGasto().addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent arg0) {
				try {
					if ( getMode() == SpiritMode.UPDATE_MODE ){
						compraGastoClase = SessionServiceLocator.getCompraGastoSessionService().procesarCompraGasto(compraGastoClase);
						cargarTablaGasto();
						SpiritAlert.createAlert("Gastos guardados con éxito !!", SpiritAlert.INFORMATION);
					} else {
						SpiritAlert.createAlert("Gastos solo pueden ser guardados en Actualizacion !!", SpiritAlert.WARNING);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				} catch (Exception e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Error general al guardar Gastos !!", SpiritAlert.ERROR);
				}
			}
		});
		
		getBtnGuardar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					CarteraIf carteraRetencion = SessionServiceLocator.getCompraSessionService().procesarDatosRetenciones(compra, compraRetencionColeccion);
					SpiritAlert.createAlert("Información guardada con éxito", SpiritAlert.INFORMATION);
					SpiritAlert.createAlert("A continuación se abrirá el asiento correspondiente\npara modificar la glosa del mismo", SpiritAlert.INFORMATION);
					Map parameterMap = new HashMap();
					parameterMap.put("tipoDocumentoId", carteraRetencion.getTipodocumentoId());
					parameterMap.put("transaccionId", carteraRetencion.getId());
					Iterator it = SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(parameterMap).iterator();
					if (it.hasNext()) {
						AsientoIf asiento = (AsientoIf) it.next();
						editarAsientoContable(asiento);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error al guardar los datos", SpiritAlert.ERROR);
				}
			}
		});
		
		getTxtProducto().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				try {
					if (e.getKeyChar() == KeyEvent.VK_ENTER) {
						Map parameterMap = new HashMap();
						String strProducto = getTxtProducto().getText().trim() + "%";
						parameterMap.put("strCriterio", strProducto);
						parameterMap.put("estado", "A");
						parameterMap.put("proveedorId", proveedorIf.getId());

						Collection productoCollection = SessionServiceLocator.getProductoSessionService().findProductoByCriterioMap(parameterMap); 
						int tamanoLista = productoCollection.size();
						ProductoIf producto = null;
						if (tamanoLista == 1) {
							Iterator productoIterator = productoCollection.iterator();
							if (productoIterator.hasNext()) {
								productoIf = (ProductoIf) productoIterator.next();
								genericoIf = (GenericoIf) genericosMap.get(productoIf.getGenericoId());
								setProductoDetalle(productoIf);
							}
						} else if (tamanoLista > 1) {
							buscarProducto(JDPopupFinderModel.BUSQUEDA_TODOS, true, parameterMap, tamanoLista);
						} else if (tamanoLista < 1) {
							SpiritAlert.createAlert("No se ha encontrado el producto", SpiritAlert.INFORMATION);
						}
						
						if (productoIf != null)
							cargarComboRetencion();
					}
				} catch (GenericBusinessException ex) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					ex.printStackTrace();
				}
			}
		});	
	}
	
	ActionListener oActionListenerCmbFechaEmision = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			java.util.Date fechaEmision = getCmbFecha().getDate();
			if ( fechaEmision != null ){
				java.util.Date fechaVencimiento = new Date(fechaEmision.getYear(), fechaEmision.getMonth(), fechaEmision.getDate());   
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(fechaVencimiento);
				getCmbFechaVencimiento().setCalendar(calendar);
				getCmbFechaVencimiento().repaint();
			}
		}
	};
	
	private void editarAsientoContable(AsientoIf asiento) {
		SpiritModel panelAsiento = (SpiritModel) new AsientoModel(asiento);
		
		if (panels.size()>0 && panels.get("Asiento")!=null){
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea cerrar la ventana Asiento?, se perderá la información que no haya sido guardada", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				MainFrameModel.get_dockingManager().removeFrame("Asiento");
			}
		}
		
		PanelHandler.showPanelModel(panelAsiento);
	}

	private void manejarBotonActivar() {
		//MODIFIED 19 JULIO
		//if (ordenCompraRequerida || ordenMedioRequerida) {
		if (ordenRequerida) {
			//MODIFIED 19 JULIO
			//getBtnActivar().setToolTipText("Desactivar requerimiento de orden de compra u orden de medio");
			getBtnActivarBusquedaOrdenes().setToolTipText("Desactivar requerimiento de orden");
			getBtnActivarBusquedaOrdenes().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/lightOn.png"));
		} else {
			//MODIFIED 19 JULIO
			//getBtnActivar().setToolTipText("Activar requerimiento de orden de compra");
			getBtnActivarBusquedaOrdenes().setToolTipText("Activar requerimiento de orden");
			getBtnActivarBusquedaOrdenes().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/lightOff.png"));
		}
	}
	
	private void presentarVentanaDetalleGasto(GastoIf gasto,Double valor) {
		if (valor > 0D){
			CompraGastoIf cg = compraGastoClase.getMapaCompraGasto(gasto.getId());
			cg.setValor(new BigDecimal(valor));
			Map<Long, CompraDetalleGastoClase> mapaCompraDetalleGasto = compraGastoClase.getMapaCompraDetalleGasto();
			cdgm = new CompraDetalleGastoModel(
					Parametros.getMainFrame(),gasto,cg,mapaCompraDetalleGasto,
					compraDetalleColeccion,mapaProductoNombre,
					genericosMap, productosMap,presentacionesMap, lineasMap,
					modelosMap,coloresMap);
			cdgm.addWindowListener(new WindowAdapter(){
				public void windowClosed(WindowEvent e) {
					cdgm = null;
					System.gc();
				}		
			});
		} else {
			SpiritAlert.createAlert("El valor del gasto debe ser mayor que CERO para poder ingresar detalle !!", SpiritAlert.WARNING);
		}
	}
	
	private void presentarVentanaCompraAsociada(GastoIf gasto){
		
		//if ( getMode() == SpiritMode.UPDATE_MODE ){
			CompraGastoIf cg = compraGastoClase.getMapaComprasGastos().get(gasto.getId());
			CompraAsociadaGastoModel cagm = new CompraAsociadaGastoModel(
				Parametros.getMainFrame(),compraGastoClase,gasto,cg);
		//}
	}
	
	private void llenarCmbTipoDocumento() {
		tipoDocumentoIf = (TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem();
		
		if (tipoDocumentoIf != null) {
			try {
				getCmbDocumento().removeAllItems();
				llenarCmbDocumentoDesdeTipoDocumento();
				cargarTablaGastosDesdeTipoDocumento(true);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}

	private void llenarCmbDocumentoDesdeTipoDocumento()
			throws GenericBusinessException {
		SpiritComboBoxModel cmbModelDocumento = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getDocumentoSessionService().findDocumentoByTipodocumentoIdAndUsuarioId(tipoDocumentoIf.getId(), ((UsuarioIf) Parametros.getUsuarioIf()).getId()));
		getCmbDocumento().setModel(cmbModelDocumento);
		if (getCmbDocumento().getItemCount() > 0) {
			if (getMode() != SpiritMode.FIND_MODE) {
				getCmbDocumento().setEnabled(true);
				if (getCmbDocumento().getItemCount() > 0){
					Map documentoMap = new HashMap();
					documentoMap.put("tipoDocumentoId",tipoDocumentoIf.getId());
					if (tipoDocumentoIf.getCodigo().equals("COM"))
						documentoMap.put("codigo", DOCUMENTO_COMPRA_LOCAL);
					if(SessionServiceLocator.getDocumentoSessionService().findDocumentoByQuery(documentoMap).size() > 0){
						DocumentoIf documentoCompraLocal = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByQuery(documentoMap).iterator().next();
						getCmbDocumento().setSelectedItem(documentoCompraLocal);
					}else{
						getCmbDocumento().setSelectedIndex(-1);
					}
					getCmbDocumento().validate();
					getCmbDocumento().repaint();
				}
			}
		}
	}

	private void cargarTablaGastosDesdeTipoDocumento(boolean eliminarComprasAsociadas) throws GenericBusinessException{
		limpiarMapaCompraGasto(false,eliminarComprasAsociadas);
		if ( tipoDocumentoIf != null ){
			String codigoTipoDocumento = tipoDocumentoIf.getCodigo();
			Map<String, Object> mapa = null;
			Collection<GastoIf> gastos = null;
			if ( codigoTipoDocumento.equals("COM") || codigoTipoDocumento.equals("LIC") 
					|| codigoTipoDocumento.equals("COR") || codigoTipoDocumento.equals("CNV") || codigoTipoDocumento.equals("SAE")){
	
				mapa =  new HashMap<String, Object>();
				mapa.put("empresaId", Parametros.getIdEmpresa());
				mapa.put("tipo", TipoGasto.LOCAL.getLetra());
				gastos = SessionServiceLocator.getGastoSessionService().findGastoByQuery(mapa);
				for ( GastoIf g : gastos ){
					
					if ( !compraGastoClase.getMapaComprasGastos().containsKey(g.getId()) ){
						CompraGastoIf cgd = new CompraGastoData();
						cgd.setGastoId(g.getId());
						cgd.setValor(BigDecimal.ZERO);
						compraGastoClase.getMapaComprasGastos().put(g.getId(), cgd);
						CompraDetalleGastoClase cdgc = compraGastoClase.getMapaCompraDetalleGasto().get(g.getId());
						if ( cdgc != null ){
							cdgc.guardarDetalleGastoDesdeDetalleCompra(compraDetalleColeccion);
						} 
						else {
							cdgc = new CompraDetalleGastoClase(null);
							cdgc.guardarDetalleGastoDesdeDetalleCompra(compraDetalleColeccion);
							compraGastoClase.getMapaCompraDetalleGasto().put(g.getId(),cdgc);
						}
						CompraAsociadaGastoClase cagc = compraGastoClase.getMapaComprasAsociadas().get(g.getId());
						if ( cagc == null ){
							cagc = new CompraAsociadaGastoClase();
							compraGastoClase.getMapaComprasAsociadas().put(g.getId(), cagc);
						}
					}
				}
	
			} else if ( codigoTipoDocumento.equals("COI") ){
	
				mapa =  new HashMap<String, Object>();
				mapa.put("empresaId", Parametros.getIdEmpresa());
				mapa.put("tipo", TipoGasto.IMPORTADO.getLetra());
				gastos = SessionServiceLocator.getGastoSessionService().findGastoByQuery(mapa);
				for ( GastoIf g : gastos ){
					if ( !compraGastoClase.getMapaComprasGastos().containsKey(g.getId()) ){
						CompraGastoIf cgd = new CompraGastoData();
						cgd.setGastoId(g.getId());
						cgd.setValor(BigDecimal.ZERO);
						compraGastoClase.getMapaComprasGastos().put(g.getId(), cgd);
						CompraDetalleGastoClase cdgc = compraGastoClase.getMapaCompraDetalleGasto().get(g.getId());
						if ( cdgc != null ){
							cdgc.guardarDetalleGastoDesdeDetalleCompra(compraDetalleColeccion);
						} else {
							cdgc = new CompraDetalleGastoClase(null);
							cdgc.guardarDetalleGastoDesdeDetalleCompra(compraDetalleColeccion);
							compraGastoClase.getMapaCompraDetalleGasto().put(g.getId(),cdgc);
						}
						CompraAsociadaGastoClase cagc = compraGastoClase.getMapaComprasAsociadas().get(g.getId());
						if ( cagc == null ){
							cagc = new CompraAsociadaGastoClase();
							compraGastoClase.getMapaComprasAsociadas().put(g.getId(), cagc);
						}
					}
				}
	
			} else {
			}
	
			mapa =  new HashMap<String, Object>();
			mapa.put("empresaId", Parametros.getIdEmpresa());
			mapa.put("tipo", TipoGasto.AMBOS.getLetra());
			gastos = SessionServiceLocator.getGastoSessionService().findGastoByQuery(mapa);
			for ( GastoIf g : gastos ){
				if ( !compraGastoClase.getMapaComprasGastos().containsKey(g.getId()) ){
					CompraGastoData cgd = new CompraGastoData();
					cgd.setGastoId(g.getId());
					cgd.setValor(BigDecimal.ZERO);
					compraGastoClase.getMapaComprasGastos().put(g.getId(), cgd);
					CompraDetalleGastoClase cdgc = compraGastoClase.getMapaCompraDetalleGasto().get(g.getId());
					if ( cdgc != null ){
						cdgc.guardarDetalleGastoDesdeDetalleCompra(compraDetalleColeccion);
					} else {
						cdgc = new CompraDetalleGastoClase(null);
						cdgc.guardarDetalleGastoDesdeDetalleCompra(compraDetalleColeccion);
						compraGastoClase.getMapaCompraDetalleGasto().put(g.getId(),cdgc);
					}
					CompraAsociadaGastoClase cagc = compraGastoClase.getMapaComprasAsociadas().get(g.getId());
					if ( cagc == null ){
						cagc = new CompraAsociadaGastoClase();
						compraGastoClase.getMapaComprasAsociadas().put(g.getId(), cagc);
					}
				}
			}
		}
		cargarTablaGasto();
	}
	
	//Saca del mapa los gastos que hayan sido agregados que no
	//pertenecen al tipo de documento seleccionado actualmente.
	private void limpiarMapaCompraGasto(boolean preguntar,boolean establecerEliminarComprasAsociadas){
		if ( tipoDocumentoIf != null ){
			
			boolean eliminarComprasAsociadas = false;
			ArrayList<Long> listaEliminar = new ArrayList<Long>();
			eliminarComprasAsociadas = verificarEliminacionGastosOComprasAsociadasIngresados(preguntar,
					establecerEliminarComprasAsociadas,listaEliminar,tipoDocumentoIf,
					compraGastoClase,MAPA_COMPRA_ASOCIADA_GASTO);
			if (eliminarComprasAsociadas){
				Map<Long, CompraAsociadaGastoClase> mapaCompraAsociada = compraGastoClase.getMapaComprasAsociadas();
				for ( Long gastoId : listaEliminar ) {
					ArrayList<CompraAsociadaGastoIf> comprasAsociadas = mapaCompraAsociada.get(gastoId).getDetalle();
					Iterator<CompraAsociadaGastoIf> itComprasAsociadas = comprasAsociadas.iterator();
					while ( itComprasAsociadas.hasNext() ){
						CompraAsociadaGastoIf cag = itComprasAsociadas.next();
						if ( cag.getId() != null ){
							compraGastoClase.getListaEliminacionComprassAsociadas().add(cag);
						}
						itComprasAsociadas.remove();
					}
					mapaCompraAsociada.remove(gastoId);
				}
			}
			
			boolean eliminarGastos = false;
			listaEliminar = new ArrayList<Long>();
			eliminarGastos = verificarEliminacionGastosOComprasAsociadasIngresados(preguntar,
					establecerEliminarComprasAsociadas,listaEliminar,tipoDocumentoIf,
					compraGastoClase,MAPA_COMPRA_GASTO);
			if (eliminarGastos){
				Map<Long, CompraGastoIf> mapaCompraGasto = compraGastoClase.getMapaComprasGastos();
				for ( Long gastoId : listaEliminar ) {
					CompraGastoIf cg = mapaCompraGasto.remove(gastoId);
					if ( cg.getId() != null )
						compraGastoClase.getListaEliminacionComprasGastos().add(cg);
					mapaCompraGasto.get(gastoId);
				}
			}
			
			
		}
	}

	private boolean verificarEliminacionGastosOComprasAsociadasIngresados(boolean preguntar,boolean eliminarComprasAsociadas,
			ArrayList<Long> listaEliminar, TipoDocumentoIf tipoDocumentoIf,
			CompraGastoClase cgc,int tipoMapaGasto ) {
		boolean eliminar = true;
		if ( tipoMapaGasto == MAPA_COMPRA_ASOCIADA_GASTO )
			eliminar = eliminarComprasAsociadas;
		String codigoTipoDocumento = tipoDocumentoIf.getCodigo();
		if ( codigoTipoDocumento.equals("COM") || codigoTipoDocumento.equals("LIC") 
				|| codigoTipoDocumento.equals("COR") || codigoTipoDocumento.equals("CNV") || codigoTipoDocumento.equals("SAE")){
			eliminar = procedimientoVerificacionEliminacion(
				preguntar, listaEliminar, tipoMapaGasto, eliminar, TipoGasto.IMPORTADO.getLetra());
			
		} else if ( codigoTipoDocumento.equals("COI") ){
			eliminar = procedimientoVerificacionEliminacion(preguntar,
				listaEliminar, tipoMapaGasto, eliminar,TipoGasto.LOCAL.getLetra());
		}
		return eliminar;
	}

	private boolean procedimientoVerificacionEliminacion(boolean preguntar,
			ArrayList<Long> listaEliminar, int tipoMapaGasto, boolean eliminar,String letraTipoGasto) {
		if ( tipoMapaGasto == MAPA_COMPRA_GASTO ){
			Map<Long, CompraGastoIf> mapaCompraGasto = compraGastoClase.getMapaComprasGastos();
			for ( Long gastoId : mapaCompraGasto.keySet() ){
				GastoIf g = mapaGastosPorId.get(gastoId);
				CompraGastoIf cg = mapaCompraGasto.get(gastoId);
				if ( g.getTipo().equals(letraTipoGasto) ){
					if ( preguntar && cg.getValor().doubleValue() > 0.0  ){
						if ( confimarCambioDocumentoDesdeGasto("los gastos") ){
							preguntar = false;
							eliminar = true;
						} else {
							eliminar = false;
							if ( listaEliminar != null )
								listaEliminar.clear();
							break;
						}
					}
					if ( listaEliminar != null )
						listaEliminar.add(g.getId());
				}
			}
		} else if ( tipoMapaGasto == MAPA_COMPRA_ASOCIADA_GASTO ) {
			Map<Long, CompraAsociadaGastoClase> mapaCompraAsociada = compraGastoClase.getMapaComprasAsociadas();
			for ( Long gastoId : mapaCompraAsociada.keySet() ){
				CompraAsociadaGastoClase cagc =  mapaCompraAsociada.get(gastoId);
				if ( preguntar && cagc.getDetalle().size() > 0 ){
					if (confimarCambioDocumentoDesdeGasto("las compras asociadas ingresadas")){
						preguntar = false;
						eliminar = true;
					} else {
						eliminar = false;
						if ( listaEliminar != null )
						listaEliminar.clear();
						break;
					}
				}
				if ( eliminar && listaEliminar != null )
					listaEliminar.add(gastoId);
			}
		}
		return eliminar;
	}
	
	private boolean confimarCambioDocumentoDesdeGasto(String elementosAeliminar){
		Object[] options = {"Si","No"}; 
		int opcion = JOptionPane.showOptionDialog(
				null,"Está realizando cambio de Tipo de Documento, "+elementosAeliminar+" que no esten relacionados" +
						"\n con el Tipo de documento serán eliminados." +
						"\n¿Desea continuar? ", "Confirmación"
				,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE
				,null,options,"No");
		if ( opcion == JOptionPane.YES_OPTION )
			return true;
		return false;
	}
	
	private void cargarGastos() {
		mapaGastosPorId = null;
		mapaGastosPorId = new HashMap<Long, GastoIf>();
		mapaGastosPorNombre = null;
		mapaGastosPorNombre = new HashMap<String, GastoIf>();
		try {
			Collection<GastoIf> gastos = SessionServiceLocator.getGastoSessionService().findGastoByEmpresaId(Parametros.getIdEmpresa());
			for (GastoIf g : gastos){
				mapaGastosPorId.put(g.getId(), g);
				mapaGastosPorNombre.put(g.getNombre(), g);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar Gastos !!", SpiritAlert.ERROR);
		}
		tfce.setMapaGastoPorNombre(mapaGastosPorNombre);
	}
	
	private void cargarTablaGasto(){
		limpiarTabla(getTblGasto());
		cargarGastos();
		DefaultTableModel modelo = (DefaultTableModel)getTblGasto().getModel();
		Map<Long, CompraGastoIf> mapaCompraGasto = compraGastoClase.getMapaComprasGastos();
		for (Long gastoId : mapaCompraGasto.keySet()){
			CompraGastoIf cg = mapaCompraGasto.get(gastoId);
			Vector fila = new Vector();
			GastoIf gasto = mapaGastosPorId.get(cg.getGastoId());
			String nombreGasto = gasto.getNombre();
			fila.add(nombreGasto);
			fila.add(TipoGasto.getTipoGastoByLetra(gasto.getTipo()));
			fila.add(cg.getValor().doubleValue());
			fila.add("");
			modelo.addRow(fila);
		}
	}
	
	private void cleanTablaGasto(){
		limpiarTabla(getTblGasto());
	}
	
	private void verificarPreimpreso() {
		String preimpreso = getTxtPreimpreso().getText();
		String autorizacion = getTxtAutorizacion().getText();
		if ( preimpreso!= null && !preimpreso.equals("") ){
			if ( proveedorIf == null ){
				SpiritAlert.createAlert("Debe elegir un proveedor primero !!",SpiritAlert.INFORMATION);
				return;
			}
			if ( autorizacion == null || autorizacion.equals("") ){
				SpiritAlert.createAlert("Debe ingresar la autorización primero !!",SpiritAlert.INFORMATION);
				return;
			}
			if ( validatePreimpresoValido() ){
				try {
					tipoDocumentoIf = (TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem();					
					if (tipoDocumentoIf != null) {					

						boolean existePreimpreso = SessionServiceLocator.getCompraSessionService().verificarPreimpreso(proveedorIf.getId(),preimpreso,autorizacion,tipoDocumentoIf.getId());
						if ( existePreimpreso ){
							Collection<CompraIf> compras = SessionServiceLocator.getCompraSessionService().findCompraByPreimpreso(preimpreso);
							CompraIf compra = compras.iterator().next();
							SpiritAlert.createAlert("Preimpreso ya existe en compra con código "+compra.getCodigo()+" !!",SpiritAlert.WARNING);
						} else 
							SpiritAlert.createAlert("El Preimpreso es válido !!",SpiritAlert.INFORMATION);
						
					}
					else{
						SpiritAlert.createAlert("Debe escoger el tipo de documento !!",SpiritAlert.INFORMATION);
					}
					
				} catch (GenericBusinessException e1) {
					e1.printStackTrace();
					SpiritAlert.createAlert(e1.getMessage(),SpiritAlert.ERROR);
				}
			}
		}
	}
	
	private void anularRetenciones() {
		try {
			if (compra != null) {
				if (compraRetencionColeccion.size() > 0) {
					if (confirmarAnularRetencion()) {
						SessionServiceLocator.getCarteraSessionService().procesarAnularRetencion(compra, Parametros.getUsuario());
						cleanRetencion();
						cleanTableRetencion();
						SpiritAlert.createAlert("Retención anulada con éxito !!", SpiritAlert.INFORMATION);
					}
				} else {
					SpiritAlert.createAlert("No hay datos de retención.", SpiritAlert.INFORMATION);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al procesar la anulación de la retención!!!", SpiritAlert.ERROR);
		}
	}
	
	private boolean confirmarAnularRetencion(){
		Object[] options = {"Si","No"}; 
		int opcion = JOptionPane.showOptionDialog(
				null,"¿Está seguro de que desea anular la retención?", "Confirmación"
				,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE
				,null,options,"No");
		return opcion == JOptionPane.YES_OPTION ? true : false;
	}
	
	KeyListener oKeyListenerTxtCodigo = new TextChecker(MAX_LONGITUD_CODIGO);
	
	private void enableCompraRetencionForUpdate() {
		if (getTblRetenciones().getSelectedRow() != -1) {
			filaSeleccionadaRetencion = getTblRetenciones().convertRowIndexToModel(getTblRetenciones().getSelectedRow() );
			compraRetencion = (CompraRetencionIf) compraRetencionColeccion.get(filaSeleccionadaRetencion);
			getTxtEstablecimiento().setText(compraRetencion.getEstablecimiento());
			getTxtPuntoEmision().setText(compraRetencion.getPuntoEmision());
			getTxtSecuencial().setText(compraRetencion.getSecuencial());
			getTxtAutorizacionRetencion().setText(compraRetencion.getAutorizacion());
			getCmbFechaEmision().setDate(compraRetencion.getFechaEmision());
			getCmbFechaEmision().repaint();
		}
	}
	
	private void actualizarRetencion() {
		if((filaSeleccionadaRetencion != -1) && (compraRetencion != null)){
			if (validateFieldsRetenciones()) {
				modelTblRetenciones = (DefaultTableModel) getTblRetenciones().getModel();
				Vector<String> filaRetenciones = new Vector<String>();
				compraRetencion.setEstablecimiento(getTxtEstablecimiento().getText());
				compraRetencion.setPuntoEmision(getTxtPuntoEmision().getText());
				compraRetencion.setSecuencial(getTxtSecuencial().getText());
				compraRetencion.setAutorizacion(getTxtAutorizacionRetencion().getText());
				compraRetencion.setFechaEmision(new java.sql.Date(getCmbFechaEmision().getDate().getTime()));
				compraRetencionColeccion.set(filaSeleccionadaRetencion, compraRetencion);
								
				filaRetenciones = (Vector<String>)modelTblRetenciones.getDataVector().elementAt(filaSeleccionadaRetencion);
				filaRetenciones.set(1, getTxtEstablecimiento().getText() + " - " + getTxtPuntoEmision().getText() + " - " + getTxtSecuencial().getText());
				filaRetenciones.set(2, getTxtAutorizacionRetencion().getText());
				filaRetenciones.set(7, Utilitarios.getFechaUppercase(getCmbFechaEmision().getDate()));
				
				modelTblRetenciones.insertRow(filaSeleccionadaRetencion, filaRetenciones);
				modelTblRetenciones.removeRow(filaSeleccionadaRetencion+1);
				cleanRetencion();
			}
		} else {
			SpiritAlert.createAlert("Primero debe seleccionar una retención para actualizar !", SpiritAlert.WARNING);
		}
	}
	
	public void cleanRetencion(){
		filaSeleccionadaRetencion = -1;
		compraRetencion = null;
		getTxtEstablecimiento().setText("");
		getTxtPuntoEmision().setText("");
		getTxtSecuencial().setText("");
		getTxtAutorizacionRetencion().setText("");
	}
	
	public boolean validateFieldsRetenciones() {
		if (getTxtEstablecimiento().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar el No. de Serie !", SpiritAlert.WARNING);
			getTxtEstablecimiento().grabFocus();
			return false;
		}		
		if (getTxtPuntoEmision().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar el No. de Serie !", SpiritAlert.WARNING);
			getTxtPuntoEmision().grabFocus();
			return false;
		}
		if (getTxtSecuencial().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar el No. Secuencial !", SpiritAlert.WARNING);
			getTxtSecuencial().grabFocus();
			return false;
		}
		if (getTxtAutorizacionRetencion().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar el No. de Autorización !", SpiritAlert.WARNING);
			getTxtAutorizacionRetencion().grabFocus();
			return false;
		}
		if(getCmbFechaEmision().getSelectedItem() == null){
			SpiritAlert.createAlert("Debe seleccionar una Fecha de Emisión !", SpiritAlert.WARNING);
			getCmbFechaEmision().grabFocus();
			return false;
		}
		return true;
	}
	
	private void selectRow() {
		if (getTblCompraDetalle().getSelectedRow() != -1) {
			int filaSeleccionada = getTblCompraDetalle().convertRowIndexToModel(getTblCompraDetalle().getSelectedRow() );
			compraDetalleForUpdate = (CompraDetalleIf) compraDetalleColeccion.get(filaSeleccionada);
			enableCompraDetalleForUpdate(compraDetalleForUpdate);
		}
	}
	
	private void manejarOrdenes() {
		showJDAsociarOrden();
	}
	
	private void showJDAsociarOrden() {
		AsociarOrdenModel aom = new AsociarOrdenModel(Parametros.getMainFrame(), ordenesDataVector, proveedorIf, compra);
		ordenesDataVector = aom.getAssociatedOrdersDataVector();
		if (!aom.isCancelAction())
			buscarReferencia(true);
	}
	
	private void buscarReferencia(boolean eventoEnter) {
		if (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE) {
			if (compraDetalleColeccion.size() != 0) {
				Iterator it = compraDetalleColeccion.iterator();
				while (it.hasNext()) {
					CompraDetalleIf detalleRemovido = (CompraDetalleIf) it.next();
					if (detalleRemovido.getId() != null)
						compraDetalleEliminadas.add(detalleRemovido);
				}
				cleanTableDetalle();
				compraDetalleColeccion.clear();
			}
			
			cleanTableDetalle();
			String observacion = "";
			for (int i=0; i<ordenesDataVector.size(); i++) {
				OrderData orden = ordenesDataVector.get(i);
				if (orden.getOrderType().equals("OC")){
					observacion = buscarObservacionOC(orden.getPurchaseOrder());					
					cargarDetallesOrdenCompra(orden.getPurchaseOrder());					
				}else{
					observacion = buscarObservacionOM(orden.getMediaOrder());					
					cargarDetallesOrdenMedio(orden.getMediaOrder());
					
				}
			}
			getTxtObservacion().setText(observacion);
		}
	}
	
	public String buscarObservacionOC(OrdenCompraIf ordenCompra){
		String observacion = "";
		try {
			Collection presupuestosDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByOrdenCompraId(ordenCompra.getId());
			Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
			while(presupuestosDetalleIt.hasNext()){
				PresupuestoDetalleIf presupuestoDetalle = (PresupuestoDetalleIf) presupuestosDetalleIt.next();
				PresupuestoIf presupuesto = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoDetalle.getPresupuestoId());
				getTxtReferencia().setText("P: " + presupuesto.getCodigo().split("-")[1]);
				ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(presupuesto.getClienteOficinaId());
				ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
				if(observacion.equals("")){
					String ordenCodigo = ordenCompra.getCodigo().split("-")[1];
					observacion = "Orden: " + ordenCodigo + ", Cliente: " + cliente.getNombreLegal();
				}else{
					String ordenCodigo = ordenCompra.getCodigo().split("-")[1];
					observacion = observacion + " - Orden: " + ordenCodigo + ", Cliente: " + cliente.getNombreLegal();
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return observacion;
	}
	
	public String buscarObservacionOM(OrdenMedioIf ordenMedio){
		String observacion = "";
		try {
			PlanMedioIf planMedio = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(ordenMedio.getPlanMedioId());
			getTxtReferencia().setText("P: " + planMedio.getCodigo().split("-")[1]);
			ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenMedio.getClienteOficinaId());
			ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
			if(observacion.equals("")){
				String ordenCodigo = ordenMedio.getCodigo().split("-")[1];
				observacion = "Orden: " + ordenCodigo + ", Cliente: " + cliente.getNombreLegal();
			}else{
				String ordenCodigo = ordenMedio.getCodigo().split("-")[1];
				observacion = observacion + " - Orden: " + ordenCodigo + ", Cliente: " + cliente.getNombreLegal();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return observacion;
	}
	
	public void cargarDetallesOrdenMedio(OrdenMedioIf ordenMedioIf) {
		agregarCompraDetalleDesdeOrdenMedio(ordenMedioIf);
	}
		
	public void cargarDetallesOrdenCompra(OrdenCompraIf ordenCompraIf) {
		agregarCompraDetalleDesdeOrdenCompra(ordenCompraIf);
	}
	
	public void getSelectedObject(Object compraSeleccionada) {
		compra = (CompraIf) compraSeleccionada;
		ESTADO_ANTERIOR = new String(compra.getEstado());
		this.showUpdateMode();
		cleanTableDetalle();

		mapaProductoNombre = null;
		mapaProductoNombre = new HashMap<Long, String>();
		try {
			ordenRequerida = (SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(compra.getId()).size() == 0)?false:true;
			manejarBotonActivar();
			proveedorIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(compra.getProveedorId());
			ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorIf.getClienteId());
				
			getTxtCodigo().setText(compra.getCodigo());
			Calendar calendarFecha = new GregorianCalendar();
			calendarFecha.setTime(compra.getFecha());
			getCmbFecha().setCalendar(calendarFecha);
			getCmbFecha().repaint();
			Calendar calendarFechaVencimiento = new GregorianCalendar();
			calendarFechaVencimiento.setTime(compra.getFechaVencimiento());
			getCmbFechaVencimiento().setCalendar(calendarFechaVencimiento);
			getCmbFechaVencimiento().repaint();
			cargarComboOficina(compra.getOficinaId());
			
			if (compra.getFechaCaducidad() != null) {
				Calendar calendarFechaCaducidad = new GregorianCalendar();
				calendarFechaCaducidad.setTime(compra.getFechaCaducidad());
				getCmbFechaCaducidad().setCalendar(calendarFechaCaducidad);
				getCmbFechaCaducidad().repaint();
			}
			
			getTxtProveedor().setText(proveedor.getIdentificacion() + " - " + proveedorIf.getDescripcion()); // " - " + proveedor.getNombreLegal() + 
			getTxtPreimpreso().setText(compra.getPreimpreso());
			getTxtAutorizacion().setText(compra.getAutorizacion());
			
			tipoDocumentoIf = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(compra.getTipodocumentoId());
			getCmbTipoDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoDocumento(), compra.getTipodocumentoId()));
			getCmbMoneda().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbMoneda(), compra.getMonedaId()));
			getCmbTipoSustentoTributario().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoSustentoTributario(), compra.getIdSriSustentoTributario()));
			
			if (CompraModel.ESTADO_INACTIVA.equals(compra.getEstado()))
				getCmbEstado().setSelectedItem(CompraModel.NOMBRE_ESTADO_INACTIVA);
			else if (CompraModel.ESTADO_NORMAL_REEMBOLSO.equals(compra.getEstado()))
				getCmbEstado().setSelectedItem(CompraModel.NOMBRE_ESTADO_NORMAL_REEMBOLSO);
			else if (CompraModel.ESTADO_REEMBOLSO_NORMAL.equals(compra.getEstado()))
				getCmbEstado().setSelectedItem(CompraModel.NOMBRE_ESTADO_REEMBOLSO_NORMAL);
			else
				getCmbEstado().setSelectedItem(CompraModel.NOMBRE_ESTADO_ACTIVA);
			
			if (CompraModel.TIPO_COMPRA_LOCAL.equals(compra.getLocalimportada()))
				getCmbTipoCompra().setSelectedItem(CompraModel.NOMBRE_TIPO_COMPRA_LOCAL);
			else
				getCmbTipoCompra().setSelectedItem(CompraModel.NOMBRE_TIPO_COMPRA_IMPORTACION);
			
			getTxtObservacion().setText(compra.getObservacion());
			
			if (proveedorIf != null) {
				if (ordenRequerida)
					getBtnManejarOrdenes().setEnabled(true);
				else
					getBtnManejarOrdenes().setEnabled(false);
			}			
			
			Iterator<OrdenAsociadaIf> ordenAsociadaIt = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(compra.getId()).iterator();
			while (ordenAsociadaIt.hasNext()) {
				OrdenAsociadaIf ordenAsociada = ordenAsociadaIt.next();
				OrderData orden = new OrderData();
				orden.setProvider(proveedor);
				orden.setOrderType(ordenAsociada.getTipoOrden());
				if (ordenAsociada.getTipoOrden().equals("OC")) {
					orden.setPurchaseOrder(SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(ordenAsociada.getOrdenId()));
				} else {
					orden.setMediaOrder(SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenAsociada.getOrdenId()));
				}
				ordenesDataVector.add(orden);
			}
			
			getTxtReferencia().setText(compra.getReferencia());
			DefaultTableModel tableModel = (DefaultTableModel) getTblCompraDetalle().getModel();
			compraDetalleColeccion = (List) SessionServiceLocator.getCompraDetalleSessionService().findCompraDetalleByCompraId(compra.getId());
			Iterator it = compraDetalleColeccion.iterator();
			
			while (it.hasNext()) {
				CompraDetalleIf compraDetalle = (CompraDetalleIf) it.next();
				ProductoIf producto = (ProductoIf) productosMap.get(compraDetalle.getProductoId());
				productoIf = producto;
				cargarComboRetencion();
				
				if (compraDetalle.getIdSriAir() != null)
					getCmbRetencionRenta().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionRenta(),compraDetalle.getIdSriAir()));
				
				if (compraDetalle.getSriIvaRetencionId() != null)
					getCmbRetencionIva().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionIva(),compraDetalle.getSriIvaRetencionId()));
				
				Vector<String> fila = new Vector<String>();
				double cantidad = Double.parseDouble(compraDetalle.getCantidad().toString());
				double valor = Double.parseDouble(compraDetalle.getValor().toString());
				double subtotal = cantidad * valor;
				double porcentajeDescuentoEspecial = compraDetalle.getPorcentajeDescuentoEspecial().doubleValue() / 100;
				double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
				double otroImpuestoSubtotal = Double.parseDouble(compraDetalle.getOtroImpuesto().toString());
				double descuentoSubtotal = Double.parseDouble(compraDetalle.getDescuento().toString());
				double porcentajeBonificacion = Double.parseDouble(compraDetalle.getPorcentajeDescuentosVarios().toString());
				double bonificacion = (subtotal - descuentoEspecial) * (porcentajeBonificacion / 100D);
				double iva = Double.parseDouble(compraDetalle.getIva().toString());
				SriAirIf sriAir = (SriAirIf) getCmbRetencionRenta().getSelectedItem();
				SriIvaRetencionIf sriIvaRetencion = (SriIvaRetencionIf) getCmbRetencionIva().getSelectedItem();
				double porcentaje_retencion_fuente = 0D;
				double porcentaje_retencion_iva = 0D;
				
				if (sriAir != null)
					porcentaje_retencion_fuente = sriAir.getPorcentaje().doubleValue();
				
				if (sriIvaRetencion != null)
					porcentaje_retencion_iva = sriIvaRetencion.getPorcentaje().doubleValue();
				
				double retencion_fuente = (subtotal - descuentoEspecial - descuentoSubtotal - bonificacion) * (porcentaje_retencion_fuente / 100);
				double retencion_iva = (iva) * porcentaje_retencion_iva / 100;
				double ice = Double.parseDouble(compraDetalle.getIce().toString());
				subtotal = subtotal - descuentoEspecial - descuentoSubtotal - bonificacion + iva + ice + otroImpuestoSubtotal;
				String descripcionProducto = getDescripcionProducto(genericosMap,presentacionesMap,lineasMap,
						modelosMap,coloresMap,productoIf);
				
				fila.add(descripcionProducto);
				fila.add(formatoDecimal.format(cantidad));
				fila.add(formatoDecimal.format(valor));
				fila.add(formatoDecimal.format(descuentoEspecial));
				fila.add(formatoDecimal.format(descuentoSubtotal));
				fila.add(formatoDecimal.format(bonificacion));
				fila.add(formatoDecimal.format(iva));
				fila.add(formatoDecimal.format(retencion_fuente + retencion_iva));
				fila.add(formatoDecimal.format(ice));
				fila.add(formatoDecimal.format(otroImpuestoSubtotal));
				fila.add(formatoDecimal.format(subtotal));
				tableModel.addRow(fila);
				
				verificarNombreProducto(mapaProductoNombre, productoIf.getId(), null);
			}
			
			actualizarTotales();			
			cargarTablaRetenciones();
			
			//GASTOS
			cargarMapasComprasGastoYCompraGastoDetalle();
			cargarTablaGastosDesdeTipoDocumento(false);
			cargarTablaGasto();
			
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

	private void cargarMapas() {
		genericosMap = mapearGenericos();
		productosMap = mapearProductos();
		presentacionesMap = mapearPresentaciones();
		modelosMap = mapearModelos();
		lineasMap = mapearLineas();
		coloresMap = mapearColores();
	}
	
	private void cargarMapasComprasGastoYCompraGastoDetalle() throws GenericBusinessException {
		compraGastoClase = SessionServiceLocator.getCompraGastoSessionService().getMapaGastoMapaDetalleGasto(compra.getId());
		tfce.setCompraGasto(compraGastoClase);
		compraGastoClase.getMapaCompraDetalleGasto();
		compraGastoClase.getMapaComprasAsociadas();
		System.out.println("");
	}

	private void cargarTablaRetenciones() throws GenericBusinessException {
		Collection compraRetencionesColeccion = SessionServiceLocator.getCompraRetencionSessionService().findCompraRetencionByCompraId(compra.getId());
		Iterator itCompraRetenciones = compraRetencionesColeccion.iterator();
		
		while (itCompraRetenciones.hasNext()) {
			CompraRetencionIf  compraRetencionIf = (CompraRetencionIf) itCompraRetenciones.next();
			compraRetencionColeccion.add(compraRetencionIf);
			
			modelTblRetenciones = (DefaultTableModel) getTblRetenciones().getModel();
			Vector<String> filaRetenciones = new Vector<String>();
			filaRetenciones.add(compraRetencionIf.getEjercicioFiscal());
			filaRetenciones.add(compraRetencionIf.getEstablecimiento() +" - "+ compraRetencionIf.getPuntoEmision() + " - " + compraRetencionIf.getSecuencial());
			filaRetenciones.add(compraRetencionIf.getAutorizacion());
			filaRetenciones.add(formatoDecimal.format(compraRetencionIf.getBaseImponible()));
			if(compraRetencionIf.getImpuesto().equals(IMPUESTO_RENTA.substring(0,1))){
				filaRetenciones.add(IMPUESTO_RENTA);
			}else if(compraRetencionIf.getImpuesto().equals(IMPUESTO_IVA.substring(0,1))){
				filaRetenciones.add(IMPUESTO_IVA);
			}
			filaRetenciones.add(formatoDecimal.format(compraRetencionIf.getPorcentajeRetencion()));
			filaRetenciones.add(formatoDecimal.format(compraRetencionIf.getValorRetenido()));
			filaRetenciones.add(Utilitarios.getFechaUppercase(compraRetencionIf.getFechaEmision()));
			modelTblRetenciones.addRow(filaRetenciones);				
			cleanRetencion();
		}
	}
	
	private boolean existenRetenciones(Collection comprasRetencionesColeccion, Long compraId) {
		Iterator comprasRetencionesIterator = comprasRetencionesColeccion.iterator();
		
		while (comprasRetencionesIterator.hasNext()) {
			CompraRetencionIf compraRetencion = (CompraRetencionIf) comprasRetencionesIterator.next();
			if (compraRetencion.getCompraId().compareTo(compraId) == 0)
				return true;
		}
		
		return false;
	}
	
	private void buscarProveedor() {
		Long idCorporacion = 0L;
		Long idCliente = 0L;
		String tipoCliente = "PR";
		String tituloVentanaBusqueda = "Proveedores";
		ClienteOficinaCriteria clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente,"", false);
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.addElement(70);
		anchoColumnas.addElement(200);
		anchoColumnas.addElement(80);
		anchoColumnas.addElement(230);
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
		if (popupFinder.getElementoSeleccionado() != null)
			setProveedor(popupFinder);
	}
	
	private void buscarProducto(int tipoBusqueda, boolean busquedaPorTexto, Map<String, Object> aMap, int tamanoLista) {
		Long idReferencia = 0L;
		if (ordenRequerida) {
			String mmpg = "";
			productoCompraCriteria = new ProductoCompraCriteria("Producto", proveedorIf.getId(), "A", "P", "A", false, mmpg);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), productoCompraCriteria, tipoBusqueda);
			if (popupFinder.getElementoSeleccionado() != null) {
				boolean productoRepetido = false;
				productoIf = (ProductoIf) popupFinder.getElementoSeleccionado();
				genericoIf = genericosMap.get(productoIf.getGenericoId());
				setProductoDetalle(productoIf);
			}
		} else {
			if (!busquedaPorTexto) {
				String mmpg = "";
				productoCriteria = new ProductoCriteria("Producto", proveedorIf.getId(), "A", "P", "A",false,mmpg);
			} else {
				productoCriteria = new ProductoCriteria("Producto");
				productoCriteria.setResultListSize(tamanoLista);
				productoCriteria.setQueryBuilded(aMap);
			}

			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), productoCriteria, tipoBusqueda);
			if (popupFinder.getElementoSeleccionado() != null) {
				productoIf = (ProductoIf) popupFinder.getElementoSeleccionado();
				genericoIf = genericosMap.get(productoIf.getGenericoId());
				setProductoDetalle(productoIf);					
				getTxtCantidad().setText("0");
				getTxtCantidad().setEnabled(true);
				getTxtCantidad().setEditable(true);
				getTxtValor().setText("0");
				getTxtValor().setEnabled(true);
				getTxtValor().setEditable(true);
				getTxtPorcentajeDescuentoEspecial().setText("0");
				getTxtPorcentajeDescuentoEspecial().setEnabled(true);
				getTxtPorcentajeDescuentoEspecial().setEditable(true);
				getTxtPorcentajeDescuentoAgencia().setText("0");
				getTxtPorcentajeDescuentoAgencia().setEnabled(true);
				getTxtPorcentajeDescuentoAgencia().setEditable(true);
				getTxtPorcentajeDescuentosVarios().setText("0");
				getTxtPorcentajeDescuentosVarios().setEnabled(true);
				getTxtPorcentajeDescuentosVarios().setEditable(true);
				getTxtPorcentajeOtroImpuesto().setText("0");
				getTxtPorcentajeOtroImpuesto().setEnabled(true);
				getTxtPorcentajeOtroImpuesto().setEditable(true);
			}
		}
	}
	
	private void setProveedor(JDPopupFinderModel popupFinder) {
		try {
			if (popupFinder != null && popupFinder.getElementoSeleccionado() != null) {
				proveedorIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
				
				ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorIf.getClienteId());

				String estadoOperadorProveedor = proveedor.getEstado();
				if(estadoOperadorProveedor.equals(ESTADO_RIESGO)){
					/*int opcion = JOptionPane.showOptionDialog(null,
							"Proveedor: " + proveedor.getNombreLegal() + " es sujeto de RIESGO, consulte con el Dpto. de Contabilidad, ¿Desea continuar?",
							"Confirmación", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options, no);
					if (opcion == JOptionPane.YES_OPTION) {						
						setearProveedor(proveedor);					
					}*/
					SpiritAlert.createAlert("Proveedor: " + proveedor.getNombreLegal() + " es sujeto de RIESGO, consulte con el Dpto. de Contabilidad",SpiritAlert.WARNING);
				}else{
					setearProveedor(proveedor);
				}					
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void setearProveedor(ClienteIf proveedor) throws GenericBusinessException {
		getTxtProveedor().setText(proveedor.getIdentificacion() + " - " + proveedorIf.getDescripcion()); // " - " + proveedor.getNombreLegal() + 
		getBtnBuscarProducto().setEnabled(true);
		//MODIFIED 19 JULIO
		//if (!ordenCompraRequerida)
		if (!ordenRequerida)
			getTxtProducto().setEditable(true);					
		
		if (proveedorIf != null) {
			if (ordenRequerida)
				getBtnManejarOrdenes().setEnabled(true);
			else
				getBtnManejarOrdenes().setEnabled(false);
			
			getTxtReferencia().setEditable(true);
			getTxtReferencia().setText("");
			getTxtObservacion().setText("");
			getTxtProducto().setText("");
			getBtnBuscarProducto().setEnabled(true);
			//MODIFIED 19 JULIO
			//if (!ordenCompraRequerida)
			if (!ordenRequerida)
				getTxtProducto().setEditable(true);
			ordenesDataVector = new Vector<OrderData>();
			cleanTableDetalle();			
			if (getCmbDocumento().getItemCount() > 0 && getMode() != SpiritMode.FIND_MODE) {
				getCmbDocumento().setEnabled(true);
				if (getCmbDocumento().getItemCount() > 0){
					Map documentoMap = new HashMap();
					documentoMap.put("tipoDocumentoId",tipoDocumentoIf.getId());
					documentoMap.put("codigo", DOCUMENTO_COMPRA_LOCAL);
					if(SessionServiceLocator.getDocumentoSessionService().findDocumentoByQuery(documentoMap).size() > 0){
						DocumentoIf documentoCompraLocal = (DocumentoIf)SessionServiceLocator.getDocumentoSessionService().findDocumentoByQuery(documentoMap).iterator().next();
						getCmbDocumento().setSelectedItem(documentoCompraLocal);
					}else{
						getCmbDocumento().setSelectedIndex(-1);
					}
					getCmbDocumento().validate();
					getCmbDocumento().repaint();
				}
			}
		}
	}
	
	private void setProductoDetalle(ProductoIf productoIf) {
		try {
			String descripcionProducto = getDescripcionProducto(genericosMap,presentacionesMap,lineasMap,
					modelosMap,coloresMap,productoIf);
			getTxtProducto().setText(descripcionProducto);
			getTxtDescripcion().setText(descripcionProducto);
			getTxtCantidad().setText("");
			getTxtCantidad().setEnabled(true);
			getTxtCantidad().setEditable(true);
			getTxtValor().setText("");
			getTxtValor().setEnabled(true);
			getTxtValor().setEditable(true);
			getTxtPorcentajeDescuentoEspecial().setText("0");
			getTxtPorcentajeDescuentoEspecial().setEnabled(true);
			getTxtPorcentajeDescuentoEspecial().setEditable(true);
			getTxtPorcentajeDescuentoAgencia().setText("0");
			getTxtPorcentajeDescuentoAgencia().setEnabled(true);
			getTxtPorcentajeDescuentoAgencia().setEditable(true);
			getTxtPorcentajeDescuentosVarios().setText("0");
			getTxtPorcentajeDescuentosVarios().setEnabled(true);
			getTxtPorcentajeDescuentosVarios().setEditable(true);
			getTxtPorcentajeOtroImpuesto().setText("0");
			getTxtPorcentajeOtroImpuesto().setEnabled(true);
			getTxtPorcentajeOtroImpuesto().setEditable(true);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	static String getDescripcionProducto(Map<Long,GenericoIf> genericosMap,
			Map<Long,PresentacionIf> presentacionesMap,Map<Long,LineaIf> lineasMap,
			Map<Long,ModeloIf> modelosMap,Map<Long,ColorIf> coloresMap,
			ProductoIf productoIf) throws GenericBusinessException {
		String descripcionProducto = "";
		GenericoIf genericoIf = genericosMap.get(productoIf.getGenericoId());
		if ("S".equals(genericoIf.getUsaLote())) {
			PresentacionIf presentacion = (productoIf.getPresentacionId()!=null)? presentacionesMap.get(productoIf.getPresentacionId()):null;
			ModeloIf modelo = (productoIf.getModeloId()!=null)? modelosMap.get(productoIf.getModeloId()):null;
			LineaIf linea = (genericoIf.getLineaId()!=null)? lineasMap.get(genericoIf.getLineaId()):null;
			ColorIf color = (productoIf.getColorId()!=null)? coloresMap.get(productoIf.getColorId()):null;
			descripcionProducto = (productoIf.getCodigoBarras() != null && !productoIf.getCodigoBarras().equals(""))?productoIf.getCodigoBarras():productoIf.getCodigo();
			descripcionProducto += " " + genericoIf.getNombre();
			if (modelo!=null)
				descripcionProducto += " " + modelo.getNombre();
			if (linea!=null)
				descripcionProducto += " " + linea.getNombre();
			if (color!=null)
				descripcionProducto += " " + color.getNombre();
			if (presentacion!=null)
				descripcionProducto += " " + presentacion.getNombre();

		} else
			descripcionProducto = productoIf.getCodigo() + " " + genericoIf.getNombre();
		return descripcionProducto;
	}
	
	private void setDatosCompraDesdeOrdenCompra(OrdenCompraIf ordenCompraIf){
		try {
			proveedorIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenCompraIf.getProveedorId());
			Collection proveedores=  SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(proveedorIf.getId());
			if (proveedores.size()>0){
				ClienteIf proveedor= (ClienteIf) proveedores.iterator().next();
				getTxtProveedor().setText(proveedor.getIdentificacion() + " - "+proveedorIf.getCodigo() + " - " + proveedor.getNombreLegal());
			}
			
			if (proveedorIf != null) {
				getTxtReferencia().setEditable(true);
				getTxtReferencia().setText(ordenCompraIf.getCodigo());
				getTxtProducto().setText("");
				//MODIFIED 19 JULIO
				//if (!ordenCompraRequerida)
				if (!ordenRequerida)
					getTxtProducto().setEditable(true);
				getBtnBuscarProducto().setEnabled(true);
			}
			
			getCmbTipoDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoDocumento(), ordenCompraIf.getTipodocumentoId()));
			getTxtObservacion().setText(ordenCompraIf.getObservacion());
			
			if (CompraModel.TIPO_COMPRA_LOCAL.equals(ordenCompraIf.getLocalimportada()))
				getCmbTipoCompra().setSelectedItem(CompraModel.NOMBRE_TIPO_COMPRA_LOCAL);
			else
				getCmbTipoCompra().setSelectedItem(CompraModel.NOMBRE_TIPO_COMPRA_IMPORTACION);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la carga de datos desde Orden de Compra", SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error General en la carga de datos desde orden de Compra", SpiritAlert.ERROR);
		}
	}
	
	private void agregarCompraDetalleDesdeOrdenMedio(OrdenMedioIf ordenMedio) {
		try {
			//reviso si el proveedor tiene asociado retencion de renta o iva			
			ClienteIf proveedor = (ClienteIf)SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedio.getProveedorId()).iterator().next();
			Collection proveedorRetenciones = SessionServiceLocator.getClienteRetencionSessionService().findClienteRetencionByClienteId(proveedor.getId());
			Iterator proveedorRetencionesIt = proveedorRetenciones.iterator();
			
			ClienteRetencionIf clienteRetencion = null;
			
			while(proveedorRetencionesIt.hasNext()){
				clienteRetencion = (ClienteRetencionIf)proveedorRetencionesIt.next();
			}
			
			//cargo ordenes detalle	
			Collection ordenCompraDetalle = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedio.getId());
			
			if (ordenCompraDetalle.size() > 0) {
				DefaultTableModel tableModel = (DefaultTableModel) getTblCompraDetalle().getModel();
				Iterator itComDet = ordenCompraDetalle.iterator();
				
				productoIf = (ProductoIf) productosMap.get(ordenMedio.getProductoProveedorId());								
				genericoIf = (GenericoIf) genericosMap.get(productoIf.getGenericoId());
				cargarComboRetencion();
				
				//si el proveedor tiene asociados porcentajes de retenciones los selecciono
				if(clienteRetencion != null){
					getCmbRetencionRenta().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionRenta(),clienteRetencion.getSriAirId()));
					getCmbRetencionRenta().validate();
					getCmbRetencionRenta().repaint();
					getCmbRetencionIva().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionIva(),clienteRetencion.getSriIvaRetencionId()));
					getCmbRetencionIva().validate();
					getCmbRetencionIva().repaint();
				}
				
				//seteo por default la primera retencion de cada combo
				else{
					if ( getCmbRetencionRenta().getModel().getSize() > 0 )
						getCmbRetencionRenta().setSelectedIndex(0);	
					
					if ( getCmbRetencionIva().getModel().getSize() > 0 )
						getCmbRetencionIva().setSelectedIndex(0);
				}
				
				//ret. renta
				SriAirIf sriAir = (SriAirIf) getCmbRetencionRenta().getSelectedItem();
				double porcentaje_retencion_fuente = 0D;
				
				if (sriAir != null)
					porcentaje_retencion_fuente = sriAir.getPorcentaje().doubleValue();
				
				//ret. iva
				SriIvaRetencionIf sriIvaRetencion = (SriIvaRetencionIf) getCmbRetencionIva().getSelectedItem();
				double porcentaje_retencion_iva = 0D;
				
				if (sriIvaRetencion != null)
					porcentaje_retencion_iva = sriIvaRetencion.getPorcentaje().doubleValue();
							
				
				//la cantidad en orden de medios siempre va a ser uno
				long cantidad = 1L;
				double subtotal = 0D;
				double otroImpuestoSubtotal = Double.parseDouble(new BigDecimal(0).toString());
				double descuentoSubtotal = 0D;
				double bonificacionSubtotal = 0D;
				double iva = 0D;
				double ice = Double.parseDouble(new BigDecimal(0).toString());
				double total = 0D;
				
				//para armar la descripcion
				String descripcion = "";
				String nombreProductoProveedor = "";
				String descripcionClienteOficina = "";
				String nombreCampana = "";
				String periodoInicio = "";
				String periodoFin = "";
				long comercialId = 0;
				String productos = "";
				String canje = "";
				
				//ONLY TO ORDENES DE MEDIO AGRUPADAS POR CANAL
				String[] comercialSplit = new String[2]; 
				
				//ONLY TO ORDENES DE MEDIO AGRUPADAS POR CANAL
				ArrayList<String> productosVersion = new ArrayList<String>();
				String[] productoSplit = new String[20];				
				
				ProductoIf productoProveedor = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedio.getProductoProveedorId());
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoProveedor.getGenericoId());
				ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenMedio.getProveedorId()); 
				PlanMedioIf planMedio = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(ordenMedio.getPlanMedioId());
				OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(planMedio.getOrdenTrabajoDetalleId());
				OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
				CampanaIf campana = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajo.getCampanaId());
				
				while(itComDet.hasNext()){
					OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) itComDet.next();			
					
					comercialId = ordenMedioDetalleIf.getComercialId();
					
					ComercialIf comercialIf = SessionServiceLocator.getComercialSessionService().getComercial(comercialId);
										
					if (ordenMedio.getOrdenMedioTipo() == null ){//&& ordenMedio.getAgrupadaPorCanal().compareTo("SI") != 0){
						
						//PARA ESCOGER EL PRODUCTO O LETRA CON LA DURACION DE LA CUÑA PARA EL REPORTE
						Long derechoPrograma = comercialIf.getDerechoprogramaId();
						DerechoProgramaIf derechoProgramaIf = SessionServiceLocator.getDerechoProgramaSessionService().getDerechoPrograma(derechoPrograma);
						if (derechoProgramaIf.getNombre().trim().compareTo("CUÑA") == 0){
							productos = comercialIf.getVersion() + " (" + comercialIf.getDuracion() +") " + comercialIf.getNombre();
						}else{
							//Long campaniaId = comercialIf.getCampanaId();
							//Long productoId = comercialIf.getProductoClienteId();
							Map aMap = new HashMap();
							aMap.put("campanaId", comercialIf.getCampanaId());
							aMap.put("productoClienteId", comercialIf.getProductoClienteId());
							Collection<ComercialIf> comercialIfColl = SessionServiceLocator.getComercialSessionService().findComercialByQuery(aMap);
							if (!comercialIfColl.isEmpty()){
								for (ComercialIf comercialIfTemp : comercialIfColl ){
									Long derechoProgramaTemp = comercialIfTemp.getDerechoprogramaId();
									DerechoProgramaIf derechoProgramaIfTemp = SessionServiceLocator.getDerechoProgramaSessionService().getDerechoPrograma(derechoProgramaTemp);
									if (derechoProgramaIfTemp.getNombre().trim().compareTo("CUÑA") == 0){
										productos = comercialIfTemp.getVersion() + " (" + comercialIfTemp.getDuracion() +") " + comercialIfTemp.getNombre();
										break;
									}
								}
							}
						}						
										
					}else{	
					
						String productosCanal = "";
						String productoCanalVersion = "";
						
						
						//PARA ESCOGER EL PRODUCTO O LETRA CON LA DURACION DE LA CUÑA PARA EL REPORTE
						Long derechoPrograma = comercialIf.getDerechoprogramaId();
						DerechoProgramaIf derechoProgramaIf = SessionServiceLocator.getDerechoProgramaSessionService().getDerechoPrograma(derechoPrograma);
						if (derechoProgramaIf.getNombre().trim().compareTo("CUÑA") == 0){
							productosCanal = comercialIf.getVersion() + " (" + comercialIf.getDuracion() +") " + comercialIf.getNombre();
						}else{
							//Long campaniaId = comercialIf.getCampanaId();
							//Long productoId = comercialIf.getProductoClienteId();
							Map aMap = new HashMap();
							aMap.put("campanaId", comercialIf.getCampanaId());
							aMap.put("productoClienteId", comercialIf.getProductoClienteId());
							Collection<ComercialIf> comercialIfColl = SessionServiceLocator.getComercialSessionService().findComercialByQuery(aMap);
							if (!comercialIfColl.isEmpty()){
								for (ComercialIf comercialIfTemp : comercialIfColl ){
									Long derechoProgramaTemp = comercialIfTemp.getDerechoprogramaId();
									DerechoProgramaIf derechoProgramaIfTemp = SessionServiceLocator.getDerechoProgramaSessionService().getDerechoPrograma(derechoProgramaTemp);
									//if (derechoProgramaIfTemp.getNombre().trim().compareTo("CUÑA") == 0){
										productosCanal = comercialIfTemp.getVersion() + " (" + comercialIfTemp.getDuracion() +") " + comercialIfTemp.getNombre();
										break;
									//}
								}
							}
						}						
																			
						productoCanalVersion = productosCanal.substring(0,1);
						
													
						//PARA EVITAR QUE SE REPIRTAN LOS PRODUCTOS
						if (productos.length() > 0){
							productoSplit = productos.split(", ");
							boolean isVersion = false;
							for (int i = 0; i < productoSplit.length; i ++){
								String productoVersionTemp = productoSplit[i].substring(0,1);
								if (productoVersionTemp.compareTo(productoCanalVersion) == 0){
									isVersion = true;
								}
							}
							if (!isVersion){
								productos = productos + productosCanal + ", "; //+ "\n";
							}
						}else{
							productos = productosCanal + ", ";// + "\n";
						}
					}								
				}
				
				subtotal = ordenMedio.getValorSubtotal().doubleValue();
				
				//Si existe facturacion directa y es mayor que cero y menor que 100 entonces se la usa como porcentaje de descuento
				BigDecimal porcentajeCanje = ordenMedio.getPorcentajeCanje() != null? ordenMedio.getPorcentajeCanje() : new BigDecimal(0);
				BigDecimal porcentajeNegociacionComision = ordenMedio.getPorcentajeNegociacionComision() != null? ordenMedio.getPorcentajeNegociacionComision() : new BigDecimal(0);
				if(porcentajeCanje.compareTo(new BigDecimal(0)) == 1 && porcentajeCanje.compareTo(new BigDecimal(100)) == -1){
					descuentoSubtotal = ordenMedio.getValorSubtotal().multiply(porcentajeNegociacionComision.divide(new BigDecimal(100))).doubleValue();
				}
				else{
					descuentoSubtotal = ordenMedio.getValorDescuento().doubleValue();
				}
				
				double bonificacion = ordenMedio.getValorSubtotal().subtract(BigDecimal.valueOf(descuentoSubtotal)).multiply(ordenMedio.getPorcentajeBonificacionCompra().divide(new BigDecimal(100))).doubleValue();
				bonificacionSubtotal = bonificacionSubtotal + bonificacion;
				
				iva = (subtotal - descuentoSubtotal - bonificacion) * (Parametros.IVA / 100D);
				ice = 0D;	
				
				//Necesito saber si la orden viene de un presupuesto detalle con Negociacion Directa
				if(ordenMedio.getPorcentajeCanje() != null 
						&& ordenMedio.getPorcentajeCanje().compareTo(new BigDecimal(0)) == 1){
					//calculo el porcentaje para deducirlo a todos los valores
					double porcentajeND = 1 - (ordenMedio.getPorcentajeCanje().doubleValue()/100);
					subtotal = subtotal * porcentajeND;
					otroImpuestoSubtotal = otroImpuestoSubtotal * porcentajeND;
					descuentoSubtotal = descuentoSubtotal * porcentajeND;
					bonificacionSubtotal = bonificacionSubtotal * porcentajeND;
					iva = iva * porcentajeND;
					ice = ice * porcentajeND;				
				}
				
				double retencion_fuente = (subtotal - descuentoSubtotal - bonificacionSubtotal) * porcentaje_retencion_fuente / 100;
				double retencion_iva = (iva) * porcentaje_retencion_iva / 100;
				
				total = subtotal - descuentoSubtotal - bonificacionSubtotal + iva + ice + otroImpuestoSubtotal;
				
				//Agregar compraDetalle a la coleccion
				CompraDetalleData data = new CompraDetalleData();
				data.setProductoId(productoIf.getId());
				data.setCantidad(Long.valueOf(cantidad));
				
				data.setValor(BigDecimal.valueOf(Double.valueOf(subtotal)));
				data.setDescuento(BigDecimal.valueOf(Double.valueOf(descuentoSubtotal)));
				data.setPorcentajeDescuentosVarios(ordenMedio.getPorcentajeBonificacionCompra());
				data.setIva(BigDecimal.valueOf(Double.valueOf(iva)));
				data.setIce(BigDecimal.valueOf(Double.valueOf(ice)));				
				data.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf(otroImpuestoSubtotal)));
				data.setIdSriAir(sriAir!=null?sriAir.getId():null);
				data.setSriIvaRetencionId(sriIvaRetencion!=null?sriIvaRetencion.getId():null);
				data.setPorcentajeDescuentoEspecial(BigDecimal.ZERO);
				//para descripcion
				nombreProductoProveedor = generico.getNombre();
				descripcionClienteOficina = clienteOficina.getDescripcion();
				nombreCampana = campana.getNombre();
				
				//periodoInicio = "" + planMedio.getFechaInicio(); 
				//periodoFin = "" + planMedio.getFechaFin(); 
				periodoInicio = "" + Utilitarios.getFechaCortaUppercase(new java.util.Date(planMedio.getFechaInicio().getTime()));
				periodoFin = "" + Utilitarios.getFechaCortaUppercase(new java.util.Date(planMedio.getFechaFin().getTime()));
				canje = "" + ordenMedio.getPorcentajeCanje() + "%";
							
				descripcion += "" + nombreProductoProveedor + "\n";
				descripcion += "" + descripcionClienteOficina + "\n";
				descripcion += "Campaña: " + nombreCampana + "\n";
				descripcion += "Periodo: del " + periodoInicio + " al " + periodoFin  +"\n";
				descripcion += "Producto(s): " + productos +"\n";
				descripcion += "Canje: " + canje +"\n";
								
				data.setDescripcion(descripcion);
				
				// Si es así ingreso el detalle de la compra en la tabla
				compraDetalleColeccion.add(data);
				Vector<String> fila = new Vector<String>();
				String descripcionProducto = getDescripcionProducto(genericosMap,presentacionesMap,lineasMap,
						modelosMap,coloresMap,productoIf);
				
				fila.add(descripcionProducto);
				fila.add(formatoDecimal.format(cantidad));			
				fila.add(formatoDecimal.format(subtotal));
				fila.add(formatoDecimal.format(0D));
				fila.add(formatoDecimal.format(descuentoSubtotal));
				fila.add(formatoDecimal.format(bonificacionSubtotal));
				fila.add(formatoDecimal.format(iva));
				fila.add(formatoDecimal.format(retencion_fuente + retencion_iva));
				fila.add(formatoDecimal.format(ice));
				fila.add(formatoDecimal.format(otroImpuestoSubtotal));
				fila.add(formatoDecimal.format(total));
				tableModel.addRow(fila);
				
				actualizarTotales();
			
			} else {
				SpiritAlert.createAlert("No se puede obtener el detalle de la Orden de Medio", SpiritAlert.INFORMATION);
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la carga del detalle desde Orden de Medio", SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error General en la carga del detalle desde Orden de Medio", SpiritAlert.ERROR);
		}
	}
		
	
	private void agregarCompraDetalleDesdeOrdenCompra(OrdenCompraIf ordenCompra) {
		try {
			//reviso si el proveedor tiene asociado retencion de renta o iva			
			ClienteIf proveedor = (ClienteIf)SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenCompra.getProveedorId()).iterator().next();
			Collection proveedorRetenciones = SessionServiceLocator.getClienteRetencionSessionService().findClienteRetencionByClienteId(proveedor.getId());
			Iterator proveedorRetencionesIt = proveedorRetenciones.iterator();
			
			ClienteRetencionIf clienteRetencion = null;
			
			while(proveedorRetencionesIt.hasNext()){
				clienteRetencion = (ClienteRetencionIf)proveedorRetencionesIt.next();
			}
			
			//cargo ordenes detalle			
			Collection ordenCompraDetalle = SessionServiceLocator.getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByOrdencompraId(ordenCompra.getId());
			
			if (ordenCompraDetalle.size() > 0) {
				DefaultTableModel tableModel = (DefaultTableModel) getTblCompraDetalle().getModel();
				Iterator itComDet = ordenCompraDetalle.iterator();
				
				while(itComDet.hasNext()){
					OrdenCompraDetalleIf ordenCompraDetalleIf = (OrdenCompraDetalleIf) itComDet.next();
					productoIf = (ProductoIf) productosMap.get(ordenCompraDetalleIf.getProductoId());
					genericoIf = (GenericoIf) genericosMap.get(productoIf.getGenericoId());
					cargarComboRetencion();
					
					//si el proveedor tiene asociados porcentajes de retenciones los selecciono
					if(clienteRetencion != null){
						getCmbRetencionRenta().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionRenta(),clienteRetencion.getSriAirId()));
						getCmbRetencionRenta().validate();
						getCmbRetencionRenta().repaint();
						getCmbRetencionIva().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionIva(),clienteRetencion.getSriIvaRetencionId()));
						getCmbRetencionIva().validate();
						getCmbRetencionIva().repaint();
					}
					
					//seteo por default la primera retencion de cada combo
					else{
						if ( getCmbRetencionRenta().getModel().getSize() > 0 )
							getCmbRetencionRenta().setSelectedIndex(0);	
						
						if ( getCmbRetencionIva().getModel().getSize() > 0 )
							getCmbRetencionIva().setSelectedIndex(0);
					}
					
					//ret. renta
					SriAirIf sriAir = (SriAirIf) getCmbRetencionRenta().getSelectedItem();
					double porcentaje_retencion_fuente = 0D;
					
					if (sriAir != null)
						porcentaje_retencion_fuente = sriAir.getPorcentaje().doubleValue();
					
					//ret. iva
					SriIvaRetencionIf sriIvaRetencion = (SriIvaRetencionIf) getCmbRetencionIva().getSelectedItem();
					double porcentaje_retencion_iva = 0D;
					
					if (sriIvaRetencion != null)
						porcentaje_retencion_iva = sriIvaRetencion.getPorcentaje().doubleValue();
					
					
					long cantidad = Long.parseLong(ordenCompraDetalleIf.getCantidad().toString());
					double valor = Double.parseDouble(ordenCompraDetalleIf.getValor().toString());
					double subtotal = cantidad * valor;	
					double porcentajeDescuentoEspecial = ordenCompraDetalleIf.getPorcentajeDescuentoEspecial().doubleValue() / 100;
					double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
					double otroImpuestoSubtotal = Double.parseDouble(ordenCompraDetalleIf.getOtroImpuesto().toString());
					double descuentoSubtotal = Double.parseDouble(ordenCompraDetalleIf.getDescuentoAgenciaCompra().toString());
					double porcentajeBonificacion = Double.parseDouble(ordenCompraDetalleIf.getPorcentajeDescuentosVariosCompra().toString());
					double bonificacionSubTotal = (subtotal - descuentoEspecial) * (porcentajeBonificacion / 100D);
					double iva = Double.parseDouble(ordenCompraDetalleIf.getIva().toString());
					double ice = Double.parseDouble(ordenCompraDetalleIf.getIce().toString());
					
					//Necesito saber si la orden viene de un presupuesto detalle con Negociacion Directa
					if(ordenCompraDetalleIf.getPorcentajeNegociacionDirecta() != null 
							&& ordenCompraDetalleIf.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(0)) == 1){
						//calculo el porcentaje para deducirlo a todos los valores
						double porcentajeND = 1 - (ordenCompraDetalleIf.getPorcentajeNegociacionDirecta().doubleValue()/100);
						valor = valor * porcentajeND;
						subtotal = cantidad * valor;
						descuentoEspecial = descuentoEspecial * porcentajeND;
						otroImpuestoSubtotal = otroImpuestoSubtotal * porcentajeND;
						descuentoSubtotal = descuentoSubtotal * porcentajeND;
						bonificacionSubTotal = bonificacionSubTotal * porcentajeND;
						iva = iva * porcentajeND;
						ice = ice * porcentajeND;					
					}
					
					double retencion_fuente = (subtotal - descuentoEspecial - descuentoSubtotal - bonificacionSubTotal) * (porcentaje_retencion_fuente / 100);
					double retencion_iva = (iva) * porcentaje_retencion_iva / 100;
					subtotal = subtotal - descuentoEspecial - descuentoSubtotal - bonificacionSubTotal + iva + ice + otroImpuestoSubtotal;
					
					//Agregar compraDetalle a la coleccion
					CompraDetalleData data = new CompraDetalleData();
					data.setProductoId(productoIf.getId());
					data.setCantidad(Long.valueOf(cantidad));
					data.setValor(BigDecimal.valueOf(Double.valueOf(valor)));
					data.setDescuento(BigDecimal.valueOf(Double.valueOf(descuentoSubtotal)));
					data.setPorcentajeDescuentosVarios(ordenCompraDetalleIf.getPorcentajeDescuentosVariosCompra());
					data.setPorcentajeDescuentoEspecial(ordenCompraDetalleIf.getPorcentajeDescuentoEspecial());
					data.setIva(BigDecimal.valueOf(Double.valueOf(iva)));
					data.setIce(BigDecimal.valueOf(Double.valueOf(ice)));
					data.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf(otroImpuestoSubtotal)));
					data.setIdSriAir(sriAir!=null?sriAir.getId():null);
					data.setSriIvaRetencionId(sriIvaRetencion!=null?sriIvaRetencion.getId():null);
					
					String concepto = ordenCompraDetalleIf.getDescripcion();
					if(ordenCompraDetalleIf.getFechaPublicacion() != null){
						String fechaCompleta = Utilitarios.getFechaDiaSemanaDiaMesAnioUppercase(ordenCompraDetalleIf.getFechaPublicacion());
						concepto = concepto + "\n\nPUBLICACIÓN: " + fechaCompleta + "\n";
					}
					data.setDescripcion(concepto);
					
					// Si es así ingreso el detalle de la compra en la tabla
					compraDetalleColeccion.add(data);
					Vector<String> fila = new Vector<String>();
					String descripcionProducto = getDescripcionProducto(genericosMap,presentacionesMap,lineasMap,
							modelosMap,coloresMap,productoIf);
					
					fila.add(descripcionProducto);
					fila.add(formatoDecimal.format(cantidad));
					fila.add(String.valueOf(valor));
					fila.add(formatoDecimal.format(descuentoEspecial));
					fila.add(formatoDecimal.format(descuentoSubtotal));
					fila.add(formatoDecimal.format(bonificacionSubTotal));
					fila.add(formatoDecimal.format(iva));
					fila.add(formatoDecimal.format(retencion_fuente + retencion_iva));
					fila.add(formatoDecimal.format(ice));
					fila.add(formatoDecimal.format(otroImpuestoSubtotal));
					fila.add(formatoDecimal.format(subtotal));
					tableModel.addRow(fila);
				}
				
				actualizarTotales();
			
			} else {
				SpiritAlert.createAlert("No se puede obtener el detalle de la Orden de Compra", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la carga del detalle desde Orden de Compra", SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error General en la carga del detalle desde orden de Compra", SpiritAlert.ERROR);
		}
	}
	
	private void agregarCompraDetalle() {
		try {
			Vector<String> fila = new Vector<String>();
			String strCantidad = getTxtCantidad().getText().trim();
			String strValor = Utilitarios.removeDecimalFormat(getTxtValor().getText()).trim();
			String strPorcentajeDescuentoEspecial = Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoEspecial().getText()).trim();
			String strDescuento = Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoAgencia().getText()).trim();
			String strPorcentajeBonificacion = Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentosVarios().getText()).trim();
			String strOtroImpuesto = Utilitarios.removeDecimalFormat(getTxtPorcentajeOtroImpuesto().getText()).trim();
			
			if ("".equals(strPorcentajeDescuentoEspecial))
				strPorcentajeDescuentoEspecial = "0";
			
			if ("".equals(strDescuento))
				strDescuento = "0";
			
			if ("".equals(strPorcentajeBonificacion))
				strPorcentajeBonificacion = "0";
			
			if ("".equals(strOtroImpuesto))
				strOtroImpuesto = "0";
			
			if ( documentoIf == null ){
				SpiritAlert.createAlert("Debe elegir un documento !!", SpiritAlert.INFORMATION);
				return;
			}	
			
			if (validarCompraDetalle(CompraModel.AGREGAR, documentoIf.getBonificacion())) {				
				long cantidad = Long.parseLong(strCantidad);
				double valor = Double.parseDouble(strValor);
				double subtotal = cantidad * valor;
				double porcentajeDescuentoEspecial = Double.parseDouble(strPorcentajeDescuentoEspecial) / 100D;
				double descuento = Double.parseDouble(strDescuento) / 100D;
				double porcentajeBonificacion = Double.parseDouble(strPorcentajeBonificacion) / 100D;
				double otroImpuesto = Double.parseDouble(strOtroImpuesto) / 100D;
				double otroImpuestoSubtotal = subtotal * otroImpuesto;
				double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
				double descuentoSubtotal = (subtotal - descuentoEspecial) * descuento;
				double bonificacionSubtotal = (subtotal - descuentoEspecial) * porcentajeBonificacion;
				double iva = calcularIVA(subtotal, descuentoEspecial, descuentoSubtotal, bonificacionSubtotal);
				double ice = calcularICE(subtotal, descuentoEspecial, descuentoSubtotal, bonificacionSubtotal);
				SriAirIf sriAir = (SriAirIf) getCmbRetencionRenta().getSelectedItem();
				double porcentaje_retencion_fuente = sriAir.getPorcentaje().doubleValue();
				double retencion_fuente = (subtotal - descuentoEspecial - descuentoSubtotal - bonificacionSubtotal) * porcentaje_retencion_fuente / 100;
				SriIvaRetencionIf sriIvaRetencion = (SriIvaRetencionIf) getCmbRetencionIva().getSelectedItem();
				double porcentaje_retencion_iva = 0D;
				
				if (sriIvaRetencion != null)
					porcentaje_retencion_iva = sriIvaRetencion.getPorcentaje().doubleValue();
				
				double retencion_iva = (iva) * porcentaje_retencion_iva / 100;
				subtotal = subtotal - descuentoEspecial - descuentoSubtotal - bonificacionSubtotal + iva + ice + otroImpuestoSubtotal;
				
				// Agregar compraDetalle a la coleccion
				CompraDetalleData data = new CompraDetalleData();
				data.setDocumentoId(documentoIf.getId());
				data.setProductoId(productoIf.getId());
				data.setCantidad(Long.valueOf(cantidad));
				data.setValor(BigDecimal.valueOf(Double.valueOf(valor)));
				data.setDescuento(BigDecimal.valueOf(Double.valueOf(descuentoSubtotal)));
				data.setPorcentajeDescuentosVarios(BigDecimal.valueOf(Double.parseDouble(strPorcentajeBonificacion)));
				data.setPorcentajeDescuentoEspecial(BigDecimal.valueOf(Double.parseDouble(strPorcentajeDescuentoEspecial)));
				data.setIva(BigDecimal.valueOf(Double.valueOf(iva)));
				data.setIce(BigDecimal.valueOf(Double.valueOf(ice)));
				data.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf(otroImpuestoSubtotal)));
				data.setIdSriAir(sriAir.getId());
				if (sriIvaRetencion != null)
					data.setSriIvaRetencionId(sriIvaRetencion.getId());
				if(getTxtDescripcion().getText() != null)
					data.setDescripcion(getTxtDescripcion().getText());
				// Si es así ingreso el detalle de la compra en la tabla
				compraDetalleColeccion.add(data);
				// Agrega información a la tabla visual para el usuario.
				String descripcionProducto = getDescripcionProducto(genericosMap,presentacionesMap,lineasMap,
						modelosMap,coloresMap,productoIf);
				fila.add(descripcionProducto);
				fila.add(formatoDecimal.format(cantidad));
				fila.add(String.valueOf(valor));
				fila.add(formatoDecimal.format(descuentoEspecial));
				fila.add(formatoDecimal.format(descuentoSubtotal));
				fila.add(formatoDecimal.format(bonificacionSubtotal));
				fila.add(formatoDecimal.format(iva));
				fila.add(formatoDecimal.format(retencion_fuente + retencion_iva));
				fila.add(formatoDecimal.format(ice));
				fila.add(formatoDecimal.format(otroImpuestoSubtotal));
				fila.add(formatoDecimal.format(subtotal));
				productosLocal.put(productoIf.getId(), PRODUCTO);
				DefaultTableModel tableModel = (DefaultTableModel) getTblCompraDetalle().getModel();
				tableModel.addRow(fila);
				verificarNombreProducto(mapaProductoNombre, productoIf.getId(),genericoIf);
				cleanCompraDetalle();
				productoIf = null;
				genericoIf = null;
			}
			
			actualizarTotales();
		} catch (Exception e) {
			SpiritAlert.createAlert("Ocurrió un error al agregar el detalle de la Compra !!!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private Map mapearGenericos() {
		Map genericosMap = new HashMap();
		
		try {
			Iterator genericosIterator = SessionServiceLocator.getGenericoSessionService().getGenericoList().iterator();
			while (genericosIterator.hasNext()) {
				GenericoIf generico = (GenericoIf) genericosIterator.next();
				genericosMap.put(generico.getId(), generico);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return genericosMap;
	}
	
	private Map mapearProductos() {
		Map productosMap = new HashMap();
		
		try {
			Iterator productosIterator = SessionServiceLocator.getProductoSessionService().getProductoList().iterator();
			while (productosIterator.hasNext()) {
				ProductoIf producto = (ProductoIf) productosIterator.next();
				productosMap.put(producto.getId(), producto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return productosMap;
	}
	
	private Map mapearPresentaciones() {
		Map presentacionesMap = new HashMap();
		
		try {
			Iterator presentacionesIterator = SessionServiceLocator.getPresentacionSessionService().getPresentacionList().iterator();
			while (presentacionesIterator.hasNext()) {
				PresentacionIf presentacion = (PresentacionIf) presentacionesIterator.next();
				presentacionesMap.put(presentacion.getId(), presentacion);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return presentacionesMap;
	}
	
	private Map mapearModelos() {
		Map modelosMap = new HashMap();
		
		try {
			Iterator modelosIterator = SessionServiceLocator.getModeloSessionService().getModeloList().iterator();
			while (modelosIterator.hasNext()) {
				ModeloIf modelo = (ModeloIf) modelosIterator.next();
				modelosMap.put(modelo.getId(), modelo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return modelosMap;
	}
	
	private Map mapearLineas() {
		Map lineasMap = new HashMap();
		
		try {
			Iterator lineasIterator = SessionServiceLocator.getLineaSessionService().getLineaList().iterator();
			while (lineasIterator.hasNext()) {
				LineaIf linea = (LineaIf) lineasIterator.next();
				lineasMap.put(linea.getId(), linea);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return lineasMap;
	}
	
	private Map mapearColores() {
		Map coloresMap = new HashMap();
		
		try {
			Iterator coloresIterator = SessionServiceLocator.getColorSessionService().getColorList().iterator();
			while (coloresIterator.hasNext()) {
				ColorIf color = (ColorIf) coloresIterator.next();
				coloresMap.put(color.getId(), color);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return coloresMap;
	}
	
	public static  String verificarNombreProducto(Map<Long, String> mapaProductoNombre,Long productoId,GenericoIf genericoIf) throws GenericBusinessException{
		String nombre = "";
		if ( mapaProductoNombre.containsKey(productoId) ){
			nombre = mapaProductoNombre.get(productoId);
			if ( genericoIf != null && !genericoIf.getNombre().equals(nombre) ){
				nombre = SessionServiceLocator.getProductoSessionService().getNombreProductoByProductoId(productoId);
				mapaProductoNombre.put(productoId, nombre);
			}
		} else {
			if ( genericoIf == null ){
				nombre = SessionServiceLocator.getProductoSessionService().getNombreProductoByProductoId(productoId);
				mapaProductoNombre.put(productoId, nombre);
			} else {
				mapaProductoNombre.put(productoId, genericoIf.getNombre());
			}
		}
		return nombre;
	}
	
	private double calcularIVA(double subtotal, double descuentoEspecial, double descuentoSubtotal, double bonificacionSubtotal) {
		double iva = 0.0;
		
		if ("S".equals(genericoIf.getCobraIva()) && !documentoIf.getCodigo().equals("COMI") && !documentoIf.getCodigo().equals("CIIN"))
			iva = (subtotal - descuentoEspecial - descuentoSubtotal - bonificacionSubtotal) * IVA;
		else
			iva = 0.0;
		
		return iva;
	}
	
	private double calcularICE(double subtotal, double descuentoEspecial, double descuentoSubtotal, double bonificacionSubtotal) {
		double ice = 0.0;
		
		if ("S".equals(genericoIf.getCobraIce()))
			ice = (subtotal - descuentoEspecial - descuentoSubtotal - bonificacionSubtotal) * (genericoIf.getIce().doubleValue() / 100D);
		else
			ice = 0.0;
		
		return ice;
	}
	
	private void actualizarCompraDetalle() {
		DefaultTableModel tableModel = (DefaultTableModel) getTblCompraDetalle().getModel();
		try {
			if (getTblCompraDetalle().getSelectedRow() == -1) {
				SpiritAlert.createAlert("Por favor, elija la información que desea actualizar !!!", SpiritAlert.WARNING);
				
			} else {
				if ( documentoIf == null ){
					SpiritAlert.createAlert("Debe elegir un documento !!", SpiritAlert.INFORMATION);
					return;
				}
				if (validarCompraDetalle(CompraModel.ACTUALIZAR, documentoIf.getBonificacion())) {
					// Copio la referencia que se debe actualizar
					CompraDetalleIf data = compraDetalleForUpdate;
					boolean verificacionAcutlizacionDesdeDetalleGasto = 
						verificarActualizacionCompraDetalleGasto(data,productoIf.getId());
					if ( verificacionAcutlizacionDesdeDetalleGasto ) {
						String strCantidad = getTxtCantidad().getText();
						String strValor = Utilitarios.removeDecimalFormat(getTxtValor().getText());
						String strPorcentajeDescuentoEspecial = Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoEspecial().getText());
						String strDescuento = Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoAgencia().getText());
						String strPorcentajeBonificacion = Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentosVarios().getText());
						String strOtroImpuesto = Utilitarios.removeDecimalFormat(getTxtPorcentajeOtroImpuesto().getText());
						SriAirIf sriAir = (SriAirIf) getCmbRetencionRenta().getSelectedItem();
						double porcentaje_retencion_fuente = sriAir.getPorcentaje().doubleValue();
						SriIvaRetencionIf sriIvaRetencion = (SriIvaRetencionIf) getCmbRetencionIva().getSelectedItem();
						double porcentaje_retencion_iva = 0D;
						if (sriIvaRetencion != null)
							porcentaje_retencion_iva = sriIvaRetencion.getPorcentaje().doubleValue();
						long cantidad = Long.parseLong(strCantidad);
						double valor = Double.parseDouble(strValor);
						double subtotal = cantidad * valor;
						double porcentajeDescuentoEspecial = Double.parseDouble(strPorcentajeDescuentoEspecial) / 100D;
						double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
						double descuento = Double.parseDouble(strDescuento) / 100D;
						double porcentajeBonificacion = Double.parseDouble(strPorcentajeBonificacion) / 100D;
						double otroImpuesto = Double.parseDouble(strOtroImpuesto) / 100D;
						double otroImpuestoSubtotal = subtotal * otroImpuesto;
						double descuentoSubtotal = (subtotal - descuentoEspecial) * descuento;
						double bonificacionSubtotal = (subtotal - descuentoEspecial) * porcentajeBonificacion;
						double iva = calcularIVA(subtotal, descuentoEspecial, descuentoSubtotal, bonificacionSubtotal);
						double ice = calcularICE(subtotal, descuentoEspecial, descuentoSubtotal, bonificacionSubtotal);
						double retencion_fuente = (subtotal - descuentoEspecial - descuentoSubtotal - bonificacionSubtotal) * porcentaje_retencion_fuente / 100;
						double retencion_iva = (iva) * porcentaje_retencion_iva / 100;
						subtotal = subtotal - descuentoEspecial - descuentoSubtotal - bonificacionSubtotal + iva + ice + otroImpuestoSubtotal;
						data.setDocumentoId(documentoIf.getId());
						data.setProductoId(productoIf.getId());
						data.setCantidad(Long.valueOf(cantidad));
						data.setValor(BigDecimal.valueOf(Double.valueOf(valor)));
						data.setDescuento(BigDecimal.valueOf(Double.valueOf(descuentoSubtotal)));
						data.setPorcentajeDescuentosVarios(BigDecimal.valueOf(Double.parseDouble(strPorcentajeBonificacion)));
						data.setPorcentajeDescuentoEspecial(BigDecimal.valueOf(Double.parseDouble(strPorcentajeDescuentoEspecial)));
						data.setIva(BigDecimal.valueOf(Double.valueOf(iva)));
						data.setIce(BigDecimal.valueOf(Double.valueOf(ice)));
						data.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf(otroImpuestoSubtotal)));
						data.setIdSriAir(sriAir.getId());
						if (sriIvaRetencion != null)
							data.setSriIvaRetencionId(sriIvaRetencion.getId());
						if(getTxtDescripcion().getText() != null)
							data.setDescripcion(getTxtDescripcion().getText());
						
						int filaSeleccionada = getTblCompraDetalle().convertRowIndexToModel(getTblCompraDetalle().getSelectedRow() );
						compraDetalleColeccion.set(filaSeleccionada, data);
						// actualizo informacion a la tabla visual para el usuario.
						String descripcionProducto = getDescripcionProducto(genericosMap,presentacionesMap,lineasMap,
								modelosMap,coloresMap,productoIf);
						tableModel.setValueAt(descripcionProducto, filaSeleccionada, 0);
						tableModel.setValueAt(formatoDecimal.format(cantidad), filaSeleccionada, 1);
						tableModel.setValueAt(String.valueOf(valor), filaSeleccionada, 2);
						tableModel.setValueAt(formatoDecimal.format(descuentoEspecial), filaSeleccionada, 3);
						tableModel.setValueAt(formatoDecimal.format(descuentoSubtotal), filaSeleccionada, 4);
						tableModel.setValueAt(formatoDecimal.format(bonificacionSubtotal), filaSeleccionada, 5);
						tableModel.setValueAt(formatoDecimal.format(iva), filaSeleccionada, 6);
						tableModel.setValueAt(formatoDecimal.format(retencion_fuente + retencion_iva), filaSeleccionada, 7);
						tableModel.setValueAt(formatoDecimal.format(ice), filaSeleccionada, 8);
						tableModel.setValueAt(formatoDecimal.format(otroImpuestoSubtotal), filaSeleccionada, 9);
						tableModel.setValueAt(formatoDecimal.format(subtotal), filaSeleccionada, 10);
						
						if (productosLocal.get(productoIf.getId()) == null) {
							productosLocal.remove(idProductoTemp);
							productosLocal.put(productoIf.getId(), "P");
						}
	
						verificarNombreProducto(mapaProductoNombre, productoIf.getId(),genericoIf);
						actualizarTotales();
						cleanCompraDetalle();
						productoIf = null;
						genericoIf = null;
					}
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private boolean verificarActualizacionCompraDetalleGasto(CompraDetalleIf compraDetalle,Long nuevoProductoId){

		Map<Long, CompraDetalleGastoClase> mapaDetalleGasto = compraGastoClase.getMapaCompraDetalleGasto();
		for ( Long gastoId : mapaDetalleGasto.keySet() ){
			CompraDetalleGastoClase cdgc = mapaDetalleGasto.get(gastoId);
			Map<CompraDetalleIf, CompraDetalleGastoIf> mapaCompraDetalleGasto = cdgc.getDetalle();
			Iterator<CompraDetalleIf> itCd = mapaCompraDetalleGasto.keySet().iterator(); 
			while ( itCd.hasNext()  ){
				CompraDetalleIf cd = itCd.next();
				if ( compraDetalle == cd  ){
					CompraDetalleGastoIf cdg = mapaCompraDetalleGasto.get(cd);
					if ( !nuevoProductoId.equals(cd.getProductoId()) && cdg.getValor().doubleValue() > 0 ){
						return confimarAcutalizacionCompraDetalleGasto();
					}
				}
			}
		}
		return true;
	}
	
	private boolean confimarAcutalizacionCompraDetalleGasto(){
		Object[] options = {"Si","No"}; 
		int opcion = JOptionPane.showOptionDialog(
				null,"Está realizando actualizacion del producto del detalle" +
						"\nLos detalles de gasto que esten relacionados a este detalle tambien se actualizará ." +
						"\n¿Desea continuar? ", "Confirmación"
				,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE
				,null,options,"No");
		if ( opcion == JOptionPane.YES_OPTION )
			return true;
		return false;
	}
	
	private boolean validarCompraDetalle(String tipoOperacion, String esBonificacion) {
		if (genericoIf.getLlevaInventario() != null && genericoIf.getLlevaInventario().equals("S")) {
			if (!documentoIf.getCodigo().equals("CIIN") && !documentoIf.getCodigo().equals("COIN")) {
				SpiritAlert.createAlert("Documento no corresponde a compra de inventario\nSi continua no se registrarán los gastos asociados!!!", SpiritAlert.WARNING);
				getJtpCompras().setSelectedIndex(1);
				getBtnBuscarProducto().grabFocus();
				return false;
			}
		}
		
		if ("".equals(getTxtProducto().getText().trim())) {
			SpiritAlert.createAlert("Debe ingresar un producto primero !!!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(1);
			getBtnBuscarProducto().grabFocus();
			return false;
		}
		
		/*boolean productoRepetido = false;
		
		if (compraDetalleColeccion.size() > 0) {
			for (int i = 0; i < compraDetalleColeccion.size(); i++) {
				if (productoIf.getId().compareTo(((CompraDetalleIf) compraDetalleColeccion.get(i)).getProductoId()) == 0)
					productoRepetido = true;
			}
		}
		
		if (productoRepetido && CompraModel.AGREGAR.equals(tipoOperacion)) {
			SpiritAlert.createAlert("El producto seleccionado ya ha sido agregado previamente al detalle!!!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(1);
			getBtnBuscarProducto().grabFocus();
			return false;
		}*/
		
		if ("".equals(getTxtCantidad().getText().trim())) {
			SpiritAlert.createAlert("Debe ingresar la cantidad !!!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(1);
			getTxtCantidad().grabFocus();
			return false;
		} 
		
		if (Double.parseDouble(getTxtCantidad().getText()) <= 0D) {
			SpiritAlert.createAlert("La cantidad debe ser mayor que 0 !!!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(1);
			getTxtCantidad().grabFocus();
			return false;
		} 
		
		if ("".equals(Utilitarios.removeDecimalFormat(getTxtValor().getText()).trim())) {
			SpiritAlert.createAlert("Debe ingresar el valor !!!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(1);
			getTxtValor().grabFocus();
			return false;
		} 
		
		if ("S".equals(esBonificacion) == false && Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtValor().getText())) <= 0D) {
			SpiritAlert.createAlert("El valor debe ser mayor que 0 !!!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(1);
			getTxtValor().grabFocus();
			return false;
		}/*
		* else if (productosLocal.get(productoIf.getId()) != null &&
		* CompraModel.AGREGAR.equals(tipoOperacion)) {
		* SpiritAlert.createAlert("El producto ya se encuentra en el
		* detalle"); return false; }
		*/
		
		if (getCmbRetencionRenta().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el porcentaje de retencion renta!!!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(1);
			getCmbRetencionRenta().grabFocus();
			return false;
		}
		
		if (getCmbRetencionIva().isEnabled() && getCmbRetencionIva().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el porcentaje de retencion iva!!!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(1);
			getCmbRetencionIva().grabFocus();
		}
		
		return true;
	}
	
	private void enableCompraDetalleForUpdate(CompraDetalleIf compraDetalle) {
		try {
			productoIf = SessionServiceLocator.getProductoSessionService().getProducto(compraDetalle.getProductoId());
			cargarComboRetencion();
			genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
			idProductoTemp = productoIf.getId();
			codigoProductoTemp = productoIf.getCodigo();
			
			if(compraDetalle.getDocumentoId() != null)
				getCmbDocumento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocumento(),compraDetalle.getDocumentoId()));
			else
				getCmbDocumento().setSelectedItem(null);
			
			getCmbDocumento().validate();
			getCmbDocumento().repaint();
			setProductoDetalle(productoIf);
			Long cantidad = compraDetalle.getCantidad();
			getTxtCantidad().setText(compraDetalle.getCantidad().toString());
			Double valor = compraDetalle.getValor().doubleValue();			
			getTxtValor().setText(String.valueOf(valor));
			double subtotal = valor * cantidad;
			
			double porcentajeDescuentoEspecial = compraDetalle.getPorcentajeDescuentoEspecial().doubleValue() / 100;
			double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
			getTxtPorcentajeDescuentoEspecial().setText(formatoDecimal.format(compraDetalle.getPorcentajeDescuentoEspecial()));
			
			Double descuento = 0D;
			if(compraDetalle.getDescuento().compareTo(new BigDecimal(0)) == 1){
				descuento = (compraDetalle.getDescuento().doubleValue() * 100D) / (subtotal - descuentoEspecial);
			}
			
			getTxtPorcentajeDescuentoAgencia().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuento)));
			getTxtPorcentajeDescuentosVarios().setText(formatoDecimal.format(compraDetalle.getPorcentajeDescuentosVarios()));
			String strOtroImpuesto = compraDetalle.getOtroImpuesto().toString();
			Double otroImpuesto = (Double.parseDouble(strOtroImpuesto) * 100D) / subtotal;
			getTxtPorcentajeOtroImpuesto().setText(String.valueOf(otroImpuesto.intValue()));
			
			if(compraDetalle.getDescripcion() != null)
				getTxtDescripcion().setText(compraDetalle.getDescripcion());
			else
				getTxtDescripcion().setText("");
			
			getTxtCantidad().setEnabled(true);
			getTxtCantidad().setEditable(true);
			getTxtValor().setEnabled(true);
			getTxtValor().setEditable(true);
			getTxtPorcentajeDescuentoEspecial().setEnabled(true);
			getTxtPorcentajeDescuentoEspecial().setEditable(true);
			getTxtPorcentajeDescuentoAgencia().setEnabled(true);
			getTxtPorcentajeDescuentoAgencia().setEditable(true);
			getTxtPorcentajeDescuentosVarios().setEnabled(true);
			getTxtPorcentajeDescuentosVarios().setEditable(true);
			getTxtPorcentajeOtroImpuesto().setEnabled(true);
			getTxtPorcentajeOtroImpuesto().setEditable(true);
			getCmbRetencionRenta().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionRenta(),compraDetalle.getIdSriAir()));
			getCmbRetencionRenta().validate();
			getCmbRetencionRenta().repaint();
			
			if (compraDetalle.getSriIvaRetencionId() != null)
				getCmbRetencionIva().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetencionIva(),compraDetalle.getSriIvaRetencionId()));
			else
				getCmbRetencionIva().setSelectedItem(null);
			
			getCmbRetencionIva().validate();
			getCmbRetencionIva().repaint();
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void eliminarCompraDetalle() {
		if (getTblCompraDetalle().getSelectedRow() != -1) {
			DefaultTableModel model = (DefaultTableModel) getTblCompraDetalle().getModel();
			
			int filaSeleccionada = getTblCompraDetalle().convertRowIndexToModel(getTblCompraDetalle().getSelectedRow() );
			CompraDetalleIf compraDetalleTemp = (CompraDetalleIf) compraDetalleColeccion.get(filaSeleccionada);
			
			boolean verificarRelacionGastoComptraDetalle
				= verificarEliminacionCompraDetalleGastoDesdeCompraDetalle(compraDetalleTemp);
			if (verificarRelacionGastoComptraDetalle){
				eliminarCompraDetalleGasto(compraDetalleTemp);
				
				if (compraDetalleTemp.getId() != null)
					compraDetalleEliminadas.add(compraDetalleTemp);
	
				compraDetalleColeccion.remove(filaSeleccionada);
				model.removeRow(filaSeleccionada);
			}
		} else {
			SpiritAlert.createAlert("Debe elegir el detalle de la compra a eliminar !!!", SpiritAlert.WARNING);
		}
		
		cleanCompraDetalle();
		actualizarTotales();
	}
	
	private void eliminarCompraDetalleGasto(CompraDetalleIf compraDetalle){
		
		ArrayList<CompraDetalleGastoIf> listaEliminacionDetalle = compraGastoClase.getListaEliminacionComprasDetalleGastos(); 
		Map<Long, CompraDetalleGastoClase> mapaDetalleGasto = compraGastoClase.getMapaCompraDetalleGasto();
		Map<Long, CompraGastoIf> mapaCompraGasto = compraGastoClase.getMapaComprasGastos();
		for ( Long gastoId : mapaDetalleGasto.keySet() ){
			CompraDetalleGastoClase cdgc = mapaDetalleGasto.get(gastoId);
			Map<CompraDetalleIf, CompraDetalleGastoIf> mapaCompraDetalleGasto = cdgc.getDetalle();
			Iterator<CompraDetalleIf> itCd = mapaCompraDetalleGasto.keySet().iterator(); 
			while ( itCd.hasNext()  ){
				CompraDetalleIf cd = itCd.next();
				if ( compraDetalle == cd  ){
					CompraDetalleGastoIf cdg = mapaCompraDetalleGasto.get(cd);
					if ( cdg.getValor().doubleValue() > 0D ){
						
						if ( cdg.getId() != null ){
							listaEliminacionDetalle.add(cdg);
						}
					}
					itCd.remove();
					
					//Le resto al gasto la cantidad del detalle eliminado
					CompraGastoIf cg = mapaCompraGasto.get(gastoId);
					Double valorGasto = cg.getValor().doubleValue(); 
					Double valorGastoDetalle = cdg.getValor().doubleValue();
					Double total = valorGasto - valorGastoDetalle;
					cg.setValor(new BigDecimal(total));
				}
			}
		}
		cargarTablaGasto();
	}
	
	private boolean verificarEliminacionCompraDetalleGastoDesdeCompraDetalle(CompraDetalleIf compraDetalle){
		Map<Long, CompraDetalleGastoClase> mapaDetalleGasto = compraGastoClase.getMapaCompraDetalleGasto();
		for ( Long gastoId : mapaDetalleGasto.keySet() ){
			CompraDetalleGastoClase cdgc = mapaDetalleGasto.get(gastoId);
			Map<CompraDetalleIf, CompraDetalleGastoIf> mapaCompraDetalleGasto = cdgc.getDetalle();
			for ( CompraDetalleIf cd : mapaCompraDetalleGasto.keySet() ){
				if (compraDetalle == cd  ){
					CompraDetalleGastoIf cdg = mapaCompraDetalleGasto.get(cd);
					if ( cdg.getValor().doubleValue() > 0D ){
						return confimarEliminacionDetalleGasto();	
					}
				}
			}	
		}
		return true;
	}
	
	private boolean confimarEliminacionDetalleGasto(){
		Object[] options = {"Si","No"}; 
		int opcion = JOptionPane.showOptionDialog(
				null,"Este detalle tiene valores de gasto(s) asociado(s)." +
					 "\nLo que implica la reducción del valor del gasto tambien."+
					 "\n¿Desea continuar con la eliminación? ", "Confirmación"
				,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE
				,null,options,"No");
		if ( opcion == JOptionPane.YES_OPTION )
			return true;
		return false;
	}
	
	private void cleanCompraDetalle() {
		getTxtProducto().setText("");
		getTxtCantidad().setText("");
		getTxtCantidad().setEnabled(false);
		getTxtCantidad().setEditable(false);
		getTxtValor().setText("");
		getTxtValor().setEnabled(false);
		getTxtValor().setEditable(false);
		getTxtPorcentajeDescuentoEspecial().setText("");
		getTxtPorcentajeDescuentoEspecial().setEnabled(false);
		getTxtPorcentajeDescuentoEspecial().setEditable(false);
		getTxtPorcentajeDescuentoAgencia().setText("");
		getTxtPorcentajeDescuentoAgencia().setEnabled(false);
		getTxtPorcentajeDescuentoAgencia().setEditable(false);
		getTxtPorcentajeDescuentosVarios().setText("");
		getTxtPorcentajeDescuentosVarios().setEnabled(false);
		getTxtPorcentajeDescuentosVarios().setEditable(false);
		getTxtPorcentajeOtroImpuesto().setText("");
		getTxtDescripcion().setText("");
		getTxtPorcentajeOtroImpuesto().setEnabled(false);
		getTxtPorcentajeOtroImpuesto().setEditable(false);
	}
	
	private void actualizarTotales() {
		DefaultTableModel tableModel = (DefaultTableModel) getTblCompraDetalle().getModel();
		
		totalValor = 0D;
		totalDescuentoEspecial = 0D;
		totalDescuento = 0D;
		totalBonificacion = 0D;
		totalRetencion = 0D;
		totalIVA = 0D;
		totalICE = 0D;
		totalOtrosImpuestos = 0D;
		grandTotal = 0D;
		
		try {	
			for (int i=0; i < compraDetalleColeccion.size(); i++) {
				CompraDetalleIf compraDetalle = compraDetalleColeccion.get(i);
				Long strCantidad = compraDetalle.getCantidad();
				double strValor = compraDetalle.getValor().doubleValue();
				double subtotal = strValor * strCantidad;
				double porcentajeDescuentoEspecial = compraDetalle.getPorcentajeDescuentoEspecial().doubleValue() / 100;
				double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
				double strDescuento = compraDetalle.getDescuento().doubleValue();				
				double strPorcentajeBonificacion = compraDetalle.getPorcentajeDescuentosVarios().doubleValue();
				double bonificacion = (subtotal - descuentoEspecial) * (strPorcentajeBonificacion / 100D);
				double strIVA = compraDetalle.getIva().doubleValue();
				String strRetencion = Utilitarios.removeDecimalFormat(tableModel.getValueAt(i, 7).toString().trim());
				double strICE = compraDetalle.getIce().doubleValue();
				double strOtrosImpuestos = compraDetalle.getOtroImpuesto().doubleValue();
				double strGrandTotal = subtotal - descuentoEspecial - strDescuento - bonificacion + strIVA + strICE + strOtrosImpuestos;
				
				totalValor += subtotal;
				totalDescuentoEspecial += descuentoEspecial;
				totalDescuento += strDescuento;
				totalBonificacion += bonificacion;
				totalIVA += strIVA;
				totalRetencion += Double.parseDouble(strRetencion);
				totalICE += strICE;
				totalOtrosImpuestos += strOtrosImpuestos;
				grandTotal += strGrandTotal;
			}
			
			getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalValor)));
			getTxtDescuentoEspecialFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalDescuentoEspecial)));
			getTxtDescuentoAgenciaFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalDescuento)));
			getTxtDescuentosVariosFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalBonificacion)));
			getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalIVA)));
			getTxtRetencionFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalRetencion)));
			getTxtICEFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalICE)));
			getTxtOtroImpuestoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalOtrosImpuestos)));
			getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(grandTotal)));
		
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("¡ Ocurrió un error al actualizar los totales de la Compra !", SpiritAlert.ERROR);
		}
	}
	
	public void cleanTableDetalle() {
		DefaultTableModel model = (DefaultTableModel) this.getTblCompraDetalle().getModel();
		for (int i = this.getTblCompraDetalle().getRowCount(); i > 0; --i) {
			if (compraDetalleColeccion.size() > 0) {
				CompraDetalleIf compraDetalleEliminado = (CompraDetalleIf) compraDetalleColeccion.get(i-1);
				if (compraDetalleEliminado.getId() != null)
					compraDetalleEliminadas.add(compraDetalleEliminado);
			}
			model.removeRow(i - 1);
		}
			
		compraDetalleColeccion.clear();
		actualizarTotales();
		cleanCompraDetalle();
		cleanTablaGasto();
		this.repaint();
	}
	
	public void cleanTableRetencion() {
		limpiarTabla(getTblRetenciones());
		
		compraRetencionColeccion.clear();
	}
	
	public void save() {
		if (validateFields()) {
			try {
				CompraIf compra = registrarCompra();
				
				OficinaIf oficina = (OficinaIf)getCmbOficina().getSelectedItem();
				compra = SessionServiceLocator.getCompraSessionService().procesarCompra(
						compra, compraDetalleColeccion, Parametros.getIdEmpresa(), 
						oficina.getId(), idTareaCompra, compraRetencionColeccion,
						tipoDocumentoIf.getId(), (UsuarioIf) Parametros.getUsuarioIf(),
						compraGastoClase, ordenesDataVector);
				SpiritAlert.createAlert("Compra guardada con \u00e9xito", SpiritAlert.INFORMATION);
								
				//Si la compra fue hecha a partir de una orden de compra, ponemos la orden como INGRESADA
				Iterator<OrdenAsociadaIf> ordenAsociadaIt = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(compra.getId()).iterator();
				while (ordenAsociadaIt.hasNext()) {
					OrdenAsociadaIf ordenAsociada = ordenAsociadaIt.next();
					if (ordenAsociada.getTipoOrden().equals("OC")) {
						OrdenCompraIf ordenCompra = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(ordenAsociada.getOrdenId());
						ordenCompra.setEstado("G");
						SessionServiceLocator.getOrdenCompraSessionService().saveOrdenCompra(ordenCompra);
					} else {
						OrdenMedioIf ordenMedio = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedio(ordenAsociada.getOrdenId());
						ordenMedio.setEstado("I");
						SessionServiceLocator.getOrdenMedioSessionService().saveOrdenMedio(ordenMedio);
					}
				}
				//Doy la opccion de imprimir el reporte del asiento
				this.compra = compra;
				generarReporteAsiento();
				this.clean();
				this.showSaveMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);				
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error general al guardar la Compra",SpiritAlert.ERROR);				
			}
		}
	}
		
	public void update() {
		Object[] options = {"Si","No"}; 
		int opcion = JOptionPane.showOptionDialog(null,"¿Desea actualizar la factura? ", "Confirmación"
				,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE
				,null,options,"No");
		if ( opcion == JOptionPane.YES_OPTION ) {
			if (validateFields()) {
				try {
					CompraIf compraAnterior = registrarCompraAnterior();
					CompraIf compra = registrarCompraForUpdate();
					boolean exito = false;
					String msj = "";

					OficinaIf oficina = (OficinaIf)getCmbOficina().getSelectedItem();
					
					if (compra.getEstado().equalsIgnoreCase("L"))
						exito = SessionServiceLocator.getCarteraSessionService().actualizarCompra(compra, compraDetalleColeccion, compraDetalleEliminadas, idTareaCompra, compraRetencionColeccion, compraRetencionEliminadaColeccion, Parametros.getIdEmpresa(), oficina.getId(), (UsuarioIf) Parametros.getUsuarioIf(), ordenesDataVector);
					else if (compra.getEstado().equalsIgnoreCase(ESTADO_NORMAL_REEMBOLSO)) {
						compraAnterior.setEstado(ESTADO_NORMAL_REEMBOLSO);
						compraAnterior.setIdSriSustentoTributario(((SriSustentoTributarioIf) getCmbTipoSustentoTributario().getSelectedItem()).getId());
						SessionServiceLocator.getCompraSessionService().saveCompra(compraAnterior);
						exito = true;
					} else if (compra.getEstado().equalsIgnoreCase(ESTADO_REEMBOLSO_NORMAL)) {
						compraAnterior.setEstado(ESTADO_REEMBOLSO_NORMAL);
						compraAnterior.setIdSriSustentoTributario(((SriSustentoTributarioIf) getCmbTipoSustentoTributario().getSelectedItem()).getId());
						SessionServiceLocator.getCompraSessionService().saveCompra(compraAnterior);
						exito = true;
					} else {
						boolean actualizarCodigoCartera = true;
						java.sql.Date fechaCompraAnterior = compraAnterior.getFecha();
						int yearCompraAnterior = fechaCompraAnterior.getYear() + 1900;
						int monthCompraAnterior = fechaCompraAnterior.getMonth() + 1;
						java.sql.Date fechaCompra = compra.getFecha();
						int yearCompra = fechaCompra.getYear() + 1900;
						int monthCompra = fechaCompra.getMonth() + 1;
						if (yearCompraAnterior == yearCompra && monthCompraAnterior == monthCompra) {
							compra.setCodigo(compraAnterior.getCodigo());
							actualizarCodigoCartera = false;
						}

						Map parameterMap = new HashMap();
						parameterMap.put("tipodocumentoId", compraAnterior.getTipodocumentoId());
						parameterMap.put("referenciaId", compraAnterior.getId());
						Iterator it = SessionServiceLocator.getCarteraSessionService().findCarteraByQuery(parameterMap).iterator();
						if (it.hasNext() || compra.getEstado().equals(ESTADO_INACTIVA) || (compraAnterior.getEstado().equals(ESTADO_INACTIVA) && compra.getEstado().equals(ESTADO_ACTIVA))) {
							CarteraIf carteraAnterior = (it.hasNext())?(CarteraIf) it.next():null;
							if (carteraAnterior == null)
								actualizarCodigoCartera = true;
							if (compra.getEstado().equals(ESTADO_INACTIVA) || (compraAnterior.getEstado().equals(ESTADO_INACTIVA) && compra.getEstado().equals(ESTADO_ACTIVA)) || (carteraAnterior != null && Utilitarios.redondeoValor(carteraAnterior.getValor().doubleValue()) == Utilitarios.redondeoValor(carteraAnterior.getSaldo().doubleValue()))){
								exito = SessionServiceLocator.getCompraSessionService().actualizarCompra(compra, compraDetalleColeccion, compraAnterior, carteraAnterior, compraDetalleEliminadas, 
										Parametros.getIdEmpresa(), oficina.getId(), idTareaCompra, (UsuarioIf) Parametros.getUsuarioIf(), 
										actualizarCodigoCartera,compraGastoClase, ordenesDataVector);
								//Cambio el estado de la orden de trabajo si se eligio una nueva orden, o si ya no hay orden.
								/*if (!compra.getEstado().equals(ESTADO_INACTIVA)) {
									if(ordenCompraIf != null && ordenCompraIfAnterior != null && 
											ordenCompraIf.getId().compareTo(ordenCompraIfAnterior.getId()) != 0){
										ordenCompraIf.setEstado("G");
										SessionServiceLocator.getOrdenCompraSessionService().saveOrdenCompra(ordenCompraIf);
										ordenCompraIfAnterior.setEstado("E");
										SessionServiceLocator.getOrdenCompraSessionService().saveOrdenCompra(ordenCompraIfAnterior);
									}else{
										if(ordenCompraIf != null){
											ordenCompraIf.setEstado("G");
											SessionServiceLocator.getOrdenCompraSessionService().saveOrdenCompra(ordenCompraIf);							
										}
										if(ordenCompraIfAnterior != null){
											ordenCompraIfAnterior.setEstado("E");
											SessionServiceLocator.getOrdenCompraSessionService().saveOrdenCompra(ordenCompraIfAnterior);
										}
									}
									if(ordenMedioIf != null && ordenMedioIfAnterior != null && 
											ordenMedioIf.getId().compareTo(ordenMedioIfAnterior.getId()) != 0){
										ordenMedioIf.setEstado("I");
										SessionServiceLocator.getOrdenMedioSessionService().saveOrdenMedio(ordenMedioIf);
										ordenMedioIfAnterior.setEstado("E");
										SessionServiceLocator.getOrdenMedioSessionService().saveOrdenMedio(ordenMedioIfAnterior);
									}else{
										if(ordenMedioIf != null){
											ordenMedioIf.setEstado("I");
											SessionServiceLocator.getOrdenMedioSessionService().saveOrdenMedio(ordenMedioIf);							
										}
										if(ordenMedioIfAnterior != null){
											ordenMedioIfAnterior.setEstado("E");
											SessionServiceLocator.getOrdenMedioSessionService().saveOrdenMedio(ordenMedioIfAnterior);
										}
									}*/
								}
							} else
								msj += "\nLa factura ha sido cancelada total o parcialmente.";
						}

					if (exito){
						SpiritAlert.createAlert("Compra actualizada con éxito", SpiritAlert.INFORMATION);
						clean();
						showSaveMode();
					} else
						SpiritAlert.createAlert("No se ha podido actualizar la compra!!!" + msj, SpiritAlert.WARNING);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
				} catch (Exception e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error general al actualizar la compra",SpiritAlert.ERROR);
				}
			}
		}
	}
	
	public void delete() {
		//DO NOTHING
	}
	
	public void authorize(){
		System.out.println("authorize");
	}
	
	public void report() {
		retencionReportList.clear();
		for(CompraRetencionIf compraRetencionIf : compraRetencionColeccion){
			generarRetencionReportList(retencionReportList, compra, compraRetencionIf);
		}		
		
		compraDetalleLiquidacionList.clear();
		//johanna
		if(tipoDocumentoIf.getCodigo()!=null && tipoDocumentoIf.getCodigo().equals("LIC"))
		{
			compraDetalleLiquidacionList.clear();
			for(CompraDetalleIf compraDetalleIf : compraDetalleColeccion){
				generarCompraDetalleLiquidacionReportList(compraDetalleLiquidacionList, compra, compraDetalleIf);
			}
		}
		
		if (retencionReportList.size() > 0) {
			String si = "Si"; 
	        String no = "No"; 
	        Object[] options ={si,no}; 
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Retencion para imprimir?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				String fileName = "jaspers/cartera/RPComprobanteRetencion.jasper";
				HashMap parametrosMap = new HashMap();
				ReportModelImpl.processReport(fileName, parametrosMap, retencionReportList, true);
			}		
		}
				
		if(compraDetalleLiquidacionList.size()>0)
		{
			int opcion2 = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Liquidación de compras/Prestación de servicios para imprimir?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion2 == JOptionPane.YES_OPTION) {
				String fileName2 = "jaspers/cartera/RPLiquidacionDeCompras.jasper";
				HashMap parametrosMap = new HashMap();
				
				System.out.println(grandTotal+"<<<<<<<<<<<<<<<<<<totalIVA:::****************:"+totalIVA);
				
				//parametrosMap.put("ivaOtros",String.valueOf(Double.valueOf(totalOtrosImpuestos)));
				parametrosMap.put("ivaOtros",formatoDecimal.format(Utilitarios.redondeoValor(totalOtrosImpuestos)));
				parametrosMap.put("iva",formatoDecimal.format(Utilitarios.redondeoValor(totalIVA)));
				parametrosMap.put("grandTotal",formatoDecimal.format(Utilitarios.redondeoValor(grandTotal)));
			
				
				ReportModelImpl.processReport(fileName2, parametrosMap, compraDetalleLiquidacionList, true);
			}		
		}
		
		//Doy la opccion de imprimir el reporte del asiento
		generarReporteAsiento(); 
	}

	private void generarReporteAsiento() {
		try {
			if(compra != null){
				HashMap asientoCompraMap = new HashMap();
				asientoCompraMap.put("tipoDocumentoId", compra.getTipodocumentoId());
				asientoCompraMap.put("transaccionId", compra.getId());
				Collection asientosCompra = SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(asientoCompraMap);
				if(asientosCompra.size() > 0){
					int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte del Asiento?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
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
						Map tiposDocumentoMap = SessionServiceLocator.getTipoDocumentoSessionService().mapearTiposDocumento(Parametros.getIdEmpresa());
						Map tiposTroqueladoMap = SessionServiceLocator.getTipoTroqueladoSessionService().mapearTiposTroquelado();
						if (asientoCompra.getTipoDocumentoId() != null) {
							TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get(asientoCompra.getTipoDocumentoId());
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
	
	private void generarRetencionReportList(List<RetencionProveedorData> retencionReportList, CompraIf compra, CompraRetencionIf compraRetencion) {
		try {
			RetencionProveedorData retencionProveedorData = new RetencionProveedorData();
			ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(compra.getProveedorId());
			ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
			TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(compra.getTipodocumentoId());
			retencionProveedorData.setBaseImponible(String.valueOf(compraRetencion.getBaseImponible()));
			retencionProveedorData.setCodigoImpuesto(compraRetencion.getImpuesto());
			
			CiudadIf ciudad = SessionServiceLocator.getCiudadSessionService().getCiudad(clienteOficina.getCiudadId());
			retencionProveedorData.setDireccion(ciudad.getNombre() + ", " + clienteOficina.getDireccion());
						
			retencionProveedorData.setEjercicioFiscal(String.valueOf(compraRetencion.getEjercicioFiscal()));
			java.sql.Date fechaEmision = compraRetencion.getFechaEmision();
			int year = fechaEmision.getYear() + 1900;
			int month = fechaEmision.getMonth() + 1;
			int day = fechaEmision.getDate();
			String mes = Utilitarios.getNombreMes(month);
			String fechaRetencion = String.valueOf(year) + "-" + mes.substring(0,3) + "-" + formatoSerialMes.format(Double.parseDouble(String.valueOf(day)));
			//retencionProveedorData.setFechaEmision(String.valueOf(compraRetencion.getFechaEmision()));
			retencionProveedorData.setFechaEmision(fechaRetencion);
			if(compraRetencion.getImpuesto().equals(IMPUESTO_RENTA.substring(0,1))){
				retencionProveedorData.setImpuesto(IMPUESTO_RENTA);
			}else if(compraRetencion.getImpuesto().equals(IMPUESTO_IVA.substring(0,1))){
				retencionProveedorData.setImpuesto(IMPUESTO_IVA);
			}			
			retencionProveedorData.setNumComprobanteRetencion(compraRetencion.getEstablecimiento()+compraRetencion.getPuntoEmision()+compraRetencion.getSecuencial());
			retencionProveedorData.setNumComprobanteVenta(compra.getPreimpreso());
			retencionProveedorData.setCodigoImpuesto(compraRetencion.getCodigoImpuesto());
			retencionProveedorData.setPorcentajeRetencion(formatoDecimal.format(compraRetencion.getPorcentajeRetencion()));
			retencionProveedorData.setProveedor(cliente.getRazonSocial());
			retencionProveedorData.setRuc(cliente.getIdentificacion());
			retencionProveedorData.setTelefono((clienteOficina.getTelefono() != null && clienteOficina.getTelefono().length() >= 7)?clienteOficina.getTelefono():"");
			if(tipoDocumento.getCodigo().equals("COM") || tipoDocumento.getCodigo().equals("COR") || tipoDocumento.getCodigo().equals("COI") || tipoDocumento.getCodigo().equals("CNV") || tipoDocumento.getCodigo().equals("SAE")){
				retencionProveedorData.setTipoComprobante("FACTURA");
			}else{
				retencionProveedorData.setTipoComprobante(tipoDocumento.getNombre());
			}		
			retencionProveedorData.setValor(String.valueOf(compraRetencion.getValorRetenido()));
			retencionReportList.add(retencionProveedorData);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	
	private void generarCompraDetalleLiquidacionReportList(List<CompraDetalleLiquidacionData> compraDetalleLiquidacionList, CompraIf compra, CompraDetalleIf compraDetalle) {
		try {
			CompraDetalleLiquidacionData compraDetalleLiquidacionData = new CompraDetalleLiquidacionData();
			
			ClienteOficinaIf clienteOficina;			
			clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(compra.getProveedorId());
			ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
			compraDetalleLiquidacionData.setDescripcion(compraDetalle.getDescripcion());
			compraDetalleLiquidacionData.setProveedor(cliente.getNombreLegal());
			compraDetalleLiquidacionData.setRuc(cliente.getIdentificacion());					
			compraDetalleLiquidacionData.setDireccion(clienteOficina.getDireccion());
			OficinaIf oficina = (OficinaIf)getCmbOficina().getSelectedItem();
			compraDetalleLiquidacionData.setDireccionEmpresa(oficina.getDireccion());
			compraDetalleLiquidacionData.setCantidad(String.valueOf(compraDetalle.getCantidad()));
			compraDetalleLiquidacionData.setValor(String.valueOf(compraDetalle.getValor()));	
			compraDetalleLiquidacionData.setId(compraDetalle.getId());
			
			double cantidad = Double.parseDouble(compraDetalle.getCantidad().toString());
			double valor = Double.parseDouble(compraDetalle.getValor().toString());
			double subtotal = cantidad * valor;
			
			compraDetalleLiquidacionData.setDescuento(String.valueOf(Double.valueOf(subtotal)));
			
			//compraDetalleLiquidacionData.setDescuento(BigDecimal.valueOf(Double.valueOf(subtotal)));
			
			System.out.println("DESCUENTO:****************"+compraDetalleLiquidacionData.getId());
			
			java.sql.Date fechaEmision = compra.getFecha();
			int year = fechaEmision.getYear() + 1900;
			int month = fechaEmision.getMonth() + 1;
			int day = fechaEmision.getDate();
			String mes = Utilitarios.getNombreMes(month);
			String fechaRetencion = String.valueOf(year) + "-" + mes.substring(0,3) + "-" + formatoSerialMes.format(Double.parseDouble(String.valueOf(day)));
			//retencionProveedorData.setFechaEmision(String.valueOf(compraRetencion.getFechaEmision()));
			compraDetalleLiquidacionData.setFechaEmision(fechaRetencion);
			
			
			compraDetalleLiquidacionList.add(compraDetalleLiquidacionData);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void refresh() {
		cargarGastos();
		cargarComboTipoDocumento();
		cargarComboMonedas();
		if (productoIf != null)
			cargarComboRetencion();
		cargarMapas();
	}
	
	public void duplicate() {
	
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpCompras().getSelectedIndex();
		int tabCount = this.getJtpCompras().getTabCount();
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpCompras().setSelectedIndex(selectedTab);
	}
	
	public void addDetail() {
		if (getJtpCompras().getSelectedIndex() == 1)
			agregarCompraDetalle();
	}
	
	public void updateDetail() {
		if (getJtpCompras().getSelectedIndex() == 1)
			actualizarCompraDetalle();
	}
	
	public void deleteDetail() {
		if (getJtpCompras().getSelectedIndex() == 1)
			eliminarCompraDetalle();
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		Long proveedorId = 0L, tipoDocumentoId = 0L;
		
		if (getCmbTipoCompra().getSelectedItem() != null) {
			if ("".compareTo(getCmbTipoCompra().getSelectedItem().toString().substring(0, 1)) != 0)
				aMap.put("localimportada", getCmbTipoCompra().getSelectedItem().toString().substring(0, 1));
		}
		
		if (getTxtCodigo().getText().equals("") == false)
			aMap.put("codigo", getTxtCodigo().getText() + "%");
		else
			aMap.put("codigo", "%");
		
		if (getTxtReferencia().getText().equals("") == false)
			aMap.put("referencia", getTxtReferencia().getText() + "%");
		
		if (proveedorIf != null) {
			proveedorId = proveedorIf.getId();
			aMap.put("proveedorId", proveedorId);
		}
		
		if (tipoDocumentoIf != null) {
			tipoDocumentoId = tipoDocumentoIf.getId();
			aMap.put("tipodocumentoId", tipoDocumentoId);
		}
		
		if ( getCmbEstado().getSelectedItem()!=null ) {
			if (!getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_REEMBOLSO_NORMAL) && !getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_NORMAL_REEMBOLSO))
				aMap.put("estado", getCmbEstado().getSelectedItem().toString().substring(0, 1));
			else if (getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_REEMBOLSO_NORMAL))
				aMap.put("estado", ESTADO_REEMBOLSO_NORMAL);
			else if (getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_NORMAL_REEMBOLSO))
				aMap.put("estado", ESTADO_NORMAL_REEMBOLSO);
		}
		
		if ( getTxtPreimpreso().getText()!=null && !"".equals(getTxtPreimpreso().getText()))
			aMap.put("preimpreso", getTxtPreimpreso().getText().trim() );
		
		if ( getTxtObservacion().getText()!=null && !"".equals(getTxtObservacion().getText()))
			aMap.put("observacion", getTxtObservacion().getText().trim() );
		
		return aMap;
	}
	
	public void find() {
		try {
			Map mapa = buildQuery();
			int tamanoLista = SessionServiceLocator.getCompraSessionService().findCompraByQueryListSize(mapa, Parametros.getIdEmpresa());
			if (tamanoLista > 0) {
				CompraCriteria compraCriteria = new CompraCriteria(true);
				compraCriteria.setResultListSize(tamanoLista);
				compraCriteria.setQueryBuilded(mapa);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), compraCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if (popupFinder.getElementoSeleccionado() != null)
					getSelectedObject(popupFinder.getElementoSeleccionado());
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
	
	public boolean isEmpty() {
		return false;
	}
	
	public void clean() {
		//retencionReportList.clear();
		retencionReportList = null;
		retencionReportList = new ArrayList<RetencionProveedorData>();
		//compraDetalleColeccion.clear();
		compraDetalleColeccion = null;
		compraDetalleColeccion = new ArrayList<CompraDetalleIf>();
		//compraDetalleEliminadas.clear();
		compraDetalleEliminadas = null;
		compraDetalleEliminadas = new ArrayList<CompraDetalleIf>();
		//compraRetencionColeccion.clear();
		compraRetencionColeccion = null;
		compraRetencionColeccion = new Vector<CompraRetencionIf>();
		compraRetencion = null;
		filaSeleccionadaRetencion = -1;
		//compraRetencionEliminadaColeccion.clear();
		compraRetencionEliminadaColeccion = null;
		compraRetencionEliminadaColeccion = new Vector<CompraRetencionIf>();
		

		compraGastoClase = null;
		compraGastoClase = new CompraGastoClase();
		
		mapaProductoNombre = null;
		mapaProductoNombre = new HashMap<Long, String>();
		ESTADO_ANTERIOR = "";
		
		//ADD 19 JULIO
		tipoOrden = TIPO_ORDEN_COMPRA;
		//END 19 JULIO
		ordenesDataVector = null;
		ordenesDataVector = new Vector<OrderData>();
		compra = null;
	}
	
	public void showFindMode() {
		clean();
		cleanRetencion();
		cleanTableDetalle();
		cleanTableRetencion();
		cleanTablaGasto();
		getCmbEstado().setBackground(Parametros.findColor);
		getCmbTipoCompra().setBackground(Parametros.findColor);
		getTxtCodigo().setBackground(Parametros.findColor);
		getTxtProveedor().setBackground(Parametros.findColor);
		getCmbTipoDocumento().setBackground(Parametros.findColor);
		getTxtCodigo().addKeyListener(oKeyListenerTxtCodigo);
		getTxtPreimpreso().setBackground(Parametros.findColor);
		getTxtReferencia().setBackground(Parametros.findColor);
		getTxtObservacion().setBackground(Parametros.findColor);
		proveedorIf = null;
		tipoDocumentoIf = null;
		getTxtCodigo().setText("");
		getTxtCodigo().setEditable(true);
		getCmbFecha().setSelectedItem(null);
		getCmbFecha().setEnabled(false);
		getCmbFechaVencimiento().setSelectedItem(null);
		getCmbFechaVencimiento().setEnabled(false);
		getCmbFechaCaducidad().setSelectedItem(null);
		getCmbFechaCaducidad().setEnabled(false);
		getTxtProveedor().setText("");
		getTxtProveedor().setEditable(false);
		getBtnBuscarProveedor().setEnabled(true);
		getCmbMoneda().setEnabled(false);
		getCmbEstado().setEnabled(true);
		getCmbTipoDocumento().setEnabled(true);
		getCmbTipoCompra().setEnabled(true);
		getTxtObservacion().setText("");
		getTxtPreimpreso().setText("");
		getTxtPreimpreso().setEnabled(true);
		getTxtAutorizacion().setText("");
		getTxtAutorizacion().setEnabled(false);
		//MODIFIED 15 JULIO
		//getBtnBuscarOrdenCompra().setEnabled(false);
		getBtnManejarOrdenes().setEnabled(false);
		getTxtReferencia().setEditable(true);
		getTxtUsuario().setEnabled(false);
		getBtnBuscarProducto().setEnabled(false);
		getTxtProducto().setEditable(false);
		getTxtCantidad().setEnabled(false);
		getTxtValor().setEnabled(false);
		getTxtPorcentajeDescuentoEspecial().setEnabled(false);
		getTxtPorcentajeDescuentoAgencia().setEnabled(false);
		getTxtPorcentajeDescuentosVarios().setEnabled(false);
		getTxtPorcentajeOtroImpuesto().setEnabled(false);
		getBtnAgregarDetalle().setEnabled(false);
		getBtnActualizarDetalle().setEnabled(false);
		getBtnEliminarDetalle().setEnabled(false);
		getCmbTipoDocumento().setSelectedItem(null);
		getCmbMoneda().setSelectedItem(null);
		getCmbTipoCompra().setSelectedItem(null);
		getTxtReferencia().setText("");
		getTxtCodigo().grabFocus();
	}
	
	/*public void showSaveMode() {
		try {
			ArrayList<String> detalles = new ArrayList<String>();
			CompraIf compra = (CompraIf) getCompraSessionService().getCompra(200L);
			Iterator it = getCompraDetalleSessionService().findCompraDetalleByCompraId(compra.getId()).iterator();
			while (it.hasNext()) {
				CompraDetalleIf compraDetalle = (CompraDetalleIf) it.next();
				double cantidad = compraDetalle.getCantidad().doubleValue();
				double valor = compraDetalle.getValor().doubleValue();
				double iva = compraDetalle.getIva().doubleValue();
				double costo = (cantidad * valor) + iva;
				Iterator it2 = getCompraDetalleGastoSessionService().findCompraDetalleGastoByCompraDetalleId(compraDetalle.getId()).iterator();
				while (it2.hasNext()) {
					CompraDetalleGastoIf cdg = (CompraDetalleGastoIf) it2.next();
					costo += cdg.getValor().doubleValue();
				}
				
				double costoUnitario = costo/cantidad;
				ProductoIf producto = productosMap.get(compraDetalle.getProductoId());
				LoteIf lote = (LoteIf) getLoteSessionService().findLoteByProductoId(producto.getId()).iterator().next();
				String detalle = lote.getId() + "\t" + costoUnitario;
				detalles.add(detalle);
			}
			
			it = detalles.iterator();
			while (it.hasNext()) {
				System.out.println((String)it.next());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		
	}*/
	
	public void showSaveMode() {
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		getCmbTipoCompra().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().setBackground(getBackground());
		getTxtPreimpreso().setBackground(Parametros.saveUpdateColor);
		getTxtProveedor().setBackground(getBackground());
		getCmbTipoDocumento().setBackground(Parametros.saveUpdateColor);
		getTxtReferencia().setBackground(Parametros.saveUpdateColor);
		getTxtObservacion().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().removeKeyListener(oKeyListenerTxtCodigo);
		
		//getTxtEstablecimiento().setEnabled(false);
		//getTxtPuntoEmision().setEnabled(false);
		//getTxtSecuencial().setEnabled(false);
		//getTxtAutorizacionRetencion().setEnabled(false);
		getCmbFechaEmision().setEditable(false);
		
		setSaveMode();
		proveedorIf = null;
		tipoDocumentoIf = null;
		getTxtCodigo().setText("");
		getTxtCodigo().setEditable(false);
		getCmbFecha().setEnabled(true);
		getCmbFechaVencimiento().setEnabled(true);
		getCmbFechaCaducidad().setEnabled(true);
		getTxtUsuario().setText(Parametros.getUsuario());
		getTxtUsuario().setEnabled(true);
		getTxtObservacion().setText("");
		getTxtObservacion().setEnabled(true);
		getTxtPreimpreso().setText("");
		getTxtPreimpreso().setEnabled(true);
		getTxtAutorizacion().setText("");
		getTxtAutorizacion().setEnabled(true);
		getBtnManejarOrdenes().setEnabled(false);
		getTxtReferencia().setText("");
		getTxtReferencia().setEditable(false);
		getTxtProveedor().setText("");
		getTxtProveedor().setEditable(false);
		getCmbTipoDocumento().setEnabled(true);
		getCmbMoneda().setEnabled(true);
		if (getCmbMoneda().getItemCount() > 0)
			getCmbMoneda().setSelectedIndex(0);
		getCmbEstado().setEnabled(true);
		getCmbTipoCompra().setEnabled(true);
		if (getCmbTipoCompra().getItemCount() > 0)
			getCmbTipoCompra().setSelectedIndex(0);
		getTxtProducto().setText("");
		getTxtProducto().setEditable(false);
		getBtnBuscarProducto().setEnabled(false);
		getTxtCantidad().setText("");
		getTxtCantidad().setEnabled(false);
		getTxtCantidad().setEditable(false);
		getTxtValor().setText("");
		getTxtValor().setEnabled(false);
		getTxtValor().setEditable(false);
		getTxtPorcentajeDescuentoEspecial().setText("");
		getTxtPorcentajeDescuentoEspecial().setEnabled(false);
		getTxtPorcentajeDescuentoEspecial().setEditable(false);
		getTxtPorcentajeDescuentoAgencia().setText("");
		getTxtPorcentajeDescuentoAgencia().setEnabled(false);
		getTxtPorcentajeDescuentoAgencia().setEditable(false);
		getTxtPorcentajeDescuentosVarios().setText("");
		getTxtPorcentajeDescuentosVarios().setEnabled(false);
		getTxtPorcentajeDescuentosVarios().setEditable(false);
		getTxtPorcentajeOtroImpuesto().setText("");
		getTxtPorcentajeOtroImpuesto().setEnabled(false);
		getTxtPorcentajeOtroImpuesto().setEditable(false);
		totalValor = 0.00;
		totalDescuentoEspecial = 0.00;
		totalDescuento = 0.00;
		totalBonificacion = 0.00;
		totalIVA = 0.00;
		totalRetencion = 0.00;
		totalICE = 0.00;
		totalOtrosImpuestos = 0.00;
		grandTotal = 0.00;
		getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalValor)));
		getTxtValorFinal().setEditable(false);
		getTxtDescuentoEspecialFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalDescuentoEspecial)));
		getTxtDescuentoAgenciaFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalDescuento)));
		getTxtDescuentoAgenciaFinal().setEditable(false);
		getTxtDescuentosVariosFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalBonificacion)));
		getTxtDescuentosVariosFinal().setEditable(false);
		getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalIVA)));
		getTxtIVAFinal().setEditable(false);
		getTxtRetencionFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalRetencion)));
		getTxtRetencionFinal().setEditable(false);
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
		getCmbFecha().setCalendar(calendar);
		getCmbFechaVencimiento().setCalendar(calendar);
		getCmbFechaCaducidad().setCalendar(calendar);
		getCmbFechaEmision().setCalendar(calendar);
		//MODIFIED 19 JULIO
		//ordenCompraRequerida = true;
		ordenRequerida = true;
		Map parameterMap = new HashMap();
		parameterMap.put("codigo", "OCR");
		parameterMap.put("empresaId", Parametros.getIdEmpresa());
		try {
			Iterator it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
			if (it.hasNext()) {
				ParametroEmpresaIf parametroEmpresa = (ParametroEmpresaIf) it.next();
				if (parametroEmpresa.getValor().equals("N"))
					ordenRequerida = false;
					//ordenCompraRequerida = false; //MODIFIED 19 JULIO
			}
		} catch (GenericBusinessException e) { 
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		manejarBotonActivar();
		
		clean();
		cleanRetencion();
		cleanTableDetalle();
		cleanTableRetencion();
		cleanTablaGasto();
		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVA);
		
		tfce.setCompraGasto(compraGastoClase);
		
		if (getCmbTipoDocumento().getItemCount() > 0){
			preseleccionarCompraLocal();
		}
		
		this.getJtpCompras().setSelectedIndex(0);
		getBtnBuscarProveedor().grabFocus();
	}
	
	public void showUpdateMode() {
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		getCmbTipoCompra().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().setBackground(getBackground());
		getTxtProveedor().setBackground(getBackground());
		getTxtPreimpreso().setBackground(Parametros.saveUpdateColor);
		getCmbTipoDocumento().setBackground(Parametros.saveUpdateColor);
		getTxtReferencia().setBackground(Parametros.saveUpdateColor);
		getTxtObservacion().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().removeKeyListener(oKeyListenerTxtCodigo);
		
		setUpdateMode();
		getTxtCodigo().setText("");
		getTxtCodigo().setEditable(false);
		getCmbFecha().setEnabled(true);
		getCmbFechaVencimiento().setEnabled(true);
		getCmbFechaCaducidad().setEnabled(true);
		getTxtProveedor().setEditable(false);
		getBtnBuscarProveedor().setEnabled(true);
		getCmbMoneda().setEnabled(true);
		getCmbEstado().setEnabled(true);
		getCmbTipoDocumento().setEnabled(true);
		getCmbTipoCompra().setEnabled(true);
		getTxtObservacion().setText("");
		getTxtObservacion().setEnabled(true);
		getTxtPreimpreso().setText("");
		getTxtPreimpreso().setEnabled(true);
		getTxtAutorizacion().setText("");
		getTxtAutorizacion().setEnabled(true);
		getBtnManejarOrdenes().setEnabled(false);
		getTxtReferencia().setEditable(true);
		getTxtUsuario().setEnabled(true);
		getBtnBuscarProducto().setEnabled(true);
		if (!ordenRequerida)
			getTxtProducto().setEditable(true);
		getTxtCantidad().setEnabled(true);
		getTxtValor().setEnabled(true);
		getTxtPorcentajeDescuentoEspecial().setEnabled(true);
		getTxtPorcentajeDescuentoAgencia().setEnabled(true);
		getTxtPorcentajeDescuentosVarios().setEnabled(true);
		getTxtPorcentajeOtroImpuesto().setEnabled(true);
		getBtnAgregarDetalle().setEnabled(true);
		getBtnActualizarDetalle().setEnabled(true);
		getBtnEliminarDetalle().setEnabled(true);
		getJtpCompras().setSelectedIndex(0);
		getTxtObservacion().grabFocus();		
		
	}
	
	public boolean validateFields() {
		String strObservacion = getTxtObservacion().getText();
		String strReferencia = getTxtReferencia().getText();
		String strAutorizacion = getTxtAutorizacion().getText();
		Date fechaCompra = getCmbFecha().getDate();
		Date fechaVencimiento = getCmbFechaVencimiento().getDate();
		//Date fechaCaducidad = getCmbFechaCaducidad().getDate();
		String strTotalValor = getTxtValorFinal().getText();
				
		if ( getCmbEstado().getSelectedItem().toString().equals("L") ){
			SpiritAlert.createAlert("El estado no puede ser L!!", SpiritAlert.WARNING);
			return false;
		}
		
		if (fechaCompra != null) {
			if (fechaVencimiento.before(fechaCompra)) {
				SpiritAlert.createAlert("La fecha de vencimiento debe ser posterior a la fecha de la Compra !!", SpiritAlert.WARNING);
				getJtpCompras().setSelectedIndex(0);
				getCmbFechaVencimiento().grabFocus();
				return false;
			}
			
			/*if (fechaCaducidad.before(fechaCompra)) {
				SpiritAlert.createAlert("La fecha de caducidad debe ser posterior a la fecha de la Compra !!", SpiritAlert.WARNING);
				getJtpCompras().setSelectedIndex(0);
				getCmbFechaCaducidad().grabFocus();
				return false;
			}*/
		} else {
			SpiritAlert.createAlert("La fecha de la Compra debe ser ingresada !!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(0);
			this.getCmbFecha().grabFocus();
			return false;
		}
		
		if (proveedorIf == null) {
			SpiritAlert.createAlert("Debe seleccionar un proveedor !!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(0);
			getBtnBuscarProveedor().grabFocus();
			return false;
		}
		
		if ("".equals(strObservacion)) {
			SpiritAlert.createAlert("Debe ingresar una observación !!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(0);
			getTxtObservacion().grabFocus();
			return false;
		}
		
		//MODIFIED 19 JULIO
		//if ("".equals(strReferencia) && ordenCompraRequerida) {
		if ("".equals(strReferencia) && ordenRequerida) {
			SpiritAlert.createAlert("Debe ingresar la referencia !!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(0);
			getTxtReferencia().grabFocus();
			return false;
		}
		
		if ( !validatePreimpresoValido() )
			return false;
		
		if ("".equals(strAutorizacion)) {
			SpiritAlert.createAlert("Debe ingresar la autorización !!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(0);
			getTxtAutorizacion().grabFocus();
			return false;
		}
		
		if (getTblCompraDetalle().getRowCount() == 0) {
			SpiritAlert.createAlert("Debe existir al menos un detalle para guardar la Compra !!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(1);
			getBtnBuscarProducto().grabFocus();
			return false;
		}
		
		//esta validación siempre debe estar antes del siguiente "for" porque valida que cada detalle tenga asociado un documento
		for(CompraDetalleIf compraDetalle : compraDetalleColeccion){
			if(compraDetalle.getDocumentoId() == null){
				SpiritAlert.createAlert("Uno o más detalles de la compra no tienen Documento asociado !", SpiritAlert.WARNING);
				getJtpCompras().setSelectedIndex(1);
				getCmbDocumento().grabFocus();
				return false;
			}
		}
		
		for (int i=0; i<compraDetalleColeccion.size(); i++) {
			try {
				CompraDetalleIf compraDetalle = (CompraDetalleIf) compraDetalleColeccion.get(i);
				DocumentoIf documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(compraDetalle.getDocumentoId());
				if (documento.getTipoDocumentoId().compareTo(tipoDocumentoIf.getId()) != 0) {
					SpiritAlert.createAlert("Documento no corresponde con tipo de documento seleccionado !!", SpiritAlert.WARNING);
					this.getJtpCompras().setSelectedIndex(1);
					this.getTblCompraDetalle().getSelectionModel().setSelectionInterval(i,i);
					return false;
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
			}
		}
		
		if (Double.parseDouble(Utilitarios.removeDecimalFormat(strTotalValor)) <= 0) {
			SpiritAlert.createAlert("El valor total debe ser mayor que 0 !!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(1);
			getBtnBuscarProducto().grabFocus();
			return false;
		}
		
		return true;
	}
	
	private boolean validatePreimpresoValido(){
		String strPreimpreso = getTxtPreimpreso().getText().trim();
		if ("".equals(strPreimpreso)) {
			SpiritAlert.createAlert("Debe ingresar el preimpreso !!", SpiritAlert.WARNING);
			getJtpCompras().setSelectedIndex(0);
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
				SpiritAlert.createAlert("El formato del preimpreso tiene que ser ###-###-####### ", SpiritAlert.WARNING);
				getJtpCompras().setSelectedIndex(0);
				getTxtPreimpreso().grabFocus();
				return false;
			}
		}
		return true;
	}
	
	private CompraIf registrarCompra() throws GenericBusinessException {
		CompraData data = new CompraData();
		
		java.sql.Date fechaCreacion = new java.sql.Date(getCmbFecha().getDate().getTime());
		String codigo = getCodigoCompra(new java.sql.Date(fechaCreacion.getYear(), fechaCreacion.getMonth(), fechaCreacion.getDate()));
		data.setCodigo(codigo);
		OficinaIf oficina = (OficinaIf)getCmbOficina().getSelectedItem();
		data.setOficinaId(oficina.getId());
		data.setTipodocumentoId(tipoDocumentoIf.getId());
		data.setProveedorId(proveedorIf.getId());
		data.setMonedaId(((MonedaIf) getCmbMoneda().getSelectedItem()).getId());
		Long idUsuario = ((UsuarioIf) (SessionServiceLocator.getUsuarioSessionService().findUsuarioByUsuario(Parametros.getUsuario().toLowerCase()).iterator().next())).getId();
		data.setUsuarioId(idUsuario);
		data.setLocalimportada(getCmbTipoCompra().getSelectedItem().toString().substring(0, 1));
		data.setFecha(fechaCreacion);
		data.setFechaVencimiento(new java.sql.Date(getCmbFechaVencimiento().getDate().getTime()));
		
		if (!getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_REEMBOLSO_NORMAL) && !getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_NORMAL_REEMBOLSO))
			data.setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
		else if (getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_REEMBOLSO_NORMAL))
			data.setEstado(ESTADO_REEMBOLSO_NORMAL);
		else if (getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_NORMAL_REEMBOLSO))
			data.setEstado(ESTADO_NORMAL_REEMBOLSO);
		
		if (tipoOrden.compareTo(TIPO_ORDEN_COMPRA) == 0)
			data.setTipoCompra(TIPO_ORDEN_COMPRA);
		else if (tipoOrden.compareTo(TIPO_ORDEN_MEDIO) == 0)
			data.setTipoCompra(TIPO_ORDEN_MEDIO);		
		
		data.setReferencia(getTxtReferencia().getText());
		data.setObservacion(getTxtObservacion().getText());
		data.setPreimpreso(getTxtPreimpreso().getText());
		data.setAutorizacion(getTxtAutorizacion().getText());
		data.setValor(BigDecimal.valueOf(totalValor));
		data.setDescuento(BigDecimal.valueOf(totalDescuento));
		data.setIva(BigDecimal.valueOf(totalIVA));
		data.setIce(BigDecimal.valueOf(totalICE));
		data.setOtroImpuesto(BigDecimal.valueOf(totalOtrosImpuestos));
		Double retencion = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtRetencionFinal().getText()));
		data.setRetencion(BigDecimal.valueOf(retencion));
		data.setIdSriSustentoTributario(((SriSustentoTributarioIf) getCmbTipoSustentoTributario().getSelectedItem()).getId()); 
		
		return data;
	}
	
	private String getCodigoCompra(java.sql.Date fechaCompra) {
		String codigo = "";
		String anioCompra = Utilitarios.getYearFromDate(fechaCompra);
		codigo += anioCompra + "-";
		return codigo;
	}
	
	/*private Vector<CompraDetalleIf> collectionToVector(Collection coleccion) {
		Iterator it = coleccion.iterator();
		Vector<CompraDetalleIf> vector = new Vector<CompraDetalleIf>();
		while (it.hasNext()) {
			CompraDetalleIf detalleCompra = (CompraDetalleIf) it.next();
			if (detalleCompra != null)
				vector.add(detalleCompra);
		}
		
		return vector;
	}*/
	
	private CompraIf registrarCompraForUpdate() {
		
		OficinaIf oficina = (OficinaIf)getCmbOficina().getSelectedItem();
		compra.setOficinaId(oficina.getId());
		compra.setTipodocumentoId(tipoDocumentoIf.getId());
		compra.setProveedorId(proveedorIf.getId());
		compra.setMonedaId(((MonedaIf) getCmbMoneda().getSelectedItem()).getId());
		Long idUsuario = 0L;
		
		try {
			idUsuario = ((UsuarioIf) (SessionServiceLocator.getUsuarioSessionService().findUsuarioByUsuario(Parametros.getUsuario().toLowerCase()).iterator().next())).getId();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		compra.setUsuarioId(idUsuario);
		compra.setLocalimportada(getCmbTipoCompra().getSelectedItem().toString().substring(0, 1));
		compra.setFecha(new java.sql.Date(getCmbFecha().getDate().getTime()));
		compra.setFechaVencimiento(new java.sql.Date(getCmbFechaVencimiento().getDate().getTime()));
		
		if ( compra.getEstado()!=null && !compra.getEstado().equalsIgnoreCase("L") ) {
			if (!getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_NORMAL_REEMBOLSO) && !getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_REEMBOLSO_NORMAL))
				compra.setEstado(getCmbEstado().getSelectedItem().toString().substring(0, 1));
			else if (getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_NORMAL_REEMBOLSO))
				compra.setEstado(ESTADO_NORMAL_REEMBOLSO);
			else if (getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_REEMBOLSO_NORMAL))
				compra.setEstado(ESTADO_REEMBOLSO_NORMAL);
		}
		
		compra.setReferencia(getTxtReferencia().getText());
		compra.setObservacion(getTxtObservacion().getText());
		compra.setPreimpreso(getTxtPreimpreso().getText());
		compra.setAutorizacion(getTxtAutorizacion().getText());
		compra.setValor(BigDecimal.valueOf(totalValor));
		compra.setDescuento(BigDecimal.valueOf(totalDescuento));
		compra.setIva(BigDecimal.valueOf(totalIVA));
		compra.setIce(BigDecimal.valueOf(totalICE));
		compra.setOtroImpuesto(BigDecimal.valueOf(totalOtrosImpuestos));
		compra.setRetencion(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtRetencionFinal().getText()))));
		compra.setIdSriSustentoTributario(((SriSustentoTributarioIf) getCmbTipoSustentoTributario().getSelectedItem()).getId());
		
		return compra;
	}
	
	private CompraIf registrarCompraAnterior() {
		CompraData compraAnterior = new CompraData();
		compraAnterior.setId(compra.getId());
		compraAnterior.setOficinaId(compra.getOficinaId());
		compraAnterior.setTipodocumentoId(compra.getTipodocumentoId());
		compraAnterior.setCodigo(compra.getCodigo());
		compraAnterior.setProveedorId(compra.getProveedorId());
		compraAnterior.setMonedaId(compra.getMonedaId());
		compraAnterior.setUsuarioId(compra.getUsuarioId());
		compraAnterior.setFecha(compra.getFecha());
		compraAnterior.setFechaVencimiento(compra.getFechaVencimiento());
		compraAnterior.setPreimpreso(compra.getPreimpreso());
		compraAnterior.setAutorizacion(compra.getAutorizacion());
		compraAnterior.setReferencia(compra.getReferencia());
		compraAnterior.setLocalimportada(compra.getLocalimportada());
		compraAnterior.setValor(compra.getValor());
		compraAnterior.setDescuento(compra.getDescuento());
		compraAnterior.setIva(compra.getIva());
		compraAnterior.setIce(compra.getIce());
		compraAnterior.setOtroImpuesto(compra.getOtroImpuesto());
		compraAnterior.setObservacion(compra.getObservacion());
		compraAnterior.setEstado(compra.getEstado());
		compraAnterior.setEstadoBpm(compra.getEstadoBpm());
		compraAnterior.setRetencion(compra.getRetencion());
		compraAnterior.setIdSriSustentoTributario(compra.getIdSriSustentoTributario());
		//compraAnterior.setFechaCaducidad(compra.getFechaCaducidad());
		return compraAnterior;
	}
	
	//PARA OBTENER LA REFERENCIA DE LA TAREA
	private Tarea tarea;

	@Override
	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
}