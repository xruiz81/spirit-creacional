package com.spirit.medios.gui.importer;

import java.sql.Date;
import java.util.ArrayList;

public class InfoComercialMultiple implements InfoComercial {

	TipoInfoComercial tipo = TipoInfoComercial.MULTIPLE;
	private Date fecha = null;
	private ArrayList<String> letras = new ArrayList<String>();
	private ArrayList<Integer> frecuencia = new ArrayList<Integer>();
	
	public InfoComercialMultiple() {
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

	public ArrayList<String> getLista() {
		return letras;
	}
	
	public ArrayList<String> setLista(ArrayList lista) {
		return this.letras = lista;
	}

	/*public void setValor(String letras) {
		char letrasA[] = letras.toCharArray();
		for (char c : letrasA){
			this.letras.add(String.valueOf(c));
		}
	}*/
	
	public void setVersion(String version) {
		this.letras.add(version);
	}
	
	public void setFrecuencia(Integer frecuencia) {
		this.frecuencia.add(frecuencia);
	}
	
	public ArrayList<Integer> getFrecuencia() {
		return this.frecuencia;
	}
}
