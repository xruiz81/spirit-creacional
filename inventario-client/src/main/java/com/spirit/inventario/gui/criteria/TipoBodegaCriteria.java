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
import com.spirit.inventario.entity.TipoBodegaIf;

public class TipoBodegaCriteria extends Criteria {

	String txtCodigo,txtNombre;
	Map queryBuilded = null;
	
	public TipoBodegaCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Tipos de Bodega";
	}
	
	public TipoBodegaCriteria(String nombrePanel) {
		this.nombrePanel = nombrePanel;
	}
	
	public TipoBodegaCriteria(List listaResultados){
		this.listaResultados = listaResultados;
	}

	public List getHeaders() {
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Código");
		headers.add("Nombre");
		return headers;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList<String> fila = new ArrayList<String>();
			TipoBodegaIf bean = (TipoBodegaIf) it.next();
            
			fila.add(bean.getCodigo()); 
            fila.add(bean.getNombre()); 
          
        	data.add(fila);
		}
		return data;
	}
	
	private Map buildQuery() {
		Map<String,Object> aMap = new HashMap<String,Object>();
		if (txtCodigo!=null && !"".equals(txtCodigo)) {
			aMap.put("codigo", txtCodigo+"%");
		}
		else
			aMap.put("codigo", "%");
		if (txtNombre!=null && !"".equals(txtNombre)) {
			aMap.put("nombre", txtNombre+"%");
		}
		else
			aMap.put("nombre", "%");
		
		//if (Long.valueOf(Parametros.getIdEmpresa()).compareTo(0L) != 0)
		aMap.put("empresaId", Parametros.getIdEmpresa());
		
		return aMap;
	}
	
	private List obtenerListaModel(){
		List lista = null;
		try {
			//if(idFiltroBusqueda==0L ){
				lista = ( (ArrayList) SessionServiceLocator.getTipoBodegaSessionService().findTipoBodegaByQuery(buildQuery()) );
			//}
			listaResultados = lista;
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return lista;
	}
	
	public void buscarRegistros(int startIndex,int endIndex){
		try{
			listaResultados = ( (ArrayList) SessionServiceLocator.getTipoBodegaSessionService().getTipoBodegaList(
							startIndex,endIndex,queryBuilded) );
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public int getResultListSize() {
		try{
			tamanoListaResultados = SessionServiceLocator.getTipoBodegaSessionService()
				.getTipoBodegaListSize(queryBuilded);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public void setTxtParametros(String parametro1, String parametro2, String parametro3) {
		this.txtCodigo = parametro1;
		this.txtNombre = parametro2;
		queryBuilded = buildQuery();
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
	 
}
