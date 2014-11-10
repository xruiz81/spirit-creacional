package com.spirit.general.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModel;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;

public class ClienteCriteria extends Criteria {

	Long idFiltroBusqueda;
	String codigoTipoCliente;
	String txtIdentificacion, txtNombreLegal, txtRazonSocial;
	Map queryBuilded;
	
	//ADD 26 JULIO
	Long idTipoProveedor = 0L;
	//END 26 JULIO

	public ClienteCriteria() {
	}

	public void setNombrePanel() {
		this.nombrePanel = "de Cliente";
	}
	
	public ClienteCriteria(String nombrePanel, Long idFiltro, String tipoCliente) {
		this.nombrePanel = nombrePanel;
		this.idFiltroBusqueda = idFiltro;
		this.codigoTipoCliente = tipoCliente;
	}
	
	public ClienteCriteria(String nombrePanel, Long idFiltro, String tipoCliente, Long tipoProveedorId) {
		this.nombrePanel = nombrePanel;
		this.idFiltroBusqueda = idFiltro;
		this.codigoTipoCliente = tipoCliente;
		this.idTipoProveedor = tipoProveedorId;
	}

	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Identificacion");
		headers.add("Nombre Legal");
		headers.add("Razon Social");
		return headers;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();

		for (int i = 0; i < lista.size(); i++) {
			ArrayList fila = new ArrayList();
			ClienteIf bean = (ClienteIf) lista.get(i);
			fila.add(bean.getIdentificacion() != null ? bean.getIdentificacion() : "");
			fila.add(bean.getNombreLegal() != null ? bean.getNombreLegal() : "");
			fila.add(bean.getRazonSocial() != null ? bean.getRazonSocial() : "");
			data.add(fila);
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
		this.txtIdentificacion = parametro1;
		this.txtNombreLegal = parametro2;
		this.txtRazonSocial = parametro3;
		queryBuilded = buildQuery();
	}

	public SpiritModel getModelForm() {
		return null;
	}

	// Me devuele un map segun el campo que mando a buscar
	private Map buildQuery() {
		Map aMap = new HashMap();
		if (txtIdentificacion != null && !("".equals(this.txtIdentificacion)))
			aMap.put("identificacion", this.txtIdentificacion + "%");
		if (txtNombreLegal != null && !("".equals(this.txtNombreLegal)))
			aMap.put("nombreLegal", this.txtNombreLegal + "%");
		if (txtRazonSocial != null && !("".equals(this.txtRazonSocial)))
			aMap.put("razonSocial", this.txtRazonSocial + "%");

		return aMap;
	}

