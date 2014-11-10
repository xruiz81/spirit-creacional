package com.spirit.contabilidad.gui.panel;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.l2fprod.common.demo.OutlookBarMain;
import com.l2fprod.common.swing.PercentLayout;
import com.spirit.client.IconButton;
import com.spirit.client.model.SpiritResourceManager;

public class JPContabilidad extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3181015946591039049L;

	IconButton botonTipoCuenta = new IconButton();

	IconButton botonAsiento = new IconButton();

	IconButton botonPlanCuenta = new IconButton();

	IconButton botonTipocuenta = new IconButton();

	IconButton botonBalance = new IconButton();

	IconButton botonSaldos = new IconButton();

	IconButton botonCuenta = new IconButton();

	/**
	 * This method initializes
	 */
	public JPContabilidad(ActionListener LoadPanelActionListenerIn) {
		super();
		initialize(LoadPanelActionListenerIn);
	}

	/**
	 * This method initializes this
	 */
	private void initialize(ActionListener LoadPanelActionListenerIn) {
		this.setSize(90, 279);
		this.setLayout(new PercentLayout(PercentLayout.VERTICAL, 0));
		this.setOpaque(false);

		botonTipoCuenta.setText("Tipo Cuenta");
		botonTipoCuenta
				.setName("com.spirit.contabilidad.gui.panel.JPTipoCuenta");
		botonTipoCuenta.setIcon(SpiritResourceManager.getImageIcon("icons/outlook-outbox.gif"));
		this.add(botonTipoCuenta);

		botonPlanCuenta.setText("Plan Cuenta");
		botonPlanCuenta
				.setName("com.spirit.contabilidad.gui.panel.JPPlanCuenta");
		botonPlanCuenta.setIcon(SpiritResourceManager.getImageIcon("icons/outlook-outbox.gif"));
		this.add(botonPlanCuenta);

		botonCuenta.setText("Cuenta");
		botonCuenta.setName("com.spirit.contabilidad.gui.panel.JPCuenta");
		botonCuenta.setIcon(SpiritResourceManager.getImageIcon("icons/outlook-outbox.gif"));
		this.add(botonCuenta);

		botonAsiento.setText("Asiento");
		botonAsiento.setName("com.spirit.contabilidad.gui.panel.JPAsiento");
		botonAsiento.setIcon(SpiritResourceManager.getImageIcon("icons/outlook-outbox.gif"));
		this.add(botonAsiento);

		botonBalance.setText("Balance");
		botonBalance.setName("com.spirit.contabilidad.gui.panel.JPBalance");
		botonBalance.setIcon(SpiritResourceManager.getImageIcon("icons/outlook-outbox.gif"));
		this.add(botonBalance);

		botonTipoCuenta.addActionListener(LoadPanelActionListenerIn);
		botonAsiento.addActionListener(LoadPanelActionListenerIn);
		botonPlanCuenta.addActionListener(LoadPanelActionListenerIn);
		botonTipocuenta.addActionListener(LoadPanelActionListenerIn);
		botonBalance.addActionListener(LoadPanelActionListenerIn);
		botonCuenta.addActionListener(LoadPanelActionListenerIn);
	}
}
