package com.spirit.util;

import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

public class NumberTextFieldDecimal extends TextField implements KeyListener {
	private static final int TECLA_DELETE = 127;
	private static final int TECLA_BACKSPACE = 8;
	public static final int EDIT_MODE = 0;
	public static final int VIEW_MODE = 1;
	
	public void keyTyped(KeyEvent evt) {
		JTextComponent c = (JTextComponent)evt.getSource();
		if (c.isEditable()) {
			char keyChar = evt.getKeyChar();
			int caretPosition = c.getCaretPosition();
			int cantidadSeparadoresAntesFormato = getCantidadSeparadoresMiles(c.getText().substring(0, caretPosition));
			int cantidadSeparadoresDespuesFormato = 0;
			String textoAntesInsertarCaracter = c.getText();
			String textoDespuesInsertarCaracter = "";
			if (((keyChar >= '0') && (keyChar <= '9') || (keyChar == '.' && !textoAntesInsertarCaracter.contains(".")))) {			
				try {
					c.getDocument().insertString(caretPosition, ""+ Character.toString(keyChar), null);	
					//  evt.consume();
				} catch (BadLocationException e) {
					
				}
			} //else
			// evt.consume();
			
			textoDespuesInsertarCaracter = c.getText();
			evt.consume();
			//if (!textoAntesInsertarCaracter.equals(textoDespuesInsertarCaracter)) {
			//if (!c.getText().equals("")) {
			if (((keyChar >= '0') && (keyChar <= '9')) || (keyChar == '.' && !textoAntesInsertarCaracter.contains(".")) || ((int) keyChar) == TECLA_BACKSPACE || ((int) keyChar) == TECLA_DELETE) {
				c.setText(formatearTexto(c.getText(), EDIT_MODE));
				if (((int) keyChar) != TECLA_BACKSPACE && ((int) keyChar) != TECLA_DELETE) {
					cantidadSeparadoresDespuesFormato = getCantidadSeparadoresMiles(c.getText().substring(0, caretPosition));
					c.setCaretPosition(caretPosition + (cantidadSeparadoresDespuesFormato - cantidadSeparadoresAntesFormato) + 1);
				} else if ((int) keyChar == TECLA_BACKSPACE) {
					if ((caretPosition - 1) >= 0) {
						cantidadSeparadoresDespuesFormato = getCantidadSeparadoresMiles(c.getText().substring(0, caretPosition - 1));
						c.setCaretPosition(caretPosition + (cantidadSeparadoresDespuesFormato - cantidadSeparadoresAntesFormato));
					}
				} else if ((int) keyChar == TECLA_DELETE) {
					cantidadSeparadoresDespuesFormato = getCantidadSeparadoresMiles(c.getText().substring(0, caretPosition));
					c.setCaretPosition(caretPosition + (cantidadSeparadoresDespuesFormato - cantidadSeparadoresAntesFormato));
				}
			}
		} else
			evt.consume();
		//}
		//}
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub			
	}
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub		
	}
	
	public static String formatearTexto(String textoSinFormato, int mode) {
		String textoSinFormatoSinSeparadoresMiles = Utilitarios.removeDecimalFormat(textoSinFormato);
		if (mode == VIEW_MODE)
			textoSinFormatoSinSeparadoresMiles = String.valueOf(redondear(Double.parseDouble(Utilitarios.removeDecimalFormat(textoSinFormatoSinSeparadoresMiles)), 2));
		String textoFormateado = "";
		String parteDecimal = "";
		String parteEntera = new String(textoSinFormatoSinSeparadoresMiles);
		int separadoresMiles = 0;
		int numerosSueltos = 0;
		
		if (textoSinFormatoSinSeparadoresMiles.contains(".")) {
			parteDecimal = textoSinFormatoSinSeparadoresMiles.substring(textoSinFormatoSinSeparadoresMiles.indexOf('.'), textoSinFormatoSinSeparadoresMiles.length());
			parteEntera = textoSinFormatoSinSeparadoresMiles.substring(0, textoSinFormatoSinSeparadoresMiles.indexOf('.'));
		}
		
		if (parteEntera.length() > 3) {
			separadoresMiles = (parteEntera.length() / 3) - 1;
			numerosSueltos = parteEntera.length() % 3;
			
			textoFormateado = parteEntera.substring(0, numerosSueltos);
			if (textoFormateado.length() > 0)
				textoFormateado += ",";
			
			parteEntera = parteEntera.substring(numerosSueltos, parteEntera.length());
			
			for (int i=0; i<parteEntera.length(); i=i+3) {
				textoFormateado += parteEntera.substring(i,i+3);
				if (i / 3 < separadoresMiles)
					textoFormateado += ",";
			}
			
			textoFormateado += parteDecimal;
			
			if (mode == VIEW_MODE) {
				if (parteDecimal.length() == 2)
					textoFormateado += "0";
				else if (parteDecimal.length() <= 1)
					textoFormateado += ".00";
			}
		} else {
			textoFormateado = textoSinFormatoSinSeparadoresMiles;
			if (textoFormateado.contains(".")) {
				parteDecimal = textoFormateado.substring(textoFormateado.indexOf('.'), textoFormateado.length());
				parteEntera = textoFormateado.substring(0, textoFormateado.indexOf('.'));
			}
			
			if (mode == VIEW_MODE) {
				if (parteDecimal.length() == 2)
					parteDecimal += "0";
				else if (parteDecimal.length() <= 1)
					parteDecimal += ".00";
			}
			
			textoFormateado = parteEntera + parteDecimal;
		}
		
		return textoFormateado;
	}
	
	private int getCantidadSeparadoresMiles(String texto) {
		int numeroSeparadoresMiles = 0;
		
		for (int i=0; i<texto.length(); i++)
			if (texto.charAt(i) == ',')
				numeroSeparadoresMiles++;
		
		return numeroSeparadoresMiles;
	}
	
	private static double redondear(double numero, int decimales) {
		return (Math.round(numero*Math.pow(10,decimales)) / Math.pow(10,decimales));
	}
}
