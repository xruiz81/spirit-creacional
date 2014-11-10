package com.spirit.contabilidad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.SaldoCuentaIf;
import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.contabilidad.entity.TipoResultadoIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.panel.JPEstadoResultado;
import com.spirit.contabilidad.util.MyRowTreeTableEstadoResultados;
import com.spirit.contabilidad.util.MyTreeTableModelEstadoResultados;
import com.spirit.contabilidad.util.MyTreeTableModelEstadoResultadosAcumulado;
import com.spirit.contabilidad.util.MyTreeTableModelEstadoResultadosMensual;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.sri.entity.ImpuestoRentaIf;
import com.spirit.util.DeepCopy;
import com.spirit.util.Utilitarios;

public class EstadoResultadosModel extends JPEstadoResultado {
	private static final long serialVersionUID = -4493555932231658898L;
	private static PeriodoIf periodo;
	private static Date fechaInicioCalculo;
	private static Date fechaInicioTemp;
	private static Date fechaFinCalculo;
	private static Date fechaFinTemp;
	private Long idPlanCuenta;
	private Long idPeriodo;
	private PlanCuentaIf planCuenta;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private MyTreeTableModelEstadoResultados myTreeTableModel;
	Vector<CuentaIf> cuentasEstadoResultadosTreeTable = new Vector<CuentaIf>();
	private final static String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private final static String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private Map cuentasMap = new HashMap();
	private Map tiposCuentaMap = new HashMap();
	private Map tiposResultadoMap = new HashMap();
	private Map saldosTiposResultadoMap = new HashMap();
	private double PORCENTAJE_PARTICIPACION_TRABAJADORES = 0D;
	private double PORCENTAJE_IMPUESTO_RENTA = 0D;
	
	public EstadoResultadosModel() {
		initKeyListeners();
		initListeners();
		cargarCombos();
		this.showSaveMode();
		new HotKeyComponent(this);
	}

