package com.spirit.general.gui.handler;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

import com.spirit.client.MainFrameModel;
import com.spirit.client.Parametros;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.general.gui.controller.PanelHandler;
import com.spirit.general.gui.main.SpiritLogin;

public class JDCloseSystemModel extends JDCloseSystem {
	ImageIcon spirit_icon = SpiritResourceManager.getImageIcon("images/icons/spirit.png");
	private String si = "Sí"; 
	private String no = "No"; 
	private Object[] options = {si, no}; 
	
	public JDCloseSystemModel(Frame frame) {
		super(frame);
		setearEscKey();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 3, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3);
        initListeners();
        this.pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.addWindowListener(new CloseSystemWindowAdapter());
        setModal(true);
        getBtnSalir().requestFocusInWindow();
        setVisible(true);
	}
    
    //Se implementa la accion de salir con ESC
    private void setearEscKey(){
    	ActionMap am = getRootPane().getActionMap();
        //InputMap im = getRootPane().getInputMap(JComponent.WHEN_FOCUSED);
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        //InputMap im1 = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        Object windowCloseKey = new Object();
        //Object windowCloseKey1 = new Object();
        KeyStroke windowCloseStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        Action windowCloseAction = new AbstractAction() {
			private static final long serialVersionUID = 4518311957146680262L;

			public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        };
        im.put(windowCloseStroke, windowCloseKey);
        am.put(windowCloseKey, windowCloseAction);
    }
    
    private void initListeners() {
    	getBtnCerrarSesion().addActionListener(actionListenerBtnCerrarSesion);
    	getBtnSalir().addActionListener(actionListenerBtnSalir);
    }
    
    ActionListener actionListenerBtnCerrarSesion = new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		Parametros.getMainFrame().removeAll();
           	Parametros.getMainFrame().dispose();
           	Parametros.setMainFrame(null);
           	PanelHandler.clearCache();
			new SpiritLogin();
    		/*List listaFrame = MainFrameModel.get_dockingManager().getAllFrameNames();
    		int contador = 0;
    		for (int i=0; i<listaFrame.size(); i++) {
				String frameName = listaFrame.get(i).toString();
				if (!frameName.equals("Background") && !frameName.equals("Lista Tareas") && !frameName.equals("Noticias") && !frameName.equals("Mensajes")){
					int opcion = JOptionPane.showOptionDialog(null, "¿Desea cerrar las ventanas?, se perdará toda la información que no haya sido guardada", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if (opcion == JOptionPane.YES_OPTION) {
						contador++;
						Parametros.getMainFrame().removeAll();
			           	Parametros.getMainFrame().dispose();
			           	Parametros.setMainFrame(null);
						new SpiritLogin();
						break;
					}else{
						contador++;
					}
				}
			}
    		
    		if(contador == 0){
    			Parametros.getMainFrame().removeAll();
	           	Parametros.getMainFrame().dispose();
	           	Parametros.setMainFrame(null);
				new SpiritLogin();
    		}*/
    	}
    };
    
    ActionListener actionListenerBtnSalir = new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		setVisible(false);
            dispose(); 
            System.exit(0);
    		/*List listaFrame = MainFrameModel.get_dockingManager().getAllFrameNames();
    		int contador = 0;
    		for (int i=0; i<listaFrame.size(); i++) {
				String frameName = listaFrame.get(i).toString();
				if (!frameName.equals("Background") && !frameName.equals("Lista Tareas") && !frameName.equals("Noticias") && !frameName.equals("Mensajes")){
					int opcion = JOptionPane.showOptionDialog(null, "¿Desea cerrar las ventanas?, se perdará toda la información que no haya sido guardada", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
					if (opcion == JOptionPane.YES_OPTION) {
						contador++;
						setVisible(false);
			            dispose(); 
			            System.exit(0);
						break;
					}else{
						contador++;
					}
				}
			}
    		
    		if(contador == 0){
    			setVisible(false);
	            dispose(); 
	            System.exit(0);
    		}*/
    	}
    };
    
    class CloseSystemWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent event) {
        	MainFrameModel.get_frame().setVisible(true);
        }
	}
}