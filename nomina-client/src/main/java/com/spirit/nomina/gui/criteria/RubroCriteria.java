package com.spirit.nomina.gui.criteria;

import java.sql.Date;
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
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.handler.TipoRubro;

public class RubroCriteria extends Criteria {

	private String codigo = null;
	private String nombre = null;
	private TipoRubro tipoRubro = null;
	
	Map queryBuilded;
	Date fechaActual = null;
	
	/*Object[] tipoRubros = new Object[]{
			TipoRubro.EVENTUAL,//NOMBRE_TIPO_RUBRO_EVENTUAL,
			TipoRubro.DESCUENTO,//NOMBRE_TIPO_RUBRO_DESCUENTO,
			TipoRubro.SUELDO,//NOMBRE_TIPO_RUBRO_SUELDO,
			TipoRubro.BENEFICIO,//NOMBRE_TIPO_RUBRO_BENEFICIO,
			TipoRubro.QUINCENA,//NOMBRE_TIPO_RUBRO_QUINCENA,
			TipoRubro.ANTICIPO,//NOMBRE_TIPO_RUBRO_ANTICIPO,
			TipoRubro.ADELANTO
		};*/
	
	Object[] tipoRubros = TipoRubro.values();
	
	/*public RubroCriteria(){
	}*/
	
	public RubroCriteria(TipoRubro tipoRubroInicial){
		this.tipoRubro = tipoRubroInicial;
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Rubros";
	}
	
	/*public RubroCriteria(List listaResultados){
		this.listaResultados = listaResultados;
	}*/

	public List getHeaders() {
		ArrayList<Object> headers = new ArrayList<Object>();
		headers.add("Código");
		headers.add("Nombre");
		headers.add(
			new Object[]{
				"Tipo de Rubro",
				tipoRubros,
				this.tipoRubro
			}
		);
		return headers;
	}
	
	@Override
	public List getHeadersString() {
		ArrayList<Object> headers = new ArrayList<Object>();
		headers.add("Código");
		headers.add("Nombre");
		headers.add("Tipo de Rubro");
		return headers;
	}
	
	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = listaResultados.iterator();
		while (it.hasNext()) {
			ArrayList<String> fila = new ArrayList<String>();
			RubroIf bean = (RubroIf) it.next();

			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());
			String letraTipoRubro = bean.getTipoRubro();
			try {
				TipoRubro tr = TipoRubro.getTipoRubroByLetra(letraTipoRubro);
				fila.add( tr.toString() );
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				fila.add("");
			}
			data.add(fila);
		}
		return data;
	}
	
	public void buscarRegistros(int startIndex,int endIndex){
		try{
			listaResultados = (ArrayList) SessionServiceLocator.getRubroSessionService()
					.findRubroByQuery(startIndex,endIndex,queryBuilded);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public int getResultListSize() {
		try{
			tamanoListaResultados = SessionServiceLocator.getRubroSessionService()
				.getRubroListSize(queryBuilded);
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
		if ( tipoRubro != null  ){
			try {
				queryBuilded.put("tipoRubro", this.tipoRubro.getLetra());
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
			}
		}
	}
	
	public void setTxtParametros(String parametro1, String parametro2, String parametro3) {
		
	}
	
	@Override
	public void setTxtParametros(Map<String, Object> mapaParametros) {
		this.codigo = (String)mapaParametros.get("parametro1");
		this.nombre = (String)mapaParametros.get("parametro2");
		this.tipoRubro = (TipoRubro)mapaParametros.get("parametro3");
		buildQuery();
	}
	
	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
	
}
