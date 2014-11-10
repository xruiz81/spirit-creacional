/*
 * SubTarea.java
 *
 * Created on June 19, 2007, 5:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package timeTracker.tiempo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.spirit.medios.entity.TiempoParcialDotData;
import com.spirit.medios.entity.TiempoParcialDotIf;

public class SubTarea implements Serializable {

	private static final long serialVersionUID = 1L;

	private TiempoParcialDotIf tiempoParcial;
	private Collection<SubTareaDetalle> detalle = new ArrayList<SubTareaDetalle>();

	public SubTarea(Long idOrdenTrabajoDetalle) {
		this.tiempoParcial = new TiempoParcialDotData();
		this.tiempoParcial.setIdOrdenTrabajoDetalle(idOrdenTrabajoDetalle);
		java.util.Date fecha = new Date();
		this.tiempoParcial.setFechaInicio(fecha.getTime());
		this.tiempoParcial.setFechaFin(fecha.getTime());
		this.tiempoParcial.setDescripcion("");
		this.tiempoParcial.setTiempo(0L);
	}

	public Long getId() {
		return this.tiempoParcial.getId();
	}

	public SubTarea(TiempoParcialDotIf tiempoParcial) {
		this.tiempoParcial = tiempoParcial;
	}

	public Object[] getFilaDeTabla() {
		Object[] objetos = { tiempoParcial.getDescripcion(),
				tiempoParcial.getTiempo() };
		return objetos;
	}

	public void addDetalle(SubTareaDetalle detalle) {
		if (detalle != null)
			this.detalle.add(detalle);
	}

	public TiempoParcialDotIf getTiempoParcial() {
		return tiempoParcial;
	}

	public void setTiempoParcial(TiempoParcialDotIf tiempoParcial) {
		this.tiempoParcial = tiempoParcial;
	}

	public Collection<SubTareaDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(Collection<SubTareaDetalle> detalle) {
		this.detalle = detalle;
	}

	public String getDescripcion() {
		return tiempoParcial.getDescripcion();
	}

	public void setDescripcion(String descripcion) {
		tiempoParcial.setDescripcion(descripcion);
	}

	public long getSegundos() {
		return tiempoParcial.getTiempo().longValue();
	}

	public void setSegundos(long segundos) {
		tiempoParcial.setTiempo(segundos);
	}

	public void setFechaFin(Date fechaFin) {
		tiempoParcial.setFechaFin(fechaFin.getTime());
	}

	public long getFechaInicio() {
		return tiempoParcial.getFechaInicio();
	}

	public long getFechaFin() {
		return tiempoParcial.getFechaFin();
	}

	public java.lang.Long getUsuarioAsignadoId() {
		return tiempoParcial.getUsuarioAsignadoId();
	}

	public void setUsuarioAsignadoId(java.lang.Long usuarioAsignadoId) {
		this.tiempoParcial.setUsuarioAsignadoId(usuarioAsignadoId);
	}
}
