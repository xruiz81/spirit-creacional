package com.spirit.bpm.gui.helper;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.spirit.bpm.process.elements.Proceso;
import com.spirit.bpm.process.elemets.state.EnumProcessState;
import com.spirit.client.model.SpiritResourceManager;

public class ProcesoContextMenu extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	private JMenuItem itemIniciar = new JMenuItem("Nueva instancia");
	private JMenuItem itemReasignar = new JMenuItem("Asignar");
	private JMenuItem itemTerminar = new JMenuItem("Terminar");
	private JMenuItem itemSuspender = new JMenuItem("Suspender");
	private JMenuItem itemContinuar = new JMenuItem("Continuar");
	private JMenuItem itemReanudar = new JMenuItem("Reanudar");

	public ProcesoContextMenu() {
		itemIniciar.setIcon(SpiritResourceManager
				.getImageIcon("/images/bpm/iniciar16.png"));
		itemReasignar.setIcon(SpiritResourceManager
				.getImageIcon("/images/bpm/asignar16.png"));
		itemTerminar.setIcon(SpiritResourceManager
				.getImageIcon("/images/bpm/terminar16.png"));
		itemSuspender.setIcon(SpiritResourceManager
				.getImageIcon("/images/bpm/pausa16.png"));
		itemContinuar.setIcon(SpiritResourceManager
				.getImageIcon("/images/bpm/continuar16.png"));
		itemReanudar.setIcon(SpiritResourceManager
				.getImageIcon("/images/bpm/reanudar16.png"));

		add(itemIniciar);
		add(itemReasignar);
		add(itemTerminar);
		add(itemSuspender);
		add(itemContinuar);
		add(itemReanudar);
	}

	public void setListener(ActionListener actionListener) {
		itemIniciar.addActionListener(actionListener);
		itemReasignar.addActionListener(actionListener);
		itemTerminar.addActionListener(actionListener);
		itemSuspender.addActionListener(actionListener);
		itemContinuar.addActionListener(actionListener);
		itemReanudar.addActionListener(actionListener);
	}

	private void habilitar(boolean visible) {
		itemIniciar.setVisible(visible);
		itemReasignar.setVisible(visible);
		itemTerminar.setVisible(visible);
		itemSuspender.setVisible(visible);
		itemContinuar.setVisible(visible);
		itemReanudar.setVisible(visible);
	}

	public void habilitarOpcionesAdmin(Proceso proceso) {
		EnumProcessState estado = proceso.getEstado();
		habilitar(false);
		if (EnumProcessState.HABILITADO.equals(estado)) {
			itemIniciar.setVisible(true);
		}
	}

}
