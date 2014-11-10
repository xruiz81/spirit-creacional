package com.spirit.timeTracker.gui.model;

import static com.spirit.timeTracker.gui.model.PanelListaProyectosModel.COLUMNA_CODIGO_ORDEN_TAREA;
import static com.spirit.timeTracker.gui.model.PanelListaTareasModel.COLUMNA_NOMBRE_TAREA;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import timeTracker.tiempo.Proyecto;
import timeTracker.tiempo.SubTarea;
import timeTracker.tiempo.SubTareaDetalle;
import timeTracker.tiempo.SubTareaHilo;
import timeTracker.tiempo.Tarea;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.timeTracker.componentes.SubTareaListener;
import com.spirit.timeTracker.gui.model.cache.MapaCache;


public class Utiles {

	static JFrame timeTrackerVentana = null;
	
	static SubTareaListener subtatareaListener = new SubTareaListener();

	private static SubTarea subTareaActiva = null;
	private static SubTareaHilo subTareaHiloContadorGlobal = null;
	private static JTable tablaProyectosGlobal = null;
	private static HashMap<String,Proyecto> mapaProyectosGlobal = null;
	private static JTable tablaTareasGlobal = null;
	private static JTable tablaSubTareasGlobal = null;
	private static JTable tablaSubTareasDetalleGlobal = null;
	
	static JScrollPane contenedorPanelInformacion = null;
	static Map mapaContador = new HashMap();

	public Utiles() {
	}

	public static boolean verificarGuardadoAntesCierre(){
		
		if ( existenCambios() )
			return continuar("Desea guardar los cambios realizados ?", "Cambios");
		
		return false;
	}
	
