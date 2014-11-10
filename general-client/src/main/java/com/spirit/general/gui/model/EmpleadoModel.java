package com.spirit.general.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.DepartamentoIf;
import com.spirit.general.entity.EmpleadoData;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.general.gui.panel.JPEmpleado;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;
import com.spirit.util.ValidarIdentificacion;

public class EmpleadoModel extends JPEmpleado {

	private static final long serialVersionUID = -277761314914965073L;
	private JDPopupFinderModel popupFinder;
	private EmpleadoCriteria empleadoCriteria;
	private EmpleadoIf empleado,jefeIf;
	private TipoIdentificacionIf tipoIdentificacionIf;
	
	private static final int MAX_LONGITUD_CODIGO = 10;
	private static final int MAX_LONGITUD_NOMBRES = 50;
	private static final int MAX_LONGITUD_APELLIDOS = 50;
	private static final int MAX_LONGITUD_CEDULA = 10;
	private static final int MAX_LONGITUD_RUC = 13;
	private static final int MAX_LONGITUD_PROFESION = 60;
	private static final int MAX_LONGITUD_DOMICILIO = 150;
	private static final int MAX_LONGITUD_TELEFONO = 10;
	private static final int MAX_LONGITUD_CELULAR = 10;
	private static final int MAX_LONGITUD_EXTENSION = 4;
	private static final int MAX_LONGITUD_EMAIL = 60;
	private static final int MAX_LONGITUD_NIVEL = 3;
	private static final int NIVEL_JERARQUICO = 1;
	private static final String TIPOCUENTA_AHORROS = "A - AHORROS";
	private static final String TIPOCUENTA_CORRIENTE = "C - CORRIENTE";
	private static final String TIPOCUENTA_EFECTIVO = "E - EFECTIVO";
	
	
	public EmpleadoModel() {
		iniciarComponentes();
		initKeyListeners();
		initButtonListeners();
		this.showSaveMode();
		initListeners();
		new HotKeyComponent(this);		 
	}
	
