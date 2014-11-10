package com.spirit.util;

import java.awt.Component;
import java.util.EventObject;
import java.util.Map;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.jidesoft.combobox.DateComboBox;

public class MyTableCellEditorDateComboBox extends AbstractCellEditor implements TableCellEditor {
    // This is the component that will handle the editing of the cell value
    DateComboBox combobox = new DateComboBox();
    Map filasSelecionadasTabla;
    
    
    public MyTableCellEditorDateComboBox(Map filasSeleccionadas){
    	filasSelecionadasTabla = filasSeleccionadas;
    	//combobox.addActionListener(oActionListenerCmbFecha);
    }
    
    
    // This method is called when a cell value is edited by the user.
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int vColIndex) {
    	if(filasSelecionadasTabla!=null)
    		filasSelecionadasTabla.get(rowIndex);
  /*  	filaSeleccionada = rowIndex;
    	if(esDependienteMes){
			if((Boolean)PlanMedioModel._filasSelecionadasTabla.get(filaSeleccionada))
				return true;
			else
				return false;
		}*/
		return combobox;
    }

    // This method is called when editing is completed.
    // It must return the new value to be stored in the cell.
    public Object getCellEditorValue(){
    	return combobox.getDate();	
    }

   
	public boolean isCellEditable(EventObject anEvent ) {
		return true;
	
	}
	
/*	
	//Action Listener del combo de fecha 
	ActionListener oActionListenerCmbFecha = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			Date fechaHoy = new java.util.Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(fechaHoy);
			
			Date fechaCombo = (Date) ((DateComboBox) evento.getSource()).getDate();
  			
  			if(fechaCombo.compareTo(fechaHoy)!=0){
  				JOptionPane.showMessageDialog(null,	"Fecha del combo no es la de hoy!", "Mensaje de Aviso",	JOptionPane.INFORMATION_MESSAGE);
  				fechaCombo = fechaHoy;
  				((DateComboBox) evento.getSource()).setCalendar(calendar);
  			}
		}	
	};
	*/
}