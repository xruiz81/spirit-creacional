package com.spirit.general.gui.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.util.GeneralUtil;

public class NuevoCuentaBancariaCriteria extends Criteria {
	
	Map queryBuilded;
	Map<Long,BancoIf> mapaBanco = new HashMap<Long, BancoIf>();
	
	public NuevoCuentaBancariaCriteria(String nombrePanel) {
		this.nombrePanel = nombrePanel;
	}
	
	public void setNombrePanel() {
		this.nombrePanel = "de Cuentas Bancarias";
	}
	
	public NuevoCuentaBancariaCriteria(List listaResultados) {
		this.listaResultados = listaResultados;
	}
	
	public List getHeaders() {
		ArrayList headers = new ArrayList();
		headers.add("Banco");
		headers.add("Cuenta");
		headers.add("Tipo de Cuenta");
		return headers;
	}
	
	public void setResultList(List listaResultados) {
		this.listaResultados = listaResultados;
	}
	
	public List armarModel(List lista) {
		ArrayList data = new ArrayList();
		Iterator it = listaResultados.iterator();
		
		try {
			while (it.hasNext()) {
				ArrayList fila = new ArrayList();
				CuentaBancariaIf bean = (CuentaBancariaIf) it.next();
				BancoIf banco = GeneralUtil.verificarMapaBanco(mapaBanco, bean.getBancoId());
				fila.add(banco.getNombre());
				fila.add(bean.getCuenta());
				if (bean.getTipocuenta().equals("C"))
					fila.add("CORRIENTE");
				else if (bean.getTipocuenta().equals("A"))
					fila.add("AHORROS");
				data.add(fila);
			}
		} catch(GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return data;
	}
	
	public void buscarRegistros(int startIndex, int endIndex) {
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			parameterMap.put("cuentaCliente", this.queryBuilded.get("cuentaCliente"));
			listaResultados = (ArrayList) SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByQuery(parameterMap);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public int getResultListSize() {
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			parameterMap.put("cuentaCliente", this.queryBuilded.get("cuentaCliente"));
			tamanoListaResultados = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancariaByQueryListSize(parameterMap);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return tamanoListaResultados;
	}
	
	public List getListaResultados() {
		return listaResultados;
	}
	
	public void setTxtParametros(String parametro1, String parametro2,
			String parametro3) {
	}
	
	public void setResultListSize(int tamanoLista) {
		this.tamanoListaResultados = tamanoLista;
	}
	
	public void setQueryBuilded(Map queryBuilded) {
		this.queryBuilded = queryBuilded;
	}
	 
}
