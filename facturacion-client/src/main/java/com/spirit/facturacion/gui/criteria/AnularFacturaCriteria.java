package com.spirit.facturacion.gui.criteria;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.general.util.GeneralUtil;
import com.spirit.util.Utilitarios;

public class AnularFacturaCriteria extends Criteria {
	
	Map queryBuilded;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");

	private Map<Long, ClienteIf> mapaCliente = new HashMap<Long, ClienteIf>();
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	
	public AnularFacturaCriteria() {
		
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Factura";
	}

	public AnularFacturaCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List getHeaders() {
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Preimpreso");
		headers.add("Fecha");
		headers.add("Cliente");
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
				FacturaIf bean = (FacturaIf) it.next();
				//fila.add(formatoSerial.format(bean.getNumero().doubleValue()));
				fila.add(bean.getPreimpresoNumero() != null ? bean.getPreimpresoNumero() : "" );
				fila.add(Utilitarios.getFechaCortaUppercase(bean.getFechaFactura()));
				ClienteOficinaIf clienteOficina = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina,bean.getClienteoficinaId());
				ClienteIf cliente = GeneralUtil.verificarMapaCliente(mapaCliente,clienteOficina.getClienteId());
				fila.add(cliente.getRazonSocial());
				fila.add(bean.getObservacion());
				double valor = bean.getValor().doubleValue();
				double iva = bean.getIva().doubleValue();
				double ice = bean.getIce().doubleValue();
				double otroImpuesto = bean.getOtroImpuesto().doubleValue();
				double descuento = bean.getDescuento().doubleValue();
				double descuentosVarios = bean.getDescuentosVarios().doubleValue();
				double descuentoGlobal = bean.getDescuentoGlobal().doubleValue();
				double porcentajeComision = bean.getPorcentajeComisionAgencia().doubleValue();
				double comision = Utilitarios.redondeoValor(((valor - descuento - descuentosVarios - descuentoGlobal) * porcentajeComision) / 100D);
				double total = valor - descuento - descuentosVarios - descuentoGlobal + comision + iva + ice + otroImpuesto; 
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
			listaResultados = (ArrayList) SessionServiceLocator.getFacturaSessionService().getFacturaNoAnuladaList(startIndex, endIndex, queryBuilded, Parametros.getIdEmpresa());
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