	private void iniciarComponentes(){
		getBtnBuscarJefe().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		//getBtnBuscarJefe().setToolTipText("Buscar Jefe");
		//getPanelEmpleado().add(getBtnBuscarJefe(), new CellConstraints().xy(9, 33));
		getBtnBorrarJefe().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		//getBtnBorrarJefe().setToolTipText("Borro el Jefe seleccionado");
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombres().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRES));
		getTxtApellidos().addKeyListener(new TextChecker(MAX_LONGITUD_APELLIDOS));
		getTxtProfesion().addKeyListener(new TextChecker(MAX_LONGITUD_PROFESION));
		getTxtDomicilio().addKeyListener(new TextChecker(MAX_LONGITUD_DOMICILIO));
		getTxtTelefono().addKeyListener(new TextChecker(MAX_LONGITUD_TELEFONO));
		getTxtTelefono().addKeyListener(new NumberTextField());
		getTxtCelular().addKeyListener(new TextChecker(MAX_LONGITUD_CELULAR));
		getTxtCelular().addKeyListener(new NumberTextField());
		getTxtExtension().addKeyListener(new TextChecker(MAX_LONGITUD_EXTENSION));
		getTxtEmail().addKeyListener(new TextChecker(MAX_LONGITUD_EMAIL, 0));
		getTxtNivel().addKeyListener(new TextChecker(MAX_LONGITUD_NIVEL));
	}
	
	public void initListeners() {
		getCmbTipoIdentificacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipoIdentificacionIf = (TipoIdentificacionIf) getCmbTipoIdentificacion().getModel().getSelectedItem();
				if ( tipoIdentificacionIf != null ){
					for (int i=0; i<getTxtIdentificacion().getKeyListeners().length; i++) {
						KeyListener keyListener = getTxtIdentificacion().getKeyListeners()[i];
						getTxtIdentificacion().removeKeyListener(keyListener);
					}	
						
					if (tipoIdentificacionIf.getCodigo().equals("CE")) {
						if (getTxtIdentificacion().getText().length() > MAX_LONGITUD_CEDULA)
							getTxtIdentificacion().setText("");
						getTxtIdentificacion().addKeyListener(new TextChecker(MAX_LONGITUD_CEDULA));
					} else if (tipoIdentificacionIf.getCodigo().equals("RU") || tipoIdentificacionIf.getCodigo().equals("PA"))
						if (getTxtIdentificacion().getText().length() > MAX_LONGITUD_RUC)
							getTxtIdentificacion().setText("");
					getTxtIdentificacion().addKeyListener(new TextChecker(MAX_LONGITUD_RUC));
					getTxtIdentificacion().setEnabled(true);
					getTxtIdentificacion().grabFocus();
				}
			}
		});
	}
	
	public void save() {

		if (validateFields()) {
			EmpleadoData data = new EmpleadoData();
			data.setEmpresaId(Parametros.getIdEmpresa());
			data.setCodigo(this.getTxtCodigo().getText());
			data.setNombres(this.getTxtNombres().getText());
			data.setApellidos(this.getTxtApellidos().getText());
			data.setTipoidentificacionId(((TipoIdentificacionIf) this.getCmbTipoIdentificacion().getSelectedItem()).getId());
			data.setIdentificacion(this.getTxtIdentificacion().getText());
			data.setProfesion(this.getTxtProfesion().getText());
			data.setDireccionDomicilio(this.getTxtDomicilio().getText());
			data.setTelefonoDomicilio(this.getTxtTelefono().getText());
			data.setCelular(this.getTxtCelular().getText());
			data.setEmailOficina(this.getTxtEmail().getText());
			data.setOficinaId(((OficinaIf) this.getCmbOficina().getSelectedItem()).getId());
			data.setDepartamentoId(((DepartamentoIf) this.getCmbDepartamento().getSelectedItem()).getId());
			data.setTipoempleadoId(((TipoEmpleadoIf) this.getCmbTipoEmpleado().getSelectedItem()).getId());
			data.setExtensionOficina(this.getTxtExtension().getText());
			data.setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));
			
			if(getCmbTipoCuenta().getSelectedItem() != null){
				data.setBancoId(((BancoIf) this.getCmbBanco().getSelectedItem()).getId());
				data.setTipoCuenta(((String)getCmbTipoCuenta().getSelectedItem()).substring(0, 1));
				
				if(!getTxtNumeroCuenta().getText().equals("")){
					data.setNumeroCuenta(getTxtNumeroCuenta().getText());
				}			
			}			
			
			// Seteo el Nivel del Empleado
			if (jefeIf == null) {
				data.setJefeId(null);
				data.setNivel(NIVEL_JERARQUICO);
			} else {
				data.setJefeId(jefeIf.getId());
				if (!getTxtNivel().getText().equals("") && getTxtNivel().getText() != null)
					data.setNivel(Integer.parseInt(getTxtNivel().getText()));
			}
			try {
				SessionServiceLocator.getEmpleadoSessionService().addEmpleado(data);
				SpiritAlert.createAlert("Empleado guardado con éxito!",SpiritAlert.INFORMATION);
				SpiritCache.setObject("empleado",null);
				clean();
				showSaveMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la informaci\u00f3n!",SpiritAlert.ERROR);
			}
		}
	}

	public void update() {
		if (validateFields()) {
			empleado.setNombres(this.getTxtNombres().getText());
			empleado.setApellidos(this.getTxtApellidos().getText());
			empleado.setTipoidentificacionId(((TipoIdentificacionIf) this.getCmbTipoIdentificacion().getSelectedItem()).getId());
			empleado.setIdentificacion(this.getTxtIdentificacion().getText());
			empleado.setProfesion(this.getTxtProfesion().getText());
			empleado.setDireccionDomicilio(this.getTxtDomicilio().getText());
			empleado.setTelefonoDomicilio(this.getTxtTelefono().getText());
			empleado.setCelular(this.getTxtCelular().getText());
			empleado.setEmailOficina(this.getTxtEmail().getText());
			empleado.setOficinaId(((OficinaIf) this.getCmbOficina().getSelectedItem()).getId());
			empleado.setDepartamentoId(((DepartamentoIf) this.getCmbDepartamento().getSelectedItem()).getId());
			
			empleado.setTipoempleadoId(((TipoEmpleadoIf) this.getCmbTipoEmpleado().getSelectedItem()).getId());
			empleado.setExtensionOficina(this.getTxtExtension().getText());
			empleado.setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));
			
			if(getCmbTipoCuenta().getSelectedItem() != null){
				empleado.setBancoId(((BancoIf) this.getCmbBanco().getSelectedItem()).getId());
				empleado.setTipoCuenta(((String)getCmbTipoCuenta().getSelectedItem()).substring(0, 1));
				
				if(!getTxtNumeroCuenta().getText().equals("")){
					empleado.setNumeroCuenta(getTxtNumeroCuenta().getText());
				}			
			}	
			
			if (jefeIf == null) {
				empleado.setJefeId(null);
				empleado.setNivel(NIVEL_JERARQUICO);
			} else {
				empleado.setJefeId(jefeIf.getId());
				empleado.setNivel(!getTxtNivel().getText().equals("")?Integer.parseInt(getTxtNivel().getText()):null);
			}
			try {
				SessionServiceLocator.getEmpleadoSessionService().saveEmpleado(empleado);
				SpiritAlert.createAlert("Empleado actualizado con éxito!",SpiritAlert.INFORMATION);
				SpiritCache.setObject("empleado",null);
				clean();
				showSaveMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al actualizar informaci\u00f3n!",SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}

	public void delete() {
		try {
			SessionServiceLocator.getEmpleadoSessionService().deleteEmpleado(empleado.getId());
			SpiritAlert.createAlert("Empleado eliminado con éxito!",SpiritAlert.INFORMATION);
			SpiritCache.setObject("empleado",null);
			clean();
			showSaveMode();
		} catch (Exception e) {
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			e.printStackTrace();
			clean();
			showSaveMode();
		}
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtNombres().getText())
				&& "".equals(this.getTxtApellidos().getText())
				&& "".equals(this.getTxtIdentificacion().getText())
				&& "".equals(this.getTxtProfesion().getText())
				&& "".equals(this.getTxtDomicilio().getText())
				&& "".equals(this.getTxtTelefono().getText())
				&& "".equals(this.getTxtCelular().getText())
				&& "".equals(this.getTxtEmail().getText())
				&& "".equals(this.getTxtJefe().getText())
				&& "".equals(this.getTxtExtension().getText())
				&& "".equals(this.getTxtNivel().getText())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateFields() {
		String strCodigo = getTxtCodigo().getText();
		String strNombres = getTxtNombres().getText();
		String strApellidos = getTxtApellidos().getText();
		String strIdentificacion = getTxtIdentificacion().getText();
		String strProfesion = getTxtProfesion().getText();
		String strDomicilio = getTxtDomicilio().getText();
		String strExtension = getTxtExtension().getText();
		
		Collection empleado = null;
		boolean codigoRepetido = false;

		try {
			empleado = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator empleadoIt = empleado.iterator();

		while (empleadoIt.hasNext()) {
			EmpleadoIf empleadoIf = (EmpleadoIf) empleadoIt.next();

			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(empleadoIf.getCodigo()))
					codigoRepetido = true;

			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(empleadoIf.getCodigo()))
					if (this.empleado.getId().equals(empleadoIf.getId()) == false)
						codigoRepetido = true;
		}

		if (codigoRepetido) {
			SpiritAlert.createAlert("El c\u00f3digo del Empleado está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un c\u00f3digo para el Empleado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombres)) || (strNombres == null))) {
			SpiritAlert.createAlert("Debe ingresar los nombres del Empleado !!",SpiritAlert.WARNING);
			getTxtNombres().grabFocus();
			return false;
		}

		if ((("".equals(strApellidos)) || (strApellidos == null))) {
			SpiritAlert.createAlert("Debe ingresar los apellidos del Empleado !!",SpiritAlert.WARNING);
			getTxtApellidos().grabFocus();
			return false;
		}
		
		if (getCmbTipoIdentificacion().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar el tipo de identificaci\u00f3n para el Empleado !!",SpiritAlert.WARNING);
			getCmbTipoIdentificacion().grabFocus();
			return false;
		}

		if ((("".equals(strIdentificacion)) || (strIdentificacion == null))){
			SpiritAlert.createAlert("Debe ingresar la identificaci\u00f3n para el Empleado !!",SpiritAlert.WARNING);
			getTxtIdentificacion().grabFocus();
			return false;
		}
		
		tipoIdentificacionIf = (TipoIdentificacionIf)getCmbTipoIdentificacion().getSelectedItem();
		
		if ( tipoIdentificacionIf!=null ){
			if (tipoIdentificacionIf.getCodigo().equals("CE")) {
				if (getTxtIdentificacion().getText().trim().length() < MAX_LONGITUD_CEDULA) {
					SpiritAlert.createAlert("El número de cédula del empleado debe tener " + MAX_LONGITUD_CEDULA + " dígitos!!!", SpiritAlert.WARNING);
					getTxtIdentificacion().grabFocus();
					return false;
				}
			} else if (tipoIdentificacionIf.getCodigo().equals("RU")) {
				if (getTxtIdentificacion().getText().trim().length() < MAX_LONGITUD_RUC) {
					SpiritAlert.createAlert("El RUC del empleado debe tener " + MAX_LONGITUD_RUC + " dígitos!!!", SpiritAlert.WARNING);
					getTxtIdentificacion().grabFocus();
					return false;
				}
			}
			
			if (tipoIdentificacionIf.getCodigo().equals("CE") || tipoIdentificacionIf.getCodigo().equals("RU")) {
				if (!ValidarIdentificacion.esNumeroIdentificacionValido(tipoIdentificacionIf.getCodigo(), getTxtIdentificacion().getText())) {
					if ( tipoIdentificacionIf.getCodigo().equals("CE") )
						SpiritAlert.createAlert("Número de Cédula no es válido!!!", SpiritAlert.WARNING);
					else// if ( tipoIdentificacionIf.getCodigo().equals("RU") )
						SpiritAlert.createAlert("Número de RUC no es válido!!!", SpiritAlert.WARNING);
					getTxtIdentificacion().grabFocus();
					return false;
				}
			}
		} else {
			SpiritAlert.createAlert("No existe Tipo de Identificaci\u00f3n, Ingrese Una!!!", SpiritAlert.WARNING);
			getCmbTipoIdentificacion().grabFocus();
			return false;
		}	

		if ((("".equals(strProfesion)) || (strProfesion == null))){
			SpiritAlert.createAlert("Debe ingresar la profesi\u00f3n del Empleado !!",SpiritAlert.WARNING);
			getTxtProfesion().grabFocus();
			return false;
		}

		if ((("".equals(strDomicilio)) || (strDomicilio == null))){
			SpiritAlert.createAlert("Debe ingresar el domicilio del Empleado !!",SpiritAlert.WARNING);
			getTxtDomicilio().grabFocus();
			return false;
		}
		
		if(!("".equals(getTxtEmail().getText())) && !(getTxtEmail().getText() == null)){
			if(!Utilitarios.validarCorreoElectronico(getTxtEmail().getText())){
				SpiritAlert.createAlert("El E-mail está mal escrito !!",SpiritAlert.WARNING);
				getTxtEmail().grabFocus();
				return false;
			}
		}

		if (getCmbOficina().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar la Oficina en la que trabajará el Empleado !!",SpiritAlert.WARNING);
			getCmbOficina().grabFocus();
			return false;
		}

		if (getCmbDepartamento().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar el Departamento al que será asignado el Empleado !!",SpiritAlert.WARNING);
			getCmbDepartamento().grabFocus();
			return false;
		}

		/*if (getCmbTipoContrato().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar el Tipo de Contrato para el Empleado !!",SpiritAlert.WARNING);
			getCmbTipoContrato().grabFocus();
			return false;
		}*/

		if (getCmbTipoEmpleado().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar el tipo de empleado !!",SpiritAlert.WARNING);
			getCmbTipoEmpleado().grabFocus();
			return false;
		}

		/*if ((("".equals(strExtension)) || (strExtension == null))){
			SpiritAlert.createAlert("Debe ingresar la extensi\u00f3n del Empleado !!",SpiritAlert.WARNING);
			getTxtExtension().grabFocus();
			return false;
		}*/

		if (getCmbEstado().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar el estado del Empleado !!",SpiritAlert.WARNING);
			getCmbEstado().grabFocus();
			return false;
		}
		
		if(getCmbTipoCuenta().getSelectedItem() != null && getCmbBanco().getSelectedItem() == null){
			SpiritAlert.createAlert("Si eligio un tipo de cuenta, debe seleccionar el Banco.",SpiritAlert.WARNING);
			getCmbBanco().grabFocus();
			return false;
		}

		return true;
	}
	
	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombres().setText("");
		this.getTxtApellidos().setText("");
		this.getTxtIdentificacion().setText("");
		this.getTxtProfesion().setText("");
		this.getTxtDomicilio().setText("");
		this.getTxtTelefono().setText("");
		this.getTxtCelular().setText("");
		this.getTxtEmail().setText("");
		this.getCmbOficina().setSelectedItem("");
		this.getCmbDepartamento().setSelectedItem("");
		this.getCmbTipoEmpleado().setSelectedItem("");
		this.getTxtJefe().setText("");
		this.getTxtExtension().setText("");
		this.getCmbEstado().setSelectedItem("");
		this.getTxtNivel().setText("");
		this.getCmbDepartamento().removeAllItems();
		this.getCmbEstado().removeAllItems();
		this.getCmbOficina().removeAllItems();
		this.getCmbTipoEmpleado().removeAllItems();
		this.getTxtNumeroCuenta().setText("");
	}

	public void showFindMode() {
		getTxtCodigo().setBackground(Parametros.findColor);
		getTxtNombres().setBackground(Parametros.findColor);
		getTxtApellidos().setBackground(Parametros.findColor);
		getTxtIdentificacion().setBackground(Parametros.findColor);
		getCmbTipoIdentificacion().setBackground(Parametros.findColor);
		
		getTxtCodigo().setEnabled(true);
		getTxtNombres().setEnabled(true);
		getTxtApellidos().setEnabled(true);
		getCmbTipoIdentificacion().setSelectedItem(null);
		getCmbTipoIdentificacion().setEnabled(true);
		getTxtIdentificacion().setEnabled(true);
		getTxtProfesion().setEnabled(false);
		getTxtDomicilio().setEnabled(false);
		getTxtTelefono().setEnabled(false);
		getTxtCelular().setEnabled(false);
		getTxtEmail().setEnabled(false);
		getCmbOficina().setEnabled(false);
		getCmbDepartamento().setEnabled(false);
		getCmbTipoEmpleado().setEnabled(false);
		getTxtJefe().setEnabled(false);
		getBtnBuscarJefe().setEnabled(false);
		getTxtExtension().setEnabled(false);
		getCmbEstado().setEnabled(false);
		getTxtNivel().setEnabled(false);
		getTxtCodigo().grabFocus();
	}

	public void cargarCombos() {
		cargarComboEstado();
		cargarComboTipoIdentificacion();
		cargarComboOficina();
		cargarComboDepartamento();
		cargarComboTipoEmpleado();
		cargarComboBanco();
		cargarComboTipoCuenta();
	}
	
	private void cargarComboTipoCuenta(){
		getCmbTipoCuenta().removeAllItems();
		getCmbTipoCuenta().addItem(TIPOCUENTA_CORRIENTE);
		getCmbTipoCuenta().addItem(TIPOCUENTA_AHORROS);	
		getCmbTipoCuenta().addItem(TIPOCUENTA_EFECTIVO);	
		getCmbTipoCuenta().setSelectedIndex(-1);
	}
	
	private void cargarComboBanco(){
		try {
			List bancos = (List) SessionServiceLocator.getBancoSessionService().getBancoList();
			refreshCombo(getCmbBanco(), bancos);
			getCmbBanco().setSelectedIndex(-1);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	private void cargarComboEstado(){
		getCmbEstado().removeAllItems();
		getCmbEstado().addItem("ACTIVO");
		getCmbEstado().addItem("INACTIVO");
	}

	private void cargarComboTipoIdentificacion(){
		try {
			List tiposIdentificaciones = (List) SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacionList();
			refreshCombo(getCmbTipoIdentificacion(),tiposIdentificaciones);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboOficina(){
		try {
			List oficinas = (List) SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbOficina(),oficinas);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboDepartamento(){
		try {
			List departamentos = (List) SessionServiceLocator.getDepartamentoSessionService().findDepartamentoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbDepartamento(),departamentos);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	Comparator<TipoEmpleadoIf> ordenadorEmpleadoNombre = new Comparator<TipoEmpleadoIf>(){
		public int compare(TipoEmpleadoIf te1, TipoEmpleadoIf te2) {
			if(te1.getNombre() == null){
				return -1;
			}else if(te2.getNombre() == null){
				return 1;
			}else{
				return (te1.getNombre().compareTo(te2.getNombre()));
			}
		}		
	};
	
	private void cargarComboTipoEmpleado(){
		try {
			List tiposEmpleados = (List) SessionServiceLocator.getTipoEmpleadoSessionService().findTipoEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Collections.sort(tiposEmpleados,ordenadorEmpleadoNombre);
			refreshCombo(getCmbTipoEmpleado(),tiposEmpleados);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		getTxtNombres().setBackground(Parametros.saveUpdateColor);
		getTxtApellidos().setBackground(Parametros.saveUpdateColor);
		getTxtIdentificacion().setBackground(Parametros.saveUpdateColor);
		getCmbTipoIdentificacion().setBackground(Parametros.saveUpdateColor);
		
		getTxtCodigo().setEnabled(true);
		getTxtNombres().setEnabled(true);
		getTxtApellidos().setEnabled(true);
		getCmbTipoIdentificacion().setEnabled(true);
		getTxtIdentificacion().setEnabled(true);
		getTxtProfesion().setEnabled(true);
		getTxtDomicilio().setEnabled(true);
		getTxtTelefono().setEnabled(true);
		getTxtCelular().setEnabled(true);
		getTxtEmail().setEnabled(true);
		getCmbOficina().setEnabled(true);
		getCmbDepartamento().setEnabled(true);
		getCmbTipoEmpleado().setEnabled(true);
		getTxtJefe().setEnabled(false);
		getBtnBuscarJefe().setEnabled(true);
		getTxtExtension().setEnabled(true);
		getCmbEstado().setEnabled(true);
		getTxtNivel().setEnabled(false);
		getBtnBorrarJefe().setEnabled(false);
		cargarCombos();
		getTxtCodigo().grabFocus();
	}

	private void initButtonListeners() {
		// Manejo los eventos de Buscar Jefe
		getBtnBuscarJefe().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoCriteria = new EmpleadoCriteria("Jefes",Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					jefeIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getTxtJefe().setText(jefeIf.getCodigo() + " - " + jefeIf.getNombres() + " "+ jefeIf.getApellidos());
					//Seteo el nivel del Empleado a uno mas de su jefe seleccionado
					if(jefeIf.getNivel() != null){
						int nivelEmpleadoActual = jefeIf.getNivel() + 1; 
						getTxtNivel().setText(nivelEmpleadoActual + "");
						getBtnBorrarJefe().setEnabled(true);
					}					
				}
			}
		});

		getBtnBorrarJefe().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				jefeIf = null;
				getTxtJefe().setText("");
				getTxtNivel().setText("" + NIVEL_JERARQUICO);
				getBtnBorrarJefe().setEnabled(false);
			}
		});
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		getTxtNombres().setBackground(Parametros.saveUpdateColor);
		getTxtApellidos().setBackground(Parametros.saveUpdateColor);
		getTxtIdentificacion().setBackground(Parametros.saveUpdateColor);
		getCmbTipoIdentificacion().setBackground(Parametros.saveUpdateColor);
		
		getTxtCodigo().setEnabled(false);
		getTxtNombres().setEnabled(true);
		getTxtApellidos().setEnabled(true);
		getCmbTipoIdentificacion().setEnabled(true);
		getTxtIdentificacion().setEnabled(true);
		getTxtProfesion().setEnabled(true);
		getTxtDomicilio().setEnabled(true);
		getTxtTelefono().setEnabled(true);
		getTxtCelular().setEnabled(true);
		getTxtEmail().setEnabled(true);
		getCmbOficina().setEnabled(true);
		getCmbDepartamento().setEnabled(true);
		getCmbTipoEmpleado().setEnabled(true);
		getTxtJefe().setEnabled(false);
		getBtnBuscarJefe().setEnabled(true);
		getBtnBorrarJefe().setEnabled(true);
		getTxtExtension().setEnabled(true);
		getCmbEstado().setEnabled(true);
		getTxtNivel().setEnabled(false);
	}

	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if (Long.valueOf(Parametros.getIdEmpresa()).compareTo(0L) != 0)
			aMap.put("empresaId", Parametros.getIdEmpresa());
		
		if ("".equals(getTxtCodigo().getText()) == false)
			aMap.put("codigo", getTxtCodigo().getText() + "%");
		else
			aMap.put("codigo", "%");
		
		if ("".equals(getTxtNombres().getText()) == false)
			aMap.put("nombres", getTxtNombres().getText() + "%");
		else
			aMap.put("nombres", "%");
		
		if ("".equals(getTxtApellidos().getText()) == false)
			aMap.put("apellidos", getTxtApellidos().getText() + "%");
		else
			aMap.put("apellidos", "%");
		
		if ("".equals(getTxtIdentificacion().getText()) == false)
			aMap.put("identificacion", getTxtIdentificacion().getText() + "%");
		else
			aMap.put("identificacion", "%");
		
		if (getCmbTipoIdentificacion().getSelectedItem() != null)
			aMap.put("tipoidentificacionId", ((TipoIdentificacionIf) getCmbTipoIdentificacion().getSelectedItem()).getId());

		return aMap;
	}

	public void find() {
		try {
			Map  mapa = buildQuery();
			EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria();
			empleadoCriteria.setQueryBuilded(mapa);
			if (empleadoCriteria.getResultListSize() > 0) {
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), empleadoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if ( popupFinder.getElementoSeleccionado() != null )
					getSelectedObject();
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
			}

		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la búsqueda de informaci\u00f3n", SpiritAlert.ERROR);
		}
	}

	private void getSelectedObject(){
		empleado = (EmpleadoIf) popupFinder.getElementoSeleccionado();
		cargarCombos();
		getTxtCodigo().setText(empleado.getCodigo());
		getTxtNombres().setText(empleado.getNombres());
		getTxtApellidos().setText(empleado.getApellidos());
		getTxtIdentificacion().setText(empleado.getIdentificacion());
		getTxtProfesion().setText(empleado.getProfesion());
		getTxtDomicilio().setText(empleado.getDireccionDomicilio());
		getTxtTelefono().setText(empleado.getTelefonoDomicilio());
		getTxtCelular().setText(empleado.getCelular());
		getTxtEmail().setText(empleado.getEmailOficina());
		getTxtExtension().setText(empleado.getExtensionOficina());
			
		if (empleado.getNivel() != null)
			getTxtNivel().setText(empleado.getNivel().toString());

		// Selecciono los elementos de los combos segun lo almacenado en la base
		if ( empleado.getTipoidentificacionId() != null )
			getCmbTipoIdentificacion().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoIdentificacion(), empleado.getTipoidentificacionId()));
		else
			getCmbTipoIdentificacion().setSelectedIndex(-1);
		
		if ( empleado.getOficinaId() != null )
			getCmbOficina().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbOficina(),empleado.getOficinaId()));
		else
			getCmbOficina().setSelectedIndex(-1);
		
		if ( empleado.getDepartamentoId() != null )
			getCmbDepartamento().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDepartamento(), empleado.getDepartamentoId()));
		else
			getCmbDepartamento().setSelectedIndex(-1);
		
		if ( empleado.getTipoempleadoId() != null )
			getCmbTipoEmpleado().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTipoEmpleado(), empleado.getTipoempleadoId()));
		else
			getCmbTipoEmpleado().setSelectedIndex(-1);

		try {
			if (empleado.getJefeId() != null) {
				jefeIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(empleado.getJefeId());
				getTxtJefe().setText(jefeIf.getCodigo() + " - " + jefeIf.getNombres() + " " + jefeIf.getApellidos());
			}
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}

		getTxtJefe().setEnabled(true);

		if ("A".equals(empleado.getEstado().toString()))
			getCmbEstado().setSelectedItem("ACTIVO");
		else
			getCmbEstado().setSelectedItem("INACTIVO");
		
		
		if(empleado.getTipoCuenta() != null && !empleado.getTipoCuenta().equals("")){
			if ( empleado.getBancoId() != null )
				getCmbBanco().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbBanco(),empleado.getBancoId()));
			else
				getCmbBanco().setSelectedIndex(-1);
			
			if (TIPOCUENTA_AHORROS.substring(0,1).equals(empleado.getTipoCuenta())){
				getCmbTipoCuenta().setSelectedItem(TIPOCUENTA_AHORROS);
			}else if (TIPOCUENTA_CORRIENTE.substring(0,1).equals(empleado.getTipoCuenta())){
				getCmbTipoCuenta().setSelectedItem(TIPOCUENTA_CORRIENTE);
			}else{
				getCmbTipoCuenta().setSelectedItem(TIPOCUENTA_EFECTIVO);
			}
			
			if(empleado.getNumeroCuenta() != null && !empleado.getNumeroCuenta().equals("")){		
				getTxtNumeroCuenta().setText(empleado.getNumeroCuenta());			
			}
			
		}else{
			getCmbBanco().setSelectedIndex(-1);
			getCmbTipoCuenta().setSelectedIndex(-1);
			getTxtNumeroCuenta().setText("");
		}	

		this.showUpdateMode();
	}

	public void report() {
		
	}
	
	public void refresh() {
		cargarComboTipoIdentificacion();
		cargarComboOficina();
		cargarComboDepartamento();
		cargarComboTipoEmpleado();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}
 
}
