package com.spirit.rrhh.gui.model;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
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
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.DepartamentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.rrhh.entity.EmpleadoVacacionesData;
import com.spirit.rrhh.entity.EmpleadoVacacionesIf;
import com.spirit.rrhh.gui.panel.JPVacaciones;
import com.spirit.util.Archivos;
import com.spirit.util.ExtensionFileFilter;
import com.spirit.util.FileInputStreamSerializable;
import com.spirit.util.LabelAccessory;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class VacacionesModel extends JPVacaciones {
	
	protected EmpleadoIf empleadoIf;	
	private static final int MAX_LONGITUD_OBSERVACION = 300;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no}; 
	private Vector<EmpleadoVacacionesIf> empleadoVacacionesVector = new Vector<EmpleadoVacacionesIf>();
	private Vector<EmpleadoVacacionesIf> empleadoVacacionesEliminadasVector = new Vector<EmpleadoVacacionesIf>();
	private DefaultTableModel modelVacaciones;
	private DefaultTableModel modelVacacionesReportes;
	private DefaultTableModel modelReporteFechas;
	private Vector<File> archivosVacaciones = new Vector<File>();
	private File selectedFile;
	final long MILISEGUNDOS_EN_UN_DIA = 24 * 60 * 60 * 1000;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	final JPopupMenu popupArchivoVacaciones = new JPopupMenu();
	private Map<Long, EmpleadoIf> mapaEmpleado = new HashMap<Long, EmpleadoIf>();
	private Map<Long, OficinaIf> mapaOficina = new HashMap<Long, OficinaIf>();
	private Map<Long, DepartamentoIf> mapaDepartamento = new HashMap<Long, DepartamentoIf>();
	protected EmpleadoVacacionesIf empleadoVacacionesIf;	
	private Double diasDisponibles = 0D;
	private static final String TODOS = "TODOS";
	
	
	public VacacionesModel(){
		cargarComboOficina();
		cargarComboDepartamento();
		anchoColumnasTabla();
		cargarMapas();
		initKeyListeners();
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
	
	public void cargarMapas(){
		try {			
			mapaEmpleado.clear();
			Collection empleados = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Iterator empleadosIt = empleados.iterator();
			while(empleadosIt.hasNext()){
				EmpleadoIf empleadoIf = (EmpleadoIf)empleadosIt.next();
				mapaEmpleado.put(empleadoIf.getId(), empleadoIf);
			}
			
			mapaOficina.clear();
			Collection oficinas = SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator oficinasIt = oficinas.iterator();
			while(oficinasIt.hasNext()){
				OficinaIf oficinaIf = (OficinaIf)oficinasIt.next();
				mapaOficina.put(oficinaIf.getId(), oficinaIf);
			}
			
			mapaDepartamento.clear();
			Collection departamentos = SessionServiceLocator.getDepartamentoSessionService().findDepartamentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator departamentosIt = departamentos.iterator();
			while(departamentosIt.hasNext()){
				DepartamentoIf departamentoIf = (DepartamentoIf)departamentosIt.next();
				mapaDepartamento.put(departamentoIf.getId(), departamentoIf);
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	private void anchoColumnasTabla() {
		setSorterTable(getTblVacacionesReporte());
		setSorterTable(getTblReporteFechas());
		getTblVacaciones().getTableHeader().setReorderingAllowed(false);
		getTblVacacionesReporte().getTableHeader().setReorderingAllowed(false);
		getTblReporteFechas().getTableHeader().setReorderingAllowed(false);
		//getTblVacaciones().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblVacaciones().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblVacaciones().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(120);
		anchoColumna = getTblVacaciones().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblVacaciones().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(65);
		anchoColumna = getTblVacaciones().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblVacaciones().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblVacaciones().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblVacaciones().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(100);
				
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		getTblVacaciones().getColumnModel().getColumn(0).setCellRenderer(tableCellRendererCenter);
		getTblVacaciones().getColumnModel().getColumn(1).setCellRenderer(tableCellRendererCenter);
		getTblVacaciones().getColumnModel().getColumn(2).setCellRenderer(tableCellRendererCenter);
		getTblVacaciones().getColumnModel().getColumn(3).setCellRenderer(tableCellRendererCenter);
		getTblVacaciones().getColumnModel().getColumn(4).setCellRenderer(tableCellRendererCenter);
		getTblVacaciones().getColumnModel().getColumn(5).setCellRenderer(tableCellRendererCenter);
		//TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		//getTblOrdenesClientes().getColumnModel().getColumn(10).setCellRenderer(tableCellRenderer);
				
		//TABLA REPORTES 1
		TableColumn anchoColumnaReportes = getTblVacacionesReporte().getColumnModel().getColumn(0);
		anchoColumnaReportes.setPreferredWidth(150);
		anchoColumnaReportes = getTblVacacionesReporte().getColumnModel().getColumn(1);
		anchoColumnaReportes.setPreferredWidth(100);
		anchoColumnaReportes = getTblVacacionesReporte().getColumnModel().getColumn(2);
		anchoColumnaReportes.setPreferredWidth(50);
		anchoColumnaReportes = getTblVacacionesReporte().getColumnModel().getColumn(3);
		anchoColumnaReportes.setPreferredWidth(100);
		anchoColumnaReportes = getTblVacacionesReporte().getColumnModel().getColumn(4);
		anchoColumnaReportes.setPreferredWidth(60);
		anchoColumnaReportes = getTblVacacionesReporte().getColumnModel().getColumn(5);
		anchoColumnaReportes.setPreferredWidth(60);
		anchoColumnaReportes = getTblVacacionesReporte().getColumnModel().getColumn(6);
		anchoColumnaReportes.setPreferredWidth(60);
				
		getTblVacacionesReporte().getColumnModel().getColumn(1).setCellRenderer(tableCellRendererCenter);
		getTblVacacionesReporte().getColumnModel().getColumn(4).setCellRenderer(tableCellRendererCenter);
		getTblVacacionesReporte().getColumnModel().getColumn(5).setCellRenderer(tableCellRendererCenter);
		getTblVacacionesReporte().getColumnModel().getColumn(6).setCellRenderer(tableCellRendererCenter);
		
		//TABLA REPORTES 2
		TableColumn anchoColumnaReportesFechas = getTblReporteFechas().getColumnModel().getColumn(0);
		anchoColumnaReportesFechas.setPreferredWidth(150);
		anchoColumnaReportesFechas = getTblReporteFechas().getColumnModel().getColumn(1);
		anchoColumnaReportesFechas.setPreferredWidth(60);
		anchoColumnaReportesFechas = getTblReporteFechas().getColumnModel().getColumn(2);
		anchoColumnaReportesFechas.setPreferredWidth(100);
		anchoColumnaReportesFechas = getTblReporteFechas().getColumnModel().getColumn(3);
		anchoColumnaReportesFechas.setPreferredWidth(90);
		anchoColumnaReportesFechas = getTblReporteFechas().getColumnModel().getColumn(4);
		anchoColumnaReportesFechas.setPreferredWidth(90);
		anchoColumnaReportesFechas = getTblReporteFechas().getColumnModel().getColumn(5);
		anchoColumnaReportesFechas.setPreferredWidth(60);
				
		getTblReporteFechas().getColumnModel().getColumn(3).setCellRenderer(tableCellRendererCenter);
		getTblReporteFechas().getColumnModel().getColumn(4).setCellRenderer(tableCellRendererCenter);
		getTblReporteFechas().getColumnModel().getColumn(5).setCellRenderer(tableCellRendererCenter);
	}
	
	public void initKeyListeners(){
		getBtnEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnEmpleado().setToolTipText("Buscar Empleado");						
		getBtnArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/attachFile.png"));
		getBtnArchivo().setToolTipText("Agregar Archivo");
		getBtnVisualizarArchivo().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnVisualizarArchivo().setToolTipText("Visualizar Archivo");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnConsultar().setToolTipText("Consultar");		
		
		getBtnAgregarDetalle().setText("");
		getBtnActualizarDetalle().setText("");
		getBtnEliminarDetalle().setText("");
		getBtnAgregarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarDetalle().setToolTipText("Agregar Detalle");		
		getBtnActualizarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarDetalle().setToolTipText("Actualizar Detalle");		
		getBtnEliminarDetalle().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarDetalle().setToolTipText("Eliminar Detalle");
		
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);	
		
		getTxtObservacion().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACION, true, 0));
		
		getCmbFechaInicioReporte().setLocale(Utilitarios.esLocale);
		getCmbFechaFinReporte().setLocale(Utilitarios.esLocale);
		getCmbFechaInicioReporte().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFinReporte().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicioReporte().setEditable(false);
		getCmbFechaFinReporte().setEditable(false);
		getCmbFechaInicioReporte().setShowNoneButton(false);
		getCmbFechaFinReporte().setShowNoneButton(false);
		getCmbFechaInicioReporte().setEnabled(false);
		getCmbFechaFinReporte().setEnabled(false);
	}
	
	private void cleanTableVacaciones() {
		DefaultTableModel model = (DefaultTableModel) getTblVacaciones().getModel();
		for(int i= this.getTblVacaciones().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	private void cleanTableVacacionesReporte() {
		DefaultTableModel model = (DefaultTableModel) getTblVacacionesReporte().getModel();
		for(int i= this.getTblVacacionesReporte().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	private void cleanTableReporteFechas() {
		DefaultTableModel model = (DefaultTableModel) getTblReporteFechas().getModel();
		for(int i= this.getTblReporteFechas().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	public void clean() {
		empleadoVacacionesVector = new Vector<EmpleadoVacacionesIf>();
		empleadoVacacionesEliminadasVector = new Vector<EmpleadoVacacionesIf>();
		empleadoIf = null;
		empleadoVacacionesIf = null;
		diasDisponibles = 0D;
		
		getTxtEmpleado().setText("");
		getTxtFechaIngreso().setText("");
		getTxtTotalDiasGenerados().setText("");
		getTxtTotalDiasDisfrutados().setText("");
		getTxtDiasDisponibles().setText("");
		
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		cleanPeriodoVacaciones();
		
		cleanTableVacaciones();
		cleanTableVacacionesReporte();		
	}
	
	public void cleanPeriodoVacaciones(){
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		getTxtDiasDisfrutados().setText("");
		getTxtObservacion().setText("");
		getTxtArchivo().setText("");
	}
	
	Comparator<EmpleadoVacacionesIf> ordenadorEmpleadoVacacionesPorFecha = new Comparator<EmpleadoVacacionesIf>(){
		public int compare(EmpleadoVacacionesIf o1, EmpleadoVacacionesIf o2) {
			return o1.getFechaFin().compareTo(o2.getFechaFin());			
		}		
	};
	
	public void getSelectedObject(){
		
		//seteo empleado
		getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
		
		try {
			//busco fecha Ingreso
			java.sql.Date fechaInicioContrato = null;
			
			//busco contrato
			Map aMap = new HashMap();
			//aMap.put("estado", "A");
			aMap.put("empleadoId", empleadoIf.getId());
			
			//busco codigo contrato relacion dependencia (rrhh indico que servicio profesional no tienen vacaciones)
			Collection tiposContratoRelacionDependencia = SessionServiceLocator.getTipoContratoSessionService().findTipoContratoByCodigo("RD");
			Iterator tiposContratoRelacionDependenciaIt = tiposContratoRelacionDependencia.iterator();
			while(tiposContratoRelacionDependenciaIt.hasNext()){
				TipoContratoIf tipoContratoRelacionDependencia = (TipoContratoIf)tiposContratoRelacionDependenciaIt.next();
				aMap.put("tipocontratoId", tipoContratoRelacionDependencia.getId());
			}
			
			//solo si el empleado tiene contrato relacion de dependencia
			if(aMap.get("tipocontratoId") != null){
				Collection contratos = SessionServiceLocator.getContratoSessionService().findContratoByQuery(aMap);
				Iterator contratosIt = contratos.iterator();
				while(contratosIt.hasNext()){
					ContratoIf contrato = (ContratoIf)contratosIt.next();
					java.sql.Date fechaInicio = contrato.getFechaInicio();
					
					if(fechaInicioContrato == null
							|| fechaInicio.compareTo(fechaInicioContrato) == 1){
						fechaInicioContrato = fechaInicio;
					}
				}
			}
			
			//si se encontro contrato RD con fecha inicio
			if(fechaInicioContrato != null){
				//seteo fecha de ingreso
				getTxtFechaIngreso().setText(Utilitarios.getFechaUppercase(fechaInicioContrato));
										
				////////////SALDO DE DIAS/////////////////////////
				
				diasDisponibles = diasDisponibles(fechaInicioContrato);
				
				//seteo total de dias
				Double totalDiasGenerados = diasDisponibles;
				getTxtTotalDiasGenerados().setText(formatoDecimal.format(totalDiasGenerados));
							
				//////////REGISTROS DE VACACIONES////////////////////
				
				modelVacaciones = (DefaultTableModel) getTblVacaciones().getModel();
				
				ArrayList empleadoVacaciones = (ArrayList)SessionServiceLocator.getEmpleadoVacacionesSessionService().findEmpleadoVacacionesByEmpleadoId(empleadoIf.getId());
				Iterator empleadoVacacionesIt = empleadoVacaciones.iterator();
				
				//ordenar en forma ascendente segun fecha de vacaciones
				Collections.sort((ArrayList)empleadoVacaciones,ordenadorEmpleadoVacacionesPorFecha);
				
				while(empleadoVacacionesIt.hasNext()){
					EmpleadoVacacionesIf empleadoVacacionesIf = (EmpleadoVacacionesIf)empleadoVacacionesIt.next();
					
					empleadoVacacionesVector.add(empleadoVacacionesIf);
					
					//se pone un null por los archivos que ya estan en el servidor
					archivosVacaciones.add(null);
									
					Vector<String> filaDetalle = new Vector<String>();
									
					//saldo de días original es igual a:
					//saldo de dias más el rango de fechas
					Date fechaInicio = empleadoVacacionesIf.getFechaInicio();
					Date fechaFin = empleadoVacacionesIf.getFechaFin();
					
					double diasVacaciones = (fechaFin.getTime() - fechaInicio.getTime()) / MILISEGUNDOS_EN_UN_DIA;
					//se suma uno porque en la resta no esta incluido el dia cero
					diasVacaciones = diasVacaciones + 1;
					double saldoDiasOriginal = diasVacaciones + empleadoVacacionesIf.getSaldoDias();
					
					filaDetalle.add(formatoDecimal.format(saldoDiasOriginal));
					filaDetalle.add(Utilitarios.getFechaCortaUppercase(empleadoVacacionesIf.getFechaInicio()) + " // " + Utilitarios.getFechaCortaUppercase(empleadoVacacionesIf.getFechaFin()));
					
					//dias disfrutados
					filaDetalle.add(formatoDecimal.format(diasVacaciones));
					
					//saldo de días
					filaDetalle.add(formatoDecimal.format(empleadoVacacionesIf.getSaldoDias()));
					
					//cuantos días Sábados y Domingos
					//cuantos días de Lunes a Viernes
					int diasSabadoDomingo = 0;
					int diasLunesViernes = 0;
										
					Calendar fechaInicial = new GregorianCalendar();
					fechaInicial.setTimeInMillis(fechaInicio.getTime());
					Calendar fechaFinal = new GregorianCalendar();
					fechaFinal.setTimeInMillis(fechaFin.getTime());
					
					while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {
						//si el dia de la semana es sabado o domingo
						if (fechaInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY 
								|| fechaInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
							diasSabadoDomingo = diasSabadoDomingo + 1;
						}else{
							diasLunesViernes = diasLunesViernes + 1;
						}
						
						//se suma 1 dia para hacer la validacion del siguiente dia.
						fechaInicial.add(Calendar.DATE, 1);
					}
					filaDetalle.add(String.valueOf(diasLunesViernes));
					filaDetalle.add(String.valueOf(diasSabadoDomingo));					
					
					filaDetalle.add(empleadoVacacionesIf.getObservacion());
					filaDetalle.add(empleadoVacacionesIf.getArchivoAdjunto());
					
					modelVacaciones.addRow(filaDetalle);
					
					//voy actualizando el saldo de dias real
					diasDisponibles = diasDisponibles - diasVacaciones;					
				}
				
				//////TOTAL DÍAS DISFRUTADOS//////////////////
				getTxtTotalDiasDisfrutados().setText(formatoDecimal.format(totalDiasGenerados - diasDisponibles));
				
				//////SALDO DE DIAS DISPONIBLES///////////////
				getTxtDiasDisponibles().setText(formatoDecimal.format(diasDisponibles));
			}			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private Double diasDisponibles(java.sql.Date fechaInicioContrato) {
		Date fechaActual = new Date();
		int anioActual = fechaActual.getYear();
		int anioAnterior = anioActual - 1;
		
		long diasHoy = 0;
		long diasHastaDicAnioAnterior = 0;
		
		//si no ingreso el presente año						
		if(fechaInicioContrato.getYear() < anioActual){
			//primero obtengo el 12 de diciembre del año anterior
			//Date fecha31DicAnioAnterior = new Date(anioAnterior, 11, 31);						
			//calculo días entre 31 dic anio anterior y la fecha de ingreso.
			//long diasComoEmpleado = (fecha31DicAnioAnterior.getTime() - fechaInicioContrato.getTime()) / MILISEGUNDOS_EN_UN_DIA;
									
			//otro calculo de dias como empleado tomando 12 meses de 30 dias
			//primero los dias dentro del mes de ingreso
			long diasEnMesDeIngreso = 1;
			if(fechaInicioContrato.getDate() < 31){
				//sumo uno porque si entro un 30 seria 30 - 30 = 0 y en realidad es 1 dia de trabajo
				diasEnMesDeIngreso = 30 - fechaInicioContrato.getDate() + 1;
			}
			//luego los dias hasta diciembre del anio de ingreso
 			long diasHastaDicAnioIngreso = diasEnMesDeIngreso;
			int mesesTrabajadosAnioIngreso = 11 - fechaInicioContrato.getMonth() + 1;
			if(mesesTrabajadosAnioIngreso > 1){
				diasHastaDicAnioIngreso = diasEnMesDeIngreso + ((mesesTrabajadosAnioIngreso-1)*30);
			}
			//años de trabajo hasta el 31 dic del año anterior y luego dias de trabajo
			diasHastaDicAnioAnterior = diasHastaDicAnioIngreso;
			int aniosTrabajo = anioAnterior - fechaInicioContrato.getYear() + 1;
			if(aniosTrabajo > 1){
				diasHastaDicAnioAnterior = diasHastaDicAnioIngreso + ((aniosTrabajo-1)*360);
			}
			
			//dias del presente año
			//1ro Enero del presente años
			Date primeroEnero = new Date(anioActual, 0, 1);
			long diasAnioActual = (fechaActual.getTime() - primeroEnero.getTime()) / MILISEGUNDOS_EN_UN_DIA;
			
			diasHoy = diasHastaDicAnioAnterior + diasAnioActual;
		}
		
		//ingreso el presente anio
		else{
			diasHoy = (fechaActual.getTime() - fechaInicioContrato.getTime()) / MILISEGUNDOS_EN_UN_DIA;
		}	
		
		//obtengo proporcional de vacaciones al dia en un anio
		double vacacionesPorDia = 15D / 360D;
		//obtengo vacaciones hasta el 31 dic del anio anterior
		Double diasDisponiblesTemp = diasHoy * vacacionesPorDia;
		
		//dias por antiguedad
		//double diasExtraPorAntiguedad = calcularDiasExtraVacacionesPorAntiguedad(diasHastaDicAnioAnterior);
		double diasExtraPorAntiguedad = calcularDiasExtraVacacionesPorAntiguedad(diasHoy);
		
		///////////DIAS DISPONIBLES TOTALES/////////////
		diasDisponiblesTemp = diasDisponiblesTemp + diasExtraPorAntiguedad;
		
		return diasDisponiblesTemp;
	}
	
	Comparator<EmpleadoIf> ordenadorEmpleadoPorApellido = new Comparator<EmpleadoIf>(){
		public int compare(EmpleadoIf e1, EmpleadoIf e2) {
			return e1.getApellidos().compareTo(e2.getApellidos());
		}		
	};
	
	Comparator<Vector<String>> ordenadorVacacionesFechasPorEmpleado = new Comparator<Vector<String>>(){
		public int compare(Vector<String> e1, Vector<String> e2) {
			return e1.get(0).compareTo(e2.get(0));
		}		
	};
	
	Comparator<Vector<String>> ordenadorVacacionesFechasPorFechaInicio = new Comparator<Vector<String>>(){
		public int compare(Vector<String> e1, Vector<String> e2) {
			return e1.get(3).compareTo(e2.get(3));
		}		
	};
	
	public void cargarTablaReportes(){
		cleanTableVacacionesReporte();
		cleanTableReporteFechas();
		
		modelVacacionesReportes = (DefaultTableModel) getTblVacacionesReporte().getModel();
		modelReporteFechas = (DefaultTableModel) getTblReporteFechas().getModel();
		
		java.sql.Date fechaInicioReporte = null;
		java.sql.Date fechaFinReporte = null;
		if(getCbPorFechas().isSelected()){
			getSpTblVacacionesReporte().setViewportView(getTblReporteFechas());
			fechaInicioReporte = new java.sql.Date(getCmbFechaInicioReporte().getDate().getTime());
			fechaFinReporte = new java.sql.Date(getCmbFechaFinReporte().getDate().getTime());
		}else{
			getSpTblVacacionesReporte().setViewportView(getTblVacacionesReporte());
		}
		
		Long idOficina = 0L;
		Long idDepartamento = 0L;
		if(!getCmbOficina().getSelectedItem().equals(TODOS)){
			OficinaIf oficinaIf = (OficinaIf)getCmbOficina().getSelectedItem();
			idOficina = oficinaIf.getId();
		}
		if(!getCmbDepartamento().getSelectedItem().equals(TODOS)){
			DepartamentoIf departamentoIf = (DepartamentoIf)getCmbDepartamento().getSelectedItem();
			idDepartamento = departamentoIf.getId();
		}
		
		try {			
			Collection vacacionesReporte = SessionServiceLocator.getEmpleadoVacacionesSessionService().findEmpleadoVacacionesByOficinaIdByDepartamentoIdAndByFechas(idOficina, idDepartamento, fechaInicioReporte, fechaFinReporte);
			Iterator vacacionesReporteIt = vacacionesReporte.iterator();
			
			//si es por fecha es un tipo de reporte
			if(getCbPorFechas().isSelected()){
				
				List vacacionesPorFecha = new ArrayList();
				
				while(vacacionesReporteIt.hasNext()){
					EmpleadoVacacionesIf empleadoVacacionesIf = (EmpleadoVacacionesIf)vacacionesReporteIt.next();
										
					Vector<String> filaDetalle = new Vector<String>();
										
					EmpleadoIf empleadoDesordenado = mapaEmpleado.get(empleadoVacacionesIf.getEmpleadoId());
					String apellido = empleadoDesordenado.getApellidos().split(" ")[0];
					filaDetalle.add(apellido + " " + empleadoDesordenado.getNombres());
					
					OficinaIf oficina = mapaOficina.get(empleadoDesordenado.getOficinaId());
					DepartamentoIf departamento = mapaDepartamento.get(empleadoDesordenado.getDepartamentoId());
					filaDetalle.add(oficina.getNombre().replaceAll("CREACIONAL/", ""));
					filaDetalle.add(departamento.getNombre());	
					
					Date fechaInicio = empleadoVacacionesIf.getFechaInicio();
					Date fechaFin = empleadoVacacionesIf.getFechaFin();		
					filaDetalle.add(Utilitarios.getFechaUppercase(fechaInicio));
					filaDetalle.add(Utilitarios.getFechaUppercase(fechaFin));
					
					double diasVacaciones = (fechaFin.getTime() - fechaInicio.getTime()) / MILISEGUNDOS_EN_UN_DIA;
					//se suma uno porque en la resta no esta incluido el dia cero
					diasVacaciones = diasVacaciones + 1;
					filaDetalle.add(formatoDecimal.format(diasVacaciones));
										
					vacacionesPorFecha.add(filaDetalle);
					//modelReporteFechas.addRow(filaDetalle);											
				}
				
				//ordeno vector por fecha y por empleado (importante este orden)
				Collections.sort(vacacionesPorFecha, ordenadorVacacionesFechasPorFechaInicio);
				Collections.sort(vacacionesPorFecha, ordenadorVacacionesFechasPorEmpleado);
				
				//recorro el vector y agrego en la tabla
				for(int i=0; i<vacacionesPorFecha.size(); i++){
					Vector<String> filaDetalle = (Vector<String>)vacacionesPorFecha.get(i);
					modelReporteFechas.addRow(filaDetalle);
				}
			}
			//si no es por fecha es otro tipo de reporte
			else{
				//vector empleadosReporte
				List<EmpleadoIf> empleadosVacacionesReporte = new ArrayList<EmpleadoIf>();
				
				//mapa de diasDisfrutados
				Map<Long,Double> diasDisfrutadosMap = new HashMap<Long,Double>();
				
				while(vacacionesReporteIt.hasNext()){
					EmpleadoVacacionesIf empleadoVacacionesIf = (EmpleadoVacacionesIf)vacacionesReporteIt.next();
									
					//DIAS DE VACACIONES POR CADA REGISTRO
					Date fechaInicio = empleadoVacacionesIf.getFechaInicio();
					Date fechaFin = empleadoVacacionesIf.getFechaFin();				
					double diasVacaciones = (fechaFin.getTime() - fechaInicio.getTime()) / MILISEGUNDOS_EN_UN_DIA;
					//se suma uno porque en la resta no esta incluido el dia cero
					diasVacaciones = diasVacaciones + 1;
					
					if(diasDisfrutadosMap.get(empleadoVacacionesIf.getEmpleadoId()) != null){
						double diasVacacionesAcumulado = diasDisfrutadosMap.get(empleadoVacacionesIf.getEmpleadoId()) + diasVacaciones;
						diasDisfrutadosMap.put(empleadoVacacionesIf.getEmpleadoId(), diasVacacionesAcumulado);					
					}else{
						diasDisfrutadosMap.put(empleadoVacacionesIf.getEmpleadoId(), diasVacaciones);
						//lleno vector con empleados para luego poder ordenar por nombre
						EmpleadoIf empleadoDesordenado = mapaEmpleado.get(empleadoVacacionesIf.getEmpleadoId());
						empleadosVacacionesReporte.add(empleadoDesordenado);
					}
				}
				
				//busco codigo contrato relacion dependencia (rrhh indico que servicio profesional no tienen vacaciones)
				TipoContratoIf tipoContratoRelacionDependencia = null;
				Collection tiposContratoRelacionDependencia = SessionServiceLocator.getTipoContratoSessionService().findTipoContratoByCodigo("RD");
				if(tiposContratoRelacionDependencia.size() > 0){
					tipoContratoRelacionDependencia = (TipoContratoIf)tiposContratoRelacionDependencia.iterator().next();
				}
				
				Map aMapContrato = new HashMap();
				aMapContrato.put("estado", "A");
				if(tipoContratoRelacionDependencia != null){
					aMapContrato.put("tipocontratoId", tipoContratoRelacionDependencia.getId());
				}
				//empleados que aun no tienen información de vacaciones
				Collection contratosActivos = SessionServiceLocator.getContratoSessionService().findContratoByQuery(aMapContrato);
				Iterator contratosActivosIt = contratosActivos.iterator();
				while(contratosActivosIt.hasNext()){
					ContratoIf contrato = (ContratoIf)contratosActivosIt.next();
					EmpleadoIf empleadoSinVacaciones = mapaEmpleado.get(contrato.getEmpleadoId());
					if(diasDisfrutadosMap.get(contrato.getEmpleadoId()) == null){
						if(idOficina.compareTo(0L) == 0 && idDepartamento.compareTo(0L) == 0){
							empleadosVacacionesReporte.add(empleadoSinVacaciones);
						}
						else if(idOficina.compareTo(0L) != 0 
								&& empleadoSinVacaciones.getOficinaId().compareTo(idOficina) == 0
								&& idDepartamento.compareTo(0L) == 0){
							empleadosVacacionesReporte.add(empleadoSinVacaciones);
						}
						else if(idDepartamento.compareTo(0L) != 0 
								&& empleadoSinVacaciones.getDepartamentoId().compareTo(idDepartamento) == 0
								&& idOficina.compareTo(0L) == 0){
							empleadosVacacionesReporte.add(empleadoSinVacaciones);
						}
						else if(idOficina.compareTo(0L) != 0 
								&& empleadoSinVacaciones.getOficinaId().compareTo(idOficina) == 0
								&& idDepartamento.compareTo(0L) != 0 
								&& empleadoSinVacaciones.getDepartamentoId().compareTo(idDepartamento) == 0){
							empleadosVacacionesReporte.add(empleadoSinVacaciones);
						}					
					}
				}
				
				//ordeno vector empleado
				Collections.sort(empleadosVacacionesReporte, ordenadorEmpleadoPorApellido);
											
				//recorro el vector
				for(EmpleadoIf empleado : empleadosVacacionesReporte){
					double diasTotalesVacaciones = 0;
					
					if(diasDisfrutadosMap.get(empleado.getId()) != null){
						diasTotalesVacaciones = diasDisfrutadosMap.get(empleado.getId());
					}
					
					//busco fecha Ingreso
					java.sql.Date fechaInicioContrato = null;
					
					//busco contrato
					Map aMap = new HashMap();
					aMap.put("estado", "A");
					aMap.put("empleadoId", empleado.getId());
					
					//busco codigo contrato relacion dependencia (rrhh indico que servicio profesional no tienen vacaciones)
					if(tipoContratoRelacionDependencia != null){
						aMap.put("tipocontratoId", tipoContratoRelacionDependencia.getId());
					}
					/*Collection tiposContratoRelacionDependencia = SessionServiceLocator.getTipoContratoSessionService().findTipoContratoByCodigo("RD");
					Iterator tiposContratoRelacionDependenciaIt = tiposContratoRelacionDependencia.iterator();
					while(tiposContratoRelacionDependenciaIt.hasNext()){
						TipoContratoIf tipoContratoRelacionDependencia = (TipoContratoIf)tiposContratoRelacionDependenciaIt.next();
						aMap.put("tipocontratoId", tipoContratoRelacionDependencia.getId());
					}*/
					
					//solo si el empleado tiene contrato relacion de dependencia
					if(aMap.get("tipocontratoId") != null){
						Collection contratos = SessionServiceLocator.getContratoSessionService().findContratoByQuery(aMap);
						Iterator contratosIt = contratos.iterator();
						while(contratosIt.hasNext()){
							ContratoIf contrato = (ContratoIf)contratosIt.next();
							java.sql.Date fechaInicio = contrato.getFechaInicio();
							
							if(fechaInicioContrato == null
									|| fechaInicio.compareTo(fechaInicioContrato) == 1){
								fechaInicioContrato = fechaInicio;
							}
						}
					}
					
					if(fechaInicioContrato != null){
						double diasTotalesDisponibles = diasDisponibles(fechaInicioContrato);
						
						double saldoDiasEmpleado = diasTotalesDisponibles - diasTotalesVacaciones;
						
						OficinaIf oficina = mapaOficina.get(empleado.getOficinaId());
						DepartamentoIf departamento = mapaDepartamento.get(empleado.getDepartamentoId());
						
						Vector<String> filaDetalle = new Vector<String>();
						
						/*String nombre = empleado.getNombres().split(" ")[0];
						if(nombre.equals("MARIA")){
							nombre = empleado.getNombres();
						}*/
						
						String apellido = empleado.getApellidos().split(" ")[0];
						filaDetalle.add(apellido + " " + empleado.getNombres());
											
						filaDetalle.add(Utilitarios.getFechaUppercase(fechaInicioContrato));
						filaDetalle.add(oficina.getNombre().replaceAll("CREACIONAL/", ""));
						filaDetalle.add(departamento.getNombre());
						filaDetalle.add(formatoDecimal.format(diasTotalesDisponibles));
						filaDetalle.add(formatoDecimal.format(diasTotalesVacaciones));
						filaDetalle.add(formatoDecimal.format(saldoDiasEmpleado));
						
						modelVacacionesReportes.addRow(filaDetalle);					
					}				
				}
			}		
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	private void initListeners() {
		getBtnArchivo().addActionListener(oActionListenerAgregarArchivo);
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cargarTablaReportes();
			}
		});
		
		getCbPorFechas().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbPorFechas().isSelected()){
					getCmbFechaInicioReporte().setEnabled(true);
					getCmbFechaFinReporte().setEnabled(true);
					getCmbFechaInicioReporte().setCalendar(new GregorianCalendar());
					getCmbFechaFinReporte().setCalendar(new GregorianCalendar());
				}else{
					getCmbFechaInicioReporte().setEnabled(false);
					getCmbFechaFinReporte().setEnabled(false);
					getCmbFechaInicioReporte().setDate(null);
					getCmbFechaFinReporte().setDate(null);
				}
			}
		});
		
		getCmbFechaInicio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fechaInicio = getCmbFechaInicio().getDate();
				Date fechaFin = getCmbFechaFin().getDate();
				
				if(!fechaFin.before(fechaInicio)){
					double diasDisfrutados = (fechaFin.getTime() - fechaInicio.getTime()) / MILISEGUNDOS_EN_UN_DIA;
					getTxtDiasDisfrutados().setText(formatoDecimal.format(diasDisfrutados+1));
				}else{
					getCmbFechaFin().setDate(getCmbFechaInicio().getDate());
				}
			}			
		});
		
		getCmbFechaFin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fechaInicio = getCmbFechaInicio().getDate();
				Date fechaFin = getCmbFechaFin().getDate();
				
				if(!fechaFin.before(fechaInicio)){
					double diasDisfrutados = (fechaFin.getTime() - fechaInicio.getTime()) / MILISEGUNDOS_EN_UN_DIA;
					getTxtDiasDisfrutados().setText(formatoDecimal.format(diasDisfrutados+1));
				}else{
					getCmbFechaInicio().setDate(getCmbFechaFin().getDate());
				}
			}			
		});
		
		getBtnEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoIf = null;
				EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria("Empleados",Parametros.getIdEmpresa());
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					clean();
					empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getSelectedObject();
				}
			}
		});
		
		getBtnAgregarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDetalle();
				cleanPeriodoVacaciones();
			}
		});
		
		getBtnActualizarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getTblVacaciones().getSelectedRow() != -1){
					actualizarDetalle();
					cleanPeriodoVacaciones();
				}else{
					SpiritAlert.createAlert("Debe seleccionar una fila de la tabla.", SpiritAlert.INFORMATION);
				}				
			}
		});
		
		getBtnEliminarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getTblVacaciones().getSelectedRow() != -1){
					eliminarDetalle();
					cleanPeriodoVacaciones();
				}else{
					SpiritAlert.createAlert("Debe seleccionar una fila de la tabla.", SpiritAlert.INFORMATION);
				}				
			}
		});
		
		// Opcion Que Permite Visualizar un archivo deseado de la tabla
		JMenuItem itemVerArchivo = new JMenuItem("Visualizar Archivo");
		popupArchivoVacaciones.add(itemVerArchivo);
		itemVerArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				verArchivo();
			}
		});
		
		getBtnVisualizarArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					if(!getTxtArchivo().getText().equals(""))
						Archivos.abrirArchivoDesdeServidor(getTxtArchivo().getText());
				} catch (IOException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		});
		
		getTblVacaciones().addMouseListener(oMouseAdapterTblVacaciones);
		getTblVacaciones().addKeyListener(oKeyAdapterTblVacaciones);
	}
	
	MouseListener oMouseAdapterTblVacaciones = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblVacaciones().getModel().getRowCount() > 0)
				popupArchivoVacaciones.show(evt.getComponent(), evt.getX(), evt.getY());
			else
				enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblVacaciones= new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	private void verArchivo() {
		if (getTblVacaciones().getSelectedRow() != -1) {
			try {
				String urlCampanaArchivo = getTxtArchivo().getText();
				Archivos.abrirArchivoDesdeServidor(urlCampanaArchivo);
			} catch (IOException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de la lista a visualizar !!!", SpiritAlert.WARNING);
		}
	}
	
	private void eliminarDetalle() {
		if (getTblVacaciones().getSelectedRow() != -1) {
			int filaSeleccionada = getTblVacaciones().getSelectedRow();
			
			//solo se puede eliminar la ultima fila
			if(filaSeleccionada == (empleadoVacacionesVector.size()-1)){
				EmpleadoVacacionesIf empleadoVacaciones = empleadoVacacionesVector.get(filaSeleccionada);
				if (empleadoVacaciones.getId() != null){
					empleadoVacacionesEliminadasVector.add(empleadoVacaciones);
				}
				
				//saldo de días
				double diasDisfrutados = Double.valueOf(getTxtDiasDisfrutados().getText());				
				diasDisponibles = diasDisponibles + diasDisfrutados;				
				getTxtDiasDisponibles().setText(formatoDecimal.format(diasDisponibles));
				
				//dias disponibles
				double totalDiasDisfrutados = Double.valueOf(getTxtTotalDiasDisfrutados().getText());		
				getTxtTotalDiasDisfrutados().setText(formatoDecimal.format(totalDiasDisfrutados - diasDisfrutados));				
				
				archivosVacaciones.remove(getTblVacaciones().getSelectedRow());
				empleadoVacacionesVector.remove(getTblVacaciones().getSelectedRow());
				modelVacaciones.removeRow(getTblVacaciones().getSelectedRow());
			}else{
				SpiritAlert.createAlert("Solo es posible eliminar el ultimo registro guardado", SpiritAlert.WARNING);
			}	
			
		} else {
			SpiritAlert.createAlert("Debe elegir el registro a eliminar.", SpiritAlert.WARNING);
		}
	}
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (getTblVacaciones().getSelectedRow() != -1) {
			EmpleadoVacacionesIf empleadoVacacionesIf = (EmpleadoVacacionesIf) empleadoVacacionesVector.get(((JTable) evt.getSource()).getSelectedRow());
			
			getCmbFechaInicio().setDate(empleadoVacacionesIf.getFechaInicio());
			getCmbFechaFin().setDate(empleadoVacacionesIf.getFechaFin());
			
			Date fechaInicio = getCmbFechaInicio().getDate();
			Date fechaFin = getCmbFechaFin().getDate();
			double diasDisfrutados = (fechaFin.getTime() - fechaInicio.getTime()) / MILISEGUNDOS_EN_UN_DIA;
				
			getTxtDiasDisfrutados().setText(formatoDecimal.format(diasDisfrutados+1));
			
			getTxtObservacion().setText(empleadoVacacionesIf.getObservacion());
			
			getTxtArchivo().setText(empleadoVacacionesIf.getArchivoAdjunto());
		}
	}
	
	public double calcularDiasExtraVacacionesPorAntiguedad(long dias){
		
		double diasExtraTotales = 0D;
		int diasExtra = 0;
		Double anios = dias / 360D;
		int aniosCompletos = anios.intValue();
				
		if(aniosCompletos >= 6){
			for(int i=6; i<=aniosCompletos; i++){
				diasExtra = diasExtra + i - 5;
			}
			/*
			//por lo que va del año
			Date fechaActual = new Date();
			int anioActual = fechaActual.getYear();
			Date primeroEnero = new Date(anioActual, 0, 1);
			//dias que van del año
			long diasAnioActual = (fechaActual.getTime() - primeroEnero.getTime()) / MILISEGUNDOS_EN_UN_DIA;
			//calculo de lo que va del año
			//porcentaje extra de dias extra en el año en curso
			//int diasExtraPresenteAnio = aniosCompletos - 5;
			double diasExtraPresenteAnio = ((dias + diasAnioActual) / 360D) - 5;
			
			diasExtraTotales = (diasExtraPresenteAnio/360D) * diasAnioActual;
			diasExtraTotales = diasExtraTotales + diasExtra;
			*/
			
			//por los dias fuera del año completo
			//double diasFueraDeAniosCompletos = (anios - anios.intValue()) * 360;
			diasExtraTotales = diasExtra;
		}	
		
		//maximos 120 dias
		if(diasExtra > 120)
			diasExtra = 120;
		
		return diasExtraTotales;
	}
	
	/**
	 * Action Listener que me permite adjuntar un archivo
	 */
	
	ActionListener oActionListenerAgregarArchivo = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			
			Component parent = (Component) actionEvent.getSource();
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAccessory(new LabelAccessory(fileChooser));
			
			FileFilter filterJPG = new ExtensionFileFilter(null, new String[] {"JPG", "JPEG" });
			fileChooser.addChoosableFileFilter(filterJPG);
			FileFilter filterGIF = new ExtensionFileFilter("gif", new String[] { "gif" });
			fileChooser.addChoosableFileFilter(filterGIF);
			FileFilter filterDOC = new ExtensionFileFilter("doc", new String[] { "doc" });
			fileChooser.addChoosableFileFilter(filterDOC);
			fileChooser.setFileFilter(fileChooser.getAcceptAllFileFilter());
			int status = fileChooser.showOpenDialog(parent);
			
			if (status == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				boolean existe;
				try {
					existe = SessionServiceLocator.getFileManagerSessionService().existeArchivo(
							Parametros.getUrlCarpetaServidor(), selectedFile.getName());
					if (!existe){
						/**
						 * valido que botón ha sido presionado y le asigno al
						 * correspondiente textbox el path del archivo que haya
						 * seleccionado
						 */
						if ((actionEvent.getSource() == getBtnArchivo()))
							getTxtArchivo().setText(fileChooser.getSelectedFile().getAbsolutePath());
												
						/**
						 * ejecuto el archivo con su respectivo programa para poder ser
						 * previsualizado
						 */
						try {
							int opcion = JOptionPane.showOptionDialog(null, "Desea previsualizar el archivo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
							if (opcion == JOptionPane.YES_OPTION) {
								String filename = selectedFile.getAbsolutePath();
								Archivos.abrirArchivoLocal(filename);
							}
						} catch (IOException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					} else {
						int opcion = JOptionPane.showOptionDialog(null, "Archivo ya existe, desea reemplazarlo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "No");
						if (opcion == JOptionPane.YES_OPTION) {
							if ((actionEvent.getSource() == getBtnArchivo()))
								getTxtArchivo().setText(fileChooser.getSelectedFile().getAbsolutePath());
						} else {
							if ((actionEvent.getSource() == getBtnArchivo()))
								getTxtArchivo().setText("");
						}
					}
				} catch (GenericBusinessException e1) {
					e1.printStackTrace();
					SpiritAlert.createAlert(e1.getMessage(), SpiritAlert.ERROR);
				}
			} else if (status == JFileChooser.CANCEL_OPTION) {
				
			}
		}
	};
	
	public boolean validateFieldsDetalle(boolean agregarDetalle) {
		Date fechaInicio = getCmbFechaInicio().getDate();
		Date fechaFin = getCmbFechaFin().getDate();
		
		if (empleadoIf == null) {
			SpiritAlert.createAlert("Debe seleccionar un empleado.", SpiritAlert.WARNING);
			getBtnEmpleado().grabFocus();
			return false;
		}
		
		if(fechaFin.before(fechaInicio)){
			SpiritAlert.createAlert("La Fecha Fin no puede estar antes de la Fecha Inicio.", SpiritAlert.WARNING);
			getCmbFechaFin().grabFocus();
			return false;
		}
		
		if(getTxtDiasDisfrutados().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar los días disfrutados.", SpiritAlert.WARNING);
			getCmbFechaInicio().grabFocus();
			return false;
		}
		
		if(agregarDetalle){
			for(EmpleadoVacacionesIf empleadoVacaciones : empleadoVacacionesVector){
				Date fechaInicioTemp = empleadoVacaciones.getFechaInicio();
				Date fechaFinTemp = empleadoVacaciones.getFechaFin();
				
				if(fechaInicio.compareTo(fechaInicioTemp) == 0
						|| fechaInicio.compareTo(fechaFinTemp) == 0 
						|| (fechaInicio.after(fechaInicioTemp) && fechaInicio.before(fechaFinTemp))){
					SpiritAlert.createAlert("La Fecha Inicio se cruza con fechas previas.", SpiritAlert.WARNING);
					getCmbFechaInicio().grabFocus();
					return false;
				}
				
				if(fechaFin.compareTo(fechaInicioTemp) == 0
						|| fechaFin.compareTo(fechaFinTemp) == 0 
						|| (fechaFin.after(fechaInicioTemp) && fechaFin.before(fechaFinTemp))){
					SpiritAlert.createAlert("La Fecha Fin se cruza con fechas previas.", SpiritAlert.WARNING);
					getCmbFechaInicio().grabFocus();
					return false;
				}
			}
		}		
		
		return true;
	}
	
	public void agregarDetalle() {
		try {			
			if (validateFieldsDetalle(true)){
				
				double diasPendientes = Double.valueOf(getTxtDiasDisponibles().getText());
				double diasDisfrutados2 = Double.valueOf(getTxtDiasDisfrutados().getText());
				double saldoDias = diasPendientes - diasDisfrutados2;
				
				if(saldoDias < 0){
					String si = "Si";
					String no = "No";
					Object[] options = {si,no};
					String mensaje = "Los días disfrutados son más que los días disponibles. ¿Desea continuar?";
					int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if(opcion == JOptionPane.YES_OPTION) {
						agregandoDetalle();
					}
				}else{
					agregandoDetalle();
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se pudo ingresar el detalle.", SpiritAlert.ERROR);
		}
	}

	private void agregandoDetalle() {
		modelVacaciones = (DefaultTableModel) getTblVacaciones().getModel();
		
		Vector<String> filaDetalle = new Vector<String>();
						
		filaDetalle.add(getTxtDiasDisponibles().getText());
		filaDetalle.add(Utilitarios.getFechaCortaUppercase(getCmbFechaInicio().getDate()) + " // " + Utilitarios.getFechaCortaUppercase(getCmbFechaFin().getDate()));
		filaDetalle.add(getTxtDiasDisfrutados().getText());
		
		//saldo de días
		double diasDisfrutados = Double.valueOf(getTxtDiasDisfrutados().getText());
		diasDisponibles = diasDisponibles - diasDisfrutados;				
		filaDetalle.add(formatoDecimal.format(diasDisponibles));
		
		//tambien actualizo los dias disponibes y días disfrutados totales
		getTxtDiasDisponibles().setText(formatoDecimal.format(diasDisponibles));
		
		Double totalDiasDisfrutados = Double.valueOf(getTxtTotalDiasDisfrutados().getText());
		getTxtTotalDiasDisfrutados().setText(formatoDecimal.format(totalDiasDisfrutados + diasDisfrutados));
		
		filaDetalle.add(getTxtObservacion().getText());
		filaDetalle.add(getTxtArchivo().getText());
		
		modelVacaciones.addRow(filaDetalle);	
		
		EmpleadoVacacionesData data = new EmpleadoVacacionesData();
		
		data.setEmpleadoId(empleadoIf.getId());
		data.setSaldoDias(diasDisponibles.floatValue());
		data.setFechaInicio(new java.sql.Timestamp(getCmbFechaInicio().getDate().getTime()));
		data.setFechaFin(new java.sql.Timestamp(getCmbFechaFin().getDate().getTime()));
		data.setObservacion(this.getTxtObservacion().getText());
		
		if (!getTxtArchivo().getText().equals(""))
			data.setArchivoAdjunto(selectedFile.getPath());
		
		if (selectedFile != null){
			archivosVacaciones.add(selectedFile);
		}else{
			//necesario sino se cae al eliminar detalles
			archivosVacaciones.add(null);
		}
		
		empleadoVacacionesVector.add(data);
	}
	
	public void actualizarDetalle() {
		try {			
			if(validateFieldsDetalle(false)){
				
				int fila = getTblVacaciones().getSelectedRow();
				empleadoVacacionesIf = (EmpleadoVacacionesIf) empleadoVacacionesVector.get(fila);
				
				empleadoVacacionesIf.setEmpleadoId(empleadoIf.getId());
				
				//en la actualización no se modifica fechas ni saldo de dias
				//porque puede haber errores si que escoje registros de vacaciones pasadas
				empleadoVacacionesIf.setSaldoDias(empleadoVacacionesIf.getSaldoDias());
				empleadoVacacionesIf.setFechaInicio(empleadoVacacionesIf.getFechaInicio());
				empleadoVacacionesIf.setFechaFin(empleadoVacacionesIf.getFechaFin());
				
				empleadoVacacionesIf.setObservacion(this.getTxtObservacion().getText());
				
				if (!getTxtArchivo().getText().equals("") && selectedFile != null)
					empleadoVacacionesIf.setArchivoAdjunto(selectedFile.getPath());
				
				if (selectedFile != null){
					archivosVacaciones.add(selectedFile);
				}
				
				empleadoVacacionesVector.set(fila, empleadoVacacionesIf);
								
				//tabla							
				modelVacaciones.setValueAt(getTxtObservacion().getText(), fila, 4);
				modelVacaciones.setValueAt(getTxtArchivo().getText(), fila, 5);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se pudo ingresar el detalle.", SpiritAlert.ERROR);
		}
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
			if(getJtpVacaciones().getSelectedIndex() == 1){
				if (getTblVacacionesReporte().getModel().getRowCount() > 0 
						|| (getCbPorFechas().isSelected() && getTblReporteFechas().getModel().getRowCount() > 0)) {
					//int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Vacaciones?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					//if (opcion == JOptionPane.YES_OPTION) {
						
						String fileName = "jaspers/rrhh/RPEmpleadoVacacionesReporte.jasper";
						
						int opcion = JOptionPane.showOptionDialog(null, "¿Desea el reporte sin cabeceras?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
						if (opcion == JOptionPane.YES_OPTION) {
							fileName = "jaspers/rrhh/RPEmpleadoVacacionesReporteSC.jasper";
						}
						
						HashMap parametrosMap = new HashMap();
						
						JTable tablaVista = new JTable(getTblVacacionesReporte().getRowCount(), getTblVacacionesReporte().getColumnCount());
						
						for (int i = 0; i < getTblVacacionesReporte().getRowCount(); i++) {
							for (int j = 0; j < getTblVacacionesReporte().getColumnCount(); j++) {
								tablaVista.setValueAt(getTblVacacionesReporte().getValueAt(i, j), i, j);
							}
						}
						
						DefaultTableModel tblModelReporte = (DefaultTableModel)tablaVista.getModel();
						
						if(getCbPorFechas().isSelected()){
							int opcion2 = JOptionPane.showOptionDialog(null, "¿Desea el reporte sin cabeceras?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
							if (opcion2 == JOptionPane.YES_OPTION) {
								fileName = "jaspers/rrhh/RPEmpleadoVacacionesReportePorFechasSC.jasper";
							}else{
								fileName = "jaspers/rrhh/RPEmpleadoVacacionesReportePorFechas.jasper";
							}
							
							parametrosMap.put("fechaInicio", Utilitarios.getFechaUppercase(getCmbFechaInicioReporte().getDate()));
							parametrosMap.put("fechaFin", Utilitarios.getFechaUppercase(getCmbFechaFinReporte().getDate()));
							
							
							JTable tablaVistaPorFechas = new JTable(getTblReporteFechas().getRowCount(), getTblReporteFechas().getColumnCount());
							
							for (int i = 0; i < getTblReporteFechas().getRowCount(); i++) {
								for (int j = 0; j < getTblReporteFechas().getColumnCount(); j++) {
									tablaVistaPorFechas.setValueAt(getTblReporteFechas().getValueAt(i, j), i, j);
								}
							}
							
							tblModelReporte = (DefaultTableModel)tablaVistaPorFechas.getModel();
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
						EmpresaIf empresa = (EmpresaIf)Parametros.getEmpresa();
						parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
						
						ReportModelImpl.processReport(fileName, parametrosMap, tblModelReporte, true);
					//}
				} else{
					SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
				}
				
			}else{
				if (getTblVacaciones().getModel().getRowCount() > 0) {
					//int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Vacaciones?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					//if (opcion == JOptionPane.YES_OPTION) {
						String fileName = "jaspers/rrhh/RPEmpleadoVacaciones.jasper";
						
						DefaultTableModel tblModelReporte = (DefaultTableModel)getTblVacaciones().getModel();
						
						HashMap parametrosMap = new HashMap();
						CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
						parametrosMap.put("ciudad", ciudad.getNombre());
						String fechaActual = Utilitarios.dateHoraHoy();
						String year = fechaActual.substring(0,4);
						String month = fechaActual.substring(5,7);
						String day = fechaActual.substring(8,10);
						String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " del " + year;
						parametrosMap.put("usuario", Parametros.getUsuario());
						parametrosMap.put("emitido", fechaEmision);
						EmpresaIf empresa = (EmpresaIf)Parametros.getEmpresa();
						parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
						
						parametrosMap.put("empleado", empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
						
						parametrosMap.put("fechaIngreso", getTxtFechaIngreso().getText());
						
						parametrosMap.put("totalDiasGenerados", getTxtTotalDiasGenerados().getText());
						parametrosMap.put("totalDiasDisfrutados", getTxtTotalDiasDisfrutados().getText());
						parametrosMap.put("diasDisponibles", getTxtDiasDisponibles().getText());
						
						ReportModelImpl.processReport(fileName, parametrosMap, tblModelReporte, true);
					//}
				} else{
					SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
				}
			}
			
			
		} catch (ParseException pe) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			pe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			if(empleadoVacacionesVector.size() > 0 || empleadoVacacionesEliminadasVector.size() > 0){
				
				//archivos
				Collection<FileInputStreamSerializable> archivosColeccion = new Vector<FileInputStreamSerializable>();
				for (File archivo: archivosVacaciones){
					if (archivo!=null){
						archivosColeccion.add(new FileInputStreamSerializable(archivo, archivo.getName()));
					} else
						archivosColeccion.add(null);
				}
				
				String urlCarpetaServidor = Parametros.getUrlCarpetaServidor()+"vacaciones/";
				String rutaWindowsCarpetaServidor = Parametros.getRutaWindowsCarpetaServidor()+"vacaciones\\\\";
								
				SessionServiceLocator.getEmpleadoVacacionesSessionService().procesarEmpleadoVacaciones(empleadoVacacionesVector, empleadoVacacionesEliminadasVector, archivosColeccion, 
						urlCarpetaServidor, rutaWindowsCarpetaServidor);
				SpiritAlert.createAlert("Vacaciones grabadas con éxito.", SpiritAlert.INFORMATION);
				showSaveMode();				
				
			}else{
				SpiritAlert.createAlert("No existen datos para grabar.", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

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
		// TODO Auto-generated method stub
		
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showSaveMode() {
		clean();
		setSaveMode();
		
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
}
