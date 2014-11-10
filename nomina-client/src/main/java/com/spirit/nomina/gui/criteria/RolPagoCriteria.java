package com.spirit.nomina.gui.criteria;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModel;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.PopupFinderActionListener;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.gui.util.EstadoRolPago;

public class RolPagoCriteria extends Criteria {

	Map queryBuilded;
	String nombrePanel = null;

	EstadoRolPago estadoRolPago = null;

	String mes = null;
	String anio = null;
	TipoRolIf tipoRolIf = null;

	public RolPagoCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Roles de Pago";
	}

	public RolPagoCriteria(String nombrePanel){
		this.nombrePanel = nombrePanel;
	}

	private PopupFinderActionListener alRolPago =  new PopupFinderActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			TipoRolCriteria trc = new TipoRolCriteria(" de Tipo de Rol");
			JDPopupFinderModel popupModel =  new JDPopupFinderModel(Parametros.getMainFrame(),trc,JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if ( popupModel.getElementoSeleccionado() != null ){
				tipoRolIf = null;
				tipoRolIf = (TipoRolIf) popupModel.getElementoSeleccionado();
				super.getTxtField().setText(tipoRolIf.getNombre());
			}
		}
	};

	public List getHeaders() {
		ArrayList<Object> headers = new ArrayList<Object>();
		headers.add("Mes");
		headers.add("Anio");
		headers.add(new Object[]{"Tipo de Rol",alRolPago});
		return headers;
	}

	@Override
	public List getHeadersString() {
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Mes");
		headers.add("Anio");
		headers.add("Tipo de Rol");
		return headers;
	}

	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = listaResultados.iterator();
		while (it.hasNext()) {
			try {
				ArrayList fila = new ArrayList();
				RolPagoIf bean = (RolPagoIf) it.next();

				fila.add(bean.getMes());
				fila.add(bean.getAnio());
				TipoRolIf tipoRol = SessionServiceLocator.getTipoRolSessionService().getTipoRol(bean.getTiporolId());
				fila.add(tipoRol.getNombre());

				data.add(fila);

			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
		return data;
	}

	public void buscarRegistros(int startIndex,int endIndex){
		try{
			listaResultados = (ArrayList) SessionServiceLocator.getRolPagoSessionService()
			.findRolPagoByQuery(startIndex,endIndex,queryBuilded);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		try{
			tamanoListaResultados = SessionServiceLocator.getRolPagoSessionService().getRolPagoListSize(queryBuilded);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}

	public List getListaResultados() {
		return listaResultados;
	}

	public void setTxtParametros(String parametro1, String parametro2, String parametro3) {
		this.mes = parametro1;
		this.anio = parametro2;
		buildQuery();
	}

	private void buildQuery(){
		queryBuilded = new HashMap<String, Object>();
		if ( mes != null &&!"".equals(mes) ){
			queryBuilded.put("mes", mes);
		}
		if ( anio != null &&!"".equals(anio) ){
			queryBuilded.put("anio", anio);
		}
		if ( tipoRolIf != null ){
			queryBuilded.put("tiporolId", tipoRolIf.getId());
		}

	}

	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}

	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}

}
