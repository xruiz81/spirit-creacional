package timeTracker.tiempo;

import static com.spirit.timeTracker.gui.model.PanelListaProyectosModel.COLUMNA_TIEMPO_ORDEN_TAREA;
import static com.spirit.timeTracker.gui.model.PanelListaSubTareasModel.COLUMNA_TIEMPO_SUBTAREA;
import static com.spirit.timeTracker.gui.model.PanelListaTareasModel.COLUMNA_TIEMPO_TAREA;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.spirit.timeTracker.gui.model.PanelSubtareaDetalleModel;

public class SubTareaHilo extends Thread {
    
	private SubTareaDetalle subTareadetalle = null;
    private SubTarea subTarea = null;
    private Proyecto proyecto = null;
    private long contador;
    private boolean contar = true;
    
    private int filaTablaSubTareaDetalle = 0;
    private JTable tablaSubTareaDetalle = null;
    private DefaultTableModel modeloTablaSubTareaDetalle = null;
    private boolean imprimirEnTablaSubTareaDetalle = true;
    
    private long filaTablaSubTarea = 0;
    private JTable tablaSubTareas = null;
    private DefaultTableModel modeloTablaSubTareas = null;
    private boolean imprimirEnTablaSubTarea = true;
    
    private int filaTablaTarea = 0;
    private JTable tablaTareas = null;
    private DefaultTableModel modeloTablaTareas = null;
    private boolean imprimirEnTablaTareas = true;
    
    private int filaTablaProyectos = 0;
    private JTable tablaProyectos = null;
    private DefaultTableModel modeloTablaProyectos = null;
    private boolean imprimirEnTablaProyectos = true;
    
    private Tarea tarea = null;
   
    public SubTareaHilo(Proyecto proyecto,Tarea tarea,SubTarea subTarea) {
        this.setContador(subTarea.getSegundos());
        this.setSubTarea(subTarea);
        this.setTarea(tarea);
        this.setProyecto(proyecto);
    }
    public SubTareaHilo(Proyecto proyecto,Tarea tarea,SubTarea subTarea,JTable tablaSubTareas) {
        this.setContador(subTarea.getSegundos());
        this.setSubTarea(subTarea);
        this.setTarea(tarea);
        this.setProyecto(proyecto);
        if (getTablaSubTareas()!=null ){
            setModeloTablaSubTareas((DefaultTableModel) tablaSubTareas.getModel());
        }
    }
    
    
    public void run(){
    	
    	try {
    		//Se establece cual es el detalle de la subtarea en el
        	//que se tiene que contar
        	subTareadetalle = subTarea.getDetalle().get(subTarea.getDetalle().size()-1);
    		while (isContar()){

    			Thread.sleep(1000);
    			if ( isContar() ){
    				subTareadetalle.setSegundos(subTareadetalle.getSegundos()+1);
    				getSubTarea().setSegundos(getSubTarea().getSegundos()+1);
    				getTarea().setSegundosTotales( getTarea().getSegundosTotales()+1 );
    				getProyecto().setSegundosAumentados( getProyecto().getSegundosAumentados()+1 );
    				filaTablaSubTareaDetalle = modeloTablaSubTareaDetalle.getRowCount()-1;

    				if (imprimirEnTablaSubTareaDetalle && modeloTablaSubTareaDetalle!=null &&
    						( modeloTablaSubTareaDetalle.getRowCount()>0 ) &&
    						( filaTablaSubTareaDetalle>=0 && filaTablaSubTareaDetalle<modeloTablaSubTareaDetalle.getRowCount() ) ){
    					modeloTablaSubTareaDetalle.setValueAt(
    							subTareadetalle.getSegundos(), filaTablaSubTareaDetalle, PanelSubtareaDetalleModel.COLUMNA_TIEMPO);
    				}
    				if ( isImprimirEnTablaSubTarea() && getModeloTablaSubTareas()!=null && 
    						(getModeloTablaSubTareas().getRowCount() > 0)  &&
    						( getFilaTablaSubTarea() >= 0  && getFilaTablaSubTarea() < getModeloTablaSubTareas().getRowCount() )  ){
    					getModeloTablaSubTareas().setValueAt(
    							getSubTarea().getSegundos(),getFilaTablaSubTarea().intValue(), COLUMNA_TIEMPO_SUBTAREA);
    				}
    				if ( isImprimirEnTablaTareas() &&  getModeloTablaTareas()!=null && 
    						(getModeloTablaTareas().getRowCount() > 0)  &&
    						( getFilaTablaTarea() >= 0  && getFilaTablaTarea() < getModeloTablaTareas().getRowCount() )  ){
    					getModeloTablaTareas().setValueAt(
    							getTarea().getSegundosTotales(),getFilaTablaTarea(), COLUMNA_TIEMPO_TAREA);
    				}
    				if ( isImprimirEnTablaProyectos() && getModeloTablaProyectos()!=null && 
    						(getModeloTablaProyectos().getRowCount() > 0)  &&
    						( getFilaTablaProyectos() >= 0  && getFilaTablaProyectos() < getModeloTablaProyectos().getRowCount() )  ){
    					getModeloTablaProyectos().setValueAt(
    							getProyecto().getSegundosTotales(),getFilaTablaProyectos(), COLUMNA_TIEMPO_ORDEN_TAREA);
    				}
    			}
    			//System.out.println("SubTarea: "+getSubTarea().getIdSubTarea()+" seg: "+getSubTarea().getSegundos());

    		}
    	} catch (InterruptedException ex) {
    		ex.printStackTrace();
    	}
    }
    
