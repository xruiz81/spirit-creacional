package com.spirit.timeTracker.componentes;

import static com.spirit.timeTracker.gui.model.Utiles.getSubTareaHiloContadorGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.iniciarHiloContadorGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.pararHiloContadorGlobal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;

import timeTracker.tiempo.Proyecto;
import timeTracker.tiempo.SubTarea;
import timeTracker.tiempo.SubTareaDetalle;
import timeTracker.tiempo.Tarea;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoEJB;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.TiempoParcialDotDetalleIf;
import com.spirit.medios.entity.TiempoParcialDotIf;
import com.spirit.timeTracker.gui.model.PanelListaProyectosModel;
import com.spirit.timeTracker.gui.model.PanelListaSubTareasModel;
import com.spirit.timeTracker.gui.model.PanelListaTareasModel;
import com.spirit.timeTracker.gui.model.Utiles;

public class SubTareaListener implements ActionListener {

	public void actionPerformed(ActionEvent acev) {
		String comando="",nombre="";
		if ( acev.getSource() instanceof JMenuItem)
			comando = acev.getActionCommand();
		if ( acev.getSource() instanceof JButton)
			nombre = ((JButton)acev.getSource()).getName();
		if ( "guardar".equalsIgnoreCase(nombre) ){
			guardar();
		}
		else if ( "nueva".equalsIgnoreCase(comando) 
				|| "nueva".equalsIgnoreCase(nombre) ){
			nuevaSubTarea();
		} else if ( "iniciar".equalsIgnoreCase(comando) 
				|| "iniciar".equalsIgnoreCase(nombre) ){
			iniciarSubTarea();
		} else if ( "parar".equalsIgnoreCase(comando) 
				|| "parar".equalsIgnoreCase(nombre) ){
			pararSubTarea();
		} else if ( "eliminar".equalsIgnoreCase(comando) 
				|| "eliminar".equalsIgnoreCase(nombre) ){
			eliminarSubTarea();
		}
	}
	
	public void nuevaSubTarea(){
		try{
			SubTarea  subTarea = addSubtarea();
			
			if (!Utiles.verificarUsuario()){
				SpiritAlert.createAlert("Solo el usuario asignado a esta tarea puede agregar nuevas subtareas"
						, SpiritAlert.WARNING);
				return;
			}
			
			Utiles.mostrarInformacionSubTareaGlobal();
			Utiles.mostrarSubTareaDetalleGlobal(subTarea);
			habilitarImpresionTablaSubtareaDetalle(subTarea);

		} catch(Exception ex){
			ex.printStackTrace();
			if (ex instanceof GenericBusinessException)
				SpiritAlert.createAlert(ex.getMessage(), SpiritAlert.INFORMATION);
			else
				SpiritAlert.createAlert("Error al iniciar la SubTarea"
						, SpiritAlert.INFORMATION);
		}
	}

	private SubTarea addSubtarea() throws GenericBusinessException{
    	Tarea tarea = Utiles.getTareaActivaGlobal();
    	SubTarea subTarea = new SubTarea(tarea.getIdTarea());
    	addSubtareaDetalle(subTarea);
    	tarea.addSubTarea(subTarea);
    	DefaultTableModel modeloTablaSubTareas = (DefaultTableModel)Utiles.getTablaSubTareasGlobal().getModel();
    	modeloTablaSubTareas.addRow(subTarea.getFilaDeTabla());
    	int ultimaFila = modeloTablaSubTareas.getRowCount()-1;
    	Utiles.getTablaSubTareasGlobal().setRowSelectionInterval(ultimaFila, ultimaFila);
    	Utiles.setSubTareaActivaGlobal(subTarea);
    	return subTarea;
    }
	
	private void addSubtareaDetalle(SubTarea subTarea){
		SubTareaDetalle detalle = new SubTareaDetalle();
		subTarea.addDetalle(detalle);
	}
    
