package com.spirit.contabilidad.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.exception.GenericBusinessException;

public class CuentaCriteria extends Criteria{

	Long idFiltroBusqueda;
	Long idUsuario;
	String estado;
	Map cuentasMap = new HashMap();
	String esImputable = "";
	String txtCodigo,txtNombre,txtNombreCorto;
	Map queryBuilded;
	
	public CuentaCriteria(){
		cuentasMap = mapearCuentas();
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "";
	}
	
	public CuentaCriteria( List listaResultados ) {
		this.listaResultados =listaResultados;
		cuentasMap = mapearCuentas();
	}
	
	public CuentaCriteria(String nombre, String imputable, Long idFiltroBusqueda, Long idUsuario, String estado) {
		//modelForm = model;
		nombrePanel = nombre;
		esImputable = imputable;
		this.idFiltroBusqueda = idFiltroBusqueda;
		this.idUsuario = idUsuario;
		this.estado = estado;
		cuentasMap = mapearCuentas();
	}
	
	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Nombre");
		headers.add("Cuenta Padre");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			CuentaIf bean = (CuentaIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());
			if (bean.getPadreId()!=null) {
				CuentaIf padre = (CuentaIf) cuentasMap.get(bean.getPadreId());
				fila.add(padre.getNombre());
			} else
				fila.add("");
			data.add(fila);
		}
		return data;
	}
	
	private Map mapearCuentas() {
		Map cuentasMap = new HashMap();
		try {
			Iterator it = SessionServiceLocator.getCuentaSessionService().getCuentaList().iterator();
			while (it.hasNext()) {
				CuentaIf cuenta = (CuentaIf) it.next();
				cuentasMap.put(cuenta.getId(), cuenta);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al recuperar el listado de cuentas", SpiritAlert.ERROR);
		}
		return cuentasMap;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados =listaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	private Map buildQuery() {
		Map aMap  = new HashMap();
		
		if ( this.txtCodigo!=null && !("".equals(this.txtCodigo))) {
			aMap.put("codigo", this.txtCodigo+"%");
		}
		else
			aMap.put("codigo", "%");
		
		if ( this.txtNombre!=null && !("".equals(this.txtNombre))) {
			aMap.put("nombre", this.txtNombre+"%");
		}
		
		if ( this.txtNombreCorto!=null && !("".equals(this.txtNombreCorto))) {
			aMap.put("nombreCorto", this.txtNombreCorto + "%");
		}
		
		if (!esImputable.equals(""))
			aMap.put("imputable", esImputable);
		
		if (!estado.equals(""))
			aMap.put("estado", estado);
		
		return aMap;
	}
	
	public int getResultListSize(){
		try{
			//Obtengo eltamaño de los regsitros devueltos en la busqueda
			if(idFiltroBusqueda==null){
				//tamanoListaResultados = modelForm.getListSize(queryBuilded);
				tamanoListaResultados = SessionServiceLocator.getCuentaSessionService().getCuentaListSize(queryBuilded, idUsuario);
			}
			else{
				//tamanoListaResultados = modelForm.getListSize(queryBuilded,idFiltroBusqueda);
				queryBuilded.put("plancuentaId", idFiltroBusqueda);
				tamanoListaResultados = SessionServiceLocator.getCuentaSessionService().getCuentaListSize(queryBuilded, idUsuario);
			}
		}catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}
	
	public void buscarRegistros(int startIndex,int endIndex) {
		try{
			if (idFiltroBusqueda == null)
				listaResultados = (ArrayList) SessionServiceLocator.getCuentaSessionService().getCuentaList(startIndex, endIndex, queryBuilded, idUsuario);
			else {
				queryBuilded.put("plancuentaId", idFiltroBusqueda);
				listaResultados = (ArrayList) SessionServiceLocator.getCuentaSessionService().getCuentaList(startIndex, endIndex, queryBuilded, idUsuario);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void setTxtParametros(String parametro1, String parametro2, String parametro3) {
		this.txtCodigo = parametro1;
		this.txtNombre = parametro2;
		this.txtNombreCorto = parametro3;
		queryBuilded = buildQuery() ;
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
}
