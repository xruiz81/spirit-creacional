package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.criteria.ContratoCriteria;
import com.spirit.nomina.gui.criteria.RubroCriteria;
import com.spirit.nomina.gui.panel.JPConsultaRubroEventual;
import com.spirit.nomina.gui.util.ModelUtil;
import com.spirit.nomina.handler.EstadoRubroEventual;
import com.spirit.nomina.handler.RolPagoContratoDatos;
import com.spirit.nomina.handler.RubroContratoDatos;
import com.spirit.nomina.handler.TipoRol;
import com.spirit.nomina.handler.TipoRubro;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.Utilitarios;

public class ConsultaRubroEventualModel extends JPConsultaRubroEventual {

	private static final long serialVersionUID = -7292916188285266727L;
	private DecimalFormat formatoDosEnteros = new DecimalFormat("00");
	
	private RubroIf rubroIf = null;
	private String mesSeleccionado = "";
	private String mesDigitosSeleccionado = "";
	private String anioSeleccionado = "";
	
	private String estado = null;
	private TipoRolIf tipoRolCobroIf = null;
	private TipoContratoIf tipoContratoIf = null;
	private Map<String,Object> mapaParametros = null;
	private RolPagoIf rolPagoIf = null;
	private EmpleadoIf empleadoIf = null;
	private ContratoIf contratoIf = null;
	Collection<TipoRolIf> tiposRolCollection = null;
	
	private Map<Long,RolPagoContratoDatos> rolPagoDetalleMapaTodo = null;
	private Map<Long, TipoRolIf> mapaTipoRol = new HashMap<Long, TipoRolIf>();
	
	private Map<Long, RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
	private NumberFormat formatoDosDecimales = new DecimalFormat("'$ '###,##0.00");

	private TipoRol tipoRol; 
	
	private Double total = 0.0;
	
	private static final int COLUMNA_RUBRO = 0;
	private static final int COLUMNA_EMPLEADO = 1;
	private static final int COLUMNA_VALOR = 2;
	private static final int COLUMNA_ESTADO = 3;
	private static final int COLUMNA_FECHA_COBRO = 4;
	private static final int COLUMNA_TIPO_ROL_COBRO = 5;
	private static final int COLUMNA_OBSERVACION = 6;
	

	public ConsultaRubroEventualModel(){
		initKeyListeners();
		iniciarComponentes();
		initListeners();
		showSaveMode();
		new HotKeyComponent(this);
	}
	
	public void initKeyListeners(){
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnConsultar().setToolTipText("Consultar");
	}
	
