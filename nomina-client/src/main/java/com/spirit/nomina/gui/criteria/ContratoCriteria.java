package com.spirit.nomina.gui.criteria;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.handler.EstadoContrato;

public class ContratoCriteria extends Criteria {

	Map queryBuilded;
	Date fechaActual = null;
	String nombrePanel = null;
	
	public ContratoCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Contratos";
	}
	
	public ContratoCriteria(String nombrePanel){
		this.nombrePanel = nombrePanel;
	}
	
	public ContratoCriteria(List listaResultados){
		this.listaResultados = listaResultados;
	}

	public List getHeaders() {
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Código");
		headers.add("Empleado");
		headers.add("Tipo de Contrato");
		headers.add("Estado");
		return headers;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = listaResultados.iterator();
        while (it.hasNext()) {
        	try {
        		ArrayList fila = new ArrayList();
    			ContratoIf bean = (ContratoIf) it.next();
    			TipoContratoIf tipoContrato = SessionServiceLocator.getTipoContratoSessionService()
    				.getTipoContrato(bean.getTipocontratoId());
    						
    			fila.add(bean.getCodigo());
    			EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService()
    				.getEmpleado(bean.getEmpleadoId());
    			fila.add(empleado.getNombres() + " " + empleado.getApellidos());
    			fila.add(tipoContrato);
    			String estado = bean.getEstado();
    			fila.add(EstadoContrato.getEstadoContratoByLetra(estado));
    			
    			data.add(fila);
			
        	} catch (GenericBusinessException e) {
        		SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public void buscarRegistros(int startIndex,int endIndex){
		try{
			/*if (fechaActual != null)
				listaResultados = (ArrayList) getContratoSessionService()
					.findContratoByQueryByFechaActual(startIndex,endIndex,queryBuilded,fechaActual);
			else*/
				listaResultados = (ArrayList) SessionServiceLocator.getContratoSessionService()
					.findContratoByQuery(startIndex,endIndex,queryBuilded);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public int getResultListSize() {
		try{
			/*if (fechaActual != null)
				tamanoListaResultados = getContratoSessionService()
					.getContratoListSizeByQueryByFechaActual(queryBuilded,fechaActual);
			else*/
				tamanoListaResultados = SessionServiceLocator.getContratoSessionService()
					.getContratoListSize(queryBuilded);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public void setTxtParametros(String parametro1, String parametro2, String parametro3) {
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
}
