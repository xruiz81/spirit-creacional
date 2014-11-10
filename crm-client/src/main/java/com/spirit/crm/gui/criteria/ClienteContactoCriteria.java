package com.spirit.crm.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModel;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteContactoIf;
import com.spirit.crm.entity.TipoContactoIf;
import com.spirit.exception.GenericBusinessException;

public class ClienteContactoCriteria extends Criteria {

	Long idFiltroBusqueda;
	String txtNombre;
	Map queryBuilded;

	public ClienteContactoCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Operador de Negocio";
	}

	public ClienteContactoCriteria(String nombrePanel, Long idFiltro) {
		this.nombrePanel = nombrePanel;
		this.idFiltroBusqueda = idFiltro;
	}

	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Nombre");
		headers.add("Tipo Contacto");
		return headers;
	}

	public List getModel() {
		ArrayList data = (ArrayList) armarModel(listaResultados);
		return data;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();
		try {
			for (int i = 0; i < lista.size(); i++) {
				ArrayList fila = new ArrayList();
				ClienteContactoIf bean = (ClienteContactoIf) lista.get(i);
				TipoContactoIf tipoContacto = SessionServiceLocator.getTipoContactoSessionService().getTipoContacto(bean.getTipocontactoId());
				fila.add(bean.getNombre());
				fila.add(tipoContacto.getNombre());
				data.add(fila);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return data;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public void setTxtParametros(String parametro1, String parametro2, String parametro3) {
		this.txtNombre = parametro1;
		queryBuilded = buildQuery();
	}

	public SpiritModel getModelForm() {
		return null;
	}

	// Me devuele un map segun el campo que mando a buscar
	private Map buildQuery() {
		Map aMap = new HashMap();
		if (txtNombre != null && !("".equals(txtNombre))) {
			aMap.put("nombre", txtNombre + "%");
		} else
			aMap.put("nombre", "%");
		return aMap;
	}

	public int getResultListSize() {
		// Obtengo eltamaño de los regsitros devueltos en la busqueda
		try {
			if (idFiltroBusqueda == 0L) {
				if (queryBuilded.isEmpty())
					tamanoListaResultados = SessionServiceLocator.getClienteContactoSessionService()
							.getClienteContactoListSize();
				else
					tamanoListaResultados = SessionServiceLocator.getClienteContactoSessionService()
							.getClienteContactoListSize(queryBuilded);
			} else {
				if (queryBuilded.isEmpty())
					tamanoListaResultados = SessionServiceLocator.getClienteContactoSessionService()
							.getClienteContactoListSize(idFiltroBusqueda);
				else
					tamanoListaResultados = SessionServiceLocator.getClienteContactoSessionService()
							.getClienteContactoListSize(queryBuilded,
									idFiltroBusqueda);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			if (idFiltroBusqueda == 0L) {
				if (queryBuilded.isEmpty()) {
					// dataList = modelForm.find(startIndex, endIndex);
					listaResultados = (ArrayList) SessionServiceLocator.getClienteContactoSessionService()
							.getClienteContactoList(startIndex, endIndex);
				} else {
					// dataList = modelForm.find(startIndex, endIndex,
					// buildQuery());
					listaResultados = (ArrayList) SessionServiceLocator.getClienteContactoSessionService()
							.getClienteContactoList(startIndex, endIndex,
									queryBuilded);
				}
			} else {
				if (queryBuilded.isEmpty()) {
					// dataList = modelForm.find(startIndex,
					// endIndex,idFiltroBusqueda);
					listaResultados = (ArrayList) SessionServiceLocator.getClienteContactoSessionService()
							.getClienteContactoList(startIndex, endIndex,
									idFiltroBusqueda);
				} else {
					// dataList = modelForm.find(startIndex, endIndex,
					// buildQuery(),idFiltroBusqueda);
					listaResultados = (ArrayList) SessionServiceLocator.getClienteContactoSessionService()
							.getClienteContactoList(startIndex, endIndex,
									queryBuilded, idFiltroBusqueda);
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
}
