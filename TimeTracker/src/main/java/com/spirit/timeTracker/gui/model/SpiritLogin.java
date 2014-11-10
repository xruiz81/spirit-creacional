package com.spirit.timeTracker.gui.model;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.utils.Lm;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.client.model.SwingWorker;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.model.CiudadModel;
import com.spirit.timeTracker.gui.base.ParametrosIniciales;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.Utilitarios;

public class SpiritLogin extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JScrollPane scrollPane;
	ImageIcon icon;
	private JPanel dialogPane;
	private JPanel contentPane;
	private JLabel lblUsuario;
	private TextField txtUsuario;
	private JLabel lblPassword;
	private TextField txtPassword;
	private JLabel lblEmpresa;
	private JComboBox cmbEmpresa;
	private JLabel lblOficina;
	private JComboBox cmbOficina;
	private JButton okButton;
	private JButton cancelButton;
	public static UsuarioIf _usuario;
	public static Long _idEmpresaUsuario,_idOficinaUsuario;
	public static EmpresaIf _empresa;
	public static OficinaIf _oficina;

	private Map empleadosMap = new HashMap();
	private static String ESTADO_ACTIVO = "A";
	
	public SpiritLogin() {
		super("Bienvenido");
		icon = SpiritResourceManager.getImageIcon("images/spirit_login.png");
		ImageIcon spirit_icon = SpiritResourceManager.getImageIcon("images/icons/spirit.png");
		setIconImage(spirit_icon.getImage());
		
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				//  Approach 1: Display image at at full size
				g.drawImage(icon.getImage(), 0, 0, null);
				//  Approach 2: Scale image to size of component
				// Dimension d = getSize();
				// g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				//  Approach 3: Fix the image position in the scroll pane
				// Point p = scrollPane.getViewport().getViewPosition();
				// g.drawImage(icon.getImage(), p.x, p.y, null);
				setOpaque( false );
				super.paintComponent(g);
			}
		};
		
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPane = new JPanel();
		lblUsuario = new JLabel();
		txtUsuario = new TextField();
		lblPassword = new JLabel();
		txtPassword = new TextField();
		txtPassword.setEchoChar('*');
		lblEmpresa = new JLabel();
		cmbEmpresa = new JComboBox();
		lblOficina = new JLabel();
		cmbOficina = new JComboBox();
		okButton = new JButton();
		cancelButton = new JButton();
		CellConstraints cc = new CellConstraints();
		
		//======== this ========
		Container contentPane2 = getContentPane();
		contentPane2.setLayout(new BorderLayout());
		
		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());
			
			//======== contentPane ========
			{
				contentPane.setLayout(new FormLayout(
						new ColumnSpec[] {
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec("160px"),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec("90px"),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec("100px"),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						},
						new RowSpec[] {
								new RowSpec("40px"),
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								new RowSpec("60px"),
								FormFactory.LINE_GAP_ROWSPEC
						}));
				
				//---- lblUsuario ----
				lblUsuario.setText("Usuario");
				contentPane.add(lblUsuario, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				contentPane.add(txtUsuario, cc.xywh(5, 3, 3, 1));
				
				//---- lblPassword ----
				lblPassword.setText("Contrase\u00f1a");
				contentPane.add(lblPassword, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				contentPane.add(txtPassword, cc.xywh(5, 5, 3, 1));
				
				//---- lblEmpresa ----
				lblEmpresa.setText("Empresa");
				contentPane.add(lblEmpresa, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				contentPane.add(cmbEmpresa, cc.xywh(5, 7, 3, 1));
				
				//---- lblOficina ----
				lblOficina.setText("Oficina");
				contentPane.add(lblOficina, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				contentPane.add(cmbOficina, cc.xywh(5, 9, 3, 1));
				
				//---- okButton ----
				okButton.setText("Aceptar");
				okButton.setIcon(SpiritResourceManager.getImageIcon("images/icons/dialog/aceptar_icon.png"));
				contentPane.add(okButton, cc.xy(5, 11));
				
				//---- cancelButton ----
				cancelButton.setText("Cancelar");
				cancelButton.setIcon(SpiritResourceManager.getImageIcon("images/icons/dialog/cancelar_icon.png"));
				contentPane.add(cancelButton, cc.xy(7, 11));
				contentPane.setOpaque(false);
			}
			dialogPane.add(contentPane, BorderLayout.CENTER);
			dialogPane.setOpaque(false);
		}
		panel.add(dialogPane, BorderLayout.CENTER);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		setSize(500,300);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
		
		//JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
		scrollPane = new JScrollPane(panel);
		setContentPane(scrollPane);
		scrollPane.setOpaque(false);
		
		// Seteo las teclas de escape para los botones
		txtUsuario.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					validar();
				}
			}
		}
		);
		txtUsuario.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		}
		);
		txtPassword.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					validar();
				}
			}
		}
		);
		txtPassword.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		}
		);
		cmbEmpresa.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					validar();
				}
			}
		}
		);
		cmbEmpresa.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		}
		);
		cmbOficina.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					validar();
				}
			}
		}
		);
		cmbOficina.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		}
		);
		
		//Cargo el combo de empresa
		List empresas=null;
		try {
			empresas = (List) SessionServiceLocator.getEmpresaSessionService().getEmpresaList();
			SpiritCache.setObject("empresas", empresas);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		SpiritComboBoxModel cmbModel = new SpiritComboBoxModel(empresas);
		cmbEmpresa.setModel(cmbModel);
		//Cargo el combo de oficina		
		cmbEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				SpiritComboBoxModel cmbModel = new SpiritComboBoxModel(
						findOficinasByEmpresa(((EmpresaIf) cmbEmpresa.getSelectedItem()).getId()));
				cmbOficina.setModel(cmbModel);
			}
		});
		
		//Valido si el usuario ingresado es valido
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validar();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		setCursor(SpiritCursor.normalCursor);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setSize(468,285);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		toFront();
		txtUsuario.getCursor();
		empleadosMap = mapearEmpleados();
	}
	
	private Map mapearEmpleados() {
		Map empleadosMap = new HashMap();
		
		try {
			Iterator empleadosIterator = SessionServiceLocator.getEmpleadoSessionService().getEmpleadoList().iterator();
			while (empleadosIterator.hasNext()) {
				EmpleadoIf empleado = (EmpleadoIf) empleadosIterator.next();
				empleadosMap.put(empleado.getId(), empleado);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return empleadosMap;
	}
	
	/*private void validar() {
		try {
			//Variables bandera para ver si los campos son validos
			Boolean usuarioValido = false;
			Boolean contraseñaValida = false;
			Boolean empresaValida = false;
			Boolean oficinaValida = false;
			//Bandera la cual se pone en false cuando algun elemento de la ventana no ha sido seteado
			Boolean bandera = true;
			ParametrosIniciales.modulosUsuarioByRoles.clear();
			ParametrosIniciales.menusUsuarioByRoles.clear();
			ParametrosIniciales._SubMenusUsuarioByRoles.clear();
			ParametrosIniciales.funcionesSubMenuByRoles.clear();
			
			//Veo que haya sido ingresado un usuario
			if (!"".equals(txtUsuario.getText())) {
				//Veo si encontro el usuario en la base
				Collection usuarios = getUsuarioSessionService().findUsuarioByUsuario(txtUsuario.getText());
				if (usuarios.size() != 0) {
					Iterator usuariosIterator = usuarios.iterator();
					while (usuariosIterator.hasNext()) {
						// Veo que haya sido ingresada la contraseña
						if (!"".equals(txtPassword.getText())) {
							//Si es asi, lo seteo en la variavble _usuario
							_usuario = (UsuarioIf) usuariosIterator.next();
							Parametros.setUsuarioIf(_usuario);
							//Veo si la contraseña ingresada es correcta
							try {
								if (_usuario.getClave().equals(Utilitarios.encrypt(txtPassword.getText()))) {
									//Si el usuario no es tipo SUPER veo si pertenece a la empresa que escogio
									if (!"S".equals(_usuario.getTipousuario())) {// && !"DEVEL".equals(_usuario.getTipousuario())) {
										//Si el usuario existe en la base, verifico que se encuentre en la empresa que el selecciono del combo
										if(cmbEmpresa.getSelectedItem() != null) {
											//Comparo si la empresa seleccionada en el combo es la misma del empleado
											if (_usuario.getEmpresaId().compareTo(((EmpresaIf) cmbEmpresa.getSelectedItem()).getId()) == 0) {
												//Almaceno el id de la Empresa del usuario logoneado
												_idEmpresaUsuario = _usuario.getEmpresaId();
												//Mano a cargar al empleado de la tabla empleado segun el idEmpleado de la tabla usuario
												//EmpleadoIf empleado = EmpleadoModel.getEmpleadoSessionService().getEmpleado(_usuario.getEmpleadoId());
												//Si el usuario no es tipo ADMIN veo si ha escogido una oficina
												if (!"A".equals(_usuario.getTipousuario())) {
													//Veo que el usuario
													if (cmbOficina.getSelectedItem() != null) {
														//Almaceno el id de la Oficina del usuario logoneado
														_idOficinaUsuario = ((OficinaIf) cmbOficina.getSelectedItem()).getId();
														EmpleadoIf empleadoUsuario = getEmpleadoSessionService().getEmpleado(_usuario.getEmpleadoId());
														if (empleadoUsuario.getOficinaId().compareTo(_idOficinaUsuario) == 0) {
																														
															OficinaIf oficina = (OficinaIf) cmbOficina.getSelectedItem();
															Parametros.setOficina(oficina);
															Parametros.setIdOficina(_idOficinaUsuario);
															CiudadIf ciudad = getCiudadSessionService().getCiudad(oficina.getCiudadId());
															Parametros.setCiudad(ciudad);
															
															inicializarTimeTracker();
															usuarioValido = true;
															empresaValida = true;
															oficinaValida = true;
														} else
															oficinaValida = false;
														empresaValida = true;
													} else {
														SpiritAlert.createAlert("Seleccione la oficina donde trabaja por favor", SpiritAlert.INFORMATION);
														bandera = false;
														this.cmbOficina.requestFocusInWindow();
													}
												} else {
													inicializarTimeTracker();
													usuarioValido = true;
													empresaValida = true;
													oficinaValida = true;
												}
											} else
												empresaValida = false;
										} else {
											SpiritAlert.createAlert("Seleccione la empresa a la que pertenece por favor", SpiritAlert.INFORMATION);
											bandera = false;
											this.cmbEmpresa.requestFocusInWindow();
											break;
										}
									} else {
										inicializarTimeTracker();
										usuarioValido = true;
										contraseñaValida = true;
										empresaValida = true;
										oficinaValida = true;
										break;
									}
									usuarioValido = true;
									contraseñaValida = true;
									if (empresaValida)
										break;
								} else
									contraseñaValida = false;
							} catch (NoSuchAlgorithmException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							usuarioValido = true;
						} else {
							SpiritAlert.createAlert("Ingrese su contraseña por favor", SpiritAlert.INFORMATION);
							bandera = false;
							this.txtPassword.requestFocusInWindow();
							break;
						}
					}
				} else
					usuarioValido = false;
			} else {
				SpiritAlert.createAlert("Ingrese su usuario por favor", SpiritAlert.INFORMATION);
				bandera = false;
				this.txtUsuario.requestFocusInWindow();
			}
			
			if (bandera) {
				//Valido las variables bandera que use para ver si los campos son correctos
				if (usuarioValido) {
					if (contraseñaValida) {
						if (empresaValida) {
							if (!oficinaValida) {
								SpiritAlert.createAlert("Usuario no pertenece a Oficina seleccionada", SpiritAlert.INFORMATION);
								this.cmbOficina.requestFocusInWindow();
							}
						} else {
							SpiritAlert.createAlert("Usuario no pertenece a Empresa seleccionada", SpiritAlert.INFORMATION);
							this.cmbEmpresa.requestFocusInWindow();
						}
					} else {
						SpiritAlert.createAlert("Contraseña Inválida", SpiritAlert.INFORMATION);
						this.txtPassword.requestFocusInWindow();
					}
				} else {
					SpiritAlert.createAlert("Usuario no existe", SpiritAlert.INFORMATION);
					this.txtUsuario.requestFocusInWindow();
				}
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}*/
	
	private void validar() {
		try {
			//Variables bandera para ver si los campos son validos
			Boolean usuarioValido = false;
			Boolean contraseñaValida = false;
			Boolean empresaValida = false;
			Boolean oficinaValida = false;
			Boolean usuarioActivo = false;
			//Bandera la cual se pone en false cuando algun elemento de la ventana no ha sido seteado
			Boolean bandera = true;
			ParametrosIniciales.modulosUsuarioByRoles.clear();
			ParametrosIniciales.menusUsuarioByRoles.clear();
			ParametrosIniciales._SubMenusUsuarioByRoles.clear();
			ParametrosIniciales.funcionesSubMenuByRoles.clear();
			
			//Veo que haya sido ingresado un usuario
			if (!"".equals(txtUsuario.getText())) {
				//Veo si encontro el usuario en la base
				//Collection usuarios = getUsuarioSessionService().findUsuarioAndEmpleadoAndRolesByUsuario(txtUsuario.getText());
				Collection usuarios = SessionServiceLocator.getUsuarioSessionService().findUsuarioAndRolesByUsuario(txtUsuario.getText());
				if (usuarios.size() != 0) {
					Iterator usuariosIterator = usuarios.iterator();
					while (usuariosIterator.hasNext()) {
						// Veo que haya sido ingresada la contraseña
						if (!"".equals(txtPassword.getText())) {
							//Si es asi, lo seteo en la variavble _usuario
							Object[] o = (Object[]) usuariosIterator.next();
							_usuario = (UsuarioIf) o[0];
							Parametros.setUsuarioIf(_usuario);
							Parametros.setUsuario(_usuario.getUsuario());
							
							String tipoUsuarioTimeTracker = _usuario.getTipousuarioTimetracker();
							if ( tipoUsuarioTimeTracker == null || "".equals(tipoUsuarioTimeTracker) ){
								SpiritAlert.createAlert("Usuario no tiene definido tipo de Usuario para TimeTracker !!", SpiritAlert.INFORMATION);
								return;
							}
							
							//Veo si la contraseña ingresada es correcta
							try {
								if (_usuario.getClave().equals(Utilitarios.encrypt(txtPassword.getText(), "SHA"))) {
									//Si el usuario no es tipo SUPER veo si pertenece a la empresa que escogio
									if (!"S".equals(_usuario.getTipousuario())) {// && !"DEVEL".equals(_usuario.getTipousuario())) {
										//Si el usuario existe en la base, verifico que se encuentre en la empresa que el selecciono del combo
										if(cmbEmpresa.getSelectedItem() != null) {
											_empresa = (EmpresaIf) cmbEmpresa.getSelectedItem();
											Parametros.setEmpresa(_empresa);
											Parametros.setIdEmpresa(_empresa.getId());
											//Comparo si la empresa seleccionada en el combo es la misma del empleado
											if (_usuario.getEmpresaId().compareTo(((EmpresaIf) cmbEmpresa.getSelectedItem()).getId()) == 0) {
												//Almaceno el id de la Empresa del usuario logoneado
												_idEmpresaUsuario = _usuario.getEmpresaId();
												//Mano a cargar al empleado de la tabla empleado segun el idEmpleado de la tabla usuario
												//EmpleadoIf empleado = EmpleadoModel.getEmpleadoSessionService().getEmpleado(_usuario.getEmpleadoId());
												//Si el usuario no es tipo ADMIN veo si ha escogido una oficina
												if (!"A".equals(_usuario.getTipousuario())) {
													//Veo que el usuario
													if (cmbOficina.getSelectedItem() != null) {
														_oficina = (OficinaIf) cmbOficina.getSelectedItem();
														//Almaceno el id de la Oficina del usuario logoneado
														_idOficinaUsuario = ((OficinaIf) cmbOficina.getSelectedItem()).getId();
														//EmpleadoIf empleadoUsuario = getEmpleadoSessionService().getEmpleado(_usuario.getEmpleadoId());
														EmpleadoIf empleadoUsuario = (EmpleadoIf) empleadosMap.get(_usuario.getEmpleadoId());
														if (empleadoUsuario.getEstado().equals(ESTADO_ACTIVO)) {
															if (empleadoUsuario.getOficinaId().compareTo(_idOficinaUsuario) == 0) {
																OficinaIf oficina = (OficinaIf) cmbOficina.getSelectedItem();
																Parametros.setOficina(oficina);
																Parametros.setIdOficina(_idOficinaUsuario);
																CiudadIf ciudad = CiudadModel.getCiudadSessionService().getCiudad(oficina.getCiudadId());
																Parametros.setCiudad(ciudad);
																inicializarTimeTracker(usuarios);
																usuarioValido = true;
																empresaValida = true;
																oficinaValida = true;
															} else
																oficinaValida = false;
															usuarioActivo = true;
														} else {
															oficinaValida = true;
															usuarioActivo = false;
														}
														empresaValida = true;
													} else {
														SpiritAlert.createAlert("Seleccione la oficina donde trabaja por favor", SpiritAlert.INFORMATION);
														bandera = false;
														this.cmbOficina.requestFocusInWindow();
													}
												} else {
													inicializarTimeTracker(usuarios);
													usuarioValido = true;
													empresaValida = true;
													oficinaValida = true;
													usuarioActivo = true;
												}
											} else
												empresaValida = false;
										} else {
											SpiritAlert.createAlert("Seleccione la empresa a la que pertenece por favor", SpiritAlert.INFORMATION);
											bandera = false;
											this.cmbEmpresa.requestFocusInWindow();
											break;
										}
									} else {
										inicializarTimeTracker(usuarios);
										usuarioValido = true;
										contraseñaValida = true;
										empresaValida = true;
										oficinaValida = true;
										usuarioActivo = true;
										break;
									}
									usuarioValido = true;
									contraseñaValida = true;
									if (empresaValida)
										break;
								} else
									contraseñaValida = false;
							} catch (NoSuchAlgorithmException e) {
								SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
								e.printStackTrace();
							} catch (UnsupportedEncodingException e) {
								SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
								e.printStackTrace();
							}
							usuarioValido = true;
						} else {
							SpiritAlert.createAlert("Ingrese su contraseña por favor", SpiritAlert.INFORMATION);
							bandera = false;
							this.txtPassword.requestFocusInWindow();
							break;
						}
					}
				} else
					usuarioValido = false;
			} else {
				SpiritAlert.createAlert("Ingrese su usuario por favor", SpiritAlert.INFORMATION);
				bandera = false;
				this.txtUsuario.requestFocusInWindow();
			}
			
			if (bandera) {
				//Valido las variables bandera que use para ver si los campos son correctos
				if (usuarioValido) {
					if (contraseñaValida) {
						if (empresaValida) {
							if (oficinaValida) {
								if (!usuarioActivo) {
									SpiritAlert.createAlert("Usuario no se encuentra activo. Verifique estado del empleado", SpiritAlert.INFORMATION);
									this.txtUsuario.requestFocusInWindow();
								}
							} else {
								SpiritAlert.createAlert("Usuario no pertenece a Oficina seleccionada", SpiritAlert.INFORMATION);
								this.cmbOficina.requestFocusInWindow();
							}
						} else {
							SpiritAlert.createAlert("Usuario no pertenece a Empresa seleccionada", SpiritAlert.INFORMATION);
							this.cmbEmpresa.requestFocusInWindow();
						}
					} else {
						SpiritAlert.createAlert("Contraseña Inválida", SpiritAlert.INFORMATION);
						this.txtPassword.requestFocusInWindow();
						this.txtPassword.setSelectionStart(0);
						this.txtPassword.setSelectionEnd(this.txtPassword.getText().length());
					}
				} else {
					SpiritAlert.createAlert("Usuario no existe", SpiritAlert.INFORMATION);
					this.txtUsuario.requestFocusInWindow();
					this.txtUsuario.setSelectionStart(0);
					this.txtUsuario.setSelectionEnd(this.txtUsuario.getText().length());
				}
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void inicializarTimeTracker(final Collection usuarios) {
		this.dispose();
		/*java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new com.spirit.timeTracker.gui.model.ParametrosIniciales();
				TimeTracker timeTracker = new TimeTracker();
            	Lm.setParent(timeTracker);
            	Utiles.timeTrackerVentana = timeTracker;
            	timeTracker.setVisible(true);
            }
        });*/
		new SwingWorker(){
			public Object construct() {
				setCursor(SpiritCursor.hourglassCursor);
				new ParametrosIniciales();
				ParametrosIniciales.cargarModulos_Menus_SubMenusByRoles(usuarios);
				//MainFrame.inicializa();
				//MainFrameModel.get_frame().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
				setCursor(SpiritCursor.normalCursor);
				dispose();
				
				TimeTracker timeTracker = new TimeTracker();
            	Lm.setParent(timeTracker);
            	Utiles.timeTrackerVentana = timeTracker;
            	Parametros.setMainFrame(timeTracker);
            	timeTracker.setVisible(true);
        		return null;
			};
		};
	}
	
	
	public TextField getTxtUsuario() {
		return txtUsuario;
	}
	
	public void setTxtUsuario(TextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}
	
	public static List findOficinasByEmpresa(Long idEmpresa) {
		List oficinasByEmpresas;
		oficinasByEmpresas = SpiritCache.findObject("oficinasByEmpresas");

		if (oficinasByEmpresas == null) {
			try {
				oficinasByEmpresas = (List) SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(idEmpresa);
				SpiritCache.setObject("oficinas", oficinasByEmpresas);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return oficinasByEmpresas;
		}
		return oficinasByEmpresas;
	}	
}