    public void parar(){
        this.setContar(false);
    }
    
    public SubTarea getSubTarea() {
        return subTarea;
    }
    
    public void setSubTarea(SubTarea subTarea) {
        this.subTarea = subTarea;
    }
    
    public long getContador() {
        return contador;
    }
    
    public void setContador(long contador) {
        this.contador = contador;
    }
    
    public boolean isContar() {
        return contar;
    }
    
    public void setContar(boolean contar) {
        this.contar = contar;
    }

    public Long getFilaTablaSubTarea() {
        return filaTablaSubTarea;
    }

    public void setFilaTablaSubTarea(long filaTablaSubTarea) {
        this.filaTablaSubTarea = filaTablaSubTarea;
    }

    public JTable getTablaSubTareas() {
        return tablaSubTareas;
    }

    public void setTablaSubTareas(JTable tablaSubTareas) {
        this.tablaSubTareas = tablaSubTareas;
        if (getTablaSubTareas()!=null ){
            setModeloTablaSubTareas((DefaultTableModel) tablaSubTareas.getModel());
        }
    }

    public DefaultTableModel getModeloTablaSubTareas() {
        return modeloTablaSubTareas;
    }

    public void setModeloTablaSubTareas(DefaultTableModel modeloTablaSubTareas) {
        this.modeloTablaSubTareas = modeloTablaSubTareas;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public boolean isImprimirEnTablaSubTarea() {
        return imprimirEnTablaSubTarea;
    }

    public void setImprimirEnTablaSubTarea(boolean imprimirEnTablaSubTarea) {
        this.imprimirEnTablaSubTarea = imprimirEnTablaSubTarea;
    }

    public int getFilaTablaTarea() {
        return filaTablaTarea;
    }

    public void setFilaTablaTarea(int filaTablaTarea) {
        this.filaTablaTarea = filaTablaTarea;
    }

    public JTable getTablaTareas() {
        return tablaTareas;
    }

    public void setTablaTareas(JTable tablaTareas) {
        this.tablaTareas = tablaTareas;
        if ( tablaTareas != null ){
            setModeloTablaTareas((DefaultTableModel)tablaTareas.getModel());
            setFilaTablaTarea(getTablaTareas().getSelectedRow());
        }
    }

    public DefaultTableModel getModeloTablaTareas() {
        return modeloTablaTareas;
    }

    public void setModeloTablaTareas(DefaultTableModel modeloTablaTareas) {
        this.modeloTablaTareas = modeloTablaTareas;
    }

    public int getFilaTablaProyectos() {
        return filaTablaProyectos;
    }

    public void setFilaTablaProyecto(int filaTablaProyectos) {
        this.setFilaTablaProyectos(filaTablaProyectos);
    }

    public JTable getTablaProyectos() {
        return tablaProyectos;
    }

    public void setTablaProyectos(JTable tablaProyectos) {
        this.tablaProyectos = tablaProyectos;
        if ( tablaProyectos != null ){
            setModeloTablaProyectos((DefaultTableModel)tablaProyectos.getModel());
            setFilaTablaProyecto(getTablaProyectos().getSelectedRow());
        }
    }

    public DefaultTableModel getModeloTablaProyectos() {
        return modeloTablaProyectos;
    }

    public void setModeloTablaProyectos(DefaultTableModel modeloTablaProyectos) {
        this.modeloTablaProyectos = modeloTablaProyectos;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public void setFilaTablaProyectos(int filaTablaProyectos) {
        this.filaTablaProyectos = filaTablaProyectos;
    }

    public boolean isImprimirEnTablaTareas() {
        return imprimirEnTablaTareas;
    }

    public void setImprimirEnTablaTareas(boolean imprimirEnTablaTareas) {
        this.imprimirEnTablaTareas = imprimirEnTablaTareas;
    }

    public boolean isImprimirEnTablaProyectos() {
        return imprimirEnTablaProyectos;
    }

    public void setImprimirEnTablaProyectos(boolean imprimirEnTablaProyectos) {
        this.imprimirEnTablaProyectos = imprimirEnTablaProyectos;
    }
	public int getFilaTablaSubTareaDetalle() {
		return filaTablaSubTareaDetalle;
	}
	public void setFilaTablaSubTareaDetalle(int filaTablaSubTareaDetalle) {
		this.filaTablaSubTareaDetalle = filaTablaSubTareaDetalle;
	}
	public boolean isImprimirEnTablaSubTareaDetalle() {
		return imprimirEnTablaSubTareaDetalle;
	}
	public void setImprimirEnTablaSubTareaDetalle(
			boolean imprimirEnTablaSubTareaDetalle) {
		this.imprimirEnTablaSubTareaDetalle = imprimirEnTablaSubTareaDetalle;
	}
	public JTable getTablaSubTareaDetalle() {
		return tablaSubTareaDetalle;
	}
	public void setTablaSubTareaDetalle(JTable tablaSubTareaDetalle) {
		this.tablaSubTareaDetalle = tablaSubTareaDetalle;
		if (tablaSubTareaDetalle!=null)
			this.modeloTablaSubTareaDetalle = (DefaultTableModel) tablaSubTareaDetalle.getModel();
	}

}
