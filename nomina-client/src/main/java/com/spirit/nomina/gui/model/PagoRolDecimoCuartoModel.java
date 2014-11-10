package com.spirit.nomina.gui.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.nomina.gui.panel.JPPagoRolDecimoCuarto;
import com.spirit.nomina.gui.util.ModelUtil;
import com.spirit.util.Utilitarios;


public class PagoRolDecimoCuartoModel extends JPPagoRolDecimoCuarto {

	private static final String TODOS = "TODOS";
	private Map<String, CuentaBancariaIf> cuentaBancariaMap = null;
	private Map<Long, String> cuentaBancariaLongMap = null;
	private Map<String, TipoPagoIf> tipoPagoMap = null;
	private Map<Long, TipoPagoIf> tipoPagoLongMap = null;
	
	
	public PagoRolDecimoCuartoModel(){
		cargarTipoPagos();
		cargarCuentasBancarias();
		initKeyListeners();		
		cargarComboOficina();
		cargarComboTipoPago(getCmbTipoPagoTodos());
		cargarComboCuentaBancaria(getCmbCuentaBancariaTodos());
		showSaveMode();
	}
	
	public void initKeyListeners() {
		getCmbMesInicio().setLocale(Utilitarios.esLocale);
		getCmbMesInicio().setShowNoneButton(false);
		getCmbMesInicio().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbMesInicio().setEditable(false);
		getCmbMesFin().setLocale(Utilitarios.esLocale);
		getCmbMesFin().setShowNoneButton(false);
		getCmbMesFin().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbMesFin().setEditable(false);
		//new HotKeyComponent(this);
	}
	
	private void cargarComboTipoPago(JComboBox comboTipoPago){
		comboTipoPago.setModel(new DefaultComboBoxModel());
		llenarComboTipoPagoTodos(comboTipoPago);
	}
	
	private void cargarComboCuentaBancaria(JComboBox comboCuentasBancarias){
		comboCuentasBancarias.setModel(new DefaultComboBoxModel());
		llenarComboCuentaBancariaTodos(comboCuentasBancarias);
	}

	private JComboBox llenarComboCuentaBancariaTodos(JComboBox comboCuentasBancarias) {
		for ( String cuenta : cuentaBancariaMap.keySet() ){
			comboCuentasBancarias.addItem(cuenta);
		}
		//if ( comboCuentasBancarias.getModel().getSize() > 0 )
		//	comboCuentasBancarias.setSelectedIndex(0);
		return comboCuentasBancarias;
	}

	private JComboBox llenarComboTipoPagoTodos(JComboBox comboTipoPago) {
		for (String tipoPagoNombre : tipoPagoMap.keySet()){
			TipoPagoIf tipoPago = tipoPagoMap.get(tipoPagoNombre);
			comboTipoPago.addItem(tipoPago);
		}
		return comboTipoPago;
	}

	private void cargarCuentasBancarias(){
		try {
			cuentaBancariaMap = null;
			cuentaBancariaMap = new HashMap<String, CuentaBancariaIf>();
			cuentaBancariaLongMap = null;
			cuentaBancariaLongMap = new HashMap<Long, String>();
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			parameterMap.put("cuentaCliente", "N");
			Collection<CuentaBancariaIf> cuentasBancarias = SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByQuery(parameterMap);
			for ( CuentaBancariaIf cuentaBancaria : cuentasBancarias ){
				BancoIf banco = SessionServiceLocator.getBancoSessionService().getBanco(cuentaBancaria.getBancoId());
				cuentaBancariaMap.put(ModelUtil.nombreCuentaBanco(banco, cuentaBancaria), cuentaBancaria);
				cuentaBancariaLongMap.put(cuentaBancaria.getId(), ModelUtil.nombreCuentaBanco(banco, cuentaBancaria));
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la carga de Cuentas Bancarias !!", SpiritAlert.ERROR);
		}
	}

	private void cargarTipoPagos(){
		try {
			tipoPagoMap = null;
			tipoPagoMap =  new HashMap<String, TipoPagoIf>();
			tipoPagoLongMap = null;
			tipoPagoLongMap =  new HashMap<Long, TipoPagoIf>();
			Collection<TipoPagoIf> tipos = SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByEmpresaId(Parametros.getIdEmpresa());
			for ( TipoPagoIf tipo : tipos ){
				if ( tipo.getNombre().contains("DEBITO") || tipo.getNombre().contains("DÉBITO") )
					tipoPagoMap.put(tipo.getNombre(), tipo);
				else if ( tipo.getNombre().contains("CHEQUE") )
					tipoPagoMap.put(tipo.getNombre(), tipo);
				tipoPagoLongMap.put(tipo.getId(), tipo);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error en la carga de tipos de pagos !!", SpiritAlert.ERROR);
		}
	}
	
	private void cargarComboOficina(){
		try {
			List oficinas = (ArrayList)SessionServiceLocator.getOficinaSessionService().findOficinaByEmpresaId(Parametros.getIdEmpresa());
			oficinas.add(TODOS);
			refreshCombo(getCmbOficina(),oficinas);
			getCmbOficina().setSelectedItem(TODOS);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void deleteDetail() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showSaveMode() {
		setSaveMode();
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
}
