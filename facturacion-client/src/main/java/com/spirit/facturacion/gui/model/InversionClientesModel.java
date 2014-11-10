package com.spirit.facturacion.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.gui.panel.JPInversionClientes;
import com.spirit.facturacion.gui.reporteData.InversionClientesData;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
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
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;


public class InversionClientesModel extends JPInversionClientes {

	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String TODOS = "TODOS";
	private static final String NOMBRE_TIPO_PROVEEDOR_MEDIOS = "MEDIOS";
	private static final String NOMBRE_TIPO_PROVEEDOR_PRODUCCION = "PRODUCCION";
	private static final String TIPO_PROVEEDOR_MEDIOS = "M";
	private static final String PAUTA_REGULAR_AUSPICIO = "B";
	protected String tipoPauta = PAUTA_REGULAR_AUSPICIO;
	private static final String PLAN_MEDIO_ESTADO_EN_PROCESO = "N";
	private static final String PLAN_MEDIO_ESTADO_PENDIENTE = "P";
	private static final String PRESUPUESTO_ESTADO_COTIZADO = "T";
	private static final String PRESUPUESTO_ESTADO_PENDIENTE = "P";
	protected ClienteOficinaIf clienteOficinaIf;
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private ArrayList<Object[]> ordenesMedioDetalle = new ArrayList<Object[]>();
	private ArrayList presupuestosDetalle = new ArrayList();
	
	protected ClienteOficinaIf medioOficinaIf = null;
	protected MarcaProductoIf marcaProductoIf = null;
	protected TipoProveedorIf tipoProveedorIf = null;
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
		
	ArrayList<InversionClientesData> inversionClientesDataVector = new ArrayList<InversionClientesData>();
	
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
	
	public InversionClientesModel(){
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
		cargarComboTipoMedio();
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
		anchoColumna.setPreferredWidth(120);
		anchoColumna = getTblInversion().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(80);		
		anchoColumna = getTblInversion().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblInversion().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblInversion().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(180);
		anchoColumna = getTblInversion().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(180);
		anchoColumna = getTblInversion().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblInversion().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblInversion().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblInversion().getColumnModel().getColumn(11);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblInversion().getColumnModel().getColumn(12);
		anchoColumna.setPreferredWidth(80);
	}
	
	public void cargarComboSegmento(){
		
	}
	
	public void cargarComboProducto(){
		
	}
	
