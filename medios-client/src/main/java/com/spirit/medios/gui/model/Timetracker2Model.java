package com.spirit.medios.gui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DepartamentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.medios.entity.Timetracker2DetalleData;
import com.spirit.medios.entity.Timetracker2DetalleIf;
import com.spirit.medios.entity.Timetracker2EmpleadoIf;
import com.spirit.medios.entity.Timetracker2If;
import com.spirit.medios.entity.Timetracker2TiempoIf;
import com.spirit.medios.gui.panel.JPTimetracker2;
import com.spirit.seguridad.entity.RolIf;
import com.spirit.util.NumberTextField;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class Timetracker2Model extends JPTimetracker2 {
	
	private EmpleadoIf empleadoIf = null;
	private EmpleadoCriteria empleadoCriteria;
	private JDPopupFinderModel popupFinder;
	private Map<Long, Timetracker2If> mapaTimetracker = new HashMap<Long, Timetracker2If>();
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private DefaultTableModel tableModel;
	protected ClienteOficinaIf clienteOficinaIf;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private Vector<Timetracker2DetalleIf> detalles = new Vector<Timetracker2DetalleIf>();
	private Map<Integer, Long> mapaFilaTimetrackerEmpleadoId = new HashMap<Integer, Long>();
	private DecimalFormat formatoDecimal = new DecimalFormat("##0.00");
	private static final String TIME8HOURS_PATTERN = "([0-8]):[0-5][0-9]";
	private Vector<Boolean> filasPintadas = new Vector<Boolean>();
	private Map<Integer, Integer> diasAprobados = new HashMap<Integer, Integer>();
	private java.util.Date anioMesActual;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no};
	private static final int MAX_LONGITUD_VALOR = 3;
	private static final String TODOS = "TODOS";
	private boolean tiempoEnDecimal = false;
	
	
	public Timetracker2Model(){
		cargarComboOficina();
		cargarComboDepartamento();
		cargarMapas();
		initKeyListener();
		anchoColumnasTabla();
		habilitarEmpleadoReporteAprobacion();
		showSaveMode();
		initListeners();
	}
	
	private void cargarComboOficina(){
		try {
			List oficinas = (ArrayList)SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(Parametros.getIdEmpresa());
			oficinas.add(TODOS);
			refreshCombo(getCmbOficina(),oficinas);
			getCmbOficina().setSelectedItem(TODOS);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	Comparator<DepartamentoIf> ordenadorDepartamentoNombre = new Comparator<DepartamentoIf>(){
		public int compare(DepartamentoIf te1, DepartamentoIf te2) {
			if(te1.getNombre() == null){
				return -1;
			}else if(te2.getNombre() == null){
				return 1;
			}else{
				return (te1.getNombre().compareTo(te2.getNombre()));
			}
		}		
	};
	
	private void cargarComboDepartamento(){
		try {
			List departamentos = (ArrayList)SessionServiceLocator.getDepartamentoSessionService().findDepartamentoByEmpresaId(Parametros.getIdEmpresa());
			Collections.sort(departamentos,ordenadorDepartamentoNombre);
			departamentos.add(TODOS);
			refreshCombo(getCmbDepartamento(),departamentos);
			getCmbDepartamento().setSelectedItem(TODOS);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarMapas() {
		try {		
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator
					.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while (clientesOficinaIt.hasNext()) {
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clientesOficinaIt
						.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
			
			mapaTimetracker.clear();
			Collection timetracker = SessionServiceLocator
					.getTimetracker2SessionService().findTimetracker2ByEmpresaId(Parametros.getIdEmpresa());
			Iterator timetrackerIt = timetracker.iterator();
			while (timetrackerIt.hasNext()) {
				Timetracker2If timetrackerIf = (Timetracker2If)timetrackerIt.next();
				mapaTimetracker.put(timetrackerIf.getId(), timetrackerIf);
			}						
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void initKeyListener(){
		getTxtTiempoDesignado().setEditable(false);
		
		empleadoIf = null;
		getTxtEmpleado().setText("");
		clienteOficinaIf = null;
		getTxtCliente().setText("");
		getCbTodosClientes().setSelected(true);
		cleanTable();
		
		getTxtTiempoDesignado().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtTiempoDesignado().addKeyListener(new NumberTextField());
		
		getBtnBuscarEmpleado().setToolTipText("Buscar Empleado");
		getBtnBuscarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCliente().setToolTipText("Buscar Cliente");
		getBtnBuscarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnConsultar().setToolTipText("Consultar Tiempos");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
				
		getCmbMesAnio().setLocale(Utilitarios.esLocale);
		getCmbMesAnio().setShowNoneButton(false);
		getCmbMesAnio().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbMesAnio().setEditable(false);
		
		getCmbDiaInicio().setLocale(Utilitarios.esLocale);
		getCmbDiaInicio().setShowNoneButton(false);
		getCmbDiaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbDiaInicio().setEditable(false);
		
		getCmbDiaFin().setLocale(Utilitarios.esLocale);
		getCmbDiaFin().setShowNoneButton(false);
		getCmbDiaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbDiaFin().setEditable(false);
		
		getCmbMesAnio().setCalendar(new GregorianCalendar());
		getCmbDiaInicio().setCalendar(new GregorianCalendar());
		getCmbDiaFin().setCalendar(new GregorianCalendar());
		
		anioMesActual = getCmbMesAnio().getDate();
		setearFechasLimite();
		
		getCbVerFechasAprobacion().setSelected(false);
		getLblDiaInicio().setVisible(false);
		getLblDiaFin().setVisible(false);
		getCmbDiaInicio().setVisible(false);
		getCmbDiaFin().setVisible(false);
	}
	
	public void habilitarEmpleadoReporteAprobacion(){
		try {
			//si el usuario tiene el rol jefe timetracker entonces puede elegir cualquier empleado
			//caso contrario sale automaticamente el empleado del usuario que ingreso.
			Long idRolJefeTimetracker = 0L;
			Collection jefeTimetracker = SessionServiceLocator.getRolSessionService().findRolByCodigo("JTT");
			
			if(jefeTimetracker.size() > 0){
				idRolJefeTimetracker = ((RolIf)jefeTimetracker.iterator().next()).getId();
			}
			
			boolean jefeTimeTracker = false;
			UsuarioIf usuario = (UsuarioIf)Parametros.getUsuarioIf();
			if(idRolJefeTimetracker > 0L){
				Map aMap = new HashMap();
				aMap.put("rolId", idRolJefeTimetracker);
				aMap.put("usuarioId", usuario.getId());
				Collection rolUsuarioJTT = SessionServiceLocator.getRolUsuarioSessionService().findRolUsuarioByQuery(aMap);
				if(rolUsuarioJTT.size() > 0){
					jefeTimeTracker = true;
				}
			}
			
			if(!jefeTimeTracker){
				getBtnBuscarEmpleado().setEnabled(false);
				empleadoIf = (EmpleadoIf) SessionServiceLocator.getEmpleadoSessionService().getEmpleado(usuario.getEmpleadoId());
				getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
				limpiarTabla();
				getTxtTiempoDesignado().setText("0");
				
				//tambien oculto la opcion de reporte por Cliente y aprobación de fechas
				getCbTodosEmpleados().setVisible(false);
				getCbReportePorCliente().setSelected(false);
				getCbReportePorCliente().setVisible(false);
				getCbReportePorEmpleado().setSelected(false);
				getCbReportePorEmpleado().setVisible(false);
				getLblOficina().setVisible(false);
				getCmbOficina().setVisible(false);
				getLblDepartamento().setVisible(false);
				getCmbDepartamento().setVisible(false);
				getSpRangoAprobacion().setVisible(false);
				getLblDiaInicio().setVisible(false);
				getLblDiaFin().setVisible(false);
				getCbVerFechasAprobacion().setVisible(false);
				getCmbDiaInicio().setVisible(false);
				getCmbDiaFin().setVisible(false);
				getCbTransformarHorasDecimal().setVisible(false);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	private void anchoColumnasTabla() {
		//setSorterTable(getTblTiempos());
		getTblTiempos().getTableHeader().setReorderingAllowed(false);
		getTblTiempos().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//getTblTiempos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TableColumn anchoColumna = getTblTiempos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(270);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(40);		
		anchoColumna = getTblTiempos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(11);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(12);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(13);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(14);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(15);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(16);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(17);
		anchoColumna.setPreferredWidth(40);		
		anchoColumna = getTblTiempos().getColumnModel().getColumn(18);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(19);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(20);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(21);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(22);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(23);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(24);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(25);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(26);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(27);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(28);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(29);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(30);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(31);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(32);
		anchoColumna.setPreferredWidth(50);
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblTiempos().getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(8).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(9).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(10).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(11).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(12).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(13).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(14).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(15).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(16).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(17).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(18).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(19).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(20).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(21).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(22).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(23).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(24).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(25).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(26).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(27).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(28).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(29).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(30).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(31).setCellRenderer(tableCellRenderer);
		getTblTiempos().getColumnModel().getColumn(32).setCellRenderer(tableCellRenderer);
	}
	
	public void initListeners(){
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				
				boolean jefeTimeTracker = false;
				try {
					Long idRolJefeTimetracker = 0L;
					Collection jefeTimetracker = SessionServiceLocator.getRolSessionService().findRolByCodigo("JTT");
					if(jefeTimetracker.size() > 0){
						idRolJefeTimetracker = ((RolIf)jefeTimetracker.iterator().next()).getId();
					}
					
					UsuarioIf usuario = (UsuarioIf)Parametros.getUsuarioIf();
					if(idRolJefeTimetracker > 0L){
						Map aMap = new HashMap();
						aMap.put("rolId", idRolJefeTimetracker);
						aMap.put("usuarioId", usuario.getId());
						Collection rolUsuarioJTT = SessionServiceLocator.getRolUsuarioSessionService().findRolUsuarioByQuery(aMap);
						if(rolUsuarioJTT.size() > 0){
							jefeTimeTracker = true;
						}
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}				
				
				if(empleadoIf == null && !getCbReportePorEmpleado().isSelected()){
					SpiritAlert.createAlert("Debe seleccionar un empleado.", SpiritAlert.WARNING);
				}else if(!jefeTimeTracker && clienteOficinaIf == null){
					SpiritAlert.createAlert("Debe seleccionar un cliente.", SpiritAlert.WARNING);
				}/*else if(getTxtTiempoDesignado().getText().equals("")){
					SpiritAlert.createAlert("Debe ingresar un tiempo designado.", SpiritAlert.WARNING);
				}*/else if(getCbReportePorEmpleado().isSelected() || getCbReportePorCliente().isSelected()){
					cargarTabla2();
				}else{
					cargarTabla();
				}
			}
		});
		
		getBtnBuscarEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoCriteria = new EmpleadoCriteria("Empleados",Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
										empleadoCriteria,
										JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
					getCbTodosEmpleados().setSelected(false);
					limpiarTabla();
					getTxtTiempoDesignado().setText("0");
					
					//seteo el tiempo definido para este cliente si existe.
					if(clienteOficinaIf != null){					
						try {
							Map aMap = new HashMap();
							aMap.put("empleadoId", empleadoIf.getId());
							aMap.put("clienteOficinaId", clienteOficinaIf.getId());	
							Collection registros = SessionServiceLocator.getTimetracker2TiempoSessionService().findTimetracker2TiempoByQuery(aMap);
							if(registros.size() > 0){
								Timetracker2TiempoIf tiempo = (Timetracker2TiempoIf)registros.iterator().next();
								getTxtTiempoDesignado().setText(tiempo.getTiempoDesignado().toString());
							}
						} catch (GenericBusinessException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		getBtnBuscarCliente().addActionListener(new ActionListener() {
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
					getCbTodosClientes().setSelected(false);
					limpiarTabla();
					getTxtTiempoDesignado().setText("0");
					
					//seteo el tiempo definido para este cliente si existe.
					if(empleadoIf != null){					
						try {
							Map aMap = new HashMap();
							aMap.put("empleadoId", empleadoIf.getId());
							aMap.put("clienteOficinaId", clienteOficinaIf.getId());	
							Collection registros = SessionServiceLocator.getTimetracker2TiempoSessionService().findTimetracker2TiempoByQuery(aMap);
							if(registros.size() > 0){
								Timetracker2TiempoIf tiempo = (Timetracker2TiempoIf)registros.iterator().next();
								getTxtTiempoDesignado().setText(tiempo.getTiempoDesignado().toString());
							}
						} catch (GenericBusinessException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		getCbTodosClientes().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodosClientes().isSelected()){
					clienteOficinaIf = null;
					getTxtCliente().setText("");
				}
			}
		});
		
		getCbTodosEmpleados().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodosEmpleados().isSelected()){
					empleadoIf = null;
					getTxtEmpleado().setText("");
				}
			}
		});

		getCbReportePorCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbReportePorCliente().isSelected()){
					getCbReportePorEmpleado().setSelected(false);
				}
			}
		});
		
		getCbReportePorEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbReportePorEmpleado().isSelected()){
					getCbReportePorCliente().setSelected(false);
				}
			}
		});
		
		getCmbMesAnio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setearFechasLimite();
			}		
		});
		
		getCbVerFechasAprobacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbVerFechasAprobacion().isSelected()){
					getLblDiaInicio().setVisible(true);
					getLblDiaFin().setVisible(true);
					getCmbDiaInicio().setVisible(true);
					getCmbDiaFin().setVisible(true);
				}else{
					getLblDiaInicio().setVisible(false);
					getLblDiaFin().setVisible(false);
					getCmbDiaInicio().setVisible(false);
					getCmbDiaFin().setVisible(false);
				}
			}
		});
	}
	
	private void setearFechasLimite() {
		int mes = getCmbMesAnio().getDate().getMonth();
		int anio = getCmbMesAnio().getDate().getYear() + 1900;
		int ultimoDia = Utilitarios.getLastDayOfMonth(mes, anio);
		
		Calendar inicio = new GregorianCalendar(anio, mes, 1);
		Calendar fin = new GregorianCalendar(anio, mes, ultimoDia);
		
		if(anioMesActual.compareTo(getCmbMesAnio().getDate()) >= 0){
			getCmbDiaInicio().getDateModel().setMinDate(inicio);
			getCmbDiaInicio().getDateModel().setMaxDate(fin);
			getCmbDiaFin().getDateModel().setMinDate(inicio);
			getCmbDiaFin().getDateModel().setMaxDate(fin);
		}else if(anioMesActual.compareTo(getCmbMesAnio().getDate()) <= 0){
			getCmbDiaInicio().getDateModel().setMaxDate(fin);
			getCmbDiaInicio().getDateModel().setMinDate(inicio);
			getCmbDiaFin().getDateModel().setMaxDate(fin);
			getCmbDiaFin().getDateModel().setMinDate(inicio);
		}	
		
		getCmbDiaInicio().setDate(inicio.getTime());
		getCmbDiaFin().setDate(fin.getTime());
		
		anioMesActual = getCmbMesAnio().getDate();
	}
	
	Comparator<Timetracker2If> ordenadorActividadPorNombre = new Comparator<Timetracker2If>() {
		public int compare(Timetracker2If o1, Timetracker2If o2) {
			return o1.getActividad().compareTo(o2.getActividad());
		}
	};
	
	Comparator<Long> ordenadorClientePorNombre = new Comparator<Long>() {
		public int compare(Long o1, Long o2) {
			String cliente1 = mapaClienteOficina.get(o1).getDescripcion();
			String cliente2 = mapaClienteOficina.get(o2).getDescripcion();
			return cliente1.compareTo(cliente2);
		}
	};
	
	Comparator<Long> ordenadorEmpleadoPorNombre = new Comparator<Long>() {
		public int compare(Long o1, Long o2) {
			String empleado1 = "";
			String empleado2 = "";
			try {
				empleado1 = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(o1).getNombres();
				empleado2 = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(o2).getNombres();
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
			return empleado1.compareTo(empleado2);
		}
	};
	
	public void cargarTabla(){
		try {
			limpiarTabla();
			
			int mes = getCmbMesAnio().getDate().getMonth();
			int anio = getCmbMesAnio().getDate().getYear();
			
			Timestamp fechaInicio = new Timestamp(anio, mes, 1, 0, 0, 0, 0);
			int ultimoDia = Utilitarios.getLastDayOfMonth(mes, anio);
			Timestamp fechaFin = new Timestamp(anio, mes, ultimoDia, 0, 0, 0, 0);
			
			////////////////////////////////
			//FILA DE DIAS DE LA SEMANA
			Vector<String> filaDias = new Vector<String>();		
			for(int i=0; i<ultimoDia+1; i++){
				if(i == 0){
					filaDias.add("");
				}else{
					Timestamp fechaDia = new Timestamp(anio, mes, i, 0, 0, 0, 0);
					String diaSemana = Utilitarios.getFechaDiaSemanaDiaMesAnioUppercase(fechaDia).split(",")[0];
					if(diaSemana.equals("MIÉRCOLES")){
						filaDias.add("X");
					}else{
						filaDias.add(diaSemana.substring(0,1));
					}					
				}
				
				//para alinear los datos a la derecha
				getTblTiempos().getColumnModel().getColumn(i).setCellRenderer(cellRendererAliniar);
			}
			filasPintadas.add(true);
			tableModel.addRow(filaDias);
			//////////////////////////////
						
			int filaNumero = 1;
			
			//LLENO LA TABLA CON LOS DATOS DE LA BASE
			
			//ordenamiento
			ArrayList<Timetracker2If> actividadesEmpleado = new ArrayList<Timetracker2If>();
			Map<Long, Timetracker2EmpleadoIf> mapaActividadEmpleado = new HashMap<Long, Timetracker2EmpleadoIf>();
			
			Map aMapFindTimetrackerEmpleado = new HashMap();
			if(empleadoIf != null){
				aMapFindTimetrackerEmpleado.put("empleadoId", empleadoIf.getId());
			}
			Collection timetrackerEmpleados = SessionServiceLocator.getTimetracker2EmpleadoSessionService().findTimetracker2EmpleadoByQuery(aMapFindTimetrackerEmpleado);
			Iterator timetrackerEmpleadosIt = timetrackerEmpleados.iterator();
			while(timetrackerEmpleadosIt.hasNext()){
				Timetracker2EmpleadoIf timetracker2Empleado = (Timetracker2EmpleadoIf)timetrackerEmpleadosIt.next();
				
				if(timetracker2Empleado.getId().compareTo(319L) == 0){
					System.out.println("a");
				}
				
				Timetracker2If timetracker = mapaTimetracker.get(timetracker2Empleado.getTimetracker2Id());
				actividadesEmpleado.add(timetracker);
				mapaActividadEmpleado.put(timetracker.getId(), timetracker2Empleado);
			}
			Collections.sort(actividadesEmpleado,ordenadorActividadPorNombre);
			
			//mapa para sumar tiempos por cliente (del empleado) cuando un jefe quiere el reporte por cliente
			Map<Long, Map<Integer,String>> mapaClienteOficinaTiempo = new HashMap<Long,Map<Integer,String>>();
			
			//mapa para sumar tiempos por empleado cuando un jefe quiere el reporte por empleado
			Map<Long, Map<Integer,String>> mapaEmpleadoDiaTiempo = new HashMap<Long,Map<Integer,String>>();
			
			//carga de filas
			for(Timetracker2If timetracker : actividadesEmpleado){
				Timetracker2EmpleadoIf timetracker2Empleado = mapaActividadEmpleado.get(timetracker.getId());
				
				//crear arreglo de dias para la fila
				Vector<String> fila = new Vector<String>();	
				//llega hasta el día 32 porque se debe setear totales por día
				for(int i=0; i<33; i++){
					fila.add("");
				}
				
				//seteo la actividad
				fila.set(0, timetracker.getActividad());
				
				Map aMap = new HashMap();
				aMap.put("timetracker2EmpleadoId", timetracker2Empleado.getId());
				if(clienteOficinaIf != null){
					aMap.put("timetracker2ClienteOficinaId", clienteOficinaIf.getId());
				}
				Collection timetrackerDetalles = SessionServiceLocator.getTimetracker2DetalleSessionService().findTimetracker2DetalleByQueryByFechaInicioAndByFechaFin(aMap, fechaInicio, fechaFin, 0L, true);
				Iterator timetrackerDetallesIt = timetrackerDetalles.iterator();
				while(timetrackerDetallesIt.hasNext()){
					Timetracker2DetalleIf timetrackerDetalleIf = (Timetracker2DetalleIf)timetrackerDetallesIt.next();
					
					Timestamp fechaDetalle = timetrackerDetalleIf.getFecha();
										
					//double tiempo = timetrackerDetalleIf.getTiempo();
					
					Double tiempo = timetrackerDetalleIf.getTiempo().doubleValue();
					
					if(getCbTransformarHorasDecimal().isSelected()){
						tiempoEnDecimal = true;
						
						int tiempoEntero = tiempo.intValue();
						double tiempoDecimal = tiempo - tiempoEntero;
						
						tiempoDecimal = tiempoDecimal / 0.60D;
						tiempoDecimal = Utilitarios.redondeoValor(tiempoDecimal);
						
						if(tiempoDecimal >= 1D){
							tiempoEntero = tiempoEntero + 1;
							tiempo = Double.valueOf(tiempoEntero);
						}else{
							tiempo = tiempoEntero + tiempoDecimal;
						}
					}else{
						tiempoEnDecimal = false;
					}
					
					//suma los tiempos en cada celda cuando hay varios clientes
					String celdaDia = fila.get(fechaDetalle.getDate());
					if(celdaDia != null && !celdaDia.equals("")){
						sumarTiempos(fila, fechaDetalle.getDate(), String.valueOf(tiempo));
					}else if(!formatoDecimal.format(tiempo).equals("0.00")){
						fila.set(fechaDetalle.getDate(), formatoDecimal.format(tiempo));
					}else{
						fila.set(fechaDetalle.getDate(), "");
					}
					
					//cargo el mapa que suma los tiempos por cliente (para el reporte)
					if(mapaClienteOficinaTiempo.get(timetrackerDetalleIf.getClienteOficinaId()) == null){
						Map<Integer, String> mapaClienteDiaTiempo = new HashMap<Integer, String>();
						mapaClienteDiaTiempo.put(fechaDetalle.getDate(), formatoDecimal.format(tiempo));
						mapaClienteOficinaTiempo.put(timetrackerDetalleIf.getClienteOficinaId(), mapaClienteDiaTiempo);
					}else{						
						Map<Integer, String> mapaClienteDiaTiempo = mapaClienteOficinaTiempo.get(timetrackerDetalleIf.getClienteOficinaId());
						if(mapaClienteDiaTiempo.get(fechaDetalle.getDate()) == null){
							mapaClienteDiaTiempo.put(fechaDetalle.getDate(), formatoDecimal.format(tiempo));
							mapaClienteOficinaTiempo.put(timetrackerDetalleIf.getClienteOficinaId(), mapaClienteDiaTiempo);
						}else{
							String tiempoCliente = mapaClienteDiaTiempo.get(fechaDetalle.getDate());
							String sumaTiemposCliente = sumarTiemposDouble(tiempoCliente, formatoDecimal.format(tiempo));
							mapaClienteDiaTiempo.put(fechaDetalle.getDate(), sumaTiemposCliente);
							mapaClienteOficinaTiempo.put(timetrackerDetalleIf.getClienteOficinaId(), mapaClienteDiaTiempo);
						}						
					}
					
					//cargo el mapa que suma los tiempos por empleado (para el reporte)
					if(mapaEmpleadoDiaTiempo.get(timetracker2Empleado.getEmpleadoId()) == null){
						Map<Integer, String> mapaDiaTiempo = new HashMap<Integer, String>();
						mapaDiaTiempo.put(fechaDetalle.getDate(), formatoDecimal.format(tiempo));
						mapaEmpleadoDiaTiempo.put(timetracker2Empleado.getEmpleadoId(), mapaDiaTiempo);
					}else{						
						Map<Integer, String> mapaDiaTiempo = mapaEmpleadoDiaTiempo.get(timetracker2Empleado.getEmpleadoId());
						if(mapaDiaTiempo.get(fechaDetalle.getDate()) == null){
							mapaDiaTiempo.put(fechaDetalle.getDate(), formatoDecimal.format(tiempo));
							mapaEmpleadoDiaTiempo.put(timetracker2Empleado.getEmpleadoId(), mapaDiaTiempo);
						}else{
							String tiempoEmpleado = mapaDiaTiempo.get(fechaDetalle.getDate());
							String sumaTiemposEmpleado = sumarTiemposDouble(tiempoEmpleado, formatoDecimal.format(tiempo));
							mapaDiaTiempo.put(fechaDetalle.getDate(), sumaTiemposEmpleado);
							mapaEmpleadoDiaTiempo.put(timetracker2Empleado.getEmpleadoId(), mapaDiaTiempo);
						}						
					}
															
					//columna 32, totales por actividad
					String celdaTotal = fila.get(32);
					if(celdaTotal != null && !celdaTotal.equals("")){
						sumarTiempos(fila, 32, String.valueOf(tiempo));
					}else{
						fila.set(32, formatoDecimal.format(tiempo));
					}
					
					//para el color del estado
					if(timetrackerDetalleIf.getEstado().equals("A")){
						getTblTiempos().getColumnModel().getColumn(fechaDetalle.getDate()).setCellRenderer(cellRenderer);
						diasAprobados.put(fechaDetalle.getDate(), fechaDetalle.getDate());
					}else{
						getTblTiempos().getColumnModel().getColumn(fechaDetalle.getDate()).setCellRenderer(cellRendererAliniar);
					}
										
					detalles.add(timetrackerDetalleIf);
				}
				filasPintadas.add(false);
				tableModel.addRow(fila);
				
				mapaFilaTimetrackerEmpleadoId.put(filaNumero, timetracker2Empleado.getId());
				filaNumero++;	
			}
						
			////////////////////////////////
			//FILA DE TOTALES POR DIA
			Vector<String> filaTotales = new Vector<String>();		
			for(int i=0; i<33; i++){
				if(i == 0){
					filaTotales.add("TOTAL");
				}else{
					filaTotales.add("");			
				}
			}
			//empiezo en la fila 1 porque la 0 tiene los días
			for(int fila=1; fila<tableModel.getDataVector().size(); fila++){
				for(int columna=1; columna<33; columna++){							
					Object celda = ((Vector)tableModel.getDataVector().get(fila)).get(columna);
					if(celda != null && !((String)celda).equals("")){
						String tiempoTexto = ((Vector)tableModel.getDataVector().get(fila)).get(columna).toString();
						sumarTiempos(filaTotales, columna, tiempoTexto);
					}
				}
			}
			filasPintadas.add(false);
			
			//si el usuario tiene el rol jefe timetracker entonces puede ver totales por día.
			Long idRolJefeTimetracker = 0L;
			Collection jefeTimetracker = SessionServiceLocator.getRolSessionService().findRolByCodigo("JTT");
			if(jefeTimetracker.size() > 0){
				idRolJefeTimetracker = ((RolIf)jefeTimetracker.iterator().next()).getId();
			}
			
			boolean jefeTimeTracker = false;
			UsuarioIf usuario = (UsuarioIf)Parametros.getUsuarioIf();
			if(idRolJefeTimetracker > 0L){
				Map aMap = new HashMap();
				aMap.put("rolId", idRolJefeTimetracker);
				aMap.put("usuarioId", usuario.getId());
				Collection rolUsuarioJTT = SessionServiceLocator.getRolUsuarioSessionService().findRolUsuarioByQuery(aMap);
				if(rolUsuarioJTT.size() > 0){
					jefeTimeTracker = true;
				}
			}
			
			if(jefeTimeTracker){
				tableModel.addRow(filaTotales);
			}
			////////////////////////////////
			
			//si un jefe quiere ver el reporte por cliente entonces limpio la tabla y cargo la informacion
			if(getCbReportePorCliente().isSelected()){
				cleanTable();
				tableModel = (DefaultTableModel) getTblTiempos().getModel();
				
				ArrayList<Long> clientesOficinaId = new ArrayList<Long>();
				Iterator mapaClienteOficinaTiempoIt = mapaClienteOficinaTiempo.keySet().iterator();
				while(mapaClienteOficinaTiempoIt.hasNext()){
					Long clienteOficinaId = (Long)mapaClienteOficinaTiempoIt.next();
					clientesOficinaId.add(clienteOficinaId);
				}
				Collections.sort(clientesOficinaId,ordenadorClientePorNombre);
				
				for(Long clienteOficinaId : clientesOficinaId){
					ClienteOficinaIf clienteOficina = mapaClienteOficina.get(clienteOficinaId);
					
					//crear arreglo de dias para la fila
					Vector<String> fila = new Vector<String>();	
					//llega hasta el día 32 porque se debe setear totales por día
					for(int i=0; i<33; i++){
						fila.add("");
					}
					
					//seteo la actividad
					fila.set(0, clienteOficina.getDescripcion());
					
					Map<Integer,String> mapaClienteDiaTiempo = mapaClienteOficinaTiempo.get(clienteOficinaId);
					Iterator mapaClienteDiaTiempoIt = mapaClienteDiaTiempo.keySet().iterator();
					while(mapaClienteDiaTiempoIt.hasNext()){
						Integer dia = (Integer)mapaClienteDiaTiempoIt.next();
						
						String tiempoCliente = mapaClienteDiaTiempo.get(dia);
						fila.set(dia, tiempoCliente);
						
						//columna 32, totales por actividad
						String celdaTotal = fila.get(32);
						if(celdaTotal != null && !celdaTotal.equals("")){
							sumarTiempos(fila, 32, tiempoCliente);
						}else{
							fila.set(32, tiempoCliente);
						}
					}
					tableModel.addRow(fila);
				}
				
				//FILA DE TOTALES POR DIA
				Vector<String> filaTotalesCliente = new Vector<String>();		
				for(int i=0; i<33; i++){
					if(i == 0){
						filaTotalesCliente.add("TOTAL");
					}else{
						filaTotalesCliente.add("");			
					}
				}
				//empiezo en la fila 1 porque la 0 tiene los días
				for(int fila=1; fila<tableModel.getDataVector().size(); fila++){
					for(int columna=1; columna<33; columna++){							
						Object celda = ((Vector)tableModel.getDataVector().get(fila)).get(columna);
						if(celda != null && !((String)celda).equals("")){
							String tiempoTexto = ((Vector)tableModel.getDataVector().get(fila)).get(columna).toString();
							sumarTiempos(filaTotalesCliente, columna, tiempoTexto);
						}
					}
				}
								
				tableModel.addRow(filaTotales);
			}
			
			//si un jefe quiere ver el reporte por empleado entonces limpio la tabla y cargo la informacion
			if(getCbReportePorEmpleado().isSelected()){
				cleanTable();
				tableModel = (DefaultTableModel) getTblTiempos().getModel();
				
				ArrayList<Long> empleadosId = new ArrayList<Long>();
				Iterator mapaEmpleadoDiaTiempoIt = mapaEmpleadoDiaTiempo.keySet().iterator();
				while(mapaEmpleadoDiaTiempoIt.hasNext()){
					Long empleadoId = (Long)mapaEmpleadoDiaTiempoIt.next();
					empleadosId.add(empleadoId);
				}
				Collections.sort(empleadosId,ordenadorEmpleadoPorNombre);
				
				for(Long empleadoId : empleadosId){
					EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(empleadoId);
					
					//crear arreglo de dias para la fila
					Vector<String> fila = new Vector<String>();	
					//llega hasta el día 32 porque se debe setear totales por día
					for(int i=0; i<33; i++){
						fila.add("");
					}
					
					//seteo la actividad
					fila.set(0, empleado.getNombres() + " " + empleado.getApellidos());
					
					Map<Integer,String> mapaDiaTiempo = mapaEmpleadoDiaTiempo.get(empleadoId);
					Iterator mapaDiaTiempoIt = mapaDiaTiempo.keySet().iterator();
					while(mapaDiaTiempoIt.hasNext()){
						Integer dia = (Integer)mapaDiaTiempoIt.next();
						
						String tiempoCliente = mapaDiaTiempo.get(dia);
						fila.set(dia, tiempoCliente);
						
						//columna 32, totales por actividad
						String celdaTotal = fila.get(32);
						if(celdaTotal != null && !celdaTotal.equals("")){
							sumarTiempos(fila, 32, tiempoCliente);
						}else{
							fila.set(32, tiempoCliente);
						}
					}
					tableModel.addRow(fila);
				}
				
				//FILA DE TOTALES POR DIA
				Vector<String> filaTotalesCliente = new Vector<String>();		
				for(int i=0; i<33; i++){
					if(i == 0){
						filaTotalesCliente.add("TOTAL");
					}else{
						filaTotalesCliente.add("");			
					}
				}
				//empiezo en la fila 1 porque la 0 tiene los días
				for(int fila=1; fila<tableModel.getDataVector().size(); fila++){
					for(int columna=1; columna<33; columna++){							
						Object celda = ((Vector)tableModel.getDataVector().get(fila)).get(columna);
						if(celda != null && !((String)celda).equals("")){
							String tiempoTexto = ((Vector)tableModel.getDataVector().get(fila)).get(columna).toString();
							sumarTiempos(filaTotalesCliente, columna, tiempoTexto);
						}
					}
				}
								
				tableModel.addRow(filaTotales);
			}
			
			//alineo a la derecha la ultima columna
			getTblTiempos().getColumnModel().getColumn(32).setCellRenderer(cellRendererAliniar);
						
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarTabla2(){
		try {
			limpiarTabla();
						
			int mes = getCmbMesAnio().getDate().getMonth();
			int anio = getCmbMesAnio().getDate().getYear();
			
			Timestamp fechaInicio = new Timestamp(anio, mes, 1, 0, 0, 0, 0);
			int ultimoDia = Utilitarios.getLastDayOfMonth(mes, anio);
			Timestamp fechaFin = new Timestamp(anio, mes, ultimoDia, 0, 0, 0, 0);
			
			////////////////////////////////
			//FILA DE DIAS DE LA SEMANA
			Vector<String> filaDias = new Vector<String>();		
			for(int i=0; i<ultimoDia+1; i++){
				if(i == 0){
					filaDias.add("");
				}else{
					Timestamp fechaDia = new Timestamp(anio, mes, i, 0, 0, 0, 0);
					String diaSemana = Utilitarios.getFechaDiaSemanaDiaMesAnioUppercase(fechaDia).split(",")[0];
					if(diaSemana.equals("MIÉRCOLES")){
						filaDias.add("X");
					}else{
						filaDias.add(diaSemana.substring(0,1));
					}					
				}
				
				//para alinear los datos a la derecha
				getTblTiempos().getColumnModel().getColumn(i).setCellRenderer(cellRendererAliniar);
			}
			filasPintadas.add(true);
			tableModel.addRow(filaDias);
			//////////////////////////////
						
			int filaNumero = 1;
			
			//LLENO LA TABLA CON LOS DATOS DE LA BASE
						
			//Collections.sort(actividadesEmpleado,ordenadorActividadPorNombre);
			
			//mapa para sumar tiempos por cliente (del empleado) cuando un jefe quiere el reporte por cliente
			Map<Long, Map<Integer,String>> mapaClienteOficinaTiempo = new HashMap<Long,Map<Integer,String>>();
			
			//mapa para sumar tiempos por empleado cuando un jefe quiere el reporte por empleado
			Map<Long, Map<Integer,String>> mapaEmpleadoDiaTiempo = new HashMap<Long,Map<Integer,String>>();
			
			Long idEmpleado = 0L;
			if(empleadoIf != null){
				idEmpleado = empleadoIf.getId();
			}
			
			Map aMap = new HashMap();
			if(clienteOficinaIf != null){
				aMap.put("timetracker2ClienteOficinaId", clienteOficinaIf.getId());
			}
			
			if(!getCmbOficina().getSelectedItem().equals(TODOS)){
				OficinaIf oficina = (OficinaIf)getCmbOficina().getSelectedItem();
				aMap.put("oficinaId", oficina.getId());
			}
			
			if(!getCmbDepartamento().getSelectedItem().equals(TODOS)){
				DepartamentoIf departamento = (DepartamentoIf)getCmbDepartamento().getSelectedItem();
				aMap.put("departamentoId", departamento.getId());
			}		
			
			Collection timetrackerDetalles = SessionServiceLocator.getTimetracker2DetalleSessionService().findTimetracker2DetalleByQueryByFechaInicioAndByFechaFin2(aMap, fechaInicio, fechaFin, idEmpleado, true);
			Iterator timetrackerDetallesIt = timetrackerDetalles.iterator();
			while(timetrackerDetallesIt.hasNext()){
				Object[] timetrackerDetalle = (Object[])timetrackerDetallesIt.next();
				Timetracker2DetalleIf timetrackerDetalleIf = (Timetracker2DetalleIf)timetrackerDetalle[0];
				Timetracker2EmpleadoIf timetrackerEmpleadoIf = (Timetracker2EmpleadoIf)timetrackerDetalle[1];
				Timetracker2If timetrackerIf = (Timetracker2If)timetrackerDetalle[2];
														
				//crear arreglo de dias para la fila
				Vector<String> fila = new Vector<String>();	
				//llega hasta el día 32 porque se debe setear totales por día
				for(int i=0; i<33; i++){
					fila.add("");
				}
				
				//seteo la actividad
				fila.set(0, timetrackerIf.getActividad());					
				
				Timestamp fechaDetalle = timetrackerDetalleIf.getFecha();
									
				//double tiempo = timetrackerDetalleIf.getTiempo();
				Double tiempo = timetrackerDetalleIf.getTiempo().doubleValue();
				
				if(getCbTransformarHorasDecimal().isSelected()){
					tiempoEnDecimal = true;
					
					int tiempoEntero = tiempo.intValue();
					double tiempoDecimal = tiempo - tiempoEntero;
					
					tiempoDecimal = tiempoDecimal / 0.60D;
					tiempoDecimal = Utilitarios.redondeoValor(tiempoDecimal);
					
					if(tiempoDecimal >= 1D){
						tiempoEntero = tiempoEntero + 1;
						tiempo = Double.valueOf(tiempoEntero);
					}else{
						tiempo = tiempoEntero + tiempoDecimal;
					}
				}else{
					tiempoEnDecimal = false;
				}
				
				//suma los tiempos en cada celda cuando hay varios clientes
				String celdaDia = fila.get(fechaDetalle.getDate());
				if(celdaDia != null && !celdaDia.equals("")){
					sumarTiempos(fila, fechaDetalle.getDate(), String.valueOf(tiempo));
				}else if(!formatoDecimal.format(tiempo).equals("0.00")){
					fila.set(fechaDetalle.getDate(), formatoDecimal.format(tiempo));
				}else{
					fila.set(fechaDetalle.getDate(), "");
				}
				
				//cargo el mapa que suma los tiempos por cliente (para el reporte)
				if(mapaClienteOficinaTiempo.get(timetrackerDetalleIf.getClienteOficinaId()) == null){
					Map<Integer, String> mapaClienteDiaTiempo = new HashMap<Integer, String>();
					mapaClienteDiaTiempo.put(fechaDetalle.getDate(), formatoDecimal.format(tiempo));
					mapaClienteOficinaTiempo.put(timetrackerDetalleIf.getClienteOficinaId(), mapaClienteDiaTiempo);
				}else{						
					Map<Integer, String> mapaClienteDiaTiempo = mapaClienteOficinaTiempo.get(timetrackerDetalleIf.getClienteOficinaId());
					if(mapaClienteDiaTiempo.get(fechaDetalle.getDate()) == null){
						mapaClienteDiaTiempo.put(fechaDetalle.getDate(), formatoDecimal.format(tiempo));
						mapaClienteOficinaTiempo.put(timetrackerDetalleIf.getClienteOficinaId(), mapaClienteDiaTiempo);
					}else{
						String tiempoCliente = mapaClienteDiaTiempo.get(fechaDetalle.getDate());
						String sumaTiemposCliente = sumarTiemposDouble(tiempoCliente, formatoDecimal.format(tiempo));
						mapaClienteDiaTiempo.put(fechaDetalle.getDate(), sumaTiemposCliente);
						mapaClienteOficinaTiempo.put(timetrackerDetalleIf.getClienteOficinaId(), mapaClienteDiaTiempo);
					}						
				}
				
				//cargo el mapa que suma los tiempos por empleado (para el reporte)
				if(mapaEmpleadoDiaTiempo.get(timetrackerEmpleadoIf.getEmpleadoId()) == null){
					Map<Integer, String> mapaDiaTiempo = new HashMap<Integer, String>();
					mapaDiaTiempo.put(fechaDetalle.getDate(), formatoDecimal.format(tiempo));
					mapaEmpleadoDiaTiempo.put(timetrackerEmpleadoIf.getEmpleadoId(), mapaDiaTiempo);
				}else{						
					Map<Integer, String> mapaDiaTiempo = mapaEmpleadoDiaTiempo.get(timetrackerEmpleadoIf.getEmpleadoId());
					if(mapaDiaTiempo.get(fechaDetalle.getDate()) == null){
						mapaDiaTiempo.put(fechaDetalle.getDate(), formatoDecimal.format(tiempo));
						mapaEmpleadoDiaTiempo.put(timetrackerEmpleadoIf.getEmpleadoId(), mapaDiaTiempo);
					}else{
						String tiempoEmpleado = mapaDiaTiempo.get(fechaDetalle.getDate());
						String sumaTiemposEmpleado = sumarTiemposDouble(tiempoEmpleado, formatoDecimal.format(tiempo));
						mapaDiaTiempo.put(fechaDetalle.getDate(), sumaTiemposEmpleado);
						mapaEmpleadoDiaTiempo.put(timetrackerEmpleadoIf.getEmpleadoId(), mapaDiaTiempo);
					}						
				}
														
				//columna 32, totales por actividad
				String celdaTotal = fila.get(32);
				if(celdaTotal != null && !celdaTotal.equals("")){
					sumarTiempos(fila, 32, String.valueOf(tiempo));
				}else{
					fila.set(32, formatoDecimal.format(tiempo));
				}
				
				//para el color del estado
				if(timetrackerDetalleIf.getEstado().equals("A")){
					getTblTiempos().getColumnModel().getColumn(fechaDetalle.getDate()).setCellRenderer(cellRenderer);
					diasAprobados.put(fechaDetalle.getDate(), fechaDetalle.getDate());
				}else{
					getTblTiempos().getColumnModel().getColumn(fechaDetalle.getDate()).setCellRenderer(cellRendererAliniar);
				}
									
				detalles.add(timetrackerDetalleIf);
				
				
				
				filasPintadas.add(false);
				tableModel.addRow(fila);
				
				mapaFilaTimetrackerEmpleadoId.put(filaNumero, timetrackerEmpleadoIf.getId());
				filaNumero++;
			}
						
			////////////////////////////////
			//FILA DE TOTALES POR DIA
			Vector<String> filaTotales = new Vector<String>();		
			for(int i=0; i<33; i++){
				if(i == 0){
					filaTotales.add("TOTAL");
				}else{
					filaTotales.add("");			
				}
			}
			//empiezo en la fila 1 porque la 0 tiene los días
			for(int fila=1; fila<tableModel.getDataVector().size(); fila++){
				for(int columna=1; columna<33; columna++){							
					Object celda = ((Vector)tableModel.getDataVector().get(fila)).get(columna);
					if(celda != null && !((String)celda).equals("")){
						String tiempoTexto = ((Vector)tableModel.getDataVector().get(fila)).get(columna).toString();
						sumarTiempos(filaTotales, columna, tiempoTexto);
					}
				}
			}
			filasPintadas.add(false);
			
			//si el usuario tiene el rol jefe timetracker entonces puede ver totales por día.
			Long idRolJefeTimetracker = 0L;
			Collection jefeTimetracker = SessionServiceLocator.getRolSessionService().findRolByCodigo("JTT");
			if(jefeTimetracker.size() > 0){
				idRolJefeTimetracker = ((RolIf)jefeTimetracker.iterator().next()).getId();
			}
			
			boolean jefeTimeTracker = false;
			UsuarioIf usuario = (UsuarioIf)Parametros.getUsuarioIf();
			if(idRolJefeTimetracker > 0L){
				Map aMapJefe = new HashMap();
				aMapJefe.put("rolId", idRolJefeTimetracker);
				aMapJefe.put("usuarioId", usuario.getId());
				Collection rolUsuarioJTT = SessionServiceLocator.getRolUsuarioSessionService().findRolUsuarioByQuery(aMapJefe);
				if(rolUsuarioJTT.size() > 0){
					jefeTimeTracker = true;
				}
			}
			
			if(jefeTimeTracker){
				tableModel.addRow(filaTotales);
			}
			////////////////////////////////
			
			//si un jefe quiere ver el reporte por cliente entonces limpio la tabla y cargo la informacion
			if(getCbReportePorCliente().isSelected()){
				cleanTable();
				tableModel = (DefaultTableModel) getTblTiempos().getModel();
				
				ArrayList<Long> clientesOficinaId = new ArrayList<Long>();
				Iterator mapaClienteOficinaTiempoIt = mapaClienteOficinaTiempo.keySet().iterator();
				while(mapaClienteOficinaTiempoIt.hasNext()){
					Long clienteOficinaId = (Long)mapaClienteOficinaTiempoIt.next();
					clientesOficinaId.add(clienteOficinaId);
				}
				Collections.sort(clientesOficinaId,ordenadorClientePorNombre);
				
				////////////////////////////////
				//FILA DE DIAS DE LA SEMANA
				Vector<String> filaDiasReporte = new Vector<String>();		
				for(int i=0; i<ultimoDia+1; i++){
					if(i == 0){
						filaDiasReporte.add("");
					}else{
						Timestamp fechaDia = new Timestamp(anio, mes, i, 0, 0, 0, 0);
						String diaSemana = Utilitarios.getFechaDiaSemanaDiaMesAnioUppercase(fechaDia).split(",")[0];
						if(diaSemana.equals("MIÉRCOLES")){
							filaDiasReporte.add("X");
						}else{
							filaDiasReporte.add(diaSemana.substring(0,1));
						}					
					}
					
					//para alinear los datos a la derecha
					getTblTiempos().getColumnModel().getColumn(i).setCellRenderer(cellRendererAliniar);
				}
				tableModel.addRow(filaDiasReporte);
				//////////////////////////////
				
				for(Long clienteOficinaId : clientesOficinaId){
					ClienteOficinaIf clienteOficina = mapaClienteOficina.get(clienteOficinaId);
					
					//crear arreglo de dias para la fila
					Vector<String> fila = new Vector<String>();	
					//llega hasta el día 32 porque se debe setear totales por día
					for(int i=0; i<33; i++){
						fila.add("");
					}
					
					//seteo la actividad
					fila.set(0, clienteOficina.getDescripcion());
					
					Map<Integer,String> mapaClienteDiaTiempo = mapaClienteOficinaTiempo.get(clienteOficinaId);
					Iterator mapaClienteDiaTiempoIt = mapaClienteDiaTiempo.keySet().iterator();
					while(mapaClienteDiaTiempoIt.hasNext()){
						Integer dia = (Integer)mapaClienteDiaTiempoIt.next();
						
						String tiempoCliente = mapaClienteDiaTiempo.get(dia);
						fila.set(dia, tiempoCliente);
						
						//columna 32, totales por actividad
						String celdaTotal = fila.get(32);
						if(celdaTotal != null && !celdaTotal.equals("")){
							sumarTiempos(fila, 32, tiempoCliente);
						}else{
							fila.set(32, tiempoCliente);
						}
					}
					tableModel.addRow(fila);
				}
				
				//FILA DE TOTALES POR DIA
				Vector<String> filaTotalesCliente = new Vector<String>();		
				for(int i=0; i<33; i++){
					if(i == 0){
						filaTotalesCliente.add("TOTAL");
					}else{
						filaTotalesCliente.add("");			
					}
				}
				//empiezo en la fila 1 porque la 0 tiene los días
				for(int fila=1; fila<tableModel.getDataVector().size(); fila++){
					for(int columna=1; columna<33; columna++){							
						Object celda = ((Vector)tableModel.getDataVector().get(fila)).get(columna);
						if(celda != null && !((String)celda).equals("")){
							String tiempoTexto = ((Vector)tableModel.getDataVector().get(fila)).get(columna).toString();
							sumarTiempos(filaTotalesCliente, columna, tiempoTexto);
						}
					}
				}
								
				tableModel.addRow(filaTotales);
			}
			
			//si un jefe quiere ver el reporte por empleado entonces limpio la tabla y cargo la informacion
			if(getCbReportePorEmpleado().isSelected()){
				cleanTable();
				tableModel = (DefaultTableModel) getTblTiempos().getModel();
				
				ArrayList<Long> empleadosId = new ArrayList<Long>();
				Iterator mapaEmpleadoDiaTiempoIt = mapaEmpleadoDiaTiempo.keySet().iterator();
				while(mapaEmpleadoDiaTiempoIt.hasNext()){
					Long empleadoId = (Long)mapaEmpleadoDiaTiempoIt.next();
					empleadosId.add(empleadoId);
				}
				Collections.sort(empleadosId,ordenadorEmpleadoPorNombre);
				
				////////////////////////////////
				//FILA DE DIAS DE LA SEMANA
				Vector<String> filaDiasReporte = new Vector<String>();		
				for(int i=0; i<ultimoDia+1; i++){
					if(i == 0){
						filaDiasReporte.add("");
					}else{
						Timestamp fechaDia = new Timestamp(anio, mes, i, 0, 0, 0, 0);
						String diaSemana = Utilitarios.getFechaDiaSemanaDiaMesAnioUppercase(fechaDia).split(",")[0];
						if(diaSemana.equals("MIÉRCOLES")){
							filaDiasReporte.add("X");
						}else{
							filaDiasReporte.add(diaSemana.substring(0,1));
						}					
					}
					
					//para alinear los datos a la derecha
					getTblTiempos().getColumnModel().getColumn(i).setCellRenderer(cellRendererAliniar);
				}
				tableModel.addRow(filaDiasReporte);
				//////////////////////////////
				
				for(Long empleadoId : empleadosId){
					EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(empleadoId);
					
					//crear arreglo de dias para la fila
					Vector<String> fila = new Vector<String>();	
					//llega hasta el día 32 porque se debe setear totales por día
					for(int i=0; i<33; i++){
						fila.add("");
					}
					
					//seteo la actividad
					fila.set(0, empleado.getNombres() + " " + empleado.getApellidos());
					
					Map<Integer,String> mapaDiaTiempo = mapaEmpleadoDiaTiempo.get(empleadoId);
					Iterator mapaDiaTiempoIt = mapaDiaTiempo.keySet().iterator();
					while(mapaDiaTiempoIt.hasNext()){
						Integer dia = (Integer)mapaDiaTiempoIt.next();
						
						String tiempoCliente = mapaDiaTiempo.get(dia);
						fila.set(dia, tiempoCliente);
						
						//columna 32, totales por actividad
						String celdaTotal = fila.get(32);
						if(celdaTotal != null && !celdaTotal.equals("")){
							sumarTiempos(fila, 32, tiempoCliente);
						}else{
							fila.set(32, tiempoCliente);
						}
					}
					tableModel.addRow(fila);
				}
				
				//FILA DE TOTALES POR DIA
				Vector<String> filaTotalesCliente = new Vector<String>();		
				for(int i=0; i<33; i++){
					if(i == 0){
						filaTotalesCliente.add("TOTAL");
					}else{
						filaTotalesCliente.add("");			
					}
				}
				//empiezo en la fila 1 porque la 0 tiene los días
				for(int fila=1; fila<tableModel.getDataVector().size(); fila++){
					for(int columna=1; columna<33; columna++){							
						Object celda = ((Vector)tableModel.getDataVector().get(fila)).get(columna);
						if(celda != null && !((String)celda).equals("")){
							String tiempoTexto = ((Vector)tableModel.getDataVector().get(fila)).get(columna).toString();
							sumarTiempos(filaTotalesCliente, columna, tiempoTexto);
						}
					}
				}
								
				tableModel.addRow(filaTotales);
			}
			
			//alineo a la derecha la ultima columna
			getTblTiempos().getColumnModel().getColumn(32).setCellRenderer(cellRendererAliniar);
						
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void limpiarTabla() {
		cleanTable();
		detalles.clear();
		mapaFilaTimetrackerEmpleadoId.clear();
		diasAprobados.clear();
		filasPintadas.clear();
		tableModel = (DefaultTableModel) getTblTiempos().getModel();
	}

	private void sumarTiempos(Vector<String> filaTotales, int columna,
			String tiempoTexto) {
		
		double tiempo = Double.valueOf(tiempoTexto);
		tiempoTexto = formatoDecimal.format(tiempo);
		
		if(tiempo > 0 && !tiempoEnDecimal){			
			//hora de la celda
			String tiempoHora = tiempoTexto.replace(".", ":");
			int hora = Integer.valueOf(tiempoHora.split(":")[0]);
			int minutos = Integer.valueOf(tiempoHora.split(":")[1]);
			minutos = minutos + (hora * 60);
			float segundos = minutos * 60;
			
			//si ya existe un total le sumo el valor de cada celda
			if(!filaTotales.get(columna).equals("")){
				//total del día
				String tiempoTextoTemp = filaTotales.get(columna).replace(".", ":");
				int horaTemp = Integer.valueOf(tiempoTextoTemp.split(":")[0]);
				int minutosTemp = Integer.valueOf(tiempoTextoTemp.split(":")[1]);
				minutosTemp = minutosTemp + (horaTemp * 60);
				float segudosTemp = minutosTemp * 60;
				
				//suma del total que ya habia más el de la celda leída
				segundos = segundos + segudosTemp;
											
				//transformo a horas y minutos
				String horaTotalTexto = String.valueOf(segundos / 3600).replace(".", ":");
				int horaTotal = Integer.valueOf(horaTotalTexto.split(":")[0]);
				double minutosTotal = (segundos / 3600) % 1;
				minutosTotal = Utilitarios.redondeoValor(minutosTotal * 60);
				
				if(minutosTotal > 9){
					filaTotales.set(columna, horaTotal+"."+(int)minutosTotal);
				}else{
					filaTotales.set(columna, horaTotal+".0"+(int)minutosTotal);
				}							
				
			}
			//primer total del día
			else{
				filaTotales.set(columna, formatoDecimal.format(tiempo));
			}
		}else{
			if(filaTotales.get(columna).equals("")){
				filaTotales.set(columna, formatoDecimal.format(tiempo));
			}else{
				double tiempoTotal = 0D; 
				
				String tiempoTextoTemp = filaTotales.get(columna).replace(".", ":");
				int horaTemp = Integer.valueOf(tiempoTextoTemp.split(":")[0]);
				int minutosTemp = Integer.valueOf(tiempoTextoTemp.split(":")[1]);
				
				double minutosDecimal = Double.valueOf(minutosTemp) / 100D;
				tiempoTotal = horaTemp + minutosDecimal;
											
				tiempoTotal = tiempoTotal + tiempo;
				filaTotales.set(columna, formatoDecimal.format(tiempoTotal));
			}		
		}
	}
	
	private String sumarTiemposDouble(String tiempoTexto1, String tiempoTexto2) {
		
		String sumaTiempos = "";
		
		if(!getCbTransformarHorasDecimal().isSelected()){
			//hora 1
			String tiempoHora1 = tiempoTexto1.replace(".", ":");
			int hora1 = Integer.valueOf(tiempoHora1.split(":")[0]);
			int minutos1 = Integer.valueOf(tiempoHora1.split(":")[1]);
			minutos1 = minutos1 + (hora1 * 60);
			float segundos1 = minutos1 * 60;
			
			//hora 2
			String tiempoHora2 = tiempoTexto2.replace(".", ":");
			int hora2 = Integer.valueOf(tiempoHora2.split(":")[0]);
			int minutos2 = Integer.valueOf(tiempoHora2.split(":")[1]);
			minutos2 = minutos2 + (hora2 * 60);
			float segundos2 = minutos2 * 60;
			
			segundos1 = segundos1 + segundos2;
										
			//transformo a horas y minutos
			String horaTotalTexto = String.valueOf(segundos1 / 3600).replace(".", ":");
			int horaTotal = Integer.valueOf(horaTotalTexto.split(":")[0]);
			double minutosTotal = (segundos1 / 3600) % 1;
			minutosTotal = Utilitarios.redondeoValor(minutosTotal * 60);
			
			if(minutosTotal > 9){
				sumaTiempos = horaTotal+"."+(int)minutosTotal;
			}else{
				sumaTiempos = horaTotal+".0"+(int)minutosTotal;
			}
		}
		else{
			//hora 1
			double tiempoTotal1 = 0D; 
			String tiempoHora1 = tiempoTexto1.replace(".", ":");
			int hora1 = Integer.valueOf(tiempoHora1.split(":")[0]);
			int minutos1 = Integer.valueOf(tiempoHora1.split(":")[1]);
			
			double minutosDecimal1 = Double.valueOf(minutos1) / 100D;
			tiempoTotal1 = hora1 + minutosDecimal1;
			
			//hora 12
			double tiempoTotal2 = 0D; 
			String tiempoHora2 = tiempoTexto2.replace(".", ":");
			int hora2 = Integer.valueOf(tiempoHora2.split(":")[0]);
			int minutos2 = Integer.valueOf(tiempoHora2.split(":")[1]);
			
			double minutosDecimal2 = Double.valueOf(minutos2) / 100D;
			tiempoTotal2 = hora2 + minutosDecimal2;
			
			double tiempoTotal = tiempoTotal1 + tiempoTotal2;
			sumaTiempos = formatoDecimal.format(tiempoTotal);
		}
		
		
		return sumaTiempos;
	}	
	
	private void cleanTable() {
		//pongo en blanco todas las celdas
		for(int i=0; i<33; i++){
			getTblTiempos().getColumnModel().getColumn(i).setCellRenderer(cellRendererLimpiar);
		}
		
		//para quitarle el cell rederer de background blanco porque sino al seleccionar una fila la pinta de blanco.
		for(int i=0; i<33; i++){
			getTblTiempos().getColumnModel().getColumn(i).setCellRenderer(null);
		}
		
		//remuevo todas las filas
		DefaultTableModel model = (DefaultTableModel) getTblTiempos().getModel();
		for(int i= this.getTblTiempos().getRowCount();i>0;--i){
			model.removeRow(i-1);
		}	
	}
	
	public void clean() {
		//empleadoIf = null;
		//getTxtEmpleado().setText("");
		//clienteOficinaIf = null;
		//getTxtCliente().setText("");
		//getCbTodos().setSelected(true);
		//getCmbMesAnio().setCalendar(new GregorianCalendar());
		//getCmbEstado().setSelectedItem("EN CURSO");
		cleanTable();		
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
			if (getTblTiempos().getModel().getRowCount() > 0) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte del Timetracker?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/medios/RPTimetracker2.jasper";
					if(getCbReportePorCliente().isSelected() || getCbReportePorEmpleado().isSelected()){
						fileName = "jaspers/medios/RPTimetracker2PorCliente.jasper";
					}
										
					HashMap parametrosMap = new HashMap();
					
					if(empleadoIf != null){
						parametrosMap.put("empleado", empleadoIf.getNombres().split(" ")[0] + " " + empleadoIf.getApellidos().split(" ")[0]);
					}else{
						parametrosMap.put("empleado", "");
					}					
					
					if(clienteOficinaIf != null){
						parametrosMap.put("cliente", clienteOficinaIf.getDescripcion());
					}else{
						parametrosMap.put("cliente", "");
					}
					
					if(getTxtTiempoDesignado().getText().equals("0")){
						parametrosMap.put("tiempoDesignado", "");
					}else{
						parametrosMap.put("tiempoDesignado", getTxtTiempoDesignado().getText());
					}
					
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " del " + year;
					parametrosMap.put("usuario", Parametros.getUsuario());
					parametrosMap.put("emitido", fechaEmision);
					
					parametrosMap.put("mes", Utilitarios.getFechaMesAnioUppercase(getCmbMesAnio().getDate()));
					
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
										
					ReportModelImpl.processReport(fileName, parametrosMap, tableModel, true);
				}
			} else{
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
			}
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {	
		update();
	}
	
	public void authorize() {
		try {
			Date diaInicio = new java.sql.Date(getCmbDiaInicio().getDate().getTime());
			Date diaFin = new java.sql.Date(getCmbDiaFin().getDate().getTime());
			
			//en el rango de días autorizados cambio el estado de cada detalle
			//si esta EN CURSO cambia a APROBADO y viceversa
			for(int j=0; j<detalles.size(); j++){
				Timetracker2DetalleIf detalle = detalles.get(j);
			
				Date fecha = new Date(detalle.getFecha().getTime());
				
				if(Utilitarios.compararFechas(fecha, diaInicio) != -1 
						&& Utilitarios.compararFechas(fecha, diaFin) != 1){
					if(detalle.getEstado().equals("E")){
						detalle.setEstado("A");
					}else{
						detalle.setEstado("E");
					}
				}				
			}
		
			//guardo la misma información solo con los cambios de estado que hubieron
			SessionServiceLocator.getTimetracker2DetalleSessionService().procesarTimetracker2DetalleColeccion(detalles);
			SpiritAlert.createAlert("Rango de fechas Aprobado con éxito.",SpiritAlert.INFORMATION);
			showSaveMode();

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		try {
			if(tiempoEnDecimal){
				SpiritAlert.createAlert("No es posible guardar si se uso la opción: Transformar horas a decimal. Debe volver a consultar sin la opción (se borraran datos no guardados).",SpiritAlert.WARNING);
				
			}else{
				//si el usuario tiene el rol jefe timetracker entonces tiene una fila más de totales
				//caso contrario la ultima fila si es un actividad y debe ser considerada para guardar horas
				Long idRolJefeTimetracker = 0L;
				Collection jefeTimetracker = SessionServiceLocator.getRolSessionService().findRolByCodigo("JTT");
				
				if(jefeTimetracker.size() > 0){
					idRolJefeTimetracker = ((RolIf)jefeTimetracker.iterator().next()).getId();
				}
				
				boolean jefeTimeTracker = false;
				UsuarioIf usuario = (UsuarioIf)Parametros.getUsuarioIf();
				if(idRolJefeTimetracker > 0L){
					Map aMap = new HashMap();
					aMap.put("rolId", idRolJefeTimetracker);
					aMap.put("usuarioId", usuario.getId());
					Collection rolUsuarioJTT = SessionServiceLocator.getRolUsuarioSessionService().findRolUsuarioByQuery(aMap);
					if(rolUsuarioJTT.size() > 0){
						//VEO SI ES JEFE O NO Y LUEGO USO ESTO PARA VER LA ULTIMA LINEA DEL FOR
						jefeTimeTracker = true;
					}
				}
				
				if(validateFields()){
					Vector<Timetracker2DetalleIf> detallesTabla = new Vector<Timetracker2DetalleIf>();
					
					int mes = getCmbMesAnio().getDate().getMonth();
					int anio = getCmbMesAnio().getDate().getYear();
					int ultimoDia = Utilitarios.getLastDayOfMonth(mes, anio);
					
					int hayFilaTotales = 1;
					if(!jefeTimeTracker){
						hayFilaTotales = 0;
					}							
					
					//EMPIEZO EN FILA 1 PORQUE LA 0 TIENE LOS DIAS DE LA SEMANA
					for(int fila=1; fila<tableModel.getDataVector().size()-hayFilaTotales; fila++){
						for(int columna=1; columna<ultimoDia+1; columna++){
							
							String diaSemana = (String)((Vector)tableModel.getDataVector().get(0)).get(columna);
							
							//si esta aprobado no hay cambios
							if(diasAprobados.get(columna) == null /*&& !diaSemana.equals("S") && !diaSemana.equals("D")*/){
								Object celda = ((Vector)tableModel.getDataVector().get(fila)).get(columna);
								
								if(celda != null && !((String)celda).trim().equals("")){
									double tiempo = Double.valueOf(((Vector)tableModel.getDataVector().get(fila)).get(columna).toString());
									
									if(tiempo >= 0){									
										Timetracker2DetalleData detalle = new Timetracker2DetalleData();
										
										if(clienteOficinaIf != null){
											detalle.setClienteOficinaId(clienteOficinaIf.getId());
										}
										
										Timestamp fecha = new Timestamp(anio, mes, columna, 0, 0, 0, 0);
										detalle.setFecha(fecha);
										
										detalle.setEstado("E"); //EN CURSO								
										detalle.setTiempo((float)tiempo);								
										detalle.setTimetracker2EmpleadoId(mapaFilaTimetrackerEmpleadoId.get(fila));
										detalle.setTiempoDesignado(Integer.valueOf(getTxtTiempoDesignado().getText()));									
										detallesTabla.add(detalle);
									}
								}
							}				
						}
					}
					
					//actualizo id en vector para guardar/actualizar
					for(int i=0; i<detallesTabla.size(); i++){
						Timetracker2DetalleIf detalleTabla = detallesTabla.get(i);
						for(int j=0; j<detalles.size(); j++){
							Timetracker2DetalleIf detalle = detalles.get(j);
							
							/*if(detalleTabla.getClienteOficinaId() != null && detalle.getClienteOficinaId() != null){
								if(detalleTabla.getClienteOficinaId().compareTo(detalle.getClienteOficinaId()) == 0
										&& detalleTabla.getFecha().compareTo(detalle.getFecha()) == 0
										&& detalleTabla.getTimetracker2EmpleadoId().compareTo(detalle.getTimetracker2EmpleadoId()) == 0){
									detalleTabla.setId(detalle.getId());
								}
							}else{*/
								//si la fecha y la actividad es la misma de un detalle ya guardado 
								//entonces le seteo el id ya guardado para que solo actualice el detalle
								if(detalleTabla.getFecha().compareTo(detalle.getFecha()) == 0
										&& detalleTabla.getTimetracker2EmpleadoId().compareTo(detalle.getTimetracker2EmpleadoId()) == 0){
									detalleTabla.setId(detalle.getId());
								}
							//}										
						}
					}
					
					//guardo/actualizo los detalles
					SessionServiceLocator.getTimetracker2DetalleSessionService().procesarTimetracker2DetalleColeccion(detallesTabla);
					SpiritAlert.createAlert("Timetracker2 guardado con exito.",SpiritAlert.INFORMATION);
					showSaveMode();
				}
			}			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		int mes = getCmbMesAnio().getDate().getMonth();
		int anio = getCmbMesAnio().getDate().getYear();
		int ultimoDia = Utilitarios.getLastDayOfMonth(mes, anio);
		
		Calendar calendarHoy = new GregorianCalendar();
		Date fechaHoy = new Date(calendarHoy.getTimeInMillis());
		
		//validacion de formato de hora (patron)
		for(int fila=1; fila<tableModel.getDataVector().size()-1; fila++){
			for(int columna=1; columna<ultimoDia+1; columna++){
				
				Object celda = ((Vector)tableModel.getDataVector().get(fila)).get(columna);
				String horas = (String)celda;
				
				if(!horas.trim().equals("") && !validate(horas)){
					SpiritAlert.createAlert("El formato de horas de horas debe ser #.##", SpiritAlert.INFORMATION); //y no puede pasar de 8 horas
					return false;
				}
				
				Date fecha = new Date(anio, mes, columna);
				if(fecha.compareTo(fechaHoy) == 1 && !horas.trim().equals("")){
					SpiritAlert.createAlert("No es permitido guardar horas de fechas futuras, por favor borrelas para poder guardar.", SpiritAlert.INFORMATION); //y no puede pasar de 8 horas
					return false;
				}				
			}
		}
		
		if(clienteOficinaIf == null || getTxtCliente().getText().equals("")){
			SpiritAlert.createAlert("Debe seleccionar un Cliente para poder guardar las horas.", SpiritAlert.INFORMATION);
			return false;
		}		
		
		/*
		//validacion de maximo de 8 horas
		for(int columna=1; columna<32; columna++){
			String filaSumatoria = "";
			for(int fila=1; fila<tableModel.getDataVector().size()-1; fila++){							
				Object celda = ((Vector)tableModel.getDataVector().get(fila)).get(columna);
				if(celda != null && !((String)celda).trim().equals("")){
					String tiempoTexto = ((Vector)tableModel.getDataVector().get(fila)).get(columna).toString();
					
					double tiempo = Double.valueOf(tiempoTexto);
					tiempoTexto = formatoDecimal.format(tiempo);
					
					String tiempoHora = tiempoTexto.replace(".", ":");
					int hora = Integer.valueOf(tiempoHora.split(":")[0]);
					int minutos = Integer.valueOf(tiempoHora.split(":")[1]);
										
					//si la hora de una sola celda pasa las 8 horas
					if(hora > 8 || (hora == 8 && minutos > 0)){
						SpiritAlert.createAlert("El tiempo de una actividad al día no puede ser mayor a 8 horas.", SpiritAlert.INFORMATION);
						return false;
					}
					
					java.sql.Date fechaTemp = new java.sql.Date(anio, mes, columna);
					Timestamp fecha = new Timestamp(fechaTemp.getTime());
					
					if(tiempo > 0){
						
						//busco tiempos del empleado con otros clientes en esta fecha
						//para ver si no se pasa de las 8 horas diarias de trabajo.
						float segundosOtrosClientes = 0;
						Map<Long, Float> otrosClientesMap = new HashMap<Long, Float>();
						
						try {
							//Timestamp fecha = new Timestamp(anio, mes, columna, 0, 0, 0, 0);
							
							
							Map aMap = new HashMap();
							
							Collection tiemposOtrosClientes = SessionServiceLocator.getTimetracker2DetalleSessionService().findTimetracker2DetalleByQueryByFechaInicioAndByFechaFin(aMap, fecha, fecha, empleadoIf.getId(), false);
							Iterator tiemposOtrosClientesIt = tiemposOtrosClientes.iterator();
							
							while(tiemposOtrosClientesIt.hasNext()){
								Timetracker2DetalleIf timetrackerDetalle = (Timetracker2DetalleIf)tiemposOtrosClientesIt.next();
								
								//si hay registros de tiempos con otros clientes sumo el tiempo para ver si no se pasa de las 8 horas.
								if(timetrackerDetalle.getClienteOficinaId().compareTo(clienteOficinaIf.getId()) != 0){
									if(otrosClientesMap.get(timetrackerDetalle.getClienteOficinaId()) == null){
										otrosClientesMap.put(timetrackerDetalle.getClienteOficinaId(), timetrackerDetalle.getTiempo());											
									}else{
										Float totalTiempoOtroCliente = otrosClientesMap.get(timetrackerDetalle.getClienteOficinaId()) + timetrackerDetalle.getTiempo();
										otrosClientesMap.put(timetrackerDetalle.getClienteOficinaId(), totalTiempoOtroCliente);
									}
									
									String tiempoTextoOtroCliente = formatoDecimal.format(timetrackerDetalle.getTiempo());
									String tiempoHoraOtroCliente = tiempoTextoOtroCliente.replace(".", ":");
									int horaTemp = Integer.valueOf(tiempoHoraOtroCliente.split(":")[0]);
									int minutosTemp = Integer.valueOf(tiempoHoraOtroCliente.split(":")[1]);
									minutosTemp = minutosTemp + (horaTemp * 60);
									segundosOtrosClientes = segundosOtrosClientes + (minutosTemp * 60);
								}
							}
						
						} catch (GenericBusinessException e) {
							e.printStackTrace();
						}
						
						
						minutos = minutos + (hora * 60);
						float segundos = minutos * 60;
						
						if(!filaSumatoria.equals("")){
							String tiempoTextoTemp = filaSumatoria.replace(".", ":");
							int horaTemp = Integer.valueOf(tiempoTextoTemp.split(":")[0]);
							int minutosTemp = Integer.valueOf(tiempoTextoTemp.split(":")[1]);
							minutosTemp = minutosTemp + (horaTemp * 60);
							float segudosTemp = minutosTemp * 60;
							
							segundos = segundos + segudosTemp + segundosOtrosClientes;
														
							String horaTotalTexto = String.valueOf(segundos / 3600).replace(".", ":");
							int horaTotal = Integer.valueOf(horaTotalTexto.split(":")[0]);
							double minutosTotal = (segundos / 3600) % 1;
							minutosTotal = Utilitarios.redondeoValor(minutosTotal * 60);
							
							//si la suma de todo el día pasa las 8 horas
							if(horaTotal > 8 || (horaTotal == 8 && minutosTotal > 0)){
								
								String clientes = "";
								if(otrosClientesMap.size() > 0){
									clientes = "\nClientes: ";
									Iterator otrosClientesMapIt = otrosClientesMap.keySet().iterator();
									while(otrosClientesMapIt.hasNext()){
										Long idCliente = (Long)otrosClientesMapIt.next();
										Float tiempoOtroCliente = otrosClientesMap.get(idCliente);
										ClienteOficinaIf cliente = mapaClienteOficina.get(idCliente);
										clientes = clientes + "\n" + cliente + " (" + formatoDecimal.format(tiempoOtroCliente) + ")";
									}
								}								
								
								SpiritAlert.createAlert("El tiempo no puede ser mayor a 8 horas al día. (" + Utilitarios.getFechaUppercase(fechaTemp) + ")" + clientes, SpiritAlert.INFORMATION);
								return false;
							}else if(minutosTotal > 9){
								filaSumatoria = horaTotal+"."+(int)minutosTotal;
							}else{
								filaSumatoria = horaTotal+".0"+(int)minutosTotal;
							}
						}
						else if(segundosOtrosClientes > 0){
							segundos = segundos + segundosOtrosClientes;
							
							String horaTotalTexto = String.valueOf(segundos / 3600).replace(".", ":");
							int horaTotal = Integer.valueOf(horaTotalTexto.split(":")[0]);
							double minutosTotal = (segundos / 3600) % 1;
							minutosTotal = Utilitarios.redondeoValor(minutosTotal * 60);
							
							//si la suma total de clientes en todo el día pasa las 8 horas
							if(horaTotal > 8 || (horaTotal == 8 && minutosTotal > 0)){
								
								String clientes = "";
								if(otrosClientesMap.size() > 0){
									clientes = "\nClientes: ";
									Iterator otrosClientesMapIt = otrosClientesMap.keySet().iterator();
									while(otrosClientesMapIt.hasNext()){
										Long idCliente = (Long)otrosClientesMapIt.next();
										Float tiempoOtroCliente = otrosClientesMap.get(idCliente);
										ClienteOficinaIf cliente = mapaClienteOficina.get(idCliente);
										clientes = clientes + "\n" + cliente + " (" + formatoDecimal.format(tiempoOtroCliente) + ")";
									}
								}
								
								SpiritAlert.createAlert("El tiempo no puede ser mayor a 8 horas al día. (" + Utilitarios.getFechaUppercase(fechaTemp) + ")" + clientes, SpiritAlert.INFORMATION);
								return false;
							}else{
								filaSumatoria = formatoDecimal.format(tiempo);
							}
						}
						else{
							filaSumatoria = formatoDecimal.format(tiempo);
						}
					}
				}
			}
		}
		
		for(int fila=1; fila<tableModel.getDataVector().size()-1; fila++){
			for(int columna=1; columna<ultimoDia+1; columna++){
				
				String diaSemana = (String)((Vector)tableModel.getDataVector().get(0)).get(columna);
				Object celda = ((Vector)tableModel.getDataVector().get(fila)).get(columna);
				
				if((diaSemana.equals("S") || diaSemana.equals("D")) && celda != null && !((String)celda).trim().equals("")){				
					
					double tiempo = Double.valueOf(((Vector)tableModel.getDataVector().get(fila)).get(columna).toString());
					
					if(tiempo > 0){
						SpiritAlert.createAlert("No es posible ingresar horas en fin de semana.", SpiritAlert.WARNING);
						return false;
					}
				}
			}
		}
		*/
		return true;
	}
	
	public boolean validate(final String time){
		Pattern pattern;
		Matcher matcher;
	 
		if(!time.equals("")){
			String tiempo = time.replace(".", ":");
			pattern = Pattern.compile(TIME8HOURS_PATTERN);
			matcher = pattern.matcher(tiempo);
			return matcher.matches();
		}
		
		return true;
	}
	
	DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
		private Color color1 = new Color(240, 128, 128);
		 
		public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

        	Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        	if (filasPintadas.get(row)){
        		c.setBackground(color1);
        	}
        	
        	
        	if(column == 0)
        		setHorizontalAlignment(JLabel.LEFT);        	
        	else
        		setHorizontalAlignment(JLabel.RIGHT);
        	
        	return c;
       }
    };
    
    DefaultTableCellRenderer cellRendererLimpiar = new DefaultTableCellRenderer() {
		private Color colorBlanco = new Color(255, 255, 255);
        
		public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

        	Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        	c.setBackground(colorBlanco);
        	       	
        	if(column == 0)
        		setHorizontalAlignment(JLabel.LEFT);        	
        	/*else
        		setHorizontalAlignment(JLabel.RIGHT);*/
        	
        	return c;
       }
    };
    
    DefaultTableCellRenderer cellRendererAliniar = new DefaultTableCellRenderer() {
		 
		public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

        	Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        	if(column == 0)
        		setHorizontalAlignment(JLabel.LEFT);        	
        	else
        		setHorizontalAlignment(JLabel.RIGHT);
        	
        	return c;
       }
    };


	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void deleteDetail() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		cargarMapas();
	}

	public void showFindMode() {
		setFindMode();
	}

	public void showSaveMode() {
		setUpdateMode();
		clean();		
	}

	public void showUpdateMode() {
		setUpdateMode();
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}

}
