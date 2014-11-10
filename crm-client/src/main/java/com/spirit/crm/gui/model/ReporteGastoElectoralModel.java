package com.spirit.crm.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.GastoElectoralAbonoIf;
import com.spirit.crm.entity.GastoElectoralIf;
import com.spirit.crm.gui.panel.JPReporteGastoElectoral;
import com.spirit.crm.session.GastoElectoralAbonoSessionService;
import com.spirit.crm.session.GastoElectoralSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.session.CiudadSessionService;
import com.spirit.general.session.OficinaSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.Utilitarios;

public class ReporteGastoElectoralModel extends JPReporteGastoElectoral {
	
	private List<GastoElectoralIf> gastoElectoralColeccion = new ArrayList<GastoElectoralIf>();
	private List<GastoElectoralAbonoIf> gastoElectoralIngresosColeccion = new ArrayList<GastoElectoralAbonoIf>();
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("###0.00");
	private BigDecimal totalGastoInvConFactura = new BigDecimal(0);
	private BigDecimal totalGastoInvSinFactura = new BigDecimal(0);
	private BigDecimal totalIngresos = new BigDecimal(0);
	private BigDecimal totalEgresos = new BigDecimal(0);
	private List<GastoElectoralReporteData> gastoElectoralReporteColeccion = new ArrayList<GastoElectoralReporteData>();
	private List<GastoElectoralIngresosReporteData> gastoElectoralIngresosReporteColeccion = new ArrayList<GastoElectoralIngresosReporteData>();
	
	public ReporteGastoElectoralModel(){
		anchoColumnasTabla();
		initKeyListeners();
		cargarCombos();
		showSaveMode();
		cleanTable();
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		//setSorterTable(getTblGastos());
		//getTblGastos().getTableHeader().setReorderingAllowed(false);
		
		TableColumn anchoColumna = getTblGastos().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblGastos().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblGastos().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblGastos().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblGastos().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblGastos().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblGastos().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblGastos().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(70);
	}
	
	private void initKeyListeners() {
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaInicio().setCalendar(new GregorianCalendar(2009,1,1));
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setEditable(false);
		getCmbFechaFin().setShowNoneButton(false);
		getCmbFechaFin().setCalendar(new GregorianCalendar(2009,3,30));
		getCbTodaCampana().setSelected(true);
		getCbTodoProveedor().setSelected(true);
		getCbTodoTipo().setSelected(true);
		getCbReporteCampana().setSelected(true);
		getCbReporteIngresos().setSelected(false);
		getCbReporteTipo().setSelected(false);
	}
	
