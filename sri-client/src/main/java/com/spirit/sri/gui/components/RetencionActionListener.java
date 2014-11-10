package com.spirit.sri.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.sri.gui.model.RetencionPopUpModel;
import com.spirit.sri.reoc.DetalleCompras;

public class RetencionActionListener implements ActionListener{

	DetalleCompras detalle = null;
	
	public void actionPerformed(ActionEvent e) {
		if (detalle!=null){
			new RetencionPopUpModel(Parametros.getMainFrame(),detalle);
		} else
			SpiritAlert.createAlert("Ning\u00fan detalle seleccionado", SpiritAlert.WARNING);
	}

	public DetalleCompras getDetalle() {
		return detalle;
	}

	public void setDetalle(DetalleCompras detalle) {
		this.detalle = detalle;
	}
}
