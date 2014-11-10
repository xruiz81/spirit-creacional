package com.spirit.general.gui.controller;

import java.awt.Frame;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.ImageIcon;

import com.spirit.client.Version;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.seguridad.entity.MenuIf;

public class JDAcercaDeModel extends JDAcercaDe {
	
	Vector<MenuIf> vectorComboModel;
	private MenuIf menuSeleccionado;
	private static final int MAX_LONGITUD_CODIGO = 10;
	ImageIcon spirit_icon = SpiritResourceManager.getImageIcon("images/icons/spirit.png");
	
	public JDAcercaDeModel(Frame frame) {
		super(frame);
		setLocation(
        		(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 3, 
        		(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3
        		);
        getLblVersion().setText(Version.VERSION);
        getLblLogo().setText("");
        getLblLogo().setIcon(
        	SpiritResourceManager.getImageIcon("images/icons/spirit.png"));
        this.pack();
        setModal(true);    
        setVisible(true);
	}
    
   
}
