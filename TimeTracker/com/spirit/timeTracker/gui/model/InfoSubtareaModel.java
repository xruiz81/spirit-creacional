package com.spirit.timeTracker.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import timeTracker.tiempo.SubTarea;

import com.spirit.medios.entity.TiempoParcialDotIf;
import com.spirit.timeTracker.gui.main.JPInfoSubtarea;

public class InfoSubtareaModel extends JPInfoSubtarea {
	
	private static final long serialVersionUID = 1L;
	
	private SubTarea subTarea = null;
	public InfoSubtareaModel(){
	}
	
	public InfoSubtareaModel(SubTarea subTarea){
		this.subTarea = subTarea;
		arreglarBotonGuardar();
		getBtnGuardar().addActionListener(guardarActionListener);
		//TiempoParcialDotIf tiempoParcial = subTarea.getTiempoParcial();
		
		getTxtId().setText(
				//subTarea.getId() != 0 && 
				(subTarea.getId() != null) ? 
						String.valueOf(subTarea.getId()) : "---" );
		
		getTxtFechaInicio().setText(
				timeTracker.tiempo.Utiles.convertirFecha(
						subTarea.getFechaInicio()).toString() );
		
		getTxtFechaFin().setText(
				timeTracker.tiempo.Utiles.convertirFecha(
						subTarea.getFechaFin()).toString() );
		
		getTxtDescripcion().setText(subTarea.getDescripcion());
	} 
	
	ActionListener guardarActionListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			subTarea.setDescripcion(
					getTxtDescripcion().getText());
			
			Utiles.getTablaSubTareasGlobal().setValueAt(
					subTarea.getDescripcion()
					, Utiles.getTablaSubTareasGlobal().getSelectedRow()
					, PanelListaSubTareasModel.COLUMNA_DESCRIPCION_SUBTAREA);
		}
	};
	
	private void arreglarBotonGuardar(){
		getBtnGuardar().setText(" ");
		getBtnGuardar().setToolTipText("Guardar informacion subtarea");
		getBtnGuardar().setIcon(
				new ImageIcon("images/timetracker/saveSubtask.png"));
		
		//DefaultTableModel modeloTablaSubtarea = (DefaultTableModel) Utiles.getTablaSubTareasGlobal().getModel();
		
	}

}
