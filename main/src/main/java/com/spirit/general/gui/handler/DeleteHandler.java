package com.spirit.general.gui.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.spirit.client.ActivePanel;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritModel;

public class DeleteHandler implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		SpiritModel panel = (SpiritModel) ActivePanel.getSingleton().getActivePanel();
		
		if (panel != null) {
    		if (panel.usingDeleteHandler()) {
    			if (panel.getMode() == SpiritMode.UPDATE_MODE) {
    				String si = "Si"; 
    				String no = "No"; 
    				Object[] options ={si,no}; 
    				int opcion = JOptionPane.showOptionDialog(null, "Está seguro que desea eliminar el registro?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
    				if(opcion == JOptionPane.YES_OPTION){
    					panel.delete();
    					return;
    				}
    			} else
    				SpiritAlert.createAlert("Seleccione primero el registro a eliminar", SpiritAlert.INFORMATION);
    		} else {
    			panel.delete();
    			return;
    		}
		} else
    		SpiritAlert.createAlert("No está activo ningún panel", SpiritAlert.WARNING);
	}
}
