package com.spirit.facturacion.gui.model;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.spirit.client.SpiritAlert;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.gui.panel.JDPreimpresoAnularFactura;
import com.spirit.facturacion.session.FacturaSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;

public class PreimpresoAnularFacturaModel extends JDPreimpresoAnularFactura {

	private static final long serialVersionUID = -6033900065353505839L;
	private final int MAX_LONGITUD_PREIMPRESO = 7;
	
	private boolean anularFactura = false;
	private FacturaIf facturaIf = null;

	public PreimpresoAnularFacturaModel(Frame owner, FacturaIf facturaIf) {
		super(owner);
		this.facturaIf = facturaIf; 
		iniciar();
	}

	public PreimpresoAnularFacturaModel(Dialog owner) {
		super(owner);
		iniciar();
	}
	
	private void iniciar(){
		setearEscKey();
		initKeyListener();
		initListener();
		this.pack();
        setModal(true);
        setVisual();
	}
	
	private void setVisual(){
		setLocation(
        		(Toolkit.getDefaultToolkit().getScreenSize().width/3), //- getWidth()) / 6, 
        		(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3
        		);
		setVisible(true);
        repaint();
	}
	
	private void initKeyListener(){
		getTxtPreimpreso().addKeyListener(new TextChecker(MAX_LONGITUD_PREIMPRESO));
		getTxtPreimpreso().addKeyListener(new NumberTextField());
	}
	
	private void initListener(){
		
		getBtnCancelar().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		getTxtPreimpreso().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				actualizarPreimpreso();
			}
		});
		
		getBtnAceptar().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actualizarPreimpreso();
			}
		});
	}
	
	private void actualizarPreimpreso() {
		if ( !getTxtPreimpreso().getText().equals("") ){
			String preimpreso = getTxtPreimpreso().getText();
			if ( facturaIf != null ){
				facturaIf.setPreimpresoNumero(preimpreso);
				anularFactura = true;
				dispose();
			} else {
				SpiritAlert.createAlert("Debe especificar una factura valida !!", SpiritAlert.WARNING);
				dispose();
			}
		} else {
			SpiritAlert.createAlert("Debe ingresar mínimo 1 digito, máximo 7", SpiritAlert.WARNING);
		}
	}

	public boolean isAnularFactura() {
		return anularFactura;
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
