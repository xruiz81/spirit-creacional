package com.spirit.inventario.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.MovimientoIf;
import com.spirit.inventario.gui.helper.SessionServiceLocator;

public class MovimientoCriteria extends Criteria {

	Map queryBuilded;
	HashMap<Long, String> cacheBodegas = new HashMap<Long, String>();

	public void setNombrePanel() {
		this.nombrePanel = "de Movimientos";
	}
	
	/** **************************************************************** */
	
	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Codigo");
		headers.add("Fecha Aprobacion.");
		headers.add("Bodega");
		headers.add("Bodega Ref.");
		headers.add("Observacion.");
		return headers;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			MovimientoIf bean = (MovimientoIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(bean.getFechaDocumento() != null ? bean
					.getFechaDocumento() : "");
			try {
				String bodegaCache = cacheBodegas.get(bean.getBodegaId());
				if (bodegaCache == null) {
					bodegaCache = SessionServiceLocator
							.getBodegaSessionService().getBodega(
									bean.getBodegaId()).getNombre();
					cacheBodegas.put(bean.getBodegaId(), bodegaCache);
				}
				fila.add(bodegaCache);

				if (bean.getBodegarefId() != null) {
					bodegaCache = cacheBodegas.get(bean.getBodegarefId());
					if (bodegaCache == null) {
						bodegaCache = SessionServiceLocator
								.getBodegaSessionService().getBodega(
										bean.getBodegarefId()).getNombre();
						cacheBodegas.put(bean.getBodegarefId(), bodegaCache);
					}
					fila.add(bodegaCache);
				} else {
					fila.add("-");
				}

			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			fila.add(bean.getObservacion() != null ? bean.getObservacion()
							: "");
			data.add(fila);
		}
		return data;
	}

	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			listaResultados = (ArrayList) SessionServiceLocator
					.getMovimientoSessionService().getMovimientoList(
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
					.getMovimientoSessionService().getMovimientoListSize(
							queryBuilded);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	/*
	 * public int getResultListSize() { return this.tamanoListaResultados; }
	 */
	/** **************************************************************** */
	public MovimientoCriteria() {
	}

	public MovimientoCriteria(List listaResultados) {
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
