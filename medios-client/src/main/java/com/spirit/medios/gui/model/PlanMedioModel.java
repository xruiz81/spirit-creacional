package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jidesoft.grid.TableScrollPane;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.medios.entity.ComercialIf;
import com.spirit.medios.entity.DerechoProgramaIf;
import com.spirit.medios.entity.GrupoObjetivoIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PlanMedioData;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PlanMedioMesData;
import com.spirit.medios.entity.PlanMedioMesIf;
import com.spirit.medios.entity.PrensaFormatoIf;
import com.spirit.medios.entity.PrensaSeccionIf;
import com.spirit.medios.entity.PrensaTarifaIf;
import com.spirit.medios.entity.PrensaUbicacionIf;
import com.spirit.medios.gui.criteria.PlanMedioCriteria;
import com.spirit.medios.gui.importer.Pauta;
import com.spirit.medios.gui.importer.PautaImporter;
import com.spirit.medios.gui.panel.JPPlanMedio;
import com.spirit.medios.util.TableScrollPaneMapaPauta;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class PlanMedioModel extends JPPlanMedio {
	
	private static final String NOMBRE_ESTADO_EN_PROCESO = "EN PROCESO";
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_APROBADO = "APROBADO";
	private static final String NOMBRE_ESTADO_ELIMINADO = "ELIMINADO";
	private static final String ESTADO_EN_PROCESO = "N";
	private static final String ESTADO_PENDIENTE = "P";
	private static final String ESTADO_APROBADO = "A";
	private static final String ESTADO_ELIMINADO = "E";
	
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = "A";
	private static final String ESTADO_INACTIVO = "I";
	
	private static final String NOMBRE_TIPO_LANZAMIENTO = "LANZAMIENTO";
	private static final String NOMBRE_TIPO_MANTENIMIENTO = "MANTENIMIENTO";
	private static final String NOMBRE_TIPO_PROMOCIONAL = "PROMOCIONAL";
	private static final String NOMBRE_TIPO_EXPECTATIVA = "EXPECTATIVA";
	private static final String TIPO_LANZAMIENTO = "L";
	private static final String TIPO_MANTENIMIENTO = "M";
	private static final String TIPO_PROMOCIONAL = "P";
	private static final String TIPO_EXPECTATIVA = "E";
	
	private static final String NOMBRE_MEDIO_TELEVISION = "TELEVISION";
	private static final String NOMBRE_MEDIO_RADIO = "RADIO";
	private static final String NOMBRE_MEDIO_PRENSA = "PRENSA";
	private static final String NOMBRE_MEDIO_OTROS = "OTROS";
	private static final String MEDIO_TELEVISION = "L";
	private static final String MEDIO_RADIO = "M";
	private static final String MEDIO_PRENSA = "P";
	private static final String MEDIO_OTROS = "E";
	
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String TIPO_ORDEN_MEDIOS = "ME";
	
	private static final String NOMBRE_COLOR_BN = "B/N";
	private static final String NOMBRE_COLOR_COLOR = "COLOR";
	private static final String COLOR_BN = "B";
	private static final String COLOR_COLOR = "C";
	
	private static final String NOMBRE_DIA_ORDINARIO = "ORDINARIO";
	private static final String NOMBRE_DIA_SABADO = "SABADO";
	private static final String NOMBRE_DIA_DOMINGO = "DOMINGO ";
	private static final String NOMBRE_DIA_FERIADO = "FERIADO";
	private static final String DIA_ORDINARIO = "O";
	private static final String DIA_SABADO = "S";
	private static final String DIA_DOMINGO = "D";
	private static final String DIA_FERIADO = "F";
	
	private Icon customOpenIconTv = SpiritResourceManager.getImageIcon("images/icons/funcion/pantallaNegra.png");
	private Icon customClosedIconTv = SpiritResourceManager.getImageIcon("images/icons/funcion/pantallaBlanca.png");
	private Icon customLeftIconTv = SpiritResourceManager.getImageIcon("images/icons/funcion/leftNodeNegro.png");
	private Icon customOpenIconPrensa = SpiritResourceManager.getImageIcon("images/icons/funcion/periodicoAmarillo.png");
	private Icon customClosedIconPrensa = SpiritResourceManager.getImageIcon("images/icons/funcion/periodicoAzul.png");
	private Icon customLeftIconPrensa = SpiritResourceManager.getImageIcon("images/icons/funcion/leftNodeNaranja.png");
		
	private static final int MAX_LONGITUD_CODIGO = 10;
	private static final int MAX_LONGITUD_CONCEPTO = 100;
	private static final int MAX_LONGITUD_VALOR = 10; 	
	private static final int MAX_LONGITUD_CANALTV = 100; 
	private static final int MAX_LONGITUD_PROGRAMATV = 100;
	private static final int MAX_LONGITUD_HORAPROGRAMA = 5; 
	private static final int MAX_LONGITUD_DIASPROGRAMA = 13; 
	private static final int MAX_LONGITUD_RATINGPROGRAMA = 6; 
		
	private CorporacionCriteria corporacionCriteria;
	private ClienteCriteria clienteCriteria;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private JDPopupFinderModel popupFinder;
	private CorporacionIf corporacionIf;
	private ClienteIf clienteIf;
	private ClienteOficinaIf clienteOficinaIf;
	protected GrupoObjetivoIf grupoObjetivoIf;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private Double guayaquil;
	private Double quito;
	private Double totalGuayaquilQuito;
	private Double margenGuayaquil;
	private Double margenQuito;
	private Calendar calendarFechaInicio = new GregorianCalendar();
	private Calendar calendarFechaFin = new GregorianCalendar();
	private Calendar calendarSubFechaInicio = new GregorianCalendar();
	private Calendar calendarSubFechaFin = new GregorianCalendar();
	private DefaultTableModel tableSubPeriodo;
	private Vector<PlanMedioMesIf> planMedioMesVector = new Vector<PlanMedioMesIf>();
	private Vector<PlanMedioMesIf> planMedioMesRemovidoVector = new Vector<PlanMedioMesIf>();
	private int planMedioMesSeleccionado;
	private PlanMedioMesIf planMedioMesActualizadoIf;
	private DefaultTableModel tableComercial;
	private TipoOrdenIf tipoOrden;
	private OrdenTrabajoIf ordenTrabajoIf;
	private OrdenTrabajoDetalleIf ordenTrabajoDetalleIf;
	private PlanMedioIf planMedioIf;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no}; 
	private Vector<PrensaTarifaIf> prensaTarifaColeccion = new Vector<PrensaTarifaIf>();
	private CellConstraints cc = new CellConstraints();
	private TableScrollPaneMapaPauta scrollPanelMapaPrensa;
	private TableScrollPaneMapaPauta scrollPanelMapaTv;
	private String[] cabecerasFijasPrensa = new String[]{};
	private String[] cabecerasVariablesPrensa = new String[]{}; 
	private Object[][] datosPrensa = new Object[][]{};
	private int numeroFilasPrensa = 20;
	private String[] cabecerasFijasTv = new String[]{};
	private String[] cabecerasVariablesTv = new String[]{}; 
	private Object[][] datosTv = new Object[][]{};
	private int numeroFilasTv = 20;
	public static Locale esLocale = new Locale("es");
	private Vector<Pauta> pautasTv = new Vector<Pauta>();
	private Vector<Vector<Pauta>> pautasTvColecciones = new Vector<Vector<Pauta>>();
	private Vector<Pauta> pautasTvExtendida = new Vector<Pauta>();
	private Vector<ComercialIf> comercialVectorTabla = new Vector<ComercialIf>();
	private Vector<ComercialIf> comercialesSeleccionadosTabla = new Vector<ComercialIf>();
	private Vector<ComercialIf> comercialesSeleccionadosTablaExtendida = new Vector<ComercialIf>();
	private Vector<String> canales = new Vector<String>();
	private Vector<String> programas = new Vector<String>();
	private Map<Integer,ComercialIf> mapaPautaComerciales = new LinkedHashMap<Integer,ComercialIf>();
	private Vector<DefaultTreeModel> modelArbolTvColecciones = new Vector<DefaultTreeModel>();
	private Vector<TreePath[]> treePathsTvColecciones = new Vector<TreePath[]>();
	private Vector<TableScrollPane> tableScrollPaneTvColecciones = new Vector<TableScrollPane>();
	private TableScrollPane tableScrollTvRemovido = null;
	private Vector<TreePath[]> treePathsPrensaColecciones = new Vector<TreePath[]>();
	private Vector<TableScrollPane> tableScrollPanePrensaColecciones = new Vector<TableScrollPane>();
	private TableScrollPane tableScrollPrensaRemovido = null;
	
	
	public PlanMedioModel(){
		anchoColumnasTabla();
		cargarCombos();
		initKeyListeners();
		showSaveMode();
		initListeners();
		initMapasPauta();
		getTblSubPeriodo().addMouseListener(oMouseAdapterTblSubPeriodo);
		getTblSubPeriodo().addKeyListener(oKeyAdapterTblSubPeriodo);
		cargarTreeTV();
		cargarTreePrensa();
		
		new HotKeyComponent(this);				
	}
	
	private void initKeyListeners() {
		getBtnCorporacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCorporacion().setToolTipText("Buscar Corporaci\u00f3n");
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnOficina().setToolTipText("Buscar Oficina");
		
		getBtnAgregarDetalle().setText("");
		getBtnActualizarDetalle().setText("");
		getBtnEliminarDetalle().setText("");
		
		getBtnGuayaquil().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnGuayaquil().setToolTipText("Recalcular con valor ingresado");
		getBtnQuito().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnQuito().setToolTipText("Recalcular con valor ingresado");
		getBtnTotalCiudad().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnTotalCiudad().setToolTipText("Recalcular con valor ingresado");
		
		getBtnAgregarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarDetalle().setToolTipText("Agregar Detalle");
		getBtnActualizarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarDetalle().setToolTipText("Actualizar Detalle");
		getBtnEliminarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarDetalle().setToolTipText("Eliminar Detalle");
		
		getTxtCodigo().setEditable(false);
		getLblMedio().setVisible(false);
		getCmbMedio().setVisible(false);
		getLblTipoPeriodoMapa().setVisible(false);
		getCmbTipoPeriodoMapa().setVisible(false);
		
		getTblSubPeriodo().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblComercial().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		getArbolTelevision().getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		getArbolPrensa().getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
				
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtConcepto().addKeyListener(new TextChecker(MAX_LONGITUD_CONCEPTO));
		getTxtGuayaquil().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtGuayaquil().addKeyListener(new NumberTextField());
		getTxtQuito().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtQuito().addKeyListener(new NumberTextField());
		getTxtCanalTv().addKeyListener(new TextChecker(MAX_LONGITUD_CANALTV));
		getTxtProgramaTv().addKeyListener(new TextChecker(MAX_LONGITUD_PROGRAMATV));
		getTxtHoraInicioPrograma().addKeyListener(new TextChecker(MAX_LONGITUD_HORAPROGRAMA));
		getTxtHoraFinPrograma().addKeyListener(new TextChecker(MAX_LONGITUD_HORAPROGRAMA));
		getTxtDiasPrograma().addKeyListener(new TextChecker(MAX_LONGITUD_DIASPROGRAMA));
		getTxtRatingTv().addKeyListener(new TextChecker(MAX_LONGITUD_RATINGPROGRAMA));
		getTxtRatingTv().addKeyListener(new NumberTextFieldDecimal());
		getTxtVCunaNegocio().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtVCunaNegocio().addKeyListener(new NumberTextFieldDecimal());
		getTxtVCunaTarifa().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtVCunaTarifa().addKeyListener(new NumberTextFieldDecimal());
		
		getTxtComercialTv().setEditable(false);
		getTxtDerechoPrograma().setEditable(false);
		getTxtVersionPrograma().setEditable(false);
		getTxtDiario().setEditable(false);
		getTxtSeccion().setEditable(false);
		getTxtUbicacion().setEditable(false);
		getTxtFormato().setEditable(false);
		getTxtAnchoColumnas().setEditable(false);
		getTxtAltoModulos().setEditable(false);
		getTxtAnchoCm().setEditable(false);
		getTxtAltoCm().setEditable(false);
		getTxtColor().setEditable(false);
		getTxtDia().setEditable(false);
		getTxtTarifa().setEditable(false);
				
		getCmbTipoPeriodoMapa().setEnabled(false);
		getTxtFechaCreacion().setEditable(false);
		getTxtFechaCreacion().setText(Utilitarios.fechaAhora());
				
		getTxtCorporacion().setEditable(false);
		getTxtCliente().setEditable(false);
		getTxtOficina().setEditable(false);
		getTxtOrdenTrabajoDetalle().setEditable(false);
		getTxtTotalCiudad().setEditable(false);
		getTxtValorTarifa().setEditable(false);
		getTxtValorDescuento().setEditable(false);
		getTxtModificaciones().setEditable(false);
		
		getCmbPeriodoFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbPeriodoFechaFin().setLocale(Utilitarios.esLocale);
		getCmbSubPeriodoFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbSubPeriodoFechaFin().setLocale(Utilitarios.esLocale);
		getCmbPeriodoFechaInicio().setShowNoneButton(false);
		getCmbPeriodoFechaFin().setShowNoneButton(false);
		getCmbSubPeriodoFechaInicio().setShowNoneButton(false);
		getCmbSubPeriodoFechaFin().setShowNoneButton(false);
		
		getCmbPeriodoFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbPeriodoFechaInicio().setEditable(false);
		getCmbPeriodoFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbPeriodoFechaFin().setEditable(false);
		getCmbSubPeriodoFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbSubPeriodoFechaInicio().setEditable(false);
		getCmbSubPeriodoFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbSubPeriodoFechaFin().setEditable(false);
		
		getTxtDiario().setEditable(false);
		getTxtSeccion().setEditable(false);
		getTxtUbicacion().setEditable(false);
		getTxtFormato().setEditable(false);
		getTxtAnchoColumnas().setEditable(false);
		getTxtAltoModulos().setEditable(false);
		getTxtAnchoCm().setEditable(false);
		getTxtAltoCm().setEditable(false);
		getTxtColor().setEditable(false);
		getTxtDia().setEditable(false);
		getTxtTarifa().setEditable(false);
	}
	
	private void initMapasPauta(){
		//Cabeceras fijas: scrollPanelMapaPrensa.getTable().getRowHeaderTable().getColumnModel()
		//Cabeceras de fechas: scrollPanelMapaPrensa.getTable().getMainTable().getColumnModel()
		//Totales por fecha, footer: scrollPanelMapaPrensa.getTable().getColumnFooterTable().getColumnModel()
		//Total por filas: scrollPanelMapaPrensa.getTable().getRowFooterTable().getColumnModel()
		scrollPanelMapaPrensa = new TableScrollPaneMapaPauta(cabecerasFijasPrensa, cabecerasVariablesPrensa, datosPrensa, numeroFilasPrensa);
		getPanelMapaPautaPrensa().add(scrollPanelMapaPrensa.getTable(), cc.xywh(3, 3, 5, 5));
		tableScrollPrensaRemovido = scrollPanelMapaPrensa.getTable();
		
		scrollPanelMapaTv = new TableScrollPaneMapaPauta(cabecerasFijasTv, cabecerasVariablesTv, datosTv, numeroFilasTv);
		getPanelMapaPautaTv().add(scrollPanelMapaTv.getTable(), cc.xywh(3, 3, 5, 5));
		tableScrollTvRemovido = scrollPanelMapaTv.getTable();
	}
	
	private void cargarTreeTV() {
		try {
			DefaultTreeModel treeModel = generateTreeTvModel();
					
			getArbolTelevision().setModel(treeModel);
						
			DefaultTreeCellRenderer customTreeRenderer = new DefaultTreeCellRenderer();
			customTreeRenderer.setOpenIcon(customOpenIconTv);
			customTreeRenderer.setClosedIcon(customClosedIconTv);
			customTreeRenderer.setLeafIcon(customLeftIconTv);
			getArbolTelevision().setCellRenderer(customTreeRenderer);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al generar el árbol de TV!",SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarTreePrensa() {
		DefaultTreeModel treeModel = generateTreePrensaModel();
		getArbolPrensa().setModel(treeModel);
		DefaultTreeCellRenderer customTreeRenderer = new DefaultTreeCellRenderer();
		customTreeRenderer.setOpenIcon(customOpenIconPrensa);
		customTreeRenderer.setClosedIcon(customClosedIconPrensa);
		customTreeRenderer.setLeafIcon(customLeftIconPrensa);		
		getArbolPrensa().setCellRenderer(customTreeRenderer);
	}
	
	private DefaultTreeModel generateTreeTvModel() throws GenericBusinessException {
						
		/*String[][][] arbol = 
		{
				{
					{"ECUAVISA","CANAL UNO"},
					{"VIVOS","CERO TOLERANCIA"},
					{"FUTBOL","NOVELA"},
					{"comercialA","comercialB","comercialC"}
				}, 
				{
					{"programa1","programa2"}, 
					{"comercial1","comercial2"}
				}
			};
		System.out.println("arbol[0][0][0]: " + arbol[0][0][0]);
		System.out.println("arbol[0][0][1]: " + arbol[0][0][1]);
		System.out.println("arbol[0][1][0]: " + arbol[0][1][0]);
		System.out.println("arbol[0][1][1]: " + arbol[0][1][1]);
		System.out.println("arbol[0][2][0]: " + arbol[0][2][0]);
		System.out.println("arbol[0][2][1]: " + arbol[0][2][1]);
		System.out.println("arbol[0][3][0]: " + arbol[0][3][0]);
		System.out.println("arbol[0][3][1]: " + arbol[0][3][1]);
		
		System.out.println("arbol[1][0][0]: " + arbol[1][0][0]);
		System.out.println("arbol[1][0][1]: " + arbol[1][0][1]);
		System.out.println("arbol[1][1][0]: " + arbol[1][1][0]);
		System.out.println("arbol[1][1][1]: " + arbol[1][1][1]);
				
		for(int i=0; i<arbol[0][0].length; i++){
			String nodo = arbol[0][0][i];
			DefaultMutableTreeNode cuentaNodo = new DefaultMutableTreeNode(nodo);
			root.add(cuentaNodo);
			insertarNodosTv(i,cuentaNodo,arbol);
		}*/
		
		DefaultMutableTreeNode rootArbolTv = new DefaultMutableTreeNode("ARBOL DE TELEVISION");	
				
		if(!pautasTv.isEmpty()){
			for(int i=0; i<canales.size(); i++){
				String canal = canales.get(i).toUpperCase();
				DefaultMutableTreeNode canalNodo = new DefaultMutableTreeNode(canal);
				rootArbolTv.add(canalNodo);
				insertarNodosTv(i,canalNodo);
			}
		}		
		
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(rootArbolTv);
		
		int subPeriodoSeleccionado = getCmbTipoPeriodo().getSelectedIndex();
		if(subPeriodoSeleccionado != -1){
			modelArbolTvColecciones.set(subPeriodoSeleccionado, defaultTreeModel);
		}
		
		return defaultTreeModel;
	}

	private void crearColeccionesCanalesProgramas() {
		canales.clear();
		programas.clear();
		for(int i=0; i<pautasTv.size(); i++){
			String canal = pautasTv.get(i).getCanal();
			if(!existeCanal(canales, canal)){
				canales.add(canal);
			}			
		}
		for(int i=0; i<pautasTv.size(); i++){
			programas.add(pautasTv.get(i).getPrograma());
		}
	}

	private void cargarVectorComercialesSeleccionados() {
		comercialesSeleccionadosTabla.clear();
		if(!comercialVectorTabla.isEmpty()){
			boolean comercialSeleccionado = false;
			for(int i=0; i<comercialVectorTabla.size(); i++){
				comercialSeleccionado = (Boolean)getTblComercial().getModel().getValueAt(i,0);
				if(comercialSeleccionado == true){
					comercialesSeleccionadosTabla.add(comercialVectorTabla.get(i));
				}
			}		
		}
	}
	
	public void limpiarSeleccionesTablaComerciales(){
		if(!comercialVectorTabla.isEmpty()){
			for(int i=0; i<comercialVectorTabla.size(); i++){
				getTblComercial().getModel().setValueAt(false, i, 0);				
			}		
		}
	}
	
	private void insertarNodosTv(Integer padre, DefaultMutableTreeNode nodoSeleccionado) {
		for(int k=0; k<programas.size(); k++){
			String nodo = programas.get(k);
			DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(nodo);
			if(pautasTv.get(k).getCanal().toUpperCase().equals(nodoSeleccionado.getUserObject())){
				nodoSeleccionado.add(nodoHijo);
				for(int i=0; i<comercialesSeleccionadosTabla.size(); i++){
					ComercialIf comercial = comercialesSeleccionadosTabla.get(i);
					DefaultMutableTreeNode nodoComercial = new DefaultMutableTreeNode(comercial);
					nodoHijo.add(nodoComercial);
				}
			}			
		}
	}
	
	private void generarComercialesColeccionArbolTv(){
		try {
			mapaPautaComerciales.clear();
			ComercialIf comercialSeleccionado = null;
			String canalSeleccionado = "";
			String programaSeleccionado = "";
			String canal = "";
			String programa = "";
			ComercialIf comercial = null;
			TreePath[] treePaths = getArbolTelevision().getCheckBoxTreeSelectionModel().getSelectionPaths();
			if (treePaths != null) {
			    for (int i = 0; i < treePaths.length; i++) {
			        TreePath path = treePaths[i];
			        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)path.getLastPathComponent();
			        if(nodo != null){
			        	if(nodo.isRoot()){
				        	for(int m=0; m<pautasTvExtendida.size(); m++){
				        		comercial = comercialesSeleccionadosTablaExtendida.get(m);
	        					mapaPautaComerciales.put(pautasTvExtendida.get(m).getId(), comercial);			        				
	        				}			        	
				        }
			        	else if(((DefaultMutableTreeNode) nodo.getParent()).isRoot()){
				        	canalSeleccionado = (String)nodo.getUserObject();
				        	for(int m=0; m<pautasTvExtendida.size(); m++){
	        					canal = pautasTvExtendida.get(m).getCanal().toUpperCase();
	        					comercial = comercialesSeleccionadosTablaExtendida.get(m);
	        					if(canalSeleccionado.equals(canal)){
				        			mapaPautaComerciales.put(pautasTvExtendida.get(m).getId(), comercial);			        			
				        		}	
	        				}
				        }
				        else if(((DefaultMutableTreeNode) nodo.getParent().getParent()).isRoot()){
				        	DefaultMutableTreeNode padre = (DefaultMutableTreeNode) nodo.getParent();
				        	canalSeleccionado = (String)padre.getUserObject();
				        	programaSeleccionado = (String)nodo.getUserObject();
				        	for(int m=0; m<pautasTvExtendida.size(); m++){
	        					canal = pautasTvExtendida.get(m).getCanal().toUpperCase();
				        		programa = pautasTvExtendida.get(m).getPrograma();
				        		comercial = comercialesSeleccionadosTablaExtendida.get(m);
				        		if((canalSeleccionado.equals(canal)) && (programaSeleccionado.equals(programa))){
				        			mapaPautaComerciales.put(pautasTvExtendida.get(m).getId(), comercial);			        			
				        		}	
	        				}			        	
				        }
				        else if(((DefaultMutableTreeNode) nodo.getParent().getParent().getParent()).isRoot()){
				        	DefaultMutableTreeNode abuelo = (DefaultMutableTreeNode) nodo.getParent().getParent();
				        	canalSeleccionado = (String)abuelo.getUserObject();
				        	DefaultMutableTreeNode padre = (DefaultMutableTreeNode) nodo.getParent();
				        	programaSeleccionado = (String)padre.getUserObject();
				        	comercialSeleccionado = (ComercialIf)nodo.getUserObject();
				        	for(int m=0; m<pautasTvExtendida.size(); m++){
	        					canal = pautasTvExtendida.get(m).getCanal().toUpperCase();
				        		programa = pautasTvExtendida.get(m).getPrograma();
				        		comercial = comercialesSeleccionadosTablaExtendida.get(m);
				        		if((canalSeleccionado.equals(canal)) && (programaSeleccionado.equals(programa)) && (comercialSeleccionado.equals(comercial))){
				        			mapaPautaComerciales.put(pautasTvExtendida.get(m).getId(), comercial);			        			
				        		}	
	        				}			        	
				        }
				    }
			    }
			    
			    /*for (Iterator it=mapaPautaComerciales.keySet().iterator(); it.hasNext();){
			    	int idKey = (Integer) it.next();
			    	System.out.println("llaveEx: " + idKey);
			    	System.out.println("nodoEx: " + mapaPautaComerciales.get(idKey));
			    }*/
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al generar la colección de comerciales Tv", SpiritAlert.ERROR);
		}
	}
	
	/*private void insertarNodosTv(Integer padre, DefaultMutableTreeNode nodoSeleccionado, String[][][] arbol) {
		for(int k=0; k<arbol[0][padre+1].length; k++){
			String nodo = arbol[0][padre+1][k];
			DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(nodo);
			nodoSeleccionado.add(nodoHijo);
			if(padre < 2){
				insertarNodosTv(2,nodoHijo,arbol);
			}
		}
	}*/
	
	public boolean existeCanal(Vector<String> canales, String canal){
		for(String canalSeleccionado : canales){
			canalSeleccionado = canalSeleccionado.toUpperCase();
			canal = canal.toUpperCase();
			if(canalSeleccionado.equals(canal)){
				return true;
			}
		}
		return false;
	}
	
	private DefaultTreeModel generateTreePrensaModel() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("ARBOL DE PRENSA");		
		List<ClienteIf> diarios = null;
		
		try {
			Collection tipoProveedorColeccion = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByNombre("PRENSA");
			if(!tipoProveedorColeccion.isEmpty()){
				Long tipoProveedorPrensa = ((TipoProveedorIf)tipoProveedorColeccion.iterator().next()).getId();
				diarios = (List<ClienteIf>) SessionServiceLocator.getClienteSessionService().findClienteByTipoproveedorId(tipoProveedorPrensa);
						
				for(int i=0; i<diarios.size(); i++){
					ClienteIf nodo = diarios.get(i);
					DefaultMutableTreeNode diarioNodo = new DefaultMutableTreeNode(nodo);
					root.add(diarioNodo);
					insertarNodosSeccion(diarioNodo,nodo);
				}
			}			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el árbol de prensa", SpiritAlert.ERROR);
		}
		
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);
		return defaultTreeModel;
	}
	
	private void insertarNodosSeccion(DefaultMutableTreeNode nodoSeleccionado, ClienteIf diario) {
		try {
			List<PrensaSeccionIf> prensaSecciones = (List<PrensaSeccionIf>) SessionServiceLocator.getPrensaSeccionSessionService().findPrensaSeccionByClienteId(diario.getId());
			for(int k=0; k<prensaSecciones.size(); k++){
				PrensaSeccionIf nodoSeccion = prensaSecciones.get(k);
				DefaultMutableTreeNode nodoHijoSeccion = new DefaultMutableTreeNode(nodoSeccion);
				nodoSeleccionado.add(nodoHijoSeccion);
				insertarNodosUbicacion(nodoHijoSeccion, diario, nodoSeccion);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void insertarNodosUbicacion(DefaultMutableTreeNode nodoSeleccionado, ClienteIf diario, PrensaSeccionIf nodoSeccion) {
		try {
			List<PrensaUbicacionIf> prensaUbicaciones = (List<PrensaUbicacionIf>) SessionServiceLocator.getPrensaUbicacionSessionService().findPrensaUbicacionByClienteId(diario.getId());
			for(int m=0; m<prensaUbicaciones.size(); m++){
				PrensaUbicacionIf nodoUbicacion = prensaUbicaciones.get(m);
				DefaultMutableTreeNode nodoHijoUbicacion = new DefaultMutableTreeNode(nodoUbicacion);
				nodoSeleccionado.add(nodoHijoUbicacion);
				insertarNodosFormato(nodoHijoUbicacion, diario, nodoSeccion, nodoUbicacion);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void insertarNodosFormato(DefaultMutableTreeNode nodoSeleccionado, ClienteIf diario, PrensaSeccionIf nodoSeccion, PrensaUbicacionIf nodoUbicacion) {
		try {
			List<PrensaFormatoIf> prensaFormatos = (List<PrensaFormatoIf>) SessionServiceLocator.getPrensaFormatoSessionService().findPrensaFormatoByClienteId(diario.getId());
			Map aMap = new HashMap();
			for(int n=0; n<prensaFormatos.size(); n++){
				PrensaFormatoIf nodoFormato = prensaFormatos.get(n);
				DefaultMutableTreeNode nodoHijoFormato = new DefaultMutableTreeNode(nodoFormato);
				aMap.clear();
				aMap.put("prensaUbicacionId", nodoUbicacion.getId());
				aMap.put("prensaFormatoId", nodoFormato.getId());
				if(!SessionServiceLocator.getPrensaTarifaSessionService().findPrensaTarifaByQuery(aMap).isEmpty()){
					nodoSeleccionado.add(nodoHijoFormato);
					insertarNodosTarifa(nodoHijoFormato, diario, nodoSeccion, nodoUbicacion, nodoFormato);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void insertarNodosTarifa(DefaultMutableTreeNode nodoSeleccionado, ClienteIf diario, PrensaSeccionIf nodoSeccion, PrensaUbicacionIf nodoUbicacion, PrensaFormatoIf nodoFormato) {
		try {
			Map aMap = new HashMap();
			aMap.put("clienteId", diario.getId());
			aMap.put("prensaSeccionId", nodoSeccion.getId());
			aMap.put("prensaUbicacionId", nodoUbicacion.getId());
			aMap.put("prensaFormatoId", nodoFormato.getId());
			List<PrensaTarifaIf> prensaTarifas = (List<PrensaTarifaIf>) SessionServiceLocator.getPrensaTarifaSessionService().findPrensaTarifaByQuery(aMap);
			for(int p=0; p<prensaTarifas.size(); p++){
				PrensaTarifaIf nodoTarifa = prensaTarifas.get(p);
				
				DefaultMutableTreeNode nodoHijoUbicacion = new DefaultMutableTreeNode(nodoTarifa);
				nodoSeleccionado.add(nodoHijoUbicacion);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumnaComercial = getTblComercial().getColumnModel().getColumn(0);
		anchoColumnaComercial.setMinWidth(10);
		anchoColumnaComercial = getTblComercial().getColumnModel().getColumn(1);
		anchoColumnaComercial.setMinWidth(70);
		anchoColumnaComercial = getTblComercial().getColumnModel().getColumn(2);
		anchoColumnaComercial.setMinWidth(300);
		anchoColumnaComercial = getTblComercial().getColumnModel().getColumn(3);
		anchoColumnaComercial.setMinWidth(120);
		anchoColumnaComercial = getTblComercial().getColumnModel().getColumn(4);
		anchoColumnaComercial.setMinWidth(40);
		anchoColumnaComercial = getTblComercial().getColumnModel().getColumn(5);
		anchoColumnaComercial.setMinWidth(40);
		
		/*TableColumn anchoColumnaMapaTv = getTblMapaPautaTv().getColumnModel().getColumn(0);
		anchoColumnaMapaTv.setMinWidth(50);
		anchoColumnaMapaTv = getTblMapaPautaTv().getColumnModel().getColumn(1);
		anchoColumnaMapaTv.setMinWidth(120);
		anchoColumnaMapaTv = getTblMapaPautaTv().getColumnModel().getColumn(2);
		anchoColumnaMapaTv.setMinWidth(30);
		anchoColumnaMapaTv = getTblMapaPautaTv().getColumnModel().getColumn(3);
		anchoColumnaMapaTv.setMinWidth(30);
		anchoColumnaMapaTv = getTblMapaPautaTv().getColumnModel().getColumn(4);
		anchoColumnaMapaTv.setMinWidth(40);*/
	}
	
	public void cargarCombos(){
		cargarComboEstado();
		cargarComboGrupoObjetivo();
		
		getCmbTipo().removeAllItems();
		getCmbTipo().addItem(NOMBRE_TIPO_LANZAMIENTO);
		getCmbTipo().addItem(NOMBRE_TIPO_MANTENIMIENTO);
		getCmbTipo().addItem(NOMBRE_TIPO_PROMOCIONAL);
		getCmbTipo().addItem(NOMBRE_TIPO_EXPECTATIVA);
		getCmbTipo().setSelectedIndex(0);
		
		getCmbMedio().removeAllItems();
		getCmbMedio().addItem(NOMBRE_MEDIO_TELEVISION);
		getCmbMedio().addItem(NOMBRE_MEDIO_RADIO);
		getCmbMedio().addItem(NOMBRE_MEDIO_PRENSA);
		getCmbMedio().addItem(NOMBRE_MEDIO_OTROS);
		getCmbMedio().setSelectedIndex(0);
		
		/*getCmbTipoPeriodoMapa().removeAllItems();
		getCmbTipoPeriodoMapa().addItem(NOMBRE_TIPO_LANZAMIENTO);
		getCmbTipoPeriodoMapa().addItem(NOMBRE_TIPO_MANTENIMIENTO);
		getCmbTipoPeriodoMapa().addItem(NOMBRE_TIPO_PROMOCIONAL);
		getCmbTipoPeriodoMapa().addItem(NOMBRE_TIPO_EXPECTATIVA);
		getCmbTipoPeriodoMapa().setSelectedIndex(0);
		
		getCmbMedioMapa().removeAllItems();
		getCmbMedioMapa().addItem(NOMBRE_MEDIO_TELEVISION);
		getCmbMedioMapa().addItem(NOMBRE_MEDIO_RADIO);
		getCmbMedioMapa().addItem(NOMBRE_MEDIO_PRENSA);
		getCmbMedioMapa().addItem(NOMBRE_MEDIO_OTROS);
		getCmbMedioMapa().setSelectedIndex(0);*/
	}

	private void cargarComboEstado() {
		getCmbEstado().removeAllItems();
		getCmbEstado().addItem(NOMBRE_ESTADO_EN_PROCESO);
		getCmbEstado().addItem(NOMBRE_ESTADO_PENDIENTE);
		getCmbEstado().addItem(NOMBRE_ESTADO_APROBADO);
		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_EN_PROCESO);
	}
	
	private void cargarComboGrupoObjetivo(){
		try {
			List target = (List)SessionServiceLocator.getGrupoObjetivoSessionService().findGrupoObjetivoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTarget(), target);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar combo Target", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	MouseListener oMouseAdapterTblSubPeriodo = new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblSubPeriodo = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setPlanMedioMesSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			planMedioMesActualizadoIf = (PlanMedioMesIf)  getPlanMedioMesVector().get(getPlanMedioMesSeleccionado());
			
			if(planMedioMesActualizadoIf.getTipo().equals(TIPO_LANZAMIENTO))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_LANZAMIENTO);
			else if(planMedioMesActualizadoIf.getTipo().equals(TIPO_MANTENIMIENTO))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_MANTENIMIENTO);
			else if(planMedioMesActualizadoIf.getTipo().equals(TIPO_PROMOCIONAL))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_PROMOCIONAL);
			else if(planMedioMesActualizadoIf.getTipo().equals(TIPO_EXPECTATIVA))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_EXPECTATIVA);
			
			calendarSubFechaInicio.setTime(planMedioMesActualizadoIf.getFechaInicio());
			getCmbSubPeriodoFechaInicio().setCalendar(calendarSubFechaInicio);
			getCmbSubPeriodoFechaInicio().repaint();
			calendarSubFechaFin.setTime(planMedioMesActualizadoIf.getFechaFin());
			getCmbSubPeriodoFechaFin().setCalendar(calendarSubFechaFin);
			getCmbSubPeriodoFechaFin().repaint();
		}
	}
	
	private void initListeners() {
		getBtnCorporacion().addActionListener(oActionListenerBtnCorporacion);
		getBtnCliente().addActionListener(oActionListenerBtnCliente);
		getBtnOficina().addActionListener(oActionListenerBtnOficina);
		getCmbTarget().addActionListener(oActionListenerCmbTarget);
		getCmbPeriodoFechaInicio().addActionListener(oActionListenerCmbPeriodoFechaInicio);
		getCmbPeriodoFechaFin().addActionListener(oActionListenerCmbPeriodoFechaFin);
		getBtnAgregarDetalle().addActionListener(oActionListenerBtnAgregarDetalle);
		getBtnActualizarDetalle().addActionListener(oActionListenerBtnActualizarDetalle);
		getBtnEliminarDetalle().addActionListener(oActionListenerBtnRemoverDetalle);
		getBtnTvData().addActionListener(oActionListenerBtnTvData);
		getBtnCrearMapaPautaTv().addActionListener(oActionListenerBtnCrearMapaPautaTv);
		getBtnCrearMapaPautaPrensa().addActionListener(oActionListenerBtnCrearMapaPautaPrensa);
		getBtnAgregarProgramaTv().addActionListener(oActionListenerBtnAgregarProgramaTv);
		getBtnEliminarProgramaTv().addActionListener(oActionListenerBtnEliminarProgramaTv);
		getCmbTipoPeriodo().addActionListener(oActionListenerCmbTipoPeriodo);
		getCmbOrdenTrabajo().addActionListener(oActionListenerCmbOrdenTrabajo);
		
		getArbolPrensa().addTreeSelectionListener(new TreeSelectionListener() {
		    public void valueChanged(TreeSelectionEvent e) {
		    	listenerCheckBoxTreePrensa();
		    }
		});
		
		getArbolTelevision().addTreeSelectionListener(new TreeSelectionListener() {
		    public void valueChanged(TreeSelectionEvent e) {
		    	listenerCheckBoxTreeTv();
		    }
		});
	}
	
	ActionListener oActionListenerCmbOrdenTrabajo = new ActionListener() {
		public void actionPerformed(ActionEvent evento){
			ordenTrabajoIf = (OrdenTrabajoIf) getCmbOrdenTrabajo().getSelectedItem();
			cargarTablaComercial();
		}		
	};
	
	ActionListener oActionListenerCmbTipoPeriodo = new ActionListener() {
		public void actionPerformed(ActionEvent evento){
			try{
				//PARA TV
				limpiarSeleccionesTablaComerciales();
				cleanTv();
				
				int subPeriodoSeleccionado = getCmbTipoPeriodo().getSelectedIndex();
				if(!modelArbolTvColecciones.isEmpty()){
					if(modelArbolTvColecciones.get(subPeriodoSeleccionado) != null){
						getArbolTelevision().setModel(modelArbolTvColecciones.get(subPeriodoSeleccionado));
						
						DefaultTreeCellRenderer customTreeRenderer = new DefaultTreeCellRenderer();
						customTreeRenderer.setOpenIcon(customOpenIconTv);
						customTreeRenderer.setClosedIcon(customClosedIconTv);
						customTreeRenderer.setLeafIcon(customLeftIconTv);
						getArbolTelevision().setCellRenderer(customTreeRenderer);
						
						if(treePathsTvColecciones.get(subPeriodoSeleccionado) != null){
							getArbolTelevision().getCheckBoxTreeSelectionModel().setSelectionPaths(treePathsTvColecciones.get(subPeriodoSeleccionado));	
							
							getPanelMapaPautaTv().remove(tableScrollTvRemovido);
							getPanelMapaPautaTv().add(tableScrollPaneTvColecciones.get(subPeriodoSeleccionado), cc.xywh(3, 3, 5, 5));
							tableScrollTvRemovido = tableScrollPaneTvColecciones.get(subPeriodoSeleccionado);
						}else{
							getPanelMapaPautaTv().remove(tableScrollTvRemovido);
							cleanArbolMapaTv();
						}					
					}else{
						getPanelMapaPautaTv().remove(tableScrollTvRemovido);
						cleanArbolMapaTv();
					}
				}			
				getPanelMapaPautaTv().validate();
				getPanelMapaPautaTv().repaint();
				
				//PARA PRENSA
				limpiarSeleccionesTablaComerciales();
				cleanPrensa();
				
				if(!treePathsPrensaColecciones.isEmpty()){
					if(treePathsPrensaColecciones.get(subPeriodoSeleccionado) != null){
						getArbolPrensa().getCheckBoxTreeSelectionModel().setSelectionPaths(treePathsPrensaColecciones.get(subPeriodoSeleccionado));	
						
						getPanelMapaPautaPrensa().remove(tableScrollPrensaRemovido);
						getPanelMapaPautaPrensa().add(tableScrollPanePrensaColecciones.get(subPeriodoSeleccionado), cc.xywh(3, 3, 5, 5));
						tableScrollPrensaRemovido = tableScrollPanePrensaColecciones.get(subPeriodoSeleccionado);
					}else{
						getArbolPrensa().getCheckBoxTreeSelectionModel().clearSelection();
						getPanelMapaPautaPrensa().remove(tableScrollPrensaRemovido);
						cleanMapaPrensa();
					}	
				}					
				getPanelMapaPautaPrensa().validate();
				getPanelMapaPautaPrensa().repaint();
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
	};
	
	ActionListener oActionListenerBtnAgregarProgramaTv = new ActionListener() {
		public void actionPerformed(ActionEvent evento){
			if(validateFieldsNodoTv()){
				Pauta pautaExtra = new Pauta();
				String nuevoPrograma = getTxtProgramaTv().getText();
				boolean error = false;
				try{
					//rootArbolTv.removeAllChildren();
					pautaExtra.setCanal(getTxtCanalTv().getText());
					pautaExtra.setPrograma(nuevoPrograma);
					java.util.Date fechaInicio = new java.util.Date();
					String[] horaInicio = getTxtHoraInicioPrograma().getText().split(":");
					int horasInicio = Integer.parseInt(horaInicio[0]);
					int minutosInicio = Integer.parseInt(horaInicio[1]);
					fechaInicio.setHours(horasInicio);
					fechaInicio.setMinutes(minutosInicio);
					pautaExtra.setHoraInicio(fechaInicio);
					java.util.Date fechaFin = new java.util.Date();
					String[] horaFin = getTxtHoraFinPrograma().getText().split(":");
					int horasFin = Integer.parseInt(horaFin[0]);
					int minutosFin = Integer.parseInt(horaFin[1]);
					fechaFin.setHours(horasFin);
					fechaFin.setMinutes(minutosFin);
					pautaExtra.setHoraFinal(fechaFin);
					String[] dias = getTxtDiasPrograma().getText().split("-");
					char[] arregloDias = new char[dias.length];
					for(int i=0; i<dias.length; i++){
						arregloDias[i] = dias[i].charAt(0);
					}
					pautaExtra.setDias(arregloDias);
					pautaExtra.setRating(Double.parseDouble(getTxtRatingTv().getText()));
					int id = pautasTv.size() + 1;
					pautaExtra.setId(id);
				}catch(Exception e){
					error = true;
					e.printStackTrace();
					SpiritAlert.createAlert("Los datos del programa nuevo no han sido ingresados correctamente", SpiritAlert.WARNING);
				}
				if(!error){
					pautasTv.add(pautaExtra);
					crearColeccionesCanalesProgramas();
					cargarTreeTV();
					searchAndExpand(nuevoPrograma);		
				}							
			}
		}		
	};
	
	ActionListener oActionListenerBtnEliminarProgramaTv = new ActionListener() {
		public void actionPerformed(ActionEvent evento){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) getArbolTelevision().getLastSelectedPathComponent();
			String canal = "";
			String programa = "";
			if(!pautasTv.isEmpty() && node != null && node.getParent() != null){
				if(((DefaultMutableTreeNode) node.getParent().getParent()).isRoot()){
					DefaultMutableTreeNode nodoPadre = (DefaultMutableTreeNode) node.getParent();
					canal = (String) nodoPadre.getUserObject();
					programa = (String) node.getUserObject();
				}
				else if(node.getUserObject() instanceof ComercialIf){
					DefaultMutableTreeNode nodoAbuelo = (DefaultMutableTreeNode) node.getParent().getParent();
					DefaultMutableTreeNode nodoPadre = (DefaultMutableTreeNode) node.getParent();
					canal = (String) nodoAbuelo.getUserObject();
					programa = (String) nodoPadre.getUserObject();
				}
				
				int pautaEliminada = -1;
				for(int i=0; i<pautasTv.size(); i++){
					if((pautasTv.get(i).getCanal().toUpperCase().equals(canal)) && (pautasTv.get(i).getPrograma().toUpperCase().equals(programa))){
						pautaEliminada = i;				
					}
				}
				if(pautaEliminada != -1){
					pautasTv.remove(pautaEliminada);
					crearColeccionesCanalesProgramas();
					cargarTreeTV();
					cleanTv();	
				}				
			}else{
				SpiritAlert.createAlert("Debe seleccionar primero el Programa que desea Eliminar", SpiritAlert.WARNING);
			}
		}
	};
	
	public void generarPautasComercialesColeccionExtendida(){
		pautasTvExtendida.clear();
		comercialesSeleccionadosTablaExtendida.clear();
		Pauta pautaExtra = null;
		int id = 0;
		for(int i=0; i<pautasTv.size(); i++){
			for(int j=0; j<comercialesSeleccionadosTabla.size(); j++){
				id++;
				pautaExtra = new Pauta();
				pautaExtra.setId(id);								
				pautaExtra.setCanal(pautasTv.get(i).getCanal());
				pautaExtra.setPrograma(pautasTv.get(i).getPrograma());
				pautaExtra.setHoraInicio(pautasTv.get(i).getHoraInicio());
				pautaExtra.setHoraFinal(pautasTv.get(i).getHoraFinal());
				pautaExtra.setDias(pautasTv.get(i).getDias());
				pautaExtra.setRating(pautasTv.get(i).getRating());
				pautaExtra.setDuracion(pautasTv.get(i).getDuracion());
				pautasTvExtendida.add(pautaExtra);
				comercialesSeleccionadosTablaExtendida.add(comercialesSeleccionadosTabla.get(j));
			}			
		}
	}
	
	public boolean searchAndExpand(String text) {
        TreeNode[] path = search ((DefaultMutableTreeNode) getArbolTelevision().getModel().getRoot(), text);

        if (path != null) {
            TreePath treePath = new TreePath(path);
            getArbolTelevision().scrollPathToVisible(treePath);
            getArbolTelevision().setSelectionPath(treePath);
            return true;
        }
        
        return false;
    }
	
	private TreeNode[] search(DefaultMutableTreeNode node, Object object) {
        TreeNode[] path = null;

        if (node.getUserObject().equals(object)) {
            path = ((DefaultTreeModel)getArbolTelevision().getModel()).getPathToRoot(node);
        } else {
            int i = 0;
            int n = ((DefaultTreeModel)getArbolTelevision().getModel()).getChildCount(node);

            while ((i < n) && (path == null)) {
                path = search((DefaultMutableTreeNode) ((DefaultTreeModel)getArbolTelevision().getModel()).getChild (node, i), object);
                i++;
            }
        }

        return path;
    }
		
	private void listenerCheckBoxTreeTv() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) getArbolTelevision().getLastSelectedPathComponent();
		String canal = "";
		String programa = "";
		ComercialIf comercial = null;
		if(node != null){
			if(node.getParent() != null){
				if(((DefaultMutableTreeNode) node.getParent()).isRoot()){
					cleanTv();
					canal = (String) node.getUserObject();
					getTxtCanalTv().setText(canal.toUpperCase());
				}
				else if(((DefaultMutableTreeNode) node.getParent().getParent()).isRoot()){
					cleanTv();
					DefaultMutableTreeNode nodoPadre = (DefaultMutableTreeNode) node.getParent();
					canal = (String) nodoPadre.getUserObject();
					programa = (String) node.getUserObject();
					comercial = null;
					setearInfoNodosTv(canal, programa, comercial);					
				}
				else if(node.getUserObject() instanceof ComercialIf){
					cleanTv();
					comercial = (ComercialIf) node.getUserObject();
					DefaultMutableTreeNode nodoAbuelo = (DefaultMutableTreeNode) node.getParent().getParent();
					DefaultMutableTreeNode nodoPadre = (DefaultMutableTreeNode) node.getParent();
					canal = (String) nodoAbuelo.getUserObject();
					programa = (String) nodoPadre.getUserObject();
					setearInfoNodosTv(canal, programa, comercial);
				}
			}			
			else{
				cleanTv();
			}
		}
		else{
			cleanTv();
		}
	}
	
	private void setearInfoNodosTv(String canal, String programa, ComercialIf comercial) {
		try {
			getTxtCanalTv().setText(canal.toUpperCase());
			getTxtProgramaTv().setText(programa);
			Pauta pauta = null;
			int subPeriodoSeleccionado = getCmbTipoPeriodo().getSelectedIndex();
			pautasTv = pautasTvColecciones.get(subPeriodoSeleccionado);
			for(int i=0; i<pautasTv.size(); i++){
				if((pautasTv.get(i).getCanal().toUpperCase().equals(canal)) && (pautasTv.get(i).getPrograma().toUpperCase().equals(programa))){
					pauta = pautasTv.get(i);
				}
			}
			if(pauta != null){
				getTxtHoraInicioPrograma().setText(generarHora(pauta.getHoraInicio()));
				getTxtHoraInicioPrograma().repaint();
				getTxtHoraFinPrograma().setText(generarHora(pauta.getHoraFinal()));
				getTxtHoraFinPrograma().repaint();
				char[] arregloDias = pauta.getDias();
				String dias = "";
				for(int i=0; i<arregloDias.length; i++){
					dias = dias + "-" + arregloDias[i];
				}
				getTxtDiasPrograma().setText(dias.substring(1));
				getTxtDiasPrograma().repaint();
				getTxtRatingTv().setText(String.valueOf(pauta.getRating()));
				getTxtRatingTv().repaint();
			}
			if(comercial != null){
				getTxtComercialTv().setText(comercial.getNombre() + " (" + comercial.getDuracion() +  " seg)");
				DerechoProgramaIf derechoPrograma = SessionServiceLocator.getDerechoProgramaSessionService().getDerechoPrograma(comercial.getDerechoprogramaId());
				getTxtDerechoPrograma().setText(derechoPrograma.getNombre());
				getTxtVersionPrograma().setText(comercial.getVersion());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al setear información de nodos Tv", SpiritAlert.ERROR);
		}
	}
	
	public String generarHora(java.util.Date fecha){
		String hora = "";
		
		String horas = String.valueOf(fecha.getHours());
		if(horas.length() == 1){
			horas = "0"+horas; 
		}
		String minutos = String.valueOf(fecha.getMinutes());
		if(minutos.length() == 1){
			minutos = "0"+minutos; 
		}
		hora = horas + ":" + minutos;
		
		return hora;
	}
	
	ActionListener oActionListenerBtnTvData = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if(!planMedioMesVector.isEmpty()){
				cargarVectorComercialesSeleccionados();
				int subPeriodoSeleccionado = getCmbTipoPeriodo().getSelectedIndex();
				if(!comercialesSeleccionadosTabla.isEmpty()){
					pautasTv = new Vector<Pauta>();
					pautasTv = PautaImporter.retornarPautas();
					if(subPeriodoSeleccionado != -1){
						pautasTvColecciones.set(subPeriodoSeleccionado, pautasTv);
					}
					if(pautasTv != null){
						crearColeccionesCanalesProgramas();
						cleanArbolMapaTv();
						cargarTreeTV();
						getBtnAgregarProgramaTv().setEnabled(true);
					}else{
						pautasTv = new Vector<Pauta>();
						getBtnAgregarProgramaTv().setEnabled(false);
					}
				}
				else{
					SpiritAlert.createAlert("Antes de cargar el archivo, debe seleccionar al menos 1 comercial", SpiritAlert.INFORMATION);
					getTblComercial().grabFocus();
				}
			}
			else{
				SpiritAlert.createAlert("Antes de generar el Mapa de Pauta, al menos ingrese 1 subPeriodo en Planificación", SpiritAlert.INFORMATION);
			}		
		}		
	};
	
	private void listenerCheckBoxTreePrensa() {
		try {
    		DefaultMutableTreeNode node = (DefaultMutableTreeNode) getArbolPrensa().getLastSelectedPathComponent();
    		ClienteIf cliente = null;
    		PrensaTarifaIf prensaTarifa = null;
    		PrensaFormatoIf prensaFormato = null;
    		PrensaUbicacionIf prensaUbicacion = null;
    		PrensaSeccionIf prensaSeccion = null;
    		if(node != null){
    			if(node.getUserObject() instanceof ClienteIf){
		    		cleanPrensa();
		    		cliente = (ClienteIf) node.getUserObject();
		    		getTxtDiario().setText(cliente.getNombreLegal());
		    	}
		    	else if(node.getUserObject() instanceof PrensaSeccionIf){
		    		cleanPrensa();
		    		prensaSeccion = (PrensaSeccionIf) node.getUserObject();
		    		cliente = SessionServiceLocator.getClienteSessionService().getCliente(prensaSeccion.getClienteId());
		    		getTxtDiario().setText(cliente.getNombreLegal());
		    		getTxtSeccion().setText(prensaSeccion.getSeccion());
		    	}
		    	else if(node.getUserObject() instanceof PrensaUbicacionIf){
		    		cleanPrensa();
		    		prensaUbicacion = (PrensaUbicacionIf) node.getUserObject();
		    		prensaSeccion = (PrensaSeccionIf)((DefaultMutableTreeNode) node.getParent()).getUserObject();
		    		cliente = SessionServiceLocator.getClienteSessionService().getCliente(prensaUbicacion.getClienteId());
		    		getTxtDiario().setText(cliente.getNombreLegal());
		    		getTxtSeccion().setText(prensaSeccion.getSeccion());
		    		getTxtUbicacion().setText(prensaUbicacion.getUbicacion());
		    	}
		    	else if(node.getUserObject() instanceof PrensaFormatoIf){
		    		cleanPrensa();
		    		prensaFormato = (PrensaFormatoIf) node.getUserObject();
		    		prensaUbicacion = (PrensaUbicacionIf)((DefaultMutableTreeNode) node.getParent()).getUserObject();
		    		prensaSeccion = (PrensaSeccionIf)((DefaultMutableTreeNode) node.getParent().getParent()).getUserObject();
		    		cliente = SessionServiceLocator.getClienteSessionService().getCliente(prensaUbicacion.getClienteId());
		    		prensaTarifa = null;
		    		setearInfoNodosPrensa(prensaTarifa, prensaFormato, prensaUbicacion, prensaSeccion, cliente);
		    	}
		    	else if(node.getUserObject() instanceof PrensaTarifaIf){
		    		cleanPrensa();
		    		prensaTarifa = (PrensaTarifaIf) node.getUserObject();
		    		prensaFormato = (PrensaFormatoIf)((DefaultMutableTreeNode) node.getParent()).getUserObject();
		    		prensaUbicacion = (PrensaUbicacionIf)((DefaultMutableTreeNode) node.getParent().getParent()).getUserObject();
		    		prensaSeccion = (PrensaSeccionIf)((DefaultMutableTreeNode) node.getParent().getParent().getParent()).getUserObject();
		    		cliente = SessionServiceLocator.getClienteSessionService().getCliente(prensaUbicacion.getClienteId());
		    		setearInfoNodosPrensa(prensaTarifa, prensaFormato, prensaUbicacion, prensaSeccion, cliente);
		    	}
		    	else{
		    		cleanPrensa();
		    	}
    		}
    		else{
	    		cleanPrensa();
	    	}		    		
    	} catch (GenericBusinessException e1) {
			e1.printStackTrace();
			SpiritAlert.createAlert("Error en el listener del árbol de prensa", SpiritAlert.ERROR);
		}
	}

	private void setearInfoNodosPrensa(PrensaTarifaIf prensaTarifa, PrensaFormatoIf prensaFormato, PrensaUbicacionIf prensaUbicacion, PrensaSeccionIf prensaSeccion, ClienteIf cliente) {
		getTxtDiario().setText(cliente.getNombreLegal());
		getTxtSeccion().setText(prensaSeccion.getSeccion());
		getTxtUbicacion().setText(prensaUbicacion.getUbicacion());
		getTxtFormato().setText(prensaFormato.getFormato());
		getTxtAnchoColumnas().setText(prensaFormato.getAnchoColumnas().toString());
		if(prensaFormato.getAltoModulos() != null){
			getTxtAltoModulos().setText(prensaFormato.getAltoModulos().toString());
		}
		getTxtAnchoCm().setText(prensaFormato.getAnchoCm().toString());
		getTxtAltoCm().setText(prensaFormato.getAltoCm().toString());
		String color = "", dia= "";
		if(prensaTarifa != null){
			if(prensaTarifa.getColor().equals(COLOR_COLOR)){
				color = NOMBRE_COLOR_COLOR;
			}else if(prensaTarifa.getColor().equals(COLOR_BN)){
				color = NOMBRE_COLOR_BN;
			}
			getTxtColor().setText(color);
			if(prensaTarifa.getDia().equals(DIA_DOMINGO)){
				dia = NOMBRE_DIA_DOMINGO;
			}else if(prensaTarifa.getDia().equals(DIA_ORDINARIO)){
				dia = NOMBRE_DIA_ORDINARIO;
			}else if(prensaTarifa.getDia().equals(DIA_SABADO)){
				dia = NOMBRE_DIA_SABADO;
			}else if(prensaTarifa.getDia().equals(DIA_FERIADO)){
				dia = NOMBRE_DIA_FERIADO;
			}
			getTxtDia().setText(dia);
			getTxtTarifa().setText(formatoDecimal.format(prensaTarifa.getTarifa()));
		}
	} 	
	
	ActionListener oActionListenerBtnCrearMapaPautaPrensa = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if(!planMedioMesVector.isEmpty()){
				TreePath[] treePaths = getArbolPrensa().getCheckBoxTreeSelectionModel().getSelectionPaths();
				
				int subPeriodoSeleccionado = getCmbTipoPeriodo().getSelectedIndex();
				if(subPeriodoSeleccionado != -1){
					treePathsPrensaColecciones.set(subPeriodoSeleccionado, treePaths);	
				}
				
				if(treePaths != null){
					generarPrensaTarifaColeccion();
		            Vector<String> diasSubPeriodoColeccion = getDiasPeriodoColeccion();
					
					cabecerasFijasPrensa = new String[diasSubPeriodoColeccion.size()+5];
					cabecerasFijasPrensa[0] = "#";
					cabecerasFijasPrensa[1] = "DIARIO";
					cabecerasFijasPrensa[2] = "SECCION";
					cabecerasFijasPrensa[3] = "UBICACION";
					cabecerasFijasPrensa[4] = "FORMATO";
					for(int i=0; i < diasSubPeriodoColeccion.size(); i++){
						cabecerasFijasPrensa[i+5] = diasSubPeriodoColeccion.get(i);
					}
									
					for(int i=0; i < (diasSubPeriodoColeccion.size()+5); i++){
						System.out.println("cabecerasFijas"+i+": "+cabecerasFijasPrensa[i]);
					}
					
					cabecerasVariablesPrensa = new String[diasSubPeriodoColeccion.size()-1];
					for(int i=0; i < (diasSubPeriodoColeccion.size()-1); i++){
						cabecerasVariablesPrensa[i] = diasSubPeriodoColeccion.get(i);
					}
					
					Vector<Object[]> arregloDatosPrensa = generarArregloDatosPrensa(diasSubPeriodoColeccion);
					
					getPanelMapaPautaPrensa().remove(scrollPanelMapaPrensa.getTable());
					numeroFilasPrensa = arregloDatosPrensa.size();
					scrollPanelMapaPrensa = new TableScrollPaneMapaPauta(cabecerasFijasPrensa, cabecerasVariablesPrensa, datosPrensa, numeroFilasPrensa);
					setearPreferenciasMapaPautaPrensa();
					
					if(subPeriodoSeleccionado != -1){
						tableScrollPanePrensaColecciones.set(subPeriodoSeleccionado, scrollPanelMapaPrensa.getTable());	
					}
					
					getPanelMapaPautaPrensa().add(scrollPanelMapaPrensa.getTable(), cc.xywh(3, 3, 5, 5));
					tableScrollPrensaRemovido = scrollPanelMapaPrensa.getTable();
					
					getJtpPlanMedio().setSelectedIndex(3);
					getTpMapasPauta().setSelectedIndex(2);
				}
				else{
					SpiritAlert.createAlert("Debe seleccionar al menos 1 tarifa para crear el mapa de pauta", SpiritAlert.INFORMATION);
				}
			}
			else{
				SpiritAlert.createAlert("Antes de generar el Mapa de Pauta, al menos ingrese 1 subPeriodo en Planificación", SpiritAlert.INFORMATION);
			}
		}		
	};
	
	private Vector<Object[]> generarArregloDatosTv(Vector<String> diasPeriodoColeccion){
		Vector<Object[]> arregloDatosTv = new Vector<Object[]>();
		ComercialIf comercial = null;
		Pauta pauta = null;
		Object[] datoTv;
		DerechoProgramaIf derechoPrograma = null;
		try {
			for(int j=0; j<pautasTvExtendida.size(); j++){
				if(mapaPautaComerciales.get(j+1) != null){
					comercial = mapaPautaComerciales.get(j+1);
					pauta = pautasTvExtendida.get(j);
				}else{
					comercial = null;
					pauta = null;
				}
				if((comercial!=null)&&(pauta!=null)){
					datoTv = new Object[diasPeriodoColeccion.size()+6];
					datoTv[0] = null;
					datoTv[1] = pauta.getCanal().toUpperCase();
					datoTv[2] = pauta.getPrograma();
					datoTv[3] = generarHora(pauta.getHoraInicio());
					datoTv[4] = generarHora(pauta.getHoraFinal());
					derechoPrograma = SessionServiceLocator.getDerechoProgramaSessionService().getDerechoPrograma(comercial.getDerechoprogramaId());
					datoTv[5] = comercial.getNombre()+"-"+comercial.getVersion()+"-"+derechoPrograma.getNombre();
					for(int i=0; i < (diasPeriodoColeccion.size()-1); i++){
						datoTv[i+6] = "";
					}
					arregloDatosTv.add(datoTv);
				}				
			}
											
			datosTv = new Object[arregloDatosTv.size()][];
			for(int i=0; i < arregloDatosTv.size(); i++){
				datosTv[i] = arregloDatosTv.get(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al generar el arreglo de datos de Tv", SpiritAlert.ERROR);
		}
		return arregloDatosTv;
	}
	
	private Vector<Object[]> generarArregloDatosPrensa(Vector<String> diasPeriodoColeccion){
		Vector<Object[]> arregloDatosPrensa = new Vector<Object[]>();
		ClienteIf cliente = null;
		PrensaSeccionIf prensaSeccion = null;
		PrensaUbicacionIf prensaUbicacion = null;
		PrensaFormatoIf prensaFormato = null;
		Object[] datoPrensa;
		try {
			for(PrensaTarifaIf prensaTarifaSeleccionado : prensaTarifaColeccion){
				cliente = SessionServiceLocator.getClienteSessionService().getCliente(prensaTarifaSeleccionado.getClienteId());
				prensaSeccion = SessionServiceLocator.getPrensaSeccionSessionService().getPrensaSeccion(prensaTarifaSeleccionado.getPrensaSeccionId());
				prensaUbicacion = SessionServiceLocator.getPrensaUbicacionSessionService().getPrensaUbicacion(prensaTarifaSeleccionado.getPrensaUbicacionId());
				prensaFormato = SessionServiceLocator.getPrensaFormatoSessionService().getPrensaFormato(prensaTarifaSeleccionado.getPrensaFormatoId());
				
				datoPrensa = new Object[diasPeriodoColeccion.size()+5];
				datoPrensa[0] = null;
				datoPrensa[1] = cliente.getNombreLegal();
				datoPrensa[2] = prensaSeccion.getSeccion();
				datoPrensa[3] = prensaUbicacion.getUbicacion();
				datoPrensa[4] = prensaFormato.getFormato();
				for(int i=0; i < (diasPeriodoColeccion.size()-1); i++){
					datoPrensa[i+5] = "";
				}
				arregloDatosPrensa.add(datoPrensa);
			}
											
			datosPrensa = new Object[arregloDatosPrensa.size()][];
			for(int i=0; i < arregloDatosPrensa.size(); i++){
				datosPrensa[i] = arregloDatosPrensa.get(i);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al generar el arreglo de datos de prensa", SpiritAlert.ERROR);
		}
		return arregloDatosPrensa;
	}
	
	private void generarPrensaTarifaColeccion(){
		try {
			prensaTarifaColeccion.clear();
			ClienteIf cliente = null;
			PrensaSeccionIf prensaSeccion = null;
			PrensaUbicacionIf prensaUbicacion = null;
			PrensaFormatoIf prensaFormato = null;	
			PrensaTarifaIf prensaTarifa = null;
			TreePath[] treePaths = getArbolPrensa().getCheckBoxTreeSelectionModel().getSelectionPaths();
			if (treePaths != null) {
			    for (int i = 0; i < treePaths.length; i++) {
			        TreePath path = treePaths[i];
			        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)path.getLastPathComponent();
			        System.out.println("nodo seleccionado"+i+": "+nodo.getUserObject());
			        if(nodo.getUserObject() instanceof PrensaTarifaIf){
			        	prensaTarifa = (PrensaTarifaIf) nodo.getUserObject();
			        	if(!prensaVectorContieneElemento(prensaTarifa)){
			        		prensaTarifaColeccion.add(prensaTarifa);
			        	}
			        }
			        else if(nodo.getUserObject() instanceof PrensaFormatoIf){
			        	prensaFormato = (PrensaFormatoIf) nodo.getUserObject();
			        	Collection prensaTarifaSeleccionados = SessionServiceLocator.getPrensaTarifaSessionService().findPrensaTarifaByPrensaFormatoId(prensaFormato.getId());
						Iterator prensaTarifaSeleccionadosIterator = prensaTarifaSeleccionados.iterator();
			        	while(prensaTarifaSeleccionadosIterator.hasNext()){
			        		prensaTarifa = (PrensaTarifaIf) prensaTarifaSeleccionadosIterator.next();
			        		if(!prensaVectorContieneElemento(prensaTarifa)){
			            		prensaTarifaColeccion.add(prensaTarifa);
			            	}
			        	}
			        }
			        else if(nodo.getUserObject() instanceof PrensaUbicacionIf){
			        	prensaUbicacion = (PrensaUbicacionIf) nodo.getUserObject();
			        	Collection prensaTarifaSeleccionados = SessionServiceLocator.getPrensaTarifaSessionService().findPrensaTarifaByPrensaUbicacionId(prensaUbicacion.getId());
						Iterator prensaTarifaSeleccionadosIterator = prensaTarifaSeleccionados.iterator();
			        	while(prensaTarifaSeleccionadosIterator.hasNext()){
			        		prensaTarifa = (PrensaTarifaIf) prensaTarifaSeleccionadosIterator.next();
			        		if(!prensaVectorContieneElemento(prensaTarifa)){
			            		prensaTarifaColeccion.add(prensaTarifa);
			            	}
			        	}
			        }
			        else if(nodo.getUserObject() instanceof PrensaSeccionIf){
			        	prensaSeccion = (PrensaSeccionIf) nodo.getUserObject();
			        	Collection prensaTarifaSeleccionados = SessionServiceLocator.getPrensaTarifaSessionService().findPrensaTarifaByPrensaSeccionId(prensaSeccion.getId());
						Iterator prensaTarifaSeleccionadosIterator = prensaTarifaSeleccionados.iterator();
			        	while(prensaTarifaSeleccionadosIterator.hasNext()){
			        		prensaTarifa = (PrensaTarifaIf) prensaTarifaSeleccionadosIterator.next();
			        		if(!prensaVectorContieneElemento(prensaTarifa)){
			            		prensaTarifaColeccion.add(prensaTarifa);
			            	}
			        	}
			        }
			        else if(nodo.getUserObject() instanceof ClienteIf){
			        	cliente = (ClienteIf) nodo.getUserObject();
			        	Collection prensaTarifaSeleccionados = SessionServiceLocator.getPrensaTarifaSessionService().findPrensaTarifaByClienteId(cliente.getId());
						Iterator prensaTarifaSeleccionadosIterator = prensaTarifaSeleccionados.iterator();
			        	while(prensaTarifaSeleccionadosIterator.hasNext()){
			        		prensaTarifa = (PrensaTarifaIf) prensaTarifaSeleccionadosIterator.next();
			        		if(!prensaVectorContieneElemento(prensaTarifa)){
			            		prensaTarifaColeccion.add(prensaTarifa);
			            	}
			        	}
			        }
			        else{
			        	Collection prensaTarifaSeleccionados = SessionServiceLocator.getPrensaTarifaSessionService().getPrensaTarifaList();
						Iterator prensaTarifaSeleccionadosIterator = prensaTarifaSeleccionados.iterator();
			        	while(prensaTarifaSeleccionadosIterator.hasNext()){
			        		prensaTarifa = (PrensaTarifaIf) prensaTarifaSeleccionadosIterator.next();
			        		if(!prensaVectorContieneElemento(prensaTarifa)){
			            		prensaTarifaColeccion.add(prensaTarifa);
			            	}
			        	}
			        }         
			    }
			    for(PrensaTarifaIf prensaTarifaSeleccion : prensaTarifaColeccion){
			    	System.out.println("prensaTarifaSeleccion: " + prensaTarifaSeleccion.getId()+"-"+prensaTarifaSeleccion);
			    }
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al generar al colección de tarifas de prensa", SpiritAlert.ERROR);
		}
	}
	
	private Vector<String> getDiasPeriodoColeccion() {
		int tipoPeriodoSeleccionado = getCmbTipoPeriodo().getSelectedIndex();
		PlanMedioMesIf planMedioMesSeleccionado = getPlanMedioMesVector().get(tipoPeriodoSeleccionado);
		Date inicioPeriodoSeleccionado = Utilitarios.fromTimestampToSqlDate(planMedioMesSeleccionado.getFechaInicio());
		Date finPeriodoSeleccionado = Utilitarios.fromTimestampToSqlDate(planMedioMesSeleccionado.getFechaFin());
		Long intervalo = finPeriodoSeleccionado.getTime() - inicioPeriodoSeleccionado.getTime();
		Long diasPeriodo = (intervalo / 86400000) + 1; //886400000 = 1000*60*60*24
		System.out.println("diasPeriodo: " + diasPeriodo);
		int diaInicioPeriodo = inicioPeriodoSeleccionado.getDate();
		int mesInicioPeriodo = inicioPeriodoSeleccionado.getMonth(); 
		int anioInicioPeriodo = inicioPeriodoSeleccionado.getYear(); 
		Vector<String> diasPeriodoColeccion = new Vector<String>();
		Date diaPeriodo;
		for(int i=0; i<diasPeriodo; i++){
			int ultimoDiaMes = Utilitarios.getLastDayOfMonth(mesInicioPeriodo, anioInicioPeriodo);
			
			if(diaInicioPeriodo<=ultimoDiaMes){
				diaPeriodo = new Date(anioInicioPeriodo, mesInicioPeriodo, diaInicioPeriodo);
				diaInicioPeriodo++;
				diasPeriodoColeccion.add(Utilitarios.getFechaEEEddMMM(diaPeriodo));
			}
			else {
				diaInicioPeriodo = 1;
				if(mesInicioPeriodo<11){
					mesInicioPeriodo++;
				}else{
					mesInicioPeriodo=0;
					anioInicioPeriodo++;
				}							
				diaPeriodo = new Date(anioInicioPeriodo, mesInicioPeriodo, diaInicioPeriodo);
				diaInicioPeriodo++;
				diasPeriodoColeccion.add(Utilitarios.getFechaEEEddMMM(diaPeriodo));
			}
		}
		diasPeriodoColeccion.add("CUÑAS");
		
		return diasPeriodoColeccion;
	}
	
	private void setearPreferenciasMapaPautaPrensa() {
		scrollPanelMapaPrensa.getTable().getMainTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanelMapaPrensa.getTable().getRowHeaderTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		scrollPanelMapaPrensa.getTable().getMainTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPanelMapaPrensa.getTable().getRowHeaderTable().getColumnModel().getColumn(0).setPreferredWidth(24);
		scrollPanelMapaPrensa.getTable().getRowHeaderTable().getColumnModel().getColumn(1).setPreferredWidth(80);
		scrollPanelMapaPrensa.getTable().getRowHeaderTable().getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPanelMapaPrensa.getTable().getRowHeaderTable().getColumnModel().getColumn(3).setPreferredWidth(100);
		scrollPanelMapaPrensa.getTable().getRowHeaderTable().getColumnModel().getColumn(4).setPreferredWidth(100);
		scrollPanelMapaPrensa.getTable().getRowFooterTable().getColumnModel().getColumn(0).setPreferredWidth(50);
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		scrollPanelMapaPrensa.getTable().getRowFooterTable().getColumnModel().getColumn(0).setCellRenderer(tableCellRenderer);
	}
	
	private void setearPreferenciasMapaPautaTv() {
		scrollPanelMapaTv.getTable().getMainTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanelMapaTv.getTable().getRowHeaderTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPanelMapaTv.getTable().getMainTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPanelMapaTv.getTable().getRowHeaderTable().getColumnModel().getColumn(0).setPreferredWidth(24);
		scrollPanelMapaTv.getTable().getRowHeaderTable().getColumnModel().getColumn(1).setPreferredWidth(80);
		scrollPanelMapaTv.getTable().getRowHeaderTable().getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPanelMapaTv.getTable().getRowHeaderTable().getColumnModel().getColumn(3).setPreferredWidth(45);
		scrollPanelMapaTv.getTable().getRowHeaderTable().getColumnModel().getColumn(4).setPreferredWidth(45);
		scrollPanelMapaTv.getTable().getRowHeaderTable().getColumnModel().getColumn(5).setPreferredWidth(120);
		scrollPanelMapaTv.getTable().getRowFooterTable().getColumnModel().getColumn(0).setPreferredWidth(50);
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		scrollPanelMapaTv.getTable().getRowFooterTable().getColumnModel().getColumn(0).setCellRenderer(tableCellRenderer);
	}
	
	protected boolean prensaVectorContieneElemento(PrensaTarifaIf prensaTarifa) {
		for(PrensaTarifaIf prensaTarifaEnVector : prensaTarifaColeccion){
			if(prensaTarifaEnVector.getId().equals(prensaTarifa.getId())){
				return true;
			}
		}
		return false;
	}
	
	ActionListener oActionListenerBtnCrearMapaPautaTv = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			try{
			if(!planMedioMesVector.isEmpty()){
				TreePath[] treePaths = getArbolTelevision().getCheckBoxTreeSelectionModel().getSelectionPaths();
				
				int subPeriodoSeleccionado = getCmbTipoPeriodo().getSelectedIndex();
				if(subPeriodoSeleccionado != -1){
					treePathsTvColecciones.set(subPeriodoSeleccionado, treePaths);
				}
				
				if(treePaths != null){
					generarPautasComercialesColeccionExtendida();
					generarComercialesColeccionArbolTv();
					Vector<String> diasSubPeriodoColeccion = getDiasPeriodoColeccion();
					
					cabecerasFijasTv = new String[diasSubPeriodoColeccion.size()+6];
					cabecerasFijasTv[0] = "#";
					cabecerasFijasTv[1] = "CANAL";
					cabecerasFijasTv[2] = "PROGRAMA";
					cabecerasFijasTv[3] = "H.INI";
					cabecerasFijasTv[4] = "H.FIN";
					cabecerasFijasTv[5] = "COMERCIAL";
					for(int i=0; i < diasSubPeriodoColeccion.size(); i++){
						cabecerasFijasTv[i+6] = diasSubPeriodoColeccion.get(i);
					}
									
					for(int i=0; i < (diasSubPeriodoColeccion.size()+6); i++){
						System.out.println("cabecerasFijasTv"+i+": "+cabecerasFijasTv[i]);	
					}
					
					cabecerasVariablesTv = new String[diasSubPeriodoColeccion.size()-1];
					for(int i=0; i < (diasSubPeriodoColeccion.size()-1); i++){
						cabecerasVariablesTv[i] = diasSubPeriodoColeccion.get(i);
					}
					
					Vector<Object[]> arregloDatosTv = generarArregloDatosTv(diasSubPeriodoColeccion);
										
					/*
					cabecerasFijasTv = new String[]{"#", "CANAL", "PROGRAMA", "H.INI", "H.FIN", "COMERCIAL", "Oct-Lu-22", "Oct-Ma-23", "Oct-Mi-24", "Oct-Ju-25", "Oct-Vi-26", "Oct-Sa-27", "Oct-Do-28", "Oct-Lu-29", "TOTAL"};
					cabecerasVariablesTv = new String[]{"Oct-Lu-22", "Oct-Ma-23", "Oct-Mi-24", "Oct-Ju-25", "Oct-Vi-26", "Oct-Sa-27", "Oct-Do-28", "Oct-Lu-29"};
								
					datosTv = new Object[][]{
			            new Object[]{null, "ECUAVISA", "VIVOS II", "22:34", "23:41", "COMERCIAL A", "", "2", "", "2", "", "", "", "", null},
			            new Object[]{null, "TC TELEVISION", "FLASH INFORMATIVO", "22:02", "22:06", "COMERCIAL B", "", "", "", "", "4", "", "", "", null},
			        };
					*/
					
					getPanelMapaPautaTv().remove(scrollPanelMapaTv.getTable());
					numeroFilasTv = arregloDatosTv.size();
					scrollPanelMapaTv = new TableScrollPaneMapaPauta(cabecerasFijasTv, cabecerasVariablesTv, datosTv, numeroFilasTv);
					setearPreferenciasMapaPautaTv();
					
					if(subPeriodoSeleccionado != -1){
						tableScrollPaneTvColecciones.set(subPeriodoSeleccionado, scrollPanelMapaTv.getTable());	
					}
					
					getPanelMapaPautaTv().add(scrollPanelMapaTv.getTable(), cc.xywh(3, 3, 5, 5));
					tableScrollTvRemovido = scrollPanelMapaTv.getTable();
					 
					getJtpPlanMedio().setSelectedIndex(3);
					getTpMapasPauta().setSelectedIndex(0);
				}
				else{
					SpiritAlert.createAlert("Debe seleccionar al menos 1 comercial para crear el mapa de pauta", SpiritAlert.INFORMATION);
				}				
			}
			else{
				SpiritAlert.createAlert("Antes de generar el Mapa de Pauta, al menos ingrese 1 subPeriodo en Planificación", SpiritAlert.INFORMATION);
			}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	};
	
	ActionListener oActionListenerBtnCorporacion = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.add(80);
			anchoColumnas.add(500);	
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
			if(popupFinder.getElementoSeleccionado() != null){
				corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
				getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
				clienteIf = null;
				clienteOficinaIf = null;
				getTxtCliente().setText("");
				getTxtOficina().setText("");
			}
		}
	};
	
	ActionListener oActionListenerBtnCliente = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			Long idCorporacion = 0L;

			if(corporacionIf != null){
				idCorporacion = corporacionIf.getId();
			}

			clienteCriteria = new ClienteCriteria("Clientes", idCorporacion, CODIGO_TIPO_CLIENTE);
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.add(80);
			anchoColumnas.add(300);
			anchoColumnas.add(300);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
			if ( popupFinder.getElementoSeleccionado() != null ){
				clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
				getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
				cleanTablaComercial();
				cargarTablaComercial();
				if(corporacionIf == null){
					try {
						corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
						getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error al setear el nombre de la Corporación", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}
				
				clienteOficinaIf = null;
				getTxtOficina().setText("");
				
				try {
					Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(clienteIf.getId());
					if ( oficinas.size() == 1 ) {
						clienteOficinaIf = oficinas.iterator().next();
						setClienteOficina();
					}
				} catch (Exception e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error al consultar la oficina del cliente", SpiritAlert.ERROR);
				}
			}
		}
	};
	
	ActionListener oActionListenerBtnOficina = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			Long idCorporacion = 0L;
			Long idCliente = 0L;
			String tipoCliente = "CL";
			String tituloVentanaBusqueda = "Oficinas de Clientes";
						
			if (corporacionIf != null)
				idCorporacion = corporacionIf.getId();
				
			if (clienteIf != null)
				idCliente = clienteIf.getId();
			
			clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, "", false);
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.addElement(70);
			anchoColumnas.addElement(200);
			anchoColumnas.addElement(80);
			anchoColumnas.addElement(230);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria,	JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
			if(popupFinder.getElementoSeleccionado() != null){
				clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
				if (clienteIf == null) {
					try {
						clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
						getTxtCliente().setText(clienteIf.getNombreLegal());
						if (corporacionIf == null) {
							corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
							getTxtCorporacion().setText(corporacionIf.getNombre());
						}
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}
				setClienteOficina();
				cleanTablaComercial();
				cargarTablaComercial();
			}
		}		
	};
	
	private void setClienteOficina() {
		getTxtOficina().setText(clienteOficinaIf.getCodigo() + " - "+ clienteOficinaIf.getDescripcion());
		cargarOrdenTrabajo();
	}
	
	private void cargarOrdenTrabajo() {
		try {
			boolean saveMode = false;
			if(getMode() == SpiritMode.SAVE_MODE)
				saveMode = true;
			
			ArrayList<OrdenTrabajoIf> ordenesTrabajo = (ArrayList) SessionServiceLocator.getOrdenTrabajoSessionService().findOrdenTrabajoPendienteByClienteOficinaIdAndTipoOrden(clienteOficinaIf.getId(),TIPO_ORDEN_MEDIOS,saveMode);
			if(!ordenesTrabajo.isEmpty()){
				SpiritComboBoxModel cmbModelOrdenTrabajo = new SpiritComboBoxModel(ordenesTrabajo);
				getCmbOrdenTrabajo().setModel(cmbModelOrdenTrabajo);
				getCmbOrdenTrabajo().setSelectedIndex(0);
				getCmbOrdenTrabajo().setEnabled(true);
				tipoOrden = (TipoOrdenIf)SessionServiceLocator.getTipoOrdenSessionService().findTipoOrdenByTipo("M").iterator().next();
				ordenTrabajoIf = (OrdenTrabajoIf) getCmbOrdenTrabajo().getSelectedItem();
				ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajoAndEstado(tipoOrden.getId(), ordenTrabajoIf.getId()).iterator().next();
				getTxtOrdenTrabajoDetalle().setText(tipoOrden.getNombre());
			} else {
				getCmbOrdenTrabajo().removeAllItems();
				getCmbOrdenTrabajo().setSelectedIndex(-1);
				getCmbOrdenTrabajo().setEnabled(false);
				SpiritAlert.createAlert("No se encontraron órdenes de trabajo pendientes para el cliente seleccionado\n" + 
										", debe ingresarse una para poder guardar el Plan de Medios", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar combo Orden de Trabajo", SpiritAlert.ERROR);
			e.printStackTrace();
		}		
	}
	
	ActionListener oActionListenerCmbTarget = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			listenerCmbTarget();			
		}
	};
	
	private void listenerCmbTarget() {
		grupoObjetivoIf = (GrupoObjetivoIf) getCmbTarget().getModel().getSelectedItem();

		if(grupoObjetivoIf != null){
			guayaquil = grupoObjetivoIf.getCiudad1().doubleValue();
			quito = grupoObjetivoIf.getCiudad2().doubleValue();
			totalGuayaquilQuito = guayaquil + quito;

			margenGuayaquil = guayaquil / totalGuayaquilQuito;
			margenQuito = quito / totalGuayaquilQuito;

			getTxtGuayaquil().setText(formatoDecimal.format(grupoObjetivoIf.getCiudad1()));
			getTxtQuito().setText(formatoDecimal.format(grupoObjetivoIf.getCiudad2()));
			getTxtTotalCiudad().setText(formatoDecimal.format(totalGuayaquilQuito));
			getBtnGuayaquil().setEnabled(true);
			getBtnQuito().setEnabled(true);
			getBtnTotalCiudad().setEnabled(true);
		}
	}
	
	ActionListener oActionListenerCmbPeriodoFechaInicio = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if(getCmbPeriodoFechaInicio().getCalendar() != null){
				calendarFechaInicio = getCmbPeriodoFechaInicio().getCalendar();
				
				if(calendarFechaInicio.after(calendarFechaFin)){
					getCmbPeriodoFechaFin().setCalendar(calendarFechaInicio);
				}
				
				getCmbSubPeriodoFechaInicio().getDateModel().setMinDate(calendarFechaInicio);
				getCmbSubPeriodoFechaFin().getDateModel().setMinDate(calendarFechaInicio);
				getCmbSubPeriodoFechaInicio().getDateModel().setMaxDate(calendarFechaFin);
				getCmbSubPeriodoFechaFin().getDateModel().setMaxDate(calendarFechaFin);
				getCmbSubPeriodoFechaInicio().setCalendar(calendarFechaInicio);
			}
		}
	};
	
	ActionListener oActionListenerCmbPeriodoFechaFin = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if(getCmbPeriodoFechaFin().getCalendar() != null){
				calendarFechaFin = getCmbPeriodoFechaFin().getCalendar();
				
				if(calendarFechaFin.before(calendarFechaInicio)){
					getCmbPeriodoFechaInicio().setCalendar(calendarFechaFin);
				}
				
				getCmbSubPeriodoFechaInicio().getDateModel().setMaxDate(calendarFechaFin);
				getCmbSubPeriodoFechaFin().getDateModel().setMaxDate(calendarFechaFin);
				getCmbSubPeriodoFechaInicio().getDateModel().setMinDate(calendarFechaInicio);
				getCmbSubPeriodoFechaFin().getDateModel().setMinDate(calendarFechaInicio);
				getCmbSubPeriodoFechaFin().setCalendar(calendarFechaFin);
			}
		}
	};
	
	ActionListener oActionListenerBtnAgregarDetalle = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			PlanMedioMesIf bean = new PlanMedioMesData();
			setPlanMedioMesActualizadoIf(bean);
			
			if(getCmbTipo().getSelectedItem().equals(NOMBRE_TIPO_EXPECTATIVA))
				getPlanMedioMesActualizadoIf().setTipo(TIPO_EXPECTATIVA);
			else if(getCmbTipo().getSelectedItem().equals(NOMBRE_TIPO_LANZAMIENTO))
				getPlanMedioMesActualizadoIf().setTipo(TIPO_LANZAMIENTO);
			else if(getCmbTipo().getSelectedItem().equals(NOMBRE_TIPO_MANTENIMIENTO))
				getPlanMedioMesActualizadoIf().setTipo(TIPO_MANTENIMIENTO);
			else if(getCmbTipo().getSelectedItem().equals(NOMBRE_TIPO_PROMOCIONAL))
				getPlanMedioMesActualizadoIf().setTipo(TIPO_PROMOCIONAL);
			
			getPlanMedioMesActualizadoIf().setFechaInicio(Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(getCmbSubPeriodoFechaInicio().getDate().getTime())));
			getPlanMedioMesActualizadoIf().setFechaFin(Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(getCmbSubPeriodoFechaFin().getDate().getTime())));
			
			getPlanMedioMesVector().add(getPlanMedioMesActualizadoIf());
			
			tableSubPeriodo = (DefaultTableModel) getTblSubPeriodo().getModel();
			Vector<String> fila = new Vector<String>();
			String tipo = getCmbTipo().getSelectedItem().toString();
			String subPeriodoFechaInicio = Utilitarios.getFechaUppercase(getCmbSubPeriodoFechaInicio().getDate());
			String subPeriodoFechaFin = Utilitarios.getFechaUppercase(getCmbSubPeriodoFechaFin().getDate());
			fila.add(tipo);
			fila.add(subPeriodoFechaInicio);
			fila.add(subPeriodoFechaFin);
			tableSubPeriodo.addRow(fila);
			
			getCmbTipoPeriodo().addItem(tipo + " (" + subPeriodoFechaInicio + " al " + subPeriodoFechaFin + ")");
			getCmbTipoPeriodoMapa().addItem(tipo + " (" + subPeriodoFechaInicio + " al " + subPeriodoFechaFin + ")");
			
			modelArbolTvColecciones.add(null);
			treePathsTvColecciones.add(null);
			tableScrollPaneTvColecciones.add(null);
			pautasTvColecciones.add(null);
			
			treePathsPrensaColecciones.add(null);
			tableScrollPanePrensaColecciones.add(null);
		}
	};
	
	ActionListener oActionListenerBtnActualizarDetalle = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			int filaSeleccionada = getTblSubPeriodo().getSelectedRow();
			if (filaSeleccionada >= 0) {
				if(getCmbTipo().getSelectedItem().equals(NOMBRE_TIPO_EXPECTATIVA))
					getPlanMedioMesActualizadoIf().setTipo(TIPO_EXPECTATIVA);
				else if(getCmbTipo().getSelectedItem().equals(NOMBRE_TIPO_LANZAMIENTO))
					getPlanMedioMesActualizadoIf().setTipo(TIPO_LANZAMIENTO);
				else if(getCmbTipo().getSelectedItem().equals(NOMBRE_TIPO_MANTENIMIENTO))
					getPlanMedioMesActualizadoIf().setTipo(TIPO_MANTENIMIENTO);
				else if(getCmbTipo().getSelectedItem().equals(NOMBRE_TIPO_PROMOCIONAL))
					getPlanMedioMesActualizadoIf().setTipo(TIPO_PROMOCIONAL);
				
				getPlanMedioMesActualizadoIf().setFechaInicio(Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(getCmbSubPeriodoFechaInicio().getDate().getTime())));
				getPlanMedioMesActualizadoIf().setFechaFin(Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(getCmbSubPeriodoFechaFin().getDate().getTime())));
				
				getPlanMedioMesVector().add(filaSeleccionada, getPlanMedioMesActualizadoIf());
				getPlanMedioMesVector().remove(filaSeleccionada + 1);
				
				tableSubPeriodo = (DefaultTableModel) getTblSubPeriodo().getModel();
				Vector<String> fila = new Vector<String>();
				String tipo = getCmbTipo().getSelectedItem().toString();
				String subPeriodoFechaInicio = Utilitarios.getFechaUppercase(getCmbSubPeriodoFechaInicio().getDate());
				String subPeriodoFechaFin = Utilitarios.getFechaUppercase(getCmbSubPeriodoFechaFin().getDate());
				fila.add(tipo);
				fila.add(subPeriodoFechaInicio);
				fila.add(subPeriodoFechaFin);
				tableSubPeriodo.insertRow(filaSeleccionada, fila);
				tableSubPeriodo.removeRow(filaSeleccionada + 1);
				
				getCmbTipoPeriodo().insertItemAt(tipo + " (" + subPeriodoFechaInicio + " al " + subPeriodoFechaFin + ")" , filaSeleccionada);
				getCmbTipoPeriodo().removeItemAt(filaSeleccionada + 1);
				getCmbTipoPeriodoMapa().insertItemAt(tipo + " (" + subPeriodoFechaInicio + " al " + subPeriodoFechaFin + ")" , filaSeleccionada);
				getCmbTipoPeriodoMapa().removeItemAt(filaSeleccionada + 1);
			}else{
				SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
			}
		}
	};
	
	ActionListener oActionListenerBtnRemoverDetalle = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			int filaSeleccionada = getTblSubPeriodo().getSelectedRow();
			if (filaSeleccionada >= 0) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					planMedioMesRemovidoVector.add(getPlanMedioMesVector().get(filaSeleccionada));
					getPlanMedioMesVector().remove(filaSeleccionada);
					tableSubPeriodo.removeRow(filaSeleccionada);
					getCmbTipoPeriodo().removeItemAt(filaSeleccionada);
					getCmbTipoPeriodoMapa().removeItemAt(filaSeleccionada);
					
					modelArbolTvColecciones.remove(filaSeleccionada);
					treePathsTvColecciones.remove(filaSeleccionada);
					tableScrollPaneTvColecciones.remove(filaSeleccionada);
					pautasTvColecciones.remove(filaSeleccionada);
					
					treePathsPrensaColecciones.remove(filaSeleccionada);
					tableScrollPanePrensaColecciones.remove(filaSeleccionada);
				}
			} else {
				SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.WARNING);
			}
		}
	};

	private Map buildQuery() {
		Map aMap = new HashMap();

		if ("".equals(getTxtCodigo().getText()) == false)
			aMap.put("codigo", getTxtCodigo().getText() + "%");
		else
			aMap.put("codigo", "%");

		if ("".equals(getTxtConcepto().getText()) == false)
			aMap.put("concepto", "%" + getTxtConcepto().getText() + "%");
		else
			aMap.put("concepto", "%");

		if (getCmbEstado().getSelectedItem() != null){
			if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_EN_PROCESO))
				aMap.put("estado", ESTADO_EN_PROCESO);
			else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PENDIENTE))
				aMap.put("estado", ESTADO_PENDIENTE);
			else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_APROBADO))
				aMap.put("estado", ESTADO_APROBADO);
			else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ELIMINADO))
				aMap.put("estado", ESTADO_ELIMINADO);
		}
		
		if (getCmbPeriodoFechaInicio().getSelectedItem() != null)
			aMap.put("fechaini", new java.sql.Date(getCmbPeriodoFechaInicio().getDate().getTime()));

		if (getCmbPeriodoFechaFin().getSelectedItem() != null)
			aMap.put("fechafin", new java.sql.Date(getCmbPeriodoFechaFin().getDate().getTime()));
		
		return aMap;
	}
	
	public void find() {
		try {
			Map mapa = buildQuery();
			int tamanoLista = 0;
			Long clienteId = null;
			if (clienteIf != null){
				clienteId = clienteIf.getId();
				tamanoLista = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByQueryAndByIdClienteSize(mapa,clienteId, Parametros.getIdEmpresa());
			}
			else{
				tamanoLista = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByQuerySize(mapa, Parametros.getIdEmpresa());
			}
			
			if (tamanoLista > 0) {
				PlanMedioCriteria planMedioCriteria = new PlanMedioCriteria(clienteId);
				planMedioCriteria.setResultListSize(tamanoLista);
				planMedioCriteria.setQueryBuilded(mapa);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
										planMedioCriteria,
										JDPopupFinderModel.BUSQUEDA_TODOS);
				if ( popupFinder.getElementoSeleccionado() != null )
					getSelectedObject();
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la busqueda de información", SpiritAlert.ERROR);
		}
	}
	
	public void getSelectedObject(){
		try {
			planMedioIf = (PlanMedioIf) popupFinder.getElementoSeleccionado();

			////PLAN MEDIO 
			
			getTxtCodigo().setText(planMedioIf.getCodigo());
			getTxtFechaCreacion().setText(Utilitarios.getFechaUppercase(planMedioIf.getFechaCreacion())); 
			if(planMedioIf.getEstado().equals(ESTADO_EN_PROCESO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_EN_PROCESO);
			else if(planMedioIf.getEstado().equals(ESTADO_PENDIENTE))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_PENDIENTE);
			else if(planMedioIf.getEstado().equals(ESTADO_APROBADO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_APROBADO);
			else if(planMedioIf.getEstado().equals(ESTADO_ELIMINADO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ELIMINADO);
						
			tipoOrden = (TipoOrdenIf)SessionServiceLocator.getTipoOrdenSessionService().findTipoOrdenByTipo("M").iterator().next();
			ordenTrabajoDetalleIf = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(planMedioIf.getOrdenTrabajoDetalleId());
			ordenTrabajoIf = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalleIf.getOrdenId());
			clienteOficinaIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenTrabajoIf.getClienteoficinaId());
			clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
			corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
			getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
			getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
			getTxtOficina().setText(clienteOficinaIf.getDescripcion());
			
			boolean saveMode = false;
			if(getMode() == SpiritMode.SAVE_MODE)
				saveMode = true;
			
			SpiritComboBoxModel cmbModelOrdenTrabajo = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getOrdenTrabajoSessionService().findOrdenTrabajoPendienteByClienteOficinaIdAndTipoOrden(clienteOficinaIf.getId(),TIPO_ORDEN_MEDIOS,saveMode));
			getCmbOrdenTrabajo().setModel(cmbModelOrdenTrabajo);
			getCmbOrdenTrabajo().setEnabled(true);
			getCmbOrdenTrabajo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbOrdenTrabajo(), ordenTrabajoIf.getId()));
			getCmbOrdenTrabajo().repaint();
			getTxtOrdenTrabajoDetalle().setText(tipoOrden.getNombre());
			getTxtConcepto().setText(planMedioIf.getConcepto());
			
			getCmbTarget().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTarget(), planMedioIf.getGrupoObjetivoId()));
			getCmbTarget().repaint();
			listenerCmbTarget();
			getCmbPeriodoFechaInicio().setDate(planMedioIf.getFechaInicio());
			getCmbPeriodoFechaFin().setDate(planMedioIf.getFechaFin());
			getTxtCobertura().setText(planMedioIf.getCobertura());
			
			if(planMedioIf.getConsideraciones() != null){
				getTxtOtrasConsideraciones().setText(planMedioIf.getConsideraciones());
			}
			
			////PLAN MEDIO MES
			
			Collection planMedioMesColeccion = SessionServiceLocator.getPlanMedioMesSessionService().findPlanMedioMesByPlanmedioId(planMedioIf.getId());
			Iterator planMedioMesIterator = planMedioMesColeccion.iterator();
			
			while(planMedioMesIterator.hasNext()){
				PlanMedioMesIf planMedioMesIf = (PlanMedioMesIf) planMedioMesIterator.next();
				getPlanMedioMesVector().add(planMedioMesIf);
				
				tableSubPeriodo = (DefaultTableModel) getTblSubPeriodo().getModel();
				Vector<String> fila = new Vector<String>();
				String tipo = "";
				String subPeriodoFechaInicio = Utilitarios.getFechaUppercase(planMedioMesIf.getFechaInicio());
				String subPeriodoFechaFin = Utilitarios.getFechaUppercase(planMedioMesIf.getFechaFin());
				
				if(planMedioMesIf.getTipo().equals(TIPO_EXPECTATIVA))
					tipo = NOMBRE_TIPO_EXPECTATIVA;
				else if(planMedioMesIf.getTipo().equals(TIPO_MANTENIMIENTO))
					tipo = NOMBRE_TIPO_MANTENIMIENTO;
				else if(planMedioMesIf.getTipo().equals(TIPO_LANZAMIENTO))
					tipo = NOMBRE_TIPO_LANZAMIENTO;
				else if(planMedioMesIf.getTipo().equals(TIPO_PROMOCIONAL))
					tipo = NOMBRE_TIPO_PROMOCIONAL;
				
				fila.add(tipo);
				fila.add(subPeriodoFechaInicio);
				fila.add(subPeriodoFechaFin);
				tableSubPeriodo.addRow(fila);
				
				getCmbTipoPeriodo().addItem(tipo + " (" + subPeriodoFechaInicio + " al " + subPeriodoFechaFin + ")");
			}
			
						
			
						
			showUpdateMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private String getCodigoPlanMedio(java.sql.Date fechaHoy) {
		String codigo = "";
		String anioPlanMedio = Utilitarios.getYearFromDate(fechaHoy);
		codigo += anioPlanMedio + "-";
		return codigo;
	}
	
	private PlanMedioIf registrarPlanMedio(){
		PlanMedioData data = new PlanMedioData();

		try {
			String codigo = getCodigoPlanMedio(new java.sql.Date(new GregorianCalendar().getTimeInMillis()));
			data.setCodigo(codigo);
			data.setConcepto(getTxtConcepto().getText());
			
			tipoOrden = (TipoOrdenIf)SessionServiceLocator.getTipoOrdenSessionService().findTipoOrdenByTipo("M").iterator().next();
			ordenTrabajoIf = (OrdenTrabajoIf) getCmbOrdenTrabajo().getSelectedItem();
			ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajoAndEstado(tipoOrden.getId(), ordenTrabajoIf.getId()).iterator().next();
			data.setOrdenTrabajoDetalleId(ordenTrabajoDetalleIf.getId());
			
			GrupoObjetivoIf grupoObjetivo = (GrupoObjetivoIf) getCmbTarget().getSelectedItem();
			data.setGrupoObjetivoId(grupoObjetivo.getId());
			
			data.setUsuarioId(((UsuarioIf)Parametros.getUsuarioIf()).getId());

			try {
				data.setFechaCreacion(Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(Utilitarios.dateHoy().getTime())));
			} catch (ParseException e) {
				SpiritAlert.createAlert("Se ha producido un error al setear Fecha de Creación", SpiritAlert.ERROR);
				e.printStackTrace();
			}

			data.setFechaInicio(Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(getCmbPeriodoFechaInicio().getDate().getTime())));
			data.setFechaFin(Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(getCmbPeriodoFechaFin().getDate().getTime())));
			data.setCiudad1(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtGuayaquil().getText()))));
			data.setCiudad2(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtQuito().getText()))));
			
			if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_APROBADO))
				data.setEstado(ESTADO_APROBADO);
			else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_EN_PROCESO))
				data.setEstado(ESTADO_EN_PROCESO);
			else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PENDIENTE))
				data.setEstado(ESTADO_PENDIENTE);
			
			data.setCobertura(getTxtCobertura().getText());
			
			if(!getTxtOtrasConsideraciones().getText().equals("")){
				data.setConsideraciones(getTxtOtrasConsideraciones().getText());
			}
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error al registrar Plan de Medio", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
		
		return data;
	}
	
	private void registrarPlanMedioForUpdate(){
		try {
			planMedioIf.setCodigo(getTxtCodigo().getText());
			planMedioIf.setConcepto(getTxtConcepto().getText());
			
			tipoOrden = (TipoOrdenIf)SessionServiceLocator.getTipoOrdenSessionService().findTipoOrdenByTipo("M").iterator().next();
			ordenTrabajoIf = (OrdenTrabajoIf) getCmbOrdenTrabajo().getSelectedItem();
			ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajoAndEstado(tipoOrden.getId(), ordenTrabajoIf.getId()).iterator().next();
			planMedioIf.setOrdenTrabajoDetalleId(ordenTrabajoDetalleIf.getId());
			
			GrupoObjetivoIf grupoObjetivo = (GrupoObjetivoIf) getCmbTarget().getSelectedItem();
			planMedioIf.setGrupoObjetivoId(grupoObjetivo.getId());
			
			planMedioIf.setUsuarioId(((UsuarioIf)Parametros.getUsuarioIf()).getId());

			try {
				planMedioIf.setFechaCreacion(Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(Utilitarios.dateHoy().getTime())));
			} catch (ParseException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error al setear Fecha de Creación", SpiritAlert.ERROR);
			}

			planMedioIf.setFechaInicio(Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(getCmbPeriodoFechaInicio().getDate().getTime())));
			planMedioIf.setFechaFin(Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(getCmbPeriodoFechaFin().getDate().getTime())));
			planMedioIf.setCiudad1(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtGuayaquil().getText()))));
			planMedioIf.setCiudad2(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtQuito().getText()))));
			
			if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_APROBADO))
				planMedioIf.setEstado(ESTADO_APROBADO);
			else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_EN_PROCESO))
				planMedioIf.setEstado(ESTADO_EN_PROCESO);
			else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PENDIENTE))
				planMedioIf.setEstado(ESTADO_PENDIENTE);
			
			planMedioIf.setCobertura(getTxtCobertura().getText());
			
			if(!getTxtOtrasConsideraciones().getText().equals("")){
				planMedioIf.setConsideraciones(getTxtOtrasConsideraciones().getText());
			}
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error al registrar Plan de Medio para actualizar", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	public void save() {
		/*try {
			if (validateFields()) {
				PlanMedioIf planMedio = registrarPlanMedio();
				
				SessionServiceLocator.getPlanMedioSessionService().procesarPlanMedio(planMedio, planMedioMesVector);
							
				SpiritAlert.createAlert("Plan de Medios guardado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
			e.printStackTrace();
		}*/
	}

	public void update() {
		/*try {
			if (validateFields()) {
				registrarPlanMedioForUpdate();
				
				SessionServiceLocator.getPlanMedioSessionService().actualizarPlanMedio(planMedioIf, planMedioMesVector, planMedioMesRemovidoVector, null, null);
							
				SpiritAlert.createAlert("Plan de Medios actualizado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
			e.printStackTrace();
		}*/
	}

	public void delete() {
		try {
			planMedioIf.setEstado(ESTADO_ELIMINADO);
			SessionServiceLocator.getPlanMedioSessionService().eliminarPlanMedio(planMedioIf);
			SpiritAlert.createAlert("Plan de Medios eliminado con éxito", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (Exception e) {
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void clean() {
		prensaTarifaColeccion.clear();
		corporacionIf = null;
		clienteIf = null;
		clienteOficinaIf = null;
		planMedioMesVector.clear();
		planMedioMesRemovidoVector.clear();
		pautasTv.clear();
		comercialVectorTabla.clear();
		comercialesSeleccionadosTabla.clear();
		canales.clear();
		programas.clear();
		mapaPautaComerciales.clear();
		pautasTvExtendida.clear();
		comercialesSeleccionadosTablaExtendida.clear();
		modelArbolTvColecciones.clear();
		treePathsTvColecciones.clear();
		tableScrollPaneTvColecciones.clear();
		tableScrollTvRemovido = null;	
		treePathsPrensaColecciones.clear();
		tableScrollPanePrensaColecciones.clear();
		tableScrollPrensaRemovido = null;
		pautasTvColecciones.clear();
		
		getTxtCodigo().setText("");
		getTxtCorporacion().setText("");
		getTxtCliente().setText("");
		getTxtOficina().setText("");
		getTxtConcepto().setText("");
		getCmbOrdenTrabajo().setSelectedIndex(-1);
		getCmbOrdenTrabajo().setEnabled(false);
		getTxtOrdenTrabajoDetalle().setText("");
		
		getCmbTarget().setSelectedIndex(-1);
		getCmbTarget().repaint();
		getTxtGuayaquil().setText("");
		getTxtQuito().setText("");
		getTxtTotalCiudad().setText("");
		getBtnGuayaquil().setEnabled(false);
		getBtnQuito().setEnabled(false);
		getBtnTotalCiudad().setEnabled(false);
		Calendar calendar = new GregorianCalendar();
		getCmbPeriodoFechaInicio().setCalendar(calendar);
		getCmbPeriodoFechaFin().setCalendar(calendar);
		getCmbSubPeriodoFechaInicio().setCalendar(calendar);
		getCmbSubPeriodoFechaFin().setCalendar(calendar);
		getCmbSubPeriodoFechaInicio().getDateModel().setMinDate(calendar);
		getCmbSubPeriodoFechaFin().getDateModel().setMinDate(calendar);
		getCmbSubPeriodoFechaInicio().getDateModel().setMaxDate(calendar);
		getCmbSubPeriodoFechaFin().getDateModel().setMaxDate(calendar);
	
		getTxtCobertura().setText("");
		getTxtOtrasConsideraciones().setText("");
		getTxtValorTarifa().setText("");
		getTxtValorDescuento().setText("");
		getTxtModificaciones().setText("");
		
		DefaultTableModel modelSubPeriodo = (DefaultTableModel) getTblSubPeriodo().getModel();
		for(int i = this.getTblSubPeriodo().getRowCount(); i > 0; --i)
			modelSubPeriodo.removeRow(i - 1);
		
		getCmbTipoPeriodo().removeAllItems();
		cleanTablaComercial();
	}

	private void cleanTablaComercial() {
		DefaultTableModel modelComercial = (DefaultTableModel) getTblComercial().getModel();
		for(int i= this.getTblComercial().getRowCount();i>0;--i)
			modelComercial.removeRow(i-1);
	}
	
	private void cleanPrensa(){
		getTxtDiario().setText("");
		getTxtSeccion().setText("");
		getTxtUbicacion().setText("");
		getTxtFormato().setText("");
		getTxtAnchoColumnas().setText("");
		getTxtAltoModulos().setText("");
		getTxtAnchoCm().setText("");
		getTxtAltoCm().setText("");
		getTxtColor().setText("");
		getTxtDia().setText("");
		getTxtTarifa().setText("");
	}
	
	private void cleanTv(){
		getTxtCanalTv().setText("");
		getTxtProgramaTv().setText("");
		getTxtHoraInicioPrograma().setText("");
		getTxtHoraFinPrograma().setText("");
		getTxtDiasPrograma().setText("");
		getTxtRatingTv().setText("");
		getTxtVCunaTarifa().setText("");
		getTxtVCunaNegocio().setText("");
		getTxtComercialTv().setText("");
		getTxtDerechoPrograma().setText("");
		getTxtVersionPrograma().setText("");
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
	}

	public boolean validateFields() {
		if((getCmbEstado().getSelectedIndex() == -1) || (getCmbEstado().getSelectedItem().equals(""))){
			SpiritAlert.createAlert("Debe seleccionar un Estado !", SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getCmbEstado().grabFocus();
			return false;
		}
		if((("".equals(getTxtCorporacion().getText())) || (getTxtCorporacion().getText() == null))){
			SpiritAlert.createAlert("Debe seleccionar una Corporación !", SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getBtnCorporacion().grabFocus();
			return false;
		}
		if((("".equals(getTxtCliente().getText())) || (getTxtCliente().getText() == null))){
			SpiritAlert.createAlert("Debe seleccionar un Cliente !", SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getBtnCliente().grabFocus();
			return false;
		}
		if((("".equals(getTxtOficina().getText())) || (getTxtOficina().getText() == null))){
			SpiritAlert.createAlert("Debe seleccionar una Oficina !", SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getBtnOficina().grabFocus();
			return false;
		}
		if(getCmbOrdenTrabajo().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar una Orden de Trabajo !", SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getCmbOrdenTrabajo().grabFocus();
			return false;
		}
		if((("".equals(getTxtConcepto().getText())) || (getTxtConcepto().getText() == null))){
			SpiritAlert.createAlert("Debe ingresar un Concepto !", SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getTxtConcepto().grabFocus();
			return false;
		}
		if(getCmbTarget().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Target !", SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getCmbTarget().grabFocus();
			return false;
		}
		if((("".equals(getTxtGuayaquil().getText())) || (getTxtGuayaquil().getText() == null))){
			SpiritAlert.createAlert("Debe ingresar un estimado para Guayaquil !", SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getTxtGuayaquil().grabFocus();
			return false;
		}
		if((("".equals(getTxtQuito().getText())) || (getTxtQuito().getText() == null))){
			SpiritAlert.createAlert("Debe ingresar un estimado para Quito !", SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getTxtQuito().grabFocus();
			return false;
		}
		if(getCmbPeriodoFechaInicio().getSelectedItem() == null){
			SpiritAlert.createAlert("Debe seleccionar una Fecha de Inicio para el Periodo !", SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getCmbPeriodoFechaInicio().grabFocus();
			return false;
		}
		if(getCmbPeriodoFechaFin().getSelectedItem() == null){
			SpiritAlert.createAlert("Debe seleccionar una Fecha de Fin para el Periodo !", SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getCmbPeriodoFechaFin().grabFocus();
			return false;
		}
		if((("".equals(getTxtCobertura().getText())) || (getTxtCobertura().getText() == null))){
			SpiritAlert.createAlert("Debe ingresar la Cobertura !", SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getTxtCobertura().grabFocus();
			return false;
		}
		if(planMedioMesVector.isEmpty()){
			SpiritAlert.createAlert("Debe ingresar por lo menos una Planificación !", SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(1);
			getCmbTipo().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public boolean validateFieldsNodoTv() {
		if((("".equals(getTxtCanalTv().getText())) || (getTxtCanalTv().getText() == null))){
			SpiritAlert.createAlert("Debe ingresar el nombre del Canal !", SpiritAlert.INFORMATION);
			getTxtCanalTv().grabFocus();
			return false;
		}
		if((("".equals(getTxtProgramaTv().getText())) || (getTxtProgramaTv().getText() == null))){
			SpiritAlert.createAlert("Debe ingresar el nombre del Programa !", SpiritAlert.INFORMATION);
			getTxtProgramaTv().grabFocus();
			return false;
		}
		if((("".equals(getTxtHoraInicioPrograma().getText())) || (getTxtHoraInicioPrograma().getText() == null))){
			SpiritAlert.createAlert("Debe ingresar la Hora de Inicio del programa !", SpiritAlert.INFORMATION);
			getTxtHoraInicioPrograma().grabFocus();
			return false;
		}
		if((("".equals(getTxtHoraFinPrograma().getText())) || (getTxtHoraFinPrograma().getText() == null))){
			SpiritAlert.createAlert("Debe ingresar la Hora de Fin del programa !", SpiritAlert.INFORMATION);
			getTxtHoraFinPrograma().grabFocus();
			return false;
		}
		if((("".equals(getTxtDiasPrograma().getText())) || (getTxtDiasPrograma().getText() == null))){
			SpiritAlert.createAlert("Debe ingresar los Días del programa !", SpiritAlert.INFORMATION);
			getTxtDiasPrograma().grabFocus();
			return false;
		}
		if((("".equals(getTxtRatingTv().getText())) || (getTxtRatingTv().getText() == null))){
			SpiritAlert.createAlert("Debe ingresar el Rating del programa !", SpiritAlert.INFORMATION);
			getTxtRatingTv().grabFocus();
			return false;
		}
				
		return true;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarComboEstado();
		getTxtCodigo().setBackground(getBackground());
		getTxtConcepto().setBackground(Parametros.saveUpdateColor);
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		getCmbPeriodoFechaInicio().setBackground(Parametros.saveUpdateColor);
		getCmbPeriodoFechaFin().setBackground(Parametros.saveUpdateColor);
		getBtnAgregarProgramaTv().setEnabled(false);
		
		getJtpPlanMedio().setSelectedIndex(0);
		getTxtCodigo().grabFocus();
	}
	
	public void cargarTablaComercial(){
		try {	
			/*if(!pautasTv.isEmpty()){
				int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro desea cambiar de Orden? Se perderá información de las tablas.!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					
				}
			}*/
			if(clienteIf != null && ordenTrabajoIf != null){
				Map aMap = new HashMap();
				aMap.put("empresaId", Parametros.getIdEmpresa());
				aMap.put("estado", ESTADO_ACTIVO);
				aMap.put("campanaId", ordenTrabajoIf.getCampanaId());
				//Collection comerciales = getComercialSessionService().findComercialByEmpresaIdByEstadoActivoAndByClienteId(Parametros.getIdEmpresa(), clienteIf.getId());
				Collection comerciales = SessionServiceLocator.getComercialSessionService().findComercialByQuery(aMap);				
				Iterator comercialesIterator = comerciales.iterator();
				
				if(!getComercialVectorTabla().isEmpty()){
					getComercialVectorTabla().removeAllElements();
				}
				
				cleanTablaComercial();
				
				while (comercialesIterator.hasNext()) {
					ComercialIf comercialesIf = (ComercialIf) comercialesIterator.next();
					
					tableComercial = (DefaultTableModel) getTblComercial().getModel();
					Vector<Object> fila = new Vector<Object>();
					getComercialVectorTabla().add(comercialesIf);
					
					agregarColumnasTablaComercial(comercialesIf, fila);
								
					tableComercial.addRow(fila);
				}
			}			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void agregarColumnasTablaComercial(ComercialIf comercialesIf, Vector<Object> fila){
		try {
			fila.add(new Boolean(false));
			fila.add(comercialesIf.getCodigo());
			fila.add(comercialesIf.getNombre());
			
			DerechoProgramaIf derechoPrograma = SessionServiceLocator.getDerechoProgramaSessionService().getDerechoPrograma(comercialesIf.getDerechoprogramaId());
			fila.add(derechoPrograma.getNombre());
			
			fila.add(comercialesIf.getVersion());
			fila.add(comercialesIf.getDuracion() + " seg.");
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void showFindMode() {
		clean();
		getCmbEstado().addItem(NOMBRE_ESTADO_ELIMINADO);
		getCmbEstado().setSelectedItem(null);
		getCmbPeriodoFechaInicio().setSelectedItem(null);
		getCmbPeriodoFechaFin().setSelectedItem(null);
		getCmbEstado().setSelectedIndex(-1);
		getJtpPlanMedio().setSelectedIndex(0);
		getTxtCodigo().grabFocus();
		
		getTxtCodigo().setBackground(Parametros.findColor);
		getTxtConcepto().setBackground(Parametros.findColor);
		getCmbEstado().setBackground(Parametros.findColor);
		getCmbPeriodoFechaInicio().setBackground(Parametros.findColor);
		getCmbPeriodoFechaFin().setBackground(Parametros.findColor);
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setBackground(getBackground());
		getTxtConcepto().setBackground(Parametros.saveUpdateColor);
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		getCmbPeriodoFechaInicio().setBackground(Parametros.saveUpdateColor);
		getCmbPeriodoFechaFin().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().grabFocus();
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public void refresh() {
		/*int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
		if (opcion == JOptionPane.YES_OPTION) {
			
		}*/
		getCmbTarget().removeActionListener(oActionListenerCmbTarget);
		cargarComboGrupoObjetivo();
		getCmbTarget().addActionListener(oActionListenerCmbTarget);
		cargarTreePrensa();
		cleanPrensa();
		cleanMapaPrensa();
		cargarTablaComercial();
		cleanArbolMapaTv();
		cleanTv();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}

	public void cleanArbolMapaTv() {
		DefaultMutableTreeNode rootArbolTv = new DefaultMutableTreeNode("ARBOL DE TELEVISION");	
		//rootArbolTv.removeAllChildren();
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(rootArbolTv);
		getArbolTelevision().setModel(defaultTreeModel);
		getArbolTelevision().getCheckBoxTreeSelectionModel().clearSelection();
		
		cabecerasFijasTv = new String[]{};
		cabecerasVariablesTv = new String[]{}; 
		datosTv = new Object[][]{};
		numeroFilasTv = 20;
		getPanelMapaPautaTv().remove(scrollPanelMapaTv.getTable());
		scrollPanelMapaTv = new TableScrollPaneMapaPauta(cabecerasFijasTv, cabecerasVariablesTv, datosTv, numeroFilasTv);
		getPanelMapaPautaTv().add(scrollPanelMapaTv.getTable(), cc.xywh(3, 3, 5, 5));
		tableScrollTvRemovido = scrollPanelMapaTv.getTable();
	}
	
	public void cleanMapaPrensa() {
		cabecerasFijasPrensa = new String[]{};
		cabecerasVariablesPrensa = new String[]{}; 
		datosPrensa = new Object[][]{};
		numeroFilasPrensa = 20;
		getPanelMapaPautaPrensa().remove(scrollPanelMapaPrensa.getTable());
		scrollPanelMapaPrensa = new TableScrollPaneMapaPauta(cabecerasFijasPrensa, cabecerasVariablesPrensa, datosPrensa, numeroFilasPrensa);
		getPanelMapaPautaPrensa().add(scrollPanelMapaPrensa.getTable(), cc.xywh(3, 3, 5, 5));
		tableScrollPrensaRemovido = scrollPanelMapaPrensa.getTable();
	}
	 

	public PlanMedioMesIf getPlanMedioMesActualizadoIf() {
		return planMedioMesActualizadoIf;
	}

	public void setPlanMedioMesActualizadoIf(
			PlanMedioMesIf planMedioMesActualizadoIf) {
		this.planMedioMesActualizadoIf = planMedioMesActualizadoIf;
	}

	public int getPlanMedioMesSeleccionado() {
		return planMedioMesSeleccionado;
	}

	public void setPlanMedioMesSeleccionado(int planMedioMesSeleccionado) {
		this.planMedioMesSeleccionado = planMedioMesSeleccionado;
	}

	public Vector<PlanMedioMesIf> getPlanMedioMesVector() {
		return planMedioMesVector;
	}

	public void setPlanMedioMesVector(Vector<PlanMedioMesIf> planMedioMesVector) {
		this.planMedioMesVector = planMedioMesVector;
	}
	
	public Vector<ComercialIf> getComercialVectorTabla() {
		return comercialVectorTabla;
	}

	public void setComercialVectorTabla(Vector<ComercialIf> comercialVector) {
		this.comercialVectorTabla = comercialVector;
	}
}