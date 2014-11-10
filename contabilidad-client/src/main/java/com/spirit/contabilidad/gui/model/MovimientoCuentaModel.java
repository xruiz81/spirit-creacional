package com.spirit.contabilidad.gui.model;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
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

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.MainFrameModel;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritModel;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.SaldoCuentaIf;
import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.criteria.CuentaCriteria;
import com.spirit.contabilidad.gui.data.MovimientoCuentaData;
import com.spirit.contabilidad.gui.panel.JPMovimientoCuenta;
import com.spirit.contabilidad.gui.tblmodel.MovimientoCuentasTableModel;
import com.spirit.contabilidad.handler.SaldoInicial;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.controller.PanelHandler;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.seguridad.entity.UsuarioCuentaIf;
import com.spirit.util.DeepCopy;
import com.spirit.util.MyTableCellEditorNumber;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class MovimientoCuentaModel extends JPMovimientoCuenta {
	
	private static final long serialVersionUID = 1921749189459677585L;
	
	private int selectedRow = -1;
	private static PeriodoIf periodo;
	private static Date fechaInicioCalculo;
	private static Date fechaInicioTemp;
	private static Date fechaFinCalculo;
	private static Date fechaFinTemp;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private static final int MAX_LONGITUD_CUENTA = 153;
	PlanCuentaIf planCuenta;
	private AsientoIf asientoForView;
	private List movimientoCuentaList;
	private Vector<CuentaIf> cuentasVector;
	private String codigoCuentaAnterior = "";
	//private Map saldosInicialesMap = new HashMap();
	private Map cuentasAgregadasMap;
	private Map movimientosAgregadosMap = new HashMap();
	private CuentaIf cuentaInicial = null;
	private CuentaIf cuentaFinal = null;
	private JDPopupFinderModel popupFinder;
	private CuentaCriteria cuentaCriteria;
	private static final String NOMBRE_MENU_MOVIMIENTO_ASIENTO = "MOVIMIENTO DE ASIENTOS";
	private final static String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private final static String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	//private Map tiposCuentaMap = new HashMap();
	private Map<Long,TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long,TipoDocumentoIf>();
	private Map<Long,ModuloIf> mapaModulo = new HashMap<Long,ModuloIf>();
	private Map<Long,TipoRolIf> mapaTipoRol = new HashMap<Long,TipoRolIf>();
	private static Map panels;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no};
	Map asientosMap = new HashMap();
	Map cuentasMap = new HashMap();
	
	String variable="I";
	
	
	
	
	private MovimientoCuentasTableModel modelTblMovimientoCuenta = new MovimientoCuentasTableModel();
	
	public MovimientoCuentaModel() {
		panels = MainFrameModel.get_panels();
		cargarMapas();
		initKeyListeners();
		addPopupMenu();
		initListeners();
		 
		//setSorterTable(getTblMovimientoCuenta());
		showSaveMode();
		anchoColumnasTablas();
		cargarMapasPlanCuentas();
		new HotKeyComponent(this);
	}
	
	public void cargarMapas(){
		try {
			
			cuentasMap.clear();
			asientosMap.clear();
			
			mapaTipoDocumento.clear();
			Collection tiposDocumento = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator tiposDocumentoIt = tiposDocumento.iterator();
			while(tiposDocumentoIt.hasNext()){
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf)tiposDocumentoIt.next();
				mapaTipoDocumento.put(tipoDocumento.getId(), tipoDocumento);
			}
			
			mapaModulo.clear();
			Collection modulos = SessionServiceLocator.getModuloSessionService().getModuloList();
			Iterator modulosIt = modulos.iterator();
			while(modulosIt.hasNext()){
				ModuloIf modulo = (ModuloIf)modulosIt.next();
				mapaModulo.put(modulo.getId(), modulo);
			}
			
			mapaTipoRol.clear();
			Collection tipoRoles = SessionServiceLocator.getTipoRolSessionService().findTipoRolByEmpresaId(Parametros.getIdEmpresa());
			Iterator tipoRolesIt = tipoRoles.iterator();
			while(tipoRolesIt.hasNext()){
				TipoRolIf tipoRol = (TipoRolIf)tipoRolesIt.next();
				mapaTipoRol.put(tipoRol.getId(), tipoRol);
			}
			 
			 
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	 
	public void cargarMapasPlanCuentas(){
		try {
			
						
			List<AsientoIf> asientosList = getAsientosList();
			mapearAsientos(asientosList);
			mapearCuentas(planCuenta);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public void initKeyListeners(){
		getBtnBuscarCuentaInicial().setToolTipText("Buscar cuenta");
		getBtnBuscarCuentaInicial().setText("");
		getBtnBuscarCuentaInicial().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCuentaFinal().setToolTipText("Buscar cuenta");
		getBtnBuscarCuentaFinal().setText("");
		getBtnBuscarCuentaFinal().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnAgregar().setToolTipText("Agregar cuenta[s]");
		getBtnAgregar().setText("");
		getBtnAgregar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		
		getTxtCuentaInicial().setName("txtCuentaInicial");
		getTxtCuentaFinal().setName("txtCuentaFinal");
		
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
				
		DefaultTableCellRenderer dtcrColumnCenter = new DefaultTableCellRenderer();
		dtcrColumnCenter.setHorizontalAlignment(JTextField.CENTER);
		getTblMovimientoCuenta().getColumnModel().getColumn(0).setCellEditor(new MyTableCellEditorNumber());
		getTblMovimientoCuenta().getColumnModel().getColumn(0).setCellRenderer(dtcrColumnCenter);
		getTblMovimientoCuenta().getColumnModel().getColumn(1).setCellEditor(new MyTableCellEditorNumber());
		getTblMovimientoCuenta().getColumnModel().getColumn(1).setCellRenderer(dtcrColumnCenter);
		getTblMovimientoCuenta().getColumnModel().getColumn(2).setCellEditor(new MyTableCellEditorNumber());
		getTblMovimientoCuenta().getColumnModel().getColumn(2).setCellRenderer(dtcrColumnCenter);
		
		DefaultTableCellRenderer dtcrColumn = new DefaultTableCellRenderer();
		dtcrColumn.setHorizontalAlignment(JTextField.RIGHT);
		getTblMovimientoCuenta().getColumnModel().getColumn(4).setCellEditor(new MyTableCellEditorNumber());
		getTblMovimientoCuenta().getColumnModel().getColumn(4).setCellRenderer(dtcrColumn);
		getTblMovimientoCuenta().getColumnModel().getColumn(5).setCellEditor(new MyTableCellEditorNumber());
		getTblMovimientoCuenta().getColumnModel().getColumn(5).setCellRenderer(dtcrColumn);
		
		//getBtnActualizar().setVisible(false);
		getTxtCuentaInicial().addKeyListener(new TextChecker(MAX_LONGITUD_CUENTA));
		getTxtCuentaFinal().addKeyListener(new TextChecker(MAX_LONGITUD_CUENTA));
		
		getTblMovimientoCuenta().setModel(modelTblMovimientoCuenta);		
		getLblProgress().setText("");		
		getBtnAgregar().setText("Agregar/Añadir Cuenta");		
		//getBtnConsultar().setText("Actualizar /n Fechas");	 
	}	 
	
	private void anchoColumnasTablas() {
		getTblMovimientoCuenta().getTableHeader().setReorderingAllowed(false);
		getTblMovimientoCuenta().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn anchoColumna = getTblMovimientoCuenta().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblMovimientoCuenta().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblMovimientoCuenta().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblMovimientoCuenta().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(300);		
		anchoColumna = getTblMovimientoCuenta().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblMovimientoCuenta().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblMovimientoCuenta().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(0);
		anchoColumna.setMinWidth(0);
		anchoColumna.setMaxWidth(0);
		
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);		
	}		

	public void clean() {
		cleanTable();
	}
	
	private void addPopupMenu() {
		 		
		menuItem = new JMenuItem("<html><font color=red>Ir a asiento contable</font></html>");
		menuItem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evento) {
				if (asientoForView != null)
					visualizarAsientoContable(asientoForView);
			}
		});
		popup.add(menuItem);

		getTblMovimientoCuenta().add(popup);
		getTblMovimientoCuenta().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON3)
					if (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE)
						popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCuentaInicial().setEnabled(false);
		getTxtCuentaInicial().setText("");
		getTxtCuentaFinal().setEnabled(false);
		getTxtCuentaFinal().setText("");
		getBtnBuscarCuentaInicial().setEnabled(false);
		getBtnBuscarCuentaFinal().setEnabled(false);
		cargarComboPlanCuenta();
		cargarComboPeriodo();
		clean();
		 
		 
		movimientoCuentaList = new ArrayList();
		cuentasVector = new Vector<CuentaIf>();
		cuentasAgregadasMap = new HashMap();
		
		getCmbPlanCuenta().grabFocus();
	}
	
	private void visualizarAsientoContable(AsientoIf asiento) {
		SpiritModel panelAsiento = (SpiritModel) new AsientoModel(asiento);
		
		if (panels.size()>0 && panels.get("Asiento")!=null){
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea cerrar la ventana Asiento?, se perderá la información que no haya sido guardada", "Confirmación", 

JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				MainFrameModel.get_dockingManager().removeFrame("Asiento");
			}
		}
		
		PanelHandler.showPanelModel(panelAsiento);
	}
	
	private void cargarComboPlanCuenta() {
		List planesCuenta = ContabilidadFinder.findPlanCuentaActivo(Parametros.getIdEmpresa());
		refreshCombo(getCmbPlanCuenta(),planesCuenta);
		if (getCmbPlanCuenta().getSelectedItem() == null)
			PlanCuentaModel.seleccionarPlanCuentaPredeterminado(getCmbPlanCuenta());
	}
	
	private void cargarComboPeriodo(){
		List periodos = ContabilidadFinder.findPeriodo(Parametros.getIdEmpresa());
		refreshCombo(getCmbPeriodo(),periodos);
		PeriodoModel.seleccionarPeriodoActivo(getCmbPeriodo());
	}
	
	public void cleanTable() {
		//saldosInicialesMap = new HashMap();
/*		
		DefaultTableModel model = (DefaultTableModel) this.getTblMovimientoCuenta().getModel();
		for(int i= this.getTblMovimientoCuenta().getRowCount();i>0;--i)
			model.removeRow(i-1);
		 */
	}
	
	
	 

	private void initListeners() {
		getBtnBuscarCuentaInicial().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				findCuentaPopupFinder(getTxtCuentaInicial());
			}
		});
		
		getBtnBuscarCuentaFinal().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				findCuentaPopupFinder(getTxtCuentaFinal());
			}
		});

		getBtnConsultar().addActionListener(new ActionListener() {
			Runnable runnable = new Runnable(){
				public void run() {
					variable="I";
					setCursor(SpiritCursor.hourglassCursor);
					getBtnConsultar().setEnabled(false);
					getBtnAgregar().setEnabled(false); 
					if(asientosMap==null)			SpiritAlert.createAlert("Seleccione el plan de cuentas y periodo a Consultar.",SpiritAlert.INFORMATION);
					getLblProgress().setVisible(true);	
					getLblProgress().setText("Procesando...");
					getLblProgress().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/waiting.gif"));
					
					long start=System.currentTimeMillis();
					
					repaintTblMovimientoCuenta();
					
					long fin=System.currentTimeMillis();
					System.out.println("---------------------123: "+(fin-start)/1000+" seg");
				 
					 
					variable="I";
					getBtnAgregar().setEnabled(true);
					getBtnConsultar().setEnabled(true);
					
					getLblProgress().setVisible(false);		
					setCursor(SpiritCursor.normalCursor);
				}
				
			};
			
			public void actionPerformed(ActionEvent evento) {
				Thread t = new Thread(runnable);
				t.start();
				
				if(variable.equals("I"))
				{	
					getLblProgress().setVisible(true);		
					getLblProgress().setText("Procesando...");
					 getLblProgress().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/waiting.gif"));					
				}
				/*if(!t.isAlive())
					{
					getLblProgress().setVisible(false);
					getLblProgress().setIcon(null);
					} */
			}
			
			
		});
		
		getTxtCuentaInicial().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				findCuentaTxtField(e, getTxtCuentaInicial());
			}
		});
		
		getTxtCuentaFinal().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				findCuentaTxtField(e, getTxtCuentaFinal());
			}
		});
		
		getCbRango().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (getCbRango().isSelected()) {
					getTxtCuentaFinal().setEnabled(true);
					getBtnBuscarCuentaFinal().setEnabled(true);
				} else {
					getTxtCuentaFinal().setEnabled(false);
					getBtnBuscarCuentaFinal().setEnabled(false);
					getTxtCuentaFinal().setText("");
				}
			}
		});

		
		
		// Veo si la fecha esta dentro del periodo contable
		getCmbFechaInicio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				boolean fechaIncorrecta = false;
				fechaInicioCalculo = (Date) ((DateComboBox) evento.getSource()).getDate();
				if (fechaInicioCalculo.before(periodo.getFechaini()) || fechaInicioCalculo.after(periodo.getFechafin())) {
					SpiritAlert.createAlert("Por favor seleccione una fecha dentro del periodo contable!", SpiritAlert.INFORMATION);
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(fechaInicioTemp);
					fechaInicioCalculo = fechaInicioTemp;
					getCmbFechaInicio().setCalendar(calendarInicio);
					fechaIncorrecta = true;
				} else if (fechaInicioCalculo.after(fechaFinCalculo)) {
					SpiritAlert.createAlert("La fecha de inicio no puede ser despues de la fecha de fin!", SpiritAlert.INFORMATION);
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(fechaInicioTemp);
					fechaInicioCalculo = fechaInicioTemp;
					getCmbFechaInicio().setCalendar(calendarInicio);
					fechaIncorrecta = true;
				} else if (!fechaInicioCalculo.equals(fechaInicioTemp) || !fechaFinCalculo.equals(fechaFinTemp)) {
					fechaInicioTemp = fechaInicioCalculo;
				}
				
				//if (!fechaIncorrecta)
					//repaintTblMovimientoCuenta();
			}
		});

		// Veo si la fecha esta dentro del periodo contable
		getCmbFechaFin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				boolean fechaIncorrecta = false;
				fechaFinCalculo = (Date) ((DateComboBox) evento.getSource()).getDate();
				if (fechaFinCalculo.after(periodo.getFechafin()) || fechaFinCalculo.before(periodo.getFechaini())) {
					SpiritAlert.createAlert("Por favor seleccione una fecha dentro del periodo contable!", SpiritAlert.INFORMATION);
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(fechaFinTemp);
					fechaFinCalculo = fechaFinTemp;
					getCmbFechaFin().setCalendar(calendarFin);
					fechaIncorrecta = true;
				} else if (fechaFinCalculo.before(fechaInicioCalculo)) {
					SpiritAlert.createAlert("La fecha final no puede ser antes de la fecha de inicio!", SpiritAlert.INFORMATION);
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(fechaFinTemp);
					fechaFinCalculo = fechaFinTemp;
					getCmbFechaFin().setCalendar(calendarFin);
					fechaIncorrecta = true;
				} else if (!fechaFinCalculo.equals(fechaFinTemp) || !fechaInicioCalculo.equals(fechaInicioTemp)) {
					fechaFinTemp = fechaFinCalculo;
				}
				
				//if (!fechaIncorrecta)
					//repaintTblMovimientoCuenta();
			}
		});
		
		

		getCmbPlanCuenta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				PlanCuentaIf planCuentaAnterior = (planCuenta!=null)?(PlanCuentaIf) DeepCopy.copy(planCuenta):null;
				planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getModel().getSelectedItem();
				if (planCuentaAnterior == null || (planCuentaAnterior!=null && planCuentaAnterior.getId().compareTo(planCuenta.getId()) != 0)) {
					if (planCuenta != null){
						getTxtCuentaInicial().setEnabled(true);
						getBtnBuscarCuentaInicial().setEnabled(true);
						getTxtCuentaInicial().setText("");
						getCbRango().setEnabled(true);
						clean();
						if (cuentasVector != null) {
							cuentasVector.clear();
							cuentasAgregadasMap.clear();							
						}
						
						System.out.println("HEEEEEEEEEEEEEEEEE11111111111111!!!!!!!!!!!!!!!!!!!!");
						cargarMapasPlanCuentas();
					}
				}
			}
		});
		
		
		

		getCmbPeriodo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				periodo = (PeriodoIf) getCmbPeriodo().getModel().getSelectedItem();
				if(periodo != null){
					// Seteo la fecha de inicio segun el la fecha de inicio del periodo contable
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(periodo.getFechaini());
					fechaInicioCalculo = periodo.getFechaini();
					fechaInicioTemp = periodo.getFechaini();
					// Seteo la fecha de fin segun el la fecha de fin del periodo contable
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(periodo.getFechafin());
					fechaFinCalculo = periodo.getFechafin();
					fechaFinTemp = periodo.getFechafin();
					getCmbFechaInicio().setCalendar(calendarInicio);
					getCmbFechaFin().setCalendar(calendarFin);
					
					cargarMapasPlanCuentas();
				}else{
					SpiritAlert.createAlert("Debe al menos existir un Periodo", SpiritAlert.WARNING);
				}
			}
		});

		
		getBtnAgregar().addActionListener(new ActionListener() {			
			Runnable runnable = new Runnable(){
				public void run() {
					variable="I";
					setCursor(SpiritCursor.hourglassCursor);
					getBtnConsultar().setEnabled(false);
					getBtnAgregar().setEnabled(false);
					
					agregarCuentaHilo();					
										
					getBtnAgregar().setEnabled(true);
					getBtnConsultar().setEnabled(true);
					variable="I";	
					getLblProgress().setVisible(false);										
					setCursor(SpiritCursor.normalCursor);
				}
			};
			
			public void actionPerformed(ActionEvent evento) {
				Thread t = new Thread(runnable);
				t.start();
				if(variable.equals("I"))
				{	
					getLblProgress().setVisible(true);//ajaxloaderc.gif
					getLblProgress().setText("Procesando...");
					 getLblProgress().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/waiting.gif"));					
				}
				/*
				if(!t.isAlive())
					{
					getLblProgress().setVisible(false);
					getLblProgress().setText(null);
					getLblProgress().setIcon(null);
					}*/
			}
		});
		
		
		
		getTblMovimientoCuenta().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				enableSelectedRowForView(evt);
			}
		});
		
		getTblMovimientoCuenta().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				enableSelectedRowForView(evt);
			}
		});
	}
	
	
 
	private void agregarCuentaHilo() {
		try {
			
			movimientosAgregadosMap.clear();
			if(cuentaInicial != null){
				 
				if (!getCbRango().isSelected()) {
					if (cuentasAgregadasMap.get(cuentaInicial.getId()) == null) {
						agregarCuenta( cuentaInicial);
						//agregarMovimientos(asientosDetallesList);
						repaintTblMovimientoCuenta();
					}
				} else if (cuentaFinal != null) {
					UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
					Iterator cuentasIterator = SessionServiceLocator.getCuentaSessionService().findCuentaByPlanCuentaIdBetweenCuentaInicialCodigoAndCuentaFinalCodigoByUsuarioId(planCuenta.getId(), usuario.getId(), cuentaInicial.getCodigo(), cuentaFinal.getCodigo(), "S").iterator();
					while (cuentasIterator.hasNext()) {
						CuentaIf cuentaIf = (CuentaIf) cuentasIterator.next();
						if (cuentasAgregadasMap.get(cuentaIf) == null) {
							agregarCuenta( cuentaIf);
							//agregarMovimientos(asientosDetallesList);
						}
					}
					
					repaintTblMovimientoCuenta();
				} else {
					SpiritAlert.createAlert("Debe ingresar la segunda Cuenta del Rango!", SpiritAlert.WARNING);
					getTxtCuentaFinal().grabFocus();
				}
			} else {
				SpiritAlert.createAlert("Ingrese una Cuenta válida!", SpiritAlert.WARNING);
				getTxtCuentaInicial().grabFocus();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);					
		} catch( Exception e ){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general con agregar cuenta!!", SpiritAlert.ERROR);
		}
	}
	
	private void enableSelectedRowForView(ComponentEvent evt) {
		if (getTblMovimientoCuenta().getSelectedRow() != -1) {
			int selectedRow = ((JTable) evt.getSource()).getSelectedRow();
			selectRow(selectedRow);
		}
	}

	private void selectRow(int selectedRow) {
		setSelectedRow(selectedRow);
		  
		MovimientoCuentaData mvd = (MovimientoCuentaData)modelTblMovimientoCuenta.getListaMovimientosCuentas().get(getSelectedRow());		
		String value="";
		value=mvd.getCodigoCuenta();
		if(value==null) value="nulo";
		
		//asientoForView = ((MovimientoCuentaData) movimientoCuentaColeccion.get(getTblMovimientoCuenta().convertRowIndexToModel(getSelectedRow()))).getAsiento();
		//asientoForView = ((MovimientoCuentaData) movimientoCuentaColeccion.get(Integer.parseInt(value))).getAsiento();
		
		if (value.equals("algo"))
			asientoForView = null;
		else
			asientoForView = mvd.getAsiento();
		 
	}
	
	private int getSelectedRow() {
		return this.selectedRow;
	}
	
	private void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}
		
	private void mapearCuentas(PlanCuentaIf planCuenta) {
		cuentasMap = new HashMap();
		try {	
			Iterator cuentasIterator = SessionServiceLocator.getCuentaSessionService().findCuentaByPlancuentaId(planCuenta.getId()).iterator();
			while (cuentasIterator.hasNext()) {
				CuentaIf cuenta = (CuentaIf) cuentasIterator.next();
				cuentasMap.put(cuenta.getId(), cuenta);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		//return cuentasMap;
	}
	
	private void mapearAsientos(List<AsientoIf> asientosList) {
		asientosMap = new HashMap();
		Iterator asientosIterator = asientosList.iterator();
		while (asientosIterator.hasNext()) {
			AsientoIf asiento = (AsientoIf) asientosIterator.next();
			asientosMap.put(asiento.getId(), asiento);		
		}
		
		//return asientosMap;
	}
	
	private List<AsientoIf> getAsientosList() {
		List<AsientoIf> asientosList = new ArrayList<AsientoIf>();
		try {
			if(periodo!=null && planCuenta!=null){
			Long idPeriodo = periodo.getId();
			Long idPlanCuenta = planCuenta.getId();
			java.sql.Date fechaInicioMovimiento = new java.sql.Date(fechaInicioCalculo.getYear(), fechaInicioCalculo.getMonth(), fechaInicioCalculo.getDate());
			java.sql.Date fechaFinMovimiento = new java.sql.Date(fechaFinCalculo.getYear(), fechaFinCalculo.getMonth(), fechaFinCalculo.getDate());
			Map parameterMap = new HashMap();
			parameterMap.put("periodoId", idPeriodo);
			parameterMap.put("plancuentaId", idPlanCuenta);
			parameterMap.put("status", "A");
			
			asientosList = (List) SessionServiceLocator.getAsientoSessionService().findAsientoByQueryByFechaInicioAndFechaFin(parameterMap, fechaInicioMovimiento,fechaFinMovimiento);
			}
		} catch(Exception e) {
			getLblProgress().setVisible(false);
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		
		return asientosList;
	}
	
	
	
	
	
	private ArrayList getAsientosDetallesList() {
		ArrayList asientosDetallesList = new ArrayList();
		try {
			Long idPeriodo = periodo.getId();
			Long idPlanCuenta = planCuenta.getId();
			java.sql.Date fechaInicioMovimiento = new java.sql.Date(fechaInicioCalculo.getYear(), fechaInicioCalculo.getMonth(), fechaInicioCalculo.getDate());
			java.sql.Date fechaFinMovimiento = new java.sql.Date(fechaFinCalculo.getYear(), fechaFinCalculo.getMonth(), fechaFinCalculo.getDate());
	 		asientosDetallesList = (ArrayList) SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByEmpresaByPeriodoIdByPlanCuentaIdByStatusAndByFechaInicioAndFechaFinOrderedByAsientoIdAndCuentaId(
	 				Parametros.getIdEmpresa(), idPeriodo, idPlanCuenta, ESTADO_ACTIVO, fechaInicioMovimiento, fechaFinMovimiento, true);
			
		} catch(Exception e) {
			getLblProgress().setVisible(false);
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		
		return asientosDetallesList;
	}
	
	private void findCuentaPopupFinder(JTextField txtCuenta) {
		CuentaIf cuentaContable = null;
		cuentaCriteria = new CuentaCriteria("Buscar Cuenta", "S", planCuenta.getId(), 0L, "");
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), cuentaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		
		if ( popupFinder.getElementoSeleccionado() != null ){
			cuentaContable = (CuentaIf) popupFinder.getElementoSeleccionado();
			Map queryMap = new HashMap();
			queryMap.put("usuarioId", ((UsuarioIf)Parametros.getUsuarioIf()).getId());
			queryMap.put("cuentaId", cuentaContable.getId());
			try {
				Iterator<UsuarioCuentaIf> it = SessionServiceLocator.getUsuarioCuentaSessionService().findUsuarioCuentaByQuery(queryMap).iterator();
				if (it.hasNext()) {
					if (txtCuenta.getName().equals("txtCuentaInicial"))
						cuentaInicial = cuentaContable;
					else if (txtCuenta.getName().equals("txtCuentaFinal"))
						cuentaFinal = cuentaContable;
					txtCuenta.setText(cuentaContable.getCodigo() + " - " + cuentaContable.getNombre());
				} else {
					SpiritAlert.createAlert("Usted no tiene los permisos adecuados para revisar los movimientos de la cuenta seleccionada", SpiritAlert.WARNING);
					txtCuenta.setText(SpiritConstants.getEmptyCharacter());
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			}
		}
	}
	
	private void findCuentaTxtField(KeyEvent e, JTextField txtCuenta) {
		try {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				Map parameterMap = new HashMap();
				String strCuenta = txtCuenta.getText();
				String strCodigoNombreCuenta = "";
				
				if (strCuenta.contains(" - "))
					strCodigoNombreCuenta = strCuenta.split(" - ")[0] + "%";
				else 
					strCodigoNombreCuenta = strCuenta + "%";
				
				parameterMap.put("codigoNombreCuenta", strCodigoNombreCuenta);
				if (getCmbPlanCuenta().getSelectedItem() != null)
					parameterMap.put("plancuentaId", ((PlanCuentaIf) getCmbPlanCuenta().getSelectedItem()).getId());
				parameterMap.put("imputable", "S");

				int tamanoLista = SessionServiceLocator.getCuentaSessionService().findCuentaByQueryByUsuarioIdAndCodigoOrNombreListSize(parameterMap, ((UsuarioIf) Parametros.getUsuarioIf()).getId());
				CuentaIf cuentaIf = null;
				if (tamanoLista == 1) {
					Iterator cuentaIterator = SessionServiceLocator.getCuentaSessionService().findCuentaByQueryByUsuarioIdAndCodigoOrNombre(parameterMap, ((UsuarioIf) Parametros.getUsuarioIf()).getId()).iterator();
					if (cuentaIterator.hasNext()) {
						cuentaIf = (CuentaIf) cuentaIterator.next();
						txtCuenta.setText(cuentaIf.getCodigo() + " - " + cuentaIf.getNombre());
					}
				} else if (tamanoLista > 1) {
					CuentaCriteria cuentaCriteria = new CuentaCriteria();
					cuentaCriteria.setIdUsuario(((UsuarioIf) Parametros.getUsuarioIf()).getId());
					cuentaCriteria.setResultListSize(tamanoLista);
					cuentaCriteria.setQueryBuilded(parameterMap);
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), cuentaCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
					if ( popupFinder.getElementoSeleccionado() != null ) {
						cuentaIf = (CuentaIf) popupFinder.getElementoSeleccionado();
						txtCuenta.setText(cuentaIf.getCodigo() + " - " + cuentaIf.getNombre());
					}
				} 
				
				if (cuentaIf == null) {
					if (tamanoLista <= 0)
						{
						getLblProgress().setVisible(false);
						SpiritAlert.createAlert("No se halló la cuenta deseada en el plan de cuentas seleccionado", SpiritAlert.WARNING);			

			
						}
				} else {
					if (txtCuenta.getName().equals("txtCuentaInicial"))
						cuentaInicial = cuentaIf;
					else if (txtCuenta.getName().equals("txtCuentaFinal"))
						cuentaFinal = cuentaIf;
				}
			}
		} catch (GenericBusinessException ex) {
			getLblProgress().setVisible(false);
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			ex.printStackTrace();
		}
	}
	
	private void repaintTblMovimientoCuenta() {
		clean();
		try {
			if (cuentasVector != null) {
				Vector<CuentaIf> cuentasVectorTemporal = (Vector<CuentaIf>) cuentasVector.clone();
				cuentasVector.clear();
				cuentasAgregadasMap.clear();
				movimientosAgregadosMap.clear();
				//tiposCuentaMap = mapearTiposCuenta();
				
				ArrayList asientosDetallesList = getAsientosDetallesList();				
			
				//saldosInicialesMap = new HashMap();
				for (int i=0; i<cuentasVectorTemporal.size(); i++) {
					CuentaIf cuentaIf = cuentasVectorTemporal.get(i);
					agregarCuenta(cuentaIf);
				}
								
				codigoCuentaAnterior = "";	
				
			 
				
				if(movimientoCuentaList.size()>0)
				movimientoCuentaList.clear();		
				agregarMovimientosCuenta(asientosMap, asientosDetallesList, cuentasMap);
				 
			 
				
				
			}
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en la consulta !!", SpiritAlert.ERROR);
		}
	}
  
	
	public boolean validateFields() {
		if (getTxtCuentaInicial().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar una cuenta contable", SpiritAlert.INFORMATION);
			this.getTxtCuentaInicial().grabFocus();
			return false;
		}
		
		if (getCbRango().isSelected() && getTxtCuentaFinal().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar una cuenta límite para el rango", SpiritAlert.INFORMATION);
			this.getTxtCuentaFinal().grabFocus();
			return false;
		}
		
		if (getCmbPeriodo().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un periodo contable", SpiritAlert.INFORMATION);
			getCmbPeriodo().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public void report() {
		try {
			if (movimientoCuentaList.size() > 0) {
				String si = "Si"; 
    	        String no = "No"; 
    	        Object[] options ={si,no}; 
    			int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
				if(opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/contabilidad/RPMovimientoCuenta.jasper";
					//String fileName = "jaspers/contabilidad/RPMovimientoCuentaEXCEL.jasper";
					HashMap parametrosMap = new HashMap();
					//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("MOVIMIENTO DE CUENTAS").iterator().next();
					MenuIf menu = null;
					Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre("MOVIMIENTO DE CUENTAS").iterator();
					if(menuIT.hasNext()) menu = (MenuIf)menuIT.next();
					
					
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("usuario", Parametros.getUsuario().toLowerCase());
					parametrosMap.put("emitido", fechaEmision);
					java.sql.Date fechaInicioMovimiento = new java.sql.Date(fechaInicioCalculo.getYear(), fechaInicioCalculo.getMonth(), fechaInicioCalculo.getDate());
					java.sql.Date fechaFinMovimiento = new java.sql.Date(fechaFinCalculo.getYear(), fechaFinCalculo.getMonth(), fechaFinCalculo.getDate());
					parametrosMap.put("fechaInicial", fechaInicioMovimiento.toString());
					parametrosMap.put("fechaFinal", fechaFinMovimiento.toString());
					parametrosMap.put("codigoPlanCuenta", planCuenta.getCodigo());
					
					JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(movimientoCuentaList);
										
					//ReportModelImpl.processReport(fileName, parametrosMap, movimientoCuentaColeccion, true);
					
					ReportModelImpl.processReport(fileName, parametrosMap, dataSourceDetail, true);
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
	
	public void refresh() {
		cargarComboPlanCuenta();
		cargarComboPeriodo();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private void agregarCuenta(CuentaIf cuentaContable) {
		if (validateFields()) {
			Long idCuentaContable = cuentaContable.getId();				
			cuentasVector.add(cuentaContable);
			cuentasAgregadasMap.put(cuentaContable.getId(), cuentaContable);		
		}
	}
	
	private String determinarTipoCuentaSegunAsiento(AsientoDetalleIf asientoDetalle) {
		BigDecimal zero = new BigDecimal(0.00);
		if (asientoDetalle.getDebe().compareTo(zero) == 1) {
			return "D";
		} else
			return "H";
	}
	
	private double calcularSaldoCuenta(AsientoDetalleIf asientoDetalle, TipoCuentaIf tipoCuentaIf, String tipoCuentaSegunAsiento, double saldoCuenta) {
		double valorDebe = 0D;
		double valorHaber = 0D;
		
		if (("D".equals(tipoCuentaIf.getDebehaber())) && ("D".equals(tipoCuentaSegunAsiento)))
			valorDebe = asientoDetalle.getDebe().doubleValue();
		
		if (("D".equals(tipoCuentaIf.getDebehaber())) && ("H".equals(tipoCuentaSegunAsiento)))
			valorHaber = asientoDetalle.getHaber().doubleValue();
		
		if (("H".equals(tipoCuentaIf.getDebehaber())) && ("D".equals(tipoCuentaSegunAsiento)))
			valorDebe = asientoDetalle.getDebe().doubleValue();
		
		if (("H".equals(tipoCuentaIf.getDebehaber())) && ("H".equals(tipoCuentaSegunAsiento)))
			valorHaber = asientoDetalle.getHaber().doubleValue();
		
		if ("D".equals(tipoCuentaIf.getDebehaber()))
			saldoCuenta += valorDebe - valorHaber;
		else
			saldoCuenta += valorHaber - valorDebe;
						
		return saldoCuenta;
	}
	
	public static PeriodoIf obtenerPeriodoAnterior(java.sql.Date fechaFinMovimiento) {
		int añoActual = fechaFinMovimiento.getYear() + 1900;
		int mesActual = fechaFinMovimiento.getMonth() + 1; 
		int añoAnterior = 0;
		int mesAnterior = 0;
		String añoAnteriorString = "";
		String mesAnteriorString = "";
		PeriodoIf periodoAnterior = null;
		
		if (mesActual == 1) {
			mesAnterior = 12;
			añoAnterior = añoActual - 1;
		} else {
			mesAnterior = mesActual - 1;
			añoAnterior = añoActual;
		}
		
		mesAnteriorString = String.valueOf(mesAnterior);

		if (mesAnterior <= 9)
			mesAnteriorString = "0" + mesAnteriorString;
		
		añoAnteriorString = String.valueOf(añoAnterior);
		
		try {
			Iterator periodosIterator = SessionServiceLocator.getPeriodoSessionService().findPeriodoByMesAndAnio(mesAnteriorString, añoAnteriorString).iterator();
			if (periodosIterator.hasNext())
				periodoAnterior = (PeriodoIf) periodosIterator.next();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		return periodoAnterior; 
	}
	
	public static List<SaldoCuentaIf> obtenerSaldosCuentasPeriodoDetalleAnteriorList(PeriodoIf periodoAnterior, java.sql.Date fechaFinMovimiento) {
		List<SaldoCuentaIf> saldosCuentasList = new ArrayList<SaldoCuentaIf>();
		int añoActual = fechaFinMovimiento.getYear() + 1900;
		int mesActual = fechaFinMovimiento.getMonth() + 1; 
		int añoAnterior = 0;
		int mesAnterior = 0;
		String añoAnteriorString = "";
		String mesAnteriorString = "";
		double saldoCuentaPeriodoDetalleAnterior = 0D;
		
		if (mesActual == 1) {
			mesAnterior = 12;
			añoAnterior = añoActual - 1;
		} else {
			mesAnterior = mesActual - 1;
			añoAnterior = añoActual;
		}
		
		mesAnteriorString = String.valueOf(mesAnterior);

		if (mesAnterior <= 9)
			mesAnteriorString = "0" + mesAnteriorString;
		
		añoAnteriorString = String.valueOf(añoAnterior);
		
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("periodoId", periodoAnterior.getId());
			parameterMap.put("mes", mesAnteriorString);
			parameterMap.put("anio", añoAnteriorString);
			Iterator saldoCuentaIterator = SessionServiceLocator.getSaldoCuentaSessionService().findSaldoCuentaByQuery(parameterMap).iterator();
			while (saldoCuentaIterator.hasNext()) {
				SaldoCuentaIf saldoCuenta = (SaldoCuentaIf) saldoCuentaIterator.next();
				saldosCuentasList.add(saldoCuenta);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		return saldosCuentasList; 
	}
	
	public static double obtenerSaldoCuentaPeriodoDetalleAnterior(List<SaldoCuentaIf> saldosCuentasList, CuentaIf cuentaBalanceIf) {
		double saldoCuentaPeriodoDetalleAnterior = 0D;
		Iterator saldosCuentasIterator = saldosCuentasList.iterator();
		
		while(saldosCuentasIterator.hasNext()) {
			SaldoCuentaIf saldoCuenta = (SaldoCuentaIf) saldosCuentasIterator.next();
			if (saldoCuenta.getCuentaId().compareTo(cuentaBalanceIf.getId()) == 0)
				saldoCuentaPeriodoDetalleAnterior = saldoCuenta.getValor().doubleValue();
		}
		
		return saldoCuentaPeriodoDetalleAnterior; 
	}
	
	private List<AsientoDetalleIf> getAsientosDetallesByCuentaId(ArrayList asientosDetallesList, Long idCuenta) {
		List<AsientoDetalleIf> asientosDetallesByCuentaIdList = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesList.iterator();
		
		while (asientosDetallesIterator.hasNext()) {
			Object[] data = (Object[]) asientosDetallesIterator.next();
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) data[1];
			if (asientoDetalle.getCuentaId().compareTo(idCuenta) == 0) {
				asientosDetallesByCuentaIdList.add(asientoDetalle);
			}
		}
		
		return asientosDetallesByCuentaIdList;
	}
	
	
	private void removeCuentaFromVector(CuentaIf cuentaPorExcluir, Vector<CuentaIf> cuentasVector) {
		Iterator cuentasVectorIterator = cuentasVector.iterator();
		
		while (cuentasVectorIterator.hasNext()) {
			CuentaIf cuentaIf = (CuentaIf) cuentasVectorIterator.next();
			if (cuentaPorExcluir.getId().compareTo(cuentaIf.getId()) == 0) {
				cuentasVectorIterator.remove();
				cuentasAgregadasMap.remove(cuentaIf.getId());
			}
		}
	}
	 
		
	private void agregarMovimientosCuenta(Map asientosMap, ArrayList asientosDetallesList, Map cuentasMap) throws GenericBusinessException {
		try {
			
			List<MovimientoCuentaData> listaMovimiento = new ArrayList<MovimientoCuentaData>();
			
			if (validateFields()) {				
				//carlos
				//Map<Long,Map<Long,SaldoInicial>> mapaMapaSaldosIniciales =  new HashMap<Long, Map<Long,SaldoInicial>>();
				Map<Long,SaldoInicial> mapaSaldosIniciales = new HashMap<Long, SaldoInicial>();
				Map<Long,Double> mapaTotales =  new HashMap<Long, Double>();
				int contadorFilas = 0;
				Double totalDebeTabla = 0D;
				Double totalHaberTabla = 0D;
				
				//-------------
				
				Double totalDebe = 0D;
				Double totalHaber = 0D;
				String codigoComprobante = "";
				Map aMap = new HashMap();
				
				//modelTblMovimientoCuenta = (DefaultTableModel) getTblMovimientoCuenta().getModel();
				
				Iterator asientosDetallesIterator = asientosDetallesList.iterator();
			 	int h=0;
				
				 
				
				while (asientosDetallesIterator.hasNext()) {
					Object[] data = (Object[]) asientosDetallesIterator.next();
					AsientoDetalleIf asientoDetalleIf = (AsientoDetalleIf) data[1];
					h++;
				 
					CuentaIf cuentaContable = (CuentaIf) cuentasMap.get(asientoDetalleIf.getCuentaId());
					
				
					if ( !mapaSaldosIniciales.containsKey(cuentaContable.getId()) ){
					mapaSaldosIniciales = SessionServiceLocator.getSaldoCuentaSessionService().verificarSaldosInicialesCuentasNuevo(				
							getCmbFechaInicio().getDate(),mapaSaldosIniciales,
							cuentaContable,Parametros.getIdEmpresa());
					}
					//------------------------------
					
					if (cuentasAgregadasMap.get(cuentaContable.getId()) != null) {
						AsientoIf asientoIf = (AsientoIf) asientosMap.get(asientoDetalleIf.getAsientoId());
						if (asientoIf != null && (asientoIf.getAsientoCierre() == null || !asientoIf.getAsientoCierre().equals("S"))) {
						codigoComprobante = "";
						if(asientoIf != null && asientoIf.getTipoDocumentoId() != null && asientoIf.getTransaccionId() != null){						 
						 
							TipoDocumentoIf tipoDocumento = mapaTipoDocumento.get(asientoIf.getTipoDocumentoId());
							if(tipoDocumento.getModuloId() != null){								
								ModuloIf modulo = mapaModulo.get(tipoDocumento.getModuloId());
								aMap = new HashMap();
								aMap.put("oficinaId",Parametros.getIdOficina());
								aMap.put("id",asientoIf.getTransaccionId());
								aMap.put("tipodocumentoId",asientoIf.getTipoDocumentoId());
								if(modulo.getCodigo().equals("FACT")){
										ArrayList fact = (ArrayList) SessionServiceLocator.getFacturaSessionService().findFacturaByQuery(aMap);
										if(fact!=null){
											Iterator itfact= fact.iterator();
											if(itfact.hasNext()){
												codigoComprobante = ((FacturaIf)itfact.next()).getPreimpresoNumero();
											}
										}
								}else if(modulo.getCodigo().equals("COMP")){
									
									ArrayList comp = (ArrayList) SessionServiceLocator.getCompraSessionService().findCompraByQuery(aMap);
									if(comp!=null){
										Iterator itComp= comp.iterator();
										if(itComp.hasNext()){
											codigoComprobante = ((CompraIf)itComp.next()).getCodigo();
										}
									}
								}else if(modulo.getCodigo().equals("CART")){									
									ArrayList cart = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCarteraByQuery(aMap);
									if(cart!=null){
										Iterator itCart= cart.iterator();
										if(itCart.hasNext()){
											codigoComprobante = ((CarteraIf)itCart.next()).getCodigo();					

						
										}
										else{
											aMap.remove("tipodocumentoId");
											aMap.put("tipoDocumentoId",asientoIf.getTipoDocumentoId());
											ArrayList NC = (ArrayList) SessionServiceLocator.getNotaCreditoSessionService().findNotaCreditoByQuery(aMap);
											if(NC!=null){
												Iterator itNC= NC.iterator();
												if(itNC.hasNext()){
													codigoComprobante = ((NotaCreditoIf)itNC.next()).getCodigo();			
												}
											}									
										}
									}
									else{
										aMap.remove("tipodocumentoId");
										aMap.put("tipoDocumentoId",asientoIf.getTipoDocumentoId());
										
										ArrayList NC = (ArrayList) SessionServiceLocator.getNotaCreditoSessionService().findNotaCreditoByQuery(aMap);
										if(NC!=null){
											Iterator itNC= NC.iterator();
											if(itNC.hasNext()){
												codigoComprobante = ((NotaCreditoIf)itNC.next()).getCodigo();				

							
											}
										}
									}									 
								}else if(modulo.getCodigo().equals("NOMI")){
									RolPagoIf rp = (RolPagoIf) SessionServiceLocator.getRolPagoSessionService().getRolPago(asientoIf.getTransaccionId());
									if(rp!=null){
										if (mapaTipoRol.get(rp.getTiporolId()).getNombre().length() > 18){
											codigoComprobante = rp.getMes() + " - " + rp.getAnio() + " - " + mapaTipoRol.get(rp.getTiporolId()).getNombre().substring(0, 18);
										}else{
											codigoComprobante = rp.getMes() + " - " + rp.getAnio() + " - " + mapaTipoRol.get(rp.getTiporolId()).getNombre();
										}
										}								
									}
								}
							} 
						
						String fecha = Utilitarios.getFechaCortaUppercase(asientoIf.getFecha());
						MovimientoCuentaData movimientoCuenta = new MovimientoCuentaData();
						
						//carlos
						//Map<Long,SaldoInicial> mapaSaldosIniciales = mapaMapaSaldosIniciales.get(cuentaContable.getId());
						SaldoInicial si = mapaSaldosIniciales.get(cuentaContable.getId());
						if (si == null){
							System.out.println("");
							si = new SaldoInicial();
							si.setDebeInicial(BigDecimal.ZERO);
							si.setHaberInicial(BigDecimal.ZERO);
							movimientoCuenta.setDebeInicial(Utilitarios.redondeoValor(si.getDebeInicial()));
							movimientoCuenta.setHaberInicial(Utilitarios.redondeoValor(si.getHaberInicial()));
							mapaSaldosIniciales.put(cuentaContable.getId(), si);
						} else {
							movimientoCuenta.setDebeInicial(Utilitarios.redondeoValor(si.getDebeInicial()));
							movimientoCuenta.setHaberInicial(Utilitarios.redondeoValor(si.getHaberInicial()));
						}
							
						//double saldoInicial = ((Double) saldosInicialesMap.get(cuentaContable.getId())).doubleValue();
						//movimientoCuenta.setSaldoInicial(BigDecimal.valueOf(saldoInicial));
						//movimientoCuenta.setSaldoInicial(BigDecimal.valueOf(new Long("0").longValue()));
						movimientoCuenta.setCuentaId(cuentaContable.getId());
						movimientoCuenta.setFechaAsiento(fecha);
						
						//CarteraIf carteraTemp = SessionServiceLocator.getCarteraSessionService().getCartera(asientoIf.getTransaccionId());
						//ClienteOficinaIf clienteOficinaTemp = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(carteraTemp.getClienteoficinaId());
						//ClienteIf clienteTemp = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaTemp.getClienteId());
												
						//movimientoCuenta.setNumeroAsiento(clienteTemp.getIdentificacion());
						movimientoCuenta.setNumeroAsiento(asientoIf.getNumero());
						
						String glosa = asientoDetalleIf.getGlosa();
						if (glosa == null)
							glosa = asientoIf.getObservacion();
						if (glosa.length() > 100)
							glosa = glosa.substring(0, 100);
						
						movimientoCuenta.setGlosa(glosa);
						movimientoCuenta.setDebe(Utilitarios.redondeoValor(asientoDetalleIf.getDebe()));
						movimientoCuenta.setHaber(Utilitarios.redondeoValor(asientoDetalleIf.getHaber()));
						movimientoCuenta.setCodigoCuenta(cuentaContable.getCodigo());
						movimientoCuenta.setNombreCuenta(cuentaContable.getNombre());
						movimientoCuenta.setCodigoComprobante(codigoComprobante);
						movimientoCuenta.setAsiento(asientoIf);
						movimientoCuentaList.add(movimientoCuenta);

						if (!codigoCuentaAnterior.equals(cuentaContable.getCodigo())) {
							
							if (contadorFilas!=0){							
								
								MovimientoCuentaData movimientoCuentaA1 = new MovimientoCuentaData();
								movimientoCuentaA1.setNumeroAsiento("");
								movimientoCuentaA1.setCodigoComprobante("");
								movimientoCuentaA1.setFechaAsiento("");
								movimientoCuentaA1.setGlosa("<html><b>TOTAL</b></html>");
								movimientoCuentaA1.setDebe(new BigDecimal(Utilitarios.redondeoValor(totalDebeTabla)));
								movimientoCuentaA1.setHaber(new BigDecimal(Utilitarios.redondeoValor(totalHaberTabla)));
							 
								listaMovimiento.add(movimientoCuentaA1);
								totalDebeTabla = 0D;
								totalHaberTabla = 0D;
							
								MovimientoCuentaData movimientoCuentaA = new MovimientoCuentaData();
								movimientoCuentaA.setNumeroAsiento("");
								movimientoCuentaA.setCodigoComprobante("");
								movimientoCuentaA.setFechaAsiento("");
								movimientoCuentaA.setGlosa("");
								movimientoCuentaA.setDebe(new BigDecimal("0"));
								movimientoCuentaA.setHaber(new BigDecimal("0"));	
								 
								listaMovimiento.add(movimientoCuentaA);
							}
							 
							MovimientoCuentaData movimientoCuentaB = new MovimientoCuentaData();
							movimientoCuentaB.setNumeroAsiento("<html><b>" + cuentaContable.getCodigo() + "</b></html>");
							movimientoCuentaB.setCodigoComprobante("<html><b>" + cuentaContable.getNombre() + "</b></html>");
							movimientoCuentaB.setFechaAsiento("");
							movimientoCuentaB.setGlosa("");
							movimientoCuentaB.setDebe(new BigDecimal("0"));
							movimientoCuentaB.setHaber(new BigDecimal("0"));
							movimientoCuentaB.setCodigoCuenta("algo");
						 
							listaMovimiento.add(movimientoCuentaB);
						 
							MovimientoCuentaData movimientoCuentaC = new MovimientoCuentaData();
							movimientoCuentaC.setNumeroAsiento("");
							movimientoCuentaC.setCodigoComprobante("");
							movimientoCuentaC.setFechaAsiento("");
							movimientoCuentaC.setGlosa("<html><b>SALDO INICIAL</b></html>");
							movimientoCuentaC.setDebe(new BigDecimal(Utilitarios.redondeoValor(si.getDebeInicial().doubleValue())));
							movimientoCuentaC.setHaber(new BigDecimal(Utilitarios.redondeoValor(si.getHaberInicial().doubleValue())));
							movimientoCuentaC.setCodigoCuenta("algo");	
						 
							listaMovimiento.add(movimientoCuentaC);
				
							contadorFilas++;
						}
						
						codigoCuentaAnterior = cuentaContable.getCodigo();
						
						if (movimientosAgregadosMap.get(asientoDetalleIf.getId()) == null) {
						 	
							MovimientoCuentaData movimientoCuentaD = new MovimientoCuentaData();
							
							movimientoCuentaD.setNumeroAsiento(movimientoCuenta.getNumeroAsiento());
							//movimientoCuentaD.setNumeroAsiento(clienteTemp.getIdentificacion());
							
							movimientoCuentaD.setCodigoComprobante(movimientoCuenta.getCodigoComprobante());
							movimientoCuentaD.setFechaAsiento(movimientoCuenta.getFechaAsiento());
							movimientoCuentaD.setGlosa(movimientoCuenta.getGlosa());
							movimientoCuentaD.setDebe(new BigDecimal(movimientoCuenta.getDebe().doubleValue()));
							movimientoCuentaD.setHaber(new BigDecimal(movimientoCuenta.getHaber().doubleValue()));
							movimientoCuentaD.setAsiento(asientoIf);
							 
							double debe = Utilitarios.redondeoValor(movimientoCuenta.getDebe().doubleValue());
							totalDebeTabla += debe;
							
							double haber = Utilitarios.redondeoValor(movimientoCuenta.getHaber().doubleValue());
							totalHaberTabla += haber;
							
							listaMovimiento.add(movimientoCuentaD);
							movimientosAgregadosMap.put(asientoDetalleIf.getId(), asientoDetalleIf);
						} 
						}
					}
				}

				MovimientoCuentaData movimientoCuentaC = new MovimientoCuentaData();
				movimientoCuentaC.setNumeroAsiento("");
				movimientoCuentaC.setCodigoComprobante("");
				movimientoCuentaC.setFechaAsiento("");
				movimientoCuentaC.setGlosa("<html><b>TOTAL</b></html>");
			
				System.out.println("valor de HH:"+h);
			  
				movimientoCuentaC.setDebe(new BigDecimal(Utilitarios.redondeoValor(totalDebeTabla)));
				movimientoCuentaC.setHaber(new BigDecimal(Utilitarios.redondeoValor(totalHaberTabla)));
				
				
				listaMovimiento.add(movimientoCuentaC);
			 
				totalDebeTabla = 0D;
				totalHaberTabla = 0D;
				//--------------------------------
				   
				modelTblMovimientoCuenta.refresh(listaMovimiento);
			
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("");
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("");
		}
	}	
}
