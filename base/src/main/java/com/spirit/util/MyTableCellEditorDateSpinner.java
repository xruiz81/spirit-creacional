package com.spirit.util;

import java.awt.Component;
import java.util.Date;
import java.util.Map;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.jidesoft.spinner.DateSpinner;




public class MyTableCellEditorDateSpinner extends AbstractCellEditor implements TableCellEditor {
    // This is the component that will handle the editing of the cell value
	
    DateSpinner spinner;
    String format;
    Date valor;
    Map filasSelecionadasTabla;
 
    
    
    // Initializes the spinner.
    public MyTableCellEditorDateSpinner (String f) {
    	valor =  new Date();
    	format = f;
    	spinner = new DateSpinner(format);
    }
    
    // Initializes the spinner.
    public MyTableCellEditorDateSpinner (String f,Map filasSeleccionadas) {
    	valor =  new Date();
    	format = f;
    	spinner = new DateSpinner(format);
    	filasSelecionadasTabla = filasSeleccionadas;
    }
    
    

    // Prepares the spinner component and returns it.
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    	return spinner;
    }

    // Returns the spinners current value.
    public Object getCellEditorValue() {
    	if(format.equals("MMM/yyyy")){
    		valor = (Date) spinner.getValue();
    		
    		int año = valor.getYear() + 1900;
    		String mes = "";
    		
    		switch(valor.getMonth()){
    			case 0: mes= "Jan"; break;
    			case 1: mes= "Feb"; break;
    			case 2: mes= "Mar"; break;
    			case 3: mes= "Apr"; break;
    			case 4: mes= "May"; break;
    			case 5: mes= "Jun"; break;
    			case 6: mes= "Jul"; break;
    			case 7: mes= "Aug"; break;
    			case 8: mes= "Sep"; break;
    			case 9: mes= "Oct"; break;
    			case 10: mes= "Nov"; break;
    			case 11: mes= "Dec"; break;
    		}
    		return  mes+"/"+año;
    	}    	
    	
    	if(format.equals("HH:mm")){
    		valor = (Date) spinner.getValue();
    		
    		int hora = valor.getHours();
    		int minutos = valor.getMinutes();
    		return hora+":"+minutos;
    	}   
    	return valor;
    }
 }