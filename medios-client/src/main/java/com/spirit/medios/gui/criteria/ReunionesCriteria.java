package com.spirit.medios.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.util.GeneralUtil;
import com.spirit.medios.entity.ReunionIf;
import com.spirit.util.Utilitarios;

public class ReunionesCriteria extends MediosCriteriaBase{

	Map queryBuilded;
	
	public ReunionesCriteria(String nombre){
		nombrePanel = nombre;
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Movimientos";
	}
	
	public ReunionesCriteria( List listaResultados ){
		this.listaResultados =listaResultados;
	}
	
	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("F. Creación");
		headers.add("Cliente");
		headers.add("Descripción");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			ReunionIf bean = (ReunionIf) it.next();
			try{
				fila.add(Utilitarios.getFechaCortaUppercase(bean.getFecha()));
				if (bean.getClienteId() != null) {
					ClienteIf cliente = GeneralUtil.verificarMapaCliente(mapaCliente,bean.getClienteId());
					fila.add(cliente.getNombreLegal());
				} else
					fila.add(bean.getProspectocliente());
				fila.add(bean.getDescripcion());
	
				data.add(fila);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
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
			listaResultados = (ArrayList) SessionServiceLocator.getReunionSessionService().findReunionByQuery(
					startIndex,endIndex,queryBuilded, Parametros.getIdEmpresa());
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		return this.tamanoListaResultados;
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
