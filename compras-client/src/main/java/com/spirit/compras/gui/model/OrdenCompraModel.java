package com.spirit.compras.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.bpm.process.elements.Tarea;
import com.spirit.bpm.process.gui.BpmPanel;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.OrdenCompraData;
import com.spirit.compras.entity.OrdenCompraDetalleData;
import com.spirit.compras.entity.OrdenCompraDetalleIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraDetalleIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.compras.gui.criteria.OrdenCompraCriteria;
import com.spirit.compras.gui.criteria.SolicitudCompraCriteria;
import com.spirit.compras.gui.panel.JPOrdenCompra;
import com.spirit.compras.handler.EstadoOrdenCompra;
import com.spirit.compras.reportesData.OrdenCompraDetalleReporteData;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.ClienteRetencionIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.ServiceInstantiationException;
import com.spirit.exception.UnknownServiceException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.PresupuestoProductoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class OrdenCompraModel extends JPOrdenCompra implements BpmPanel {
	
	private static final long serialVersionUID = -8353135374105991424L;
	
	private static final String PRODUCTO = "P";
	private static final String NOMBRE_TIPO_COMPRA_LOCAL = "LOCAL";
	private static final String TIPO_COMPRA_LOCAL = NOMBRE_TIPO_COMPRA_LOCAL
			.substring(0, 1);
	private static final String NOMBRE_TIPO_COMPRA_IMPORTACION = "IMPORTACIÓN";
	private static final String OPCION_NO = "N";
	private static final String OPERACION_AGREGAR = "AGREGAR";
	private static final String AGREGAR = OPERACION_AGREGAR.substring(1, 2);
	private static final String OPERACION_ACTUALIZAR = "ACTUALIZAR";
	private static final String ACTUALIZAR = OPERACION_ACTUALIZAR.substring(1,2);
	private static final int MAX_LONGITUD_OBSERVACION = 3000;
	private static final String TIPO_REFERENCIA_PRESUPUESTO = "P";
	private static final String TIPO_REFERENCIA_ORDEN_MEDIOS = "O";
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DecimalFormat formatoEntero = new DecimalFormat("###0");
	private static final String NO_ES_REPORTE = "N";
	private double totalValor = 0.00;
	private double totalDescuentoEspecial = 0.00;
	private double totalDescuento = 0.00;
	private double totalBonificacion = 0.00;
	private double totalIVA = 0.00;
	private double totalICE = 0.00;
	private double totalOtrosImpuestos = 0.00;
	private double grandTotal = 0.00;
	protected Double IVA = Parametros.getIVA() / 100;
	protected JDPopupFinderModel popupFinder;
	private ProductoCriteria productoCriteria;
	protected ClienteOficinaCriteria clienteOficinaCriteria;
	ArrayList lista;
	private SolicitudCompraIf solicitudCompraIf;
	private List<OrdenCompraDetalleIf> ordenCompraDetalleColeccion = new ArrayList<OrdenCompraDetalleIf>();
	Vector<OrdenCompraDetalleIf> ordenCompraDetalleEliminadas = new Vector<OrdenCompraDetalleIf>();
	private OrdenCompraDetalleIf ordenCompraDetalleForUpdate;
	private ClienteOficinaIf proveedorIf;
	private ProductoIf productoIf;
	private GenericoIf genericoIf;
	DefaultTableModel modelOrdenCompraDetalle;
	private Map productosLocal = new HashMap();
	private OrdenCompraIf ordenCompra;
	Long idProductoTemp = 0L;
	String codigoProductoTemp = "";
	private static final int MAX_LONGITUD_CODIGO = 10;
	private static final int MAX_LONGITUD_CANTIDAD = 8;
	private static final int MAX_LONGITUD_VALOR = 22;
	private static final int MAX_LONGITUD_DESCUENTO = 6;
	private static final int MAX_LONGITUD_OTRO_IMPUESTO = 3;
	private static final int MAX_LONGITUD_DESCRIPCION = 3000;
	long idTareaOrdenCompra = 0L;
	private static final String TIPO_MEDIOS = "M";
	private Vector<OrdenCompraDetalleReporteData> ordenCompraDetalleDataColeccion = new Vector<OrdenCompraDetalleReporteData>();
	private BigDecimal suma;
	private BigDecimal descuentoEspecial;
	private BigDecimal descuento;
	private BigDecimal bonificacion;
	private BigDecimal iva;
	private BigDecimal total;
	private String si = "Si";
	private String no = "No";
	private Object[] options = { si, no };
	protected JPopupMenu popup = new JPopupMenu();
	protected JMenuItem menuItem;
	

	public OrdenCompraModel() {
		anchoColumnasTabla();
		initKeyListeners();
		setSorterTable(getTblOrdenCompraDetalle());
		addPopupMenu();
		cargarCombos();
		initListeners();
		setSorterTable(getTblOrdenCompraDetalle());
		getTblOrdenCompraDetalle().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		this.clean();
		this.showSaveMode();
		new HotKeyComponent(this);
	}

	public OrdenCompraModel(SolicitudCompraIf solicitudCompraIf,
			long idTareaOrdenCompra) {
		anchoColumnasTabla();
		initKeyListeners();
		addPopupMenu();
		cargarCombos();
		initListeners();
		setSorterTable(getTblOrdenCompraDetalle());
		getTblOrdenCompraDetalle().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		agregarOrdenesCompraDetalleDesdeSolicitudCompra(solicitudCompraIf);
		new HotKeyComponent(this);
		this.idTareaOrdenCompra = idTareaOrdenCompra;
	}

	public OrdenCompraModel(Object ordenSeleccionada, long idTareaOrdenCompra) {
		anchoColumnasTabla();
		initKeyListeners();
		addPopupMenu();
		cargarCombos();
		initListeners();
		setSorterTable(getTblOrdenCompraDetalle());
		getTblOrdenCompraDetalle().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		new HotKeyComponent(this);
		getSelectedObject(ordenSeleccionada);
		this.idTareaOrdenCompra = idTareaOrdenCompra;
	}

	public void anchoColumnasTabla() {
		getTblOrdenCompraDetalle().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblOrdenCompraDetalle().getColumnModel()
				.getColumn(0);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblOrdenCompraDetalle().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblOrdenCompraDetalle().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblOrdenCompraDetalle().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblOrdenCompraDetalle().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblOrdenCompraDetalle().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblOrdenCompraDetalle().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblOrdenCompraDetalle().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblOrdenCompraDetalle().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblOrdenCompraDetalle().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(70);
	}

	private void initKeyListeners() {
		getBtnBuscarProveedor().setToolTipText("Buscar proveedor");
		getBtnBuscarProveedor().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarSolicitudCompra().setToolTipText(
				"Buscar solicitud de compra");
		getBtnBuscarSolicitudCompra().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarProducto().setToolTipText("Buscar producto");
		getBtnBuscarProducto().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnAgregarDetalle().setToolTipText("Agregar detalle");
		getBtnAgregarDetalle().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarDetalle().setToolTipText("Actualizar detalle");
		getBtnActualizarDetalle()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnEliminarDetalle().setToolTipText("Eliminar detalle");
		getBtnEliminarDetalle()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnAgregarDetalle().setText("");
		getBtnActualizarDetalle().setText("");
		getBtnEliminarDetalle().setText("");

		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblOrdenCompraDetalle().getColumnModel().getColumn(1)
				.setCellRenderer(tableCellRenderer);
		getTblOrdenCompraDetalle().getColumnModel().getColumn(2)
				.setCellRenderer(tableCellRenderer);
		getTblOrdenCompraDetalle().getColumnModel().getColumn(3)
				.setCellRenderer(tableCellRenderer);
		getTblOrdenCompraDetalle().getColumnModel().getColumn(4)
				.setCellRenderer(tableCellRenderer);
		getTblOrdenCompraDetalle().getColumnModel().getColumn(5)
				.setCellRenderer(tableCellRenderer);
		getTblOrdenCompraDetalle().getColumnModel().getColumn(6)
				.setCellRenderer(tableCellRenderer);
		getTblOrdenCompraDetalle().getColumnModel().getColumn(7)
				.setCellRenderer(tableCellRenderer);
		getTblOrdenCompraDetalle().getColumnModel().getColumn(8)
			.setCellRenderer(tableCellRenderer);
		getTblOrdenCompraDetalle().getColumnModel().getColumn(9)
		.setCellRenderer(tableCellRenderer);

		getTxtSolicitudCompra().addKeyListener(
				new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtObservacion().addKeyListener(
				new TextChecker(MAX_LONGITUD_OBSERVACION));
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
		getCmbFecha().setLocale(Utilitarios.esLocale);
		getCmbFechaRecepcion().setLocale(Utilitarios.esLocale);
		getCmbFechaVencimiento().setLocale(Utilitarios.esLocale);
		getCmbFecha().setEditable(false);
		getCmbFechaRecepcion().setEditable(false);
		getCmbFechaVencimiento().setEditable(false);
		getCmbFecha().setShowNoneButton(false);
		getCmbFechaRecepcion().setShowNoneButton(false);
		getCmbFechaVencimiento().setShowNoneButton(false);
	}

	private void addPopupMenu() {
		menuItem = new JMenuItem(
				"<html><font color=red>Eliminar detalle</font></html>");
		menuItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				eliminarOrdenCompraDetalle();
			}
		});
		popup.add(menuItem);
		getTblOrdenCompraDetalle().add(popup);
		getTblOrdenCompraDetalle().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON3)
					if (getMode() == SpiritMode.SAVE_MODE
							|| getMode() == SpiritMode.UPDATE_MODE)
						popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	private void cargarCombos() {
		// Combos estáticos
		/*getCmbEstado().addItem(OrdenCompraModel.NOMBRE_ESTADO_INACTIVA);
		getCmbEstado().addItem(OrdenCompraModel.NOMBRE_ESTADO_PENDIENTE);
		getCmbEstado().addItem(OrdenCompraModel.NOMBRE_ESTADO_ENVIADA);
		getCmbEstado().addItem(OrdenCompraModel.NOMBRE_ESTADO_INGRESADA);*/
		DefaultComboBoxModel modelo = new DefaultComboBoxModel(EstadoOrdenCompra.values());
		getCmbEstado().setModel(modelo);
		getCmbTipoCompra().addItem(OrdenCompraModel.NOMBRE_TIPO_COMPRA_LOCAL);
		getCmbTipoCompra().addItem(
				OrdenCompraModel.NOMBRE_TIPO_COMPRA_IMPORTACION);

		if (getMode() == SpiritMode.FIND_MODE) {
			getCmbFecha().setSelectedItem(null);
			getCmbFechaRecepcion().setSelectedItem(null);
			getCmbFechaVencimiento().setSelectedItem(null);
		}

		if (getMode() == SpiritMode.SAVE_MODE) {
			Calendar calendarInicio = new GregorianCalendar();
			Calendar now = Calendar.getInstance();
			calendarInicio.setTime(now.getTime());
			getCmbFecha().setCalendar(calendarInicio);
			getCmbFechaRecepcion().setCalendar(calendarInicio);
			getCmbFechaVencimiento().setCalendar(calendarInicio);
			// getCmbFechaVencimiento().getDateModel().setMinDate(now);
		}

		// Combos dinámicos
		cargarComboMoneda();
		cargarComboEmpleado();
		cargarComboBodega();
	}

	private void cargarComboMoneda() {
		try {
			List monedas = (List) SessionServiceLocator
					.getMonedaSessionService().getMonedaList();
			refreshCombo(getCmbMoneda(), monedas);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void cargarComboEmpleado() {
		try {
			List empleados = (List) SessionServiceLocator
					.getEmpleadoSessionService().findEmpleadoByEmpresaId(
							Parametros.getIdEmpresa());
			refreshCombo(getCmbEmpleado(), empleados);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void cargarComboBodega() {
		try {
			List bodegas = (List) SessionServiceLocator
					.getBodegaSessionService().findBodegaByOficinaId(
							Parametros.getIdOficina());
			refreshCombo(getCmbBodega(), bodegas);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void initListeners() {
		getBtnBuscarProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarProveedor();
			}
		});

		getBtnBuscarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarProducto();
			}
		});

		getBtnAgregarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarOrdenCompraDetalle();
			}
		});

		getBtnActualizarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarOrdenCompraDetalle();
			}
		});

		getBtnEliminarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarOrdenCompraDetalle();
			}
		});

		getTxtSolicitudCompra().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					buscarReferencia(true);
				}
			}
		});

		getBtnBuscarSolicitudCompra().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarReferencia(false);
			}
		});

		getTblOrdenCompraDetalle().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				selectTableRow();
			}
		});

		getTblOrdenCompraDetalle().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				selectTableRow();
			}
		});
	}

	private void selectTableRow() {
		if (getTblOrdenCompraDetalle().getSelectedRow() != -1) {
			int filaSeleccionada = getTblOrdenCompraDetalle().getSelectedRow();
			ordenCompraDetalleForUpdate = (OrdenCompraDetalleIf) ordenCompraDetalleColeccion
					.get(getTblOrdenCompraDetalle().convertRowIndexToModel(
							filaSeleccionada));
			enableOrdenCompraDetalleForUpdate(ordenCompraDetalleForUpdate);
		}
	}

	private void buscarReferencia(boolean eventoEnter) {
		solicitudCompraIf = buscarSolicitudCompra(eventoEnter);

		if (getMode() == SpiritMode.SAVE_MODE
				|| getMode() == SpiritMode.UPDATE_MODE) {
			if (ordenCompraDetalleColeccion.size() != 0) {
				Iterator it = ordenCompraDetalleColeccion.iterator();
				while (it.hasNext()) {
					OrdenCompraDetalleIf detalleRemovido = (OrdenCompraDetalleIf) it
							.next();
					if (detalleRemovido.getId() != null)
						ordenCompraDetalleEliminadas.add(detalleRemovido);
				}

				cleanTable();
				ordenCompraDetalleColeccion.clear();
			}

			if (solicitudCompraIf != null)
				cargarDetallesSolicitudCompra();
		}
	}

	public void cargarDetallesSolicitudCompra() {
		agregarOrdenesCompraDetalleDesdeSolicitudCompra(solicitudCompraIf);
	}

	private SolicitudCompraIf buscarSolicitudCompra(boolean eventoEnter) {
		SolicitudCompraIf solicitudCompra = null;
		String codigo = getTxtSolicitudCompra().getText();

		try {
			Long idProveedor = proveedorIf != null ? proveedorIf.getId() : 0;
			int tamanoLista = 0;
			Map parameterMap = new HashMap();
			if (eventoEnter) {
				parameterMap.put("codigo", "%");
				parameterMap.put("estado", "B"); // Solo las solicitudes
				// aprobadas
				tamanoLista = SessionServiceLocator
						.getSolicitudCompraSessionService()
						.findSolicitudCompraByQueryAndEmpresaIdSize(
								parameterMap, Parametros.getIdEmpresa());
				if (tamanoLista > 0) {
					// parameterMap.put("oficinaId", Parametros.getIdOficina());
					/*
					 * Collection solicitudesCompra =
					 * SolicitudCompraModel.getSolicitudCompraSessionService().findSolicitudCompraByQuery(parameterMap);
					 * Iterator solicitudCompraIterator =
					 * solicitudesCompra.iterator(); if
					 * (solicitudCompraIterator.hasNext()){ solicitudCompra =
					 * (SolicitudCompraIf) solicitudCompraIterator.next(); }
					 */
					SolicitudCompraCriteria solicitudCompraCriteria = new SolicitudCompraCriteria(
							Parametros.getIdEmpresa(), 0L, false);
					solicitudCompraCriteria.setResultListSize(tamanoLista);
					solicitudCompraCriteria.setQueryBuilded(parameterMap);
					popupFinder = new JDPopupFinderModel(Parametros
							.getMainFrame(), solicitudCompraCriteria,
							JDPopupFinderModel.BUSQUEDA_TODOS);
					if (popupFinder.getElementoSeleccionado() != null)
						solicitudCompra = (SolicitudCompraIf) popupFinder
								.getElementoSeleccionado();
					popupFinder = null;
				}
			} else {
				parameterMap.put("estado", "A");
				tamanoLista = SessionServiceLocator
						.getSolicitudCompraSessionService()
						.findSolicitudCompraByQueryAndEmpresaIdSize(
								parameterMap, Parametros.getIdEmpresa(),
								idProveedor);

				if (tamanoLista == 1 && eventoEnter) {
					Iterator solicitudCompraIterator = SessionServiceLocator
							.getSolicitudCompraSessionService()
							.findSolicitudCompraByQueryAndEmpresaId(
									parameterMap, Parametros.getIdEmpresa(),
									proveedorIf.getId()).iterator();
					if (solicitudCompraIterator.hasNext())
						solicitudCompra = (SolicitudCompraIf) solicitudCompraIterator
								.next();
				} else if (tamanoLista > 1
						|| (tamanoLista == 1 && !eventoEnter)) {
					SolicitudCompraCriteria solicitudCompraCriteria = new SolicitudCompraCriteria(
							Parametros.getIdEmpresa(), proveedorIf.getId(),
							true);
					solicitudCompraCriteria.setResultListSize(tamanoLista);
					solicitudCompraCriteria.setQueryBuilded(parameterMap);
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.add(50);
					anchoColumnas.add(50);
					anchoColumnas.add(70);
					anchoColumnas.add(70);
					anchoColumnas.add(200);
					anchoColumnas.add(70);
					popupFinder = new JDPopupFinderModel(Parametros
							.getMainFrame(), solicitudCompraCriteria,
							JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas,
							null);
					if (popupFinder.getElementoSeleccionado() != null)
						solicitudCompra = (SolicitudCompraIf) popupFinder
								.getElementoSeleccionado();
					popupFinder = null;
				} else if (tamanoLista == 0) {
					SpiritAlert.createAlert("No se encontraron registros",
							SpiritAlert.WARNING);
				}
			}

			/*
			 * if (solicitudCompra == null) { if (tamanoLista <= 0 &&
			 * eventoEnter) SpiritAlert.createAlert("No se halló la solicitud de
			 * compra", SpiritAlert.WARNING); } else {
			 */
			if (solicitudCompra != null) {
				getCmbEmpleado().setSelectedIndex(
						ComboBoxComponent.getIndexToSelectItem(
								getCmbEmpleado(), solicitudCompra
										.getEmpleadoId()));
				getCmbEmpleado().repaint();
				getCmbEmpleado().validate();
				getTxtObservacion().setText(solicitudCompra.getObservacion());
				getTxtSolicitudCompra().setText(solicitudCompra.getCodigo());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de información",
					SpiritAlert.ERROR);
		}

		return solicitudCompra;
	}

	public void getSelectedObject(Object ordenSeleccionada) {
		ordenCompra = (OrdenCompraIf) ordenSeleccionada;
		this.showUpdateMode();
		cleanTable();

		try {
			proveedorIf = SessionServiceLocator
					.getClienteOficinaSessionService().getClienteOficina(
							ordenCompra.getProveedorId());
			getTxtCodigo().setText(ordenCompra.getCodigo());
			getTxtRevision().setText(ordenCompra.getRevision());
			getCmbFecha().setDate(ordenCompra.getFecha());
			getCmbFechaRecepcion().setDate(ordenCompra.getFechaRecepcion());
			getCmbFechaVencimiento().setDate(ordenCompra.getFechaVencimiento());
			ClienteIf clienteIf = (ClienteIf) SessionServiceLocator
					.getClienteSessionService().findClienteByClienteOficinaId(
							proveedorIf.getId()).iterator().next();
			getTxtProveedor().setText(
					clienteIf.getIdentificacion() + " - "
							+ proveedorIf.getCodigo() + " - "
							+ clienteIf.getNombreLegal());
			getCmbMoneda().setSelectedIndex(
					ComboBoxComponent.getIndexToSelectItem(getCmbMoneda(),
							ordenCompra.getMonedaId()));

			String estadoLetra = ordenCompra.getEstado();
			EstadoOrdenCompra estado = EstadoOrdenCompra.getEstadoOrdenCompraByLetra(estadoLetra); 
			getCmbEstado().setSelectedItem(estado);
			
			if (OrdenCompraModel.TIPO_COMPRA_LOCAL.equals(ordenCompra
					.getLocalimportada()))
				getCmbTipoCompra().setSelectedItem(
						OrdenCompraModel.NOMBRE_TIPO_COMPRA_LOCAL);
			else
				getCmbTipoCompra().setSelectedItem(
						OrdenCompraModel.NOMBRE_TIPO_COMPRA_IMPORTACION);

			getTxtObservacion().setText(ordenCompra.getObservacion());
			getCmbEmpleado().setSelectedIndex(
					ComboBoxComponent.getIndexToSelectItem(getCmbEmpleado(),
							ordenCompra.getEmpleadoId()));

			if (ordenCompra.getBodegaId() != null)
				getCmbBodega().setSelectedIndex(
						ComboBoxComponent.getIndexToSelectItem(getCmbBodega(),
								ordenCompra.getBodegaId()));

			ordenCompraDetalleColeccion = (List) SessionServiceLocator
					.getOrdenCompraDetalleSessionService()
					.findOrdenCompraDetalleByOrdencompraId(ordenCompra.getId());

			if (proveedorIf != null)
				getBtnBuscarSolicitudCompra().setEnabled(true);

			if (ordenCompra.getSolicitudcompraId() != null) {
				solicitudCompraIf = SessionServiceLocator
						.getSolicitudCompraSessionService().getSolicitudCompra(
								ordenCompra.getSolicitudcompraId());
				getTxtSolicitudCompra().setText(solicitudCompraIf.getCodigo());

				if (solicitudCompraIf.getTipoReferencia().endsWith(
						TIPO_REFERENCIA_PRESUPUESTO)) {
					if (solicitudCompraIf.getReferencia() != null) {
						ArrayList presupuestos = (ArrayList)SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(solicitudCompraIf.getReferencia());
						if(presupuestos.size() > 0){
							PresupuestoIf presupuestoIf = (PresupuestoIf) presupuestos.iterator().next();
							getTxtPresupuesto().setText(presupuestoIf.getCodigo());
						}else{
							SpiritAlert.createAlert("¡El Presupuesto de esta Orden no existe!, pudo haber sido eliminado.",	SpiritAlert.WARNING);
						}						
					}
				}
			}

			llenarTablaDetalle();

		} catch (UnknownServiceException e1) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e1.printStackTrace();
		} catch (ServiceInstantiationException e1) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e1.printStackTrace();
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	public void llenarOrdenCompraDetalleDataColeccion() {
		suma = new BigDecimal(0);
		descuentoEspecial = new BigDecimal(0);
		descuento = new BigDecimal(0);
		bonificacion = new BigDecimal(0);
		iva = new BigDecimal(0);
		total = new BigDecimal(0);
		ordenCompraDetalleDataColeccion = null;
		ordenCompraDetalleDataColeccion = new Vector<OrdenCompraDetalleReporteData>();
		int item = 1;
		Iterator it = ordenCompraDetalleColeccion.iterator();
		
		while (it.hasNext()) {
			OrdenCompraDetalleIf ordenCompraDetalle = (OrdenCompraDetalleIf) it.next();
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
			double porcentajeDescuentoEspecial = ordenCompraDetalle.getPorcentajeDescuentoEspecial().doubleValue() / 100;
			double descuentoEspecialTemp = subtotal * porcentajeDescuentoEspecial;			
			double descuentoSubtotal = Double.parseDouble(ordenCompraDetalle.getDescuentoAgenciaCompra().toString());
			double porcentajeBonificacion = Double.parseDouble(ordenCompraDetalle.getPorcentajeDescuentosVariosCompra().toString());
			double bonificacion = (subtotal-descuentoEspecialTemp)*(porcentajeBonificacion/100);
			double otroImpuestoSubtotal = Double.parseDouble(ordenCompraDetalle.getOtroImpuesto().toString());
			ordenCompraDetalleData.setTotal(formatoDecimal.format(Utilitarios.redondeoValor(subtotal)));
			suma = suma.add(BigDecimal.valueOf(subtotal));
			descuento = descuento.add(ordenCompraDetalle.getDescuentoAgenciaCompra());
			descuentoEspecial = descuentoEspecial.add(BigDecimal.valueOf(descuentoEspecialTemp));
			
			this.bonificacion = this.bonificacion.add(BigDecimal.valueOf(bonificacion));
			double iva = Double.parseDouble(ordenCompraDetalle.getIva().toString());
			this.iva = this.iva.add(ordenCompraDetalle.getIva());
			double ice = Double.parseDouble(ordenCompraDetalle.getIce().toString());
			subtotal = subtotal - descuentoEspecialTemp - descuentoSubtotal - bonificacion + iva + ice	+ otroImpuestoSubtotal;
			total = total.add(BigDecimal.valueOf(subtotal));
			ordenCompraDetalleDataColeccion.add(ordenCompraDetalleData);
			item++;
		}
	}

	private void llenarTablaDetalle() throws GenericBusinessException {
		suma = new BigDecimal(0);
		descuentoEspecial = new BigDecimal(0);
		descuento = new BigDecimal(0);
		bonificacion = new BigDecimal(0);
		iva = new BigDecimal(0);
		total = new BigDecimal(0);
		ordenCompraDetalleDataColeccion = null;
		ordenCompraDetalleDataColeccion = new Vector<OrdenCompraDetalleReporteData>();
		int item = 1;
		modelOrdenCompraDetalle = (DefaultTableModel) getTblOrdenCompraDetalle().getModel();
		Iterator it = ordenCompraDetalleColeccion.iterator();
		
		while (it.hasNext()) {
			OrdenCompraDetalleIf ordenCompraDetalle = (OrdenCompraDetalleIf) it.next();
			ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(ordenCompraDetalle.getProductoId());
			Vector<String> fila = new Vector<String>();
			OrdenCompraDetalleReporteData ordenCompraDetalleData = new OrdenCompraDetalleReporteData();
			ordenCompraDetalleData.setItem(String.valueOf(item));
			ordenCompraDetalleData.setDetalle(ordenCompraDetalle.getDescripcion() != null ? ordenCompraDetalle.getDescripcion() : "");
			ordenCompraDetalleData.setCantidad(ordenCompraDetalle.getCantidad().toString());
			ordenCompraDetalleData.setParcial(formatoDecimal.format(Utilitarios.redondeoValor(ordenCompraDetalle.getValor().doubleValue())));
			
			double cantidad = Double.parseDouble(ordenCompraDetalle.getCantidad().toString());
			double valor = Double.parseDouble(ordenCompraDetalle.getValor().toString());
			double subtotal = cantidad * valor;
			double porcentajeDescuentoEspecial = ordenCompraDetalle.getPorcentajeDescuentoEspecial().doubleValue() / 100;
			double descuentoEspecialTemp = subtotal * porcentajeDescuentoEspecial;
			double descuentoSubtotal = Double.parseDouble(ordenCompraDetalle.getDescuentoAgenciaCompra().toString());
			double porcentajeBonificacion = Double.parseDouble(ordenCompraDetalle.getPorcentajeDescuentosVariosCompra().toString());
			double bonificacion = (subtotal-descuentoEspecialTemp)*(porcentajeBonificacion/100);
			double otroImpuestoSubtotal = Double.parseDouble(ordenCompraDetalle.getOtroImpuesto().toString());
			ordenCompraDetalleData.setTotal(formatoDecimal.format(Utilitarios.redondeoValor(subtotal)));
			suma = suma.add(BigDecimal.valueOf(subtotal));
			descuentoEspecial = descuentoEspecial.add(BigDecimal.valueOf(descuentoEspecialTemp));
			descuento = descuento.add(ordenCompraDetalle.getDescuentoAgenciaCompra());
			this.bonificacion = this.bonificacion.add(BigDecimal.valueOf(bonificacion));
			double iva = Double.parseDouble(ordenCompraDetalle.getIva().toString());
			this.iva = this.iva.add(ordenCompraDetalle.getIva());
			double ice = Double.parseDouble(ordenCompraDetalle.getIce().toString());
			subtotal = subtotal - descuentoEspecialTemp - descuentoSubtotal - bonificacion + iva + ice	+ otroImpuestoSubtotal;
			total = total.add(BigDecimal.valueOf(subtotal));
			
			GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(producto.getGenericoId());
			PresentacionIf presentacion = null;
			
			if (producto.getPresentacionId() != null)
				presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(producto.getPresentacionId());
			
			String productoNombre = /* producto.getCodigo() + " - " + */generico.getNombre();
			
			if (presentacion != null)
				productoNombre += " - " + presentacion.getNombre();
			
			fila.add(productoNombre);
			fila.add(String.valueOf(Double.valueOf(cantidad).intValue()));
			fila.add(String.valueOf(valor));
			fila.add(formatoDecimal.format(descuentoEspecialTemp));
			fila.add(formatoDecimal.format(descuentoSubtotal));
			fila.add(formatoDecimal.format(bonificacion));
			fila.add(formatoDecimal.format(iva));
			fila.add(formatoDecimal.format(ice));
			fila.add(formatoDecimal.format(otroImpuestoSubtotal));
			fila.add(formatoDecimal.format(subtotal));
			modelOrdenCompraDetalle.addRow(fila);

			ordenCompraDetalleDataColeccion.add(ordenCompraDetalleData);
			item++;
		}

		actualizarTotales();
	}

	private void buscarProveedor() {
		Long idCorporacion = 0L;
		Long idCliente = 0L;
		String tipoCliente = "PR";
		String tituloVentanaBusqueda = "Proveedores";
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.addElement(10);
		anchoColumnas.addElement(350);

		clienteOficinaCriteria = new ClienteOficinaCriteria(
				tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente,
				"", false);
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
				clienteOficinaCriteria,
				JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if (popupFinder.getElementoSeleccionado() != null)
			setProveedor(popupFinder.getElementoSeleccionado());
	}

	private void buscarProducto() {
		String mmpg = "";
		productoCriteria = new ProductoCriteria("Producto",
				proveedorIf.getId(), "A", "P", "A", false, mmpg);
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
				productoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if (popupFinder.getElementoSeleccionado() != null) {
			productoIf = (ProductoIf) popupFinder.getElementoSeleccionado();
			// if (!productoRepetido())
			setProductoDetalle();
			// else
			// SpiritAlert.createAlert("El producto seleccionado ya consta en el
			// detalle!!!", SpiritAlert.WARNING);
		}
	}

	private void setProveedor(Object proveedorObjeto) {
		// proveedorIf = (ClienteOficinaIf)
		// popupFinder.getElementoSeleccionado();
		proveedorIf = (ClienteOficinaIf) proveedorObjeto;
		try {
			Collection proveedores = SessionServiceLocator
					.getClienteSessionService().findClienteByClienteOficinaId(
							proveedorIf.getId());
			if (proveedores.size() > 0) {
				ClienteIf proveedor = (ClienteIf) proveedores.iterator().next();
				getTxtProveedor().setText(
						proveedor.getIdentificacion() + " - "
								+ proveedor.getNombreLegal() + " - "
								+ proveedorIf.getDescripcion());
				getBtnBuscarProducto().setEnabled(true);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}

		if (proveedorIf != null) {
			getBtnBuscarSolicitudCompra().setEnabled(true);
			getTxtProducto().setText("");
			getBtnBuscarProducto().setEnabled(true);
			// solicitudCompraIf = null;
			// getTxtSolicitudCompra().setText("");
			cleanTable();
		}
	}

	private void setProductoDetalle() {
		productoIf = (ProductoIf) popupFinder.getElementoSeleccionado();
		getTxtProducto().setText(productoIf.getCodigo());

		try {
			genericoIf = SessionServiceLocator.getGenericoSessionService()
					.getGenerico(productoIf.getGenericoId());
			if ("S".equals(genericoIf.getUsaLote())) {
				PresentacionIf presentacion = SessionServiceLocator
						.getPresentacionSessionService().getPresentacion(
								productoIf.getPresentacionId());
				getTxtProducto().setText(
						productoIf.getCodigo() + " - " + genericoIf.getNombre()
								+ " - " + presentacion.getNombre());
			} else
				getTxtProducto()
						.setText(
								productoIf.getCodigo() + " - "
										+ genericoIf.getNombre());

			/*
			 * if (genericoIf.getCambioDescripcion() != null &&
			 * genericoIf.getCambioDescripcion().equals("S")) {
			 * getTxtProducto().setEnabled(true);
			 * getTxtProducto().setEditable(true); }
			 */
			getTxtCantidad().setText("");
			getTxtCantidad().setEnabled(true);
			getTxtCantidad().setEditable(true);
			getTxtValor().setText("");
			getTxtValor().setEnabled(true);
			getTxtValor().setEditable(true);
			getTxtPorcentajeDescuentoAgencia().setText("0");
			getTxtPorcentajeDescuentoAgencia().setEnabled(true);
			getTxtPorcentajeDescuentoAgencia().setEditable(true);
			getTxtPorcentajeDescuentoEspecial().setText("0");
			getTxtPorcentajeDescuentoEspecial().setEnabled(true);
			getTxtPorcentajeDescuentoEspecial().setEditable(true);
			getTxtPorcentajeDescuentosVarios().setText("0");
			getTxtPorcentajeDescuentosVarios().setEnabled(true);
			getTxtPorcentajeDescuentosVarios().setEditable(true);
			getTxtPorcentajeOtroImpuesto().setText("0");
			getTxtPorcentajeOtroImpuesto().setEnabled(true);
			getTxtPorcentajeOtroImpuesto().setEditable(true);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void agregarOrdenesCompraDetalleDesdeSolicitudCompra(
			SolicitudCompraIf solicitudCompra) {
		try {
			PresupuestoIf presupuesto = null;
			OrdenMedioIf ordenMedios = null;
			String tipoReferencia = solicitudCompra.getTipoReferencia();

			if (tipoReferencia.equals(TIPO_REFERENCIA_PRESUPUESTO) || tipoReferencia.equals(TIPO_REFERENCIA_ORDEN_MEDIOS)) {
				Map parameterMap = new HashMap();
				parameterMap.put("codigo", solicitudCompra.getReferencia());
				Iterator referenciaIterator = null;

				if (tipoReferencia.equals(TIPO_REFERENCIA_PRESUPUESTO)) {
					referenciaIterator = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByQuery(parameterMap, Parametros.getIdEmpresa()).iterator();
					if (referenciaIterator.hasNext()){
						presupuesto = (PresupuestoIf) referenciaIterator.next();
					}
				
				} else if (tipoReferencia.equals(TIPO_REFERENCIA_ORDEN_MEDIOS)) {
					referenciaIterator = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQuery(parameterMap, Parametros.getIdEmpresa()).iterator();
					if (referenciaIterator.hasNext()){
						ordenMedios = (OrdenMedioIf) referenciaIterator.next();
					}
				}
			}

			Collection solicitudCompraDetalle = SessionServiceLocator
					.getSolicitudCompraDetalleSessionService()
					.findSolicitudCompraDetalleBySolicitudcompraId(solicitudCompra.getId());
			
			if (solicitudCompraDetalle.size() > 0) {
				cleanTable();
				modelOrdenCompraDetalle = (DefaultTableModel) getTblOrdenCompraDetalle().getModel();
				Iterator it = solicitudCompraDetalle.iterator();

				Map<Long, Long> mapaPresupuestoDetalle = new HashMap<Long, Long>();

				while (it.hasNext()) {
					SolicitudCompraDetalleIf solicitudCompraDetalleIf = (SolicitudCompraDetalleIf) it.next();
					productoIf = SessionServiceLocator.getProductoSessionService().getProducto(solicitudCompraDetalleIf.getProductoId());
					
					if (proveedorIf == null	|| proveedorIf.getId().compareTo(productoIf.getProveedorId()) == 0) {
						genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
						Map parameterMap = new HashMap();
						
						if (tipoReferencia.equals(TIPO_REFERENCIA_PRESUPUESTO)) {
							parameterMap.put("presupuestoId", presupuesto.getId());
							parameterMap.put("productoId", solicitudCompraDetalleIf.getProductoId());
							Iterator detallesIterator = SessionServiceLocator
									.getPresupuestoDetalleSessionService()
									.findPresupuestoDetalleByQuery(parameterMap).iterator();
							
							while (detallesIterator.hasNext()) {
								PresupuestoDetalleIf detalle = (PresupuestoDetalleIf) detallesIterator.next();
								
								if (detalle.getReporte().equals(NO_ES_REPORTE)) {
									String strValor = "0";
									String strOtroImpuesto = "0";
									String descripcionDetalle = "";
									
									if (mapaPresupuestoDetalle.get(detalle.getId()) == null) {
										strValor = detalle.getPrecioagencia().toString();
										
										descripcionDetalle = detalle.getConcepto() != null ? detalle.getConcepto() : " ";
										long cantidad = detalle.getCantidad().longValue();
										double valor = Double.parseDouble(strValor);
										double porcentajeDescuentoEspecial = detalle.getPorcentajeDescuentoEspecialCompra().doubleValue() / 100;
										double descuento = detalle.getPorcentajeDescuentoAgenciaCompra().doubleValue() / 100;
										double porcentajeBonificacion = detalle.getPorcentajeDescuentosVariosCompra().doubleValue() / 100;
										double otroImpuesto = Double.parseDouble(strOtroImpuesto) / 100D;
										double subtotal = cantidad * valor;
										double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
										double descuentoSubtotal = (subtotal - descuentoEspecial)	* descuento;
										double bonificacionSubtotal = (subtotal - descuentoEspecial) * porcentajeBonificacion;
										double otroImpuestoSubtotal = (subtotal - descuentoEspecial - descuentoSubtotal - bonificacionSubtotal) * otroImpuesto;
										double iva = calcularIVA(subtotal, descuentoEspecial, descuentoSubtotal, bonificacionSubtotal);
										double ice = calcularICE(subtotal, descuentoEspecial, descuentoSubtotal, bonificacionSubtotal);
										subtotal = subtotal - descuentoEspecial - descuentoSubtotal - bonificacionSubtotal + iva + ice	+ otroImpuestoSubtotal;

										// Agregar ordenCompraDetalle a la coleccion
										OrdenCompraDetalleData data = new OrdenCompraDetalleData();
										data.setProductoId(productoIf.getId());
										data.setDescripcion(descripcionDetalle);
										data.setCantidad(Long.valueOf(cantidad));
										data.setValor(BigDecimal.valueOf(Double.valueOf(valor)));
										data.setDescuentoAgenciaCompra(BigDecimal.valueOf(Double.valueOf(descuentoSubtotal)));
										data.setPorcentajeDescuentosVariosCompra(detalle.getPorcentajeDescuentosVariosCompra());
										data.setIva(BigDecimal.valueOf(Double.valueOf(iva)));
										data.setIce(BigDecimal.valueOf(Double.valueOf(ice)));
										data.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf(otroImpuestoSubtotal)));
										data.setPorcentajeDescuentoEspecial(detalle.getPorcentajeDescuentoEspecialCompra());
										ordenCompraDetalleColeccion.add(data);

										Vector<String> fila = new Vector<String>();
										if ("S".equals(genericoIf.getUsaLote())) {
											PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService()
													.getPresentacion(productoIf.getPresentacionId());
											fila.add(productoIf.getCodigo()
													+ " - " + genericoIf.getNombre()
													+ " - " + presentacion.getNombre());
										} else
											fila.add(productoIf.getCodigo()	+ " - "	+ genericoIf.getNombre());
										
										fila.add(formatoDecimal.format(detalle.getCantidad()));
										fila.add(formatoDecimal.format(valor));
										fila.add(formatoDecimal.format(descuentoEspecial));
										fila.add(formatoDecimal.format(descuentoSubtotal));
										fila.add(formatoDecimal.format(bonificacionSubtotal));
										fila.add(formatoDecimal.format(iva));
										fila.add(formatoDecimal.format(ice));
										fila.add(formatoDecimal.format(otroImpuestoSubtotal));
										fila.add(formatoDecimal.format(subtotal));
										productosLocal.put(productoIf.getId(), PRODUCTO);
										modelOrdenCompraDetalle.addRow(fila);

										mapaPresupuestoDetalle.put(detalle.getId(), detalle.getId());
									}
								}
							}
						}
					}
				}
				cleanOrdenCompraDetalle();
				actualizarTotales();

			} else if (tipoReferencia.equals(TIPO_REFERENCIA_ORDEN_MEDIOS)
					|| tipoReferencia.equals(TIPO_REFERENCIA_PRESUPUESTO))
				SpiritAlert.createAlert("No se puede obtener el "
						+ "detalle de la Solicitud de Compra",
						SpiritAlert.ERROR);

		} catch (Exception e) {
			SpiritAlert
					.createAlert(
							"Ocurrió un error al agregar el detalle de la Orden de Compra !!!",
							SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void agregarOrdenCompraDetalle() {
		try {
			Vector<String> fila = new Vector<String>();
			String strCantidad = getTxtCantidad().getText().trim();
			String strValor = Utilitarios.removeDecimalFormat(getTxtValor().getText()).trim();
			String strDescuentoEspecial = Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoEspecial().getText()).trim();
			String strDescuento = Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoAgencia().getText()).trim();
			String strPorcentajeBonificacion = Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentosVarios().getText()).trim();
			String strOtroImpuesto = Utilitarios.removeDecimalFormat(getTxtPorcentajeOtroImpuesto().getText()).trim();

			if ("".equals(strDescuentoEspecial))
				strDescuentoEspecial = "0";
			
			if ("".equals(strDescuento))
				strDescuento = "0";
			
			if ("".equals(strPorcentajeBonificacion))
				strPorcentajeBonificacion = "0";

			if ("".equals(strOtroImpuesto))
				strOtroImpuesto = "0";

			if (validarOrdenCompraDetalle(OrdenCompraModel.AGREGAR)) {
				long cantidad = Long.parseLong(strCantidad);
				double valor = Double.parseDouble(strValor);
				double porcentajeDescuentoEspecial = Double.parseDouble(strDescuentoEspecial) / 100D;
				double descuento = Double.parseDouble(strDescuento) / 100D;
				double porcentajeBonificacion = Double.parseDouble(strPorcentajeBonificacion);
				double otroImpuesto = Double.parseDouble(strOtroImpuesto) / 100D;
				double subtotal = cantidad * valor;
				double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
				double descuentoSubtotal = (subtotal - descuentoEspecial) * descuento;
				double bonificacionSubtotal = (subtotal - descuentoEspecial) * (porcentajeBonificacion / 100);
				double iva = calcularIVA(subtotal, descuentoEspecial, descuentoSubtotal, bonificacionSubtotal);
				double ice = calcularICE(subtotal, descuentoEspecial, descuentoSubtotal, bonificacionSubtotal);
				double otroImpuestoSubtotal = (subtotal - descuentoEspecial - descuentoSubtotal - bonificacionSubtotal) * otroImpuesto;
				subtotal = subtotal - descuentoEspecial - descuentoSubtotal - bonificacionSubtotal + iva + ice + otroImpuestoSubtotal;
				
				// Agregar ordenCompraDetalle a la coleccion
				OrdenCompraDetalleData data = new OrdenCompraDetalleData();
				data.setProductoId(productoIf.getId());
				// TODO: Añadir a data id de orden de compra
				data.setCantidad(Long.valueOf(cantidad));
				data.setValor(BigDecimal.valueOf(Double.valueOf(valor)));
				data.setDescuentoAgenciaCompra(BigDecimal.valueOf(Double.valueOf(descuentoSubtotal)));
				data.setPorcentajeDescuentosVariosCompra(BigDecimal.valueOf(Double.valueOf(porcentajeBonificacion)));
				data.setIva(BigDecimal.valueOf(Double.valueOf(iva)));
				data.setIce(BigDecimal.valueOf(Double.valueOf(ice)));
				data.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf(otroImpuestoSubtotal)));
				data.setPorcentajeDescuentoEspecial(BigDecimal.valueOf(Double.parseDouble(strDescuentoEspecial)));
				
				if (getTxtDescripcion().getText() != null) {
					data.setDescripcion(getTxtDescripcion().getText());
				}
				
				// Si es así ingreso el detalle de la orden de compra en la tabla
				ordenCompraDetalleColeccion.add(data);
				
				// agrega información a la tabla visual para el usuario.
				GenericoIf generico = SessionServiceLocator
						.getGenericoSessionService().getGenerico(
								productoIf.getGenericoId());
				
				PresentacionIf presentacion = null;
				
				if (productoIf.getPresentacionId() != null)
					presentacion = SessionServiceLocator
							.getPresentacionSessionService().getPresentacion(
									productoIf.getPresentacionId());
				
				String producto = /* productoIf.getCodigo() + " - " + */generico.getNombre();
				
				if (presentacion != null)
					producto += " - " + presentacion.getNombre();
				
				fila.add(producto);
				fila.add(formatoDecimal.format(cantidad));
				fila.add(String.valueOf(valor));
				fila.add(formatoDecimal.format(descuentoEspecial));
				fila.add(formatoDecimal.format(descuentoSubtotal));
				fila.add(formatoDecimal.format(bonificacionSubtotal));
				fila.add(formatoDecimal.format(iva));
				fila.add(formatoDecimal.format(ice));
				fila.add(formatoDecimal.format(otroImpuestoSubtotal));
				fila.add(formatoDecimal.format(subtotal));
				productosLocal.put(productoIf.getId(), PRODUCTO);

				DefaultTableModel tableModel = (DefaultTableModel) getTblOrdenCompraDetalle().getModel();
				tableModel.addRow(fila);
				cleanOrdenCompraDetalle();
			}

			actualizarTotales();

		} catch (Exception e) {
			SpiritAlert
					.createAlert(
							"Ocurrió un error al agregar el detalle de la Orden de Compra !!!",
							SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private double calcularIVA(double subtotal, double descuentoEspecial, double descuentoSubtotal, double bonificacionSubtotal) {
		double iva = 0.0;

		if ("S".equals(genericoIf.getCobraIva()))
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

	private void actualizarOrdenCompraDetalle() {
		DefaultTableModel tableModel = (DefaultTableModel) getTblOrdenCompraDetalle()
				.getModel();

		try {
			if (getTblOrdenCompraDetalle().getSelectedRow() == -1) {
				SpiritAlert.createAlert("Por favor, elija la información que desea actualizar !!!",	SpiritAlert.WARNING);
			}
			else if (validarOrdenCompraDetalle(OrdenCompraModel.ACTUALIZAR)) {
				
				OrdenCompraDetalleIf data = ordenCompraDetalleForUpdate;
				String strCantidad = getTxtCantidad().getText();
				String strValor = Utilitarios.removeDecimalFormat(getTxtValor().getText());
				String strPorcentajeDescuentoEspecial = Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoEspecial().getText());
				String strDescuento = Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentoAgencia().getText());
				String strPorcentajeBonificacion = Utilitarios.removeDecimalFormat(getTxtPorcentajeDescuentosVarios().getText());
				String strOtroImpuesto = Utilitarios.removeDecimalFormat(getTxtPorcentajeOtroImpuesto().getText());
				long cantidad = Long.parseLong(strCantidad);
				double valor = Double.parseDouble(strValor);
				double porcentajeDescuentoEspecial = 0D;
				double descuento = 0D;
				double porcentajeBonificacion = 0D;
				
				if (!strPorcentajeDescuentoEspecial.isEmpty()) {
					porcentajeDescuentoEspecial = Double.parseDouble(strPorcentajeDescuentoEspecial) / 100D;
				}
				
				if (!strDescuento.isEmpty()) {
					descuento = Double.parseDouble(strDescuento) / 100D;
				}
				
				if (!strPorcentajeBonificacion.isEmpty()) {
					porcentajeBonificacion = Double.parseDouble(strPorcentajeBonificacion);
				}
				
				double otroImpuesto = 0D;
				
				if (!strOtroImpuesto.isEmpty()) {
					otroImpuesto = Double.parseDouble(strOtroImpuesto) / 100D;
				}
				
				double subtotal = cantidad * valor;
				double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
				double descuentoSubtotal = (subtotal - descuentoEspecial) * descuento;
				double bonificacionSubtotal = (subtotal - descuentoEspecial) * (porcentajeBonificacion / 100);
				double otroImpuestoSubtotal = (subtotal - descuentoEspecial - descuentoSubtotal - bonificacionSubtotal) * otroImpuesto;
				double iva = calcularIVA(subtotal, descuentoEspecial, descuentoSubtotal, bonificacionSubtotal);
				double ice = calcularICE(subtotal, descuentoEspecial, descuentoSubtotal, bonificacionSubtotal);
				
				subtotal = subtotal - descuentoEspecial - descuentoSubtotal - bonificacionSubtotal + iva + ice	+ otroImpuestoSubtotal;
				data.setProductoId(productoIf.getId());
				
				// TODO: Añadir a data id de orden de compra
				data.setCantidad(Long.valueOf(cantidad));
				data.setValor(BigDecimal.valueOf(Double.valueOf(valor)));
				data.setDescuentoAgenciaCompra(BigDecimal.valueOf(Double.valueOf(descuentoSubtotal)));
				data.setPorcentajeDescuentosVariosCompra(BigDecimal.valueOf(Double.valueOf(porcentajeBonificacion)));
				data.setIva(BigDecimal.valueOf(Double.valueOf(iva)));
				data.setIce(BigDecimal.valueOf(Double.valueOf(ice)));
				data.setOtroImpuesto(BigDecimal.valueOf(Double.valueOf(otroImpuestoSubtotal)));
				data.setPorcentajeDescuentoEspecial(BigDecimal.valueOf(Double.parseDouble(strPorcentajeDescuentoEspecial)));
				
				if (getTxtDescripcion().getText() != null) {
					data.setDescripcion(getTxtDescripcion().getText());
				}
				
				int filaSeleccionada = getTblOrdenCompraDetalle().convertRowIndexToModel(getTblOrdenCompraDetalle().getSelectedRow());
				ordenCompraDetalleColeccion.set(filaSeleccionada, data);
				
				// actualizo informacion a la tabla visual para el usuario.
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
				PresentacionIf presentacion = null;
				
				if (productoIf.getPresentacionId() != null)
					presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
				
				String producto = /* productoIf.getCodigo() + " - " + */generico.getNombre();
				
				if (presentacion != null)
					producto += " - " + presentacion.getNombre();
				
				tableModel.setValueAt(producto, filaSeleccionada, 0);
				tableModel.setValueAt(formatoDecimal.format(cantidad), filaSeleccionada, 1);
				tableModel.setValueAt(String.valueOf(valor), filaSeleccionada, 2);
				tableModel.setValueAt(formatoDecimal.format(descuentoEspecial), filaSeleccionada, 3);
				tableModel.setValueAt(formatoDecimal.format(descuentoSubtotal), filaSeleccionada, 4);
				tableModel.setValueAt(formatoDecimal.format(bonificacionSubtotal), filaSeleccionada, 5);
				tableModel.setValueAt(formatoDecimal.format(iva), filaSeleccionada, 6);
				tableModel.setValueAt(formatoDecimal.format(ice), filaSeleccionada, 7);
				tableModel.setValueAt(formatoDecimal.format(otroImpuestoSubtotal),	filaSeleccionada, 8);
				tableModel.setValueAt(formatoDecimal.format(subtotal),	filaSeleccionada, 9);

				if (productosLocal.get(productoIf.getId()) == null) {
					productosLocal.remove(idProductoTemp);
					productosLocal.put(productoIf.getId(), "P");
				}

				actualizarTotales();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private boolean validarOrdenCompraDetalle(String tipoOperacion) {
		if ("".equals(getTxtProducto().getText().trim())) {
			SpiritAlert.createAlert("Debe ingresar un producto primero !!!",
					SpiritAlert.WARNING);
			this.getBtnBuscarProducto().grabFocus();
			this.getJtpOrdenCompra().setSelectedIndex(1);
			return false;
		}

		if ("".equals(getTxtCantidad().getText().trim())) {
			SpiritAlert.createAlert("Debe ingresar la cantidad !!!",
					SpiritAlert.WARNING);
			this.getTxtCantidad().grabFocus();
			this.getJtpOrdenCompra().setSelectedIndex(1);
			return false;
		}

		if (Double.parseDouble(getTxtCantidad().getText()) <= 0D) {
			SpiritAlert.createAlert("La cantidad debe ser mayor que 0 !!!",
					SpiritAlert.WARNING);
			this.getTxtCantidad().grabFocus();
			this.getJtpOrdenCompra().setSelectedIndex(1);
			return false;
		}

		if ("".equals(Utilitarios.removeDecimalFormat(getTxtValor().getText()).trim())) {
			SpiritAlert.createAlert("Debe ingresar el valor !!!",
					SpiritAlert.WARNING);
			this.getTxtValor().grabFocus();
			this.getJtpOrdenCompra().setSelectedIndex(1);
			return false;
		}

		if (Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtValor().getText())) <= 0D) {
			SpiritAlert.createAlert("El valor debe ser mayor que 0 !!!",
					SpiritAlert.WARNING);
			this.getTxtValor().grabFocus();
			this.getJtpOrdenCompra().setSelectedIndex(1);
			return false;
		} 
		
		if(!getTxtPorcentajeDescuentoEspecial().getText().equals("") && Double.valueOf(getTxtPorcentajeDescuentoEspecial().getText().replaceAll(",", "")) < 0){
			SpiritAlert.createAlert("¡El Porcentaje de Descuento Especial debe ser mayor que cero!",SpiritAlert.WARNING);
			getTxtPorcentajeDescuentoEspecial().grabFocus();
			return false;
		}else if(!getTxtPorcentajeDescuentoEspecial().getText().equals("") && Double.valueOf(getTxtPorcentajeDescuentoEspecial().getText().replaceAll(",", "")) > 100){
			SpiritAlert.createAlert("¡El Porcentaje de Descuento Especial no puede ser mayor que 100%!",SpiritAlert.WARNING);
			getTxtPorcentajeDescuentoEspecial().grabFocus();
			return false;
		}
		
		if(!getTxtPorcentajeDescuentoAgencia().getText().equals("") && Double.valueOf(getTxtPorcentajeDescuentoAgencia().getText().replaceAll(",", "")) < 0){
			SpiritAlert.createAlert("¡El Porcentaje de Descuento de Agencia debe ser mayor que cero!",SpiritAlert.WARNING);
			getTxtPorcentajeDescuentoAgencia().grabFocus();
			return false;
		}else if(!getTxtPorcentajeDescuentoAgencia().getText().equals("") && Double.valueOf(getTxtPorcentajeDescuentoAgencia().getText().replaceAll(",", "")) > 100){
			SpiritAlert.createAlert("¡El Porcentaje de Descuento de Agencia no puede ser mayor que 100%!",SpiritAlert.WARNING);
			getTxtPorcentajeDescuentoAgencia().grabFocus();
			return false;
		}
		
		if(!getTxtPorcentajeDescuentosVarios().getText().equals("") && Double.valueOf(getTxtPorcentajeDescuentosVarios().getText().replaceAll(",", "")) < 0){
			SpiritAlert.createAlert("¡El Porcentaje de Descuentos Varios debe ser mayor que cero!",SpiritAlert.WARNING);
			getTxtPorcentajeDescuentosVarios().grabFocus();
			return false;
		}else if(!getTxtPorcentajeDescuentosVarios().getText().equals("") && Double.valueOf(getTxtPorcentajeDescuentosVarios().getText().replaceAll(",", "")) > 100){
			SpiritAlert.createAlert("¡El Porcentaje de Descuentos Varios no puede ser mayor que 100%!",SpiritAlert.WARNING);
			getTxtPorcentajeDescuentosVarios().grabFocus();
			return false;
		}

		return true;
	}

	private void enableOrdenCompraDetalleForUpdate(
			OrdenCompraDetalleIf ordenCompraDetalle) {
		try {
			productoIf = SessionServiceLocator.getProductoSessionService().getProducto(ordenCompraDetalle.getProductoId());
			genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
			idProductoTemp = productoIf.getId();
			codigoProductoTemp = productoIf.getCodigo();

			if ("S".equals(genericoIf.getUsaLote())) {
				PresentacionIf presentacion = SessionServiceLocator
						.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
				getTxtProducto().setText(
						productoIf.getCodigo() + " - " + genericoIf.getNombre()	+ " - " + presentacion.getNombre());
			} else
				getTxtProducto().setText(productoIf.getCodigo() + " - "	+ genericoIf.getNombre());
			
			Double cantidad = ordenCompraDetalle.getCantidad().doubleValue();
			getTxtCantidad().setText(String.valueOf(cantidad.intValue()));
			Double valor = ordenCompraDetalle.getValor().doubleValue();
			getTxtValor().setText(String.valueOf(valor));
			double subtotal = valor * cantidad;
			
			double porcentajeDescuentoEspecial = ordenCompraDetalle.getPorcentajeDescuentoEspecial().doubleValue() / 100;
			double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
			getTxtPorcentajeDescuentoEspecial().setText(formatoDecimal.format(ordenCompraDetalle.getPorcentajeDescuentoEspecial()));
			
			String strDescuento = ordenCompraDetalle.getDescuentoAgenciaCompra().toString();
			Double descuento = 0D;
			if(subtotal != 0){
				descuento = (Double.parseDouble(strDescuento) * 100D) / (subtotal - descuentoEspecial);
			}
			getTxtPorcentajeDescuentoAgencia().setText(formatoDecimal.format(descuento));
			
			getTxtPorcentajeDescuentosVarios().setText(formatoDecimal.format(ordenCompraDetalle.getPorcentajeDescuentosVariosCompra()));
			double bonificacion = (subtotal - descuentoEspecial) * ordenCompraDetalle.getPorcentajeDescuentosVariosCompra().doubleValue();
			
			String strOtroImpuesto = ordenCompraDetalle.getOtroImpuesto().toString();
			Double otroImpuesto = 0D;
			if(subtotal != 0){
				otroImpuesto = (Double.parseDouble(strOtroImpuesto) * 100D / (subtotal - descuentoEspecial - Double.valueOf(strDescuento) - bonificacion));
			}
			
			if(otroImpuesto <= 0) otroImpuesto = 0D; //para quitarle el signo negativo si lo tuviera (-0.00)
			getTxtPorcentajeOtroImpuesto().setText(formatoDecimal.format(otroImpuesto));
			
			if (ordenCompraDetalle.getDescripcion() != null) {
				getTxtDescripcion().setText(ordenCompraDetalle.getDescripcion());
			} else {
				getTxtDescripcion().setText("");
			}
			
			if(ordenCompraDetalle.getPorcentajeNegociacionDirecta() != null){
				getTxtPorcentajeNegociacionDirecta().setText(formatoDecimal.format(ordenCompraDetalle.getPorcentajeNegociacionDirecta()));
			}else{
				getTxtPorcentajeNegociacionDirecta().setText(formatoDecimal.format(0D));
			}
			
			if(ordenCompraDetalle.getFechaPublicacion() != null){
				String anio = String.valueOf(ordenCompraDetalle.getFechaPublicacion().getYear()+1900);
				String mes = String.valueOf(ordenCompraDetalle.getFechaPublicacion().getMonth()+1);
				mes = Integer.valueOf(mes) < 10 ? "0"+mes : mes;
				String dia = String.valueOf(ordenCompraDetalle.getFechaPublicacion().getDate());
				dia = Integer.valueOf(dia) < 10 ? "0"+dia : dia;
				getTxtFechaPublicacion().setText(anio+"-"+mes+"-"+dia);
			}else{
				getTxtFechaPublicacion().setText("");
			}
			
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
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",	SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void eliminarOrdenCompraDetalle() {
		modelOrdenCompraDetalle = (DefaultTableModel) getTblOrdenCompraDetalle()
				.getModel();
		int filaSeleccionada = getTblOrdenCompraDetalle()
				.convertRowIndexToModel(
						getTblOrdenCompraDetalle().getSelectedRow());

		if (filaSeleccionada != -1) {
			OrdenCompraDetalleIf ordenCompraDetalleIf = (OrdenCompraDetalleIf) ordenCompraDetalleColeccion
					.get(filaSeleccionada);

			reversarTotales(ordenCompraDetalleIf);

			OrdenCompraDetalleIf detalleRemovido = (OrdenCompraDetalleIf) ordenCompraDetalleColeccion
					.get(filaSeleccionada);
			if (detalleRemovido.getId() != null)
				ordenCompraDetalleEliminadas.add(detalleRemovido);

			ordenCompraDetalleColeccion.remove(filaSeleccionada);
			modelOrdenCompraDetalle.removeRow(filaSeleccionada);
			getTxtProducto().setText("");
			getTxtCantidad().setText("");
			getTxtValor().setText("");
			getTxtPorcentajeOtroImpuesto().setText("");
			getTxtPorcentajeDescuentoAgencia().setText("");
			getTxtPorcentajeDescuentoEspecial().setText("");
			getTxtPorcentajeDescuentosVarios().setText("");
			getTxtPorcentajeNegociacionDirecta().setText("");
			productoIf = null;
		} else
			SpiritAlert.createAlert(
					"Debe seleccionar el detalle a eliminar !!!",
					SpiritAlert.WARNING);
	}

	private void reversarTotales(OrdenCompraDetalleIf ordenCompraDetalle) {
		Double cantidad = ordenCompraDetalle.getCantidad().doubleValue();
		Double valor = ordenCompraDetalle.getValor().doubleValue();
		double subtotal = valor * cantidad;
		double porcentajeDescuentoEspecial = ordenCompraDetalle.getPorcentajeDescuentoEspecial().doubleValue() / 100;
		double descuentoEspecial = subtotal * porcentajeDescuentoEspecial;
		Double descuento = ordenCompraDetalle.getDescuentoAgenciaCompra().doubleValue();
		Double porcentajeBonificacion = ordenCompraDetalle.getPorcentajeDescuentosVariosCompra().doubleValue();
		Double bonificacion = (subtotal-descuentoEspecial)*(porcentajeBonificacion/100);
		Double iva = ordenCompraDetalle.getIva().doubleValue();
		
		totalValor = totalValor - subtotal;
		totalDescuentoEspecial = totalDescuentoEspecial - descuentoEspecial;
		totalDescuento = totalDescuento - descuento;
		totalBonificacion = totalBonificacion - bonificacion;
		totalIVA = totalIVA - iva;
		grandTotal = totalValor - totalDescuentoEspecial - totalDescuento - totalBonificacion + totalIVA;
		
		getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalValor)));
		getTxtDescuentoEspecialFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalDescuentoEspecial)));
		getTxtDescuentoAgenciaFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalDescuento)));
		getTxtDescuentosVariosFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalBonificacion)));
		getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalIVA)));
		getTxtICEFinal().setText("0.00");
		getTxtOtroImpuestoFinal().setText("0.00");
		getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(grandTotal)));
	}

	private void cleanOrdenCompraDetalle() {
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
		getTxtPorcentajeNegociacionDirecta().setText("");
	}

	private void actualizarTotales() {
		DefaultTableModel tableModel = (DefaultTableModel) getTblOrdenCompraDetalle().getModel();
		totalValor = 0.00;
		totalDescuentoEspecial = 0.00;
		totalDescuento = 0.00;
		totalBonificacion = 0.00;
		totalIVA = 0.00;
		totalICE = 0.00;
		totalOtrosImpuestos = 0.00;
		grandTotal = 0.00;

		try {
			for (int fila = 0; fila < getTblOrdenCompraDetalle().getRowCount(); fila++) {
				String strCantidad = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 1).toString().trim());
				String strValor = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 2).toString().trim());
				String strDescuentoEspecial = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 3).toString().trim());
				String strDescuento = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 4).toString().trim());
				String strBonificacion = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 5).toString().trim());
				String strIVA = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 6).toString().trim());
				String strICE = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 7).toString().trim());
				String strOtrosImpuestos = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 8).toString().trim());
				String strGrandTotal = Utilitarios.removeDecimalFormat(tableModel.getValueAt(fila, 9).toString().trim());

				if ((!strValor.equals("0")) && (!strValor.equals("0.00"))
						&& (!strValor.equals("")) && (strValor != null))
					totalValor += Double.parseDouble(strValor)
							* Double.parseDouble(strCantidad);
				if ((!strDescuento.equals("0"))
						&& (!strDescuento.equals("0.00"))
						&& (!strDescuento.equals("")) && (strDescuento != null))
					totalDescuento += Double.parseDouble(strDescuento);
				if ((!strDescuentoEspecial.equals("0"))
						&& (!strDescuentoEspecial.equals("0.00"))
						&& (!strDescuentoEspecial.equals("")) && (strDescuentoEspecial != null))
					totalDescuentoEspecial += Double.parseDouble(strDescuentoEspecial);
				if ((!strBonificacion.equals("0"))
						&& (!strBonificacion.equals("0.00"))
						&& (!strBonificacion.equals("")) && (strBonificacion != null))
					totalBonificacion += Double.parseDouble(strBonificacion);
				if ((!strIVA.equals("0")) && (!strIVA.equals("0.00"))
						&& (!strIVA.equals("")) && (strIVA != null))
					totalIVA += Double.parseDouble(strIVA);
				if ((!strICE.equals("0")) && (!strICE.equals("0.00"))
						&& (!strICE.equals("")) && (strICE != null))
					totalICE += Double.parseDouble(strICE);
				if ((!strOtrosImpuestos.equals("0"))
						&& (!strOtrosImpuestos.equals("0.00"))
						&& (!strOtrosImpuestos.equals(""))
						&& (strOtrosImpuestos != null))
					totalOtrosImpuestos += Double
							.parseDouble(strOtrosImpuestos);
				if ((!strGrandTotal.equals("0"))
						&& (!strGrandTotal.equals("0.00"))
						&& (!strGrandTotal.equals(""))
						&& (strGrandTotal != null))
					grandTotal += Double.parseDouble(strGrandTotal);
			}

			getTxtValorFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalValor)));
			getTxtDescuentoEspecialFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalDescuentoEspecial)));
			getTxtDescuentoAgenciaFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalDescuento)));
			getTxtDescuentosVariosFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalBonificacion)));
			getTxtIVAFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalIVA)));
			getTxtICEFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalICE)));
			getTxtOtroImpuestoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalOtrosImpuestos)));
			getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(grandTotal)));
			
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("¡ Ocurrió un error al actualizar los totales de la Orden de Compra !", SpiritAlert.ERROR);
		}
	}

	public void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) this
				.getTblOrdenCompraDetalle().getModel();

		for (int i = this.getTblOrdenCompraDetalle().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);

		ordenCompraDetalleColeccion.clear();
		actualizarTotales();
		cleanOrdenCompraDetalle();
		this.repaint();
	}

	public void save() {
		if (validateFields()) {
			try {
				OrdenCompraIf ordenCompraSave = registrarOrdenCompra();
				boolean existePresupuesto = false;
				guardarOrdenCompra(ordenCompraSave);

				/*
				 * if (ordenCompra.getSolicitudcompraId() != null) {
				 * solicitudCompraIf =
				 * SessionServiceLocator.getSolicitudCompraSessionService().getSolicitudCompra(ordenCompra.getSolicitudcompraId());
				 * if(solicitudCompraIf.getTipoReferencia().endsWith(TIPO_REFERENCIA_PRESUPUESTO)){
				 * if(solicitudCompraIf.getReferencia() != null){
				 * if(SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(solicitudCompraIf.getReferencia()).size() >
				 * 0){ existePresupuesto = true; } } } }
				 * 
				 * if(existePresupuesto){ guardarOrdenCompra(ordenCompraSave);
				 * }else{ int opcion = JOptionPane.showOptionDialog(null,
				 * "¿Desea asociar esta Orden a un Presupuesto?",
				 * "Confirmación", JOptionPane.YES_NO_OPTION,
				 * JOptionPane.QUESTION_MESSAGE, null, options, no); if (opcion ==
				 * JOptionPane.YES_OPTION) {
				 *  } }
				 */

			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			} catch (Exception e) {
				SpiritAlert.createAlert(
						"Ocurrió un error al guardar la Orden de Compra",
						SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}

	private void guardarOrdenCompra(OrdenCompraIf ordenCompraSave)
			throws GenericBusinessException {
		ordenCompra = SessionServiceLocator.getOrdenCompraSessionService()
				.procesarOrdenCompra(ordenCompraSave,
						ordenCompraDetalleColeccion, Parametros.getIdEmpresa(),
						idTareaOrdenCompra);
		SpiritAlert.createAlert("Orden de Compra guardada con éxito",
				SpiritAlert.INFORMATION);
		report();
		clean();
		showSaveMode();
	}

	public void update() {
		try {
			SolicitudCompraIf solicitudCompra = SessionServiceLocator.getSolicitudCompraSessionService().getSolicitudCompra(ordenCompra.getSolicitudcompraId());
			if(solicitudCompra.getTipoReferencia().equals(TIPO_REFERENCIA_PRESUPUESTO)
					|| solicitudCompra.getTipoReferencia().equals(TIPO_REFERENCIA_ORDEN_MEDIOS)){
				SpiritAlert.createAlert(
						"Para modificar la orden se debe modificar el presupuesto: " + solicitudCompra.getReferencia() + ".",	SpiritAlert.WARNING);
			}else{
				String estadoLetra = ordenCompra.getEstado();
				EstadoOrdenCompra estado = EstadoOrdenCompra.getEstadoOrdenCompraByLetra(estadoLetra);
				if ( estado != EstadoOrdenCompra.INGRESADA ){
					actualizarOrden();
				} else {
					SpiritAlert.createAlert(
						"No es posible actualizar esta Orden, porque la compra ya fue ingresada.",
						SpiritAlert.WARNING);
				}
			}		
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(),SpiritAlert.WARNING);
		} 
		
	}

	public void delete() {
		try {
			Map aMap = new HashMap();
			aMap.put("tipoOrden", "OC");
			aMap.put("ordenId", ordenCompra.getId());
			Collection ordenCompraAsociada = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByQuery(aMap);
			if(ordenCompraAsociada.size() > 0){
				SpiritAlert.createAlert("No es posible anular la orden, está asociada a una compra.", SpiritAlert.WARNING);
			}else{
				SessionServiceLocator.getOrdenCompraSessionService().eliminarOrdenCompra(ordenCompra.getId());
				SpiritAlert.createAlert("Orden de Compra anulada con éxito",SpiritAlert.INFORMATION);
				clean();
				showSaveMode();
			}			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al eliminar la Orden de Compra!",SpiritAlert.ERROR);
		}
	}

	public void authorize() {
		try {
			String estado = ordenCompra.getEstado();
			if (estado.equals(EstadoOrdenCompra.INGRESADA.getLetra())) {
				int opcion = JOptionPane.showOptionDialog(null,
						"¿Autoriza que la orden pueda cambiarse?",
						"Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					ordenCompra.setEstado(EstadoOrdenCompra.PENDIENTE.getLetra());
					SessionServiceLocator.getOrdenCompraSessionService()
							.saveOrdenCompra(ordenCompra);
					SpiritAlert.createAlert(
							"La orden ya puede ser modificada.",
							SpiritAlert.INFORMATION);
				}
			} else {
				int opcion = JOptionPane
						.showOptionDialog(
								null,
								"¿Desea cambiar el estado de la orden a INGRESADA?",
								"Confirmación", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					ordenCompra.setEstado(EstadoOrdenCompra.INGRESADA.getLetra());
									
					SessionServiceLocator.getOrdenCompraSessionService()
							.saveOrdenCompra(ordenCompra);
					SpiritAlert.createAlert(
							"La orden cambio a INGRESADA.",
							SpiritAlert.INFORMATION);
				}
			}
			showSaveMode();

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarOrden(){
		if (validateFields()) {
			try {
				OrdenCompraIf ordenCompra = registrarOrdenCompraForUpdate();
				SessionServiceLocator.getOrdenCompraSessionService()
					.actualizarOrdenCompra(ordenCompra,ordenCompraDetalleColeccion,
						ordenCompraDetalleEliminadas,idTareaOrdenCompra);
				SpiritAlert.createAlert("Orden de Compra actualizada con éxito",SpiritAlert.INFORMATION);
				report();
				clean();
				showSaveMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			}
		}
	}

	public void refresh() {
		cargarComboMoneda();
		cargarComboEmpleado();
		cargarComboBodega();
	}

	public void duplicate() {
		// TODO Auto-generated method stub
	}

	public void report() {
		try {
			if(proveedorIf == null || ordenCompra == null){
				SpiritAlert.createAlert(
						"No hay información para presentar el reporte.",
						SpiritAlert.INFORMATION);
			}else{
				int opcion = JOptionPane.showOptionDialog(null,
						"¿Desea generar el reporte de la Orden de Compra?",
						"Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					ordenCompraDetalleColeccion = null;
					ordenCompraDetalleColeccion = new Vector<OrdenCompraDetalleIf>();
					ordenCompraDetalleColeccion = (List) SessionServiceLocator.getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByOrdencompraId(ordenCompra.getId());
					llenarOrdenCompraDetalleDataColeccion();
					String fileName = "jaspers/compras/RPOrdenCompra.jasper";
					HashMap parametrosMap = new HashMap();
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
					parametrosMap.put("numeroOrden", "Orden de Contratación No. "
							+ ordenCompra.getCodigo() + " - " + ordenCompra.getRevision());

					if (ordenCompra.getSolicitudcompraId() != null) {
						SolicitudCompraIf solicitudCompra = SessionServiceLocator
								.getSolicitudCompraSessionService()
								.getSolicitudCompra(
										ordenCompra.getSolicitudcompraId());
						parametrosMap.put("numeroSolicitud", solicitudCompra
								.getCodigo());
						if ((solicitudCompra.getTipoReferencia() != null)
								&& solicitudCompra.getTipoReferencia().equals(
										TIPO_REFERENCIA_PRESUPUESTO)) {
							PresupuestoIf presupuestoIf = (PresupuestoIf) SessionServiceLocator
									.getPresupuestoSessionService()
									.findPresupuestoByCodigo(
											solicitudCompra.getReferencia())
									.iterator().next();
							parametrosMap.put("presupuesto", presupuestoIf
									.getCodigo());
							parametrosMap.put("mesPresupuesto", Utilitarios
									.getFechaMesAnioUppercase(presupuestoIf
											.getFecha()));
							if (presupuestoIf.getOrdentrabajodetId() != null) {
								OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator
										.getOrdenTrabajoDetalleSessionService()
										.getOrdenTrabajoDetalle(
												presupuestoIf
														.getOrdentrabajodetId());
								OrdenTrabajoIf ordenTrabajo = SessionServiceLocator
										.getOrdenTrabajoSessionService()
										.getOrdenTrabajo(
												ordenTrabajoDetalle.getOrdenId());
								ClienteIf cliente = (ClienteIf) SessionServiceLocator
										.getClienteSessionService()
										.findClienteByClienteOficinaId(
												ordenTrabajo.getClienteoficinaId())
										.iterator().next();
								parametrosMap.put("cliente", cliente
										.getNombreLegal());
								
								PresupuestoProductoIf presupuestoProducto = (PresupuestoProductoIf)SessionServiceLocator.getPresupuestoProductoSessionService().findPresupuestoProductoByPresupuestoId(presupuestoIf.getId()).iterator().next();
								
								ProductoClienteIf productoCliente = SessionServiceLocator
										.getProductoClienteSessionService()
										.getProductoCliente(
												presupuestoProducto
														.getProductoClienteId());
								parametrosMap.put("producto", productoCliente
										.getNombre());
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
					String fechaEmision = day
							+ " "
							+ Utilitarios.getNombreMes(Integer.parseInt(month))
									.toLowerCase() + " del " + year;
					parametrosMap.put("fechaEmision", fechaEmision);

					ClienteIf clienteIf = (ClienteIf) SessionServiceLocator
							.getClienteSessionService()
							.findClienteByClienteOficinaId(proveedorIf.getId())
							.iterator().next();
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
					
					/*descuento = ordenCompra.getDescuentoAgenciaCompra();
					bonificacion = suma.subtract(descuento).multiply(ordenCompra.getPorcentajeDescuentosVariosCompra().divide(new BigDecimal(100)));
					iva = suma.subtract(descuento).subtract(bonificacion).multiply(BigDecimal.valueOf(IVA));
					total = suma.subtract(descuento).subtract(bonificacion).add(iva);*/
					
					parametrosMap.put("suma", suma);
					
					parametrosMap.put("porcentajeDescuentoEspecial", formatoDecimal.format(ordenCompra.getPorcentajeDescuentoEspecial()));
					double porcentajeDescuentoEspecial = ordenCompra.getPorcentajeDescuentoEspecial().doubleValue() / 100;
					double descuentoEspecial = suma.doubleValue() * porcentajeDescuentoEspecial;
					parametrosMap.put("descuentoEspecial", BigDecimal.valueOf(descuentoEspecial));
					
					double porcentajeDescuentoCompra = 0D;
					if(suma.compareTo(new BigDecimal(0)) != 0){
						porcentajeDescuentoCompra = (ordenCompra.getDescuentoAgenciaCompra().doubleValue() * 100) / (suma.doubleValue() - descuentoEspecial);
					}
					parametrosMap.put("porcentajeDescuento", formatoDecimal.format(Utilitarios.redondeoValor(porcentajeDescuentoCompra)));
					parametrosMap.put("descuento", descuento);
					parametrosMap.put("porcentajeComisionAgencia", formatoDecimal.format(Utilitarios.redondeoValor(ordenCompra.getPorcentajeDescuentosVariosCompra())));
					parametrosMap.put("bonificacionOC", bonificacion);
					parametrosMap.put("iva", iva);
					parametrosMap.put("total", total);
					
					String elaboradoPor = "";
					if (ordenCompra.getEmpleadoId() != null) {
						EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenCompra.getEmpleadoId());
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
							double subTotalOrden = suma.doubleValue() - descuentoEspecial - descuento.doubleValue() - bonificacion.doubleValue();
							retencionRenta = subTotalOrden * (porcentajeRetencionRenta / 100D);
							
							parametrosMap.put("porcentajeRetencionRenta", formatoDecimal.format(porcentajeRetencionRenta));
							parametrosMap.put("retencionRenta", BigDecimal.valueOf(retencionRenta));
						}
						
						double retencionIva = 0D;
						if(sriIvaRetencion.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
							double porcentajeRetencionIva = sriIvaRetencion.getPorcentaje().doubleValue();
							double ivaOrden = iva.doubleValue();
							retencionIva = ivaOrden * (porcentajeRetencionIva / 100D);
							
							parametrosMap.put("porcentajeRetencionIva", formatoDecimal.format(porcentajeRetencionIva));
							parametrosMap.put("retencionIva", BigDecimal.valueOf(retencionIva));
						}
						
						double totalPagar = total.doubleValue() - retencionRenta - retencionIva;
						parametrosMap.put("totalPagar", BigDecimal.valueOf(totalPagar));
					}
					

					ReportModelImpl.processReport(fileName, parametrosMap,
							ordenCompraDetalleDataColeccion, true);
				}
			}

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

	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public void switchTab() {
		int selectedTab = this.getJtpOrdenCompra().getSelectedIndex();
		int tabCount = this.getJtpOrdenCompra().getTabCount();
		selectedTab++;

		if (selectedTab >= tabCount)
			selectedTab = 0;

		this.getJtpOrdenCompra().setSelectedIndex(selectedTab);
	}

	public void addDetail() {
		if (getJtpOrdenCompra().getSelectedIndex() == 1)
			agregarOrdenCompraDetalle();
	}

	public void updateDetail() {
		if (getJtpOrdenCompra().getSelectedIndex() == 1)
			actualizarOrdenCompraDetalle();
	}
	
	public void deleteDetail() {
		if (getJtpOrdenCompra().getSelectedIndex() == 1)
			eliminarOrdenCompraDetalle();
	}

	private Map buildQuery() {
		Map aMap = new HashMap();

		try {
			Long proveedorId = 0L;

			if (getCmbTipoCompra().getSelectedItem() != null) {
				if (!("".equals(getCmbTipoCompra().getSelectedItem().toString()
						.substring(0, 1)))) {
					aMap.put("localimportada", getCmbTipoCompra()
							.getSelectedItem().toString().substring(0, 1));
				}
			}

			if (getTxtCodigo().getText() != null
					&& !getTxtCodigo().getText().equals("")) {
				aMap.put("codigo", getTxtCodigo().getText() + "%");
			} else {
				aMap.put("codigo", "%");
			}

			if (proveedorIf != null) {
				proveedorId = proveedorIf.getId();
				aMap.put("proveedorId", proveedorId);
			}

			if (getCmbEstado().getSelectedItem() != null) {
				EstadoOrdenCompra estado = (EstadoOrdenCompra) getCmbEstado().getSelectedItem();
				aMap.put("estado", estado.getLetra());
				/*if (getCmbEstado().getSelectedItem().equals(
						NOMBRE_ESTADO_INACTIVA))
					aMap.put("estado", ESTADO_INACTIVA);
				else if (getCmbEstado().getSelectedItem().equals(
						NOMBRE_ESTADO_PENDIENTE))
					aMap.put("estado", ESTADO_PENDIENTE);
				else if (getCmbEstado().getSelectedItem().equals(
						NOMBRE_ESTADO_ENVIADA))
					aMap.put("estado", ESTADO_ENVIADA);
				else
					aMap.put("estado", ESTADO_INGRESADA);*/
			}

			if (getTxtPresupuesto().getText() != null && !getTxtPresupuesto().getText().equals("")) {
				if (!SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo("%" + getTxtPresupuesto().getText()).isEmpty()) {
					PresupuestoIf presupuestoIf = (PresupuestoIf) SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo("%" + getTxtPresupuesto().getText()).iterator().next();
					Map aMapSolicitudCompra = new HashMap();
					aMapSolicitudCompra.put("tipoReferencia", TIPO_REFERENCIA_PRESUPUESTO);
					aMapSolicitudCompra.put("referencia", presupuestoIf.getCodigo());
					if (!SessionServiceLocator.getSolicitudCompraSessionService().findSolicitudCompraByQuery(aMapSolicitudCompra).isEmpty()) {
						List solicitudCompraColeccion = (ArrayList)SessionServiceLocator.getSolicitudCompraSessionService().findSolicitudCompraByQuery(aMapSolicitudCompra);
						aMap.put("solicitudesCompra", solicitudCompraColeccion);
					}
				}
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}

		return aMap;
	}

	public void find() {
		try {
			Map mapa = buildQuery();
			int tamanoLista = SessionServiceLocator.getOrdenCompraSessionService()
				.getOrdenCompraByQueryListSize(mapa, Parametros.getIdEmpresa(), false);
			if (tamanoLista > 0) {
				try {
					OrdenCompraCriteria ordenCompraCriteria = new OrdenCompraCriteria(
							Parametros.getIdEmpresa(), false);
					ordenCompraCriteria.setResultListSize(tamanoLista);
					ordenCompraCriteria.setQueryBuilded(mapa);
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.add(50);
					anchoColumnas.add(180);
					anchoColumnas.add(50);
					anchoColumnas.add(50);
					anchoColumnas.add(40);
					anchoColumnas.add(150);
					popupFinder = new JDPopupFinderModel(Parametros
							.getMainFrame(), ordenCompraCriteria,
							JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas,
							null);
					if (popupFinder.getElementoSeleccionado() != null)
						getSelectedObject(popupFinder.getElementoSeleccionado());
				} catch (Exception e) {
					SpiritAlert.createAlert("Se ha producido un error",SpiritAlert.ERROR);
					e.printStackTrace();
				}
			} else {
				SpiritAlert.createAlert("No se encontraron registros",SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de información",SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en la búsqueda de información",SpiritAlert.ERROR);
		}
	}

	public boolean isEmpty() {
		return false;
	}

	public void clean() {
		cleanTable();
		ordenCompraDetalleColeccion = new Vector();
		ordenCompraDetalleEliminadas = new Vector();
		totalValor = 0.0;
		totalDescuentoEspecial = 0.0;
		totalDescuento = 0.0;
		totalBonificacion = 0.0;
		totalIVA = 0.0;
		totalICE = 0.0;
		totalOtrosImpuestos = 0.0;
		grandTotal = 0.0;
	}

	public void showFindMode() {
		getCmbTipoCompra().setBackground(Parametros.findColor);
		getTxtCodigo().setBackground(Parametros.findColor);
		getTxtProveedor().setBackground(Parametros.findColor);
		getCmbEstado().setBackground(Parametros.findColor);
		getTxtPresupuesto().setBackground(Parametros.findColor);
		getTxtPresupuesto().setEditable(true);
		getTxtPresupuesto().setText("");
		proveedorIf = null;
		this.getTxtCodigo().setText("");
		this.getTxtCodigo().setEditable(true);
		this.getTxtRevision().setText("");
		this.getTxtSolicitudCompra().setText("");
		this.getCmbFecha().setEnabled(false);
		this.getCmbFechaRecepcion().setEnabled(false);
		this.getCmbFechaVencimiento().setEnabled(false);
		this.getTxtOficina().setEnabled(false);
		this.getTxtProveedor().setText("");
		this.getTxtProveedor().setEditable(false);
		this.getBtnBuscarProveedor().setEnabled(true);
		this.getCmbMoneda().setEnabled(false);
		this.getCmbEstado().setEnabled(true);
		this.getCmbEstado().setSelectedIndex(-1);
		this.getCmbTipoCompra().setEnabled(true);
		this.getTxtObservacion().setText("");
		this.getTxtObservacion().setEnabled(false);
		this.getBtnBuscarSolicitudCompra().setEnabled(false);
		this.getCmbEmpleado().setEnabled(false);
		this.getCmbBodega().setEnabled(false);
		this.getTxtUsuario().setEnabled(false);
		this.getBtnBuscarProducto().setEnabled(false);
		this.getTxtProducto().setEditable(false);
		this.getTxtCantidad().setEnabled(false);
		this.getTxtValor().setEnabled(false);
		this.getTxtPorcentajeDescuentoAgencia().setEnabled(false);
		this.getTxtPorcentajeDescuentoEspecial().setEnabled(false);
		this.getTxtPorcentajeDescuentosVarios().setEnabled(false);
		this.getTxtPorcentajeOtroImpuesto().setEnabled(false);
		this.getBtnAgregarDetalle().setEnabled(false);
		this.getBtnActualizarDetalle().setEnabled(false);
		this.getBtnEliminarDetalle().setEnabled(false);
		this.getCmbMoneda().setSelectedItem(null);
		this.getCmbTipoCompra().setSelectedItem(null);
		this.getTxtCodigo().grabFocus();
	}

	public void showSaveMode() {
		getCmbEstado().setSelectedItem(EstadoOrdenCompra.PENDIENTE);
		//getCmbEstado().setSelectedItem(NOMBRE_ESTADO_PENDIENTE);

		getCmbTipoCompra().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().setBackground(getBackground());
		getTxtProveedor().setBackground(getBackground());
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		getTxtPresupuesto().setBackground(getBackground());
		getTxtPresupuesto().setEditable(false);
		getTxtPresupuesto().setText("");
		setSaveMode();
		proveedorIf = null;
		getTxtCodigo().setText("");
		getTxtCodigo().setEditable(false);
		getTxtRevision().setText("");
		getCmbFecha().setEnabled(true);
		getCmbFechaRecepcion().setEnabled(true);
		getCmbFechaVencimiento().setEnabled(true);
		getTxtOficina().setText(Parametros.getNombreOficina());
		getTxtOficina().setEnabled(true);
		getTxtOficina().setFocusable(false);
		getTxtUsuario().setText(Parametros.getUsuario());
		getTxtUsuario().setEnabled(true);
		getTxtObservacion().setText("");
		getTxtObservacion().setEnabled(true);
		getTxtProveedor().setText("");
		getTxtProveedor().setEditable(false);
		getTxtSolicitudCompra().setText("");
		getBtnBuscarSolicitudCompra().setEnabled(false);
		getCmbMoneda().setEnabled(true);

		if (getCmbMoneda().getItemCount() > 0)
			getCmbMoneda().setSelectedIndex(0);

		getCmbTipoCompra().setEnabled(true);

		if (getCmbTipoCompra().getItemCount() > 0)
			getCmbTipoCompra().setSelectedIndex(0);

		getCmbEmpleado().setSelectedItem(null);
		getCmbEmpleado().setEnabled(true);
		// getCmbBodega().setSelectedItem(null);
		getCmbBodega().setEnabled(true);
		getTxtProducto().setText("");
		getTxtProducto().setEditable(false);
		getBtnBuscarProducto().setEnabled(false);
		getTxtCantidad().setText("");
		getTxtCantidad().setEnabled(false);
		getTxtCantidad().setEditable(false);
		getTxtValor().setText("");
		getTxtValor().setEnabled(false);
		getTxtValor().setEditable(false);
		getTxtPorcentajeDescuentoAgencia().setText("");
		getTxtPorcentajeDescuentoAgencia().setEnabled(false);
		getTxtPorcentajeDescuentoAgencia().setEditable(false);
		getTxtPorcentajeDescuentoEspecial().setText("");
		getTxtPorcentajeDescuentoEspecial().setEnabled(false);
		getTxtPorcentajeDescuentoEspecial().setEditable(false);
		getTxtPorcentajeDescuentosVarios().setText("");
		getTxtPorcentajeDescuentosVarios().setEnabled(false);
		getTxtPorcentajeDescuentosVarios().setEditable(false);
		getTxtPorcentajeOtroImpuesto().setText("");
		getTxtPorcentajeOtroImpuesto().setEnabled(false);
		getTxtPorcentajeOtroImpuesto().setEditable(false);
		getTxtPorcentajeNegociacionDirecta().setText("");
		getTxtFechaPublicacion().setText("");
		totalValor = 0.00;
		totalDescuentoEspecial = 0.00;
		totalDescuento = 0.00;
		totalBonificacion = 0.00;
		totalIVA = 0.00;
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
		getTxtICEFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalICE)));
		getTxtICEFinal().setEditable(false);
		getTxtOtroImpuestoFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalOtrosImpuestos)));
		getTxtOtroImpuestoFinal().setEditable(false);
		getTxtTotalFinal().setText(formatoDecimal.format(Utilitarios.redondeoValor(grandTotal)));
		getTxtTotalFinal().setEditable(false);
		getBtnAgregarDetalle().setEnabled(true);
		getBtnActualizarDetalle().setEnabled(true);
		getBtnEliminarDetalle().setEnabled(true);
		cleanTable();

		// Seteo del formato de la combos de fecha
		getCmbFecha().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaRecepcion().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaVencimiento().setFormat(Utilitarios.setFechaUppercase());
		this.getCmbFecha().grabFocus();
		this.getJtpOrdenCompra().setSelectedIndex(0);
	}

	public void showUpdateMode() {
		getCmbTipoCompra().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().setBackground(getBackground());
		getTxtProveedor().setBackground(getBackground());
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		getTxtPresupuesto().setBackground(getBackground());
		getTxtPresupuesto().setEditable(false);
		getTxtPresupuesto().setText("");
		setUpdateMode();
		this.getTxtCodigo().setText("");
		this.getTxtCodigo().setEditable(false);
		this.getTxtRevision().setText("");
		this.getCmbFecha().setEnabled(true);
		this.getCmbFechaRecepcion().setEnabled(true);
		this.getCmbFechaVencimiento().setEnabled(true);
		this.getTxtOficina().setEnabled(true);
		this.getTxtOficina().setFocusable(false);
		this.getTxtProveedor().setEditable(false);
		this.getBtnBuscarProveedor().setEnabled(true);
		this.getCmbMoneda().setEnabled(true);
		this.getCmbEstado().setEnabled(true);
		this.getCmbTipoCompra().setEnabled(true);
		this.getTxtObservacion().setText("");
		this.getTxtObservacion().setEnabled(true);
		this.getBtnBuscarSolicitudCompra().setEnabled(false);
		this.getCmbEmpleado().setEnabled(true);
		this.getCmbBodega().setEnabled(true);
		this.getTxtUsuario().setEnabled(true);
		this.getBtnBuscarProducto().setEnabled(true);
		this.getTxtProducto().setEditable(false);
		this.getTxtCantidad().setEnabled(true);
		this.getTxtValor().setEnabled(true);
		this.getTxtPorcentajeDescuentoAgencia().setEnabled(true);
		this.getTxtPorcentajeDescuentoEspecial().setEnabled(true);
		this.getTxtPorcentajeDescuentosVarios().setEnabled(true);
		this.getTxtPorcentajeOtroImpuesto().setEnabled(true);
		this.getBtnAgregarDetalle().setEnabled(true);
		this.getBtnActualizarDetalle().setEnabled(true);
		this.getBtnEliminarDetalle().setEnabled(true);
		this.getCmbFecha().grabFocus();
		this.getJtpOrdenCompra().setSelectedIndex(0);
	}

	public boolean validateFields() {
		Date fechaOrdenCompra = getCmbFecha().getDate();
		Date fechaRecepcion = getCmbFechaRecepcion().getDate();
		Date fechaVencimiento = getCmbFechaVencimiento().getDate();
		String strTotalValor = Utilitarios.removeDecimalFormat(getTxtValorFinal().getText());
		Object objEmpleado = getCmbEmpleado().getSelectedItem();

		if (fechaOrdenCompra != null) {
			if (fechaRecepcion.before(fechaOrdenCompra)) {
				SpiritAlert.createAlert(
					"La fecha de recepción debe ser posterior a la fecha de la Orden de Compra !!",
					SpiritAlert.WARNING);
				this.getCmbFechaRecepcion().grabFocus();
				this.getJtpOrdenCompra().setSelectedIndex(0);
				return false;
			}

			if (fechaVencimiento.before(fechaOrdenCompra)) {
				SpiritAlert.createAlert(
					"La fecha de vencimiento debe ser posterior a la fecha de la Orden de Compra !!",
					SpiritAlert.WARNING);
				this.getCmbFechaVencimiento().grabFocus();
				this.getJtpOrdenCompra().setSelectedIndex(0);
				return false;
			}
		} else {
			SpiritAlert.createAlert(
					"La fecha de la Orden de Compra debe ser ingresada !!",
					SpiritAlert.WARNING);
			this.getCmbFecha().grabFocus();
			this.getJtpOrdenCompra().setSelectedIndex(0);
			return false;
		}

		if (proveedorIf == null) {
			SpiritAlert.createAlert("Debe seleccionar un proveedor !!",
					SpiritAlert.WARNING);
			this.getBtnBuscarProveedor().grabFocus();
			this.getJtpOrdenCompra().setSelectedIndex(0);
			return false;
		}

		/*
		 * if ("".equals(getTxtSolicitudCompra().getText()) || solicitudCompraIf ==
		 * null) { int opcion = JOptionPane.showOptionDialog(null, "¿Desea usar
		 * la Solicitud de Medios?!", "Confirmación", JOptionPane.YES_NO_OPTION,
		 * JOptionPane.QUESTION_MESSAGE, null, options, no); if (opcion ==
		 * JOptionPane.YES_OPTION) { try { solicitudCompraIf =
		 * SessionServiceLocator.getSolicitudCompraSessionService().getSolicitudCompra(new
		 * Long(2820)); } catch (GenericBusinessException e) {
		 * e.printStackTrace(); } }else{
		 * getBtnBuscarSolicitudCompra().grabFocus();
		 * getJtpOrdenCompra().setSelectedIndex(0); return false; } }
		 */

		if ("".equals(getTxtSolicitudCompra().getText())
				|| solicitudCompraIf == null) {
			SpiritAlert.createAlert("Debe seleccionar una Solicitud de Compra!",
					SpiritAlert.WARNING);
			this.getTxtSolicitudCompra().grabFocus();
			this.getJtpOrdenCompra().setSelectedIndex(0);
			return false;
		}

		if (objEmpleado == null) {
			SpiritAlert.createAlert("Debe ingresar un Empleado !!",SpiritAlert.WARNING);
			if (getCmbEmpleado().getItemCount() > 0) {
				getCmbEmpleado().setSelectedIndex(0);
			}
			getCmbEmpleado().grabFocus();
			return false;
		}

		if (Double.parseDouble(strTotalValor) <= 0) {
			SpiritAlert.createAlert("El valor total debe ser mayor que 0 !!",
					SpiritAlert.WARNING);
			this.getBtnBuscarProducto().grabFocus();
			this.getJtpOrdenCompra().setSelectedIndex(1);
			return false;
		}
		
		for(int i=0; i<ordenCompraDetalleColeccion.size(); i++){
			OrdenCompraDetalleIf ordenCompraDetalle = ordenCompraDetalleColeccion.get(i);
			for(int j=0; j<ordenCompraDetalleColeccion.size(); j++){
				OrdenCompraDetalleIf ordenCompraDetalleTemp = ordenCompraDetalleColeccion.get(j);
				if(i != j 
					&& ordenCompraDetalle.getPorcentajeDescuentosVariosCompra().compareTo(ordenCompraDetalleTemp.getPorcentajeDescuentosVariosCompra()) != 0){
					SpiritAlert.createAlert("Todos los detalles de la orden deben tener el mismo Porcentaje de Bonificación.",SpiritAlert.WARNING);
					getJtpOrdenCompra().setSelectedIndex(1);
					getTblOrdenCompraDetalle().grabFocus();
					return false;
				}
			}
		}

		return true;
	}

	private OrdenCompraIf registrarOrdenCompra()
			throws GenericBusinessException {
		OrdenCompraData data = new OrdenCompraData();
		java.sql.Date fechaCreacion = new java.sql.Date(getCmbFecha().getDate().getTime());
		String codigo = getCodigoOrdenCompra(new java.sql.Date(fechaCreacion.getYear(), fechaCreacion.getMonth(), fechaCreacion.getDate()));
		data.setCodigo(codigo);
		data.setOficinaId(Parametros.getIdOficina());
		data.setProveedorId(proveedorIf.getId());
		data.setMonedaId(((MonedaIf) getCmbMoneda().getSelectedItem()).getId());
		data.setEmpleadoId(((EmpleadoIf) getCmbEmpleado().getSelectedItem()).getId());
		data.setUsuarioId(((UsuarioIf) Parametros.getUsuarioIf()).getId());

		if (getCmbBodega().getSelectedItem() != null)
			data.setBodegaId(((BodegaIf) getCmbBodega().getSelectedItem()).getId());

		data.setLocalimportada(getCmbTipoCompra().getSelectedItem().toString().substring(0, 1));
		data.setFecha(new java.sql.Date(getCmbFecha().getDate().getTime()));
		data.setFechaRecepcion(new java.sql.Date(getCmbFechaRecepcion().getDate().getTime()));
		data.setFechaVencimiento(new java.sql.Date(getCmbFechaVencimiento().getDate().getTime()));
		
		if(getCmbEstado().getSelectedItem().equals(EstadoOrdenCompra.ENVIADA))
			data.setEstado(EstadoOrdenCompra.getLetraEstadoOrdenCompra(EstadoOrdenCompra.ENVIADA));
		else if(getCmbEstado().getSelectedItem().equals(EstadoOrdenCompra.INACTIVA))
			data.setEstado(EstadoOrdenCompra.getLetraEstadoOrdenCompra(EstadoOrdenCompra.INACTIVA));
		else if(getCmbEstado().getSelectedItem().equals(EstadoOrdenCompra.INGRESADA))
			data.setEstado(EstadoOrdenCompra.getLetraEstadoOrdenCompra(EstadoOrdenCompra.INGRESADA));
		else if(getCmbEstado().getSelectedItem().equals(EstadoOrdenCompra.PENDIENTE))
			data.setEstado(EstadoOrdenCompra.getLetraEstadoOrdenCompra(EstadoOrdenCompra.PENDIENTE));
		else if(getCmbEstado().getSelectedItem().equals(EstadoOrdenCompra.ANULADA))
			data.setEstado(EstadoOrdenCompra.getLetraEstadoOrdenCompra(EstadoOrdenCompra.ANULADA));
		
		data.setObservacion(getTxtObservacion().getText());
		data.setValor(BigDecimal.valueOf(totalValor));
		data.setDescuentoAgenciaCompra(BigDecimal.valueOf(totalDescuento));
		data.setIva(BigDecimal.valueOf(totalIVA));
		data.setIce(BigDecimal.valueOf(totalICE));
		data.setOtroImpuesto(BigDecimal.valueOf(totalOtrosImpuestos));
		data.setSolicitudcompraId(solicitudCompraIf.getId());

		if(ordenCompraDetalleColeccion.size() > 0){
			OrdenCompraDetalleIf ordenCompraDetalle = ordenCompraDetalleColeccion.get(0);
			data.setPorcentajeDescuentosVariosCompra(ordenCompraDetalle.getPorcentajeDescuentosVariosCompra());
			data.setPorcentajeDescuentoEspecial(ordenCompraDetalle.getPorcentajeDescuentoEspecial());
		}
		
		data.setRevision("01");
		
		return data;
	}

	private String getCodigoOrdenCompra(java.sql.Date fechaOrdenCompra) {
		String codigo = "";
		String anioOrdenCompra = Utilitarios.getYearFromDate(fechaOrdenCompra);
		codigo += anioOrdenCompra + "-";
		return codigo;
	}

	private OrdenCompraIf registrarOrdenCompraForUpdate() throws GenericBusinessException {
		ordenCompra.setOficinaId(Parametros.getIdOficina());
		ordenCompra.setProveedorId(proveedorIf.getId());
		ordenCompra.setMonedaId(((MonedaIf) getCmbMoneda().getSelectedItem()).getId());
		ordenCompra.setEmpleadoId(((EmpleadoIf) getCmbEmpleado().getSelectedItem()).getId());
		ordenCompra.setUsuarioId(((UsuarioIf) Parametros.getUsuarioIf()).getId());

		if (getCmbBodega().getSelectedItem() != null)
			ordenCompra.setBodegaId(((BodegaIf) getCmbBodega().getSelectedItem()).getId());

		ordenCompra.setLocalimportada(getCmbTipoCompra().getSelectedItem().toString().substring(0, 1));
		ordenCompra.setFecha(new java.sql.Date(getCmbFecha().getDate().getTime()));
		ordenCompra.setFechaRecepcion(new java.sql.Date(getCmbFechaRecepcion().getDate().getTime()));
		ordenCompra.setFechaVencimiento(new java.sql.Date(getCmbFechaVencimiento().getDate().getTime()));
		
		if(getCmbEstado().getSelectedItem().equals(EstadoOrdenCompra.ENVIADA))
			ordenCompra.setEstado(EstadoOrdenCompra.getLetraEstadoOrdenCompra(EstadoOrdenCompra.ENVIADA));
		else if(getCmbEstado().getSelectedItem().equals(EstadoOrdenCompra.INACTIVA))
			ordenCompra.setEstado(EstadoOrdenCompra.getLetraEstadoOrdenCompra(EstadoOrdenCompra.INACTIVA));
		else if(getCmbEstado().getSelectedItem().equals(EstadoOrdenCompra.INGRESADA))
			ordenCompra.setEstado(EstadoOrdenCompra.getLetraEstadoOrdenCompra(EstadoOrdenCompra.INGRESADA));
		else if(getCmbEstado().getSelectedItem().equals(EstadoOrdenCompra.PENDIENTE))
			ordenCompra.setEstado(EstadoOrdenCompra.getLetraEstadoOrdenCompra(EstadoOrdenCompra.PENDIENTE));
		else if(getCmbEstado().getSelectedItem().equals(EstadoOrdenCompra.ANULADA))
			ordenCompra.setEstado(EstadoOrdenCompra.getLetraEstadoOrdenCompra(EstadoOrdenCompra.ANULADA));
		
		ordenCompra.setObservacion(getTxtObservacion().getText());
		ordenCompra.setValor(BigDecimal.valueOf(totalValor));
		ordenCompra.setDescuentoAgenciaCompra(BigDecimal.valueOf(totalDescuento));
		ordenCompra.setIva(BigDecimal.valueOf(totalIVA));
		ordenCompra.setIce(BigDecimal.valueOf(totalICE));
		ordenCompra.setOtroImpuesto(BigDecimal.valueOf(totalOtrosImpuestos));
		ordenCompra.setSolicitudcompraId(solicitudCompraIf.getId());
		
		if(ordenCompraDetalleColeccion.size() > 0){
			OrdenCompraDetalleIf ordenCompraDetalle = ordenCompraDetalleColeccion.get(0);
			ordenCompra.setPorcentajeDescuentosVariosCompra(ordenCompraDetalle.getPorcentajeDescuentosVariosCompra());
			ordenCompra.setPorcentajeDescuentoEspecial(ordenCompraDetalle.getPorcentajeDescuentoEspecial());
		}
		
		int revisionNumero = Integer.valueOf(ordenCompra.getRevision());
		revisionNumero = revisionNumero + 1;
		String revision = "";
		if(revisionNumero < 10){
			revision = "0" + String.valueOf(revisionNumero);
		}else{
			revision = String.valueOf(revisionNumero);
		}
		ordenCompra.setRevision(revision);

		return ordenCompra;
	}

	//PARA OBTENER LA REFERENCIA DE LA TAREA
	private Tarea tarea;

	@Override
	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
}
