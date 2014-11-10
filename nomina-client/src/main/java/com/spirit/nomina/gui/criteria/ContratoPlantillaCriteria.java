package com.spirit.nomina.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModel;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.ContratoPlantillaIf;
import com.spirit.nomina.entity.TipoContratoIf;

public class ContratoPlantillaCriteria extends Criteria {

	Map queryBuilded;
	String nombrePanel = null;
	
	public ContratoPlantillaCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Plantilla de Contrato";
	}
	
	public ContratoPlantillaCriteria(String nombrePanel){
		this.nombrePanel = nombrePanel;
	}
	
	public ContratoPlantillaCriteria(List listaResultados){
		this.listaResultados = listaResultados;
	}

	public List getHeaders() {
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Código");
		headers.add("Tipo de Contrato");
		headers.add("Observación");
		return headers;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = listaResultados.iterator();
        while (it.hasNext()) {
        	try {
        		ArrayList fila = new ArrayList();
    			ContratoPlantillaIf bean = (ContratoPlantillaIf) it.next();
    			TipoContratoIf tipoContrato = SessionServiceLocator.getTipoContratoSessionService()
    				.getTipoContrato(bean.getTipoContratoId());
    			fila.add(bean.getCodigo());
    			fila.add(tipoContrato.getNombre());
    			fila.add(bean.getObservacion()!=null?bean.getObservacion():"");
    			
    			data.add(fila);
			
        	} catch (GenericBusinessException e) {
        		SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public void buscarRegistros(int startIndex,int endIndex){
		try{
			listaResultados = (ArrayList) SessionServiceLocator.getContratoPlantillaSessionService()
				.findContratoPlantillaByQuery(startIndex,endIndex,queryBuilded);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public int getResultListSize() {
		try{
			tamanoListaResultados = SessionServiceLocator.getContratoPlantillaSessionService()
				.getContratoPlantillaListSize(queryBuilded);
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
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
}
