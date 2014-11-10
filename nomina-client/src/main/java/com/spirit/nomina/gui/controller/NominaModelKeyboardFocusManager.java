package com.spirit.nomina.gui.controller;

import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.spirit.client.ActivePanel;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.SpiritModelImpl;


public class NominaModelKeyboardFocusManager {
	public static void initKeyboardFocusManager() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("permanentFocusOwner", new PropertyChangeListener() {
			public void propertyChange(final PropertyChangeEvent e) {
				if (ActivePanel.getSingleton().getActivePanel() != SpiritConstants.getNullValue() && ((SpiritModelImpl) ActivePanel.getSingleton().getActivePanel()).getName().equals("Datos total ventas y horas extras")) {
					if (e.getOldValue() instanceof JFormattedTextField) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								JFormattedTextField oldTextField = (JFormattedTextField) e.getOldValue();
								oldTextField.setSelectionStart(0);
								oldTextField.setSelectionEnd(0);
							}
						});
					} else if (e.getOldValue() instanceof JTextField) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								JTextField oldTextField = (JTextField) e.getOldValue();
								oldTextField.setSelectionStart(0);
								oldTextField.setSelectionEnd(0);
							}
						});
					}
					if (e.getNewValue() instanceof JFormattedTextField && ((JFormattedTextField) e.getNewValue()).getName() == SpiritConstants.getNullValue()) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								JFormattedTextField textField = (JFormattedTextField) e.getNewValue();
								textField.selectAll();
							}
						});
					} else if (e.getNewValue() instanceof JTextField && ((JTextField) e.getNewValue()).getName() == SpiritConstants.getNullValue()) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								JTextField textField = (JTextField) e.getNewValue();
								textField.selectAll();
							}
						});
					}
				}
			}
		});
	}
}
