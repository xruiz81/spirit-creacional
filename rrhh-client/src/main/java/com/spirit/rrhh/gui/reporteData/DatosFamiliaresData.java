package com.spirit.rrhh.gui.reporteData;

import java.io.Serializable;

public class DatosFamiliaresData implements Serializable  {
	private static final long serialVersionUID = 7644977459530184219L;

	private String nombre;
	private String fecNac;
	private String cedIdentidad;
	private String ocupacion;
	private String trabaja;
	private String embara;
	
	
	public DatosFamiliaresData(){
		nombre="";
		fecNac="";
		cedIdentidad="";
		ocupacion="";
		trabaja="";
		embara="";
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getFecNac() {
		return fecNac;
	}


	public void setFecNac(String fecNac) {
		this.fecNac = fecNac;
	}


	public String getCedIdentidad() {
		return cedIdentidad;
	}


	public void setCedIdentidad(String cedIdentidad) {
		this.cedIdentidad = cedIdentidad;
	}


	public String getOcupacion() {
		return ocupacion;
	}


	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}


	public String getTrabaja() {
		return trabaja;
	}


	public void setTrabaja(String trabaja) {
		this.trabaja = trabaja;
	}


	public String getEmbara() {
		return embara;
	}


	public void setEmbara(String embara) {
		this.embara = embara;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	 
}
