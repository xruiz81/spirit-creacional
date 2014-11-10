package com.spirit.client.model;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public abstract class JDialogModelImpl extends JDialog {

	
	public JDialogModelImpl(Frame owner) {
		super(owner);
		
		setearEscKey();
	}
	
	public JDialogModelImpl(Dialog owner) {
		super(owner);
		setearEscKey();
	}
	
	protected void limpiarTabla(JTable tabla){
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		for (int i = tabla.getRowCount(); i > 0; --i)
			modelo.removeRow(i - 1);
		tabla.repaint();
	}
	
	//Se implementa la accion de salir con ESC
    protected void setearEscKey(){
    	ActionMap am = getRootPane().getActionMap();
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        //InputMap im1 = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        Object windowCloseKey = new Object();
        //Object windowCloseKey1 = new Object();
        KeyStroke windowCloseStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        Action windowCloseAction = new AbstractAction() {
        	private static final long serialVersionUID = -1003206414355095876L;
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        };
        im.put(windowCloseStroke, windowCloseKey);
        am.put(windowCloseKey, windowCloseAction);
    }
    
}
