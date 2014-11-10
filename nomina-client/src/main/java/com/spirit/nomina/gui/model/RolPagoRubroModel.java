package com.spirit.nomina.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoDetalleData;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.RubroEventualData;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.criteria.ContratoCriteria;
import com.spirit.nomina.gui.criteria.RolPagoCriteria;
import com.spirit.nomina.gui.panel.JPRolPagoRubro;
import com.spirit.nomina.handler.EstadoRolPagoDetalle;
import com.spirit.util.Utilitarios;

public class RolPagoRubroModel extends JPRolPagoRubro {

	private static final long serialVersionUID = 6853127079620021097L;
	
	private static final String RUBRO_EVENTUAL = "E";
	private static final String RUBRO_EVENTUAL_SI = "S";
	
	private static final String NOMBRE_TIPO_RUBRO_BENEFICIO = "BENEFICIO";
	private static final String NOMBRE_TIPO_RUBRO_DESCUENTO = "DESCUENTO";
	private static final String NOMBRE_TIPO_RUBRO_SUELDO = "SUELDO";
	private static final String NOMBRE_TIPO_RUBRO_ANTICIPO = "ANTICIPO";
	private static final String NOMBRE_TIPO_RUBRO_EVENTUAL = "EVENTUAL";
	private static final String NOMBRE_TIPO_RUBRO_PROVISIONADO = "PROVISIONADO";
	
	private static final String TIPO_RUBRO_BENEFICIO = "B";
	private static final String TIPO_RUBRO_DESCUENTO = "D";
	private static final String TIPO_RUBRO_SUELDO = "S";
	private static final String TIPO_RUBRO_ANTICIPO = "A";
	private static final String TIPO_RUBRO_EVENTUAL = "E";
	private static final String TIPO_RUBRO_PROVISIONADO = "P";
	
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	private RolPagoIf rolPagoIf = null;
	private EmpleadoIf empleadoIf = null;
	private ContratoIf contratoIf = null;
	
	private ArrayList<RolPagoDetalleIf> rolpagoDetalleNormalArraylist = null;
	private ArrayList<RolPagoDetalleIf> rolpagoDetalleNormalArraylistRemove = null;
	
	private ArrayList<RolPagoDetalleIf> rolpagoDetalleEventualArraylist = null;
	private ArrayList<RolPagoDetalleIf> rolpagoDetalleEventualArraylistRemove = null;
	
	private Map<Integer,RubroEventualIf> rubroEventualArrayMapa = null;
	private Map<Integer,RubroEventualIf> rubroEventualArraylistRemove = null;

	public RolPagoRubroModel(){
		iniciarComponentes();
		initListeners();
		showSaveMode();
		new HotKeyComponent(this);
	}
	
	private void iniciarComponentes(){
		
		getCmbFechaRubroEventual().setLocale(Utilitarios.esLocale);
		getCmbFechaRubroEventual().setShowNoneButton(false);
		getCmbFechaRubroEventual().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaRubroEventual().setEditable(false);
		
		getBtnBuscarRolPago().setText("");
		getBtnBuscarRolPago().setToolTipText("Buscar Rol de Pago");
		getBtnBuscarRolPago().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getBtnBuscarContrato().setText("");
		getBtnBuscarContrato().setToolTipText("Buscar Contrato");
		getBtnBuscarContrato().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getBtnBuscarEmpleado().setText("");
		getBtnBuscarEmpleado().setToolTipText("Buscar Empleado");
		getBtnBuscarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		getBtnAgregarRubroNormal().setText("");
		getBtnAgregarRubroNormal().setToolTipText("Agregar Rubro");
		getBtnAgregarRubroNormal().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		
		getBtnActualizarRubroNormal().setText("");
		getBtnActualizarRubroNormal().setToolTipText("Actualizar Rubro");
		getBtnActualizarRubroNormal().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		
		getBtnRemoverRubroNormal().setText("");
		getBtnRemoverRubroNormal().setToolTipText("Eliminar Rubro");
		getBtnRemoverRubroNormal().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		
		getBtnAgregarRubroEventual().setText("");
		getBtnAgregarRubroEventual().setToolTipText("Agregar Rubro Eventual");
		getBtnAgregarRubroEventual().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		
		getBtnActualizarRubroEventual().setText("");
		getBtnActualizarRubroEventual().setToolTipText("Actualizar Rubro Eventual");
		getBtnActualizarRubroEventual().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		
		getBtnRemoverRubroEventual().setText("");
		getBtnRemoverRubroEventual().setToolTipText("Eliminar Rubro Eventual");
		getBtnRemoverRubroEventual().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		
		getBtnBuscarRubro().setText("");
		getBtnBuscarRubro().setToolTipText("Buscar Rubro");
		getBtnBuscarRubro().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
	}
	
