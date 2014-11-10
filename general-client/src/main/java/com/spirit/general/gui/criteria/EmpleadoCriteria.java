package com.spirit.general.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.util.GeneralUtil;

public class EmpleadoCriteria extends Criteria {

	Long idFiltroBusqueda = 0L;
	String txtCodigo, txtNombres, txtApellidos;
	Map queryBuilded;
	Map<Long,TipoIdentificacionIf> mapaTipoIdentificacion = new HashMap<Long, TipoIdentificacionIf>();

	public EmpleadoCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Empleados";
	}

	public EmpleadoCriteria(String nombre, Long idFiltro) {
		nombrePanel = nombre;
		idFiltroBusqueda = idFiltro;
	}

	public EmpleadoCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Nombres");
		headers.add("Apellidos");
		headers.add("Tipo de Identificación");
		headers.add("Identificación");
		return headers;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();
		Iterator it = listaResultados.iterator();

		try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				EmpleadoIf bean = (EmpleadoIf) it.next();

				fila.add(bean.getCodigo());
				fila.add(bean.getNombres());
				fila.add(bean.getApellidos());
				TipoIdentificacionIf tipoIdentificacion = GeneralUtil.verificarMapaTipoIdentificacion(
						mapaTipoIdentificacion, bean.getTipoidentificacionId() ); 
				fila.add(tipoIdentificacion.getNombre());
				fila.add(bean.getIdentificacion());
				data.add(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
		return data;
	}

	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			if (idFiltroBusqueda == 0L) {
				if (queryBuilded.isEmpty()) {
					// dataList = modelForm.find(startIndex, endIndex);
					listaResultados = (ArrayList) SessionServiceLocator.getEmpleadoSessionService().getEmpleadoList(startIndex, endIndex, Parametros.getIdEmpresa());
				} else {
					// dataList = modelForm.find(startIndex, endIndex,
					// queryBuilded);
					listaResultados = (ArrayList) SessionServiceLocator.getEmpleadoSessionService().getEmpleadoList(startIndex, endIndex, queryBuilded, Parametros.getIdEmpresa());
				}
			} else {
				if (queryBuilded.isEmpty()) {
					// dataList = modelForm.find(startIndex,
					// endIndex,idFiltroBusqueda);
					listaResultados = (ArrayList) SessionServiceLocator.getEmpleadoSessionService().getEmpleadoList(startIndex, endIndex, idFiltroBusqueda);
				} else {
					// dataList = modelForm.find(startIndex,
					// endIndex,queryBuilded,idFiltroBusqueda);
					listaResultados = (ArrayList) SessionServiceLocator.getEmpleadoSessionService().getEmpleadoList(startIndex, endIndex, queryBuilded, idFiltroBusqueda);
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try {
			if (idFiltroBusqueda == 0L) {
				if (queryBuilded.size() == 0) {
					// listaSize = modelForm.getListSize();
					tamanoListaResultados = SessionServiceLocator.getEmpleadoSessionService().getEmpleadoListSize();
				} else {
					// listaSize = modelForm.getListSize(queryBuilded);
					tamanoListaResultados = SessionServiceLocator.getEmpleadoSessionService().getEmpleadoListSize(queryBuilded);
				}
			} else {
				if (queryBuilded.size() == 0) {
					// listaSize = modelForm.getListSize(idFiltroBusqueda);
					tamanoListaResultados = SessionServiceLocator.getEmpleadoSessionService().getEmpleadoListSize(idFiltroBusqueda);
				} else {
					// listaSize =
					// modelForm.getListSize(queryBuilded,idFiltroBusqueda);
					tamanoListaResultados = SessionServiceLocator.getEmpleadoSessionService().getEmpleadoListSize(queryBuilded, idFiltroBusqueda);
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return 0;
		}
		return tamanoListaResultados;
	}

	private Map buildQuery() {
		Map aMap = new HashMap();
		if (txtCodigo != null && !("".equals(txtCodigo)))
			aMap.put("codigo", txtCodigo.toUpperCase() + "%");
		else
			aMap.put("codigo", "%");
		if (txtNombres != null && !("".equals(txtNombres)))
			aMap.put("nombres", txtNombres.toUpperCase() + "%");
		if (txtApellidos != null && !("".equals(txtApellidos)))
			aMap.put("apellidos", txtApellidos.toUpperCase() + "%");
		return aMap;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public void setTxtParametros(String parametro1, String parametro2,
			String parametro3) {
		this.txtCodigo = parametro1;
		this.txtNombres = parametro2;
		this.txtApellidos = parametro3;
		this.queryBuilded = buildQuery();
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
 
}
