package com.spirit.general.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.session.EmpresaSessionService;
import com.spirit.servicelocator.ServiceLocator;

public class EmpresaCriteria extends Criteria {
	
	Map queryBuilded;

	public EmpresaCriteria() {
		queryBuilded = new HashMap();
		queryBuilded.put("codigo","%");
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Empresas";
	}

	public EmpresaCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List getHeaders() {
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Código");
		headers.add("Ruc");
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
			EmpresaIf bean = (EmpresaIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(bean.getRuc());
			fila.add(bean.getNombre());
			data.add(fila);
		}
		return data;
	}

	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			listaResultados = (ArrayList) getEmpresaSessionService().findEmpresaByQuery(startIndex, endIndex, queryBuilded);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try {
			tamanoListaResultados = getEmpresaSessionService().getEmpresaListSize(queryBuilded);
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

	public static EmpresaSessionService getEmpresaSessionService() {
		try {
			return (EmpresaSessionService) ServiceLocator
					.getService(ServiceLocator.EMPRESASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
}
