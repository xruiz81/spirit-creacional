package timeTracker.tiempo;

import java.util.ArrayList;
import java.util.Collection;

import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.TiempoParcialDotDetalleIf;
import com.spirit.medios.entity.TiempoParcialDotIf;


public class AutoSave extends Thread {
	
	private SubTarea subTarea = null;
    private Proyecto proyecto = null;
    
	public AutoSave(Proyecto proyecto, SubTarea subTarea) {
		this.setSubTarea(subTarea);
        this.setProyecto(proyecto);
	}

	public void run() {
		try {
			TiempoParcialDotIf tiempoParcial = getSubTarea().getTiempoParcial();
			Collection<TiempoParcialDotDetalleIf> tiempopParcialDetalleVector = new ArrayList<TiempoParcialDotDetalleIf>();
			Collection<SubTareaDetalle> detalleVector = getSubTarea().getDetalle();
			for ( SubTareaDetalle subTareaDetalle : detalleVector ){
				tiempopParcialDetalleVector.add(subTareaDetalle.getTiempoParcialDetalle());
			}
			
			//Guardo cada Tarea
			TiempoParcialDotIf tiempoParcialGuardado = SessionServiceLocator.getTiempoParcialDotSessionService()
				.procesarTiempoParcialDot(tiempoParcial, tiempopParcialDetalleVector , getProyecto().getOrdenTrabajo().getCodigo(), false);
			
			System.out.println("guardado automático: " + tiempoParcialGuardado.getDescripcion() + " : " + tiempoParcialGuardado.getTiempo());    				
			
		}catch (GenericBusinessException gbe) {
			gbe.printStackTrace();
		}
	}
	
	public SubTarea getSubTarea() {
		return subTarea;
	}

	public void setSubTarea(SubTarea subTarea) {
		this.subTarea = subTarea;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
}