package com.spirit.cartera.gui.criteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.util.GeneralUtil;
import com.spirit.util.Utilitarios;

public class NotaCreditoCriteria extends CarteraCriteriaBase {

	Map queryBuilded;
	
	public NotaCreditoCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Compras";
	}
	
	public NotaCreditoCriteria( List listaResultados ) {
		this.listaResultados =listaResultados;
	}
	
	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Proveedor");
		headers.add("Fecha");
		headers.add("Preimpreso");
		headers.add("Estado");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				NotaCreditoIf bean = (NotaCreditoIf) it.next();

				fila.add(bean.getCodigo());
				fila.add(GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina,bean.getOperadorNegocioId()));
				fila.add(Utilitarios.getFechaCortaUppercase((Date)bean.getFechaEmision()));
				fila.add(bean.getPreimpreso());
				String estado = bean.getEstado(); 
				fila.add(estado.equals("A")?"ACTIVA":"INACTIVA");
				data.add(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
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
			listaResultados = (ArrayList) SessionServiceLocator.getNotaCreditoSessionService().getNotaCreditoByQueryList(startIndex,endIndex,queryBuilded,Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
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
