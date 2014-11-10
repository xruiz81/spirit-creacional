package com.spirit.medios.gui.importer;

import java.sql.Date;
import java.util.ArrayList;

public class InfoMapaComercial implements InfoComercial {

	TipoInfoComercial tipo = TipoInfoComercial.NUMERICO;
	private Date fecha = null;
	private ArrayList<String> letras = new ArrayList<String>();
	private ArrayList<Integer> frecuencia = new ArrayList<Integer>();
	
	public InfoMapaComercial() {
	}
	
	public TipoInfoComercial getTipo() {
		return tipo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ArrayList<Integer> getLista() {
		return frecuencia;
	}

	public void setFrecuencia(Integer frecuencia) {
		this.frecuencia.add(frecuencia);
	}
	
	public ArrayList<Integer> getFrecuencia() {
		return this.frecuencia;
	}

	public void setVersion(String version) {
		this.letras.add(version);
	}	
}
