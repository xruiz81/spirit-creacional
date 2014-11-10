package com.spirit.facturacion.gui.criteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.general.util.GeneralUtil;
import com.spirit.util.Utilitarios;

public class PedidoCriteria extends Criteria {
	
	Map queryBuilded;
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_COMPLETO = "COMPLETO";
	private static final String NOMBRE_ESTADO_INCOMPLETO = "INCOMPLETO";
	private static final String NOMBRE_ESTADO_ANULADO = "ANULADO";
	private static final String NOMBRE_ESTADO_COTIZACION = "COTIZACION";
	private static final String ESTADO_PENDIENTE = NOMBRE_ESTADO_PENDIENTE.substring(0, 1);
	private static final String ESTADO_COMPLETO = NOMBRE_ESTADO_COMPLETO.substring(0, 1);
	private static final String ESTADO_INCOMPLETO = NOMBRE_ESTADO_INCOMPLETO.substring(0, 1);
	private static final String ESTADO_ANULADO = NOMBRE_ESTADO_ANULADO.substring(0, 1);
	private static final String ESTADO_COTIZACION = NOMBRE_ESTADO_COTIZACION.substring(2, 3);

	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	
	public PedidoCriteria() {
		
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Pedido";
	}

	public PedidoCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}
	
	public List getHeaders() {
		ArrayList<Object> headers = new ArrayList<Object>();
		headers.add("Número");
		headers.add("Fecha Pedido");
		headers.add("Cliente");
		headers.add("Observación");
		headers.add("Estado");
		return headers;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList<String> fila = new ArrayList<String>();
			PedidoIf bean = (PedidoIf) it.next();
			fila.add(bean.getCodigo());
			fila.add(Utilitarios.getFechaCortaUppercase((Date)bean.getFechaPedido()));
			ClienteOficinaIf clienteOficinaIf = null;
			try {
				clienteOficinaIf = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina,bean.getClienteoficinaId());
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
			fila.add(clienteOficinaIf!=null?clienteOficinaIf.getDescripcion():"");
			fila.add(bean.getObservacion()!=null?bean.getObservacion():"");
			if (ESTADO_PENDIENTE.equals(bean.getEstado()))
				fila.add(NOMBRE_ESTADO_PENDIENTE);
			else if (ESTADO_COMPLETO.equals(bean.getEstado()))
				fila.add(NOMBRE_ESTADO_COMPLETO);
			else if (ESTADO_INCOMPLETO.equals(bean.getEstado()))
				fila.add(NOMBRE_ESTADO_INCOMPLETO);
			else if (ESTADO_COTIZACION.equals(bean.getEstado()))
				fila.add(NOMBRE_ESTADO_COTIZACION);
			else if (ESTADO_ANULADO.equals(bean.getEstado()))
				fila.add(NOMBRE_ESTADO_ANULADO);
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
			listaResultados = (ArrayList) SessionServiceLocator.getPedidoSessionService().getPedidoList(startIndex, endIndex, queryBuilded, Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		return this.tamanoListaResultados;
	}

	public void setTxtParametros(String txtCodigo, String txtDescripcion,
			String parametro3) {
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
 
}
