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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.SaldoCuentaIf;
import com.spirit.contabilidad.entity.SubtipoAsientoIf;
import com.spirit.contabilidad.entity.TipoAsientoIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.panel.JPEstadoFlujoEfectivo;
import com.spirit.contabilidad.util.MyRowTreeTableFlujoEfectivo;
import com.spirit.contabilidad.util.MyTreeTableModelFlujoEfectivo;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class EstadoFlujoEfectivoModel extends JPEstadoFlujoEfectivo {
	private static final long serialVersionUID = 4231467110059859027L;
	private static PeriodoIf periodo;
	private static Date fechaInicioCalculo;
	private static Date fechaInicioTemp;
	private static Date fechaFinCalculo;
	private static Date fechaFinTemp;
	private DefaultTreeModel modelActividadesDetalle;
	private Vector actividadDetallesColeccion;
	private Long idPlanCuenta;
	private Long idPeriodo;
	private PlanCuentaIf planCuenta;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private MyTreeTableModelFlujoEfectivo myTreeTableModel;
	Map tiposAsientoMap = new HashMap();
	Map subtiposAsientoMap = new HashMap();
	
	public EstadoFlujoEfectivoModel() {
		initKeyListeners();
		initListeners();
		this.showSaveMode();
		new HotKeyComponent(this);
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
		
		getCmbFechaFin().setNextFocusableComponent(getBtnConsultar());
		
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
		getTxtSaldoInicial().setEditable(false);
		getTxtSaldoFinal().setEditable(false);
		cargarComboPlanCuenta();
		cargarComboPeriodo();
		cleanTable();
		cleanTreeTable();		
		getCmbPlanCuenta().grabFocus();
	}
	
	private void cargarComboPlanCuenta(){
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

	private void initListeners(){
		getCmbPlanCuenta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getModel().getSelectedItem();
			}
		});				
		
		getCmbPeriodo().addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent evento) {
				periodo = (PeriodoIf) getCmbPeriodo().getModel().getSelectedItem();
				if(periodo != null){
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(periodo.getFechaini());
					fechaInicioCalculo = periodo.getFechaini();
					fechaInicioTemp = periodo.getFechaini();
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
		
		getCmbFechaInicio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				fechaInicioCalculo = (Date) ((DateComboBox) evento.getSource()).getDate();
				clean();
				if(fechaInicioCalculo.before(periodo.getFechaini()) || fechaInicioCalculo.after(periodo.getFechafin())) {
					SpiritAlert.createAlert("Por favor seleccione una fecha dentro del periodo contable!", SpiritAlert.INFORMATION);
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(fechaInicioTemp);
					fechaInicioCalculo = fechaInicioTemp;
					getCmbFechaInicio().setCalendar(calendarInicio);
				} else if(fechaInicioCalculo.after(fechaFinCalculo)) {
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
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(SpiritCursor.hourglassCursor);
				mostrarDetallesSaldoInicialFinal();
				setCursor(SpiritCursor.normalCursor);
			}
		});
	}
	
	private void mostrarDetallesSaldoInicialFinal() {
		if (planCuenta != null){
			idPlanCuenta = planCuenta.getId();			
			if (periodo != null){
				idPeriodo = periodo.getId();
				if (validateFields()) {
					try {												
						Long idEmpresa = Parametros.getIdEmpresa();
						Double saldoMesAnterior = 0D;
						Double totalDebe = 0D;
						Double totalHaber = 0D;
						Double totalDebeActividad = 0D;
						Double totalHaberActividad = 0D;
						Double totalActividad = 0D;
						actividadDetallesColeccion = new Vector();
						int diaFIC = fechaInicioCalculo.getDate();
						int mesFIC = fechaInicioCalculo.getMonth()+1;
						int añoFIC = fechaInicioCalculo.getYear()+1900;
						int mesSaldoAnterior = 0;
						int diasMovimiento = 0;
						int diaFFC = fechaFinCalculo.getDate();
						int mesFFC = fechaFinCalculo.getMonth()+1;
						int añoFFC = fechaFinCalculo.getYear()+1900;
						if(mesFIC>0){
							if(mesFIC==1)
								mesSaldoAnterior = 12;
							else
								mesSaldoAnterior = mesFIC - 1;
							diasMovimiento = diaFIC;
						}
												
						Collection saldosCuenta = SessionServiceLocator.getSaldoCuentaSessionService().findSaldoCuentaByPlanCuentaIdByCuentaEfectivoImputableAndByPeriodoId(idEmpresa, idPlanCuenta, idPeriodo);
						Iterator itSaldosCuenta = saldosCuenta.iterator();
						while (itSaldosCuenta.hasNext()){
							SaldoCuentaIf saldoCuentaIf = (SaldoCuentaIf) itSaldosCuenta.next(); 
							if(String.valueOf(mesSaldoAnterior).equals(saldoCuentaIf.getMes()) && String.valueOf(añoFIC).equals(saldoCuentaIf.getAnio())){
								saldoMesAnterior = saldoCuentaIf.getValor().doubleValue();
							}
						}
						java.sql.Date fechaInicioMesMovimiento = new java.sql.Date(fechaInicioCalculo.getYear(),fechaInicioCalculo.getMonth(),1);
						java.sql.Date fechaFinMesMovimiento = new java.sql.Date(fechaInicioCalculo.getYear(),fechaInicioCalculo.getMonth(),fechaInicioCalculo.getDate()-1);
						Map parameterMap = new HashMap();
						parameterMap.put("efectivo", "S");
						parameterMap.put("periodoId", idPeriodo);
						parameterMap.put("plancuentaId", idPlanCuenta);
						parameterMap.put("status", "A");
						Collection<Object[]> asientoDetallesColeccion = SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByEmpresaIdAndByCuentaEfectivoImputable_DebeHaberJuntos(parameterMap, fechaInicioMesMovimiento,fechaFinMesMovimiento,Parametros.getIdEmpresa());
						Iterator itAsientoDetalles = asientoDetallesColeccion.iterator();
						while (itAsientoDetalles.hasNext()){
							Object[] datos = (Object[]) itAsientoDetalles.next();
							if(datos!=null){
								if(datos[1]!=null) {
									BigDecimal valorDebe = (BigDecimal) datos[1];
									totalDebe = totalDebe + valorDebe.doubleValue();											
								}
								if(datos[2]!=null) {
									BigDecimal valorHaber = (BigDecimal) datos[2];
									totalHaber = totalHaber + valorHaber.doubleValue();										
								}
							}
						}
						 
						Double saldoInicial = saldoMesAnterior + totalDebe - totalHaber;
						getTxtSaldoInicial().setText(formatoDecimal.format(saldoInicial));
						totalDebe = 0D;
						totalHaber = 0D;
						java.sql.Date fechaInicioMovimiento = new java.sql.Date(fechaInicioCalculo.getYear(),fechaInicioCalculo.getMonth(),fechaInicioCalculo.getDate());
						java.sql.Date fechaFinMovimiento = new java.sql.Date(fechaFinCalculo.getYear(),fechaFinCalculo.getMonth(),fechaFinCalculo.getDate());
						tiposAsientoMap = mapearTiposAsiento();
						subtiposAsientoMap = mapearSubtiposAsiento();
						ArrayList myList = new ArrayList();
						Iterator itTipoAsiento = tiposAsientoMap.keySet().iterator();
						
						while (itTipoAsiento.hasNext()) {	
							TipoAsientoIf tipoAsientoIf = (TipoAsientoIf) tiposAsientoMap.get((Long) itTipoAsiento.next());
							totalDebeActividad = 0D;
							totalHaberActividad = 0D;
							totalActividad = 0D;
							Collection<Object[]> asientoDetallesColeccion2 = SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByEmpresaIdAndByCuentaEfectivoImputable_DebeHaberJuntosTipo(parameterMap, fechaInicioMovimiento,fechaFinMovimiento,Parametros.getIdEmpresa(),tipoAsientoIf.getId());
							Iterator itAsientoDetalles2 = asientoDetallesColeccion2.iterator();
							while (itAsientoDetalles2.hasNext()){
								Object[] datos = (Object[]) itAsientoDetalles2.next();																
								if(datos!=null){
									if(datos[1]!=null) {
										BigDecimal valorDebe = (BigDecimal) datos[1];
										totalDebeActividad = totalDebeActividad + valorDebe.doubleValue();											
									}
									if(datos[2]!=null) {
										BigDecimal valorHaber = (BigDecimal) datos[2];
										totalHaberActividad = totalHaberActividad + valorHaber.doubleValue();										
									}
								}
								totalActividad = totalDebeActividad - totalHaberActividad;
							}			
							
							myList.add(new MyRowTreeTableFlujoEfectivo(tipoAsientoIf, "TipoAsiento", null, false, filtrosBusqueda()));
							myList.add(new MyRowTreeTableFlujoEfectivo("TOTAL DE " + tipoAsientoIf.getNombre() + ": ", "Total", formatoDecimal.format(totalActividad), true, null));
							myList.add(new MyRowTreeTableFlujoEfectivo(null, "Total", null, true, null));
							MyRowTreeTableFlujoEfectivo.setFechaInicio(fechaInicioMovimiento);
							MyRowTreeTableFlujoEfectivo.setFechaFin(fechaFinMovimiento);
							MyRowTreeTableFlujoEfectivo.setAsientoDetallesColeccion(asientoDetallesColeccion);
							MyRowTreeTableFlujoEfectivo.setSubtiposAsientoMap(subtiposAsientoMap);
							myTreeTableModel = new MyTreeTableModelFlujoEfectivo(myList);
							getTreeTblEstadoFlujoEfectivo().setModel(myTreeTableModel);
							getTreeTblEstadoFlujoEfectivo().expandAll();
							getTreeTblEstadoFlujoEfectivo().setSortable(false);
						}
						
						Collection<Object[]> asientoDetallesColeccion3 = SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByEmpresaIdAndByCuentaEfectivoImputable_DebeHaberJuntos(parameterMap, fechaInicioMovimiento,fechaFinMovimiento,Parametros.getIdEmpresa());
						Iterator itAsientoDetalles3 = asientoDetallesColeccion3.iterator();
						while (itAsientoDetalles3.hasNext()){
							Object[] datos = (Object[]) itAsientoDetalles3.next();
							if(datos!=null){
								if(datos[1]!=null) {
									BigDecimal valorDebe = (BigDecimal) datos[1];
									totalDebe = totalDebe + valorDebe.doubleValue();											
								}
								if(datos[2]!=null) {
									BigDecimal valorHaber = (BigDecimal) datos[2];
									totalHaber = totalHaber + valorHaber.doubleValue();										
								}
							}
						}

						Double saldoFinal = saldoInicial + totalDebe - totalHaber;
						getTxtSaldoFinal().setText(formatoDecimal.format(saldoFinal));
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
	
	public boolean validateFields() {
		if (getCmbPlanCuenta().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un plan de cuentas", SpiritAlert.INFORMATION);
			getCmbPlanCuenta().grabFocus();
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
			DefaultTableModel tableModel = treeTableModelToDefaultTableModel();			
			if (tableModel.getRowCount() > 0) {
				String si = "Si"; 
    	        String no = "No"; 
    	        Object[] options ={si,no}; 
    			int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir el Estado de Flujo de Efectivo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if(opcion == JOptionPane.YES_OPTION) {
					try {
						String fileName = "jaspers/contabilidad/RPFlujoEfectivo.jasper";
						HashMap parametrosMap = new HashMap();
						MenuIf menu = null;
						Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre("FLUJO DE EFECTIVO").iterator();
						if(menuIT.hasNext()) 
							menu = (MenuIf)menuIT.next();						
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
						parametrosMap.put("fechaInicial", new java.sql.Date(getCmbFechaInicio().getDate().getTime()).toString());
						parametrosMap.put("fechaFinal", new java.sql.Date(getCmbFechaFin().getDate().getTime()).toString());
						parametrosMap.put("saldoInicial", this.getTxtSaldoInicial().getText());
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
		
	private Map mapearTiposAsiento() {
		Map tiposAsientoMap = new HashMap();
		try {
			Collection tiposAsientoColeccion = SessionServiceLocator.getTipoAsientoSessionService().findTipoAsientoByEmpresaId(Parametros.getIdEmpresa());
			Iterator it = tiposAsientoColeccion.iterator();
			while (it.hasNext()) {
				TipoAsientoIf tipoAsiento = (TipoAsientoIf) it.next();
				tiposAsientoMap.put(tipoAsiento.getId(), tipoAsiento);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return tiposAsientoMap;
	}
	
	private Map mapearSubtiposAsiento() {
		Map subtiposAsientoMap = new HashMap();
		try {
			Collection subtiposAsientoColeccion = SessionServiceLocator.getSubTipoAsientoSessionService().findSubtipoAsientoByEmpresaId(Parametros.getIdEmpresa());
			Iterator it = subtiposAsientoColeccion.iterator();
			while (it.hasNext()) {
				SubtipoAsientoIf subtipoAsiento = (SubtipoAsientoIf) it.next();
				subtiposAsientoMap.put(subtipoAsiento.getId(), subtipoAsiento);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return subtiposAsientoMap;
	}
	
	public void refresh() {
		cargarComboPlanCuenta();
		cargarComboPeriodo();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private DefaultTableModel treeTableModelToDefaultTableModel() {
		int rows = getTreeTblEstadoFlujoEfectivo().getModel().getRowCount();
		int cols = getTreeTblEstadoFlujoEfectivo().getModel().getColumnCount();
		cleanTable();
		DefaultTableModel tableModel = (DefaultTableModel) getTblEstadoFlujoEfectivo().getModel();
		try {
			for (int i = 0; i < rows; i++) {
				Vector<String> fila = new Vector<String>();
				for (int j = 0; j < cols; j++) {
					if (getTreeTblEstadoFlujoEfectivo().getModel().getValueAt(i,j) != null) {
						String data = getTreeTblEstadoFlujoEfectivo().getModel().getValueAt(i,j).toString().trim();
						String sangria = "";
						if (j == 0) {
							Collection subtipoAsientoCollection = SessionServiceLocator.getSubTipoAsientoSessionService().findSubtipoAsientoByNombre(data);
							if (subtipoAsientoCollection.size() > 0) {
								SubtipoAsientoIf cuenta = (SubtipoAsientoIf) subtipoAsientoCollection.iterator().next();							
								sangria = sangria.concat("     ");
							}
						}
						fila.add(sangria.concat(data));
					} else
						fila.add("");
				}			
				tableModel.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return tableModel;
	}
	
	public void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) this.getTblEstadoFlujoEfectivo().getModel();
		for (int i = this.getTblEstadoFlujoEfectivo().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}
	
	public void cleanTreeTable() {
		MyTreeTableModelFlujoEfectivo model = myTreeTableModel;	
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