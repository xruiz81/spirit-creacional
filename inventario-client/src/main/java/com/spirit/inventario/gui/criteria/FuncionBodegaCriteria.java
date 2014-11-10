package com.spirit.inventario.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.FuncionBodegaIf;

public class FuncionBodegaCriteria extends Criteria {

	String txtCodigo,txtNombre,txtEstado;
	Map queryBuilded = null;
	
	public FuncionBodegaCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Función de Bodega";
	}
	
	public FuncionBodegaCriteria(String nombrePanel) {
		this.nombrePanel = nombrePanel;
	}
	
	public FuncionBodegaCriteria(List listaResultados){
		this.listaResultados = listaResultados;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Nombre");
		headers.add("Estado");
		return headers;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			FuncionBodegaIf bean = (FuncionBodegaIf) it.next();
            
			fila.add(bean.getCodigo()); 
            fila.add(bean.getNombre()); 
            String estado = (bean.getEstado().equals("A")) ? "ACTIVO" : "INACTIVO"; 
        	fila.add(estado);
          
        	data.add(fila);
		}
		return data;
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		if (txtCodigo!=null && !"".equals(txtCodigo)) {
			aMap.put("codigo", txtCodigo+"%");
		}
		else
			aMap.put("codigo", "%");
		if (txtNombre!=null && !"".equals(txtNombre)) {
			aMap.put("nombre", txtNombre+"%");
		}
		if (txtEstado!=null && !"".equals(txtEstado)) {
			aMap.put("estado", txtEstado.substring(0, 1));
		}
		else
			aMap.put("estado", "%");
		
		return aMap;
	}
	
	public void buscarRegistros(int startIndex,int endIndex){
		try{
			listaResultados = (ArrayList) SessionServiceLocator.getFuncionBodegaSessionService()
						.getFuncionBodegaList(startIndex,endIndex,queryBuilded,Parametros.getIdEmpresa());
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public int getResultListSize() {
		try{
			tamanoListaResultados = SessionServiceLocator.getFuncionBodegaSessionService()
					.getFuncionBodegaListSize(queryBuilded,Parametros.getIdEmpresa());
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	public void setTxtParametros(String parametro1, String parametro2, String parametro3) {
		this.txtCodigo = parametro1;
		this.txtNombre = parametro2;
		this.txtEstado = parametro3;
		queryBuilded = buildQuery();
	}
	
	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
 
}
