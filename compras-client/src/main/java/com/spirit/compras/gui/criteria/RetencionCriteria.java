package com.spirit.compras.gui.criteria;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.general.util.GeneralUtil;
import com.spirit.util.Utilitarios;

public class RetencionCriteria extends CompraCriteriaBase {
	
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
	
	public RetencionCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Pedido";
	}
	
	public RetencionCriteria(String nombrePanel, Long idFiltro, String tipoCliente) {
		this.nombrePanel = nombrePanel;
		this.idFiltroBusqueda = idFiltro;
		
	}

	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}
	
	public List getHeaders() {
		ArrayList<Object> headers = new ArrayList<Object>();
		headers.add("Num. Retencion");
		headers.add("Cliente");		
		headers.add("Fecha Pedido");	
				
		return headers;
	}

	public List armarModel(List lista) {
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				CompraRetencionIf bean = (CompraRetencionIf) it.next();
				fila.add(bean.getSecuencial());
				ClienteIf clienteif=null; 
				
				CompraIf compraIf = SessionServiceLocator.getCompraSessionService().getCompra(bean.getCompraId());							

				ClienteOficinaIf clienteOficinaIf = GeneralUtil.verificarMapaClienteOficina(mapaClienteOficina,compraIf.getProveedorId());
				clienteif = GeneralUtil.verificarMapaCliente(mapaCliente,clienteOficinaIf.getClienteId());	

				if(clienteif!=null) fila.add(clienteif.getNombreLegal());
				else fila.add("");
				fila.add(Utilitarios.getFechaCortaUppercase((Date)bean.getFechaEmision()));
				
				data.add(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
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
			//listaResultados = (ArrayList) getPedidoSessionService().getPedidoList(startIndex, endIndex, queryBuilded, Parametros.getIdEmpresa());
			System.out.println("******************************");
			listaResultados = (ArrayList) SessionServiceLocator.getCompraRetencionSessionService().getCompraRetencionByQueryList2(startIndex,endIndex,queryBuilded,Parametros.getIdEmpresa());
			
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try {
			if (idFiltroBusqueda == 0L) {
				if (queryBuilded.size() == 0) {
					tamanoListaResultados = SessionServiceLocator.getCompraRetencionSessionService().getCompraRetencionListSize(queryBuilded, Parametros.getIdEmpresa());
				} else {
					tamanoListaResultados = SessionServiceLocator.getCompraRetencionSessionService().getCompraRetencionListSize(queryBuilded, Parametros.getIdEmpresa());
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

	
	public void setTxtParametros(String numero, String cliente,String fecha) {
		this.txtnumero = numero;
		this.txtcliente = cliente;
		 
		queryBuilded = buildQuery();
	}

	
	private Map buildQuery() {
		String estado="A";
		Map aMap = new HashMap();
		boolean entro=false;
	 
			
		if (txtnumero != null && !("".equals(this.txtnumero)))
			{
			aMap.put("secuencial", this.txtnumero + "%");
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
		 
		return aMap;
	}
	
	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
	 
}
