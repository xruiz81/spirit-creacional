package com.spirit.medios.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModel;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.util.GeneralUtil;
import com.spirit.medios.entity.CampanaIf;

public class CampanaCriteria extends MediosCriteriaBase{

	Long idFiltroBusqueda=0L;
	SpiritModel modelForm=null;
	String nombrePanel="";	
	Long corporacion=0L;	
	Map queryBuilded;	
	String txtCodigo,txtNombre;	
	private static final String NOMBRE_ESTADO_ACTIVA = "ACTIVA";
	private static final String NOMBRE_ESTADO_FINALIZADA = "FINALIZADA";
	private static final String NOMBRE_ESTADO_INACTIVA = "INACTIVA";
	
	public CampanaCriteria() {
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Campañas";
	}
	
	public CampanaCriteria( List listaResultados ){
		this.listaResultados =listaResultados;
	}
	
	public CampanaCriteria( Long idFiltroBusqueda ){
		this.idFiltroBusqueda = 0L;
		this.corporacion =idFiltroBusqueda;
	}
	
	public CampanaCriteria( String nombrePanel,Long idFiltroBusqueda ){
		//this.modelForm = model;
		this.nombrePanel = nombrePanel;
		this.idFiltroBusqueda = idFiltroBusqueda;
	}
	
	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Código");
		headers.add("Nombre");
		headers.add("Cliente");
		headers.add("Estado");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			CampanaIf bean = (CampanaIf) it.next();

			try {
				fila.add(bean.getCodigo());
				fila.add(bean.getNombre());
				ClienteIf cliente = GeneralUtil.verificarMapaCliente(mapaCliente,bean.getClienteId());
				fila.add(cliente != null?cliente.getNombreLegal():"");
				
				if(bean.getEstado().equals(NOMBRE_ESTADO_ACTIVA.substring(0,1)))
					fila.add(NOMBRE_ESTADO_ACTIVA);
				else if(bean.getEstado().equals(NOMBRE_ESTADO_FINALIZADA.substring(0,1)))
					fila.add(NOMBRE_ESTADO_FINALIZADA);
				else 
					fila.add(NOMBRE_ESTADO_INACTIVA);
				
				data.add(fila);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
		return data;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados =listaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	private Map buildQuery() {
		Map aMap  = new HashMap();
		if ( this.txtCodigo!=null && !("".equals(this.txtCodigo))) {
			aMap.put("codigo", this.txtCodigo+"%");
		}
		else
			aMap.put("codigo", "%");
		if ( this.txtNombre!=null && !("".equals(this.txtNombre))) {
			aMap.put("nombre", this.txtNombre+"%");
		}
		return aMap;
	}
	
	public void buscarRegistros(int startIndex, int endIndex) {
		try{
			if (idFiltroBusqueda == 0L) {
				if (queryBuilded.isEmpty()){
					listaResultados = (ArrayList) SessionServiceLocator.getCampanaSessionService()
						.getCampanaList(startIndex, endIndex);
				}
				else{
					if ( corporacion!=0L )
						listaResultados = (ArrayList) SessionServiceLocator.getCampanaSessionService()
							.findCampanaByQueryAndByCorporacionId(buildQuery(),
									corporacion, Parametros.getIdEmpresa());
					else
						listaResultados = (ArrayList) SessionServiceLocator.getCampanaSessionService()
							.getCampanaList(startIndex, endIndex, queryBuilded, Parametros.getIdEmpresa());
				}
			} else {
				if (queryBuilded.isEmpty()){
					Map filtroMap = new HashMap();
					filtroMap.put("clienteId", idFiltroBusqueda);
					listaResultados = (ArrayList) SessionServiceLocator.getCampanaSessionService()
						.getCampanaList(startIndex, endIndex, filtroMap, Parametros.getIdEmpresa());
				}
				else{
					queryBuilded.put("clienteId", idFiltroBusqueda);
					listaResultados = (ArrayList) SessionServiceLocator.getCampanaSessionService()
						.getCampanaList(startIndex, endIndex, queryBuilded, Parametros.getIdEmpresa());
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public int getResultListSize() {
		try{
			if (idFiltroBusqueda == 0L) {
				if (queryBuilded.isEmpty()){
					tamanoListaResultados = SessionServiceLocator.getCampanaSessionService().getCampanaListSize();
				}
				else{
					tamanoListaResultados = SessionServiceLocator.getCampanaSessionService()
						.getCampanaListSize(queryBuilded, Parametros.getIdEmpresa());
				}
			} else {
				if (queryBuilded.isEmpty()){
					Map filtroMap = new HashMap();
					filtroMap.put("clienteId", idFiltroBusqueda);
					tamanoListaResultados = SessionServiceLocator.getCampanaSessionService()
						.getCampanaListSize(filtroMap, Parametros.getIdEmpresa());
				}
				else{
					queryBuilded.put("clienteId", idFiltroBusqueda);
					tamanoListaResultados = SessionServiceLocator.getCampanaSessionService()
						.getCampanaListSize(queryBuilded, Parametros.getIdEmpresa());
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	public void setTxtParametros(String paramtero1, String paramtero2,String parametro3) {
		this.txtCodigo = paramtero1;
		this.txtNombre = paramtero2;
		queryBuilded = buildQuery();
	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
	 
}
