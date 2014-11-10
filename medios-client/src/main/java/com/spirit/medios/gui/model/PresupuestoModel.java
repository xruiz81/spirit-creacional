package com.spirit.medios.gui.model;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.bpm.process.elements.Tarea;
import com.spirit.bpm.process.gui.BpmPanel;
import com.spirit.cartera.entity.ClienteCondicionIf;
import com.spirit.cartera.entity.FormaPagoIf;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.OrdenCompraDetalleIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.compras.handler.EstadoOrdenCompra;
import com.spirit.compras.reportesData.OrdenCompraDetalleReporteData;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.ClienteRetencionIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.handler.EstadoPedido;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoArchivoIf;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.general.util.GeneralUtil;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoArchivoData;
import com.spirit.medios.entity.PresupuestoArchivoIf;
import com.spirit.medios.entity.PresupuestoData;
import com.spirit.medios.entity.PresupuestoDetalleData;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoFacturacionIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.PresupuestoProductoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.medios.gui.criteria.PlanMedioCriteria;
import com.spirit.medios.gui.criteria.PresupuestoCriteria;
import com.spirit.medios.gui.panel.JPPresupuesto;
import com.spirit.medios.handler.EstadoOrdenTrabajo;
import com.spirit.medios.handler.EstadoPresupuesto;
import com.spirit.medios.util.InfoOrdenTrabajoDetalle;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.util.Archivos;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.DeepCopy;
import com.spirit.util.ExtensionFileFilter;
import com.spirit.util.FileInputStreamSerializable;
import com.spirit.util.LabelAccessory;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.ObjectCloner;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class PresupuestoModel extends JPPresupuesto implements BpmPanel {
	private static final long serialVersionUID = 8418014934972644858L;
	private CorporacionCriteria corporacionCriteria;
	private ClienteCriteria clienteCriteria;
	private ProductoCriteria productoCriteria;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private PresupuestoIf presupuesto;
	private PresupuestoDetalleIf presupuestoDetalleIf;
	private PresupuestoDetalleIf presupuestoDetalleIfP;
	protected ProductoIf productoIf, productoIfP;
	private DefaultTableModel modelPresupuestoDetalle, modelPresupuestoArchivo,	modelPresupuestoDetalleP;
	protected CorporacionIf corporacionIf;
	protected ClienteIf clienteIf;
	protected ClienteOficinaIf proveedorIf, proveedorIfP;
	protected ClienteCondicionIf clienteCondicion;
	protected ClienteOficinaIf clienteOficinaIf;
	protected TipoOrdenIf tipoOrdenIf;
	protected OrdenTrabajoDetalleIf ordenTD;
	protected OrdenTrabajoIf ordenTrabajoIf;
	JMenuItem itemEliminarPresupuestoDetalle;
	JMenuItem itemEliminarPresupuestoDetalleP;
	final JPopupMenu popupMenuPresupuestoDetalle = new JPopupMenu();
	final JPopupMenu popupMenuPresupuestoDetalleP = new JPopupMenu();
	protected double subTotal = 0D, valorBruto = 0D, descuentoTotal = 0D, descuentoCompraTotal = 0D,
			descuentoEspecialTotalCompra = 0D, descuentoEspecialTotalVenta = 0D,
			bonificacionCompraTotal = 0D, descuentosVariosVentaTotal, 
			ivaTotal = 0D, ivaTotalAgencia = 0D, total = 0D, totalAgencia = 0D,  
			ivaTemp = 0D, ivaCompraTemp = 0D;
	protected double porcentajeComisionAgencia = 0D, comisionAgencia = 0D;
	protected double descT;
	protected double IVA = Parametros.getIVA() / 100;
	protected int contModif = 0;
	private static final String REPORTE_SI = "S";
	private static final String REPORTE_NO = "N";
	
	private Map<Long,OrdenCompraIf> mapaOrdenesCompra = new HashMap<Long, OrdenCompraIf>();
	private Map<Long,GenericoIf> mapaGenericos = new HashMap<Long, GenericoIf>();
	private Map<Long,ProductoIf> mapaProductos = new HashMap<Long, ProductoIf>();
	private Map<Long,ClienteIf> mapaClientes = new HashMap<Long, ClienteIf>();
	private Map<Long,TipoOrdenIf> mapaTiposOrden = new HashMap<Long, TipoOrdenIf>();
	private Map<Long,SubtipoOrdenIf> mapaSubTiposOrden = new HashMap<Long, SubtipoOrdenIf>();
	private Map<Long,EmpleadoIf> mapaEmpleados = new HashMap<Long, EmpleadoIf>();
	private Map<Long,ClienteOficinaIf> mapaClienteOficinas = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long,CiudadIf> mapaCiudades= new HashMap<Long, CiudadIf>();
	private Map<Long,UsuarioIf> mapaUsuarios= new HashMap<Long, UsuarioIf>();
	//---------------------ESTADOS---------------------------
	/*private static final String NOMBRE_ESTADO_COTIZADO = "COTIZADO";
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_APROBADO = "APROBADO";
	private static final String NOMBRE_ESTADO_CANCELADO = "CANCELADO";
	private static final String NOMBRE_ESTADO_FACTURADO = "FACTURADO";
	private static final String NOMBRE_ESTADO_ORDEN_EN_CURSO = "EN_CURSO";
	
	public static final String ESTADO_COTIZADO = NOMBRE_ESTADO_COTIZADO
			.substring(2, 3);
	public static final String ESTADO_PENDIENTE = NOMBRE_ESTADO_PENDIENTE
			.substring(0, 1);
	public static final String ESTADO_APROBADO = NOMBRE_ESTADO_APROBADO
			.substring(0, 1);
	public static final String ESTADO_CANCELADO = NOMBRE_ESTADO_CANCELADO
			.substring(0, 1);
	public static final String ESTADO_FACTURADO = NOMBRE_ESTADO_FACTURADO
			.substring(0, 1);
	public static final String ESTADO_ORDEN_EN_CURSO = NOMBRE_ESTADO_ORDEN_EN_CURSO
			.substring(0, 1);*/
	//---------------------------------------------------------
	
	
	private static final String ESTADO_RIESGO = "R";
	//private static final String ESTADO_REALIZADO = "R";
	private static final String CODIGO_TIPO_PROVEEDOR = "PR";
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String TIPO_ORDEN_CODIGO_PRODUCCION = "PR";
	private static final String TIPO_ORDEN_CODIGO_MEDIOS = "ME";
	private static final String TIPO_ORDEN_CODIGO_CREATIVO = "CR";
	private Vector<PresupuestoDetalleIf> presupuestoDetalleColeccion = new Vector<PresupuestoDetalleIf>();
	private Vector<PresupuestoDetalleIf> presupuestoDetalleColeccionP = new Vector<PresupuestoDetalleIf>();
	private Vector<PresupuestoDetalleIf> presupuestoDetalleEliminadosColeccion = new Vector<PresupuestoDetalleIf>();
	private Vector<PresupuestoDetalleIf> presupuestoDetalleEliminadosColeccionP = new Vector<PresupuestoDetalleIf>();
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DecimalFormat formatoEntero = new DecimalFormat("###0");
	private static final int MAX_LONGITUD_VALOR = 22;
	//private static final int MAX_LONGITUD_CODIGO = 10;
	private static final int MAX_LONGITUD_CABECERA = 1000;
	private static final int MAX_LONGITUD_DESCUENTO = 5;
	private static final int MAX_LONGITUD_PORCENTAJE_COMISION = 5;
	private static final int MAX_LONGITUD_DIAS_VALIDEZ = 3;
	private static final int MAX_LONGITUD_CONCEPTO = 100;
	private static final int MAX_LONGITUD_CONCEPTO_DETALLE = 3000;
	private static final int MAX_LONGITUD_TEMA_CAMPANA = 200;
	private static final int MAX_LONGITUD_AUTORIZACION_SAP = 15;
	private String si = "Sí";
	private String no = "No";
	private Object[] options = { si, no };
	private static final String BUSQUEDA_POR_PROVEEDOR = "P";
	private static final String SERVICIO_CONSUMO = "A";
	private static final String TIPO_PRESUPUESTO = "P";
	private static final String TIPO_MEDIOS = "M";
	private static final String TIPO_GASTO = "G";
	//private static final String CODIGO_DEPARTAMENTO_CUENTAS = "CUE";
	private Vector<PresupuestoDetalleReporteData> presupuestoDetalleDataColeccion = new Vector<PresupuestoDetalleReporteData>();
	private ProductoClienteIf productoClienteIf;
	private Vector<PresupuestoArchivoIf> archivosPresupuestoColeccion = new Vector<PresupuestoArchivoIf>();
	private Vector<File> archivosColeccion = new Vector<File>();
	private Vector<String> archivosNombreColeccion = new Vector<String>();
	private Vector<PresupuestoArchivoIf> archivosEliminadosColeccion = new Vector<PresupuestoArchivoIf>();
	private final JPopupMenu popupMenuArchivo = new JPopupMenu();
	private Map<Long, ClienteOficinaIf> listaProveedoresMap = new HashMap<Long, ClienteOficinaIf>();
	private Map<OrdenCompraIf, List<OrdenCompraDetalleIf>> ordenesCompraMap = new HashMap<OrdenCompraIf, List<OrdenCompraDetalleIf>>();
	private BigDecimal sumaOC = new BigDecimal(0);
	private BigDecimal descuentoOC = new BigDecimal(0);
	private BigDecimal bonificacionOC = new BigDecimal(0);
	private BigDecimal ivaOC = new BigDecimal(0);
	private BigDecimal totalOC = new BigDecimal(0);
	private Vector<OrdenCompraDetalleReporteData> ordenCompraDetalleDataColeccion = new Vector<OrdenCompraDetalleReporteData>();
	private BigDecimal totalVentaReporte = new BigDecimal(0);
	private BigDecimal totalVentaReporteP = new BigDecimal(0);
	private List<ProductoClienteIf> productoClienteList = new ArrayList<ProductoClienteIf>();
	private Set<Integer> ordenesCompraParaModificar = new HashSet<Integer>();
	private DefaultListModel modelProductoCliente = new DefaultListModel();
	private static final String SI_TIENE_NEGOCIACION_DIRECTA = "S";
	private static final String NO_TIENE_NEGOCIACION_DIRECTA = "N";
	private static final String SI_TIENE_COMISION_PURA = "S";
	private static final String NO_TIENE_COMISION_PURA= "N";
	private Long referenciaId = null;
	private String tipoReferencia = null;
	
	
	public PresupuestoModel() {
		iniciarContructor();
	}

	private void iniciarContructor() {
		initKeyListeners();
		cargarMapas();
		anchoColumnasTabla();
		getTxtDescripcionOTdetalle().setEditable(false);
		this.showSaveMode();
		initListeners();
		itemEliminarPresupuestoDetalle = new JMenuItem("Eliminar");
		itemEliminarPresupuestoDetalle
				.addActionListener(oActionListenerEliminarPresupuestoDetalle);
		popupMenuPresupuestoDetalle.add(itemEliminarPresupuestoDetalle);
		popupMenuPresupuestoDetalleP.add(itemEliminarPresupuestoDetalle);
		this.getTblPresupuestoDetalle().addMouseListener(
				oMouseAdapterTblPresupuestoDetalle);
		this.getTblPresupuestoDetalleP().addMouseListener(
				oMouseAdapterTblPresupuestoDetalleP);
		initAddDetailListener();
		new HotKeyComponent(this);
	}
	
	public PresupuestoModel(PresupuestoIf presupuestoIf){
		iniciarContructor();
		try {
			getSelectedObject(presupuestoIf);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al abrir Prespuesto existente !!", SpiritAlert.ERROR);
		}
	}
	
	public PresupuestoModel(OrdenTrabajoDetalleIf ordenTrabajoDetalle){
		iniciarContructor();
		try {
			nuevoConstrutorOrdenTrabajoDetalle(ordenTrabajoDetalle);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al abrir Prespuesto desde una Orden de Trabajo !!", SpiritAlert.ERROR);
		}
	}

	private void nuevoConstrutorOrdenTrabajoDetalle(OrdenTrabajoDetalleIf ordenTrabajoDetalle)
			throws GenericBusinessException {
		OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId() );
		clienteOficinaIf = mapaClienteOficinas.get(ordenTrabajo.getClienteoficinaId());
		seleccionarClienteOficina();
		
		SubtipoOrdenIf subTipoOrden = mapaSubTiposOrden.get(ordenTrabajoDetalle.getSubtipoId());
		TipoOrdenIf to = mapaTiposOrden.get(subTipoOrden.getTipoordenId());
		getCmbTipoOrden().setSelectedIndex(
				ComboBoxComponent.getIndexToSelectItem(getCmbTipoOrden(), to.getId()));
		getCmbTipoOrden().repaint();
		
		ordenTrabajoIf = ordenTrabajo;
		
		getCmbOrdenTrabajo().setSelectedIndex(
				ComboBoxComponent.getIndexToSelectItem(getCmbOrdenTrabajo(), ordenTrabajoIf.getId()));
		getCmbOrdenTrabajo().repaint();
		
		InfoOrdenTrabajoDetalle info = crearInfoOrdenTrabajo(ordenTrabajoDetalle);
		getCmbOrdenTrabajoDetalle().setSelectedItem(info);
		getCmbOrdenTrabajoDetalle().repaint();
	}
	
	public void cargarMapas(){
		try {			
			mapaClienteOficinas.clear();
			Collection clientesOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficinaList();
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while(clientesOficinaIt.hasNext()){
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf)clientesOficinaIt.next();
				mapaClienteOficinas.put(clienteOficina.getId(), clienteOficina);
			}
			
			mapaClientes.clear();
			Collection clientes = SessionServiceLocator.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesIt = clientes.iterator();
			while(clientesIt.hasNext()){
				ClienteIf cliente = (ClienteIf)clientesIt.next();
				mapaClientes.put(cliente.getId(), cliente);
			}
			
			mapaProductos.clear();
			Collection productos = SessionServiceLocator.getProductoSessionService().findProductoByEmpresaId(Parametros.getIdEmpresa());
			Iterator productosIt = productos.iterator();
			while(productosIt.hasNext()){
				ProductoIf producto = (ProductoIf)productosIt.next();
				mapaProductos.put(producto.getId(), producto);
			}	
			
			mapaGenericos.clear();
			Collection genericos = SessionServiceLocator.getGenericoSessionService().findGenericoByEmpresaId(Parametros.getIdEmpresa());
			Iterator genericosIt = genericos.iterator();
			while(genericosIt.hasNext()){
				GenericoIf generico = (GenericoIf)genericosIt.next();
				mapaGenericos.put(generico.getId(), generico);
			}
			
			mapaTiposOrden.clear();
			Collection tiposOrden = SessionServiceLocator.getTipoOrdenSessionService().findTipoOrdenByEmpresaId(Parametros.getIdEmpresa());
			Iterator tiposOrdenIt = tiposOrden.iterator();
			while(tiposOrdenIt.hasNext()){
				TipoOrdenIf tipoOrden = (TipoOrdenIf)tiposOrdenIt.next();
				mapaTiposOrden.put(tipoOrden.getId(), tipoOrden);
			}
			
			mapaSubTiposOrden.clear();
			Collection subTiposOrden = SessionServiceLocator.getSubtipoOrdenSessionService().findSubtipoOrdenByEmpresaId(Parametros.getIdEmpresa());
			Iterator subTiposOrdenIt = subTiposOrden.iterator();
			while(subTiposOrdenIt.hasNext()){
				SubtipoOrdenIf subtipoOrden = (SubtipoOrdenIf)subTiposOrdenIt.next();
				mapaSubTiposOrden.put(subtipoOrden.getId(), subtipoOrden);
			}
			
			mapaEmpleados.clear();
			Collection empleados = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Iterator empleadosIt = empleados.iterator();
			while(empleadosIt.hasNext()){
				EmpleadoIf empleado = (EmpleadoIf)empleadosIt.next();
				mapaEmpleados.put(empleado.getId(), empleado);
			}
			
			mapaCiudades.clear();
			Collection ciudades = SessionServiceLocator.getCiudadSessionService().getCiudadList();
			Iterator ciudadesIt = ciudades.iterator();
			while(ciudadesIt.hasNext()){
				CiudadIf ciudad = (CiudadIf)ciudadesIt.next();
				mapaCiudades.put(ciudad.getId(), ciudad);
			}
			
			mapaUsuarios.clear();
			Collection usuarios = SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpresaId(Parametros.getIdEmpresa());
			Iterator usuariosIt = usuarios.iterator();
			while(usuariosIt.hasNext()){
				UsuarioIf usuario = (UsuarioIf)usuariosIt.next();
				mapaUsuarios.put(usuario.getId(), usuario);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void anchoColumnasTabla() {
		setSorterTable(getTblPresupuestoDetalle());
		getTblPresupuestoDetalle().getTableHeader().setReorderingAllowed(false);
		getTblPresupuestoDetalle().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		getTblPresupuestoDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(25);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(110);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(11);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(12);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(13);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(14);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(15);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblPresupuestoDetalle().getColumnModel().getColumn(16);
		anchoColumna.setPreferredWidth(90);
		
		getTblPresupuestoDetalleP().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumnaP = getTblPresupuestoDetalleP()
				.getColumnModel().getColumn(0);
		anchoColumnaP.setPreferredWidth(200);
		anchoColumnaP = getTblPresupuestoDetalleP().getColumnModel().getColumn(
				1);
		anchoColumnaP.setPreferredWidth(40);
		anchoColumnaP = getTblPresupuestoDetalleP().getColumnModel().getColumn(
				2);
		anchoColumnaP.setPreferredWidth(100);
		anchoColumnaP = getTblPresupuestoDetalleP().getColumnModel().getColumn(
				3);
		anchoColumnaP.setPreferredWidth(100);
		anchoColumnaP = getTblPresupuestoDetalleP().getColumnModel().getColumn(
				4);
		anchoColumnaP.setPreferredWidth(60);
		anchoColumnaP = getTblPresupuestoDetalleP().getColumnModel().getColumn(
				5);
		anchoColumnaP.setPreferredWidth(60);

		getTblArchivo().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumnaArchivo = getTblArchivo().getColumnModel()
				.getColumn(0);
		anchoColumnaArchivo.setPreferredWidth(150);
		anchoColumnaArchivo = getTblArchivo().getColumnModel().getColumn(1);
		anchoColumnaArchivo.setPreferredWidth(400);
	}

	private void initKeyListeners() {
		getTxtSubTipoOrden().setVisible(false);
		getBtnAgregarDetalle().setText("");
		getBtnActualizarDetalle().setText("");
		getBtnEliminarDetalle().setText("");
		getBtnAgregarDetalleP().setText("");
		getBtnActualizarDetalleP().setText("");
		getBtnEliminarDetalleP().setText("");
		getBtnAgregarArchivo().setText("");
		getBtnActualizarArchivo().setText("");
		getBtnEliminarArchivo().setText("");
		getBtnBuscarProveedor().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarProveedor().setToolTipText("Buscar Proveedor");
		getBtnBuscarCorporacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCorporacion().setToolTipText("Buscar Corporación");
		getBtnBuscarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCliente().setToolTipText("Buscar Cliente");
		getBtnBuscarOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarOficina().setToolTipText("Buscar Oficina");
		getBtnReferencia().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnReferencia().setToolTipText("Buscar Referencia");
		getBtnLimpiarReferencia().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/cancelar.png"));
		getBtnLimpiarReferencia().setToolTipText("Limpiar Referencia");
		getBtnBuscarProducto().setToolTipText("Buscar Producto");
		getBtnBuscarProducto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnAgregarDetalle().setToolTipText("Agregar Detalle");
		getBtnAgregarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarDetalle().setToolTipText("Actualizar Detalle");
		getBtnActualizarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnEliminarDetalle().setToolTipText("Eliminar Detalle");
		getBtnEliminarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnAgregarDetalleP().setToolTipText("Agregar Detalle");
		getBtnAgregarDetalleP().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarDetalleP().setToolTipText("Actualizar Detalle");
		getBtnActualizarDetalleP().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnEliminarDetalleP().setToolTipText("Eliminar Detalle");
		getBtnEliminarDetalleP().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnVerArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnVerArchivo().setToolTipText("Ver Archivo");
		getBtnBuscarArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/attachFile.png"));
		getBtnBuscarArchivo().setToolTipText("Buscar Archivo");
		getBtnAgregarArchivo().setToolTipText("Agregar Archivo");
		getBtnAgregarArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarArchivo().setToolTipText("Actualizar Archivo");
		getBtnActualizarArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnEliminarArchivo().setToolTipText("Eliminar Archivo");
		getBtnEliminarArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));

		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		getTblPresupuestoDetalle().getColumnModel().getColumn(15).setCellRenderer(tableCellRendererCenter);
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		
		getTblPresupuestoDetalle().getColumnModel().getColumn(2)
				.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalle().getColumnModel().getColumn(3)
				.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalle().getColumnModel().getColumn(4)
				.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalle().getColumnModel().getColumn(5)
				.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalle().getColumnModel().getColumn(6)
				.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalle().getColumnModel().getColumn(7)
		.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalle().getColumnModel().getColumn(8)
		.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalle().getColumnModel().getColumn(9)
		.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalle().getColumnModel().getColumn(10)
		.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalle().getColumnModel().getColumn(11)
		.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalle().getColumnModel().getColumn(12)
		.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalle().getColumnModel().getColumn(13)
		.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalle().getColumnModel().getColumn(14)
		.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalle().getColumnModel().getColumn(15)
		.setCellRenderer(tableCellRenderer);

		getTblPresupuestoDetalleP().getColumnModel().getColumn(1)
				.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalleP().getColumnModel().getColumn(2)
				.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalleP().getColumnModel().getColumn(3)
				.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalleP().getColumnModel().getColumn(4)
				.setCellRenderer(tableCellRenderer);
		getTblPresupuestoDetalleP().getColumnModel().getColumn(5)
				.setCellRenderer(tableCellRenderer);

		getBtnAgregarDetalleP().setEnabled(false);
		getCbSinIVA().setSelected(false);
		getTxtSubTipoOrden().setEditable(false);
		getTxtValorComision().setEditable(false);
		getTxtCorporacion().setEditable(false);
		getTxtCliente().setEditable(false);
		getTxtOficina().setEditable(false);
		getTxtCabecera().addKeyListener(
				new TextChecker(MAX_LONGITUD_CABECERA, true));
		getTxtCantidad().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtCantidad().addKeyListener(new NumberTextField());
		
		getTxtAutorizacionSAP().addKeyListener(new TextChecker(MAX_LONGITUD_AUTORIZACION_SAP));
		
		getTxtPrecioCompra()
				.addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtPrecioCompra().addKeyListener(new NumberTextFieldDecimal());
		getTxtPrecioVenta().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtPrecioVenta().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeDescuentoEspecialCompra().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_COMISION));
		getTxtPorcentajeDescuentoEspecialCompra().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeDsctoAgenciaCompra().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_COMISION));
		getTxtPorcentajeDsctoAgenciaCompra().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeDescuentosVariosCompra().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_COMISION));
		getTxtPorcentajeDescuentosVariosCompra().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeNotaCredito().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_COMISION));
		getTxtPorcentajeNotaCredito().addKeyListener(new NumberTextFieldDecimal());
		
		getTxtPorcentajeNegociacionDirecta().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_COMISION));
		getTxtPorcentajeNegociacionDirecta().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeComisionPura().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_COMISION));
		getTxtPorcentajeComisionPura().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeComisionAdicional().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_COMISION));
		getTxtPorcentajeComisionAdicional().addKeyListener(new NumberTextFieldDecimal());
		
		getTxtPorcentajeDsctoAgenciaVenta().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_COMISION));
		getTxtPorcentajeDsctoAgenciaVenta().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeDescuentoEspecialVenta().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_COMISION));
		getTxtPorcentajeDescuentoEspecialVenta().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeDescuentosVariosVenta().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_COMISION));
		getTxtPorcentajeDescuentosVariosVenta().addKeyListener(new NumberTextFieldDecimal());
		getTxtPorcentajeComision().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE_COMISION));
		getTxtPorcentajeComision().addKeyListener(new NumberTextFieldDecimal());
		
		getTxtDiasValidez().addKeyListener(new TextChecker(MAX_LONGITUD_DIAS_VALIDEZ));
		getTxtDiasValidez().addKeyListener(new NumberTextField());
		getTxtConceptoPresupuesto().addKeyListener(new TextChecker(MAX_LONGITUD_CONCEPTO));
		getTxtConceptoPresupuestoDetalle().addKeyListener(new TextChecker(MAX_LONGITUD_CONCEPTO_DETALLE, true, 0));
		getTxtTemaCampana().addKeyListener(new TextChecker(MAX_LONGITUD_TEMA_CAMPANA));
		
		getCmbFechaCreacion().setLocale(Utilitarios.esLocale);
		getCmbFechaPresupuesto().setLocale(Utilitarios.esLocale);
		getCmbFechaCreacion().setShowNoneButton(false);
		getCmbFechaCreacion().setEnabled(false);
		
		getCmbFechaAprobacion().setLocale(Utilitarios.esLocale);		
		getCmbFechaAprobacion().setShowNoneButton(true);
		getCmbFechaAprobacion().setEnabled(false);
		
		getCmbFechaPublicacion().setLocale(Utilitarios.esLocale);		
		getCmbFechaPublicacion().setShowNoneButton(true);
	}

	private void initAddDetailListener() {
		getBtnAgregarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDetallePresupuesto();
			}
		});

		getBtnEliminarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getTblPresupuestoDetalle().getSelectedRow() != -1){
					int filaSeleccionada = getTblPresupuestoDetalle().convertRowIndexToModel(getTblPresupuestoDetalle().getSelectedRow());
					
					PresupuestoDetalleIf presupuestoDetalleEliminado = (PresupuestoDetalleIf) presupuestoDetalleColeccion.get(filaSeleccionada);

					if(presupuestoDetalleEliminado.getOrdenCompraId() != null){
						SpiritAlert.createAlert("No es posible eliminar el detalle porque tiene asociada una Orden.",SpiritAlert.WARNING);
					}else{
						eliminarDetallePresupuesto();
						
						//si es Medios elimino automaticamente tambien del reporte el detalle
						TipoOrdenIf tipoOrden = (TipoOrdenIf) getCmbTipoOrden().getSelectedItem();
						if (tipoOrden.getTipo().equals(TIPO_MEDIOS) && (filaSeleccionada <= presupuestoDetalleColeccionP.size())) {
							presupuestoDetalleIfP = (PresupuestoDetalleIf) presupuestoDetalleColeccionP.get(filaSeleccionada);
							if (presupuestoDetalleIfP.getId() != null){
								presupuestoDetalleEliminadosColeccion.add(presupuestoDetalleIfP);
							}
							presupuestoDetalleColeccionP.remove(filaSeleccionada);
							modelPresupuestoDetalleP.removeRow(filaSeleccionada);
							calcularTotalReporte();
						}
					}
				}else {
					SpiritAlert.createAlert("Debe seleccionar el detalle a eliminar.", SpiritAlert.WARNING);
				}	
			}
		});

		getBtnEliminarDetalleP().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarDetallePresupuestoP();
			}
		});
	}

	private void actualizarTotales(boolean esFind) throws GenericBusinessException {
		Iterator detallesIterator = presupuestoDetalleColeccion.iterator();
		subTotal = 0D;
		descuentoTotal = 0D;
		descuentoCompraTotal = 0D;
		descuentoEspecialTotalCompra = 0D;
		descuentoEspecialTotalVenta = 0D;
		bonificacionCompraTotal = 0D;
		descuentosVariosVentaTotal = 0D;
		ivaTotal = 0D;
		ivaTotalAgencia = 0D;
		total = 0D;
		totalAgencia = 0D;
		valorBruto = 0D;
		ivaTemp = 0D;
		ivaCompraTemp = 0D;
		int i = 0;

		if (!getTxtPorcentajeComision().getText().trim().equals("")){
			porcentajeComisionAgencia = Double.valueOf(Utilitarios.removeDecimalFormat(getTxtPorcentajeComision().getText()));
		}else{
			porcentajeComisionAgencia = 0D;
		}
		
		while (detallesIterator.hasNext()) {
			PresupuestoDetalleIf presupuestoDetalle = (PresupuestoDetalleIf) detallesIterator.next();
			getTblPresupuestoDetalle().getSelectionModel().setSelectionInterval(i, i);
			setPresupuestoDetalle(presupuestoDetalle);
			actualizarDetallePresupuesto(true, false, esFind);
			i++;
		}
	}

	private void agregarDetallePresupuesto() {
		if (validateFieldsDetalle(false)) {
			if ("".equals(getTxtPorcentajeDsctoAgenciaVenta().getText()))
				getTxtPorcentajeDsctoAgenciaVenta().setText("0");
			if ("".equals(getTxtPorcentajeDescuentoEspecialVenta().getText()))
				getTxtPorcentajeDescuentoEspecialVenta().setText("0");
			if ("".equals(getTxtPorcentajeDescuentosVariosVenta().getText()))
				getTxtPorcentajeDescuentosVariosVenta().setText("0");
			if ("".equals(getTxtPorcentajeDsctoAgenciaCompra().getText()))
				getTxtPorcentajeDsctoAgenciaCompra().setText("0");
			if ("".equals(getTxtPorcentajeDescuentoEspecialCompra().getText()))
				getTxtPorcentajeDescuentoEspecialCompra().setText("0");
			if ("".equals(getTxtPorcentajeDescuentosVariosCompra().getText()))
				getTxtPorcentajeDescuentosVariosCompra().setText("0");
			if ("".equals(getTxtPorcentajeNotaCredito().getText()))
				getTxtPorcentajeNotaCredito().setText("0");
			if ("".equals(getTxtPorcentajeNegociacionDirecta().getText()))
				getTxtPorcentajeNegociacionDirecta().setText("0");
			if ("".equals(getTxtPorcentajeComisionPura().getText()))
				getTxtPorcentajeComisionPura().setText("0");
			if ("".equals(getTxtPorcentajeComisionAdicional().getText()))
				getTxtPorcentajeComisionAdicional().setText("0");
			if ((Double.parseDouble(getTxtPrecioCompra().getText().replaceAll(
					",", ""))) > (Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecioVenta().getText())))) {
				SpiritAlert.createAlert("El Precio de Venta no puede ser menor al Precio de Agencia",
					SpiritAlert.WARNING);
				getTxtPrecioVenta().grabFocus();
			} else if (Double.parseDouble(getTxtPorcentajeDsctoAgenciaVenta()
					.getText().replaceAll(",", "")) > 100) {
				SpiritAlert.createAlert("El porcentaje de descuento no puede ser mayor al 100%",
					SpiritAlert.WARNING);
				getTxtPorcentajeDsctoAgenciaVenta().grabFocus();
			} else if (Double.parseDouble(getTxtPorcentajeDescuentoEspecialVenta()
					.getText().replaceAll(",", "")) > 100) {
				SpiritAlert.createAlert("El porcentaje de descuento especial venta no puede ser mayor al 100%",
					SpiritAlert.WARNING);
				getTxtPorcentajeDescuentoEspecialVenta().grabFocus();
			} else if (Double.parseDouble(getTxtPorcentajeDescuentosVariosVenta()
					.getText().replaceAll(",", "")) > 100) {
				SpiritAlert.createAlert("El porcentaje de descuentos varios venta no puede ser mayor al 100%",
					SpiritAlert.WARNING);
				getTxtPorcentajeDescuentosVariosVenta().grabFocus();
			}else if (Double.parseDouble(getTxtPorcentajeDsctoAgenciaCompra()
					.getText().replaceAll(",", "")) > 100) {
				SpiritAlert.createAlert("El porcentaje de descuento en compra no puede ser mayor al 100%",
					SpiritAlert.WARNING);
				getTxtPorcentajeDsctoAgenciaCompra().grabFocus();
			} else if (Double.parseDouble(getTxtPorcentajeDescuentoEspecialCompra()
					.getText().replaceAll(",", "")) > 100) {
				SpiritAlert.createAlert("El porcentaje de Descuento Especial no puede ser mayor al 100%",
					SpiritAlert.WARNING);
				getTxtPorcentajeDescuentoEspecialCompra().grabFocus();			
			} else if (Double.parseDouble(getTxtPorcentajeDescuentosVariosCompra()
					.getText().replaceAll(",", "")) > 100) {
				SpiritAlert.createAlert("El porcentaje de dctos. varios de compra no puede ser mayor al 100%",
					SpiritAlert.WARNING);
				getTxtPorcentajeDescuentosVariosCompra().grabFocus();
			} else if (Double.parseDouble(getTxtPorcentajeNotaCredito()
					.getText().replaceAll(",", "")) > 100) {
				SpiritAlert.createAlert("El porcentaje de nota de crédito de compra no puede ser mayor al 100%",
					SpiritAlert.WARNING);
				getTxtPorcentajeNotaCredito().grabFocus();
			} else {
				agregarDetalle();
				calcularDetalle();
				getBtnBuscarProducto().setEnabled(false);

				if ((getMode() == SpiritMode.SAVE_MODE)	
						&& (getTxtPorcentajeComision().getText() == null || getTxtPorcentajeComision().getText().equals(""))) {
					TipoOrdenIf tipoOrden = (TipoOrdenIf) getCmbTipoOrden().getSelectedItem();
					if (tipoOrden.getTipo().equals(TIPO_MEDIOS)) {
						getTxtPorcentajeComision().setText("0");
					} else {
						getTxtPorcentajeComision().setText(String.valueOf(Parametros.getPorcentajeComision()));
					}
				}
			}
		}
	}

	private void eliminarDetallePresupuesto() {
		try {
			if (getTblPresupuestoDetalle().getSelectedRow() != -1) {
				int filaSeleccionada = getTblPresupuestoDetalle().convertRowIndexToModel(getTblPresupuestoDetalle().getSelectedRow());
				
				if (getMode() != SpiritMode.FIND_MODE) {
					presupuestoDetalleIf = (PresupuestoDetalleIf) presupuestoDetalleColeccion.get(filaSeleccionada);

					// obtengo los valores para actualizar los valores de presupuesto
					Double pv = presupuestoDetalleIf.getPrecioventa().doubleValue();
					Double pa = presupuestoDetalleIf.getPrecioagencia().doubleValue();
					Double cant = presupuestoDetalleIf.getCantidad().doubleValue();
					Double descu = presupuestoDetalleIf.getPorcentajeDescuentoAgenciaVenta().doubleValue();
					Double descuCompra = presupuestoDetalleIf.getPorcentajeDescuentoAgenciaCompra().doubleValue();
					Double descuEspecialCompra = presupuestoDetalleIf.getPorcentajeDescuentoEspecialCompra().doubleValue();
					Double descuEspecialVenta = presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta().doubleValue();
					Double porcentajeBonificacionCompra = presupuestoDetalleIf.getPorcentajeDescuentosVariosCompra().doubleValue();
					Double porcentajeDescuentosVariosVenta = presupuestoDetalleIf.getPorcentajeDescuentosVariosVenta().doubleValue();

					if (presupuestoDetalleIf.getId() != null) {
						String letraEstado = presupuesto.getEstado();
						EstadoPresupuesto estado = EstadoPresupuesto.getEstadoPresupuestoByLetra(letraEstado);
						//if (ESTADO_COTIZADO.equals(letraEstado)) {
						if (EstadoPresupuesto.COTIZADO == estado) {
							// Elimino el registro de la coleccion y de la tabla
							presupuestoDetalleColeccion.remove(filaSeleccionada);
							modelPresupuestoDetalle.removeRow(filaSeleccionada);
							recalcularTotales(pv, pa, cant, descu, descuCompra, 
									porcentajeBonificacionCompra, descuEspecialCompra, descuEspecialVenta, porcentajeDescuentosVariosVenta);
							presupuestoDetalleEliminadosColeccion.add(presupuestoDetalleIf);
							presupuesto.setValorbruto(BigDecimal.valueOf(subTotal));
							presupuesto.setDescuento(BigDecimal.valueOf(descuentoTotal));
							presupuesto.setIva(BigDecimal.valueOf(ivaTotal));
							presupuesto.setValor(BigDecimal.valueOf(total));
							cleanCamposDetalle();
						//} else if (ESTADO_PENDIENTE.equals(letraEstado)) {
						} else if (EstadoPresupuesto.PENDIENTE == estado) {
							SpiritAlert.createAlert(
								"El presupuesto esta Pendiente de aprobación, no se puede borrar el detalle !!!",
								SpiritAlert.WARNING);
						//} else if (ESTADO_APROBADO.equals(letraEstado)) {
						} else if (EstadoPresupuesto.APROBADO == estado) {
							SpiritAlert.createAlert(
								"El presupuesto ya ha sido Aceptado, no se puede borrar el detalle !!!",
								SpiritAlert.WARNING);
						//} else if (ESTADO_CANCELADO.equals(letraEstado)) {
						} else if (EstadoPresupuesto.CANCELADO == estado) {
							SpiritAlert.createAlert(
								"El presupuesto ha sido Cancelado, no se puede borrar el detalle !!!",
								SpiritAlert.WARNING);
						}
					} else {
						// Elimino el registro de la coleccion y de la tabla
						presupuestoDetalleColeccion.remove(filaSeleccionada);
						modelPresupuestoDetalle.removeRow(getTblPresupuestoDetalle().convertRowIndexToModel(getTblPresupuestoDetalle().getSelectedRow()));

						recalcularTotales(pv, pa, cant, descu, descuCompra, 
								porcentajeBonificacionCompra, descuEspecialCompra, descuEspecialVenta, porcentajeDescuentosVariosVenta);
						cleanCamposDetalle();
					}
				}

			} else {
				SpiritAlert.createAlert("Debe seleccionar el Detalle a eliminar !!!",SpiritAlert.WARNING);
			}

		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert(
				"El Detalle de Presupuesto tiene datos referenciados y no puede ser eliminado!",
				SpiritAlert.ERROR);
		}
	}

	private void eliminarDetallePresupuestoP() {
		try {
			int filaSeleccionada = getTblPresupuestoDetalleP().getSelectedRow();
			if (filaSeleccionada != -1) {
				// si esta en modo update lo borra directamente de la base
				if (getMode() != SpiritMode.FIND_MODE) {
					presupuestoDetalleIfP = (PresupuestoDetalleIf) presupuestoDetalleColeccionP
							.get(filaSeleccionada);

					if (presupuestoDetalleIfP.getId() != null) {
						String letraEstado = presupuesto.getEstado();
						//if (ESTADO_COTIZADO.equals(letraEstado)) {
						if (EstadoPresupuesto.COTIZADO.getLetra().equals(letraEstado)) {
							// Elimino el registro de la coleccion y de la tabla
							presupuestoDetalleColeccionP.remove(filaSeleccionada);
							modelPresupuestoDetalleP.removeRow(getTblPresupuestoDetalleP()
								.getSelectedRow());
							presupuestoDetalleEliminadosColeccion.add(presupuestoDetalleIfP);
							cleanCamposDetalleP();
						//} else if (ESTADO_PENDIENTE.equals(letraEstado)) {
						} else if (EstadoPresupuesto.PENDIENTE.getLetra().equals(letraEstado)) {
							SpiritAlert.createAlert(
								"El presupuesto esta Pendiente de aprobación, no se puede borrar el detalle !!!",
								SpiritAlert.WARNING);
						//} else if (ESTADO_APROBADO.equals(letraEstado)) {
						} else if (EstadoPresupuesto.APROBADO.getLetra().equals(letraEstado)) {
							SpiritAlert.createAlert(
								"El presupuesto ya ha sido Aceptado, no se puede borrar el detalle !!!",
								SpiritAlert.WARNING);
						//} else if (ESTADO_CANCELADO.equals(letraEstado)) {
						} else if (EstadoPresupuesto.CANCELADO.getLetra().equals(letraEstado)) {
							SpiritAlert.createAlert(
								"El presupuesto ha sido Cancelado, no se puede borrar el detalle !!!",
								SpiritAlert.WARNING);
						}
					} else {
						// Elimino el registro de la coleccion y de la tabla
						presupuestoDetalleColeccionP.remove(filaSeleccionada);
						modelPresupuestoDetalleP
								.removeRow(getTblPresupuestoDetalleP()
										.getSelectedRow());

						cleanCamposDetalleP();
					}
				}
				calcularTotalReporte();

			} else {
				SpiritAlert.createAlert(
						"Debe seleccionar el Detalle a eliminar !!!",
						SpiritAlert.WARNING);
			}

		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert
					.createAlert(
							"El Detalle de Presupuesto tiene datos referenciados y no puede ser eliminado!",
							SpiritAlert.ERROR);
		}
	}

	private void cleanCamposDetalle() {
		getTxtProveedor().setText("");
		getTxtProducto().setText("");
		getTxtConceptoPresupuestoDetalle().setText("");
		getTxtCantidad().setText("");
		getTxtPrecioCompra().setText("");
		getTxtPrecioVenta().setText("");
		getTxtPorcentajeDsctoAgenciaVenta().setText("0");
		getTxtPorcentajeDescuentoEspecialVenta().setText("0");
		getTxtPorcentajeDescuentosVariosVenta().setText("0");
		getTxtPorcentajeDsctoAgenciaCompra().setText("0");
		getTxtPorcentajeDescuentoEspecialCompra().setText("0");
		getTxtPorcentajeDescuentosVariosCompra().setText("0");
		getTxtPorcentajeNotaCredito().setText("0");
		getTxtOrden().setText("");
		getCmbFechaPublicacion().setCalendar(null);
		getCbNegociacionDirecta().setSelected(false);
		getCbComisionPura().setSelected(false);
		getTxtPorcentajeNegociacionDirecta().setText("");
		getTxtPorcentajeNegociacionDirecta().setText("");
		getTxtPorcentajeNegociacionDirecta().setEditable(false);
		getTxtPorcentajeComisionPura().setText("");
		getTxtPorcentajeComisionPura().setEditable(false);
		getTxtPorcentajeComisionAdicional().setText("");
		getTxtPorcentajeComisionAdicional().setEditable(false);
		getBtnBuscarProducto().setEnabled(false);
	}

	private void cleanCamposDetalleP() {
		getTxtProveedorP().setText("");
		getTxtProductoP().setText("");
		getTxtConceptoPresupuestoDetalleP().setText("");
		getTxtCantidadP().setText("");
		getTxtPrecioVentaP().setText("");
	}

	private void recalcularTotales(Double pv, Double pa, Double cant, Double descu, Double descuCompra, 
			Double porcentajeBonificacionCompra, Double descuEspecialCompra, Double descuEspecialVenta,
			Double porcentajeDescuentosVariosVenta) {
		subTotal = subTotal - (pv * cant);
		valorBruto = valorBruto - (pa * cant);
		Double descEspecialCompra = (pa * cant) * (descuEspecialCompra / 100);
		Double descEspecialVenta = (pv * cant) * (descuEspecialVenta / 100);
		
		Double descCompra = 0D;
		//Calculo descuento de compra solo si negociacion directa es menor a 100% y no hay Comision Pura
		if (!Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDsctoAgenciaCompra().getText()).equals("") && !getCbComisionPura().isSelected()){
			
			if(getCbNegociacionDirecta().isSelected()){
				double porcentajeNegociacion = Double.valueOf(getTxtPorcentajeNegociacionDirecta().getText().replaceAll(",", ""));
				if(porcentajeNegociacion < 100D){
					descCompra = ((pa * cant) - descEspecialCompra) * (descuCompra / 100);
				}
			}else{
				descCompra = ((pa * cant) - descEspecialCompra) * (descuCompra / 100);
			}
		}
		
		Double bonificacionCompra = 0D;
		//Calculo descuento varios compra solo si negociacion directa es menor a 100% y no hay Comision Pura
		if (!Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentosVariosCompra().getText()).equals("") && !getCbComisionPura().isSelected()){
			
			if(getCbNegociacionDirecta().isSelected()){
				double porcentajeNegociacion = Double.valueOf(getTxtPorcentajeNegociacionDirecta().getText().replaceAll(",", ""));
				if(porcentajeNegociacion < 100D){
					bonificacionCompra = ((pa * cant) - descEspecialCompra) * (porcentajeBonificacionCompra / 100);
				}
			}else{
				bonificacionCompra = ((pa * cant) - descEspecialCompra) * (porcentajeBonificacionCompra / 100);
			}
		}
		
		//Double bonificacionCompra = ((pa * cant) - descEspecialCompra) * (porcentajeBonificacionCompra / 100);
				
		Double desc = ((pv * cant) - descEspecialVenta) * (descu / 100);				
		Double descuentosVariosVenta = ((pv * cant) - descEspecialVenta) * (porcentajeDescuentosVariosVenta / 100);
		
		descuentoEspecialTotalCompra = descuentoEspecialTotalCompra - descEspecialCompra;
		descuentoEspecialTotalVenta = descuentoEspecialTotalVenta - descEspecialVenta;
				
		descuentoTotal = descuentoTotal - desc;
		descuentoCompraTotal = descuentoCompraTotal - descCompra;
		
		bonificacionCompraTotal = bonificacionCompraTotal - bonificacionCompra;
		descuentosVariosVentaTotal = descuentosVariosVentaTotal - descuentosVariosVenta;
		
		ivaTotalAgencia = (valorBruto - descuentoEspecialTotalCompra - descuentoCompraTotal - bonificacionCompraTotal) * IVA;
		totalAgencia = valorBruto - descuentoEspecialTotalCompra - descuentoCompraTotal - bonificacionCompraTotal + ivaTotalAgencia;
		
		comisionAgencia = 0D;		
		if (porcentajeComisionAgencia > 0D)
			comisionAgencia = ((subTotal - descuentoEspecialTotalVenta - descuentoTotal - descuentosVariosVentaTotal) * porcentajeComisionAgencia) / 100D;
		
		ivaTotal = (subTotal - descuentoEspecialTotalVenta - descuentoTotal - descuentosVariosVentaTotal + comisionAgencia) * IVA;
		total = subTotal - descuentoEspecialTotalVenta - descuentoTotal - descuentosVariosVentaTotal + comisionAgencia + ivaTotal;

		getTxtSubTotalVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
		getTxtSubTotalCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(valorBruto)));
		getTxtDescuentoEspecialTotalCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoEspecialTotalCompra)));
		getTxtDescuentoEspecialTotalVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoEspecialTotalVenta)));
				
		this.getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgencia)));
		
		double subTotal2Compra = valorBruto - descuentoEspecialTotalCompra;
		double subTotal2Venta = subTotal - descuentoEspecialTotalVenta;
		getTxtSubTotal2Compra().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal2Compra)));
		getTxtSubTotal2Venta().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal2Venta)));
		
		getTxtDsctoAgenciaVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));
		getTxtDsctoAgenciaCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoCompraTotal)));
		getTxtDescuentosVariosCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(bonificacionCompraTotal)));
		getTxtDescuentosVariosVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosVentaTotal)));
		getTxtIvaVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
		getTxtIvaTotalCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotalAgencia)));
		getTxtTotalVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
		getTxtTotalCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalAgencia)));
	}

	private Map<String,Object> buildQuery() {
		Map<String,Object> aMap = new HashMap<String,Object>();
		if ("".equals(getTxtCodigo().getText()) == false)
			aMap.put("codigo", getTxtCodigo().getText() + "%");
		else
			aMap.put("codigo", "%");

		if ("".equals(getTxtConceptoPresupuesto().getText()) == false)
			aMap.put("concepto", "%" + getTxtConceptoPresupuesto().getText() + "%");
		else
			aMap.put("concepto", "%");

		EstadoPresupuesto estado = (EstadoPresupuesto) getCmbEstado().getSelectedItem();
		if (estado != null) {
			aMap.put("estado", estado.getLetra());
		}

		if (getCmbFechaPresupuesto().getSelectedItem() != null)
			aMap.put("fecha", new java.sql.Date(getCmbFechaPresupuesto().getDate().getTime()));

		return aMap;
	}

	public void find() {
		try {
			Long clienteId = 0L, clienteOficinaId = 0L;

			if (clienteIf != null)
				clienteId = clienteIf.getId();

			if (clienteOficinaIf != null)
				clienteOficinaId = clienteOficinaIf.getId();

			Map<String,Object> mapa = buildQuery();
			int tamanoLista = SessionServiceLocator
					.getPresupuestoSessionService()
					.findPresupuestoByFilteredQuerySize(mapa, clienteId,
							clienteOficinaId, Parametros.getIdEmpresa());

			if (tamanoLista > 0) {
				PresupuestoCriteria presupuestoCriteria = new PresupuestoCriteria(
						clienteId, clienteOficinaId);
				presupuestoCriteria.setResultListSize(tamanoLista);
				presupuestoCriteria.setQueryBuilded(mapa);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(80);
				anchoColumnas.add(150);
				anchoColumnas.add(350);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
						presupuestoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS,
						anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null){
					PresupuestoIf presupuesto = (PresupuestoIf) popupFinder.getElementoSeleccionado();
					getSelectedObject(presupuesto);
				}
			} else {
				SpiritAlert.createAlert("No se encontraron registros",
						SpiritAlert.INFORMATION);

				if (getMode() == SpiritMode.FIND_MODE) {
					this.getCmbFechaPresupuesto().setSelectedItem(null);
					this.getCmbEstado().setSelectedItem(null);
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de información",
					SpiritAlert.ERROR);
		}
	}

	/*private String getCodigo(java.sql.Date fecha) {
		String codigo = "";
		String anio = Utilitarios.getYearFromDate(fecha);
		codigo += anio + "-";
		return codigo;
	}*/

	public void crearListaProductos() {
		productoClienteList = null;
		productoClienteList = new ArrayList<ProductoClienteIf>();
		int[] selected = getCbListProductos().getCheckBoxListSelectedIndices();
		for (int i = 0; i < selected.length; i++) {
			productoClienteList.add((ProductoClienteIf) getCbListProductos()
					.getModel().getElementAt(selected[i]));
		}
	}

	public void save() {
		crearListaProductos();
		if (validateFields()) {			
			if (compararTotalDetalleDifiereTotalReporte()) {
				int opcion = JOptionPane.showOptionDialog(null,"¡Los totales de las secciones Detalle ($"+
					formatoDecimal.format(totalVentaReporte)+ ") y Reporte ($"+ formatoDecimal
						.format(totalVentaReporteP)+ ") son diferentes! ¿Desea guardar el Presupuesto?",
						"Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					//guardo de todas formas
					guardarPresupuesto();
				}
			} else {
				//guardo porque no difieren los valores
				guardarPresupuesto();
			}
		}
	}
	
	private void guardarPresupuesto() {
		try {
			UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
			PresupuestoIf presupuestoRegistrado = registrarPresupuesto();
			
			presupuesto = presupuestoRegistrado;
			
			OrdenTrabajoIf ordenTrabajo = (OrdenTrabajoIf) this
					.getCmbOrdenTrabajo().getSelectedItem();
			
			// Una vez que se realiza un prepuesto con una orden de trabajo, el
			// estado de la orden cambia a EN_CURSO.
			ordenTrabajo.setEstado(EstadoOrdenTrabajo.EN_CURSO.getLetra());
			ordenTD.setEstado(EstadoOrdenTrabajo.EN_CURSO.getLetra());
			
			boolean generarOrdenesCompra = false;
			boolean eliminarOrdenesCompraPrevias = false;
			
			Map<String,Object> mapaResultado = SessionServiceLocator.getPresupuestoSessionService()
				.procesarPresupuestoServer(generarOrdenesCompra,eliminarOrdenesCompraPrevias,
					presupuestoRegistrado,presupuestoDetalleColeccion,presupuestoDetalleColeccionP, 
					ordenTrabajo,ordenTD, productoClienteList,ordenesCompraParaModificar,usuario,
					IVA,Parametros.getCodMoneda(),Parametros.getIdOficina(),tarea);
			
			presupuesto = (PresupuestoIf) mapaResultado.get("presupuesto");
			
			Collection<PresupuestoDetalleIf> presupuestoDetalles = (Collection<PresupuestoDetalleIf>)
				mapaResultado.get("presupuestoDetalles");
			presupuestoDetalleColeccion = new Vector<PresupuestoDetalleIf>(presupuestoDetalles);
			
			Collection<PresupuestoDetalleIf> presupuestoDetallesP = (Collection<PresupuestoDetalleIf>)
			mapaResultado.get("presupuestoDetallesP");
			presupuestoDetalleColeccionP = new Vector<PresupuestoDetalleIf>(presupuestoDetallesP);
		
			ordenTrabajo = (OrdenTrabajoIf) mapaResultado.get("ordenTrabajo");
			ordenTD = (OrdenTrabajoDetalleIf) mapaResultado.get("ordenTrabajoDetalle");
			
			SpiritAlert.createAlert("Presupuesto guardado con éxito",SpiritAlert.INFORMATION);
			
			reporte();
			
			// Para los archivos
			actualizarArchivosDesdeCliente(ordenTrabajo);
			
			actualizarPresupuestoProceso(presupuesto, ordenTD, usuario, false);
			
			//los dos metodos comentados a continuacion servian para limpiar la pantalla luego de guardar.
			//productoClienteList.clear();
			//showSaveMode();
			
			//metodo para dejar datos en pantalla luego de guardar
			presentarDatosGuardados();
			

		} catch (Exception e) {
			if (e instanceof GenericBusinessException) {
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			} else {
				SpiritAlert.createAlert("Error al guardar la información",SpiritAlert.ERROR);
			}
			e.printStackTrace();
		}
	}
	
	private void presentarDatosGuardados() throws GenericBusinessException {
		setUpdateMode();	
		
		//busca la nueva coleccion de detalles y la ordeno
		ArrayList<PresupuestoDetalleIf> listaPlantillaDetalle = (ArrayList<PresupuestoDetalleIf>)SessionServiceLocator
		.getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(presupuesto.getId());
		
		Collections.sort((ArrayList) listaPlantillaDetalle,ordenadorPresupuestoDetallePorId);
		
		ordenesCompraParaModificar.clear();
		
		//cargar mapa de ordenes de compra del presupuesto
		if(presupuesto != null){
			mapaOrdenesCompra.clear();
			Collection ordenesCompraPorPresupuesto = SessionServiceLocator.getOrdenCompraSessionService().findOrdenCompraByPresupuestoId(presupuesto.getId());
			Iterator ordenesCompraPorPresupuestoIt = ordenesCompraPorPresupuesto.iterator();
			while(ordenesCompraPorPresupuestoIt.hasNext()){
				OrdenCompraIf ordenCompra = (OrdenCompraIf)ordenesCompraPorPresupuestoIt.next();
				mapaOrdenesCompra.put(ordenCompra.getId(), ordenCompra);
			}
		}
		
		getTxtCodigo().setText(presupuesto.getCodigo());
		if(presupuesto.getFechaAceptacion() != null){
			getCmbFechaAprobacion().setDate(presupuesto.getFechaAceptacion());
		}		
		
		presupuestoDetalleColeccion.clear();
		
		//para setear en la tabla las ordenes que han sido creadas
		int indiceTabla = 0;
		for (PresupuestoDetalleIf pd : listaPlantillaDetalle ) {
			presupuestoDetalleIf = pd;				
			if (presupuestoDetalleIf.getReporte().equals(REPORTE_NO)) {
				presupuestoDetalleColeccion.add(presupuestoDetalleIf);
				
				OrdenCompraIf oc = null;
				if(presupuestoDetalleIf.getOrdenCompraId() != null){
					oc = mapaOrdenesCompra.get(presupuestoDetalleIf.getOrdenCompraId());
					if(oc == null){
						oc = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(presupuestoDetalleIf.getOrdenCompraId());
					}
				}
				
				String ordenCompra = "";
				if(oc != null){
					ordenCompra = oc.getCodigo()+"-"+oc.getRevision();
				}
				
				modelPresupuestoDetalle.setValueAt(ordenCompra, indiceTabla, 8);
				indiceTabla++;
			}				
		}
				
		//por si cambio el estado
		String estadoLetra = presupuesto.getEstado();
		EstadoPresupuesto estado = EstadoPresupuesto.getEstadoPresupuestoByLetra(estadoLetra);
		getCmbEstado().setSelectedItem(estado);
	}	

	private boolean compararTotalDetalleDifiereTotalReporte() {
		totalVentaReporte = new BigDecimal(0);
		BigDecimal precioVenta = new BigDecimal(0);
		BigDecimal cantidad = new BigDecimal(0);

		for (PresupuestoDetalleIf presupuestoDetalle : presupuestoDetalleColeccion) {
			precioVenta = presupuestoDetalle.getPrecioventa();
			cantidad = presupuestoDetalle.getCantidad();
			totalVentaReporte = totalVentaReporte.add(precioVenta
					.multiply(cantidad));
		}
		BigDecimal diferencia = totalVentaReporte.subtract(totalVentaReporteP)
				.abs();
		if (diferencia.compareTo(new BigDecimal(0.02)) == 1) {
			return true;
		}
		return false;
	}

	public void reportOrdenCompra(OrdenCompraIf ordenCompra) {
		int opcion = JOptionPane.showOptionDialog(null,
				"¿Desea generar el reporte de la Orden de Compra?",
				"Confirmación", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, si);
		if (opcion == JOptionPane.YES_OPTION) {
			imprimirOrden(ordenCompra);
		}
	}
	
	public void imprimirOrden(OrdenCompraIf ordenCompra){
		try {
			List<OrdenCompraDetalleIf> ordenCompraDetalleColeccion = new ArrayList<OrdenCompraDetalleIf>();

			ordenCompraDetalleColeccion = null;
			ordenCompraDetalleColeccion = new ArrayList<OrdenCompraDetalleIf>();
			ordenCompraDetalleColeccion = (List) SessionServiceLocator.getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByOrdencompraId(ordenCompra.getId());
			llenarOrdenCompraDetalleDataColeccion(ordenCompraDetalleColeccion, ordenCompra.getPorcentajeDescuentoEspecial());				

			String fileName = "jaspers/compras/RPOrdenCompra.jasper";
			HashMap<String,Object> parametrosMap = new HashMap<String,Object>();
			EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
			parametrosMap.put("empresa", empresa.getNombre());
			parametrosMap.put("ruc", "RUC: " + empresa.getRuc());
			parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
			parametrosMap.put("usuario", Parametros.getUsuario());
			OficinaIf oficina = (OficinaIf) Parametros.getOficina();
			parametrosMap.put("direccion", oficina.getDireccion());
			parametrosMap.put("telefono", "Teléfono: "
					+ oficina.getTelefono() + "    Fax: " + oficina.getFax());
			parametrosMap.put("numeroOrden", "Orden de Contratación No. " + ordenCompra.getCodigo() + " - " + ordenCompra.getRevision());

			if (ordenCompra.getSolicitudcompraId() != null) {
				SolicitudCompraIf solicitudCompra = SessionServiceLocator
						.getSolicitudCompraSessionService()
						.getSolicitudCompra(ordenCompra.getSolicitudcompraId());
				parametrosMap.put("numeroSolicitud", solicitudCompra.getCodigo());
				
				if ((solicitudCompra.getTipoReferencia() != null) 
						&& solicitudCompra.getTipoReferencia().equals(TIPO_PRESUPUESTO)) {
					
					PresupuestoIf presupuestoIf = (PresupuestoIf) SessionServiceLocator
							.getPresupuestoSessionService()
							.findPresupuestoByCodigo(solicitudCompra.getReferencia()).iterator().next();
					parametrosMap.put("presupuesto", presupuestoIf.getCodigo());
					parametrosMap.put("mesPresupuesto", Utilitarios
							.getFechaMesAnioUppercase(presupuestoIf.getFecha()));
					
					if (presupuestoIf.getOrdentrabajodetId() != null) {
						
						if(presupuestoIf.getClienteOficinaId() != null){
							ClienteOficinaIf clienteOficina = mapaClienteOficinas.get(presupuestoIf.getClienteOficinaId());
							ClienteIf cliente = mapaClientes.get(clienteOficina.getClienteId());
							parametrosMap.put("cliente", cliente.getNombreLegal());
						}else{
							OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuestoIf.getOrdentrabajodetId());
							OrdenTrabajoIf ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
							ClienteOficinaIf clienteOficina = mapaClienteOficinas.get(ordenTrabajo.getClienteoficinaId());
							ClienteIf cliente = mapaClientes.get(clienteOficina.getClienteId());
							parametrosMap.put("cliente", cliente.getNombreLegal());
						}						
						
						PresupuestoProductoIf presupuestoProducto = (PresupuestoProductoIf)SessionServiceLocator.getPresupuestoProductoSessionService().findPresupuestoProductoByPresupuestoId(presupuestoIf.getId()).iterator().next();
												
						ProductoClienteIf productoCliente = SessionServiceLocator
								.getProductoClienteSessionService()
								.getProductoCliente(presupuestoProducto.getProductoClienteId());
						parametrosMap.put("producto", productoCliente.getNombre());
					} else {
						parametrosMap.put("cliente", "N/A");
						parametrosMap.put("producto", "N/A");
					}
				} else {
					parametrosMap.put("cliente", "N/A");
					parametrosMap.put("producto", "N/A");
					parametrosMap.put("presupuesto", "N/A");
					parametrosMap.put("mesPresupuesto", "N/A");
				}
			} else {
				parametrosMap.put("numeroSolicitud", "N/A");
				parametrosMap.put("cliente", "N/A");
				parametrosMap.put("producto", "N/A");
				parametrosMap.put("presupuesto", "N/A");
				parametrosMap.put("mesPresupuesto", "N/A");
			}

			String fechaActual = Utilitarios.dateHoraHoy();
			String year = fechaActual.substring(0, 4);
			String month = fechaActual.substring(5, 7);
			String day = fechaActual.substring(8, 10);
			String fechaEmision = day + " "
					+ Utilitarios.getNombreMes(Integer.parseInt(month))
							.toLowerCase() + " del " + year;
			parametrosMap.put("fechaEmision", fechaEmision);
			proveedorIf = mapaClienteOficinas.get(ordenCompra.getProveedorId());
			ClienteIf clienteIf = mapaClientes.get(proveedorIf.getClienteId());						
			
			parametrosMap.put("proveedor", clienteIf.getNombreLegal());
			parametrosMap.put("rucProveedor", clienteIf.getIdentificacion());
			parametrosMap.put("proveedorOficina", proveedorIf.getDescripcion());
			
			TipoProveedorIf tipoProveedor = SessionServiceLocator.getTipoProveedorSessionService().getTipoProveedor(clienteIf.getTipoproveedorId());
			if (tipoProveedor.getTipo().equals(TIPO_MEDIOS)) {
				parametrosMap.put("sirvanse", "Publicar");					
			}else{
				parametrosMap.put("sirvanse", "Producir");					
			}
			
			parametrosMap.put("mesOrden", Utilitarios
					.getFechaMesAnioUppercase(ordenCompra.getFecha()));
			parametrosMap.put("porcentajeIVA", formatoEntero
					.format(Parametros.getIVA()));
							
			/*bonificacionOC = sumaOC.subtract(descuentoOC).multiply(ordenCompra.getPorcentajeDescuentosVariosCompra().divide(new BigDecimal(100)));
			ivaOC = (sumaOC.subtract(descuentoOC).subtract(bonificacionOC)).multiply(BigDecimal.valueOf(IVA));
			totalOC = (sumaOC.subtract(descuentoOC).subtract(bonificacionOC)).add(ivaOC);*/			
			
			parametrosMap.put("suma", sumaOC);
			parametrosMap.put("porcentajeDescuentoEspecial", formatoDecimal.format(ordenCompra.getPorcentajeDescuentoEspecial()));
			double porcentajeDescuentoEspecial = ordenCompra.getPorcentajeDescuentoEspecial().doubleValue() / 100;
			double descuentoEspecial = sumaOC.doubleValue() * porcentajeDescuentoEspecial;
			parametrosMap.put("descuentoEspecial", BigDecimal.valueOf(descuentoEspecial));
			
			double porcentajeDescuentoCompra = 0D;
			if(sumaOC.compareTo(new BigDecimal(0)) != 0){
				porcentajeDescuentoCompra = (ordenCompra.getDescuentoAgenciaCompra().doubleValue() * 100) / (sumaOC.doubleValue() - descuentoEspecial);
			}
				
			parametrosMap.put("porcentajeDescuento", formatoDecimal.format(Utilitarios.redondeoValor(porcentajeDescuentoCompra)));
			parametrosMap.put("descuento", descuentoOC);
			parametrosMap.put("porcentajeComisionAgencia", formatoDecimal.format(Utilitarios.redondeoValor(ordenCompra.getPorcentajeDescuentosVariosCompra())));
			parametrosMap.put("bonificacionOC", bonificacionOC);
			parametrosMap.put("iva", ivaOC);
			parametrosMap.put("total", totalOC);
			
			String elaboradoPor = "";
			if (ordenCompra.getEmpleadoId() != null) {
				EmpleadoIf empleado = mapaEmpleados.get(ordenCompra.getEmpleadoId());
				elaboradoPor = empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0];
			}				
			parametrosMap.put("elaboradoPor", elaboradoPor);
						
			//busco si el proveedor tiene valores de retencion renta o iva
			Collection proveedorRetenciones = SessionServiceLocator.getClienteRetencionSessionService().findClienteRetencionByClienteId(clienteIf.getId());
			
			if(proveedorRetenciones.size() > 0){
				fileName = "jaspers/compras/RPOrdenCompraConRetencion.jasper";
			}
			
			Iterator proveedorRetencionesIt = proveedorRetenciones.iterator();
			while(proveedorRetencionesIt.hasNext()){
				ClienteRetencionIf clienteRetencion = (ClienteRetencionIf)proveedorRetencionesIt.next();
				
				SriAirIf sriAir = SessionServiceLocator.getSriAirSessionService().getSriAir(clienteRetencion.getSriAirId());
				SriIvaRetencionIf sriIvaRetencion = SessionServiceLocator.getSriIvaRetencionSessionService().getSriIvaRetencion(clienteRetencion.getSriIvaRetencionId());
				
				double retencionRenta = 0D;
				if(sriAir.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
					double porcentajeRetencionRenta = sriAir.getPorcentaje().doubleValue();
					double subTotalOrden = sumaOC.doubleValue() - descuentoEspecial - descuentoOC.doubleValue() - bonificacionOC.doubleValue();
					retencionRenta = subTotalOrden * (porcentajeRetencionRenta / 100D);
					
					parametrosMap.put("porcentajeRetencionRenta", formatoDecimal.format(porcentajeRetencionRenta));
					parametrosMap.put("retencionRenta", BigDecimal.valueOf(retencionRenta));
				}
				
				double retencionIva = 0D;
				if(sriIvaRetencion.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
					double porcentajeRetencionIva = sriIvaRetencion.getPorcentaje().doubleValue();
					double ivaOrden = ivaOC.doubleValue();
					retencionIva = ivaOrden * (porcentajeRetencionIva / 100D);
					
					parametrosMap.put("porcentajeRetencionIva", formatoDecimal.format(porcentajeRetencionIva));
					parametrosMap.put("retencionIva", BigDecimal.valueOf(retencionIva));
				}
				
				double totalPagar = totalOC.doubleValue() - retencionRenta - retencionIva;
				parametrosMap.put("totalPagar", BigDecimal.valueOf(totalPagar));
			}

			
			ReportModelImpl.processReport(fileName, parametrosMap,
					ordenCompraDetalleDataColeccion, true);

		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void llenarOrdenCompraDetalleDataColeccion(
			List<OrdenCompraDetalleIf> ordenCompraDetalleColeccion,BigDecimal porcentajeDescuentoEspecial) {
		sumaOC = new BigDecimal(0);
		descuentoOC = new BigDecimal(0);
		bonificacionOC = new BigDecimal(0);
		ivaOC = new BigDecimal(0);
		totalOC = new BigDecimal(0);
		ordenCompraDetalleDataColeccion = null;
		ordenCompraDetalleDataColeccion = new Vector<OrdenCompraDetalleReporteData>();
		int item = 1;
		for ( OrdenCompraDetalleIf ordenCompraDetalle : ordenCompraDetalleColeccion ){
			OrdenCompraDetalleReporteData ordenCompraDetalleData = new OrdenCompraDetalleReporteData();
			ordenCompraDetalleData.setItem(String.valueOf(item));
			
			String concepto = ordenCompraDetalle.getDescripcion() != null ? ordenCompraDetalle.getDescripcion() : "";
			if(ordenCompraDetalle.getFechaPublicacion() != null){
				String fechaCompleta = Utilitarios.getFechaDiaSemanaDiaMesAnioUppercase(ordenCompraDetalle.getFechaPublicacion());
				concepto = concepto + "\n\nPUBLICACIÓN: " + fechaCompleta + "\n";
			}
			
			//ordenCompraDetalleData.setDetalle(ordenCompraDetalle.getDescripcion() != null ? ordenCompraDetalle.getDescripcion().replaceAll("\t", " ") : "");
			ordenCompraDetalleData.setDetalle(concepto.replaceAll("\t", " "));
			
			ordenCompraDetalleData.setCantidad(ordenCompraDetalle.getCantidad().toString());
			ordenCompraDetalleData.setParcial(formatoDecimal.format(Utilitarios.redondeoValor(ordenCompraDetalle.getValor().doubleValue())));
			double cantidad = Double.parseDouble(ordenCompraDetalle.getCantidad().toString());
			double valor = Double.parseDouble(ordenCompraDetalle.getValor().toString());
			double subtotal = cantidad * valor;
			double descuentoEspecial = subtotal * (porcentajeDescuentoEspecial.doubleValue() / 100);			
			double descuentoSubtotal = Double.parseDouble(ordenCompraDetalle.getDescuentoAgenciaCompra().toString());
			double otroImpuestoSubtotal = Double.parseDouble(ordenCompraDetalle.getOtroImpuesto().toString());
			ordenCompraDetalleData.setTotal(formatoDecimal.format(Utilitarios.redondeoValor(subtotal)));
			sumaOC = sumaOC.add(BigDecimal.valueOf(subtotal));
			descuentoOC = descuentoOC.add(ordenCompraDetalle.getDescuentoAgenciaCompra());
			
			double porcentajeBonificacion = ordenCompraDetalle.getPorcentajeDescuentosVariosCompra().doubleValue() / 100;
			double bonificacion = (subtotal - descuentoEspecial) * porcentajeBonificacion;
			bonificacionOC = bonificacionOC.add(BigDecimal.valueOf(bonificacion));
			
			double iva = Double.parseDouble(ordenCompraDetalle.getIva().toString());
			this.ivaOC = this.ivaOC.add(ordenCompraDetalle.getIva());
			double ice = Double.parseDouble(ordenCompraDetalle.getIce().toString());
			subtotal = subtotal - descuentoEspecial - descuentoSubtotal - bonificacion + iva + ice + otroImpuestoSubtotal;
			totalOC = totalOC.add(BigDecimal.valueOf(subtotal));
			ordenCompraDetalleDataColeccion.add(ordenCompraDetalleData);
			item++;
		}
	}

	public void update() {
		crearListaProductos();
		if (validateFields()) {
			if (compararTotalDetalleDifiereTotalReporte()) {
				int opcion = JOptionPane.showOptionDialog(null,"¡Los totales de las secciones Detalle ($"+ 
					formatoDecimal.format(totalVentaReporte)+ ") y Reporte ($"+ formatoDecimal.format(totalVentaReporteP)+
					") son diferentes! ¿Desea actualizar el Presupuesto?","Confirmación", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					actualizarPresupuesto();
				}
			} else {
				actualizarPresupuesto();
			}			
		}
	}
	
	private void actualizarPresupuesto() {
		boolean bandera = false;
		try {
			Map<String,Object> aMap = new HashMap<String,Object>();
			aMap.put("tiporeferencia", TIPO_PRESUPUESTO);
			aMap.put("referencia", presupuesto.getCodigo());
			aMap.put("estado", EstadoPedido.COMPLETO.getLetra());
			Collection pedidoColeccion = SessionServiceLocator.getPedidoSessionService().findPedidoByQuery(aMap);
			
			if (pedidoColeccion.size() > 0) {
				int opcion = JOptionPane.showOptionDialog(null,"Ya hay una Factura de este presupuesto, ¿Desea de todas formas cambiar el presupuesto?",
					"Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					actualizar();
				}
			} else {
				actualizar();
			}

		} catch (GenericBusinessException e) {
			if (bandera) {
				SpiritAlert.createAlert("Error al actualizar el Presupuesto. No se pudo actualizar la Orden asociada al Presupuesto.",SpiritAlert.ERROR);
			} else {
				SpiritAlert.createAlert("Error al actualizar el Presupuesto!",SpiritAlert.ERROR);
			}
			e.printStackTrace();
		}
	}

	private void actualizar() throws GenericBusinessException {
		OrdenTrabajoDetalleIf ordenDetalle = ((InfoOrdenTrabajoDetalle) getCmbOrdenTrabajoDetalle()
				.getSelectedItem()).getOrdenTrabajoDetalleIf();

		PresupuestoIf presupuestoActualizado = registrarPresupuestoForUpdate(ordenDetalle);
		presupuesto = presupuestoActualizado;
		UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
		actualizarPresupuestoProceso(presupuestoActualizado,ordenDetalle,usuario,true);
		
		//los dos metodos comentados a continuacion servian para limpiar la pantalla luego de guardar.
		//productoClienteList.clear();ordenesCompraParaModificar
		//showSaveMode();
		
		//metodo para dejar datos en pantalla luego de guardar
		presentarDatosGuardados();
	}

	private void actualizarPresupuestoProceso(PresupuestoIf presupuestoActualizado,
			OrdenTrabajoDetalleIf ordenDetalle, UsuarioIf usuario, boolean esActualizacion)
			throws GenericBusinessException {
		
		boolean generarOrdenesCompra = false;
		//boolean eliminarOrdenesCompraPrevias = false;
		
		//en la primera no se elimina ordenes previas si es actualizacion
		if ( !esActualizacion ){
			generarOrdenesCompra = confirmarGeneracionOrdenesCompra();
			//confirmarActualizacionOrdenesCompra();
			//eliminarOrdenesCompraPrevias = confirmarEliminarOrdenesCompraPrevias();
		}
		
		Map<String,Object> mapaResultado = SessionServiceLocator.getPresupuestoSessionService()
			.actualizarPresupuestoServer(generarOrdenesCompra,//eliminarOrdenesCompraPrevias,
				presupuestoActualizado,presupuestoDetalleColeccion,presupuestoDetalleColeccionP, ordenDetalle,
				presupuestoDetalleEliminadosColeccion,productoClienteList,ordenesCompraParaModificar,usuario,
				IVA,Parametros.getCodMoneda(),Parametros.getIdOficina(),tarea,false);
		
		OrdenTrabajoIf ordenTrabajo = (OrdenTrabajoIf) mapaResultado.get("ordenTrabajo");
		presupuesto = (PresupuestoIf) mapaResultado.get("presupuesto");

		Collection<PresupuestoDetalleIf> presupuestoDetalles = (Collection<PresupuestoDetalleIf>)
		mapaResultado.get("presupuestoDetalles");
		presupuestoDetalleColeccion = new Vector<PresupuestoDetalleIf>(presupuestoDetalles);

		Collection<PresupuestoDetalleIf> presupuestoDetallesP = (Collection<PresupuestoDetalleIf>)
		mapaResultado.get("presupuestoDetallesP");
		presupuestoDetalleColeccionP = new Vector<PresupuestoDetalleIf>(presupuestoDetallesP);
		
		ordenesCompraMap = (Map<OrdenCompraIf, List<OrdenCompraDetalleIf>>) mapaResultado.get("ordenesCompraMap");
		actualizarArchivosDesdeCliente(ordenTrabajo);
		
		if ( esActualizacion ){
			SpiritAlert.createAlert("Presupuesto actualizado con éxito",SpiritAlert.INFORMATION);
			EstadoPresupuesto estado = EstadoPresupuesto.getEstadoPresupuestoByLetra(presupuestoActualizado.getEstado());
			if ( estado != EstadoPresupuesto.CANCELADO ){
				reporte();
			
				generarOrdenesCompra = confirmarGeneracionOrdenesCompra();
				//if ( generarOrdenesCompra )
				confirmarActualizacionOrdenesCompra();
				
				presupuestoDetalleEliminadosColeccion =  new Vector<PresupuestoDetalleIf>();
				
				mapaResultado = SessionServiceLocator.getPresupuestoSessionService()
				.actualizarPresupuestoServer(generarOrdenesCompra,//eliminarOrdenesCompraPrevias,
					presupuesto,presupuestoDetalleColeccion,presupuestoDetalleColeccionP, ordenDetalle,
					presupuestoDetalleEliminadosColeccion,productoClienteList,ordenesCompraParaModificar,usuario,
					IVA,Parametros.getCodMoneda(),Parametros.getIdOficina(),tarea, esActualizacion);
			}
		}
		
		String ordenesComprasEliminadasConExito = (String)
			mapaResultado.get("ordenesComprasActualizadasConExito");
		
		if ( ordenesComprasEliminadasConExito != null )
			SpiritAlert.createAlert(ordenesComprasEliminadasConExito,SpiritAlert.INFORMATION);
		
		Map<String,OrdenCompraIf> mapaOrdenesCreadas = (Map<String,OrdenCompraIf>) 
		mapaResultado.get("ordenesComprasCreadas");
		if ( mapaOrdenesCreadas != null && mapaOrdenesCreadas.size() > 0 ){
			for (String mensaje : mapaOrdenesCreadas.keySet()){
				SpiritAlert.createAlert(mensaje,SpiritAlert.INFORMATION);
				OrdenCompraIf ordenCompra = mapaOrdenesCreadas.get(mensaje);
				reportOrdenCompra(ordenCompra);
			}
		}
	
		Map<String,OrdenCompraIf> mapaOrdenesModificadas = (Map<String,OrdenCompraIf>) 
		mapaResultado.get("ordenesComprasModificadas");
		if ( mapaOrdenesModificadas != null && mapaOrdenesModificadas.size() > 0 ){
			for (String mensaje : mapaOrdenesModificadas.keySet()){
				SpiritAlert.createAlert(mensaje,SpiritAlert.INFORMATION);
				OrdenCompraIf ordenCompra = mapaOrdenesModificadas.get(mensaje);
				reportOrdenCompra(ordenCompra);
			}
		}		
	}
	
	private boolean confirmarGeneracionOrdenesCompra(){
		
		Set<Integer> setOrdenesNuevas = new HashSet<Integer>();
		
		Map<Integer,Collection<PresupuestoDetalleIf>> mapaOrdenPresupuestos = 
			new HashMap<Integer, Collection<PresupuestoDetalleIf>>();
		
		for ( PresupuestoDetalleIf pd : presupuestoDetalleColeccion ){
			Integer orden = pd.getOrden();
			Collection<PresupuestoDetalleIf> lista = mapaOrdenPresupuestos.get(orden);
			if ( lista == null ){
				lista = new ArrayList<PresupuestoDetalleIf>();
			}
			lista.add(pd);
			mapaOrdenPresupuestos.put(orden, lista);
		}
		
		for ( Integer orden : mapaOrdenPresupuestos.keySet()){
			boolean agregar = true;
			Collection<PresupuestoDetalleIf> lista = mapaOrdenPresupuestos.get(orden);
			for (PresupuestoDetalleIf pd : lista){
				if ( pd.getOrdenCompraId() != null )
					agregar = false;
			}
			if (agregar)
				setOrdenesNuevas.add(orden);
		}
		
		if ( setOrdenesNuevas.size() > 0 ){
			int opcion1 = JOptionPane.showOptionDialog(null,
					"¿Desea generar las Ordenes de Compra?", "Confirmación",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, no);
			if (opcion1 == JOptionPane.YES_OPTION) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	private void actualizarArchivosDesdeCliente(OrdenTrabajoIf ordenTrabajo)
			throws GenericBusinessException {
		if (!Parametros.getUrlCarpetaServidor().equals("")) {
			//ClienteOficinaIf clienteOficina = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenTrabajo.getClienteoficinaId());
			ClienteOficinaIf clienteOficina = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficinas, ordenTrabajo.getClienteoficinaId());
			//ClienteIf cliente = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficina.getClienteId());
			ClienteIf cliente = GeneralUtil.verificarMapaCliente(mapaClientes, clienteOficina.getClienteId());
			String slashUrl = Parametros.getUrlCarpetaServidor().substring(Parametros.getUrlCarpetaServidor().length() - 1,
				Parametros.getUrlCarpetaServidor().length());
			String slashRuta = Parametros.getRutaWindowsCarpetaServidor().substring(Parametros.getRutaWindowsCarpetaServidor().length() - 1,
				Parametros.getRutaWindowsCarpetaServidor().length());

			for (File archivo : archivosColeccion) {
				if (archivo != null&& !archivo.getName().equals("")) {
					FileInputStreamSerializable archivoFuente = new FileInputStreamSerializable(archivo);
					int n = SessionServiceLocator.getFileManagerSessionService().guardarArchivoZip(archivoFuente,
						Parametros.getUrlCarpetaServidor()+ cliente.getIdentificacion().replaceAll(" ","_")+ slashUrl,archivo.getName());
					if (n == 3) {
						SpiritAlert.createAlert("Archivo: "+ archivo.getName()+ " no se guard\u00f3",SpiritAlert.WARNING);
					}
				}
			}
			SessionServiceLocator.getPresupuestoSessionService().actualizarArchivosPresupuesto(presupuesto,
				archivosPresupuestoColeccion,archivosEliminadosColeccion,
				Parametros.getRutaWindowsCarpetaServidor()+ cliente.getIdentificacion().replaceAll(" ", "_")+ slashRuta + slashRuta);
		}
	}

	public void delete() {
		try {
			boolean existeOrdenActiva = false;
			HashMap aMapSolicitud = new HashMap();
			aMapSolicitud.put("tipoReferencia", TIPO_PRESUPUESTO);
			aMapSolicitud.put("referencia", presupuesto.getCodigo());
			Collection solicitudesCompraAsociadas = SessionServiceLocator.getSolicitudCompraSessionService().findSolicitudCompraByQuery(aMapSolicitud);
			Iterator solicitudesCompraAsociadasIt = solicitudesCompraAsociadas.iterator();
			while(solicitudesCompraAsociadasIt.hasNext()){
				SolicitudCompraIf solicitudCompra = (SolicitudCompraIf)solicitudesCompraAsociadasIt.next();
				
				Collection ordenesPorSolicitud = SessionServiceLocator.getOrdenCompraSessionService().findOrdenCompraBySolicitudcompraId(solicitudCompra.getId());
				Iterator ordenesPorSolicitudIt = ordenesPorSolicitud.iterator();
				while(ordenesPorSolicitudIt.hasNext()){
					OrdenCompraIf ordenCompra = (OrdenCompraIf)ordenesPorSolicitudIt.next();
					if(!ordenCompra.getEstado().equals(EstadoOrdenCompra.ANULADA.getLetra())){
						existeOrdenActiva = true;
					}
				}
			}			
			
			boolean presupuestoFactura = false;
			
			if(presupuesto.getEstado().equals(EstadoPresupuesto.FACTURADO.getLetra())){
				presupuestoFactura = true;
			}
			
			HashMap aMapPedido = new HashMap();
			aMapPedido.put("tiporeferencia", TIPO_PRESUPUESTO);
			aMapPedido.put("referencia", presupuesto.getCodigo());
			ArrayList pedidosAsociados = (ArrayList)SessionServiceLocator.getPedidoSessionService().findPedidoByQuery(aMapPedido);
			
			if(pedidosAsociados.size() > 0){
				presupuestoFactura = true;
			}
			
			Collection presupuestoDetalles = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(presupuesto.getId());
			Iterator presupuestoDetallesIt = presupuestoDetalles.iterator();
			while(presupuestoDetallesIt.hasNext()){
				PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf)presupuestoDetallesIt.next();
				Collection presupuestoFacturacion = SessionServiceLocator.getPresupuestoFacturacionSessionService().findPresupuestoFacturacionByPresupuestoDetalleId(presupuestoDetalleIf.getId());
				Iterator presupuestoFacturacionIt = presupuestoFacturacion.iterator();
				while(presupuestoFacturacionIt.hasNext()){
					PresupuestoFacturacionIf presupuestoFacturacionIf = (PresupuestoFacturacionIf)presupuestoFacturacionIt.next();
					//si la factura no esta anulada
					if(!presupuestoFacturacionIf.getEstado().equals("A")){
						presupuestoFactura = true;
					}
				}
			}
						
			if(existeOrdenActiva){
				SpiritAlert.createAlert("El Prespuesto no puede ser cancelado porque tiene Ordenes asociadas.",SpiritAlert.WARNING);
							
			}else if(presupuestoFactura){
				SpiritAlert.createAlert("El Prespuesto no puede ser cancelado porque esta Facturado o tiene Pedidos/Facturas asociadas.",SpiritAlert.WARNING);
				
			}else{
				OrdenTrabajoDetalleIf ordendetalle = ((InfoOrdenTrabajoDetalle) getCmbOrdenTrabajoDetalle()
						.getSelectedItem()).getOrdenTrabajoDetalleIf();
				
				ordenTD = ordendetalle;			
				ordenTD.setEstado(EstadoOrdenTrabajo.PENDIENTE.getLetra());
				
				//SessionServiceLocator.getPresupuestoSessionService().eliminarPresupuesto(presupuesto.getId(), ordenTD);
				//SpiritAlert.createAlert("Presupuesto eliminado con éxito",SpiritAlert.INFORMATION);
				presupuesto.setEstado(EstadoPresupuesto.CANCELADO.getLetra());
				SessionServiceLocator.getPresupuestoSessionService().savePresupuesto(presupuesto);
				SpiritAlert.createAlert("El Presupuesto fue CANCELADO con éxito",SpiritAlert.INFORMATION);
				
				showSaveMode();
			}		
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			if (e instanceof GenericBusinessException) {
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			} else {
				SpiritAlert.createAlert("Error al eliminar la Propuesta!",SpiritAlert.ERROR);
			}
		}
	}
	
	public void reporte(){
		try {
			String siCliente = "Sí, Cliente";
			String siInterno =  "Sí, Interno";
			Object[] opciones = { siCliente, siInterno, no };
			
			if (presupuesto != null) {
				int opcion = JOptionPane.showOptionDialog(null,
						"¿Desea generar el reporte del Presupuesto?",
						"Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, opciones, si);
				
				int opcionSiCliente = 0;
				int opcionSiInterno = 1;
				int opcionNo = 2;
				
				if (opcion == opcionSiCliente || opcion == opcionSiInterno) {

					DecimalFormat formatoDecimalParcialCuatro = new DecimalFormat("#,##0.0000");
					DecimalFormat formatoDecimalParcialTres = new DecimalFormat("#,##0.000");
					DecimalFormat formatoDecimalParcialDos = new DecimalFormat("#,##0.00");
					presupuestoDetalleDataColeccion = null;
					presupuestoDetalleDataColeccion = new Vector<PresupuestoDetalleReporteData>();

					if(opcion == opcionSiInterno){
						generarPresupuestoDetalleDataColeccionInterno(opcion,
								opcionSiInterno, formatoDecimalParcialCuatro,
								formatoDecimalParcialTres, formatoDecimalParcialDos);
					}else{
						generarPresupuestoDetalleDataColeccionCliente(opcion,
								opcionSiInterno, formatoDecimalParcialCuatro,
								formatoDecimalParcialTres, formatoDecimalParcialDos);
					}					

					String fileName = "jaspers/medios/RPPresupuesto.jasper";
					
					if(opcion == opcionSiInterno){
						fileName = "jaspers/medios/RPPresupuestoInterno.jasper";
					}
					
					/*if(comisionAgencia > 0 && bonificacionVenta > 0){
						fileName = "jaspers/medios/RPPresupuesto.jasper";
					}else if(comisionAgencia > 0){
						fileName = "jaspers/medios/RPPresupuestoSinBonificacion.jasper";
					}else{
						fileName = "jaspers/medios/RPPresupuestoSinComision.jasper";
					}*/
					
					HashMap<String,Object> parametrosMap = new HashMap<String,Object> ();
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", "RUC: " + empresa.getRuc());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("usuario", Parametros.getUsuario());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					parametrosMap.put("direccion", oficina.getDireccion());
					parametrosMap.put("telefono", "Teléfono: "
							+ oficina.getTelefono() + "    Fax: "
							+ oficina.getFax());
					parametrosMap.put("numeroPresupuesto", "Presupuesto No. "
							+ presupuesto.getCodigo());

					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0, 4);
					String month = fechaActual.substring(5, 7);
					String day = fechaActual.substring(8, 10);
					String fechaEmision = day
							+ " "
							+ Utilitarios.getNombreMes(Integer.parseInt(month))
									.toLowerCase() + " del " + year;
					parametrosMap.put("fechaEmision", fechaEmision);

					OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator
							.getOrdenTrabajoDetalleSessionService()
							.getOrdenTrabajoDetalle(presupuesto.getOrdentrabajodetId());
					SubtipoOrdenIf subtipoOrden = mapaSubTiposOrden.get(ordenTrabajoDetalle.getSubtipoId());
					TipoOrdenIf tipoOrden = mapaTiposOrden.get(subtipoOrden.getTipoordenId());
					parametrosMap.put("ordenTipo", tipoOrden.getNombre());
					parametrosMap.put("ordenSubtipo", subtipoOrden.getNombre());
					OrdenTrabajoIf ordenTrabajo = SessionServiceLocator
							.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
					parametrosMap.put("numeroOrdenTrabajo", ordenTrabajo.getCodigo());
					
					ClienteOficinaIf clienteOficina = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficinas, ordenTrabajo.getClienteoficinaId());
					ClienteIf cliente = GeneralUtil.verificarMapaCliente(mapaClientes, clienteOficina.getClienteId() );
					parametrosMap.put("cliente", cliente.getNombreLegal());

					String productos = "";
					String marcas = "";
					for (ProductoClienteIf productoClienteIf : productoClienteList) {
						productos = productos + productoClienteIf.getNombre()+ ", ";
						if (!marcas.contains(productoClienteIf.getMarcaProductoNombre())) {
							marcas = marcas + productoClienteIf.getMarcaProductoNombre() + ", ";
						}
					}
					productos = productos.length() >= 2 ? productos.substring(
							0, productos.length() - 2) : "";
					marcas = marcas.length() >= 2 ? marcas.substring(0, marcas.length() - 2) : "";
					parametrosMap.put("productos", productos);
					parametrosMap.put("marca", marcas);

					parametrosMap.put("mesPresupuesto", Utilitarios.getFechaMesAnioUppercase(presupuesto.getFecha()));
					
					if(opcion == opcionSiInterno){
						parametrosMap.put("suma", BigDecimal.valueOf(valorBruto - descuentoEspecialTotalCompra - descuentoCompraTotal - bonificacionCompraTotal));
						parametrosMap.put("iva", BigDecimal.valueOf(ivaTotalAgencia));
						parametrosMap.put("total", BigDecimal.valueOf(totalAgencia));
					}else{
						parametrosMap.put("suma", BigDecimal.valueOf(subTotal - descuentoEspecialTotalVenta));
						parametrosMap.put("iva", BigDecimal.valueOf(ivaTotal));
						parametrosMap.put("total", BigDecimal.valueOf(total));
					}
					
					//el reporte de Presupuesto (para clientes) no tiene los campos de los descuentos totales porque ya van en cada detalle
					parametrosMap.put("descuento", BigDecimal.valueOf(descuentoTotal));
					parametrosMap.put("descuentosVariosVenta", BigDecimal.valueOf(descuentosVariosVentaTotal));
					parametrosMap.put("porcentajeComisionAgencia", formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
					parametrosMap.put("valorComision", BigDecimal.valueOf(comisionAgencia));
					parametrosMap.put("porcentajeIVA", formatoEntero.format(Parametros.getIVA()));
					
					String formaPago = "";
					
					if (tipoOrden.getTipo().equals(TIPO_PRESUPUESTO) && 
							getCmbFormaPago().getSelectedItem().toString().equals("PAGO 70 - 30")) {
						formaPago = "70% anticipo / 30% finalización del trabajo \nTiempo de producción a convenir a partir del anticipo.";
					} else {
						formaPago = getCmbFormaPago().getSelectedItem().toString();
					}
					parametrosMap.put("validezOferta", "Validez de la Oferta: "
							+ getTxtDiasValidez().getText() + " días.");
					parametrosMap.put("formaPago", "Forma de Pago: "+ formaPago);
					parametrosMap.put("tema", getTxtTemaCampana().getText());
					
					String elaboradoPor = "";
					if (presupuesto.getUsuarioCreacionId() != null) {
						if(presupuesto.getUsuarioActualizacionId() != null){
							UsuarioIf usuario = mapaUsuarios.get(presupuesto.getUsuarioActualizacionId());
							EmpleadoIf empleado = mapaEmpleados.get(usuario.getEmpleadoId());
							elaboradoPor = empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0];
						}else{
							UsuarioIf usuario = mapaUsuarios.get(presupuesto.getUsuarioCreacionId());
							EmpleadoIf empleado = mapaEmpleados.get(usuario.getEmpleadoId());	
							elaboradoPor = empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0];
						}					
					}				
					parametrosMap.put("elaboradoPor", elaboradoPor);

					// del presupuesto, de la orden de trabajo sacar el nombre del ejecutivo
					String nombreEjecutivo = "";
					EmpleadoIf emp = mapaEmpleados.get(ordenTrabajo.getEmpleadoId());
					if (emp != null)
						nombreEjecutivo = emp.getApellidos() + " " + emp.getNombres();
					parametrosMap.put("nombreEjecutivo", nombreEjecutivo);

					ReportModelImpl.processReport(fileName, parametrosMap,presupuestoDetalleDataColeccion, true);
				}
				
			} else {
				SpiritAlert.createAlert("No hay ningún presupuesto en pantalla",SpiritAlert.INFORMATION);
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
		} catch (ParseException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
		}
	}

	private void generarPresupuestoDetalleDataColeccionCliente(int opcion,
			int opcionSiInterno, DecimalFormat formatoDecimalParcialCuatro,
			DecimalFormat formatoDecimalParcialTres,
			DecimalFormat formatoDecimalParcialDos)
			throws GenericBusinessException {
		ArrayList<PresupuestoDetalleIf> detalle = new ArrayList<PresupuestoDetalleIf>(presupuestoDetalleColeccionP);
		
		Collections.sort(detalle,ordenadorPresupuestoDetallePorId);
		int item = 1;
		for (PresupuestoDetalleIf pd : detalle){
			presupuestoDetalleIf = pd;
			
			if ((presupuestoDetalleIf.getReporte() == null)
					|| presupuestoDetalleIf.getReporte().equals(REPORTE_SI)) {
				
				ProductoIf producto = GeneralUtil.verificarMapaProducto(mapaProductos, presupuestoDetalleIf.getProductoId());

				PresupuestoDetalleReporteData presupuestoDetalleReporteData = new PresupuestoDetalleReporteData();
				
				proveedorIf = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficinas, producto.getProveedorId());
				
				ClienteIf cliente = GeneralUtil.verificarMapaCliente(mapaClientes, proveedorIf.getClienteId());
				
				presupuestoDetalleReporteData.setProveedor(cliente.getIdentificacion() /*
					* + " - " +* cliente.getNombreLegal()*/);
				if (presupuestoDetalleIf.getPrecioventa().doubleValue() > 0) {
					
					presupuestoDetalleReporteData.setItem(String.valueOf(item));
					item++;
					presupuestoDetalleReporteData.setCantidad(formatoDecimal.format(Utilitarios.redondeoValor(presupuestoDetalleIf.getCantidad())));
								
					//seteo el valor parcial
					double precioVenta = 0D;
												
					//si el reporte es interno entonces en precio de venta va el valor bruto
					if(opcion == opcionSiInterno){
						precioVenta = presupuestoDetalleIf.getPrecioventa().doubleValue() * presupuestoDetalleIf.getCantidad().doubleValue();
					}
					
					//caso contrario solo va el valor parcial, o el parcial menos el descuento especial si hubiera.								
					else{
						precioVenta = presupuestoDetalleIf.getPrecioventa().doubleValue();
						
						if(presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta().compareTo(new BigDecimal(0)) == 1){
							double porcentajeDescuentoEspecialVenta = presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta().doubleValue() / 100;
							double descuentoEspecialVenta = precioVenta * porcentajeDescuentoEspecialVenta;
							precioVenta = precioVenta - descuentoEspecialVenta;
						}
					}							
																					
					//Proceso para saber cuantos decimales tiene el precio parcial
					//y segun eso ponerle el formato decimal adecuado.
					String valorParcial = String.valueOf(presupuestoDetalleIf.getPrecioventa().stripTrailingZeros().doubleValue());
					int posicionPuntoDecimal = valorParcial.indexOf(".");
					int longitudDecimales = posicionPuntoDecimal>=1?valorParcial.substring(posicionPuntoDecimal).length()-1:0;
					
					//segun el numero de decimales pongo el formato adecuado para el precio parcial.
					if(longitudDecimales >= 4){
						presupuestoDetalleReporteData.setParcial(formatoDecimalParcialCuatro.format(precioVenta));
					}else if(longitudDecimales >= 3){
						presupuestoDetalleReporteData.setParcial(formatoDecimalParcialTres.format(precioVenta));
					}else{
						presupuestoDetalleReporteData.setParcial(formatoDecimalParcialDos.format(precioVenta));
					}
					
					//seteo el valor total
					double precioTotal = 0D;	
					
					//si el reporte es interno entonces en precio de total es el valor neto (bruto - descuentos)
					if(opcion == opcionSiInterno){
						double porcentajeDescuentoEspecialVenta = presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta().doubleValue() / 100;
						double descuentoEspecialVenta = precioVenta * porcentajeDescuentoEspecialVenta;
						double porcentajeDescuentoAgenciaVenta = presupuestoDetalleIf.getPorcentajeDescuentoAgenciaVenta().doubleValue() / 100;
						double descuentoAgenciaVenta = (precioVenta - descuentoEspecialVenta) * porcentajeDescuentoAgenciaVenta;
						double porcentajeDescuentosVariosVenta = presupuestoDetalleIf.getPorcentajeDescuentosVariosVenta().doubleValue() / 100;
						double descuentosVariosVenta = (precioVenta - descuentoEspecialVenta) * porcentajeDescuentosVariosVenta;
						
						precioTotal = precioVenta - descuentoEspecialVenta - descuentoAgenciaVenta - descuentosVariosVenta;
					}
					
					//caso contrario solo va el valor total del precio de venta por la cantidad								
					else{
						precioTotal = precioVenta * presupuestoDetalleIf.getCantidad().doubleValue();									
					}
					
					presupuestoDetalleReporteData.setTotal(formatoDecimal.format(Utilitarios.redondeoValor(precioTotal)));
					
				} else {
					presupuestoDetalleReporteData.setItem("");
					presupuestoDetalleReporteData.setCantidad("");
					presupuestoDetalleReporteData.setParcial("");
					presupuestoDetalleReporteData.setTotal("");
				}
				
				String concepto = presupuestoDetalleIf.getConcepto();
				if(presupuestoDetalleIf.getFechaPublicacion() != null){
					String fechaCompleta = Utilitarios.getFechaDiaSemanaDiaMesAnioUppercase(presupuestoDetalleIf.getFechaPublicacion());
					concepto = concepto + "\n\nPUBLICACIÓN: " + fechaCompleta + "\n";
				}
				
				//presupuestoDetalleReporteData.setDetalle(presupuestoDetalleIf.getConcepto().replaceAll("\t", " "));
				presupuestoDetalleReporteData.setDetalle(concepto.replaceAll("\t", " "));
				presupuestoDetalleDataColeccion.add(presupuestoDetalleReporteData);
			}
		}
	}
	
	private void generarPresupuestoDetalleDataColeccionInterno(int opcion,
			int opcionSiInterno, DecimalFormat formatoDecimalParcialCuatro,
			DecimalFormat formatoDecimalParcialTres,
			DecimalFormat formatoDecimalParcialDos)
			throws GenericBusinessException {
		ArrayList<PresupuestoDetalleIf> detalle = new ArrayList<PresupuestoDetalleIf>(presupuestoDetalleColeccion);
		
		Collections.sort(detalle,ordenadorPresupuestoDetallePorId);
		int item = 1;
		for (PresupuestoDetalleIf pd : detalle){
			presupuestoDetalleIf = pd;
			
			if ((presupuestoDetalleIf.getReporte() == null)
					|| presupuestoDetalleIf.getReporte().equals(REPORTE_NO)) {
				
				ProductoIf producto = GeneralUtil.verificarMapaProducto(mapaProductos, presupuestoDetalleIf.getProductoId());

				PresupuestoDetalleReporteData presupuestoDetalleReporteData = new PresupuestoDetalleReporteData();
				
				proveedorIf = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficinas, producto.getProveedorId());
				
				ClienteIf cliente = GeneralUtil.verificarMapaCliente(mapaClientes, proveedorIf.getClienteId());
				
				presupuestoDetalleReporteData.setProveedor(cliente.getIdentificacion() /*
					* + " - " +* cliente.getNombreLegal()*/);
				if (presupuestoDetalleIf.getPrecioventa().doubleValue() > 0) {
					
					presupuestoDetalleReporteData.setItem(String.valueOf(item));
					item++;
					presupuestoDetalleReporteData.setCantidad(formatoDecimal.format(Utilitarios.redondeoValor(presupuestoDetalleIf.getCantidad())));
								
					//seteo el valor parcial
					double precioVenta = 0D;
												
					//si el reporte es interno entonces en precio de venta va el valor bruto de compra
					if(opcion == opcionSiInterno){
						precioVenta = presupuestoDetalleIf.getPrecioagencia().doubleValue() * presupuestoDetalleIf.getCantidad().doubleValue();
					}
					
					//caso contrario solo va el valor parcial, o el parcial menos el descuento especial si hubiera.								
					else{
						precioVenta = presupuestoDetalleIf.getPrecioventa().doubleValue();
						
						if(presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta().compareTo(new BigDecimal(0)) == 1){
							double porcentajeDescuentoEspecialVenta = presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta().doubleValue() / 100;
							double descuentoEspecialVenta = precioVenta * porcentajeDescuentoEspecialVenta;
							precioVenta = precioVenta - descuentoEspecialVenta;
						}
					}							
																					
					//Proceso para saber cuantos decimales tiene el precio parcial
					//y segun eso ponerle el formato decimal adecuado.
					String valorParcial = String.valueOf(presupuestoDetalleIf.getPrecioventa().stripTrailingZeros().doubleValue());
					int posicionPuntoDecimal = valorParcial.indexOf(".");
					int longitudDecimales = posicionPuntoDecimal>=1?valorParcial.substring(posicionPuntoDecimal).length()-1:0;
					
					//segun el numero de decimales pongo el formato adecuado para el precio parcial.
					if(longitudDecimales >= 4){
						presupuestoDetalleReporteData.setParcial(formatoDecimalParcialCuatro.format(precioVenta));
					}else if(longitudDecimales >= 3){
						presupuestoDetalleReporteData.setParcial(formatoDecimalParcialTres.format(precioVenta));
					}else{
						presupuestoDetalleReporteData.setParcial(formatoDecimalParcialDos.format(precioVenta));
					}
					
					//seteo el valor total
					double precioTotal = 0D;	
					
					//si el reporte es interno entonces en precio de total es el valor neto (bruto - descuentos)
					if(opcion == opcionSiInterno){
						double porcentajeDescuentoEspecialCompra = presupuestoDetalleIf.getPorcentajeDescuentoEspecialCompra().doubleValue() / 100;
						double descuentoEspecialCompra = precioVenta * porcentajeDescuentoEspecialCompra;
						double porcentajeDescuentoAgenciaCompra = presupuestoDetalleIf.getPorcentajeDescuentoAgenciaCompra().doubleValue() / 100;
						double descuentoAgenciaCompra = (precioVenta - descuentoEspecialCompra) * porcentajeDescuentoAgenciaCompra;
						double porcentajeDescuentosVariosCompra = presupuestoDetalleIf.getPorcentajeDescuentosVariosCompra().doubleValue() / 100;
						double descuentosVariosCompra = (precioVenta - descuentoEspecialCompra) * porcentajeDescuentosVariosCompra;
						
						precioTotal = precioVenta - descuentoEspecialCompra - descuentoAgenciaCompra - descuentosVariosCompra;
					}
					
					//caso contrario solo va el valor total del precio de venta por la cantidad								
					else{
						precioTotal = precioVenta * presupuestoDetalleIf.getCantidad().doubleValue();									
					}
					
					presupuestoDetalleReporteData.setTotal(formatoDecimal.format(Utilitarios.redondeoValor(precioTotal)));
					
				} else {
					presupuestoDetalleReporteData.setItem("");
					presupuestoDetalleReporteData.setCantidad("");
					presupuestoDetalleReporteData.setParcial("");
					presupuestoDetalleReporteData.setTotal("");
				}
				
				String concepto = presupuestoDetalleIf.getConcepto();
				if(presupuestoDetalleIf.getFechaPublicacion() != null){
					String fechaCompleta = Utilitarios.getFechaDiaSemanaDiaMesAnioUppercase(presupuestoDetalleIf.getFechaPublicacion());
					concepto = concepto + "\n\nPUBLICACIÓN: " + fechaCompleta + "\n";
				}
				
				//presupuestoDetalleReporteData.setDetalle(presupuestoDetalleIf.getConcepto().replaceAll("\t", " "));
				presupuestoDetalleReporteData.setDetalle(concepto.replaceAll("\t", " "));
				presupuestoDetalleDataColeccion.add(presupuestoDetalleReporteData);
			}
		}
	}

	public void report() {
		//para imprimir el presupuesto
		reporte();
		//para imprimir todas las ordenes del presupuesto
		reportesOrdenesCompra();
	}
	
	public void reportesOrdenesCompra(){
		try{
			Vector<OrdenCompraIf> ordenesParaImprimir = new Vector<OrdenCompraIf>();
			
			//busco si hay ordenes
			Map aMapSolicitudCompra = new HashMap();
			aMapSolicitudCompra.put("tipoReferencia", TIPO_PRESUPUESTO);
			aMapSolicitudCompra.put("referencia", presupuesto.getCodigo());
			Collection solicitudCompraColeccion = SessionServiceLocator.getSolicitudCompraSessionService().findSolicitudCompraByQuery(aMapSolicitudCompra);
			Iterator solicitudCompraColeccionIt = solicitudCompraColeccion.iterator();
			while(solicitudCompraColeccionIt.hasNext()){
				SolicitudCompraIf solicitudCompra = (SolicitudCompraIf)solicitudCompraColeccionIt.next();
				Collection ordenesCompra = SessionServiceLocator.getOrdenCompraSessionService().findOrdenCompraBySolicitudcompraId(solicitudCompra.getId());
				Iterator ordenesCompraIt = ordenesCompra.iterator();
				while(ordenesCompraIt.hasNext()){
					OrdenCompraIf ordenCompra = (OrdenCompraIf)ordenesCompraIt.next();
					if(!ordenCompra.getEstado().equals(EstadoOrdenCompra.ANULADA.getLetra())){
						ordenesParaImprimir.add(ordenCompra);						
					}
				}
			}
			
			//si existen ordenes para imprimir, pregunto
			if(ordenesParaImprimir.size() > 0){
				int opcion = JOptionPane.showOptionDialog(null,
						"¿Desea generar el reporte de las ordenes del Presupuesto?",
						"Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, si);
				
				if (opcion == JOptionPane.YES_OPTION) {
					for(OrdenCompraIf ordenCompra : ordenesParaImprimir){
						imprimirOrden(ordenCompra);
					}
				}	
			}				
			
		}catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
		}	
	}

	public void refresh() {
		cargarComboTipoOrden();
		cargarComboFormaPago();
		cargarComboTipoArchivo();
		if (clienteOficinaIf != null)
			cargarComboOrdenTrabajo();
		
		mapaOrdenesCompra = new HashMap<Long, OrdenCompraIf>();
		cargarMapas();		
	}

	public void duplicate() {
		try {
			if (getMode() == SpiritMode.UPDATE_MODE) {
				int opcion = JOptionPane.showOptionDialog(null,"¿Desea hacer una COPIA del Presupuesto?",
					"Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					PresupuestoIf presupuestoDuplicado = (PresupuestoIf) DeepCopy.copy(presupuesto);

					java.util.Date fechaCreacion = new java.util.Date();
					String codigo = getCodigoPresupuesto(new java.sql.Date(
							fechaCreacion.getTime()));
					presupuestoDuplicado.setId(null);
					presupuestoDuplicado.setCodigo(codigo);
					presupuestoDuplicado.setFecha(new java.sql.Timestamp(fechaCreacion.getTime()));
					presupuestoDuplicado.setEstado(EstadoPresupuesto.COTIZADO.getLetra());
					presupuestoDuplicado.setFechaAceptacion(null);

					for (int i = 0; i < presupuestoDetalleColeccion.size(); i++) {
						presupuestoDetalleColeccion.get(i).setId(null);
						presupuestoDetalleColeccion.get(i).setPresupuestoId(null);
						presupuestoDetalleColeccion.get(i).setOrdenCompraId(null);
					}

					for (int i = 0; i < presupuestoDetalleColeccionP.size(); i++) {
						presupuestoDetalleColeccionP.get(i).setId(null);
						presupuestoDetalleColeccionP.get(i).setPresupuestoId(null);
						presupuestoDetalleColeccionP.get(i).setOrdenCompraId(null);
					}

					OrdenTrabajoIf ordenTrabajo = (OrdenTrabajoIf) this.getCmbOrdenTrabajo().getSelectedItem();

					presupuestoDuplicado = SessionServiceLocator.getPresupuestoSessionService()
						.procesarPresupuesto(presupuestoDuplicado,presupuestoDetalleColeccion,
							presupuestoDetalleColeccionP, ordenTrabajo,ordenTD, productoClienteList,tarea);
					SpiritAlert.createAlert("Presupuesto copiado con éxito!, el código es: "+ 
						presupuestoDuplicado.getCodigo(),SpiritAlert.INFORMATION);
					showSaveMode();
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public void switchTab() {
		int selectedTab = this.getJtpPresupuesto().getSelectedIndex();
		int tabCount = this.getJtpPresupuesto().getTabCount();
		selectedTab++;

		if (selectedTab >= tabCount)
			selectedTab = 0;

		this.getJtpPresupuesto().setSelectedIndex(selectedTab);
	}

	public void addDetail() {
		if (this.getJtpPresupuesto().getSelectedIndex() == 1) {
			agregarDetallePresupuesto();
		}
	}

	public void updateDetail() {
		if (this.getJtpPresupuesto().getSelectedIndex() == 1) {
			try {
				actualizarDetallePresupuesto(false, true, false);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			}
		}
	}
	
	public void deleteDetail() {
		if (this.getJtpPresupuesto().getSelectedIndex() == 1) {
			eliminarDetallePresupuesto();
		}
	}

	private PresupuestoIf registrarPresupuesto()
			throws GenericBusinessException {
		PresupuestoData data = new PresupuestoData();
		java.util.Date fechaCreacion = new java.util.Date();
		java.util.Date fechaPresupuesto = getCmbFechaPresupuesto().getDate();
		
		String codigo = getCodigoPresupuesto(new java.sql.Date(fechaCreacion.getTime()));
		data.setCodigo(codigo);

		data.setOrdentrabajodetId((((InfoOrdenTrabajoDetalle) getCmbOrdenTrabajoDetalle()
						.getSelectedItem()).getOrdenTrabajoDetalleIf()).getId());
		ordenTD = ((InfoOrdenTrabajoDetalle) getCmbOrdenTrabajoDetalle()
				.getSelectedItem()).getOrdenTrabajoDetalleIf();

		if (clienteCondicion != null)
			data.setClienteCondicionId(clienteCondicion.getId());

		data.setConcepto(this.getTxtConceptoPresupuesto().getText().toUpperCase());
		data.setModificacion(contModif);
		
		data.setFechaValidez(new java.sql.Timestamp(fechaCreacion.getTime()));
		data.setFecha(new java.sql.Timestamp(fechaPresupuesto.getTime()));
				
		data.setValorbruto(BigDecimal.valueOf(subTotal));
		data.setDescuento(BigDecimal.valueOf(descuentoTotal));
		data.setDescuentoEspecial(BigDecimal.valueOf(descuentoEspecialTotalVenta));
		data.setDescuentosVarios(BigDecimal.valueOf(descuentosVariosVentaTotal));
		data.setValor(BigDecimal.valueOf(total));
		data.setIva(BigDecimal.valueOf(ivaTotal));
		data.setCabecera(this.getTxtCabecera().getText());
		data.setTemaCampana(getTxtTemaCampana().getText() != null ? getTxtTemaCampana().getText(): "");

		EstadoPresupuesto estado = (EstadoPresupuesto) getCmbEstado().getSelectedItem();
		data.setEstado(estado.getLetra());
				
		data.setFormaPagoId(((FormaPagoIf) getCmbFormaPago().getSelectedItem()).getId());
		data.setPorcentajeComisionAgencia(BigDecimal.valueOf(porcentajeComisionAgencia));
		data.setDiasValidez(Integer.parseInt(getTxtDiasValidez().getText()));
		data.setUsuarioCreacionId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
		data.setFechaCreacion(new java.sql.Timestamp(fechaCreacion.getTime()));
		
		java.util.Date fechaAprobacion = getCmbFechaAprobacion().getDate();
		if(fechaAprobacion != null){
			data.setFechaAceptacion(new java.sql.Timestamp(fechaAprobacion.getTime()));
			//si el estado es cotizado o pendiente se cambia a Aprobado
			//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F'
			if(estado.getLetra().equals(EstadoPresupuesto.COTIZADO.getLetra()) || estado.getLetra().equals(EstadoPresupuesto.PENDIENTE.getLetra())){
				data.setEstado(EstadoPresupuesto.APROBADO.getLetra());
			}
		}
		//si se va a guardar de una vez como aprobado y no hay fecha de aprobacion entonces se setea la fecha de aprobación.
		else if((estado.getLetra().equals(EstadoPresupuesto.APROBADO.getLetra()) 
				|| estado.getLetra().equals(EstadoPresupuesto.FACTURADO.getLetra()))
				&& fechaAprobacion == null){
			data.setFechaAceptacion(new java.sql.Timestamp(fechaPresupuesto.getTime()));
		}
		
		if(getTxtAutorizacionSAP().getText() != null && !getTxtAutorizacionSAP().getText().equals("")){
			data.setAutorizacionSap(getTxtAutorizacionSAP().getText());
		}
		
		if(getCbPrepago().isSelected()){
			data.setPrepago("S");
		}else{
			data.setPrepago("N");
		}
		
		data.setReferenciaId(referenciaId);
		data.setTipoReferencia(tipoReferencia);
				
		return data;
	}

	private String getCodigoPresupuesto(java.sql.Date fechaPresupuesto) {
		String codigo = "";
		String anioPresupuesto = Utilitarios.getYearFromDate(fechaPresupuesto);
		codigo += anioPresupuesto + "-";
		return codigo;
	}

	private PresupuestoIf registrarPresupuestoForUpdate(
			OrdenTrabajoDetalleIf ordenDetalle) {
		
		java.util.Date fechaPresupuesto = getCmbFechaPresupuesto().getDate();
		java.util.Date fechaActualizacion = new java.util.Date();		
		
		presupuesto.setOrdentrabajodetId(ordenDetalle.getId());
		presupuesto.setConcepto(this.getTxtConceptoPresupuesto().getText().toUpperCase());
		
		EstadoPresupuesto estado = (EstadoPresupuesto) getCmbEstado().getSelectedItem();
		
		if (EstadoPresupuesto.CANCELADO == estado)
			presupuesto.setFechaCancelacion(new java.sql.Timestamp(fechaActualizacion.getTime()));
		else if (EstadoPresupuesto.PENDIENTE == estado)
			presupuesto.setFechaEnvio(new java.sql.Timestamp(fechaActualizacion.getTime()));
		else if (EstadoPresupuesto.APROBADO == estado && !presupuesto.getEstado().equals(EstadoPresupuesto.APROBADO.getLetra()))
			presupuesto.setFechaAceptacion(new java.sql.Timestamp(fechaActualizacion.getTime()));
		
		contModif = contModif + 1;
		
		presupuesto.setFechaValidez(new java.sql.Timestamp(fechaActualizacion.getTime()));
		presupuesto.setFecha(new java.sql.Timestamp(fechaPresupuesto.getTime()));				
		presupuesto.setValorbruto(BigDecimal.valueOf(subTotal));
		presupuesto.setDescuento(BigDecimal.valueOf(descuentoTotal));
		presupuesto.setDescuentoEspecial(BigDecimal.valueOf(descuentoEspecialTotalVenta));
		presupuesto.setDescuentosVarios(BigDecimal.valueOf(descuentosVariosVentaTotal));
		presupuesto.setValor(BigDecimal.valueOf(total));
		presupuesto.setIva(BigDecimal.valueOf(ivaTotal));
		presupuesto.setModificacion(contModif);
		presupuesto.setCabecera(this.getTxtCabecera().getText());
		presupuesto.setTemaCampana(getTxtTemaCampana().getText() != null ? getTxtTemaCampana().getText(): "");		
		String estadoPrevio = presupuesto.getEstado();		
		presupuesto.setEstado(estado.getLetra());		
		presupuesto.setFormaPagoId(((FormaPagoIf) this.getCmbFormaPago().getSelectedItem()).getId());
		presupuesto.setPorcentajeComisionAgencia(BigDecimal.valueOf(porcentajeComisionAgencia));
		presupuesto.setDiasValidez(Integer.parseInt(this.getTxtDiasValidez().getText()));
		presupuesto.setUsuarioActualizacionId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
		presupuesto.setFechaActualizacion(new java.sql.Timestamp((new Date()).getTime()));
		
		java.util.Date fechaAprobacion = getCmbFechaAprobacion().getDate();
		if(fechaAprobacion != null){
			presupuesto.setFechaAceptacion(new java.sql.Timestamp(fechaAprobacion.getTime()));
			//si el estado anterior es cotizado o pendiente se cambia a Aprobado 
			//si es que el nuevo estado no es Prepagado
			//PRESUPUESTO, COTIZADO = 'T', PENDIENTE = 'P', APROBADO = 'A', FACTURADO = 'F', PREPAGADO = 'R'
			if((estadoPrevio.equals(EstadoPresupuesto.COTIZADO.getLetra()) || estadoPrevio.equals(EstadoPresupuesto.PENDIENTE.getLetra()))
					&& !estado.equals(EstadoPresupuesto.PREPAGADO)){
				presupuesto.setEstado(EstadoPresupuesto.APROBADO.getLetra());
			}
		}
		//si se va a guardar de una vez como aprobado y no hay fecha de aprobacion entonces se setea la fecha de aprobación.
		else if((estado.getLetra().equals(EstadoPresupuesto.APROBADO.getLetra()) 
				|| estado.getLetra().equals(EstadoPresupuesto.FACTURADO.getLetra())
				|| estado.getLetra().equals(EstadoPresupuesto.PREPAGADO.getLetra()))
				&& fechaAprobacion == null){
			presupuesto.setFechaAceptacion(new java.sql.Timestamp(fechaPresupuesto.getTime()));
		}
		
		if(getTxtAutorizacionSAP().getText() != null && !getTxtAutorizacionSAP().getText().equals("")){
			presupuesto.setAutorizacionSap(getTxtAutorizacionSAP().getText());
		}
		
		if(getCbPrepago().isSelected()){
			presupuesto.setPrepago("S");
		}else{
			presupuesto.setPrepago("N");
		}
		
		presupuesto.setReferenciaId(referenciaId);
		presupuesto.setTipoReferencia(tipoReferencia);
		
		return presupuesto;
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtConceptoPresupuesto().getText())
				&& "".equals(this.getTxtProveedor().getText())
				&& "".equals(this.getTxtOficina().getText())
				&& "".equals(this.getTxtModificacion().getText())
				&& "".equals(this.getTxtCabecera().getText())
				&& "".equals(this.getTxtConceptoPresupuestoDetalle().getText())
				&& "".equals(this.getTxtCantidad().getText())
				&& "".equals(this.getTxtPrecioCompra().getText().replaceAll(
						",", ""))
				&& "".equals(Utilitarios.removeDecimalFormat(this.getTxtPrecioVenta().getText()))
				&& "".equals(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDsctoAgenciaVenta().getText()))
				&& "".equals(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentoEspecialVenta().getText()))
				&& "".equals(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentosVariosVenta().getText()))
				&& "".equals(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDsctoAgenciaCompra().getText()))
				&& "".equals(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentoEspecialCompra().getText()))
				&& "".equals(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentosVariosCompra().getText()))
				&& "".equals(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeNegociacionDirecta().getText()))
				&& "".equals(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeComisionPura().getText()))
				&& "".equals(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeComisionAdicional().getText()))
				&& "".equals(this.getTxtSubTotalVenta().getText())
				&& "".equals(this.getTxtDsctoAgenciaVenta().getText())
				&& "".equals(this.getTxtDescuentoEspecialTotalVenta().getText())
				&& "".equals(this.getTxtDescuentosVariosVenta().getText())
				&& "".equals(this.getTxtIvaVenta().getText())
				&& "".equals(this.getTxtTotalVenta().getText())
				&& (this.getCmbTipoOrden().getSelectedItem() == null)
				&& (this.getCmbOrdenTrabajo().getSelectedItem() == null)
				&& (this.getCmbOrdenTrabajoDetalle().getSelectedItem() == null)
				&& (this.getCmbEstado().getSelectedItem() == null)
				&& (this.getCmbFechaPresupuesto().getSelectedItem() == null)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateFields() {
		
		EstadoPresupuesto estado = (EstadoPresupuesto) getCmbEstado().getSelectedItem();
		if(estado.getLetra().equals(EstadoPresupuesto.FACTURADO.getLetra())){
			SpiritAlert.createAlert("No es posible cambiar manualmente el estado del presupuesto a FACTURADO.",
					SpiritAlert.WARNING);
				return false;
		}
		
		if (getMode() == SpiritMode.UPDATE_MODE
				&& presupuesto.getEstado().equals(EstadoPresupuesto.FACTURADO.getLetra())) {
				//&& presupuesto.getEstado().equals(ESTADO_FACTURADO)) {
			SpiritAlert.createAlert("El presupuesto ya está Facturado, no se puede actualizar!",
				SpiritAlert.WARNING);
			return false;
		}

		if (ivaTotal == 0) {
			int opcion = JOptionPane.showOptionDialog(null, "¿El IVA es "
					+ ivaTotal + ", desea guardar el presupuesto?",
					"Confirmación", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.NO_OPTION) {
				getJtpPresupuesto().setSelectedIndex(0);
				getCbSinIVA().grabFocus();
				return false;
			}
		}
		
		if (referenciaId != null && (tipoReferencia == null || getCmbTipoReferencia().equals("NINGUNO"))) {
			SpiritAlert.createAlert("Limpie la Referencia y vuelva a seleccionarla.",SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(0);
			getCmbEstado().grabFocus();
			return false;
		}

		if (this.getCmbEstado().getSelectedItem() == null) {
			SpiritAlert.createAlert("¡Debe seleccionar un Estado!",SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(0);
			getCmbEstado().grabFocus();
			return false;
		}

		if ("".equals(getTxtConceptoPresupuesto().getText())) {
			SpiritAlert.createAlert("¡Debe ingresar un Concepto!",SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(0);
			getTxtConceptoPresupuesto().grabFocus();
			return false;
		}

		if ("".equals(getTxtCliente().getText())) {
			SpiritAlert.createAlert("¡Debe seleccionar el Cliente!",SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(0);
			getBtnBuscarCliente().grabFocus();
			return false;
		}

		if ("".equals(getTxtOficina().getText())) {
			SpiritAlert.createAlert("¡Debe seleccionar la Oficina del Cliente!",SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(0);
			getBtnBuscarOficina().grabFocus();
			return false;
		}

		if (getCmbFechaPresupuesto().getSelectedItem() == null) {
			SpiritAlert.createAlert("¡Debe seleccionar una Fecha de Presupuesto!",SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(0);
			getCmbFechaPresupuesto().grabFocus();
			return false;
		}

		if (getCmbTipoOrden().getSelectedItem() == null) {
			SpiritAlert.createAlert("¡Debe seleccionar el Tipo de Orden!",SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(0);
			getCmbTipoOrden().grabFocus();
			return false;
		}

		if (getCmbOrdenTrabajo().getSelectedItem() == null) {
			SpiritAlert.createAlert("¡Debe seleccionar una Orden de Trabajo!",SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(0);
			getCmbOrdenTrabajo().grabFocus();
			return false;
		}

		if (getCmbOrdenTrabajoDetalle().getSelectedItem() == null) {
			SpiritAlert.createAlert(
					"¡Debe seleccionar un Detalle de Orden de Trabajo!",SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(0);
			getCmbOrdenTrabajoDetalle().grabFocus();
			return false;
		}

		if (getCmbFormaPago().getSelectedItem() == null) {
			SpiritAlert.createAlert("¡Debe seleccionar una Forma de Pago!",SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(0);
			getCmbFormaPago().grabFocus();
			return false;
		}

		if (getTxtDiasValidez().getText().trim().equals("")) {
			SpiritAlert.createAlert("¡Debe ingresar los Días de Validez!",SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(0);
			getTxtDiasValidez().grabFocus();
			return false;
		}

		if (productoClienteList.size() == 0) {
			SpiritAlert.createAlert(
					"Debe ingresar al menos un Producto para el Presupuesto!",SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(1);
			getCbListProductos().grabFocus();
			return false;
		}
		
		TipoOrdenIf tipoOrden = (TipoOrdenIf) getCmbTipoOrden().getSelectedItem();
		if(productoClienteList.size() > 1 && tipoOrden.getTipo().equals(TIPO_MEDIOS)){			
			SpiritAlert.createAlert(
					"Hay más de 1 producto seleccionado, esto DUPLICARA los valores en los reportes de inversión de Medios. Por favor seleccione sólo 1 producto.",
					SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(1);
			getCbListProductos().grabFocus();
			return false;
		}

		if (presupuestoDetalleColeccion.isEmpty()) {
			SpiritAlert.createAlert("¡Debe ingresar al menos un Detalle!",SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(2);
			getBtnBuscarProducto().grabFocus();
			return false;
		}
		
		if (presupuestoDetalleColeccionP.isEmpty()) {
			SpiritAlert.createAlert("¡Debe ingresar al menos un Detalle para el Reporte!",SpiritAlert.WARNING);
			getJtpPresupuesto().setSelectedIndex(3);
			getBtnActualizarDetalleP().grabFocus();
			return false;
		}
		
		for(int i=0; i<presupuestoDetalleColeccion.size(); i++){
			PresupuestoDetalleIf presupuestoDetalle = presupuestoDetalleColeccion.get(i);
			for(int j=0; j<presupuestoDetalleColeccion.size(); j++){
				PresupuestoDetalleIf presupuestoDetalleTemp = presupuestoDetalleColeccion.get(j);
				if(i != j 
					&& presupuestoDetalle.getOrden() == presupuestoDetalleTemp.getOrden() 
					&& presupuestoDetalle.getPorcentajeDescuentosVariosCompra().compareTo(presupuestoDetalleTemp.getPorcentajeDescuentosVariosCompra()) != 0){
					SpiritAlert.createAlert("Los Porcentajes de Dctos. Varios de Compra en una misma Orden ("+ presupuestoDetalle.getOrden() +"), deben ser iguales.",SpiritAlert.WARNING);
					getJtpPresupuesto().setSelectedIndex(2);
					getTblPresupuestoDetalle().grabFocus();
					return false;
				}
			}
		}
		
		for(int i=0; i < presupuestoDetalleColeccion.size(); i++){
			PresupuestoDetalleIf presupuestoDetalle = presupuestoDetalleColeccion.get(i);
			
			for(int j=0; j < presupuestoDetalleColeccion.size(); j++){
				PresupuestoDetalleIf presupuestoDetalleTemp = presupuestoDetalleColeccion.get(j);
				
				if(j != i && presupuestoDetalle.getOrden() == presupuestoDetalleTemp.getOrden() &&
						presupuestoDetalleTemp.getPorcentajeDescuentoEspecialCompra().doubleValue() != presupuestoDetalle.getPorcentajeDescuentoEspecialCompra().doubleValue()){
					SpiritAlert.createAlert("El Porcentaje de Descuento Especial Compra debe ser igual en detalles de la misma Orden: " + presupuestoDetalleTemp.getOrden(),SpiritAlert.WARNING);
					getTxtPorcentajeDescuentoEspecialCompra().grabFocus();
					return false;
				}
			}			
		}

		return true;
	}

	public boolean validateFieldsDetalle(boolean esReporte) {
		if (!esReporte) {
			if ("".equals(getTxtProveedor().getText())) {
				SpiritAlert.createAlert("¡Debe seleccionar un Proveedor!",SpiritAlert.WARNING);
				getBtnBuscarProveedor().grabFocus();
				return false;
			}
			
			if(getCbNegociacionDirecta().isSelected()){
				if(getTxtPorcentajeNegociacionDirecta().getText().equals("")){
					SpiritAlert.createAlert("¡Debe ingresar un Porcentaje de Facturación Directa!",SpiritAlert.WARNING);
					getTxtPorcentajeNegociacionDirecta().grabFocus();
					return false;
				}else if(Double.valueOf(getTxtPorcentajeNegociacionDirecta().getText().replaceAll(",", "")) <= 0){
					SpiritAlert.createAlert("¡El Porcentaje de Facturación Directa debe ser mayor que cero!",SpiritAlert.WARNING);
					getTxtPorcentajeNegociacionDirecta().grabFocus();
					return false;
				}else if(Double.valueOf(getTxtPorcentajeNegociacionDirecta().getText().replaceAll(",", "")) > 100){
					SpiritAlert.createAlert("¡El Porcentaje de Facturación Directa no puede ser mayor que 100%!",SpiritAlert.WARNING);
					getTxtPorcentajeNegociacionDirecta().grabFocus();
					return false;
				}else if(getTxtPorcentajeDsctoAgenciaCompra().getText().equals("")){
					SpiritAlert.createAlert("¡Debe ingresar un Porcentaje de Descuento de Agencia!",SpiritAlert.WARNING);
					getTxtPorcentajeDsctoAgenciaCompra().grabFocus();
					return false;
				}else if(Double.valueOf(getTxtPorcentajeDsctoAgenciaCompra().getText().replaceAll(",", "")) <= 0){
					SpiritAlert.createAlert("¡El Porcentaje de Descuento de Agencia  debe ser mayor que cero!",SpiritAlert.WARNING);
					getTxtPorcentajeDsctoAgenciaCompra().grabFocus();
					return false;
				}else if(Double.valueOf(getTxtPorcentajeDsctoAgenciaCompra().getText().replaceAll(",", "")) > 100){
					SpiritAlert.createAlert("¡El Porcentaje de Descuento de Agencia no puede ser mayor que 100%!",SpiritAlert.WARNING);
					getTxtPorcentajeDsctoAgenciaCompra().grabFocus();
					return false;
				}/*else if(!getTxtPorcentajeDescuentosVariosCompra().getText().equals("") && 
						Double.valueOf(getTxtPorcentajeDescuentosVariosCompra().getText().replaceAll(",", "")) > 0){
					SpiritAlert.createAlert("¡Si existe Facturación Directa no puede haber Bonificación en la Compra!",SpiritAlert.WARNING);
					getTxtPorcentajeDescuentosVariosCompra().grabFocus();
					return false;
				}*/
			}else{
				getTxtPorcentajeNegociacionDirecta().setText("0");
			}
			
			//COMISION PURA
			if(getCbComisionPura().isSelected()){
				if(getTxtPorcentajeComisionPura().getText().equals("")){
					SpiritAlert.createAlert("¡Debe ingresar un Porcentaje de Comisión Directa!",SpiritAlert.WARNING);
					getTxtPorcentajeComisionPura().grabFocus();
					return false;
				}else if(Double.valueOf(getTxtPorcentajeComisionPura().getText().replaceAll(",", "")) <= 0){
					SpiritAlert.createAlert("¡El Porcentaje de Comisión Directa debe ser mayor que cero!",SpiritAlert.WARNING);
					getTxtPorcentajeComisionPura().grabFocus();
					return false;
				}else if(Double.valueOf(getTxtPorcentajeComisionPura().getText().replaceAll(",", "")) > 100){
					SpiritAlert.createAlert("¡El Porcentaje de Comisión Directa no puede ser mayor que 100%!",SpiritAlert.WARNING);
					getTxtPorcentajeComisionPura().grabFocus();
					return false;
				}
				
				double porcentajeDescuentoProveedor = 0D;
				if(!getTxtPorcentajeDsctoAgenciaCompra().getText().equals("")){
					porcentajeDescuentoProveedor = Double.valueOf(getTxtPorcentajeDsctoAgenciaCompra().getText().replaceAll(",", ""));
				}
				double porcentajeDescuentosVariosCompra = 0D;
				if(!getTxtPorcentajeDescuentosVariosCompra().getText().equals("")){
					porcentajeDescuentosVariosCompra = Double.valueOf(getTxtPorcentajeDescuentosVariosCompra().getText().replaceAll(",", ""));
				}
				
				double porcentajeComisionPura = Double.valueOf(getTxtPorcentajeComisionPura().getText().replaceAll(",", ""));
				if(porcentajeComisionPura != (porcentajeDescuentoProveedor + porcentajeDescuentosVariosCompra)){
					SpiritAlert.createAlert("¡La suma de los porcentajes Descuento Agencia y Varios (Compra) debe ser igual al Porcentaje de Comisión Directa!",SpiritAlert.WARNING);
					getTxtPorcentajeDsctoAgenciaCompra().grabFocus();
					return false;
				}
				
			}else{
				getTxtPorcentajeComisionPura().setText("0");
			}
			
			//COMISION ADICIONAL
			if(getCbComisionAdicional().isSelected()){
				if(getTxtPorcentajeComisionAdicional().getText().equals("")){
					SpiritAlert.createAlert("¡Debe ingresar un Porcentaje de Comisión Adicional!",SpiritAlert.WARNING);
					getTxtPorcentajeComisionAdicional().grabFocus();
					return false;
				}else if(Double.valueOf(getTxtPorcentajeComisionAdicional().getText().replaceAll(",", "")) <= 0){
					SpiritAlert.createAlert("¡El Porcentaje de Comisión Adicional debe ser mayor que cero!",SpiritAlert.WARNING);
					getTxtPorcentajeComisionAdicional().grabFocus();
					return false;
				}else if(Double.valueOf(getTxtPorcentajeComisionAdicional().getText().replaceAll(",", "")) > 100){
					SpiritAlert.createAlert("¡El Porcentaje de Comisión Adicional no puede ser mayor que 100%!",SpiritAlert.WARNING);
					getTxtPorcentajeComisionAdicional().grabFocus();
					return false;
				}				
			}else{
				getTxtPorcentajeComisionAdicional().setText("0");
			}

			if ("".equals(getTxtProducto().getText())) {
				SpiritAlert.createAlert("¡Debe seleccionar un Producto!",SpiritAlert.WARNING);
				getBtnBuscarProducto().grabFocus();
				return false;
			}

			if ("".equals(getTxtConceptoPresupuestoDetalle().getText())) {
				SpiritAlert.createAlert("¡Debe ingresar un Concepto!",SpiritAlert.WARNING);
				getTxtConceptoPresupuestoDetalle().grabFocus();
				return false;
			}

			if ("".equals(Utilitarios.removeDecimalFormat(getTxtPrecioCompra().getText()))) {
				SpiritAlert.createAlert("¡Debe ingresar el Precio de Compra!",SpiritAlert.WARNING);
				getTxtPrecioCompra().grabFocus();
				return false;
			}

			if ("".equals(getTxtCantidad().getText())) {
				SpiritAlert.createAlert("¡Debe ingresar una Cantidad!",SpiritAlert.WARNING);
				getTxtCantidad().grabFocus();
				return false;
			}

			if (Double.valueOf(getTxtCantidad().getText()) <= 0D) {
				SpiritAlert.createAlert("¡La Cantidad debe deber ser mayor que 0!",SpiritAlert.WARNING);
				getTxtCantidad().grabFocus();
				return false;
			}

			if ("".equals(Utilitarios.removeDecimalFormat(getTxtPrecioVenta().getText()))) {
				SpiritAlert.createAlert("¡Debe ingresar el Precio de Venta!",SpiritAlert.WARNING);
				getTxtPrecioVenta().grabFocus();
				return false;
			}
			
		} else {
			if ("".equals(getTxtConceptoPresupuestoDetalleP().getText())) {
				SpiritAlert.createAlert("¡Debe ingresar un Concepto!",SpiritAlert.WARNING);
				getTxtConceptoPresupuestoDetalle().grabFocus();
				return false;
			}

			if ("".equals(getTxtCantidadP().getText())) {
				SpiritAlert.createAlert("¡Debe ingresar una Cantidad!",SpiritAlert.WARNING);
				getTxtCantidad().grabFocus();
				return false;
			}

			if (Double.valueOf(getTxtCantidadP().getText()) <= 0D) {
				SpiritAlert.createAlert("¡La Cantidad debe deber ser mayor que 0!",SpiritAlert.WARNING);
				getTxtCantidad().grabFocus();
				return false;
			}

			if ("".equals(Utilitarios.removeDecimalFormat(getTxtPrecioVentaP().getText()))) {
				SpiritAlert.createAlert("¡Debe ingresar el Precio de Venta!",SpiritAlert.WARNING);
				getTxtPrecioVenta().grabFocus();
				return false;
			}
		}
		return true;
	}

	public void clean() {
		tarea = null;
		
		presupuesto = null;
		referenciaId = null;
		tipoReferencia = null;
		getCmbTipoReferencia().setSelectedItem("NINGUNO");
		getTxtReferencia().setText("");
		getCbSinIVA().setSelected(false);
		getCbPrepago().setSelected(false);
		getModelProductoCliente().removeAllElements();
		ordenesCompraParaModificar = null;
		ordenesCompraParaModificar = new HashSet<Integer>();
		archivosEliminadosColeccion = null;
		archivosEliminadosColeccion = new Vector<PresupuestoArchivoIf>();
		archivosNombreColeccion = null;
		archivosNombreColeccion = new Vector<String>();
		archivosColeccion = null;
		archivosColeccion = new Vector<File>();
		archivosPresupuestoColeccion = null;
		archivosPresupuestoColeccion = new Vector<PresupuestoArchivoIf>();
		productoClienteList = null;
		productoClienteList = new ArrayList<ProductoClienteIf>();
		cleanListaProductos();
		proveedorIf = null;
		corporacionIf = null;
		clienteIf = null;
		clienteOficinaIf = null;
		cleanTable();
		presupuestoDetalleColeccion.clear();
		presupuestoDetalleColeccionP.clear();
		presupuestoDetalleEliminadosColeccion.clear();
		subTotal = 0.00;
		valorBruto = 0.00;
		descuentoEspecialTotalCompra = 0.00;
		descuentoCompraTotal = 0.00;
		descuentoEspecialTotalVenta = 0.00;
		descuentosVariosVentaTotal = 0.00;
		bonificacionCompraTotal = 0.00;
		descuentoTotal = 0.00;
		ivaTotal = 0.00;
		total = 0.00;
		ivaTotalAgencia = 0.00;
		totalAgencia = 0.00;
		contModif = 0;
		porcentajeComisionAgencia = 0D;
		comisionAgencia = 0D;
		this.getTxtConceptoPresupuesto().setText("");
		this.getTxtCodigo().setText("");
		this.getTxtConceptoPresupuesto().setText("");
		this.getTxtProveedor().setText("");
		this.getTxtCorporacion().setText("");
		this.getTxtCliente().setText("");
		this.getTxtOficina().setText("");
		this.getTxtModificacion().setText("");
		this.getTxtAutorizacionSAP().setText("");
		this.getTxtCabecera().setText("");
		this.getTxtSubTotalVenta().setText("");
		this.getTxtDsctoAgenciaVenta().setText("");
		this.getTxtDescuentoEspecialTotalVenta().setText("");
		this.getTxtDescuentosVariosVenta().setText("");
		this.getTxtDsctoAgenciaCompra().setText("");
		this.getTxtDescuentoEspecialTotalCompra().setText("");
		this.getTxtDescuentoEspecialTotalVenta().setText("");		
		this.getTxtSubTotal2Compra().setText("");
		this.getTxtSubTotal2Venta().setText("");		
		this.getTxtDescuentosVariosCompra().setText("");
		this.getTxtIvaVenta().setText("");
		this.getTxtTotalVenta().setText("");
		this.getTxtConceptoPresupuestoDetalle().setText("");
		this.getTxtCantidad().setText("");
		this.getTxtPrecioVenta().setText("");
		this.getTxtPrecioCompra().setText("");
		this.getTxtPorcentajeDsctoAgenciaVenta().setText("0");
		this.getTxtPorcentajeDescuentoEspecialVenta().setText("0");
		this.getTxtPorcentajeDescuentosVariosVenta().setText("0");
		this.getTxtPrecioCompra().setText("");
		this.getTxtProducto().setText("");
		this.getTxtPorcentajeDsctoAgenciaCompra().setText("0");
		this.getTxtPorcentajeDescuentoEspecialCompra().setText("0");
		this.getTxtPorcentajeDescuentosVariosCompra().setText("0");
		this.getTxtPorcentajeNotaCredito().setText("0");
		this.getTxtSubTotalCompra().setText("");
		this.getTxtIvaTotalCompra().setText("");
		this.getTxtTotalCompra().setText("");
		this.getTxtDiasValidez().setText("");
		this.getTxtPorcentajeComision().setText("");
		this.getTxtValorComision().setText("");
		this.getCmbEstado().setSelectedItem(null);
		this.getTxtSubTipoOrden().setText("");
		this.getTxtDescripcionOTdetalle().setText("");
		this.getTxtTemaCampana().setText("");
		this.getTxtPorcentajeNegociacionDirecta().setText("");
		this.getTxtPorcentajeComisionPura().setText("");
		this.getTxtPorcentajeComisionAdicional().setText("");
		this.getCmbFechaPublicacion().setCalendar(null);

		this.getTxtProveedorP().setText("");
		this.getTxtProductoP().setText("");
		this.getTxtConceptoPresupuestoDetalleP().setText("");
		this.getTxtPrecioVentaP().setText("");
		this.getTxtCantidadP().setText("");

		if (getMode() == SpiritMode.SAVE_MODE) {
			this.getCmbFechaCreacion().setCalendar(new GregorianCalendar());
			this.getCmbFechaPresupuesto().setCalendar(new GregorianCalendar());
			this.getCmbFechaAprobacion().setCalendar(new GregorianCalendar());
		}

		if (getMode() == SpiritMode.FIND_MODE) {
			this.getCmbFechaPresupuesto().setCalendar(new GregorianCalendar());
		}
		
		if (getMode() == SpiritMode.SAVE_MODE
				|| getMode() == SpiritMode.UPDATE_MODE) {
			this.getCmbTipoOrden().removeAllItems();
			this.getCmbOrdenTrabajo().removeAllItems();
			this.getCmbOrdenTrabajoDetalle().removeAllItems();
			this.getCmbEstado().removeAllItems();
		}

		this.getCmbTipoOrden().setSelectedIndex(-1);
		// this.getCmbTipoOrden().removeItemListener(oItemListenerSetearOrdenTrabajoDetalle);
		this.getCmbOrdenTrabajo().setSelectedIndex(-1);
		// this.getCmbOrdenTrabajo().removeItemListener(oItemListenerSetearOrdenTrabajoDetalle);
		this.getCmbOrdenTrabajoDetalle().setSelectedIndex(-1);

		this.getTxtArchivo().setText("");
		cleanTableArchivo();
		
		mapaOrdenesCompra = new HashMap<Long, OrdenCompraIf>();
		//mapaGenericos = new HashMap<Long, GenericoIf>();
		//mapaProductos = new HashMap<Long, ProductoIf>();
		//mapaClientes = new HashMap<Long, ClienteIf>();
		//mapaClienteOficinas = new HashMap<Long, ClienteOficinaIf>();
		
		getCbNegociacionDirecta().setSelected(false);
		getTxtPorcentajeNegociacionDirecta().setEditable(false);
		getCbComisionPura().setSelected(false);
		getTxtPorcentajeComisionPura().setEditable(false);
		getTxtPorcentajeComisionAdicional().setEditable(false);

		this.repaint();
	}

	public void cleanDetalle() {
		cleanTable();
		presupuestoDetalleColeccion.clear();
		presupuestoDetalleEliminadosColeccion.clear();
		subTotal = 0.00;
		valorBruto = 0.00;
		descuentoTotal = 0.00;
		descuentoCompraTotal = 0.00;
		descuentoEspecialTotalCompra = 0.00;
		bonificacionCompraTotal = 0.00;
		descuentoEspecialTotalVenta = 0.00;
		descuentosVariosVentaTotal = 0.00;
		ivaTotal = 0.00;
		total = 0.00;
		ivaTotalAgencia = 0.00;
		totalAgencia = 0.00;
		porcentajeComisionAgencia = 0D;
		comisionAgencia = 0D;
		this.getTxtSubTotalVenta().setText("");
		this.getTxtDsctoAgenciaVenta().setText("");
		this.getTxtDescuentoEspecialTotalVenta().setText("");
		this.getTxtDescuentosVariosVenta().setText("");
		this.getTxtDsctoAgenciaCompra().setText("");
		this.getTxtDescuentoEspecialTotalCompra().setText("");
		this.getTxtDescuentoEspecialTotalVenta().setText("");		
		this.getTxtSubTotal2Compra().setText("");
		this.getTxtSubTotal2Venta().setText("");
		this.getTxtDescuentosVariosCompra().setText("");
		this.getTxtIvaVenta().setText("");
		this.getTxtTotalVenta().setText("");
		this.getTxtConceptoPresupuestoDetalle().setText("");
		this.getTxtCantidad().setText("");
		this.getTxtPrecioVenta().setText("");
		this.getTxtPrecioCompra().setText("");
		this.getTxtPorcentajeDsctoAgenciaVenta().setText("0");
		this.getTxtPorcentajeDescuentoEspecialVenta().setText("0");
		this.getTxtPorcentajeDescuentosVariosVenta().setText("0");
		this.getTxtSubTotalVenta().setText("");
		this.getTxtPrecioCompra().setText("");
		this.getTxtProducto().setText("");
		this.getTxtPorcentajeDsctoAgenciaCompra().setText("0");
		this.getTxtPorcentajeDescuentoEspecialCompra().setText("0");
		this.getTxtPorcentajeDescuentosVariosCompra().setText("0");
		this.getTxtPorcentajeNotaCredito().setText("0");
		this.getTxtSubTotalCompra().setText("");
		this.getTxtIvaTotalCompra().setText("");
		this.getTxtTotalCompra().setText("");
		this.getTxtDiasValidez().setText("");
		this.getTxtPorcentajeComision().setText("");
		this.getTxtValorComision().setText("");
		this.getTxtPorcentajeNegociacionDirecta().setText("");
		this.getTxtPorcentajeComisionPura().setText("");
		this.getTxtPorcentajeComisionAdicional().setText("");
		this.getTxtOrden().setText("");
		this.getCmbFechaPublicacion().setCalendar(null);
	}

	private void cleanTable() {
		// Vacio las tablas de orden detalle
		limpiarTabla(getTblPresupuestoDetalle());
		limpiarTabla(getTblPresupuestoDetalleP());
	}

	private void cleanTableArchivo() {
		limpiarTabla(getTblArchivo());
	}

	public void showFindMode() {
		proveedorIf = null;
		referenciaId = null;
		tipoReferencia = null;
		getCmbTipoReferencia().setSelectedItem("NINGUNO");
		getTxtReferencia().setText("");
		getCbSinIVA().setSelected(false);
		this.getTxtCodigo().setBackground(Parametros.findColor);
		this.getTxtCliente().setBackground(Parametros.findColor);
		this.getCmbEstado().setBackground(Parametros.findColor);
		this.getCmbFechaPresupuesto().setBackground(Parametros.findColor);
		this.getTxtCodigo().setEditable(true);
		this.getTxtConceptoPresupuesto().setEditable(true);
		this.getTxtConceptoPresupuestoDetalle().setEditable(false);
		this.getBtnBuscarProveedor().setEnabled(true);
		this.getBtnBuscarCorporacion().setEnabled(true);
		this.getBtnBuscarCliente().setEnabled(true);
		this.getCmbTipoOrden().setEnabled(false);
		this.getCmbOrdenTrabajo().setEnabled(false);
		this.getCmbOrdenTrabajoDetalle().setEnabled(false);
		this.getCmbFechaCreacion().setEnabled(false);
		this.getCmbFechaPresupuesto().setEnabled(true);
		this.getCmbFechaAprobacion().setEnabled(false);
		this.getCmbEstado().setEnabled(true);
		this.getTxtModificacion().setEditable(false);
		this.getTxtCabecera().setEditable(false);
		this.getCmbFechaPresupuesto().setSelectedItem(null);
		this.getTxtCodigo().grabFocus();
		this.getJtpPresupuesto().setSelectedIndex(0);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		this.getTxtCodigo().setBackground(getBackground());
		this.getTxtCliente().setBackground(getBackground());
		this.getCmbEstado().setBackground(Parametros.saveUpdateColor);
		this.getCmbFechaPresupuesto().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().setEditable(false);
		getTxtConceptoPresupuesto().setEditable(true);
		getTxtConceptoPresupuestoDetalle().setEditable(true);
		getTxtCabecera().setEditable(true);
		getCmbOrdenTrabajo().setEnabled(false);
		getCmbOrdenTrabajo().setSelectedIndex(-1);
		getCmbOrdenTrabajoDetalle().setEnabled(false);
		getTxtProveedor().setEditable(false);
		getTxtModificacion().setEditable(false);
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getBtnBuscarProveedor().setEnabled(true);
		getBtnBuscarOficina().setEnabled(true);
		getCmbFechaCreacion().setEnabled(false);
		getCmbFechaPresupuesto().setEnabled(true);
		getCmbFechaAprobacion().setEnabled(true);
		getCmbTipoOrden().setEnabled(true);
		getTxtSubTotalVenta().setEditable(false);
		getTxtDsctoAgenciaVenta().setEditable(false);
		getTxtIvaVenta().setEditable(false);
		getTxtTotalVenta().setEditable(false);
		getTxtProducto().setEditable(false);
		getTxtConceptoPresupuestoDetalle().setEditable(false);
		getBtnBuscarProducto().setEnabled(false);
		getTxtIvaTotalCompra().setEditable(false);
		getTxtSubTotalCompra().setEditable(false);
		getTxtTotalCompra().setEditable(false);
		getCmbFechaCreacion().setCalendar(new GregorianCalendar());
		getCmbFechaPresupuesto().setCalendar(new GregorianCalendar());
		getCmbFechaAprobacion().setCalendar(null);
		// initListeners();
		loadCombos();
		getCmbEstado().setEnabled(true);
		getJtpPresupuesto().setSelectedIndex(0);
		getTxtConceptoPresupuesto().grabFocus();
		
		
		//cargarClienteOfinaId();
	}
	
	public void cargarClienteOfinaId(){
		try {
			Collection presupuestos = SessionServiceLocator.getPresupuestoSessionService().getPresupuestoList();
			Iterator presupuestosIt = presupuestos.iterator();
			while(presupuestosIt.hasNext()){
				PresupuestoIf presupuestoIf = (PresupuestoIf)presupuestosIt.next();
				OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(presupuestoIf.getOrdentrabajodetId());
				OrdenTrabajoIf ordenTrabajoIf = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalleIf.getOrdenId());
				presupuestoIf.setClienteOficinaId(ordenTrabajoIf.getClienteoficinaId());
				SessionServiceLocator.getPresupuestoSessionService().savePresupuesto(presupuestoIf);
			}
			System.out.println("FIN");
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void showUpdateMode() {
		setUpdateMode();
		this.getTxtCodigo().setBackground(getBackground());
		this.getTxtCliente().setBackground(getBackground());
		this.getCmbEstado().setBackground(Parametros.saveUpdateColor);
		this.getCmbFechaPresupuesto().setBackground(Parametros.saveUpdateColor);
		this.getTxtCodigo().setEditable(false);
		this.getTxtConceptoPresupuesto().setEditable(true);
		this.getCmbTipoOrden().setEnabled(true);
		this.getCmbOrdenTrabajo().setEnabled(true);
		this.getCmbOrdenTrabajoDetalle().setEnabled(true);
		this.getCmbFechaCreacion().setEnabled(false);
		this.getCmbFechaPresupuesto().setEnabled(true);
		this.getCmbFechaAprobacion().setEnabled(true);
		this.getCmbEstado().setEnabled(true);
		this.getTxtModificacion().setEditable(false);
		this.getTxtCabecera().setEditable(true);
		this.getTxtConceptoPresupuestoDetalle().setEditable(true);
		this.getBtnBuscarProveedor().setEnabled(true);
		this.getBtnBuscarCorporacion().setEnabled(true);
		this.getBtnBuscarCliente().setEnabled(true);
		this.getBtnBuscarOficina().setEnabled(true);
		// initListeners();
		getTxtConceptoPresupuesto().grabFocus();
		this.getJtpPresupuesto().setSelectedIndex(0);
	}

	private void cleanListaProductos() {
		if (getCbListProductos().getModel().getSize() > 0) {
			getCbListProductos().getCheckBoxListSelectionModel()
					.clearSelection();
			((DefaultListModel) getCbListProductos().getModel())
					.removeAllElements();
		}
	}

	public boolean validateFieldsArchivo() {
		if (this.getCmbTipoArchivo().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de archivo !!!",
					SpiritAlert.WARNING);
			getCmbTipoArchivo().grabFocus();
			return false;
		}

		if ("".equals(this.getTxtArchivo().getText())) {
			SpiritAlert.createAlert("Debe ingresar la URL del archivo !!!",
					SpiritAlert.WARNING);
			this.getBtnBuscarArchivo().grabFocus();
			return false;
		}

		return true;
	}

	private void agregarArchivo() {
		try {
			if (validateFieldsArchivo()) {
				boolean isExisteArchivo = false;
				TipoArchivoIf tipoArchivo = (TipoArchivoIf) getCmbTipoArchivo()
						.getSelectedItem();

				// Si la coleccion tiene algun elemento
				if (archivosPresupuestoColeccion.size() != 0) {
					// Recorro la coleccion de Archivos
					for (int i = 0; i < archivosPresupuestoColeccion.size(); i++) {
						PresupuestoArchivoIf presupuestoArchivoTemp = (PresupuestoArchivoIf) archivosPresupuestoColeccion
								.get(i);
						// Si la reunion Archivo cargado ya esta en lista,
						// entonces muestro un mensaje de error
						if (presupuestoArchivoTemp.getTipoArchivoId().equals(
								tipoArchivo.getId())
								&& presupuestoArchivoTemp.getUrl().equals(
										getTxtArchivo().getText().replaceAll(
												" ", "_"))) {
							isExisteArchivo = true;
							break;
						}
					}
				}

				modelPresupuestoArchivo = (DefaultTableModel) getTblArchivo()
						.getModel();
				Vector<String> filaArchivo = new Vector<String>();

				if (isExisteArchivo == false) {
					PresupuestoArchivoData data = new PresupuestoArchivoData();

					data.setTipoArchivoId(tipoArchivo.getId());
					data.setUrl(getTxtArchivo().getText());
					archivosPresupuestoColeccion.add(data);

					File archivo = new File(getTxtArchivo().getText());
					archivosColeccion.add(archivo);

					archivosNombreColeccion.add(archivo.getName());

					filaArchivo.add(tipoArchivo.getNombre());
					filaArchivo.add(getTxtArchivo().getText());
					modelPresupuestoArchivo.addRow(filaArchivo);

					// Reinicio los componentes
					if (getCmbTipoArchivo().getItemCount() > 0)
						getCmbTipoArchivo().setSelectedIndex(0);

					getTxtArchivo().setText("");
					getTxtArchivo().grabFocus();

				} else {
					SpiritAlert.createAlert(
							"El Archivo ya se encuentra agregado !!!",
							SpiritAlert.INFORMATION);

					// Reinicio los componentes
					if (getCmbTipoArchivo().getItemCount() > 0)
						getCmbTipoArchivo().setSelectedIndex(0);

					getTxtArchivo().setText("");
					getTxtArchivo().grabFocus();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert(" No se pudo ingresar el detalle !!!",
					SpiritAlert.ERROR);
		}
	}

	private void actualizarArchivo() {
		int filaSeleccionada = getTblArchivo().getSelectedRow();
		if (filaSeleccionada >= 0) {
			TipoArchivoIf tipoArchivo = (TipoArchivoIf) getCmbTipoArchivo()
					.getSelectedItem();

			modelPresupuestoArchivo = (DefaultTableModel) getTblArchivo()
					.getModel();
			Vector<String> filaArchivo = new Vector<String>();

			if (validateFieldsArchivo()) {
				PresupuestoArchivoData data = new PresupuestoArchivoData();

				data.setId(archivosPresupuestoColeccion.get(filaSeleccionada)
						.getId());
				data.setTipoArchivoId(tipoArchivo.getId());
				data.setUrl(getTxtArchivo().getText());

				// Agregar a la coleccion para grabar al final toda la coleccion
				archivosPresupuestoColeccion.add(filaSeleccionada, data);
				archivosPresupuestoColeccion.remove(filaSeleccionada + 1);

				File archivo = new File(getTxtArchivo().getText());
				archivosColeccion.add(archivo);

				filaArchivo.add(tipoArchivo.getNombre());
				filaArchivo.add(getTxtArchivo().getText());

				modelPresupuestoArchivo
						.insertRow(filaSeleccionada, filaArchivo);
				modelPresupuestoArchivo.removeRow(filaSeleccionada + 1);

				// Reinicio los componentes
				if (getCmbTipoArchivo().getItemCount() > 0)
					getCmbTipoArchivo().setSelectedIndex(0);

				getTxtArchivo().setText("");
				getTxtArchivo().grabFocus();
			}
		} else {
			SpiritAlert.createAlert(
					"Debe seleccionar una fila para ser actualizada !",
					SpiritAlert.WARNING);
		}
	}

	private void eliminarArchivo() {
		if (getTblArchivo().getSelectedRow() != -1) {
			int filaSeleccionada = getTblArchivo().getSelectedRow();
			PresupuestoArchivoIf presupuestoArchivo = archivosPresupuestoColeccion
					.get(filaSeleccionada);
			if (presupuestoArchivo.getId() != null) {
				archivosEliminadosColeccion.add(presupuestoArchivo);
			}
			archivosColeccion.remove(getTblArchivo().getSelectedRow());
			archivosPresupuestoColeccion.remove(getTblArchivo()
					.getSelectedRow());
			modelPresupuestoArchivo.removeRow(getTblArchivo().getSelectedRow());
			getTxtArchivo().setText("");

		} else {
			SpiritAlert.createAlert(
					"Debe elegir el registro de la lista a eliminar !!!",
					SpiritAlert.WARNING);
		}
	}

	ActionListener oActionListenerAgregarArchivo = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {

			Component parent = (Component) actionEvent.getSource();
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAccessory(new LabelAccessory(fileChooser));
			FileFilter filterJPG = new ExtensionFileFilter(null, new String[] {"JPG", "JPEG" });
			fileChooser.addChoosableFileFilter(filterJPG);
			FileFilter filterGIF = new ExtensionFileFilter("gif",new String[] { "gif" });
			fileChooser.addChoosableFileFilter(filterGIF);
			FileFilter filterDOC = new ExtensionFileFilter("doc",new String[] { "doc" });
			fileChooser.addChoosableFileFilter(filterDOC);
			fileChooser.setFileFilter(fileChooser.getAcceptAllFileFilter());
			int status = fileChooser.showOpenDialog(parent);

			if (status == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				Object[] options = { "Si", "No" };
				try {
					boolean existe = SessionServiceLocator.getFileManagerSessionService().existeArchivo(
						Parametros.getUrlCarpetaServidor(),selectedFile.getName());
					if (!existe) {
						/**
						 * valido que botón ha sido presionado y le asigno al
						 * correspondiente textbox el path del archivo que haya
						 * seleccionado
						 */

						if ((actionEvent.getSource() == getBtnBuscarArchivo()))
							getTxtArchivo().setText(fileChooser.getSelectedFile().toString());

						/**
						 * ejecuto el archivo con su respectivo programa para
						 * poder ser previsualizado
						 */
						try {
							int opcion = JOptionPane.showOptionDialog(null,
									"Desea previsualizar el archivo?",
									"Confirmación", JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									options, "No");
							if (opcion == JOptionPane.YES_OPTION) {
								String filename = selectedFile.getAbsolutePath();
								Archivos.abrirArchivoLocal(filename);
							}

						} catch (IOException e) {
							SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
							e.printStackTrace();
						}

					} else {
						int opcion = JOptionPane.showOptionDialog(null,
								"Archivo ya existe, desea reemplazarlo?",
								"Confirmación", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								"No");
						if (opcion == JOptionPane.YES_OPTION) {
							if ((actionEvent.getSource() == getBtnBuscarArchivo()))
								getTxtArchivo().setText(fileChooser.getSelectedFile().toString());
						} else {
							if ((actionEvent.getSource() == getBtnBuscarArchivo()))
								getTxtArchivo().setText("");
						}
					}
				} catch (GenericBusinessException e1) {
					e1.printStackTrace();
					SpiritAlert.createAlert(e1.getMessage(), SpiritAlert.ERROR);
				}
			}
		}
	};

	Comparator<ProductoClienteIf> ordenadorProductoClienteIf = new Comparator<ProductoClienteIf>() {
		public int compare(ProductoClienteIf o1, ProductoClienteIf o2) {
			return o1.getMarcaProductoNombre().compareTo(
					o2.getMarcaProductoNombre());
		}
	};

	private void cargarListaProductos() {
		try {
			if (getCmbOrdenTrabajo().getSelectedItem() != null) {
				DefaultListModel model = new DefaultListModel();
				OrdenTrabajoIf ordenTrabajo = (OrdenTrabajoIf) getCmbOrdenTrabajo().getSelectedItem();
				List<ProductoClienteIf> productosCliente = (ArrayList<ProductoClienteIf>) SessionServiceLocator
					.getProductoClienteSessionService().findProductoClienteByOrdenTrabajoId(ordenTrabajo.getId());
				Collections.sort((ArrayList) productosCliente,ordenadorProductoClienteIf);
				Iterator productosClienteIt = productosCliente.iterator();

				while (productosClienteIt.hasNext()) {
					ProductoClienteIf productoCliente = (ProductoClienteIf) productosClienteIt.next();
					model.addElement(productoCliente);
				}
				getCbListProductos().setModel(model);
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void ingresoAutomaticoDetalleComisionAdicional(Long referenciaId, String tipoReferencia){		
		try {
			Collection proveedores = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByDescripcion(((EmpresaIf)Parametros.getEmpresa()).getNombre());
			
			if(proveedores.size() > 0){
				proveedorIf = (ClienteOficinaIf)proveedores.iterator().next();									
				Collection comisionesAgencia = SessionServiceLocator.getGenericoSessionService().findGenericoByNombre("COMISION DE AGENCIA");
				if(comisionesAgencia.size() > 0){
					GenericoIf genericoIf = (GenericoIf)comisionesAgencia.iterator().next();
					Map aMapProducto = new HashMap();
					aMapProducto.put("proveedorId", proveedorIf.getId());
					aMapProducto.put("genericoId", genericoIf.getId());
					Collection productosProveedor = SessionServiceLocator.getProductoSessionService().findProductoByQuery(aMapProducto);
					if(productosProveedor.size() > 0){
						productoIf = (ProductoIf)productosProveedor.iterator().next();
											
						double comisionAdicional = 0D;
						String valorComisionAdicional = "0.00";
												
						//calculo de comision adicional
						if(tipoReferencia.equals("P")){
							Map aMapPresupuestoDetalle = new HashMap();
							aMapPresupuestoDetalle.put("presupuestoId", referenciaId);
							aMapPresupuestoDetalle.put("proveedorId", clienteOficinaIf.getId());
							aMapPresupuestoDetalle.put("reporte", "N");											
							Collection presupuestosDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByQuery(aMapPresupuestoDetalle);
							if(presupuestosDetalle.size() > 0){
								PresupuestoDetalleIf presupuestoDetalleReferencia = (PresupuestoDetalleIf)presupuestosDetalle.iterator().next();
								
								if (presupuestoDetalleReferencia.getPorcentajeComisionAdicional() != null) {
									comisionAdicional = presupuestoDetalleReferencia.getPrecioagencia().doubleValue() * (presupuestoDetalleReferencia.getPorcentajeComisionAdicional().doubleValue() / 100D);
									valorComisionAdicional = formatoDecimal.format(comisionAdicional);
								}
							}
						}else if(tipoReferencia.equals("M")){
							Map aMapOrdenMedio = new HashMap();
							aMapOrdenMedio.put("planMedioId", referenciaId);
							aMapOrdenMedio.put("proveedorId", clienteOficinaIf.getId());									
							Collection ordenesMedio = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQuery(aMapOrdenMedio);
							if(ordenesMedio.size() > 0){
								OrdenMedioIf ordenMedioReferencia = (OrdenMedioIf)ordenesMedio.iterator().next();
								
								if(ordenMedioReferencia.getPorcentajeComisionAdicional() != null){
									comisionAdicional = ordenMedioReferencia.getValorSubtotal().doubleValue() * (ordenMedioReferencia.getPorcentajeComisionAdicional().doubleValue() / 100D);
									valorComisionAdicional = formatoDecimal.format(comisionAdicional);
								}
							}
						}
						
						if(comisionAdicional > 0){
							if ("".equals(getTxtPorcentajeDsctoAgenciaVenta().getText()))
								getTxtPorcentajeDsctoAgenciaVenta().setText("0");
							if ("".equals(getTxtPorcentajeDescuentoEspecialVenta().getText()))
								getTxtPorcentajeDescuentoEspecialVenta().setText("0");
							if ("".equals(getTxtPorcentajeDescuentosVariosVenta().getText()))
								getTxtPorcentajeDescuentosVariosVenta().setText("0");
							if ("".equals(getTxtPorcentajeDsctoAgenciaCompra().getText()))
								getTxtPorcentajeDsctoAgenciaCompra().setText("0");
							if ("".equals(getTxtPorcentajeDescuentoEspecialCompra().getText()))
								getTxtPorcentajeDescuentoEspecialCompra().setText("0");
							if ("".equals(getTxtPorcentajeDescuentosVariosCompra().getText()))
								getTxtPorcentajeDescuentosVariosCompra().setText("0");
							if ("".equals(getTxtPorcentajeNotaCredito().getText()))
								getTxtPorcentajeNotaCredito().setText("0");
							if ("".equals(getTxtPorcentajeNegociacionDirecta().getText()))
								getTxtPorcentajeNegociacionDirecta().setText("0");
							if ("".equals(getTxtPorcentajeComisionPura().getText()))
								getTxtPorcentajeComisionPura().setText("0");
							if ("".equals(getTxtPorcentajeComisionAdicional().getText()))
								getTxtPorcentajeComisionAdicional().setText("0");
							
							getTxtConceptoPresupuestoDetalle().setText("COMISION ADICIONAL");
							getTxtCantidad().setText("1");
							getTxtPrecioCompra().setText(valorComisionAdicional);
							getTxtPrecioVenta().setText(valorComisionAdicional);
																		
							agregarDetalle();
							calcularDetalle();
						}						
					}
				}
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void initListeners() {
		getBtnReferencia().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					if (clienteOficinaIf == null){
						SpiritAlert.createAlert("Primero debe seleccionar la Oficina del Cliente", SpiritAlert.WARNING);
					}else if(getCmbTipoReferencia().getSelectedItem().equals("NINGUNO")){
						SpiritAlert.createAlert("Primero debe seleccionar un Tipo de Referencia", SpiritAlert.WARNING);
					}
					else if(getCmbTipoReferencia().getSelectedItem().equals("PRESUPUESTO")){
						Long clienteId = 0L, clienteOficinaId = 0L;
						
						if (clienteIf != null)
							clienteId = clienteIf.getId();
						
						if (clienteOficinaIf != null)
							clienteOficinaId = clienteOficinaIf.getId();
						
						String[] estados = {"A","F"};
						//int tamanoLista;
						//tamanoLista = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByEmpresaIdByProveedorIdAndByEstadosSize(clienteOficinaIf.getId(), Parametros.getIdEmpresa(), estados);
						Collection presupuestosReferencia = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByEmpresaIdByProveedorIdAndByEstados(0,10000,clienteOficinaIf.getId(), Parametros.getIdEmpresa(), estados);
						int tamanoLista = presupuestosReferencia.size();
												
						if (tamanoLista > 0) {
							PresupuestoCriteria presupuestoCriteria = new PresupuestoCriteria(clienteId, clienteOficinaId);
							presupuestoCriteria.setResultListSize(tamanoLista);
							presupuestoCriteria.setEstados(estados);
							presupuestoCriteria.setBusquedaPorProveedor(true);
							Vector<Integer> anchoColumnas = new Vector<Integer>();
							anchoColumnas.add(70);
							anchoColumnas.add(70);	
							anchoColumnas.add(270);
							anchoColumnas.add(400);
							JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), presupuestoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, null);
							if ( popupFinder.getElementoSeleccionado() != null ) {
								PresupuestoIf referenciaPresupuestoIf = (PresupuestoIf) popupFinder.getElementoSeleccionado();
								getTxtReferencia().setText(referenciaPresupuestoIf.getCodigo());
								referenciaId = referenciaPresupuestoIf.getId();
								tipoReferencia = "P";
								
								ingresoAutomaticoDetalleComisionAdicional(referenciaId, tipoReferencia);	
							}
						} else {
							SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.INFORMATION);
						}
					}
					else if(getCmbTipoReferencia().getSelectedItem().equals("PLAN DE MEDIOS")){
						Long clienteId = 0L, clienteOficinaId = 0L;
						
						if (clienteIf != null)
							clienteId = clienteIf.getId();
						
						if (clienteOficinaIf != null)
							clienteOficinaId = clienteOficinaIf.getId();
						
						Map mapa = new HashMap();		
						
						//se busca los Planes de Medio x cliente id		
						//ESTADO APROBADO, PEDIDO y FACTURADO
						//int tamanoLista = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByQueryByProveedorIdByEmpresaIdAndByEstadosSize(mapa, clienteOficinaId, Parametros.getIdEmpresa(), "A","D","F");
						Collection planMediosReferencia = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByQueryByProveedorIdByEmpresaIdAndByEstados(0,10000, mapa, clienteOficinaId, Parametros.getIdEmpresa(), "A","D","F");
						int tamanoLista = planMediosReferencia.size();
						
						if (tamanoLista > 0) {
							PlanMedioCriteria planMedioCriteria = new PlanMedioCriteria(clienteOficinaId,true,"A","D","F"); 
							planMedioCriteria.setResultListSize(tamanoLista);
							planMedioCriteria.setQueryBuilded(mapa);
							
							Vector<Integer> anchoColumnas = new Vector<Integer>();
							anchoColumnas.add(70);
							anchoColumnas.add(70);	
							anchoColumnas.add(270);
							anchoColumnas.add(400);
							
							JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),planMedioCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
							
							if (popupFinder.getElementoSeleccionado() != null) {
								PlanMedioIf referenciaPlanMedioIf = (PlanMedioIf) popupFinder.getElementoSeleccionado();
								getTxtReferencia().setText(referenciaPlanMedioIf.getCodigo());
								referenciaId = referenciaPlanMedioIf.getId();
								tipoReferencia = "M";
								
								ingresoAutomaticoDetalleComisionAdicional(referenciaId, tipoReferencia);
							}
						} else {
							SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.INFORMATION);
						}
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}
			}
		});
		
		getBtnLimpiarReferencia().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				referenciaId = null;
				tipoReferencia = null;
				getTxtReferencia().setText("");
			}
		});
		
		getBtnReorganizarTabla().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getTblPresupuestoDetalle().setRowSorter(null);
				setSorterTable(getTblPresupuestoDetalle());
			}
		});
		
		getCbNegociacionDirecta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbNegociacionDirecta().isSelected()){
					getTxtPorcentajeNegociacionDirecta().setEditable(true);
					getTxtPorcentajeNegociacionDirecta().setText("0");
				}else{
					getTxtPorcentajeNegociacionDirecta().setEditable(false);
					getTxtPorcentajeNegociacionDirecta().setText("");
				}
			}
		});
		
		getCbComisionPura().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbComisionPura().isSelected()){
					getTxtPorcentajeComisionPura().setEditable(true);
					getTxtPorcentajeComisionPura().setText("0");
				}else{
					getTxtPorcentajeComisionPura().setEditable(false);
					getTxtPorcentajeComisionPura().setText("");
				}
			}
		});
		
		getCbComisionAdicional().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbComisionAdicional().isSelected()){
					getTxtPorcentajeComisionAdicional().setEditable(true);
					getTxtPorcentajeComisionAdicional().setText("0");
				}else{
					getTxtPorcentajeComisionAdicional().setEditable(false);
					getTxtPorcentajeComisionAdicional().setText("");
				}
			}
		});
		
		getBtnSeleccionarTodo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCbListProductos().getCheckBoxListSelectionModel()
					.addSelectionInterval(0,getCbListProductos().getModel().getSize() - 1);
			}
		});

		getBtnDeseleccionarTodo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCbListProductos().getCheckBoxListSelectionModel().clearSelection();
			}
		});

		getBtnActualizarDetalle().addActionListener(oActionListenerBtnActualizarPresupuestoDetalle);
		getBtnActualizarDetalleP().addActionListener(oActionListenerBtnActualizarPresupuestoDetalleP);

		getCmbOrdenTrabajo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarOrdenTrabajoDetalle();
				cargarListaProductos();
			}
		});

		getCmbOrdenTrabajoDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbOrdenTrabajoDetalle().getSelectedItem() != null) {
					OrdenTrabajoDetalleIf ordenTrabajoDetalle = (OrdenTrabajoDetalleIf) ((InfoOrdenTrabajoDetalle) getCmbOrdenTrabajoDetalle()
							.getSelectedItem()).getOrdenTrabajoDetalleIf();
					if (ordenTrabajoDetalle != null) {
						try {
							SubtipoOrdenIf subTipoTemp = mapaSubTiposOrden.get(ordenTrabajoDetalle.getSubtipoId());
							getTxtSubTipoOrden().setText(subTipoTemp.getNombre());
							getTxtDescripcionOTdetalle().setText(ordenTrabajoDetalle.getDescripcion());

							Collection presupuestos = SessionServiceLocator.getPresupuestoSessionService()
								.findPresupuestoByOrdentrabajodetId(ordenTrabajoDetalle.getId());
							getTxtContadorPresupuestos().setText(String.valueOf(presupuestos.size()));
						} catch (GenericBusinessException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});

		// Opcion Que Permite Borrar un regitsro deseado de la tabla de archivo
		JMenuItem itemEliminarArchivo = new JMenuItem("Eliminar");
		popupMenuArchivo.add(itemEliminarArchivo);
		// Añado el listener de menupopup
		itemEliminarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarArchivo();
			}
		});

		// Opcion Que Permite Visualizar un archivo deseado de la tabla de
		// archivos
		JMenuItem itemVerArchivo = new JMenuItem("Visualizar Archivo");
		popupMenuArchivo.add(itemVerArchivo);
		// Añado el listener de menupopup
		itemVerArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getTblArchivo().getSelectedRow() != -1) {
					try {
						String urlArchivo = (String) getTblArchivo().getModel()
								.getValueAt(getTblArchivo().getSelectedRow(), 1);
						Archivos.abrirArchivoDesdeServidor(urlArchivo);
					} catch (IOException e) {
						SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
						e.printStackTrace();
					}
				} else {
					SpiritAlert.createAlert("Debe elegir el registro de la lista a visualizar !",
									SpiritAlert.WARNING);
				}
			}
		});

		getBtnVerArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (!getTxtArchivo().getText().equals("")) {
					try {
						String urlArchivo = getTxtArchivo().getText();
						Archivos.abrirArchivoDesdeServidor(urlArchivo);
					} catch (IOException e) {
						SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
						e.printStackTrace();
					}
				} else {
					SpiritAlert.createAlert("Debe elegir el archivo a visualizar !",SpiritAlert.WARNING);
				}
			}
		});

		// Listenner de la tabla de reunion archivo
		getTblArchivo().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if (getTblArchivo().getSelectedRow() != -1) {
					for (int i = 0; i < getCmbTipoArchivo().getItemCount(); i++) {
						TipoArchivoIf bean = (TipoArchivoIf) getCmbTipoArchivo().getItemAt(i);
						if (bean.getNombre().compareTo(getTblArchivo().getModel().getValueAt(
							getTblArchivo().getSelectedRow(), 0).toString()) == 0)
							getCmbTipoArchivo().setSelectedItem(bean);
						getCmbTipoArchivo().repaint();
					}

					getTxtArchivo().setText(getTblArchivo().getModel().getValueAt(
						getTblArchivo().getSelectedRow(), 1).toString());
				}
			}

			public void mouseReleased(MouseEvent evt) {
				if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblArchivo().getModel().getRowCount() > 0) {
					popupMenuArchivo.show(evt.getComponent(), evt.getX(), evt.getY());
				}
			}
		});

		getBtnAgregarArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarArchivo();
			}
		});

		getBtnActualizarArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarArchivo();
			}
		});

		getBtnEliminarArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarArchivo();
			}
		});

		getBtnBuscarArchivo().addActionListener(oActionListenerAgregarArchivo);

		/*
		 * getTxtPrecioAgencia().addCaretListener(new CaretListener() { public
		 * void caretUpdate(CaretEvent e) {
		 * getTxtPrecioVenta().setText(getTxtPrecioAgencia().getText());
		 * getTxtPrecioAgencia().grabFocus(); } });
		 */

		if (getMode() == SpiritMode.SAVE_MODE) {
			getBtnBuscarProveedor().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evento) {
					try {
						Long idCorporacion = 0L;
						Long idCliente = 0L;
						String tipoCliente = CODIGO_TIPO_PROVEEDOR;
						String tituloVentanaBusqueda = "Proveedores";
						Vector<Integer> anchoColumnas = new Vector<Integer>();
						anchoColumnas.add(150);
						anchoColumnas.add(450);
						anchoColumnas.add(100);
						anchoColumnas.add(400);
						String mmpg = "MPG";
						TipoOrdenIf tipoOrden = (TipoOrdenIf) getCmbTipoOrden().getSelectedItem();
						
						//Quito la letra que representa el tipo de proveedor que estoy buscando
						//Medios, Produccion, Gasto
						if (tipoOrden != null)
							mmpg = mmpg.replaceAll(tipoOrden.getTipo(), "");
						
						//Se solicito que en Creacional que se puedan ver todos los proveedores de Medios y Produccion
						mmpg = "G";
						
						clienteOficinaCriteria = new ClienteOficinaCriteria(
								tituloVentanaBusqueda, idCorporacion, idCliente,tipoCliente, mmpg, false);
						JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros
								.getMainFrame(), clienteOficinaCriteria,
								JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS,
								anchoColumnas, null);
						if (popupFinder.getElementoSeleccionado() != null) {
							proveedorIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
							
							ClienteIf operadorProveedor = GeneralUtil.verificarMapaCliente(mapaClientes, proveedorIf.getClienteId());
							String estadoOperadorProveedor = operadorProveedor.getEstado();
							if(estadoOperadorProveedor.equals(ESTADO_RIESGO)){
								SpiritAlert.createAlert("Proveedor: " + operadorProveedor.getNombreLegal() + " es sujeto de RIESGO, consulte con el Dpto. de Contabilidad",SpiritAlert.WARNING);
							}else{
								CiudadIf ciudad = mapaCiudades.get(proveedorIf.getCiudadId());
								cargarProveedor(proveedorIf, ciudad);
							}							
						}
						
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}

				private void cargarProveedor(ClienteOficinaIf operadorProveedor, CiudadIf ciudad)
						throws GenericBusinessException {
					getTxtProveedor().setText(ciudad.getCodigo() + " - "+ operadorProveedor.getDescripcion());

					getTxtProducto().setText("");
					productoIf = null;
					getBtnBuscarProducto().setEnabled(true);
					getTxtProducto().setText("");
					getTxtConceptoPresupuestoDetalle().setText("");
					getTxtPrecioCompra().setText("");
					getTxtPrecioVenta().setText("");
					getTxtCantidad().setText("");
					getTxtPorcentajeDsctoAgenciaVenta().setText("0");
					getTxtPorcentajeDescuentoEspecialVenta().setText("0");
					getTxtPorcentajeDescuentosVariosVenta().setText("0");
					getTxtPorcentajeDsctoAgenciaCompra().setText("0");
					getTxtPorcentajeDescuentoEspecialCompra().setText("0");
					getTxtPorcentajeDescuentosVariosCompra().setText("0");
					getTxtPorcentajeNotaCredito().setText("0");
					getCbNegociacionDirecta().setSelected(false);
					getCbComisionPura().setSelected(false);
					getTxtPorcentajeNegociacionDirecta().setEditable(false);
					getTxtPorcentajeNegociacionDirecta().setText("");
					getTxtPorcentajeComisionPura().setEditable(false);					
					getTxtPorcentajeComisionPura().setText("");
					getTxtPorcentajeComisionAdicional().setEditable(false);					
					getTxtPorcentajeComisionAdicional().setText("");
				}
			});

			// Manejo los eventos de Buscar Corporación
			getBtnBuscarCorporacion().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evento) {
					corporacionCriteria = new CorporacionCriteria(
							"Corporaciones", Parametros.getIdEmpresa());
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.add(80);
					anchoColumnas.add(500);
					JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros
							.getMainFrame(), corporacionCriteria,
							JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS,
							anchoColumnas, null);
					if (popupFinder.getElementoSeleccionado() != null) {
						corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
						getTxtCorporacion().setText(
							corporacionIf.getCodigo() + " - "+ corporacionIf.getNombre());
						getTxtCliente().setText("");
						clienteIf = null;
						getTxtOficina().setText("");
						clienteOficinaIf = null;
						getCmbOrdenTrabajo().setSelectedItem(null);
						getCmbOrdenTrabajoDetalle().setSelectedItem(null);
						getCmbOrdenTrabajo().setEnabled(false);
						getCmbOrdenTrabajoDetalle().setEnabled(false);
						getCmbOrdenTrabajo().removeAllItems();
						getCmbOrdenTrabajoDetalle().removeAllItems();
						cleanListaProductos();
					}
				}
			});

			// Manejo los eventos de Buscar Cliente
			getBtnBuscarCliente().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evento) {
					Long idCorporacion = 0L;
					if (corporacionIf != null)
						idCorporacion = corporacionIf.getId();

					clienteCriteria = new ClienteCriteria("Clientes",
							idCorporacion, CODIGO_TIPO_CLIENTE);
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.add(80);
					anchoColumnas.add(300);
					anchoColumnas.add(300);
					JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros
							.getMainFrame(), clienteCriteria,
							JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS,
							anchoColumnas, null);
					if (popupFinder.getElementoSeleccionado() != null) {
						cleanListaProductos();
						getModelProductoCliente().removeAllElements();
						productoClienteList = new ArrayList<ProductoClienteIf>();
						clienteIf = (ClienteIf) popupFinder
								.getElementoSeleccionado();
						getTxtCliente().setText(clienteIf.getNombreLegal());
						
						if(clienteIf.getInformacionAdc() != null && !clienteIf.getInformacionAdc().equals("")){
							SpiritAlert.createAlert("INFORMACIÓN: \n" + clienteIf.getInformacionAdc(),SpiritAlert.INFORMATION);
						}
						
						if (corporacionIf == null) {
							try {
								corporacionIf = SessionServiceLocator.getCorporacionSessionService()
									.getCorporacion(clienteIf.getCorporacionId());
								getTxtCorporacion().setText(corporacionIf.getCodigo() + " - "
												+ corporacionIf.getNombre());
							} catch (GenericBusinessException e) {
								SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
								e.printStackTrace();
							}
						}
						clienteOficinaIf = null;
						referenciaId = null;
						tipoReferencia = null;
						getTxtReferencia().setText("");
						getTxtOficina().setText("");
						getCmbOrdenTrabajo().setSelectedItem(null);
						getCmbOrdenTrabajoDetalle().setSelectedItem(null);
						getCmbOrdenTrabajo().setEnabled(false);
						getCmbOrdenTrabajoDetalle().setEnabled(false);
						getCmbOrdenTrabajo().removeAllItems();
						getCmbOrdenTrabajoDetalle().removeAllItems();
					}
				}
			});

			// Manejo los eventos de Buscar Cliente Oficina
			getBtnBuscarOficina().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evento) {
					Long idCorporacion = 0L;
					Long idCliente = 0L;
					String tipoCliente = CODIGO_TIPO_CLIENTE;
					String tituloVentanaBusqueda = "Oficinas de Clientes";

					if (corporacionIf != null)
						idCorporacion = corporacionIf.getId();

					if (clienteIf != null)
						idCliente = clienteIf.getId();

					clienteOficinaCriteria = new ClienteOficinaCriteria(
							tituloVentanaBusqueda, idCorporacion, idCliente,tipoCliente, "", false);
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.addElement(70);
					anchoColumnas.addElement(200);
					anchoColumnas.addElement(80);
					anchoColumnas.addElement(230);
					JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros
							.getMainFrame(), clienteOficinaCriteria,
							JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS,anchoColumnas, null);
					if (popupFinder.getElementoSeleccionado() != null) {
						clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
						referenciaId = null;
						tipoReferencia = null;
						getTxtReferencia().setText("");
						seleccionarClienteOficina();
					}
				}

				
			});

			// Manejo los eventos del Combo de Tipo de Orden
			getCmbTipoOrden().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evento) {
					Map<Integer,PresupuestoDetalleIf> detallesNoValidosMap = verificacionDetallesTipoOrden();
					Map<Integer,PresupuestoDetalleIf> reporteNoValidosMap = verificacionReporteTipoOrden();
					if (detallesNoValidosMap.size() > 0) {
						int opcion = JOptionPane
								.showOptionDialog(
										null,
										"El presupuesto contiene detalles que no se corresponden con el tipo de orden seleccionado.\n"
												+ "Si cambia el tipo de orden, se eliminarán del presupuesto estos detalles. ¿Desea continuar?",
										"Confirmación",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										options, no);
						if (opcion == JOptionPane.YES_OPTION) {
							removerDetallesNoValidos(detallesNoValidosMap, reporteNoValidosMap);
							buscarOrdenTrabajoDetalle();
							cleanDetalle();
						} else {
							getCmbTipoOrden().setSelectedIndex(
								ComboBoxComponent.getIndexToSelectItem(getCmbTipoOrden(), tipoOrdenIf.getId()));
							getCmbTipoOrden().validate();
							getCmbTipoOrden().repaint();
						}
					} else {
						cargarComboOrdenTrabajo();
						buscarOrdenTrabajoDetalle();
					}

					tipoOrdenIf = (TipoOrdenIf) getCmbTipoOrden()
							.getSelectedItem();
				}
			});

			// Mando a cargar el popup de las pantallas mantenedoras de Producto
			getBtnBuscarProducto().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evento) {					
					try {
						if (proveedorIf != null) {
							String generoProducto = "A";
							TipoOrdenIf tipoOrden = (TipoOrdenIf) getCmbTipoOrden()
									.getSelectedItem();
							/*
							 * if(tipoOrden.getTipo().equals(TIPO_PRESUPUESTO)){
							 * tipo = TIPO_PRESUPUESTO; }else
							 * if(tipoOrden.getTipo().equals(TIPO_MEDIOS)){ tipo =
							 * TIPO_MEDIOS; }
							 */
							String mmpg = "";
							
							//Limitar busqueda por tipo de producto, cliente Creacional no desea que aparezcan productos
							//tipo GAV - GASTOS VARIOS
							Long tipoproductoGastosVariosId = 0L;
							Collection tiposProductoGastosVarios = SessionServiceLocator.getTipoProductoSessionService().findTipoProductoByCodigo("GAV");
							if(Parametros.getExcluirProductoTipoGastosVarios().equals("S") && tiposProductoGastosVarios.size() > 0){
								tipoproductoGastosVariosId = ((TipoProductoIf)tiposProductoGastosVarios.iterator().next()).getId();
							}
							
							productoCriteria = new ProductoCriteria("Producto",
									proveedorIf.getId(), generoProducto,
									BUSQUEDA_POR_PROVEEDOR, SERVICIO_CONSUMO,
									false, mmpg);
							
							JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros
									.getMainFrame(), productoCriteria,
									JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
							if (popupFinder.getElementoSeleccionado() != null) {
								ProductoIf producto = (ProductoIf) popupFinder.getElementoSeleccionado();
								GenericoIf generico = mapaGenericos.get(producto.getGenericoId());
								
								if(generico.getTipoproductoId().compareTo(tipoproductoGastosVariosId) == 0){
									SpiritAlert.createAlert("El Producto no puede ser seleccionado porque es tipo Gastos Varios.", SpiritAlert.WARNING);
								}else{
									/*if (generico.getMediosProduccion().equals(tipoOrden.getTipo())
									|| generico.getMediosProduccion().equals(TIPO_GASTO)) {*/
									productoIf = producto;
									if ("S".equals(generico.getUsaLote())) {
										PresentacionIf presentacion = SessionServiceLocator
										.getPresentacionSessionService()
										.getPresentacion(productoIf.getPresentacionId());
										getTxtProducto().setText(generico.getNombre());
										getTxtConceptoPresupuestoDetalle().setText(
												generico.getNombre()+ " - "+ presentacion.getNombre());
										getTxtConceptoPresupuestoDetalle().setEditable(false);
									} else {
										if (tipoOrden.getTipo().equals(TIPO_MEDIOS)) {
											getTxtConceptoPresupuestoDetalle().setText(proveedorIf.getDescripcion()
													+ "\n"+ generico.getNombre() + "\nTEMA:"
													/*+ "\nORDEN:" + "\nPUBLICACION:"*/);
										} else {
											getTxtConceptoPresupuestoDetalle().setText(generico.getNombre());
										}
										getTxtProducto().setText(generico.getNombre());
										getTxtConceptoPresupuestoDetalle().setEditable(true);
									}
									/*} else {
									int opcion = JOptionPane
										.showOptionDialog(
											null,
											"El producto seleccionado no se corresponde con el tipo de orden del presupuesto.\n¿Desea cambiar el tipo de orden para este presupuesto?",
											"Confirmación",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE,
											null, options, no);
									if (opcion == JOptionPane.YES_OPTION) {
										getJtpPresupuesto().setSelectedIndex(0);
										getCmbTipoOrden().grabFocus();
									}
								}*/
								}								
							}
						} else {
							SpiritAlert.createAlert(
								"Debe seleccionar primero un Proveedor para buscar un Producto",SpiritAlert.INFORMATION);
						}
					} catch (GenericBusinessException e1) {
						e1.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
					}					
				}
			});
		}

		getTxtPorcentajeComision().addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				try {
					double porcentajeComision = 0D;
						
					if (!getTxtPorcentajeComision().getText().equals(""))
						porcentajeComision = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPorcentajeComision().getText()));
	
					if (porcentajeComision != porcentajeComisionAgencia) {
						porcentajeComisionAgencia = porcentajeComision;						
						actualizarTotales(false);
						
						getTblPresupuestoDetalle().getSelectionModel().removeSelectionInterval(
							getTblPresupuestoDetalle().getRowCount() - 1,getTblPresupuestoDetalle().getRowCount() - 1);
						getTxtPorcentajeComision().grabFocus();
					}
				} catch (GenericBusinessException e1) {
					e1.printStackTrace();
					SpiritAlert.createAlert(e1.getMessage(), SpiritAlert.ERROR);
				}
			}

		});
		
		getTxtDiasValidez().addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				/*int diasValidez = 0;
				if (!getTxtDiasValidez().getText().trim().equals(""))
					diasValidez = Integer.parseInt(getTxtDiasValidez()
							.getText());
				if (getCmbFechaInicial().getCalendar() != null) {
					getCmbFechaValidez().setCalendar(
							(Calendar) getCmbFechaInicial().getCalendar()
									.clone());
					getCmbFechaValidez().getCalendar().add(Calendar.DATE,
							diasValidez);
					getCmbFechaValidez().validate();
					getCmbFechaValidez().repaint();
					fechaValidez = getCmbFechaValidez().getDate();
				}*/
			}
		});
	}

	private void cargarComboOrdenTrabajo() {
		try {
			if (clienteOficinaIf != null) {
				List ordenesTrabajo = new ArrayList();
				TipoOrdenIf tipoOrden = (TipoOrdenIf) getCmbTipoOrden().getSelectedItem();
				
				boolean saveMode = false;
				if(getMode() == SpiritMode.SAVE_MODE)
					saveMode = true;
				
				if (saveMode && tipoOrden == null) {
					ordenesTrabajo = (List) SessionServiceLocator.getOrdenTrabajoSessionService()
						.findOrdenTrabajoByClienteOficinaIdAndEstadoAndEmpresaId(clienteOficinaIf.getId(),
							Parametros.getIdEmpresa());
				} else {					
					if (tipoOrden.getCodigo().equals(TIPO_ORDEN_CODIGO_CREATIVO)) {
						ordenesTrabajo = (ArrayList) SessionServiceLocator.getOrdenTrabajoSessionService()
							.findOrdenTrabajoPendienteByClienteOficinaIdAndTipoOrden(
								clienteOficinaIf.getId(),TIPO_ORDEN_CODIGO_CREATIVO, saveMode);
					} else if (tipoOrden.getCodigo().equals(
							TIPO_ORDEN_CODIGO_MEDIOS)) {
						ordenesTrabajo = (ArrayList) SessionServiceLocator.getOrdenTrabajoSessionService()
							.findOrdenTrabajoPendienteByClienteOficinaIdAndTipoOrden(
								clienteOficinaIf.getId(),TIPO_ORDEN_CODIGO_MEDIOS, saveMode);
					} else if (tipoOrden.getCodigo().equals(
							TIPO_ORDEN_CODIGO_PRODUCCION)) {
						ordenesTrabajo = (ArrayList) SessionServiceLocator.getOrdenTrabajoSessionService()
							.findOrdenTrabajoPendienteByClienteOficinaIdAndTipoOrden(
								clienteOficinaIf.getId(),TIPO_ORDEN_CODIGO_PRODUCCION, saveMode);
					}
				}
				refreshCombo(getCmbOrdenTrabajo(), ordenesTrabajo);
				getCmbOrdenTrabajo().setSelectedIndex(-1);
				getCmbOrdenTrabajoDetalle().setSelectedIndex(-1);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
		}
	}

	private void seleccionarClienteOficina() {
		getTxtOficina().setText(clienteOficinaIf.getCodigo() + " - "+ clienteOficinaIf.getDescripcion());
		if (clienteIf == null) {
			try {
				//clienteIf = SessionServiceLocator.getClienteSessionService()
				//	.getCliente(clienteOficinaIf.getClienteId());
				clienteIf = GeneralUtil.verificarMapaCliente(mapaClientes, clienteOficinaIf.getClienteId());
								
				getTxtCliente().setText(clienteIf.getNombreLegal());
				
				if(clienteIf.getInformacionAdc() != null && !clienteIf.getInformacionAdc().equals("")){
					SpiritAlert.createAlert("INFORMACIÓN: \n" + clienteIf.getInformacionAdc(),SpiritAlert.INFORMATION);
				}
				
				if (corporacionIf == null) {
					corporacionIf = SessionServiceLocator.getCorporacionSessionService()
						.getCorporacion(clienteIf.getCorporacionId());
					getTxtCorporacion().setText(corporacionIf.getNombre());
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
		// hago esta validación por que el find lo hace por
		// cliente oficina y no quiero que se abilite el combo
		// de orden trabajo
		if (!(getMode() == SpiritMode.FIND_MODE)) {
			// Hablilito el combo de Orden Trabajo
			getCmbOrdenTrabajo().setEnabled(true);
			// Remuevo Los Items de Orden Trabajo y de Orden
			// Trabajo Detalle
			getCmbOrdenTrabajo().removeAllItems();
			getCmbOrdenTrabajoDetalle().removeAllItems();
			// Mando a Cargar el combo de Orden Trabajo por
			// ClienteOficina
			cargarComboOrdenTrabajo();
			// Mando a Buscar las orden Trabajo detalle segun el
			// tipo orden y la orden Trabajo seteadas
			buscarOrdenTrabajoDetalle();
		}
	}
	
	private void cargarComboFormaPago() {
		try {
			List formasPago = (List) SessionServiceLocator
					.getFormaPagoSessionService().findFormaPagoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbFormaPago(), formasPago);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	// Action Listener que manda a buscar los Orden Trabajo Detalle
	public void buscarOrdenTrabajoDetalle() {
		// Veo que ninguno de los dos combos no tenga lso elementos
		// selccionaodos para la busuqeda adecuada
		if (getCmbOrdenTrabajo().getModel().getSelectedItem() != null
				&& getCmbTipoOrden().getModel().getSelectedItem() != null) {
			// Veo cuales fueron de los combos TipoOrden y Orden Trabajo los
			// elementos seleccionados
			tipoOrdenIf = (TipoOrdenIf) getCmbTipoOrden().getModel().getSelectedItem();
			ordenTrabajoIf = (OrdenTrabajoIf) getCmbOrdenTrabajo().getModel().getSelectedItem();
			// Habilito el Combo de Orden Trabajo Detalle
			getCmbOrdenTrabajoDetalle().setEnabled(true);
			// Remuevo los items del mismo combo
			getCmbOrdenTrabajoDetalle().removeAllItems();

			try {
				// Seteo como Tema el Nombre de la Campaña
				if (getMode() == SpiritMode.UPDATE_MODE && presupuesto.getTemaCampana() != null) {
					getTxtTemaCampana().setText(presupuesto.getTemaCampana());
				} else {
					getTxtTemaCampana().setText(SessionServiceLocator.getCampanaSessionService()
						.getCampana(ordenTrabajoIf.getCampanaId()).getNombre());
				}

				// Cargo los subtipos de acuerdo al tipo y orden de trabajo
				// escogido
				Collection<OrdenTrabajoDetalleIf> ordenTrabajoDetalleCollection = SessionServiceLocator
						.getOrdenTrabajoDetalleSessionService()
						.findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajoAndEstado(
								tipoOrdenIf.getId(), ordenTrabajoIf.getId());
				//Iterator itOrdenTrabajoDetalleCollection = ordenTrabajoDetalleCollection.iterator();
				//while (itOrdenTrabajoDetalleCollection.hasNext()) {
				for ( OrdenTrabajoDetalleIf ordenTrabajoDetalleTemp : ordenTrabajoDetalleCollection ){
					//OrdenTrabajoDetalleIf ordenTrabajoDetalleTemp = (OrdenTrabajoDetalleIf) itOrdenTrabajoDetalleCollection
					//		.next();
					InfoOrdenTrabajoDetalle info = crearInfoOrdenTrabajo(ordenTrabajoDetalleTemp);

					getCmbOrdenTrabajoDetalle().addItem(info);
					// getCmbOrdenTrabajoDetalle().addItem(ordenTrabajoDetalleTemp);
				}

			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
			}
		}
	}
	
	private InfoOrdenTrabajoDetalle crearInfoOrdenTrabajo(OrdenTrabajoDetalleIf ordenTrabajoDetalleTemp) throws GenericBusinessException{
		SubtipoOrdenIf subtipoOrden = mapaSubTiposOrden.get(ordenTrabajoDetalleTemp.getSubtipoId());
		EmpleadoIf empleado = mapaEmpleados.get(ordenTrabajoDetalleTemp.getAsignadoaId());

		InfoOrdenTrabajoDetalle info = new InfoOrdenTrabajoDetalle(ordenTrabajoDetalleTemp, 
			subtipoOrden.getNombre()+ " - "+ empleado.getNombres().split(" ")[0] + " "
			+ empleado.getApellidos().split(" ")[0]);
		return info;
	}

	MouseListener oMouseAdapterTblPresupuestoDetalle = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblPresupuestoDetalle().getModel().getRowCount() > 0) {
				popupMenuPresupuestoDetalle.show(evt.getComponent(),evt.getX(), evt.getY());
			} else if (getTblPresupuestoDetalle().getSelectedRow() != -1) {
				//int filaSeleccionada = getTblPresupuestoDetalle().getSelectedRow();
				int filaSeleccionada = getTblPresupuestoDetalle().convertRowIndexToModel(getTblPresupuestoDetalle().getSelectedRow());
				
				PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf) presupuestoDetalleColeccion.get(filaSeleccionada);
				setPresupuestoDetalle(presupuestoDetalleIf);
				validarSeleccionDeDetalle(filaSeleccionada,	presupuestoDetalleIf);
			}
		}		
	};
	
	private void validarSeleccionDeDetalle(int filaSeleccionada,
			PresupuestoDetalleIf presupuestoDetalleIf) {
		filaSeleccionada = getTblPresupuestoDetalle().convertRowIndexToView(filaSeleccionada);
		boolean seleccionado = (Boolean)getTblPresupuestoDetalle().getValueAt(filaSeleccionada, 0);
		try {
			if ( seleccionado ){
				Long idOrden = presupuestoDetalleIf.getOrdenCompraId();
				if ( idOrden != null ){

					OrdenCompraIf oc = mapaOrdenesCompra.get(idOrden);
					if ( oc == null ){
						SpiritAlert.createAlert("Orden de Detalle no existe !!", SpiritAlert.ERROR);
						presupuestoDetalleIf.setOrdenCompraId(null);
					} else {
						boolean ordenCompraActualizable = ordenCompraEsActualizable(oc);
						if ( ordenCompraActualizable ){
							ordenesCompraParaModificar.add(presupuestoDetalleIf.getOrden());
						} else {
							getTblPresupuestoDetalle().setValueAt(false, filaSeleccionada, 0);
							SpiritAlert.createAlert("Este detalle ya tiene Compra Ingresada, no es posible modificarlo.", SpiritAlert.WARNING);
						}
					}
				}	
			} else {
				if(ordenesCompraParaModificar.contains(presupuestoDetalleIf.getOrden())){
					ordenesCompraParaModificar.remove(presupuestoDetalleIf.getOrden());
				}
				
				/*DefaultTableModel modelo = (DefaultTableModel) getTblPresupuestoDetalle().getModel();
				Set<Integer> listaActualizar = new HashSet<Integer>();
				for (int i = 0; i < modelo.getRowCount() ; i++){
					boolean seleccionadoTmp = (Boolean) getTblPresupuestoDetalle().getValueAt(i, 0);
					if ( seleccionadoTmp ){
						PresupuestoDetalleIf pd = presupuestoDetalleColeccion.get(i);
						listaActualizar.add(pd.getOrden());
					}
				}*/
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Orden de Detalle no existe !!", SpiritAlert.ERROR);
		}
	}
	
	private boolean ordenCompraEsActualizable( OrdenCompraIf oc ) throws GenericBusinessException{
		
		if(oc.getEstado().equals(EstadoOrdenCompra.INGRESADA.getLetra())){
			return false;
		}
		
		return true;
	}
	
	MouseListener oMouseAdapterTblPresupuestoDetalleP = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblPresupuestoDetalleP().getModel().getRowCount() > 0) {
				popupMenuPresupuestoDetalle.show(evt.getComponent(),evt.getX(), evt.getY());
			} else if (getTblPresupuestoDetalleP().getSelectedRow() != -1) {
				PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf) presupuestoDetalleColeccionP
						.get(((JTable) evt.getSource()).getSelectedRow());
				setPresupuestoDetalleP(presupuestoDetalleIf);
			}
		}
	};

	private void setPresupuestoDetalle(PresupuestoDetalleIf presupuestoDetalleIf) {
		try {
			if (presupuestoDetalleIf.getProductoId() != null) {
				productoIf = GeneralUtil.verificarMapaProducto(mapaProductos, 
					presupuestoDetalleIf.getProductoId());
				
				proveedorIf = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficinas, 
					productoIf.getProveedorId());
				
				//ClienteIf cliente = GeneralUtil.verificarMapaCliente(mapaClientes,proveedorIf.getClienteId());
				
				CiudadIf ciudad = mapaCiudades.get(proveedorIf.getCiudadId());
				getTxtProveedor().setText(ciudad.getCodigo() + " - " + proveedorIf.getDescripcion());
				
				GenericoIf generico = GeneralUtil.verificarMapaGenerico(mapaGenericos,productoIf.getGenericoId());
				getTxtProducto().setText(generico.getNombre());
			}

			getTxtConceptoPresupuestoDetalle().setText(presupuestoDetalleIf.getConcepto());
			getTxtCantidad().setText(String.valueOf(Double.valueOf(presupuestoDetalleIf.getCantidad().toString()).intValue()));
			getTxtPrecioCompra().setText(String.valueOf(presupuestoDetalleIf.getPrecioagencia().doubleValue()));
			getTxtPrecioVenta().setText(String.valueOf(presupuestoDetalleIf.getPrecioventa().doubleValue()));
			
			double descuentoEspecialCompra = presupuestoDetalleIf.getPorcentajeDescuentoEspecialCompra()!=null?presupuestoDetalleIf.getPorcentajeDescuentoEspecialCompra().doubleValue():0D;
			double descuentoEspecialVenta= presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta()!=null?presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta().doubleValue():0D;
			getTxtPorcentajeDescuentoEspecialCompra().setText(formatoDecimal.format(descuentoEspecialCompra));
			getTxtPorcentajeDescuentoEspecialVenta().setText(formatoDecimal.format(descuentoEspecialVenta));
			
			getTxtPorcentajeDsctoAgenciaCompra().setText(String.valueOf(Double.valueOf(presupuestoDetalleIf.getPorcentajeDescuentoAgenciaCompra().toString())));
			getTxtPorcentajeDsctoAgenciaVenta().setText(String.valueOf(Double.valueOf(presupuestoDetalleIf.getPorcentajeDescuentoAgenciaVenta().toString())));
			
			getTxtPorcentajeDescuentosVariosCompra().setText(String.valueOf(Double.valueOf(presupuestoDetalleIf.getPorcentajeDescuentosVariosCompra().toString())));
			getTxtPorcentajeDescuentosVariosVenta().setText(String.valueOf(Double.valueOf(presupuestoDetalleIf.getPorcentajeDescuentosVariosVenta().toString())));
			
			if(presupuestoDetalleIf.getPorcentajeNotaCreditoCompra() != null){
				getTxtPorcentajeNotaCredito().setText(String.valueOf(
						Double.valueOf(presupuestoDetalleIf.getPorcentajeNotaCreditoCompra().toString())));
			}else{
				getTxtPorcentajeNotaCredito().setText("0");
			}
			
			if(presupuestoDetalleIf.getPorcentajeNegociacionDirecta() != null){
				getTxtPorcentajeNegociacionDirecta().setText(String.valueOf(
						Double.valueOf(presupuestoDetalleIf.getPorcentajeNegociacionDirecta().toString())));
			}else{
				getTxtPorcentajeNegociacionDirecta().setText("0");
			}
			
			if(presupuestoDetalleIf.getPorcentajeComisionPura() != null){
				getTxtPorcentajeComisionPura().setText(String.valueOf(Double.valueOf(presupuestoDetalleIf.getPorcentajeComisionPura().toString())));
			}else{
				getTxtPorcentajeComisionPura().setText("0");
			}
			
			if(presupuestoDetalleIf.getPorcentajeComisionAdicional() != null){
				getTxtPorcentajeComisionAdicional().setText(String.valueOf(Double.valueOf(presupuestoDetalleIf.getPorcentajeComisionAdicional().toString())));
			}else{
				getTxtPorcentajeComisionAdicional().setText("0");
			}	
			
			if(presupuestoDetalleIf.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(0)) == 1){
				getCbNegociacionDirecta().setSelected(true);
				getTxtPorcentajeNegociacionDirecta().setEditable(true);
			}else{
				getCbNegociacionDirecta().setSelected(false);
				getTxtPorcentajeNegociacionDirecta().setEditable(false);
			}
			
			if(presupuestoDetalleIf.getPorcentajeComisionPura().compareTo(new BigDecimal(0)) == 1){
				getCbComisionPura().setSelected(true);
				getTxtPorcentajeComisionPura().setEditable(true);
			}else{
				getCbComisionPura().setSelected(false);
				getTxtPorcentajeComisionPura().setEditable(false);
			}
			
			if(presupuestoDetalleIf.getPorcentajeComisionAdicional().compareTo(new BigDecimal(0)) == 1){
				getCbComisionAdicional().setSelected(true);
				getTxtPorcentajeComisionAdicional().setEditable(true);
			}else{
				getCbComisionAdicional().setSelected(false);
				getTxtPorcentajeComisionAdicional().setEditable(false);
			}
			
			getBtnBuscarProducto().setEnabled(true);			
			
			Integer orden = buscarOrdenPorProveedor(presupuestoDetalleIf);
			getTxtOrden().setText(orden.toString());
			
			if(presupuestoDetalleIf.getFechaPublicacion() != null){
				Calendar calendarPublicacion = new GregorianCalendar();
				calendarPublicacion.setTime(presupuestoDetalleIf.getFechaPublicacion());
				getCmbFechaPublicacion().setCalendar(calendarPublicacion);
			}else{
				getCmbFechaPublicacion().setCalendar(null);
			}
			
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private Integer buscarOrdenPorProveedor(PresupuestoDetalleIf presupuestoDetalleIf) throws GenericBusinessException {
		Integer orden = presupuestoDetalleIf.getOrden();
		if ( orden == null || orden == 0 ){
			for ( PresupuestoDetalleIf pd : presupuestoDetalleColeccion ){
				//SI existe algun detalle del mismo proveedor y no es Medios, se le da 
				//el mismo numero de orden
				TipoOrdenIf tipoOrden = (TipoOrdenIf) getCmbTipoOrden().getSelectedItem();				
				if (pd.getProveedorId().equals(presupuestoDetalleIf.getProveedorId()) &&
					pd.getOrden() != null && !tipoOrden.getTipo().equals(TIPO_MEDIOS)){
					orden = pd.getOrden();
				}
			}
		}
		if ( orden == null || orden == 0 )
			orden = getUltimoOrden()+1;
		return orden;
	}
	
	private void confirmarActualizacionOrdenesCompra() throws GenericBusinessException{
		
		Map<Integer,Collection<PresupuestoDetalleIf>> mapaOrdenPresupuestos = 
			new HashMap<Integer, Collection<PresupuestoDetalleIf>>();
		Map<Integer,Long> mapaOrdenOrdenCompra = new HashMap<Integer,Long>();
		
		for ( PresupuestoDetalleIf pd : presupuestoDetalleColeccion ){
			Integer orden = pd.getOrden();
			Collection<PresupuestoDetalleIf> lista = mapaOrdenPresupuestos.get(orden);
			if ( lista == null ){
				lista = new ArrayList<PresupuestoDetalleIf>();
			}
			lista.add(pd);
			mapaOrdenPresupuestos.put(orden, lista);
			
			if ( !mapaOrdenOrdenCompra.containsKey(orden) ){
				mapaOrdenOrdenCompra.put(orden, null);
			}
			
			Long idOrdenCompra = mapaOrdenOrdenCompra.get(orden);
			if ( idOrdenCompra == null ){
				mapaOrdenOrdenCompra.put(orden, pd.getOrdenCompraId());
			}
		}
		
		Map<Long,OrdenCompraIf> mapaOrdenCompraTmp = new HashMap<Long, OrdenCompraIf>();
		for (Integer orden : mapaOrdenPresupuestos.keySet()){
			
			Collection<PresupuestoDetalleIf> lista = mapaOrdenPresupuestos.get(orden);
			for ( PresupuestoDetalleIf pd : lista ){
				
				Long idOrdenCompra = mapaOrdenOrdenCompra.get(orden);
				if ( pd.getOrdenCompraId() == null &&
					 idOrdenCompra != null ){
					OrdenCompraIf oc = GeneralUtil.verificarMapaOrdenCompra(mapaOrdenCompraTmp, idOrdenCompra);
					if ( ordenCompraEsActualizable(oc) ){
						int opcion1 = JOptionPane.showOptionDialog(null,
								"¿Desea actualizar la Orden de Compra con codigo "+oc.getCodigo()+"-"+oc.getRevision()+" ?", "Confirmación",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
								null, options, no);
						if (opcion1 == JOptionPane.YES_OPTION) {
							ordenesCompraParaModificar.add(orden);
						}
					}
				}
			}
		}
	}

	private void setPresupuestoDetalleP(
			PresupuestoDetalleIf presupuestoDetalleIfP) {
		try {
			// Para el reporte
			if (presupuestoDetalleIfP.getProductoId() != null) {
				//productoIfP = SessionServiceLocator.getProductoSessionService()
				//	.getProducto(presupuestoDetalleIfP.getProductoId());
				productoIfP = GeneralUtil.verificarMapaProducto(mapaProductos, presupuestoDetalleIfP.getProductoId());
				//proveedorIfP = SessionServiceLocator.getClienteOficinaSessionService()
				//	.getClienteOficina(productoIfP.getProveedorId());
				proveedorIfP = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficinas, productoIfP.getProveedorId());
				//ClienteIf cliente = (ClienteIf) SessionServiceLocator.getClienteSessionService()
				//		.findClienteByClienteOficinaId(proveedorIfP.getId()).iterator().next();
				ClienteIf cliente = GeneralUtil.verificarMapaCliente(mapaClientes, proveedorIfP.getClienteId());
				
				//GenericoIf generico = SessionServiceLocator.getGenericoSessionService()
				//	.getGenerico(productoIfP.getGenericoId());
				GenericoIf generico = GeneralUtil.verificarMapaGenerico(mapaGenericos, productoIfP.getGenericoId());

				getTxtProveedorP().setText(proveedorIfP.getCodigo()+ " - "+cliente.getNombreLegal());
				getTxtProductoP().setText(generico.getNombre());
			}

			getTxtConceptoPresupuestoDetalleP().setText(
				presupuestoDetalleIfP.getConcepto());
			getTxtCantidadP().setText(String.valueOf(Double.valueOf(
				presupuestoDetalleIfP.getCantidad().toString()).intValue()));
			getTxtPrecioVentaP().setText(String.valueOf(
				presupuestoDetalleIfP.getPrecioventa().doubleValue()));

		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	Comparator<PresupuestoDetalleIf> ordenadorPresupuestoDetallePorId = new Comparator<PresupuestoDetalleIf>() {
		public int compare(PresupuestoDetalleIf o1, PresupuestoDetalleIf o2) {
			return o1.getId().compareTo(o2.getId());
		}
	};

	public void getSelectedObject(PresupuestoIf presupuesto) {
		
		setUpdateMode();
		this.presupuesto = presupuesto;
		
		try {
			OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService()
				.getOrdenTrabajoDetalle(presupuesto.getOrdentrabajodetId());
			ordenTrabajoIf = SessionServiceLocator.getOrdenTrabajoSessionService()
				.getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
			SubtipoOrdenIf subtipoOrden = mapaSubTiposOrden.get(ordenTrabajoDetalle.getSubtipoId());
			tipoOrdenIf = mapaTiposOrden.get(subtipoOrden.getTipoordenId());
			clienteOficinaIf = mapaClienteOficinas.get(ordenTrabajoIf.getClienteoficinaId());
			clienteIf = mapaClientes.get(clienteOficinaIf.getClienteId());
			corporacionIf = SessionServiceLocator.getCorporacionSessionService()
				.getCorporacion(clienteIf.getCorporacionId());
			
			//presupuesto de referencia
			referenciaId = presupuesto.getReferenciaId();
			tipoReferencia = presupuesto.getTipoReferencia();
			if(referenciaId != null && tipoReferencia != null){
				if(tipoReferencia.equals("P")){
					PresupuestoIf referenciaPresupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuesto.getReferenciaId());
					if(referenciaPresupuestoIf != null){
						getTxtReferencia().setText(referenciaPresupuestoIf.getCodigo());
						getCmbTipoReferencia().setSelectedItem("PRESUPUESTO");
						getCmbTipoReferencia().repaint();
					}
				}else if(tipoReferencia.equals("M")){
					PlanMedioIf referenciaPlanMedioIf = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(presupuesto.getReferenciaId());
					if(referenciaPlanMedioIf != null){
						getTxtReferencia().setText(referenciaPlanMedioIf.getCodigo());
						getCmbTipoReferencia().setSelectedItem("PLAN DE MEDIOS");
						getCmbTipoReferencia().repaint();
					}
				}				
			}else{
				getTxtReferencia().setText("");
				getCmbTipoReferencia().setSelectedItem("NINGUNO");
				getCmbTipoReferencia().repaint();
			}
			
			if (presupuesto.getClienteCondicionId() != null)
				clienteCondicion = SessionServiceLocator.getClienteCondicionSessionService()
						.getClienteCondicion(presupuesto.getClienteCondicionId());
			// mando habilitar el boton de buscar productos
			getBtnBuscarProducto().setEnabled(true);
			getTxtCodigo().setText(presupuesto.getCodigo());
			getTxtConceptoPresupuesto().setText(presupuesto.getConcepto());
			getTxtOficina().setText(clienteOficinaIf.getCodigo() + " - "
							+ clienteOficinaIf.getDescripcion());
			getTxtCliente().setText(clienteIf.getIdentificacion() + " - "
							+ clienteIf.getNombreLegal());
			getTxtCorporacion().setText(corporacionIf.getCodigo() + " - "
							+ corporacionIf.getNombre());
			getTxtDiasValidez().setText(String.valueOf(presupuesto.getDiasValidez()));
			
			//SAP
			if(presupuesto.getAutorizacionSap() != null){
				getTxtAutorizacionSAP().setText(presupuesto.getAutorizacionSap());
			}

			if (presupuesto.getIva().compareTo(new BigDecimal(0)) == 0) {
				getCbSinIVA().setSelected(true);
			} else {
				getCbSinIVA().setSelected(false);
			}
			
			if (presupuesto.getPrepago() != null && presupuesto.getPrepago().equals("S")) {
				getCbPrepago().setSelected(true);
			} else {
				getCbPrepago().setSelected(false);
			}

			getCmbTipoOrden().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoOrden(),tipoOrdenIf.getId()));
			
			cargarComboOrdenTrabajo();
			//Debo volver a buscar el ordenTrabajoIf porque al refrescar el combo se cambia.
			ordenTrabajoIf = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
			getCmbOrdenTrabajo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbOrdenTrabajo(), ordenTrabajoIf.getId()));
			getCmbOrdenTrabajo().repaint();
			
			
			// cargo los subtipos de acuerdo al tipo y orden de trabajo escogido
			InfoOrdenTrabajoDetalle infoSeleccionada = new InfoOrdenTrabajoDetalle(ordenTD, "");
			getCmbOrdenTrabajoDetalle().removeAllItems();
			Collection<OrdenTrabajoDetalleIf> ordenTrabajoDetalleCollection = SessionServiceLocator
					.getOrdenTrabajoDetalleSessionService()
					.findOrdenTrabajoDetalleByOrdenId(ordenTrabajoIf.getId());
			
			for (OrdenTrabajoDetalleIf ordenTrabajoDetalleIf : ordenTrabajoDetalleCollection ) {
				SubtipoOrdenIf subtipoOrdenIf = mapaSubTiposOrden.get(ordenTrabajoDetalleIf.getSubtipoId());
				EmpleadoIf empleado = mapaEmpleados.get(ordenTrabajoDetalleIf.getAsignadoaId());
				InfoOrdenTrabajoDetalle info = new InfoOrdenTrabajoDetalle(
					ordenTrabajoDetalleIf, subtipoOrdenIf.getNombre()
							+ " - "
							+ empleado.getNombres().split(" ")[0] + " "
							+ empleado.getApellidos().split(" ")[0]);

				getCmbOrdenTrabajoDetalle().addItem(info);

				if (subtipoOrdenIf.getId().compareTo(subtipoOrden.getId()) == 0) {
					ordenTD = ordenTrabajoDetalleIf;

					SubtipoOrdenIf subtipoOrdenSeleccionada = mapaSubTiposOrden.get(ordenTD.getSubtipoId());
					EmpleadoIf empleadoSeleccionado = mapaEmpleados.get(ordenTD.getAsignadoaId());

					infoSeleccionada = new InfoOrdenTrabajoDetalle(
					ordenTrabajoDetalleIf, subtipoOrdenSeleccionada.getNombre()
					+ " - "+ empleadoSeleccionado.getNombres().split(" ")[0]
					+ " "+ empleadoSeleccionado.getApellidos().split(" ")[0]);

					getTxtSubTipoOrden().setText(subtipoOrdenIf.getNombre());
					getTxtDescripcionOTdetalle().setText(ordenTrabajoDetalleIf.getDescripcion());
				}
			}
			
			getCmbOrdenTrabajoDetalle().setSelectedItem(infoSeleccionada);

			getCmbFormaPago().setSelectedIndex(
					ComboBoxComponent.getIndexToSelectItem(getCmbFormaPago(),
							presupuesto.getFormaPagoId()));
			getCmbFormaPago().validate();
			getCmbFormaPago().repaint();

			getCmbFechaCreacion().setDate(presupuesto.getFechaCreacion());
			getCmbFechaPresupuesto().setDate(presupuesto.getFecha());
			getCmbFechaAprobacion().setDate(presupuesto.getFechaAceptacion());
			
			String estadoLetra = presupuesto.getEstado();
			EstadoPresupuesto estado = EstadoPresupuesto.getEstadoPresupuestoByLetra(estadoLetra);
			getCmbEstado().setSelectedItem(estado);
			
			getTxtModificacion().setText(presupuesto.getModificacion().toString());
			getTxtCabecera().setText(presupuesto.getCabecera());
			getTxtSubTotalVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(presupuesto.getValorbruto().doubleValue())));
			getTxtDsctoAgenciaVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(presupuesto.getDescuento().doubleValue())));
			getTxtIvaVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(presupuesto.getIva().doubleValue())));
			getTxtTotalVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(presupuesto.getValor().doubleValue())));
			contModif = presupuesto.getModificacion();
			subTotal = presupuesto.getValorbruto().doubleValue();
			descuentoTotal = presupuesto.getDescuento().doubleValue();
			ivaTotal = presupuesto.getIva().doubleValue();
			
			porcentajeComisionAgencia = presupuesto.getPorcentajeComisionAgencia().doubleValue();
			comisionAgencia = ((subTotal - descuentoTotal) * porcentajeComisionAgencia) / 100D;
			getTxtPorcentajeComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeComisionAgencia)));
			getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgencia)));
			
			total = presupuesto.getValor().doubleValue();

			// Cargo los productos clientes pertencientes al presupuesto
			cargarListaProductos();
			Collection<PresupuestoProductoIf> listaOrdenProducto = SessionServiceLocator
				.getPresupuestoProductoSessionService().findPresupuestoProductoByPresupuestoId(presupuesto.getId());
			for (PresupuestoProductoIf ordenTrabajoProductoIf : listaOrdenProducto) {
				
				productoClienteIf = SessionServiceLocator.getProductoClienteSessionService()
					.getProductoCliente(ordenTrabajoProductoIf.getProductoClienteId());

				// Agrego el producto cliente cargado a la coleccion
				productoClienteList.add(productoClienteIf);
			}
			for (int i = 0; i < getCbListProductos().getModel().getSize(); i++) {
				ProductoClienteIf productoCliente = (ProductoClienteIf) getCbListProductos()
						.getModel().getElementAt(i);
				for (int j = 0; j < productoClienteList.size(); j++) {
					ProductoClienteIf productoClienteUpdate = productoClienteList.get(j);
					if (productoCliente.getId().compareTo(productoClienteUpdate.getId()) == 0) {
						getCbListProductos().getCheckBoxListSelectionModel().addSelectionInterval(i, i);
					}
				}
			}
			
			//cargar mapa de ordenes de compra del presupuesto
			if(presupuesto != null){
				mapaOrdenesCompra.clear();
				Collection ordenesCompraPorPresupuesto = SessionServiceLocator.getOrdenCompraSessionService().findOrdenCompraByPresupuestoId(presupuesto.getId());
				Iterator ordenesCompraPorPresupuestoIt = ordenesCompraPorPresupuesto.iterator();
				while(ordenesCompraPorPresupuestoIt.hasNext()){
					OrdenCompraIf ordenCompra = (OrdenCompraIf)ordenesCompraPorPresupuestoIt.next();
					mapaOrdenesCompra.put(ordenCompra.getId(), ordenCompra);
				}
			}

			// Presupuesto Detalle
			modelPresupuestoDetalle = (DefaultTableModel) getTblPresupuestoDetalle().getModel();
			modelPresupuestoDetalleP = (DefaultTableModel) getTblPresupuestoDetalleP().getModel();
			Collection<PresupuestoDetalleIf> listaPlantillaDetalle = SessionServiceLocator
					.getPresupuestoDetalleSessionService()
					.findPresupuestoDetalleByPresupuestoId(presupuesto.getId());

			Collections.sort((ArrayList) listaPlantillaDetalle,ordenadorPresupuestoDetallePorId);

			int contador = 0;
			
			for (PresupuestoDetalleIf pd : listaPlantillaDetalle ) {
				//presupuestoDetalleIf = pd;
				Double subTotalTemp = 0.00;

				if (pd.getReporte() == null) {
					PresupuestoDetalleIf presupuestoDetalleReporteN = (PresupuestoDetalleIf) ObjectCloner
							.deepCopy(pd);
					presupuestoDetalleReporteN.setReporte(REPORTE_NO);

					Vector<Object> filaPlantillaDetalle = new Vector<Object>();
					filaPlantillaDetalle.add(false);
					presupuestoDetalleColeccion.add(presupuestoDetalleReporteN);
					filaPlantillaDetalle
							.add(/* producto.getCodigo() + " - " + */pd
									.getConcepto());
					filaPlantillaDetalle.add(String.valueOf(Double.valueOf(
							pd.getCantidad().toString())
							.intValue()));
					filaPlantillaDetalle.add(String
							.valueOf(pd.getPrecioagencia().doubleValue()));
					filaPlantillaDetalle.add(String
							.valueOf(pd.getPrecioventa().doubleValue()));
					filaPlantillaDetalle.add(String.valueOf(Double.valueOf(
							pd.getPorcentajeDescuentoAgenciaVenta().toString()).intValue())+ " % ");
					subTotalTemp = pd.getPrecioagencia().doubleValue()
							* pd.getCantidad().doubleValue();
					valorBruto = valorBruto + subTotalTemp;
					descuentoCompraTotal = descuentoCompraTotal	+ pd.getPorcentajeDescuentoAgenciaCompra().doubleValue();
					ivaTotalAgencia = (valorBruto) * IVA;
					totalAgencia = valorBruto + ivaTotalAgencia;
					getTxtSubTotalCompra().setText(	formatoDecimal.format(Utilitarios.redondeoValor(valorBruto)));
					getTxtDsctoAgenciaCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoCompraTotal)));
					
					bonificacionCompraTotal = bonificacionCompraTotal + ((valorBruto - descuentoCompraTotal) * pd.getPorcentajeDescuentosVariosCompra().doubleValue());
					getTxtDescuentosVariosCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(bonificacionCompraTotal)));
					
					getTxtIvaTotalCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotalAgencia)));
					getTxtTotalCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalAgencia)));
					filaPlantillaDetalle.add(pd.getOrden());
					
					OrdenCompraIf oc = null;
					if(pd.getOrdenCompraId() != null){
						oc = mapaOrdenesCompra.get(pd.getOrdenCompraId());
						if(oc == null){
							oc = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(pd.getOrdenCompraId());
						}
					}
					
					filaPlantillaDetalle.add(oc != null?oc.getCodigo()+"-"+oc.getRevision():"");					
					
					modelPresupuestoDetalle.addRow(filaPlantillaDetalle);

					PresupuestoDetalleIf presupuestoDetalleReporteS = (PresupuestoDetalleIf) ObjectCloner
							.deepCopy(pd);
					presupuestoDetalleReporteS.setId(null);
					presupuestoDetalleReporteS.setPorcentajeDescuentoAgenciaVenta(new BigDecimal(0));
					presupuestoDetalleReporteS.setPorcentajeDescuentoAgenciaCompra(new BigDecimal(0));
					presupuestoDetalleReporteS.setIva(null);
					presupuestoDetalleReporteS.setIvaCompra(null);
					presupuestoDetalleReporteS.setPrecioagencia(null);
					presupuestoDetalleReporteS.setProveedorId(null);
					presupuestoDetalleReporteS.setReporte(REPORTE_SI);
					Vector<String> filaPlantillaDetalleP = new Vector<String>();
					presupuestoDetalleColeccionP.add(presupuestoDetalleReporteS);
					filaPlantillaDetalleP.add(pd.getConcepto());
					filaPlantillaDetalleP.add(String.valueOf(Double.valueOf(
							pd.getCantidad().toString()).intValue()));
					filaPlantillaDetalleP.add("");
					filaPlantillaDetalleP.add(String
							.valueOf(pd.getPrecioventa().doubleValue()));
					modelPresupuestoDetalleP.addRow(filaPlantillaDetalleP);

				} else if (pd.getReporte().equals(REPORTE_NO)) {
					
					Vector<Object> filaPlantillaDetalle = new Vector<Object>();
					presupuestoDetalleColeccion.add(pd);
					filaPlantillaDetalle.add(false);
					filaPlantillaDetalle.add(pd.getConcepto());
					filaPlantillaDetalle.add(String.valueOf(Double.valueOf(pd.getCantidad().toString()).intValue()));
					filaPlantillaDetalle.add(String.valueOf(pd.getPrecioagencia().doubleValue()));
					filaPlantillaDetalle.add(String.valueOf(pd.getPrecioventa().doubleValue()));
					filaPlantillaDetalle.add(String.valueOf(Double.valueOf(pd.getPorcentajeDescuentoAgenciaVenta().toString()).intValue())+ " % ");
					subTotalTemp = pd.getPrecioagencia().doubleValue()* pd.getCantidad().doubleValue();
					valorBruto = valorBruto + subTotalTemp;
					double descuentoEspecialCompra = pd.getPorcentajeDescuentoEspecialCompra()!=null?pd.getPorcentajeDescuentoEspecialCompra().doubleValue():0D;
					descuentoEspecialTotalCompra = descuentoEspecialTotalCompra + descuentoEspecialCompra;
					descuentoCompraTotal = descuentoCompraTotal + pd.getPorcentajeDescuentoAgenciaCompra().doubleValue();
					ivaTotalAgencia = (valorBruto) * IVA;
					totalAgencia = valorBruto + ivaTotalAgencia;
					getTxtSubTotalCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(valorBruto)));
					getTxtDsctoAgenciaCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoCompraTotal)));
					getTxtDescuentoEspecialTotalCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoEspecialTotalCompra)));
					getTxtDescuentoEspecialTotalVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoEspecialTotalVenta)));
					
					bonificacionCompraTotal = bonificacionCompraTotal + ((valorBruto - descuentoEspecialTotalCompra) * pd.getPorcentajeDescuentosVariosCompra().doubleValue());
					
					getTxtDescuentosVariosCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(bonificacionCompraTotal)));
										
					getTxtIvaTotalCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotalAgencia)));
					getTxtTotalCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalAgencia)));
					getTxtOrden().setText("1");
					modelPresupuestoDetalle.addRow(filaPlantillaDetalle);

				} else if (pd.getReporte().equals(REPORTE_SI)) {
					Vector<String> filaPlantillaDetalleP = new Vector<String>();
					presupuestoDetalleColeccionP.add(pd);
					filaPlantillaDetalleP.add(pd.getConcepto());
					filaPlantillaDetalleP.add(String.valueOf(Double.valueOf(
							pd.getCantidad().toString()).intValue()));
					filaPlantillaDetalleP.add("");
					filaPlantillaDetalleP.add(String
							.valueOf(pd.getPrecioventa().doubleValue()));
					modelPresupuestoDetalleP.addRow(filaPlantillaDetalleP);
				}
				contador++;
			}
			
			// Cargo los archivos
			Collection<PresupuestoArchivoIf> presupuestoArchivoColeccion = SessionServiceLocator
					.getPresupuestoArchivoSessionService()
					.findPresupuestoArchivoByPresupuestoId(presupuesto.getId());
			
			modelPresupuestoArchivo = (DefaultTableModel) getTblArchivo().getModel();

			for (PresupuestoArchivoIf presupuestoArchivoIf : presupuestoArchivoColeccion) {
				Vector<String> filaArchivo = new Vector<String>();

				archivosPresupuestoColeccion.add(presupuestoArchivoIf);
				archivosColeccion.add(null);

				TipoArchivoIf tipoArchivo = (TipoArchivoIf) SessionServiceLocator
						.getTipoArchivoSessionService().getTipoArchivo(
								presupuestoArchivoIf.getTipoArchivoId());
				filaArchivo.add(tipoArchivo.getNombre());

				if (presupuestoArchivoIf.getUrl() != null)
					filaArchivo.add(presupuestoArchivoIf.getUrl());
				else
					filaArchivo.add("");

				modelPresupuestoArchivo.addRow(filaArchivo);
			}
			
			getTxtOrden().setText("");
			getCmbFechaPublicacion().setCalendar(null);
			actualizarTotales(true);
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.showUpdateMode();
	}
	
	private OrdenCompraIf verificarOrdenCompraById(Map<Long,OrdenCompraIf> mapa,Long idOrdenCompra) throws GenericBusinessException{
		OrdenCompraIf p = null;
		if (  idOrdenCompra != null ){
			p = mapa.get(idOrdenCompra);
			if ( p == null ){
				p = SessionServiceLocator.getOrdenCompraSessionService()
					.getOrdenCompra(idOrdenCompra);
				if (p != null)
					mapa.put(p.getId(), p);
			}
		}
		return p;
	}

	public void agregarDetalle() {
		try {
			modelPresupuestoDetalle = (DefaultTableModel) getTblPresupuestoDetalle().getModel();
			modelPresupuestoDetalleP = (DefaultTableModel) getTblPresupuestoDetalleP().getModel();
			Vector<Object> filaDetalle = new Vector<Object>();
			Vector<String> filaDetalleP = new Vector<String>();
			
			double porcentajeBonificacionCompra = 0;
			if(getTxtPorcentajeDescuentosVariosCompra().getText() != null && !getTxtPorcentajeDescuentosVariosCompra().getText().equals("")){
				porcentajeBonificacionCompra = Double.parseDouble(getTxtPorcentajeDescuentosVariosCompra().getText().replaceAll(",", ""));
			}
			
			double porcentajeDescuentosVariosVenta = 0;
			if(getTxtPorcentajeDescuentosVariosVenta().getText() != null && !getTxtPorcentajeDescuentosVariosVenta().getText().equals("")){
				porcentajeDescuentosVariosVenta = Double.parseDouble(getTxtPorcentajeDescuentosVariosVenta().getText().replaceAll(",", ""));
			}
			
			double porcentajeComisionAgencia = 0;
			if(getTxtPorcentajeComision().getText() != null && !getTxtPorcentajeComision().getText().equals("")){
				porcentajeComisionAgencia = Double.parseDouble(getTxtPorcentajeComision().getText().replaceAll(",", ""));
			}

			PresupuestoDetalleData data = new PresupuestoDetalleData();
			PresupuestoDetalleData dataP = new PresupuestoDetalleData();
			
			// el iva de cada detalle lo inicializo con cero cada vez que se agrega un detalle
			
			Double subTotalCompraTemp = Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPrecioCompra().getText())) * Double.parseDouble(this.getTxtCantidad().getText());
			Double descuentoEspecialCompraTemp = subTotalCompraTemp * (Double.parseDouble(this.getTxtPorcentajeDescuentoEspecialCompra().getText().replaceAll(",", "")) / 100);
			Double descuentoCompraTemp = (subTotalCompraTemp - descuentoEspecialCompraTemp) * (Double.parseDouble(this.getTxtPorcentajeDsctoAgenciaCompra().getText().replaceAll(",", "")) / 100);
			Double bonificacionCompraTemp = (subTotalCompraTemp - descuentoEspecialCompraTemp) * (porcentajeBonificacionCompra / 100);
			
			Double subTotalVentaTemp = Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPrecioVenta().getText())) * Double.parseDouble(this.getTxtCantidad().getText());
			Double descuentoEspecialVentaTemp = subTotalVentaTemp * (Double.parseDouble(this.getTxtPorcentajeDescuentoEspecialVenta().getText().replaceAll(",", "")) / 100);
			Double descuentoVentaTemp = (subTotalVentaTemp - descuentoEspecialVentaTemp) * (Double.parseDouble(this.getTxtPorcentajeDsctoAgenciaVenta().getText().replaceAll(",", "")) / 100);
			Double descuentosVariosVentaTemp = (subTotalVentaTemp - descuentoEspecialVentaTemp) * (porcentajeDescuentosVariosVenta / 100);
			Double comisionAgenciaTemp = (subTotalVentaTemp - descuentoEspecialVentaTemp - descuentoVentaTemp - descuentosVariosVentaTemp) * (porcentajeComisionAgencia / 100);
			
			
			// validacion cuando el producto cobra iva
			data.setProductoId(productoIf.getId());

			ivaCompraTemp = (subTotalCompraTemp - descuentoEspecialCompraTemp - descuentoCompraTemp - bonificacionCompraTemp) * IVA;
			
			if (getCbSinIVA().isSelected())
				ivaTemp = 0.0;
			else
				ivaTemp = (subTotalVentaTemp - descuentoEspecialVentaTemp - descuentoVentaTemp - descuentosVariosVentaTemp + comisionAgenciaTemp) * IVA;
				
			data.setConcepto(this.getTxtConceptoPresupuestoDetalle().getText());
			data.setCantidad(BigDecimal.valueOf(Double.parseDouble(this.getTxtCantidad().getText())));
			data.setPrecioagencia(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPrecioCompra().getText()))));
			data.setPrecioventa(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPrecioVenta().getText()))));
			data.setPorcentajeDescuentoAgenciaVenta(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDsctoAgenciaVenta().getText()))));
			data.setPorcentajeDescuentoEspecialVenta(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentoEspecialVenta().getText()))));
			data.setPorcentajeDescuentosVariosVenta(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentosVariosVenta().getText()))));
			data.setIva(BigDecimal.valueOf(ivaTemp));
			data.setProveedorId(proveedorIf.getId());
			data.setPorcentajeDescuentoAgenciaCompra(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDsctoAgenciaCompra().getText()))));
			data.setPorcentajeDescuentoEspecialCompra(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentoEspecialCompra().getText()))));
			data.setPorcentajeDescuentosVariosCompra(BigDecimal.valueOf(porcentajeBonificacionCompra));
			data.setPorcentajeNotaCreditoCompra(BigDecimal.valueOf(Double.parseDouble(getTxtPorcentajeNotaCredito().getText())));
			data.setIvaCompra(BigDecimal.valueOf(ivaCompraTemp));
			data.setReporte(REPORTE_NO);
			data.setOrdenCompraId(null);
			data.setPorcentajeNegociacionDirecta(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeNegociacionDirecta().getText()))));
			data.setPorcentajeComisionPura(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeComisionPura().getText()))));
			data.setPorcentajeComisionAdicional(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeComisionAdicional().getText()))));
			
			if(getCmbFechaPublicacion().getDate() != null){
				data.setFechaPublicacion(new Timestamp(getCmbFechaPublicacion().getDate().getTime()));
			}
			
			setOrdenAutomatico(data,-1);
			presupuestoDetalleColeccion.add(data);
			
			dataP.setProductoId(productoIf.getId());
			dataP.setConcepto(this.getTxtConceptoPresupuestoDetalle().getText());
			dataP.setCantidad(BigDecimal.valueOf(Double.parseDouble(this.getTxtCantidad().getText())));
			dataP.setPrecioventa(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPrecioVenta().getText()))));
			dataP.setReporte(REPORTE_SI);
			dataP.setPorcentajeNegociacionDirecta(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeNegociacionDirecta().getText()))));
			dataP.setPorcentajeComisionPura(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeComisionPura().getText()))));
			dataP.setPorcentajeComisionAdicional(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeComisionAdicional().getText()))));
			dataP.setPorcentajeDescuentoEspecialCompra(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentoEspecialCompra().getText()))));
			dataP.setPorcentajeDescuentoEspecialVenta(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentoEspecialVenta().getText()))));
			
			if(getCmbFechaPublicacion().getDate() != null){
				dataP.setFechaPublicacion(new Timestamp(getCmbFechaPublicacion().getDate().getTime()));
			}
			
			presupuestoDetalleColeccionP.add(dataP);
			
			// Agrega los valores al registro que va ser añadido a la tabla
			// Agrega informacion visual a la tabla de detalle para el usuario
			filaDetalle.add(false);
			filaDetalle.add(getTxtConceptoPresupuestoDetalle().getText());
			filaDetalle.add(getTxtCantidad().getText());
			filaDetalle.add(String.valueOf(Utilitarios.removeDecimalFormat(getTxtPrecioCompra().getText())));
			filaDetalle.add(String.valueOf(Utilitarios.removeDecimalFormat(getTxtPrecioVenta().getText())));
			filaDetalle.add(getTxtPorcentajeDsctoAgenciaVenta().getText() + " % ");
			filaDetalle.add(getTxtPorcentajeDsctoAgenciaCompra().getText() + " % ");
			filaDetalle.add(data.getOrden().toString());
			//oc no existe aún porque es detalle nuevo
			filaDetalle.add("");
			filaDetalle.add(getTxtPorcentajeNegociacionDirecta().getText() + " % ");
			filaDetalle.add(getTxtPorcentajeComisionPura().getText() + " % ");
			filaDetalle.add(porcentajeBonificacionCompra + " % ");
			filaDetalle.add(getTxtPorcentajeDescuentoEspecialCompra().getText() + " % ");
			filaDetalle.add(porcentajeDescuentosVariosVenta + " % ");
			filaDetalle.add(getTxtPorcentajeDescuentoEspecialVenta().getText() + " % ");
			filaDetalle.add(getTxtPorcentajeComisionAdicional().getText() + " % ");
			
			if(getCmbFechaPublicacion().getDate() != null){
				String anio = String.valueOf(getCmbFechaPublicacion().getDate().getYear()+1900);
				String mes = String.valueOf(getCmbFechaPublicacion().getDate().getMonth()+1);
				mes = Integer.valueOf(mes) < 10 ? "0"+mes : mes;
				String dia = String.valueOf(getCmbFechaPublicacion().getDate().getDate());
				dia = Integer.valueOf(dia) < 10 ? "0"+dia : dia;
				filaDetalle.add(anio+"-"+mes+"-"+dia);
			}else{
				filaDetalle.add("");
			}			
			
			filaDetalleP.add(getTxtConceptoPresupuestoDetalle().getText());
			filaDetalleP.add(getTxtCantidad().getText());
			filaDetalleP.add("");
			filaDetalleP.add(String.valueOf(Utilitarios.removeDecimalFormat(getTxtPrecioVenta().getText())));
			filaDetalleP.add("");
			filaDetalleP.add("");
			modelPresupuestoDetalle.addRow(filaDetalle);
			modelPresupuestoDetalleP.addRow(filaDetalleP);

			calcularTotalReporte();

		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert	.createAlert("No se pudo agregar el Detalle del Presupuesto a la lista !!!", SpiritAlert.ERROR);
		}
	}
	
	private int getUltimoOrden(){
		Integer ultimoOrden = 0;
		if ( presupuestoDetalleColeccion != null ){
			for ( PresupuestoDetalleIf detalle : presupuestoDetalleColeccion){
				Integer ordenTmp = detalle.getOrden()!= null ? detalle.getOrden() : 0;
				ultimoOrden =  Math.max(ordenTmp, ultimoOrden) ; 
			}
		}
		return ultimoOrden;
	}

	public void calcularTotalReporte() {
		totalVentaReporteP = new BigDecimal(0);
		BigDecimal precioVentaP = new BigDecimal(0);
		BigDecimal cantidadP = new BigDecimal(0);
		for (PresupuestoDetalleIf presupuestoDetalleP : presupuestoDetalleColeccionP) {
			precioVentaP = presupuestoDetalleP.getPrecioventa();
			cantidadP = presupuestoDetalleP.getCantidad();
			totalVentaReporteP = totalVentaReporteP.add(precioVentaP.multiply(cantidadP));
		}
		getTxtTotalReporte().setText(
				formatoDecimal.format(Utilitarios.redondeoValor(totalVentaReporteP.doubleValue())));
	}

	public void calcularDetalle() {
		// try {
		Double subtotalTemp = 0D, valorBrutoTemp = 0D;
		Double descuentoEspecialCompraTemp = 0D, descuentoEspecialVentaTemp = 0D;
		Double descuentoCompraTemp = 0D, descuentoTemp = 0D;
		Double bonificacionCompraTemp = 0D, descuentosVariosVentaTemp = 0D;
		
		subtotalTemp = Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPrecioVenta().getText())) * Double.parseDouble(this.getTxtCantidad().getText());
		subTotal = subTotal + subtotalTemp;
		this.getTxtSubTotalVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(subTotal)));
		
		// el valor bruto es el subtotal para la agencia
		valorBrutoTemp = Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPrecioCompra().getText()))* Double.parseDouble(this.getTxtCantidad().getText());
		valorBruto = valorBruto + valorBrutoTemp;
		this.getTxtSubTotalCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(valorBruto)));
				
		descuentoEspecialCompraTemp = valorBrutoTemp * (Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentoEspecialCompra().getText())) / 100);
		descuentoEspecialVentaTemp = subtotalTemp * (Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentoEspecialVenta().getText())) / 100);
				
		descuentoEspecialTotalCompra = descuentoEspecialTotalCompra + descuentoEspecialCompraTemp;
		descuentoEspecialTotalVenta = descuentoEspecialTotalVenta + descuentoEspecialVentaTemp;

		if (!Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDsctoAgenciaVenta().getText()).equals("")
				&& !Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDsctoAgenciaVenta().getText()).equals("0"))
			descuentoTemp = (subtotalTemp - descuentoEspecialVentaTemp) * (Double.parseDouble(this.getTxtPorcentajeDsctoAgenciaVenta().getText().replaceAll(",", "")) / 100);
		
		//Calculo descuento de compra solo si negociacion directa es menor a 100% y no hay Comision Pura
		if (!Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDsctoAgenciaCompra().getText()).equals("") && !getCbComisionPura().isSelected()){
			double porcentajeDescuentoNegociacion = Double.valueOf(getTxtPorcentajeDsctoAgenciaCompra().getText().replaceAll(",", ""));
			
			if(getCbNegociacionDirecta().isSelected()){
				double porcentajeNegociacion = Double.valueOf(getTxtPorcentajeNegociacionDirecta().getText().replaceAll(",", ""));
				if(porcentajeNegociacion < 100D){
					descuentoCompraTemp = (valorBrutoTemp - descuentoEspecialCompraTemp) * (porcentajeDescuentoNegociacion / 100);
				}
			}else{
				descuentoCompraTemp = (valorBrutoTemp - descuentoEspecialCompraTemp) * (porcentajeDescuentoNegociacion / 100);
			}
		}
		
		//Calculo descuentos varios compra solo si negociacion directa es menor a 100% y no hay Comision Pura
		if (!Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentosVariosCompra().getText()).equals("") && !getCbComisionPura().isSelected()){
			double porcentajeDescuentoNegociacion = Double.valueOf(getTxtPorcentajeDescuentosVariosCompra().getText().replaceAll(",", ""));
			
			if(getCbNegociacionDirecta().isSelected()){
				double porcentajeNegociacion = Double.valueOf(getTxtPorcentajeNegociacionDirecta().getText().replaceAll(",", ""));
				if(porcentajeNegociacion < 100D){
					bonificacionCompraTemp = (valorBrutoTemp - descuentoEspecialCompraTemp) * (porcentajeDescuentoNegociacion / 100);
				}
			}else{
				bonificacionCompraTemp = (valorBrutoTemp - descuentoEspecialCompraTemp) * (porcentajeDescuentoNegociacion / 100);
			}
		}
		
		
		/*if (!Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentosVariosCompra().getText()).equals("")){
			bonificacionCompraTemp = (valorBrutoTemp - descuentoEspecialCompraTemp) * (Double.parseDouble(this.getTxtPorcentajeDescuentosVariosCompra().getText().replaceAll(",", "")) / 100);
		}*/
		
		//Calculo descuentos varios venta 		
		if (!Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentosVariosVenta().getText()).equals("")){
			descuentosVariosVentaTemp = (subtotalTemp - descuentoEspecialVentaTemp) * (Double.parseDouble(this.getTxtPorcentajeDescuentosVariosVenta().getText().replaceAll(",", "")) / 100);
		}

		descuentoTotal = descuentoTotal + descuentoTemp;
		this.getTxtDsctoAgenciaVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoTotal)));

		descuentoCompraTotal = descuentoCompraTotal + descuentoCompraTemp;
		this.getTxtDsctoAgenciaCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoCompraTotal)));
		
		bonificacionCompraTotal = bonificacionCompraTotal + bonificacionCompraTemp;
		this.getTxtDescuentosVariosCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(bonificacionCompraTotal)));
		
		descuentosVariosVentaTotal = descuentosVariosVentaTotal + descuentosVariosVentaTemp;
		this.getTxtDescuentosVariosVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentosVariosVentaTotal)));
				
		comisionAgencia = 0D;
		if (porcentajeComisionAgencia > 0D){
			comisionAgencia = ((subTotal - descuentoEspecialTotalVenta - descuentoTotal - descuentosVariosVentaTotal) * porcentajeComisionAgencia) / 100D;
		}
		this.getTxtValorComision().setText(formatoDecimal.format(Utilitarios.redondeoValor(comisionAgencia)));
		
		this.getTxtDescuentoEspecialTotalCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoEspecialTotalCompra)));
		this.getTxtDescuentoEspecialTotalVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(descuentoEspecialTotalVenta)));
		
		double subtTotal2Compra = valorBruto - descuentoEspecialTotalCompra;
		double subtTotal2Venta = subTotal - descuentoEspecialTotalVenta;
		this.getTxtSubTotal2Compra().setText(formatoDecimal.format(Utilitarios.redondeoValor(subtTotal2Compra)));
		this.getTxtSubTotal2Venta().setText(formatoDecimal.format(Utilitarios.redondeoValor(subtTotal2Venta)));
		
		ivaTotalAgencia = (valorBruto - descuentoEspecialTotalCompra - descuentoCompraTotal - bonificacionCompraTotal) * IVA;
				
		if (getCbSinIVA().isSelected())
			ivaTotal = 0D;
		else
			ivaTotal = (subTotal - descuentoEspecialTotalVenta - descuentoTotal - descuentosVariosVentaTotal + comisionAgencia) * IVA;
			

		this.getTxtIvaVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotal)));
		
		// calculos de los totales de agencia
		this.getTxtIvaTotalCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(ivaTotalAgencia)));
		totalAgencia = valorBruto - descuentoEspecialTotalCompra - descuentoCompraTotal - bonificacionCompraTotal + ivaTotalAgencia;
		this.getTxtTotalCompra().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalAgencia)));
		total = subTotal - descuentoEspecialTotalVenta - descuentoTotal - descuentosVariosVentaTotal + comisionAgencia + ivaTotal;
		this.getTxtTotalVenta().setText(formatoDecimal.format(Utilitarios.redondeoValor(total)));
		getTxtProveedor().setText("");
		getTxtProducto().setText("");
		getTxtConceptoPresupuestoDetalle().setText("");
		getTxtPrecioCompra().setText("");
		getTxtCantidad().setText("");
		getTxtPrecioVenta().setText("");
		getTxtPorcentajeDsctoAgenciaVenta().setText("0");
		getTxtPorcentajeDescuentoEspecialVenta().setText("0");
		getTxtPorcentajeDescuentosVariosVenta().setText("0");
		getTxtPorcentajeDsctoAgenciaCompra().setText("0");
		getTxtPorcentajeDescuentoEspecialCompra().setText("0");
		getTxtPorcentajeDescuentosVariosCompra().setText("0");
		getTxtPorcentajeNotaCredito().setText("0");
		getTxtPorcentajeNegociacionDirecta().setText("");
		getCbNegociacionDirecta().setSelected(false);
		getTxtPorcentajeNegociacionDirecta().setText("");
		getTxtPorcentajeNegociacionDirecta().setEditable(false);
		getCbComisionPura().setSelected(false);
		getTxtPorcentajeComisionPura().setText("");
		getTxtPorcentajeComisionPura().setEditable(false);
		getTxtPorcentajeComisionAdicional().setText("");
		getTxtPorcentajeComisionAdicional().setEditable(false);
		getTxtConceptoPresupuestoDetalle().grabFocus();
		getCmbFechaPublicacion().setCalendar(null);
	}

	ActionListener oActionListenerEliminarPresupuestoDetalle = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			eliminarDetallePresupuesto();
		}
	};

	public void loadCombos() {
		// Seteo el formato de los combos de fecha
		Calendar calendar = new GregorianCalendar();
		getCmbFechaCreacion().setCalendar(calendar);
		getCmbFechaPresupuesto().setCalendar(calendar);
		getCmbFechaAprobacion().setCalendar(null);
		getCmbFechaPublicacion().setCalendar(null);
		getCmbFechaCreacion().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaPresupuesto().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaAprobacion().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaPublicacion().setFormat(Utilitarios.setFechaUppercase());
		cargarComboTipoOrden();
		cargarComboEstado();
		/*getCmbEstado().addItem(NOMBRE_ESTADO_COTIZADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_PENDIENTE);
		getCmbEstado().addItem(NOMBRE_ESTADO_APROBADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_CANCELADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_FACTURADO);
		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_COTIZADO);*/
		getCmbEstado().repaint();
		cargarComboFormaPago();
		cargarComboTipoArchivo();
	}
	
	private void cargarComboEstado(){
		//Vector<EstadoPresupuesto> estados = new Vector<EstadoPresupuesto>(Arrays.asList(EstadoPresupuesto.values()));
		DefaultComboBoxModel modelo = new DefaultComboBoxModel(EstadoPresupuesto.values());
		getCmbEstado().setModel(modelo);
		getCmbEstado().setSelectedItem(EstadoPresupuesto.COTIZADO);
	}

	private void cargarComboTipoArchivo() {
		try {
			SpiritComboBoxModel cmbModelTipoArchivo = new SpiritComboBoxModel(
					(ArrayList) SessionServiceLocator
							.getTipoArchivoSessionService()
							.getTipoArchivoList());
			getCmbTipoArchivo().setModel(cmbModelTipoArchivo);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void cargarComboTipoOrden() {
		try {
			List tipoOrdenList = (List) SessionServiceLocator.getTipoOrdenSessionService().findTipoOrdenByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTipoOrden(), tipoOrdenList);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);			
		}
	}

	// Action Listener del Boton Actualizar Presupuesto Detalle
	ActionListener oActionListenerBtnActualizarPresupuestoDetalle = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (validateFieldsDetalle(false)) {
				try {
					actualizarDetallePresupuesto(false, true, false);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				}
			}
		}
	};

	ActionListener oActionListenerBtnActualizarPresupuestoDetalleP = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (validateFieldsDetalle(true)) {
				actualizarDetallePresupuestoP();
			}
		}
	};

	private void actualizarDetallePresupuesto(boolean recalcularTotales,
			boolean actualizarDetalleReporte, boolean esFind) throws GenericBusinessException {

		if(getTblPresupuestoDetalle().getSelectedRow() != -1){
			int filaSeleccionada = getTblPresupuestoDetalle().convertRowIndexToModel(getTblPresupuestoDetalle().getSelectedRow());
			// si esta en modo update lo actualiza directamente de la base
			if (getMode() != SpiritMode.FIND_MODE) {
				if ("".equals(getTxtPorcentajeDsctoAgenciaVenta().getText()))
					getTxtPorcentajeDsctoAgenciaVenta().setText("0");
				if ("".equals(getTxtPorcentajeDescuentoEspecialVenta().getText()))
					getTxtPorcentajeDescuentoEspecialVenta().setText("0");
				if ("".equals(getTxtPorcentajeDescuentosVariosVenta().getText()))
					getTxtPorcentajeDescuentosVariosVenta().setText("0");
				if ("".equals(getTxtPorcentajeDsctoAgenciaCompra().getText()))
					getTxtPorcentajeDsctoAgenciaCompra().setText("0");
				if ("".equals(getTxtPorcentajeDescuentoEspecialCompra().getText()))
					getTxtPorcentajeDescuentoEspecialCompra().setText("0");
				if ("".equals(getTxtPorcentajeDescuentosVariosCompra().getText()))
					getTxtPorcentajeDescuentosVariosCompra().setText("0");
				if ("".equals(getTxtPorcentajeNotaCredito().getText()))
					getTxtPorcentajeNotaCredito().setText("0");
				if ("".equals(getTxtPorcentajeNegociacionDirecta().getText()))
					getTxtPorcentajeNegociacionDirecta().setText("0");
				if ((Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecioCompra().getText()))) > (Double.parseDouble(
						Utilitarios.removeDecimalFormat(getTxtPrecioVenta().getText())))) {
					SpiritAlert.createAlert("El Precio de Venta no puede ser menor al Precio de Agencia",SpiritAlert.WARNING);
					getTxtPrecioVenta().grabFocus();
				} else if (Double.parseDouble(getTxtPorcentajeDsctoAgenciaVenta().getText().replaceAll(",", "")) > 100) {
					SpiritAlert.createAlert("El porcentaje de descuento no puede ser mayor al 100%",SpiritAlert.WARNING);
					getTxtPorcentajeDsctoAgenciaVenta().grabFocus();
				} else if (Double.parseDouble(getTxtPorcentajeDescuentoEspecialVenta().getText().replaceAll(",", "")) > 100) {
					SpiritAlert.createAlert("El porcentaje de descuento especial venta no puede ser mayor al 100%",SpiritAlert.WARNING);
					getTxtPorcentajeDescuentoEspecialVenta().grabFocus();
				} else if (Double.parseDouble(getTxtPorcentajeDescuentosVariosVenta().getText().replaceAll(",", "")) > 100) {
					SpiritAlert.createAlert("El porcentaje de descuentos varios venta no puede ser mayor al 100%",SpiritAlert.WARNING);
					getTxtPorcentajeDescuentosVariosVenta().grabFocus();
				} else if (Double.parseDouble(getTxtPorcentajeDsctoAgenciaCompra().getText().replaceAll(",", "")) > 100) {
					SpiritAlert.createAlert("El porcentaje de descuento de agencia no puede ser mayor al 100%",SpiritAlert.WARNING);
					getTxtPorcentajeDsctoAgenciaCompra().grabFocus();
				} else if (Double.parseDouble(getTxtPorcentajeDescuentoEspecialCompra().getText().replaceAll(",", "")) > 100) {
					SpiritAlert.createAlert("El porcentaje de descuento especial no puede ser mayor al 100%",SpiritAlert.WARNING);
					getTxtPorcentajeDescuentoEspecialCompra().grabFocus();
				} else if (Double.parseDouble(getTxtPorcentajeDescuentosVariosCompra().getText().replaceAll(",", "")) > 100) {
					SpiritAlert.createAlert("El porcentaje de desctos. varios de compra no puede ser mayor al 100%",SpiritAlert.WARNING);
					getTxtPorcentajeDescuentosVariosCompra().grabFocus();
				} else if (Double.parseDouble(getTxtPorcentajeNotaCredito().getText().replaceAll(",", "")) > 100) {
					SpiritAlert.createAlert("El porcentaje de nota de crédito no puede ser mayor al 100%",SpiritAlert.WARNING);
					getTxtPorcentajeNotaCredito().grabFocus();
				}
				
				else {
					
					presupuestoDetalleIf = (PresupuestoDetalleIf) presupuestoDetalleColeccion.get(filaSeleccionada);
					
					if(presupuestoDetalleIf.getOrdenCompraId() != null && presupuestoDetalleIf.getProveedorId().compareTo(proveedorIf.getId()) != 0){
						SpiritAlert.createAlert("La orden tiene un proveedor diferente, elimine primero la orden.", SpiritAlert.WARNING);
					}else{			
						
						PresupuestoDetalleData data = new PresupuestoDetalleData();
						
						Double subTotalTemp = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecioVenta().getText())) * Double.parseDouble(getTxtCantidad().getText());
						Double subTotalCompraTemp = Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPrecioCompra().getText())) * Double.parseDouble(this.getTxtCantidad().getText());
						
						Double descuentoEspecialCompraTemp = subTotalCompraTemp	* (Double.parseDouble(this.getTxtPorcentajeDescuentoEspecialCompra().getText().replaceAll(",", "")) / 100);					
						Double descuentoEspecialVentaTemp = subTotalTemp * (Double.parseDouble(this.getTxtPorcentajeDescuentoEspecialVenta().getText().replaceAll(",", "")) / 100);					
											
						Double descuentoTemp = (subTotalTemp - descuentoEspecialVentaTemp) * (Double.parseDouble(getTxtPorcentajeDsctoAgenciaVenta().getText().replaceAll(",", "")) / 100);
						Double descuentoCompraTemp = (subTotalCompraTemp - descuentoEspecialCompraTemp) * (Double.parseDouble(this.getTxtPorcentajeDsctoAgenciaCompra().getText().replaceAll(",", "")) / 100);
						
						Double bonificacionCompraTemp = (subTotalCompraTemp - descuentoEspecialCompraTemp) * (Double.parseDouble(this.getTxtPorcentajeDescuentosVariosCompra().getText().replaceAll(",", "")) / 100);
						Double descuentosVariosVentaTemp = (subTotalTemp - descuentoEspecialVentaTemp) * (Double.parseDouble(this.getTxtPorcentajeDescuentosVariosVenta().getText().replaceAll(",", "")) / 100);
						
						ivaCompraTemp = (subTotalCompraTemp - descuentoEspecialCompraTemp - descuentoCompraTemp - bonificacionCompraTemp) * IVA;
						
						double comisionAgenciaTemp = (subTotalTemp - descuentoEspecialVentaTemp - descuentoTemp - descuentosVariosVentaTemp) * (porcentajeComisionAgencia / 100);
						
						if (getCbSinIVA().isSelected())
							ivaTemp = 0.0;
						else
							ivaTemp = (subTotalTemp - descuentoEspecialVentaTemp - descuentoTemp - descuentosVariosVentaTemp + comisionAgenciaTemp) * IVA;
							
						Double pv = presupuestoDetalleIf.getPrecioventa().doubleValue();
						Double pa = presupuestoDetalleIf.getPrecioagencia().doubleValue();
						Double cant = presupuestoDetalleIf.getCantidad().doubleValue();
						Double porcentajeEspecialCompra = presupuestoDetalleIf.getPorcentajeDescuentoEspecialCompra()!=null?presupuestoDetalleIf.getPorcentajeDescuentoEspecialCompra().doubleValue():0D;
						Double porcentajeEspecialVenta = presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta()!=null?presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta().doubleValue():0D;
						Double descu = presupuestoDetalleIf.getPorcentajeDescuentoAgenciaVenta().doubleValue();
						Double descuCompra = presupuestoDetalleIf.getPorcentajeDescuentoAgenciaCompra().doubleValue();
						Double porcentajeBonificacionCompra = presupuestoDetalleIf.getPorcentajeDescuentosVariosCompra().doubleValue();
						Double porcentajeDescuentosVariosVenta = presupuestoDetalleIf.getPorcentajeDescuentosVariosVenta().doubleValue();
						
						// seteo con los nuevos valores al objeto del presupuesto
						data.setId(presupuestoDetalleIf.getId());
						data.setProductoId(productoIf.getId());
						data.setConcepto(getTxtConceptoPresupuestoDetalle().getText());
						data.setCantidad(BigDecimal.valueOf(Double.parseDouble(getTxtCantidad().getText())));
						data.setPrecioagencia(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecioCompra().getText()))));
						data.setPrecioventa(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecioVenta().getText()))));
						data.setPorcentajeDescuentoAgenciaVenta(BigDecimal.valueOf(Double.parseDouble(getTxtPorcentajeDsctoAgenciaVenta().getText())));
						data.setPorcentajeDescuentoAgenciaCompra(BigDecimal.valueOf(Double.parseDouble(getTxtPorcentajeDsctoAgenciaCompra().getText())));
						data.setPorcentajeDescuentosVariosCompra(BigDecimal.valueOf(Double.parseDouble(getTxtPorcentajeDescuentosVariosCompra().getText())));
						data.setPorcentajeNotaCreditoCompra(BigDecimal.valueOf(Double.parseDouble(getTxtPorcentajeNotaCredito().getText())));
						data.setPorcentajeDescuentosVariosVenta(BigDecimal.valueOf(Double.parseDouble(getTxtPorcentajeDescuentosVariosVenta().getText())));					
						data.setIva(BigDecimal.valueOf(ivaTemp));
						data.setIvaCompra(BigDecimal.valueOf(ivaCompraTemp));
						data.setPresupuestoId(presupuestoDetalleIf.getPresupuestoId());					
						data.setProveedorId(proveedorIf.getId());
						data.setReporte(REPORTE_NO);
						data.setOrdenCompraId(presupuestoDetalleIf.getOrdenCompraId());
						data.setPorcentajeNegociacionDirecta(BigDecimal.valueOf(Double.parseDouble(getTxtPorcentajeNegociacionDirecta().getText())));
						data.setPorcentajeComisionPura(BigDecimal.valueOf(Double.parseDouble(getTxtPorcentajeComisionPura().getText())));
						data.setPorcentajeComisionAdicional(BigDecimal.valueOf(Double.parseDouble(getTxtPorcentajeComisionAdicional().getText())));
						data.setPorcentajeDescuentoEspecialCompra(BigDecimal.valueOf(Double.parseDouble(getTxtPorcentajeDescuentoEspecialCompra().getText())));
						data.setPorcentajeDescuentoEspecialVenta(BigDecimal.valueOf(Double.parseDouble(getTxtPorcentajeDescuentoEspecialVenta().getText())));
						
						if(getCmbFechaPublicacion().getDate() != null){
							data.setFechaPublicacion(new Timestamp(getCmbFechaPublicacion().getDate().getTime()));
						}
						
						setOrdenAutomatico(data,filaSeleccionada);
						
						OrdenCompraIf oc = null;
						if(data.getOrdenCompraId() != null){
							oc = mapaOrdenesCompra.get(data.getOrdenCompraId());
							if(oc == null){
								oc = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(data.getOrdenCompraId());
							}
						}
										
						presupuestoDetalleColeccion.set(filaSeleccionada, data);
						
						// Actualizo en la tabla					
						modelPresupuestoDetalle.setValueAt(false,filaSeleccionada,0);
						modelPresupuestoDetalle.setValueAt(getTxtConceptoPresupuestoDetalle().getText(),filaSeleccionada,1);
						modelPresupuestoDetalle.setValueAt(getTxtCantidad().getText(), filaSeleccionada, 2);
						modelPresupuestoDetalle.setValueAt(Utilitarios.removeDecimalFormat(getTxtPrecioCompra().getText()),filaSeleccionada, 3);
						modelPresupuestoDetalle.setValueAt(Utilitarios.removeDecimalFormat(getTxtPrecioVenta().getText()),filaSeleccionada, 4);
						modelPresupuestoDetalle.setValueAt(getTxtPorcentajeDsctoAgenciaVenta().getText() + " % ", filaSeleccionada, 5);
						modelPresupuestoDetalle.setValueAt(getTxtPorcentajeDsctoAgenciaCompra().getText() + " % ", filaSeleccionada, 6);
						modelPresupuestoDetalle.setValueAt(data.getOrden().toString(), filaSeleccionada, 7);
						modelPresupuestoDetalle.setValueAt(oc!=null?oc.getCodigo()+"-"+oc.getRevision():"",	filaSeleccionada, 8);
						modelPresupuestoDetalle.setValueAt(getTxtPorcentajeNegociacionDirecta().getText() + " % ", filaSeleccionada, 9);
						modelPresupuestoDetalle.setValueAt(getTxtPorcentajeComisionPura().getText() + " % ", filaSeleccionada, 10);
						modelPresupuestoDetalle.setValueAt(getTxtPorcentajeDescuentosVariosCompra().getText() + " % ", filaSeleccionada, 11);
						modelPresupuestoDetalle.setValueAt(getTxtPorcentajeDescuentoEspecialCompra().getText() + " % ",	filaSeleccionada, 12);					
						modelPresupuestoDetalle.setValueAt(getTxtPorcentajeDescuentosVariosVenta().getText() + " % ", filaSeleccionada, 13);
						modelPresupuestoDetalle.setValueAt(getTxtPorcentajeDescuentoEspecialVenta().getText() + " % ", filaSeleccionada, 14);
						modelPresupuestoDetalle.setValueAt(getTxtPorcentajeComisionAdicional().getText() + " % ", filaSeleccionada, 15);
						
						if(getCmbFechaPublicacion().getDate() != null){
							String anio = String.valueOf(getCmbFechaPublicacion().getDate().getYear()+1900);
							String mes = String.valueOf(getCmbFechaPublicacion().getDate().getMonth()+1);
							mes = Integer.valueOf(mes) < 10 ? "0"+mes : mes;
							String dia = String.valueOf(getCmbFechaPublicacion().getDate().getDate());
							dia = Integer.valueOf(dia) < 10 ? "0"+dia : dia;
							
							modelPresupuestoDetalle.setValueAt(anio+"-"+mes+"-"+dia,
									filaSeleccionada, 16);
						}else{
							modelPresupuestoDetalle.setValueAt("", filaSeleccionada, 16);
						}
						
						
						
						// Para actualizar tambien la parte de reporte
						
						//si es Medios actualizo automaticamente el reporte porque deben ser iguales
						TipoOrdenIf tipoOrden = (TipoOrdenIf) getCmbTipoOrden().getSelectedItem();
						
						if (tipoOrden.getTipo().equals(TIPO_MEDIOS) && !esFind) {
							actualizarReporteDetalle(filaSeleccionada);
						}else if(actualizarDetalleReporte){
							int opcion = JOptionPane.showOptionDialog(
									null,
									"¿Desea actualizar también el detalle del Reporte?",
									"Confirmación",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									options, no);
							if (opcion == JOptionPane.YES_OPTION) {
								actualizarReporteDetalle(filaSeleccionada);
							}
						}

						// disminuyo del presupuesto los valores viejos del presupuesto
						if (!recalcularTotales) {
							subTotal = subTotal - (pv * cant);
							valorBruto = valorBruto - (pa * cant);
							Double descEspecialCompra = (pa * cant) * (porcentajeEspecialCompra / 100);
							Double descEspecialVenta = (pv * cant) * (porcentajeEspecialVenta / 100);
							
							Double desc = ((pv * cant) - descEspecialVenta) * (descu / 100);
							
							//El porcentaje de descuento compra no es tomado en cuenta cuando existe Negociacion Directa 100% o Comision Pura
							//como es actualización hay muchos casos dependiendo que se esta actualizando.
							Double descCompra = 0D;						
							if(presupuestoDetalleIf.getPorcentajeComisionPura().compareTo(new BigDecimal(0)) == 0){
								if(presupuestoDetalleIf.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(100)) == -1){
									descCompra = ((pa * cant) - descEspecialCompra) * (descuCompra / 100);
								}else if(presupuestoDetalleIf.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(100)) != 0
										&& !getCbComisionPura().isSelected()){
									if(getCbNegociacionDirecta().isSelected()){
										double porcentajeNegociacion = Double.valueOf(getTxtPorcentajeNegociacionDirecta().getText().replaceAll(",", ""));
										if(porcentajeNegociacion < 100D){
											descCompra = ((pa * cant) - descEspecialCompra) * (descuCompra / 100);
										}
									}else if(!getCbNegociacionDirecta().isSelected()){
										descCompra = ((pa * cant) - descEspecialCompra) * (descuCompra / 100);
									}
								}
							}
							
							if((descuentoCompraTotal - descCompra) < 0){
								descCompra = 0D;
							}
							
							//El porcentaje de descuentos varios compra no es tomado en cuenta cuando existe Negociacion Directa 100% o Comision Pura
							//como es actualización hay muchos casos dependiendo que se esta actualizando.
							Double bonificacionCompra = 0D;						
							if(presupuestoDetalleIf.getPorcentajeComisionPura().compareTo(new BigDecimal(0)) == 0){
								if(presupuestoDetalleIf.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(100)) == -1){
									bonificacionCompra = ((pa * cant) - descEspecialCompra) * (porcentajeBonificacionCompra / 100);
								}else if(presupuestoDetalleIf.getPorcentajeNegociacionDirecta().compareTo(new BigDecimal(100)) != 0
										&& !getCbComisionPura().isSelected()){
									if(getCbNegociacionDirecta().isSelected()){
										double porcentajeNegociacion = Double.valueOf(getTxtPorcentajeNegociacionDirecta().getText().replaceAll(",", ""));
										if(porcentajeNegociacion < 100D){
											bonificacionCompra = ((pa * cant) - descEspecialCompra) * (porcentajeBonificacionCompra / 100);
										}
									}else if(!getCbNegociacionDirecta().isSelected()){
										bonificacionCompra = ((pa * cant) - descEspecialCompra) * (porcentajeBonificacionCompra / 100);
									}
								}
							}
							
							if((bonificacionCompraTotal - bonificacionCompra) < 0){
								bonificacionCompra = 0D;
							}						
							
							//Double bonificacionCompra = ((pa * cant) - descEspecialCompra) * (porcentajeBonificacionCompra / 100);
													
							Double descuentosVariosVenta = ((pv * cant) - descEspecialVenta) * (porcentajeDescuentosVariosVenta / 100);
							
							descuentoEspecialTotalCompra = descuentoEspecialTotalCompra - descEspecialCompra; 
							descuentoEspecialTotalVenta = descuentoEspecialTotalVenta - descEspecialVenta; 
							
							descuentoTotal = descuentoTotal - desc;
							descuentoCompraTotal = descuentoCompraTotal	- descCompra;
							
							bonificacionCompraTotal = bonificacionCompraTotal - bonificacionCompra;
							descuentosVariosVentaTotal = descuentosVariosVentaTotal - descuentosVariosVenta;
							
							ivaTotalAgencia = (valorBruto - descuentoEspecialTotalCompra - descuentoCompraTotal - bonificacionCompraTotal) * IVA;
							
							comisionAgencia = comisionAgencia - ((((pv * cant) - descEspecialVenta - desc - descuentosVariosVenta) * porcentajeComisionAgencia) / 100);
							ivaTotal = (subTotal - descuentoEspecialTotalVenta - descuentoTotal - descuentosVariosVentaTotal + comisionAgencia) * IVA;
												
							totalAgencia = valorBruto - descuentoEspecialTotalCompra - descuentoCompraTotal - bonificacionCompraTotal + ivaTotalAgencia;
							total = subTotal - descuentoEspecialTotalVenta  - descuentoTotal - descuentosVariosVentaTotal + comisionAgencia + ivaTotal;
						}
						// actualizo el presupuesto con los nuevos valores
						calcularDetalle();
						getTxtConceptoPresupuestoDetalle().setText("");
						getTxtCantidad().setText("");
						getTxtPrecioCompra().setText("");
						getTxtPrecioVenta().setText("");
						getTxtPorcentajeDsctoAgenciaVenta().setText("0");
						getTxtPorcentajeDescuentoEspecialVenta().setText("0");
						getTxtPorcentajeDescuentosVariosVenta().setText("0");
						getTxtPorcentajeDsctoAgenciaCompra().setText("0");
						getTxtPorcentajeDescuentoEspecialCompra().setText("0");
						getTxtPorcentajeDescuentosVariosCompra().setText("0");
						getTxtPorcentajeNotaCredito().setText("0");
						getTxtPorcentajeNegociacionDirecta().setText("");
						getTxtOrden().setText("");
						getCmbFechaPublicacion().setCalendar(null);
						getCbNegociacionDirecta().setSelected(false);
						getTxtPorcentajeNegociacionDirecta().setText("");
						getTxtPorcentajeNegociacionDirecta().setEditable(false);
						getCbComisionPura().setSelected(false);
						getTxtPorcentajeComisionPura().setText("");
						getTxtPorcentajeComisionPura().setEditable(false);
						getTxtPorcentajeComisionAdicional().setText("");
						getTxtPorcentajeComisionAdicional().setEditable(false);
						getBtnBuscarProducto().setEnabled(false);
					}
					
					
				}
			}
			calcularTotalReporte(); 
			
		}else {
			SpiritAlert.createAlert("Debe seleccionar el Detalle a actualizar !!!",	SpiritAlert.WARNING);
		}		
	}

	private void actualizarReporteDetalle(int filaSeleccionada) {
		if (presupuestoDetalleColeccion.size() == presupuestoDetalleColeccionP.size()) {
			presupuestoDetalleIfP = (PresupuestoDetalleIf) presupuestoDetalleColeccionP.get(filaSeleccionada);

			PresupuestoDetalleData dataP = new PresupuestoDetalleData();
			dataP.setId(presupuestoDetalleIfP.getId());
			dataP.setProductoId(productoIf.getId());
			dataP.setConcepto(getTxtConceptoPresupuestoDetalle().getText());
			dataP.setCantidad(BigDecimal.valueOf(Double.parseDouble(getTxtCantidad().getText())));
			dataP.setPrecioventa(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtPrecioVenta().getText()))));
			dataP.setPresupuestoId(presupuestoDetalleIfP.getPresupuestoId());
			dataP.setReporte(REPORTE_SI);
			dataP.setPorcentajeNegociacionDirecta(BigDecimal.valueOf(Double.parseDouble(getTxtPorcentajeNegociacionDirecta().getText())));
			dataP.setPorcentajeComisionPura(BigDecimal.valueOf(Double.parseDouble(getTxtPorcentajeComisionPura().getText())));
			dataP.setPorcentajeComisionAdicional(BigDecimal.valueOf(Double.parseDouble(getTxtPorcentajeComisionAdicional().getText())));
			dataP.setPorcentajeDescuentoEspecialCompra(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentoEspecialCompra().getText()))));
			dataP.setPorcentajeDescuentoEspecialVenta(BigDecimal.valueOf(Double.parseDouble(Utilitarios.removeDecimalFormat(this.getTxtPorcentajeDescuentoEspecialVenta().getText()))));
			
			if(getCmbFechaPublicacion().getDate() != null){
				dataP.setFechaPublicacion(new Timestamp(getCmbFechaPublicacion().getDate().getTime()));
			}
			
			presupuestoDetalleColeccionP.set(filaSeleccionada, dataP);
			
			// Actualizo en la tabla
			modelPresupuestoDetalleP.setValueAt(getTxtConceptoPresupuestoDetalle().getText(),filaSeleccionada, 0);
			modelPresupuestoDetalleP.setValueAt(getTxtCantidad().getText(),filaSeleccionada, 1);
			modelPresupuestoDetalleP.setValueAt(Utilitarios.removeDecimalFormat(getTxtPrecioVenta().getText()),filaSeleccionada, 3);

			getTxtConceptoPresupuestoDetalleP().setText("");
			getTxtCantidadP().setText("");
			getTxtPrecioVentaP().setText("");
		} else {
			SpiritAlert.createAlert(
				"No se puede modificar el detalle del Reporte porque ya se han eliminado detalles.",SpiritAlert.WARNING);
		}
	}

	private void setOrdenAutomatico(PresupuestoDetalleIf data,int fila) throws GenericBusinessException {
		String ordenS = getTxtOrden().getText().trim();
		Integer ordenNuevo = buscarOrdenPorProveedor(data); 
		if ( !"".equals(ordenS) ){
			int orden = Integer.valueOf(ordenS);
			if ( orden <= 0 ){
				SpiritAlert.createAlert("Orden tiene que ser mayor que Cero !!", SpiritAlert.WARNING);
				PresupuestoDetalleIf detalleAntiguo = presupuestoDetalleColeccion.get(fila);
				if ( detalleAntiguo.getOrden() != null ){
					data.setOrden(detalleAntiguo.getOrden());
				} else 
					data.setOrden(ordenNuevo);
				//return;
			} else {
				//data.setOrden(orden);
				getSetOrdenComparado(data, orden, ordenNuevo, fila);
			}
		} else {
			
			data.setOrden(ordenNuevo);
			data.setOrdenCompraId(null);
		}
	}
	
	private void getSetOrdenComparado(PresupuestoDetalleIf data, int orden, int ordenNuevo,int fila){
		if ( presupuestoDetalleColeccion != null ){
			//Long idOrdenCompra = data.getOrdenCompraId();
			//data.setOrdenCompraId(null);
			PresupuestoDetalleIf detalle = getIndiceOrden(orden);
			if ( detalle != null ){
				//Si es del mismo proveedor de un detalle existente se pone 
				//el mimo numero de orden que tiene ese detalle
				if ( detalle.getProveedorId().equals(data.getProveedorId()) ){
					data.setOrden(orden);
					data.setOrdenCompraId(detalle.getOrdenCompraId());
				} else{
					if ( fila >= 0 && fila < presupuestoDetalleColeccion.size() ){
						PresupuestoDetalleIf detalleAntiguo = presupuestoDetalleColeccion.get(fila);
						if ( !detalle.getProveedorId().equals(data.getProveedorId()) ){
							data.setOrden(ordenNuevo);
							data.setOrdenCompraId(null);
						}//if ( detalleAntiguo.getOrden() != null )
						else if ( detalleAntiguo.getOrden() != null )
							data.setOrden(detalleAntiguo.getOrden());
						
					} else {
						data.setOrden(ordenNuevo);
						//data.setOrdenCompraId(idOrdenCompra);
					}
				}
			} else{
				data.setOrden(orden);
				data.setOrdenCompraId(null);
			}
		}
	}
	
	private PresupuestoDetalleIf getIndiceOrden(int orden){
		PresupuestoDetalleIf detalleExistente = null;
		if ( presupuestoDetalleColeccion != null ){
			for ( PresupuestoDetalleIf detalle : presupuestoDetalleColeccion){
				if ( detalle.getOrden() != null && orden == detalle.getOrden() )
					detalleExistente = detalle;
			}
		}
		return detalleExistente;
	}

	private void actualizarDetallePresupuestoP() {
		int filaSeleccionada = getTblPresupuestoDetalleP().getSelectedRow();
		if (filaSeleccionada != -1) {
			if (getMode() != SpiritMode.FIND_MODE) {
				presupuestoDetalleIfP = (PresupuestoDetalleIf) presupuestoDetalleColeccionP
						.get(filaSeleccionada);
				PresupuestoDetalleData data = new PresupuestoDetalleData();

				data.setId(presupuestoDetalleIfP.getId());
				data.setProductoId(productoIfP.getId());
				data.setConcepto(getTxtConceptoPresupuestoDetalleP().getText());
				data.setCantidad(BigDecimal.valueOf(Double
						.parseDouble(getTxtCantidadP().getText())));
				data.setPrecioventa(BigDecimal.valueOf(Double
						.parseDouble(getTxtPrecioVentaP().getText().replaceAll(
								",", ""))));
				data.setReporte(REPORTE_SI);
				data.setPorcentajeNegociacionDirecta(new BigDecimal(0));
				data.setPorcentajeComisionPura(new BigDecimal(0));		
				
				if(getCmbFechaPublicacion().getDate() != null){
					data.setFechaPublicacion(new Timestamp(getCmbFechaPublicacion().getDate().getTime()));
				}
				
				presupuestoDetalleColeccionP.set(filaSeleccionada, data);
				
				// Actualizo en la tabla
				modelPresupuestoDetalleP.setValueAt(
						getTxtConceptoPresupuestoDetalleP().getText(),
						getTblPresupuestoDetalleP().getSelectedRow(), 0);
				modelPresupuestoDetalleP.setValueAt(
						getTxtCantidadP().getText(),
						getTblPresupuestoDetalleP().getSelectedRow(), 1);
				modelPresupuestoDetalleP.setValueAt(Utilitarios.removeDecimalFormat(getTxtPrecioVentaP()
						.getText()),
						getTblPresupuestoDetalleP().getSelectedRow(), 3);

				getTxtConceptoPresupuestoDetalleP().setText("");
				getTxtCantidadP().setText("");
				getTxtPrecioVentaP().setText("");
			}
			calcularTotalReporte();
		} else {
			SpiritAlert.createAlert(
					"Debe seleccionar el Detalle a actualizar !!!",
					SpiritAlert.WARNING);
		}
	}

	public void authorize() {
		try {
			String estadoLetra = presupuesto.getEstado();
			EstadoPresupuesto estado = EstadoPresupuesto.getEstadoPresupuestoByLetra(estadoLetra);
			//if (presupuesto.getEstado().equals(ESTADO_FACTURADO)) {
			if (estado == EstadoPresupuesto.FACTURADO) {
				int opcion = JOptionPane.showOptionDialog(null,
						"¿Autoriza que el presupuesto pueda cambiarse?",
						"Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					//presupuesto.setEstado(ESTADO_COTIZADO);
					presupuesto.setEstado(EstadoPresupuesto.COTIZADO.getLetra());
					SessionServiceLocator.getPresupuestoSessionService()
							.savePresupuesto(presupuesto);
					SpiritAlert.createAlert(
							"El presupuesto ya puede ser modificado",
							SpiritAlert.INFORMATION);
				}
			} else {
				int opcion = JOptionPane
						.showOptionDialog(
								null,
								"¿Desea cambiar el estado del presupuesto a FACTURADO?",
								"Confirmación", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					//presupuesto.setEstado(ESTADO_FACTURADO);
					presupuesto.setEstado(EstadoPresupuesto.FACTURADO.getLetra());
					
					java.util.Date fechaAprobacion = getCmbFechaAprobacion().getDate();
					if(fechaAprobacion == null){
						presupuesto.setFechaAceptacion(new java.sql.Timestamp(Utilitarios.calendarHoy().getTimeInMillis()));
					}
					
					SessionServiceLocator.getPresupuestoSessionService()
							.savePresupuesto(presupuesto);
					SpiritAlert.createAlert(
							"El presupuesto cambio a Facturado",
							SpiritAlert.INFORMATION);
				}
			}
			productoClienteList.clear();
			showSaveMode();

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private Map<Integer,PresupuestoDetalleIf> verificacionDetallesTipoOrden() {
		Map<Integer,PresupuestoDetalleIf> detallesNoValidosMap = new HashMap<Integer,PresupuestoDetalleIf>();
		int i = 0;
		
		for ( PresupuestoDetalleIf presupuestoDetalle : presupuestoDetalleColeccion ){
			
			ProductoIf producto = mapaProductos.get(presupuestoDetalle.getProductoId());
			GenericoIf generico = mapaGenericos.get(producto.getGenericoId());
			TipoOrdenIf tipoOrden = (TipoOrdenIf) getCmbTipoOrden().getSelectedItem();
			if (!tipoOrden.getTipo().equals(generico.getMediosProduccion())) {
				detallesNoValidosMap.put(i, presupuestoDetalle);
				i++;
			}
		}

		return detallesNoValidosMap;
	}
	
	private Map<Integer,PresupuestoDetalleIf> verificacionReporteTipoOrden() {
		Map<Integer,PresupuestoDetalleIf> detallesNoValidosMap = new HashMap<Integer,PresupuestoDetalleIf>();
		int i = 0;
		
		for ( PresupuestoDetalleIf presupuestoDetalle : presupuestoDetalleColeccionP ){
			
			ProductoIf producto = mapaProductos.get(presupuestoDetalle.getProductoId());
			GenericoIf generico = mapaGenericos.get(producto.getGenericoId());
			TipoOrdenIf tipoOrden = (TipoOrdenIf) getCmbTipoOrden().getSelectedItem();
			if (!tipoOrden.getTipo().equals(generico.getMediosProduccion())) {
				detallesNoValidosMap.put(i, presupuestoDetalle);
				i++;
			}
		}

		return detallesNoValidosMap;
	}

	private void removerDetallesNoValidos(Map<Integer,PresupuestoDetalleIf> detallesNoValidosMap, Map<Integer, PresupuestoDetalleIf> reporteNoValidosMap) {
		for ( Integer indice : detallesNoValidosMap.keySet() ){
			PresupuestoDetalleIf presupuestoDetalle = detallesNoValidosMap.get(indice);
			presupuestoDetalleColeccion.remove(presupuestoDetalle);			
		}
		for ( Integer indice : reporteNoValidosMap.keySet() ){
			PresupuestoDetalleIf presupuestoDetalle = reporteNoValidosMap.get(indice);
			presupuestoDetalleColeccionP.remove(presupuestoDetalle);
		}
	}

	private Tarea tarea;

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	
	public DefaultListModel getModelProductoCliente() {
		return modelProductoCliente;
	}

	public void setModelProductoCliente(DefaultListModel modelProductoCliente) {
		this.modelProductoCliente = modelProductoCliente;
	}
}