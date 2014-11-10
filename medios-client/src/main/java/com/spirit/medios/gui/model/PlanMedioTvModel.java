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
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
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

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.poi.poifs.filesystem.OfficeXmlFileException;

import com.jgoodies.forms.layout.CellConstraints;
import com.jidesoft.grid.TableScrollPane;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.ObservacionesModel;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.ClienteRetencionIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.medios.entity.CampanaData;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.CampanaProductoVersionIf;
import com.spirit.medios.entity.ComercialIf;
import com.spirit.medios.entity.DerechoProgramaIf;
import com.spirit.medios.entity.GrupoObjetivoIf;
import com.spirit.medios.entity.MapaComercialData;
import com.spirit.medios.entity.MapaComercialIf;
import com.spirit.medios.entity.OrdenMedioData;
import com.spirit.medios.entity.OrdenMedioDetalleData;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioDetalleMapaData;
import com.spirit.medios.entity.OrdenMedioDetalleMapaIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PlanMedioData;
import com.spirit.medios.entity.PlanMedioDetalleData;
import com.spirit.medios.entity.PlanMedioDetalleIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PlanMedioMesData;
import com.spirit.medios.entity.PlanMedioMesIf;
import com.spirit.medios.entity.PrensaFormatoIf;
import com.spirit.medios.entity.PrensaSeccionIf;
import com.spirit.medios.entity.PrensaTarifaIf;
import com.spirit.medios.entity.PrensaUbicacionIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.medios.gui.criteria.PlanMedioCriteria;
import com.spirit.medios.gui.criteria.PlanMedioOriginalCriteria;
import com.spirit.medios.gui.importer.InfoComercialMultiple;
import com.spirit.medios.gui.importer.ManejarExcel;
import com.spirit.medios.gui.importer.Pauta;
import com.spirit.medios.gui.importer.PautaImporter;
import com.spirit.medios.gui.panel.JPPlanMedioTv;
import com.spirit.medios.gui.reporteData.OrdenMedioReporteData;
import com.spirit.medios.gui.reporteData.PlanMedioReporteData;
import com.spirit.medios.gui.reporteData.ResumenTrpsCanalData;
import com.spirit.medios.gui.reporteData.ResumenTrpsFranjaData;
import com.spirit.medios.gui.reporteData.ResumenTrpsSemanaData;
import com.spirit.medios.util.InfoOrdenTrabajoDetalle;
import com.spirit.medios.util.TableScrollPaneMapaPauta;
import com.spirit.nomina.handler.DatoObservacion;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class PlanMedioTvModel extends JPPlanMedioTv {

	private static final String NOMBRE_ESTADO_EN_PROCESO = "EN PROCESO";
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_APROBADO = "APROBADO";
	private static final String NOMBRE_ESTADO_FACTURADO = "FACTURADO";
	private static final String NOMBRE_ESTADO_HISTORICO = "HISTORICO";
	private static final String NOMBRE_ESTADO_PEDIDO = "PEDIDO";
	private static final String NOMBRE_ESTADO_PREPAGADO = "PREPAGADO";
	private static final String ESTADO_EN_PROCESO = "N";
	private static final String ESTADO_PENDIENTE = "P";
	private static final String ESTADO_APROBADO = "A";
	private static final String ESTADO_FACTURADO = "F";
	private static final String ESTADO_HISTORICO = "H";
	private static final String ESTADO_PEDIDO = "D";
	private static final String ESTADO_PREPAGADO = "R";
	private static final String NOMBRE_PLAN_MEDIO_TIPO_NORMAL = "NORMAL";
	private static final String NOMBRE_PLAN_MEDIO_TIPO_NUEVA_VERSION = "VERSION";
	private static final String NOMBRE_PLAN_MEDIO_TIPO_NUEVO_MES = "MES";

	private static final String PLAN_MEDIO_TIPO_NORMAL = "O";
	private static final String PLAN_MEDIO_TIPO_NUEVA_VERSION = "V";
	private static final String PLAN_MEDIO_TIPO_NUEVO_MES = "M";

	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = "A";
	private static final String ESTADO_INACTIVO = "I";

	private static final String NOMBRE_ESTADO_ORDEN_MEDIO_ENVIADA = "ENVIADA";
	private static final String NOMBRE_ESTADO_ORDEN_MEDIO_INGRESADA = "INGRESADA";
	private static final String NOMBRE_ESTADO_ORDEN_MEDIO_ANULADA = "ANULADA";
	private static final String NOMBRE_ESTADO_ORDEN_MEDIO_EMITIDA = "EMITIDA";
	private static final String NOMBRE_ESTADO_ORDEN_MEDIO_ORDENADA = "ORDENADA";
	private static final String NOMBRE_ESTADO_ORDEN_MEDIO_PREPAGADA = "PREPAGADA";
	private static final String ESTADO_ORDEN_ENVIADA = "E";
	private static final String ESTADO_ORDEN_INGRESADA = "I";
	private static final String ESTADO_ORDEN_ANULADA = "A";
	private static final String ESTADO_ORDEN_EMITIDA = "M";
	private static final String ESTADO_ORDEN_ORDENADA = "D";
	private static final String ESTADO_ORDEN_PREPAGADA = "R";
	
	private static final String NOMBRE_ORDEN_MEDIO_TIPO_CANAL = "CANAL";
	private static final String NOMBRE_ORDEN_MEDIO_TIPO_PRODUCTO_COMERCIAL = "PRODUCTO COMERCIAL";
	private static final String NOMBRE_ORDEN_MEDIO_TIPO_VERSION = "VERSION";

	private static final String ORDEN_MEDIO_TIPO_CANAL = "C";
	private static final String ORDEN_MEDIO_TIPO_PRODUCTO_COMERCIAL = "P";
	private static final String ORDEN_MEDIO_TIPO_VERSION = "V";

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

	private static final String NOMBRE_CUNIA = "C";
	private static final String NOMBRE_MENCION = "M";
	private static final String NOMBRE_PATROCINIO = "P";
	private static final String NOMBRE_DESPEDIDA = "D";
	private static final String NOMBRE_SOBREIMPOSICION = "S";
	private static final String NOMBRE_SEGMENTO = "E";

	private static final String ESTADO_ORDEN_EN_CURSO = "E";

	private Icon customOpenIconTv = SpiritResourceManager
			.getImageIcon("images/icons/funcion/pantallaNegra.png");
	private Icon customClosedIconTv = SpiritResourceManager
			.getImageIcon("images/icons/funcion/pantallaBlanca.png");
	private Icon customLeftIconTv = SpiritResourceManager
			.getImageIcon("images/icons/funcion/leftNodeNegro.png");
	private Icon customOpenIconPrensa = SpiritResourceManager
			.getImageIcon("images/icons/funcion/periodicoAmarillo.png");
	private Icon customClosedIconPrensa = SpiritResourceManager
			.getImageIcon("images/icons/funcion/periodicoAzul.png");
	private Icon customLeftIconPrensa = SpiritResourceManager
			.getImageIcon("images/icons/funcion/leftNodeNaranja.png");

	private static final int MAX_LONGITUD_CODIGO = 10;
	private static final int MAX_LONGITUD_CONCEPTO = 100;
	private static final int MAX_LONGITUD_VALOR = 10;
	private static final int MAX_LONGITUD_CANALTV = 100;
	private static final int MAX_LONGITUD_PROGRAMATV = 100;
	private static final int MAX_LONGITUD_HORAPROGRAMA = 5;
	private static final int MAX_LONGITUD_DIASPROGRAMA = 13;
	private static final int MAX_LONGITUD_RATINGPROGRAMA = 6;
	private static final int MAX_LONGITUD_DESCUENTO = 5;
	private static final int MAX_LONGITUD_AUTORIZACION_SAP = 15;

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
	private DefaultTableModel tableTGRP;
	private TipoOrdenIf tipoOrden;

	private OrdenTrabajoIf ordenTrabajoIf;
	private OrdenTrabajoDetalleIf ordenTrabajoDetalleIf;

	private PlanMedioIf planMedioIf;
	private PlanMedioIf planMedioIfSaved;
	private PlanMedioIf planMedioOriginalIf;

	private String si = "Si";
	private String no = "No";
	private Object[] options = { si, no };
	private Vector<PrensaTarifaIf> prensaTarifaColeccion = new Vector<PrensaTarifaIf>();
	private CellConstraints cc = new CellConstraints();
	private TableScrollPaneMapaPauta scrollPanelMapaPrensa;
	// scroll TABLA MAPAS DE PAUTA -> TELEVISION
	private TableScrollPaneMapaPauta scrollPanelMapaTv;

	private TableScrollPaneMapaPauta scrollPanelTvTGRP;
	private String[] cabecerasFijasPrensa = new String[] {};
	private String[] cabecerasVariablesPrensa = new String[] {};
	private Object[][] datosPrensa = new Object[][] {};
	private int numeroFilasPrensa = 20;
	private String[] cabecerasFijasTv = new String[] {};
	private String[] cabecerasVariablesTv = new String[] {};

	// datosTv contiene cada linea del excel, hora inicio, descripcion o
	// cliente, programa, comercial, frecuencia,
	private Object[][] datosTv = new Object[][] {};

	private Object[][] datosTGRP = new Object[][] {};
	private int numeroFilasTv = 20;
	public static Locale esLocale = new Locale("es");

	private Vector<Pauta> pautasTv = new Vector<Pauta>();
	private Vector<Vector<Pauta>> pautasTvColecciones = new Vector<Vector<Pauta>>();
	private Vector<Pauta> pautasTvExtendida = new Vector<Pauta>();

	// 15 MAYO GIOMY REVISAR !!!!!!!!!!!!!
	private Vector<ComercialIf> comercialVectorTabla = new Vector<ComercialIf>();
	private Vector<ComercialIf> comercialesSeleccionadosTabla = new Vector<ComercialIf>();
	private Vector<ComercialIf> comercialesSeleccionadosTablaExtendida = new Vector<ComercialIf>();

	private Vector<String> canales = new Vector<String>();
	private Vector<String> programas = new Vector<String>();

	// 15 MAYO GIOMY REVISAR !!!!!!!!!!!!!
	private Map<Integer, ComercialIf> mapaPautaComerciales = new LinkedHashMap<Integer, ComercialIf>();

	private Vector<DefaultTreeModel> modelArbolTvColecciones = new Vector<DefaultTreeModel>();
	private Vector<TreePath[]> treePathsTvColecciones = new Vector<TreePath[]>();
	private Vector<TableScrollPane> tableScrollPaneTvColecciones = new Vector<TableScrollPane>();
	private TableScrollPane tableScrollTvRemovido = null;
	private Vector<TreePath[]> treePathsPrensaColecciones = new Vector<TreePath[]>();
	private Vector<TableScrollPane> tableScrollPanePrensaColecciones = new Vector<TableScrollPane>();
	private TableScrollPane tableScrollPrensaRemovido = null;
	
	//CADA LINEA DEL CUERPO DEL EXCEL
	private Collection<PlanMedioDetalleIf> planMedioDetallesPlantilla = new ArrayList<PlanMedioDetalleIf>(); 
	private Vector<OrdenMedioIf> ordenMedioCollection = new Vector<OrdenMedioIf>();
	private LinkedHashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>> mapasComercialesPlantilla = new LinkedHashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>>();
	private Map<PlanMedioDetalleIf, Collection<InfoComercialMultiple>> mapasComercialesPlantillaMultiple = new LinkedHashMap<PlanMedioDetalleIf, Collection<InfoComercialMultiple>>();

	// put(proveedor.getId(),proveedor)
	private Map<String, ClienteOficinaIf> listaProveedoresMap = new LinkedHashMap<String, ClienteOficinaIf>();

	// MAPA mapaComercialOrdenes PARA (comercialId, MAPA ordenesMedioMap);
	// SOLO SE LA UTILIZA EN UN METODO QUE YA NO SE LO LLAMA crearOrdenesMedio
	private Map<Long, Map<OrdenMedioIf, List<OrdenMedioDetalleIf>>> mapaComercialOrdenes = new LinkedHashMap<Long, Map<OrdenMedioIf, List<OrdenMedioDetalleIf>>>();

	private DecimalFormat formatoEntero = new DecimalFormat("###0");
	private BigDecimal ivaTotal = new BigDecimal(0);
	private BigDecimal descuentoTotal = new BigDecimal(0);
	private BigDecimal bonificacionCompraTotal = new BigDecimal(0);
	private BigDecimal porcentajeDescuentoTotal = new BigDecimal(0);
	private BigDecimal porcentajeBonificacionCompraTotal = new BigDecimal(0);
	private BigDecimal porcentajeDescuentoCompraDefault = new BigDecimal(0);
	private BigDecimal porcentajeDescuentoCompraDefaultSaved = new BigDecimal(0);
	private BigDecimal porcentajeBonificacionCompraDefault = new BigDecimal(0);
	private BigDecimal porcentajeBonificacionCompraDefaultSaved = new BigDecimal(0);
	private BigDecimal totalSumaTotal = new BigDecimal(0);
	private BigDecimal totalSumaTotalConIVA = new BigDecimal(0);
	private BigDecimal totalSumaTotalSinIVA = new BigDecimal(0);
	private BigDecimal totalValorTotal = new BigDecimal(0);
	private BigDecimal totalSubTotal = new BigDecimal(0);
	private BigDecimal totalSubTotalBonificacion = new BigDecimal(0);
	BigDecimal descuentoTotalVenta = new BigDecimal(0);
	BigDecimal porcentajeDescuentoTotalVenta = new BigDecimal(0);
	BigDecimal porcentajeComisionAgencia = new BigDecimal(0);
	BigDecimal porcentajeBonificacionVenta = new BigDecimal(0);
	BigDecimal totalSubTotalVenta1 = new BigDecimal(0);
	BigDecimal totalSubTotalVenta2 = new BigDecimal(0);
	BigDecimal comisionAgenciaTotal = new BigDecimal(0);
	BigDecimal bonificacionVentaTotal = new BigDecimal(0);
	BigDecimal ivaTotalVenta = new BigDecimal(0);
	BigDecimal totalValorTotalVenta = new BigDecimal(0);
	private GenericoIf genericoPautaRegular = null;
	private boolean nuevoPlan = false;
	// ADD 12 MAYO

	private Map<OrdenMedioIf, List<OrdenMedioDetalleIf>> ordenesMedioMap = new LinkedHashMap<OrdenMedioIf, List<OrdenMedioDetalleIf>>();
	private Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMediosMapComp = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();

	// mapaComercialOrdenesComp.put(comercialId,ordenesMedioParcial);
	// SOLO SE LA UTILIZA EN UN METODO QUE YA NO SE LO LLAMA crearOrdenesMedio
	private Map<Long, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaComercialOrdenesComp = new LinkedHashMap<Long, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>>();
	// mapOrdenMediobyProveedor.put(proveedorId, listOrdenMedio);
	private Map<String, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapOrdenMediobyProveedor = new LinkedHashMap<String, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>>();

	private Map<String, ArrayList<String>> mapOrdenMediobyProveedorPDesc = new LinkedHashMap<String, ArrayList<String>>();
	private Map<String, ArrayList<String>> mapOrdenMediobyProveedorPBono = new LinkedHashMap<String, ArrayList<String>>();
	private Map<String, ArrayList<BigDecimal>> mapOrdenMediobyProveedorPCanje = new LinkedHashMap<String, ArrayList<BigDecimal>>();

	private static final String TIPO_PLANTILLA_SENCILLA = "SENCILLA";
	private static final String TIPO_PLANTILLA_MULTIPLE = "MULTIPLE";
	private String tipoPlantilla = TIPO_PLANTILLA_SENCILLA;

	private OrdenMedioIf ordenMedioTblDetalle;
	private BigDecimal porcentajeDescuentoTotalAnt = new BigDecimal(0);
	private int filaSeleccionada = -1;
	// ADD 15 JUNIO
	private int filaSeleccionadaSaved = -1;
	// END 15 JUNIO

	private Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMediosMapCompSaved = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
	private Map<OrdenMedioIf, List<OrdenMedioDetalleIf>> ordenesMedioMapSaved = new LinkedHashMap<OrdenMedioIf, List<OrdenMedioDetalleIf>>();

	// put(proveedorId, listOrdenMedio)
	private Map<String, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapOrdenMediobyProveedorSaved = new LinkedHashMap<String, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>>();
	// put(proveedorId,listPCanje)
	private Map<String, ArrayList<BigDecimal>> mapOrdenMediobyProveedorPCanjeSaved = new LinkedHashMap<String, ArrayList<BigDecimal>>();
	// put(proveedorId, listPDescuento)
	private Map<String, ArrayList<String>> mapOrdenMediobyProveedorPDescSaved = new LinkedHashMap<String, ArrayList<String>>();

	private LinkedHashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>> mapasComercialesPlantillaSaved = new LinkedHashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>>();
	// put(proveedor.getId(),proveedor)
	private Map<String, ClienteOficinaIf> listaProveedoresMapSaved = new LinkedHashMap<String, ClienteOficinaIf>();

	private ClienteOficinaIf clienteOficinaIfSaved;
	private GenericoIf genericoPautaRegularSaved = null;
	private Map<PlanMedioDetalleIf, Collection<InfoComercialMultiple>> mapasComercialesPlantillaMultipleSaved = new LinkedHashMap<PlanMedioDetalleIf, Collection<InfoComercialMultiple>>();
	private Collection<PlanMedioDetalleIf> planMedioDetallesPlantillaSaved = new ArrayList<PlanMedioDetalleIf>();
	private Vector<OrdenMedioIf> ordenMedioCollectionSaved = new Vector<OrdenMedioIf>();

	// ADD 25 ABRIL
	// para agregar mas planes de Medio Mes aunque ahorita esta limitado que
	// solo se pueda agregar uno
	private ArrayList<PlanMedioMesIf> planMedioMesArray = new ArrayList<PlanMedioMesIf>();
	// END 25 ABRIL
	// ADD 29 ABRIL
	double porcentajeLimite = 100D;
	private static final int MAX_LONGITUD_PORCENTAJE = 6;
	// END 29 ABRIL

	// ADD 3 MAYO
	// DEBEN SER COMENTADAS utilizadas agrupacion X PRODUCTO
	private Map<String, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaProductoClienteOrdenesComp = new LinkedHashMap<String, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>>();
	// END 3 MAYO
	
	// ADD 10 OCTUBRE
	private Map<String, Map<Long, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>>> mapaProductoClienteVersionOrdenesComp = new LinkedHashMap<String, Map<Long, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>>>();
	// END 10 OCTUBRE

	// 6 MAYO
	// para colocar el SUBTOTAL de todas las ordenes de cada proveedor
	Map<String, BigDecimal> proveedorValorSubtotal = new LinkedHashMap<String, BigDecimal>();
	// 6 MAYO
	// 9 MAYO
	// para conocer el inde de la orden segun la fila seleccionada en la tabla
	// de ordenes
	ArrayList<Integer> indiceProveedorSubtotalOrdenes = new ArrayList<Integer>();

	// ADD 13 MAYO
	// VARIABLES A UTILIZAR EN LA ACTUALIZACION DEL PLAN DE MEDIO CUANDO SE
	// CARGAN LOS DATOS ALMACENADOS EN LA BD
	// put(productoClienteId, MAPA ordenesMedioMap)
	private Map<Long, Map<OrdenMedioIf, List<OrdenMedioDetalleIf>>> mapaProductoClienteOrdenesSaved = new LinkedHashMap<Long, Map<OrdenMedioIf, List<OrdenMedioDetalleIf>>>();
	// put(productoClienteId,ordenesMedioParcial);
	// ADD AGAIN 13 OCTUBRE
	private Map<String, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaProductoClienteOrdenesCompSaved = new LinkedHashMap<String, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>>();
	private Map<String, Map<Long, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>>> mapaProductoClienteVersionesOrdenesCompSaved = new LinkedHashMap<String, Map<Long, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>>>();

	// PARA CUANDO LAS ORDENES SON AGRUPADAS POR CANAL SE VA A TENER UNA SOLA
	// ORDEN X CANAL
	// codigo ClienteOficinaId con sus respectivas Ordenes y Ordenes Medio
	// Detalle
	private Map<String, Map<OrdenMedioIf, List<OrdenMedioDetalleIf>>> mapaProveedorOrdenes = new LinkedHashMap<String, Map<OrdenMedioIf, List<OrdenMedioDetalleIf>>>();
	private Map<String, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaProveedorOrdenesComp = new LinkedHashMap<String, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>>();
	// put(proveedorId, MAPA ordenesMedioMap)
	private Map<String, Map<OrdenMedioIf, List<OrdenMedioDetalleIf>>> mapaProveedorOrdenesSaved = new LinkedHashMap<String, Map<OrdenMedioIf, List<OrdenMedioDetalleIf>>>();
	// put(proveedorId,ordenesMedioParcial);
	private Map<String, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaProveedorOrdenesCompSaved = new LinkedHashMap<String, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>>();

	// private String isOrdenesXCanal = ""; //COMENTED 7 OCTUBRE
	private String ordenMedioTipo = "";

	// mapOrdenMediobyProveedorObservacion.put(proveedorId, listObservacion);
	private Map<String, ArrayList<String>> mapOrdenMediobyProveedorObservacion = new LinkedHashMap<String, ArrayList<String>>();

	// put(proveedorId, listObservacionSave)
	private Map<String, ArrayList<String>> mapOrdenMediobyProveedorbservacionSaved = new LinkedHashMap<String, ArrayList<String>>();
	// para mostrar los Observaciones temporales de las Ordenes de Medio antes
	// de guardarlas en la DB
	private ArrayList<String> listObservacionesTemp = new ArrayList<String>();
	private static final int MAX_LONGITUD_ORDEN_MEDIO_OBSERVACION = 350;

	// para colocar el SUBTOTAL de todas las ordenes de cada proveedor
	private Map<String, BigDecimal> proveedorValorSubtotalSaved = new LinkedHashMap<String, BigDecimal>();

	// mapOrdenMediobyProveedorObservacion.put(proveedorId, listPDescuento);
	private Map<String, ArrayList<String>> mapOrdenMediobyProveedorObservacionSaved = new LinkedHashMap<String, ArrayList<String>>();

	// para conocer el inde de la orden segun la fila seleccionada en la tabla
	// de ordenes
	ArrayList<Integer> indiceProveedorSubtotalOrdenesSaved = new ArrayList<Integer>();

	// para mostrar los Observaciones temporales de las Ordenes de Medio antes
	// de guardarlas en la DB
	private ArrayList<String> listObservacionesTempSaved = new ArrayList<String>();
	private CampanaIf campanaIf;

	private String planMedioTipo = PLAN_MEDIO_TIPO_NORMAL;// ""; MODIFIED 6
															// JULIO
	// PARA LAS ORDENES DE MEDIO AGRUPADAS POR PRODUCTO CLIENTE
	private Map<String, ArrayList<Map<Long, String>>> mapProductoClienteByProveedorCodigoOrdenMedio = new LinkedHashMap<String, ArrayList<Map<Long, String>>>();
	private Map<String, ArrayList<Map<Long, Map<String, String>>>> mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved = new LinkedHashMap<String, ArrayList<Map<Long, Map<String, String>>>>();
	private Map<String, ArrayList<Map<Long, Map<Long, Map<String, String>>>>> mapProductoClienteVersionByProveedorCodigoEstadoOrdenMedioSaved = new LinkedHashMap<String, ArrayList<Map<Long, Map<Long, Map<String, String>>>>>();

	// PARA LAS ORDENES DE MEDIO AGRUPADAS POR CANAL
	private Map<String, ArrayList<String>> mapCodigoOrdenMedioByProveedor = new LinkedHashMap<String, ArrayList<String>>();
	private Map<Long, ArrayList<String>> mapCodigoOrdenMedioByProveedorSaved = new LinkedHashMap<Long, ArrayList<String>>();
	private Map<String, ArrayList<Map<String, String>>> mapCodigoEstadoOrdenMedioByProveedorSaved = new LinkedHashMap<String, ArrayList<Map<String, String>>>();
	private ArrayList<Map<Long, Map<String, String>>> listIdsCodigoEstadoOrdenesMedioSaved = new ArrayList<Map<Long, Map<String, String>>>();
	private Map<String, ArrayList<String>> mapOrdenMediobyProveedorPCodigoOrden = new LinkedHashMap<String, ArrayList<String>>();
	private Map<String, ArrayList<String>> mapOrdenMediobyProveedorPCodigoOrdenSaved = new LinkedHashMap<String, ArrayList<String>>();
	private Map<Long, ArrayList<Timestamp>> mapFechaInicioFinPlanesMedioHermanosSaved = new LinkedHashMap<Long, ArrayList<Timestamp>>();
	private PlanMedioIf planMedioCrucePeriodo = null;

	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long, DerechoProgramaIf> mapaDerechoPrograma = new HashMap<Long, DerechoProgramaIf>();
	private Map<Long, ComercialIf> mapaComercial = new HashMap<Long, ComercialIf>();
	private Map<String, DerechoProgramaIf> mapaDerechoProgramaPorNombre = new HashMap<String, DerechoProgramaIf>();
	private Map<Long, ProductoIf> mapaProducto = new HashMap<Long, ProductoIf>();
	private Map<Long, GenericoIf> mapaGenerico = new HashMap<Long, GenericoIf>();
	private Map<Long, Map<Long, ProductoIf>> mapaGenericoMapaProveedorProducto = new HashMap<Long, Map<Long, ProductoIf>>();

	public PlanMedioTvModel() {
		//proveedorRepetidoEnGenerico();
		//setearVersionPlan();
		//setearVersionOrdenMedio();
		cargarMapas();
		anchoColumnasTabla();
		cargarCombos();
		initKeyListeners();
		showSaveMode();
		initListeners();
		initMapasPauta();
		getTblOrdenesMedios().addMouseListener(oMouseoAdapterTblOrdenesMedio);
		getTblOrdenesMediosCmp().addMouseListener(oMouseoAdapterTblOrdenesMedioComp);
		getTblSubPeriodo().addMouseListener(oMouseAdapterTblSubPeriodo);
		getTblSubPeriodo().addKeyListener(oKeyAdapterTblSubPeriodo);
		cargarTreeTV();
		cargarTreePrensa();

		new HotKeyComponent(this);
	}
	
	public void proveedorRepetidoEnGenerico(){
		try{
			Map proveedores = new HashMap();
			List<String> repetidos = new ArrayList<String>();
			Collection genericos = SessionServiceLocator.getGenericoSessionService().getGenericoList();
			Iterator genericosIt = genericos.iterator();
			while(genericosIt.hasNext()){
				GenericoIf generico = (GenericoIf)genericosIt.next();
				proveedores.clear();
				Collection productos = SessionServiceLocator.getProductoSessionService().findProductoByGenericoId(generico.getId());
				Iterator productosIt = productos.iterator();
				while(productosIt.hasNext()){
					ProductoIf producto = (ProductoIf)productosIt.next();
					if(proveedores.get(producto.getProveedorId()) == null){
						proveedores.put(producto.getProveedorId(), producto);
					}else{
						ClienteOficinaIf proveedor = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(producto.getProveedorId());
						String repetido = "proveedor repetido: " + proveedor.getDescripcion() + " , generico: " + generico.getNombre();
						repetidos.add(repetido);
					}
				}
			}
			
			for(String repetido : repetidos){
				System.out.println(repetido);
			}
			
		}catch (GenericBusinessException e){
			e.printStackTrace();
		}
	}
	
	public void setearVersionPlan(){
		try {
			Map<String,Vector<PlanMedioIf>> planesPorCodigo = new HashMap<String,Vector<PlanMedioIf>>();
			Collection planesMedios = SessionServiceLocator.getPlanMedioSessionService().getPlanMedioList();
			Iterator planesMediosIt = planesMedios.iterator();
			while(planesMediosIt.hasNext()){
				PlanMedioIf planMedio = (PlanMedioIf)planesMediosIt.next();
				
				Vector<PlanMedioIf> planesDelCodigo = new Vector<PlanMedioIf>();
				if(planesPorCodigo.get(planMedio.getCodigo()) == null){
					planesDelCodigo.add(planMedio);
					planesPorCodigo.put(planMedio.getCodigo(), planesDelCodigo);
				}else{
					planesDelCodigo = planesPorCodigo.get(planMedio.getCodigo());
					planesDelCodigo.add(planMedio);
					planesPorCodigo.put(planMedio.getCodigo(), planesDelCodigo);
				}				
			}
			
			List<Vector<PlanMedioIf>> planesOrdenados = new ArrayList<Vector<PlanMedioIf>>();
			Iterator planesPorCodigoIt = planesPorCodigo.keySet().iterator();
			while(planesPorCodigoIt.hasNext()){
				String codigo = (String)planesPorCodigoIt.next();
				Vector<PlanMedioIf> planesDelCodigo = planesPorCodigo.get(codigo);
				Collections.sort(planesDelCodigo,ordenarPlanesPorId);
				planesOrdenados.add(planesDelCodigo);
			}
			
			for(Vector<PlanMedioIf> vectorPlanes : planesOrdenados){
				for(int i = 0; i < vectorPlanes.size(); i++){
					PlanMedioIf planConRevision = vectorPlanes.get(i);
					int revisionNumero = i + 1;
					String revision = "";
					if(revisionNumero < 10){
						revision = "0" + revisionNumero;
					}else{
						revision = String.valueOf(revisionNumero);
					}
					planConRevision.setRevision(revision);
					SessionServiceLocator.getPlanMedioSessionService().savePlanMedio(planConRevision);
				}
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	/*public void setearVersionOrdenMedio(){
		try {
			Map<String,Vector<OrdenMedioIf>> ordenesPorCodigo = new HashMap<String,Vector<OrdenMedioIf>>();
			Collection ordenesMedios = SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedioList();
			Iterator ordenesMediosIt = ordenesMedios.iterator();
			while(ordenesMediosIt.hasNext()){
				OrdenMedioIf ordenMedio = (OrdenMedioIf)ordenesMediosIt.next();
				
				Vector<OrdenMedioIf> ordenesDelCodigo = new Vector<OrdenMedioIf>();
				if(ordenesPorCodigo.get(ordenMedio.getCodigo()) == null){
					ordenesDelCodigo.add(ordenMedio);
					ordenesPorCodigo.put(ordenMedio.getCodigo(), ordenesDelCodigo);
				}else{
					ordenesDelCodigo = ordenesPorCodigo.get(ordenMedio.getCodigo());
					ordenesDelCodigo.add(ordenMedio);
					ordenesPorCodigo.put(ordenMedio.getCodigo(), ordenesDelCodigo);
				}				
			}
			
			List<Vector<OrdenMedioIf>> ordenesOrdenados = new ArrayList<Vector<OrdenMedioIf>>();
			Iterator ordenesPorCodigoIt = ordenesPorCodigo.keySet().iterator();
			while(ordenesPorCodigoIt.hasNext()){
				String codigo = (String)ordenesPorCodigoIt.next();
				Vector<OrdenMedioIf> ordenesDelCodigo = ordenesPorCodigo.get(codigo);
				Collections.sort(ordenesDelCodigo,ordenarOrdenesPorId);
				ordenesOrdenados.add(ordenesDelCodigo);
			}
			
			for(Vector<OrdenMedioIf> vectorOrdenes : ordenesOrdenados){
				for(int i = 0; i < vectorOrdenes.size(); i++){
					OrdenMedioIf ordenConRevision = vectorOrdenes.get(i);
					int revisionNumero = i + 1;
					String revision = "";
					if(revisionNumero < 10){
						revision = "0" + revisionNumero;
					}else{
						revision = String.valueOf(revisionNumero);
					}
					ordenConRevision.setRevision(revision);
					SessionServiceLocator.getOrdenMedioSessionService().saveOrdenMedio(ordenConRevision);
				}
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}*/
	
	Comparator<PlanMedioIf> ordenarPlanesPorId = new Comparator<PlanMedioIf>() {
		public int compare(PlanMedioIf o1, PlanMedioIf o2) {
			return o1.getId().compareTo(o2.getId());
		}
	};
	
	Comparator<OrdenMedioIf> ordenarOrdenesPorId = new Comparator<OrdenMedioIf>() {
		public int compare(OrdenMedioIf o1, OrdenMedioIf o2) {
			return o1.getId().compareTo(o2.getId());
		}
	};

	public void cargarMapas() {
		try {
			mapaDerechoPrograma.clear();
			mapaDerechoProgramaPorNombre.clear();
			Collection derechosPrograma = SessionServiceLocator
					.getDerechoProgramaSessionService()
					.findDerechoProgramaByEmpresaId(Parametros.getIdEmpresa());
			Iterator derechosProgramaIt = derechosPrograma.iterator();
			while (derechosProgramaIt.hasNext()) {
				DerechoProgramaIf derechoPrograma = (DerechoProgramaIf) derechosProgramaIt.next();
				mapaDerechoPrograma.put(derechoPrograma.getId(), derechoPrograma);
				mapaDerechoProgramaPorNombre.put(derechoPrograma.getNombre(), derechoPrograma);
			}

			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator
					.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while (clientesOficinaIt.hasNext()) {
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clientesOficinaIt
						.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}			
			
			mapaComercial.clear();
			Collection comerciales = SessionServiceLocator
					.getComercialSessionService().findComercialByEmpresaId(Parametros.getIdEmpresa());
			Iterator comercialesIt = comerciales.iterator();
			while (comercialesIt.hasNext()) {
				ComercialIf comercial = (ComercialIf) comercialesIt.next();
				mapaComercial.put(comercial.getId(), comercial);
			}
			
			mapaProducto.clear();
			Collection productosProveedor = SessionServiceLocator
					.getProductoSessionService().findProductoByEmpresaId(Parametros.getIdEmpresa());
			Iterator productosProveedorIt = productosProveedor.iterator();
			while (productosProveedorIt.hasNext()) {
				ProductoIf producto = (ProductoIf) productosProveedorIt
						.next();
				mapaProducto.put(producto.getId(), producto);
			}
			
			
			Map aMap = new HashMap();
			aMap.put("mediosProduccion", "M");
			aMap.put("empresaId", Parametros.getIdEmpresa());
						
			mapaGenerico.clear();
			Collection genericos = SessionServiceLocator
					.getGenericoSessionService().findGenericoByQuery(aMap);
			Iterator genericosIt = genericos.iterator();
			while (genericosIt.hasNext()) {
				GenericoIf generico = (GenericoIf) genericosIt
						.next();
				mapaGenerico.put(generico.getId(), generico);
			}
			
			mapaGenericoMapaProveedorProducto.clear();
			Iterator genericosColeccionIt = genericos.iterator();
			while (genericosColeccionIt.hasNext()) {
				GenericoIf genericoIf = (GenericoIf) genericosColeccionIt.next();
				
				Map<Long, ProductoIf> mapaProveedorProducto = new HashMap<Long, ProductoIf>();
				Collection productosGenerico = SessionServiceLocator.getProductoSessionService().findProductoByGenericoId(genericoIf.getId());
				Iterator productosGenericoIt = productosGenerico.iterator();
				while (productosGenericoIt.hasNext()) {
					ProductoIf productoIf = (ProductoIf) productosGenericoIt.next();
					mapaProveedorProducto.put(productoIf.getProveedorId(), productoIf);
				}
				mapaGenericoMapaProveedorProducto.put(genericoIf.getId(), mapaProveedorProducto);
			}			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void initKeyListeners() {
		getCbPautaBasica().setVisible(false);
		// getJtpPlanMedio().setEnabledAt(2, false);
		getTpArbolMedios().setEnabledAt(1, false);
		getTpArbolMedios().setEnabledAt(2, false);
		getBtnTvData().setVisible(false);
		getSpArbolTelevision().setVisible(false);
		getArbolTelevision().setVisible(false);
		getLblCanalTv().setVisible(false);
		getLblProgramaTv().setVisible(false);
		getLblHoraInicioPrograma().setVisible(false);
		getLblHoraFinPrograma().setVisible(false);
		getLblDiasPrograma().setVisible(false);
		getLblRatingTv().setVisible(false);
		getLblVCunaTarifa().setVisible(false);
		getLblVCunaNegocio().setVisible(false);
		getLblComercialTv().setVisible(false);
		getLblDerechoPrograma().setVisible(false);
		getLblVersionPrograma().setVisible(false);
		getTxtCanalTv().setVisible(false);
		getTxtProgramaTv().setVisible(false);
		getTxtHoraInicioPrograma().setVisible(false);
		getTxtHoraFinPrograma().setVisible(false);
		getTxtDiasPrograma().setVisible(false);
		getTxtRatingTv().setVisible(false);
		getTxtVCunaTarifa().setVisible(false);
		getTxtVCunaNegocio().setVisible(false);
		getTxtComercialTv().setVisible(false);
		getTxtDerechoPrograma().setVisible(false);
		getTxtVersionPrograma().setVisible(false);
		getBtnAgregarProgramaTv().setVisible(false);
		getBtnEliminarProgramaTv().setVisible(false);
		getBtnCrearMapaPautaTv().setVisible(false);

		getBtnCorporacion().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCorporacion().setToolTipText("Buscar Corporaci\u00f3n");
		getBtnCliente().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnOficina().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnOficina().setToolTipText("Buscar Oficina");
		// ADD 9 JUNIO
		getBtnPlanMedioRelacion().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnPlanMedioRelacion().setToolTipText(
				"Buscar Plan Medio a relacionar");
		getBtnPlanMedioRelacion().setEnabled(false);
		// END 9 JUNIO

		// ADD 15 JUNIO
		getCbPlanMedioNuevaVersion().setEnabled(false);
		getCbPlanMedioNuevoMes().setEnabled(false);

		getTextCodigoOrden().setEnabled(false);
		getBtnCambiarCodigo().setEnabled(false);
		// ADD 29 JUNIO
		getBtnCambiarCodigo().setVisible(false);
		getBtnLimpiarCodigoOrden().setEnabled(false);
		// END 15 JUNIO

		getBtnAgregarDetalle().setText("");
		getBtnActualizarDetalle().setText("");
		getBtnEliminarDetalle().setText("");

		getBtnGuayaquil()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnGuayaquil().setToolTipText("Recalcular con valor ingresado");
		getBtnQuito()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnQuito().setToolTipText("Recalcular con valor ingresado");
		getBtnTotalCiudad()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnTotalCiudad().setToolTipText("Recalcular con valor ingresado");

		getBtnAgregarDetalle().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarDetalle().setToolTipText("Agregar Detalle");
		getBtnActualizarDetalle()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarDetalle().setToolTipText("Actualizar Detalle");
		getBtnEliminarDetalle()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarDetalle().setToolTipText("Eliminar Detalle");

		getTxtCodigo().setEditable(false);
		getLblMedio().setVisible(false);
		getCmbMedio().setVisible(false);
		getLblTipoPeriodoMapa().setVisible(false);
		getCmbTipoPeriodoMapa().setVisible(false);

		getTblSubPeriodo()
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblComercial().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 9 MAYO
		getTblOrdenesMedios().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		getArbolTelevision().getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		getArbolPrensa().getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtConcepto().addKeyListener(new TextChecker(MAX_LONGITUD_CONCEPTO));
		getTxtAutorizacionSAP().addKeyListener(
				new TextChecker(MAX_LONGITUD_AUTORIZACION_SAP));
		getTxtGuayaquil().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtGuayaquil().addKeyListener(new NumberTextField());
		getTxtQuito().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtQuito().addKeyListener(new NumberTextField());
		getTxtCanalTv().addKeyListener(new TextChecker(MAX_LONGITUD_CANALTV));
		getTxtProgramaTv().addKeyListener(
				new TextChecker(MAX_LONGITUD_PROGRAMATV));
		getTxtHoraInicioPrograma().addKeyListener(
				new TextChecker(MAX_LONGITUD_HORAPROGRAMA));
		getTxtHoraFinPrograma().addKeyListener(
				new TextChecker(MAX_LONGITUD_HORAPROGRAMA));
		getTxtDiasPrograma().addKeyListener(
				new TextChecker(MAX_LONGITUD_DIASPROGRAMA));
		getTxtRatingTv().addKeyListener(
				new TextChecker(MAX_LONGITUD_RATINGPROGRAMA));
		getTxtRatingTv().addKeyListener(new NumberTextFieldDecimal());
		getTxtVCunaNegocio()
				.addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtVCunaNegocio().addKeyListener(new NumberTextFieldDecimal());
		getTxtVCunaTarifa().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtVCunaTarifa().addKeyListener(new NumberTextFieldDecimal());
		getTxtObservacionOrdenMedio().addKeyListener(
				new TextChecker(MAX_LONGITUD_ORDEN_MEDIO_OBSERVACION));

		getTxtPorcentajeDescuentoVenta().addKeyListener(
				new TextChecker(MAX_LONGITUD_PORCENTAJE));
		getTxtPorcentajeDescuentoVenta().addKeyListener(
				new NumberTextFieldDecimal());
		getTxtPorcentajeComisionAgencia().addKeyListener(
				new TextChecker(MAX_LONGITUD_PORCENTAJE));
		getTxtPorcentajeComisionAgencia().addKeyListener(
				new NumberTextFieldDecimal());
		getTxtPorcentajeBonificacionVenta().addKeyListener(
				new TextChecker(MAX_LONGITUD_PORCENTAJE));
		getTxtPorcentajeBonificacionVenta().addKeyListener(
				new NumberTextFieldDecimal());
		getTxtPorcentajeDescuentoOrdenMedio().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE));
		getTxtPorcentajeDescuentoOrdenMedio().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeComisionAdicional().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE));
		getTxtPorcentajeComisionAdicional().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeBonificacionCompra().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE));
		getTxtPorcentajeBonificacionCompra().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeCanje().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE));
		getTxtPorcentajeCanje().addKeyListener(new NumberTextFieldDecimal());
		
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
		getTxtTotalCiudad().setEditable(false);
		getTxtModificaciones().setEditable(false);
		// ADD 9 JUNIO
		getTextPlanMedioRelacion().setEditable(false);
		// END 8 JUNIO

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
		getCmbSubPeriodoFechaInicio()
				.setFormat(Utilitarios.setFechaUppercase());
		getCmbSubPeriodoFechaInicio().setEditable(false);
		getCmbSubPeriodoFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbSubPeriodoFechaFin().setEditable(false);

		getCmbFechaAprobacion().setLocale(Utilitarios.esLocale);
		getCmbFechaAprobacion().setShowNoneButton(true);
		getCmbFechaAprobacion().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaAprobacion().setEditable(false);

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

		// habilitar o no la bonificacion
		try {
			Collection parametrosEmpresa = SessionServiceLocator
					.getParametroEmpresaSessionService()
					.findParametroEmpresaByCodigo("HABILITARBONIFICACIONVENTA");
			if (parametrosEmpresa.size() > 0) {
				Iterator parametrosEmpresaIt = parametrosEmpresa.iterator();
				while (parametrosEmpresaIt.hasNext()) {
					ParametroEmpresaIf parametroBonificacion = (ParametroEmpresaIf) parametrosEmpresaIt
							.next();
					if (!parametroBonificacion.getValor().equals("S")) {
						getLblPorcentajeBonifiacionVenta().setVisible(false);
						getTxtPorcentajeBonificacionVenta().setVisible(false);
						getLblBonificacionVenta().setVisible(false);
						getTxtBonificacionVenta().setVisible(false);
						getLblBonificacionVentaPlanMedio().setVisible(false);
						getTxtBonificacionVentaPlanMedio().setVisible(false);
						getLblSubtotalBonificacionVenta().setVisible(false);
						getTxtSubtotalBonificacionVenta().setVisible(false);
						getLblSubtotalBonificacionVentaPlanMedio().setVisible(
								false);
						getTxtSubtotalBonificacionVentaPlanMedio().setVisible(
								false);
					}
				}
			} else {
				getLblPorcentajeBonifiacionVenta().setVisible(false);
				getTxtPorcentajeBonificacionVenta().setVisible(false);
				getLblBonificacionVenta().setVisible(false);
				getTxtBonificacionVenta().setVisible(false);
				getLblBonificacionVentaPlanMedio().setVisible(false);
				getTxtBonificacionVentaPlanMedio().setVisible(false);
				getLblSubtotalBonificacionVenta().setVisible(false);
				getTxtSubtotalBonificacionVenta().setVisible(false);
				getLblSubtotalBonificacionVentaPlanMedio().setVisible(false);
				getTxtSubtotalBonificacionVentaPlanMedio().setVisible(false);
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void initMapasPauta() {
		// Cabeceras fijas:
		// scrollPanelMapaPrensa.getTable().getRowHeaderTable().getColumnModel()
		// Cabeceras de fechas:
		// scrollPanelMapaPrensa.getTable().getMainTable().getColumnModel()
		// Totales por fecha, footer:
		// scrollPanelMapaPrensa.getTable().getColumnFooterTable().getColumnModel()
		// Total por filas:
		// scrollPanelMapaPrensa.getTable().getRowFooterTable().getColumnModel()
		scrollPanelMapaPrensa = new TableScrollPaneMapaPauta(
				cabecerasFijasPrensa, cabecerasVariablesPrensa, datosPrensa,
				numeroFilasPrensa);
		// getPanelMapaPautaPrensa().add(scrollPanelMapaPrensa.getTable(),
		// cc.xywh(3, 3, 5, 5));
		tableScrollPrensaRemovido = scrollPanelMapaPrensa.getTable();

		scrollPanelMapaTv = new TableScrollPaneMapaPauta(cabecerasFijasTv,
				cabecerasVariablesTv, datosTv, numeroFilasTv);
		getPanelMapaPautaTv().add(scrollPanelMapaTv.getTable(),
				cc.xywh(3, 5, 7, 5));
		tableScrollTvRemovido = scrollPanelMapaTv.getTable();

		/*
		 * scrollPanelTvTGRP = new TableScrollPaneMapaPauta(cabecerasFijasTv,
		 * cabecerasVariablesTv, datosTv, numeroFilasTv);
		 * getPanelTGRPtv().add(scrollPanelTvTGRP.getTable(), cc.xywh(3, 5, 5,
		 * 5)); tableScrollTvRemovido = scrollPanelTvTGRP.getTable();
		 */
	}

	private void iniciarMapaTv() {
		cabecerasFijasTv = new String[] {};
		cabecerasVariablesTv = new String[] {};
		datosTv = new Object[][] {};
		datosTGRP = new Object[][] {};
		numeroFilasTv = 20;
		if (scrollPanelMapaTv != null)
			getPanelMapaPautaTv().remove(scrollPanelMapaTv.getTable());
		scrollPanelMapaTv = new TableScrollPaneMapaPauta(cabecerasFijasTv,
				cabecerasVariablesTv, datosTv, numeroFilasTv);
		getPanelMapaPautaTv().add(scrollPanelMapaTv.getTable(),
				cc.xywh(3, 5, 7, 5));
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
			SpiritAlert.createAlert("Error al generar el árbol de TV!",
					SpiritAlert.ERROR);
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

	private DefaultTreeModel generateTreeTvModel()
			throws GenericBusinessException {

		/*
		 * String[][][] arbol = { { {"ECUAVISA","CANAL UNO"},
		 * {"VIVOS","CERO TOLERANCIA"}, {"FUTBOL","NOVELA"},
		 * {"comercialA","comercialB","comercialC"} }, {
		 * {"programa1","programa2"}, {"comercial1","comercial2"} } };
		 * System.out.println("arbol[0][0][0]: " + arbol[0][0][0]);
		 * System.out.println("arbol[0][0][1]: " + arbol[0][0][1]);
		 * System.out.println("arbol[0][1][0]: " + arbol[0][1][0]);
		 * System.out.println("arbol[0][1][1]: " + arbol[0][1][1]);
		 * System.out.println("arbol[0][2][0]: " + arbol[0][2][0]);
		 * System.out.println("arbol[0][2][1]: " + arbol[0][2][1]);
		 * System.out.println("arbol[0][3][0]: " + arbol[0][3][0]);
		 * System.out.println("arbol[0][3][1]: " + arbol[0][3][1]);
		 * 
		 * System.out.println("arbol[1][0][0]: " + arbol[1][0][0]);
		 * System.out.println("arbol[1][0][1]: " + arbol[1][0][1]);
		 * System.out.println("arbol[1][1][0]: " + arbol[1][1][0]);
		 * System.out.println("arbol[1][1][1]: " + arbol[1][1][1]);
		 * 
		 * for(int i=0; i<arbol[0][0].length; i++){ String nodo =
		 * arbol[0][0][i]; DefaultMutableTreeNode cuentaNodo = new
		 * DefaultMutableTreeNode(nodo); root.add(cuentaNodo);
		 * insertarNodosTv(i,cuentaNodo,arbol); }
		 */

		DefaultMutableTreeNode rootArbolTv = new DefaultMutableTreeNode(
				"ARBOL DE TELEVISION");

		if (!pautasTv.isEmpty()) {
			for (int i = 0; i < canales.size(); i++) {
				String canal = canales.get(i).toUpperCase();
				DefaultMutableTreeNode canalNodo = new DefaultMutableTreeNode(
						canal);
				rootArbolTv.add(canalNodo);
				insertarNodosTv(i, canalNodo);
			}
		}

		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(rootArbolTv);

		int subPeriodoSeleccionado = getCmbTipoPeriodo().getSelectedIndex();
		if (subPeriodoSeleccionado != -1) {
			modelArbolTvColecciones.set(subPeriodoSeleccionado,
					defaultTreeModel);
		}

		return defaultTreeModel;
	}

	private void crearColeccionesCanalesProgramas() {
		canales.clear();
		programas.clear();
		for (int i = 0; i < pautasTv.size(); i++) {
			String canal = pautasTv.get(i).getCanal();
			if (!existeCanal(canales, canal)) {
				canales.add(canal);
			}
		}
		for (int i = 0; i < pautasTv.size(); i++) {
			programas.add(pautasTv.get(i).getPrograma());
		}
	}

	private void cargarVectorComercialesSeleccionados() {
		comercialesSeleccionadosTabla.clear();
		if (!comercialVectorTabla.isEmpty()) {
			boolean comercialSeleccionado = false;
			for (int i = 0; i < comercialVectorTabla.size(); i++) {
				comercialSeleccionado = (Boolean) getTblComercial().getModel()
						.getValueAt(i, 0);
				if (comercialSeleccionado == true) {
					comercialesSeleccionadosTabla.add(comercialVectorTabla
							.get(i));
				}
			}
		}
	}

	// ADD 29 AGOSTO
	public boolean validarComercialesPautaBasica() {
		boolean isValido = true;
		ArrayList<String> list = new ArrayList<String>();
		if (!comercialesSeleccionadosTabla.isEmpty()) {
			for (int i = 0; i < comercialesSeleccionadosTabla.size(); i++) {
				ComercialIf comercialIf = comercialesSeleccionadosTabla.get(i);
				if (list.size() == 0) {
					list.add(comercialIf.getVersion());
				} else {
					// COMENTED 6 OCTUBRE
					/*
					 * if (!list.contains(comercialIf.getVersion())){ isValido =
					 * false; break; }
					 */

					// ADD 6 OCTUBRE
					for (String identificacion : list) {
						if (identificacion.compareTo(comercialIf.getVersion()) != 0) {
							isValido = false;
							break;
						}
					}
					// END 6 OCTUBRE
				}

				// ADD 6 OCTUBRE
				if (!isValido) {
					break;
				}
				// END 6 OCTUBRE
			}
		}
		return isValido;
	}

	// END 29 AGOSTO

	public void limpiarSeleccionesTablaComerciales() {
		if (!comercialVectorTabla.isEmpty()) {
			for (int i = 0; i < comercialVectorTabla.size(); i++) {
				getTblComercial().getModel().setValueAt(false, i, 0);
			}
		}
	}

	// ADD 29 AGOSTO
	public void marcarSeleccionesTablaComerciales() {
		if (!comercialVectorTabla.isEmpty()) {
			for (int i = 0; i < comercialVectorTabla.size(); i++) {
				getTblComercial().getModel().setValueAt(true, i, 0);
			}
		}
	}

	// END 29 AGOSTO

	private void insertarNodosTv(Integer padre,
			DefaultMutableTreeNode nodoSeleccionado) {
		for (int k = 0; k < programas.size(); k++) {
			String nodo = programas.get(k);
			DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(nodo);
			if (pautasTv.get(k).getCanal().toUpperCase()
					.equals(nodoSeleccionado.getUserObject())) {
				nodoSeleccionado.add(nodoHijo);
				for (int i = 0; i < comercialesSeleccionadosTabla.size(); i++) {
					ComercialIf comercial = comercialesSeleccionadosTabla
							.get(i);
					DefaultMutableTreeNode nodoComercial = new DefaultMutableTreeNode(
							comercial);
					nodoHijo.add(nodoComercial);
				}
			}
		}
	}

	private void generarComercialesColeccionArbolTv() {
		try {
			mapaPautaComerciales.clear();
			ComercialIf comercialSeleccionado = null;
			String canalSeleccionado = "";
			String programaSeleccionado = "";
			String canal = "";
			String programa = "";
			ComercialIf comercial = null;
			TreePath[] treePaths = getArbolTelevision()
					.getCheckBoxTreeSelectionModel().getSelectionPaths();
			if (treePaths != null) {
				for (int i = 0; i < treePaths.length; i++) {
					TreePath path = treePaths[i];
					DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) path
							.getLastPathComponent();
					if (nodo != null) {
						if (nodo.isRoot()) {
							for (int m = 0; m < pautasTvExtendida.size(); m++) {
								comercial = comercialesSeleccionadosTablaExtendida
										.get(m);
								mapaPautaComerciales.put(
										pautasTvExtendida.get(m).getId(),
										comercial);
							}
						} else if (((DefaultMutableTreeNode) nodo.getParent())
								.isRoot()) {
							canalSeleccionado = (String) nodo.getUserObject();
							for (int m = 0; m < pautasTvExtendida.size(); m++) {
								canal = pautasTvExtendida.get(m).getCanal()
										.toUpperCase();
								comercial = comercialesSeleccionadosTablaExtendida
										.get(m);
								if (canalSeleccionado.equals(canal)) {
									mapaPautaComerciales.put(pautasTvExtendida
											.get(m).getId(), comercial);
								}
							}
						} else if (((DefaultMutableTreeNode) nodo.getParent()
								.getParent()).isRoot()) {
							DefaultMutableTreeNode padre = (DefaultMutableTreeNode) nodo
									.getParent();
							canalSeleccionado = (String) padre.getUserObject();
							programaSeleccionado = (String) nodo
									.getUserObject();
							for (int m = 0; m < pautasTvExtendida.size(); m++) {
								canal = pautasTvExtendida.get(m).getCanal()
										.toUpperCase();
								programa = pautasTvExtendida.get(m)
										.getPrograma();
								comercial = comercialesSeleccionadosTablaExtendida
										.get(m);
								if ((canalSeleccionado.equals(canal))
										&& (programaSeleccionado
												.equals(programa))) {
									mapaPautaComerciales.put(pautasTvExtendida
											.get(m).getId(), comercial);
								}
							}
						} else if (((DefaultMutableTreeNode) nodo.getParent()
								.getParent().getParent()).isRoot()) {
							DefaultMutableTreeNode abuelo = (DefaultMutableTreeNode) nodo
									.getParent().getParent();
							canalSeleccionado = (String) abuelo.getUserObject();
							DefaultMutableTreeNode padre = (DefaultMutableTreeNode) nodo
									.getParent();
							programaSeleccionado = (String) padre
									.getUserObject();
							comercialSeleccionado = (ComercialIf) nodo
									.getUserObject();
							for (int m = 0; m < pautasTvExtendida.size(); m++) {
								canal = pautasTvExtendida.get(m).getCanal()
										.toUpperCase();
								programa = pautasTvExtendida.get(m)
										.getPrograma();
								comercial = comercialesSeleccionadosTablaExtendida
										.get(m);
								if ((canalSeleccionado.equals(canal))
										&& (programaSeleccionado
												.equals(programa))
										&& (comercialSeleccionado
												.equals(comercial))) {
									mapaPautaComerciales.put(pautasTvExtendida
											.get(m).getId(), comercial);
								}
							}
						}
					}
				}

				/*
				 * for (Iterator it=mapaPautaComerciales.keySet().iterator();
				 * it.hasNext();){ int idKey = (Integer) it.next();
				 * System.out.println("llaveEx: " + idKey);
				 * System.out.println("nodoEx: " +
				 * mapaPautaComerciales.get(idKey)); }
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert(
					"Error al generar la colección de comerciales Tv",
					SpiritAlert.ERROR);
		}
	}

	/*
	 * private void insertarNodosTv(Integer padre, DefaultMutableTreeNode
	 * nodoSeleccionado, String[][][] arbol) { for(int k=0;
	 * k<arbol[0][padre+1].length; k++){ String nodo = arbol[0][padre+1][k];
	 * DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(nodo);
	 * nodoSeleccionado.add(nodoHijo); if(padre < 2){
	 * insertarNodosTv(2,nodoHijo,arbol); } } }
	 */

	public boolean existeCanal(Vector<String> canales, String canal) {
		for (String canalSeleccionado : canales) {
			canalSeleccionado = canalSeleccionado.toUpperCase();
			canal = canal.toUpperCase();
			if (canalSeleccionado.equals(canal)) {
				return true;
			}
		}
		return false;
	}

	private DefaultTreeModel generateTreePrensaModel() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(
				"ARBOL DE PRENSA");
		List<ClienteIf> diarios = null;

		try {
			Collection tipoProveedorColeccion = SessionServiceLocator
					.getTipoProveedorSessionService()
					.findTipoProveedorByNombre("PRENSA");
			if (!tipoProveedorColeccion.isEmpty()) {
				Long tipoProveedorPrensa = ((TipoProveedorIf) tipoProveedorColeccion
						.iterator().next()).getId();
				diarios = (List<ClienteIf>) SessionServiceLocator
						.getClienteSessionService()
						.findClienteByTipoproveedorId(tipoProveedorPrensa);

				for (int i = 0; i < diarios.size(); i++) {
					ClienteIf nodo = diarios.get(i);
					DefaultMutableTreeNode diarioNodo = new DefaultMutableTreeNode(
							nodo);
					root.add(diarioNodo);
					
					//despuesto se puede usar porque si funciona.
					//insertarNodosSeccion(diarioNodo, nodo);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(
					"Se ha producido un error al generar el árbol de prensa",
					SpiritAlert.ERROR);
		}

		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);
		return defaultTreeModel;
	}

	private void insertarNodosSeccion(DefaultMutableTreeNode nodoSeleccionado,
			ClienteIf diario) {
		try {
			List<PrensaSeccionIf> prensaSecciones = (List<PrensaSeccionIf>) SessionServiceLocator
					.getPrensaSeccionSessionService()
					.findPrensaSeccionByClienteId(diario.getId());
			for (int k = 0; k < prensaSecciones.size(); k++) {
				PrensaSeccionIf nodoSeccion = prensaSecciones.get(k);
				DefaultMutableTreeNode nodoHijoSeccion = new DefaultMutableTreeNode(
						nodoSeccion);
				nodoSeleccionado.add(nodoHijoSeccion);
				insertarNodosUbicacion(nodoHijoSeccion, diario, nodoSeccion);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void insertarNodosUbicacion(
			DefaultMutableTreeNode nodoSeleccionado, ClienteIf diario,
			PrensaSeccionIf nodoSeccion) {
		try {
			List<PrensaUbicacionIf> prensaUbicaciones = (List<PrensaUbicacionIf>) SessionServiceLocator
					.getPrensaUbicacionSessionService()
					.findPrensaUbicacionByClienteId(diario.getId());
			for (int m = 0; m < prensaUbicaciones.size(); m++) {
				PrensaUbicacionIf nodoUbicacion = prensaUbicaciones.get(m);
				DefaultMutableTreeNode nodoHijoUbicacion = new DefaultMutableTreeNode(
						nodoUbicacion);
				nodoSeleccionado.add(nodoHijoUbicacion);
				insertarNodosFormato(nodoHijoUbicacion, diario, nodoSeccion,
						nodoUbicacion);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void insertarNodosFormato(DefaultMutableTreeNode nodoSeleccionado,
			ClienteIf diario, PrensaSeccionIf nodoSeccion,
			PrensaUbicacionIf nodoUbicacion) {
		try {
			List<PrensaFormatoIf> prensaFormatos = (List<PrensaFormatoIf>) SessionServiceLocator
					.getPrensaFormatoSessionService()
					.findPrensaFormatoByClienteId(diario.getId());
			Map aMap = new HashMap();
			for (int n = 0; n < prensaFormatos.size(); n++) {
				PrensaFormatoIf nodoFormato = prensaFormatos.get(n);
				DefaultMutableTreeNode nodoHijoFormato = new DefaultMutableTreeNode(
						nodoFormato);
				aMap.clear();
				aMap.put("prensaUbicacionId", nodoUbicacion.getId());
				aMap.put("prensaFormatoId", nodoFormato.getId());
				if (!SessionServiceLocator.getPrensaTarifaSessionService()
						.findPrensaTarifaByQuery(aMap).isEmpty()) {
					nodoSeleccionado.add(nodoHijoFormato);
					insertarNodosTarifa(nodoHijoFormato, diario, nodoSeccion,
							nodoUbicacion, nodoFormato);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void insertarNodosTarifa(DefaultMutableTreeNode nodoSeleccionado,
			ClienteIf diario, PrensaSeccionIf nodoSeccion,
			PrensaUbicacionIf nodoUbicacion, PrensaFormatoIf nodoFormato) {
		try {
			Map aMap = new HashMap();
			aMap.put("clienteId", diario.getId());
			aMap.put("prensaSeccionId", nodoSeccion.getId());
			aMap.put("prensaUbicacionId", nodoUbicacion.getId());
			aMap.put("prensaFormatoId", nodoFormato.getId());
			List<PrensaTarifaIf> prensaTarifas = (List<PrensaTarifaIf>) SessionServiceLocator
					.getPrensaTarifaSessionService().findPrensaTarifaByQuery(
							aMap);
			for (int p = 0; p < prensaTarifas.size(); p++) {
				PrensaTarifaIf nodoTarifa = prensaTarifas.get(p);

				DefaultMutableTreeNode nodoHijoUbicacion = new DefaultMutableTreeNode(
						nodoTarifa);
				nodoSeleccionado.add(nodoHijoUbicacion);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void anchoColumnasTabla() {
		TableColumn anchoColumnaComercial = getTblComercial().getColumnModel()
				.getColumn(0);
		anchoColumnaComercial.setPreferredWidth(15);
		anchoColumnaComercial = getTblComercial().getColumnModel().getColumn(1);
		anchoColumnaComercial.setPreferredWidth(40);
		anchoColumnaComercial = getTblComercial().getColumnModel().getColumn(2);
		anchoColumnaComercial.setPreferredWidth(170);
		anchoColumnaComercial = getTblComercial().getColumnModel().getColumn(3);
		anchoColumnaComercial.setPreferredWidth(130);
		anchoColumnaComercial = getTblComercial().getColumnModel().getColumn(4);
		anchoColumnaComercial.setPreferredWidth(35);
		anchoColumnaComercial = getTblComercial().getColumnModel().getColumn(5);
		anchoColumnaComercial.setPreferredWidth(35);

		TableCellRendererHorizontalCenterAlignment tableCellCenterRenderer = new TableCellRendererHorizontalCenterAlignment();
		getTblComercial().getColumnModel().getColumn(4)
				.setCellRenderer(tableCellCenterRenderer);
		getTblComercial().getColumnModel().getColumn(5)
				.setCellRenderer(tableCellCenterRenderer);

		TableColumn anchoColumnaMapaTv = getTblTGRPtv().getColumnModel()
				.getColumn(0);
		anchoColumnaMapaTv.setPreferredWidth(25);
		anchoColumnaMapaTv = getTblTGRPtv().getColumnModel().getColumn(1);
		anchoColumnaMapaTv.setPreferredWidth(50);
		anchoColumnaMapaTv = getTblTGRPtv().getColumnModel().getColumn(2);
		anchoColumnaMapaTv.setPreferredWidth(110);
		anchoColumnaMapaTv = getTblTGRPtv().getColumnModel().getColumn(3);
		anchoColumnaMapaTv.setPreferredWidth(160);
		anchoColumnaMapaTv = getTblTGRPtv().getColumnModel().getColumn(4);
		anchoColumnaMapaTv.setPreferredWidth(60);
		anchoColumnaMapaTv = getTblTGRPtv().getColumnModel().getColumn(5);
		anchoColumnaMapaTv.setPreferredWidth(45);
		anchoColumnaMapaTv = getTblTGRPtv().getColumnModel().getColumn(6);
		anchoColumnaMapaTv.setPreferredWidth(37);
		anchoColumnaMapaTv = getTblTGRPtv().getColumnModel().getColumn(7);
		anchoColumnaMapaTv.setPreferredWidth(37);
		anchoColumnaMapaTv = getTblTGRPtv().getColumnModel().getColumn(8);
		anchoColumnaMapaTv.setPreferredWidth(37);
		anchoColumnaMapaTv = getTblTGRPtv().getColumnModel().getColumn(9);
		anchoColumnaMapaTv.setPreferredWidth(45);
		anchoColumnaMapaTv = getTblTGRPtv().getColumnModel().getColumn(10);
		anchoColumnaMapaTv.setPreferredWidth(65);
		anchoColumnaMapaTv = getTblTGRPtv().getColumnModel().getColumn(11);
		anchoColumnaMapaTv.setPreferredWidth(90);

		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblTGRPtv().getColumnModel().getColumn(0)
				.setCellRenderer(tableCellRenderer);
		getTblTGRPtv().getColumnModel().getColumn(5)
				.setCellRenderer(tableCellRenderer);
		getTblTGRPtv().getColumnModel().getColumn(6)
				.setCellRenderer(tableCellRenderer);
		getTblTGRPtv().getColumnModel().getColumn(7)
				.setCellRenderer(tableCellRenderer);
		getTblTGRPtv().getColumnModel().getColumn(8)
				.setCellRenderer(tableCellRenderer);
		getTblTGRPtv().getColumnModel().getColumn(9)
				.setCellRenderer(tableCellRenderer);
		getTblTGRPtv().getColumnModel().getColumn(10)
				.setCellRenderer(tableCellRenderer);
		getTblTGRPtv().getColumnModel().getColumn(11)
				.setCellRenderer(tableCellRenderer);
	}

	public void cargarCombos() {
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

		/*
		 * getCmbTipoPeriodoMapa().removeAllItems();
		 * getCmbTipoPeriodoMapa().addItem(NOMBRE_TIPO_LANZAMIENTO);
		 * getCmbTipoPeriodoMapa().addItem(NOMBRE_TIPO_MANTENIMIENTO);
		 * getCmbTipoPeriodoMapa().addItem(NOMBRE_TIPO_PROMOCIONAL);
		 * getCmbTipoPeriodoMapa().addItem(NOMBRE_TIPO_EXPECTATIVA);
		 * getCmbTipoPeriodoMapa().setSelectedIndex(0);
		 * 
		 * getCmbMedioMapa().removeAllItems();
		 * getCmbMedioMapa().addItem(NOMBRE_MEDIO_TELEVISION);
		 * getCmbMedioMapa().addItem(NOMBRE_MEDIO_RADIO);
		 * getCmbMedioMapa().addItem(NOMBRE_MEDIO_PRENSA);
		 * getCmbMedioMapa().addItem(NOMBRE_MEDIO_OTROS);
		 * getCmbMedioMapa().setSelectedIndex(0);
		 */
	}

	// estados del Plan de Medio
	private void cargarComboEstado() {
		getCmbEstado().removeAllItems();
		getCmbEstado().addItem(NOMBRE_ESTADO_EN_PROCESO);
		getCmbEstado().addItem(NOMBRE_ESTADO_PENDIENTE);
		getCmbEstado().addItem(NOMBRE_ESTADO_APROBADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_PREPAGADO);
		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_EN_PROCESO);
	}

	private void cargarComboGrupoObjetivo() {
		try {
			List target = (List) SessionServiceLocator
					.getGrupoObjetivoSessionService()
					.findGrupoObjetivoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTarget(), target);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert(
					"Se ha producido un error al cargar combo Target",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	MouseListener oMouseAdapterTblSubPeriodo = new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	MouseListener oMouseoAdapterTblOrdenesMedio = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			setSelectDetail(evt);
		}
	};

	// ADD 15 JUNIO
	// Para cuando el usuario seleccione una fila de la tabla de Ordenes Medio a
	// Comparar
	// o tb vista como la tabla de la derecha
	MouseListener oMouseoAdapterTblOrdenesMedioComp = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			setSelectDetailComp(evt);
		}
	};

	// END 15 JUNIO

	// ADD 15 JUNIO
	// Se lo llama cuando se selecciona una fila de la tabla de las ordenes de
	// la derecha
	private void setSelectDetailComp(ComponentEvent evt) {
		if (getTblOrdenesMediosCmp().getSelectedRow() != -1) {
			getTblOrdenesMedios().clearSelection();
			int position = ((JTable) evt.getSource()).getSelectedRow();
			// seteo los campos de texto orden medio segun el item que se
			// escogio
			int acumulador = 0;
			boolean isIndiceSubtotal = false;
			for (int i = 0; i < indiceProveedorSubtotalOrdenesSaved.size(); i++) {
				if (position == indiceProveedorSubtotalOrdenesSaved.get(i)) {
					isIndiceSubtotal = true;
				} else if (position > indiceProveedorSubtotalOrdenesSaved
						.get(i)) {
					acumulador++;
				}
			}

			if (!isIndiceSubtotal) {
				setPedidoDetalleSaved(position, acumulador);
			} else { // cuando han seleccionado un Subtotal
				SpiritAlert.createAlert(
						"Por favor seleccione una Orden de Medio Guardada",
						SpiritAlert.INFORMATION);
			}
		}
	}

	// END 15 JUNIO

	// Se lo llama cuando se selecciona una fila de la tabla de las ordenes de
	// la izquierda
	private void setSelectDetail(ComponentEvent evt) {
		if (getTblOrdenesMedios().getSelectedRow() != -1) {
			// para deseleccionar la fila seleccionada de la tabla de Ordenes de
			// Medio Comparar
			getTblOrdenesMediosCmp().clearSelection();

			int position = ((JTable) evt.getSource()).getSelectedRow();
			// seteo los campos de texto orden medio segun el item que se
			// escogio
			int acumulador = 0;
			boolean isIndiceSubtotal = false;
			for (int i = 0; i < indiceProveedorSubtotalOrdenes.size(); i++) {
				if (position == indiceProveedorSubtotalOrdenes.get(i)) {
					isIndiceSubtotal = true;
				} else if (position > indiceProveedorSubtotalOrdenes.get(i)) {
					acumulador++;
				}
			}

			if (!isIndiceSubtotal) {
				setPedidoDetalle(position, acumulador);
			} else {// cuando han seleccionado un Subtotal
				SpiritAlert.createAlert(
						"Por favor seleccione una Orden de Medio",
						SpiritAlert.INFORMATION);
			}
		}
	}

	// Setea los datos de la Orden de Medio seleccionada
	// de la tabla de Ordenes Medio en los textbox suman, descuento en
	// compra,subtotal
	private void setPedidoDetalleSaved(int position, int inidicesAcumulados) {
		
		DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMediosCmp().getModel();
		String tipo = (String) modelOrdenMedio.getValueAt(position, 5);
		String porcentajeDescuentoTmp = (String) modelOrdenMedio.getValueAt(position, 4);
		String porcentajeDescuentoTmpArray[] = porcentajeDescuentoTmp.split("%");
		String porcentajeDescuento = porcentajeDescuentoTmpArray[0];
		BigDecimal porcentajeDescuentoN = new BigDecimal(porcentajeDescuento);
		String tipoCanje[];
		String tipoCanjePorc[];
		String tipoCanjeValorPorc;
		getTxtPorcentajeDescuentoOrdenMedio().setText(porcentajeDescuento);

		if (tipo.compareTo("NORMAL") == 0) {
			getRbTipoPagoNormal().setSelected(true);
			getTxtPorcentajeCanje().setEnabled(false);
			getTxtPorcentajeCanje().setText("");
		} else {
			getRbTipoPagoCanje().setSelected(true);
			tipoCanje = tipo.split("-");
			tipoCanjePorc = tipoCanje[1].split("%");
			tipoCanjeValorPorc = tipoCanjePorc[0];
			getTxtPorcentajeCanje().setEnabled(true);
			getTxtPorcentajeCanje().setText(tipoCanjeValorPorc);
		}
		
		int contador = 0;
		Iterator proveedorIdIt2 = mapOrdenMediobyProveedorSaved.keySet().iterator();
		
		while (proveedorIdIt2.hasNext()) {
			String[] key = ((String) proveedorIdIt2.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);//;
			String numeroOrden = key[1];
			Map listOrdenMedio = (Map) mapOrdenMediobyProveedorSaved.get(String.valueOf(proveedorId) + "-" + numeroOrden);
			Iterator ordenesMedioMapIt = listOrdenMedio.keySet().iterator();
			
			while (ordenesMedioMapIt.hasNext()) {
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioMapIt.next();
				ProductoIf productoProveedor = mapaProducto.get(ordenMedioIf.getProductoProveedorId());
				GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
				if (contador == (position - inidicesAcumulados)) {
					// suman descuento subtotal iva total
					BigDecimal suman = ordenMedioIf.getValorSubtotal();
					BigDecimal descuento = suman.multiply(porcentajeDescuentoN.divide(new BigDecimal(100)));
					BigDecimal subTotal = suman.subtract(descuento);

					BigDecimal porcentajeBonificacion = new BigDecimal(0);
					if (ordenMedioIf.getPorcentajeBonificacionCompra() == null) {
						porcentajeBonificacion = porcentajeBonificacionCompraDefault;
					} else {
						porcentajeBonificacion = ordenMedioIf.getPorcentajeBonificacionCompra();
					}
					
					BigDecimal bonificacion = new BigDecimal(0);
					bonificacion = subTotal.multiply(porcentajeBonificacion.divide(new BigDecimal(100)));

					subTotal = subTotal.subtract(bonificacion);
					BigDecimal iva = genericoProveedor.getCobraIva().equals("S") ? subTotal.multiply(BigDecimal.valueOf(Parametros.IVA / 100D))	: BigDecimal.ZERO;
					BigDecimal total = subTotal.add(iva);

					// Si existe porcentaje de negociación se lo setea en el campo de porcentaje de descuento
					if (ordenMedioIf.getPorcentajeNegociacionComision() != null	&& ordenMedioIf.getPorcentajeNegociacionComision().compareTo(new BigDecimal(0)) == 1) {
						getTxtPorcentajeDescuentoOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(ordenMedioIf.getPorcentajeNegociacionComision())));
					}

					// Si tiene comision Pura marco el checkbox
					if (ordenMedioIf.getComisionPura() != null && ordenMedioIf.getComisionPura().equals("S")) {
						getCbTipoPagoComision().setSelected(true);
					} else {
						getCbTipoPagoComision().setSelected(false);
					}
					
					// Si tiene comision adicional marco el checkbox y seteo el porcentaje
					if (ordenMedioIf.getPorcentajeComisionAdicional() != null && ordenMedioIf.getPorcentajeComisionAdicional().compareTo(new BigDecimal(0)) == 1) {
						getCbComisionAdicional().setSelected(true);
						getTxtPorcentajeComisionAdicional().setEditable(true);
						getTxtPorcentajeComisionAdicional().setEnabled(true);
						getTxtPorcentajeComisionAdicional().setText(formatoDecimal.format(ordenMedioIf.getPorcentajeComisionAdicional()));
					} else {
						getCbComisionAdicional().setSelected(false);
						getTxtPorcentajeComisionAdicional().setEditable(false);
						getTxtPorcentajeComisionAdicional().setText(formatoDecimal.format(new BigDecimal(0)));
					}

					getTxtPorcentajeBonificacionCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeBonificacion)));
					getTxtBonificacionCompraOrden().setText(formatoDecimal.format(Utilitarios.redondeoValor(bonificacion)));

					getTxtSumanOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(suman)));
					getTxtDescuentoOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuento)));
					getTxtSubtotalOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(iva)));
					getTxtTotalOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
				}
				contador++;
			}
		}

		for (int i = 0; i < listObservacionesTempSaved.size(); i++) {
			if (i == (position - inidicesAcumulados)) {
				getTxtObservacionOrdenMedio().setText(listObservacionesTempSaved.get(i));
			}
		}

		String codigoOrden = (String) modelOrdenMedio.getValueAt(position, 7); // ADD 14 OCTUBRE
		getTextCodigoOrden().setText(codigoOrden);
	}

	// Setea los datos de la Orden de Medio seleccionada
	// de la tabla de Ordenes Medio en los textbox suman, descuento en compra,subtotal
	private void setPedidoDetalle(int position, int inidicesAcumulados) {
		
		DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMedios().getModel();
		String tipo = (String) modelOrdenMedio.getValueAt(position, 5);
		String porcentajeDescuentoTmp = (String) modelOrdenMedio.getValueAt(position, 4);
		String porcentajeDescuentoTmpArray[] = porcentajeDescuentoTmp.split("%");
		String porcentajeDescuento = porcentajeDescuentoTmpArray[0];
		BigDecimal porcentajeDescuentoN = new BigDecimal(porcentajeDescuento);
		String tipoCanje[];
		String tipoCanjePorc[];
		String tipoCanjeValorPorc;
		getTxtPorcentajeDescuentoOrdenMedio().setText(porcentajeDescuento);

		if (tipo.compareTo("NORMAL") == 0) {
			getRbTipoPagoNormal().setSelected(true);
			getTxtPorcentajeCanje().setEnabled(false);
			getTxtPorcentajeCanje().setText("");
		} else {
			getRbTipoPagoCanje().setSelected(true);
			tipoCanje = tipo.split("-");
			tipoCanjePorc = tipoCanje[1].split("%");
			tipoCanjeValorPorc = tipoCanjePorc[0];
			getTxtPorcentajeCanje().setEnabled(true);
			getTxtPorcentajeCanje().setText(tipoCanjeValorPorc);
		}

		int contador = 0;
		Iterator proveedorIdIt2 = mapOrdenMediobyProveedor.keySet().iterator();
		while (proveedorIdIt2.hasNext()) {
			String[] key = ((String) proveedorIdIt2.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			Map listOrdenMedio = (Map) mapOrdenMediobyProveedor.get(String.valueOf(proveedorId) + "-" + numeroOrden);
			Iterator ordenesMedioMapIt = listOrdenMedio.keySet().iterator();
			
			while (ordenesMedioMapIt.hasNext()) {
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioMapIt.next();
				ProductoIf productoProveedor = mapaProducto.get(ordenMedioIf.getProductoProveedorId());
				GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
				
				if (contador == (position - inidicesAcumulados)) {
					// suman descuento subtotal iva total
					BigDecimal suman = ordenMedioIf.getValorSubtotal();
					BigDecimal descuento = suman.multiply(porcentajeDescuentoN.divide(new BigDecimal(100)));
					BigDecimal subTotal = suman.subtract(descuento);

					BigDecimal porcentajeBonificacion = new BigDecimal(0);
					if (ordenMedioIf.getPorcentajeBonificacionCompra() == null) {
						porcentajeBonificacion = porcentajeBonificacionCompraDefault;
					} else {
						porcentajeBonificacion = ordenMedioIf.getPorcentajeBonificacionCompra();
					}
					
					BigDecimal bonificacion = new BigDecimal(0);
					bonificacion = subTotal.multiply(porcentajeBonificacion.divide(new BigDecimal(100)));

					subTotal = subTotal.subtract(bonificacion);
					BigDecimal iva = genericoProveedor.getCobraIva().equals("S") ? subTotal.multiply(BigDecimal.valueOf(Parametros.IVA / 100D))	: BigDecimal.ZERO;
					BigDecimal total = subTotal.add(iva);

					getTxtSumanOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(suman)));

					// Si existe porcentaje de negociación se lo setea en el
					// campo de porcentaje de descuento
					if (ordenMedioIf.getPorcentajeNegociacionComision() != null
							&& ordenMedioIf.getPorcentajeNegociacionComision().compareTo(new BigDecimal(0)) == 1) {
						getTxtPorcentajeDescuentoOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(ordenMedioIf.getPorcentajeNegociacionComision())));
					}

					// Si tiene comision Pura marco el checkbox
					if (ordenMedioIf.getComisionPura() != null && ordenMedioIf.getComisionPura().equals("S")) {
						getCbTipoPagoComision().setSelected(true);
					} else {
						getCbTipoPagoComision().setSelected(false);
					}
					
					// Si tiene comision adicional marco el checkbox y seteo el porcentaje
					if (ordenMedioIf.getPorcentajeComisionAdicional() != null && ordenMedioIf.getPorcentajeComisionAdicional().compareTo(new BigDecimal(0)) == 1) {
						getCbComisionAdicional().setSelected(true);
						getTxtPorcentajeComisionAdicional().setEditable(true);
						getTxtPorcentajeComisionAdicional().setEnabled(true);
						getTxtPorcentajeComisionAdicional().setText(formatoDecimal.format(ordenMedioIf.getPorcentajeComisionAdicional()));
					} else {
						getCbComisionAdicional().setSelected(false);
						getTxtPorcentajeComisionAdicional().setEditable(false);
						getTxtPorcentajeComisionAdicional().setText(formatoDecimal.format(new BigDecimal(0)));
					}

					getTxtPorcentajeBonificacionCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeBonificacion)));
					getTxtBonificacionCompraOrden().setText(formatoDecimal.format(Utilitarios.redondeoValor(bonificacion)));
					getTxtDescuentoOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuento)));
					getTxtSubtotalOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtIVAOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(iva)));
					getTxtTotalOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
				}
				contador++;
			}
		}

		for (int i = 0; i < listObservacionesTemp.size(); i++) {
			if (i == (position - inidicesAcumulados)) {
				getTxtObservacionOrdenMedio().setText(listObservacionesTemp.get(i));
			}
		}
		
		String codigoOrden = (String) modelOrdenMedio.getValueAt(position, 6); // ADD 14 OCTUBRE
		getTextCodigoOrden().setText(codigoOrden);
	}

	
	private final class setObservacionOrdenMedio implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			if (getTblOrdenesMedios().getSelectedRow() != -1) {
				setFilaSeleccionada(getTblOrdenesMedios().getSelectedRow());
				String strObservacionOrdenMedio = getTxtObservacionOrdenMedio()
						.getText();
				if (strObservacionOrdenMedio != null) {
					int acumulador = 0;
					boolean isIndiceSubtotal = false;
					int position = filaSeleccionada;
					for (int i = 0; i < indiceProveedorSubtotalOrdenes.size(); i++) {
						if (position == indiceProveedorSubtotalOrdenes.get(i)) {
							isIndiceSubtotal = true;
						} else if (position > indiceProveedorSubtotalOrdenes
								.get(i)) {
							acumulador++;
						}
					}
					if (!isIndiceSubtotal) {
						actualizarObservacionOrdenMedio(acumulador);
					} else {
						SpiritAlert.createAlert(
								"Por favor seleccione una Orden de Medio",
								SpiritAlert.INFORMATION);
					}

				} else {
					SpiritAlert.createAlert(
							"Debe seleccionar el detalle a actualizar !!!",
							SpiritAlert.WARNING);
				}
			}
		}
	}

	private void actualizarObservacionOrdenMedio(int indicesAcumulados) {
		DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMedios()
				.getModel();
		// String nuevoPCanjeStr = getTxtPorcentajeCanje().getText();
		// BigDecimal nuevoPCanje = new BigDecimal(nuevoPCanjeStr);
		String nuevoObservacionStr = getTxtObservacionOrdenMedio().getText();

		/*
		 * if(getRbTipoPagoNormal().isSelected()){
		 * modelOrdenMedio.setValueAt("NORMAL", filaSeleccionada, 4);
		 * nuevoPCanje = new BigDecimal(0); }else{ String porcentajeCanje =
		 * getTxtPorcentajeCanje().getText();
		 * modelOrdenMedio.setValueAt("CANJE-"+porcentajeCanje+"%",
		 * filaSeleccionada, 4); nuevoPCanje = new BigDecimal(porcentajeCanje);
		 * }
		 */// 30 MAYO

		int contador = 0;
		// Iterator proveedorIdIt =
		// mapOrdenMediobyProveedorPCanje.keySet().iterator(); // 30 MAYO
		Iterator proveedorIdIt = mapOrdenMediobyProveedorObservacion.keySet().iterator();
		while (proveedorIdIt.hasNext()) {
			String[] key = ((String) proveedorIdIt.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			// ArrayList<BigDecimal> listPorcentajeCanje =
			// (ArrayList<BigDecimal>)mapOrdenMediobyProveedorPCanje.get(proveedorId);
			ArrayList<String> listObservaciones = (ArrayList<String>) mapOrdenMediobyProveedorObservacion.get(String.valueOf(proveedorId) + "-" + numeroOrden);
			// for(int i = 0; i<listPorcentajeCanje.size();i++){
			for (int i = 0; i < listObservaciones.size(); i++) {
				// MODIFIED 9 MAYO
				if (contador == (filaSeleccionada - indicesAcumulados)) {
					// if(contador==filaSeleccionada){
					// listPorcentajeCanje.set(i, nuevoPCanje);
					listObservaciones.set(i, nuevoObservacionStr);
				}
				contador++;
			}
			// mapOrdenMediobyProveedorPCanje.put(proveedorId,
			// listPorcentajeCanje);
			mapOrdenMediobyProveedorObservacion.put(String.valueOf(proveedorId)
					+ "-" + numeroOrden, listObservaciones);
		}

		// ADD 31 MAYO
		for (int i = 0; i < listObservacionesTemp.size(); i++) {
			if (i == (filaSeleccionada - indicesAcumulados)) {
				// ADD 30 MAYO
				listObservacionesTemp.add(i, nuevoObservacionStr);
				listObservacionesTemp.remove(i + 1);
				getTxtObservacionOrdenMedio().setText(nuevoObservacionStr);
				// END 30 MAYO
			}
		}
		// END 31 MAYO
	}

	// END 26 MAYO

	// ADD 30 MAYO
	private final class setObservacionOrdenMedioxProv implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			if (getTblOrdenesMedios().getSelectedRow() != -1) {

				setFilaSeleccionada(getTblOrdenesMedios().getSelectedRow());
				String strObservacion = getTxtObservacionOrdenMedio().getText();

				if (strObservacion != null) {

					int acumulador = 0;
					boolean isIndiceSubtotal = false;
					int position = filaSeleccionada;

					for (int i = 0; i < indiceProveedorSubtotalOrdenes.size(); i++) {
						if (position == indiceProveedorSubtotalOrdenes.get(i)) {
							isIndiceSubtotal = true;
						} else if (position > indiceProveedorSubtotalOrdenes
								.get(i)) {
							acumulador++;
						}
					}

					if (!isIndiceSubtotal) {
						actualizarObservacionOrdenMedioxCanal(acumulador);
					} else {
						SpiritAlert.createAlert(
								"Por favor seleccione una Orden de Medio",
								SpiritAlert.INFORMATION);
					}
				}
			} else {
				SpiritAlert.createAlert(
						"Debe seleccionar el detalle a actualizar !!!",
						SpiritAlert.WARNING);
			}
		}
	}

	private void actualizarObservacionOrdenMedioxCanal(int acumulador) {
		// String tipoPago = "";
		// BigDecimal nuevoPCanje = new BigDecimal(0);
		/*
		 * if(getRbTipoPagoNormal().isSelected()){ tipoPago = "NORMAL";
		 * nuevoPCanje = new BigDecimal(0); }else{ String porcentajeCanje =
		 * getTxtPorcentajeCanje().getText(); tipoPago =
		 * "CANJE-"+porcentajeCanje+"%"; nuevoPCanje = new
		 * BigDecimal(porcentajeCanje); }
		 */// 30 MAYO
		String nuevaObservacion = getTxtObservacionOrdenMedio().getText();
		DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMedios().getModel();
		String canal = (String) modelOrdenMedio.getValueAt(filaSeleccionada, 1);
		Long proveedorSeleccionado = new Long(0);
		Iterator proveedorIt = listaProveedoresMap.keySet().iterator();
		while (proveedorIt.hasNext()) {
			String[] key = ((String) proveedorIt.next()).split("-");
			Long proveedor = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ClienteOficinaIf cliente = (ClienteOficinaIf) listaProveedoresMap.get(String.valueOf(proveedor) + "-" + numeroOrden);
			if (cliente.getDescripcion().compareTo(canal) == 0) {
				proveedorSeleccionado = proveedor;
			}
		}

		int contador = 0;// para indicar los indices de un proveedor;
		// ADD 31 MAYO PARA VER EN QUE INDICE COMIENZA y termina EL PROVEEDOR DE
		// LAS NUEVAS OBSERVACIONES
		ArrayList<Integer> indicesOrdenesProveedor = new ArrayList<Integer>();
		// int acumuladorIndicesXProveedor = 0;
		// END 31 MAYO
		Iterator proveedorIdIt = mapOrdenMediobyProveedorObservacion.keySet().iterator();
		while (proveedorIdIt.hasNext()) {
			String[] key = ((String) proveedorIdIt.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<String> listObservacion = (ArrayList<String>) mapOrdenMediobyProveedorObservacion.get(String.valueOf(proveedorId) + "-" + numeroOrden);
			for (int i = 0; i < listObservacion.size(); i++) {
				if (proveedorSeleccionado.compareTo(proveedorId) == 0) {
					listObservacion.set(i, nuevaObservacion);

					// ADD 31 MAYO
					indicesOrdenesProveedor.add(contador);
					// END 31 MAYO

					// MODIFIED 11 MAYO
					// modelOrdenMedio.setValueAt(tipoPago, contador +
					// acumulador, 4);//30 MAYO
					// modelOrdenMedio.setValueAt(tipoPago, contador, 4);
					// 9 MAYO POSIBLE ERROR!! REVISAR
					// GIOMYYYYYYYYYYYYYYYYYYY!!!!!!!!!!!!
					// sadasdasDASD
					// asDasDasdfasfas

				}
				contador++;
			}// 30 MAYO
			mapOrdenMediobyProveedorObservacion.put(String.valueOf(proveedorId) + "-" + numeroOrden, listObservacion);
		}

		// ADD 31 MAYO
		for (int i = 0; i < listObservacionesTemp.size(); i++) {
			for (int j = 0; j < indicesOrdenesProveedor.size(); j++) {
				if (i == indicesOrdenesProveedor.get(j)) {
					// ADD 30 MAYO
					listObservacionesTemp.add(i, nuevaObservacion);
					listObservacionesTemp.remove(i + 1);
					getTxtObservacionOrdenMedio().setText(nuevaObservacion);
					// END 30 MAYO
				}
			}
		}
		// END 31 MAYO

	}

	private final class setObservacionOrdenMedioTotal implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			if (getTblOrdenesMedios().getSelectedRow() != -1) {
				setFilaSeleccionada(getTblOrdenesMedios().getSelectedRow());
				// actualizarTipoPagoOrdenMedioTotal();
				// String strPorcentajeCanje =
				// getTxtPorcentajeCanje().getText();
				String strObservacion = getTxtObservacionOrdenMedio().getText();
				// MODIFIED 8 JULIO
				// if ((!("".equals(strObservacion)) && (strObservacion !=
				// null))) {
				if (strObservacion != null) {
					/*
					 * double porcentajeCanje = 0D; porcentajeCanje =
					 * Double.parseDouble
					 * (Utilitarios.removeDecimalFormat(getTxtPorcentajeCanje
					 * ().getText())); if (porcentajeCanje > porcentajeLimite){
					 * SpiritAlert.createAlert(
					 * "El Porcentaje de Canje debe ser menor igual al 100%",
					 * SpiritAlert.WARNING);
					 * getJtpPlanMedio().setSelectedIndex(3);
					 * getTpMapasPauta().setSelectedIndex(2);
					 * getTxtPorcentajeCanje().grabFocus(); }else{
					 */// 30 MAYO
					// 9 MAYO
					int acumulador = 0;
					boolean isIndiceSubtotal = false;
					int position = filaSeleccionada;
					for (int i = 0; i < indiceProveedorSubtotalOrdenes.size(); i++) {
						if (position == indiceProveedorSubtotalOrdenes.get(i)) {
							isIndiceSubtotal = true;
						} else if (position > indiceProveedorSubtotalOrdenes
								.get(i)) {
							acumulador++;
						}
					}
					if (!isIndiceSubtotal) {
						// System.out.println("POSITION Y ACUMULADOR: "
						// +position +acumulador);
						actualizarObservacionOrdenMedioTotal(acumulador);
					}// EN 9 MAYO
						// MODIFIED 9 MAYO
						// actualizarTipoPagoOrdenMedioTotal();
					else {// 11 MAYO //cuando han seleccionado un Subtotal
						SpiritAlert.createAlert(
								"Por favor seleccione una Orden de Medio",
								SpiritAlert.INFORMATION);
					}// END 11 MAYO
						// }30 MAYO
				}
			} else {
				SpiritAlert.createAlert(
						"Debe seleccionar el detalle a actualizar !!!",
						SpiritAlert.WARNING);
			}
		}
	}

	private void actualizarObservacionOrdenMedioTotal(int acumulador) {
		DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMedios().getModel();
		String nuevoObservacion = getTxtObservacionOrdenMedio().getText();
		int contador = 0;
		Iterator proveedorIdIt = mapOrdenMediobyProveedorObservacion.keySet().iterator();
		while (proveedorIdIt.hasNext()) {
			String[] key = ((String) proveedorIdIt.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<String> listObservacion = (ArrayList<String>) mapOrdenMediobyProveedorObservacion.get(String.valueOf(proveedorId) + "-" + numeroOrden);

			for (int i = 0; i < listObservacion.size(); i++) {
				listObservacion.set(i, nuevoObservacion);
			}
			mapOrdenMediobyProveedorObservacion.put(String.valueOf(proveedorId) + "-" + numeroOrden, listObservacion);
		}

		for (int i = 0; i < listObservacionesTemp.size(); i++) {
			listObservacionesTemp.add(i, nuevoObservacion);
			listObservacionesTemp.remove(i + 1);
			getTxtObservacionOrdenMedio().setText(nuevoObservacion);
		}
	}

	private final class SetPDescuentoOrdenMedio implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			if (getTblOrdenesMedios().getSelectedRow() != -1) {
				setFilaSeleccionada(getTblOrdenesMedios().getSelectedRow());
				String strPorcentajeDescuentoOrdenMedio = getTxtPorcentajeDescuentoOrdenMedio().getText();
				String strPorcentajeBonificacionCompra = getTxtPorcentajeBonificacionCompra().getText();

				if (!("".equals(strPorcentajeDescuentoOrdenMedio))
						&& (strPorcentajeDescuentoOrdenMedio != null)
						&& !("".equals(strPorcentajeBonificacionCompra))
						&& (strPorcentajeBonificacionCompra != null)) {

					double porcentajeDescuentoOrdenMedio = 0D;
					porcentajeDescuentoOrdenMedio = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoOrdenMedio().getText()));
					double porcentajeBonificacionCompra = 0D;
					porcentajeBonificacionCompra = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeBonificacionCompra().getText()));

					if (porcentajeDescuentoOrdenMedio > porcentajeLimite) {
						SpiritAlert.createAlert("El Porcentaje de Descuento de las Ordenes de Medio \n debe ser menor o igual al 100%", SpiritAlert.WARNING);
						getJtpPlanMedio().setSelectedIndex(3);
						getTpMapasPauta().setSelectedIndex(2);
						getTxtPorcentajeDescuentoOrdenMedio().grabFocus();
					} else if (porcentajeBonificacionCompra > porcentajeLimite) {
						SpiritAlert.createAlert("El Porcentaje de Bonificación \n debe ser menor o igual al 100%", SpiritAlert.WARNING);
						getJtpPlanMedio().setSelectedIndex(3);
						getTpMapasPauta().setSelectedIndex(2);
						getTxtPorcentajeBonificacionCompra().grabFocus();
					} else {
						int acumulador = 0;
						boolean isIndiceSubtotal = false;
						int position = filaSeleccionada;
						for (int i = 0; i < indiceProveedorSubtotalOrdenes.size(); i++) {
							if (position == indiceProveedorSubtotalOrdenes.get(i)) {
								isIndiceSubtotal = true;
							} else if (position > indiceProveedorSubtotalOrdenes.get(i)) {
								acumulador++;
							}
						}
						if (!isIndiceSubtotal) {
							actualizarDescuentoOrdenMedio(false, acumulador, false);
						} else {
							SpiritAlert.createAlert("Por favor seleccione una Orden de Medio",	SpiritAlert.INFORMATION);
						}
					}
				} else {
					SpiritAlert.createAlert("Debe ingresar el porcentaje de descuento y el de bonificación.", SpiritAlert.WARNING);
				}

			} else {
				SpiritAlert.createAlert("Debe seleccionar el detalle a actualizar.", SpiritAlert.WARNING);
			}
		}
	}

	private void actualizarDescuentoOrdenMedio(boolean calcularDescuento, int indicesAcumulados, boolean existeNegociacionComision) {
		setearGenericoPautaRegular();
		String nuevoDescuentoStr = getTxtPorcentajeDescuentoOrdenMedio().getText();
		String nuevaBonificacionStr = getTxtPorcentajeBonificacionCompra().getText();
		
		String nuevaComisionAdicional = getTxtPorcentajeComisionAdicional().getText();
		if(nuevaComisionAdicional == null || nuevaComisionAdicional.equals("")){
			nuevaComisionAdicional = "0";
		}
		
		// Si existe Negociacion o Comision entonces no se debe tomar en cuenta
		// el porcentaje de descuento para los calculos
		// pero si se debe guardar el porcentaje para calcular la negociaciones
		// y comisiones, usamos nueva variable.
		BigDecimal porcentajeNegociacionComision = new BigDecimal(0);
		BigDecimal porcentajeComisionAdicional = new BigDecimal(0);
		if (existeNegociacionComision) {
			porcentajeComisionAdicional = new BigDecimal(nuevaComisionAdicional);
			if(nuevaComisionAdicional.equals("0") || getRbTipoPagoCanje().isSelected() || getCbTipoPagoComision().isSelected()){
				porcentajeNegociacionComision = new BigDecimal(nuevoDescuentoStr);
				nuevoDescuentoStr = "0";
				nuevaBonificacionStr = "0";
			}			
		} else {
			porcentajeNegociacionComision = new BigDecimal(0);
			porcentajeComisionAdicional = new BigDecimal(0);
		}
		
		// para la comision pura
		String existeComisionPura = getCbTipoPagoComision().isSelected()?"S":"N";
		BigDecimal nuevoDescuento = new BigDecimal(nuevoDescuentoStr);
		BigDecimal nuevaBonificacion = new BigDecimal(nuevaBonificacionStr);
		boolean calcularTotales = true;
		int contador = 0;
		
		Iterator proveedorIdIt = mapOrdenMediobyProveedorPDesc.keySet().iterator();
		while (proveedorIdIt.hasNext()) {
			String[] key = ((String) proveedorIdIt.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<String> listPorcentajesxOrdenMedio = (ArrayList<String>) mapOrdenMediobyProveedorPDesc.get(String.valueOf(proveedorId) + "-" + numeroOrden);
			for (int i = 0; i < listPorcentajesxOrdenMedio.size(); i++) {
				if (contador == (filaSeleccionada - indicesAcumulados)) {
					if (!existeNegociacionComision && nuevaBonificacion.compareTo(new BigDecimal(0)) == 0 && listPorcentajesxOrdenMedio.get(i).compareTo(nuevoDescuentoStr) == 0) {
						calcularTotales = true;
					} else
						listPorcentajesxOrdenMedio.set(i, String.valueOf(nuevoDescuentoStr));
				}
				contador++;
			}
			if (calcularTotales) {
				mapOrdenMediobyProveedorPDesc.put(String.valueOf(proveedorId) + "-" + numeroOrden, listPorcentajesxOrdenMedio);
			}
		}
		
		contador = 0;
		
		Iterator proveedorIdItBono = mapOrdenMediobyProveedorPBono.keySet().iterator();
		while (proveedorIdItBono.hasNext()) {
			String[] key = ((String) proveedorIdItBono.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<String> listPorcentajesxOrdenMedio = (ArrayList<String>) mapOrdenMediobyProveedorPBono.get(String.valueOf(proveedorId) + "-" + numeroOrden);
			for (int i = 0; i < listPorcentajesxOrdenMedio.size(); i++) {
				if (contador == (filaSeleccionada - indicesAcumulados)) {
					if (!existeNegociacionComision && nuevaBonificacion.compareTo(new BigDecimal(0)) == 0 && listPorcentajesxOrdenMedio.get(i).compareTo(nuevaBonificacionStr) == 0) {
						calcularTotales = true;
					} else
						listPorcentajesxOrdenMedio.set(i, String.valueOf(nuevaBonificacionStr));
				}
				contador++;
			}
			if (calcularTotales) {
				mapOrdenMediobyProveedorPBono.put(String.valueOf(proveedorId) + "-" + numeroOrden, listPorcentajesxOrdenMedio);
			}
		}

		if (calcularTotales) {
			// cambiar el porcentaje en la tabla
			DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMedios().getModel();
			modelOrdenMedio.setValueAt(nuevoDescuentoStr + "%", filaSeleccionada, 4);
			Long campanaProductoVersionId;
			Long productoClienteId;
			contador = 0;
			Iterator proveedorIdIt2 = mapOrdenMediobyProveedor.keySet().iterator();
			while (proveedorIdIt2.hasNext()) {
				String[] key = ((String) proveedorIdIt2.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				Map listOrdenMedio = (Map) mapOrdenMediobyProveedor.get(String.valueOf(proveedorId) + "-" + numeroOrden);
				Iterator ordenesMedioMapIt = listOrdenMedio.keySet().iterator();
				while (ordenesMedioMapIt.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioMapIt.next();
					
					if (contador == (filaSeleccionada - indicesAcumulados)) {
						Map<Long, ProductoIf> mapaProveedorProducto = mapaGenericoMapaProveedorProducto.get(genericoPautaRegular.getId());
						ProductoIf productoProveedor = mapaProveedorProducto.get(ordenMedioIf.getProveedorId());
													
						GenericoIf genericoProveedor = genericoPautaRegular;
						BigDecimal suman = ordenMedioIf.getValorSubtotal();
						BigDecimal descuento = suman.multiply(nuevoDescuento.divide(new BigDecimal(100)));
						BigDecimal subTotal = suman.subtract(descuento);

						BigDecimal bonificacion = subTotal.multiply(nuevaBonificacion.divide(new BigDecimal(100)));
						subTotal = subTotal.subtract(bonificacion);

						BigDecimal iva = genericoProveedor.getCobraIva().equals("S") ? subTotal.multiply(BigDecimal.valueOf(Parametros.IVA / 100D)): BigDecimal.ZERO;
						BigDecimal total = subTotal.add(iva);
						getTxtSumanOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(suman)));
						getTxtDescuentoOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuento)));
						getTxtBonificacionCompraOrden().setText(formatoDecimal.format(Utilitarios.redondeoValor(bonificacion)));
						getTxtSubtotalOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
						getTxtIVAOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(iva)));
						getTxtTotalOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));

						// Cambiar porcentaje en PlanMedioDetalles
						Map ordenMedioDetalleMap = (Map) listOrdenMedio.get(ordenMedioIf);
						Iterator ordenMedioDetalleIt = ordenMedioDetalleMap.keySet().iterator();
						if (ordenMedioDetalleIt.hasNext()) {// solo una vez
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt.next();
							campanaProductoVersionId = ordenMedioDetalleIf.getCampanaProductoVersionId();
							productoClienteId = ordenMedioDetalleIf.getProductoClienteId();
							ordenMedioIf.setPorcentajeNegociacionComision(porcentajeNegociacionComision);
							ordenMedioIf.setPorcentajeComisionAdicional(porcentajeComisionAdicional);
							ordenMedioIf.setComisionPura(existeComisionPura);

							// seteo la bonificacion, esto sirver para que
							// en setPedidoDetalle se pueda setear el
							// porcentaje de bonificacion
							ordenMedioIf.setPorcentajeBonificacionCompra(nuevaBonificacion);
							ordenMedioIf.setValorIva(iva);
							ordenMedioIf.setValorTotal(total);
							ordenMedioIf.setProductoProveedorId(productoProveedor.getId());

							// Si la ordenes estan agrupadas Por Medio
							if (planMedioIf.getOrdenMedioTipo().equals("C")) {
								if (existeNegociacionComision) {
									actualizarDescuentoPlanMedioDetallexOrden(
											proveedorId,
											Integer.parseInt(numeroOrden),
											porcentajeNegociacionComision,
											nuevaBonificacion,
											existeNegociacionComision,
											productoProveedor);
								} else {
									actualizarDescuentoPlanMedioDetallexOrden(
											proveedorId, 
											Integer.parseInt(numeroOrden),
											nuevoDescuento,
											nuevaBonificacion,
											existeNegociacionComision,
											productoProveedor);
								}
							}
							// Si la ordenes estan agrupadas Por Producto Comercial
							else if (planMedioIf.getOrdenMedioTipo().equals("P")) {
								if (existeNegociacionComision) {
									actualizarDescuentoPlanMedioDetallexProducto(
											proveedorId,
											Integer.parseInt(numeroOrden),
											productoClienteId,
											porcentajeNegociacionComision,
											nuevaBonificacion,
											existeNegociacionComision,
											productoProveedor);
								} else {
									actualizarDescuentoPlanMedioDetallexProducto(
											proveedorId, 
											Integer.parseInt(numeroOrden),
											productoClienteId,
											nuevoDescuento,
											nuevaBonificacion,
											existeNegociacionComision,
											productoProveedor);
								}
							}
							// Si la ordenes estan agrupadas Por Comercial
							else {
								if (existeNegociacionComision) {
									actualizarDescuentoPlanMedioDetallexComercial(
											proveedorId,
											Integer.parseInt(numeroOrden),
											campanaProductoVersionId,
											porcentajeNegociacionComision,
											nuevaBonificacion,
											existeNegociacionComision,
											productoProveedor);
								} else {
									actualizarDescuentoPlanMedioDetallexComercial(
											proveedorId,
											Integer.parseInt(numeroOrden),
											campanaProductoVersionId,
											nuevoDescuento,
											nuevaBonificacion,
											existeNegociacionComision,
											productoProveedor);
								}
							}
							
							BigDecimal sumanDetalle = ordenMedioDetalleIf.getValorSubtotal();
							BigDecimal descuentoDetalle = sumanDetalle.multiply(nuevoDescuento.divide(new BigDecimal(100)));
							BigDecimal subTotalDetalle = sumanDetalle.subtract(descuentoDetalle);

							BigDecimal bonificacionDetalle = subTotalDetalle.multiply(nuevaBonificacion.divide(new BigDecimal(100)));
							subTotalDetalle = subTotalDetalle.subtract(bonificacionDetalle);

							BigDecimal ivaDetalle = genericoProveedor.getCobraIva().equals("S") ? subTotalDetalle.multiply(BigDecimal.valueOf(Parametros.IVA / 100D)) : BigDecimal.ZERO;
							ordenMedioDetalleIf.setValorIva(ivaDetalle);
							BigDecimal totalDetalle = subTotalDetalle.add(ivaDetalle);
							ordenMedioDetalleIf.setValorTotal(totalDetalle);
						}
					}
					contador++;
				}
			}
		}
	}

	private final class SetPDescuentoOrdenMedioxProv implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			if (getTblOrdenesMedios().getSelectedRow() != -1) {

				setFilaSeleccionada(getTblOrdenesMedios().getSelectedRow());
				String strPorcentajeDescuentoOrdenMedio = getTxtPorcentajeDescuentoOrdenMedio()
						.getText();
				String strPorcentajeBonificacionCompra = getTxtPorcentajeBonificacionCompra()
						.getText();

				if (!("".equals(strPorcentajeDescuentoOrdenMedio))
						&& (strPorcentajeDescuentoOrdenMedio != null)
						&& !("".equals(strPorcentajeBonificacionCompra))
						&& (strPorcentajeBonificacionCompra != null)) {

					double porcentajeDescuentoOrdenMedio = 0D;
					porcentajeDescuentoOrdenMedio = Double
							.parseDouble(Utilitarios
									.removeDecimalFormat(getTxtPorcentajeDescuentoOrdenMedio()
											.getText()));
					double porcentajeBonificacionCompra = 0D;
					porcentajeBonificacionCompra = Double
							.parseDouble(Utilitarios
									.removeDecimalFormat(getTxtPorcentajeBonificacionCompra()
											.getText()));

					if (porcentajeDescuentoOrdenMedio > porcentajeLimite) {
						SpiritAlert
								.createAlert(
										"El Porcentaje de Descuento de las Ordenes de Medio \n debe ser menor igual al 100%",
										SpiritAlert.WARNING);
						getJtpPlanMedio().setSelectedIndex(3);
						getTpMapasPauta().setSelectedIndex(2);
						getTxtPorcentajeDescuentoOrdenMedio().grabFocus();
					} else if (porcentajeBonificacionCompra > porcentajeLimite) {
						SpiritAlert
								.createAlert(
										"El Porcentaje de Bonificación \n debe ser menor o igual al 100%",
										SpiritAlert.WARNING);
						getJtpPlanMedio().setSelectedIndex(3);
						getTpMapasPauta().setSelectedIndex(2);
						getTxtPorcentajeBonificacionCompra().grabFocus();
					} else {
						int acumulador = 0;
						boolean isIndiceSubtotal = false;
						int position = filaSeleccionada;
						for (int i = 0; i < indiceProveedorSubtotalOrdenes
								.size(); i++) {
							if (position == indiceProveedorSubtotalOrdenes
									.get(i)) {
								isIndiceSubtotal = true;
							} else if (position > indiceProveedorSubtotalOrdenes
									.get(i)) {
								acumulador++;
							}
						}
						if (!isIndiceSubtotal) {
							actualizarDescuentoOrdenMedioxCanal(acumulador,
									false);
						} else {
							SpiritAlert.createAlert(
									"Por favor seleccione una Orden de Medio",
									SpiritAlert.INFORMATION);
						}
					}
				}
			} else {
				SpiritAlert.createAlert(
						"Debe seleccionar el detalle a actualizar !!!",
						SpiritAlert.WARNING);
			}
		}
	}

	private void actualizarDescuentoOrdenMedioxCanal(Integer indicesAcumulados, boolean existeNegociacionComision) {
		setearGenericoPautaRegular();
		DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMedios().getModel();
		String canal = (String) modelOrdenMedio.getValueAt(filaSeleccionada, 1);
		Long proveedorSeleccionado = new Long(0);
		Iterator proveedorIt = listaProveedoresMap.keySet().iterator();
		while (proveedorIt.hasNext()) {
			String[] key = ((String) proveedorIt.next()).split("-");
			Long proveedor = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ClienteOficinaIf cliente = (ClienteOficinaIf) listaProveedoresMap.get(String.valueOf(proveedor) + "-" + numeroOrden);
			if (cliente.getDescripcion().compareTo(canal) == 0) {
				proveedorSeleccionado = proveedor;
			}
		}

		String nuevoDescuentoStr = getTxtPorcentajeDescuentoOrdenMedio().getText();
		String nuevaBonificacionStr = getTxtPorcentajeBonificacionCompra().getText();
		
		String nuevaComisionAdicional = getTxtPorcentajeComisionAdicional().getText();
		if(nuevaComisionAdicional == null || nuevaComisionAdicional.equals("")){
			nuevaComisionAdicional = "0";
		}

		// para la comision pura
		String existeComisionPura = getCbTipoPagoComision().isSelected() ? "S": "N";
		
		// Si existe Negociacion o Comision entonces no se debe tomar en cuenta
		// el porcentaje de descuento para los calculos
		// pero si se debe guardar el porcentaje para calcular la negociaciones
		// y comisiones, usamos nueva variable.
		BigDecimal porcentajeNegociacionComision = new BigDecimal(0);
		BigDecimal porcentajeComisionAdicional = new BigDecimal(0);
		
		if (existeNegociacionComision) {
			porcentajeComisionAdicional = new BigDecimal(nuevaComisionAdicional);
			if(nuevaComisionAdicional.equals("0")  || getRbTipoPagoCanje().isSelected() || getCbTipoPagoComision().isSelected()){
				porcentajeNegociacionComision = new BigDecimal(nuevoDescuentoStr);
				nuevoDescuentoStr = "0";
				nuevaBonificacionStr = "0";
			}	
		} else {
			porcentajeNegociacionComision = new BigDecimal(0);
			porcentajeComisionAdicional = new BigDecimal(0);
		}
		
		BigDecimal nuevoDescuento = new BigDecimal(nuevoDescuentoStr);
		BigDecimal nuevaBonificacion = new BigDecimal(nuevaBonificacionStr);

		boolean calcularTotales = true;
		int contador = 0;
		Iterator proveedorIdIt = mapOrdenMediobyProveedorPDesc.keySet().iterator();
		while (proveedorIdIt.hasNext()) {
			String[] key = ((String) proveedorIdIt.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<String> listPorcentajesxOrdenMedio = (ArrayList<String>) mapOrdenMediobyProveedorPDesc.get(String.valueOf(proveedorId) + "-" + numeroOrden);
			for (int i = 0; i < listPorcentajesxOrdenMedio.size(); i++) {
				if (proveedorSeleccionado.compareTo(proveedorId) == 0) {
					if (indicesAcumulados != null && (contador == (filaSeleccionada - indicesAcumulados))) {
						if (!existeNegociacionComision && nuevaBonificacion.compareTo(new BigDecimal(0)) == 0 && listPorcentajesxOrdenMedio.get(i).compareTo(nuevoDescuentoStr) == 0) {
							calcularTotales = true;
						}
					}
					listPorcentajesxOrdenMedio.set(i, String.valueOf(nuevoDescuentoStr));
					modelOrdenMedio.setValueAt(nuevoDescuentoStr + "%", contador + indicesAcumulados, 4);
				}
				contador++;
			}
			if (calcularTotales) {
				mapOrdenMediobyProveedorPDesc.put(String.valueOf(proveedorId) + "-" + numeroOrden, listPorcentajesxOrdenMedio);
			}			
		}

		contador = 0;
		Iterator proveedorIdItBono = mapOrdenMediobyProveedorPBono.keySet().iterator();
		while (proveedorIdItBono.hasNext()) {
			String[] key = ((String) proveedorIdItBono.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<String> listPorcentajesxOrdenMedio = (ArrayList<String>) mapOrdenMediobyProveedorPBono.get(String.valueOf(proveedorId) + "-" + numeroOrden);
			for (int i = 0; i < listPorcentajesxOrdenMedio.size(); i++) {
				if (proveedorSeleccionado.compareTo(proveedorId) == 0) {
					if (indicesAcumulados != null && (contador == (filaSeleccionada - indicesAcumulados))) {
						if (!existeNegociacionComision && nuevaBonificacion.compareTo(new BigDecimal(0)) == 0 && listPorcentajesxOrdenMedio.get(i).compareTo(nuevaBonificacionStr) == 0) {
							calcularTotales = true;
						}
					}

					listPorcentajesxOrdenMedio.set(i, String.valueOf(nuevaBonificacionStr));
				}
				contador++;
			}
			if (calcularTotales) {
				mapOrdenMediobyProveedorPBono.put(String.valueOf(proveedorId) + "-" + numeroOrden, listPorcentajesxOrdenMedio);
			}
		}

		if (calcularTotales) {
			Long comercialId;
			Iterator proveedorIdIt2 = mapOrdenMediobyProveedor.keySet().iterator();
			while (proveedorIdIt2.hasNext()) {
				String[] key = ((String) proveedorIdIt2.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				Map listOrdenMedio = (Map) mapOrdenMediobyProveedor.get(String.valueOf(proveedorId) + "-" + numeroOrden);
				Iterator ordenesMedioMapIt = listOrdenMedio.keySet().iterator();
				while (ordenesMedioMapIt.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioMapIt.next();
					if (proveedorSeleccionado.compareTo(proveedorId) == 0) {
						Map<Long, ProductoIf> mapaProveedorProducto = mapaGenericoMapaProveedorProducto.get(genericoPautaRegular.getId());
						ProductoIf productoProveedor = mapaProveedorProducto.get(ordenMedioIf.getProveedorId());
						
						GenericoIf genericoProveedor = genericoPautaRegular;
						ordenMedioIf.setProductoProveedorId(productoProveedor.getId());

						BigDecimal suman = ordenMedioIf.getValorSubtotal();
						BigDecimal descuento = suman.multiply(nuevoDescuento.divide(new BigDecimal(100)));
						BigDecimal subTotal = suman.subtract(descuento);

						BigDecimal bonificacion = subTotal.multiply(nuevaBonificacion.divide(new BigDecimal(100)));
						subTotal = subTotal.subtract(bonificacion);

						BigDecimal iva = genericoProveedor.getCobraIva().equals("S") ? subTotal.multiply(BigDecimal.valueOf(Parametros.IVA / 100D)): BigDecimal.ZERO;
						BigDecimal total = subTotal.add(iva);
						ordenMedioIf.setValorIva(iva);
						ordenMedioIf.setValorTotal(total);

						getTxtSumanOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(suman)));
						getTxtDescuentoOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuento)));
						getTxtSubtotalOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
						getTxtBonificacionCompraOrden().setText(formatoDecimal.format(Utilitarios.redondeoValor(bonificacion)));
						getTxtIVAOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(iva)));
						getTxtTotalOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));

						// Cambiar porcentaje en PlanMedioDetalles
						Map ordenMedioDetalleMap = (Map) listOrdenMedio.get(ordenMedioIf);
						Iterator ordenMedioDetalleIt = ordenMedioDetalleMap.keySet().iterator();

						if (ordenMedioDetalleIt.hasNext()) {// solo una vez
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt.next();
							comercialId = ordenMedioDetalleIf.getComercialId();

							if (existeNegociacionComision) {
								actualizarDescuentoPlanMedioDetallexCanal(
										proveedorId,
										porcentajeNegociacionComision,
										nuevaBonificacion,
										existeNegociacionComision,
										productoProveedor);
							} else {
								actualizarDescuentoPlanMedioDetallexCanal(
										proveedorId,
										nuevoDescuento,
										nuevaBonificacion,
										existeNegociacionComision,
										productoProveedor);
							}
							
							BigDecimal sumanDetalle = ordenMedioDetalleIf.getValorSubtotal();
							BigDecimal descuentoDetalle = sumanDetalle.multiply(nuevoDescuento.divide(new BigDecimal(100)));
							BigDecimal subTotalDetalle = sumanDetalle.subtract(descuentoDetalle);

							BigDecimal bonificacionDetalle = subTotalDetalle.multiply(nuevaBonificacion.divide(new BigDecimal(100)));
							subTotalDetalle = subTotalDetalle.subtract(bonificacionDetalle);

							BigDecimal ivaDetalle = genericoProveedor.getCobraIva().equals("S") ? subTotalDetalle.multiply(BigDecimal.valueOf(Parametros.IVA / 100D)) : BigDecimal.ZERO;
							ordenMedioDetalleIf.setValorIva(ivaDetalle);
							BigDecimal totalDetalle = subTotalDetalle.add(ivaDetalle);
							ordenMedioDetalleIf.setValorTotal(totalDetalle);
						}
						// seteo la bonificacion, esto sirver para que en
						// setPedidoDetalle se pueda setear el porcentaje de
						// bonificacion
						ordenMedioIf.setPorcentajeBonificacionCompra(nuevaBonificacion);
						ordenMedioIf.setPorcentajeNegociacionComision(porcentajeNegociacionComision);
						ordenMedioIf.setPorcentajeComisionAdicional(porcentajeComisionAdicional);
						ordenMedioIf.setComisionPura(existeComisionPura);
					}
				}
			}
		}
	}

	private final class SetPDescuentoOrdenMedioTotal implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			if (getTblOrdenesMedios().getSelectedRow() != -1) {
				setFilaSeleccionada(getTblOrdenesMedios().getSelectedRow());

				String strPorcentajeDescuentoOrdenMedio = getTxtPorcentajeDescuentoOrdenMedio().getText();
				String strPorcentajeBonificacionCompra = getTxtPorcentajeBonificacionCompra().getText();

				if (!("".equals(strPorcentajeDescuentoOrdenMedio))
						&& (strPorcentajeDescuentoOrdenMedio != null)
						&& !("".equals(strPorcentajeBonificacionCompra))
						&& (strPorcentajeBonificacionCompra != null)) {

					double porcentajeDescuentoOrdenMedio = 0D;
					porcentajeDescuentoOrdenMedio = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoOrdenMedio().getText()));
					double porcentajeBonificacionCompra = 0D;
					porcentajeBonificacionCompra = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeBonificacionCompra().getText()));

					if (porcentajeDescuentoOrdenMedio > porcentajeLimite) {
						SpiritAlert.createAlert("El Porcentaje de Descuento de las Ordenes de Medio \n debe ser menor igual al 100%",SpiritAlert.WARNING);
						getJtpPlanMedio().setSelectedIndex(3);
						getTpMapasPauta().setSelectedIndex(2);
						getTxtPorcentajeDescuentoOrdenMedio().grabFocus();
					} else if (porcentajeBonificacionCompra > porcentajeLimite) {
						SpiritAlert.createAlert("El Porcentaje de Bonificación \n debe ser menor o igual al 100%",SpiritAlert.WARNING);
						getJtpPlanMedio().setSelectedIndex(3);
						getTpMapasPauta().setSelectedIndex(2);
						getTxtPorcentajeBonificacionCompra().grabFocus();
					} else {
						int acumulador = 0;
						boolean isIndiceSubtotal = false;
						int position = filaSeleccionada;
						for (int i = 0; i < indiceProveedorSubtotalOrdenes.size(); i++) {
							if (position == indiceProveedorSubtotalOrdenes.get(i)) {
								isIndiceSubtotal = true;
							} else if (position > indiceProveedorSubtotalOrdenes.get(i)) {
								acumulador++;
							}
						}
						if (!isIndiceSubtotal) {
							actualizarDescuentoOrdenMedioTotal(false,acumulador, false);
						} else {
							SpiritAlert.createAlert("Por favor seleccione una Orden de Medio",SpiritAlert.INFORMATION);
						}
					}
				}
			} else {
				SpiritAlert.createAlert("Debe seleccionar el detalle a actualizar !!!",SpiritAlert.WARNING);
			}
		}
	}

	private void actualizarDescuentoOrdenMedioTotal(boolean calcularDescuento, int indicesAcumulados, boolean existeNegociacionComision) {
		setearGenericoPautaRegular();
		DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMedios().getModel();
		String nuevoDescuentoStr = getTxtPorcentajeDescuentoOrdenMedio().getText();
		String nuevaBonificacionStr = getTxtPorcentajeBonificacionCompra().getText();
		
		String nuevaComisionAdicional = getTxtPorcentajeComisionAdicional().getText();
		if(nuevaComisionAdicional == null || nuevaComisionAdicional.equals("")){
			nuevaComisionAdicional = "0";
		}

		// Si existe Negociacion o Comision entonces no se debe tomar en cuenta
		// el porcentaje de descuento para los calculos
		// pero si se debe guardar el porcentaje para calcular la negociaciones
		// y comisiones, usamos nueva variable.
		BigDecimal porcentajeNegociacionComision = new BigDecimal(0);
		BigDecimal porcentajeComisionAdicional = new BigDecimal(0);
		if (existeNegociacionComision) {
			porcentajeComisionAdicional = new BigDecimal(nuevaComisionAdicional);
			if(nuevaComisionAdicional.equals("0") || getRbTipoPagoCanje().isSelected() || getCbTipoPagoComision().isSelected()){
				porcentajeNegociacionComision = new BigDecimal(nuevoDescuentoStr);
				nuevoDescuentoStr = "0";
				nuevaBonificacionStr = "0";
			}	
		} else {
			porcentajeNegociacionComision = new BigDecimal(0);
			porcentajeComisionAdicional = new BigDecimal(0);
		}

		BigDecimal nuevoDescuento = new BigDecimal(nuevoDescuentoStr);
		BigDecimal nuevaBonificacion = new BigDecimal(nuevaBonificacionStr);

		// para la comision pura
		String existeComisionPura = getCbTipoPagoComision().isSelected()?"S":"N";

		boolean calcularTotales = true;
		int contador = 0;
		Iterator proveedorIdIt = mapOrdenMediobyProveedorPDesc.keySet().iterator();
		while (proveedorIdIt.hasNext()) {
			String[] key = ((String) proveedorIdIt.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<String> listPorcentajesxOrdenMedio = (ArrayList<String>) mapOrdenMediobyProveedorPDesc.get(String.valueOf(proveedorId) + "-" + numeroOrden);
			for (int i = 0; i < listPorcentajesxOrdenMedio.size(); i++) {
				listPorcentajesxOrdenMedio.set(i,String.valueOf(nuevoDescuentoStr));
				boolean isIndiceSubtotal = false;
				int position = contador;
				for (int j = 0; j < indiceProveedorSubtotalOrdenes.size(); j++) {
					if (position == indiceProveedorSubtotalOrdenes.get(j)) {
						isIndiceSubtotal = true;
					}
				}
				// Si la linea no contiene al subtotal
				if (!isIndiceSubtotal) {
					modelOrdenMedio.setValueAt(nuevoDescuentoStr + "%", contador, 4); // ADD 14 OCTUBRE
					contador++;
				}
				// cuando la linea contiene al subtotal
				else {
					contador++;
					i--;
				}
			}

			mapOrdenMediobyProveedorPDesc.put(String.valueOf(proveedorId) + "-" + numeroOrden, listPorcentajesxOrdenMedio);
		}

		contador = 0;
		Iterator proveedorIdItBono = mapOrdenMediobyProveedorPBono.keySet().iterator();
		while (proveedorIdItBono.hasNext()) {
			String[] key = ((String) proveedorIdItBono.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<String> listPorcentajesxOrdenMedio = (ArrayList<String>) mapOrdenMediobyProveedorPBono.get(String.valueOf(proveedorId) + "-" + numeroOrden);

			for (int i = 0; i < listPorcentajesxOrdenMedio.size(); i++) {
				listPorcentajesxOrdenMedio.set(i,String.valueOf(nuevaBonificacionStr));
				contador++;
			}
			mapOrdenMediobyProveedorPBono.put(String.valueOf(proveedorId) + "-" + numeroOrden, listPorcentajesxOrdenMedio);
		}

		if (calcularTotales) {
			Long proveedorIdSel;
			Long comercialId;
			contador = 0;
			Iterator proveedorIdIt2 = mapOrdenMediobyProveedor.keySet().iterator();
			while (proveedorIdIt2.hasNext()) {
				String[] key = ((String) proveedorIdIt2.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				Map listOrdenMedio = (Map) mapOrdenMediobyProveedor.get(String.valueOf(proveedorId) + "-" + numeroOrden);
				Iterator ordenesMedioMapIt = listOrdenMedio.keySet().iterator();
				while (ordenesMedioMapIt.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioMapIt.next();
					
					Map<Long, ProductoIf> mapaProveedorProducto = mapaGenericoMapaProveedorProducto.get(genericoPautaRegular.getId());
					ProductoIf productoProveedor = mapaProveedorProducto.get(ordenMedioIf.getProveedorId());
					
					GenericoIf genericoProveedor = genericoPautaRegular;
					ordenMedioIf.setProductoProveedorId(productoProveedor.getId());
					
					BigDecimal suman = ordenMedioIf.getValorSubtotal();
					BigDecimal descuento = suman.multiply(nuevoDescuento.divide(new BigDecimal(100)));
					BigDecimal subTotal = suman.subtract(descuento);

					BigDecimal bonificacion = subTotal.multiply(nuevaBonificacion.divide(new BigDecimal(100)));
					subTotal = subTotal.subtract(bonificacion);

					BigDecimal iva = genericoProveedor.getCobraIva().equals("S") ? subTotal.multiply(BigDecimal.valueOf(Parametros.IVA / 100D)) : BigDecimal.ZERO;
					BigDecimal total = subTotal.add(iva);

					getTxtSumanOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(suman)));
					getTxtDescuentoOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuento)));
					getTxtSubtotalOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
					getTxtBonificacionCompraOrden().setText(formatoDecimal.format(Utilitarios.redondeoValor(bonificacion)));
					getTxtIVAOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(iva)));
					getTxtTotalOrdenMedio().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
					// Cambiar porcentaje en PlanMedioDetalles
					Map ordenMedioDetalleMap = (Map) listOrdenMedio.get(ordenMedioIf);
					Iterator ordenMedioDetalleIt = ordenMedioDetalleMap.keySet().iterator();
					if (ordenMedioDetalleIt.hasNext()) {// solo una vez
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt.next();
						comercialId = ordenMedioDetalleIf.getComercialId();
						proveedorIdSel = proveedorId;

						if (existeNegociacionComision) {
							actualizarDescuentoPlanMedioDetalleTotal(
									porcentajeNegociacionComision,
									nuevaBonificacion,
									existeNegociacionComision,
									productoProveedor);
						} else {
							actualizarDescuentoPlanMedioDetalleTotal(
									nuevoDescuento, nuevaBonificacion,
									existeNegociacionComision,
									productoProveedor);
						}
						BigDecimal sumanDetalle = ordenMedioDetalleIf.getValorSubtotal();
						BigDecimal descuentoDetalle = sumanDetalle.multiply(nuevoDescuento.divide(new BigDecimal(100)));
						BigDecimal subTotalDetalle = sumanDetalle.subtract(descuentoDetalle);

						BigDecimal bonificacionDetalle = subTotalDetalle.multiply(nuevaBonificacion.divide(new BigDecimal(100)));
						subTotalDetalle = subTotalDetalle.subtract(bonificacionDetalle);

						BigDecimal ivaDetalle = genericoProveedor.getCobraIva().equals("S") ? subTotalDetalle.multiply(BigDecimal.valueOf(Parametros.IVA / 100D)) : BigDecimal.ZERO;
						ordenMedioDetalleIf.setValorIva(ivaDetalle);
						BigDecimal totalDetalle = subTotalDetalle.add(ivaDetalle);
						ordenMedioDetalleIf.setValorTotal(totalDetalle);
						System.out.println("OK");
					}
					// }
					// seteo la bonificacion, esto sirver para que en
					// setPedidoDetalle se pueda setear el porcentaje de
					// bonificacion
					ordenMedioIf.setPorcentajeBonificacionCompra(nuevaBonificacion);
					ordenMedioIf.setPorcentajeNegociacionComision(porcentajeNegociacionComision);
					ordenMedioIf.setPorcentajeComisionAdicional(porcentajeComisionAdicional);
					ordenMedioIf.setComisionPura(existeComisionPura);
					ordenMedioIf.setValorIva(iva);
					ordenMedioIf.setValorTotal(total);
					contador++;
				}
			} 
		}
	}

	// ADD 29 JUNIO
	private void actualizarCodigoOrdenMedio(int indicesAcumulados) {
		DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMedios()
				.getModel();
		// ADD 29 JUNIO
		String nuevoPCodigoOrdenStr = "";
		// END 29 JUNIO

		// COMENTED 29 JUNIO
		// String nuevoPCanjeStr = getTxtPorcentajeCanje().getText();
		// BigDecimal nuevoPCanje = new BigDecimal(nuevoPCanjeStr);
		/*
		 * if(getRbTipoPagoNormal().isSelected()){
		 * modelOrdenMedio.setValueAt("NORMAL", filaSeleccionada, 4);
		 * nuevoPCanje = new BigDecimal(0); }else{
		 */
		/*
		 * String porcentajeCanje = getTxtPorcentajeCanje().getText();
		 * modelOrdenMedio.setValueAt("CANJE-"+porcentajeCanje+"%",
		 * filaSeleccionada, 4); nuevoPCanje = new BigDecimal(porcentajeCanje);
		 */

		// ADD 29 JUNIO
		// le colocamos el anio con que inicia el codigo de la orden
		nuevoPCodigoOrdenStr = getCodigo(new java.sql.Date(
				new java.util.Date().getTime()));
		// modelOrdenMedio.setValueAt(nuevoPCodigoOrdenStr, filaSeleccionada,
		// 5); COMENTED 14 OCTUBRE
		modelOrdenMedio.setValueAt(nuevoPCodigoOrdenStr, filaSeleccionada, 6); // ADD
																				// 14
																				// OCTUBRE
		// aki dejar en blanco el text box 29 JUNIO
		getTextCodigoOrden().setText(nuevoPCodigoOrdenStr);
		// END 29 JUNIO

		// }

		int contador = 0;
		// COMENTED 29 JUNIO
		// Iterator proveedorIdIt =
		// mapOrdenMediobyProveedorPCanje.keySet().iterator();//borrar
		Iterator proveedorIdIt = mapOrdenMediobyProveedorPCodigoOrden.keySet()
				.iterator();

		while (proveedorIdIt.hasNext()) {
			String[] key = ((String) proveedorIdIt.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			// COMENTED 29 JUNIO
			// ArrayList<BigDecimal> listPorcentajeCanje =
			// (ArrayList<BigDecimal>)mapOrdenMediobyProveedorPCanje.get(proveedorId);
			ArrayList<String> listCodigoOrden = (ArrayList<String>) mapOrdenMediobyProveedorPCodigoOrden
					.get(String.valueOf(proveedorId) + "-" + numeroOrden);
			// COMENTED 29 JUNIO
			// for(int i = 0; i<listPorcentajeCanje.size();i++){
			for (int i = 0; i < listCodigoOrden.size(); i++) {
				// MODIFIED 9 MAYO
				if (contador == (filaSeleccionada - indicesAcumulados)) {
					// if(contador==filaSeleccionada){
					// COMENTED 29 JUNIO
					// listPorcentajeCanje.set(i, nuevoPCanje);
					listCodigoOrden.set(i, nuevoPCodigoOrdenStr);
				}
				contador++;
			}
			// COMENTED 29 JUNIO
			// mapOrdenMediobyProveedorPCanje.put(proveedorId,
			// listPorcentajeCanje);
			mapOrdenMediobyProveedorPCodigoOrden.put(
					String.valueOf(proveedorId) + "-" + numeroOrden,
					listCodigoOrden);
		}
	}

	private final class limpiarCodigoOrden implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			if (getTblOrdenesMedios().getSelectedRow() != -1) {
				setFilaSeleccionada(getTblOrdenesMedios().getSelectedRow());

				// String strPorcentajeCanje =
				// getTxtPorcentajeCanje().getText();
				String strCodigoOrden = getTextCodigoOrden().getText().trim();
				if ((!("".equals(strCodigoOrden)) && (strCodigoOrden != null))) {
					// COMENTED 29 JUNIO
					/*
					 * double porcentajeCanje = 0D; porcentajeCanje =
					 * Double.parseDouble
					 * (Utilitarios.removeDecimalFormat(getTxtPorcentajeCanje
					 * ().getText())); if (porcentajeCanje > porcentajeLimite){
					 * SpiritAlert.createAlert(
					 * "El Porcentaje de Canje debe ser menor igual al 100%",
					 * SpiritAlert.WARNING);
					 * getJtpPlanMedio().setSelectedIndex(3);
					 * getTpMapasPauta().setSelectedIndex(2);
					 * getTxtPorcentajeCanje().grabFocus(); }else{
					 */
					// 9 MAYO
					int acumulador = 0;
					boolean isIndiceSubtotal = false;
					int position = filaSeleccionada;
					for (int i = 0; i < indiceProveedorSubtotalOrdenes.size(); i++) {
						if (position == indiceProveedorSubtotalOrdenes.get(i)) {
							isIndiceSubtotal = true;
						} else if (position > indiceProveedorSubtotalOrdenes
								.get(i)) {
							acumulador++;
						}
					}
					if (!isIndiceSubtotal) {
						// System.out.println("POSITION Y ACUMULADOR: "
						// +position +acumulador);
						// MODIFIED 29 JUNIO
						// actualizarTipoPagoOrdenMedio(acumulador);
						actualizarCodigoOrdenMedio(acumulador);
					}// EN 9 MAYO
						// MODIFIED 9 MAYO
						// actualizarTipoPagoOrdenMedio();
					else {// 11 MAYO //cuando han seleccionado un Subtotal
						SpiritAlert.createAlert(
								"Por favor seleccione una Orden de Medio",
								SpiritAlert.INFORMATION);
					}// END 11 MAYO
						// }ADD 29 JUNIO
				}
			} else {
				SpiritAlert.createAlert(
						"Debe seleccionar el detalle a actualizar !!!",
						SpiritAlert.WARNING);
			}
		}
	}

	private boolean validarTipoPago() {

		double porcentajeDescuentoOrdenMedio = 0D;
		if (getTxtPorcentajeDescuentoOrdenMedio().getText() != null
				&& !getTxtPorcentajeDescuentoOrdenMedio().getText().equals("")) {
			porcentajeDescuentoOrdenMedio = Double.valueOf(getTxtPorcentajeDescuentoOrdenMedio().getText().replaceAll(",", ""));
		}

		double porcentajeBonificacionCompra = 0D;
		if (getTxtPorcentajeBonificacionCompra().getText() != null
				&& !getTxtPorcentajeBonificacionCompra().getText().equals("")) {
			porcentajeBonificacionCompra = Double.valueOf(getTxtPorcentajeBonificacionCompra().getText().replaceAll(",", ""));
		}

		double porcentajeCanje = 0D;
		if (getTxtPorcentajeCanje().getText() != null
				&& !getTxtPorcentajeCanje().getText().equals("")) {
			porcentajeCanje = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeCanje().getText().replaceAll(",", "")));
		}
		
		double porcentajeComisionAdicional = 0D;
		if (getTxtPorcentajeComisionAdicional().getText() != null
				&& !getTxtPorcentajeComisionAdicional().getText().equals("")) {
			porcentajeComisionAdicional = Double.valueOf(getTxtPorcentajeComisionAdicional().getText().replaceAll(",", ""));
		}

		if (getTblOrdenesMedios().getSelectedRow() < 0) {
			SpiritAlert.createAlert("Debe seleccionar el detalle a actualizar.", SpiritAlert.WARNING);
			return false;
		} else if (!getRbTipoPagoNormal().isSelected()
				&& porcentajeDescuentoOrdenMedio <= 0D) {
			SpiritAlert.createAlert("El Porcentaje de Descuento debe ser mayor a 0%", SpiritAlert.WARNING);
			getTxtPorcentajeDescuentoOrdenMedio().grabFocus();
			return false;
		} else if (!getRbTipoPagoNormal().isSelected()
				&& porcentajeDescuentoOrdenMedio > 100D) {
			SpiritAlert.createAlert("El Porcentaje de Descuento no puede ser mayor a 100%",	SpiritAlert.WARNING);
			getTxtPorcentajeDescuentoOrdenMedio().grabFocus();
			return false;
		} else if (getCbComisionAdicional().isSelected()
				&& porcentajeComisionAdicional <= 0D) {
			SpiritAlert.createAlert("El Porcentaje Comisión Adicional debe ser mayor a 0%", SpiritAlert.WARNING);
			getTxtPorcentajeComisionAdicional().grabFocus();
			return false;
		} else if (getCbComisionAdicional().isSelected()
				&& porcentajeComisionAdicional > 100D) {
			SpiritAlert.createAlert("El Porcentaje Comisión Adicional no puede ser mayor a 100%",	SpiritAlert.WARNING);
			getTxtPorcentajeComisionAdicional().grabFocus();
			return false;
		} else if (porcentajeBonificacionCompra > 100D) {
			SpiritAlert.createAlert("El Porcentaje de Bonificación no puede ser mayor a 100%", SpiritAlert.WARNING);
			getTxtPorcentajeBonificacionCompra().grabFocus();
			return false;
		} else if (!getRbTipoPagoNormal().isSelected()
				&& porcentajeCanje > porcentajeLimite) {
			SpiritAlert.createAlert("El Porcentaje de Negociación no puede ser mayor a 100%", SpiritAlert.WARNING);
			getTxtPorcentajeCanje().grabFocus();
			return false;
		} else if (!getRbTipoPagoNormal().isSelected() && porcentajeCanje <= 0D) {
			SpiritAlert.createAlert("El Porcentaje de Negociación debe ser mayor a 0%",	SpiritAlert.WARNING);
			getTxtPorcentajeCanje().grabFocus();
			return false;
		}

		return true;
	}

	private final class SetTipoPagoOrdenMedio implements ActionListener {
		public void actionPerformed(ActionEvent evento) {

			if (validarTipoPago()) {
				setFilaSeleccionada(getTblOrdenesMedios().getSelectedRow());

				int acumulador = 0;
				boolean isIndiceSubtotal = false;
				int position = filaSeleccionada;
				for (int i = 0; i < indiceProveedorSubtotalOrdenes.size(); i++) {
					if (position == indiceProveedorSubtotalOrdenes.get(i)) {
						isIndiceSubtotal = true;
					} else if (position > indiceProveedorSubtotalOrdenes.get(i)) {
						acumulador++;
					}
				}
				if (!isIndiceSubtotal) {
					actualizarTipoPagoOrdenMedio(acumulador);

					/*
					 * double porcentajeCanje = 0D;
					 * if(getTxtPorcentajeCanje().getText() != null &&
					 * !getTxtPorcentajeCanje().getText().equals("")){
					 * porcentajeCanje =
					 * Double.parseDouble(Utilitarios.removeDecimalFormat
					 * (getTxtPorcentajeCanje().getText().replaceAll(",", "")));
					 * }
					 * 
					 * if(porcentajeCanje >= 100 ||
					 * getCbTipoPagoComision().isSelected()){
					 */

					if (getRbTipoPagoCanje().isSelected()
							|| getCbTipoPagoComision().isSelected()
							|| getCbComisionAdicional().isSelected()) {
						actualizarDescuentoOrdenMedio(false, acumulador, true);
					} else {
						getTxtPorcentajeCanje().setText("");
						actualizarDescuentoOrdenMedio(false, acumulador, false);
					}
				} else {
					SpiritAlert.createAlert(
							"Por favor seleccione una Orden de Medio",
							SpiritAlert.INFORMATION);
				}
			}
		}
	}

	private void actualizarTipoPagoOrdenMedio(int indicesAcumulados) {
		DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMedios().getModel();
		BigDecimal nuevoPCanje = new BigDecimal(0);
		if (getRbTipoPagoNormal().isSelected()) {
			modelOrdenMedio.setValueAt("NORMAL", filaSeleccionada, 5); // ADD 14 OCTUBRE
			nuevoPCanje = new BigDecimal(0);
		} else {
			String porcentajeCanje = getTxtPorcentajeCanje().getText();
			modelOrdenMedio.setValueAt("CANJE-" + porcentajeCanje + "%",filaSeleccionada, 5); // ADD 14 OCTUBRE
			nuevoPCanje = new BigDecimal(porcentajeCanje);
		}

		int contador = 0;
		Iterator proveedorIdIt = mapOrdenMediobyProveedorPCanje.keySet().iterator();
		while (proveedorIdIt.hasNext()) {
			String[] key = ((String) proveedorIdIt.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<BigDecimal> listPorcentajeCanje = (ArrayList<BigDecimal>) mapOrdenMediobyProveedorPCanje.get(String.valueOf(proveedorId) + "-" + numeroOrden);
			for (int i = 0; i < listPorcentajeCanje.size(); i++) {
				if (contador == (filaSeleccionada - indicesAcumulados)) {
					listPorcentajeCanje.set(i, nuevoPCanje);
				}
				contador++;
			}
			mapOrdenMediobyProveedorPCanje.put(String.valueOf(proveedorId) + "-" + numeroOrden, listPorcentajeCanje);
		}
	}

	private final class SetTipoPagoOrdenMedioxProv implements ActionListener {
		public void actionPerformed(ActionEvent evento) {

			if (validarTipoPago()) {
				setFilaSeleccionada(getTblOrdenesMedios().getSelectedRow());

				int acumulador = 0;
				boolean isIndiceSubtotal = false;
				int position = filaSeleccionada;
				for (int i = 0; i < indiceProveedorSubtotalOrdenes.size(); i++) {
					if (position == indiceProveedorSubtotalOrdenes.get(i)) {
						isIndiceSubtotal = true;
					} else if (position > indiceProveedorSubtotalOrdenes.get(i)) {
						acumulador++;
					}
				}

				if (!isIndiceSubtotal) {
					actualizarTipoPagoOrdenMedioxCanal(acumulador);
					if (getRbTipoPagoCanje().isSelected()
							|| getCbTipoPagoComision().isSelected()
							|| getCbComisionAdicional().isSelected()) {
						actualizarDescuentoOrdenMedioxCanal(acumulador, true);
					} else {
						getTxtPorcentajeCanje().setText("");
						actualizarDescuentoOrdenMedioxCanal(acumulador, false);
					}
				} else {
					SpiritAlert.createAlert(
							"Por favor seleccione una Orden de Medio",
							SpiritAlert.INFORMATION);
				}
			}
		}
	}

	private void actualizarTipoPagoOrdenMedioxCanal(int acumulador) {
		String tipoPago = "";
		BigDecimal nuevoPCanje = new BigDecimal(0);

		if (getRbTipoPagoNormal().isSelected()) {
			tipoPago = "NORMAL";
			nuevoPCanje = new BigDecimal(0);
		} else {
			String porcentajeCanje = getTxtPorcentajeCanje().getText();
			tipoPago = "CANJE-" + porcentajeCanje + "%";
			nuevoPCanje = new BigDecimal(porcentajeCanje);
		}

		DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMedios().getModel();
		String canal = (String) modelOrdenMedio.getValueAt(filaSeleccionada, 1);
		Long proveedorSeleccionado = new Long(0);
		Iterator proveedorIt = listaProveedoresMap.keySet().iterator();

		while (proveedorIt.hasNext()) {
			String[] key = ((String) proveedorIt.next()).split("-");
			Long proveedor = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ClienteOficinaIf cliente = (ClienteOficinaIf) listaProveedoresMap.get(String.valueOf(proveedor) + "-" + numeroOrden);
			if (cliente.getDescripcion().compareTo(canal) == 0) {
				proveedorSeleccionado = proveedor;
			}
		}

		int contador = 0;
		Iterator proveedorIdIt = mapOrdenMediobyProveedorPCanje.keySet().iterator();
		while (proveedorIdIt.hasNext()) {
			String[] key = ((String) proveedorIdIt.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<BigDecimal> listPorcentajeCanje = (ArrayList<BigDecimal>) mapOrdenMediobyProveedorPCanje.get(String.valueOf(proveedorId) + "-" + numeroOrden);
			for (int i = 0; i < listPorcentajeCanje.size(); i++) {
				if (proveedorSeleccionado.compareTo(proveedorId) == 0) {
					listPorcentajeCanje.set(i, nuevoPCanje);
					modelOrdenMedio.setValueAt(tipoPago, contador + acumulador, 5);
				}
				contador++;
			}
			mapOrdenMediobyProveedorPCanje.put(String.valueOf(proveedorId) + "-" + numeroOrden, listPorcentajeCanje);
		}
	}

	private final class SetTipoPagoOrdenMedioTotal implements ActionListener {
		public void actionPerformed(ActionEvent evento) {

			if (validarTipoPago()) {
				int acumulador = 0;
				boolean isIndiceSubtotal = false;
				int position = filaSeleccionada;
				for (int i = 0; i < indiceProveedorSubtotalOrdenes.size(); i++) {
					if (position == indiceProveedorSubtotalOrdenes.get(i)) {
						isIndiceSubtotal = true;
					} else if (position > indiceProveedorSubtotalOrdenes.get(i)) {
						acumulador++;
					}
				}
				if (!isIndiceSubtotal) {
					actualizarTipoPagoOrdenMedioTotal(acumulador);
					if (getRbTipoPagoCanje().isSelected()
							|| getCbTipoPagoComision().isSelected()
							|| getCbComisionAdicional().isSelected()) {
						actualizarDescuentoOrdenMedioTotal(false, acumulador,
								true);
					} else {
						getTxtPorcentajeCanje().setText("");
						actualizarDescuentoOrdenMedioTotal(false, acumulador,
								false);
					}
				} else {
					SpiritAlert.createAlert(
							"Por favor seleccione una Orden de Medio",
							SpiritAlert.INFORMATION);
				}
			}
		}
	}

	private void actualizarTipoPagoOrdenMedioTotal(int acumulador) {
		DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMedios()
				.getModel();
		String tipoPago = "";
		BigDecimal nuevoPCanje = new BigDecimal(0);
		if (getRbTipoPagoNormal().isSelected()) {
			tipoPago = "NORMAL";
			nuevoPCanje = new BigDecimal(0);
		} else {
			String porcentajeCanje = getTxtPorcentajeCanje().getText();
			tipoPago = "CANJE-" + porcentajeCanje + "%";
			nuevoPCanje = new BigDecimal(porcentajeCanje);
		}

		int contador = 0;
		Iterator proveedorIdIt = mapOrdenMediobyProveedorPCanje.keySet().iterator();
		while (proveedorIdIt.hasNext()) {
			String[] key = ((String) proveedorIdIt.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<BigDecimal> listPorcentajeCanje = (ArrayList<BigDecimal>) mapOrdenMediobyProveedorPCanje.get(String.valueOf(proveedorId) + "-" + numeroOrden);

			for (int i = 0; i < listPorcentajeCanje.size(); i++) {
				listPorcentajeCanje.set(i, nuevoPCanje);
				// ADD 11 MAYO
				boolean isIndiceSubtotal = false;
				int position = contador;
				for (int j = 0; j < indiceProveedorSubtotalOrdenes.size(); j++) {
					if (position == indiceProveedorSubtotalOrdenes.get(j)) {
						isIndiceSubtotal = true;
					}
				}
				// Si la linea no contiene al subtotal
				if (!isIndiceSubtotal) {
					// modelOrdenMedio.setValueAt(tipoPago, contador, 4);
					// COMENTED 14 OCTUBRE
					modelOrdenMedio.setValueAt(tipoPago, contador, 5); // ADD 14
																		// OCTUBRE
					contador++;
				} else {// cuando la linea contiene al subtotal
					contador++;
					i--;
				}
				// END 11 MAYO
				// modelOrdenMedio.setValueAt(tipoPago, contador, 4);
				// 9 MAYO POSIBLE ERROR!! REVISAR
				// GIOMYYYYYYYYYYYYYYYYYYY!!!!!!!!!!!!
			}
			mapOrdenMediobyProveedorPCanje.put(String.valueOf(proveedorId) + "-" + numeroOrden, listPorcentajeCanje);
		}
	}

	private void setFilaSeleccionada(int filaSeleccionada) {
		this.filaSeleccionada = filaSeleccionada;
	}

	// ADD 15 JUNIO
	private void setFilaSeleccionadaSaved(int filaSeleccionadaSaved) {
		this.filaSeleccionadaSaved = filaSeleccionadaSaved;
	}

	// END 15 JUNIO

	private void actualizarDescuentoPlanMedioDetallexComercial(Long proveedorId, int numeroOrden, Long campanaProductoVersionId, BigDecimal nuevoDescuento, BigDecimal nuevaBonificacion, boolean existeNegociacionComision, ProductoIf productoProveedor) {
		// Recorrer mapasComercialesPlantilla
		Iterator planMedioDetalleIt = mapasComercialesPlantilla.keySet().iterator();
		while (planMedioDetalleIt.hasNext()) {
			PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) planMedioDetalleIt.next();
			Collection<MapaComercialIf> mapasComerciales = (Collection<MapaComercialIf>) mapasComercialesPlantilla.get(planMedioDetalleIf);
			if (planMedioDetalleIf.getProveedorId().compareTo(proveedorId) == 0 && planMedioDetalleIf.getCampanaProductoVersionId().compareTo(campanaProductoVersionId) == 0 && planMedioDetalleIf.getNumeroOrdenAgrupacion() == numeroOrden) {
				planMedioDetalleIf.setPorcentajeDescuento(nuevoDescuento);
				planMedioDetalleIf.setPorcentajeBonificacionCompra(nuevaBonificacion);
				planMedioDetalleIf.setNegociacionComision(existeNegociacionComision ? "S": "N");
				planMedioDetalleIf.setProductoProveedorId(productoProveedor.getId());
				mapasComercialesPlantilla.put(planMedioDetalleIf,mapasComerciales);
			}
		}
		calcularTotales();
	}

	private void actualizarDescuentoPlanMedioDetallexProducto(Long proveedorId, int numeroOrden,
			Long productoClienteId, BigDecimal nuevoDescuento,
			BigDecimal nuevaBonificacion, boolean existeNegociacionComision, ProductoIf productoProveedor) {
		// Recorrer mapasComercialesPlantilla
		Iterator planMedioDetalleIt = mapasComercialesPlantilla.keySet().iterator();
		while (planMedioDetalleIt.hasNext()) {
			PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) planMedioDetalleIt.next();
			Collection<MapaComercialIf> mapasComerciales = (Collection<MapaComercialIf>) mapasComercialesPlantilla.get(planMedioDetalleIf);
			if (planMedioDetalleIf.getProveedorId().compareTo(proveedorId) == 0 && planMedioDetalleIf.getProductoClienteId().compareTo(productoClienteId) == 0 && planMedioDetalleIf.getNumeroOrdenAgrupacion() == numeroOrden) {
				planMedioDetalleIf.setPorcentajeDescuento(nuevoDescuento);
				planMedioDetalleIf.setPorcentajeBonificacionCompra(nuevaBonificacion);
				planMedioDetalleIf.setNegociacionComision(existeNegociacionComision ? "S": "N");
				planMedioDetalleIf.setProductoProveedorId(productoProveedor.getId());
				mapasComercialesPlantilla.put(planMedioDetalleIf,mapasComerciales);
			}
		}

		calcularTotales();
	}

	private void calcularTotales() {
		totalSumaTotal = new BigDecimal(0);
		totalSumaTotalSinIVA = new BigDecimal(0);
		totalSumaTotalConIVA = new BigDecimal(0);
		descuentoTotal = new BigDecimal(0);
		bonificacionCompraTotal = new BigDecimal(0);
		ivaTotal = new BigDecimal(0);
		Iterator planMedioDetalleIt = mapasComercialesPlantilla.keySet().iterator();
		
		/*while (planMedioDetalleIt.hasNext()) {
			PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) planMedioDetalleIt.next();
			Collection<MapaComercialIf> mapasComerciales = (Collection<MapaComercialIf>) mapasComercialesPlantilla.get(planMedioDetalleIf);
			BigDecimal total_cunias = new BigDecimal(planMedioDetalleIf.getTotalCunias());
			BigDecimal sumatotal = planMedioDetalleIf.getValorTarifa().multiply(total_cunias);
			planMedioDetalleIf.setValorSubtotal(sumatotal);

			BigDecimal descuento = new BigDecimal(0);
			BigDecimal bonificacion = new BigDecimal(0);
			BigDecimal subtotal = sumatotal;

			if (planMedioDetalleIf.getNegociacionComision() == null || planMedioDetalleIf.getNegociacionComision().equals("N")) {
				descuento = sumatotal.multiply(planMedioDetalleIf.getPorcentajeDescuento().divide(new BigDecimal(100)));
				subtotal = sumatotal.subtract(descuento);
				bonificacion = subtotal.multiply(planMedioDetalleIf.getPorcentajeBonificacionCompra().divide(new BigDecimal(100)));
				subtotal = subtotal.subtract(bonificacion);
			}
			planMedioDetalleIf.setValorDescuento(descuento);
			ProductoIf productoProveedor = mapaProducto.get(planMedioDetalleIf.getProductoProveedorId());
			GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
			BigDecimal valor_iva = genericoProveedor.getCobraIva().equals("S") ? subtotal.multiply(new BigDecimal(Parametros.IVA / 100D)) : BigDecimal.ZERO;
			planMedioDetalleIf.setValorIva(valor_iva);
			BigDecimal valorTotal = subtotal.add(valor_iva);
			planMedioDetalleIf.setValorTotal(valorTotal);

			totalSumaTotal = totalSumaTotal.add(sumatotal);
			totalSumaTotalConIVA = totalSumaTotalConIVA.add(genericoProveedor.getCobraIvaCliente().equals("S") ? sumatotal: BigDecimal.ZERO);
			totalSumaTotalSinIVA = totalSumaTotalSinIVA.add(genericoProveedor.getCobraIvaCliente().equals("N") ? sumatotal: BigDecimal.ZERO);
			descuentoTotal = descuentoTotal.add(descuento);
			bonificacionCompraTotal = bonificacionCompraTotal.add(bonificacion);
			// --- CAMBIO PARA CALCULO DE IVA
			ivaTotal = ivaTotal.add(valor_iva);
		}*/
		
		while (planMedioDetalleIt.hasNext()) {
			PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) planMedioDetalleIt.next();
			Collection<MapaComercialIf> mapasComerciales = (Collection<MapaComercialIf>) mapasComercialesPlantilla.get(planMedioDetalleIf);
			double total_cunias = planMedioDetalleIf.getTotalCunias();
			double sumatotal = planMedioDetalleIf.getValorTarifa().doubleValue() * total_cunias;
			planMedioDetalleIf.setValorSubtotal(BigDecimal.valueOf(sumatotal));

			double descuento = 0D;
			double bonificacion = 0D;
			double subtotal = sumatotal;

			if (planMedioDetalleIf.getNegociacionComision() == null || planMedioDetalleIf.getNegociacionComision().equals("N")) {
				descuento = sumatotal * (planMedioDetalleIf.getPorcentajeDescuento().doubleValue() / 100D);
				subtotal = sumatotal - descuento;
				bonificacion = subtotal * (planMedioDetalleIf.getPorcentajeBonificacionCompra().doubleValue() / 100D);
				subtotal = subtotal - bonificacion;
			}
			planMedioDetalleIf.setValorDescuento(new BigDecimal(descuento));
			ProductoIf productoProveedor = mapaProducto.get(planMedioDetalleIf.getProductoProveedorId());
			GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
			double valor_iva = genericoProveedor.getCobraIva().equals("S") ? subtotal * (Parametros.IVA / 100D) : 0D;
			planMedioDetalleIf.setValorIva(new BigDecimal(valor_iva));
			double valorTotal = subtotal + valor_iva;
			planMedioDetalleIf.setValorTotal(new BigDecimal(valorTotal));

			totalSumaTotal = totalSumaTotal.add(new BigDecimal(sumatotal));
			totalSumaTotalConIVA = totalSumaTotalConIVA.add(genericoProveedor.getCobraIvaCliente().equals("S") ? new BigDecimal(sumatotal): BigDecimal.ZERO);
			totalSumaTotalSinIVA = totalSumaTotalSinIVA.add(genericoProveedor.getCobraIvaCliente().equals("N") ? new BigDecimal(sumatotal): BigDecimal.ZERO);
			descuentoTotal = descuentoTotal.add(new BigDecimal(descuento));
			bonificacionCompraTotal = bonificacionCompraTotal.add(new BigDecimal(bonificacion));
			// --- CAMBIO PARA CALCULO DE IVA
			ivaTotal = ivaTotal.add(new BigDecimal(valor_iva));
		}

		totalSubTotal = totalSumaTotal.subtract(descuentoTotal);
		totalSubTotalBonificacion = totalSubTotal.subtract(bonificacionCompraTotal);
		// ivaTotal =
		// totalSubTotalBonificacion.multiply(BigDecimal.valueOf(Parametros.IVA
		// / 100D));
		totalValorTotal = totalSubTotalBonificacion.add(ivaTotal);

		getTxtSuman().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalSumaTotal)));
		getTxtDescuento().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
		getTxtSubTotal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalSubTotal)));
		getTxtBonificacionCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(bonificacionCompraTotal)));
		getTxtSubtotalBonificacionCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalSubTotalBonificacion)));
		getTxtIVA().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
		getTxtTotalPauta().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalValorTotal)));
	}
	
	private void actualizarDescuentoPlanMedioDetallexOrden(Long proveedorId, int numeroOrden, BigDecimal nuevoDescuento, BigDecimal nuevaBonificacion,boolean existeNegociacionComision, ProductoIf productoProveedor) {
		// Recorrer mapasComercialesPlantilla
		Iterator planMedioDetalleIt = mapasComercialesPlantilla.keySet().iterator();
		while (planMedioDetalleIt.hasNext()) {
			PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) planMedioDetalleIt.next();
			Collection<MapaComercialIf> mapasComerciales = (Collection<MapaComercialIf>) mapasComercialesPlantilla.get(planMedioDetalleIf);
			if (planMedioDetalleIf.getProveedorId().compareTo(proveedorId) == 0 && planMedioDetalleIf.getNumeroOrdenAgrupacion() == numeroOrden) {
				planMedioDetalleIf.setPorcentajeDescuento(nuevoDescuento);
				planMedioDetalleIf.setPorcentajeBonificacionCompra(nuevaBonificacion);
				planMedioDetalleIf.setNegociacionComision(existeNegociacionComision ? "S": "N");
				planMedioDetalleIf.setProductoProveedorId(productoProveedor.getId());
				mapasComercialesPlantilla.put(planMedioDetalleIf,mapasComerciales);
			}
		}
		calcularTotales();
	}

	private void actualizarDescuentoPlanMedioDetallexCanal(Long proveedorId, BigDecimal nuevoDescuento, BigDecimal nuevaBonificacion,boolean existeNegociacionComision, ProductoIf productoProveedor) {
		// Recorrer mapasComercialesPlantilla
		Iterator planMedioDetalleIt = mapasComercialesPlantilla.keySet().iterator();
		while (planMedioDetalleIt.hasNext()) {
			PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) planMedioDetalleIt.next();
			Collection<MapaComercialIf> mapasComerciales = (Collection<MapaComercialIf>) mapasComercialesPlantilla.get(planMedioDetalleIf);
			if (planMedioDetalleIf.getProveedorId().compareTo(proveedorId) == 0) {
				planMedioDetalleIf.setPorcentajeDescuento(nuevoDescuento);
				planMedioDetalleIf.setPorcentajeBonificacionCompra(nuevaBonificacion);
				planMedioDetalleIf.setNegociacionComision(existeNegociacionComision ? "S": "N");
				planMedioDetalleIf.setProductoProveedorId(productoProveedor.getId());
				mapasComercialesPlantilla.put(planMedioDetalleIf,mapasComerciales);
			}
		}
		calcularTotales();
	}

	private void actualizarDescuentoPlanMedioDetalleTotal(BigDecimal nuevoDescuento, BigDecimal nuevaBonificacion,boolean existeNegociacionComision, ProductoIf productoProveedor) {
		// Recorrer mapasComercialesPlantilla
		Iterator planMedioDetalleIt = mapasComercialesPlantilla.keySet().iterator();
		while (planMedioDetalleIt.hasNext()) {
			PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) planMedioDetalleIt.next();
			Collection<MapaComercialIf> mapasComerciales = (Collection<MapaComercialIf>) mapasComercialesPlantilla.get(planMedioDetalleIf);
			planMedioDetalleIf.setPorcentajeDescuento(nuevoDescuento);
			planMedioDetalleIf.setPorcentajeBonificacionCompra(nuevaBonificacion);
			planMedioDetalleIf.setNegociacionComision(existeNegociacionComision ? "S": "N");
			planMedioDetalleIf.setProductoProveedorId(productoProveedor.getId());
			mapasComercialesPlantilla.put(planMedioDetalleIf, mapasComerciales);
		}
		calcularTotales();
	}

	KeyListener oKeyAdapterTblSubPeriodo = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable) evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable) evt.getSource()).getSelectedRow();
			setPlanMedioMesSeleccionado(((JTable) evt.getSource())
					.convertRowIndexToModel(selectedRow));
			planMedioMesActualizadoIf = (PlanMedioMesIf) getPlanMedioMesVector()
					.get(getPlanMedioMesSeleccionado());

			if (planMedioMesActualizadoIf.getTipo().equals(TIPO_LANZAMIENTO))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_LANZAMIENTO);
			else if (planMedioMesActualizadoIf.getTipo().equals(
					TIPO_MANTENIMIENTO))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_MANTENIMIENTO);
			else if (planMedioMesActualizadoIf.getTipo().equals(
					TIPO_PROMOCIONAL))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_PROMOCIONAL);
			else if (planMedioMesActualizadoIf.getTipo().equals(
					TIPO_EXPECTATIVA))
				getCmbTipo().setSelectedItem(NOMBRE_TIPO_EXPECTATIVA);

			calendarSubFechaInicio.setTime(planMedioMesActualizadoIf
					.getFechaInicio());
			getCmbSubPeriodoFechaInicio().setCalendar(calendarSubFechaInicio);
			getCmbSubPeriodoFechaInicio().repaint();
			calendarSubFechaFin
					.setTime(planMedioMesActualizadoIf.getFechaFin());
			getCmbSubPeriodoFechaFin().setCalendar(calendarSubFechaFin);
			getCmbSubPeriodoFechaFin().repaint();
		}
	}

	private void initListeners() {
		getCbPautaTelevision().addActionListener(
				oActionListenerCbPautaTelevision);
		getCbPautaRadio().addActionListener(oActionListenerCbPautaRadio);
		getCbPautaCine().addActionListener(oActionListenerCbPautaCine);
		getRbTipoPagoNormal()
				.addActionListener(oActionListenerRbTipoPagoNormal);
		getRbTipoPagoCanje().addActionListener(oActionListenerRbTipoPagoCanje);
		getCbComisionAdicional().addActionListener(oActionListenerCbComisionAdicional);
		getBtnCorporacion().addActionListener(oActionListenerBtnCorporacion);
		getBtnCliente().addActionListener(oActionListenerBtnCliente);
		getBtnOficina().addActionListener(oActionListenerBtnOficina);
		getCmbTarget().addActionListener(oActionListenerCmbTarget);
		getCmbPeriodoFechaInicio().addActionListener(
				oActionListenerCmbPeriodoFechaInicio);
		getCmbPeriodoFechaFin().addActionListener(
				oActionListenerCmbPeriodoFechaFin);
		getBtnAgregarDetalle().addActionListener(
				oActionListenerBtnAgregarDetalle);
		getBtnActualizarDetalle().addActionListener(
				oActionListenerBtnActualizarDetalle);
		getBtnEliminarDetalle().addActionListener(
				oActionListenerBtnRemoverDetalle);
		getBtnTvData().addActionListener(oActionListenerBtnTvData);
		getBtnImportarMapaPautaTv().addActionListener(
				oActionListenerBtnCrearMapaPautaTv);
		getBtnImportarMapaPautaTvMultiple().addActionListener(
				oActionListenerBtnCrearMapaPautaTvMultiple);// button Excel
															// Mutiple
		getBtnCrearMapaPautaPrensa().addActionListener(
				oActionListenerBtnCrearMapaPautaPrensa);
		getBtnAgregarProgramaTv().addActionListener(
				oActionListenerBtnAgregarProgramaTv);
		getBtnEliminarProgramaTv().addActionListener(
				oActionListenerBtnEliminarProgramaTv);
		getCmbTipoPeriodo().addActionListener(oActionListenerCmbTipoPeriodo);
		getCmbOrdenTrabajo().addActionListener(oActionListenerCmbOrdenTrabajo);
		getBtnCrearOrdenes().addActionListener(oActionListenerBtnCrearOrdenes);
		getBtnGuayaquil().addActionListener(oActionListenerBtnGuayaquil);
		getCbPlanMedioNuevaVersion().addActionListener(
				oActionListenerCbPlanMedioNuevaVersion);
		getCbPlanMedioNuevoMes().addActionListener(
				oActionListenerCbPlanMedioNuevoMes);
		getBtnPlanMedioRelacion().addActionListener(
				oActionListenerBtnPlanMedioRelacion);
		getBtnSetPDsctoOrdenMedio().addActionListener(
				new SetPDescuentoOrdenMedio());
		getBtnSetPDsctoOrdenMedioxProv().addActionListener(
				new SetPDescuentoOrdenMedioxProv());
		getBtnSetPDsctoOrdenMedioTotal().addActionListener(
				new SetPDescuentoOrdenMedioTotal());
		getBtnSetTipoPago().addActionListener(new SetTipoPagoOrdenMedio());
		getBtnSetTipoPagoxProv().addActionListener(
				new SetTipoPagoOrdenMedioxProv());
		getBtnSetTipoPagoTotal().addActionListener(
				new SetTipoPagoOrdenMedioTotal());
		getBtnSetObservacionOrdenMedio().addActionListener(
				new setObservacionOrdenMedio());
		getBtnSetObservacionOrdenMedioxProv().addActionListener(
				new setObservacionOrdenMedioxProv());
		getBtnSetObservacionOrdenMedioTotal().addActionListener(
				new setObservacionOrdenMedioTotal());
		getCbPautaBasica().addActionListener(oActionListenerCbPautaBasica);
		getCbAgrupaOrdenes().addActionListener(oActionListenerCbAgrupaOrdenes);
		getCbOrdenPorProductoComercial().addActionListener(
				oActionListenerCbOrdenPorProductoComercial);
		getCbOrdenPorVersion().addActionListener(
				oActionListenerCbOrdenPorVersion);

		getTxtPorcentajeDescuentoVenta().addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				double porcentajeDescuentoPauta = 0D;

				if (!getTxtPorcentajeDescuentoVenta().getText().equals(""))
					porcentajeDescuentoPauta = Double.parseDouble(Utilitarios
							.removeDecimalFormat(getTxtPorcentajeDescuentoVenta()
									.getText()));

				if (porcentajeDescuentoPauta != descuentoTotalVenta
						.doubleValue()) {
					porcentajeDescuentoTotalVenta = BigDecimal
							.valueOf(porcentajeDescuentoPauta);
					calcularTotalesVenta();
				}
			}
		});

		getTxtPorcentajeComisionAgencia().addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				double porcentajeComisionAgenciaPauta = 0D;

				if (!getTxtPorcentajeComisionAgencia().getText().equals(""))
					porcentajeComisionAgenciaPauta = Double.parseDouble(Utilitarios
							.removeDecimalFormat(getTxtPorcentajeComisionAgencia()
									.getText()));

				if (porcentajeComisionAgenciaPauta != comisionAgenciaTotal
						.doubleValue()) {
					porcentajeComisionAgencia = BigDecimal
							.valueOf(porcentajeComisionAgenciaPauta);
					calcularTotalesVenta();
				}
			}
		});

		getTxtPorcentajeBonificacionVenta().addCaretListener(
				new CaretListener() {
					public void caretUpdate(CaretEvent e) {
						double porcentajeBonificacionVentaPauta = 0D;

						if (!getTxtPorcentajeBonificacionVenta().getText()
								.equals(""))
							porcentajeBonificacionVentaPauta = Double.parseDouble(Utilitarios
									.removeDecimalFormat(getTxtPorcentajeBonificacionVenta()
											.getText()));

						if (porcentajeBonificacionVentaPauta != bonificacionVentaTotal
								.doubleValue()) {
							porcentajeBonificacionVenta = BigDecimal
									.valueOf(porcentajeBonificacionVentaPauta);
							calcularTotalesVenta();
						}
					}
				});

		getArbolPrensa().addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				listenerCheckBoxTreePrensa();
			}
		});

		getArbolTelevision().addTreeSelectionListener(
				new TreeSelectionListener() {
					public void valueChanged(TreeSelectionEvent e) {
						listenerCheckBoxTreeTv();
					}
				});
	}

	ActionListener oActionListenerBtnCrearOrdenes = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			report();
		}
	};

	// ADD 29 AGOSTO
	ActionListener oActionListenerCbPautaBasica = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getCbPautaBasica().isSelected()) {
				limpiarSeleccionesTablaComerciales();
				// ADD 5 SEPTIEMBRE
				getCbAgrupaOrdenes().setSelected(false);
				// ADD 6 OCTUBRE
				getCbOrdenPorProductoComercial().setSelected(false);
				getCbOrdenPorVersion().setSelected(false);
			} else
				marcarSeleccionesTablaComerciales();
		}
	};
	// END 29 AGOSTO

	// ADD 5 SEPTIEMBRE
	ActionListener oActionListenerCbAgrupaOrdenes = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getCbAgrupaOrdenes().isSelected()) {
				getCbPautaBasica().setSelected(false);
				// ADD 13 OCTUBRE
				getCbOrdenPorProductoComercial().setSelected(false);
				getCbOrdenPorVersion().setSelected(false);

				marcarSeleccionesTablaComerciales();
			}
		}
	};
	// END 5 SEPTIEMBRE

	// ADD 6 OCTUBRE
	ActionListener oActionListenerCbOrdenPorProductoComercial = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getCbOrdenPorProductoComercial().isSelected()) {
				getCbPautaBasica().setSelected(false);
				getCbAgrupaOrdenes().setSelected(false);
				getCbOrdenPorVersion().setSelected(false);
				marcarSeleccionesTablaComerciales();
			}
		}
	};
	// END 6 OCTUBRE

	// ADD 6 OCTUBRE
	ActionListener oActionListenerCbOrdenPorVersion = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getCbOrdenPorVersion().isSelected()) {
				getCbPautaBasica().setSelected(false);
				getCbAgrupaOrdenes().setSelected(false);
				getCbOrdenPorProductoComercial().setSelected(false);
				marcarSeleccionesTablaComerciales();
			}
		}
	};
	// END 6 OCTUBRE

	// ADD 16 JUNIO
	ActionListener oActionListenerCbPlanMedioNuevaVersion = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			// AKI GIOMY 23 JUNIO PREGUNTAR SI KIERO CAMBIAR LUEGO DE HABER
			// CARGADO YA UN EXCEL
			// MOSTRAR MENSAJITO DE QUE PREFERIBLE HAGA UNO NUEVO

			if (getCbPlanMedioNuevaVersion().isSelected()) {
				if (getCbPlanMedioNuevoMes().isSelected()) {
					getCbPlanMedioNuevoMes().setSelected(false);

					// ADD 20 JUNIO
					// planMedioTipo = null;
					planMedioTipo = PLAN_MEDIO_TIPO_NORMAL;
					getTextPlanMedioRelacion().setText(null);
					planMedioOriginalIf = null;
					// END 20 JUNIO

					// ADD 23 JUNIO
					cleanDatosPlanMedioSaved();
					cleanTablaOrdenesMedioCmp();
					// END 23 JUNIO
				}
				// Para poder seleccionar un Plan Medio Origen y mantener la
				// relacion
				getBtnPlanMedioRelacion().setEnabled(true);
				// Para actualizar las Ordenes de Medio
				getTextCodigoOrden().setEnabled(true);// habilita la caja de
														// texto de codigo de
														// las ordenes de medio
				getBtnLimpiarCodigoOrden().setEnabled(true);
				// MODIFIED 29 JUNIO
				// getBtnCambiarCodigo().setEnabled(true);
				getBtnCambiarCodigo().setEnabled(false);
				// ADD 29 JUNIO
				getBtnCambiarCodigo().setVisible(false);
				// ADD 20 JUNIO
				planMedioTipo = PLAN_MEDIO_TIPO_NUEVA_VERSION;
				// END 20 JUNIO
			} else if (!getCbPlanMedioNuevoMes().isSelected()) {
				getBtnPlanMedioRelacion().setEnabled(false);

				// ADD 20 JUNIO
				// planMedioTipo = "";
				planMedioTipo = PLAN_MEDIO_TIPO_NORMAL;
				getTextPlanMedioRelacion().setText(null);
				planMedioOriginalIf = null;
				// END 20 JUNIO

				// COMENTED 23 JUNIO
				// ADD 21 JUNIO
				/*
				 * planMedioIfSaved = null; planMedioDetallesPlantillaSaved =
				 * null; planMedioDetallesPlantillaSaved = new
				 * ArrayList<PlanMedioDetalleIf>();
				 */

				// ADD 23 JUNIO
				cleanDatosPlanMedioSaved();
				cleanTablaOrdenesMedioCmp();
				// END 21 JUNIO
			}
		}
	};

	ActionListener oActionListenerCbPlanMedioNuevoMes = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			// AKI GIOMY 23 JUNIO PREGUNTAR SI KIERO CAMBIAR LUEGO DE HABER
			// CARGADO YA UN EXCEL
			// MOSTRAR MENSAJITO DE QUE PREFERIBLE HAGA UNO NUEVO

			if (getCbPlanMedioNuevoMes().isSelected()) {
				if (getCbPlanMedioNuevaVersion().isSelected()) {
					getCbPlanMedioNuevaVersion().setSelected(false);

					// planMedioTipo = null;
					planMedioTipo = PLAN_MEDIO_TIPO_NORMAL;
					getTextPlanMedioRelacion().setText(null);
					planMedioOriginalIf = null;

					// ADD 23 JUNIO
					cleanDatosPlanMedioSaved();
					cleanTablaOrdenesMedioCmp();
					// END 23 JUNIO
				}
				getBtnPlanMedioRelacion().setEnabled(true);
				// ADD 20 JUNIO
				planMedioTipo = PLAN_MEDIO_TIPO_NUEVO_MES;
				// END 20 JUNIO
			} else if (!getCbPlanMedioNuevaVersion().isSelected()) {
				getBtnPlanMedioRelacion().setEnabled(false);
				// Para actualizar las Ordenes de Medio
				getTextCodigoOrden().setEnabled(false);// habilita la caja de
														// texto de codigo de
														// las ordenes de medio
				getBtnLimpiarCodigoOrden().setEnabled(false);
				getBtnCambiarCodigo().setEnabled(false);
				// ADD 29 JUNIO
				getBtnCambiarCodigo().setVisible(false);
				// ADD 20 JUNIO
				// planMedioTipo = "";
				planMedioTipo = PLAN_MEDIO_TIPO_NORMAL;
				getTextPlanMedioRelacion().setText(null);
				planMedioOriginalIf = null;
				// END 20 JUNIO
			}
		}
	};

	ActionListener oActionListenerBtnPlanMedioRelacion = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if ((getTxtCorporacion().getText().compareTo("") != 0 && getTxtCorporacion()
					.getText() != null)
					&& (getTxtCliente().getText().compareTo("") != 0 && getTxtCliente()
							.getText() != null) && (campanaIf.getId() != null)) {

				getTextPlanMedioRelacion().setText(null);
				planMedioOriginalIf = null;
				
				cleanDatosPlanMedioSaved();

				cleanTablaOrdenesMedioCmp();
				
				findPlanMedioOriginal();
				
			} else {
				SpiritAlert
						.createAlert(
								"Debe seleccionar los datos Coorporación,Cliente,Campaña",
								SpiritAlert.WARNING);
			}
		}
	};
	
	ActionListener oActionListenerCmbOrdenTrabajo = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			try {
				ordenTrabajoIf = (OrdenTrabajoIf) getCmbOrdenTrabajo()
						.getSelectedItem();

				if (tipoOrden != null && ordenTrabajoIf != null) {

					ArrayList<OrdenTrabajoDetalleIf> ordenesTrabajoDetalle = (ArrayList) SessionServiceLocator
							.getOrdenTrabajoDetalleSessionService()
							.findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajoAndEstado(
									tipoOrden.getId(), ordenTrabajoIf.getId());

					// Cargo los subtipos de acuerdo al tipo y orden de trabajo
					// escogido
					getCmbOrdenTrabajoDetalle().removeAllItems();
					Iterator itOrdenTrabajoDetalleCollection = ordenesTrabajoDetalle
							.iterator();

					while (itOrdenTrabajoDetalleCollection.hasNext()) {
						ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) itOrdenTrabajoDetalleCollection
								.next();

						SubtipoOrdenIf subtipoOrden = SessionServiceLocator
								.getSubtipoOrdenSessionService()
								.getSubtipoOrden(
										ordenTrabajoDetalleIf.getSubtipoId());
						EmpleadoIf empleado = SessionServiceLocator
								.getEmpleadoSessionService().getEmpleado(
										ordenTrabajoDetalleIf.getAsignadoaId());

						InfoOrdenTrabajoDetalle info = new InfoOrdenTrabajoDetalle(
								ordenTrabajoDetalleIf, subtipoOrden.getNombre()
										+ " - "
										+ empleado.getNombres().split(" ")[0]
										+ " "
										+ empleado.getApellidos().split(" ")[0]);

						getCmbOrdenTrabajoDetalle().addItem(info);
					}

					CampanaIf campana = SessionServiceLocator
							.getCampanaSessionService().getCampana(
									ordenTrabajoIf.getCampanaId());
					getTxtCampana().setText(
							campana.getCodigo() + " - " + campana.getNombre());
					// AKI CAMPANA ID
					// ADD 17 JUNIO
					campanaIf = new CampanaData();
					campanaIf = campana;
					// END 17 JUNIO
					cargarTablaComercial();
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}
	};

	ActionListener oActionListenerCmbTipoPeriodo = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			try {
				// PARA TV
				// limpiarSeleccionesTablaComerciales();
				cleanTv();

				int subPeriodoSeleccionado = getCmbTipoPeriodo()
						.getSelectedIndex();
				if (!modelArbolTvColecciones.isEmpty()) {
					if (modelArbolTvColecciones.get(subPeriodoSeleccionado) != null) {
						getArbolTelevision().setModel(
								modelArbolTvColecciones
										.get(subPeriodoSeleccionado));

						DefaultTreeCellRenderer customTreeRenderer = new DefaultTreeCellRenderer();
						customTreeRenderer.setOpenIcon(customOpenIconTv);
						customTreeRenderer.setClosedIcon(customClosedIconTv);
						customTreeRenderer.setLeafIcon(customLeftIconTv);
						getArbolTelevision()
								.setCellRenderer(customTreeRenderer);

						if (treePathsTvColecciones.get(subPeriodoSeleccionado) != null) {
							getArbolTelevision()
									.getCheckBoxTreeSelectionModel()
									.setSelectionPaths(
											treePathsTvColecciones
													.get(subPeriodoSeleccionado));

							getPanelMapaPautaTv().remove(tableScrollTvRemovido);
							getPanelMapaPautaTv()
									.add(tableScrollPaneTvColecciones
											.get(subPeriodoSeleccionado),
											cc.xywh(3, 5, 7, 5));
							tableScrollTvRemovido = tableScrollPaneTvColecciones
									.get(subPeriodoSeleccionado);
						} else {
							getPanelMapaPautaTv().remove(tableScrollTvRemovido);
							cleanArbolMapaTv();
						}
					} else {
						getPanelMapaPautaTv().remove(tableScrollTvRemovido);
						cleanArbolMapaTv();
					}
				}
				getPanelMapaPautaTv().validate();
				getPanelMapaPautaTv().repaint();

				// PARA PRENSA
				/*
				 * limpiarSeleccionesTablaComerciales(); cleanPrensa();
				 * 
				 * if(!treePathsPrensaColecciones.isEmpty()){
				 * if(treePathsPrensaColecciones.get(subPeriodoSeleccionado) !=
				 * null){ getArbolPrensa().getCheckBoxTreeSelectionModel().
				 * setSelectionPaths
				 * (treePathsPrensaColecciones.get(subPeriodoSeleccionado));
				 * 
				 * getPanelMapaPautaPrensa().remove(tableScrollPrensaRemovido);
				 * getPanelMapaPautaPrensa
				 * ().add(tableScrollPanePrensaColecciones
				 * .get(subPeriodoSeleccionado), cc.xywh(3, 3, 5, 5));
				 * tableScrollPrensaRemovido =
				 * tableScrollPanePrensaColecciones.get(subPeriodoSeleccionado);
				 * }else{
				 * getArbolPrensa().getCheckBoxTreeSelectionModel().clearSelection
				 * ();
				 * getPanelMapaPautaPrensa().remove(tableScrollPrensaRemovido);
				 * cleanMapaPrensa(); } } getPanelMapaPautaPrensa().validate();
				 * getPanelMapaPautaPrensa().repaint();
				 */
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	ActionListener oActionListenerBtnAgregarProgramaTv = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (validateFieldsNodoTv()) {
				Pauta pautaExtra = new Pauta();
				String nuevoPrograma = getTxtProgramaTv().getText();
				boolean error = false;
				try {
					// rootArbolTv.removeAllChildren();
					pautaExtra.setCanal(getTxtCanalTv().getText());
					pautaExtra.setPrograma(nuevoPrograma);
					java.util.Date fechaInicio = new java.util.Date();
					String[] horaInicio = getTxtHoraInicioPrograma().getText()
							.split(":");
					int horasInicio = Integer.parseInt(horaInicio[0]);
					int minutosInicio = Integer.parseInt(horaInicio[1]);
					fechaInicio.setHours(horasInicio);
					fechaInicio.setMinutes(minutosInicio);
					pautaExtra.setHoraInicio(fechaInicio);
					java.util.Date fechaFin = new java.util.Date();
					String[] horaFin = getTxtHoraFinPrograma().getText().split(
							":");
					int horasFin = Integer.parseInt(horaFin[0]);
					int minutosFin = Integer.parseInt(horaFin[1]);
					fechaFin.setHours(horasFin);
					fechaFin.setMinutes(minutosFin);
					pautaExtra.setHoraFinal(fechaFin);
					String[] dias = getTxtDiasPrograma().getText().split("-");
					char[] arregloDias = new char[dias.length];
					for (int i = 0; i < dias.length; i++) {
						arregloDias[i] = dias[i].charAt(0);
					}
					pautaExtra.setDias(arregloDias);
					pautaExtra.setRating(Double.parseDouble(getTxtRatingTv()
							.getText()));
					int id = pautasTv.size() + 1;
					pautaExtra.setId(id);
				} catch (Exception e) {
					error = true;
					e.printStackTrace();
					SpiritAlert
							.createAlert(
									"Los datos del programa nuevo no han sido ingresados correctamente",
									SpiritAlert.WARNING);
				}
				if (!error) {
					pautasTv.add(pautaExtra);
					crearColeccionesCanalesProgramas();
					cargarTreeTV();
					searchAndExpand(nuevoPrograma);
				}
			}
		}
	};

	ActionListener oActionListenerBtnEliminarProgramaTv = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) getArbolTelevision()
					.getLastSelectedPathComponent();
			String canal = "";
			String programa = "";
			if (!pautasTv.isEmpty() && node != null && node.getParent() != null) {
				if (((DefaultMutableTreeNode) node.getParent().getParent())
						.isRoot()) {
					DefaultMutableTreeNode nodoPadre = (DefaultMutableTreeNode) node
							.getParent();
					canal = (String) nodoPadre.getUserObject();
					programa = (String) node.getUserObject();
				} else if (node.getUserObject() instanceof ComercialIf) {
					DefaultMutableTreeNode nodoAbuelo = (DefaultMutableTreeNode) node
							.getParent().getParent();
					DefaultMutableTreeNode nodoPadre = (DefaultMutableTreeNode) node
							.getParent();
					canal = (String) nodoAbuelo.getUserObject();
					programa = (String) nodoPadre.getUserObject();
				}

				int pautaEliminada = -1;
				for (int i = 0; i < pautasTv.size(); i++) {
					if ((pautasTv.get(i).getCanal().toUpperCase().equals(canal))
							&& (pautasTv.get(i).getPrograma().toUpperCase()
									.equals(programa))) {
						pautaEliminada = i;
					}
				}
				if (pautaEliminada != -1) {
					pautasTv.remove(pautaEliminada);
					crearColeccionesCanalesProgramas();
					cargarTreeTV();
					cleanTv();
				}
			} else {
				SpiritAlert
						.createAlert(
								"Debe seleccionar primero el Programa que desea Eliminar",
								SpiritAlert.WARNING);
			}
		}
	};

	ActionListener oActionListenerBtnGuayaquil = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	};

	public boolean searchAndExpand(String text) {
		TreeNode[] path = search((DefaultMutableTreeNode) getArbolTelevision()
				.getModel().getRoot(), text);

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
			path = ((DefaultTreeModel) getArbolTelevision().getModel())
					.getPathToRoot(node);
		} else {
			int i = 0;
			int n = ((DefaultTreeModel) getArbolTelevision().getModel())
					.getChildCount(node);

			while ((i < n) && (path == null)) {
				path = search(
						(DefaultMutableTreeNode) ((DefaultTreeModel) getArbolTelevision()
								.getModel()).getChild(node, i), object);
				i++;
			}
		}

		return path;
	}

	private void listenerCheckBoxTreeTv() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) getArbolTelevision()
				.getLastSelectedPathComponent();
		String canal = "";
		String programa = "";
		ComercialIf comercial = null;
		if (node != null) {
			if (node.getParent() != null) {
				if (((DefaultMutableTreeNode) node.getParent()).isRoot()) {
					cleanTv();
					canal = (String) node.getUserObject();
					getTxtCanalTv().setText(canal.toUpperCase());
				} else if (((DefaultMutableTreeNode) node.getParent()
						.getParent()).isRoot()) {
					cleanTv();
					DefaultMutableTreeNode nodoPadre = (DefaultMutableTreeNode) node
							.getParent();
					canal = (String) nodoPadre.getUserObject();
					programa = (String) node.getUserObject();
					comercial = null;
					setearInfoNodosTv(canal, programa, comercial);
				} else if (node.getUserObject() instanceof ComercialIf) {
					cleanTv();
					comercial = (ComercialIf) node.getUserObject();
					DefaultMutableTreeNode nodoAbuelo = (DefaultMutableTreeNode) node
							.getParent().getParent();
					DefaultMutableTreeNode nodoPadre = (DefaultMutableTreeNode) node
							.getParent();
					canal = (String) nodoAbuelo.getUserObject();
					programa = (String) nodoPadre.getUserObject();
					setearInfoNodosTv(canal, programa, comercial);
				}
			} else {
				cleanTv();
			}
		} else {
			cleanTv();
		}
	}

	private void setearInfoNodosTv(String canal, String programa,
			ComercialIf comercial) {
		getTxtCanalTv().setText(canal.toUpperCase());
		getTxtProgramaTv().setText(programa);
		Pauta pauta = null;
		int subPeriodoSeleccionado = getCmbTipoPeriodo().getSelectedIndex();
		pautasTv = pautasTvColecciones.get(subPeriodoSeleccionado);
		for (int i = 0; i < pautasTv.size(); i++) {
			if ((pautasTv.get(i).getCanal().toUpperCase().equals(canal))
					&& (pautasTv.get(i).getPrograma().toUpperCase()
							.equals(programa))) {
				pauta = pautasTv.get(i);
			}
		}
		if (pauta != null) {
			getTxtHoraInicioPrograma().setText(
					generarHora(pauta.getHoraInicio()));
			getTxtHoraInicioPrograma().repaint();
			getTxtHoraFinPrograma().setText(generarHora(pauta.getHoraFinal()));
			getTxtHoraFinPrograma().repaint();
			char[] arregloDias = pauta.getDias();
			String dias = "";
			for (int i = 0; i < arregloDias.length; i++) {
				dias = dias + "-" + arregloDias[i];
			}
			getTxtDiasPrograma().setText(dias.substring(1));
			getTxtDiasPrograma().repaint();
			getTxtRatingTv().setText(String.valueOf(pauta.getRating()));
			getTxtRatingTv().repaint();
		}
		if (comercial != null) {
			getTxtComercialTv().setText(
					comercial.getNombre() + " (" + comercial.getDuracion()
							+ " seg)");
			DerechoProgramaIf derechoPrograma = mapaDerechoPrograma
					.get(comercial.getDerechoprogramaId());
			getTxtDerechoPrograma().setText(derechoPrograma.getNombre());
			getTxtVersionPrograma().setText(comercial.getVersion());
		}
	}

	public String generarHora(java.util.Date fecha) {
		String hora = "";

		String horas = String.valueOf(fecha.getHours());
		if (horas.length() == 1) {
			horas = "0" + horas;
		}
		String minutos = String.valueOf(fecha.getMinutes());
		if (minutos.length() == 1) {
			minutos = "0" + minutos;
		}
		hora = horas + ":" + minutos;

		return hora;
	}

	ActionListener oActionListenerBtnTvData = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (!planMedioMesVector.isEmpty()) {
				cargarVectorComercialesSeleccionados();
				int subPeriodoSeleccionado = getCmbTipoPeriodo()
						.getSelectedIndex();
				if (!comercialesSeleccionadosTabla.isEmpty()) {

					pautasTv = new Vector<Pauta>();
					pautasTv = PautaImporter.retornarPautas();

					String nombreHoja = "Pauta";
					try {
						ManejarExcel manejarExcel = new ManejarExcel();

						String tipoMedio = "TV";
						if (getCbPautaRadio().isSelected()) {
							tipoMedio = "RADIO";
						} else if (getCbPautaCine().isSelected()) {
							tipoMedio = "CINE";
						} else {
							tipoMedio = "TV";
						}

						setearGenericoPautaRegular();
						Collection<DatoObservacion> observaciones = manejarExcel
								.convertirInformacionDesdeExcel(nombreHoja,
										planMedioDetallesPlantilla,
										mapasComercialesPlantilla, null, null,
										tipoMedio, genericoPautaRegular);

						if (observaciones.size() > 0) {
							new ObservacionesModel(Parametros.getMainFrame(),
									"Observaciones", observaciones);
						}

						int statusManejarExcel = manejarExcel.getStatus();
						if (statusManejarExcel == 0) {

							if (subPeriodoSeleccionado != -1) {
								pautasTvColecciones.set(subPeriodoSeleccionado,
										pautasTv);
							}

							if (pautasTv != null) {
								crearColeccionesCanalesProgramas();
								cleanArbolMapaTv();
								cargarTreeTV();
								getBtnAgregarProgramaTv().setEnabled(true);
							} else {
								pautasTv = new Vector<Pauta>();
								getBtnAgregarProgramaTv().setEnabled(false);
							}

							// ADD 26 ABRIL
							// PARA ELIMINAR LOS DATOS QUE CARGA EL EXCEL
							// Obtener los datos del PlanMedioMes hasta ahora
							// solo hay uno x eso es estatico con 0
							String tipoPlanMedioMesIf = null;
							String subPeriodoFechaInicioBeforeXls = null;
							String subPeriodoFechaFinBeforeXls = null;
							// 27 ABRIL
							Timestamp fechaInicioPlanMedioMesIfTemp = null;
							Timestamp fechaFinPlanMedioMesIfTemp = null;
							// 27 ABRIL

							// obtengo los datos actuales del PlanMedioMes antes
							// de q sean seteados
							// con los datos del excel cargado
							if (getPlanMedioMesVector().size() > 0) {
								PlanMedioMesIf planMedioMesIfTemp = getPlanMedioMesVector()
										.get(0);

								tipoPlanMedioMesIf = planMedioMesIfTemp
										.getTipo();
								fechaInicioPlanMedioMesIfTemp = planMedioMesIfTemp
										.getFechaInicio();
								fechaFinPlanMedioMesIfTemp = planMedioMesIfTemp
										.getFechaFin();

								subPeriodoFechaInicioBeforeXls = Utilitarios
										.getFechaUppercase(fechaInicioPlanMedioMesIfTemp);
								subPeriodoFechaFinBeforeXls = Utilitarios
										.getFechaUppercase(fechaFinPlanMedioMesIfTemp);
							}
							// datos de la tabla SubPeriodo de Planificacion
							if (tableSubPeriodo.getRowCount() > 0) {
								int cont = 0;
								for (int i = 0; i < tableSubPeriodo
										.getRowCount(); i++) {

									String tipoTabla = (String) tableSubPeriodo
											.getValueAt(i, 0);

									if (getCmbTipo().getSelectedItem().equals(
											NOMBRE_TIPO_EXPECTATIVA))
										tipoTabla = TIPO_EXPECTATIVA;
									else if (getCmbTipo().getSelectedItem()
											.equals(NOMBRE_TIPO_LANZAMIENTO))
										tipoTabla = TIPO_LANZAMIENTO;
									else if (getCmbTipo().getSelectedItem()
											.equals(NOMBRE_TIPO_MANTENIMIENTO))
										tipoTabla = TIPO_MANTENIMIENTO;
									else if (getCmbTipo().getSelectedItem()
											.equals(NOMBRE_TIPO_PROMOCIONAL))
										tipoTabla = TIPO_PROMOCIONAL;

									// String tipoTabla = (String)
									// tableSubPeriodo.getValueAt(i, 0);

									String subPeriodoFechaInicioTabla = (String) tableSubPeriodo
											.getValueAt(i, 1);
									String subPeriodoFechaFinTabla = (String) tableSubPeriodo
											.getValueAt(i, 2);

									/*
									 * if (tipoPlanMedioMesIf.equals(tipoTabla)
									 * && subPeriodoFechaInicioBeforeXls.equals(
									 * subPeriodoFechaInicioTabla) &&
									 * subPeriodoFechaFinBeforeXls
									 * .equals(subPeriodoFechaFinTabla)){
									 * cont++; }
									 */
									if (tipoPlanMedioMesIf.equals(tipoTabla)
											&& subPeriodoFechaInicioBeforeXls
													.equals(subPeriodoFechaInicioTabla)
											&& subPeriodoFechaFinBeforeXls
													.equals(subPeriodoFechaFinTabla)) {
										cont++;
									}
									if (cont >= 1) {
										// se remueve primero del CmbTipoPeriodo
										// y de la tabla para actualizarlos
										// getCmbTipoPeriodo().removeItemAt(i);
										// tableSubPeriodo.removeRow(i);

										// 27 ABRIL
										PlanMedioMesIf bean = new PlanMedioMesData();
										setPlanMedioMesActualizadoIf(bean);
										getPlanMedioMesActualizadoIf().setTipo(
												tipoPlanMedioMesIf);
										getPlanMedioMesActualizadoIf()
												.setFechaInicio(
														fechaInicioPlanMedioMesIfTemp);
										getPlanMedioMesActualizadoIf()
												.setFechaFin(
														fechaFinPlanMedioMesIfTemp);
										// 27 ABRIL

										PlanMedioMesIf planMedioMesDelete = getPlanMedioMesVector()
												.get(i);
										Timestamp timeStampDateInicio = planMedioMesDelete
												.getFechaInicio();
										Timestamp timeStampDateFin = planMedioMesDelete
												.getFechaFin();
										// seteamos los valores hora, minutos y
										// segundos en la cero hora
										timeStampDateInicio = Utilitarios
												.resetTimestampStartDate(timeStampDateInicio);
										timeStampDateFin = Utilitarios
												.resetTimestampStartDate(timeStampDateFin);

										java.util.Date fechaInicioTime = new java.util.Date(
												timeStampDateInicio.getTime());
										java.util.Date fechaFinTime = new java.util.Date(
												timeStampDateInicio.getTime());
									}
								}
							}

							// cambios en periodo y subperiodo del Plan de Medio
							// TV
							java.util.Date fechaInicio = manejarExcel
									.getFechaMenor();
							java.util.Date fechaFin = manejarExcel
									.getFechaMayor();

							Calendar calendarInicioHelp = new GregorianCalendar();
							calendarInicioHelp.setTime(fechaInicio);
							Calendar calendarFinHelp = new GregorianCalendar();
							calendarFinHelp.setTime(fechaFin);
							// calendarFechaInicio.setTime(fechaInicio);
							// calendarFechaFin.setTime(fechaFin);

							getCmbPeriodoFechaInicio().setCalendar(
									calendarInicioHelp);
							getCmbPeriodoFechaFin()
									.setCalendar(calendarFinHelp);

							// AHORA SE AGREGA LOS DATOS DE PLANIFICACION Y
							// MEDIOS CON LAS FECHAS DEL EXCEL CARGADO
							// LUEGO DE HABER BORRADO LOS ANTERIORES
							if (getPlanMedioMesVector().size() < 1) {
								// END 25 ABRIL
								PlanMedioMesIf bean = new PlanMedioMesData();
								setPlanMedioMesActualizadoIf(bean);

								if (getCmbTipo().getSelectedItem().equals(
										NOMBRE_TIPO_EXPECTATIVA))
									getPlanMedioMesActualizadoIf().setTipo(
											TIPO_EXPECTATIVA);
								else if (getCmbTipo().getSelectedItem().equals(
										NOMBRE_TIPO_LANZAMIENTO))
									getPlanMedioMesActualizadoIf().setTipo(
											TIPO_LANZAMIENTO);
								else if (getCmbTipo().getSelectedItem().equals(
										NOMBRE_TIPO_MANTENIMIENTO))
									getPlanMedioMesActualizadoIf().setTipo(
											TIPO_MANTENIMIENTO);
								else if (getCmbTipo().getSelectedItem().equals(
										NOMBRE_TIPO_PROMOCIONAL))
									getPlanMedioMesActualizadoIf().setTipo(
											TIPO_PROMOCIONAL);

								getPlanMedioMesActualizadoIf()
										.setFechaInicio(
												Utilitarios
														.fromSqlDateToTimestamp(new java.sql.Date(
																getCmbSubPeriodoFechaInicio()
																		.getDate()
																		.getTime())));
								getPlanMedioMesActualizadoIf()
										.setFechaFin(
												Utilitarios
														.fromSqlDateToTimestamp(new java.sql.Date(
																getCmbSubPeriodoFechaFin()
																		.getDate()
																		.getTime())));

								getPlanMedioMesVector().add(
										getPlanMedioMesActualizadoIf());

								// ADD 25 ABRIL
								planMedioMesArray
										.add(getPlanMedioMesActualizadoIf());
								// END 25 ABRIL

								tableSubPeriodo = (DefaultTableModel) getTblSubPeriodo()
										.getModel();
								Vector<String> fila = new Vector<String>();
								String tipo = getCmbTipo().getSelectedItem()
										.toString();
								String subPeriodoFechaInicio = Utilitarios
										.getFechaUppercase(getCmbSubPeriodoFechaInicio()
												.getDate());
								String subPeriodoFechaFin = Utilitarios
										.getFechaUppercase(getCmbSubPeriodoFechaFin()
												.getDate());
								fila.add(tipo);
								fila.add(subPeriodoFechaInicio);
								fila.add(subPeriodoFechaFin);
								tableSubPeriodo.addRow(fila);

								getCmbTipoPeriodo().addItem(
										tipo + " (" + subPeriodoFechaInicio
												+ " al " + subPeriodoFechaFin
												+ ")");

							}

							else {
								int filaSeleccionada = 0;// getTblSubPeriodo().getSelectedRow();
								if (filaSeleccionada >= 0) {/*
															 * if(getCmbTipo().
															 * getSelectedItem
															 * ().equals(
															 * NOMBRE_TIPO_EXPECTATIVA
															 * ))
															 * getPlanMedioMesActualizadoIf
															 * ().setTipo(
															 * TIPO_EXPECTATIVA
															 * ); else
															 * if(getCmbTipo
															 * ().getSelectedItem
															 * ().equals(
															 * NOMBRE_TIPO_LANZAMIENTO
															 * ))
															 * getPlanMedioMesActualizadoIf
															 * ().setTipo(
															 * TIPO_LANZAMIENTO
															 * ); else
															 * if(getCmbTipo
															 * ().getSelectedItem
															 * ().equals(
															 * NOMBRE_TIPO_MANTENIMIENTO
															 * ))
															 * getPlanMedioMesActualizadoIf
															 * ().setTipo(
															 * TIPO_MANTENIMIENTO
															 * ); else
															 * if(getCmbTipo
															 * ().getSelectedItem
															 * ().equals(
															 * NOMBRE_TIPO_PROMOCIONAL
															 * ))
															 * getPlanMedioMesActualizadoIf
															 * ().setTipo(
															 * TIPO_PROMOCIONAL
															 * );
															 */

									// ADD 25 ABRIL
									/*
									 * 28 abril PlanMedioMesIf
									 * planMedioMesSeleccionado =
									 * getPlanMedioMesVector
									 * ().get(filaSeleccionada); boolean
									 * isPlanMedioMesInListTemp = false;
									 * if(planMedioMesArray.size()>0){ int
									 * contadorTemp = 0; for(int i = 0; i <
									 * planMedioMesArray.size(); i++){
									 * PlanMedioMesIf planMedioMesActualizarTemp
									 * = (PlanMedioMesIf)
									 * planMedioMesArray.get(i); //Si el
									 * comercial a eliminar se encuentra en la
									 * lista temporal if
									 * (planMedioMesSeleccionado
									 * .getTipo().equals
									 * (planMedioMesActualizarTemp.getTipo()) &&
									 * planMedioMesSeleccionado
									 * .getFechaInicio().
									 * equals(planMedioMesActualizarTemp
									 * .getFechaInicio()) &&
									 * planMedioMesSeleccionado
									 * .getFechaFin().equals
									 * (planMedioMesActualizarTemp
									 * .getFechaFin())){ contadorTemp++; }if
									 * (contadorTemp >= 1){
									 * isPlanMedioMesInListTemp = true;
									 * planMedioMesArray.remove(i); break; } } }
									 */// END 25 ABRIL 28 abril

									getPlanMedioMesActualizadoIf()
											.setFechaInicio(
													Utilitarios
															.fromSqlDateToTimestamp(new java.sql.Date(
																	getCmbSubPeriodoFechaInicio()
																			.getDate()
																			.getTime())));
									getPlanMedioMesActualizadoIf()
											.setFechaFin(
													Utilitarios
															.fromSqlDateToTimestamp(new java.sql.Date(
																	getCmbSubPeriodoFechaFin()
																			.getDate()
																			.getTime())));

									// getPlanMedioMesVector().add(filaSeleccionada,
									// getPlanMedioMesActualizadoIf());
									// getPlanMedioMesVector().remove(filaSeleccionada
									// + 1);
									getPlanMedioMesVector().get(0).setTipo(
											getPlanMedioMesActualizadoIf()
													.getTipo());
									getPlanMedioMesVector()
											.get(0)
											.setFechaInicio(
													getPlanMedioMesActualizadoIf()
															.getFechaInicio());
									getPlanMedioMesVector().get(0).setFechaFin(
											getPlanMedioMesActualizadoIf()
													.getFechaFin());

									// ADD 25 ABRIL
									// planMedioMesArray.add(getPlanMedioMesActualizadoIf());
									// 28 abril
									// END 25 ABRIL

									tableSubPeriodo = (DefaultTableModel) getTblSubPeriodo()
											.getModel();
									Vector<String> fila = new Vector<String>();
									String tipo = getCmbTipo()
											.getSelectedItem().toString();
									String subPeriodoFechaInicio = Utilitarios
											.getFechaUppercase(getCmbSubPeriodoFechaInicio()
													.getDate());
									String subPeriodoFechaFin = Utilitarios
											.getFechaUppercase(getCmbSubPeriodoFechaFin()
													.getDate());
									fila.add(tipo);
									fila.add(subPeriodoFechaInicio);
									fila.add(subPeriodoFechaFin);
									tableSubPeriodo.insertRow(filaSeleccionada,
											fila);
									tableSubPeriodo
											.removeRow(filaSeleccionada + 1);

									getCmbTipoPeriodo().removeItemAt(
											filaSeleccionada);
									getCmbTipoPeriodo().insertItemAt(
											tipo + " (" + subPeriodoFechaInicio
													+ " al "
													+ subPeriodoFechaFin + ")",
											filaSeleccionada);
									getCmbTipoPeriodo().setSelectedItem(
											tipo + " (" + subPeriodoFechaInicio
													+ " al "
													+ subPeriodoFechaFin + ")");
									// getCmbTipoPeriodo().removeItemAt(filaSeleccionada
									// + 1);
									// getCmbTipoPeriodo().repaint();
									// getCmbTipoPeriodoMapa().insertItemAt(tipo
									// + " (" + subPeriodoFechaInicio + " al " +
									// subPeriodoFechaFin + ")" ,
									// filaSeleccionada);
									// getCmbTipoPeriodoMapa().removeItemAt(filaSeleccionada
									// + 1);
								} else {
									SpiritAlert
											.createAlert(
													"Debe seleccionar una fila para ser actualizada !",
													SpiritAlert.WARNING);
								}
							}
							// ADD 26 ABRIL
							// else{
							// SpiritAlert.createAlert("Actualmente solo se permite una Planificación!!!",
							// SpiritAlert.INFORMATION);
							// }//END 26 ABRIL
							// ADD 26 ABRIL

						}// END IF DE VALIDAR EXCEL

					} catch (GenericBusinessException e) {
						// ADD 24 AGOSTO
						e.printStackTrace();
						// SpiritAlert.createAlert("El Archivo Excel cargado no cumple con el formato de un Plan de Medios",SpiritAlert.WARNING);
						SpiritAlert.createAlert(e.getMessage(),
								SpiritAlert.WARNING);
					} catch (OfficeXmlFileException e) {
						// ADD 24 AGOSTO
						e.printStackTrace();
						SpiritAlert
								.createAlert(
										"El Archivo Excel cargado no es de extesnsión .xls",
										SpiritAlert.WARNING);
					} catch (Exception e) {
						// ADD 24 AGOSTO
						e.printStackTrace();
						SpiritAlert.createAlert(
								"Problemas con el Archivo Excel cargado",
								SpiritAlert.WARNING);
					}
				} else {
					SpiritAlert
							.createAlert(
									"Antes de cargar el archivo, debe seleccionar al menos 1 comercial",
									SpiritAlert.INFORMATION);
					getTblComercial().grabFocus();
				}
			} else {
				SpiritAlert
						.createAlert(
								"Antes de generar el Mapa de Pauta, al menos ingrese 1 subPeriodo en Planificación",
								SpiritAlert.INFORMATION);
			}
		}
	};

	private void listenerCheckBoxTreePrensa() {
		try {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) getArbolPrensa()
					.getLastSelectedPathComponent();
			ClienteIf cliente = null;
			PrensaTarifaIf prensaTarifa = null;
			PrensaFormatoIf prensaFormato = null;
			PrensaUbicacionIf prensaUbicacion = null;
			PrensaSeccionIf prensaSeccion = null;
			if (node != null) {
				if (node.getUserObject() instanceof ClienteIf) {
					cleanPrensa();
					cliente = (ClienteIf) node.getUserObject();
					getTxtDiario().setText(cliente.getNombreLegal());
				} else if (node.getUserObject() instanceof PrensaSeccionIf) {
					cleanPrensa();
					prensaSeccion = (PrensaSeccionIf) node.getUserObject();
					cliente = SessionServiceLocator.getClienteSessionService()
							.getCliente(prensaSeccion.getClienteId());
					getTxtDiario().setText(cliente.getNombreLegal());
					getTxtSeccion().setText(prensaSeccion.getSeccion());
				} else if (node.getUserObject() instanceof PrensaUbicacionIf) {
					cleanPrensa();
					prensaUbicacion = (PrensaUbicacionIf) node.getUserObject();
					prensaSeccion = (PrensaSeccionIf) ((DefaultMutableTreeNode) node
							.getParent()).getUserObject();
					cliente = SessionServiceLocator.getClienteSessionService()
							.getCliente(prensaUbicacion.getClienteId());
					getTxtDiario().setText(cliente.getNombreLegal());
					getTxtSeccion().setText(prensaSeccion.getSeccion());
					getTxtUbicacion().setText(prensaUbicacion.getUbicacion());
				} else if (node.getUserObject() instanceof PrensaFormatoIf) {
					cleanPrensa();
					prensaFormato = (PrensaFormatoIf) node.getUserObject();
					prensaUbicacion = (PrensaUbicacionIf) ((DefaultMutableTreeNode) node
							.getParent()).getUserObject();
					prensaSeccion = (PrensaSeccionIf) ((DefaultMutableTreeNode) node
							.getParent().getParent()).getUserObject();
					cliente = SessionServiceLocator.getClienteSessionService()
							.getCliente(prensaUbicacion.getClienteId());
					prensaTarifa = null;
					setearInfoNodosPrensa(prensaTarifa, prensaFormato,
							prensaUbicacion, prensaSeccion, cliente);
				} else if (node.getUserObject() instanceof PrensaTarifaIf) {
					cleanPrensa();
					prensaTarifa = (PrensaTarifaIf) node.getUserObject();
					prensaFormato = (PrensaFormatoIf) ((DefaultMutableTreeNode) node
							.getParent()).getUserObject();
					prensaUbicacion = (PrensaUbicacionIf) ((DefaultMutableTreeNode) node
							.getParent().getParent()).getUserObject();
					prensaSeccion = (PrensaSeccionIf) ((DefaultMutableTreeNode) node
							.getParent().getParent().getParent())
							.getUserObject();
					cliente = SessionServiceLocator.getClienteSessionService()
							.getCliente(prensaUbicacion.getClienteId());
					setearInfoNodosPrensa(prensaTarifa, prensaFormato,
							prensaUbicacion, prensaSeccion, cliente);
				} else {
					cleanPrensa();
				}
			} else {
				cleanPrensa();
			}
		} catch (GenericBusinessException e1) {
			e1.printStackTrace();
			SpiritAlert.createAlert("Error en el listener del árbol de prensa",
					SpiritAlert.ERROR);
		}
	}

	private void setearInfoNodosPrensa(PrensaTarifaIf prensaTarifa,
			PrensaFormatoIf prensaFormato, PrensaUbicacionIf prensaUbicacion,
			PrensaSeccionIf prensaSeccion, ClienteIf cliente) {
		getTxtDiario().setText(cliente.getNombreLegal());
		getTxtSeccion().setText(prensaSeccion.getSeccion());
		getTxtUbicacion().setText(prensaUbicacion.getUbicacion());
		getTxtFormato().setText(prensaFormato.getFormato());
		getTxtAnchoColumnas().setText(
				prensaFormato.getAnchoColumnas().toString());
		if (prensaFormato.getAltoModulos() != null) {
			getTxtAltoModulos().setText(
					prensaFormato.getAltoModulos().toString());
		}
		getTxtAnchoCm().setText(prensaFormato.getAnchoCm().toString());
		getTxtAltoCm().setText(prensaFormato.getAltoCm().toString());
		String color = "", dia = "";
		if (prensaTarifa != null) {
			if (prensaTarifa.getColor().equals(COLOR_COLOR)) {
				color = NOMBRE_COLOR_COLOR;
			} else if (prensaTarifa.getColor().equals(COLOR_BN)) {
				color = NOMBRE_COLOR_BN;
			}
			getTxtColor().setText(color);
			if (prensaTarifa.getDia().equals(DIA_DOMINGO)) {
				dia = NOMBRE_DIA_DOMINGO;
			} else if (prensaTarifa.getDia().equals(DIA_ORDINARIO)) {
				dia = NOMBRE_DIA_ORDINARIO;
			} else if (prensaTarifa.getDia().equals(DIA_SABADO)) {
				dia = NOMBRE_DIA_SABADO;
			} else if (prensaTarifa.getDia().equals(DIA_FERIADO)) {
				dia = NOMBRE_DIA_FERIADO;
			}
			getTxtDia().setText(dia);
			getTxtTarifa().setText(
					formatoDecimal.format(Utilitarios
							.redondeoValor(prensaTarifa.getTarifa())));
		}
	}

	ActionListener oActionListenerBtnCrearMapaPautaPrensa = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (!planMedioMesVector.isEmpty()) {
				TreePath[] treePaths = getArbolPrensa()
						.getCheckBoxTreeSelectionModel().getSelectionPaths();

				int subPeriodoSeleccionado = getCmbTipoPeriodo()
						.getSelectedIndex();
				if (subPeriodoSeleccionado != -1) {
					treePathsPrensaColecciones.set(subPeriodoSeleccionado,
							treePaths);
				}

				if (treePaths != null) {
					generarPrensaTarifaColeccion();
					Vector<String> diasSubPeriodoColeccion = getDiasPeriodoColeccion();

					cabecerasFijasPrensa = new String[diasSubPeriodoColeccion
							.size() + 5];
					cabecerasFijasPrensa[0] = "#";
					cabecerasFijasPrensa[1] = "DIARIO";
					cabecerasFijasPrensa[2] = "SECCION";
					cabecerasFijasPrensa[3] = "UBICACION";
					cabecerasFijasPrensa[4] = "FORMATO";
					for (int i = 0; i < diasSubPeriodoColeccion.size(); i++) {
						cabecerasFijasPrensa[i + 5] = diasSubPeriodoColeccion
								.get(i);
					}

					for (int i = 0; i < (diasSubPeriodoColeccion.size() + 5); i++) {
						System.out.println("cabecerasFijas" + i + ": "
								+ cabecerasFijasPrensa[i]);
					}

					cabecerasVariablesPrensa = new String[diasSubPeriodoColeccion
							.size() - 1];
					for (int i = 0; i < (diasSubPeriodoColeccion.size() - 1); i++) {
						cabecerasVariablesPrensa[i] = diasSubPeriodoColeccion
								.get(i);
					}

					Vector<Object[]> arregloDatosPrensa = generarArregloDatosPrensa(diasSubPeriodoColeccion);
					
					// getPanelMapaPautaPrensa().remove(scrollPanelMapaPrensa.getTable());
					numeroFilasPrensa = arregloDatosPrensa.size();
					scrollPanelMapaPrensa = new TableScrollPaneMapaPauta(
							cabecerasFijasPrensa, cabecerasVariablesPrensa,
							datosPrensa, numeroFilasPrensa);
					setearPreferenciasMapaPautaPrensa();

					if (subPeriodoSeleccionado != -1) {
						tableScrollPanePrensaColecciones.set(
								subPeriodoSeleccionado,
								scrollPanelMapaPrensa.getTable());
					}

					// getPanelMapaPautaPrensa().add(scrollPanelMapaPrensa.getTable(),
					// cc.xywh(3, 3, 5, 5));
					tableScrollPrensaRemovido = scrollPanelMapaPrensa
							.getTable();

					getJtpPlanMedio().setSelectedIndex(3);
					getTpMapasPauta().setSelectedIndex(2);
				} else {
					SpiritAlert
							.createAlert(
									"Debe seleccionar al menos 1 tarifa para crear el mapa de pauta",
									SpiritAlert.INFORMATION);
				}
			} else {
				SpiritAlert
						.createAlert(
								"Antes de generar el Mapa de Pauta, al menos ingrese 1 subPeriodo en Planificación",
								SpiritAlert.INFORMATION);
			}
		}
	};

	// AKI SE LLENAN LOS DATOS DE MAPAS DE PAUTA TELEVISION
	private Vector<Object[]> generarArregloDatosTv(
			Vector<String> diasPeriodoColeccion) {
		Vector<Object[]> arregloDatosTv = new Vector<Object[]>();
		Object[] datoTv;
		int dia = 1;
		Map diaFrecuencia = new HashMap();

		try {
			// Collection<PlanMedioDetalleIf> planMedioDetallesPlantilla
			Iterator detallesPlantillaIt = planMedioDetallesPlantilla
					.iterator();

			while (detallesPlantillaIt.hasNext()) {
				PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) detallesPlantillaIt
						.next();

				datoTv = new Object[diasPeriodoColeccion.size() + 5];
				datoTv[0] = null;
				datoTv[1] = planMedioDetalleIf.getHoraInicio();
				datoTv[2] = mapaClienteOficina.get(
						planMedioDetalleIf.getProveedorId()).getDescripcion();
				datoTv[3] = planMedioDetalleIf.getPrograma();
				// MODIFIED 20 ABRIL
				/*
				 * if (getMode() == SpiritMode.UPDATE_MODE){ ComercialIf
				 * comercialIfTemp =
				 * SessionServiceLocator.getComercialSessionService
				 * ().getComercial(planMedioDetalleIf.getComercialId());
				 * datoTv[4] = comercialIfTemp.getVersion()+","+
				 * planMedioDetalleIf.getComercial(); }else datoTv[4] =
				 * planMedioDetalleIf.getComercial();
				 */
				// END MODIFIED 20 ABRIL
				datoTv[4] = planMedioDetalleIf.getComercial();/**/

				// HashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>>
				// mapasComercialesPlantilla
				if (mapasComercialesPlantilla.size() > 0) {
					Collection<MapaComercialIf> mapasComerciales = mapasComercialesPlantilla
							.get(planMedioDetalleIf);
					Iterator mapasComercialesIt = mapasComerciales.iterator();

					while (mapasComercialesIt.hasNext()) {
						MapaComercialIf mapaComercialIf = (MapaComercialIf) mapasComercialesIt
								.next();
						diaFrecuencia.put(mapaComercialIf.getFecha().getDate(),
								mapaComercialIf.getFrecuencia().toString());
					}
				} else {
					// Map<PlanMedioDetalleIf,
					// Collection<InfoComercialMultiple>>
					// mapasComercialesPlantillaMultiple
					Collection<InfoComercialMultiple> mapasComerciales = mapasComercialesPlantillaMultiple
							.get(planMedioDetalleIf);
					Iterator<InfoComercialMultiple> mapasComercialesIt = mapasComerciales
							.iterator();

					while (mapasComercialesIt.hasNext()) {
						InfoComercialMultiple mapaComercialIf = (InfoComercialMultiple) mapasComercialesIt
								.next();
						diaFrecuencia.put(mapaComercialIf.getFecha().getDate(),
								mapaComercialIf.getFrecuencia().iterator()
										.next().toString());
					}
				}

				for (int i = 0; i < (diasPeriodoColeccion.size() - 1); i++) {
					if (diaFrecuencia.get(i + 1) != null) {
						datoTv[i + 5] = diaFrecuencia.get(i + 1);
					} else {
						datoTv[i + 5] = "";
					}
				}

				diaFrecuencia.clear();
				arregloDatosTv.add(datoTv);
			}

			// datosTv contiene cada linea del excel, hora inicio, descripcion o
			// cliente, programa, comercial, frecuencia,
			datosTv = new Object[arregloDatosTv.size()][];
			for (int i = 0; i < arregloDatosTv.size(); i++) {
				datosTv[i] = arregloDatosTv.get(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert(
					"Error al generar el arreglo de datos de Tv",
					SpiritAlert.ERROR);
		}
		// datosTv contiene cada linea del excel, hora inicio, descripcion o
		// cliente, programa, comercial, frecuencia,
		return arregloDatosTv;
	}

	// AKI SE LLENA EL TGRP DE LA SECCION MAPAS DE PAUTA
	private void generarArregloDatosTGRP() {
		Object[] datoTGRP;
		Vector<Object[]> arregloDatosTGRP = new Vector<Object[]>();
		int contador = 0;
		totalSumaTotal = new BigDecimal(0);
		totalSumaTotalSinIVA = new BigDecimal(0);
		totalSumaTotalConIVA = new BigDecimal(0);
		double totalSinDescuento = 0D;

		try {
			Iterator detallesPlantillaIt = planMedioDetallesPlantilla
					.iterator();

			while (detallesPlantillaIt.hasNext()) {
				PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) detallesPlantillaIt
						.next();

				contador++;

				datoTGRP = new Object[14];
				datoTGRP[0] = contador;
				datoTGRP[1] = planMedioDetalleIf.getHoraInicio();
				datoTGRP[2] = mapaClienteOficina.get(
						planMedioDetalleIf.getProveedorId()).getDescripcion();
				datoTGRP[3] = planMedioDetalleIf.getPrograma();
				// MODIFIED 20 ABRIL
				/*
				 * if (getMode() == SpiritMode.UPDATE_MODE){ ComercialIf
				 * comercialIfTemp =
				 * SessionServiceLocator.getComercialSessionService
				 * ().getComercial(planMedioDetalleIf.getComercialId());
				 * datoTGRP[4] = comercialIfTemp.getVersion()+","+
				 * planMedioDetalleIf.getComercial(); }else datoTGRP[4] =
				 * planMedioDetalleIf.getComercial();
				 */
				// END MODIFIED
				datoTGRP[4] = planMedioDetalleIf.getComercial();/**/

				datoTGRP[5] = planMedioDetalleIf.getTotalCunias();
				datoTGRP[6] = planMedioDetalleIf.getRaiting1();
				datoTGRP[7] = planMedioDetalleIf.getRaiting2();

				datoTGRP[8] = planMedioDetalleIf.getRaitingPonderado();
				datoTGRP[9] = Utilitarios.redondeoValor(planMedioDetalleIf
						.getTotalCunias().doubleValue()
						* planMedioDetalleIf.getRaitingPonderado()
								.doubleValue());
				datoTGRP[10] = planMedioDetalleIf.getValorTarifa();

				totalSinDescuento = planMedioDetalleIf.getTotalCunias()
						.doubleValue()
						* planMedioDetalleIf.getValorTarifa().doubleValue();
				datoTGRP[11] = Utilitarios.redondeoValor(totalSinDescuento);
				datoTGRP[12] = Utilitarios.redondeoValor(planMedioDetalleIf
						.getTotalCunias().doubleValue()
						* planMedioDetalleIf.getRaiting1().doubleValue());
				datoTGRP[13] = Utilitarios.redondeoValor(planMedioDetalleIf
						.getTotalCunias().doubleValue()
						* planMedioDetalleIf.getRaiting2().doubleValue());

				if (planMedioDetalleIf.getProductoProveedorId() != null) {
					ProductoIf productoProveedor = mapaProducto.get(planMedioDetalleIf.getProductoProveedorId());
					GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
					totalSumaTotalConIVA = totalSumaTotalConIVA
							.add(genericoProveedor.getCobraIvaCliente().equals("S") ? BigDecimal
									.valueOf(totalSinDescuento) : BigDecimal.ZERO);
					totalSumaTotalSinIVA = totalSumaTotalSinIVA
							.add(genericoProveedor.getCobraIvaCliente().equals("N") ? BigDecimal
									.valueOf(totalSinDescuento) : BigDecimal.ZERO);
				}
				totalSumaTotal = totalSumaTotal.add(BigDecimal
						.valueOf(totalSinDescuento));
				arregloDatosTGRP.add(datoTGRP);
			}
			// getTxtValorTarifa().setText(formatoDecimal.format(totalValorTotal));
			// getTxtValorDescuento().setText(formatoDecimal.format(0D));

			datosTGRP = new Object[arregloDatosTGRP.size()][];
			for (int i = 0; i < arregloDatosTGRP.size(); i++) {
				datosTGRP[i] = arregloDatosTGRP.get(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert(
					"Error al generar el arreglo de datos de Tv",
					SpiritAlert.ERROR);
		}
	}

	private Vector<Object[]> generarArregloDatosPrensa(
			Vector<String> diasPeriodoColeccion) {
		Vector<Object[]> arregloDatosPrensa = new Vector<Object[]>();
		ClienteIf cliente = null;
		PrensaSeccionIf prensaSeccion = null;
		PrensaUbicacionIf prensaUbicacion = null;
		PrensaFormatoIf prensaFormato = null;
		Object[] datoPrensa;
		try {
			for (PrensaTarifaIf prensaTarifaSeleccionado : prensaTarifaColeccion) {
				cliente = SessionServiceLocator.getClienteSessionService()
						.getCliente(prensaTarifaSeleccionado.getClienteId());
				prensaSeccion = SessionServiceLocator
						.getPrensaSeccionSessionService().getPrensaSeccion(
								prensaTarifaSeleccionado.getPrensaSeccionId());
				prensaUbicacion = SessionServiceLocator
						.getPrensaUbicacionSessionService()
						.getPrensaUbicacion(
								prensaTarifaSeleccionado.getPrensaUbicacionId());
				prensaFormato = SessionServiceLocator
						.getPrensaFormatoSessionService().getPrensaFormato(
								prensaTarifaSeleccionado.getPrensaFormatoId());

				datoPrensa = new Object[diasPeriodoColeccion.size() + 5];
				datoPrensa[0] = null;
				datoPrensa[1] = cliente.getNombreLegal();
				datoPrensa[2] = prensaSeccion.getSeccion();
				datoPrensa[3] = prensaUbicacion.getUbicacion();
				datoPrensa[4] = prensaFormato.getFormato();
				for (int i = 0; i < (diasPeriodoColeccion.size() - 1); i++) {
					datoPrensa[i + 5] = "";
				}
				arregloDatosPrensa.add(datoPrensa);
			}

			datosPrensa = new Object[arregloDatosPrensa.size()][];
			for (int i = 0; i < arregloDatosPrensa.size(); i++) {
				datosPrensa[i] = arregloDatosPrensa.get(i);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(
					"Error al generar el arreglo de datos de prensa",
					SpiritAlert.ERROR);
		}
		return arregloDatosPrensa;
	}

	private void generarPrensaTarifaColeccion() {
		try {
			prensaTarifaColeccion.clear();
			ClienteIf cliente = null;
			PrensaSeccionIf prensaSeccion = null;
			PrensaUbicacionIf prensaUbicacion = null;
			PrensaFormatoIf prensaFormato = null;
			PrensaTarifaIf prensaTarifa = null;
			TreePath[] treePaths = getArbolPrensa()
					.getCheckBoxTreeSelectionModel().getSelectionPaths();
			if (treePaths != null) {
				for (int i = 0; i < treePaths.length; i++) {
					TreePath path = treePaths[i];
					DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) path
							.getLastPathComponent();
					System.out.println("nodo seleccionado" + i + ": "
							+ nodo.getUserObject());
					if (nodo.getUserObject() instanceof PrensaTarifaIf) {
						prensaTarifa = (PrensaTarifaIf) nodo.getUserObject();
						if (!prensaVectorContieneElemento(prensaTarifa)) {
							prensaTarifaColeccion.add(prensaTarifa);
						}
					} else if (nodo.getUserObject() instanceof PrensaFormatoIf) {
						prensaFormato = (PrensaFormatoIf) nodo.getUserObject();
						Collection prensaTarifaSeleccionados = SessionServiceLocator
								.getPrensaTarifaSessionService()
								.findPrensaTarifaByPrensaFormatoId(
										prensaFormato.getId());
						Iterator prensaTarifaSeleccionadosIterator = prensaTarifaSeleccionados
								.iterator();
						while (prensaTarifaSeleccionadosIterator.hasNext()) {
							prensaTarifa = (PrensaTarifaIf) prensaTarifaSeleccionadosIterator
									.next();
							if (!prensaVectorContieneElemento(prensaTarifa)) {
								prensaTarifaColeccion.add(prensaTarifa);
							}
						}
					} else if (nodo.getUserObject() instanceof PrensaUbicacionIf) {
						prensaUbicacion = (PrensaUbicacionIf) nodo
								.getUserObject();
						Collection prensaTarifaSeleccionados = SessionServiceLocator
								.getPrensaTarifaSessionService()
								.findPrensaTarifaByPrensaUbicacionId(
										prensaUbicacion.getId());
						Iterator prensaTarifaSeleccionadosIterator = prensaTarifaSeleccionados
								.iterator();
						while (prensaTarifaSeleccionadosIterator.hasNext()) {
							prensaTarifa = (PrensaTarifaIf) prensaTarifaSeleccionadosIterator
									.next();
							if (!prensaVectorContieneElemento(prensaTarifa)) {
								prensaTarifaColeccion.add(prensaTarifa);
							}
						}
					} else if (nodo.getUserObject() instanceof PrensaSeccionIf) {
						prensaSeccion = (PrensaSeccionIf) nodo.getUserObject();
						Collection prensaTarifaSeleccionados = SessionServiceLocator
								.getPrensaTarifaSessionService()
								.findPrensaTarifaByPrensaSeccionId(
										prensaSeccion.getId());
						Iterator prensaTarifaSeleccionadosIterator = prensaTarifaSeleccionados
								.iterator();
						while (prensaTarifaSeleccionadosIterator.hasNext()) {
							prensaTarifa = (PrensaTarifaIf) prensaTarifaSeleccionadosIterator
									.next();
							if (!prensaVectorContieneElemento(prensaTarifa)) {
								prensaTarifaColeccion.add(prensaTarifa);
							}
						}
					} else if (nodo.getUserObject() instanceof ClienteIf) {
						cliente = (ClienteIf) nodo.getUserObject();
						Collection prensaTarifaSeleccionados = SessionServiceLocator
								.getPrensaTarifaSessionService()
								.findPrensaTarifaByClienteId(cliente.getId());
						Iterator prensaTarifaSeleccionadosIterator = prensaTarifaSeleccionados
								.iterator();
						while (prensaTarifaSeleccionadosIterator.hasNext()) {
							prensaTarifa = (PrensaTarifaIf) prensaTarifaSeleccionadosIterator
									.next();
							if (!prensaVectorContieneElemento(prensaTarifa)) {
								prensaTarifaColeccion.add(prensaTarifa);
							}
						}
					} else {
						Collection prensaTarifaSeleccionados = SessionServiceLocator
								.getPrensaTarifaSessionService()
								.getPrensaTarifaList();
						Iterator prensaTarifaSeleccionadosIterator = prensaTarifaSeleccionados
								.iterator();
						while (prensaTarifaSeleccionadosIterator.hasNext()) {
							prensaTarifa = (PrensaTarifaIf) prensaTarifaSeleccionadosIterator
									.next();
							if (!prensaVectorContieneElemento(prensaTarifa)) {
								prensaTarifaColeccion.add(prensaTarifa);
							}
						}
					}
				}
				for (PrensaTarifaIf prensaTarifaSeleccion : prensaTarifaColeccion) {
					System.out.println("prensaTarifaSeleccion: "
							+ prensaTarifaSeleccion.getId() + "-"
							+ prensaTarifaSeleccion);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(
					"Error al generar al colección de tarifas de prensa",
					SpiritAlert.ERROR);
		}
	}

	private Vector<String> getDiasPeriodoColeccion() {
		int tipoPeriodoSeleccionado = getCmbTipoPeriodo().getSelectedIndex();
		PlanMedioMesIf planMedioMesSeleccionado = getPlanMedioMesVector().get(
				tipoPeriodoSeleccionado);
		Date inicioPeriodoSeleccionado = Utilitarios
				.fromTimestampToSqlDate(planMedioMesSeleccionado
						.getFechaInicio());
		Date finPeriodoSeleccionado = Utilitarios
				.fromTimestampToSqlDate(planMedioMesSeleccionado.getFechaFin());
		Long intervalo = finPeriodoSeleccionado.getTime()
				- inicioPeriodoSeleccionado.getTime();
		Long diasPeriodo = (intervalo / 86400000) + 1; // 886400000 =
														// 1000*60*60*24
		System.out.println("diasPeriodo: " + diasPeriodo);
		int diaInicioPeriodo = inicioPeriodoSeleccionado.getDate();
		int mesInicioPeriodo = inicioPeriodoSeleccionado.getMonth();
		int anioInicioPeriodo = inicioPeriodoSeleccionado.getYear();
		Vector<String> diasPeriodoColeccion = new Vector<String>();
		Date diaPeriodo;
		for (int i = 0; i < diasPeriodo; i++) {
			int ultimoDiaMes = Utilitarios.getLastDayOfMonth(mesInicioPeriodo,
					anioInicioPeriodo);

			if (diaInicioPeriodo <= ultimoDiaMes) {
				diaPeriodo = new Date(anioInicioPeriodo, mesInicioPeriodo,
						diaInicioPeriodo);
				diaInicioPeriodo++;
				diasPeriodoColeccion.add(Utilitarios
						.getFechaEEEddMMM(diaPeriodo));
			} else {
				diaInicioPeriodo = 1;
				if (mesInicioPeriodo < 11) {
					mesInicioPeriodo++;
				} else {
					mesInicioPeriodo = 0;
					anioInicioPeriodo++;
				}
				diaPeriodo = new Date(anioInicioPeriodo, mesInicioPeriodo,
						diaInicioPeriodo);
				diaInicioPeriodo++;
				diasPeriodoColeccion.add(Utilitarios
						.getFechaEEEddMMM(diaPeriodo));
			}
		}
		diasPeriodoColeccion.add("CUÑAS");

		return diasPeriodoColeccion;
	}

	private void setearPreferenciasMapaPautaPrensa() {
		scrollPanelMapaPrensa.getTable().getMainTable()
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanelMapaPrensa.getTable().getRowHeaderTable()
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanelMapaPrensa.getTable().getMainTable()
				.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPanelMapaPrensa.getTable().getRowHeaderTable().getColumnModel()
				.getColumn(0).setPreferredWidth(24);
		scrollPanelMapaPrensa.getTable().getRowHeaderTable().getColumnModel()
				.getColumn(1).setPreferredWidth(80);
		scrollPanelMapaPrensa.getTable().getRowHeaderTable().getColumnModel()
				.getColumn(2).setPreferredWidth(100);
		scrollPanelMapaPrensa.getTable().getRowHeaderTable().getColumnModel()
				.getColumn(3).setPreferredWidth(100);
		scrollPanelMapaPrensa.getTable().getRowHeaderTable().getColumnModel()
				.getColumn(4).setPreferredWidth(100);
		scrollPanelMapaPrensa.getTable().getRowFooterTable().getColumnModel()
				.getColumn(0).setPreferredWidth(50);
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		scrollPanelMapaPrensa.getTable().getRowFooterTable().getColumnModel()
				.getColumn(0).setCellRenderer(tableCellRenderer);
	}

	// FORMATO EN LAS TABLAS
	private void setearPreferenciasMapaPautaTv() {
		scrollPanelMapaTv.getTable().getMainTable()
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanelMapaTv.getTable().getRowHeaderTable()
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPanelMapaTv.getTable().getMainTable()
				.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPanelMapaTv.getTable().getRowHeaderTable().getColumnModel()
				.getColumn(0).setPreferredWidth(24);
		scrollPanelMapaTv.getTable().getRowHeaderTable().getColumnModel()
				.getColumn(1).setPreferredWidth(45);
		scrollPanelMapaTv.getTable().getRowHeaderTable().getColumnModel()
				.getColumn(2).setPreferredWidth(80);
		scrollPanelMapaTv.getTable().getRowHeaderTable().getColumnModel()
				.getColumn(3).setPreferredWidth(100);
		scrollPanelMapaTv.getTable().getRowHeaderTable().getColumnModel()
				.getColumn(4).setPreferredWidth(80);
		scrollPanelMapaTv.getTable().getRowFooterTable().getColumnModel()
				.getColumn(0).setPreferredWidth(50);

		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		// Alineo a la derecha la columna de totales por fila "CUÑAS"
		scrollPanelMapaTv.getTable().getRowFooterTable().getColumnModel()
				.getColumn(0).setCellRenderer(tableCellRenderer);
		// Alineo a la derecha a la columna de los numeros #
		scrollPanelMapaTv.getTable().getRowHeaderTable().getColumnModel()
				.getColumn(0).setCellRenderer(tableCellRenderer);
	}

	protected boolean prensaVectorContieneElemento(PrensaTarifaIf prensaTarifa) {
		for (PrensaTarifaIf prensaTarifaEnVector : prensaTarifaColeccion) {
			if (prensaTarifaEnVector.getId().equals(prensaTarifa.getId())) {
				return true;
			}
		}
		return false;
	}

	ActionListener oActionListenerBtnCrearMapaPautaTv = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			try {
				tipoPlantilla = TIPO_PLANTILLA_SENCILLA;
				// MODIFIED 29 ABRIL
				if (cargarPlantillaTv(tipoPlantilla)) {
					validarComerciales();
					generarMapaPautaTv(tipoPlantilla);
					// END 29 ABRIL
					// estab sin el if
					/*
					 * cargarPlantillaTv(tipoPlantilla); validarComerciales();
					 * generarMapaPautaTv(tipoPlantilla);
					 */
				}

			} catch (GenericBusinessException e) {
				// ADD 24 AGOSTO
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	/* Action del Boton que permite cargar un Archivo Excel */
	ActionListener oActionListenerBtnCrearMapaPautaTvMultiple = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			try {
				if (ordenTrabajoIf != null) {
					// ADD 29 JUNIO
					if (!((planMedioTipo
							.compareTo(PLAN_MEDIO_TIPO_NUEVA_VERSION) == 0 || planMedioTipo
							.compareTo(PLAN_MEDIO_TIPO_NUEVO_MES) == 0) && planMedioIfSaved == null)) {
						// ADD 27 ABRIL
						if (!planMedioMesVector.isEmpty()) {
							if (comercialesIsNotEmpty()) {

								// ADD 29 AGOSTO
								cargarVectorComercialesSeleccionados();
								if (comercialesSeleccionadosTabla.size() > 0) {

									// ADD 7 OCTUBRE
									if (getCbPautaBasica().isSelected()
											|| getCbAgrupaOrdenes()
													.isSelected()
											|| getCbOrdenPorProductoComercial()
													.isSelected()
											|| getCbOrdenPorVersion()
													.isSelected()) {

										if (!getCbPautaBasica().isSelected()
												|| validarComercialesPautaBasica()) {

											tipoPlantilla = TIPO_PLANTILLA_MULTIPLE;

											// COMENTED 10 JUNIO
											/*
											 * if(getMode() ==
											 * SpiritMode.UPDATE_MODE){ //COPIA
											 * TODAS LOS MAPAS ANTERIORES
											 * copiarPlanMedioGuardado(); }
											 */// END COMENTED 10 JUNIO
												// MODIFIED 29 ABRIL
											if (cargarPlantillaTv(tipoPlantilla)) {// }EN
																					// 29
																					// ABRIL
												// estaba sin if
												// cargarPlantillaTv(tipoPlantilla);
												if (validarComerciales()) {
													generarMapaPautaTv(tipoPlantilla);
													boolean problema = false;
													PlanMedioIf planMedio = new PlanMedioData();

													if (planMedioIf != null) {
														if (planMedioIf.getId() != null) {
															planMedio = planMedioIf;
														}
													} else {
														String codigo = getCodigoPlanMedio(new java.sql.Date(
																new GregorianCalendar()
																		.getTimeInMillis()));
														planMedio
																.setCodigo(codigo);
													}

													planMedio
															.setConcepto(getTxtConcepto()
																	.getText());

													tipoOrden = (TipoOrdenIf) SessionServiceLocator
															.getTipoOrdenSessionService()
															.findTipoOrdenByTipo(
																	"M")
															.iterator().next();
													ordenTrabajoIf = (OrdenTrabajoIf) getCmbOrdenTrabajo()
															.getSelectedItem();
													ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) SessionServiceLocator
															.getOrdenTrabajoDetalleSessionService()
															.findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajoAndEstado(
																	tipoOrden
																			.getId(),
																	ordenTrabajoIf
																			.getId())
															.iterator().next();
													planMedio
															.setOrdenTrabajoDetalleId(ordenTrabajoDetalleIf
																	.getId());

													ordenTrabajoIf
															.setEstado(ESTADO_ORDEN_EN_CURSO);
													ordenTrabajoDetalleIf
															.setEstado(ESTADO_ORDEN_EN_CURSO);

													setearGenericoPautaRegular();
													setearDescuentoyProductoPlanMedioDetallesPlantilla(true);

													porcentajeComisionAgencia = new BigDecimal(
															0);
													porcentajeBonificacionVenta = new BigDecimal(
															0);
													porcentajeDescuentoTotalVenta = new BigDecimal(
															0);
													getTxtPorcentajeComisionAgencia()
															.setText("");
													getTxtPorcentajeBonificacionVenta()
															.setText("");
													getTxtPorcentajeDescuentoVenta()
															.setText("");

													problema = generarMapasComercialesPlantilla(problema);

													if (!problema) {
														// ADD 8 JULIO
														/*
														 * if
														 * (planMedioTipo.compareTo
														 * (
														 * PLAN_MEDIO_TIPO_NUEVA_VERSION
														 * ) == 0 &&
														 * planMedioIfSaved !=
														 * null){
														 * planMedio.setCodigo
														 * (planMedioIfSaved
														 * .getCodigo()); }
														 */
														// END 8 JULIO
														// MODIFIED 20 MAYO
														// se genera las ordenes
														// de medio

														/*
														 * COMENTED 7 OCTUBRE if
														 * (
														 * !getCbAgrupaOrdenes()
														 * .isSelected()){
														 * //true si se kiere
														 * agrupar las ordenes
														 * de Medio x PRODUCTO
														 * isOrdenesXCanal =
														 * "NO"; planMedio.
														 * setOrdenesXCanal
														 * ("NO");
														 * generarOrdenesMedio
														 * (planMedio,false);
														 * cargarOrdenesMedioaAgrupadas
														 * (); } else {
														 * isOrdenesXCanal =
														 * "SI"; planMedio.
														 * setOrdenesXCanal
														 * ("SI"); //true si se
														 * kiere agrupar las
														 * ordenes de Medio x
														 * CANAL
														 * generarOrdenesMedio
														 * (planMedio,true);
														 * cargarOrdenesMedioaAgrupadasXCanal
														 * (); }
														 */

														// ADD 7 OCTUBRE
														if (getCbAgrupaOrdenes()
																.isSelected()) { // ORDENES
																					// X
																					// CANAL
															ordenMedioTipo = ORDEN_MEDIO_TIPO_CANAL;
															// planMedio.setOrdenesXCanal("SI");
															// COMENTED 7
															// OCTUBRE
															planMedio
																	.setOrdenMedioTipo(ORDEN_MEDIO_TIPO_CANAL);
															// 1 si se kiere
															// agrupar las
															// ordenes de Medio
															// x CANAL
															generarOrdenesMedio(
																	planMedio,
																	1);
															cargarOrdenesMedioaAgrupadasXCanal();
														} else if (getCbOrdenPorProductoComercial()
																.isSelected()) {// ORDENES
																				// X
																				// PRODUCTO
																				// CLIENTE
															ordenMedioTipo = ORDEN_MEDIO_TIPO_PRODUCTO_COMERCIAL;
															planMedio
																	.setOrdenMedioTipo(ORDEN_MEDIO_TIPO_PRODUCTO_COMERCIAL);
															// 2 si se kiere
															// agrupar las
															// ordenes de Medio
															// x PRODUCTO
															// COMERCIAL
															generarOrdenesMedio(
																	planMedio,
																	2);
															// cargarOrdenesMedioaAgrupadas();
															// COMENTED 13
															// OCTUBRE
															cargarOrdenesMedioaAgrupadasXProductoCliente(); // ADD
																											// 13
																											// OCTUBRE
														} else if (getCbOrdenPorVersion()
																.isSelected()
																|| getCbPautaBasica()
																		.isSelected()) {// ORDENES
																						// X
																						// VERSION
																						// Y
																						// PAUTA
																						// BASICA(NUMERICA)
															ordenMedioTipo = ORDEN_MEDIO_TIPO_VERSION;
															planMedio
																	.setOrdenMedioTipo(ORDEN_MEDIO_TIPO_VERSION);
															// 3 si se kiere
															// agrupar las
															// ordenes de Medio
															// x VERSION
															generarOrdenesMedio(
																	planMedio,
																	3);
															cargarOrdenesMedioaAgrupadasXCampanaProductoVersion();
														}
														// END 7 OCTUBRE

														// END MODIFIED 20 MAYO
														// MODIFIED 3 MAYO
														// generarOrdenesMedio(planMedio,true);
														// generarOrdenesMedio(planMedio);
														// se cargan las ordenes
														// de medio en la
														// pantalla del sistema
														// cargarOrdenesMedioaAgrupadas();
														// // 20 MAYO
														if (getMode() == SpiritMode.UPDATE_MODE) {
															// GIOMY REVISAR
															// PARA
															// ACTUALIZACIONES
															// 13 MAYO
															// mapaProductoClienteOrdenesCompSaved.clear();//decomented
															// cargarOrdenesMedioForUpdate();
															// //20 MAYO
															// ADD 20 MAYO
															/*
															 * if
															 * (!getCbAgrupaOrdenes
															 * ().isSelected()){
															 * //true si se
															 * kiere agrupar las
															 * ordenes de Medio
															 * x PRODUCTO //AKI
															 * 14 JUNIO //
															 * cargarOrdenesMedioForUpdate
															 * ();
															 * cargarOrdenesMedioAgrupadasForUpdatePrueba
															 * (); } else {
															 * //true si se
															 * kiere agrupar las
															 * ordenes de Medio
															 * x CANAL //AKI 14
															 * JUNIO //
															 * cargarOrdenesMedioXCanalForUpdate
															 * (); }
															 */

															// ADD 15 JUNIO
															/*
															 * if
															 * (planMedioIfSaved
															 * .
															 * getOrdenesXCanal(
															 * ).compareTo("SI")
															 * != 0){
															 * generarOrdenesMedioForUpdate
															 * (
															 * planMedioIfSaved,
															 * false);
															 * cargarOrdenesMedioaAgrupadasForUpdate
															 * (); }else{
															 * generarOrdenesMedioForUpdate
															 * (
															 * planMedioIfSaved,
															 * true);
															 * cargarOrdenesMedioaAgrupadasXCanalForUpdate
															 * (); }
															 */
															// END 15 JUNIO
															// END 20 MAYO
														}
													} else {
														SpiritAlert.createAlert("Una o más Versiones no se encuentran ingresadas en Comercial!", SpiritAlert.WARNING);
													}
												}/*
												 * else{COMENTED 5 JULIO
												 * SpiritAlert.createAlert(
												 * "El Archivo Excel no fue cargado debido a que uno o más comerciales no están autorizados"
												 * ,SpiritAlert.WARNING); }
												 */
											}
										}// ADD 29 AGOSTO
										else {
											SpiritAlert
													.createAlert(
															"El Archivo Excel no fue cargado debido a que debe seleccionar \n"
																	+ "Comerciales de una misma Versión e Identificación en una Pauta Básica",
															SpiritAlert.WARNING);
										}

									}// ADD 7 OCTUBRE
									else {
										SpiritAlert
												.createAlert(
														"El Archivo Excel no fue cargado debido a que no ha seleccionado \n"
																+ "el Tipo de Orden de Medio a generar",
														SpiritAlert.WARNING);
									}
									// END 7 OCTUBRE
								} else {
									SpiritAlert.createAlert(
											"El Archivo Excel no fue cargado debido a que no ha seleccionado \n"
													+ "al menos un Comercial",
											SpiritAlert.WARNING);
								}
								// END 29 AGOSTO
							} else {
								// MODIFIED 29 AGOSTO
								// SpiritAlert.createAlert("El Archivo Excel no fue cargado debido a que no existen comerciales \n"
								// +
								// "asignados a la OT "+ordenTrabajoIf.getDescripcion(),SpiritAlert.WARNING);
								SpiritAlert.createAlert(
										"El Archivo Excel no fue cargado debido a que no existen comerciales \n"
												+ "asignados a la Campaña "
												+ campanaIf.getNombre(),
										SpiritAlert.WARNING);
							}
						} else {
							SpiritAlert
									.createAlert(
											"El Archivo Excel no fue cargado debido a que no ha elegido una Planificación",
											SpiritAlert.WARNING);
						}
						// ADD 29 JUNIO
					} else {
						SpiritAlert
								.createAlert(
										"El Archivo Excel no fue cargado debido a que no se ha elegido Plan Medio a relacionar",
										SpiritAlert.WARNING);
						getJtpPlanMedio().setSelectedIndex(0);
						getBtnPlanMedioRelacion().grabFocus();
					}
					// END 29 JUNIO
				} else {
					SpiritAlert
							.createAlert(
									"El Archivo Excel no fue cargado debido a que no se ha elegido una OT",
									SpiritAlert.WARNING);
				}

			} catch (GenericBusinessException e) {
				// ADD 24 AGOSTO
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
			} catch (OfficeXmlFileException e) {
				// ADD 24 AGOSTO
				e.printStackTrace();
				SpiritAlert.createAlert(
						"El Archivo Excel cargado no es de extesnsión .xls",
						SpiritAlert.WARNING);
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert(
						"Problemas con el Archivo Excel cargado",
						SpiritAlert.WARNING);
			}
		}
	};

	// METODO NO UTILIZADO 21 JUNIO
	// GIOMY REVISAR PARA ACTUALIZACIONES 13 MAYO
	private void copiarPlanMedioGuardado() {

		// ADD 10 JUNIO
		planMedioIfSaved = planMedioIf;
		planMedioDetallesPlantillaSaved = planMedioDetallesPlantilla;
		mapasComercialesPlantillaSaved = mapasComercialesPlantilla;
		mapasComercialesPlantillaMultipleSaved = mapasComercialesPlantillaMultiple;
		listaProveedoresMapSaved = listaProveedoresMap;

		// mapaProductoClienteOrdenesCompSaved = mapaProductoClienteOrdenesComp;
		// COMENTED 31 AGOSTO
		ordenesMediosMapCompSaved = ordenesMediosMapComp;
		ordenesMedioMapSaved = ordenesMedioMap;
		mapOrdenMediobyProveedorSaved = mapOrdenMediobyProveedor;
		mapOrdenMediobyProveedorPCanjeSaved = mapOrdenMediobyProveedorPCanje;
		mapOrdenMediobyProveedorPDescSaved = mapOrdenMediobyProveedorPDesc;

		proveedorValorSubtotalSaved = proveedorValorSubtotal;
		mapOrdenMediobyProveedorObservacionSaved = mapOrdenMediobyProveedorObservacion;
		ordenMedioCollectionSaved = ordenMedioCollection;
		indiceProveedorSubtotalOrdenesSaved = indiceProveedorSubtotalOrdenes;
		listObservacionesTempSaved = listObservacionesTemp;
		mapaProveedorOrdenesCompSaved = mapaProveedorOrdenesComp;
		mapaProveedorOrdenesSaved = mapaProveedorOrdenes;

		clienteOficinaIfSaved = clienteOficinaIf;

		// END 10 JUNIO

		genericoPautaRegularSaved = genericoPautaRegular;
		// ADD 26 MAYO
		mapOrdenMediobyProveedorbservacionSaved = mapOrdenMediobyProveedorObservacion;
		// END 26 MAYO

	}

	private boolean comercialesIsNotEmpty() {
		try {
			Map aMap = new HashMap();
			aMap.put("empresaId", Parametros.getIdEmpresa());
			aMap.put("estado", ESTADO_ACTIVO);
			aMap.put("campanaId", ordenTrabajoIf.getCampanaId());

			// cambiarlos por los q tienen el visto
			Collection comerciales = SessionServiceLocator
					.getComercialSessionService().findComercialByQuery(aMap);
			if (comerciales.size() > 0) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			// ADD 24 AGOSTO
			e.printStackTrace();
			// TODO: handle exception
		}
		return false;
	}

	// ADD MODIFIED 5 JULIO
	private boolean validarComerciales() {
		try {
			Map aMap = new HashMap();
			aMap.put("empresaId", Parametros.getIdEmpresa());
			aMap.put("estado", ESTADO_ACTIVO);
			aMap.put("campanaId", ordenTrabajoIf.getCampanaId());

			// cambiarlos por los q tienen el visto
			Collection comerciales = SessionServiceLocator
					.getComercialSessionService().findComercialByQuery(aMap);
			DefaultTableModel modelComercial = (DefaultTableModel) getTblComercial()
					.getModel();
			int col = getTblComercial().getColumnCount();
			int nfilas = getTblComercial().getRowCount();

			int[] arrayCheckbox = new int[comerciales.size()];
			for (int i = 0; i < nfilas; i++) {
				Boolean checkbox = (Boolean) modelComercial.getValueAt(i, 0);
				if (checkbox.booleanValue()) {
					arrayCheckbox[i] = 1;
				} else
					arrayCheckbox[i] = 0;
			}

			// llena la coleccion de los Comerciales seleccionados
			Collection<ComercialIf> comercialesElegidos = new ArrayList<ComercialIf>();
			Iterator comercialesIt = comerciales.iterator();
			for (int i = 0; i < comerciales.size(); i++) {
				if (comercialesIt.hasNext()) {
					ComercialIf comercialDepIf = (ComercialIf) comercialesIt
							.next();
					if (arrayCheckbox[i] == 1) {
						comercialesElegidos.add(comercialDepIf);
					}
				}
			}

			Collection planMedioDetallesPlantillaVal;
			Map<PlanMedioDetalleIf, Collection<InfoComercialMultiple>> mapasComercialesPlantillaMultipleVal;

			planMedioDetallesPlantillaVal = planMedioDetallesPlantilla;
			// mapasComercialesPlantillaMultiple CONTIENE A LA LETRAS VER DONDE
			// SE LLENA
			mapasComercialesPlantillaMultipleVal = mapasComercialesPlantillaMultiple;

			Iterator detallesPlantillaIt = planMedioDetallesPlantillaVal
					.iterator();
			// ADD 5 JULIO
			String cadenaNoComercialExistente = "";

			if (mapasComercialesPlantillaMultipleVal.size() > 0) {

				Iterator planMedioDetalleIt = mapasComercialesPlantillaMultipleVal
						.keySet().iterator();
				while (planMedioDetalleIt.hasNext()) {
					PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) planMedioDetalleIt
							.next();
					// este comercial es: VERSION,DERECHO_DE_PROGRAMA, hay que
					// separarlos
					String comercial = planMedioDetalleIf.getComercial();

					// 15 MAYO CAMBIAR ESTO GIOMAYRA
					String[] comercialSeparado = comercial.split(",");
					if (comercialSeparado.length > 2)
						comercial = comercialSeparado[1];
					else
						comercial = comercialSeparado[0];

					ArrayList<InfoComercialMultiple> listInfoComercialMul = (ArrayList<InfoComercialMultiple>) mapasComercialesPlantillaMultipleVal
							.get(planMedioDetalleIf);
					for (int i = 0; i < listInfoComercialMul.size(); i++) {
						InfoComercialMultiple infoComercialMultiple = listInfoComercialMul
								.get(i);
						ArrayList letras = infoComercialMultiple.getLista();

						for (int j = 0; j < letras.size(); j++) {
							String letra = (String) letras.get(j);

							Iterator comercialIt = comercialesElegidos
									.iterator();
							boolean tmp = false;
							// ADD 30 JUNIO
							// String cadenaNoComercialExistente = "";
							// //COMENTED 5 JULIO
							// END 30 JUNIO
							while (comercialIt.hasNext()) {
								ComercialIf comercialIf = (ComercialIf) comercialIt
										.next();
								DerechoProgramaIf derechoProgramaIf = mapaDerechoPrograma
										.get(comercialIf.getDerechoprogramaId());
								// MODIFIED 1 DE JUNIO
								// String derechoPrograma =
								// derechoProgramaIf.getNombre()+"-"+derechoProgramaIf.getTipo();
								String derechoPrograma = derechoProgramaIf
										.getNombre();// +"-"+derechoProgramaIf.getTipo();
								// planMedioDetalleData.setComercial(comercial.getVersion()+","+
								// planMedioDetalleData.getComercial());

								// MODIFIED 28 ABRIL
								if (comercialIf.getVersion().trim()
										.compareTo(letra.trim()) == 0
										&& derechoPrograma.trim().compareTo(
												comercial.split(",")[0].trim()) == 0) {
									tmp = true;
									break;
								}// /EN 28 ABRIL
							}
							if (tmp == false) {
								// ADD 6 JULIO
								String nuevoComercialNoAutorizado = letra
										.trim() + " " + comercial.trim();// +
																			// ",";

								if (cadenaNoComercialExistente.length() > 0) {

									String cadenaNoComercialExistenteSplit[] = cadenaNoComercialExistente
											.split(",");
									if (cadenaNoComercialExistenteSplit.length > 0) {
										int acum = 0;
										for (int c = 0; c < cadenaNoComercialExistenteSplit.length; c++) {
											String comercialTemp = cadenaNoComercialExistenteSplit[c];
											if (nuevoComercialNoAutorizado
													.compareTo(comercialTemp) == 0) {
												acum++;
											}
										}
										if (acum == 0) {
											cadenaNoComercialExistente += nuevoComercialNoAutorizado
													+ ",";
										}
									}
								} else {
									cadenaNoComercialExistente += nuevoComercialNoAutorizado
											+ ",";
								}
								// END 6 JULIO
							}
						}
					}
				}
				// ADD 5 JULIO
				if (cadenaNoComercialExistente.length() > 0) {
					System.out.println("cadenaNoComercialExistente "
							+ cadenaNoComercialExistente);
					SpiritAlert
							.createAlert(
									"Los Comerciales: "
											+ cadenaNoComercialExistente
											+ " no existen para esta Campaña."
											+ "\nVerificar que el archivo Excel cargado tenga las Versiones y Derechos de Programa"
											+ "\nescritos correctamente o en su defecto Agregue estos Comerciales a la Campaña.",
									SpiritAlert.WARNING);
					return false;
				}
				// END 5 JULIO
			}
			return true;

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return true;
	}

	// se colocan los datos de las plantillas mapasComercialesPlantilla,
	// mapasComercialesPlantillaMultiple
	// en la tabla de la pestaña Television de la Seccion Mapas de Pauta
	private void generarMapaPautaTv(String tipoPlantilla) {

		// HashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>>
		// mapasComercialesPlantilla
		// Map<PlanMedioDetalleIf, Collection<InfoComercialMultiple>>
		// mapasComercialesPlantillaMultiple
		if (mapasComercialesPlantilla.size() > 0
				|| mapasComercialesPlantillaMultiple.size() > 0) {
			Vector<String> diasSubPeriodoColeccion = new Vector<String>();

			int mes = 0;
			int anio = 0;

			Iterator mapasComercialesPlantillaIt = null;
			if (mapasComercialesPlantilla.size() > 0) {
				mapasComercialesPlantillaIt = mapasComercialesPlantilla
						.keySet().iterator();
			} else {
				mapasComercialesPlantillaIt = mapasComercialesPlantillaMultiple
						.keySet().iterator();
			}
			while (mapasComercialesPlantillaIt.hasNext()) {
				PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) mapasComercialesPlantillaIt
						.next();
				if (mapasComercialesPlantilla.size() > 0) {
					if (mapasComercialesPlantilla.get(planMedioDetalleIf)
							.size() > 0) {
						mes = mapasComercialesPlantilla.get(planMedioDetalleIf)
								.iterator().next().getFecha().getMonth();
						anio = mapasComercialesPlantilla
								.get(planMedioDetalleIf).iterator().next()
								.getFecha().getYear();
						break;
					}
				} else {
					if (mapasComercialesPlantillaMultiple.get(
							planMedioDetalleIf).size() > 0) {
						mes = mapasComercialesPlantillaMultiple
								.get(planMedioDetalleIf).iterator().next()
								.getFecha().getMonth();
						anio = mapasComercialesPlantillaMultiple
								.get(planMedioDetalleIf).iterator().next()
								.getFecha().getYear();
						break;
					}
				}
			}

			Date fechaDiaria = new Date(anio, mes, 1);
			String[] fechaFormateada = new String[] {};
			for (int i = 0; i < 31; i++) {
				fechaDiaria = new Date(anio, mes, i + 1);
				fechaFormateada = Utilitarios.getFechaEEEddMMM(fechaDiaria)
						.split("-");
				diasSubPeriodoColeccion.add(fechaFormateada[0]
						+ fechaFormateada[1]);
			}
			diasSubPeriodoColeccion.add("CUÑAS");

			cabecerasFijasTv = new String[diasSubPeriodoColeccion.size() + 5];
			cabecerasFijasTv[0] = "#";
			cabecerasFijasTv[1] = "H.INI";
			cabecerasFijasTv[2] = "MEDIO";
			cabecerasFijasTv[3] = "PROGRAMA";
			cabecerasFijasTv[4] = "COMERCIAL";

			for (int i = 0; i < diasSubPeriodoColeccion.size(); i++) {
				cabecerasFijasTv[i + 5] = diasSubPeriodoColeccion.get(i);
			}

			cabecerasVariablesTv = new String[diasSubPeriodoColeccion.size() - 1];
			for (int i = 0; i < (diasSubPeriodoColeccion.size() - 1); i++) {
				cabecerasVariablesTv[i] = diasSubPeriodoColeccion.get(i);
			}

			// AKI SE GENERAN LOS DATOS DE LA SECCION MAPAS DE PAUTA TELEVISION
			// datosTv contiene cada linea del excel, hora inicio, descripcion o
			// cliente, programa, comercial, frecuencia,
			Vector<Object[]> arregloDatosTv = generarArregloDatosTv(diasSubPeriodoColeccion);

			getPanelMapaPautaTv().remove(scrollPanelMapaTv.getTable());

			// Collection<PlanMedioDetalleIf> planMedioDetallesPlantilla
			numeroFilasTv = planMedioDetallesPlantilla.size();
			// TABLE TABLA MAPAS DE PAUTA -> TELEVISION
			scrollPanelMapaTv = new TableScrollPaneMapaPauta(cabecerasFijasTv,
					cabecerasVariablesTv, datosTv, numeroFilasTv);
			// MultiTableModel modelo = scrollPanelMapaTv.getModel();
			// modelo.setValueAt("3", 0, 6);
			setearPreferenciasMapaPautaTv();

			getPanelMapaPautaTv().add(scrollPanelMapaTv.getTable(),
					cc.xywh(3, 5, 7, 5));
			tableScrollTvRemovido = scrollPanelMapaTv.getTable();

			cleanTablaTGRPtv();
			// AKI SE GENERAN LOS DATOS DE LA SECCION MAPAS DE PAUTA TGRP
			// AKI ME KEDE 17 MAYO

			generarArregloDatosTGRP();
			calcularTotales();
			calcularTotalesVenta();

			tableTGRP = (DefaultTableModel) getTblTGRPtv().getModel();

			for (int i = 0; i < datosTGRP.length; i++) {
				Vector<Object> fila = new Vector<Object>();
				for (int j = 0; j < 12; j++) {
					fila.add(datosTGRP[i][j]);
				}
				tableTGRP.addRow(fila);
			}

			getJtpPlanMedio().setSelectedIndex(3);
			getTpMapasPauta().setSelectedIndex(0);
		}
	}

	public void calcularTotalesVenta() {
		getTxtSumaPlanMedio()
				.setText(
						formatoDecimal.format(Utilitarios
								.redondeoValor(totalSumaTotal)));
		getTxtSuman2()
				.setText(
						formatoDecimal.format(Utilitarios
								.redondeoValor(totalSumaTotal)));
		BigDecimal descuentoTotalSinIVA = totalSumaTotalSinIVA
				.multiply((porcentajeDescuentoTotalVenta.divide(new BigDecimal(
						100))));
		BigDecimal descuentoTotalConIVA = totalSumaTotalConIVA
				.multiply((porcentajeDescuentoTotalVenta.divide(new BigDecimal(
						100))));
		descuentoTotalVenta = descuentoTotalSinIVA.add(descuentoTotalConIVA);
		getTxtDescuentoVenta().setText(
				formatoDecimal.format(Utilitarios
						.redondeoValor(descuentoTotalVenta)));
		getTxtDescuentoPlanMedio().setText(
				formatoDecimal.format(Utilitarios
						.redondeoValor(descuentoTotalVenta)));
		BigDecimal totalSubTotalVenta1SinIVA = totalSumaTotalSinIVA
				.subtract(descuentoTotalSinIVA);
		BigDecimal totalSubTotalVenta1ConIVA = totalSumaTotalConIVA
				.subtract(descuentoTotalConIVA);
		totalSubTotalVenta1 = totalSubTotalVenta1SinIVA
				.add(totalSubTotalVenta1ConIVA);
		BigDecimal comisionAgenciaTotalSinIVA = totalSubTotalVenta1SinIVA
				.multiply((porcentajeComisionAgencia
						.divide(new BigDecimal(100))));
		BigDecimal comisionAgenciaTotalConIVA = totalSubTotalVenta1ConIVA
				.multiply((porcentajeComisionAgencia
						.divide(new BigDecimal(100))));
		comisionAgenciaTotal = comisionAgenciaTotalSinIVA
				.add(comisionAgenciaTotalConIVA);
		getTxtComisionAgencia().setText(
				formatoDecimal.format(Utilitarios
						.redondeoValor(comisionAgenciaTotal)));
		getTxtComisionAgenciaPlanMedio().setText(
				formatoDecimal.format(Utilitarios
						.redondeoValor(comisionAgenciaTotal)));
		BigDecimal totalSubTotalVenta2SinIVA = totalSubTotalVenta1SinIVA
				.add(comisionAgenciaTotalSinIVA);
		BigDecimal totalSubTotalVenta2ConIVA = totalSubTotalVenta1ConIVA
				.add(comisionAgenciaTotalConIVA);
		totalSubTotalVenta2 = totalSubTotalVenta2SinIVA
				.add(totalSubTotalVenta2ConIVA);
		getTxtSubtotalVenta().setText(
				formatoDecimal.format(Utilitarios
						.redondeoValor(totalSubTotalVenta2)));
		getTxtSubtotalPlanMedio().setText(
				formatoDecimal.format(Utilitarios
						.redondeoValor(totalSubTotalVenta2)));
		// para la bonificacion de venta
		BigDecimal bonificacionVentaTotalSinIVA = totalSubTotalVenta2SinIVA
				.multiply((porcentajeBonificacionVenta.divide(new BigDecimal(
						100))));
		BigDecimal bonificacionVentaTotalConIVA = totalSubTotalVenta2ConIVA
				.multiply((porcentajeBonificacionVenta.divide(new BigDecimal(
						100))));
		bonificacionVentaTotal = bonificacionVentaTotalSinIVA
				.add(bonificacionVentaTotalConIVA);
		getTxtBonificacionVenta().setText(
				formatoDecimal.format(Utilitarios
						.redondeoValor(bonificacionVentaTotal)));
		getTxtBonificacionVentaPlanMedio().setText(
				formatoDecimal.format(Utilitarios
						.redondeoValor(bonificacionVentaTotal)));
		BigDecimal subtotalBonificacionVentaSinIVA = totalSubTotalVenta2SinIVA
				.subtract(bonificacionVentaTotalSinIVA);
		BigDecimal subtotalBonificacionVentaConIVA = totalSubTotalVenta2ConIVA
				.subtract(bonificacionVentaTotalConIVA);
		BigDecimal subtotalBonificacionVenta = subtotalBonificacionVentaSinIVA
				.add(subtotalBonificacionVentaConIVA);
		getTxtSubtotalBonificacionVenta().setText(
				formatoDecimal.format(Utilitarios
						.redondeoValor(subtotalBonificacionVenta)));
		getTxtSubtotalBonificacionVentaPlanMedio().setText(
				formatoDecimal.format(Utilitarios
						.redondeoValor(subtotalBonificacionVenta)));
		Iterator planMedioDetalleIt = mapasComercialesPlantilla.keySet()
				.iterator();
		ivaTotalVenta = subtotalBonificacionVentaConIVA.multiply(BigDecimal
				.valueOf(Parametros.IVA / 100D));
		getTxtIVA2()
				.setText(
						formatoDecimal.format(Utilitarios
								.redondeoValor(ivaTotalVenta)));
		getTxtIvaPlanMedio()
				.setText(
						formatoDecimal.format(Utilitarios
								.redondeoValor(ivaTotalVenta)));
		totalValorTotalVenta = subtotalBonificacionVenta.add(ivaTotalVenta);
		getTxtTotalPauta2().setText(
				formatoDecimal.format(Utilitarios
						.redondeoValor(totalValorTotalVenta)));
		getTxtTotalPlanMedio().setText(
				formatoDecimal.format(Utilitarios
						.redondeoValor(totalValorTotalVenta)));
	}

	// para validar las fechas para que no se crucen con las fechas de un plan
	// medio hermano
	private boolean validarFechasWithHermanos(java.util.Date fechaInicio,
			java.util.Date fechaFin) {
		boolean isValidate = true;
		Timestamp timeFechaInicio = Utilitarios
				.resetTimestampStartDate(new Timestamp(fechaInicio.getTime()));
		Timestamp timeFechaFin = Utilitarios
				.resetTimestampStartDate(new Timestamp(fechaFin.getTime()));

		if (mapFechaInicioFinPlanesMedioHermanosSaved != null
				&& mapFechaInicioFinPlanesMedioHermanosSaved.size() > 0) {
			Iterator fechasByPlanesMedioHermanosIt = mapFechaInicioFinPlanesMedioHermanosSaved
					.keySet().iterator();

			while (fechasByPlanesMedioHermanosIt.hasNext()) {
				// for (Map mapFechasByPlanMedio:
				// mapFechaInicioFinPlanesMedioHermanosSaved){
				Long idPlanMedioHermano = (Long) fechasByPlanesMedioHermanosIt
						.next();
				ArrayList<Timestamp> listFechas = mapFechaInicioFinPlanesMedioHermanosSaved
						.get(idPlanMedioHermano);
				Timestamp timeHermanoFechaInicio = Utilitarios
						.resetTimestampStartDate(listFechas.get(0));
				Timestamp timeHermanoFechaFin = Utilitarios
						.resetTimestampStartDate(listFechas.get(1));

				// if (!(timeHermanoFechaInicio.compareTo(timeFechaFin) > 0) &&
				// !(timeHermanoFechaFin.compareTo(timeFechaInicio) < 0)){
				if (!(timeHermanoFechaInicio.compareTo(timeFechaFin) > 0)) {
					if (!(timeHermanoFechaFin.compareTo(timeFechaInicio) < 0)) {
						try {
							planMedioCrucePeriodo = SessionServiceLocator
									.getPlanMedioSessionService().getPlanMedio(
											idPlanMedioHermano);
						} catch (GenericBusinessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						isValidate = false;
					}
				}
			}
		}
		return isValidate;
	}

	// END 4 JULIO

	/*
	 * Se cargan las Plantillas planMedioDetallesPlantilla y
	 * mapasComercialesPlantillaMultiple con los datos del excel
	 */
	private boolean cargarPlantillaTv(String tipoPlantilla)
			throws GenericBusinessException, OfficeXmlFileException, Exception {

		// ADD 29 ABRIL
		boolean isExcelCargado = true;
		// END 29 ABRIL

		planMedioDetallesPlantilla = new ArrayList<PlanMedioDetalleIf>();
		mapasComercialesPlantilla = new LinkedHashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>>();
		mapasComercialesPlantillaMultiple = new LinkedHashMap<PlanMedioDetalleIf, Collection<InfoComercialMultiple>>();

		String nombreHoja = "Pauta";
		ManejarExcel manejarExcel = new ManejarExcel();
		Collection<DatoObservacion> observaciones = new ArrayList<DatoObservacion>();

		String tipoMedio = "TV";
		if (getCbPautaRadio().isSelected()) {
			tipoMedio = "RADIO";
		} else if (getCbPautaCine().isSelected()) {
			tipoMedio = "CINE";
		} else {
			tipoMedio = "TV";
		}

		setearGenericoPautaRegular();

		if (tipoPlantilla.endsWith(TIPO_PLANTILLA_SENCILLA)) { // con numeros en
																// lugar de
																// letras
			// observaciones =
			// manejarExcel.convertirInformacionDesdeExcel(nombreHoja,
			// planMedioDetallesPlantilla, mapasComercialesPlantilla, null);
			// //comented 29 AGOSTO
			observaciones = manejarExcel.convertirInformacionDesdeExcel(
					nombreHoja, planMedioDetallesPlantilla,
					mapasComercialesPlantilla, null, null, tipoMedio,
					genericoPautaRegular); // ADD 29 AGOSTO
		} else {
			// MODIFIED 29 AGOSTO
			// observaciones =
			// manejarExcel.convertirInformacionDesdeExcel(nombreHoja,
			// planMedioDetallesPlantilla, null,
			// mapasComercialesPlantillaMultiple);

			// ADD 29 AGOSTO
			if (getCbPautaBasica().isSelected()
					&& !comercialesSeleccionadosTabla.isEmpty()
					&& comercialesSeleccionadosTabla.size() >= 1)
				observaciones = manejarExcel.convertirInformacionDesdeExcel(
						nombreHoja, planMedioDetallesPlantilla, null,
						mapasComercialesPlantillaMultiple,
						comercialesSeleccionadosTabla.get(0), tipoMedio,
						genericoPautaRegular);
			else
				observaciones = manejarExcel.convertirInformacionDesdeExcel(
						nombreHoja, planMedioDetallesPlantilla, null,
						mapasComercialesPlantillaMultiple, null, tipoMedio,
						genericoPautaRegular);
			// END 29 AGOSTO
		}

		// ADD 29 ABRIL
		int statusExcel = manejarExcel.getStatus();
		// END 29 ABRIL

		// ADD 29 ABRIL
		if (statusExcel == 0) {
			// ADD 26 ABRIL
			// PARA ELIMINAR LOS DATOS QUE CARGA EL EXCEL
			// Obtener los datos del PlanMedioMes hasta ahora solo hay uno x eso
			// es estatico con 0
			String tipoPlanMedioMesIf = null;
			String subPeriodoFechaInicioBeforeXls = null;
			String subPeriodoFechaFinBeforeXls = null;
			Timestamp fechaInicioPlanMedioMesIfTemp = null;
			Timestamp fechaFinPlanMedioMesIfTemp = null;
			PlanMedioMesIf planMedioMesIfTemp = null;

			// obtengo los datos actuales del PlanMedioMes antes de q sean
			// seteados
			// con los datos del excel cargado
			if (getPlanMedioMesVector().size() > 0) {// && !(getMode() ==
														// SpiritMode.FIND_MODE)){
				planMedioMesIfTemp = getPlanMedioMesVector().get(0);

				tipoPlanMedioMesIf = planMedioMesIfTemp.getTipo();
				fechaInicioPlanMedioMesIfTemp = planMedioMesIfTemp
						.getFechaInicio();
				fechaFinPlanMedioMesIfTemp = planMedioMesIfTemp.getFechaFin();

				subPeriodoFechaInicioBeforeXls = Utilitarios
						.getFechaUppercase(fechaInicioPlanMedioMesIfTemp);
				subPeriodoFechaFinBeforeXls = Utilitarios
						.getFechaUppercase(fechaFinPlanMedioMesIfTemp);

			}// MODIFIED 27 ABRIL
				// datos de la tabla SubPeriodo de Planificacion
			if (tableSubPeriodo.getRowCount() > 0) {
				int cont = 0;
				for (int i = 0; i < tableSubPeriodo.getRowCount(); i++) {

					String tipoTabla = (String) tableSubPeriodo
							.getValueAt(i, 0);

					if (getCmbTipo().getSelectedItem().equals(
							NOMBRE_TIPO_EXPECTATIVA))
						tipoTabla = TIPO_EXPECTATIVA;
					else if (getCmbTipo().getSelectedItem().equals(
							NOMBRE_TIPO_LANZAMIENTO))
						tipoTabla = TIPO_LANZAMIENTO;
					else if (getCmbTipo().getSelectedItem().equals(
							NOMBRE_TIPO_MANTENIMIENTO))
						tipoTabla = TIPO_MANTENIMIENTO;
					else if (getCmbTipo().getSelectedItem().equals(
							NOMBRE_TIPO_PROMOCIONAL))
						tipoTabla = TIPO_PROMOCIONAL;

					// String tipoTabla = (String) tableSubPeriodo.getValueAt(i,
					// 0);

					String subPeriodoFechaInicioTabla = (String) tableSubPeriodo
							.getValueAt(i, 1);
					String subPeriodoFechaFinTabla = (String) tableSubPeriodo
							.getValueAt(i, 2);

					if (tipoPlanMedioMesIf.equals(tipoTabla)
							&& subPeriodoFechaInicioBeforeXls
									.equals(subPeriodoFechaInicioTabla)
							&& subPeriodoFechaFinBeforeXls
									.equals(subPeriodoFechaFinTabla)) {
						cont++;
					}
					if (cont >= 1) {
						// ADD 27 ABRIL
						PlanMedioMesIf bean = new PlanMedioMesData();
						setPlanMedioMesActualizadoIf(bean);
						getPlanMedioMesActualizadoIf().setTipo(
								tipoPlanMedioMesIf);
						getPlanMedioMesActualizadoIf().setFechaInicio(
								fechaInicioPlanMedioMesIfTemp);
						getPlanMedioMesActualizadoIf().setFechaFin(
								fechaFinPlanMedioMesIfTemp);
						// END 27 ABRIL

						PlanMedioMesIf planMedioMesDelete = getPlanMedioMesVector()
								.get(i);
						Timestamp timeStampDateInicio = planMedioMesDelete
								.getFechaInicio();
						Timestamp timeStampDateFin = planMedioMesDelete
								.getFechaFin();
						// seteamos los valores hora, minutos y segundos en la
						// cero hora
						timeStampDateInicio = Utilitarios
								.resetTimestampStartDate(timeStampDateInicio);
						timeStampDateFin = Utilitarios
								.resetTimestampStartDate(timeStampDateFin);

						java.util.Date fechaInicioTime = new java.util.Date(
								timeStampDateInicio.getTime());
						java.util.Date fechaFinTime = new java.util.Date(
								timeStampDateInicio.getTime());

						/*
						 * int contador = 0; boolean agregar = false; if
						 * (planMedioMesArray.size() > 0){ for (int j = 0; j <
						 * planMedioMesArray.size(); j++){ PlanMedioMesIf
						 * planMedioMesIfDeleteTemp = planMedioMesArray.get(j);
						 * 
						 * Timestamp timeStampDateInicioTemp =
						 * planMedioMesIfDeleteTemp.getFechaInicio(); Timestamp
						 * timeStampDateFinTemp =
						 * planMedioMesIfDeleteTemp.getFechaFin(); //seteamos
						 * los valores hora, minutos y segundos en la cero hora
						 * timeStampDateInicioTemp =
						 * Utilitarios.resetTimestampStartDate
						 * (timeStampDateInicioTemp); timeStampDateFinTemp =
						 * Utilitarios
						 * .resetTimestampStartDate(timeStampDateFinTemp);
						 * 
						 * //verifica si que plan medio mes se encuentra en la
						 * lista temporal if
						 * (planMedioMesDelete.getTipo().equals
						 * (planMedioMesIfDeleteTemp.getTipo()) &&
						 * fechaInicioTime.compareTo(timeStampDateInicioTemp) ==
						 * 0 && fechaFinTime.compareTo(timeStampDateFinTemp) ==
						 * 0 ){ //para indicar que no se puede agregar a la
						 * lista de planMedioMesRemovidoVector //xq si se
						 * encuentra en la lista temporal agregar = true; //se
						 * la remueve de la lista temporal
						 * planMedioMesArray.remove(j); } }
						 * 
						 * }28 abril
						 */
						// 27 ABRIL ULTIMISIOM
						// if (!agregar)//por verdad se agrega a la lista de
						// planes de Medio Mes a remover
						// planMedioMesRemovidoVector.add(getPlanMedioMesVector().get(i));
						// planMedioMesRemovidoVector.add(planMedioMesDelete);
						// finalmente se remueve el PlanMedioMes del vector
						// VER EL REY 27 ABRIL
						// getPlanMedioMesVector().remove(i);
					}
				}
			}
			// END 26 ABRIL
			// }//ADD 27 ABRIL

			// AKI GIOMY 1 JULIO VERIFICAR CRUCE CON LAS FECHAS DE LOS HERMANOS

			// ADD 21 ABRIL
			java.util.Date fechaInicio = manejarExcel.getFechaMenor();
			java.util.Date fechaFin = manejarExcel.getFechaMayor();

			// ADD 4 JULIO
			if (!validarFechasWithHermanos(fechaInicio, fechaFin)) {
				isExcelCargado = false;
				SpiritAlert
						.createAlert(
								"El Periodo del Plan de Medio del excel cargado se cruza con el Periodo del Plan de Medio código "
										+ planMedioCrucePeriodo.getCodigo(),
								SpiritAlert.WARNING);
			} else {// END 4 JULIO
				Calendar calendarInicioHelp = new GregorianCalendar();
				calendarInicioHelp.setTime(fechaInicio);
				Calendar calendarFinHelp = new GregorianCalendar();
				calendarFinHelp.setTime(fechaFin);

				getCmbPeriodoFechaInicio().setCalendar(calendarInicioHelp);
				getCmbPeriodoFechaFin().setCalendar(calendarFinHelp);
			}// ADD 4 JULIO

			/*
			 * getCmbPeriodoFechaInicio().getDateModel().setMinDate(
			 * calendarInicioHelp);
			 * getCmbPeriodoFechaFin().getDateModel().setMinDate
			 * (calendarInicioHelp);
			 * getCmbPeriodoFechaInicio().getDateModel().setMaxDate
			 * (calendarFinHelp);
			 * getCmbPeriodoFechaFin().getDateModel().setMaxDate
			 * (calendarFinHelp);
			 */

			// END 21 ABRIL

			// ADD 4 JULIO
			if (isExcelCargado) {
				// ADD 26 ABRIL
				// AHORA SE AGREGA LOS DATOS DE PLANIFICACION Y MEDIOS CON LAS
				// FECHAS DEL EXCEL CARGADO
				// LUEGO DE HABER BORRADO LOS ANTERIORES
				if (getPlanMedioMesVector().size() < 1) {
					// END 25 ABRIL
					PlanMedioMesIf bean = new PlanMedioMesData();
					setPlanMedioMesActualizadoIf(bean);

					if (getCmbTipo().getSelectedItem().equals(
							NOMBRE_TIPO_EXPECTATIVA))
						getPlanMedioMesActualizadoIf()
								.setTipo(TIPO_EXPECTATIVA);
					else if (getCmbTipo().getSelectedItem().equals(
							NOMBRE_TIPO_LANZAMIENTO))
						getPlanMedioMesActualizadoIf()
								.setTipo(TIPO_LANZAMIENTO);
					else if (getCmbTipo().getSelectedItem().equals(
							NOMBRE_TIPO_MANTENIMIENTO))
						getPlanMedioMesActualizadoIf().setTipo(
								TIPO_MANTENIMIENTO);
					else if (getCmbTipo().getSelectedItem().equals(
							NOMBRE_TIPO_PROMOCIONAL))
						getPlanMedioMesActualizadoIf()
								.setTipo(TIPO_PROMOCIONAL);

					getPlanMedioMesActualizadoIf().setFechaInicio(
							Utilitarios
									.fromSqlDateToTimestamp(new java.sql.Date(
											getCmbSubPeriodoFechaInicio()
													.getDate().getTime())));
					getPlanMedioMesActualizadoIf().setFechaFin(
							Utilitarios
									.fromSqlDateToTimestamp(new java.sql.Date(
											getCmbSubPeriodoFechaFin()
													.getDate().getTime())));

					getPlanMedioMesVector().add(getPlanMedioMesActualizadoIf());

					// ADD 25 ABRIL
					planMedioMesArray.add(getPlanMedioMesActualizadoIf());// 28
																			// abril
					// END 25 ABRIL

					tableSubPeriodo = (DefaultTableModel) getTblSubPeriodo()
							.getModel();
					Vector<String> fila = new Vector<String>();
					String tipo = getCmbTipo().getSelectedItem().toString();
					String subPeriodoFechaInicio = Utilitarios
							.getFechaUppercase(getCmbSubPeriodoFechaInicio()
									.getDate());
					String subPeriodoFechaFin = Utilitarios
							.getFechaUppercase(getCmbSubPeriodoFechaFin()
									.getDate());
					fila.add(tipo);
					fila.add(subPeriodoFechaInicio);
					fila.add(subPeriodoFechaFin);
					tableSubPeriodo.addRow(fila);

					getCmbTipoPeriodo().addItem(
							tipo + " (" + subPeriodoFechaInicio + " al "
									+ subPeriodoFechaFin + ")");

					// isCargaExcel = false;

					// MODIFIED 25 ABRIL POR FAVOR REVISAR MAS ADELANTE
					// GIOMY!!!!!!!!!!!!!!!!!!!!!!!!!
					/*
					 * getCmbTipoPeriodoMapa().addItem(tipo + " (" +
					 * subPeriodoFechaInicio + " al " + subPeriodoFechaFin +
					 * ")");
					 * 
					 * modelArbolTvColecciones.add(null);
					 * treePathsTvColecciones.add(null);
					 * tableScrollPaneTvColecciones.add(null);
					 * pautasTvColecciones.add(null);
					 * 
					 * treePathsPrensaColecciones.add(null);
					 * tableScrollPanePrensaColecciones.add(null);
					 */
					// END MODIFIED 25 ABRIL
					// GIOMY!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				}
				// ADD 27 ABRIL
				else {
					// System.out.println(dfhdf);
					int filaSeleccionada = 0;// getTblSubPeriodo().getSelectedRow();
					if (filaSeleccionada >= 0) {// && !(getMode() ==
												// SpiritMode.FIND_MODE)) {
						/*
						 * if(getCmbTipo().getSelectedItem().equals(
						 * NOMBRE_TIPO_EXPECTATIVA))
						 * getPlanMedioMesActualizadoIf
						 * ().setTipo(TIPO_EXPECTATIVA); else
						 * if(getCmbTipo().getSelectedItem
						 * ().equals(NOMBRE_TIPO_LANZAMIENTO))
						 * getPlanMedioMesActualizadoIf
						 * ().setTipo(TIPO_LANZAMIENTO); else
						 * if(getCmbTipo().getSelectedItem
						 * ().equals(NOMBRE_TIPO_MANTENIMIENTO))
						 * getPlanMedioMesActualizadoIf
						 * ().setTipo(TIPO_MANTENIMIENTO); else
						 * if(getCmbTipo().getSelectedItem
						 * ().equals(NOMBRE_TIPO_PROMOCIONAL))
						 * getPlanMedioMesActualizadoIf
						 * ().setTipo(TIPO_PROMOCIONAL);
						 */

						// ADD 25 ABRIL 28 abril
						/*
						 * PlanMedioMesIf planMedioMesSeleccionado =
						 * getPlanMedioMesVector().get(filaSeleccionada);
						 * boolean isPlanMedioMesInListTemp = false;
						 * if(planMedioMesArray.size()>0){ int contadorTemp = 0;
						 * for(int i = 0; i < planMedioMesArray.size(); i++){
						 * PlanMedioMesIf planMedioMesActualizarTemp =
						 * (PlanMedioMesIf) planMedioMesArray.get(i); //Si el
						 * comercial a eliminar se encuentra en la lista
						 * temporal if
						 * (planMedioMesSeleccionado.getTipo().equals
						 * (planMedioMesActualizarTemp.getTipo()) &&
						 * planMedioMesSeleccionado
						 * .getFechaInicio().equals(planMedioMesActualizarTemp
						 * .getFechaInicio()) &&
						 * planMedioMesSeleccionado.getFechaFin
						 * ().equals(planMedioMesActualizarTemp.getFechaFin())){
						 * contadorTemp++; }if (contadorTemp >= 1){
						 * isPlanMedioMesInListTemp = true;
						 * planMedioMesArray.remove(i); break; } } }
						 */// END 25 ABRIL 28 abril

						getPlanMedioMesActualizadoIf()
								.setFechaInicio(
										Utilitarios
												.fromSqlDateToTimestamp(new java.sql.Date(
														getCmbSubPeriodoFechaInicio()
																.getDate()
																.getTime())));
						getPlanMedioMesActualizadoIf()
								.setFechaFin(
										Utilitarios
												.fromSqlDateToTimestamp(new java.sql.Date(
														getCmbSubPeriodoFechaFin()
																.getDate()
																.getTime())));

						// getPlanMedioMesVector().add(filaSeleccionada,
						// getPlanMedioMesActualizadoIf());
						// getPlanMedioMesVector().remove(filaSeleccionada + 1);
						// 27 ABRIL
						getPlanMedioMesVector().get(0).setTipo(
								getPlanMedioMesActualizadoIf().getTipo());
						getPlanMedioMesVector().get(0)
								.setFechaInicio(
										getPlanMedioMesActualizadoIf()
												.getFechaInicio());
						getPlanMedioMesVector().get(0).setFechaFin(
								getPlanMedioMesActualizadoIf().getFechaFin());
						// 27 ABRIL

						// ADD 25 ABRIL
						// planMedioMesArray.add(getPlanMedioMesActualizadoIf());
						// 28 abril
						// END 25 ABRIL

						tableSubPeriodo = (DefaultTableModel) getTblSubPeriodo()
								.getModel();
						Vector<String> fila = new Vector<String>();
						String tipo = getCmbTipo().getSelectedItem().toString();
						String subPeriodoFechaInicio = Utilitarios
								.getFechaUppercase(getCmbSubPeriodoFechaInicio()
										.getDate());
						String subPeriodoFechaFin = Utilitarios
								.getFechaUppercase(getCmbSubPeriodoFechaFin()
										.getDate());
						fila.add(tipo);
						fila.add(subPeriodoFechaInicio);
						fila.add(subPeriodoFechaFin);
						tableSubPeriodo.insertRow(filaSeleccionada, fila);
						tableSubPeriodo.removeRow(filaSeleccionada + 1);

						// 28 ABRIL
						// getCmbTipoPeriodo().removeItemAt(filaSeleccionada);
						// 28 ABRIL
						getCmbTipoPeriodo().removeItemAt(filaSeleccionada);
						getCmbTipoPeriodo().insertItemAt(
								tipo + " (" + subPeriodoFechaInicio + " al "
										+ subPeriodoFechaFin + ")",
								filaSeleccionada);
						getCmbTipoPeriodo().setSelectedItem(
								tipo + " (" + subPeriodoFechaInicio + " al "
										+ subPeriodoFechaFin + ")");
						// getCmbTipoPeriodo().;

					} else {
						SpiritAlert
								.createAlert(
										"Debe seleccionar una fila para ser actualizada !",
										SpiritAlert.WARNING);
					}
				}// END 27 ABRIL
					// ADD 26 ABRIL
				/*
				 * else{ SpiritAlert.createAlert(
				 * "Actualmente solo se permite una Planificación!!!",
				 * SpiritAlert.INFORMATION); }//END 26 ABRIL //ADD 26 ABRIL
				 */

				nuevoPlan = true;

				if (observaciones.size() > 0) {
					new ObservacionesModel(Parametros.getMainFrame(),
							"Observaciones", observaciones);
				}
			}// ADD 4 JULIO
		}
		/*
		 * nuevoPlan = true; if ( observaciones.size() > 0 ){ new
		 * ObservacionesModel
		 * (Parametros.getMainFrame(),"Observaciones",observaciones); }
		 */
		else {// ADD 29 ABRIL
				// nuevoPlan = false;
			isExcelCargado = false;
		}// END 29 ABRIL

		return isExcelCargado;
	}

	ActionListener oActionListenerRbTipoPagoNormal = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getRbTipoPagoNormal().isSelected()) {
				getCbTipoPagoComision().setSelected(false);
				getTxtPorcentajeCanje().setText("");
				getTxtPorcentajeCanje().setEnabled(false);
			}
		}
	};

	ActionListener oActionListenerRbTipoPagoCanje = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getRbTipoPagoCanje().isSelected()) {
				getTxtPorcentajeCanje().setText("");
				getTxtPorcentajeCanje().setEnabled(true);
			}
		}
	};
	
	ActionListener oActionListenerCbComisionAdicional = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getCbComisionAdicional().isSelected()) {
				getTxtPorcentajeComisionAdicional().setText("");
				getTxtPorcentajeComisionAdicional().setEnabled(true);
				getTxtPorcentajeComisionAdicional().setEditable(true);
			}else{
				getTxtPorcentajeComisionAdicional().setText("");
				getTxtPorcentajeComisionAdicional().setEnabled(false);
				getTxtPorcentajeComisionAdicional().setEditable(false);
			}
		}
	};

	ActionListener oActionListenerBtnCorporacion = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			corporacionCriteria = new CorporacionCriteria("Corporaciones",
					Parametros.getIdEmpresa());
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.add(80);
			anchoColumnas.add(500);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
					corporacionCriteria,
					JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas,
					null);
			if (popupFinder.getElementoSeleccionado() != null) {
				corporacionIf = (CorporacionIf) popupFinder
						.getElementoSeleccionado();
				getTxtCorporacion().setText(
						corporacionIf.getCodigo() + " - "
								+ corporacionIf.getNombre());
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

			if (corporacionIf != null) {
				idCorporacion = corporacionIf.getId();
			}

			clienteCriteria = new ClienteCriteria("Clientes", idCorporacion,
					CODIGO_TIPO_CLIENTE);
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.add(80);
			anchoColumnas.add(300);
			anchoColumnas.add(300);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
					clienteCriteria,
					JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas,
					null);
			if (popupFinder.getElementoSeleccionado() != null) {
				clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
				getTxtCliente().setText(clienteIf.getNombreLegal());

				if (clienteIf.getInformacionAdc() != null
						&& !clienteIf.getInformacionAdc().equals("")) {
					SpiritAlert.createAlert(
							"INFORMACIÓN: \n" + clienteIf.getInformacionAdc(),
							SpiritAlert.INFORMATION);
				}

				cleanTablaComercial();
				cargarTablaComercial();
				if (corporacionIf == null) {
					try {
						corporacionIf = SessionServiceLocator
								.getCorporacionSessionService().getCorporacion(
										clienteIf.getCorporacionId());
						getTxtCorporacion().setText(
								corporacionIf.getCodigo() + " - "
										+ corporacionIf.getNombre());
					} catch (GenericBusinessException e) {
						SpiritAlert
								.createAlert(
										"Se ha producido un error al setear el nombre de la Corporación",
										SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}

				clienteOficinaIf = null;
				getTxtOficina().setText("");

				try {
					Collection<ClienteOficinaIf> oficinas = SessionServiceLocator
							.getClienteOficinaSessionService()
							.findClienteOficinaByClienteId(clienteIf.getId());
					if (oficinas.size() == 1) {
						clienteOficinaIf = oficinas.iterator().next();

						iniciarMapaTv();
						cleanTablaTGRPtv();

						setClienteOficina();
					}
				} catch (Exception e) {
					e.printStackTrace();
					SpiritAlert
							.createAlert(
									"Se ha producido un error al consultar la oficina del cliente",
									SpiritAlert.ERROR);
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

			clienteOficinaCriteria = new ClienteOficinaCriteria(
					tituloVentanaBusqueda, idCorporacion, idCliente,
					tipoCliente, "", false);
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.addElement(70);
			anchoColumnas.addElement(200);
			anchoColumnas.addElement(80);
			anchoColumnas.addElement(230);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
					clienteOficinaCriteria,
					JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas,
					null);
			if (popupFinder.getElementoSeleccionado() != null) {
				clienteOficinaIf = (ClienteOficinaIf) popupFinder
						.getElementoSeleccionado();

				iniciarMapaTv();
				cleanTablaTGRPtv();

				if (clienteIf == null) {
					try {
						clienteIf = SessionServiceLocator
								.getClienteSessionService().getCliente(
										clienteOficinaIf.getClienteId());
						getTxtCliente().setText(clienteIf.getNombreLegal());

						if (clienteIf.getInformacionAdc() != null
								&& !clienteIf.getInformacionAdc().equals("")) {
							SpiritAlert.createAlert("INFORMACIÓN: \n"
									+ clienteIf.getInformacionAdc(),
									SpiritAlert.INFORMATION);
						}

						if (corporacionIf == null) {
							corporacionIf = SessionServiceLocator
									.getCorporacionSessionService()
									.getCorporacion(
											clienteIf.getCorporacionId());
							getTxtCorporacion().setText(
									corporacionIf.getNombre());
						}
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error",
								SpiritAlert.ERROR);
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
		getTxtOficina().setText(
				clienteOficinaIf.getCodigo() + " - "
						+ clienteOficinaIf.getDescripcion());
		cargarOrdenTrabajo();
	}

	private void cargarOrdenTrabajo() {
		try {
			boolean saveMode = false;
			if (getMode() == SpiritMode.SAVE_MODE)
				saveMode = true;

			ArrayList<OrdenTrabajoIf> ordenesTrabajo = (ArrayList) SessionServiceLocator
					.getOrdenTrabajoSessionService()
					.findOrdenTrabajoPendienteByClienteOficinaIdAndTipoOrden(
							clienteOficinaIf.getId(), TIPO_ORDEN_MEDIOS,
							saveMode);
			if (!ordenesTrabajo.isEmpty()) {
				SpiritComboBoxModel cmbModelOrdenTrabajo = new SpiritComboBoxModel(
						ordenesTrabajo);
				getCmbOrdenTrabajo().setModel(cmbModelOrdenTrabajo);
				getCmbOrdenTrabajo().setSelectedIndex(0);
				getCmbOrdenTrabajo().setEnabled(true);
				tipoOrden = (TipoOrdenIf) SessionServiceLocator
						.getTipoOrdenSessionService().findTipoOrdenByTipo("M")
						.iterator().next();
				ordenTrabajoIf = (OrdenTrabajoIf) getCmbOrdenTrabajo()
						.getSelectedItem();
				ArrayList<OrdenTrabajoDetalleIf> ordenesTrabajoDetalle = (ArrayList) SessionServiceLocator
						.getOrdenTrabajoDetalleSessionService()
						.findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajoAndEstado(
								tipoOrden.getId(), ordenTrabajoIf.getId());

				// Cargo los subtipos de acuerdo al tipo y orden de trabajo
				// escogido
				getCmbOrdenTrabajoDetalle().removeAllItems();
				Iterator itOrdenTrabajoDetalleCollection = ordenesTrabajoDetalle
						.iterator();

				while (itOrdenTrabajoDetalleCollection.hasNext()) {
					ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) itOrdenTrabajoDetalleCollection
							.next();

					SubtipoOrdenIf subtipoOrden = SessionServiceLocator
							.getSubtipoOrdenSessionService().getSubtipoOrden(
									ordenTrabajoDetalleIf.getSubtipoId());
					EmpleadoIf empleado = SessionServiceLocator
							.getEmpleadoSessionService().getEmpleado(
									ordenTrabajoDetalleIf.getAsignadoaId());

					InfoOrdenTrabajoDetalle info = new InfoOrdenTrabajoDetalle(
							ordenTrabajoDetalleIf, subtipoOrden.getNombre()
									+ " - "
									+ empleado.getNombres().split(" ")[0] + " "
									+ empleado.getApellidos().split(" ")[0]);

					getCmbOrdenTrabajoDetalle().addItem(info);
				}

				CampanaIf campana = SessionServiceLocator
						.getCampanaSessionService().getCampana(
								ordenTrabajoIf.getCampanaId());
				getTxtCampana().setText(
						campana.getCodigo() + " - " + campana.getNombre());
				// ADD 17 JUNIO
				campanaIf = new CampanaData();
				campanaIf = campana;
				// END 17 JUNIO
				cargarTablaComercial();

			} else {
				getCmbOrdenTrabajo().removeAllItems();
				getCmbOrdenTrabajo().setSelectedIndex(-1);
				getCmbOrdenTrabajo().setEnabled(false);
				SpiritAlert
						.createAlert(
								"No se encontraron Ordenes de Trabajo pendientes para el Cliente seleccionado.\n"
										+ "Ingresar una Orden para poder crear el Plan de Medios",
								SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert
					.createAlert(
							"Se ha producido un error al cargar el combo Orden de Trabajo",
							SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	ActionListener oActionListenerCmbTarget = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			listenerCmbTarget();
		}
	};

	private void listenerCmbTarget() {
		grupoObjetivoIf = (GrupoObjetivoIf) getCmbTarget().getModel()
				.getSelectedItem();

		if (grupoObjetivoIf != null) {
			guayaquil = grupoObjetivoIf.getCiudad1().doubleValue();
			quito = grupoObjetivoIf.getCiudad2().doubleValue();
			totalGuayaquilQuito = guayaquil + quito;

			margenGuayaquil = guayaquil / totalGuayaquilQuito;
			margenQuito = quito / totalGuayaquilQuito;

			getTxtGuayaquil().setText(
					formatoDecimal.format(Utilitarios
							.redondeoValor(grupoObjetivoIf.getCiudad1())));
			getTxtQuito().setText(
					formatoDecimal.format(Utilitarios
							.redondeoValor(grupoObjetivoIf.getCiudad2())));
			getTxtTotalCiudad().setText(
					formatoDecimal.format(Utilitarios
							.redondeoValor(totalGuayaquilQuito)));
			getBtnGuayaquil().setEnabled(true);
			getBtnQuito().setEnabled(true);
			getBtnTotalCiudad().setEnabled(true);
		}
	}

	// ADD 26 ABRIL
	/*
	 * ActionListener oActionListenerCmbSubPeriodoFechaInicio = new
	 * ActionListener(){ public void actionPerformed(ActionEvent evento) {
	 * if(getCmbSubPeriodoFechaInicio().getCalendar() != null){
	 * calendarSubFechaInicio = getCmbSubPeriodoFechaInicio().getCalendar();
	 * 
	 * if(calendarSubFechaInicio.after(calendarSubFechaFin)){
	 * getCmbSubPeriodoFechaFin().setCalendar(calendarSubFechaInicio); }
	 * 
	 * /*if (calendarFechaInicio.compareTo(calendarSubFechaInicio) != 0 &&
	 * !isCargaExcel && !isClean){ SpiritAlert.createAlert(
	 * "La Fecha Inicio del Plan Medio Mes debe coincidir \n con la Fecha Inicio del Plan Medio TV!!!"
	 * , SpiritAlert.WARNING);
	 * getCmbSubPeriodoFechaInicio().setCalendar(calendarFechaInicio); }* / } }
	 * };
	 * 
	 * ActionListener oActionListenerCmbSubPeriodoFechaFin = new
	 * ActionListener(){ public void actionPerformed(ActionEvent evento) {
	 * if(getCmbSubPeriodoFechaFin().getCalendar() != null){ calendarSubFechaFin
	 * = getCmbSubPeriodoFechaFin().getCalendar();
	 * 
	 * if(calendarSubFechaFin.before(calendarSubFechaInicio)){
	 * getCmbSubPeriodoFechaInicio().setCalendar(calendarSubFechaFin); }
	 * 
	 * /*if (calendarFechaInicio.compareTo(calendarSubFechaInicio) != 0 &&
	 * calendarFechaFin.compareTo(calendarSubFechaFin) != 0 && !isCargaExcel &&
	 * !isClean){ SpiritAlert.createAlert(
	 * "La Fecha Fin del Plan Medio Mes debe coincidir \n con la Fecha Fin del Plan Medio TV!!!"
	 * , SpiritAlert.WARNING);
	 * getCmbSubPeriodoFechaFin().setCalendar(calendarFechaFin); }else if
	 * (isClean){ isClean = false; }//else if (){}* / } } };
	 */

	// END 26 ABRIL

	ActionListener oActionListenerCmbPeriodoFechaInicio = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getCmbPeriodoFechaInicio().getCalendar() != null) {
				calendarFechaInicio = getCmbPeriodoFechaInicio().getCalendar();

				/*
				 * if(calendarFechaInicio.after(calendarFechaFin)){
				 * getCmbPeriodoFechaFin().setCalendar(calendarFechaInicio); }
				 */
				getCmbSubPeriodoFechaInicio().setCalendar(calendarFechaInicio);
				// 28 de ABRIL REVISAR
				/*
				 * try{ getCmbSubPeriodoFechaInicio().getDateModel().setMinDate(
				 * calendarFechaInicio);
				 * getCmbSubPeriodoFechaFin().getDateModel(
				 * ).setMinDate(calendarFechaInicio);
				 * getCmbSubPeriodoFechaInicio
				 * ().getDateModel().setMaxDate(calendarFechaFin);
				 * getCmbSubPeriodoFechaFin
				 * ().getDateModel().setMaxDate(calendarFechaFin); //28 de ABRIL
				 * REVISAR END
				 * getCmbSubPeriodoFechaInicio().setCalendar(calendarFechaInicio
				 * ); }catch(IllegalArgumentException e){
				 * SpiritAlert.createAlert
				 * ("Verificar la Fecha de la PC, por favor."
				 * ,SpiritAlert.WARNING); }
				 */
			}
		}
	};

	ActionListener oActionListenerCmbPeriodoFechaFin = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getCmbPeriodoFechaFin().getCalendar() != null) {
				calendarFechaFin = getCmbPeriodoFechaFin().getCalendar();

				/*
				 * if(calendarFechaFin.before(calendarFechaInicio)){
				 * getCmbPeriodoFechaInicio().setCalendar(calendarFechaFin); }
				 */

				getCmbSubPeriodoFechaFin().setCalendar(calendarFechaFin);
				// 28 ABRIL REVISAR GIOMY
				/*
				 * try{ getCmbSubPeriodoFechaInicio().getDateModel().setMaxDate(
				 * calendarFechaFin);
				 * getCmbSubPeriodoFechaFin().getDateModel().setMaxDate
				 * (calendarFechaFin);
				 * getCmbSubPeriodoFechaInicio().getDateModel
				 * ().setMinDate(calendarFechaInicio);
				 * getCmbSubPeriodoFechaFin()
				 * .getDateModel().setMinDate(calendarFechaInicio); //28 ABRIL
				 * REVISAR GIOMY
				 * getCmbSubPeriodoFechaFin().setCalendar(calendarFechaFin);
				 * //getCmbSubPeriodoFechaInicio
				 * ().setCalendar(calendarFechaInicio);
				 * }catch(IllegalArgumentException e){
				 * SpiritAlert.createAlert("Verificar la Fecha de la PC, por favor."
				 * ,SpiritAlert.WARNING); }
				 */

			}
		}
	};

	ActionListener oActionListenerBtnAgregarDetalle = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			// ADD 25 ABRIL
			// hasta estos momentos solo permite un Plan Medio Mes
			if (getPlanMedioMesVector().size() < 1) {
				// END 25 ABRIL
				PlanMedioMesIf bean = new PlanMedioMesData();
				setPlanMedioMesActualizadoIf(bean);

				if (getCmbTipo().getSelectedItem().equals(
						NOMBRE_TIPO_EXPECTATIVA))
					getPlanMedioMesActualizadoIf().setTipo(TIPO_EXPECTATIVA);
				else if (getCmbTipo().getSelectedItem().equals(
						NOMBRE_TIPO_LANZAMIENTO))
					getPlanMedioMesActualizadoIf().setTipo(TIPO_LANZAMIENTO);
				else if (getCmbTipo().getSelectedItem().equals(
						NOMBRE_TIPO_MANTENIMIENTO))
					getPlanMedioMesActualizadoIf().setTipo(TIPO_MANTENIMIENTO);
				else if (getCmbTipo().getSelectedItem().equals(
						NOMBRE_TIPO_PROMOCIONAL))
					getPlanMedioMesActualizadoIf().setTipo(TIPO_PROMOCIONAL);

				getPlanMedioMesActualizadoIf().setFechaInicio(
						Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(
								getCmbSubPeriodoFechaInicio().getDate()
										.getTime())));
				getPlanMedioMesActualizadoIf().setFechaFin(
						Utilitarios
								.fromSqlDateToTimestamp(new java.sql.Date(
										getCmbSubPeriodoFechaFin().getDate()
												.getTime())));

				getPlanMedioMesVector().add(getPlanMedioMesActualizadoIf());

				// ADD 25 ABRIL
				planMedioMesArray.add(getPlanMedioMesActualizadoIf());
				// END 25 ABRIL

				tableSubPeriodo = (DefaultTableModel) getTblSubPeriodo()
						.getModel();
				Vector<String> fila = new Vector<String>();
				String tipo = getCmbTipo().getSelectedItem().toString();
				String subPeriodoFechaInicio = Utilitarios
						.getFechaUppercase(getCmbSubPeriodoFechaInicio()
								.getDate());
				String subPeriodoFechaFin = Utilitarios
						.getFechaUppercase(getCmbSubPeriodoFechaFin().getDate());
				fila.add(tipo);
				fila.add(subPeriodoFechaInicio);
				fila.add(subPeriodoFechaFin);
				tableSubPeriodo.addRow(fila);

				getCmbTipoPeriodo().addItem(
						tipo + " (" + subPeriodoFechaInicio + " al "
								+ subPeriodoFechaFin + ")");
				// MODIFIED 25 ABRIL POR FAVOR REVISAR MAS ADELANTE
				// GIOMY!!!!!!!!!!!!!!!!!!!!!!!!!
				/*
				 * getCmbTipoPeriodoMapa().addItem(tipo + " (" +
				 * subPeriodoFechaInicio + " al " + subPeriodoFechaFin + ")");
				 * 
				 * modelArbolTvColecciones.add(null);
				 * treePathsTvColecciones.add(null);
				 * tableScrollPaneTvColecciones.add(null);
				 * pautasTvColecciones.add(null);
				 * 
				 * treePathsPrensaColecciones.add(null);
				 * tableScrollPanePrensaColecciones.add(null);
				 */
				// END MODIFIED 25 ABRIL
				// GIOMY!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			}// ADD 25 ABRIL
			else {
				SpiritAlert.createAlert(
						"Actualmente solo se permite una Planificación!!!",
						SpiritAlert.INFORMATION);
			}// END 25 ABRIL
		}
	};

	ActionListener oActionListenerBtnActualizarDetalle = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			int filaSeleccionada = getTblSubPeriodo().getSelectedRow();
			if (filaSeleccionada >= 0) {
				if (getCmbTipo().getSelectedItem().equals(
						NOMBRE_TIPO_EXPECTATIVA))
					getPlanMedioMesActualizadoIf().setTipo(TIPO_EXPECTATIVA);
				else if (getCmbTipo().getSelectedItem().equals(
						NOMBRE_TIPO_LANZAMIENTO))
					getPlanMedioMesActualizadoIf().setTipo(TIPO_LANZAMIENTO);
				else if (getCmbTipo().getSelectedItem().equals(
						NOMBRE_TIPO_MANTENIMIENTO))
					getPlanMedioMesActualizadoIf().setTipo(TIPO_MANTENIMIENTO);
				else if (getCmbTipo().getSelectedItem().equals(
						NOMBRE_TIPO_PROMOCIONAL))
					getPlanMedioMesActualizadoIf().setTipo(TIPO_PROMOCIONAL);

				// REVISAR ZULLY
				/*
				 * getPlanMedioMesActualizadoIf().setFechaInicio(new
				 * java.sql.Date
				 * (getCmbSubPeriodoFechaInicio().getDate().getTime()));
				 * getPlanMedioMesActualizadoIf().setFechaFin(new
				 * java.sql.Date(getCmbSubPeriodoFechaFin
				 * ().getDate().getTime()));
				 */
				// actualizando al PlanMedioMes

				// ADD 25 ABRIL
				/*
				 * PlanMedioMesIf planMedioMesSeleccionado =
				 * getPlanMedioMesVector().get(filaSeleccionada); for (int i =
				 * 0; i < planMedioMesArray.size(); i++){ PlanMedioMesIf
				 * planMedioIfTemp = planMedioMesArray.get(i); if
				 * (planMedioMesSeleccionado
				 * .getTipo().compareTo(planMedioIfTemp.getTipo()) == 0 &&
				 * planMedioMesSeleccionado
				 * .getFechaInicio().compareTo(planMedioIfTemp.getFechaInicio())
				 * == 0 &&
				 * planMedioMesSeleccionado.getFechaFin().compareTo(planMedioIfTemp
				 * .getFechaFin()) == 0) planMedioMesArray.remove(i); }
				 */
				// END 25 ABRIL

				// ADD 25 ABRIL
				PlanMedioMesIf planMedioMesSeleccionado = getPlanMedioMesVector()
						.get(filaSeleccionada);
				boolean isPlanMedioMesInListTemp = false;
				if (planMedioMesArray.size() > 0) {
					int contadorTemp = 0;
					for (int i = 0; i < planMedioMesArray.size(); i++) {
						PlanMedioMesIf planMedioMesActualizarTemp = (PlanMedioMesIf) planMedioMesArray
								.get(i);
						// Si el comercial a eliminar se encuentra en la lista
						// temporal
						if (planMedioMesSeleccionado.getTipo().equals(
								planMedioMesActualizarTemp.getTipo())
								&& planMedioMesSeleccionado.getFechaInicio()
										.equals(planMedioMesActualizarTemp
												.getFechaInicio())
								&& planMedioMesSeleccionado.getFechaFin()
										.equals(planMedioMesActualizarTemp
												.getFechaFin())) {
							contadorTemp++;
						}
						if (contadorTemp >= 1) {
							isPlanMedioMesInListTemp = true;
							planMedioMesArray.remove(i);
							break;
						}
					}
				}// END 25 ABRIL

				getPlanMedioMesActualizadoIf().setFechaInicio(
						Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(
								getCmbSubPeriodoFechaInicio().getDate()
										.getTime())));
				getPlanMedioMesActualizadoIf().setFechaFin(
						Utilitarios
								.fromSqlDateToTimestamp(new java.sql.Date(
										getCmbSubPeriodoFechaFin().getDate()
												.getTime())));

				getPlanMedioMesVector().add(filaSeleccionada,
						getPlanMedioMesActualizadoIf());
				getPlanMedioMesVector().remove(filaSeleccionada + 1);

				// ADD 25 ABRIL
				planMedioMesArray.add(getPlanMedioMesActualizadoIf());
				// END 25 ABRIL

				tableSubPeriodo = (DefaultTableModel) getTblSubPeriodo()
						.getModel();
				Vector<String> fila = new Vector<String>();
				String tipo = getCmbTipo().getSelectedItem().toString();
				String subPeriodoFechaInicio = Utilitarios
						.getFechaUppercase(getCmbSubPeriodoFechaInicio()
								.getDate());
				String subPeriodoFechaFin = Utilitarios
						.getFechaUppercase(getCmbSubPeriodoFechaFin().getDate());
				fila.add(tipo);
				fila.add(subPeriodoFechaInicio);
				fila.add(subPeriodoFechaFin);
				tableSubPeriodo.insertRow(filaSeleccionada, fila);
				tableSubPeriodo.removeRow(filaSeleccionada + 1);

				// 28 ABRIL
				System.out.println("filaSeleccionada: " + filaSeleccionada);
				getCmbTipoPeriodo().removeItemAt(filaSeleccionada);
				getCmbTipoPeriodo().insertItemAt(
						tipo + " (" + subPeriodoFechaInicio + " al "
								+ subPeriodoFechaFin + ")", filaSeleccionada);
				// getCmbTipoPeriodo().removeItemAt(filaSeleccionada + 1);

			} else {
				SpiritAlert.createAlert(
						"Debe seleccionar una fila para ser actualizada !",
						SpiritAlert.WARNING);
			}
		}
	};

	ActionListener oActionListenerBtnRemoverDetalle = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			int filaSeleccionada = getTblSubPeriodo().getSelectedRow();
			// boolean agregar = false;
			if (filaSeleccionada >= 0) {
				int opcion = JOptionPane
						.showOptionDialog(
								null,
								"¿Está seguro que desea remover la fila seleccionada?!",
								"Confirmación", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					// AKI ADD 25 ABRIL
					// ADD 25 ABRIL
					PlanMedioMesIf planMedioMesSeleccionado = getPlanMedioMesVector()
							.get(filaSeleccionada);
					/*
					 * for (int i = 0; i < planMedioMesArray.size(); i++){
					 * PlanMedioMesIf planMedioIfTemp =
					 * planMedioMesArray.get(i); if
					 * (planMedioMesSeleccionado.getTipo
					 * ().compareTo(planMedioIfTemp.getTipo()) == 0 &&
					 * planMedioMesSeleccionado
					 * .getFechaInicio().compareTo(planMedioIfTemp
					 * .getFechaInicio()) == 0 &&
					 * planMedioMesSeleccionado.getFechaFin
					 * ().compareTo(planMedioIfTemp.getFechaFin()) == 0) agregar
					 * = true; }
					 */

					boolean isPlanMedioMesInListTemp = false;
					if (planMedioMesArray.size() > 0) {
						int contadorTemp = 0;
						for (int i = 0; i < planMedioMesArray.size(); i++) {
							PlanMedioMesIf planMedioMesActualizarTemp = (PlanMedioMesIf) planMedioMesArray
									.get(i);
							// Si el comercial a eliminar se encuentra en la
							// lista temporal
							if (planMedioMesSeleccionado.getTipo().equals(
									planMedioMesActualizarTemp.getTipo())
									&& planMedioMesSeleccionado
											.getFechaInicio().equals(
													planMedioMesActualizarTemp
															.getFechaInicio())
									&& planMedioMesSeleccionado.getFechaFin()
											.equals(planMedioMesActualizarTemp
													.getFechaFin())) {
								contadorTemp++;
							}
							if (contadorTemp >= 1) {
								isPlanMedioMesInListTemp = true;
								planMedioMesArray.remove(i);
								break;
							}
						}
					}// END 25 ABRIL

					if (!isPlanMedioMesInListTemp)
						// END 25 ABRIL
						planMedioMesRemovidoVector.add(getPlanMedioMesVector()
								.get(filaSeleccionada));
					// AKI END 25 ABRIL
					System.out
							.println("FILA SELECCIONADA: " + filaSeleccionada);
					getPlanMedioMesVector().remove(filaSeleccionada);
					tableSubPeriodo.removeRow(filaSeleccionada);
					getCmbTipoPeriodo().removeItemAt(filaSeleccionada);
					// MODIFIED 25 ABRIL POR FAVOR REVISAR MAS ADELANTE
					// GIOMY!!!!!!!!!!!!!!!!!!!!!!!!!
					// getCmbTipoPeriodoMapa().removeItemAt(filaSeleccionada);

					// modelArbolTvColecciones.remove(filaSeleccionada);
					// treePathsTvColecciones.remove(filaSeleccionada);
					// tableScrollPaneTvColecciones.remove(filaSeleccionada);
					// pautasTvColecciones.remove(filaSeleccionada);

					// treePathsPrensaColecciones.remove(filaSeleccionada);
					// tableScrollPanePrensaColecciones.remove(filaSeleccionada);
					// END MODIFIED 25 ABRIL
					// GIOMY!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				}
			} else {
				SpiritAlert.createAlert(
						"Debe seleccionar una fila para ser removida !",
						SpiritAlert.WARNING);
			}
		}
	};

	// ADD 17 JUNIO
	private String getEstadoToPlanMedioOriginal() {
		String estado = "";
		if (getCmbEstado().getSelectedItem() != null) {
			if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_EN_PROCESO))
				estado = ESTADO_EN_PROCESO;
			else if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_PENDIENTE))
				estado = ESTADO_PENDIENTE;
			/*
			 * else if
			 * (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_APROBADO))
			 * estado = ESTADO_APROBADO;
			 */
		}

		return estado;
	}// END ADD 17 JUNIO

	// METODO NO UTILIZADO 17 JUNIO
	// ADD 16 JUNIO
	private Map buildQueryForPlanMedioOriginal() {
		Map aMap = new HashMap();

		if (getCmbEstado().getSelectedItem() != null) {
			if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_EN_PROCESO))
				aMap.put("estado", ESTADO_EN_PROCESO);
			else if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_PENDIENTE))
				aMap.put("estado", ESTADO_PENDIENTE);
			else if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_APROBADO))
				aMap.put("estado", ESTADO_APROBADO);
		}
		
		return aMap;
	}

	// END 16 JUNIO

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

		
		if (getCmbPeriodoFechaInicio().getSelectedItem() != null)
			aMap.put("fechaini", new java.sql.Date(getCmbPeriodoFechaInicio()
					.getDate().getTime()));

		if (getCmbPeriodoFechaFin().getSelectedItem() != null)
			aMap.put("fechafin", new java.sql.Date(getCmbPeriodoFechaFin()
					.getDate().getTime()));

		return aMap;
	}

	public String getEstadoToQuery() {
		String estado = "";
		if (getCmbEstado().getSelectedItem() != null) {
			if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_EN_PROCESO))
				estado = ESTADO_EN_PROCESO;
			else if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_PENDIENTE))
				estado = ESTADO_PENDIENTE;
			else if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_APROBADO))
				estado = ESTADO_APROBADO;
			else if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_FACTURADO))
				estado = ESTADO_FACTURADO;
			else if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_HISTORICO))
				estado = ESTADO_HISTORICO;
			else if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_PEDIDO))
				estado = ESTADO_PEDIDO;
			else if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_PREPAGADO))
				estado = ESTADO_PREPAGADO;
		}
		
		return estado;
	}

	public void findPlanMedioOriginal() {
		try {
			// Map mapa = buildQuery();
			// Map mapa = buildQueryForPlanMedioOriginal();
			String estado = getEstadoToPlanMedioOriginal();
			int tamanoLista = 0;
			// Long clienteId = null;
			// ADD 20 JUNIO
			Long clienteOficinaId = null;
			// END 20 JUNIO
			// ADD 17 JUNIO
			Long campanaId = null;

			// clienteId = clienteIf.getId();
			clienteOficinaId = clienteOficinaIf.getId();
			// ADD 17 JUNIO
			campanaId = campanaIf.getId();

			// tamanoLista =
			// SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByQueryAndByIdClienteSize(mapa,clienteId,
			// Parametros.getIdEmpresa());
			// tamanoLista =
			// SessionServiceLocator.getPlanMedioSessionService().findPlanMedioOriginalByQueryAndByIdClienteAndIdCampanaSize(clienteId,campanaId,
			// Parametros.getIdEmpresa(),estado.trim());
			// tamanoLista =
			// SessionServiceLocator.getPlanMedioSessionService().findPlanMedioOriginalByQueryAndByIdClienteAndIdCampanaSize(clienteOficinaId,campanaId,
			// Parametros.getIdEmpresa(),estado.trim());
			// MODIFIED 20 JUNIO
			System.out.println("planMedioTipo " + planMedioTipo);

			tamanoLista = SessionServiceLocator
					.getPlanMedioSessionService()
					.findPlanMedioOriginalByQueryAndByIdClienteAndIdCampanaSize(
							clienteOficinaId, campanaId,
							Parametros.getIdEmpresa(), planMedioTipo);

			if (tamanoLista > 0) {
				// PlanMedioOriginalCriteria planMedioOriginalCriteria = new
				// PlanMedioOriginalCriteria(clienteId,campanaId, estado);
				// PlanMedioOriginalCriteria planMedioOriginalCriteria = new
				// PlanMedioOriginalCriteria(clienteOficinaId,campanaId,
				// estado);
				// MODIFIED 20 JUNIO
				System.out.println("planMedioTipo " + planMedioTipo);
				PlanMedioOriginalCriteria planMedioOriginalCriteria = new PlanMedioOriginalCriteria(
						clienteOficinaId, campanaId, planMedioTipo);
				planMedioOriginalCriteria.setResultListSize(tamanoLista);
				// planMedioOriginalCriteria.setQueryBuilded(mapa);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
						planMedioOriginalCriteria,
						JDPopupFinderModel.BUSQUEDA_TODOS);
				if (popupFinder.getElementoSeleccionado() != null) {
					// COMENTED 16 JUNIO
					// getSelectedObject();
					// setea el Codigo del Plan Medio Original con el que tendra
					// relacion
					getSelectedOriginalObject();
					// 17 MAYO
					// Se llenan los datos del PLAN DE NEDIO buscado incluye
					// (plan de medio mes,plan medio detalle,mapa
					// comercial,ordenes de medio y ordenes de medio detalle,
					// ordenes de medio detalle mapa)
				}
			} else {
				SpiritAlert.createAlert(
						"No se encontraron registros para Plan Medio Original",
						SpiritAlert.WARNING);
			}

		} catch (GenericBusinessException e) {
			// ADD 24 AGOSTO
			e.printStackTrace();
			SpiritAlert
					.createAlert(
							"Error en la busqueda de información para Plan Medio Original",
							SpiritAlert.ERROR);
		}
	}

	// END 16 JUNIO

	public void find() {
		try {
			Map mapa = buildQuery();
			int tamanoLista = 0;
			Long clienteOficinaId = null;

			String estado = getEstadoToQuery();
			if (estado.compareTo("") != 0) {
				mapa.put("estado", estado);
			}

			if (clienteOficinaIf != null) {
				clienteOficinaId = clienteOficinaIf.getId();
				tamanoLista = SessionServiceLocator
						.getPlanMedioSessionService()
						.findPlanMedioByQueryAndByIdClienteSize(mapa,
								clienteOficinaId, Parametros.getIdEmpresa(),
								estado);
			} else {
				tamanoLista = SessionServiceLocator
						.getPlanMedioSessionService().findPlanMedioByQuerySize(
								mapa, Parametros.getIdEmpresa());
			}

			if (tamanoLista > 0) {
				// MODIFIED 24 JUNIO
				// PlanMedioCriteria planMedioCriteria = new
				// PlanMedioCriteria(clienteId);
				PlanMedioCriteria planMedioCriteria = new PlanMedioCriteria(
						clienteOficinaId, estado);
				planMedioCriteria.setResultListSize(tamanoLista);
				planMedioCriteria.setQueryBuilded(mapa);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
						planMedioCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if (popupFinder.getElementoSeleccionado() != null)
					getSelectedObject();
				// 17 MAYO
				// Se llenan los datos del PLAN DE NEDIO buscado incluye
				// (plan de medio mes,plan medio detalle,mapa comercial,ordenes
				// de medio y ordenes de medio detalle,
				// ordenes de medio detalle mapa)

			} else {
				SpiritAlert.createAlert("No se encontraron registros",
						SpiritAlert.WARNING);
			}

		} catch (GenericBusinessException e) {
			// ADD 24 AGOSTO
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la busqueda de información",
					SpiritAlert.ERROR);
		}
	}

	public void getSelectedOriginalObject() {
		try {
			// plan de medio guardado en la DB
			planMedioOriginalIf = (PlanMedioIf) popupFinder
					.getElementoSeleccionado();
			getTextPlanMedioRelacion().setText(planMedioOriginalIf.getCodigo());

			// MOVED 23 JUNIO
			planMedioIfSaved = planMedioOriginalIf;
			// END MOVED 23 JUNIO

			// ADD 21 JUNIO
			// para generar una NUEVA VERSION DEL PLAN DE MEDIO
			if (getCbPlanMedioNuevaVersion().isSelected()) {

				// ADD 4 JULIO
				mapFechaInicioFinPlanesMedioHermanosSaved.clear();

				PlanMedioIf planMedioHermanoMayor = null;

				ArrayList<Timestamp> listFechas = new ArrayList<Timestamp>();

				// mapFechaInicioFinPlanesMedioHermanosSaved.put(planMedioIfSaved.getId(),listFechas);
				Collection<PlanMedioIf> planesMedioHermanosColl = null;
				if (planMedioIfSaved.getPlanMedioHermanoId() != null) {

					planMedioHermanoMayor = SessionServiceLocator
							.getPlanMedioSessionService().getPlanMedio(
									planMedioIfSaved.getPlanMedioHermanoId());
					listFechas.add(planMedioHermanoMayor.getFechaInicio());
					listFechas.add(planMedioHermanoMayor.getFechaFin());
					mapFechaInicioFinPlanesMedioHermanosSaved.put(
							planMedioHermanoMayor.getId(), listFechas);

					planesMedioHermanosColl = (Collection) SessionServiceLocator
							.getPlanMedioSessionService()
							.findPlanMedioByIdPlanMedioHermanoNuevaVersion(
									planMedioIfSaved.getId(),
									planMedioIfSaved.getPlanMedioHermanoId());

					if (planesMedioHermanosColl != null
							&& planesMedioHermanosColl.size() > 0) {
						for (PlanMedioIf planMedioIfHermano : planesMedioHermanosColl) {
							ArrayList<Timestamp> listFechasHermanos = new ArrayList<Timestamp>();
							listFechasHermanos.add(planMedioIfHermano
									.getFechaInicio());
							listFechasHermanos.add(planMedioIfHermano
									.getFechaFin());
							mapFechaInicioFinPlanesMedioHermanosSaved.put(
									planMedioIfHermano.getId(),
									listFechasHermanos);
						}
					}
				} else {

					planesMedioHermanosColl = (Collection) SessionServiceLocator
							.getPlanMedioSessionService()
							.findPlanMedioByIdPlanMedioHermanoNuevaVersion(
									planMedioIfSaved.getId(),
									planMedioIfSaved.getId());

					if (planesMedioHermanosColl != null
							&& planesMedioHermanosColl.size() > 0) {
						for (PlanMedioIf planMedioIfHermano : planesMedioHermanosColl) {
							ArrayList<Timestamp> listFechasHermanos = new ArrayList<Timestamp>();
							listFechasHermanos.add(planMedioIfHermano
									.getFechaInicio());
							listFechasHermanos.add(planMedioIfHermano
									.getFechaFin());
							mapFechaInicioFinPlanesMedioHermanosSaved.put(
									planMedioIfHermano.getId(),
									listFechasHermanos);
						}
					}
				}

				// END 4 JULIO

				// MOVED 23 JUNIO
				// planMedioIfSaved = planMedioOriginalIf;

				Collection planMedioMesColeccion = SessionServiceLocator
						.getPlanMedioMesSessionService()
						.findPlanMedioMesByPlanmedioId(planMedioIfSaved.getId());
				Iterator planMedioMesIterator = planMedioMesColeccion
						.iterator();

				while (planMedioMesIterator.hasNext()) {
					PlanMedioMesIf planMedioMesIf = (PlanMedioMesIf) planMedioMesIterator
							.next();

					planMedioDetallesPlantillaSaved.clear();
					// Se encuentra los planes medios detalle del plan de medio
					// a actualizar o consultar
					// Collection<PlanMedioDetalleIf> planMedioDetallesPlantilla
					planMedioDetallesPlantillaSaved = SessionServiceLocator
							.getPlanMedioDetalleSessionService()
							.findPlanMedioDetalleByPlanMedioMesId(
									planMedioMesIf.getId());

				}

				// planMedioDetallesPlantillaSaved = planMedioDetallesPlantilla;
				clienteOficinaIfSaved = clienteOficinaIf;
				// COMENTED 12 OCTUBRE ADD 15 JUNIO
				/*
				 * if (planMedioIfSaved.getOrdenesXCanal().compareTo("SI") !=
				 * 0){ generarOrdenesMedioForUpdate(planMedioIfSaved, false);
				 * //COMENTED 27 JUNIO cargarOrdenesMedioaAgrupadasForUpdate();
				 * //CONSIDERADA Y ADD EL 28 JUNIO //MODIFIED 27 JUNIO //if
				 * (cargarOrdenesMedioaAgrupadasForUpdate()) //
				 * SpiritAlert.createAlert(
				 * "Existen Ordenes de Medio Ingresadas. \n por favor Verifique la(s) Orden(es) Medio y Anule la(s) Compra(s)"
				 * , SpiritAlert.WARNING); }else{
				 * generarOrdenesMedioForUpdate(planMedioIfSaved, true);
				 * cargarOrdenesMedioaAgrupadasXCanalForUpdate(); }
				 */

				// ADD 12 OCTUBRE
				if (planMedioIfSaved.getOrdenMedioTipo().compareTo(
						ORDEN_MEDIO_TIPO_CANAL) == 0) {
					generarOrdenesMedioForUpdate(planMedioIfSaved, 1);
					cargarOrdenesMedioaAgrupadasXCanalForUpdate();
				} else if (planMedioIfSaved.getOrdenMedioTipo().compareTo(
						ORDEN_MEDIO_TIPO_PRODUCTO_COMERCIAL) == 0) {
					generarOrdenesMedioForUpdate(planMedioIfSaved, 2);
					// cargarOrdenesMedioaAgrupadasForUpdate(); COMENTED 13
					// OCTUBRE
					cargarOrdenesMedioaAgrupadasXProductoClienteForUpdate(); // ADD
																				// 13
																				// OCTUBRE
				} else if (planMedioIfSaved.getOrdenMedioTipo().compareTo(
						ORDEN_MEDIO_TIPO_VERSION) == 0) {
					generarOrdenesMedioForUpdate(planMedioIfSaved, 3);
					cargarOrdenesMedioaAgrupadasXCampanaProductoVersionForUpdate();
				}
				// END 12 OCTUBRE

				// ADD 28 JUNIO
				/*
				 * if(verificarExistenciaOrdenesMedioIngresadas()){
				 * SpiritAlert.createAlert(
				 * "Existen Ordenes de Medio Ingresadas. \n Verifique los codigos de las Ordenes de Medio  \n y Anule las Compras"
				 * , SpiritAlert.WARNING); }
				 */
			}// END 21 JUNIO
				// ADD 1 JULIO para luego validar las fechas del archivo excel
				// que se cargue
			else if (getCbPlanMedioNuevoMes().isSelected()) {

				// ESTA VALIDACION HACERLA TB EN VERSION WHY TRATARLO COMO
				// HERMANO PARA EVITAR QUE CARGUE UN EXCEL CON NUEVAS FECHAS
				// QUE INVOLUCREN A UN HERMANO

				mapFechaInicioFinPlanesMedioHermanosSaved.clear();

				ArrayList<Timestamp> listFechas = new ArrayList<Timestamp>();
				listFechas.add(planMedioIfSaved.getFechaInicio());
				listFechas.add(planMedioIfSaved.getFechaFin());

				mapFechaInicioFinPlanesMedioHermanosSaved.put(
						planMedioIfSaved.getId(), listFechas);

				Collection<PlanMedioIf> planesMedioHermanosColl = (Collection) SessionServiceLocator
						.getPlanMedioSessionService()
						.findPlanMedioByIdPlanMedioHermanoNuevoMes(
								planMedioIfSaved.getId());

				if (planesMedioHermanosColl != null
						&& planesMedioHermanosColl.size() > 0) {
					for (PlanMedioIf planMedioIfHermano : planesMedioHermanosColl) {
						ArrayList<Timestamp> listFechasHermanos = new ArrayList<Timestamp>();
						listFechasHermanos.add(planMedioIfHermano
								.getFechaInicio());
						listFechasHermanos
								.add(planMedioIfHermano.getFechaFin());
						mapFechaInicioFinPlanesMedioHermanosSaved.put(
								planMedioIfHermano.getId(), listFechasHermanos);
					}
				}

			}// END 1 JULIO

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getSelectedObject() {
		try {
			// plan de medio guardado en la DB
			planMedioIf = (PlanMedioIf) popupFinder.getElementoSeleccionado();

			// //PLAN MEDIO

			getTxtCodigo().setText(planMedioIf.getCodigo());
			getTxtRevision().setText(planMedioIf.getRevision());
			
			getTxtFechaCreacion().setText(
					Utilitarios.getFechaUppercase(planMedioIf
							.getFechaCreacion()));
			
			if (planMedioIf.getEstado().equals(ESTADO_EN_PROCESO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_EN_PROCESO);
			else if (planMedioIf.getEstado().equals(ESTADO_PENDIENTE))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_PENDIENTE);
			else if (planMedioIf.getEstado().equals(ESTADO_APROBADO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_APROBADO);
			else if (planMedioIf.getEstado().equals(ESTADO_FACTURADO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_FACTURADO);			
			else if (planMedioIf.getEstado().equals(ESTADO_HISTORICO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_HISTORICO);
			else if (planMedioIf.getEstado().equals(ESTADO_PEDIDO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_PEDIDO);
			else if (planMedioIf.getEstado().equals(ESTADO_PREPAGADO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_PREPAGADO);
			
			tipoOrden = (TipoOrdenIf) SessionServiceLocator
					.getTipoOrdenSessionService().findTipoOrdenByTipo("M")
					.iterator().next();
			ordenTrabajoDetalleIf = SessionServiceLocator
					.getOrdenTrabajoDetalleSessionService()
					.getOrdenTrabajoDetalle(
							planMedioIf.getOrdenTrabajoDetalleId());
			ordenTrabajoIf = SessionServiceLocator
					.getOrdenTrabajoSessionService().getOrdenTrabajo(
							ordenTrabajoDetalleIf.getOrdenId());
			clienteOficinaIf = mapaClienteOficina.get(ordenTrabajoIf
					.getClienteoficinaId());
			clienteIf = SessionServiceLocator.getClienteSessionService()
					.getCliente(clienteOficinaIf.getClienteId());
			corporacionIf = SessionServiceLocator
					.getCorporacionSessionService().getCorporacion(
							clienteIf.getCorporacionId());
			getTxtCorporacion().setText(
					corporacionIf.getCodigo() + " - "
							+ corporacionIf.getNombre());
			getTxtCliente().setText(
					clienteIf.getIdentificacion() + " - "
							+ clienteIf.getNombreLegal());
			getTxtOficina().setText(clienteOficinaIf.getDescripcion());

			boolean saveMode = false;
			if (getMode() == SpiritMode.SAVE_MODE)
				saveMode = true;

			SpiritComboBoxModel cmbModelOrdenTrabajo = new SpiritComboBoxModel(
					(ArrayList) SessionServiceLocator
							.getOrdenTrabajoSessionService()
							.findOrdenTrabajoPendienteByClienteOficinaIdAndTipoOrden(
									clienteOficinaIf.getId(),
									TIPO_ORDEN_MEDIOS, saveMode));
			getCmbOrdenTrabajo().setModel(cmbModelOrdenTrabajo);
			getCmbOrdenTrabajo().setEnabled(true);
			getCmbOrdenTrabajo().setSelectedIndex(
					ComboBoxComponent.getIndexToSelectItem(
							getCmbOrdenTrabajo(), ordenTrabajoIf.getId()));
			getCmbOrdenTrabajo().repaint();
			// getTxtOrdenTrabajoDetalle().setText(tipoOrden.getNombre());
			getTxtConcepto().setText(planMedioIf.getConcepto());

			// SAP
			if (planMedioIf.getAutorizacionSap() != null) {
				getTxtAutorizacionSAP().setText(
						planMedioIf.getAutorizacionSap());
			}
			
			if (planMedioIf.getPrepago() != null && planMedioIf.getPrepago().equals("S")) {
				getCbPrepago().setSelected(true);
			} else {
				getCbPrepago().setSelected(false);
			}

			// Si el tipo de plan de medio es "Nuevo Mes" entonces seteo en el
			// campo de texto el código del plan medio hermano.
			// para el caso de nueva versión no es necesario ya que la nueva
			// versión tiene el MISMO CODIGO que el orginal.
			if (planMedioIf.getPlanMedioTipo()
					.equals(PLAN_MEDIO_TIPO_NUEVO_MES)) {
				PlanMedioIf planMedioHermano = SessionServiceLocator
						.getPlanMedioSessionService().getPlanMedio(
								planMedioIf.getPlanMedioHermanoId());
				getTextPlanMedioRelacion()
						.setText(planMedioHermano.getCodigo());
			}

			getCmbTarget().setSelectedIndex(
					ComboBoxComponent.getIndexToSelectItem(getCmbTarget(),
							planMedioIf.getGrupoObjetivoId()));
			getCmbTarget().repaint();
			listenerCmbTarget();

			if (planMedioIf.getFechaAprobacion() != null)
				getCmbFechaAprobacion().setDate(
						planMedioIf.getFechaAprobacion());

			getCmbPeriodoFechaInicio().setDate(planMedioIf.getFechaInicio());
			getCmbPeriodoFechaFin().setDate(planMedioIf.getFechaFin());

			// END 28 ABRIL
			getTxtCobertura().setText(planMedioIf.getCobertura());

			getTxtModificaciones().setText(
					String.valueOf(planMedioIf.getModificacion()));

			if (planMedioIf.getConsideraciones() != null) {
				getTxtOtrasConsideraciones().setText(
						planMedioIf.getConsideraciones());
			}

			// //PLAN MEDIO MES

			// Collection Plan Medio Mes que se encuentra en la Base
			Collection planMedioMesColeccion = SessionServiceLocator
					.getPlanMedioMesSessionService()
					.findPlanMedioMesByPlanmedioId(planMedioIf.getId());
			Iterator planMedioMesIterator = planMedioMesColeccion.iterator();

			while (planMedioMesIterator.hasNext()) {
				PlanMedioMesIf planMedioMesIf = (PlanMedioMesIf) planMedioMesIterator
						.next();
				getPlanMedioMesVector().add(planMedioMesIf);

				tableSubPeriodo = (DefaultTableModel) getTblSubPeriodo()
						.getModel();
				Vector<String> fila = new Vector<String>();
				String tipo = "";
				String subPeriodoFechaInicio = Utilitarios
						.getFechaUppercase(planMedioMesIf.getFechaInicio());
				String subPeriodoFechaFin = Utilitarios
						.getFechaUppercase(planMedioMesIf.getFechaFin());

				if (planMedioMesIf.getTipo().equals(TIPO_EXPECTATIVA))
					tipo = NOMBRE_TIPO_EXPECTATIVA;
				else if (planMedioMesIf.getTipo().equals(TIPO_MANTENIMIENTO))
					tipo = NOMBRE_TIPO_MANTENIMIENTO;
				else if (planMedioMesIf.getTipo().equals(TIPO_LANZAMIENTO))
					tipo = NOMBRE_TIPO_LANZAMIENTO;
				else if (planMedioMesIf.getTipo().equals(TIPO_PROMOCIONAL))
					tipo = NOMBRE_TIPO_PROMOCIONAL;

				fila.add(tipo);
				fila.add(subPeriodoFechaInicio);
				fila.add(subPeriodoFechaFin);
				tableSubPeriodo.addRow(fila);

				getCmbTipoPeriodo().addItem(
						tipo + " (" + subPeriodoFechaInicio + " al "
								+ subPeriodoFechaFin + ")");

				// PLAN MEDIO DETALLE

				// Collection<PlanMedioDetalleIf> planMedioDetallesPlantilla
				planMedioDetallesPlantilla.clear();
				// HashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>>
				mapasComercialesPlantilla.clear();
				// Map<PlanMedioDetalleIf, Collection<InfoComercialMultiple>>
				mapasComercialesPlantillaMultiple.clear();
				// Map<Long,ClienteOficinaIf> listaProveedoresMap
				listaProveedoresMap.clear();

				// Se encuentra los planes medios detalle del plan de medio a
				// actualizar o consultar
				// Collection<PlanMedioDetalleIf> planMedioDetallesPlantilla
				planMedioDetallesPlantilla = SessionServiceLocator
						.getPlanMedioDetalleSessionService()
						.findPlanMedioDetalleByPlanMedioMesId(
								planMedioMesIf.getId());
				
				String letras = "";
				Iterator detallesPlantillaIt = planMedioDetallesPlantilla
						.iterator();
				while (detallesPlantillaIt.hasNext()) {
					PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) detallesPlantillaIt
							.next();

					getTxtPorcentajeDescuentoVenta().setText(
							formatoDecimal.format(Utilitarios
									.redondeoValor(planMedioDetalleIf
											.getPorcentajeDescuentoVenta())));
					getTxtPorcentajeComisionAgencia().setText(
							formatoDecimal.format(Utilitarios
									.redondeoValor(planMedioDetalleIf
											.getPorcentajeComisionAgencia())));
					getTxtPorcentajeBonificacionVenta()
							.setText(
									formatoDecimal.format(Utilitarios.redondeoValor(planMedioDetalleIf
											.getPorcentajeBonificacionVenta())));
					porcentajeDescuentoTotalVenta = planMedioDetalleIf
							.getPorcentajeDescuentoVenta();
					porcentajeComisionAgencia = planMedioDetalleIf
							.getPorcentajeComisionAgencia();
					porcentajeBonificacionVenta = planMedioDetalleIf
							.getPorcentajeBonificacionVenta();

					Collection mapasComerciales = SessionServiceLocator
							.getMapaComercialSessionService()
							.findMapaComercialByPlanMedioDetalleId(
									planMedioDetalleIf.getId());

					// HashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>>
					mapasComercialesPlantilla.put(planMedioDetalleIf,
							mapasComerciales);

					ArrayList<InfoComercialMultiple> listaMultiples = new ArrayList<InfoComercialMultiple>();
					ComercialIf comercial = mapaComercial.get(planMedioDetalleIf.getComercialId());
					Iterator mapasComercialesIt = mapasComerciales.iterator();
					while (mapasComercialesIt.hasNext()) {
						MapaComercialIf mapaComercialIf = (MapaComercialIf) mapasComercialesIt
								.next();

						InfoComercialMultiple infoComercial = new InfoComercialMultiple();
						infoComercial.setFecha(mapaComercialIf.getFecha());
						infoComercial.setVersion(comercial.getVersion().split(
								" ")[0]);
						infoComercial.setFrecuencia(mapaComercialIf
								.getFrecuencia());
						listaMultiples.add(infoComercial);

						// Map<PlanMedioDetalleIf,
						// Collection<InfoComercialMultiple>>
						mapasComercialesPlantillaMultiple.put(
								planMedioDetalleIf, listaMultiples);

					}
				}
			}

			// SE COLOCAN LOS DATOS PARA MOSTRAR EN LA PANTALLA DEL SISTEMA
			generarMapaPautaTv(TIPO_PLANTILLA_SENCILLA);

			getJtpPlanMedio().setSelectedIndex(0);
			getTxtConcepto().grabFocus();
			// porcentajeDescuentoTotalAnt =
			// planMedioIf.getPorcentajeDescuento();

			// CARGAR ORDEN MEDIO
			// Esto debe ir antes del metodo setearGenericoPautaRegular();
			// porque determina si es tv, radio o cine.
			TipoProveedorIf tipoProveedorDelPlan = SessionServiceLocator
					.getTipoProveedorSessionService().getTipoProveedor(
							planMedioIf.getTipoProveedorId());
			if (tipoProveedorDelPlan.getNombre().equals("TELEVISION")) {
				getCbPautaTelevision().setSelected(true);
				getCbPautaRadio().setSelected(false);
				getCbPautaCine().setSelected(false);
			} else if (tipoProveedorDelPlan.getNombre().equals("RADIO")) {
				getCbPautaTelevision().setSelected(false);
				getCbPautaRadio().setSelected(true);
				getCbPautaCine().setSelected(false);
			} else {
				getCbPautaTelevision().setSelected(false);
				getCbPautaRadio().setSelected(false);
				getCbPautaCine().setSelected(true);
			}
			setearGenericoPautaRegular();
			setearDescuentoyProductoPlanMedioDetallesPlantilla(false);

			// MODIFIED 12 OCTUBRE ADD 20 MAYO
			// se genera las ordenes de medio
			if (planMedioIf.getOrdenMedioTipo().compareTo(
					ORDEN_MEDIO_TIPO_CANAL) == 0) {
				ordenMedioTipo = ORDEN_MEDIO_TIPO_CANAL;
				getCbAgrupaOrdenes().setSelected(true);
				getCbOrdenPorProductoComercial().setSelected(false);
				getCbOrdenPorVersion().setSelected(false);
				generarOrdenesMedio(planMedioIf, 1);
				cargarOrdenesMedioaAgrupadasXCanal();
			} else if (planMedioIf.getOrdenMedioTipo().compareTo(
					ORDEN_MEDIO_TIPO_PRODUCTO_COMERCIAL) == 0) {
				ordenMedioTipo = ORDEN_MEDIO_TIPO_PRODUCTO_COMERCIAL;
				getCbOrdenPorProductoComercial().setSelected(true);
				getCbAgrupaOrdenes().setSelected(false);
				getCbOrdenPorVersion().setSelected(false);
				generarOrdenesMedio(planMedioIf, 2); // ADD 13 OCTUBRE
				cargarOrdenesMedioaAgrupadasXProductoCliente(); // ADD 13
																// OCTUBRE
			} else if (planMedioIf.getOrdenMedioTipo().compareTo(
					ORDEN_MEDIO_TIPO_VERSION) == 0) {
				ordenMedioTipo = ORDEN_MEDIO_TIPO_VERSION;
				getCbOrdenPorVersion().setSelected(true);
				getCbAgrupaOrdenes().setSelected(false);
				getCbOrdenPorProductoComercial().setSelected(false);
				generarOrdenesMedio(planMedioIf, 3);
				cargarOrdenesMedioaAgrupadasXCampanaProductoVersion();
			}

			planMedioIfSaved = planMedioIf;
			planMedioDetallesPlantillaSaved = planMedioDetallesPlantilla;
			clienteOficinaIfSaved = clienteOficinaIf;

			if (planMedioIfSaved.getOrdenMedioTipo().compareTo(
					ORDEN_MEDIO_TIPO_CANAL) == 0) {
				generarOrdenesMedioForUpdate(planMedioIfSaved, 1);
				cargarOrdenesMedioaAgrupadasXCanalForUpdate();
			} else if (planMedioIfSaved.getOrdenMedioTipo().compareTo(
					ORDEN_MEDIO_TIPO_PRODUCTO_COMERCIAL) == 0) {
				generarOrdenesMedioForUpdate(planMedioIfSaved, 2);
				// cargarOrdenesMedioaAgrupadasForUpdate(); COMENTED 13 OCTUBRE
				cargarOrdenesMedioaAgrupadasXProductoClienteForUpdate(); // ADD
																			// 13
																			// OCTUBRE
			} else if (planMedioIfSaved.getOrdenMedioTipo().compareTo(
					ORDEN_MEDIO_TIPO_VERSION) == 0) {
				generarOrdenesMedioForUpdate(planMedioIfSaved, 3);
				cargarOrdenesMedioaAgrupadasXCampanaProductoVersionForUpdate();
			}

			/*
			 * if(verificarExistenciaOrdenesMedioIngresadas()){
			 * SpiritAlert.createAlert(
			 * "Existen Ordenes de Medio Ingresadas. \n Verifique los codigos de las Ordenes de Medio  \n y Anule las Compras"
			 * , SpiritAlert.WARNING); }
			 */

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

	private PlanMedioIf registrarPlanMedio() {
		PlanMedioData data = new PlanMedioData();

		try {

			if (planMedioTipo.compareTo(PLAN_MEDIO_TIPO_NUEVA_VERSION) == 0
					&& planMedioIfSaved != null) {
				data.setCodigo(planMedioIfSaved.getCodigo());
				
				int revisionNumero = Integer.valueOf(planMedioIfSaved.getRevision());
				revisionNumero = revisionNumero + 1;
				String revision = "";
				if(revisionNumero < 10){
					revision = "0" + revisionNumero;
				}else{
					revision = String.valueOf(revisionNumero);
				}
				data.setRevision(revision);
				
			} else {
				String codigo = getCodigoPlanMedio(new java.sql.Date(
						new GregorianCalendar().getTimeInMillis()));
				data.setCodigo(codigo);
				data.setRevision("01");
			}

			data.setConcepto(getTxtConcepto().getText());

			tipoOrden = (TipoOrdenIf) SessionServiceLocator
					.getTipoOrdenSessionService().findTipoOrdenByTipo("M")
					.iterator().next();
			ordenTrabajoIf = (OrdenTrabajoIf) getCmbOrdenTrabajo()
					.getSelectedItem();
			ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) SessionServiceLocator
					.getOrdenTrabajoDetalleSessionService()
					.findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajoAndEstado(
							tipoOrden.getId(), ordenTrabajoIf.getId())
					.iterator().next();
			data.setOrdenTrabajoDetalleId(ordenTrabajoDetalleIf.getId());

			GrupoObjetivoIf grupoObjetivo = (GrupoObjetivoIf) getCmbTarget()
					.getSelectedItem();
			data.setGrupoObjetivoId(grupoObjetivo.getId());

			data.setOrdenMedioTipo(ordenMedioTipo);

			if (planMedioTipo.compareTo("") == 0) {
				planMedioTipo = PLAN_MEDIO_TIPO_NORMAL;
			}

			data.setPlanMedioTipo(planMedioTipo);
			if (planMedioIfSaved != null) {
				data.setPlanMedioHermanoId(planMedioIfSaved
						.getPlanMedioHermanoId());
			}

			data.setModificacion(0);
			data.setFechaInicio(Utilitarios
					.fromSqlDateToTimestamp(new java.sql.Date(
							getCmbPeriodoFechaInicio().getDate().getTime())));
			data.setFechaFin(Utilitarios
					.fromSqlDateToTimestamp(new java.sql.Date(
							getCmbPeriodoFechaFin().getDate().getTime())));
			data.setCiudad1(BigDecimal.valueOf(Double.parseDouble(Utilitarios
					.removeDecimalFormat(getTxtGuayaquil().getText()))));
			data.setCiudad2(BigDecimal.valueOf(Double.parseDouble(Utilitarios
					.removeDecimalFormat(getTxtQuito().getText()))));

			if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_APROBADO))
				data.setEstado(ESTADO_APROBADO);
			else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_FACTURADO))
				data.setEstado(ESTADO_FACTURADO);
			else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_EN_PROCESO))
				data.setEstado(ESTADO_EN_PROCESO);
			else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PENDIENTE))
				data.setEstado(ESTADO_PENDIENTE);
			
			else if (planMedioIf.getEstado().equals(ESTADO_HISTORICO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_HISTORICO);
			else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PEDIDO))
				data.setEstado(ESTADO_PEDIDO);
			else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PREPAGADO))
				data.setEstado(ESTADO_PREPAGADO);

			data.setCobertura(getTxtCobertura().getText());

			if (!getTxtOtrasConsideraciones().getText().equals("")) {
				data.setConsideraciones(getTxtOtrasConsideraciones().getText());
			}

			data.setValorDescuentoVenta(descuentoTotalVenta);
			data.setValorComisionAgencia(comisionAgenciaTotal);
			data.setValorIva(ivaTotal);
			data.setValorDescuento(descuentoTotal);
			data.setValorSubtotal(totalSumaTotal);
			data.setValorTotal(totalValorTotal);
			data.setFechaCreacion(Utilitarios
					.fromSqlDateToTimestamp(new java.sql.Date(
							(new GregorianCalendar()).getTimeInMillis())));
			data.setFechaActualizacion(Utilitarios
					.fromSqlDateToTimestamp(new java.sql.Date(
							(new GregorianCalendar()).getTimeInMillis())));
			data.setUsuarioId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
			data.setUsuarioActualizacionId(((UsuarioIf) Parametros
					.getUsuarioIf()).getId());

			// Para setear el tipo de proveedor en el plan
			if (getCbPautaRadio().isSelected()) {
				Collection tipoProveedor = SessionServiceLocator
						.getTipoProveedorSessionService()
						.findTipoProveedorByNombre("RADIO");
				Iterator tipoProveedorIt = tipoProveedor.iterator();
				while (tipoProveedorIt.hasNext()) {
					TipoProveedorIf tipoProveedorRadio = (TipoProveedorIf) tipoProveedorIt
							.next();
					data.setTipoProveedorId(tipoProveedorRadio.getId());
				}
			} else if (getCbPautaTelevision().isSelected()) {
				Collection tipoProveedor = SessionServiceLocator
						.getTipoProveedorSessionService()
						.findTipoProveedorByNombre("TELEVISION");
				Iterator tipoProveedorIt = tipoProveedor.iterator();
				while (tipoProveedorIt.hasNext()) {
					TipoProveedorIf tipoProveedorTv = (TipoProveedorIf) tipoProveedorIt
							.next();
					data.setTipoProveedorId(tipoProveedorTv.getId());
				}
			} else {
				Collection tipoProveedor = SessionServiceLocator
						.getTipoProveedorSessionService()
						.findTipoProveedorByNombre("OTROS MEDIOS");
				Iterator tipoProveedorIt = tipoProveedor.iterator();
				while (tipoProveedorIt.hasNext()) {
					TipoProveedorIf tipoProveedorTv = (TipoProveedorIf) tipoProveedorIt
							.next();
					data.setTipoProveedorId(tipoProveedorTv.getId());
				}
			}

			if (getTxtAutorizacionSAP().getText() != null
					&& !getTxtAutorizacionSAP().getText().equals("")) {
				data.setAutorizacionSap(getTxtAutorizacionSAP().getText());
			}

			java.util.Date fechaAprobacion = getCmbFechaAprobacion().getDate();
			if (fechaAprobacion != null) {
				data.setFechaAprobacion(new java.sql.Timestamp(fechaAprobacion
						.getTime()));
			} else if (data.getEstado().equals(ESTADO_APROBADO) || data.getEstado().equals(ESTADO_PREPAGADO)) {
				data.setFechaAprobacion(new java.sql.Timestamp(Utilitarios
						.calendarHoy().getTimeInMillis()));
			}
			
			if(getCbPrepago().isSelected()){
				data.setPrepago("S");
			}else{
				data.setPrepago("N");
			}

		} catch (GenericBusinessException e1) {
			e1.printStackTrace();
			SpiritAlert.createAlert(
					"Se ha producido un error al registrar Plan de Medio",
					SpiritAlert.ERROR);
		}

		return data;
	}

	ActionListener oActionListenerCbPautaTelevision = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getCbPautaTelevision().isSelected()) {
				getCbPautaRadio().setSelected(false);
				getCbPautaCine().setSelected(false);
			}
		}
	};

	ActionListener oActionListenerCbPautaRadio = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getCbPautaRadio().isSelected()) {
				getCbPautaTelevision().setSelected(false);
				getCbPautaCine().setSelected(false);
			}
		}
	};

	ActionListener oActionListenerCbPautaCine = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (getCbPautaCine().isSelected()) {
				getCbPautaTelevision().setSelected(false);
				getCbPautaRadio().setSelected(false);
			}
		}
	};

	private void setearGenericoPautaRegular() {
		try {
			String tipoProductoCodigo = SpiritConstants.getEmptyCharacter();
			if (getCbPautaTelevision().isSelected())
				tipoProductoCodigo = "PTV";
			else if (getCbPautaRadio().isSelected())
				tipoProductoCodigo = "PRA";
			else
				tipoProductoCodigo = "PCI";
			String cobraIvaProveedor = getCbIvaProveedor().isSelected()?"S":"N";
			String cobraIvaCliente = getCbIvaCliente().isSelected()?"S":"N";
			genericoPautaRegular = (GenericoIf) SessionServiceLocator.getGenericoSessionService().findGenericoPautaRegular(clienteIf.getId(),tipoProductoCodigo, Parametros.getIdEmpresa(), cobraIvaProveedor, cobraIvaCliente).iterator().next();
			getTxtProductoProveedor().setText(genericoPautaRegular.getNombre());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha encontrado un problema al establecer el producto para la orden", SpiritAlert.ERROR);
		} catch (java.util.NoSuchElementException ex) {
			ex.printStackTrace();
			SpiritAlert.createAlert("Se ha encontrado un problema al establecer el producto para la orden", SpiritAlert.ERROR);
		}
	}

	private void setearDescuentoyProductoPlanMedioDetallesPlantilla(boolean crearMapaPauta) {
		Collection<PlanMedioDetalleIf> planMedioDetallesPlantillaTemp = new ArrayList<PlanMedioDetalleIf>();
		Iterator detallesPlantillaIt = planMedioDetallesPlantilla.iterator();
		while (detallesPlantillaIt.hasNext()) {
			// Seteo Descuento
			PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) detallesPlantillaIt.next();
			// Seteo Producto Proveedor Id
			ProductoIf productoPautaRegularTv = null;
			if (crearMapaPauta) {
				/*
				Map mapProductoPautaRegularTv = new HashMap();
				mapProductoPautaRegularTv.put("genericoId", genericoPautaRegular.getId());
				mapProductoPautaRegularTv.put("proveedorId", planMedioDetalleIf.getProveedorId());
				productoPautaRegularTv = (ProductoIf) SessionServiceLocator.getProductoSessionService().findProductoByQuery(mapProductoPautaRegularTv).iterator().next();
				*/
				Map<Long, ProductoIf> mapaProveedorProducto = mapaGenericoMapaProveedorProducto.get(genericoPautaRegular.getId());
				productoPautaRegularTv = mapaProveedorProducto.get(planMedioDetalleIf.getProveedorId());
				
			} else {
				productoPautaRegularTv = mapaProducto.get(planMedioDetalleIf.getProductoProveedorId());
			}
			planMedioDetalleIf.setProductoProveedorId(productoPautaRegularTv.getId());
			planMedioDetallesPlantillaTemp.add(planMedioDetalleIf);
		}
		planMedioDetallesPlantilla = planMedioDetallesPlantillaTemp;
	}

	private void registrarPlanMedioForUpdate() {
		try {
			planMedioIf.setCodigo(getTxtCodigo().getText());
			planMedioIf.setConcepto(getTxtConcepto().getText());
			tipoOrden = (TipoOrdenIf) SessionServiceLocator.getTipoOrdenSessionService().findTipoOrdenByTipo("M").iterator().next();
			ordenTrabajoIf = (OrdenTrabajoIf) getCmbOrdenTrabajo().getSelectedItem();// poner aquí la validación
			ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajoAndEstado(tipoOrden.getId(), ordenTrabajoIf.getId()).iterator().next();
			planMedioIf.setOrdenTrabajoDetalleId(ordenTrabajoDetalleIf.getId());

			GrupoObjetivoIf grupoObjetivo = (GrupoObjetivoIf) getCmbTarget().getSelectedItem();
			planMedioIf.setGrupoObjetivoId(grupoObjetivo.getId());

			planMedioIf.setFechaInicio(Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(getCmbPeriodoFechaInicio().getDate().getTime())));
			planMedioIf.setFechaFin(Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(getCmbPeriodoFechaFin().getDate().getTime())));
			planMedioIf.setCiudad1(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtGuayaquil().getText()))));
			planMedioIf.setCiudad2(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtQuito().getText()))));

			planMedioIf.setModificacion(planMedioIf.getModificacion() + 1);

			if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_APROBADO))
				planMedioIf.setEstado(ESTADO_APROBADO);
			else if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_FACTURADO))
				planMedioIf.setEstado(ESTADO_FACTURADO);
			else if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_EN_PROCESO))
				planMedioIf.setEstado(ESTADO_EN_PROCESO);
			else if (getCmbEstado().getSelectedItem().equals(
					NOMBRE_ESTADO_PENDIENTE))
				planMedioIf.setEstado(ESTADO_PENDIENTE);
			
			else if (planMedioIf.getEstado().equals(ESTADO_HISTORICO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_HISTORICO);
			else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PEDIDO))
				planMedioIf.setEstado(ESTADO_PEDIDO);
			else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PREPAGADO))
				planMedioIf.setEstado(ESTADO_PREPAGADO);
			

			planMedioIf.setCobertura(getTxtCobertura().getText());

			if (!getTxtOtrasConsideraciones().getText().equals("")) {
				planMedioIf.setConsideraciones(getTxtOtrasConsideraciones().getText());
			}

			planMedioIf.setValorDescuento(descuentoTotal);
			planMedioIf.setValorIva(ivaTotal);
			planMedioIf.setValorSubtotal(totalSumaTotal);
			planMedioIf.setValorTotal(totalValorTotal);

			planMedioIf.setValorDescuentoVenta(descuentoTotalVenta);
			planMedioIf.setValorComisionAgencia(comisionAgenciaTotal);

			planMedioIf.setFechaActualizacion(Utilitarios.fromSqlDateToTimestamp(new java.sql.Date((new GregorianCalendar()).getTimeInMillis())));
			planMedioIf.setUsuarioActualizacionId(((UsuarioIf) Parametros.getUsuarioIf()).getId());

			if (getTxtAutorizacionSAP().getText() != null && !getTxtAutorizacionSAP().getText().equals("")) {
				planMedioIf.setAutorizacionSap(getTxtAutorizacionSAP().getText());
			}

			java.util.Date fechaAprobacion = getCmbFechaAprobacion().getDate();
			if (fechaAprobacion != null) {
				planMedioIf.setFechaAprobacion(new java.sql.Timestamp(fechaAprobacion.getTime()));
			} else if (planMedioIf.getEstado().equals(ESTADO_APROBADO) || planMedioIf.getEstado().equals(ESTADO_PREPAGADO)) {
				planMedioIf.setFechaAprobacion(new java.sql.Timestamp(Utilitarios.calendarHoy().getTimeInMillis()));
			}
			
			if(getCbPrepago().isSelected()){
				planMedioIf.setPrepago("S");
			}else{
				planMedioIf.setPrepago("N");
			}

		} catch (GenericBusinessException e1) {
			e1.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al registrar Plan de Medio para actualizar", SpiritAlert.ERROR);
		}
	}

	// ADD 28 JUNIO
	// verifica si existen ordenes de medio guardadas en la DB con estado
	// Ingresadas
	public boolean verificarExistenciaOrdenesMedioIngresadas() {
		// ADD 28 JUNIO
		boolean isOrdenIngresada = false;

		if (listIdsCodigoEstadoOrdenesMedioSaved != null
				&& listIdsCodigoEstadoOrdenesMedioSaved.size() > 0) {
			for (Map mapIdOrdenCodigoEstado : listIdsCodigoEstadoOrdenesMedioSaved) {
				Long idOrden = (Long) mapIdOrdenCodigoEstado.keySet().iterator().next();
				// MODIFIED 28 JUNIO
				// String estadoOrden = (String) mapIdOrdenEstado.get(idOrden);
				Map<String, String> mapCodigoEstado = new HashMap<String, String>();
				mapCodigoEstado = (Map<String, String>) mapIdOrdenCodigoEstado.get(idOrden);
				String codigoOrden = (String) mapCodigoEstado.keySet().iterator().next();
				String estadoOrden = (String) mapCodigoEstado.get(codigoOrden);

				// COMENTED 28 JUNIO Por el momento se decidio que si existen
				// Ordenes de medio Ingresadas que no se pueda guardar las
				// ordenes
				// if (estadoOrden.compareTo("I") != 0){
				if (estadoOrden.compareTo(ESTADO_ORDEN_INGRESADA) == 0) {
					// COMENTED 28 JUNIO
					// this.actualizarOrdenMedioEstado(idOrden,"A");//ESTADO
					// ANULADA
					isOrdenIngresada = true;
				}
			}
		}
		return isOrdenIngresada;
	}

	// END 28 JUNIO

	public void save() {
		try {
			if (validateFields()) {
				if (!verificarExistenciaOrdenesMedioIngresadas()) {
					guardarPlanMedios();
				} else {
					int opcion = JOptionPane
							.showOptionDialog(
									null,
									"Existen Ordenes de Medio Ingresadas, estas ordenes No se actualizarán. ¿Desea continuar con la actualización de la Pauta?",
									"Confirmación", JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									options, no);
					if (opcion == JOptionPane.YES_OPTION) {
						guardarPlanMedios();
					}
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!",
					SpiritAlert.ERROR);
		}
	}

	private void guardarPlanMedios() throws GenericBusinessException {
		boolean problema = false;
		// se crea una instancia de PlanMedio
		PlanMedioIf planMedio = registrarPlanMedio();
		ordenTrabajoIf.setEstado(ESTADO_ORDEN_EN_CURSO);
		ordenTrabajoDetalleIf.setEstado(ESTADO_ORDEN_EN_CURSO);
		// setea a genericoPautaRegularTv como PAUTA REGULAR TELEVISION
		//setearGenericoPautaRegular();
		setearDescuentoyProductoPlanMedioDetallesPlantilla(false);
		setearDescuentoVentaComisionAgencia();
		// problema = generarMapasComercialesPlantilla(problema);

		if (planMedio.getPlanMedioTipo().compareTo(PLAN_MEDIO_TIPO_NORMAL) == 0) {
			planMedio = SessionServiceLocator.getPlanMedioSessionService()
					.procesarPlanMedio(planMedio, planMedioMesVector,
							planMedioDetallesPlantilla,
							mapasComercialesPlantilla);
		} else {
			planMedio = SessionServiceLocator.getPlanMedioSessionService()
					.procesarPlanMedioXTipo(planMedio, planMedioMesVector,
							planMedioDetallesPlantilla,
							mapasComercialesPlantilla, planMedioIfSaved);
			// setear el mismo estado que tenia el PlanMedioSaved u Original
			// SOLO SI ES VERSION AKI POR FAVOR 28 JUNIO
			if (planMedio.getPlanMedioTipo().compareTo(
					PLAN_MEDIO_TIPO_NUEVA_VERSION) == 0)
				planMedio.setEstado(planMedioIfSaved.getEstado());
		}

		if (!problema) {
			SpiritAlert.createAlert("Plan de Medio guardado con éxito",
					SpiritAlert.INFORMATION);
			planMedioIf = planMedio;
			reportePlanMedio();
			// actualiza el plan de medio y guarda las ordenes
			saveOrdenesMedio();
		}

		showSaveMode();
		iniciarMapaTv();
		cleanTablaTGRPtv();
		cleanTablaOrdenesMedio();
		clean();
	}

	// MODIFIED 22 JUNIO
	private void saveOrdenesMedio() {
		try {
			Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaComercialOrdenesCompCreadas; // =
																																// new
																																// HashMap<OrdenMedioData,
																																// Map<OrdenMedioDetalleData,ArrayList<OrdenMedioDetalleMapaIf>>>();
			setearDescuentosComisionAgenciaOrdenMedio();

			// COMENTED 22 JUNIO
			// mapaComercialOrdenesCompCreadas =
			// SessionServiceLocator.getOrdenMedioSessionService().procesarOrdenMedio(planMedioIf,
			// mapOrdenMediobyProveedor);

			// ADD 22 JUNIO
			if (planMedioTipo.compareTo(PLAN_MEDIO_TIPO_NUEVA_VERSION) != 0) {// Cuando
																				// es
																				// TIPO
																				// NORMAL
																				// o
																				// TIP
				mapaComercialOrdenesCompCreadas = SessionServiceLocator
						.getOrdenMedioSessionService().procesarOrdenMedio(
								planMedioIf, mapOrdenMediobyProveedor);
			} else {
				// mapaComercialOrdenesCompCreadas =
				// SessionServiceLocator.getOrdenMedioSessionService().actualizarOrdenMedio(planMedioIf,
				// mapOrdenMediobyProveedor,nuevoPlan);
				mapaComercialOrdenesCompCreadas = SessionServiceLocator
						.getOrdenMedioSessionService()
						.procesarOrdenMedioXNuevaVersion(planMedioIf,
								mapOrdenMediobyProveedor,
								listIdsCodigoEstadoOrdenesMedioSaved);
			}
			SpiritAlert.createAlert("Orden(es) de Medio guardada(s) con éxito",
					SpiritAlert.INFORMATION);

			// Llamar a reporteOrdenMedio cuando se lo llame desde save y desde
			// update
			reporteOrdenMedio(mapaComercialOrdenesCompCreadas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// END MODIFIED 22 JUNIO

	/*
	 * private void saveOrdenesMedio(){ try{ Map
	 * <OrdenMedioIf,Map<OrdenMedioDetalleIf,
	 * ArrayList<OrdenMedioDetalleMapaIf>>> mapaComercialOrdenesCompCreadas; //=
	 * new HashMap<OrdenMedioData,
	 * Map<OrdenMedioDetalleData,ArrayList<OrdenMedioDetalleMapaIf>>>();
	 * setearDescuentosComisionAgenciaOrdenMedio();
	 * 
	 * 
	 * mapaComercialOrdenesCompCreadas =
	 * SessionServiceLocator.getOrdenMedioSessionService
	 * ().procesarOrdenMedio(planMedioIf, mapOrdenMediobyProveedor);
	 * 
	 * SpiritAlert.createAlert("Orden(es) de Medio guardada(s) con éxito",
	 * SpiritAlert.INFORMATION);
	 * 
	 * //Llamar a reporteOrdenMedio cuando se lo llame desde save y desde update
	 * reporteOrdenMedio(mapaComercialOrdenesCompCreadas); }catch (Exception e)
	 * { e.printStackTrace(); } }
	 */

	private void updateOrdenesMedio(boolean isReemplazado) {
		try {
			Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaComercialOrdenesCompCreadas; // =
																																// new
																																// HashMap<OrdenMedioData,
																																// Map<OrdenMedioDetalleData,ArrayList<OrdenMedioDetalleMapaIf>>>();
			setearDescuentosComisionAgenciaOrdenMedio();
			mapaComercialOrdenesCompCreadas = SessionServiceLocator
					.getOrdenMedioSessionService().actualizarOrdenMedio(
							planMedioIf, mapOrdenMediobyProveedor, nuevoPlan,
							genericoPautaRegular);

			if (!isReemplazado) {
				SpiritAlert.createAlert(
						"Orden(es) de Medio actualizada(s) con éxito",
						SpiritAlert.INFORMATION);
			} else {
				SpiritAlert.createAlert(
						"Orden(es) de Medio reemplazada(s) con éxito",
						SpiritAlert.INFORMATION);
			}
			reporteOrdenMedio(mapaComercialOrdenesCompCreadas);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// setea los datos de las Ordenes Medio modificados
	private void setearDescuentosComisionAgenciaOrdenMedio() {
		Iterator proveedorIdIt = mapOrdenMediobyProveedor.keySet().iterator();
		while (proveedorIdIt.hasNext()) {
			String[] key = ((String) proveedorIdIt.next()).split("-");
			Long proveedorIdIf = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenMedioMapa = (Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>) mapOrdenMediobyProveedor
					.get(String.valueOf(proveedorIdIf) + "-" + numeroOrden);

			Iterator ordenMedioIt = ordenMedioMapa.keySet().iterator();
			int contador = 0;

			ArrayList<String> ordenMedioArray = new ArrayList<String>();
			Iterator proveedorIdIt2 = mapOrdenMediobyProveedorPDesc.keySet()
					.iterator();
			while (proveedorIdIt2.hasNext()) {
				String[] keyByProveedorPDesc = ((String) proveedorIdIt2.next())
						.split("-");
				Long proveedorIdIf2 = Long.parseLong(keyByProveedorPDesc[0]);
				String numeroOrdenByProveedorPDesc = keyByProveedorPDesc[1];
				if (proveedorIdIf2.compareTo(proveedorIdIf) == 0
						&& numeroOrdenByProveedorPDesc.equals(numeroOrden))
					ordenMedioArray = (ArrayList<String>) mapOrdenMediobyProveedorPDesc
							.get(String.valueOf(proveedorIdIf2) + "-"
									+ numeroOrdenByProveedorPDesc);
			}

			ArrayList<String> ordenMedioBonoArray = new ArrayList<String>();
			Iterator proveedorIdItBono = mapOrdenMediobyProveedorPBono.keySet()
					.iterator();
			while (proveedorIdItBono.hasNext()) {
				String[] keyByProveedorPBono = ((String) proveedorIdItBono
						.next()).split("-");
				Long proveedorIdIf2 = Long.parseLong(keyByProveedorPBono[0]);
				String numeroOrdenByProveedorPBono = keyByProveedorPBono[1];
				if (proveedorIdIf2.compareTo(proveedorIdIf) == 0
						&& numeroOrdenByProveedorPBono.equals(numeroOrden))
					ordenMedioBonoArray = (ArrayList<String>) mapOrdenMediobyProveedorPBono
							.get(String.valueOf(proveedorIdIf2) + "-"
									+ numeroOrdenByProveedorPBono);
			}

			ArrayList<BigDecimal> ordenMedioArrayCanje = new ArrayList<BigDecimal>();
			Iterator proveedorIdIt3 = mapOrdenMediobyProveedorPCanje.keySet()
					.iterator();
			while (proveedorIdIt3.hasNext()) {
				String[] keyByProveedorPCanje = ((String) proveedorIdIt3.next())
						.split("-");
				Long proveedorIdIf3 = Long.parseLong(keyByProveedorPCanje[0]);
				String numeroOrdenByProveedorPCanje = keyByProveedorPCanje[1];
				if (proveedorIdIf3.compareTo(proveedorIdIf) == 0
						&& numeroOrdenByProveedorPCanje.equals(numeroOrden))
					ordenMedioArrayCanje = (ArrayList<BigDecimal>) mapOrdenMediobyProveedorPCanje
							.get(String.valueOf(proveedorIdIf3) + "-"
									+ numeroOrdenByProveedorPCanje);
			}

			ArrayList<String> ordenMedioArrayObservacion = new ArrayList<String>();
			Iterator proveedorIdIt4 = mapOrdenMediobyProveedorObservacion
					.keySet().iterator();
			while (proveedorIdIt4.hasNext()) {
				String[] keyByProveedorObservacion = ((String) proveedorIdIt4
						.next()).split("-");
				Long proveedorIdIf4 = Long
						.parseLong(keyByProveedorObservacion[0]);
				String numeroOrdenByProveedorObservacion = key[1];
				if (proveedorIdIf4.compareTo(proveedorIdIf) == 0
						&& numeroOrdenByProveedorObservacion
								.equals(numeroOrden))
					ordenMedioArrayObservacion = (ArrayList<String>) mapOrdenMediobyProveedorObservacion
							.get(String.valueOf(proveedorIdIf4) + "-"
									+ numeroOrdenByProveedorObservacion);
			}

			ArrayList<String> ordenMedioArrayCodigoOrden = new ArrayList<String>();
			Iterator proveedorIdIt5 = mapOrdenMediobyProveedorPCodigoOrden
					.keySet().iterator();
			while (proveedorIdIt5.hasNext()) {
				String[] keyByProveedorPCodigoOrden = ((String) proveedorIdIt5
						.next()).split("-");
				Long proveedorIdIf5 = Long
						.parseLong(keyByProveedorPCodigoOrden[0]);
				String numeroOrdenByProveedorPCodigoOrden = key[1];
				if (proveedorIdIf5.compareTo(proveedorIdIf) == 0
						&& numeroOrdenByProveedorPCodigoOrden
								.equals(numeroOrden))
					ordenMedioArrayCodigoOrden = (ArrayList<String>) mapOrdenMediobyProveedorPCodigoOrden
							.get(String.valueOf(proveedorIdIf5) + "-"
									+ numeroOrdenByProveedorPCodigoOrden);
			}

			while (ordenMedioIt.hasNext()) {
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt.next();
				BigDecimal sumaDescuentoOrdenMedio = new BigDecimal(0);
				BigDecimal sumaIvaOrdenMedio = new BigDecimal(0);
				BigDecimal sumaSubtotalOrdenMedio = new BigDecimal(0);
				BigDecimal sumaTotalOrdenMedio = new BigDecimal(0);
				BigDecimal porcentajeCanje = new BigDecimal(0);
				Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenMedioDetalleMap = (Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>) ordenMedioMapa
						.get(ordenMedioIf);
				Iterator ordenMedioDetalleIt = ordenMedioDetalleMap.keySet()
						.iterator();
				
				while (ordenMedioDetalleIt.hasNext()) {
					OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt
							.next();

					BigDecimal porcentajeDescuentoOrden = new BigDecimal(
							ordenMedioArray.get(contador));
					BigDecimal porcentajeBonificacionOrden = new BigDecimal(
							ordenMedioBonoArray.get(contador));
					porcentajeCanje = new BigDecimal(
							ordenMedioArray.get(contador));

					if (ordenMedioIf.getPorcentajeNegociacionComision() != null
							&& ordenMedioIf
									.getPorcentajeNegociacionComision()
									.compareTo(new BigDecimal(0)) == 1) {
						ordenMedioDetalleIf
								.setPorcentajeDescuento(new BigDecimal(0));
						porcentajeBonificacionOrden = new BigDecimal(0);
					} else {
						ordenMedioDetalleIf
								.setPorcentajeDescuento(porcentajeDescuentoOrden);
					}

					ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaArrayList = (ArrayList<OrdenMedioDetalleMapaIf>) ordenMedioDetalleMap
							.get(ordenMedioDetalleIf);

					BigDecimal total_cunias = new BigDecimal(
							ordenMedioDetalleIf.getTotalCunias());
					BigDecimal sumatotal1 = ordenMedioDetalleIf
							.getValorTarifa().multiply(total_cunias);
					ordenMedioDetalleIf.setValorSubtotal(sumatotal1);
					BigDecimal descuento = sumatotal1
							.multiply(ordenMedioDetalleIf
									.getPorcentajeDescuento().divide(
											new BigDecimal(100)));
					ordenMedioDetalleIf.setValorDescuento(descuento);
					BigDecimal bonificacion = sumatotal1
							.subtract(descuento).multiply(
									porcentajeBonificacionOrden
											.divide(new BigDecimal(100)));
					BigDecimal subtotal = sumatotal1.subtract(descuento)
							.subtract(bonificacion);
					ProductoIf productoProveedor = mapaProducto.get(ordenMedioIf.getProductoProveedorId());
					GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
					BigDecimal valor_iva = genericoProveedor.getCobraIva()
							.equals("S") ? subtotal
							.multiply(new BigDecimal(Parametros.IVA / 100D))
							: BigDecimal.ZERO;
					ordenMedioDetalleIf.setValorIva(valor_iva);
					BigDecimal valorTotal = subtotal.add(valor_iva);
					ordenMedioDetalleIf.setValorTotal(valorTotal);

					sumaDescuentoOrdenMedio = sumaDescuentoOrdenMedio
							.add(descuento);
					sumaIvaOrdenMedio = sumaIvaOrdenMedio.add(valor_iva);
					sumaSubtotalOrdenMedio = sumaSubtotalOrdenMedio
							.add(sumatotal1);
					sumaTotalOrdenMedio = sumaTotalOrdenMedio
							.add(valorTotal);

					ordenMedioDetalleIf
							.setPorcentajeComisionAgencia(porcentajeComisionAgencia);
					ordenMedioDetalleIf
							.setPorcentajeDescuentoVenta(porcentajeDescuentoTotalVenta);
					BigDecimal sumatotal = ordenMedioDetalleIf
							.getValorSubtotal();
					BigDecimal descuentoVenta = sumatotal
							.multiply(porcentajeDescuentoTotalVenta
									.divide(new BigDecimal(100)));
					ordenMedioDetalleIf
							.setValorDescuentoVenta(descuentoVenta);
					BigDecimal subtotalVenta1 = sumatotal
							.subtract(descuentoVenta);
					BigDecimal comisionAgencia = subtotalVenta1
							.multiply(porcentajeComisionAgencia
									.divide(new BigDecimal(100)));
					ordenMedioDetalleIf
							.setValorComisionAgencia(comisionAgencia);
					/*
					 * BigDecimal subtotalVenta2 =
					 * subtotalVenta1.add(comisionAgencia); BigDecimal
					 * ivaVenta = subtotalVenta2.multiply(new
					 * BigDecimal(Parametros.IVA / 100D)); BigDecimal
					 * valorTotalVenta = subtotalVenta2.add(ivaVenta);
					 */
					ordenMedioIf
							.setPorcentajeBonificacionCompra(porcentajeBonificacionOrden);

					ordenMedioDetalleMap.put(ordenMedioDetalleIf,
							ordenMedioDetalleMapaArrayList);
				}

				ordenMedioIf
						.setPorcentajeComisionAgencia(porcentajeComisionAgencia);
				ordenMedioIf
						.setPorcentajeBonificacionVenta(porcentajeBonificacionVenta);
				ordenMedioIf
						.setPorcentajeDescuentoVenta(porcentajeDescuentoTotalVenta);
				ordenMedioIf.setValorSubtotal(sumaSubtotalOrdenMedio);
				ordenMedioIf.setValorDescuento(sumaDescuentoOrdenMedio);
				ordenMedioIf.setValorIva(sumaIvaOrdenMedio);
				ordenMedioIf.setValorTotal(sumaTotalOrdenMedio);

				BigDecimal sumatotalOrdenMedio = ordenMedioIf
						.getValorSubtotal();
				BigDecimal descuentoVentaOrdenMedio = sumatotalOrdenMedio
						.multiply(porcentajeDescuentoTotalVenta
								.divide(new BigDecimal(100)));
				ordenMedioIf.setValorDescuentoVenta(descuentoVentaOrdenMedio);
				BigDecimal subtotalVenta1OrdenMedio = sumatotalOrdenMedio
						.subtract(descuentoVentaOrdenMedio);
				BigDecimal comisionAgenciaOrdenMedio = subtotalVenta1OrdenMedio
						.multiply(porcentajeComisionAgencia
								.divide(new BigDecimal(100)));
				ordenMedioIf.setValorComisionAgencia(comisionAgenciaOrdenMedio);
				ordenMedioIf.setPorcentajeCanje(porcentajeCanje);
				/*
				 * BigDecimal subtotalVenta2OrdenMedio =
				 * subtotalVenta1OrdenMedio.add(comisionAgenciaOrdenMedio);
				 * BigDecimal ivaVentaOrdenMedio =
				 * subtotalVenta2OrdenMedio.multiply(new
				 * BigDecimal(Parametros.IVA / 100D)); BigDecimal
				 * valorTotalVenta =
				 * subtotalVenta2OrdenMedio.add(ivaVentaOrdenMedio);
				 */
				ordenMedioIf.setPorcentajeCanje(ordenMedioArrayCanje
						.get(contador));

				// ADD 30 MAYO
				ordenMedioIf.setObservacion(ordenMedioArrayObservacion
						.get(contador));
				// END 30 MAYO

				// ADD 29 JUNIO
				ordenMedioIf
						.setCodigo(ordenMedioArrayCodigoOrden.get(contador));
				ordenMedioIf.setNumeroOrdenAgrupacion(Integer
						.parseInt(numeroOrden));
				// END 29 JUNIO

				ordenMedioMapa.put(ordenMedioIf, ordenMedioDetalleMap);
				contador++;
			}
			mapOrdenMediobyProveedor.put(String.valueOf(proveedorIdIf) + "-"
					+ numeroOrden, ordenMedioMapa);
		}
	}

	public void setearTipoPago() {
		int contador = 1;
		Iterator proveedorIdIt = mapOrdenMediobyProveedor.keySet().iterator();
		while (proveedorIdIt.hasNext()) {
			String[] key = ((String) proveedorIdIt.next()).split("-");
			Long proveedorId = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			Map listOrdenMedio = (Map) mapOrdenMediobyProveedor.get(String
					.valueOf(proveedorId) + "-" + numeroOrden);
			Iterator ordenesMedioMapIt = listOrdenMedio.keySet().iterator();

			while (ordenesMedioMapIt.hasNext()) {
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioMapIt
						.next();
				Map mapOrdenMedioDetalle = (Map) listOrdenMedio
						.get(ordenMedioIf);
				Iterator mapOrdenMedioDetalleIt = mapOrdenMedioDetalle.keySet()
						.iterator();
				while (mapOrdenMedioDetalleIt.hasNext()) {
					OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) mapOrdenMedioDetalleIt
							.next();
					ArrayList<OrdenMedioDetalleMapaIf> mapOrdenMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>) mapOrdenMedioDetalle
							.get(ordenMedioDetalleIf);

					for (OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf : mapOrdenMedioDetalleMapa) {

					}
				}
				contador++;
			}
		}
	}

	public void setearDescuentoCompraPredeterminado() {

		try {
			BigDecimal descuentoCompraPredeterminado = new BigDecimal(0);
			// si es pauta de radio
			Collection descuentoCompra = SessionServiceLocator
					.getParametroEmpresaSessionService()
					.findParametroEmpresaByCodigo("PORCENTAJEDESCTCOMPRARADIO");
			if (getCbPautaRadio().isSelected() && descuentoCompra.size() > 0) {
				ParametroEmpresaIf porcentajeDescuentoCompra = (ParametroEmpresaIf) descuentoCompra
						.iterator().next();
				descuentoCompraPredeterminado = BigDecimal.valueOf(Double
						.valueOf(porcentajeDescuentoCompra.getValor()));
			}
			// para todos los demas tipos de pauta
			else {
				descuentoCompra = SessionServiceLocator
						.getParametroEmpresaSessionService()
						.findParametroEmpresaByCodigo("PORCENTAJEDESCTCOMPRA");
				if (descuentoCompra.size() > 0) {
					ParametroEmpresaIf porcentajeDescuentoCompra = (ParametroEmpresaIf) descuentoCompra
							.iterator().next();
					descuentoCompraPredeterminado = BigDecimal.valueOf(Double
							.valueOf(porcentajeDescuentoCompra.getValor()));
				}
			}

			porcentajeDescuentoCompraDefault = descuentoCompraPredeterminado;
			porcentajeDescuentoCompraDefaultSaved = descuentoCompraPredeterminado;

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void setearDescuentoVentaComisionAgencia() {
		// recorrer y set valores los valores del Plan de Medio Detalle del
		// mapasComercialesPlantilla
		// mapasComercialesPlantilla = Mapa<PlanMedioDetalleIf,
		// Collection<MapaComercialIf>>
		Iterator planMedioDetalleIt = mapasComercialesPlantilla.keySet()
				.iterator();
		
		while (planMedioDetalleIt.hasNext()) {
			PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) planMedioDetalleIt
					.next();
			// comerciales del Plan Medio Detalle
			Collection<MapaComercialIf> mapasComerciales = (Collection<MapaComercialIf>) mapasComercialesPlantilla
					.get(planMedioDetalleIf);
			BigDecimal total_cunias = new BigDecimal(
					planMedioDetalleIf.getTotalCunias());
			BigDecimal sumatotal = planMedioDetalleIf.getValorTarifa()
					.multiply(total_cunias);
			planMedioDetalleIf.setValorSubtotal(sumatotal);
			BigDecimal descuento = sumatotal.multiply(planMedioDetalleIf
					.getPorcentajeDescuento().divide(new BigDecimal(100)));
			planMedioDetalleIf.setValorDescuento(descuento);
			BigDecimal subtotal = sumatotal.subtract(descuento);
			ProductoIf productoProveedor = mapaProducto.get(planMedioDetalleIf.getProductoProveedorId());
			GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
			BigDecimal valor_iva = genericoProveedor.getCobraIva().equals(
					"S") ? subtotal.multiply(new BigDecimal(
					Parametros.IVA / 100D)) : BigDecimal.ZERO;
			planMedioDetalleIf.setValorIva(valor_iva);
			planMedioDetalleIf.setValorIva(valor_iva);
			BigDecimal valorTotal = subtotal.add(valor_iva);
			planMedioDetalleIf.setValorTotal(valorTotal);

			planMedioDetalleIf
					.setPorcentajeDescuentoVenta(porcentajeDescuentoTotalVenta);
			planMedioDetalleIf
					.setPorcentajeComisionAgencia(porcentajeComisionAgencia);
			planMedioDetalleIf
					.setPorcentajeBonificacionVenta(porcentajeBonificacionVenta);
			BigDecimal descuentoVenta = sumatotal
					.multiply(porcentajeDescuentoTotalVenta
							.divide(new BigDecimal(100)));
			planMedioDetalleIf.setValorDescuentoVenta(descuentoVenta);
			BigDecimal subtotalVenta1 = sumatotal.subtract(descuentoVenta);
			BigDecimal comisionAgencia = subtotalVenta1
					.multiply(porcentajeComisionAgencia
							.divide(new BigDecimal(100)));
			planMedioDetalleIf.setValorComisionAgencia(comisionAgencia);
			BigDecimal subtotalVenta2 = subtotalVenta1.add(comisionAgencia);
			BigDecimal ivaVenta = genericoProveedor.getCobraIvaCliente()
					.equals("S") ? subtotalVenta2.multiply(new BigDecimal(
					Parametros.IVA / 100D)) : BigDecimal.ZERO;
			BigDecimal valorTotalVenta = subtotalVenta2.add(ivaVenta);

			planMedioDetalleIf.setValorComisionAgencia(comisionAgencia);
			planMedioDetalleIf.setValorDescuentoVenta(descuentoVenta);
		}
	}

	private boolean generarMapasComercialesPlantilla(boolean problema)
			throws GenericBusinessException {

		// cargo el valor predeterminado de descuento en compra
		setearDescuentoCompraPredeterminado();
		
		//seteo la campaña
		CampanaIf campana = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajoIf.getCampanaId());

		if (tipoPlantilla.equals(TIPO_PLANTILLA_MULTIPLE)) {

			// transformar mapasComercialesPlantillaMultiple a mapasComercialesPlantilla y
			// cargar nuevamente planMedioDetallesPlantilla porque crece segun la cantidad de versiones.

			planMedioDetallesPlantilla = null;
			planMedioDetallesPlantilla = new ArrayList<PlanMedioDetalleIf>();
			mapasComercialesPlantilla = null;
			mapasComercialesPlantilla = new LinkedHashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>>();

			// producto de cliente venia de Orden Trabajo Producto, ahora viene de Comercial, campo Producto (producto_cliente_id)
			if (revisarDerechosPrograma()) {
				
				Iterator mapasComercialesPlantillaMultipleIt = mapasComercialesPlantillaMultiple.keySet().iterator();
				while (mapasComercialesPlantillaMultipleIt.hasNext()) {
					
					PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf) mapasComercialesPlantillaMultipleIt.next();
					ArrayList<InfoComercialMultiple> infoComercialArreglo = (ArrayList) mapasComercialesPlantillaMultiple.get(planMedioDetalle);
					Map<String, Map<Date, Integer>> versionesFechaFrecuencia = generarMapaVersionesFechaFrecuencia(infoComercialArreglo);

					int frecuencia = 0;
					// Recorro todo el mapa versionesFechaContador para ir armando los mapas_comercial por plan_medio_detalle
					Iterator versionesFechaContadorIt = versionesFechaFrecuencia.keySet().iterator();
					while (versionesFechaContadorIt.hasNext()) {
						String version = (String) versionesFechaContadorIt.next();
						Map<Date, Integer> fechaContador = versionesFechaFrecuencia.get(version);

						// el planMedioDetalle original tiene que dividirse en la cantidad de versiones que exista.
						ArrayList<MapaComercialIf> mapas = new ArrayList<MapaComercialIf>();
						PlanMedioDetalleData planMedioDetalleData = new PlanMedioDetalleData();
						planMedioDetalleData.setAudiencia(planMedioDetalle.getAudiencia());
						planMedioDetalleData.setColor(planMedioDetalle.getColor());
						planMedioDetalleData.setVersion(planMedioDetalle.getVersion());
						planMedioDetalleData.setComercial(planMedioDetalle.getComercial());
						planMedioDetalleData.setPauta(planMedioDetalle.getPauta());
						planMedioDetalleData.setAuspicioDescripcion(planMedioDetalle.getAuspicioDescripcion());
						planMedioDetalleData.setValorDescuento(planMedioDetalle.getValorDescuento());
						planMedioDetalleData.setGeneroProgramaId(planMedioDetalle.getGeneroProgramaId());
						planMedioDetalleData.setHoraInicio(planMedioDetalle.getHoraInicio());
						planMedioDetalleData.setPlanMedioMesId(planMedioDetalle.getPlanMedioMesId());
						planMedioDetalleData.setProductoProveedorId(planMedioDetalle.getProductoProveedorId());
						planMedioDetalleData.setPrograma(planMedioDetalle.getPrograma());
						planMedioDetalleData.setProveedorId(planMedioDetalle.getProveedorId());
						planMedioDetalleData.setRaiting1(planMedioDetalle.getRaiting1());
						planMedioDetalleData.setRaiting2(planMedioDetalle.getRaiting2());
						planMedioDetalleData.setRaitingPonderado(planMedioDetalle.getRaitingPonderado());
						planMedioDetalleData.setSeccion(planMedioDetalle.getSeccion());
						planMedioDetalleData.setTamanio(planMedioDetalle.getTamanio());
						planMedioDetalleData.setUbicacion(planMedioDetalle.getUbicacion());

						planMedioDetalleData.setPorcentajeDescuento(porcentajeDescuentoCompraDefault);
						planMedioDetalleData.setPorcentajeBonificacionCompra(porcentajeBonificacionCompraDefault);
						planMedioDetalleData.setPorcentajeDescuentoVenta(porcentajeDescuentoTotalVenta);
						planMedioDetalleData.setPorcentajeComisionAgencia(porcentajeComisionAgencia);
						planMedioDetalleData.setPorcentajeBonificacionVenta(porcentajeBonificacionVenta);

						planMedioDetalleData.setValorTarifa(planMedioDetalle.getValorTarifa());

						Iterator fechaContadorIt = fechaContador.keySet().iterator();
						
						while (fechaContadorIt.hasNext()) {
							Date fecha = (Date) fechaContadorIt.next();
							frecuencia = fechaContador.get(fecha) + frecuencia;

							MapaComercialData mapaComercial = new MapaComercialData();
							mapaComercial.setFecha(fecha);
							mapaComercial.setFrecuencia(fechaContador.get(fecha));
							mapas.add(mapaComercial);
						}
						
						planMedioDetalleData.setTotalCunias(frecuencia);
						frecuencia = 0;

						BigDecimal total_cunias = new BigDecimal(planMedioDetalleData.getTotalCunias());
						BigDecimal sumatotal = planMedioDetalleData.getValorTarifa().multiply(total_cunias);
						planMedioDetalleData.setValorSubtotal(sumatotal);

						BigDecimal descuento = sumatotal.multiply(porcentajeDescuentoCompraDefault.divide(new BigDecimal(100)));
						planMedioDetalleData.setValorDescuento(descuento);
						BigDecimal subtotal = sumatotal.subtract(descuento);
						ProductoIf productoProveedor = mapaProducto.get(planMedioDetalle.getProductoProveedorId());
						GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
						BigDecimal valor_iva = genericoProveedor.getCobraIva().equals("S") ? subtotal.multiply(new BigDecimal(Parametros.IVA / 100D)) : BigDecimal.ZERO;
						planMedioDetalleData.setValorIva(valor_iva);
						BigDecimal valorTotal = subtotal.add(valor_iva);
						planMedioDetalleData.setValorTotal(valorTotal);

						BigDecimal descuentoVenta = new BigDecimal(0);
						planMedioDetalleData.setValorDescuentoVenta(descuentoVenta);
						BigDecimal subtotalVenta1 = sumatotal.subtract(descuentoVenta);
						BigDecimal comisionAgencia = new BigDecimal(0);
						planMedioDetalleData.setValorComisionAgencia(comisionAgencia);
						BigDecimal subtotalVenta2 = subtotalVenta1.add(comisionAgencia);
						BigDecimal ivaVenta = genericoProveedor.getCobraIvaCliente().equals("S") ? subtotalVenta2.multiply(new BigDecimal(Parametros.IVA / 100D)) : BigDecimal.ZERO;
						BigDecimal valorTotalVenta = subtotalVenta2.add(ivaVenta);

						// busco "si existe" el comercial que tenga esta version, por empresa, por campaña, por estado activo y por derechoprograma_id
						Map aMap = new HashMap();
						aMap.put("version", version);
						aMap.put("empresaId", Parametros.getIdEmpresa());						
						aMap.put("campanaId", campana.getId());
						aMap.put("estado", ESTADO_ACTIVO);
						String derechoProgramaNombre = planMedioDetalle.getComercial().split(",")[0];

						String parametro = planMedioDetalle.getComercial().split(",")[0];
						DerechoProgramaIf derechoPrograma = mapaDerechoProgramaPorNombre.get(parametro);

						if (derechoPrograma != null) {
							
							aMap.put("derechoprogramaId", derechoPrograma.getId());
							Collection comerciales = SessionServiceLocator.getComercialSessionService().findComercialByQuery(aMap);
							
							if (comerciales.size() > 0) {
								ComercialIf comercial = (ComercialIf) comerciales.iterator().next();
								// se agrega comercial_id (producto_cliente_id) a planMedioDetalle antes de agregarlo a
								// mapasComercialesPlantilla una version NO ESTARA EN MAS DE UN PLAN MEDIO DETALLE
								planMedioDetalleData.setComercialId(comercial.getId());
								planMedioDetalleData.setProductoClienteId(comercial.getProductoClienteId());
								planMedioDetalleData.setCampanaProductoVersionId(comercial.getCampanaProductoVersionId());
								planMedioDetalleData.setVersion(comercial.getVersion());
								planMedioDetalleData.setComercial(comercial.getVersion()+ "," + planMedioDetalleData.getComercial());
								planMedioDetalleData.setProductoClienteId(comercial.getProductoClienteId());
								planMedioDetallesPlantilla.add(planMedioDetalleData);
							} else {
								problema = true;
							}
							planMedioDetalleData.setNumeroOrdenAgrupacion(planMedioDetalle.getNumeroOrdenAgrupacion());
							mapasComercialesPlantilla.put(planMedioDetalleData,	mapas);
						}
					}
				}
				calcularTotales();
				calcularTotalesVenta();
			} else {
				problema = true;
			}
		}

		return problema;
	}

	// METODO NO SE UTILIZA
	private boolean generarMapasComercialesPlantilla1(boolean problema)
			throws GenericBusinessException {

		if (tipoPlantilla.equals(TIPO_PLANTILLA_MULTIPLE)) {

			// transformar mapasComercialesPlantillaMultiple a
			// mapasComercialesPlantilla y
			// cargar nuevamente planMedioDetallesPlantilla porque crece segun
			// la cantidad de versiones.

			planMedioDetallesPlantilla = null;
			planMedioDetallesPlantilla = new ArrayList<PlanMedioDetalleIf>();
			mapasComercialesPlantilla = null;
			mapasComercialesPlantilla = new LinkedHashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>>();

			// producto de cliente venia de Orden Trabajo Producto,
			// ahora viene de Comercial, campo Producto (producto_cliente_id)

			if (revisarDerechosPrograma()) {
				Iterator mapasComercialesPlantillaMultipleIt = mapasComercialesPlantillaMultiple
						.keySet().iterator();
				while (mapasComercialesPlantillaMultipleIt.hasNext()) {
					PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf) mapasComercialesPlantillaMultipleIt
							.next();
					ArrayList<InfoComercialMultiple> infoComercialArreglo = (ArrayList) mapasComercialesPlantillaMultiple
							.get(planMedioDetalle);

					Map<String, Map<Date, Integer>> versionesFechaFrecuencia = generarMapaVersionesFechaFrecuencia(infoComercialArreglo);

					int frecuencia = 0;
					// Recorro todo el mapa versionesFechaContador para ir
					// armando los mapas_comercial por plan_medio_detalle
					Iterator versionesFechaContadorIt = versionesFechaFrecuencia
							.keySet().iterator();
					while (versionesFechaContadorIt.hasNext()) {
						String version = (String) versionesFechaContadorIt
								.next();
						Map<Date, Integer> fechaContador = versionesFechaFrecuencia
								.get(version);

						// el planMedioDetalle original tiene que dividirse en
						// la cantidad de versiones que exista.
						// PlanMedioDetalleIf planMedioDetallePorVersion =
						// (PlanMedioDetalleIf)DeepCopy.copy(planMedioDetalle);
						ArrayList<MapaComercialIf> mapas = new ArrayList<MapaComercialIf>();

						PlanMedioDetalleData planMedioDetalleData = new PlanMedioDetalleData();

						planMedioDetalleData.setAudiencia(planMedioDetalle
								.getAudiencia());
						planMedioDetalleData.setColor(planMedioDetalle
								.getColor());
						planMedioDetalleData.setVersion(planMedioDetalle
								.getVersion());
						planMedioDetalleData.setComercial(planMedioDetalle
								.getComercial());
						// planMedioDetalleData.setComercialId(planMedioDetalle.getComercialId());
						planMedioDetalleData.setValorDescuento(planMedioDetalle
								.getValorDescuento());

						planMedioDetalleData
								.setGeneroProgramaId(planMedioDetalle
										.getGeneroProgramaId());
						planMedioDetalleData.setHoraInicio(planMedioDetalle
								.getHoraInicio());
						// planMedioDetalleData.setId(planMedioDetalle.getId());

						planMedioDetalleData.setPlanMedioMesId(planMedioDetalle
								.getPlanMedioMesId());
						planMedioDetalleData
								.setProductoProveedorId(planMedioDetalle
										.getProductoProveedorId());
						planMedioDetalleData.setPrograma(planMedioDetalle
								.getPrograma());
						planMedioDetalleData.setProveedorId(planMedioDetalle
								.getProveedorId());
						planMedioDetalleData.setRaiting1(planMedioDetalle
								.getRaiting1());
						planMedioDetalleData.setRaiting2(planMedioDetalle
								.getRaiting2());
						planMedioDetalleData
								.setRaitingPonderado(planMedioDetalle
										.getRaitingPonderado());
						planMedioDetalleData.setSeccion(planMedioDetalle
								.getSeccion());
						planMedioDetalleData.setTamanio(planMedioDetalle
								.getTamanio());
						planMedioDetalleData.setUbicacion(planMedioDetalle
								.getUbicacion());

						planMedioDetalleData
								.setPorcentajeDescuentoVenta(porcentajeDescuentoTotalVenta);
						planMedioDetalleData
								.setPorcentajeComisionAgencia(porcentajeComisionAgencia);
						planMedioDetalleData
								.setPorcentajeBonificacionVenta(porcentajeBonificacionVenta);
						planMedioDetalleData
								.setPorcentajeBonificacionCompra(planMedioDetalle
										.getPorcentajeBonificacionCompra());

						planMedioDetalleData.setValorTarifa(planMedioDetalle
								.getValorTarifa());

						Iterator fechaContadorIt = fechaContador.keySet()
								.iterator();
						while (fechaContadorIt.hasNext()) {
							Date fecha = (Date) fechaContadorIt.next();
							frecuencia = fechaContador.get(fecha) + frecuencia;

							MapaComercialData mapaComercial = new MapaComercialData();
							mapaComercial.setFecha(fecha);
							mapaComercial.setFrecuencia(fechaContador
									.get(fecha));
							mapas.add(mapaComercial);
						}
						planMedioDetalleData.setTotalCunias(frecuencia);
						frecuencia = 0;

						BigDecimal total_cunias = new BigDecimal(
								planMedioDetalleData.getTotalCunias());
						BigDecimal sumatotal = planMedioDetalleData
								.getValorTarifa().multiply(total_cunias);
						planMedioDetalleData.setValorSubtotal(sumatotal);

						BigDecimal descuento = sumatotal
								.multiply(porcentajeDescuentoTotal
										.divide(new BigDecimal(100)));
						planMedioDetalleData.setValorDescuento(descuento);
						BigDecimal subtotal = sumatotal.subtract(descuento);
						ProductoIf productoProveedor = mapaProducto.get(planMedioDetalle.getProductoProveedorId());
						GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
						BigDecimal valor_iva = genericoProveedor.getCobraIva()
								.equals("S") ? subtotal
								.multiply(new BigDecimal(Parametros.IVA / 100D))
								: BigDecimal.ZERO;
						planMedioDetalleData.setValorIva(valor_iva);
						BigDecimal valorTotal = subtotal.add(valor_iva);
						planMedioDetalleData.setValorTotal(valorTotal);

						BigDecimal descuentoVenta = sumatotal
								.multiply(porcentajeDescuentoTotalVenta
										.divide(new BigDecimal(100)));
						planMedioDetalleData
								.setValorDescuentoVenta(descuentoVenta);
						BigDecimal subtotalVenta1 = sumatotal
								.subtract(descuentoVenta);
						BigDecimal comisionAgencia = subtotalVenta1
								.multiply(porcentajeComisionAgencia
										.divide(new BigDecimal(100)));
						planMedioDetalleData
								.setValorComisionAgencia(comisionAgencia);
						BigDecimal subtotalVenta2 = subtotalVenta1
								.add(comisionAgencia);
						BigDecimal ivaVenta = genericoProveedor
								.getCobraIvaCliente().equals("S") ? subtotalVenta2
								.multiply(new BigDecimal(Parametros.IVA / 100D))
								: BigDecimal.ZERO;
						BigDecimal valorTotalVenta = subtotalVenta2
								.add(ivaVenta);

						// busco "si existe" el comercial que tenga esta
						// version, por empresa, por campaña, por estado activo
						// y por derechoprograma_id
						Map aMap = new HashMap();
						aMap.put("version", version);
						aMap.put("empresaId", Parametros.getIdEmpresa());
						CampanaIf campana = SessionServiceLocator
								.getCampanaSessionService().getCampana(
										ordenTrabajoIf.getCampanaId());
						aMap.put("campanaId", campana.getId());
						aMap.put("estado", ESTADO_ACTIVO);
						String derechoProgramaNombre = planMedioDetalle
								.getComercial();
						
						String parametro = planMedioDetalle.getComercial();
						DerechoProgramaIf derechoPrograma = mapaDerechoProgramaPorNombre.get(parametro);
						
						if (derechoPrograma != null) {
							
							aMap.put("derechoprogramaId",
									derechoPrograma.getId());
							Collection comerciales = SessionServiceLocator
									.getComercialSessionService()
									.findComercialByQuery(aMap);
							if (comerciales.size() > 0) {
								ComercialIf comercial = (ComercialIf) comerciales
										.iterator().next();
								// se agrega comercial_id (producto_cliente_id)
								// a planMedioDetalle antes de agregarlo a
								// mapasComercialesPlantilla
								// una version NO ESTARA EN MAS DE UN PLAN MEDIO
								// DETALLE
								planMedioDetalleData.setComercialId(comercial
										.getId());
								// ADD 12 MAYO
								planMedioDetalleData
										.setProductoClienteId(comercial
												.getProductoClienteId());
								// END 12 MAYO
								planMedioDetallesPlantilla
										.add(planMedioDetalleData);
							} else {
								problema = true;
							}

							mapasComercialesPlantilla.put(planMedioDetalleData,
									mapas);
						}// ADD 1 JUNIO
					}

				}
			} else {
				problema = true;
			}
		}

		return problema;
	}

	private void llenarMapasComercialesPlantilla(PlanMedioIf planMedio,
			boolean cambiarPorcentaje) {
		mapasComercialesPlantilla = null;
		mapasComercialesPlantilla = new LinkedHashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>>();
		planMedioIf = planMedio;
		Collection planMedioMesColl;
		Collection planMedioDetalleColl;
		Collection mapaComercialColl;
		try {
			planMedioMesColl = SessionServiceLocator
					.getPlanMedioMesSessionService()
					.findPlanMedioMesByPlanmedioId(planMedio.getId());

			Iterator planMedioMesIt = planMedioMesColl.iterator();
			while (planMedioMesIt.hasNext()) {
				PlanMedioMesIf planMedioMesIf = (PlanMedioMesIf) planMedioMesIt
						.next();
				planMedioDetalleColl = SessionServiceLocator
						.getPlanMedioDetalleSessionService()
						.findPlanMedioDetalleByPlanMedioMesId(
								planMedioMesIf.getId());

				Iterator planMedioDetalleIt = planMedioDetalleColl.iterator();
				while (planMedioDetalleIt.hasNext()) {
					PlanMedioDetalleIf planMedioDetalleIf = (PlanMedioDetalleIf) planMedioDetalleIt
							.next();
					BigDecimal porcentajeTmp = new BigDecimal(0);
					ProductoIf productoProveedor = mapaProducto.get(planMedioDetalleIf.getProductoProveedorId());
					GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
					// preguntar si se lo llama desde función update Si ver lo
					// del porcentaje NO no hacer lo del porcentaje
					if (cambiarPorcentaje) {
						if (porcentajeDescuentoTotal
								.compareTo(planMedioDetalleIf
										.getPorcentajeDescuento()) != 0) {
							BigDecimal descuento = planMedioDetalleIf
									.getValorSubtotal()
									.multiply(
											porcentajeDescuentoTotal
													.divide(new BigDecimal(100)));
							planMedioDetalleIf.setValorDescuento(descuento);
							BigDecimal subtotal = planMedioDetalleIf
									.getValorSubtotal().subtract(descuento);
							BigDecimal valor_iva = genericoProveedor
									.getCobraIva().equals("S") ? subtotal
									.multiply(new BigDecimal(
											Parametros.IVA / 100D))
									: BigDecimal.ZERO;
							planMedioDetalleIf.setValorIva(valor_iva);
							BigDecimal valorTotal = subtotal.add(valor_iva);
							planMedioDetalleIf.setValorTotal(valorTotal);
							planMedioDetalleIf
									.setPorcentajeDescuento(porcentajeDescuentoTotal);
						}
						if (porcentajeDescuentoTotalVenta
								.compareTo(planMedioDetalleIf
										.getPorcentajeDescuentoVenta()) != 0
								|| porcentajeComisionAgencia
										.compareTo(planMedioDetalleIf
												.getPorcentajeComisionAgencia()) != 0
								|| porcentajeBonificacionVenta
										.compareTo(planMedioDetalleIf
												.getPorcentajeBonificacionVenta()) != 0) {
							BigDecimal descuentoVenta = planMedioDetalleIf
									.getValorSubtotal()
									.multiply(
											porcentajeDescuentoTotalVenta
													.divide(new BigDecimal(100)));
							planMedioDetalleIf
									.setValorDescuentoVenta(descuentoVenta);
							BigDecimal subtotalVenta1 = planMedioDetalleIf
									.getValorSubtotal()
									.subtract(descuentoVenta);
							BigDecimal comisionAgencia = subtotalVenta1
									.multiply(porcentajeComisionAgencia
											.divide(new BigDecimal(100)));
							planMedioDetalleIf
									.setValorComisionAgencia(comisionAgencia);
							BigDecimal subtotalVenta2 = subtotalVenta1
									.add(comisionAgencia);
							BigDecimal ivaVenta = genericoProveedor
									.getCobraIvaCliente().equals("S") ? subtotalVenta2
									.multiply(new BigDecimal(
											Parametros.IVA / 100D))
									: BigDecimal.ZERO;
							BigDecimal totalVenta = subtotalVenta2
									.add(ivaVenta);
							planMedioDetalleIf
									.setPorcentajeDescuentoVenta(porcentajeDescuentoTotalVenta);
							planMedioDetalleIf
									.setPorcentajeComisionAgencia(porcentajeComisionAgencia);
							planMedioDetalleIf
									.setPorcentajeBonificacionVenta(porcentajeBonificacionVenta);
						}
					}
					mapaComercialColl = SessionServiceLocator
							.getMapaComercialSessionService()
							.findMapaComercialByPlanMedioDetalleId(
									planMedioDetalleIf.getId());
					mapasComercialesPlantilla.put(planMedioDetalleIf,
							mapaComercialColl);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Map<String, Map<Date, Integer>> generarMapaVersionesFechaFrecuencia(
			ArrayList<InfoComercialMultiple> infoComercialArreglo) {
		Map<String, Map<Date, Integer>> versionesFechaFrecuencia = new LinkedHashMap<String, Map<Date, Integer>>();

		for (InfoComercialMultiple infoComercial : infoComercialArreglo) {

			Map<Date, Integer> fechaContador = new HashMap<Date, Integer>();

			for (int i = 0; i < infoComercial.getLista().size(); i++) {
				// frecuencia = versiones, puede ser: [A], [AA], [AB], [AAA],
				// [AABCCC]
				String versionComercial = infoComercial.getLista().get(i);
				// ver cuantas versiones hay y cuento se repite cada una, pilas
				// con la fecha

				// Todas las configuraciones posibles para contar versiones por
				// fechas en las celdas
				if (versionesFechaFrecuencia.get(versionComercial) == null) {
					fechaContador = new HashMap<Date, Integer>();
					fechaContador.put(infoComercial.getFecha(), infoComercial
							.getFrecuencia().iterator().next());
					versionesFechaFrecuencia.put(versionComercial,
							fechaContador);
				} else if (versionesFechaFrecuencia.get(versionComercial).get(
						infoComercial.getFecha()) == null) {
					fechaContador = versionesFechaFrecuencia
							.get(versionComercial);
					fechaContador.put(infoComercial.getFecha(), infoComercial
							.getFrecuencia().iterator().next());
					fechaContador = versionesFechaFrecuencia
							.get(versionComercial);
					if (fechaContador.get(infoComercial.getFecha()) == null) {
						fechaContador
								.put(infoComercial.getFecha(), infoComercial
										.getFrecuencia().iterator().next());
					} else {
						fechaContador.put(infoComercial.getFecha(),
								fechaContador.get(infoComercial.getFecha()));
					}
					versionesFechaFrecuencia.put(versionComercial,
							fechaContador);
				}
			}
		}
		return versionesFechaFrecuencia;
	}

	private boolean revisarDerechosPrograma() throws GenericBusinessException {

		mapasComercialesPlantilla.clear();
		Iterator mapasComercialesPlantillaMultipleIt = mapasComercialesPlantillaMultiple
				.keySet().iterator();

		// reviso si todos los tipos de comerciales existen en la tabla Derecho
		// Programa
		String tiposComercialNoEncontrados = "";
		while (mapasComercialesPlantillaMultipleIt.hasNext()) {
			PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf) mapasComercialesPlantillaMultipleIt
					.next();
			
			String parametro = planMedioDetalle.getComercial().split(",")[0];
			DerechoProgramaIf derechoPrograma = mapaDerechoProgramaPorNombre.get(parametro);

			if (derechoPrograma == null) {
				if (tiposComercialNoEncontrados.equals("")) {
					tiposComercialNoEncontrados = planMedioDetalle
							.getComercial();
				} else {
					tiposComercialNoEncontrados = tiposComercialNoEncontrados
							+ ", " + planMedioDetalle.getComercial();
				}
			}
		}
		if (!tiposComercialNoEncontrados.equals("")) {
			SpiritAlert.createAlert("Los derechos de programa: "
					+ tiposComercialNoEncontrados + " no están registrados.",
					SpiritAlert.WARNING);
			return false;
		}
		return true;
	}

	// AKI GIOMAYRA QUITAR TIPO 1 JUNIO
	private HashMap getParametrosComercial(String comercial) {
		// Formato de comercial = nombre-tipo
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		String nombre = "";
		String tipo = "";
		// HACER TODOS MAYUSCULAS
		comercial = comercial.trim();
		String[] parame = comercial.split("-");
		if (parame.length == 2) {
			nombre = parame[0].trim();
			tipo = parame[1].trim();
		}

		parametros.put("nombre", nombre);
		parametros.put("tipo", tipo);
		return parametros;
	}

	public void update() {
		try {
			if (validateFields()) {

				if (planMedioIfSaved.getEstado().compareTo(ESTADO_EN_PROCESO) == 0
						|| planMedioIfSaved.getEstado().compareTo(ESTADO_PENDIENTE) == 0
						|| planMedioIfSaved.getEstado().compareTo(ESTADO_APROBADO) == 0
						|| planMedioIfSaved.getEstado().compareTo(ESTADO_PREPAGADO) == 0) {
					
					if (!verificarExistenciaOrdenesMedioIngresadas()) {
						actualizarPlanMedios();
					} else {
						int opcion = JOptionPane
								.showOptionDialog(
										null,
										"Existen Ordenes de Medio Ingresadas, estas ordenes No se actualizarán. ¿Desea continuar con la actualización de la Pauta?",
										"Confirmación",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										options, no);
						if (opcion == JOptionPane.YES_OPTION) {
							actualizarPlanMedios();
						}
					}

				} else {
					String estadoPlanMedioSaved = "";

					if (planMedioIfSaved.getEstado().equals(ESTADO_FACTURADO))
						estadoPlanMedioSaved = NOMBRE_ESTADO_FACTURADO;
					
					else if (planMedioIfSaved.getEstado().equals(ESTADO_HISTORICO))
						estadoPlanMedioSaved = NOMBRE_ESTADO_HISTORICO;
					
					else if (planMedioIfSaved.getEstado().equals(ESTADO_PEDIDO))
						estadoPlanMedioSaved = NOMBRE_ESTADO_PEDIDO;
					
					SpiritAlert.createAlert("El Plan de Medio esta en Estado "
							+ estadoPlanMedioSaved + " no se puede Actualizar",
							SpiritAlert.WARNING);
				}
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la información!",
					SpiritAlert.ERROR);
		}
	}

	private void actualizarPlanMedios() throws GenericBusinessException {
		int opcion = -1;
		boolean bandera = false;
		if (!nuevoPlan) {
			opcion = JOptionPane
					.showOptionDialog(
							null,
							"Se actualizará el Plan de Medio y las Ordenes, ¿Desea hacerlo? ",
							"Confirmación", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, no);
		} else {
			opcion = JOptionPane
					.showOptionDialog(
							null,
							"Se reemplazará el Plan de Medio y las Ordnes, ¿Desea hacerlo? ",
							"Confirmación", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, no);
			bandera = true;
		}
		if (opcion == JOptionPane.YES_OPTION) { // END ADD 15 JUNIO
			registrarPlanMedioForUpdate();
			setearGenericoPautaRegular();
			setearDescuentoyProductoPlanMedioDetallesPlantilla(false);
			setearDescuentoVentaComisionAgencia();

			if (validarComerciales()) {
				SessionServiceLocator.getPlanMedioSessionService()
						.actualizarPlanMedio(planMedioIf, planMedioMesVector,
								planMedioMesRemovidoVector,
								planMedioDetallesPlantilla,
								mapasComercialesPlantilla, nuevoPlan);

				// SpiritAlert.createAlert("Plan de Medios actualizado con éxito",SpiritAlert.INFORMATION);

				if (!bandera) {
					SpiritAlert.createAlert(
							"Plan de Medios actualizado con éxito",
							SpiritAlert.INFORMATION);
				} else {
					SpiritAlert.createAlert(
							"Plan de Medios reemplazado con éxito",
							SpiritAlert.INFORMATION);
				}

				reportePlanMedio();
				// updateOrdenesMedio();
				updateOrdenesMedio(bandera);
				// generarOrdenesMedio3(planMedioIf);

				showSaveMode();
				iniciarMapaTv();
				cleanTablaTGRPtv();
				cleanTablaOrdenesMedio();
			} else {
				SpiritAlert
						.createAlert(
								"Error al actualizar la información! Los comerciales de la Orden de Trabajo escogida no coincide con el mapa de medios cargado !",
								SpiritAlert.ERROR);
			}
		}
	}

	public void delete() {
		try {
			planMedioIf.setEstado(ESTADO_HISTORICO);
			SessionServiceLocator.getPlanMedioSessionService()
					.eliminarPlanMedio(planMedioIf);

			SpiritAlert.createAlert("Plan de Medios eliminado con éxito",
					SpiritAlert.INFORMATION);
			showSaveMode();
			iniciarMapaTv();
			cleanTablaTGRPtv();
			cleanTablaOrdenesMedio();

		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",
					SpiritAlert.ERROR);
		}
	}

	public void authorize() {
		try {
			String estadoLetra = planMedioIf.getEstado();
			String estado = NOMBRE_ESTADO_PENDIENTE;

			if (planMedioIf.getEstado().equals(ESTADO_EN_PROCESO))
				estado = NOMBRE_ESTADO_EN_PROCESO;
			else if (planMedioIf.getEstado().equals(ESTADO_PENDIENTE))
				estado = NOMBRE_ESTADO_PENDIENTE;
			else if (planMedioIf.getEstado().equals(ESTADO_APROBADO))
				estado = NOMBRE_ESTADO_APROBADO;
			else if (planMedioIf.getEstado().equals(ESTADO_FACTURADO))
				estado = NOMBRE_ESTADO_FACTURADO;
			else if (planMedioIf.getEstado().equals(ESTADO_HISTORICO))
				estado = NOMBRE_ESTADO_HISTORICO;
			else if (planMedioIf.getEstado().equals(ESTADO_PEDIDO))
				estado = NOMBRE_ESTADO_PEDIDO;
			else if (planMedioIf.getEstado().equals(ESTADO_PREPAGADO))
				estado = NOMBRE_ESTADO_PREPAGADO;

			if (estado.equals(NOMBRE_ESTADO_FACTURADO)
					|| estado.equals(NOMBRE_ESTADO_PEDIDO)) {
				int opcion = JOptionPane.showOptionDialog(null,
						"¿Autoriza que el Plan de Medios pueda cambiarse?",
						"Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					planMedioIf.setEstado(ESTADO_PENDIENTE);
					SessionServiceLocator.getPlanMedioSessionService()
							.savePlanMedio(planMedioIf);
					SpiritAlert.createAlert(
							"El Plan de Medios ya puede ser modificado.",
							SpiritAlert.INFORMATION);
				}
			} else if (!estado.equals(NOMBRE_ESTADO_HISTORICO)) {
				int opcion = JOptionPane.showOptionDialog(null,
						"¿Desea cambiar el estado del Plan a FACTURADO?",
						"Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					planMedioIf.setEstado(ESTADO_FACTURADO);

					java.util.Date fechaAprobacion = getCmbFechaAprobacion()
							.getDate();
					if (fechaAprobacion == null) {
						planMedioIf.setFechaAprobacion(new java.sql.Timestamp(
								Utilitarios.calendarHoy().getTimeInMillis()));
					}

					SessionServiceLocator.getPlanMedioSessionService()
							.savePlanMedio(planMedioIf);
					SpiritAlert.createAlert(
							"El Plan de Medios cambio a Facturado.",
							SpiritAlert.INFORMATION);
				}
			} else {
				SpiritAlert.createAlert(
						"No es posible modificar un Plan en estado Historico.",
						SpiritAlert.WARNING);
			}
			showSaveMode();
			iniciarMapaTv();
			cleanTablaTGRPtv();
			cleanTablaOrdenesMedio();

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	// ADD 12 OCTUBRE
	private void generarOrdenesMedioForUpdate(PlanMedioIf planMedioSaved,
			int ordenMedioTipo) {
		try {
			planMedioIfSaved = planMedioSaved;

			switch (ordenMedioTipo) {
			case 1:
				crearOrdenesMedioAgrupadasXCanalForUpdate();
				break;
			case 2:
				crearOrdenesMedioAgrupadasXProductoClienteForUpdate(); // ADD 13
																		// OCTUBRE
				break;
			case 3:
				crearOrdenesMedioAgrupadasXCampanaProductoVersionForUpdate();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void generarOrdenesMedio(PlanMedioIf planMedio, int ordenMedioTipo) {
		try {
			planMedioIf = planMedio;

			switch (ordenMedioTipo) {
			case 1:
				crearOrdenesMedioAgrupadasXCanal(); // Ordenes Agrupadas x Canal
				break;
			case 2:
				crearOrdenesMedioAgrupadasXProductoCliente(); // Ordenes
																// Agrupadas x
																// Producto
																// Comercial ADD
																// 13 OCTUBRE
				break;
			case 3:
				// crearOrdenesMedioAgrupadasXVersion(); //Ordenes Agrupadas x
				// Versiones o CampanaProductoVersion
				crearOrdenesMedioAgrupadasXCampanaProductoVersion();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// END 7 OCTUBRE

	// COMENTED 13 OCTUBRE MODIFIED 20 MAYO
	// private void generarOrdenesMedio(PlanMedioIf planMedio,boolean
	// agruparOrdenes){
	/*
	 * private void generarOrdenesMedio(PlanMedioIf planMedio,boolean
	 * agruparOrdenesXCanal){ try { planMedioIf = planMedio; //MODIFIED AND ADD
	 * 20 MAYO if (!agruparOrdenesXCanal){ //planMedioIf.setOrdenesXCanal("NO");
	 * crearOrdenesMedioAgrupadas(); } else{
	 * //planMedioIf.setOrdenesXCanal("SI"); crearOrdenesMedioAgrupadasXCanal();
	 * } //END MODIFIED AND ADD 20 MAYO //MODIFIED AND ADD 3 MAYO /*if
	 * (!agruparOrdenes) crearOrdenesMedio(); else
	 * crearOrdenesMedioAgrupadas();* / //END MODIFIED AND ADD 3 MAYO
	 * //crearOrdenesMedio(); }catch (Exception e) { e.printStackTrace(); } }
	 */

	// ADD 12 OCTUBRE
	// MODIFIED 5 SEPTIEMBRE ADD 15 JUNIO ADD 19 MAYO
	/*
	 * Se crean las ordenes de Medio agrupadas por Canales es decir en una Orden
	 * de Medio contendran todos los productos y versiones del canal en su
	 * detalle ejm Orden de Medio Canal 1: Ecuacolor A, Ecuacolor X, TARJETAS C,
	 * INSTITUCIONAL D..... Detalle de Orden de Medio DE LOS COMERCIALES: A
	 * CUÑA, A MENCION, X CUÑA, X MENCION, B PRESENTACION, C DESPEDIDA LOS MAPAS
	 * DE ORDENES DE MEDIO SERAN SEGUN PROVEEDOR
	 * crearOrdenesMedioAgrupadasXCanalForUpdate
	 */
	private void crearOrdenesMedioAgrupadasXCanalForUpdate()
			throws GenericBusinessException {
		// COMENTED 27 JUNIO
		// planMedioIfSaved.setEstado(ESTADO_APROBADO);
		Collection<OrdenMedioIf> ordenMedioColl = SessionServiceLocator
				.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(
						planMedioIfSaved.getId());

		if (ordenMedioColl.size() > 0) {// && !nuevoPlan){ 15 JUNIO
			// listaProveedoresMap->Map<Long,ClienteOficinaIf>
			// listaProveedoresMap = new HashMap<Long,ClienteOficinaIf>();
			listaProveedoresMapSaved.clear();
			for (PlanMedioDetalleIf planMedioDetalle : planMedioDetallesPlantillaSaved) {
				ClienteOficinaIf proveedor = mapaClienteOficina
						.get(planMedioDetalle.getProveedorId());
				listaProveedoresMapSaved
						.put(String.valueOf(proveedor.getId())
								+ "-"
								+ String.valueOf(planMedioDetalle
										.getNumeroOrdenAgrupacion()), proveedor);
			}

			// ADD 11 MAYO
			// ArrayList<Long>listaProductosCliente = new ArrayList<Long>(); //
			// 20 MAYO

			ArrayList<String> listaProveedoresTemp = new ArrayList<String>();

			// ArrayList<Long>listaComerciales = new ArrayList<Long>();

			Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioTotales = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
			Iterator ordenMedioIt = ordenMedioColl.iterator();
			while (ordenMedioIt.hasNext()) {
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt.next();

				// ADD 11 MAYO
				// Long productoClienteId =
				// ordenMedioIf.getProductoClienteId();//20 MAYO
				Long proveedorId = ordenMedioIf.getProveedorId();

				int tmp = 0;
				/*
				 * for(Long productoClienteIdtmp : listaProductosCliente){
				 * if(productoClienteIdtmp.compareTo(productoClienteId)!=0){
				 * tmp++; } }
				 */// 20 MAYO
				for (String proveedorTmp : listaProveedoresTemp) {
					String[] key = proveedorTmp.split("-");
					Long proveedorIdTmp = Long.parseLong(key[0]);
					String numeroOrden = key[1];
					if (proveedorIdTmp.compareTo(proveedorId) != 0
							|| (proveedorIdTmp.compareTo(proveedorId) == 0 && Integer
									.parseInt(numeroOrden) != ordenMedioIf
									.getNumeroOrdenAgrupacion())) {
						tmp++;
					}
				}

				/*
				 * if(tmp==listaProductosCliente.size()){
				 * listaProductosCliente.add(productoClienteId); }
				 */// 20 mayo
					// END 11 MAYO
				if (tmp == listaProveedoresTemp.size()) {
					listaProveedoresTemp.add(String.valueOf(proveedorId) + "-"
							+ ordenMedioIf.getNumeroOrdenAgrupacion());
				}

				Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleTotales = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();

				Collection<OrdenMedioDetalleIf> ordenMedioDetalleColl = SessionServiceLocator
						.getOrdenMedioDetalleSessionService()
						.findOrdenMedioDetalleByOrdenMedioId(
								ordenMedioIf.getId());
				Iterator ordenMedioDetalleIt = ordenMedioDetalleColl.iterator();
				while (ordenMedioDetalleIt.hasNext()) {
					OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt
							.next();

					ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = new ArrayList<OrdenMedioDetalleMapaIf>();
					Collection<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaColl = SessionServiceLocator
							.getOrdenMedioDetalleMapaSessionService()
							.findOrdenMedioDetalleMapaByOrdenMedioDetalleId(
									ordenMedioDetalleIf.getId());
					Iterator ordenMedioDetalleMapaIt = ordenMedioDetalleMapaColl
							.iterator();
					while (ordenMedioDetalleMapaIt.hasNext()) {
						OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf = (OrdenMedioDetalleMapaIf) ordenMedioDetalleMapaIt
								.next();
						ordenMedioDetalleMapaList.add(ordenMedioDetalleMapaIf);
					}
					ordenesMedioDetalleTotales.put(ordenMedioDetalleIf,
							ordenMedioDetalleMapaList);
				}
				ordenesMedioTotales.put(ordenMedioIf,
						ordenesMedioDetalleTotales);
			}
			// for(Long comercialIdtmp : listaComerciales){
			// for(Long productoClienteIdtmp : listaProductosCliente){//20 MAYO
			for (String proveedorTmp : listaProveedoresTemp) {
				String[] key = proveedorTmp.split("-");
				Long proveedorIdTmp = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				Iterator ordenMedioIt2 = ordenesMedioTotales.keySet()
						.iterator();
				Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioParcial = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
				while (ordenMedioIt2.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt2
							.next();
					// ADD 11 MAYO
					// Long productoClienteId =
					// ordenMedioIf.getProductoClienteId();//20 MAYO
					Long proveedorId = ordenMedioIf.getProveedorId();
					Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleMap = ordenesMedioTotales
							.get(ordenMedioIf);
					Iterator ordenMedioDetalleIt = ordenesMedioDetalleMap
							.keySet().iterator();
					if (ordenMedioDetalleIt.hasNext()) {
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt
								.next();
						// Long comercialId =
						// ordenMedioDetalleIf.getComercialId();// 20 MAYO
						if (proveedorId.compareTo(proveedorIdTmp) == 0
								&& Integer.parseInt(numeroOrden) == ordenMedioIf
										.getNumeroOrdenAgrupacion()) {
							// if(productoClienteId.compareTo(productoClienteIdtmp)==0){
							// if(comercialId.compareTo(comercialIdtmp)==0){
							ordenesMedioParcial.put(ordenMedioIf,
									ordenesMedioDetalleMap);
						}
					}
				}
				// mapaComercialOrdenesComp.put(comercialIdtmp,ordenesMedioParcial);
				// mapaProductoClienteOrdenesComp.put(productoClienteIdtmp,
				// ordenesMedioParcial);//20 NAYO
				mapaProveedorOrdenesCompSaved.put(
						String.valueOf(proveedorIdTmp) + "-" + numeroOrden,
						ordenesMedioParcial);
			}
		}
	}

	// END 15 JUNIO
	// END 12 OCTUBRE

	// MODIFIED 5 SEPTIEMBRE ADD 15 JUNIO ADD 19 MAYO
	/*
	 * Se crean las ordenes de Medio agrupadas por Canales es decir en una Orden
	 * de Medio contendran todos los productos y versiones del canal en su
	 * detalle ejm Orden de Medio Canal 1: Ecuacolor A, Ecuacolor X, TARJETAS C,
	 * INSTITUCIONAL D..... Detalle de Orden de Medio DE LOS COMERCIALES: A
	 * CUÑA, A MENCION, X CUÑA, X MENCION, B PRESENTACION, C DESPEDIDA LOS MAPAS
	 * DE ORDENES DE MEDIO SERAN SEGUN PROVEEDOR
	 * crearOrdenesMedioAgrupadasXCanalForUpdate
	 */
	/*
	 * private void crearOrdenesMedioAgrupadasXCanalForUpdate() throws
	 * GenericBusinessException { //COMENTED 27 JUNIO
	 * //planMedioIfSaved.setEstado(ESTADO_APROBADO);
	 * Collection<OrdenMedioIf>ordenMedioColl =
	 * SessionServiceLocator.getOrdenMedioSessionService
	 * ().findOrdenMedioByPlanMedioId(planMedioIfSaved.getId());
	 * 
	 * if(ordenMedioColl.size() > 0 ){//&& !nuevoPlan){ 15 JUNIO
	 * //listaProveedoresMap->Map<Long,ClienteOficinaIf> listaProveedoresMap =
	 * new HashMap<Long,ClienteOficinaIf>(); listaProveedoresMapSaved.clear();
	 * for(PlanMedioDetalleIf planMedioDetalle :
	 * planMedioDetallesPlantillaSaved){ ClienteOficinaIf proveedor =
	 * SessionServiceLocator
	 * .getClienteOficinaSessionService().getClienteOficina(
	 * planMedioDetalle.getProveedorId());
	 * listaProveedoresMapSaved.put(proveedor.getId(),proveedor); }
	 * 
	 * //ADD 11 MAYO //ArrayList<Long>listaProductosCliente = new
	 * ArrayList<Long>(); // 20 MAYO
	 * 
	 * ArrayList<Long>listaProveedoresTemp = new ArrayList<Long>();
	 * 
	 * //ArrayList<Long>listaComerciales = new ArrayList<Long>();
	 * 
	 * Map <OrdenMedioIf,Map
	 * <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>
	 * ordenesMedioTotales = new LinkedHashMap<OrdenMedioIf,
	 * Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>(); Iterator
	 * ordenMedioIt = ordenMedioColl.iterator(); while(ordenMedioIt.hasNext()){
	 * OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenMedioIt.next();
	 * 
	 * //ADD 11 MAYO //Long productoClienteId =
	 * ordenMedioIf.getProductoClienteId();//20 MAYO Long proveedorId =
	 * ordenMedioIf.getProveedorId();
	 * 
	 * int tmp = 0; /*for(Long productoClienteIdtmp : listaProductosCliente){
	 * if(productoClienteIdtmp.compareTo(productoClienteId)!=0){ tmp++; } }*
	 * ///20 MAYO for(Long proveedorIdtmp : listaProveedoresTemp){
	 * if(proveedorIdtmp.compareTo(proveedorId)!=0){ tmp++; } }
	 * 
	 * /*if(tmp==listaProductosCliente.size()){
	 * listaProductosCliente.add(productoClienteId); }* ///20 mayo //END 11 MAYO
	 * if(tmp==listaProveedoresTemp.size()){
	 * listaProveedoresTemp.add(proveedorId); }
	 * 
	 * Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>
	 * ordenesMedioDetalleTotales = new LinkedHashMap<OrdenMedioDetalleIf,
	 * ArrayList<OrdenMedioDetalleMapaIf>>();
	 * 
	 * Collection<OrdenMedioDetalleIf>ordenMedioDetalleColl =
	 * SessionServiceLocator
	 * .getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId
	 * (ordenMedioIf.getId()); Iterator ordenMedioDetalleIt =
	 * ordenMedioDetalleColl.iterator(); while(ordenMedioDetalleIt.hasNext()){
	 * OrdenMedioDetalleIf ordenMedioDetalleIf =
	 * (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
	 * 
	 * ArrayList<OrdenMedioDetalleMapaIf>ordenMedioDetalleMapaList = new
	 * ArrayList<OrdenMedioDetalleMapaIf>(); Collection<OrdenMedioDetalleMapaIf>
	 * ordenMedioDetalleMapaColl =
	 * SessionServiceLocator.getOrdenMedioDetalleMapaSessionService
	 * ().findOrdenMedioDetalleMapaByOrdenMedioDetalleId
	 * (ordenMedioDetalleIf.getId()); Iterator ordenMedioDetalleMapaIt =
	 * ordenMedioDetalleMapaColl.iterator();
	 * while(ordenMedioDetalleMapaIt.hasNext()){ OrdenMedioDetalleMapaIf
	 * ordenMedioDetalleMapaIf =
	 * (OrdenMedioDetalleMapaIf)ordenMedioDetalleMapaIt.next();
	 * ordenMedioDetalleMapaList.add(ordenMedioDetalleMapaIf); }
	 * ordenesMedioDetalleTotales
	 * .put(ordenMedioDetalleIf,ordenMedioDetalleMapaList); }
	 * ordenesMedioTotales.put(ordenMedioIf,ordenesMedioDetalleTotales); }
	 * //for(Long comercialIdtmp : listaComerciales){ //for(Long
	 * productoClienteIdtmp : listaProductosCliente){//20 MAYO for(Long
	 * proveedorIdtmp : listaProveedoresTemp){ Iterator ordenMedioIt2 =
	 * ordenesMedioTotales.keySet().iterator(); Map <OrdenMedioIf,Map
	 * <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>
	 * ordenesMedioParcial = new LinkedHashMap<OrdenMedioIf,
	 * Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
	 * while(ordenMedioIt2.hasNext()){ OrdenMedioIf ordenMedioIf =
	 * (OrdenMedioIf)ordenMedioIt2.next(); //ADD 11 MAYO //Long
	 * productoClienteId = ordenMedioIf.getProductoClienteId();//20 MAYO Long
	 * proveedorId = ordenMedioIf.getProveedorId(); Map
	 * <OrdenMedioDetalleIf,ArrayList
	 * <OrdenMedioDetalleMapaIf>>ordenesMedioDetalleMap=
	 * ordenesMedioTotales.get(ordenMedioIf); Iterator ordenMedioDetalleIt =
	 * ordenesMedioDetalleMap.keySet().iterator();
	 * if(ordenMedioDetalleIt.hasNext()){ OrdenMedioDetalleIf
	 * ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
	 * //Long comercialId = ordenMedioDetalleIf.getComercialId();// 20 MAYO
	 * if(proveedorId.compareTo(proveedorIdtmp)==0){
	 * //if(productoClienteId.compareTo(productoClienteIdtmp)==0){
	 * //if(comercialId.compareTo(comercialIdtmp)==0){
	 * ordenesMedioParcial.put(ordenMedioIf, ordenesMedioDetalleMap); } } }
	 * //mapaComercialOrdenesComp.put(comercialIdtmp,ordenesMedioParcial);
	 * //mapaProductoClienteOrdenesComp.put(productoClienteIdtmp,
	 * ordenesMedioParcial);//20 NAYO
	 * mapaProveedorOrdenesCompSaved.put(proveedorIdtmp, ordenesMedioParcial); }
	 * } }
	 */
	// END 15 JUNIO

	/*
	 * COMENTED 5 SEPTIEMBRE ADD 15 JUNIO ADD 19 MAYO Se crean las ordenes de
	 * Medio agrupadas por Canales es decir en una Orden de Medio contendran
	 * todos los productos del canal en su detalle ejm Orden de Medio Canal 1:
	 * Ecuacolor A, TARJETAS C, INSTITUCIONAL D..... Detalle de Orden de Medio
	 * DE LOS COMERCIALES: A CUÑA, A MENCION, B PRESENTACION, C DESPEDIDA LOS
	 * MAPAS DE ORDENES DE MEDIO SERAN SEGUN PROVEEDOR
	 * crearOrdenesMedioAgrupadasXCanalForUpdate
	 * 
	 * private void crearOrdenesMedioAgrupadasXCanalForUpdate() throws
	 * GenericBusinessException { //COMENTED 27 JUNIO
	 * //planMedioIfSaved.setEstado(ESTADO_APROBADO);
	 * Collection<OrdenMedioIf>ordenMedioColl =
	 * SessionServiceLocator.getOrdenMedioSessionService
	 * ().findOrdenMedioByPlanMedioId(planMedioIfSaved.getId());
	 * 
	 * if(ordenMedioColl.size() > 0 ){//&& !nuevoPlan){ 15 JUNIO
	 * //listaProveedoresMap->Map<Long,ClienteOficinaIf> listaProveedoresMap =
	 * new HashMap<Long,ClienteOficinaIf>(); listaProveedoresMapSaved.clear();
	 * for(PlanMedioDetalleIf planMedioDetalle :
	 * planMedioDetallesPlantillaSaved){ ClienteOficinaIf proveedor =
	 * SessionServiceLocator
	 * .getClienteOficinaSessionService().getClienteOficina(
	 * planMedioDetalle.getProveedorId());
	 * listaProveedoresMapSaved.put(proveedor.getId(),proveedor); }
	 * 
	 * //ADD 11 MAYO //ArrayList<Long>listaProductosCliente = new
	 * ArrayList<Long>(); // 20 MAYO
	 * 
	 * ArrayList<Long>listaProveedoresTemp = new ArrayList<Long>();
	 * 
	 * //ArrayList<Long>listaComerciales = new ArrayList<Long>();
	 * 
	 * Map <OrdenMedioIf,Map
	 * <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>
	 * ordenesMedioTotales = new LinkedHashMap<OrdenMedioIf,
	 * Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>(); Iterator
	 * ordenMedioIt = ordenMedioColl.iterator(); while(ordenMedioIt.hasNext()){
	 * OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenMedioIt.next();
	 * 
	 * //ADD 11 MAYO //Long productoClienteId =
	 * ordenMedioIf.getProductoClienteId();//20 MAYO Long proveedorId =
	 * ordenMedioIf.getProveedorId();
	 * 
	 * int tmp = 0; for(Long productoClienteIdtmp : listaProductosCliente){
	 * if(productoClienteIdtmp.compareTo(productoClienteId)!=0){ tmp++; } }//20
	 * MAYO for(Long proveedorIdtmp : listaProveedoresTemp){
	 * if(proveedorIdtmp.compareTo(proveedorId)!=0){ tmp++; } }
	 * 
	 * if(tmp==listaProductosCliente.size()){
	 * listaProductosCliente.add(productoClienteId); }//20 mayo //END 11 MAYO
	 * if(tmp==listaProveedoresTemp.size()){
	 * listaProveedoresTemp.add(proveedorId); }
	 * 
	 * Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>
	 * ordenesMedioDetalleTotales = new LinkedHashMap<OrdenMedioDetalleIf,
	 * ArrayList<OrdenMedioDetalleMapaIf>>();
	 * 
	 * Collection<OrdenMedioDetalleIf>ordenMedioDetalleColl =
	 * SessionServiceLocator
	 * .getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId
	 * (ordenMedioIf.getId()); Iterator ordenMedioDetalleIt =
	 * ordenMedioDetalleColl.iterator(); while(ordenMedioDetalleIt.hasNext()){
	 * OrdenMedioDetalleIf ordenMedioDetalleIf =
	 * (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
	 * 
	 * ArrayList<OrdenMedioDetalleMapaIf>ordenMedioDetalleMapaList = new
	 * ArrayList<OrdenMedioDetalleMapaIf>(); Collection<OrdenMedioDetalleMapaIf>
	 * ordenMedioDetalleMapaColl =
	 * SessionServiceLocator.getOrdenMedioDetalleMapaSessionService
	 * ().findOrdenMedioDetalleMapaByOrdenMedioDetalleId
	 * (ordenMedioDetalleIf.getId()); Iterator ordenMedioDetalleMapaIt =
	 * ordenMedioDetalleMapaColl.iterator();
	 * while(ordenMedioDetalleMapaIt.hasNext()){ OrdenMedioDetalleMapaIf
	 * ordenMedioDetalleMapaIf =
	 * (OrdenMedioDetalleMapaIf)ordenMedioDetalleMapaIt.next();
	 * ordenMedioDetalleMapaList.add(ordenMedioDetalleMapaIf); }
	 * ordenesMedioDetalleTotales
	 * .put(ordenMedioDetalleIf,ordenMedioDetalleMapaList); }
	 * ordenesMedioTotales.put(ordenMedioIf,ordenesMedioDetalleTotales); }
	 * //for(Long comercialIdtmp : listaComerciales){ //for(Long
	 * productoClienteIdtmp : listaProductosCliente){//20 MAYO for(Long
	 * proveedorIdtmp : listaProveedoresTemp){ Iterator ordenMedioIt2 =
	 * ordenesMedioTotales.keySet().iterator(); Map <OrdenMedioIf,Map
	 * <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>
	 * ordenesMedioParcial = new LinkedHashMap<OrdenMedioIf,
	 * Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
	 * while(ordenMedioIt2.hasNext()){ OrdenMedioIf ordenMedioIf =
	 * (OrdenMedioIf)ordenMedioIt2.next(); //ADD 11 MAYO //Long
	 * productoClienteId = ordenMedioIf.getProductoClienteId();//20 MAYO Long
	 * proveedorId = ordenMedioIf.getProveedorId(); Map
	 * <OrdenMedioDetalleIf,ArrayList
	 * <OrdenMedioDetalleMapaIf>>ordenesMedioDetalleMap=
	 * ordenesMedioTotales.get(ordenMedioIf); Iterator ordenMedioDetalleIt =
	 * ordenesMedioDetalleMap.keySet().iterator();
	 * if(ordenMedioDetalleIt.hasNext()){ OrdenMedioDetalleIf
	 * ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
	 * //Long comercialId = ordenMedioDetalleIf.getComercialId();// 20 MAYO
	 * if(proveedorId.compareTo(proveedorIdtmp)==0){
	 * //if(productoClienteId.compareTo(productoClienteIdtmp)==0){
	 * //if(comercialId.compareTo(comercialIdtmp)==0){
	 * ordenesMedioParcial.put(ordenMedioIf, ordenesMedioDetalleMap); } } }
	 * //mapaComercialOrdenesComp.put(comercialIdtmp,ordenesMedioParcial);
	 * //mapaProductoClienteOrdenesComp.put(productoClienteIdtmp,
	 * ordenesMedioParcial);//20 NAYO
	 * mapaProveedorOrdenesCompSaved.put(proveedorIdtmp, ordenesMedioParcial); }
	 * } } //END COMENTED 5 SEPTIEMBRE END 15 JUNIO
	 */

	// ADD 12 OCTUBRE
	/*
	 * Se crean las ordenes de Medio agrupadas por Productos y Versiones del
	 * Producto para actualizar el Plan de Medio es decir en una Orden de Medio
	 * contendran varios comerciales en su detalle ejm Orden de Medio segun
	 * versiones: Ecuacolor A, Ecuacolor X Detalle de Orden de Medio DE LOS
	 * COMERCIALES: A CUÑA, A MENCION, A PRESENTACION, A DESPEDIDA LOS MAPAS DE
	 * ORDENES DE MEDIO SERAN SEGUN VERSION DE UN PRODUCTO CLIENTE
	 */
	private void crearOrdenesMedioAgrupadasXCampanaProductoVersionForUpdate()
			throws GenericBusinessException {
		// MODIFIED 15 JUNIO
		// GIOMY REVISAR QUE SUCEDE CON EL PLAN MEDIO SAVE
		// planMedioIf.setEstado(ESTADO_APROBADO);

		// COMENTED 27 JUNIO
		// planMedioIfSaved.setEstado(ESTADO_APROBADO);
		Collection<OrdenMedioIf> ordenMedioColl = SessionServiceLocator
				.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(
						planMedioIfSaved.getId());

		if (ordenMedioColl.size() > 0) {// 15 JUNIO && !nuevoPlan){
			// listaProveedoresMap->Map<Long,ClienteOficinaIf>
			// listaProveedoresMap = new HashMap<Long,ClienteOficinaIf>();
			listaProveedoresMapSaved.clear();
			for (PlanMedioDetalleIf planMedioDetalle : planMedioDetallesPlantillaSaved) {
				ClienteOficinaIf proveedor = mapaClienteOficina
						.get(planMedioDetalle.getProveedorId());
				listaProveedoresMapSaved.put(String.valueOf(proveedor.getId())
						+ "-" + planMedioDetalle.getNumeroOrdenAgrupacion(),
						proveedor);
			}

			// ArrayList<Long>listaComerciales = new ArrayList<Long>();
			// ADD 11 MAYO
			// ArrayList<Long>listaProductosCliente = new ArrayList<Long>();
			// COMENTED 5 SEPTIEMBRE
			// COMENTED 12 OCTUBRE ADD 5 SEPTIEMBRE
			/*
			 * Map<Long,ArrayList<String>> mapProductoClienteListVersiones = new
			 * LinkedHashMap<Long, ArrayList<String>>();
			 * ArrayList<String>listaProductosCliente;
			 */

			// ADD 12 OCTUBRE
			Map<String, ArrayList<Long>> mapProductoClienteListVersiones = new LinkedHashMap<String, ArrayList<Long>>();
			ArrayList<Long> listaProductosCliente;

			Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioTotales = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
			Iterator ordenMedioIt = ordenMedioColl.iterator();
			while (ordenMedioIt.hasNext()) {
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt.next();

				// COMENETED 5 SEPTIEMBRE ADD 11 MAYO
				/*
				 * Long productoClienteId = ordenMedioIf.getProductoClienteId();
				 * int tmp = 0; for(Long productoClienteIdtmp :
				 * listaProductosCliente){
				 * if(productoClienteIdtmp.compareTo(productoClienteId)!=0){
				 * tmp++; } }
				 * 
				 * if(tmp==listaProductosCliente.size()){
				 * listaProductosCliente.add(productoClienteId); }
				 */
				// END 11 MAYO

				// MODIFIED 5 SEPTIEMBRE
				Long productoClienteId = ordenMedioIf.getProductoClienteId();
				String numeroOrdenProductoCliente = String.valueOf(ordenMedioIf
						.getNumeroOrdenAgrupacion());
				// String productoClienteVersion = ""; COMENTED 12 OCTUBRE

				Long campanaProductoVersion = 0L; // ADD 12 OCTUBRE

				// ADD 5 SEPTIEMBRE
				Collection<OrdenMedioDetalleIf> ordenMedioDetalleVersionColl = SessionServiceLocator
						.getOrdenMedioDetalleSessionService()
						.findOrdenMedioDetalleByOrdenMedioId(
								ordenMedioIf.getId());
				if (!ordenMedioDetalleVersionColl.isEmpty())
					campanaProductoVersion = ordenMedioDetalleVersionColl
							.iterator().next().getCampanaProductoVersionId(); // ADD
																				// 12
																				// OCTUBRE
				// productoClienteVersion =
				// ordenMedioDetalleVersionColl.iterator().next().getProductoClienteVersion();
				// COMENTED 12 OCTUBRE

				// END 5 SEPTIEMBRE

				// COMENTED 12 OCTUBRE
				/*
				 * if(mapProductoClienteListVersiones.containsKey(productoClienteId
				 * )){ listaProductosCliente =
				 * mapProductoClienteListVersiones.get(productoClienteId); if
				 * (!listaProductosCliente.contains(productoClienteVersion)){
				 * listaProductosCliente.add(productoClienteVersion); } }else{
				 * listaProductosCliente = new ArrayList<String>();
				 * listaProductosCliente.add(productoClienteVersion);
				 * mapProductoClienteListVersiones.put(productoClienteId,
				 * listaProductosCliente); }
				 */
				// END MODIFIED 5 SEPTIEMBRE

				// ADD 12 OCTUBRE
				boolean existeProductoCliente = false;
				for (String productoClienteTemp : mapProductoClienteListVersiones
						.keySet()) {
					String[] key = productoClienteTemp.split("-");
					Long idProductoClienteTemp = Long.parseLong(key[0]);
					String numeroOrdenTmp = key[1];
					if (idProductoClienteTemp.compareTo(productoClienteId) == 0
							&& numeroOrdenTmp
									.equals(numeroOrdenProductoCliente)) {
						listaProductosCliente = mapProductoClienteListVersiones
								.get(String.valueOf(idProductoClienteTemp)
										+ "-" + numeroOrdenTmp);
						boolean existeVersion = false;
						for (Long idCampanaProductoVersionTemp : listaProductosCliente) {
							if (idCampanaProductoVersionTemp
									.compareTo(campanaProductoVersion) == 0) {
								existeVersion = true;
								break;
							}
						}
						if (!existeVersion) {
							listaProductosCliente.add(campanaProductoVersion);
						}
						existeProductoCliente = true;
						break;
					}
				}
				if (!existeProductoCliente) {
					listaProductosCliente = new ArrayList<Long>();
					listaProductosCliente.add(campanaProductoVersion);
					mapProductoClienteListVersiones.put(
							String.valueOf(productoClienteId) + "-"
									+ numeroOrdenProductoCliente,
							listaProductosCliente);
				}
				// END 12 OCTUBRE

				Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleTotales = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();

				Collection<OrdenMedioDetalleIf> ordenMedioDetalleColl = SessionServiceLocator
						.getOrdenMedioDetalleSessionService()
						.findOrdenMedioDetalleByOrdenMedioId(
								ordenMedioIf.getId());
				Iterator ordenMedioDetalleIt = ordenMedioDetalleColl.iterator();
				while (ordenMedioDetalleIt.hasNext()) {
					OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt
							.next();

					ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = new ArrayList<OrdenMedioDetalleMapaIf>();
					Collection<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaColl = SessionServiceLocator
							.getOrdenMedioDetalleMapaSessionService()
							.findOrdenMedioDetalleMapaByOrdenMedioDetalleId(
									ordenMedioDetalleIf.getId());
					Iterator ordenMedioDetalleMapaIt = ordenMedioDetalleMapaColl
							.iterator();
					while (ordenMedioDetalleMapaIt.hasNext()) {
						OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf = (OrdenMedioDetalleMapaIf) ordenMedioDetalleMapaIt
								.next();
						ordenMedioDetalleMapaList.add(ordenMedioDetalleMapaIf);
					}
					ordenesMedioDetalleTotales.put(ordenMedioDetalleIf,
							ordenMedioDetalleMapaList);
				}
				ordenesMedioTotales.put(ordenMedioIf,
						ordenesMedioDetalleTotales);
			}

			// for(Long comercialIdtmp : listaComerciales){
			// COMENTED 5 SEPTIEMBRE
			// for(Long productoClienteIdtmp : listaProductosCliente){
			// COMENTED 12 OCTUBRE MODIFIED 5 SEPTIEMBRE
			/*
			 * for(Long productoClienteIdtmp :
			 * mapProductoClienteListVersiones.keySet()){ //ADD 5 SEPTIEMBRE
			 * Map<String,Map <OrdenMedioIf,Map<OrdenMedioDetalleIf,
			 * ArrayList<OrdenMedioDetalleMapaIf>>>> mapaVersionesOrdenesComp =
			 * new LinkedHashMap<String,
			 * Map<OrdenMedioIf,Map<OrdenMedioDetalleIf
			 * ,ArrayList<OrdenMedioDetalleMapaIf>>>>(); ArrayList<String>
			 * listProductoClienteVersiontmp =
			 * mapProductoClienteListVersiones.get(productoClienteIdtmp); for
			 * (String productoClienteVersiontmp :
			 * listProductoClienteVersiontmp){
			 * 
			 * Iterator ordenMedioIt2 = ordenesMedioTotales.keySet().iterator();
			 * Map <OrdenMedioIf,Map
			 * <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>
			 * ordenesMedioParcial = new LinkedHashMap<OrdenMedioIf,
			 * Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
			 * while(ordenMedioIt2.hasNext()){ OrdenMedioIf ordenMedioIf =
			 * (OrdenMedioIf)ordenMedioIt2.next(); //ADD 11 MAYO Long
			 * productoClienteId = ordenMedioIf.getProductoClienteId(); Map
			 * <OrdenMedioDetalleIf
			 * ,ArrayList<OrdenMedioDetalleMapaIf>>ordenesMedioDetalleMap=
			 * ordenesMedioTotales.get(ordenMedioIf); Iterator
			 * ordenMedioDetalleIt = ordenesMedioDetalleMap.keySet().iterator();
			 * if(ordenMedioDetalleIt.hasNext()){ OrdenMedioDetalleIf
			 * ordenMedioDetalleIf =
			 * (OrdenMedioDetalleIf)ordenMedioDetalleIt.next(); //ADD 5
			 * SEPTIEMBRE String productoClienteVersion =
			 * ordenMedioDetalleIf.getProductoClienteVersion(); //Long
			 * comercialId = ordenMedioDetalleIf.getComercialId();
			 * //if(comercialId.compareTo(comercialIdtmp)==0){ //COMENTED 5
			 * SEPTIEMBRE
			 * //if(productoClienteId.compareTo(productoClienteIdtmp)==0){ //ADD
			 * 5 SEPTIEMBRE
			 * if(productoClienteId.compareTo(productoClienteIdtmp)==0 &&
			 * productoClienteVersion.compareTo(productoClienteVersiontmp) == 0
			 * ){ ordenesMedioParcial.put(ordenMedioIf, ordenesMedioDetalleMap);
			 * //ADD 31 MAYO //posibilidad de que vaya la lista temporal de
			 * observaciones //END 31 MAYO } } }
			 * mapaVersionesOrdenesComp.put(productoClienteVersiontmp
			 * ,ordenesMedioParcial); }//ADD 5 SEPTIEMBRE
			 * //mapaComercialOrdenesComp
			 * .put(comercialIdtmp,ordenesMedioParcial); //COMENTED 5 SEPTIEMBRE
			 * //mapaProductoClienteOrdenesCompSaved.put(productoClienteIdtmp,
			 * ordenesMedioParcial); //ADD 5 SEPTIEMBRE
			 * mapaProductoClienteVersionesOrdenesCompSaved
			 * .put(productoClienteIdtmp,mapaVersionesOrdenesComp); }
			 */

			// MODIFIED 12 OCTUBRE
			for (String productoClientetmp : mapProductoClienteListVersiones
					.keySet()) {
				// COMENTED 12 OCTUBRE ADD 5 SEPTIEMBRE
				/*
				 * Map<String,Map <OrdenMedioIf,Map<OrdenMedioDetalleIf,
				 * ArrayList<OrdenMedioDetalleMapaIf>>>>
				 * mapaVersionesOrdenesComp = new LinkedHashMap<String,
				 * Map<OrdenMedioIf
				 * ,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf
				 * >>>>(); ArrayList<String> listProductoClienteVersiontmp =
				 * mapProductoClienteListVersiones.get(productoClienteIdtmp);
				 */
				String[] key = productoClientetmp.split("-");
				Long productoClienteIdtmp = Long.parseLong(key[0]);
				String numeroOrdenTmp = key[1];
				Map<Long, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaVersionesOrdenesComp = new LinkedHashMap<Long, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>>();
				ArrayList<Long> listProductoClienteVersiontmp = mapProductoClienteListVersiones
						.get(String.valueOf(productoClienteIdtmp) + "-"
								+ numeroOrdenTmp);

				// for (String productoClienteVersiontmp :
				// listProductoClienteVersiontmp){ COMENTED 12 OCTUBRE
				for (Long campanaProductoVersiontmp : listProductoClienteVersiontmp) { // ADD
																						// 12
																						// OCTUBRE

					Iterator ordenMedioIt2 = ordenesMedioTotales.keySet()
							.iterator();
					Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioParcial = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
					while (ordenMedioIt2.hasNext()) {
						OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt2
								.next();
						// ADD 11 MAYO
						Long productoClienteId = ordenMedioIf
								.getProductoClienteId();
						Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleMap = ordenesMedioTotales
								.get(ordenMedioIf);
						Iterator ordenMedioDetalleIt = ordenesMedioDetalleMap
								.keySet().iterator();
						if (ordenMedioDetalleIt.hasNext()) {
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt
									.next();
							// COMENTED 12 OCTUBRE ADD 5 SEPTIEMBRE
							// String productoClienteVersion =
							// ordenMedioDetalleIf.getProductoClienteVersion();

							Long campanaProductoVersion = ordenMedioDetalleIf
									.getCampanaProductoVersionId(); // ADD 12
																	// OCTUBRE

							// Long comercialId =
							// ordenMedioDetalleIf.getComercialId();
							// if(comercialId.compareTo(comercialIdtmp)==0){
							// COMENTED 5 SEPTIEMBRE
							// if(productoClienteId.compareTo(productoClienteIdtmp)==0){
							// COMENTED 12 OCTUBRE ADD 5 SEPTIEMBRE
							/*
							 * if(productoClienteId.compareTo(productoClienteIdtmp
							 * )==0 && productoClienteVersion.compareTo(
							 * productoClienteVersiontmp) == 0 ){
							 */

							// ADD 12 OCTUBRE
							if (productoClienteId
									.compareTo(productoClienteIdtmp) == 0
									&& campanaProductoVersion
											.compareTo(campanaProductoVersiontmp) == 0
									&& String
											.valueOf(
													ordenMedioIf
															.getNumeroOrdenAgrupacion())
											.equals(numeroOrdenTmp)) {

								ordenesMedioParcial.put(ordenMedioIf,
										ordenesMedioDetalleMap);
								// ADD 31 MAYO
								// posibilidad de que vaya la lista temporal de
								// observaciones
								// END 31 MAYO
							}
						}
					}
					// mapaVersionesOrdenesComp.put(productoClienteVersiontmp,ordenesMedioParcial);
					// COMENTED 12 OCTUBRE

					mapaVersionesOrdenesComp.put(campanaProductoVersiontmp,
							ordenesMedioParcial); // ADD 12 OCTUBRE
				}// ADD 5 SEPTIEMBRE
					// mapaComercialOrdenesComp.put(comercialIdtmp,ordenesMedioParcial);
					// COMENTED 5 SEPTIEMBRE
					// mapaProductoClienteOrdenesCompSaved.put(productoClienteIdtmp,
					// ordenesMedioParcial);
					// ADD 5 SEPTIEMBRE
				mapaProductoClienteVersionesOrdenesCompSaved.put(
						String.valueOf(productoClienteIdtmp) + "-"
								+ numeroOrdenTmp, mapaVersionesOrdenesComp);
			}
			// END 12 OCTUBRE

		}
		// ADD 11 MAYO
		// 6 MAYO
		Map<String, ArrayList<BigDecimal>> proveedorOrdenesSubtotal = new LinkedHashMap<String, ArrayList<BigDecimal>>();
		ArrayList<BigDecimal> ordenesSubtotales = new ArrayList<BigDecimal>();
		// 6 MAYO

		// COMENTED 5 SEPTIEMBRE
		/*
		 * Iterator mapasProductoOrdenesIt =
		 * mapaProductoClienteOrdenesCompSaved.keySet().iterator();
		 * while(mapasProductoOrdenesIt.hasNext()){ Long idproducto =
		 * (Long)mapasProductoOrdenesIt.next(); Map ordenesProveedor =
		 * mapaProductoClienteOrdenesCompSaved.get(idproducto);//* /
		 * 
		 * Iterator mapaOrdenesProveedorIt =
		 * ordenesProveedor.keySet().iterator();
		 * while(mapaOrdenesProveedorIt.hasNext()){ OrdenMedioIf ordenMedio =
		 * (OrdenMedioIf)mapaOrdenesProveedorIt.next(); Long idProveedor =
		 * ordenMedio.getProveedorId();
		 * 
		 * if (proveedorOrdenesSubtotal.get(idProveedor)== null){
		 * ordenesSubtotales = new ArrayList<BigDecimal>();
		 * ordenesSubtotales.add(ordenMedio.getValorSubtotal()); }else{
		 * ordenesSubtotales =
		 * proveedorOrdenesSubtotal.get(idProveedor);//.add(ordenMedio
		 * .getValorSubtotal()); proveedorOrdenesSubtotal.remove(idProveedor);
		 * ordenesSubtotales.add(ordenMedio.getValorSubtotal()); }
		 * 
		 * proveedorOrdenesSubtotal.put(idProveedor, ordenesSubtotales); } }
		 */

		// COMENTED 12 OCTUBRE MODIFIED 5 SEPTIEMBRE
		/*
		 * Iterator mapasProductoVersionesOrdenesIt =
		 * mapaProductoClienteVersionesOrdenesCompSaved.keySet().iterator();
		 * while(mapasProductoVersionesOrdenesIt.hasNext()){ Long idproducto =
		 * (Long)mapasProductoVersionesOrdenesIt.next(); Map
		 * mapVersionOrdenesProveedor =
		 * mapaProductoClienteVersionesOrdenesCompSaved.get(idproducto);//* /
		 * //ADD 5 SEPTIEMBRE Iterator mapVersionOrdenesProveedorIt =
		 * mapVersionOrdenesProveedor.keySet().iterator();
		 * while(mapVersionOrdenesProveedorIt.hasNext()){ String versionProducto
		 * = (String) mapVersionOrdenesProveedorIt.next();
		 * 
		 * Map ordenesProveedor = (Map)
		 * mapVersionOrdenesProveedor.get(versionProducto); Iterator
		 * mapaOrdenesProveedorIt = ordenesProveedor.keySet().iterator();
		 * while(mapaOrdenesProveedorIt.hasNext()){ OrdenMedioIf ordenMedio =
		 * (OrdenMedioIf)mapaOrdenesProveedorIt.next(); Long idProveedor =
		 * ordenMedio.getProveedorId();
		 * 
		 * if (proveedorOrdenesSubtotal.get(idProveedor)== null){
		 * ordenesSubtotales = new ArrayList<BigDecimal>();
		 * ordenesSubtotales.add(ordenMedio.getValorSubtotal()); }else{
		 * ordenesSubtotales =
		 * proveedorOrdenesSubtotal.get(idProveedor);//.add(ordenMedio
		 * .getValorSubtotal()); proveedorOrdenesSubtotal.remove(idProveedor);
		 * ordenesSubtotales.add(ordenMedio.getValorSubtotal()); }
		 * 
		 * proveedorOrdenesSubtotal.put(idProveedor, ordenesSubtotales); }
		 * }//ADD 5 SEPTIEMBRE }
		 */
		// END 5 SEPTIEMBRE

		// MODIFIED 12 OCTUBRE
		Iterator mapasProductoVersionesOrdenesIt = mapaProductoClienteVersionesOrdenesCompSaved
				.keySet().iterator();
		while (mapasProductoVersionesOrdenesIt.hasNext()) {
			String[] key = ((String) mapasProductoVersionesOrdenesIt.next())
					.split("-");
			Long idproducto = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			Map mapVersionOrdenesProveedor = mapaProductoClienteVersionesOrdenesCompSaved
					.get(String.valueOf(idproducto) + "-" + numeroOrden);// * /
			// ADD 5 SEPTIEMBRE
			Iterator mapVersionOrdenesProveedorIt = mapVersionOrdenesProveedor
					.keySet().iterator();
			while (mapVersionOrdenesProveedorIt.hasNext()) {
				Long versionProducto = (Long) mapVersionOrdenesProveedorIt
						.next();

				Map ordenesProveedor = (Map) mapVersionOrdenesProveedor
						.get(versionProducto);
				Iterator mapaOrdenesProveedorIt = ordenesProveedor.keySet()
						.iterator();
				while (mapaOrdenesProveedorIt.hasNext()) {
					OrdenMedioIf ordenMedio = (OrdenMedioIf) mapaOrdenesProveedorIt
							.next();
					Long idProveedor = ordenMedio.getProveedorId();

					if (proveedorOrdenesSubtotal.get(String
							.valueOf(idProveedor)
							+ "-"
							+ String.valueOf(ordenMedio
									.getNumeroOrdenAgrupacion())) == null) {
						ordenesSubtotales = new ArrayList<BigDecimal>();
						ordenesSubtotales.add(ordenMedio.getValorSubtotal());
					} else {
						ordenesSubtotales = proveedorOrdenesSubtotal.get(String
								.valueOf(idProveedor)
								+ "-"
								+ String.valueOf(ordenMedio
										.getNumeroOrdenAgrupacion()));// .add(ordenMedio.getValorSubtotal());
						proveedorOrdenesSubtotal.remove(String
								.valueOf(idProveedor)
								+ "-"
								+ String.valueOf(ordenMedio
										.getNumeroOrdenAgrupacion()));
						ordenesSubtotales.add(ordenMedio.getValorSubtotal());
					}

					proveedorOrdenesSubtotal.put(
							String.valueOf(idProveedor)
									+ "-"
									+ String.valueOf(ordenMedio
											.getNumeroOrdenAgrupacion()),
							ordenesSubtotales);
				}
			}// ADD 5 SEPTIEMBRE
		}
		// END 12 OCTUBRE

		Iterator proveedorSubtotalIt2 = proveedorOrdenesSubtotal.keySet()
				.iterator();
		while (proveedorSubtotalIt2.hasNext()) {
			String[] key = ((String) proveedorSubtotalIt2.next()).split("-");
			Long idProveedorTemp = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<BigDecimal> listica = proveedorOrdenesSubtotal.get(String
					.valueOf(idProveedorTemp) + "-" + numeroOrden);
			BigDecimal subtotalProveedor = new BigDecimal(0);
			for (int i = 0; i < listica.size(); i++) {
				subtotalProveedor = subtotalProveedor.add(listica.get(i));
			}
			// Map<Long,BigDecimal> proveedorValorSubtotal = new HashMap<Long,
			// BigDecimal>();
			proveedorValorSubtotalSaved.put(String.valueOf(idProveedorTemp)
					+ "-" + numeroOrden, subtotalProveedor);
			System.out.println("proveedor: " + idProveedorTemp + "SUBTOTAL  "
					+ subtotalProveedor);
		}

	}

	// END 12 OCTUBRE

	// ADD 13 OCTUBRE ADD 15 JUNIO
	/*
	 * Se crean las ordenes de Medio agrupadas por Productos para actualizar el
	 * Plan de Medio es decir en una Orden de Medio contendran varios
	 * comerciales en su detalle ejm Orden de Medio del Producto: Ecuacolor A
	 * Detalle de Orden de Medio DE LOS COMERCIALES: A CUÑA, A MENCION, A
	 * PRESENTACION, A DESPEDIDA LOS MAPAS DE ORDENES DE MEDIO SERAN SEGUN
	 * PRODUCTO CLIENTE
	 */
	// private void crearOrdenesMedioAgrupadasForUpdate() throws
	// GenericBusinessException {COMENTED 13 OCTUBRE
	private void crearOrdenesMedioAgrupadasXProductoClienteForUpdate()
			throws GenericBusinessException {

		// MODIFIED 15 JUNIO
		// GIOMY REVISAR QUE SUCEDE CON EL PLAN MEDIO SAVE
		// planMedioIf.setEstado(ESTADO_APROBADO);

		// COMENTED 27 JUNIO
		// planMedioIfSaved.setEstado(ESTADO_APROBADO);
		Collection<OrdenMedioIf> ordenMedioColl = SessionServiceLocator
				.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(
						planMedioIfSaved.getId());

		if (ordenMedioColl.size() > 0) {// 15 JUNIO && !nuevoPlan){
			// listaProveedoresMap->Map<Long,ClienteOficinaIf>
			// listaProveedoresMap = new HashMap<Long,ClienteOficinaIf>();
			listaProveedoresMapSaved.clear();
			for (PlanMedioDetalleIf planMedioDetalle : planMedioDetallesPlantillaSaved) {
				ClienteOficinaIf proveedor = mapaClienteOficina
						.get(planMedioDetalle.getProveedorId());
				listaProveedoresMapSaved
						.put(String.valueOf(proveedor.getId())
								+ "-"
								+ String.valueOf(planMedioDetalle
										.getNumeroOrdenAgrupacion()), proveedor);
			}

			// ADD 11 MAYO
			ArrayList<String> listaProductosCliente = new ArrayList<String>();

			// ArrayList<Long>listaComerciales = new ArrayList<Long>();

			Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioTotales = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
			Iterator ordenMedioIt = ordenMedioColl.iterator();
			while (ordenMedioIt.hasNext()) {
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt.next();

				// ADD 11 MAYO
				Long productoClienteId = ordenMedioIf.getProductoClienteId();
				String numeroOrden = String.valueOf(ordenMedioIf
						.getNumeroOrdenAgrupacion());
				int tmp = 0;
				for (String productoClienteTmp : listaProductosCliente) {
					String[] key = productoClienteTmp.split("-");
					Long productoClienteIdtmp = Long.parseLong(key[0]);
					String numeroOrdenTmp = key[1];
					if (productoClienteIdtmp.compareTo(productoClienteId) != 0
							|| (productoClienteIdtmp
									.compareTo(productoClienteId) == 0 && !numeroOrdenTmp
									.endsWith(numeroOrden))) {
						tmp++;
					}
				}

				if (tmp == listaProductosCliente.size()) {
					listaProductosCliente.add(String.valueOf(productoClienteId)
							+ "-" + numeroOrden);
				}
				// END 11 MAYO

				Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleTotales = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();

				Collection<OrdenMedioDetalleIf> ordenMedioDetalleColl = SessionServiceLocator
						.getOrdenMedioDetalleSessionService()
						.findOrdenMedioDetalleByOrdenMedioId(
								ordenMedioIf.getId());
				Iterator ordenMedioDetalleIt = ordenMedioDetalleColl.iterator();
				while (ordenMedioDetalleIt.hasNext()) {
					OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt
							.next();

					ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = new ArrayList<OrdenMedioDetalleMapaIf>();
					Collection<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaColl = SessionServiceLocator
							.getOrdenMedioDetalleMapaSessionService()
							.findOrdenMedioDetalleMapaByOrdenMedioDetalleId(
									ordenMedioDetalleIf.getId());
					Iterator ordenMedioDetalleMapaIt = ordenMedioDetalleMapaColl
							.iterator();
					while (ordenMedioDetalleMapaIt.hasNext()) {
						OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf = (OrdenMedioDetalleMapaIf) ordenMedioDetalleMapaIt
								.next();
						ordenMedioDetalleMapaList.add(ordenMedioDetalleMapaIf);
					}
					ordenesMedioDetalleTotales.put(ordenMedioDetalleIf,
							ordenMedioDetalleMapaList);
				}
				ordenesMedioTotales.put(ordenMedioIf,
						ordenesMedioDetalleTotales);
			}
			// for(Long comercialIdtmp : listaComerciales){
			for (String productoClienteTmp : listaProductosCliente) {
				String key[] = productoClienteTmp.split("-");
				Long productoClienteIdtmp = Long.parseLong(key[0]);
				String numeroOrdenTmp = key[1];
				Iterator ordenMedioIt2 = ordenesMedioTotales.keySet()
						.iterator();
				Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioParcial = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
				while (ordenMedioIt2.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt2
							.next();
					// ADD 11 MAYO
					Long productoClienteId = ordenMedioIf
							.getProductoClienteId();
					String numeroOrden = String.valueOf(ordenMedioIf
							.getNumeroOrdenAgrupacion());
					Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleMap = ordenesMedioTotales
							.get(ordenMedioIf);
					Iterator ordenMedioDetalleIt = ordenesMedioDetalleMap
							.keySet().iterator();
					if (ordenMedioDetalleIt.hasNext()) {
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt
								.next();
						// Long comercialId =
						// ordenMedioDetalleIf.getComercialId();
						if (productoClienteId.compareTo(productoClienteIdtmp) == 0
								&& numeroOrden.equals(numeroOrdenTmp)) {
							// if(comercialId.compareTo(comercialIdtmp)==0){
							ordenesMedioParcial.put(ordenMedioIf,
									ordenesMedioDetalleMap);
							// ADD 31 MAYO
							// posibilidad de que vaya la lista temporal de
							// observaciones
							// END 31 MAYO
						}
					}
				}
				// mapaComercialOrdenesComp.put(comercialIdtmp,ordenesMedioParcial);
				mapaProductoClienteOrdenesCompSaved.put(
						String.valueOf(productoClienteIdtmp) + "-"
								+ numeroOrdenTmp, ordenesMedioParcial);
			}
		}
		// ADD 11 MAYO
		// 6 MAYO
		Map<String, ArrayList<BigDecimal>> proveedorOrdenesSubtotal = new HashMap<String, ArrayList<BigDecimal>>();
		ArrayList<BigDecimal> ordenesSubtotales = new ArrayList<BigDecimal>();
		// 6 MAYO
		Iterator mapasProductoOrdenesIt = mapaProductoClienteOrdenesCompSaved
				.keySet().iterator();
		while (mapasProductoOrdenesIt.hasNext()) {
			String[] key = ((String) mapasProductoOrdenesIt.next()).split("-");
			Long idproducto = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			Map ordenesProveedor = mapaProductoClienteOrdenesCompSaved
					.get(String.valueOf(idproducto) + "-" + numeroOrden);// * /

			Iterator mapaOrdenesProveedorIt = ordenesProveedor.keySet()
					.iterator();
			while (mapaOrdenesProveedorIt.hasNext()) {
				OrdenMedioIf ordenMedio = (OrdenMedioIf) mapaOrdenesProveedorIt
						.next();
				Long idProveedor = ordenMedio.getProveedorId();
				if (proveedorOrdenesSubtotal
						.get(String.valueOf(idProveedor)
								+ "-"
								+ String.valueOf(ordenMedio
										.getNumeroOrdenAgrupacion())) == null) {
					ordenesSubtotales = new ArrayList<BigDecimal>();
					ordenesSubtotales.add(ordenMedio.getValorSubtotal());
				} else {
					ordenesSubtotales = proveedorOrdenesSubtotal.get(String
							.valueOf(idProveedor)
							+ "-"
							+ String.valueOf(ordenMedio
									.getNumeroOrdenAgrupacion()));// .add(ordenMedio.getValorSubtotal());
					proveedorOrdenesSubtotal.remove(String.valueOf(idProveedor)
							+ "-"
							+ String.valueOf(ordenMedio
									.getNumeroOrdenAgrupacion()));
					ordenesSubtotales.add(ordenMedio.getValorSubtotal());
				}

				proveedorOrdenesSubtotal.put(String.valueOf(idProveedor) + "-"
						+ numeroOrden, ordenesSubtotales);
			}
		}

		Iterator proveedorSubtotalIt2 = proveedorOrdenesSubtotal.keySet()
				.iterator();
		while (proveedorSubtotalIt2.hasNext()) {
			String[] key = ((String) proveedorSubtotalIt2.next()).split("-");
			Long idProveedorTemp = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<BigDecimal> listica = proveedorOrdenesSubtotal.get(String
					.valueOf(idProveedorTemp) + "-" + numeroOrden);
			BigDecimal subtotalProveedor = new BigDecimal(0);
			for (int i = 0; i < listica.size(); i++) {
				subtotalProveedor = subtotalProveedor.add(listica.get(i));
			}
			// Map<Long,BigDecimal> proveedorValorSubtotal = new HashMap<Long,
			// BigDecimal>();
			proveedorValorSubtotalSaved.put(String.valueOf(idProveedorTemp)
					+ "-" + numeroOrden, subtotalProveedor);
			System.out.println("proveedor: " + idProveedorTemp + "SUBTOTAL  "
					+ subtotalProveedor);
		}

	}

	// END 13 OCTUBRE END 15 JUNIO

	// MODIFIED 5 SEPTIEMBRE ADD 15 JUNIO
	/*
	 * Se crean las ordenes de Medio agrupadas por Productos y Versiones del
	 * Producto para actualizar el Plan de Medio es decir en una Orden de Medio
	 * contendran varios comerciales en su detalle ejm Orden de Medio segun
	 * versiones: Ecuacolor A, Ecuacolor X Detalle de Orden de Medio DE LOS
	 * COMERCIALES: A CUÑA, A MENCION, A PRESENTACION, A DESPEDIDA LOS MAPAS DE
	 * ORDENES DE MEDIO SERAN SEGUN VERSION DE UN PRODUCTO CLIENTE
	 */
	/*
	 * private void crearOrdenesMedioAgrupadasForUpdate() throws
	 * GenericBusinessException { //MODIFIED 15 JUNIO //GIOMY REVISAR QUE SUCEDE
	 * CON EL PLAN MEDIO SAVE //planMedioIf.setEstado(ESTADO_APROBADO);
	 * 
	 * //COMENTED 27 JUNIO //planMedioIfSaved.setEstado(ESTADO_APROBADO);
	 * Collection<OrdenMedioIf>ordenMedioColl =
	 * SessionServiceLocator.getOrdenMedioSessionService
	 * ().findOrdenMedioByPlanMedioId(planMedioIfSaved.getId());
	 * 
	 * if(ordenMedioColl.size() > 0){//15 JUNIO && !nuevoPlan){
	 * //listaProveedoresMap->Map<Long,ClienteOficinaIf> listaProveedoresMap =
	 * new HashMap<Long,ClienteOficinaIf>(); listaProveedoresMapSaved.clear();
	 * for(PlanMedioDetalleIf planMedioDetalle :
	 * planMedioDetallesPlantillaSaved){ ClienteOficinaIf proveedor =
	 * SessionServiceLocator
	 * .getClienteOficinaSessionService().getClienteOficina(
	 * planMedioDetalle.getProveedorId());
	 * listaProveedoresMapSaved.put(proveedor.getId(),proveedor); }
	 * 
	 * //ArrayList<Long>listaComerciales = new ArrayList<Long>(); //ADD 11 MAYO
	 * //ArrayList<Long>listaProductosCliente = new ArrayList<Long>(); COMENTED
	 * 5 SEPTIEMBRE //ADD 5 SEPTIEMBRE Map<Long,ArrayList<String>>
	 * mapProductoClienteListVersiones = new LinkedHashMap<Long,
	 * ArrayList<String>>(); ArrayList<String>listaProductosCliente;
	 * 
	 * Map <OrdenMedioIf,Map
	 * <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>
	 * ordenesMedioTotales = new LinkedHashMap<OrdenMedioIf,
	 * Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>(); Iterator
	 * ordenMedioIt = ordenMedioColl.iterator(); while(ordenMedioIt.hasNext()){
	 * OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenMedioIt.next();
	 * 
	 * //COMENETED 5 SEPTIEMBRE ADD 11 MAYO /*Long productoClienteId =
	 * ordenMedioIf.getProductoClienteId(); int tmp = 0; for(Long
	 * productoClienteIdtmp : listaProductosCliente){
	 * if(productoClienteIdtmp.compareTo(productoClienteId)!=0){ tmp++; } }
	 * 
	 * if(tmp==listaProductosCliente.size()){
	 * listaProductosCliente.add(productoClienteId); }* / //END 11 MAYO
	 * 
	 * //MODIFIED 5 SEPTIEMBRE Long productoClienteId =
	 * ordenMedioIf.getProductoClienteId(); String productoClienteVersion = "";
	 * //ADD 5 SEPTIEMBRE
	 * Collection<OrdenMedioDetalleIf>ordenMedioDetalleVersionColl =
	 * SessionServiceLocator
	 * .getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId
	 * (ordenMedioIf.getId()); if (!ordenMedioDetalleVersionColl.isEmpty())
	 * productoClienteVersion =
	 * ordenMedioDetalleVersionColl.iterator().next().getProductoClienteVersion
	 * (); //END 5 SEPTIEMBRE
	 * if(mapProductoClienteListVersiones.containsKey(productoClienteId)){
	 * listaProductosCliente =
	 * mapProductoClienteListVersiones.get(productoClienteId); if
	 * (!listaProductosCliente.contains(productoClienteVersion)){
	 * listaProductosCliente.add(productoClienteVersion); } }else{
	 * listaProductosCliente = new ArrayList<String>();
	 * listaProductosCliente.add(productoClienteVersion);
	 * mapProductoClienteListVersiones.put(productoClienteId,
	 * listaProductosCliente); } //END MODIFIED 5 SEPTIEMBRE
	 * 
	 * Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>
	 * ordenesMedioDetalleTotales = new LinkedHashMap<OrdenMedioDetalleIf,
	 * ArrayList<OrdenMedioDetalleMapaIf>>();
	 * 
	 * Collection<OrdenMedioDetalleIf>ordenMedioDetalleColl =
	 * SessionServiceLocator
	 * .getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId
	 * (ordenMedioIf.getId()); Iterator ordenMedioDetalleIt =
	 * ordenMedioDetalleColl.iterator(); while(ordenMedioDetalleIt.hasNext()){
	 * OrdenMedioDetalleIf ordenMedioDetalleIf =
	 * (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
	 * 
	 * ArrayList<OrdenMedioDetalleMapaIf>ordenMedioDetalleMapaList = new
	 * ArrayList<OrdenMedioDetalleMapaIf>(); Collection<OrdenMedioDetalleMapaIf>
	 * ordenMedioDetalleMapaColl =
	 * SessionServiceLocator.getOrdenMedioDetalleMapaSessionService
	 * ().findOrdenMedioDetalleMapaByOrdenMedioDetalleId
	 * (ordenMedioDetalleIf.getId()); Iterator ordenMedioDetalleMapaIt =
	 * ordenMedioDetalleMapaColl.iterator();
	 * while(ordenMedioDetalleMapaIt.hasNext()){ OrdenMedioDetalleMapaIf
	 * ordenMedioDetalleMapaIf =
	 * (OrdenMedioDetalleMapaIf)ordenMedioDetalleMapaIt.next();
	 * ordenMedioDetalleMapaList.add(ordenMedioDetalleMapaIf); }
	 * ordenesMedioDetalleTotales
	 * .put(ordenMedioDetalleIf,ordenMedioDetalleMapaList); }
	 * ordenesMedioTotales.put(ordenMedioIf,ordenesMedioDetalleTotales); }
	 * 
	 * //for(Long comercialIdtmp : listaComerciales){ //COMENTED 5 SEPTIEMBRE
	 * //for(Long productoClienteIdtmp : listaProductosCliente){ //MODIFIED 5
	 * SEPTIEMBRE for(Long productoClienteIdtmp :
	 * mapProductoClienteListVersiones.keySet()){ //ADD 5 SEPTIEMBRE
	 * Map<String,Map <OrdenMedioIf,Map<OrdenMedioDetalleIf,
	 * ArrayList<OrdenMedioDetalleMapaIf>>>> mapaVersionesOrdenesComp = new
	 * LinkedHashMap<String,
	 * Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf
	 * >>>>(); ArrayList<String> listProductoClienteVersiontmp =
	 * mapProductoClienteListVersiones.get(productoClienteIdtmp); for (String
	 * productoClienteVersiontmp : listProductoClienteVersiontmp){
	 * 
	 * Iterator ordenMedioIt2 = ordenesMedioTotales.keySet().iterator(); Map
	 * <OrdenMedioIf,Map
	 * <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>
	 * ordenesMedioParcial = new LinkedHashMap<OrdenMedioIf,
	 * Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
	 * while(ordenMedioIt2.hasNext()){ OrdenMedioIf ordenMedioIf =
	 * (OrdenMedioIf)ordenMedioIt2.next(); //ADD 11 MAYO Long productoClienteId
	 * = ordenMedioIf.getProductoClienteId(); Map
	 * <OrdenMedioDetalleIf,ArrayList<
	 * OrdenMedioDetalleMapaIf>>ordenesMedioDetalleMap=
	 * ordenesMedioTotales.get(ordenMedioIf); Iterator ordenMedioDetalleIt =
	 * ordenesMedioDetalleMap.keySet().iterator();
	 * if(ordenMedioDetalleIt.hasNext()){ OrdenMedioDetalleIf
	 * ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
	 * //ADD 5 SEPTIEMBRE String productoClienteVersion =
	 * ordenMedioDetalleIf.getProductoClienteVersion(); //Long comercialId =
	 * ordenMedioDetalleIf.getComercialId();
	 * //if(comercialId.compareTo(comercialIdtmp)==0){ //COMENTED 5 SEPTIEMBRE
	 * //if(productoClienteId.compareTo(productoClienteIdtmp)==0){ //ADD 5
	 * SEPTIEMBRE if(productoClienteId.compareTo(productoClienteIdtmp)==0 &&
	 * productoClienteVersion.compareTo(productoClienteVersiontmp) == 0 ){
	 * ordenesMedioParcial.put(ordenMedioIf, ordenesMedioDetalleMap); //ADD 31
	 * MAYO //posibilidad de que vaya la lista temporal de observaciones //END
	 * 31 MAYO } } }
	 * mapaVersionesOrdenesComp.put(productoClienteVersiontmp,ordenesMedioParcial
	 * ); }//ADD 5 SEPTIEMBRE
	 * //mapaComercialOrdenesComp.put(comercialIdtmp,ordenesMedioParcial);
	 * //COMENTED 5 SEPTIEMBRE
	 * //mapaProductoClienteOrdenesCompSaved.put(productoClienteIdtmp,
	 * ordenesMedioParcial); //ADD 5 SEPTIEMBRE
	 * mapaProductoClienteVersionesOrdenesCompSaved
	 * .put(productoClienteIdtmp,mapaVersionesOrdenesComp); } } //ADD 11 MAYO
	 * //6 MAYO Map<Long, ArrayList<BigDecimal>>proveedorOrdenesSubtotal = new
	 * LinkedHashMap<Long, ArrayList<BigDecimal>>(); ArrayList<BigDecimal>
	 * ordenesSubtotales = new ArrayList<BigDecimal>(); //6 MAYO
	 * 
	 * //COMENTED 5 SEPTIEMBRE /*Iterator mapasProductoOrdenesIt =
	 * mapaProductoClienteOrdenesCompSaved.keySet().iterator();
	 * while(mapasProductoOrdenesIt.hasNext()){ Long idproducto =
	 * (Long)mapasProductoOrdenesIt.next(); Map ordenesProveedor =
	 * mapaProductoClienteOrdenesCompSaved.get(idproducto);//* /
	 * 
	 * Iterator mapaOrdenesProveedorIt = ordenesProveedor.keySet().iterator();
	 * while(mapaOrdenesProveedorIt.hasNext()){ OrdenMedioIf ordenMedio =
	 * (OrdenMedioIf)mapaOrdenesProveedorIt.next(); Long idProveedor =
	 * ordenMedio.getProveedorId();
	 * 
	 * if (proveedorOrdenesSubtotal.get(idProveedor)== null){ ordenesSubtotales
	 * = new ArrayList<BigDecimal>();
	 * ordenesSubtotales.add(ordenMedio.getValorSubtotal()); }else{
	 * ordenesSubtotales =
	 * proveedorOrdenesSubtotal.get(idProveedor);//.add(ordenMedio
	 * .getValorSubtotal()); proveedorOrdenesSubtotal.remove(idProveedor);
	 * ordenesSubtotales.add(ordenMedio.getValorSubtotal()); }
	 * 
	 * proveedorOrdenesSubtotal.put(idProveedor, ordenesSubtotales); } }* /
	 * 
	 * //MODIFIED 5 SEPTIEMBRE Iterator mapasProductoVersionesOrdenesIt =
	 * mapaProductoClienteVersionesOrdenesCompSaved.keySet().iterator();
	 * while(mapasProductoVersionesOrdenesIt.hasNext()){ Long idproducto =
	 * (Long)mapasProductoVersionesOrdenesIt.next(); Map
	 * mapVersionOrdenesProveedor =
	 * mapaProductoClienteVersionesOrdenesCompSaved.get(idproducto);//* / //ADD
	 * 5 SEPTIEMBRE Iterator mapVersionOrdenesProveedorIt =
	 * mapVersionOrdenesProveedor.keySet().iterator();
	 * while(mapVersionOrdenesProveedorIt.hasNext()){ String versionProducto =
	 * (String) mapVersionOrdenesProveedorIt.next();
	 * 
	 * Map ordenesProveedor = (Map)
	 * mapVersionOrdenesProveedor.get(versionProducto); Iterator
	 * mapaOrdenesProveedorIt = ordenesProveedor.keySet().iterator();
	 * while(mapaOrdenesProveedorIt.hasNext()){ OrdenMedioIf ordenMedio =
	 * (OrdenMedioIf)mapaOrdenesProveedorIt.next(); Long idProveedor =
	 * ordenMedio.getProveedorId();
	 * 
	 * if (proveedorOrdenesSubtotal.get(idProveedor)== null){ ordenesSubtotales
	 * = new ArrayList<BigDecimal>();
	 * ordenesSubtotales.add(ordenMedio.getValorSubtotal()); }else{
	 * ordenesSubtotales =
	 * proveedorOrdenesSubtotal.get(idProveedor);//.add(ordenMedio
	 * .getValorSubtotal()); proveedorOrdenesSubtotal.remove(idProveedor);
	 * ordenesSubtotales.add(ordenMedio.getValorSubtotal()); }
	 * 
	 * proveedorOrdenesSubtotal.put(idProveedor, ordenesSubtotales); } }//ADD 5
	 * SEPTIEMBRE } //END 5 SEPTIEMBRE
	 * 
	 * Iterator proveedorSubtotalIt2 =
	 * proveedorOrdenesSubtotal.keySet().iterator();
	 * while(proveedorSubtotalIt2.hasNext()){ Long idProveedorTemp =
	 * (Long)proveedorSubtotalIt2.next(); ArrayList<BigDecimal> listica =
	 * proveedorOrdenesSubtotal.get(idProveedorTemp); BigDecimal
	 * subtotalProveedor = new BigDecimal(0); for (int i = 0; i< listica.size();
	 * i++){ subtotalProveedor = subtotalProveedor.add(listica.get(i)); }
	 * //Map<Long,BigDecimal> proveedorValorSubtotal = new HashMap<Long,
	 * BigDecimal>();
	 * proveedorValorSubtotalSaved.put(idProveedorTemp,subtotalProveedor);
	 * System.out.println("proveedor: " + idProveedorTemp+ "SUBTOTAL  "
	 * +subtotalProveedor); }
	 * 
	 * }
	 */
	// END 5 SEPTIEMBRE END 15 JUNIO

	/*
	 * COMENTED 5 SEPTIEMBRE //ADD 15 JUNIO Se crean las ordenes de Medio
	 * agrupadas por Productos para actualizar el Plan de Medio es decir en una
	 * Orden de Medio contendran varios comerciales en su detalle ejm Orden de
	 * Medio del Producto: Ecuacolor A Detalle de Orden de Medio DE LOS
	 * COMERCIALES: A CUÑA, A MENCION, A PRESENTACION, A DESPEDIDA LOS MAPAS DE
	 * ORDENES DE MEDIO SERAN SEGUN PRODUCTO CLIENTE
	 * 
	 * private void crearOrdenesMedioAgrupadasForUpdate() throws
	 * GenericBusinessException { //MODIFIED 15 JUNIO //GIOMY REVISAR QUE SUCEDE
	 * CON EL PLAN MEDIO SAVE //planMedioIf.setEstado(ESTADO_APROBADO);
	 * 
	 * //COMENTED 27 JUNIO //planMedioIfSaved.setEstado(ESTADO_APROBADO);
	 * Collection<OrdenMedioIf>ordenMedioColl =
	 * SessionServiceLocator.getOrdenMedioSessionService
	 * ().findOrdenMedioByPlanMedioId(planMedioIfSaved.getId());
	 * 
	 * if(ordenMedioColl.size() > 0){//15 JUNIO && !nuevoPlan){
	 * //listaProveedoresMap->Map<Long,ClienteOficinaIf> listaProveedoresMap =
	 * new HashMap<Long,ClienteOficinaIf>(); listaProveedoresMapSaved.clear();
	 * for(PlanMedioDetalleIf planMedioDetalle :
	 * planMedioDetallesPlantillaSaved){ ClienteOficinaIf proveedor =
	 * SessionServiceLocator
	 * .getClienteOficinaSessionService().getClienteOficina(
	 * planMedioDetalle.getProveedorId());
	 * listaProveedoresMapSaved.put(proveedor.getId(),proveedor); }
	 * 
	 * //ADD 11 MAYO ArrayList<Long>listaProductosCliente = new
	 * ArrayList<Long>();
	 * 
	 * //ArrayList<Long>listaComerciales = new ArrayList<Long>();
	 * 
	 * Map <OrdenMedioIf,Map
	 * <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>
	 * ordenesMedioTotales = new LinkedHashMap<OrdenMedioIf,
	 * Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>(); Iterator
	 * ordenMedioIt = ordenMedioColl.iterator(); while(ordenMedioIt.hasNext()){
	 * OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenMedioIt.next();
	 * 
	 * //ADD 11 MAYO Long productoClienteId =
	 * ordenMedioIf.getProductoClienteId(); int tmp = 0; for(Long
	 * productoClienteIdtmp : listaProductosCliente){
	 * if(productoClienteIdtmp.compareTo(productoClienteId)!=0){ tmp++; } }
	 * 
	 * if(tmp==listaProductosCliente.size()){
	 * listaProductosCliente.add(productoClienteId); } //END 11 MAYO
	 * 
	 * Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>
	 * ordenesMedioDetalleTotales = new LinkedHashMap<OrdenMedioDetalleIf,
	 * ArrayList<OrdenMedioDetalleMapaIf>>();
	 * 
	 * Collection<OrdenMedioDetalleIf>ordenMedioDetalleColl =
	 * SessionServiceLocator
	 * .getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId
	 * (ordenMedioIf.getId()); Iterator ordenMedioDetalleIt =
	 * ordenMedioDetalleColl.iterator(); while(ordenMedioDetalleIt.hasNext()){
	 * OrdenMedioDetalleIf ordenMedioDetalleIf =
	 * (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
	 * 
	 * ArrayList<OrdenMedioDetalleMapaIf>ordenMedioDetalleMapaList = new
	 * ArrayList<OrdenMedioDetalleMapaIf>(); Collection<OrdenMedioDetalleMapaIf>
	 * ordenMedioDetalleMapaColl =
	 * SessionServiceLocator.getOrdenMedioDetalleMapaSessionService
	 * ().findOrdenMedioDetalleMapaByOrdenMedioDetalleId
	 * (ordenMedioDetalleIf.getId()); Iterator ordenMedioDetalleMapaIt =
	 * ordenMedioDetalleMapaColl.iterator();
	 * while(ordenMedioDetalleMapaIt.hasNext()){ OrdenMedioDetalleMapaIf
	 * ordenMedioDetalleMapaIf =
	 * (OrdenMedioDetalleMapaIf)ordenMedioDetalleMapaIt.next();
	 * ordenMedioDetalleMapaList.add(ordenMedioDetalleMapaIf); }
	 * ordenesMedioDetalleTotales
	 * .put(ordenMedioDetalleIf,ordenMedioDetalleMapaList); }
	 * ordenesMedioTotales.put(ordenMedioIf,ordenesMedioDetalleTotales); }
	 * //for(Long comercialIdtmp : listaComerciales){ for(Long
	 * productoClienteIdtmp : listaProductosCliente){ Iterator ordenMedioIt2 =
	 * ordenesMedioTotales.keySet().iterator(); Map <OrdenMedioIf,Map
	 * <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>
	 * ordenesMedioParcial = new LinkedHashMap<OrdenMedioIf,
	 * Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
	 * while(ordenMedioIt2.hasNext()){ OrdenMedioIf ordenMedioIf =
	 * (OrdenMedioIf)ordenMedioIt2.next(); //ADD 11 MAYO Long productoClienteId
	 * = ordenMedioIf.getProductoClienteId(); Map
	 * <OrdenMedioDetalleIf,ArrayList<
	 * OrdenMedioDetalleMapaIf>>ordenesMedioDetalleMap=
	 * ordenesMedioTotales.get(ordenMedioIf); Iterator ordenMedioDetalleIt =
	 * ordenesMedioDetalleMap.keySet().iterator();
	 * if(ordenMedioDetalleIt.hasNext()){ OrdenMedioDetalleIf
	 * ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
	 * //Long comercialId = ordenMedioDetalleIf.getComercialId();
	 * if(productoClienteId.compareTo(productoClienteIdtmp)==0){
	 * //if(comercialId.compareTo(comercialIdtmp)==0){
	 * ordenesMedioParcial.put(ordenMedioIf, ordenesMedioDetalleMap); //ADD 31
	 * MAYO //posibilidad de que vaya la lista temporal de observaciones //END
	 * 31 MAYO } } }
	 * //mapaComercialOrdenesComp.put(comercialIdtmp,ordenesMedioParcial);
	 * mapaProductoClienteOrdenesCompSaved.put(productoClienteIdtmp,
	 * ordenesMedioParcial); } } //ADD 11 MAYO //6 MAYO Map<Long,
	 * ArrayList<BigDecimal>>proveedorOrdenesSubtotal = new LinkedHashMap<Long,
	 * ArrayList<BigDecimal>>(); ArrayList<BigDecimal> ordenesSubtotales = new
	 * ArrayList<BigDecimal>(); //6 MAYO
	 * 
	 * Iterator mapasProductoOrdenesIt =
	 * mapaProductoClienteOrdenesCompSaved.keySet().iterator();
	 * while(mapasProductoOrdenesIt.hasNext()){ Long idproducto =
	 * (Long)mapasProductoOrdenesIt.next(); Map ordenesProveedor =
	 * mapaProductoClienteOrdenesCompSaved.get(idproducto);//* /
	 * 
	 * Iterator mapaOrdenesProveedorIt = ordenesProveedor.keySet().iterator();
	 * while(mapaOrdenesProveedorIt.hasNext()){ OrdenMedioIf ordenMedio =
	 * (OrdenMedioIf)mapaOrdenesProveedorIt.next(); Long idProveedor =
	 * ordenMedio.getProveedorId();
	 * 
	 * if (proveedorOrdenesSubtotal.get(idProveedor)== null){ ordenesSubtotales
	 * = new ArrayList<BigDecimal>();
	 * ordenesSubtotales.add(ordenMedio.getValorSubtotal()); }else{
	 * ordenesSubtotales =
	 * proveedorOrdenesSubtotal.get(idProveedor);//.add(ordenMedio
	 * .getValorSubtotal()); proveedorOrdenesSubtotal.remove(idProveedor);
	 * ordenesSubtotales.add(ordenMedio.getValorSubtotal()); }
	 * 
	 * proveedorOrdenesSubtotal.put(idProveedor, ordenesSubtotales); } }
	 * 
	 * Iterator proveedorSubtotalIt2 =
	 * proveedorOrdenesSubtotal.keySet().iterator();
	 * while(proveedorSubtotalIt2.hasNext()){ Long idProveedorTemp =
	 * (Long)proveedorSubtotalIt2.next(); ArrayList<BigDecimal> listica =
	 * proveedorOrdenesSubtotal.get(idProveedorTemp); BigDecimal
	 * subtotalProveedor = new BigDecimal(0); for (int i = 0; i< listica.size();
	 * i++){ subtotalProveedor = subtotalProveedor.add(listica.get(i)); }
	 * //Map<Long,BigDecimal> proveedorValorSubtotal = new HashMap<Long,
	 * BigDecimal>();
	 * proveedorValorSubtotalSaved.put(idProveedorTemp,subtotalProveedor);
	 * System.out.println("proveedor: " + idProveedorTemp+ "SUBTOTAL  "
	 * +subtotalProveedor); }
	 * 
	 * } //END 15 JUNIO
	 */

	// MODIFIED 5 SEPTIEMBRE 28 JUNIO
	public Map<String, String> getCodigoEstadoOrdenMedioAgrupadaXCanalSaved(
			Long idProveedor, int numeroOrden) {
		String codigo = "";
		Map mapCodigoEstado = null;
		try {
			// System.out.println("SIZE CANAL"+mapCodigoOrdenMedioByProveedorSaved.size());
			// Iterator mapProductoByProveedorCodigoOrdenSavedTempIt =
			// mapProductoClienteByProveedorCodigoOrdenMedioSaved.keySet().iterator();
			// MODIFIED 27 JUNIO
			// Iterator mapCodigoOrdenMedioByProveedorSavedTempIt =
			// mapCodigoOrdenMedioByProveedorSaved.keySet().iterator();
			Iterator mapCodigoEstadoOrdenMedioByProveedorSavedTempIt = mapCodigoEstadoOrdenMedioByProveedorSaved
					.keySet().iterator();

			// while(mapProductoByProveedorCodigoOrdenSavedTempIt.hasNext()){
			while (mapCodigoEstadoOrdenMedioByProveedorSavedTempIt.hasNext()) {
				// MODIFIED 27 JUNIO
				// Long idProveedorMapa =
				// (Long)mapCodigoEstadoOrdenMedioByProveedorSavedTempIt.hasNext.next();
				// ArrayList<String> listCodigo =
				// mapCodigoOrdenMedioByProveedorSaved.get(idProveedorMapa);
				String[] key = ((String) mapCodigoEstadoOrdenMedioByProveedorSavedTempIt
						.next()).split("-");
				Long idProveedorMapa = Long.parseLong(key[0]);
				String numeroOrdenMapa = key[1];
				// MODIFIED 27 JUNIO
				// ArrayList<String> listCodigo =
				// mapCodigoEstadoOrdenMedioByProveedorSaved.get(idProveedorMapa);
				/*
				 * for (String codigoTemp: listCodigo){ //if
				 * (mapaProductoCodigo.get(idProducto) != null){
				 * System.out.println("ENTRA AKI CANAL: " ); codigo =
				 * codigoTemp;//(String)mapaProductoCodigo.get(idProducto);
				 * System.out.println("codigo CANAL: "+ codigo); //} }
				 */
				if (idProveedorMapa.compareTo(idProveedor) == 0
						&& numeroOrdenMapa.equals(String.valueOf(numeroOrden))) {
					ArrayList<Map<String, String>> listCodigoEstado = mapCodigoEstadoOrdenMedioByProveedorSaved
							.get(String.valueOf(idProveedorMapa) + "-"
									+ numeroOrdenMapa);
					for (Map mapaCodigoEstado : listCodigoEstado) {
						// codigo =
						// (String)mapaProductoCodigo.keySet().iterator().next();
						// System.out.println("codigo CANAL: "+ codigo);
						mapCodigoEstado = new HashMap();
						mapCodigoEstado = mapaCodigoEstado;
					}
				}
			}
			return mapCodigoEstado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapCodigoEstado;
	}

	// END MODIFIED 5 SEPTIEMBRE

	/*
	 * COMENTED 5 SEPTIEMBRE MODIFIED 28 JUNIO public Map<String,String>
	 * getCodigoEstadoOrdenMedioAgrupadaXCanalSaved(Long idProveedor){ String
	 * codigo =""; Map mapCodigoEstado = null; try{
	 * //System.out.println("SIZE CANAL"
	 * +mapCodigoOrdenMedioByProveedorSaved.size()); //Iterator
	 * mapProductoByProveedorCodigoOrdenSavedTempIt =
	 * mapProductoClienteByProveedorCodigoOrdenMedioSaved.keySet().iterator();
	 * //MODIFIED 27 JUNIO //Iterator mapCodigoOrdenMedioByProveedorSavedTempIt
	 * = mapCodigoOrdenMedioByProveedorSaved.keySet().iterator(); Iterator
	 * mapCodigoEstadoOrdenMedioByProveedorSavedTempIt =
	 * mapCodigoEstadoOrdenMedioByProveedorSaved.keySet().iterator();
	 * 
	 * //while(mapProductoByProveedorCodigoOrdenSavedTempIt.hasNext()){
	 * while(mapCodigoEstadoOrdenMedioByProveedorSavedTempIt.hasNext()){
	 * //MODIFIED 27 JUNIO //Long idProveedorMapa =
	 * (Long)mapCodigoEstadoOrdenMedioByProveedorSavedTempIt.hasNext.next();
	 * //ArrayList<String> listCodigo =
	 * mapCodigoOrdenMedioByProveedorSaved.get(idProveedorMapa); Long
	 * idProveedorMapa =
	 * (Long)mapCodigoEstadoOrdenMedioByProveedorSavedTempIt.next(); //MODIFIED
	 * 27 JUNIO //ArrayList<String> listCodigo =
	 * mapCodigoEstadoOrdenMedioByProveedorSaved.get(idProveedorMapa); for
	 * (String codigoTemp: listCodigo){ //if (mapaProductoCodigo.get(idProducto)
	 * != null){ System.out.println("ENTRA AKI CANAL: " ); codigo =
	 * codigoTemp;//(String)mapaProductoCodigo.get(idProducto);
	 * System.out.println("codigo CANAL: "+ codigo); //} } if
	 * (idProveedorMapa.equals(idProveedor)){ ArrayList<Map<String,String>>
	 * listCodigoEstado =
	 * mapCodigoEstadoOrdenMedioByProveedorSaved.get(idProveedorMapa); for (Map
	 * mapaCodigoEstado: listCodigoEstado){ //codigo =
	 * (String)mapaProductoCodigo.keySet().iterator().next();
	 * //System.out.println("codigo CANAL: "+ codigo); mapCodigoEstado = new
	 * HashMap(); mapCodigoEstado = mapaCodigoEstado; } } } return
	 * mapCodigoEstado; }catch (Exception e){ e.printStackTrace(); } return
	 * mapCodigoEstado; }
	 */

	// COMENTED 28 JUNIO
	// ADD 21 JUNIO
	/*
	 * public String getCodigoOrdenMedioAgrupadaXCanalSaved(Long idProveedor){
	 * String codigo ="";
	 * 
	 * try{
	 * //System.out.println("SIZE CANAL"+mapCodigoOrdenMedioByProveedorSaved
	 * .size()); //Iterator mapProductoByProveedorCodigoOrdenSavedTempIt =
	 * mapProductoClienteByProveedorCodigoOrdenMedioSaved.keySet().iterator();
	 * //MODIFIED 27 JUNIO //Iterator mapCodigoOrdenMedioByProveedorSavedTempIt
	 * = mapCodigoOrdenMedioByProveedorSaved.keySet().iterator(); Iterator
	 * mapCodigoEstadoOrdenMedioByProveedorSavedTempIt =
	 * mapCodigoEstadoOrdenMedioByProveedorSaved.keySet().iterator();
	 * 
	 * //while(mapProductoByProveedorCodigoOrdenSavedTempIt.hasNext()){
	 * while(mapCodigoEstadoOrdenMedioByProveedorSavedTempIt.hasNext()){
	 * //MODIFIED 27 JUNIO //Long idProveedorMapa =
	 * (Long)mapProductoByProveedorCodigoOrdenSavedTempIt.hasNext.next();
	 * //ArrayList<String> listCodigo =
	 * mapCodigoOrdenMedioByProveedorSaved.get(idProveedorMapa); Long
	 * idProveedorMapa =
	 * (Long)mapCodigoEstadoOrdenMedioByProveedorSavedTempIt.next(); //MODIFIED
	 * 27 JUNIO //ArrayList<String> listCodigo =
	 * mapCodigoEstadoOrdenMedioByProveedorSaved.get(idProveedorMapa); /*for
	 * (String codigoTemp: listCodigo){ //if (mapaProductoCodigo.get(idProducto)
	 * != null){ System.out.println("ENTRA AKI CANAL: " ); codigo =
	 * codigoTemp;//(String)mapaProductoCodigo.get(idProducto);
	 * System.out.println("codigo CANAL: "+ codigo); //} }* /
	 * ArrayList<Map<String,String>> listCodigo =
	 * mapCodigoEstadoOrdenMedioByProveedorSaved.get(idProveedorMapa); for (Map
	 * mapaProductoCodigo: listCodigo){ codigo =
	 * (String)mapaProductoCodigo.keySet().iterator().next();
	 * System.out.println("codigo CANAL: "+ codigo); } } return codigo; }catch
	 * (Exception e){ e.printStackTrace(); } return codigo; }
	 */
	// END 21 JUNIO

	// MODIFIED 7 OCTUBRE MODIFIED 1 SEPTIEMBRE ADD 19 MAYO
	/*
	 * Se crean las ordenes de Medio agrupadas por Canales es decir en una Orden
	 * de Medio contendran todos los productos con sus diferentes Versiones del
	 * canal en su detalle ejm Orden de Medio Canal 1: Ecuacolor A, Ecuaolor X,
	 * TARJETAS C, INSTITUCIONAL D, INSTITUCIONAL Z......... Detalle de Orden de
	 * Medio DE LOS COMERCIALES: A CUÑA, A MENCION, B PRESENTACION, C DESPEDIDA,
	 * Z PRESENTACION Z DESPEDIDA LOS MAPAS DE ORDENES DE MEDIO SERAN SEGUN
	 * PROVEEDOR crearOrdenesMedioAgrupadasXCanalForUpdate
	 */
	private void crearOrdenesMedioAgrupadasXCanal()
			throws GenericBusinessException {

		if (getMode() != SpiritMode.FIND_MODE) {
			planMedioIf.setEstado(ESTADO_APROBADO);
		}

		Collection<OrdenMedioIf> ordenMedioColl = SessionServiceLocator
				.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(
						planMedioIf.getId());

		if (ordenMedioColl.size() > 0 && !nuevoPlan) {

			listaProveedoresMap.clear();
			for (PlanMedioDetalleIf planMedioDetalle : planMedioDetallesPlantilla) {
				ClienteOficinaIf proveedor = mapaClienteOficina
						.get(planMedioDetalle.getProveedorId());
				listaProveedoresMap
						.put(String.valueOf(proveedor.getId())
								+ "-"
								+ String.valueOf(planMedioDetalle
										.getNumeroOrdenAgrupacion()), proveedor);
			}

			ArrayList<String> listaProveedoresTemp = new ArrayList<String>();

			Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioTotales = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
			Iterator ordenMedioIt = ordenMedioColl.iterator();
			while (ordenMedioIt.hasNext()) {
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt.next();

				Long proveedorId = ordenMedioIf.getProveedorId();
				int tmp = 0;

				for (String proveedorTmp : listaProveedoresTemp) {
					String key[] = proveedorTmp.split("-");
					Long proveedorIdTmp = Long.parseLong(key[0]);
					String numeroOrden = key[1];
					if (proveedorIdTmp.compareTo(proveedorId) != 0
							|| (proveedorIdTmp.compareTo(proveedorId) == 0 && Integer
									.parseInt(numeroOrden) != ordenMedioIf
									.getNumeroOrdenAgrupacion())) {
						tmp++;
					}
				}

				if (tmp == listaProveedoresTemp.size()) {
					listaProveedoresTemp.add(String.valueOf(proveedorId) + "-"
							+ ordenMedioIf.getNumeroOrdenAgrupacion());
				}

				Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleTotales = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();

				Collection<OrdenMedioDetalleIf> ordenMedioDetalleColl = SessionServiceLocator
						.getOrdenMedioDetalleSessionService()
						.findOrdenMedioDetalleByOrdenMedioId(
								ordenMedioIf.getId());
				Iterator ordenMedioDetalleIt = ordenMedioDetalleColl.iterator();
				while (ordenMedioDetalleIt.hasNext()) {
					OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt
							.next();

					ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = new ArrayList<OrdenMedioDetalleMapaIf>();
					Collection<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaColl = SessionServiceLocator
							.getOrdenMedioDetalleMapaSessionService()
							.findOrdenMedioDetalleMapaByOrdenMedioDetalleId(
									ordenMedioDetalleIf.getId());
					Iterator ordenMedioDetalleMapaIt = ordenMedioDetalleMapaColl
							.iterator();
					while (ordenMedioDetalleMapaIt.hasNext()) {
						OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf = (OrdenMedioDetalleMapaIf) ordenMedioDetalleMapaIt
								.next();
						ordenMedioDetalleMapaList.add(ordenMedioDetalleMapaIf);
					}
					ordenesMedioDetalleTotales.put(ordenMedioDetalleIf,
							ordenMedioDetalleMapaList);
				}
				ordenesMedioTotales.put(ordenMedioIf,
						ordenesMedioDetalleTotales);
			}

			for (String proveedorTmp : listaProveedoresTemp) {
				String key[] = proveedorTmp.split("-");
				Long proveedorIdTmp = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				Iterator ordenMedioIt2 = ordenesMedioTotales.keySet()
						.iterator();
				Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioParcial = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
				while (ordenMedioIt2.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt2
							.next();
					Long proveedorId = ordenMedioIf.getProveedorId();
					Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleMap = ordenesMedioTotales
							.get(ordenMedioIf);
					Iterator ordenMedioDetalleIt = ordenesMedioDetalleMap
							.keySet().iterator();
					if (ordenMedioDetalleIt.hasNext()) {
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt
								.next();
						if (proveedorId.compareTo(proveedorIdTmp) == 0
								&& ordenMedioIf.getNumeroOrdenAgrupacion() == Integer
										.parseInt(numeroOrden)) {
							ordenesMedioParcial.put(ordenMedioIf,
									ordenesMedioDetalleMap);
						}
					}
				}
				mapaProveedorOrdenesComp.put(String.valueOf(proveedorIdTmp)
						+ "-" + numeroOrden, ordenesMedioParcial);
			}
		} else {
			ordenesMediosMapComp.clear();
			ordenesMedioMap.clear();
			mapOrdenMediobyProveedor.clear();
			mapOrdenMediobyProveedorPCanje.clear();
			mapOrdenMediobyProveedorPDesc.clear();
			mapOrdenMediobyProveedorPBono.clear();

			// ver donde se utilizan las de producto y tambien colocarlas
			mapaProveedorOrdenes.clear();
			mapaProveedorOrdenesComp.clear();
			mapOrdenMediobyProveedorObservacion.clear();
			mapCodigoOrdenMedioByProveedor.clear();

			BigDecimal valor_subtotal = new BigDecimal(0);
			BigDecimal valor_descuento = new BigDecimal(0);
			BigDecimal iva = new BigDecimal(0);
			BigDecimal valorDetalle = new BigDecimal(0);
			BigDecimal ivaDetalle = new BigDecimal(0);
			BigDecimal subtotal = new BigDecimal(0);

			BigDecimal valorSubtotalVenta1 = new BigDecimal(0);
			BigDecimal valorSubtotalVenta2 = new BigDecimal(0);
			BigDecimal valorDescuentoVenta = new BigDecimal(0);
			BigDecimal valorComisionAgencia = new BigDecimal(0);
			BigDecimal valorIvaVenta = new BigDecimal(0);

			UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
			OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = SessionServiceLocator
					.getOrdenTrabajoDetalleSessionService()
					.getOrdenTrabajoDetalle(
							planMedioIf.getOrdenTrabajoDetalleId());
			OrdenTrabajoIf ordenTrabajoIf = SessionServiceLocator
					.getOrdenTrabajoSessionService().getOrdenTrabajo(
							ordenTrabajoDetalleIf.getOrdenId());
			ClienteOficinaIf clienteOficina = mapaClienteOficina
					.get(ordenTrabajoIf.getClienteoficinaId());

			listaProveedoresMap.clear();

			for (PlanMedioDetalleIf planMedioDetalle : planMedioDetallesPlantilla) {
				ClienteOficinaIf proveedor = mapaClienteOficina
						.get(planMedioDetalle.getProveedorId());
				listaProveedoresMap
						.put(String.valueOf(proveedor.getId())
								+ "-"
								+ String.valueOf(planMedioDetalle
										.getNumeroOrdenAgrupacion()), proveedor);
			}

			Map<String, Map<Long, List<Long>>> proveedorIdProductoClienteIdVersiones = new LinkedHashMap<String, Map<Long, List<Long>>>();
			Map<Long, List<Long>> productoClienteIdVersiones = new LinkedHashMap<Long, List<Long>>();

			// agrega a todos los Productos Cliente id de cada PlanMedioDetalle
			// de mapasComercialesPlantilla
			List<Long> planMedioDetalleIdList = new ArrayList<Long>(); // ADD 7
																		// OCTUBRE
			// List<String> planMedioDetalleIdList = new ArrayList<String>();
			// COMENTED 7 OCTUBRE

			// Se llena el hashMap mapasComercialesPlantilla
			if (mapasComercialesPlantilla.isEmpty()) {
				llenarMapasComercialesPlantilla(planMedioIf, false);
			}

			Iterator mapasComercialesPlantillaIt = mapasComercialesPlantilla
					.keySet().iterator();

			while (mapasComercialesPlantillaIt.hasNext()) {
				PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf) mapasComercialesPlantillaIt
						.next();
				ClienteOficinaIf clienteOficinaIfTemp = mapaClienteOficina
						.get(planMedioDetalle.getProveedorId());

				if (proveedorIdProductoClienteIdVersiones.get(String
						.valueOf(clienteOficinaIfTemp.getId())
						+ "-"
						+ String.valueOf(planMedioDetalle
								.getNumeroOrdenAgrupacion())) == null) {
					productoClienteIdVersiones = new LinkedHashMap<Long, List<Long>>();
					planMedioDetalleIdList = new ArrayList<Long>();

					// agrega a todas las versione y los Productos Cliente id de
					// cada PlanMedioDetalle de mapasComercialesPlantilla
					planMedioDetalleIdList.add(planMedioDetalle
							.getCampanaProductoVersionId());
					productoClienteIdVersiones.put(
							planMedioDetalle.getProductoClienteId(),
							planMedioDetalleIdList);
				} else {
					productoClienteIdVersiones = proveedorIdProductoClienteIdVersiones
							.get(String.valueOf(clienteOficinaIfTemp.getId())
									+ "-"
									+ String.valueOf(planMedioDetalle
											.getNumeroOrdenAgrupacion()));

					if (productoClienteIdVersiones.get(planMedioDetalle
							.getProductoClienteId()) == null) {
						planMedioDetalleIdList = new ArrayList<Long>();

						planMedioDetalleIdList.add(planMedioDetalle
								.getCampanaProductoVersionId());
						productoClienteIdVersiones.put(
								planMedioDetalle.getProductoClienteId(),
								planMedioDetalleIdList);
					} else {
						planMedioDetalleIdList = productoClienteIdVersiones
								.get(planMedioDetalle.getProductoClienteId());
						boolean existeIdVersion = false;
						for (Long idCampanaProductoVersion : planMedioDetalleIdList) {
							if (idCampanaProductoVersion
									.compareTo(planMedioDetalle
											.getCampanaProductoVersionId()) == 0) {
								existeIdVersion = true;
								break;
							}
						}
						if (!existeIdVersion) {
							// agrega a todos los Productos Cliente id de cada
							// PlanMedioDetalle de mapasComercialesPlantilla
							planMedioDetalleIdList.add(planMedioDetalle
									.getCampanaProductoVersionId());
							productoClienteIdVersiones.put(
									planMedioDetalle.getProductoClienteId(),
									planMedioDetalleIdList);
						}
					}
				}
				String key = String.valueOf(clienteOficinaIfTemp.getId()) + "-"
						+ planMedioDetalle.getComercial().split(",")[2];
				proveedorIdProductoClienteIdVersiones.put(key,
						productoClienteIdVersiones);
			}

			Iterator proveedorIdProductoClienteIdVersionesIt = proveedorIdProductoClienteIdVersiones.keySet().iterator();
			while (proveedorIdProductoClienteIdVersionesIt.hasNext()) {
				String key = (String) proveedorIdProductoClienteIdVersionesIt.next();
				String[] keySplit = key.split("-");
				Long proveedorId = Long.parseLong(keySplit[0]);
				int numeroOrden = Integer.parseInt(keySplit[1]);
				int tmp = 0;

				BigDecimal subtotalOrdenProv = new BigDecimal(0);
				clienteOficinaIf = listaProveedoresMap.get(String.valueOf(proveedorId) + "-" + numeroOrden);

				valor_subtotal = new BigDecimal(0);
				valor_descuento = new BigDecimal(0);
				valorDescuentoVenta = new BigDecimal(0);
				valorComisionAgencia = new BigDecimal(0);

				OrdenMedioIf data = new OrdenMedioData();
				data.setClienteOficinaId(clienteOficina.getId());
				data.setOficinaId(Parametros.getIdOficina());
				data.setProveedorId(proveedorId);

				// se indica de que manera fue generada la orden de Medio C
				// Canal, P Producto Comercial, V Version
				data.setOrdenMedioTipo(ordenMedioTipo);

				String observacion = "";
				data.setObservacion(observacion);

				Map<String, String> mapCodigoEstado = getCodigoEstadoOrdenMedioAgrupadaXCanalSaved(proveedorId, numeroOrden);
				String codigo = "";
				String estado = "";

				// LAS ORDENES QUE REEMPLAZAN A LAS ANTERIORES TENDRAN EL MISMO CODIGO Y ESTADO
				if (mapCodigoEstado != null) {
					codigo = (String) mapCodigoEstado.keySet().iterator().next();
					estado = mapCodigoEstado.get(codigo);
				} 
				
				// LAS ORDENES NUEVAS TENDRAN NUEVO CODIGO Y ESTADO ENVIADO
				else {
					codigo = getCodigo(new java.sql.Date(new java.util.Date().getTime()));
					//estado = ESTADO_ORDEN_ENVIADA;
					// desde ahora (abril 2013) sera "Emitida"
					estado = ESTADO_ORDEN_EMITIDA;
				}

				data.setCodigo(codigo);
				data.setEstado(estado);

				ArrayList<String> listCodigoOrden = new ArrayList<String>();
				/*
				Map mapProductoPautaRegularTv = new HashMap();
				mapProductoPautaRegularTv.put("genericoId", genericoPautaRegular.getId());
				mapProductoPautaRegularTv.put("proveedorId", proveedorId);
				ProductoIf productoPautaRegularTv = (ProductoIf) SessionServiceLocator.getProductoSessionService().findProductoByQuery(mapProductoPautaRegularTv).iterator().next();
				*/
				Map<Long, ProductoIf> mapaProveedorProducto = mapaGenericoMapaProveedorProducto.get(genericoPautaRegular.getId());
				ProductoIf productoPautaRegularTv = mapaProveedorProducto.get(proveedorId);
				
				GenericoIf genericoProveedor = mapaGenerico.get(productoPautaRegularTv.getGenericoId());
				// producto id es de PAUTA REGULAR TELEVISION
				data.setProductoProveedorId(productoPautaRegularTv.getId());

				// El usuario y el empleado seran el mismo, el que esta haciendo
				// el presupuesto
				data.setEmpleadoId(SessionServiceLocator.getEmpleadoSessionService().getEmpleado(usuario.getEmpleadoId()).getId());
				data.setUsuarioId(usuario.getId());

				// Las fechas todas seran la misma, la actual
				data.setFechaCreacion(Utilitarios.fromSqlDateToTimestamp(new java.sql.Date(new GregorianCalendar().getTimeInMillis())));

				// MODIFIED 6 JULIO
				data.setFechaOrden(planMedioIf.getFechaInicio());
				
				// El estado siempre sera orden "Enviada"
				//data.setEstado(ESTADO_ORDEN_ENVIADA);
				// desde ahora (abril 2013) sera "Emitida"
				data.setEstado(ESTADO_ORDEN_EMITIDA);

				if (tmp == 0) {
					ordenesMedioMap = new LinkedHashMap<OrdenMedioIf, List<OrdenMedioDetalleIf>>();
					ordenesMediosMapComp = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
				}

				List<OrdenMedioDetalleIf> dataDetalleVector = new ArrayList<OrdenMedioDetalleIf>();
				Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> dataDetalleHash = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();

				Map mapProductoClienteIdVersiones = proveedorIdProductoClienteIdVersiones.get(key);
				Iterator mapProductoClienteIdVersionesIt = mapProductoClienteIdVersiones.keySet().iterator();

				while (mapProductoClienteIdVersionesIt.hasNext()) {
					Long productoClienteId = (Long) mapProductoClienteIdVersionesIt.next();

					List<Long> campanaProductoVersionList = (List<Long>) mapProductoClienteIdVersiones.get(productoClienteId);

					// recorre el array de proveedores y generara una orden
					for (Long campanaProductoVersion : campanaProductoVersionList) {// ADD
																					// 7
																					// OCTUBRE

						BigDecimal porcentajeComisionAgenciaTmp = new BigDecimal(0);
						BigDecimal porcentajeBonificacionVentaTmp = new BigDecimal(0);
						BigDecimal porcentajeDescuentoVentaTmp = new BigDecimal(0);

						Iterator mapasComercialesPlantillaIt2 = mapasComercialesPlantilla.keySet().iterator();
						while (mapasComercialesPlantillaIt2.hasNext()) {
							PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf) mapasComercialesPlantillaIt2.next();
							ComercialIf comercialIfPlantilla = mapaComercial.get(planMedioDetalle.getComercialId());
							if (planMedioDetalle.getProveedorId().compareTo(proveedorId) == 0 
									&& comercialIfPlantilla.getProductoClienteId().compareTo(productoClienteId) == 0 
									&& comercialIfPlantilla.getCampanaProductoVersionId().compareTo(campanaProductoVersion) == 0
									&& planMedioDetalle.getComercial().split(",")[2].equals(String.valueOf(numeroOrden))) {

								if (dataDetalleHash.size() > 0) {
									OrdenMedioDetalleData dataDetalle = new OrdenMedioDetalleData();
									dataDetalle.setComercial(planMedioDetalle
											.getComercial());
									dataDetalle.setComercialId(planMedioDetalle
											.getComercialId());
									dataDetalle.setHora(planMedioDetalle
											.getHoraInicio());
									dataDetalle.setPrograma(planMedioDetalle
											.getPrograma());
									dataDetalle
											.setProductoClienteId(comercialIfPlantilla
													.getProductoClienteId());
									dataDetalle
											.setCampanaProductoVersionId(comercialIfPlantilla
													.getCampanaProductoVersionId());
									dataDetalle.setPauta(planMedioDetalle
											.getPauta());
									dataDetalle
											.setAuspicioDescripcion(planMedioDetalle
													.getAuspicioDescripcion());

									int cuniasByOrdenMedioDetalle = 0;
									ArrayList<OrdenMedioDetalleMapaIf> dataDetalleMapaList = new ArrayList<OrdenMedioDetalleMapaIf>();
									Collection mapasComercial = mapasComercialesPlantilla.get(planMedioDetalle);
									Iterator mapasComercialIt = mapasComercial.iterator();
									while (mapasComercialIt.hasNext()) {
										MapaComercialIf mapaComercialIf = (MapaComercialIf) mapasComercialIt.next();
										OrdenMedioDetalleMapaData dataDetalleMapa = new OrdenMedioDetalleMapaData();
										dataDetalleMapa.setFecha(Utilitarios.fromSqlDateToTimestamp(mapaComercialIf.getFecha()));
										dataDetalleMapa.setFrecuencia(mapaComercialIf.getFrecuencia());
										dataDetalleMapaList.add(dataDetalleMapa);
										cuniasByOrdenMedioDetalle = cuniasByOrdenMedioDetalle + mapaComercialIf.getFrecuencia();
									}
									BigDecimal valorDataDetalle = new BigDecimal(0);
									BigDecimal ivaDataDetalle = new BigDecimal(0);

									dataDetalle.setValorTarifa(planMedioDetalle.getValorTarifa());
									dataDetalle.setTotalCunias(planMedioDetalle.getTotalCunias());
									dataDetalle.setProductoProveedorId(planMedioDetalle.getProductoProveedorId());
									dataDetalle.setPorcentajeDescuento(planMedioDetalle.getPorcentajeDescuento());
									dataDetalle.setValorDescuento(planMedioDetalle.getValorDescuento());
									dataDetalle.setValorIva(planMedioDetalle.getValorIva());
									dataDetalle.setValorSubtotal(planMedioDetalle.getValorSubtotal());
									dataDetalle.setValorTotal(planMedioDetalle.getValorTotal());

									dataDetalle.setValorDescuentoVenta(planMedioDetalle.getValorDescuentoVenta());
									dataDetalle.setValorComisionAgencia(planMedioDetalle.getValorComisionAgencia());
									dataDetalle.setPorcentajeDescuentoVenta(planMedioDetalle.getPorcentajeDescuentoVenta());
									dataDetalle.setPorcentajeComisionAgencia(planMedioDetalle.getPorcentajeComisionAgencia());
									porcentajeComisionAgenciaTmp = planMedioDetalle.getPorcentajeComisionAgencia();
									porcentajeBonificacionVentaTmp = planMedioDetalle.getPorcentajeBonificacionVenta();
									porcentajeDescuentoVentaTmp = planMedioDetalle.getPorcentajeDescuentoVenta();

									dataDetalleHash.put(dataDetalle,dataDetalleMapaList);
									dataDetalleVector.add(dataDetalle);

									valor_subtotal = valor_subtotal.add(planMedioDetalle.getValorSubtotal());
									valor_descuento = valor_descuento.add(planMedioDetalle.getValorDescuento());

									valorDescuentoVenta = valorDescuentoVenta.add(planMedioDetalle.getValorDescuentoVenta());
									valorComisionAgencia = valorComisionAgencia.add(planMedioDetalle.getValorComisionAgencia());

								} else {// CUANDO EL TAMAÑO DE dataDetalleHash =
										// 0
									OrdenMedioDetalleData dataDetalle = new OrdenMedioDetalleData();
									dataDetalle.setComercial(planMedioDetalle.getComercial());
									dataDetalle.setComercialId(planMedioDetalle.getComercialId());
									dataDetalle.setHora(planMedioDetalle.getHoraInicio());
									dataDetalle.setPrograma(planMedioDetalle.getPrograma());
									dataDetalle.setProductoClienteId(comercialIfPlantilla.getProductoClienteId());
									dataDetalle.setCampanaProductoVersionId(comercialIfPlantilla.getCampanaProductoVersionId()); // END
																						// 7
																						// OCTUBRE
									dataDetalle.setPauta(planMedioDetalle.getPauta());
									dataDetalle.setAuspicioDescripcion(planMedioDetalle.getAuspicioDescripcion());

									int cuniasByOrdenMedioDetalle = 0;
									ArrayList<OrdenMedioDetalleMapaIf> dataDetalleMapaList = new ArrayList<OrdenMedioDetalleMapaIf>();
									Collection mapasComercial = mapasComercialesPlantilla.get(planMedioDetalle);
									Iterator mapasComercialIt = mapasComercial.iterator();
									while (mapasComercialIt.hasNext()) {
										MapaComercialIf mapaComercialIf = (MapaComercialIf) mapasComercialIt.next();
										OrdenMedioDetalleMapaData dataDetalleMapa = new OrdenMedioDetalleMapaData();
										dataDetalleMapa.setFecha(Utilitarios.fromSqlDateToTimestamp(mapaComercialIf.getFecha()));
										dataDetalleMapa.setFrecuencia(mapaComercialIf.getFrecuencia());
										dataDetalleMapaList.add(dataDetalleMapa);
										cuniasByOrdenMedioDetalle = cuniasByOrdenMedioDetalle + mapaComercialIf.getFrecuencia();
									}
									BigDecimal valorDataDetalle = new BigDecimal(0);
									BigDecimal ivaDataDetalle = new BigDecimal(0);

									dataDetalle.setValorTarifa(planMedioDetalle.getValorTarifa());
									dataDetalle.setTotalCunias(planMedioDetalle.getTotalCunias());
									dataDetalle.setProductoProveedorId(planMedioDetalle.getProductoProveedorId());
									dataDetalle.setPorcentajeDescuento(planMedioDetalle.getPorcentajeDescuento());
									dataDetalle.setValorDescuento(planMedioDetalle.getValorDescuento());
									
									dataDetalle.setValorIva(planMedioDetalle.getValorIva());
									dataDetalle.setValorSubtotal(planMedioDetalle.getValorSubtotal());
									dataDetalle.setValorTotal(planMedioDetalle.getValorTotal());

									dataDetalle.setValorDescuentoVenta(planMedioDetalle.getValorDescuentoVenta());
									dataDetalle.setValorComisionAgencia(planMedioDetalle.getValorComisionAgencia());
									dataDetalle.setPorcentajeDescuentoVenta(planMedioDetalle.getPorcentajeDescuentoVenta());
									dataDetalle.setPorcentajeComisionAgencia(planMedioDetalle.getPorcentajeComisionAgencia());
									porcentajeComisionAgenciaTmp = planMedioDetalle.getPorcentajeComisionAgencia();
									porcentajeBonificacionVentaTmp = planMedioDetalle.getPorcentajeBonificacionVenta();
									porcentajeDescuentoVentaTmp = planMedioDetalle.getPorcentajeDescuentoVenta();
									dataDetalleHash.put(dataDetalle,dataDetalleMapaList);
									dataDetalleVector.add(dataDetalle);

									valor_subtotal = valor_subtotal.add(planMedioDetalle.getValorSubtotal());
									valor_descuento = valor_descuento.add(planMedioDetalle.getValorDescuento());

									valorDescuentoVenta = valorDescuentoVenta.add(planMedioDetalle.getValorDescuentoVenta());
									valorComisionAgencia = valorComisionAgencia.add(planMedioDetalle.getValorComisionAgencia());
								}
							}
						}

						data.setPorcentajeComisionAgencia(porcentajeComisionAgenciaTmp);
						data.setPorcentajeBonificacionVenta(porcentajeBonificacionVentaTmp);
						data.setPorcentajeDescuentoVenta(porcentajeDescuentoVentaTmp);
						data.setValorSubtotal(valor_subtotal);
						data.setValorDescuento(valor_descuento);
						subtotal = data.getValorSubtotal().subtract(valor_descuento);
						iva = genericoProveedor.getCobraIva().equals("S") ? subtotal.multiply(new BigDecimal(Parametros.IVA / 100D)): BigDecimal.ZERO;
						data.setValorIva(iva);
						data.setValorTotal(subtotal.add(iva));
						data.setValorDescuentoVenta(valorDescuentoVenta);
						data.setValorComisionAgencia(valorComisionAgencia);
						data.setPorcentajeCanje(new BigDecimal(0));
						data.setNumeroOrdenAgrupacion(numeroOrden);
						ordenesMediosMapComp.put(data, dataDetalleHash);
						ordenesMedioMap.put(data, dataDetalleVector);
						listCodigoOrden.add(data.getCodigo());
						tmp++;
					}
				}

				mapaProveedorOrdenes.put(String.valueOf(proveedorId) + "-"
						+ String.valueOf(numeroOrden), ordenesMedioMap);
				mapaProveedorOrdenesComp.put(String.valueOf(proveedorId) + "-"
						+ String.valueOf(numeroOrden), ordenesMediosMapComp);
				mapCodigoOrdenMedioByProveedor.put(String.valueOf(proveedorId)
						+ "-" + String.valueOf(numeroOrden), listCodigoOrden);
			}
		}
	}

	public Map<String, String> getCodigoEstadoOrdenMedioAgrupadaXCampanaProductoVersionSaved(
			Long idProveedor, Long idProducto, String numeroOrden,
			Long idCampanaProductoVersion) {
		Map mapaCodigoEstado = null;
		try {
			Iterator mapProductoClienteVersionByProveedorCodigoEstadoOrdenMedioSavedIt = mapProductoClienteVersionByProveedorCodigoEstadoOrdenMedioSaved
					.keySet().iterator();

			while (mapProductoClienteVersionByProveedorCodigoEstadoOrdenMedioSavedIt
					.hasNext()) {
				String[] key = ((String) mapProductoClienteVersionByProveedorCodigoEstadoOrdenMedioSavedIt
						.next()).split("-");
				Long idProveedorMapa = Long.parseLong(key[0]);
				String numeroOrdenMapa = key[1];
				// MODIFIED 27 JUNIO
				// ArrayList<Map<Long,String>> listMapProductoCodigo =
				// mapProductoClienteByProveedorCodigoOrdenMedioSaved.get(idProveedorMapa);
				/*
				 * for (Map mapaProductoCodigo: listMapProductoCodigo){ if
				 * (mapaProductoCodigo.get(idProducto) != null){
				 * //System.out.println("ENTRA AKI: " ); codigo =
				 * (String)mapaProductoCodigo.get(idProducto);
				 * //System.out.println("codigo: "+ codigo); } }
				 */
				if (idProveedorMapa.compareTo(idProveedor) == 0
						&& numeroOrdenMapa.equals(numeroOrden)) {

					ArrayList<Map> listVersionByProveedorCodigoEstadoOrdenMedioSaved = (ArrayList) mapProductoClienteVersionByProveedorCodigoEstadoOrdenMedioSaved
							.get(String.valueOf(idProveedorMapa) + "-"
									+ numeroOrdenMapa);

					/*
					 * COMENTED 11 OCTUBRE for (Map
					 * mapaProductoVersionCodigoEstado:
					 * listVersionByProveedorCodigoEstadoOrdenMedioSaved){
					 * 
					 * //ADD 5 SEPTIEMBRE
					 * if(mapaProductoVersionCodigoEstado.containsKey
					 * (idProducto)){ Map mapaVersionCodigoEstado = (Map)
					 * mapaProductoVersionCodigoEstado.get(idProducto);
					 * 
					 * if (mapaVersionCodigoEstado.get(versionProductoCliente)
					 * != null){ mapaCodigoEstado = new HashMap();
					 * mapaCodigoEstado = (Map)
					 * mapaVersionCodigoEstado.get(versionProductoCliente);
					 * //COMENTED 28 JUNIO //codigo =
					 * (String)mapaCodigoEstado.keySet().iterator().next();
					 * //System.out.println("codigo CANAL: "+ codigo); }
					 * 
					 * } }
					 */

					// ADD 11 OCTUBRE
					boolean existeProducto = false;
					for (Map mapaProductoVersionCodigoEstado : listVersionByProveedorCodigoEstadoOrdenMedioSaved) {
						Long idProductoClienteTemp = (Long) mapaProductoVersionCodigoEstado
								.keySet().iterator().next();
						if (idProductoClienteTemp.compareTo(idProducto) == 0) {
							Map<Long, Map<String, String>> mapaCampanaProductoVersionCodigoEstado = (Map) mapaProductoVersionCodigoEstado
									.get(idProductoClienteTemp);

							for (Long idVersion : mapaCampanaProductoVersionCodigoEstado
									.keySet()) {
								if (idVersion
										.compareTo(idCampanaProductoVersion) == 0) {
									mapaCodigoEstado = new HashMap();
									mapaCodigoEstado = (Map) mapaCampanaProductoVersionCodigoEstado
											.get(idVersion);
									break;
								}
							}
						}
					}
					// END 11 OCTUBRE
				}
			}
			// COMENTED AND ADD 28 JUNIO
			// return codigo;
			return mapaCodigoEstado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// COMENTED AND ADD 28 JUNIO
		// return codigo;
		return mapaCodigoEstado;
	}

	// END 11 OCTUBRE

	// ADD 13 OCTUBRE MODIFIED 28 JUNIO ADD 21 JUNIO
	public Map<String, String> getCodigoEstadoOrdenMedioAgrupadaXProductoSaved(
			Long idProveedor, String numeroOrden, Long idProducto) {
		// COMENTED AND ADD 28 JUNIO
		// String codigo ="";
		Map mapaCodigoEstado = null;
		try {
			// System.out.println("SIZE "+mapProductoClienteByProveedorCodigoOrdenMedioSaved.size());
			// MODIFIED 27 JUNIO
			// Iterator mapProductoByProveedorCodigoOrdenSavedTempIt =
			// mapProductoClienteByProveedorCodigoOrdenMedioSaved.keySet().iterator();
			Iterator mapProductoByProveedorCodigoOrdenSavedTempIt = mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved
					.keySet().iterator();

			while (mapProductoByProveedorCodigoOrdenSavedTempIt.hasNext()) {
				String[] key = ((String) mapProductoByProveedorCodigoOrdenSavedTempIt
						.next()).split("-");
				Long idProveedorMapa = Long.parseLong(key[0]);
				String numeroOrdenMapa = key[1];
				// MODIFIED 27 JUNIO
				// ArrayList<Map<Long,String>> listMapProductoCodigo =
				// mapProductoClienteByProveedorCodigoOrdenMedioSaved.get(idProveedorMapa);
				/*
				 * for (Map mapaProductoCodigo: listMapProductoCodigo){ if
				 * (mapaProductoCodigo.get(idProducto) != null){
				 * //System.out.println("ENTRA AKI: " ); codigo =
				 * (String)mapaProductoCodigo.get(idProducto);
				 * //System.out.println("codigo: "+ codigo); } }
				 */
				if (idProveedorMapa.compareTo(idProveedor) == 0
						&& numeroOrden.equals(numeroOrdenMapa)) {
					ArrayList<Map<Long, Map<String, String>>> listMapProductoCodigoEstado = mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved
							.get(String.valueOf(idProveedorMapa) + "-"
									+ numeroOrdenMapa);
					for (Map mapaProductoCodigoEstado : listMapProductoCodigoEstado) {
						if (mapaProductoCodigoEstado.get(idProducto) != null) {
							mapaCodigoEstado = new HashMap();
							mapaCodigoEstado = (Map) mapaProductoCodigoEstado
									.get(idProducto);
							// COMENTED 28 JUNIO
							// codigo =
							// (String)mapaCodigoEstado.keySet().iterator().next();
							// System.out.println("codigo CANAL: "+ codigo);
						}
					}
				}
			}
			// COMENTED AND ADD 28 JUNIO
			// return codigo;
			return mapaCodigoEstado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// COMENTED AND ADD 28 JUNIO
		// return codigo;
		return mapaCodigoEstado;
	}

	// ADD 13 OCTUBRE END MODIFIED 28 JUNIO END 21 JUNIO

	// MODIFIED 5 SEPTIEMBRE 28 JUNIO ADD 21 JUNIO
	public Map<String, String> getCodigoEstadoOrdenMedioAgrupadaXProductoSaved(
			Long idProveedor, String numeroOrden, Long idProducto,
			String versionProductoCliente) {
		// COMENTED AND ADD 28 JUNIO
		// String codigo ="";
		Map mapaCodigoEstado = null;
		try {
			// System.out.println("SIZE "+mapProductoClienteByProveedorCodigoOrdenMedioSaved.size());
			// MODIFIED 27 JUNIO
			// Iterator mapProductoByProveedorCodigoOrdenSavedTempIt =
			// mapProductoClienteByProveedorCodigoOrdenMedioSaved.keySet().iterator();
			// COMENTED 5 SEPTIEMBRE
			// Iterator mapProductoByProveedorCodigoOrdenSavedTempIt =
			// mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved.keySet().iterator();
			Iterator mapProductoClienteVersionByProveedorCodigoEstadoOrdenMedioSavedIt = mapProductoClienteVersionByProveedorCodigoEstadoOrdenMedioSaved
					.keySet().iterator();

			while (mapProductoClienteVersionByProveedorCodigoEstadoOrdenMedioSavedIt
					.hasNext()) {
				String[] key = ((String) mapProductoClienteVersionByProveedorCodigoEstadoOrdenMedioSavedIt
						.next()).split("-");
				Long idProveedorMapa = Long.parseLong(key[0]);
				String numeroOrdenMapa = key[1];
				// MODIFIED 27 JUNIO
				// ArrayList<Map<Long,String>> listMapProductoCodigo =
				// mapProductoClienteByProveedorCodigoOrdenMedioSaved.get(idProveedorMapa);
				/*
				 * for (Map mapaProductoCodigo: listMapProductoCodigo){ if
				 * (mapaProductoCodigo.get(idProducto) != null){
				 * //System.out.println("ENTRA AKI: " ); codigo =
				 * (String)mapaProductoCodigo.get(idProducto);
				 * //System.out.println("codigo: "+ codigo); } }
				 */
				if (idProveedorMapa.compareTo(idProveedor) == 0
						&& numeroOrdenMapa.equals(numeroOrden)) {

					ArrayList<Map> listVersionByProveedorCodigoEstadoOrdenMedioSaved = (ArrayList) mapProductoClienteVersionByProveedorCodigoEstadoOrdenMedioSaved
							.get(String.valueOf(idProveedorMapa) + "-"
									+ numeroOrdenMapa);

					for (Map mapaProductoVersionCodigoEstado : listVersionByProveedorCodigoEstadoOrdenMedioSaved) {

						// ADD 5 SEPTIEMBRE
						if (mapaProductoVersionCodigoEstado
								.containsKey(idProducto)) {
							Map mapaVersionCodigoEstado = (Map) mapaProductoVersionCodigoEstado
									.get(idProducto);

							if (mapaVersionCodigoEstado
									.get(versionProductoCliente) != null) {
								mapaCodigoEstado = new HashMap();
								mapaCodigoEstado = (Map) mapaVersionCodigoEstado
										.get(versionProductoCliente);
								// COMENTED 28 JUNIO
								// codigo =
								// (String)mapaCodigoEstado.keySet().iterator().next();
								// System.out.println("codigo CANAL: "+ codigo);
							}

						}
					}

				}
			}
			// COMENTED AND ADD 28 JUNIO
			// return codigo;
			return mapaCodigoEstado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// COMENTED AND ADD 28 JUNIO
		// return codigo;
		return mapaCodigoEstado;
	}

	// END MODIFIED 5 SEPTIEMBRE Y 28 JUNIO END 21 JUNIO

	// COLOCADO AGAIN 11 OCTUBRE MODIFIED 28 JUNIO
	// ADD 21 JUNIO
	public Map<String, String> getCodigoEstadoOrdenMedioAgrupadaXProductoClienteSaved(
			Long idProveedor, String numeroOrden, Long idProducto) {
		// COMENTED AND ADD 28 JUNIO
		// String codigo ="";
		Map mapaCodigoEstado = null;
		try {
			// System.out.println("SIZE "+mapProductoClienteByProveedorCodigoOrdenMedioSaved.size());
			// MODIFIED 27 JUNIO
			// Iterator mapProductoByProveedorCodigoOrdenSavedTempIt =
			// mapProductoClienteByProveedorCodigoOrdenMedioSaved.keySet().iterator();
			Iterator mapProductoByProveedorCodigoOrdenSavedTempIt = mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved
					.keySet().iterator();

			while (mapProductoByProveedorCodigoOrdenSavedTempIt.hasNext()) {
				String[] key = ((String) mapProductoByProveedorCodigoOrdenSavedTempIt
						.next()).split("-");
				Long idProveedorMapa = Long.parseLong(key[0]);
				String numeroOrdenMapa = key[1];
				// MODIFIED 27 JUNIO
				// ArrayList<Map<Long,String>> listMapProductoCodigo =
				// mapProductoClienteByProveedorCodigoOrdenMedioSaved.get(idProveedorMapa);
				/*
				 * for (Map mapaProductoCodigo: listMapProductoCodigo){ if
				 * (mapaProductoCodigo.get(idProducto) != null){
				 * //System.out.println("ENTRA AKI: " ); codigo =
				 * (String)mapaProductoCodigo.get(idProducto);
				 * //System.out.println("codigo: "+ codigo); } }
				 */
				if (idProveedorMapa.equals(idProveedor)) {
					ArrayList<Map<Long, Map<String, String>>> listMapProductoCodigoEstado = mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved
							.get(String.valueOf(idProveedorMapa) + "-"
									+ numeroOrdenMapa);
					for (Map mapaProductoCodigoEstado : listMapProductoCodigoEstado) {
						if (mapaProductoCodigoEstado.get(idProducto) != null) {
							mapaCodigoEstado = new HashMap();
							mapaCodigoEstado = (Map) mapaProductoCodigoEstado
									.get(idProducto);
							// COMENTED 28 JUNIO
							// codigo =
							// (String)mapaCodigoEstado.keySet().iterator().next();
							// System.out.println("codigo CANAL: "+ codigo);
						}
					}
				}
			}
			// COMENTED AND ADD 28 JUNIO
			// return codigo;
			return mapaCodigoEstado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// COMENTED AND ADD 28 JUNIO
		// return codigo;
		return mapaCodigoEstado;
	}

	// END 21 JUNIO
	// END MODIFIED 28 JUNIO

	/*
	 * COMENTED 5 SEPTIEMBRE MODIFIED 28 JUNIO ADD 21 JUNIO public
	 * Map<String,String> getCodigoEstadoOrdenMedioAgrupadaXProductoSaved(Long
	 * idProveedor, Long idProducto){ //COMENTED AND ADD 28 JUNIO //String
	 * codigo =""; Map mapaCodigoEstado = null; try{
	 * //System.out.println("SIZE "
	 * +mapProductoClienteByProveedorCodigoOrdenMedioSaved.size()); //MODIFIED
	 * 27 JUNIO //Iterator mapProductoByProveedorCodigoOrdenSavedTempIt =
	 * mapProductoClienteByProveedorCodigoOrdenMedioSaved.keySet().iterator();
	 * Iterator mapProductoByProveedorCodigoOrdenSavedTempIt =
	 * mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved
	 * .keySet().iterator();
	 * 
	 * while(mapProductoByProveedorCodigoOrdenSavedTempIt.hasNext()){ Long
	 * idProveedorMapa =
	 * (Long)mapProductoByProveedorCodigoOrdenSavedTempIt.next(); //MODIFIED 27
	 * JUNIO //ArrayList<Map<Long,String>> listMapProductoCodigo =
	 * mapProductoClienteByProveedorCodigoOrdenMedioSaved.get(idProveedorMapa);
	 * for (Map mapaProductoCodigo: listMapProductoCodigo){ if
	 * (mapaProductoCodigo.get(idProducto) != null){
	 * //System.out.println("ENTRA AKI: " ); codigo =
	 * (String)mapaProductoCodigo.get(idProducto);
	 * //System.out.println("codigo: "+ codigo); } } if
	 * (idProveedorMapa.equals(idProveedor)){
	 * ArrayList<Map<Long,Map<String,String>>> listMapProductoCodigoEstado =
	 * mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved
	 * .get(idProveedorMapa); for (Map mapaProductoCodigoEstado:
	 * listMapProductoCodigoEstado){ if
	 * (mapaProductoCodigoEstado.get(idProducto) != null){ mapaCodigoEstado =
	 * new HashMap(); mapaCodigoEstado = (Map)
	 * mapaProductoCodigoEstado.get(idProducto); //COMENTED 28 JUNIO //codigo =
	 * (String)mapaCodigoEstado.keySet().iterator().next();
	 * //System.out.println("codigo CANAL: "+ codigo); } } } } //COMENTED AND
	 * ADD 28 JUNIO //return codigo; return mapaCodigoEstado; }catch (Exception
	 * e){ e.printStackTrace(); } //COMENTED AND ADD 28 JUNIO //return codigo;
	 * return mapaCodigoEstado; } //END COMENTED 5 SEPTIEMBRE END MODIFIED 28
	 * JUNIO END 21 JUNIO
	 */
	// COMENTED 28 JUNIO
	// ADD 21 JUNIO
	/*
	 * public String getCodigoOrdenMedioAgrupadaXProductoSaved(Long idProveedor,
	 * Long idProducto){ String codigo ="";
	 * 
	 * try{ //System.out.println("SIZE "+
	 * mapProductoClienteByProveedorCodigoOrdenMedioSaved.size()); //MODIFIED 27
	 * JUNIO //Iterator mapProductoByProveedorCodigoOrdenSavedTempIt =
	 * mapProductoClienteByProveedorCodigoOrdenMedioSaved.keySet().iterator();
	 * Iterator mapProductoByProveedorCodigoOrdenSavedTempIt =
	 * mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved
	 * .keySet().iterator();
	 * 
	 * while(mapProductoByProveedorCodigoOrdenSavedTempIt.hasNext()){ Long
	 * idProveedorMapa =
	 * (Long)mapProductoByProveedorCodigoOrdenSavedTempIt.next(); //MODIFIED 27
	 * JUNIO //ArrayList<Map<Long,String>> listMapProductoCodigo =
	 * mapProductoClienteByProveedorCodigoOrdenMedioSaved.get(idProveedorMapa);
	 * /*for (Map mapaProductoCodigo: listMapProductoCodigo){ if
	 * (mapaProductoCodigo.get(idProducto) != null){
	 * //System.out.println("ENTRA AKI: " ); codigo =
	 * (String)mapaProductoCodigo.get(idProducto);
	 * //System.out.println("codigo: "+ codigo); } }* /
	 * 
	 * ArrayList<Map<Long,Map<String,String>>> listMapProductoCodigo =
	 * mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved
	 * .get(idProveedorMapa); for (Map mapaProductoCodigoEstado:
	 * listMapProductoCodigo){ if (mapaProductoCodigoEstado.get(idProducto) !=
	 * null){ Map mapaCodigoEstado = (Map)
	 * mapaProductoCodigoEstado.get(idProducto); codigo =
	 * (String)mapaCodigoEstado.keySet().iterator().next();
	 * System.out.println("codigo CANAL: "+ codigo); } } } return codigo; }catch
	 * (Exception e){ e.printStackTrace(); } return codigo; }
	 */
	// END 21 JUNIO

	// MODIFIED 7 OCTUBRE
	/*
	 * Se crean las ordenes de Medio agrupadas por Productos con VERSION es
	 * decir en una Orden de Medio contendran varios comerciales en su detalle
	 * ejm Orden de Medio del Producto: Ecuacolor Version: A la cual tiene una
	 * duración de 10 seg Detalle de Orden de Medio DE LOS COMERCIALES: A CUÑA,
	 * A MENCION, A PRESENTACION, A DESPEDIDA LOS MAPAS DE ORDENES DE MEDIO
	 * SERAN SEGUN PRODUCTO CLIENTE
	 */
	private void crearOrdenesMedioAgrupadasXCampanaProductoVersion()
			throws GenericBusinessException {

		// ADD 28 JUNIO
		if (getMode() != SpiritMode.FIND_MODE) {
			planMedioIf.setEstado(ESTADO_APROBADO);
		}
		// END 28 JUNIO

		Collection<OrdenMedioIf> ordenMedioColl = SessionServiceLocator
				.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(
						planMedioIf.getId());

		if (ordenMedioColl.size() > 0 && !nuevoPlan) {
			// listaProveedoresMap->Map<Long,ClienteOficinaIf>
			// listaProveedoresMap = new HashMap<Long,ClienteOficinaIf>();
			listaProveedoresMap.clear();
			for (PlanMedioDetalleIf planMedioDetalle : planMedioDetallesPlantilla) {
				ClienteOficinaIf proveedor = mapaClienteOficina
						.get(planMedioDetalle.getProveedorId());
				listaProveedoresMap
						.put(String.valueOf(proveedor.getId())
								+ "-"
								+ String.valueOf(planMedioDetalle
										.getNumeroOrdenAgrupacion()), proveedor);
			}

			// ADD 11 MAYO
			// ArrayList<Long>listaProductosCliente = new ArrayList<Long>();
			// COMENTED 3 AGOSTO

			// COMENTED 10 OCTUBRE ADD 30 AGOSTO
			/*
			 * Map<Long,ArrayList<String>> mapaProductosClienteVersion = new
			 * LinkedHashMap<Long,ArrayList<String>>();
			 * ArrayList<String>listaVersiones = new ArrayList<String>();
			 */
			// END 30 AGOSTO

			// ADD 10 OCTUBRE
			Map<String, ArrayList<Long>> mapaProductosClienteVersion = new LinkedHashMap<String, ArrayList<Long>>();
			ArrayList<Long> listaVersiones = new ArrayList<Long>();
			// END 10 OCTUBRE

			Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioTotales = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
			Iterator ordenMedioIt = ordenMedioColl.iterator();
			while (ordenMedioIt.hasNext()) {
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt.next();

				// COMENTE 30 AGOSTO ADD 11 MAYO
				/*
				 * Long productoClienteId = ordenMedioIf.getProductoClienteId();
				 * 
				 * int tmp = 0; for(Long productoClienteIdtmp :
				 * listaProductosCliente){
				 * if(productoClienteIdtmp.compareTo(productoClienteId)!=0){
				 * tmp++; } }
				 * 
				 * if(tmp==listaProductosCliente.size()){
				 * listaProductosCliente.add(productoClienteId); }
				 */
				// END 11 MAYO

				// COMENTED 10 OCTUBRE ADD 30 AGOSTO
				/*
				 * Long productoClienteId = ordenMedioIf.getProductoClienteId();
				 * 
				 * String productoClienteVersion = "";
				 * Collection<OrdenMedioDetalleIf> ordenMedioDetalleIfColl =
				 * SessionServiceLocator.getOrdenMedioDetalleSessionService().
				 * findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId()); if
				 * (!ordenMedioDetalleIfColl.isEmpty()) productoClienteVersion =
				 * ordenMedioDetalleIfColl
				 * .iterator().next().getProductoClienteVersion();
				 * 
				 * if
				 * (mapaProductosClienteVersion.containsKey(productoClienteId)){
				 * listaVersiones =
				 * mapaProductosClienteVersion.get(productoClienteId); if
				 * (!listaVersiones.contains(productoClienteVersion)){
				 * listaVersiones.add(productoClienteVersion);
				 * mapaProductosClienteVersion.put(productoClienteId,
				 * listaVersiones); } }else{ listaVersiones.clear();
				 * listaVersiones.add(productoClienteVersion);
				 * mapaProductosClienteVersion.put(productoClienteId,
				 * listaVersiones); }
				 */
				// END 30 AGOSTO

				// ADD 10 OCTUBRE
				Long productoClienteId = ordenMedioIf.getProductoClienteId();
				String numeroOrdenProductoCliente = String.valueOf(ordenMedioIf
						.getNumeroOrdenAgrupacion());

				Long campanaProductoVersion = 0L;
				Collection<OrdenMedioDetalleIf> ordenMedioDetalleIfColl = SessionServiceLocator
						.getOrdenMedioDetalleSessionService()
						.findOrdenMedioDetalleByOrdenMedioId(
								ordenMedioIf.getId());
				if (!ordenMedioDetalleIfColl.isEmpty())
					campanaProductoVersion = ordenMedioDetalleIfColl.iterator()
							.next().getCampanaProductoVersionId();

				boolean existeProductoCliente = false;
				for (String productoCliente : mapaProductosClienteVersion
						.keySet()) {
					String[] key = productoCliente.split("-");
					Long idProductoCliente = Long.parseLong(key[0]);
					String numeroOrden = key[1];
					if (idProductoCliente.compareTo(productoClienteId) == 0
							&& numeroOrden.equals(numeroOrdenProductoCliente)) {
						listaVersiones = mapaProductosClienteVersion
								.get(String.valueOf(idProductoCliente) + "-"
										+ numeroOrden);

						boolean existeCampanaProductoVersion = false;
						for (Long idCampanaProductoVersion : listaVersiones) {
							if (idCampanaProductoVersion
									.compareTo(campanaProductoVersion) == 0) {
								existeCampanaProductoVersion = true;
								break;
							}
						}
						if (!existeCampanaProductoVersion) {
							listaVersiones.add(campanaProductoVersion);
						}
						mapaProductosClienteVersion.put(
								String.valueOf(productoClienteId) + "-"
										+ numeroOrdenProductoCliente,
								listaVersiones);
						existeProductoCliente = true;
						break;
					}
				}
				if (!existeProductoCliente) {
					ArrayList<Long> listaVersionesNueva = new ArrayList<Long>();
					listaVersionesNueva.add(campanaProductoVersion);
					mapaProductosClienteVersion.put(
							String.valueOf(productoClienteId) + "-"
									+ numeroOrdenProductoCliente,
							listaVersionesNueva);
				}
				// END 10 OCTUBRE

				Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleTotales = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();

				Collection<OrdenMedioDetalleIf> ordenMedioDetalleColl = SessionServiceLocator
						.getOrdenMedioDetalleSessionService()
						.findOrdenMedioDetalleByOrdenMedioId(
								ordenMedioIf.getId());
				Iterator ordenMedioDetalleIt = ordenMedioDetalleColl.iterator();
				while (ordenMedioDetalleIt.hasNext()) {
					OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt
							.next();

					ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = new ArrayList<OrdenMedioDetalleMapaIf>();
					Collection<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaColl = SessionServiceLocator
							.getOrdenMedioDetalleMapaSessionService()
							.findOrdenMedioDetalleMapaByOrdenMedioDetalleId(
									ordenMedioDetalleIf.getId());
					Iterator ordenMedioDetalleMapaIt = ordenMedioDetalleMapaColl
							.iterator();
					while (ordenMedioDetalleMapaIt.hasNext()) {
						OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf = (OrdenMedioDetalleMapaIf) ordenMedioDetalleMapaIt
								.next();
						ordenMedioDetalleMapaList.add(ordenMedioDetalleMapaIf);
					}
					ordenesMedioDetalleTotales.put(ordenMedioDetalleIf,
							ordenMedioDetalleMapaList);
				}
				ordenesMedioTotales.put(ordenMedioIf,
						ordenesMedioDetalleTotales);
			}
			// for(Long comercialIdtmp : listaComerciales){
			// COMENTED 30 AGOSTO
			/*
			 * for(Long productoClienteIdtmp : listaProductosCliente){ Iterator
			 * ordenMedioIt2 = ordenesMedioTotales.keySet().iterator(); Map
			 * <OrdenMedioIf,Map
			 * <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>
			 * ordenesMedioParcial = new LinkedHashMap<OrdenMedioIf,
			 * Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
			 * while(ordenMedioIt2.hasNext()){ OrdenMedioIf ordenMedioIf =
			 * (OrdenMedioIf)ordenMedioIt2.next(); //ADD 11 MAYO Long
			 * productoClienteId = ordenMedioIf.getProductoClienteId(); Map
			 * <OrdenMedioDetalleIf
			 * ,ArrayList<OrdenMedioDetalleMapaIf>>ordenesMedioDetalleMap=
			 * ordenesMedioTotales.get(ordenMedioIf); Iterator
			 * ordenMedioDetalleIt = ordenesMedioDetalleMap.keySet().iterator();
			 * if(ordenMedioDetalleIt.hasNext()){ OrdenMedioDetalleIf
			 * ordenMedioDetalleIf =
			 * (OrdenMedioDetalleIf)ordenMedioDetalleIt.next(); //Long
			 * comercialId = ordenMedioDetalleIf.getComercialId();
			 * if(productoClienteId.compareTo(productoClienteIdtmp)==0){
			 * //if(comercialId.compareTo(comercialIdtmp)==0){
			 * ordenesMedioParcial.put(ordenMedioIf, ordenesMedioDetalleMap);
			 * //ADD 31 MAYO //posibilidad de que vaya la lista temporal de
			 * observaciones //END 31 MAYO } } }
			 * //mapaComercialOrdenesComp.put(comercialIdtmp
			 * ,ordenesMedioParcial);
			 * mapaProductoClienteOrdenesComp.put(productoClienteIdtmp,
			 * ordenesMedioParcial); }
			 */// END COMENTED 30 AGOSTO

			// COMENTED 10 OCTUBRE ADD 30 AGOSTO
			/*
			 * for(Long productoClienteIdTmp :
			 * mapaProductosClienteVersion.keySet()){
			 * 
			 * //ADD 30 AGOSTO Map<String,Map<OrdenMedioIf,Map
			 * <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>>
			 * versionOrdenesMedioParcial = new LinkedHashMap<String,
			 * Map<OrdenMedioIf
			 * ,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>>();
			 * 
			 * for (String productoClienteVersionTmp :
			 * mapaProductosClienteVersion.get(productoClienteIdTmp)){ //ADD 30
			 * AGOSTO Iterator ordenMedioIt2 =
			 * ordenesMedioTotales.keySet().iterator(); Map <OrdenMedioIf,Map
			 * <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>
			 * ordenesMedioParcial = new LinkedHashMap<OrdenMedioIf,
			 * Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>();
			 * while(ordenMedioIt2.hasNext()){ OrdenMedioIf ordenMedioIf =
			 * (OrdenMedioIf)ordenMedioIt2.next(); //ADD 11 MAYO Long
			 * productoClienteId = ordenMedioIf.getProductoClienteId(); Map
			 * <OrdenMedioDetalleIf
			 * ,ArrayList<OrdenMedioDetalleMapaIf>>ordenesMedioDetalleMap=
			 * ordenesMedioTotales.get(ordenMedioIf); Iterator
			 * ordenMedioDetalleIt = ordenesMedioDetalleMap.keySet().iterator();
			 * if(ordenMedioDetalleIt.hasNext()){ OrdenMedioDetalleIf
			 * ordenMedioDetalleIf =
			 * (OrdenMedioDetalleIf)ordenMedioDetalleIt.next();
			 * 
			 * //ADD 30 AGOSTO String productoClienteVersion =
			 * ordenMedioDetalleIf.getProductoClienteVersion();
			 * 
			 * //if(productoClienteId.compareTo(productoClienteIdTmp)==0 ){
			 * COMENTED 30 AGOSTO
			 * 
			 * //ADD 30 AGOSTO if(
			 * productoClienteId.compareTo(productoClienteIdTmp) == 0 &&
			 * productoClienteVersion.compareTo(productoClienteVersionTmp) ==
			 * 0){
			 * 
			 * ordenesMedioParcial.put(ordenMedioIf, ordenesMedioDetalleMap); }
			 * } }
			 * 
			 * //ADD 30 AGOSTO
			 * versionOrdenesMedioParcial.put(productoClienteVersionTmp,
			 * ordenesMedioParcial); }
			 * 
			 * //mapaProductoClienteOrdenesComp.put(productoClienteIdtmp,
			 * ordenesMedioParcial); COMENTED 30 AGOSTO
			 * 
			 * //ADD 30 AGOSTO
			 * mapaProductoClienteVersionOrdenesComp.put(productoClienteIdTmp,
			 * versionOrdenesMedioParcial); }
			 */
			// END ADD 30 AGOSTO

			// ADD 10 OCTUBRE
			for (String productoClienteTmp : mapaProductosClienteVersion
					.keySet()) {
				String[] key = productoClienteTmp.split("-");
				Long productoClienteIdTmp = Long.parseLong(key[0]);
				String numeroOrdenTmp = key[1];
				Map<Long, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> versionOrdenesMedioParcial = new LinkedHashMap<Long, Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>>();

				for (Long campanaProductoVersionTmp : mapaProductosClienteVersion
						.get(String.valueOf(productoClienteIdTmp) + "-"
								+ numeroOrdenTmp)) {
					Iterator ordenMedioIt2 = ordenesMedioTotales.keySet()
							.iterator();
					Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioParcial = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
					while (ordenMedioIt2.hasNext()) {
						OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt2
								.next();

						Long productoClienteId = ordenMedioIf
								.getProductoClienteId();
						String numeroOrden = String.valueOf(ordenMedioIf
								.getNumeroOrdenAgrupacion());
						Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleMap = ordenesMedioTotales
								.get(ordenMedioIf);
						Iterator ordenMedioDetalleIt = ordenesMedioDetalleMap
								.keySet().iterator();
						if (ordenMedioDetalleIt.hasNext()) {
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt
									.next();

							// ADD 30 AGOSTO
							// String productoClienteVersion =
							// ordenMedioDetalleIf.getProductoClienteVersion();

							// ADD 10 OCTUBRE
							Long campanaProductoVersion = ordenMedioDetalleIf
									.getCampanaProductoVersionId();

							// if(productoClienteId.compareTo(productoClienteIdTmp)==0
							// ){ COMENTED 30 AGOSTO

							// COMENTED 10 OCTUBRE ADD 30 AGOSTO
							/*
							 * if(
							 * productoClienteId.compareTo(productoClienteIdTmp)
							 * == 0 && productoClienteVersion.compareTo(
							 * productoClienteVersionTmp) == 0){
							 * 
							 * ordenesMedioParcial.put(ordenMedioIf,
							 * ordenesMedioDetalleMap); }
							 */

							// ADD 10 OCTUBRE
							if (productoClienteId
									.compareTo(productoClienteIdTmp) == 0
									&& campanaProductoVersion
											.compareTo(campanaProductoVersionTmp) == 0
									&& numeroOrden.equals(numeroOrdenTmp)) {

								ordenesMedioParcial.put(ordenMedioIf,
										ordenesMedioDetalleMap);
							}
							// END 10 OCTUBRE
						}
					}
					// COMENTED 10 OCTUBRE
					// versionOrdenesMedioParcial.put(productoClienteVersionTmp,
					// ordenesMedioParcial);

					// ADD 10 OCTUBRE
					versionOrdenesMedioParcial.put(campanaProductoVersionTmp,
							ordenesMedioParcial);
				}
				// mapaProductoClienteOrdenesComp.put(productoClienteIdtmp,
				// ordenesMedioParcial); COMENTED 30 AGOSTO

				mapaProductoClienteVersionOrdenesComp.put(
						String.valueOf(productoClienteIdTmp) + "-"
								+ numeroOrdenTmp, versionOrdenesMedioParcial);
			}
			// END ADD 10 OCTUBRE
		} else {

			// Map <OrdenMedioIf,Map<OrdenMedioDetalleIf,
			// ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMediosMapComp
			ordenesMediosMapComp.clear();
			// Map<OrdenMedioIf,List<OrdenMedioDetalleIf>> ordenesMedioMap
			ordenesMedioMap.clear();

			// mapaProductoClienteOrdenesComp.clear(); COMENTED 31 AGOSTO
			// END 3 MAYO

			// ADD 30 AGOSTO nuevos mapas en lugar de los mapas del 3 MAYO de
			// arriba
			mapaProductoClienteVersionOrdenesComp.clear();

			proveedorValorSubtotal.clear();

			// NO SE UTILIZA POR ENDE 31 AGOSTO
			// mapProductoClienteByProveedorCodigoOrdenMedio.clear(); COMENTED
			// 31 AGOSTO

			// ADD 31 AGOSTO SOLO SE LA UTILIZA AKI!!!
			// END 31 AGOSTO

			BigDecimal valor_subtotal = new BigDecimal(0);
			BigDecimal valor_descuento = new BigDecimal(0);
			BigDecimal iva = new BigDecimal(0);
			BigDecimal valorDetalle = new BigDecimal(0);
			BigDecimal ivaDetalle = new BigDecimal(0);
			BigDecimal subtotal = new BigDecimal(0);

			BigDecimal valorSubtotalVenta1 = new BigDecimal(0);
			BigDecimal valorSubtotalVenta2 = new BigDecimal(0);
			BigDecimal valorDescuentoVenta = new BigDecimal(0);
			BigDecimal valorComisionAgencia = new BigDecimal(0);
			BigDecimal valorIvaVenta = new BigDecimal(0);

			UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
			OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = SessionServiceLocator
					.getOrdenTrabajoDetalleSessionService()
					.getOrdenTrabajoDetalle(
							planMedioIf.getOrdenTrabajoDetalleId());
			OrdenTrabajoIf ordenTrabajoIf = SessionServiceLocator
					.getOrdenTrabajoSessionService().getOrdenTrabajo(
							ordenTrabajoDetalleIf.getOrdenId());
			ClienteOficinaIf clienteOficina = mapaClienteOficina
					.get(ordenTrabajoIf.getClienteoficinaId());

			// Lista de Proveedores
			// Map<Long,ClienteOficinaIf> listaProveedoresMap
			listaProveedoresMap.clear();
			// Collection<PlanMedioDetalleIf> planMedioDetallesPlantilla
			for (PlanMedioDetalleIf planMedioDetalle : planMedioDetallesPlantilla) {
				ClienteOficinaIf proveedor = mapaClienteOficina
						.get(planMedioDetalle.getProveedorId());
				listaProveedoresMap
						.put(String.valueOf(proveedor.getId())
								+ "-"
								+ String.valueOf(planMedioDetalle
										.getNumeroOrdenAgrupacion()), proveedor);
			}

			// COMENTED 10 OCTUBRE ADD 30 AGOSTO
			// Map<String,List<Long>> productoClienteVersionProveedores;// = new
			// HashMap<String,List<Long>>();
			// Map<Long,Map<String,List<Long>>>
			// productoClienteIdVersionProveedores = new LinkedHashMap<Long,
			// Map<String,List<Long>>>();
			// END 30 AGOSTO

			// ADD 10 OCTUBRE
			Map<String, Map<Long, List<String>>> productoClienteVersionIdProveedores = new LinkedHashMap<String, Map<Long, List<String>>>();
			Map<Long, List<String>> campanaProductoVersionProveedores;// = new
																		// HashMap<String,List<Long>>();
			// END 10 OCTUBRE

			// agrega a todos los proveedores id de cada PlanMedioDetalle de
			// mapasComercialesPlantilla
			List<String> planMedioDetalleIdList;// = new ArrayList<Long>();
			// List<Long> planMedioDetalleIdList2 = new ArrayList<Long>();

			// aqui llamar a llenar
			// HashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>>
			// mapasComercialesPlantilla
			if (mapasComercialesPlantilla.isEmpty()) {
				llenarMapasComercialesPlantilla(planMedioIf, false);
			}

			Iterator mapasComercialesPlantillaIt = mapasComercialesPlantilla
					.keySet().iterator();
			// se llena la lista de comercialIdProveedores, map<long,Array> :
			// comercialId, Array idProveedores
			// GIOMY REVISAR AKI 15 MAYO
			while (mapasComercialesPlantillaIt.hasNext()) {
				PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf) mapasComercialesPlantillaIt
						.next();
				ComercialIf comercialIf = mapaComercial.get(planMedioDetalle.getComercialId());

				boolean existeIdProductoCliente = false;
				for (String productoCliente : productoClienteVersionIdProveedores
						.keySet()) {
					String[] keyProductoCliente = productoCliente.split("-");
					Long idProductoCliente = Long
							.parseLong(keyProductoCliente[0]);
					String numeroOrdenProductoCliente = keyProductoCliente[1];
					if (idProductoCliente.compareTo(comercialIf
							.getProductoClienteId()) == 0
							&& String
									.valueOf(
											planMedioDetalle
													.getNumeroOrdenAgrupacion())
									.equals(numeroOrdenProductoCliente)) {
						campanaProductoVersionProveedores = productoClienteVersionIdProveedores
								.get(String.valueOf(idProductoCliente) + "-"
										+ numeroOrdenProductoCliente);

						boolean existeIdCampanaProductoVersion = false;
						for (Long idCampanaProductoVersion : campanaProductoVersionProveedores
								.keySet()) {
							if (idCampanaProductoVersion.compareTo(comercialIf
									.getCampanaProductoVersionId()) == 0) {
								planMedioDetalleIdList = campanaProductoVersionProveedores
										.get(idCampanaProductoVersion);

								boolean existeIdProveedor = false;
								for (String proveedor : planMedioDetalleIdList) {
									String[] key = proveedor.split("-");
									Long idProveedor = Long.parseLong(key[0]);
									String numeroOrden = key[1];
									if (idProveedor.compareTo(planMedioDetalle
											.getProveedorId()) == 0
											&& numeroOrden
													.equals(String
															.valueOf(planMedioDetalle
																	.getNumeroOrdenAgrupacion()))) {
										existeIdProveedor = true;
										break;
									}
								}
								if (!existeIdProveedor) {
									planMedioDetalleIdList
											.add(String
													.valueOf(planMedioDetalle
															.getProveedorId())
													+ "-"
													+ String.valueOf(planMedioDetalle
															.getNumeroOrdenAgrupacion()));
								}

								campanaProductoVersionProveedores.put(
										idCampanaProductoVersion,
										planMedioDetalleIdList);
								existeIdCampanaProductoVersion = true;
								break;
							}
						}
						if (!existeIdCampanaProductoVersion) {
							planMedioDetalleIdList = new ArrayList<String>();

							planMedioDetalleIdList.add(String
									.valueOf(planMedioDetalle.getProveedorId())
									+ "-"
									+ String.valueOf(planMedioDetalle
											.getNumeroOrdenAgrupacion()));
							campanaProductoVersionProveedores.put(
									comercialIf.getCampanaProductoVersionId(),
									planMedioDetalleIdList);
						}

						productoClienteVersionIdProveedores.put(
								String.valueOf(idProductoCliente) + "-"
										+ numeroOrdenProductoCliente,
								campanaProductoVersionProveedores);
						existeIdProductoCliente = true;
						break;
					}
				}
				if (!existeIdProductoCliente) {
					campanaProductoVersionProveedores = new LinkedHashMap<Long, List<String>>();
					planMedioDetalleIdList = new ArrayList<String>();
					planMedioDetalleIdList.add(String.valueOf(planMedioDetalle
							.getProveedorId())
							+ "-"
							+ String.valueOf(planMedioDetalle
									.getNumeroOrdenAgrupacion()));
					campanaProductoVersionProveedores.put(
							comercialIf.getCampanaProductoVersionId(),
							planMedioDetalleIdList);
					productoClienteVersionIdProveedores.put(
							String.valueOf(comercialIf.getProductoClienteId())
									+ "-"
									+ String.valueOf(planMedioDetalle
											.getNumeroOrdenAgrupacion()),
							campanaProductoVersionProveedores);
				}
				// END 10 OCTUBRE
			}

			// AKI ME KEDE 30 AGOSTO

			// ADD 4 MAYO
			// Creacion automatica de las Ordenes de Compra
			// Iterator productoClienteIdProveedoresIt =
			// productoClienteIdProveedores.keySet().iterator(); COMENTED 31
			// AGOSTO
			// END 4 MAYO

			// ADD 31 AGOSTO
			// Iterator productoClienteIdVersionProveedoresIt =
			// productoClienteIdVersionProveedores.keySet().iterator();
			// END 31 AGOSTO

			// ADD 10 OCTUBRE
			Iterator productoClienteVersionIdProveedoresIt = productoClienteVersionIdProveedores
					.keySet().iterator();
			// END 10 OCTUBRE

			// ADD 5 MAYO
			// BigDecimal subtotalote = new BigDecimal(0);
			// END 5 MAYO

			// MODIFIED 4 MAYO
			// while(productoClienteIdProveedoresIt.hasNext()){ COMENTED 31
			// AGOSTO
			// END 4 MAYO

			// COMENTED 10 OCTUBRE ADD 31 AGOSTO
			// while(productoClienteIdVersionProveedoresIt.hasNext()){
			// ADD 10 OCTUBRE
			while (productoClienteVersionIdProveedoresIt.hasNext()) {

				// List<Long> clienteOficinaIdList =
				// productoClienteIdProveedores.get(productoClienteId); COMENTED
				// 31 AGOSTO
				// Long productoClienteId =
				// (Long)productoClienteIdVersionProveedoresIt.next(); COMENTED
				// 10 OCTUBRE
				String[] keyProductoCliente = ((String) productoClienteVersionIdProveedoresIt
						.next()).split("-");
				Long productoClienteId = Long.parseLong(keyProductoCliente[0]); // ADD
																				// 10
																				// OCTUBRE
				String numeroOrdenProductoCliente = keyProductoCliente[1];

				// COMENTED 10 OCTUBRE ADD 31 AGOSTO
				/*
				 * Map mapaVersionOrdenes = new
				 * LinkedHashMap<String,Map<OrdenMedioIf
				 * ,List<OrdenMedioDetalleIf>>>(); Map mapaVersionOrdenesComp =
				 * new
				 * LinkedHashMap<String,Map<OrdenMedioIf,List<OrdenMedioDetalleIf
				 * >>>();
				 * 
				 * Map mapProductoClienteVersionProveedores =
				 * productoClienteIdVersionProveedores.get(productoClienteId);
				 * Iterator productoClienteVersionProveedoresIt =
				 * mapProductoClienteVersionProveedores.keySet().iterator();
				 */

				// ADD 10 OCTUBRE
				Map mapaVersionOrdenes = new LinkedHashMap<Long, Map<OrdenMedioIf, List<OrdenMedioDetalleIf>>>();
				Map mapaVersionOrdenesComp = new LinkedHashMap<Long, Map<OrdenMedioIf, List<OrdenMedioDetalleIf>>>();

				Map mapProductoClienteVersionProveedores = productoClienteVersionIdProveedores
						.get(String.valueOf(productoClienteId) + "-"
								+ numeroOrdenProductoCliente);
				Iterator productoClienteVersionProveedoresIt = mapProductoClienteVersionProveedores
						.keySet().iterator();
				// END 10 OCTUBRE

				while (productoClienteVersionProveedoresIt.hasNext()) {

					// COMENTED 10 OCTUBRE ADD 31 AGOSTO
					// String productoClienteVersion =
					// (String)productoClienteVersionProveedoresIt.next();

					// ADD 10 OCTUBRE
					Long campanaProductoVersion = (Long) productoClienteVersionProveedoresIt
							.next();

					List<String> clienteOficinaIdList = (List<String>) mapProductoClienteVersionProveedores
							.get(campanaProductoVersion);

					int tmp = 0;

					// recorre el array de proveedores x comercialId
					for (String proveedor : clienteOficinaIdList) {
						// 5 MAYO
						BigDecimal subtotalOrdenProv = new BigDecimal(0);
						// END 5 MAYO

						// Map<Long,ClienteOficinaIf> listaProveedoresMap
						// Map<id, proveedor>
						String[] key = proveedor.split("-");
						Long proveedorId = Long.parseLong(key[0]);
						String numeroOrden = key[1];
						clienteOficinaIf = listaProveedoresMap.get(String
								.valueOf(proveedorId) + "-" + numeroOrden);

						valor_subtotal = new BigDecimal(0);
						valor_descuento = new BigDecimal(0);
						valorDescuentoVenta = new BigDecimal(0);
						valorComisionAgencia = new BigDecimal(0);

						// ORDENES DE MEDIO
						// ADD 3 DE MAYO
						OrdenMedioIf data = new OrdenMedioData();

						// 21 JUNIO
						// String codigo = getCodigo(new java.sql.Date(new
						// java.util.Date().getTime()));
						// data.setCodigo(codigo);
						// data.setPlanMedioId(planMedioIf.getId());
						data.setClienteOficinaId(clienteOficina.getId());
						// data.setCodigo(codigo);
						data.setOficinaId(Parametros.getIdOficina());
						data.setProveedorId(proveedorId);
						data.setOrdenMedioTipo(ordenMedioTipo);
						// ADD 4 MAYO
						data.setProductoClienteId(productoClienteId);
						// END 4 MAYO

						// ADD 10 OCTUBRE
						data.setCampanaProductoVersionId(campanaProductoVersion);
						// END 10 OCTUBRE

						// ADD 30 MAYO
						String observacion = "";
						data.setObservacion(observacion);
						// END 30 MAYO

						// COMENTED 10 OCTUBRE PERO YA NO SE UTILIZABA ADD 22
						// JUNIO
						// ArrayList<Map<Long, String>>
						// listMapProductoClienteCodigoOrden = new
						// ArrayList<Map<Long, String>>(); //ahora no se
						// utilizara
						// END 22 JUNIO

						// COMENTED 10 OCTUBRE ADD 31 AGOSTO
						// ArrayList<Map<Long, Map<String, String>>>
						// listMapProductoClienteVersionCodigoOrden = new
						// ArrayList<Map<Long,Map<String,String>>>();
						// END 31 AGOSTO
						// ADD 10 OCTUBRE
						ArrayList<Map<Long, Map<Long, String>>> listMapProductoClienteVersionCodigoOrden = new ArrayList<Map<Long, Map<Long, String>>>();
						// END 10 OCTUBRE

						// ADD 21 JUNIO
						// para agregar el codigo de la orden anterior que le
						// correspondia a este procucto del proveedor
						// a la orden nueva
						// data.setCodigo(getCodigoOrdenMedioAgrupadaXProductoSaved(proveedorId,productoClienteId));
						// MOVED 21 JUNIO
						// COMENTED 28 JUNIO
						/*
						 * String codigo =
						 * getCodigoOrdenMedioAgrupadaXProductoSaved
						 * (proveedorId,productoClienteId); if
						 * (codigo.compareTo("") == 0) codigo = getCodigo(new
						 * java.sql.Date(new java.util.Date().getTime()));
						 * 
						 * data.setCodigo(codigo);
						 */
						// END 21 JUNIO

						// COMENTED 5 SEPTIEMBRE MODIFIED 28 JUNIO obtengo el
						// codigo y estado de las ordenes guardadas en la DB por
						// PRODUCTO
						// Map<String,String> mapCodigoEstado =
						// getCodigoEstadoOrdenMedioAgrupadaXProductoSaved(proveedorId,productoClienteId);

						// COMENTED 11 OCTUBRE MODIFIED 5 SEPTIEMBRE 28 JUNIO
						// obtengo el codigo y estado de las ordenes guardadas
						// en la DB por VERSION DEL RODUCTO
						// Map<String,String> mapCodigoEstado =
						// getCodigoEstadoOrdenMedioAgrupadaXProductoSaved(proveedorId,productoClienteId,productoClienteVersion);

						// ADD 11 OCTUBRE
						Map<String, String> mapCodigoEstado = getCodigoEstadoOrdenMedioAgrupadaXCampanaProductoVersionSaved(
								proveedorId, productoClienteId, numeroOrden,
								campanaProductoVersion);

						String codigo = "";
						String estado = "";

						// ADD 28 JUNIO
						// LAS ORDENES QUE REEMPLAZAN A LAS ANTERIORES TENDRAN
						// EL MISMO CODIGO Y ESTADO
						if (mapCodigoEstado != null) {
							codigo = (String) mapCodigoEstado.keySet()
									.iterator().next();
							estado = mapCodigoEstado.get(codigo);
						} else {// LAS ORDENES NUEVAS TENDRAN NUEVO CODIGO Y
								// ESTADO ENVIADO
							codigo = getCodigo(new java.sql.Date(
									new java.util.Date().getTime()));
							//estado = ESTADO_ORDEN_ENVIADA;
							
							// desde ahora (abril 2013) sera "Emitida"
							estado = ESTADO_ORDEN_EMITIDA;
						}
						data.setCodigo(codigo);
						data.setEstado(estado);

						// ADD 22 JUNIO
						Map<Long, String> mapProductoClienteCodigo = new LinkedHashMap<Long, String>(); // ahorita
																										// ya
																										// no
																										// se
																										// utilizara
						// Long productoClienteId = data.getProductoClienteId();
						// String codigoOrdenMedio = data.getCodigo();
						// END 22 JUNIO

						// ADD 31 AGOSTO
						// Map<Long,Map<String,String>>
						// mapProductoClienteVersionCodigo = new
						// LinkedHashMap<Long, Map<String,String>>(); //donde
						// va???????
						// Map<String,String> mapVersionCodigoOrden = new
						// LinkedHashMap<String, String>();
						// END 31 AGOSTO
						// ADD 11 OCTUBRE
						Map<Long, Map<Long, String>> mapProductoClienteVersionCodigo = new LinkedHashMap<Long, Map<Long, String>>(); // donde
																																		// va???????
						Map<Long, String> mapVersionCodigoOrden = new LinkedHashMap<Long, String>();
						
						/*
						Map mapProductoPautaRegularTv = new HashMap();
						mapProductoPautaRegularTv.put("genericoId",
								genericoPautaRegular.getId());
						mapProductoPautaRegularTv.put("proveedorId",
								proveedorId);
						ProductoIf productoPautaRegularTv = (ProductoIf) SessionServiceLocator
								.getProductoSessionService()
								.findProductoByQuery(mapProductoPautaRegularTv)
								.iterator().next();
						*/
						Map<Long, ProductoIf> mapaProveedorProducto = mapaGenericoMapaProveedorProducto.get(genericoPautaRegular.getId());
						ProductoIf productoPautaRegularTv = mapaProveedorProducto.get(proveedorId);
						
						data.setProductoProveedorId(productoPautaRegularTv.getId());

						// El usuario y el empleado seran el mismo, el que esta
						// haciendo el presupuesto
						data.setEmpleadoId(SessionServiceLocator
								.getEmpleadoSessionService()
								.getEmpleado(usuario.getEmpleadoId()).getId());
						data.setUsuarioId(usuario.getId());

						// Las fechas todas seran la misma, la actual
						data.setFechaCreacion(Utilitarios
								.fromSqlDateToTimestamp(new java.sql.Date(
										new GregorianCalendar()
												.getTimeInMillis())));

						// MODIFIED 6 JULIO
						// data.setFechaOrden(Utilitarios.fromSqlDateToTimestamp(new
						// java.sql.Date(new
						// GregorianCalendar().getTimeInMillis())));
						data.setFechaOrden(planMedioIf.getFechaInicio());
						// GIOMY PREGUNTAR POR EL ESTADO DE CREACION DE LA ORDEN
						// MEDIO

						// COMENTED 28 JUNIO
						// El estado siempre sera orden "Enviada"
						// data.setEstado(ESTADO_ORDEN_ENVIADA);

						if (tmp == 0) {
							ordenesMedioMap = new LinkedHashMap<OrdenMedioIf, List<OrdenMedioDetalleIf>>();
							ordenesMediosMapComp = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
						}

						List<OrdenMedioDetalleIf> dataDetalleVector = new ArrayList<OrdenMedioDetalleIf>();
						Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> dataDetalleHash = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();

						BigDecimal porcentajeComisionAgenciaTmp = new BigDecimal(
								0);
						BigDecimal porcentajeBonificacionVentaTmp = new BigDecimal(
								0);
						BigDecimal porcentajeDescuentoVentaTmp = new BigDecimal(
								0);

						Iterator mapasComercialesPlantillaIt2 = mapasComercialesPlantilla
								.keySet().iterator();
						while (mapasComercialesPlantillaIt2.hasNext()) {
							PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf) mapasComercialesPlantillaIt2
									.next();
							
							ComercialIf comercialIfPlantilla = mapaComercial.get(planMedioDetalle.getComercialId());
							
							if (planMedioDetalle.getProveedorId().compareTo(
									proveedorId) == 0
									&& comercialIfPlantilla
											.getProductoClienteId().compareTo(
													productoClienteId) == 0
									&& comercialIfPlantilla
											.getCampanaProductoVersionId()
											.compareTo(campanaProductoVersion) == 0
									&& planMedioDetalle
											.getNumeroOrdenAgrupacion() == Integer
											.parseInt(numeroOrden)) { // END 11
																		// OCTUBRE

								if (dataDetalleHash.size() > 0) {
									OrdenMedioDetalleData dataDetalle = new OrdenMedioDetalleData();
									dataDetalle.setComercial(planMedioDetalle
											.getComercial());
									dataDetalle.setComercialId(planMedioDetalle
											.getComercialId());
									dataDetalle.setHora(planMedioDetalle
											.getHoraInicio());
									dataDetalle.setPrograma(planMedioDetalle
											.getPrograma());
									// ADD 1 JUNIO
									dataDetalle.setPauta(planMedioDetalle
											.getPauta());
									dataDetalle
											.setAuspicioDescripcion(planMedioDetalle
													.getAuspicioDescripcion());
									// END 1 JUNIO
									// ADD 22 AGOSTO
									dataDetalle
											.setProductoClienteId(comercialIfPlantilla
													.getProductoClienteId());
									// END 22 AGOSTO
									// ADD 31 AGOSTO
									// dataDetalle.setProductoClienteVersion(comercialIfPlantilla.getVersion());
									// COMENTED 11 OCTUBRE
									// END 31 AGOSTO

									// ADD 11 OCTUBRE
									dataDetalle
											.setCampanaProductoVersionId(comercialIfPlantilla
													.getCampanaProductoVersionId());
									// END 11 OCTUBRE

									int cuniasByOrdenMedioDetalle = 0;
									ArrayList<OrdenMedioDetalleMapaIf> dataDetalleMapaList = new ArrayList<OrdenMedioDetalleMapaIf>();
									Collection mapasComercial = mapasComercialesPlantilla
											.get(planMedioDetalle);
									Iterator mapasComercialIt = mapasComercial
											.iterator();
									while (mapasComercialIt.hasNext()) {
										MapaComercialIf mapaComercialIf = (MapaComercialIf) mapasComercialIt
												.next();
										OrdenMedioDetalleMapaData dataDetalleMapa = new OrdenMedioDetalleMapaData();
										dataDetalleMapa
												.setFecha(Utilitarios
														.fromSqlDateToTimestamp(mapaComercialIf
																.getFecha()));
										dataDetalleMapa
												.setFrecuencia(mapaComercialIf
														.getFrecuencia());
										dataDetalleMapaList
												.add(dataDetalleMapa);
										cuniasByOrdenMedioDetalle = cuniasByOrdenMedioDetalle
												+ mapaComercialIf
														.getFrecuencia();
									}
									BigDecimal valorDataDetalle = new BigDecimal(
											0);
									BigDecimal ivaDataDetalle = new BigDecimal(
											0);

									dataDetalle.setValorTarifa(planMedioDetalle
											.getValorTarifa());
									dataDetalle.setTotalCunias(planMedioDetalle
											.getTotalCunias());
									dataDetalle
											.setProductoProveedorId(planMedioDetalle
													.getProductoProveedorId());
									dataDetalle
											.setPorcentajeDescuento(planMedioDetalle
													.getPorcentajeDescuento());
									dataDetalle
											.setValorDescuento(planMedioDetalle
													.getValorDescuento());
									dataDetalle.setValorIva(planMedioDetalle
											.getValorIva());
									dataDetalle
											.setValorSubtotal(planMedioDetalle
													.getValorSubtotal());
									dataDetalle.setValorTotal(planMedioDetalle
											.getValorTotal());

									dataDetalle
											.setValorDescuentoVenta(planMedioDetalle
													.getValorDescuentoVenta());
									dataDetalle
											.setValorComisionAgencia(planMedioDetalle
													.getValorComisionAgencia());
									dataDetalle
											.setPorcentajeDescuentoVenta(planMedioDetalle
													.getPorcentajeDescuentoVenta());
									dataDetalle
											.setPorcentajeComisionAgencia(planMedioDetalle
													.getPorcentajeComisionAgencia());
									porcentajeComisionAgenciaTmp = planMedioDetalle
											.getPorcentajeComisionAgencia();
									porcentajeBonificacionVentaTmp = planMedioDetalle
											.getPorcentajeBonificacionVenta();
									porcentajeDescuentoVentaTmp = planMedioDetalle
											.getPorcentajeDescuentoVenta();

									dataDetalleHash.put(dataDetalle,
											dataDetalleMapaList);
									dataDetalleVector.add(dataDetalle);

									valor_subtotal = valor_subtotal
											.add(planMedioDetalle
													.getValorSubtotal());
									valor_descuento = valor_descuento
											.add(planMedioDetalle
													.getValorDescuento());

									valorDescuentoVenta = valorDescuentoVenta
											.add(planMedioDetalle
													.getValorDescuentoVenta());
									valorComisionAgencia = valorComisionAgencia
											.add(planMedioDetalle
													.getValorComisionAgencia());

								} else {// CUANDO EL TAMANIO DE dataDetalleHash
										// = 0
									OrdenMedioDetalleData dataDetalle = new OrdenMedioDetalleData();
									dataDetalle.setComercial(planMedioDetalle
											.getComercial());
									dataDetalle.setComercialId(planMedioDetalle
											.getComercialId());
									dataDetalle.setHora(planMedioDetalle
											.getHoraInicio());
									dataDetalle.setPrograma(planMedioDetalle
											.getPrograma());
									// ADD 1 JUNIO
									dataDetalle.setPauta(planMedioDetalle
											.getPauta());
									dataDetalle
											.setAuspicioDescripcion(planMedioDetalle
													.getAuspicioDescripcion());
									// END 1 JUNIO

									// ADD 22 AGOSTO
									dataDetalle
											.setProductoClienteId(comercialIfPlantilla
													.getProductoClienteId());
									// END 22 AGOSTO

									// ADD 31 AGOSTO
									// dataDetalle.setProductoClienteVersion(comercialIfPlantilla.getVersion());
									// COMENTED 11 OCTUBRE
									// END 31 AGOSTO

									// ADD 11 OCTUBRE
									dataDetalle
											.setCampanaProductoVersionId(comercialIfPlantilla
													.getCampanaProductoVersionId()); // COMENTED
																						// 11
																						// OCTUBRE
									// END 11 OCTUBRE

									int cuniasByOrdenMedioDetalle = 0;
									ArrayList<OrdenMedioDetalleMapaIf> dataDetalleMapaList = new ArrayList<OrdenMedioDetalleMapaIf>();
									Collection mapasComercial = mapasComercialesPlantilla
											.get(planMedioDetalle);
									Iterator mapasComercialIt = mapasComercial
											.iterator();
									while (mapasComercialIt.hasNext()) {
										MapaComercialIf mapaComercialIf = (MapaComercialIf) mapasComercialIt
												.next();
										OrdenMedioDetalleMapaData dataDetalleMapa = new OrdenMedioDetalleMapaData();
										dataDetalleMapa
												.setFecha(Utilitarios
														.fromSqlDateToTimestamp(mapaComercialIf
																.getFecha()));
										dataDetalleMapa
												.setFrecuencia(mapaComercialIf
														.getFrecuencia());
										dataDetalleMapaList
												.add(dataDetalleMapa);
										cuniasByOrdenMedioDetalle = cuniasByOrdenMedioDetalle
												+ mapaComercialIf
														.getFrecuencia();
									}
									BigDecimal valorDataDetalle = new BigDecimal(
											0);
									BigDecimal ivaDataDetalle = new BigDecimal(
											0);

									dataDetalle.setValorTarifa(planMedioDetalle
											.getValorTarifa());
									dataDetalle.setTotalCunias(planMedioDetalle
											.getTotalCunias());
									dataDetalle
											.setProductoProveedorId(planMedioDetalle
													.getProductoProveedorId());
									dataDetalle
											.setPorcentajeDescuento(planMedioDetalle
													.getPorcentajeDescuento());
									dataDetalle
											.setValorDescuento(planMedioDetalle
													.getValorDescuento());
									dataDetalle.setValorIva(planMedioDetalle
											.getValorIva());
									dataDetalle
											.setValorSubtotal(planMedioDetalle
													.getValorSubtotal());
									dataDetalle.setValorTotal(planMedioDetalle
											.getValorTotal());

									dataDetalle
											.setValorDescuentoVenta(planMedioDetalle
													.getValorDescuentoVenta());
									dataDetalle
											.setValorComisionAgencia(planMedioDetalle
													.getValorComisionAgencia());
									dataDetalle
											.setPorcentajeDescuentoVenta(planMedioDetalle
													.getPorcentajeDescuentoVenta());
									dataDetalle
											.setPorcentajeComisionAgencia(planMedioDetalle
													.getPorcentajeComisionAgencia());
									porcentajeComisionAgenciaTmp = planMedioDetalle
											.getPorcentajeComisionAgencia();
									porcentajeBonificacionVentaTmp = planMedioDetalle
											.getPorcentajeBonificacionVenta();
									porcentajeDescuentoVentaTmp = planMedioDetalle
											.getPorcentajeDescuentoVenta();
									dataDetalleHash.put(dataDetalle,
											dataDetalleMapaList);
									dataDetalleVector.add(dataDetalle);

									valor_subtotal = valor_subtotal
											.add(planMedioDetalle
													.getValorSubtotal());
									valor_descuento = valor_descuento
											.add(planMedioDetalle
													.getValorDescuento());

									valorDescuentoVenta = valorDescuentoVenta
											.add(planMedioDetalle
													.getValorDescuentoVenta());
									valorComisionAgencia = valorComisionAgencia
											.add(planMedioDetalle
													.getValorComisionAgencia());
								}
							}
						}

						data.setPorcentajeComisionAgencia(porcentajeComisionAgenciaTmp);
						data.setPorcentajeBonificacionVenta(porcentajeBonificacionVentaTmp);
						data.setPorcentajeDescuentoVenta(porcentajeDescuentoVentaTmp);

						data.setValorSubtotal(valor_subtotal);
						data.setValorDescuento(valor_descuento);

						subtotal = data.getValorSubtotal().subtract(
								valor_descuento);
						// ADD 5 MAYO
						// subtotalote = subtotalote.add(valor_subtotal);
						// END 5 MAYO
						ProductoIf productoProveedor = mapaProducto.get(productoPautaRegularTv.getId());
						GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
						iva = genericoProveedor.getCobraIva().equals("S") ? subtotal
								.multiply(new BigDecimal(Parametros.IVA / 100D))
								: BigDecimal.ZERO;
						data.setValorIva(iva);
						data.setValorTotal(subtotal.add(iva));

						data.setValorDescuentoVenta(valorDescuentoVenta);
						data.setValorComisionAgencia(valorComisionAgencia);
						data.setPorcentajeCanje(new BigDecimal(0));
						data.setNumeroOrdenAgrupacion(Integer
								.parseInt(numeroOrden));
						ordenesMediosMapComp.put(data, dataDetalleHash);
						ordenesMedioMap.put(data, dataDetalleVector);

						// ADD 31 MAYO
						// listObservacionesTemp.add(data.getObservacion());
						// END 31 MAYO

						// ADD 22 JUNIO
						// mapProductoClienteCodigo.put(data.getProductoClienteId(),
						// data.getCodigo()); COMENTED 31 AGOSTO
						// listMapProductoClienteCodigoOrden.add(mapProductoClienteCodigo);
						// COMENTED 31 AGOSTO
						// listCodigosOrdenesMedioTemp.add(codigoOrdenMedio);

						// COMENTED 11 OCTUBRE ADD 31 AGOSTO
						/*
						 * mapVersionCodigoOrden.put(productoClienteVersion,data.
						 * getCodigo()); //CREO QUE NO SE UTILIZA
						 * mapProductoClienteVersionCodigo
						 * .put(data.getProductoClienteId(),
						 * mapVersionCodigoOrden); //CREO QUE NO SE UTILIZA
						 * listMapProductoClienteVersionCodigoOrden
						 * .add(mapProductoClienteVersionCodigo);
						 */// CREO QUE NO SE UTILIZA
						// END 31 AGOSTO

						// MODIFIED 11 OCTUBRE ADD 31 AGOSTO
						mapVersionCodigoOrden.put(campanaProductoVersion,
								data.getCodigo());
						// CREO QUE NO SE UTILIZA
						mapProductoClienteVersionCodigo.put(
								data.getProductoClienteId(),
								mapVersionCodigoOrden); // CREO QUE NO SE
														// UTILIZA
						listMapProductoClienteVersionCodigoOrden
								.add(mapProductoClienteVersionCodigo); // CREO
																		// QUE
																		// NO SE
																		// UTILIZA
						// END 11 OCTUBRE

						// CREO QUE NO SE ESTABA UTILIZANDO ESTO :S
						// mapProductoClienteByProveedorCodigoOrdenMedio.put(proveedorId,listMapProductoClienteCodigoOrden);
						// COMENTED 31 AGOSTO
						// END 22 JUNIO

						// ADD 31 AGOSTO //CREO QUE NO SE UTILIZA ESTO
						// END AGOSTO

						tmp++;
					}

					// ADD 4 MAYO
					// mapaProductoClienteOrdenesComp.put(productoClienteId,
					// ordenesMediosMapComp); COMENTED 31 AGOSTO
					// END 4 MAYO

					// ADD 31 AGOSTO
					// mapaVersionOrdenes.put(productoClienteVersion,
					// ordenesMedioMap); COMENTED 11 OCTUBRE
					// mapaVersionOrdenesComp.put(productoClienteVersion,
					// ordenesMediosMapComp); COMENTED 11 OCTUBRE
					// END 31 AGOSTO

					// ADD 11 OCTUBRE
					mapaVersionOrdenes.put(campanaProductoVersion,
							ordenesMedioMap);
					mapaVersionOrdenesComp.put(campanaProductoVersion,
							ordenesMediosMapComp);
					// END 11 OCTUBRE

				}// ADD 31 AGOSTO

				// ADD 31 AGOSTO
				mapaProductoClienteVersionOrdenesComp.put(
						String.valueOf(productoClienteId) + "-"
								+ numeroOrdenProductoCliente,
						mapaVersionOrdenesComp);
				// END 31 AGOSTO

			}

			// System.out.println("SUBTOTAL orden: " + valor_subtotal);
			// System.out.println("SUBTOTALOTE: " + subtotalote);

		}

		// Map <OrdenMedioIf,Map<OrdenMedioDetalleIf,
		// ArrayList<OrdenMedioDetalleMapaIf>>> mapaComercialOrdenesCompCreadas;
		// //= new HashMap<OrdenMedioData,
		// Map<OrdenMedioDetalleData,ArrayList<OrdenMedioDetalleMapaIf>>>();
		// mapaComercialOrdenesCompCreadas =
		// SessionServiceLocator.getOrdenMedioSessionService().procesarOrdenMedio(planMedioIf,
		// mapaComercialOrdenesComp);
		// SpiritAlert.createAlert("Orden de Medio" /*+ ordenMedio.getCodigo()
		// +*/+ " guardada con éxito", SpiritAlert.INFORMATION);
		// Llamar a reporteOrdenMedio cuando se lo llame desde save y desde
		// update
		// reporteOrdenMedio(mapaComercialOrdenesCompCreadas);

		// ADD 11 MAYO
		// 6 MAYO
		Map<String, ArrayList<BigDecimal>> proveedorOrdenesSubtotal = new LinkedHashMap<String, ArrayList<BigDecimal>>();
		ArrayList<BigDecimal> ordenesSubtotales = new ArrayList<BigDecimal>();
		// 6 MAYO

		// COMENTED 31 AGOSTO
		/*
		 * Iterator mapasProductoOrdenesIt =
		 * mapaProductoClienteOrdenesComp.keySet().iterator();
		 * while(mapasProductoOrdenesIt.hasNext()){ Long idproducto =
		 * (Long)mapasProductoOrdenesIt.next(); Map ordenesProveedor =
		 * mapaProductoClienteOrdenesComp.get(idproducto);//* /
		 * 
		 * Iterator mapaOrdenesProveedorIt =
		 * ordenesProveedor.keySet().iterator();
		 * while(mapaOrdenesProveedorIt.hasNext()){ OrdenMedioIf ordenMedio =
		 * (OrdenMedioIf)mapaOrdenesProveedorIt.next(); Long idProveedor =
		 * ordenMedio.getProveedorId();
		 * 
		 * if (proveedorOrdenesSubtotal.get(idProveedor)== null){
		 * ordenesSubtotales = new ArrayList<BigDecimal>();
		 * ordenesSubtotales.add(ordenMedio.getValorSubtotal()); }else{
		 * ordenesSubtotales =
		 * proveedorOrdenesSubtotal.get(idProveedor);//.add(ordenMedio
		 * .getValorSubtotal()); proveedorOrdenesSubtotal.remove(idProveedor);
		 * ordenesSubtotales.add(ordenMedio.getValorSubtotal()); }
		 * 
		 * proveedorOrdenesSubtotal.put(idProveedor, ordenesSubtotales); } }
		 */

		// COMENTED 11 OCTUBRE ADD 31 AGOSTO
		/*
		 * Iterator mapaProductoClienteVersionOrdenesIt =
		 * mapaProductoClienteVersionOrdenesComp.keySet().iterator();
		 * while(mapaProductoClienteVersionOrdenesIt.hasNext()){ Long idproducto
		 * = (Long)mapaProductoClienteVersionOrdenesIt.next();
		 * 
		 * Map mapVersionOrdenesComp =
		 * mapaProductoClienteVersionOrdenesComp.get(idproducto);//* / Iterator
		 * mapVersionOrdenesCompIt = mapVersionOrdenesComp.keySet().iterator();
		 * 
		 * //ADD 31 AGOSTO while(mapVersionOrdenesCompIt.hasNext()){ String
		 * versionProducto = (String) mapVersionOrdenesCompIt.next();
		 * 
		 * Map ordenesProveedor = (Map)
		 * mapVersionOrdenesComp.get(versionProducto);//* /
		 * 
		 * Iterator mapaOrdenesProveedorIt =
		 * ordenesProveedor.keySet().iterator();
		 * while(mapaOrdenesProveedorIt.hasNext()){ OrdenMedioIf ordenMedio =
		 * (OrdenMedioIf)mapaOrdenesProveedorIt.next(); Long idProveedor =
		 * ordenMedio.getProveedorId();
		 * 
		 * if (proveedorOrdenesSubtotal.get(idProveedor)== null){
		 * ordenesSubtotales = new ArrayList<BigDecimal>();
		 * ordenesSubtotales.add(ordenMedio.getValorSubtotal()); }else{
		 * ordenesSubtotales =
		 * proveedorOrdenesSubtotal.get(idProveedor);//.add(ordenMedio
		 * .getValorSubtotal()); proveedorOrdenesSubtotal.remove(idProveedor);
		 * ordenesSubtotales.add(ordenMedio.getValorSubtotal()); }
		 * 
		 * proveedorOrdenesSubtotal.put(idProveedor, ordenesSubtotales); }
		 * }//ADD 31 AGOSTO }
		 */
		// END 31 AGOSTO

		// ADD 11 OCTUBRE
		Iterator mapaProductoClienteVersionOrdenesIt = mapaProductoClienteVersionOrdenesComp
				.keySet().iterator();
		while (mapaProductoClienteVersionOrdenesIt.hasNext()) {
			String[] key = ((String) mapaProductoClienteVersionOrdenesIt.next())
					.split("-");
			Long idproducto = Long.parseLong(key[0]);
			String numeroOrden = key[1];

			Map mapVersionOrdenesComp = mapaProductoClienteVersionOrdenesComp
					.get(String.valueOf(idproducto) + "-" + numeroOrden);// * /
			Iterator mapVersionOrdenesCompIt = mapVersionOrdenesComp.keySet()
					.iterator();

			// ADD 31 AGOSTO
			while (mapVersionOrdenesCompIt.hasNext()) {
				// String versionProducto = (String)
				// mapVersionOrdenesCompIt.next(); COMENTED 11 OCTUBRE
				Long versionProducto = (Long) mapVersionOrdenesCompIt.next(); // ADD
																				// 11
																				// OCTUBRE

				Map ordenesProveedor = (Map) mapVersionOrdenesComp
						.get(versionProducto);// * /

				Iterator mapaOrdenesProveedorIt = ordenesProveedor.keySet()
						.iterator();
				while (mapaOrdenesProveedorIt.hasNext()) {
					OrdenMedioIf ordenMedio = (OrdenMedioIf) mapaOrdenesProveedorIt
							.next();
					Long idProveedor = ordenMedio.getProveedorId();

					if (proveedorOrdenesSubtotal.get(String
							.valueOf(idProveedor)
							+ "-"
							+ String.valueOf(ordenMedio
									.getNumeroOrdenAgrupacion())) == null) {
						ordenesSubtotales = new ArrayList<BigDecimal>();
						ordenesSubtotales.add(ordenMedio.getValorSubtotal());
					} else {
						ordenesSubtotales = proveedorOrdenesSubtotal.get(String
								.valueOf(idProveedor)
								+ "-"
								+ String.valueOf(ordenMedio
										.getNumeroOrdenAgrupacion()));// .add(ordenMedio.getValorSubtotal());
						proveedorOrdenesSubtotal.remove(String
								.valueOf(idProveedor)
								+ "-"
								+ String.valueOf(ordenMedio
										.getNumeroOrdenAgrupacion()));
						ordenesSubtotales.add(ordenMedio.getValorSubtotal());
					}

					proveedorOrdenesSubtotal.put(
							String.valueOf(idProveedor)
									+ "-"
									+ String.valueOf(ordenMedio
											.getNumeroOrdenAgrupacion()),
							ordenesSubtotales);
				}
			}// ADD 31 AGOSTO
		}
		// END 11 OCTUBRE

		Iterator proveedorSubtotalIt2 = proveedorOrdenesSubtotal.keySet()
				.iterator();
		while (proveedorSubtotalIt2.hasNext()) {
			String[] key = ((String) proveedorSubtotalIt2.next()).split("-");
			Long idProveedorTemp = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<BigDecimal> listica = proveedorOrdenesSubtotal.get(String
					.valueOf(idProveedorTemp) + "-" + numeroOrden);
			BigDecimal subtotalProveedor = new BigDecimal(0);
			for (int i = 0; i < listica.size(); i++) {
				subtotalProveedor = subtotalProveedor.add(listica.get(i));
			}
			// Map<Long,BigDecimal> proveedorValorSubtotal = new HashMap<Long,
			// BigDecimal>();
			proveedorValorSubtotal.put(String.valueOf(idProveedorTemp) + "-"
					+ numeroOrden, subtotalProveedor);
			System.out.println("proveedor: " + idProveedorTemp + "SUBTOTAL  "
					+ subtotalProveedor);
		}

	}

	// END ADD 3 MAYO
	// END MODIFIED 7 OCTUBRE

	// ADD 13 OCTUBRE
	/*
	 * Se crean las ordenes de Medio agrupadas por Productos es decir en una
	 * Orden de Medio contendran varios comerciales en su detalle ejm Orden de
	 * Medio del Producto: Ecuacolor A Detalle de Orden de Medio DE LOS
	 * COMERCIALES: A CUÑA, A MENCION, A PRESENTACION, A DESPEDIDA LOS MAPAS DE
	 * ORDENES DE MEDIO SERAN SEGUN PRODUCTO CLIENTE
	 */
	private void crearOrdenesMedioAgrupadasXProductoCliente()
			throws GenericBusinessException {
		// MODIFIED 28 JUNIO
		// planMedioIf.setEstado(ESTADO_APROBADO);

		// ADD 28 JUNIO
		if (getMode() != SpiritMode.FIND_MODE) {
			planMedioIf.setEstado(ESTADO_APROBADO);
		}
		// END 28 JUNIO

		Collection<OrdenMedioIf> ordenMedioColl = SessionServiceLocator
				.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(
						planMedioIf.getId());

		if (ordenMedioColl.size() > 0 && !nuevoPlan) {
			// listaProveedoresMap->Map<Long,ClienteOficinaIf>
			// listaProveedoresMap = new HashMap<Long,ClienteOficinaIf>();
			listaProveedoresMap.clear();
			for (PlanMedioDetalleIf planMedioDetalle : planMedioDetallesPlantilla) {
				ClienteOficinaIf proveedor = mapaClienteOficina
						.get(planMedioDetalle.getProveedorId());
				listaProveedoresMap
						.put(String.valueOf(proveedor.getId())
								+ "-"
								+ String.valueOf(planMedioDetalle
										.getNumeroOrdenAgrupacion()), proveedor);
			}

			// ADD 11 MAYO
			ArrayList<String> listaProductosCliente = new ArrayList<String>();

			// ArrayList<Long>listaComerciales = new ArrayList<Long>();

			Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioTotales = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
			Iterator ordenMedioIt = ordenMedioColl.iterator();
			while (ordenMedioIt.hasNext()) {
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt.next();

				// ADD 11 MAYO
				Long productoClienteId = ordenMedioIf.getProductoClienteId();
				String numeroOrden = String.valueOf(ordenMedioIf
						.getNumeroOrdenAgrupacion());
				int tmp = 0;
				for (String productoClienteTmp : listaProductosCliente) {
					String[] key = productoClienteTmp.split("-");
					Long productoClienteIdtmp = Long.parseLong(key[0]);
					String numeroOrdenTmp = key[1];
					if (productoClienteIdtmp.compareTo(productoClienteId) != 0
							|| (productoClienteIdtmp
									.compareTo(productoClienteId) == 0 && !numeroOrdenTmp
									.equals(numeroOrden))) {
						tmp++;
					}
				}

				if (tmp == listaProductosCliente.size()) {
					listaProductosCliente.add(String.valueOf(productoClienteId)
							+ "-" + numeroOrden);
				}
				// END 11 MAYO

				Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleTotales = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();

				Collection<OrdenMedioDetalleIf> ordenMedioDetalleColl = SessionServiceLocator
						.getOrdenMedioDetalleSessionService()
						.findOrdenMedioDetalleByOrdenMedioId(
								ordenMedioIf.getId());
				Iterator ordenMedioDetalleIt = ordenMedioDetalleColl.iterator();
				while (ordenMedioDetalleIt.hasNext()) {
					OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt
							.next();

					ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = new ArrayList<OrdenMedioDetalleMapaIf>();
					Collection<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaColl = SessionServiceLocator
							.getOrdenMedioDetalleMapaSessionService()
							.findOrdenMedioDetalleMapaByOrdenMedioDetalleId(
									ordenMedioDetalleIf.getId());
					Iterator ordenMedioDetalleMapaIt = ordenMedioDetalleMapaColl
							.iterator();
					while (ordenMedioDetalleMapaIt.hasNext()) {
						OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf = (OrdenMedioDetalleMapaIf) ordenMedioDetalleMapaIt
								.next();
						ordenMedioDetalleMapaList.add(ordenMedioDetalleMapaIf);
					}
					ordenesMedioDetalleTotales.put(ordenMedioDetalleIf,
							ordenMedioDetalleMapaList);
				}
				ordenesMedioTotales.put(ordenMedioIf,
						ordenesMedioDetalleTotales);
			}
			// for(Long comercialIdtmp : listaComerciales){
			for (String productoClienteTmp : listaProductosCliente) {
				String[] key = productoClienteTmp.split("-");
				Long productoClienteIdtmp = Long.parseLong(key[0]);
				String numeroOrdenTmp = key[1];
				Iterator ordenMedioIt2 = ordenesMedioTotales.keySet()
						.iterator();
				Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> ordenesMedioParcial = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
				while (ordenMedioIt2.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt2
							.next();
					// ADD 11 MAYO
					Long productoClienteId = ordenMedioIf
							.getProductoClienteId();
					String numeroOrden = String.valueOf(ordenMedioIf
							.getNumeroOrdenAgrupacion());
					Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenesMedioDetalleMap = ordenesMedioTotales
							.get(ordenMedioIf);
					Iterator ordenMedioDetalleIt = ordenesMedioDetalleMap
							.keySet().iterator();
					if (ordenMedioDetalleIt.hasNext()) {
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetalleIt
								.next();
						// Long comercialId =
						// ordenMedioDetalleIf.getComercialId();
						if (productoClienteId.compareTo(productoClienteIdtmp) == 0
								&& numeroOrdenTmp.equals(numeroOrden)) {
							// if(comercialId.compareTo(comercialIdtmp)==0){
							ordenesMedioParcial.put(ordenMedioIf,
									ordenesMedioDetalleMap);
							// ADD 31 MAYO
							// posibilidad de que vaya la lista temporal de
							// observaciones
							// END 31 MAYO
						}
					}
				}
				// mapaComercialOrdenesComp.put(comercialIdtmp,ordenesMedioParcial);
				mapaProductoClienteOrdenesComp.put(
						String.valueOf(productoClienteIdtmp) + "-"
								+ numeroOrdenTmp, ordenesMedioParcial);
			}
		} else {

			ordenesMediosMapComp.clear();
			ordenesMedioMap.clear();
			mapOrdenMediobyProveedor.clear();
			mapOrdenMediobyProveedorPCanje.clear();
			mapOrdenMediobyProveedorPDesc.clear();
			mapOrdenMediobyProveedorPBono.clear();

			// ADD 3 DE MAYO
			// variables para Ordenes de Medio de Productos con sus respectivos
			// Comerciales
			// Map<Long,Map <OrdenMedioIf,Map<OrdenMedioDetalleIf,
			// ArrayList<OrdenMedioDetalleMapaIf>>>>
			// mapaProductoClienteOrdenesComp
			mapaProductoClienteOrdenesComp.clear();
			// END 3 MAYO

			proveedorValorSubtotal.clear();

			// ADD 26 MAYO
			mapOrdenMediobyProveedorObservacion.clear();
			// END 26 MAYO
			// ADD 31 MAYO
			// listObservacionesTemp.clear();
			// END 31 MAYO
			// ADD 22 JUNIO
			// listCodigosOrdenesMedioTemp.clear();
			mapProductoClienteByProveedorCodigoOrdenMedio.clear();
			// END 22 JUNIO

			BigDecimal valor_subtotal = new BigDecimal(0);
			BigDecimal valor_descuento = new BigDecimal(0);
			BigDecimal iva = new BigDecimal(0);
			BigDecimal valorDetalle = new BigDecimal(0);
			BigDecimal ivaDetalle = new BigDecimal(0);
			BigDecimal subtotal = new BigDecimal(0);

			BigDecimal valorSubtotalVenta1 = new BigDecimal(0);
			BigDecimal valorSubtotalVenta2 = new BigDecimal(0);
			BigDecimal valorDescuentoVenta = new BigDecimal(0);
			BigDecimal valorComisionAgencia = new BigDecimal(0);
			BigDecimal valorIvaVenta = new BigDecimal(0);

			UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
			OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = SessionServiceLocator
					.getOrdenTrabajoDetalleSessionService()
					.getOrdenTrabajoDetalle(
							planMedioIf.getOrdenTrabajoDetalleId());
			OrdenTrabajoIf ordenTrabajoIf = SessionServiceLocator
					.getOrdenTrabajoSessionService().getOrdenTrabajo(
							ordenTrabajoDetalleIf.getOrdenId());
			ClienteOficinaIf clienteOficina = mapaClienteOficina
					.get(ordenTrabajoIf.getClienteoficinaId());

			// Lista de Proveedores
			// Map<Long,ClienteOficinaIf> listaProveedoresMap
			listaProveedoresMap.clear();
			// Collection<PlanMedioDetalleIf> planMedioDetallesPlantilla
			for (PlanMedioDetalleIf planMedioDetalle : planMedioDetallesPlantilla) {
				ClienteOficinaIf proveedor = mapaClienteOficina
						.get(planMedioDetalle.getProveedorId());
				listaProveedoresMap
						.put(String.valueOf(proveedor.getId())
								+ "-"
								+ String.valueOf(planMedioDetalle
										.getNumeroOrdenAgrupacion()), proveedor);
			}
			// ADD 4 MAYO
			Map<String, List<String>> productoClienteIdProveedores = new HashMap<String, List<String>>();
			// END 4 MAYO

			// agrega a todos los proveedores id de cada PlanMedioDetalle de
			// mapasComercialesPlantilla
			List<String> planMedioDetalleIdList = new ArrayList<String>();
			// List<Long> planMedioDetalleIdList2 = new ArrayList<Long>();

			// aqui llamar a llenar
			// HashMap<PlanMedioDetalleIf, Collection<MapaComercialIf>>
			// mapasComercialesPlantilla
			if (mapasComercialesPlantilla.isEmpty()) {
				llenarMapasComercialesPlantilla(planMedioIf, false);
			}

			Iterator mapasComercialesPlantillaIt = mapasComercialesPlantilla
					.keySet().iterator();
			// se llena la lista de comercialIdProveedores, map<long,Array> :
			// comercialId, Array idProveedores
			// GIOMY REVISAR AKI 15 MAYO
			while (mapasComercialesPlantillaIt.hasNext()) {
				PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf) mapasComercialesPlantillaIt
						.next();
				
				ComercialIf comercialIf = mapaComercial.get(planMedioDetalle.getComercialId());

				if (productoClienteIdProveedores.get(String.valueOf(comercialIf
						.getProductoClienteId())
						+ "-"
						+ String.valueOf(planMedioDetalle
								.getNumeroOrdenAgrupacion())) == null) {
					planMedioDetalleIdList = new ArrayList<String>();
					if (!planMedioDetalleIdList.contains(String
							.valueOf(planMedioDetalle.getProveedorId())
							+ "-"
							+ String.valueOf(planMedioDetalle
									.getNumeroOrdenAgrupacion()))) {
						// agrega a todos los proveedores id de cada
						// PlanMedioDetalle de mapasComercialesPlantilla
						planMedioDetalleIdList.add(String
								.valueOf(planMedioDetalle.getProveedorId())
								+ "-"
								+ planMedioDetalle.getNumeroOrdenAgrupacion());
					}
				} else {// akiii
					planMedioDetalleIdList = productoClienteIdProveedores
							.get(String.valueOf(comercialIf
									.getProductoClienteId())
									+ "-"
									+ String.valueOf(planMedioDetalle
											.getNumeroOrdenAgrupacion()));
					if (!planMedioDetalleIdList.contains(String
							.valueOf(planMedioDetalle.getProveedorId())
							+ "-"
							+ String.valueOf(planMedioDetalle
									.getNumeroOrdenAgrupacion()))) {
						// agrega a todos los proveedores id de cada
						// PlanMedioDetalle de mapasComercialesPlantilla
						planMedioDetalleIdList.add(String
								.valueOf(planMedioDetalle.getProveedorId())
								+ "-"
								+ planMedioDetalle.getNumeroOrdenAgrupacion());
					}
				}
				// Comercial Id, lista de Proveedores Id
				productoClienteIdProveedores.put(
						String.valueOf(comercialIf.getProductoClienteId())
								+ "-"
								+ String.valueOf(planMedioDetalle
										.getNumeroOrdenAgrupacion()),
						planMedioDetalleIdList);
				// END 4 MAYO
			}

			// ADD 4 MAYO
			// Creacion automatica de las Ordenes de Compra
			Iterator productoClienteIdProveedoresIt = productoClienteIdProveedores
					.keySet().iterator();
			// END 4 MAYO

			// ADD 5 MAYO
			// BigDecimal subtotalote = new BigDecimal(0);
			// END 5 MAYO

			// MODIFIED 4 MAYO
			while (productoClienteIdProveedoresIt.hasNext()) {
				// END 4 MAYO
				String[] keyProductoCliente = ((String) productoClienteIdProveedoresIt
						.next()).split("-");
				Long productoClienteId = Long.parseLong(keyProductoCliente[0]);
				String numeroOrdenProductoCliente = keyProductoCliente[1];
				List<String> clienteOficinaIdList = productoClienteIdProveedores
						.get(String.valueOf(productoClienteId) + "-"
								+ numeroOrdenProductoCliente);

				int tmp = 0;

				// recorre el array de proveedores x comercialId
				for (String proveedor : clienteOficinaIdList) {
					// 5 MAYO
					BigDecimal subtotalOrdenProv = new BigDecimal(0);
					// END 5 MAYO

					// Map<Long,ClienteOficinaIf> listaProveedoresMap
					// Map<id, proveedor>
					String[] key = proveedor.split("-");
					Long proveedorId = Long.parseLong(key[0]);
					String numeroOrden = key[1];
					clienteOficinaIf = listaProveedoresMap.get(String
							.valueOf(proveedorId) + "-" + numeroOrden);

					valor_subtotal = new BigDecimal(0);
					valor_descuento = new BigDecimal(0);
					valorDescuentoVenta = new BigDecimal(0);
					valorComisionAgencia = new BigDecimal(0);

					// ORDENES DE MEDIO
					// ADD 3 DE MAYO
					OrdenMedioIf data = new OrdenMedioData();

					// 21 JUNIO
					// String codigo = getCodigo(new java.sql.Date(new
					// java.util.Date().getTime()));
					// data.setCodigo(codigo);
					// data.setPlanMedioId(planMedioIf.getId());
					data.setClienteOficinaId(clienteOficina.getId());
					// data.setCodigo(codigo);
					data.setOficinaId(Parametros.getIdOficina());
					data.setProveedorId(proveedorId);
					data.setOrdenMedioTipo(ordenMedioTipo);
					// ADD 4 MAYO
					data.setProductoClienteId(productoClienteId);
					// END 4 MAYO

					// ADD 30 MAYO
					String observacion = "";
					data.setObservacion(observacion);
					// END 30 MAYO

					// ADD 22 JUNIO
					ArrayList<Map<Long, String>> listMapProductoClienteCodigoOrden = new ArrayList<Map<Long, String>>();
					// END 22 JUNIO

					// ADD 21 JUNIO
					// para agregar el codigo de la orden anterior que le
					// correspondia a este procucto del proveedor
					// a la orden nueva
					// data.setCodigo(getCodigoOrdenMedioAgrupadaXProductoSaved(proveedorId,productoClienteId));
					// MOVED 21 JUNIO
					// COMENTED 28 JUNIO
					/*
					 * String codigo =
					 * getCodigoOrdenMedioAgrupadaXProductoSaved(
					 * proveedorId,productoClienteId); if (codigo.compareTo("")
					 * == 0) codigo = getCodigo(new java.sql.Date(new
					 * java.util.Date().getTime()));
					 * 
					 * data.setCodigo(codigo);
					 */
					// END 21 JUNIO

					// MODIFIED 28 JUNIO
					Map<String, String> mapCodigoEstado = getCodigoEstadoOrdenMedioAgrupadaXProductoSaved(
							proveedorId, numeroOrden, productoClienteId);
					String codigo = "";
					String estado = "";
					// ADD 28 JUNIO
					// LAS ORDENES QUE REEMPLAZAN A LAS ANTERIORES TENDRAN EL
					// MISMO CODIGO Y ESTADO
					if (mapCodigoEstado != null) {
						codigo = (String) mapCodigoEstado.keySet().iterator()
								.next();
						estado = mapCodigoEstado.get(codigo);
					} else {// LAS ORDENES NUEVAS TENDRAN NUEVO CODIGO Y ESTADO
							// ENVIADO
						codigo = getCodigo(new java.sql.Date(
								new java.util.Date().getTime()));
						//estado = ESTADO_ORDEN_ENVIADA;
						
						// desde ahora (abril 2013) sera "Emitida"
						estado = ESTADO_ORDEN_EMITIDA;
					}
					data.setCodigo(codigo);
					data.setEstado(estado);

					Map<Long, String> mapProductoClienteCodigo = new LinkedHashMap<Long, String>();
					
					/*
					Map mapProductoPautaRegularTv = new HashMap();
					mapProductoPautaRegularTv.put("genericoId",
							genericoPautaRegular.getId());
					mapProductoPautaRegularTv.put("proveedorId", proveedorId);
					ProductoIf productoPautaRegularTv = (ProductoIf) SessionServiceLocator
							.getProductoSessionService()
							.findProductoByQuery(mapProductoPautaRegularTv)
							.iterator().next();
					*/
					Map<Long, ProductoIf> mapaProveedorProducto = mapaGenericoMapaProveedorProducto.get(genericoPautaRegular.getId());
					ProductoIf productoPautaRegularTv = mapaProveedorProducto.get(proveedorId);
										
					data.setProductoProveedorId(productoPautaRegularTv.getId());

					// El usuario y el empleado seran el mismo, el que esta
					// haciendo el presupuesto
					data.setEmpleadoId(SessionServiceLocator
							.getEmpleadoSessionService()
							.getEmpleado(usuario.getEmpleadoId()).getId());
					data.setUsuarioId(usuario.getId());

					// Las fechas todas seran la misma, la actual
					data.setFechaCreacion(Utilitarios
							.fromSqlDateToTimestamp(new java.sql.Date(
									new GregorianCalendar().getTimeInMillis())));

					// MODIFIED 6 JULIO
					// data.setFechaOrden(Utilitarios.fromSqlDateToTimestamp(new
					// java.sql.Date(new
					// GregorianCalendar().getTimeInMillis())));
					data.setFechaOrden(planMedioIf.getFechaInicio());
					// GIOMY PREGUNTAR POR EL ESTADO DE CREACION DE LA ORDEN
					// MEDIO

					// COMENTED 28 JUNIO
					// El estado siempre sera orden "Enviada"
					// data.setEstado(ESTADO_ORDEN_ENVIADA);

					if (tmp == 0) {
						ordenesMedioMap = new LinkedHashMap<OrdenMedioIf, List<OrdenMedioDetalleIf>>();
						ordenesMediosMapComp = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
					}

					List<OrdenMedioDetalleIf> dataDetalleVector = new ArrayList<OrdenMedioDetalleIf>();
					Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> dataDetalleHash = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();

					BigDecimal porcentajeComisionAgenciaTmp = new BigDecimal(0);
					BigDecimal porcentajeBonificacionVentaTmp = new BigDecimal(
							0);
					BigDecimal porcentajeDescuentoVentaTmp = new BigDecimal(0);

					Iterator mapasComercialesPlantillaIt2 = mapasComercialesPlantilla
							.keySet().iterator();
					while (mapasComercialesPlantillaIt2.hasNext()) {
						PlanMedioDetalleIf planMedioDetalle = (PlanMedioDetalleIf) mapasComercialesPlantillaIt2
								.next();
						
						ComercialIf comercialIfPlantilla = mapaComercial.get(planMedioDetalle.getComercialId());
						
						if (planMedioDetalle.getProveedorId().compareTo(
								proveedorId) == 0
								&& comercialIfPlantilla.getProductoClienteId()
										.compareTo(productoClienteId) == 0
								&& planMedioDetalle.getNumeroOrdenAgrupacion() == Integer
										.parseInt(numeroOrden)) {

							if (dataDetalleHash.size() > 0) {
								OrdenMedioDetalleData dataDetalle = new OrdenMedioDetalleData();
								dataDetalle.setComercial(planMedioDetalle
										.getComercial());
								dataDetalle.setComercialId(planMedioDetalle
										.getComercialId());
								dataDetalle.setHora(planMedioDetalle
										.getHoraInicio());
								dataDetalle.setPrograma(planMedioDetalle
										.getPrograma());
								// ADD 1 JUNIO
								dataDetalle.setPauta(planMedioDetalle
										.getPauta());
								dataDetalle
										.setAuspicioDescripcion(planMedioDetalle
												.getAuspicioDescripcion());
								// END 1 JUNIO
								// ADD 22 AGOSTO
								dataDetalle
										.setProductoClienteId(comercialIfPlantilla
												.getProductoClienteId());
								// END 22 AGOSTO

								// ADD 13 OCTUBRE
								dataDetalle
										.setCampanaProductoVersionId(comercialIfPlantilla
												.getCampanaProductoVersionId());
								// END 13 OCTUBRE

								int cuniasByOrdenMedioDetalle = 0;
								ArrayList<OrdenMedioDetalleMapaIf> dataDetalleMapaList = new ArrayList<OrdenMedioDetalleMapaIf>();
								Collection mapasComercial = mapasComercialesPlantilla
										.get(planMedioDetalle);
								Iterator mapasComercialIt = mapasComercial
										.iterator();
								while (mapasComercialIt.hasNext()) {
									MapaComercialIf mapaComercialIf = (MapaComercialIf) mapasComercialIt
											.next();
									OrdenMedioDetalleMapaData dataDetalleMapa = new OrdenMedioDetalleMapaData();
									dataDetalleMapa
											.setFecha(Utilitarios
													.fromSqlDateToTimestamp(mapaComercialIf
															.getFecha()));
									dataDetalleMapa
											.setFrecuencia(mapaComercialIf
													.getFrecuencia());
									dataDetalleMapaList.add(dataDetalleMapa);
									cuniasByOrdenMedioDetalle = cuniasByOrdenMedioDetalle
											+ mapaComercialIf.getFrecuencia();
								}
								BigDecimal valorDataDetalle = new BigDecimal(0);
								BigDecimal ivaDataDetalle = new BigDecimal(0);

								dataDetalle.setValorTarifa(planMedioDetalle
										.getValorTarifa());
								dataDetalle.setTotalCunias(planMedioDetalle
										.getTotalCunias());
								dataDetalle
										.setProductoProveedorId(planMedioDetalle
												.getProductoProveedorId());
								dataDetalle
										.setPorcentajeDescuento(planMedioDetalle
												.getPorcentajeDescuento());
								dataDetalle.setValorDescuento(planMedioDetalle
										.getValorDescuento());
								dataDetalle.setValorIva(planMedioDetalle
										.getValorIva());
								dataDetalle.setValorSubtotal(planMedioDetalle
										.getValorSubtotal());
								dataDetalle.setValorTotal(planMedioDetalle
										.getValorTotal());

								dataDetalle
										.setValorDescuentoVenta(planMedioDetalle
												.getValorDescuentoVenta());
								dataDetalle
										.setValorComisionAgencia(planMedioDetalle
												.getValorComisionAgencia());
								dataDetalle
										.setPorcentajeDescuentoVenta(planMedioDetalle
												.getPorcentajeDescuentoVenta());
								dataDetalle
										.setPorcentajeComisionAgencia(planMedioDetalle
												.getPorcentajeComisionAgencia());
								porcentajeComisionAgenciaTmp = planMedioDetalle
										.getPorcentajeComisionAgencia();
								porcentajeBonificacionVentaTmp = planMedioDetalle
										.getPorcentajeBonificacionVenta();
								porcentajeDescuentoVentaTmp = planMedioDetalle
										.getPorcentajeDescuentoVenta();

								dataDetalleHash.put(dataDetalle,
										dataDetalleMapaList);
								dataDetalleVector.add(dataDetalle);

								valor_subtotal = valor_subtotal
										.add(planMedioDetalle
												.getValorSubtotal());
								valor_descuento = valor_descuento
										.add(planMedioDetalle
												.getValorDescuento());

								valorDescuentoVenta = valorDescuentoVenta
										.add(planMedioDetalle
												.getValorDescuentoVenta());
								valorComisionAgencia = valorComisionAgencia
										.add(planMedioDetalle
												.getValorComisionAgencia());

							} else {// CUANDO EL TAMANIO DE dataDetalleHash = 0
								OrdenMedioDetalleData dataDetalle = new OrdenMedioDetalleData();
								dataDetalle.setComercial(planMedioDetalle
										.getComercial());
								dataDetalle.setComercialId(planMedioDetalle
										.getComercialId());
								dataDetalle.setHora(planMedioDetalle
										.getHoraInicio());
								dataDetalle.setPrograma(planMedioDetalle
										.getPrograma());
								// ADD 1 JUNIO
								dataDetalle.setPauta(planMedioDetalle
										.getPauta());
								dataDetalle
										.setAuspicioDescripcion(planMedioDetalle
												.getAuspicioDescripcion());
								// END 1 JUNIO

								// ADD 22 AGOSTO
								dataDetalle
										.setProductoClienteId(comercialIfPlantilla
												.getProductoClienteId());
								// END 22 AGOSTO

								// ADD 13 OCTUBRE
								dataDetalle
										.setCampanaProductoVersionId(comercialIfPlantilla
												.getCampanaProductoVersionId());
								// END 13 OCTUBRE

								int cuniasByOrdenMedioDetalle = 0;
								ArrayList<OrdenMedioDetalleMapaIf> dataDetalleMapaList = new ArrayList<OrdenMedioDetalleMapaIf>();
								Collection mapasComercial = mapasComercialesPlantilla
										.get(planMedioDetalle);
								Iterator mapasComercialIt = mapasComercial
										.iterator();
								while (mapasComercialIt.hasNext()) {
									MapaComercialIf mapaComercialIf = (MapaComercialIf) mapasComercialIt
											.next();
									OrdenMedioDetalleMapaData dataDetalleMapa = new OrdenMedioDetalleMapaData();
									dataDetalleMapa
											.setFecha(Utilitarios
													.fromSqlDateToTimestamp(mapaComercialIf
															.getFecha()));
									dataDetalleMapa
											.setFrecuencia(mapaComercialIf
													.getFrecuencia());
									dataDetalleMapaList.add(dataDetalleMapa);
									cuniasByOrdenMedioDetalle = cuniasByOrdenMedioDetalle
											+ mapaComercialIf.getFrecuencia();
								}
								BigDecimal valorDataDetalle = new BigDecimal(0);
								BigDecimal ivaDataDetalle = new BigDecimal(0);

								dataDetalle.setValorTarifa(planMedioDetalle
										.getValorTarifa());
								dataDetalle.setTotalCunias(planMedioDetalle
										.getTotalCunias());
								dataDetalle
										.setProductoProveedorId(planMedioDetalle
												.getProductoProveedorId());
								dataDetalle
										.setPorcentajeDescuento(planMedioDetalle
												.getPorcentajeDescuento());
								dataDetalle.setValorDescuento(planMedioDetalle
										.getValorDescuento());
								dataDetalle.setValorIva(planMedioDetalle
										.getValorIva());
								dataDetalle.setValorSubtotal(planMedioDetalle
										.getValorSubtotal());
								dataDetalle.setValorTotal(planMedioDetalle
										.getValorTotal());

								dataDetalle
										.setValorDescuentoVenta(planMedioDetalle
												.getValorDescuentoVenta());
								dataDetalle
										.setValorComisionAgencia(planMedioDetalle
												.getValorComisionAgencia());
								dataDetalle
										.setPorcentajeDescuentoVenta(planMedioDetalle
												.getPorcentajeDescuentoVenta());
								dataDetalle
										.setPorcentajeComisionAgencia(planMedioDetalle
												.getPorcentajeComisionAgencia());
								porcentajeComisionAgenciaTmp = planMedioDetalle
										.getPorcentajeComisionAgencia();
								porcentajeBonificacionVentaTmp = planMedioDetalle
										.getPorcentajeBonificacionVenta();
								porcentajeDescuentoVentaTmp = planMedioDetalle
										.getPorcentajeDescuentoVenta();
								dataDetalleHash.put(dataDetalle,
										dataDetalleMapaList);
								dataDetalleVector.add(dataDetalle);

								valor_subtotal = valor_subtotal
										.add(planMedioDetalle
												.getValorSubtotal());
								valor_descuento = valor_descuento
										.add(planMedioDetalle
												.getValorDescuento());

								valorDescuentoVenta = valorDescuentoVenta
										.add(planMedioDetalle
												.getValorDescuentoVenta());
								valorComisionAgencia = valorComisionAgencia
										.add(planMedioDetalle
												.getValorComisionAgencia());
							}
						}
					}

					data.setPorcentajeComisionAgencia(porcentajeComisionAgenciaTmp);
					data.setPorcentajeBonificacionVenta(porcentajeBonificacionVentaTmp);
					data.setPorcentajeDescuentoVenta(porcentajeDescuentoVentaTmp);

					data.setValorSubtotal(valor_subtotal);
					data.setValorDescuento(valor_descuento);

					subtotal = data.getValorSubtotal()
							.subtract(valor_descuento);
					// ADD 5 MAYO
					// subtotalote = subtotalote.add(valor_subtotal);
					// END 5 MAYO
					ProductoIf productoProveedor = mapaProducto.get(productoPautaRegularTv.getId());
					GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
					iva = genericoProveedor.getCobraIva().equals("S") ? subtotal
							.multiply(new BigDecimal(Parametros.IVA / 100D))
							: BigDecimal.ZERO;
					data.setValorIva(iva);
					data.setValorTotal(subtotal.add(iva));

					data.setValorDescuentoVenta(valorDescuentoVenta);
					data.setValorComisionAgencia(valorComisionAgencia);
					data.setPorcentajeCanje(new BigDecimal(0));
					data.setNumeroOrdenAgrupacion(Integer.parseInt(numeroOrden));

					ordenesMediosMapComp.put(data, dataDetalleHash);
					ordenesMedioMap.put(data, dataDetalleVector);

					// ADD 31 MAYO
					// listObservacionesTemp.add(data.getObservacion());
					// END 31 MAYO

					// ADD 22 JUNIO
					mapProductoClienteCodigo.put(data.getProductoClienteId(),
							data.getCodigo());
					listMapProductoClienteCodigoOrden
							.add(mapProductoClienteCodigo);
					// listCodigosOrdenesMedioTemp.add(codigoOrdenMedio);

					mapProductoClienteByProveedorCodigoOrdenMedio.put(
							String.valueOf(proveedorId) + "-" + numeroOrden,
							listMapProductoClienteCodigoOrden);

					// END 22 JUNIO

					tmp++;
				}

				// ADD 4 MAYO
				mapaProductoClienteOrdenesComp.put(
						String.valueOf(productoClienteId) + "-"
								+ numeroOrdenProductoCliente,
						ordenesMediosMapComp);
				// END 4 MAYO

			}

			// System.out.println("SUBTOTAL orden: " + valor_subtotal);
			// System.out.println("SUBTOTALOTE: " + subtotalote);

		}

		// Map <OrdenMedioIf,Map<OrdenMedioDetalleIf,
		// ArrayList<OrdenMedioDetalleMapaIf>>> mapaComercialOrdenesCompCreadas;
		// //= new HashMap<OrdenMedioData,
		// Map<OrdenMedioDetalleData,ArrayList<OrdenMedioDetalleMapaIf>>>();
		// mapaComercialOrdenesCompCreadas =
		// SessionServiceLocator.getOrdenMedioSessionService().procesarOrdenMedio(planMedioIf,
		// mapaComercialOrdenesComp);
		// SpiritAlert.createAlert("Orden de Medio" /*+ ordenMedio.getCodigo()
		// +*/+ " guardada con éxito", SpiritAlert.INFORMATION);
		// Llamar a reporteOrdenMedio cuando se lo llame desde save y desde
		// update
		// reporteOrdenMedio(mapaComercialOrdenesCompCreadas);

		// ADD 11 MAYO
		// 6 MAYO
		Map<String, ArrayList<BigDecimal>> proveedorOrdenesSubtotal = new HashMap<String, ArrayList<BigDecimal>>();
		ArrayList<BigDecimal> ordenesSubtotales = new ArrayList<BigDecimal>();
		// 6 MAYO

		Iterator mapasProductoOrdenesIt = mapaProductoClienteOrdenesComp
				.keySet().iterator();
		while (mapasProductoOrdenesIt.hasNext()) {
			String key[] = ((String) mapasProductoOrdenesIt.next()).split("-");
			Long idproducto = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			Map ordenesProveedor = mapaProductoClienteOrdenesComp.get(String
					.valueOf(idproducto) + "-" + numeroOrden);// * /

			Iterator mapaOrdenesProveedorIt = ordenesProveedor.keySet()
					.iterator();
			while (mapaOrdenesProveedorIt.hasNext()) {
				OrdenMedioIf ordenMedio = (OrdenMedioIf) mapaOrdenesProveedorIt
						.next();
				Long idProveedor = ordenMedio.getProveedorId();

				if (proveedorOrdenesSubtotal
						.get(String.valueOf(idProveedor)
								+ "-"
								+ String.valueOf(ordenMedio
										.getNumeroOrdenAgrupacion())) == null) {
					ordenesSubtotales = new ArrayList<BigDecimal>();
					ordenesSubtotales.add(ordenMedio.getValorSubtotal());
				} else {
					ordenesSubtotales = proveedorOrdenesSubtotal.get(String
							.valueOf(idProveedor)
							+ "-"
							+ String.valueOf(ordenMedio
									.getNumeroOrdenAgrupacion()));// .add(ordenMedio.getValorSubtotal());
					proveedorOrdenesSubtotal.remove(String.valueOf(idProveedor)
							+ "-"
							+ String.valueOf(ordenMedio
									.getNumeroOrdenAgrupacion()));
					ordenesSubtotales.add(ordenMedio.getValorSubtotal());
				}

				proveedorOrdenesSubtotal.put(
						String.valueOf(idProveedor)
								+ "-"
								+ String.valueOf(ordenMedio
										.getNumeroOrdenAgrupacion()),
						ordenesSubtotales);
			}
		}

		Iterator proveedorSubtotalIt2 = proveedorOrdenesSubtotal.keySet()
				.iterator();
		while (proveedorSubtotalIt2.hasNext()) {
			String[] key = ((String) proveedorSubtotalIt2.next()).split("-");
			Long idProveedorTemp = Long.parseLong(key[0]);
			String numeroOrden = key[1];
			ArrayList<BigDecimal> listica = proveedorOrdenesSubtotal.get(String
					.valueOf(idProveedorTemp) + "-" + numeroOrden);
			BigDecimal subtotalProveedor = new BigDecimal(0);
			for (int i = 0; i < listica.size(); i++) {
				subtotalProveedor = subtotalProveedor.add(listica.get(i));
			}
			// Map<Long,BigDecimal> proveedorValorSubtotal = new HashMap<Long,
			// BigDecimal>();
			proveedorValorSubtotal.put(String.valueOf(idProveedorTemp) + "-"
					+ numeroOrden, subtotalProveedor);
			System.out.println("proveedor: " + idProveedorTemp + "SUBTOTAL  "
					+ subtotalProveedor);
		}

	}

	private void cargarOrdenesMedioaAgrupadasXCanalForUpdate() {
		try {
			cleanTablaOrdenesMedioCmp();
			mapOrdenMediobyProveedorSaved.clear();
			mapOrdenMediobyProveedorPDescSaved.clear();
			mapOrdenMediobyProveedorPCanjeSaved.clear();
			DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMediosCmp()
					.getModel();
			ordenMedioCollectionSaved.clear();
			mapOrdenMediobyProveedorObservacionSaved.clear();
			mapOrdenMediobyProveedorPCodigoOrdenSaved.clear();
			listObservacionesTempSaved.clear();
			mapCodigoEstadoOrdenMedioByProveedorSaved.clear();
			listIdsCodigoEstadoOrdenesMedioSaved.clear();

			Iterator proveedorIt = listaProveedoresMapSaved.keySet().iterator();
			while (proveedorIt.hasNext()) {
				String[] key = ((String) proveedorIt.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> listOrdenMedio = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
				ArrayList listPDescuento = new ArrayList();
				Iterator proveedorIdIt2 = mapaProveedorOrdenesCompSaved
						.keySet().iterator();
				while (proveedorIdIt2.hasNext()) {
					String[] keyProveedor = ((String) proveedorIdIt2.next())
							.split("-");
					Long proveedorOrdenId = Long.parseLong(keyProveedor[0]);
					String numeroOrdenAgrupacion = keyProveedor[1];
					Map ordenesMedioMapByCanal = mapaProveedorOrdenesCompSaved
							.get(String.valueOf(proveedorOrdenId) + "-"
									+ numeroOrdenAgrupacion);
					Iterator ordenMedioIt = ordenesMedioMapByCanal.keySet()
							.iterator();
					while (ordenMedioIt.hasNext()) {
						OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt
								.next();
						Map ordenesMedioDetallesMap = (Map) ordenesMedioMapByCanal
								.get(ordenMedioIf);
						if (ordenMedioIf.getProveedorId()
								.compareTo(proveedorId) == 0
								&& ordenMedioIf.getNumeroOrdenAgrupacion() == Integer
										.parseInt(numeroOrden)) {
							listOrdenMedio.put(ordenMedioIf,
									ordenesMedioDetallesMap);
						}
					}
				}
				mapOrdenMediobyProveedorSaved.put(String.valueOf(proveedorId)
						+ "-" + numeroOrden, listOrdenMedio);
			}

			DefaultTableModel tblOrdenesMedios = (DefaultTableModel) getTblOrdenesMediosCmp()
					.getModel();

			ArrayList vectores = new ArrayList();
			int contador = 1;
			Iterator proveedorIdIt = mapOrdenMediobyProveedorSaved.keySet()
					.iterator();
			while (proveedorIdIt.hasNext()) {
				String[] key = ((String) proveedorIdIt.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				ClienteOficinaIf proveedor = mapaClienteOficina
						.get(proveedorId);
				Map listOrdenMedio = (Map) mapOrdenMediobyProveedorSaved
						.get(String.valueOf(proveedorId) + "-" + numeroOrden);
				ArrayList listPDescuento = new ArrayList();
				ArrayList listPCanje = new ArrayList();
				ArrayList listPObservacion = new ArrayList();
				ArrayList listPCodigoOrden = new ArrayList();
				ArrayList<Map<String, String>> listCodigoEstadoOrden = new ArrayList<Map<String, String>>();

				Iterator ordenesMedioMapIt = listOrdenMedio.keySet().iterator();

				while (ordenesMedioMapIt.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioMapIt
							.next();
					BigDecimal porcentajeCanje = new BigDecimal(0);
					Map<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>> productosClienteListCampanaProductoVersion = null;
					ArrayList<CampanaProductoVersionIf> listVersiones = null;
					String productosNombre = "";
					String versionesNombre = "";
					String descuento = "";
					String observacion = "";

					String codigoOrdenMedio = ordenMedioIf.getCodigo();
					Long idOrdenMedio = ordenMedioIf.getId();

					Map<Long, Map<String, String>> mapIdCodigoEstadoOrdenMedio = new LinkedHashMap<Long, Map<String, String>>();
					Map<String, String> mapCodigoEstadoOrden = new LinkedHashMap<String, String>();
					String estadoOrdenMedio = ordenMedioIf.getEstado();

					if (ordenMedioIf.getObservacion() != null) {
						observacion = ordenMedioIf.getObservacion();
					}

					BigDecimal porcentajeOrdenMedioGuardado = new BigDecimal(0);
					Map mapOrdenMedioDetalle = (Map) listOrdenMedio
							.get(ordenMedioIf);
					Iterator mapOrdenMedioDetalleIt = mapOrdenMedioDetalle
							.keySet().iterator();

					if (mapOrdenMedioDetalleIt.hasNext()) {
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) mapOrdenMedioDetalleIt
								.next();
						porcentajeOrdenMedioGuardado = ordenMedioDetalleIf
								.getPorcentajeDescuento();
						descuento = ordenMedioDetalleIf
								.getPorcentajeDescuento().toString();
						ComercialIf comercialIf = mapaComercial.get(ordenMedioDetalleIf.getComercialId());
						DerechoProgramaIf derechoProgramaIf = mapaDerechoPrograma
								.get(comercialIf.getDerechoprogramaId());

						productosClienteListCampanaProductoVersion = getProductosClienteCampanasProductoVersionOfMapaOrdenMedioDetalle(mapOrdenMedioDetalle);
						for (ProductoClienteIf producto : productosClienteListCampanaProductoVersion
								.keySet()) {
							productosNombre += producto.getNombre() + ", ";
							listVersiones = productosClienteListCampanaProductoVersion
									.get(producto);
							for (CampanaProductoVersionIf version : listVersiones) {
								versionesNombre += version.getNombre() + ", ";
							}
						}
					}

					Vector<String> filaOrdenMedio = new Vector<String>();
					filaOrdenMedio.add(String.valueOf(contador));
					filaOrdenMedio.add(proveedor.getDescripcion());
					filaOrdenMedio.add(productosNombre);
					filaOrdenMedio.add(versionesNombre);
					// filaOrdenMedio.add(comercial); COMENTED 14 OCTUBRE

					if (ordenMedioIf.getId() == null) {
						filaOrdenMedio
								.add(porcentajeDescuentoCompraDefaultSaved
										.toString() + "%");
						filaOrdenMedio.add("NORMAL");
						porcentajeCanje = new BigDecimal(0);
					} else {
						filaOrdenMedio.add(formatoDecimal.format(Utilitarios
								.redondeoValor(porcentajeOrdenMedioGuardado))
								+ "%");
						porcentajeCanje = ordenMedioIf.getPorcentajeCanje();
						if (ordenMedioIf.getPorcentajeCanje().compareTo(
								new BigDecimal(0)) == 0) {
							filaOrdenMedio.add("NORMAL");
						} else {
							filaOrdenMedio
									.add("CANJE"
											+ "-"
											+ formatoDecimal
													.format(Utilitarios
															.redondeoValor(porcentajeCanje))
													.toString() + "%");
						}
					}

					ordenMedioCollectionSaved.add(ordenMedioIf);

					String estadoOrdenSaved = ordenMedioIf.getEstado();
					if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_ENVIADA) == 0)
						filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_ENVIADA);
					else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_INGRESADA) == 0)
						filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_INGRESADA);
					else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_ANULADA) == 0)
						filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_ANULADA);
					else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_EMITIDA) == 0)
						filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_EMITIDA);
					else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_ORDENADA) == 0)
						filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_ORDENADA);
					else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_PREPAGADA) == 0)
						filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_PREPAGADA);

					filaOrdenMedio.add(ordenMedioIf.getCodigo());
					tblOrdenesMedios.addRow(filaOrdenMedio);
					listPDescuento.add(descuento);
					listPCanje.add(porcentajeCanje);
					listPObservacion.add(observacion);
					listPCodigoOrden.add(codigoOrdenMedio);
					listObservacionesTempSaved.add(observacion);

					mapCodigoEstadoOrden
							.put(codigoOrdenMedio, estadoOrdenMedio);
					mapIdCodigoEstadoOrdenMedio.put(idOrdenMedio,
							mapCodigoEstadoOrden);

					listCodigoEstadoOrden.add(mapCodigoEstadoOrden);

					if (planMedioTipo.compareTo(PLAN_MEDIO_TIPO_NUEVA_VERSION) == 0
							|| getMode() == SpiritMode.FIND_MODE) {
						listIdsCodigoEstadoOrdenesMedioSaved
								.add(mapIdCodigoEstadoOrdenMedio);
					}

					contador++;
				}
				mapOrdenMediobyProveedorPDescSaved.put(
						String.valueOf(proveedorId) + "-" + numeroOrden,
						listPDescuento);
				mapOrdenMediobyProveedorPCanjeSaved.put(
						String.valueOf(proveedorId) + "-" + numeroOrden,
						listPCanje);
				mapOrdenMediobyProveedorObservacionSaved.put(
						String.valueOf(proveedorId) + "-" + numeroOrden,
						listPObservacion);
				mapOrdenMediobyProveedorPCodigoOrden.put(
						String.valueOf(proveedorId) + "-" + numeroOrden,
						listPCodigoOrden);

				if (planMedioTipo.compareTo(PLAN_MEDIO_TIPO_NUEVA_VERSION) == 0
						|| getMode() == SpiritMode.FIND_MODE) {
					mapCodigoEstadoOrdenMedioByProveedorSaved.put(
							String.valueOf(proveedorId) + "-" + numeroOrden,
							listCodigoEstadoOrden);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 23 MAYO GIOMY VALIDAR QUE SIEMPRE HAYA CANJE DEL 100% PARA Q LAS ORDENES
	// SE AGRUPEN X CANAL
	// QUE NO LE PERMITA GUARDAR SI NO CUMPLE LA VALIDACION
	private void cargarOrdenesMedioaAgrupadasXCanal() {
		try {
			cleanTablaOrdenesMedio();
			mapOrdenMediobyProveedor.clear();
			mapOrdenMediobyProveedorPDesc.clear();
			mapOrdenMediobyProveedorPBono.clear();
			mapOrdenMediobyProveedorPCanje.clear();

			DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMedios()
					.getModel();
			ordenMedioCollection.clear();
			mapOrdenMediobyProveedorObservacion.clear();
			listObservacionesTemp.clear();
			mapOrdenMediobyProveedorPCodigoOrden.clear();

			Iterator proveedorIt = listaProveedoresMap.keySet().iterator();
			while (proveedorIt.hasNext()) {
				String[] key = ((String) proveedorIt.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> listOrdenMedio = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
				ArrayList listPDescuento = new ArrayList();

				Iterator proveedorIdIt2 = mapaProveedorOrdenesComp.keySet()
						.iterator();
				while (proveedorIdIt2.hasNext()) {
					String[] keyProveedor = ((String) proveedorIdIt2.next())
							.split("-");
					Long proveedorOrdenId = Long.parseLong(keyProveedor[0]);
					String numeroOrdenProveedor = keyProveedor[1];
					Map ordenesMedioMapByCanal = mapaProveedorOrdenesComp
							.get(String.valueOf(proveedorOrdenId) + "-"
									+ numeroOrdenProveedor);
					Iterator ordenMedioIt = ordenesMedioMapByCanal.keySet()
							.iterator();
					while (ordenMedioIt.hasNext()) {
						OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt
								.next();
						Map ordenesMedioDetallesMap = (Map) ordenesMedioMapByCanal
								.get(ordenMedioIf);
						if (ordenMedioIf.getProveedorId()
								.compareTo(proveedorId) == 0
								&& ordenMedioIf.getNumeroOrdenAgrupacion() == Integer
										.parseInt(numeroOrden)) {
							listOrdenMedio.put(ordenMedioIf,
									ordenesMedioDetallesMap);
						}
					}
				}
				mapOrdenMediobyProveedor.put(String.valueOf(proveedorId) + "-"
						+ numeroOrden, listOrdenMedio);
			}

			DefaultTableModel tblOrdenesMedios = (DefaultTableModel) getTblOrdenesMedios()
					.getModel();

			ArrayList vectores = new ArrayList();
			int contador = 1;
			Iterator proveedorIdIt = mapOrdenMediobyProveedor.keySet()
					.iterator();
			while (proveedorIdIt.hasNext()) {
				String[] key = ((String) proveedorIdIt.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				ClienteOficinaIf proveedor = mapaClienteOficina
						.get(proveedorId);
				Map listOrdenMedio = (Map) mapOrdenMediobyProveedor.get(String
						.valueOf(proveedorId) + "-" + numeroOrden);
				ArrayList listPDescuento = new ArrayList();
				ArrayList listPBonificacion = new ArrayList();
				ArrayList listPCanje = new ArrayList();
				ArrayList listPObservacion = new ArrayList();
				ArrayList listPCodigoOrden = new ArrayList();

				Iterator ordenesMedioMapIt = listOrdenMedio.keySet().iterator();

				while (ordenesMedioMapIt.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioMapIt
							.next();
					BigDecimal porcentajeCanje = new BigDecimal(0);

					Map<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>> productosClienteListCampanaProductoVersion = null;
					ArrayList<CampanaProductoVersionIf> listVersiones = null;
					String productosNombre = "";
					String versionesNombre = "";

					String descuento = "";
					String porcentajeBonificacion = "";
					String observacion = "";

					if (ordenMedioIf.getObservacion() != null) {
						observacion = ordenMedioIf.getObservacion();
					}

					BigDecimal porcentajeOrdenMedioGuardado = new BigDecimal(0);
					Map mapOrdenMedioDetalle = (Map) listOrdenMedio
							.get(ordenMedioIf);
					Iterator mapOrdenMedioDetalleIt = mapOrdenMedioDetalle
							.keySet().iterator();

					if (mapOrdenMedioDetalleIt.hasNext()) {
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) mapOrdenMedioDetalleIt
								.next();
						porcentajeOrdenMedioGuardado = ordenMedioDetalleIf
								.getPorcentajeDescuento();
						descuento = ordenMedioDetalleIf
								.getPorcentajeDescuento().toString();
						porcentajeBonificacion = ordenMedioIf
								.getPorcentajeBonificacionCompra() != null ? ordenMedioIf
								.getPorcentajeBonificacionCompra().toString()
								: "0";
						ComercialIf comercialIf = mapaComercial.get(ordenMedioDetalleIf.getComercialId());
						DerechoProgramaIf derechoProgramaIf = mapaDerechoPrograma
								.get(comercialIf.getDerechoprogramaId());

						productosClienteListCampanaProductoVersion = getProductosClienteCampanasProductoVersionOfMapaOrdenMedioDetalle(mapOrdenMedioDetalle);
						for (ProductoClienteIf producto : productosClienteListCampanaProductoVersion
								.keySet()) {
							productosNombre += producto.getNombre() + ", ";
							listVersiones = productosClienteListCampanaProductoVersion
									.get(producto);
							for (CampanaProductoVersionIf version : listVersiones) {
								versionesNombre += version.getNombre() + ", ";
							}
						}
					}

					Vector<String> filaOrdenMedio = new Vector<String>();
					filaOrdenMedio.add(String.valueOf(contador));
					filaOrdenMedio.add(proveedor.getDescripcion());
					filaOrdenMedio.add(productosNombre);
					filaOrdenMedio.add(versionesNombre);
					// filaOrdenMedio.add(comercial); COMENTED 14 OCTUBRE

					// 23 MAYO GIOMY VALIDAR QUE SIEMPRE HAY CANJE DEL 100%
					if (ordenMedioIf.getId() == null) {
						filaOrdenMedio.add(porcentajeDescuentoCompraDefault
								.toString() + "%");
						filaOrdenMedio.add("NORMAL");
						porcentajeCanje = new BigDecimal(0);
					} else {
						filaOrdenMedio.add(formatoDecimal.format(Utilitarios
								.redondeoValor(porcentajeOrdenMedioGuardado))
								+ "%");
						porcentajeCanje = ordenMedioIf.getPorcentajeCanje();
						if (ordenMedioIf.getPorcentajeCanje().compareTo(
								new BigDecimal(0)) == 0) {
							filaOrdenMedio.add("NORMAL");
						} else {
							filaOrdenMedio
									.add("CANJE"
											+ "-"
											+ formatoDecimal
													.format(Utilitarios
															.redondeoValor(porcentajeCanje))
													.toString() + "%");
						}
					}

					ordenMedioCollection.add(ordenMedioIf);

					// REVISAR ESTE CONDICIONAL GIOMY 21 JUNIO
					if (!getCbPlanMedioNuevaVersion().isSelected()
							|| !getCbPlanMedioNuevaVersion().isSelected()) {
						if (nuevoPlan) {
							// filaOrdenMedio.add("Codigo Nuevo");
							filaOrdenMedio.add(ordenMedioIf.getCodigo());
						} else {
							filaOrdenMedio.add(ordenMedioIf.getCodigo());
						}
					} else {
						filaOrdenMedio.add(ordenMedioIf.getCodigo());
					}

					tblOrdenesMedios.addRow(filaOrdenMedio);
					listPDescuento.add(descuento);
					listPBonificacion.add(porcentajeBonificacion);
					listPCanje.add(porcentajeCanje);
					listPObservacion.add(observacion);
					listPCodigoOrden.add(ordenMedioIf.getCodigo());
					listObservacionesTemp.add(observacion);

					contador++;
				}
				mapOrdenMediobyProveedorPDesc.put(String.valueOf(proveedorId)
						+ "-" + numeroOrden, listPDescuento);
				mapOrdenMediobyProveedorPBono.put(String.valueOf(proveedorId)
						+ "-" + numeroOrden, listPBonificacion);
				mapOrdenMediobyProveedorPCanje.put(String.valueOf(proveedorId)
						+ "-" + numeroOrden, listPCanje);
				mapOrdenMediobyProveedorObservacion.put(
						String.valueOf(proveedorId) + "-" + numeroOrden,
						listPObservacion);
				mapOrdenMediobyProveedorPCodigoOrden.put(
						String.valueOf(proveedorId) + "-" + numeroOrden,
						listPCodigoOrden);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>> getProductosClienteCampanasProductoVersionOfMapaOrdenMedioDetalle(
			Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> mapOrdenMedioDetalle) {
		Map<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>> mapProductoClienteCampanasProductoVersion = new LinkedHashMap<ProductoClienteIf, ArrayList<CampanaProductoVersionIf>>();
		ArrayList<CampanaProductoVersionIf> listCampanaProductoVersion;
		try {
			for (OrdenMedioDetalleIf ordenMedioDetalle : mapOrdenMedioDetalle
					.keySet()) {
				boolean existeProductoCliente = false;
				for (ProductoClienteIf productoCliente : mapProductoClienteCampanasProductoVersion
						.keySet()) {
					if (productoCliente.getId().compareTo(
							ordenMedioDetalle.getProductoClienteId()) == 0) {
						listCampanaProductoVersion = mapProductoClienteCampanasProductoVersion
								.get(productoCliente);
						boolean existeCampanaProductoVersion = false;
						for (CampanaProductoVersionIf campanaProductoVersion : listCampanaProductoVersion) {
							if (campanaProductoVersion.getId().compareTo(
									ordenMedioDetalle
											.getCampanaProductoVersionId()) == 0) {
								existeCampanaProductoVersion = true;
								break;
							}
						}
						if (!existeCampanaProductoVersion) {
							CampanaProductoVersionIf campanaProductoVersion = SessionServiceLocator
									.getCampanaProductoVersionSessionService()
									.getCampanaProductoVersion(
											ordenMedioDetalle
													.getCampanaProductoVersionId());
							listCampanaProductoVersion
									.add(campanaProductoVersion);
						}
						mapProductoClienteCampanasProductoVersion.put(
								productoCliente, listCampanaProductoVersion);
						existeProductoCliente = true;
						break;
					}
				}
				if (!existeProductoCliente) {
					CampanaProductoVersionIf campanaProductoVersion = SessionServiceLocator
							.getCampanaProductoVersionSessionService()
							.getCampanaProductoVersion(
									ordenMedioDetalle
											.getCampanaProductoVersionId());
					ProductoClienteIf productoCliente = SessionServiceLocator
							.getProductoClienteSessionService()
							.getProductoCliente(
									ordenMedioDetalle.getProductoClienteId());
					listCampanaProductoVersion = new ArrayList<CampanaProductoVersionIf>();
					listCampanaProductoVersion.add(campanaProductoVersion);
					mapProductoClienteCampanasProductoVersion.put(
							productoCliente, listCampanaProductoVersion);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return mapProductoClienteCampanasProductoVersion;
	}

	// END 14 OCTUBRE

	// ADD 12 OCTUBRE
	private void cargarOrdenesMedioaAgrupadasXCampanaProductoVersionForUpdate() {
		try {
			cleanTablaOrdenesMedioCmp();
			mapOrdenMediobyProveedorSaved.clear();
			mapOrdenMediobyProveedorPDescSaved.clear();
			mapOrdenMediobyProveedorPCanjeSaved.clear();
			// ADD 29 JUNIO
			mapOrdenMediobyProveedorPCodigoOrdenSaved.clear();
			// END 29 JUNIO

			DefaultTableModel modelOrdenMedioSaved = (DefaultTableModel) getTblOrdenesMediosCmp()
					.getModel();
			ordenMedioCollectionSaved.clear();
			// 9 MAYO
			indiceProveedorSubtotalOrdenesSaved.clear();
			// ADD 26 MAYO
			mapOrdenMediobyProveedorObservacionSaved.clear();
			// END 26 MAYO
			// ADD 31 MAYO
			listObservacionesTempSaved.clear();
			// END 31 MAYO
			// ADD 21 JUNIO
			// COMENTED 22 JUNIO
			// listCodigosOrdenesMedioTemp.clear();
			// MODIFIED 27 JUNIO
			// mapProductoClienteByProveedorCodigoOrdenMedioSaved.clear();
			// COMENTED 5 SEPTIEMBRE
			// mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved.clear();
			// END 21 JUNIO

			// ADD 5 SEPTIEMBRE
			mapProductoClienteVersionByProveedorCodigoEstadoOrdenMedioSaved
					.clear();

			// ADD 22 JUNIO
			// MODIFIED 27 JUNIO
			// listIdsOrdenesMedioSaved.clear();
			// MODIFIED 28 JUNIO
			// listIdsEstadosOrdenesMedioSaved.clear();
			listIdsCodigoEstadoOrdenesMedioSaved.clear();
			// END 22 JUNIO

			// mapaProductoClienteOrdenesComp.put(productoClienteId,
			// ordenesMediosMapComp);

			Iterator proveedorIt = listaProveedoresMapSaved.keySet().iterator();
			while (proveedorIt.hasNext()) {
				String[] key = ((String) proveedorIt.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> listOrdenMedio = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
				ArrayList listPDescuento = new ArrayList();
				// Iterator comercialIdIt =
				// mapaProductoClienteOrdenesComp.keySet().iterator();
				// MODIFIED 5 SEPTIEMEBRE
				// Iterator productoClienteIdIt =
				// mapaProductoClienteOrdenesCompSaved.keySet().iterator();
				Iterator productoClienteIdIt = mapaProductoClienteVersionesOrdenesCompSaved
						.keySet().iterator();
				// while(comercialIdIt.hasNext()){
				while (productoClienteIdIt.hasNext()) {
					String[] keyProductoCliente = ((String) productoClienteIdIt
							.next()).split("-");
					// Long comercialId = (Long)comercialIdIt.next();
					Long productoClienteId = Long
							.parseLong(keyProductoCliente[0]);
					String numeroOrdenProductoCliente = keyProductoCliente[1];
					// Map ordenesMedioMapByComercial =
					// mapaProductoClienteOrdenesComp.get(comercialId);

					// COMENTED 12 OCTUBRE ADD 5 SEPTIEMBRE
					/*
					 * Map mapaVersionOrdenesComp =
					 * mapaProductoClienteVersionesOrdenesCompSaved
					 * .get(productoClienteId); Iterator
					 * mapaVersionOrdenesCompIt =
					 * mapaVersionOrdenesComp.keySet().iterator();
					 * while(mapaVersionOrdenesCompIt.hasNext()){ String
					 * productoClienteVersion = (String)
					 * mapaVersionOrdenesCompIt.next();
					 * 
					 * Map ordenesMedioMapByProductoClienteVersion = (Map)
					 * mapaVersionOrdenesComp.get(productoClienteVersion);
					 * //Iterator ordenMedioIt =
					 * ordenesMedioMapByComercial.keySet().iterator(); Iterator
					 * ordenMedioIt =
					 * ordenesMedioMapByProductoClienteVersion.keySet
					 * ().iterator(); while(ordenMedioIt.hasNext()){
					 * OrdenMedioIf ordenMedioIf =
					 * (OrdenMedioIf)ordenMedioIt.next(); //Map
					 * ordenesMedioDetallesMap =
					 * (Map)ordenesMedioMapByComercial.get(ordenMedioIf); Map
					 * ordenesMedioDetallesMap =
					 * (Map)ordenesMedioMapByProductoClienteVersion
					 * .get(ordenMedioIf);
					 * if(ordenMedioIf.getProveedorId().compareTo
					 * (proveedorId)==0){
					 * listOrdenMedio.put(ordenMedioIf,ordenesMedioDetallesMap);
					 * //listPDescuento.add("15"); } }
					 * 
					 * }
					 */// ADD 5 SEPTIEMBRE

					// ADD 12 OCTUBRE
					Map mapaVersionOrdenesComp = mapaProductoClienteVersionesOrdenesCompSaved
							.get(String.valueOf(productoClienteId) + "-"
									+ numeroOrdenProductoCliente);
					Iterator mapaVersionOrdenesCompIt = mapaVersionOrdenesComp
							.keySet().iterator();
					while (mapaVersionOrdenesCompIt.hasNext()) {
						// String productoClienteVersion = (String)
						// mapaVersionOrdenesCompIt.next(); COMENTED 12 OCTUBRE
						Long campanaProductoVersion = (Long) mapaVersionOrdenesCompIt
								.next(); // ADD 12 OCTUBRE

						// Map ordenesMedioMapByProductoClienteVersion = (Map)
						// mapaVersionOrdenesComp.get(productoClienteVersion);
						// COMENTED 12 OCTUBRE
						Map ordenesMedioMapByProductoClienteVersion = (Map) mapaVersionOrdenesComp
								.get(campanaProductoVersion); // ADD 12 OCTUBRE

						// Iterator ordenMedioIt =
						// ordenesMedioMapByComercial.keySet().iterator();
						Iterator ordenMedioIt = ordenesMedioMapByProductoClienteVersion
								.keySet().iterator();
						while (ordenMedioIt.hasNext()) {
							OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt
									.next();
							// Map ordenesMedioDetallesMap =
							// (Map)ordenesMedioMapByComercial.get(ordenMedioIf);
							Map ordenesMedioDetallesMap = (Map) ordenesMedioMapByProductoClienteVersion
									.get(ordenMedioIf);
							if (ordenMedioIf.getProveedorId().compareTo(
									proveedorId) == 0
									&& ordenMedioIf.getNumeroOrdenAgrupacion() == Integer
											.parseInt(numeroOrden)) {
								listOrdenMedio.put(ordenMedioIf,
										ordenesMedioDetallesMap);
								// listPDescuento.add("15");
							}
						}
					}// END 12 OCTUBRE
				}
				mapOrdenMediobyProveedorSaved.put(String.valueOf(proveedorId)
						+ "-" + numeroOrden, listOrdenMedio);
			}

			DefaultTableModel tblOrdenesMediosSaved = (DefaultTableModel) getTblOrdenesMediosCmp()
					.getModel();

			ArrayList vectores = new ArrayList();
			int contador = 1;
			Iterator proveedorIdIt = mapOrdenMediobyProveedorSaved.keySet()
					.iterator();
			while (proveedorIdIt.hasNext()) {
				String[] key = ((String) proveedorIdIt.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];

				// esta validacion se hace porque puede ser un plan historico
				// con ordenes ingresadas que hayan pasado al plan actual
				if (!mapOrdenMediobyProveedorSaved.get(
						String.valueOf(proveedorId) + "-" + numeroOrden)
						.isEmpty()) {

					ClienteOficinaIf proveedor = mapaClienteOficina
							.get(proveedorId);
					Map listOrdenMedio = (Map) mapOrdenMediobyProveedorSaved
							.get(String.valueOf(proveedorId) + "-"
									+ numeroOrden);
					ArrayList listPDescuento = new ArrayList();
					ArrayList listPCanje = new ArrayList();
					// ADD 26 MAYO
					ArrayList listPObservacion = new ArrayList();
					// END 26 MAYO
					// ADD 29 JUNIO
					ArrayList listPCodigoOrden = new ArrayList();
					// END 29 JUNIO
					// ADD 21 JUNIO
					// COMENTED 27 JUNIO
					// ArrayList<Map<Long, String>>
					// listMapProductoClienteCodigoOrden = new
					// ArrayList<Map<Long, String>>();
					// MODIFIED 5 SEPTIEMBRE MODIFIED 27 JUNIO
					// ArrayList<Map<Long, Map<String,String>>>
					// listMapProductoClienteCodigoOrden = new
					// ArrayList<Map<Long,Map<String,String>>>();
					// COMENTED 12 OCTUBRE
					// ArrayList<Map<Long,Map<String, Map<String,String>>>>
					// listMapProductoClienteVersionCodigoOrden = new
					// ArrayList<Map<Long,Map<String, Map<String,String>>>>();
					// MODIFIED 12 OCTUBRE
					ArrayList<Map<Long, Map<Long, Map<String, String>>>> listMapProductoClienteVersionCodigoOrden = new ArrayList<Map<Long, Map<Long, Map<String, String>>>>();

					Iterator ordenesMedioMapIt = listOrdenMedio.keySet()
							.iterator();

					while (ordenesMedioMapIt.hasNext()) {
						OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioMapIt
								.next();
						BigDecimal porcentajeCanje = new BigDecimal(0);

						// String comercial = "CUÑA"; COMENTED 14 OCTUBRE
						String versionNombre = ""; // ADD 14 OCTUBRE
						String descuento = "";
						String observacion = "";

						// COMENTED 12 OCTUBRE ADD 5 SEPTIEMBRE
						// Map<Long,Map<String,Map<String,String>>>
						// mapProductoClienteVersionCodigo = new
						// LinkedHashMap<Long,Map<String,
						// Map<String,String>>>();
						// ADD 12 OCTUBRE
						Map<Long, Map<Long, Map<String, String>>> mapProductoClienteVersionCodigo = new LinkedHashMap<Long, Map<Long, Map<String, String>>>();

						// MODIFIED 27 JUNIO
						// Map<String,Map<String,String>> mapVersionCodigo = new
						// LinkedHashMap<String, Map<String,String>>(); COMENTED
						// 12 OCTUBRE
						Map<Long, Map<String, String>> mapVersionCodigo = new LinkedHashMap<Long, Map<String, String>>();
						Long productoClienteId = ordenMedioIf
								.getProductoClienteId();
						String codigoOrdenMedio = ordenMedioIf.getCodigo();
						// ADD 27 JUNIO
						// MODIFIED 28 JUNIO
						// Map<Long,String> mapIdEstadoOrdenMedio = new
						// LinkedHashMap<Long, String>();
						Map<Long, Map<String, String>> mapIdCodigoEstadoOrdenMedio = new LinkedHashMap<Long, Map<String, String>>();
						Map<String, String> mapCodigoEstadoOrden = new LinkedHashMap<String, String>();
						String estadoOrdenMedio = ordenMedioIf.getEstado();
						// END 27

						// COMENTED 27 JUNIO
						// ADD 21 JUNIO
						/*
						 * Map<Long,String> mapProductoClienteCodigo = new
						 * LinkedHashMap<Long, String>(); Long productoClienteId
						 * = ordenMedioIf.getProductoClienteId(); String
						 * codigoOrdenMedio = ordenMedioIf.getCodigo();
						 */
						// END 21 JUNIO

						// ADD 22 JUNIO
						Long idCodigoOrdenMedio = ordenMedioIf.getId();
						// END 22 JUNIO

						// COMENTED 12 OCTUBRE ADD 5 SEPTIEMBRE
						// String productoClienteVersion = "";
						// END 5 SEPTIEMBRE

						// ADD 12 OCTUBRE
						Long campanaProductoVersion = 0L;
						// END ADD 12 OCTUBRE

						if (ordenMedioIf.getObservacion() != null) {
							// ADD 26 MAYO
							observacion = ordenMedioIf.getObservacion();
							// END 26 MAYO
						}
						BigDecimal porcentajeOrdenMedioGuardado = new BigDecimal(
								0);
						Map mapOrdenMedioDetalle = (Map) listOrdenMedio
								.get(ordenMedioIf);
						Iterator mapOrdenMedioDetalleIt = mapOrdenMedioDetalle
								.keySet().iterator();
						if (mapOrdenMedioDetalleIt.hasNext()) {
							OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) mapOrdenMedioDetalleIt
									.next();
							porcentajeOrdenMedioGuardado = ordenMedioDetalleIf
									.getPorcentajeDescuento();
							descuento = ordenMedioDetalleIf
									.getPorcentajeDescuento().toString();
							ComercialIf comercialIf = mapaComercial.get(ordenMedioDetalleIf.getComercialId());
							DerechoProgramaIf derechoProgramaIf = mapaDerechoPrograma
									.get(comercialIf.getDerechoprogramaId());

							campanaProductoVersion = ordenMedioDetalleIf
									.getCampanaProductoVersionId();

							versionNombre = SessionServiceLocator
									.getCampanaProductoVersionSessionService()
									.getCampanaProductoVersion(
											ordenMedioDetalleIf
													.getCampanaProductoVersionId())
									.getNombre();

						}

						Vector<String> filaOrdenMedio = new Vector<String>();
						filaOrdenMedio.add(String.valueOf(contador));
						filaOrdenMedio.add(proveedor.getDescripcion());
						// ADD 14 OCTUBRE
						filaOrdenMedio.add(SessionServiceLocator
								.getProductoClienteSessionService()
								.getProductoCliente(
										ordenMedioIf.getProductoClienteId())
								.getNombre());
						filaOrdenMedio.add(versionNombre);
						// filaOrdenMedio.add(comercial); COMENTED 14 OCTUBRE

						if (ordenMedioIf.getId() == null) {
							filaOrdenMedio
									.add(porcentajeDescuentoCompraDefaultSaved
											.toString() + "%");
							filaOrdenMedio.add("NORMAL");
							porcentajeCanje = new BigDecimal(0);
						} else {
							filaOrdenMedio
									.add(formatoDecimal.format(Utilitarios
											.redondeoValor(porcentajeOrdenMedioGuardado))
											+ "%");
							porcentajeCanje = ordenMedioIf.getPorcentajeCanje();
							if (ordenMedioIf.getPorcentajeCanje().compareTo(
									new BigDecimal(0)) == 0) {
								filaOrdenMedio.add("NORMAL");
							} else {
								filaOrdenMedio
										.add("CANJE"
												+ "-"
												+ formatoDecimal
														.format(Utilitarios
																.redondeoValor(porcentajeCanje))
														.toString() + "%");
							}
						}

						ordenMedioCollectionSaved.add(ordenMedioIf);

						String estadoOrdenSaved = ordenMedioIf.getEstado();
						if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_ENVIADA) == 0)
							filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_ENVIADA);
						else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_INGRESADA) == 0)
							filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_INGRESADA);
						else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_ANULADA) == 0)
							filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_ANULADA);
						else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_EMITIDA) == 0)
							filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_EMITIDA);
						else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_ORDENADA) == 0)
							filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_ORDENADA);
						else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_PREPAGADA) == 0)
							filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_PREPAGADA);

						// ADD 15 JUNIO
						filaOrdenMedio.add(ordenMedioIf.getCodigo());
						// END 15 JUNIO
						tblOrdenesMediosSaved.addRow(filaOrdenMedio);
						listPDescuento.add(descuento);
						listPCanje.add(porcentajeCanje);
						// ADD 26 MAYO
						listPObservacion.add(observacion);
						// END 26 MAYO
						// ADD 29 JUNIO
						listPCodigoOrden.add(codigoOrdenMedio);
						// END 29 JUNIO
						// ADD 31 MAYO
						listObservacionesTempSaved.add(observacion);
						// END 31 MAYO

						// ADD 27 JUNIO
						mapCodigoEstadoOrden.put(codigoOrdenMedio,
								estadoOrdenMedio);
						// MODIFIED 28 JUNIO
						// mapIdEstadoOrdenMedio.put(idCodigoOrdenMedio,estadoOrdenMedio);
						mapIdCodigoEstadoOrdenMedio.put(idCodigoOrdenMedio,
								mapCodigoEstadoOrden);
						// ADD 21 JUNIO
						// MODIFIED 27 JUNIO
						// mapProductoClienteCodigo.put(productoClienteId,
						// codigoOrdenMedio);
						// MODIFIED 5 SEPTIEMBRE
						// mapProductoClienteCodigo.put(productoClienteId,
						// mapCodigoEstadoOrden);
						// mapVersionCodigo.put(productoClienteVersion,
						// mapCodigoEstadoOrden); COMENTED 12 OCTUBRE
						mapVersionCodigo.put(campanaProductoVersion,
								mapCodigoEstadoOrden); // ADD 12 OCTUBRE
						// ADD 5 SEPTIEMBRE
						mapProductoClienteVersionCodigo.put(productoClienteId,
								mapVersionCodigo);

						listMapProductoClienteVersionCodigoOrden
								.add(mapProductoClienteVersionCodigo);
						// COMENTED 22 JUNIO
						// listCodigosOrdenesMedioTemp.add(codigoOrdenMedio);
						// END 21 JUNIO
						// ADD 22 JUNIO
						// MODIFIED 27 JUNIO
						// listIdsOrdenesMedioSaved.add(idCodigoOrdenMedio);
						// MODIFIED 28 JUNIO
						// listIdsEstadosOrdenesMedioSaved.add(mapIdEstadoOrdenMedio);
						// MODIFIED 19 JULIO
						// listIdsCodigoEstadoOrdenesMedioSaved.add(mapIdCodigoEstadoOrdenMedio);
						// END 22 JUNIO

						// MODIFIED 19 JULIO
						if (planMedioTipo
								.compareTo(PLAN_MEDIO_TIPO_NUEVA_VERSION) == 0
								|| getMode() == SpiritMode.FIND_MODE) {
							listIdsCodigoEstadoOrdenesMedioSaved
									.add(mapIdCodigoEstadoOrdenMedio);
						}

						contador++;
					}

					mapOrdenMediobyProveedorPDescSaved.put(
							String.valueOf(proveedorId) + "-" + numeroOrden,
							listPDescuento);
					mapOrdenMediobyProveedorPCanjeSaved.put(
							String.valueOf(proveedorId) + "-" + numeroOrden,
							listPCanje);
					// ADD 26 MAYO
					mapOrdenMediobyProveedorObservacionSaved.put(
							String.valueOf(proveedorId) + "-" + numeroOrden,
							listPObservacion);
					// END 26 MAYO
					// ADD 29 JUNIO
					mapOrdenMediobyProveedorPCodigoOrdenSaved.put(
							String.valueOf(proveedorId) + "-" + numeroOrden,
							listPCodigoOrden);
					// END 29 JUNIO

					// ADD 21 JUNIO
					// MODIFIED 27 JUNIO
					// mapProductoClienteByProveedorCodigoOrdenMedioSaved.put(proveedorId,listMapProductoClienteCodigoOrden);
					// MODIFIED 19 JULIO
					// mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved.put(proveedorId,listMapProductoClienteCodigoOrden);
					// END 21 JUNIO

					// MODIFIED 19 JULIO
					if (planMedioTipo.compareTo(PLAN_MEDIO_TIPO_NUEVA_VERSION) == 0
							|| getMode() == SpiritMode.FIND_MODE) {
						// COMENTED 5 SEPTIEMBRE
						// mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved.put(proveedorId,listMapProductoClienteCodigoOrden);
						mapProductoClienteVersionByProveedorCodigoEstadoOrdenMedioSaved
								.put(String.valueOf(proveedorId) + "-"
										+ numeroOrden,
										listMapProductoClienteVersionCodigoOrden);
					}

					// AKI AGREGO MI VALOR DEL TOTAL X PROVEEDOR

					// proveedorValorSubtotal.put(idProveedorTemp,subtotalProveedor);
					Vector<String> filaOrdenMedioSubtotal = new Vector<String>();
					filaOrdenMedioSubtotal.add("-");
					filaOrdenMedioSubtotal.add("SUBTOTAL "
							+ proveedor.getDescripcion());
					filaOrdenMedioSubtotal
							.add(formatoDecimal.format(Utilitarios
									.redondeoValor((BigDecimal) proveedorValorSubtotalSaved
											.get(String.valueOf(proveedorId)
													+ "-" + numeroOrden))));

					tblOrdenesMediosSaved.addRow(filaOrdenMedioSubtotal);
					// 9 MAYOOOOOOOOOOOOOOOOOO
					indiceProveedorSubtotalOrdenesSaved
							.add(tblOrdenesMediosSaved.getRowCount() - 1);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();// "Error en :: cargarOrdenesMedioaAgrupadasForUpdate()"
		}
	}

	// END 12 OCTUBRE

	// ADD AGAIN 13 OCTUBRE ADD 15 JUNIO
	// private void cargarOrdenesMedioaAgrupadasForUpdate(){ COMENTED 13 OCTUBRE
	private void cargarOrdenesMedioaAgrupadasXProductoClienteForUpdate() { // ADD
																			// 13
																			// OCTUBRE
		try {
			cleanTablaOrdenesMedioCmp();
			mapOrdenMediobyProveedorSaved.clear();
			mapOrdenMediobyProveedorPDescSaved.clear();
			mapOrdenMediobyProveedorPCanjeSaved.clear();
			// ADD 29 JUNIO
			mapOrdenMediobyProveedorPCodigoOrdenSaved.clear();
			// END 29 JUNIO

			DefaultTableModel modelOrdenMedioSaved = (DefaultTableModel) getTblOrdenesMediosCmp()
					.getModel();
			ordenMedioCollectionSaved.clear();
			// 9 MAYO
			indiceProveedorSubtotalOrdenesSaved.clear();
			// ADD 26 MAYO
			mapOrdenMediobyProveedorObservacionSaved.clear();
			// END 26 MAYO
			// ADD 31 MAYO
			listObservacionesTempSaved.clear();
			// END 31 MAYO
			// ADD 21 JUNIO
			// COMENTED 22 JUNIO
			// listCodigosOrdenesMedioTemp.clear();
			// MODIFIED 27 JUNIO
			// mapProductoClienteByProveedorCodigoOrdenMedioSaved.clear();
			mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved.clear();
			// END 21 JUNIO

			// ADD 22 JUNIO
			// MODIFIED 27 JUNIO
			// listIdsOrdenesMedioSaved.clear();
			// MODIFIED 28 JUNIO
			// listIdsEstadosOrdenesMedioSaved.clear();
			listIdsCodigoEstadoOrdenesMedioSaved.clear();
			// END 22 JUNIO

			// mapaProductoClienteOrdenesComp.put(productoClienteId,
			// ordenesMediosMapComp);

			Iterator proveedorIt = listaProveedoresMapSaved.keySet().iterator();
			while (proveedorIt.hasNext()) {
				String[] key = ((String) proveedorIt.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> listOrdenMedio = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
				ArrayList listPDescuento = new ArrayList();
				// Iterator comercialIdIt =
				// mapaProductoClienteOrdenesComp.keySet().iterator();
				Iterator productoClienteIdIt = mapaProductoClienteOrdenesCompSaved
						.keySet().iterator();
				// while(comercialIdIt.hasNext()){
				while (productoClienteIdIt.hasNext()) {
					// Long comercialId = (Long)comercialIdIt.next();
					String[] keyProductoCliente = ((String) productoClienteIdIt
							.next()).split("-");
					Long productoClienteId = Long
							.parseLong(keyProductoCliente[0]);
					String numeroOrdenProductoCliente = keyProductoCliente[1];
					// Map ordenesMedioMapByComercial =
					// mapaProductoClienteOrdenesComp.get(comercialId);
					Map ordenesMedioMapByProductoCliente = mapaProductoClienteOrdenesCompSaved
							.get(String.valueOf(productoClienteId) + "-"
									+ numeroOrdenProductoCliente);
					// Iterator ordenMedioIt =
					// ordenesMedioMapByComercial.keySet().iterator();
					Iterator ordenMedioIt = ordenesMedioMapByProductoCliente
							.keySet().iterator();
					while (ordenMedioIt.hasNext()) {
						OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt
								.next();
						// Map ordenesMedioDetallesMap =
						// (Map)ordenesMedioMapByComercial.get(ordenMedioIf);
						Map ordenesMedioDetallesMap = (Map) ordenesMedioMapByProductoCliente
								.get(ordenMedioIf);
						if (ordenMedioIf.getProveedorId()
								.compareTo(proveedorId) == 0
								&& ordenMedioIf.getNumeroOrdenAgrupacion() == Integer
										.parseInt(numeroOrden)) {
							listOrdenMedio.put(ordenMedioIf,
									ordenesMedioDetallesMap);
							// listPDescuento.add("15");
						}
					}
				}
				mapOrdenMediobyProveedorSaved.put(String.valueOf(proveedorId)
						+ "-" + numeroOrden, listOrdenMedio);
			}

			DefaultTableModel tblOrdenesMediosSaved = (DefaultTableModel) getTblOrdenesMediosCmp()
					.getModel();

			ArrayList vectores = new ArrayList();
			int contador = 1;
			Iterator proveedorIdIt = mapOrdenMediobyProveedorSaved.keySet()
					.iterator();
			while (proveedorIdIt.hasNext()) {
				String[] key = ((String) proveedorIdIt.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				ClienteOficinaIf proveedor = mapaClienteOficina
						.get(proveedorId);
				Map listOrdenMedio = (Map) mapOrdenMediobyProveedorSaved
						.get(String.valueOf(proveedorId) + "-" + numeroOrden);
				ArrayList listPDescuento = new ArrayList();
				ArrayList listPCanje = new ArrayList();
				// ADD 26 MAYO
				ArrayList listPObservacion = new ArrayList();
				// END 26 MAYO
				// ADD 29 JUNIO
				ArrayList listPCodigoOrden = new ArrayList();
				// END 29 JUNIO
				// ADD 21 JUNIO
				// COMENTED 27 JUNIO
				// ArrayList<Map<Long, String>>
				// listMapProductoClienteCodigoOrden = new ArrayList<Map<Long,
				// String>>();
				// MODIFIED 27 JUNIO
				ArrayList<Map<Long, Map<String, String>>> listMapProductoClienteCodigoOrden = new ArrayList<Map<Long, Map<String, String>>>();
				// END 21 JUNIO

				Iterator ordenesMedioMapIt = listOrdenMedio.keySet().iterator();

				while (ordenesMedioMapIt.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioMapIt
							.next();
					BigDecimal porcentajeCanje = new BigDecimal(0);
					// String comercial = "CUÑA"; COMENTED 20 OCTUBRE

					// ADD 20 OCTUBRE
					Map<Long, CampanaProductoVersionIf> mapCampanasProductoVersion = null;
					String versionesNombres = "";

					String descuento = "";
					String observacion = "";

					// MODIFIED 27 JUNIO
					Map<Long, Map<String, String>> mapProductoClienteCodigo = new LinkedHashMap<Long, Map<String, String>>();
					Long productoClienteId = ordenMedioIf
							.getProductoClienteId();
					String codigoOrdenMedio = ordenMedioIf.getCodigo();
					// ADD 27 JUNIO
					// MODIFIED 28 JUNIO
					// Map<Long,String> mapIdEstadoOrdenMedio = new
					// LinkedHashMap<Long, String>();
					Map<Long, Map<String, String>> mapIdCodigoEstadoOrdenMedio = new LinkedHashMap<Long, Map<String, String>>();
					Map<String, String> mapCodigoEstadoOrden = new LinkedHashMap<String, String>();
					String estadoOrdenMedio = ordenMedioIf.getEstado();
					// END 27

					// COMENTED 27 JUNIO
					// ADD 21 JUNIO
					/*
					 * Map<Long,String> mapProductoClienteCodigo = new
					 * LinkedHashMap<Long, String>(); Long productoClienteId =
					 * ordenMedioIf.getProductoClienteId(); String
					 * codigoOrdenMedio = ordenMedioIf.getCodigo();
					 */
					// END 21 JUNIO

					// ADD 22 JUNIO
					Long idCodigoOrdenMedio = ordenMedioIf.getId();
					// END 22 JUNIO

					if (ordenMedioIf.getObservacion() != null) {
						// ADD 26 MAYO
						observacion = ordenMedioIf.getObservacion();
						// END 26 MAYO
					}
					BigDecimal porcentajeOrdenMedioGuardado = new BigDecimal(0);
					Map mapOrdenMedioDetalle = (Map) listOrdenMedio
							.get(ordenMedioIf);
					Iterator mapOrdenMedioDetalleIt = mapOrdenMedioDetalle
							.keySet().iterator();
					if (mapOrdenMedioDetalleIt.hasNext()) {
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) mapOrdenMedioDetalleIt
								.next();
						porcentajeOrdenMedioGuardado = ordenMedioDetalleIf
								.getPorcentajeDescuento();
						descuento = ordenMedioDetalleIf
								.getPorcentajeDescuento().toString();
						ComercialIf comercialIf = mapaComercial.get(ordenMedioDetalleIf.getComercialId());
						DerechoProgramaIf derechoProgramaIf = mapaDerechoPrograma
								.get(comercialIf.getDerechoprogramaId());

						// AKIIIIIIIIIIIIII SOLO VERSION
						// COMENTED 20 OCTUBRE
						// comercial = comercialIf.getVersion(); //+ ", " +
						// derechoProgramaIf.getNombre() + "-" +
						// derechoProgramaIf.getTipo();

						// ADD 20 OCTUBRE
						mapCampanasProductoVersion = getCampanasProductoVersionOfMapaOrdenMedioDetalle(mapOrdenMedioDetalle);
						for (Long idVersion : mapCampanasProductoVersion
								.keySet()) {
							versionesNombres += mapCampanasProductoVersion.get(
									idVersion).getNombre()
									+ ", ";
						}
						// END 20 OCTUBRE

					}

					Vector<String> filaOrdenMedio = new Vector<String>();
					filaOrdenMedio.add(String.valueOf(contador));
					filaOrdenMedio.add(proveedor.getDescripcion());
					// ADD 20 OCTUBRE
					filaOrdenMedio.add(SessionServiceLocator
							.getProductoClienteSessionService()
							.getProductoCliente(
									ordenMedioIf.getProductoClienteId())
							.getNombre());
					filaOrdenMedio.add(versionesNombres);
					// filaOrdenMedio.add(comercial); COMENTED 20 OCTUBRE

					if (ordenMedioIf.getId() == null) {
						filaOrdenMedio
								.add(porcentajeDescuentoCompraDefaultSaved
										.toString() + "%");
						filaOrdenMedio.add("NORMAL");
						porcentajeCanje = new BigDecimal(0);
					} else {
						filaOrdenMedio.add(formatoDecimal.format(Utilitarios
								.redondeoValor(porcentajeOrdenMedioGuardado))
								+ "%");
						porcentajeCanje = ordenMedioIf.getPorcentajeCanje();
						if (ordenMedioIf.getPorcentajeCanje().compareTo(
								new BigDecimal(0)) == 0) {
							filaOrdenMedio.add("NORMAL");
						} else {
							filaOrdenMedio
									.add("CANJE"
											+ "-"
											+ formatoDecimal
													.format(Utilitarios
															.redondeoValor(porcentajeCanje))
													.toString() + "%");
						}
					}

					ordenMedioCollectionSaved.add(ordenMedioIf);

					String estadoOrdenSaved = ordenMedioIf.getEstado();
					if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_ENVIADA) == 0)
						filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_ENVIADA);
					else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_INGRESADA) == 0)
						filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_INGRESADA);
					else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_ANULADA) == 0)
						filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_ANULADA);
					else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_EMITIDA) == 0)
						filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_EMITIDA);
					else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_ORDENADA) == 0)
						filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_ORDENADA);
					else if (estadoOrdenSaved.compareTo(ESTADO_ORDEN_PREPAGADA) == 0)
						filaOrdenMedio.add(NOMBRE_ESTADO_ORDEN_MEDIO_PREPAGADA);
					
					// ADD 15 JUNIO
					filaOrdenMedio.add(ordenMedioIf.getCodigo());
					// END 15 JUNIO
					tblOrdenesMediosSaved.addRow(filaOrdenMedio);
					listPDescuento.add(descuento);
					listPCanje.add(porcentajeCanje);
					// ADD 26 MAYO
					listPObservacion.add(observacion);
					// END 26 MAYO
					// ADD 29 JUNIO
					listPCodigoOrden.add(codigoOrdenMedio);
					// END 29 JUNIO
					// ADD 31 MAYO
					listObservacionesTempSaved.add(observacion);
					// END 31 MAYO

					// ADD 27 JUNIO
					mapCodigoEstadoOrden
							.put(codigoOrdenMedio, estadoOrdenMedio);
					// MODIFIED 28 JUNIO
					// mapIdEstadoOrdenMedio.put(idCodigoOrdenMedio,estadoOrdenMedio);
					mapIdCodigoEstadoOrdenMedio.put(idCodigoOrdenMedio,
							mapCodigoEstadoOrden);
					// ADD 21 JUNIO
					// MODIFIED 27 JUNIO
					// mapProductoClienteCodigo.put(productoClienteId,
					// codigoOrdenMedio);
					mapProductoClienteCodigo.put(productoClienteId,
							mapCodigoEstadoOrden);
					listMapProductoClienteCodigoOrden
							.add(mapProductoClienteCodigo);
					// COMENTED 22 JUNIO
					// listCodigosOrdenesMedioTemp.add(codigoOrdenMedio);
					// END 21 JUNIO
					// ADD 22 JUNIO
					// MODIFIED 27 JUNIO
					// listIdsOrdenesMedioSaved.add(idCodigoOrdenMedio);
					// MODIFIED 28 JUNIO
					// listIdsEstadosOrdenesMedioSaved.add(mapIdEstadoOrdenMedio);
					// MODIFIED 19 JULIO
					// listIdsCodigoEstadoOrdenesMedioSaved.add(mapIdCodigoEstadoOrdenMedio);
					// END 22 JUNIO

					// MODIFIED 19 JULIO
					if (planMedioTipo.compareTo(PLAN_MEDIO_TIPO_NUEVA_VERSION) == 0
							|| getMode() == SpiritMode.FIND_MODE) {
						listIdsCodigoEstadoOrdenesMedioSaved
								.add(mapIdCodigoEstadoOrdenMedio);
					}

					contador++;
				}

				mapOrdenMediobyProveedorPDescSaved.put(
						String.valueOf(proveedorId) + "-" + numeroOrden,
						listPDescuento);
				mapOrdenMediobyProveedorPCanjeSaved.put(
						String.valueOf(proveedorId) + "-" + numeroOrden,
						listPCanje);
				// ADD 26 MAYO
				mapOrdenMediobyProveedorObservacionSaved.put(
						String.valueOf(proveedorId) + "-" + numeroOrden,
						listPObservacion);
				// END 26 MAYO
				// ADD 29 JUNIO
				mapOrdenMediobyProveedorPCodigoOrdenSaved.put(
						String.valueOf(proveedorId) + "-" + numeroOrden,
						listPCodigoOrden);
				// END 29 JUNIO

				// ADD 21 JUNIO
				// MODIFIED 27 JUNIO
				// mapProductoClienteByProveedorCodigoOrdenMedioSaved.put(proveedorId,listMapProductoClienteCodigoOrden);
				// MODIFIED 19 JULIO
				// mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved.put(proveedorId,listMapProductoClienteCodigoOrden);
				// END 21 JUNIO

				// MODIFIED 19 JULIO
				if (planMedioTipo.compareTo(PLAN_MEDIO_TIPO_NUEVA_VERSION) == 0
						|| getMode() == SpiritMode.FIND_MODE) {
					mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved
							.put(String.valueOf(proveedorId) + "-"
									+ numeroOrden,
									listMapProductoClienteCodigoOrden);
				}

				// AKI AGREGO MI VALOR DEL TOTAL X PROVEEDOR

				// proveedorValorSubtotal.put(idProveedorTemp,subtotalProveedor);
				Vector<String> filaOrdenMedioSubtotal = new Vector<String>();
				filaOrdenMedioSubtotal.add("-");
				filaOrdenMedioSubtotal.add("SUBTOTAL "
						+ proveedor.getDescripcion());
				filaOrdenMedioSubtotal.add(formatoDecimal.format(Utilitarios
						.redondeoValor((BigDecimal) proveedorValorSubtotalSaved
								.get(String.valueOf(proveedorId) + "-"
										+ numeroOrden))));

				tblOrdenesMediosSaved.addRow(filaOrdenMedioSubtotal);
				// 9 MAYOOOOOOOOOOOOOOOOOO
				indiceProveedorSubtotalOrdenesSaved.add(tblOrdenesMediosSaved
						.getRowCount() - 1);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();// "Error en :: cargarOrdenesMedioaAgrupadasForUpdate()"
		}
	}

	// MODIFIED 12 OCTUBRE ADD 31 AGOSTO cargarOrdenesMedioaAgrupadasForUpdate
	private void cargarOrdenesMedioaAgrupadasXCampanaProductoVersion() {
		try {
			cleanTablaOrdenesMedio();
			mapOrdenMediobyProveedor.clear();
			mapOrdenMediobyProveedorPDesc.clear();
			mapOrdenMediobyProveedorPBono.clear();
			mapOrdenMediobyProveedorPCanje.clear();

			DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMedios()
					.getModel();
			ordenMedioCollection.clear();
			indiceProveedorSubtotalOrdenes.clear();
			mapOrdenMediobyProveedorObservacion.clear();
			mapOrdenMediobyProveedorPCodigoOrden.clear();
			listObservacionesTemp.clear();

			Iterator proveedorIt = listaProveedoresMap.keySet().iterator();
			while (proveedorIt.hasNext()) {
				String[] key = ((String) proveedorIt.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> listOrdenMedio = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
				ArrayList listPDescuento = new ArrayList();

				Iterator productoClienteIdVersionIt = mapaProductoClienteVersionOrdenesComp
						.keySet().iterator();

				while (productoClienteIdVersionIt.hasNext()) {
					String[] keyProductoCliente = ((String) productoClienteIdVersionIt
							.next()).split("-");
					Long productoClienteId = Long
							.parseLong(keyProductoCliente[0]);
					String numeroOrdenProductoCliente = keyProductoCliente[1];
					Map mapProductoClienteVersion = mapaProductoClienteVersionOrdenesComp
							.get(String.valueOf(productoClienteId) + "-"
									+ numeroOrdenProductoCliente);
					Iterator mapProductoClienteVersionIt = mapProductoClienteVersion
							.keySet().iterator();

					while (mapProductoClienteVersionIt.hasNext()) {
						Long campanaProductoVersion = (Long) mapProductoClienteVersionIt
								.next();

						Map ordenesMedioMapByProductoClienteVersion = (Map) mapProductoClienteVersion
								.get(campanaProductoVersion);

						Iterator ordenMedioIt = ordenesMedioMapByProductoClienteVersion
								.keySet().iterator();

						while (ordenMedioIt.hasNext()) {
							OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt
									.next();

							Map ordenesMedioDetallesMap = (Map) ordenesMedioMapByProductoClienteVersion
									.get(ordenMedioIf);

							if (ordenMedioIf.getProveedorId().compareTo(
									proveedorId) == 0
									&& ordenMedioIf.getNumeroOrdenAgrupacion() == Integer
											.parseInt(numeroOrden)) {
								listOrdenMedio.put(ordenMedioIf,
										ordenesMedioDetallesMap);
							}
						}
					}
				}
				mapOrdenMediobyProveedor.put(String.valueOf(proveedorId) + "-"
						+ numeroOrden, listOrdenMedio);
			}

			DefaultTableModel tblOrdenesMedios = (DefaultTableModel) getTblOrdenesMedios()
					.getModel();

			ArrayList vectores = new ArrayList();
			int contador = 1;
			Iterator proveedorIdIt = mapOrdenMediobyProveedor.keySet()
					.iterator();
			while (proveedorIdIt.hasNext()) {
				String[] key = ((String) proveedorIdIt.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				ClienteOficinaIf proveedor = mapaClienteOficina
						.get(proveedorId);
				Map listOrdenMedio = (Map) mapOrdenMediobyProveedor.get(String
						.valueOf(proveedorId) + "-" + numeroOrden);
				ArrayList listPDescuento = new ArrayList();
				ArrayList listPBonificacion = new ArrayList();
				ArrayList listPCanje = new ArrayList();
				ArrayList listPObservacion = new ArrayList();
				ArrayList listPCodigoOrden = new ArrayList();

				Iterator ordenesMedioMapIt = listOrdenMedio.keySet().iterator();

				while (ordenesMedioMapIt.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioMapIt
							.next();
					BigDecimal porcentajeCanje = new BigDecimal(0);
					String versionNombre = "";
					String descuento = "";
					String porcentajeBonificacion = "";
					String observacion = "";

					if (ordenMedioIf.getObservacion() != null) {
						observacion = ordenMedioIf.getObservacion();
					}

					BigDecimal porcentajeOrdenMedioGuardado = new BigDecimal(0);
					Map mapOrdenMedioDetalle = (Map) listOrdenMedio
							.get(ordenMedioIf);
					Iterator mapOrdenMedioDetalleIt = mapOrdenMedioDetalle
							.keySet().iterator();
					if (mapOrdenMedioDetalleIt.hasNext()) {
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) mapOrdenMedioDetalleIt
								.next();
						porcentajeOrdenMedioGuardado = ordenMedioDetalleIf
								.getPorcentajeDescuento();
						descuento = ordenMedioDetalleIf
								.getPorcentajeDescuento().toString();
						porcentajeBonificacion = ordenMedioIf
								.getPorcentajeBonificacionCompra() != null ? ordenMedioIf
								.getPorcentajeBonificacionCompra().toString()
								: "0";
						ComercialIf comercialIf = mapaComercial.get(ordenMedioDetalleIf.getComercialId());
						DerechoProgramaIf derechoProgramaIf = mapaDerechoPrograma
								.get(comercialIf.getDerechoprogramaId());
						versionNombre = SessionServiceLocator
								.getCampanaProductoVersionSessionService()
								.getCampanaProductoVersion(
										ordenMedioDetalleIf
												.getCampanaProductoVersionId())
								.getNombre();
					}

					Vector<String> filaOrdenMedio = new Vector<String>();
					filaOrdenMedio.add(String.valueOf(contador));
					filaOrdenMedio.add(proveedor.getDescripcion());
					filaOrdenMedio.add(SessionServiceLocator
							.getProductoClienteSessionService()
							.getProductoCliente(
									ordenMedioIf.getProductoClienteId())
							.getNombre());
					filaOrdenMedio.add(versionNombre);

					if (ordenMedioIf.getId() == null) {
						filaOrdenMedio.add(porcentajeDescuentoCompraDefault
								.toString() + "%");
						filaOrdenMedio.add("NORMAL");
						porcentajeCanje = new BigDecimal(0);
					} else {
						filaOrdenMedio.add(formatoDecimal.format(Utilitarios
								.redondeoValor(porcentajeOrdenMedioGuardado))
								+ "%");
						porcentajeCanje = ordenMedioIf.getPorcentajeCanje();
						if (ordenMedioIf.getPorcentajeCanje().compareTo(
								new BigDecimal(0)) == 0) {
							filaOrdenMedio.add("NORMAL");
						} else {
							filaOrdenMedio
									.add("CANJE"
											+ "-"
											+ formatoDecimal
													.format(Utilitarios
															.redondeoValor(porcentajeCanje))
													.toString() + "%");
						}
					}

					ordenMedioCollection.add(ordenMedioIf);

					// REVISAR ESTE CONDICIONAL GIOMY 21 JUNIO
					if (!getCbPlanMedioNuevaVersion().isSelected()
							|| !getCbPlanMedioNuevaVersion().isSelected()) {
						if (nuevoPlan) {
							// filaOrdenMedio.add("Codigo Nuevo");
							filaOrdenMedio.add(ordenMedioIf.getCodigo());
						} else {
							filaOrdenMedio.add(ordenMedioIf.getCodigo());
						}
					} else {
						filaOrdenMedio.add(ordenMedioIf.getCodigo());
					}

					tblOrdenesMedios.addRow(filaOrdenMedio);
					listPDescuento.add(descuento);
					listPBonificacion.add(porcentajeBonificacion);
					listPCanje.add(porcentajeCanje);
					listPObservacion.add(observacion);
					listPCodigoOrden.add(ordenMedioIf.getCodigo());
					listObservacionesTemp.add(observacion);

					contador++;
				}

				mapOrdenMediobyProveedorPDesc.put(String.valueOf(proveedorId)
						+ "-" + numeroOrden, listPDescuento);
				mapOrdenMediobyProveedorPBono.put(String.valueOf(proveedorId)
						+ "-" + numeroOrden, listPBonificacion);
				mapOrdenMediobyProveedorPCanje.put(String.valueOf(proveedorId)
						+ "-" + numeroOrden, listPCanje);
				mapOrdenMediobyProveedorObservacion.put(
						String.valueOf(proveedorId) + "-" + numeroOrden,
						listPObservacion);
				mapOrdenMediobyProveedorPCodigoOrden.put(
						String.valueOf(proveedorId) + "-" + numeroOrden,
						listPCodigoOrden);

				Vector<String> filaOrdenMedioSubtotal = new Vector<String>();
				filaOrdenMedioSubtotal.add("-");
				filaOrdenMedioSubtotal.add("SUBTOTAL "
						+ proveedor.getDescripcion());
				// if(proveedorValorSubtotal.get(proveedorId) != null)
				filaOrdenMedioSubtotal.add(formatoDecimal.format(Utilitarios
						.redondeoValor((BigDecimal) proveedorValorSubtotal
								.get(String.valueOf(proveedorId) + "-"
										+ numeroOrden))));

				tblOrdenesMedios.addRow(filaOrdenMedioSubtotal);
				indiceProveedorSubtotalOrdenes.add(tblOrdenesMedios
						.getRowCount() - 1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// cargarOrdenesMedioaAgrupadasForUpdate
	private void cargarOrdenesMedioaAgrupadasXProductoCliente() {
		try {
			cleanTablaOrdenesMedio();
			mapOrdenMediobyProveedor.clear();
			mapOrdenMediobyProveedorPDesc.clear();
			mapOrdenMediobyProveedorPBono.clear();
			mapOrdenMediobyProveedorPCanje.clear();

			DefaultTableModel modelOrdenMedio = (DefaultTableModel) getTblOrdenesMedios()
					.getModel();
			ordenMedioCollection.clear();
			indiceProveedorSubtotalOrdenes.clear();
			mapOrdenMediobyProveedorObservacion.clear();
			mapOrdenMediobyProveedorPCodigoOrden.clear();
			listObservacionesTemp.clear();

			Iterator proveedorIt = listaProveedoresMap.keySet().iterator();
			while (proveedorIt.hasNext()) {
				String[] key = ((String) proveedorIt.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> listOrdenMedio = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();
				ArrayList listPDescuento = new ArrayList();
				Iterator productoClienteIdIt = mapaProductoClienteOrdenesComp
						.keySet().iterator();

				while (productoClienteIdIt.hasNext()) {
					String[] keyProductoCliente = ((String) productoClienteIdIt
							.next()).split("-");
					Long productoClienteId = Long
							.parseLong(keyProductoCliente[0]);
					String numeroOrdenProductoCliente = keyProductoCliente[1];
					Map ordenesMedioMapByProductoCliente = mapaProductoClienteOrdenesComp
							.get(String.valueOf(productoClienteId) + "-"
									+ numeroOrdenProductoCliente);
					Iterator ordenMedioIt = ordenesMedioMapByProductoCliente
							.keySet().iterator();
					while (ordenMedioIt.hasNext()) {
						OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenMedioIt
								.next();
						Map ordenesMedioDetallesMap = (Map) ordenesMedioMapByProductoCliente
								.get(ordenMedioIf);
						if (ordenMedioIf.getProveedorId()
								.compareTo(proveedorId) == 0
								&& ordenMedioIf.getNumeroOrdenAgrupacion() == Integer
										.parseInt(numeroOrden)) {
							listOrdenMedio.put(ordenMedioIf,
									ordenesMedioDetallesMap);
						}
					}
				}
				mapOrdenMediobyProveedor.put(String.valueOf(proveedorId) + "-"
						+ numeroOrden, listOrdenMedio);
			}

			DefaultTableModel tblOrdenesMedios = (DefaultTableModel) getTblOrdenesMedios()
					.getModel();

			ArrayList vectores = new ArrayList();
			int contador = 1;
			Iterator proveedorIdIt = mapOrdenMediobyProveedor.keySet()
					.iterator();
			while (proveedorIdIt.hasNext()) {
				String[] key = ((String) proveedorIdIt.next()).split("-");
				Long proveedorId = Long.parseLong(key[0]);
				String numeroOrden = key[1];
				ClienteOficinaIf proveedor = mapaClienteOficina
						.get(proveedorId);
				Map listOrdenMedio = (Map) mapOrdenMediobyProveedor.get(String
						.valueOf(proveedorId) + "-" + numeroOrden);
				ArrayList listPDescuento = new ArrayList();
				ArrayList listPBonificacion = new ArrayList();
				ArrayList listPCanje = new ArrayList();
				ArrayList listPObservacion = new ArrayList();
				ArrayList listPCodigoOrden = new ArrayList();

				Iterator ordenesMedioMapIt = listOrdenMedio.keySet().iterator();

				while (ordenesMedioMapIt.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMedioMapIt
							.next();
					BigDecimal porcentajeCanje = new BigDecimal(0);
					Map<Long, CampanaProductoVersionIf> mapCampanasProductoVersion = null;
					String versionesNombres = "";
					String descuento = "";
					String porcentajeBonificacion = "";
					String observacion = "";

					if (ordenMedioIf.getObservacion() != null) {
						observacion = ordenMedioIf.getObservacion();
					}

					BigDecimal porcentajeOrdenMedioGuardado = new BigDecimal(0);
					Map mapOrdenMedioDetalle = (Map) listOrdenMedio
							.get(ordenMedioIf);
					Iterator mapOrdenMedioDetalleIt = mapOrdenMedioDetalle
							.keySet().iterator();
					if (mapOrdenMedioDetalleIt.hasNext()) {
						OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) mapOrdenMedioDetalleIt
								.next();
						porcentajeOrdenMedioGuardado = ordenMedioDetalleIf
								.getPorcentajeDescuento();
						descuento = ordenMedioDetalleIf
								.getPorcentajeDescuento().toString();
						porcentajeBonificacion = ordenMedioIf
								.getPorcentajeBonificacionCompra() != null ? ordenMedioIf
								.getPorcentajeBonificacionCompra().toString()
								: "0";
						ComercialIf comercialIf = mapaComercial.get(ordenMedioDetalleIf.getComercialId());
						DerechoProgramaIf derechoProgramaIf = mapaDerechoPrograma
								.get(comercialIf.getDerechoprogramaId());

						mapCampanasProductoVersion = getCampanasProductoVersionOfMapaOrdenMedioDetalle(mapOrdenMedioDetalle);
						for (Long idVersion : mapCampanasProductoVersion
								.keySet()) {
							versionesNombres += mapCampanasProductoVersion.get(
									idVersion).getNombre()
									+ ", ";
						}
					}

					Vector<String> filaOrdenMedio = new Vector<String>();
					filaOrdenMedio.add(String.valueOf(contador));
					filaOrdenMedio.add(proveedor.getDescripcion());
					filaOrdenMedio.add(SessionServiceLocator
							.getProductoClienteSessionService()
							.getProductoCliente(
									ordenMedioIf.getProductoClienteId())
							.getNombre());
					filaOrdenMedio.add(versionesNombres);

					if (ordenMedioIf.getId() == null) {
						filaOrdenMedio.add(porcentajeDescuentoCompraDefault
								.toString() + "%");
						filaOrdenMedio.add("NORMAL");
						porcentajeCanje = new BigDecimal(0);
					} else {
						filaOrdenMedio.add(formatoDecimal.format(Utilitarios
								.redondeoValor(porcentajeOrdenMedioGuardado))
								+ "%");
						porcentajeCanje = ordenMedioIf.getPorcentajeCanje();
						if (ordenMedioIf.getPorcentajeCanje().compareTo(
								new BigDecimal(0)) == 0) {
							filaOrdenMedio.add("NORMAL");
						} else {
							filaOrdenMedio
									.add("CANJE"
											+ "-"
											+ formatoDecimal
													.format(Utilitarios
															.redondeoValor(porcentajeCanje))
													.toString() + "%");
						}
					}

					ordenMedioCollection.add(ordenMedioIf);

					// REVISAR ESTE CONDICIONAL GIOMY 21 JUNIO
					if (!getCbPlanMedioNuevaVersion().isSelected()
							|| !getCbPlanMedioNuevaVersion().isSelected()) {
						if (nuevoPlan) {
							// filaOrdenMedio.add("Codigo Nuevo");
							filaOrdenMedio.add(ordenMedioIf.getCodigo());
						} else {
							filaOrdenMedio.add(ordenMedioIf.getCodigo());
						}
					} else {
						filaOrdenMedio.add(ordenMedioIf.getCodigo());
					}

					tblOrdenesMedios.addRow(filaOrdenMedio);
					listPDescuento.add(descuento);
					listPBonificacion.add(porcentajeBonificacion);
					listPCanje.add(porcentajeCanje);
					listPObservacion.add(observacion);
					listPCodigoOrden.add(ordenMedioIf.getCodigo());
					listObservacionesTemp.add(observacion);

					contador++;
				}

				mapOrdenMediobyProveedorPDesc.put(String.valueOf(proveedorId)
						+ "-" + numeroOrden, listPDescuento);
				mapOrdenMediobyProveedorPBono.put(String.valueOf(proveedorId)
						+ "-" + numeroOrden, listPBonificacion);
				mapOrdenMediobyProveedorPCanje.put(String.valueOf(proveedorId)
						+ "-" + numeroOrden, listPCanje);
				mapOrdenMediobyProveedorObservacion.put(
						String.valueOf(proveedorId) + "-" + numeroOrden,
						listPObservacion);
				mapOrdenMediobyProveedorPCodigoOrden.put(
						String.valueOf(proveedorId) + "-" + numeroOrden,
						listPCodigoOrden);

				Vector<String> filaOrdenMedioSubtotal = new Vector<String>();
				filaOrdenMedioSubtotal.add("-");
				filaOrdenMedioSubtotal.add("SUBTOTAL "
						+ proveedor.getDescripcion());
				filaOrdenMedioSubtotal.add(formatoDecimal.format(Utilitarios
						.redondeoValor((BigDecimal) proveedorValorSubtotal
								.get(String.valueOf(proveedorId) + "-"
										+ numeroOrden))));

				tblOrdenesMedios.addRow(filaOrdenMedioSubtotal);
				indiceProveedorSubtotalOrdenes.add(tblOrdenesMedios
						.getRowCount() - 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<Long, CampanaProductoVersionIf> getCampanasProductoVersionOfMapaOrdenMedioDetalle(
			Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> mapOrdenMedioDetalle) {
		Map<Long, CampanaProductoVersionIf> mapCampanasProductoVersion = new LinkedHashMap<Long, CampanaProductoVersionIf>();
		try {
			for (OrdenMedioDetalleIf ordenMedioDetalle : mapOrdenMedioDetalle
					.keySet()) {
				boolean existeCampanaProductoVersionIf = false;
				for (Long idCampanaProductoVersion : mapCampanasProductoVersion
						.keySet()) {
					if (idCampanaProductoVersion.compareTo(ordenMedioDetalle
							.getCampanaProductoVersionId()) == 0) {
						existeCampanaProductoVersionIf = true;
						break;
					}
				}
				if (!existeCampanaProductoVersionIf) {
					CampanaProductoVersionIf campanaProductoVersion = SessionServiceLocator
							.getCampanaProductoVersionSessionService()
							.getCampanaProductoVersion(
									ordenMedioDetalle
											.getCampanaProductoVersionId());
					mapCampanasProductoVersion.put(
							campanaProductoVersion.getId(),
							campanaProductoVersion);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return mapCampanasProductoVersion;
	}

	// END 14 OCTUBRE

	private String getCodigo(java.sql.Date fecha) {
		String codigo = "";
		String anio = Utilitarios.getYearFromDate(fecha);
		codigo += anio + "-";
		return codigo;
	}

	public void clean() {
		getCbPrepago().setSelected(false);
		setearDescuentoCompraPredeterminado();
		getCbPautaTelevision().setSelected(true);
		getCbPautaRadio().setSelected(false);
		getCbPautaCine().setSelected(false);
		ordenesMedioMap.clear();
		nuevoPlan = false;
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
		planMedioMesArray = null;
		planMedioMesArray = new ArrayList<PlanMedioMesIf>();
		planMedioIf = null;
		getCmbTipoPeriodo().removeAllItems();
		tableSubPeriodo = null;
		mapaProductoClienteOrdenesComp.clear();
		proveedorValorSubtotal.clear();
		indiceProveedorSubtotalOrdenes.clear();
		indiceProveedorSubtotalOrdenes = new ArrayList<Integer>();
		cleanTablaOrdenesMedioCmp();
		mapaProveedorOrdenes.clear();
		mapaProveedorOrdenesComp.clear();
		mapaProveedorOrdenesSaved.clear();
		mapaProveedorOrdenesCompSaved.clear();
		mapOrdenMediobyProveedorObservacion.clear();
		listObservacionesTemp.clear();
		getCbAgrupaOrdenes().setSelected(false);
		planMedioTipo = null;
		planMedioTipo = PLAN_MEDIO_TIPO_NORMAL;// ""; MODIFIED 6 JULIO
		getCbPlanMedioNuevaVersion().setSelected(false);
		getCbPlanMedioNuevoMes().setSelected(false);
		getTextPlanMedioRelacion().setText(null);
		planMedioOriginalIf = null;
		cleanTablaOrdenesMedio();
		cleanDatosPlanMedioSaved();
		mapProductoClienteByProveedorCodigoOrdenMedio.clear();
		mapCodigoOrdenMedioByProveedor.clear();
		mapaProductoClienteVersionOrdenesComp.clear();

		getCmbFechaAprobacion().setSelectedItem(null);
		getCmbPeriodoFechaInicio().setCalendar(null);
		getCmbPeriodoFechaFin().setCalendar(null);
		getCmbSubPeriodoFechaInicio().setCalendar(null);
		getCmbSubPeriodoFechaFin().setCalendar(null);
		calendarFechaInicio = null;
		calendarFechaFin = null;
		Calendar calendar = new GregorianCalendar();
		calendarFechaInicio = calendar;
		calendarFechaFin = calendar;

		getTxtCodigo().setText("");
		getTxtRevision().setText("");
		getTxtCorporacion().setText("");
		getTxtCliente().setText("");
		getTxtOficina().setText("");
		getTxtConcepto().setText("");
		getTxtCampana().setText("");
		getCmbOrdenTrabajo().setSelectedIndex(-1);
		getCmbOrdenTrabajo().setEnabled(false);
		getTxtAutorizacionSAP().setText("");

		getCmbTarget().setSelectedIndex(-1);
		getCmbTarget().repaint();
		getTxtGuayaquil().setText("");
		getTxtQuito().setText("");
		getTxtTotalCiudad().setText("");
		getBtnGuayaquil().setEnabled(false);
		getBtnQuito().setEnabled(false);
		getBtnTotalCiudad().setEnabled(false);

		getCmbPeriodoFechaInicio().setCalendar(calendar);
		getCmbPeriodoFechaFin().setCalendar(calendar);

		getCmbSubPeriodoFechaInicio().setCalendar(calendar);
		getCmbSubPeriodoFechaFin().setCalendar(calendar);
		getCmbPeriodoFechaInicio().setEnabled(false);
		getCmbPeriodoFechaFin().setEnabled(false);
		getCmbSubPeriodoFechaInicio().setEnabled(false);
		getCmbSubPeriodoFechaFin().setEnabled(false);
		getTxtCobertura().setText("");
		getTxtOtrasConsideraciones().setText("");
		getTxtSubtotalPlanMedio().setText("");
		getTxtDescuentoPlanMedio().setText("");
		getTxtSumaPlanMedio().setText("");
		getTxtIvaPlanMedio().setText("");
		getTxtTotalPlanMedio().setText("");
		getTxtComisionAgencia().setText("");
		getTxtModificaciones().setText("");

		getTxtSuman().setText("");
		getTxtDescuento().setText("");
		getTxtBonificacionCompra().setText("");
		getTxtSubtotalBonificacionCompra().setText("");
		getTxtSubTotal().setText("");
		getTxtIVA().setText("");
		getTxtTotalPauta().setText("");
		getTxtPorcentajeDescuentoVenta().setText("");
		getTxtPorcentajeComisionAgencia().setText("");
		getTxtPorcentajeBonificacionVenta().setText("");
		getTxtDescuentoVenta().setText("");
		getTxtComisionAgencia().setText("");
		getTxtComisionAgenciaPlanMedio().setText("");
		getTxtSubtotalVenta().setText("");
		getTxtBonificacionVenta().setText("");
		getTxtBonificacionVentaPlanMedio().setText("");
		getTxtSubtotalBonificacionVenta().setText("");
		getTxtSubtotalBonificacionVentaPlanMedio().setText("");
		getTxtSubtotalPlanMedio().setText("");
		getTxtSuman2().setText("");
		getTxtIVA2().setText("");
		getTxtIvaPlanMedio().setText("");
		getTxtTotalPauta2().setText("");
		getTxtTotalPlanMedio().setText("");

		getTxtTotalOrdenMedio().setText("");
		getTxtSubtotalOrdenMedio().setText("");
		getTxtSumanOrdenMedio().setText("");
		getTxtPorcentajeDescuentoOrdenMedio().setText("");
		getTxtPorcentajeComisionAdicional().setText("");
		getTxtPorcentajeBonificacionCompra().setText("");
		getTxtIVAOrdenMedio().setText("");
		getTxtDescuentoOrdenMedio().setText("");
		getTxtBonificacionCompraOrden().setText("");

		getTxtPorcentajeCanje().setText("");
		getTxtPorcentajeCanje().setEnabled(false);
		getTxtPorcentajeComisionAdicional().setText("");
		getTxtPorcentajeComisionAdicional().setEnabled(false);
		getCbComisionAdicional().setSelected(false);
		getRbTipoPagoNormal().setSelected(true);
		getCbTipoPagoComision().setSelected(false);

		DefaultTableModel modelSubPeriodo = (DefaultTableModel) getTblSubPeriodo()
				.getModel();
		for (int i = this.getTblSubPeriodo().getRowCount(); i > 0; --i)
			modelSubPeriodo.removeRow(i - 1);

		getCbAgrupaOrdenes().setSelected(true);
		getCbPautaBasica().setSelected(false);
		getCbOrdenPorProductoComercial().setSelected(false);
		getCbOrdenPorVersion().setSelected(false);

		getCmbTipoPeriodo().removeAllItems();
		cleanTablaComercial();
	}

	private void cleanTablaComercial() {
		DefaultTableModel modelComercial = (DefaultTableModel) getTblComercial()
				.getModel();
		for (int i = this.getTblComercial().getRowCount(); i > 0; --i)
			modelComercial.removeRow(i - 1);
	}

	private void cleanTablaTGRPtv() {
		DefaultTableModel modelTGRPtv = (DefaultTableModel) getTblTGRPtv()
				.getModel();
		for (int i = this.getTblTGRPtv().getRowCount(); i > 0; --i)
			modelTGRPtv.removeRow(i - 1);
	}

	private void cleanTablaOrdenesMedio() {
		DefaultTableModel modelOrdenesMedio = (DefaultTableModel) getTblOrdenesMedios()
				.getModel();
		for (int i = this.getTblOrdenesMedios().getRowCount(); i > 0; --i)
			modelOrdenesMedio.removeRow(i - 1);
	}

	// ADD 21 JUNIO
	public void cleanDatosPlanMedioSaved() {
		planMedioIfSaved = null;
		setearDescuentoCompraPredeterminado();
		ordenesMediosMapCompSaved.clear();
		ordenesMedioMapSaved.clear();
		mapOrdenMediobyProveedorSaved.clear();
		mapOrdenMediobyProveedorPCanjeSaved.clear();

		mapOrdenMediobyProveedorPDescSaved.clear();
		mapasComercialesPlantillaSaved.clear();
		listaProveedoresMapSaved.clear();
		clienteOficinaIfSaved = null;
		// genericoPautaRegularTvSaved.
		mapasComercialesPlantillaMultipleSaved.clear();
		planMedioDetallesPlantillaSaved.clear();
		ordenMedioCollectionSaved.clear();
		mapaProductoClienteOrdenesSaved.clear();
		// mapaProductoClienteOrdenesCompSaved.clear(); COMENTED 5 SEPTIEMBRE
		mapaProveedorOrdenesSaved.clear();
		mapaProveedorOrdenesCompSaved.clear();
		mapOrdenMediobyProveedorbservacionSaved.clear();
		proveedorValorSubtotalSaved = null;
		proveedorValorSubtotalSaved = new LinkedHashMap<String, BigDecimal>();
		mapOrdenMediobyProveedorObservacionSaved.clear();
		indiceProveedorSubtotalOrdenesSaved = new ArrayList<Integer>();
		listObservacionesTempSaved = new ArrayList<String>();

		filaSeleccionadaSaved = -1;

		// ADD 23 JUNIO
		// MODIFIED 27 JUNIO
		// mapProductoClienteByProveedorCodigoOrdenMedioSaved.clear();
		mapProductoClienteByProveedorCodigoEstadoOrdenMedioSaved.clear();
		mapCodigoOrdenMedioByProveedorSaved.clear();
		// MODIFIED 27 JUNIO
		// ADD 20 JULIO
		mapCodigoEstadoOrdenMedioByProveedorSaved.clear();
		// END 20 JULIO
		// listIdsOrdenesMedioSaved.clear();
		// MODIFIED 28 JUNIO
		// listIdsEstadosOrdenesMedioSaved.clear();
		listIdsCodigoEstadoOrdenesMedioSaved.clear();
		// END 23 JUNIO
		// ADD 29 JUNIO
		mapOrdenMediobyProveedorPCodigoOrdenSaved.clear();
		// END 29 JUNIO
		// ADD 1 JULIO
		mapFechaInicioFinPlanesMedioHermanosSaved.clear();
		// END 1 JULIO
		// ADD 5 SEPTIEMBRE
		mapaProductoClienteVersionesOrdenesCompSaved.clear();
		mapProductoClienteVersionByProveedorCodigoEstadoOrdenMedioSaved.clear();
	}

	// END 21 JUNIO

	// ADD 15 ABRIL
	private void cleanTablaOrdenesMedioCmp() {
		DefaultTableModel modelOrdenesMedioCmp = (DefaultTableModel) getTblOrdenesMediosCmp()
				.getModel();
		for (int i = this.getTblOrdenesMediosCmp().getRowCount(); i > 0; --i)
			modelOrdenesMedioCmp.removeRow(i - 1);
	}

	// END 15 ABRIL

	private void cleanPrensa() {
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

	private void cleanTv() {
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

	public void report() {
		try {
			if (planMedioIf != null) {
				Map<OrdenMedioIf, List<OrdenMedioDetalleIf>> mapaOrdenesCreadas = new LinkedHashMap<OrdenMedioIf, List<OrdenMedioDetalleIf>>();
				Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaComercialOrdenesCompCreadas = new LinkedHashMap<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>();

				Collection ordenesMedios = SessionServiceLocator
						.getOrdenMedioSessionService()
						.findOrdenMedioByPlanMedioId(planMedioIf.getId());

				Iterator ordenesMediosIt = ordenesMedios.iterator();
				while (ordenesMediosIt.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) ordenesMediosIt
							.next();
					Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenMedioDetalleHashM = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();

					List<OrdenMedioDetalleIf> ordenesMediosDetalle = (ArrayList<OrdenMedioDetalleIf>) SessionServiceLocator
							.getOrdenMedioDetalleSessionService()
							.findOrdenMedioDetalleByOrdenMedioId(
									ordenMedioIf.getId());
					for (OrdenMedioDetalleIf ordenMedioDetalle : ordenesMediosDetalle) {
						ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = (ArrayList<OrdenMedioDetalleMapaIf>) SessionServiceLocator
								.getOrdenMedioDetalleMapaSessionService()
								.findOrdenMedioDetalleMapaByOrdenMedioDetalleId(
										ordenMedioDetalle.getId());
						ordenMedioDetalleHashM.put(ordenMedioDetalle,
								ordenMedioDetalleMapaList);
					}

					porcentajeDescuentoTotal = ordenesMediosDetalle.get(0)
							.getPorcentajeDescuento();
					mapaComercialOrdenesCompCreadas.put(ordenMedioIf,
							ordenMedioDetalleHashM);

				}

				reportePlanMedio();

				if (!mapaComercialOrdenesCompCreadas.isEmpty()) {
					reporteOrdenMedio(mapaComercialOrdenesCompCreadas);

				} else {
					int opcion = JOptionPane
							.showOptionDialog(
									null,
									"No se han creado las órdenes de medios, ¿Desea crearlas?",
									"Confirmación", JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									options, no);
					if (opcion == JOptionPane.YES_OPTION) {
						setearGenericoPautaRegular();
					}
				}

			} else {
				SpiritAlert.createAlert("Debe abrir primero un Plan de Medio.",
						SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void reportePlanMedio() {
		int opcion = JOptionPane.showOptionDialog(null,	"¿Desea generar el reporte del Plan de Medios?", "Confirmación", JOptionPane.YES_NO_OPTION,	JOptionPane.QUESTION_MESSAGE, null, options, no);
		if (opcion == JOptionPane.YES_OPTION) {
			try {
				String fileName = "jaspers/medios/RPPlanMediosTv3.jasper";
				
				String fechaActual = Utilitarios.dateHoraHoy();
				String year = fechaActual.substring(0, 4);
				String month = fechaActual.substring(5, 7);
				String day = fechaActual.substring(8, 10);
				String fechaEmision = day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)).toLowerCase() + " del " + year;
				
				ClienteIf clienteIf = (ClienteIf) SessionServiceLocator.getClienteSessionService().findClienteByPlanMedioId(planMedioIf.getId()).iterator().next();
				
				HashMap parametrosMap = new HashMap();
				parametrosMap.put("usuario", Parametros.getUsuario().toLowerCase());
				
				EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
				parametrosMap.put("empresa", empresa.getNombre());
				
				OficinaIf oficina = (OficinaIf) Parametros.getOficina();
				parametrosMap.put("direccion", oficina.getDireccion());
				
				parametrosMap.put("telefono", "Teléfono: " + oficina.getTelefono() + "    Fax: " + oficina.getFax());
				parametrosMap.put("ruc", "RUC: " + empresa.getRuc());
				parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
				parametrosMap.put("fechaEmision", fechaEmision.toUpperCase());
				parametrosMap.put("mes", Utilitarios.getFechaMesAnioUppercase(planMedioIf.getFechaInicio()));
				parametrosMap.put("cliente", clienteIf.getNombreLegal());
				parametrosMap.put("codigo", planMedioIf.getCodigo());
				
				GrupoObjetivoIf grupoObjetivo = SessionServiceLocator.getGrupoObjetivoSessionService().getGrupoObjetivo(planMedioIf.getGrupoObjetivoId());
				parametrosMap.put("target", grupoObjetivo.getNombre());
				
				CampanaIf campana = (CampanaIf) SessionServiceLocator.getCampanaSessionService().findCampanaByPlanMedioId(planMedioIf.getId()).iterator().next();
				parametrosMap.put("campana", campana.getNombre());
				
				OrdenTrabajoIf ot = (OrdenTrabajoIf) SessionServiceLocator.getOrdenTrabajoSessionService().findOrdenTrabajoByCampanaId(campana.getId()).iterator().next();
				parametrosMap.put("ordenTrabajo", ot.getCodigo());
				
				String elaboradoPor = "";
				if (planMedioIf.getUsuarioId() != null) {
					UsuarioIf usuario = SessionServiceLocator.getUsuarioSessionService().getUsuario(planMedioIf.getUsuarioId());
					EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(usuario.getEmpleadoId());
					elaboradoPor = empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0];
				}				
				parametrosMap.put("elaboradoPor", elaboradoPor);
				
				Iterator<PlanMedioDetalleIf> it = planMedioDetallesPlantilla.iterator();
				int i = 0;
				List<PlanMedioReporteData> planMedioReporteDataLista = new ArrayList<PlanMedioReporteData>();
				String producto = "";
				Double total = 0D;
				String versiones = "";
				String[] versionSplit;
								
				
				//////////////////CREACION DE ATRIBUTOS PARA EL RESUMEN TV POR CANAL////////////////////////
				
				Map<Integer, Map<String, Object[]>> mapaOrdenProveedorResumenCanal = new HashMap<Integer, Map<String, Object[]>>();
								
				//////////////////CREACION DE ATRIBUTOS PARA EL RESUMEN TV POR SEMANA////////////////////////
				//para el resumen por semanas divido el mes en semanas
				java.util.Date primeraFechaMes = Utilitarios.getFirstDateFromMonth(planMedioIf.getFechaInicio());
				int totalDiasMes = Utilitarios.getLastDayOfMonth(planMedioIf.getFechaInicio().getMonth(), planMedioIf.getFechaInicio().getYear());
				java.util.Date ultimaFechaMes = Utilitarios.getLastDateFromMonth(planMedioIf.getFechaInicio());
				
				//agrupo dias del mes en semanas de lunes a domingo
				//en este
				Map<Integer,Object[]> semanaDatos = new HashMap<Integer,Object[]>();
				//en este mapa pongo en que semana esta cada dia
				Map<Integer,Integer> diaSemana = new HashMap<Integer,Integer>();
				//en este mapa pongo en numero de la semana con sus dias inicio y fin
				Map<Integer,Integer[]> semanas = new HashMap<Integer,Integer[]>();
				primeraFechaMes.setDate(1);
				int semana = 1;
				for(int j=0; j<totalDiasMes; j++){
					primeraFechaMes.setDate(j + 1);	
					diaSemana.put(primeraFechaMes.getDate(), semana);
					
					//composicion de la semana
					if(semanas.get(semana) == null){
						Integer[] principioFinSemana = new Integer[2];
						principioFinSemana[0] = j+1;
						principioFinSemana[1] = j+1;
						semanas.put(semana, principioFinSemana);
					}else{
						Integer[] principioFinSemana = semanas.get(semana);
						principioFinSemana[1] = j+1;
						semanas.put(semana, principioFinSemana);
					}					
					
					//cuando el día es domingo(día 0) se cambia de semana
					if(primeraFechaMes.getDay() == 0){
						semana++;
					}				
				}
				
				//////////////////CREACION DE ATRIBUTOS PARA EL RESUMEN TV POR FRANJA////////////////////////
				
				Map<Integer, Object[]> mapaFranjaObjectos = new HashMap<Integer, Object[]>();				
				
				/////////////////////////////////////////////////////////////////////////////////////////////
				
				while (it.hasNext()) {
					PlanMedioDetalleIf planMedioDetalle = it.next();
					
					Object[] tgrp = datosTGRP[i];
					Object[] pauta = datosTv[i];
					
					PlanMedioReporteData planMedioReporteData = new PlanMedioReporteData();
					
					String hora = (String) tgrp[1];
					planMedioReporteData.setHora(hora);
					planMedioReporteData.setProveedorOrden(String.valueOf(planMedioDetalle.getNumeroOrdenAgrupacion()) + "-" + ((String)tgrp[2]).replaceAll("-", " "));
					planMedioReporteData.setPrograma((String) tgrp[3]);
					
					Long comercialId = planMedioDetalle.getComercialId();
					ComercialIf comercialIf = mapaComercial.get(comercialId);
					
					if (getMode() == SpiritMode.UPDATE_MODE) {
						String[] version = ((String) tgrp[4]).split(",");
						planMedioReporteData.setVersion(planMedioDetalle.getVersion() + " (" + version[1] + ", " + comercialIf.getDuracion() + "'')");
					} else {
						String[] version = ((String) tgrp[4]).split(",");
						planMedioReporteData.setVersion(planMedioDetalle.getVersion() + " (" + version[0] + ", " + comercialIf.getDuracion() + "'')");
					}
									
					planMedioReporteData.setTotal(formatoDecimal.format(Utilitarios.redondeoValor((Double) tgrp[11])));
					planMedioReporteData.setDiaLetra01(!((String) pauta[5]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[5]) : 0);
					planMedioReporteData.setDiaLetra02(!((String) pauta[6]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[6]) : 0);
					planMedioReporteData.setDiaLetra03(!((String) pauta[7]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[7]) : 0);
					planMedioReporteData.setDiaLetra04(!((String) pauta[8]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[8]) : 0);
					planMedioReporteData.setDiaLetra05(!((String) pauta[9]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[9]) : 0);
					planMedioReporteData.setDiaLetra06(!((String) pauta[10]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[10]) : 0);
					planMedioReporteData.setDiaLetra07(!((String) pauta[11]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[11]) : 0);
					planMedioReporteData.setDiaLetra08(!((String) pauta[12]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[12]) : 0);
					planMedioReporteData.setDiaLetra09(!((String) pauta[13]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[13]) : 0);
					planMedioReporteData.setDiaLetra10(!((String) pauta[14]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[14]) : 0);
					planMedioReporteData.setDiaLetra11(!((String) pauta[15]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[15]) : 0);
					planMedioReporteData.setDiaLetra12(!((String) pauta[16]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[16]) : 0);
					planMedioReporteData.setDiaLetra13(!((String) pauta[17]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[17]) : 0);
					planMedioReporteData.setDiaLetra14(!((String) pauta[18]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[18]) : 0);
					planMedioReporteData.setDiaLetra15(!((String) pauta[19]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[19]) : 0);
					planMedioReporteData.setDiaLetra16(!((String) pauta[20]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[20]) : 0);
					planMedioReporteData.setDiaLetra17(!((String) pauta[21]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[21]) : 0);
					planMedioReporteData.setDiaLetra18(!((String) pauta[22]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[22]) : 0);
					planMedioReporteData.setDiaLetra19(!((String) pauta[23]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[23]) : 0);
					planMedioReporteData.setDiaLetra20(!((String) pauta[24]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[24]) : 0);
					planMedioReporteData.setDiaLetra21(!((String) pauta[25]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[25]) : 0);
					planMedioReporteData.setDiaLetra22(!((String) pauta[26]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[26]) : 0);
					planMedioReporteData.setDiaLetra23(!((String) pauta[27]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[27]) : 0);
					planMedioReporteData.setDiaLetra24(!((String) pauta[28]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[28]) : 0);
					planMedioReporteData.setDiaLetra25(!((String) pauta[29]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[29]) : 0);
					planMedioReporteData.setDiaLetra26(!((String) pauta[30]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[30]) : 0);
					planMedioReporteData.setDiaLetra27(!((String) pauta[31]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[31]) : 0);
					planMedioReporteData.setDiaLetra28(!((String) pauta[32]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[32]) : 0);
					planMedioReporteData.setDiaLetra29(!((String) pauta[33]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[33]) : 0);
					planMedioReporteData.setDiaLetra30(!((String) pauta[34]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[34]) : 0);
					planMedioReporteData.setDiaLetra31(!((String) pauta[35]).equals(SpiritConstants.getEmptyCharacter()) ? Integer.parseInt((String) pauta[35]) : 0);
					
					//SETEOS RATINGS, TRPS, TARIFAS					
					planMedioReporteData.setCunias(String.valueOf((Integer) tgrp[5]));
					planMedioReporteData.setGye(String.valueOf((BigDecimal) tgrp[6]));
					planMedioReporteData.setUio(String.valueOf((BigDecimal) tgrp[7]));
					planMedioReporteData.setPon(String.valueOf((BigDecimal) tgrp[8]));
					planMedioReporteData.setTgrpsgye(String.valueOf((Double) tgrp[12]));
					planMedioReporteData.setTgrpsuio(String.valueOf((Double) tgrp[13]));
					planMedioReporteData.setTgrps(String.valueOf((Double) tgrp[9]));
					planMedioReporteData.setTarifaBig((BigDecimal) tgrp[10]);
					planMedioReporteData.setTarifa(formatoDecimal.format(Utilitarios.redondeoValor((BigDecimal) tgrp[10])));
					
					total += (Double) tgrp[11];
					
					//////////////RESUMENES POR CANAL, SEMANA Y TRPS /////////////
					
					//solo para TV
					if(getCbPautaTelevision().isSelected()){
						String canal = (String)tgrp[2];
						Integer cunias = (Integer)tgrp[5];
						BigDecimal inversion = ((BigDecimal)tgrp[10]).multiply(BigDecimal.valueOf(cunias));
						Double tgrpGye = (Double)tgrp[12];
						Double tgrpUio = (Double)tgrp[13];
						Double tgrpNac = (Double)tgrp[9];
						
						Double cpr = 0D;
						if(tgrpNac > 0){
							cpr = inversion.doubleValue() / tgrpNac;
						}
						
						//////////////DATOS PARA RESUMEN TRPS POR CANAL //////////////
						
						if(mapaOrdenProveedorResumenCanal.get(planMedioDetalle.getNumeroOrdenAgrupacion()) == null){
							Map<String, Object[]> proveedorResumenCanal = new HashMap<String, Object[]>();
							Object[] resumenCanal = {cunias, inversion, tgrpGye, tgrpUio, tgrpNac, cpr};
							proveedorResumenCanal.put(canal, resumenCanal);
							mapaOrdenProveedorResumenCanal.put(planMedioDetalle.getNumeroOrdenAgrupacion(), proveedorResumenCanal);
							
						}else{
							Map<String, Object[]> proveedorResumenCanal = mapaOrdenProveedorResumenCanal.get(planMedioDetalle.getNumeroOrdenAgrupacion());
							
							if(proveedorResumenCanal.get(canal) == null){
								Object[] resumenCanal = {cunias, inversion, tgrpGye, tgrpUio, tgrpNac, cpr};
								proveedorResumenCanal.put(canal, resumenCanal);
							}else{
								Object[] resumenCanal = proveedorResumenCanal.get(canal);
								resumenCanal[0] = (Integer)resumenCanal[0] + cunias;
								resumenCanal[1] = ((BigDecimal)resumenCanal[1]).add(inversion);
								resumenCanal[2] = (Double)resumenCanal[2] + tgrpGye;
								resumenCanal[3] = (Double)resumenCanal[3] + tgrpUio;
								resumenCanal[4] = (Double)resumenCanal[4] + tgrpNac;
								
								if(((Double)resumenCanal[4]) > 0){
									resumenCanal[5] = ((BigDecimal)resumenCanal[1]).doubleValue() / ((Double)resumenCanal[4]);
								}else{
									resumenCanal[5] = 0D;
								}
								
								proveedorResumenCanal.put(canal, resumenCanal);
							}						
							
							mapaOrdenProveedorResumenCanal.put(planMedioDetalle.getNumeroOrdenAgrupacion(), proveedorResumenCanal);
						}			
									
						/////////////////DATOS PARA RESUMEN TRPS POR SEMANA //////////////
						
						BigDecimal tarifa = ((BigDecimal)tgrp[10]);
						BigDecimal raitingGye = (BigDecimal)tgrp[6];
						BigDecimal raitingUio = (BigDecimal)tgrp[7];
						BigDecimal raitingNac = (BigDecimal)tgrp[8];
						
						for(int h=5; h<=35; h++){
							if(!((String) pauta[h]).equals(SpiritConstants.getEmptyCharacter())){
								
								int semanaTemp = diaSemana.get(h-4);
								int spots = Integer.parseInt((String) pauta[h]);
								BigDecimal inversionTemp = ((BigDecimal)tgrp[10]).multiply(BigDecimal.valueOf(spots));
								BigDecimal trpsGye = raitingGye.multiply(BigDecimal.valueOf(spots));
								BigDecimal trpsUio = raitingUio.multiply(BigDecimal.valueOf(spots));
								BigDecimal trpsNac = raitingNac.multiply(BigDecimal.valueOf(spots));
								
								if(semanaDatos.get(semanaTemp) == null){							
									Object[] resumenSemana = {spots, inversionTemp, trpsGye, trpsUio, trpsNac};
									semanaDatos.put(semanaTemp, resumenSemana);
								}else{
									Object[] resumenSemana = semanaDatos.get(semanaTemp);
									resumenSemana[0] = (Integer)resumenSemana[0] + spots;
									resumenSemana[1] = ((BigDecimal)resumenSemana[1]).add(inversionTemp);
									resumenSemana[2] = ((BigDecimal)resumenSemana[2]).add(trpsGye);
									resumenSemana[3] = ((BigDecimal)resumenSemana[3]).add(trpsUio);
									resumenSemana[4] = ((BigDecimal)resumenSemana[4]).add(trpsNac);
									
									semanaDatos.put(semanaTemp, resumenSemana);
								}						
							}
						}
						
						/////////////////DATOS PARA RESUMEN TRPS POR FRANJA //////////////
						
						//divido hora en horas y minutos
						String horaFormato = hora.replaceAll("h", "H").replaceAll(":", "H");
						int horas = Integer.valueOf(horaFormato.split("H")[0]);
						
						if(horas >= 6 && horas <= 12){
							if(mapaFranjaObjectos.get(1) == null){
								Object[] resumenFranja = {cunias, inversion, tgrpGye, tgrpUio, tgrpNac, cpr};
								mapaFranjaObjectos.put(1, resumenFranja);
							}else{
								Object[] resumenFranja = mapaFranjaObjectos.get(1);
								resumenFranja[0] = (Integer)resumenFranja[0] + cunias;
								resumenFranja[1] = ((BigDecimal)resumenFranja[1]).add(inversion);
								resumenFranja[2] = (Double)resumenFranja[2] + tgrpGye;
								resumenFranja[3] = (Double)resumenFranja[3] + tgrpUio;
								resumenFranja[4] = (Double)resumenFranja[4] + tgrpNac;
								
								if(((Double)resumenFranja[4]) > 0){
									resumenFranja[5] = ((BigDecimal)resumenFranja[1]).doubleValue() / ((Double)resumenFranja[4]);
								}else{
									resumenFranja[5] = 0D;
								}
								
								mapaFranjaObjectos.put(1, resumenFranja);
							}					
							
						}else if(horas >= 13 && horas <= 18){
							if(mapaFranjaObjectos.get(2) == null){
								Object[] resumenFranja = {cunias, inversion, tgrpGye, tgrpUio, tgrpNac, cpr};
								mapaFranjaObjectos.put(2, resumenFranja);
							}else{
								Object[] resumenFranja = mapaFranjaObjectos.get(2);
								resumenFranja[0] = (Integer)resumenFranja[0] + cunias;
								resumenFranja[1] = ((BigDecimal)resumenFranja[1]).add(inversion);
								resumenFranja[2] = (Double)resumenFranja[2] + tgrpGye;
								resumenFranja[3] = (Double)resumenFranja[3] + tgrpUio;
								resumenFranja[4] = (Double)resumenFranja[4] + tgrpNac;
								
								if(((Double)resumenFranja[4]) > 0){
									resumenFranja[5] = ((BigDecimal)resumenFranja[1]).doubleValue() / ((Double)resumenFranja[4]);
								}else{
									resumenFranja[5] = 0D;
								}
								
								mapaFranjaObjectos.put(2, resumenFranja);
							}
							
						}else if(horas >= 19 && horas <= 22){
							if(mapaFranjaObjectos.get(3) == null){
								Object[] resumenFranja = {cunias, inversion, tgrpGye, tgrpUio, tgrpNac, cpr};
								mapaFranjaObjectos.put(3, resumenFranja);
							}else{
								Object[] resumenFranja = mapaFranjaObjectos.get(3);
								resumenFranja[0] = (Integer)resumenFranja[0] + cunias;
								resumenFranja[1] = ((BigDecimal)resumenFranja[1]).add(inversion);
								resumenFranja[2] = (Double)resumenFranja[2] + tgrpGye;
								resumenFranja[3] = (Double)resumenFranja[3] + tgrpUio;
								resumenFranja[4] = (Double)resumenFranja[4] + tgrpNac;
								
								if(((Double)resumenFranja[4]) > 0){
									resumenFranja[5] = ((BigDecimal)resumenFranja[1]).doubleValue() / ((Double)resumenFranja[4]);
								}else{
									resumenFranja[5] = 0D;
								}
								
								mapaFranjaObjectos.put(3, resumenFranja);
							}
							
						}else{
							if(mapaFranjaObjectos.get(4) == null){
								Object[] resumenFranja = {cunias, inversion, tgrpGye, tgrpUio, tgrpNac, cpr};
								mapaFranjaObjectos.put(4, resumenFranja);
							}else{
								Object[] resumenFranja = mapaFranjaObjectos.get(4);
								resumenFranja[0] = (Integer)resumenFranja[0] + cunias;
								resumenFranja[1] = ((BigDecimal)resumenFranja[1]).add(inversion);
								resumenFranja[2] = (Double)resumenFranja[2] + tgrpGye;
								resumenFranja[3] = (Double)resumenFranja[3] + tgrpUio;
								resumenFranja[4] = (Double)resumenFranja[4] + tgrpNac;
								
								if(((Double)resumenFranja[4]) > 0){
									resumenFranja[5] = ((BigDecimal)resumenFranja[1]).doubleValue() / ((Double)resumenFranja[4]);
								}else{
									resumenFranja[5] = 0D;
								}
								
								mapaFranjaObjectos.put(4, resumenFranja);
							}
						}
					}								
					
					//////////////////////////////////////////////////////////////////
					
					
					//SE SETEAN LOS DIAS EN LETRA PARA EL REPORTE
					for (int j = 0; j < 31; j++) {
						setDiaLetraPauta(planMedioReporteData,	j + 1);
					}
					
					planMedioReporteDataLista.add(planMedioReporteData);
														
					ProductoClienteIf productoCliente = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(planMedioDetalle.getProductoClienteId());
					
					if (!producto.contains(productoCliente.getNombre()))
						producto += productoCliente.getNombre() + ", ";
					
					if (planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) == 0) {
						
						String programa = planMedioDetalle.getPrograma();
						String comercial = planMedioDetalle.getComercial();
						
						// 0 version, 1 derecho de programa, 2 orden
						String[] comercialSplit = comercial.split(","); 
						
						String productosCanal = "";
						String productoCanalVersion = "";
						String versionesCanal = "";
						String versionCanal = "";
						Long derechoPrograma = comercialIf.getDerechoprogramaId();
						DerechoProgramaIf derechoProgramaIf = mapaDerechoPrograma.get(derechoPrograma);
						
						if (derechoProgramaIf.getNombre().trim().compareTo("CUÑA") == 0) {
							versionesCanal = comercialIf.getVersion() + " (" + comercialIf.getDuracion() + ") "	+ comercialIf.getNombre();
						} else {
							Map aMap = new HashMap();
							aMap.put("campanaId", comercialIf.getCampanaId());
							aMap.put("productoClienteId", comercialIf.getProductoClienteId());
							Collection<ComercialIf> comercialIfColl = SessionServiceLocator.getComercialSessionService().findComercialByQuery(aMap);
							
							if (!comercialIfColl.isEmpty()) {
								for (ComercialIf comercialIfTemp : comercialIfColl) {
									Long derechoProgramaTemp = comercialIfTemp.getDerechoprogramaId();
									DerechoProgramaIf derechoProgramaIfTemp = mapaDerechoPrograma.get(derechoProgramaTemp);
									versionesCanal = comercialIf.getVersion() + " (" + comercialIf.getDuracion() + ") " + comercialIf.getNombre();
									break;
								}
							}
						}

						versionCanal = versionesCanal; // ADD 22 OCTUBRE

						if (versiones.length() > 0) {
							versionSplit = versiones.split(", ");
							boolean isVersion = false;
							for (int j = 0; j < versionSplit.length; j++) {
								String versionTemp = versionSplit[j];
								if (versionTemp.compareTo(versionCanal) == 0) {
									isVersion = true;
								}
							}
							if (!isVersion) {
								versiones = versiones + versionesCanal + ", ";
							}
						} else {
							versiones = versionesCanal + ", ";
						}
						
					} else if (planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_PRODUCTO_COMERCIAL) == 0
							|| planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_VERSION) == 0) {
						
						String programa = planMedioDetalle.getPrograma();
						String comercial = planMedioDetalle.getComercial();						
						String versionesProductoComercial = "";
						String versionProductoComercial = "";
						Long derechoPrograma = comercialIf.getDerechoprogramaId();
						DerechoProgramaIf derechoProgramaIf = mapaDerechoPrograma.get(derechoPrograma);
						
						if (derechoProgramaIf.getNombre().trim().compareTo("CUÑA") == 0) {
							versionesProductoComercial = comercialIf.getVersion() + " (" + comercialIf.getDuracion()	+ ") " + comercialIf.getNombre();
						} else {
							Map aMap = new HashMap();
							aMap.put("campanaId", comercialIf.getCampanaId());
							aMap.put("productoClienteId", comercialIf.getProductoClienteId());
							Collection<ComercialIf> comercialIfColl = SessionServiceLocator.getComercialSessionService().findComercialByQuery(aMap);
							
							if (!comercialIfColl.isEmpty()) {
								for (ComercialIf comercialIfTemp : comercialIfColl) {
									Long derechoProgramaTemp = comercialIfTemp.getDerechoprogramaId();
									DerechoProgramaIf derechoProgramaIfTemp = mapaDerechoPrograma.get(derechoProgramaTemp);
									versionesProductoComercial = comercialIf.getVersion() + " (" + comercialIf.getDuracion() + ") " + comercialIf.getNombre();
									break;
								}
							}
						}
						versionProductoComercial = versionesProductoComercial;

						if (versiones.length() > 0) {
							versionSplit = versiones.split(", ");
							boolean isVersion = false;
							for (int j = 0; j < versionSplit.length; j++) {
								String versionTemp = versionSplit[j];
								if (versionTemp.compareTo(versionProductoComercial) == 0) {
									isVersion = true;
								}
							}
							if (!isVersion) {
								versiones = versiones + versionesProductoComercial + ", ";
							}
						} else {
							versiones = versionesProductoComercial + ", ";
						}
					}

					i++;
				}
				
				///////////////CREACION DE VECTORES PARA LOS RESUMENES DE TV ////////////////////////
				
				String tipoPauta = "";
				//creo el vector de datos para el resumen trps por canal
				List<ResumenTrpsCanalData> resumenTrpsCanalList = new ArrayList<ResumenTrpsCanalData>();
				if(mapaOrdenProveedorResumenCanal.size() >0){
					Iterator mapaOrdenProveedorResumenCanalIt = mapaOrdenProveedorResumenCanal.keySet().iterator();
					while(mapaOrdenProveedorResumenCanalIt.hasNext()){
						Integer orden = (Integer)mapaOrdenProveedorResumenCanalIt.next();
						Map proveedorResumenCanal = mapaOrdenProveedorResumenCanal.get(orden);
						
						String canal = (String)proveedorResumenCanal.keySet().iterator().next();
						Object[] resumenCanal = (Object[])proveedorResumenCanal.get(canal);
						
						ResumenTrpsCanalData resumenTrpsCanal = new ResumenTrpsCanalData();
						resumenTrpsCanal.setCanal(canal);
						resumenTrpsCanal.setSpots(((Integer)resumenCanal[0]).toString());
						resumenTrpsCanal.setInversion(formatoDecimal.format(Utilitarios.redondeoValor((BigDecimal)resumenCanal[1])));
						resumenTrpsCanal.setTrpsGye(formatoDecimal.format(Utilitarios.redondeoValor((Double)resumenCanal[2])));
						resumenTrpsCanal.setTrpsUio(formatoDecimal.format(Utilitarios.redondeoValor((Double)resumenCanal[3])));
						resumenTrpsCanal.setTrpsNac(formatoDecimal.format(Utilitarios.redondeoValor((Double)resumenCanal[4])));
						if(((Double)resumenCanal[5]) > 0){
							resumenTrpsCanal.setCpr(formatoDecimal.format(Utilitarios.redondeoValor((Double)resumenCanal[5])));
						}else{
							resumenTrpsCanal.setCpr("-");
						}
						resumenTrpsCanalList.add(resumenTrpsCanal);
					}
					tipoPauta = "TV";
				}
				
				//creo el vector de datos para el resumen trps por semana
				List<ResumenTrpsSemanaData> resumenTrpsSemanaList = new ArrayList<ResumenTrpsSemanaData>();
				if(semanaDatos.size() >0){
					Iterator mapaSemanaDatosIt = semanaDatos.keySet().iterator();
					while(mapaSemanaDatosIt.hasNext()){
						Integer semanaTemp = (Integer)mapaSemanaDatosIt.next();
						
						Object[] datosSemana = semanaDatos.get(semanaTemp);
						
						ResumenTrpsSemanaData resumenTrpsSemana = new ResumenTrpsSemanaData();
												
						Integer[] semanaDiaInicioFin = semanas.get(semanaTemp);
						Date semanaInicio = new Date(planMedioIf.getFechaInicio().getYear(), planMedioIf.getFechaInicio().getMonth(), semanaDiaInicioFin[0]);
						Date semanaFin = new Date(planMedioIf.getFechaInicio().getYear(), planMedioIf.getFechaInicio().getMonth(), semanaDiaInicioFin[1]);
						String semanaInicioFin = Utilitarios.getFechaCortaUppercase(semanaInicio) + " / " + Utilitarios.getFechaCortaUppercase(semanaFin);
						
						resumenTrpsSemana.setSemana(semanaInicioFin);
						resumenTrpsSemana.setSpots(((Integer)datosSemana[0]).toString());
						resumenTrpsSemana.setInversion(formatoDecimal.format(Utilitarios.redondeoValor((BigDecimal)datosSemana[1])));
						resumenTrpsSemana.setTrpsGye(formatoDecimal.format(Utilitarios.redondeoValor((BigDecimal)datosSemana[2])));
						resumenTrpsSemana.setTrpsUio(formatoDecimal.format(Utilitarios.redondeoValor((BigDecimal)datosSemana[3])));
						resumenTrpsSemana.setTrpsNac(formatoDecimal.format(Utilitarios.redondeoValor((BigDecimal)datosSemana[4])));
						
						resumenTrpsSemanaList.add(resumenTrpsSemana);
					}
					tipoPauta = "TV";
				}
				
				//creo el vector de datos para el resumen trps por canal
				List<ResumenTrpsFranjaData> resumenTrpsFranjaList = new ArrayList<ResumenTrpsFranjaData>();
				if(mapaFranjaObjectos.size() >0){
					Iterator mapaFranjaObjectosIt = mapaFranjaObjectos.keySet().iterator();
					while(mapaFranjaObjectosIt.hasNext()){
						Integer franja = (Integer)mapaFranjaObjectosIt.next();
						Object[] resumenFranja = mapaFranjaObjectos.get(franja);
												
						ResumenTrpsFranjaData resumenTrpsFranja = new ResumenTrpsFranjaData();
						
						if(franja == 1){
							resumenTrpsFranja.setFranja("A");
						}else if(franja == 2){
							resumenTrpsFranja.setFranja("AA");
						}else if(franja == 3){
							resumenTrpsFranja.setFranja("PRIME");
						}else if(franja == 4){
							resumenTrpsFranja.setFranja("LATE");
						}
						
						resumenTrpsFranja.setSpots(((Integer)resumenFranja[0]).toString());
						resumenTrpsFranja.setInversion(formatoDecimal.format(Utilitarios.redondeoValor((BigDecimal)resumenFranja[1])));
						resumenTrpsFranja.setTrpsGye(formatoDecimal.format(Utilitarios.redondeoValor((Double)resumenFranja[2])));
						resumenTrpsFranja.setTrpsUio(formatoDecimal.format(Utilitarios.redondeoValor((Double)resumenFranja[3])));
						resumenTrpsFranja.setTrpsNac(formatoDecimal.format(Utilitarios.redondeoValor((Double)resumenFranja[4])));
						if(((Double)resumenFranja[5]) > 0){
							resumenTrpsFranja.setCpr(formatoDecimal.format(Utilitarios.redondeoValor((Double)resumenFranja[5])));
						}else{
							resumenTrpsFranja.setCpr("-");
						}
						resumenTrpsFranjaList.add(resumenTrpsFranja);
					}
					tipoPauta = "TV";
				}
				
				/////////////////////////////////////////////////////////////////////////////////////////////
				
				parametrosMap.put("tipoPauta", tipoPauta);
				parametrosMap.put("pathsubreportResumenCanal", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/medios/RPPlanMediosTvResumenCanal.jasper");
				JRDataSource dataSourceResumenCanal = new JRBeanCollectionDataSource(resumenTrpsCanalList);
				parametrosMap.put("dataSourceResumenCanal", dataSourceResumenCanal);
				
				parametrosMap.put("pathsubreportResumenSemana", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/medios/RPPlanMediosTvResumenSemana.jasper");
				JRDataSource dataSourceResumenSemana = new JRBeanCollectionDataSource(resumenTrpsSemanaList);
				parametrosMap.put("dataResumenSemana", dataSourceResumenSemana);
				
				parametrosMap.put("pathsubreportResumenFranja", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/medios/RPPlanMediosTvResumenFranja.jasper");
				JRDataSource dataSourceResumenFranja = new JRBeanCollectionDataSource(resumenTrpsFranjaList);
				parametrosMap.put("dataResumenFranja", dataSourceResumenFranja);
				
				parametrosMap.put("version", versiones.substring(0, versiones.length() - 2));
				parametrosMap.put("total", formatoDecimal.format(Utilitarios.redondeoValor(total)));
				
				producto = producto.substring(0, producto.length() - 2);
				parametrosMap.put("producto", producto);
				
				parametrosMap.put("suma", totalSumaTotal);
				parametrosMap.put("porcentajeDescuento", getTxtPorcentajeDescuentoVenta().getText());
				parametrosMap.put("descuento", descuentoTotalVenta);
				parametrosMap.put("porcentajeComisionAgencia", getTxtPorcentajeComisionAgencia().getText());
				parametrosMap.put("comisionAgencia", comisionAgenciaTotal);
				parametrosMap.put("porcentajeIVA", String.valueOf(Parametros.IVA));
				parametrosMap.put("iva", ivaTotalVenta);
				parametrosMap.put("totalPresupuesto", formatoDecimal.format(Utilitarios.redondeoValor(totalValorTotalVenta)));
				
				ReportModelImpl.processReport(fileName, parametrosMap, planMedioReporteDataLista, true);
			
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			} catch (ParseException pe) {
				pe.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void reporteOrdenMedio(Map<OrdenMedioIf, Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> mapaComercialOrdenesCompCreadas) {
		try {
			String programa = "";
			String comercial = "";
			String hora = "";
			int cunias = 0;
			int cuniasExtra = 0;
			Integer totalCunias = 0;
			long comercialId = 0;
			BigDecimal valor = new BigDecimal(0);
			String productos = "";
			String versiones = "";
			// ONLY TO ORDENES DE MEDIO AGRUPADAS POR CANAL
			String[] comercialSplit = new String[2];
			// ONLY TO ORDENES DE MEDIO AGRUPADAS POR CANAL
			ArrayList<String> productosVersion = new ArrayList<String>();
			String[] productoSplit = new String[20];
			String[] versionSplit = new String[20];
			
			String siAgrupados = "Si, Agrupados";
			String siSeparados = "Si, Separados";
			String siSeleccionados = "Si, Seleccionados";
			String noGenerar = "No";
			Object[] opcionesOrdenes = {siAgrupados, siSeparados, siSeleccionados, noGenerar};

			int opcion = JOptionPane.showOptionDialog(null,
					"¿Desea generar el reporte de las Ordenes de Medios?",
					"Confirmación", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, opcionesOrdenes, noGenerar);
			
			int opcionSiAgrupados = 0;
			int opcionSiSeparados = 1;
			int opcionSiSeleccionados = 2;
			
			Map<Long, Long> ordenesSeleccionadas = new HashMap<Long, Long>();
			if(opcion == opcionSiSeleccionados){
				SeleccionarOrdenesModel jdSeleccionarOrdenesModel = new SeleccionarOrdenesModel(Parametros.getMainFrame(), "M", planMedioIf.getId(), mapaClienteOficina, mapaGenerico, mapaProducto);
				ArrayList<Object> listaOrdenMedio = jdSeleccionarOrdenesModel.getPresupuestoDetallesSeleccionados();
				for (int i = 0; i < listaOrdenMedio.size(); i++) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) listaOrdenMedio.get(i);
					ordenesSeleccionadas.put(ordenMedioIf.getId(), ordenMedioIf.getId());
				}
			}			
			
			if (opcion == opcionSiAgrupados || opcion == opcionSiSeparados || opcion == opcionSiSeleccionados) {
				List<OrdenMedioReporteData> ordenMedioReporteDataLista = new ArrayList<OrdenMedioReporteData>();
				String fileName = "jaspers/medios/RPOrdenMediosTv.jasper";
				HashMap parametrosMap = new HashMap();
				Iterator mapaOrdenesIt = mapaComercialOrdenesCompCreadas.keySet().iterator();
				while (mapaOrdenesIt.hasNext()) {
					OrdenMedioIf ordenMedioIf = (OrdenMedioIf) mapaOrdenesIt.next();
					
					//si no se eligio la opcion de seleccionar ordenes, o, si la orden estan entre las seleccionadas
					if(opcion != opcionSiSeleccionados || (opcion == opcionSiSeleccionados && ordenesSeleccionadas.get(ordenMedioIf.getId()) != null)){
						Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenMedioDetallesHashM = mapaComercialOrdenesCompCreadas.get(ordenMedioIf);
				
						BigDecimal porcentajeDescuento = new BigDecimal(0);
						String fechaActual = Utilitarios.dateHoraHoy();
						String year = fechaActual.substring(0, 4);
						String month = fechaActual.substring(5, 7);
						String day = fechaActual.substring(8, 10);
						String fechaEmision = day + " "	+ Utilitarios.getNombreMes(Integer.parseInt(month)).toLowerCase() + " del " + year;
						ClienteOficinaIf proveedorOficina = mapaClienteOficina.get(ordenMedioIf.getProveedorId());
						ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
						ClienteIf clienteIf = (ClienteIf) SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getClienteOficinaId()).iterator().next();
						OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(planMedioIf.getOrdenTrabajoDetalleId());
						OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
				
						parametrosMap.put("usuario", Parametros.getUsuario());
						EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
						parametrosMap.put("empresa", empresa.getNombre());
						OficinaIf oficina = (OficinaIf) Parametros.getOficina();
						parametrosMap.put("direccion", oficina.getDireccion());
						parametrosMap.put("telefono", "Teléfono: " + oficina.getTelefono() + "    Fax: " + oficina.getFax());
						parametrosMap.put("ruc", "RUC: " + empresa.getRuc());
						parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
						parametrosMap.put("sirvanse", "Transmitir");
						
						TipoProveedorIf tipoProveedor = SessionServiceLocator.getTipoProveedorSessionService().getTipoProveedor(proveedor.getTipoproveedorId());
						String numeroOrden = "";						
						if (tipoProveedor.getNombre().equals("RADIO")) {
							numeroOrden = "Orden de Transmisión Radio No. "	+ ordenMedioIf.getCodigo();
						} else if (tipoProveedor.getNombre().equals("TELEVISION")) {
							numeroOrden = "Orden de Transmisión Televisión No. " + ordenMedioIf.getCodigo();
						} else {
							numeroOrden = "Orden de Transmisión Cine No. " + ordenMedioIf.getCodigo();
						}
						numeroOrden = numeroOrden + " - " + ordenMedioIf.getRevision();
				
						String tipoOrden = "";
						if (tipoProveedor.getNombre().equals("TELEVISION")) {
							tipoOrden = "TELEVISION";
						}
						parametrosMap.put("tipoOrden", tipoOrden);
						
						// lista temporal de Auspicios para escribir la palabra AUSPICIO antes que comienze un orden medio detalle que sea parte de un auspicio
						ArrayList<String> auspiciosListTemp = new ArrayList<String>();
				
						Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> mapaComercialOrdenMedioDetalle = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
						Iterator ordenMedioDetallesHashMIt = ordenMedioDetallesHashM.keySet().iterator();

						versiones = "";

						////////////////////////////////////////////////////////////
						// CUANDO LAS ORDENES DE MEDIO FUERON AGRUPADAS POR CANAL //
						////////////////////////////////////////////////////////////
						
						if (planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) == 0) {

							while (ordenMedioDetallesHashMIt.hasNext()) {
								OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetallesHashMIt.next();

								ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = ordenMedioDetallesHashM.get(ordenMedioDetalleIf);

								comercialId = ordenMedioDetalleIf.getComercialId();
								programa = ordenMedioDetalleIf.getPrograma();
								comercial = ordenMedioDetalleIf.getComercial();
								hora = ordenMedioDetalleIf.getHora().toString();
								
								// 0 version, 1 derecho de programa, 2 orden
								comercialSplit = comercial.split(",");
								
								ComercialIf comercialIf = mapaComercial.get(comercialId);
						
								String productosCanal = "";
								String productoCanalVersion = "";
								String versionesCanal = "";
								String versionCanal = "";

								// PARA ESCOGER EL PRODUCTO O LETRA CON LA DURACION DE LA CUÑA PARA EL REPORTE
								Long derechoPrograma = comercialIf.getDerechoprogramaId();
								DerechoProgramaIf derechoProgramaIf = mapaDerechoPrograma.get(derechoPrograma);
								
								if (derechoProgramaIf.getNombre().trim().compareTo("CUÑA") == 0) {
									
									ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(comercialIf.getProductoClienteId());
									productosCanal = productoClienteIf.getNombre();
									versionesCanal = comercialIf.getVersion() + " (" + comercialIf.getDuracion() + ") " + comercialIf.getNombre();
							
								} else {
									Map aMap = new HashMap();
									aMap.put("campanaId", comercialIf.getCampanaId());
									aMap.put("productoClienteId", comercialIf.getProductoClienteId());
									Collection<ComercialIf> comercialIfColl = SessionServiceLocator.getComercialSessionService().findComercialByQuery(aMap);
							
									if (!comercialIfColl.isEmpty()) {
								
										for (ComercialIf comercialIfTemp : comercialIfColl) {
											Long derechoProgramaTemp = comercialIfTemp.getDerechoprogramaId();
											DerechoProgramaIf derechoProgramaIfTemp = mapaDerechoPrograma.get(derechoProgramaTemp);
											ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(comercialIf.getProductoClienteId());
											productosCanal = productoClienteIf.getNombre();
											versionesCanal = comercialIf.getVersion() + " (" + comercialIf.getDuracion() + ") "	+ comercialIf.getNombre();
											break;
										}
									}
								}

								versionCanal = versionesCanal; 						

								// PARA EVITAR QUE SE REPITAN LOS PRODUCTOS
								if (productos.length() > 0) {
									productoSplit = productos.split(", ");
									boolean isProducto = false;
									
									for (int i = 0; i < productoSplit.length; i++) {								
										String productoVersionTemp = productoSplit[i];
										
										if (productoVersionTemp.compareTo(productosCanal) == 0) {
											isProducto = true;
										}
									}
							
									if (!isProducto) {
										productos = productos + productosCanal + ", "; 
									}
								} else {
									productos = productosCanal + ", ";
								}

								if (versiones.length() > 0) {
									versionSplit = versiones.split(", ");
									boolean isVersion = false;
									
									for (int j = 0; j < versionSplit.length; j++) {
										String versionTemp = versionSplit[j];
										
										if (versionTemp.compareTo(versionCanal) == 0) {
											isVersion = true;
										}
									}
							
									if (!isVersion) {
										versiones = versiones + versionesCanal + ", ";
									}
								} else {
									versiones = versionesCanal + ", ";
								}

								if (ordenMedioDetalleIf.getPauta().compareTo("A") == 0 && ordenMedioDetalleIf.getAuspicioDescripcion() != null) {
									String auspicioDescripcion = "AUSPICIO - " + ordenMedioDetalleIf.getAuspicioDescripcion().trim();
									boolean isAuspicio = false;
							
									if (auspiciosListTemp.size() > 0) {
										for (int i = 0; i < auspiciosListTemp.size(); i++) {
											if (auspicioDescripcion.compareTo(auspiciosListTemp.get(i)) == 0) {
												isAuspicio = true;
											}
										}
									}
							
									if (!isAuspicio) {
										auspiciosListTemp.add(auspicioDescripcion);
										OrdenMedioReporteData ordenMedioReporteDataAuspicio = new OrdenMedioReporteData();
										ordenMedioReporteDataAuspicio.setHora("");
										ordenMedioReporteDataAuspicio.setPrograma(auspicioDescripcion);
										ordenMedioReporteDataAuspicio.setVersion(ordenMedioDetalleIf.getComercial().split(",")[0]);
										ordenMedioReporteDataAuspicio.setValor("");
										ordenMedioReporteDataAuspicio.setCunias("");
										ordenMedioReporteDataAuspicio.setTotal("");
										ordenMedioReporteDataAuspicio.setFechaEmision(fechaEmision);
										ordenMedioReporteDataAuspicio.setProveedorOficina(proveedorOficina.getDescripcion());
										ordenMedioReporteDataAuspicio.setProveedor(proveedor.getNombreLegal());
										ordenMedioReporteDataAuspicio.setRucProveedor(proveedor.getIdentificacion());
										ordenMedioReporteDataAuspicio.setMes(Utilitarios.getFechaMesAnioUppercase(ordenMedioIf.getFechaOrden()));
										ordenMedioReporteDataAuspicio.setCliente(clienteIf.getNombreLegal());
										ordenMedioReporteDataAuspicio.setOrdenTrabajo(ordenTrabajo.getCodigo());
										ordenMedioReporteDataAuspicio.setPlanMedio(planMedioIf.getCodigo());
								
										String elaboradoPor = "";
								
										if (ordenMedioIf.getEmpleadoId() != null) {
											EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenMedioIf.getEmpleadoId());
											elaboradoPor = empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0];
										}
										
										ordenMedioReporteDataAuspicio.setElaboradoPor(elaboradoPor);
										ordenMedioReporteDataAuspicio.setOrdenCanje("");
										ordenMedioReporteDataAuspicio.setNota2("");
										ordenMedioReporteDataAuspicio.setNotaPersonalizada(ordenMedioIf.getObservacion());
										CampanaIf campana = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajoIf.getCampanaId());
										ordenMedioReporteDataAuspicio.setCampania(campana.getNombre());
										ordenMedioReporteDataAuspicio.setNumeroOrden(numeroOrden);
										ordenMedioReporteDataAuspicio.setProducto((planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) == 0) ? productos.substring(0, productos.length() - 2) : productos);
										ordenMedioReporteDataAuspicio.setVersiones(versiones.substring(0, versiones.length() - 2));
										ordenMedioReporteDataAuspicio.setPorcentajeIVA(formatoEntero.format(Parametros.getIVA()));
										//suma de todo ordenMedio
										ordenMedioReporteDataAuspicio.setSuma(ordenMedioIf.getValorSubtotal()); 
										porcentajeDescuento = ordenMedioDetalleIf.getPorcentajeDescuento();
										BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje() != null ? ordenMedioIf.getPorcentajeCanje() : new BigDecimal(0);
										BigDecimal porcentajeNegociacionComision = ordenMedioIf.getPorcentajeNegociacionComision() != null ? ordenMedioIf.getPorcentajeNegociacionComision() : new BigDecimal(0);
										
										if (porcentajeCanje.compareTo(new BigDecimal(0)) == 1 && porcentajeCanje.compareTo(new BigDecimal(100)) == -1) {
											porcentajeDescuento = porcentajeNegociacionComision;
										}
										
										BigDecimal descuentoOrden = ordenMedioIf.getValorSubtotal().multiply(porcentajeDescuento.divide(new BigDecimal(100)));
										BigDecimal subTotalOrden = ordenMedioIf.getValorSubtotal().subtract(descuentoOrden);
										BigDecimal porcentajeBonificacion = ordenMedioIf.getPorcentajeBonificacionCompra() != null ? ordenMedioIf.getPorcentajeBonificacionCompra() : new BigDecimal(0);
										BigDecimal bonificacionOrden = subTotalOrden.multiply(porcentajeBonificacion.divide(new BigDecimal(100)));
								
										ProductoIf productoProveedor = mapaProducto.get(ordenMedioIf.getProductoProveedorId());
										GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
										BigDecimal ivaOrden = genericoProveedor.getCobraIva().equals("S") ? subTotalOrden.subtract(bonificacionOrden).multiply(BigDecimal.valueOf(Parametros.IVA / 100D)) : BigDecimal.ZERO;
								
										ordenMedioReporteDataAuspicio.setDescuento(descuentoOrden);
										ordenMedioReporteDataAuspicio.setBonificacion(bonificacionOrden);
										ordenMedioReporteDataAuspicio.setIva(ivaOrden);
										ordenMedioReporteDataAuspicio.setPorcentajeDescuento(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeDescuento)) + "%");
										ordenMedioReporteDataAuspicio.setPorcentajeBonificacion(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeBonificacion))	+ "%");
										BigDecimal totalOrden = subTotalOrden.subtract(bonificacionOrden).add(ivaOrden);
										ordenMedioReporteDataAuspicio.setTotalOrden(totalOrden);

										//busco si el proveedor tiene valores de retencion renta o iva
										Collection proveedorRetenciones = SessionServiceLocator.getClienteRetencionSessionService().findClienteRetencionByClienteId(proveedor.getId());
										
										if(proveedorRetenciones.size() > 0){
											fileName = "jaspers/medios/RPOrdenMediosTvConRetencion.jasper";
										}
										
										Iterator proveedorRetencionesIt = proveedorRetenciones.iterator();
										while(proveedorRetencionesIt.hasNext()){
											ClienteRetencionIf clienteRetencion = (ClienteRetencionIf)proveedorRetencionesIt.next();
											
											SriAirIf sriAir = SessionServiceLocator.getSriAirSessionService().getSriAir(clienteRetencion.getSriAirId());
											SriIvaRetencionIf sriIvaRetencion = SessionServiceLocator.getSriIvaRetencionSessionService().getSriIvaRetencion(clienteRetencion.getSriIvaRetencionId());
											
											double retencionRenta = 0D;
											if(sriAir.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
												double porcentajeRetencionRenta = sriAir.getPorcentaje().doubleValue();
												double totalOrdenDouble = subTotalOrden.subtract(bonificacionOrden).doubleValue();
												retencionRenta = totalOrdenDouble * (porcentajeRetencionRenta / 100D);
												
												ordenMedioReporteDataAuspicio.setPorcentajeRetencionRenta(formatoDecimal.format(porcentajeRetencionRenta));
												ordenMedioReporteDataAuspicio.setRetencionRenta(BigDecimal.valueOf(retencionRenta));
											}
											
											double retencionIva = 0D;
											if(sriIvaRetencion.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
												double porcentajeRetencionIva = sriIvaRetencion.getPorcentaje().doubleValue();
												double ivaOrdenDouble = ivaOrden.doubleValue();
												retencionIva = ivaOrdenDouble * (porcentajeRetencionIva / 100D);
												
												ordenMedioReporteDataAuspicio.setPorcentajeRetencionIva(formatoDecimal.format(porcentajeRetencionIva));
												ordenMedioReporteDataAuspicio.setRetencionIva(BigDecimal.valueOf(retencionIva));
												//ordenMedioReporteDataAuspicio.setRetencionIva(Utilitarios.redondeoValor(BigDecimal.valueOf(retencionIva)));
											}
											
											double totalPagar = totalOrden.doubleValue() - retencionRenta - retencionIva;
											ordenMedioReporteDataAuspicio.setTotalPagar(BigDecimal.valueOf(totalPagar));
										}
										
										//SE SETEAN LOS DIAS EN LETRA PARA EL REPORTE
										for (int i = 0; i < 31; i++) {									
											setDiaLetraPauta(ordenMedioReporteDataAuspicio,	i + 1);
										}
										
										ordenMedioReporteDataLista.add(ordenMedioReporteDataAuspicio);
									}
								}

								totalCunias = 0;
								BigDecimal valor_tarifa = ordenMedioDetalleIf.getValorTarifa();
								OrdenMedioReporteData ordenMedioReporteData = new OrdenMedioReporteData();
								ordenMedioReporteData.setFechaEmision(fechaEmision);
								ordenMedioReporteData.setProveedorOficina(proveedorOficina.getDescripcion());
								ordenMedioReporteData.setProveedor(proveedor.getNombreLegal());
								ordenMedioReporteData.setRucProveedor(proveedor.getIdentificacion());
								ordenMedioReporteData.setMes(Utilitarios.getFechaMesAnioUppercase(ordenMedioIf.getFechaOrden()));
								ordenMedioReporteData.setCliente(clienteIf.getNombreLegal());
								ordenMedioReporteData.setOrdenTrabajo(ordenTrabajo.getCodigo());
								ordenMedioReporteData.setPlanMedio(planMedioIf.getCodigo());
								
								String elaboradoPor = "";
						
								if (ordenMedioIf.getEmpleadoId() != null) {
									EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenMedioIf.getEmpleadoId());
									elaboradoPor = empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0];
								}
								
								ordenMedioReporteData.setElaboradoPor(elaboradoPor);
								ordenMedioReporteData.setOrdenCanje("");
								ordenMedioReporteData.setNota2("");
								ordenMedioReporteData.setNotaPersonalizada(ordenMedioIf.getObservacion());
								CampanaIf campana = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajoIf.getCampanaId());
								ordenMedioReporteData.setCampania(campana.getNombre());
								ordenMedioReporteData.setNumeroOrden(numeroOrden);
								ordenMedioReporteData.setProducto((planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) == 0) ? productos.substring(0, productos.length() - 2) : productos);
								ordenMedioReporteData.setVersiones(versiones.substring(0, versiones.length() - 2));
						
								// variable para indicar si se encuentra o no la OrdenMedioReporteData en la lista de ordenMedioReporteDataLista
								boolean equal = false;
						
								// variable indice de la OrdenMedioReporteData en la lista de ordenMedioReporteDataLista
								int indice = -1;
						
								if (ordenMedioReporteDataLista.size() > 0) {
							
									// PARA VERIFICAR QUE LA ORDEN MEDIO REPORT EXISTE Y AGREGARLE DATOS INCLUIR OTROS PRODUCTOS DENTRO DE UN REPORDATA QUE YA CONTIENE PRODUCTOS
									for (int i = 0; i < ordenMedioReporteDataLista.size(); i++) {
										OrdenMedioReporteData ordenMedioReporteDataTemp = ordenMedioReporteDataLista.get(i);
										
										if (ordenMedioReporteDataTemp.getHora().compareTo(hora) == 0 
												&& ordenMedioReporteDataTemp.getPrograma().compareTo(programa + " (" + comercialSplit[1]	+ ")") == 0
												&& ordenMedioReporteDataTemp.getValorBig().equals(valor_tarifa)
												&& ordenMedioReporteDataTemp.getVersion().equals(comercialSplit[0])
												&& ordenMedioReporteDataTemp.getNumeroOrden().equals(String.valueOf(ordenMedioIf.getNumeroOrdenAgrupacion()))) {
												// ordenMedioReporteDataTemp.getValor().compareTo(formatoDecimal.format(valor_tarifa)) == 0 ){
											equal = true;
										}
								
										if (equal) {
											ordenMedioReporteData = ordenMedioReporteDataTemp;
											indice = i;
											break;
										}
									}
								}
								
								if (indice != -1) {
									totalCunias = Integer.valueOf(ordenMedioReporteData.getCunias());
									java.sql.Date fechaPrograma = new Date(1999, 12, 15);
							
									for (OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf : ordenMedioDetalleMapaList) {
										totalCunias = totalCunias + ordenMedioDetalleMapaIf.getFrecuencia();
										fechaPrograma = Utilitarios.fromTimestampToSqlDate(ordenMedioDetalleMapaIf.getFecha());
								
										// SE SETEAN LAS FRECUENCIAS EN LOS DIAS CORRESPONDIENTES DE LA ORDEN MEDIO REPORTE DATA
										setDiaPautaByCanal(ordenMedioDetalleMapaIf,	ordenMedioReporteData, comercialSplit[0]);
									}
									
									ordenMedioReporteData.setCunias(totalCunias.toString());
							
									BigDecimal tarifaTemp = ordenMedioReporteData.getValorBig();
									BigDecimal totalCuniasBD = new BigDecimal(totalCunias);
									BigDecimal totalDetalle = tarifaTemp.multiply(totalCuniasBD);
									ordenMedioReporteData.setTotal(formatoDecimal.format(Utilitarios.redondeoValor(totalDetalle)));
									ordenMedioReporteDataLista.add(indice, ordenMedioReporteData);
									ordenMedioReporteDataLista.remove(indice + 1);
							
								} else {
									ordenMedioReporteData.setHora(ordenMedioDetalleIf.getHora().toString());
									ordenMedioReporteData.setPrograma(ordenMedioDetalleIf.getPrograma() + " (" + comercialSplit[1] + ")");
									ordenMedioReporteData.setVersion(ordenMedioDetalleIf.getComercial().split(",")[0]);
									valor_tarifa = ordenMedioDetalleIf.getValorTarifa();
									ordenMedioReporteData.setValor(formatoDecimal.format(Utilitarios.redondeoValor(valor_tarifa)));
									ordenMedioReporteData.setValorBig(valor_tarifa);
									totalCunias = 0;

									java.sql.Date fechaPrograma = new Date(1999, 12, 15);
							
									//SE SETEAN LOS DIAS EN LETRA PARA EL REPORTE
									for (int i = 0; i < 31; i++) {
										setDiaLetraPauta(ordenMedioReporteData,	i + 1);
									}
							
									for (OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf : ordenMedioDetalleMapaList) {
										totalCunias = totalCunias + ordenMedioDetalleMapaIf.getFrecuencia();
										fechaPrograma = Utilitarios.fromTimestampToSqlDate(ordenMedioDetalleMapaIf.getFecha());
										
										//SE SETEAN LAS FRECUENCIAS EN LOS DIAS CORRESPONDIENTES DE LA ORDEN MEDIO REPORTE DATA
										setDiaPautaByCanal(ordenMedioDetalleMapaIf,	ordenMedioReporteData, comercialSplit[0]);
									}

									ordenMedioReporteData.setCunias(String.valueOf(totalCunias));
									BigDecimal totalDetalle = ordenMedioDetalleIf.getValorSubtotal();
									// valor total
									ordenMedioReporteData.setTotal(formatoDecimal.format(Utilitarios.redondeoValor(totalDetalle)));

									ordenMedioReporteData.setDia(fechaPrograma.getDate());
									ordenMedioReporteData.setFechaPrograma(fechaPrograma);
									porcentajeDescuento = ordenMedioDetalleIf.getPorcentajeDescuento();
									ordenMedioReporteData.setPorcentajeIVA(formatoEntero.format(Parametros.getIVA()));
									
									// suma de todo ordenMedio
									ordenMedioReporteData.setSuma(ordenMedioIf.getValorSubtotal()); 
							
									BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje() != null ? ordenMedioIf.getPorcentajeCanje() : new BigDecimal(0);
									BigDecimal porcentajeNegociacionComision = ordenMedioIf.getPorcentajeNegociacionComision() != null ? ordenMedioIf.getPorcentajeNegociacionComision() : new BigDecimal(0);
							
									if (porcentajeCanje.compareTo(new BigDecimal(0)) == 1 
											&& porcentajeCanje.compareTo(new BigDecimal(100)) == -1) {										
										porcentajeDescuento = porcentajeNegociacionComision;
									}
									
									BigDecimal descuentoOrden = ordenMedioIf.getValorSubtotal().multiply(porcentajeDescuento.divide(new BigDecimal(100)));
									BigDecimal subTotalOrden = ordenMedioIf.getValorSubtotal().subtract(descuentoOrden);
									BigDecimal porcentajeBonificacion = ordenMedioIf.getPorcentajeBonificacionCompra() != null ? ordenMedioIf.getPorcentajeBonificacionCompra() : new BigDecimal(0);
									BigDecimal bonificacionOrden = subTotalOrden.multiply(porcentajeBonificacion.divide(new BigDecimal(100)));
							
									ProductoIf productoProveedor = mapaProducto.get(ordenMedioIf.getProductoProveedorId());
									GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
									BigDecimal ivaOrden = genericoProveedor.getCobraIva().equals("S") ? subTotalOrden.subtract(bonificacionOrden).multiply(BigDecimal.valueOf(Parametros.IVA / 100D)) : BigDecimal.ZERO;
										
									ordenMedioReporteData.setDescuento(descuentoOrden);
									ordenMedioReporteData.setBonificacion(bonificacionOrden);
									ordenMedioReporteData.setIva(ivaOrden);
									ordenMedioReporteData.setPorcentajeDescuento(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeDescuento)) + "%");
									ordenMedioReporteData.setPorcentajeBonificacion(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeBonificacion)) + "%");
									BigDecimal totalOrden = subTotalOrden.subtract(bonificacionOrden).add(ivaOrden);
									ordenMedioReporteData.setTotalOrden(totalOrden);
									
									//busco si el proveedor tiene valores de retencion renta o iva
									Collection proveedorRetenciones = SessionServiceLocator.getClienteRetencionSessionService().findClienteRetencionByClienteId(proveedor.getId());
									
									if(proveedorRetenciones.size() > 0){
										fileName = "jaspers/medios/RPOrdenMediosTvConRetencion.jasper";
									}
									
									Iterator proveedorRetencionesIt = proveedorRetenciones.iterator();
									while(proveedorRetencionesIt.hasNext()){
										ClienteRetencionIf clienteRetencion = (ClienteRetencionIf)proveedorRetencionesIt.next();
										
										SriAirIf sriAir = SessionServiceLocator.getSriAirSessionService().getSriAir(clienteRetencion.getSriAirId());
										SriIvaRetencionIf sriIvaRetencion = SessionServiceLocator.getSriIvaRetencionSessionService().getSriIvaRetencion(clienteRetencion.getSriIvaRetencionId());
										
										double retencionRenta = 0D;
										if(sriAir.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
											double porcentajeRetencionRenta = sriAir.getPorcentaje().doubleValue();
											double totalOrdenDouble = subTotalOrden.subtract(bonificacionOrden).doubleValue();
											retencionRenta = totalOrdenDouble * (porcentajeRetencionRenta / 100D);
											
											ordenMedioReporteData.setPorcentajeRetencionRenta(formatoDecimal.format(porcentajeRetencionRenta));
											ordenMedioReporteData.setRetencionRenta(BigDecimal.valueOf(retencionRenta));
										}
										
										double retencionIva = 0D;
										if(sriIvaRetencion.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
											double porcentajeRetencionIva = sriIvaRetencion.getPorcentaje().doubleValue();
											double ivaOrdenDouble = ivaOrden.doubleValue();
											retencionIva = ivaOrdenDouble * (porcentajeRetencionIva / 100D);
											
											ordenMedioReporteData.setPorcentajeRetencionIva(formatoDecimal.format(porcentajeRetencionIva));
											ordenMedioReporteData.setRetencionIva(BigDecimal.valueOf(retencionIva));
										}
										
										double totalPagar = totalOrden.doubleValue() - retencionRenta - retencionIva;
										ordenMedioReporteData.setTotalPagar(BigDecimal.valueOf(totalPagar));
									}									
									
									ordenMedioReporteDataLista.add(ordenMedioReporteData);
								}
							}
						}
						
						/////////////////////////////////////////////////////////////////////////////////////
						// CUANDO LAS ORDENES DE MEDIO FUERON AGRUPADAS POR PRODUCTO COMERCIAL O COMERCIAL //
						/////////////////////////////////////////////////////////////////////////////////////
						
						else if (planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_PRODUCTO_COMERCIAL) == 0 
								|| planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_VERSION) == 0) {

							while (ordenMedioDetallesHashMIt.hasNext()) {
								OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetallesHashMIt.next();

								comercialId = ordenMedioDetalleIf.getComercialId();
								ComercialIf comercialIf = mapaComercial.get(comercialId);
						
								String versionesProductoComercial = "";
								String versionProductoComercial = "";

								//PARA ESCOGER EL PRODUCTO O LETRA CON LA DURACION DE LA CUÑA PARA EL REPORTE
								Long derechoPrograma = comercialIf.getDerechoprogramaId();
								DerechoProgramaIf derechoProgramaIf = mapaDerechoPrograma.get(derechoPrograma);
						
								if (derechoProgramaIf.getNombre().trim().compareTo("CUÑA") == 0) {
									
									ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(comercialIf.getProductoClienteId());
									productos = productoClienteIf.getNombre();
									versionesProductoComercial = comercialIf.getVersion() + " (" + comercialIf.getDuracion() + ") " + comercialIf.getNombre();
							
								} else {							
									Map aMap = new HashMap();
									aMap.put("campanaId", comercialIf.getCampanaId());
									aMap.put("productoClienteId", comercialIf.getProductoClienteId());
									Collection<ComercialIf> comercialIfColl = SessionServiceLocator.getComercialSessionService().findComercialByQuery(aMap);
									
									if (!comercialIfColl.isEmpty()) {
										for (ComercialIf comercialIfTemp : comercialIfColl) {
											Long derechoProgramaTemp = comercialIfTemp.getDerechoprogramaId();
											DerechoProgramaIf derechoProgramaIfTemp = mapaDerechoPrograma.get(derechoProgramaTemp);
											ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(comercialIf.getProductoClienteId());
											productos = productoClienteIf.getNombre();
											versionesProductoComercial = comercialIf.getVersion() + " (" + comercialIf.getDuracion() + ") " + comercialIf.getNombre();
											break;									
										}
									}
								}						

								versionProductoComercial = versionesProductoComercial;
						
								if (versiones.length() > 0) {
									versionSplit = versiones.split(", ");
									boolean isVersion = false;
										
									for (int j = 0; j < versionSplit.length; j++) {
										String versionTemp = versionSplit[j];
										if (versionTemp.compareTo(versionProductoComercial) == 0) {
											isVersion = true;
										}
									}
									
									if (!isVersion) {
										versiones = versiones + versionesProductoComercial + ", ";
									}
									
								} else {
									versiones = versionesProductoComercial + ", ";
								}						

								if (ordenMedioDetalleIf.getPauta().compareTo("A") == 0
										&& ordenMedioDetalleIf.getAuspicioDescripcion() != null) {
									String auspicioDescripcion = "AUSPICIO - " + ordenMedioDetalleIf.getAuspicioDescripcion().trim();
									boolean isAuspicio = false;
							
									if (auspiciosListTemp.size() > 0) {
										for (int i = 0; i < auspiciosListTemp.size(); i++) {
											if (auspicioDescripcion.compareTo(auspiciosListTemp.get(i)) == 0) {
												isAuspicio = true;
											}
										}
									}
							
									if (!isAuspicio) {
										auspiciosListTemp.add(auspicioDescripcion);
										
										OrdenMedioReporteData ordenMedioReporteDataAuspicio = new OrdenMedioReporteData();

										ordenMedioReporteDataAuspicio.setHora("");
										ordenMedioReporteDataAuspicio.setPrograma(auspicioDescripcion);
										ordenMedioReporteDataAuspicio.setVersion("");
										ordenMedioReporteDataAuspicio.setValor("");
										ordenMedioReporteDataAuspicio.setCunias("");
										ordenMedioReporteDataAuspicio.setTotal("");
										ordenMedioReporteDataAuspicio.setFechaEmision(fechaEmision);
										ordenMedioReporteDataAuspicio.setProveedorOficina(proveedorOficina.getDescripcion());
										ordenMedioReporteDataAuspicio.setProveedor(proveedor.getNombreLegal());
										ordenMedioReporteDataAuspicio.setRucProveedor(proveedor.getIdentificacion());
										ordenMedioReporteDataAuspicio.setMes(Utilitarios.getFechaMesAnioUppercase(ordenMedioIf.getFechaOrden()));
										ordenMedioReporteDataAuspicio.setCliente(clienteIf.getNombreLegal());
										ordenMedioReporteDataAuspicio.setOrdenTrabajo(ordenTrabajo.getCodigo());
										ordenMedioReporteDataAuspicio.setPlanMedio(planMedioIf.getCodigo());
										
										String elaboradoPor = "";
										
										if (ordenMedioIf.getEmpleadoId() != null) {
											EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenMedioIf.getEmpleadoId());
											elaboradoPor = empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0];
										}
								
										ordenMedioReporteDataAuspicio.setElaboradoPor(elaboradoPor);
										ordenMedioReporteDataAuspicio.setOrdenCanje("");
										ordenMedioReporteDataAuspicio.setNota2("");
										ordenMedioReporteDataAuspicio.setNotaPersonalizada(ordenMedioIf.getObservacion());
										CampanaIf campana = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajoIf.getCampanaId());
										ordenMedioReporteDataAuspicio.setCampania(campana.getNombre());
										ordenMedioReporteDataAuspicio.setNumeroOrden(numeroOrden);
										ordenMedioReporteDataAuspicio.setProducto((planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) == 0) ? productos.substring(0, productos.length() - 2)	: productos);
										ordenMedioReporteDataAuspicio.setVersiones(versiones.substring(0, versiones.length() - 2));
										ordenMedioReporteDataAuspicio.setPorcentajeIVA(formatoEntero.format(Parametros.getIVA()));
										//suma de todo ordenMedio
										ordenMedioReporteDataAuspicio.setSuma(ordenMedioIf.getValorSubtotal()); 
								
										porcentajeDescuento = ordenMedioDetalleIf.getPorcentajeDescuento();
										BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje() != null ? ordenMedioIf.getPorcentajeCanje() : new BigDecimal(0);
										BigDecimal porcentajeNegociacionComision = ordenMedioIf.getPorcentajeNegociacionComision() != null ? ordenMedioIf.getPorcentajeNegociacionComision(): new BigDecimal(0);
								
										if (porcentajeCanje.compareTo(new BigDecimal(0)) == 1
												&& porcentajeCanje.compareTo(new BigDecimal(100)) == -1) {
											porcentajeDescuento = porcentajeNegociacionComision;
										}
										
										BigDecimal descuentoOrden = ordenMedioIf.getValorSubtotal().multiply(porcentajeDescuento.divide(new BigDecimal(100)));
										BigDecimal subTotalOrden = ordenMedioIf.getValorSubtotal().subtract(descuentoOrden);
										BigDecimal porcentajeBonificacion = ordenMedioIf.getPorcentajeBonificacionCompra() != null ? ordenMedioIf.getPorcentajeBonificacionCompra(): new BigDecimal(0);
										BigDecimal bonificacionOrden = subTotalOrden.multiply(porcentajeBonificacion.divide(new BigDecimal(100)));
								
										ProductoIf productoProveedor = mapaProducto.get(ordenMedioIf.getProductoProveedorId());
										GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
										BigDecimal ivaOrden = genericoProveedor.getCobraIva().equals("S") ? subTotalOrden.subtract(bonificacionOrden).multiply(BigDecimal.valueOf(Parametros.IVA / 100D)) : BigDecimal.ZERO;
								
										ordenMedioReporteDataAuspicio.setDescuento(descuentoOrden);
										ordenMedioReporteDataAuspicio.setBonificacion(bonificacionOrden);
										ordenMedioReporteDataAuspicio.setIva(ivaOrden);
										ordenMedioReporteDataAuspicio.setPorcentajeDescuento(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeDescuento)) + "%");
										ordenMedioReporteDataAuspicio.setPorcentajeBonificacion(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeBonificacion)) + "%");
										BigDecimal totalOrden = subTotalOrden.subtract(bonificacionOrden).add(ivaOrden);
										ordenMedioReporteDataAuspicio.setTotalOrden(totalOrden);
								
										//busco si el proveedor tiene valores de retencion renta o iva
										Collection proveedorRetenciones = SessionServiceLocator.getClienteRetencionSessionService().findClienteRetencionByClienteId(proveedor.getId());
										
										if(proveedorRetenciones.size() > 0){
											fileName = "jaspers/medios/RPOrdenMediosTvConRetencion.jasper";
										}
										
										Iterator proveedorRetencionesIt = proveedorRetenciones.iterator();
										while(proveedorRetencionesIt.hasNext()){
											ClienteRetencionIf clienteRetencion = (ClienteRetencionIf)proveedorRetencionesIt.next();
											
											SriAirIf sriAir = SessionServiceLocator.getSriAirSessionService().getSriAir(clienteRetencion.getSriAirId());
											SriIvaRetencionIf sriIvaRetencion = SessionServiceLocator.getSriIvaRetencionSessionService().getSriIvaRetencion(clienteRetencion.getSriIvaRetencionId());
											
											double retencionRenta = 0D;
											if(sriAir.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
												double porcentajeRetencionRenta = sriAir.getPorcentaje().doubleValue();
												double totalOrdenDouble = subTotalOrden.subtract(bonificacionOrden).doubleValue();;
												retencionRenta = totalOrdenDouble * (porcentajeRetencionRenta / 100D);
												
												ordenMedioReporteDataAuspicio.setPorcentajeRetencionRenta(formatoDecimal.format(porcentajeRetencionRenta));
												ordenMedioReporteDataAuspicio.setRetencionRenta(BigDecimal.valueOf(retencionRenta));
											}
											
											double retencionIva = 0D;
											if(sriIvaRetencion.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
												double porcentajeRetencionIva = sriIvaRetencion.getPorcentaje().doubleValue();
												double ivaOrdenDouble = ivaOrden.doubleValue();
												retencionIva = ivaOrdenDouble * (porcentajeRetencionIva / 100D);
												
												ordenMedioReporteDataAuspicio.setPorcentajeRetencionIva(formatoDecimal.format(porcentajeRetencionIva));
												ordenMedioReporteDataAuspicio.setRetencionIva(BigDecimal.valueOf(retencionIva));
												//ordenMedioReporteDataAuspicio.setRetencionIva(Utilitarios.redondeoValor(BigDecimal.valueOf(retencionIva)));
											}
											
											double totalPagar = totalOrden.doubleValue() - retencionRenta - retencionIva;
											ordenMedioReporteDataAuspicio.setTotalPagar(BigDecimal.valueOf(totalPagar));
										}
										
										//SE SETEAN LOS DIAS EN LETRA PARA EL REPORTE
										for (int i = 0; i < 31; i++) {
											setDiaLetraPauta(ordenMedioReporteDataAuspicio, i + 1);
										}
								
										ordenMedioReporteDataLista.add(ordenMedioReporteDataAuspicio);
									}
								}
						
								ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = ordenMedioDetallesHashM.get(ordenMedioDetalleIf);

								OrdenMedioReporteData ordenMedioReporteData = new OrdenMedioReporteData();
								ordenMedioReporteData.setFechaEmision(fechaEmision);
								ordenMedioReporteData.setProveedorOficina(proveedorOficina.getDescripcion());
								ordenMedioReporteData.setProveedor(proveedor.getNombreLegal());
								ordenMedioReporteData.setRucProveedor(proveedor.getIdentificacion());
								ordenMedioReporteData.setMes(Utilitarios.getFechaMesAnioUppercase(ordenMedioIf.getFechaOrden()));
								ordenMedioReporteData.setCliente(clienteIf.getNombreLegal());
								ordenMedioReporteData.setOrdenTrabajo(ordenTrabajo.getCodigo());
								ordenMedioReporteData.setPlanMedio(planMedioIf.getCodigo());
								String elaboradoPor = "";
								
								if (ordenMedioIf.getEmpleadoId() != null) {
									EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenMedioIf.getEmpleadoId());
									elaboradoPor = empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0];
								}
								
								ordenMedioReporteData.setElaboradoPor(elaboradoPor);
								ordenMedioReporteData.setOrdenCanje("");
								ordenMedioReporteData.setNota2("");
								ordenMedioReporteData.setNotaPersonalizada(ordenMedioIf.getObservacion());
								
								CampanaIf campana = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajoIf.getCampanaId());
								ordenMedioReporteData.setCampania(campana.getNombre());
								ordenMedioReporteData.setNumeroOrden(numeroOrden);
								ordenMedioReporteData.setProducto((planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) == 0) ? productos.substring(0, productos.length() - 2) : productos);
								ordenMedioReporteData.setVersiones(versiones.substring(0, versiones.length() - 2));
						
								totalCunias = 0;
						
								java.sql.Date fechaPrograma = new Date(1999, 12, 15);
						
								//SE SETEAN LOS DIAS EN LETRA PARA EL REPORTE
								for (int i = 0; i < 31; i++) {
									setDiaLetraPauta(ordenMedioReporteData, i + 1);
								}
						
								for (OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf : ordenMedioDetalleMapaList) {
									totalCunias = totalCunias + ordenMedioDetalleMapaIf.getFrecuencia();
									fechaPrograma = Utilitarios.fromTimestampToSqlDate(ordenMedioDetalleMapaIf.getFecha());
							
									//SE SETEAN LAS FRECUENCIAS EN LOS DIAS CORRESPONDIENTES DE LA ORDEN MEDIO REPORTE DATA
									setDiaPauta(ordenMedioDetalleMapaIf, ordenMedioReporteData);
								}

								comercialId = ordenMedioDetalleIf.getComercialId();
								programa = ordenMedioDetalleIf.getPrograma();
								comercial = ordenMedioDetalleIf.getComercial();
								hora = ordenMedioDetalleIf.getHora();

								ordenMedioReporteData.setHora(ordenMedioDetalleIf.getHora().toString());
								ordenMedioReporteData.setPrograma(ordenMedioDetalleIf.getPrograma() + " (" + ordenMedioDetalleIf.getComercial().split(",")[1] + ")");
								ordenMedioReporteData.setVersion(ordenMedioDetalleIf.getComercial().split(",")[0]);
						
								BigDecimal valor_tarifa = ordenMedioDetalleIf.getValorTarifa();
								ordenMedioReporteData.setValor(formatoDecimal.format(Utilitarios.redondeoValor(valor_tarifa)));
								ordenMedioReporteData.setCunias(String.valueOf(totalCunias));
								BigDecimal totalDetalle = ordenMedioDetalleIf.getValorSubtotal();
								//valor total
								ordenMedioReporteData.setTotal(formatoDecimal.format(Utilitarios.redondeoValor(totalDetalle)));
								ordenMedioReporteData.setDia(fechaPrograma.getDate());
														
								ordenMedioReporteData.setFechaPrograma(fechaPrograma);
								porcentajeDescuento = ordenMedioDetalleIf.getPorcentajeDescuento();
								ordenMedioReporteData.setPorcentajeIVA(formatoEntero.format(Parametros.getIVA()));
								// suma de todo ordenMedio
								ordenMedioReporteData.setSuma(ordenMedioIf.getValorSubtotal()); 
						
								BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje() != null ? ordenMedioIf.getPorcentajeCanje() : new BigDecimal(0);
								BigDecimal porcentajeNegociacionComision = ordenMedioIf.getPorcentajeNegociacionComision() != null ? ordenMedioIf.getPorcentajeNegociacionComision() : new BigDecimal(0);
						
								if (porcentajeCanje.compareTo(new BigDecimal(0)) == 1 && porcentajeCanje.compareTo(new BigDecimal(100)) == -1) {
									porcentajeDescuento = porcentajeNegociacionComision;
								}
								
								BigDecimal descuentoOrden = ordenMedioIf.getValorSubtotal().multiply(porcentajeDescuento.divide(new BigDecimal(100)));
								BigDecimal subTotalOrden = ordenMedioIf.getValorSubtotal().subtract(descuentoOrden);
								BigDecimal porcentajeBonificacion = ordenMedioIf.getPorcentajeBonificacionCompra() != null ? ordenMedioIf.getPorcentajeBonificacionCompra() : new BigDecimal(0);
								BigDecimal bonificacionOrden = subTotalOrden.multiply(porcentajeBonificacion.divide(new BigDecimal(100)));
								
								ProductoIf productoProveedor = mapaProducto.get(ordenMedioIf.getProductoProveedorId());
								GenericoIf genericoProveedor = mapaGenerico.get(productoProveedor.getGenericoId());
								
								BigDecimal ivaOrden = genericoProveedor.getCobraIva().equals("S") ? subTotalOrden.subtract(bonificacionOrden).multiply(BigDecimal.valueOf(Parametros.IVA / 100D)) : BigDecimal.ZERO;
						
								ordenMedioReporteData.setDescuento(descuentoOrden);
								ordenMedioReporteData.setBonificacion(bonificacionOrden);
								ordenMedioReporteData.setIva(ivaOrden);
								ordenMedioReporteData.setPorcentajeDescuento(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeDescuento)) + "%");
								ordenMedioReporteData.setPorcentajeBonificacion(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeBonificacion)) + "%");
								BigDecimal totalOrden = subTotalOrden.subtract(bonificacionOrden).add(ivaOrden);
								ordenMedioReporteData.setTotalOrden(totalOrden);
								
								//busco si el proveedor tiene valores de retencion renta o iva
								Collection proveedorRetenciones = SessionServiceLocator.getClienteRetencionSessionService().findClienteRetencionByClienteId(proveedor.getId());
								
								if(proveedorRetenciones.size() > 0){
									fileName = "jaspers/medios/RPOrdenMediosTvConRetencion.jasper";
								}
								
								Iterator proveedorRetencionesIt = proveedorRetenciones.iterator();
								while(proveedorRetencionesIt.hasNext()){
									ClienteRetencionIf clienteRetencion = (ClienteRetencionIf)proveedorRetencionesIt.next();
									
									SriAirIf sriAir = SessionServiceLocator.getSriAirSessionService().getSriAir(clienteRetencion.getSriAirId());
									SriIvaRetencionIf sriIvaRetencion = SessionServiceLocator.getSriIvaRetencionSessionService().getSriIvaRetencion(clienteRetencion.getSriIvaRetencionId());
									
									double retencionRenta = 0D;
									if(sriAir.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
										double porcentajeRetencionRenta = sriAir.getPorcentaje().doubleValue();
										double subTotalOrdenDouble = subTotalOrden.subtract(bonificacionOrden).doubleValue();
										retencionRenta = subTotalOrdenDouble * (porcentajeRetencionRenta / 100D);
										
										ordenMedioReporteData.setPorcentajeRetencionRenta(formatoDecimal.format(porcentajeRetencionRenta));
										ordenMedioReporteData.setRetencionRenta(BigDecimal.valueOf(retencionRenta));
									}
									
									double retencionIva = 0D;
									if(sriIvaRetencion.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
										double porcentajeRetencionIva = sriIvaRetencion.getPorcentaje().doubleValue();
										double ivaOrdenDouble = ivaOrden.doubleValue();
										retencionIva = ivaOrdenDouble * (porcentajeRetencionIva / 100D);
										
										ordenMedioReporteData.setPorcentajeRetencionIva(formatoDecimal.format(porcentajeRetencionIva));
										ordenMedioReporteData.setRetencionIva(BigDecimal.valueOf(retencionIva));
									}
									
									double totalPagar = totalOrden.doubleValue() - retencionRenta - retencionIva;
									ordenMedioReporteData.setTotalPagar(BigDecimal.valueOf(totalPagar));
								}
								
								ordenMedioReporteDataLista.add(ordenMedioReporteData);
							}
						}
				
						if(opcion == opcionSiSeparados || opcion == opcionSiSeleccionados){
							ReportModelImpl.processReport(fileName, parametrosMap,ordenMedioReporteDataLista, true);
							ordenMedioReporteDataLista.clear();
						}
					}					
				}
				
				if(opcion == opcionSiAgrupados){
					ReportModelImpl.processReport(fileName,parametrosMap,ordenMedioReporteDataLista, true);
				}						
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// END MODIFIED 14 OCTUBRE

	// ADD 25 MAYO
	private void setDiaLetraPauta(OrdenMedioReporteData ordenMedioReporteData,
			int dia) {

		Date fechaPlanMedio = Utilitarios.fromTimestampToSqlDate(planMedioIf
				.getFechaInicio());
		int mes = fechaPlanMedio.getMonth();
		int anio = fechaPlanMedio.getYear();
		Date fechaDiaria = new Date(anio, mes, dia);
		String[] fechaFormateada = Utilitarios.getFechaEEEddMMMToReporte(
				fechaDiaria).split("-");

		switch (dia) {
		case 1:
			ordenMedioReporteData.setDiaLetra01(fechaFormateada[0]);
			break;
		case 2:
			ordenMedioReporteData.setDiaLetra02(fechaFormateada[0]);
			break;
		case 3:
			ordenMedioReporteData.setDiaLetra03(fechaFormateada[0]);
			break;
		case 4:
			ordenMedioReporteData.setDiaLetra04(fechaFormateada[0]);
			break;
		case 5:
			ordenMedioReporteData.setDiaLetra05(fechaFormateada[0]);
			break;
		case 6:
			ordenMedioReporteData.setDiaLetra06(fechaFormateada[0]);
			break;
		case 7:
			ordenMedioReporteData.setDiaLetra07(fechaFormateada[0]);
			break;
		case 8:
			ordenMedioReporteData.setDiaLetra08(fechaFormateada[0]);
			break;
		case 9:
			ordenMedioReporteData.setDiaLetra09(fechaFormateada[0]);
			break;
		case 10:
			ordenMedioReporteData.setDiaLetra10(fechaFormateada[0]);
			break;
		case 11:
			ordenMedioReporteData.setDiaLetra11(fechaFormateada[0]);
			break;
		case 12:
			ordenMedioReporteData.setDiaLetra12(fechaFormateada[0]);
			break;
		case 13:
			ordenMedioReporteData.setDiaLetra13(fechaFormateada[0]);
			break;
		case 14:
			ordenMedioReporteData.setDiaLetra14(fechaFormateada[0]);
			break;
		case 15:
			ordenMedioReporteData.setDiaLetra15(fechaFormateada[0]);
			break;
		case 16:
			ordenMedioReporteData.setDiaLetra16(fechaFormateada[0]);
			break;
		case 17:
			ordenMedioReporteData.setDiaLetra17(fechaFormateada[0]);
			break;
		case 18:
			ordenMedioReporteData.setDiaLetra18(fechaFormateada[0]);
			break;
		case 19:
			ordenMedioReporteData.setDiaLetra19(fechaFormateada[0]);
			break;
		case 20:
			ordenMedioReporteData.setDiaLetra20(fechaFormateada[0]);
			break;
		case 21:
			ordenMedioReporteData.setDiaLetra21(fechaFormateada[0]);
			break;
		case 22:
			ordenMedioReporteData.setDiaLetra22(fechaFormateada[0]);
			break;
		case 23:
			ordenMedioReporteData.setDiaLetra23(fechaFormateada[0]);
			break;
		case 24:
			ordenMedioReporteData.setDiaLetra24(fechaFormateada[0]);
			break;
		case 25:
			ordenMedioReporteData.setDiaLetra25(fechaFormateada[0]);
			break;
		case 26:
			ordenMedioReporteData.setDiaLetra26(fechaFormateada[0]);
			break;
		case 27:
			ordenMedioReporteData.setDiaLetra27(fechaFormateada[0]);
			break;
		case 28:
			ordenMedioReporteData.setDiaLetra28(fechaFormateada[0]);
			break;
		case 29:
			ordenMedioReporteData.setDiaLetra29(fechaFormateada[0]);
			break;
		case 30:
			ordenMedioReporteData.setDiaLetra30(fechaFormateada[0]);
			break;
		case 31:
			ordenMedioReporteData.setDiaLetra31(fechaFormateada[0]);
			break;
		default:
			System.out
					.println("error, no coincidio con ningun dia de la semana");
		}
	}
	
	private void setDiaLetraPauta(PlanMedioReporteData planMedioReporteData, int dia) {

		Date fechaPlanMedio = Utilitarios.fromTimestampToSqlDate(planMedioIf
				.getFechaInicio());
		int mes = fechaPlanMedio.getMonth();
		int anio = fechaPlanMedio.getYear();
		Date fechaDiaria = new Date(anio, mes, dia);
		String[] fechaFormateada = Utilitarios.getFechaEEEddMMMToReporte(
				fechaDiaria).split("-");

		switch (dia) {
		case 1:
			planMedioReporteData.setDiaSemana01(fechaFormateada[0]);
			break;
		case 2:
			planMedioReporteData.setDiaSemana02(fechaFormateada[0]);
			break;
		case 3:
			planMedioReporteData.setDiaSemana03(fechaFormateada[0]);
			break;
		case 4:
			planMedioReporteData.setDiaSemana04(fechaFormateada[0]);
			break;
		case 5:
			planMedioReporteData.setDiaSemana05(fechaFormateada[0]);
			break;
		case 6:
			planMedioReporteData.setDiaSemana06(fechaFormateada[0]);
			break;
		case 7:
			planMedioReporteData.setDiaSemana07(fechaFormateada[0]);
			break;
		case 8:
			planMedioReporteData.setDiaSemana08(fechaFormateada[0]);
			break;
		case 9:
			planMedioReporteData.setDiaSemana09(fechaFormateada[0]);
			break;
		case 10:
			planMedioReporteData.setDiaSemana10(fechaFormateada[0]);
			break;
		case 11:
			planMedioReporteData.setDiaSemana11(fechaFormateada[0]);
			break;
		case 12:
			planMedioReporteData.setDiaSemana12(fechaFormateada[0]);
			break;
		case 13:
			planMedioReporteData.setDiaSemana13(fechaFormateada[0]);
			break;
		case 14:
			planMedioReporteData.setDiaSemana14(fechaFormateada[0]);
			break;
		case 15:
			planMedioReporteData.setDiaSemana15(fechaFormateada[0]);
			break;
		case 16:
			planMedioReporteData.setDiaSemana16(fechaFormateada[0]);
			break;
		case 17:
			planMedioReporteData.setDiaSemana17(fechaFormateada[0]);
			break;
		case 18:
			planMedioReporteData.setDiaSemana18(fechaFormateada[0]);
			break;
		case 19:
			planMedioReporteData.setDiaSemana19(fechaFormateada[0]);
			break;
		case 20:
			planMedioReporteData.setDiaSemana20(fechaFormateada[0]);
			break;
		case 21:
			planMedioReporteData.setDiaSemana21(fechaFormateada[0]);
			break;
		case 22:
			planMedioReporteData.setDiaSemana22(fechaFormateada[0]);
			break;
		case 23:
			planMedioReporteData.setDiaSemana23(fechaFormateada[0]);
			break;
		case 24:
			planMedioReporteData.setDiaSemana24(fechaFormateada[0]);
			break;
		case 25:
			planMedioReporteData.setDiaSemana25(fechaFormateada[0]);
			break;
		case 26:
			planMedioReporteData.setDiaSemana26(fechaFormateada[0]);
			break;
		case 27:
			planMedioReporteData.setDiaSemana27(fechaFormateada[0]);
			break;
		case 28:
			planMedioReporteData.setDiaSemana28(fechaFormateada[0]);
			break;
		case 29:
			planMedioReporteData.setDiaSemana29(fechaFormateada[0]);
			break;
		case 30:
			planMedioReporteData.setDiaSemana30(fechaFormateada[0]);
			break;
		case 31:
			planMedioReporteData.setDiaSemana31(fechaFormateada[0]);
			break;
		default:
			System.out
					.println("error, no coincidio con ningun dia de la semana");
		}
	}

	// CUANDO LAS ORDENES DE MEDIO ESTAN AGRUPADAS POR CANAL SE COLOCAN LAS
	// VERSIONES EN LUGAR DE LAS FRECUENCIAS
	private void setDiaPautaByCanal(
			OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf,
			OrdenMedioReporteData ordenMedioReporteData, String version) {
		Utilitarios.fromTimestampToSqlDate(ordenMedioDetalleMapaIf.getFecha())
				.getDate();

		String dataDia = "";

		/*
		 * for (int i = 0; i < ordenMedioDetalleMapaIf.getFrecuencia(); i++){
		 * dataDia = dataDia + version; }
		 * 
		 * switch
		 * (Utilitarios.fromTimestampToSqlDate(ordenMedioDetalleMapaIf.getFecha
		 * ()).getDate()){ case 1:
		 * ordenMedioReporteData.setDia01(ordenMedioReporteData.getDia01() +
		 * dataDia); break; case 2:
		 * ordenMedioReporteData.setDia02(ordenMedioReporteData.getDia02() +
		 * dataDia); break; case 3:
		 * ordenMedioReporteData.setDia03(ordenMedioReporteData.getDia03() +
		 * dataDia); break; case 4:
		 * ordenMedioReporteData.setDia04(ordenMedioReporteData.getDia04() +
		 * dataDia); break; case 5:
		 * ordenMedioReporteData.setDia05(ordenMedioReporteData.getDia05() +
		 * dataDia); break; case 6:
		 * ordenMedioReporteData.setDia06(ordenMedioReporteData.getDia06() +
		 * dataDia); break; case 7:
		 * ordenMedioReporteData.setDia07(ordenMedioReporteData.getDia07() +
		 * dataDia); break; case 8:
		 * ordenMedioReporteData.setDia08(ordenMedioReporteData.getDia08() +
		 * dataDia); break; case 9:
		 * ordenMedioReporteData.setDia09(ordenMedioReporteData.getDia09() +
		 * dataDia); break; case 10:
		 * ordenMedioReporteData.setDia10(ordenMedioReporteData.getDia10() +
		 * dataDia); break; case 11:
		 * ordenMedioReporteData.setDia11(ordenMedioReporteData.getDia11() +
		 * dataDia); break; case 12:
		 * ordenMedioReporteData.setDia12(ordenMedioReporteData.getDia12() +
		 * dataDia); break; case 13:
		 * ordenMedioReporteData.setDia13(ordenMedioReporteData.getDia13() +
		 * dataDia); break; case 14:
		 * ordenMedioReporteData.setDia14(ordenMedioReporteData.getDia14() +
		 * dataDia); break; case 15:
		 * ordenMedioReporteData.setDia15(ordenMedioReporteData.getDia15() +
		 * dataDia); break; case 16:
		 * ordenMedioReporteData.setDia16(ordenMedioReporteData.getDia16() +
		 * dataDia); break; case 17:
		 * ordenMedioReporteData.setDia17(ordenMedioReporteData.getDia17() +
		 * dataDia); break; case 18:
		 * ordenMedioReporteData.setDia18(ordenMedioReporteData.getDia18() +
		 * dataDia); break; case 19:
		 * ordenMedioReporteData.setDia19(ordenMedioReporteData.getDia19() +
		 * dataDia); break; case 20:
		 * ordenMedioReporteData.setDia20(ordenMedioReporteData.getDia20() +
		 * dataDia); break; case 21:
		 * ordenMedioReporteData.setDia21(ordenMedioReporteData.getDia21() +
		 * dataDia); break; case 22:
		 * ordenMedioReporteData.setDia22(ordenMedioReporteData.getDia22() +
		 * dataDia); break; case 23:
		 * ordenMedioReporteData.setDia23(ordenMedioReporteData.getDia23() +
		 * dataDia); break; case 24:
		 * ordenMedioReporteData.setDia24(ordenMedioReporteData.getDia24() +
		 * dataDia); break; case 25:
		 * ordenMedioReporteData.setDia25(ordenMedioReporteData.getDia25() +
		 * dataDia); break; case 26:
		 * ordenMedioReporteData.setDia26(ordenMedioReporteData.getDia26() +
		 * dataDia); break; case 27:
		 * ordenMedioReporteData.setDia27(ordenMedioReporteData.getDia27() +
		 * dataDia); break; case 28:
		 * ordenMedioReporteData.setDia28(ordenMedioReporteData.getDia28() +
		 * dataDia); break; case 29:
		 * ordenMedioReporteData.setDia29(ordenMedioReporteData.getDia29() +
		 * dataDia); break; case 30:
		 * ordenMedioReporteData.setDia30(ordenMedioReporteData.getDia30() +
		 * dataDia); break; case 31:
		 * ordenMedioReporteData.setDia31(ordenMedioReporteData.getDia31() +
		 * dataDia); break; default:
		 * System.out.println("error, no coincidio con ningun dia de la semana"
		 * ); }
		 */
		int frecuencia = ordenMedioDetalleMapaIf.getFrecuencia();
		int frecuenciaAcumulada = 0;
		switch (Utilitarios.fromTimestampToSqlDate(
				ordenMedioDetalleMapaIf.getFecha()).getDate()) {
		case 1:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia01() != null && !ordenMedioReporteData
					.getDia01().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia01()) : 0;
			ordenMedioReporteData.setDia01(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 2:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia02() != null && !ordenMedioReporteData
					.getDia02().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia02()) : 0;
			ordenMedioReporteData.setDia02(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 3:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia03() != null && !ordenMedioReporteData
					.getDia03().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia03()) : 0;
			ordenMedioReporteData.setDia03(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 4:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia04() != null && !ordenMedioReporteData
					.getDia04().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia04()) : 0;
			ordenMedioReporteData.setDia04(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 5:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia05() != null && !ordenMedioReporteData
					.getDia05().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia05()) : 0;
			ordenMedioReporteData.setDia05(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 6:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia06() != null && !ordenMedioReporteData
					.getDia06().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia06()) : 0;
			ordenMedioReporteData.setDia06(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 7:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia07() != null && !ordenMedioReporteData
					.getDia07().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia07()) : 0;
			ordenMedioReporteData.setDia07(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 8:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia08() != null && !ordenMedioReporteData
					.getDia08().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia08()) : 0;
			ordenMedioReporteData.setDia08(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 9:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia09() != null && !ordenMedioReporteData
					.getDia09().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia09()) : 0;
			ordenMedioReporteData.setDia09(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 10:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia10() != null && !ordenMedioReporteData
					.getDia10().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia10()) : 0;
			ordenMedioReporteData.setDia10(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 11:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia11() != null && !ordenMedioReporteData
					.getDia11().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia11()) : 0;
			ordenMedioReporteData.setDia11(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 12:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia12() != null && !ordenMedioReporteData
					.getDia12().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia12()) : 0;
			ordenMedioReporteData.setDia12(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 13:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia13() != null && !ordenMedioReporteData
					.getDia13().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia13()) : 0;
			ordenMedioReporteData.setDia13(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 14:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia14() != null && !ordenMedioReporteData
					.getDia14().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia14()) : 0;
			ordenMedioReporteData.setDia14(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 15:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia15() != null && !ordenMedioReporteData
					.getDia15().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia15()) : 0;
			ordenMedioReporteData.setDia15(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 16:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia16() != null && !ordenMedioReporteData
					.getDia16().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia16()) : 0;
			ordenMedioReporteData.setDia16(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 17:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia17() != null && !ordenMedioReporteData
					.getDia17().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia17()) : 0;
			ordenMedioReporteData.setDia17(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 18:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia18() != null && !ordenMedioReporteData
					.getDia18().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia18()) : 0;
			ordenMedioReporteData.setDia18(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 19:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia19() != null && !ordenMedioReporteData
					.getDia19().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia19()) : 0;
			ordenMedioReporteData.setDia19(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 20:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia20() != null && !ordenMedioReporteData
					.getDia20().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia20()) : 0;
			ordenMedioReporteData.setDia20(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 21:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia21() != null && !ordenMedioReporteData
					.getDia21().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia21()) : 0;
			ordenMedioReporteData.setDia21(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 22:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia22() != null && !ordenMedioReporteData
					.getDia22().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia22()) : 0;
			ordenMedioReporteData.setDia22(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 23:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia23() != null && !ordenMedioReporteData
					.getDia23().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia23()) : 0;
			ordenMedioReporteData.setDia23(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 24:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia24() != null && !ordenMedioReporteData
					.getDia24().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia24()) : 0;
			ordenMedioReporteData.setDia24(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 25:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia25() != null && !ordenMedioReporteData
					.getDia25().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia25()) : 0;
			ordenMedioReporteData.setDia25(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 26:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia26() != null && !ordenMedioReporteData
					.getDia26().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia26()) : 0;
			ordenMedioReporteData.setDia26(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 27:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia27() != null && !ordenMedioReporteData
					.getDia27().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia27()) : 0;
			ordenMedioReporteData.setDia27(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 28:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia28() != null && !ordenMedioReporteData
					.getDia28().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia28()) : 0;
			ordenMedioReporteData.setDia28(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 29:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia29() != null && !ordenMedioReporteData
					.getDia29().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia29()) : 0;
			ordenMedioReporteData.setDia29(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 30:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia30() != null && !ordenMedioReporteData
					.getDia30().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia30()) : 0;
			ordenMedioReporteData.setDia30(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 31:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia31() != null && !ordenMedioReporteData
					.getDia31().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia31()) : 0;
			ordenMedioReporteData.setDia31(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		default:
			System.out
					.println("No se encontraron coincidencias con ningún día de la semana");
		}
	}

	// END 25 MAYO

	// CUANDO LAS ORDENES DE MEDIO ESTAN AGRUPADAS POR PRODUCTO CLIENTE SE
	// COLOCAN LAS FRECUENCIAS DE LOS MAPAS
	private void setDiaPauta(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf,
			OrdenMedioReporteData ordenMedioReporteData) {
		Utilitarios.fromTimestampToSqlDate(ordenMedioDetalleMapaIf.getFecha())
				.getDate();

		int frecuencia = ordenMedioDetalleMapaIf.getFrecuencia();
		int frecuenciaAcumulada = 0;
		switch (Utilitarios.fromTimestampToSqlDate(
				ordenMedioDetalleMapaIf.getFecha()).getDate()) {
		case 1:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia01() != null && !ordenMedioReporteData
					.getDia01().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia01()) : 0;
			ordenMedioReporteData.setDia01(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 2:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia02() != null && !ordenMedioReporteData
					.getDia02().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia02()) : 0;
			ordenMedioReporteData.setDia02(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 3:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia03() != null && !ordenMedioReporteData
					.getDia03().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia03()) : 0;
			ordenMedioReporteData.setDia03(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 4:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia04() != null && !ordenMedioReporteData
					.getDia04().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia04()) : 0;
			ordenMedioReporteData.setDia04(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 5:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia05() != null && !ordenMedioReporteData
					.getDia05().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia05()) : 0;
			ordenMedioReporteData.setDia05(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 6:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia06() != null && !ordenMedioReporteData
					.getDia06().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia06()) : 0;
			ordenMedioReporteData.setDia06(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 7:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia07() != null && !ordenMedioReporteData
					.getDia07().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia07()) : 0;
			ordenMedioReporteData.setDia07(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 8:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia08() != null && !ordenMedioReporteData
					.getDia08().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia08()) : 0;
			ordenMedioReporteData.setDia08(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 9:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia09() != null && !ordenMedioReporteData
					.getDia09().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia09()) : 0;
			ordenMedioReporteData.setDia09(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 10:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia10() != null && !ordenMedioReporteData
					.getDia10().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia10()) : 0;
			ordenMedioReporteData.setDia10(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 11:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia11() != null && !ordenMedioReporteData
					.getDia11().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia11()) : 0;
			ordenMedioReporteData.setDia11(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 12:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia12() != null && !ordenMedioReporteData
					.getDia12().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia12()) : 0;
			ordenMedioReporteData.setDia12(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 13:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia13() != null && !ordenMedioReporteData
					.getDia13().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia13()) : 0;
			ordenMedioReporteData.setDia13(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 14:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia14() != null && !ordenMedioReporteData
					.getDia14().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia14()) : 0;
			ordenMedioReporteData.setDia14(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 15:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia15() != null && !ordenMedioReporteData
					.getDia15().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia15()) : 0;
			ordenMedioReporteData.setDia15(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 16:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia16() != null && !ordenMedioReporteData
					.getDia16().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia16()) : 0;
			ordenMedioReporteData.setDia16(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 17:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia17() != null && !ordenMedioReporteData
					.getDia17().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia17()) : 0;
			ordenMedioReporteData.setDia17(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 18:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia18() != null && !ordenMedioReporteData
					.getDia18().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia18()) : 0;
			ordenMedioReporteData.setDia18(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 19:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia19() != null && !ordenMedioReporteData
					.getDia19().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia19()) : 0;
			ordenMedioReporteData.setDia19(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 20:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia20() != null && !ordenMedioReporteData
					.getDia20().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia20()) : 0;
			ordenMedioReporteData.setDia20(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 21:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia21() != null && !ordenMedioReporteData
					.getDia21().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia21()) : 0;
			ordenMedioReporteData.setDia21(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 22:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia22() != null && !ordenMedioReporteData
					.getDia22().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia22()) : 0;
			ordenMedioReporteData.setDia22(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 23:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia23() != null && !ordenMedioReporteData
					.getDia23().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia23()) : 0;
			ordenMedioReporteData.setDia23(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 24:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia24() != null && !ordenMedioReporteData
					.getDia24().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia24()) : 0;
			ordenMedioReporteData.setDia24(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 25:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia25() != null && !ordenMedioReporteData
					.getDia25().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia25()) : 0;
			ordenMedioReporteData.setDia25(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 26:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia26() != null && !ordenMedioReporteData
					.getDia26().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia26()) : 0;
			ordenMedioReporteData.setDia26(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 27:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia27() != null && !ordenMedioReporteData
					.getDia27().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia27()) : 0;
			ordenMedioReporteData.setDia27(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 28:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia28() != null && !ordenMedioReporteData
					.getDia28().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia28()) : 0;
			ordenMedioReporteData.setDia28(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 29:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia29() != null && !ordenMedioReporteData
					.getDia29().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia29()) : 0;
			ordenMedioReporteData.setDia29(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 30:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia30() != null && !ordenMedioReporteData
					.getDia30().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia30()) : 0;
			ordenMedioReporteData.setDia30(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 31:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia31() != null && !ordenMedioReporteData
					.getDia31().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia31()) : 0;
			ordenMedioReporteData.setDia31(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		default:
			System.out
					.println("No se encontraron coincidencias con ningún día de la semana");
		}
	}

	public boolean validateFields() {
		if ((getCmbEstado().getSelectedIndex() == -1)
				|| (getCmbEstado().getSelectedItem().equals(""))) {
			SpiritAlert.createAlert("Debe seleccionar un Estado !",
					SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getCmbEstado().grabFocus();
			return false;
		}
		if ((("".equals(getTxtCorporacion().getText())) || (getTxtCorporacion()
				.getText() == null))) {
			SpiritAlert.createAlert("Debe seleccionar una Corporación !",
					SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getBtnCorporacion().grabFocus();
			return false;
		}
		if ((("".equals(getTxtCliente().getText())) || (getTxtCliente()
				.getText() == null))) {
			SpiritAlert.createAlert("Debe seleccionar un Cliente !",
					SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getBtnCliente().grabFocus();
			return false;
		}
		if ((("".equals(getTxtOficina().getText())) || (getTxtOficina()
				.getText() == null))) {
			SpiritAlert.createAlert("Debe seleccionar una Oficina !",
					SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getBtnOficina().grabFocus();
			return false;
		}
		if (getCmbOrdenTrabajo().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar una Orden de Trabajo !",
					SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getCmbOrdenTrabajo().grabFocus();
			return false;
		}
		if ((("".equals(getTxtConcepto().getText())) || (getTxtConcepto()
				.getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar un Concepto !",
					SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getTxtConcepto().grabFocus();
			return false;
		}
		if (getCmbTarget().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar un Target !",
					SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getCmbTarget().grabFocus();
			return false;
		}
		if ((("".equals(getTxtGuayaquil().getText())) || (getTxtGuayaquil()
				.getText() == null))) {
			SpiritAlert.createAlert(
					"Debe ingresar un estimado para Guayaquil !",
					SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getTxtGuayaquil().grabFocus();
			return false;
		}
		if ((("".equals(getTxtQuito().getText())) || (getTxtQuito().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar un estimado para Quito !",
					SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getTxtQuito().grabFocus();
			return false;
		}
		if (getCmbPeriodoFechaInicio().getSelectedItem() == null) {
			SpiritAlert.createAlert(
					"Debe seleccionar una Fecha de Inicio para el Periodo !",
					SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getCmbPeriodoFechaInicio().grabFocus();
			return false;
		}
		if (getCmbPeriodoFechaFin().getSelectedItem() == null) {
			SpiritAlert.createAlert(
					"Debe seleccionar una Fecha de Fin para el Periodo !",
					SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getCmbPeriodoFechaFin().grabFocus();
			return false;
		}
		if ((("".equals(getTxtCobertura().getText())) || (getTxtCobertura()
				.getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar la Cobertura !",
					SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(0);
			getTxtCobertura().grabFocus();
			return false;
		}
		if (planMedioMesVector.isEmpty()) {
			SpiritAlert.createAlert(
					"Debe ingresar por lo menos una Planificación !",
					SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(1);
			getCmbTipo().grabFocus();
			return false;
		}
		if (planMedioDetallesPlantilla.isEmpty()) {
			SpiritAlert.createAlert("Debe importar el Mapa de Pauta !",
					SpiritAlert.INFORMATION);
			getJtpPlanMedio().setSelectedIndex(2);
			// MODIFIED 29 JUNIO
			// getBtnImportarMapaPautaTv().grabFocus();
			getBtnImportarMapaPautaTvMultiple().grabFocus();

			return false;
		}

		// ADD 29 JUNIO
		if (planMedioTipo.compareTo(PLAN_MEDIO_TIPO_NORMAL) != 0) {
			if (planMedioIfSaved == null) {
				SpiritAlert.createAlert(
						"Debe seleccionar el Plan Medio a relacionar!!!",
						SpiritAlert.WARNING);
				getJtpPlanMedio().setSelectedIndex(0);
				getBtnPlanMedioRelacion().grabFocus();
				return false;
			}

		}
		// END 29 JUNIO

		// ADD 29 ABRIL
		String strPorcentajeDescuentoVenta = this
				.getTxtPorcentajeDescuentoVenta().getText();

		if ((!("".equals(strPorcentajeDescuentoVenta)) && (strPorcentajeDescuentoVenta != null))) {
			double porcentajeDescuentoPauta = 0D;
			porcentajeDescuentoPauta = Double.parseDouble(Utilitarios
					.removeDecimalFormat(getTxtPorcentajeDescuentoVenta()
							.getText()));
			if (porcentajeDescuentoPauta > porcentajeLimite) {
				SpiritAlert
						.createAlert(
								"El Porcentaje de Descuento Venta debe ser menor igual al 100%",
								SpiritAlert.WARNING);
				this.getJtpPlanMedio().setSelectedIndex(3);
				this.getTpMapasPauta().setSelectedIndex(1);
				this.getTxtPorcentajeDescuentoVenta().grabFocus();
				return false;
			}
		}

		String strPorcentajeComisionAgencia = this
				.getTxtPorcentajeComisionAgencia().getText();
		if ((!("".equals(strPorcentajeComisionAgencia)) && (strPorcentajeComisionAgencia != null))) {
			double porcentajeComisioAgencia = 0D;
			porcentajeComisioAgencia = Double.parseDouble(Utilitarios
					.removeDecimalFormat(getTxtPorcentajeComisionAgencia()
							.getText()));
			if (porcentajeComisioAgencia > porcentajeLimite) {
				this.getJtpPlanMedio().setSelectedIndex(3);
				this.getTpMapasPauta().setSelectedIndex(1);
				this.getTxtPorcentajeComisionAgencia().grabFocus();
				SpiritAlert
						.createAlert(
								"El Porcentaje de Comisión de Agencia debe ser menor o igual al 100%",
								SpiritAlert.WARNING);
				return false;
			}
		}

		String strPorcentajeBonificacionVenta = this
				.getTxtPorcentajeBonificacionVenta().getText();
		if ((!("".equals(strPorcentajeBonificacionVenta)) && (strPorcentajeBonificacionVenta != null))) {
			double porcentajeBonificacionVenta = 0D;
			porcentajeBonificacionVenta = Double.parseDouble(Utilitarios
					.removeDecimalFormat(getTxtPorcentajeBonificacionVenta()
							.getText()));
			if (porcentajeBonificacionVenta > porcentajeLimite) {
				this.getJtpPlanMedio().setSelectedIndex(3);
				this.getTpMapasPauta().setSelectedIndex(1);
				this.getTxtPorcentajeBonificacionVenta().grabFocus();
				SpiritAlert
						.createAlert(
								"El Porcentaje de Bonificación debe ser menor o igual al 100%",
								SpiritAlert.WARNING);
				return false;
			}
		}

		String strPorcentajeDescuentoOrdenMedio = this.getTxtPorcentajeDescuentoOrdenMedio().getText();		
		if ((!("".equals(strPorcentajeDescuentoOrdenMedio)) && (strPorcentajeDescuentoOrdenMedio != null))) {
			double porcentajeDescuentoOrdenMedio = 0D;
			porcentajeDescuentoOrdenMedio = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoOrdenMedio().getText()));
			if (porcentajeDescuentoOrdenMedio > porcentajeLimite) {
				SpiritAlert.createAlert("El Porcentaje de Descuento de las Ordenes de Medio \n debe ser menor igual al 100%", SpiritAlert.WARNING);
				this.getJtpPlanMedio().setSelectedIndex(3);
				this.getTpMapasPauta().setSelectedIndex(2);
				this.getTxtPorcentajeDescuentoOrdenMedio().grabFocus();
				return false;
			}
		}
		
		String strPorcentajeComisionAdicional = this.getTxtPorcentajeComisionAdicional().getText();		
		if ((!("".equals(strPorcentajeComisionAdicional)) && (strPorcentajeComisionAdicional != null))) {
			double porcentajeComsionAdicional = 0D;
			porcentajeComsionAdicional = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeComisionAdicional().getText()));
			if (porcentajeComsionAdicional > porcentajeLimite) {
				SpiritAlert.createAlert("El Porcentaje de Comisión Adicional de las Ordenes de Medio \n debe ser menor o igual al 100%", SpiritAlert.WARNING);
				this.getJtpPlanMedio().setSelectedIndex(3);
				this.getTpMapasPauta().setSelectedIndex(2);
				this.getTxtPorcentajeComisionAdicional().grabFocus();
				return false;
			}
		}
		
		String strPorcentajeCanje = this.getTxtPorcentajeCanje().getText();
		if ((!("".equals(strPorcentajeCanje)) && (strPorcentajeCanje != null))) {
			double porcentajeCanje = 0D;
			porcentajeCanje = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeCanje().getText()));
			if (porcentajeCanje > porcentajeLimite) {
				this.getJtpPlanMedio().setSelectedIndex(3);
				this.getTpMapasPauta().setSelectedIndex(2);
				this.getTxtPorcentajeCanje().grabFocus();
				SpiritAlert.createAlert("El Porcentaje de Canje debe ser menor igual al 100%", SpiritAlert.WARNING);
				return false;
			}
		}

		String strPorcentajeBonificacionCompra = this
				.getTxtPorcentajeBonificacionCompra().getText();
		if ((!("".equals(strPorcentajeBonificacionCompra)) && (strPorcentajeBonificacionCompra != null))) {
			double porcentajeBonificacionCompra = 0D;
			porcentajeBonificacionCompra = Double.parseDouble(Utilitarios
					.removeDecimalFormat(getTxtPorcentajeBonificacionCompra()
							.getText()));
			if (porcentajeBonificacionCompra > porcentajeLimite) {
				SpiritAlert
						.createAlert(
								"El Porcentaje de Bonificación de las Ordenes de Medio \n debe ser menor o igual al 100%",
								SpiritAlert.WARNING);
				this.getJtpPlanMedio().setSelectedIndex(3);
				this.getTpMapasPauta().setSelectedIndex(2);
				this.getTxtPorcentajeBonificacionCompra().grabFocus();
				return false;
			}
		}
		

		if ((!("".equals(strPorcentajeCanje)) && (strPorcentajeCanje != null))) {
			double porcentajeCanje = 0D;
			porcentajeCanje = Double.parseDouble(Utilitarios
					.removeDecimalFormat(getTxtPorcentajeCanje().getText()));
			if (porcentajeCanje > porcentajeLimite) {
				this.getJtpPlanMedio().setSelectedIndex(3);
				this.getTpMapasPauta().setSelectedIndex(2);
				// this.getPane
				this.getTxtPorcentajeCanje().grabFocus();
				SpiritAlert.createAlert(
						"El Porcentaje de Canje debe ser menor igual al 100%",
						SpiritAlert.WARNING);
				return false;
			}
		}

		return true;
	}

	public boolean validateFieldsNodoTv() {
		if ((("".equals(getTxtCanalTv().getText())) || (getTxtCanalTv()
				.getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar el nombre del Canal !",
					SpiritAlert.INFORMATION);
			getTxtCanalTv().grabFocus();
			return false;
		}
		if ((("".equals(getTxtProgramaTv().getText())) || (getTxtProgramaTv()
				.getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar el nombre del Programa !",
					SpiritAlert.INFORMATION);
			getTxtProgramaTv().grabFocus();
			return false;
		}
		if ((("".equals(getTxtHoraInicioPrograma().getText())) || (getTxtHoraInicioPrograma()
				.getText() == null))) {
			SpiritAlert.createAlert(
					"Debe ingresar la Hora de Inicio del programa !",
					SpiritAlert.INFORMATION);
			getTxtHoraInicioPrograma().grabFocus();
			return false;
		}
		if ((("".equals(getTxtHoraFinPrograma().getText())) || (getTxtHoraFinPrograma()
				.getText() == null))) {
			SpiritAlert.createAlert(
					"Debe ingresar la Hora de Fin del programa !",
					SpiritAlert.INFORMATION);
			getTxtHoraFinPrograma().grabFocus();
			return false;
		}
		if ((("".equals(getTxtDiasPrograma().getText())) || (getTxtDiasPrograma()
				.getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar los Días del programa !",
					SpiritAlert.INFORMATION);
			getTxtDiasPrograma().grabFocus();
			return false;
		}
		if ((("".equals(getTxtRatingTv().getText())) || (getTxtRatingTv()
				.getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar el Rating del programa !",
					SpiritAlert.INFORMATION);
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
		getTxtModificaciones().setText("0");

		getJtpPlanMedio().setSelectedIndex(0);
		getTxtCodigo().setEditable(false);
		getTxtConcepto().grabFocus();

		getBtnAgregarDetalle().setEnabled(true);
		getBtnActualizarDetalle().setEnabled(true);
		getBtnEliminarDetalle().setEnabled(true);
		getTextCodigoOrden().setEnabled(false);
		getBtnCambiarCodigo().setEnabled(false);
		getBtnCambiarCodigo().setVisible(false);
		getBtnLimpiarCodigoOrden().setEnabled(false);
		getCbPlanMedioNuevaVersion().setEnabled(true);
		getCbPlanMedioNuevoMes().setEnabled(true);
		getBtnImportarMapaPautaTv().setVisible(false);
		getBtnImportarMapaPautaTvMultiple().setEnabled(true);
	}

	public void cargarTablaComercial() {
		try {
			if (clienteIf != null && ordenTrabajoIf != null) {
				Map aMap = new HashMap();
				aMap.put("empresaId", Parametros.getIdEmpresa());
				aMap.put("estado", ESTADO_ACTIVO);
				aMap.put("campanaId", ordenTrabajoIf.getCampanaId());

				Collection comerciales = SessionServiceLocator
						.getComercialSessionService()
						.findComercialByQuery(aMap);
				Iterator comercialesIterator = comerciales.iterator();

				if (!getComercialVectorTabla().isEmpty()) {
					getComercialVectorTabla().removeAllElements();
				}

				cleanTablaComercial();

				while (comercialesIterator.hasNext()) {
					ComercialIf comercialesIf = (ComercialIf) comercialesIterator
							.next();

					tableComercial = (DefaultTableModel) getTblComercial()
							.getModel();
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

	public void agregarColumnasTablaComercial(ComercialIf comercialesIf,
			Vector<Object> fila) {
		fila.add(new Boolean(true));
		fila.add(comercialesIf.getCodigo());
		fila.add(comercialesIf.getNombre());

		DerechoProgramaIf derechoPrograma = mapaDerechoPrograma
				.get(comercialesIf.getDerechoprogramaId());

		// ADD 1 JUNIO
		// fila.add(derechoPrograma.getNombre()+"-"+derechoPrograma.getTipo());
		fila.add(derechoPrograma.getNombre());// +"-"+derechoPrograma.getTipo());

		fila.add(comercialesIf.getVersion());
		fila.add(comercialesIf.getDuracion() + " seg.");
	}

	public void showFindMode() {
		getCmbEstado().addItem(NOMBRE_ESTADO_FACTURADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_HISTORICO);
		getCmbEstado().addItem(NOMBRE_ESTADO_PEDIDO);

		getCmbEstado().setSelectedItem(null);
		getCmbPeriodoFechaInicio().setSelectedItem(null);
		getCmbPeriodoFechaFin().setSelectedItem(null);
		getCmbEstado().setSelectedIndex(-1);
		getJtpPlanMedio().setSelectedIndex(0);
		getTxtCodigo().setEditable(true);
		getTxtCodigo().grabFocus();
		getTxtCodigo().setBackground(Parametros.findColor);
		getTxtConcepto().setBackground(Parametros.findColor);
		getCmbEstado().setBackground(Parametros.findColor);
		getCmbPeriodoFechaInicio().setBackground(Parametros.findColor);
		getCmbPeriodoFechaFin().setBackground(Parametros.findColor);
		getBtnAgregarDetalle().setEnabled(false);
		getBtnActualizarDetalle().setEnabled(false);
		getBtnEliminarDetalle().setEnabled(false);
		getBtnImportarMapaPautaTv().setVisible(false);

		getTxtPorcentajeCanje().setText("");
		getTxtPorcentajeCanje().setEnabled(false);
		getTxtPorcentajeComisionAdicional().setText("");
		getTxtPorcentajeComisionAdicional().setEnabled(false);
	}

	public void showUpdateMode() {
		setUpdateMode();
		// MODIFIED 7 JULIO
		if (planMedioIf.getEstado().compareTo(ESTADO_EN_PROCESO) == 0
				|| planMedioIf.getEstado().compareTo(ESTADO_PENDIENTE) == 0) {
			getCmbEstado().removeAllItems();
			getCmbEstado().addItem(NOMBRE_ESTADO_EN_PROCESO);
			getCmbEstado().addItem(NOMBRE_ESTADO_PENDIENTE);
			getCmbEstado().addItem(NOMBRE_ESTADO_APROBADO);
			getCmbEstado().addItem(NOMBRE_ESTADO_PREPAGADO);

			if (planMedioIf.getEstado().compareTo(ESTADO_EN_PROCESO) == 0)
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_EN_PROCESO);
			else
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_PENDIENTE);
		}
		// END 7 JULIO
		getTxtCodigo().setBackground(getBackground());
		getTxtCodigo().setEditable(false);
		getTxtConcepto().setBackground(Parametros.saveUpdateColor);
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		getCmbPeriodoFechaInicio().setBackground(Parametros.saveUpdateColor);
		getCmbPeriodoFechaFin().setBackground(Parametros.saveUpdateColor);
		getTxtConcepto().grabFocus();

		// ADD 28 ABRIL
		getBtnAgregarDetalle().setEnabled(false);
		getBtnActualizarDetalle().setEnabled(false);
		getBtnEliminarDetalle().setEnabled(false);
		// END 28 ABRIL

		// MODIFIED 21 JUNIO
		// ADD 15 JUNIO
		getTextCodigoOrden().setEnabled(false);
		getBtnCambiarCodigo().setEnabled(false);
		// ADD 29 JUNIO
		getBtnCambiarCodigo().setVisible(false);
		getBtnLimpiarCodigoOrden().setEnabled(false);

		getCbPlanMedioNuevaVersion().setEnabled(false);
		getCbPlanMedioNuevoMes().setEnabled(false);
		// END 15 JUNIO

		// ADD 21 JUNIO
		getBtnImportarMapaPautaTvMultiple().setEnabled(false);
		getBtnImportarMapaPautaTv().setVisible(false);
		// END 21 JUNIO

		// ADD 23 JUNIO
		getBtnPlanMedioRelacion().setEnabled(false);
		// END 23 JUNIO
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
		cargarMapas();
		cargarOrdenTrabajo();
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
		DefaultMutableTreeNode rootArbolTv = new DefaultMutableTreeNode(
				"ARBOL DE TELEVISION");
		// rootArbolTv.removeAllChildren();
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(rootArbolTv);
		getArbolTelevision().setModel(defaultTreeModel);
		getArbolTelevision().getCheckBoxTreeSelectionModel().clearSelection();

		cabecerasFijasTv = new String[] {};
		cabecerasVariablesTv = new String[] {};
		datosTv = new Object[][] {};
		datosTGRP = new Object[][] {};
		numeroFilasTv = 20;
		getPanelMapaPautaTv().remove(scrollPanelMapaTv.getTable());
		scrollPanelMapaTv = new TableScrollPaneMapaPauta(cabecerasFijasTv,
				cabecerasVariablesTv, datosTv, numeroFilasTv);
		getPanelMapaPautaTv().add(scrollPanelMapaTv.getTable(),
				cc.xywh(3, 3, 7, 5));
		tableScrollTvRemovido = scrollPanelMapaTv.getTable();
	}

	public void cleanMapaPrensa() {
		cabecerasFijasPrensa = new String[] {};
		cabecerasVariablesPrensa = new String[] {};
		datosPrensa = new Object[][] {};
		numeroFilasPrensa = 20;
		// getPanelMapaPautaPrensa().remove(scrollPanelMapaPrensa.getTable());
		scrollPanelMapaPrensa = new TableScrollPaneMapaPauta(
				cabecerasFijasPrensa, cabecerasVariablesPrensa, datosPrensa,
				numeroFilasPrensa);
		// getPanelMapaPautaPrensa().add(scrollPanelMapaPrensa.getTable(),
		// cc.xywh(3, 3, 5, 5));
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
