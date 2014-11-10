package com.spirit.compras.gastos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.spirit.compras.entity.CompraAsociadaGastoIf;
import com.spirit.compras.entity.CompraDetalleGastoIf;
import com.spirit.compras.entity.CompraGastoIf;

public class CompraGastoClase implements Serializable{

	private static final long serialVersionUID = 4970732507430252263L;
	
	public final int COMPRA = 0;
	public final int GASTO = 1;
	
	Long compraId = null;
	
	Map<Long,CompraGastoIf> mapaComprasGastos = null;
	Map<Long,CompraDetalleGastoClase> mapaCompraDetalleGasto = null;
	Map<Long, CompraAsociadaGastoClase> mapaComprasAsociadas = null;
	
	ArrayList<CompraGastoIf> listaEliminacionComprasGastos = null;
	ArrayList<CompraDetalleGastoIf> listaEliminacionComprasDetalleGastos = null;
	ArrayList<CompraAsociadaGastoIf> listaEliminacionComprassAsociadas = null;
	
	public CompraGastoClase(){
		mapaComprasGastos = new HashMap<Long, CompraGastoIf>();
		mapaCompraDetalleGasto = new HashMap<Long, CompraDetalleGastoClase>();
		mapaComprasAsociadas =  new HashMap<Long, CompraAsociadaGastoClase>();
		
		listaEliminacionComprasGastos = new ArrayList<CompraGastoIf>();
		listaEliminacionComprasDetalleGastos = new ArrayList<CompraDetalleGastoIf>();
		listaEliminacionComprassAsociadas = new ArrayList<CompraAsociadaGastoIf>();
	}

	public CompraGastoIf getMapaCompraGasto(Long gastoId){
		return mapaComprasGastos.get(gastoId);
	}
	
	//---------------------
	
	public Long getCompraId() {
		return compraId;
	}

	public void setCompraId(Long compraId) {
		this.compraId = compraId;
	}

	public Map<Long,CompraGastoIf> getMapaComprasGastos() {
		return mapaComprasGastos;
	}

	public void setMapaComprasGastos(Map<Long,CompraGastoIf> mapaComprasGastos) {
		this.mapaComprasGastos = mapaComprasGastos;
	}

	public Map<Long,CompraDetalleGastoClase> getMapaCompraDetalleGasto() {
		return mapaCompraDetalleGasto;
	}

	public void setMapaCompraDetalleGasto(
			Map<Long,CompraDetalleGastoClase> mapaCompraDetalleGasto) {
		this.mapaCompraDetalleGasto = mapaCompraDetalleGasto;
	}

	public ArrayList<CompraGastoIf> getListaEliminacionComprasGastos() {
		return listaEliminacionComprasGastos;
	}

	public void setListaEliminacionComprasGastos(
			ArrayList<CompraGastoIf> listaEliminacionComprasGastos) {
		this.listaEliminacionComprasGastos = listaEliminacionComprasGastos;
	}

	public ArrayList<CompraDetalleGastoIf> getListaEliminacionComprasDetalleGastos() {
		return listaEliminacionComprasDetalleGastos;
	}

	public void setListaEliminacionComprasDetalleGastos(
			ArrayList<CompraDetalleGastoIf> listaEliminacionComprasDetalleGastos) {
		this.listaEliminacionComprasDetalleGastos = listaEliminacionComprasDetalleGastos;
	}

	public Map<Long, CompraAsociadaGastoClase> getMapaComprasAsociadas() {
		return mapaComprasAsociadas;
	}

	public void setMapaComprasAsociadas(
			Map<Long, CompraAsociadaGastoClase> mapaComprasAsociadas) {
		this.mapaComprasAsociadas = mapaComprasAsociadas;
	}

	public ArrayList<CompraAsociadaGastoIf> getListaEliminacionComprassAsociadas() {
		return listaEliminacionComprassAsociadas;
	}

	public void setListaEliminacionComprassAsociadas(
			ArrayList<CompraAsociadaGastoIf> listaEliminacionComprassAsociadas) {
		this.listaEliminacionComprassAsociadas = listaEliminacionComprassAsociadas;
	}
	
}