	private void cargarComboTipoMedio(){
		try {
			List tiposMedios = new ArrayList();
						
			Iterator it = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByTipo(TIPO_PROVEEDOR_MEDIOS).iterator();
			while (it.hasNext()) {
				TipoProveedorIf tipoProveedorMedio = (TipoProveedorIf) it.next();
				tiposMedios.add(tipoProveedorMedio);
			}
			
			tiposMedios.add(TODOS);
			refreshCombo(getCmbTipoMedio(),tiposMedios);
			getCmbTipoMedio().setSelectedItem(TODOS);
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	public void initKeyListeners(){
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
		getBtnConsultar().setToolTipText("Consultar");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblInversion().getColumnModel().getColumn(10).setCellRenderer(tableCellRenderer);
		getTblInversion().getColumnModel().getColumn(11).setCellRenderer(tableCellRenderer);
		getTblInversion().getColumnModel().getColumn(12).setCellRenderer(tableCellRenderer);
	}
	
	private void initListeners() {
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cargarTabla();
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
		
		getCmbTipoMedio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoMedio().getSelectedItem() != null && !getCmbTipoMedio().getSelectedItem().equals(TODOS)){
					tipoProveedorIf = (TipoProveedorIf) getCmbTipoMedio().getSelectedItem();
				}
				else{
					tipoProveedorIf = null;
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
						getLblFechasAprobacion().setVisible(true);
					}else if(getCmbEstado().getSelectedItem().equals("FACTURADO")){
						estado = "FACTURADO";
						getCbAprobadosVsFacturados().setEnabled(false);
						getCbAprobadosVsFacturados().setSelected(false);
						getLblFechasAprobacion().setVisible(true);
					}else if(getCmbEstado().getSelectedItem().equals("PREPAGADO")){
						estado = "PREPAGADO";
						getCbAprobadosVsFacturados().setEnabled(false);
						getCbAprobadosVsFacturados().setSelected(false);
						getLblFechasAprobacion().setVisible(true);
					}else{
						estado = TODOS;
						getLblFechasAprobacion().setVisible(true);
						getCbAprobadosVsFacturados().setEnabled(true);
					}
				}
				else{
					estado = TODOS;
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
	}
	
	Comparator<InversionClientesData> ordenadorInversionClientesDataPorClienteOficina = new Comparator<InversionClientesData>(){
		public int compare(InversionClientesData o1, InversionClientesData o2) {
			return o1.getClienteOficina().compareTo(o2.getClienteOficina());			
		}		
	};
	
	Comparator<InversionClientesData> ordenadorInversionClientesDataPorDate = new Comparator<InversionClientesData>(){
		public int compare(InversionClientesData o1, InversionClientesData o2) {
			return o1.getDate().compareTo(o2.getDate());			
		}		
	};
	
	Comparator<InversionClientesData> ordenadorInversionClientesDataPorPresupuesto = new Comparator<InversionClientesData>(){
		public int compare(InversionClientesData o1, InversionClientesData o2) {
			return o1.getCodigoPresupuesto().compareTo(o2.getCodigoPresupuesto());			
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
	
	private void cargarTabla() {
		cleanTable();
		ordenesMedioDetalle = new ArrayList<Object[]>();
		presupuestosDetalle = new ArrayList();
		obtenerOrdenesMedioPresupuestosDetalle();
		llenarVectorInversionClientesData();
		
		//ordenar vector
		Collections.sort((ArrayList)inversionClientesDataVector,ordenadorInversionClientesDataPorProducto);
		Collections.sort((ArrayList)inversionClientesDataVector,ordenadorInversionClientesDataPorSegmento);
		Collections.sort((ArrayList)inversionClientesDataVector,ordenadorInversionClientesDataPorPresupuesto);
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
	}
	
	private void agregarColumnasTabla(InversionClientesData inversionClientesData, Vector<String> fila){
		fila.add(inversionClientesData.getClienteOficina());
		fila.add(inversionClientesData.getFechaFactura());
		fila.add(inversionClientesData.getFactura());
		fila.add(inversionClientesData.getFechaAprobacion());
		fila.add(inversionClientesData.getCodigoPresupuesto());
		fila.add(inversionClientesData.getSap());
		fila.add(inversionClientesData.getSegmento());
		fila.add(inversionClientesData.getProducto());
		fila.add(inversionClientesData.getMedio());
		fila.add(inversionClientesData.getProveedor());
		fila.add(inversionClientesData.getValor());
		fila.add(inversionClientesData.getIva());
		fila.add(inversionClientesData.getTotal());
	}
	
	public void reiniciarValores(){
		inversionClientesDataVector = new ArrayList<InversionClientesData>();
		
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
				
				cargarInversionClientesDataVectorMedios(ordenesMedioDetallePlan, planMedio);				
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
					presupuestosDetalleDelPresupuesto.add(presupuestoDetalle);
					mapaPresupuestoPresupuestoDetalleVector.put(presupuesto.getId(), presupuestosDetalleDelPresupuesto);
				}				
			}
			
			Iterator mapaPresupuestoPresupuestoDetalleVectorIt =  mapaPresupuestoPresupuestoDetalleVector.keySet().iterator();
			while(mapaPresupuestoPresupuestoDetalleVectorIt.hasNext()){
				Long presupuestoId = (Long)mapaPresupuestoPresupuestoDetalleVectorIt.next();
				PresupuestoIf presupuestoIf = mapaPresupuesto.get(presupuestoId);
				Vector<Object> presupuestosDetallePresupuesto = mapaPresupuestoPresupuestoDetalleVector.get(presupuestoId);
								
				cargarInversionClientesDataVectorPresupuestos(presupuestosDetallePresupuesto, presupuestoIf);								
			}					
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void cargarInversionClientesDataVectorPresupuestos(
			Vector<Object> presupuestosDetalleDelPresupuesto, PresupuestoIf presupuestoIf)
			throws GenericBusinessException {
		
		String clienteId = "";
		String clienteOficina = "";
		String fechaFactura = "";
		String factura = "";
		String fechaAprobacion = "";
		String presupuesto = "";
		String sap = "";
		String segmento = "";
		String producto = "";
		String medio = "";
		String proveedor = "";
		String valor = "";
		String iva = "";
		String total = "";
		
		Calendar calendarInicio = new GregorianCalendar();
		Timestamp date = new Timestamp(calendarInicio.getTimeInMillis());
		
		//OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuestoIf.getOrdentrabajodetId());
		//OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
		//ClienteOficinaIf clienteOficinaIf = mapaClienteOficina.get(ordenTrabajo.getClienteoficinaId());
		
		//En la posicion 1 del arreglo esta el cliente oficina Id
		Long clienteOficinaId = (Long)((Object[])presupuestosDetalleDelPresupuesto.get(0))[1];
		ClienteOficinaIf clienteOficinaIf = mapaClienteOficina.get(clienteOficinaId);
		clienteOficina = clienteOficinaIf.getDescripcion();
		ClienteIf cliente = mapaCliente.get(clienteOficinaIf.getClienteId());
		clienteId = cliente.getId().toString();
						
		//facturas
		/*Map mapaPedido = new HashMap();
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
				//si no esta anulada
				if(!facturaIf.getEstado().equals("A")){
					//se hace esta validacion porque del presupuesto tambien pueden salir facturas a los proveedores
					if(facturaIf.getClienteoficinaId().compareTo(clienteOficinaIf.getId()) == 0)
						facturasDelPlan.add(facturaIf);
				}						
			}
		}*/
		
		//facturas
		//Vector<FacturaIf> facturasDelPlan = new Vector<FacturaIf>();
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
				//si no esta anulada
				if(facturaIf != null && !facturaIf.getEstado().equals("A")){
					//se hace esta validacion porque del presupuesto tambien pueden salir facturas a los proveedores
					if(facturaIf.getClienteoficinaId().compareTo(clienteOficinaIf.getId()) == 0 || getCbVerFacturas().isSelected())
						facturasMap.put(facturaIf.getId(), facturaIf);		
				}
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
					if(facturaIf != null && !facturaIf.getEstado().equals("A")){
						//se hace esta validacion porque del presupuesto tambien pueden salir facturas a los proveedores
						if(facturaIf.getClienteoficinaId().compareTo(clienteOficinaIf.getId()) == 0 || getCbVerFacturas().isSelected())
							facturasMap.put(facturaIf.getId(), facturaIf);
					}else{
						System.out.println("ERROR NO HAY FACTURA");
					}					
				}				
			}
		}
		
