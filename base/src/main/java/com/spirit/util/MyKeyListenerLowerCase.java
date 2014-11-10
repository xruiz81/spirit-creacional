package com.spirit.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;


public class MyKeyListenerLowerCase extends KeyAdapter {
	 public void keyTyped(KeyEvent evt) {
          JTextComponent c = (JTextComponent)evt.getSource();
          char ch = evt.getKeyChar();
  
          if (Character.isUpperCase(ch)) {
              try {
                  c.getDocument().insertString(c.getCaretPosition(), ""+ Character.toLowerCase(ch), null);
                  evt.consume();
              } catch (BadLocationException e) {
              }
          }
      }
}
