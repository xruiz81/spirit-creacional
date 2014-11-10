package com.spirit.nomina.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModel;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.util.EstadoRolPago;

public class TipoRolCriteria extends Criteria {

	Map queryBuilded;
	String nombrePanel = null;
	EstadoRolPago estadoRolPago = null;
	String codigo = null;
	String nombre = null;


	public TipoRolCriteria(){
	}
	public void setNombrePanel() {
		this.nombrePanel = "de Tipo Rol";
	}

	public TipoRolCriteria(String nombrePanel){
		this.nombrePanel = nombrePanel;
	}

	public List getHeaders() {
		ArrayList<Object> headers = new ArrayList<Object>();
		headers.add("Codigo");
		headers.add("Nombre");
		return headers;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = listaResultados.iterator();
		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			TipoRolIf bean = (TipoRolIf) it.next();

			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());

			data.add(fila);
		}
		return data;
	}
	
	public void buscarRegistros(int startIndex,int endIndex){
		try{
			listaResultados = (ArrayList) SessionServiceLocator.getTipoRolSessionService()
				.findTipoRolByQuery(startIndex,endIndex,queryBuilded);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try{
			tamanoListaResultados = SessionServiceLocator.getTipoRolSessionService().getTipoRolListSize(queryBuilded);
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
		this.codigo = parametro1;
		this.nombre = parametro2;
		buildQuery();
	}

	private void buildQuery(){
		queryBuilded = new HashMap<String, Object>();
		if ( codigo != null &&!"".equals(codigo) ){
			queryBuilded.put("codigo", codigo);
		}
		if ( nombre != null &&!"".equals(nombre) ){
			queryBuilded.put("nombre", nombre);
		}
		queryBuilded.put("empresaId", Parametros.getIdEmpresa());
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}

}
