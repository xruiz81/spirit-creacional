package com.spirit.inventario.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.gui.helper.SessionServiceLocator;

public class ModeloCriteria extends Criteria {

	Map queryBuilded;
	
	public ModeloCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Modelo";
	}
	
	public ModeloCriteria( List listaResultados ){
		this.listaResultados =listaResultados;
	}
	
	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Codigo");
		headers.add("Nombre");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			ModeloIf bean = (ModeloIf) it.next();
			fila.add(bean.getCodigo()); 
            fila.add(bean.getNombre()); 
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
			listaResultados = (ArrayList) SessionServiceLocator.getModeloSessionService().getModeloList(startIndex, endIndex);
			
			System.out.println("queryBuilded--->::"+queryBuilded);
			if (queryBuilded!=null && queryBuilded.size()!=0){				
				listaResultados = (ArrayList) SessionServiceLocator.getModeloSessionService().findModeloByEmpresaId
				(startIndex, endIndex, queryBuilded);
			}
			
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try {
			tamanoListaResultados = SessionServiceLocator.getModeloSessionService().getModeloListSize();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
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