	public int getResultListSize() {
		try {
			if (idFiltroBusqueda == 0L && idTipoProveedor == 0L) {
				if (queryBuilded.size() == 0 ) {
					tamanoListaResultados = SessionServiceLocator.getClienteSessionService().getClienteListSize(queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
				} else {
					tamanoListaResultados = SessionServiceLocator.getClienteSessionService().getClienteListSize(queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
				}
			}
			else if (idFiltroBusqueda == 0L && idTipoProveedor != 0L) {
				if (queryBuilded.size() == 0 ) {
					tamanoListaResultados = SessionServiceLocator.getClienteSessionService().getClienteByTipoProveedorListSize(queryBuilded, codigoTipoCliente, idTipoProveedor,Parametros.getIdEmpresa());
				} else {
					tamanoListaResultados = SessionServiceLocator.getClienteSessionService().getClienteByTipoProveedorListSize(queryBuilded, codigoTipoCliente, idTipoProveedor,Parametros.getIdEmpresa());
				}
			}
			else {//if (queryBuilded.size() != 0 && idTipoProveedor == 0L) {//ADD 26 JULIO
				if (queryBuilded.size() == 0) {
					Map filtroMap = new HashMap();
					filtroMap.put("corporacionId", idFiltroBusqueda);
					tamanoListaResultados = SessionServiceLocator.getClienteSessionService().getClienteListSize(filtroMap, codigoTipoCliente, Parametros.getIdEmpresa());
				} else {
					queryBuilded.put("corporacionId", idFiltroBusqueda);
					tamanoListaResultados = SessionServiceLocator.getClienteSessionService().getClienteListSize(queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
				}
			}//ADD 26 JULIO
			/*else {
				if (queryBuilded.size() == 0) {
					Map filtroMap = new HashMap();
					filtroMap.put("corporacionId", idFiltroBusqueda);
					filtroMap.put("tipoproveedorId", idTipoProveedor);
					tamanoListaResultados = SessionServiceLocator.getClienteSessionService().getClienteListSize(filtroMap, codigoTipoCliente, Parametros.getIdEmpresa());
				} else {
					queryBuilded.put("corporacionId", idFiltroBusqueda);
					queryBuilded.put("tipoproveedorId", idTipoProveedor);
					tamanoListaResultados = SessionServiceLocator.getClienteSessionService().getClienteListSize(queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
				}
			}*///END 26 JULIO	
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return 0;
		}
		return tamanoListaResultados;
	}
	
	
	/*COMENTED 27 JULIO
	 * public int getResultListSize() {
		try {
			if (idFiltroBusqueda == 0L && idTipoProveedor == 0L) {
				if (queryBuilded.size() == 0 ) {
					tamanoListaResultados = SessionServiceLocator.getClienteSessionService().getClienteListSize(queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
				} else {
					tamanoListaResultados = SessionServiceLocator.getClienteSessionService().getClienteListSize(queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
				}
			}else if (queryBuilded.size() != 0 && idTipoProveedor == 0L) {//ADD 26 JULIO
				if (queryBuilded.size() == 0) {
					Map filtroMap = new HashMap();
					filtroMap.put("corporacionId", idFiltroBusqueda);
					tamanoListaResultados = SessionServiceLocator.getClienteSessionService().getClienteListSize(filtroMap, codigoTipoCliente, Parametros.getIdEmpresa());
				} else {
					queryBuilded.put("corporacionId", idFiltroBusqueda);
					tamanoListaResultados = SessionServiceLocator.getClienteSessionService().getClienteListSize(queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
				}
			}//ADD 26 JULIO
			else {
				if (queryBuilded.size() == 0) {
					Map filtroMap = new HashMap();
					filtroMap.put("corporacionId", idFiltroBusqueda);
					filtroMap.put("tipoproveedorId", idTipoProveedor);
					tamanoListaResultados = SessionServiceLocator.getClienteSessionService().getClienteListSize(filtroMap, codigoTipoCliente, Parametros.getIdEmpresa());
				} else {
					queryBuilded.put("corporacionId", idFiltroBusqueda);
					queryBuilded.put("tipoproveedorId", idTipoProveedor);
					tamanoListaResultados = SessionServiceLocator.getClienteSessionService().getClienteListSize(queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
				}
			}//END 26 JULIO	
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return 0;
		}
		return tamanoListaResultados;
	}
	 * */

	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			if (idFiltroBusqueda == 0L && idTipoProveedor == 0L) {
				if (queryBuilded.size() == 0)
					listaResultados = (ArrayList) SessionServiceLocator.getClienteSessionService().getClienteList(startIndex, endIndex, queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
				else
					listaResultados = (ArrayList) SessionServiceLocator.getClienteSessionService().getClienteList(startIndex, endIndex, queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
			}
			else if (idFiltroBusqueda == 0L && idTipoProveedor != 0L) {
				if (queryBuilded.size() == 0)
					listaResultados = (ArrayList) SessionServiceLocator.getClienteSessionService().getClienteByTipoProveedorList(startIndex, endIndex, queryBuilded, codigoTipoCliente, idTipoProveedor, Parametros.getIdEmpresa());
				else
					listaResultados = (ArrayList) SessionServiceLocator.getClienteSessionService().getClienteByTipoProveedorList(startIndex, endIndex, queryBuilded, codigoTipoCliente, idTipoProveedor,Parametros.getIdEmpresa());
			}			
			else {//if (queryBuilded.size() != 0 && idTipoProveedor == 0L) {//ADD 26 JULIO
				if (queryBuilded.size() == 0) {
					Map filtroMap = new HashMap();
					filtroMap.put("corporacionId", idFiltroBusqueda);
					listaResultados = (ArrayList) SessionServiceLocator.getClienteSessionService().getClienteList(startIndex, endIndex, filtroMap, codigoTipoCliente, Parametros.getIdEmpresa());
				} else {
					queryBuilded.put("corporacionId", idFiltroBusqueda);
					listaResultados = (ArrayList) SessionServiceLocator.getClienteSessionService().getClienteList(startIndex, endIndex, queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
				}
			}/*else {//ADD 26 JULIO
				if (queryBuilded.size() == 0) {
					Map filtroMap = new HashMap();
					filtroMap.put("corporacionId", idFiltroBusqueda);
					filtroMap.put("tipoproveedorId", idTipoProveedor);
					listaResultados = (ArrayList) SessionServiceLocator.getClienteSessionService().getClienteList(startIndex, endIndex, filtroMap, codigoTipoCliente, Parametros.getIdEmpresa());
				} else {
					queryBuilded.put("corporacionId", idFiltroBusqueda);
					queryBuilded.put("tipoproveedorId", idTipoProveedor);
					listaResultados = (ArrayList) SessionServiceLocator.getClienteSessionService().getClienteList(startIndex, endIndex, queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
				}
			}*///END 26 JULIO	
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	/*//COMENTED 27 JULIO
	 * public void buscarRegistros(int startIndex, int endIndex) {
		try {
			if (idFiltroBusqueda == 0L && idTipoProveedor == 0L) {
				if (queryBuilded.size() == 0)
					listaResultados = (ArrayList) SessionServiceLocator.getClienteSessionService().getClienteList(startIndex, endIndex, queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
				else
					listaResultados = (ArrayList) SessionServiceLocator.getClienteSessionService().getClienteList(startIndex, endIndex, queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
			}
			else if (queryBuilded.size() != 0 && idTipoProveedor == 0L) {//ADD 26 JULIO
				if (queryBuilded.size() == 0) {
					Map filtroMap = new HashMap();
					filtroMap.put("corporacionId", idFiltroBusqueda);
					listaResultados = (ArrayList) SessionServiceLocator.getClienteSessionService().getClienteList(startIndex, endIndex, filtroMap, codigoTipoCliente, Parametros.getIdEmpresa());
				} else {
					queryBuilded.put("corporacionId", idFiltroBusqueda);
					listaResultados = (ArrayList) SessionServiceLocator.getClienteSessionService().getClienteList(startIndex, endIndex, queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
				}
			}else {//ADD 26 JULIO
				if (queryBuilded.size() == 0) {
					Map filtroMap = new HashMap();
					filtroMap.put("corporacionId", idFiltroBusqueda);
					filtroMap.put("tipoproveedorId", idTipoProveedor);
					listaResultados = (ArrayList) SessionServiceLocator.getClienteSessionService().getClienteList(startIndex, endIndex, filtroMap, codigoTipoCliente, Parametros.getIdEmpresa());
				} else {
					queryBuilded.put("corporacionId", idFiltroBusqueda);
					queryBuilded.put("tipoproveedorId", idTipoProveedor);
					listaResultados = (ArrayList) SessionServiceLocator.getClienteSessionService().getClienteList(startIndex, endIndex, queryBuilded, codigoTipoCliente, Parametros.getIdEmpresa());
				}
			}//END 26 JULIO	
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	 * */

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}

	 
}
