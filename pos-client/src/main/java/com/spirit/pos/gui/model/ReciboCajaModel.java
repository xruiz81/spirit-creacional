package com.spirit.pos.gui.model;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.spirit.client.SpiritAlert;
import com.spirit.pos.gui.panel.JDReciboCaja;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class ReciboCajaModel extends JDReciboCaja {
	private static final int MAX_LONGITUD_TOTAL = 22;
	private static double totalReciboCaja = 0D;
	private static String referencia = null;
	
	public ReciboCajaModel(Frame owner) {
		super(owner);
		initKeyListeners();
		initListeners();
		int width = 300;
		int height = 250;
		setSize(width, height);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		setLocation(x, y);
		pack();
		//setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
	
	private void initKeyListeners() {
		getTxtTotal().addKeyListener(new TextChecker(MAX_LONGITUD_TOTAL));
		getTxtTotal().addKeyListener(new NumberTextFieldDecimal());
	}
	
	private void initListeners() {
		getOkButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					aceptar();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		});
		
		getTxtReferencia().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				if (ev.getKeyChar() == KeyEvent.VK_ENTER) {
					getTxtTotal().grabFocus();
				}
			}
		});
		
		getTxtTotal().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				if (ev.getKeyChar() == KeyEvent.VK_ENTER) {
					try {
						aceptar();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	private void aceptar() throws CloneNotSupportedException {
		if (validateFields()) {
			referencia = getTxtReferencia().getText();
			double total = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtTotal().getText()));
			totalReciboCaja = total;
			this.dispose();
		}
	}
	
	private boolean validateFields() {
		if (getTxtReferencia().getText() == null || getTxtReferencia().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar la referencia", SpiritAlert.WARNING);
			getTxtReferencia().grabFocus();
			return false;
		}
		
		if (getTxtTotal().getText() == null || getTxtTotal().getText().equals("")) {
			SpiritAlert.createAlert("Debe ingresar el total del recibo de caja", SpiritAlert.WARNING);
			getTxtTotal().grabFocus();
			return false;
		}
		
		double total = Double.parseDouble(Utilitarios.removeDecimalFormat(getTxtTotal().getText()));
		if (total < 0D) {
			SpiritAlert.createAlert("Total del recibo de caja debe ser positivo", SpiritAlert.WARNING);
			getTxtTotal().grabFocus();
			return false;
		}
		
		return true;
	}

	public static double getTotalReciboCaja() {
		return totalReciboCaja;
	}

	public static void setTotalReciboCaja(double totalReciboCaja) {
		ReciboCajaModel.totalReciboCaja = totalReciboCaja;
	}

	public static String getReferencia() {
		return referencia;
	}

	public static void setReferencia(String referencia) {
		ReciboCajaModel.referencia = referencia;
	}
}