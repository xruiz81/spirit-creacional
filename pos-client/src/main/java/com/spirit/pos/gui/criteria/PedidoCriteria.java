package com.spirit.pos.gui.criteria;

 

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import com.spirit.facturacion.entity.PedidoIf;
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

	String txtnumero="", txtcliente="", txtestado="";
	public PedidoIf pedido;
	Long idFiltroBusqueda;
	
	public PedidoCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Pedido";
	}
	
	public PedidoCriteria(String nombrePanel, Long idFiltro, String tipoCliente) {
		this.nombrePanel = nombrePanel;
		this.idFiltroBusqueda = idFiltro;
		
	}

	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}
	
	public List getHeaders() {
		ArrayList<Object> headers = new ArrayList<Object>();
		headers.add("Pre-Impreso");
		headers.add("Cliente");
		headers.add("Estado");
		headers.add("Fecha Pedido");		
		headers.add("Observación");		
		return headers;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList<String> fila = new ArrayList<String>();
			PedidoIf bean = (PedidoIf) it.next();
			
			FacturaIf facturaIf = null;
			//ClienteIf clienteif=null; 
			try {
				//clienteOficinaIf = getClienteOficinaSessionService().getClienteOficina(bean.getClienteoficinaId());
				Iterator itFactura= SessionServiceLocator.getFacturaSessionService().findFacturaByPedidoId(bean.getId()).iterator();
				if(itFactura.hasNext()){
					facturaIf=(FacturaIf)itFactura.next();
					fila.add(facturaIf.getPreimpresoNumero());					
				}
				else{					
					fila.add(bean.getCodigo());
				}
					
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
			
			ClienteOficinaIf clienteOficinaIf = null;
			ClienteIf clienteif=null; 
			try {
				clienteOficinaIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(bean.getClienteoficinaId());							
				clienteif = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());				 
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
			
			
			
			fila.add(clienteif!=null?clienteif.getNombreLegal():"");
			
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
			
			fila.add(Utilitarios.getFechaCortaUppercase((Date)bean.getFechaPedido()));
			fila.add(bean.getObservacion()!=null?bean.getObservacion():"");
			
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
		try {
			 
			
			if (idFiltroBusqueda == 0L) {
				if (queryBuilded.size() == 0) {
					tamanoListaResultados = SessionServiceLocator.getPedidoSessionService().getPedidoListSize(queryBuilded, Parametros.getIdEmpresa());
				} else {
					tamanoListaResultados = SessionServiceLocator.getPedidoSessionService().getPedidoListSize(queryBuilded, Parametros.getIdEmpresa());
				}
			} /*else {
				if (queryBuilded.size() == 0) {
					Map filtroMap = new HashMap();
					filtroMap.put("corporacionId", idFiltroBusqueda);
					tamanoListaResultados = getPedidoSessionService().getPedidoListSize(queryBuilded, Parametros.getIdEmpresa());
				} else {
					queryBuilded.put("corporacionId", idFiltroBusqueda);
					tamanoListaResultados = getPedidoSessionService().getPedidoListSize(queryBuilded, Parametros.getIdEmpresa());
				}
			}*/
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return 0;
		}
		return tamanoListaResultados;
	}

	
	public void setTxtParametros(String numero, String cliente,String estado) {
		this.txtnumero = numero;
		this.txtcliente = cliente;
		this.txtestado = estado;
		queryBuilded = buildQuery();
	}

	
	private Map buildQuery() {
		String estado="A";
		Map aMap = new HashMap();
		boolean entro=false;
	 
			
		if (txtnumero != null && !("".equals(this.txtnumero)))
			{
			aMap.put("codigo", this.txtnumero + "%");
			entro=true;
			}
		if (txtcliente != null && !("".equals(this.txtcliente)))
			{
			
			entro=true;
			
			
			ClienteOficinaIf clienteOficinaIf = null;
			ClienteIf clienteif=null; 
			try {				
				Map aMap2 = new HashMap();
				aMap2.put("nombreLegal",this.txtcliente+"%");				
				Collection<ClienteIf> clientesColeccion= SessionServiceLocator.getClienteSessionService().findClienteByQuery(aMap2);					 
				if ( clientesColeccion.size() > 0 ){
					clienteif = clientesColeccion.iterator().next();					 
					Collection<ClienteOficinaIf> clientesOficinaColeccion = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(clienteif.getId());					
					if ( clientesOficinaColeccion.size() > 0 ){
						clienteOficinaIf = clientesOficinaColeccion.iterator().next();						 
					}					
				}
				
			} catch (GenericBusinessException e) {
				e.printStackTrace();
			}
			
			String clienteOficinaID="%";
			
			clienteOficinaID=clienteOficinaIf!=null?clienteOficinaIf.getId().toString():"";
		 
		      if(!clienteOficinaID.equals(""))
		    	  aMap.put("clienteoficinaId", new Long(clienteOficinaID));
		      else
		    	  entro=false;
			}
		if (txtestado != null && !("".equals(this.txtestado)))
			{
			entro=true;
			aMap.put("estado", this.txtestado + "%");
			
			}
		
		if(!entro)
			aMap.put("estado", "%");
		
		
		
		return aMap;
	}
	
	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}

}
