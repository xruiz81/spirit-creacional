package com.spirit.timeTracker.gui.model;

import static com.spirit.timeTracker.gui.model.Utiles.getSubTareaHiloContadorGlobal;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import timeTracker.tiempo.SubTarea;
import timeTracker.tiempo.Tarea;

import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.exception.GenericBusinessException;
import com.spirit.timeTracker.componentes.TiempoCellRenderer;
import com.spirit.timeTracker.gui.main.JPSubtareas;


public class PanelListaSubTareasModel extends JPSubtareas {
    
	private static final long serialVersionUID = 1L;
	//public static final int COLUMNA_ID_SUBTAREA = 0;
    public static final int COLUMNA_DESCRIPCION_SUBTAREA = 0;
    public static final int COLUMNA_TIEMPO_SUBTAREA = 1;
    
    //private DefaultTableModel modeloTablaSubTareas = null;
    
    private javax.swing.JMenuItem miNueva;
    private javax.swing.JMenuItem miEliminar;
    private javax.swing.JMenuItem miIniciar;
    private javax.swing.JMenuItem miParar;
    private javax.swing.JPopupMenu popupSubTarea;
    
    /** Creates a new instance of PanelListaSubTareasModel */
    public PanelListaSubTareasModel() {
    	iniciarComponentes();
        crearPopPup();
        //this.modeloTablaSubTareas = (DefaultTableModel) getTblSubTareas().getModel();
        getSplitPaneContenedor().setDividerLocation(450);
        modificarTabla();
        iniciarListeners();
                
    }
    
