package com.spirit.general.gui.controller;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.seguridad.entity.RolUsuarioIf;
import com.spirit.seguridad.session.MenuSessionService;
import com.spirit.seguridad.session.RolUsuarioSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;

public class JDQuickSearchModel extends JDQuickSearch {
	
	Vector<MenuIf> vectorComboModel;
	private MenuIf menuSeleccionado;
	private static final int MAX_LONGITUD_CODIGO = 10;
	
	public JDQuickSearchModel(Frame frame) {
		super(frame);
		
		setLocation(
        		(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 3, 
        		(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3
        		);
        
        initListener();
        fillFilteringJList();
        getListQuickSearch().installJTextField(getTxtCodigo());
        getListQuickSearch().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.pack();
        setModal(true);        
        setearEscKey();
        setVisible(true);
	}
    
    private void initListener() {
    	getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
    	getTxtCodigo().addKeyListener(keyListenerTxtCodigo);
    	getListQuickSearch().addMouseListener(mouseListenerListQuickSearch);
    	getListQuickSearch().addKeyListener(keyListenerListQuickSearch);
    }
    
    private void fillFilteringJList() {
    	try {
    		vectorComboModel = new Vector<MenuIf>();
    		String codigo = getTxtCodigo().getText();
    		Iterator rolesUsuarioIterator = SessionServiceLocator.getRolUsuarioSessionService().findRolUsuarioByUsuarioId(((UsuarioIf)Parametros.getUsuarioIf()).getId()).iterator();
    		int nivelMinimo = 3;
    		while (rolesUsuarioIterator.hasNext()) {
    			RolUsuarioIf rolUsuario = (RolUsuarioIf) rolesUsuarioIterator.next();
    			Long rolId = rolUsuario.getRolId();
    			Iterator menusIterator = SessionServiceLocator.getMenuSessionService().findMenuByCodigoAndRolIdAndNivelMinimo(codigo, rolId, nivelMinimo).iterator();
    			addItemsToVector(menusIterator);
    		}
    	} catch (GenericBusinessException e) {
    		SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
    	}    	
    }
	
	private void addItemsToVector(Iterator menusIterator) {
		while (menusIterator.hasNext()) {
			MenuIf menu = (MenuIf) menusIterator.next();
			if (!estaAgregadoMenu(menu)) {
				vectorComboModel.add(menu);
				getListQuickSearch().addElement(menu);
			}
		}
	}
	
	private boolean estaAgregadoMenu(MenuIf menu) {
		for (int i=0; i<vectorComboModel.size(); i++) {
			MenuIf menuElement = vectorComboModel.get(i);
			if (menuElement.getId().compareTo(menu.getId()) == 0)
				return true;
		}
		
		return false;
	}
		
	KeyListener keyListenerTxtCodigo = new KeyAdapter(){
		public void keyPressed(KeyEvent e) {
			try {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					int nivelMinimo = 3;
					Iterator menuIterator = SessionServiceLocator.getMenuSessionService().findMenuByCodigoAndNivelMinimo(getTxtCodigo().getText(), nivelMinimo).iterator();
					if (menuIterator.hasNext()) {
						menuSeleccionado = (MenuIf) menuIterator.next();
						if (estaAgregadoMenu(menuSeleccionado)) {
							PanelHandler.showPanelModel(menuSeleccionado.getPanel());
							dispose();
						} else {
							SpiritAlert.createAlert("Usted no tiene permiso para acceder a este panel", SpiritAlert.WARNING);
						}
					} else {
						menuSeleccionado = null;
						SpiritAlert.createAlert("El nemónico ingresado no corresponde a ningún panel", SpiritAlert.WARNING);
					}
				}	
			} catch (GenericBusinessException ex) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				ex.printStackTrace();
			}
		}
    };
    
    MouseListener mouseListenerListQuickSearch = new MouseAdapter(){
		public void mouseClicked(MouseEvent e) {
			if ( e.getClickCount() == 2 ){
				menuSeleccionado = (MenuIf) getListQuickSearch().getSelectedValue();
				if (menuSeleccionado != null) {
					PanelHandler.showPanelModel(menuSeleccionado.getPanel());
					dispose();
				}
			}
		}		
	};
	
	KeyListener keyListenerListQuickSearch = new KeyAdapter(){
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				menuSeleccionado = (MenuIf) getListQuickSearch().getSelectedValue();
				if (menuSeleccionado != null) {
					PanelHandler.showPanelModel(menuSeleccionado.getPanel());
					dispose();
				}
			}			
		}
    };
    
    //Se implementa la accion de salir con ESC
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
    
    public MenuIf getMenuSeleccionado() {
    	return this.menuSeleccionado;
    }
 
}
