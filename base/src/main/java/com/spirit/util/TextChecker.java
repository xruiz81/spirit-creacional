package com.spirit.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/*
 *  Se limita el numero de caracteres que se pueden
 *  introducir en los textfields o textareas
 */

public class TextChecker extends KeyAdapter {

	int maxSize;
	boolean isTextArea = false;
	int mayusculas = 1;
	Character character;
	public final static int NO_MAYUSCULAS = 0;
	
	public TextChecker(int maxSize){
		this.maxSize = maxSize;
	}
	
	public TextChecker(int maxSize,int mayusculas){
		this.maxSize = maxSize;
		this.mayusculas = mayusculas;
	}
	
	
	public TextChecker(int maxSize,boolean isTextArea){
		this.maxSize = maxSize;
		this.isTextArea = isTextArea;
	}
	
	public TextChecker(int maxSize,boolean isTextArea, int mayusculas){
		this.maxSize = maxSize;
		this.isTextArea = isTextArea;
		this.mayusculas = mayusculas;
	}
	
	public void keyTyped(KeyEvent e) {
		character = new Character(e.getKeyChar());
		String texto = "";
		int txtLength = 0;
		if ( mayusculas == 1 )
			e.setKeyChar(character.toUpperCase(character.charValue()));
		
		JTextComponent   txtField = (JTextComponent)e.getSource();
		replaceSelected(e,txtField);
		txtLength = txtField.getText().length();
		texto = txtField.getText();
		if(txtLength>=maxSize && txtField.isEditable()) {
			e.setKeyChar((char)KeyEvent.VK_BACK_SPACE);
			txtField.setText(texto.substring(0, maxSize));
		}
		
		/*if(isTextArea){
			JTextArea txtField = (JTextArea)e.getSource();
						
			replaceSelected(e,txtField);
			txtLength = txtField.getText().length();
			texto = txtField.getText();
			if(txtLength>=maxSize) {
				e.setKeyChar((char)KeyEvent.VK_BACK_SPACE);
				txtField.setText(texto.substring(0, maxSize));
			}
		} else {
			JTextField txtField = (JTextField)e.getSource();
			
			replaceSelected(e,txtField);
			txtLength = txtField.getText().length();
			texto = txtField.getText();
			if(txtLength>=maxSize) {
				e.setKeyChar((char)KeyEvent.VK_BACK_SPACE);
				txtField.setText(texto.substring(0, maxSize));
			}
		}*/
	}
	
	private void replaceSelected(KeyEvent e,Object objeto){
		int txtSelecLength = 0;
		String txtSelec;
		if ( objeto instanceof JTextField ){
			JTextComponent   txtField = (JTextComponent)objeto;

			txtSelec = txtField.getSelectedText();
			if ( txtSelec!=null && !e.isControlDown() ){
				txtSelecLength = txtSelec.length();
				if ( txtSelecLength > 0 && txtSelecLength <= maxSize && txtField.isEditable()){
					txtField.replaceSelection("");
				}
			}
		}
		else if ( objeto instanceof JTextArea ){
			JTextArea textArea = (JTextArea)objeto;
			txtSelec = textArea.getSelectedText();
			if ( txtSelec!=null && !e.isControlDown() ){
				txtSelecLength = txtSelec.length();
				if ( txtSelecLength > 0 && txtSelecLength <= maxSize && textArea.isEditable()){
					textArea.replaceSelection("");
				}
			}
		}
	}
}
