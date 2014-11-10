package com.spirit.general.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.sri.entity.SriAirIf;

public class SriAirCriteria extends Criteria {
	
	Map queryBuilded = new HashMap();

	public SriAirCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Conceptos de Retenciones";
	}
	
	public SriAirCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List getHeaders() {
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Código");
		headers.add("Porcentaje");
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
			SriAirIf bean = (SriAirIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(String.valueOf(bean.getPorcentaje().doubleValue()));
			fila.add(bean.getConcepto());
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
			listaResultados = (ArrayList) SessionServiceLocator.getSriAirSessionService().getSriAirList(startIndex, endIndex);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try {
			tamanoListaResultados = SessionServiceLocator.getSriAirSessionService().getSriAirListSize();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public void setTxtParametros(String parametro1, String parametro2, String parametro3) {
		if ( parametro1==null || "".equals(parametro1) )
			queryBuilded.put("codigo", "%");
		else
			queryBuilded.put("codigo", parametro1);
		if (parametro2==null || "".equals(parametro2) )
			queryBuilded.put("concepto", "%");
		else
			queryBuilded.put("concepto", parametro2+"%");
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	} 
}
