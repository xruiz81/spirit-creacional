package com.spirit.cartera.gui.criteria;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.util.GeneralUtil;
import com.spirit.util.Utilitarios;

public class AnularNotaCreditoCriteria extends CarteraCriteriaBase {
	
	Map queryBuilded;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");

	public AnularNotaCreditoCriteria() {
		
	}

	public void setNombrePanel() {
		this.nombrePanel = "de Nota de Crédito";
	}
	
	public AnularNotaCreditoCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List getHeaders() {
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Preimpreso");
		headers.add("Fecha");
		headers.add("Operador");
		headers.add("Observación");
		headers.add("Total");
		return headers;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();
		
		try {
			Iterator it = lista.iterator();
			
			while (it.hasNext()) {
				ArrayList<String> fila = new ArrayList<String>();
				NotaCreditoIf bean = (NotaCreditoIf) it.next();
				//fila.add(formatoSerial.format(bean.getNumero().doubleValue()));
				fila.add(bean.getPreimpreso() != null ? bean.getPreimpreso() : "" );
				fila.add(Utilitarios.getFechaCortaUppercase(bean.getFechaEmision()));
				ClienteOficinaIf clienteOficina = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina, bean.getOperadorNegocioId());
				ClienteIf operador = GeneralUtil.verificarMapaCliente(mapaCliente,clienteOficina.getClienteId());
				fila.add(operador.getRazonSocial());
				fila.add(bean.getObservacion());
				double valor = bean.getValor().doubleValue();
				double iva = bean.getIva().doubleValue();
				double ice = bean.getIce().doubleValue();
				double otroImpuesto = bean.getOtroImpuesto().doubleValue();
				double total = valor + iva + ice + otroImpuesto; 
				fila.add( formatoDecimal.format(total) );
				data.add(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error !!", SpiritAlert.ERROR);
			e.printStackTrace();
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
			listaResultados = (ArrayList) SessionServiceLocator.getNotaCreditoSessionService().getNotaCreditoNoAnuladaList(startIndex, endIndex, queryBuilded, Parametros.getIdEmpresa());
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
