package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.NumberCellRenderer;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.criteria.ContratoCriteria;
import com.spirit.nomina.gui.panel.JPConsultaHojaVida;
import com.spirit.nomina.handler.TipoRol;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class ConsultaHojaVidaModel extends JPConsultaHojaVida {

	private static final long serialVersionUID = -7292916188285266727L;
	private DecimalFormat formatoDosEnteros = new DecimalFormat("00");
	
	private RubroIf rubroIf = null;
	private TipoRolIf tipoRolIf = null; 
	private TipoContratoIf tipoContratoIf = null; 
	private Map<String,Object> mapaParametros = null;
	private EmpleadoIf empleadoIf = null;
	private ContratoIf contratoIf = null;
	Collection<TipoRolIf> tiposRolCollection = null;
	
	private Map<Long,TipoContratoIf> mapaTipoContrato = new HashMap<Long, TipoContratoIf>();
	HashMap<Long, RubroIf> mapaRubros = null;
	
	Collection<Map<String, ?>> informacionReporte = null;
	
	private TipoRol tipoRol; 
	
	private Double total = 0.0;
	
	private static final int COLUMNA_ANIO = 0;
	private static final int COLUMNA_MES = 1;
	private static final int COLUMNA_EMPLEADO = 2;
	private static final int COLUMNA_VALOR = 3;
	private static final int COLUMNA_TIPO_RUBRO = 4;
	private static final int COLUMNA_ESTADO = 5;
	

	public ConsultaHojaVidaModel(){
		iniciarComponentes();
		initListeners();
		showSaveMode();
		new HotKeyComponent(this);
	}
	
	public ConsultaHojaVidaModel(TipoRolIf tipoRolIf,RolPagoIf rolPagoIf,ContratoIf contratoIf) throws GenericBusinessException{
		
		iniciarComponentes();
		initListeners();
		
		setSorterTable(getTblRubro());
		
		tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		if ( tipoRol == null )
			return;
		
		this.contratoIf = contratoIf;
		empleadoIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(contratoIf.getEmpleadoId()); 
		getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
		getTxtContrato().setText(contratoIf.getCodigo());
		
		//showSaveMode();

		//consultaTotalRolPagoContrato();
		
		setSaveMode();
		
		new HotKeyComponent(this);
	}

	private void initListeners(){

		getBtnBuscarEmpleado().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento) {
				try{
					busquedaEmpleado();
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				} catch (Exception e) {
					e.printStackTrace(  );
					SpiritAlert.createAlert("Error en la busqueda de Empleado !!", SpiritAlert.ERROR);
				}
				//verificarSelecciones();
			}			
		});

		getBtnBuscarContrato().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento) {
					busquedaContrato();
			}			
		});

		getBtnConsultar().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eve) {
				consultaGeneralHojaVida();
			}			
		});

	}
	
	@Override
	protected void limpiarTabla(JTable tabla) {
		informacionReporte = new ArrayList<Map<String, ?>>();
		total = 0.0;
		super.limpiarTabla(tabla);
	}
	
	private void consultaGeneralHojaVida() {
		try{

			limpiarTabla(getTblRubro());
			
			if(validateFields()){
				
				consultarHojaVida();
			}
		} catch(Exception e ){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en la búsqueda del Detalle del Rol de Pago", SpiritAlert.ERROR);
		}
	}

	private synchronized void consultarHojaVida() {
		Runnable r = new Runnable(){
			public void run() {
				try {
					setCursorEspera();
					
					total = 0D;
					
					mapaParametros = new HashMap<String, Object>();
					
					tipoRolIf = (TipoRolIf) getCmbTipoRol().getSelectedItem();
					
					tipoContratoIf = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
					
					mapaParametros.put("rubroId", rubroIf.getId());
					
					
					if (contratoIf != null)
						mapaParametros.put("contratoId", contratoIf.getId());
					
					Date fechaInicio = new Date(getCmbFechaInicio().getDate().getTime());
					
					Date fechaFin = null;
					if ( getCmbFechaFin().getDate() != null ){
						fechaFin = new Date(getCmbFechaFin().getDate().getTime());
					}
					
					informacionReporte = new ArrayList<Map<String, ?>>();
					
					DefaultTableModel modelo = (DefaultTableModel) getTblRubro().getModel();
					
					Collection<Object> filas = null;
					
					
					if ( filas != null ){
						for (Object o : filas){
								Object[] fila = crearFilaTablaFondoReserva(o);
								modelo.addRow(fila);
						}
						
						total = Utilitarios.redondeoValor(total);
						
						Collections.sort((ArrayList<Map<String, ?>>)informacionReporte,comparadorNombre);
					} else {
						SpiritAlert.createAlert("No existe informaci\u00f3n !!", SpiritAlert.ERROR);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				} catch(Exception e){
					e.printStackTrace();
					SpiritAlert.createAlert("Error al consultar Rol por Contrato", SpiritAlert.ERROR);
				} finally{
					setCursorNormal();
				}
			}
		};

		Thread t = new Thread(r);
		t.start();
		t = null;
	}	

	
	
	Comparator<Map<String, ?>> comparadorNombre = new Comparator<Map<String,?>>(){
		int contador = 0;
		public int compare(Map<String, ?> o1, Map<String, ?> o2) {
			contador++;
			String nombre1 = (String)o1.get("nombreEmpleado");
			String nombre2 = (String)o2.get("nombreEmpleado");
			if ( nombre1 == null ){
				System.out.println("- "+contador+" - "+o1.toString());
			}
			if ( nombre2 == null ){
				System.out.println("+"+contador+" - "+o2.toString());
			}
			if ( nombre1==null )
				return 0;
			return nombre1.compareTo(nombre2);
		}
	};
	
	private synchronized Object[] crearFilaTablaFondoReserva(Object o) throws GenericBusinessException {
		
		Object[] filaAnterior = (Object[]) o;
		Object[] filaNueva = new Object[6];
		
		
		HashMap<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("nombreEmpleado", filaNueva[2] );
		mapa.put("fechaRol", filaNueva[1]+"/"+filaNueva[0] );
		mapa.put("fechaIngreso", null );
		
		informacionReporte.add(mapa);
		
		return filaNueva;
	}
	
	
	
	private void busquedaEmpleado() throws GenericBusinessException, ParseException {
		
		EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria("de Empleados", Parametros.getIdEmpresa());
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if ( popupFinder.getElementoSeleccionado()!=null ){
			limpiarTabla(getTblRubro());
			empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
			getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
			
			if ( getCmbFechaInicio().getDate() != null ){
				//Calendar calFechaMedia = new GregorianCalendar(anio,mes,1);
				Calendar calFechaMedia = getCmbFechaInicio().getCalendar();
				int mes = calFechaMedia.get(Calendar.DAY_OF_MONTH);
				int anio = calFechaMedia.get(Calendar.YEAR);
				Date fechaMedia = new Date(calFechaMedia.getTime().getTime());
				int diaFinal = calFechaMedia.getActualMaximum(Calendar.DATE);
				calFechaMedia = new GregorianCalendar(anio,mes,diaFinal);
				Date fechaMediaMax = new Date(calFechaMedia.getTime().getTime());
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("fechaMediaContrato", fechaMedia);
				mapa.put("fechaMediaContratoMax", fechaMediaMax);
				//mapa.put("tipocontratoId", tipoContratoIf.getId());
				mapa.put("empleadoId", empleadoIf.getId());
				//mapa.put("estado", "A");
	
				Collection<ContratoIf> contratos  = SessionServiceLocator.getContratoSessionService().findContratoByQueryConFecha(mapa);
				if ( contratos.size() == 1 ){
					contratoIf = contratos.iterator().next();
					getTxtContrato().setText(contratoIf.getCodigo()+" - ("+tipoContratoIf.getNombre()+")" );
				} 
			} else {
				contratoIf = null;
				getTxtContrato().setText("");
			}
		}
		empleadoCriteria = null;
		popupFinder = null;
	}

	private void busquedaContrato() {
		try {
			
			if ( empleadoIf == null ){
				SpiritAlert.createAlert("Debe elegir un empleado !!", SpiritAlert.WARNING);
				return;
			}
			
			if ( getCmbFechaInicio().getDate() == null ){
				SpiritAlert.createAlert("Debe elegir fecha de inicio !!", SpiritAlert.WARNING);
				return;
			}
			
			//long fecha = new GregorianCalendar(anio,mes,ultimoDiaMes).getTime().getTime();
			
			//Calendar calFechaMedia = new GregorianCalendar(anio,mes,1);
			Calendar calFechaMedia = getCmbFechaInicio().getCalendar();
			int mes = calFechaMedia.get(Calendar.DAY_OF_MONTH);
			int anio = calFechaMedia.get(Calendar.YEAR);
			
			Date fechaMedia = new Date(calFechaMedia.getTime().getTime());
			int diaFinal = calFechaMedia.getActualMaximum(Calendar.DATE);
			calFechaMedia = new GregorianCalendar(anio,mes,diaFinal);
			Date fechaMediaMax = new Date(calFechaMedia.getTime().getTime());
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("fechaMediaContrato", fechaMedia);
			mapa.put("fechaMediaContratoMax", fechaMediaMax);
			//mapa.put("tipocontratoId", tipoContratoIf.getId());
			mapa.put("empleadoId", empleadoIf.getId());
			//mapa.put("estado", "A");

			int tamanoLista = SessionServiceLocator.getContratoSessionService().getContratoListSize(mapa);
			if ( tamanoLista > 0 ){
				ContratoCriteria contratoCriteria = new ContratoCriteria("de Contratos");
				contratoCriteria.setQueryBuilded(mapa);
				contratoCriteria.setResultListSize(tamanoLista);

				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	contratoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					limpiarTabla(getTblRubro());
					contratoIf = (ContratoIf) popupFinder.getElementoSeleccionado();
					TipoContratoIf tipoContratoIf = verificarTipoContrato(mapaTipoContrato, contratoIf.getTipocontratoId());
					getTxtContrato().setText(contratoIf.getCodigo()+" - ("+tipoContratoIf.getNombre()+")" );
				}
				contratoCriteria = null;
				popupFinder = null;
			} else
				SpiritAlert.createAlert("No existen contratos activos para el empleado !!",	SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de Contrato", SpiritAlert.WARNING);
		}
	}
	
	private void cargarCmbTipoContrato(){
		try {
			Collection<TipoContratoIf> tiposContratoCollection =  SessionServiceLocator.getTipoContratoSessionService().findTiposContratosUsados(Parametros.getIdEmpresa());
			Vector<TipoContratoIf> tiposContrato = new Vector<TipoContratoIf>(tiposContratoCollection);
			//tiposContrato.add(0,null);
			DefaultComboBoxModel comboModel = new DefaultComboBoxModel(tiposContrato);
			getCmbTipoContrato().setModel(comboModel);
			getCmbTipoContrato().repaint();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la cargar de los tipos de rol", SpiritAlert.WARNING);
			e.printStackTrace();
		};
	}
	
	private void cargarCmbTipoRolCobro(){

		try {
			tiposRolCollection =  SessionServiceLocator.getTipoRolSessionService().findTipoRolByEmpresaId(Parametros.getIdEmpresa());
			Vector<TipoRolIf> tiposRol = new Vector<TipoRolIf>(tiposRolCollection);
			tiposRol.add(0,null);
			DefaultComboBoxModel comboModel = new DefaultComboBoxModel(tiposRol);
			getCmbTipoRol().setModel(comboModel);
			getCmbTipoRol().repaint();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la cargar de los tipos de rol", SpiritAlert.WARNING);
			e.printStackTrace();
		};
	}
	
	private TipoContratoIf verificarTipoContrato(Map<Long, TipoContratoIf> mapaTipoContrato, Long idTipoContrato) throws GenericBusinessException{
		TipoContratoIf tipoContratoIf = mapaTipoContrato.get(idTipoContrato);
		if ( tipoContratoIf == null ){
			tipoContratoIf = SessionServiceLocator.getTipoContratoSessionService().getTipoContrato(idTipoContrato);
			mapaTipoContrato.put(tipoContratoIf.getId(), tipoContratoIf);
		}
		return tipoContratoIf;
	}

	private void accionImprimirRol() {
		report();
	}

	private void iniciarComponentes(){
		
		//COMBOS DE FECHA
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setShowNoneButton(false);
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setEditable(false);
		
		//BOTON BUSQUEDA DE EMPLEADO
		getBtnBuscarEmpleado().setText("");
		getBtnBuscarEmpleado().setToolTipText("Buscar Empleado");
		getBtnBuscarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));

		//BOTON BUSQUEDA DE CONTRATO
		getBtnBuscarContrato().setText("");
		getBtnBuscarContrato().setToolTipText("Buscar Contrato");
		getBtnBuscarContrato().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));

		//Tabla
		TableColumn columna = getTblRubro().getColumnModel().getColumn(COLUMNA_ANIO);
		columna.setPreferredWidth(80);
		columna = getTblRubro().getColumnModel().getColumn(COLUMNA_MES);
		columna.setPreferredWidth(150);
		columna = getTblRubro().getColumnModel().getColumn(COLUMNA_EMPLEADO);
		columna.setPreferredWidth(270);
		columna = getTblRubro().getColumnModel().getColumn(COLUMNA_VALOR);
		columna.setPreferredWidth(100);
		columna = getTblRubro().getColumnModel().getColumn(COLUMNA_TIPO_RUBRO);
		columna.setPreferredWidth(150);
		columna = getTblRubro().getColumnModel().getColumn(COLUMNA_ESTADO);
		columna.setPreferredWidth(150);
		
		cargarCmbTipoRolCobro();
		cargarCmbTipoContrato();
		
		getTblRubro().getColumnModel().getColumn(COLUMNA_VALOR).setCellRenderer(
			new NumberCellRenderer("######.00",NumberCellRenderer.DERECHA) );
	}
	

	@Override
	public boolean validateFields() {

		if ( getCmbTipoRol().getSelectedItem() == null ){
			SpiritAlert.createAlert("Seleccionar el tipo de Rol !!", SpiritAlert.INFORMATION);
			return false;
		}
		
		if ( rubroIf == null){
			SpiritAlert.createAlert("Seleccionar un Rubro !!", SpiritAlert.INFORMATION);
			return false;
		} 
		
		if ( getCmbTipoContrato().getSelectedItem() == null ){
			SpiritAlert.createAlert("Seleccionar un Tipo de Contrato !!", SpiritAlert.INFORMATION);
			return false;
		}
		
		if ( getCmbFechaInicio().getDate() == null ){
			SpiritAlert.createAlert("Seleccionar Fecha de Inicio !!", SpiritAlert.INFORMATION);
			return false;
		}	
		
		return true;
	}

	public void clean() {
		rubroIf = null;
		contratoIf = null;
		getTxtContrato().setText("");
		empleadoIf = null;
		getTxtEmpleado().setText("");
		
		getCmbFechaInicio().setDate(null);
		getCmbFechaFin().setDate(null);
		
		limpiarTabla(getTblRubro());
		
		total = 0.0;
	}

	public void refresh() {
		mapaTipoContrato = new HashMap<Long, TipoContratoIf>();
		mapaRubros = new HashMap<Long, RubroIf>();
		
		cargarCmbTipoRolCobro();
		cargarCmbTipoContrato();
	}
	
	public void delete() {
	}

	public void save() {
	}

	public void showFindMode() {
		showSaveMode();
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}

	public void update() {
	}

	public void report() {
		try {

			if ( rubroIf == null){
				SpiritAlert.createAlert("Debe elgir y consultar un rubro !!", SpiritAlert.WARNING);
				return;
			}
			if ( informacionReporte == null){
				SpiritAlert.createAlert("Debe elgir y consultar un rubro !!", SpiritAlert.WARNING);
				return;
			}
			if ( informacionReporte.size() == 0){
				SpiritAlert.createAlert("No existe detalle para reporte !!", SpiritAlert.WARNING);
				return;
			}
				

			String fileName = "jaspers/nomina/RPConsultaPorRubroFondoReserva.jasper";
			
			HashMap parametrosMap = new HashMap();

			MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("CONSULTA DE RUBROS").iterator().next();
			parametrosMap.put("codigoReporte", menu.getCodigo());
			EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
			parametrosMap.put("empresa", empresa.getNombre());
			parametrosMap.put("ruc", empresa.getRuc());
			parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
			OficinaIf oficina = (OficinaIf) Parametros.getOficina();
			CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
			parametrosMap.put("ciudad", ciudad.getNombre());
			parametrosMap.put("usuario", Parametros.getUsuario());
			
			parametrosMap.put("nombreRubro", rubroIf.getNombre());

			JRDataSource dataSourceDetail = new JRMapCollectionDataSource(informacionReporte);
			
			parametrosMap.put("total", total);

			ReportModelImpl.processReport(fileName, parametrosMap, dataSourceDetail, true);

		} catch ( GenericBusinessException e ) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
		} catch( Exception e ){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al generar Reporte !!", SpiritAlert.WARNING);
		}
	}

}
