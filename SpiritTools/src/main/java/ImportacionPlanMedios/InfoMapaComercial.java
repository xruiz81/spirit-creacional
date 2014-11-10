package ImportacionPlanMedios;

import java.sql.Date;

public class InfoMapaComercial {

	private Date fecha = null;
	private Integer frecuencia = null;
	
	public InfoMapaComercial() {
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Integer frecuencia) {
		this.frecuencia = frecuencia;
	}
	
}
