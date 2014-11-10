package com.spirit.timeTracker.componentes;

import static timeTracker.tiempo.Utiles.getTiempoCompleto;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.spirit.client.Parametros;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.UsuarioIf;

public class TiempoCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	private Color colorCeleste = new Color(200, 255, 255);
	private Color colorBlanco = new Color(255, 255, 255);
	private Color colorVerde = new Color(194, 255, 204);
	private Color colorAmarillo = new Color(255, 255, 165);
	private Color colorRojo = new Color(255, 160, 160);
	private Color colorAzulSeleccion = new Color(0, 0, 200);
	
	List<String> colores = null;

	boolean funcionar = true;

	public TiempoCellRenderer(boolean funcionar) {
		this.funcionar = funcionar;
	}

	protected void setValue(Object value) {
		if (funcionar) {
			setHorizontalAlignment(SwingConstants.RIGHT);
			if (value != null) {
				if (value instanceof Long) {
					long segundos = (Long) value;
					setText(getTiempoCompleto(segundos));
				} /*
				 * else if ( value instanceof String ){ String sSegundos =
				 * (String) value; if ( !sSegundos.contains(":") ){ int
				 * iSegundos = Integer.valueOf(sSegundos);
				 * setText(getTiempoCompleto(iSegundos)); } else{
				 * setText(sSegundos); } }
				 */
			} else {
				setText(getTiempoCompleto(0));
			}
		} else {
			super.setValue(value);
		}
		if (value != null && value instanceof EmpleadoIf) {
			EmpleadoIf empleadoIf = (EmpleadoIf) value;
			setText(empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
		}
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		UsuarioIf usuarioIf=(UsuarioIf)Parametros.getUsuarioIf();
		
		if(row >= 0 && colores != null && colores.get(row) != null){
			String color = colores.get(row);
			if(isSelected){
				c.setBackground(colorAzulSeleccion);
				c.setForeground(Color.WHITE);
			}
			
			else if(color.equals("BLANCO")){
				c.setBackground(colorBlanco);
				c.setForeground(Color.BLACK);
			}else if(color.equals("CELESTE")){
				c.setBackground(colorCeleste);
				c.setForeground(Color.BLACK);
			}else if(color.equals("VERDE")){
				c.setBackground(colorVerde);
				c.setForeground(Color.BLACK);
			}else if(color.equals("AMARILLO")){
				c.setBackground(colorAmarillo);
				c.setForeground(Color.BLACK);
			}else if(color.equals("ROJO")){
				c.setBackground(colorRojo);
				c.setForeground(Color.BLACK);
			}
			
		}
		
		return c;
	}

	public List<String> getColores() {
		return colores;
	}

	public void setColores(List<String> colores) {
		this.colores = colores;
	}

}
