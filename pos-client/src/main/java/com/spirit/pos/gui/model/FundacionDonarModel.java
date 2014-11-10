package com.spirit.pos.gui.model;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.pos.gui.panel.JDElegirFundacion;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.SpiritComboBoxModel;

public class FundacionDonarModel extends JDElegirFundacion{

	private boolean aceptar=false;
	
	private String idFundacion=null;
	
	
	public FundacionDonarModel(Frame owner,String idFundacion) {
		super(owner);	 
		
		/*if(idFundacion==null)	{
			idFundacion="";
			this.idFundacion=idFundacion;
		}*/
		this.idFundacion=idFundacion;
	 
		iniciarcomp();	
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});		
	}

	public void llenar_combo(){
		SpiritComboBoxModel cmbModelTipoCliente;
		try {

			TipoClienteIf tipoClienteIf; 
			Collection<TipoClienteIf> tipoclientes  = SessionServiceLocator.getTipoClienteSessionService().findTipoClienteByCodigo("FU");			 
			List fundaciones = null;				 
			if ( tipoclientes.size() == 1 ) 
			{
				tipoClienteIf = tipoclientes.iterator().next();					
				cmbModelTipoCliente = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getClienteSessionService().findClienteByTipoclienteId(tipoClienteIf.getId()));
				getCmbFundaciones().setModel(cmbModelTipoCliente);
			}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (GenericBusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	 

	private void iniciarcomp(){	
		getOkButton().addActionListener(oActionListenerBotonAceptar);		
		getCancelButton().addActionListener(oActionListenerBotonCancelar);			
		getCmbFundaciones().addKeyListener(onKeyFundacion);
		
		llenar_combo();
		
		 
		if(this.idFundacion!=null)	
		{
			getCmbFundaciones().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbFundaciones(),new Long(idFundacion)));
		
		}
			
		
	}

	
	
	 KeyListener onKeyFundacion = new KeyAdapter() {		 
		//si da tab o enter, el foco pase a descuento
		public void keyTyped(KeyEvent e) {//
			if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB) {				
				//getTxtDescuento().grabFocus();
				setear_datos();			 
				dispose(); 
				aceptar=true;
				}				 
			}
	 
	}; 

	
	
	
	ActionListener oActionListenerBotonAceptar= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			setear_datos();
			
			dispose(); 
			aceptar=true;
			 
		}
	};
	
	ActionListener oActionListenerBotonCancelar= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			aceptar=false;			
			dispose(); 
		}		
	};
	
	public void setear_datos() {	
		int selec= getCmbFundaciones().getSelectedIndex();		
				
		if(selec==-1)			
			idFundacion=null;
		else		 
			idFundacion=((ClienteIf)getCmbFundaciones().getSelectedItem()).getId().toString();
		
	}	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public boolean isAceptar() {
		return aceptar;
	}

	public void setAceptar(boolean aceptar) {
		this.aceptar = aceptar;
	}

	public String getIdfundacion() {
		return idFundacion;
	}

	public void setIdfundacion(String idfundacion) {
		this.idFundacion = idfundacion;
	}
	
	
 
}
