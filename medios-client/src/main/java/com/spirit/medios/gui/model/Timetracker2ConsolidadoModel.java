package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
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
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.medios.entity.Timetracker2DetalleIf;
import com.spirit.medios.entity.Timetracker2EmpleadoIf;
import com.spirit.medios.entity.Timetracker2If;
import com.spirit.medios.entity.Timetracker2TiempoIf;
import com.spirit.medios.gui.panel.JPTimetracker2Consolidado;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;


public class Timetracker2ConsolidadoModel extends JPTimetracker2Consolidado {
	
	private EmpleadoIf empleadoIf = null;
	private EmpleadoCriteria empleadoCriteria;
	private JDPopupFinderModel popupFinder;
	protected ClienteOficinaIf clienteOficinaIf;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private Map<Long, EmpleadoIf> mapaEmpleado = new HashMap<Long, EmpleadoIf>();
	private Map<Long, Timetracker2If> mapaTimetracker = new HashMap<Long, Timetracker2If>();
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long, Timetracker2EmpleadoIf> mapaTimetrackerEmpleado = new HashMap<Long, Timetracker2EmpleadoIf>();
	private DecimalFormat formatoDecimal = new DecimalFormat("##0.00");
	private DefaultTableModel tableModel;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no};
	private static final String TODOS = "TODOS";
	
	
	public Timetracker2ConsolidadoModel(){
		cargarComboOficina();
		cargarComboDepartamento();
		cargarMapas();
		initKeyListeners();
		anchoColumnasTabla();
		initListeners();
		showSaveMode();
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
			
			mapaEmpleado.clear();
			Collection empleados = SessionServiceLocator
					.getEmpleadoSessionService().findEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Iterator empleadosIt = empleados.iterator();
			while (empleadosIt.hasNext()) {
				EmpleadoIf empleadoIf = (EmpleadoIf)empleadosIt.next();
				mapaEmpleado.put(empleadoIf.getId(), empleadoIf);
			}
			
			mapaTimetracker.clear();
			Collection timetracker = SessionServiceLocator
					.getTimetracker2SessionService().findTimetracker2ByEmpresaId(Parametros.getIdEmpresa());
			Iterator timetrackerIt = timetracker.iterator();
			while (timetrackerIt.hasNext()) {
				Timetracker2If timetrackerIf = (Timetracker2If)timetrackerIt.next();
				mapaTimetracker.put(timetrackerIf.getId(), timetrackerIf);
			}
			
			mapaTimetrackerEmpleado.clear();
			Collection timetracker2Empleado = SessionServiceLocator
					.getTimetracker2EmpleadoSessionService().getTimetracker2EmpleadoList();
			Iterator timetracker2EmpleadoIt = timetracker2Empleado.iterator();
			while (timetracker2EmpleadoIt.hasNext()) {
				Timetracker2EmpleadoIf timetrackerEmpleadoIf = (Timetracker2EmpleadoIf)timetracker2EmpleadoIt.next();
				mapaTimetrackerEmpleado.put(timetrackerEmpleadoIf.getId(), timetrackerEmpleadoIf);
			}	
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void initKeyListeners(){
		getBtnBuscarEmpleado().setToolTipText("Buscar Empleado");
		getBtnBuscarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCliente().setToolTipText("Buscar Cliente");
		getBtnBuscarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnConsultar().setToolTipText("Consultar Tiempos");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setShowNoneButton(false);
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setEditable(false);
		
		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());
		
		getCbTodosEmpleados().setSelected(true);
	}
	
	private void anchoColumnasTabla() {
		getTblTiempos().getTableHeader().setReorderingAllowed(false);
		getTblTiempos().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumn anchoColumna = getTblTiempos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(70);		
		anchoColumna = getTblTiempos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(11);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(12);
		anchoColumna.setPreferredWidth(90);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(13);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTiempos().getColumnModel().getColumn(14);
		anchoColumna.setPreferredWidth(70);
				
		TableCellRendererHorizontalCenterAlignment tableCellCenterRenderer = new TableCellRendererHorizontalCenterAlignment();
		getTblTiempos().getColumnModel().getColumn(1).setCellRenderer(tableCellCenterRenderer);
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
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
	}
	
	public void initListeners(){
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				/*if(clienteOficinaIf == null){
					SpiritAlert.createAlert("Debe seleccionar un cliente.", SpiritAlert.WARNING);
				}else{*/
					cargarTabla2();
				//}
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
					cleanTable();
					getCbTodosEmpleados().setSelected(false);
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
		
		getCbTodosClientes().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbTodosClientes().isSelected()){
					clienteOficinaIf = null;
					getTxtCliente().setText("");
				}
			}
		});
		
		getCbReporteCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbReporteCliente().isSelected()){
					getCbReporteEmpleado().setSelected(false);
				}
			}
		});
		
		getCbReporteEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbReporteEmpleado().isSelected()){
					getCbReporteCliente().setSelected(false);
				}
			}
		});
	}
	
	public void cargarTabla(){
		try {
			cleanTable();
			tableModel = (DefaultTableModel) getTblTiempos().getModel();
			
			Timestamp fechaInicio = new java.sql.Timestamp(getCmbFechaInicio().getDate().getTime());
			Timestamp fechaFin = new java.sql.Timestamp(getCmbFechaFin().getDate().getTime());
			
			Map aMap = new HashMap();
			if(clienteOficinaIf != null){
				aMap.put("timetracker2ClienteOficinaId", clienteOficinaIf.getId());
			}
			
			Long empleadoId = 0L;
			if(empleadoIf != null){
				empleadoId = empleadoIf.getId();
			}
			
			//mapa de cada timetrackerEmpleadoId con su actividad
			Map<Long,Vector<Timetracker2DetalleIf>> mapaEmpleadoActividadDetalle = new HashMap<Long,Vector<Timetracker2DetalleIf>>();
			//se busca todas la actividades filtradas por fechas, y cliente y empleado si se uso esos filtros			
			Collection timetrackerDetalles = SessionServiceLocator.getTimetracker2DetalleSessionService().findTimetracker2DetalleByQueryByFechaInicioAndByFechaFin(aMap, fechaInicio, fechaFin, empleadoId, true);
			
			if(timetrackerDetalles.size() > 0){
				Iterator timetrackerDetallesIt = timetrackerDetalles.iterator();
				while(timetrackerDetallesIt.hasNext()){
					Timetracker2DetalleIf timetrackerDetalleIf = (Timetracker2DetalleIf)timetrackerDetallesIt.next();
					Timetracker2EmpleadoIf timetrackerEmpleadoIf = mapaTimetrackerEmpleado.get(timetrackerDetalleIf.getTimetracker2EmpleadoId());
					
					Vector<Timetracker2DetalleIf> detalles = new Vector<Timetracker2DetalleIf>();
					if(mapaEmpleadoActividadDetalle.get(timetrackerEmpleadoIf.getEmpleadoId()) == null){
						detalles.add(timetrackerDetalleIf);
						mapaEmpleadoActividadDetalle.put(timetrackerEmpleadoIf.getEmpleadoId(), detalles);
					}else{
						detalles = mapaEmpleadoActividadDetalle.get(timetrackerEmpleadoIf.getEmpleadoId());
						detalles.add(timetrackerDetalleIf);
						mapaEmpleadoActividadDetalle.put(timetrackerEmpleadoIf.getEmpleadoId(), detalles);
					}
				}
							
				//mapa empleado con su arreglo de meses y tiempos
				Map<Long,Vector<String>> mapaEmpleadoTiempos = new HashMap<Long,Vector<String>>();
				Iterator mapaEmpleadoDetalleIt = mapaEmpleadoActividadDetalle.keySet().iterator();
				while(mapaEmpleadoDetalleIt.hasNext()){
					Long empleadoDetalleId = (Long)mapaEmpleadoDetalleIt.next();
					Vector<Timetracker2DetalleIf> detalles  = mapaEmpleadoActividadDetalle.get(empleadoDetalleId);
					
					for(Timetracker2DetalleIf detalle : detalles){
						int mes = detalle.getFecha().getMonth();
						double tiempo = detalle.getTiempo();
						String tiempoTexto = formatoDecimal.format(tiempo);
						
						if(mapaEmpleadoTiempos.get(empleadoDetalleId) == null){
							Vector<String> tiemposPorMesPorEmpleado = new Vector<String>();
							
							for(int i=0; i<13; i++){
								tiemposPorMesPorEmpleado.add("");
							}
							
							tiemposPorMesPorEmpleado.set(mes, tiempoTexto);
							//para el total por empleado
							tiemposPorMesPorEmpleado.set(12, tiempoTexto);
							mapaEmpleadoTiempos.put(empleadoDetalleId, tiemposPorMesPorEmpleado);
						}else{
							Vector<String> tiemposPorMesPorEmpleado = mapaEmpleadoTiempos.get(empleadoDetalleId);						
							sumarTiempos(tiemposPorMesPorEmpleado, mes, String.valueOf(tiempo));
							//para el total por empleado
							sumarTiempos(tiemposPorMesPorEmpleado, 12, String.valueOf(tiempo));
							mapaEmpleadoTiempos.put(empleadoDetalleId, tiemposPorMesPorEmpleado);
						}
					}			
				}
				
				//lleno la tabla
				//arreglo de datos para luego ordenarse
				ArrayList<Vector<String>> ordenarEmpleados = new ArrayList<Vector<String>>();
				//arreglo de sumas por mes
				Vector<String> tiemposTotalesPorMes = new Vector<String>();
				for(int i=0; i<13; i++){
					tiemposTotalesPorMes.add("");
				}
				
				Iterator mapaEmpleadoTiemposIt = mapaEmpleadoTiempos.keySet().iterator();
				while(mapaEmpleadoTiemposIt.hasNext()){
					Long empleadoIdFila = (Long)mapaEmpleadoTiemposIt.next();
					EmpleadoIf empleado = mapaEmpleado.get(empleadoIdFila);
					
					//if(!empleado.getCodigo().equals("PCI")){
						Vector<String> fila = new Vector<String>();
						
						//empleado				
						fila.add(empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0]);
						
						//tiempo designado
						if(clienteOficinaIf != null){
							Map mapaTiempo = new HashMap();
							mapaTiempo.put("empleadoId", empleado.getId());
							mapaTiempo.put("clienteOficinaId", clienteOficinaIf.getId());			
							Collection registros = SessionServiceLocator.getTimetracker2TiempoSessionService().findTimetracker2TiempoByQuery(mapaTiempo);
							if(registros.size() > 0){
								Timetracker2TiempoIf tiempo = (Timetracker2TiempoIf)registros.iterator().next();
								fila.add(formatoDecimal.format(tiempo.getTiempoDesignado()));
							}else{
								fila.add("");
							}
						}else{
							fila.add("");
						}											
						
						//totales por mes
						Vector<String> tiemposPorMesPorEmpleado = mapaEmpleadoTiempos.get(empleadoIdFila);
						/*for(String tiempoMes : tiemposPorMesPorEmpleado){
							fila.add(tiempoMes);
						}*/
						for(int i=0; i<tiemposPorMesPorEmpleado.size(); i++){
							String tiempoMes = tiemposPorMesPorEmpleado.get(i);
							fila.add(tiempoMes);
							if(!tiempoMes.equals("")){
								sumarTiempos(tiemposTotalesPorMes, i, tiempoMes);
							}
						}
						ordenarEmpleados.add(fila);
					//}
					
					
				}
				
				Collections.sort((ArrayList)ordenarEmpleados,ordenadorPorNombre);
				for(Vector<String> filaTabla : ordenarEmpleados){
					tableModel.addRow(filaTabla);
				}
				
				//agrego ultima fila con totales
				Vector<String> filaTotales = new Vector<String>();
				filaTotales.add("");
				filaTotales.add("TOTAL:");
				for(String filaTotal : tiemposTotalesPorMes){
					filaTotales.add(filaTotal);
				}
				tableModel.addRow(filaTotales);
				
				System.out.println("a ver");
				
			}else{
				SpiritAlert.createAlert("No existe información en el rango de fechas seleccionado.",SpiritAlert.INFORMATION);
			}
			
			
			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarTabla2(){
		try {
			cleanTable();
			tableModel = (DefaultTableModel) getTblTiempos().getModel();
			
			Timestamp fechaInicio = new java.sql.Timestamp(getCmbFechaInicio().getDate().getTime());
			Timestamp fechaFin = new java.sql.Timestamp(getCmbFechaFin().getDate().getTime());
			
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
			
			//se busca todas la actividades filtradas por fechas, y cliente y empleado si se uso esos filtros			
			Collection timetrackerDetalles = SessionServiceLocator.getTimetracker2DetalleSessionService().findTimetracker2DetalleByQueryByFechaInicioAndByFechaFin2(aMap, fechaInicio, fechaFin, idEmpleado, true);
			
			//mapa empleado con su arreglo de meses y tiempos
			Map<Long,Vector<String>> mapaEmpleadoTiempos = new HashMap<Long,Vector<String>>();
			
			//mapa cliente con su arreglo de meses y tiempos
			Map<Long,Vector<String>> mapaClienteTiempos = new HashMap<Long,Vector<String>>();
			
			if(timetrackerDetalles.size() > 0){
				Iterator timetrackerDetallesIt = timetrackerDetalles.iterator();
				while(timetrackerDetallesIt.hasNext()){
					Object[] timetrackerDetalle = (Object[])timetrackerDetallesIt.next();
					Timetracker2DetalleIf timetrackerDetalleIf = (Timetracker2DetalleIf)timetrackerDetalle[0];
					EmpleadoIf empleadoIf = (EmpleadoIf)timetrackerDetalle[3];
					
					ClienteOficinaIf clienteOficina = mapaClienteOficina.get(timetrackerDetalleIf.getClienteOficinaId());
					
					int mes = timetrackerDetalleIf.getFecha().getMonth();
					double tiempo = timetrackerDetalleIf.getTiempo();
					String tiempoTexto = formatoDecimal.format(tiempo);
					
					if(getCbReporteCliente().isSelected()){
						if(mapaClienteTiempos.get(clienteOficina.getId()) == null){
							Vector<String> tiemposPorMesPorCliente = new Vector<String>();
							
							for(int i=0; i<13; i++){
								tiemposPorMesPorCliente.add("");
							}
							
							tiemposPorMesPorCliente.set(mes, tiempoTexto);
							//para el total por empleado
							tiemposPorMesPorCliente.set(12, tiempoTexto);
							mapaClienteTiempos.put(clienteOficina.getId(), tiemposPorMesPorCliente);
							
						}else{
							Vector<String> tiemposPorMesPorCliente = mapaClienteTiempos.get(clienteOficina.getId());						
							sumarTiempos(tiemposPorMesPorCliente, mes, String.valueOf(tiempo));
							//para el total por empleado
							sumarTiempos(tiemposPorMesPorCliente, 12, String.valueOf(tiempo));
							mapaClienteTiempos.put(clienteOficina.getId(), tiemposPorMesPorCliente);
						}
					}else{
						if(mapaEmpleadoTiempos.get(empleadoIf.getId()) == null){
							Vector<String> tiemposPorMesPorEmpleado = new Vector<String>();
							
							for(int i=0; i<13; i++){
								tiemposPorMesPorEmpleado.add("");
							}
							
							tiemposPorMesPorEmpleado.set(mes, tiempoTexto);
							//para el total por empleado
							tiemposPorMesPorEmpleado.set(12, tiempoTexto);
							mapaEmpleadoTiempos.put(empleadoIf.getId(), tiemposPorMesPorEmpleado);
							
						}else{
							Vector<String> tiemposPorMesPorEmpleado = mapaEmpleadoTiempos.get(empleadoIf.getId());						
							sumarTiempos(tiemposPorMesPorEmpleado, mes, String.valueOf(tiempo));
							//para el total por empleado
							sumarTiempos(tiemposPorMesPorEmpleado, 12, String.valueOf(tiempo));
							mapaEmpleadoTiempos.put(empleadoIf.getId(), tiemposPorMesPorEmpleado);
						}
					}					
				}			
				
				//lleno la tabla arreglo de datos para luego ordenarse
				ArrayList<Vector<String>> ordenarEmpleados = new ArrayList<Vector<String>>();
				ArrayList<Vector<String>> ordenarClientes = new ArrayList<Vector<String>>();
				//arreglo de sumas por mes
				Vector<String> tiemposTotalesPorMes = new Vector<String>();
				for(int i=0; i<13; i++){
					tiemposTotalesPorMes.add("");
				}
				
				if(getCbReporteCliente().isSelected()){
					Iterator mapaClienteTiemposIt = mapaClienteTiempos.keySet().iterator();
					while(mapaClienteTiemposIt.hasNext()){
						Long clienteOficinaIdFila = (Long)mapaClienteTiemposIt.next();
						ClienteOficinaIf clienteOficina = mapaClienteOficina.get(clienteOficinaIdFila);
						
						Vector<String> fila = new Vector<String>();
						
						//empleado				
						fila.add(clienteOficina.getDescripcion());
						
						//tiempo designado
						fila.add("");
												
						//totales por mes
						Vector<String> tiemposPorMesPorCliente = mapaClienteTiempos.get(clienteOficinaIdFila);
						
						for(int i=0; i<tiemposPorMesPorCliente.size(); i++){
							String tiempoMes = tiemposPorMesPorCliente.get(i);
							fila.add(tiempoMes);
							if(!tiempoMes.equals("")){
								sumarTiempos(tiemposTotalesPorMes, i, tiempoMes);
							}
						}
						ordenarClientes.add(fila);
					}
					
					Collections.sort((ArrayList)ordenarClientes,ordenadorPorNombre);
					for(Vector<String> filaTabla : ordenarClientes){
						tableModel.addRow(filaTabla);
					}
				}
				else{
					Iterator mapaEmpleadoTiemposIt = mapaEmpleadoTiempos.keySet().iterator();
					while(mapaEmpleadoTiemposIt.hasNext()){
						Long empleadoIdFila = (Long)mapaEmpleadoTiemposIt.next();
						EmpleadoIf empleado = mapaEmpleado.get(empleadoIdFila);
						
						Vector<String> fila = new Vector<String>();
						
						//empleado				
						fila.add(empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0]);
						
						//tiempo designado
						if(clienteOficinaIf != null){
							Map mapaTiempo = new HashMap();
							mapaTiempo.put("empleadoId", empleado.getId());
							mapaTiempo.put("clienteOficinaId", clienteOficinaIf.getId());			
							Collection registros = SessionServiceLocator.getTimetracker2TiempoSessionService().findTimetracker2TiempoByQuery(mapaTiempo);
							if(registros.size() > 0){
								Timetracker2TiempoIf tiempo = (Timetracker2TiempoIf)registros.iterator().next();
								fila.add(formatoDecimal.format(tiempo.getTiempoDesignado()));
							}else{
								fila.add("");
							}
						}else{
							fila.add("");
						}											
						
						//totales por mes
						Vector<String> tiemposPorMesPorEmpleado = mapaEmpleadoTiempos.get(empleadoIdFila);
						
						for(int i=0; i<tiemposPorMesPorEmpleado.size(); i++){
							String tiempoMes = tiemposPorMesPorEmpleado.get(i);
							fila.add(tiempoMes);
							if(!tiempoMes.equals("")){
								sumarTiempos(tiemposTotalesPorMes, i, tiempoMes);
							}
						}
						ordenarEmpleados.add(fila);
					}
					
					Collections.sort((ArrayList)ordenarEmpleados,ordenadorPorNombre);
					for(Vector<String> filaTabla : ordenarEmpleados){
						tableModel.addRow(filaTabla);
					}
				}				
				
				//agrego ultima fila con totales
				Vector<String> filaTotales = new Vector<String>();
				filaTotales.add("");
				filaTotales.add("TOTAL:");
				for(String filaTotal : tiemposTotalesPorMes){
					filaTotales.add(filaTotal);
				}
				tableModel.addRow(filaTotales);
				
				//System.out.println("a ver");
				
			}else{
				SpiritAlert.createAlert("No existe información en el rango de fechas seleccionado.",SpiritAlert.INFORMATION);
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	Comparator<Vector<String>> ordenadorPorNombre = new Comparator<Vector<String>>(){
		public int compare(Vector<String> o1, Vector<String> o2) {
			return o1.get(0).compareTo(o2.get(0));			
		}		
	};
	
	private void sumarTiempos(Vector<String> filaTotales, int columna,
			String tiempoTexto) {
		
		double tiempo = Double.valueOf(tiempoTexto);
		tiempoTexto = formatoDecimal.format(tiempo);
		
		if(tiempo > 0){			
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
		}
	}

	public void clean() {
		getCbTodosEmpleados().setSelected(false);
		getCbReporteEmpleado().setSelected(true);
		getCbReporteCliente().setSelected(false);
		cleanTable();
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblTiempos().getModel();
		for(int i= this.getTblTiempos().getRowCount();i>0;--i){
			model.removeRow(i-1);
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
			if (getTblTiempos().getModel().getRowCount() > 0) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte del Timetracker?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/medios/RPTimetracker2Consolidado.jasper";
										
					HashMap parametrosMap = new HashMap();
					
					if(clienteOficinaIf != null){
						parametrosMap.put("cliente", clienteOficinaIf.getDescripcion());
					}else{
						parametrosMap.put("cliente", "");
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
					
					parametrosMap.put("fechaInicio", Utilitarios.getFechaMesAnioUppercase(getCmbFechaInicio().getDate()));
					parametrosMap.put("fechaFin", Utilitarios.getFechaMesAnioUppercase(getCmbFechaFin().getDate()));
					
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

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
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
		setSaveMode();
		clean();
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
}
