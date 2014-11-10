package com.spirit.medios.gui.importer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;

public class Medio {

	String identificacion = null;
	ClienteIf clienteif = null;
	Map<String,ClienteOficinaIf> oficinas = new HashMap<String,ClienteOficinaIf>();
	Set<String> oficinasRevisadas = new HashSet<String>();
	
	public Medio() {
	}

	public void agregarOficina(String codigo,ClienteOficinaIf oficina){
		oficinas.put(codigo, oficina);
	}
	
	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public Map<String, ClienteOficinaIf> getOficinas() {
		return oficinas;
	}

	public void setOficinas(Map<String, ClienteOficinaIf> oficinas) {
		this.oficinas = oficinas;
	}

	public ClienteIf getClienteif() {
		return clienteif;
	}

	public void setClienteif(ClienteIf clienteif) {
		this.clienteif = clienteif;
	}

	public Set<String> getOficinasRevisadas() {
		return oficinasRevisadas;
	}

	public void setOficinasRevisadas(Set<String> oficinasRevisadas) {
		this.oficinasRevisadas = oficinasRevisadas;
	}
	
}
