package com.spirit.contabilidad.gui.model;

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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.SaldoCuentaIf;
import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.controller.PopupInfoAsientoDetalle;
import com.spirit.contabilidad.gui.criteria.CuentaCriteria;
import com.spirit.contabilidad.gui.panel.JPConsultaMayorGeneral;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CentroGastoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DepartamentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.LineaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.MyTableCellEditorNumber;
import com.spirit.util.Utilitarios;

public class ConsultaMayorGeneralModel extends JPConsultaMayorGeneral {
	private static final long serialVersionUID = -1212867567562835183L;
	private static PeriodoIf periodo;
	private static Date fechaInicioCalculo;
	private static Date fechaInicioTemp;
	private static Date fechaFinCalculo;
	private static Date fechaFinTemp;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	PlanCuentaIf planCuenta;
	private DefaultTableModel modelAsientoDetalle;
	private Vector asientoDetallesColeccion = new Vector();
	private CuentaIf cuentaContable;
	private JDPopupFinderModel popupFinder;
	private CuentaCriteria cuentaCriteria;
	private AsientoDetalleIf asientoDetalleIf;
	private PopupInfoAsientoDetalle ppInfoAsientoDetalle;
	private static final String NOMBRE_MENU_MAYORGENERAL = "CONSULTA MAYOR GENERAL";
	private Double saldoInicial;
	private Double saldoFinal;
	
	public ConsultaMayorGeneralModel() {
		getTblDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		initKeyListeners();
		initListeners();
		setSorterTable(getTblDetalle());
		showSaveMode();
		anchoColumnasTablas();
		getTblDetalle().addMouseListener(oMouseAdapterTblDetalle);
		getTblDetalle().addKeyListener(oKeyAdapterTblDetalle);
		getBtnConsultar().addKeyListener(oKeyAdapterBtnConsultar);
		getBtnBuscarCuenta().addKeyListener(oKeyAdapterBtnBuscarCuenta);
		getTxtSaldoInicial().setNextFocusableComponent(getBtnConsultar());
		new HotKeyComponent(this);
	}
	
	public void initKeyListeners(){
		getBtnConsultar().setToolTipText("Consultar Mayor General");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		getBtnBuscarCuenta().setToolTipText("Buscar Cuenta Contable");
		getBtnBuscarCuenta().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
				
		DefaultTableCellRenderer dtcrColumn = new DefaultTableCellRenderer();
		dtcrColumn.setHorizontalAlignment(JTextField.RIGHT);
		getTblDetalle().getColumnModel().getColumn(3).setCellEditor(new MyTableCellEditorNumber());
		getTblDetalle().getColumnModel().getColumn(3).setCellRenderer(dtcrColumn);
		getTblDetalle().getColumnModel().getColumn(4).setCellEditor(new MyTableCellEditorNumber());
		getTblDetalle().getColumnModel().getColumn(4).setCellRenderer(dtcrColumn);
	}
	
