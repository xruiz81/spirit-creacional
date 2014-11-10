package com.spirit.inventario.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;

import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.gui.panel.JPFixInventario;

public class FixInventarioModel extends JPFixInventario {

	public FixInventarioModel() {
		getBtnRegenerarCodigos().addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				SessionServiceLocator.getGenericoSessionService().regenerarCodigoBarras();
				SpiritAlert.createAlert("Regeneracion completada con exito", SpiritAlert.INFORMATION);
			} catch (GenericBusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				SpiritAlert.createAlert("Error al regenerar codigos de Barra "+e1.getMessage(), 
						SpiritAlert.INFORMATION);
				
			}
			
		}});
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

	@Override
	public void report() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showSaveMode() {
		// TODO Auto-generated method stub

	}

}
