package com.spirit.contabilidad.gui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.CompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.SubtipoAsientoIf;
import com.spirit.contabilidad.entity.TipoAsientoIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.controller.PopupInfoAsiento;
import com.spirit.contabilidad.gui.panel.JPDiarioGeneral;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class DiarioGeneralModel extends JPDiarioGeneral {
	private PlanCuentaIf planCuenta;
	private static PeriodoIf periodo;
	private static Date fechaInicioCalculo;
	private static Date fechaInicioTemp;
	private static Date fechaFinCalculo;
	private static Date fechaFinTemp;
	private DefaultTableModel tableModel;
	private Map asientoMap = new HashMap();
	private Long idPlanCuenta;
	private Long idPeriodo;
	private String status = "A";
	private boolean asientoDiferente = true;
	Vector<Object> filaAnterior = new Vector<Object>();
	private PopupInfoAsiento ppInfoAsiento;
	private int asientoSeleccionado;
	private AsientoIf asientoIf;
	private Vector asientoDetalleVector = new Vector();
	private AsientoDetalleIf asientoDetalleIf;
	private int asientoDetalleSeleccionado;
	private int numeroFilas = 0;
	Vector<Boolean> filasPintadas = new Vector<Boolean>();
	//private boolean cambioAsiento = false;
	private TipoDocumentoIf tipoDocumentoIf = null;
	private double totalDebe, totalHaber;
	private DecimalFormat formatoSerial = new DecimalFormat("0000000");
	private final static String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private final static String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String NOMBRE_ESTADO_ASIENTO = "ASIENTO";
	private static final String NOMBRE_ESTADO_PRE_ASIENTO = "PRE-ASIENTO";
	private static final String NOMBRE_ESTADO_TODOS = "TODOS";
	private static final String ESTADO_ASIENTO = NOMBRE_ESTADO_ASIENTO.substring(0,1);
	private static final String ESTADO_PRE_ASIENTO = NOMBRE_ESTADO_PRE_ASIENTO.substring(0,1);
	private static final String ESTADO_TODOS = "%";
	private static final String NOMBRE_MENU_DIARIOGENERAL = "DIARIO GENERAL";
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private Map cuentasMap = new HashMap();
	private Map tiposDocumentoMap = new HashMap();
	private Map modulosMap = new HashMap();
	private Map facturasMap = new HashMap();
	private Map comprasMap = new HashMap();
	private Map carterasMap = new HashMap();
	private Map notasCreditoMap = new HashMap();
	private EmpresaIf empresa = null;
	
	public DiarioGeneralModel() {
		getTblDiario().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		initKeyListeners();
		showSaveMode();
		initListeners();
		initMapas();
		cargarCombos();
		anchoColumnasTabla();
		getTblDiario().addMouseListener(oMouseAdapterTblDiario);
		getTblDiario().addKeyListener(oKeyAdapterTblDiario);
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getBtnConsultar().setToolTipText("Consultar Diario General");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		getCmbFechaIni().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaIni().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaIni().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaIni().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
		
		getBtnConsultar().addKeyListener(oKeyAdapterBtnConsultar);
		
		getCmbStatus().setNextFocusableComponent(getBtnConsultar());
	}	
	
	private void initMapas() {
		empresa = (EmpresaIf) Parametros.getEmpresa();
		tiposDocumentoMap = mapearTiposDocumento();
		modulosMap = mapearModulos();
	} 
	
	KeyListener oKeyAdapterBtnConsultar = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				setCursor(SpiritCursor.hourglassCursor);
				clean();
				cargarTabla();
				setCursor(SpiritCursor.normalCursor);
			}
		}
	};
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblDiario().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblDiario().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblDiario().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblDiario().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(35);
		anchoColumna = getTblDiario().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(210);
		anchoColumna = getTblDiario().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(35);
		anchoColumna = getTblDiario().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(35);	
	}
	
	//Cargo los listeners de los combos
	private void initListeners(){
		//Listener del comboPlanCuenta
		getCmbPlanCuenta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getModel().getSelectedItem();
				if ( planCuenta != null )
					getCmbEjercicioContable().setEnabled(true);
			}
		});				
		
		//Listener del comboPeriodo
		getCmbEjercicioContable().addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent evento) {
				periodo = (PeriodoIf) getCmbEjercicioContable().getModel().getSelectedItem();
				if(periodo != null){
					//Seteo la fecha de inicio segun la fecha de inicio del periodo contable
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(periodo.getFechaini());
					fechaInicioCalculo = periodo.getFechaini();
					fechaInicioTemp = periodo.getFechaini();
					
					//Seteo la fecha de fin segun el la fecha de fin del periodo contable
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(periodo.getFechafin());
					fechaFinCalculo = periodo.getFechafin();
					fechaFinTemp = periodo.getFechafin();
					
					getCmbFechaIni().setCalendar(calendarInicio);
					getCmbFechaFin().setCalendar(calendarFin);
				}else{
					SpiritAlert.createAlert("Debe al menos existir un Periodo", SpiritAlert.WARNING);
				}
			}
		});
				
		//Veo si la fecha Inicio esta dentro del periodo contable
		getCmbFechaIni().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				fechaInicioCalculo = (Date) ((DateComboBox) evento.getSource()).getDate();
				clean();
				if(fechaInicioCalculo.before(periodo.getFechaini()) || fechaInicioCalculo.after(periodo.getFechafin())) {
					SpiritAlert.createAlert("Por favor seleccione una fecha dentro del periodo contable!", SpiritAlert.INFORMATION);
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(fechaInicioTemp);
					fechaInicioCalculo = fechaInicioTemp;
					getCmbFechaIni().setCalendar(calendarInicio);
				} else if(fechaInicioCalculo.after(fechaFinCalculo)) {
					SpiritAlert.createAlert("La fecha de inicio no puede ser despues de la fecha de fin!", SpiritAlert.INFORMATION);
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(fechaInicioTemp);
					fechaInicioCalculo = fechaInicioTemp;
					getCmbFechaIni().setCalendar(calendarInicio);
				} else if(!fechaInicioCalculo.equals(fechaInicioTemp) || !fechaFinCalculo.equals(fechaFinTemp)) {
					fechaInicioTemp = fechaInicioCalculo;
				}
			}
		});
		
		//Veo si la fecha Fin esta dentro del periodo contable
		getCmbFechaFin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				fechaFinCalculo = (Date) ((DateComboBox) evento.getSource()).getDate();
				clean();
				if(fechaFinCalculo.after(periodo.getFechafin()) || fechaFinCalculo.before(periodo.getFechaini())) {
					SpiritAlert.createAlert("Por favor seleccione una fecha dentro del periodo contable!", SpiritAlert.INFORMATION);
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(fechaFinTemp);
					fechaFinCalculo = fechaFinTemp;
					getCmbFechaFin().setCalendar(calendarFin);
				} else if(fechaFinCalculo.before(fechaInicioCalculo)) {
					SpiritAlert.createAlert("La fecha final no puede ser antes de la fecha de inicio!", SpiritAlert.INFORMATION);
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(fechaFinTemp);
					fechaFinCalculo = fechaFinTemp;
					getCmbFechaFin().setCalendar(calendarFin);
				} else if (!fechaFinCalculo.equals(fechaFinTemp) || !fechaInicioCalculo.equals(fechaInicioTemp)) {
					fechaFinTemp = fechaFinCalculo;
				}
			}
		});
		
		getCmbTipoDocumento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoDocumento().getSelectedItem() != null)
					if (!getCmbTipoDocumento().getSelectedItem().toString().equals("TODOS") && !getCmbTipoDocumento().getSelectedItem().toString().equals("DIARIOS"))
						tipoDocumentoIf = (TipoDocumentoIf) getCmbTipoDocumento().getSelectedItem();
					else
						tipoDocumentoIf = null;
			}
		});
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setCursor(SpiritCursor.hourglassCursor);
				clean();
				
				long start=System.currentTimeMillis();	
				
				cargarTabla();
				
				long fin=System.currentTimeMillis();
				System.out.println("---------------------tiempo de todo procesoA: "+(fin-start)/1000+" seg");
				
				setCursor(SpiritCursor.normalCursor);
			}
		});
	}
	
	public void report() {
		if (getMode() == SpiritMode.UPDATE_MODE) {
			try {
				DefaultTableModel tblModelReporte = (DefaultTableModel) getTblDiario().getModel();
				if (tblModelReporte.getRowCount() > 0) {
					String si = "Si";
	    	        String no = "No";
	    	        Object[] options ={si,no}; 
	    			int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir el Diario General?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if(opcion == JOptionPane.YES_OPTION) {
						String fileName = "jaspers/contabilidad/RPDiario.jasper";
						HashMap parametrosMap = new HashMap();
						//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_DIARIOGENERAL).iterator().next();
						MenuIf menu = null;
						Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_DIARIOGENERAL).iterator();
						if(menuIT.hasNext()) menu = (MenuIf)menuIT.next();
					
						
						
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
						String yearInicioCalculo = String.valueOf(fechaInicioCalculo.getYear() + 1900);
						String monthInicioCalculo = String.valueOf(fechaInicioCalculo.getMonth() + 1);
						String dayInicioCalculo = String.valueOf(fechaInicioCalculo.getDate());
						String yearFinCalculo = String.valueOf(fechaFinCalculo.getYear() + 1900);
						String monthFinCalculo = String.valueOf(fechaFinCalculo.getMonth() + 1);
						String dayFinCalculo = String.valueOf(fechaFinCalculo.getDate());
						String fechaInicioCalculo = Utilitarios.getNombreMes(Integer.parseInt(monthInicioCalculo)) + " " + dayInicioCalculo + " DEL " + yearInicioCalculo;
						String fechaFinCalculo = Utilitarios.getNombreMes(Integer.parseInt(monthFinCalculo)) + " " + dayFinCalculo + " DEL " + yearFinCalculo;
						//parametrosMap.put("fechaIniPer", fechaInicioPeriodo);
						//parametrosMap.put("fechaFinPer", fechaFinPeriodo);
						parametrosMap.put("codigoPlanCuenta", planCuenta.getCodigo());
						//parametrosMap.put("nombrePlanCuenta", planCuenta.getNombre());
						parametrosMap.put("fechaInicial",  fechaInicioCalculo);
						parametrosMap.put("fechaFinal", fechaFinCalculo);
						parametrosMap.put("colorFila", filasPintadas);
						parametrosMap.put("totalDebe", formatoDecimal.format(totalDebe));
						parametrosMap.put("totalHaber", formatoDecimal.format(totalHaber));
						
						if(getCmbStatus().getSelectedItem() == NOMBRE_ESTADO_ASIENTO)
							parametrosMap.put("estado", NOMBRE_ESTADO_ASIENTO);
						else if(getCmbStatus().getSelectedItem() == NOMBRE_ESTADO_PRE_ASIENTO)
							parametrosMap.put("estado", NOMBRE_ESTADO_PRE_ASIENTO); 
						else if(getCmbStatus().getSelectedItem() == NOMBRE_ESTADO_TODOS)
							parametrosMap.put("estado", "Todos");
						
						ReportModelImpl.processReport(fileName, parametrosMap, tblModelReporte, true);
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
	}
	
	public void refresh() {
		cargarComboPlanCuenta();
		cargarComboPeriodo();
		cargarComboTipoDocumento();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	MouseListener oMouseAdapterTblDiario = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblDiario = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		SubtipoAsientoIf subTipoAsientoIf = null;
		TipoAsientoIf tipoAsientoIf = null;
		
		try {
			if ( ((JTable)evt.getSource()).getSelectedRow() != -1 ){
				//Obtengo la instancia del objeto seleccionado de la tabla
				setAsientoSeleccionado(((JTable)evt.getSource()).getSelectedRow());
				setAsientoDetalleSeleccionado(((JTable)evt.getSource()).getSelectedRow());
				asientoIf = (AsientoIf)  asientoMap.get(getAsientoSeleccionado());
				asientoDetalleIf = (AsientoDetalleIf)  getAsientoDetalleVector().get(getAsientoDetalleSeleccionado());
				
				if(asientoIf.getSubtipoasientoId() != null)
					subTipoAsientoIf = SessionServiceLocator.getSubTipoAsientoSessionService().getSubtipoAsiento(asientoIf.getSubtipoasientoId());
				if(subTipoAsientoIf != null)
					tipoAsientoIf = SessionServiceLocator.getTipoAsientoSessionService().getTipoAsiento(subTipoAsientoIf.getTipoId()); 
				
				if(tipoAsientoIf == null && subTipoAsientoIf == null)
					ppInfoAsiento = new PopupInfoAsiento("", "", asientoIf, asientoDetalleIf.getReferencia(), asientoDetalleIf.getGlosa()); 
				else if(subTipoAsientoIf == null)
					ppInfoAsiento = new PopupInfoAsiento(tipoAsientoIf.getNombre(), "", asientoIf, asientoDetalleIf.getReferencia(), asientoDetalleIf.getGlosa()); 
				else if(tipoAsientoIf == null)
					ppInfoAsiento = new PopupInfoAsiento("", subTipoAsientoIf.getNombre(), asientoIf, asientoDetalleIf.getReferencia(), asientoDetalleIf.getGlosa()); 
				else if(tipoAsientoIf != null && subTipoAsientoIf != null)
					ppInfoAsiento = new PopupInfoAsiento(tipoAsientoIf.getNombre(), subTipoAsientoIf.getNombre(), asientoIf, asientoDetalleIf.getReferencia(), asientoDetalleIf.getGlosa()); 
				
				ppInfoAsiento.setOwner(getTblDiario());
				ppInfoAsiento.setAttachable(false);
				//ppInfoAsiento.setFocusable(true);
				
				if (ppInfoAsiento.isPopupVisible())
					ppInfoAsiento.hidePopup();
				else
					ppInfoAsiento.showPopup((Toolkit.getDefaultToolkit().getScreenSize().width - 400) / 2,(Toolkit.getDefaultToolkit().getScreenSize().height - 450) / 2);
				
				getTblDiario().clearSelection();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void clean() {
		filasPintadas.removeAllElements();

		DefaultTableModel model = (DefaultTableModel) getTblDiario().getModel();
		
		for(int i= this.getTblDiario().getRowCount();i>0;--i)
			model.removeRow(i-1);
		
		
		getCmbPlanCuenta().setFocusable(true);
		
		getCmbPlanCuenta().grabFocus();
		
		
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}

	private void cargarCombos() {
		cargarComboPlanCuenta();
		cargarComboPeriodo();
		cargarComboTipoDocumento();
		getCmbStatus().addItem(NOMBRE_ESTADO_ASIENTO);
		getCmbStatus().addItem(NOMBRE_ESTADO_PRE_ASIENTO);
		getCmbStatus().addItem(NOMBRE_ESTADO_TODOS);
	}
	
	private void cargarComboPlanCuenta(){
		List planesCuenta = ContabilidadFinder.findPlanCuentaActivo(Parametros.getIdEmpresa());
		refreshCombo(getCmbPlanCuenta(),planesCuenta);
		if (getCmbPlanCuenta().getSelectedItem() == null)
			PlanCuentaModel.seleccionarPlanCuentaPredeterminado(getCmbPlanCuenta());
	}
	
	private void cargarComboPeriodo(){
		List periodos = ContabilidadFinder.findPeriodo(Parametros.getIdEmpresa());
		refreshCombo(getCmbEjercicioContable(),periodos);
		PeriodoModel.seleccionarPeriodoActivo(getCmbEjercicioContable());
	}
	
	private void cargarComboTipoDocumento() {
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			parameterMap.put("estado", "A");
			List tiposDocumento = (List) SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQuery(parameterMap);
			tiposDocumento.add("DIARIOS");
			tiposDocumento.add("TODOS");
			refreshCombo(getCmbTipoDocumento(), tiposDocumento);
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
		private Color color1 = new Color(135, 206, 250);
		private Color color2 = new Color(244, 255, 255);
        
		public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

        	Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        	if (filasPintadas.get(row))
        		c.setBackground(color2);
        	else
        		c.setBackground(color1);
        	
        	if(column == 0)
        		setHorizontalAlignment(JLabel.CENTER);
        	if(column == 1)
        		setHorizontalAlignment(JLabel.CENTER);
        	if(column == 2)
        		setHorizontalAlignment(JLabel.LEFT);
        	if(column == 3)
        		setHorizontalAlignment(JLabel.RIGHT);
        	if(column == 4)
        		setHorizontalAlignment(JLabel.LEFT);
        	if(column == 5)
        		setHorizontalAlignment(JLabel.RIGHT);
        	if(column == 6)
        		setHorizontalAlignment(JLabel.RIGHT);
        	
        	return c;
       }
    };

	private void cargarTabla() {
		if (planCuenta != null){
			//cambioAsiento = true;
			idPlanCuenta = planCuenta.getId();
			totalDebe = 0D; totalHaber = 0D;
			
			if (periodo != null){
				java.sql.Date fechaInicioMovimiento = new java.sql.Date(fechaInicioCalculo.getYear(),fechaInicioCalculo.getMonth(),fechaInicioCalculo.getDate());
				java.sql.Date fechaFinMovimiento = new java.sql.Date(fechaFinCalculo.getYear(),fechaFinCalculo.getMonth(),fechaFinCalculo.getDate());
				
				cuentasMap = mapearCuentas(idPlanCuenta);
				ModuloIf modulo = null;
				
				if (tipoDocumentoIf != null)
					modulo = (ModuloIf) modulosMap.get(tipoDocumentoIf.getModuloId());
				
				if (modulo == null || modulo.getCodigo().equals("FACT"))
					facturasMap = mapearFacturas(fechaInicioMovimiento, fechaFinMovimiento);
								
				if (modulo == null || modulo.getCodigo().equals("COMP"))
					comprasMap = mapearCompras(fechaInicioMovimiento, fechaFinMovimiento);
				
				//391 segundos :531:407:375:438:453:391				
				
				if (modulo == null || modulo.getCodigo().equals("CART")) {
					notasCreditoMap = mapearNotasCredito();
					carterasMap = mapearCarteras(fechaInicioMovimiento, fechaFinMovimiento);
				}
				
				idPeriodo = periodo.getId();
				
				try {
					if(getCmbStatus().getSelectedItem() == NOMBRE_ESTADO_ASIENTO)
						status = ESTADO_ASIENTO;
					if(getCmbStatus().getSelectedItem() == NOMBRE_ESTADO_PRE_ASIENTO)
						status = ESTADO_PRE_ASIENTO;
					if(getCmbStatus().getSelectedItem() == NOMBRE_ESTADO_TODOS)
						status = ESTADO_TODOS;
					
					Map parameterMap = new HashMap();
					parameterMap.put("empresaId", Parametros.getIdEmpresa());
					parameterMap.put("periodoId", idPeriodo);
					parameterMap.put("plancuentaId", idPlanCuenta);
					parameterMap.put("status", status);
					if (tipoDocumentoIf != null)
						parameterMap.put("tipoDocumentoId", tipoDocumentoIf.getId());
					if (getCmbTipoDocumento().getSelectedItem().toString().equals("DIARIOS"))
						parameterMap.put("diarios", "S");
					else
						parameterMap.put("diarios", "N");
					
							 
					//703
					Iterator diariosIterator = SessionServiceLocator.getAsientoSessionService().findDiariosByQueryAndByFechaInicioAndFechaFin(parameterMap, fechaInicioMovimiento, fechaFinMovimiento, true).iterator();
		 					 					
					
					if (!asientoMap.isEmpty())
						asientoMap.clear();
					
					numeroFilas = 0;
					long idAsientoAnterior = 0L;
					boolean cambioAsiento = true;
		 					
					while (diariosIterator.hasNext()) {
						Object[] diario = (Object[]) diariosIterator.next();
						AsientoIf asientoIf = (AsientoIf) diario[0];
						AsientoDetalleIf asientoDetalleIf = (AsientoDetalleIf) diario[1];
						tableModel = (DefaultTableModel) getTblDiario().getModel();
						Vector<Object> fila = new Vector<Object>();
						asientoMap.put(numeroFilas, asientoIf);
						getAsientoDetalleVector().add(asientoDetalleIf);
						
						if (asientoIf.getId().compareTo(idAsientoAnterior) == 0) {
							asientoDiferente = false;
							//filasPintadas.add(true);
							filasPintadas.add(filasPintadas.get(filasPintadas.size() - 1).booleanValue());
						} else {
							asientoDiferente = true;
							if (cambioAsiento)
								filasPintadas.add(false);
							else
								filasPintadas.add(true);
							
							cambioAsiento = !cambioAsiento;
							filaAnterior.removeAllElements();
						}
												
						idAsientoAnterior = asientoIf.getId();
						agregarFilaTabla(asientoIf, asientoDetalleIf, fila, filaAnterior);
											
						
						tableModel.addRow(fila);
						++numeroFilas;
					}
									
					for (int i = 0; i < getTblDiario().getColumnCount(); i++)
						getTblDiario().getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
					
					setUpdateMode();
				
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}
			} else {
				SpiritAlert.createAlert("Debe seleccionar el periodo contable", SpiritAlert.WARNING);
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar el plan de cuentas", SpiritAlert.WARNING);
		}
	}
	
	private void agregarFilaTabla(AsientoIf asientoIf, AsientoDetalleIf asientoDetalleIf, Vector<Object> fila, Vector<Object> filaAnterior){
		if (asientoDiferente) {
			fila.add(Utilitarios.getFechaCortaUppercase(asientoIf.getFecha()));
			filaAnterior.add(asientoIf.getFecha().toString());
			fila.add(asientoIf.toString());
			filaAnterior.add(asientoIf.toString());
			String numeroComprobante = obtenerNumeroComprobante(asientoIf);
			fila.add(numeroComprobante);
			//CuentaIf cuentaIf = (CuentaIf) CuentaModel.getCuentaSessionService().getCuenta(asientoDetalleIf.getCuentaId());
			CuentaIf cuentaIf = (CuentaIf) cuentasMap.get(asientoDetalleIf.getCuentaId());
			fila.add(cuentaIf.getCodigo());
			
			if (asientoDetalleIf != null) {
				if(asientoDetalleIf.getHaber() != null) {
					if(asientoDetalleIf.getHaber().compareTo(BigDecimal.ZERO) == 0)
						fila.add(cuentaIf.getNombre());
					else 
						fila.add("          " + cuentaIf.getNombre());
				}else if(asientoDetalleIf.getDebe() != null) {
					if(asientoDetalleIf.getDebe().compareTo(BigDecimal.ZERO) == 0)
						fila.add("          " + cuentaIf.getNombre());
					else 
						fila.add(cuentaIf.getNombre());
				}
			
				if (asientoDetalleIf.getDebe() != null)
					fila.add(formatoDecimal.format(asientoDetalleIf.getDebe()));
				if (asientoDetalleIf.getHaber() != null)
					fila.add(formatoDecimal.format(asientoDetalleIf.getHaber()));
			
			}
			
			//asientoDiferente = false;
		} else {
			if (!filaAnterior.get(1).equals(asientoIf)) {
				fila.add(Utilitarios.getFechaCortaUppercase(asientoIf.getFecha()));
				fila.add(asientoIf.toString());
				String numeroComprobante = obtenerNumeroComprobante(asientoIf);
				fila.add(numeroComprobante);
				//CuentaIf cuentaIf = (CuentaIf) CuentaModel.getCuentaSessionService().getCuenta(asientoDetalleIf.getCuentaId());
				CuentaIf cuentaIf = (CuentaIf) cuentasMap.get(asientoDetalleIf.getCuentaId());
				fila.add(cuentaIf.getCodigo());
				
				if (asientoDetalleIf != null) {
					if(asientoDetalleIf.getHaber() != null) {
						if(asientoDetalleIf.getHaber().compareTo(BigDecimal.ZERO) == 0)
							fila.add(cuentaIf.getNombre());
						else 
							fila.add("          " + cuentaIf.getNombre());
					}else if(asientoDetalleIf.getDebe() != null) {
						if(asientoDetalleIf.getDebe().compareTo(BigDecimal.ZERO) == 0)
							fila.add("          " + cuentaIf.getNombre());
						else 
							fila.add(cuentaIf.getNombre());
					}
				
					if (asientoDetalleIf.getDebe() != null)
						fila.add(formatoDecimal.format(asientoDetalleIf.getDebe()));
					if (asientoDetalleIf.getHaber() != null)
						fila.add(formatoDecimal.format(asientoDetalleIf.getHaber()));

				}
				
				//asientoDiferente = true;
			} else {
				fila.add("");
				fila.add("");
				String numeroComprobante = obtenerNumeroComprobante(asientoIf);
				fila.add(numeroComprobante);
				//CuentaIf cuentaIf = (CuentaIf) CuentaModel.getCuentaSessionService().getCuenta(asientoDetalleIf.getCuentaId());
				CuentaIf cuentaIf = (CuentaIf) cuentasMap.get(asientoDetalleIf.getCuentaId());
				fila.add(cuentaIf.getCodigo());
				
				if(asientoDetalleIf.getHaber() != null) {
					if(asientoDetalleIf.getHaber().compareTo(BigDecimal.ZERO) == 0)
						fila.add(cuentaIf.getNombre());
					else 
						fila.add("          " + cuentaIf.getNombre());
				}else if(asientoDetalleIf.getDebe() != null) {
					if(asientoDetalleIf.getDebe().compareTo(BigDecimal.ZERO) == 0)
						fila.add("          " + cuentaIf.getNombre());
					else 
						fila.add(cuentaIf.getNombre());
				}
				
				if (asientoDetalleIf.getDebe() != null)
					fila.add(formatoDecimal.format(asientoDetalleIf.getDebe()));
				if (asientoDetalleIf.getHaber() != null)
					fila.add(formatoDecimal.format(asientoDetalleIf.getHaber()));
				
			}
		}
		
		if (asientoDetalleIf.getDebe() != null)
			totalDebe += asientoDetalleIf.getDebe().doubleValue();
		if (asientoDetalleIf.getHaber() != null)
			totalHaber += asientoDetalleIf.getHaber().doubleValue();
	}
	
	private List<AsientoDetalleIf> getAsientosDetallesListByAsientoId(Collection asientosDetallesColeccion, Long idAsiento) {
		List<AsientoDetalleIf> asientosDetallesListByAsientoId = new ArrayList<AsientoDetalleIf>();
		Iterator asientosDetallesIterator = asientosDetallesColeccion.iterator();
		
		while (asientosDetallesIterator.hasNext()) {
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) asientosDetallesIterator.next();
			if (asientoDetalle.getAsientoId().compareTo(idAsiento) == 0)
				asientosDetallesListByAsientoId.add(asientoDetalle);
		}
		
		return asientosDetallesListByAsientoId;
	}
	
	private Map mapearCuentas(Long idPlanCuenta) {
		Map cuentasMap = new HashMap();
		try {
			Iterator cuentasIterator = SessionServiceLocator.getCuentaSessionService().findCuentaByPlancuentaId(idPlanCuenta).iterator();
			while (cuentasIterator.hasNext()) {
				CuentaIf cuenta = (CuentaIf) cuentasIterator.next();
				cuentasMap.put(cuenta.getId(), cuenta);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return cuentasMap;
	}
	
	private Map mapearFacturas(java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		Map facturasMap = new LinkedHashMap();
		try {
			Iterator facturasIterator = SessionServiceLocator.getFacturaSessionService().findFacturaByEmpresaIdByFechaInicioAndFechaFin(Parametros.getIdEmpresa(), fechaInicio, fechaFin).iterator();
			while (facturasIterator.hasNext()) {
				FacturaIf factura = (FacturaIf) facturasIterator.next();
				facturasMap.put(factura.getId(), factura);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return facturasMap;
	}
	
	private Map mapearCompras(java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		Map comprasMap = new HashMap();
		try {
			Iterator comprasIterator = SessionServiceLocator.getCompraSessionService().findCompraByEmpresaIdByFechaInicioAndFechaFin(Parametros.getIdEmpresa(), fechaInicio, fechaFin).iterator();
			while (comprasIterator.hasNext()) {
				CompraIf compra = (CompraIf) comprasIterator.next();
				comprasMap.put(compra.getId(), compra);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return comprasMap;
	}
	
	private Map mapearCarteras(java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		Map carterasMap = new HashMap();
		try {
			Iterator carterasIterator = SessionServiceLocator.getCarteraSessionService().findCarteraByEmpresaIdByFechaInicioAndFechaFin(Parametros.getIdEmpresa(), fechaInicio, fechaFin).iterator();
			while (carterasIterator.hasNext()) {
				CarteraIf cartera = (CarteraIf) carterasIterator.next();
				carterasMap.put(cartera.getId(), cartera);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return carterasMap;
	}
	
	private Map mapearNotasCredito() {
		Map notasCreditoMap = new HashMap();
		try {
			Iterator notasCreditoIterator = SessionServiceLocator.getNotaCreditoSessionService().findNotaCreditoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (notasCreditoIterator.hasNext()) {
				NotaCreditoIf notaCredito = (NotaCreditoIf) notasCreditoIterator.next();
				notasCreditoMap.put(notaCredito.getId(), notaCredito);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return notasCreditoMap;
	}
	
	private Map mapearTiposDocumento() {
		Map tiposDocumentoMap = new HashMap();
		
		try {
			Iterator tiposDocumentoIterator = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (tiposDocumentoIterator.hasNext()) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoIterator.next();
				tiposDocumentoMap.put(tipoDocumento.getId(), tipoDocumento);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return tiposDocumentoMap;
	}
	
	private Map mapearModulos() {
		Map modulosMap = new HashMap();
		
		try {
			Iterator modulosIterator = SessionServiceLocator.getModuloSessionService().getModuloList().iterator();
			while (modulosIterator.hasNext()) {
				ModuloIf modulo = (ModuloIf) modulosIterator.next();
				modulosMap.put(modulo.getId(), modulo);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return modulosMap;
	}
	
	private String obtenerNumeroComprobante(AsientoIf asiento) {
		String numeroComprobante = "";
		Long idTipoDocumento = asiento.getTipoDocumentoId();
		Long idTransaccion = asiento.getTransaccionId();
		
		try{
			if (idTipoDocumento != null) {
				TipoDocumentoIf tipoDocumento = (TipoDocumentoIf) tiposDocumentoMap.get(idTipoDocumento);
				ModuloIf modulo = (ModuloIf) modulosMap.get(tipoDocumento.getModuloId());
				String preimpreso = "";
				String cliente = "";
				
				if (modulo.getCodigo().equals("FACT")) {
					if (idTransaccion != null) {
						preimpreso = facturasMap.get(idTransaccion)!=null?((FacturaIf) facturasMap.get(idTransaccion)).getPreimpresoNumero():null;
						cliente = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaIdNombreLegal(((FacturaIf) facturasMap.get(idTransaccion)).getClienteoficinaId());
						
						if (preimpreso == null)
							preimpreso = "";
						
						numeroComprobante = "F/C " + preimpreso + " -- " + cliente;
					}
				} else if (modulo.getCodigo().equals("COMP")) {
					if (idTransaccion != null) {
						preimpreso = ((CompraIf) comprasMap.get(idTransaccion)).getPreimpreso();
						cliente = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaIdNombreLegal(((CompraIf) comprasMap.get(idTransaccion)).getProveedorId());
						
						if (preimpreso == null)
							preimpreso = "";
						
						numeroComprobante = "F/P " + preimpreso + " -- " + cliente;
					}
				} else if (modulo.getCodigo().equals("CART")) {
					if (tipoDocumento.getCodigo().equals("NCC") || tipoDocumento.getCodigo().equals("NCP")) {
						if (idTransaccion != null) {
							preimpreso = ((NotaCreditoIf) notasCreditoMap.get(idTransaccion)).getPreimpreso();
							cliente = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaIdNombreLegal(((NotaCreditoIf) notasCreditoMap.get(idTransaccion)).getOperadorNegocioId());
							
							if (preimpreso == null)
								preimpreso = "";
							
							numeroComprobante = "N/C " + preimpreso.substring(8,15) + " -- " + cliente;
						}
					} else {
						if (idTransaccion != null) {
							CarteraIf carteraIf = null;
							
							if(carterasMap.get(idTransaccion) != null){
								carteraIf = (CarteraIf) carterasMap.get(idTransaccion);
							}else if(SessionServiceLocator.getCarteraSessionService().getCartera(idTransaccion) != null){
								carteraIf = SessionServiceLocator.getCarteraSessionService().getCartera(idTransaccion);
							}
							
							if(carteraIf != null){
								cliente = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaIdNombreLegal((carteraIf).getClienteoficinaId());
							}							
							
							String cartera = "";
							if(carteraIf != null && empresa.getCodigo().equals(tipoDocumento.getCodigo())){
								cartera = (carteraIf).getCodigo().split(empresa.getCodigo()+"-")[2];
							}else if(carteraIf != null){
								cartera = (carteraIf).getCodigo().split(empresa.getCodigo()+"-")[1];
							}
							
							if (empresa.getCodigo().equals(tipoDocumento.getCodigo()))
								numeroComprobante = empresa.getCodigo() + "-" + cartera + " -- " + cliente;
							else {
								numeroComprobante = cartera + " -- " + cliente;
							}
						}
					}
				}
			}
		}catch (GenericBusinessException e){
			e.printStackTrace();
		}
		
		return numeroComprobante;
	}
	
	public int getAsientoSeleccionado() {
		return asientoSeleccionado;
	}
	
	public void setAsientoSeleccionado(int selectedAsiento) {
		asientoSeleccionado = selectedAsiento;
	}
	
	public Vector getAsientoDetalleVector() {
		return asientoDetalleVector;
	}
	
	public void setAsientoDetalleVector(Vector asientoDetalleVec) {
		asientoDetalleVector = asientoDetalleVec;
	}
	
	public int getAsientoDetalleSeleccionado() {
		return asientoDetalleSeleccionado;
	}
	
	public void setAsientoDetalleSeleccionado(int selectedAsientoDetalle) {
		asientoDetalleSeleccionado = selectedAsientoDetalle;
	}
}
