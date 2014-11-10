package timeTracker.tiempo;

import static timeTracker.tiempo.Utiles.getFechaActual;
import static timeTracker.tiempo.Utiles.getHoraActual;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.timeTracker.gui.model.cache.MapaCache;

public class Tarea implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private OrdenTrabajoDetalleIf tareaOrdenTrabajo;
	
    private long idTarea = 0;
    private String nombreTarea;
    private String descripcion;
    private long SegundosTotales;
    private Fecha fechaInicio;
    private Hora horaInicio;
    private String tipo;
    
    private Collection<SubTarea> subTareas = new ArrayList<SubTarea>();
    
    public Tarea(long idTarea,OrdenTrabajoDetalleIf tareaOrdenTrabajo) {
    	this.tareaOrdenTrabajo = tareaOrdenTrabajo;
        this.setIdTarea(idTarea);
        this.setTipo("Tarea");
        this.setNombreTarea(nombreTarea);
        this.setDescripcion("");
        this.setFechaInicio(new Fecha(getFechaActual()));
        this.setHoraInicio(new Hora(getHoraActual()));
    }
    
    public Tarea(int idTarea,OrdenTrabajoDetalleIf tareaOrdenTrabajo,String descripcion) {
        this(idTarea,tareaOrdenTrabajo);
        this.setDescripcion(descripcion);
    }
    
    public Object[] getFilaDeTabla(){
        try {
        	Object[] objetos = {
        		MapaCache.verificarEmpleadoEnMapa(MapaCache.getMapaEmpleados(), tareaOrdenTrabajo.getAsignadoaId())
        		,getSegundosTotales() };
        	return objetos;
        } catch (GenericBusinessException e){
		e.printStackTrace();}
        return new Object[] {"",getSegundosTotales()};
    }
    
    /*public void convertirSegundosTotales(){
        convertirSegundos(getSegundosTotales());
    }*/
    
    public void addSubTarea(SubTarea subTarea){
        if ( subTarea != null )
            this.getSubTareas().add(subTarea);
    }
    
    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<SubTarea> getSubTareas() {
        return subTareas;
    }

    public void setSubTareas(Collection<SubTarea> subTareas) {
        this.subTareas = subTareas;
    }
    
    public Fecha getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Fecha fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Hora getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Hora horaInicio) {
        this.horaInicio = horaInicio;
    }

    public long getSegundosTotales() {
        return SegundosTotales;
    }

    public void setSegundosTotales(long SegundosTotales) {
        this.SegundosTotales = SegundosTotales;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(long idTarea) {
        this.idTarea = idTarea;
    }

	public OrdenTrabajoDetalleIf getTareaOrdenTrabajo() {
		return tareaOrdenTrabajo;
	}

	public void setTareaOrdenTrabajo(OrdenTrabajoDetalleIf tareaOrdenTrabajo) {
		this.tareaOrdenTrabajo = tareaOrdenTrabajo;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
