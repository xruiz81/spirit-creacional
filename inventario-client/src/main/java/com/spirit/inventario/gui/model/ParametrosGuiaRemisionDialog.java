package com.spirit.inventario.gui.model;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;

import com.spirit.inventario.gui.panel.JDParametrosGuiaRemision;

public class ParametrosGuiaRemisionDialog extends JDParametrosGuiaRemision {
	private OkCancelListener okCancelListener;

	public ParametrosGuiaRemisionDialog(Frame owner) {
		super(owner);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
		setLocation(x, y);
		pack();
	}

	public void initComponents() {
		getCmbFechaFinal2().setDate(new Date());
		getCmbFechaInicial().setDate(new Date());
		getBtnOk().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okCancelListener.ok(getParametrosIngresados());
			}
		});
		
		getBtnCancel().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okCancelListener.cancel();
			}
		});
	}

	private HashMap<String, Object> getParametrosIngresados() {
		HashMap<String, Object> mapaIngresados = new HashMap<String, Object>();
		mapaIngresados.put("fechaInicio", getCmbFechaInicial());
		mapaIngresados.put("fechaFin", getCmbFechaFinal2());
		mapaIngresados.put("comprobanteVenta", getTxtComprobanteVenta());
		mapaIngresados.put("transportistaRucCI", getTxtTransportistaRucCI());
		mapaIngresados.put("transportistaRazonSocial",
				getTxtTransportistaRazonSocial());
		mapaIngresados.put("transportistaPlacaVehiculo",
				getTxtTransportistaPlacaVehiculo());
		mapaIngresados.put("transportistaTelefono",
				getTxtTransportistaTelefono());
		return mapaIngresados;
	}

	public void setOkCancelListener(OkCancelListener okCancelListener) {
		this.okCancelListener = okCancelListener;
	}

}