	public ConsultaRubroEventualModel(TipoRolIf tipoRolIf,RolPagoIf rolPagoIf,ContratoIf contratoIf) throws GenericBusinessException{
		
		iniciarComponentes();
		initListeners();
		
		this.tipoRolCobroIf = tipoRolIf;
		tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		if ( tipoRol == null )
			return;
		getCmbTipoRolCobro().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoRolCobro(), tipoRolIf.getId()));
		getCmbTipoRolCobro().repaint();
		
		this.contratoIf = contratoIf;
		empleadoIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(contratoIf.getEmpleadoId()); 
		getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
		getTxtContrato().setText(contratoIf.getCodigo());
		
		this.rolPagoIf = rolPagoIf;
		String mesSeleccionadoStringNumero = rolPagoIf.getMes();
		int mesSeleccionadoInt = Integer.parseInt( mesSeleccionadoStringNumero );
		mesSeleccionado = Utilitarios.getNombreMes(mesSeleccionadoInt);
		getCmbMes().setSelectedItem(mesSeleccionado);

		anioSeleccionado = rolPagoIf.getAnio();
		getCmbAnio().setSelectedItem(anioSeleccionado);
		
		//showSaveMode();

		//consultaTotalRolPagoContrato();
		
		setSaveMode();
		
		new HotKeyComponent(this);
	}

	private void initListeners(){

		getBtnBuscarRubro().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				buscarRubro();
			}
		});
		
		getCbTodosRubros().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(getCbTodosRubros().isSelected()){
					rubroIf = null; 
					getTxtRubro().setText("");
					limpiarTabla(getTblRolPagoContrato());
				}				
			}
		});		
		
		getCmbTipoRolCobro().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				limpiarTabla(getTblRolPagoContrato());
				//verificarSelecciones();
			}
		});
		
		getCbTodoTipoRol().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(getCbTodoTipoRol().isSelected()){
					tipoRolCobroIf = null;
					getCmbTipoRolCobro().setSelectedItem(null);
					limpiarTabla(getTblRolPagoContrato());
				}				
			}
		});		
		
		getCmbTipoContrato().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				limpiarTabla(getTblRolPagoContrato());
				contratoIf = null;
				getTxtContrato().setText("");
			}
		});

		getCmbMes().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				limpiarTabla(getTblRolPagoContrato());
				//verificarSelecciones();
			}
		});

		getCmbAnio().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				limpiarTabla(getTblRolPagoContrato());
				//verificarSelecciones();
			}
		});
		
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
		
		getCbTodosEmpleados().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(getCbTodosEmpleados().isSelected()){
					contratoIf = null;
					getTxtContrato().setText("");
					empleadoIf = null;
					getTxtEmpleado().setText("");
					limpiarTabla(getTblRolPagoContrato());
				}				
			}
		});	

		getBtnBuscarContrato().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evento) {
					busquedaContrato();
			}			
		});

		getBtnConsultar().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eve) {
				consultaTotalRolPagoRubro();
			}			
		});

	}
	
	@Override
	protected void limpiarTabla(JTable tabla) {
		total = 0.0;
		mostrarTotal();
		super.limpiarTabla(tabla);
	}
	
	private void buscarRubro(){
		RubroCriteria rc = new RubroCriteria(TipoRubro.EVENTUAL);
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(
				Parametros.getMainFrame(),	rc,
				JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if ( popupFinder.getElementoSeleccionado() != null ){
			rubroIf = (RubroIf) popupFinder.getElementoSeleccionado(); 
			getTxtRubro().setText(rubroIf.toString());
			limpiarTabla(getTblRolPagoContrato());
		}
	}
	
	private void consultaTotalRolPagoRubro() {
		try{
			if(validateFields()){
				consultarRolPagoPorRubro();
			}
		} catch(Exception e ){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en la búsqueda del Detalle del Rol de Pago", SpiritAlert.ERROR);
		}
	}

	private void consultarRolPagoPorRubro() {
		Runnable r = new Runnable(){
			public void run() {
				try {
					setCursorEspera();
					limpiarTabla(getTblRolPagoContrato());
					mapaParametros = new HashMap<String, Object>();
					if (rubroIf != null)
						mapaParametros.put("rubroId", rubroIf.getId());
					if (contratoIf != null)
						mapaParametros.put("contratoId", contratoIf.getId());
					if ( estado != null )
						mapaParametros.put("estado", estado);
					if ( tipoRolCobroIf != null )
						mapaParametros.put("tipoRolIdCobro", tipoRolCobroIf.getId());
					
					Collection<Object[]> rubrosEventuales = SessionServiceLocator.getRubroEventualSessionService()
					.findRubroEventualByQueryByMesCobroByAnioCobroByTipoContratoId(
							mapaParametros, mesDigitosSeleccionado, anioSeleccionado
							,tipoContratoIf!= null ? tipoContratoIf.getId():null);
					DefaultTableModel modelo = (DefaultTableModel) getTblRolPagoContrato().getModel();
					for ( Object[] o : rubrosEventuales ){
						Vector<Object> fila = crearFilaTabla(o);
						modelo.addRow(fila);
					}
					mostrarTotal();
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


	private void mostrarTotal(){
		total = Utilitarios.redondeoValor(total);
		getLblCantidadTotal().setText(formatoDosDecimales.format(total));
	}
	
	private RolPagoContratoDatos verificarMapaContrato(Long contratoId) {
		
		RolPagoContratoDatos datosContrato = rolPagoDetalleMapaTodo.get(contratoId);
		if ( datosContrato == null ){
			datosContrato = new RolPagoContratoDatos();
			Collection<RubroContratoDatos> ingresos = new ArrayList<RubroContratoDatos>();
			datosContrato.setIngresosRubro(ingresos);
			Collection<RubroContratoDatos> egresos = new ArrayList<RubroContratoDatos>();
			datosContrato.setEgresosRubro(egresos);
			rolPagoDetalleMapaTodo.put(contratoId, datosContrato);
		}
		return datosContrato;
	}

	private Vector<Object> crearFilaTabla(Object[] o) throws GenericBusinessException {
		Vector<Object> fila = new Vector<Object>();
		
		RubroEventualIf re = (RubroEventualIf) o[0];
		String empleado = (String) o[1];
		String rubroNombre = (String) o[2];
		
		/*String rubroNombre = "";
		if(re.getRubroId() != null){
			RubroIf rubro = SessionServiceLocator.getRubroSessionService().getRubro(re.getRubroId());
			rubroNombre = rubro.getNombre();
		}*/
		
		fila.add(rubroNombre);
		
		fila.add(empleado);
		double valor = Utilitarios.redondeoValor( re.getValor().doubleValue() ); 
		fila.add( valor );
		total += valor;
		
		String estado = re.getEstado();
		EstadoRubroEventual ere = EstadoRubroEventual.getRubroEventualByLetra(estado);
		String nombreEstado = ere.toString();
		/*if (estado.equals(EstadoRubroEventual.EMITIDO.getLetra()) ) 
			estado=EstadoRubroEventual.EMITIDO.toString();
		else if ( estado.equals(EstadoRubroEventual.AUTORIZADO.getLetra()) )
			estado = EstadoRubroEventual.AUTORIZADO.toString();
		else if (estado.equals(EstadoRubroEventual.AUTORIZADO_PARCIAL.getLetra()) ) 
			estado = EstadoRubroEventual.AUTORIZADO_PARCIAL.toString();
		else if (estado.equals(EstadoRubroEventual.PAGADO.getLetra()) ) 
			estado = EstadoRubroEventual.PAGADO.toString();*/
		
		fila.add(nombreEstado);
		String fechaCobro  = Utilitarios.getFechaMesAnioUppercase(re.getFechaCobro());
		fila.add(fechaCobro);
		TipoRolIf tr = verificarTipoRol(re.getTipoRolIdCobro());
		fila.add(tr.getNombre());
		
		fila.add(re.getObservacion());
		
		return fila;
	}

	private TipoRolIf verificarTipoRol(Long tipoRolId) throws GenericBusinessException{
		TipoRolIf tr = mapaTipoRol.get(tipoRolId);
		if ( tr==null ){
			tr = SessionServiceLocator.getTipoRolSessionService().getTipoRol(tipoRolId);
			mapaTipoRol.put(tipoRolId, tr);
		}
		return tr;
	}
	
	private void busquedaEmpleado() throws GenericBusinessException, ParseException {
		EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria("de Empleados", Parametros.getIdEmpresa());
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if ( popupFinder.getElementoSeleccionado()!=null ){
			limpiarTabla(getTblRolPagoContrato());
			empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
			getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
			
			int mes = Utilitarios.getMesInt(mesSeleccionado);
			int anio = Integer.valueOf(anioSeleccionado);
			int ultimoDiaMes = Utilitarios.fechaHoy(mes, anio).getDate();
			long fecha = new GregorianCalendar(anio,mes,ultimoDiaMes).getTime().getTime();
			Date fechaActual = new Date( fecha );

			tipoContratoIf = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
			if ( tipoContratoIf == null ){
				return;
			}
			
			Calendar calFechaMedia = new GregorianCalendar(anio,mes,1);
			Date fechaMedia = new Date(calFechaMedia.getTime().getTime());
			int diaFinal = calFechaMedia.getActualMaximum(Calendar.DATE);
			calFechaMedia = new GregorianCalendar(anio,mes,diaFinal);
			Date fechaMediaMax = new Date(calFechaMedia.getTime().getTime());
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("fechaMediaContrato", fechaMedia);
			mapa.put("fechaMediaContratoMax", fechaMediaMax);
			mapa.put("tipocontratoId", tipoContratoIf.getId());
			mapa.put("empleadoId", empleadoIf.getId());
			//mapa.put("estado", "A");

			Collection<ContratoIf> contratos  = SessionServiceLocator.getContratoSessionService().findContratoByQueryConFecha(mapa);
			if ( contratos.size() == 1 ){
				contratoIf = contratos.iterator().next();
				getTxtContrato().setText(contratoIf.getCodigo());
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
			
			tipoContratoIf = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
			if ( tipoContratoIf == null ){
				SpiritAlert.createAlert("Debe elegir un tipo de Contrato !!", SpiritAlert.INFORMATION);
				return;
			}
			
			if ( empleadoIf == null ){
				SpiritAlert.createAlert("Debe elegir un empleado !!", SpiritAlert.INFORMATION);
				return;
			}
			
			mesSeleccionado = (String)getCmbMes().getSelectedItem();
			int mes = Utilitarios.getMesInt(mesSeleccionado);
			
			anioSeleccionado =(String)getCmbAnio().getSelectedItem();
			int anio = Integer.valueOf(anioSeleccionado);
			
			Calendar calFechaMedia = new GregorianCalendar(anio,mes,1);
			Date fechaMedia = new Date(calFechaMedia.getTime().getTime());
			int diaFinal = calFechaMedia.getActualMaximum(Calendar.DATE);
			calFechaMedia = new GregorianCalendar(anio,mes,diaFinal);
			Date fechaMediaMax = new Date(calFechaMedia.getTime().getTime());
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("fechaMediaContrato", fechaMedia);
			mapa.put("fechaMediaContratoMax", fechaMediaMax);
			mapa.put("tipocontratoId", tipoContratoIf.getId());
			mapa.put("empleadoId", empleadoIf.getId());
			//mapa.put("estado", "A");

			int tamanoLista = SessionServiceLocator.getContratoSessionService().getContratoListSize(mapa);
			if ( tamanoLista > 0 ){
				ContratoCriteria contratoCriteria = new ContratoCriteria("de Contratos");
				contratoCriteria.setQueryBuilded(mapa);
				contratoCriteria.setResultListSize(tamanoLista);

				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	contratoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					limpiarTabla(getTblRolPagoContrato());
					contratoIf = (ContratoIf) popupFinder.getElementoSeleccionado();
					getTxtContrato().setText(contratoIf.getCodigo());
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

	private void accionImprimirRol() {
		report();
	}

	private void iniciarComponentes(){
		
		//SE CARGA LOS ESTADOS DE LOS DETALLES DE ROL DE PAGO POR LOS QUE SE PUEDE BUSCAR
		getCmbEstadoDetalle().addItem(null);
		getCmbEstadoDetalle().addItem(EstadoRubroEventual.EMITIDO);
		getCmbEstadoDetalle().addItem(EstadoRubroEventual.AUTORIZADO);
		getCmbEstadoDetalle().addItem(EstadoRubroEventual.AUTORIZADO_PARCIAL);
		getCmbEstadoDetalle().addItem(EstadoRubroEventual.PAGADO);
		
		//MES
		getCmbMes().setSelectedIndex(0);
		mesSeleccionado = (String) getCmbMes().getSelectedItem();
		
		//COMBO ANIO
		ModelUtil.establecerCmbAnio(getCmbAnio());
		anioSeleccionado = (String)getCmbAnio().getSelectedItem();

		//COMBO TIPO ROL
		ModelUtil.cargarCmbTipoRol(getCmbTipoRolCobro(),null,null);
		getCmbTipoRolCobro().setSelectedItem(null);
		
		//COMBO TIPO DE CONTRATO
		ModelUtil.cargarCmbTipoContrato(getCmbTipoContrato());
		ModelUtil.cargarCmbTipoContrato(getCmbTipoContrato());

		//BOTON BUSQUEDA DE RUBRO
		getBtnBuscarRubro().setText("");
		getBtnBuscarRubro().setToolTipText("Buscar Rubro");
		getBtnBuscarRubro().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		//BOTON BUSQUEDA DE EMPLEADO
		getBtnBuscarEmpleado().setText("");
		getBtnBuscarEmpleado().setToolTipText("Buscar Empleado");
		getBtnBuscarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));

		//BOTON BUSQUEDA DE CONTRATO
		getBtnBuscarContrato().setText("");
		getBtnBuscarContrato().setToolTipText("Buscar Contrato");
		getBtnBuscarContrato().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));

		
		//Tabla
		TableColumn columna = getTblRolPagoContrato().getColumnModel().getColumn(COLUMNA_RUBRO);
		columna.setPreferredWidth(200);
		columna = getTblRolPagoContrato().getColumnModel().getColumn(COLUMNA_EMPLEADO);
		columna.setPreferredWidth(250);
		columna = getTblRolPagoContrato().getColumnModel().getColumn(COLUMNA_VALOR);
		columna.setPreferredWidth(85);
		columna = getTblRolPagoContrato().getColumnModel().getColumn(COLUMNA_ESTADO);
		columna.setPreferredWidth(90);
		columna = getTblRolPagoContrato().getColumnModel().getColumn(COLUMNA_FECHA_COBRO);
		columna.setPreferredWidth(140);
		columna = getTblRolPagoContrato().getColumnModel().getColumn(COLUMNA_TIPO_ROL_COBRO);
		columna.setPreferredWidth(130);
		columna = getTblRolPagoContrato().getColumnModel().getColumn(COLUMNA_OBSERVACION);
		columna.setPreferredWidth(350);
		
		
		getTblRolPagoContrato().getColumnModel().getColumn(COLUMNA_VALOR).setCellRenderer(
			new NumberCellRenderer("######.00",NumberCellRenderer.DERECHA) );
	}
	
	@Override
	public boolean validateFields() {

		/*if ( rubroIf == null && contratoIf == null){
			SpiritAlert.createAlert("Seleccionar minimo un Rubro o un Contrato !!", SpiritAlert.INFORMATION);
			return false;
		} */
		
		if ( getCmbMes().getSelectedItem() == null ){
			SpiritAlert.createAlert("Seleccionar Mes !!", SpiritAlert.INFORMATION);
			return false;
		} else {
			try {
				mesSeleccionado = (String) getCmbMes().getSelectedItem();
				mesDigitosSeleccionado = formatoDosEnteros.format( Utilitarios.getMesInt(mesSeleccionado)+1 );
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Error al obtener Mes !!", SpiritAlert.ERROR);
				return false;
			}
		}

		if ( getCmbAnio().getSelectedItem() == null ){
			SpiritAlert.createAlert("Seleccionar A\u00f1o !!", SpiritAlert.INFORMATION);
			return false;
		} else {
			anioSeleccionado = (String) getCmbAnio().getSelectedItem();
		}
		
		
		if ( getCmbEstadoDetalle().getSelectedItem() == null ){
			estado = null;
		} else {
			try {
				estado = ((EstadoRubroEventual)getCmbEstadoDetalle().getSelectedItem()).getLetra();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			    SpiritAlert.createAlert("Error al obtener Estado !!", SpiritAlert.ERROR);
				return false;
			} 
		}
		
		if ( getCmbTipoRolCobro().getSelectedItem() == null )
			tipoRolCobroIf = null;
		else {
			tipoRolCobroIf = (TipoRolIf) getCmbTipoRolCobro().getSelectedItem();
		}
		
		if ( getCmbTipoContrato().getSelectedItem() == null )
			tipoContratoIf = null;
		else {
			tipoContratoIf = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
		}
		
		return true;
	}

	public void clean() {
		getCmbTipoRolCobro().setSelectedItem(null);
		rubroIf = null;
		getTxtRubro().setText("");
		rolPagoDetalleMapaTodo = null;
		contratoIf = null;
		tipoContratoIf = null;
		getTxtContrato().setText("");
		empleadoIf = null;
		getTxtEmpleado().setText("");
		limpiarTabla(getTblRolPagoContrato());
		
		total = 0.0;
		mostrarTotal();
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
			HashMap parametrosMap = new HashMap();
			
			String fileName = "jaspers/nomina/RPRubrosEventuales.jasper";
			
			ReportModelImpl.processReport(fileName, parametrosMap, (DefaultTableModel)getTblRolPagoContrato().getModel(), true);
			
			/*tipoContratoIf = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
			if ( tipoContratoIf == null ){
				SpiritAlert.createAlert("Seleccionar un tipo de contrato !!", SpiritAlert.INFORMATION);
				return;
			}
			if ( rolPagoIf != null){
				
				if ( rolPagoDetalleMapaTodo==null || rolPagoDetalleMapaTodo.size() == 0){
					SpiritAlert.createAlert("Rol de pago no tiene detalle !!", SpiritAlert.INFORMATION);
					return;
				}

				String fileName = "jaspers/nomina/RPRolPagoContrato.jasper";

				HashMap parametrosMap = new HashMap();

				MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("ESTADO DE CUENTA").iterator().next();
				parametrosMap.put("codigoReporte", menu.getCodigo());
				EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
				parametrosMap.put("empresa", empresa.getNombre());
				parametrosMap.put("ruc", empresa.getRuc());
				parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
				OficinaIf oficina = (OficinaIf) Parametros.getOficina();
				CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
				parametrosMap.put("ciudad", ciudad.getNombre());
				parametrosMap.put("usuario", Parametros.getUsuario());

				parametrosMap.put("mes", mesSeleccionado);
				parametrosMap.put("anio", anioSeleccionado);
				if ( tipoContratoIf.getNombre().contains("PROFESIONAL") ){
					parametrosMap.put("tituloRol","L I Q U I D A C I Ó N");
					parametrosMap.put("nombreTipoRol", tipoRolCobroIf.getNombre().replace("ROL", "").replace("DE", ""));
				}
				else if ( tipoContratoIf.getNombre().contains("DEPENDENCIA") ){
					parametrosMap.put("tituloRol","R O L   D E   P A G O  P O R   C O N T R A T O");
					parametrosMap.put("nombreTipoRol", tipoRolCobroIf.getNombre());
				}
				
				if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) ){
					parametrosMap.put("pathSubreportEgresos", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/nomina/RPRolPagoContratoDetalleEgresos.jasper");
					parametrosMap.put("pathSubreportIngresos", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/nomina/RPRolPagoContratoDetalleIngresos.jasper");
				}
				else 
					throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
				
				Collection contratoCollection = transformarContratoCollection();
				JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(contratoCollection);
				//parametrosMap.put("contratosCollection", dataSourceDetail);

				ReportModelImpl.processReport(fileName, parametrosMap, dataSourceDetail, true);
				
				contratoCollection = null;
				//rolPagoDetalleCollectionIngresos = null;
				//rolPagoDetalleCollectionEgresos = null;
			}
		} catch ( GenericBusinessException e ) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);*/
		} catch( Exception e ){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al generar Reporte !!", SpiritAlert.WARNING);
		}
	}
	
	private Collection transformarContratoCollection(){
		Collection<RolPagoContratoDatos> coleccion = new ArrayList<RolPagoContratoDatos>();
		if (rolPagoDetalleMapaTodo!=null){
			Iterator<Long> itMapa = rolPagoDetalleMapaTodo.keySet().iterator();
			while( itMapa.hasNext() ){
				Long contratoId = itMapa.next();
				
				RolPagoContratoDatos datoContrato = rolPagoDetalleMapaTodo.get(contratoId);
				
				//Map<String, Object> mapaTransformado = new HashMap<String, Object>();
				
				//clonarMapa(mapa, mapaTransformado);
				
				Collection<RubroContratoDatos> ingresos = datoContrato.getIngresosRubro();
				JRBeanCollectionDataSource ingresosBeans = new JRBeanCollectionDataSource(ingresos);
				datoContrato.setIngresos(ingresosBeans);
				//datoContrato.setIngresosRubro(null);
				ingresos = null;
				
				Collection<RubroContratoDatos> egresos = datoContrato.getEgresosRubro();
				JRBeanCollectionDataSource egresosBeans = new JRBeanCollectionDataSource(egresos);
				datoContrato.setEgresos(egresosBeans);
				//datoContrato.setEgresosRubro(null);
				egresos = null;
				
				coleccion.add(datoContrato);
			}
			Collections.sort((ArrayList<RolPagoContratoDatos>)coleccion, comparadorNombre);
		}
		return coleccion;
	}
	
	Comparator<RolPagoContratoDatos> comparadorNombre = new Comparator<RolPagoContratoDatos>(){
		public int compare(RolPagoContratoDatos o1, RolPagoContratoDatos o2) {
			return o1.getNombreEmpleado().compareTo(o2.getNombreEmpleado());
		}
	};
	

}
