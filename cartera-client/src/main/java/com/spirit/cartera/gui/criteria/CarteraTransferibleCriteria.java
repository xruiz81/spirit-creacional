package com.spirit.cartera.gui.criteria;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.util.GeneralUtil;
import com.spirit.util.Utilitarios;

public class CarteraTransferibleCriteria extends CarteraCriteriaBase {

	String estado;
	Map queryBuilded;
	
	Long moduloId,empresaId;
	
	private static final String NOMBRE_ESTADO_NORMAL = "NORMAL";
	private static final String ESTADO_NORMAL = NOMBRE_ESTADO_NORMAL.substring(0,1);
	private static final String NOMBRE_ESTADO_ANULADO = "ANULADO";
	private static final String ESTADO_ANULADO = NOMBRE_ESTADO_ANULADO.substring(0, 1);
	private static final String NOMBRE_ESTADO_DUDOSO = "DUDOSO";
	private static final String ESTADO_DUDOSO = NOMBRE_ESTADO_DUDOSO.substring(0,1);
	private static final String NOMBRE_ESTADO_CASTIGADO = "CASTIGADO";
	private static final String ESTADO_CASTIGADO = NOMBRE_ESTADO_CASTIGADO.substring(0,1);
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	public CarteraTransferibleCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Carteras";
	}
	
	public CarteraTransferibleCriteria(List listaResultados){
		this.listaResultados = listaResultados;
	}
	
	public CarteraTransferibleCriteria(Long moduloId, Long empresaId) {
		this.moduloId = moduloId;
		this.empresaId = empresaId;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("F. Emisión");
		headers.add("Tipo Documento");
		headers.add("Cliente");
		headers.add("Preimpreso");
		headers.add("Estado");
		headers.add("Saldo");
		return headers;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();
		try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				CarteraIf bean = (CarteraIf) it.next();
	           
				fila.add(bean.getCodigo());
				
				fila.add(
						bean.getFechaEmision()!=null ? Utilitarios.getFechaCortaUppercase(bean.getFechaEmision()) : ""
						);
				
				TipoDocumentoIf tipoDocumento = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(bean.getTipodocumentoId());
				fila.add(tipoDocumento.getNombre()!=null?tipoDocumento.getNombre():"");
				
				ClienteOficinaIf clienteOficina = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina,bean.getClienteoficinaId());
				ClienteIf cliente = GeneralUtil.verificarMapaCliente(mapaCliente,clienteOficina.getClienteId());
				fila.add(cliente.getNombreLegal()!=null?cliente.getNombreLegal():"");
				
				fila.add(bean.getPreimpreso()!=null?bean.getPreimpreso():"");
	            
				if(ESTADO_CASTIGADO.equals(bean.getEstado()))
	            	fila.add(NOMBRE_ESTADO_CASTIGADO); 
				else if(ESTADO_ANULADO.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_ANULADO); 
				else if(ESTADO_DUDOSO.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_DUDOSO); 
				else if (ESTADO_NORMAL.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_NORMAL); 
				else fila.add("SIN ESTADO (OJO)");
				
				fila.add(bean.getSaldo()!=null?formatoDecimal.format(bean.getSaldo()):"error");
				
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
		try{
			listaResultados = (ArrayList)SessionServiceLocator.getCarteraSessionService()
								.getCarteraTransferibleList(startIndex,endIndex,queryBuilded, moduloId, empresaId);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public List getListaResultados() {
		return listaResultados;
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
