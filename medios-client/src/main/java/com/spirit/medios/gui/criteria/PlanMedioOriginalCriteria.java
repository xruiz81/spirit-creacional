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

public class PlanMedioOriginalCriteria extends Criteria{

	Map queryBuilded;
	
	Long clienteOficinaId = null;
	//ADD 17 JUNIO
	Long campanaId = null;
	String tipoPlanMedio = null;
	
	private static final String NOMBRE_ESTADO_EN_PROCESO = "EN PROCESO";
	private static final String NOMBRE_ESTADO_PENDIENTE  = "PENDIENTE";
	private static final String NOMBRE_ESTADO_APROBADO   = "APROBADO";
	//private static final String NOMBRE_ESTADO_ELIMINADO  = "ELIMINADO";
	private static final String NOMBRE_ESTADO_FACTURADO  = "FACTURADO";
	//ADD 9 JUNIO
	//private static final String NOMBRE_ESTADO_MODIFICADO = "MODIFICADO";
	//private static final String NOMBRE_ESTADO_MODIFICADO = "HISTORICO";
	private static final String NOMBRE_ESTADO_PEDIDO 	 = "PEDIDO";
	//END 9 JUNIO
	private static final String ESTADO_EN_PROCESO = "N";
	private static final String ESTADO_PENDIENTE  = "P";
	private static final String ESTADO_APROBADO   = "A";
	//private static final String ESTADO_ELIMINADO  = "E";
	private static final String ESTADO_FACTURADO  = "F";
	//ADD 9 JUNIO
	//private static final String ESTADO_MODIFICADO = "M";
	//private static final String ESTADO_HISTORICO = "H";
	private static final String ESTADO_PEDIDO 	  = "D";
	//END 9 JUNIO

	public PlanMedioOriginalCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Planes de Medios";
	}
	
	//MODIFIED 17 JUNIO
	//public PlanMedioOriginalCriteria(Long clienteOficinaId){
	public PlanMedioOriginalCriteria(Long clienteOficinaId,Long campanaId, String tipoPlanMedio){
		this.clienteOficinaId = clienteOficinaId;
		this.campanaId = campanaId;
		this.tipoPlanMedio = tipoPlanMedio;
	}
	
	public PlanMedioOriginalCriteria( List listaResultados ){
		this.listaResultados = listaResultados;
	}
	
	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Concepto");
		//headers.add("Tipo de Proveedor");
		headers.add("Estado");
		return headers;
	}
//me kede aki
	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();
		//try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				PlanMedioIf bean = (PlanMedioIf) it.next();

				fila.add(bean.getCodigo());
				fila.add(bean.getConcepto());
				//TipoProveedorIf tipoProveedorTemp = getTipoProveedorSessionService().getTipoProveedor(bean.getTipoproveedorId());
				//fila.add(tipoProveedorTemp.getNombre());
				//fila.add("");
				
				if (ESTADO_EN_PROCESO.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_EN_PROCESO);
				else if (ESTADO_PENDIENTE.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_PENDIENTE);
				else if (ESTADO_APROBADO.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_APROBADO);
				else if (ESTADO_FACTURADO.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_FACTURADO);
				/*else if (ESTADO_ELIMINADO.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_ELIMINADO);*/
				//ADD 9 JUNIO
				/*else if (ESTADO_MODIFICADO.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_MODIFICADO);*/
				/*else if (ESTADO_HISTORICO.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_HISTORICO);*/
				else if (ESTADO_PEDIDO.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_PEDIDO);
				//END 9 JUNIO
				
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
			listaResultados = (ArrayList) SessionServiceLocator.getPlanMedioSessionService()
							  .findPlanMedioOriginalByQueryAndByIdClienteOficinaAndIdCampana(startIndex,endIndex,
							    clienteOficinaId, campanaId, Parametros.getIdEmpresa(), tipoPlanMedio);
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
