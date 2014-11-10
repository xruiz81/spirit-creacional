package timeTracker.tiempo;

import static timeTracker.tiempo.Utiles.getFechaActual;
import static timeTracker.tiempo.Utiles.getHoraActual;
import static timeTracker.tiempo.Utiles.getTiempoCompleto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.spirit.medios.entity.OrdenTrabajoEJB;
import com.spirit.medios.entity.OrdenTrabajoIf;


public class Proyecto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private OrdenTrabajoIf ordenTrabajo;
	
    private String idProyecto;
    private String descripcion;
    private long SegundosTotales = 0L;
    private long segundosAumentados = 0L;
    private long segundosRestados = 0L;
    private String tipo;
    
    private HashMap<Long,Object> tareas;
    
    public Proyecto(String idProyecto,OrdenTrabajoIf ordenTrabajo) {
    	this.ordenTrabajo = ordenTrabajo;
        this.setIdProyecto(idProyecto);
        this.setTipo("Proyecto");
        this.setTareas(new LinkedHashMap<Long,Object>());
    }
    
    public Object[] getFilaDeTabla(){
        //Object[] objetos= {ordenTrabajo.getCodigo(),getTiempoCompleto(getSegundosTotales())};
    	Object[] objetos= {ordenTrabajo.getCodigo(),getSegundosTotales()};
        return objetos;
    }
    
    /*public void addTarea(Tarea tarea){
        if ( tarea!=null )
            getTareas().put(tarea.getNombreTarea(),tarea);
    }*/
    
    /*public void convertirSegundosTotales(){
        convertirSegundos(getSegundosTotales());
    }*/
     
    private void sumarSegundosProyecto(int segundos){
        setSegundosTotales( getSegundosTotales() + segundos );
        //int[] segundosTot = sumarSegundos(
        //        new int[]{getHoras(),getMinutos(),getSegundos()} , convertirSegundos(segundos));
        //convertirSegundosTotales();
    }

    public HashMap<Long,Object> getTareas() {
        return tareas;
    }

    public void setTareas(HashMap<Long,Object> tareas) {
        this.tareas = tareas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getSegundosTotales() {
        return SegundosTotales + segundosAumentados - segundosRestados;
    }

    public void setSegundosTotales(long SegundosTotales) {
        this.SegundosTotales = SegundosTotales;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public OrdenTrabajoIf getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajoEJB ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public long getSegundosAumentados() {
		return segundosAumentados;
	}

	public void setSegundosAumentados(long segundosAumentados) {
		this.segundosAumentados = segundosAumentados;
	}

	public long getSegundosRestados() {
		return segundosRestados;
	}

	public void setSegundosRestados(long segundosRestados) {
		this.segundosRestados = segundosRestados;
	}
    
}
