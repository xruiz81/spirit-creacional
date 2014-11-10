package com.spirit.timeTracker.componentes;

import static com.spirit.timeTracker.gui.model.Utiles.getSubTareaHiloContadorGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.getTablaTareasGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.iniciarHiloContadorGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.pararHiloContadorGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.removerTodasFilasTabla;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;

import timeTracker.tiempo.Proyecto;
import timeTracker.tiempo.SubTarea;
import timeTracker.tiempo.SubTareaDetalle;
import timeTracker.tiempo.Tarea;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.medios.entity.TiempoParcialDotDetalleIf;
import com.spirit.medios.entity.TiempoParcialDotIf;
import com.spirit.timeTracker.gui.model.PanelListaProyectosModel;
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
			if ( Utiles.getTareaActivaGlobal() == null )
				return;
			
			/*if (!Utiles.verificarUsuario()){
				SpiritAlert.createAlert("Solo el usuario asignado a esta tarea puede agregar nuevas subtareas"
						, SpiritAlert.WARNING);
				return;
			}*/
			
			SubTarea  subTarea = addSubtarea();
			
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
    		//para la tarea que esta corriendo actualmente para luego iniciar la nueva
    		if(getSubTareaHiloContadorGlobal()!=null)
    			pararHiloContadorGlobal();
    		
    		//todo este if es en caso de que exita una sola tarea
			//cuando esto paso se da clic en play solo seleccionando la orden de trabajo
			//por lo que la tarea y subtarea no se selecciona y queda como exception
    		SubTarea subTarea = null;
    		if(Utiles.getProyectoActivoGlobal().getTareas().size() == 1){
    			Iterator tareasIterator = Utiles.getProyectoActivoGlobal().getTareas().keySet().iterator();
    			while(tareasIterator.hasNext()){
    				Long unicaTareaId = (Long)tareasIterator.next();
    				Tarea unicaTarea = Utiles.getProyectoActivoGlobal().getTareas().get(unicaTareaId);
    				if(unicaTarea.getSubTareas().size() == 1){
    					Proyecto proyecto = Utiles.getProyectoActivoGlobal();
    					Tarea tarea = Utiles.getTareaActivaByOrden(proyecto,0);
    					subTarea = ((ArrayList<SubTarea>)tarea.getSubTareas()).get(0);
    				}else{
    					UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
    					boolean existeUsuario = false;
    					int contador = 0;
    					Iterator subTareasIt = unicaTarea.getSubTareas().iterator();
    					while(subTareasIt.hasNext()){
    						SubTarea subTareaTemp = (SubTarea)subTareasIt.next();
    						if(!existeUsuario && 
    								subTareaTemp.getTiempoParcial().getUsuarioAsignadoId().compareTo(usuario.getEmpleadoId()) == 0){
    							existeUsuario = true;
    							Proyecto proyecto = Utiles.getProyectoActivoGlobal();
    	    					Tarea tarea = Utiles.getTareaActivaByOrden(proyecto,0);
    	    					subTarea = ((ArrayList<SubTarea>)tarea.getSubTareas()).get(contador);
    						}
    						contador++;
    					}
    				}
    			}
    		}
    		
    		if(subTarea == null){
    			subTarea = Utiles.getSubTareaActivaGlobal();
    		}
	        //SubTarea subTarea = Utiles.getSubTareaActivaGlobal();
	        
	        if (subTarea == null)
	        	return;
	        if (subTarea.getDetalle().size()==1){
	        	SubTareaDetalle detalle = subTarea.getDetalle().iterator().next();
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
    
    public static void guardar(){
    	if (Utiles.getSubTareaHiloContadorGlobal() == null){
			HashMap<String, Proyecto> mapaProyectos = Utiles.getMapaProyectosGlobal();
			
			//if(validarExisteUsuarioAsignado(mapaProyectos)){
				
				Utiles.removerTodasFilasTabla(Utiles.getTablaSubTareasGlobal().getModel());
				Utiles.removerTodasFilasTabla(Utiles.getTablaSubTareasDetalleGlobal().getModel());
				Utiles.borrarInformacionPanelGlobal();
				
				for ( String codigoProyecto : mapaProyectos.keySet() ){
					Proyecto proyecto = mapaProyectos.get(codigoProyecto);
					HashMap<Long,Tarea> mapaTareas = proyecto.getTareas();
					Long tiempoTotalProyecto = 0L;
					for ( Long idTarea : mapaTareas.keySet() ){
						Tarea tarea = mapaTareas.get(idTarea);
						Collection<SubTarea> vectorSubTarea = tarea.getSubTareas();
						Iterator<SubTarea> itSubtarea = vectorSubTarea.iterator();
						Long tiempoTotalTarea = 0L;
						while ( itSubtarea.hasNext() ){
							SubTarea subTarea = itSubtarea.next();
							TiempoParcialDotIf tiempoParcial = subTarea.getTiempoParcial();
							Collection<TiempoParcialDotDetalleIf> tiempopParcialDetalleVector = new ArrayList<TiempoParcialDotDetalleIf>();
							Collection<SubTareaDetalle> detalleVector = subTarea.getDetalle();
							for ( SubTareaDetalle subTareaDetalle : detalleVector ){
								tiempopParcialDetalleVector.add(subTareaDetalle.getTiempoParcialDetalle());
							}
							
							try {
								//Si el usuario asignado es null entonces se le asigna el usuario asignado en la orden detalle.
								//Tambien sirve para validar si se envia o no mail ya que si la tarea va para el mismo usuario que
								//la crea no vale la pena enviar el mail.
								Long empleadoAsignadoOrdenDetalleId = tarea.getTareaOrdenTrabajo().getAsignadoaId();
								boolean enviarCorreo = true;
								if(tiempoParcial.getUsuarioAsignadoId() == null){
									tiempoParcial.setUsuarioAsignadoId(empleadoAsignadoOrdenDetalleId);
								}else if(tiempoParcial.getUsuarioAsignadoId().compareTo(empleadoAsignadoOrdenDetalleId) == 0){
									enviarCorreo = false;
								}
								
								//Guardo cada Tarea
								TiempoParcialDotIf tiempoParcialGuardado = SessionServiceLocator.getTiempoParcialDotSessionService()
									.procesarTiempoParcialDot(tiempoParcial, tiempopParcialDetalleVector ,codigoProyecto, enviarCorreo);
								
								if (tiempoParcialGuardado != null ){
									//Collection<TiempoParcialDotDetalleIf> dotDetalles = SessionServiceLocator.getTiempoParcialDotDetalleSessionService().findTiempoParcialDotDetalleByIdTiempoParcialDot(tiempoParcialGuardado.getId());
									Collection<TiempoParcialDotDetalleIf> dotDetalles = SessionServiceLocator.getTiempoParcialDotDetalleSessionService().findTiempoParcialDotDetalleByIdTiempoParcialDot(tiempoParcialGuardado.getId());
									Collection<SubTareaDetalle> subtareasDetalles = new ArrayList<SubTareaDetalle>();
									for (TiempoParcialDotDetalleIf dotDetalle : dotDetalles){
										SubTareaDetalle subtareaDetalle = new SubTareaDetalle(dotDetalle);
										subtareasDetalles.add(subtareaDetalle);
									}
									subTarea.setDetalle(subtareasDetalles);
									subTarea.setTiempoParcial(tiempoParcialGuardado);
									tiempoTotalTarea += tiempoParcialGuardado.getTiempo();
								} else {
									itSubtarea.remove();
								}
								
							} catch (GenericBusinessException ex1) {
								ex1.printStackTrace();
								SpiritAlert.createAlert("Error al guardar SubTarea en Tarea de C\u00f3digo: "+tarea.getIdTarea()
										+"\n"+ex1.getMessage(),SpiritAlert.ERROR);
							}
						}
						tarea.setSegundosTotales(tiempoTotalTarea);
						tiempoTotalProyecto += tiempoTotalTarea;
					}
					proyecto.setSegundosTotales(0L);
					if ( proyecto.getOrdenTrabajo().getCodigo().equals("2010-01871") )
						System.out.println("");
					proyecto.setSegundosTotales(tiempoTotalProyecto);
				}
				
				actualizarTablaProyectos();
				
				
			/*}else{
				SpiritAlert.createAlert("Debe asignar toda tarea a alguien del equipo.", SpiritAlert.WARNING);
			}*/
			
		} else {
    		SpiritAlert.createAlert("No se puede guardar mientras una subtarea est\u00e1 en proceso"
    				, SpiritAlert.INFORMATION);
    	}
    }

	private static boolean validarExisteUsuarioAsignado(
			HashMap<String, Proyecto> mapaProyectos) {
		for ( String codigoProyecto : mapaProyectos.keySet() ){
			Proyecto proyecto = mapaProyectos.get(codigoProyecto);
			HashMap<Long,Tarea> mapaTareas = proyecto.getTareas();
			Long tiempoTotalProyecto = 0L;
			for ( Long idTarea : mapaTareas.keySet() ){
				Tarea tarea = mapaTareas.get(idTarea);
				Collection<SubTarea> vectorSubTarea = tarea.getSubTareas();
				Iterator<SubTarea> itSubtarea = vectorSubTarea.iterator();
				while ( itSubtarea.hasNext() ){
					SubTarea subTarea = itSubtarea.next();
					TiempoParcialDotIf tiempoParcial = subTarea.getTiempoParcial();
					if(tiempoParcial.getUsuarioAsignadoId() == null){
						return false;
					}
				}
			}
		}
		return true;
	}
    
    public static void actualizarTablaProyectos() {
    	DefaultTableModel modelo = (DefaultTableModel) Utiles.getTablaProyectosGlobal().getModel();
    	if ( modelo.getRowCount() <= 0 )
    		return;
    	int filaSeleccionada = Utiles.getTablaProyectosGlobal().getSelectedRow();
    	String codigoSeleccionado = null;
    	if ( filaSeleccionada >= 0 )
    		codigoSeleccionado = (String) modelo.getValueAt(filaSeleccionada, PanelListaProyectosModel.COLUMNA_CODIGO_ORDEN_TAREA);;
    	
    	int numeroFilasTareas = Utiles.getTablaTareasGlobal().getRowCount();
    	
    	try {
			Proyecto proyecto = Utiles.getProyectoActivoGlobal();
			for ( int i = 0 ; i < modelo.getRowCount() ; i++ ){
	    		String codigoTabla = (String) modelo.getValueAt(i, PanelListaProyectosModel.COLUMNA_CODIGO_ORDEN_TAREA); 
	    		if ( proyecto.getOrdenTrabajo().getCodigo().equals(codigoTabla) )
					modelo.setValueAt(proyecto.getSegundosTotales(), i, PanelListaProyectosModel.COLUMNA_TIEMPO_ORDEN_TAREA);
				
	    		if ( codigoTabla.equals(codigoSeleccionado) && numeroFilasTareas > 0 ){
	    			actualizarTablaTareas(proyecto);
	    		}
	    		
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
    	
    }
    
    private static void actualizarTablaTareas(Proyecto proyecto) {
    	Map<Long, Tarea> mapaTareas = proyecto.getTareas();
    	removerTodasFilasTabla(getTablaTareasGlobal().getModel());
    	DefaultTableModel modeloTareas = (DefaultTableModel) getTablaTareasGlobal().getModel();
    	for ( Long idTarea : mapaTareas.keySet() ){
    		Tarea tarea = mapaTareas.get(idTarea);
			modeloTareas.addRow(tarea.getFilaDeTabla());
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
    		if (subTareaSeleccionada == null)
	        	return;
    		/*if (!Utiles.verificarUsuario()){
    			SpiritAlert.createAlert("Solo el usuario asignado a esta tarea puede eliminar la subtarea"
    					, SpiritAlert.WARNING);
    			return;
    		}*/
    		
    		if ( Utiles.continuar("Desea eliminar la subtarea ???. " 
    				+"\nLos datos ser\u00e1n borrados permanentemente", "Eliminar Subtarea") ){
	    		
	    		if (subTareaSeleccionada.getId() != null){
	    			SessionServiceLocator.getTiempoParcialDotSessionService().eliminarTiempoParcialDot(
	    				subTareaSeleccionada.getId() );
	    		}
	    		
	    		long segundosSubtarea = subTareaSeleccionada.getSegundos();
	    		tarea.setSegundosTotales(tarea.getSegundosTotales()-segundosSubtarea);
	    		//proyecto.setSegundosRestados(segundosSubtarea);
	    		proyecto.setSegundosTotales(proyecto.getSegundosTotales()-segundosSubtarea);
	
	    		((ArrayList<SubTarea>)tarea.getSubTareas()).remove(Utiles.getTablaSubTareasGlobal().getSelectedRow());
	
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
