package com.spirit.general.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DepartamentoIf;

public class DepartamentoCriteria extends Criteria {
	
	Map queryBuilded;
	
	public DepartamentoCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Departamentos";
	}
	
	public DepartamentoCriteria(List listaResultados) {
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
			DepartamentoIf bean = (DepartamentoIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());
			data.add(fila);
		}
		return data;
	}

	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			listaResultados = (ArrayList) SessionServiceLocator.getDepartamentoSessionService().findDepartamentoByQuery(
					queryBuilded,startIndex, endIndex);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try {
			tamanoListaResultados = SessionServiceLocator.getDepartamentoSessionService().getDepartamentoListSizeByQuery(queryBuilded);
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
		if ( parametro1==null || "".equals(parametro1) )
			queryBuilded.put("codigo", "%");
		else
			queryBuilded.put("codigo", parametro1);
		if (parametro2==null || "".equals(parametro2) )
			queryBuilded.put("nombre", "%");
		else
			queryBuilded.put("nombre", parametro2+"%");
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
 
}
