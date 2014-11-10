package com.spirit.compras.gui.criteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.SpiritAlert;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.util.GeneralUtil;
import com.spirit.util.Utilitarios;
import com.spirit.compras.handler.EstadoOrdenCompra;


public class OrdenCompraCriteria extends CompraCriteriaBase{

	private Map queryBuilded;
	private Long empresaId = 0L;
	private Boolean compra = null;
	private String[] estados = new String[]{};
	private Long presupuestoId = 0L;
	/*private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_ENVIADA = "ENVIADA";
	private static final String NOMBRE_ESTADO_INACTIVA = "INACTIVA";
	private static final String NOMBRE_ESTADO_INGRESADA = "INGRESADA";
	private static final String NOMBRE_ESTADO_ANULADA = "ANULADA";
	private static final String ESTADO_INACTIVA = NOMBRE_ESTADO_INACTIVA.substring(0,1);
	private static final String ESTADO_PENDIENTE = NOMBRE_ESTADO_PENDIENTE.substring(0,1);
	private static final String ESTADO_ENVIADA = NOMBRE_ESTADO_ENVIADA.substring(0,1);
	private static final String ESTADO_ANULADA = NOMBRE_ESTADO_ANULADA.substring(0,1);*/
	
	public OrdenCompraCriteria(Long empresaId, Boolean compra) {
		this.empresaId = empresaId;
		this.compra = compra;
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Ordenes de Compras";
	}
	
	public OrdenCompraCriteria( List listaResultados ){
		this.listaResultados =listaResultados;
	}
	
	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}

	public List getHeaders() {
		List<String> headers = new ArrayList<String>();
		headers.add("Código");
		headers.add("Proveedor");
		headers.add("Fecha");
		headers.add("F.Recepción");
		headers.add("Estado");
		headers.add("Observación");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				OrdenCompraIf bean = (OrdenCompraIf) it.next();
				fila.add(bean.getCodigo());
				fila.add(GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina,bean.getProveedorId()));
				fila.add(Utilitarios.getFechaCortaUppercase((Date)bean.getFecha()));
				fila.add(Utilitarios.getFechaCortaUppercase((Date)bean.getFechaRecepcion()));
				
				String estado = bean.getEstado();				
				/*if(estado.equals(ESTADO_INACTIVA))
					fila.add(NOMBRE_ESTADO_INACTIVA);
				else if(estado.equals(ESTADO_PENDIENTE))
					fila.add(NOMBRE_ESTADO_PENDIENTE);
				else if(estado.equals(ESTADO_ENVIADA))
					fila.add(NOMBRE_ESTADO_ENVIADA);
				else if(estado.equals(ESTADO_ANULADA))
					fila.add(NOMBRE_ESTADO_ANULADA);
				else
					fila.add(NOMBRE_ESTADO_INGRESADA);*/
				
				fila.add(EstadoOrdenCompra.getEstadoOrdenCompraByLetra(estado));
				
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

	public void setResultList(List listaResultados) {
		this.listaResultados =listaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public void buscarRegistros(int startIndex,int endIndex){
		try{
			if(estados.length > 0){
				listaResultados = (ArrayList) SessionServiceLocator.getOrdenCompraSessionService().findOrdenCompraByQueryByPresupuestoIdAndByEstados(startIndex, endIndex, queryBuilded, presupuestoId, estados);
			}else{
				listaResultados = (ArrayList) SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompraByQueryList(startIndex,endIndex,queryBuilded,empresaId,compra);
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

	public void setEstados(String[] estados) {
		this.estados = estados;
	}

	public void setPresupuestoId(Long presupuestoId) {
		this.presupuestoId = presupuestoId;
	}
}