		Iterator facturasMapIt = facturasMap.keySet().iterator();
		while(facturasMapIt.hasNext()){
			Long facturaId = (Long)facturasMapIt.next();
			FacturaIf facturaDelPlan = facturasMap.get(facturaId);
		
			if(factura.equals("")){
				fechaFactura = Utilitarios.getFechaCortaUppercase(facturaDelPlan.getFechaFactura());
				factura = facturaDelPlan.getPreimpresoNumero()!=null?facturaDelPlan.getPreimpresoNumero():"FALTA PREIMPRESO";
				
			}else if(getCbVerFacturas().isSelected()){
				fechaFactura = fechaFactura + "\n" + Utilitarios.getFechaCortaUppercase(facturaDelPlan.getFechaFactura());
				factura = factura + "\n" + facturaDelPlan.getPreimpresoNumero();
			}
			
			if(sap.equals("") && facturaDelPlan.getAutorizacionSap() != null){
				sap = facturaDelPlan.getAutorizacionSap();
			}else if(facturaDelPlan.getAutorizacionSap() != null){
				sap = sap + "\n" + facturaDelPlan.getAutorizacionSap();
			}
		}
		
		//si no se seteo sap en la factura busco si hay en el presupuesto
		if(sap.equals("") && presupuestoIf.getAutorizacionSap() != null){
			sap = presupuestoIf.getAutorizacionSap();
		}else if(sap.equals("") && cliente.getRequiereSap().equals("S")){
			sap = "PENDIENTE";
		}
				
		//si es presupuesto PREPAGADO
		if(presupuestoIf.getEstado().equals("R")){
			factura = "PREPAGADO";
		}	
		//si no hay factura se setea el campo como PENDIENTE
		else if(factura == null || factura.equals("")){
			factura = "PENDIENTE";
		}
		
		if(presupuestoIf.getFechaAceptacion() != null && !getCmbEstado().getSelectedItem().equals("COTIZADO")){
			fechaAprobacion = Utilitarios.getFechaCortaUppercase(presupuestoIf.getFechaAceptacion());
			date = presupuestoIf.getFechaAceptacion();
			
		}else{
			fechaAprobacion = Utilitarios.getFechaCortaUppercase(presupuestoIf.getFecha());
			date = presupuestoIf.getFecha();			
		}
		
		//mes = Utilitarios.getMesesMayusculas()[presupuestoIf.getFecha().getMonth()];
		presupuesto = presupuestoIf.getCodigo();
		
		if(sap.equals("") && presupuestoIf.getAutorizacionSap() != null){
			sap = presupuestoIf.getAutorizacionSap();
		}
						
