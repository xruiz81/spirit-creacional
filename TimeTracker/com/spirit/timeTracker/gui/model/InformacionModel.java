package com.spirit.timeTracker.gui.model;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.jgoodies.forms.layout.CellConstraints;
import com.spirit.timeTracker.componentes.PanelInformacion;
import com.spirit.timeTracker.gui.main.JDInformacion;

public class InformacionModel extends JDInformacion {

	private static final long serialVersionUID = -4203955368432707001L;

	public InformacionModel(PanelInformacion panelInformacion,String titulo) {
		super(Utiles.timeTrackerVentana);
		setearEscKey();
		setSize(550, 210);
		CellConstraints cc = new CellConstraints();
		getContentPane().add(panelInformacion,cc.xy(1, 1));
		setVisual(titulo);
		
	}
	
	public InformacionModel(PanelInformacion panelInformacion,String titulo,int x, int y) {
		super(Utiles.timeTrackerVentana);
		setearEscKey();
		setSize(x, y);
		CellConstraints cc = new CellConstraints();
		getContentPane().add(panelInformacion,cc.xy(1, 1));
		setVisual(titulo);
	}
	
	private void setVisual(String titutlo){
		setLocation(
        		(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 3, 
        		(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3
        		);
		setModal(true);
		setVisible(true);
	}
	
	private void setearEscKey(){
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
