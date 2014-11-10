package com.spirit.contabilidad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
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
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.SaldoCuentaIf;
import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.contabilidad.entity.TipoResultadoIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.panel.JPEstadoResultadoComparativo;
import com.spirit.contabilidad.util.MyRowTreeTableEstadoResultadosComparativo;
import com.spirit.contabilidad.util.MyTreeTableModelEstadoResultadoComparativo;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class EstadoResultadosComparativoModel extends JPEstadoResultadoComparativo {

	private static final long serialVersionUID = -4493555932231658898L;
	private static PeriodoIf periodo;
	private static PeriodoIf comparativo;
	private static Date fechaInicioPeriodo;
	private static Date fechaInicioComparativo;
	private static Date fechaInicioPeriodoAuxiliar;
	private static Date fechaInicioComparativoAuxiliar;
	private static Date fechaFinPeriodo;
	private static Date fechaFinComparativo;
	private static Date fechaFinPeriodoAuxiliar;
	private static Date fechaFinComparativoAuxiliar;
	private Long idPlanCuenta;
	private Long idPeriodo;
	private Long idComparativo;
	private PlanCuentaIf planCuenta;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private MyTreeTableModelEstadoResultadoComparativo myTreeTableModel;
	Vector<CuentaIf> cuentasEstadoResultadosTreeTable = new Vector<CuentaIf>();
	Vector<CuentaIf> cuentasEstadoResultadosComparativoTreeTable = new Vector<CuentaIf>();
	private Map cuentasMap = new HashMap();
	private Map tiposCuentaMap = new HashMap();
	private Map tiposResultadoMap = new HashMap();
	private final static String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private final static String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	
	public EstadoResultadosComparativoModel(){
		initKeyListeners();
		initListeners();
		cargarCombos();
		this.showSaveMode();
		new HotKeyComponent(this);
	}
	
	public void initKeyListeners(){
		getCmbFechaInicioPeriodo().setLocale(Utilitarios.esLocale);
		getCmbFechaFinPeriodo().setLocale(Utilitarios.esLocale);
		getCmbFechaInicioPeriodo().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFinPeriodo().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicioPeriodo().setEditable(false);
		getCmbFechaFinPeriodo().setEditable(false);	
		getCmbFechaInicioPeriodo().setShowNoneButton(false);
		getCmbFechaFinPeriodo().setShowNoneButton(false);
		
		getCmbFechaInicioComparativo().setLocale(Utilitarios.esLocale);
		getCmbFechaFinComparativo().setLocale(Utilitarios.esLocale);
		getCmbFechaInicioComparativo().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFinComparativo().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicioComparativo().setEditable(false);
		getCmbFechaFinComparativo().setEditable(false);	
		getCmbFechaInicioComparativo().setShowNoneButton(false);
		getCmbFechaFinComparativo().setShowNoneButton(false);
		
		getBtnConsultar().addKeyListener(oKeyAdapterBtnConsultar);
		getCmbNivelesVisibles().setNextFocusableComponent(getBtnConsultar());
		
	}
	
	public void clean() {
		
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

	public void showSaveMode() {
		setSaveMode();
		cleanTreeTable();
		cleanTable();
		getCmbPlanCuenta().grabFocus();
	}

	private void cargarCombos() {
		cargarComboPlanCuenta();
		cargarComboPeriodo();	
		cargarComboComparativo();
		cargarComboNivelesVisibles();
	}
	
	private void cargarComboPlanCuenta() {
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
	
	private void cargarComboComparativo(){
		List periodos = ContabilidadFinder.findPeriodo(Parametros.getIdEmpresa());
		refreshCombo(getCmbComparativo(),periodos);
	}
	
	private void cargarComboNivelesVisibles() {
		getCmbNivelesVisibles().removeAllItems();
		
		if (planCuenta != null) {
			int nivelMaximo = SessionServiceLocator.getCuentaSessionService().getNivelMaximoByPlanCuentaId(planCuenta.getId());
			
			for (int i=nivelMaximo; i>0; i--)
				getCmbNivelesVisibles().addItem(String.valueOf(i));
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
				//Seteo la fecha de inicio segun el la fecha de inicio del periodo contable
				Calendar calendarInicio = new GregorianCalendar();
				if (periodo != null) {
					calendarInicio.setTime(periodo.getFechaini());
					fechaInicioPeriodo = periodo.getFechaini();
					fechaInicioPeriodoAuxiliar = periodo.getFechaini();
					
					//Seteo la fecha de fin segun el la fecha de fin del periodo contable
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(periodo.getFechafin());
					fechaFinPeriodo = periodo.getFechafin();
					fechaFinPeriodoAuxiliar = periodo.getFechafin();
					
					getCmbFechaInicioPeriodo().setCalendar(calendarInicio);
					getCmbFechaFinPeriodo().setCalendar(calendarFin);
				}
				cleanTable();
				cleanTreeTable();
			}
		});
		
		getCmbComparativo().addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent evento) {
				comparativo = (PeriodoIf) getCmbComparativo().getModel().getSelectedItem();
				//Seteo la fecha de inicio segun el la fecha de inicio del periodo contable
				Calendar calendarInicio = new GregorianCalendar();
				if (comparativo != null) {
					calendarInicio.setTime(comparativo.getFechaini());
					fechaInicioComparativo = comparativo.getFechaini();
					fechaInicioComparativoAuxiliar = comparativo.getFechaini();
					
					//Seteo la fecha de fin segun el la fecha de fin del periodo contable
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(comparativo.getFechafin());
					fechaFinComparativo = comparativo.getFechafin();
					fechaFinComparativoAuxiliar = comparativo.getFechafin();
					
					getCmbFechaInicioComparativo().setCalendar(calendarInicio);
					getCmbFechaFinComparativo().setCalendar(calendarFin);
				}
				cleanTable();
				cleanTreeTable();
			}
		});
		
		getCmbFechaInicioPeriodo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				fechaInicioPeriodo = (Date) ((DateComboBox) evento.getSource()).getDate();
				clean();
				if(fechaInicioPeriodo.before(periodo.getFechaini()) || fechaInicioPeriodo.after(periodo.getFechafin())){
					SpiritAlert.createAlert("Por favor seleccione una fecha dentro del periodo contable!", SpiritAlert.INFORMATION);
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(fechaInicioPeriodoAuxiliar);
					fechaInicioPeriodo = fechaInicioPeriodoAuxiliar;
					getCmbFechaInicioPeriodo().setCalendar(calendarInicio);
				} else if(fechaInicioPeriodo.after(fechaFinPeriodo)) {
					SpiritAlert.createAlert("La fecha de inicio no puede ser posterior a la fecha de fin!", SpiritAlert.INFORMATION);
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(fechaInicioPeriodoAuxiliar);
					fechaInicioPeriodo = fechaInicioPeriodoAuxiliar;
					getCmbFechaInicioPeriodo().setCalendar(calendarInicio);
				} else if(!fechaInicioPeriodo.equals(fechaInicioPeriodoAuxiliar) || !fechaFinPeriodo.equals(fechaFinPeriodoAuxiliar)) {
					fechaInicioPeriodoAuxiliar = fechaInicioPeriodo;
				}
			}
		});
		
		getCmbFechaFinPeriodo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				fechaFinPeriodo = (Date) ((DateComboBox) evento.getSource()).getDate();
				clean();
				if(fechaFinPeriodo.after(periodo.getFechafin()) || fechaFinPeriodo.before(periodo.getFechaini())){
					JOptionPane.showMessageDialog(null,	"Por favor seleccione una fecha dentro del periodo contable!", "Mensaje de Aviso",	JOptionPane.INFORMATION_MESSAGE);
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(fechaFinPeriodoAuxiliar);
					fechaFinPeriodo = fechaFinPeriodoAuxiliar;
					getCmbFechaFinPeriodo().setCalendar(calendarFin);
				} else if(fechaFinPeriodo.before(fechaInicioPeriodo)) {
					JOptionPane.showMessageDialog(null,	"La fecha final no puede ser anterior a la fecha de inicio!", "Mensaje de Aviso",	JOptionPane.INFORMATION_MESSAGE);
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(fechaFinPeriodoAuxiliar);
					fechaFinPeriodo = fechaFinPeriodoAuxiliar;
					getCmbFechaFinPeriodo().setCalendar(calendarFin);
				} else if (!fechaFinPeriodo.equals(fechaFinPeriodoAuxiliar) || !fechaInicioPeriodo.equals(fechaInicioPeriodoAuxiliar)) {
					fechaFinPeriodoAuxiliar = fechaFinPeriodo;
				}
			}
		});
		
		getCmbFechaInicioComparativo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				fechaInicioComparativo = (Date) ((DateComboBox) evento.getSource()).getDate();
				clean();
				if(fechaInicioComparativo.before(comparativo.getFechaini()) || fechaInicioComparativo.after(comparativo.getFechafin())){
					SpiritAlert.createAlert("Por favor seleccione una fecha dentro del periodo contable!", SpiritAlert.INFORMATION);
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(fechaInicioComparativoAuxiliar);
					fechaInicioComparativo = fechaInicioComparativoAuxiliar;
					getCmbFechaInicioComparativo().setCalendar(calendarInicio);
				} else if(fechaInicioComparativo.after(fechaFinComparativo)) {
					SpiritAlert.createAlert("La fecha de inicio no puede ser posterior a la fecha de fin!", SpiritAlert.INFORMATION);
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(fechaInicioComparativoAuxiliar);
					fechaInicioComparativo = fechaInicioComparativoAuxiliar;
					getCmbFechaInicioComparativo().setCalendar(calendarInicio);
				} else if(!fechaInicioComparativo.equals(fechaInicioComparativoAuxiliar) || !fechaFinComparativo.equals(fechaFinComparativoAuxiliar)) {
					fechaInicioComparativoAuxiliar = fechaInicioComparativo;
				}
			}
		});
		
		getCmbFechaFinComparativo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				fechaFinComparativo = (Date) ((DateComboBox) evento.getSource()).getDate();
				clean();
				if(fechaFinComparativo.after(comparativo.getFechafin()) || fechaFinComparativo.before(comparativo.getFechaini())){
					JOptionPane.showMessageDialog(null,	"Por favor seleccione una fecha dentro del periodo contable!", "Mensaje de Aviso",	JOptionPane.INFORMATION_MESSAGE);
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(fechaFinComparativoAuxiliar);
					fechaFinComparativo = fechaFinComparativoAuxiliar;
					getCmbFechaFinComparativo().setCalendar(calendarFin);
				} else if(fechaFinComparativo.before(fechaInicioComparativo)) {
					JOptionPane.showMessageDialog(null,	"La fecha final no puede anterior a la fecha de inicio!", "Mensaje de Aviso",	JOptionPane.INFORMATION_MESSAGE);
					Calendar calendarFin = new GregorianCalendar();
					calendarFin.setTime(fechaFinComparativoAuxiliar);
					fechaFinComparativo = fechaFinComparativoAuxiliar;
					getCmbFechaFinComparativo().setCalendar(calendarFin);
				} else if (!fechaFinComparativo.equals(fechaFinComparativoAuxiliar) || !fechaInicioComparativo.equals(fechaInicioComparativoAuxiliar)) {
					fechaFinComparativoAuxiliar = fechaFinComparativo;
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
	
	Comparator<CuentaIf> ordenadorCuentasPorCodigo = new Comparator<CuentaIf>(){
		public int compare(CuentaIf o1, CuentaIf o2) {
			return o1.getCodigo().compareTo(o2.getCodigo());
		}		
	};
	
	//Funcion que construye el treetable con todos sus resultados
	private void mostrarDetallesSaldoInicialFinal(){
		if (planCuenta != null){
			idPlanCuenta = planCuenta.getId();
			
			if (periodo != null){
				idPeriodo = periodo.getId();
				
				LinkedHashMap cuentasEstadoResultadosMap = new LinkedHashMap();
				LinkedHashMap cuentasEstadoResultadosComparativoMap = new LinkedHashMap();
				cuentasEstadoResultadosTreeTable.clear();
				cuentasEstadoResultadosComparativoTreeTable.clear();
				if(validateFields()) {
					try {
						double totalIngresos = 0D;
						double totalGastos = 0D;
						double totalOtrosIngresos = 0D;
						double totalOtrosEgresos = 0D;
						double totalFinal = 0D;
						Double porcentaje = 0D;
						double totalIngresosComparativo = 0D;
						double totalGastosComparativo = 0D;
						double totalOtrosIngresosComparativo = 0D;
						double totalOtrosEgresosComparativo = 0D;
						double totalFinalComparativo = 0D;
						Double porcentajeComparativo = 0D;
						Double diferenciaPorcentaje = 0D;
						int contador = 0;
						int nivelesVisibles = Integer.parseInt(getCmbNivelesVisibles().getSelectedItem().toString());
						idPlanCuenta = planCuenta.getId();
						idPeriodo = periodo.getId();
						idComparativo = comparativo.getId();
						Long idEmpresa = Parametros.getIdEmpresa();
						java.sql.Date fechaInicioMovimiento = new java.sql.Date(fechaInicioPeriodo.getYear(),fechaInicioPeriodo.getMonth(),fechaInicioPeriodo.getDate());
						java.sql.Date fechaFinMovimiento = new java.sql.Date(fechaFinPeriodo.getYear(),fechaFinPeriodo.getMonth(),fechaFinPeriodo.getDate());
						java.sql.Date fechaInicioMovimientoComparativo = new java.sql.Date(fechaInicioComparativo.getYear(),fechaInicioComparativo.getMonth(),fechaInicioComparativo.getDate());
						java.sql.Date fechaFinMovimientoComparativo = new java.sql.Date(fechaFinComparativo.getYear(),fechaFinComparativo.getMonth(),fechaFinComparativo.getDate());
						Collection cuentasColeccion = SessionServiceLocator.getCuentaSessionService().findCuentaByPlancuentaId(planCuenta.getId());
						Collections.sort((ArrayList)cuentasColeccion,ordenadorCuentasPorCodigo);
						cuentasMap = mapearPlanCuentas(cuentasColeccion);
						Collection tiposCuentaColeccion = SessionServiceLocator.getTipoCuentaSessionService().getTipoCuentaList();
						tiposCuentaMap = mapearTiposCuenta(tiposCuentaColeccion);
						Collection tiposResultadoColeccion = SessionServiceLocator.getTipoResultadoSessionService().getTipoResultadoList();
						tiposResultadoMap = mapearTiposResultado(tiposResultadoColeccion);
						Collection cuentasEstadoResultadosColeccion = SessionServiceLocator.getCuentaSessionService().findCuentasForEstadoResultados(planCuenta.getId(), periodo.getId(), fechaInicioMovimiento, fechaFinMovimiento);
						Vector<CuentaIf> cuentasEstadoResultadosVector = generarVectorCuentasEstadoResultados(cuentasEstadoResultadosColeccion);
						ArrayList asientoDetalleColeccion = (ArrayList) SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByEmpresaByPeriodoIdByPlanCuentaIdByStatusAndByFechaInicioAndFechaFin(idEmpresa, idPeriodo, idPlanCuenta, ESTADO_ACTIVO, fechaInicioMovimiento, fechaFinMovimiento, true);
						Collection cuentasEstadoResultadosComparativoColeccion = SessionServiceLocator.getCuentaSessionService().findCuentasForEstadoResultados(planCuenta.getId(), comparativo.getId(), fechaInicioMovimientoComparativo, fechaFinMovimientoComparativo);
						Vector<CuentaIf> cuentasEstadoResultadosComparativoVector = generarVectorCuentasEstadoResultados(cuentasEstadoResultadosComparativoColeccion);
						ArrayList asientoDetalleComparativoColeccion = (ArrayList) SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByEmpresaByPeriodoIdByPlanCuentaIdByStatusAndByFechaInicioAndFechaFin(idEmpresa, idComparativo, idPlanCuenta, ESTADO_ACTIVO, fechaInicioMovimientoComparativo, fechaFinMovimientoComparativo, true);
						
						for (int i=0; i<cuentasEstadoResultadosVector.size(); i++) {
							CuentaIf cuentaEstadoResultadosIf = cuentasEstadoResultadosVector.get(i);
							if (cuentaEstadoResultadosIf.getTiporesultadoId() != null) {
								Double saldoCuenta = 0D;
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
							}
						}
						
						for (int i=0; i<cuentasEstadoResultadosComparativoVector.size(); i++) {
							CuentaIf cuentaEstadoResultadosIf = cuentasEstadoResultadosComparativoVector.get(i);
							if (cuentaEstadoResultadosIf.getTiporesultadoId() != null) {
								Double saldoCuenta = 0D;
								cuentasEstadoResultadosComparativoMap.put(cuentaEstadoResultadosIf.getId(), saldoCuenta);
								List<AsientoDetalleIf> asientoDetallesList = getAsientoDetalleByCuentaIdList(asientoDetalleComparativoColeccion, cuentaEstadoResultadosIf.getId());
								Iterator asientosDetalleCuentaEstadoResultadosIterator = asientoDetallesList.iterator();
								while (asientosDetalleCuentaEstadoResultadosIterator.hasNext()) {
									AsientoDetalleIf asientoDetalleCuentaEstadoResultadosIf = (AsientoDetalleIf) asientosDetalleCuentaEstadoResultadosIterator.next();
									TipoCuentaIf tipoCuentaIf = (TipoCuentaIf) tiposCuentaMap.get(cuentaEstadoResultadosIf.getTipocuentaId());
									String tipoCuentaSegunAsiento = determinarTipoCuentaSegunAsiento(asientoDetalleCuentaEstadoResultadosIf);
									saldoCuenta = (Double) cuentasEstadoResultadosComparativoMap.get(cuentaEstadoResultadosIf.getId());
									saldoCuenta = calcularSaldoCuenta(asientoDetalleCuentaEstadoResultadosIf, tipoCuentaIf, tipoCuentaSegunAsiento, saldoCuenta);
									cuentasEstadoResultadosComparativoMap.put(cuentaEstadoResultadosIf.getId(), saldoCuenta);
								}
							}
						}
						
						for (int i=0; i<cuentasEstadoResultadosVector.size(); i++) {
							CuentaIf cuentaEstadoResultadosIf = cuentasEstadoResultadosVector.get(i);
							if (cuentaEstadoResultadosIf.getTiporesultadoId() != null)
								calcularSaldoCuentaPadre(cuentasEstadoResultadosMap, cuentaEstadoResultadosIf, 0D);
						}
						
						for (int i=0; i<cuentasEstadoResultadosComparativoVector.size(); i++) {
							CuentaIf cuentaEstadoResultadosIf = cuentasEstadoResultadosComparativoVector.get(i);
							if (cuentaEstadoResultadosIf.getTiporesultadoId() != null)
								calcularSaldoCuentaPadre(cuentasEstadoResultadosComparativoMap, cuentaEstadoResultadosIf, 0D);
						}
						
						Iterator cuentasIterator = cuentasColeccion.iterator();
						while (cuentasIterator.hasNext()) {
							CuentaIf cuentaIf = (CuentaIf) cuentasIterator.next();
							if (cuentasEstadoResultadosMap.get(cuentaIf.getId()) != null)
								cuentasEstadoResultadosTreeTable.add(cuentaIf);
							if (cuentasEstadoResultadosComparativoMap.get(cuentaIf.getId()) != null)
								cuentasEstadoResultadosComparativoTreeTable.add(cuentaIf);
						}
						
						Map parameterMap = new HashMap();
						parameterMap.put("plancuentaId", planCuenta.getId());
						parameterMap.put("nivel", 1);
						List<CuentaIf> cuentasList = (ArrayList<CuentaIf>) SessionServiceLocator.getCuentaSessionService().findCuentaByQuery(parameterMap);
						Iterator it = SessionServiceLocator.getCuentaSessionService().findCuentaByCodigo("71010").iterator();
						CuentaIf otrosIngresos = (it.hasNext())?(CuentaIf) it.next():null;
						if (otrosIngresos != null)
							cuentasList.add(otrosIngresos);
						it = SessionServiceLocator.getCuentaSessionService().findCuentaByCodigo("71020").iterator();
						CuentaIf otrosEgresos = (it.hasNext())?(CuentaIf) it.next():null;
						if (otrosEgresos != null)
							cuentasList.add(otrosEgresos);
						cuentasIterator = cuentasList.iterator();
						ArrayList myList = new ArrayList();
						
						//--------------
						
						/*for (int i=0; i<tiposResultadoVector.size(); i++) {
							TipoResultadoIf tipoResultadoIf = tiposResultadoVector.get(i);
							//TreeTable
							myList.add(new MyRowTreeTableEstadoResultadosComparativo(tipoResultadoIf, "TipoResultado", null, false, filtrosBusqueda(), null, null, null, null, null, null));
							//diferenciaPorcentaje = Math.sqrt(diferenciaPorcentaje*diferenciaPorcentaje);
							if (cuentasEstadoResultadosMap.get(tipoResultadoIf.getCodigo()) != null)
								totalResultado = (Double) cuentasEstadoResultadosMap.get(tipoResultadoIf.getCodigo());
							if (cuentasEstadoResultadosComparativoMap.get(tipoResultadoIf.getCodigo()) != null)
								totalResultadoComparativo = (Double) cuentasEstadoResultadosComparativoMap.get(tipoResultadoIf.getCodigo());
							diferenciaPorcentaje = ((totalResultado - totalResultadoComparativo)/totalResultadoComparativo)*100;
							if (String.valueOf(diferenciaPorcentaje).equals("NaN")) {
								diferenciaPorcentaje = 0.0;
							}
							if (contador == 0) {
								myList.add(new MyRowTreeTableEstadoResultadosComparativo("TOTAL DE " + tipoResultadoIf.getNombre() + ": ", "Total", formatoDecimal.format(totalResultado), true, null, null, "100.00%", formatoDecimal.format(totalResultadoComparativo), null, "100.00%", signo+formatoDecimal.format(diferenciaPorcentaje)+"%"));
							} else {
								myList.add(new MyRowTreeTableEstadoResultadosComparativo("TOTAL DE " + tipoResultadoIf.getNombre() + ": ", "Total", formatoDecimal.format(totalResultado), true, null, null, null, formatoDecimal.format(totalResultadoComparativo), null, null, signo+formatoDecimal.format(diferenciaPorcentaje)+"%"));
							}
							
							//Obtengo el Saldo Final
							if (tipoResultadoIf.getCodigo().equals("IN") || tipoResultadoIf.getCodigo().equals("OI")) {
								totalFinal += totalResultado;
								totalFinalComparativo += totalResultadoComparativo;
							} else {
								totalFinal -= totalResultado;
								totalFinalComparativo -= totalResultadoComparativo;
							}
							
							if (contador != 0) {
								porcentaje = (totalFinal / totalIngreso)*100;
								porcentaje = Math.sqrt(porcentaje*porcentaje);
								porcentajeComparativo = (totalFinalComparativo / totalIngresoComparativo)*100;
								porcentajeComparativo = Math.sqrt(porcentajeComparativo*porcentajeComparativo);
								if (String.valueOf(porcentaje).equals("NaN")) {
									porcentaje = 0.0;
								}
								if (String.valueOf(porcentajeComparativo).equals("NaN")) {
									porcentajeComparativo = 0.0;
								}
								myList.add(new MyRowTreeTableEstadoResultadosComparativo(null, "Total", "Margen " + contador + ": ", true, null, formatoDecimal.format(totalFinal), formatoDecimal.format(porcentaje) +"%", "Margen " + contador + ": ", formatoDecimal.format(totalFinalComparativo), formatoDecimal.format(porcentajeComparativo) +"%", null));
							} else {
								myList.add(new MyRowTreeTableEstadoResultadosComparativo(null, "Total", null, true, null, null, null, null, null, null, null));
								totalIngreso = totalResultado;
								totalIngresoComparativo = totalResultadoComparativo;
							}
							
							contador++;
							
							//Envio las fechas escogidas a MyRowTreeTable
							MyRowTreeTableEstadoResultadosComparativo.setFechaInicio(fechaInicioMovimiento);
							MyRowTreeTableEstadoResultadosComparativo.setFechaFin(fechaFinMovimiento);
							MyRowTreeTableEstadoResultadosComparativo.setFechaInicioComparativo(fechaInicioMovimientoComparativo);
							MyRowTreeTableEstadoResultadosComparativo.setFechaFinComparativo(fechaFinMovimientoComparativo);
							
							//Creo el modelo del árbol.
							myTreeTableModel = new MyTreeTableModelEstadoResultadoComparativo(myList);
							
							//Creo el árbol enviando el modelo
							getTreeTblEstadoResultados().setModel(myTreeTableModel);
							anchoColumnasTabla();
							//Opciones para que el árbol salga expandido y no se pueda reordenar
							getTreeTblEstadoResultados().expandAll();
							getTreeTblEstadoResultados().setSortable(false);
						}*/
						
						//-----------------------
						
						while (cuentasIterator.hasNext()) {
							CuentaIf cuentaIf = (CuentaIf) cuentasIterator.next();
							if (!cuentaIf.getCodigo().equals("1") && !cuentaIf.getCodigo().equals("2") && !cuentaIf.getCodigo().equals("3") && !cuentaIf.getCodigo().equals("7")) {
								double saldo = 0D;
								double saldoComparativo = 0D;
								if (cuentasEstadoResultadosMap.get(cuentaIf.getId()) != null)
									saldo = (Double) cuentasEstadoResultadosMap.get(cuentaIf.getId());
								if (cuentasEstadoResultadosComparativoMap.get(cuentaIf.getId()) != null)
									saldoComparativo = (Double) cuentasEstadoResultadosComparativoMap.get(cuentaIf.getId());
								TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(cuentaIf.getTipocuentaId());
								TipoResultadoIf tipoResultado = (TipoResultadoIf) tiposResultadoMap.get(cuentaIf.getTiporesultadoId());
								if ((Utilitarios.redondeoValor(saldo) > 0D || Utilitarios.redondeoValor(saldo) < 0D) && !tipoCuenta.getCodigo().equals("A") && !tipoCuenta.getCodigo().equals("P") && !tipoCuenta.getCodigo().equals("C")) {
									MyRowTreeTableEstadoResultadosComparativo.setTiposCuentaMap(tiposCuentaMap);
									MyRowTreeTableEstadoResultadosComparativo treeTableEstadoResultadosComparativo = null;
									treeTableEstadoResultadosComparativo = new MyRowTreeTableEstadoResultadosComparativo(cuentaIf, "Cuenta", formatoDecimal.format(saldo), false, filtrosBusqueda(), null, null, formatoDecimal.format(saldoComparativo), null, null, "", nivelesVisibles);
									MyRowTreeTableEstadoResultadosComparativo.setCuentasEstadoResultadosTreeTable(cuentasEstadoResultadosTreeTable);
									MyRowTreeTableEstadoResultadosComparativo.setCuentasEstadoResultadosComparativoTreeTable(cuentasEstadoResultadosComparativoTreeTable);
									MyRowTreeTableEstadoResultadosComparativo.setCuentasEstadoResultadosMap(cuentasEstadoResultadosMap);
									MyRowTreeTableEstadoResultadosComparativo.setCuentasEstadoResultadosComparativoMap(cuentasEstadoResultadosComparativoMap);
									myList.add(treeTableEstadoResultadosComparativo);

									if (tipoCuenta.getCodigo().equals("I")) {
										totalIngresos += saldo;
										totalIngresosComparativo += saldoComparativo;
									} else if (tipoCuenta.getCodigo().equals("G")) {
										totalGastos += saldo;
										totalGastosComparativo += saldoComparativo;
									} else if (tipoCuenta.getCodigo().equals("OI")) {
										totalOtrosIngresos += saldo;
										totalOtrosIngresosComparativo += saldoComparativo;
									} else if (tipoCuenta.getCodigo().equals("OO")) {
										totalOtrosEgresos += saldo;
										totalOtrosEgresosComparativo += saldoComparativo;
									}
									
									if (tipoResultado.getCodigo().equals("IN") || tipoResultado.getCodigo().equals("OI")) {
										totalFinal += saldo;
										totalFinalComparativo += saldoComparativo;
									} else {
										totalFinal -= saldo;
										totalFinalComparativo -= saldoComparativo;
									}
									
									porcentaje = (totalFinal / totalIngresos)*100;
									porcentaje = Math.sqrt(porcentaje*porcentaje);
									porcentajeComparativo = (totalFinalComparativo / totalIngresosComparativo)*100;
	 								porcentajeComparativo = Math.sqrt(porcentajeComparativo*porcentajeComparativo);
									if (String.valueOf(porcentaje).equals("NaN")) {
										porcentaje = 0.0;
									}
									if (String.valueOf(porcentajeComparativo).equals("NaN")) {
										porcentajeComparativo = 0.0;
									}
									
									diferenciaPorcentaje = ((saldo - saldoComparativo)/saldoComparativo)*100;
									
									if (tipoCuenta.getDebehaber().equals("H")) {
										saldo = saldo * -1D;
										saldoComparativo = saldoComparativo * -1D;
									}
									
									if (cuentasEstadoResultadosMap.get(cuentaIf.getId()) != null)
										//myList.add(new MyRowTreeTableEstadoResultadosComparativo("<html><b>TOTAL DE " + cuentaIf.getNombre() + ": </b></html>", "Total", "<html><b>" + formatoDecimal.format(saldo) + "</b></html>", true, null, null, null, "<html><b>" + formatoDecimal.format(saldoComparativo) + "</b></html>", null, null, "<html><b>" + formatoDecimal.format(diferenciaPorcentaje)+"%" + "</b></html>", nivelesVisibles));
										myList.add(new MyRowTreeTableEstadoResultadosComparativo("<html><b>TOTAL DE " + cuentaIf.getNombre() + ": </b></html>", "Total", "<html><b>" + formatoDecimal.format(saldo) + "</b></html>", true, null, null, null, "<html><b>" + formatoDecimal.format(saldoComparativo) + "</b></html>", null, null, "", nivelesVisibles));
									else
										//myList.add(new MyRowTreeTableEstadoResultadosComparativo("<html><b>TOTAL DE " + cuentaIf.getNombre() + ": </b></html>", "Total", "<html><b>" + formatoDecimal.format(0.0) + "</b></html>", true, null, null, null, "<html><b>" + formatoDecimal.format(0.0) + "</b></html>", null, null, "<html><b>" + formatoDecimal.format(diferenciaPorcentaje)+"%" + "</b></html>", nivelesVisibles));
										myList.add(new MyRowTreeTableEstadoResultadosComparativo("<html><b>TOTAL DE " + cuentaIf.getNombre() + ": </b></html>", "Total", "<html><b>" + formatoDecimal.format(0.0) + "</b></html>", true, null, null, null, "<html><b>" + formatoDecimal.format(0.0) + "</b></html>", null, null, "", nivelesVisibles));
										
									//myList.add(new MyRowTreeTableEstadoResultadosComparativo(null, "Total", "<html><b>M A R G E N   " + ++contador + " : </b></html>", true, null, "<html><b>" + formatoDecimal.format(totalFinal) + "</b></html>", "<html><b>" + formatoDecimal.format(porcentaje) + "%</b></html>", )));
									myList.add(new MyRowTreeTableEstadoResultadosComparativo(null, "Total", "<html><b>M A R G E N   " + ++contador + " : </b></html>", true, null, formatoDecimal.format(totalFinal), formatoDecimal.format(porcentaje) +"%", "", formatoDecimal.format(totalFinalComparativo), formatoDecimal.format(porcentajeComparativo) +"%", "<html><b>" + formatoDecimal.format(diferenciaPorcentaje)+"%" + "</b></html>", nivelesVisibles));
									//Añado una fila en blanco despues de la familia de cada padre, solo por estetica
									myList.add(new MyRowTreeTableEstadoResultadosComparativo(null, "Total", "", true, null, null, null, null, null, null, null, nivelesVisibles));
								}
							}
						}
						
						//Creo el árbol enviando el modelo
						double utilidad = (totalIngresos - totalGastos + totalOtrosIngresos - totalOtrosEgresos) * -1D;
						double utilidadComparativo = (totalIngresosComparativo - totalGastosComparativo + totalOtrosIngresosComparativo - totalOtrosEgresosComparativo) * -1D;
						myList.add(new MyRowTreeTableEstadoResultadosComparativo("<html><b>UTILIDAD / PERDIDA DEL EJERCICIO: </b></html>", "Total", "<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(utilidad)) + "</b></html>", true, null, null, null, "<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(utilidadComparativo)) + "</b></html>", null, null, "", nivelesVisibles));
						myTreeTableModel = new MyTreeTableModelEstadoResultadoComparativo(myList);
						getTreeTblEstadoResultados().setModel(myTreeTableModel);
						//Opcion para que el árbol salga expandido y no se pueda reordenar
						getTreeTblEstadoResultados().expandAll();
						//Opcion para que no se pueda reordenar el arbol
						getTreeTblEstadoResultados().getTableHeader().setReorderingAllowed(false);
						getTreeTblEstadoResultados().setSortable(false);
						getTreeTblEstadoResultados().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						//Seteo el ancho del treetable
						setAnchoColumnas();
						setUpdateMode();
						setUpdateMode();
					} catch (GenericBusinessException e) {
						e.printStackTrace();
					}
				}				
			}else{
				SpiritAlert.createAlert("Debe al menos existir un Periodo", SpiritAlert.WARNING);
			}
		}else{
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
	
	private List<AsientoDetalleIf> getAsientoDetalleByCuentaIdList(ArrayList asientoDetallesAuxiliarList, Long cuentaId) {
		List<AsientoDetalleIf> asientoDetallesList = new ArrayList<AsientoDetalleIf>();
		Iterator asientoDetallesAuxiliarIterator = asientoDetallesAuxiliarList.iterator();
		
		while(asientoDetallesAuxiliarIterator.hasNext()) {
			Object[] data = (Object[]) asientoDetallesAuxiliarIterator.next();
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) data[1];
			if (asientoDetalle.getCuentaId().compareTo(cuentaId) == 0) {
				asientoDetallesList.add(asientoDetalle);
				asientoDetallesAuxiliarIterator.remove();
			}
		}
		return asientoDetallesList;
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
	
	private void setAnchoColumnas() {
		TableColumn anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(0);
		anchoColumna.setMinWidth(227);
		
		anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(52);
		    
		anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(52);
		    
		anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(25);
		    
		anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(52);
		    
		anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(52);
		    
		anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(25);
		
		anchoColumna = getTreeTblEstadoResultados().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(38);
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
		
		if (getCmbComparativo().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el periodo contable comparativo", SpiritAlert.INFORMATION);
			getCmbComparativo().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public void report() {
		if (getMode() == SpiritMode.UPDATE_MODE) {
			DefaultTableModel tableModel = treeTableModelToDefaultTableModel();
			
			if (tableModel.getRowCount() > 0) {
				
				try {
					String fileName = "jaspers/contabilidad/RPEstadoResultadosComparativo.jasper";
					HashMap parametrosMap = new HashMap();
					//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("ESTADO DE RESULTADOS COMPARATIVO").iterator().next();
					MenuIf menu = null;
					Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre("ESTADO DE RESULTADOS COMPARATIVO").iterator();
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
					parametrosMap.put("fechaInicialComparativo", new java.sql.Date(getCmbFechaInicioComparativo().getDate().getTime()).toString());
					parametrosMap.put("fechaFinalComparativo", new java.sql.Date(getCmbFechaFinComparativo().getDate().getTime()).toString());
					parametrosMap.put("fechaInicial", new java.sql.Date(getCmbFechaInicioPeriodo().getDate().getTime()).toString());
					parametrosMap.put("fechaFinal", new java.sql.Date(getCmbFechaFinPeriodo().getDate().getTime()).toString());
					ReportModelImpl.processReport(fileName, parametrosMap, tableModel, true);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
			}
		}
	}
	
	public void refresh() {
		cargarComboPlanCuenta();
		cargarComboPeriodo();
		cargarComboComparativo();
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
		DefaultTableModel tableModel = (DefaultTableModel) getTblEstadoResultados().getModel();
		
		try {
			for (int i = 0; i < rows; i++) {
				Vector<String> fila = new Vector<String>();
				String sangria = "";
				
				for (int j = 0; j < cols; j++) {
					if (getTreeTblEstadoResultados().getModel().getValueAt(i,j) != null) {
						String data = getTreeTblEstadoResultados().getModel().getValueAt(i,j).toString().trim();
						int indexOfCierreCorchete = 0;
						String codigoCuenta = "";
						if (data.contains("]")) {
							indexOfCierreCorchete = data.indexOf("]");
							codigoCuenta = data.substring(1, indexOfCierreCorchete);
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
									sangria = sangria.concat("     ");
							}
						}
						
						data = data.replaceAll("<html><b>", "").replaceAll("</b></html>","");
						if (j == 0) {
							if (data.length() >= 43)
								data = data.substring(0, 43);
							fila.add(sangria.concat(data));
						} else if (j >= 1)
							fila.add(data);
					} else
						fila.add("");
				}
			
				tableModel.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		System.out.println("DefaultTableModel creado satisfactoriamente");
		
		return tableModel;
	}
	
	public void cleanTable() {

		DefaultTableModel model = (DefaultTableModel) this.getTblEstadoResultados().getModel();

		for (int i = this.getTblEstadoResultados().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}
	
	public void cleanTreeTable() {
		MyTreeTableModelEstadoResultadoComparativo model = myTreeTableModel;	
		
		if (model != null) {
			for (int i = model.getRowCount(); i > 0; --i)
				model.removeRow(i - 1);
		}
	}
	
	private Map filtrosBusqueda() {
		Map aMap = new HashMap();
		
		aMap.put("idPeriodo", idPeriodo);
		aMap.put("idPlanCuenta", idPlanCuenta);
		aMap.put("idPeriodoC", idComparativo);
		
		return aMap;
	}
}