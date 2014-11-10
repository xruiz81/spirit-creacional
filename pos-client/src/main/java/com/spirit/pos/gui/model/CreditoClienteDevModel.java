package com.spirit.pos.gui.model;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.pos.gui.panel.JDCreditoClienteDEV;
import com.spirit.util.Utilitarios;


public class CreditoClienteDevModel extends JDCreditoClienteDEV{

	public boolean aplicarcreditodev=false;
	 	String clave="";
	
	public CreditoClienteDevModel(Frame owner, String total,String datoscliente) {
		super(owner);

		getTxtClave().setEchoChar('*');
		initKeyListeners();
		setName("Credito Cliente Devoluciones");
		getTxtUsuario().setEnabled(true);
		getTxtValorAplicar().setText(total);

		getTxtDatosCliente().setText(datoscliente); 
		
		addWindowListener(new WindowAdapter() {
			public void windowOpened( WindowEvent e ){
				getTxtUsuario().requestFocus();
			}
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});		
	}




	public void initKeyListeners(){

		getBtnAceptar().addActionListener(oActionListenerBotonAceptar);	
		getBtnCancelar().addActionListener(oActionListenerBotonCancelar);	
		getTxtUsuario().addKeyListener(oKeyAdapterUsuario);
		getTxtClave().addKeyListener(oKeyAdapterUsuario);
	}
	
	ActionListener oActionListenerBotonAceptar= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			validar();
			 
			 
		}
	};
	
	
	ActionListener oActionListenerBotonCancelar= new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	};
	
	KeyListener oKeyAdapterUsuario = new KeyAdapter() {	
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ENTER) {
				validar();
			}
		}
	};
	 
	public void validar(){
		
		aplicarcreditodev=false;
		if (!"".equals(getTxtUsuario().getText())) {
			//Veo si encontro el usuario en la base
			try {
			Collection usuarios = SessionServiceLocator.getUsuarioSessionService().findUsuarioAndEmpleadoAndRolesByUsuario(getTxtUsuario().getText());
			if (usuarios.size() != 0) {
				
				System.out.println("usuarios!!!!***>>"+usuarios.size());
				Iterator usuariosIterator = usuarios.iterator();
				while (usuariosIterator.hasNext()) {
					// Veo que haya sido ingresada la contraseña
					if (!"".equals(getTxtClave().getText())) {
						//Si es asi, lo seteo en la variable _usuario
						Object[] o = (Object[]) usuariosIterator.next();
						UsuarioIf usuario = (UsuarioIf) o[0];

						//validamos si el usuario es empleado de tipo supervisor
						if(validar_empleado(usuario.getEmpleadoId()))
						{
						//Veo si la contraseña ingresada es correcta
								if (usuario.getClave().equals(Utilitarios.encrypt(getTxtClave().getText(), "SHA"))) {
									aplicarcreditodev=true;
									dispose();
									
								}
								else{
									SpiritAlert.createAlert("La clave ingresada no es la correcta.",SpiritAlert.INFORMATION);
									getTxtUsuario().setText("");
									getTxtClave().setText("");
									getTxtUsuario().grabFocus();
									
									break;
								}
						}
						else{
							SpiritAlert.createAlert("El usuario no es de tipo SUPERVISOR",SpiritAlert.INFORMATION);
							getTxtUsuario().setText("");
							getTxtClave().setText("");
							getTxtUsuario().grabFocus();
							break;
						}
						}
					else{
						SpiritAlert.createAlert("Debe ingresar la contraseña",SpiritAlert.INFORMATION);
						getTxtClave().setText("");
						getTxtClave().requestFocus();
						break;
					}
							
					}
				}
			else{
				SpiritAlert.createAlert("No existe dicho USUARIO",SpiritAlert.INFORMATION);
				getTxtUsuario().setText("");
				getTxtClave().setText("");
				getTxtUsuario().grabFocus();
			}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			SpiritAlert.createAlert("Debe ingresar datos de usuario y clave",SpiritAlert.INFORMATION);
		}
		
	}


	public boolean validar_empleado(Long empleadoId){
		boolean tiposupervisor=false;
		try {

			HashMap<String, Object> mapa = new HashMap<String, Object>();
			mapa.clear();
			mapa.put("id" , empleadoId );			
			Iterator vendedoresIt;
			vendedoresIt = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByQuery(mapa).iterator();

			if (vendedoresIt.hasNext()) 
			{
				EmpleadoIf empleado = (EmpleadoIf) vendedoresIt.next();				
				Long tipoempleado=empleado.getTipoempleadoId();
				if(getIdTIPOempleado_supervisor("SUP").equals(tipoempleado))
					tiposupervisor=true;
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tiposupervisor;
	}
	
	
	private Long getIdTIPOempleado_supervisor(String codigo){		 
		Long idtipoempleado=0L;		
		TipoEmpleadoIf tipoempleadoIf=null;
		Iterator it2;		  
		try {
			it2 = SessionServiceLocator.getTipoEmpleadoSessionService().findTipoEmpleadoByCodigo(codigo).iterator();
			tipoempleadoIf = (it2.hasNext())?(TipoEmpleadoIf) it2.next():null;
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(tipoempleadoIf!=null)
			idtipoempleado=tipoempleadoIf.getId();	
		
		return(idtipoempleado);
	} 

	 

	public boolean isAplicarcreditodev() {
		return aplicarcreditodev;
	}




	public void setAplicarcreditodev(boolean aplicarcreditodev) {
		this.aplicarcreditodev = aplicarcreditodev;
	}

}
