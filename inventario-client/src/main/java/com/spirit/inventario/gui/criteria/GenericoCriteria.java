package com.spirit.inventario.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.GenericoIf;

public class GenericoCriteria extends Criteria{

	Long idFiltroBusqueda=0L;
	String txtCodigo,txtNombre;
	Map queryBuilded;
	
	public GenericoCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Genéricos";
	}
	
	public GenericoCriteria( List listaResultados ){
		this.listaResultados =listaResultados;
	}

	public GenericoCriteria(Long idFiltro) {
		idFiltroBusqueda = idFiltro;
	}
	
	public GenericoCriteria(String nombre, Long idFiltro) {
		//modelForm = model;
		nombrePanel = nombre;
		idFiltroBusqueda = idFiltro;
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
			GenericoIf bean = (GenericoIf) it.next();
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
			if (idFiltroBusqueda==0L){
				if (queryBuilded.size()==0){
					listaResultados = (ArrayList) SessionServiceLocator.getGenericoSessionService()
								.getGenericoList(startIndex, endIndex);
				}
				else{
					listaResultados = (ArrayList) SessionServiceLocator.getGenericoSessionService()
								.getGenericoList(startIndex, endIndex, queryBuilded);
				}
			}
			else{
				if (queryBuilded.size()==0){
					Map filtroMap = new HashMap();
					filtroMap.put("empresaId", idFiltroBusqueda);
					listaResultados = (ArrayList) SessionServiceLocator.getGenericoSessionService()
								.getGenericoList(startIndex, endIndex, filtroMap);
				}
				else{
					queryBuilded.put("empresaId", idFiltroBusqueda);
					listaResultados = (ArrayList) SessionServiceLocator.getGenericoSessionService()
								.getGenericoList(startIndex, endIndex, queryBuilded);
				}
			}
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public int getResultListSize(){
		//Obtengo eltamaño de los regsitros devueltos en la busqueda
		try{
			if(idFiltroBusqueda==0L){
				if(queryBuilded.size()==0){
					//tamanoListaResultados = modelForm.getListSize();
					tamanoListaResultados = SessionServiceLocator.getGenericoSessionService()
						.getGenericoListSize();
				}
				else{
					//tamanoListaResultados = modelForm.getListSize(queryBuilded);
					tamanoListaResultados = SessionServiceLocator.getGenericoSessionService()
						.getGenericoListSize(queryBuilded);
				}
			}else{
				if(queryBuilded.size()==0){
					//tamanoListaResultados = modelForm.getListSize(idFiltroBusqueda);
					Map filtroMap = new HashMap();
					filtroMap.put("empresaId", idFiltroBusqueda);
					tamanoListaResultados = SessionServiceLocator.getGenericoSessionService()
						.getGenericoListSize(filtroMap);
				}
				else{
					//tamanoListaResultados = modelForm.getListSize(queryBuilded,idFiltroBusqueda);
					queryBuilded.put("empresaId", idFiltroBusqueda);
					tamanoListaResultados = SessionServiceLocator.getGenericoSessionService()
						.getGenericoListSize(queryBuilded);
				}
			}
		}catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return 0;
		}
		return tamanoListaResultados;
	}
	
	private Map buildQuery() {
		Map aMap  = new HashMap();
		if ( this.txtCodigo!=null && !("".equals(this.txtCodigo))) {
			aMap.put("codigo", this.txtCodigo+"%");
		}
		else 
			aMap.put("codigo", "%");
		if ( this.txtNombre!=null && !("".equals(this.txtNombre))) {
			aMap.put("nombre", this.txtNombre+"%");
		}
		return aMap;
	}

	public void setTxtParametros(String parametro1, String parametro2,String parametro3) {
		this.txtCodigo = parametro1;
		this.txtNombre = parametro2;
		queryBuilded = buildQuery() ;
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
	 
}
