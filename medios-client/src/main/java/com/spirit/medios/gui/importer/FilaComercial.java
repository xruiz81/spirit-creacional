package com.spirit.medios.gui.importer;

import java.util.ArrayList;

public class FilaComercial {

	private int numeroOrden = 0;
	private String identificacionMedio = null;
	private String codigoMedio = null;
	private String version = "";
	private String programa = null;
	private String comercial = null;
	private String hora = null;
	private Integer totalCunias = null;
	private Double raiting1 = null;
	private Double raiting2 = null;
	private Double raitingPonderado = null;
	private Double tarifa = null;
	private Double tarifaTotal = null;
	private ArrayList<InfoComercial> comerciales = new ArrayList<InfoComercial>();
	//ADD 1 JUNIO
	private String pauta = null;
	private String auspicioDescripcion = null;
	//END 1 JUNIO
	
	public FilaComercial() {
		
	}

	public int getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(int numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public String getIdentificacionMedio() {
		return identificacionMedio;
	}

	public void setIdentificacionMedio(String identificacionMedio) {
		this.identificacionMedio = identificacionMedio;
	}

	public String getCodigoMedio() {
		return codigoMedio;
	}

	public void setCodigoMedio(String codigoMedio) {
		this.codigoMedio = codigoMedio;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Integer getTotalCunias() {
		return totalCunias;
	}

	public void setTotalCunias(Integer totalCunias) {
		this.totalCunias = totalCunias;
	}

	public Double getTarifa() {
		return tarifa;
	}

	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
	}

	public Double getTarifaTotal() {
		return tarifaTotal;
	}

	public void setTarifaTotal(Double tarifaTotal) {
		this.tarifaTotal = tarifaTotal;
	}

	public ArrayList<InfoComercial> getComerciales() {
		return comerciales;
	}

	public void setComerciales(ArrayList<InfoComercial> comerciales) {
		this.comerciales = comerciales;
	}

	public String getComercial() {
		return comercial;
	}

	public void setComercial(String comercial) {
		this.comercial = comercial;
	}

	public Double getRaiting1() {
		return raiting1;
	}

	public void setRaiting1(Double raiting1) {
		this.raiting1 = raiting1;
	}

	public Double getRaiting2() {
		return raiting2;
	}

	public void setRaiting2(Double raiting2) {
		this.raiting2 = raiting2;
	}

	public Double getRaitingPonderado() {
		return raitingPonderado;
	}

	public void setRaitingPonderado(Double raitingPonderado) {
		this.raitingPonderado = raitingPonderado;
	}

	public String getPauta() {
		return pauta;
	}

	public void setPauta(String pauta) {
		this.pauta = pauta;
	}

	public String getAuspicioDescripcion() {
		return auspicioDescripcion;
	}

	public void setAuspicioDescripcion(String auspicioDescripcion) {
		this.auspicioDescripcion = auspicioDescripcion;
	}
	
}
