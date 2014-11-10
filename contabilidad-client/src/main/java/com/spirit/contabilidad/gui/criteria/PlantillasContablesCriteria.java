package com.spirit.contabilidad.gui.criteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.entity.PlantillaIf;
import com.spirit.exception.GenericBusinessException;

public class PlantillasContablesCriteria extends Criteria{

	private CuentaIf cuentaIf;
	Long planCuenta=null,eventoContable=null;
	Map queryBuilded;
	
	public PlantillasContablesCriteria(){
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "";
	}
	
	public PlantillasContablesCriteria( List listaResultados ){
		this.listaResultados =listaResultados;
	}
	
	public PlantillasContablesCriteria( Long planCuenta, Long eventoContable ){
		this.eventoContable = eventoContable;
		this.planCuenta = planCuenta;
	}
	
	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}

	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Evento Contable");
		headers.add("Cuenta");
		headers.add("Debe/Haber");
		headers.add("Referencia");
		headers.add("Glosa");
		return headers;
	}

	public List armarModel(List lista){
		ArrayList data = new ArrayList();
		Iterator it = lista.iterator();
		EventoContableIf eventoContableIf = null;
		
		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			PlantillaIf bean = (PlantillaIf) it.next();

			try {
				cuentaIf = SessionServiceLocator.getCuentaSessionService().getCuenta(bean.getCuentaId());
				eventoContableIf = SessionServiceLocator.getEventoContableSessionService().getEventoContable(bean.getEventocontableId());
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
			fila.add(eventoContableIf.getNombre());
			fila.add(cuentaIf.getNombre());
			if(bean.getDebehaber().equals("D"))
				fila.add("Debe");
			else if(bean.getDebehaber().equals("H"))
				fila.add("Haber");
			if (bean.getReferencia() != null) 
				fila.add(bean.getReferencia());
			else fila.add("");
			if (bean.getGlosa() != null) 
				fila.add(bean.getGlosa());
			else fila.add("");
			data.add(fila);
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
		try{
			if(eventoContable != null && planCuenta != null)
				listaResultados = (ArrayList) SessionServiceLocator.getPlantillaSessionService()
					.getPlantillaByEventoContableIdAndByPlanCuentaIdList(startIndex,endIndex,eventoContable,planCuenta);
			else if(eventoContable != null)
				listaResultados = (ArrayList) SessionServiceLocator.getPlantillaSessionService()
					.getPlantillaByEventocontableIdList(startIndex,endIndex,eventoContable);
			else if(planCuenta != null)
				listaResultados = (ArrayList) SessionServiceLocator.getPlantillaSessionService()
					.getPlantillaByPlanCuentaIdList(startIndex,endIndex,planCuenta);
		}
		catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public int getResultListSize() {
		return tamanoListaResultados;
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
