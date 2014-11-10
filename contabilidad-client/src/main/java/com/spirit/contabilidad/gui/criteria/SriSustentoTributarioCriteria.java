package com.spirit.contabilidad.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.sri.entity.SriSustentoTributarioIf;

public class SriSustentoTributarioCriteria extends Criteria {
	
	String txtCodigo, txtDescripcion;
	Map queryBuilded;

	public SriSustentoTributarioCriteria(String nombrePanel) {
		this.nombrePanel = nombrePanel;
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Tipo Sustento Tributario";
	}

	public SriSustentoTributarioCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Descripción");
		return headers;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();
		Iterator it = listaResultados.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			SriSustentoTributarioIf bean = (SriSustentoTributarioIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(bean.getDescripcion());
			data.add(fila);
		}
		return data;
	}

	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			listaResultados = (ArrayList) SessionServiceLocator.getSriSustentoTributarioSessionService().findSriSustentoTributarioByQuery(startIndex, endIndex, queryBuilded);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try {
			tamanoListaResultados = SessionServiceLocator.getSriSustentoTributarioSessionService().getSriSustentoTributarioListSize(queryBuilded);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	private Map buildQuery() {
		Map aMap = new HashMap();
		if (txtCodigo != null && !("".equals(txtCodigo)))
			aMap.put("codigo", txtCodigo.toUpperCase() + "%");
		else
			aMap.put("codigo", "%");
		
		if (txtDescripcion != null && !("".equals(txtDescripcion)))
			aMap.put("descripcion", txtDescripcion.toUpperCase() + "%");
		
		return aMap;
	}

	public void setTxtParametros(String parametro1, String parametro2, String parametro3) {
		this.txtCodigo = parametro1;
		this.txtDescripcion = parametro2;
		this.queryBuilded = buildQuery();
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
}
