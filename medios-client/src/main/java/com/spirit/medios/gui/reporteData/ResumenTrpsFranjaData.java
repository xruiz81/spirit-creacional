package com.spirit.medios.gui.reporteData;

import java.io.Serializable;

public class ResumenTrpsFranjaData implements Serializable {

	private static final long serialVersionUID = -629734876902731877L;
	
	String franja;
	String spots;
	String inversion;
	String trpsGye;
	String trpsUio;
	String trpsNac;
	String cpr;
	
	public ResumenTrpsFranjaData(){
		franja = "";
		spots = "";
		inversion = "";
		trpsGye = "";
		trpsUio = "";
		trpsNac = "";
		cpr = "";
	}
	
	public String getFranja() {
		return franja;
	}

	public void setFranja(String franja) {
		this.franja = franja;
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

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}
}
