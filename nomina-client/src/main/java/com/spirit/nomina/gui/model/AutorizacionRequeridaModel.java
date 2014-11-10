package com.spirit.nomina.gui.model;


import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.nomina.gui.panel.JDAutorizacionRequerida;


public class AutorizacionRequeridaModel extends JDAutorizacionRequerida{

	public boolean correctousr=false;
	 	String clave="";	 
	 	Long usuarioId;
	 	
	 	String username="";
	 	String password="";
	 	
	
	public AutorizacionRequeridaModel(Frame owner) {
		super(owner);

		getTxtClave().setEchoChar('*');
		initKeyListeners();
		setName("Credito Cliente Devoluciones");
		getTxtUsuario().setEnabled(true);
	 
 
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
		
		correctousr=false;
		if (!"".equals(getTxtUsuario().getText())) {
			//Veo si encontro el usuario en la base
				
				 correctousr=true;
				 
				 try {
					 String email=getTxtUsuario().getText()+"@creacional.com";
					
					 //no BORRAR...
					 Collection empleadoEmail =SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByEmailOficina(email);					
					if(empleadoEmail.size()!=0)
					{
						correctousr=true;
						 username=getTxtClave().getText();
						 password=getTxtUsuario().getText();						 
						 dispose();
						 
					}
					else{
						correctousr=false;
						username="";
						password="";
						SpiritAlert.createAlert("El email ingresado no existe.",SpiritAlert.INFORMATION);
					} 
					//VERIFICAR LA VALIDACION 
					 correctousr=true;
					 username=getTxtClave().getText();
					 password=getTxtUsuario().getText();						 
					 dispose();
					
					
				} catch (GenericBusinessException e) {
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

 





	public boolean isCorrectousr() {
		return correctousr;
	}




	public void setCorrectousr(boolean correctousr) {
		this.correctousr = correctousr;
	}




	public Long getUsuarioId() {
		return usuarioId;
	}




	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}



	
	
	

}
