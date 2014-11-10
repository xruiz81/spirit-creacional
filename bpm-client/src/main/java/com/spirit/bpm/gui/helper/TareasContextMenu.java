package com.spirit.bpm.gui.helper;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.spirit.bpm.process.elements.Tarea;
import com.spirit.bpm.process.elemets.state.EnumTareaState;
import com.spirit.client.model.SpiritResourceManager;

public class TareasContextMenu extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	private JMenuItem itemIniciar = new JMenuItem("Iniciar");
	private JMenuItem itemReasignar = new JMenuItem("Asignar");
	private JMenuItem itemTerminar = new JMenuItem("Terminar");
	private JMenuItem itemSuspender = new JMenuItem("Suspender");
	private JMenuItem itemContinuar = new JMenuItem("Continuar");
	private JMenuItem itemReanudar = new JMenuItem("Reanudar");
	private JMenuItem itemComentar = new JMenuItem("Comentar");

	public TareasContextMenu() {
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

		itemComentar.setIcon(SpiritResourceManager
				.getImageIcon("/images/bpm/comentario16.png"));
		
		add(itemIniciar);
		add(itemReasignar);
		add(itemTerminar);
		add(itemSuspender);
		add(itemContinuar);
		add(itemReanudar);
		add(itemComentar);
	}

	public void setListener(ActionListener actionListener) {
		itemIniciar.addActionListener(actionListener);
		itemReasignar.addActionListener(actionListener);
		itemTerminar.addActionListener(actionListener);
		itemSuspender.addActionListener(actionListener);
		itemContinuar.addActionListener(actionListener);
		itemReanudar.addActionListener(actionListener);
		itemComentar.addActionListener(actionListener);
	}

	private void habilitar(boolean visible) {
		itemIniciar.setVisible(visible);
		itemReasignar.setVisible(visible);
		itemTerminar.setVisible(visible);
		itemSuspender.setVisible(visible);
		itemContinuar.setVisible(visible);
		itemReanudar.setVisible(visible);
		itemComentar.setVisible(visible);
	}

	public void habilitarOpcionesUser(Tarea tarea) {
		EnumTareaState estado = tarea.getEstado();
		habilitar(false);
		if (EnumTareaState.EJECUCION.equals(estado)) {
			itemTerminar.setVisible(true);
			itemSuspender.setVisible(true);
			itemContinuar.setVisible(true);
		} else if (EnumTareaState.PENDIENTE.equals(estado)) {
			itemIniciar.setVisible(true);
			itemReasignar.setVisible(true);
		} else if (EnumTareaState.SUSPENDIDA.equals(estado)) {
			itemReanudar.setVisible(true);
		}else if (EnumTareaState.TERMINADA.equals(estado)) {
			itemContinuar.setVisible(true);
			itemReanudar.setVisible(true);
		}
		itemComentar.setVisible(true);
	}

	public void habilitarOpcionesAdmin(Tarea tarea) {
		EnumTareaState estado = tarea.getEstado();
		habilitar(false);
		if (EnumTareaState.PENDIENTE.equals(estado)) {
			itemReasignar.setVisible(true);
		}
		itemComentar.setVisible(true);
	}

}
