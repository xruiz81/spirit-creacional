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
import com.spirit.contabilidad.entity.PeriodoDetalleIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.SaldoCuentaIf;
import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.contabilidad.entity.TipoResultadoIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.panel.JPBalanceGeneral;
import com.spirit.contabilidad.util.MyRowTreeTableBalance;
import com.spirit.contabilidad.util.MyTreeTableModelBalance;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

//Clase principal para presentar el Balance General
public class BalanceGeneralModel extends JPBalanceGeneral {

	private static final long serialVersionUID = 5051512376009020561L;
	//Variables principales
	private PlanCuentaIf planCuenta;
	private static PeriodoIf periodo;
	private static Date fechaElegida;
	private static Date fechaElegidaTemp;
	private static Date fechaFinPeriodo;
	private Long idPlanCuenta;
	private Long idPeriodo;
	private MyTreeTableModelBalance myTreeTableModel;
	public static ArrayList filasPadre = new ArrayList();
	public Date fechaHoy = new Date();
	Vector<CuentaIf> cuentasBalanceTreeTable = new Vector<CuentaIf>();
	private Map cuentasMap = new HashMap();
	private Map tiposCuentaMap = new HashMap();
	private Map tiposResultadoMap = new HashMap();
	private DecimalFormat formatoSerialMes = new DecimalFormat("00");
	private final static String NOMBRE_MENU_BALANCEGENERAL = "BALANCE";
	private final static String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private final static String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	public BalanceGeneralModel(){
		this.showSaveMode();
		initListeners();
		cargarCombos();
		new HotKeyComponent(this);
		getBtnConsultar().addKeyListener(oKeyAdapterBtnConsultar);
		
		getCmbNivelesVisibles().setNextFocusableComponent(getBtnConsultar());
	}
	
