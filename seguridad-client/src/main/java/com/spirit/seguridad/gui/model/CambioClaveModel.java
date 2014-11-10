package com.spirit.seguridad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.general.session.EmpleadoSessionService;
import com.spirit.general.session.UsuarioSessionService;
import com.spirit.seguridad.gui.panel.JPCambioClave;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.MyKeyListenerLowerCase;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class CambioClaveModel extends JPCambioClave {

	private static final long serialVersionUID = 8307114561914703670L;

	private static final int MAX_LONGITUD_CLAVE = 10;
	private JDPopupFinderModel popupFinder;
	private EmpleadoCriteria empleadoCriteria;
	private EmpleadoIf empleado;
	private UsuarioIf usuario;
	
	public CambioClaveModel() {
		initListeners();
		initKeyListeners();
		this.showUpdateMode();
		new HotKeyComponent(this);
	}
	
	private void initListeners() {
		//Manejo los eventos de Buscar empleado
		getBtnBuscarEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idEmpresa = Parametros.getIdEmpresa();
				empleadoCriteria = new EmpleadoCriteria("Empleados",idEmpresa);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),empleadoCriteria,JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ) {
					empleado = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					
					clean();
										
					try {
						Collection usuarios  = SessionServiceLocator.getUsuarioSessionService().findUsuarioByEmpleadoId(empleado.getId());
						
						Iterator itusuarios = usuarios.iterator();
						while (itusuarios.hasNext()) {
							UsuarioIf usuarioIf = (UsuarioIf) itusuarios.next();
							if(usuarioIf.getEmpleadoId().compareTo(empleado.getId())==0)
								usuario = usuarioIf;
						}
						
						if (empleado != null && usuario != null) {
							getTxtEmpleado().setText(empleado.getNombres() + " - " + empleado.getApellidos());
        					getTxtUsuario().setText(usuario.getUsuario());        					
						}
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	private void initKeyListeners() {
		getTxtClaveAnterior().addKeyListener(new TextChecker(MAX_LONGITUD_CLAVE));
		getTxtClaveAnterior().addKeyListener(new MyKeyListenerLowerCase());
		getTxtClaveNueva().addKeyListener(new TextChecker(MAX_LONGITUD_CLAVE));
		getTxtClaveNueva().addKeyListener(new MyKeyListenerLowerCase());
		getTxtConfirmarClave().addKeyListener(new TextChecker(MAX_LONGITUD_CLAVE));
		getTxtConfirmarClave().addKeyListener(new MyKeyListenerLowerCase());
	}

	public void save() {
		// TODO Auto-generated method stub
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public void refresh() {
		// TODO Auto-generated method stub
		
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

	public void update() {
		if(validateFields()) {
			//if(this.getTxtClaveNueva().getText().equals(this.getTxtConfirmarClave().getText()) && Utilitarios.encrypt(this.getTxtClaveAnterior().getText()).equals(usuario.getClave())){
				try {
					usuario.setClave(Utilitarios.encrypt(this.getTxtClaveNueva().getText(), "SHA"));
					SessionServiceLocator.getUsuarioSessionService().saveUsuario(usuario);
					SpiritAlert.createAlert("Clave actualizada con éxito", SpiritAlert.INFORMATION);
					clean();
					showUpdateMode();
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
				
			//}
		}
	}

	public void find() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void clean() {
		getTxtEmpleado().setText("");
		getTxtUsuario().setText("");
		getTxtClaveAnterior().setText("");
		getTxtClaveNueva().setText("");
		getTxtConfirmarClave().setText("");
	}

	public void showFindMode() {
		showUpdateMode();
	}

	public void showSaveMode() {
		showUpdateMode();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtEmpleado().setEnabled(true);
		getTxtEmpleado().setEditable(false);
		if (((UsuarioIf) Parametros.getUsuarioIf()).getTipousuario().equals("A"))
			getBtnBuscarEmpleado().setEnabled(true);
		else
			getBtnBuscarEmpleado().setEnabled(false);
		getTxtUsuario().setEnabled(false);
		getTxtUsuario().setEditable(false);
		getTxtClaveAnterior().setEnabled(true);
		getTxtClaveNueva().setEnabled(true);
		getTxtConfirmarClave().setEnabled(true);
		
		//seteo los datos con el id del empleado con que se logonea
		try {	
			if (((UsuarioIf) Parametros.getUsuarioIf()).getTipousuario().equals("U")) {
				empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(((UsuarioIf)Parametros.getUsuarioIf()).getEmpleadoId());
				getTxtEmpleado().setText(empleado.getNombres() + " " + empleado.getApellidos());
			}
			
			usuario = (UsuarioIf) Parametros.getUsuarioIf();
			getTxtUsuario().setText(usuario.getUsuario());
				getTxtClaveNueva().addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e) {
					}

					public void mousePressed(MouseEvent e) {
						try {
							if(Utilitarios.encrypt(getTxtClaveAnterior().getText(), "SHA").equals(usuario.getClave())){
								getTxtClaveNueva().setEnabled(true);
							}
							else if(!Utilitarios.encrypt(getTxtClaveAnterior().getText(), "SHA").equals(usuario.getClave()) && !("".equals(getTxtClaveAnterior().getText()))){
								SpiritAlert.createAlert("La clave anterior no es la correcta!", SpiritAlert.WARNING);
								getTxtClaveAnterior().setText("");
								getTxtClaveNueva().setText("");
								getTxtClaveAnterior().grabFocus();
							}
						} catch (NoSuchAlgorithmException e1) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e1.printStackTrace();
						} catch (UnsupportedEncodingException e1) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e1.printStackTrace();
						}
					}
					
				});
				
				getTxtClaveAnterior().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evento) {
						getTxtClaveAnterior().addKeyListener(new KeyAdapter() {
							public void keyTyped(KeyEvent evt) {
								char keyChar = evt.getKeyChar();
								if(keyChar == KeyEvent.VK_ENTER){
									try {
										if(Utilitarios.encrypt(getTxtClaveAnterior().getText(), "SHA").equals(usuario.getClave())) {
											getTxtClaveNueva().setEnabled(true);
											getTxtClaveNueva().grabFocus();
										} else if(!Utilitarios.encrypt(getTxtClaveAnterior().getText(), "SHA").equals(usuario.getClave()) && !("".equals(getTxtClaveAnterior().getText()))){
											SpiritAlert.createAlert("La clave anterior no es la correcta!", SpiritAlert.WARNING);
											getTxtClaveAnterior().setText("");
											getTxtClaveNueva().setText("");
											getTxtClaveAnterior().grabFocus();
										}
									} catch (NoSuchAlgorithmException e) {
										SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
										e.printStackTrace();
									} catch (UnsupportedEncodingException e) {
										SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
										e.printStackTrace();
									}
								}
							}
							});
					}
				});
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
		
		getTxtClaveAnterior().grabFocus();
	}
	
    //Valido que todos los campos hayan sido llenados
	public boolean validateFields() {
		//UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
		
		if (usuario.getTipousuario().equals("U") && ((("".equals(getTxtEmpleado().getText())) || (getTxtEmpleado().getText() == null)))) {
			SpiritAlert.createAlert("Debe existir el nombre del Empleado !!", SpiritAlert.WARNING);
			getTxtUsuario().grabFocus();
			return false;
		}
		
		if ((("".equals(getTxtUsuario().getText())) || (getTxtUsuario().getText() == null))) {
			SpiritAlert.createAlert("Debe existir el Usuario !!", SpiritAlert.WARNING);
			getTxtEmpleado().grabFocus();
			return false;
		}
		
		if (!(((UsuarioIf) Parametros.getUsuarioIf()).getTipousuario().equals("A"))) {
			if ((("".equals(getTxtClaveAnterior().getText())) || (getTxtClaveAnterior().getText() == null))) {
				SpiritAlert.createAlert("Debe ingresar la Clave Anterior !!", SpiritAlert.WARNING);
				getTxtClaveAnterior().grabFocus();
				return false;
			}
		}
		
		if ((("".equals(getTxtClaveNueva().getText())) || (getTxtClaveNueva().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar la Clave Nueva !!", SpiritAlert.WARNING);
			getTxtClaveNueva().grabFocus();
			return false;
		}
		
		if ((("".equals(getTxtConfirmarClave().getText())) || (getTxtConfirmarClave().getText() == null))) {
			SpiritAlert.createAlert("Debe Confirmar la Clave !!", SpiritAlert.WARNING);
			getTxtConfirmarClave().grabFocus();
			return false;
		}
		
		if (!(((UsuarioIf) Parametros.getUsuarioIf()).getTipousuario().equals("A"))) {
			try {
				if(!Utilitarios.encrypt(this.getTxtClaveAnterior().getText(), "SHA").equals(usuario.getClave())) {
					SpiritAlert.createAlert("La clave anterior no es la correcta!", SpiritAlert.WARNING);
					return false;
				}
			} catch (NoSuchAlgorithmException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
		
		if(!this.getTxtClaveNueva().getText().equals(this.getTxtConfirmarClave().getText())){
			SpiritAlert.createAlert(" La nueva Clave y la Confirmación no coinciden!", SpiritAlert.WARNING);
			return false;
		}
				
		return true;
	}
 
}
