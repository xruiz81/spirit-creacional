package com.spirit.sri.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.Date;
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

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.gui.controller.ContabilidadFinder;
import com.spirit.contabilidad.gui.criteria.CuentaCriteria;
import com.spirit.contabilidad.gui.model.PlanCuentaModel;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.sri.entity.SriClienteRetencionData;
import com.spirit.sri.entity.SriClienteRetencionIf;
import com.spirit.sri.gui.panel.JPRetencionesCliente;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class RetencionesClienteModel extends JPRetencionesCliente {
	
	private static final long serialVersionUID = -3508697489201538630L;
	
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_ACTIVO = "A";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_INACTIVO = "I";
	private static final String NOMBRE_TIPO_RETENCION_RENTA = "RENTA";
	private static final String TIPO_RETENCION_RENTA = "R";
	private static final String NOMBRE_TIPO_RETENCION_IVA = "IVA";
	private static final String TIPO_RETENCION_IVA = "I";
	private static final int MAX_LONGITUD_PORCENTAJE = 5; 
	private static final int MAX_LONGITUD_CUENTA = 100; 
	
	private CuentaIf cuenta;
	private JDPopupFinderModel popupFinder;
	private CuentaCriteria cuentaCriteria;
	private int filaSeleccionada = -1;
	private ArrayList<SriClienteRetencionIf> clienteRetencionVector = new ArrayList<SriClienteRetencionIf>();
	private SriClienteRetencionIf clienteRetencionIf;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");

	private Map<Long, CuentaIf> mapaCuentas = null;
	
	public RetencionesClienteModel(){
		initKeyListeners();
		anchoColumnasTabla();
		cargarCombos();
		showSaveMode();
		initListeners();
		new HotKeyComponent(this);	
		setSorterTable(getTblRetencionesCliente());
	}
	
	public void cargarCombos(){
		cargarComboPlanCuenta();
	}
	
	private void cargarComboPlanCuenta(){
		List planesCuentas = ContabilidadFinder.findPlanCuentaActivo(Parametros.getIdEmpresa());
		refreshCombo(getCmbPlanCuenta(),planesCuentas);
		if (getCmbPlanCuenta().getSelectedItem() == null){
			PlanCuentaModel.seleccionarPlanCuentaPredeterminado(getCmbPlanCuenta());
		}
	}
	
	public void initKeyListeners(){
		getTxtPorcentaje().addKeyListener(new TextChecker(MAX_LONGITUD_PORCENTAJE));
		getTxtPorcentaje().addKeyListener(new NumberTextFieldDecimal());
		
		getTblRetencionesCliente().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblRetencionesCliente().addMouseListener(oMouseAdapterTblRetencionesCliente);
		getTblRetencionesCliente().addKeyListener(oKeyAdapterTblRetencionesCliente);
		
		getTxtCuenta().addKeyListener(new TextChecker(MAX_LONGITUD_CUENTA));
		
		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);		
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setEditable(false);
	}
	
	public void anchoColumnasTabla(){
		TableColumn anchoColumna = getTblRetencionesCliente().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblRetencionesCliente().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(45);
		anchoColumna = getTblRetencionesCliente().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblRetencionesCliente().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(60);
		anchoColumna = getTblRetencionesCliente().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblRetencionesCliente().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(50);
	}
	
	MouseListener oMouseAdapterTblRetencionesCliente = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblRetencionesCliente = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (getTblRetencionesCliente().getSelectedRow() != -1) {
			try {
				int selectedRow = getTblRetencionesCliente().getSelectedRow();
				filaSeleccionada =  getTblRetencionesCliente().convertRowIndexToModel(selectedRow); 
				clienteRetencionIf = clienteRetencionVector.get(filaSeleccionada);
				
				if(clienteRetencionIf.getEstado().equals(ESTADO_ACTIVO)){
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
				}else if(clienteRetencionIf.getEstado().equals(ESTADO_INACTIVO)){
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
				}
				getCmbEstado().repaint();
				
				getTxtPorcentaje().setText(clienteRetencionIf.getPorcentajeRetencion().toString());
				
				if(clienteRetencionIf.getTipoRetencion().equals(TIPO_RETENCION_RENTA)){
					getCmbTipoRetencion().setSelectedItem(NOMBRE_TIPO_RETENCION_RENTA);
				}else if(clienteRetencionIf.getTipoRetencion().equals(TIPO_RETENCION_IVA)){
					getCmbTipoRetencion().setSelectedItem(NOMBRE_TIPO_RETENCION_IVA);
				}
				getCmbTipoRetencion().repaint();
				
				getCmbFechaInicio().setDate(clienteRetencionIf.getFechaInicio());
				getCmbFechaInicio().repaint();
				if(clienteRetencionIf.getFechaFin() != null){
					getCmbFechaFin().setDate(clienteRetencionIf.getFechaFin());
				}else{
					getCmbFechaFin().setDate(null);
				}
				getCmbFechaFin().repaint();
				
				if(clienteRetencionIf.getCuentaId() != null){
					//cuenta = SessionServiceLocator.getCuentaSessionService().getCuenta(clienteRetencionIf.getCuentaId());
					cuenta = verificarMapaCuenta(mapaCuentas, clienteRetencionIf.getCuentaId());
					getTxtCuenta().setText(cuenta.getCodigo() + " - " + cuenta.getNombre());
				}else{
					getTxtCuenta().setText("");
				}
				
				if(cuenta != null){
					PlanCuentaIf planCuenta = SessionServiceLocator.getPlanCuentaSessionService().getPlanCuenta(cuenta.getPlancuentaId());
					getCmbPlanCuenta().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPlanCuenta(), planCuenta.getId()));
				}
				
				getCmbPlanCuenta().repaint();	
				showUpdateMode();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
		}
	}
	
	private CuentaIf verificarMapaCuenta(Map<Long, CuentaIf> mapaCuenta, Long cuentaId) throws GenericBusinessException{
		CuentaIf cuentaIf = null;
		if ( cuentaId != null ){
			cuentaIf = mapaCuenta.get(cuentaId);
			if ( cuentaIf == null ){
				cuentaIf = SessionServiceLocator.getCuentaSessionService().getCuenta(cuentaId);
				mapaCuenta.put(cuentaIf.getId(), cuentaIf);
			}
		}
		return cuentaIf;
	}
	
	public void initListeners(){
		getBtnCuenta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				cuenta = actionListenerBtnCuenta(getTxtCuenta());
			}	
		});
		
		getTxtCuenta().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				cuenta = keyListenerTxtCuenta(e, getTxtCuenta());
			}
		});
	}
	
	private CuentaIf actionListenerBtnCuenta(JTextField txtCuenta) {
		CuentaIf cuenta = null;
		if (getCmbPlanCuenta().getSelectedItem() != null) {
			PlanCuentaIf planCuenta = (PlanCuentaIf) getCmbPlanCuenta().getSelectedItem();
			cuentaCriteria = new CuentaCriteria("Plan de Cuentas", "S", planCuenta.getId(), ((UsuarioIf) Parametros.getUsuarioIf()).getId(), ESTADO_ACTIVO);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),cuentaCriteria,JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if (popupFinder.getElementoSeleccionado() != null) {
				cuenta = (CuentaIf) popupFinder.getElementoSeleccionado();
				txtCuenta.setText(cuenta.getCodigo() + " - " + cuenta.getNombre());
			}
		}else{
			SpiritAlert.createAlert("Debe seleccionar un Plan de Cuentas", SpiritAlert.WARNING);
		}
		return cuenta;
	}		
	
	private CuentaIf keyListenerTxtCuenta(KeyEvent e, JTextField txtCuenta) {
		CuentaIf cuenta = null;
		try {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				Map<String,Object> parameterMap = new HashMap<String,Object>();
				String strCuenta = txtCuenta.getText();
				String strCodigoNombreCuenta = "";
				
				if (strCuenta.contains(" - "))
					strCodigoNombreCuenta = strCuenta.split(" - ")[0] + "%";
				else 
					strCodigoNombreCuenta = strCuenta + "%";
				
				parameterMap.put("codigoNombreCuenta", strCodigoNombreCuenta);
				if (getCmbPlanCuenta().getSelectedItem() != null)
					parameterMap.put("plancuentaId", ((PlanCuentaIf) getCmbPlanCuenta().getSelectedItem()).getId());
				parameterMap.put("imputable", "S");
				parameterMap.put("estado", ESTADO_ACTIVO);
				
				CuentaIf cuentaIf  = null;
				int tamanoLista = SessionServiceLocator.getCuentaSessionService().findCuentaByQueryByUsuarioIdAndCodigoOrNombreListSize(parameterMap, ((UsuarioIf) Parametros.getUsuarioIf()).getId());
				if (tamanoLista == 1) {
					Collection<CuentaIf> cuentas = SessionServiceLocator.getCuentaSessionService().findCuentaByQueryByUsuarioIdAndCodigoOrNombre(parameterMap, ((UsuarioIf) Parametros.getUsuarioIf()).getId()); 
					Iterator<CuentaIf> cuentaIterator = cuentas.iterator(); 
					if (cuentaIterator.hasNext()) {
						cuentaIf = cuentaIterator.next();
						cuenta = cuentaIf;
						txtCuenta.setText(cuenta.getCodigo() + " - " + cuenta.getNombre());
					}
				} else if (tamanoLista > 1) {
					CuentaCriteria cuentaCriteria = new CuentaCriteria();
					cuentaCriteria.setIdUsuario(((UsuarioIf) Parametros.getUsuarioIf()).getId());
					cuentaCriteria.setResultListSize(tamanoLista);
					cuentaCriteria.setQueryBuilded(parameterMap);
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), cuentaCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
					if ( popupFinder.getElementoSeleccionado() != null ) {
						cuentaIf = (CuentaIf) popupFinder.getElementoSeleccionado();
						txtCuenta.setText(cuentaIf.getCodigo() + " - " + cuentaIf.getNombre());
					}
				} 
				
				if (cuentaIf == null) {
					cuenta = null;
					if (tamanoLista <= 0)
						SpiritAlert.createAlert("No se halló la cuenta deseada en el plan de cuentas seleccionado", SpiritAlert.WARNING);
				} else {
					cuenta = cuentaIf;
				}
			}
		} catch (GenericBusinessException ex) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			ex.printStackTrace();
		}
		return cuenta;
	}
	
	public void save() {
		try {
			if (validateFields()) {
				SriClienteRetencionData data = new SriClienteRetencionData();
				
				if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO)){
					data.setEstado(ESTADO_ACTIVO);
				}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO)){
					data.setEstado(ESTADO_INACTIVO);
				}
				
				if(getCmbTipoRetencion().getSelectedItem().equals(NOMBRE_TIPO_RETENCION_RENTA)){
					data.setTipoRetencion(TIPO_RETENCION_RENTA);
				}else if(getCmbTipoRetencion().getSelectedItem().equals(NOMBRE_TIPO_RETENCION_IVA)){
					data.setTipoRetencion(TIPO_RETENCION_IVA);
				}
				
				BigDecimal porcentaje = BigDecimal.valueOf(Double.valueOf(getTxtPorcentaje().getText()));
				data.setPorcentajeRetencion(porcentaje);
				
				Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
				data.setFechaInicio(fechaInicio);
				
				if(getCmbFechaFin().getCalendar() != null){
					Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
					data.setFechaFin(fechaFin);
				}
				
				data.setCuentaId(cuenta.getId());
				
				SessionServiceLocator.getSriClienteRetencionSessionService().addSriClienteRetencion(data);
				SpiritAlert.createAlert("Retención al Cliente guardada con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la infomación!", SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {	
			if (validateFields()) {
				if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO)){
					clienteRetencionIf.setEstado(ESTADO_ACTIVO);
				}else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO)){
					clienteRetencionIf.setEstado(ESTADO_INACTIVO);
				}
				
				if(getCmbTipoRetencion().getSelectedItem().equals(NOMBRE_TIPO_RETENCION_RENTA)){
					clienteRetencionIf.setTipoRetencion(TIPO_RETENCION_RENTA);
				}else if(getCmbTipoRetencion().getSelectedItem().equals(NOMBRE_TIPO_RETENCION_IVA)){
					clienteRetencionIf.setTipoRetencion(TIPO_RETENCION_IVA);
				}
				
				BigDecimal porcentaje = BigDecimal.valueOf(Double.valueOf(getTxtPorcentaje().getText()));
				clienteRetencionIf.setPorcentajeRetencion(porcentaje);
				
				Date fechaInicio = new Date(getCmbFechaInicio().getCalendar().getTime().getTime());
				clienteRetencionIf.setFechaInicio(fechaInicio);
				
				if(getCmbFechaFin().getCalendar() != null){
					Date fechaFin = new Date(getCmbFechaFin().getCalendar().getTime().getTime());
					clienteRetencionIf.setFechaFin(fechaFin);
				}
				
				clienteRetencionIf.setCuentaId(cuenta.getId());
								
				SessionServiceLocator.getSriClienteRetencionSessionService().saveSriClienteRetencion(clienteRetencionIf);
				SpiritAlert.createAlert("Retención al Cliente actualizada con \u00e9xito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informaci\u00f3n!",	SpiritAlert.ERROR);
		}		
	}
	
	public void delete() {
		try {
			SriClienteRetencionIf clienteRetencion = clienteRetencionVector.get(filaSeleccionada);
			SessionServiceLocator.getSriClienteRetencionSessionService().deleteSriClienteRetencion(clienteRetencion.getId());
			SpiritAlert.createAlert("Retención al Cliente eliminada con \u00e9xito", SpiritAlert.INFORMATION);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void clean() {
		cuenta = null;
		Calendar calendar = new GregorianCalendar();
		getCmbFechaInicio().setCalendar(calendar);
		getCmbFechaFin().setCalendar(calendar);
		getTxtPorcentaje().setText("");
		getTxtCuenta().setText("");
		
		clienteRetencionVector = null;
		clienteRetencionVector = new ArrayList<SriClienteRetencionIf>();
		
		mapaCuentas = new HashMap<Long, CuentaIf>();
			
		limpiarTabla(getTblRetencionesCliente());
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		getCmbEstado().grabFocus();
	}
	
	private void cargarTabla() {
		try {			
			DefaultTableModel tableModel = (DefaultTableModel) getTblRetencionesCliente().getModel();
			
			Collection<SriClienteRetencionIf> clienteRetencionesExistentes = 
				SessionServiceLocator.getSriClienteRetencionSessionService().getSriClienteRetencionList(); 
			for (SriClienteRetencionIf clienteRetencionIf : clienteRetencionesExistentes){
				Vector<String> fila = new Vector<String>();
				clienteRetencionVector.add(clienteRetencionIf);
				agregarColumnasTabla(clienteRetencionIf, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblRetencionesCliente(), clienteRetencionesExistentes, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(SriClienteRetencionIf clienteRetencion, Vector<String> fila){
		try {
			
			if(clienteRetencion.getTipoRetencion().equals(TIPO_RETENCION_RENTA)){
				fila.add(NOMBRE_TIPO_RETENCION_RENTA);
			}else if(clienteRetencion.getTipoRetencion().equals(TIPO_RETENCION_IVA)){
				fila.add(NOMBRE_TIPO_RETENCION_IVA);
			}
			
			fila.add(formatoDecimal.format(clienteRetencion.getPorcentajeRetencion()));
			
			fila.add(" " + Utilitarios.getFechaCortaUppercase(clienteRetencion.getFechaInicio()));
			if(clienteRetencion.getFechaFin() != null){
				fila.add(Utilitarios.getFechaCortaUppercase(clienteRetencion.getFechaFin()));
			}else{
				fila.add("");
			}
			
			if(clienteRetencion.getCuentaId() != null){
				CuentaIf cuenta = 
					SessionServiceLocator.getCuentaSessionService().getCuenta(clienteRetencion.getCuentaId());
				fila.add(cuenta.getCodigo() + " - " + cuenta.getNombre());
			}else{
				fila.add("");
			}
						
			if(clienteRetencion.getEstado().equals(ESTADO_ACTIVO)){
				fila.add(NOMBRE_ESTADO_ACTIVO);
			}else if(clienteRetencion.getEstado().equals(ESTADO_INACTIVO)){
				fila.add(NOMBRE_ESTADO_INACTIVO);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public boolean validateFields() {
		if (getCmbEstado().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar el Estado!", SpiritAlert.WARNING);
			getCmbEstado().grabFocus();
			return false;
		}
		if (getCmbTipoRetencion().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Tipo de Retención!", SpiritAlert.WARNING);
			getCmbTipoRetencion().grabFocus();
			return false;
		}
		if (getTxtPorcentaje().getText() == null || getTxtPorcentaje().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar el Porcentaje!", SpiritAlert.WARNING);
			getTxtPorcentaje().grabFocus();
			return false;
		}
		BigDecimal porcentaje = BigDecimal.valueOf(Double.valueOf(getTxtPorcentaje().getText()));
		if(porcentaje.compareTo(new BigDecimal (100)) == 1){
			SpiritAlert.createAlert("El Porcentaje no puede ser mayor que 100 !", SpiritAlert.WARNING);
			getTxtPorcentaje().grabFocus();
			return false;
		}
		if (getCmbFechaInicio().getSelectedItem() == null){
			SpiritAlert.createAlert("Debe seleccionar la Fecha de Inicio!", SpiritAlert.WARNING);
			getCmbFechaInicio().grabFocus();
			return false;
		}
		if ((getTxtCuenta().getText() == null) || cuenta == null || getTxtCuenta().getText().equals("")){
			SpiritAlert.createAlert("Debe ingresar la Cuenta !", SpiritAlert.WARNING);
			getTxtCuenta().grabFocus();
			return false;
		}		
			
		return true;
	}
	
	public void refresh() {
		cargarComboPlanCuenta();
	}
	
	
}
