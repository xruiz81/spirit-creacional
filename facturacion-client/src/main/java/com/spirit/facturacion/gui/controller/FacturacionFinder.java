package com.spirit.facturacion.gui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.gui.model.ListaPrecioModel;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * 
 * @author C. Briones
 * 
 * Permite encontrar objetos con cache del módulo de medios
 *
 */

public class FacturacionFinder {
	
	private static Logger log = LogService.getLogger(FacturacionFinder.class);
	
	public static List findPrecio(Long idListaPrecio, Long idProducto) {
		List precio;
		precio = SpiritCache.findObject("precio");
	
		if (precio == null) {
			try {
				Map aMap = new HashMap();
				
					aMap.put("listaprecioId", idListaPrecio);
					aMap.put("productoId", idProducto);
				
					precio = (List) SessionServiceLocator
						.getPrecioSessionService().findPrecioByQuery(aMap);
				SpiritCache.setObject("precio", precio);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return precio;
		}
		return precio;
	}
	
	public static List findPedido(String fechaPedido, Long clienteOficinaId) {
		List pedido;
		pedido = SpiritCache.findObject("pedido");
	
		if (pedido == null) {
			try {
				Map aMap = new HashMap();
				
					aMap.put("fechaPedido", fechaPedido);
					aMap.put("clienteoficinaId", clienteOficinaId);
				
					pedido = (List) SessionServiceLocator.getPedidoSessionService().findPedidoByQuery(aMap);
				SpiritCache.setObject("pedido", pedido);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return pedido;
		}
		return pedido;
	}

}