	private void anchoColumnasTablas() {
		TableColumn anchoColumna = getTblDetalle().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(60);		
		anchoColumna = getTblDetalle().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(80);		
		anchoColumna = getTblDetalle().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(350);		
		anchoColumna = getTblDetalle().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(90);		
		anchoColumna = getTblDetalle().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(90);
		
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
	}
	
 
	
	
	KeyListener oKeyAdapterBtnBuscarCuenta = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				cuentaCriteria = new CuentaCriteria("Buscar Cuenta", "S", planCuenta.getId(), 0L, "");
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), cuentaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					cuentaContable = (CuentaIf) popupFinder.getElementoSeleccionado();
					getTxtCuentaContable().setText(cuentaContable.getCodigo() + " - " + cuentaContable.getNombre());
					clean();
				}	
			}
		}
	};
	
	
	KeyListener oKeyAdapterBtnConsultar = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				setCursor(SpiritCursor.hourglassCursor);
				mostrarDetallesSaldoInicialFinal();
				setCursor(SpiritCursor.normalCursor);				
			}
		}
	};
 
	
	MouseListener oMouseAdapterTblDetalle = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblDetalle = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		try {
			JTable tabla = (JTable) evt.getSource();
			int fila = tabla.getSelectedRow();
			if(fila!=-1){
				fila = tabla.convertRowIndexToModel(fila);
				asientoDetalleIf = (AsientoDetalleIf) asientoDetallesColeccion.get(fila);
				AsientoIf asientoIf = SessionServiceLocator.getAsientoSessionService().getAsiento(asientoDetalleIf.getAsientoId());
				CentroGastoIf centroGastoIf = null;
				String centroGasto = "";
				EmpleadoIf empleadoIf = null;
				String empleado = "";
				DepartamentoIf departamentoIf = null;
				String departamento = "";
				LineaIf lineaIf = null;
				String linea = "";
				ClienteIf clienteIf = null;
				String cliente = "";
				String referencia = "";
				String valorDebe = "";
				String valorHaber = "";
				
				if (asientoDetalleIf.getCentrogastoId() != null) {
					centroGastoIf = SessionServiceLocator.getCentroGastoSessionService().getCentroGasto(asientoDetalleIf.getCentrogastoId());
					centroGasto = centroGastoIf.getNombre();
				}
				
				if (asientoDetalleIf.getEmpleadoId() != null) {
					empleadoIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(asientoDetalleIf.getEmpleadoId());
					empleado = empleadoIf.getNombres() + " " + empleadoIf.getApellidos();
				}
				
				if (asientoDetalleIf.getDepartamentoId() != null) {
					departamentoIf = SessionServiceLocator.getDepartamentoSessionService().getDepartamento(asientoDetalleIf.getDepartamentoId());
					departamento = departamentoIf.getNombre();
				}
				
				if (asientoDetalleIf.getLineaId() != null) {
					lineaIf = SessionServiceLocator.getLineaSessionService().getLinea(asientoDetalleIf.getLineaId());
					linea = lineaIf.getNombre();
				}
				
				if (asientoDetalleIf.getClienteId() != null) {
					clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(asientoDetalleIf.getClienteId());
					cliente = clienteIf.getNombreLegal();
				}
				
				if (asientoDetalleIf.getReferencia() != null)
					referencia = asientoDetalleIf.getReferencia();
				
				if (asientoDetalleIf.getDebe() != null)
					valorDebe = formatoDecimal.format(asientoDetalleIf.getDebe());
				
				if (asientoDetalleIf.getHaber() != null)
					valorHaber = formatoDecimal.format(asientoDetalleIf.getHaber());
				
				ppInfoAsientoDetalle = new PopupInfoAsientoDetalle(asientoIf, referencia, asientoDetalleIf.getGlosa(), centroGasto, empleado, departamento, linea, cliente, valorDebe, valorHaber); 
				ppInfoAsientoDetalle.setOwner(getTblDetalle());
				//ppInfoAsientoDetalle.setFocusable(true);
				ppInfoAsientoDetalle.setAttachable(false);
				if(ppInfoAsientoDetalle.isPopupVisible())
					ppInfoAsientoDetalle.hidePopup();
				else
					ppInfoAsientoDetalle.showPopup((Toolkit.getDefaultToolkit().getScreenSize().width - 400) / 2,(Toolkit.getDefaultToolkit().getScreenSize().height - 450) / 2);
				
				getTblDetalle().clearSelection();
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void clean() {
		// Vacio las tablas
		DefaultTableModel model = (DefaultTableModel) this.getTblDetalle().getModel();
		
		for (int i = this.getTblDetalle().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
		
		getTxtSaldoFinal().setText("0.0");
		getTxtSaldoInicial().setText("0.0");
		getTxtTotalDebe().setText("0.0");
		getTxtTotalHaber().setText("0.0");
		
		//getCmbPlanCuenta().setFocusable(true);
		getCmbPlanCuenta().grabFocus();
	}
	
	public void showSaveMode() {
		setSaveMode();
		//Seteo formato de combos de fechas
		getTxtCuentaContable().setEditable(false);
		getBtnBuscarCuenta().setEnabled(false);
		getCmbFechaInicio().setEnabled(false);
		getCmbFechaFin().setEnabled(false);
		getTxtCuentaContable().setText("");
		// Cargo los combobox
		cargarComboPlanCuenta();
		cargarComboPeriodo();
		cleanTable();
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
		asientoDetallesColeccion.clear();
		DefaultTableModel model = (DefaultTableModel) this.getTblDetalle().getModel();
		for(int i= this.getTblDetalle().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	private void initListeners() {
		getCmbPlanCuenta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getModel().getSelectedItem();
				if (planCuenta != null){
					getBtnBuscarCuenta().setEnabled(true);
					getTxtCuentaContable().setText("");
					clean();
				}
			}
		});
		
		getBtnBuscarCuenta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				//cuentaFinderCriteria = new CuentaFinderCriteria(new CuentaModel(true),
				cuentaCriteria = new CuentaCriteria("Buscar Cuenta", "S", planCuenta.getId(), 0L, "");
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), cuentaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					cuentaContable = (CuentaIf) popupFinder.getElementoSeleccionado();
					getTxtCuentaContable().setText(cuentaContable.getCodigo() + " - " + cuentaContable.getNombre());
					clean();
				}
			}
		});
		
		getCmbPeriodo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				periodo = (PeriodoIf) getCmbPeriodo().getModel().getSelectedItem();
				if ( periodo != null ) {
					clean();
					// Seteo la fecha de inicio segun el la fecha de inicio del periodo contable
					getCmbFechaInicio().setEnabled(true);
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(periodo.getFechaini());
					fechaInicioCalculo = periodo.getFechaini();
					fechaInicioTemp = periodo.getFechaini();
					// Seteo la fecha de fin segun el la fecha de fin del periodo contable
					getCmbFechaFin().setEnabled(true);
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(periodo.getFechafin());
					fechaFinCalculo = periodo.getFechafin();
					fechaFinTemp = periodo.getFechafin();
					getCmbFechaInicio().setCalendar(calendarInicio);
					getCmbFechaFin().setCalendar(calendarFin);
				}
			}
		});
		
		// Veo si la fecha esta dentro del periodo contable
		getCmbFechaInicio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				fechaInicioCalculo = (Date) ((DateComboBox) evento.getSource()).getDate();
				clean();
				if (fechaInicioCalculo.before(periodo.getFechaini()) || fechaInicioCalculo.after(periodo.getFechafin())) {
					SpiritAlert.createAlert("Por favor seleccione una fecha dentro del periodo contable!", SpiritAlert.INFORMATION);
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(fechaInicioTemp);
					fechaInicioCalculo = fechaInicioTemp;
					getCmbFechaInicio().setCalendar(calendarInicio);
				} else if (fechaInicioCalculo.after(fechaFinCalculo)) {
					SpiritAlert.createAlert("La fecha de inicio no puede ser despues de la fecha de fin!", SpiritAlert.INFORMATION);
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(fechaInicioTemp);
					fechaInicioCalculo = fechaInicioTemp;
					getCmbFechaInicio().setCalendar(calendarInicio);
				} else if (!fechaInicioCalculo.equals(fechaInicioTemp) || !fechaFinCalculo.equals(fechaFinTemp)) {
					fechaInicioTemp = fechaInicioCalculo;
				}
			}
		});
		
		// Veo si la fecha esta dentro del periodo contable
		getCmbFechaFin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				fechaFinCalculo = (Date) ((DateComboBox) evento.getSource()).getDate();
				clean();
				if (fechaFinCalculo.after(periodo.getFechafin()) || fechaFinCalculo.before(periodo.getFechaini())) {
					SpiritAlert.createAlert("Por favor seleccione una fecha dentro del periodo contable!", SpiritAlert.INFORMATION);
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(fechaFinTemp);
					fechaFinCalculo = fechaFinTemp;
					getCmbFechaFin().setCalendar(calendarFin);
				} else if (fechaFinCalculo.before(fechaInicioCalculo)) {
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
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(SpiritCursor.hourglassCursor);
				mostrarDetallesSaldoInicialFinal();
				setCursor(SpiritCursor.normalCursor);
			}
		});
	}
	
 
	
	public boolean validateFields() {
		if (this.getTxtCuentaContable().getText().equals("")) {
			SpiritAlert.createAlert("Debe seleccionar una cuenta contable", SpiritAlert.INFORMATION);
			this.getBtnBuscarCuenta().grabFocus();
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
		if (getMode() == SpiritMode.UPDATE_MODE) {
			try {
				DefaultTableModel tblModelReporte = (DefaultTableModel) getTblDetalle().getModel();
				
				if (tblModelReporte.getRowCount() > 0) {
					String si = "Si"; 
					String no = "No"; 
					Object[] options ={si,no}; 
					int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir el Mayor General?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if(opcion == JOptionPane.YES_OPTION) {
						String fileName = "jaspers/contabilidad/RPMayorGeneral.jasper";
						HashMap parametrosMap = new HashMap();
						//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_MAYORGENERAL).iterator().next();
						MenuIf menu = null;
						Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_MAYORGENERAL).iterator();
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
						parametrosMap.put("usuario", Parametros.getUsuario());
						parametrosMap.put("emitido", fechaEmision);
						parametrosMap.put("fechaIniPer", periodo.getFechaini().toString());
						parametrosMap.put("fechaFinPer", periodo.getFechafin().toString());
						parametrosMap.put("codPlanCuenta", planCuenta.getCodigo());
						parametrosMap.put("fechaIni",  Utilitarios.getStringDateFromDate(fechaInicioCalculo));
						parametrosMap.put("fechaFin", Utilitarios.getStringDateFromDate(fechaFinCalculo));
						parametrosMap.put("cuenta", cuentaContable.getCodigo() + " " + cuentaContable.getNombre());
						parametrosMap.put("saldoInicial", formatoDecimal.format(saldoInicial));
						parametrosMap.put("saldoFinal", formatoDecimal.format(saldoFinal));
						ReportModelImpl.processReport(fileName, parametrosMap, tblModelReporte, true);
					}
				} else {
					SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
				e.printStackTrace();
			}
			catch (ParseException e) {
				SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	public void refresh() {
		cargarComboPlanCuenta();
		cargarComboPeriodo();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	private void mostrarDetallesSaldoInicialFinal() {
		if (validateFields()) {
			try {
				saldoFinal = 0D;
				Long idCuentaContable = cuentaContable.getId();
				Long idPeriodo = periodo.getId();
				Long idPlanCuenta = planCuenta.getId();
				Double totalDebe = 0D;
				Double totalHaber = 0D;
				cleanTable();
				ArrayList asientoDetallesList = new ArrayList();
				java.util.Date fechaAnterior = Utilitarios.getDateBeforeFromDate(fechaInicioCalculo);
				java.sql.Date fechaAnteriorMovimiento = new java.sql.Date(fechaAnterior.getYear(), fechaAnterior.getMonth(), fechaAnterior.getDate());
				PeriodoIf periodoAnterior = obtenerPeriodoAnterior(fechaAnteriorMovimiento);
				SaldoCuentaIf saldoCuentaIf = (periodoAnterior!=null)?obtenerSaldosCuentasPeriodoDetalleAnteriorByCuentaIdList(idCuentaContable, periodoAnterior, fechaAnteriorMovimiento):null;
				saldoInicial = 0D;
				if (saldoCuentaIf != null)
					saldoInicial = saldoCuentaIf.getValor().doubleValue();
				
				Map parameterMap = new HashMap();
				parameterMap.put("periodoId", idPeriodo);
				parameterMap.put("plancuentaId", idPlanCuenta);
				parameterMap.put("status", "A");
				
				java.sql.Date fechaAnteriorInicioMovimiento = new java.sql.Date(fechaAnteriorMovimiento.getYear(), fechaAnteriorMovimiento.getMonth(), 1);
				java.sql.Date fechaAnteriorFinMovimiento = new java.sql.Date(fechaAnteriorMovimiento.getYear(), fechaAnteriorMovimiento.getMonth(), fechaAnteriorMovimiento.getDate());
				asientoDetallesList = (ArrayList) SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByEmpresaByCuentaIdByPeriodoIdByPlanCuentaIdAndByFechaInicioAndFechaFin(Parametros.getIdEmpresa(), idCuentaContable, periodoAnterior.getId(), idPlanCuenta, fechaAnteriorInicioMovimiento, fechaAnteriorFinMovimiento, true);
				Iterator it = asientoDetallesList.iterator();
				TipoCuentaIf tipoCuentaIf = SessionServiceLocator.getTipoCuentaSessionService().getTipoCuenta(cuentaContable.getTipocuentaId());
				while (it.hasNext()) {
					Object[] data = (Object[]) it.next();
					AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) data[0];
					String tipoCuentaSegunAsiento = determinarTipoCuentaSegunAsiento(asientoDetalle);
					saldoInicial = calcularSaldoCuenta(asientoDetalle, tipoCuentaIf, tipoCuentaSegunAsiento, saldoInicial);
				}
				
				getTxtSaldoInicial().setText(formatoDecimal.format(saldoInicial));
				java.sql.Date fechaInicioMovimiento = new java.sql.Date(fechaInicioCalculo.getYear(), fechaInicioCalculo.getMonth(), fechaInicioCalculo.getDate());
				java.sql.Date fechaFinMovimiento = new java.sql.Date(fechaFinCalculo.getYear(), fechaFinCalculo.getMonth(), fechaFinCalculo.getDate());
				asientoDetallesList = (ArrayList) SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByEmpresaByCuentaIdByPeriodoIdByPlanCuentaIdAndByFechaInicioAndFechaFin(Parametros.getIdEmpresa(), idCuentaContable, idPeriodo, idPlanCuenta, fechaInicioMovimiento, fechaFinMovimiento, true);
				it = asientoDetallesList.iterator();
				
				if (it.hasNext()) {
					while (it.hasNext()) {
						Object[] data = (Object[]) it.next();
						AsientoIf asientoIf = (AsientoIf) data[1];
						AsientoDetalleIf asientoDetalleIf = (AsientoDetalleIf) data[0];
						// Obtengo el modelo de la tabla donde van a ir insertados los asientos detalles
						modelAsientoDetalle = (DefaultTableModel) getTblDetalle().getModel();
						Vector<String> filaAsientoDetalle = new Vector<String>();
						//DateFormat dateFormatter;
						//dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
						//String fecha = dateFormatter.format(asientoIf.getFecha().getTime());
						String fecha = Utilitarios.getFechaCortaUppercase(asientoIf.getFecha());
						// Agregar a la coleccion de TipoOrdenColeccion para grabar al final toda la coleccion
						asientoDetallesColeccion.add(asientoDetalleIf);
						// Agrego los valores leidos de la base del asiento detalle en la fila.
						filaAsientoDetalle.add(fecha);
						if (asientoDetalleIf != null) {
							if (asientoDetalleIf.getReferencia() != null)
								filaAsientoDetalle.add(asientoDetalleIf.getReferencia().toString());
							else
								filaAsientoDetalle.add("");
							if (asientoDetalleIf.getGlosa() != null)
								filaAsientoDetalle.add(asientoDetalleIf.getGlosa().toString());
							else
								filaAsientoDetalle.add("");
							if (asientoDetalleIf.getDebe() != null) {
								filaAsientoDetalle.add(formatoDecimal.format(asientoDetalleIf.getDebe()));
								// Sumo el valor del debe al total acumulado del debe de todos los movimientos transcurridos
								totalDebe = totalDebe + asientoDetalleIf.getDebe().doubleValue();
							}
							if (asientoDetalleIf.getHaber() != null) {
								filaAsientoDetalle.add(formatoDecimal.format(asientoDetalleIf.getHaber()));
								// Sumo el valor del haber al total acumulado del haber
								// de todos los movimientos transcurridos
								totalHaber = totalHaber + asientoDetalleIf.getHaber().doubleValue();
							}
							// Agrego la fila con datos a la tabla.
							modelAsientoDetalle.addRow(filaAsientoDetalle);
						}
					}
				} else {
					SpiritAlert.createAlert("No se encontraron movimientos contables para la cuenta y rango de fechas seleccionadas!", SpiritAlert.INFORMATION);
				}
				// Muestro el total del debe y del haber en el formulario
				getTxtTotalDebe().setText(formatoDecimal.format(totalDebe));
				getTxtTotalHaber().setText(formatoDecimal.format(totalHaber));
				// Muestro el saldo final en el formulario
				Double saldoFinal = saldoInicial + totalDebe - totalHaber;
				getTxtSaldoFinal().setText(formatoDecimal.format(saldoFinal));
				this.saldoFinal = saldoFinal;
				setUpdateMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error en la consulta !!", SpiritAlert.ERROR);
			} catch(Exception e){
				e.printStackTrace();
				SpiritAlert.createAlert("Error general en la consulta !!", SpiritAlert.ERROR);
			}
		}
	}
	
	public static PeriodoIf obtenerPeriodoAnterior(java.sql.Date fecha) {
		int añoActual = fecha.getYear() + 1900;
		int mesActual = fecha.getMonth() + 1; 
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
	
	public static SaldoCuentaIf obtenerSaldosCuentasPeriodoDetalleAnteriorByCuentaIdList(Long idCuenta, PeriodoIf periodoAnterior, java.sql.Date fechaFinMovimiento) {
		SaldoCuentaIf saldoCuenta = null;
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
			parameterMap.put("cuentaId", idCuenta);
			Iterator saldoCuentaIterator = SessionServiceLocator.getSaldoCuentaSessionService().findSaldoCuentaByQuery(parameterMap).iterator();
			if (saldoCuentaIterator.hasNext())
				saldoCuenta = (SaldoCuentaIf) saldoCuentaIterator.next();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		return saldoCuenta; 
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
	
	private String determinarTipoCuentaSegunAsiento(AsientoDetalleIf asientoDetalle) {
		BigDecimal zero = new BigDecimal(0.00);
		if (asientoDetalle.getDebe().compareTo(zero) == 1) {
			return "D";
		} else
			return "H";
	}
}
