package com.spirit.rrhh.gui.reporteData;

import java.io.Serializable;

public class DatosIdiomasData implements Serializable  {
	private static final long serialVersionUID = 7644977459530184219L;

	private String idioma;
	private String habla;
	private String lee;
	private String comprende;
	private String escribe;
	
	
	public DatosIdiomasData(){

		idioma="";
		habla="";
		lee="";
		comprende="";
		escribe="";
	}


	public String getIdioma() {
		return idioma;
	}


	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}


	public String getHabla() {
		return habla;
	}


	public void setHabla(String habla) {
		this.habla = habla;
	}


	public String getLee() {
		return lee;
	}


	public void setLee(String lee) {
		this.lee = lee;
	}


	public String getComprende() {
		return comprende;
	}


	public void setComprende(String comprende) {
		this.comprende = comprende;
	}


	public String getEscribe() {
		return escribe;
	}


	public void setEscribe(String escribe) {
		this.escribe = escribe;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	 
}
