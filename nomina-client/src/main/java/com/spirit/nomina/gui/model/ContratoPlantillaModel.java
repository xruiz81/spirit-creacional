package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.nomina.entity.ContratoPlantillaData;
import com.spirit.nomina.entity.ContratoPlantillaDetalleData;
import com.spirit.nomina.entity.ContratoPlantillaDetalleIf;
import com.spirit.nomina.entity.ContratoPlantillaIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.criteria.ContratoPlantillaCriteria;
import com.spirit.nomina.gui.panel.JPContratoPlantilla;
import com.spirit.nomina.gui.util.NominaUtil;
import com.spirit.nomina.gui.util.Rubros;
import com.spirit.nomina.handler.NominaParametros;
import com.spirit.nomina.handler.TipoRubro;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class ContratoPlantillaModel extends JPContratoPlantilla {
	
	private static final long serialVersionUID = -9109052284536456346L;
	
	private final int COLUMNA_RUBRO_SELECCION = 0;
	private final int COLUMNA_RUBRO_NOMBRE = 1;
	private final int COLUMNA_RUBRO_TIPO_RUBRO = 2;
	
	private final int COLUMNA_RUBRO_CONTRATO_NOMBRE = 0;
	private final int COLUMNA_RUBRO_CONTRATO_TIPO_RUBRO = 1;
	private final int COLUMNA_RUBRO_CONTRATO_VALOR = 2;
	
	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_OBSERVACION = 100;
	private static final int MAX_LONGITUD_TXTVALOR = 15;
	
	private static final String MODO_OPERACION_REGISTRADO = "R";
	
	private DefaultTableModel tableModelRubro;
	private Vector<RubroIf> rubrosColeccion = new Vector<RubroIf>();
	
	private Map<Long,BigDecimal> mapaRubrosContrato = null;
	
	private Map<Long,RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
	private Map<String,RubroIf> mapaRubroPorNombre = new HashMap<String, RubroIf>();
	
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private ContratoPlantillaIf contratoPlantillaIf;
	private Collection<RubroIf> rubrosContratoRemovidos = new ArrayList<RubroIf>();
	private Collection<ContratoPlantillaDetalleIf> contratosRubroColeccion = new ArrayList<ContratoPlantillaDetalleIf>();
	private Calendar calendarFechaInicio = new GregorianCalendar();
	private Calendar calendarFechaFin = new GregorianCalendar();
	private Collection<TipoRolIf> tipoRolCollection = null;
	
	public ContratoPlantillaModel(){
		iniciarComponentes();
		anchoColumnasTabla();
		initKeyListeners();
		cargarCombos();
		showSaveMode();
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void iniciarComponentes(){
		
		//SE CARGAN LOS TIPOS DE ROL DE LA EMPRESA
		Map<String, Object> mapa =  new HashMap<String, Object>();
		mapa.put("empresaId", Parametros.getIdEmpresa());
		mapa.put("rubroEventual", "S");
		try {
			tipoRolCollection = SessionServiceLocator.getTipoRolSessionService().findTipoRolByQuery(mapa);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		getBtnAgregarRubroContrato().setText("");
		getBtnAgregarRubroContrato().setToolTipText("Agregar Rubro Contrato");
		getBtnAgregarRubroContrato().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnRemoverRubroContrato().setText("");
		getBtnRemoverRubroContrato().setToolTipText("Eliminar Rubro Contrato");
		getBtnRemoverRubroContrato().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
	}
	
	private void anchoColumnasTabla() {
		getTblRubro().getTableHeader().setReorderingAllowed(false);
		getTblRubroContrato().getTableHeader().setReorderingAllowed(false);
		getTblRubroContrato().setCellSelectionEnabled(true);
		
		getTblRubro().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblRubroContrato().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		TableColumn anchoColumnaRubroContrato = getTblRubroContrato().getColumnModel().getColumn(COLUMNA_RUBRO_CONTRATO_NOMBRE);
		anchoColumnaRubroContrato.setPreferredWidth(250);
		anchoColumnaRubroContrato = getTblRubroContrato().getColumnModel().getColumn(COLUMNA_RUBRO_CONTRATO_TIPO_RUBRO);
		anchoColumnaRubroContrato.setPreferredWidth(200);
		anchoColumnaRubroContrato = getTblRubroContrato().getColumnModel().getColumn(COLUMNA_RUBRO_CONTRATO_VALOR);
		anchoColumnaRubroContrato.setPreferredWidth(150);
		
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
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtObservacion().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACION));
	}
	
	public void cargarCombos(){
		cargarComboTipoContrato();
	}
	
	public void cargarComboTipoContrato(){
		try {
			List tiposContrato = (List) SessionServiceLocator.getTipoContratoSessionService().findTipoContratoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTipoContrato(), tiposContrato);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	//Saca de la tabla rubros los rubros que ya estan en el contrato
	public void cleanSeleccionTablaRubro(Collection<Long> rubroCollection) throws GenericBusinessException{
		try {
			//for (RubroIf rubro : mapaRubrosContrato.keySet()){
			for (Long rubroId : rubroCollection){
				RubroIf rubro = Rubros.verificarRubrosEnMapa(mapaRubros, null, rubroId);
				if ( rubro != null ){
					Iterator<RubroIf> itRubros = rubrosColeccion.iterator(); 
					//for(int i=0; i<rubrosColeccion.size(); i++){
					while ( itRubros.hasNext() ) {
						//RubroIf rubroTemp = rubrosColeccion.get(i);
						RubroIf rubroTemp = itRubros.next();
						if(rubroTemp.getId().compareTo(rubro.getId()) == 0){
							//rubrosColeccion.remove(i);
							itRubros.remove();
						}
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error al cargar Rubros !!");
		}
		limpiarTabla(getTblRubro());
		cargarFilasTablaRubro();
	}
	
	private void initListeners() {
		
		getBtnAgregarRubroContrato().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				//generarMapaRubrosContratoMap();
				Collection<RubroIf> rubrosSeleccionados = getRubrosSeleccionados();
				if( rubrosSeleccionados.size() > 0 ){
					agregarRubroContrato(rubrosSeleccionados);
					try {
						cleanSeleccionTablaRubro(mapaRubrosContrato.keySet());
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
					}
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
	
	public void agregarRubroContrato(Collection<RubroIf> rubrosSeleccionados){
		DefaultTableModel tableModelRubroContrato = (DefaultTableModel) getTblRubroContrato().getModel();
		
		if (rubrosSeleccionados != null){
			for(RubroIf rubro : rubrosSeleccionados){
				mapaRubrosContrato.put(rubro.getId(), null);
				
				Vector<String> filaRubroContrato = crearFilaRubroContrato(rubro);						
				tableModelRubroContrato.addRow(filaRubroContrato);
			}
		}
	}

	private Vector<String> crearFilaRubroContrato(RubroIf rubro) {
		Vector<String> filaRubroContrato = new Vector<String>();			
		filaRubroContrato.add( getNombreRubroParaTablaRubroContrato(rubro) );

		try{
			String letraTipoRubro = rubro.getTipoRubro();
			TipoRubro tipoRubro = TipoRubro.getTipoRubroByLetra(letraTipoRubro);
			filaRubroContrato.add(tipoRubro.toString());
		} catch(GenericBusinessException ex){
			filaRubroContrato.add("");
		}

		if(rubro.getModoOperacion().equals(MODO_OPERACION_REGISTRADO)){
			BigDecimal valor = mapaRubrosContrato.get(rubro);
			if ( valor == null ) {
				mapaRubrosContrato.put(rubro.getId(), BigDecimal.ZERO);
				filaRubroContrato.add("0.00");
			} else{
				filaRubroContrato.add(String.valueOf(valor.doubleValue()));
			}
				
		}else{
			filaRubroContrato.add("CALCULADO");
		}
		return filaRubroContrato;
	}
	
	public void actualizarValoresMapaRubrosContratoMap(){
		try{
			for(int i=0; i<getTblRubroContrato().getModel().getRowCount(); i++){
				BigDecimal valor = null;
				String nombre = (String)getTblRubroContrato().getValueAt(i, COLUMNA_RUBRO_CONTRATO_NOMBRE);
				String valorS = getTblRubroContrato().getValueAt(i, COLUMNA_RUBRO_CONTRATO_VALOR).toString();  
				if( valorS != null){
					if(!valorS.equals("CALCULADO")){
						valorS = Utilitarios.removeDecimalFormat(valorS);
						valor = BigDecimal.valueOf(Double.valueOf(valorS));
					}
					RubroIf rubroIf = mapaRubroPorNombre.get(nombre);
					if ( rubroIf != null ){
						mapaRubrosContrato.put(rubroIf.getId(), valor);
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
			String nombre = (String)getTblRubroContrato().getValueAt(filaSeleccionada, COLUMNA_RUBRO_CONTRATO_NOMBRE);
			RubroIf rubro = (RubroIf)mapaRubroPorNombre.get(nombre);
			rubrosColeccion.add(rubro);
			if(rubro.getId() != null){
				rubrosContratoRemovidos.add(rubro);
			}				
			removerRubroDeMapaRubroContrato(rubro);
			tableModelRubroContrato.removeRow(filaSeleccionada);
			limpiarTabla(getTblRubro());
			cargarFilasTablaRubro();
		}else{
			SpiritAlert.createAlert("Debe seleccionar una fila en la tabla!", SpiritAlert.WARNING);
		}
				
	}
	
	private void removerRubroDeMapaRubroContrato(RubroIf rubro){
		Iterator<Long> itRubros = mapaRubrosContrato.keySet().iterator();
		while( itRubros.hasNext() ){
			Long rubroId = itRubros.next();
			if ( rubroId != null && rubro.getId().equals(rubroId) )
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
			aMap.put("tipoContratoId", ((TipoContratoIf)getCmbTipoContrato().getSelectedItem()).getId());
		
		return aMap;
	}

	public void find() {
		try {			
			Map mapa = buildQuery();
			if ( mapa==null ){
				SpiritAlert.createAlert("Error en estado de Contato !!", SpiritAlert.WARNING);
				return;
			}
			int tamanoLista = SessionServiceLocator.getContratoPlantillaSessionService().getContratoPlantillaListSize(mapa);
			if (tamanoLista > 0) {
				try {
					Vector<Integer> anchoColumnasBusqueda = new Vector<Integer>();
					anchoColumnasBusqueda.add(70);
					anchoColumnasBusqueda.add(230);
					anchoColumnasBusqueda.add(150);
					
					ContratoPlantillaCriteria contratoPlantillaCriteria = new ContratoPlantillaCriteria();
					contratoPlantillaCriteria.setResultListSize(tamanoLista);
					contratoPlantillaCriteria.setQueryBuilded(mapa);
					JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), contratoPlantillaCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnasBusqueda, null);
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

		//GENERAL
		contratoPlantillaIf = (ContratoPlantillaIf) contratoSeleccionado;
		getCmbTipoContrato().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoContrato(), contratoPlantillaIf.getTipoContratoId()));
		getCmbTipoContrato().repaint();
		getTxtCodigo().setText(contratoPlantillaIf.getCodigo());

		if(contratoPlantillaIf.getObservacion() != null)
			getTxtObservacion().setText(contratoPlantillaIf.getObservacion());

		try {
			//RUBROS POR CONTRATO
			cargarTablaRubro();
			cargarActualizacionMapaRubrosContratoMap();
			cleanSeleccionTablaRubro(mapaRubrosContrato.keySet());
			cargarTablaRubroContrato();
			//agregarRubroContrato(null);
			//RUBROS EVENTUALES
			//cargarActualizacionRubrosEventualesColeccion();
	
			showUpdateMode();
		} catch(GenericBusinessException e){
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
	}
	
	private void cargarTablaRubroContrato() throws GenericBusinessException{
		limpiarTabla(getTblRubroContrato());
		DefaultTableModel tableModelRubroContrato = (DefaultTableModel) getTblRubroContrato().getModel();
		for( Long rubroId : mapaRubrosContrato.keySet() ){
			RubroIf rubro = Rubros.verificarRubrosEnMapa(mapaRubros, null, rubroId);
			if ( rubro != null ) {
				Vector<String> fila = crearFilaRubroContrato(rubro);
				tableModelRubroContrato.addRow(fila);
			}
		}
	}
	
	public void cargarActualizacionMapaRubrosContratoMap(){
		try {
			contratosRubroColeccion = SessionServiceLocator.getContratoPlantillaDetalleSessionService().findContratoPlantillaDetalleByContratoPlantillaId(contratoPlantillaIf.getId());
			BigDecimal valor = new BigDecimal(0);
			for(ContratoPlantillaDetalleIf contratoRubro : contratosRubroColeccion){
				RubroIf rubro = SessionServiceLocator.getRubroSessionService().getRubro(contratoRubro.getRubroId());
				valor = contratoRubro.getValor();
				mapaRubrosContrato.put(rubro.getId(), valor);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public ContratoPlantillaData registrarContrato() throws GenericBusinessException{
		ContratoPlantillaData dataContrato = new ContratoPlantillaData();
		dataContrato.setCodigo(getTxtCodigo().getText());
		TipoContratoIf tipoContrato = (TipoContratoIf)getCmbTipoContrato().getSelectedItem();
		dataContrato.setTipoContratoId(tipoContrato.getId());
		dataContrato.setObservacion(getTxtObservacion().getText());
		
		return dataContrato;
	}
	
	public ContratoPlantillaIf registrarContratoParaActualizar() throws GenericBusinessException{
		contratoPlantillaIf.setCodigo(getTxtCodigo().getText());
		TipoContratoIf tipoContrato = (TipoContratoIf)getCmbTipoContrato().getSelectedItem();
		contratoPlantillaIf.setTipoContratoId(tipoContrato.getId());
		contratoPlantillaIf.setObservacion(getTxtObservacion().getText());
		
		return contratoPlantillaIf;
	}
	
	public void save() {
		try {
			if(validateFields()){
				actualizarValoresMapaRubrosContratoMap();
				ContratoPlantillaIf contratoPlantilla = registrarContrato();
				SessionServiceLocator.getContratoPlantillaSessionService().procesarContratoPlantilla(contratoPlantilla, mapaRubrosContrato);
				SpiritAlert.createAlert("Plantilla de contrato guardada con éxito",SpiritAlert.INFORMATION);
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
				actualizarValoresMapaRubrosContratoMap();
				actualizarContratosRubroColeccion();
				registrarContratoParaActualizar();
				SessionServiceLocator.getContratoPlantillaSessionService().actualizarContratoPlantilla(contratoPlantillaIf, contratosRubroColeccion, rubrosContratoRemovidos);
				SpiritAlert.createAlert("Plantilla de contrato actualizada con éxito",SpiritAlert.INFORMATION);
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
			SessionServiceLocator.getContratoPlantillaSessionService().eliminarContratoPlantilla(contratoPlantillaIf.getId());
			SpiritAlert.createAlert("Plantilla de contrato eliminada con éxito", SpiritAlert.INFORMATION);
			clean();
			showSaveMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al eliminar el Contrato!", SpiritAlert.ERROR);
		}
	}
	
	public void actualizarContratosRubroColeccion() throws GenericBusinessException{
		for(RubroIf rubroRemovido : rubrosContratoRemovidos){
			Iterator<ContratoPlantillaDetalleIf> itContratoRubro = contratosRubroColeccion.iterator();
			while(itContratoRubro.hasNext()){
				ContratoPlantillaDetalleIf contratoRubro = itContratoRubro.next();
				if(contratoRubro.getRubroId().compareTo(rubroRemovido.getId()) == 0){
					itContratoRubro.remove();
				}
			}
		}
		
		BigDecimal valor = new BigDecimal(0);
		for (Long rubroId : mapaRubrosContrato.keySet()){
			RubroIf rubro = Rubros.verificarRubrosEnMapa(mapaRubros, null, rubroId);
			if ( rubro != null ){
				valor = mapaRubrosContrato.get(rubro);
				if(!existeRubroEnColeccion(rubro)){
					ContratoPlantillaDetalleData contratoRubroData = new ContratoPlantillaDetalleData();
					contratoRubroData.setRubroId(rubro.getId());
					contratoRubroData.setValor(valor);
					contratosRubroColeccion.add(contratoRubroData);
				}
			}
		}
	}
	
	public boolean existeRubroEnColeccion(RubroIf rubroIf ){
		if ( rubroIf != null ){
			Iterator<ContratoPlantillaDetalleIf> itContratoRubro = contratosRubroColeccion.iterator();
			while(itContratoRubro.hasNext()){
				ContratoPlantillaDetalleIf contratoRubro = itContratoRubro.next();
				if(contratoRubro!= null && contratoRubro.getRubroId().compareTo(rubroIf.getId()) == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	public void clean() {
		rubrosColeccion.clear();
		
		mapaRubrosContrato = null;
		mapaRubrosContrato = new HashMap<Long, BigDecimal>();
		
		contratoPlantillaIf = null;
		
		rubrosContratoRemovidos = null;
		rubrosContratoRemovidos = new ArrayList<RubroIf>();
		
		contratosRubroColeccion = null;
		contratosRubroColeccion = new ArrayList<ContratoPlantillaDetalleIf>();
		
		getTxtCodigo().setText("");
		getTxtObservacion().setText("");
		getCmbTipoContrato().setSelectedItem(null);
		
		limpiarTabla(this.getTblRubro());
		limpiarTabla(this.getTblRubroContrato());
	}
	
	public void report() {
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
		if(mapaRubrosContrato.isEmpty()){
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
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		getTxtObservacion().setEnabled(true);
		getCmbTipoContrato().setBackground(Parametros.saveUpdateColor);
		setSaveMode();
		clean();
		cargarTablaRubro();
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
			mapaRubroPorNombre = new HashMap<String, RubroIf>();
			for (RubroIf rubro : rubros) {
				mapaRubros.put(rubro.getId(), rubro);
				mapaRubroPorNombre.put(getNombreRubroParaTablaRubroContrato(rubro), rubro);
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
			TipoContratoIf tipoContrato = (TipoContratoIf) getCmbTipoContrato().getSelectedItem();
			Collection<ContratoPlantillaDetalleIf> contratoPlantillaDetalles = null;
			if ( tipoContrato != null ){
				Collection<ContratoPlantillaIf> contratoPlantillas = SessionServiceLocator.getContratoPlantillaSessionService().findContratoPlantillaByTipoContratoId(tipoContrato.getId());
				for ( ContratoPlantillaIf cp : contratoPlantillas ){
					contratoPlantillaDetalles = SessionServiceLocator.getContratoPlantillaDetalleSessionService().findContratoPlantillaDetalleByContratoPlantillaId(cp.getId());
				}
			}
			
			Collection<Long> rubrosPlantilla = null;
			if ( contratoPlantillaDetalles != null ){
				rubrosPlantilla = new ArrayList<Long>();
				for ( ContratoPlantillaDetalleIf cpd : contratoPlantillaDetalles ){
					RubroIf rubro = Rubros.verificarRubrosEnMapa(mapaRubros, null, cpd.getRubroId());
					rubrosPlantilla.add(rubro.getId());
					mapaRubrosContrato.put(rubro.getId(), cpd.getValor());
				}
			}
			
			if ( rubrosPlantilla != null ){
				Map<String,String> mapaCargarPlantilla = NominaUtil.getParametrosMapa(false, NominaParametros.CARGAR_CONTRATO_PLANTILLA_EN_CONTRATO_NUEVO);
				if ( mapaCargarPlantilla.size() == 1 ){
					String esCargar = mapaCargarPlantilla.keySet().iterator().next();
					String valor = mapaCargarPlantilla.get(esCargar);
					if ( valor!=null && !valor.equals("") && valor.substring(0, 1).toLowerCase().startsWith("s") ){
						cleanSeleccionTablaRubro(rubrosPlantilla);
						cargarTablaRubroContrato();
					}
				}
			}
		} catch (Exception e ){
			e.printStackTrace();
			throw new GenericBusinessException("Error al Cargar Plantilla de Contrato !!");
		}
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
		getTxtObservacion().setEnabled(false);
		getCmbTipoContrato().setBackground(Parametros.findColor);
		getCmbTipoContrato().setSelectedIndex(-1);
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		getTxtObservacion().setEnabled(true);
		getCmbTipoContrato().setBackground(Parametros.saveUpdateColor);
	}

	public void addDetail() {
	}

	public void updateDetail() {
	}
	
	public void deleteDetail() {
		
	}

	public void refresh() {
		cargarCombos();
		cargarTablaRubro();
		cargarActualizacionMapaRubrosContratoMap();
		try {
			cleanSeleccionTablaRubro(mapaRubrosContrato.keySet());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
	}
	
	public void duplicate() {
	}

}
