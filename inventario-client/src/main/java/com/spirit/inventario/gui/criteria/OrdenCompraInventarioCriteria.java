package com.spirit.inventario.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.gui.helper.SessionServiceLocator;

public class OrdenCompraInventarioCriteria extends Criteria{

	Map queryBuilded;
	String txtCodigo="", txtObservacion="", txtProveedor="";
	
	public OrdenCompraInventarioCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Ordenes de Compra";
	}
	
	public OrdenCompraInventarioCriteria( List listaResultados ){
		this.listaResultados =listaResultados;
	}
	
	public List getHeaders() {
		List<String> headers = new ArrayList<String>();
		headers.add("C\u00f3digo");
		headers.add("Observaci\u00f3n");
		headers.add("Proveedor");
		/*headers.add("Tipo de Documento");
		headers.add("Fecha");
		headers.add("Fecha Recepción");*/
		headers.add("Estado");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		//try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				OrdenCompraIf bean = (OrdenCompraIf) it.next();
				fila.add(bean.getCodigo());
				fila.add(bean.getObservacion());
				try {
					ClienteOficinaIf clienteOficinaIf = com.spirit.comun.util.SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(bean.getProveedorId());
					fila.add(clienteOficinaIf!=null?clienteOficinaIf.getDescripcion():"");
				} catch (GenericBusinessException e) {
					fila.add("");
					e.printStackTrace();
				}
				/*fila.add(TipoDocumentoModel.getTipoDocumentoSessionService().getTipoDocumento(bean.getTipodocumentoId()));
				fila.add(Utilitarios.getFechaCortaUppercase((Date)bean.getFecha()));
				fila.add(Utilitarios.getFechaCortaUppercase((Date)bean.getFechaRecepcion()));*/
				String estado = bean.getEstado();
				fila.add(estado.equals("E")?"ENVIADA":"INACTIVA");
				data.add(fila);
			}
		/*} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}*/
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
			listaResultados = (ArrayList) SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompraByMapList(startIndex,endIndex,queryBuilded,Parametros.getIdEmpresa());
		}
		catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private Map buildQuery() {
		Map<String,Object> aMap = new HashMap<String,Object>();
		if (txtCodigo != null && !("".equals(txtCodigo))) {
			aMap.put("codigo", txtCodigo + "%");
		} else
			aMap.put("codigo", "%");
		if (txtObservacion != null && !("".equals(txtObservacion))) {
			aMap.put("observacionCompra", txtObservacion + "%");
		}
		if (txtProveedor != null && !("".equals(txtProveedor))) {
			aMap.put("descripcionCliente", txtProveedor + "%");
		}
		aMap.put("estado", "E" );
		
		return aMap;
	}

	public int getResultListSize() {
		try {
			tamanoListaResultados = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompraByMapSize(queryBuilded,Parametros.getIdEmpresa());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.tamanoListaResultados;
	}

	public void setTxtParametros(String txtCodigo, String txtObservacion,String txtProveedor) {
		this.txtCodigo = txtCodigo;
		this.txtObservacion = txtObservacion;
		this.txtProveedor = txtProveedor;
		queryBuilded = buildQuery();
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
 
}
