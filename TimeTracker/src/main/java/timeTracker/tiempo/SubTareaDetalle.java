package timeTracker.tiempo;

import java.io.Serializable;
import java.util.Date;

import com.spirit.medios.entity.TiempoParcialDotDetalleData;
import com.spirit.medios.entity.TiempoParcialDotDetalleIf;

public class SubTareaDetalle implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private TiempoParcialDotDetalleIf tiempoParcialDetalle;

	public SubTareaDetalle(){
		this.tiempoParcialDetalle = new TiempoParcialDotDetalleData();
		Date fecha = new Date();
		//this.tiempoParcialDetalle.setId(0L);
		this.tiempoParcialDetalle.setTiempo(0L);
		this.tiempoParcialDetalle.setFecha(fecha.getTime());
		this.tiempoParcialDetalle.setHoraFin(0L);
		this.tiempoParcialDetalle.setHoraInicio(0L);
		this.tiempoParcialDetalle.setIdTiempoParcialDot(0L);
	}
	
	public SubTareaDetalle(TiempoParcialDotDetalleIf tiempoParcialDetalle){
		this.tiempoParcialDetalle = tiempoParcialDetalle;
	}
	
	public Object[] getFileTabla(){
		Object[] objetos = new Object[]{
				Utiles.convertirFecha(tiempoParcialDetalle.getFecha())
				,Utiles.convertirHora(tiempoParcialDetalle.getHoraInicio())
				,Utiles.convertirHora(tiempoParcialDetalle.getHoraFin())
				,tiempoParcialDetalle.getTiempo()
		};
		return objetos;
	}
	
	public void setSegundos(long segundos){
		tiempoParcialDetalle.setTiempo(segundos);
	}
	
	public long getSegundos(){
		return tiempoParcialDetalle.getTiempo().longValue();
	}
	
	public void setHoraInicio(Date horaInicio){
		tiempoParcialDetalle.setHoraInicio(horaInicio.getTime());
	}
	
	public void setHoraFin(Date horaFin){
		tiempoParcialDetalle.setHoraFin(horaFin.getTime());
		//tiempoParcialDetalle.setTiempo(
		//		(tiempoParcialDetalle.getHoraFin() - tiempoParcialDetalle.getHoraInicio())/1000
		//);
	}

	public TiempoParcialDotDetalleIf getTiempoParcialDetalle() {
		return tiempoParcialDetalle;
	}

}
