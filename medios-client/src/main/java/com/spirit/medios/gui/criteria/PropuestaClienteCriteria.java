package com.spirit.medios.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.util.GeneralUtil;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PropuestaIf;

public class PropuestaClienteCriteria extends MediosCriteriaBase{

	Map queryBuilded;
	Long clienteId = null;
	
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0, 1);
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0, 1);
	
	public PropuestaClienteCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Movimientos";
	}
	
	public PropuestaClienteCriteria(Long clienteId){
		this.clienteId = clienteId;
	}
	
	public PropuestaClienteCriteria( List listaResultados ){
		this.listaResultados =listaResultados;
	}
	
	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Cliente");
		headers.add("Estado");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			PropuestaIf bean = (PropuestaIf) it.next();
			try{
				fila.add(bean.getCodigo());
	           
				OrdenTrabajoIf orden = GeneralUtil.verificarMapaOrdenTrabajo(mapaOrdenTrabajo,bean.getOrdentrabajoId());
				ClienteOficinaIf clienteOficina = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina,orden.getClienteoficinaId());
				ClienteIf cliente = GeneralUtil.verificarMapaCliente(mapaCliente,clienteOficina.getClienteId());
				
				fila.add(cliente.getNombreLegal());
				
				if(ESTADO_ACTIVO.equals(bean.getEstado()))
	            	fila.add(NOMBRE_ESTADO_ACTIVO); 
				else if(ESTADO_INACTIVO.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_INACTIVO); 
				
				data.add(fila);
			}
			catch(GenericBusinessException e){
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
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
			if ( clienteId != null )
				listaResultados = (ArrayList)SessionServiceLocator.getPropuestaSessionService()
					.findPropuestaByQueryAndByIdCliente(startIndex,endIndex
						,queryBuilded,clienteId, Parametros.getIdEmpresa());
			else
				listaResultados = (ArrayList) SessionServiceLocator.getPropuestaSessionService()
				.findPropuestaByQuery(startIndex,endIndex,queryBuilded, Parametros.getIdEmpresa());	
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
