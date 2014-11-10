package com.spirit.cartera.gui.criteria;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModel;
import com.spirit.compras.entity.CompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.util.GeneralUtil;
import com.spirit.util.Utilitarios;

public class CarteraCruceNotaCreditoCriteria extends CarteraCriteriaBase {
	
	String estado;
	Map queryBuilded;
	Long documentoAplicaId;
	TipoDocumentoIf tipoDocumento;
	DocumentoIf documento;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	public CarteraCruceNotaCreditoCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Carteras";
	}
	
	public CarteraCruceNotaCreditoCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}
	
	public CarteraCruceNotaCreditoCriteria(Long documentoAplicaId) {
		try {
		this.documentoAplicaId = documentoAplicaId;
		documento = SessionServiceLocator.getDocumentoSessionService().getDocumento(documentoAplicaId);
		tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(documento.getTipoDocumentoId());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Factura");
		headers.add("Fecha Emisión");
		headers.add("Referencia");
		headers.add("Proveedor");
		headers.add("Saldo");
		return headers;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}
	
	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();
		try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				CarteraIf bean = (CarteraIf) it.next();
				fila.add(bean.getPreimpreso());
				fila.add(bean.getFechaEmision()!=null ? Utilitarios.getFechaCortaUppercase(bean.getFechaEmision()) : "");
				if (tipoDocumento.getCodigo().equals("COM") || tipoDocumento.getCodigo().equals("COR") || tipoDocumento.getCodigo().equals("LIC") || tipoDocumento.getCodigo().equals("COI") || tipoDocumento.getCodigo().equals("CNV") || tipoDocumento.getCodigo().equals("SAE")) {
					Map parameterMap = new HashMap();
					parameterMap.put("tipodocumentoId", tipoDocumento.getId());
					parameterMap.put("id", bean.getReferenciaId());
					Iterator compraIt = SessionServiceLocator.getCompraSessionService().findCompraByQuery(parameterMap).iterator();
					if (compraIt.hasNext()) {
						CompraIf compra = (CompraIf) compraIt.next();
						fila.add(compra.getReferencia());
					} else
						fila.add("");
				} else
					fila.add("");
				ClienteOficinaIf operadorNegocioOficina = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina,bean.getClienteoficinaId());
				ClienteIf operadorNegocio = GeneralUtil.verificarMapaCliente(mapaCliente,operadorNegocioOficina.getClienteId());
				fila.add(operadorNegocio.getRazonSocial()!=null?operadorNegocio.getRazonSocial():"");
				fila.add(formatoDecimal.format(Utilitarios.redondeoValor(bean.getSaldo().doubleValue())));
	            data.add(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
		return data;
	}
	
	public void buscarRegistros(int startIndex,int endIndex){
		try {
			listaResultados = (ArrayList)SessionServiceLocator.getCarteraSessionService().getCarteraParaCruceNotaCreditoList(startIndex, endIndex, queryBuilded, documentoAplicaId);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public SpiritModel getSpiritModel() {
		return null;
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
