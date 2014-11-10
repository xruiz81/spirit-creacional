package com.spirit.facturacion.gui.model;

import java.awt.Frame;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.WindowConstants;
import com.spirit.facturacion.gui.panel.JDFacturaReembolsoFormato;

public class FacturaReembolsoFormatoModel extends JDFacturaReembolsoFormato {

	public FacturaReembolsoFormatoModel(Frame owner) {
		super(owner);
		
		getRbFormatoNormal().setSelected(true);
		
		initListeners();
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setVisible(true);
	}
	
	private void initListeners() {
		getBtnAceptar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				aceptar();
			}
		});		
	}
	
	public void aceptar(){
		this.dispose();
	}
}
