package com.spirit.medios.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.util.GeneralUtil;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.util.Utilitarios;

public class OrdenMedioCriteria extends Criteria{
	
	private Map queryBuilded;
	private String fechaInicial = null, fechaFinal = null;
	private Long clienteId = null, empresaId = null;		
	private Boolean medio = null;
	private String[] estados = new String[]{};
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_ENVIADA = "ENVIADA";
	private static final String NOMBRE_ESTADO_ANULADA = "ANULADA";
	private static final String NOMBRE_ESTADO_INGRESADA = "INGRESADA";
	private static final String ESTADO_ANULADA = NOMBRE_ESTADO_ANULADA.substring(0,1);
	private static final String ESTADO_PENDIENTE = NOMBRE_ESTADO_PENDIENTE.substring(0,1);
	private static final String ESTADO_ENVIADA = NOMBRE_ESTADO_ENVIADA.substring(0,1);	
	protected Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
		
	
	public OrdenMedioCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Ordendes de Medios";
	}
	
	public OrdenMedioCriteria( List listaResultados ) {
		this.listaResultados =listaResultados;
	}
	
	public OrdenMedioCriteria(Long empresaId) {
		this.empresaId = empresaId;
	}
	
	public OrdenMedioCriteria( String fechaInicial, String fechaFinal, Long clienteId ){
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.clienteId = clienteId;
	}
	
	public OrdenMedioCriteria(Long empresaId, Boolean medio) {
		this.empresaId = empresaId;
		this.medio = medio;
	}
	
	
	//para Compra de Ordenes de Medio directamente
	public List getHeaders() {
		List<String> headers = new ArrayList<String>();
		headers.add("Código");
		headers.add("Proveedor");
		headers.add("F.Creación");
		headers.add("F.Orden Medio");
		headers.add("Plan de Medio");
		headers.add("Estado");
		headers.add("Observación");
		return headers;
	}
		
	//ADD 18 JULIO
	//para Compra de Ordenes de Medio directamente
	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				OrdenMedioIf bean = (OrdenMedioIf) it.next();
				fila.add(bean.getCodigo());
				fila.add(GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina,bean.getProveedorId()));
				fila.add(Utilitarios.getFechaCortaUppercase(new java.util.Date(bean.getFechaCreacion().getTime())));
				fila.add(Utilitarios.getFechaCortaUppercase(new java.util.Date(bean.getFechaOrden().getTime())));
				
				PlanMedioIf planMedio = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(bean.getPlanMedioId());
				fila.add(planMedio.getCodigo());
				
				String estado = bean.getEstado();				
				if(estado.equals(ESTADO_ANULADA))
					fila.add(NOMBRE_ESTADO_ANULADA);
				else if(estado.equals(ESTADO_PENDIENTE))
					fila.add(NOMBRE_ESTADO_PENDIENTE);
				else if(estado.equals(ESTADO_ENVIADA))
					fila.add(NOMBRE_ESTADO_ENVIADA);
				else
					fila.add(NOMBRE_ESTADO_INGRESADA);
				
				fila.add(bean.getObservacion()!=null?bean.getObservacion():"");
				data.add(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
		return data;
	}
	//END ADD 18 JULIO

	public void setResultList(List listaResultados) {
		this.listaResultados =listaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	//COMENTED 18 JULIO era para Solicitud de Compra de Ordenes de Medio
	/*public void buscarRegistros(int startIndex,int endIndex){
		try{
			if (fechaInicial != null && fechaFinal != null && empresaId == null)
				listaResultados = (ArrayList) SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQueryAndByFechas(queryBuilded, fechaInicial, fechaFinal, Parametros.getIdEmpresa());
			else if (fechaInicial != null && fechaFinal != null && clienteId != null && empresaId == null)
				listaResultados = (ArrayList) SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQueryAndByFechasAndByClienteId(queryBuilded, fechaInicial, fechaFinal, clienteId, Parametros.getIdEmpresa());
			else if (clienteId != null && empresaId == null)
				listaResultados = (ArrayList) SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQueryAndByClienteId(queryBuilded, clienteId, Parametros.getIdEmpresa());
			else if (empresaId != null)
				listaResultados = (ArrayList) SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQueryAndEmpresaId(startIndex, endIndex, queryBuilded, empresaId);
			else
				listaResultados = (ArrayList) SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQuery(queryBuilded);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}*/
	
	public void buscarRegistros(int startIndex,int endIndex){
		try{
			if(estados.length > 0){
				listaResultados = (ArrayList) SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQueryAndByEstados(startIndex, endIndex, queryBuilded, estados);
			}else{
				listaResultados = (ArrayList) SessionServiceLocator.getOrdenMedioSessionService().getOrdenMedioByQueryList(startIndex,endIndex,queryBuilded,empresaId,medio);
			}			
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	

	public int getResultListSize() {
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

	public void setEstados(String[] estados) {
		this.estados = estados;
	}
 
}
