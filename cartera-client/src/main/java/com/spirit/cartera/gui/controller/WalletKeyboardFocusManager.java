package com.spirit.cartera.gui.controller;

import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.spirit.client.ActivePanel;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.SpiritModelImpl;


public class WalletKeyboardFocusManager {
	public static void initKeyboardFocusManager() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("permanentFocusOwner", new PropertyChangeListener() {
			public void propertyChange(final PropertyChangeEvent e) {
				if (ActivePanel.getSingleton().getActivePanel() != SpiritConstants.getNullValue() && ((SpiritModelImpl) ActivePanel.getSingleton().getActivePanel()).getName().equals("Cartera")) {
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
					//if (e.getNewValue() instanceof JFormattedTextField && (((JFormattedTextField) e.getNewValue()).getName() == SpiritConstants.getNullValue() || !((JFormattedTextField) e.getNewValue()).getName().equals("txtEmissionDate") || ((JFormattedTextField) e.getNewValue()).getName().equals("txtComment") || (((JFormattedTextField) e.getNewValue()).getName().equals("txtLegalName") && (((JFormattedTextField) e.getOldValue()).getName().equals("txtComment") || ((JButton) e.getOldValue()).getName().equals("btnSearchBusinessOperator"))))) {
					if (e.getNewValue() instanceof JFormattedTextField && (((JFormattedTextField) e.getNewValue()).getName() == SpiritConstants.getNullValue() || (!((JFormattedTextField) e.getNewValue()).getName().equals("txtEmissionDate") && !((JFormattedTextField) e.getNewValue()).getName().equals("txtLegalName") && !((JFormattedTextField) e.getNewValue()).getName().equals("txtInitialDate") && !((JFormattedTextField) e.getNewValue()).getName().equals("txtFinalDate")))) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								JFormattedTextField textField = (JFormattedTextField) e.getNewValue();
								textField.selectAll();
							}
						});
						//} else if (e.getNewValue() instanceof JTextField && (((JTextField) e.getNewValue()).getName() == SpiritConstants.getNullValue() || !((JTextField) e.getNewValue()).getName().equals("txtEmissionDate") || ((JTextField) e.getNewValue()).getName().equals("txtComment") || (((JTextField) e.getNewValue()).getName().equals("txtLegalName") && (((JTextField) e.getOldValue()).getName().equals("txtComment") || ((JButton) e.getOldValue()).getName().equals("btnSearchBusinessOperator"))))) {
					} else if (e.getNewValue() instanceof JTextField && (((JTextField) e.getNewValue()).getName() == SpiritConstants.getNullValue() || (!((JTextField) e.getNewValue()).getName().equals("txtEmissionDate") && !((JTextField) e.getNewValue()).getName().equals("txtLegalName") && !((JTextField) e.getNewValue()).getName().equals("txtInitialDate") && !((JTextField) e.getNewValue()).getName().equals("txtFinalDate")))) {
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
