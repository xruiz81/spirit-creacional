package com.spirit.crm.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.entity.TipoNegocioIf;
import com.spirit.crm.session.ClienteSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.servicelocator.ServiceLocator;

public class AdministracionClienteCriteria extends Criteria {

	Map queryBuilded;
	Long tipoClienteId = 0L;

	public AdministracionClienteCriteria() {
	}

	public void setNombrePanel() {
		this.nombrePanel = "";
	}
	
	public AdministracionClienteCriteria(Long tipoClienteId) {
		this.tipoClienteId = tipoClienteId;
	}

	public AdministracionClienteCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Nombre Legal");
		headers.add("Corporación");
		headers.add("Identificación");
		headers.add("Tipo de Operador");
		headers.add("Tipo de Negocio");
		return headers;
	}

	public List getModel() {
		ArrayList data = (ArrayList) armarModel(listaResultados);
		return data;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();
		Iterator it = listaResultados.iterator();

		try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				ClienteIf bean = (ClienteIf) it.next();

				fila.add(bean.getNombreLegal() != null ? bean.getNombreLegal() : "");
				
				if(bean.getCorporacionId() != null){
					CorporacionIf corporacion = SessionServiceLocator
						.getCorporacionSessionService().getCorporacion(bean.getCorporacionId());
					fila.add(corporacion.getNombre());
				} else
					fila.add("");		
				
				fila.add(bean.getIdentificacion() != null ? bean.getIdentificacion() : "");
				TipoClienteIf tipoCliente = SessionServiceLocator
					.getTipoClienteSessionService().getTipoCliente(bean.getTipoclienteId());
				fila.add(tipoCliente.getNombre());
				if (bean.getTiponegocioId() != null) {
					TipoNegocioIf tipoNegocio = SessionServiceLocator
						.getTipoNegocioSessionService().getTipoNegocio(bean.getTiponegocioId());
					fila.add(tipoNegocio.getNombre());
				} else
					fila.add("");
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
		this.listaResultados = listaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			listaResultados = (ArrayList) getClienteSessionService().findClienteByFilteredQuery(startIndex, endIndex,queryBuilded, tipoClienteId, Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		return this.tamanoListaResultados;
	}

	public void setTxtParametros(String txtCodigo, String txtDescripcion,
			String parametro3) {
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}

	public static ClienteSessionService getClienteSessionService() {
		try {
			return (ClienteSessionService) ServiceLocator
					.getService(ServiceLocator.CLIENTESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

}
