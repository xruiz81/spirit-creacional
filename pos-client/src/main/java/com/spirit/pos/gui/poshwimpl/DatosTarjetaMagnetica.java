package com.spirit.pos.gui.poshwimpl;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatosTarjetaMagnetica {

	public static int ANIO_BASE = 2000;
	String primerNombre = "";
	String segundoNombreInicial = "";
	String apellido = "";
	String numeroCuenta = "";
	String fechaExpiracion = "";
	String titulo = "";
	String track1 = "";
	String track2 = "";
	String track3 = "";
	String track4 = "";
	
	public DatosTarjetaMagnetica() {
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombreInicial() {
		return segundoNombreInicial;
	}

	public void setSegundoNombreInicial(String segundoNombreInicial) {
		this.segundoNombreInicial = segundoNombreInicial;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTrack1() {
		return track1;
	}

	public void setTrack1(String track1) {
		this.track1 = track1;
	}

	public String getTrack2() {
		return track2;
	}

	public void setTrack2(String track2) {
		this.track2 = track2;
	}

	public String getTrack3() {
		return track3;
	}

	public void setTrack3(String track3) {
		this.track3 = track3;
	}

	public String getTrack4() {
		return track4;
	}

	public void setTrack4(String track4) {
		this.track4 = track4;
	}
	
	public String getNombreCompletoDesdeTrack1(String patronSeparacion,int ubicacionEnArreglo){
		if ( getTrack1() != null  ){
			String[] linea = getTrack1().split("\\^");
		    if ( linea.length>=ubicacionEnArreglo+1 ){
		    	String nombres[] = linea[ubicacionEnArreglo].split(" ");
		    	StringBuffer nombreCompleto = new StringBuffer();
		    	for ( int i = 0; i<nombres.length ; i++ ){
		    		nombreCompleto.append(nombres[i]+" ");
		    	}
		    	return nombreCompleto.toString().trim();
		    }
		}
	    return "";
	}
	
	public Calendar getCalendarFechaExpiracion(){
		if ( getFechaExpiracion()!=null ){
			int anio = ANIO_BASE + Integer.parseInt(getFechaExpiracion().substring(0, 2));
			int mes = Integer.parseInt(getFechaExpiracion().substring(2, 4))-1;
			Calendar cal = new GregorianCalendar(anio,mes,1);
			int dia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			cal.set(Calendar.DAY_OF_MONTH, dia);
			//cal.set(anio, mes, dia);
			return cal;
		}
		return null;
	}
	
}
