package com.spirit.medios.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.EquipoTrabajoIf;

public class EquipoTrabajoCriteria extends Criteria{

	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	Map queryBuilded;
	
	public EquipoTrabajoCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Equipos de Trabajo";
	}
	
	public EquipoTrabajoCriteria( List listaResultados ){
		this.listaResultados =listaResultados;
	}
	
	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Tipo de Orden");
		headers.add("Fecha Inicio");
		headers.add("Fecha Fin");
		headers.add("Estado");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			EquipoTrabajoIf bean = (EquipoTrabajoIf) it.next();
 
            fila.add(bean.getCodigo()); 
            try {
				fila.add(SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(bean.getTipoordenId()).getNombre());
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
            fila.add(bean.getFechaini()); 
            fila.add(bean.getFechafin()); 
            if (bean.getEstado().equals(ESTADO_ACTIVO))
            	fila.add(NOMBRE_ESTADO_ACTIVO);
            else
            	fila.add(NOMBRE_ESTADO_INACTIVO);
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
			listaResultados = (ArrayList) SessionServiceLocator.getEquipoTrabajoSessionService().getEquipoTrabajoList(startIndex,endIndex,queryBuilded);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		return tamanoListaResultados;
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
