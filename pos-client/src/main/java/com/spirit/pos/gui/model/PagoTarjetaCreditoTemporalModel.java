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
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.entity.TarjetaCreditoIf;
import com.spirit.pos.gui.panel.JDPagoTarjetaCredito_Temporal;
import com.spirit.pos.gui.poshwimpl.DatosTarjetaMagnetica;
import com.spirit.pos.gui.poshwimpl.LectorTarjeta;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;
import com.spirit.util.ValidarIdentificacion;

public class PagoTarjetaCreditoTemporalModel extends
JDPagoTarjetaCredito_Temporal {

	private static final long serialVersionUID = 1L;
	BigDecimal total_pagar = new BigDecimal("0.00");
	BigDecimal total_monto = new BigDecimal("0.00");
	BigDecimal cero = new BigDecimal("0.00");
	String nombre_tarjeta = "";
	String nombre_cliente = "";
	String cedula_cliente = "";
	String telefono = "";

	String codAutorizacion = "";
	String noReferencia = "";
	String noVoucher = "";

	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private static final int MAX_LONGITUD_REFERENCIAS = 11;

	BigDecimal balance = new BigDecimal("0.00");
	Long cajaAbiertaID = new Long("0");
	String cedula = "";

	private boolean aceptar = false;

	DatosTarjetaMagnetica datosTarjetaMagnetica = null;
	LectorTarjeta lectorTarjeta = new LectorTarjeta("MSR1");

	public PagoTarjetaCreditoTemporalModel(Frame owner, String total,
			String cedula1) {
		super(owner);
		this.total_pagar = new BigDecimal(total);
		setName("Tarjeta de Crédito");
		cedula = cedula1;
		iniciarComponentes();
		initKeyListeners();

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				getCmbTipoTarjetaCredito().requestFocus();
			}

			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

	}

	private void iniciarComponentes() {
		Caja_abierta_id();
		getTxtMonto().setText(formatoDecimal.format(total_pagar));
		getTxtTotalPagar().setText(formatoDecimal.format(total_pagar));
		llenarComboTipoTarjetadCredito();
		getBtnAceptar().addActionListener(oActionListenerBotonAceptar);
		getBtnCancelar().addActionListener(oActionListenerBotonCancelar);
		getChkOtroCliente().addActionListener(new checkboxCopiar());
		getChkManual().addActionListener(new checkboxManual());

		getTxtCodigoAutorizacion().setEditable(false);
		getTxtNoVoucher().setEditable(false);
		getTxtNoReferencia().setEditable(true);

		getTxtCodigoAutorizacion().addKeyListener(new TextChecker(MAX_LONGITUD_REFERENCIAS));		
		getTxtNoVoucher().addKeyListener(new TextChecker(MAX_LONGITUD_REFERENCIAS));
		getTxtNoReferencia().addKeyListener(new TextChecker(MAX_LONGITUD_REFERENCIAS));

	}

	public void initKeyListeners() {
		// getTxtCodigoAutorizacion().setEditable(true);
		getTxtMonto().addKeyListener(new TextChecker(9));
		getTxtMonto().addKeyListener(new NumberTextFieldDecimal()); 
		getTxtMonto().addKeyListener(oKeyAdapterVuelto);
		if (!cedula.equals("9999999999"))
			setearCliente(cedula);
		else
			SpiritAlert.createAlert(
					"Debe ingresar datos del dueño de la tarjeta",
					SpiritAlert.ERROR);
	}

	private class checkboxCopiar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (getChkOtroCliente().isSelected()) {
				clean_datos();
			} else {

				if (!cedula.equals("9999999999"))
					setearCliente(cedula);
				else
					SpiritAlert.createAlert(
							"Debe ingresar datos del dueño de la tarjeta",
							SpiritAlert.ERROR);
			}
		}
	}

	private class checkboxManual implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (getChkManual().isSelected()) {
				getTxtCodigoAutorizacion().setEditable(true);
				getTxtNoVoucher().setEditable(true);
				getTxtNoReferencia().setEditable(false);
				getTxtNoReferencia().setText("");
			} else {
				getTxtCodigoAutorizacion().setEditable(false);
				getTxtNoVoucher().setEditable(false);
				getTxtNoReferencia().setEditable(true);
				getTxtCodigoAutorizacion().setText("");
				getTxtNoVoucher().setText("");
			}
		}
	}

	public void clean_datos() {

		llenarComboTipoTarjetadCredito();
		getTxtTitularCuenta().setText("");
		getTxtIdentificacionCliente().setText("");
		getTxtTelefono().setText("");
		getTxtCodigoAutorizacion().setText("");
		// getTxtMonto().setText("");
	}

	private boolean setearCliente(String cedulaCliente) {
		boolean find = false;
		HashMap<String, Object> mapa = new HashMap<String, Object>();
		mapa.clear();
		ClienteIf clienteIf;
		Iterator clienteIt;
		getTxtIdentificacionCliente().setText(cedulaCliente);
		try {
			clienteIt = SessionServiceLocator.getClienteSessionService().findClienteByIdentificacion(
					cedulaCliente).iterator();
			if (clienteIt.hasNext()) {
				clienteIf = (ClienteIf) clienteIt.next();
				getTxtTitularCuenta().setText(clienteIf.getNombreLegal());
				find = true;
				ClienteOficinaIf clienteOficinaIf;
				clienteOficinaIf = null;
				try {
					Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService()
					.findClienteOficinaByClienteId(clienteIf.getId());
					if (oficinas.size() == 1) {
						clienteOficinaIf = oficinas.iterator().next();
						String telefono = clienteOficinaIf.getTelefono();
						String email = clienteOficinaIf.getEmail();
						if (telefono == null)
							telefono = "";
						if (email == null)
							email = "";

						getTxtTelefono().setText(telefono);

					}
				} catch (Exception e) {
					e.printStackTrace();
					SpiritAlert
					.createAlert(
							"Se ha producido un error al consultar la oficina del cliente",
							SpiritAlert.ERROR);
				}
			}
		} catch (GenericBusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return find;
	}

	private void llenarComboTipoTarjetadCredito() {
		/* model = new DefaultComboBoxModel();
		model.addElement(null);
		model.addElement("Visa");
		model.addElement("Mastercard");
		model.addElement("American Express");
		model.addElement("Diners");
		getCmbTipoTarjetaCredito().setModel(model);

		 */


		SpiritComboBoxModel cmbModelTarjetaC;
		try {
			cmbModelTarjetaC = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getTarjetaCreditoSesionSessionService().getTarjetaCreditoList());
			getCmbTipoTarjetaCredito().setModel(cmbModelTarjetaC);	
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

	KeyListener oKeyAdapterVuelto = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			BigDecimal monto = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));
			BigDecimal deuda = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtTotalPagar().getText()));
			BigDecimal balance = deuda.subtract(monto);
			if (balance.signum() == -1) {
				SpiritAlert
				.createAlert(
						"Debe escribir un monto menor o igual al valor de la Deuda ",
						SpiritAlert.INFORMATION);
				getTxtBalance().setText("0.00");
				getTxtMonto().setText("0.00");

			} else {
				getTxtBalance().setText(balance.toString());
			}
		}


		public void keyTyped(KeyEvent e) {	
			if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB) {
				if (validar_datos()) {
					setear_datos();
					aceptar = true;
				}
			}  
		}	

	};

	ActionListener oActionListenerBotonAceptar = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
 
			if (validar_datos()) {
				setear_datos();
				aceptar = true;

			}

		}
	};

	public boolean validar_datos() {
		boolean llenos = true;
		
		 

		if (getTxtTelefono() == null || getTxtTelefono().getText().equals("")) {
			llenos = false;
			SpiritAlert.createAlert("Debe ingresar el teléfono del cliente",
					SpiritAlert.INFORMATION);
		}
		if (getTxtTitularCuenta() == null
				|| getTxtTitularCuenta().getText().equals("")) {
			llenos = false;
			SpiritAlert.createAlert("Debe ingresar el nombre del cliente",
					SpiritAlert.INFORMATION);
		}
		if (getTxtIdentificacionCliente() == null
				|| getTxtIdentificacionCliente().getText().equals("")) {
			llenos = false;
			SpiritAlert.createAlert("Debe ingresar el número de cédula",
					SpiritAlert.INFORMATION);
		}
		if (getTxtMonto() == null || getTxtMonto().getText().equals("")) {
			llenos = false;
			SpiritAlert.createAlert("Ingrese el monto ",
					SpiritAlert.INFORMATION);
		}

		if (!ValidarIdentificacion.esNumeroIdentificacionValido("CE", getTxtIdentificacionCliente().getText())) {
			SpiritAlert.createAlert("La identificación ingresada no es válida!!!", SpiritAlert.WARNING);
			getTxtIdentificacionCliente().grabFocus();				
			return false;
		}


//		String nombre_tarjeta = ((String) this.getCmbTipoTarjetaCredito().getSelectedItem());

		
		 if (getCmbTipoTarjetaCredito().getSelectedItem() == null )
				 {
			 llenos = false;
			 SpiritAlert.createAlert("Debe escoger el nombre de su tarjeta de crédito",SpiritAlert.INFORMATION);
				 }
		 else{
		TarjetaCreditoIf tarjetaCreditoIf = ((TarjetaCreditoIf) this.getCmbTipoTarjetaCredito().getSelectedItem());

		String nombre_tarjeta = tarjetaCreditoIf.getNombre();



		if (nombre_tarjeta == null) {
			llenos = false;
			SpiritAlert.createAlert("Debe escoger el nombre de su tarjeta de crédito",SpiritAlert.INFORMATION);
			getCmbTipoTarjetaCredito().grabFocus();
		}
		 }

		if (getChkManual().isSelected()) {
			getTxtNoReferencia().setText("");
			getTxtNoReferencia().setEnabled(false);

			String noVoucher = getTxtNoVoucher().getText();
			String codAutor = getTxtCodigoAutorizacion().getText();

			if (noVoucher == null)
				noVoucher = "";
			if (codAutor == null)
				codAutor = "";

			if (noVoucher.equals("") || codAutor.equals("")) {
				llenos = false;
				SpiritAlert
				.createAlert(
						"Deben estar llenos los campos de No. Voucher y Cod. Autorización",
						SpiritAlert.INFORMATION);
				getTxtNoVoucher().grabFocus();
			}

		} else {
			getTxtCodigoAutorizacion().setText("");
			getTxtNoVoucher().setText("");
			getTxtCodigoAutorizacion().setEnabled(true);
			getTxtNoVoucher().setEnabled(true);

			getTxtNoReferencia().setEnabled(true);

			String noReferencia = getTxtNoReferencia().getText();
			if (noReferencia == null || noReferencia.equals("")) {
				llenos = false;
				SpiritAlert.createAlert(
						"Debe tener lleno el campo de Referencia",
						SpiritAlert.INFORMATION);
				getTxtNoReferencia().grabFocus();
			}

		}

		return llenos;
	}

	ActionListener oActionListenerBotonCancelar = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			aceptar = false;
			dispose();

		}

	};

	public void setear_datos() {
		String valorpagado = Utilitarios.removeDecimalFormat(getTxtMonto().getText());
		String codAut = getTxtCodigoAutorizacion().getText();
		BigDecimal monto = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));
		BigDecimal deuda2 = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtTotalPagar().getText()));
		BigDecimal balance = deuda2.subtract(monto);

		setBalance(balance);
		setTotal_monto(new BigDecimal(valorpagado));

		String noVo = getTxtNoVoucher().getText();
		String noRefer = getTxtNoReferencia().getText();

		setCodAutorizacion(codAut);
		setNoVoucher(noVo);
		setNoReferencia(noRefer);




		TarjetaCreditoIf tarjetaCreditoIf = ((TarjetaCreditoIf) this.getCmbTipoTarjetaCredito().getSelectedItem());

		String nombre_tarjeta = tarjetaCreditoIf.getNombre();
		//((String) this.getCmbTipoTarjetaCredito().getSelectedItem());

		setNombre_tarjeta(nombre_tarjeta);
		setNombre_cliente(getTxtTitularCuenta().getText());
		setTelefono(getTxtTelefono().getText());
		setCedula_cliente(getTxtIdentificacionCliente().getText());
		this.dispose();
	}

	public void Caja_abierta_id() {
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
			cajavalorIt = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(
					aMap).iterator();
			if (cajavalorIt.hasNext()) {
				CajaSesionIf valor_actual = (CajaSesionIf) cajavalorIt.next();
				BigDecimal valor = valor_actual.getValorInicial();
				caja_id = valor_actual.getId();

			} else {
				SpiritAlert.createAlert("Debe tener una caja abierta",
						SpiritAlert.INFORMATION);
				aceptar = false;
				dispose();

			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cajaAbiertaID = caja_id;

	}

 
	public BigDecimal getTotal_pagar() {
		return total_pagar;
	}

	public void setTotal_pagar(BigDecimal total_pagar) {
		this.total_pagar = total_pagar;
	}

	public BigDecimal getTotal_monto() {
		return total_monto;
	}

	public void setTotal_monto(BigDecimal total_monto) {
		this.total_monto = total_monto;
	}

	public BigDecimal getCero() {
		return cero;
	}

	public void setCero(BigDecimal cero) {
		this.cero = cero;
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

	public String getNombre_tarjeta() {
		return nombre_tarjeta;
	}

	public void setNombre_tarjeta(String nombre_tarjeta) {
		this.nombre_tarjeta = nombre_tarjeta;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getCedula_cliente() {
		return cedula_cliente;
	}

	public void setCedula_cliente(String cedula_cliente) {
		this.cedula_cliente = cedula_cliente;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCodAutorizacion() {
		return codAutorizacion;
	}

	public void setCodAutorizacion(String codAutorizacion) {
		this.codAutorizacion = codAutorizacion;
	}

	public String getNoReferencia() {
		return noReferencia;
	}

	public void setNoReferencia(String noReferencia) {
		this.noReferencia = noReferencia;
	}

	public String getNoVoucher() {
		return noVoucher;
	}

	public void setNoVoucher(String noVoucher) {
		this.noVoucher = noVoucher;
	}

}
