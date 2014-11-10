package com.spirit.timeTracker.gui.model;

import static com.spirit.timeTracker.gui.model.Utiles.getMapaProyectosGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.getSubTareaHiloContadorGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.getTablaSubTareasDetalleGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.getTablaSubTareasGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.getTablaTareasGlobal;
import static com.spirit.timeTracker.gui.model.Utiles.removerTodasFilasTabla;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import timeTracker.tiempo.Proyecto;
import timeTracker.tiempo.SubTarea;
import timeTracker.tiempo.SubTareaDetalle;
import timeTracker.tiempo.Tarea;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoEJB;
import com.spirit.general.session.EmpleadoSessionService;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoEJB;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.TiempoParcialDotDetalleIf;
import com.spirit.medios.entity.TiempoParcialDotIf;
import com.spirit.medios.session.OrdenTrabajoDetalleSessionService;
import com.spirit.medios.session.OrdenTrabajoSessionService;
import com.spirit.medios.session.TiempoParcialDotDetalleSessionService;
import com.spirit.medios.session.TiempoParcialDotSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.timeTracker.componentes.TiempoCellRenderer;
import com.spirit.timeTracker.gui.main.JPOrdenTrabajo;

public class PanelListaProyectosModel extends JPOrdenTrabajo {
    
    private static final long serialVersionUID = 1L;
	public static final int COLUMNA_CODIGO_ORDEN_TAREA = 0;
    public static final int COLUMNA_TIEMPO_ORDEN_TAREA = 1;
    
    private static Proyecto proyectoActivoLocal;
    static int numeroProyectos=0;
    
    static DefaultTableModel modeloTablaProyectos;
    
    private JSplitPane splitPaneContenido = null;
    private PanelListaSubTareasModel panelListSubTareas = null;
    
    private javax.swing.JMenuItem miActualizar;
    private javax.swing.JPopupMenu popupProyecto;
    
    public PanelListaProyectosModel() {
    	iniciarComponente();
    	crearPopUp();
    	modeloTablaProyectos = (DefaultTableModel)getTblProyectos().getModel();
        modificarTabla();
        iniciarListeners();
        //llenar();
    }
    
