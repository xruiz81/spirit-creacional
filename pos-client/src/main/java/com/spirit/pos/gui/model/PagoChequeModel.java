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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.gui.panel.JPPagoCheque;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class PagoChequeModel extends JPPagoCheque{
 



	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	BigDecimal deuda= new BigDecimal("0.00");
	BigDecimal monto= new BigDecimal("0.00");
	
	BigDecimal balance= new BigDecimal("0.00");
	
	Long cajaAbiertaID=new Long("0"); 
	private boolean aceptar=false;
	String numcheque="";
	String banco="";
	String numcta="";
 
	
	BigDecimal cambio= new BigDecimal("0.00");

	public PagoChequeModel(Frame owner, String total) {
		super(owner);
		this.deuda=new BigDecimal(total);		 
		iniciarcomp();
		initKeyListeners();
		setName("Pago Cheque");
		
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
		 
		getTxtMonto().setText(formatoDecimal.format(deuda));
		getTxtMonto().setNextFocusableComponent(getCmbBanco());
		getCmbBanco().setNextFocusableComponent(getTxtNumCheque());
		getTxtNumCheque().setNextFocusableComponent(getTxtNumCuenta());
		getTxtNumCuenta().setNextFocusableComponent(getBtnAceptar());
		
		getTxtDeuda().setText(formatoDecimal.format(deuda));
		getTxtBalance().setText("0.00");
		
		getBtnAceptar().addActionListener(oActionListenerBotonAceptar);		
		getBtnCancelar().addActionListener(oActionListenerBotonCancelar);	
		getTxtMonto().addKeyListener(oKeyAdapterVuelto);
		
		getTxtNumCuenta().addKeyListener(oKeyAdapterEnter);
		
		Caja_abierta_id();
		llenar_combo();
	}
	
	
	public boolean validar_datos() {
		boolean llenos = true;
		if (getTxtNumCheque().getText() == null || getTxtNumCheque().getText().equals("")) {
			llenos = false;
			SpiritAlert.createAlert("Debe ingresar el Número de Cheque del cliente",
					SpiritAlert.INFORMATION);
		}
		if (getTxtNumCuenta().getText() == null || getTxtNumCuenta().getText().equals("")) {
			llenos = false;
			SpiritAlert.createAlert("Debe ingresar el Número de Cuenta del cliente",
					SpiritAlert.INFORMATION);
		}
		BancoIf bancoIf = ((BancoIf) this.getCmbBanco().getSelectedItem());
		if(bancoIf==null)
		{
			llenos = false;
			SpiritAlert.createAlert("Debe escoger el Banco",SpiritAlert.INFORMATION);
		}
		
		System.out.println("bancoId"+bancoIf);
		
		return llenos;
	}

	KeyListener oKeyAdapterVuelto = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			BigDecimal monto = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));
			BigDecimal deuda = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));
			BigDecimal balance = deuda.subtract(monto);
			if (balance.signum() == -1) {
				SpiritAlert.createAlert("Debe escribir un monto menor o igual al valor de la Deuda ",SpiritAlert.INFORMATION);
				getTxtBalance().setText("0.00");
				getTxtMonto().setText("0.00");

			} else {
				getTxtBalance().setText(balance.toString());
			}
		}
	};

	
	KeyListener oKeyAdapterEnter = new KeyAdapter() {
		

		public void keyTyped(KeyEvent e) {	
			if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB) {
				if (validar_datos()) {
					aceptar();
					aceptar=true;
				}
			}  
		}	
		
	};
	
	public void llenar_combo(){
		SpiritComboBoxModel cmbModelTipoBanco;
		try {

			BancoIf bancoIf; 
			Collection<BancoIf> tipoclientes  = SessionServiceLocator.getBancoSessionService().getBancoList();			 
		 		 System.out.println("tipo clientes-<"+tipoclientes.size());
			if ( tipoclientes.size() >0 ) 
			{
				bancoIf = tipoclientes.iterator().next();					
				cmbModelTipoBanco = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getBancoSessionService().getBancoList());
				getCmbBanco().setModel(cmbModelTipoBanco);
			}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (GenericBusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	 
	

	public void initKeyListeners(){		 
		getTxtMonto().addKeyListener(new TextChecker(7));
		getTxtMonto().addKeyListener(new NumberTextFieldDecimal()); 
		getTxtMonto().setNextFocusableComponent(getCmbBanco());		
	}

	 

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
			if (validar_datos()) {
				aceptar();
				aceptar=true;
			}
			 
			 
		}
	};
	
	ActionListener oActionListenerBotonCancelar= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			aceptar=false;
			dispose(); 
		}
	};
	
	public void aceptar() {		
	 
		String monto_str=Utilitarios.removeDecimalFormat(getTxtMonto().getText());		
		String balance_str=Utilitarios.removeDecimalFormat(getTxtBalance().getText()).trim();
		String total_pagar=Utilitarios.removeDecimalFormat(getTxtDeuda().getText());		
		if(deuda.equals("0.00"))
			{			
			setMonto(new BigDecimal(total_pagar));							 
			BigDecimal deuda2=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));
			BigDecimal monto=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));
			BigDecimal balance=deuda2.subtract(monto);			
			if(balance.signum()==-1)
				balance=new BigDecimal("0.00");			
			
			setBalance(balance);
			numcheque=getTxtNumCheque().getText();
			setNumcheque(numcheque);
			
			numcta=getTxtNumCuenta().getText();
			setNumcta(numcta);
			
			BancoIf bancoNombre=((BancoIf) this.getCmbBanco().getSelectedItem());
			setBanco(bancoNombre.getNombre());
			
			 
			
			}
		else 
			{		
			setMonto(new BigDecimal(monto_str));
			setBalance(new BigDecimal(balance_str));
			numcheque=getTxtNumCheque().getText();
			setNumcheque(numcheque);
			
			numcta=getTxtNumCuenta().getText();
			setNumcta(numcta);
			
			BancoIf bancoNombre=((BancoIf) this.getCmbBanco().getSelectedItem());
			setBanco(bancoNombre.getNombre());
			}
		
	 
		 
		
		this.dispose(); 

	}

	
	public void save() {		
		 

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

	public String getNumcheque() {
		return numcheque;
	}

	public void setNumcheque(String numcheque) {
		this.numcheque = numcheque;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public BigDecimal getDeuda() {
		return deuda;
	}

	public void setDeuda(BigDecimal deuda) {
		this.deuda = deuda;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getNumcta() {
		return numcta;
	}

	public void setNumcta(String numcta) {
		this.numcta = numcta;
	}
 
	
	
	
}
