package com.spirit.timeTracker.componentes;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

import com.spirit.client.model.SpiritResourceManager;


public class IconCellRenderer extends DefaultTableCellRenderer {
    protected void setValue(Object value) {
        if ( value != null ) {
            ImageIcon icono = (ImageIcon)value;
            //System.out.println("valor de value:"+icono.getImage());
            setText("");
            setIcon( (ImageIcon)value );
            //setHorizontalAlignment(SwingConstants.CENTER);
            //setVerticalAlignment(SwingConstants.CENTER);
        } else {
            setIcon(SpiritResourceManager.getImageIcon("images/no_imagen.gif"));
        }
    }
}
