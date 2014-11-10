package com.spirit.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class Criteria {
	
	protected List listaResultados;
	protected int numElementosPorPagina=Parametros.getNumeroElementosPorPagina();
	protected int tamanoListaResultados=0;
	protected String nombrePanel = "";
	protected Map<String, Object> mapaParametros = null;
	
	public Criteria(){
		setNombrePanel();
	}
	
	public abstract void setNombrePanel();
	
	public abstract List getHeaders();
	public List getHeadersString(){
		return null;
	};
	
	public abstract void setResultList(List list);
	
	public List pagina(int page){
		ArrayList data;
		int base = page*numElementosPorPagina;
		int maximo = tamanoListaResultados;
		int resultado = ((base+numElementosPorPagina) <= maximo )? (base+numElementosPorPagina) : maximo ;
		buscarRegistros(base,resultado);
		data = (ArrayList)armarModel(listaResultados);
		return data;
	}
	
	public abstract void buscarRegistros(int startIndex,int endIndex);
	public abstract List armarModel(List lista);
	
	public List first(){
		return pagina(0);
	};
	
	public List last() {
		if (tamanoListaResultados%numElementosPorPagina == 0)
			return pagina((tamanoListaResultados / numElementosPorPagina) - 1);
		
		return pagina(tamanoListaResultados/numElementosPorPagina);
	};
	
	public List previous(int page) {
		page--;
		return pagina(page);
	}

	public List next(int page) {
		page++;
		return pagina(page);
	}

	public abstract List getListaResultados();
	
	public abstract int getResultListSize();
	public abstract void setResultListSize(int tamanoLista);
	public abstract void setQueryBuilded(Map queryBuilded);
	public abstract void setTxtParametros(String parametro1, String parametro2, String parametro3);
	public void setTxtParametros(Map<String, Object> mapaParametros){
		this.mapaParametros = mapaParametros;
	}
	
	public String getNombrePanel(){
		return nombrePanel;
	}
	
}
