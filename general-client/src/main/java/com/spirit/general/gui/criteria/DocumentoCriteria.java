package com.spirit.general.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;

public class DocumentoCriteria extends Criteria {
	
	String txtCodigo, txtNombre;
	Map queryBuilded;

	public DocumentoCriteria(String nombrePanel) {
		this.nombrePanel = nombrePanel;
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Documentos";
	}

	public DocumentoCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
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
			ArrayList fila = new ArrayList();
			DocumentoIf bean = (DocumentoIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());
			data.add(fila);
		}
		return data;
	}

	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			listaResultados = (ArrayList) SessionServiceLocator.getDocumentoSessionService().findDocumentoByQuery(startIndex, endIndex, queryBuilded, Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try {
			tamanoListaResultados = SessionServiceLocator.getDocumentoSessionService()
					.getDocumentoListSize(queryBuilded,
							Parametros.getIdEmpresa());
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
		
		if (txtNombre != null && !("".equals(txtNombre)))
			aMap.put("nombre", txtNombre.toUpperCase() + "%");
		
		aMap.put("estado", "A");

		return aMap;
	}

	public void setTxtParametros(String parametro1, String parametro2, String parametro3) {
		this.txtCodigo = parametro1;
		this.txtNombre = parametro2;
		this.queryBuilded = buildQuery();
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
	 
}
