package com.spirit.cartera.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.ClienteCondicionIf;
import com.spirit.cartera.gui.panel.JPEstadoCuenta;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class EstadoCuentaModel extends JPEstadoCuenta {
	private static final long serialVersionUID = 813224798513730392L;
	JDPopupFinderModel popupFinder;
	private CorporacionCriteria corporacionCriteria;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private ClienteCriteria clienteCriteria;
	protected ClienteCondicionIf clienteCondicion;
	private CorporacionIf corporacionIf;
	private ClienteIf clienteIf;
	private ClienteOficinaIf clienteOficinaIf;
	java.util.Date fechaCreacion = new java.util.Date();
	private static final String NOMBRE_TIPO_CARTERA_CLIENTE = "CLIENTE";
	private static final String TIPO_CARTERA_CLIENTE = NOMBRE_TIPO_CARTERA_CLIENTE.substring(0, 1);
	private static final String NOMBRE_TIPO_CARTERA_PROVEEDOR = "PROVEEDOR";
	private static final String TIPO_CARTERA_PROVEEDOR = NOMBRE_TIPO_CARTERA_PROVEEDOR.substring(0, 1);
	private static final String TIPO_FILTRO_CORPORACION = "CORP";
	private static final String TIPO_FILTRO_CLIENTE = "CLIE";
	private static final String TIPO_FILTRO_CLIENTE_OFICINA = "CLOF";
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	java.util.Date fechaCarteraDetalle = new java.util.Date();
	java.util.Date fechaCreacionDetalle = new java.util.Date();
	java.util.Date fechaActualizacionDetalle = new java.util.Date();
	java.util.Date fechaVencimientoDetalle = new java.util.Date();
	int secuencialDetalle = 1;
	int filaSeleccionadaTablaDetalle = -1;
	Vector carteraDetalleColeccionCredito = new Vector();
	Vector carteraDetalleColeccionDebito = new Vector();
	Vector carteraDetalleColeccionDiferido = new Vector();
	DefaultTableModel modelCarteraCreditos;
	DefaultTableModel modelCarteraDebitos;
	DefaultTableModel modelCarteraDiferidos;
	Calendar now = Calendar.getInstance();
	Map tiposDocumentosMap = new HashMap();
	Map documentosMap = new HashMap();
	
	public EstadoCuentaModel() {
		
		initKeyListeners();
		
		getCmbFechaCorte().setLocale(Utilitarios.esLocale);
		getCmbFechaCorte().setFormat(Utilitarios.setFechaUppercase());
		showSaveMode();
		setSorterTable(getTblCreditos());
		setSorterTable(getTblDebitos());
		setSorterTable(getTblDiferidos());
		getTblCreditos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblDiferidos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblDebitos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTblCreditos().addMouseListener(oMouseAdapterTblCreditos);
		getTblCreditos().addKeyListener(oKeyAdapterTblCreditos);
		getTblDiferidos().addMouseListener(oMouseAdapterTblDiferidos);
		getTblDiferidos().addKeyListener(oKeyAdapterTblDiferidos);
		getTblDebitos().addMouseListener(oMouseAdapterTblDebitos);
		getTblDebitos().addKeyListener(oKeyAdapterTblDebitos);
		
		
		getBtnConsultar().addKeyListener(oKeyAdapterBtnConsultar);		
		getBtnBuscarCorporacion().addKeyListener(oKeyAdapterBtnCorporacion);
		getBtnBuscarCliente().addKeyListener(oKeyAdapterBtnOperador);
		getBtnBuscarOficina().addKeyListener(oKeyAdapterBtnOperadorOficina);
		
		getCmbTipoCartera().setNextFocusableComponent(getBtnBuscarCorporacion());
	}
	
	KeyListener oKeyAdapterBtnConsultar = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) { 
				if (corporacionIf != null || clienteIf != null || clienteOficinaIf != null) {
					cargarCartera();
				}
			}
		}
	};
	
	KeyListener oKeyAdapterBtnCorporacion = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) { 
				corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				
				if (popupFinder.getElementoSeleccionado() != null) {
					clean();
					corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
					getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
					clienteIf = null;
					clienteOficinaIf = null;
					getTxtCliente().setText("");
					getTxtOficina().setText("");
				}
			}
		}
	};
	
	
	
	KeyListener oKeyAdapterBtnOperador = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) { 
				buscarOperador();
			}
		}
	};
	
	KeyListener oKeyAdapterBtnOperadorOficina = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) { 
				buscarOficina();
			}
		}
	};
	
	public void report() {
		if (getMode() == SpiritMode.UPDATE_MODE) {
			try {
				if (modelCarteraCreditos.getRowCount() > 0 || modelCarteraDebitos.getRowCount() > 0 || modelCarteraDiferidos.getRowCount() > 0) {
					String si = "Si"; 
					String no = "No"; 
					Object[] options ={si,no}; 
					int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir el Estado de Cuenta?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if (opcion == JOptionPane.YES_OPTION) {
						String fileName = "jaspers/cartera/RPEstadoCuenta.jasper";
						HashMap parametrosMap = new HashMap();
						
						if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) ) {
							/* Subreportes */
							JRDataSource dataSourceDebitos = new JRTableModelDataSource(modelCarteraDebitos);
							parametrosMap.put("pathsubreportDebitos", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/cartera/RPEstadoCuentaDebitos.jasper");
							parametrosMap.put("dataSourceDetailDebitos", dataSourceDebitos);
							JRDataSource dataSourceCreditos = new JRTableModelDataSource(modelCarteraCreditos);
							parametrosMap.put("pathsubreportCreditos", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/cartera/RPEstadoCuentaCreditos.jasper");
							parametrosMap.put("dataSourceDetailCreditos", dataSourceCreditos);
							JRDataSource dataSourceDiferidos = new JRTableModelDataSource(modelCarteraDiferidos);
							parametrosMap.put("pathsubreportDiferidos", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/cartera/RPEstadoCuentaDiferidos.jasper");
							parametrosMap.put("dataSourceDetailDiferidos", dataSourceDiferidos);
						}
						else 
							throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
	
						/* Datos Reporte Maestro */
						//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("ESTADO DE CUENTA").iterator().next();
						MenuIf menu = null;
						Iterator itmenu= SessionServiceLocator.getMenuSessionService().findMenuByNombre("ESTADO DE CUENTA").iterator();
						if(itmenu.hasNext())  menu = (MenuIf) itmenu.next();
						
						
						parametrosMap.put("codigoReporte", menu.getCodigo());
						EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
						parametrosMap.put("empresa", empresa.getNombre());
						parametrosMap.put("ruc", empresa.getRuc());
						parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
						OficinaIf oficina = (OficinaIf) Parametros.getOficina();
						CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
						parametrosMap.put("ciudad", ciudad.getNombre());
						parametrosMap.put("usuario", Parametros.getUsuario());
						String fechaActual = Utilitarios.dateHoraHoy();
						String year = fechaActual.substring(0,4);
						String month = fechaActual.substring(5,7);
						String day = fechaActual.substring(8,10);
						String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
						parametrosMap.put("emitido", fechaEmision);
						/* Saldos */
						parametrosMap.put("saldoDiferido", this.getTxtSaldoTotalDiferidos().getText());
						parametrosMap.put("pdp", this.getTxtPDP().getText());
						parametrosMap.put("pdc", this.getTxtPDC().getText());
						parametrosMap.put("saldo030", this.getTxtSaldo0().getText());
						parametrosMap.put("saldo3160", this.getTxtSaldo31().getText());
						parametrosMap.put("saldo6190", this.getTxtSaldo61().getText());
						parametrosMap.put("saldo91120", this.getTxtSaldo91().getText());
						parametrosMap.put("saldo120", this.getTxtSaldo120().getText());
						parametrosMap.put("saldoTotal", this.getTxtSaldoTotal().getText());
						
						ReportModelImpl.processReport(fileName, parametrosMap, new JREmptyDataSource(), true);
					}
				} else {
					SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			} catch (ParseException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	

	public void buscarOperador(){
		Long idCorporacion = 0L;
		String tipoCliente = "";
		String tituloVentanaBusqueda = "";
		
		if (corporacionIf != null)
			idCorporacion = corporacionIf.getId();
		
		if (getCmbTipoCartera().getSelectedItem() != null) {
			if (getCmbTipoCartera().getSelectedItem().equals(NOMBRE_TIPO_CARTERA_CLIENTE)) {
				tipoCliente = "CL";
				tituloVentanaBusqueda = "Clientes";
			} else {
				tipoCliente = "PR";
				tituloVentanaBusqueda = "Proveedores";
			}
		} else {
			tipoCliente = "AM";
			tituloVentanaBusqueda = "Operadores de Negocio";
		}
		
		clienteCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, tipoCliente);
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if (popupFinder.getElementoSeleccionado() != null) {
			cleanTable();
			cleanTextFieldsSaldos();
			clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
			getTxtCliente().setText(clienteIf.getNombreLegal());
			if (corporacionIf == null) {
				try {
					corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
					getTxtCorporacion().setText(corporacionIf.getNombre());
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
			
			getTxtOficina().setText("");
			clienteOficinaIf = null;
		}
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpEstadoCuenta().getSelectedIndex();
		int tabCount = this.getJtpEstadoCuenta().getTabCount();
		
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpEstadoCuenta().setSelectedIndex(selectedTab);
	}
	
	public void clean() {
		cleanTable();
		corporacionIf = null;
		clienteIf = null;
		clienteOficinaIf = null;
		getTxtCorporacion().setText("");
		getTxtCliente().setText("");
		getTxtOficina().setText("");
		cleanTextFieldsSaldos();
		this.repaint();
	}
	
	private void cleanTextFieldsSaldos() {
		getTxtSaldoCredito().setText("");
		getTxtSaldoDebito().setText("");
		getTxtSaldoDiferido().setText("");
		getTxtSaldoTotalDiferidos().setText("");
		getTxtSaldoTotal().setText("");
		getTxtSaldo0().setText("");
		getTxtSaldo31().setText("");
		getTxtSaldo61().setText("");
		getTxtSaldo91().setText("");
		getTxtSaldo120().setText("");
		getTxtPDC().setText("");
		getTxtPDP().setText("");
		getTxtSaldo120().setText("");
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) this.getTblDebitos().getModel();
		
		for (int i = this.getTblDebitos().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
		
		model = (DefaultTableModel) this.getTblCreditos().getModel();
		
		for (int i = this.getTblCreditos().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
		
		model = (DefaultTableModel) this.getTblDiferidos().getModel();
		
		for (int i = this.getTblDiferidos().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
		
		carteraDetalleColeccionCredito = new Vector();
		carteraDetalleColeccionDebito = new Vector();
		carteraDetalleColeccionDiferido = new Vector();
	}
	
	public void showFindMode() {
		showSaveMode();
	}
	
	public void showSaveMode() {
		getTxtCliente().setBackground(getBackground());
		setSaveMode();
		clean();
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getBtnBuscarOficina().setEnabled(true);
		//cleanTable();
		this.getJtpEstadoCuenta().setSelectedIndex(0);		
		getCmbTipoCartera().grabFocus();
		
	}
	
	private void initListeners() {
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (corporacionIf != null || clienteIf != null || clienteOficinaIf != null) {
					cargarCartera();
				}
			}
		});
		
		getBtnBuscarCorporacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				
				if (popupFinder.getElementoSeleccionado() != null) {
					clean();
					corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
					getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
					clienteIf = null;
					clienteOficinaIf = null;
					getTxtCliente().setText("");
					getTxtOficina().setText("");
				}
			}
		});
		
		getBtnBuscarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarOperador();
			}
		});
		
		getBtnBuscarOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarOficina();
			}
		});
		
		getCmbTipoCartera().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (corporacionIf != null || clienteIf != null || clienteOficinaIf != null) {
					clean();
				}
			}
		});
	}
	
	
	public void buscarOficina(){
		Long idCorporacion = 0L;
		Long idCliente = 0L;
		String tipoCliente = "";
		String tituloVentanaBusqueda = "";
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.addElement(10);
		anchoColumnas.addElement(350);
		
		if (corporacionIf != null)
			idCorporacion = corporacionIf.getId();
		
		if (clienteIf != null)
			idCliente = clienteIf.getId();
		
		if (getCmbTipoCartera().getSelectedItem() != null) {
			if (getCmbTipoCartera().getSelectedItem().equals(NOMBRE_TIPO_CARTERA_CLIENTE)) {
				tipoCliente = "CL";
				tituloVentanaBusqueda = "Oficinas de Clientes";
			} else {
				tipoCliente = "PR";
				tituloVentanaBusqueda = "Oficinas de Proveedores";
			}
		}
		
		clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, "", false);
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
		if (popupFinder.getElementoSeleccionado() != null) {
			cleanTable();
			cleanTextFieldsSaldos();
			clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
			getTxtOficina().setText(clienteOficinaIf.getDescripcion());
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
		}
	}
	
	public void cargarCartera() {
		try {
			setCursor(SpiritCursor.hourglassCursor);
			Calendar now = Calendar.getInstance();
			fechaCreacion = now.getTime();
			fechaCreacion.setDate(now.getTime().getDate());
			fechaCreacion.setMonth(now.getTime().getMonth());
			fechaCreacion.setYear(now.getTime().getYear());
			Date fecha0 = new Date(fechaCreacion.getTime());
			fechaCreacion.setDate(now.getTime().getDate() - 30);
			fechaCreacion.setMonth(now.getTime().getMonth());
			fechaCreacion.setYear(now.getTime().getYear());
			Date fecha30 = new Date(fechaCreacion.getTime());
			fechaCreacion.setDate(now.getTime().getDate() - 31);
			fechaCreacion.setMonth(now.getTime().getMonth());
			fechaCreacion.setYear(now.getTime().getYear());
			Date fecha31 = new Date(fechaCreacion.getTime());
			fechaCreacion.setDate(now.getTime().getDate() - 60);
			fechaCreacion.setMonth(now.getTime().getMonth());
			fechaCreacion.setYear(now.getTime().getYear());
			Date fecha60 = new Date(fechaCreacion.getTime());
			fechaCreacion.setDate(now.getTime().getDate() - 61);
			fechaCreacion.setMonth(now.getTime().getMonth());
			fechaCreacion.setYear(now.getTime().getYear());
			Date fecha61 = new Date(fechaCreacion.getTime());
			fechaCreacion.setDate(now.getTime().getDate() - 90);
			fechaCreacion.setMonth(now.getTime().getMonth());
			fechaCreacion.setYear(now.getTime().getYear());
			Date fecha90 = new Date(fechaCreacion.getTime());
			fechaCreacion.setDate(now.getTime().getDate() - 91);
			fechaCreacion.setMonth(now.getTime().getMonth());
			fechaCreacion.setYear(now.getTime().getYear());
			Date fecha91 = new Date(fechaCreacion.getTime());
			fechaCreacion.setDate(now.getTime().getDate() - 120);
			fechaCreacion.setMonth(now.getTime().getMonth());
			fechaCreacion.setYear(now.getTime().getYear());
			Date fecha120 = new Date(fechaCreacion.getTime());
			fechaCreacion.setDate(now.getTime().getDate() - 121);
			fechaCreacion.setMonth(now.getTime().getMonth());
			fechaCreacion.setYear(now.getTime().getYear());
			Date fecha121 = new Date(fechaCreacion.getTime());
			ArrayList estadoCuenta = new ArrayList();
			Map parameterMap = new HashMap();
			parameterMap.put("tipo", getCmbTipoCartera().getSelectedItem().toString().substring(0, 1));
			java.util.Date fechaCorteSeleccionada = (java.util.Date) getCmbFechaCorte().getDate();
			java.sql.Date fechaCorte = null;
			if (fechaCorteSeleccionada != null)
				fechaCorte = new java.sql.Date(fechaCorteSeleccionada.getYear(),fechaCorteSeleccionada.getMonth(),fechaCorteSeleccionada.getDate());
			parameterMap.put("fechaCorte", fechaCorte);
			String tipoFiltro = "";
			Long idFiltro = 0L;
			
			if (corporacionIf != null && clienteIf == null && clienteOficinaIf == null) {
				tipoFiltro = TIPO_FILTRO_CORPORACION;
				idFiltro = corporacionIf.getId(); 
			} else if (corporacionIf != null && clienteIf != null && clienteOficinaIf == null) {
				tipoFiltro = TIPO_FILTRO_CLIENTE;
				idFiltro = clienteIf.getId();
			} else if (corporacionIf != null && clienteIf != null && clienteOficinaIf != null) {
				tipoFiltro = TIPO_FILTRO_CLIENTE_OFICINA;
				idFiltro = clienteOficinaIf.getId();
			}
			
			estadoCuenta = (ArrayList) SessionServiceLocator.getCarteraSessionService().findEstadoCuentaByQueryByTipoFiltroByFiltroIdAndEmpresaId(parameterMap, tipoFiltro, idFiltro, Parametros.getIdEmpresa());
			
			if (estadoCuenta != null && estadoCuenta.size() > 0) {
				Double saldo0 = 0.00, saldo31 = 0.00, saldo61 = 0.00, saldo91 = 0.00, saldo121 = 0.00;
				Iterator it = estadoCuenta.iterator();
				modelCarteraDebitos = (DefaultTableModel) getTblDebitos().getModel();
				modelCarteraCreditos = (DefaultTableModel) getTblCreditos().getModel();
				modelCarteraDiferidos = (DefaultTableModel) getTblDiferidos().getModel();
				Double saldoValor = 0.00, saldoCredito = 0.00, saldoDebito = 0.00, saldoDiferido = 0.00, saldoTotal = 0.00, PDP = 0.00, POC = 0.00;
				tiposDocumentosMap = mapearTiposDocumentos();
				
				while (it.hasNext()) {
					Object[] registro = (Object[]) it.next();
					CarteraIf cartera = (CarteraIf) registro[0];
					CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) registro[1];
					TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentosMap.get(cartera.getTipodocumentoId());
					DateFormat dateFormatter = Utilitarios.setFechaCortaUppercase();
						
					if (carteraDetalle.getCartera().equals("S")) {
						java.sql.Date fecha = carteraDetalle.getFechaCartera();
						
						if (Utilitarios.dateBetween(fecha, fecha30, fecha0))
							saldo0 = saldo0 + carteraDetalle.getSaldo().doubleValue();
						if (Utilitarios.dateBetween(fecha, fecha60, fecha31))
							saldo31 = saldo31 + carteraDetalle.getSaldo().doubleValue();
						if (Utilitarios.dateBetween(fecha, fecha90, fecha61))
							saldo61 = saldo61 + carteraDetalle.getSaldo().doubleValue();
						if (Utilitarios.dateBetween(fecha, fecha120, fecha91))
							saldo91 = saldo91 + carteraDetalle.getSaldo().doubleValue();
						if (Utilitarios.dateBefore(fecha, fecha121))
							saldo121 = saldo121 + carteraDetalle.getSaldo().doubleValue();
					}
					
					Vector<String> filaPlantillaDetalle = new Vector<String>();
					filaPlantillaDetalle.add(cartera.getCodigo());
					filaPlantillaDetalle.add(dateFormatter.format(cartera.getFechaEmision()));
					String preimpreso = "";
					if (carteraDetalle.getPreimpreso() != null)
						preimpreso = carteraDetalle.getPreimpreso();
					filaPlantillaDetalle.add(preimpreso);
					String autorizacion = "";
					if (carteraDetalle.getAutorizacion() != null)
						autorizacion = carteraDetalle.getAutorizacion();
					filaPlantillaDetalle.add(autorizacion);
					filaPlantillaDetalle.add(cartera.getComentario());
					double valor = 0D;
					if (carteraDetalle.getValor() != null)
						valor = carteraDetalle.getValor().doubleValue();
					filaPlantillaDetalle.add(formatoDecimal.format(valor));
					double saldo = 0D;
					if (carteraDetalle.getSaldo() != null)
						saldo = carteraDetalle.getSaldo().doubleValue();
					filaPlantillaDetalle.add(formatoDecimal.format(saldo));
					filaPlantillaDetalle.add(dateFormatter.format(carteraDetalle.getFechaCartera()));
					
					// si el documento es de signo + es una cuenta de debito
					if (tipoDocumento.getSignocartera().equals("+") && carteraDetalle.getCartera().equals("S")) {
						saldoDebito = saldoDebito + carteraDetalle.getSaldo().doubleValue();
						getTxtSaldoDebito().setText(formatoDecimal.format(saldoDebito));
						modelCarteraDebitos.addRow(filaPlantillaDetalle);
						carteraDetalleColeccionDebito.add(carteraDetalle);
					}
					
					// si el documento es de signo - es una cuenta de credito
					if (tipoDocumento.getSignocartera().equals("-") && carteraDetalle.getCartera().equals("S")) {
						saldoCredito = saldoCredito + carteraDetalle.getSaldo().doubleValue();
						getTxtSaldoCredito().setText(formatoDecimal.format(saldoCredito));
						modelCarteraCreditos.addRow(filaPlantillaDetalle);
						carteraDetalleColeccionCredito.add(carteraDetalle);
					}
					
					// si el documento tiene cartera "N", independiente del signo
					if (carteraDetalle.getCartera().equals("N")) {
						saldoDiferido = saldoDiferido + carteraDetalle.getSaldo().doubleValue();
						getTxtSaldoDiferido().setText(formatoDecimal.format(saldoDiferido));
						modelCarteraDiferidos.addRow(filaPlantillaDetalle);
						carteraDetalleColeccionDiferido.add(carteraDetalle);
					}
					
					// calculo los indices
					if (carteraDetalle.getCartera().equals("S")) {
						saldoValor = saldoValor + carteraDetalle.getValor().doubleValue();
						long timeInMillis = now.getTimeInMillis() - carteraDetalle.getFechaCartera().getTime();
						long days = timeInMillis / 86400000; // 1000 * 60 // * 60 * 2
						PDP = PDP + (carteraDetalle.getValor().doubleValue() - carteraDetalle.getSaldo().doubleValue()) * (int) days;
						POC = POC + (carteraDetalle.getSaldo().doubleValue()) * (int) days;
						//System.out.println("diferencia de dias:" + (int) days);
					}
				}
				
				getTxtSaldo0().setText(formatoDecimal.format(saldo0));
				getTxtSaldo31().setText(formatoDecimal.format(saldo31));
				getTxtSaldo61().setText(formatoDecimal.format(saldo61));
				getTxtSaldo91().setText(formatoDecimal.format(saldo91));
				getTxtSaldo120().setText(formatoDecimal.format(saldo121));
				saldoTotal = saldoCredito + saldoDebito;
				getTxtSaldoDebito().setText(formatoDecimal.format(saldoDebito));
				getTxtSaldoCredito().setText(formatoDecimal.format(saldoCredito));
				getTxtSaldoDiferido().setText(formatoDecimal.format(saldoDiferido));
				getTxtSaldoTotalDiferidos().setText(formatoDecimal.format(saldoDiferido));
				getTxtSaldoTotal().setText(formatoDecimal.format(saldoTotal));
				Double indicePDP = 0.00, indicePOC = 0.00;
				
				if (saldoValor > 0)
					indicePDP = PDP / saldoValor;
				
				if (saldoTotal > 0)
					indicePOC = POC / saldoTotal;
				
				getTxtPDP().setText(formatoDecimal.format(indicePDP));
				getTxtPDC().setText(formatoDecimal.format(indicePOC));
				setUpdateMode();
			} else {
				SpiritAlert.createAlert("No existen registros para el criterio de búsqueda!", SpiritAlert.WARNING);
				cleanTable();
				cleanTextFieldsSaldos();
			}
			
			setCursor(SpiritCursor.normalCursor);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private Map mapearTiposDocumentos() {
		Map tiposDocumentosMap = new HashMap();
		
		try {
			Iterator it = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) it.next();
				tiposDocumentosMap.put(tipoDocumento.getId(), tipoDocumento);
			}
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return tiposDocumentosMap;
	}

	private List<CarteraDetalleIf> getCarteraDetallesByCarteraId(Collection carteraDetalleColeccion, Long idCartera) {
		List<CarteraDetalleIf> carteraDetalleListByCarteraId = new ArrayList<CarteraDetalleIf>();
		Iterator it = carteraDetalleColeccion.iterator();
		while (it.hasNext()) {
			CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) it.next();
			if (carteraDetalle.getCarteraId().compareTo(idCartera) == 0)
				carteraDetalleListByCarteraId.add(carteraDetalle);
		}
		
		return carteraDetalleListByCarteraId;
	}
	
	public void showUpdateMode() {
		showSaveMode();
	}
	
	MouseListener oMouseAdapterTblCreditos = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			getDetailTblCreditos(evt);
		}
	};
	
	KeyListener oKeyAdapterTblCreditos = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			getDetailTblCreditos(evt);
		}
	};
	
	MouseListener oMouseAdapterTblDebitos = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			getDetailTblDebitos(evt);
		}
	};
	
	KeyListener oKeyAdapterTblDebitos = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			getDetailTblDebitos(evt);
		}
	};
	
	MouseListener oMouseAdapterTblDiferidos = new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
			getDetailTblDiferidos(evt);
		}
	};
	
	KeyListener oKeyAdapterTblDiferidos = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			getDetailTblDiferidos(evt);
		}
	};
	
	private void getDetailTblCreditos(ComponentEvent evt) {
		if (getTblCreditos().getSelectedRow() != -1) {
			CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) carteraDetalleColeccionCredito.get(((JTable) evt.getSource()).getSelectedRow());
			System.out.println("valor del item credito:" + carteraDetalle.getValor());
		}
	}
	
	private void getDetailTblDebitos(ComponentEvent evt) {
		if (getTblDebitos().getSelectedRow() != -1) {
			CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) carteraDetalleColeccionDebito.get(((JTable) evt.getSource()).getSelectedRow());
			System.out.println("valor del item debito:" + carteraDetalle.getValor());
		}
	}
	
	private void getDetailTblDiferidos(ComponentEvent evt) {
		if (getTblDiferidos().getSelectedRow() != -1) {
			CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) carteraDetalleColeccionDiferido.get(((JTable) evt.getSource()).getSelectedRow());
			System.out.println("valor del item diferido:" + carteraDetalle.getValor());
		}
	}
}