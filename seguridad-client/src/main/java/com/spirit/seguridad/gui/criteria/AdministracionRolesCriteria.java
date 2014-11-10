package com.spirit.seguridad.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.seguridad.entity.RolIf;

public class AdministracionRolesCriteria extends Criteria{

	Map queryBuilded;
	
	public AdministracionRolesCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Administración de Roles";
	}
	
	public AdministracionRolesCriteria( List listaResultados ){
		this.listaResultados =listaResultados;
	}
	
	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Codigo");
		headers.add("Nombre");
		headers.add("Empresa");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			RolIf bean = (RolIf) it.next();

			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());
			try {
				if(bean.getEmpresaId() != null){
					EmpresaIf empresa = SessionServiceLocator.getEmpresaSessionService().getEmpresa(bean.getEmpresaId());
					fila.add(empresa.getNombre());
				}else{
					fila.add("");
				}				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}

			data.add(fila);
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
			listaResultados = (ArrayList) SessionServiceLocator.getRolSessionService()
				.findRolByQuery(startIndex,endIndex,queryBuilded);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try{
			tamanoListaResultados = SessionServiceLocator.getRolSessionService()
				.getRolListSize(queryBuilded);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
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
