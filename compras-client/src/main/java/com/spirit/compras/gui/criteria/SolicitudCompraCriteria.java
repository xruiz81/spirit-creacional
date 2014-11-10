package com.spirit.compras.gui.criteria;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.util.Utilitarios;

public class SolicitudCompraCriteria extends Criteria{
	
	Map queryBuilded;
	Long empresaId = 0L;
	Long proveedorId = 0L;
	Boolean ordenCompra = null;
	static final String NOMBRE_TIPO_REFERENCIA_PRESUPUESTO = "PRESUPUESTO";
	static final String TIPO_REFERENCIA_PRESUPUESTO = NOMBRE_TIPO_REFERENCIA_PRESUPUESTO.substring(0,1);
	static final String NOMBRE_TIPO_REFERENCIA_ORDEN_MEDIOS = "ORDEN DE MEDIOS";
	static final String TIPO_REFERENCIA_ORDEN_MEDIOS = NOMBRE_TIPO_REFERENCIA_ORDEN_MEDIOS.substring(0,1);
	static final String NOMBRE_TIPO_REFERENCIA_NINGUNO = "NINGUNO";
	static final String TIPO_REFERENCIA_NINGUNO = NOMBRE_TIPO_REFERENCIA_NINGUNO.substring(0,1);
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_APROBADO = "APROBADO";
	private static final String NOMBRE_ESTADO_REALIZADO = "REALIZADO";
	private static final String ESTADO_ACTIVO = "A";
	private static final String ESTADO_INACTIVO = "I";
	private static final String ESTADO_PENDIENTE = "P";
	private static final String ESTADO_APROBADO = "B";
	private static final String ESTADO_REALIZADO = "R";
	
	
	public SolicitudCompraCriteria(Long empresaId, Long proveedorId, Boolean ordenCompra) {
		this.empresaId = empresaId;
		this.proveedorId = proveedorId;
		this.ordenCompra = ordenCompra;
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de SOlicitudes de Compras";
	}
	
	public SolicitudCompraCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}
	
	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}

	public List getHeaders() {
		List<String> headers = new ArrayList<String>();
		headers.add("Código");
		headers.add("F.Entrega");
		headers.add("T.Referencia");
		headers.add("Referencia");
		headers.add("Solicitud");
		headers.add("Estado");
		return headers;
	}

	public List getModel() {
		//NO SIRVE EL PARAMETRO SI YA ESTA SETIADO EN EL METODO CRITERIA(LIST L)
		ArrayList data = (ArrayList) armarModel(listaResultados);
		return data;
	}
	
	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			SolicitudCompraIf bean = (SolicitudCompraIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(Utilitarios.getFechaCortaUppercase((Date)bean.getFechaEntrega()));
			
			if (bean.getTipoReferencia().equals(TIPO_REFERENCIA_PRESUPUESTO))
				fila.add(NOMBRE_TIPO_REFERENCIA_PRESUPUESTO);
			else if (bean.getTipoReferencia().equals(TIPO_REFERENCIA_ORDEN_MEDIOS))
				fila.add( NOMBRE_TIPO_REFERENCIA_ORDEN_MEDIOS);
			else
				fila.add( NOMBRE_TIPO_REFERENCIA_NINGUNO);
						
			fila.add(bean.getReferencia() != null?bean.getReferencia():"");
			fila.add(bean.getObservacion() != null? bean.getObservacion():"");
			
			if(bean.getEstado().equals(ESTADO_ACTIVO))
				fila.add(NOMBRE_ESTADO_ACTIVO);
			else if(bean.getEstado().equals(ESTADO_INACTIVO))
				fila.add(NOMBRE_ESTADO_INACTIVO);
			else if(bean.getEstado().equals(ESTADO_PENDIENTE))
				fila.add(NOMBRE_ESTADO_PENDIENTE);
			else if(bean.getEstado().equals(ESTADO_APROBADO))
				fila.add(NOMBRE_ESTADO_APROBADO);
			else
				fila.add(NOMBRE_ESTADO_REALIZADO);
			
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
		try {
			if (this.proveedorId.compareTo(0L) == 0)
				listaResultados = (ArrayList) SessionServiceLocator.getSolicitudCompraSessionService().findSolicitudCompraByQueryAndEmpresaId(startIndex, endIndex, queryBuilded, empresaId, ordenCompra);
			else
				listaResultados = (ArrayList) SessionServiceLocator.getSolicitudCompraSessionService().findSolicitudCompraByQueryAndEmpresaId(startIndex, endIndex, queryBuilded, empresaId, proveedorId, ordenCompra);
		} catch (GenericBusinessException e) {
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
