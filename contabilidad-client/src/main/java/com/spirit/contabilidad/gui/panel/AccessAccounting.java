package com.spirit.contabilidad.gui.panel;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.l2fprod.common.swing.PercentLayout;
import com.spirit.client.IconButton;
import com.spirit.client.model.SpiritResourceManager;

public class AccessAccounting extends JPanel {



	/**
	 * 
	 */
	private static final long serialVersionUID = 3724570654775811637L;

	IconButton botonEmpresa = new IconButton();

	IconButton botonAsiento = new IconButton();

	IconButton botonPlanCuenta = new IconButton();

	IconButton botonTipocuenta = new IconButton();

	IconButton botonBalance = new IconButton();

	IconButton botonSaldos = new IconButton();

	IconButton botonCuenta = new IconButton();

	/**
	 * This method initializes
	 */
	public AccessAccounting(ActionListener BotonFrecuentesActionListener) {
		super();
		initialize(BotonFrecuentesActionListener);
	}

	/**
	 * This method initializes this
	 */
	private void initialize(ActionListener BotonFrecuentesActionListener) {
		this.setSize(90, 279);
		this.setLayout(new PercentLayout(PercentLayout.VERTICAL, 0));
		this.setOpaque(false);
		botonEmpresa.setText("Empresa");
		botonEmpresa.setName("com.spirit.general.gui.model.EmpresaModel");
		botonEmpresa.setIcon(SpiritResourceManager.getImageIcon("icons/outlook-inbox.gif"));
		this.add(botonEmpresa);

		botonPlanCuenta.setText("Plan Cuenta");
		botonPlanCuenta
				.setName("com.spirit.contabilidad.gui.model.PlanCuentaModel");
		botonPlanCuenta.setIcon(SpiritResourceManager.getImageIcon("icons/outlook-outbox.gif"));
		this.add(botonPlanCuenta);

		botonCuenta.setText("Cuenta");
		botonCuenta.setName("com.spirit.contabilidad.gui.model.CuentaModel");
		botonCuenta.setIcon(SpiritResourceManager.getImageIcon("icons/outlook-outbox.gif"));
		this.add(botonCuenta);

		botonAsiento.setText("Asiento");
		botonAsiento
				.setName("com.spirit.contabilidad.gui.model.AsientoModel");
		botonAsiento.setIcon(SpiritResourceManager.getImageIcon("icons/outlook-outbox.gif"));
		this.add(botonAsiento);

		botonBalance.setText("Balance");
		botonBalance
				.setName("com.spirit.contabilidad.gui.model.BalanceModel");
		botonBalance.setIcon(SpiritResourceManager.getImageIcon("icons/outlook-outbox.gif"));
		this.add(botonBalance);

		botonEmpresa.addActionListener(BotonFrecuentesActionListener);
		botonAsiento.addActionListener(BotonFrecuentesActionListener);
		botonPlanCuenta.addActionListener(BotonFrecuentesActionListener);
		botonTipocuenta.addActionListener(BotonFrecuentesActionListener);
		botonBalance.addActionListener(BotonFrecuentesActionListener);
		botonCuenta.addActionListener(BotonFrecuentesActionListener);
	}
} 
