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
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.gui.panel.JDPagoCreditoCliente;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

 
public class PagoCreditoClienteModel extends JDPagoCreditoCliente{

	private DefaultTableModel tableModel;	
	BigDecimal total_pagar= new BigDecimal("0.00");	
	BigDecimal total_monto= new BigDecimal("0.00");
	BigDecimal total_balance= new BigDecimal("0.00");
	BigDecimal balance= new BigDecimal("0.00");
	
	BigDecimal saldoFinal= new BigDecimal("0.00");
	BigDecimal saldoInicial= new BigDecimal("0.00");
	BigDecimal valorAnterior= new BigDecimal("0.00");
	
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");		
	
	Long cajaAbiertaID=new Long("0"); 	
	public String codigoBarras="";
	public String codigo="";	
 
	public String clienteOficinaId="";
	private boolean aceptar=false;	
	private boolean borrar_repetido=false;
	
 
	Long empresa=0L;
	 
	
	public PagoCreditoClienteModel(Frame owner, String total,String nombre_cl,String clienteOficinaId,Long empresa) {
		super(owner);
		this.total_pagar=new BigDecimal(total);		       
		this.clienteOficinaId=clienteOficinaId;
		this.empresa=empresa;
		
		iniciarcomp();
		initKeyListeners();
		  
		setName("Pago Crédito Cliente");		
		 addWindowListener(new WindowAdapter() {
			 public void windowOpened( WindowEvent e ){
				 getTxtUsuario().requestFocus();
		      }

			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});	 
	 
	}
	
	private void iniciarcomp(){		
		getTxtDeuda().setText(formatoDecimal.format(total_pagar));
		
		
		//getTxtMonto().setText(formatoDecimal.format(total_pagar));		
		getBtnAceptar().addActionListener(oActionListenerBotonAceptar);		
		getBtnCancelar().addActionListener(oActionListenerBotonCancelar);	
		Caja_abierta_id();
		llenar_datos_creditos();
		
		getTxtMonto().setEnabled(true);
		getTxtMonto().grabFocus();
		getTxtClave().setNextFocusableComponent(getTxtMonto());
	}	
	
