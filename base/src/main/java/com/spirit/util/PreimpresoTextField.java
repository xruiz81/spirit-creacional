package com.spirit.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

public class PreimpresoTextField extends JTextField implements KeyListener {	
	public void keyTyped(KeyEvent evt) {
		JTextComponent c = (JTextComponent)evt.getSource();
		char keyChar = evt.getKeyChar();
		
		if (((keyChar >= '0' && keyChar <= '9') || keyChar == '-') && c.isEditable()) {			
			try {
				c.getDocument().insertString(c.getCaretPosition(), ""+ Character.toString(keyChar), null);
				evt.consume();
			} catch (BadLocationException e) {
				
			}
		} else
			evt.consume();
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void keyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
	}
}
