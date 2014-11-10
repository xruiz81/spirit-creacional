package com.spirit.contabilidad.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.UsuarioIf;

public class UsuarioCuentaCriteria extends Criteria{

	Map queryBuilded;
	
	public UsuarioCuentaCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "";
	}
	
	public UsuarioCuentaCriteria( List listaResultados ){
		this.listaResultados =listaResultados;
	}
	
	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Usuario");
		headers.add("Empleado");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();
		try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				UsuarioIf bean = (UsuarioIf) it.next();
				fila.add(bean.getUsuario());
				if (bean.getEmpleadoId() != null) {
					EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(bean.getEmpleadoId());
					fila.add(empleado.getApellidos() + " " + empleado.getNombres());
				} else if (bean.getTipousuario().equals("A"))
					fila.add("ADMINISTRADOR");
				else if (bean.getTipousuario().equals("S"))
					fila.add("SUPERUSUARIO");
				data.add(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
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
			listaResultados = (ArrayList)SessionServiceLocator.getUsuarioSessionService()
							.getUsuarioList(startIndex,endIndex,queryBuilded);
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
