package com.spirit.general.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.UsuarioIf;

public class UsuarioCriteria extends Criteria {
	
	String nombrePanel = "de Usuarios";
	
	private static final String NOMBRE_TIPO_USUARIO = "USUARIO";
	private static final String TIPO_USUARIO = NOMBRE_TIPO_USUARIO.substring(0,1);
	private static final String NOMBRE_TIPO_ADMINISTRADOR = "ADMINISTRADOR";
	private static final String TIPO_ADMINISTRADOR = NOMBRE_TIPO_ADMINISTRADOR.substring(0,1);
	private static final String NOMBRE_TIPO_SUPER = "SUPERUSUARIO";
	private static final String TIPO_SUPER = NOMBRE_TIPO_SUPER.substring(0,1);
	Map queryBuilded;

	public UsuarioCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Usuarios";
	}
	
	public UsuarioCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Empleado");
		headers.add("Usuario");
		headers.add("Empresa");
		return headers;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();
		Iterator it = listaResultados.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			UsuarioIf bean = (UsuarioIf) it.next();
			
			try {
				if (bean.getEmpleadoId() != null) {
					EmpleadoIf empleadoBean = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(bean.getEmpleadoId());
					fila.add(empleadoBean.getNombres() + " " + empleadoBean.getApellidos());
				} else{
					fila.add("");
				}
					
				fila.add(bean.getUsuario());
				
				if(bean.getEmpresaId() != null){
					EmpresaIf empresa = SessionServiceLocator.getEmpresaSessionService().getEmpresa(bean.getEmpresaId());
					fila.add(empresa.getNombre());
				}else{
					fila.add("");
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}		
			
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

	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			listaResultados = (ArrayList) SessionServiceLocator.getUsuarioSessionService().getUsuarioList(startIndex,
							endIndex, queryBuilded);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try {
			tamanoListaResultados = SessionServiceLocator.getUsuarioSessionService()
					.getUsuarioListSize(queryBuilded);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	public void setTxtParametros(String txtCodigo, String txtDescripcion,
			String parametro3) {
	}
	
	public void setNombrePanel(String nombrePanel) {
		this.nombrePanel = nombrePanel;
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
 
}
