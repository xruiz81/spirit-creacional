package com.spirit.inventario.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.SolicitudTransferenciaIf;
import com.spirit.inventario.gui.helper.CatalogoLocator;
import com.spirit.inventario.gui.helper.SessionServiceLocator;

public class SolicitudTransferenciaCriteria extends Criteria {

	Map queryBuilded;

	/** **************************************************************** */
	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Fecha Creación");
		headers.add("Estado");
		return headers;
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Movimientos";
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			SolicitudTransferenciaIf bean = (SolicitudTransferenciaIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(bean.getFechaIngreso());
			fila.add(CatalogoLocator.CAT_ESTADO_SOLICITUD_TRF.getCatalogoByCodigo(bean.getEstado()).getDescripcion());
			data.add(fila);
		}
		return data;
	}

	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			listaResultados = (ArrayList) SessionServiceLocator
					.getSolicitudTransferenciaSessionService().getSolicitudTransferenciaList(
							startIndex, endIndex, queryBuilded);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try {
			tamanoListaResultados = SessionServiceLocator
					.getSolicitudTransferenciaSessionService().getSolicitudTransferenciaListSize(queryBuilded);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	/*
	 * public int getResultListSize() { return this.tamanoListaResultados; }
	 */
	/** **************************************************************** */
	public SolicitudTransferenciaCriteria() {
	}

	public SolicitudTransferenciaCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public void setTxtParametros(String txtCodigo, String txtDescripcion,
			String parametro3) {
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}

}