		//Vector del presupuestos detalle del Presupuesto actual
		
		Map<Long,String> marcaMapa = new HashMap<Long,String>();
		Map<Long,String> productoMapa = new HashMap<Long,String>();
		Map<Long,String> tipoMedioMapa = new HashMap<Long,String>();
		Map<Long,String> medioOficinaMapa = new HashMap<Long,String>();
		
		for(int i = 0; i < presupuestosDetalleDelPresupuesto.size(); i++){
			Object[] presupuestoDetalle = (Object[])presupuestosDetalleDelPresupuesto.get(i);
		
			Long marcaId = (Long)presupuestoDetalle[14];
			Long productoClienteId = (Long)presupuestoDetalle[5];					
			Long tipoMedioId = (Long)presupuestoDetalle[7];
			Long medioOficinaId = (Long)presupuestoDetalle[3];
			
			String marca = (String)presupuestoDetalle[13];
			String productoCliente = (String)presupuestoDetalle[4];
			String tipoMedio = (String)presupuestoDetalle[6];
			String medioOficina = (String)presupuestoDetalle[2];
								
			marcaMapa.put(marcaId, marca);
			productoMapa.put(productoClienteId, productoCliente);
			tipoMedioMapa.put(tipoMedioId, tipoMedio);
			medioOficinaMapa.put(medioOficinaId, medioOficina);					
		}
		
		//segmento
		Iterator marcaMapaIt = marcaMapa.keySet().iterator();
		while(marcaMapaIt.hasNext()){
			Long marcaId = (Long)marcaMapaIt.next();
			if(segmento.equals("")){
				segmento = marcaMapa.get(marcaId);
			}else{
				segmento = segmento + "\n" + marcaMapa.get(marcaId);
			}
		}
		
		//producto
		Iterator productoMapaIt = productoMapa.keySet().iterator();
		while(productoMapaIt.hasNext()){
			Long productoId = (Long)productoMapaIt.next();
			if(producto.equals("")){
				producto = productoMapa.get(productoId);
			}else{
				producto = producto + "\n" + productoMapa.get(productoId);
			}
		}
		
		//medio
		Iterator tipoMedioMapaIt = tipoMedioMapa.keySet().iterator();
		while(tipoMedioMapaIt.hasNext()){
			Long tipoMedioId = (Long)tipoMedioMapaIt.next();
			if(medio.equals("")){
				medio = tipoMedioMapa.get(tipoMedioId);
			}else{
				medio = medio + "\n" + tipoMedioMapa.get(tipoMedioId);
			}
		}
		
		//proveedor
		Iterator medioOficinaMapaIt = medioOficinaMapa.keySet().iterator();
		while(medioOficinaMapaIt.hasNext()){
			Long medioOficinaId = (Long)medioOficinaMapaIt.next();
			if(proveedor.equals("")){
				proveedor = medioOficinaMapa.get(medioOficinaId);
			}else if(getCbVerProveedores().isSelected()){
				proveedor = proveedor + "\n" + medioOficinaMapa.get(medioOficinaId);
			}
		}
		
		BigDecimal subtotal = presupuestoIf.getValorbruto();
		BigDecimal descuentoVenta = presupuestoIf.getDescuento();
		BigDecimal porcentajeComisionAgencia = presupuestoIf.getPorcentajeComisionAgencia();
		BigDecimal descuentosVarios = presupuestoIf.getDescuentosVarios();
		BigDecimal descuentoEspecial = presupuestoIf.getDescuentoEspecial();
		
		//BigDecimal comisionAgencia = (subtotal.subtract(descuentoVenta)).multiply(porcentajeComisionAgencia.divide(new BigDecimal(100)));
		//BigDecimal subTotal = subtotal.subtract(descuentoEspecial).subtract(descuentoVenta).subtract(descuentosVarios).add(comisionAgencia);
		BigDecimal subtotal2 = subtotal.subtract(descuentoEspecial).subtract(descuentoVenta).subtract(descuentosVarios);
		BigDecimal comisionAgenciaPresupuesto = subtotal2.multiply(porcentajeComisionAgencia.divide(new BigDecimal(100)));
		BigDecimal subTotal = subtotal.subtract(descuentoEspecial).subtract(descuentoVenta).subtract(descuentosVarios).add(comisionAgenciaPresupuesto);
		
		valor = formatoDecimal.format(subTotal);
		iva = formatoDecimal.format(presupuestoIf.getIva());
		total = formatoDecimal.format(presupuestoIf.getValor());
		
		//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'				
		String estadoPresupuesto = presupuestoIf.getEstado();
				
