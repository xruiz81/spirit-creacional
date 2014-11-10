package com.spirit.contabilidad.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.util.Utilitarios;

public class AsientoCriteria extends Criteria {

	Map queryBuilded;
	
	public AsientoCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "";
	}
	
	public AsientoCriteria( List listaResultados ) {
		this.listaResultados =listaResultados;
	}
	
	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Número");
		headers.add("Fecha");
		headers.add("Concepto");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			AsientoIf bean = (AsientoIf) it.next();
			fila.add(bean.getNumero());
			fila.add(Utilitarios.getFechaCortaUppercase(bean.getFecha()));
			fila.add(bean.getObservacion());
			data.add(fila);
		}
		return data;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados =listaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}
	
	public void buscarRegistros(int startIndex,int endIndex){
		try {
			listaResultados = (ArrayList) SessionServiceLocator.getAsientoSessionService().getAsientoList(startIndex, endIndex, queryBuilded);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		return tamanoListaResultados;
	}

	public void setTxtParametros(String txtCodigo, String txtDescripcion,String parametro3) {
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}

	
}
