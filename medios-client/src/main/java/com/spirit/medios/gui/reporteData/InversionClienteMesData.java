package com.spirit.medios.gui.reporteData;

import java.io.Serializable;

/**
 * @author Windows
 *
 */

public class InversionClienteMesData implements Serializable {
	

	private String cliente;
	private Double enero;
	private Double febrero;
	private Double marzo;
	private Double abril;
	private Double mayo;
	private Double junio;
	private Double julio;
	private Double agosto;
	private Double septiembre;
	private Double octubre;
	private Double noviembre;
	private Double diciembre;
	private Double total;
	
	private String clienteId;
	private String ruc;
	private String productoId;
	private String producto;
	private String campanaId;
	private String campana;
	private String medio;
	private String medioId;
		
	private String rucMedio;
		
	private String clienteOficina;
	private String clienteOficinaId;
	private String marca;
	private String marcaId;
	private String tipoMedio;
	private String tipoMedioId;
	private String medioOficina;
	private String medioOficinaId;
	private String tipoPauta;
	private String derechoPrograma;
	private String derechoProgramaId;
	
	
	public InversionClienteMesData(){
		cliente = "";
		clienteId = "";
		ruc = "";
		producto = "";
		medio = "";
		medioId = "";
		rucMedio = "";
		clienteOficina = "";
		clienteOficinaId = "";
		marca = "";
		marcaId = "";
		tipoMedio = "";
		tipoMedioId = "";
		medioOficina = "";
		medioOficinaId = "";
		tipoPauta = "";
		derechoPrograma = "";
		derechoProgramaId = "";
		campanaId = "";
		campana = "";
			
		enero = 0D;
		febrero = 0D;
		marzo = 0D;
		abril = 0D;
		mayo = 0D;
		junio = 0D;
		julio = 0D;
		agosto = 0D;
		septiembre = 0D;
		octubre = 0D;
		noviembre = 0D;
		diciembre = 0D;
		total = 0D;
	}

	public String getCampanaId() {
		return campanaId;
	}

	public String getCampana() {
		return campana;
	}

	public void setCampanaId(String campanaId) {
		this.campanaId = campanaId;
	}

	public void setCampana(String campana) {
		this.campana = campana;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getMedio() {
		return medio;
	}

	public void setMedio(String medio) {
		this.medio = medio;
	}

	public String getProductoId() {
		return productoId;
	}

	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}

	public Double getEnero() {
		return enero;
	}

	public void setEnero(Double enero) {
		this.enero = enero;
	}

	public Double getFebrero() {
		return febrero;
	}

	public void setFebrero(Double febrero) {
		this.febrero = febrero;
	}

	public Double getMarzo() {
		return marzo;
	}

	public void setMarzo(Double marzo) {
		this.marzo = marzo;
	}

	public Double getAbril() {
		return abril;
	}

	public void setAbril(Double abril) {
		this.abril = abril;
	}

	public Double getMayo() {
		return mayo;
	}

	public void setMayo(Double mayo) {
		this.mayo = mayo;
	}

	public Double getJunio() {
		return junio;
	}

	public void setJunio(Double junio) {
		this.junio = junio;
	}

	public Double getJulio() {
		return julio;
	}

	public void setJulio(Double julio) {
		this.julio = julio;
	}

	public Double getAgosto() {
		return agosto;
	}

	public void setAgosto(Double agosto) {
		this.agosto = agosto;
	}

	public Double getSeptiembre() {
		return septiembre;
	}

	public void setSeptiembre(Double septiembre) {
		this.septiembre = septiembre;
	}

	public Double getOctubre() {
		return octubre;
	}

	public void setOctubre(Double octubre) {
		this.octubre = octubre;
	}

	public Double getNoviembre() {
		return noviembre;
	}

	public void setNoviembre(Double noviembre) {
		this.noviembre = noviembre;
	}

	public Double getDiciembre() {
		return diciembre;
	}

	public void setDiciembre(Double diciembre) {
		this.diciembre = diciembre;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getMedioId() {
		return medioId;
	}

	public void setMedioId(String medioId) {
		this.medioId = medioId;
	}

	public String getRucMedio() {
		return rucMedio;
	}

	public void setRucMedio(String rucMedio) {
		this.rucMedio = rucMedio;
	}

	public String getClienteOficina() {
		return clienteOficina;
	}

	public void setClienteOficina(String clienteOficina) {
		this.clienteOficina = clienteOficina;
	}

	public String getClienteOficinaId() {
		return clienteOficinaId;
	}

	public void setClienteOficinaId(String clienteOficinaId) {
		this.clienteOficinaId = clienteOficinaId;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(String marcaId) {
		this.marcaId = marcaId;
	}

	public String getTipoMedio() {
		return tipoMedio;
	}

	public void setTipoMedio(String tipoMedio) {
		this.tipoMedio = tipoMedio;
	}

	public String getTipoMedioId() {
		return tipoMedioId;
	}

	public void setTipoMedioId(String tipoMedioId) {
		this.tipoMedioId = tipoMedioId;
	}

	public String getMedioOficina() {
		return medioOficina;
	}

	public void setMedioOficina(String medioOficina) {
		this.medioOficina = medioOficina;
	}

	public String getMedioOficinaId() {
		return medioOficinaId;
	}

	public void setMedioOficinaId(String medioOficinaId) {
		this.medioOficinaId = medioOficinaId;
	}

	public String getTipoPauta() {
		return tipoPauta;
	}

	public void setTipoPauta(String tipoPauta) {
		this.tipoPauta = tipoPauta;
	}

	public String getDerechoPrograma() {
		return derechoPrograma;
	}

	public void setDerechoPrograma(String derechoPrograma) {
		this.derechoPrograma = derechoPrograma;
	}

	public String getDerechoProgramaId() {
		return derechoProgramaId;
	}

	public void setDerechoProgramaId(String derechoProgramaId) {
		this.derechoProgramaId = derechoProgramaId;
	}
}
