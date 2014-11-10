/*
 * PanelListaTareasModel.java
 *
 * Created on June 18, 2007, 11:12 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.spirit.timeTracker.gui.model;

import static com.spirit.timeTracker.gui.model.Utiles.getSubTareaHiloContadorGlobal;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

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
import timeTracker.tiempo.Tarea;

import com.spirit.client.SpiritAlert;
import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.timeTracker.componentes.TiempoCellRenderer;
import com.spirit.timeTracker.gui.main.JPTareaOrdenTrabajo;

public class PanelListaTareasModel extends JPTareaOrdenTrabajo {

	private static final long serialVersionUID = 1L;

	public static final int COLUMNA_NOMBRE_TAREA = 0;

	public static final int COLUMNA_TIEMPO_TAREA = 1;

	// private int numeroTareas=0;

	private DefaultTableModel modeloTablaTareas = null;

	private JSplitPane splitPaneContenido = null;

	private PanelListaSubTareasModel panelListSubTareas = null;

	private static Tarea tareaActivaLocal = null;

	private javax.swing.JMenuItem miActualizar;

	private javax.swing.JPopupMenu popupTarea;

	public PanelListaTareasModel() {
		iniciarComponente();
		crearPopUp();
		this.setModeloTablaTareas((DefaultTableModel) getTblTareas()
						.getModel());
		modificarTabla();
		iniciarListeners();
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
		getTblTareas().getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
				"doNothing");
		getTblTareas().getActionMap().put("doNothing", doNothing);
	}

	private Action doNothing = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			// do nothing
		}
	};

	private void crearPopUp() {
		setPopupTarea(new javax.swing.JPopupMenu());
		miActualizar = new javax.swing.JMenuItem();

		getPopupTarea().setBorder(
				javax.swing.BorderFactory.createTitledBorder(null, "Tareas",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(
								0, 0, 0)));
		miActualizar.setText("Actualizar");
		getPopupTarea().add(miActualizar);
		miActualizar.setIcon(new ImageIcon("images/timetracker/refresh.png"));

		getTblTareas().setComponentPopupMenu(getPopupTarea());
		getJScrollPane1().setComponentPopupMenu(getPopupTarea());
	}

	public void modificarTabla() {
		// MODO DE SELECCION DE FILAS
		getTblTareas().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// getTblTareas().getColumnModel().getColumn(COLUMNA_TIEMPO_TAREA).setMinWidth(55);
		TiempoCellRenderer tiempoCellRenderer = new TiempoCellRenderer();
		getTblTareas().getColumnModel().getColumn(getCOLUMNA_TIEMPO_TAREA())
				.setCellRenderer(tiempoCellRenderer);
		getTblTareas().getColumnModel().getColumn(getCOLUMNA_TIEMPO_TAREA())
				.setMaxWidth(55);
	}

	private void iniciarListeners() {

		// TABLA
		getTblTareas().addMouseListener(getMlTablaTareas());
		getTblTareas().addKeyListener(getKlTablaTarea());
		// ((AbstractTableModel)getTblTareas().getModel()).addTableModelListener(getTmlTablaTareas());

		// POPUP MENU
		popupTarea.addPopupMenuListener(pmlPopUpTarea);
		miActualizar.addActionListener(getAlPopupMenuTarea());
	}

	private PopupMenuListener pmlPopUpTarea = new PopupMenuListener() {
		public void popupMenuCanceled(PopupMenuEvent e) {
		}

		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		}

		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			if ( getSubTareaHiloContadorGlobal() == null )
	    		miActualizar.setEnabled(true);
	    	else
	    		miActualizar.setEnabled(false);
		}
	};

	private ActionListener alPopupMenuTarea = new ActionListener() {
		public void actionPerformed(ActionEvent acev) {
			if (acev.getSource() instanceof JMenuItem) {
				String comando = acev.getActionCommand();
				if ("actualizar".equalsIgnoreCase(comando)) {
					actualizarDetallesOrdenTrabajo();
				}
			}
		}
	};

	private MouseListener mlTablaTareas = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				/*int filaSeleccionada = getTblTareas().getSelectedRow();
				if (filaSeleccionada >= 0) {
					mostrarInformacionTareaOrdenTrabajo();
				}*/
			}
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {
			int filaSeleccionada = getTblTareas().getSelectedRow();
			if (filaSeleccionada >= 0) {
				if (e.getClickCount()==1){
					Utiles.mostrarInformacionTareaGlobal();
					llenarTablaSubTareas();
				}
			} else {
				Utiles.borrarInformacionPanelGlobal();
        		removerTodasFilasTabla(Utiles.getTablaSubTareasGlobal().getModel());
        		removerTodasFilasTabla(Utiles.getTablaSubTareasDetalleGlobal().getModel());
			}
		}
	};

	private KeyListener klTablaTarea = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent kev) {
			int filaSeleccionada = getTblTareas().getSelectedRow();
			if (filaSeleccionada >= 0) {
				if (kev.getKeyCode() == KeyEvent.VK_UP || 
						kev.getKeyCode() == KeyEvent.VK_DOWN){
					Utiles.mostrarInformacionTareaGlobal();
				} else {
					Utiles.borrarInformacionPanelGlobal();
	        		removerTodasFilasTabla(Utiles.getTablaSubTareasGlobal().getModel());
	        		removerTodasFilasTabla(Utiles.getTablaSubTareasDetalleGlobal().getModel());
				}
			}
		}
		public void keyTyped(KeyEvent e) {}
	};

	/*
	 * private TableModelListener tmlTablaTareas = new TableModelListener() {
	 * public void tableChanged(TableModelEvent e) { if (
	 * getSubTareaHiloContadorGlobal()==null ){ //SE HACE SI ES QUE NO HAY
	 * CONTEO EN UNA SUBTAREA if ( getNumeroTareasGlobales() ==
	 * getTblTareas().getRowCount() ){ if ( getTblTareas().getSelectedRow() >=
	 * 0){ int row = e.getFirstRow(); TableModel model =
	 * (TableModel)e.getSource(); String columnName =
	 * model.getColumnName(COLUMNA_CODIGO_ORDEN_TAREA); String nombreNuevo =
	 * (String) model.getValueAt(row, COLUMNA_CODIGO_ORDEN_TAREA);
	 * guardarNombreTarea(row,nombreNuevo); //System.out.println("Nombre actual:
	 * "+getTareaActivaLocal().getNombreTarea()); //System.out.println("Nombre
	 * nuevo: "+nombreNuevo); //guardarNombreTarea(row,nombreNuevo);
	 * //System.out.println("- -->"+getTblTareas().getRowCount()); } } } } };
	 */

	private void mostrarInformacionTareaOrdenTrabajo() {
		try {
			Tarea tarea = Utiles.getTareaActivaGlobal();
			InfoTareaOrdenTrabajoModel infoTareaOrdenTrabajo = new InfoTareaOrdenTrabajoModel(
					tarea.getTareaOrdenTrabajo());
			new InformacionModel(infoTareaOrdenTrabajo,
					"Tarea de Orden de Trabajo", 550, 268);
		} catch (Exception ex) {
			if (ex instanceof GenericBusinessException)
				SpiritAlert.createAlert(ex.getMessage(),
						SpiritAlert.INFORMATION);
			else {
				SpiritAlert.createAlert(
						"Ocurrio un error al obtener información de la tarea",
						SpiritAlert.ERROR);
				ex.printStackTrace();
			}

		}
	}

	private void actualizarDetallesOrdenTrabajo() {
		removerTodasFilasTabla(Utiles.getTablaSubTareasGlobal().getModel());
		try {
			Proyecto proyecto = Utiles.getProyectoActivoGlobal();
			if (proyecto.getTareas() != null) {
				removerTodasFilasTabla(getTblTareas().getModel());
				removerTodasFilasTabla(getTablaTareasGlobal().getModel());
        		removerTodasFilasTabla(Utiles.getTablaSubTareasDetalleGlobal().getModel());
        		Utiles.borrarInformacionPanelGlobal();

				OrdenTrabajoIf ordenTrabajoIf = proyecto.getOrdenTrabajo();
				if (ordenTrabajoIf != null)
					try {
						Collection listaOrdenDetalle = PanelListaProyectosModel
						.getOrdenTrabajoDetalleSessionService()
						.findOrdenTrabajoDetalleByOrdenId(
								ordenTrabajoIf.getId());
						Iterator it = listaOrdenDetalle.iterator();
						
						//LISTA VIEJA
						HashMap listaTareasAntigua = proyecto.getTareas();
						
						//LISTA NUEVA
						HashMap<Long, Object> listaTareasNueva = new LinkedHashMap<Long, Object>();
						
						OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = null;
						DefaultTableModel modeloTareas = (DefaultTableModel) getTablaTareasGlobal()
							.getModel();
						while (it.hasNext()) {
							ordenTrabajoDetalleIf = (OrdenTrabajoDetalleIf) it.next();
							long idTarea = ordenTrabajoDetalleIf.getId().longValue();
							//CREO UNA NUEVA TAREA
							Tarea tareaNueva = new Tarea(idTarea, ordenTrabajoDetalleIf);
							// SI LA TAREA EXISTE PONGO EL TIEMPO HECHO A LA TAREA QUE LE 
							//CORRESPONDE EN LA LISTA NUEVA
							if ( listaTareasAntigua.containsKey(idTarea) ){
								Tarea tareaVieja = (Tarea)listaTareasAntigua.get(idTarea);
								tareaNueva.setSegundosTotales(tareaVieja.getSegundosTotales());
								tareaNueva.setSubTareas(tareaVieja.getSubTareas());
							}
							listaTareasNueva.put(tareaNueva.getIdTarea(), tareaNueva);
							modeloTareas.addRow(tareaNueva.getFilaDeTabla());
						}
						listaTareasAntigua = null;
						proyecto.setTareas(listaTareasNueva);
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert(e.getMessage(),
								SpiritAlert.ERROR);
					}

					if (getTablaTareasGlobal().getRowCount() > 0) {
						getTablaTareasGlobal().setRowSelectionInterval(0, 0);
						/*
						 * itTarea = proyecto.getTareas().keySet().iterator(); if (
						 * itTarea.hasNext() ){ Tarea tarea = (Tarea)
						 * proyecto.getTareas().get(itTarea.next());
						 * //habilitarImpresionTablaTareas(proyecto);
						 * //llenarTablaSubTareas(tarea); }
						 */
					}
			}
		} catch (Exception ex) {
			if (ex instanceof GenericBusinessException)
				SpiritAlert.createAlert(ex.getMessage(), SpiritAlert.INFORMATION);
			else
				SpiritAlert.createAlert("Ocurrio un error al actualizar las Tareas de las Ordenes de Tareas"
						, SpiritAlert.ERROR);
		}
	}

	/*
	 * private void guardarNombreTarea(int filaSeleccionada,String nombreNuevo){
	 * Tarea tarea = getTareaActivaLocal(); if (
	 * !nombreNuevo.equals(tarea.getNombreTarea()) ){ HashMap mapaTareas =
	 * Utiles.getProyectoActivoGlobal().getTareas(); if (
	 * !existeTareaByName(nombreNuevo) ){
	 * //mapaTareas.remove(tarea.getNombreTarea());
	 * //tarea.setNombreTarea(nombreNuevo); //mapaTareas.put(nombreNuevo,tarea);
	 * tarea.setNombreTarea(nombreNuevo); } else{
	 * getTblTareas().getModel().setValueAt(
	 * tarea.getNombreTarea(),filaSeleccionada, getCOLUMNA_NOMBRE_TAREA());
	 * //removerSeleccionesTabla(getTblTareas());
	 * //getTblTareas().setRowSelectionInterval(filaSeleccionada,filaSeleccionada);
	 * JOptionPane.showMessageDialog(null, "No puede haber dos Tareas con el
	 * mismo Nombre en un Proyecto", "Advertencia",
	 * JOptionPane.WARNING_MESSAGE); } } }
	 */

	/*
	 * private void eliminarTarea(){ Tarea tareaSeleccionada = getTareaActiva();
	 * if ( tareaSeleccionada != null ){ if (getSubTareaHiloContadorGlobal() ==
	 * null){ int filaTareaSeleccionado = getTblTareas().getSelectedRow();
	 * Utiles.getProyectoActivoGlobal().getTareas().remove(tareaSeleccionada.getIdTarea());
	 * getModeloTablaTareas().removeRow(filaTareaSeleccionado);
	 * removerTodasFilasTabla(getTablaSubTareasGlobal().getModel());
	 * setNumeroTareasGlobales(getNumeroTareasGlobales()-1); } else{ Tarea
	 * tareaCorriendo = getSubTareaHiloContadorGlobal().getTarea(); if (
	 * tareaSeleccionada != tareaCorriendo ){ System.out.println("se elminina
	 * tarea, una en proceso"); } else{ JOptionPane.showMessageDialog(null, "No
	 * puede eliminar una Tarea en Proceso", "Advertencia",
	 * JOptionPane.WARNING_MESSAGE); } } } else{
	 * JOptionPane.showMessageDialog(null, "Debe escoger una tarea para
	 * eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE); } }
	 */

	private void llenarTablaSubTareas(){
		try {
			Tarea tarea = Utiles.getTareaActivaGlobal(); 
			habilitarImpresionTablaSubTareas(tarea);
			if ( getSubTareaHiloContadorGlobal() != null ){ 
				Tarea tareaHiloContador =
					getSubTareaHiloContadorGlobal().getTarea(); 
				if ( tarea != tareaHiloContador )
					getSubTareaHiloContadorGlobal().setImprimirEnTablaSubTarea(false); 
				else
					getSubTareaHiloContadorGlobal().setImprimirEnTablaSubTarea(true); 
			}
	
			removerTodasFilasTabla(Utiles.getTablaSubTareasGlobal().getModel());
			removerTodasFilasTabla(Utiles.getTablaSubTareasDetalleGlobal().getModel());
			
			DefaultTableModel modeloSubTareas = (DefaultTableModel)
				Utiles.getTablaSubTareasGlobal().getModel(); 
			for (int i=0;i<tarea.getSubTareas().size();i++){
				SubTarea subTarea = (SubTarea)
					tarea.getSubTareas().get(i);
				modeloSubTareas.addRow(subTarea.getFilaDeTabla()); 
			} 
		} catch (GenericBusinessException ex) {
			ex.printStackTrace();
			if ( ex instanceof GenericBusinessException)
				SpiritAlert.createAlert(ex.getMessage(), SpiritAlert.WARNING);
			else
				SpiritAlert.createAlert("Error al obenter las subtareas"
						, SpiritAlert.WARNING);
		}
	}
	
	/*private void llenarTablaSubTareas(){
		Tarea tarea = getTareaActiva(); 
		if ( tarea != null ){
			mostrarInformacionTarea(tarea); if ( getSubTareaHiloContadorGlobal() !=
				null ){ Tarea tareaHilContador =
					getSubTareaHiloContadorGlobal().getTarea(); 
				if ( tarea != tareaHilContador )
					getSubTareaHiloContadorGlobal().setImprimirEnTablaSubTarea(false); 
				else
					getSubTareaHiloContadorGlobal().setImprimirEnTablaSubTarea(true); 
			}

			removerTodasFilasTabla(getTablaSubTareasGlobal().getModel());
			DefaultTableModel modeloSubTareas = (DefaultTableModel)
			getTablaSubTareasGlobal().getModel(); Iterator itSubTarea =
				tarea.getSubTareas().keySet().iterator(); 
			while( itSubTarea.hasNext() ){
				SubTarea subTarea = (SubTarea)
				tarea.getSubTareas().get(itSubTarea.next());
				modeloSubTareas.addRow(subTarea.getFilaDeTabla()); 
			} 
		} 
		else{
			JOptionPane.showMessageDialog(null, "No existe tarea", "Advertencia",
					JOptionPane.WARNING_MESSAGE); 
		} 
	}*/


	/*
	 * private static boolean existeTareaByName(String nombreTarea){ Proyecto
	 * proyecto = Utiles.getProyectoActivoGlobal(); Iterator it =
	 * proyecto.getTareas().keySet().iterator(); while( it.hasNext() ){ String
	 * nombreExistente=( (Tarea) proyecto.getTareas().get(it.next())
	 * ).getNombreTarea(); if ( nombreTarea.equalsIgnoreCase(nombreExistente) )
	 * return true; } return false; }
	 */

	/*
	 * private static long getNuevoIdTarea(){ long maximo=0; Proyecto proyecto =
	 * Utiles.getProyectoActivoGlobal(); if ( proyecto != null ){ Iterator it =
	 * proyecto.getTareas().keySet().iterator(); while ( it.hasNext() ){ Tarea
	 * tarea = (Tarea) proyecto.getTareas().get(it.next()); if (
	 * tarea.getIdTarea()>maximo ) maximo = tarea.getIdTarea(); } } return
	 * maximo+1; }
	 */

	/*
	 * private static String getNuevoNombreTarea(String stringInicial){ int
	 * contador=0; String nombre = stringInicial+contador; while (
	 * existeTareaByName(nombre) ){ contador++; nombre = stringInicial+contador; }
	 * return nombre; }
	 */

	/*
	 * static void addTareaProyecto(){ Proyecto proyecto =
	 * Utiles.getProyectoActivoGlobal(); if ( proyecto != null ){ String
	 * nombreNuevaTarea = "Tarea"+0; if ( existeTareaByName(nombreNuevaTarea) )
	 * nombreNuevaTarea = getNuevoNombreTarea("Tarea"); //Tarea tarea = new
	 * Tarea(getNuevoIdTarea(),nombreNuevaTarea); Tarea tarea = null;
	 * proyecto.getTareas().put(Long.valueOf(tarea.getIdTarea()),tarea);
	 * DefaultTableModel modelo = (DefaultTableModel) getTblTareas().getModel();
	 * //System.out.println("- antes: "+getTblTareas().getRowCount());
	 * modelo.addRow(tarea.getFilaDeTabla()); //System.out.println("- despues:
	 * "+getTblTareas().getRowCount());
	 * setNumeroTareasGlobales(getNumeroTareasGlobales()+1);
	 * mostrarInformacionTarea(tarea); setTareaActivaLocal(tarea); int numFilas =
	 * getTblTareas().getRowCount()-1;
	 * getTblTareas().setRowSelectionInterval(numFilas,numFilas);
	 * //limpiarPanelInformacion();
	 * removerTodasFilasTabla(getTablaSubTareasGlobal().getModel());
	 * //habilitarImpresionTablaTareas(proyecto); } }
	 */

	private void habilitarImpresionTablaTareas(Proyecto proyecto) {
		if (getSubTareaHiloContadorGlobal() != null) {
			Proyecto proyectoHiloContador = getSubTareaHiloContadorGlobal()
					.getProyecto();
			if (proyecto != proyectoHiloContador) {
				getSubTareaHiloContadorGlobal().setImprimirEnTablaTareas(false);
			} else {
				getSubTareaHiloContadorGlobal().setImprimirEnTablaTareas(true);
			}
		}
	}
	
	private void habilitarImpresionTablaSubTareas(Tarea tarea){
        if ( getSubTareaHiloContadorGlobal() != null ){
            Tarea tareaHiloContador = getSubTareaHiloContadorGlobal().getTarea();
            if ( tarea != tareaHiloContador ){
                getSubTareaHiloContadorGlobal().setImprimirEnTablaSubTarea(false);
            } else{
                getSubTareaHiloContadorGlobal().setImprimirEnTablaSubTarea(true);
            }
        }
    }

	public ActionListener getAlPopupMenuTarea() {
		return alPopupMenuTarea;
	}

	public void setAlPopupMenuTarea(ActionListener alPopupMenuTarea) {
		this.alPopupMenuTarea = alPopupMenuTarea;
	}

	public DefaultTableModel getModeloTablaTareas() {
		return modeloTablaTareas;
	}

	public void setModeloTablaTareas(DefaultTableModel modeloTablaTareas) {
		this.modeloTablaTareas = modeloTablaTareas;
	}

	public MouseListener getMlTablaTareas() {
		return mlTablaTareas;
	}

	public void setMlTablaTareas(MouseListener mlTablaTareas) {
		this.mlTablaTareas = mlTablaTareas;
	}

	public JSplitPane getSplitPaneContenido() {
		return splitPaneContenido;
	}

	public void setSplitPaneContenido(JSplitPane splitPaneContenido) {
		this.splitPaneContenido = splitPaneContenido;
	}

	public PopupMenuListener getPmlPopUpTarea() {
		return pmlPopUpTarea;
	}

	public void setPmlPopUpTarea(PopupMenuListener pmlPopUpTarea) {
		this.pmlPopUpTarea = pmlPopUpTarea;
	}

	public PanelListaSubTareasModel getPanelListSubTareas() {
		return panelListSubTareas;
	}

	public void setPanelListSubTareas(
			PanelListaSubTareasModel panelListSubTareas) {
		this.panelListSubTareas = panelListSubTareas;
	}

	public static int getCOLUMNA_NOMBRE_TAREA() {
		return COLUMNA_NOMBRE_TAREA;
	}

	public static int getCOLUMNA_TIEMPO_TAREA() {
		return COLUMNA_TIEMPO_TAREA;
	}

	public KeyListener getKlTablaTarea() {
		return klTablaTarea;
	}

	public void setKlTablaTarea(KeyListener klTablaTarea) {
		this.klTablaTarea = klTablaTarea;
	}

	public Tarea getTareaActivaLocal() {
		return tareaActivaLocal;
	}

	public static void setTareaActivaLocal(Tarea tareaActivaLocalp) {
		tareaActivaLocal = tareaActivaLocalp;
	}

	public javax.swing.JPopupMenu getPopupTarea() {
		return popupTarea;
	}

	public void setPopupTarea(javax.swing.JPopupMenu popupTarea) {
		this.popupTarea = popupTarea;
	}

}
