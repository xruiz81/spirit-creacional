package com.spirit.timeTracker.componentes;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.spirit.timeTracker.gui.model.Utiles;

public class IconTreeCellRenderer extends DefaultTreeCellRenderer {
    
	private static final long serialVersionUID = 1L;
	ImageIcon iconComputadora,iconSesion,iconCarpeta;
    
    public IconTreeCellRenderer() {
        try {
            iconComputadora = Utiles.createImageIcon("images/computadora.png");
            iconSesion = Utiles.createImageIcon("images/sesion.png");
            iconCarpeta = Utiles.createImageIcon("images/carpeta.png");
        } catch (Exception e) {
            System.out.println("error en imagenes: "+e);
        }
    }
    
    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {
        
        super.getTreeCellRendererComponent(
                tree, value, sel,
                expanded, leaf, row,
                hasFocus);
        
        Icon icontmp = retornarIcono(value);
        if ( icontmp != null ){
            setIcon(icontmp);
            //setToolTipText("This book is in the Tutorial series.");
        } else {
            setToolTipText(null); //no tool tip
        }
        return this;
    }
    
    ImageIcon retornarIcono(Object value){
        DefaultMutableTreeNode nodo =
                (DefaultMutableTreeNode)value;
        MyTreeNode minodo = (MyTreeNode) (nodo.getUserObject());
        char tipo = minodo.getTipo();
        if ( tipo=='0' )
            return this.iconComputadora;
        else if ( tipo=='1' )
            return this.iconSesion;
        else if ( tipo=='2' )
            return this.iconCarpeta;
        return null;
    }
}


