package com.spirit.general.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.compras.entity.GastoIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.enums.TipoGasto;

public class GastoCriteria extends Criteria{
	
	Map queryBuilded;
	
	public GastoCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Gasto";
	}
	
	public GastoCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}
	
	@Override
	public List getHeadersString() {
		List<String> headers = new ArrayList<String>();
		headers.add("Código");
		headers.add("Nombre");
		headers.add("Tipo");
		return headers;
	}
	
	public List getHeaders() {
		List headers = new ArrayList();
		headers.add("Código");
		headers.add("Nombre");
		Object[] tipos = new Object[TipoGasto.values().length+1];
		TipoGasto[] tps = TipoGasto.values();
		tipos[0] = null;
		for (int i = 1; i < tipos.length ; i++){
			tipos[i] = tps[i-1];
		}
		headers.add(new Object[]{
				"Tipo",
				tipos});
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			GastoIf bean = (GastoIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());
			fila.add( bean.getTipo()!=null ? 
				TipoGasto.getTipoGastoByLetra(bean.getTipo()):"" );
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
		try {
			listaResultados = (ArrayList) 
				SessionServiceLocator.getGastoSessionService().getGastoByQueryList(startIndex, endIndex,queryBuilded);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try {
			tamanoListaResultados = SessionServiceLocator.getGastoSessionService().getGastoListSizeByQuery(queryBuilded);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
		return tamanoListaResultados;
	}

	@Override
	public void setTxtParametros(Map<String, Object> mapaParametros) {
		mapaParametros.put("empresaId", Parametros.getIdEmpresa());
		String p1 = (String) mapaParametros.remove("parametro1");
		if ( p1 == null || p1.trim().equals("") )
			mapaParametros.put("codigo", "%");
		
		String p2 = (String) mapaParametros.remove("parametro2");
		if ( p2 == null || p2.trim().equals("") )
			mapaParametros.put("nombre", "%");
		
		Object p3 = mapaParametros.remove("parametro3");
		if ( p3 == null )
			mapaParametros.put("tipo", null);
		else {
			TipoGasto tg = (TipoGasto) p3;
			mapaParametros.put("tipo", tg.getLetra() );
		}
		this.queryBuilded = mapaParametros;
	}
	
	public void setTxtParametros(String txtCodigo, String txtDescripcion,String tipoGasto) {
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}	
	
 
}
