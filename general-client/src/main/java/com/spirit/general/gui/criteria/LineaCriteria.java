package com.spirit.general.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.LineaIf;
import com.spirit.general.session.LineaSessionService;
import com.spirit.servicelocator.ServiceLocator;

public class LineaCriteria extends Criteria {


	Map queryBuilded;

	public LineaCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Linea";
	}

	public LineaCriteria(List listaResultados) {
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
			LineaIf bean = (LineaIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());
			data.add(fila);
		}
		return data;
	}

	public List pagina(int page) {
		ArrayList data;
		int base = page * numElementosPorPagina;
		int maximo = tamanoListaResultados;
		int resultado = ((base + 10) <= maximo) ? (base + 10) : maximo;
		// System.out.println("Base: "+base+" Max: "+maximo+" Resul:
		// "+resultado);
		buscarRegistros(base, resultado);
		data = (ArrayList) armarModel(listaResultados);
		return data;
	}

	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			listaResultados = (ArrayList) getLineaSessionService()
					.findLineaByQuery(startIndex, endIndex, queryBuilded);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try {
			tamanoListaResultados = getLineaSessionService().getLineaListSize(
					queryBuilded);
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

	public static LineaSessionService getLineaSessionService() {
		try {
			return (LineaSessionService) ServiceLocator
					.getService(ServiceLocator.LINEASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
}
