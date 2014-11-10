package com.spirit.facturacion.gui.util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.facturacion.gui.model.PreimpresoFacturasModel;
import com.spirit.util.PreimpresoTextField;
import com.spirit.util.TextChecker;

public class PreimpresoCellEditor  extends AbstractCellEditor implements TableCellEditor, CellEditorListener {
	private static final long serialVersionUID = 1197080664716168450L;
	JTable tabla = null;
	JTextField component = new JTextField();

	public PreimpresoCellEditor(JTable tabla){
		this.tabla = tabla;
		
		//Calculo y seteo la maxima longitud del preimpreso segun lo fijado en Parametros Empresa
		int maxlongPreimpEstablecimiento = Parametros.getMaximaLongitudPreimpresoEstablecimiento();
		int maxlongPreimpPuntoEmision = Parametros.getMaximaLongitudPreimpresoPuntoEmision();
		int maxlongPreimpSecuencial = Parametros.getMaximaLongitudPreimpresoSecuencial();
		int guionesSeparadores = 2;
		int longitudPreimpreso = maxlongPreimpEstablecimiento + maxlongPreimpPuntoEmision + maxlongPreimpSecuencial + guionesSeparadores;
		
		component.addKeyListener(new TextChecker(longitudPreimpreso));
		//component.addKeyListener(new TextChecker(15));
				
		component.addKeyListener(new PreimpresoTextField());
		addCellEditorListener(this);
	}

	public Component getTableCellEditorComponent(JTable tabla, Object value, boolean isSelected, int row, int col) {
		if (isSelected) {
			// cell (and perhaps other cells) are selected
		}

		// Configure the component with the specified value
		component.setText((String)value);
		return component;
	}

	public void editingStopped(ChangeEvent arg0) {
		verificarPreimpreso();
		System.out.println("Se paro la editada");
	}

	public void editingCanceled(ChangeEvent arg0) {
		System.out.println("Se cancelo la editada");
	}

	public Object getCellEditorValue() {
		return ( (JTextField)component ).getText();
	}

	public void actionPerformed(ActionEvent arg0) {
		verificarPreimpreso();
	}
	
	private void verificarPreimpreso(){
		//Pattern patron = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{7}");
		Pattern patron = Pattern.compile(Parametros.getPatronPreimpreso());
		String preimpreso = component.getText().trim();
		Matcher matcher = patron.matcher(preimpreso);
		//boolean encontrado = matcher.find();
		boolean encontrado = matcher.matches(); 
		if ( !encontrado ||
			( encontrado && matcher.end()!=preimpreso.length() )	){
			int i = tabla.getSelectedRow();
			SpiritAlert.createAlert("Error en Preimpreso de la fila "+(i+1), SpiritAlert.WARNING);
			tabla.getModel().setValueAt("", i,PreimpresoFacturasModel.COLUMNA_PREIMPRESO);
			tabla.setRowSelectionInterval(i, i);
			fireEditingCanceled();
		} else{
			component.setText(preimpreso);
		}
	}
}