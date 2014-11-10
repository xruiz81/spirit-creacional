package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.nomina.entity.ContratoData;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.ContratoPlantillaDetalleIf;
import com.spirit.nomina.entity.ContratoPlantillaIf;
import com.spirit.nomina.entity.ContratoRubroData;
import com.spirit.nomina.entity.ContratoRubroIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.criteria.ContratoCriteria;
import com.spirit.nomina.gui.panel.JPContrato;
import com.spirit.nomina.gui.util.NominaUtil;
import com.spirit.nomina.handler.EstadoContrato;
import com.spirit.nomina.handler.ModoOperacionRubro;
import com.spirit.nomina.handler.NominaParametros;
import com.spirit.nomina.handler.TipoRubro;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class ContratoModel extends JPContrato {
	
	private static final long serialVersionUID = -9109052284536456346L;
	
	private final int COLUMNA_RUBRO_SELECCION = 0;
	private final int COLUMNA_RUBRO_NOMBRE = 1;
	private final int COLUMNA_RUBRO_TIPO_RUBRO = 2;
	
	private final int COLUMNA_RUBRO_CONTRATO_NOMBRE = 0;
	private final int COLUMNA_RUBRO_CONTRATO_TIPO_RUBRO = 1;
	private final int COLUMNA_RUBRO_CONTRATO_VALOR = 2;
	
	private static final int MAX_LONGITUD_CODIGO = 18;
	private static final int MAX_LONGITUD_OBSERVACION = 100;
	private static final int MAX_LONGITUD_VALOR = 10;
	private static final int MAX_LONGITUD_NUMERO_PAGOS = 2;
	private static final int MAX_LONGITUD_TXTVALOR = 15;
	private static final int MAX_LONGITUD_CANTIDAD = 5;
	
	//private static final String MODO_OPERACION_REGISTRADO = "R";
	//private static final String MODO_OPERACION_CALCULADO = "C";
	
	private EmpleadoCriteria empleadoCriteria;
	private EmpleadoIf empleado;
	private DefaultTableModel tableModelRubro;
	private Vector<RubroIf> rubrosColeccion = new Vector<RubroIf>();
	
	//private Map<RubroIf,BigDecimal> mapaRubrosContrato = null;
	
	private Map<Long,RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
	//private Map<String,RubroIf> mapaRubroPorNombre = new HashMap<String, RubroIf>();
	
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private ContratoIf contratoIf;
	private Collection<RubroIf> rubrosContratoRemovidos = new ArrayList<RubroIf>();
	private ArrayList<ContratoRubroIf> contratosRubroColeccion = new ArrayList<ContratoRubroIf>();
	private Calendar calendarFechaInicio = new GregorianCalendar();
	private Calendar calendarFechaFin = new GregorianCalendar();
	private Collection<TipoRolIf> tipoRolCollection = null;
	
	public ContratoModel(){
		iniciarComponentes();
		anchoColumnasTabla();
		initKeyListeners();
		cargarCombos();
		showSaveMode();
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void iniciarComponentes(){
		//COMBOS DE FECHA
		getCmbFechaElaboracion().setLocale(Utilitarios.esLocale);
		getCmbFechaElaboracion().setShowNoneButton(false);
		getCmbFechaElaboracion().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaElaboracion().setEditable(false);
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setShowNoneButton(false);
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setEditable(false);
		
		//SE CARGAN LOS TIPOS DE ROL DE LA EMPRESA
		Map<String, Object> mapa =  new HashMap<String, Object>();
		mapa.put("empresaId", Parametros.getIdEmpresa());
		mapa.put("rubroEventual", "S");
		try {
			tipoRolCollection = SessionServiceLocator.getTipoRolSessionService().findTipoRolByQuery(mapa);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		getBtnEmpleado().setToolTipText("Buscar Empleado");
		getBtnEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getBtnAgregarRubroContrato().setText("");
		getBtnAgregarRubroContrato().setToolTipText("Agregar Rubro Contrato");
		getBtnAgregarRubroContrato().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnRemoverRubroContrato().setText("");
		getBtnRemoverRubroContrato().setToolTipText("Eliminar Rubro Contrato");
		getBtnRemoverRubroContrato().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		
		getCmbFechaInicioRubro().setLocale(Utilitarios.esLocale);
		getCmbFechaInicioRubro().setShowNoneButton(true);
		getCmbFechaInicioRubro().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaInicioRubro().setEditable(false);
		getCmbFechaInicioRubro().setEnabled(false);
		
		getCmbFechaFinRubro().setLocale(Utilitarios.esLocale);
		getCmbFechaFinRubro().setShowNoneButton(true);
		getCmbFechaFinRubro().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaFinRubro().setEditable(false);
		getCmbFechaFinRubro().setEditable(false);
		getCmbFechaFinRubro().setEnabled(false);
	}
	
	private void anchoColumnasTabla() {
		getTblRubro().getTableHeader().setReorderingAllowed(false);
		getTblRubroContrato().getTableHeader().setReorderingAllowed(false);
		getTblRubroContrato().setCellSelectionEnabled(true);
		
		getTblRubro().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblRubroContrato().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		TableColumn anchoColumnaRubroContrato = getTblRubroContrato().getColumnModel().getColumn(COLUMNA_RUBRO_CONTRATO_NOMBRE);
		anchoColumnaRubroContrato.setPreferredWidth(240);
		anchoColumnaRubroContrato = getTblRubroContrato().getColumnModel().getColumn(COLUMNA_RUBRO_CONTRATO_TIPO_RUBRO);
		anchoColumnaRubroContrato.setPreferredWidth(90);
		anchoColumnaRubroContrato = getTblRubroContrato().getColumnModel().getColumn(COLUMNA_RUBRO_CONTRATO_VALOR);
		anchoColumnaRubroContrato.setPreferredWidth(90);
		anchoColumnaRubroContrato = getTblRubroContrato().getColumnModel().getColumn(3);
		anchoColumnaRubroContrato.setPreferredWidth(90);
		anchoColumnaRubroContrato = getTblRubroContrato().getColumnModel().getColumn(4);
		anchoColumnaRubroContrato.setPreferredWidth(90);
		
		TableColumn anchoColumnaRubro = getTblRubro().getColumnModel().getColumn(COLUMNA_RUBRO_SELECCION);
		anchoColumnaRubro.setPreferredWidth(50);
		anchoColumnaRubro.setMaxWidth(60);
		anchoColumnaRubro.setMinWidth(50);
		anchoColumnaRubro = getTblRubro().getColumnModel().getColumn(COLUMNA_RUBRO_NOMBRE);
		anchoColumnaRubro.setPreferredWidth(300);
		anchoColumnaRubro = getTblRubro().getColumnModel().getColumn(COLUMNA_RUBRO_TIPO_RUBRO);
		anchoColumnaRubro.setPreferredWidth(350);
		
		//((DefaultCellEditor)getTblRubroContrato().getDefaultEditor(String.class)).setClickCountToStart(1);
		TableColumn txtColumna = getTblRubroContrato().getColumnModel().getColumn(2);
		JTextField txtCell = new JTextField();
		txtCell.addKeyListener(new TextChecker(MAX_LONGITUD_TXTVALOR));
		txtCell.addKeyListener(new NumberTextFieldDecimal());
		txtColumna.setCellEditor(new DefaultCellEditor(txtCell));
		((DefaultCellEditor)getTblRubroContrato().getCellEditor(0,2)).setClickCountToStart(1);
		getTblRubroContrato().setSurrendersFocusOnKeystroke(true);
	}
	
	public void initKeyListeners(){
		getCmbFechaElaboracion().setEnabled(false);
		getTxtEmpleado().setEditable(false);
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtObservacion().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACION));
		getTxtFondoReservaDiasPrevios().addKeyListener(new NumberTextField());
		getTxtFondoReservaDiasPrevios().addKeyListener(new TextChecker(MAX_LONGITUD_CANTIDAD));
	}
	
	public void cargarCombos(){
		//ModelUtil.cargarCmbTipoContrato(getCmbTipoContrato());
		cargarComboTipoContrato();
		cargarComboEstado();
	}
	
	public void cargarComboTipoContrato(){
		try {
			List tiposContrato = (List) SessionServiceLocator.getTipoContratoSessionService().findTipoContratoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTipoContrato(), tiposContrato);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarComboEstado(){
		List<EstadoContrato> estados = new ArrayList<EstadoContrato>();
		for (EstadoContrato ec :EstadoContrato.values()){
			estados.add(ec);
		}
		refreshCombo(getCmbEstado(), estados);
	}
	
	//Saca de la tabla rubros los rubros que ya estan en el contrato
	public void cleanSeleccionTablaRubro(Collection<ContratoRubroIf> rubroCollection){
		
		//for (RubroIf rubro : mapaRubrosContrato.keySet()){
		if (rubroCollection != null){
			for (ContratoRubroIf cr : rubroCollection){
				for(int i=0; i<rubrosColeccion.size(); i++){
					RubroIf rubroTemp = rubrosColeccion.get(i);
					if(rubroTemp.getId().compareTo(cr.getRubroId()) == 0){
						rubrosColeccion.remove(i);	
					}
				}					
			}
		}
		limpiarTabla(getTblRubro());
		cargarFilasTablaRubro();
	}
	
	private void initListeners() {
		getCbTemporal().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					if(getCbTemporal().isSelected()){
						getCmbFechaInicioRubro().setEnabled(true);
						getCmbFechaFinRubro().setEnabled(true);
						getCmbFechaInicioRubro().setDate(Utilitarios.dateHoy());
						getCmbFechaFinRubro().setDate(Utilitarios.dateHoy());
					}else{
						getCmbFechaInicioRubro().setEnabled(false);
						getCmbFechaFinRubro().setEnabled(false);
						getCmbFechaInicioRubro().setDate(null);
						getCmbFechaFinRubro().setDate(null);
					}
				}catch (ParseException e) {
					e.printStackTrace();
				}			
			}
		});
		
		getCmbFechaInicio().addActionListener(oActionListenerCmbFechaInicio);
		getCmbFechaFin().addActionListener(oActionListenerCmbFechaFin);
		
		getBtnAgregarRubroContrato().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				//generarMapaRubrosContratoMap();
				Collection<RubroIf> rubrosSeleccionados = getRubrosSeleccionados();
				if( rubrosSeleccionados.size() > 0 ){
					agregarRubroContrato(rubrosSeleccionados);
					cleanSeleccionTablaRubro(contratosRubroColeccion);
					cargarTablaRubroContrato();
				}else{
					SpiritAlert.createAlert("Debe seleccionar al menos un Rubro!", SpiritAlert.WARNING);
				}
			}
		});
		
		getBtnRemoverRubroContrato().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarRubroContrato();
			}
		});
		
		getBtnEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoCriteria = new EmpleadoCriteria("de Empleados", Parametros.getIdEmpresa());
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					empleado = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getTxtEmpleado().setText(empleado.getNombres() + " " + empleado.getApellidos());
				}
				popupFinder = null;
			}
		});
		
		getCmbTipoContrato().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					cargarContratoPantillaDetalle();
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				} 
			}}
		);
	}
	
	private Collection<RubroIf> getRubrosSeleccionados(){
		Collection<RubroIf> rubrosSeleccionados = new ArrayList<RubroIf>();
		for ( int i = 0 ; i < getTblRubro().getRowCount() ; i++ ){
			boolean seleccionado = (Boolean) getTblRubro().getValueAt(i, COLUMNA_RUBRO_SELECCION);
			if ( seleccionado ){
				rubrosSeleccionados.add( rubrosColeccion.get(i) );
			}
		}
		return rubrosSeleccionados;
	}
	
	ActionListener oActionListenerCmbFechaInicio = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if(getCmbFechaInicio().getCalendar() != null){
				calendarFechaInicio = getCmbFechaInicio().getCalendar();
				if(calendarFechaInicio.after(calendarFechaFin)){
					getCmbFechaFin().setCalendar(calendarFechaInicio);
				}
			}
		}
	};
	
	ActionListener oActionListenerCmbFechaFin = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if(getCmbFechaFin().getCalendar() != null){
				calendarFechaFin = getCmbFechaFin().getCalendar();
				if(calendarFechaFin.before(calendarFechaInicio)){
					getCmbFechaInicio().setCalendar(calendarFechaFin);
				}
			}
		}
	};
	
	public void agregarRubroContrato(Collection<RubroIf> rubrosSeleccionados){
		DefaultTableModel tableModelRubroContrato = (DefaultTableModel) getTblRubroContrato().getModel();
		if (rubrosSeleccionados != null){
			for(RubroIf rubro : rubrosSeleccionados){
				ContratoRubroIf cr = crearContratoRubroData(null, rubro);
				contratosRubroColeccion.add(cr);
			}
		}	
	}

	private Vector<String> crearFilaRubroContrato(ContratoRubroIf cr) {
		RubroIf rubro = mapaRubros.get(cr.getRubroId());
		BigDecimal valor = cr.getValor();
		
		Vector<String> filaRubroContrato = new Vector<String>();			
		filaRubroContrato.add( getNombreRubroParaTablaRubroContrato(rubro) );

		try{
			String letraTipoRubro = rubro.getTipoRubro();
			TipoRubro tipoRubro = TipoRubro.getTipoRubroByLetra(letraTipoRubro);
			filaRubroContrato.add(tipoRubro.toString());
		} catch(GenericBusinessException ex){
			filaRubroContrato.add("");
		}

		if(rubro.getModoOperacion().equals(ModoOperacionRubro.REGISTRADO.getLetra())){
			if ( valor == null ) {
				cr.setValor(BigDecimal.ZERO);
				filaRubroContrato.add("0.00");
			} else{
				filaRubroContrato.add(String.valueOf(valor.doubleValue()));
			}
				
		}else{
			filaRubroContrato.add("CALCULADO");
		}
		
		if(cr.getFechaInicio() != null){
			filaRubroContrato.add(Utilitarios.getFechaMesAnioUppercase(cr.getFechaInicio()));
			System.out.println(cr.getFechaInicio());
		}else{
			filaRubroContrato.add("");
		}
		
		if(cr.getFechaFin() != null){
			filaRubroContrato.add(Utilitarios.getFechaMesAnioUppercase(cr.getFechaFin()));
			System.out.println(cr.getFechaFin());
		}else{
			filaRubroContrato.add("");
		}
		
		return filaRubroContrato;
	}
	
	public void actualizarValoresContratoRubroColleccion(){
		try{
			for(int fila=0; fila<getTblRubroContrato().getModel().getRowCount(); fila++){
				fila = getTblRubroContrato().convertRowIndexToModel(fila);
				//String nombre = (String)getTblRubroContrato().getValueAt(fila, COLUMNA_RUBRO_CONTRATO_NOMBRE);
				String valorS = getTblRubroContrato().getValueAt(fila, COLUMNA_RUBRO_CONTRATO_VALOR).toString();
				ContratoRubroIf cr = contratosRubroColeccion.get(fila);
				if( valorS != null){
					if(!valorS.equals("CALCULADO")){
						valorS = Utilitarios.removeDecimalFormat(valorS);
						BigDecimal valor = BigDecimal.valueOf(Double.valueOf(valorS));
						cr.setValor(valor);
					}
				}		
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error, el formato de uno de los valores puede ser incorrecto !!", SpiritAlert.ERROR);
		}
	}
	
	public void eliminarRubroContrato(){
		DefaultTableModel tableModelRubroContrato = (DefaultTableModel) getTblRubroContrato().getModel();
		int filaSeleccionada = getTblRubroContrato().getSelectedRow(); 
		if( filaSeleccionada != -1){
			filaSeleccionada = getTblRubroContrato().convertRowIndexToModel(filaSeleccionada);
			//String nombre = (String)getTblRubroContrato().getValueAt(filaSeleccionada, COLUMNA_RUBRO_CONTRATO_NOMBRE);
			//RubroIf rubro = (RubroIf)mapaRubroPorNombre.get(nombre);
			ContratoRubroIf cr = contratosRubroColeccion.get(filaSeleccionada);
			RubroIf rubro = mapaRubros.get(cr.getRubroId());
			rubrosColeccion.add(rubro);
			if(rubro.getId() != null){
				rubrosContratoRemovidos.add(rubro);
			}				
			removerRubroDeMapaRubroContrato(rubro);
			//tableModelRubroContrato.removeRow(filaSeleccionada);
			cargarTablaRubroContrato();
			limpiarTabla(getTblRubro());
			cargarFilasTablaRubro();
		}else{
			SpiritAlert.createAlert("Debe seleccionar una fila en la tabla!", SpiritAlert.WARNING);
		}
				
	}
	
	private void removerRubroDeMapaRubroContrato(RubroIf rubro){
		Iterator<ContratoRubroIf> itRubros = contratosRubroColeccion.iterator();
		while( itRubros.hasNext() ){
			ContratoRubroIf cr = itRubros.next();
			if ( rubro.getId().equals(cr.getRubroId()) )
				itRubros.remove();
		}
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		Long empleadoId = 0L;
		
		if (!getTxtCodigo().getText().equals(""))
			aMap.put("codigo", getTxtCodigo().getText() + "%");
		else
			aMap.put("codigo", "%");
		
		if (getCmbTipoContrato().getSelectedIndex() != -1)
			aMap.put("tipocontratoId", ((TipoContratoIf)getCmbTipoContrato().getSelectedItem()).getId());
		
		if ((empleado != null) && !getTxtEmpleado().getText().equals("")) {
			empleadoId = empleado.getId();
			aMap.put("empleadoId", empleadoId);
		}
		
		if (getCmbEstado().getSelectedIndex() != -1) {
			EstadoContrato ec = (EstadoContrato) getCmbEstado().getSelectedItem();
			try {
				aMap.put("estado", ec.getLetra());
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		if (getCmbFechaInicio().getSelectedItem() != null)
			aMap.put("fechaInicio", new java.sql.Date(getCmbFechaInicio().getDate().getTime()));

		if (getCmbFechaFin().getSelectedItem() != null)
			aMap.put("fechaFin", new java.sql.Date(getCmbFechaFin().getDate().getTime()));
				
		return aMap;
	}

	public void find() {
		try {			
			Map mapa = buildQuery();
			if ( mapa==null ){
				SpiritAlert.createAlert("Error en estado de Contato !!", SpiritAlert.WARNING);
				return;
			}
			int tamanoLista = SessionServiceLocator.getContratoSessionService().getContratoListSize(mapa);
			if (tamanoLista > 0) {
				try {
					Vector<Integer> anchoColumnasBusqueda = new Vector<Integer>();
					anchoColumnasBusqueda.add(70);
					anchoColumnasBusqueda.add(230);
					anchoColumnasBusqueda.add(150);
					anchoColumnasBusqueda.add(50);
					
					ContratoCriteria contratoCriteria = new ContratoCriteria();
					contratoCriteria.setResultListSize(tamanoLista);
					contratoCriteria.setQueryBuilded(mapa);
					JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), contratoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnasBusqueda, null);
					if (popupFinder.getElementoSeleccionado() != null){
						getSelectedObject(popupFinder.getElementoSeleccionado());
					}
					popupFinder = null;
				} catch(Exception e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en la búsqueda de información", SpiritAlert.ERROR);
		}
	}
	
	private void getSelectedObject(Object contratoSeleccionado){		
		try {
			//GENERAL
			contratoIf = (ContratoIf) contratoSeleccionado;
			getTxtCodigo().setText(contratoIf.getCodigo());
			empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(contratoIf.getEmpleadoId());
			getTxtEmpleado().setText(empleado.getNombres() + " " + empleado.getApellidos());
			getCmbFechaElaboracion().setDate(contratoIf.getFechaElaboracion());
			getCmbFechaInicio().setDate(contratoIf.getFechaInicio());
			getCmbFechaFin().setDate(contratoIf.getFechaFin());
			String estado = contratoIf.getEstado();
			getCmbEstado().setSelectedItem(EstadoContrato.getEstadoContratoByLetra(estado));
			
			if(contratoIf.getFondoReservaDiasPrevios() != null)
				getTxtFondoReservaDiasPrevios().setText(contratoIf.getFondoReservaDiasPrevios().toString());
					
			if(contratoIf.getObservacion() != null){
				getTxtObservacion().setText(contratoIf.getObservacion());
			}
			
			//RUBROS POR CONTRATO
			cargarTablaRubro();
			cargarActualizacionContratoRubroColleccion();
			cleanSeleccionTablaRubro(contratosRubroColeccion);
			cargarTablaRubroContrato();
			
			
			//Se cargar aplica el valor al combo tipo de contrato al final para que ya este lleno el mapa de Rubros por Contratos
			getCmbTipoContrato().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoContrato(), contratoIf.getTipocontratoId()));
			getCmbTipoContrato().repaint();
			
			//agregarRubroContrato(null);
			//RUBROS EVENTUALES
			//cargarActualizacionRubrosEventualesColeccion();
			
			showUpdateMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar el contrato seleccionado", SpiritAlert.ERROR);
		}
	}
	
	private void cargarTablaRubroContrato(){
		limpiarTabla(getTblRubroContrato());
		DefaultTableModel tableModelRubroContrato = (DefaultTableModel) getTblRubroContrato().getModel();
		for (ContratoRubroIf cr : contratosRubroColeccion){
			Vector<String> filaRubroContrato = crearFilaRubroContrato(cr);						
			tableModelRubroContrato.addRow(filaRubroContrato);
		}
	}
	
	public void cargarActualizacionContratoRubroColleccion(){
		try {
			if (contratoIf != null)
				contratosRubroColeccion = (ArrayList<ContratoRubroIf>) SessionServiceLocator.getContratoRubroSessionService().findContratoRubroByContratoId(contratoIf.getId());
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
	}
	
	public ContratoIf registrarContrato() throws GenericBusinessException{
		ContratoData dataContrato = new ContratoData();
		dataContrato.setCodigo(getTxtCodigo().getText());
		TipoContratoIf tipoContrato = (TipoContratoIf)getCmbTipoContrato().getSelectedItem();
		dataContrato.setTipocontratoId(tipoContrato.getId());
		dataContrato.setEmpleadoId(empleado.getId());
		Date fechaElaboracion = getCmbFechaElaboracion().getDate();
		dataContrato.setFechaElaboracion(new java.sql.Date(fechaElaboracion.getTime()));
		Date fechaInicio = getCmbFechaInicio().getDate();
		//INI_CAMBIO_20140627
		//Se estandariza fecha de ingreso hasta el dia 30 en caso de que el inicio de 
		//contrato se de en dia 31
		if (fechaInicio.getDate()==31){
			fechaInicio.setDate(30);
		}
				
		dataContrato.setFechaInicio(new java.sql.Date(fechaInicio.getTime()));
		Date fechaFin = getCmbFechaFin().getDate();
		//Se estandariza la fecha fin a 30 dias en caso de que el fin de contrato sea 
		//en dia 31
		if (fechaFin.getDate()==31){
			fechaFin.setDate(30);
		}
		//FIN_CAMBIO_20140627
		
		dataContrato.setFechaFin(new java.sql.Date(fechaFin.getTime()));
		EstadoContrato ec = (EstadoContrato)getCmbEstado().getSelectedItem();
		dataContrato.setEstado(ec.getLetra());
		dataContrato.setObservacion(getTxtObservacion().getText());
		if(getTxtFondoReservaDiasPrevios().getText() != null && !getTxtFondoReservaDiasPrevios().getText().trim().equals(""))
			dataContrato.setFondoReservaDiasPrevios(Integer.valueOf(getTxtFondoReservaDiasPrevios().getText()));
		
		return dataContrato;
	}
	
	public ContratoIf registrarContratoParaActualizar() throws GenericBusinessException{
		contratoIf.setCodigo(getTxtCodigo().getText());
		TipoContratoIf tipoContrato = (TipoContratoIf)getCmbTipoContrato().getSelectedItem();
		contratoIf.setTipocontratoId(tipoContrato.getId());
		contratoIf.setEmpleadoId(empleado.getId());
		Date fechaElaboracion = getCmbFechaElaboracion().getDate();
		contratoIf.setFechaElaboracion(new java.sql.Date(fechaElaboracion.getTime()));
		Date fechaInicio = getCmbFechaInicio().getDate();
		//INI_CAMBIO_20140708 Si se actualiza la fecha de inicio de contrato con valor 31 esta
		//se estandariza a 30 dias
		if(fechaInicio.getDate()==31){
			contratoIf.setFechaInicio(new java.sql.Date(fechaInicio.getTime()-1));
		}else
			contratoIf.setFechaInicio(new java.sql.Date(fechaInicio.getTime()));
		
		Date fechaFin = getCmbFechaFin().getDate();
		//INI_CAMBIO_20140708 Si se actualiza la fecha de fin de contrato con valor 31 esta se
		//estandariza a 30 dias	
		if(fechaFin.getDate()==31){
			contratoIf.setFechaFin(new java.sql.Date(fechaFin.getTime()-1));
		}else
			contratoIf.setFechaFin(new java.sql.Date(fechaFin.getTime()));
		//FIN_CAMBIO_20140708
		EstadoContrato ec = (EstadoContrato)getCmbEstado().getSelectedItem();
		contratoIf.setEstado(ec.getLetra());
		
		/*if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO)){
			contratoIf.setEstado(ESTADO_ACTIVO);
		}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO)){
			contratoIf.setEstado(ESTADO_INACTIVO);
		}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_TERMINADO)){
			contratoIf.setEstado(ESTADO_TERMINADO);
		}*/
		contratoIf.setObservacion(getTxtObservacion().getText());
		if(getTxtFondoReservaDiasPrevios().getText() != null && !getTxtFondoReservaDiasPrevios().getText().trim().equals(""))
			contratoIf.setFondoReservaDiasPrevios(Integer.valueOf(getTxtFondoReservaDiasPrevios().getText()));
		
		
		return contratoIf;
	}
	
	public void save() {
		try {
			if(validateFields()){
				actualizarValoresContratoRubroColleccion();
				ContratoIf contrato = registrarContrato();
				SessionServiceLocator.getContratoSessionService().procesarContrato(contrato, contratosRubroColeccion);
				SpiritAlert.createAlert("Contrato guardado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}	
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al guardar la información!", SpiritAlert.ERROR);
		} 
	}

	public void update() {
		try {
			if(validateFields()){
				actualizarValoresContratoRubroColleccion();
				contratoIf = registrarContratoParaActualizar();
				SessionServiceLocator.getContratoSessionService().actualizarContrato(contratoIf, contratosRubroColeccion, rubrosContratoRemovidos);
				SpiritAlert.createAlert("Contrato actualizado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}	
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch(Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al actualizar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			SessionServiceLocator.getContratoSessionService().eliminarContrato(contratoIf.getId());
			SpiritAlert.createAlert("Contrato eliminado con éxito", SpiritAlert.INFORMATION);
			clean();
			showSaveMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al eliminar el Contrato!", SpiritAlert.ERROR);
		}
	}
	
	/*public void actualizarContratosRubroColeccion(){
		for(RubroIf rubroRemovido : rubrosContratoRemovidos){
			Iterator<ContratoRubroIf> itContratoRubro = contratosRubroColeccion.iterator();
			while(itContratoRubro.hasNext()){
				ContratoRubroIf contratoRubro = itContratoRubro.next();
				if(contratoRubro.getRubroId().compareTo(rubroRemovido.getId()) == 0){
					itContratoRubro.remove();
				}
			}
		}
		
		BigDecimal valor = new BigDecimal(0);
		for (RubroIf rubro : mapaRubrosContrato.keySet()){
			valor = mapaRubrosContrato.get(rubro);
			if(!existeRubroEnColeccion(rubro)){
				ContratoRubroData contratoRubroData = crearContratoRubroData(valor, rubro);
				contratosRubroColeccion.add(contratoRubroData);
			}			
		}
	}*/

	private ContratoRubroData crearContratoRubroData(BigDecimal valor,
			RubroIf rubro) {
		ContratoRubroData contratoRubroData = new ContratoRubroData();
		contratoRubroData.setRubroId(rubro.getId());
		contratoRubroData.setValor(valor);
		
		if(getCbTemporal().isSelected()){
			java.util.Date fechaInicio = Utilitarios.getFirstDateFromMonth(getCmbFechaInicioRubro().getDate());
			contratoRubroData.setFechaInicio(new java.sql.Date(fechaInicio.getTime()));
			java.util.Date fechaFin = Utilitarios.getLastDateFromMonth(getCmbFechaFinRubro().getDate());
			contratoRubroData.setFechaFin(new java.sql.Date(fechaFin.getTime()));
		}
		
		return contratoRubroData;
	}
	
	public boolean existeRubroEnColeccion(RubroIf rubroIf ){
		Iterator<ContratoRubroIf> itContratoRubro = contratosRubroColeccion.iterator();
		while(itContratoRubro.hasNext()){
			ContratoRubroIf contratoRubro = itContratoRubro.next();
			if(contratoRubro.getRubroId().compareTo(rubroIf.getId()) == 0){
				return true;
			}
		}
		return false;
	}
	
	public void clean() {
		getCbTemporal().setSelected(false);
		rubrosColeccion.clear();		
		contratoIf = null;
		
		rubrosContratoRemovidos = null;
		rubrosContratoRemovidos = new ArrayList<RubroIf>();
		
		contratosRubroColeccion = null;
		contratosRubroColeccion = new ArrayList<ContratoRubroIf>();
		
		getTxtCodigo().setText("");
		empleado = null;
		getTxtEmpleado().setText("");
		Calendar calendar = new GregorianCalendar();
		getCmbFechaElaboracion().setCalendar(calendar);
		getCmbFechaInicio().setCalendar(calendar);
		getCmbFechaFin().setCalendar(calendar);
		getTxtObservacion().setText("");
		getTxtFondoReservaDiasPrevios().setText("");
		
		limpiarTabla(this.getTblRubro());
		limpiarTabla(this.getTblRubroContrato());
		
		getCmbFechaInicioRubro().setDate(null);
		getCmbFechaFinRubro().setDate(null);
	}
	
	@Override
	public void report() {
		// TODO Auto-generated method stub
		
	}

	public boolean validateFields() {
		if(getTxtCodigo().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar un Código!", SpiritAlert.WARNING);
			getJtpContrato().setSelectedIndex(0);
			getTxtCodigo().grabFocus();
			return false;
		}
		if(getCmbTipoContrato().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Tipo de Contrato!", SpiritAlert.WARNING);
			getJtpContrato().setSelectedIndex(0);
			getCmbTipoContrato().grabFocus();
			return false;
		}
		if(getTxtEmpleado().getText().equals("") || empleado == null){
			SpiritAlert.createAlert("Debe seleccionar un Empleado!", SpiritAlert.WARNING);
			getJtpContrato().setSelectedIndex(0);
			getTxtEmpleado().grabFocus();
			return false;
		}
		if(getCmbFechaElaboracion().getDate() == null){
			SpiritAlert.createAlert("Debe existir una Fecha de Elaboración!", SpiritAlert.WARNING);
			getJtpContrato().setSelectedIndex(0);
			getCmbFechaElaboracion().grabFocus();
			return false;
		}
		if(getCmbFechaInicio().getDate() == null){
			SpiritAlert.createAlert("Debe seleccionar una Fecha de Inicio!", SpiritAlert.WARNING);
			getJtpContrato().setSelectedIndex(0);
			getCmbFechaInicio().grabFocus();
			return false;
		}
		if(getCmbFechaFin().getDate() == null){
			SpiritAlert.createAlert("Debe seleccionar una Fecha de Fin!", SpiritAlert.WARNING);
			getJtpContrato().setSelectedIndex(0);
			getCmbFechaFin().grabFocus();
			return false;
		}
		if(getCmbEstado().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Estado!", SpiritAlert.WARNING);
			getJtpContrato().setSelectedIndex(0);
			getCmbEstado().grabFocus();
			return false;
		}
		if( contratosRubroColeccion.size() == 0 ){
			SpiritAlert.createAlert("Debe al menos ingresar un Rubro para el Contrato!", SpiritAlert.WARNING);
			getJtpContrato().setSelectedIndex(1);
			getTblRubroContrato().grabFocus();
			return false;
		}
		return true;
	}
	

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showSaveMode() {
		getCmbTipoContrato().setSelectedIndex(0);
		getCmbEstado().setSelectedIndex(0);
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		getCmbTipoContrato().setBackground(Parametros.saveUpdateColor);
		getTxtEmpleado().setBackground(getBackground());
		getCmbFechaInicio().setBackground(Parametros.saveUpdateColor);
		getCmbFechaFin().setBackground(Parametros.saveUpdateColor);
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		setSaveMode();
		clean();
		cargarTablaRubro();
		
		try {
			cargarContratoPantillaDetalle();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
		}
		
		getJtpContrato().setSelectedIndex(0);
		getTxtCodigo().grabFocus();
		
	}
	
	private void cargarTablaRubro() {
		rubrosColeccion.clear();
		limpiarTabla(getTblRubro());
		//cleanTablaRubro();
		try {
			Collection<RubroIf> rubros = SessionServiceLocator.getRubroSessionService().getRubroList();
			String tipoRubroEventual = TipoRubro.EVENTUAL.getLetra();
			mapaRubros = new HashMap<Long, RubroIf>();
			for (RubroIf rubro : rubros) {
				mapaRubros.put(rubro.getId(), rubro);
				if ( !rubro.getTipoRubro().equals(tipoRubroEventual) ) 
					rubrosColeccion.add(rubro);				
			}
			cargarFilasTablaRubro();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarContratoPantillaDetalle() throws GenericBusinessException {
		try{
			Map<String,String> mapaCargarPlantilla = NominaUtil.getParametrosMapa(false, NominaParametros.CARGAR_CONTRATO_PLANTILLA_EN_CONTRATO_NUEVO);
			if ( mapaCargarPlantilla.size() == 1 ){
				String esCargar = mapaCargarPlantilla.keySet().iterator().next();
				String valor = mapaCargarPlantilla.get(esCargar);
				if ( valor!=null && !valor.equals("") && valor.substring(0, 1).toLowerCase().startsWith("s") ){
					if ( contratosRubroColeccion.size() == 0 || getMode() == SpiritMode.SAVE_MODE ){
						contratosRubroColeccion = null;
						contratosRubroColeccion = new ArrayList<ContratoRubroIf>();
					}
					Collection<ContratoRubroIf> rubrosPlantilla = buscarPlantillaDetalle();	
					cleanSeleccionTablaRubro(rubrosPlantilla);
					cargarTablaRubroContrato();
				}
			}

		} catch (Exception e ){
			e.printStackTrace();
			throw new GenericBusinessException("Error al Cargar Plantilla de Contrato !!");
		}
	}
	
	private void agregarRubroContratoDesdePlantilla(ContratoRubroIf cr){
		if ( !existeRubroMapaRubroContrato(cr) )
			contratosRubroColeccion.add(cr);
	}

	private Collection<ContratoRubroIf> buscarPlantillaDetalle()
			throws GenericBusinessException {
		TipoContratoIf tipoContrato = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
		Collection<ContratoPlantillaDetalleIf> contratoPlantillaDetalles = null;
		if ( tipoContrato != null ){
			Collection<ContratoPlantillaIf> contratoPlantillas = SessionServiceLocator.getContratoPlantillaSessionService().findContratoPlantillaByTipoContratoId(tipoContrato.getId());
			for ( ContratoPlantillaIf cp : contratoPlantillas ){
				contratoPlantillaDetalles = SessionServiceLocator.getContratoPlantillaDetalleSessionService().findContratoPlantillaDetalleByContratoPlantillaId(cp.getId());
			}
		}
		
		Collection<ContratoRubroIf> rubrosPlantilla = null;
		if ( contratoPlantillaDetalles != null && (contratosRubroColeccion.size() == 0 || getMode() == SpiritMode.SAVE_MODE )){
			rubrosPlantilla = new ArrayList<ContratoRubroIf>();
			for ( ContratoPlantillaDetalleIf cpd : contratoPlantillaDetalles ){
				RubroIf rubro = mapaRubros.get(cpd.getRubroId());
				ContratoRubroIf cr = crearContratoRubroData(cpd.getValor(), rubro);
				rubrosPlantilla.add(cr);
				agregarRubroContratoDesdePlantilla(cr);
			}
		}
		return rubrosPlantilla;
	}
	
	private boolean existeRubroMapaRubroContrato(ContratoRubroIf cr){
		for ( ContratoRubroIf crTmp : contratosRubroColeccion ){
			if ( crTmp.getRubroId().equals(cr.getRubroId()) )
				return true;
		}
		return false;
	}
	
	private void cargarFilasTablaRubro() {
		tableModelRubro = (DefaultTableModel) getTblRubro().getModel();
		for(RubroIf rubroIf : rubrosColeccion){
			Vector<Object> fila = new Vector<Object>();
			agregarFilaTablaRubro(rubroIf, fila);				
			tableModelRubro.addRow(fila);
		}
	}
	
	private void agregarFilaTablaRubro(RubroIf rubroIf, Vector<Object> fila){
		fila.add(new Boolean(false));
		fila.add(getNombreRubroParaTablaRubroContrato(rubroIf));
		
		String letraTipoRubro = rubroIf.getTipoRubro();
		TipoRubro tipoRubro;
		try {
			tipoRubro = TipoRubro.getTipoRubroByLetra(letraTipoRubro);
			fila.add(tipoRubro.toString());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			fila.add("");
		}
	}
	
	private String getNombreRubroParaTablaRubroContrato(RubroIf rubroIf){
		return rubroIf.getCodigo() + " - " + rubroIf.getNombre();
	}

	public void showFindMode() {
		clean();
		getTxtCodigo().setBackground(Parametros.findColor);
		getCmbTipoContrato().setBackground(Parametros.findColor);
		getTxtEmpleado().setBackground(Parametros.findColor);
		getCmbFechaInicio().setBackground(Parametros.findColor);
		getCmbFechaFin().setBackground(Parametros.findColor);
		getCmbEstado().setBackground(Parametros.findColor);
		getCmbTipoContrato().setSelectedIndex(-1);
		getCmbEstado().setSelectedIndex(-1);
		getCmbFechaElaboracion().setSelectedItem(null);
		getCmbFechaInicio().setSelectedItem(null);
		getCmbFechaFin().setSelectedItem(null);
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		getCmbTipoContrato().setBackground(Parametros.saveUpdateColor);
		getTxtEmpleado().setBackground(getBackground());
		getCmbFechaInicio().setBackground(Parametros.saveUpdateColor);
		getCmbFechaFin().setBackground(Parametros.saveUpdateColor);
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public void refresh() {
		cargarCombos();
		cargarTablaRubro();
		cargarActualizacionContratoRubroColleccion();
		cleanSeleccionTablaRubro(contratosRubroColeccion);
	}
	
	public void duplicate() {
	}

	
}
