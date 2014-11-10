package com.spirit.crm.gui.controller;

import java.util.List;

import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.session.ClienteSessionService;
import com.spirit.crm.session.CorporacionSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.servicelocator.ServiceLocator;

public class CrmFinder {

	public static List findClientes() {
		List clientes;
		clientes = SpiritCache.findObject("clientes");
	
		if (clientes == null) {
			try {
				clientes = (List) SessionServiceLocator.getClienteSessionService().getClienteList();
				SpiritCache.setObject("clientes", clientes);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return clientes;
		}
		return clientes;
	}

	public static List findCorporacionesByEmpresa(Long idEmpresa) {
		List corporacionesByEmpresas;
		corporacionesByEmpresas = SpiritCache.findObject("corporacionesByEmpresas");
	
		if (corporacionesByEmpresas == null) {
			try {
				corporacionesByEmpresas = (List) SessionServiceLocator.getCorporacionSessionService().findCorporacionByEmpresaId(idEmpresa);
				SpiritCache.setObject("corporaciones", corporacionesByEmpresas);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return corporacionesByEmpresas;
		}
		return corporacionesByEmpresas;
	}

	public static List findClientesByCorporacion(Long idCoorporacion) {
		List clientesByCorporacion;
		clientesByCorporacion = SpiritCache.findObject("clientesByProveedor");
	
		if (clientesByCorporacion == null) {
			try {
				clientesByCorporacion = (List) SessionServiceLocator.getClienteSessionService().findClienteByCorporacionId(idCoorporacion);
				SpiritCache.setObject("clienteOficinas", clientesByCorporacion);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		} else {
			return clientesByCorporacion;
		}
		return clientesByCorporacion;
	}
	 
}
