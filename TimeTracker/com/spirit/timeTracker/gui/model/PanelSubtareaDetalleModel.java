package com.spirit.timeTracker.gui.model;

import javax.swing.ListSelectionModel;

import com.spirit.timeTracker.componentes.GenericCellRenderer;
import com.spirit.timeTracker.componentes.TiempoCellRenderer;
import com.spirit.timeTracker.gui.main.JPSubtareasDetalle;

public class PanelSubtareaDetalleModel extends JPSubtareasDetalle {

	private static final long serialVersionUID = 1L;
	
	public static int COLUMNA_FECHA = 0;
	public static int COLUMNA_HORA_INICIO = 1;
	public static int COLUMNA_HORA_FIN = 2;
	public static int COLUMNA_TIEMPO = 3;
	
	public PanelSubtareaDetalleModel(){
		modificarTabla();
	}
	
	private void modificarTabla(){
		GenericCellRenderer cellRendererCentrado = new GenericCellRenderer(GenericCellRenderer.CENTER);
		TiempoCellRenderer tiempoCellRenderer = new TiempoCellRenderer();
        
		//MODO DE SELECCION DE FILAS
        getTblSubtareaDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //FECHA
        getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_FECHA).setMinWidth(115);
        //getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_FECHA).setMaxWidth(65);
        
        //HORA INICIO
        getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_HORA_INICIO).setCellRenderer(cellRendererCentrado);
        getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_HORA_INICIO).setMinWidth(68);
        getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_HORA_INICIO).setMaxWidth(68);
        
        //HORA FIN
        getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_HORA_FIN).setCellRenderer(cellRendererCentrado);
        getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_HORA_FIN).setMinWidth(63);
        getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_HORA_FIN).setMaxWidth(63);
        
        //TIEMPO
        //getTblSubTareas().getColumnModel().getColumn(COLUMNA_TIEMPO).setMinWidth(55);
        getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_TIEMPO).setCellRenderer(tiempoCellRenderer);
        getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_TIEMPO).setMinWidth(60);
        getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_TIEMPO).setMaxWidth(60);
    }
}
