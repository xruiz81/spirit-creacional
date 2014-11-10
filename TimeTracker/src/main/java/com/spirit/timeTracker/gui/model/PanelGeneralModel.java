package com.spirit.timeTracker.gui.model;

import static com.spirit.timeTracker.gui.model.Utiles.setTablaProyectosGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.setTablaSubTareasDetalleGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.setTablaSubTareasGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.setTablaTareasGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.setMapaProyectosGlobal;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.LinkedHashMap;

import timeTracker.tiempo.Proyecto;

import com.spirit.timeTracker.gui.main.JPPanelPrincipal;


public class PanelGeneralModel extends JPPanelPrincipal {
    
    private static final long serialVersionUID = -2351208070432668975L;
    
    private PanelListaProyectosModel panelListaProyectos = null;
    private PanelListaTareasModel panelListaTareas = null;
    private PanelListaSubTareasModel panelListaSubtareas = null;
    private PanelSubtareaDetalleModel panelSubtareaDetalle = null;
    
	//private static SubTarea subTareaActiva = null;
    private HashMap<String,Proyecto> mapaProyectos = new LinkedHashMap<String,Proyecto>();
    
    private boolean existeTrayIcon = false;
    
    public PanelGeneralModel() {
    	iniciarComponents();
    }
    
    private void iniciarComponents(){
    	
    	//getSplitPaneListas().setDividerLocation( TimeTracker.altoPantalla/2 - 50 -20); // cuando el divisor aumenta la pantalla donde aparecen las ordenes por codigo se hace mas pequeña.
    	getSplitPaneListas().setDividerLocation( 650 ); // ancho de la ventana donde salen las ordenes de trabajo.
    	getSplitPaneGeneral().setDividerLocation( TimeTracker.anchoPantalla/4 ); // cuando el divisor aumenta la pantalla donde aparecen las ordenes por codigo se achica en la altura.
        
        //PANEL SUBTAREAS
        panelListaSubtareas = new PanelListaSubTareasModel();
        getSplitPaneGeneral().setRightComponent(panelListaSubtareas);
        
        
        panelListaSubtareas.getSplitPaneInferior().setDividerLocation(
        		230);// si el divisor aumenta, la pantalla donde esta fecha, hora inicio, hora fin, tiempo, se achica. 
        
        //DETALLE DE SUBTAREAS
        panelSubtareaDetalle = new PanelSubtareaDetalleModel();
        panelSubtareaDetalle.setMaximumSize(new Dimension(200,50)); //pantalla donde se da click derecho para poner nueva tarea.
        //panelSubtareaDetalle.setMinimumSize(new Dimension(100,50));
        
        panelListaSubtareas.getSplitPaneInferior().setRightComponent(panelSubtareaDetalle);
        
        //MENU PROYECTOS
        //ASIGNACION PARA PODER MOSTRAR LA INFO EN EL PANEL DE LA DERECHA
        panelListaProyectos = new PanelListaProyectosModel();
        panelListaProyectos.setMinimumSize(new Dimension(300,150));
        panelListaProyectos.setSplitPaneContenido(getSplitPaneGeneral());
        panelListaProyectos.setPanelListSubTareas(panelListaSubtareas);
        
        //MENU TAREAS
        //ASIGNACION PARA PODER MOSTRAR LA INFO EN EL PANEL DE LA DERECHA
        panelListaTareas = new PanelListaTareasModel();
        panelListaTareas.setMinimumSize(new Dimension(100,150));
        panelListaTareas.setSplitPaneContenido(getSplitPaneGeneral());
        panelListaTareas.setPanelListSubTareas(panelListaSubtareas);
        
        //UTILES
        setMapaProyectosGlobal(getMapaProyectos());
        setTablaProyectosGlobal(panelListaProyectos.getTblProyectos());
        setTablaTareasGlobal(panelListaTareas.getTblTareas());
        setTablaSubTareasGlobal(panelListaSubtareas.getTblSubTareas());
        setTablaSubTareasDetalleGlobal(panelSubtareaDetalle.getTblSubtareaDetalle());
        Utiles.contenedorPanelInformacion = panelListaSubtareas.getSpanelInformacion();
        
        //UBICARLOS EN EL SPLITPANE DE LAS LISTAS DE PROYECTOS Y TAREAS
        getSplitPaneListas().setLeftComponent(panelListaProyectos); //LISTA DE ORDENES
        
        
        getSplitPaneListas().setRightComponent(panelListaTareas); //LISTA DE TAREAS
        //getSplitPaneListas().setRightComponent(panelListaSubtareas);
        
    }
    
    public HashMap<String,Proyecto> getMapaProyectos() {
        return mapaProyectos;
    }
    
    public void setMapaProyectos(HashMap<String,Proyecto> mapaProyectos) {
        this.mapaProyectos = mapaProyectos;
    }
    
    public boolean isExisteTrayIcon() {
        return existeTrayIcon;
    }
    
    public void setExisteTrayIcon(boolean existeTrayIcon) {
        this.existeTrayIcon = existeTrayIcon;
    }
}