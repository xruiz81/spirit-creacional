package com.spirit.timeTracker.componentes;

import javax.swing.table.DefaultTableCellRenderer;

public class GenericCellRenderer extends DefaultTableCellRenderer {
    
	private static final long serialVersionUID = 1L;

	/*public static final int IZQUIERDA = 0;
	public static final int CENTRO = 1;
	public static final int DERECHA = 2;*/
	
	private int alineacion = 0;

	public GenericCellRenderer(int alineacion){
		this.alineacion = alineacion;
	}

	protected void setValue(Object value) {
        setHorizontalAlignment(alineacion);
        /*switch(alineacion){
        	case IZQUIERDA:
        		setHorizontalAlignment(SwingConstants.LEFT);
        		break;
        	case CENTRO:
        		setHorizontalAlignment(SwingConstants.CENTER);
        		break;
        	case DERECHA:
        		setHorizontalAlignment(SwingConstants.RIGHT);
        		break;
        }*/
        super.setValue(value);
        /*if ( value != null ) {
            if ( value instanceof Long ){
            	long segundos = (Long)value;
            	setText(getTiempoCompleto(segundos));
            } 
        } else {
        	setText(getTiempoCompleto(0));
        }*/
    } 
}
