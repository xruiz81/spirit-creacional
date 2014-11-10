package com.spirit.medios.gui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.gui.model.OrdenMedioModel;
import com.spirit.medios.gui.model.PresupuestoModel;
import com.spirit.medios.handler.EstadoPresupuesto;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * 
 * @author C. Briones
 * 
 * Permite encontrar objetos con cache del módulo de medios
 *
 */

public class MediosFinder {
	
	private static Logger log = LogService.getLogger(MediosFinder.class);
	
	public static List findOrdenMedio(Long idClienteOficina) {
		List ordenmedio;
		ordenmedio = SpiritCache.findObject("ordenmedio");
	
		if (ordenmedio == null) {
			try {
				Map aMap = new HashMap();
				
					aMap.put("clienteoficinaId", idClienteOficina);
					aMap.put("estado", /*OrdenMedioModel.ESTADO_ENVIADO*/"");
				
					ordenmedio = (List) SessionServiceLocator
						.getOrdenMedioSessionService().findOrdenMedioByQuery(aMap);
				SpiritCache.setObject("ordenmedio", ordenmedio);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return ordenmedio;
		}
		return ordenmedio;
	}
	
	public static List findOrdenMedio(String referencia) {
		List ordenmedio;
		ordenmedio = SpiritCache.findObject("ordenmedio");
	
		if (ordenmedio == null) {
			try {
				Map aMap = new HashMap();
				
					aMap.put("codigo", referencia);
				
					ordenmedio = (List) SessionServiceLocator
						.getOrdenMedioSessionService().findOrdenMedioByQuery(aMap);
				SpiritCache.setObject("ordenmedio", ordenmedio);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return ordenmedio;
		}
		return ordenmedio;
	}	
	
	public static List findPresupuesto(Long idProveedor) {
		List presupuesto;
		presupuesto = SpiritCache.findObject("presupuesto");
	
		if (presupuesto == null) {
			try {
				Map aMap = new HashMap();
				
					aMap.put("proveedorId", idProveedor);
					//aMap.put("estado", PresupuestoModel.ESTADO_APROBADO);
					aMap.put("estado", EstadoPresupuesto.APROBADO.getLetra());
				
					presupuesto = (List) SessionServiceLocator
						.getPresupuestoSessionService().findPresupuestoByQuery(aMap);
				SpiritCache.setObject("presupuesto", presupuesto);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return presupuesto;
		}
		return presupuesto;
	}

	public static List findProductoClienteByCliente(Long idCliente) {
		List productosClienteByCliente;
		productosClienteByCliente = SpiritCache.findObject("productosClienteByCliente");
	
		if (productosClienteByCliente == null) {
			try {
				productosClienteByCliente = (List) SessionServiceLocator.getProductoClienteSessionService().findProductoClienteByClienteId(idCliente);
				SpiritCache.setObject("productosClienteByCliente",productosClienteByCliente);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return productosClienteByCliente;
		}
		return productosClienteByCliente;
	}

	public static List findProductoCliente() {
		List productocliente;
		productocliente = SpiritCache.findObject("producto cliente");
	
		if (productocliente == null) {
			try {
				productocliente = (List) SessionServiceLocator.getProductoClienteSessionService().getProductoClienteList();
				SpiritCache.setObject("producto cliente", productocliente);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return productocliente;
		}
		return productocliente;
	}
}
