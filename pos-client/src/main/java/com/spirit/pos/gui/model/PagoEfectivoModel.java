package com.spirit.pos.gui.model;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.gui.panel.JDPagoEfectivo;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class PagoEfectivoModel extends JDPagoEfectivo{


	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	BigDecimal total_pagar= new BigDecimal("0.00");
	BigDecimal total_pagado= new BigDecimal("0.00");
	
	BigDecimal balance= new BigDecimal("0.00");
	
	Long cajaAbiertaID=new Long("0"); 
	private boolean aceptar=false;
 
	
	BigDecimal cambio= new BigDecimal("0.00");

	public PagoEfectivoModel(Frame owner, String total) {
		super(owner);
		this.total_pagar=new BigDecimal(total);

		 
		iniciarcomp();
		initKeyListeners();
		setName("Pago Efectivo");
		
		addWindowListener(new WindowAdapter() {
			 public void windowOpened( WindowEvent e ){
				 getTxtMonto().requestFocus();
		      }
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});		
	}
 
	private void iniciarcomp(){		
	 	
		
		getTxtMonto().setText(formatoDecimal.format(total_pagar));	 
		getTxtDeuda().setText(formatoDecimal.format(total_pagar));
		getTxtBalance().setText("0.00");
		
		getBtnAceptar().addActionListener(oActionListenerBotonAceptar);		
		getBtnCancelar().addActionListener(oActionListenerBotonCancelar);	
		Caja_abierta_id();
	}



	public void initKeyListeners(){		 
		getTxtMonto().addKeyListener(new TextChecker(9));
		getTxtMonto().addKeyListener(new NumberTextFieldDecimal()); 
		getTxtMonto().addKeyListener(oKeyAdapterVuelto);		
		System.out.println("aki en efectivo");
		getTxtMonto().setNextFocusableComponent(getBtnAceptar());		
	}

	KeyListener oKeyAdapterVuelto = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			BigDecimal deuda=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));
			BigDecimal totalrecibido=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));
		 	BigDecimal vuelto=totalrecibido.subtract(deuda);
			BigDecimal balance=deuda.subtract(totalrecibido);
			//totalrecibio > deuda entonces no debo nada balance=0.00
			if(totalrecibido.compareTo(deuda)==1)
				balance=new BigDecimal("0.00");
			
			if(vuelto.signum()==-1)
				vuelto=new BigDecimal("0.00");
		
			getTxtBalance().setText(formatoDecimal.format(balance));
			getTxtCambio().setText(formatoDecimal.format(vuelto));
			
		
		}
		
		public void keyTyped(KeyEvent e) {	
			if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB) {
				aceptar();
				aceptar=true;
			}  
		}
	};

	public void Caja_abierta_id(){
		Long caja_id=new Long("0");

		Long usuario_id=((UsuarioIf)Parametros.getUsuarioIf()).getId();		
		Map aMap = new HashMap();
		aMap.clear();
		aMap.put("usuarioId", usuario_id);
		aMap.put("estado", "A");
		aMap.put("fechafin",null);
		
		cajaAbiertaID=new Long("0");
		Iterator cajavalorIt;
		try {
			cajavalorIt = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();
			if (cajavalorIt.hasNext()) {
				CajaSesionIf valor_actual = (CajaSesionIf) cajavalorIt.next();
				BigDecimal valor=valor_actual.getValorInicial();				 	 
				caja_id=valor_actual.getId();
		 
				 
			}
			else
				{
				SpiritAlert.createAlert("Debe tener una caja abierta",SpiritAlert.INFORMATION);
				aceptar=false;
				dispose(); 				
				
				}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cajaAbiertaID=caja_id;

	}




	ActionListener oActionListenerBotonAceptar= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			aceptar();
			aceptar=true;
			 
		}
	};
	
	ActionListener oActionListenerBotonCancelar= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			aceptar=false;
			dispose(); 
		}
	};
	
	public void aceptar() {		
		String valorpagado=Utilitarios.removeDecimalFormat(getTxtMonto().getText());		
		
		String deuda=Utilitarios.removeDecimalFormat(getTxtBalance().getText()).trim();		 
	 
		
		String total_pagar=Utilitarios.removeDecimalFormat(getTxtDeuda().getText());

		
		if(deuda.equals("0.00"))
			{
			
			setTotal_pagado(new BigDecimal(total_pagar));
			
					 
			BigDecimal deuda2=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));
			BigDecimal monto=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));
			BigDecimal balance=deuda2.subtract(monto);
			
			if(balance.signum()==-1)
				balance=new BigDecimal("0.00");
			
			
			setBalance(balance);
			
			}
		else 
			{
		
			setTotal_pagado(new BigDecimal(valorpagado));
			setBalance(new BigDecimal(deuda));
			}
		
		
		
		String cambio=Utilitarios.removeDecimalFormat(getTxtCambio().getText());
		
	
		
		if(cambio.equals("") || cambio==null)
			setCambio(new BigDecimal("0.00"));
		else
			setCambio(new BigDecimal(cambio));
		 
		
		this.dispose(); 

	}

	
	public void save() {		
		 

	}
 
	public BigDecimal getTotal_pagar() {
		return total_pagar;
	}

	public void setTotal_pagar(BigDecimal total_pagar) {
		this.total_pagar = total_pagar;
	}

	public BigDecimal getTotal_pagado() {
		return total_pagado;
	}

	public void setTotal_pagado(BigDecimal total_pagado) {
		this.total_pagado = total_pagado;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Long getCajaAbiertaID() {
		return cajaAbiertaID;
	}

	public void setCajaAbiertaID(Long cajaAbiertaID) {
		this.cajaAbiertaID = cajaAbiertaID;
	}

	public boolean isAceptar() {
		return aceptar;
	}

	public void setAceptar(boolean aceptar) {
		this.aceptar = aceptar;
	}

	public BigDecimal getCambio() {
		return cambio;
	}

	public void setCambio(BigDecimal cambio) {
		this.cambio = cambio;
	}

	public KeyListener getOKeyAdapterVuelto() {
		return oKeyAdapterVuelto;
	}

	public void setOKeyAdapterVuelto(KeyListener keyAdapterVuelto) {
		oKeyAdapterVuelto = keyAdapterVuelto;
	}
	 
	
	
	
}
