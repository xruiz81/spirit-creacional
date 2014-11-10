package com.spirit.crm.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.exception.GenericBusinessException;

public class CorporacionCriteria extends Criteria {

	Long idFiltroBusqueda;
	String txtCodigo, txtNombre;
	Map queryBuilded;

	public CorporacionCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Corporaci\u00f3n";
	}

	public CorporacionCriteria(String nombre, Long idFiltro) {
		nombrePanel = nombre;
		idFiltroBusqueda = idFiltro;
	}

	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Codigo");
		headers.add("Nombre");
		return headers;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();

		for (int i = 0; i < lista.size(); i++) {
			ArrayList fila = new ArrayList();
			CorporacionIf bean = (CorporacionIf) lista.get(i);
			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());
			data.add(fila);
		}
		return data;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			if (idFiltroBusqueda == 0L) {
				if (queryBuilded.size() == 0) {
					// dataList = modelForm.find(startIndex,endIndex);
					listaResultados = (ArrayList) SessionServiceLocator
						.getCorporacionSessionService().getCorporacionList(startIndex, endIndex);
				} else {
					// dataList = modelForm.find(startIndex, endIndex,
					// queryBuilded);
					listaResultados = (ArrayList) SessionServiceLocator
						.getCorporacionSessionService().getCorporacionList(startIndex, endIndex, queryBuilded);
				}
			} else {
				if (queryBuilded.size() == 0) {
					// dataList = modelForm.find(startIndex,
					// endIndex,idFiltroBusqueda);
					listaResultados = (ArrayList) SessionServiceLocator
						.getCorporacionSessionService().getCorporacionList(startIndex, endIndex, idFiltroBusqueda);
				} else {
					// dataList = modelForm.find(startIndex, endIndex,
					// queryBuilded,idFiltroBusqueda);
					listaResultados = (ArrayList) SessionServiceLocator
						.getCorporacionSessionService().getCorporacionList(startIndex, endIndex, queryBuilded, idFiltroBusqueda);
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		// Obtengo eltamaño de los regsitros devueltos en la busqueda
		try {
			if (idFiltroBusqueda == 0L) {
				if (queryBuilded.size() == 0) {
					// listaSize = modelForm.getListSize();
					tamanoListaResultados = SessionServiceLocator
						.getCorporacionSessionService().getCorporacionListSize();
				} else {
					// /listaSize = modelForm.getListSize(queryBuilded);
					tamanoListaResultados = SessionServiceLocator
						.getCorporacionSessionService().getCorporacionListSize(queryBuilded);
				}
			} else {
				if (queryBuilded.size() == 0) {
					// listaSize = modelForm.getListSize(idFiltroBusqueda);
					tamanoListaResultados = SessionServiceLocator
						.getCorporacionSessionService().getCorporacionListSize(idFiltroBusqueda);
				} else {
					// listaSize =
					// modelForm.getListSize(queryBuilded,idFiltroBusqueda);
					tamanoListaResultados = SessionServiceLocator
						.getCorporacionSessionService().getCorporacionListSize(queryBuilded,idFiltroBusqueda);
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	public void setTxtParametro1(String parametro1) {
		this.txtCodigo = parametro1;
		queryBuilded = buildQuery();
	}

	public void setTxtParametro2(String parametro2) {
		this.txtNombre = parametro2;
		queryBuilded = buildQuery();
	}

	public void setTxtParametro3(String parametro3) {
	}

	public void setTxtParametros(String parametro1, String parametro2,
			String parametro3) {
		this.txtCodigo = parametro1;
		this.txtNombre = parametro2;
		queryBuilded = buildQuery();
	}

	// Me devuele un map segun el campo que mando a buscar
	private Map buildQuery() {
		Map aMap = new HashMap();
		if (this.txtCodigo != null && !("".equals(this.txtCodigo))) {
			aMap.put("codigo", this.txtCodigo + "%");
		} else
			aMap.put("codigo", "%");
		if (this.txtNombre != null && !("".equals(this.txtNombre))) {
			aMap.put("nombre", this.txtNombre + "%");
		}
		return aMap;
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}

}
