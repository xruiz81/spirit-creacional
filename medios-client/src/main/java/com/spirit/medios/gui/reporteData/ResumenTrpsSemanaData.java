package com.spirit.medios.gui.reporteData;

import java.io.Serializable;

public class ResumenTrpsSemanaData implements Serializable {

	private static final long serialVersionUID = -5601736763824120368L;
	
	String semana;
	String spots;
	String inversion;
	String trpsGye;
	String trpsUio;
	String trpsNac;
	
	public ResumenTrpsSemanaData(){
		semana = "";
		spots = "";
		inversion = "";
		trpsGye = "";
		trpsUio = "";
		trpsNac = "";
	}

	public String getSemana() {
		return semana;
	}

	public void setSemana(String semana) {
		this.semana = semana;
	}

	public String getSpots() {
		return spots;
	}

	public void setSpots(String spots) {
		this.spots = spots;
	}

	public String getInversion() {
		return inversion;
	}

	public void setInversion(String inversion) {
		this.inversion = inversion;
	}

	public String getTrpsGye() {
		return trpsGye;
	}

	public void setTrpsGye(String trpsGye) {
		this.trpsGye = trpsGye;
	}

	public String getTrpsUio() {
		return trpsUio;
	}

	public void setTrpsUio(String trpsUio) {
		this.trpsUio = trpsUio;
	}

	public String getTrpsNac() {
		return trpsNac;
	}

	public void setTrpsNac(String trpsNac) {
		this.trpsNac = trpsNac;
	}
}
