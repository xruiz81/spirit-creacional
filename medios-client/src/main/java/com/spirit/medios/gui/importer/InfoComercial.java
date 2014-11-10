package com.spirit.medios.gui.importer;

import java.sql.Date;
import java.util.ArrayList;

public interface InfoComercial {

	public TipoInfoComercial getTipo(); 
	
	public Date getFecha();
	public void setFecha(Date fecha);
	
	public ArrayList<? extends Object> getLista();
	
	public void setVersion(String cadena);
	
	public void setFrecuencia(Integer frecuencia);
	
	public ArrayList<Integer> getFrecuencia();
}
