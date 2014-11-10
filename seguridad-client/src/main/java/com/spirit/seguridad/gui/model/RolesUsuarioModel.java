package com.spirit.seguridad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.UsuarioData;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.GeneralFinder;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.general.gui.criteria.UsuarioCriteria;
import com.spirit.seguridad.entity.RolIf;
import com.spirit.seguridad.entity.RolUsuarioData;
import com.spirit.seguridad.entity.RolUsuarioIf;
import com.spirit.seguridad.gui.panel.JPRolesUsuario;
import com.spirit.seguridad.handler.TipoUsuarioTimeTracker;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.MyKeyListenerLowerCase;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class RolesUsuarioModel extends JPRolesUsuario {

	private static final long serialVersionUID = -5070239473558071868L;
	private EmpleadoCriteria empleadoCriteria;
	private UsuarioCriteria rolesUsuarioCriteria;
	private UsuarioIf usuario; 
	private EmpleadoIf empleado;
	private EmpresaIf empresa;
	
    //Contiene los nodos ya insertados en el arbol Original
	Map existeRolMapT = new HashMap();
	//Contiene los nodos ya insertados en el arbol Personalizado
	Map existeRolMapS = new HashMap();
	
	private static final int MAX_LONGITUD_USUARIO = 10;
	private static final int MAX_LONGITUD_CLAVE = 10;
	private static final String TIPO_USER = "USUARIO";
	private static final String TIPO_ADMIN = "ADMINISTRADOR";
	private static final String TIPO_SUPER = "SUPERUSUARIO";
	private static final String TIPO_DEVELOPER = "DESARROLLADOR";
	
	public RolesUsuarioModel() {
		iniciarComponentes();
		initKeyListeners();
		encerarRolesUsuario();
		this.showSaveMode();
		initListeners();
		cargarTodosRoles();
		new HotKeyComponent(this);
	}

	private void iniciarComponentes(){
		
		getBtnAgregarRoles().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/goNextRecord.png"));
		getBtnAgregarRoles().setText("");
		getBtnQuitarRoles().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/goPreviousRecord.png"));
		getBtnQuitarRoles().setText("");
		getBtnBuscarEmpleado().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		cargarComboTipoUsuarioTimeTracker();
	}
	
	private void cargarComboTipoUsuarioTimeTracker(){
		TipoUsuarioTimeTracker tipos[] = TipoUsuarioTimeTracker.values();
		List<TipoUsuarioTimeTracker> listaTipos = new ArrayList<TipoUsuarioTimeTracker>(Arrays.asList(tipos));
		listaTipos.add(0, null);
		refreshCombo(getCmbTipoUsuarioTimeTracker(), listaTipos);
		
	}
	
	private void initKeyListeners() {
		getTxtUsuario().addKeyListener(new TextChecker(MAX_LONGITUD_USUARIO));
		getTxtUsuario().addKeyListener(new MyKeyListenerLowerCase());
		getTxtClave().addKeyListener(new TextChecker(MAX_LONGITUD_CLAVE));
		getTxtClave().addKeyListener(new MyKeyListenerLowerCase());
		getTxtConfirmarClave().addKeyListener(new TextChecker(MAX_LONGITUD_CLAVE));
		getTxtConfirmarClave().addKeyListener(new MyKeyListenerLowerCase());
	}
	
	private void initListeners() {
		//Manejo los eventos de Buscar empleado
		getBtnBuscarEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idEmpresa = 0L;
				
				if (getCmbTipoUsuario().getSelectedItem() != null) {
					if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_ADMIN))
						idEmpresa = Parametros.getIdEmpresa();
					else if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_SUPER))
						idEmpresa = ((EmpresaIf) getCmbEmpresa().getSelectedItem()).getId();
				}

				empleadoCriteria = new EmpleadoCriteria(
						"de Empleados",Parametros.getIdEmpresa());
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
										empleadoCriteria,
										JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					empleado = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					
					clean();
					
					try {
						empresa = SessionServiceLocator.getEmpresaSessionService().getEmpresa(empleado.getEmpresaId());
					} catch (GenericBusinessException e1) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e1.printStackTrace();
					}
					
					getCmbEmpresa().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbEmpresa(), empleado.getEmpresaId()));
					
					try {
						Collection usuarios  = SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpleadoId(empleado.getId());
						
						Iterator itusuarios = usuarios.iterator();
						while (itusuarios.hasNext()) {
							UsuarioIf usuarioIf = (UsuarioIf) itusuarios.next();
							if(usuarioIf.getEmpleadoId().compareTo(empleado.getId())==0)
								usuario = usuarioIf;
						}
						
						if (usuarios.size()>0) {
							getTxtEmpleado().setText(empleado.getNombres() + " " + empleado.getApellidos());
        					//pfEmp.hidePopup();
        					Collection rolesUsuario = SessionServiceLocator.getRolUsuarioSessionService().findRolUsuarioByUsuarioId(usuario.getId());
							
        					//seteo los campos para usuario
        					getTxtUsuario().setText(usuario.getUsuario());
        					
        					loadCombos();
        					
        					if(TIPO_ADMIN.substring(0,1).equals(usuario.getTipousuario()))
        						getCmbTipoUsuario().setSelectedItem(TIPO_ADMIN);
        					else if(TIPO_SUPER.substring(0,1).equals(usuario.getTipousuario()))
        						getCmbTipoUsuario().setSelectedItem(TIPO_SUPER);
        					else if(TIPO_USER.substring(0,1).equals(usuario.getTipousuario()))
        						getCmbTipoUsuario().setSelectedItem(TIPO_USER);
        					
        					TipoUsuarioTimeTracker tutt = TipoUsuarioTimeTracker.getTipoUsuarioByLetra(usuario.getTipousuarioTimetracker());
        					getCmbTipoUsuarioTimeTracker().getModel().setSelectedItem(tutt);
        					getTxtClave().setText("");
        					getTxtConfirmarClave().setText("");
        					//getTxtClave().setText(usuario.getClave());
        					//getTxtConfirmarClave().setText(usuario.getClave());
        					
							if(rolesUsuario.size()>0)
								armarListViewSeleccionados(rolesUsuario);
							
							showUpdateMode();
							
						} else {
							//seteo los campos para usuario
        					getTxtUsuario().setText("");
        					getTxtClave().setText("");
        					getTxtConfirmarClave().setText("");
        					getTxtEmpleado().setText(empleado.getNombres() + " " + empleado.getApellidos());
        					//pfEmp.hidePopup();
        					encerarRolesUsuario();
        					cargarTodosRoles();
        					showSaveMode();
        				}
						
					} catch (GenericBusinessException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		getCmbTipoUsuario().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoUsuario().getSelectedItem() != null) {
					if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_USER))
						getBtnBuscarEmpleado().setEnabled(true);
					else
						getBtnBuscarEmpleado().setEnabled(false);

					encerarRolesUsuario();
					cargarTodosRoles();
				}
			}
		}); 
		
		getCmbEmpresa().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				//getCmbEmpresa().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbEmpresa(), empleado.getEmpresaId()));
				if (empresa != null && getCmbEmpresa().getSelectedItem() != null) {
					if (empresa.getId().compareTo(((EmpresaIf) getCmbEmpresa().getSelectedItem()).getId()) != 0) {
						getTxtEmpleado().setText("");
						empleado = null;
					}
				}
				
				empresa = (EmpresaIf) getCmbEmpresa().getSelectedItem();
				System.out.println(empresa.getNombre()+"empresa seleccionada:"+empresa.getId());
				cargarTodosRoles();
				
			}
		});
	}
	
	private void encerarRolesUsuario(){
		getListRolesSeleccionados().setModel(new DefaultListModel());
	}
	
	//Mando a cargar todos los roles de la base
	private void cargarTodosRoles(){
		try {
			//Seteo el model del List View de los Roles Seleccionados en blanco
			//getListRolesSeleccionados().setModel(new DefaultListModel());
			DefaultListModel modelListRolesT = new DefaultListModel();
			
			//Creo la coleccion con los roles existentes
			Map parameterMap = new HashMap();
			
			if (getCmbTipoUsuario().getSelectedItem() != null) {
				if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_DEVELOPER))
					parameterMap.put("tipoRolUsuario", "%");
				else if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_SUPER))
					parameterMap.put("tipoRolUsuario", TIPO_SUPER.substring(0,1));
				else if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_ADMIN)) {
					parameterMap.put("tipoRolUsuario", TIPO_ADMIN.substring(0,1));
					//J:Se lo añadio porq si habian 1 rol de empresa 1, y otro rol de empresa 2
					//.. dejaba crear usuario admin de la empresa 1 pero con el rol de la empresa 2					
					parameterMap.put("empresaId", ((EmpresaIf) getCmbEmpresa().getSelectedItem()).getId());
				} else if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_USER)) {
					parameterMap.put("tipoRolUsuario", TIPO_USER.substring(0,1));
					parameterMap.put("empresaId", Parametros.getIdEmpresa());
				}
			}
			else{				
				parameterMap.put("empresaId", ((EmpresaIf) getCmbEmpresa().getSelectedItem()).getId());	
			}
			
			
			
			System.out.println("antes del select parame"+parameterMap);
			Collection rolesColeccionT = SessionServiceLocator.getRolSessionService().findRolByQuery(parameterMap);
			Iterator itRolesColeccionT = rolesColeccionT.iterator();
 
			// Creo instancia de cada uno de los roles
			while (itRolesColeccionT.hasNext()) {
				RolIf rolIf = (RolIf) itRolesColeccionT.next();
				String codigoRolT = rolIf.getCodigo();
				if ( !existeRolMapS.containsKey( codigoRolT ) ){
					modelListRolesT.addElement(rolIf);				
					
					//Extraigo el codigo del rol para guardarlo en el mapa
					existeRolMapT.put(codigoRolT,rolIf);
				}
			}
			
			//Añado el modelo con los items seleccionados a la lista
			getListTodosRoles().setModel(modelListRolesT);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	//Valido que todos los campos hayan sido llenados
	public boolean validateFields() {	
		if (getCmbTipoUsuario().getSelectedItem() != null) {
			if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_SUPER) == false && getCmbEmpresa().getSelectedItem() == null) {
				SpiritAlert.createAlert("Debe seleccionar la empresa!!!", SpiritAlert.WARNING);
				this.getCmbEmpresa().grabFocus();
				return false;
			}
			
			if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_USER) && "".equals(this.getTxtEmpleado().getText())) {
				SpiritAlert.createAlert("Debe ingresar el empleado!!!", SpiritAlert.WARNING);
				this.getBtnBuscarEmpleado().grabFocus();
				return false;
			}
			
			if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_ADMIN) && getCmbEmpresa().getSelectedItem() != null) {
				try {
					Map parameterMap = new HashMap();
					parameterMap.put("tipousuario", TIPO_ADMIN.substring(0,1));
					parameterMap.put("empresaId", ((EmpresaIf) getCmbEmpresa().getSelectedItem()).getId());
					Iterator it = SessionServiceLocator.getUsuarioSessionService().findUsuarioByQuery(parameterMap).iterator();
					
					
					if (it.hasNext()) {
						SpiritAlert.createAlert("No se pudo crear el usuario.\n" +
												"La empresa seleccionada ya tiene asignado un usuario administrador", SpiritAlert.WARNING);
						return false;
					}
					
					//VALIDAR QUE LA EMPRESA SEA LA MISMA Q EL ROL EMPRESA					
					DefaultListModel modelListRolesS = (DefaultListModel) getListRolesSeleccionados().getModel();
					//Mando a insertar cada uno de los roles que fueron escogidos para este usuario
					Long idempresaGlobal=((EmpresaIf) getCmbEmpresa().getSelectedItem()).getId();
					for(int i= 0; i < modelListRolesS.getSize(); i++) {
						RolIf rol = (RolIf) modelListRolesS.getElementAt(i);
						Long idempresaRol=rol.getEmpresaId();						
						if(!idempresaGlobal.equals(idempresaRol))
						{
							SpiritAlert.createAlert("No se pudo crear el usuario.\n" +
													"El rol que escogio pertenece a otra empresa", SpiritAlert.WARNING);
							return false;
						}
					}
					
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.WARNING);
					getTxtUsuario().grabFocus();
					return false;
				}
			}
		}
		
		if ("".equals(this.getTxtUsuario().getText())) {
			SpiritAlert.createAlert("Debe ingresar el usuario!!!", SpiritAlert.WARNING);
			getTxtUsuario().grabFocus();
			return false;
		}
		
		Collection usuarios = null;
		boolean usuarioRepetido = false;

		try {
			usuarios = SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpresaId(((EmpresaIf) getCmbEmpresa().getSelectedItem()).getId());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator usuariosIt = usuarios.iterator();

		while (usuariosIt.hasNext()) {
			UsuarioIf usuarioIf = (UsuarioIf) usuariosIt.next();

			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtUsuario().getText().equals(usuarioIf.getUsuario()))
					usuarioRepetido = true;

			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtUsuario().getText().equals(usuarioIf.getUsuario()))
					if (usuario.getId().equals(usuarioIf.getId()) == false)
						usuarioRepetido = true;
		}

		if (usuarioRepetido) {
			SpiritAlert.createAlert("El nombre de usuario ingresado ya existe !!", SpiritAlert.WARNING);
			getTxtUsuario().grabFocus();
			return false;
		}
		
		if ("".equals(this.getTxtClave().getPassword())) {
			SpiritAlert.createAlert("Debe ingresar una clave!!!", SpiritAlert.WARNING);
			getTxtClave().grabFocus();
			return false;
		}
		
		if ("".equals(this.getTxtConfirmarClave().getPassword())) {
			SpiritAlert.createAlert("Debe confirmar la clave!!!", SpiritAlert.WARNING);
			getTxtConfirmarClave().grabFocus();
			return false;
		}
		
		if(this.getTxtClave().getText().equals(this.getTxtConfirmarClave().getText()) == false) {
			SpiritAlert.createAlert("Las claves no son iguales!!!", SpiritAlert.WARNING);
			getTxtClave().grabFocus();
			return false;
		}
		
		if (this.getCmbTipoUsuario().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de usuario!!!", SpiritAlert.WARNING);
			getCmbTipoUsuario().grabFocus();
			return false;
		}
		
		return true;
	}

	public void save() {
		//Veo que todos los datos hayan sido llenados antes de insertar el registro
		if (validateFields()) {
			Long idUsuario = 0L;
			
			try {
				//guardo los datos del usuario
				UsuarioData dataUser = new UsuarioData();
				try {
					dataUser.setClave(Utilitarios.encrypt(this.getTxtClave().getText(), "SHA"));
				} catch (NoSuchAlgorithmException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
				dataUser.setTipousuario(this.getCmbTipoUsuario().getSelectedItem().toString().substring(0,1));
				dataUser.setUsuario(this.getTxtUsuario().getText());
				if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_USER))
					dataUser.setEmpleadoId(empleado.getId());
				if (!getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_SUPER))
					dataUser.setEmpresaId(((EmpresaIf)getCmbEmpresa().getSelectedItem()).getId());
				
				TipoUsuarioTimeTracker tutt = (TipoUsuarioTimeTracker) getCmbTipoUsuarioTimeTracker().getSelectedItem();
				if ( tutt != null )
					dataUser.setTipousuarioTimetracker(tutt.getLetra());
				
				idUsuario = SessionServiceLocator.getUsuarioSessionService().addUsuario(dataUser).getPrimaryKey();
				
				
				RolUsuarioData data = new RolUsuarioData();
				//Obtengo todos los roles que han sido seleccionados para asignarle al usuario
				DefaultListModel modelListRolesS = (DefaultListModel) getListRolesSeleccionados().getModel();
				//Mando a insertar cada uno de los roles que fueron escogidos para este usuario
				
				for(int i= 0; i < modelListRolesS.getSize(); i++) {
					RolIf rol = (RolIf) modelListRolesS.getElementAt(i);
					data.setRolId(rol.getId());
					data.setUsuarioId(idUsuario);
					SessionServiceLocator.getRolUsuarioSessionService().addRolUsuario(data);
				}
				
				SpiritAlert.createAlert("Información guardada con éxito!", SpiritAlert.INFORMATION);
				
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
			}
			
			this.clean();
			this.showSaveMode();
		}
	}
	
	public void update() {
		//Veo que todos los datos hayan sido llenados antes de actualizar los registros
		if (validateFields()) {
			
			//Mando a borrar los roles de usuarios que tenia en usuario anteriormente en la base
			deleteRolesUsuario();			
			try {
				
				//guardo los datos del usuario
				//usuario.setClave(Utilitarios.encrypt(this.getTxtClave().getText()));
				usuario.setTipousuario(this.getCmbTipoUsuario().getSelectedItem().toString().substring(0,1));
				
				TipoUsuarioTimeTracker tutt = (TipoUsuarioTimeTracker) getCmbTipoUsuarioTimeTracker().getSelectedItem();
				if ( tutt != null )
					usuario.setTipousuarioTimetracker(tutt.getLetra());
				
				if (empleado != null)
					usuario.setEmpleadoId(empleado.getId());
				
				if (!getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_SUPER))
					usuario.setEmpresaId(((EmpresaIf)getCmbEmpresa().getSelectedItem()).getId());
				SessionServiceLocator.getUsuarioSessionService().saveUsuario(usuario);
				
				RolUsuarioData data = new RolUsuarioData();
				//Obtengo todos los roles que han sido seleccionados para asignarle al usuario
				DefaultListModel modelListRolesS = (DefaultListModel) getListRolesSeleccionados().getModel();
				//Mando a insertar cada uno de los roles que fuereon esocgidos para este usuario
				for(int i= 0; i < modelListRolesS.getSize(); i++){
					RolIf rol = (RolIf) modelListRolesS.getElementAt(i);
					data.setRolId(rol.getId());
					data.setUsuarioId(usuario.getId());
					SessionServiceLocator.getRolUsuarioSessionService().addRolUsuario(data);
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
			}
			
			SpiritAlert.createAlert("Información actualizada con éxito!!!", SpiritAlert.INFORMATION);
			this.clean();
			this.showSaveMode();
		}
	}
	
	public void delete() {
		try {
			deleteRolesUsuario();
			SessionServiceLocator.getUsuarioSessionService().deleteUsuario(usuario.getId());
			SpiritAlert.createAlert("Registro eliminado con éxito!!!", SpiritAlert.INFORMATION);
			this.clean();
			this.showSaveMode();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			this.clean();
			this.showSaveMode();
		}		
	}
	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public void refresh() {
		cargarTodosRoles();
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
	
	public void deleteRolesUsuario(){
		try{
			
			//Creo la coleccion con los roles de usuarios segun el id de usuario
			Collection rolesUsuarioColeccion = SessionServiceLocator.getRolUsuarioSessionService().findRolUsuarioByUsuarioId(usuario.getId());
			Iterator itRolesUsuarioColeccion = rolesUsuarioColeccion.iterator();
 
			// Creo instancia de cada uno de los roles de Usuario
			while (itRolesUsuarioColeccion.hasNext()) {
				RolUsuarioIf rolUsuarioIf = (RolUsuarioIf) itRolesUsuarioColeccion.next();
				SessionServiceLocator.getRolUsuarioSessionService().deleteRolUsuario(rolUsuarioIf.getId());		
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			this.clean();
		}	
	}

	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if ("".equals(getTxtUsuario().getText()) == false)
			aMap.put("usuario", getTxtUsuario().getText() + "%");			
		else
			aMap.put("usuario", "%");
		
		if ("S".equals(((UsuarioIf) Parametros.getUsuarioIf()).getTipousuario()))
			aMap.put("tipousuario", "A");
		else if ("A".equals(((UsuarioIf) Parametros.getUsuarioIf()).getTipousuario())) {
			aMap.put("tipousuario", "U");
			aMap.put("empresaId", Parametros.getIdEmpresa());
		}
		
		return aMap;
	}
	
	public void find() {
		try {
			Map mapa = buildQuery();
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.addElement(180);
			anchoColumnas.addElement(80);
			anchoColumnas.addElement(180);
			rolesUsuarioCriteria = new UsuarioCriteria();
			rolesUsuarioCriteria.setQueryBuilded(mapa);
			if (rolesUsuarioCriteria.getResultListSize() > 0) {
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), rolesUsuarioCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, null);
				if ( popupFinder.getElementoSeleccionado() != null )
					getSelectedObject(popupFinder.getElementoSeleccionado());
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		}
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtUsuario().getText())) 
			return true;
		else
			return false;
	}

	public void clean() {
		this.getTxtUsuario().setText("");
		this.getTxtEmpleado().setText("");
		this.getTxtClave().setText("");
		this.getTxtConfirmarClave().setText("");
		
		//mando a quitar los listener
		getBtnAgregarRoles().removeActionListener(oActionListenerAgregarRoles);
		getBtnQuitarRoles().removeActionListener(oActionListenerQuitarRoles);
		
		getCmbTipoUsuarioTimeTracker().setSelectedItem(null);
		
		//Limpio los mapas despues de guardar lso registros en la base
		existeRolMapT.clear();
		existeRolMapS.clear();
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtUsuario().setBackground(Parametros.saveUpdateColor);
		getTxtEmpleado().setEnabled(true);
		getTxtEmpleado().setEditable(false);
		getTxtUsuario().setEditable(true);
		getCmbTipoUsuario().setEnabled(false);
		getCmbTipoUsuarioTimeTracker().setEnabled(true);
		getTxtClave().setEnabled(true);
		getTxtConfirmarClave().setEnabled(true);
		getBtnAgregarRoles().addActionListener(oActionListenerAgregarRoles);
		getBtnQuitarRoles().addActionListener(oActionListenerQuitarRoles);
		loadCombos();	
		
		if (getCmbTipoUsuario().getSelectedItem() != null) {
			if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_USER))
				getBtnBuscarEmpleado().setEnabled(true);
			else
				getBtnBuscarEmpleado().setEnabled(false);
			
			if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_USER))
				getBtnBuscarEmpleado().grabFocus();
			else
				getTxtUsuario().grabFocus();
		}
	}
	
	public void showUpdateMode() {
		setUpdateMode();
		getTxtUsuario().setBackground(Parametros.saveUpdateColor);
		getTxtUsuario().setEditable(false);
		getTxtEmpleado().setEnabled(true);
		getTxtClave().setEnabled(false);
		getTxtConfirmarClave().setEnabled(false);
		getCmbTipoUsuario().setEnabled(false);
		getCmbTipoUsuarioTimeTracker().setEnabled(true);
		getBtnAgregarRoles().addActionListener(oActionListenerAgregarRoles);
		getBtnQuitarRoles().addActionListener(oActionListenerQuitarRoles);
		if (getCmbTipoUsuario().getSelectedItem() != null) {
			if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_USER))
				getBtnBuscarEmpleado().grabFocus();
			else
				getCmbTipoUsuario().grabFocus();
			
			if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_USER))
				getBtnBuscarEmpleado().setEnabled(true);
			else
				getBtnBuscarEmpleado().setEnabled(false);
		}
	}
	
	public void showFindMode() {
		getTxtUsuario().setBackground(Parametros.findColor);
		getTxtUsuario().setEditable(true);
		getTxtClave().setEnabled(false);
		getTxtConfirmarClave().setEnabled(false);
		getTxtEmpleado().setEnabled(false);
		getCmbTipoUsuario().setEnabled(false);
		getCmbTipoUsuarioTimeTracker().setEnabled(false);
		getBtnBuscarEmpleado().setEnabled(false);
		getCmbTipoUsuario().setSelectedItem(null);
		getTxtUsuario().grabFocus();
	}
	
	//listener para el evento del boton agregar roles al jlist de la derecha
	ActionListener oActionListenerQuitarRoles = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			//Obtengo el model del List View de todos los roles
			DefaultListModel modelListRolesT = (DefaultListModel) getListTodosRoles().getModel() ;
			//Obtengo el model del List View de los roles seleccionados
			DefaultListModel modelListRolesS = (DefaultListModel) getListRolesSeleccionados().getModel() ;
			//Mando a llamar a la funcion que me permite mover los roles de una lista a otra
			moverRolesEntreLista(modelListRolesS,modelListRolesT,getListRolesSeleccionados(),existeRolMapS,existeRolMapT);	
	    }
	};
	    	
	//listener para el evento del boton agregar roles al jlist de la derecha
	ActionListener oActionListenerAgregarRoles = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			//Obtengo el model del List View de todos los roles
			DefaultListModel modelListRolesT = (DefaultListModel) getListTodosRoles().getModel() ;
			//Obtengo el model del List View de los roles seleccionados
			DefaultListModel modelListRolesS = (DefaultListModel) getListRolesSeleccionados().getModel() ;
			//Mando a llamar a la funcion que me permite mover los roles de una lista a otra
			moverRolesEntreLista(modelListRolesT,modelListRolesS,getListTodosRoles(),existeRolMapT,existeRolMapS);		
		}
	};

	//Funcion que me permite mover los roles entre lista
	private void moverRolesEntreLista(DefaultListModel modelListRolesO, DefaultListModel modelListRolesD,JList listRolesO,Map existeRolMapO, Map existeRolMapD){
		//Obtengo todos los roles seleccionados del List View Origen
		Object[] rolesSeleccionadosO =  listRolesO.getSelectedValues();
		//Si no ha sido seleccionado ningun nodo, se meustar un mensaje de aviso
		if (rolesSeleccionadosO.length==0)
			SpiritAlert.createAlert("Por favor seleccione el/los elemento(s) a mover!", SpiritAlert.INFORMATION);
		else {
			//Recorro uno a uno los roles que fueron seleccionados del List View Origen
			for(int i=0;i < rolesSeleccionadosO.length; i++){
				//Creo una instancia del elemento seleccionado i
				RolIf rolSeleccionadoO = (RolIf) rolesSeleccionadosO[i];
				//Extraigo el codigo del rol para guardarlo en el mapa
				String codigoRolO = rolSeleccionadoO.getCodigo();
				//Remueve el elemento del listview y del mapa de origen
				modelListRolesO.removeElement(rolesSeleccionadosO[i]);
				existeRolMapO.remove(codigoRolO);
				//Agrego el elemento en el listview y en el mapa de destino
				modelListRolesD.addElement(rolesSeleccionadosO[i]);
				existeRolMapD.put(codigoRolO,rolSeleccionadoO);
			}
		}
	}
	
	private void getSelectedObject(Object objetoSeleccionado) {
		try {
			usuario = (UsuarioIf)objetoSeleccionado;
			getCmbEmpresa().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbEmpresa(), usuario.getEmpresaId()));
			getCmbEmpresa().repaint();
			getTxtUsuario().setText(usuario.getUsuario());
			
			//Mando a ver si existen ya asignados roles a ese usuario
			Collection rolesUsuario = SessionServiceLocator.getRolUsuarioSessionService().findRolUsuarioByUsuarioId(usuario.getId());
			
			//Si el usuario tiene ya roles asignados, cargamos el modo update... caso contrario el modo save xq es primera vez que se le van asignar roles
			//cargamos los datos para el usuario
			if (usuario.getEmpleadoId() != null) {
				empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(usuario.getEmpleadoId()); 
				getTxtEmpleado().setText(empleado.getNombres() + " " + empleado.getApellidos());
			}
			
			getTxtUsuario().setText(usuario.getUsuario());
			
			if(TIPO_ADMIN.substring(0,1).equals(usuario.getTipousuario()))
				getCmbTipoUsuario().setSelectedItem(TIPO_ADMIN);
			else if(TIPO_SUPER.substring(0,1).equals(usuario.getTipousuario()))
				getCmbTipoUsuario().setSelectedItem(TIPO_SUPER);
			else if(TIPO_USER.substring(0,1).equals(usuario.getTipousuario()))
				getCmbTipoUsuario().setSelectedItem(TIPO_USER);
			
			getTxtClave().setText("");
			getTxtConfirmarClave().setText("");
			
			if (rolesUsuario.size()>0)
				armarListViewSeleccionados(rolesUsuario);
			else{
				encerarRolesUsuario();
				cargarTodosRoles();
			}
			
			//setear el usuario timetracker
			getCmbTipoUsuarioTimeTracker().setSelectedItem(TipoUsuarioTimeTracker.getTipoUsuarioByLetra(usuario.getTipousuarioTimetracker()));
			
			showUpdateMode();
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	private void armarListViewSeleccionados(Collection rolesUsuario){
		try {
			//Obtengo el model del List View de todos los roles
			DefaultListModel modelListRolesT = (DefaultListModel) getListTodosRoles().getModel() ;
			//Obtengo el model del List View de los roles seleccionados
			DefaultListModel modelListRolesS = (DefaultListModel) getListRolesSeleccionados().getModel() ;
			
			//Creo un iterator para barrer cada uo de los roles del usuario
			Iterator itRolesUsuario = rolesUsuario.iterator();

			// Creo instancia de cada uno de los registros RolesUsuario
			while (itRolesUsuario.hasNext()) {
				RolUsuarioIf rolUsuarioIf = (RolUsuarioIf) itRolesUsuario.next();
				
				//Creo una instancia del rol del usuario segun el id leido 
				RolIf rolIf = (RolIf) SessionServiceLocator.getRolSessionService().getRol(rolUsuarioIf.getRolId());
				//Añado el nodo  al list view de la derecha
				modelListRolesS.addElement(rolIf);				
				
				//Extraigo el codigo del rol para guardarlo en el mapa
				String codigoRol = rolIf.getCodigo();
				existeRolMapS.put(codigoRol,rolIf);
								
				//Retiro del List View de la izquierda y del Mapa el rol que fue leido de la base  
				modelListRolesT.removeElement(existeRolMapT.get(codigoRol));
				existeRolMapT.remove(codigoRol);	

			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void loadCombos(){
		//Veo si las fechas son validas, es decir si es que no es antes del dia de hoy
		UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
		getCmbTipoUsuario().removeAllItems();
		
		// Los desarrolladores para fines de pruebas y puesta
		// a punto del sistema son los únicos que teóricamente podrían crear un usuario tipo SUPERUSUARIO
		if (usuario.getTipousuario().equals(TIPO_DEVELOPER.substring(0,1))) {
			getCmbTipoUsuario().addItem(TIPO_DEVELOPER);
			getCmbTipoUsuario().addItem(TIPO_SUPER);
		}
		
		// El superusuario sólo puede crear nuevos usuarios tipo ADMINISTRADOR
		if (usuario.getTipousuario().equals(TIPO_SUPER.substring(0,1)) || usuario.getTipousuario().equals(TIPO_DEVELOPER.substring(0,1)))
			getCmbTipoUsuario().addItem(TIPO_ADMIN);
		
		// El administrador sólo puede crear nuevos usuarios tipo USUARIO
		if (usuario.getTipousuario().equals(TIPO_ADMIN.substring(0,1)) || usuario.getTipousuario().equals(TIPO_DEVELOPER.substring(0,1)))
			getCmbTipoUsuario().addItem(TIPO_USER);
		 
		if (getCmbTipoUsuario().getItemCount() > 0){
			getCmbTipoUsuario().setSelectedIndex(0);
			/*if (usuario.getTipousuario().equals(TIPO_DEVELOPER.substring(0,1))) {
				getCmbTipoUsuario().setSelectedItem(TIPO_DEVELOPER);
			}else if (usuario.getTipousuario().equals(TIPO_SUPER.substring(0,1))) {
				getCmbTipoUsuario().setSelectedItem(TIPO_SUPER);
			}else if (usuario.getTipousuario().equals(TIPO_ADMIN.substring(0,1))) {
				getCmbTipoUsuario().setSelectedItem(TIPO_ADMIN);
			}else if (usuario.getTipousuario().equals(TIPO_USER.substring(0,1))) {
				getCmbTipoUsuario().setSelectedItem(TIPO_USER);
			}*/	
		}
		
		if (getCmbTipoUsuario().getSelectedItem() != null) {
			if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_USER)) {
				EmpresaIf empresa = null;
				try {
					empresa = SessionServiceLocator.getEmpresaSessionService().getEmpresa(Parametros.getIdEmpresa());
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
				
				if ((empresa != null) && (getCmbEmpresa().getItemCount() == 0)) {
					getCmbEmpresa().addItem(empresa);
					if (getCmbEmpresa().getItemCount() > 0)
						getCmbEmpresa().setSelectedIndex(0);
				}
				
			} else if (getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_ADMIN) || getCmbTipoUsuario().getSelectedItem().toString().equals(TIPO_DEVELOPER)) {
				SpiritComboBoxModel cmbModel = new SpiritComboBoxModel(GeneralFinder.findEmpresas());
				getCmbEmpresa().setModel(cmbModel);
				if (getCmbEmpresa().getItemCount() > 0)
					getCmbEmpresa().setSelectedIndex(0);
			}
		}
	}
 
}
