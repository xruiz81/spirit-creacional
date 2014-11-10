package com.spirit.facturacion.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.entity.OrdenCompraDetalleIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.gui.panel.JPFacturacionPresupuestos;
import com.spirit.facturacion.gui.reporteData.ComisionesAdicionalesData;
import com.spirit.facturacion.gui.reporteData.InversionClientesData;
import com.spirit.facturacion.gui.reporteData.InversionTotalesClientesData;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.medios.entity.CampanaProductoIf;
import com.spirit.medios.entity.CampanaProductoVersionIf;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioFormaPagoIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoFacturacionIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.handler.EstadoPresupuesto;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;


public class FacturacionPresupuestosModel extends JPFacturacionPresupuestos {
	
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String CODIGO_TIPO_PROVEEDOR = "PR";
	private static final String TODOS = "TODOS";
	private static final String NOMBRE_TIPO_PROVEEDOR_MEDIOS = "MEDIOS";
	private static final String NOMBRE_TIPO_PROVEEDOR_PRODUCCION = "PRODUCCION";
	private static final String TIPO_REFERENCIA_PRESUPUESTO = "P";
	private static final String TIPO_PROVEEDOR_MEDIOS = "M";
	private static final String PAUTA_REGULAR_AUSPICIO = "B";
	protected String tipoPauta = PAUTA_REGULAR_AUSPICIO;
	protected ClienteOficinaIf clienteOficinaIf;
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private ArrayList<Object[]> ordenesMedioDetalle = new ArrayList<Object[]>();
	private ArrayList presupuestosDetalle = new ArrayList();
	private ArrayList presupuestosDetalleSinOrdenes = new ArrayList();
	
	protected ClienteOficinaIf medioOficinaIf = null;
	protected MarcaProductoIf marcaProductoIf = null;
	protected TipoProveedorIf subtipoProveedorIf = null;
	private ProductoClienteIf productoClienteIf = null;
	protected ClienteIf clienteIf = null;
	protected ClienteIf medioIf = null;
	private String estado = TODOS;
	private String tipoProveedor = TODOS;
	
	private Map<Long, ClienteIf> mapaCliente = new HashMap<Long, ClienteIf>();
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long, TipoProveedorIf> mapaTipoProveedor = new HashMap<Long, TipoProveedorIf>();
	private Map<Long, MarcaProductoIf> mapaMarcasProducto = new HashMap<Long, MarcaProductoIf>();
	private Map<Long, ProductoClienteIf> mapaProductoCliente = new HashMap<Long, ProductoClienteIf>();
	private Map<Long, CampanaProductoIf> mapaCampanaProducto = new HashMap<Long, CampanaProductoIf>();
	private Map<Long, CampanaProductoVersionIf> mapaCampanaProductoVersion = new HashMap<Long, CampanaProductoVersionIf>();
	private Map<Long, TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long, TipoDocumentoIf>();
	private Map<Long, DocumentoIf> mapaDocumento = new HashMap<Long, DocumentoIf>();
		
	ArrayList<InversionClientesData> inversionClientesDataVector = new ArrayList<InversionClientesData>();
	ArrayList<InversionTotalesClientesData> inversionTotalClientesDataVector = new ArrayList<InversionTotalesClientesData>();
	ArrayList<ComisionesAdicionalesData> comisionesAdicionalesDataVector = new ArrayList<ComisionesAdicionalesData>();
	
	//totales por estado
	private int presupuestosCotizados = 0;
	private int presupuestosFacturados = 0;
	private int presupuestosPendientes = 0; //pendientes de facturar	
	private BigDecimal totalCotizado = new BigDecimal(0);
	private BigDecimal totalFacturado = new BigDecimal(0);
	private BigDecimal totalPendiente = new BigDecimal(0);
	
	private int presupuestosCotizadosEne = 0;
	private int presupuestosFacturadosEne = 0;
	private int presupuestosPendientesEne = 0;
	private BigDecimal totalCotizadoEne = new BigDecimal(0);
	private BigDecimal totalFacturadoEne = new BigDecimal(0);
	private BigDecimal totalPendienteEne = new BigDecimal(0);
	
	private int presupuestosCotizadosFeb = 0;
	private int presupuestosFacturadosFeb = 0;
	private int presupuestosPendientesFeb = 0;
	private BigDecimal totalCotizadoFeb = new BigDecimal(0);
	private BigDecimal totalFacturadoFeb = new BigDecimal(0);
	private BigDecimal totalPendienteFeb = new BigDecimal(0);
	
	private int presupuestosCotizadosMar = 0;
	private int presupuestosFacturadosMar = 0;
	private int presupuestosPendientesMar = 0;
	private BigDecimal totalCotizadoMar = new BigDecimal(0);
	private BigDecimal totalFacturadoMar = new BigDecimal(0);
	private BigDecimal totalPendienteMar = new BigDecimal(0);
	
	private int presupuestosCotizadosAbr = 0;
	private int presupuestosFacturadosAbr = 0;
	private int presupuestosPendientesAbr = 0;
	private BigDecimal totalCotizadoAbr = new BigDecimal(0);
	private BigDecimal totalFacturadoAbr = new BigDecimal(0);
	private BigDecimal totalPendienteAbr = new BigDecimal(0);
	
	private int presupuestosCotizadosMay = 0;
	private int presupuestosFacturadosMay = 0;
	private int presupuestosPendientesMay = 0;
	private BigDecimal totalCotizadoMay = new BigDecimal(0);
	private BigDecimal totalFacturadoMay = new BigDecimal(0);
	private BigDecimal totalPendienteMay = new BigDecimal(0);
	
	private int presupuestosCotizadosJun = 0;
	private int presupuestosFacturadosJun = 0;
	private int presupuestosPendientesJun = 0;
	private BigDecimal totalCotizadoJun = new BigDecimal(0);
	private BigDecimal totalFacturadoJun = new BigDecimal(0);
	private BigDecimal totalPendienteJun = new BigDecimal(0);
	
	private int presupuestosCotizadosJul = 0;
	private int presupuestosFacturadosJul = 0;
	private int presupuestosPendientesJul = 0;
	private BigDecimal totalCotizadoJul = new BigDecimal(0);
	private BigDecimal totalFacturadoJul = new BigDecimal(0);
	private BigDecimal totalPendienteJul = new BigDecimal(0);
	
	private int presupuestosCotizadosAgo = 0;
	private int presupuestosFacturadosAgo = 0;
	private int presupuestosPendientesAgo = 0;
	private BigDecimal totalCotizadoAgo = new BigDecimal(0);
	private BigDecimal totalFacturadoAgo = new BigDecimal(0);
	private BigDecimal totalPendienteAgo = new BigDecimal(0);
	
	private int presupuestosCotizadosSep = 0;
	private int presupuestosFacturadosSep = 0;
	private int presupuestosPendientesSep = 0;
	private BigDecimal totalCotizadoSep = new BigDecimal(0);
	private BigDecimal totalFacturadoSep = new BigDecimal(0);
	private BigDecimal totalPendienteSep = new BigDecimal(0);
	
	private int presupuestosCotizadosOct = 0;
	private int presupuestosFacturadosOct = 0;
	private int presupuestosPendientesOct = 0;
	private BigDecimal totalCotizadoOct = new BigDecimal(0);
	private BigDecimal totalFacturadoOct = new BigDecimal(0);
	private BigDecimal totalPendienteOct = new BigDecimal(0);
	
	private int presupuestosCotizadosNov = 0;
	private int presupuestosFacturadosNov = 0;
	private int presupuestosPendientesNov = 0;
	private BigDecimal totalCotizadoNov = new BigDecimal(0);
	private BigDecimal totalFacturadoNov = new BigDecimal(0);
	private BigDecimal totalPendienteNov = new BigDecimal(0);
	
	private int presupuestosCotizadosDic = 0;
	private int presupuestosFacturadosDic = 0;
	private int presupuestosPendientesDic = 0;
	private BigDecimal totalCotizadoDic = new BigDecimal(0);
	private BigDecimal totalFacturadoDic = new BigDecimal(0);
	private BigDecimal totalPendienteDic = new BigDecimal(0);
	
	//totales de facturacion
	private Double[] facturas = new Double[13];
	private Double[] ordenes = new Double[13];
	private Double[] compras = new Double[13];
	private double totalFacturas = 0D;
	private double totalOrdenes = 0D;
	private double totalCompras = 0D;
	
	private Integer[] cantidadFacturas = new Integer[13];
	private Integer[] cantidadOrdenes = new Integer[13];
	private Integer[] cantidadCompras = new Integer[13];
	private int totalCantidadFacturas = 0;
	private int totalCantidadOrdenes = 0;
	private int totalCantidadCompras = 0;
	
	private boolean carteraIngresoTotalSeleccionado = false;
	private boolean carteraIngresoRetencionParcialSeleccionado = false;
	private boolean carteraIngresoParcialSeleccionado = false;
	private boolean carteraIngresoRetencionSeleccionado = false;
	private boolean carteraEgresoTotalSeleccionado = false;
	private boolean carteraEgresoRetencionParcialSeleccionado = false;
	private boolean carteraEgresoParcialSeleccionado = false;
	private boolean carteraEgresoRetencionSeleccionado = false;
	
	private Map problemas = new HashMap();
	//mapa para no tomar en cuenta dos veces la misma factura en los casos de facturacion multiple
	//(dos o mas presupuestos en la misma factura)
	private Map<Long,Long> facturaIngresada = new HashMap<Long,Long>();
	private ClienteOficinaCriteria medioOficinaCriteria;
		
				
	public FacturacionPresupuestosModel(){
		initKeyListeners();
		cargarMapas();
		anchoColumnasTabla();
		cargarCombos();
		showSaveMode();
		initListeners();
	}
	
	public void cargarCombos(){
		cargarComboSegmento();
		cargarComboProducto();
		cargarComboSubtipoProveedor();
	}
	