	private void initListeners(){
		getBtnBuscarRolPago().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				busquedaRolPago();
			}
		});
		getBtnBuscarEmpleado().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				busquedaEmpleado();
			}
		});
		getBtnBuscarContrato().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				busquedaContrato();
			}
		});
	}
	
	private void busquedaRolPago(){
		RolPagoCriteria rolPagoCriteria =  new RolPagoCriteria(" de Rol de Pago");
		rolPagoCriteria.setQueryBuilded(buildQuery());
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),rolPagoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
		if ( popupFinder.getElementoSeleccionado()!=null ){
			rolPagoIf = (RolPagoIf) popupFinder.getElementoSeleccionado();
			int mes = Integer.parseInt(rolPagoIf.getMes());
			getTxtRolPago().setText(Utilitarios.getNombreMes(mes)+"//"+rolPagoIf.getAnio());
		}
	}
	
	private Map<String, Object> buildQuery(){
		Map<String, Object> mapa =  new HashMap<String, Object>();
		if ( rolPagoIf != null ){
			mapa.put("rolpagoId", rolPagoIf.getId());
		}
		if ( contratoIf != null ){
			mapa.put("contratoId", contratoIf.getId());
		}
		return mapa;
	}
	
	private void busquedaEmpleado() {
		EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria("de Empleados", Parametros.getIdEmpresa());
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if ( popupFinder.getElementoSeleccionado()!=null ){
			empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
			getTxtEmpleado().setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
			contratoIf = null;
			getTxtContrato().setText("");
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
			
			Map<String, Object> mapa = new HashMap<String, Object>();
			int mes = Integer.parseInt(rolPagoIf.getMes());
			int anio = Integer.valueOf(rolPagoIf.getAnio());
			int ultimoDiaMes = Utilitarios.fechaHoy(mes, anio).getDate();
			long fecha = new GregorianCalendar(anio,mes,ultimoDiaMes).getTime().getTime();
			//Date fechaActual = new Date( fecha );

			Calendar calFechaMedia = new GregorianCalendar(anio,mes,1);
			Date fechaMedia = new Date(calFechaMedia.getTime().getTime());
			int diaFinal = calFechaMedia.getActualMaximum(Calendar.DATE);
			calFechaMedia = new GregorianCalendar(anio,mes,diaFinal);
			Date fechaMediaMax = new Date(calFechaMedia.getTime().getTime());
			mapa.put("fechaMediaContrato", fechaMedia);
			mapa.put("fechaMediaContratoMax", fechaMediaMax);
			mapa.put("empleadoId", empleadoIf.getId());
			//mapa.put("estado", "A");

			int tamanoLista;

			tamanoLista = SessionServiceLocator.getContratoSessionService().getContratoListSize(mapa);

			if ( tamanoLista > 0 ){
				ContratoCriteria contratoCriteria = new ContratoCriteria("de Contratos");
				contratoCriteria.setQueryBuilded(mapa);
				contratoCriteria.setResultListSize(tamanoLista);

				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	contratoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
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
	
	@Override
	public void clean() {
		rolPagoIf = null;
		empleadoIf = null;
		contratoIf = null;
		rubroEventualArrayMapa = null;
		
		getTxtRolPago().setText("");
		getTxtEmpleado().setText("");
		getTxtContrato().setText("");
		
		getTxtRubroNormal().setText("");
		getTxtValorNormal().setText("");
		
		getTxtValorRubroEventual().setText("");
		getTxtObservacionRubroEventual().setText("");
		getCmbFechaRubroEventual().setDate(null);
		getCmbRubroEventual().setSelectedIndex(-1);
		getCmbTipoRolEventual().setSelectedIndex(-1);
		
		limpiarTabla(getTblRubroNormal());
		limpiarTabla(getTblRubroEventual());
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find() {
		try {
			if ( validateFields() ){
				Map<String, Object> mapa =  new HashMap<String, Object>();
				mapa.put("rolapgoId", rolPagoIf.getId());
				mapa.put("contratoId", contratoIf.getId());
				mapa.put("estado", EstadoRolPagoDetalle.EMITIDO.getLetra() );
				
				rolpagoDetalleNormalArraylist = null;
				rolpagoDetalleNormalArraylist = (ArrayList) SessionServiceLocator.getRolPagoDetalleSessionService().findRolPagoDetalleByQueryNormal(mapa);
				
				rolpagoDetalleEventualArraylist = null;
				rolpagoDetalleEventualArraylist = (ArrayList) SessionServiceLocator.getRolPagoDetalleSessionService().findRolPagoDetalleEventualesByMap(mapa);
				
				if ( rolpagoDetalleNormalArraylist.size() > 0 || rolpagoDetalleEventualArraylist.size() > 0 ){
					cargarTablaRubroNormales();
					cargarTablaRubroEventuales();
				} else {
					SpiritAlert.createAlert("Contrato no tiene rubros !!", SpiritAlert.INFORMATION);
				}
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error general en la búsqueda de Rubros de Rol de pago !!", SpiritAlert.ERROR);
		}
		
	}
	
	private void cargarTablaRubroNormales() throws GenericBusinessException{
		DefaultTableModel modelo =  (DefaultTableModel) getTblRubroNormal().getModel();
		for ( RolPagoDetalleIf detalle : rolpagoDetalleNormalArraylist ){
			Vector fila =  new Vector();
			crearFilaRubroNormal(detalle,fila);
			modelo.addRow(fila);
		}
	}
	
	private void crearFilaRubroNormal(RolPagoDetalleIf detalle,Vector fila) throws GenericBusinessException{
		RubroIf rubroIf = SessionServiceLocator.getRubroSessionService().getRubro(detalle.getRubroId());
		fila.add(rubroIf.getCodigo() + " - " + rubroIf.getNombre());
		
		if(rubroIf.getTipoRubro().equals(TIPO_RUBRO_BENEFICIO))
			fila.add(NOMBRE_TIPO_RUBRO_BENEFICIO);
		else if(rubroIf.getTipoRubro().equals(TIPO_RUBRO_DESCUENTO))
			fila.add(NOMBRE_TIPO_RUBRO_DESCUENTO);
		else if(rubroIf.getTipoRubro().equals(TIPO_RUBRO_SUELDO))
			fila.add(NOMBRE_TIPO_RUBRO_SUELDO);
		else if(rubroIf.getTipoRubro().equals(TIPO_RUBRO_ANTICIPO))
			fila.add(NOMBRE_TIPO_RUBRO_ANTICIPO);
		else if(rubroIf.getTipoRubro().equals(TIPO_RUBRO_EVENTUAL))
			fila.add(NOMBRE_TIPO_RUBRO_EVENTUAL);
		else if(rubroIf.getTipoRubro().equals(TIPO_RUBRO_PROVISIONADO))
			fila.add(NOMBRE_TIPO_RUBRO_PROVISIONADO);
		
		fila.add(detalle.getValor());
	}
	
	private void cargarTablaRubroEventuales() throws GenericBusinessException{
		DefaultTableModel modelo =  (DefaultTableModel) getTblRubroEventual().getModel();
		for ( int i = 0 ; i < rolpagoDetalleEventualArraylist.size() ; i++ ){
			RolPagoDetalleIf detalle = rolpagoDetalleEventualArraylist.get(i) ;
			Vector fila =  new Vector();
			crearFilaRubroEventual(detalle,fila,i);
			modelo.addRow(fila);
		}
		
	}
	
	private void crearFilaRubroEventual(RolPagoDetalleIf detalle,Vector<Object> fila,int indice) throws GenericBusinessException{
		
		RubroIf rubroIf = null;
		RubroEventualIf rubroEventualIf = null;
		
		if ( detalle.getRubroEventualId() != null ) {
			rubroEventualIf = SessionServiceLocator.getRubroEventualSessionService().getRubroEventual(detalle.getRubroEventualId());
		} else {
			rubroEventualIf = rubroEventualArrayMapa.get(indice);
		}
		rubroIf = SessionServiceLocator.getRubroSessionService().getRubro(rubroEventualIf.getRubroId());
		TipoRolIf tipoRol = SessionServiceLocator.getTipoRolSessionService().getTipoRol(rubroEventualIf.getTipoRolIdCobro());
		
		fila.add(rubroIf.getCodigo() + " - " + rubroIf.getNombre());
		fila.add(Utilitarios.getFechaMesAnioUppercase(rubroEventualIf.getFechaCobro()));
		fila.add(tipoRol.getCodigo() + " - " + tipoRol.getNombre());
		fila.add(formatoDecimal.format(rubroEventualIf.getValor()));
		
	}
	
	public void agregarRubroEventual(){
		if (validateFieldsRubroEventual()) {
			RubroEventualIf dataRubroEventual = new RubroEventualData();
			RubroIf rubro = (RubroIf)getCmbRubroEventual().getSelectedItem();
			dataRubroEventual.setRubroId(rubro.getId());
			Date fecha = getCmbFechaRubroEventual().getDate();
			dataRubroEventual.setFechaCobro(new java.sql.Date(fecha.getTime()));
			TipoRolIf tipoRol = (TipoRolIf)getCmbTipoRolEventual().getSelectedItem();
			dataRubroEventual.setTipoRolIdCobro(tipoRol.getId());
			BigDecimal valor = BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtValorRubroEventual().getText())));
			dataRubroEventual.setValor(valor);
			dataRubroEventual.setEstado("A");
			dataRubroEventual.setObservacion(getTxtObservacionRubroEventual().getText());
			rubroEventualArrayMapa.put(rolpagoDetalleEventualArraylist.size(),dataRubroEventual);
			
			RolPagoDetalleIf rolpagoDetalle = new RolPagoDetalleData();
			rolpagoDetalle.setRolpagoId(rolPagoIf.getId());
			rolpagoDetalle.setContratoId(contratoIf.getId());
			rolpagoDetalle.setValor(valor);
			rolpagoDetalle.setObservacion(getTxtObservacionRubroEventual().getText());
			rolpagoDetalleEventualArraylist.add(rolpagoDetalle);
			
			DefaultTableModel modelo = (DefaultTableModel) getTblRubroEventual().getModel();
			Vector<Object> filaRubroEventual = new Vector<Object>();
			filaRubroEventual.add(rubro.getCodigo() + " - " + rubro.getNombre());
			filaRubroEventual.add(Utilitarios.getFechaMesAnioUppercase(fecha));
			filaRubroEventual.add(tipoRol.getCodigo() + " - " + tipoRol.getNombre());
			filaRubroEventual.add(formatoDecimal.format(valor));
			modelo.addRow(filaRubroEventual);
			
			cleanRubrosEventuales();
		}		
	}
	
	public void actualizarRubroEventual() throws GenericBusinessException{
		if((getTblRubroEventual().getSelectedRow() != -1) && validateFieldsRubroEventual()){
			int filaSeleccionada = getTblRubroEventual().getSelectedRow();
			RubroIf rubro = (RubroIf)getCmbRubroEventual().getSelectedItem();
			
			RubroEventualIf rubroEventualIf = rubroEventualArrayMapa.get(filaSeleccionada);
			rubroEventualIf.setRubroId(rubro.getId());
			Date fecha = getCmbFechaRubroEventual().getDate();
			rubroEventualIf.setFechaCobro(new java.sql.Date(fecha.getTime()));
			TipoRolIf tipoRol = (TipoRolIf)getCmbTipoRolEventual().getSelectedItem();
			rubroEventualIf.setTipoRolIdCobro(tipoRol.getId());
			BigDecimal valor = BigDecimal.valueOf(Double.valueOf(Utilitarios.removeDecimalFormat(getTxtValorRubroEventual().getText())));
			rubroEventualIf.setValor(valor);
			rubroEventualIf.setEstado("A");
			rubroEventualIf.setObservacion(getTxtObservacionRubroEventual().getText());
			
			RolPagoDetalleIf rolDetalleEventual = rolpagoDetalleEventualArraylist.get(filaSeleccionada);
			rolDetalleEventual.setRolpagoId(rolPagoIf.getId());
			rolDetalleEventual.setContratoId(contratoIf.getId());
			rolDetalleEventual.setValor(valor);
			rolDetalleEventual.setObservacion(getTxtObservacionRubroEventual().getText());
			
			cleanRubrosEventuales();
			limpiarTabla(getTblRubroEventual());
			cargarTablaRubroEventuales();
		}else{
			SpiritAlert.createAlert("Debe seleccionar una fila para actualizar!", SpiritAlert.WARNING);
		}
	}

	public void removerRubroEventual() throws GenericBusinessException{
		if((getTblRubroEventual().getSelectedRow() != -1) && validateFieldsRubroEventual()){
			String si = "Si"; 
			String no = "No"; 
			Object[] options ={si,no}; 
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				int filaSeleccionada = getTblRubroEventual().getSelectedRow();
				RolPagoDetalleIf rolPagoDetalle = rolpagoDetalleEventualArraylist.get(filaSeleccionada);
				if( rolPagoDetalle.getId() != null ){
					rolpagoDetalleEventualArraylistRemove.add(rolPagoDetalle);
					
				}				
				rolpagoDetalleEventualArraylist.remove(filaSeleccionada);
				rubroEventualArrayMapa.remove(filaSeleccionada);
			}
			limpiarTabla(getTblRubroEventual());
			cargarTablaRubroEventuales();
		}else{
			SpiritAlert.createAlert("Debe seleccionar una fila para remover!", SpiritAlert.WARNING);
		}
	}
	
	public void cleanRubrosEventuales(){
		getTxtValorRubroEventual().setText("");
		getTxtObservacionRubroEventual().setText("");
	}
	
	public boolean validateFieldsRubroEventual(){
		if(getCmbRubroEventual().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Rubro Eventual!", SpiritAlert.WARNING);
			getCmbRubroEventual().grabFocus();
			return false;
		}
		if(getCmbFechaRubroEventual().getDate() == null){
			SpiritAlert.createAlert("Debe seleccionar una Fecha!", SpiritAlert.WARNING);
			getCmbFechaRubroEventual().grabFocus();
			return false;
		}
		if(getCmbTipoRolEventual().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Tipo de Rol!", SpiritAlert.WARNING);
			getCmbTipoRolEventual().grabFocus();
			return false;
		}
		
		if(getTxtValorRubroEventual().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar un Valor!", SpiritAlert.WARNING);
			getTxtValorRubroEventual().grabFocus();
			return false;
		}
		return true;
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
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
		
		if ( rolPagoIf == null ){
			SpiritAlert.createAlert("Debe elegir un Rol de Pago !!", SpiritAlert.INFORMATION);
			return false;
		}
		if ( contratoIf == null ){
			SpiritAlert.createAlert("Debe elegir un Contrato !!", SpiritAlert.INFORMATION);
			return false;
		}
		
		return true;
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		cargarComboRubroEventual();
		cargarComboTipoRol();
		
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void cargarComboRubroEventual(){
		try {
			Map<String, Object> mapa =  new HashMap<String, Object>();
			mapa.put("tipoRubro",RUBRO_EVENTUAL);
			mapa.put("empresaId", Parametros.getIdEmpresa());
			List rubrosEventuales = (List) SessionServiceLocator.getRubroSessionService().findRubroByQuery(mapa);
			refreshCombo(getCmbRubroEventual(), rubrosEventuales);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarComboTipoRol(){
		try {
			Map<String, Object> mapa =  new HashMap<String, Object>();
			mapa.put("rubroEventual",RUBRO_EVENTUAL_SI);
			mapa.put("empresaId", Parametros.getIdEmpresa());
			List tiposRol = (List) SessionServiceLocator.getTipoRolSessionService().findTipoRolByQuery(mapa);
			refreshCombo(getCmbTipoRolEventual(), tiposRol);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void showFindMode() {
		clean();
		getTxtRolPago().setBackground(Parametros.findColor);
		getTxtContrato().setBackground(Parametros.findColor);
		setFindMode();
	}

	public void showSaveMode() {
		showFindMode();
	}

	public void showUpdateMode() {
		
		getTxtRolPago().setBackground(Parametros.saveUpdateColor);
		getTxtContrato().setBackground(Parametros.saveUpdateColor);
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

}
