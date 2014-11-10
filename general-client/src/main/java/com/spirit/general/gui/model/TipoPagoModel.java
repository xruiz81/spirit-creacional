package com.spirit.general.gui.model;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.session.TipoPagoSessionService;
import com.spirit.servicelocator.ServiceLocator;

public class TipoPagoModel {
	public TipoPagoModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get the service interface.
	 * 
	 * @return TipoPagoSessionService interface
	 */

	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public static TipoPagoSessionService getTipoPagoSessionService() {
		try {
			return (TipoPagoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOPAGOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicacin con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
}
