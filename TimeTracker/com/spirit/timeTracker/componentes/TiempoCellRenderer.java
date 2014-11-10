package com.spirit.timeTracker.componentes;

import static timeTracker.tiempo.Utiles.getTiempoCompleto;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TiempoCellRenderer extends DefaultTableCellRenderer {
    
	private static final long serialVersionUID = 1L;

	protected void setValue(Object value) {
        setHorizontalAlignment(SwingConstants.RIGHT);
        if ( value != null ) {
            if ( value instanceof Long ){
            	long segundos = (Long)value;
            	setText(getTiempoCompleto(segundos));
            } /*else if ( value instanceof String ){
                String sSegundos = (String) value;
                if ( !sSegundos.contains(":") ){
                    int iSegundos = Integer.valueOf(sSegundos);
                    setText(getTiempoCompleto(iSegundos));
                }
                else{
                    setText(sSegundos);
                }
            }*/
        } else {
        	setText(getTiempoCompleto(0));
        }
    } 
}