    private void iniciarComponentes(){
    	getTblSubTareas().getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
		"doNothing");
    	getTblSubTareas().getActionMap().put("doNothing", doNothing);
    }
    
    private Action doNothing = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			// do nothing
		}
	};
    
    private void crearPopPup(){
    	javax.swing.JSeparator jSeparator2;
    	
    	popupSubTarea = new javax.swing.JPopupMenu();
    	miNueva = new javax.swing.JMenuItem();
        miIniciar = new javax.swing.JMenuItem();
        miParar = new javax.swing.JMenuItem();
        miEliminar = new javax.swing.JMenuItem();
        
        miNueva.setIcon(SpiritResourceManager.getImageIcon("images/timetracker/addSubTaskSmall.png"));
        miIniciar.setIcon(SpiritResourceManager.getImageIcon("images/timetracker/startSmall.png"));
        miParar.setIcon(SpiritResourceManager.getImageIcon("images/timetracker/stopSmall.png"));
        miEliminar.setIcon(SpiritResourceManager.getImageIcon("images/timetracker/deleteSubTaskSmall.png"));
        
    	popupSubTarea.setBorder(
    			javax.swing.BorderFactory.createTitledBorder(
    					null, "SubTareas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION
    					, javax.swing.border.TitledBorder.DEFAULT_POSITION
    					, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0)));
    	
    	miNueva.setText("Nueva");
        popupSubTarea.add(miNueva);
        
        jSeparator2 = new javax.swing.JSeparator();
        popupSubTarea.add(jSeparator2);
        
        miIniciar.setText("Iniciar");
        popupSubTarea.add(miIniciar);

        miParar.setText("Parar");
        popupSubTarea.add(miParar);

        jSeparator2 = new javax.swing.JSeparator();
        popupSubTarea.add(jSeparator2);

        miEliminar.setText("Eliminar");
        popupSubTarea.add(miEliminar);
        
        getSpanelSubTareas().setComponentPopupMenu(popupSubTarea);
        getTblSubTareas().setComponentPopupMenu(popupSubTarea);
    }
        
    private void modificarTabla(){
        //MODO DE SELECCION DE FILAS
        getTblSubTareas().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //ID
        //getTblSubTareas().getColumnModel().getColumn(COLUMNA_ID_SUBTAREA).setMinWidth(35);
        //getTblSubTareas().getColumnModel().getColumn(COLUMNA_ID_SUBTAREA).setMaxWidth(35);
        
        //HORA INICIO
        //getTblSubTareas().getColumnModel().getColumn(COLUMNA_HORA_INICIO_SUBTAREA).setMinWidth(53);
        //getTblSubTareas().getColumnModel().getColumn(COLUMNA_HORA_INICIO_SUBTAREA).setMaxWidth(53);
        
        //TIEMPO
        //getTblSubTareas().getColumnModel().getColumn(COLUMNA_TIEMPO).setMinWidth(55);
        TiempoCellRenderer tiempoCellRenderer = new TiempoCellRenderer(true);
        TiempoCellRenderer tiempoCellRendererNombre = new TiempoCellRenderer(false);
        
        getTblSubTareas().getColumnModel().getColumn(COLUMNA_DESCRIPCION_SUBTAREA).setCellRenderer(tiempoCellRendererNombre);
        getTblSubTareas().getColumnModel().getColumn(COLUMNA_TIEMPO_SUBTAREA).setCellRenderer(tiempoCellRenderer);
        getTblSubTareas().getColumnModel().getColumn(COLUMNA_TIEMPO_SUBTAREA).setMaxWidth(70);
    }
    
    private void iniciarListeners(){
        //MENUS
    	/*SubTareaListener menuActionListener = new SubTareaListener();
    	miNueva.addActionListener(menuActionListener);
        miIniciar.addActionListener(menuActionListener);
        miParar.addActionListener(menuActionListener);
        miEliminar.addActionListener(menuActionListener);*/
        
    	miNueva.addActionListener(Utiles.subtatareaListener);
        miIniciar.addActionListener(Utiles.subtatareaListener);
        miParar.addActionListener(Utiles.subtatareaListener);
        miEliminar.addActionListener(Utiles.subtatareaListener);
    	
        //TABLA
        getTblSubTareas().addMouseListener(getMlTablaSubTareas());
        getTblSubTareas().addKeyListener(getKlTablaSubTareas());
        
        //POPUP MENUS
        popupSubTarea.addPopupMenuListener(pmlPopUpSubTarea);
    }
    
    private PopupMenuListener pmlPopUpSubTarea = new PopupMenuListener() {
        public void popupMenuCanceled(PopupMenuEvent e) {
        }
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        	Tarea tarea = null;
        	miNueva.setEnabled(false);
        	miParar.setEnabled(false);
        	miIniciar.setEnabled(false);
			miEliminar.setEnabled(false);
            try {
            	tarea = Utiles.getTareaActivaGlobal();
            	//boolean activarNuevaYEliminarSubtarea = Utiles.verificarUsuario();
            	boolean activarNuevaYEliminarSubtarea = true;
            	if (tarea!=null && activarNuevaYEliminarSubtarea){
        			miNueva.setEnabled(true);
        			miIniciar.setEnabled(true);
            	}
            	
            	actualizarMenuPopUp(activarNuevaYEliminarSubtarea);
            } catch (GenericBusinessException ex) {
            	ex.printStackTrace();
    		}
            JPopupMenu ppMenu = (JPopupMenu) e.getSource();
            ppMenu.setVisible(false);
            this.popupMenuWillBecomeInvisible(null);
            
        }
    };
    
    private void actualizarMenuPopUp(boolean activarNuevaYEliminarSubtarea){
    	SubTarea subTarea = null;
        try {
			subTarea = Utiles.getSubTareaActivaGlobal();
		} catch (GenericBusinessException e) {
        	e.printStackTrace();
		} 
		if(subTarea!=null){
			if ( getSubTareaHiloContadorGlobal() != null ){
				miIniciar.setEnabled(true);
				miNueva.setEnabled(false);
				miParar.setEnabled(true);
			}
			else{
				miIniciar.setEnabled(true);
				if (activarNuevaYEliminarSubtarea)
					miEliminar.setEnabled(true);
			}
		}
    }
    
    private MouseListener mlTablaSubTareas = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
        }
        public void mousePressed(MouseEvent e) {
        }
        public void mouseReleased(MouseEvent e) {
            int filaSeleccionada = getTblSubTareas().getSelectedRow();
            if ( filaSeleccionada >= 0 ){
            	Utiles.mostrarInformacionSubTareaGlobal();
            	llenarTablaSubtareaDetalle();
                //SubTarea subTarea = getSubTareaActiva(filaSeleccionada);
                //mostrarInformacionSubTarea(subTarea);
            }else {
				Utiles.borrarInformacionPanelGlobal();
				Utiles.removerTodasFilasTabla(Utiles.getTablaSubTareasDetalleGlobal().getModel());
            }
        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
    };
    
    private KeyListener klTablaSubTareas = new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
        }
        public void keyReleased(KeyEvent kev) {
        	int filaSeleccionada = getTblSubTareas().getSelectedRow();
        	if ( filaSeleccionada >= 0 ){
	            if ( kev.getKeyChar() == KeyEvent.VK_ENTER ){
	                //guardarDescripcion();
	            } else if (kev.getKeyCode() == KeyEvent.VK_UP || 
	            		kev.getKeyCode() == KeyEvent.VK_DOWN){
	            	Utiles.mostrarInformacionSubTareaGlobal();
	            	llenarTablaSubtareaDetalle();
	            	//SubTarea subTarea = getSubTareaActiva(filaSeleccionada);
	            	//mostrarInformacionSubTarea(subTarea);
	            }
        	} else {
				Utiles.borrarInformacionPanelGlobal();
				Utiles.removerTodasFilasTabla(Utiles.getTablaSubTareasDetalleGlobal().getModel());
            }
        }
        public void keyTyped(KeyEvent e) {
        }
    };
    
    private void llenarTablaSubtareaDetalle() {
    	try {
			SubTarea subTarea = Utiles.getSubTareaActivaGlobal();
			if (subTarea != null){
				habilitarImpresionTablaSubtareaDetalle(subTarea);
		    	Utiles.mostrarSubTareaDetalleGlobal(subTarea);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			if ( e instanceof GenericBusinessException)
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
			else
				SpiritAlert.createAlert("Error al obenter el detalle de los tiempos para la Subtarea"
						, SpiritAlert.WARNING);
		}
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
    
    /*private void guardarDescripcion(){
        Tarea tarea = getTareaActiva();
        if ( tarea != null ){
            int filaSubTareaSeleccionada = getTblSubTareas().getSelectedRow();
            //SubTarea subTarea = getSubTareaActiva(filaSubTareaSeleccionada);
            SubTarea subTarea;
			try {
				subTarea = Utiles.getSubTareaActivaGlobal();
				if ( subTarea != null ){
	                String descripcionSubTarea = (String) getTblSubTareas().getModel()
	                .getValueAt(filaSubTareaSeleccionada,COLUMNA_DESCRIPCION_SUBTAREA);
	                subTarea.setDescripcion(descripcionSubTarea);
	                mostrarInformacionSubTarea(subTarea);
	            }
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
				e.printStackTrace();
			}
            
        }
    }*/
    
    public MouseListener getMlTablaSubTareas() {
        return mlTablaSubTareas;
    }
    
    public void setMlTablaSubTareas(MouseListener mlTablaSubTareas) {
        this.mlTablaSubTareas = mlTablaSubTareas;
    }
    
    public KeyListener getKlTablaSubTareas() {
        return klTablaSubTareas;
    }
    
    public void setKlTablaSubTareas(KeyListener klTablaSubTareas) {
        this.klTablaSubTareas = klTablaSubTareas;
    }
    
}
