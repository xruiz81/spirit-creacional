package timeTracker.tiempo;

import static timeTracker.tiempo.Utiles.getFechaActual;
import static timeTracker.tiempo.Utiles.getHoraActual;
import static timeTracker.tiempo.Utiles.getTiempoCompleto;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.TiempoParcialDotIf;
import com.spirit.timeTracker.gui.model.PanelListaProyectosModel;

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
    
    private Vector<SubTarea> subTareas = new Vector<SubTarea>();
    
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
    
    /*public void ordenarSubTareas(){
        int contador=0;
        List<SubTarea> listaSubTareas = new LinkedList<SubTarea>();
        Iterator it = getSubTareas().keySet().iterator();
        while( it.hasNext() ){
            Integer idSubTarea = (Integer) it.next();
            SubTarea sta = (SubTarea) getSubTareas().get(idSubTarea);
            listaSubTareas.add(sta);
            it.remove();
        }
        for (int i = 0; i < listaSubTareas.size() ; i++  ){
            SubTarea sta = (SubTarea) listaSubTareas.get(i);
            sta.setIdSubTarea(contador);
            getSubTareas().put(sta.getIdSubTarea(),sta);
            contador++;
        }
    }*/
    
    public Object[] getFilaDeTabla(){
        try {
        	Object[] objetos = {
        		PanelListaProyectosModel.getEmpleadoSessionService()
        		.getEmpleado(tareaOrdenTrabajo.getAsignadoaId())
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

    public Vector<SubTarea> getSubTareas() {
        return subTareas;
    }

    public void setSubTareas(Vector<SubTarea> subTareas) {
        this.subTareas = subTareas;
    }
    
    
    /*
    public static void main(String[] args){
        Tarea tarea = new Tarea("tarea");
        SubTarea st = new SubTarea(tarea.getSubTareas().size(),"desc s0");
        tarea.addSubTarea(st);
        st = new SubTarea(tarea.getSubTareas().size(),"desc1 s1");
        tarea.addSubTarea(st);
        st = new SubTarea(tarea.getSubTareas().size(),"desc2 s2");
        tarea.addSubTarea(st);
        st = new SubTarea(tarea.getSubTareas().size(),"desc3 s3");
        tarea.addSubTarea(st);
        
        Iterator it = tarea.getSubTareas().keySet().iterator();
        while( it.hasNext() ){
            SubTarea s = (SubTarea) tarea.getSubTareas().get(it.next());
            System.out.println("des id: "+s.getIdSubTarea()+" | des: "+s.getDescripcion() );
        }
        
        tarea.getSubTareas().remove(3);
        tarea.ordenarSubTareas();
        it = tarea.getSubTareas().keySet().iterator();
        System.out.println("------------------------------------------------------------- ");
        while( it.hasNext() ){
            SubTarea s = (SubTarea) tarea.getSubTareas().get(it.next());
            System.out.println("des id: "+s.getIdSubTarea()+" | des: "+s.getDescripcion() );
        }
        
        st = new SubTarea(tarea.getSubTareas().size(),"desc4 s4");
        tarea.addSubTarea(st);
        it = tarea.getSubTareas().keySet().iterator();
        System.out.println("------------------------------------------------------------- ");
        while( it.hasNext() ){
            SubTarea s = (SubTarea) tarea.getSubTareas().get(it.next());
            System.out.println("des id: "+s.getIdSubTarea()+" | des: "+s.getDescripcion() );
        }
    }
    */

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
