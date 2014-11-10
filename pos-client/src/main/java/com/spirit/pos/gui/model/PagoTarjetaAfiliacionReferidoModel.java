package com.spirit.pos.gui.model;


import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.gui.panel.JDPagoTarjetaAfiliacionReferido;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class PagoTarjetaAfiliacionReferidoModel extends JDPagoTarjetaAfiliacionReferido {
	BigDecimal puntosDinero = new BigDecimal("0.00");
	BigDecimal puntosDineroAcumulado = new BigDecimal("0.00");	
	BigDecimal puntosDineroUtilizado = new BigDecimal("0.00");
	BigDecimal puntosDineroDisponible = new BigDecimal("0.00");
	BigDecimal puntosDineroComprometido = new BigDecimal("0.00");
	BigDecimal aplicarPuntosDinero = new BigDecimal("0.00");	
	BigDecimal balance = new BigDecimal("0.00");
	BigDecimal deuda = new BigDecimal("0.00");
	BigDecimal monto = new BigDecimal("0.00");
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");		
	Long cajaAbiertaID=new Long("0"); 	
	public String identificacionFactura="";
	public String usadorporClienteOficinaId="";
	private boolean aceptar=false;	
	Long empresa=0L;
	String cedula="";	
	private JDPopupFinderModel popupFinder;
	public String identificacionDuenoTarjeta ="";
	public String cedulaDuenoTarjeta ="";
	public String tipoDuenoTarjeta ="";
	String referido=""; 
	public String nombreDuenoTarjeta="";
	public String descuentoTipoTarjeta="";
	Vector<Vector> PagosCollection_detalles =new Vector<Vector>();
	private ParametroEmpresaIf acumularPuntosDinero = null;

	public PagoTarjetaAfiliacionReferidoModel(Frame owner, String total,String nombre_cl,String clienteOficinaId,Long empresa,String identificacionClienteFactura,Vector<Vector> PagosCollection_detalles) {
		super(owner);
		clean();
		this.deuda = new BigDecimal(total);
		this.PagosCollection_detalles=PagosCollection_detalles;
		setName("Tarjeta de Afiliciación/Referido");
		this.cedula=cedula;
		this.identificacionFactura=identificacionClienteFactura;
		this.nombreDuenoTarjeta=nombre_cl;
		iniciarComponentes();
		initKeyListeners();
		
		Map parameterMap = new HashMap();
		parameterMap.put("empresaId", empresa);
		parameterMap.put("codigo", "APD");
		try {
		Iterator it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
		if (it.hasNext())
			acumularPuntosDinero = (ParametroEmpresaIf) it.next();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				getBtnBuscarOperadorNegocio().requestFocus();
			}

			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

	public void clean(){
		setMonto(new BigDecimal("0.00"));		
		setAplicarPuntosDinero(new BigDecimal("0"));
		setReferido("");
		setBalance(new BigDecimal("0.00"));
		setIdentificacionDuenoTarjeta("");
		setCedulaDuenoTarjeta("");
		setUsadorporClienteOficinaId("");
		setTipoDuenoTarjeta("");
		setDescuentoTipoTarjeta("0");		
	}

	private void iniciarComponentes() {
		setCajaAbiertaId();

		getTxtEquivalenteDinero().setText(formatoDecimal.format(monto));
		getTxtDeuda().setText(formatoDecimal.format(deuda));
		getBtnAceptar().addActionListener(oActionListenerBotonAceptar);
		getBtnCancelar().addActionListener(oActionListenerBotonCancelar);
		getBtnAplicar().addActionListener(oActionListenerBotonConvertir);		
		getChkReferido().addActionListener(new checkboxReferido());
		getChkPropietario().addActionListener(new checkboxPropietario());		
		getChkReferido().setSelected(true);
		getTxtPuntosDisponibles().setEditable(false);
		getBtnBuscarOperadorNegocio().setEnabled(true);		

		getBtnAceptar().setEnabled(false);
		getBtnAplicar().setEnabled(false);

		getBtnBuscarOperadorNegocio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {			
				String identificacion="";				
				if (getTxtIdentificacion().getText() != null)identificacion=getTxtIdentificacion().getText();		
				try {					
					Vector<String> datosDetalles = new Vector<String>();					
					datosDetalles = SessionServiceLocator.getFacturaSessionService().referirAfiliado(identificacionFactura, identificacion, Parametros.getIdEmpresa());
					if(datosDetalles.size()>0)
					{
						if(datosDetalles.get(0).equals("N"))
						{	SpiritAlert.createAlert("El cliente posee tarjeta de afiliación. Debe usar la opción Propietario.",SpiritAlert.INFORMATION);						
						}
						else{
							if(!datosDetalles.get(1).equals(""))
							{
								getTxtPropietarioTarjeta().setText(datosDetalles.get(1));
							}
							if(!datosDetalles.get(2).equals(""))
							{
								setTipoDuenoTarjeta(datosDetalles.get(2));
								setIdentificacionDuenoTarjeta(identificacion);
								setIdentificacionDuenoTarjeta(identificacionDuenoTarjeta);
								getBtnAceptar().setEnabled(true);
							}
							if(!datosDetalles.get(3).equals(""))
							{								
								setDescuentoTipoTarjeta(datosDetalles.get(3));
							}
						}	
					}
					else{
						SpiritAlert.createAlert("No se encuentra información relacionada a dicha identificacion.",SpiritAlert.INFORMATION);						

					}				 
				} catch (Exception e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error al consultar la oficina del cliente",SpiritAlert.ERROR);
				}					
			}
		});		
	}

	public void llenarDatosPuntos(){
		//CONSULTO LA TABLA TARJETAID... POR CLIENTEOFICINAID PARA OBTENER DATOS DE LOS PUNTOS!
		setearCliente();		
		BigDecimal acum= new BigDecimal(getTxtPuntosAcumulados().getText());
		BigDecimal utiliz= new BigDecimal(getTxtPuntosUtilizados().getText());
		BigDecimal compro= new BigDecimal(getTxtPuntosComprometidos().getText());
		BigDecimal disponibles= acum.subtract(utiliz);
		disponibles=disponibles.subtract(compro);					
		getTxtPuntosDisponibles().setText(disponibles.toString());
	}


	public void initKeyListeners() {
		getTxtCantidadAplicar().addKeyListener(new TextChecker(22));
		getTxtCantidadAplicar().addKeyListener(new NumberTextFieldDecimal());
	}

	//cuando es propietario
	private boolean setearCliente() {
		boolean find = false;
		try {	
			BigDecimal acum=BigDecimal.ZERO;
			BigDecimal util=BigDecimal.ZERO;
			BigDecimal comp=BigDecimal.ZERO;
			getTxtPuntosAcumulados().setText(acum.toString());
			getTxtPuntosUtilizados().setText(util.toString());
			getTxtPuntosComprometidos().setText(comp.toString());			
			Vector<String> datosPropietario = new Vector<String>();
			datosPropietario=SessionServiceLocator.getFacturaSessionService().propietarioAfiliado(nombreDuenoTarjeta,identificacionFactura,Parametros.getIdEmpresa());	

			if(datosPropietario.size()>0)
			{
				if(datosPropietario.get(0).equals("N"))
				{	
					SpiritAlert.createAlert("El cliente no posee Tarjeta de Afiliación. Muchas gracias. ",SpiritAlert.INFORMATION);
				}
				else{

					getTxtPropietarioTarjeta().setText(datosPropietario.get(1));
					getTxtIdentificacion().setText(datosPropietario.get(2));					 
					if(datosPropietario.get(3)!=null){
						setIdentificacionDuenoTarjeta(datosPropietario.get(2));
						setTipoDuenoTarjeta(datosPropietario.get(3));	
						getTxtPuntosDinero().setText(datosPropietario.get(4));
						getBtnAplicar().setEnabled(true);
						getTxtPuntosAcumulados().setText(datosPropietario.get(6));
						getTxtPuntosUtilizados().setText(datosPropietario.get(7));
						getTxtPuntosComprometidos().setText(datosPropietario.get(8));
						setDescuentoTipoTarjeta(datosPropietario.get(9));
					}
				}
			}
			else{
				SpiritAlert.createAlert("El cliente no posee Tarjeta de Afiliación. Muchas gracias. ",SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return find;
	}

	public static boolean esMultiplo(int n1,int n2){
		if (n1%n2==0)
			return true;
		else
			return false;
	}

	private class checkboxReferido implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (getChkReferido().isSelected()) {
				getBtnBuscarOperadorNegocio().setEnabled(true);
				getChkPropietario().setSelected(false);			
				getTxtCantidadAplicar().setEditable(false);
				getTxtPuntosDisponibles().setEditable(false);
				getBtnAplicar().setEnabled(false);
				clean_datos();

			} else {
				getBtnBuscarOperadorNegocio().setEnabled(false);
				getTxtCantidadAplicar().setEditable(true);
				getChkPropietario().setSelected(true);
				getBtnAplicar().setEnabled(true);
				llenarDatosPuntos(); 

			}
		}
	}

	private class checkboxPropietario implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (getChkPropietario().isSelected()) {				
				getBtnBuscarOperadorNegocio().setEnabled(false);				
				getChkReferido().setSelected(false);
				getTxtCantidadAplicar().setEditable(true);
				getBtnAplicar().setEnabled(true);
				llenarDatosPuntos();

			} else {
				getBtnBuscarOperadorNegocio().setEnabled(true);
				getChkReferido().setSelected(true);			
				getBtnAplicar().setEnabled(false);
				getTxtCantidadAplicar().setEditable(false);
			}
		}
	}

	public void clean_datos() {
		getTxtPuntosAcumulados().setText("");
		getTxtPuntosDisponibles().setText("");
		getTxtPuntosUtilizados().setText("");
		getTxtPuntosComprometidos().setText("");	
	}

	ActionListener oActionListenerBotonAceptar = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			if (validar_datos()) {
				setear_datos();
				aceptar = true;
				dispose();
			}
		}
	};

	public boolean validar_datos() {
		if (getChkPropietario().isSelected()) {

		}
		
		if (PagosCollection_detalles.size() > 0) {
			for (int l = 0; l < PagosCollection_detalles.size(); l++) {
				String tipoPago = ((Vector)PagosCollection_detalles.get(l)).get(4).toString();
				String identificacionPropietarioTarjeta = ((Vector)PagosCollection_detalles.get(l)).get(2).toString();
				if(tipoPago.equals("PT") && identificacionPropietarioTarjeta.equals(getIdentificacionDuenoTarjeta())) {
					SpiritAlert.createAlert("Tarjeta de afiliación utilizada previamente en esta transacción", SpiritAlert.WARNING);
					monto = BigDecimal.ZERO;
					setMonto(monto);
					return false;
				}
			}
		}
		return true;
	}

	ActionListener oActionListenerBotonCancelar = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			aceptar = false;
			dispose();
		}
	};

	ActionListener oActionListenerBotonConvertir = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			BigDecimal deuda = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));
			String cantidadAplicar= getTxtCantidadAplicar().getText();
			String referencia=getTxtPuntosDinero().getText();
			String puntos="0";
			String dinero="0";

			if(cantidadAplicar!=null && !cantidadAplicar.equals("") && Double.parseDouble(Utilitarios.removeDecimalFormat(cantidadAplicar)) > 0D) {
				if(referencia!=null)
				{
					int marca=referencia.indexOf("/");
					puntos=referencia.substring(0,referencia.indexOf("/"));
					dinero=referencia.substring(marca+1);

					double apPuntos= Double.parseDouble(cantidadAplicar);

					System.out.println("apPuntos-->"+apPuntos);
					System.out.println("apPuntos2 -->"+Double.parseDouble(cantidadAplicar));


					if(acumularPuntosDinero.getValor().equals("P") && !esMultiplo(new Integer(cantidadAplicar).intValue(),new Integer(puntos).intValue())) {	
						SpiritAlert.createAlert("Debe escribir un numero múltiplo de "+puntos,SpiritAlert.INFORMATION);
					}
					else{

						BigDecimal pAplicar = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtCantidadAplicar().getText()));
						BigDecimal pDisponibles = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtPuntosDisponibles().getText()));
						pDisponibles=pDisponibles.subtract(pAplicar);
						if(pDisponibles.signum()== -1)
						{
							SpiritAlert.createAlert("Debe aplicar un valor menor o igual al acumulado disponible",SpiritAlert.INFORMATION);
						}
						else{
							if (acumularPuntosDinero.getValor().equals("P")) {
								BigDecimal calcular=pAplicar.multiply(new BigDecimal(dinero));
								calcular=calcular.divide(new BigDecimal(puntos));
								getTxtEquivalenteDinero().setText(calcular.toString());
							} else {
								getTxtEquivalenteDinero().setText(pAplicar.toString());
							}
							monto = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtEquivalenteDinero().getText()));
							deuda = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtDeuda().getText()));
							balance = deuda.subtract(monto);	
							if (balance.signum() == -1) {
								SpiritAlert.createAlert("Monto aplicado sobrepasa el valor su deuda. Aplicar un monto menor. ",SpiritAlert.INFORMATION);
								getTxtBalance().setText("0.00");
								getTxtEquivalenteDinero().setText("0.00");
							}  
							else{
								getTxtBalance().setText(balance.toString());							
								getBtnAceptar().setEnabled(true);		
								getBtnAplicar().setEnabled(true);
								setMonto(monto);									
								setAplicarPuntosDinero(pAplicar);
							}
						}	
					}
					setBalance(balance);				
				}
			} else {
				SpiritAlert.createAlert("Debe ingresar el monto que desea utilizar.",SpiritAlert.INFORMATION);				
			}


		}
	};


	public void setear_datos() {		
		if (getChkReferido().isSelected()) 	setReferido("S");
		if (getChkPropietario().isSelected())  	setReferido("N");
		setBalance(balance);						
		this.dispose();
	}

	public void setCajaAbiertaId() {
		Long caja_id = new Long("0");
		Long usuario_id = ((UsuarioIf) Parametros.getUsuarioIf()).getId();
		Map aMap = new HashMap();
		aMap.clear();
		aMap.put("usuarioId", usuario_id);
		aMap.put("estado", "A");
		aMap.put("fechafin", null);
		cajaAbiertaID = new Long("0");
		Iterator cajavalorIt;
		try {
			cajavalorIt = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();
			if (cajavalorIt.hasNext()) {
				CajaSesionIf valor_actual = (CajaSesionIf) cajavalorIt.next();
				BigDecimal valor = valor_actual.getValorInicial();
				caja_id = valor_actual.getId();
			} else {
				SpiritAlert.createAlert("Debe tener una caja abierta", SpiritAlert.INFORMATION);
				aceptar = false;
				dispose();
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cajaAbiertaID = caja_id;
	}

	public DecimalFormat getFormatoDecimal() {
		return formatoDecimal;
	}

	public void setFormatoDecimal(DecimalFormat formatoDecimal) {
		this.formatoDecimal = formatoDecimal;
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

	public String getUsadorporClienteOficinaId() {
		return usadorporClienteOficinaId;
	}

	public void setUsadorporClienteOficinaId(String usadorporClienteOficinaId) {
		this.usadorporClienteOficinaId = usadorporClienteOficinaId;
	}

	public BigDecimal getDeuda() {
		return deuda;
	}

	public void setDeuda(BigDecimal deuda) {
		this.deuda = deuda;
	}

	public String getReferido() {
		return referido;
	}

	public void setReferido(String referido) {
		this.referido = referido;
	}

	public String getTipoDuenoTarjeta() {
		return tipoDuenoTarjeta;
	}

	public void setTipoDuenoTarjeta(String tipoDuenoTarjeta) {
		this.tipoDuenoTarjeta = tipoDuenoTarjeta;
	}

	public String getIdentificacionDuenoTarjeta() {
		return identificacionDuenoTarjeta;
	}

	public void setIdentificacionDuenoTarjeta(String identificacionDuenoTarjeta) {
		this.identificacionDuenoTarjeta = identificacionDuenoTarjeta;
	}

	public String getCedulaDuenoTarjeta() {
		return cedulaDuenoTarjeta;
	}

	public void setCedulaDuenoTarjeta(String cedulaDuenoTarjeta) {
		this.cedulaDuenoTarjeta = cedulaDuenoTarjeta;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getDescuentoTipoTarjeta() {
		return descuentoTipoTarjeta;
	}

	public void setDescuentoTipoTarjeta(String descuentoTipoTarjeta) {
		this.descuentoTipoTarjeta = descuentoTipoTarjeta;
	}

	public BigDecimal getPuntosDinero() {
		return puntosDinero;
	}

	public void setPuntosDinero(BigDecimal puntosDinero) {
		this.puntosDinero = puntosDinero;
	}

	public BigDecimal getPuntosDineroAcumulado() {
		return puntosDineroAcumulado;
	}

	public void setPuntosDineroAcumulado(BigDecimal puntosDineroAcumulado) {
		this.puntosDineroAcumulado = puntosDineroAcumulado;
	}

	public BigDecimal getPuntosDineroUtilizado() {
		return puntosDineroUtilizado;
	}

	public void setPuntosDineroUtilizado(BigDecimal puntosDineroUtilizado) {
		this.puntosDineroUtilizado = puntosDineroUtilizado;
	}

	public BigDecimal getPuntosDineroDisponible() {
		return puntosDineroDisponible;
	}

	public void setPuntosDineroDisponible(BigDecimal puntosDineroDisponible) {
		this.puntosDineroDisponible = puntosDineroDisponible;
	}

	public BigDecimal getPuntosDineroComprometido() {
		return puntosDineroComprometido;
	}

	public void setPuntosDineroComprometido(BigDecimal puntosDineroComprometido) {
		this.puntosDineroComprometido = puntosDineroComprometido;
	}

	public BigDecimal getAplicarPuntosDinero() {
		return aplicarPuntosDinero;
	}

	public void setAplicarPuntosDinero(BigDecimal aplicarPuntosDinero) {
		this.aplicarPuntosDinero = aplicarPuntosDinero;
	}

	public ParametroEmpresaIf getAcumularPuntosDinero() {
		return acumularPuntosDinero;
	}

	public void setAcumularPuntosDinero(ParametroEmpresaIf acumularPuntosDinero) {
		this.acumularPuntosDinero = acumularPuntosDinero;
	}
}
