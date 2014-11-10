package com.spirit.timeTracker.gui.model;

import com.spirit.timeTracker.componentes.IconCellRenderer;
import com.spirit.timeTracker.componentes.TiempoCellRenderer;
import com.spirit.timeTracker.gui.main.JPProcesos;
import javax.swing.table.DefaultTableModel;


public class PanelProcesosModel extends JPProcesos {
    
    public static final int COLUMNA_ICONO = 0;
    public static final int COLUMNA_TITULO_APLICACION = 1;
    public static final int COLUMNA_TIEMPO_PROCESO = 2;
    public static final int COLUMNA_USO_MEMORIA = 3;
    public static final int COLUMNA_NOMBRE_IMAGEN = 4;
    
    public static final int FILA_STAND_BY_PROCESO = 0;
    
    public PanelProcesosModel() {
        modificarTabla();
        //llenadoInicial();
    }
    
    public void modificarTabla(){
        IconCellRenderer iconCellRenderer = new IconCellRenderer();
        getTblProcesosActivos().getColumnModel().getColumn(COLUMNA_ICONO).setCellRenderer(iconCellRenderer);
        getTblProcesosActivos().getColumnModel().getColumn(COLUMNA_ICONO).setMinWidth(22);
        getTblProcesosActivos().getColumnModel().getColumn(COLUMNA_ICONO).setMaxWidth(22);
        
        TiempoCellRenderer tiempoCellRenderer = new TiempoCellRenderer(true);
        getTblProcesosActivos().getColumnModel().getColumn(COLUMNA_TIEMPO_PROCESO).setCellRenderer(tiempoCellRenderer);
        getTblProcesosActivos().getColumnModel().getColumn(COLUMNA_TIEMPO_PROCESO).setMinWidth(60);
        getTblProcesosActivos().getColumnModel().getColumn(COLUMNA_TIEMPO_PROCESO).setMaxWidth(60);
        
        getTblProcesosActivos().getColumnModel().getColumn(COLUMNA_USO_MEMORIA).setMinWidth(65);
        getTblProcesosActivos().getColumnModel().getColumn(COLUMNA_USO_MEMORIA).setMaxWidth(65);
        
        getTblProcesosActivos().getColumnModel().getColumn(COLUMNA_NOMBRE_IMAGEN).setMinWidth(95);
        getTblProcesosActivos().getColumnModel().getColumn(COLUMNA_NOMBRE_IMAGEN).setMaxWidth(95);
    }
    
    public void llenadoInicial(){
        Object[] fila = new Object[]{
          null, "Stand By" ,0,"",""
        };
        ((DefaultTableModel)getTblProcesosActivos().getModel()).addRow(fila);
    }
    
}
