package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.NumberCellRenderer;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RubroEventualData;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.criteria.ContratoCriteria;
import com.spirit.nomina.gui.panel.JPRubrosEventualesBeneficiosSociales;
import com.spirit.nomina.gui.util.Rubros;
import com.spirit.nomina.handler.EstadoRubroEventual;
import com.spirit.nomina.handler.TipoRolProvisionado;
import com.spirit.nomina.handler.TipoRubro;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class RubrosEventualesBeneficiosSocialesModel extends JPRubrosEventualesBeneficiosSociales {

	private static final long serialVersionUID = 5735861570034868999L;
	
	private static final int MAX_LONGITUD_OBSERVACION = 100;
	private static final int MAX_LONGITUD_VALOR = 10;
	private static final int MAX_LONGITUD_NUMERO_PAGOS = 2;
	
	private final int COLUMNA_VALOR = 3;
	private final int COLUMNA_IDENTIFICACION = 5;
	
	//private static final String RUBRO_EVENTUAL = "E";
	private static final String RUBRO_EVENTUAL_SI = "S";
		
	private DefaultTableModel modeloTablaRubroEventual = null;
	private DecimalFormat formatoDosDecimales = new DecimalFormat("#.00");
	
	private RubroEventualIf rubroEventualIf = null;
	private EmpleadoIf empleadoIf = null;
	private ContratoIf contratoIf = null;
	private Map<Long, TipoRolIf> mapaTipoRolEventual =  new HashMap<Long, TipoRolIf>();
	private Map<Long, RubroIf> mapaRubroEventual =  new HashMap<Long, RubroIf>();
	//private ArrayList<RubroEventualIf> rubrosEventualesColleccion = null;
	Map<PanelActivo,ArrayList<RubroEventualIf>> mapaRubrosEventualesCollection = null;
	private ArrayList<RubroEventualIf> rubrosEventualesRemovidos = null;
	
	enum PanelActivo { EMITIDOS , AUTORIZADOS , PAGADOS };
	
	PanelActivo panelActivo = PanelActivo.EMITIDOS;
	
	public RubrosEventualesBeneficiosSocialesModel() {
		iniciarComponentes();
		initListeners();
		anchoComlumnas();
		initKeyListeners();
		cargarCombos();
		showSaveMode();
		new HotKeyComponent(this);
	}

	public void clean() {
		///
		mapaRubrosEventualesCollection = null;
		mapaRubrosEventualesCollection = new HashMap<PanelActivo,ArrayList<RubroEventualIf>>(); 
		ArrayList<RubroEventualIf> rubrosEventuales = mapaRubrosEventualesCollection.remove(PanelActivo.EMITIDOS); 
		rubrosEventuales = null;
		mapaRubrosEventualesCollection.put(PanelActivo.EMITIDOS,new ArrayList<RubroEventualIf>());
		rubrosEventuales = mapaRubrosEventualesCollection.remove(PanelActivo.AUTORIZADOS); 
		rubrosEventuales = null;
		mapaRubrosEventualesCollection.put(PanelActivo.AUTORIZADOS,new ArrayList<RubroEventualIf>());
		rubrosEventuales = mapaRubrosEventualesCollection.remove(PanelActivo.PAGADOS); 
		rubrosEventuales = null;
		mapaRubrosEventualesCollection.put(PanelActivo.PAGADOS,new ArrayList<RubroEventualIf>());
		
		rubroEventualIf = null;
		//rubrosEventualesColleccion = null;
		
		rubrosEventualesRemovidos = null;
		rubrosEventualesRemovidos = new ArrayList<RubroEventualIf>();
		
		empleadoIf = null;
		getTxtEmpleado().setText("");
		contratoIf = null;
		getTxtContrato().setText("");

		getTxtValor().setText("");
		getTxtNumeroPagos().setText("1");
		getTxtObservacionEventual().setText("");
		

		getRbFinMesNo().setEnabled(true);
		
		//getCmbRubroEventual().setSelectedItem(null);
		//getCmbFechaCobro().setCalendar(null);
		//getCmbTipoRolCobro().setSelectedItem(null);

		//getRbEmitirChequeNo().setSelected(true);
		
		limpiarTabla(getTblRubrosEventualesEmitidos());
		limpiarTabla(getTblRubrosEventualesAutorizados());
		limpiarTabla(getTblRubrosEventualesPagados());
	}
	
	private void iniciarComponentes(){
		
		
		modeloTablaRubroEventual = (DefaultTableModel)getTblRubrosEventualesEmitidos().getModel();
		
		cargarTipoRol();
		
		getTxtValor().setText("1");
		
		getCmbFechaCobro().setLocale(Utilitarios.esLocale);
		getCmbFechaCobro().setShowNoneButton(false);
		getCmbFechaCobro().setFormat(Utilitarios.setFechaAnio());
		
		getCmbFechaPago().setLocale(Utilitarios.esLocale);
		getCmbFechaPago().setShowNoneButton(false);
		getCmbFechaPago().setFormat(Utilitarios.setFechaMesAnioUppercase());
		
		//-----FECHAS INICIO Y FIN
		getCmbFechaInicioEmitido().setLocale(Utilitarios.esLocale);
		getCmbFechaInicioEmitido().setFormat(Utilitarios.setFechaMesAnioUppercase());
		
		getCmbFechaFinEmitido().setLocale(Utilitarios.esLocale);
		getCmbFechaFinEmitido().setFormat(Utilitarios.setFechaMesAnioUppercase());
		
		getCmbFechaInicioAutorizado().setLocale(Utilitarios.esLocale);
		getCmbFechaInicioAutorizado().setFormat(Utilitarios.setFechaMesAnioUppercase());
		
		getCmbFechaFinAutorizado().setLocale(Utilitarios.esLocale);
		getCmbFechaFinAutorizado().setFormat(Utilitarios.setFechaMesAnioUppercase());
		
		getCmbFechaInicioPagado().setLocale(Utilitarios.esLocale);
		getCmbFechaInicioPagado().setFormat(Utilitarios.setFechaMesAnioUppercase());
		
		getCmbFechaFinPagado().setLocale(Utilitarios.esLocale);
		getCmbFechaFinPagado().setFormat(Utilitarios.setFechaMesAnioUppercase());
		//-----------------------------
		
		getBtnEmpleado().setText("");
		getBtnEmpleado().setToolTipText("Buscar Empleado");
		getBtnEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getBtnContrato().setText("");
		getBtnContrato().setToolTipText("Buscar Contrato");
		getBtnContrato().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getBtnAgregarRubroEventual().setText("");
		getBtnAgregarRubroEventual().setToolTipText("Agregar Rubro Eventual");
		getBtnAgregarRubroEventual().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarRubroEventual().setText("");
		getBtnActualizarRubroEventual().setToolTipText("Actualizar Rubro Eventual");
		getBtnActualizarRubroEventual().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnRemoverRubroEventual().setText("");
		getBtnRemoverRubroEventual().setToolTipText("Eliminar Rubro Eventual");
		getBtnRemoverRubroEventual().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		
		setSorterTable(getTblRubrosEventualesEmitidos());
		getTblRubrosEventualesEmitidos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblRubrosEventualesEmitidos().getColumnModel().getColumn(COLUMNA_VALOR).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		
		setSorterTable(getTblRubrosEventualesAutorizados());
		getTblRubrosEventualesAutorizados().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblRubrosEventualesAutorizados().getColumnModel().getColumn(COLUMNA_VALOR).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		
		setSorterTable(getTblRubrosEventualesPagados());
		getTblRubrosEventualesPagados().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblRubrosEventualesPagados().getColumnModel().getColumn(COLUMNA_VALOR).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		
		getCmbFechaPago().setEnabled(false);
		getCmbTipoRolPago().setEnabled(false);
		
		getRbEmitirChequeNo().setSelected(true);

		getRbFinMesNo().setSelected(true);
		
		getRbCuota().setEnabled(false);
		getRbTotal().setSelected(true);
		
		getTxtNumeroPagos().setEditable(false);
	}
	
	private void cargarTipoRol(){
		try {
			Collection<TipoRolIf> tipoRoles = SessionServiceLocator.getTipoRolSessionService()
				.findTipoRolByRubroProvisionado(TipoRolProvisionado.SI.getLetra());
			for ( TipoRolIf tipoRolIf : tipoRoles ){
				mapaTipoRolEventual.put(tipoRolIf.getId(), tipoRolIf);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar Tipos de Roles Eventuales !!", SpiritAlert.ERROR);
		}
		
	}
	
	private void initListeners(){
		getRbEmitirChequeNo().addChangeListener(clEmitirCheque);
		getRbEmitirChequeSi().addChangeListener(clEmitirCheque);
		
		getBtnEmpleado().addActionListener(alBuscarEmpleado);
		getBtnContrato().addActionListener(alBuscarContrato);
		
		getTblRubrosEventualesEmitidos().addMouseListener(mlTablaRubroEventual);
		getTblRubrosEventualesEmitidos().addKeyListener(klTablaRubroEventual);
		
		getTblRubrosEventualesAutorizados().addMouseListener(mlTablaRubroEventual);
		getTblRubrosEventualesAutorizados().addKeyListener(klTablaRubroEventual);
		
		getTblRubrosEventualesPagados().addMouseListener(mlTablaRubroEventual);
		getTblRubrosEventualesPagados().addKeyListener(klTablaRubroEventual);
		
		getBtnAgregarRubroEventual().addActionListener(alAgregarRubroEventual);
		getBtnActualizarRubroEventual().addActionListener(alActualizarRubroEventual);
		getBtnRemoverRubroEventual().addActionListener(alRemoverRubroEventual);
		
		getTpaneDetalles().addChangeListener(cl);
		
		getBtnConsultarEmitidos().addActionListener(alConsultar);
		getBtnConsultarAutorizados().addActionListener(alConsultar);
		getBtnConsultarPagados().addActionListener(alConsultar);
		
		getCmbTipoRolCobro().addActionListener(alComboTipoRolCobro);
		
	}
	
	ActionListener alComboTipoRolCobro = new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			cargarComboRubroEventual();
			TipoRolIf tipoRolCobroSeleccionado = (TipoRolIf) getCmbTipoRolCobro().getSelectedItem();
			List lista = new ArrayList();
			if ( tipoRolCobroSeleccionado != null )
				lista.add(tipoRolCobroSeleccionado);
			cargarComboTipoRolPago(lista);
		}
		
	};
	
	ActionListener alConsultar = new ActionListener(){
		
		public void actionPerformed(ActionEvent e) {
			try {
				if ( panelActivo == PanelActivo.EMITIDOS ){
						buscarRubroEventuales(EstadoRubroEventual.EMITIDO);
				} else if ( panelActivo == PanelActivo.AUTORIZADOS ){
						buscarRubroEventuales(EstadoRubroEventual.AUTORIZADO,EstadoRubroEventual.AUTORIZADO_PARCIAL);
				} else if ( panelActivo == PanelActivo.PAGADOS ){
						buscarRubroEventuales(EstadoRubroEventual.PAGADO);
				}
			} catch (GenericBusinessException e1) {
				e1.printStackTrace();
				SpiritAlert.createAlert("Error en Busqueda de Rubros Eventuales Emitidos !!",SpiritAlert.ERROR);
			}
		}
		
	};
	
	ChangeListener cl = new ChangeListener(){

		public void stateChanged(ChangeEvent e) {
			rubroEventualIf = null;
			JTabbedPane panel = (JTabbedPane)e.getSource();
			String titulo = panel.getTitleAt(panel.getSelectedIndex()); 
			if ( titulo.equalsIgnoreCase("Emitidos") ){
				getBtnRemoverRubroEventual().setEnabled(true);
				panelActivo = PanelActivo.EMITIDOS;
			} else {
				if ( titulo.equalsIgnoreCase("Autorizados") )
					panelActivo = PanelActivo.AUTORIZADOS;
				else if ( titulo.equalsIgnoreCase("Pagados") )
					panelActivo = PanelActivo.PAGADOS;
				
				getBtnRemoverRubroEventual().setEnabled(false);
			}
		}
	};
	
	ActionListener alAgregarRubroEventual =  new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			agregarRubroEventual();
		}
	};
	
	ActionListener alActualizarRubroEventual =  new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			actualizarRubroEventual();
		}
	};
	
	ActionListener alRemoverRubroEventual = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			removerRubroEventual();
		}
	};
	
	MouseListener mlTablaRubroEventual = new MouseAdapter(){
		public void mouseReleased(MouseEvent e) {
			seleccionarFila((JTable)e.getSource());
		}
	};
	
	KeyListener klTablaRubroEventual = new KeyAdapter(){
		public void keyReleased(KeyEvent e) {
			seleccionarFila((JTable)e.getSource());
		}
	};
	
	ChangeListener clEmitirCheque =  new ChangeListener(){
		public void stateChanged(ChangeEvent e) {
			if ( getRbEmitirChequeSi().isSelected() ){
				getCmbTipoRolPago().setEnabled(true);
				getCmbFechaPago().setEnabled(true);
				
				getCmbFechaPago().setCalendar(getCmbFechaCobro().getCalendar());
			} else{
				getCmbTipoRolPago().setEnabled(false);
				//getCmbTipoRolPago().setSelectedItem(null);
				getCmbFechaPago().setEnabled(false);
				//getCmbFechaPago().setSelectedItem(null);
				
			}
		}
	};
	
	/*ActionListener alEmitirCheque = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			
		}
	};*/
	
	ActionListener alBuscarEmpleado =  new ActionListener(){
		public void actionPerformed(ActionEvent ev) {
			try {
				busquedaEmpleado();
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			} catch (Exception e) {
				e.printStackTrace(  );
				SpiritAlert.createAlert("Error en la busqueda de Empleado !!", SpiritAlert.ERROR);
			}
		}
	};
	
	ActionListener alBuscarContrato =  new ActionListener(){
		public void actionPerformed(ActionEvent evento) {
			busquedaContrato();
			try {
				buscarRubroEventuales(EstadoRubroEventual.EMITIDO);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}	
	};
	
	private void seleccionarFila(JTable tabla){
		int filaTabla = tabla.getSelectedRow();
		if ( filaTabla >= 0 ){
			filaTabla = tabla.convertRowIndexToModel(filaTabla);
			ArrayList<RubroEventualIf> rubrosEventualesColleccion = mapaRubrosEventualesCollection.get(panelActivo);
			rubroEventualIf = rubrosEventualesColleccion.get(filaTabla);
			
			int iRubroEventual = ComboBoxComponent.getIndexToSelectItem(getCmbRubroEventual(), rubroEventualIf.getRubroId());
			getCmbRubroEventual().setSelectedIndex(iRubroEventual);
			getCmbRubroEventual().repaint();
			
			int iTipoRolCobro = ComboBoxComponent.getIndexToSelectItem(getCmbTipoRolCobro(), rubroEventualIf.getTipoRolIdCobro());
			getCmbTipoRolCobro().setSelectedIndex(iTipoRolCobro);
			getCmbTipoRolCobro().repaint();
			
			getTxtValor().setText(formatoDosDecimales.format(rubroEventualIf.getValor().doubleValue()));
			getCmbFechaCobro().setDate(new java.util.Date(rubroEventualIf.getFechaCobro().getTime()));
			getTxtObservacionEventual().setText(rubroEventualIf.getObservacion());
			
			getRbEmitirChequeNo().setSelected(true);
			if ( rubroEventualIf.getFechaPago() != null ){
				getCmbFechaPago().setDate(new Date(rubroEventualIf.getFechaPago().getTime()));
				getCmbFechaPago().repaint();
			} else {
				getCmbFechaPago().setDate(null);
				getCmbFechaPago().repaint();
			}
			if ( rubroEventualIf.getTipoRolIdPago() != null ){
				int iTipoRolPago = ComboBoxComponent.getIndexToSelectItem(getCmbTipoRolPago(), rubroEventualIf.getTipoRolIdPago());
				getCmbTipoRolPago().setSelectedIndex(iTipoRolPago);
				getCmbTipoRolPago().repaint();
			} else {
				getCmbTipoRolPago().setSelectedItem(null);
				getCmbTipoRolPago().repaint();
			}
		}
	}
	
	public void agregarRubroEventual() {
		try{
			if ( panelActivo != PanelActivo.EMITIDOS ){
				SpiritAlert.createAlert("Solo se puede agregar Rubros en estado Emitido !!",SpiritAlert.WARNING);
				return;
			}
			
			if (validateFieldsRubroEventual()) {
				
				Calendar calendarComboEstablecido = getCmbFechaCobro().getCalendar();
				Calendar calendarCombo = new GregorianCalendar();
				calendarCombo.set(Calendar.DAY_OF_MONTH, calendarComboEstablecido.get(Calendar.DAY_OF_MONTH));
				calendarCombo.set(Calendar.MONTH, calendarComboEstablecido.get(Calendar.MONTH));
				calendarCombo.set(Calendar.YEAR, calendarComboEstablecido.get(Calendar.YEAR));
				TipoRolIf tipoRolCobro = (TipoRolIf)getCmbTipoRolCobro().getSelectedItem();
				
				String numeroPagosString = getTxtNumeroPagos().getText();
				int numeroPagos = 0;
				Long identificacionMaxima = buscarIdentificacionMaxima() + 1L;
				if ( numeroPagosString!=null && !"".equals(numeroPagosString) ){
					numeroPagos = Integer.parseInt(numeroPagosString);
					
					TipoRolIf tipoRolPago = (TipoRolIf)getCmbTipoRolPago().getSelectedItem();
					Date fechaPago = getCmbFechaPago().getDate();
					
					if ( numeroPagos == 1 ){
						crearRubroEventual(calendarCombo.getTime(), "",tipoRolPago,fechaPago,tipoRolPago,identificacionMaxima);
					} else 
						throw new GenericBusinessException("El numero de coutas maximo es de 1 !!");
				} else {
					TipoRolIf tipoRolPago = (TipoRolIf)getCmbTipoRolPago().getSelectedItem();
					Date fechaPago = getCmbFechaPago().getDate();
					crearRubroEventual(calendarCombo.getTime(), "",tipoRolCobro,fechaPago,tipoRolPago,identificacionMaxima);
				}
				getTxtValor().setText("");
				getTxtNumeroPagos().setText("");

				getRbFinMesNo().setEnabled(true);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al agregar Rubro !!", SpiritAlert.ERROR);
		}
	}
	
	private TipoRolIf buscarTipoRol(String nombreTipoRol){
		for ( Long id : mapaTipoRolEventual.keySet() ){
			TipoRolIf tipoRol = mapaTipoRolEventual.get(id);
			if ( tipoRol.getNombre().contains(nombreTipoRol) ){
				return tipoRol;
			}
		}
		return null;
	}
	
	public boolean validateFieldsRubroEventual(){
		if(getCmbRubroEventual().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Rubro Eventual!", SpiritAlert.WARNING);
			getCmbRubroEventual().grabFocus();
			return false;
		}
		if(getCmbTipoRolCobro().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Tipo de Rol!", SpiritAlert.WARNING);
			getCmbTipoRolCobro().grabFocus();
			return false;
		}
		if(getCmbFechaCobro().getDate() == null){
			SpiritAlert.createAlert("Debe seleccionar una Fecha!", SpiritAlert.WARNING);
			getCmbFechaCobro().grabFocus();
			return false;
		}
		if(getTxtValor().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar un Valor!", SpiritAlert.WARNING);
			getTxtValor().grabFocus();
			return false;
		}
		if ( getRbEmitirChequeSi().isSelected() ){
			if ( getCmbTipoRolPago().getSelectedItem() == null ){
				SpiritAlert.createAlert("Debe seleccionar Tipo de Rol para el pago !!", SpiritAlert.WARNING);
				getCmbTipoRolPago().grabFocus();
				return false;
			}
			if ( getCmbFechaPago().getDate() == null ){
				SpiritAlert.createAlert("Debe seleccionar fecha de pago !!", SpiritAlert.WARNING);
				getCmbFechaPago().grabFocus();
				return false;
			} else{
				/*Calendar calPago = getCmbFechaPago().getCalendar();
				Calendar calCobro = getCmbFechaCobro().getCalendar();
				if ( calPago.before(calCobro) ){
					SpiritAlert.createAlert("Fecha de pago debe ser mayor que Fecha de Cobro !!", SpiritAlert.WARNING);
					getCmbFechaPago().grabFocus();
					return false;
				}*/
			}
		}
		return true;
	}
	
	private void crearRubroEventual(java.util.Date fechaCobro, String observacionExtra, TipoRolIf tipoRolCobro,java.util.Date fechaPago, TipoRolIf tipoRolPago,Long identificacion) throws GenericBusinessException {
		RubroEventualData dataRubroEventual = new RubroEventualData();
		RubroIf rubro = (RubroIf)getCmbRubroEventual().getSelectedItem();
		//TipoRolIf tipoRolCobro = (TipoRolIf)getCmbTipoRolCobro().getSelectedItem();
		
		dataRubroEventual.setRubroId(rubro.getId());
		dataRubroEventual.setFechaCobro(new java.sql.Date(fechaCobro.getTime()));
		
		dataRubroEventual.setTipoRolIdCobro(tipoRolCobro.getId());
		BigDecimal valor = BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtValor().getText())));
		dataRubroEventual.setValor(valor);
		
		EstadoRubroEventual estadoEventual = EstadoRubroEventual.EMITIDO;
		String letraEstadoEventual = estadoEventual.getLetra();
		dataRubroEventual.setEstado(letraEstadoEventual);
		
		dataRubroEventual.setObservacion(getTxtObservacionEventual().getText()+observacionExtra);
		
		if ( getRbEmitirChequeSi().isSelected() ){
			//TipoRolIf tipoRolPago = (TipoRolIf)getCmbTipoRolPago().getSelectedItem();
			dataRubroEventual.setTipoRolIdPago(tipoRolPago.getId());
			//Date fechaPago = getCmbFechaPago().getDate();
			dataRubroEventual.setFechaPago(new java.sql.Date(fechaPago.getTime()));
		}
		
		dataRubroEventual.setIdentificacion(identificacion);
		
		ArrayList<RubroEventualIf> rubrosEventualesColleccion = mapaRubrosEventualesCollection.get(PanelActivo.EMITIDOS);
		rubrosEventualesColleccion.add(dataRubroEventual);
		
		Object[] fila = crearFilaTabla(dataRubroEventual); 
		modeloTablaRubroEventual.addRow(fila);
	}
	
	private Long buscarIdentificacionMaxima(){
		Long idMaxima = 100L;
		
		ArrayList<RubroEventualIf> rubrosEventualesColleccion = mapaRubrosEventualesCollection.get(PanelActivo.EMITIDOS);
		for ( RubroEventualIf re : rubrosEventualesColleccion ){
			Long maximaIdentificacionRe = re.getIdentificacion() != null ? re.getIdentificacion() : 0L;
			idMaxima = Math.max(idMaxima, maximaIdentificacionRe);
		}
		rubrosEventualesColleccion = mapaRubrosEventualesCollection.get(PanelActivo.AUTORIZADOS);
		for ( RubroEventualIf re : rubrosEventualesColleccion ){
			Long maximaIdentificacionRe = re.getIdentificacion() != null ? re.getIdentificacion() : 0L; 
			idMaxima = Math.max(idMaxima, maximaIdentificacionRe);
		}
		rubrosEventualesColleccion = mapaRubrosEventualesCollection.get(PanelActivo.PAGADOS);
		for ( RubroEventualIf re : rubrosEventualesColleccion ){
			Long maximaIdentificacionRe = re.getIdentificacion() != null ? re.getIdentificacion() : 0L;
			idMaxima = Math.max(idMaxima, maximaIdentificacionRe);
		}
		
		return idMaxima;
	}
	
	private void removerRubroEventual(){
		try{
			
			if ( panelActivo != PanelActivo.EMITIDOS ){
				SpiritAlert.createAlert("Solo se puede eliminar Rubros Eventuales en estado EMITIDO !!",SpiritAlert.WARNING);
				return;
			}
			
			if ( rubroEventualIf == null ){
				SpiritAlert.createAlert("Rubro Eventual no seleccionado !!", SpiritAlert.WARNING);
				return;
			}
			
			int filaSeleccionada = getTblRubrosEventualesEmitidos().getSelectedRow();
			if( filaSeleccionada != -1 && validateFieldsRubroEventual() ){
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				
				ArrayList<RubroEventualIf> rubrosEventualesColleccion = mapaRubrosEventualesCollection.get(PanelActivo.EMITIDOS);
				
				int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					filaSeleccionada = getTblRubrosEventualesEmitidos().convertRowIndexToModel(filaSeleccionada);
					
					if(rubrosEventualesColleccion.get(filaSeleccionada).getId() != null){
						rubrosEventualesRemovidos.add(rubrosEventualesColleccion.get(filaSeleccionada));
					}
					rubrosEventualesColleccion.remove(filaSeleccionada);
					//modeloTablaRubroEventual.removeRow(filaSeleccionada);
				}
				cargarTablaRubroEventuales(getTblRubrosEventualesEmitidos(),rubrosEventualesColleccion);
				rubroEventualIf = null;
			}else{
				SpiritAlert.createAlert("Debe seleccionar una fila para remover!", SpiritAlert.WARNING);
			}
		} catch( Exception e ){
			SpiritAlert.createAlert("Error al Eliminar registro !!", SpiritAlert.ERROR);
		}
	}
	
	public void actualizarRubroEventual(){
		
		JTable tabla = getTablaActiva();
		
		if ( rubroEventualIf == null ){
			SpiritAlert.createAlert("Rubro Eventual no seleccionado !!", SpiritAlert.WARNING);
			return;
		}
		
		int filaSeleccionada = tabla.getSelectedRow();
		if ( filaSeleccionada != -1 && validateFieldsRubroEventual()){
			try{
				filaSeleccionada = tabla.convertRowIndexToModel(filaSeleccionada);
				
				if ( panelActivo == PanelActivo.EMITIDOS ){
					RubroIf rubro = (RubroIf)getCmbRubroEventual().getSelectedItem();
					rubroEventualIf.setRubroId(rubro.getId());
					Date fecha = getCmbFechaCobro().getDate();
					rubroEventualIf.setFechaCobro(new java.sql.Date(fecha.getTime()));
					TipoRolIf tipoRol = (TipoRolIf)getCmbTipoRolCobro().getSelectedItem();
					rubroEventualIf.setTipoRolIdCobro(tipoRol.getId());
					BigDecimal valor = BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtValor().getText())));
					rubroEventualIf.setValor(valor);
					
					if ( getRbEmitirChequeSi().isSelected() ){
						TipoRolIf tipoRolPago = (TipoRolIf)getCmbTipoRolPago().getSelectedItem();
						rubroEventualIf.setTipoRolIdPago(tipoRolPago.getId());
						Date fechaPago = getCmbFechaPago().getDate();
						rubroEventualIf.setFechaPago(new java.sql.Date(fechaPago.getTime()));
					} else if ( getRbEmitirChequeNo().isSelected() ){
						rubroEventualIf.setTipoRolIdPago(null);
						rubroEventualIf.setFechaPago(null);
					}
				} 
					
				rubroEventualIf.setObservacion(getTxtObservacionEventual().getText());
				
				DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
				Object oFila = modelo.getValueAt(filaSeleccionada, COLUMNA_IDENTIFICACION);
				Long identificacion = null;
				if ( oFila instanceof String ){
					String sIdentificacion = (String)oFila;
					identificacion = sIdentificacion!=null && !"".equals(sIdentificacion) ? Long.valueOf(sIdentificacion): null;
				} else
					identificacion = (Long)oFila;
				rubroEventualIf.setIdentificacion(identificacion);
				
				ArrayList<RubroEventualIf> rubrosEventualesColleccion = mapaRubrosEventualesCollection.get(panelActivo);
				cargarTablaRubroEventuales(tabla,rubrosEventualesColleccion);
			} catch(GenericBusinessException e){
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			} catch (Exception e){
				e.printStackTrace();
				SpiritAlert.createAlert("Erro general en la actualizacion !!", SpiritAlert.ERROR);
			}
		}else{
			SpiritAlert.createAlert("Debe seleccionar una fila para actualizar!", SpiritAlert.WARNING);
		}
	}

	private JTable getTablaActiva() {
		JTable tabla = null;
		if ( panelActivo == PanelActivo.EMITIDOS )
			tabla = getTblRubrosEventualesEmitidos();
		else if ( panelActivo == PanelActivo.AUTORIZADOS )
			tabla = getTblRubrosEventualesAutorizados();
		else if ( panelActivo == PanelActivo.PAGADOS )
			tabla = getTblRubrosEventualesPagados();
		return tabla;
	}
	
	private void busquedaEmpleado() throws GenericBusinessException, ParseException {
		EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria("de Empleados", Parametros.getIdEmpresa());
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if ( popupFinder.getElementoSeleccionado()!=null ){
			limpiarTabla(getTblRubrosEventualesEmitidos());
			limpiarTabla(getTblRubrosEventualesAutorizados());
			limpiarTabla(getTblRubrosEventualesPagados());
			empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
			getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
			
			//int mes = Utilitarios.getMesInt(mesSeleccionado);
			//int anio = Integer.valueOf(anioSeleccionado);
			//int ultimoDiaMes = Utilitarios.fechaHoy(mes, anio).getDate();
			//long fecha = new GregorianCalendar(anio,mes,ultimoDiaMes).getTime().getTime();
			//java.sql.Date fechaActual = new java.sql.Date( new java.util.Date().getTime() );
			
			Calendar calFechaMedia = new GregorianCalendar();
			calFechaMedia.set(Calendar.DATE,1);
			java.sql.Date fechaMedia = new java.sql.Date(calFechaMedia.getTime().getTime());
			int diaFinal = calFechaMedia.getActualMaximum(Calendar.DATE);
			calFechaMedia = new GregorianCalendar();
			calFechaMedia.set(Calendar.DATE,diaFinal);
			java.sql.Date fechaMediaMax = new java.sql.Date(calFechaMedia.getTime().getTime());
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("fechaMediaContrato", fechaMedia);
			mapa.put("fechaMediaContratoMax", fechaMediaMax);
			mapa.put("empleadoId", empleadoIf.getId());
			//mapa.put("estado", "A");
			
			Collection<ContratoIf> contratos = SessionServiceLocator.getContratoSessionService().findContratoByQueryConFecha(mapa);
			if ( contratos.size() == 1 ){
				contratoIf = contratos.iterator().next();
				getTxtContrato().setText(contratoIf.getCodigo());
				buscarRubroEventuales(EstadoRubroEventual.EMITIDO);
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
				SpiritAlert.createAlert("Debe elegir un empleado !!", SpiritAlert.INFORMATION);
				return;
			}
			
			/*int mes = Utilitarios.getMesInt(mesSeleccionado);
			int anio = Integer.valueOf(anioSeleccionado);
			int ultimoDiaMes = Utilitarios.fechaHoy(mes, anio).getDate();
			long fecha = new GregorianCalendar(anio,mes,ultimoDiaMes).getTime().getTime();
			*/
			//java.sql.Date fechaActual = new java.sql.Date( new java.util.Date().getTime() );
			
			Calendar calFechaMedia = new GregorianCalendar();
			calFechaMedia.set(Calendar.DATE,1);
			java.sql.Date fechaMedia = new java.sql.Date(calFechaMedia.getTime().getTime());
			int diaFinal = calFechaMedia.getActualMaximum(Calendar.DATE);
			calFechaMedia = new GregorianCalendar();
			calFechaMedia.set(Calendar.DATE,diaFinal);
			java.sql.Date fechaMediaMax = new java.sql.Date(calFechaMedia.getTime().getTime());
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("fechaMediaContrato", fechaMedia);
			mapa.put("fechaMediaContratoMax", fechaMediaMax);
			mapa.put("empleadoId", empleadoIf.getId());
			//mapa.put("estado", "A");
			
			int tamanoLista= SessionServiceLocator.getContratoSessionService().getContratoListSize(mapa);
			if ( tamanoLista > 0 ){
				
				ContratoCriteria contratoCriteria = new ContratoCriteria("de Contratos");
				contratoCriteria.setQueryBuilded(mapa);
				contratoCriteria.setResultListSize(tamanoLista);

				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	contratoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					limpiarTabla(getTblRubrosEventualesEmitidos());
					limpiarTabla(getTblRubrosEventualesAutorizados());
					limpiarTabla(getTblRubrosEventualesPagados());
					contratoIf = (ContratoIf) popupFinder.getElementoSeleccionado();
					getTxtContrato().setText(contratoIf.getCodigo());
					buscarRubroEventuales(EstadoRubroEventual.EMITIDO);
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
	
	private void initKeyListeners(){
		getTxtObservacionEventual().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACION));
		getTxtValor().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtValor().addKeyListener(new NumberTextFieldDecimal());
		getTxtNumeroPagos().addKeyListener(new TextChecker(MAX_LONGITUD_NUMERO_PAGOS));
		getTxtNumeroPagos().addKeyListener(new NumberTextFieldDecimal());
	}
	
	private void anchoComlumnas(){
		JTable tabla = getTblRubrosEventualesEmitidos();
		establecerAnchoColumnas(tabla);
		tabla = getTblRubrosEventualesAutorizados();
		establecerAnchoColumnas(tabla);
		tabla = getTblRubrosEventualesPagados();
		establecerAnchoColumnas(tabla);
		
	}

	private void establecerAnchoColumnas(JTable tabla) {
		TableColumn anchoColumnaRubroEventual = tabla.getColumnModel().getColumn(0);
		anchoColumnaRubroEventual.setPreferredWidth(200);
		anchoColumnaRubroEventual = tabla.getColumnModel().getColumn(1);
		anchoColumnaRubroEventual.setPreferredWidth(100);
		anchoColumnaRubroEventual = tabla.getColumnModel().getColumn(2);
		anchoColumnaRubroEventual.setPreferredWidth(200);
		anchoColumnaRubroEventual = tabla.getColumnModel().getColumn(3);
		anchoColumnaRubroEventual.setPreferredWidth(80);
		anchoColumnaRubroEventual = tabla.getColumnModel().getColumn(4);
		anchoColumnaRubroEventual.setPreferredWidth(60);
	}
	
	private void buscarRubroEventuales(EstadoRubroEventual... estadoRubroEventual) throws GenericBusinessException{
		
		if ( contratoIf != null ){
			
			Map<String,java.sql.Date> mapaFechas = new HashMap<String,java.sql.Date>();
			Date fci = null,fcf = null;
			JTable tabla = null;
			if ( estadoRubroEventual.length == 1 ){
				if ( estadoRubroEventual[0] == EstadoRubroEventual.EMITIDO ){
					tabla = getTblRubrosEventualesEmitidos();
					fci = getCmbFechaInicioEmitido().getDate();
					fcf = getCmbFechaFinEmitido().getDate();
				} else if ( estadoRubroEventual[0] == EstadoRubroEventual.PAGADO ){
					tabla = getTblRubrosEventualesPagados();
					fci = getCmbFechaInicioPagado().getDate();
					fcf = getCmbFechaFinPagado().getDate();
				}
			} else if ( estadoRubroEventual.length == 2 ){
				if ( estadoRubroEventual[0] == EstadoRubroEventual.AUTORIZADO ||  estadoRubroEventual[1] == EstadoRubroEventual.AUTORIZADO_PARCIAL ){
					tabla = getTblRubrosEventualesAutorizados();
					fci = getCmbFechaInicioAutorizado().getDate();
					fcf = getCmbFechaFinAutorizado().getDate();
				}
			} else 
				throw new GenericBusinessException("Maximo 2 estados de rubros considerados !!");
				  
			//if ( fci!=null && fcf!=null ){
				mapaFechas.put("fechaCobroInicio", fci != null ? new java.sql.Date(fci.getTime()) : null);
				mapaFechas.put("fechaCobroFin", fcf != null ? new java.sql.Date(fcf.getTime()) : null);
			//}
			
			Map<String, Object> mapaRubrosEventuales = new HashMap<String, Object>();
			mapaRubrosEventuales.put("contratoId", contratoIf.getId());
			rubroEventualIf = null;
			ArrayList<RubroEventualIf> rubrosEventualesColleccion = mapaRubrosEventualesCollection.get(panelActivo);
			rubrosEventualesColleccion = null;
			
			Set<Long> tiposRolesCobro = new HashSet<Long>();
			for ( int i = 0 ; i < getCmbTipoRolCobro().getItemCount() ; i++ ){
				TipoRolIf tr = (TipoRolIf) getCmbTipoRolCobro().getItemAt(i);
				tiposRolesCobro.add( tr.getId() );
			}
			mapaRubrosEventuales.put("tiposRolesCobro", tiposRolesCobro);
			
			if ( estadoRubroEventual.length == 1 )
				rubrosEventualesColleccion =  (ArrayList) SessionServiceLocator.getRubroEventualSessionService()
					.findRubroEventualByQueryByEstados(
						mapaRubrosEventuales,mapaFechas,null,true,TipoRubro.EVENTUAL_BENEFICIOS_SOCIALES.getLetra(),
						estadoRubroEventual[0].getLetra() );
			else if ( estadoRubroEventual.length == 2 )
				rubrosEventualesColleccion =  (ArrayList) SessionServiceLocator.getRubroEventualSessionService()
					.findRubroEventualByQueryByEstados(
						mapaRubrosEventuales,mapaFechas,null,true,TipoRubro.EVENTUAL_BENEFICIOS_SOCIALES.getLetra(),
						estadoRubroEventual[0].getLetra(),estadoRubroEventual[1].getLetra() );
			
			mapaRubrosEventualesCollection.put(panelActivo,rubrosEventualesColleccion);
			
			rubrosEventualesRemovidos = null ; 
			rubrosEventualesRemovidos = new ArrayList<RubroEventualIf>();
			//cargarTablaRubroEventuales(getTblRubrosEventualesEmitidos(),rubrosEventualesColleccion);
			cargarTablaRubroEventuales(tabla,rubrosEventualesColleccion);
		}// else 
		//	SpiritAlert.createAlert("Debe seleccionar un contrato !!",SpiritAlert.WARNING);
	}
	
	private void cargarTablaRubroEventuales(JTable tabla,ArrayList<RubroEventualIf> rubrosEventualesColleccion) throws GenericBusinessException{
		limpiarTabla(tabla);
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		for ( RubroEventualIf rubroEventual : rubrosEventualesColleccion ){
			Object[] fila = crearFilaTabla(rubroEventual);
			modelo.addRow(fila);
		}

		showUpdateMode();
	}
	
	private Object[] crearFilaTabla( RubroEventualIf rubroEventual ) throws GenericBusinessException{
		RubroIf rubro = Rubros.verificarRubrosEnMapa(mapaRubroEventual, null, rubroEventual.getRubroId());
		String nombreRubro = rubro.getNombre();
		//String nombreRubro = mapaRubroEventual.get(rubroEventual.getRubroId()).getNombre();
		TipoRolIf tipoRolCobro = mapaTipoRolEventual.get(rubroEventual.getTipoRolIdCobro());
		Object[] fila = { 	
				nombreRubro,
				Utilitarios.getFechaAnio(rubroEventual.getFechaCobro()),
				tipoRolCobro.getNombre(),
				rubroEventual.getValor(),
				EstadoRubroEventual.getRubroEventualByLetra(rubroEventual.getEstado()),
				rubroEventual.getIdentificacion()!=null?rubroEventual.getIdentificacion():""
		};
		return fila;
	}
	
	private void cargarCombos(){
		cargarTipoRol();
		cargarComboRubroEventual();
		cargarCombosTipoRol();
	}
	
	private void cargarComboRubroEventual(){
		try {
			Map<String, Object> mapa =  new HashMap<String, Object>();
			//mapa.put("tipoRubro",RUBRO_EVENTUAL);
			mapa.put("empresaId", Parametros.getIdEmpresa());
			mapaRubroEventual = null;
			mapaRubroEventual = new HashMap<Long, RubroIf>();
			
			ArrayList<RubroIf> rubros = new ArrayList<RubroIf>();
			
			TipoRolIf tipoRolCobro = (TipoRolIf) getCmbTipoRolCobro().getSelectedItem();
			if ( tipoRolCobro != null ){
				mapa.put("tiporolId", tipoRolCobro.getId());
			
				ArrayList<RubroIf> rubrosEventuales = (ArrayList<RubroIf>) SessionServiceLocator.getRubroSessionService()
					.findRubroByQueryByTiposRubro(mapa,TipoRubro.EVENTUAL_BENEFICIOS_SOCIALES.getLetra() );
				for ( RubroIf r : rubrosEventuales ){
					rubros.add(r);
					mapaRubroEventual.put(r.getId(), r);
				}
				
			}
			refreshCombo(getCmbRubroEventual(), rubros);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en carga de Rubros Eventuales", SpiritAlert.ERROR);
		}
	}
	
	private void cargarCombosTipoRol(){
		try {
			Map<String, Object> mapa =  new HashMap<String, Object>();
			mapa.put("rubroProvisionado",TipoRolProvisionado.SI.getLetra());
			mapa.put("empresaId", Parametros.getIdEmpresa());
			List tiposRol = (List) SessionServiceLocator.getTipoRolSessionService().findTipoRolByQuery(mapa);
			refreshCombo(getCmbTipoRolCobro(), tiposRol);
			getCmbTipoRolCobro().setSelectedItem(null);
			getCmbTipoRolCobro().repaint();
			cargarComboTipoRolPago(new ArrayList());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarComboTipoRolPago(List lista){
		refreshCombo(getCmbTipoRolPago(), lista);
		//getCmbTipoRolPago().setSelectedItem(null);
		getCmbTipoRolPago().repaint();
	}
	
	@Override
	public void delete() {
		try{
			
			if ( panelActivo != PanelActivo.EMITIDOS ){
				SpiritAlert.createAlert("Solo se puede eliminar Rubros Eventuales en estado EMITIDO !!",SpiritAlert.WARNING);
				return;
			}
			
			int filaSeleccionada = getTblRubrosEventualesEmitidos().getSelectedRow();
			if ( filaSeleccionada >= 0 ){
				ArrayList<RubroEventualIf> rubrosEventualesColleccion = mapaRubrosEventualesCollection.get(PanelActivo.EMITIDOS);
				
				filaSeleccionada = getTblRubrosEventualesEmitidos().convertRowIndexToModel(filaSeleccionada);
				RubroEventualIf rubroEventualSeleccionado = rubrosEventualesColleccion.get(filaSeleccionada);
				SessionServiceLocator.getRolPagoSessionService().eliminarRubroEventual(rubroEventualSeleccionado.getId());
			}
			SpiritAlert.createAlert("Rubro Eventual eliminado con éxito !!",SpiritAlert.INFORMATION);
			showSaveMode();
			
		} catch(GenericBusinessException e){
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
		}
	}

	@Override
	public void duplicate() {
	}

	@Override
	public void find() {
	}

	public void report() {
	}

	public void save() {
		SpiritAlert.createAlert("Panel solo para agregar Rubros Eventuales a Contratos Existentes. !!", SpiritAlert.INFORMATION);
	}

	private void actualizarIdentificacionesRubrosEventuales(){
		JTable tabla = getTblRubrosEventualesEmitidos();
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel(); 
		ArrayList<RubroEventualIf> rubrosEventualesColleccion = mapaRubrosEventualesCollection.get(PanelActivo.EMITIDOS);
		actualizarIdentificacionPorTabla(tabla, modelo,rubrosEventualesColleccion);
		
		tabla = getTblRubrosEventualesAutorizados();
		modelo = (DefaultTableModel) tabla.getModel();
		rubrosEventualesColleccion = mapaRubrosEventualesCollection.get(PanelActivo.AUTORIZADOS);
		actualizarIdentificacionPorTabla(tabla, modelo,rubrosEventualesColleccion);
		
		tabla = getTblRubrosEventualesPagados();
		modelo = (DefaultTableModel) tabla.getModel();
		rubrosEventualesColleccion = mapaRubrosEventualesCollection.get(PanelActivo.PAGADOS);
		actualizarIdentificacionPorTabla(tabla, modelo,rubrosEventualesColleccion);
	}

	private void actualizarIdentificacionPorTabla(JTable tabla,
			DefaultTableModel modelo,
			ArrayList<RubroEventualIf> rubrosEventualesColleccion) {
		if ( modelo.getRowCount() == rubrosEventualesColleccion.size() ){
			for ( int i=0 ; i < modelo.getRowCount()  ; i++ ){
				//int fila = tabla.convertRowIndexToModel(i);
				Object oFila = modelo.getValueAt(i, COLUMNA_IDENTIFICACION) ;
				Long identificacion = null;
				if ( oFila instanceof String ){
					String sIdentificacion = (String)oFila;
					identificacion = sIdentificacion!=null && !"".equals(sIdentificacion) ? Long.valueOf(sIdentificacion): null;
				} else
					identificacion = (Long)oFila;
				RubroEventualIf re = rubrosEventualesColleccion.get(i);
				re.setIdentificacion(identificacion);
			}
		}
	}
	
	public void update() {
		try {
			
			actualizarIdentificacionesRubrosEventuales();
			
			ArrayList<RubroEventualIf> rubrosEventualesEmitidosColleccion = mapaRubrosEventualesCollection.get(PanelActivo.EMITIDOS);
			ArrayList<RubroEventualIf> rubrosEventualesAutorizadosColleccion = mapaRubrosEventualesCollection.get(PanelActivo.AUTORIZADOS);
			ArrayList<RubroEventualIf> rubrosEventualesPagadosColleccion = mapaRubrosEventualesCollection.get(PanelActivo.PAGADOS);
			
			SessionServiceLocator.getRolPagoSessionService().actualizarRubroEventuales(
					contratoIf.getId(), 
					rubrosEventualesEmitidosColleccion, rubrosEventualesRemovidos,
					rubrosEventualesAutorizadosColleccion,rubrosEventualesPagadosColleccion);
			SpiritAlert.createAlert("Rubros Eventuales guardados con éxito !!", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al guardar Rubros Eventuales !!", SpiritAlert.ERROR);
		}
	}

	public boolean validateFields() {
		return false;
	}

	public void addDetail() {
		
	}

	public boolean isEmpty() {
		return false;
	}

	public void refresh() {
		cargarCombos();
		try {
			buscarRubroEventuales(EstadoRubroEventual.EMITIDO);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void showFindMode() {
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		getTxtContrato().setBackground(getBackground());
		getTxtEmpleado().setBackground(getBackground());
	}

	public void showUpdateMode() {
		setUpdateMode();
	}

	public void updateDetail() {
	}
	
	public void deleteDetail() {
		
	}
	
}
