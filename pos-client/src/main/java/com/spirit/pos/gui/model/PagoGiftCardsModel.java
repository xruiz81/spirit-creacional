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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.inventario.entity.GiftcardIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.gui.panel.JDPagoGiftCard;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class PagoGiftCardsModel extends JDPagoGiftCard {
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
	String nombre_gc=new String("GIFT CARD");
	private boolean aceptar=false;
	private boolean borrar_repetido=false;
	Long id_giftcard=0L;
	Vector<Vector> PagosCollection_detalles =new Vector<Vector>();
	private static final int MAX_LONGITUD_SERIAL_NUMBER = 13;

	public PagoGiftCardsModel(Frame owner, String total,Vector<Vector> PagosCollection_detalles) {
		super(owner);
		this.total_pagar=new BigDecimal(total);		
		this.PagosCollection_detalles=PagosCollection_detalles;
		iniciarComponentes();
		initKeyListeners();
		setName("Pago con Giftcard");		
		addWindowListener(new WindowAdapter() {
			public void windowOpened( WindowEvent e ){
				getTxtSerialNumber().requestFocus();
			}

			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});	 

	}

	private void iniciarComponentes(){
		getTxtMonto().addKeyListener(new TextChecker(9));
		getTxtMonto().addKeyListener(new NumberTextFieldDecimal()); 
		getTxtDeuda().setText(formatoDecimal.format(total_pagar));
		getTxtMonto().setText(formatoDecimal.format(total_pagar));		
		getBtnProcesar().addActionListener(oActionListenerBotonAceptar);		
		getBtnCancelar().addActionListener(oActionListenerBotonCancelar);	
		Caja_abierta_id();
		getTxtMontoDisponible().setText("0.00");
		getTxtMontoAcreditado().setText("0.00");
	}	

	public void initKeyListeners(){
		//getTxtSerialNumber().addKeyListener(new TextChecker(MAX_LONGITUD_SERIAL_NUMBER)); 
		getTxtMonto().addKeyListener(oKeyAdapterVuelto);
		getTxtSerialNumber().addKeyListener(oKeyAdapterEnterSerial);
		//getTxtCodGiftCard().addKeyListener(oKeyAdapterEnterCodigo);
		getTxtSerialNumber().setNextFocusableComponent(getTxtMonto());
		getTxtMonto().setNextFocusableComponent(getBtnProcesar());
	}

	/*KeyListener oKeyAdapterEnterCodigo = new KeyAdapter() {
		public void keyTyped(KeyEvent e) {//
			if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB) {			
				buscar_producto_GIFT(getTxtCodGiftCard().getText());
				BigDecimal montodispo=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMontoDisponible().getText()));
				BigDecimal deuda=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));		
				BigDecimal tempo=montodispo.subtract(deuda);
				if(tempo.signum() == -1)
					getTxtMonto().setText(montodispo.toString());
				BigDecimal monto=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));
				BigDecimal balance=deuda.subtract(monto);
				getTxtBalance().setText(balance.toString());
			}
		}
	};*/

	KeyListener oKeyAdapterEnterSerial = new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB) {			
				buscar_producto_GIFT(getTxtSerialNumber().getText());
				
				if (PagosCollection_detalles.size() > 0) {
					for (int l = 0; l < PagosCollection_detalles.size(); l++) {
						String tipo_gcID=((Vector)PagosCollection_detalles.get(l)).get(4).toString();
						String producto_gc=((Vector)PagosCollection_detalles.get(l)).get(7).toString();
						String total_pago=((Vector)PagosCollection_detalles.get(l)).get(1).toString();
						/*if(tipo_gcID.equals("GC") && producto_gc.equals(getGiftcardId().toString())) {
							String si = "Sí"; 
							String no = "No"; 
							Object[] options = {si, no};
							int opcion = JOptionPane.showOptionDialog(null, "Usted ya uso este giftcard en esta transacción. Deseea borrar los valores?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
							if(opcion == JOptionPane.YES_OPTION){
								borrar_repetido=true;				    				
								valorAnterior=new BigDecimal(total_pago);
								getTxtMonto().grabFocus();
							} else {
								borrar_repetido=false;
								getTxtMontoDisponible().setText("0.00");
								getTxtMontoAcreditado().setText("0.00");
								getTxtSerialNumber().setText("");
								//getTxtCodGiftCard().setText("");
								getTxtSerialNumber().grabFocus();
							}
						}*/
						if(tipo_gcID.equals("GC") && producto_gc.equals(getGiftcardId().toString())) {
							SpiritAlert.createAlert("Giftcard utilizado previamente en esta transacción", SpiritAlert.WARNING);
							borrar_repetido=false;
							getTxtMontoDisponible().setText("0.00");
							getTxtMontoAcreditado().setText("0.00");
							getTxtSerialNumber().setText("");
							getTxtSerialNumber().grabFocus();
						}
					}
				}

				BigDecimal montodispo=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMontoDisponible().getText()));
				BigDecimal deuda=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));		
				BigDecimal tempo=montodispo.subtract(deuda);
				if(tempo.signum()==-1)
					getTxtMonto().setText(montodispo.toString());

				BigDecimal monto=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));
				BigDecimal balance=deuda.subtract(monto);
				//BigDecimal SaldoFinal=montodispo.subtract(monto);
				//setSaldoFinal(SaldoFinal);
				getTxtBalance().setText(balance.toString());
				/*setCodigoBarras(getTxtSerialNumber().getText().toString());
				setCodigo(getTxtCodGiftCard().getText().toString());
				
				BigDecimal montodispo=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMontoDisponible().getText()));
				BigDecimal deuda=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));		
				BigDecimal tempo=montodispo.subtract(deuda);
				if(tempo.signum() == -1)
					getTxtMonto().setText(montodispo.toString());
				BigDecimal monto=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));
				BigDecimal balance=deuda.subtract(monto);
				getTxtBalance().setText(balance.toString());*/
			}
		}
	};

	public void buscar_producto_GIFT(String codigoBarras){
		//buscar el gift card setear monto acreditado de la lista de precios
		try {
			if(codigoBarras!=null || codigo!=null) {
				id_giftcard=0L;
				HashMap parameterMap = new HashMap();
				parameterMap.put("estado", "A");			
				parameterMap.put("codigoBarras", codigoBarras);
				Iterator giftcardIt = SessionServiceLocator.getGiftcardSessionService().findGiftcardByQueryWebService(Parametros.getIdEmpresa(), codigoBarras, "A").iterator();
				if (giftcardIt.hasNext()) {
					GiftcardIf giftcard = (GiftcardIf) giftcardIt.next();
					getTxtMontoAcreditado().setText(giftcard.getValor().toString());
					BigDecimal saldo = giftcard.getSaldo();
					if(saldo == null) 
						saldo = BigDecimal.ZERO;
					if(saldo.compareTo(BigDecimal.ZERO) == 0) {
						getTxtMontoDisponible().setText("0.00");
						getTxtMontoAcreditado().setText("0.00");
						getTxtSerialNumber().setText("");
						//getTxtCodGiftCard().setText("");
						getTxtBalance().setText("0.00");
						id_giftcard=0L;
						SpiritAlert.createAlert("Giftcard está inactivo o no tiene saldo disponible",SpiritAlert.INFORMATION);
					} else {
						getTxtMontoDisponible().setText(saldo.toString());
						id_giftcard=0L;
						setGiftcardId(giftcard.getId());
						setSaldoInicial(saldo);
						setearSaldoFinal();
						setCodigoBarras(getTxtSerialNumber().getText().toString());
						setCodigo(getTxtSerialNumber().getText().toString());
					}
					//getTxtSerialNumber().setText(giftcard.getCodigoBarras());
				} else {
					id_giftcard=0L;
					getTxtMontoDisponible().setText("0.00");
					getTxtMontoAcreditado().setText("0.00");
					getTxtSerialNumber().setText("");
					//getTxtCodGiftCard().setText("");
					getTxtBalance().setText("0.00");
					SpiritAlert.createAlert("Giftcard no existe",SpiritAlert.INFORMATION);
				}
			} else {
				getTxtMontoAcreditado().setText("0.00");
				getTxtMontoDisponible().setText("0.00");
				id_giftcard=0L;
			}
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}

	public void setearSaldoFinal() {		
		BigDecimal montodispo = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMontoDisponible().getText()));
		BigDecimal deuda = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));		
		BigDecimal tempo = montodispo.subtract(deuda);
		if(tempo.signum() == -1)
			getTxtMonto().setText(montodispo.toString());
		BigDecimal monto = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));
		BigDecimal balance = deuda.subtract(monto);		
		BigDecimal SaldoFinal = montodispo.subtract(monto);
		setSaldoFinal(SaldoFinal);
	}

	KeyListener oKeyAdapterVuelto = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {		 
			BigDecimal monto=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));	
			BigDecimal deuda=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));			
			BigDecimal balance=deuda.subtract(monto);			

			if (balance.signum() == -1) {				
				SpiritAlert.createAlert("Debe escribir un monto menor o igual al valor de la deuda ",SpiritAlert.INFORMATION);
				getTxtBalance().setText("0.00");
				getTxtMonto().setText("0.00");
			} else {
				BigDecimal disponible=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMontoDisponible().getText()));
				BigDecimal tempo=disponible.subtract(monto);
				if (tempo.signum() == -1) {
					SpiritAlert.createAlert("Debe escribir un monto menor o igual al valor disponible del giftcard ",SpiritAlert.INFORMATION);
					getTxtBalance().setText("0.00");
					getTxtMonto().setText("0.00");
				} else
					getTxtBalance().setText(balance.toString());
			}

			BigDecimal disponible = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMontoDisponible().getText()));
			BigDecimal SaldoFinal = disponible.subtract(monto);
			setSaldoFinal(SaldoFinal);
			setCodigoBarras(getTxtSerialNumber().getText().toString());
			setCodigo(getTxtSerialNumber().getText().toString());
		}
	};

	ActionListener oActionListenerBotonAceptar= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {	
			String scod= getTxtSerialNumber().getText();
			double monto = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));
			aceptar = false;
			if(scod == null || scod.equals("")) {
				SpiritAlert.createAlert("Debe ingresar el código del giftcard",SpiritAlert.INFORMATION);
				getTxtSerialNumber().grabFocus();
			} else
				aceptar = true;
			
			if (monto <= 0D) {
				SpiritAlert.createAlert("Monto debe ser mayor a 0",SpiritAlert.INFORMATION);
				getTxtMonto().grabFocus();
				aceptar = false;
			} else
				aceptar = true;
			
			if (aceptar)
				setear_datos();
		}
	};

	ActionListener oActionListenerBotonCancelar= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			aceptar=false;
			dispose(); 
		}		
	};	

	private String buscarIdTipoPago(String codigo){
		String idtipopago="";
		//id de efectivo... para no setear un id fijo
		Map aMap = new HashMap();
		aMap.clear();
		aMap.put("codigo", codigo);		
		TipoPagoIf tipoPagoIf;
		try {
			Collection<TipoPagoIf> tipoPagoIf2 = SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByQuery(aMap);
			if (tipoPagoIf2.size() == 1) {
				tipoPagoIf = tipoPagoIf2.iterator().next();
				idtipopago = tipoPagoIf.getId().toString();
			}					
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return idtipopago;
	}

	public void setear_datos() {
		String valorpagado=Utilitarios.removeDecimalFormat(getTxtMonto().getText());			
		BigDecimal monto=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText())); 
		BigDecimal deuda2=new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));			
		BigDecimal balance=deuda2.subtract(monto);	
		setBalance(balance); 
		setTotal_monto(new BigDecimal(valorpagado));
		setGiftcardId(id_giftcard);
		setCodigoBarras(getTxtSerialNumber().getText().toString());
		setCodigo(getTxtSerialNumber().getText().toString());
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
			} else
				SpiritAlert.createAlert("Debe tener una caja abierta",SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		this.cajaAbiertaID=caja_id;
	}

	public Long getId_GIFTCARD_tipoproducto(){
		Long id_giftcard=0L;
		Map aMap = new HashMap();
		aMap.clear();
		aMap.put("nombre", nombre_gc);		
		TipoProductoIf tipoProductoIf=null;		 
		try {
			tipoProductoIf = (TipoProductoIf)SessionServiceLocator.getTipoProductoSessionService().findTipoProductoByQuery(aMap).iterator().next();
			id_giftcard=tipoProductoIf.getId();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}

		return (id_giftcard);
	}
	
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

	public Long getGiftcardId() {
		return id_giftcard;
	}

	public void setGiftcardId(Long id_giftcard) {
		this.id_giftcard= id_giftcard;
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
