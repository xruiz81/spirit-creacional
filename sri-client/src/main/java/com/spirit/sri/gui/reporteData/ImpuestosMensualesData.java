package com.spirit.sri.gui.reporteData;

import java.io.Serializable;

public class ImpuestosMensualesData implements Serializable {
	private static final long serialVersionUID = -5915929647312060223L;
	
	private String campo;
	private String enero;
	private String febrero;
	private String marzo;
	private String abril;
	private String mayo;
	private String junio;
	private String julio;
	private String agosto;
	private String septiembre;
	private String octubre;
	private String noviembre;
	private String diciembre;
	private String total;
	
	public ImpuestosMensualesData(){
		campo = "";
		enero = "";
		febrero = "";
		marzo = "";
		abril = "";
		mayo = "";
		junio = "";
		julio = "";
		agosto = "";
		septiembre = "";
		octubre = "";
		noviembre = "";
		diciembre = "";
		total = "";
	}

	public String getAbril() {
		return abril;
	}

	public void setAbril(String abril) {
		this.abril = abril;
	}

	public String getAgosto() {
		return agosto;
	}

	public void setAgosto(String agosto) {
		this.agosto = agosto;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getDiciembre() {
		return diciembre;
	}

	public void setDiciembre(String diciembre) {
		this.diciembre = diciembre;
	}

	public String getEnero() {
		return enero;
	}

	public void setEnero(String enero) {
		this.enero = enero;
	}

	public String getFebrero() {
		return febrero;
	}

	public void setFebrero(String febrero) {
		this.febrero = febrero;
	}

	public String getJulio() {
		return julio;
	}

	public void setJulio(String julio) {
		this.julio = julio;
	}

	public String getJunio() {
		return junio;
	}

	public void setJunio(String junio) {
		this.junio = junio;
	}

	public String getMarzo() {
		return marzo;
	}

	public void setMarzo(String marzo) {
		this.marzo = marzo;
	}

	public String getMayo() {
		return mayo;
	}

	public void setMayo(String mayo) {
		this.mayo = mayo;
	}

	public String getNoviembre() {
		return noviembre;
	}

	public void setNoviembre(String noviembre) {
		this.noviembre = noviembre;
	}

	public String getOctubre() {
		return octubre;
	}

	public void setOctubre(String octubre) {
		this.octubre = octubre;
	}

	public String getSeptiembre() {
		return septiembre;
	}

	public void setSeptiembre(String septiembre) {
		this.septiembre = septiembre;
	}
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}	
}