	public void cargarMapas() {
		try {
			mapaCliente.clear();
			Collection clientes = SessionServiceLocator
					.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesIt = clientes.iterator();
			while (clientesIt.hasNext()) {
				ClienteIf cliente = (ClienteIf) clientesIt.next();
				mapaCliente.put(cliente.getId(), cliente);
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
			
			mapaTipoProveedor.clear();
			Collection tiposProveedor = SessionServiceLocator
					.getTipoProveedorSessionService().findTipoProveedorByEmpresaId(Parametros.getIdEmpresa());
			Iterator tiposProveedorIt = tiposProveedor.iterator();
			while (tiposProveedorIt.hasNext()) {
				TipoProveedorIf tipoProveedor = (TipoProveedorIf) tiposProveedorIt
						.next();
				mapaTipoProveedor.put(tipoProveedor.getId(), tipoProveedor);
			}
			
			mapaMarcasProducto.clear();
			Collection marcasProducto = SessionServiceLocator
					.getMarcaProductoSessionService().findMarcaProductoByEmpresaId(Parametros.getIdEmpresa());
			Iterator marcasProductoIt = marcasProducto.iterator();
			while (marcasProductoIt.hasNext()) {
				MarcaProductoIf marcaProducto = (MarcaProductoIf) marcasProductoIt
						.next();
				mapaMarcasProducto.put(marcaProducto.getId(), marcaProducto);
			}
			
			mapaCampanaProductoVersion.clear();
			Collection campanaProductoVersiones = SessionServiceLocator
					.getCampanaProductoVersionSessionService().findCampanaProductoVersionByEmpresaId(Parametros.getIdEmpresa());
			Iterator campanaProductoVersionesIt = campanaProductoVersiones.iterator();
			while (campanaProductoVersionesIt.hasNext()) {
				CampanaProductoVersionIf campanaProductoVersion = (CampanaProductoVersionIf) campanaProductoVersionesIt
						.next();
				mapaCampanaProductoVersion.put(campanaProductoVersion.getId(), campanaProductoVersion);
			}
			
			
			mapaCampanaProducto.clear();
			Collection campanaProductos = SessionServiceLocator
					.getCampanaProductoSessionService().findCampanaProductoByEmpresaId(Parametros.getIdEmpresa());
			Iterator campanaProductosIt = campanaProductos.iterator();
			while (campanaProductosIt.hasNext()) {
				CampanaProductoIf campanaProducto = (CampanaProductoIf) campanaProductosIt
						.next();
				mapaCampanaProducto.put(campanaProducto.getId(), campanaProducto);
			}
			
			
			mapaProductoCliente.clear();
			Collection productosCliente = SessionServiceLocator
					.getProductoClienteSessionService().findProductoClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator productosClienteIt = productosCliente.iterator();
			while (productosClienteIt.hasNext()) {
				ProductoClienteIf productoCliente = (ProductoClienteIf) productosClienteIt
						.next();
				mapaProductoCliente.put(productoCliente.getId(), productoCliente);
			}
			
			mapaTipoDocumento.clear();
			Collection tiposDocumento = SessionServiceLocator
					.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator tiposDocumentoIt = tiposDocumento.iterator();
			while (tiposDocumentoIt.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoIt
						.next();
				mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
			}
			
			mapaDocumento.clear();
			Collection documentos = SessionServiceLocator
					.getDocumentoSessionService().findDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator documentosIt = documentos.iterator();
			while (documentosIt.hasNext()) {
				DocumentoIf documento = (DocumentoIf) documentosIt
						.next();
				mapaDocumento.put(documento.getId(), documento);
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void anchoColumnasTabla() {
		setSorterTable(getTblInversion());
		getTblInversion().getTableHeader().setReorderingAllowed(false);
		getTblInversion().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblInversion().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblInversion().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblInversion().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblInversion().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(100);		
		anchoColumna = getTblInversion().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblInversion().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblInversion().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblInversion().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblInversion().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblInversion().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(120);
		anchoColumna = getTblInversion().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblInversion().getColumnModel().getColumn(11);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblInversion().getColumnModel().getColumn(12);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblInversion().getColumnModel().getColumn(13);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblInversion().getColumnModel().getColumn(14);
		anchoColumna.setPreferredWidth(80);
	}
	
	public void cargarComboSegmento(){
		
	}
	
	public void cargarComboProducto(){
		
	}
	
	Comparator<TipoProveedorIf> ordenarTipoPorNombre = new Comparator<TipoProveedorIf>(){
		public int compare(TipoProveedorIf o1, TipoProveedorIf o2) {
			return o1.getNombre().compareTo(o2.getNombre());
		}		
	};
	
	private void cargarComboSubtipoProveedor(){
		try {
			List tiposProveedor = new ArrayList();
						
			Iterator it = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				TipoProveedorIf tipoProveedorMedio = (TipoProveedorIf) it.next();
				tiposProveedor.add(tipoProveedorMedio);
			}
			Collections.sort(tiposProveedor,ordenarTipoPorNombre);
			tiposProveedor.add(TODOS);
			refreshCombo(getCmbSubtipoProveedor(),tiposProveedor);
			getCmbSubtipoProveedor().setSelectedItem(TODOS);
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	public void initKeyListeners(){
		//getCbAprobadosVsFacturados().setEnabled(false);
		
		getCbVerCarteraIngresoTotal().setVisible(false);
		getCbVerCarteraIngresoRetencionParcial().setVisible(false);
		getCbVerCarteraIngresoParcial().setVisible(false);
		getCbVerCarteraIngresoRetencion().setVisible(false);
		getCbVerCarteraEgresoTotal().setVisible(false);
		getCbVerCarteraEgresoRetencionParcial().setVisible(false);
		getCbVerCarteraEgresoParcial().setVisible(false);
		getCbVerCarteraEgresoRetencion().setVisible(false);
		getCbVerProveedoresFactura().setVisible(false);
		
		getLblSegmento().setVisible(false);
		getCmbSegmento().setVisible(false);
		getLblProducto().setVisible(false);
		getCmbProducto().setVisible(false);
		
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
		
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnProveedorOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnProveedorOficina().setToolTipText("Buscar Proveedor Oficina");
		getBtnConsultar().setToolTipText("Consultar");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		getCbVerCarteraIngresoTotal().setToolTipText("Ver Ingreso Pago Total");
		getCbVerCarteraIngresoRetencionParcial().setToolTipText("Ver Ingreso Pago Retención y Parcial");
		getCbVerCarteraIngresoParcial().setToolTipText("Ver Ingreso Pago Parcial");
		getCbVerCarteraIngresoRetencion().setToolTipText("Ver Ingreso Pago Retención");
		getCbVerCarteraEgresoTotal().setToolTipText("Ver Egreso Pago Total");
		getCbVerCarteraEgresoRetencionParcial().setToolTipText("Ver Egreso Pago Retención y Parcial");
		getCbVerCarteraEgresoParcial().setToolTipText("Ver Egreso Pago Parcial");
		getCbVerCarteraEgresoRetencion().setToolTipText("Ver Egreso Pago Retención");
		
		TableCellRendererHorizontalCenterAlignment tableCellCenterRenderer = new TableCellRendererHorizontalCenterAlignment();
		getTblInversion().getColumnModel().getColumn(1).setCellRenderer(tableCellCenterRenderer);
		getTblInversion().getColumnModel().getColumn(2).setCellRenderer(tableCellCenterRenderer);
		getTblInversion().getColumnModel().getColumn(3).setCellRenderer(tableCellCenterRenderer);
		getTblInversion().getColumnModel().getColumn(4).setCellRenderer(tableCellCenterRenderer);
		getTblInversion().getColumnModel().getColumn(5).setCellRenderer(tableCellCenterRenderer);
		getTblInversion().getColumnModel().getColumn(6).setCellRenderer(tableCellCenterRenderer);
		getTblInversion().getColumnModel().getColumn(7).setCellRenderer(tableCellCenterRenderer);
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblInversion().getColumnModel().getColumn(12).setCellRenderer(tableCellRenderer);
		getTblInversion().getColumnModel().getColumn(13).setCellRenderer(tableCellRenderer);
		getTblInversion().getColumnModel().getColumn(14).setCellRenderer(tableCellRenderer);
	}
	
	private void initListeners() {
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbFiltrarCodigoPresupuesto().isSelected() && getTxtPresupuesto().getText().equals("")){
					SpiritAlert.createAlert("Debe ingresar el Código del Presupuesto.", SpiritAlert.WARNING);
					getTxtPresupuesto().grabFocus();
				}else{
					cargarTabla();
					Iterator problemasIt = problemas.keySet().iterator();
					while(problemasIt.hasNext()){
						String problema = (String)problemasIt.next();
						System.out.println("PROBLEMA: " + problema);
					}
				}				
			}
		});
		
		getCbVerCarteraIngresos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbVerCarteraIngresos().isSelected()){
					getCbVerCarteraIngresoTotal().setEnabled(true);
					getCbVerCarteraIngresoRetencionParcial().setEnabled(true);
					getCbVerCarteraIngresoParcial().setEnabled(true);
					getCbVerCarteraIngresoRetencion().setEnabled(true);
					getCbVerCarteraIngresoTotal().setSelected(true);
					getCbVerCarteraIngresoRetencionParcial().setSelected(true);
					getCbVerCarteraIngresoParcial().setSelected(true);
					getCbVerCarteraIngresoRetencion().setSelected(true);
				}else{
					getCbVerCarteraIngresoTotal().setEnabled(false);
					getCbVerCarteraIngresoRetencionParcial().setEnabled(false);
					getCbVerCarteraIngresoParcial().setEnabled(false);
					getCbVerCarteraIngresoRetencion().setEnabled(false);
					getCbVerCarteraIngresoTotal().setSelected(false);
					getCbVerCarteraIngresoRetencionParcial().setSelected(false);
					getCbVerCarteraIngresoParcial().setSelected(false);
					getCbVerCarteraIngresoRetencion().setSelected(false);
				}
			}
		});
		
		getCbVerCarteraIngresoTotal().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if( !getCbVerCarteraIngresoTotal().isSelected()
						&& !getCbVerCarteraIngresoRetencionParcial().isSelected()
						&& !getCbVerCarteraIngresoParcial().isSelected()
						&& !getCbVerCarteraIngresoRetencion().isSelected()){
					getCbVerCarteraIngresos().setSelected(false);
				}else{
					getCbVerCarteraIngresos().setSelected(true);
				}
				
				if(getCbVerCarteraIngresoTotal().isSelected()){
					carteraIngresoTotalSeleccionado = true;
				}else{
					carteraIngresoTotalSeleccionado = false;
				}
			}
		});
		
		getCbVerCarteraIngresoRetencionParcial().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if( !getCbVerCarteraIngresoTotal().isSelected()
						&& !getCbVerCarteraIngresoRetencionParcial().isSelected()
						&& !getCbVerCarteraIngresoParcial().isSelected()
						&& !getCbVerCarteraIngresoRetencion().isSelected()){
					getCbVerCarteraIngresos().setSelected(false);
				}else{
					getCbVerCarteraIngresos().setSelected(true);
				}
				
				if(getCbVerCarteraIngresoRetencionParcial().isSelected()){
					carteraIngresoRetencionParcialSeleccionado = true;
				}else{
					carteraIngresoRetencionParcialSeleccionado = false;
				}
			}
		});
		
		getCbVerCarteraIngresoParcial().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if( !getCbVerCarteraIngresoTotal().isSelected()
						&& !getCbVerCarteraIngresoRetencionParcial().isSelected()
						&& !getCbVerCarteraIngresoParcial().isSelected()
						&& !getCbVerCarteraIngresoRetencion().isSelected()){
					getCbVerCarteraIngresos().setSelected(false);
				}else{
					getCbVerCarteraIngresos().setSelected(true);
				}
				
				if(getCbVerCarteraIngresoParcial().isSelected()){
					carteraIngresoParcialSeleccionado = true;
				}else{
					carteraIngresoParcialSeleccionado = false;
				}
			}
		});
		
		getCbVerCarteraIngresoRetencion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if( !getCbVerCarteraIngresoTotal().isSelected()
						&& !getCbVerCarteraIngresoRetencionParcial().isSelected()
						&& !getCbVerCarteraIngresoParcial().isSelected()
						&& !getCbVerCarteraIngresoRetencion().isSelected()){
					getCbVerCarteraIngresos().setSelected(false);
				}else{
					getCbVerCarteraIngresos().setSelected(true);
				}
				
				if(getCbVerCarteraIngresoRetencion().isSelected()){
					carteraIngresoRetencionSeleccionado = true;
				}else{
					carteraIngresoRetencionSeleccionado = false;
				}
			}
		});
		
		getCbVerCarteraEgresos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCbVerCarteraEgresos().isSelected()){
					getCbVerCarteraEgresoTotal().setEnabled(true);
					getCbVerCarteraEgresoRetencionParcial().setEnabled(true);
					getCbVerCarteraEgresoParcial().setEnabled(true);
					getCbVerCarteraEgresoRetencion().setEnabled(true);
					getCbVerCarteraEgresoTotal().setSelected(true);
					getCbVerCarteraEgresoRetencionParcial().setSelected(true);
					getCbVerCarteraEgresoParcial().setSelected(true);
					getCbVerCarteraEgresoRetencion().setSelected(true);
				}else{
					getCbVerCarteraEgresoTotal().setEnabled(false);
					getCbVerCarteraEgresoRetencionParcial().setEnabled(false);
					getCbVerCarteraEgresoParcial().setEnabled(false);
					getCbVerCarteraEgresoRetencion().setEnabled(false);
					getCbVerCarteraEgresoTotal().setSelected(false);
					getCbVerCarteraEgresoRetencionParcial().setSelected(false);
					getCbVerCarteraEgresoParcial().setSelected(false);
					getCbVerCarteraEgresoRetencion().setSelected(false);
				}
			}
		});
		
		getCbVerCarteraEgresoTotal().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if( !getCbVerCarteraEgresoTotal().isSelected()
						&& !getCbVerCarteraEgresoRetencionParcial().isSelected()
						&& !getCbVerCarteraEgresoParcial().isSelected()
						&& !getCbVerCarteraEgresoRetencion().isSelected()){
					getCbVerCarteraEgresos().setSelected(false);
				}else{
					getCbVerCarteraEgresos().setSelected(true);
				}
				
				if(getCbVerCarteraEgresoTotal().isSelected()){
					carteraEgresoTotalSeleccionado = true;
				}else{
					carteraEgresoTotalSeleccionado = false;
				}
			}
		});
		
		getCbVerCarteraEgresoRetencionParcial().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if( !getCbVerCarteraEgresoTotal().isSelected()
						&& !getCbVerCarteraEgresoRetencionParcial().isSelected()
						&& !getCbVerCarteraEgresoParcial().isSelected()
						&& !getCbVerCarteraEgresoRetencion().isSelected()){
					getCbVerCarteraEgresos().setSelected(false);
				}else{
					getCbVerCarteraEgresos().setSelected(true);
				}
				
				if(getCbVerCarteraEgresoRetencionParcial().isSelected()){
					carteraEgresoRetencionParcialSeleccionado = true;
				}else{
					carteraEgresoRetencionParcialSeleccionado = false;
				}
			}
		});
		
		getCbVerCarteraEgresoParcial().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if( !getCbVerCarteraEgresoTotal().isSelected()
						&& !getCbVerCarteraEgresoRetencionParcial().isSelected()
						&& !getCbVerCarteraEgresoParcial().isSelected()
						&& !getCbVerCarteraEgresoRetencion().isSelected()){
					getCbVerCarteraEgresos().setSelected(false);
				}else{
					getCbVerCarteraEgresos().setSelected(true);
				}
				
				if(getCbVerCarteraEgresoParcial().isSelected()){
					carteraEgresoParcialSeleccionado = true;
				}else{
					carteraEgresoParcialSeleccionado = false;
				}
			}
		});
		
		getCbVerCarteraEgresoRetencion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if( !getCbVerCarteraEgresoTotal().isSelected()
						&& !getCbVerCarteraEgresoRetencionParcial().isSelected()
						&& !getCbVerCarteraEgresoParcial().isSelected()
						&& !getCbVerCarteraEgresoRetencion().isSelected()){
					getCbVerCarteraEgresos().setSelected(false);
				}else{
					getCbVerCarteraEgresos().setSelected(true);
				}
				
				if(getCbVerCarteraEgresoRetencion().isSelected()){
					carteraEgresoRetencionSeleccionado = true;
				}else{
					carteraEgresoRetencionSeleccionado = false;
				}
			}
		});
		
		getCbFiltrarCodigoPresupuesto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getTxtPresupuesto().setText("");
				if (getCbFiltrarCodigoPresupuesto().isSelected()){
					getTxtPresupuesto().setEditable(true);
				}
				else{
					getTxtPresupuesto().setEditable(false);
				}
			}
		});
		
		getCbTodos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodos().isSelected()){
					clienteOficinaIf = null;
					getTxtCliente().setText("");
				}
			}
		});
		
		getBtnCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				String tituloVentanaBusqueda = "de Oficinas del Cliente";
				clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_CLIENTE, "", false);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					getTxtCliente().setText(clienteOficinaIf.getDescripcion());
					getCbTodos().setSelected(false);
				}
			}
		});
		
		getBtnProveedorOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				Long idCliente = 0L;
				Long idTipoProveedor = 0L;
				
				String tituloVentanaBusqueda = "Oficinas de Proveedores";
									
				if (medioIf != null){
					idCliente = medioIf.getId();
					idCorporacion = medioIf.getCorporacionId();					
				}
				
				//ADD 27 JULIO
				if (subtipoProveedorIf != null){
					idTipoProveedor = subtipoProveedorIf.getId();			
					medioOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_PROVEEDOR, idTipoProveedor,"", false);
				}else
					medioOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, CODIGO_TIPO_PROVEEDOR, "", false);
				//END 27 JULIO
				
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.addElement(70);
				anchoColumnas.addElement(200);
				anchoColumnas.addElement(80);
				anchoColumnas.addElement(230);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), medioOficinaCriteria,JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if(popupFinder.getElementoSeleccionado() != null){
					medioOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
					
					if (medioIf == null) {
						try {
							medioIf = SessionServiceLocator.getClienteSessionService().getCliente(medioOficinaIf.getClienteId());
							//getTxtProveedor().setText(medioIf.getNombreLegal());
							//getCbTodosProveedores().setSelected(false);
							
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					getTxtProveedorOficina().setText(medioOficinaIf.getDescripcion());
					getCbTodosProveedorOficina().setSelected(false);
				}
			}		
		});
				
		getCbTodosProveedorOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodosProveedorOficina().isSelected()){
					medioOficinaIf = null;
					medioIf = null;
					getTxtProveedorOficina().setText("");
				}
			}
		});
		
		getCmbSubtipoProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbSubtipoProveedor().getSelectedItem() != null && !getCmbSubtipoProveedor().getSelectedItem().equals(TODOS)){
					subtipoProveedorIf = (TipoProveedorIf) getCmbSubtipoProveedor().getSelectedItem();
					if (medioOficinaIf != null){
						getTxtProveedorOficina().setText("");
						getCbTodosProveedorOficina().setSelected(true);
						medioOficinaIf = null;
					}
				}
				else{
					subtipoProveedorIf = null;
				}
			}
		});
		
		getCmbEstado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbEstado().getSelectedItem() != null){
					if(getCmbEstado().getSelectedItem().equals("COTIZADO")){
						estado = "COTIZADO";
						getCbAprobadosVsFacturados().setEnabled(false);
						getCbAprobadosVsFacturados().setSelected(false);
						getLblFechasAprobacion().setVisible(false);
					}else if(getCmbEstado().getSelectedItem().equals("APROBADO")){
						estado = "APROBADO";
						getCbAprobadosVsFacturados().setEnabled(false);
						getCbAprobadosVsFacturados().setSelected(false);
						if(!getCbReporteInversionTotales().isSelected() && !getCbReporteComisionAdicional().isSelected()){
							getLblFechasAprobacion().setVisible(true);
						}
					}else if(getCmbEstado().getSelectedItem().equals("FACTURADO")){
						estado = "FACTURADO";
						getCbAprobadosVsFacturados().setEnabled(false);
						getCbAprobadosVsFacturados().setSelected(false);
						if(!getCbReporteInversionTotales().isSelected() && !getCbReporteComisionAdicional().isSelected()){
							getLblFechasAprobacion().setVisible(true);
						}
					}else if(getCmbEstado().getSelectedItem().equals("PREPAGADO")){
						estado = "PREPAGADO";
						getCbAprobadosVsFacturados().setEnabled(false);
						getCbAprobadosVsFacturados().setSelected(false);
						if(!getCbReporteInversionTotales().isSelected() && !getCbReporteComisionAdicional().isSelected()){
							getLblFechasAprobacion().setVisible(true);
						}
					}else{
						estado = TODOS;
						getCbAprobadosVsFacturados().setEnabled(true);
						if(!getCbReporteInversionTotales().isSelected() && !getCbReporteComisionAdicional().isSelected()){
							getLblFechasAprobacion().setVisible(true);
						}
					}
				}
				else{
					estado = TODOS;
				}
			}
		});
		
		getCbReporteInversionTotales().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbReporteInversionTotales().isSelected()){
					getLblFechasAprobacion().setVisible(false);
				}else if(!getCmbEstado().getSelectedItem().equals("COTIZADO") 
						&& !getCbReporteComisionAdicional().isSelected()){
					getLblFechasAprobacion().setVisible(true);
				}
			}
		});
		
		getCbReporteComisionAdicional().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbReporteComisionAdicional().isSelected()){
					getLblFechasAprobacion().setVisible(false);
				}else if(!getCmbEstado().getSelectedItem().equals("COTIZADO") 
						&& !getCbReporteInversionTotales().isSelected()){
					getLblFechasAprobacion().setVisible(true);
				}
			}
		});
		
		getCmbTipoProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoProveedor().getSelectedItem() != null){
					if(getCmbTipoProveedor().getSelectedItem().equals("MEDIOS")){
						tipoProveedor = "MEDIOS";
					}else if(getCmbTipoProveedor().getSelectedItem().equals("PRODUCCION")){
						tipoProveedor = "PRODUCCION";
					}else{
						tipoProveedor = TODOS;
					}
				}
				else{
					tipoProveedor = TODOS;
				}
			}
		});
		
		getCbVerFacturasNegociacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbVerFacturasNegociacion().isSelected()){
					getCbVerFacturas().setSelected(true);
				}
			}
		});
	}
	
	Comparator<InversionClientesData> ordenadorInversionClientesDataPorClienteOficina = new Comparator<InversionClientesData>(){
		public int compare(InversionClientesData o1, InversionClientesData o2) {
			return o1.getClienteOficina().compareTo(o2.getClienteOficina());			
		}		
	};
	
	Comparator<InversionClientesData> ordenadorInversionClientesDataPorCodigoPresupuesto = new Comparator<InversionClientesData>(){
		public int compare(InversionClientesData o1, InversionClientesData o2) {
			return o1.getCodigoPresupuesto().compareTo(o2.getCodigoPresupuesto());			
		}		
	};
	
	Comparator<InversionClientesData> ordenadorInversionClientesDataPorPosicion = new Comparator<InversionClientesData>(){
		public int compare(InversionClientesData o1, InversionClientesData o2) {
			return o1.getPosicion().compareTo(o2.getPosicion());			
		}		
	};
	
	Comparator<InversionClientesData> ordenadorInversionClientesDataPorDate = new Comparator<InversionClientesData>(){
		public int compare(InversionClientesData o1, InversionClientesData o2) {
			return o1.getDate().compareTo(o2.getDate());			
		}		
	};
	
	Comparator<InversionClientesData> ordenadorInversionClientesDataPorSegmento = new Comparator<InversionClientesData>(){
		public int compare(InversionClientesData o1, InversionClientesData o2) {
			return o1.getSegmento().compareTo(o2.getSegmento());			
		}		
	};
	
	Comparator<InversionClientesData> ordenadorInversionClientesDataPorProducto = new Comparator<InversionClientesData>(){
		public int compare(InversionClientesData o1, InversionClientesData o2) {
			return o1.getProducto().compareTo(o2.getProducto());			
		}		
	};
	
	//ORDENADORES PARA ARCHIVO EXCEL
	
	Comparator<InversionTotalesClientesData> ordenadorInversionTotalesClientesDataPorClienteOficina = new Comparator<InversionTotalesClientesData>(){
		public int compare(InversionTotalesClientesData o1, InversionTotalesClientesData o2) {
			return o1.getClienteOficina().compareTo(o2.getClienteOficina());			
		}		
	};
	
	Comparator<InversionTotalesClientesData> ordenadorInversionTotalesClientesDataPorCodigoPresupuesto = new Comparator<InversionTotalesClientesData>(){
		public int compare(InversionTotalesClientesData o1, InversionTotalesClientesData o2) {
			return o1.getCodigoPresupuesto().compareTo(o2.getCodigoPresupuesto());			
		}		
	};
	
	Comparator<InversionTotalesClientesData> ordenadorInversionTotalesClientesDataPorDate = new Comparator<InversionTotalesClientesData>(){
		public int compare(InversionTotalesClientesData o1, InversionTotalesClientesData o2) {
			return o1.getDate().compareTo(o2.getDate());			
		}		
	};
	
	Comparator<ComisionesAdicionalesData> ordenadorComisionesAdicionalesDataPorCodigoPresupuesto = new Comparator<ComisionesAdicionalesData>(){
		public int compare(ComisionesAdicionalesData o1, ComisionesAdicionalesData o2) {
			return o1.getCodigoPresupuesto().compareTo(o2.getCodigoPresupuesto());			
		}		
	};
	
	public void resetearValores(){
		facturas = new Double[13];
		for(int i=0; i < facturas.length; i++)
			facturas[i] = 0D;
		
		ordenes = new Double[13];
		for(int i=0; i < ordenes.length; i++)
			ordenes[i] = 0D;
		
		compras = new Double[13];
		for(int i=0; i < compras.length; i++)
			compras[i] = 0D;
		
		totalFacturas = 0D;
		totalOrdenes = 0D;
		totalCompras = 0D;
		
		cantidadFacturas = new Integer[13];
		for(int i=0; i < cantidadFacturas.length; i++)
			cantidadFacturas[i] = 0;
		
		cantidadOrdenes = new Integer[13];
		for(int i=0; i < cantidadOrdenes.length; i++)
			cantidadOrdenes[i] = 0;
		
		cantidadCompras = new Integer[13];
		for(int i=0; i < cantidadCompras.length; i++)
			cantidadCompras[i] = 0;
		
		totalCantidadFacturas = 0;
		totalCantidadOrdenes = 0;
		totalCantidadCompras = 0;
	}
	
	private void cargarTabla() {
		cleanTable();
		resetearValores();
		ordenesMedioDetalle = new ArrayList<Object[]>();
		presupuestosDetalle = new ArrayList();
		presupuestosDetalleSinOrdenes = new ArrayList();
		obtenerOrdenesMedioPresupuestosDetalle();
		llenarVectorInversionClientesData();
		
		//ordenar vector
		//Collections.sort((ArrayList)inversionClientesDataVector,ordenadorInversionClientesDataPorProducto);
		//Collections.sort((ArrayList)inversionClientesDataVector,ordenadorInversionClientesDataPorSegmento);
		Collections.sort((ArrayList)inversionClientesDataVector,ordenadorInversionClientesDataPorPosicion);
		Collections.sort((ArrayList)inversionClientesDataVector,ordenadorInversionClientesDataPorCodigoPresupuesto);
		Collections.sort((ArrayList)inversionClientesDataVector,ordenadorInversionClientesDataPorDate);
		Collections.sort((ArrayList)inversionClientesDataVector,ordenadorInversionClientesDataPorClienteOficina);
		
		for(InversionClientesData inversionClientesData : inversionClientesDataVector){
			tableModel = (DefaultTableModel) getTblInversion().getModel();
			Vector<String> fila = new Vector<String>();			
			agregarColumnasTabla(inversionClientesData, fila);
			tableModel.addRow(fila);
		}
		
		if(inversionClientesDataVector.size() == 0){
			SpiritAlert.createAlert("No existen datos que presentar.", SpiritAlert.INFORMATION);
		}
		
		report();
		
		//ARCHIVO EXCEL REPORTE DE INVERSION
		if(getCbReporteInversionTotales().isSelected() && inversionTotalClientesDataVector.size() > 0){
			Collections.sort((ArrayList)inversionTotalClientesDataVector,ordenadorInversionTotalesClientesDataPorCodigoPresupuesto);
			Collections.sort((ArrayList)inversionTotalClientesDataVector,ordenadorInversionTotalesClientesDataPorDate);
			Collections.sort((ArrayList)inversionTotalClientesDataVector,ordenadorInversionTotalesClientesDataPorClienteOficina);
			generarArchivoExcelReporteInversion();
		}
		
		//ARCHIVO EXCEL COMISIONES ADICIONALES
		if(getCbReporteComisionAdicional().isSelected() && comisionesAdicionalesDataVector.size() > 0){
			Collections.sort((ArrayList)comisionesAdicionalesDataVector,ordenadorComisionesAdicionalesDataPorCodigoPresupuesto);
			//Collections.sort((ArrayList)inversionTotalClientesDataVector,ordenadorInversionTotalesClientesDataPorDate);
			//Collections.sort((ArrayList)inversionTotalClientesDataVector,ordenadorInversionTotalesClientesDataPorClienteOficina);
			generarArchivoExcelComisionesAdicionales();
		}
	}
	
	private void agregarColumnasTabla(InversionClientesData inversionClientesData, Vector<String> fila){
		fila.add(inversionClientesData.getClienteOficina());
		fila.add(inversionClientesData.getFechaAprobacion());
		fila.add(inversionClientesData.getCodigoPresupuesto());
		fila.add(inversionClientesData.getSap());
		fila.add(inversionClientesData.getSegmento());
		fila.add(inversionClientesData.getProducto());
		fila.add(inversionClientesData.getTipoFactura());
		fila.add(inversionClientesData.getCartera());
		fila.add(inversionClientesData.getFechaFactura());
		fila.add(inversionClientesData.getFactura());
		fila.add(inversionClientesData.getMedio());
		fila.add(inversionClientesData.getProveedor());
		fila.add(inversionClientesData.getValor());
		fila.add(inversionClientesData.getIva());
		fila.add(inversionClientesData.getTotal());
	}
	
	public void generarArchivoExcelReporteInversion(){
		try {
			//PARA QUE EL NOMBRE DEL ARCHIVO PONER LA FECHA INICIO Y FIN DE LA BUSQUEDA
			Calendar fechaInicio = new GregorianCalendar();
			fechaInicio.setTimeInMillis(getCmbFechaInicio().getDate().getTime());
			String anioInicio = String.valueOf(fechaInicio.getTime().getYear()+1900);
			String mesInicio = String.valueOf(fechaInicio.getTime().getMonth()+1);
			if((fechaInicio.getTime().getMonth()+1) < 10){
				mesInicio = "0"+mesInicio;
			}			
			String diaInicio = String.valueOf(fechaInicio.getTime().getDate());
			if(fechaInicio.getTime().getDate() < 10){
				diaInicio = "0"+diaInicio;
			}
			String anioMesDiaInicio = anioInicio+mesInicio+diaInicio;
			
			Calendar fechaFin = new GregorianCalendar();
			fechaFin.setTimeInMillis(getCmbFechaFin().getDate().getTime());
			String anioFin = String.valueOf(fechaFin.getTime().getYear()+1900);
			String mesFin = String.valueOf(fechaFin.getTime().getMonth()+1);
			if((fechaFin.getTime().getMonth()+1) < 10){
				mesFin = "0"+mesFin;
			}			
			String diaFin = String.valueOf(fechaFin.getTime().getDate());
			if(fechaFin.getTime().getDate() < 10){
				diaFin = "0"+diaFin;
			}
			String anioMesDiaFin = anioFin+mesFin+diaFin;
						
			//COMIENZA LA CREACION DEL ARCHIVO
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("InversionTotalesCliente");
			
			//primera fila, Nombres de las 7 columnas
			HSSFRow filaNumeros = hoja.createRow(0);
			for(int i=0; i<7; i++){
				HSSFCell celda = filaNumeros.createCell(i);
				HSSFRichTextString texto = new HSSFRichTextString(inversionTotalClientesDataVector.get(0).getNombreAtributo(i+1));
				celda.setCellValue(texto);
			}
			
			//lleno desde la segunda fila la información de los 7 campos			
			for(int j = 0; j < inversionTotalClientesDataVector.size(); j++){
				InversionTotalesClientesData inversionTotalClientesData = inversionTotalClientesDataVector.get(j);
				HSSFRow filaCampos = hoja.createRow(j+1);
				
				for(int i=0; i<7; i++){
					HSSFCell celda = filaCampos.createCell(i);
					HSSFRichTextString texto = new HSSFRichTextString(inversionTotalClientesData.getCampo(i+1));
					celda.setCellValue(texto);
				}				
			}
			
			//indico que las columnas tengan el ancho de su texto
			for(int columnIndex = 0; columnIndex < 7; columnIndex++) {
				hoja.autoSizeColumn(columnIndex);
			}
			
			//escribo el archivo
			FileOutputStream archivo = new FileOutputStream("C:\\Temp\\INVERSION_TOTALES_CLIENTE_"+anioMesDiaInicio+"_"+anioMesDiaFin+".xls");
			libro.write(archivo);
			archivo.close();
			System.out.println("Archivo de Reporte de Inversión creado con éxito!");
			
			//long fin=System.currentTimeMillis();
			//System.out.println("tiempo: "+(fin-start)/1000+" seg");
			
			SpiritAlert.createAlert("Archivo de Reporte de Inversión creado con éxito.", SpiritAlert.INFORMATION);
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("No se pudo crear el Excel: File");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No se pudo crear el Excel: IO");
		}
	}
	
	public void generarArchivoExcelComisionesAdicionales(){
		try {
			//PARA QUE EL NOMBRE DEL ARCHIVO PONER LA FECHA INICIO Y FIN DE LA BUSQUEDA
			Calendar fechaInicio = new GregorianCalendar();
			fechaInicio.setTimeInMillis(getCmbFechaInicio().getDate().getTime());
			String anioInicio = String.valueOf(fechaInicio.getTime().getYear()+1900);
			String mesInicio = String.valueOf(fechaInicio.getTime().getMonth()+1);
			if((fechaInicio.getTime().getMonth()+1) < 10){
				mesInicio = "0"+mesInicio;
			}			
			String diaInicio = String.valueOf(fechaInicio.getTime().getDate());
			if(fechaInicio.getTime().getDate() < 10){
				diaInicio = "0"+diaInicio;
			}
			String anioMesDiaInicio = anioInicio+mesInicio+diaInicio;
			
			Calendar fechaFin = new GregorianCalendar();
			fechaFin.setTimeInMillis(getCmbFechaFin().getDate().getTime());
			String anioFin = String.valueOf(fechaFin.getTime().getYear()+1900);
			String mesFin = String.valueOf(fechaFin.getTime().getMonth()+1);
			if((fechaFin.getTime().getMonth()+1) < 10){
				mesFin = "0"+mesFin;
			}			
			String diaFin = String.valueOf(fechaFin.getTime().getDate());
			if(fechaFin.getTime().getDate() < 10){
				diaFin = "0"+diaFin;
			}
			String anioMesDiaFin = anioFin+mesFin+diaFin;
						
			//COMIENZA LA CREACION DEL ARCHIVO
			HSSFWorkbook libro = new HSSFWorkbook();
			HSSFSheet hoja = libro.createSheet("ComisionesAdicionales");
			
			//negritas
			HSSFCellStyle cellStyle = libro.createCellStyle();
			HSSFFont font = libro.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setFont(font);
			
			//primera fila, Nombres de las 12 columnas
			HSSFRow filaNumeros = hoja.createRow(0);
			for(int i=0; i<12; i++){
				HSSFCell celda = filaNumeros.createCell(i);
				HSSFRichTextString texto = new HSSFRichTextString(comisionesAdicionalesDataVector.get(0).getNombreAtributo(i+1));
				celda.setCellValue(texto);
				celda.setCellStyle(cellStyle);
			}
			
			//lleno desde la segunda fila la información de los 12 campos			
			for(int j = 0; j < comisionesAdicionalesDataVector.size(); j++){
				ComisionesAdicionalesData comisionesAdicionalesData = comisionesAdicionalesDataVector.get(j);
				HSSFRow filaCampos = hoja.createRow(j+1);
				
				for(int i=0; i<12; i++){
					HSSFCell celda = filaCampos.createCell(i);
					HSSFRichTextString texto = new HSSFRichTextString(comisionesAdicionalesData.getCampo(i+1));
					celda.setCellValue(texto);
				}				
			}
			
			//indico que las columnas tengan el ancho de su texto
			for(int columnIndex = 0; columnIndex < 12; columnIndex++) {
				hoja.autoSizeColumn(columnIndex);
			}
			
			//escribo el archivo
			FileOutputStream archivo = new FileOutputStream("C:\\Temp\\COMISIONES_ADICIONALES_"+anioMesDiaInicio+"_"+anioMesDiaFin+".xls");
			libro.write(archivo);
			archivo.close();
			System.out.println("Archivo de Comisiones Adicionales creado con éxito!");
			
			//long fin=System.currentTimeMillis();
			//System.out.println("tiempo: "+(fin-start)/1000+" seg");
			
			SpiritAlert.createAlert("Archivo de Comisiones Adicionales creado con éxito.", SpiritAlert.INFORMATION);
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("No se pudo crear el Excel: File");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No se pudo crear el Excel: IO");
		}
	}
	
	public void reiniciarValores(){
		//mapa para no tomar en cuenta dos veces la misma factura en los casos de facturacion multiple
		//(dos o mas presupuestos en la misma factura)
		facturaIngresada = new HashMap<Long,Long>();
		
		inversionClientesDataVector = new ArrayList<InversionClientesData>();
		inversionTotalClientesDataVector = new ArrayList<InversionTotalesClientesData>();
		comisionesAdicionalesDataVector = new ArrayList<ComisionesAdicionalesData>();
		
		//totales por estado
		presupuestosCotizados = 0;
		presupuestosFacturados = 0;
		presupuestosPendientes = 0;
		totalCotizado = new BigDecimal(0);
		totalFacturado = new BigDecimal(0);
		totalPendiente = new BigDecimal(0);
		
		presupuestosCotizadosEne = 0;
		presupuestosFacturadosEne = 0;
		presupuestosPendientesEne = 0;
		totalCotizadoEne = new BigDecimal(0);
		totalFacturadoEne = new BigDecimal(0);
		totalPendienteEne = new BigDecimal(0);
		presupuestosCotizadosFeb = 0;
		presupuestosFacturadosFeb = 0;
		presupuestosPendientesFeb = 0;
		totalCotizadoFeb = new BigDecimal(0);
		totalFacturadoFeb = new BigDecimal(0);
		totalPendienteFeb = new BigDecimal(0);			
		presupuestosCotizadosMar = 0;
		presupuestosFacturadosMar = 0;
		presupuestosPendientesMar = 0;
		totalCotizadoMar = new BigDecimal(0);
		totalFacturadoMar = new BigDecimal(0);
		totalPendienteMar = new BigDecimal(0);
		presupuestosCotizadosAbr = 0;
		presupuestosFacturadosAbr = 0;
		presupuestosPendientesAbr = 0;
		totalCotizadoAbr = new BigDecimal(0);
		totalFacturadoAbr = new BigDecimal(0);
		totalPendienteAbr = new BigDecimal(0);
		presupuestosCotizadosMay = 0;
		presupuestosFacturadosMay = 0;
		presupuestosPendientesMay = 0;
		totalCotizadoMay = new BigDecimal(0);
		totalFacturadoMay = new BigDecimal(0);
		totalPendienteMay = new BigDecimal(0);
		presupuestosCotizadosJun = 0;
		presupuestosFacturadosJun = 0;
		presupuestosPendientesJun = 0;
		totalCotizadoJun = new BigDecimal(0);
		totalFacturadoJun = new BigDecimal(0);
		totalPendienteJun = new BigDecimal(0);
		presupuestosCotizadosJul = 0;
		presupuestosFacturadosJul = 0;
		presupuestosPendientesJul = 0;
		totalCotizadoJul = new BigDecimal(0);
		totalFacturadoJul = new BigDecimal(0);
		totalPendienteJul = new BigDecimal(0);
		presupuestosCotizadosAgo = 0;
		presupuestosFacturadosAgo = 0;
		presupuestosPendientesAgo = 0;
		totalCotizadoAgo = new BigDecimal(0);
		totalFacturadoAgo = new BigDecimal(0);
		totalPendienteAgo = new BigDecimal(0);
		presupuestosCotizadosSep = 0;
		presupuestosFacturadosSep = 0;
		presupuestosPendientesSep = 0;
		totalCotizadoSep = new BigDecimal(0);
		totalFacturadoSep = new BigDecimal(0);
		totalPendienteSep = new BigDecimal(0);
		presupuestosCotizadosOct = 0;
		presupuestosFacturadosOct = 0;
		presupuestosPendientesOct = 0;
		totalCotizadoOct = new BigDecimal(0);
		totalFacturadoOct = new BigDecimal(0);
		totalPendienteOct = new BigDecimal(0);
		presupuestosCotizadosNov = 0;
		presupuestosFacturadosNov = 0;
		presupuestosPendientesNov = 0;
		totalCotizadoNov = new BigDecimal(0);
		totalFacturadoNov = new BigDecimal(0);
		totalPendienteNov = new BigDecimal(0);
		presupuestosCotizadosDic = 0;
		presupuestosFacturadosDic = 0;
		presupuestosPendientesDic = 0;
		totalCotizadoDic = new BigDecimal(0);
		totalFacturadoDic = new BigDecimal(0);
		totalPendienteDic = new BigDecimal(0);
	}
	
	public void llenarVectorInversionClientesData(){		
		try {
			reiniciarValores();
						
			//ORDENES DE MEDIO DETALLE
			Map<Long,Vector<Object[]>> mapaPlanMedioOrdenesMedioDetalleVector = new HashMap<Long,Vector<Object[]>>(); 
			Vector<Object[]> ordenesMedioDetalleDelPlan = new Vector<Object[]>();
			Map<Long,PlanMedioIf> mapaPlanMedio = new HashMap<Long,PlanMedioIf>();
			
			Iterator ordenesMedioDetalleIt = ordenesMedioDetalle.iterator();
			
			//Agrupo en un mapa los planes con su vector de ordenes medio detalle
			while(ordenesMedioDetalleIt.hasNext()){
				Object[] ordenesMedioDetalleObj = (Object[])ordenesMedioDetalleIt.next();
				OrdenMedioDetalleIf ordenMedioDetalle = (OrdenMedioDetalleIf)ordenesMedioDetalleObj[1];
				OrdenMedioIf ordenMedio = (OrdenMedioIf)ordenesMedioDetalleObj[0];
				PlanMedioIf planMedio = (PlanMedioIf)ordenesMedioDetalleObj[3];
				mapaPlanMedio.put(planMedio.getId(), planMedio);
				
				if(planMedio.getCodigo().equals("2014-01967")){
					System.out.println("aqui");
				}
												
				if(mapaPlanMedioOrdenesMedioDetalleVector.get(planMedio.getId()) == null){
					ordenesMedioDetalleDelPlan = new Vector<Object[]>();
					Object[] ordenDetalleOrdenCabecera = new Object[2];
					ordenDetalleOrdenCabecera[0] = ordenMedioDetalle;
					ordenDetalleOrdenCabecera[1] = ordenMedio;
					ordenesMedioDetalleDelPlan.add(ordenDetalleOrdenCabecera);
					mapaPlanMedioOrdenesMedioDetalleVector.put(planMedio.getId(), ordenesMedioDetalleDelPlan);
				}else{
					ordenesMedioDetalleDelPlan = mapaPlanMedioOrdenesMedioDetalleVector.get(planMedio.getId());
					Object[] ordenDetalleOrdenCabecera = new Object[2];
					ordenDetalleOrdenCabecera[0] = ordenMedioDetalle;
					ordenDetalleOrdenCabecera[1] = ordenMedio;
					ordenesMedioDetalleDelPlan.add(ordenDetalleOrdenCabecera);
					mapaPlanMedioOrdenesMedioDetalleVector.put(planMedio.getId(), ordenesMedioDetalleDelPlan);
				}				
			}
			
			Iterator mapaPlanMedioOrdenesMedioDetalleVectorIt =  mapaPlanMedioOrdenesMedioDetalleVector.keySet().iterator();
			while(mapaPlanMedioOrdenesMedioDetalleVectorIt.hasNext()){
				Long planMedioId = (Long)mapaPlanMedioOrdenesMedioDetalleVectorIt.next();
				PlanMedioIf planMedio = mapaPlanMedio.get(planMedioId);
				Vector<Object[]> ordenesMedioDetallePlan = mapaPlanMedioOrdenesMedioDetalleVector.get(planMedioId);
				
				//Lleno la información de cada Plan
				//if(planMedio.getCodigo().equals("2012-00474")){
					cargarInversionClientesDataVectorMedios(ordenesMedioDetallePlan, planMedio);			
				//}
			}
			
			
			//PRESUPUESTOS DETALLE
			Map<Long,Vector<Object>> mapaPresupuestoPresupuestoDetalleVector = new HashMap<Long,Vector<Object>>(); 
			Vector<Object> presupuestosDetalleDelPresupuesto = new Vector<Object>();
			Map<Long,PresupuestoIf> mapaPresupuesto = new HashMap<Long,PresupuestoIf>();
			
			//Agrupo en un mapa los planes con su vector de ordenes medio detalle
			Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
			while(presupuestosDetalleIt.hasNext()){
				Object[] presupuestoDetalle = (Object[])presupuestosDetalleIt.next();
				Long presupuestoId = (Long)presupuestoDetalle[16];
				PresupuestoIf presupuesto = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoId);
				mapaPresupuesto.put(presupuesto.getId(), presupuesto);
				
				if(mapaPresupuestoPresupuestoDetalleVector.get(presupuesto.getId()) == null){
					presupuestosDetalleDelPresupuesto = new Vector<Object>();
					presupuestosDetalleDelPresupuesto.add(presupuestoDetalle);
					mapaPresupuestoPresupuestoDetalleVector.put(presupuesto.getId(), presupuestosDetalleDelPresupuesto);
				}else{
					presupuestosDetalleDelPresupuesto = mapaPresupuestoPresupuestoDetalleVector.get(presupuesto.getId());
					presupuestosDetalleDelPresupuesto.add(presupuestoDetalle);
					mapaPresupuestoPresupuestoDetalleVector.put(presupuesto.getId(), presupuestosDetalleDelPresupuesto);
				}				
			}
			
			Iterator mapaPresupuestoPresupuestoDetalleVectorIt =  mapaPresupuestoPresupuestoDetalleVector.keySet().iterator();
			while(mapaPresupuestoPresupuestoDetalleVectorIt.hasNext()){
				Long presupuestoId = (Long)mapaPresupuestoPresupuestoDetalleVectorIt.next();
				PresupuestoIf presupuestoIf = mapaPresupuesto.get(presupuestoId);
				Vector<Object> presupuestosDetallePresupuesto = mapaPresupuestoPresupuestoDetalleVector.get(presupuestoId);
				
				//Lleno la información de cada Presupuesto
				cargarInversionClientesDataVectorPresupuestos(presupuestosDetallePresupuesto, presupuestoIf);							
			}				
			
			//seteo el total de valores y cantidades en el arreglo correspondiente
			facturas[12] = totalFacturas;
			cantidadFacturas[12] = totalCantidadFacturas;
			ordenes[12] = totalOrdenes;
			cantidadOrdenes[12] = totalCantidadOrdenes;
			compras[12] = totalCompras;
			cantidadCompras[12] = totalCantidadCompras;
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarInversionClientesDataVectorMedios(
			Vector<Object[]> ordenesMedioDetalleDelPlan, PlanMedioIf planMedio)
			throws GenericBusinessException {
			
		///////////////////////////////////
		//PARA EL PLAN
		//////////////////////////////////
		
		//Sirven para el plan, factura y compra
		String carteraFacturaPlan = "";
		String carteraCompraPlan = "";
		//////////////////////////////////////
		
		String clienteIdPlan = "";
		String clienteOficinaPlan = "";
		String codigoPresupuestoPlan = "";
		String fechaAprobacionPlan = "";
		String mesPlan = "";
		String tipoFacturaPlan = "";
		String carteraPlan = "";
		String fechaFacturaPlan = "";
		String facturaPlan = "";
		String sapPlan = "";
		String segmentoPlan = "";
		String productoPlan = "";
		String medioPlan = "";
		String proveedorPlan = "";
		String valorPlan = "";
		String ivaPlan = "";
		String totalPlan = "";
		
		String inversionPautaValor = "";
		String inversionPautaIva = "";
		String inversionPautaTotal = "";
				
		//clienteId, clienteOficina
		//Todas las ordenes de medios del vector van a tener el mismo cliente oficina
		Long clienteOficinaId = ((OrdenMedioIf)ordenesMedioDetalleDelPlan.get(0)[1]).getClienteOficinaId();
		ClienteOficinaIf clienteOficinaIf = mapaClienteOficina.get(clienteOficinaId);		
		clienteOficinaPlan = clienteOficinaIf.getDescripcion();				
		ClienteIf cliente = mapaCliente.get(clienteOficinaIf.getClienteId());
		clienteIdPlan = cliente.getId().toString();
		
		//codigoPresupuesto
		codigoPresupuestoPlan = planMedio.getCodigo();
		
		//fechaAprobacion
		Calendar calendarInicio = new GregorianCalendar();
		Timestamp date = new Timestamp(calendarInicio.getTimeInMillis());		
		
		if(planMedio.getFechaAprobacion() != null 
				&& !getCmbEstado().getSelectedItem().equals("COTIZADO") 
				&& !getCbReporteInversionTotales().isSelected() 
				&& !getCbReporteComisionAdicional().isSelected()){
			fechaAprobacionPlan = Utilitarios.getFechaCortaUppercase(planMedio.getFechaAprobacion());
			date = planMedio.getFechaAprobacion();
		}else{
			fechaAprobacionPlan = Utilitarios.getFechaCortaUppercase(planMedio.getFechaInicio());
			date = planMedio.getFechaInicio();
		}
		
		//mes del presupuesto
		mesPlan = String.valueOf(planMedio.getFechaInicio().getMonth() + 1);
				
		//sap
		if(planMedio.getAutorizacionSap() != null){
			sapPlan = planMedio.getAutorizacionSap();
		}else{
			sapPlan = "";
		}
		
		//segmento, producto
		Map<Long,Long> campanaProductoVersionIdMapa = new HashMap<Long,Long>();
		//para obtener todos los campaña producto version id sin repetirse.
		for(Object[] ordenMedioDetalleOrdenMedioCabecera : ordenesMedioDetalleDelPlan){
			OrdenMedioDetalleIf ordenMedioDetalle = (OrdenMedioDetalleIf)ordenMedioDetalleOrdenMedioCabecera[0];
			campanaProductoVersionIdMapa.put(ordenMedioDetalle.getCampanaProductoVersionId(), ordenMedioDetalle.getCampanaProductoVersionId());
		}
		
		//lleno mapas de marcas(segmentos) y productos
		Map<Long,ProductoClienteIf> productosClienteMapa = new HashMap<Long,ProductoClienteIf>();
		Map<Long,MarcaProductoIf> marcasClienteMapa = new HashMap<Long,MarcaProductoIf>();
		Iterator<Long> campanaProductoVersionIdMapaIt = campanaProductoVersionIdMapa.keySet().iterator();
		while(campanaProductoVersionIdMapaIt.hasNext()){
			Long campanaProductoVersionId = campanaProductoVersionIdMapaIt.next();
			CampanaProductoVersionIf campanaProductoVersion = mapaCampanaProductoVersion.get(campanaProductoVersionId);
			CampanaProductoIf campanaProducto = mapaCampanaProducto.get(campanaProductoVersion.getCampanaProductoId());
			ProductoClienteIf productoCliente = mapaProductoCliente.get(campanaProducto.getProductoClienteId());

			//if(productoCliente != null){				
				productosClienteMapa.put(productoCliente.getId(), productoCliente);
				MarcaProductoIf marcaProducto = mapaMarcasProducto.get(productoCliente.getMarcaProductoId());
				marcasClienteMapa.put(marcaProducto.getId(), marcaProducto);
			/*}else{
				System.out.println("a");
			}*/
		}
		
		//segmento
		Iterator<Long> marcasClienteMapaIt = marcasClienteMapa.keySet().iterator();
		while(marcasClienteMapaIt.hasNext()){
			Long marcaId = marcasClienteMapaIt.next();
			String marca = marcasClienteMapa.get(marcaId).getNombre();
			if(segmentoPlan.equals("")){
				segmentoPlan = marca;
			}else{
				segmentoPlan = segmentoPlan + "\n" + marca;
			}
		}
		
		//producto
		Iterator<Long> productosClienteMapaIt = productosClienteMapa.keySet().iterator();
		while(productosClienteMapaIt.hasNext()){
			Long productoId = productosClienteMapaIt.next();
			String productoCliente = productosClienteMapa.get(productoId).getNombre();
			if(productoPlan.equals("")){
				productoPlan = productoCliente;
			}else{
				productoPlan = productoPlan + "\n" + productoCliente;
			}
		}
		
		if(planMedio.getCodigo().equals("2014-01967")){
			System.out.println("aqui");
		}
				
		//valor, iva, total
		BigDecimal subtotalPlanMedio = new BigDecimal(0);
		BigDecimal ivaSubTotal = new BigDecimal(0);
		BigDecimal porcentajeDescuentoVentaPlan = new BigDecimal(0); 
		BigDecimal porcentajeComisionAgenciaPlan = new BigDecimal(0); 
		for(Object[] ordenMedioDetalleOrdenMedioCabecera : ordenesMedioDetalleDelPlan){
			OrdenMedioDetalleIf ordenMedioDetalle = (OrdenMedioDetalleIf)ordenMedioDetalleOrdenMedioCabecera[0];
			OrdenMedioIf ordenMedio = (OrdenMedioIf)ordenMedioDetalleOrdenMedioCabecera[1];
			
			subtotalPlanMedio = subtotalPlanMedio.add(ordenMedioDetalle.getValorSubtotal());
			//estos porcentajes son los mismos en todos los detalles
			porcentajeDescuentoVentaPlan = ordenMedio.getPorcentajeDescuentoVenta();
			porcentajeComisionAgenciaPlan = ordenMedio.getPorcentajeComisionAgencia();
		}
		
		//valor	
		BigDecimal descuentoVentaPlan = subtotalPlanMedio.multiply(porcentajeDescuentoVentaPlan.divide(new BigDecimal(100)));
		BigDecimal comisionAgenciaPlan = (subtotalPlanMedio.subtract(descuentoVentaPlan)).multiply(porcentajeComisionAgenciaPlan.divide(new BigDecimal(100)));
		BigDecimal subTotal = subtotalPlanMedio.subtract(descuentoVentaPlan).add(comisionAgenciaPlan);
		valorPlan = formatoDecimal.format(subTotal);
		inversionPautaValor = valorPlan;
		
		//iva
		double ivaActual = Parametros.getIVA() / 100D; 
		ivaSubTotal = subTotal.multiply(BigDecimal.valueOf(ivaActual));
		ivaPlan = formatoDecimal.format(ivaSubTotal);
		inversionPautaIva = ivaPlan;
		
		//total
		BigDecimal totalTotal = subTotal.add(ivaSubTotal);
		totalPlan = formatoDecimal.format(totalTotal);
		inversionPautaTotal = totalPlan;
		
		///////////////////////////////////
		//PARA LAS FACTURAS
		//////////////////////////////////
					
		//facturas
		Vector<FacturaIf> facturasDelPlan = new Vector<FacturaIf>();
		
		Collection planMedioFormasPago = SessionServiceLocator.getPlanMedioFormaPagoSessionService().findPlanMedioFormaPagoByPlanMedioId(planMedio.getId());
		Iterator planMedioFormasPagoIt = planMedioFormasPago.iterator();
		while(planMedioFormasPagoIt.hasNext()){
			PlanMedioFormaPagoIf planMedioFormaPago = (PlanMedioFormaPagoIf)planMedioFormasPagoIt.next();
			Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(planMedioFormaPago.getPedidoId());
			Iterator facturaIt = facturas.iterator();
			while(facturaIt.hasNext()){
				FacturaIf facturaIf = (FacturaIf)facturaIt.next();
				//si no esta anulada
				if(!facturaIf.getEstado().equals("A")){
					//se hace esta validacion porque del presupuesto tambien pueden salir facturas a los proveedores
					if(facturaIf.getClienteoficinaId().compareTo(clienteOficinaIf.getId()) == 0 ||
							getCbVerFacturasNegociacion().isSelected())
						facturasDelPlan.add(facturaIf);
				}						
			}
		}
		
		/*Map mapaPedido = new HashMap();
		mapaPedido.put("tiporeferencia", "I");
		mapaPedido.put("referencia", planMedio.getCodigo());
		Collection pedidos = SessionServiceLocator.getPedidoSessionService().findPedidoByQuery(mapaPedido);
		Iterator pedidosIt = pedidos.iterator();
		while(pedidosIt.hasNext()){
			PedidoIf pedido = (PedidoIf)pedidosIt.next();
			Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId());
			Iterator facturaIt = facturas.iterator();
			while(facturaIt.hasNext()){
				FacturaIf facturaIf = (FacturaIf)facturaIt.next();
				//si no esta anulada
				if(!facturaIf.getEstado().equals("A")){
					//se hace esta validacion porque del presupuesto tambien pueden salir facturas a los proveedores
					if(facturaIf.getClienteoficinaId().compareTo(clienteOficinaIf.getId()) == 0 ||
							getCbVerFacturasNegociacion().isSelected())
						facturasDelPlan.add(facturaIf);
				}						
			}
		}*/
		
		double totalValorFacturasPlan = 0D;
		double totalIvaFacturasPlan = 0D;
		double totalTotalFacturasPlan = 0D;
		boolean facturaUsada = false;
		
		//si existen facturas y se desea verlas
		if(facturasDelPlan.size() > 0 && getCbVerFacturas().isSelected()){
			
			for(FacturaIf facturaDelPlan : facturasDelPlan){
				
				//para saber si ya fue usada
				if(facturaIngresada.get(facturaDelPlan.getId()) == null){
					facturaIngresada.put(facturaDelPlan.getId(), facturaDelPlan.getId());
				}else{
					facturaUsada = true;
				}
				
				String fechaAprobacionFactura = "";
				String tipoFacturaFactura = "";
				String carteraFactura = "";
				String fechaFacturaFactura = "";
				String facturaFactura = "";
				String sapFactura = "";
				String segmentoFactura = "";
				String productoFactura = "";
				String medioFactura = "";
				String proveedorFactura = "";
				String valorFactura = "";
				String ivaFactura = "";
				String totalFactura = "";
								
				//tipo
				TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(facturaDelPlan.getTipodocumentoId());
				if(facturaDelPlan.getClienteoficinaId().compareTo(clienteOficinaIf.getId()) != 0){
					tipoFacturaFactura = "FN";
				}else if(tipoDocumento.getNombre().equals("FACTURA DE REEMBOLSO")){
					tipoFacturaFactura = "FR";
				}else if(tipoDocumento.getNombre().equals("FACTURA DE COMISION")){
					tipoFacturaFactura = "FC";
				}else if(tipoDocumento.getNombre().equals("FACTURA AL EXTERIOR")){
					tipoFacturaFactura = "FE";
				}else{
					tipoFacturaFactura = "F";
				}
				
				//cartera
				boolean tieneRetencion = false;
				double valorRetencion = 0D;
				double valorPago = 0D;
				String detallePago = "NO DETALLE PAGO";
				String fechaPago = "NO PAGO";
				if(getCbVerCarteraIngresos().isSelected()){
					CarteraIf carteraFacturaIf = null;
					Collection carterasIngresoDetalle = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaCarteraIngresoDetalleByTipoCarteraByReferenciaAfectadaIdAndByPreimpreso("C", facturaDelPlan.getId(), facturaDelPlan.getPreimpresoNumero());
					Iterator carterasIngresoDetalleIt = carterasIngresoDetalle.iterator();
					while(carterasIngresoDetalleIt.hasNext()){
						Object[] carteraIngresoDetalle = (Object[])carterasIngresoDetalleIt.next();
						CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf)carteraIngresoDetalle[0];
						CarteraDetalleIf carteraDetalleIngreso = (CarteraDetalleIf)carteraIngresoDetalle[1];
						carteraFacturaIf = (CarteraIf)carteraIngresoDetalle[2];
						
						DocumentoIf documentoIf = mapaDocumento.get(carteraDetalleIngreso.getDocumentoId());
						if(documentoIf.getRetencionIva().equals("S") || documentoIf.getRetencionRenta().equals("S")){
							tieneRetencion = true;
							valorRetencion = valorRetencion + carteraAfectaIf.getValor().doubleValue();
						}else{
							if(fechaPago.equals("NO PAGO")){
								detallePago = carteraDetalleIngreso.getObservacion();
								fechaPago = Utilitarios.getFechaCortaUppercase(carteraAfectaIf.getFechaAplicacion());
							}else{
								detallePago = detallePago + "\n" + carteraDetalleIngreso.getObservacion();
								fechaPago = fechaPago + "\n" + Utilitarios.getFechaCortaUppercase(carteraAfectaIf.getFechaAplicacion());
							}							
						}
						valorPago = valorPago + carteraAfectaIf.getValor().doubleValue();
					}
					
					if(carteraFacturaIf != null && carteraFacturaIf.getSaldo().doubleValue() <= 0.01){
						carteraFactura = "T";
						carteraFacturaPlan = "T";
					}
					else if(tieneRetencion && (valorPago > valorRetencion)){
						carteraFactura = "R/P";
						carteraFacturaPlan = "R/P";
					}
					else if(!tieneRetencion && (valorPago > 0)){
						carteraFactura = "P";
						carteraFacturaPlan = "P";
					}
					else if(tieneRetencion && (valorPago == valorRetencion)){
						carteraFactura = "R";
						carteraFacturaPlan = "R";
					}
				}			
				
				//fechaFactura, factura
				fechaFacturaFactura = Utilitarios.getFechaCortaUppercase(facturaDelPlan.getFechaFactura()); // + "\n" + detallePago;
				facturaFactura = facturaDelPlan.getPreimpresoNumero(); // + "\n" + fechaPago;
				
				//sap
				if(facturaDelPlan.getAutorizacionSap() != null){
					sapFactura = facturaDelPlan.getAutorizacionSap();
				}else if(cliente.getRequiereSap().equals("S")){
					sapFactura = "PENDIENTE";
				}
				
				//medio (tipoMedio), proveedor
				//mapas de proveedores y tipos de medios
				Map<Long,ClienteOficinaIf> proveedorIdMapa = new HashMap<Long,ClienteOficinaIf>();
				Map<Long,TipoProveedorIf> tipoMediosMapa = new HashMap<Long,TipoProveedorIf>();
				Collection facturasDetalle = SessionServiceLocator.getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(facturaDelPlan.getId());
				Iterator facturasDetalleIt = facturasDetalle.iterator();
				while(facturasDetalleIt.hasNext()){
					FacturaDetalleIf facturaDetalle = (FacturaDetalleIf)facturasDetalleIt.next();
					ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(facturaDetalle.getProductoId());
					
					ClienteOficinaIf proveedorOficinaIf = mapaClienteOficina.get(producto.getProveedorId());
					proveedorIdMapa.put(producto.getProveedorId(), proveedorOficinaIf);
					ClienteIf proveedorIf = mapaCliente.get(proveedorOficinaIf.getClienteId());
					TipoProveedorIf tipoMedio = mapaTipoProveedor.get(proveedorIf.getTipoproveedorId());
					tipoMediosMapa.put(tipoMedio.getId(), tipoMedio);
				}							
				
				//medio
				Iterator<Long> tipoMediosMapaIt = tipoMediosMapa.keySet().iterator();
				while(tipoMediosMapaIt.hasNext()){
					Long tipoMedioId = tipoMediosMapaIt.next();
					String tipoMedio = tipoMediosMapa.get(tipoMedioId).getNombre();
					if(medioFactura.equals("")){
						medioFactura = tipoMedio;
					}else{
						medioFactura = medioFactura + "\n" + tipoMedio;
					}
				}
				
				//proveedor
				if(getCbVerProveedoresFactura().isSelected()){
					Iterator<Long> proveedoresMapaIt = proveedorIdMapa.keySet().iterator();
					while(proveedoresMapaIt.hasNext()){
						Long proveedorId = proveedoresMapaIt.next();
						String proveedorNombre = proveedorIdMapa.get(proveedorId).getDescripcion();
						if(proveedorFactura.equals("")){
							proveedorFactura = proveedorNombre;
						}else if(getCbVerFacturas().isSelected()){
							proveedorFactura = proveedorFactura + ",\n" + proveedorNombre;
						}
					}
				}				
				
				//valor, iva, total				
				double valorBruto = facturaDelPlan.getValor().doubleValue();
				double descuento = facturaDelPlan.getDescuento().doubleValue();
				double porcentajeComisionAgencia = facturaDelPlan.getPorcentajeComisionAgencia().doubleValue() / 100;
				double comisionAgencia = (valorBruto - descuento) * porcentajeComisionAgencia;
				double valorNeto = valorBruto - descuento + comisionAgencia;
				double ivaValor = facturaDelPlan.getIva().doubleValue();
				double totalFacturaValor = valorNeto + ivaValor;
				valorFactura = formatoDecimal.format(valorNeto);
				ivaFactura = formatoDecimal.format(ivaValor);
				totalFactura = formatoDecimal.format(totalFacturaValor);
				
				totalValorFacturasPlan = totalValorFacturasPlan + valorNeto;
				totalIvaFacturasPlan = totalIvaFacturasPlan + ivaValor;
				totalTotalFacturasPlan = totalTotalFacturasPlan + totalFacturaValor;
							
				//porque pudo contarse ya previamente en otro plan porque
				//se puede facturar dos o mas planes en la misma factura
				if(!facturaUsada){
					//seteo el total facturado en el mes correspondiente
					if(facturas[date.getMonth()] == null){
						facturas[date.getMonth()] = totalFacturaValor;
					}else{
						facturas[date.getMonth()] = facturas[date.getMonth()] + totalFacturaValor;
					}
					//total de facturas
					totalFacturas = totalFacturas + totalFacturaValor;
					
					//seteo el total de cantidad de facturas en el mes correspondiente
					if(cantidadFacturas[date.getMonth()] == null){
						cantidadFacturas[date.getMonth()] = 1;
					}else{
						cantidadFacturas[date.getMonth()] = cantidadFacturas[date.getMonth()] + 1;
					}
					//total cantidad de facturas
					totalCantidadFacturas = totalCantidadFacturas + 1;
				}						
				
				//LINEA POR CADA FACTURA
				InversionClientesData inversionClientesVectorFactura = new InversionClientesData();
				inversionClientesVectorFactura.setClienteId(clienteIdPlan);
				inversionClientesVectorFactura.setClienteOficina(clienteOficinaPlan);
				inversionClientesVectorFactura.setCodigoPresupuesto(codigoPresupuestoPlan);
				inversionClientesVectorFactura.setTipoFactura(tipoFacturaFactura);
				inversionClientesVectorFactura.setCartera(carteraFactura);
				inversionClientesVectorFactura.setFechaFactura(fechaFacturaFactura);
				inversionClientesVectorFactura.setFactura(facturaFactura);
				inversionClientesVectorFactura.setFechaAprobacion(fechaAprobacionFactura);
				inversionClientesVectorFactura.setSap(sapFactura);
				inversionClientesVectorFactura.setSegmento(segmentoFactura);
				inversionClientesVectorFactura.setProducto(productoFactura);
				inversionClientesVectorFactura.setMedio(medioFactura);
				inversionClientesVectorFactura.setProveedor(proveedorFactura);
				inversionClientesVectorFactura.setValor(valorFactura);
				inversionClientesVectorFactura.setIva(ivaFactura);
				inversionClientesVectorFactura.setTotal(totalFactura);
				inversionClientesVectorFactura.setDate(date);
				inversionClientesVectorFactura.setPosicion("1");
				inversionClientesVectorFactura.setInversionPautaValor("0");
				inversionClientesVectorFactura.setInversionPautaIva("0");
				inversionClientesVectorFactura.setInversionPautaTotal("0");
				
				inversionClientesVectorFactura.setCarteraFactura(carteraFacturaPlan);
				
				inversionClientesDataVector.add(inversionClientesVectorFactura);
			}
		}
		//si es presupuesto PREPAGADO
		else if(planMedio.getEstado().equals("R")){
			facturaPlan = "PREPAGADO";
		}
		//si no existen facuras
		else{
			facturaPlan = "PENDIENTE";
		}
		
		//SI HAY MAS DE 2 FACTURAS SALIDAS DE LA PAUTA SE PONE UN TOTAL FACTURADO
		if(facturasDelPlan.size() > 1 && getCbVerFacturas().isSelected()){
			//LINEA EXTRA POR CADA FACTURA DONDE VA EL TOTAL
			InversionClientesData inversionClientesVectorFactura = new InversionClientesData();
			inversionClientesVectorFactura.setClienteId(clienteIdPlan);
			inversionClientesVectorFactura.setClienteOficina(clienteOficinaPlan);
			inversionClientesVectorFactura.setCodigoPresupuesto(codigoPresupuestoPlan);
			inversionClientesVectorFactura.setTipoFactura("");
			inversionClientesVectorFactura.setFechaFactura("");
			inversionClientesVectorFactura.setFactura("");
			inversionClientesVectorFactura.setFechaAprobacion("");
			inversionClientesVectorFactura.setSap("");
			inversionClientesVectorFactura.setSegmento("");
			inversionClientesVectorFactura.setProducto("");
			inversionClientesVectorFactura.setMedio("");
			inversionClientesVectorFactura.setProveedor("TOTAL FACTURADO: ");
			inversionClientesVectorFactura.setValor(formatoDecimal.format(totalValorFacturasPlan));
			inversionClientesVectorFactura.setIva(formatoDecimal.format(totalIvaFacturasPlan));
			inversionClientesVectorFactura.setTotal(formatoDecimal.format(totalTotalFacturasPlan));
			inversionClientesVectorFactura.setDate(date);
			inversionClientesVectorFactura.setPosicion("1");
			inversionClientesVectorFactura.setInversionPautaValor("0");
			inversionClientesVectorFactura.setInversionPautaIva("0");
			inversionClientesVectorFactura.setInversionPautaTotal("0");
			inversionClientesDataVector.add(inversionClientesVectorFactura);
		}
		
		///////////////////////////////////
		//PARA LAS COMPRAS
		//////////////////////////////////
		
		String tipoMedioParaExcel = "";
						
		//ordenes
		Vector<OrdenMedioIf> ordenesDelPlan = new Vector<OrdenMedioIf>();
		
		//si se desea ver las Ordnes / Compras, lleno el vector con las ordenes del plan
		if(getCbVerCompras().isSelected()){
			Collection ordenes = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planMedio.getId());
			Iterator ordenesIt = ordenes.iterator();
			while(ordenesIt.hasNext()){
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenesIt.next();
				//si no esta anulada
				if(!ordenMedioIf.getEstado().equals("A")){
					ordenesDelPlan.add(ordenMedioIf);					
				}						
			}
		}
		
		
		int cantidadDeOrdenes = 0;
		int cantidadDeCompras = 0;
		double totalValorOrdenesPlan = 0D;
		double totalIvaOrdenesPlan = 0D;
		double totalTotalOrdenesPlan = 0D;
		double totalValorComprasPlan = 0D;
		double totalIvaComprasPlan = 0D;
		double totalTotalComprasPlan = 0D;
		
		//si existen ordenes
		if(ordenesDelPlan.size() > 0){
			
			for(OrdenMedioIf ordenDelPlan : ordenesDelPlan){
				
				String fechaAprobacionOrden = "";
				String tipoFacturaOrden = "";
				String carteraCompra = "";
				String fechaFacturaOrden = "";
				String facturaOrden = "";
				String sapOrden = "";
				String segmentoOrden = "";
				String productoOrden = "";
				String medioOrden = "";
				String proveedorOrden = "";
				String valorOrden = "";
				String ivaOrden = "";
				String totalOrden = "";
				
				//tipo
				Map buscarCompra = new HashMap();
				buscarCompra.put("tipoOrden", "OM");
				buscarCompra.put("ordenId", ordenDelPlan.getId());
				ArrayList ordenesAsociadas = (ArrayList)SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByQuery(buscarCompra);
				
				CompraIf compra = null;
				if(ordenesAsociadas.size() > 0){
					OrdenAsociadaIf ordenAsociada = (OrdenAsociadaIf)ordenesAsociadas.get(0);
					compra = SessionServiceLocator.getCompraSessionService().getCompra(ordenAsociada.getCompraId());
				}
				
				if(compra != null){
					tipoFacturaOrden = "CO";
				}else{
					tipoFacturaOrden = "OR";
				}
				
				//cartera
				boolean tieneRetencion = false;
				double valorRetencion = 0D;
				double valorPago = 0D;
				if(compra != null && getCbVerCarteraEgresos().isSelected()){
					CarteraIf carteraFacturaIf = null;
					Collection carterasIngresoDetalle = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaCarteraIngresoDetalleByTipoCarteraByReferenciaAfectadaIdAndByPreimpreso("P", compra.getId(), compra.getPreimpreso());
					Iterator carterasIngresoDetalleIt = carterasIngresoDetalle.iterator();
					while(carterasIngresoDetalleIt.hasNext()){
						Object[] carteraIngresoDetalle = (Object[])carterasIngresoDetalleIt.next();
						CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf)carteraIngresoDetalle[0];
						CarteraDetalleIf carteraDetalleIngreso = (CarteraDetalleIf)carteraIngresoDetalle[1];
						carteraFacturaIf = (CarteraIf)carteraIngresoDetalle[2];
						
						DocumentoIf documentoIf = mapaDocumento.get(carteraDetalleIngreso.getDocumentoId());
						if(documentoIf.getRetencionIva().equals("S") || documentoIf.getRetencionRenta().equals("S")){
							tieneRetencion = true;
							valorRetencion = valorRetencion + carteraAfectaIf.getValor().doubleValue();
						}
						valorPago = valorPago + carteraAfectaIf.getValor().doubleValue();
					}
					
					if(carteraFacturaIf != null && carteraFacturaIf.getSaldo().doubleValue() <= 0.01){
						carteraCompra = "T";
						carteraCompraPlan = "T";
					}
					else if(tieneRetencion && (valorPago > valorRetencion)){
						carteraCompra = "R/P";
						carteraCompraPlan = "R/P";
					}
					else if(!tieneRetencion && (valorPago > 0)){
						carteraCompra = "P";
						carteraCompraPlan = "P";
					}
					else if(tieneRetencion && (valorPago == valorRetencion)){
						carteraCompra = "R";
						carteraCompraPlan = "R";
					}
				}
				
				//facturas del cliente relacionadas a la orden
				/*String preimpresoFacturaCliente = "";
				Collection planesMedioFacturacion = SessionServiceLocator.getPlanMedioFacturacionSessionService().findPlanMedioFacturacionByOrdenMedioId(ordenDelPlan.getId());
				Iterator planesMedioFacturacionIt = planesMedioFacturacion.iterator();
				while(planesMedioFacturacionIt.hasNext()){
					PlanMedioFacturacionIf planMedioFacturacionIf = (PlanMedioFacturacionIf)planesMedioFacturacionIt.next();
					if(planMedioFacturacionIf.getEstado().equals("F")){
						Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(planMedioFacturacionIf.getPedidoId());
						Iterator facturasIt = facturas.iterator();
						while(facturasIt.hasNext()){
							FacturaIf facturaIf = (FacturaIf)facturasIt.next();
							if(facturaIf.getEstado().equals("C")){
								preimpresoFacturaCliente = facturaIf.getPreimpresoNumero();
							}
						}
					}					
				}*/
				
				//fechaFactura, factura
				if(compra != null){
					fechaFacturaOrden = Utilitarios.getFechaCortaUppercase(compra.getFecha());
					facturaOrden = compra.getPreimpreso(); // + "\n" + preimpresoFacturaCliente;
				}else{
					fechaFacturaOrden = Utilitarios.getFechaCortaUppercase(ordenDelPlan.getFechaOrden());
					facturaOrden = ordenDelPlan.getCodigo(); // + "\n" + preimpresoFacturaCliente;
				}
				
				//sap NO APLICA
				
				//medio, proveedor
				//mapas de proveedores y tipos de medios
				Map<Long,ClienteOficinaIf> proveedorIdMapa = new HashMap<Long,ClienteOficinaIf>();
				Map<Long,TipoProveedorIf> tipoMediosMapa = new HashMap<Long,TipoProveedorIf>();
				if(compra != null){
					Collection comprasDetalle = SessionServiceLocator.getCompraDetalleSessionService().findCompraDetalleByCompraId(compra.getId());
					Iterator comprasDetalleIt = comprasDetalle.iterator();
					while(comprasDetalleIt.hasNext()){
						CompraDetalleIf compraDetalle = (CompraDetalleIf)comprasDetalleIt.next();
						ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(compraDetalle.getProductoId());
						
						ClienteOficinaIf proveedorOficinaIf = mapaClienteOficina.get(producto.getProveedorId());
						proveedorIdMapa.put(producto.getProveedorId(), proveedorOficinaIf);
						ClienteIf proveedorIf = mapaCliente.get(proveedorOficinaIf.getClienteId());
						TipoProveedorIf tipoMedio = mapaTipoProveedor.get(proveedorIf.getTipoproveedorId());
						tipoMediosMapa.put(tipoMedio.getId(), tipoMedio);
					}	
				}else{
					Collection ordenesDetalle = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenDelPlan.getId());
					Iterator ordenesDetalleIt = ordenesDetalle.iterator();
					while(ordenesDetalleIt.hasNext()){
						OrdenMedioDetalleIf ordenMedioDetalle = (OrdenMedioDetalleIf)ordenesDetalleIt.next();
						ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioDetalle.getProductoProveedorId());
						
						ClienteOficinaIf proveedorOficinaIf = mapaClienteOficina.get(producto.getProveedorId());
						proveedorIdMapa.put(producto.getProveedorId(), proveedorOficinaIf);
						ClienteIf proveedorIf = mapaCliente.get(proveedorOficinaIf.getClienteId());
						TipoProveedorIf tipoMedio = mapaTipoProveedor.get(proveedorIf.getTipoproveedorId());
						tipoMediosMapa.put(tipoMedio.getId(), tipoMedio);
					}	
				}				
				
				//medio
				Iterator<Long> tipoMediosMapaIt = tipoMediosMapa.keySet().iterator();
				while(tipoMediosMapaIt.hasNext()){
					Long tipoMedioId = tipoMediosMapaIt.next();
					String tipoMedio = tipoMediosMapa.get(tipoMedioId).getNombre();
					if(medioOrden.equals("")){
						medioOrden = tipoMedio;
					}else{
						medioOrden = medioOrden + "\n" + tipoMedio;
					}
				}
				
				tipoMedioParaExcel = medioOrden;
				
				//proveedor
				Iterator<Long> proveedoresMapaIt = proveedorIdMapa.keySet().iterator();
				while(proveedoresMapaIt.hasNext()){
					Long proveedorId = proveedoresMapaIt.next();
					String proveedorNombre = proveedorIdMapa.get(proveedorId).getDescripcion();
					if(proveedorOrden.equals("")){
						proveedorOrden = proveedorNombre;
					}else if(getCbVerFacturas().isSelected()){
						proveedorOrden = proveedorOrden + ",\n" + proveedorNombre;
					}
				}
				
				//valor, iva, total
				double valorBruto = ordenDelPlan.getValorSubtotal().doubleValue();
				double descuento = ordenDelPlan.getValorDescuento().doubleValue();
				double valorNeto = valorBruto - descuento;
				double ivaValor = ordenDelPlan.getValorIva().doubleValue();
				double totalOrdenValor = valorNeto + ivaValor;
				valorOrden = formatoDecimal.format(valorNeto);
				ivaOrden = formatoDecimal.format(ivaValor);
				totalOrden = formatoDecimal.format(totalOrdenValor);
				
				//total de ordenes
				if(ordenesDelPlan.size() > 0){
					totalValorOrdenesPlan = totalValorOrdenesPlan + valorNeto;
					totalIvaOrdenesPlan = totalIvaOrdenesPlan + ivaValor;
					totalTotalOrdenesPlan = totalTotalOrdenesPlan + totalOrdenValor;
					cantidadDeOrdenes = cantidadDeOrdenes + 1;
					
					//seteo el total de ordenes en el mes correspondiente
					if(ordenes[date.getMonth()] == null){
						ordenes[date.getMonth()] = totalOrdenValor;
					}else{
						ordenes[date.getMonth()] = ordenes[date.getMonth()] + totalOrdenValor;
					}
					//total de ordenes
					totalOrdenes = totalOrdenes + totalOrdenValor;
					
					//seteo el total de cantidad de ordenes en el mes correspondiente
					if(cantidadOrdenes[date.getMonth()] == null){
						cantidadOrdenes[date.getMonth()] = 1;
					}else{
						cantidadOrdenes[date.getMonth()] = cantidadOrdenes[date.getMonth()] + 1;
					}
					//total cantidad de ordenes
					totalCantidadOrdenes = totalCantidadOrdenes + 1;
				}
				
				//total solo de compras
				if(compra != null){
					totalValorComprasPlan = totalValorComprasPlan + valorNeto;
					totalIvaComprasPlan = totalIvaComprasPlan + ivaValor;
					totalTotalComprasPlan = totalTotalComprasPlan + totalOrdenValor;
					cantidadDeCompras = cantidadDeCompras + 1;
					
					//seteo el total de compras en el mes correspondiente
					if(compras[date.getMonth()] == null){
						compras[date.getMonth()] = totalOrdenValor;
					}else{
						compras[date.getMonth()] = compras[date.getMonth()] + totalOrdenValor;
					}
					//total de compras
					totalCompras = totalCompras + totalOrdenValor;
					
					//seteo el total de cantidad de compras en el mes correspondiente
					if(cantidadCompras[date.getMonth()] == null){
						cantidadCompras[date.getMonth()] = 1;
					}else{
						cantidadCompras[date.getMonth()] = cantidadCompras[date.getMonth()] + 1;
					}
					//total cantidad de ordenes
					totalCantidadCompras = totalCantidadCompras + 1;
				}		
								
				//LINEA POR CADA ORDEN
				InversionClientesData inversionClientesVectorFactura = new InversionClientesData();
				inversionClientesVectorFactura.setClienteId(clienteIdPlan);
				inversionClientesVectorFactura.setClienteOficina(clienteOficinaPlan);
				inversionClientesVectorFactura.setCodigoPresupuesto(codigoPresupuestoPlan);
				inversionClientesVectorFactura.setTipoFactura(tipoFacturaOrden);
				inversionClientesVectorFactura.setCartera(carteraCompra);
				inversionClientesVectorFactura.setFechaFactura(fechaFacturaOrden);
				inversionClientesVectorFactura.setFactura(facturaOrden);
				inversionClientesVectorFactura.setFechaAprobacion(fechaAprobacionOrden);
				inversionClientesVectorFactura.setSap(sapOrden);
				inversionClientesVectorFactura.setSegmento(segmentoOrden);
				inversionClientesVectorFactura.setProducto(productoOrden);
				inversionClientesVectorFactura.setMedio(medioOrden);
				inversionClientesVectorFactura.setProveedor(proveedorOrden);
				inversionClientesVectorFactura.setValor(valorOrden);
				inversionClientesVectorFactura.setIva(ivaOrden);
				inversionClientesVectorFactura.setTotal(totalOrden);
				inversionClientesVectorFactura.setDate(date);
				inversionClientesVectorFactura.setPosicion("2");
				inversionClientesVectorFactura.setInversionPautaValor("0");
				inversionClientesVectorFactura.setInversionPautaIva("0");
				inversionClientesVectorFactura.setInversionPautaTotal("0");
				
				inversionClientesVectorFactura.setCarteraCompra(carteraCompraPlan);
				
				inversionClientesDataVector.add(inversionClientesVectorFactura);
				
				
				///////////////////////////////////////////
				//PARA EL ARCHIVO DE COMISIONES ADICIONALES
				///////////////////////////////////////////

				if(getCbReporteComisionAdicional().isSelected() 
						&& ordenDelPlan.getPorcentajeComisionAdicional() != null 
						&& ordenDelPlan.getPorcentajeComisionAdicional().compareTo(new BigDecimal(0)) == 1){
					ComisionesAdicionalesData comisionAdicionalVector = new ComisionesAdicionalesData();

					comisionAdicionalVector.setTipoMedio(tipoMedioParaExcel);
					comisionAdicionalVector.setProveedorOficina(proveedorOrden);
					comisionAdicionalVector.setFechaCompra(fechaFacturaOrden);
					comisionAdicionalVector.setPreimpresoCompra(facturaOrden);
					comisionAdicionalVector.setCodigoPresupuesto(codigoPresupuestoPlan);
					comisionAdicionalVector.setClienteOficina(clienteOficinaPlan);
					
					//busco si este presupuesto tiene relacionado un presupuesto para cobrar comision adicional
					PresupuestoIf presupuestoAdicionalIf = null;
					Map aMap = new HashMap();
					aMap.put("tipoReferencia", "M");
					aMap.put("referenciaId", planMedio.getId());
					Collection presupuestosAdicionales = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByQuery(aMap, Parametros.getIdEmpresa());
					Iterator presupuestosAdicionalesIt = presupuestosAdicionales.iterator();
					while(presupuestosAdicionalesIt.hasNext()){
						PresupuestoIf presupuestoAdicionalTemp = (PresupuestoIf)presupuestosAdicionalesIt.next();
						//reviso si en el presupuesto adicional/hijo el cliente es proveedor que tiene la comision adicional en el presupuesto madre
						//importante porque un presupuesto madre puede tener varios presupuestos hijos
						if (presupuestoAdicionalTemp.getClienteOficinaId().compareTo(ordenDelPlan.getProveedorId()) == 0) {
							presupuestoAdicionalIf = presupuestoAdicionalTemp;
						}								
					}
										
					if(presupuestoAdicionalIf != null){
						comisionAdicionalVector.setCodigoPresupuestoProveedor(presupuestoAdicionalIf.getCodigo());
						comisionAdicionalVector.setValorPresupuestoProveedor(formatoDecimal.format(presupuestoAdicionalIf.getValorbruto()));
						comisionAdicionalVector.setEstadoPresupuestoProveedor(EstadoPresupuesto.getEstadoPresupuestoByLetra(presupuestoAdicionalIf.getEstado()).toString());
					}
					
					comisionAdicionalVector.setInversionPresupuesto(formatoDecimal.format(valorBruto));
					
					String porcentajeComisionAdicional = "0.00";
					String valorComisionAdicional = "0.00";
					if(ordenDelPlan.getPorcentajeComisionAdicional() != null){
						porcentajeComisionAdicional = formatoDecimal.format(ordenDelPlan.getPorcentajeComisionAdicional());
						double comisionAdicional = valorBruto * (ordenDelPlan.getPorcentajeComisionAdicional().doubleValue() / 100D);
						valorComisionAdicional = formatoDecimal.format(comisionAdicional);
					}
					comisionAdicionalVector.setPorcentajeComisionAdicional(porcentajeComisionAdicional);				
					comisionAdicionalVector.setValorComisionAdicional(valorComisionAdicional);	

					comisionAdicionalVector.setDate(date);

					comisionesAdicionalesDataVector.add(comisionAdicionalVector);
				}				
			}
		}
		
		//SI HAY MAS DE 2 ORDENES SALIDAS DE LA PAUTA SE PONE UN TOTAL DE ORDENES
		if(cantidadDeOrdenes > 1){
			//LINEA EXTRA POR CADA FACTURA DONDE VA EL TOTAL
			InversionClientesData inversionClientesVectorFactura = new InversionClientesData();
			inversionClientesVectorFactura.setClienteId(clienteIdPlan);
			inversionClientesVectorFactura.setClienteOficina(clienteOficinaPlan);
			inversionClientesVectorFactura.setCodigoPresupuesto(codigoPresupuestoPlan);
			inversionClientesVectorFactura.setTipoFactura("");
			inversionClientesVectorFactura.setFechaFactura("");
			inversionClientesVectorFactura.setFactura("");
			inversionClientesVectorFactura.setFechaAprobacion("");
			inversionClientesVectorFactura.setSap("");
			inversionClientesVectorFactura.setSegmento("");
			inversionClientesVectorFactura.setProducto("");
			inversionClientesVectorFactura.setMedio("");
			inversionClientesVectorFactura.setProveedor("TOTAL ORDENES: ");
			inversionClientesVectorFactura.setValor(formatoDecimal.format(totalValorOrdenesPlan));
			inversionClientesVectorFactura.setIva(formatoDecimal.format(totalIvaOrdenesPlan));
			inversionClientesVectorFactura.setTotal(formatoDecimal.format(totalTotalOrdenesPlan));
			inversionClientesVectorFactura.setDate(date);
			inversionClientesVectorFactura.setPosicion("2");
			inversionClientesVectorFactura.setInversionPautaValor("0");
			inversionClientesVectorFactura.setInversionPautaIva("0");
			inversionClientesVectorFactura.setInversionPautaTotal("0");
			inversionClientesDataVector.add(inversionClientesVectorFactura);
		}
		
		//SI HAY MAS DE 2 COMPRAS SALIDAS DE LA PAUTA SE PONE UN TOTAL DE COMPRAS
		if(cantidadDeCompras > 1){
			//LINEA EXTRA POR CADA FACTURA DONDE VA EL TOTAL
			InversionClientesData inversionClientesVectorFactura = new InversionClientesData();
			inversionClientesVectorFactura.setClienteId(clienteIdPlan);
			inversionClientesVectorFactura.setClienteOficina(clienteOficinaPlan);
			inversionClientesVectorFactura.setCodigoPresupuesto(codigoPresupuestoPlan);
			inversionClientesVectorFactura.setTipoFactura("");
			inversionClientesVectorFactura.setFechaFactura("");
			inversionClientesVectorFactura.setFactura("");
			inversionClientesVectorFactura.setFechaAprobacion("");
			inversionClientesVectorFactura.setSap("");
			inversionClientesVectorFactura.setSegmento("");
			inversionClientesVectorFactura.setProducto("");
			inversionClientesVectorFactura.setMedio("");
			inversionClientesVectorFactura.setProveedor("TOTAL COMPRAS: ");
			inversionClientesVectorFactura.setValor(formatoDecimal.format(totalValorComprasPlan));
			inversionClientesVectorFactura.setIva(formatoDecimal.format(totalIvaComprasPlan));
			inversionClientesVectorFactura.setTotal(formatoDecimal.format(totalTotalComprasPlan));
			inversionClientesVectorFactura.setDate(date);
			inversionClientesVectorFactura.setPosicion("2");
			inversionClientesVectorFactura.setInversionPautaValor("0");
			inversionClientesVectorFactura.setInversionPautaIva("0");
			inversionClientesVectorFactura.setInversionPautaTotal("0");
			inversionClientesDataVector.add(inversionClientesVectorFactura);
		}
		
		//LINEA DEL PLAN
		InversionClientesData inversionClientesVectorPlan = new InversionClientesData();
		inversionClientesVectorPlan.setClienteId(clienteIdPlan);
		inversionClientesVectorPlan.setClienteOficina(clienteOficinaPlan);
		inversionClientesVectorPlan.setCodigoPresupuesto(codigoPresupuestoPlan);
		inversionClientesVectorPlan.setTipoFactura(tipoFacturaPlan);
		inversionClientesVectorPlan.setCartera(carteraPlan);
		inversionClientesVectorPlan.setFechaFactura(fechaFacturaPlan);
		inversionClientesVectorPlan.setFactura(facturaPlan);
		inversionClientesVectorPlan.setFechaAprobacion(fechaAprobacionPlan);
		inversionClientesVectorPlan.setMesPresupuesto(mesPlan);
		inversionClientesVectorPlan.setSap(sapPlan);
		inversionClientesVectorPlan.setSegmento(segmentoPlan);
		inversionClientesVectorPlan.setProducto(productoPlan);
		inversionClientesVectorPlan.setMedio(medioPlan);
		inversionClientesVectorPlan.setProveedor(proveedorPlan);
		inversionClientesVectorPlan.setValor(valorPlan);
		inversionClientesVectorPlan.setIva(ivaPlan);
		inversionClientesVectorPlan.setTotal(totalPlan);
		inversionClientesVectorPlan.setDate(date);
		inversionClientesVectorPlan.setPosicion("0");
		inversionClientesVectorPlan.setInversionPautaValor(inversionPautaValor);
		inversionClientesVectorPlan.setInversionPautaIva(inversionPautaIva);
		inversionClientesVectorPlan.setInversionPautaTotal(inversionPautaTotal);
		
		inversionClientesVectorPlan.setCarteraFactura(carteraFacturaPlan);
		inversionClientesVectorPlan.setCarteraCompra(carteraCompraPlan);
		
		inversionClientesDataVector.add(inversionClientesVectorPlan);
		
		//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
		String estadoPlan = planMedio.getEstado();
				
		setearTotalesMediosMes(date, totalTotal, estadoPlan);
		
		//////////////////////////////////////////
		//PARA EL ARCHIVO DE TOTALES DE INVERSION
		//////////////////////////////////////////
		
		InversionTotalesClientesData inversionTotalClientesVectorFactura = new InversionTotalesClientesData();
		
		inversionTotalClientesVectorFactura.setClienteId(clienteIdPlan);
		inversionTotalClientesVectorFactura.setClienteOficina(clienteOficinaPlan);
		inversionTotalClientesVectorFactura.setMesPresupuesto(mesPlan);
		inversionTotalClientesVectorFactura.setFechaPresupuesto(fechaAprobacionPlan);
		inversionTotalClientesVectorFactura.setDate(date);
		inversionTotalClientesVectorFactura.setCodigoPresupuesto(codigoPresupuestoPlan);
		inversionTotalClientesVectorFactura.setTipoMedio(tipoMedioParaExcel);
		inversionTotalClientesVectorFactura.setSubtotalPresupuesto(inversionPautaValor);
		inversionTotalClientesVectorFactura.setSubtotalOrdenes(formatoDecimal.format(totalValorOrdenesPlan));
		
		inversionTotalClientesDataVector.add(inversionTotalClientesVectorFactura);
	}

	private void cargarInversionClientesDataVectorPresupuestos(
			Vector<Object> presupuestosDetalleDelPresupuesto, PresupuestoIf presupuestoIf)
			throws GenericBusinessException {
		
		///////////////////////////////////
		//PARA EL PRESUPUESTO
		//////////////////////////////////
		
		//Sirven para el plan, factura y compra
		String carteraFacturaPresupuesto = "";
		String carteraCompraPresupuesto = "";
		//////////////////////////////////////
		
		String clienteIdPresupuesto = "";
		String clienteOficinaPresupuesto = "";
		String codigoPresupuestoPresupuesto = "";
		String fechaAprobacionPresupuesto = "";
		String tipoFacturaPresupuesto = "";
		String fechaFacturaPresupuesto = "";
		String facturaPresupuesto = "";
		String mesPresupuesto = "";
		String sapPresupuesto = "";
		String segmentoPresupuesto = "";
		String productoPresupuesto = "";
		String medioPresupuesto = "";
		String proveedorPresupuesto = "";
		String valorPresupuesto = "";
		String ivaPresupuesto = "";
		String totalPresupuesto = "";
		
		String inversionPresupuestoValor = "";
		String inversionPresupuestoIva = "";
		String inversionPresupuestoTotal = "";
			
		//clienteId, clienteOficina
		//En la posicion 1 del arreglo esta el cliente oficina Id
		Long clienteOficinaId = (Long)((Object[])presupuestosDetalleDelPresupuesto.get(0))[1];
		ClienteOficinaIf clienteOficinaIf = mapaClienteOficina.get(clienteOficinaId);
		clienteOficinaPresupuesto = clienteOficinaIf.getDescripcion();
		ClienteIf cliente = mapaCliente.get(clienteOficinaIf.getClienteId());
		clienteIdPresupuesto = cliente.getId().toString();
		
		//codigoPresupuesto
		codigoPresupuestoPresupuesto = presupuestoIf.getCodigo();
				
		//fechaAprobacion
		Calendar calendarInicio = new GregorianCalendar();
		Timestamp date = new Timestamp(calendarInicio.getTimeInMillis());		
		
		if(presupuestoIf.getFechaAceptacion() != null 
				&& !getCmbEstado().getSelectedItem().equals("COTIZADO") 
				&& !getCbReporteInversionTotales().isSelected() 
				&& !getCbReporteComisionAdicional().isSelected()){
			fechaAprobacionPresupuesto = Utilitarios.getFechaCortaUppercase(presupuestoIf.getFechaAceptacion());
			date = presupuestoIf.getFechaAceptacion();
			
		}else{
			fechaAprobacionPresupuesto = Utilitarios.getFechaCortaUppercase(presupuestoIf.getFecha());
			date = presupuestoIf.getFecha();			
		}
		
		//mes del presupuesto
		mesPresupuesto = String.valueOf(presupuestoIf.getFecha().getMonth() + 1);			
		
		//sap
		if(presupuestoIf.getAutorizacionSap() != null){
			sapPresupuesto = presupuestoIf.getAutorizacionSap();
		}else{
			sapPresupuesto = "";
		}
		
		//segmento, producto
		Map<Long,String> marcaMapa = new HashMap<Long,String>();
		Map<Long,String> productoMapa = new HashMap<Long,String>();
		
		for(int i = 0; i < presupuestosDetalleDelPresupuesto.size(); i++){
			Object[] presupuestoDetalle = (Object[])presupuestosDetalleDelPresupuesto.get(i);
		
			Long marcaId = (Long)presupuestoDetalle[14];
			Long productoClienteId = (Long)presupuestoDetalle[5];			
			String marca = (String)presupuestoDetalle[13];
			String productoCliente = (String)presupuestoDetalle[4];
								
			marcaMapa.put(marcaId, marca);
			productoMapa.put(productoClienteId, productoCliente);							
		}
		
		//segmento
		Iterator marcaMapaIt = marcaMapa.keySet().iterator();
		while(marcaMapaIt.hasNext()){
			Long marcaId = (Long)marcaMapaIt.next();
			if(segmentoPresupuesto.equals("")){
				segmentoPresupuesto = marcaMapa.get(marcaId);
			}else{
				segmentoPresupuesto = segmentoPresupuesto + "\n" + marcaMapa.get(marcaId);
			}
		}
		
		//producto
		Iterator productoMapaIt = productoMapa.keySet().iterator();
		while(productoMapaIt.hasNext()){
			Long productoId = (Long)productoMapaIt.next();
			if(productoPresupuesto.equals("")){
				productoPresupuesto = productoMapa.get(productoId);
			}else{
				productoPresupuesto = productoPresupuesto + "\n" + productoMapa.get(productoId);
			}
		}
		
		//valor, iva, total
		BigDecimal subtotal = presupuestoIf.getValorbruto();
		BigDecimal descuentoVenta = presupuestoIf.getDescuento();
		BigDecimal porcentajeComisionAgenciaPresupuesto = presupuestoIf.getPorcentajeComisionAgencia();
		BigDecimal descuentosVariosPresupuesto = presupuestoIf.getDescuentosVarios();
		BigDecimal descuentoEspecialPresupuesto = presupuestoIf.getDescuentoEspecial();
		BigDecimal subtotal2 = subtotal.subtract(descuentoEspecialPresupuesto).subtract(descuentoVenta).subtract(descuentosVariosPresupuesto);
		BigDecimal comisionAgenciaPresupuesto = subtotal2.multiply(porcentajeComisionAgenciaPresupuesto.divide(new BigDecimal(100)));
		BigDecimal subTotal = subtotal.subtract(descuentoEspecialPresupuesto).subtract(descuentoVenta).subtract(descuentosVariosPresupuesto).add(comisionAgenciaPresupuesto);
		
		valorPresupuesto = formatoDecimal.format(subTotal);
		inversionPresupuestoValor = valorPresupuesto;
		ivaPresupuesto = formatoDecimal.format(presupuestoIf.getIva());
		inversionPresupuestoIva = ivaPresupuesto;		
		totalPresupuesto = formatoDecimal.format(presupuestoIf.getValor());
		inversionPresupuestoTotal = totalPresupuesto;
		
		
		///////////////////////////////////
		//PARA LAS FACTURAS
		//////////////////////////////////
						
		//facturas
		Vector<FacturaIf> facturasDelPresupuesto = new Vector<FacturaIf>();
		Map<Long,FacturaIf> facturasMap = new HashMap<Long,FacturaIf>();
				
		//UTILIZO DOS BUSQUEDAS PARA QUE NINGUNA FACTURA SE ME QUEDE AFUERA
		//para facturas que vienen de facturar presupuestos uno por uno
		Map mapaPedido = new HashMap();
		mapaPedido.put("tiporeferencia", "P");
		mapaPedido.put("referencia", presupuestoIf.getCodigo());
		Collection pedidos = SessionServiceLocator.getPedidoSessionService().findPedidoByQuery(mapaPedido);
		Iterator pedidosIt = pedidos.iterator();
		while(pedidosIt.hasNext()){
			PedidoIf pedido = (PedidoIf)pedidosIt.next();
			Collection facturas = SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(pedido.getId());
			Iterator facturaIt = facturas.iterator();
			while(facturaIt.hasNext()){
				FacturaIf facturaIf = (FacturaIf)facturaIt.next();
				facturasMap.put(facturaIf.getId(), facturaIf);		
			}
		}
		
		//para facturas que vienen de facturar presupuestos de caso COMBINADO
		Collection presupuestosDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(presupuestoIf.getId());
		Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
		while(presupuestosDetalleIt.hasNext()){
			PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf)presupuestosDetalleIt.next();
			Collection presupuestosFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByPresupuestoDetalleId(presupuestoDetalleIf.getId());
			Iterator presupuestosFacturacionIt = presupuestosFacturacion.iterator();
			while(presupuestosFacturacionIt.hasNext()){
				PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosFacturacionIt.next();
				if(!presupuestoFacturacionIf.getEstado().equals("A")){
					FacturaIf facturaIf = SessionServiceLocator.getFacturaSessionService().getFactura(presupuestoFacturacionIf.getFacturaId());
					if(facturaIf != null){
						facturasMap.put(facturaIf.getId(), facturaIf);
					}else{
						System.out.println("ERROR NO HAY FACTURA");
					}					
				}				
			}
		}
		
		//lleno el vector de facturas
		Iterator facturasMapIt = facturasMap.keySet().iterator();
		while(facturasMapIt.hasNext()){
			Long facturaId = (Long)facturasMapIt.next();
			FacturaIf facturaIf = facturasMap.get(facturaId);
			//si no esta anulada
			if(!facturaIf.getEstado().equals("A")){
				//se hace esta validacion porque del presupuesto tambien pueden salir facturas a los proveedores
				if(facturaIf.getClienteoficinaId().compareTo(clienteOficinaIf.getId()) == 0 ||
						getCbVerFacturasNegociacion().isSelected())
					facturasDelPresupuesto.add(facturaIf);
			}	
		}		
		
		double totalValorFacturasPresupuesto = 0D;
		double totalIvaFacturasPresupuesto = 0D;
		double totalTotalFacturasPresupuesto = 0D;		
		boolean facturaUsada = false;
		
		//si existen facturas y se desea verlas
		if(facturasDelPresupuesto.size() > 0 && getCbVerFacturas().isSelected()){
			
			for(FacturaIf facturaDelPresupuesto : facturasDelPresupuesto){
				
				//para saber si ya fue usada
				if(facturaIngresada.get(facturaDelPresupuesto.getId()) == null){
					facturaIngresada.put(facturaDelPresupuesto.getId(), facturaDelPresupuesto.getId());
				}else{
					facturaUsada = true;
				}
				
				String fechaAprobacionFactura = "";
				String tipoFacturaFactura = "";
				String carteraFactura = "";
				String fechaFacturaFactura = "";
				String facturaFactura = "";
				String sapFactura = "";
				String segmentoFactura = "";
				String productoFactura = "";
				String medioFactura = "";
				String proveedorFactura = "";
				String valorFactura = "";
				String ivaFactura = "";
				String totalFactura = "";
				
				//tipo
				TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(facturaDelPresupuesto.getTipodocumentoId());
				if(facturaDelPresupuesto.getClienteoficinaId().compareTo(clienteOficinaIf.getId()) != 0){
					tipoFacturaFactura = "FN";
				}else if(tipoDocumento.getNombre().equals("FACTURA DE REEMBOLSO")){
					tipoFacturaFactura = "FR";
				}else if(tipoDocumento.getNombre().equals("FACTURA DE COMISION")){
					tipoFacturaFactura = "FC";
				}else if(tipoDocumento.getNombre().equals("FACTURA AL EXTERIOR")){
					tipoFacturaFactura = "FE";
				}else{
					tipoFacturaFactura = "F";
				}
				
				//cartera
				boolean tieneRetencion = false;
				double valorRetencion = 0D;
				double valorPago = 0D;
				String fechaPago = "NO PAGO";
				String detallePago = "NO DETALLE PAGO";
				if(getCbVerCarteraIngresos().isSelected()){
					CarteraIf carteraFacturaIf = null;
					Collection carterasIngresoDetalle = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaCarteraIngresoDetalleByTipoCarteraByReferenciaAfectadaIdAndByPreimpreso("C", facturaDelPresupuesto.getId(), facturaDelPresupuesto.getPreimpresoNumero());
					Iterator carterasIngresoDetalleIt = carterasIngresoDetalle.iterator();
					while(carterasIngresoDetalleIt.hasNext()){
						Object[] carteraIngresoDetalle = (Object[])carterasIngresoDetalleIt.next();
						CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf)carteraIngresoDetalle[0];
						CarteraDetalleIf carteraDetalleIngreso = (CarteraDetalleIf)carteraIngresoDetalle[1];
						carteraFacturaIf = (CarteraIf)carteraIngresoDetalle[2];
						
						DocumentoIf documentoIf = mapaDocumento.get(carteraDetalleIngreso.getDocumentoId());
						if(documentoIf.getRetencionIva().equals("S") || documentoIf.getRetencionRenta().equals("S")){
							tieneRetencion = true;
							valorRetencion = valorRetencion + carteraAfectaIf.getValor().doubleValue();
						}else{
							if(fechaPago.equals("NO PAGO")){
								detallePago = carteraDetalleIngreso.getObservacion();
								fechaPago = Utilitarios.getFechaCortaUppercase(carteraAfectaIf.getFechaAplicacion());
							}else{
								detallePago = detallePago + "\n" + carteraDetalleIngreso.getObservacion();
								fechaPago = fechaPago + "\n" + Utilitarios.getFechaCortaUppercase(carteraAfectaIf.getFechaAplicacion());
							}							
						}
						valorPago = valorPago + carteraAfectaIf.getValor().doubleValue();
					}
					
					if(carteraFacturaIf != null && carteraFacturaIf.getSaldo().doubleValue() <= 0.01){
						carteraFactura = "T";
						carteraFacturaPresupuesto = "T";
					}
					else if(tieneRetencion && (valorPago > valorRetencion)){
						carteraFactura = "R/P";
						carteraFacturaPresupuesto = "R/P";
					}
					else if(!tieneRetencion && (valorPago > 0)){
						carteraFactura = "P";
						carteraFacturaPresupuesto = "P";
					}
					else if(tieneRetencion && (valorPago == valorRetencion)){
						carteraFactura = "R";
						carteraFacturaPresupuesto = "R";
					}
				}	
								
				//fechaFactura, factura
				fechaFacturaFactura = Utilitarios.getFechaCortaUppercase(facturaDelPresupuesto.getFechaFactura()); // + "\n" + detallePago;
				facturaFactura = facturaDelPresupuesto.getPreimpresoNumero(); // + "\n" + fechaPago;
				
				//sap
				if(facturaDelPresupuesto.getAutorizacionSap() != null){
					sapFactura = facturaDelPresupuesto.getAutorizacionSap();
				}else if(cliente.getRequiereSap().equals("S")){
					sapFactura = "PENDIENTE";
				}
								
				//medio (tipoMedio), proveedor
				//mapas de proveedores y tipos de medios
				Map<Long,String> tipoMedioMapa = new HashMap<Long,String>();
				Map<Long,String> medioOficinaMapa = new HashMap<Long,String>();
				
				for(int i = 0; i < presupuestosDetalleDelPresupuesto.size(); i++){
					Object[] presupuestoDetalle = (Object[])presupuestosDetalleDelPresupuesto.get(i);
				
					Long tipoMedioId = (Long)presupuestoDetalle[7];
					Long medioOficinaId = (Long)presupuestoDetalle[3];					
					String tipoMedio = (String)presupuestoDetalle[6];
					String medioOficina = (String)presupuestoDetalle[2];
										
					tipoMedioMapa.put(tipoMedioId, tipoMedio);
					medioOficinaMapa.put(medioOficinaId, medioOficina);					
				}
						
				//medio
				Iterator tipoMedioMapaIt = tipoMedioMapa.keySet().iterator();
				while(tipoMedioMapaIt.hasNext()){
					Long tipoMedioId = (Long)tipoMedioMapaIt.next();
					if(medioFactura.equals("")){
						medioFactura = tipoMedioMapa.get(tipoMedioId);
					}else{
						medioFactura = medioFactura + "\n" + tipoMedioMapa.get(tipoMedioId);
					}
				}
				
				//proveedor
				if(getCbVerProveedoresFactura().isSelected()){
					Iterator medioOficinaMapaIt = medioOficinaMapa.keySet().iterator();
					while(medioOficinaMapaIt.hasNext()){
						Long medioOficinaId = (Long)medioOficinaMapaIt.next();
						if(proveedorFactura.equals("")){
							proveedorFactura = medioOficinaMapa.get(medioOficinaId);
						}else if(getCbVerCompras().isSelected()){
							proveedorFactura = proveedorFactura + "\n" + medioOficinaMapa.get(medioOficinaId);
						}
					}
				}				
				
				//valor, iva, total
				double valorBruto = facturaDelPresupuesto.getValor().doubleValue();
				double descuento = facturaDelPresupuesto.getDescuento().doubleValue();
				double descuentosVarios = facturaDelPresupuesto.getDescuentosVarios().doubleValue();
				double porcentajeComisionAgencia = facturaDelPresupuesto.getPorcentajeComisionAgencia().doubleValue() / 100;
				double comisionAgencia = (valorBruto - descuento - descuentosVarios) * porcentajeComisionAgencia;
				double valorNeto = valorBruto - descuento - descuentosVarios + comisionAgencia;
				double ivaValor = facturaDelPresupuesto.getIva().doubleValue();
				double totalFacturaValor = valorNeto + ivaValor;
				valorFactura = formatoDecimal.format(valorNeto);
				ivaFactura = formatoDecimal.format(ivaValor);
				totalFactura = formatoDecimal.format(totalFacturaValor);
				
				totalValorFacturasPresupuesto = totalValorFacturasPresupuesto + valorNeto;
				totalIvaFacturasPresupuesto = totalIvaFacturasPresupuesto + ivaValor;
				totalTotalFacturasPresupuesto = totalTotalFacturasPresupuesto + totalFacturaValor;
								
				//porque pudo contarse ya previamente en otro plan porque
				//se puede facturar dos o mas planes en la misma factura
				if(!facturaUsada){
					//seteo el total facturado en el mes correspondiente
					if(facturas[date.getMonth()] == null){
						facturas[date.getMonth()] = totalFacturaValor;
					}else{
						facturas[date.getMonth()] = facturas[date.getMonth()] + totalFacturaValor;
					}
					//total de facturas
					totalFacturas = totalFacturas + totalFacturaValor;
					
					//seteo el total de cantidad de facturas en el mes correspondiente
					if(cantidadFacturas[date.getMonth()] == null){
						cantidadFacturas[date.getMonth()] = 1;
					}else{
						cantidadFacturas[date.getMonth()] = cantidadFacturas[date.getMonth()] + 1;
					}
					//total cantidad de facturas
					totalCantidadFacturas = totalCantidadFacturas + 1;
				}
				
				//LINEA POR CADA FACTURA
				InversionClientesData inversionClientesVectorFactura = new InversionClientesData();
				inversionClientesVectorFactura.setClienteId(clienteIdPresupuesto);
				inversionClientesVectorFactura.setClienteOficina(clienteOficinaPresupuesto);
				inversionClientesVectorFactura.setCodigoPresupuesto(codigoPresupuestoPresupuesto);
				inversionClientesVectorFactura.setTipoFactura(tipoFacturaFactura);
				inversionClientesVectorFactura.setCartera(carteraFactura);
				inversionClientesVectorFactura.setFechaFactura(fechaFacturaFactura);
				inversionClientesVectorFactura.setFactura(facturaFactura);
				inversionClientesVectorFactura.setFechaAprobacion(fechaAprobacionFactura);
				inversionClientesVectorFactura.setSap(sapFactura);
				inversionClientesVectorFactura.setSegmento(segmentoFactura);
				inversionClientesVectorFactura.setProducto(productoFactura);
				inversionClientesVectorFactura.setMedio(medioFactura);
				inversionClientesVectorFactura.setProveedor(proveedorFactura);
				inversionClientesVectorFactura.setValor(valorFactura);
				inversionClientesVectorFactura.setIva(ivaFactura);
				inversionClientesVectorFactura.setTotal(totalFactura);
				inversionClientesVectorFactura.setDate(date);
				inversionClientesVectorFactura.setPosicion("1");
				inversionClientesVectorFactura.setInversionPautaValor("0");
				inversionClientesVectorFactura.setInversionPautaIva("0");
				inversionClientesVectorFactura.setInversionPautaTotal("0");
				
				inversionClientesVectorFactura.setCarteraFactura(carteraFacturaPresupuesto);
				
				inversionClientesDataVector.add(inversionClientesVectorFactura);			
			}
		}
		//si es presupuesto PREPAGADO
		else if(presupuestoIf.getEstado().equals("R")){
			facturaPresupuesto = "PREPAGADO";
		}		
		//si no existen facuras
		else{
			facturaPresupuesto = "PENDIENTE";
		}
				
		//SI HAY MAS DE 2 FACTURAS SALIDAS DEL PRESUPUESTO SE PONE UN TOTAL FACTURADO
		if(facturasDelPresupuesto.size() > 1 && getCbVerFacturas().isSelected()){
			//LINEA EXTRA POR CADA FACTURA DONDE VA EL TOTAL
			InversionClientesData inversionClientesVectorFactura = new InversionClientesData();
			inversionClientesVectorFactura.setClienteId(clienteIdPresupuesto);
			inversionClientesVectorFactura.setClienteOficina(clienteOficinaPresupuesto);
			inversionClientesVectorFactura.setCodigoPresupuesto(codigoPresupuestoPresupuesto);
			inversionClientesVectorFactura.setTipoFactura("");
			inversionClientesVectorFactura.setFechaFactura("");
			inversionClientesVectorFactura.setFactura("");
			inversionClientesVectorFactura.setFechaAprobacion("");
			inversionClientesVectorFactura.setSap("");
			inversionClientesVectorFactura.setSegmento("");
			inversionClientesVectorFactura.setProducto("");
			inversionClientesVectorFactura.setMedio("");
			inversionClientesVectorFactura.setProveedor("TOTAL FACTURADO: ");
			inversionClientesVectorFactura.setValor(formatoDecimal.format(totalValorFacturasPresupuesto));
			inversionClientesVectorFactura.setIva(formatoDecimal.format(totalIvaFacturasPresupuesto));
			inversionClientesVectorFactura.setTotal(formatoDecimal.format(totalTotalFacturasPresupuesto));
			inversionClientesVectorFactura.setDate(date);
			inversionClientesVectorFactura.setPosicion("1");
			inversionClientesVectorFactura.setInversionPautaValor("0");
			inversionClientesVectorFactura.setInversionPautaIva("0");
			inversionClientesVectorFactura.setInversionPautaTotal("0");
			inversionClientesDataVector.add(inversionClientesVectorFactura);
		}
		
			
		///////////////////////////////////
		//PARA LAS COMPRAS
		//////////////////////////////////
						
		String tipoMedioParaExcel = "";
		
		//ordenes
		Vector<OrdenCompraIf> ordenesDelPresupuesto = new Vector<OrdenCompraIf>();
		
		//si se desea ver las Ordnes / Compras, lleno el vector con las ordenes del presupuesto
		if(getCbVerCompras().isSelected()){
			Map solicitudesMapa = new HashMap();
			solicitudesMapa.put("tipoReferencia", TIPO_REFERENCIA_PRESUPUESTO);
			solicitudesMapa.put("referencia", presupuestoIf.getCodigo());
			
			Collection solicitudesCompra = SessionServiceLocator.getSolicitudCompraSessionService().findSolicitudCompraByQuery(solicitudesMapa);
			Iterator solicitudesCompraIt = solicitudesCompra.iterator();
			while(solicitudesCompraIt.hasNext()){
				SolicitudCompraIf solicitudCompraIf = (SolicitudCompraIf)solicitudesCompraIt.next();
				
				Collection ordenes = SessionServiceLocator.getOrdenCompraSessionService().findOrdenCompraBySolicitudcompraId(solicitudCompraIf.getId());
				Iterator ordenesIt = ordenes.iterator();
				while(ordenesIt.hasNext()){
					OrdenCompraIf ordeCompraIf = (OrdenCompraIf)ordenesIt.next();
					//si la orden esta Ingresada (G), Enviada (E) o Pendiente (P), la pongo en la coleccion
					/*if(ordeCompraIf.getEstado().equals("G") 
							|| ordeCompraIf.getEstado().equals("E") 
							|| ordeCompraIf.getEstado().equals("P")
							|| ordeCompraIf.getEstado().equals("D")
							|| ordeCompraIf.getEstado().equals("R")
							|| ordeCompraIf.getEstado().equals("M")){
						ordenesDelPresupuesto.add(ordeCompraIf);					
					}*/
					if(!ordeCompraIf.getEstado().equals("A")){
						ordenesDelPresupuesto.add(ordeCompraIf);					
					}
				}
			}			
		}
				
		int cantidadDeOrdenes = 0;
		int cantidadDeCompras = 0;
		double totalValorOrdenesPresupuesto = 0D;
		double totalIvaOrdenesPresupuesto  = 0D;
		double totalTotalOrdenesPresupuesto  = 0D;
		double totalValorComprasPresupuesto  = 0D;
		double totalIvaComprasPresupuesto  = 0D;
		double totalTotalComprasPresupuesto  = 0D;
		
		//si existen ordenes
		if(ordenesDelPresupuesto.size() > 0){
			
			for(OrdenCompraIf ordenDelPresupuesto : ordenesDelPresupuesto){
				
				String fechaAprobacionOrden = "";
				String tipoFacturaOrden = "";
				String carteraCompra = "";
				String fechaFacturaOrden = "";
				String facturaOrden = "";
				String sapOrden = "";
				String segmentoOrden = "";
				String productoOrden = "";
				String medioOrden = "";
				String proveedorOrden = "";
				String valorOrden = "";
				String ivaOrden = "";
				String totalOrden = "";
				
				//tipo
				Map buscarCompra = new HashMap();
				buscarCompra.put("tipoOrden", "OC");
				buscarCompra.put("ordenId", ordenDelPresupuesto.getId());
				ArrayList ordenesAsociadas = (ArrayList)SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByQuery(buscarCompra);
				
				CompraIf compra = null;
				if(ordenesAsociadas.size() > 0){
					OrdenAsociadaIf ordenAsociada = (OrdenAsociadaIf)ordenesAsociadas.get(0);
					compra = SessionServiceLocator.getCompraSessionService().getCompra(ordenAsociada.getCompraId());
				}
				
				if(compra != null){
					tipoFacturaOrden = "CO";
				}else{
					tipoFacturaOrden = "OR";
				}
				
				//cartera
				boolean tieneRetencion = false;
				double valorRetencion = 0D;
				double valorPago = 0D;
				if(compra != null && getCbVerCarteraEgresos().isSelected()){
					CarteraIf carteraFacturaIf = null;
					Collection carterasIngresoDetalle = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaCarteraIngresoDetalleByTipoCarteraByReferenciaAfectadaIdAndByPreimpreso("P", compra.getId(), compra.getPreimpreso());
					Iterator carterasIngresoDetalleIt = carterasIngresoDetalle.iterator();
					while(carterasIngresoDetalleIt.hasNext()){
						Object[] carteraIngresoDetalle = (Object[])carterasIngresoDetalleIt.next();
						CarteraAfectaIf carteraAfectaIf = (CarteraAfectaIf)carteraIngresoDetalle[0];
						CarteraDetalleIf carteraDetalleIngreso = (CarteraDetalleIf)carteraIngresoDetalle[1];
						carteraFacturaIf = (CarteraIf)carteraIngresoDetalle[2];
						
						DocumentoIf documentoIf = mapaDocumento.get(carteraDetalleIngreso.getDocumentoId());
						if(documentoIf.getRetencionIva().equals("S") || documentoIf.getRetencionRenta().equals("S")){
							tieneRetencion = true;
							valorRetencion = valorRetencion + carteraAfectaIf.getValor().doubleValue();
						}
						valorPago = valorPago + carteraAfectaIf.getValor().doubleValue();
					}
					
					if(carteraFacturaIf != null && carteraFacturaIf.getSaldo().doubleValue() <= 0.01){
						carteraCompra = "T";
						carteraCompraPresupuesto = "T";
					}
					else if(tieneRetencion && (valorPago > valorRetencion)){
						carteraCompra = "R/P";
						carteraCompraPresupuesto = "R/P";
					}
					else if(!tieneRetencion && (valorPago > 0)){
						carteraCompra = "P";
						carteraCompraPresupuesto = "P";
					}
					else if(tieneRetencion && (valorPago == valorRetencion)){
						carteraCompra = "R";
						carteraCompraPresupuesto = "R";
					}
				}
				
				//presupuesto detalle
				/*String preimpresoFacturaCliente = "";
				Collection presupuestoDetalles = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByOrdenCompraId(ordenDelPresupuesto.getId());
				Iterator presupuestoDetallesIt = presupuestoDetalles.iterator();
				while(presupuestoDetallesIt.hasNext()){
					PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf)presupuestoDetallesIt.next();
					Collection presupuestosDetallesFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByPresupuestoDetalleId(presupuestoDetalleIf.getId());
					Iterator presupuestosDetallesFacturacionIt = presupuestosDetallesFacturacion.iterator();
					while(presupuestosDetallesFacturacionIt.hasNext()){
						PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestosDetallesFacturacionIt.next();
						if(presupuestoFacturacionIf.getEstado().equals("F")){
							FacturaIf facturaIf = SessionServiceLocator.getFacturaSessionService().getFactura(presupuestoFacturacionIf.getFacturaId());
							if(facturaIf != null && facturaIf.getEstado().equals("C")){
								if(preimpresoFacturaCliente.equals("")){
									preimpresoFacturaCliente = facturaIf.getPreimpresoNumero();
								}else{
									preimpresoFacturaCliente = preimpresoFacturaCliente + ", " + facturaIf.getPreimpresoNumero();
								}
							}	
						}											
					}
				}*/
				
				//fechaFactura, factura
				if(compra != null){
					fechaFacturaOrden = Utilitarios.getFechaCortaUppercase(compra.getFecha());
					facturaOrden = compra.getPreimpreso(); // + "\n" + preimpresoFacturaCliente;
				}else{
					fechaFacturaOrden = Utilitarios.getFechaCortaUppercase(ordenDelPresupuesto.getFecha());
					facturaOrden = ordenDelPresupuesto.getCodigo(); // + "\n" + preimpresoFacturaCliente;
				}
				
				//sap NO APLICA
				
				//medio, proveedor
				//mapas de proveedores y tipos de medios
				Map<Long,ClienteOficinaIf> proveedorIdMapa = new HashMap<Long,ClienteOficinaIf>();
				Map<Long,TipoProveedorIf> tipoMediosMapa = new HashMap<Long,TipoProveedorIf>();
				if(compra != null){
					Collection comprasDetalle = SessionServiceLocator.getCompraDetalleSessionService().findCompraDetalleByCompraId(compra.getId());
					Iterator comprasDetalleIt = comprasDetalle.iterator();
					while(comprasDetalleIt.hasNext()){
						CompraDetalleIf compraDetalle = (CompraDetalleIf)comprasDetalleIt.next();
						ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(compraDetalle.getProductoId());
						
						ClienteOficinaIf proveedorOficinaIf = mapaClienteOficina.get(producto.getProveedorId());
						proveedorIdMapa.put(producto.getProveedorId(), proveedorOficinaIf);
						ClienteIf proveedorIf = mapaCliente.get(proveedorOficinaIf.getClienteId());
						TipoProveedorIf tipoMedio = mapaTipoProveedor.get(proveedorIf.getTipoproveedorId());
						tipoMediosMapa.put(tipoMedio.getId(), tipoMedio);
					}	
				}else{
					Collection ordenesDetalle = SessionServiceLocator.getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByOrdencompraId(ordenDelPresupuesto.getId());
					Iterator ordenesDetalleIt = ordenesDetalle.iterator();
					while(ordenesDetalleIt.hasNext()){
						OrdenCompraDetalleIf ordenCompraDetalle = (OrdenCompraDetalleIf)ordenesDetalleIt.next();
						ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(ordenCompraDetalle.getProductoId());
						
						ClienteOficinaIf proveedorOficinaIf = mapaClienteOficina.get(producto.getProveedorId());
						proveedorIdMapa.put(producto.getProveedorId(), proveedorOficinaIf);
						ClienteIf proveedorIf = mapaCliente.get(proveedorOficinaIf.getClienteId());
						TipoProveedorIf tipoMedio = mapaTipoProveedor.get(proveedorIf.getTipoproveedorId());
						tipoMediosMapa.put(tipoMedio.getId(), tipoMedio);
					}	
				}				
				
				//medio
				Iterator<Long> tipoMediosMapaIt = tipoMediosMapa.keySet().iterator();
				while(tipoMediosMapaIt.hasNext()){
					Long tipoMedioId = tipoMediosMapaIt.next();
					String tipoMedio = tipoMediosMapa.get(tipoMedioId).getNombre();
					if(medioOrden.equals("")){
						medioOrden = tipoMedio;
					}else{
						medioOrden = medioOrden + "\n" + tipoMedio;
					}
				}
				
				tipoMedioParaExcel = medioOrden;
				
				//proveedor
				Iterator<Long> proveedoresMapaIt = proveedorIdMapa.keySet().iterator();
				while(proveedoresMapaIt.hasNext()){
					Long proveedorId = proveedoresMapaIt.next();
					String proveedorNombre = proveedorIdMapa.get(proveedorId).getDescripcion();
					if(proveedorOrden.equals("")){
						proveedorOrden = proveedorNombre;
					}else if(getCbVerFacturas().isSelected()){
						proveedorOrden = proveedorOrden + ",\n" + proveedorNombre;
					}
				}
				
				//valor, iva, total
				double valorBruto = ordenDelPresupuesto.getValor().doubleValue();
				double porcentajeDescuentoEspecial = ordenDelPresupuesto.getPorcentajeDescuentoEspecial().doubleValue() / 100D;
				double descuentoEspecial = valorBruto * porcentajeDescuentoEspecial;
				double descuentoAgencia = ordenDelPresupuesto.getDescuentoAgenciaCompra().doubleValue();
				double porcentajeDescuentosVarios = ordenDelPresupuesto.getPorcentajeDescuentosVariosCompra().doubleValue() / 100D;
				double descuentosVarios = (valorBruto - descuentoEspecial) * porcentajeDescuentosVarios;
				double valorNeto = valorBruto - descuentoEspecial - descuentoAgencia - descuentosVarios;
				double ivaValor = ordenDelPresupuesto.getIva().doubleValue();
				double totalOrdenValor = valorNeto + ivaValor;
				valorOrden = formatoDecimal.format(valorNeto);
				ivaOrden = formatoDecimal.format(ivaValor);
				totalOrden = formatoDecimal.format(totalOrdenValor);
				
				//total de ordenes
				if(ordenesDelPresupuesto.size() > 0){
					totalValorOrdenesPresupuesto  = totalValorOrdenesPresupuesto  + valorNeto;
					totalIvaOrdenesPresupuesto  = totalIvaOrdenesPresupuesto  + ivaValor;
					totalTotalOrdenesPresupuesto  = totalTotalOrdenesPresupuesto  + totalOrdenValor;
					cantidadDeOrdenes = cantidadDeOrdenes + 1;
					
					//seteo el total de ordenes en el mes correspondiente
					if(ordenes[date.getMonth()] == null){
						ordenes[date.getMonth()] = totalOrdenValor;
					}else{
						ordenes[date.getMonth()] = ordenes[date.getMonth()] + totalOrdenValor;
					}
					//total de ordenes
					totalOrdenes = totalOrdenes + totalOrdenValor;
					
					//seteo el total de cantidad de ordenes en el mes correspondiente
					if(cantidadOrdenes[date.getMonth()] == null){
						cantidadOrdenes[date.getMonth()] = 1;
					}else{
						cantidadOrdenes[date.getMonth()] = cantidadOrdenes[date.getMonth()] + 1;
					}
					//total cantidad de ordenes
					totalCantidadOrdenes = totalCantidadOrdenes + 1;
				}
				
				//total solo de compras
				if(compra != null){
					totalValorComprasPresupuesto  = totalValorComprasPresupuesto  + valorNeto;
					totalIvaComprasPresupuesto  = totalIvaComprasPresupuesto  + ivaValor;
					totalTotalComprasPresupuesto  = totalTotalComprasPresupuesto  + totalOrdenValor;
					cantidadDeCompras = cantidadDeCompras + 1;
					
					//seteo el total de compras en el mes correspondiente
					if(compras[date.getMonth()] == null){
						compras[date.getMonth()] = totalOrdenValor;
					}else{
						compras[date.getMonth()] = compras[date.getMonth()] + totalOrdenValor;
					}
					//total de compras
					totalCompras = totalCompras + totalOrdenValor;
					
					//seteo el total de cantidad de compras en el mes correspondiente
					if(cantidadCompras[date.getMonth()] == null){
						cantidadCompras[date.getMonth()] = 1;
					}else{
						cantidadCompras[date.getMonth()] = cantidadCompras[date.getMonth()] + 1;
					}
					//total cantidad de ordenes
					totalCantidadCompras = totalCantidadCompras + 1;
				}		
								
				//LINEA POR CADA ORDEN
				InversionClientesData inversionClientesVectorFactura = new InversionClientesData();
				inversionClientesVectorFactura.setClienteId(clienteIdPresupuesto);
				inversionClientesVectorFactura.setClienteOficina(clienteOficinaPresupuesto);
				inversionClientesVectorFactura.setCodigoPresupuesto(codigoPresupuestoPresupuesto);
				inversionClientesVectorFactura.setTipoFactura(tipoFacturaOrden);
				inversionClientesVectorFactura.setCartera(carteraCompra);
				inversionClientesVectorFactura.setFechaFactura(fechaFacturaOrden);
				inversionClientesVectorFactura.setFactura(facturaOrden);
				inversionClientesVectorFactura.setFechaAprobacion(fechaAprobacionOrden);
				inversionClientesVectorFactura.setSap(sapOrden);
				inversionClientesVectorFactura.setSegmento(segmentoOrden);
				inversionClientesVectorFactura.setProducto(productoOrden);
				inversionClientesVectorFactura.setMedio(medioOrden);
				inversionClientesVectorFactura.setProveedor(proveedorOrden);
				inversionClientesVectorFactura.setValor(valorOrden);
				inversionClientesVectorFactura.setIva(ivaOrden);
				inversionClientesVectorFactura.setTotal(totalOrden);
				inversionClientesVectorFactura.setDate(date);
				inversionClientesVectorFactura.setPosicion("2");
				inversionClientesVectorFactura.setInversionPautaValor("0");
				inversionClientesVectorFactura.setInversionPautaIva("0");
				inversionClientesVectorFactura.setInversionPautaTotal("0");
				
				inversionClientesVectorFactura.setCarteraCompra(carteraCompraPresupuesto);
				
				inversionClientesDataVector.add(inversionClientesVectorFactura);
					
				// /////////////////////////////////////////
				// PARA EL ARCHIVO DE COMISIONES ADICIONALES
				// /////////////////////////////////////////

				if (getCbReporteComisionAdicional().isSelected()) {
					
					Collection presupuestosDetalleIf = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByOrdenCompraId(ordenDelPresupuesto.getId());
					if(presupuestosDetalleIf.size() > 0){
						PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf)presupuestosDetalleIf.iterator().next();
						
						if (presupuestoDetalleIf.getPorcentajeComisionAdicional() != null
								&& presupuestoDetalleIf.getPorcentajeComisionAdicional().compareTo(new BigDecimal(0)) == 1) {
							
							ComisionesAdicionalesData comisionAdicionalVector = new ComisionesAdicionalesData();

							comisionAdicionalVector.setTipoMedio(tipoMedioParaExcel);
							comisionAdicionalVector.setProveedorOficina(proveedorOrden);
							comisionAdicionalVector.setFechaCompra(fechaFacturaOrden);
							comisionAdicionalVector.setPreimpresoCompra(facturaOrden);
							comisionAdicionalVector.setCodigoPresupuesto(codigoPresupuestoPresupuesto);
							comisionAdicionalVector.setClienteOficina(clienteOficinaPresupuesto);

							// busco si este presupuesto tiene relacionado un presupuesto para cobrar comision adicional
							PresupuestoIf presupuestoAdicionalIf = null;
							Map aMap = new HashMap();
							aMap.put("tipoReferencia", "P");
							aMap.put("referenciaId", presupuestoIf.getId());
							Collection presupuestosAdicionales = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByQuery(aMap,Parametros.getIdEmpresa());
							Iterator presupuestosAdicionalesIt = presupuestosAdicionales.iterator();
							while(presupuestosAdicionalesIt.hasNext()){
								PresupuestoIf presupuestoAdicionalTemp = (PresupuestoIf)presupuestosAdicionalesIt.next();
								//reviso si en el presupuesto adicional/hijo el cliente es proveedor que tiene la comision adicional en el presupuesto madre
								//importante porque un presupuesto madre puede tener varios presupuestos hijos
								if (presupuestoAdicionalTemp.getClienteOficinaId().compareTo(presupuestoDetalleIf.getProveedorId()) == 0) {
									presupuestoAdicionalIf = presupuestoAdicionalTemp;
								}								
							}
							
							if (presupuestoAdicionalIf != null) {
								comisionAdicionalVector.setCodigoPresupuestoProveedor(presupuestoAdicionalIf.getCodigo());
								comisionAdicionalVector.setValorPresupuestoProveedor(formatoDecimal.format(presupuestoAdicionalIf.getValorbruto()));
								comisionAdicionalVector.setEstadoPresupuestoProveedor(EstadoPresupuesto.getEstadoPresupuestoByLetra(presupuestoAdicionalIf.getEstado()).toString());
							}

							comisionAdicionalVector.setInversionPresupuesto(formatoDecimal.format(valorBruto));

							String porcentajeComisionAdicional = "0.00";
							String valorComisionAdicional = "0.00";
							if (presupuestoDetalleIf.getPorcentajeComisionAdicional() != null) {
								porcentajeComisionAdicional = formatoDecimal.format(presupuestoDetalleIf.getPorcentajeComisionAdicional());
								double comisionAdicional = valorBruto * (presupuestoDetalleIf.getPorcentajeComisionAdicional().doubleValue() / 100D);
								valorComisionAdicional = formatoDecimal.format(comisionAdicional);
							}
							
							comisionAdicionalVector.setPorcentajeComisionAdicional(porcentajeComisionAdicional);
							comisionAdicionalVector.setValorComisionAdicional(valorComisionAdicional);

							comisionAdicionalVector.setDate(date);

							comisionesAdicionalesDataVector.add(comisionAdicionalVector);
						}
					}
				}
			}
		}
		
		//SI HAY MAS DE 2 ORDENES SALIDAS DE LA PAUTA SE PONE UN TOTAL DE ORDENES
		if(cantidadDeOrdenes > 1){
			//LINEA EXTRA POR CADA FACTURA DONDE VA EL TOTAL
			InversionClientesData inversionClientesVectorFactura = new InversionClientesData();
			inversionClientesVectorFactura.setClienteId(clienteIdPresupuesto);
			inversionClientesVectorFactura.setClienteOficina(clienteOficinaPresupuesto);
			inversionClientesVectorFactura.setCodigoPresupuesto(codigoPresupuestoPresupuesto);
			inversionClientesVectorFactura.setTipoFactura("");
			inversionClientesVectorFactura.setFechaFactura("");
			inversionClientesVectorFactura.setFactura("");
			inversionClientesVectorFactura.setFechaAprobacion("");
			inversionClientesVectorFactura.setSap("");
			inversionClientesVectorFactura.setSegmento("");
			inversionClientesVectorFactura.setProducto("");
			inversionClientesVectorFactura.setMedio("");
			inversionClientesVectorFactura.setProveedor("TOTAL ORDENES: ");
			inversionClientesVectorFactura.setValor(formatoDecimal.format(totalValorOrdenesPresupuesto));
			inversionClientesVectorFactura.setIva(formatoDecimal.format(totalIvaOrdenesPresupuesto));
			inversionClientesVectorFactura.setTotal(formatoDecimal.format(totalTotalOrdenesPresupuesto));
			inversionClientesVectorFactura.setDate(date);
			inversionClientesVectorFactura.setPosicion("2");
			inversionClientesVectorFactura.setInversionPautaValor("0");
			inversionClientesVectorFactura.setInversionPautaIva("0");
			inversionClientesVectorFactura.setInversionPautaTotal("0");
			inversionClientesDataVector.add(inversionClientesVectorFactura);
		}
		
		//SI HAY MAS DE 2 COMPRAS SALIDAS DE LA PAUTA SE PONE UN TOTAL DE COMPRAS
		if(cantidadDeCompras > 1){
			//LINEA EXTRA POR CADA FACTURA DONDE VA EL TOTAL
			InversionClientesData inversionClientesVectorFactura = new InversionClientesData();
			inversionClientesVectorFactura.setClienteId(clienteIdPresupuesto);
			inversionClientesVectorFactura.setClienteOficina(clienteOficinaPresupuesto);
			inversionClientesVectorFactura.setCodigoPresupuesto(codigoPresupuestoPresupuesto);
			inversionClientesVectorFactura.setTipoFactura("");
			inversionClientesVectorFactura.setFechaFactura("");
			inversionClientesVectorFactura.setFactura("");
			inversionClientesVectorFactura.setFechaAprobacion("");
			inversionClientesVectorFactura.setSap("");
			inversionClientesVectorFactura.setSegmento("");
			inversionClientesVectorFactura.setProducto("");
			inversionClientesVectorFactura.setMedio("");
			inversionClientesVectorFactura.setProveedor("TOTAL COMPRAS: ");
			inversionClientesVectorFactura.setValor(formatoDecimal.format(totalValorComprasPresupuesto));
			inversionClientesVectorFactura.setIva(formatoDecimal.format(totalIvaComprasPresupuesto));
			inversionClientesVectorFactura.setTotal(formatoDecimal.format(totalTotalComprasPresupuesto));
			inversionClientesVectorFactura.setDate(date);
			inversionClientesVectorFactura.setPosicion("2");
			inversionClientesVectorFactura.setInversionPautaValor("0");
			inversionClientesVectorFactura.setInversionPautaIva("0");
			inversionClientesVectorFactura.setInversionPautaTotal("0");
			inversionClientesDataVector.add(inversionClientesVectorFactura);
		}
				
		
		//LINEA DEL PLAN
		InversionClientesData inversionClientesVectorPresupuesto = new InversionClientesData();
		inversionClientesVectorPresupuesto.setClienteId(clienteIdPresupuesto);
		inversionClientesVectorPresupuesto.setClienteOficina(clienteOficinaPresupuesto);
		inversionClientesVectorPresupuesto.setCodigoPresupuesto(codigoPresupuestoPresupuesto);
		inversionClientesVectorPresupuesto.setTipoFactura(tipoFacturaPresupuesto);
		inversionClientesVectorPresupuesto.setFechaFactura(fechaFacturaPresupuesto);
		inversionClientesVectorPresupuesto.setFactura(facturaPresupuesto);
		inversionClientesVectorPresupuesto.setFechaAprobacion(fechaAprobacionPresupuesto);
		inversionClientesVectorPresupuesto.setMesPresupuesto(mesPresupuesto);
		inversionClientesVectorPresupuesto.setSap(sapPresupuesto);
		inversionClientesVectorPresupuesto.setSegmento(segmentoPresupuesto);
		inversionClientesVectorPresupuesto.setProducto(productoPresupuesto);
		inversionClientesVectorPresupuesto.setMedio(medioPresupuesto);
		inversionClientesVectorPresupuesto.setProveedor(proveedorPresupuesto);
		inversionClientesVectorPresupuesto.setValor(valorPresupuesto);
		inversionClientesVectorPresupuesto.setIva(ivaPresupuesto);
		inversionClientesVectorPresupuesto.setTotal(totalPresupuesto);
		inversionClientesVectorPresupuesto.setDate(date);
		inversionClientesVectorPresupuesto.setPosicion("0");
		inversionClientesVectorPresupuesto.setInversionPautaValor(inversionPresupuestoValor);
		inversionClientesVectorPresupuesto.setInversionPautaIva(inversionPresupuestoIva);
		inversionClientesVectorPresupuesto.setInversionPautaTotal(inversionPresupuestoTotal);
		
		inversionClientesVectorPresupuesto.setCarteraFactura(carteraFacturaPresupuesto);
		inversionClientesVectorPresupuesto.setCarteraCompra(carteraCompraPresupuesto);
		
		inversionClientesDataVector.add(inversionClientesVectorPresupuesto);
			
		if(presupuestoIf.getValor().doubleValue() < totalValorOrdenesPresupuesto){
			problemas.put(codigoPresupuestoPresupuesto, codigoPresupuestoPresupuesto);
		}
		
		//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'				
		String estadoPresupuesto = presupuestoIf.getEstado();
				
		setearTotalesPresupuestoMes(presupuestoIf, date, estadoPresupuesto);
		
		//////////////////////////////////////////
		//PARA EL ARCHIVO DE TOTALES DE INVERSION
		//////////////////////////////////////////
		
		InversionTotalesClientesData inversionTotalClientesVectorFactura = new InversionTotalesClientesData();
		
		inversionTotalClientesVectorFactura.setClienteId(clienteIdPresupuesto);
		inversionTotalClientesVectorFactura.setClienteOficina(clienteOficinaPresupuesto);
		inversionTotalClientesVectorFactura.setMesPresupuesto(mesPresupuesto);
		inversionTotalClientesVectorFactura.setFechaPresupuesto(fechaAprobacionPresupuesto);
		inversionTotalClientesVectorFactura.setDate(date);
		inversionTotalClientesVectorFactura.setCodigoPresupuesto(codigoPresupuestoPresupuesto);
		inversionTotalClientesVectorFactura.setTipoMedio(tipoMedioParaExcel);
		inversionTotalClientesVectorFactura.setSubtotalPresupuesto(inversionPresupuestoValor);
		inversionTotalClientesVectorFactura.setSubtotalOrdenes(formatoDecimal.format(totalValorOrdenesPresupuesto));
		
		inversionTotalClientesDataVector.add(inversionTotalClientesVectorFactura);
	}

	private void setearTotalesPresupuestoMes(PresupuestoIf presupuestoIf,
			Timestamp date, String estadoPresupuesto) {
		if(date.getMonth() == 0 && (estadoPresupuesto.equals("T") || estadoPresupuesto.equals("P"))){
			presupuestosCotizadosEne++;
			totalCotizadoEne = totalCotizadoEne.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 0 && estadoPresupuesto.equals("A")){
			presupuestosPendientesEne++;
			totalPendienteEne = totalPendienteEne.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 0 && estadoPresupuesto.equals("F")){
			presupuestosFacturadosEne++;
			totalFacturadoEne = totalFacturadoEne.add(presupuestoIf.getValor());
		}
		
		if(date.getMonth() == 1 && (estadoPresupuesto.equals("T") || estadoPresupuesto.equals("P"))){
			presupuestosCotizadosFeb++;
			totalCotizadoFeb = totalCotizadoFeb.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 1 && estadoPresupuesto.equals("A")){
			presupuestosPendientesFeb++;
			totalPendienteFeb = totalPendienteFeb.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 1 && estadoPresupuesto.equals("F")){
			presupuestosFacturadosFeb++;
			totalFacturadoFeb = totalFacturadoFeb.add(presupuestoIf.getValor());
		}
		
		if(date.getMonth() == 2 && (estadoPresupuesto.equals("T") || estadoPresupuesto.equals("P"))){
			presupuestosCotizadosMar++;
			totalCotizadoMar = totalCotizadoMar.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 2 && estadoPresupuesto.equals("A")){
			presupuestosPendientesMar++;
			totalPendienteMar = totalPendienteMar.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 2 && estadoPresupuesto.equals("F")){
			presupuestosFacturadosMar++;
			totalFacturadoMar = totalFacturadoMar.add(presupuestoIf.getValor());
		}
		
		if(date.getMonth() == 3 && (estadoPresupuesto.equals("T") || estadoPresupuesto.equals("P"))){
			presupuestosCotizadosAbr++;
			totalCotizadoAbr = totalCotizadoAbr.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 3 && estadoPresupuesto.equals("A")){
			presupuestosPendientesAbr++;
			totalPendienteAbr = totalPendienteAbr.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 3 && estadoPresupuesto.equals("F")){
			presupuestosFacturadosAbr++;
			totalFacturadoAbr = totalFacturadoAbr.add(presupuestoIf.getValor());
		}
		
		if(date.getMonth() == 4 && (estadoPresupuesto.equals("T") || estadoPresupuesto.equals("P"))){
			presupuestosCotizadosMay++;
			totalCotizadoMay = totalCotizadoMay.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 4 && estadoPresupuesto.equals("A")){
			presupuestosPendientesMay++;
			totalPendienteMay = totalPendienteMay.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 4 && estadoPresupuesto.equals("F")){
			presupuestosFacturadosMay++;
			totalFacturadoMay = totalFacturadoMay.add(presupuestoIf.getValor());
		}
		
		if(date.getMonth() == 5 && (estadoPresupuesto.equals("T") || estadoPresupuesto.equals("P"))){
			presupuestosCotizadosJun++;
			totalCotizadoJun = totalCotizadoJun.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 5 && estadoPresupuesto.equals("A")){
			presupuestosPendientesJun++;
			totalPendienteJun = totalPendienteJun.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 5 && estadoPresupuesto.equals("F")){
			presupuestosFacturadosJun++;
			totalFacturadoJun = totalFacturadoJun.add(presupuestoIf.getValor());
		}
		
		if(date.getMonth() == 6 && (estadoPresupuesto.equals("T") || estadoPresupuesto.equals("P"))){
			presupuestosCotizadosJul++;
			totalCotizadoJul = totalCotizadoJul.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 6 && estadoPresupuesto.equals("A")){
			presupuestosPendientesJul++;
			totalPendienteJul = totalPendienteJul.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 6 && estadoPresupuesto.equals("F")){
			presupuestosFacturadosJul++;
			totalFacturadoJul = totalFacturadoJul.add(presupuestoIf.getValor());
		}
		
		if(date.getMonth() == 7 && (estadoPresupuesto.equals("T") || estadoPresupuesto.equals("P"))){
			presupuestosCotizadosAgo++;
			totalCotizadoAgo = totalCotizadoAgo.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 7 && estadoPresupuesto.equals("A")){
			presupuestosPendientesAgo++;
			totalPendienteAgo = totalPendienteAgo.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 7 && estadoPresupuesto.equals("F")){
			presupuestosFacturadosAgo++;
			totalFacturadoAgo = totalFacturadoAgo.add(presupuestoIf.getValor());
		}
		
		if(date.getMonth() == 8 && (estadoPresupuesto.equals("T") || estadoPresupuesto.equals("P"))){
			presupuestosCotizadosSep++;
			totalCotizadoSep = totalCotizadoSep.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 8 && estadoPresupuesto.equals("A")){
			presupuestosPendientesSep++;
			totalPendienteSep = totalPendienteSep.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 8 && estadoPresupuesto.equals("F")){
			presupuestosFacturadosSep++;
			totalFacturadoSep = totalFacturadoSep.add(presupuestoIf.getValor());
		}
		
		if(date.getMonth() == 9 && (estadoPresupuesto.equals("T") || estadoPresupuesto.equals("P"))){
			presupuestosCotizadosOct++;
			totalCotizadoOct = totalCotizadoOct.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 9 && estadoPresupuesto.equals("A")){
			presupuestosPendientesOct++;
			totalPendienteOct= totalPendienteOct.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 9 && estadoPresupuesto.equals("F")){
			presupuestosFacturadosOct++;
			totalFacturadoOct = totalFacturadoOct.add(presupuestoIf.getValor());
		}
		
		if(date.getMonth() == 10 && (estadoPresupuesto.equals("T") || estadoPresupuesto.equals("P"))){
			presupuestosCotizadosNov++;
			totalCotizadoNov = totalCotizadoNov.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 10 && estadoPresupuesto.equals("A")){
			presupuestosPendientesNov++;
			totalPendienteNov = totalPendienteNov.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 10 && estadoPresupuesto.equals("F")){
			presupuestosFacturadosNov++;
			totalFacturadoNov = totalFacturadoNov.add(presupuestoIf.getValor());
		}
		
		if(date.getMonth() == 11 && (estadoPresupuesto.equals("T") || estadoPresupuesto.equals("P"))){
			presupuestosCotizadosDic++;
			totalCotizadoDic = totalCotizadoDic.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 11 && estadoPresupuesto.equals("A")){
			presupuestosPendientesDic++;
			totalPendienteDic = totalPendienteDic.add(presupuestoIf.getValor());
		}else if(date.getMonth() == 11 && estadoPresupuesto.equals("F")){
			presupuestosFacturadosDic++;
			totalFacturadoDic = totalFacturadoDic.add(presupuestoIf.getValor());
		}
	}
	
	private void setearTotalesMediosMes(Timestamp date, BigDecimal totalPlan,
			String estadoPlan) {
		if(date.getMonth() == 0 && (estadoPlan.equals("N") || estadoPlan.equals("P"))){
			presupuestosCotizadosEne++;
			totalCotizadoEne = totalCotizadoEne.add(totalPlan);
		}else if(date.getMonth() == 0 && estadoPlan.equals("A")){
			presupuestosPendientesEne++;
			totalPendienteEne = totalPendienteEne.add(totalPlan);
		}else if(date.getMonth() == 0 && (estadoPlan.equals("D") || estadoPlan.equals("F"))){
			presupuestosFacturadosEne++;
			totalFacturadoEne = totalFacturadoEne.add(totalPlan);
		}
		
		if(date.getMonth() == 1 && (estadoPlan.equals("N") || estadoPlan.equals("P"))){
			presupuestosCotizadosFeb++;
			totalCotizadoFeb = totalCotizadoFeb.add(totalPlan);
		}else if(date.getMonth() == 1 && estadoPlan.equals("A")){
			presupuestosPendientesFeb++;
			totalPendienteFeb = totalPendienteFeb.add(totalPlan);
		}else if(date.getMonth() == 1 && (estadoPlan.equals("D") || estadoPlan.equals("F"))){
			presupuestosFacturadosFeb++;
			totalFacturadoFeb = totalFacturadoFeb.add(totalPlan);
		}
		
		if(date.getMonth() == 2 && (estadoPlan.equals("N") || estadoPlan.equals("P"))){
			presupuestosCotizadosMar++;
			totalCotizadoMar = totalCotizadoMar.add(totalPlan);
		}else if(date.getMonth() == 2 && estadoPlan.equals("A")){
			presupuestosPendientesMar++;
			totalPendienteMar = totalPendienteMar.add(totalPlan);
		}else if(date.getMonth() == 2 && (estadoPlan.equals("D") || estadoPlan.equals("F"))){
			presupuestosFacturadosMar++;
			totalFacturadoMar = totalFacturadoMar.add(totalPlan);
		}
		
		if(date.getMonth() == 3 && (estadoPlan.equals("N") || estadoPlan.equals("P"))){
			presupuestosCotizadosAbr++;
			totalCotizadoAbr = totalCotizadoAbr.add(totalPlan);
		}else if(date.getMonth() == 3 && estadoPlan.equals("A")){
			presupuestosPendientesAbr++;
			totalPendienteAbr = totalPendienteAbr.add(totalPlan);
		}else if(date.getMonth() == 3 && (estadoPlan.equals("D") || estadoPlan.equals("F"))){
			presupuestosFacturadosAbr++;
			totalFacturadoAbr = totalFacturadoAbr.add(totalPlan);
		}
		
		if(date.getMonth() == 4 && (estadoPlan.equals("N") || estadoPlan.equals("P"))){
			presupuestosCotizadosMay++;
			totalCotizadoMay = totalCotizadoMay.add(totalPlan);
		}else if(date.getMonth() == 4 && estadoPlan.equals("A")){
			presupuestosPendientesMay++;
			totalPendienteMay = totalPendienteMay.add(totalPlan);
		}else if(date.getMonth() == 4 && (estadoPlan.equals("D") || estadoPlan.equals("F"))){
			presupuestosFacturadosMay++;
			totalFacturadoMay = totalFacturadoMay.add(totalPlan);
		}
		
		if(date.getMonth() == 5 && (estadoPlan.equals("N") || estadoPlan.equals("P"))){
			presupuestosCotizadosJun++;
			totalCotizadoJun = totalCotizadoJun.add(totalPlan);
		}else if(date.getMonth() == 5 && estadoPlan.equals("A")){
			presupuestosPendientesJun++;
			totalPendienteJun = totalPendienteJun.add(totalPlan);
		}else if(date.getMonth() == 5 && (estadoPlan.equals("D") || estadoPlan.equals("F"))){
			presupuestosFacturadosJun++;
			totalFacturadoJun = totalFacturadoJun.add(totalPlan);
		}
		
		if(date.getMonth() == 6 && (estadoPlan.equals("N") || estadoPlan.equals("P"))){
			presupuestosCotizadosJul++;
			totalCotizadoJul = totalCotizadoJul.add(totalPlan);
		}else if(date.getMonth() == 6 && estadoPlan.equals("A")){
			presupuestosPendientesJul++;
			totalPendienteJul = totalPendienteJul.add(totalPlan);
		}else if(date.getMonth() == 6 && (estadoPlan.equals("D") || estadoPlan.equals("F"))){
			presupuestosFacturadosJul++;
			totalFacturadoJul = totalFacturadoJul.add(totalPlan);
		}
		
		if(date.getMonth() == 7 && (estadoPlan.equals("N") || estadoPlan.equals("P"))){
			presupuestosCotizadosAgo++;
			totalCotizadoAgo = totalCotizadoAgo.add(totalPlan);
		}else if(date.getMonth() == 7 && estadoPlan.equals("A")){
			presupuestosPendientesAgo++;
			totalPendienteAgo = totalPendienteAgo.add(totalPlan);
		}else if(date.getMonth() == 7 && (estadoPlan.equals("D") || estadoPlan.equals("F"))){
			presupuestosFacturadosAgo++;
			totalFacturadoAgo = totalFacturadoAgo.add(totalPlan);
		}
		
		if(date.getMonth() == 8 && (estadoPlan.equals("N") || estadoPlan.equals("P"))){
			presupuestosCotizadosSep++;
			totalCotizadoSep = totalCotizadoSep.add(totalPlan);
		}else if(date.getMonth() == 8 && estadoPlan.equals("A")){
			presupuestosPendientesSep++;
			totalPendienteSep = totalPendienteSep.add(totalPlan);
		}else if(date.getMonth() == 8 && (estadoPlan.equals("D") || estadoPlan.equals("F"))){
			presupuestosFacturadosSep++;
			totalFacturadoSep = totalFacturadoSep.add(totalPlan);
		}
		
		if(date.getMonth() == 9 && (estadoPlan.equals("N") || estadoPlan.equals("P"))){
			presupuestosCotizadosOct++;
			totalCotizadoOct = totalCotizadoOct.add(totalPlan);
		}else if(date.getMonth() == 9 && estadoPlan.equals("A")){
			presupuestosPendientesOct++;
			totalPendienteOct= totalPendienteOct.add(totalPlan);
		}else if(date.getMonth() == 9 && (estadoPlan.equals("D") || estadoPlan.equals("F"))){
			presupuestosFacturadosOct++;
			totalFacturadoOct = totalFacturadoOct.add(totalPlan);
		}
		
		if(date.getMonth() == 10 && (estadoPlan.equals("N") || estadoPlan.equals("P"))){
			presupuestosCotizadosNov++;
			totalCotizadoNov = totalCotizadoNov.add(totalPlan);
		}else if(date.getMonth() == 10 && estadoPlan.equals("A")){
			presupuestosPendientesNov++;
			totalPendienteNov = totalPendienteNov.add(totalPlan);
		}else if(date.getMonth() == 10 && (estadoPlan.equals("D") || estadoPlan.equals("F"))){
			presupuestosFacturadosNov++;
			totalFacturadoNov = totalFacturadoNov.add(totalPlan);
		}
		
		if(date.getMonth() == 11 && (estadoPlan.equals("N") || estadoPlan.equals("P"))){
			presupuestosCotizadosDic++;
			totalCotizadoDic = totalCotizadoDic.add(totalPlan);
		}else if(date.getMonth() == 11 && estadoPlan.equals("A")){
			presupuestosPendientesDic++;
			totalPendienteDic = totalPendienteDic.add(totalPlan);
		}else if(date.getMonth() == 11 && (estadoPlan.equals("D") || estadoPlan.equals("F"))){
			presupuestosFacturadosDic++;
			totalFacturadoDic = totalFacturadoDic.add(totalPlan);
		}
	}	

	public void clean() {
		getCbFiltrarCodigoPresupuesto().setSelected(false);
		getTxtPresupuesto().setText("");
		getTxtPresupuesto().setEditable(false);
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		getCmbSubtipoProveedor().setSelectedItem(TODOS);
		getCmbEstado().setSelectedItem(TODOS);
		estado = TODOS;
		getCmbTipoProveedor().setSelectedItem(TODOS);
		tipoProveedor = TODOS;
		cleanTable();
		
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblInversion().getModel();
		for(int i= this.getTblInversion().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void obtenerOrdenesMedioPresupuestosDetalle(){
		try {
			boolean aprobadosFacturados = getCbAprobadosVsFacturados().isSelected()?true:false;
			
			//Veo si se desea buscar por código de presupuesto / pauta
			String codigoUnicoPresupuesto = "";
			if(getCbFiltrarCodigoPresupuesto().isSelected()){
				codigoUnicoPresupuesto = getTxtPresupuesto().getText().trim();
			}			
			
			Date fechaInicio = getCmbFechaInicio().getDate();
			Date fechaFin = getCmbFechaFin().getDate();
			//agruparBy sirve para el reporte de inversion de plan de medios
			String agruparBy = "";
			
			Timestamp timeInicio = Utilitarios.resetTimestampStartDate(new Timestamp(fechaInicio.getTime()));
			Timestamp timeFin =  Utilitarios.resetTimestampEndDate(new Timestamp(fechaFin.getTime()));
			
			boolean fechaAprobacion = true;
			if(getCmbEstado().getSelectedItem().equals("COTIZADO") 
					|| getCbReporteInversionTotales().isSelected() 
					|| getCbReporteComisionAdicional().isSelected()){
				fechaAprobacion = false;	
			}							
						
			//ORDENES MEDIO DETALLE
			if(!tipoProveedor.equals(NOMBRE_TIPO_PROVEEDOR_PRODUCCION)){
				Map<String,Long> mapaOrden = new LinkedHashMap<String, Long>();			
				if (clienteOficinaIf != null) 
					mapaOrden.put("clienteOficinaId", clienteOficinaIf.getId());			
				if (medioOficinaIf != null)
					mapaOrden.put("proveedorId", medioOficinaIf.getId());
				
				Map mapaGeneral = new LinkedHashMap();			
				if (marcaProductoIf != null && productoClienteIf == null)
					mapaGeneral.put("keyMarcaProductoId", marcaProductoIf.getId());
				if (productoClienteIf != null)
					mapaGeneral.put("keyProductoClienteId", productoClienteIf.getId());			
				if (subtipoProveedorIf != null && medioIf == null && medioOficinaIf == null)
					mapaGeneral.put("keyTipoProveedorId", subtipoProveedorIf.getId());
				
				if (clienteIf != null && clienteOficinaIf == null) 
					mapaGeneral.put("keyClienteId", clienteIf.getId());			
				if (medioIf != null && medioOficinaIf == null)
					mapaGeneral.put("keyProveedorId", medioIf.getId());
				
				if(getCbFiltrarCodigoPresupuesto().isSelected())
					mapaGeneral.put("keyCodigoUnicoPresupuesto", codigoUnicoPresupuesto);
							
				ordenesMedioDetalle = (ArrayList)SessionServiceLocator.getOrdenMedioSessionService().
										findOrdenMedioDetalleByQueryAndQueryGeneralByProductoAndByFechas(mapaOrden,mapaGeneral,tipoPauta,timeInicio,timeFin, fechaAprobacion, Parametros.getIdEmpresa(), estado, aprobadosFacturados, "", false, true);
				//ordenesMedioDetalle.clear();
			}
			
			//PRESUPUESTOS DETALLE
			Map mapaPresupuestoDetalle = new LinkedHashMap();			
			if (clienteOficinaIf != null) 
				mapaPresupuestoDetalle.put("clienteOficinaId", clienteOficinaIf.getId());			
			if (clienteIf != null && clienteOficinaIf == null) 
				mapaPresupuestoDetalle.put("clienteId", clienteIf.getId());			
			if (medioOficinaIf != null)
				mapaPresupuestoDetalle.put("proveedorOficinaId", medioOficinaIf.getId());
			if (medioIf != null && medioOficinaIf == null)
				mapaPresupuestoDetalle.put("proveedorId", medioIf.getId());
			if (productoClienteIf != null)
				mapaPresupuestoDetalle.put("productoClienteId", productoClienteIf.getId());
			if (marcaProductoIf != null && productoClienteIf == null)
				mapaPresupuestoDetalle.put("marcaProductoId", marcaProductoIf.getId());		
			if (subtipoProveedorIf != null && medioIf == null && medioOficinaIf == null)
				mapaPresupuestoDetalle.put("tipoProveedorId", subtipoProveedorIf.getId());
			
			if(getCbFiltrarCodigoPresupuesto().isSelected())
				mapaPresupuestoDetalle.put("codigoUnicoPresupuesto", codigoUnicoPresupuesto);
			
			presupuestosDetalle = (ArrayList)SessionServiceLocator.getPresupuestoSessionService().findPresupuestosDetalleByQueryByProductoAndByFechas(mapaPresupuestoDetalle, timeInicio,timeFin, fechaAprobacion, Parametros.getIdEmpresa(), agruparBy, estado, tipoProveedor, aprobadosFacturados, true, "", false);
			
			//para ver si hay presupuestos que no tienen ordenes
			presupuestosDetalleSinOrdenes = (ArrayList)SessionServiceLocator.getPresupuestoSessionService().findPresupuestosDetalleByQueryByProductoAndByFechasSinOrdenes(mapaPresupuestoDetalle, timeInicio,timeFin, fechaAprobacion, Parametros.getIdEmpresa(), agruparBy, estado, tipoProveedor, aprobadosFacturados, true);
			
			//agrego a presupuestosDetalle los detalles que no esten porque no tenian ordenes
			//hago la comparacion viendo si estan todos los presupuestosDetalleId
			Map<Long,Long> presupuestoDetalleIdMap = new HashMap<Long,Long>();
			for(int i=0; i<presupuestosDetalle.size(); i++){
				Object[] detalle = (Object[])presupuestosDetalle.get(i);
				Long presupuestoDetalleId = (Long)detalle[25];
				presupuestoDetalleIdMap.put(presupuestoDetalleId, presupuestoDetalleId);
			}
			for(int i=0; i<presupuestosDetalleSinOrdenes.size(); i++){
				Object[] detalle = (Object[])presupuestosDetalleSinOrdenes.get(i);
				Long presupuestoDetalleId = (Long)detalle[25];
				if(presupuestoDetalleIdMap.get(presupuestoDetalleId) == null){
					presupuestosDetalle.add(detalle);
				}
			}
			
			//presupuestosDetalle.clear();			
			System.out.println("a");
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	public void report() {
		try {
			DefaultTableModel tblModelReporte = (DefaultTableModel) getTblInversion().getModel();
			if (tblModelReporte.getRowCount() > 0) {				
				String si = "Si";
				String no = "No";
				Object[] options = {si,no};
				String mensaje = "¿Desea generar el reporte de Inversión?";
				int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if(opcion == JOptionPane.YES_OPTION) {
									
					String fileName = "jaspers/facturacion/RPFacturacionPresupuestos.jasper";
										
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("INVERSION CLIENTES").iterator().next();
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre().substring(0,1).concat(ciudad.getNombre().substring(1, ciudad.getNombre().length()).toLowerCase()));
					parametrosMap.put("usuario", Parametros.getUsuario());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)).toLowerCase() + " del " + year + ".-";
					parametrosMap.put("emitido", fechaEmision);					
					Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
					Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
					parametrosMap.put("fechaInicio", Utilitarios.getFechaCortaUppercase(fechaInicio));
					parametrosMap.put("fechaFin", Utilitarios.getFechaCortaUppercase(fechaFin));
					parametrosMap.put("filtro", "");
					
					//para el sumario									
					calcularTotalesSumario();					
					setearParametrosSumario(parametrosMap);
					parametrosMap.put("facturas", facturas);
					parametrosMap.put("cantidadFacturas", cantidadFacturas);
					parametrosMap.put("ordenes", ordenes);
					parametrosMap.put("cantidadOrdenes", cantidadOrdenes);
					parametrosMap.put("compras", compras);
					parametrosMap.put("cantidadCompras", cantidadCompras);
					
					ReportModelImpl.processReport(fileName, parametrosMap, inversionClientesDataVector, true);					
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
		catch (ParseException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
	}

	private void setearParametrosSumario(HashMap parametrosMap) {
		parametrosMap.put("presupuestosCotizados", String.valueOf(presupuestosCotizados));
		parametrosMap.put("presupuestosAprobados", String.valueOf(presupuestosFacturados+presupuestosPendientes));
		parametrosMap.put("presupuestosFacturados", String.valueOf(presupuestosFacturados));
		parametrosMap.put("presupuestosPendientes", String.valueOf(presupuestosPendientes));
		parametrosMap.put("totalCotizado", formatoDecimal.format(totalCotizado));					
		parametrosMap.put("totalAprobado", formatoDecimal.format(totalFacturado.add(totalPendiente)));
		parametrosMap.put("totalFacturado", formatoDecimal.format(totalFacturado));
		parametrosMap.put("totalPendiente", formatoDecimal.format(totalPendiente));
		
		parametrosMap.put("presupuestosCotizadosEne", String.valueOf(presupuestosCotizadosEne));
		parametrosMap.put("presupuestosAprobadosEne", String.valueOf(presupuestosFacturadosEne+presupuestosPendientesEne));
		parametrosMap.put("presupuestosFacturadosEne", String.valueOf(presupuestosFacturadosEne));
		parametrosMap.put("presupuestosPendientesEne", String.valueOf(presupuestosPendientesEne));
		parametrosMap.put("totalCotizadoEne", formatoDecimal.format(totalCotizadoEne));					
		parametrosMap.put("totalAprobadoEne", formatoDecimal.format(totalFacturadoEne.add(totalPendienteEne)));
		parametrosMap.put("totalFacturadoEne", formatoDecimal.format(totalFacturadoEne));
		parametrosMap.put("totalPendienteEne", formatoDecimal.format(totalPendienteEne));
		
		parametrosMap.put("presupuestosCotizadosFeb", String.valueOf(presupuestosCotizadosFeb));
		parametrosMap.put("presupuestosAprobadosFeb", String.valueOf(presupuestosFacturadosFeb+presupuestosPendientesFeb));
		parametrosMap.put("presupuestosFacturadosFeb", String.valueOf(presupuestosFacturadosFeb));
		parametrosMap.put("presupuestosPendientesFeb", String.valueOf(presupuestosPendientesFeb));
		parametrosMap.put("totalCotizadoFeb", formatoDecimal.format(totalCotizadoFeb));					
		parametrosMap.put("totalAprobadoFeb", formatoDecimal.format(totalFacturadoFeb.add(totalPendienteFeb)));
		parametrosMap.put("totalFacturadoFeb", formatoDecimal.format(totalFacturadoFeb));
		parametrosMap.put("totalPendienteFeb", formatoDecimal.format(totalPendienteFeb));
		
		parametrosMap.put("presupuestosCotizadosMar", String.valueOf(presupuestosCotizadosMar));
		parametrosMap.put("presupuestosAprobadosMar", String.valueOf(presupuestosFacturadosMar+presupuestosPendientesMar));
		parametrosMap.put("presupuestosFacturadosMar", String.valueOf(presupuestosFacturadosMar));
		parametrosMap.put("presupuestosPendientesMar", String.valueOf(presupuestosPendientesMar));
		parametrosMap.put("totalCotizadoMar", formatoDecimal.format(totalCotizadoMar));					
		parametrosMap.put("totalAprobadoMar", formatoDecimal.format(totalFacturadoMar.add(totalPendienteMar)));
		parametrosMap.put("totalFacturadoMar", formatoDecimal.format(totalFacturadoMar));
		parametrosMap.put("totalPendienteMar", formatoDecimal.format(totalPendienteMar));
		
		parametrosMap.put("presupuestosCotizadosAbr", String.valueOf(presupuestosCotizadosAbr));
		parametrosMap.put("presupuestosAprobadosAbr", String.valueOf(presupuestosFacturadosAbr+presupuestosPendientesAbr));
		parametrosMap.put("presupuestosFacturadosAbr", String.valueOf(presupuestosFacturadosAbr));
		parametrosMap.put("presupuestosPendientesAbr", String.valueOf(presupuestosPendientesAbr));
		parametrosMap.put("totalCotizadoAbr", formatoDecimal.format(totalCotizadoAbr));					
		parametrosMap.put("totalAprobadoAbr", formatoDecimal.format(totalFacturadoAbr.add(totalPendienteAbr)));
		parametrosMap.put("totalFacturadoAbr", formatoDecimal.format(totalFacturadoAbr));
		parametrosMap.put("totalPendienteAbr", formatoDecimal.format(totalPendienteAbr));
		
		parametrosMap.put("presupuestosCotizadosMay", String.valueOf(presupuestosCotizadosMay));
		parametrosMap.put("presupuestosAprobadosMay", String.valueOf(presupuestosFacturadosMay+presupuestosPendientesMay));
		parametrosMap.put("presupuestosFacturadosMay", String.valueOf(presupuestosFacturadosMay));
		parametrosMap.put("presupuestosPendientesMay", String.valueOf(presupuestosPendientesMay));
		parametrosMap.put("totalCotizadoMay", formatoDecimal.format(totalCotizadoMay));					
		parametrosMap.put("totalAprobadoMay", formatoDecimal.format(totalFacturadoMay.add(totalPendienteMay)));
		parametrosMap.put("totalFacturadoMay", formatoDecimal.format(totalFacturadoMay));
		parametrosMap.put("totalPendienteMay", formatoDecimal.format(totalPendienteMay));
		
		parametrosMap.put("presupuestosCotizadosJun", String.valueOf(presupuestosCotizadosJun));
		parametrosMap.put("presupuestosAprobadosJun", String.valueOf(presupuestosFacturadosJun+presupuestosPendientesJun));
		parametrosMap.put("presupuestosFacturadosJun", String.valueOf(presupuestosFacturadosJun));
		parametrosMap.put("presupuestosPendientesJun", String.valueOf(presupuestosPendientesJun));
		parametrosMap.put("totalCotizadoJun", formatoDecimal.format(totalCotizadoJun));					
		parametrosMap.put("totalAprobadoJun", formatoDecimal.format(totalFacturadoJun.add(totalPendienteJun)));
		parametrosMap.put("totalFacturadoJun", formatoDecimal.format(totalFacturadoJun));
		parametrosMap.put("totalPendienteJun", formatoDecimal.format(totalPendienteJun));
		
		parametrosMap.put("presupuestosCotizadosJul", String.valueOf(presupuestosCotizadosJul));
		parametrosMap.put("presupuestosAprobadosJul", String.valueOf(presupuestosFacturadosJul+presupuestosPendientesJul));
		parametrosMap.put("presupuestosFacturadosJul", String.valueOf(presupuestosFacturadosJul));
		parametrosMap.put("presupuestosPendientesJul", String.valueOf(presupuestosPendientesJul));
		parametrosMap.put("totalCotizadoJul", formatoDecimal.format(totalCotizadoJul));					
		parametrosMap.put("totalAprobadoJul", formatoDecimal.format(totalFacturadoJul.add(totalPendienteJul)));
		parametrosMap.put("totalFacturadoJul", formatoDecimal.format(totalFacturadoJul));
		parametrosMap.put("totalPendienteJul", formatoDecimal.format(totalPendienteJul));
		
		parametrosMap.put("presupuestosCotizadosAgo", String.valueOf(presupuestosCotizadosAgo));
		parametrosMap.put("presupuestosAprobadosAgo", String.valueOf(presupuestosFacturadosAgo+presupuestosPendientesAgo));
		parametrosMap.put("presupuestosFacturadosAgo", String.valueOf(presupuestosFacturadosAgo));
		parametrosMap.put("presupuestosPendientesAgo", String.valueOf(presupuestosPendientesAgo));
		parametrosMap.put("totalCotizadoAgo", formatoDecimal.format(totalCotizadoAgo));					
		parametrosMap.put("totalAprobadoAgo", formatoDecimal.format(totalFacturadoAgo.add(totalPendienteAgo)));
		parametrosMap.put("totalFacturadoAgo", formatoDecimal.format(totalFacturadoAgo));
		parametrosMap.put("totalPendienteAgo", formatoDecimal.format(totalPendienteAgo));
		
		parametrosMap.put("presupuestosCotizadosSep", String.valueOf(presupuestosCotizadosSep));
		parametrosMap.put("presupuestosAprobadosSep", String.valueOf(presupuestosFacturadosSep+presupuestosPendientesSep));
		parametrosMap.put("presupuestosFacturadosSep", String.valueOf(presupuestosFacturadosSep));
		parametrosMap.put("presupuestosPendientesSep", String.valueOf(presupuestosPendientesSep));
		parametrosMap.put("totalCotizadoSep", formatoDecimal.format(totalCotizadoSep));					
		parametrosMap.put("totalAprobadoSep", formatoDecimal.format(totalFacturadoSep.add(totalPendienteSep)));
		parametrosMap.put("totalFacturadoSep", formatoDecimal.format(totalFacturadoSep));
		parametrosMap.put("totalPendienteSep", formatoDecimal.format(totalPendienteSep));
		
		parametrosMap.put("presupuestosCotizadosOct", String.valueOf(presupuestosCotizadosOct));
		parametrosMap.put("presupuestosAprobadosOct", String.valueOf(presupuestosFacturadosOct+presupuestosPendientesOct));
		parametrosMap.put("presupuestosFacturadosOct", String.valueOf(presupuestosFacturadosOct));
		parametrosMap.put("presupuestosPendientesOct", String.valueOf(presupuestosPendientesOct));
		parametrosMap.put("totalCotizadoOct", formatoDecimal.format(totalCotizadoOct));					
		parametrosMap.put("totalAprobadoOct", formatoDecimal.format(totalFacturadoOct.add(totalPendienteOct)));
		parametrosMap.put("totalFacturadoOct", formatoDecimal.format(totalFacturadoOct));
		parametrosMap.put("totalPendienteOct", formatoDecimal.format(totalPendienteOct));
		
		parametrosMap.put("presupuestosCotizadosNov", String.valueOf(presupuestosCotizadosNov));
		parametrosMap.put("presupuestosAprobadosNov", String.valueOf(presupuestosFacturadosNov+presupuestosPendientesNov));
		parametrosMap.put("presupuestosFacturadosNov", String.valueOf(presupuestosFacturadosNov));
		parametrosMap.put("presupuestosPendientesNov", String.valueOf(presupuestosPendientesNov));
		parametrosMap.put("totalCotizadoNov", formatoDecimal.format(totalCotizadoNov));					
		parametrosMap.put("totalAprobadoNov", formatoDecimal.format(totalFacturadoNov.add(totalPendienteNov)));
		parametrosMap.put("totalFacturadoNov", formatoDecimal.format(totalFacturadoNov));
		parametrosMap.put("totalPendienteNov", formatoDecimal.format(totalPendienteNov));
		
		parametrosMap.put("presupuestosCotizadosDic", String.valueOf(presupuestosCotizadosDic));
		parametrosMap.put("presupuestosAprobadosDic", String.valueOf(presupuestosFacturadosDic+presupuestosPendientesDic));
		parametrosMap.put("presupuestosFacturadosDic", String.valueOf(presupuestosFacturadosDic));
		parametrosMap.put("presupuestosPendientesDic", String.valueOf(presupuestosPendientesDic));
		parametrosMap.put("totalCotizadoDic", formatoDecimal.format(totalCotizadoDic));					
		parametrosMap.put("totalAprobadoDic", formatoDecimal.format(totalFacturadoDic.add(totalPendienteDic)));
		parametrosMap.put("totalFacturadoDic", formatoDecimal.format(totalFacturadoDic));
		parametrosMap.put("totalPendienteDic", formatoDecimal.format(totalPendienteDic));
	}
	
	public void calcularTotalesSumario(){
		presupuestosCotizados = presupuestosCotizadosEne + presupuestosCotizadosFeb + presupuestosCotizadosMar +
		presupuestosCotizadosAbr + presupuestosCotizadosMay + presupuestosCotizadosJun + presupuestosCotizadosJul +
		presupuestosCotizadosAgo + presupuestosCotizadosSep + presupuestosCotizadosOct + presupuestosCotizadosNov +
		presupuestosCotizadosDic;
		
		presupuestosFacturados = presupuestosFacturadosEne + presupuestosFacturadosFeb + presupuestosFacturadosMar +
		presupuestosFacturadosAbr + presupuestosFacturadosMay + presupuestosFacturadosJun + presupuestosFacturadosJul +
		presupuestosFacturadosAgo + presupuestosFacturadosSep + presupuestosFacturadosOct + presupuestosFacturadosNov +
		presupuestosFacturadosDic;
		
		presupuestosPendientes = presupuestosPendientesEne + presupuestosPendientesFeb + presupuestosPendientesMar +
		presupuestosPendientesAbr + presupuestosPendientesMay + presupuestosPendientesJun + presupuestosPendientesJul +
		presupuestosPendientesAgo + presupuestosPendientesSep + presupuestosPendientesOct + presupuestosPendientesNov +
		presupuestosPendientesDic;
		
		totalCotizado = totalCotizadoEne.add(totalCotizadoFeb).add(totalCotizadoMar).add(totalCotizadoAbr).add(totalCotizadoMay)
		.add(totalCotizadoJun).add(totalCotizadoJul).add(totalCotizadoAgo).add(totalCotizadoSep).add(totalCotizadoOct)
		.add(totalCotizadoNov).add(totalCotizadoDic);
		
		totalFacturado = totalFacturadoEne.add(totalFacturadoFeb).add(totalFacturadoMar).add(totalFacturadoAbr).add(totalFacturadoMay)
		.add(totalFacturadoJun).add(totalFacturadoJul).add(totalFacturadoAgo).add(totalFacturadoSep).add(totalFacturadoOct)
		.add(totalFacturadoNov).add(totalFacturadoDic);
		
		totalPendiente = totalPendienteEne.add(totalPendienteFeb).add(totalPendienteMar).add(totalPendienteAbr).add(totalPendienteMay)
		.add(totalPendienteJun).add(totalPendienteJul).add(totalPendienteAgo).add(totalPendienteSep).add(totalPendienteOct)
		.add(totalPendienteNov).add(totalPendienteDic);
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDetail() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		cargarMapas();
	}

	@Override
	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}

	@Override
	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
}
