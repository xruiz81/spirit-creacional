package com.spirit.general.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.OrigenDocumentoIf;
import com.spirit.general.session.OrigenDocumentoSessionService;
import com.spirit.servicelocator.ServiceLocator;

public class OrigenDocumentoCriteria extends Criteria {
	
	String nombrePanel = "de Origen Documento";
	Map queryBuilded;

	public OrigenDocumentoCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Origen Documento";
	}

	public OrigenDocumentoCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List getHeaders() {
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Código");
		headers.add("Nombre");
		return headers;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();
		Iterator it = listaResultados.iterator();

		while (it.hasNext()) {
			ArrayList<String> fila = new ArrayList<String>();
			OrigenDocumentoIf bean = (OrigenDocumentoIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());
			data.add(fila);
		}
		return data;
	}

	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			listaResultados = (ArrayList) getOrigenDocumentoSessionService()
					.findOrigenDocumentoByQuery(startIndex, endIndex, queryBuilded);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try {
			tamanoListaResultados = getOrigenDocumentoSessionService()
					.getOrigenDocumentoListSize(queryBuilded);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public void setTxtParametros(String parametro1, String parametro2,
			String parametro3) {
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}

	public static OrigenDocumentoSessionService getOrigenDocumentoSessionService() {
		try {
			return (OrigenDocumentoSessionService) ServiceLocator
					.getService(ServiceLocator.ORIGENDOCUMENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
}
