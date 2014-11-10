package com.spirit.sri.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.table.DefaultTableModel;

import com.spirit.client.Parametros;
import com.spirit.sri.at.DetalleVentas;
import com.spirit.sri.gui.model.AirPopUpModel;
import com.spirit.sri.reoc.DetalleCompras;

public class AirPopupActionListener implements ActionListener{
	
	DefaultTableModel modelo = null;
	AirPopUpModel airPopup = null;
	boolean modificar = true;
	Collection porcentajesAir = null;
	
	DetalleVentas detalleVenta = null;
	DetalleCompras detalleCompra = null;
		
	public AirPopupActionListener(boolean modificar){
		this.modificar = modificar;
	}
	
	public void actionPerformed(ActionEvent e) {
		if ( detalleVenta != null )
			airPopup = new AirPopUpModel(Parametros.getMainFrame(),modificar,detalleVenta,porcentajesAir);
		else
			airPopup = new AirPopUpModel(Parametros.getMainFrame(),modificar,detalleCompra,porcentajesAir);
	}

	public Collection getPorcentajesAir() {
		return porcentajesAir;
	}

	public void setPorcentajesAir(Collection porcentajesAir) {
		this.porcentajesAir = porcentajesAir;
	}

	public DetalleVentas getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(DetalleVentas detalleVenta) {
		this.detalleVenta = detalleVenta;
	}

	public DetalleCompras getDetalleCompra() {
		return detalleCompra;
	}

	public void setDetalleCompra(DetalleCompras detalleCompra) {
		this.detalleCompra = detalleCompra;
	}

	
}