	public static boolean existenCambios(){
		try {
			pararHiloContadorGlobal();
		} catch (GenericBusinessException e) {
			//e.printStackTrace();
			//SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
		for ( String codigoProyecto : mapaProyectosGlobal.keySet() ){
			Proyecto proyecto = mapaProyectosGlobal.get(codigoProyecto);
			Map<Long, Tarea> mapaTareas =  proyecto.getTareas();
			for ( Long idTarea : mapaTareas.keySet() ){
				Tarea tarea = mapaTareas.get(idTarea);
				Collection<SubTarea> subtareas = tarea.getSubTareas();
				for ( SubTarea subtarea : subtareas ){
					if ( subtarea.getId() == null ){
						return true;
					} else {
						Collection<SubTareaDetalle> detalles = subtarea.getDetalle();
						for ( SubTareaDetalle detalle : detalles ){
							if ( detalle.getTiempoParcialDetalle().getId() == null ){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public static void iniciarHiloContadorGlobal(SubTarea subTarea) 
	throws GenericBusinessException{
		try{
			
			//todo este if es en caso de que exita una sola tarea
			//cuando esto paso se da clic en play solo seleccionando la orden de trabajo
			//por lo que la tarea y subtarea no se selecciona y queda como exception
			if(Utiles.getProyectoActivoGlobal().getTareas().size() == 1){
				boolean existeUsuario = false;
				Iterator tareasIterator = Utiles.getProyectoActivoGlobal().getTareas().keySet().iterator();
    			while(tareasIterator.hasNext()){
    				Long unicaTareaId = (Long)tareasIterator.next();
    				Tarea unicaTarea = Utiles.getProyectoActivoGlobal().getTareas().get(unicaTareaId);
    				if(unicaTarea.getSubTareas().size() == 1){
    					Proyecto proyecto = Utiles.getProyectoActivoGlobal();
    					Tarea tarea = Utiles.getTareaActivaByOrden(proyecto,0);
    					setSubTareaHiloContadorGlobal(new SubTareaHilo(getProyectoActivoGlobal(),tarea,subTarea) );
    					existeUsuario = true;
    				}else{
    					UsuarioIf usuario = (UsuarioIf) Parametros.getUsuarioIf();
    					int contador = 0;
    					Iterator subTareasIt = unicaTarea.getSubTareas().iterator();
    					while(subTareasIt.hasNext()){
    						SubTarea subTareaTemp = (SubTarea)subTareasIt.next();
    						if(!existeUsuario && 
    								subTareaTemp.getTiempoParcial().getUsuarioAsignadoId().compareTo(usuario.getEmpleadoId()) == 0){
    							existeUsuario = true;
    							Proyecto proyecto = Utiles.getProyectoActivoGlobal();
    	    					Tarea tarea = Utiles.getTareaActivaByOrden(proyecto,0);
    	    					setSubTareaHiloContadorGlobal(new SubTareaHilo(getProyectoActivoGlobal(),tarea,subTarea) );
    						}
    						contador++;
    					}
    				}
    				
    				if(!existeUsuario){
    					setSubTareaHiloContadorGlobal(new SubTareaHilo(getProyectoActivoGlobal(),getTareaActivaGlobal(),subTarea) );
    				}
    			}
    		}else{
    			setSubTareaHiloContadorGlobal(new SubTareaHilo(getProyectoActivoGlobal(),getTareaActivaGlobal(),subTarea) );
    		}
			
			//setSubTareaHiloContadorGlobal(new SubTareaHilo(getProyectoActivoGlobal(),getTareaActivaGlobal(),subTarea) );
			getSubTareaHiloContadorGlobal().setTablaSubTareaDetalle(getTablaSubTareasDetalleGlobal());
			getSubTareaHiloContadorGlobal().setTablaSubTareas(getTablaSubTareasGlobal());
			//getSubTareaHiloContadorGlobal().setFilaTablaSubTarea(getTablaSubTareasGlobal().getRowCount()-1);
			getSubTareaHiloContadorGlobal().setFilaTablaSubTarea(getTablaSubTareasGlobal().getSelectedRow());
			getSubTareaHiloContadorGlobal().setTablaTareas(getTablaTareasGlobal());
			getSubTareaHiloContadorGlobal().setTablaProyectos(getTablaProyectosGlobal());
			getSubTareaHiloContadorGlobal().start();
		} catch(Exception ex){
			ex.printStackTrace();
			if (ex instanceof GenericBusinessException)
				throw new GenericBusinessException(ex.getMessage());
			throw new GenericBusinessException("Ocurrio un error al Iniciar el Contador de la Subtarea");
		}
	}

	public static void pararHiloContadorGlobal() throws GenericBusinessException{
		if (getSubTareaHiloContadorGlobal()!=null){
			getSubTareaHiloContadorGlobal().parar();

			SubTarea subTareaCorriendo = getSubTareaHiloContadorGlobal().getSubTarea();
			SubTareaDetalle detalle = ((ArrayList<SubTareaDetalle>)subTareaCorriendo.getDetalle()).get(subTareaCorriendo.getDetalle().size()-1);
			Date fecha_hora_fin = new Date(); 
			detalle.setHoraFin(fecha_hora_fin);
			subTareaCorriendo.setFechaFin(fecha_hora_fin);
			
			
			//todo este if es en caso de que exita una sola tarea
			//cuando esto paso se da clic en play solo seleccionando la orden de trabajo
			//por lo que la tarea y subtarea no se selecciona y queda como exception
			SubTarea subTareaSeleccionada = null;
    		if(Utiles.getProyectoActivoGlobal().getTareas().size() == 1){
    			Iterator tareasIterator = Utiles.getProyectoActivoGlobal().getTareas().keySet().iterator();
    			while(tareasIterator.hasNext()){
    				Long unicaTareaId = (Long)tareasIterator.next();
    				Tarea unicaTarea = Utiles.getProyectoActivoGlobal().getTareas().get(unicaTareaId);
    				if(unicaTarea.getSubTareas().size() == 1){
    					Proyecto proyecto = Utiles.getProyectoActivoGlobal();
    					Tarea tarea = Utiles.getTareaActivaByOrden(proyecto,0);
    					subTareaSeleccionada = ((ArrayList<SubTarea>)tarea.getSubTareas()).get(0);
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
    	    					subTareaSeleccionada = ((ArrayList<SubTarea>)tarea.getSubTareas()).get(contador);
    						}
    						contador++;
    					}
    				}
    			}
    		}
    		
    		if(subTareaSeleccionada == null){
    			subTareaSeleccionada = Utiles.getSubTareaActivaGlobal();
    		}
			
			//SubTarea subTareaSeleccionada = Utiles.getSubTareaActivaGlobal();
				
			
			if ( subTareaSeleccionada!= null && subTareaSeleccionada == subTareaCorriendo ){
				mostrarSubTareaDetalleGlobal(subTareaSeleccionada);
			}

			setSubTareaHiloContadorGlobal(null);
			
		} else {
			throw new GenericBusinessException("No existe una Subtarea en ejecucion");
		}	
	}

	private static void actualizarTiempoSubtarea(SubTarea subTarea){
		long suma = 0L;
		for (SubTareaDetalle detalle: subTarea.getDetalle()){
			suma += detalle.getSegundos();
		}
		subTarea.getTiempoParcial().setTiempo(suma);
	}

	private static void actualizarTiempoTarea(Tarea tarea){
		long suma = 0L;
		for (SubTarea subTarea: tarea.getSubTareas()){
			suma += subTarea.getSegundos();
		}
		tarea.setSegundosTotales(suma);
	}

	public static void llenarTablaSubTareasGlobal(Tarea tarea){
		removerTodasFilasTabla(getTablaSubTareasGlobal().getModel());
		//Iterator itSubTarea = tarea.getSubTareas().keySet().iterator();
		for (int i=0;i<tarea.getSubTareas().size();i++){
			SubTarea subTarea = ((ArrayList<SubTarea>)tarea.getSubTareas()).get(i);
			((DefaultTableModel)getTablaSubTareasGlobal().getModel()).addRow(subTarea.getFilaDeTabla());
		}
	}

	public static void llenarTablaSubTareas(Tarea tarea){
		if ( tarea != null ){
			if ( getSubTareaHiloContadorGlobal() != null ){
				Tarea tareaHilContador = getSubTareaHiloContadorGlobal().getTarea();
				if ( tarea != tareaHilContador )
					getSubTareaHiloContadorGlobal().setImprimirEnTablaSubTarea(false);
				else
					getSubTareaHiloContadorGlobal().setImprimirEnTablaSubTarea(true);
			}
			removerTodasFilasTabla(getTablaSubTareasGlobal().getModel());
			//Iterator itSubTarea = tarea.getSubTareas().keySet().iterator();
			for (int i=0;i<tarea.getSubTareas().size();i++){
				SubTarea subTarea = ((ArrayList<SubTarea>)tarea.getSubTareas()).get(i);
				((DefaultTableModel)getTablaSubTareasGlobal().getModel()).addRow(subTarea.getFilaDeTabla());
			}
		}
	}

	public static void borrarInformacionPanelGlobal(){
		contenedorPanelInformacion.setViewportView(new JPanel());
	}
	
	private static LinkedList<SubTareaDetalle> subtareaDetalleActivas=new LinkedList<SubTareaDetalle>();

	public static LinkedList<SubTareaDetalle> getSubtareaDetalleActivas() {
		return subtareaDetalleActivas;
	}
	
	private static LinkedList<SubTarea> subtareasActivas=new LinkedList<SubTarea>();

	public static LinkedList<SubTarea> getSubtareasActivas() {
		return subtareasActivas;
	}

	public static void mostrarSubTareaDetalleGlobal(SubTarea subTarea){
		//SubTarea subTarea = getSubTareaActivaGlobal();
		removerTodasFilasTabla(tablaSubTareasDetalleGlobal.getModel());
		subtareaDetalleActivas.clear();
		DefaultTableModel modelo = (DefaultTableModel) tablaSubTareasDetalleGlobal.getModel();
		
		for (Iterator itSubtarea=subTarea.getDetalle().iterator() ;itSubtarea.hasNext();){
			SubTareaDetalle detalle = (SubTareaDetalle) itSubtarea.next();
			subtareaDetalleActivas.add(detalle);
			modelo.addRow(detalle.getFileTabla());
		}
	}

	public static void mostrarInformacionSubTareaGlobal(){
		try {
			SubTarea subTarea = getSubTareaActivaGlobal();
			if (subTarea != null){
				InfoSubtareaModel infoSubtarea = new InfoSubtareaModel(subTarea);
				contenedorPanelInformacion.setSize(new Dimension(350,300));
				contenedorPanelInformacion.setViewportView(infoSubtarea);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
			e.printStackTrace();
		}
	}

	static void mostrarInformacionTareaGlobal(){
		try {
			Tarea tarea = getTareaActivaGlobal();
			InfoTareaOrdenTrabajoModel infoTarea = new InfoTareaOrdenTrabajoModel(tarea.getTareaOrdenTrabajo());
			contenedorPanelInformacion.setViewportView(infoTarea);
			mapaContador.put(tarea.getTareaOrdenTrabajo().getOrdenId(), "");
			System.out.println("ver");
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
			e.printStackTrace();
		}
	}

	static void mostrarInformacionProyectoGlobal(){
		try {
			Proyecto proyecto = getProyectoActivoGlobal();
			InfoOrdenTrabajoModel infoOrdenTrabajo = new InfoOrdenTrabajoModel(proyecto.getOrdenTrabajo());
			contenedorPanelInformacion.setViewportView(infoOrdenTrabajo);
			//mapaContador.put(proyecto.getOrdenTrabajo().getCodigo(), "");
			System.out.println("ver");
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
			e.printStackTrace();
		}
	}

	public static void removerSeleccionesTabla(JTable tabla){
		if ( tabla.getRowCount() > 0 )
			tabla.removeRowSelectionInterval(0,tabla.getRowCount()-1);
	}

	public static SubTarea getSubTareaActiva(){

		/*Tarea tarea = getTareaActiva();
        if ( tarea != null ){
            int filaSubTareaSeleccionada = getTablaSubTareasGlobal().getSelectedRow();
            if ( filaSubTareaSeleccionada >= 0 ){
                Integer idSubTarea = (Integer) getTablaSubTareasGlobal()
                .getModel().getValueAt(filaSubTareaSeleccionada,COLUMNA_ID_SUBTAREA) ;
                SubTarea subTarea = (SubTarea) tarea.getSubTareas().get(idSubTarea);
                if ( subTarea != null ){
                    return subTarea;
                }else{
                    JOptionPane.showMessageDialog(null,
                            "No existe SubTarea",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }*/
		return null;
	}

	public static Tarea getTareaActivaByNombre(Proyecto proyecto,String nombreTarea){
		Iterator it = proyecto.getTareas().keySet().iterator();
		while(it.hasNext()){
			Tarea tarea = (Tarea) proyecto.getTareas().get(it.next());
			if ( nombreTarea.equals(tarea.getNombreTarea()) ){
				return tarea;
			}
		}
		return null;
	}

	public static Tarea getTareaActiva(){
		try{
			Proyecto proyecto = getProyectoActivoGlobal();
			if ( proyecto != null ){
				int filaTareaSeleccionada = getTablaTareasGlobal().getSelectedRow();
				if ( filaTareaSeleccionada >= 0 ){
					String nombreTarea = (String) getTablaTareasGlobal().getModel()
						.getValueAt(filaTareaSeleccionada,COLUMNA_NOMBRE_TAREA);
					return getTareaActivaByNombre(proyecto,nombreTarea);
				} else{
					SpiritAlert.createAlert("Debe escoger una tarea !!", SpiritAlert.WARNING);
				}
			}
		} catch(GenericBusinessException ex){
			ex.printStackTrace();
		}
		return null;
	}

	public static SubTarea getSubTareaActivaGlobal() 
	throws GenericBusinessException{
		try{
			Tarea tarea = getTareaActivaGlobal();
			int filaSubTareaSeleccionada = getTablaSubTareasGlobal().getSelectedRow();
			if ( filaSubTareaSeleccionada >= 0 ){
				/*Integer idSubTarea = (Integer)getTablaSubTareasGlobal()
	    		.getModel().getValueAt(filaSubTareaSeleccionada,COLUMNA_ID_SUBTAREA);
	    		SubTarea subTarea = (SubTarea) tarea.getSubTareas().get(idSubTarea);*/
				SubTarea subTarea = ((ArrayList<SubTarea>)tarea.getSubTareas()).get(filaSubTareaSeleccionada);
				if ( subTarea != null ){
					return subTarea;
				} else
					throw new GenericBusinessException("SubTarea no Existe");
			} 
			throw new GenericBusinessException("Debe seleccionar una SubTarea");
			//SpiritAlert.createAlert("Debe seleccionar una tarea !!", SpiritAlert.INFORMATION);
			//return null;
			
		} catch(Exception ex){
			if (ex instanceof GenericBusinessException)
				throw new GenericBusinessException(ex.getMessage());
			throw new GenericBusinessException("Error en la obtención de la tarea");
		}
	}

	public static Tarea getTareaActivaByOrden(Proyecto proyecto,int numeroFila) 
	throws GenericBusinessException{
		HashMap<Long,Tarea> mapaTareas = proyecto.getTareas();
		int contador = 0;
		for ( Long idTarea : mapaTareas.keySet() ){
			Tarea tarea = proyecto.getTareas().get(idTarea);
			if (contador == numeroFila)
				return tarea;
			contador++;
		}
		throw new GenericBusinessException("No existe Tarea en la lista de tareas, actualizar");
	}

	public static Tarea getTareaActivaGlobal() 
	throws GenericBusinessException{
		try{
			Proyecto proyecto = getProyectoActivoGlobal();
			int filaTareaSeleccionada = getTablaTareasGlobal().getSelectedRow();
			if ( filaTareaSeleccionada >= 0 )
				return getTareaActivaByOrden(proyecto,filaTareaSeleccionada);
			else
				throw new GenericBusinessException("Debe seleccionar una Tarea");
		} catch(Exception ex){
			if (ex instanceof GenericBusinessException)
				throw new GenericBusinessException(ex.getMessage());
			throw new GenericBusinessException("Error en la obtención de la tarea");
		}
	}

	/*public static Proyecto getProyectoActivoByNombre(String nombreProyecto){
        Iterator it = getMapaProyectosGlobal().keySet().iterator();
        while( it .hasNext() ){
            Proyecto proyecto = (Proyecto) getMapaProyectosGlobal().get(it.next());
            if ( nombreProyecto.equals(proyecto.getNombreProyecto()) )
                return proyecto;
        }
        return null;
    }*/

	public static Proyecto getProyectoActivoGlobal() 
	throws GenericBusinessException{
		try {
			int filaProyectoSeleccionado = getTablaProyectosGlobal().getSelectedRow();
			if ( filaProyectoSeleccionado >= 0 ){
				String codigoProyecto = (String) getTablaProyectosGlobal().getModel().getValueAt(filaProyectoSeleccionado,COLUMNA_CODIGO_ORDEN_TAREA);
				//Proyecto proyecto = getProyectoActivoByNombre(nombreProyecto);
				Proyecto proyecto = (Proyecto) mapaProyectosGlobal.get(codigoProyecto);
				return proyecto;
			} else
				throw new GenericBusinessException("Debe seleccionar una Orden de Trabajo");
		} catch(Exception ex){
			if (ex instanceof GenericBusinessException)
				throw new GenericBusinessException(ex.getMessage());
			throw new GenericBusinessException("Error en la obtención de la Orden de Trabajo");
		}
	}

	public static void removerTodasFilasTabla(TableModel modelo){
		DefaultTableModel modeloTabla = (DefaultTableModel) modelo;
		//if (modeloTabla.getDataVector().size()>0 && modeloTabla.getRowCount() > 0 ){
		if (modeloTabla.getRowCount() > 0 ){
			for (int i=(modeloTabla.getRowCount()-1); i>=0;i-- )
				modeloTabla.removeRow(i);
		}
	}
	
	public static  boolean verificarUsuario(){
    	//Long idUsuario = 0L;
    	try {
			UsuarioIf usuario = (UsuarioIf)Parametros.getUsuarioIf();
    		EmpleadoIf empleadoDelUsuario = MapaCache.verificarEmpleadoEnMapa(MapaCache.getMapaEmpleados(), usuario.getEmpleadoId());
    		Long idEmpleadoUsuario = empleadoDelUsuario.getId();
    		if (idEmpleadoUsuario != null) {
	    		//idUsuario = ((EmpleadoEJB)itAsignados.next()).getId().longValue();
	    		
	    		Tarea tarea = Utiles.getTareaActivaGlobal();
				OrdenTrabajoDetalleIf ordenTrabajoDetalle = tarea.getTareaOrdenTrabajo();
				
				EmpleadoIf empleado = MapaCache.verificarEmpleadoEnMapa(MapaCache.getMapaEmpleados(), ordenTrabajoDetalle.getAsignadoaId());
				if ( idEmpleadoUsuario.equals(empleado.getId()) ){
					return true;
				}
	    	} else
	    		SpiritAlert.createAlert("Su cuenta de usuario ha sido eliminada", SpiritAlert.INFORMATION);
	    } catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.INFORMATION);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al Verificar Usuario", SpiritAlert.INFORMATION);
		}
	    return false;
    }	
	
	public static ImageIcon createImageIcon(String path) {
		ImageIcon icono=null;
		try {
			icono = SpiritResourceManager.getImageIcon(path);
		} catch (Exception e) {
			System.err.println("No se pudo crear icono de :"+path+"\n"+e);
		}
		return icono;
	}

	public static boolean continuar(String mensaje,String cabecera){
		Object[] options = {"   Si   ",	"   No   "};
		int n = JOptionPane.showOptionDialog(Utiles.timeTrackerVentana,
				mensaje,cabecera,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		if (n==0)
			return true;
		return false;
	}

	public static JTable getTablaProyectosGlobal() {
		return tablaProyectosGlobal;
	}

	public static void setTablaProyectosGlobal(JTable aTablaProyectosGlobal) {
		tablaProyectosGlobal = aTablaProyectosGlobal;
	}

	public static HashMap<String, Proyecto> getMapaProyectosGlobal() {
		return mapaProyectosGlobal;
	}

	public static void setMapaProyectosGlobal(HashMap<String, Proyecto> aMapaProyectosGlobal) {
		mapaProyectosGlobal = aMapaProyectosGlobal;
	}

	public static JTable getTablaTareasGlobal() {
		return tablaTareasGlobal;
	}

	public static void setTablaTareasGlobal(JTable aTablaTareasGlobal) {
		tablaTareasGlobal = aTablaTareasGlobal;
	}

	public static JTable getTablaSubTareasGlobal() {
		return tablaSubTareasGlobal;
	}

	public static void setTablaSubTareasGlobal(JTable aTablaSubTareasGlobal) {
		tablaSubTareasGlobal = aTablaSubTareasGlobal;
	}

	public static SubTareaHilo getSubTareaHiloContadorGlobal() {
		return subTareaHiloContadorGlobal;
	}

	public static void setSubTareaHiloContadorGlobal(SubTareaHilo aSubTareaHiloContadorGlobal) {
		subTareaHiloContadorGlobal = aSubTareaHiloContadorGlobal;
	}

	public static void setSubTareaActivaGlobal(SubTarea aSubTareaActiva) {
		subTareaActiva = aSubTareaActiva;
	}

	public static JTable getTablaSubTareasDetalleGlobal() {
		return tablaSubTareasDetalleGlobal;
	}

	public static void setTablaSubTareasDetalleGlobal(
			JTable atablaSubTareasDetalleGlobal) {
		tablaSubTareasDetalleGlobal = atablaSubTareasDetalleGlobal;
	}

	
}