	public void cargarCombos(){
		try {
			Collection gastosElectorales = SessionServiceLocator.getGastoElectoralSessionService().getGastoElectoralList();
			Iterator gastosElectoralesIt = gastosElectorales.iterator();
			Map<String,Long> campanas = new HashMap<String,Long>();
			Map<String,Long> tipos = new HashMap<String,Long>();
			Map<String,Long> proveedores = new HashMap<String,Long>();
			while(gastosElectoralesIt.hasNext()){
				GastoElectoralIf gastoElectoral = (GastoElectoralIf)gastosElectoralesIt.next();
				campanas.put(gastoElectoral.getCampana(),gastoElectoral.getId());
				tipos.put(gastoElectoral.getTipo(),gastoElectoral.getId());
				proveedores.put(gastoElectoral.getProveedor(),gastoElectoral.getId());
			}
			
			Iterator campanasIt = campanas.keySet().iterator();
			List<String> campanasLista = new ArrayList<String>();
			while(campanasIt.hasNext()){
				campanasLista.add((String)campanasIt.next());
			}
			Collections.sort((ArrayList)campanasLista,ordenadorListas);
			refreshCombo(getCmbCampana(),campanasLista);
			
			Iterator tiposIt = tipos.keySet().iterator();
			List<String> tiposLista = new ArrayList<String>();
			while(tiposIt.hasNext()){
				tiposLista.add((String)tiposIt.next());
			}
			Collections.sort((ArrayList)tiposLista,ordenadorListas);
			refreshCombo(getCmbTipo(),tiposLista);
			
			Iterator proveedoresIt = proveedores.keySet().iterator();
			List<String> proveedoresLista = new ArrayList<String>();
			while(proveedoresIt.hasNext()){
				proveedoresLista.add((String)proveedoresIt.next());
			}
			Collections.sort((ArrayList)proveedoresLista,ordenadorListas);
			refreshCombo(getCmbProveedor(),proveedoresLista);
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblGastos().getModel();
		for(int i= this.getTblGastos().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}
	
	Comparator<String> ordenadorListas = new Comparator<String>(){
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}		
	};
	
	public void initListeners(){
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cleanTable();
				cargarTabla();
			}
		});
		
		getCbReporteCampana().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbReporteCampana().isSelected()){
					getCbReporteIngresos().setSelected(false);
					getCbReporteTipo().setSelected(false);
				}
			}
		});
		
		getCbReporteIngresos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbReporteIngresos().isSelected()){
					getCbReporteCampana().setSelected(false);
					getCbReporteTipo().setSelected(false);
				}
			}
		});
		
		getCbReporteTipo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCbReporteTipo().isSelected()){
					getCbReporteCampana().setSelected(false);
					getCbReporteIngresos().setSelected(false);
				}
			}
		});
		
		/*getCbTodaCampana().addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent evento) {
		 		if(getCbTodaCampana().isSelected()){
		 			getCbTodoProveedor().setSelected(false);
		 			getCbTodoTipo().setSelected(false);
		 			}
		     }
		});
	
		getCbTodoProveedor().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if(getCbTodoProveedor().isSelected()){
				getCbTodaCampana().setSelected(false);
				getCbTodoTipo().setSelected(false);
			}
		}
		});
	
		getCbTodoTipo().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if(getCbTodoTipo().isSelected()){
				getCbTodaCampana().setSelected(false);
				getCbTodoProveedor().setSelected(false);
			}
		}
		});*/
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}

	public void showSaveMode() {
		setSaveMode();
	}
	
	public void cargarTabla(){
		try {
			gastoElectoralColeccion = null;
			gastoElectoralColeccion = new ArrayList<GastoElectoralIf>();
			totalGastoInvConFactura = new BigDecimal(0);
			totalGastoInvSinFactura = new BigDecimal(0);
			gastoElectoralIngresosColeccion = new ArrayList<GastoElectoralAbonoIf>();
			gastoElectoralReporteColeccion = new ArrayList<GastoElectoralReporteData>();
			gastoElectoralIngresosReporteColeccion = new ArrayList<GastoElectoralIngresosReporteData>();
			
			String campana = !getCbTodaCampana().isSelected()?(String)getCmbCampana().getSelectedItem():"%";
			String proveedor = !getCbTodoProveedor().isSelected()?(String)getCmbProveedor().getSelectedItem():"%";
			String tipo = !getCbTodoTipo().isSelected()?(String)getCmbTipo().getSelectedItem():"%";
			Map aMap = new HashMap();
			aMap.put("campana",campana);
			aMap.put("proveedor",proveedor);
			aMap.put("tipo",tipo);
			
			Date fechaInicio = null;
			Date fechaFin = null;			
			if(getCmbFechaInicio().getCalendar() != null && getCmbFechaFin().getCalendar() != null){
				fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
				fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());				
			}
			String orderBy = "campana";
			if(getCbReporteTipo().isSelected()){
				orderBy = "tipo";
			}else{
				orderBy = "campana";
			}
			
			Map aMapIngreso = new HashMap();
			aMapIngreso.put("campana",campana);
			gastoElectoralIngresosColeccion = (List)SessionServiceLocator.getGastoElectoralAbonoSessionService().findGastoElectoralIngresoByQueryByFechaInicioAndByFechaFin(aMapIngreso, fechaInicio, fechaFin);
			
			gastoElectoralColeccion = (List)SessionServiceLocator.getGastoElectoralSessionService().findGastoElectoralByQueryByFechaInicioAndByFechaFinOrderBy(aMap, fechaInicio, fechaFin, orderBy);
			Iterator gastoElectoralColeccionIt = gastoElectoralColeccion.iterator();
			
			while(gastoElectoralColeccionIt.hasNext()){
				GastoElectoralIf gastoElectoralIf = (GastoElectoralIf)gastoElectoralColeccionIt.next();
				
				tableModel = (DefaultTableModel) getTblGastos().getModel();
				Vector<String> fila = new Vector<String>();
				
				agregarColumnasTabla(gastoElectoralIf, fila);
				
				tableModel.addRow(fila);
			}		
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}
	
	public void agregarColumnasTabla(GastoElectoralIf gastoElectoralIf, Vector<String> fila){
		fila.add(gastoElectoralIf.getCampana());
		fila.add(Utilitarios.getFechaCortaUppercase(gastoElectoralIf.getFecha()));
		fila.add(gastoElectoralIf.getTipo());
		fila.add(gastoElectoralIf.getProducto()!=null?gastoElectoralIf.getProducto():"");
		fila.add(gastoElectoralIf.getProveedor());
		fila.add(gastoElectoralIf.getCantidad().toString());
		fila.add(gastoElectoralIf.getCostoUnitario()!=null?formatoDecimal.format(gastoElectoralIf.getCostoUnitario()):"");
		fila.add(gastoElectoralIf.getInversionSinFactura()!=null?formatoDecimal.format(gastoElectoralIf.getInversionSinFactura()):"");
		
		if(gastoElectoralIf.getInversionConFactura()!=null){
			totalGastoInvConFactura = totalGastoInvConFactura.add(gastoElectoralIf.getInversionConFactura());
		}
		if(gastoElectoralIf.getInversionSinFactura()!=null){
			totalGastoInvSinFactura = totalGastoInvSinFactura.add(gastoElectoralIf.getInversionSinFactura());
		}
	}
	
	public ArrayList<GastoElectoralReporteData> generarReporteColeccion(){
		totalIngresos = new BigDecimal(0);
		//Primero genero un mapa con el total de ingresos por campaña
		Map<String,BigDecimal> mapaIngresoCampana = new HashMap<String,BigDecimal>();
		for(GastoElectoralAbonoIf gastoElectoralAbonoIf : gastoElectoralIngresosColeccion){
			if(mapaIngresoCampana.get(gastoElectoralAbonoIf.getCampana()) == null){
				mapaIngresoCampana.put(gastoElectoralAbonoIf.getCampana(),gastoElectoralAbonoIf.getValor());
			}else{
				mapaIngresoCampana.put(gastoElectoralAbonoIf.getCampana(),gastoElectoralAbonoIf.getValor().add(mapaIngresoCampana.get(gastoElectoralAbonoIf.getCampana())));
			}
			totalIngresos = totalIngresos.add(gastoElectoralAbonoIf.getValor());
		}
				
		ArrayList<GastoElectoralReporteData> gastoElectoralReporteColeccion = new ArrayList<GastoElectoralReporteData>();
		for(GastoElectoralIf gastoElectoralIf : gastoElectoralColeccion){
			GastoElectoralReporteData gastoElectoralReporteData = new GastoElectoralReporteData();
			gastoElectoralReporteData.setCampana(gastoElectoralIf.getCampana());
			gastoElectoralReporteData.setCantidad(gastoElectoralIf.getCantidad());
			gastoElectoralReporteData.setCostoUnitario(gastoElectoralIf.getCostoUnitario());
			gastoElectoralReporteData.setDescripcion(gastoElectoralIf.getDescripcion());
			gastoElectoralReporteData.setFecha(gastoElectoralIf.getFecha());
			gastoElectoralReporteData.setInversionConFactura(gastoElectoralIf.getInversionConFactura());
			gastoElectoralReporteData.setInversionSinFactura(gastoElectoralIf.getInversionSinFactura());
			gastoElectoralReporteData.setProducto(gastoElectoralIf.getProducto());
			gastoElectoralReporteData.setProveedor(gastoElectoralIf.getProveedor());
			gastoElectoralReporteData.setTamano(gastoElectoralIf.getTamano());
			gastoElectoralReporteData.setTipo(gastoElectoralIf.getTipo());
			
			if(mapaIngresoCampana.get(gastoElectoralIf.getCampana()) == null){
				gastoElectoralReporteData.setTotalIngresoCampana(new BigDecimal(0));
			}else{
				gastoElectoralReporteData.setTotalIngresoCampana(mapaIngresoCampana.get(gastoElectoralIf.getCampana()));
			}
			gastoElectoralReporteColeccion.add(gastoElectoralReporteData);
		}
		return gastoElectoralReporteColeccion;
	}
	
	public ArrayList<GastoElectoralIngresosReporteData> generarIngresosReporteColeccion(){
		totalEgresos = new BigDecimal(0);
		//Primero genero un mapa con el total de egresos por campaña
		Map<String,BigDecimal> mapaEgresosCampana = new HashMap<String,BigDecimal>();
		for(GastoElectoralIf gastoElectoralIf : gastoElectoralColeccion){
			
			BigDecimal totalInversion = new BigDecimal(0);
			if(gastoElectoralIf.getInversionConFactura()!=null && gastoElectoralIf.getInversionSinFactura()!=null){
				totalInversion = gastoElectoralIf.getInversionConFactura().add(gastoElectoralIf.getInversionSinFactura());
			}else if(gastoElectoralIf.getInversionConFactura()!=null){
				totalInversion = gastoElectoralIf.getInversionConFactura();
			}else{
				totalInversion = gastoElectoralIf.getInversionSinFactura();
			}
			
			if(mapaEgresosCampana.get(gastoElectoralIf.getCampana()) == null){
				mapaEgresosCampana.put(gastoElectoralIf.getCampana(),totalInversion);
			}else{
				mapaEgresosCampana.put(gastoElectoralIf.getCampana(),totalInversion.add(mapaEgresosCampana.get(gastoElectoralIf.getCampana())));
			}
			
			totalEgresos = totalEgresos.add(totalInversion);
		}
				
		ArrayList<GastoElectoralIngresosReporteData> gastoElectoralIngresosReporteColeccion = new ArrayList<GastoElectoralIngresosReporteData>();
		for(GastoElectoralAbonoIf gastoElectoralAbonoIf : gastoElectoralIngresosColeccion){
			GastoElectoralIngresosReporteData gastoElectoralIngresosReporteData = new GastoElectoralIngresosReporteData();
			gastoElectoralIngresosReporteData.setCampana(gastoElectoralAbonoIf.getCampana());
			gastoElectoralIngresosReporteData.setFecha(gastoElectoralAbonoIf.getFecha());
			gastoElectoralIngresosReporteData.setEntregadoPor(gastoElectoralAbonoIf.getEntregadoPor());
			gastoElectoralIngresosReporteData.setValor(gastoElectoralAbonoIf.getValor());
						
			if(mapaEgresosCampana.get(gastoElectoralAbonoIf.getCampana()) == null){
				gastoElectoralIngresosReporteData.setTotalEgresoCampana(new BigDecimal(0));
			}else{
				gastoElectoralIngresosReporteData.setTotalEgresoCampana(mapaEgresosCampana.get(gastoElectoralAbonoIf.getCampana()));
			}
			gastoElectoralIngresosReporteColeccion.add(gastoElectoralIngresosReporteData);
		}
		return gastoElectoralIngresosReporteColeccion;
	}
	
	public void report() {
		if(!gastoElectoralColeccion.isEmpty()){
			gastoElectoralReporteColeccion = generarReporteColeccion();
			gastoElectoralIngresosReporteColeccion = generarIngresosReporteColeccion();
			try {
				String si = "Si";
				String no = "No";
				Object[] options = {si,no};
				String mensaje = "¿Desea generar el reporte de Gasto Electoral?";
				int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
				if(opcion == JOptionPane.YES_OPTION) {
					
					HashMap parametrosMap = new HashMap();
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre().substring(0,1).concat(ciudad.getNombre().substring(1, ciudad.getNombre().length()).toLowerCase()));
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)).toLowerCase() + " del " + year + ".-";
					parametrosMap.put("emitido", fechaEmision);					
					Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
					Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
					parametrosMap.put("fechaInicio", Utilitarios.getFechaCortaUppercase(fechaInicio));
					parametrosMap.put("fechaFin", Utilitarios.getFechaCortaUppercase(fechaFin));
					parametrosMap.put("totalIngresos", formatoDecimal.format(totalIngresos));										
					
					String fileName = "jaspers/crm/RPGastoElectoralPorCampana.jasper";
					
					if(getCbReporteCampana().isSelected() || getCbReporteTipo().isSelected()){
						if(getCbReporteTipo().isSelected()){
							fileName = "jaspers/crm/RPGastoElectoralPorTipo.jasper";					
						}else{
							fileName = "jaspers/crm/RPGastoElectoralPorCampana.jasper";	
						}
						
						parametrosMap.put("totalGastoElectoral", formatoDecimal.format(totalGastoInvConFactura.add(totalGastoInvSinFactura)));
						parametrosMap.put("totalSaldo", formatoDecimal.format(totalIngresos.subtract(totalGastoInvConFactura.add(totalGastoInvSinFactura))));
						ReportModelImpl.processReport(fileName, parametrosMap, gastoElectoralReporteColeccion, true);
						
					}else{
						parametrosMap.put("totalEgresos", formatoDecimal.format(totalEgresos));
						parametrosMap.put("totalSaldo", formatoDecimal.format(totalIngresos.subtract(totalEgresos)));
						fileName = "jaspers/crm/RPGastoElectoralIngresos.jasper";	
						ReportModelImpl.processReport(fileName, parametrosMap, gastoElectoralIngresosReporteColeccion, true);
					}					
				}
			} catch (ParseException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
			}
		}else{
			SpiritAlert.createAlert("No existen datos para el reporte", SpiritAlert.ERROR);
		}
	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}
	 
	
	public void refresh() {
		cargarCombos();
	}
	
	/*if(getCbTodaCampana().isSelected() && getCbTodoProveedor().isSelected() && getCbTodoTipo().isSelected()){
	gastoElectoralColeccion = (List)getGastoElectoralSessionService().getGastoElectoralList();
}
else if(getCbTodoProveedor().isSelected() && getCbTodoTipo().isSelected()){
	String campana = (String)getCmbCampana().getSelectedItem();
	gastoElectoralColeccion = (List)getGastoElectoralSessionService().findGastoElectoralByCampaña(campana);
}
else if(getCbTodaCampana().isSelected() && getCbTodoTipo().isSelected()){
	String proveedor = (String)getCmbProveedor().getSelectedItem();
	gastoElectoralColeccion = (List)getGastoElectoralSessionService().findGastoElectoralByProveedor(proveedor);
}
else if(getCbTodaCampana().isSelected() && getCbTodoProveedor().isSelected()){
	String tipo = (String)getCmbTipo().getSelectedItem();
	gastoElectoralColeccion = (List)getGastoElectoralSessionService().findGastoElectoralByTipo(tipo);
}
else if(getCbTodaCampana().isSelected()){
	String proveedor = (String)getCmbProveedor().getSelectedItem();
	String tipo = (String)getCmbTipo().getSelectedItem();
	Map aMap = new HashMap();
	aMap.put("proveedor",proveedor);
	aMap.put("tipo",tipo);
	gastoElectoralColeccion = (List)getGastoElectoralSessionService().findGastoElectoralByQuery(aMap);
}
else if(getCbTodoProveedor().isSelected()){
	String campana = (String)getCmbCampana().getSelectedItem();
	String tipo = (String)getCmbTipo().getSelectedItem();
	Map aMap = new HashMap();
	aMap.put("campaña",campana);
	aMap.put("tipo",tipo);
	gastoElectoralColeccion = (List)getGastoElectoralSessionService().findGastoElectoralByQuery(aMap);
}
else if(getCbTodoTipo().isSelected()){
	String campana = (String)getCmbCampana().getSelectedItem();
	String proveedor = (String)getCmbProveedor().getSelectedItem();
	Map aMap = new HashMap();
	aMap.put("campaña",campana);
	aMap.put("proveedor",proveedor);
	gastoElectoralColeccion = (List)getGastoElectoralSessionService().findGastoElectoralByQuery(aMap);
}
else{
	String campana = (String)getCmbCampana().getSelectedItem();
	String proveedor = (String)getCmbProveedor().getSelectedItem();
	String tipo = (String)getCmbTipo().getSelectedItem();
	Map aMap = new HashMap();
	aMap.put("campaña",campana);
	aMap.put("proveedor",proveedor);
	aMap.put("tipo",tipo);
	gastoElectoralColeccion = (List)getGastoElectoralSessionService().findGastoElectoralByQuery(aMap);
}*/
}
