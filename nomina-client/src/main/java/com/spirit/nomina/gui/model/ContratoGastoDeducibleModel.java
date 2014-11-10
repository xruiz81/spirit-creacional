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
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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
import com.spirit.nomina.entity.ContratoGastoDeducibleData;
import com.spirit.nomina.entity.ContratoGastoDeducibleIf;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.GastoDeducibleIf;
import com.spirit.nomina.gui.criteria.ContratoCriteria;
import com.spirit.nomina.gui.criteria.GastoDeducibleCriteria;
import com.spirit.nomina.gui.panel.JPContratoGastoDeducible;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class ContratoGastoDeducibleModel extends JPContratoGastoDeducible {

	private static final long serialVersionUID = 5735861570034868999L;
	
	private static final int MAX_LONGITUD_VALOR = 10;
	
	private final int COLUMNA_CODIGO_CONTRATO = 0;
	private final int COLUMNA_NOMBRE_EMPLEADO = 1;
	private final int COLUMNA_GASTO_DEDUCIBLE = 2;
	private final int COLUMNA_ANIO = 3;
	private final int COLUMNA_VALOR = 4;
		
	private DecimalFormat formatoDosDecimales = new DecimalFormat("0.00");
	
	private ContratoGastoDeducibleIf contratoGastoDeducibleIf = null;
	private EmpleadoIf empleadoIf = null;
	private ContratoIf contratoIf = null;
	private GastoDeducibleIf gastoDeducibleIf = null;
	private Map<Long, ContratoIf> mapaContrato =  new HashMap<Long, ContratoIf>();
	private Map<Long, EmpleadoIf> mapaEmpleado =  new HashMap<Long, EmpleadoIf>();
	private Map<Long, GastoDeducibleIf> mapaGastoDeducible =  new HashMap<Long, GastoDeducibleIf>();
	private ArrayList<ContratoGastoDeducibleIf> contratoGastosDeduciblesRemovidos = null;
	private ArrayList<ContratoGastoDeducibleIf> contratoGastoDeducibleColleccion = null;
	
	private Double total = 0D;
	
	public ContratoGastoDeducibleModel() {
		iniciarComponentes();
		initListeners();
		initKeyListeners();
		showSaveMode();
		new HotKeyComponent(this);
	}

	public void clean() {
		
		contratoGastoDeducibleIf = null;
		
		contratoGastosDeduciblesRemovidos = null;
		contratoGastosDeduciblesRemovidos = new ArrayList<ContratoGastoDeducibleIf>();
		
		contratoGastoDeducibleColleccion = null;
		contratoGastoDeducibleColleccion = new ArrayList<ContratoGastoDeducibleIf>();
		
		total = 0D;
		
		empleadoIf = null;
		getTxtEmpleado().setText("");
		contratoIf = null;
		getTxtContrato().setText("");
		gastoDeducibleIf = null;
		getTxtGastoDeducible().setText("");
		
		getTxtValor().setText("");
		
		limpiarTabla(getTblContratoGastoDeducible());
		
	}
	
	private void iniciarComponentes(){
		
		cargarGastoDeducible();
		
		getCmbFechaMesAnio().setLocale(Utilitarios.esLocale);
		getCmbFechaMesAnio().setShowNoneButton(true);
		getCmbFechaMesAnio().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbFechaMesAnio().setEditable(false);
		
		anchoColumnas();
		
		getTxtValor().setText("0.00");
		
		getBtnEmpleado().setText("");
		getBtnEmpleado().setToolTipText("Buscar Empleado");
		getBtnEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getBtnContrato().setText("");
		getBtnContrato().setToolTipText("Buscar Contrato");
		getBtnContrato().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getBtnGastoDeducible().setText("");
		getBtnGastoDeducible().setToolTipText("Buscar Gasto Deducible");
		getBtnGastoDeducible().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getBtnAgregarDeducible().setText("");
		getBtnAgregarDeducible().setToolTipText("Agregar Gasto Deducible");
		getBtnAgregarDeducible().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnActualizarDeducible().setText("");
		getBtnActualizarDeducible().setToolTipText("Actualizar Gasto Deducible");
		getBtnActualizarDeducible().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnRemoverDeducible().setText("");
		getBtnRemoverDeducible().setToolTipText("Eliminar Gasto Deducible");
		getBtnRemoverDeducible().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		
		setSorterTable(getTblContratoGastoDeducible());
		getTblContratoGastoDeducible().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblContratoGastoDeducible().getColumnModel().getColumn(COLUMNA_VALOR).setCellRenderer(
				new NumberCellRenderer("#####0.00",NumberCellRenderer.DERECHA) );
		
	}
	
	private void anchoColumnas(){
		TableColumnModel modelo = getTblContratoGastoDeducible().getColumnModel();
		
		TableColumn anchoColumnaRubroContrato = modelo.getColumn(COLUMNA_CODIGO_CONTRATO);
		//anchoColumnaRubroContrato.setPreferredWidth(100);
		anchoColumnaRubroContrato.setMaxWidth(100);
		
		anchoColumnaRubroContrato = modelo.getColumn(COLUMNA_NOMBRE_EMPLEADO);
		anchoColumnaRubroContrato.setMinWidth(300);
		anchoColumnaRubroContrato.setMaxWidth(300);
		anchoColumnaRubroContrato.setPreferredWidth(300);
		
		anchoColumnaRubroContrato = modelo.getColumn(COLUMNA_GASTO_DEDUCIBLE);
		anchoColumnaRubroContrato.setPreferredWidth(250);
		anchoColumnaRubroContrato.setMaxWidth(250);
		
		anchoColumnaRubroContrato = modelo.getColumn(COLUMNA_ANIO);
		anchoColumnaRubroContrato.setMinWidth(90);
		anchoColumnaRubroContrato.setPreferredWidth(90);
		anchoColumnaRubroContrato.setMaxWidth(90);
		
	}
	
	private void cargarGastoDeducible(){
		try {
			Collection<GastoDeducibleIf> gastos = SessionServiceLocator.getGastoDeducibleSessionService()
				.findGastoDeducibleByEmpresaId(Parametros.getIdEmpresa());
			for ( GastoDeducibleIf gasto : gastos ){
				mapaGastoDeducible.put(gasto.getId(), gasto);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar Tipos de Roles Eventuales !!", SpiritAlert.ERROR);
		}
		
	}
	
	private void initListeners(){
		
		getBtnEmpleado().addActionListener(alBuscarEmpleado);
		getBtnContrato().addActionListener(alBuscarContrato);
		getBtnGastoDeducible().addActionListener(alBuscarGastoDeducible);
		
		getBtnAgregarDeducible().addActionListener(alAgregarContratoGastoDeducible);
		getBtnActualizarDeducible().addActionListener(alActualizarContratoGastoDeducible);
		getBtnRemoverDeducible().addActionListener(alRemoverContratoGastoDeducible);
		
		getTblContratoGastoDeducible().addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent arg0) {
				seleccionarFila((JTable)arg0.getSource());
			}}
		);
		
		getTblContratoGastoDeducible().addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent arg0) {
				seleccionarFila((JTable)arg0.getSource());
			}}
		);
		
	}
	
	ActionListener alConsultar = new ActionListener(){
		
		public void actionPerformed(ActionEvent e) {
			try {
				buscarContratoGastosDeducibles();

			} catch (GenericBusinessException e1) {
				e1.printStackTrace();
				SpiritAlert.createAlert("Error en Busqueda de Gastos Deducibles por Contrato !!",SpiritAlert.ERROR);
			}
		}
		
	};
	
	ActionListener alAgregarContratoGastoDeducible =  new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			agregarGastoDeducible();
		}
	};
	
	ActionListener alActualizarContratoGastoDeducible =  new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			actualizarGastoDeducible();
		}
	};
	
	ActionListener alRemoverContratoGastoDeducible = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			removerGastoDeducible();
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
			/*try {
				buscarContratoGastosDeducibles();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}*/
		}	
	};
	
	ActionListener alBuscarGastoDeducible =  new ActionListener(){
		public void actionPerformed(ActionEvent evento) {
			buscarGastoDeducible();
		}	
	};
	
	private void seleccionarFila(JTable tabla){
		int filaTabla = tabla.getSelectedRow();
		if ( filaTabla >= 0 && filaTabla < contratoGastoDeducibleColleccion.size() ){
			filaTabla = tabla.convertRowIndexToModel(filaTabla);
			contratoGastoDeducibleIf = contratoGastoDeducibleColleccion.get(filaTabla);
			
			contratoIf = mapaContrato.get(contratoGastoDeducibleIf.getContratoId());
			getTxtContrato().setText(contratoIf.getCodigo());
			
			empleadoIf = mapaEmpleado.get(contratoIf.getEmpleadoId());
			getTxtEmpleado().setText(getNombreEmpleado(empleadoIf));
			
			gastoDeducibleIf = mapaGastoDeducible.get(contratoGastoDeducibleIf.getGastoDeducibleId());
			getTxtGastoDeducible().setText(gastoDeducibleIf.getNombre());
			
			Date fechaSql = contratoGastoDeducibleIf.getFecha();
			java.util.Date fecha = fechaSql != null ? new java.util.Date(fechaSql.getTime()) : null ;
			getCmbFechaMesAnio().setDate(fecha); 
			//getSpnAnio().setValue( Integer.valueOf(contratoGastoDeducibleIf.getAnio()) );
			
			getTxtValor().setText(formatoDosDecimales.format(contratoGastoDeducibleIf.getValor().doubleValue()));
			
		}
	}
	
	public void agregarGastoDeducible() {
		try{
			if (validateFields() && verificarIngreso()) {
				ContratoGastoDeducibleIf cgd = new ContratoGastoDeducibleData();
				cgd.setContratoId(contratoIf.getId());
				cgd.setGastoDeducibleId(gastoDeducibleIf.getId());
				Calendar calendar = new GregorianCalendar();
				java.util.Date fecha = getCmbFechaMesAnio().getDate();
				calendar.setTime(fecha);
				
				cgd.setFecha(new Date(fecha.getTime()));
				String valorS = getTxtValor().getText().replace(",", "");
				cgd.setValor(new BigDecimal(valorS));
				contratoGastoDeducibleColleccion.add(cgd);
				contratoGastoDeducibleIf = null;
				getTxtValor().setText("");
				cargarTablaGastosDeducibles();
			}
		} catch(GenericBusinessException e){
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al agregar Gasto Deducible !!", SpiritAlert.ERROR);
		}
	}
	
	public boolean validateFields(){
		
		if(empleadoIf == null){
			SpiritAlert.createAlert("Debe Elegir un empleado !!", SpiritAlert.WARNING);
			getTxtValor().grabFocus();
			return false;
		}
		
		if(contratoIf == null){
			SpiritAlert.createAlert("Debe elegir un contrato !!", SpiritAlert.WARNING);
			getTxtValor().grabFocus();
			return false;
		}
		
		if(gastoDeducibleIf == null){
			SpiritAlert.createAlert("Debe elegir un Gasto Deducible !!", SpiritAlert.WARNING);
			getTxtValor().grabFocus();
			return false;
		}
		
		java.util.Date fecha = getCmbFechaMesAnio().getDate();
		if (fecha == null){
			SpiritAlert.createAlert("Debe elegir una fecha !!", SpiritAlert.WARNING);
			getTxtValor().grabFocus();
			return false;
		}
		
		if(getTxtValor().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar un Valor!", SpiritAlert.WARNING);
			getTxtValor().grabFocus();
			return false;
		}
		
		return true;
	}

	private boolean verificarIngreso() throws GenericBusinessException{
		//Integer anio = getAnio();
		java.util.Date fecha = getCmbFechaMesAnio().getDate();
		for (ContratoGastoDeducibleIf cgd : contratoGastoDeducibleColleccion) {
			//Integer anioTmp = (Integer) getSpnAnio().getValue();
			Date fechaTmp = cgd.getFecha()!=null ? cgd.getFecha() : null;
			
			if ( fechaTmp != null ){
				//if (gastoDeducibleIf.getId().equals(cgd.getGastoDeducibleId()) 
				//		&& anioTmp.toString().equals(cgd.getAnio()) ){
				if ( gastoDeducibleIf.getId().equals(cgd.getGastoDeducibleId()) 
						&& fechaTmp.equals(fecha) ){
					SpiritAlert.createAlert("Gasto deducible para Fecha "
							+Utilitarios.getFechaCortaUppercase(fecha)
							+" ya se encuentra ingresado !!", SpiritAlert.WARNING);
					return false;
				}
			} else {
				SpiritAlert.createAlert("Ingresar fecha para "
					+mapaGastoDeducible.get(cgd.getGastoDeducibleId()).getNombre(),
					SpiritAlert.WARNING);
				return false;
			}
		}
		return true;
	}
	
	private boolean verificarActualizacion() throws GenericBusinessException{
		//Integer anio = getAnio();
		java.util.Date fecha = getCmbFechaMesAnio().getDate();
		for (ContratoGastoDeducibleIf cgd : contratoGastoDeducibleColleccion) {
			//Integer anioTmp = (Integer) getSpnAnio().getValue();
			java.util.Date fechaTmp = cgd.getFecha()!=null ? new java.util.Date() : null;
			//if (gastoDeducibleIf.getId().equals(cgd.getGastoDeducibleId()) 
			//		&& anioTmp.toString().equals(cgd.getAnio()) && contratoGastoDeducibleIf != cgd ){
			if ( fechaTmp!= null && gastoDeducibleIf.getId().equals(cgd.getGastoDeducibleId()) 
					 && fechaTmp.equals(fecha) && contratoGastoDeducibleIf != cgd ){
				SpiritAlert.createAlert("Gasto deducible para Fecha "
						+Utilitarios.getFechaCortaUppercase(fecha)
						+" ya se encuentra ingresado !!", SpiritAlert.WARNING);
				return false;
			}
		}
		return true;
	}
	
	private boolean verificarUpdate() throws GenericBusinessException{
		//Integer anio = getAnio();
		java.util.Date fecha = getCmbFechaMesAnio().getDate();
		for (ContratoGastoDeducibleIf cgdA : contratoGastoDeducibleColleccion) {
			//Integer anioTmp = (Integer) getSpnAnio().getValue();
			Date fechaTmp = cgdA.getFecha()!=null ? cgdA.getFecha() : null;
			Long gastoDeducibleId = cgdA.getGastoDeducibleId();
			if ( fechaTmp != null ){
				for ( ContratoGastoDeducibleIf cgdB : contratoGastoDeducibleColleccion ){
					
					if ( cgdA!= cgdB && gastoDeducibleId.equals(cgdB.getGastoDeducibleId()) &&
						 fechaTmp.equals(cgdB.getFecha()) ){
						SpiritAlert.createAlert("Gasto deducible para Fecha "+
							Utilitarios.getFechaCortaUppercase(fecha)+
							" ya se encuentra ingresado !!", SpiritAlert.WARNING);
						return false;
					}
					
				}
			} else {
				SpiritAlert.createAlert("Ingresar fecha para "
					+mapaGastoDeducible.get(gastoDeducibleId).getNombre(),
					SpiritAlert.WARNING);
				return false;
			}
		}
		return true;
	}
	
	private void removerGastoDeducible(){
		try{
			
			int filaSeleccionada = getTblContratoGastoDeducible().getSelectedRow();
			if( filaSeleccionada != -1 ){
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					contratoGastoDeducibleColleccion.remove(filaSeleccionada);
					if ( contratoGastoDeducibleIf.getId() != null ){
						contratoGastosDeduciblesRemovidos.add(contratoGastoDeducibleIf);
					}
					getTxtValor().setText("");
				}
				cargarTablaGastosDeducibles();
				contratoGastoDeducibleIf = null;
			}else{
				SpiritAlert.createAlert("Debe seleccionar una fila para remover!", SpiritAlert.WARNING);
			}
		} catch( Exception e ){
			e.printStackTrace();
			SpiritAlert.createAlert("Error al Eliminar registro !!", SpiritAlert.ERROR);
		}
	}
	
	public void actualizarGastoDeducible(){
		
		JTable tabla = getTblContratoGastoDeducible();
		int filaSeleccionada = tabla.getSelectedRow();
		if ( filaSeleccionada != -1 ){
			try{
				if ( validateFields() && verificarActualizacion() ){
					filaSeleccionada = tabla.convertRowIndexToModel(filaSeleccionada);
					
					contratoGastoDeducibleIf.setGastoDeducibleId(gastoDeducibleIf.getId());
					
					Calendar calendar = new GregorianCalendar();
					java.util.Date fecha = getCmbFechaMesAnio().getDate();
					calendar.setTime(fecha);
					contratoGastoDeducibleIf.setFecha(new Date(fecha.getTime()));
					
					String valorS = Utilitarios.removeDecimalFormat(getTxtValor().getText());
					BigDecimal valor = new BigDecimal(valorS);
					contratoGastoDeducibleIf.setValor(valor);
	
					cargarTablaGastosDeducibles();
				}
			} catch(GenericBusinessException e){
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			} catch (Exception e){
				e.printStackTrace();
				SpiritAlert.createAlert("Error general en la actualizacion !!", SpiritAlert.ERROR);
			}
		}else{
			SpiritAlert.createAlert("Debe seleccionar una fila para actualizar!", SpiritAlert.WARNING);
		}
	}
	
	private void busquedaEmpleado() throws GenericBusinessException, ParseException {
		EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria("de Empleados", Parametros.getIdEmpresa());
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if ( popupFinder.getElementoSeleccionado()!=null ){
			gastoDeducibleIf = null;
			getTxtGastoDeducible().setText("");
			getTxtValor().setText("");
			limpiarTabla(getTblContratoGastoDeducible());
			empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
			getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
			mapaEmpleado.put(empleadoIf.getId(), empleadoIf);
			
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
				mapaContrato.put(contratoIf.getId(), contratoIf);
				getTxtContrato().setText(contratoIf.getCodigo());
				buscarContratoGastosDeducibles();
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
					gastoDeducibleIf = null;
					getTxtGastoDeducible().setText("");
					getTxtValor().setText("");
					limpiarTabla(getTblContratoGastoDeducible());
					contratoIf = (ContratoIf) popupFinder.getElementoSeleccionado();
					getTxtContrato().setText(contratoIf.getCodigo());
					mapaContrato.put(contratoIf.getId(), contratoIf);
					buscarContratoGastosDeducibles();
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
	
	private void buscarGastoDeducible(){
		GastoDeducibleCriteria gastoDeducibleCriteria = new GastoDeducibleCriteria();
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	gastoDeducibleCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if ( popupFinder.getElementoSeleccionado()!=null ){
			
			gastoDeducibleIf = (GastoDeducibleIf) popupFinder.getElementoSeleccionado();
			getTxtGastoDeducible().setText(gastoDeducibleIf.toString());
			
		} 
	}
	
	private void initKeyListeners(){
		getTxtValor().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtValor().addKeyListener(new NumberTextFieldDecimal());
	}
	
	private void buscarContratoGastosDeducibles() throws GenericBusinessException{
		
		if ( contratoIf != null ){
			
			Map<String, Object> mapa =  new HashMap<String, Object>();
			mapa.put("contratoId", contratoIf.getId());
			//Integer anioI = (Integer) getSpnAnio().getValue();
			java.util.Date fecha = getCmbFechaMesAnio().getDate();
			//Date fechaSql = fecha != null ? new Date(fecha.getTime()) : null;
			
			//mapa.put("anio", anioI.toString());
			//mapa.put("fecha", fechaSql);
			
			contratoGastoDeducibleColleccion = (ArrayList<ContratoGastoDeducibleIf>) SessionServiceLocator
				.getContratoGastoDeducibleSessionService().findContratoGastoDeducibleByQuery(mapa);
			
			cargarTablaGastosDeducibles();
			showUpdateMode();
			
		} else 
			SpiritAlert.createAlert("Debe seleccionar un contrato !!",SpiritAlert.WARNING);
		
	}
	
	private void cargarTablaGastosDeducibles() throws GenericBusinessException{
		limpiarTabla(getTblContratoGastoDeducible());
		DefaultTableModel modelo = (DefaultTableModel) getTblContratoGastoDeducible().getModel();
		total = 0D;
		for ( ContratoGastoDeducibleIf cgd : contratoGastoDeducibleColleccion ){
			Object[] fila = crearFilaTabla(cgd);
			modelo.addRow(fila);
		}
		total = Utilitarios.redondeoValor(total);
		
		Object[] fila = {"","","",
			"<html><b>TOTAL</b></html>",total
		};
		modelo.addRow(fila);
		showUpdateMode();
	}
	
	private Object[] crearFilaTabla( ContratoGastoDeducibleIf cgd ) throws GenericBusinessException{
		ContratoIf contrato = mapaContrato.get(cgd.getContratoId());
		EmpleadoIf empleado = mapaEmpleado.get(contrato.getEmpleadoId()); 
		GastoDeducibleIf gastoDeducible = verificarGastoDeducible(mapaGastoDeducible,cgd.getGastoDeducibleId());
		String nombreEmpleado = getNombreEmpleado(empleado);
		BigDecimal valor = cgd.getValor(); 
		Object[] fila = {
				contrato.getCodigo(),
				nombreEmpleado,
				gastoDeducible.getNombre(),
				Utilitarios.getFechaCortaUppercase(cgd.getFecha()),
				valor
		};
		total += valor.doubleValue();
		return fila;
	}
	
	private GastoDeducibleIf verificarGastoDeducible(Map<Long, GastoDeducibleIf> mapaGastoDeducible , Long gastoDeducibleId) throws GenericBusinessException{
		GastoDeducibleIf gastoDeducible = mapaGastoDeducible.get(gastoDeducibleId);
		if ( gastoDeducible == null ){
			gastoDeducible = SessionServiceLocator.getGastoDeducibleSessionService().getGastoDeducible(gastoDeducibleId);
			mapaGastoDeducible.put(gastoDeducible.getId(), gastoDeducible);
		}
		return gastoDeducible;
	}
	
	private String getNombreEmpleado(EmpleadoIf empleado){
		return empleado.getApellidos() + " "+empleado.getNombres();
	}
	
	@Override
	public void delete() {
		
		/*try{
			
			int filaSeleccionada = getTblContratoGastoDeducible().getSelectedRow();
			if ( filaSeleccionada >= 0 ){
				filaSeleccionada = getTblContratoGastoDeducible().convertRowIndexToModel(filaSeleccionada);
				ContratoGastoDeducibleIf gastoDeducibleSeleccionado = contratoGastoDeducibleColleccion.get(filaSeleccionada);
				SessionServiceLocator.getRolPagoSessionService().eliminarRubroEventual(gastoDeducibleSeleccionado.getId());
			}
			SpiritAlert.createAlert("Rubro Eventual eliminado con éxito !!",SpiritAlert.INFORMATION);
			showSaveMode();
			
		} catch(GenericBusinessException e){
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
		}*/
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
		
	}

	public void update() {
		try {
			
			//if ( verificarIngreso() ){
			if ( verificarUpdate() ) {
				SessionServiceLocator.getContratoGastoDeducibleSessionService()
					.actualizarContratoGastoDeducible(contratoGastosDeduciblesRemovidos, 
						contratoGastoDeducibleColleccion);
				
				SpiritAlert.createAlert("Gastos Deducibles guardados con éxito !!", SpiritAlert.INFORMATION);
				showSaveMode();
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error general al guardar Gastos Deducibles !!", SpiritAlert.ERROR);
		}
	}

	public void addDetail() {
		
	}

	public boolean isEmpty() {
		return false;
	}

	public void refresh() {
		try {
			cargarGastoDeducible();
			limpiarTabla(getTblContratoGastoDeducible());
			buscarContratoGastosDeducibles();
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
		getTxtGastoDeducible().setBackground(getBackground());
		//getTxtValor().setBackground(getBackground());
	}

	public void showUpdateMode() {
		setUpdateMode();
	}

	public void updateDetail() {
	}
	
	public void deleteDetail() {
		
	}
}