		setearTotalesPresupuestoMes(presupuestoIf, date, estadoPresupuesto);
		
		InversionClientesData inversionClientesVector = new InversionClientesData();
		inversionClientesVector.setClienteId(clienteId);
		inversionClientesVector.setClienteOficina(clienteOficina);
		inversionClientesVector.setFechaFactura(fechaFactura);
		inversionClientesVector.setFactura(factura);
		inversionClientesVector.setFechaAprobacion(fechaAprobacion);
		inversionClientesVector.setCodigoPresupuesto(presupuesto);
		inversionClientesVector.setSap(sap);
		inversionClientesVector.setSegmento(segmento);
		inversionClientesVector.setProducto(producto);
		inversionClientesVector.setMedio(medio);
		inversionClientesVector.setProveedor(proveedor);
		inversionClientesVector.setValor(valor);
		inversionClientesVector.setIva(iva);
		inversionClientesVector.setTotal(total);
		inversionClientesVector.setDate(date);
		inversionClientesDataVector.add(inversionClientesVector);
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

	private void cargarInversionClientesDataVectorMedios(
			Vector<Object[]> ordenesMedioDetalleDelPlan, PlanMedioIf planMedio)
			throws GenericBusinessException {
		
		String clienteId = "";
		String clienteOficina = "";
		String fechaFactura = "";
		String factura = "";
		String fechaAprobacion = "";
		String presupuesto = "";
		String sap = "";
		String segmento = "";
		String producto = "";
		String medio = "";
		String proveedor = "";
		String valor = "";
		String iva = "";
		String total = "";
		
		Calendar calendarInicio = new GregorianCalendar();
		Timestamp date = new Timestamp(calendarInicio.getTimeInMillis());				
		
		//OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(planMedio.getOrdenTrabajoDetalleId());
		//OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
		//ClienteOficinaIf clienteOficinaIf = mapaClienteOficina.get(ordenTrabajo.getClienteoficinaId());
		
		//Todas las ordenes de medios del vector van a tener el mismo cliente oficina
		Long clienteOficinaId = ((OrdenMedioIf)ordenesMedioDetalleDelPlan.get(0)[1]).getClienteOficinaId();
		ClienteOficinaIf clienteOficinaIf = mapaClienteOficina.get(clienteOficinaId);		
		clienteOficina = clienteOficinaIf.getDescripcion();				
		ClienteIf cliente = mapaCliente.get(clienteOficinaIf.getClienteId());
		clienteId = cliente.getId().toString();
		
		//facturas
		Vector<FacturaIf> facturasDelPlan = new Vector<FacturaIf>();
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
					if(facturaIf.getClienteoficinaId().compareTo(clienteOficinaIf.getId()) == 0)
						facturasDelPlan.add(facturaIf);
				}						
			}
		}*/
		
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
					if(facturaIf.getClienteoficinaId().compareTo(clienteOficinaIf.getId()) == 0 || getCbVerFacturas().isSelected())
						facturasDelPlan.add(facturaIf);
				}						
			}
		}
		
		for(FacturaIf facturaDelPlan : facturasDelPlan){
			if(factura.equals("")){
				fechaFactura = Utilitarios.getFechaCortaUppercase(facturaDelPlan.getFechaFactura());
				factura = facturaDelPlan.getPreimpresoNumero()!=null?facturaDelPlan.getPreimpresoNumero():"FALTA PREIMPRESO";
			}else if(getCbVerFacturas().isSelected()){
				fechaFactura = fechaFactura + "\n" + Utilitarios.getFechaCortaUppercase(facturaDelPlan.getFechaFactura());
				factura = factura + "\n" + facturaDelPlan.getPreimpresoNumero();
			}
			
			if(sap.equals("") && facturaDelPlan.getAutorizacionSap() != null){
				sap = facturaDelPlan.getAutorizacionSap();
			}else if(facturaDelPlan.getAutorizacionSap() != null){
				sap = sap + "\n" + facturaDelPlan.getAutorizacionSap();
			}					
		}
		
		//si no se seteo sap en la factura busco si hay en el presupuesto
		if(sap.equals("") && planMedio.getAutorizacionSap() != null){
			sap = planMedio.getAutorizacionSap();
		}else if(sap.equals("") && cliente.getRequiereSap().equals("S")){
			sap = "PENDIENTE";
		}
		
		//si es presupuesto PREPAGADO
		if(planMedio.getEstado().equals("R")){
			factura = "PREPAGADO";
		}
		//si no hay factura se setea el campo como PENDIENTE
		else if(factura.equals("")){
			factura = "PENDIENTE";
		}
		
		if(planMedio.getFechaAprobacion() != null && !getCmbEstado().getSelectedItem().equals("COTIZADO")){
			fechaAprobacion = Utilitarios.getFechaCortaUppercase(planMedio.getFechaAprobacion());
			date = planMedio.getFechaAprobacion();
		}else{
			fechaAprobacion = Utilitarios.getFechaCortaUppercase(planMedio.getFechaInicio());
			date = planMedio.getFechaInicio();
		}
		//mes = Utilitarios.getMesesMayusculas()[planMedio.getFechaInicio().getMonth()];
		presupuesto = planMedio.getCodigo();
		
		if(sap.equals("") && planMedio.getAutorizacionSap() != null){
			sap = planMedio.getAutorizacionSap();
		}
		
		Map<Long,Long> campanaProductoVersionIdMapa = new HashMap<Long,Long>();
		Map<Long,Long> proveedorIdMapa = new HashMap<Long,Long>();
		BigDecimal subtotalPlanMedio = new BigDecimal(0);
		BigDecimal porcentajeDescuentoVenta = new BigDecimal(0); 
		BigDecimal porcentajeComisionAgencia = new BigDecimal(0); 
		BigDecimal porcentajeBonificacionVenta = new BigDecimal(0); 
		//para obtener todos los campaña producto version id sin repetirse.
		for(Object[] ordenMedioDetalleOrdenMedioCabecera : ordenesMedioDetalleDelPlan){
			OrdenMedioDetalleIf ordenMedioDetalle = (OrdenMedioDetalleIf)ordenMedioDetalleOrdenMedioCabecera[0];
			OrdenMedioIf ordenMedio = (OrdenMedioIf)ordenMedioDetalleOrdenMedioCabecera[1];
			
			campanaProductoVersionIdMapa.put(ordenMedioDetalle.getCampanaProductoVersionId(), ordenMedioDetalle.getCampanaProductoVersionId());
			proveedorIdMapa.put(ordenMedio.getProveedorId(), ordenMedio.getProveedorId());
			subtotalPlanMedio = subtotalPlanMedio.add(ordenMedioDetalle.getValorSubtotal());
			//estos porcentajes son los mismos en todos los detalles
			porcentajeDescuentoVenta = ordenMedio.getPorcentajeDescuentoVenta();
			porcentajeComisionAgencia = ordenMedio.getPorcentajeComisionAgencia();
			porcentajeBonificacionVenta = ordenMedio.getPorcentajeBonificacionVenta();
		}
		
		//para marcas y productos
		Map<Long,ProductoClienteIf> productosClienteMapa = new HashMap<Long,ProductoClienteIf>();
		Map<Long,MarcaProductoIf> marcasClienteMapa = new HashMap<Long,MarcaProductoIf>();
		Iterator<Long> campanaProductoVersionIdMapaIt = campanaProductoVersionIdMapa.keySet().iterator();
		while(campanaProductoVersionIdMapaIt.hasNext()){
			Long campanaProductoVersionId = campanaProductoVersionIdMapaIt.next();
			CampanaProductoVersionIf campanaProductoVersion = mapaCampanaProductoVersion.get(campanaProductoVersionId);
			CampanaProductoIf campanaProducto = mapaCampanaProducto.get(campanaProductoVersion.getCampanaProductoId());
			ProductoClienteIf productoCliente = mapaProductoCliente.get(campanaProducto.getProductoClienteId());
			productosClienteMapa.put(productoCliente.getId(), productoCliente);
			MarcaProductoIf marcaProducto = mapaMarcasProducto.get(productoCliente.getMarcaProductoId());
			marcasClienteMapa.put(marcaProducto.getId(), marcaProducto);
		}
		
		//segmento
		Iterator<Long> marcasClienteMapaIt = marcasClienteMapa.keySet().iterator();
		while(marcasClienteMapaIt.hasNext()){
			Long marcaId = marcasClienteMapaIt.next();
			String marca = marcasClienteMapa.get(marcaId).getNombre();
			if(segmento.equals("")){
				segmento = marca;
			}else{
				segmento = segmento + "\n" + marca;
			}
		}
		
		//producto
		Iterator<Long> productosClienteMapaIt = productosClienteMapa.keySet().iterator();
		while(productosClienteMapaIt.hasNext()){
			Long productoId = productosClienteMapaIt.next();
			String productoCliente = productosClienteMapa.get(productoId).getNombre();
			if(producto.equals("")){
				producto = productoCliente;
			}else{
				producto = producto + "\n" + productoCliente;
			}
		}
		
		//para tipos de medios y proveedores
		Map<Long,ClienteOficinaIf> proveedoresMapa = new HashMap<Long,ClienteOficinaIf>();
		Map<Long,TipoProveedorIf> tipoMediosMapa = new HashMap<Long,TipoProveedorIf>();
		Iterator<Long> proveedorIdMapaIt = proveedorIdMapa.keySet().iterator();
		while(proveedorIdMapaIt.hasNext()){
			Long proveedorId = proveedorIdMapaIt.next();
			ClienteOficinaIf proveedorOficinaIf = mapaClienteOficina.get(proveedorId);
			proveedoresMapa.put(proveedorOficinaIf.getId(), proveedorOficinaIf);
			ClienteIf proveedorIf = mapaCliente.get(proveedorOficinaIf.getClienteId());
			TipoProveedorIf tipoMedio = mapaTipoProveedor.get(proveedorIf.getTipoproveedorId());
			tipoMediosMapa.put(tipoMedio.getId(), tipoMedio);
		}
		
		//medio
		Iterator<Long> tipoMediosMapaIt = tipoMediosMapa.keySet().iterator();
		while(tipoMediosMapaIt.hasNext()){
			Long tipoMedioId = tipoMediosMapaIt.next();
			String tipoMedio = tipoMediosMapa.get(tipoMedioId).getNombre();
			if(medio.equals("")){
				medio = tipoMedio;
			}else{
				medio = medio + "\n" + tipoMedio;
			}
		}
		
		//proveedor
		Iterator<Long> proveedoresMapaIt = proveedoresMapa.keySet().iterator();
		while(proveedoresMapaIt.hasNext()){
			Long proveedorId = proveedoresMapaIt.next();
			String proveedorNombre = proveedoresMapa.get(proveedorId).getDescripcion();
			if(proveedor.equals("")){
				proveedor = proveedorNombre;
			}else if(getCbVerProveedores().isSelected()){
				proveedor = proveedor + "\n" + proveedorNombre;
			}
		}
		
		//valor
		BigDecimal descuentoVenta = subtotalPlanMedio.multiply(porcentajeDescuentoVenta.divide(new BigDecimal(100)));
		BigDecimal comisionAgencia = (subtotalPlanMedio.subtract(descuentoVenta)).multiply(porcentajeComisionAgencia.divide(new BigDecimal(100)));
		BigDecimal bonificacionVenta = (subtotalPlanMedio.subtract(descuentoVenta).add(comisionAgencia)).multiply(porcentajeBonificacionVenta.divide(new BigDecimal(100)));
		BigDecimal subTotal = subtotalPlanMedio.subtract(descuentoVenta).add(comisionAgencia).subtract(bonificacionVenta);
		valor = formatoDecimal.format(subTotal);
		
		//iva
		BigDecimal porcentajeIva = BigDecimal.valueOf(Parametros.IVA / 100);
		BigDecimal ivaSubTotal = subTotal.multiply(porcentajeIva);
		iva = formatoDecimal.format(ivaSubTotal);
		
		//total
		BigDecimal totalPlan = subTotal.add(ivaSubTotal);
		total = formatoDecimal.format(totalPlan);
		
		//MEDIOS, EN PROCESO = 'N', PENDIENTE = 'P', APROBADO = 'A', PEDIDO = 'D', FACTURADO = 'F'
		String estadoPlan = planMedio.getEstado();
				
		setearTotalesMediosMes(date, totalPlan, estadoPlan);
		
		InversionClientesData inversionClientesVector = new InversionClientesData();
		inversionClientesVector.setClienteId(clienteId);
		inversionClientesVector.setClienteOficina(clienteOficina);
		inversionClientesVector.setFechaFactura(fechaFactura);
		inversionClientesVector.setFactura(factura);
		inversionClientesVector.setFechaAprobacion(fechaAprobacion);
		inversionClientesVector.setCodigoPresupuesto(presupuesto);
		inversionClientesVector.setSap(sap);
		inversionClientesVector.setSegmento(segmento);
		inversionClientesVector.setProducto(producto);
		inversionClientesVector.setMedio(medio);
		inversionClientesVector.setProveedor(proveedor);
		inversionClientesVector.setValor(valor);
		inversionClientesVector.setIva(iva);
		inversionClientesVector.setTotal(total);
		inversionClientesVector.setDate(date);
		inversionClientesDataVector.add(inversionClientesVector);
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
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		getCmbTipoMedio().setSelectedItem(TODOS);
		getCmbEstado().setSelectedItem(TODOS);
		estado = TODOS;
		getCmbTipoProveedor().setSelectedItem(NOMBRE_TIPO_PROVEEDOR_MEDIOS);
		tipoProveedor = NOMBRE_TIPO_PROVEEDOR_MEDIOS;
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
			
			Date fechaInicio = getCmbFechaInicio().getDate();
			Date fechaFin = getCmbFechaFin().getDate();
			//agruparBy sirve para el reporte de inversion de plan de medios
			String agruparBy = "";
			
			Timestamp timeInicio = Utilitarios.resetTimestampStartDate(new Timestamp(fechaInicio.getTime()));
			Timestamp timeFin =  Utilitarios.resetTimestampEndDate(new Timestamp(fechaFin.getTime()));
			
			boolean fechaAprobacion = true;
			if(getCmbEstado().getSelectedItem().equals("COTIZADO"))
				fechaAprobacion = false;				
						
			//ORDENES MEDIO DETALLE
			if(!tipoProveedor.equals(NOMBRE_TIPO_PROVEEDOR_PRODUCCION)){
				Map<String,Long> mapaOrden = new LinkedHashMap<String, Long>();			
				if (clienteOficinaIf != null) 
					mapaOrden.put("clienteOficinaId", clienteOficinaIf.getId());			
				if (medioOficinaIf != null)
					mapaOrden.put("proveedorId", medioOficinaIf.getId());
				
				Map<String,Long> mapaGeneral = new LinkedHashMap<String, Long>();			
				if (marcaProductoIf != null && productoClienteIf == null)
					mapaGeneral.put("keyMarcaProductoId", marcaProductoIf.getId());
				if (productoClienteIf != null)
					mapaGeneral.put("keyProductoClienteId", productoClienteIf.getId());			
				if (tipoProveedorIf != null && medioIf == null && medioOficinaIf == null)
					mapaGeneral.put("keyTipoProveedorId", tipoProveedorIf.getId());
				
				if (clienteIf != null && clienteOficinaIf == null) 
					mapaGeneral.put("keyClienteId", clienteIf.getId());			
				if (medioIf != null && medioOficinaIf == null)
					mapaGeneral.put("keyProveedorId", medioIf.getId());
							
				ordenesMedioDetalle = (ArrayList)SessionServiceLocator.getOrdenMedioSessionService().
										findOrdenMedioDetalleByQueryAndQueryGeneralByProductoAndByFechas(mapaOrden,mapaGeneral,tipoPauta,timeInicio,timeFin, fechaAprobacion, Parametros.getIdEmpresa(), estado, aprobadosFacturados, "", false, true);
				//ordenesMedioDetalle.clear();
			}
			
			//PRESUPUESTOS DETALLE
			Map<String,Long> mapaPresupuestoDetalle = new LinkedHashMap<String, Long>();			
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
			if (tipoProveedorIf != null && medioIf == null && medioOficinaIf == null)
				mapaPresupuestoDetalle.put("tipoProveedorId", tipoProveedorIf.getId());
			
			presupuestosDetalle = (ArrayList)SessionServiceLocator.getPresupuestoSessionService().findPresupuestosDetalleByQueryByProductoAndByFechas(mapaPresupuestoDetalle, timeInicio,timeFin, fechaAprobacion, Parametros.getIdEmpresa(), agruparBy, estado, tipoProveedor, aprobadosFacturados, true, "", false);
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
									
					String fileName = "jaspers/facturacion/RPInversionClientes.jasper";
										
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
					/*parametrosMap.put("presupuestosCotizados", String.valueOf(presupuestosCotizados));
					parametrosMap.put("presupuestosAprobados", String.valueOf(presupuestosFacturados+presupuestosPendientes));
					parametrosMap.put("presupuestosFacturados", String.valueOf(presupuestosFacturados));
					parametrosMap.put("presupuestosPendientes", String.valueOf(presupuestosPendientes));
					parametrosMap.put("presupuestosTotales", String.valueOf(presupuestosCotizados+presupuestosFacturados+presupuestosPendientes));
					parametrosMap.put("totalCotizado", formatoDecimal.format(totalCotizado));					
					parametrosMap.put("totalAprobado", formatoDecimal.format(totalFacturado.add(totalPendiente)));
					parametrosMap.put("totalFacturado", formatoDecimal.format(totalFacturado));
					parametrosMap.put("totalPendiente", formatoDecimal.format(totalPendiente));*/
					
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