	public void llenar_datos_creditos(){
		
		DefaultTableModel model = (DefaultTableModel) getTblDetalles().getModel();
		for(int i= this.getTblDetalles().getRowCount();i>0;--i)	model.removeRow(i-1);	
		tableModel = (DefaultTableModel) getTblDetalles().getModel();
		
		HashMap<String, Object> mapa = new HashMap<String, Object>();
		mapa.clear();
		try {
		TipoDocumentoIf tipoDocumentoIf = (TipoDocumentoIf)SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo("DEV").iterator().next();
		
	 
		mapa.put("tipodocumentoId",tipoDocumentoIf.getId());
		mapa.put("clienteoficinaId",new Long(clienteOficinaId));
		Iterator carterasIt;
		Vector<Vector> detalles_id_saldo =new Vector<Vector>();					
		
			//carterasIt = getCarteraSessionService().findCarteraByQuery(mapa,empresa).iterator();
		carterasIt = SessionServiceLocator.getCarteraSessionService().findCarteraCreditoDisponible(mapa,empresa).iterator();
			
			System.out.println("-carterasIT->"+carterasIt);
			BigDecimal suma=new BigDecimal("0");
			while (carterasIt.hasNext()) 
			{
				System.out.println("-carterasIT->"+carterasIt);
				CarteraIf carteradeta= (CarteraIf) carterasIt.next();
				BigDecimal valor_deta=carteradeta.getSaldo();
				suma=suma.add(valor_deta);
				System.out.println(valor_deta);
					Vector<String> id_saldo = new Vector<String>();
					id_saldo.add(0,carteradeta.getSaldo().toString());					
					id_saldo.add(1,carteradeta.getFechaEmision().toString());
					detalles_id_saldo.add(id_saldo);	
					
			}
			
			getTxtMontoDisponible().setText(suma.toString());
			
			if(suma.compareTo(total_pagar)==-1)
			{
				getTxtMonto().setText(formatoDecimal.format(suma));	
			}
			if(suma.compareTo(total_pagar)==0)
			{
				getTxtMonto().setText(formatoDecimal.format(suma));	
			}
			if(suma.compareTo(total_pagar)==1)
			{
				getTxtMonto().setText(formatoDecimal.format(total_pagar));	
			}
				
		 
			BigDecimal monto=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));
			BigDecimal deuda=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));			
			BigDecimal balance=deuda.subtract(monto);			
			
			getTxtBalance().setText(balance.toString());
			
			
			System.out.println("detalles>>"+detalles_id_saldo.size());
	
			System.out.println("formas de pago--->"+tableModel.getRowCount());
			
		if (detalles_id_saldo.size() != 0) {
			for (int l = 0; l < detalles_id_saldo.size(); l++) {				
				Vector<String> fila = new Vector<String>();		
				fila.add(((Vector)detalles_id_saldo.get(l)).get(1).toString());
				fila.add(((Vector)detalles_id_saldo.get(l)).get(0).toString());
				 
				tableModel.addRow(fila);
			}
		}
		
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}
	
	public void initKeyListeners(){
		getTxtMonto().addKeyListener(new TextChecker(9));
		getTxtMonto().addKeyListener(new NumberTextFieldDecimal()); 
		getTxtMonto().addKeyListener(oKeyAdapterVuelto);
		
		
		
		getTxtMonto().setNextFocusableComponent(getBtnAceptar());
	
		
		
	}
	
	
	
	public void setearSaldoFinal(){
		
		BigDecimal montodispo=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMontoDisponible().getText()));
		BigDecimal deuda=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));		
		BigDecimal tempo=montodispo.subtract(deuda);
		if(tempo.signum()==-1)
			getTxtMonto().setText(montodispo.toString());
		
		
		BigDecimal monto=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));
		BigDecimal balance=deuda.subtract(monto);
		
		BigDecimal SaldoFinal=montodispo.subtract(monto);
		
 
		setSaldoFinal(SaldoFinal);
		
		 
	}
 
	KeyListener oKeyAdapterVuelto = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {		 
			BigDecimal monto=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));	
			
			
			
			BigDecimal deuda=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));			
			BigDecimal balance=deuda.subtract(monto);			
			
			
			
			
			if(balance.signum()==-1)
				{				
				SpiritAlert.createAlert("Debe escribir un monto menor o igual al valor de la Deuda ",SpiritAlert.INFORMATION);
				getTxtBalance().setText("0.00");
				getTxtMonto().setText("0.00");
				
				}
			else{
				BigDecimal disponible=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMontoDisponible().getText()));
				BigDecimal tempo=disponible.subtract(monto);
				if(tempo.signum()==-1)
				{
					SpiritAlert.createAlert("Debe escribir un monto menor o igual al valor Disponible",SpiritAlert.INFORMATION);
					getTxtBalance().setText("0.00");
					getTxtMonto().setText("0.00");
				}
				else
					getTxtBalance().setText(balance.toString());
			}
			
			BigDecimal disponible=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMontoDisponible().getText()));
			
			BigDecimal SaldoFinal=disponible.subtract(monto);
			setSaldoFinal(SaldoFinal);
			
		 
		}
		
		public void keyTyped(KeyEvent e) {	
			if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB) {
				
				String scod= getTxtMonto().getText();
				if(scod==null || scod.equals(""))
				{
					SpiritAlert.createAlert("Debe ingresar el Monto que desea aplicar",SpiritAlert.INFORMATION);
					getTxtMonto().grabFocus();
				}
				else{
				aceptar=true;
				setear_datos();
				}
				
			}
		}
		
		
	};
	
	ActionListener oActionListenerBotonAceptar= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			
			
			String scod= getTxtMonto().getText();
			if(scod==null || scod.equals(""))
			{
				SpiritAlert.createAlert("Debe ingresar el Monto que desea aplicar",SpiritAlert.INFORMATION);
				getTxtMonto().grabFocus();
			}
			else{
			aceptar=true;
			setear_datos();
			}
			
			
		}
	};
	
	ActionListener oActionListenerBotonCancelar= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			aceptar=false;
			dispose(); 
		}
		
	};
	
	 
	
	
	public void setear_datos() {		
		
		String valorpagado=Utilitarios.removeDecimalFormat(getTxtMonto().getText());	
		BigDecimal monto=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText())); 
		BigDecimal deuda2=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));		
		BigDecimal balance=deuda2.subtract(monto);
			setBalance(balance);
		setTotal_monto(new BigDecimal(valorpagado));
	 
		this.dispose(); 
		
		
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
				SpiritAlert.createAlert("Debe tener una caja abierta",SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cajaAbiertaID=caja_id;

	}
	
	  
	
/////////////////////////sesiones///////////////	
	 

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getTotal_monto() {
		return total_monto;
	}

	public void setTotal_monto(BigDecimal total_monto) {
		this.total_monto = total_monto;
	}

	public boolean isAceptar() {
		return aceptar;
	}

	public void setAceptar(boolean aceptar) {
		this.aceptar = aceptar;
	}
	

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}
	
	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial= saldoInicial;
	}
	
	
	public BigDecimal getSaldoFinal() {
		return saldoFinal;
	}
	
	public void setSaldoFinal(BigDecimal saldoFinal) {
		this.saldoFinal= saldoFinal;
	}

	public boolean isBorrar_repetido() {
		return borrar_repetido;
	}

	public void setBorrar_repetido(boolean borrar_repetido) {
		this.borrar_repetido = borrar_repetido;
	}

	public BigDecimal getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(BigDecimal valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	
}
