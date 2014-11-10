package com.spirit.pos.gui.model;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.gui.panel.JDPagoTarjetaCredito;
import com.spirit.pos.gui.poshwimpl.DatosTarjetaMagnetica;
import com.spirit.pos.gui.poshwimpl.LectorTarjeta;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class PagoTarjetaCreditoModel extends JDPagoTarjetaCredito {

	private static final long serialVersionUID = 1L;
	BigDecimal total_pagar = new BigDecimal("0.00");
	BigDecimal total_monto = new BigDecimal("0.00");
	BigDecimal cero = new BigDecimal("0.00");

	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");

	String num_autorizacion = "";

	BigDecimal balance = new BigDecimal("0.00");
	Long cajaAbiertaID = new Long("0");

	private boolean aceptar = false;

	DatosTarjetaMagnetica datosTarjetaMagnetica = null;
	LectorTarjeta lectorTarjeta = new LectorTarjeta("MSR1");

	public PagoTarjetaCreditoModel(Frame owner, String total) {
		super(owner);
		this.total_pagar = new BigDecimal(total);
		initListeners();
		iniciarComponentes();
		initKeyListeners();
		setName("Pago Efectivo");
		setVisible(true);
		try {
			datosTarjetaMagnetica = new DatosTarjetaMagnetica();
			lectorTarjeta.leerTarjetaExpress(datosTarjetaMagnetica);
			lectorTarjeta.setTxtNumeroCuenta(getTxtNumTarjeta());
			lectorTarjeta.setTxtNombre(getTxtTitularCuenta());
			lectorTarjeta.setTxtFechaCaducidad(getTxtExpira());
			//solo para pruebas
			getBtnProcesar().setEnabled(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			getBtnProcesar().setEnabled(true);
			getBtnAceptar().setEnabled(true);
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
	}

	private void iniciarComponentes() {
		getTxtNumTarjeta().grabFocus();
		
		getTxtMonto().setText(formatoDecimal.format(total_pagar));
		getTxtTotalPagar().setText(formatoDecimal.format(total_pagar));
		getProgressBar1().setVisible(false);
		Caja_abierta_id();				
		llenarComboTipoTarjetadCredito();
	}
	
	private void llenarComboTipoTarjetadCredito(){
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement(null);
		model.addElement("Visa");
		model.addElement("Mastercard");
		model.addElement("American Express");
		model.addElement("Diners");
		getCmbTipoTarjetaCredito().setModel(model);
		
	}

	private void initListeners() {
		getBtnAceptar().addActionListener(oActionListenerBotonAceptar);
		getBtnCancelar().addActionListener(oActionListenerBotonCancelar);
		getBtnProcesar().addActionListener(oActionListenerBotonAutorizacion);
		addWindowListener(wl);
		getLblImagenTarjeta().addMouseListener(mlImagenTarjeta);
	}

	public void initKeyListeners() {

		getTxtCodigoAutorizacion().setEditable(false);
		getTxtNumTarjeta().addKeyListener(new TextChecker(22));
		getTxtMonto().addKeyListener(new NumberTextFieldDecimal()); 
		getTxtMonto().addKeyListener(oKeyAdapterVuelto);
		getTxtNumTarjeta().grabFocus();
	}

	WindowListener wl = new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			try {
				lectorTarjeta.cerrar();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			dispose();
		}
	};

	MouseListener mlImagenTarjeta = new MouseAdapter(){

		public void mouseClicked(MouseEvent e) {
			
			synchronized (this) {
				try {
					lectorTarjeta.cerrar();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				try {
					datosTarjetaMagnetica = new DatosTarjetaMagnetica();
					getTxtNumTarjeta().setText("");
					getTxtTitularCuenta().setText("");
					getTxtExpira().setText("");
					lectorTarjeta.leerTarjetaExpress(datosTarjetaMagnetica);
				} catch (Exception e1) {
					e1.printStackTrace();
					getBtnProcesar().setEnabled(false);
					getBtnAceptar().setEnabled(false);
					SpiritAlert.createAlert(e1.getMessage(), SpiritAlert.ERROR);
				}
			}
		}		
		
	};
	
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
	};

	ActionListener oActionListenerBotonAceptar = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {

			System.out.println("coigo autoirazion"
					+ getTxtCodigoAutorizacion().getText() + "<---");

			System.out.println("pregunta->");

			if (getTxtCodigoAutorizacion() == null
					|| getTxtCodigoAutorizacion().getText().equals("")) {
				SpiritAlert
						.createAlert(
								"Debe de tener un CODIGO de AUTORIZACION para procesar este pago.",
								SpiritAlert.INFORMATION);
			} else {
				aceptar = true;
				setear_datos();
			}

		}
	};

	ActionListener oActionListenerBotonCancelar = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			aceptar = false;
			dispose();
		}

	};

	ActionListener oActionListenerBotonAutorizacion = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {

			getProgressBar1().setVisible(true);

			getProgressBar1().setValue(25);

			getTxtCodigoAutorizacion().setText("34562-587744-63255");

		}

	};

	public void setear_datos() {
		String valorpagado = Utilitarios.removeDecimalFormat(getTxtMonto().getText());
		String deuda = Utilitarios.removeDecimalFormat(getTxtBalance().getText());
		String num_autorizacion = getTxtCodigoAutorizacion().getText();

		BigDecimal monto = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtMonto().getText()));
		BigDecimal deuda2 = new BigDecimal(Utilitarios.removeDecimalFormat(getTxtTotalPagar().getText()));
		BigDecimal balance = deuda2.subtract(monto);

		setBalance(balance);

		getProgressBar1().setValue(100);

		setTotal_monto(new BigDecimal(valorpagado));
		setNum_autorizacion(num_autorizacion);

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

			} else
				SpiritAlert.createAlert("Debe tener una caja abierta",
						SpiritAlert.INFORMATION);
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

	public String getNum_autorizacion() {
		return num_autorizacion;
	}

	public void setNum_autorizacion(String num_autorizacion) {
		this.num_autorizacion = num_autorizacion;
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

}