	public void clean() {
		// TODO Auto-generated method stub
		
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
	
	
	public void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblBalance().getModel();
		
		for(int i= this.getTblBalance().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void cleanTreeTable() {
		MyTreeTableModelBalance model = myTreeTableModel;	
		
		if (model != null)
			for (int i = model.getRowCount(); i > 0; --i)
				model.removeRow(i - 1);
	}
	
	public void showSaveMode() {
		setSaveMode();
		initKeyListeners();
		cleanTable();
		cleanTreeTable();
		getCmbPlanCuenta().grabFocus();
	}
	
	private void initKeyListeners() {
		getCmbFechaCorte().setLocale(Utilitarios.esLocale);
		getCmbFechaCorte().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaCorte().setShowNoneButton(false);
		getCmbFechaCorte().setEditable(false);
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
					//Seteo la fecha de inicio segun el la fecha de inicio del periodo contable
					Calendar calendarInicio = new GregorianCalendar();
					//Si el periodo elegido es el de este año y abarca el presente mes, entonces se carga directamente en el combo la fecha actual
					//caso contrario, se carga el primer dia del periodo elegido
					if(periodo.getFechaini().getYear() == fechaHoy.getYear() && fechaHoy.getMonth() <= periodo.getFechafin().getMonth())
						calendarInicio.setTime(fechaHoy);
					else
						calendarInicio.setTime(periodo.getFechaini());
					
					fechaElegida = periodo.getFechaini();
					fechaElegidaTemp = periodo.getFechaini();
					fechaFinPeriodo = periodo.getFechafin();
					getCmbFechaCorte().setCalendar(calendarInicio);
				}else{
					SpiritAlert.createAlert("Debe al menos existir un Periodo", SpiritAlert.WARNING);
				}
			}
		});
		
		//Veo si la fecha está dentro del período contable
		getCmbFechaCorte().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				fechaElegida = (Date) ((DateComboBox) evento.getSource()).getDate();
				if (fechaElegida.before(periodo.getFechaini()) || fechaElegida.after(periodo.getFechafin())) {
					SpiritAlert.createAlert("Por favor seleccione una fecha dentro del periodo contable!", SpiritAlert.INFORMATION);
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(fechaElegidaTemp);
					fechaElegida = fechaElegidaTemp;
					getCmbFechaCorte().setCalendar(calendarInicio);
				} else if(fechaElegida.after(fechaFinPeriodo)) {
					SpiritAlert.createAlert("La fecha no puede estar fuera del Periodo Contable elegido!", SpiritAlert.INFORMATION);
					Calendar calendarInicio = new GregorianCalendar();
					calendarInicio.setTime(fechaElegidaTemp);
					fechaElegida = fechaElegidaTemp;
					getCmbFechaCorte().setCalendar(calendarInicio);
				} else if(!fechaElegida.equals(fechaElegidaTemp)) {
					fechaElegidaTemp = fechaElegida;
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
			try {				
				if (getTreeTblBalance().getModel().getRowCount() > 0) {
					String si = "Si"; 
	    	        String no = "No"; 
	    	        Object[] options ={si,no}; 
	    			int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir el Balance?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if (opcion == JOptionPane.YES_OPTION) {
						DefaultTableModel tblModelReporte = treeTableModelToDefaultTableModel();
						//String fileName = getClass().getResource("/RPBalanceGeneral.jasper").toString().substring(10);
						String fileName = "jaspers/contabilidad/RPBalanceGeneral.jasper";
						HashMap parametrosMap = new HashMap();
						//MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_BALANCEGENERAL).iterator().next();
						
						MenuIf menu = null;
						Iterator menuIT= SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_BALANCEGENERAL).iterator();
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
						parametrosMap.put("fecha",  Utilitarios.getStringDateFromDate(fechaElegida));
						ReportModelImpl.processReport(fileName, parametrosMap, tblModelReporte, true);
					}
				} else
					SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
		/*File archivo = new File("pruebando.txt");
		try {
			FileOutputStream fis = new FileOutputStream(archivo);
			try {
				fis.write(165);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void refresh() {
		cargarComboPlanCuenta();
		cargarComboPeriodo();
		cargarComboNivelesVisibles();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	/*private DefaultTableModel treeTableModelToDefaultTableModel() {
		int rows = getTreeTblBalance().getModel().getRowCount();
		int cols = getTreeTblBalance().getModel().getColumnCount();
		cleanTable();
		DefaultTableModel tableModel = (DefaultTableModel) getTblBalance().getModel();
		try {
			for (int i = 0; i < rows; i++) {
				Vector<String> fila = new Vector<String>();
				String sangriaCuenta = "";
				String sangriaSaldo = "";
				for (int j = 0; j < cols; j++) {
					if (getTreeTblBalance().getModel().getValueAt(i,j) != null) {
						String data = getTreeTblBalance().getModel().getValueAt(i,j).toString().trim();
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
							Collection cuentaCollection = CuentaModel.getCuentaSessionService().findCuentaByCodigo(codigoCuenta);
							
							if (cuentaCollection.size() > 0) {
								CuentaIf cuenta = (CuentaIf) cuentaCollection.iterator().next();
								int nivel = cuenta.getNivel() - NIVEL_MINIMO;
								for (int k = 0; k < (6-nivel); k++) {
									sangriaCuenta = sangriaCuenta.concat("    "); //4
								}
								for (int k = 0; k < nivel; k++) {
									sangriaSaldo = sangriaSaldo.concat("                      "); //22 																		
								}
							}
						}
						
						if (j == 0){
							//fila.add(sangriaCuenta.concat(data));
							if (data.contains("]")) {
								fila.add(codigoCuenta.concat(sangriaCuenta).concat(nombreCuenta));
							}else{
								fila.add(data);
							}
						}else if (j == 1){
							fila.add(data.concat(sangriaSaldo));
						}
					} else
						fila.add("");
				}
			
				tableModel.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		//tableModel.removeRow(rows-1);
		return tableModel;
	}*/
	
	private DefaultTableModel treeTableModelToDefaultTableModel() {
		int rows = getTreeTblBalance().getModel().getRowCount();
		int cols = getTreeTblBalance().getModel().getColumnCount();
		
		DefaultTableModel tableModelReporte = new DefaultTableModel(rows,3);
		tableModelReporte.addColumn("Cuenta");
		tableModelReporte.addColumn("NombreCuenta");
		tableModelReporte.addColumn("Valor");
		for(int i= rows;i>0;--i)
			tableModelReporte.removeRow(i-1);
		
		try {
			for (int i = 0; i < rows; i++) {
				Vector<String> fila = new Vector<String>();
				String sangriaCuenta = "";
				String sangriaSaldo = "";
				for (int j = 0; j < cols; j++) {
					if (getTreeTblBalance().getModel().getValueAt(i,j) != null) {
						String data = getTreeTblBalance().getModel().getValueAt(i,j).toString().trim();
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
								for (int k = 0; k < nivel; k++) {
									sangriaSaldo = sangriaSaldo.concat("                      "); //22 																		
								}
							}
						}
						
						if (j == 0){
							if (data.contains("]")) {
								fila.add(codigoCuenta);
								fila.add(nombreCuenta);
							}else{
								fila.add("");
								fila.add(data);								
							}
						}else if (j == 1){
							fila.add(data.concat(sangriaSaldo));
						}
					} else
						fila.add("");
				}
			
				tableModelReporte.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		return tableModelReporte;
	}
	
	Comparator<CuentaIf> ordenadorCuentasPorCodigo = new Comparator<CuentaIf>(){
		public int compare(CuentaIf o1, CuentaIf o2) {
			return o1.getCodigo().compareTo(o2.getCodigo());
		}		
	};
	
	//Función que construye el treetable con todos sus resultados!!!
	private void mostrarDetallesSaldoInicialFinal() {
		if (planCuenta != null){
			idPlanCuenta = planCuenta.getId();
			
			if (periodo != null){
				idPeriodo = periodo.getId();
				
				LinkedHashMap cuentasBalanceMap = new LinkedHashMap();
				cuentasBalanceTreeTable.clear();
				if (validateFields()) {
					try {
						double totalActivo = 0D;
						double totalPasivo = 0D;
						double totalPatrimonio = 0D;
						double totalIngresos = 0D;
						double totalEgresos = 0D;
						int nivelesVisibles = Integer.parseInt(getCmbNivelesVisibles().getSelectedItem().toString());
						idPlanCuenta = planCuenta.getId();
						idPeriodo = periodo.getId();
						Long idEmpresa = Parametros.getIdEmpresa();
						Double totalResultadoSuma1 = 0D;
						//Seteo la fecha de inicio (igual al inicio del periodo elegido) y la fecha fin (igual a la fecha elegida)
						//java.sql.Date fechaInicioMovimiento = periodo.getFechaini();
						java.sql.Date fechaInicioMovimiento = new java.sql.Date(fechaElegida.getYear(),fechaElegida.getMonth(),1);
						//java.sql.Date fechaInicioMovimiento = new java.sql.Date(fechaElegida.getYear(),0,1);
						java.sql.Date fechaFinMovimiento = new java.sql.Date(fechaElegida.getYear(),fechaElegida.getMonth(),fechaElegida.getDate());
						Collection cuentasColeccion = SessionServiceLocator.getCuentaSessionService().findCuentaByPlancuentaId(planCuenta.getId());
						Collections.sort((ArrayList)cuentasColeccion,ordenadorCuentasPorCodigo);
						cuentasMap = mapearPlanCuentas(cuentasColeccion);
						Collection tiposCuentaColeccion = SessionServiceLocator.getTipoCuentaSessionService().getTipoCuentaList();
						tiposCuentaMap = mapearTiposCuenta(tiposCuentaColeccion);
						Collection tiposResultadoColeccion = SessionServiceLocator.getTipoResultadoSessionService().getTipoResultadoList();
						tiposResultadoMap = mapearTiposResultado(tiposResultadoColeccion);
						Collection cuentasBalanceColeccion = SessionServiceLocator.getCuentaSessionService().findCuentasForBalanceGeneral(planCuenta.getId(), periodo.getId(), fechaFinMovimiento);
						Collection cuentasAdicionalesBalanceColeccion = SessionServiceLocator.getCuentaSessionService().findCuentasAdicionalesForBalanceGeneral(planCuenta.getId(), periodo.getId(), fechaFinMovimiento);
						Vector<CuentaIf> cuentasBalanceVector = generarVectorCuentasBalance(cuentasBalanceColeccion);
						cuentasBalanceVector = agregarVectorCuentasAdicionalesBalance(cuentasBalanceVector, cuentasAdicionalesBalanceColeccion);
						ArrayList asientoDetalleColeccion = (ArrayList) SessionServiceLocator.getAsientoDetalleSessionService().findAsientoDetalleByEmpresaByPeriodoIdByPlanCuentaIdByStatusAndByFechaInicioAndFechaFin(idEmpresa, idPeriodo, idPlanCuenta, ESTADO_ACTIVO, fechaInicioMovimiento, fechaFinMovimiento, true);
						PeriodoIf periodoAnterior = obtenerPeriodoAnterior(fechaFinMovimiento);
						List<SaldoCuentaIf> saldosCuentasList = (periodoAnterior != null)?obtenerSaldosCuentasPeriodoDetalleAnteriorList(periodoAnterior, fechaFinMovimiento):new ArrayList<SaldoCuentaIf>();
						String year = String.valueOf(fechaFinMovimiento.getYear() + 1900);
						String month = formatoSerialMes.format(fechaFinMovimiento.getMonth() + 1);
						CuentaIf cuentaUtilidadEjercicioAnterior = null;
						Map parameterMap = new HashMap();
						parameterMap.put("codigo", "UTILIDAD");
						parameterMap.put("empresaId", Parametros.getIdEmpresa());
						Iterator it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
						if (it.hasNext()) {
							ParametroEmpresaIf parametroEmpresa = (ParametroEmpresaIf) it.next();
							parameterMap.clear();
							parameterMap.put("codigo", parametroEmpresa.getValor());
							Iterator cuentaIt = SessionServiceLocator.getCuentaSessionService().findCuentaByEmpresaIdAndQuery(Parametros.getIdEmpresa(), parameterMap).iterator();
							if (cuentaIt.hasNext()) {
								cuentaUtilidadEjercicioAnterior = (CuentaIf) cuentaIt.next();
							}
						}
						
						CuentaIf cuentaPerdidaEjercicioAnterior = null;
						parameterMap = new HashMap();
						parameterMap.put("codigo", "PERDIDA");
						parameterMap.put("empresaId", Parametros.getIdEmpresa());
						it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
						if (it.hasNext()) {
							ParametroEmpresaIf parametroEmpresa = (ParametroEmpresaIf) it.next();
							parameterMap.clear();
							parameterMap.put("codigo", parametroEmpresa.getValor());
							Iterator cuentaIt = SessionServiceLocator.getCuentaSessionService().findCuentaByEmpresaIdAndQuery(Parametros.getIdEmpresa(), parameterMap).iterator();
							if (cuentaIt.hasNext()) {
								cuentaPerdidaEjercicioAnterior = (CuentaIf) cuentaIt.next();
							}
						}
						
						Iterator periodoDetalleIterator = SessionServiceLocator.getPeriodoDetalleSessionService().findPeriodoDetalleInicialByPeriodoId(periodo.getId()).iterator();
						PeriodoDetalleIf periodoDetalleInicial = (periodoDetalleIterator.hasNext())?(PeriodoDetalleIf) periodoDetalleIterator.next():null;
						String anioInicial = (periodoDetalleInicial!=null)?periodoDetalleInicial.getAnio():"";
						String mesInicial = (periodoDetalleInicial!=null)?periodoDetalleInicial.getMes():"";
						
						for (int i=0; i<cuentasBalanceVector.size(); i++) {
							CuentaIf cuentaBalanceIf = cuentasBalanceVector.get(i);
							TipoCuentaIf tipoCuentaIf = (TipoCuentaIf) tiposCuentaMap.get(cuentaBalanceIf.getTipocuentaId());
							Double saldoCuenta = 0D;
							if (periodoAnterior != null && (cuentaBalanceIf.getTiporesultadoId() != null && year.equals(anioInicial) && !month.equals(mesInicial)) || cuentaBalanceIf.getTiporesultadoId() == null)
								saldoCuenta = obtenerSaldoCuentaPeriodoDetalleAnterior(saldosCuentasList, cuentaBalanceIf);
							//Double saldoCuenta = 0D;
							cuentasBalanceMap.put(cuentaBalanceIf.getId(), saldoCuenta);
							//Iterator asientosDetalleCuentaBalanceIterator = AsientoModel.getAsientoDetalleSessionService().findAsientoDetalleByEmpresaByCuentaIdByPeriodoIdByPlanCuentaIdAndByFechaInicioAndFechaFin(idEmpresa, cuentaBalanceIf.getId(), idPeriodo, idPlanCuenta, fechaInicioMovimiento, fechaFinMovimiento, true).iterator();
							List<AsientoDetalleIf> asientoDetallesList = getAsientoDetalleByCuentaIdList(asientoDetalleColeccion, cuentaBalanceIf.getId());
							Iterator asientosDetalleCuentaBalanceIterator = asientoDetallesList.iterator();
							while (asientosDetalleCuentaBalanceIterator.hasNext()) {
								AsientoDetalleIf asientoDetalleCuentaBalanceIf = (AsientoDetalleIf) asientosDetalleCuentaBalanceIterator.next();
								//TipoCuentaIf tipoCuentaIf = getTipoCuentaSessionService().getTipoCuenta(cuentaBalanceIf.getTipocuentaId());
								//TipoCuentaIf tipoCuentaIf = (TipoCuentaIf) tiposCuentaMap.get(cuentaBalanceIf.getTipocuentaId());
								String tipoCuentaSegunAsiento = determinarTipoCuentaSegunAsiento(asientoDetalleCuentaBalanceIf);
								saldoCuenta = (Double) cuentasBalanceMap.get(cuentaBalanceIf.getId());
								saldoCuenta = calcularSaldoCuenta(asientoDetalleCuentaBalanceIf, tipoCuentaIf, tipoCuentaSegunAsiento, saldoCuenta);
								cuentasBalanceMap.put(cuentaBalanceIf.getId(), saldoCuenta);
							}
							
							/*if (cuentaBalanceIf.getCodigo().equals(cuentaUtilidadEjercicioAnterior.getCodigo()) || cuentaBalanceIf.getCodigo().equals(cuentaPerdidaEjercicioAnterior.getCodigo())) {
								parameterMap = new HashMap();
								parameterMap.put("cuentaId", cuentaBalanceIf.getId());
								parameterMap.put("periodoId", periodo.getId());
								parameterMap.put("anio", year);
								parameterMap.put("mes", month);
								Iterator saldoCuentaIterator = SessionServiceLocator.getSaldoCuentaSessionService().findSaldoCuentaByQuery(parameterMap).iterator();
								if (saldoCuentaIterator.hasNext()) {
									SaldoCuentaIf saldoCuentaIf = (SaldoCuentaIf) saldoCuentaIterator.next();
									saldoCuenta = saldoCuentaIf.getValor().doubleValue();
									cuentasBalanceMap.put(cuentaBalanceIf.getId(), saldoCuenta);
								}
							}*/
						}
						
						for (int i=0; i<cuentasBalanceVector.size(); i++) {
							CuentaIf cuentaBalanceIf = cuentasBalanceVector.get(i);
							calcularSaldoCuentaPadre(cuentasBalanceMap, cuentaBalanceIf, 0D);
						}
						
						//Iterator cuentasIterator = CuentaModel.getCuentaSessionService().findCuentaByPlancuentaId(planCuenta.getId()).iterator();
						Iterator cuentasIterator = cuentasColeccion.iterator();
						while (cuentasIterator.hasNext()) {
							CuentaIf cuentaIf = (CuentaIf) cuentasIterator.next();
							if (cuentasBalanceMap.get(cuentaIf.getId()) != null) {
								cuentasBalanceTreeTable.add(cuentaIf);
								//System.out.println("Cuenta: " + cuentaIf.getNombre() + " Saldo: " + cuentasBalanceMap.get(cuentaIf.getId()));
							}
						}
						
						parameterMap = new HashMap();
						parameterMap.put("plancuentaId", planCuenta.getId());
						parameterMap.put("nivel", 1);
						cuentasIterator = SessionServiceLocator.getCuentaSessionService().findCuentaByQuery(parameterMap).iterator();
						//cuentasIterator = CuentaModel.getCuentaSessionService().findCuentaByQueryForBalance(parameterMap).iterator();
						//Array que contendra la lista de filas que componen el treetable
						ArrayList myList = new ArrayList();
						while (cuentasIterator.hasNext()) {
							CuentaIf cuentaIf = (CuentaIf) cuentasIterator.next();
							double saldo = 0D;
							if (cuentasBalanceMap.get(cuentaIf.getId()) != null)
								saldo = (Double) cuentasBalanceMap.get(cuentaIf.getId());
							TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(cuentaIf.getTipocuentaId());
							if (tipoCuenta.getCodigo().equals("A"))
								totalActivo += saldo;
							else if (tipoCuenta.getCodigo().equals("P"))
								totalPasivo += saldo;
							else if (tipoCuenta.getCodigo().equals("C"))
								totalPatrimonio += saldo;
							else if (tipoCuenta.getCodigo().equals("I") || tipoCuenta.getCodigo().equals("OI"))
								totalIngresos += saldo;
							else if (tipoCuenta.getCodigo().equals("G") || tipoCuenta.getCodigo().equals("OO"))
								totalEgresos += saldo;
							if ((Utilitarios.redondeoValor(saldo) > 0D || Utilitarios.redondeoValor(saldo) < 0D) && (tipoCuenta.getCodigo().equals("A") || tipoCuenta.getCodigo().equals("P") || tipoCuenta.getCodigo().equals("C"))) {
								MyRowTreeTableBalance.setTiposCuentaMap(tiposCuentaMap);
								MyRowTreeTableBalance treetableBalance = new MyRowTreeTableBalance(cuentaIf, "Cuenta", formatoDecimal.format(saldo), nivelesVisibles, false, filtrosBusqueda());
								//Envio las fechas escogidas a MyRowTreeTableBalance			
								MyRowTreeTableBalance.setCuentasBalanceTreeTable(cuentasBalanceTreeTable);
								MyRowTreeTableBalance.setCuentasBalanceMap(cuentasBalanceMap);
								//Añado al array cada una de las filas padre, en MyrowTreeTableBalance se setean todos sus hijos
								myList.add(treetableBalance);
								//Seteo el total de cada Padre
								//Si el padre es Utilidades Retenidas uso el valor totalResultadoSuma, caso contrario uso totalResultadoSuma1
								if (tipoCuenta.getDebehaber().equals("H"))
									saldo = saldo * -1D;
								if (tipoCuenta.getCodigo().equals("U"))
									myList.add(new MyRowTreeTableBalance("TOTAL DE " + cuentaIf.getNombre() + ": ", "Total", formatoDecimal.format(totalResultadoSuma1), nivelesVisibles, true, null));
								else if (cuentasBalanceMap.get(cuentaIf.getId()) != null)
									myList.add(new MyRowTreeTableBalance("TOTAL DE " + cuentaIf.getNombre() + ": ", "Total", formatoDecimal.format(saldo), nivelesVisibles, true, null));
								else
									myList.add(new MyRowTreeTableBalance("TOTAL DE " + cuentaIf.getNombre() + ": ", "Total", formatoDecimal.format(0.0), nivelesVisibles, true, null));
								//Añado una fila en blanco despues de la familia de cada padre, solo por estetica
								myList.add(new MyRowTreeTableBalance(null, "Total", null, nivelesVisibles, true, null));
								//vuelvo a setear la variable que contiene el total de los padres en cero
								
							}
						}

						double utilidad = totalActivo - totalPasivo - totalPatrimonio;
						//double utilidad = totalIngresos - totalEgresos;
						String mensaje = " DEL EJERCICIO";
						if (Utilitarios.redondeoValor(utilidad) < 0D)
							mensaje = "PERDIDA" + mensaje;
						else
							mensaje = "UTILIDAD" + mensaje;
						
						if (Utilitarios.redondeoValor(utilidad) != 0D)
							myList.add(new MyRowTreeTableBalance(mensaje + ": ", "Total", formatoDecimal.format(Utilitarios.redondeoValor(utilidad)), nivelesVisibles, true, null));
						//Creo el modelo del árbol.
			
						double pasivo =totalPasivo;
						double patrimonio =totalPatrimonio;						
						double suma=totalPasivo+totalPatrimonio+utilidad;
						
						myList.add(new MyRowTreeTableBalance(null, "Total", null, nivelesVisibles, true, null));
						myList.add(new MyRowTreeTableBalance("TOTAL DE PASIVO Y PATRIMONIO: ", "Total", formatoDecimal.format(suma), nivelesVisibles, true, null));
						myTreeTableModel = new MyTreeTableModelBalance(myList);
						//Creo el árbol enviando el modelo
						getTreeTblBalance().setModel(myTreeTableModel);
						//Seteo el ancho del treetable
						getTreeTblBalance().getColumnModel().getColumn(0).setPreferredWidth(400);
						//Opcion para que el árbol salga expandido y no se pueda reordenar
						getTreeTblBalance().expandAll();
						//Opcion para que no se pueda reordenar el arbol
						getTreeTblBalance().setSortable(false);
						getTreeTblBalance().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						
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
	
	private List<AsientoDetalleIf> getAsientoDetalleByCuentaIdList(ArrayList asientoDetallesAuxiliarColeccion, Long cuentaId) {
		List<AsientoDetalleIf> asientoDetallesList = new ArrayList<AsientoDetalleIf>();
		Iterator asientoDetallesAuxiliarIterator = asientoDetallesAuxiliarColeccion.iterator();
		
		while(asientoDetallesAuxiliarIterator.hasNext()) {
			Object[] data = (Object[]) asientoDetallesAuxiliarIterator.next();
			AsientoIf asiento = (AsientoIf) data[0];
			AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) data[1];
			if (asientoDetalle.getCuentaId().compareTo(cuentaId) == 0  && (asiento.getAsientoCierre() == null || !asiento.getAsientoCierre().equals("S"))) {
				asientoDetallesList.add(asientoDetalle);
				asientoDetallesAuxiliarIterator.remove();
			}
		}
		return asientoDetallesList;
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
	
	private void calcularSaldoCuentaPadre(LinkedHashMap cuentasBalanceMap, CuentaIf cuentaBalanceIf, double saldoAnteriorCuentaHija) {
		if (cuentaBalanceIf.getPadreId() != null) {
			CuentaIf cuentaPadre = (CuentaIf) cuentasMap.get(cuentaBalanceIf.getPadreId());
			double saldoCuentaPadre = 0D;
			double saldoCuentaHija = (Double) cuentasBalanceMap.get(cuentaBalanceIf.getId());
			if (cuentaPadre != null) {
				Long tipoResultadoId = cuentaBalanceIf.getTiporesultadoId();
				TipoResultadoIf tipoResultadoIf = null;
				if (tipoResultadoId != null)
					tipoResultadoIf = (TipoResultadoIf) tiposResultadoMap.get(tipoResultadoId);
				
				if (cuentasBalanceMap.get(cuentaPadre.getId()) != null) {
					if (tipoResultadoIf == null || !tipoResultadoIf.getCodigo().equals("OE"))
						saldoCuentaPadre = (Double) cuentasBalanceMap.get(cuentaPadre.getId()) + saldoCuentaHija - saldoAnteriorCuentaHija;
					else
						saldoCuentaPadre = (Double) cuentasBalanceMap.get(cuentaPadre.getId()) - saldoCuentaHija + saldoAnteriorCuentaHija;

					saldoAnteriorCuentaHija = (Double) cuentasBalanceMap.get(cuentaPadre.getId());
				} else {
					if (tipoResultadoIf == null || !tipoResultadoIf.getCodigo().equals("OE"))
						saldoCuentaPadre = saldoCuentaHija;
					else
						saldoCuentaPadre = saldoCuentaHija * -1D;
				}
				
				cuentasBalanceMap.put(cuentaPadre.getId(), saldoCuentaPadre);
				if (cuentaPadre.getNivel() != null)
					calcularSaldoCuentaPadre(cuentasBalanceMap, cuentaPadre, saldoAnteriorCuentaHija);
			}
		}
	}
	
	private String determinarTipoCuentaSegunAsiento(AsientoDetalleIf asientoDetalle) {
		BigDecimal zero = new BigDecimal(0.00);
		if (asientoDetalle.getDebe().compareTo(zero) == 1) {
			return "D";
		} else
			return "H";
	}
	
	private Vector<CuentaIf> generarVectorCuentasBalance(Collection cuentasBalanceColeccion) {
		Vector<CuentaIf> cuentasBalanceVector = new Vector<CuentaIf>();
		Iterator cuentasBalanceIterator = cuentasBalanceColeccion.iterator();
		while (cuentasBalanceIterator.hasNext()) {
			CuentaIf cuenta = (CuentaIf) cuentasBalanceIterator.next();
			TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(cuenta.getTipocuentaId());
			//if (tipoCuenta.getCodigo().equals("A") || tipoCuenta.getCodigo().equals("P") || tipoCuenta.getCodigo().equals("C"))
				cuentasBalanceVector.add(cuenta);
		}
		
		return cuentasBalanceVector;
	}
	
	private Vector<CuentaIf> agregarVectorCuentasAdicionalesBalance(Vector<CuentaIf> cuentasBalanceVector, Collection cuentasAdicionalesBalanceColeccion) {
		Iterator cuentasBalanceIterator = cuentasAdicionalesBalanceColeccion.iterator();
		while (cuentasBalanceIterator.hasNext()) {
			CuentaIf cuenta = (CuentaIf) cuentasBalanceIterator.next();
			TipoCuentaIf tipoCuenta = (TipoCuentaIf) tiposCuentaMap.get(cuenta.getTipocuentaId());
			//if (tipoCuenta.getCodigo().equals("A") || tipoCuenta.getCodigo().equals("P") || tipoCuenta.getCodigo().equals("C"))
				cuentasBalanceVector.add(cuenta);
		}
		
		return cuentasBalanceVector;
	}
	
	//Uso un mapa para enviar el Periodo y PlanCuenta escogido a MyRowTreeTable
	private Map filtrosBusqueda() {
		Map aMap = new HashMap();
		aMap.put("idPeriodo", idPeriodo);
		aMap.put("idPlanCuenta", idPlanCuenta);
		return aMap;
	}
}

