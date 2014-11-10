package com.spirit.general.gui.controller;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jidesoft.swing.JideButton;

public class CalculatorHandler implements ActionListener {
	
	public JideButton btnCalculadora;
	
	public CalculatorHandler (JideButton btnCalculadora){
		this.btnCalculadora = btnCalculadora;
	}
	
	public void actionPerformed(ActionEvent e) {
		PopupCalculadora pfc = new PopupCalculadora(); 
		pfc.setOwner(btnCalculadora);
		pfc.setAttachable(false);

        if(pfc.isPopupVisible()) {
            pfc.hidePopup();
        }
        else {
            pfc.showPopup((Toolkit.getDefaultToolkit().getScreenSize().width - 400) / 2,(Toolkit.getDefaultToolkit().getScreenSize().height - 450) / 2);
        }

	}
}