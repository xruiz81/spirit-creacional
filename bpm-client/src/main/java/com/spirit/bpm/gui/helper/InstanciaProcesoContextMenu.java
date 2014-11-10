package com.spirit.bpm.gui.helper;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.spirit.bpm.process.elements.InstanciaProceso;
import com.spirit.bpm.process.elemets.state.EnumInstanceState;
import com.spirit.client.model.SpiritResourceManager;

public class InstanciaProcesoContextMenu extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	private JMenuItem itemEliminar = new JMenuItem("Eliminar");
	private JMenuItem itemCancelar = new JMenuItem("Cancelar");
	private JMenuItem itemTerminar = new JMenuItem("Terminar");

	public InstanciaProcesoContextMenu() {
		itemEliminar.setIcon(SpiritResourceManager
				.getImageIcon("/images/bpm/iniciar16.png"));
		itemCancelar.setIcon(SpiritResourceManager
				.getImageIcon("/images/bpm/cancelada16.png"));
		itemTerminar.setIcon(SpiritResourceManager
				.getImageIcon("/images/bpm/terminar16.png"));

		add(itemEliminar);
		add(itemCancelar);
		add(itemTerminar);
	}

	public void setListener(ActionListener actionListener) {
		itemEliminar.addActionListener(actionListener);
		itemCancelar.addActionListener(actionListener);
		itemTerminar.addActionListener(actionListener);
	}

	private void habilitar(boolean visible) {
		itemEliminar.setVisible(visible);
		itemCancelar.setVisible(visible);
		itemTerminar.setVisible(visible);
	}

	public void habilitarOpcionesAdmin(InstanciaProceso instanciaProceso) {
		EnumInstanceState estado = instanciaProceso.getEstado();
		habilitar(false);
		if (EnumInstanceState.INICIADA.equals(estado)) {
			itemCancelar.setVisible(true);
		}
	}

}
