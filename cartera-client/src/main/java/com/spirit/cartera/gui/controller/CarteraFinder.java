package com.spirit.cartera.gui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.model.CuentasBancariasModel;
/**
 * 
 * @author xruiz
 * 
 * Permite encontrar objetos con cache del modulo de contabilidad
 *
 */
public class CarteraFinder {
	//private static Logger log = LogService.getLogger(CarteraFinder.class);
	
	public static List findCuentaBancaria(Long idBanco, String tipoCuenta, String cuenta) {
		
		List cuentaBancaria;
		cuentaBancaria = SpiritCache.findObject("cuentaBancaria");
	
		if (cuentaBancaria == null) {
			try {
				Map aMap = new HashMap();
				
					aMap.put("bancoId", idBanco);
					aMap.put("tipocuenta", tipoCuenta);
					aMap.put("cuenta", cuenta);
				
					cuentaBancaria = (List) SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByQuery(aMap);
				
					SpiritCache.setObject("cuentaBancaria", cuentaBancaria);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return cuentaBancaria;
		}
		return cuentaBancaria;
	}
	
}
