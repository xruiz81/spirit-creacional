package com.spirit.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;


public class KeyListenerUpperCase extends KeyAdapter {
	 public void keyTyped(KeyEvent evt) {
          JTextComponent c = (JTextComponent)evt.getSource();
          char ch = evt.getKeyChar();
          
          try {
        	  if(ch!='#'){
            	  if (Character.isLowerCase(ch)) {
                      c.getDocument().insertString(c.getCaretPosition(), ""+ Character.toUpperCase(ch), null);
                      evt.consume();
            	  }
        	  }
              else
             	 evt.consume();
          } 
          catch (BadLocationException e) {
          }
      }
}
