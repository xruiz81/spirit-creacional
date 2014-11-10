package com.spirit.medios.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.util.GeneralUtil;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.handler.EstadoPresupuesto;

public class PresupuestoCriteria extends MediosCriteriaBase {
	
	private Map queryBuilded;
	private String[] estados = new String[]{};
	private String codigo = "";
	private Long clienteId = 0L, clienteOficinaId = 0L, empresaId = 0L;
	private boolean busquedaPorProveedor = false;
	/*private static final String NOMBRE_ESTADO_COTIZADO = "COTIZADO";
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_APROBADO = "APROBADO";
	private static final String NOMBRE_ESTADO_CANCELADO = "CANCELADO";
	private static final String NOMBRE_ESTADO_FACTURADO = "FACTURADO";
	public static final String ESTADO_COTIZADO = NOMBRE_ESTADO_COTIZADO.substring(2, 3);
	public static final String ESTADO_PENDIENTE = NOMBRE_ESTADO_PENDIENTE.substring(0, 1);
	public static final String ESTADO_APROBADO = NOMBRE_ESTADO_APROBADO.substring(0, 1);
	public static final String ESTADO_CANCELADO = NOMBRE_ESTADO_CANCELADO.substring(0, 1);
	public static final String ESTADO_FACTURADO = NOMBRE_ESTADO_FACTURADO.substring(0, 1);*/
	
	public PresupuestoCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Presupuestos";
	}
	
	public PresupuestoCriteria(Long empresaId) {
		this.empresaId = empresaId;
	}
	
	public PresupuestoCriteria(Long clienteId, Long clienteOficinaId) {
		this.clienteId = clienteId;
		this.clienteOficinaId = clienteOficinaId;
	}
	
	public PresupuestoCriteria(boolean busquedaPorProveedor) {
		this.busquedaPorProveedor = busquedaPorProveedor;
	}
	
	public PresupuestoCriteria( List listaResultados ) {
		this.listaResultados =listaResultados;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Estado");
		headers.add("Oficina del Cliente");
		headers.add("Concepto");
		return headers;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				PresupuestoIf bean = (PresupuestoIf) it.next();
				fila.add(bean.getCodigo());

				/*if (ESTADO_COTIZADO.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_COTIZADO);
				else if (ESTADO_PENDIENTE.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_PENDIENTE);
				else if (ESTADO_APROBADO.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_APROBADO);
				else if (ESTADO_CANCELADO.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_CANCELADO);
				else if (ESTADO_FACTURADO.equals(bean.getEstado()))
					fila.add(NOMBRE_ESTADO_FACTURADO);*/
				
				String estadoLetra = bean.getEstado();
				EstadoPresupuesto estado = EstadoPresupuesto.getEstadoPresupuestoByLetra(estadoLetra);
				fila.add(estado);

				if(bean.getClienteOficinaId() != null){
					ClienteOficinaIf clienteOficinaTempIf = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina,bean.getClienteOficinaId());
					fila.add(clienteOficinaTempIf.getDescripcion());
					fila.add(bean.getConcepto());
					data.add(fila);
				}else{
					
					if(bean.getClienteOficinaId() != null){
						ClienteOficinaIf clienteOficinaTempIf = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina, bean.getClienteOficinaId());
						fila.add(clienteOficinaTempIf.getDescripcion());
					}else{
						OrdenTrabajoDetalleIf ordenTD = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(bean.getOrdentrabajodetId());
						OrdenTrabajoIf ordenT = GeneralUtil.verificarMapaOrdenTrabajo(mapaOrdenTrabajo,ordenTD.getOrdenId());
						ClienteOficinaIf clienteOficinaTempIf = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina, ordenT.getClienteoficinaId());
						fila.add(clienteOficinaTempIf.getDescripcion());
					}					
					
					fila.add(bean.getConcepto());
					data.add(fila);
				}
				
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
		try {
			//el orden de los if es muy importante, revisar bien antes de cambiarlo
			if (empresaId.compareTo(0L) != 0)
				listaResultados = (ArrayList) SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByQueryAndEmpresaId(startIndex, endIndex, queryBuilded, empresaId);
			else if(estados.length > 0 && !busquedaPorProveedor)
				listaResultados = (ArrayList) SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigoByClienteByEmpresaAndByEstados(startIndex, endIndex, codigo, clienteId, clienteOficinaId, Parametros.getIdEmpresa(), estados);
			else if(busquedaPorProveedor)
				listaResultados = (ArrayList) SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByEmpresaIdByProveedorIdAndByEstados(startIndex, endIndex, clienteOficinaId, Parametros.getIdEmpresa(), estados);
			else
				listaResultados = (ArrayList) SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByFilteredQuery(startIndex, endIndex, queryBuilded, clienteId, clienteOficinaId, Parametros.getIdEmpresa());
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

	public void setEstados(String[] estados) {
		this.estados = estados;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public boolean isBusquedaPorProveedor() {
		return busquedaPorProveedor;
	}

	public void setBusquedaPorProveedor(boolean busquedaPorProveedor) {
		this.busquedaPorProveedor = busquedaPorProveedor;
	}
}
