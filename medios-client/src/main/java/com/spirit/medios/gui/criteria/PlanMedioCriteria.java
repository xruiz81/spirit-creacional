package com.spirit.medios.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.PlanMedioIf;

public class PlanMedioCriteria extends Criteria{

	Map queryBuilded;
	
	Long clienteOficinaId = null;
	
	private static final String NOMBRE_ESTADO_EN_PROCESO = "EN PROCESO";
	private static final String NOMBRE_ESTADO_PENDIENTE  = "PENDIENTE";
	private static final String NOMBRE_ESTADO_APROBADO   = "APROBADO";
	private static final String NOMBRE_ESTADO_FACTURADO  = "FACTURADO";
	private static final String NOMBRE_ESTADO_HISTORICO  = "HISTORICO";
	private static final String NOMBRE_ESTADO_PEDIDO 	 = "PEDIDO";
	private static final String NOMBRE_ESTADO_PREPAGADO  = "PREPAGADO";
	private static final String ESTADO_EN_PROCESO = "N";
	private static final String ESTADO_PENDIENTE  = "P";
	private static final String ESTADO_APROBADO   = "A";
	private static final String ESTADO_FACTURADO  = "F";
	private static final String ESTADO_HISTORICO  = "H";
	private static final String ESTADO_PEDIDO 	  = "D";
	private static final String ESTADO_PREPAGADO  = "R";
	private static String[] estados = null;
	private boolean busquedaReferencia = false;
	
	public PlanMedioCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Planes de Medios";
	}
	
	public PlanMedioCriteria(Long clienteOficinaId, String... estados){
		this.clienteOficinaId = clienteOficinaId;
		this.estados = new String[estados.length];
		this.estados = estados;
	}
	
	public PlanMedioCriteria(Long proveedorId, boolean busquedaReferencia, String... estados){
		this.clienteOficinaId = proveedorId;
		this.busquedaReferencia = busquedaReferencia;
		this.estados = new String[estados.length];
		this.estados = estados;
	}
	
	public PlanMedioCriteria( List listaResultados ){
		this.listaResultados = listaResultados;
	}
	
	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Concepto");
		//headers.add("Tipo de Proveedor");
		headers.add("Estado");
		headers.add("Revisión");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();
		
		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			PlanMedioIf bean = (PlanMedioIf) it.next();

			fila.add(bean.getCodigo());
			fila.add(bean.getConcepto());
			
			if (ESTADO_EN_PROCESO.equals(bean.getEstado()))
				fila.add(NOMBRE_ESTADO_EN_PROCESO);
			else if (ESTADO_PENDIENTE.equals(bean.getEstado()))
				fila.add(NOMBRE_ESTADO_PENDIENTE);
			else if (ESTADO_APROBADO.equals(bean.getEstado()))
				fila.add(NOMBRE_ESTADO_APROBADO);
			else if (ESTADO_FACTURADO.equals(bean.getEstado()))
				fila.add(NOMBRE_ESTADO_FACTURADO);
			else if (ESTADO_HISTORICO.equals(bean.getEstado()))
				fila.add(NOMBRE_ESTADO_HISTORICO);
			else if (ESTADO_PEDIDO.equals(bean.getEstado()))
				fila.add(NOMBRE_ESTADO_PEDIDO);
			else if (ESTADO_PREPAGADO.equals(bean.getEstado()))
				fila.add(NOMBRE_ESTADO_PREPAGADO);
			
			fila.add(bean.getRevision());
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
			if(busquedaReferencia){
				listaResultados = (ArrayList) SessionServiceLocator.getPlanMedioSessionService()
						.findPlanMedioByQueryByProveedorIdByEmpresaIdAndByEstados(startIndex,endIndex,
								queryBuilded, clienteOficinaId, Parametros.getIdEmpresa(),this.estados);
			}
			else if (clienteOficinaId!=null){
				listaResultados = (ArrayList) SessionServiceLocator.getPlanMedioSessionService()
				.findPlanMedioByQueryAndByIdClienteOficina(startIndex,endIndex,
						queryBuilded, clienteOficinaId, Parametros.getIdEmpresa(),this.estados);
			}
			else{
				//GIOMY ARREGLAR ESTE QUERY
				listaResultados = (ArrayList) SessionServiceLocator.getPlanMedioSessionService()
					.findPlanMedioByQuery(startIndex,endIndex,queryBuilded, Parametros.getIdEmpresa());
			}
				
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