    private void iniciarComponente() {
		// TABLA
		// MiAccion miAccion = new MiAccion();
		/*
		 * getTblTareas().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),
		 * "none");
		 * getTblTareas().getInputMap().put(KeyStroke.getKeyStroke("released
		 * SPACE"), "released"); getTblTareas().getActionMap().put("pressed",
		 * miAccion); getTblTareas().getActionMap().put("released", miAccion);
		 */
		getTblProyectos().getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
				"doNothing");
		getTblProyectos().getActionMap().put("doNothing", doNothing);
	}

	Action doNothing = new AbstractAction() {
		private static final long serialVersionUID = 8289909116937822402L;
		public void actionPerformed(ActionEvent e) {
		}
	};
	
    private void crearPopUp(){
        setPopupProyecto(new javax.swing.JPopupMenu());
        miActualizar = new javax.swing.JMenuItem();

        getPopupProyecto().setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proyectos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0)));
        miActualizar.setText("Actualizar");
        getPopupProyecto().add(miActualizar);
        miActualizar.setIcon(new ImageIcon("images/timetracker/refresh.png"));
        
    	getTblProyectos().setComponentPopupMenu(popupProyecto);
        getJScrollPane1().setComponentPopupMenu(popupProyecto);
    }
    
    private void iniciarListeners(){
        //TABLA
        getTblProyectos().addMouseListener(getMlTablaProyecto());
        getTblProyectos().addKeyListener(getKlTablaProyecto());
        //((AbstractTableModel)getTblProyectos().getModel()).addTableModelListener(
        //		getTmlTablaProyectos());
        
        //POPUP MENU
        getPopupProyecto().addPopupMenuListener(getPmlPopUpProyecto());
        miActualizar.addActionListener(getAlPopupMenuProyecto());
        //getMiEliminar().addActionListener(getAlPopupMenuProyecto());
    }
    
    
    
    private PopupMenuListener pmlPopUpProyecto = new PopupMenuListener() {
        public void popupMenuCanceled(PopupMenuEvent e) {
        }
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            actualizarMenuPopUp();
        }
    };
    
    private void actualizarMenuPopUp(){
    	if ( getSubTareaHiloContadorGlobal() == null )
    		miActualizar.setEnabled(true);
    	else
    		miActualizar.setEnabled(false);
        
    }
    
    public void modificarTabla(){
        //MODO DE SELECCION
        getTblProyectos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //getTblProyectos().getColumnModel().getColumn(COLUMNA_TIEMPO_PROYECTO).setMinWidth(55);
        TiempoCellRenderer tiempoCellRenderer = new TiempoCellRenderer();
        getTblProyectos().getColumnModel().getColumn(COLUMNA_TIEMPO_ORDEN_TAREA).setCellRenderer(tiempoCellRenderer);
        getTblProyectos().getColumnModel().getColumn(COLUMNA_TIEMPO_ORDEN_TAREA).setMaxWidth(55);
    }
    
    private ActionListener alPopupMenuProyecto = new ActionListener() {
        public void actionPerformed(ActionEvent acev) {
            if ( acev.getSource() instanceof JMenuItem){
                String comando = acev.getActionCommand();
                if ( "actualizar".equalsIgnoreCase(comando) ){
                	actualizarOrdenesTrabajos();
                	
                	int numFilas = getTblProyectos().getRowCount()-1;
    				if (numFilas>0){
    			        getTblProyectos().setRowSelectionInterval(numFilas,numFilas);
    			        removerTodasFilasTabla(getTablaTareasGlobal().getModel());
    			        removerTodasFilasTabla(getTablaSubTareasGlobal().getModel());
    			        removerTodasFilasTabla(getTablaSubTareasDetalleGlobal().getModel());
    				}
                }
            }
        }
    };
    
    private MouseListener mlTablaProyecto = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {
        	int filaSeleccionada = getTblProyectos().getSelectedRow();
        	if ( filaSeleccionada >= 0 ){
        		Utiles.mostrarInformacionProyectoGlobal();
	            llenarTablaTareas();
        	} else{
				Utiles.borrarInformacionPanelGlobal();
        		removerTodasFilasTabla(Utiles.getTablaTareasGlobal().getModel());
        		removerTodasFilasTabla(Utiles.getTablaSubTareasGlobal().getModel());
        		removerTodasFilasTabla(getTablaSubTareasDetalleGlobal().getModel());
        	}
        		
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    };
    
    private KeyListener klTablaProyecto = new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
        }
        public void keyReleased(KeyEvent kev) {
            int filaSeleccionada = getTblProyectos().getSelectedRow();
            if ( filaSeleccionada >= 0 ){
            	if ( kev.getKeyCode() == KeyEvent.VK_DOWN || 
            			kev.getKeyCode() == KeyEvent.VK_UP){
		            Utiles.mostrarInformacionProyectoGlobal();
            		llenarTablaTareas();
            	}
            } else {
            	Utiles.borrarInformacionPanelGlobal();
        		removerTodasFilasTabla(Utiles.getTablaTareasGlobal().getModel());
        		removerTodasFilasTabla(Utiles.getTablaSubTareasGlobal().getModel());
        		removerTodasFilasTabla(getTablaSubTareasDetalleGlobal().getModel());
            }
        }
        public void keyTyped(KeyEvent e) {}
    };
    
    /*private void mostrarInformacionOrdenTrabajo() {
		try{
			Proyecto proyecto = Utiles.getProyectoActivoGlobal();

			InfoOrdenTrabajoModel infoOrdenTrabajo = 
				new InfoOrdenTrabajoModel(proyecto.getOrdenTrabajo());
			new InformacionModel(
					infoOrdenTrabajo,"Orden de Trabajo",550,230);
		} catch(Exception ex){
			if (ex instanceof GenericBusinessException)
				SpiritAlert.createAlert(ex.getMessage(), SpiritAlert.ERROR);
			else
				SpiritAlert.createAlert("Ocurrio un error al obtener información de la Orden de Trabajo"
						, SpiritAlert.ERROR);
		}
	}*/
    
    /*private TableModelListener tmlTablaProyectos = new TableModelListener() {
        public void tableChanged(TableModelEvent e) {
            if ( getSubTareaHiloContadorGlobal()==null ){
                //SE HACE SI ES QUE NO HAY CONTEO EN UNA SUBTAREA
                if ( getNumeroProyectos() == getTblProyectos().getRowCount() ){
                    if (getTblProyectos().getSelectedRow() >= 0){
                        int row = e.getFirstRow();
                        TableModel model = (TableModel)e.getSource();
                        String columnName = model.getColumnName(COLUMNA_CODIGO_ORDEN_TAREA);
                        String nombreNuevo = (String) model.getValueAt(row, COLUMNA_CODIGO_ORDEN_TAREA);
                        //guardarNombreProyecto(row,nombreNuevo);
                        //System.out.println("-->"+getTblProyectos().getRowCount());
                    }
                }
            }
        }
    };*/

    public void actualizarOrdenesTrabajos(){

    	if ( !Utiles.continuar("¿ Desea continuar ? Si no ha guardado el tiempo trabajado" +
    			", se perder\u00e1n","Actualizar Ordenes de Trabajo") )
    		return;
    	Map parametros = new HashMap();
    	getMapaProyectosGlobal().clear();

    	removerTodasFilasTabla(getTblProyectos().getModel());
    	removerTodasFilasTabla(Utiles.getTablaTareasGlobal().getModel());
    	Utiles.borrarInformacionPanelGlobal();
    	removerTodasFilasTabla(Utiles.getTablaSubTareasGlobal().getModel());
    	removerTodasFilasTabla(Utiles.getTablaSubTareasDetalleGlobal().getModel());

    	try {
    		Collection idAsignadolista= getEmpleadoSessionService().findEmpleadoByEmpresaIdAndByUsuario(
    				Parametros.getIdEmpresa(),Parametros.getUsuario().toLowerCase());
    		Iterator itAsignados = idAsignadolista.iterator();
    		if (itAsignados.hasNext()){

    			long idAsignado = ((EmpleadoEJB)itAsignados.next()).getId().longValue();
    			ArrayList<Object> ordenesTrabajos = (ArrayList<Object>)getOrdenTrabajoSessionService()
    			.findOrdenTrabajoByQueryAndByAsignadoa(0,10,parametros
    					,idAsignado,Parametros.getIdEmpresa());
    			HashMap<String,Proyecto> proyectosAntiguos =  getMapaProyectosGlobal();
    			Iterator itOrdenesTrabajos = ordenesTrabajos.iterator();
    			while(itOrdenesTrabajos.hasNext()){
    				OrdenTrabajoEJB ordenTrabajo = (OrdenTrabajoEJB) itOrdenesTrabajos.next();
    				addOrdenTrabajo(proyectosAntiguos,ordenTrabajo);
    			}
    		} else {
    			SpiritAlert.createAlert("No existe Empleado con usuario \""
    					+Parametros.getUsuario()+"\"", SpiritAlert.WARNING);
    		}
    	} catch (Exception e) {
    		SpiritAlert.createAlert("Ocurrio un error en la actualizaci\u00f3n de las Ordenes de Trabajo"
    				, SpiritAlert.WARNING);
    		e.printStackTrace();
    	}

    }
    
    /*private void guardarNombreProyecto(int filaSeleccionada,String nombreNuevo){
        Proyecto proyecto = getProyectoActivoLocal();
        if ( !nombreNuevo.equals(proyecto.getNombreProyecto()) ){
            if ( !existeProyectoByName(nombreNuevo) ){
                proyecto.setNombreProyecto(nombreNuevo);
            } else{
                getTblProyectos().getModel().setValueAt(
                        proyecto.getNombreProyecto(),filaSeleccionada,COLUMNA_NOMBRE_PROYECTO);
                JOptionPane.showMessageDialog(null,
                        "No puede haber dos proyectos con el mismo Nombre",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }*/
    
    /*static boolean existeProyectoByName(String nombreProyecto){
        Iterator it = getMapaProyectosGlobal().keySet().iterator();
        while( it.hasNext() ){
            String nombreExistente=( (Proyecto) getMapaProyectosGlobal().get(it.next()) ).getNombreProyecto();
            if ( nombreProyecto.equalsIgnoreCase(nombreExistente) )
                return true;
        }
        return false;
    }*/
    
    /*static long getNuevoIdProyecto(){
    	Long maximo=0L;
        Iterator it = getMapaProyectosGlobal().keySet().iterator();
        while ( it.hasNext() ){
            Proyecto proyecto = (Proyecto) getMapaProyectosGlobal().get(it.next());
            if ( proyecto.getIdProyecto()>maximo )
                maximo = proyecto.getIdProyecto();
        }
        return maximo+1;
    }*/
    
    /*static String getNuevoNombreProyecto(){
        int contador=getNumeroProyectos()+1;
        String nombre = "Proyecto"+contador;
        while ( existeProyectoByName(nombre) ){
            contador++;
            nombre = "Proyecto"+contador;
        }
        return nombre;
    }*/
    
    /*
     * Llena la tabla de tareas con las tareas que le pertenecen
     * al proyecto seleccionado. 
     */
    private void llenarTablaTareas(){
        //setModeloTablaProyectos((DefaultTableModel) getTblProyectos().getModel());
        //if ( getSplitPaneContenido()!=null )
        //    getSplitPaneContenido().setRightComponent( getPanelListSubTareas() );
        //limpiarPanelInformacion();
        //String nombreProyecto = (String) getModeloTablaProyectos().getValueAt(
        //        filaSeleccionada, COLUMNA_CODIGO_ORDEN_TAREA);
        //Proyecto proyecto = getProyectoActivoByNombre(nombreProyecto);
    	try{
	        Proyecto proyecto = Utiles.getProyectoActivoGlobal();
	        //System.out.println("Seeccionado : "+proyecto.getNombreProyecto());
	        habilitarImpresionTablaTareas(proyecto);
	        setProyectoActivoLocal(proyecto);
	        Iterator itTarea=null;
	        if ( proyecto != null ){
	            removerTodasFilasTabla(getTablaTareasGlobal().getModel());
	            removerTodasFilasTabla(getTablaSubTareasGlobal().getModel());
        		removerTodasFilasTabla(getTablaSubTareasDetalleGlobal().getModel());
        		
	            DefaultTableModel modeloTareas = (DefaultTableModel) getTablaTareasGlobal().getModel();
	            itTarea = proyecto.getTareas().keySet().iterator();
	            while( itTarea.hasNext() ){
	                Tarea tarea = (Tarea) proyecto.getTareas().get(itTarea.next());
	                modeloTareas.addRow(tarea.getFilaDeTabla());
	            }
	        }
	        /*if ( Utiles.getTablaTareasGlobal().getRowCount()>0 ){
	            //getTablaTareasGlobal().setRowSelectionInterval(0,0);
	            itTarea = proyecto.getTareas().keySet().iterator();
	            if ( itTarea.hasNext() ){
	                Tarea tarea = (Tarea) proyecto.getTareas().get(itTarea.next());
	                habilitarImpresionTablaSubTareas(tarea);
	                llenarTablaSubTareas(tarea);
	            }
	        }*/
    	} catch(Exception ex){
    		if ( ex instanceof GenericBusinessException)
    			SpiritAlert.createAlert(ex.getMessage(), SpiritAlert.INFORMATION);
    		else
    			SpiritAlert.createAlert("Ocurrio un error al cargar la tabla de tareas"
    					, SpiritAlert.INFORMATION);
    	}
    }
    /*private void llenarTablaTareas(int filaSeleccionada){
        //setModeloTablaProyectos((DefaultTableModel) getTblProyectos().getModel());
        if ( getSplitPaneContenido()!=null )
            getSplitPaneContenido().setRightComponent( getPanelListSubTareas() );
        limpiarPanelInformacion();
        String nombreProyecto = (String) getModeloTablaProyectos().getValueAt(
                filaSeleccionada, COLUMNA_CODIGO_ORDEN_TAREA);
        //Proyecto proyecto = getProyectoActivoByNombre(nombreProyecto);
        Proyecto proyecto = Utiles.getProyectoActivoGlobal();
        //System.out.println("Seeccionado : "+proyecto.getNombreProyecto());
        habilitarImpresionTablaTareas(proyecto);
        setProyectoActivoLocal(proyecto);
        Iterator itTarea=null;
        if ( proyecto != null ){
            removerTodasFilasTabla(getTablaSubTareasGlobal().getModel());
            removerTodasFilasTabla(getTablaTareasGlobal().getModel());
            DefaultTableModel modeloTareas = (DefaultTableModel) getTablaTareasGlobal().getModel();
            itTarea = proyecto.getTareas().keySet().iterator();
            while( itTarea.hasNext() ){
                Tarea tarea = (Tarea) proyecto.getTareas().get(itTarea.next());
                modeloTareas.addRow(tarea.getFilaDeTabla());
            }
            setNumeroTareasGlobales(getTablaTareasGlobal().getRowCount());
            //System.out.println("NUmero de tareas: "+getNumeroTareasGlobales());
        }
        if ( getTablaTareasGlobal().getRowCount()>0 ){
            getTablaTareasGlobal().setRowSelectionInterval(0,0);
            itTarea = proyecto.getTareas().keySet().iterator();
            if ( itTarea.hasNext() ){
                Tarea tarea = (Tarea) proyecto.getTareas().get(itTarea.next());
                habilitarImpresionTablaSubTareas(tarea);
                llenarTablaSubTareas(tarea);
            }
        }
    }*/
    
    /*private void eliminarProyecto(){
        //
        //System.out.println("Seleccionado: "+ getTblProyectos().getSelectedRow() );
        Proyecto proyectoSeleccionado = getProyectoActivoLocal();
        if ( proyectoSeleccionado != null ){
            if (getSubTareaHiloContadorGlobal() == null){
                int filaSeleccionada = getTblProyectos().getSelectedRow();
                getMapaProyectosGlobal().remove(proyectoSeleccionado.getIdProyecto());
                getModeloTablaProyectos().removeRow(filaSeleccionada);
                removerTodasFilasTabla(getTablaTareasGlobal().getModel());
                removerTodasFilasTabla(getTablaSubTareasGlobal().getModel());
                setNumeroProyectos(getNumeroProyectos()-1);
                
                //System.out.println("NUmero de tareas: "+getNumeroTareasGlobales());
            } else {
                Proyecto proyectoCorriendo = getSubTareaHiloContadorGlobal().getProyecto();
                if ( proyectoCorriendo != proyectoSeleccionado ){
                    System.out.println("Se elimina proyecto, uno en proceso");
                } else{
                    JOptionPane.showMessageDialog(null,
                            "No puede eliminar una Proyecto en Proceso",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
            
            
        } else{
            JOptionPane.showMessageDialog(null,
                    "Debe escoger un proyecto primero",
                    "Mensaje",
                    JOptionPane.WARNING_MESSAGE);
        }
    }*/
    
    public void addOrdenTrabajo(HashMap<String,Proyecto> proyectosAntiguos,OrdenTrabajoIf ordenTrabajo){
    	long segundosProyecto = 0L;
    	long segundosTarea = 0L;
    	//if ( !getMapaProyectosGlobal().containsKey(ordenTrabajo.getCodigo()) ){
	        Proyecto proyectoNuevo = new Proyecto(ordenTrabajo.getCodigo(),ordenTrabajo);
	        //Proyecto proyectoViejo = getMapaProyectosGlobal().get(ordenTrabajo.getCodigo());
	        /*if (proyectoViejo!=null){
	        	proyectoNuevo.setSegundosAumentados(proyectoViejo.getSegundosAumentados());
	        	proyectoNuevo.setSegundosRestados(proyectoViejo.getSegundosRestados());
	        	proyectoNuevo.setSegundosTotales(proyectoViejo.getSegundosTotales());
	        }*/
	        try {
				Collection listaOrdenDetalle = getOrdenTrabajoDetalleSessionService()
					.findOrdenTrabajoDetalleByOrdenId(ordenTrabajo.getId());
				Iterator it = listaOrdenDetalle.iterator();
		        while (it.hasNext()) {
					OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) it.next();
					Tarea tareaNueva = new Tarea(ordenTrabajoDetalleIf.getId().longValue()
							,ordenTrabajoDetalleIf);
					Collection tiemposParciales = getTiempoParcialDotSessionService()
						.findTiempoParcialDotByIdOrdenTrabajoDetalle(ordenTrabajoDetalleIf.getId());
					Iterator itTiemposParciales = tiemposParciales.iterator();
					while(itTiemposParciales.hasNext()){
						TiempoParcialDotIf tiempoParcial = (TiempoParcialDotIf) itTiemposParciales.next();
						SubTarea subTareaNueva = new SubTarea(tiempoParcial);
						segundosTarea += subTareaNueva.getSegundos();
						segundosProyecto += subTareaNueva.getSegundos();
						Collection tiemposDetalles = getTiempoParcialDotDetalleSessionService()
							.findTiempoParcialDotDetalleByIdTiempoParcialDot(tiempoParcial.getId());
						Iterator itTiempoDetalle = tiemposDetalles.iterator();
						while(itTiempoDetalle.hasNext()){
							TiempoParcialDotDetalleIf tiempoDetalle = (TiempoParcialDotDetalleIf) itTiempoDetalle.next();
							SubTareaDetalle detalle = new SubTareaDetalle( tiempoDetalle);
							
							subTareaNueva.getDetalle().add(detalle);
						}
						tareaNueva.getSubTareas().add(subTareaNueva);
					}
					tareaNueva.setSegundosTotales(segundosTarea);
					proyectoNuevo.getTareas().put(tareaNueva.getIdTarea(), tareaNueva);
				}
		        proyectoNuevo.setSegundosTotales(segundosProyecto);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			}
	        
	        getMapaProyectosGlobal().put(proyectoNuevo.getIdProyecto(),proyectoNuevo);
	        getModeloTablaProyectos().addRow(proyectoNuevo.getFilaDeTabla());
	        setProyectoActivoLocal(proyectoNuevo);
	        setNumeroProyectos(getNumeroProyectos() + 1);
    	//}
    }
    
    
    
    private void habilitarImpresionTablaTareas(Proyecto proyecto){
        if ( getSubTareaHiloContadorGlobal() != null ){
            Proyecto proyectoHiloContador = getSubTareaHiloContadorGlobal().getProyecto();
            if ( proyecto != proyectoHiloContador ){
                getSubTareaHiloContadorGlobal().setImprimirEnTablaTareas(false);
            } else{
                getSubTareaHiloContadorGlobal().setImprimirEnTablaTareas(true);
            }
        }
    }
  
     
    public ActionListener getAlPopupMenuProyecto() {
        return alPopupMenuProyecto;
    }
    
    public void setAlPopupMenuProyecto(ActionListener alPopupMenuProyecto) {
        this.alPopupMenuProyecto = alPopupMenuProyecto;
    }
    
    static int getNumeroProyectos() {
        return numeroProyectos;
    }
    
    public static void setNumeroProyectos(int numeroProyectosS) {
        numeroProyectos = numeroProyectosS;
    }
    
    public MouseListener getMlTablaProyecto() {
        return mlTablaProyecto;
    }
    
    public void setMlTablaProyecto(MouseListener mlTablaProyecto) {
        this.mlTablaProyecto = mlTablaProyecto;
    }
    
    public static DefaultTableModel getModeloTablaProyectos() {
        return modeloTablaProyectos;
    }
    
    public KeyListener getKlTablaProyecto() {
        return klTablaProyecto;
    }
    
    public void setKlTablaProyecto(KeyListener klTablaProyecto) {
        this.klTablaProyecto = klTablaProyecto;
    }
    
    public JSplitPane getSplitPaneContenido() {
        return splitPaneContenido;
    }
    
    public void setSplitPaneContenido(JSplitPane splitPaneContenido) {
        this.splitPaneContenido = splitPaneContenido;
    }
    
    public PopupMenuListener getPmlPopUpProyecto() {
        return pmlPopUpProyecto;
    }
    
    public void setPmlPopUpProyecto(PopupMenuListener pmlPopUpProyecto) {
        this.pmlPopUpProyecto = pmlPopUpProyecto;
    }
    
    public PanelListaSubTareasModel getPanelListSubTareas() {
        return panelListSubTareas;
    }
    
    public void setPanelListSubTareas(PanelListaSubTareasModel panelListSubTareas) {
        this.panelListSubTareas = panelListSubTareas;
    }
    
    public static Proyecto getProyectoActivoLocal() {
        return proyectoActivoLocal;
    }

    public static void setProyectoActivoLocal(Proyecto aProyectoActivoLocal) {
        proyectoActivoLocal = aProyectoActivoLocal;
    }

    public javax.swing.JPopupMenu getPopupProyecto() {
        return popupProyecto;
    }

    public void setPopupProyecto(javax.swing.JPopupMenu popupProyecto) {
        this.popupProyecto = popupProyecto;
    }
    
}
