package com.spirit.nomina.gui.criteria;

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
import com.spirit.nomina.entity.GastoDeducibleIf;

public class GastoDeducibleCriteria extends Criteria {

	private String codigo = null;
	private String nombre = null;
	
	Map queryBuilded;
		
	public GastoDeducibleCriteria(){
	}
	
	public List getHeaders() {
		ArrayList<Object> headers = new ArrayList<Object>();
		headers.add("Código");
		headers.add("Nombre");
		return headers;
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Gastos Deducibles";
	}
	
	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}
	
	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();
		while (it.hasNext()) {
			ArrayList<String> fila = new ArrayList<String>();
			GastoDeducibleIf bean = (GastoDeducibleIf) it.next();

			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());
			data.add(fila);
		}
		return data;
	}
	
	public void buscarRegistros(int startIndex,int endIndex){
		try{
			listaResultados = (ArrayList) SessionServiceLocator.getGastoDeducibleSessionService()
					.findGastoDeducibleByQuery(startIndex,endIndex,queryBuilded);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public int getResultListSize() {
		try{
			tamanoListaResultados = SessionServiceLocator.getGastoDeducibleSessionService()
				.getGastoDeducibleListSize(queryBuilded);
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

	private void buildQuery(){
		queryBuilded =  new HashMap<String, Object>();
		if ( codigo!= null && !"".equals(codigo) )
			queryBuilded.put("codigo", "%"+codigo+"%");
		else 
			queryBuilded.put("codigo", "%");
		if ( nombre!= null && !"".equals(nombre) )
			queryBuilded.put("nombre", "%"+nombre+"%");
		
		queryBuilded.put("empresaId", Parametros.getIdEmpresa());
	}
	
	public void setTxtParametros(String parametro1, String parametro2, String parametro3) {
		
	}
	
	@Override
	public void setTxtParametros(Map<String, Object> mapaParametros) {
		this.codigo = (String)mapaParametros.get("parametro1");
		this.nombre = (String)mapaParametros.get("parametro2");
		buildQuery();
	}
	
	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
	
}