	private void loadParameters() {
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			parameterMap.put("codigo", "PPUT");
			Iterator it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
			if (it.hasNext())
				PORCENTAJE_PARTICIPACION_TRABAJADORES = Double.parseDouble(((ParametroEmpresaIf) it.next()).getValor());
			Timestamp fechaInicio = Utilitarios.resetTimestampStartDate(Utilitarios.fromUtilDateToTimestamp(fechaInicioCalculo));
			Timestamp fechaFin = Utilitarios.resetTimestampEndDate(Utilitarios.fromUtilDateToTimestamp(fechaFinCalculo));
			it = SessionServiceLocator.getImpuestoRentaSessionService().findImpuestoRentaByFechaInicioAndFechaFin(Utilitarios.fromTimestampToSqlDate(fechaInicio), Utilitarios.fromTimestampToSqlDate(fechaFin)).iterator();
			if (it.hasNext())
				PORCENTAJE_IMPUESTO_RENTA = ((ImpuestoRentaIf) it.next()).getPorcentaje().doubleValue();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	public void initKeyListeners(){
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);
		
		getBtnConsultar().addKeyListener(oKeyAdapterBtnConsultar);
		
		getCbAcumulado().addKeyListener(oKeyAdapterCbAcumulado);
		
		getCbAcumulado().setNextFocusableComponent(getBtnConsultar());
	}
	
	
	KeyListener oKeyAdapterBtnConsultar = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				setCursor(SpiritCursor.hourglassCursor);
				mostrarDetallesSaldoInicialFinal();
				setCursor(SpiritCursor.normalCursor);
			}
		}
	};
	
	KeyListener oKeyAdapterCbAcumulado = new KeyAdapter() {
		public void keyPressed(KeyEvent evt) {
			if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				getCbAcumulado().setSelected(true);
			}
		}
	};
	
	public void clean() {
		
	}
	
	public void showSaveMode() {
		setSaveMode();		
		getTxtSaldoFinal().setEditable(false);
		getTxtSaldoFinal().setText("");
		cleanTable();
		cleanTreeTable();
		
		getCmbPlanCuenta().grabFocus();
	}
	
	private void cargarCombos() {
		cargarComboPlanCuenta();
		cargarComboPeriodo();
		cargarComboNivelesVisibles();
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
	
	private void cargarComboNivelesVisibles() {
		getCmbNivelesVisibles().removeAllItems();
		
		if (planCuenta != null) {
			int nivelMaximo = SessionServiceLocator.getCuentaSessionService().getNivelMaximoByPlanCuentaId(planCuenta.getId());
			
			for (int i=nivelMaximo; i>0; i--)
				getCmbNivelesVisibles().addItem(String.valueOf(i));
		}
	}
	
	private void setModelTblEstadoResultados(boolean acumulado) {
		if (acumulado) {
			getTreeTblEstadoResultados().setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Cuenta", "Saldo", "Margen", "Porcentaje"}) {
				boolean[] columnEditable = new boolean[] {false, false, false, false};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			
			getTblEstadoResultados().setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Cuenta", "Saldo", "Margen", "Porcentaje"}) {
				boolean[] columnEditable = new boolean[] {false, false, false, false};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			
			getTreeTblEstadoResultados().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			getTblEstadoResultados().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		} else {
			getTreeTblEstadoResultados().setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Cuenta", "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC", "TOTAL"}) {
				boolean[] columnEditable = new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			
			getTblEstadoResultados().setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Cuenta", "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC", "TOTAL"}) {
				boolean[] columnEditable = new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});

			getTreeTblEstadoResultados().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			getTblEstadoResultados().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
	}
	
	private void setAnchoColumnas(boolean acumulado) {
		if (acumulado) {
			TableColumn anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(0);
			anchoColumna.setMinWidth(400);
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(1);
			anchoColumna.setPreferredWidth(100);
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(2);
			anchoColumna.setPreferredWidth(100);
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(3);
			anchoColumna.setPreferredWidth(100);
		} else {
			double column1 = 0D;
			double column2 = 0D;
			double column3 = 0D;
			double column4 = 0D;
			double column5 = 0D;
			double column6 = 0D;
			double column7 = 0D;
			double column8 = 0D;
			double column9 = 0D;
			double column10 = 0D;
			double column11 = 0D;
			double column12 = 0D;
			
			for (int row=0; row<getTreeTblEstadoResultados().getRowCount(); row++) {
				column1 += (getTreeTblEstadoResultados().getValueAt(row, 1) != null && !Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 1).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","").equals(""))?Double.parseDouble(Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 1).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","")):0D;
				column2 += (getTreeTblEstadoResultados().getValueAt(row, 2) != null && !Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 2).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","").equals(""))?Double.parseDouble(Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 2).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","")):0D;
				column3 += (getTreeTblEstadoResultados().getValueAt(row, 3) != null && !Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 3).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","").equals(""))?Double.parseDouble(Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 3).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","")):0D;
				column4 += (getTreeTblEstadoResultados().getValueAt(row, 4) != null && !Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 4).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","").equals(""))?Double.parseDouble(Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 4).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","")):0D;
				column5 += (getTreeTblEstadoResultados().getValueAt(row, 5) != null && !Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 5).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","").equals(""))?Double.parseDouble(Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 5).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","")):0D;
				column6 += (getTreeTblEstadoResultados().getValueAt(row, 6) != null && !Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 6).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","").equals(""))?Double.parseDouble(Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 6).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","")):0D;
				column7 += (getTreeTblEstadoResultados().getValueAt(row, 7) != null && !Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 7).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","").equals(""))?Double.parseDouble(Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 7).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","")):0D;
				column8 += (getTreeTblEstadoResultados().getValueAt(row, 8) != null && !Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 8).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","").equals(""))?Double.parseDouble(Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 8).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","")):0D;
				column9 += (getTreeTblEstadoResultados().getValueAt(row, 9) != null && !Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 9).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","").equals(""))?Double.parseDouble(Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 9).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","")):0D;
				column10 += (getTreeTblEstadoResultados().getValueAt(row, 10) != null && !Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 10).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","").equals(""))?Double.parseDouble(Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 10).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","")):0D;
				column11 += (getTreeTblEstadoResultados().getValueAt(row, 11) != null && !Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 11).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","").equals(""))?Double.parseDouble(Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 11).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","")):0D;
				column12 += (getTreeTblEstadoResultados().getValueAt(row, 12) != null && !Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 12).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","").equals(""))?Double.parseDouble(Utilitarios.removeDecimalFormat(getTreeTblEstadoResultados().getValueAt(row, 12).toString()).replaceAll("<html><b>","").replaceAll("</b></html>","")):0D;
			}
			
			TableColumn anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(0);
			anchoColumna.setMinWidth(400);
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(1);
			int tableWidth = 400;
			
			if (column1 != 0D) {
				anchoColumna.setMinWidth(100);
				tableWidth += 100;
			} else {
				anchoColumna.setMinWidth(0);
				anchoColumna.setMaxWidth(0);
				anchoColumna.setPreferredWidth(0);
			}
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(2);
			if (column2 != 0D) {
				anchoColumna.setMinWidth(100);
				tableWidth += 100;
			} else {
				anchoColumna.setMinWidth(0);
				anchoColumna.setMaxWidth(0);
				anchoColumna.setPreferredWidth(0);
			}
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(3);
			if (column3 != 0D) {
				anchoColumna.setMinWidth(100);
				tableWidth += 100;
			} else {
				anchoColumna.setMinWidth(0);
				anchoColumna.setMaxWidth(0);
				anchoColumna.setPreferredWidth(0);
			}
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(4);
			if (column4 != 0D) {
				anchoColumna.setMinWidth(100);
				tableWidth += 100;
			} else {
				anchoColumna.setMinWidth(0);
				anchoColumna.setMaxWidth(0);
				anchoColumna.setPreferredWidth(0);
			}
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(5);
			if (column5 != 0D) {
				anchoColumna.setMinWidth(100);
				tableWidth += 100;
			} else {
				anchoColumna.setMinWidth(0);
				anchoColumna.setMaxWidth(0);
				anchoColumna.setPreferredWidth(0);
			}
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(6);
			if (column6 != 0D) {
				anchoColumna.setMinWidth(100);
				tableWidth += 100;
			} else {
				anchoColumna.setMinWidth(0);
				anchoColumna.setMaxWidth(0);
				anchoColumna.setPreferredWidth(0);
			}
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(7);
			if (column7 != 0D) {
				anchoColumna.setMinWidth(100);
				tableWidth += 100;
			} else {
				anchoColumna.setMinWidth(0);
				anchoColumna.setMaxWidth(0);
				anchoColumna.setPreferredWidth(0);
			}
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(8);
			if (column8 != 0D) {
				anchoColumna.setMinWidth(100);
				tableWidth += 100;
			} else {
				anchoColumna.setMinWidth(0);
				anchoColumna.setMaxWidth(0);
				anchoColumna.setPreferredWidth(0);
			}
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(9);
			if (column9 != 0D) {
				anchoColumna.setMinWidth(100);
				tableWidth += 100;
			} else {
				anchoColumna.setMinWidth(0);
				anchoColumna.setMaxWidth(0);
				anchoColumna.setPreferredWidth(0);
			}
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(10);
			if (column10 != 0D) {
				anchoColumna.setMinWidth(100);
				tableWidth += 100;
			} else {
				anchoColumna.setMinWidth(0);
				anchoColumna.setMaxWidth(0);
				anchoColumna.setPreferredWidth(0);
			}
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(11);
			if (column11 != 0D) {
				anchoColumna.setMinWidth(100);
				tableWidth += 100;
			} else {
				anchoColumna.setMinWidth(0);
				anchoColumna.setMaxWidth(0);
				anchoColumna.setPreferredWidth(0);
			}
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(12);
			if (column12 != 0D) {
				anchoColumna.setMinWidth(100);
				tableWidth += 100;
			} else {
				anchoColumna.setMinWidth(0);
				anchoColumna.setMaxWidth(0);
				anchoColumna.setPreferredWidth(0);
			}
			
			anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(13);
			anchoColumna.setMinWidth(100);
			tableWidth += 100;
			
			if (tableWidth > getTreeTblEstadoResultados().getWidth()) {
				getTreeTblEstadoResultados().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				getTblEstadoResultados().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			} else {
				getTreeTblEstadoResultados().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				getTblEstadoResultados().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			}
		}
	}
	
	//Cargo los listeners de las fechas
	private void initListeners(){
		getCmbPlanCuenta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getModel().getSelectedItem();
			}
		});				
		
		getCmbEjercicioContable().addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent evento) {
				periodo = (PeriodoIf) getCmbEjercicioContable().getModel().getSelectedItem();
				if(periodo != null){
					//clean();
					//Seteo la fecha de inicio segun el la fecha de inicio del periodo contable
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(periodo.getFechaini());
					fechaInicioCalculo = periodo.getFechaini();
					fechaInicioTemp = periodo.getFechaini();
					
					//Seteo la fecha de fin segun el la fecha de fin del periodo contable
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(periodo.getFechafin());
					fechaFinCalculo = periodo.getFechafin();
					fechaFinTemp = periodo.getFechafin();
					
					getCmbFechaInicio().setCalendar(calendarInicio);
					getCmbFechaFin().setCalendar(calendarFin);
				}else{
					SpiritAlert.createAlert("Debe al menos existir un Periodo", SpiritAlert.WARNING);
				}
			}
		});
		
		//Veo si la fecha Inicio esta dentro del periodo contable
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
				if (fechaFinCalculo.after(periodo.getFechafin()) || fechaFinCalculo.before(periodo.getFechaini())) {
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
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(SpiritCursor.hourglassCursor);
				mostrarDetallesSaldoInicialFinal();
				setCursor(SpiritCursor.normalCursor);
			}
		});
		
		getCbAcumulado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!getCbAcumulado().isSelected())
					getTxtSaldoFinal().setText("");
				cleanTable();
				cleanTreeTable();
			}
		});
	}
	
	Comparator<CuentaIf> ordenadorCuentasPorCodigo = new Comparator<CuentaIf>(){
		public int compare(CuentaIf o1, CuentaIf o2) {
			return o1.getCodigo().compareTo(o2.getCodigo());
		}		
	};
	
	private void mostrarDetallesSaldoInicialFinal() {
		if (planCuenta != null){
			loadParameters();
			idPlanCuenta = planCuenta.getId();
			if (periodo != null){
				idPeriodo = periodo.getId();
				LinkedHashMap cuentasEstadoResultadosMap = new LinkedHashMap();
				cuentasEstadoResultadosTreeTable.clear();
				if (validateFields()) {
					if (getCbAcumulado().isSelected()) {
						getTreeTblEstadoResultados().setModel(new DefaultTableModel());
						setModelTblEstadoResultados(true);
					} else {
						getTreeTblEstadoResultados().setModel(new DefaultTableModel());
						setModelTblEstadoResultados(false);
					}
					
					try {
						boolean periodoDetalleIgual = false;
						int ultimoDiaMesFechaInicialMovimiento = 0;
						int primerDiaMesFechaFinalMovimiento = 0;
						double totalFinal = 0D;
						double totalIngresos = 0D;
						Map utilidadMap = new HashMap();
						Map utilidadMesAcumuladoTipoResultadoMap = new HashMap();
						Double porcentaje = 0D;
						int nivelesVisibles = Integer.parseInt(getCmbNivelesVisibles().getSelectedItem().toString());
						idPlanCuenta = planCuenta.getId();
						idPeriodo = periodo.getId();
						Long idEmpresa = Parametros.getIdEmpresa();
						java.sql.Date fechaInicioMovimiento = new java.sql.Date(fechaInicioCalculo.getYear(),fechaInicioCalculo.getMonth(),fechaInicioCalculo.getDate());
						java.sql.Date fechaFinMovimiento = new java.sql.Date(fechaFinCalculo.getYear(),fechaFinCalculo.getMonth(),fechaFinCalculo.getDate());
						Collection cuentasColeccion = SessionServiceLocator.getCuentaSessionService().findCuentaByPlancuentaId(planCuenta.getId());
						Collections.sort((ArrayList)cuentasColeccion,ordenadorCuentasPorCodigo);
						cuentasMap = mapearPlanCuentas(cuentasColeccion);
						Collection tiposCuentaColeccion = SessionServiceLocator.getTipoCuentaSessionService().getTipoCuentaList();
						tiposCuentaMap = mapearTiposCuenta(tiposCuentaColeccion);
						Collection tiposResultadoColeccion = SessionServiceLocator.getTipoResultadoSessionService().getTipoResultadoList();
						tiposResultadoMap = mapearTiposResultado(tiposResultadoColeccion);
						saldosTiposResultadoMap = mapearSaldosTiposResultado(tiposResultadoMap);
						/*Map queryMap = new HashMap();
						queryMap.put("asientoCierre", SpiritConstants.getOptionYes().substring(0,1));
						queryMap.put("periodoId", idPeriodo);
						queryMap.put("empresaId", Parametros.getIdEmpresa());
						queryMap.put("fecha", fechaFinMovimiento);  
						queryMap.put("plancuentaId", idPlanCuenta);
						queryMap.put("status", ESTADO_ACTIVO);
						Iterator<AsientoDetalleIf> asientoDetalleCierreIterator = SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleFromAsientoCierreByQuery(queryMap).iterator();
						if (asientoDetalleCierreIterator.hasNext()) {
							AsientoDetalleIf asientoDetalleCierre = asientoDetalleCierreIterator.next();
							double saldoCuentaCierre = 0D;
							if (getCbAcumulado().isSelected()) {
								CuentaIf cuentaCierreEstadoResultados = (CuentaIf) cuentasMap.get(asientoDetalleCierre.getCuentaId());
								TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(cuentaCierreEstadoResultados.getTipocuentaId());
								String tipoCuentaSegunAsiento = determinarTipoCuentaSegunAsiento(asientoDetalleCierre);
								saldoCuentaCierre = calcularSaldoCuenta(asientoDetalleCierre, tipoCuenta, tipoCuentaSegunAsiento, saldoCuentaCierre);
								cuentasEstadoResultadosMap.put(cuentaCierreEstadoResultados.getId(), saldoCuentaCierre);
							} else {
								
							}
						}*/
						Collection cuentasEstadoResultadosColeccion = SessionServiceLocator.getCuentaSessionService().findCuentasForEstadoResultados(planCuenta.getId(), periodo.getId(), fechaInicioMovimiento, fechaFinMovimiento);
						Vector<CuentaIf> cuentasEstadoResultadosVector = generarVectorCuentasEstadoResultados(cuentasEstadoResultadosColeccion);
						ArrayList asientoDetalleColeccion = (ArrayList) SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByEmpresaByPeriodoIdByPlanCuentaIdByStatusAndByFechaInicioAndFechaFin(idEmpresa, idPeriodo, idPlanCuenta, ESTADO_ACTIVO, fechaInicioMovimiento, fechaFinMovimiento, true);
						for (int i=0; i<cuentasEstadoResultadosVector.size(); i++) {
							int yearFechaInicioMovimiento = fechaInicioCalculo.getYear() + 1900;
							int mesFechaInicioMovimiento = fechaInicioCalculo.getMonth() + 1;
							int yearFechaFinMovimiento = fechaFinCalculo.getYear() + 1900;
							int mesFechaFinMovimiento = fechaFinCalculo.getMonth() + 1;
							CuentaIf cuentaEstadoResultadosIf = cuentasEstadoResultadosVector.get(i);
							if (cuentaEstadoResultadosIf.getTiporesultadoId() != null) {
								if (getCbAcumulado().isSelected()) {
									Double saldoCuenta = (cuentasEstadoResultadosMap.get(cuentaEstadoResultadosIf.getId()) != null)?(Double) cuentasEstadoResultadosMap.get(cuentaEstadoResultadosIf.getId()):0D;
									cuentasEstadoResultadosMap.put(cuentaEstadoResultadosIf.getId(), saldoCuenta);
									List<AsientoDetalleIf> asientoDetallesList = getAsientoDetalleByCuentaIdList(asientoDetalleColeccion, cuentaEstadoResultadosIf.getId());
									Iterator asientosDetalleCuentaEstadoResultadosIterator = asientoDetallesList.iterator();
									while (asientosDetalleCuentaEstadoResultadosIterator.hasNext()) {
										AsientoDetalleIf asientoDetalleCuentaEstadoResultadosIf = (AsientoDetalleIf) asientosDetalleCuentaEstadoResultadosIterator.next();
										TipoCuentaIf tipoCuentaIf = (TipoCuentaIf) tiposCuentaMap.get(cuentaEstadoResultadosIf.getTipocuentaId());
										String tipoCuentaSegunAsiento = determinarTipoCuentaSegunAsiento(asientoDetalleCuentaEstadoResultadosIf);
										saldoCuenta = (Double) cuentasEstadoResultadosMap.get(cuentaEstadoResultadosIf.getId());
										saldoCuenta = calcularSaldoCuenta(asientoDetalleCuentaEstadoResultadosIf, tipoCuentaIf, tipoCuentaSegunAsiento, saldoCuenta);
										cuentasEstadoResultadosMap.put(cuentaEstadoResultadosIf.getId(), saldoCuenta);
									}
								} else {
									if (yearFechaInicioMovimiento == yearFechaFinMovimiento && mesFechaInicioMovimiento == mesFechaFinMovimiento) {
										periodoDetalleIgual = true;
									} else {
										ultimoDiaMesFechaInicialMovimiento = Utilitarios.getLastDateFromMonth(fechaInicioCalculo).getDate();
										primerDiaMesFechaFinalMovimiento = Utilitarios.getFirstDateFromMonth(fechaFinCalculo).getDate();
									}
									
									int meses = 1;
									if (!periodoDetalleIgual)
										meses = mesFechaFinMovimiento - mesFechaInicioMovimiento + 1;
									
									Map saldosMesMap = new HashMap();
									for (int mes=1; mes<=meses; mes++) {
										int mesReal = 0;
										Double saldoCuenta = 0D;
										cuentasEstadoResultadosMap.put(cuentaEstadoResultadosIf.getId(), saldosMesMap);
										ArrayList detalles = new ArrayList();
										if (mes == 1 && periodoDetalleIgual) {
											detalles = getAsientoDetalleByCuentaIdByFechaInicioAndFechaFinList(asientoDetalleColeccion, cuentaEstadoResultadosIf, fechaInicioMovimiento, fechaFinMovimiento);
											mesReal = fechaInicioMovimiento.getMonth() + 1;
										} else if (mes == 1 && !periodoDetalleIgual) {
											fechaInicioMovimiento = new java.sql.Date(fechaInicioCalculo.getYear(),fechaInicioCalculo.getMonth(),fechaInicioCalculo.getDate());
											fechaFinMovimiento = new java.sql.Date(fechaInicioCalculo.getYear(),fechaInicioCalculo.getMonth(),ultimoDiaMesFechaInicialMovimiento);
											detalles = getAsientoDetalleByCuentaIdByFechaInicioAndFechaFinList(asientoDetalleColeccion, cuentaEstadoResultadosIf, fechaInicioMovimiento, fechaFinMovimiento);
											mesReal = fechaFinMovimiento.getMonth() + 1;
										} else if (mes == meses && !periodoDetalleIgual) {
											fechaInicioMovimiento = new java.sql.Date(fechaFinCalculo.getYear(),fechaFinCalculo.getMonth(),primerDiaMesFechaFinalMovimiento);
											fechaFinMovimiento = new java.sql.Date(fechaFinCalculo.getYear(), fechaFinCalculo.getMonth(),fechaFinCalculo.getDate());
											detalles = getAsientoDetalleByCuentaIdByFechaInicioAndFechaFinList(asientoDetalleColeccion, cuentaEstadoResultadosIf, fechaInicioMovimiento, fechaFinMovimiento);
											mesReal = fechaInicioMovimiento.getMonth() + 1;
										} else if (!periodoDetalleIgual) {
											fechaInicioMovimiento = new java.sql.Date(fechaInicioCalculo.getYear(), fechaInicioCalculo.getMonth() + mes - 1, 1);
											fechaFinMovimiento = new java.sql.Date(fechaInicioCalculo.getYear(), fechaInicioCalculo.getMonth() + mes - 1, Utilitarios.getLastDateFromMonth(fechaInicioMovimiento).getDate());
											detalles = getAsientoDetalleByCuentaIdByFechaInicioAndFechaFinList(asientoDetalleColeccion, cuentaEstadoResultadosIf, fechaInicioMovimiento, fechaFinMovimiento);
											mesReal = fechaInicioCalculo.getMonth() + mes;
										}
										
										List<AsientoDetalleIf> asientoDetallesList = getAsientoDetalleByCuentaIdList(detalles, cuentaEstadoResultadosIf.getId());
										Iterator asientosDetalleCuentaEstadoResultadosIterator = asientoDetallesList.iterator();
										while (asientosDetalleCuentaEstadoResultadosIterator.hasNext()) {
											AsientoDetalleIf asientoDetalleCuentaEstadoResultadosIf = (AsientoDetalleIf) asientosDetalleCuentaEstadoResultadosIterator.next();
											TipoCuentaIf tipoCuentaIf = (TipoCuentaIf) tiposCuentaMap.get(cuentaEstadoResultadosIf.getTipocuentaId());
											String tipoCuentaSegunAsiento = determinarTipoCuentaSegunAsiento(asientoDetalleCuentaEstadoResultadosIf);
											if (((Map) cuentasEstadoResultadosMap.get(cuentaEstadoResultadosIf.getId())).get(mesReal) != null)
												saldoCuenta = (Double) ((Map) cuentasEstadoResultadosMap.get(cuentaEstadoResultadosIf.getId())).get(mesReal);
											else
												saldoCuenta = 0D;
											saldoCuenta = calcularSaldoCuenta(asientoDetalleCuentaEstadoResultadosIf, tipoCuentaIf, tipoCuentaSegunAsiento, saldoCuenta);
											saldosMesMap.put(mesReal, saldoCuenta);
										}										
										
										cuentasEstadoResultadosMap.put(cuentaEstadoResultadosIf.getId(), saldosMesMap);
									}
								}
							}
						}
						
						for (int i=0; i<cuentasEstadoResultadosVector.size(); i++) {
							CuentaIf cuentaEstadoResultadosIf = cuentasEstadoResultadosVector.get(i);
							if (cuentaEstadoResultadosIf.getTiporesultadoId() != null) {
								if (getCbAcumulado().isSelected())
									calcularSaldoCuentaPadre(cuentasEstadoResultadosMap, cuentaEstadoResultadosIf, 0D);
								else
									calcularSaldoCuentaPadre(cuentasEstadoResultadosMap, cuentaEstadoResultadosIf, new HashMap());
							}
						}
						
						Iterator cuentasIterator = cuentasColeccion.iterator();
						while (cuentasIterator.hasNext()) {
							CuentaIf cuentaIf = (CuentaIf) cuentasIterator.next();
							if (cuentasEstadoResultadosMap.get(cuentaIf.getId()) != null)
								cuentasEstadoResultadosTreeTable.add(cuentaIf);
						}
						
						Iterator keyIterator = tiposResultadoMap.keySet().iterator(); 
						ArrayList myList = new ArrayList();
						totalFinal = 0D;
						while (keyIterator.hasNext()) {
							utilidadMesAcumuladoTipoResultadoMap = new HashMap();
							Long key = (Long) keyIterator.next();
							TipoResultadoIf tipoResultado = (TipoResultadoIf) tiposResultadoMap.get(key);
							Map parameterMap = new HashMap();
							parameterMap.put("plancuentaId", planCuenta.getId());
							parameterMap.put("nivel", 2);
							parameterMap.put("tiporesultadoId", tipoResultado.getId());
							List<CuentaIf> cuentasList = (ArrayList<CuentaIf>) SessionServiceLocator.getCuentaSessionService().findCuentaByQuery(parameterMap); 
							/*Iterator it = CuentaModel.getCuentaSessionService().findCuentaByCodigo("71010").iterator();
							CuentaIf otrosIngresos = (it.hasNext())?(CuentaIf) it.next():null;
							if (otrosIngresos != null)
								cuentasList.add(otrosIngresos);
							it = CuentaModel.getCuentaSessionService().findCuentaByCodigo("71020").iterator();
							CuentaIf otrosEgresos = (it.hasNext())?(CuentaIf) it.next():null;
							if (otrosEgresos != null)
								cuentasList.add(otrosEgresos);*/
							cuentasIterator = cuentasList.iterator();
							while (cuentasIterator.hasNext()) {
								CuentaIf cuentaIf = (CuentaIf) cuentasIterator.next();
								if (!cuentaIf.getCodigo().equals("1") && !cuentaIf.getCodigo().equals("2") && !cuentaIf.getCodigo().equals("3") && !cuentaIf.getCodigo().equals("7")) {
									double saldo = 0D;
									Map saldosMap = null;
									if (cuentasEstadoResultadosMap.get(cuentaIf.getId()) != null) {
										if (getCbAcumulado().isSelected())
											saldo = (Double) cuentasEstadoResultadosMap.get(cuentaIf.getId());
										else
											saldosMap = (Map) cuentasEstadoResultadosMap.get(cuentaIf.getId());
									}
									TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(cuentaIf.getTipocuentaId());
									
									if ((((Utilitarios.redondeoValor(saldo) > 0D || Utilitarios.redondeoValor(saldo) < 0D) && getCbAcumulado().isSelected()) || (saldosMap != null && saldosMap.size() > 0 && !getCbAcumulado().isSelected())) && (!tipoCuenta.getCodigo().equals("A") && !tipoCuenta.getCodigo().equals("P") && !tipoCuenta.getCodigo().equals("C"))) {
										MyRowTreeTableEstadoResultados.setTiposCuentaMap(tiposCuentaMap);
										MyRowTreeTableEstadoResultados treeTableEstadoResultados = null;
										if (getCbAcumulado().isSelected())
											treeTableEstadoResultados = new MyRowTreeTableEstadoResultados(cuentaIf, "Cuenta", formatoDecimal.format(saldo), nivelesVisibles, false, filtrosBusqueda(), null, null, getCbAcumulado().isSelected());
										else
											treeTableEstadoResultados = new MyRowTreeTableEstadoResultados(cuentaIf, "Cuenta", saldosMap, saldosMap, nivelesVisibles, false, filtrosBusqueda(), null, null, getCbAcumulado().isSelected(), 0L);

										MyRowTreeTableEstadoResultados.setCuentasEstadoResultadosTreeTable(cuentasEstadoResultadosTreeTable);
										MyRowTreeTableEstadoResultados.setCuentasEstadoResultadosMap(cuentasEstadoResultadosMap);
										myList.add(treeTableEstadoResultados);

										if (getCbAcumulado().isSelected()) {
											double totalTipoResultado = (Double) saldosTiposResultadoMap.get(tipoResultado.getId());
											if (tipoResultado.getUtilidad().equals("+")) {
												totalTipoResultado += saldo;
												totalFinal += saldo;
											} else {
												totalTipoResultado -= saldo;
												totalFinal -= saldo;
											}

											saldosTiposResultadoMap.put(tipoResultado.getId(), totalTipoResultado);

											/*porcentaje = (totalFinal / totalIngresos) * 100;
										porcentaje = Math.sqrt(porcentaje*porcentaje);
										if (String.valueOf(porcentaje).equals("NaN"))
											porcentaje = 0.0;

										if (tipoCuenta.getDebehaber().equals("H"))
											saldo = saldo * -1D;*/
										} else {
											Iterator it = saldosMap.keySet().iterator();
											while (it.hasNext()) {
												int mes = ((Integer) it.next());
												double saldoMes = ((Double) saldosMap.get(mes)).doubleValue();
												double utilidadMes = (utilidadMap.get(mes) != null)?((Double) utilidadMap.get(mes)).doubleValue():0D;
												if (tipoResultado.getUtilidad().equals("+"))
													utilidadMes += saldoMes;
												else
													utilidadMes -= saldoMes;

												utilidadMap.put(mes, Double.valueOf(utilidadMes));
												//utilidadMesAcumuladoTipoResultadoMap.put(tipoResultado.getId(), DeepCopy.copy(utilidadMap));
											}
											
											//utilidadMesAcumuladoTipoResultadoMap.put(tipoResultado.getId(), DeepCopy.copy(utilidadMap));
										}

										//Añado una fila en blanco despues de la familia de cada padre, solo por estetica
										myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "", nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected()));
									}
								}
							}
							
							if (getCbAcumulado().isSelected()) {
								//myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "<html><b>" + tipoResultado.getLeyendaResultado() + " : </b></html>", nivelesVisibles, true, null, "<html><b>" + formatoDecimal.format(totalFinal * -1D) + "</b></html>", "<html><b>" + formatoDecimal.format(porcentaje) + "%</b></html>", getCbAcumulado().isSelected()));
								if (tipoResultado.getOrden() == 1)
									totalIngresos = totalFinal;
							
								porcentaje = (totalFinal/totalIngresos) * 100D;
								myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "<html><b>" + tipoResultado.getLeyendaResultado() + " : </b></html>", nivelesVisibles, true, null, "<html><b>" + formatoDecimal.format(totalFinal) + "</b></html>", "<html><b>" + formatoDecimal.format(porcentaje) + "%</b></html>", getCbAcumulado().isSelected()));
							} else {
								utilidadMesAcumuladoTipoResultadoMap.put(tipoResultado.getId(), DeepCopy.copy(utilidadMap));
								myList.add(new MyRowTreeTableEstadoResultados("<html><b>" + tipoResultado.getLeyendaResultado() + ": </b></html>", "Total", (Map) utilidadMesAcumuladoTipoResultadoMap.get(tipoResultado.getId()), (Map) utilidadMesAcumuladoTipoResultadoMap.get(tipoResultado.getId()), nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected(), 0L));
							}
							
							myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "", nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected()));
						}
						
						if (getCbAcumulado().isSelected()) {
							double participacionTrabajadores = (totalFinal > 0)?totalFinal * (PORCENTAJE_PARTICIPACION_TRABAJADORES/100D):0D;
							myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "<html><b>" + String.valueOf(PORCENTAJE_PARTICIPACION_TRABAJADORES) + "% PARTICIPACION TRABAJADORES: </b></html>", nivelesVisibles, true, null, "<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(participacionTrabajadores)) + "</b></html>", "", getCbAcumulado().isSelected()));
							myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "", nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected()));
							totalFinal -= participacionTrabajadores;
							//myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "<html><b>UTILIDAD ANTES DE IMPUESTOS: </b></html>", nivelesVisibles, true, null, "<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(totalFinal * -1D)) + "</b></html>", "<html><b>" + formatoDecimal.format(porcentaje) + "%</b></html>", getCbAcumulado().isSelected()));
							porcentaje = (totalFinal/totalIngresos) * 100D;
							myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "<html><b>UTILIDAD ANTES DE IMPUESTOS: </b></html>", nivelesVisibles, true, null, "<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(totalFinal)) + "</b></html>", "<html><b>" + formatoDecimal.format(porcentaje) + "%</b></html>", getCbAcumulado().isSelected()));
							myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "", nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected()));					
							double impuestos = (totalFinal > 0)?totalFinal * (PORCENTAJE_IMPUESTO_RENTA/100D):0D;
							myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "<html><b>" + String.valueOf(PORCENTAJE_IMPUESTO_RENTA) + "% IMPUESTO A LA RENTA: </b></html>", nivelesVisibles, true, null, "<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(impuestos)) + "</b></html>", "", getCbAcumulado().isSelected()));
							myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "", nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected()));
							totalFinal -= impuestos;
							//double utilidad = totalFinal * -1D;
							porcentaje = (totalFinal/totalIngresos) * 100D;
							double utilidad = totalFinal;
							myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "<html><b>UTILIDAD / PERDIDA NETA DEL EJERCICIO: </b></html>", nivelesVisibles, true, null, "<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(utilidad)) + "</b></html>", "<html><b>" + formatoDecimal.format(porcentaje) + "%</b></html>", getCbAcumulado().isSelected()));
							myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "", nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected()));
							myTreeTableModel = new MyTreeTableModelEstadoResultadosAcumulado(myList);
						} else {
							Iterator keysetIterator = utilidadMap.keySet().iterator();
							Map participacionTrabajadoresMap = new HashMap();
							Map impuestosMap = new HashMap();
							Map utilidadAntesImpuestosMap = new HashMap();
							Map utilidadAntesImpuestosRealMap = new HashMap();
							Map utilidadRealMap = (HashMap) DeepCopy.copy(utilidadMap);
							while (keysetIterator.hasNext()) {
								int mes = ((Integer)keysetIterator.next()).intValue();
								double BAT = ((Double) utilidadRealMap.get(mes)).doubleValue();
								double participacionTrabajadores = BAT * (PORCENTAJE_PARTICIPACION_TRABAJADORES/100D);
								participacionTrabajadoresMap.put(mes, participacionTrabajadores);
								if (participacionTrabajadores > 0D)
									utilidadAntesImpuestosMap.put(mes, BAT-participacionTrabajadores);
								else
									utilidadAntesImpuestosMap.put(mes, BAT);
								BAT -= participacionTrabajadores;
								utilidadAntesImpuestosRealMap.put(mes, BAT);
								double impuestos = BAT * (PORCENTAJE_IMPUESTO_RENTA/100D);
								impuestosMap.put(mes, impuestos);
								if (impuestos > 0D)
									utilidadMap.put(mes, BAT-impuestos);
								BAT -= impuestos;
								utilidadRealMap.put(mes, BAT);
							}
							
							myList.add(new MyRowTreeTableEstadoResultados("<html><b>" + String.valueOf(PORCENTAJE_PARTICIPACION_TRABAJADORES) + "% PARTICIPACION TRABAJADORES: </b></html>", "Total", participacionTrabajadoresMap, participacionTrabajadoresMap, nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected(), 0L));
							myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "", nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected()));
							myList.add(new MyRowTreeTableEstadoResultados("<html><b>UTILIDAD ANTES DE IMPUESTOS: </b></html>", "Total", utilidadAntesImpuestosMap, utilidadAntesImpuestosRealMap, nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected(), 0L));
							myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "", nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected()));
							myList.add(new MyRowTreeTableEstadoResultados("<html><b>" + String.valueOf(PORCENTAJE_IMPUESTO_RENTA) + "% IMPUESTO A LA RENTA: </b></html>", "Total", impuestosMap, impuestosMap, nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected(), 0L));
							myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "", nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected()));
							myList.add(new MyRowTreeTableEstadoResultados("<html><b>UTILIDAD / PERDIDA DEL EJERCICIO: </b></html>", "Total", utilidadMap, utilidadRealMap, nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected(), 0L));
							myList.add(new MyRowTreeTableEstadoResultados(null, "Total", "", nivelesVisibles, true, null, null, null, getCbAcumulado().isSelected()));
							myTreeTableModel = new MyTreeTableModelEstadoResultadosMensual(myList);
						}
						//Creo el árbol enviando el modelo
						getTreeTblEstadoResultados().setModel(myTreeTableModel);
						//Opcion para que el árbol salga expandido y no se pueda reordenar
						getTreeTblEstadoResultados().expandAll();
						//Opcion para que no se pueda reordenar el arbol
						getTreeTblEstadoResultados().getTableHeader().setReorderingAllowed(false);
						getTreeTblEstadoResultados().setSortable(false);
						getTreeTblEstadoResultados().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						//Seteo el ancho del treetable
						setAnchoColumnas(getCbAcumulado().isSelected());
						setUpdateMode();
					} catch (GenericBusinessException e) {
						e.printStackTrace();
					}
				}
			} else {
				SpiritAlert.createAlert("Debe al menos existir un Periodo", SpiritAlert.WARNING);
			}
		} else {
			SpiritAlert.createAlert("Debe al menos existir un Plan de Cuenta", SpiritAlert.WARNING);
		}
	}
	
	public Map mapearPlanCuentas(Collection cuentasColeccion) {
		Map cuentasMap = new HashMap();
		Iterator cuentasIterator = cuentasColeccion.iterator();
		while (cuentasIterator.hasNext()) {
			CuentaIf cuenta = (CuentaIf) cuentasIterator.next();
			cuentasMap.put(cuenta.getId(), cuenta);
		}
		
		return cuentasMap;
	}
	
	public Map mapearSaldoCuenta(Long idPeriodo) {
		Map saldoCuentaMap = new HashMap();
		try {
		Iterator saldoCuentaIt = SessionServiceLocator.getSaldoCuentaSessionService().findSaldoCuentaByPeriodoId(idPeriodo).iterator();
		while (saldoCuentaIt.hasNext()) {
			SaldoCuentaIf saldoCuenta = (SaldoCuentaIf) saldoCuentaIt.next();
			saldoCuentaMap.put(saldoCuenta.getMes()+saldoCuenta.getAnio()+String.valueOf(saldoCuenta.getCuentaId()), saldoCuenta.getValor());
		}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return saldoCuentaMap;
	}
	
	public Map mapearTiposCuenta(Collection tiposCuentaColeccion) {
		Map tiposCuentaMap = new HashMap();
		Iterator tiposCuentaIterator = tiposCuentaColeccion.iterator();
		while (tiposCuentaIterator.hasNext()) {
			TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaIterator.next();
			tiposCuentaMap.put(tipoCuenta.getId(), tipoCuenta);
		}
		
		return tiposCuentaMap;
	}
	
	public Map mapearTiposResultado(Collection tiposResultadoColeccion) {
		Map tiposResultadoMap = new HashMap();
		Iterator tiposResultadoIterator = tiposResultadoColeccion.iterator();
		while (tiposResultadoIterator.hasNext()) {
			TipoResultadoIf tipoResultado = (TipoResultadoIf) tiposResultadoIterator.next();
			tiposResultadoMap.put(tipoResultado.getId(), tipoResultado);
		}
		
		return tiposResultadoMap;
	}
	
	private Map mapearSaldosTiposResultado(Map tiposResultadoMap) {
		Map saldosTiposResultadoMap = new HashMap();
		Iterator it = tiposResultadoMap.keySet().iterator();
		while (it.hasNext()) {
			Long keyMap = (Long) it.next();
			TipoResultadoIf tipoResultado = (TipoResultadoIf) tiposResultadoMap.get(keyMap);
			saldosTiposResultadoMap.put(tipoResultado.getId(), 0D);
		}
		
		return saldosTiposResultadoMap;
	}
	
	private List<CuentaIf> getCuentasEstadoResultadosListByTipoResultadoId(Collection cuentasEstadoResultadosColeccion, Long idTipoResultado) {
		List<CuentaIf> cuentasEstadoResultadosList = new ArrayList<CuentaIf>();
		Iterator cuentasEstadoResultadosIterator = cuentasEstadoResultadosColeccion.iterator();
		
		while (cuentasEstadoResultadosIterator.hasNext()) {
			CuentaIf cuenta = (CuentaIf) cuentasEstadoResultadosIterator.next();
			if (cuenta.getTiporesultadoId() != null && cuenta.getTiporesultadoId().compareTo(idTipoResultado) == 0)
				cuentasEstadoResultadosList.add(cuenta);
		}
		
		return cuentasEstadoResultadosList;
	}
	
	private List<AsientoDetalleIf> getAsientoDetalleByCuentaIdList(ArrayList asientoDetallesAuxiliarList, Long cuentaId) {
		List<AsientoDetalleIf> asientoDetallesList = new ArrayList<AsientoDetalleIf>();
		Iterator asientoDetallesAuxiliarIterator = asientoDetallesAuxiliarList.iterator();
		
		while(asientoDetallesAuxiliarIterator.hasNext()) {
			Object[] data = (Object[]) asientoDetallesAuxiliarIterator.next();
			AsientoIf asiento = (AsientoIf) data[0];
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) data[1];
			if (asientoDetalle.getCuentaId().compareTo(cuentaId) == 0 && (asiento.getAsientoCierre() == null || !asiento.getAsientoCierre().equals("S"))) {
				asientoDetallesList.add(asientoDetalle);
				asientoDetallesAuxiliarIterator.remove();
			}
		}
		return asientoDetallesList;
	}
	
	private ArrayList getAsientoDetalleByCuentaIdByFechaInicioAndFechaFinList(ArrayList asientoDetallesAuxiliarList, CuentaIf cuenta, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		ArrayList asientoDetallesList = new ArrayList<AsientoDetalleIf>();
		Iterator asientoDetallesAuxiliarIterator = asientoDetallesAuxiliarList.iterator();
		
		while(asientoDetallesAuxiliarIterator.hasNext()) {
			Object[] data = (Object[]) asientoDetallesAuxiliarIterator.next();
			AsientoIf asiento = (AsientoIf) data[0];
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) data[1];
			if (asientoDetalle.getCuentaId().compareTo(cuenta.getId()) == 0 && Utilitarios.dateAfter(new java.sql.Date(asiento.getFecha().getTime()), fechaInicio) && Utilitarios.dateBefore(new java.sql.Date(asiento.getFecha().getTime()), fechaFin)) {
				asientoDetallesList.add(data);
				asientoDetallesAuxiliarIterator.remove();
			}
		}
		return asientoDetallesList;
	}
	
	public static double obtenerSaldoCuentaPeriodoDetalleAnterior(List<SaldoCuentaIf> saldosCuentasList, CuentaIf cuentaEstadoResultadosIf) {
		double saldoCuentaPeriodoDetalleAnterior = 0D;
		Iterator saldosCuentasIterator = saldosCuentasList.iterator();
		
		while(saldosCuentasIterator.hasNext()) {
			SaldoCuentaIf saldoCuenta = (SaldoCuentaIf) saldosCuentasIterator.next();
			if (saldoCuenta.getCuentaId().compareTo(cuentaEstadoResultadosIf.getId()) == 0)
				saldoCuentaPeriodoDetalleAnterior = saldoCuenta.getValor().doubleValue();
		}
		
		return saldoCuentaPeriodoDetalleAnterior; 
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
	
	public boolean validateFields() {
		if (getCmbPlanCuenta().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un plan de cuentas", SpiritAlert.INFORMATION);
			getCmbPlanCuenta().grabFocus();
			return false;
		}
		
		if (getCmbEjercicioContable().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un periodo contable", SpiritAlert.INFORMATION);
			getCmbEjercicioContable().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public void report() {
		if (getMode() == SpiritMode.UPDATE_MODE) {
			DefaultTableModel tableModel = treeTableModelToDefaultTableModel();
			
			if (tableModel.getRowCount() > 0) {
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir el Estado de Resultados?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if(opcion == JOptionPane.YES_OPTION) {
					try {
						String fileName = "";
						if (getCbAcumulado().isSelected())
							fileName = "jaspers/contabilidad/RPEstadoResultados.jasper";
						else
							fileName = "jaspers/contabilidad/RPEstadoResultadosMensual.jasper";
						HashMap parametrosMap = new HashMap();
						//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("ESTADO DE RESULTADOS").iterator().next();
						MenuIf menu = null;
						Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre("ESTADO DE RESULTADOS").iterator();
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
						parametrosMap.put("codigoPlanCuenta", ((PlanCuentaIf) getCmbPlanCuenta().getSelectedItem()).getCodigo());
						parametrosMap.put("nombrePlanCuenta", ((PlanCuentaIf) getCmbPlanCuenta().getSelectedItem()).getNombre());
						parametrosMap.put("fechaInicial", new java.sql.Date(getCmbFechaInicio().getDate().getTime()).toString());
						parametrosMap.put("fechaFinal", new java.sql.Date(getCmbFechaFin().getDate().getTime()).toString());
						parametrosMap.put("saldoFinal", this.getTxtSaldoFinal().getText());
						ReportModelImpl.processReport(fileName, parametrosMap, tableModel, true);
					
					} catch (GenericBusinessException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			} else {
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
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
	
	private String determinarTipoCuentaSegunAsiento(AsientoDetalleIf asientoDetalle) {
		BigDecimal zero = new BigDecimal(0.00);
		if (asientoDetalle.getDebe().compareTo(zero) == 1) {
			return "D";
		} else
			return "H";
	}
	
	private Vector<CuentaIf> generarVectorCuentasEstadoResultados(Collection cuentasEstadoResultadosColeccion) {
		Vector<CuentaIf> cuentasEstadoResultadosVector = new Vector<CuentaIf>();
		Iterator cuentasEstadoResultadosIterator = cuentasEstadoResultadosColeccion.iterator();
		while (cuentasEstadoResultadosIterator.hasNext()) {
			CuentaIf cuenta = (CuentaIf) cuentasEstadoResultadosIterator.next();
			cuentasEstadoResultadosVector.add(cuenta);
		}
		
		return cuentasEstadoResultadosVector;
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
	
	private void calcularSaldoCuentaPadre(LinkedHashMap cuentasEstadoResultadosMap, CuentaIf cuentaEstadoResultadosIf, double saldoAnteriorCuentaHija) {
		if (cuentaEstadoResultadosIf.getPadreId() != null) {
			CuentaIf cuentaPadre = (CuentaIf) cuentasMap.get(cuentaEstadoResultadosIf.getPadreId());
			double saldoCuentaPadre = 0D;
			double saldoCuentaHija = (Double) cuentasEstadoResultadosMap.get(cuentaEstadoResultadosIf.getId());
			if (cuentaPadre != null) {
				Long tipoResultadoId = cuentaEstadoResultadosIf.getTiporesultadoId();
				TipoResultadoIf tipoResultadoIf = null;
				if (tipoResultadoId != null)
					tipoResultadoIf = (TipoResultadoIf) tiposResultadoMap.get(tipoResultadoId);
				
				if (cuentasEstadoResultadosMap.get(cuentaPadre.getId()) != null) {
					if (tipoResultadoIf == null || !tipoResultadoIf.getCodigo().equals("OE"))
						saldoCuentaPadre = (Double) cuentasEstadoResultadosMap.get(cuentaPadre.getId()) + saldoCuentaHija - saldoAnteriorCuentaHija;
					else
						saldoCuentaPadre = (Double) cuentasEstadoResultadosMap.get(cuentaPadre.getId()) - saldoCuentaHija + saldoAnteriorCuentaHija;
					
					saldoAnteriorCuentaHija = (Double) cuentasEstadoResultadosMap.get(cuentaPadre.getId());
				} else {
					if (tipoResultadoIf == null || !tipoResultadoIf.getCodigo().equals("OE"))
						saldoCuentaPadre = saldoCuentaHija;
					else
						saldoCuentaPadre = saldoCuentaHija * -1D;
				}
				
				cuentasEstadoResultadosMap.put(cuentaPadre.getId(), saldoCuentaPadre);
				if (cuentaPadre.getNivel() != null)
					calcularSaldoCuentaPadre(cuentasEstadoResultadosMap, cuentaPadre, saldoAnteriorCuentaHija);
			}
		}
	}
	
	private void calcularSaldoCuentaPadre(LinkedHashMap cuentasEstadoResultadosMap, CuentaIf cuentaEstadoResultadosIf, Map saldoAnteriorCuentaHijaMap) {
		if (cuentaEstadoResultadosIf.getPadreId() != null) {
			boolean periodoDetalleIgual = false;
			int yearFechaInicioMovimiento = fechaInicioCalculo.getYear() + 1900;
			int mesFechaInicioMovimiento = fechaInicioCalculo.getMonth() + 1;
			int yearFechaFinMovimiento = fechaFinCalculo.getYear() + 1900;
			int mesFechaFinMovimiento = fechaFinCalculo.getMonth() + 1;
			if (yearFechaInicioMovimiento == yearFechaFinMovimiento && mesFechaInicioMovimiento == mesFechaFinMovimiento)
				periodoDetalleIgual = true;
			int meses = 1;
			if (!periodoDetalleIgual)
				meses = mesFechaFinMovimiento - mesFechaInicioMovimiento + 1;
			
			CuentaIf cuentaPadre = (CuentaIf) cuentasMap.get(cuentaEstadoResultadosIf.getPadreId());
			double saldoCuentaPadre = 0D;
			Map saldoCuentaPadreMap = new HashMap();
			Map saldoCuentaHijaMap = (Map) cuentasEstadoResultadosMap.get(cuentaEstadoResultadosIf.getId());
			if (cuentaPadre != null) {
				Long tipoResultadoId = cuentaEstadoResultadosIf.getTiporesultadoId();
				TipoResultadoIf tipoResultadoIf = (tipoResultadoId != null)?(TipoResultadoIf) tiposResultadoMap.get(tipoResultadoId):null;
				//Iterator it = saldoCuentaHijaMap.keySet().iterator();
				for (int i=1; i<=meses; i++) {
					int mes = fechaInicioCalculo.getMonth() + i;
					double saldoCuentaHija = (saldoCuentaHijaMap.get(mes) != null)?((Double) saldoCuentaHijaMap.get(mes)).doubleValue():0D;
					double saldoAnteriorCuentaHija = (saldoAnteriorCuentaHijaMap != null && saldoAnteriorCuentaHijaMap.get(mes) != null)?((Double) saldoAnteriorCuentaHijaMap.get(mes)).doubleValue():0D;
					
					if (cuentasEstadoResultadosMap.get(cuentaPadre.getId()) != null) {
						Map saldoMap = (Map) cuentasEstadoResultadosMap.get(cuentaPadre.getId());
						double saldo = (saldoMap!=null && saldoMap.get(mes)!=null)?((Double) saldoMap.get(mes)).doubleValue():0D;
						
						if (tipoResultadoIf == null || !tipoResultadoIf.getCodigo().equals("OE"))
							saldoCuentaPadre = saldo + saldoCuentaHija - saldoAnteriorCuentaHija;
						else
							saldoCuentaPadre = saldo - saldoCuentaHija + saldoAnteriorCuentaHija;
					} else {
						if (tipoResultadoIf == null || !tipoResultadoIf.getCodigo().equals("OE"))
							saldoCuentaPadre = saldoCuentaHija;
						else
							saldoCuentaPadre = saldoCuentaHija * -1D;
					}
					
					saldoCuentaPadreMap.put(mes, saldoCuentaPadre);
				}
				
				if (cuentasEstadoResultadosMap.get(cuentaPadre.getId()) != null)
					saldoAnteriorCuentaHijaMap = (Map) cuentasEstadoResultadosMap.get(cuentaPadre.getId());
				
				cuentasEstadoResultadosMap.put(cuentaPadre.getId(), saldoCuentaPadreMap);
				if (cuentaPadre.getNivel() != null)
					calcularSaldoCuentaPadre(cuentasEstadoResultadosMap, cuentaPadre, saldoAnteriorCuentaHijaMap);
			}
		}
	}
	
	private void calcularSaldoTipoResultado(LinkedHashMap cuentasEstadoResultadosMap, Vector<CuentaIf> cuentasEstadoResultadosVector, TipoResultadoIf tipoResultadoIf) {
		double saldoTipoResultado = 0D;
		
		for (int i=0; i<cuentasEstadoResultadosVector.size(); i++) {
			CuentaIf cuentaEstadoResultados = cuentasEstadoResultadosVector.get(i);
			if (cuentaEstadoResultados.getTiporesultadoId().compareTo(tipoResultadoIf.getId()) == 0) {
				if (cuentasEstadoResultadosMap.get(cuentaEstadoResultados.getId()) != null) {
					double saldoCuentaEstadoResultados = (Double) cuentasEstadoResultadosMap.get(cuentaEstadoResultados.getId());
					saldoTipoResultado += saldoCuentaEstadoResultados;
				}
			}
		}
		
		cuentasEstadoResultadosMap.put(tipoResultadoIf.getCodigo(), saldoTipoResultado);
	}
	
	private DefaultTableModel treeTableModelToDefaultTableModel() {
		int rows = getTreeTblEstadoResultados().getModel().getRowCount();
		int cols = getTreeTblEstadoResultados().getModel().getColumnCount();
		cleanTable();
		DefaultTableModel tableModelReporte = new DefaultTableModel();
				
		if (getCbAcumulado().isSelected()){
			tableModelReporte = new DefaultTableModel(rows,4);
			tableModelReporte.addColumn("Cuenta");
			tableModelReporte.addColumn("Saldo");
			tableModelReporte.addColumn("Margen");
			tableModelReporte.addColumn("Porcentaje");
		}else{
			tableModelReporte = new DefaultTableModel(rows,13);
			tableModelReporte.addColumn("Cuenta");
			tableModelReporte.addColumn("Enero");
			tableModelReporte.addColumn("Febrero");
			tableModelReporte.addColumn("Marzo");
			tableModelReporte.addColumn("Abril");
			tableModelReporte.addColumn("Mayo");
			tableModelReporte.addColumn("Junio");
			tableModelReporte.addColumn("Julio");
			tableModelReporte.addColumn("Agosto");
			tableModelReporte.addColumn("Septiembre");
			tableModelReporte.addColumn("Octubre");
			tableModelReporte.addColumn("Noviembre");
			tableModelReporte.addColumn("Diciembre");
		}		
		
		for(int i= rows;i>0;--i)
			tableModelReporte.removeRow(i-1);
		
		try {
			for (int i = 0; i < rows; i++) {
				Vector<String> fila = new Vector<String>();
				String sangria = "";
				
				for (int j = 0; j < cols; j++) {
					if (getTreeTblEstadoResultados().getModel().getValueAt(i,j) != null) {
						String data = getTreeTblEstadoResultados().getModel().getValueAt(i,j).toString().trim();
						int indexOfCierreCorchete = 0;
						String codigoCuenta = "";
						String nombreCuenta = "";
						if (data.contains("]")) {
							indexOfCierreCorchete = data.indexOf("]");
							codigoCuenta = data.substring(1, indexOfCierreCorchete);
							nombreCuenta = data.substring(indexOfCierreCorchete+1);
						}

						int NIVEL_MINIMO = 1;
						if (j == 0) {
							Collection cuentaCollection = SessionServiceLocator.getCuentaSessionService().findCuentaByCodigo(codigoCuenta);
							
							if (cuentaCollection.size() > 0) {
								CuentaIf cuenta = (CuentaIf) cuentaCollection.iterator().next();
								int nivel = cuenta.getNivel() - NIVEL_MINIMO;
								if (cuenta.getCodigo().startsWith("7"))
									nivel -= 2;
								for (int k = 0; k < nivel; k++)
									sangria = sangria.concat("                      "); //22
							}
						}
						
						data = data.replaceAll("<html><b>", "").replaceAll("</b></html>","");
						if (j == 0 && getCbAcumulado().isSelected()){
							//fila.add(sangria.concat(data));
							if (data.contains("]")) {
								fila.add(codigoCuenta);
								fila.add(nombreCuenta);
							}else{
								fila.add("");
								fila.add(data);								
							}
						}
						else if (j == 0 && !getCbAcumulado().isSelected()) {
							if (data.length() >= 43)
								data = data.substring(0, 43);
							//fila.add(sangria.concat(data));
							if (data.contains("]")) {
								fila.add(codigoCuenta);
								fila.add(nombreCuenta);
							}else{
								fila.add("");
								fila.add(data);								
							}
						} else if (j >= 1 && getCbAcumulado().isSelected())
							fila.add(data.concat(sangria));
						else
							fila.add(data);
					} else{
						fila.add("");
						fila.add("");
					}
				}
			
				tableModelReporte.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		return tableModelReporte;
	}
	
	public void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) this.getTblEstadoResultados().getModel();
		for (int i = this.getTblEstadoResultados().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}
	
	public void cleanTreeTable() {
		MyTreeTableModelEstadoResultados model = myTreeTableModel;	
		if (model != null) {
			for (int i = model.getRowCount(); i > 0; --i)
				model.removeRow(i - 1);
		}
	}
	
	private Map filtrosBusqueda() {
		Map aMap = new HashMap();
		aMap.put("idPeriodo", idPeriodo);
		aMap.put("idPlanCuenta", idPlanCuenta);
		return aMap;
	}
}