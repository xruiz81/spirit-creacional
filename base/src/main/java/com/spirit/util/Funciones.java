package com.spirit.util;


//Clase la cual me permite setear las funciones que tiene cada nodo del menu Personalizado
public class Funciones {

	boolean grabarActualizar;
	boolean borrar;
	boolean consultar;
	boolean autorizar;
	boolean duplicar;
	boolean imprimir;
	boolean generarGrafico;

	public Funciones() {
		grabarActualizar = false;
		borrar = false;
		consultar = false;
		autorizar = false;
		duplicar = false;
		imprimir = false;
		generarGrafico = false;
	}

	public boolean getAutorizar() {
		return autorizar;
	}

	public void setAutorizar(boolean autorizar) {
		this.autorizar = autorizar;
	}

	public boolean getBorrar() {
		return borrar;
	}

	public void setBorrar(boolean borrar) {
		this.borrar = borrar;
	}

	public boolean getConsultar() {
		return consultar;
	}

	public void setConsultar(boolean consultar) {
		this.consultar = consultar;
	}

	public boolean getGenerarGrafico() {
		return generarGrafico;
	}

	public void setGenerarGrafico(boolean generarGrafico) {
		this.generarGrafico = generarGrafico;
	}

	public boolean getGrabarActualizar() {
		return grabarActualizar;
	}

	public void setGrabarActualizar(boolean grabarActualizar) {
		this.grabarActualizar = grabarActualizar;
	}

	public boolean getImprimir() {
		return imprimir;
	}

	public void setImprimir(boolean imprimir) {
		this.imprimir = imprimir;
	}

	public boolean getDuplicar() {
		return duplicar;
	}

	public void setDuplicar(boolean duplicar) {
		this.duplicar = duplicar;
	}
}

