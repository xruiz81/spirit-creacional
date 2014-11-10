package com.spirit.medios.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.handler.EstadoOrdenTrabajo;

public class OrdenTrabajoCriteria extends Criteria{

	Map queryBuilded;
	Long clienteId = null;
	
	public OrdenTrabajoCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Ordenes de Trabajo";
	}
	
	public OrdenTrabajoCriteria( List listaResultados ){
		this.listaResultados =listaResultados;
	}
	
	public OrdenTrabajoCriteria( Long clienteId ){
		this.clienteId =clienteId;
	}
	
	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Descripción");
		headers.add("Estado");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			OrdenTrabajoIf bean = (OrdenTrabajoIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(bean.getDescripcion());

			try {
				EstadoOrdenTrabajo estado = EstadoOrdenTrabajo.getEstadoOrdenTrabajoByLetra(bean.getEstado());
				fila.add(estado.toString());
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				fila.add("");
			}
			
			data.add(fila);
		}
		return data;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados =listaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public void buscarRegistros(int startIndex,int endIndex){
		try{
			if (clienteId != null)
				listaResultados = (ArrayList) SessionServiceLocator.getOrdenTrabajoSessionService()
						.findOrdenTrabajoByQueryAndByClienteId(startIndex,endIndex,
								queryBuilded, clienteId, Parametros.getIdEmpresa());
			else
				listaResultados = (ArrayList) SessionServiceLocator.getOrdenTrabajoSessionService()
						.findOrdenTrabajoByQuery(startIndex,endIndex,queryBuilded);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		return this.tamanoListaResultados;
	}

	public void setTxtParametros(String txtCodigo, String txtDescripcion,String parametro3) {
		
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
	 
}