    public void iniciarSubTarea(){
    	try{
	        SubTarea subTarea = Utiles.getSubTareaActivaGlobal();
	        
	        if (subTarea.getDetalle().size()==1){
	        	SubTareaDetalle detalle = subTarea.getDetalle().get(0);
	        	if (detalle.getSegundos() == 0L)
	        		detalle.setHoraInicio(new Date());
	        	else{
	        		detalle = new SubTareaDetalle();
		        	detalle.setHoraInicio(new Date());
	        		subTarea.addDetalle(detalle);
	        	}
	        } else{
	        	SubTareaDetalle detalle = new SubTareaDetalle();
	        	detalle.setHoraInicio(new Date());
	        	subTarea.addDetalle(detalle);
	        }
	        Utiles.mostrarSubTareaDetalleGlobal(subTarea);
	        
	        iniciarHiloContadorGlobal(subTarea);
    	} catch(Exception ex){
    		ex.printStackTrace();
    		if (ex instanceof GenericBusinessException)
    			SpiritAlert.createAlert(ex.getMessage(), SpiritAlert.INFORMATION);
    		else
    			SpiritAlert.createAlert("Error al iniciar la SubTarea"
    					, SpiritAlert.INFORMATION);
    	}
    }
    
    public void pararSubTarea(){
        try {
			pararHiloContadorGlobal();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.INFORMATION);
		}
    }
    
    private void guardar(){
    	if (Utiles.getSubTareaHiloContadorGlobal() == null){
			HashMap<String, Proyecto> mapaProyectos = Utiles.getMapaProyectosGlobal();
			
			Utiles.removerTodasFilasTabla(Utiles.getTablaSubTareasGlobal().getModel());
			Utiles.removerTodasFilasTabla(Utiles.getTablaSubTareasDetalleGlobal().getModel());
			Utiles.borrarInformacionPanelGlobal();
			
			for ( Iterator itProyectos = mapaProyectos.keySet().iterator() ; itProyectos.hasNext() ; ){
				Proyecto proyecto = mapaProyectos.get(itProyectos.next());
				HashMap<Long,Object> mapaTareas = proyecto.getTareas();
				for ( Iterator itTareas = mapaTareas.keySet().iterator() ; itTareas.hasNext() ; ){
					Tarea tarea = (Tarea)mapaTareas.get(itTareas.next());
					Vector<SubTarea> vectorSubTarea = tarea.getSubTareas();
					for (Iterator itSubtareas = vectorSubTarea.iterator();itSubtareas.hasNext();){
						SubTarea subTarea = (SubTarea)itSubtareas.next();
						
						TiempoParcialDotIf tiempoParcial = subTarea.getTiempoParcial();
						Vector<SubTareaDetalle> detalleVector = subTarea.getDetalle();
						
						Vector<TiempoParcialDotDetalleIf> tiempopParcialDetalleVector = new Vector<TiempoParcialDotDetalleIf>();
						for ( Iterator itTiempoDetalle = detalleVector.iterator();itTiempoDetalle.hasNext();){
							SubTareaDetalle subTareaDetalle = (SubTareaDetalle)itTiempoDetalle.next();
							tiempopParcialDetalleVector.add(subTareaDetalle.getTiempoParcialDetalle());
						}
						
						try {
							TiempoParcialDotIf tiempoParcialGuardado = PanelListaProyectosModel.getTiempoParcialDotSessionService().procesarTiempoParcialDot(tiempoParcial,tiempopParcialDetalleVector );
							subTarea.setTiempoParcial(tiempoParcialGuardado);
						} catch (GenericBusinessException ex1) {
							ex1.printStackTrace();
							SpiritAlert.createAlert("Error al guardar SubTarea en Tarea de C\u00f3digo: "+tarea.getIdTarea()
									+"\n"+ex1.getMessage(),SpiritAlert.ERROR);
						}
						
					}
				}
				
			}
		} else {
    		SpiritAlert.createAlert("No se puede guardar mientras una subtarea est\u00e1 en proceso"
    				, SpiritAlert.INFORMATION);
    	}
    }
    
    private void eliminarSubTarea(){
        //int filaSubTareaSeleccionada = getTblSubTareas().getSelectedRow();
        //SubTarea subTareaSeleccionada = null;
        //if ( filaSubTareaSeleccionada >= 0 ){
            //subTareaSeleccionada = getSubTareaActiva(filaSubTareaSeleccionada);
    	try {
    		Proyecto proyecto = Utiles.getProyectoActivoGlobal();
    		Tarea tarea = Utiles.getTareaActivaGlobal();
    		SubTarea subTareaSeleccionada = Utiles.getSubTareaActivaGlobal();
    		
    		if (!Utiles.verificarUsuario()){
    			SpiritAlert.createAlert("Solo el usuario asignado a esta tarea puede eliminar la subtarea"
    					, SpiritAlert.WARNING);
    			return;
    		}
    		
    		if ( Utiles.continuar("Desea eliminar la subtarea ???. " 
    				+"\nLos datos ser\u00e1n borrados permanentemente", "Eliminar Subtarea") ){
	    		
	    		if (subTareaSeleccionada.getId() != null){
	    			PanelListaProyectosModel.getTiempoParcialDotSessionService().eliminarTiempoParcialDot(
	    				subTareaSeleccionada.getId() );
	    		}
	    		
	    		long segundosSubtarea = subTareaSeleccionada.getSegundos();
	    		tarea.setSegundosTotales(tarea.getSegundosTotales()-segundosSubtarea);
	    		proyecto.setSegundosRestados(segundosSubtarea);
	    		//proyecto.setSegundosTotales(proyecto.getSegundosTotales()-segundosSubtarea);
	
	    		tarea.getSubTareas().remove(Utiles.getTablaSubTareasGlobal().getSelectedRow());
	
	    		int filaProyecto = Utiles.getTablaProyectosGlobal().getSelectedRow();
	    		Utiles.getTablaProyectosGlobal().getModel().setValueAt(
	    				proyecto.getSegundosTotales(), filaProyecto
	    				,PanelListaProyectosModel.COLUMNA_TIEMPO_ORDEN_TAREA);
	
	    		int filaTarea = Utiles.getTablaTareasGlobal().getSelectedRow();
	    		Utiles.getTablaTareasGlobal().getModel().setValueAt(
	    				tarea.getSegundosTotales()
	    				, filaTarea, PanelListaTareasModel.COLUMNA_TIEMPO_TAREA);
	
	    		Utiles.removerTodasFilasTabla(Utiles.getTablaSubTareasDetalleGlobal().getModel());
	    		Utiles.llenarTablaSubTareasGlobal(tarea);
	    		
	    		SpiritAlert.createAlert("Subtarea eliminada con \u00e9xito"
						,SpiritAlert.WARNING);
    		}
    		//} 
    		/*else{
    				SubTarea subTareaHilo = getSubTareaHiloContadorGlobal().getSubTarea();
    				if ( subTareaSeleccionada != subTareaHilo ) {
    					int filaTmp = getSubTareaHiloContadorGlobal().getFilaTablaSubTarea();
    					getSubTareaHiloContadorGlobal().setImprimirEnTablaSubTarea(false);
    					tarea.getSubTareas().remove(subTareaSeleccionada.getIdSubTarea());
    					modeloTablaSubTareas.removeRow(filaSubTareaSeleccionada);
    					getSubTareaHiloContadorGlobal().setFilaTablaSubTarea(filaTmp-1);
    					getSubTareaHiloContadorGlobal().setImprimirEnTablaSubTarea(true);
    				} else{
    					SpiritAlert.createAlert(
    							"No se puede eliminar una subtarea en proceso"
    							,SpiritAlert.WARNING);
    				}
    			}*/
    	} catch (GenericBusinessException e) {
    		e.printStackTrace();
    		SpiritAlert.createAlert(e.getMessage(), SpiritAlert.INFORMATION);
    	} catch(Exception e){
    		e.printStackTrace();
    		SpiritAlert.createAlert("Error la elimnar subtarea: \n"+e.getMessage(), SpiritAlert.INFORMATION);
    	}
        //}
    }
    
    private void habilitarImpresionTablaSubtareaDetalle(SubTarea subTarea){
    	if ( getSubTareaHiloContadorGlobal() != null ){
            SubTarea subTareaHiloContador = getSubTareaHiloContadorGlobal().getSubTarea();
            if ( subTarea != subTareaHiloContador ){
                getSubTareaHiloContadorGlobal().setImprimirEnTablaSubTareaDetalle(false);
            } else{
                getSubTareaHiloContadorGlobal().setImprimirEnTablaSubTareaDetalle(true);
            }
        }
    }

}
