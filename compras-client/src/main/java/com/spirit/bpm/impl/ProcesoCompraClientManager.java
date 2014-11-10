/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spirit.bpm.impl;

import com.spirit.bpm.compras.ProcesoCompraSessionService;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.client.model.SpiritModel;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.compras.gui.model.CompraModel;
import com.spirit.compras.gui.model.OrdenCompraModel;
import com.spirit.compras.gui.model.SolicitudCompraModel;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.PanelHandler;

/**
 * 
 * @author Administrador
 */
public class ProcesoCompraClientManager extends SpiritClientProcess {
	private static final long serialVersionUID = 1L;

	@Override
	public ProcesoCompraSessionService getRemoteProcess() {
		return SessionServiceLocator.getProcesoCompraSessionService();
	}
	
	@Override
	public void openTarea(Tarea tarea) throws BPMException {
		SpiritModel model = null;
		try {
			if (tarea.getNombre().equalsIgnoreCase(TAREA_SOLICITUD_COMPRA)) {
				model = new SolicitudCompraModel();
			} else if (tarea.getNombre().equalsIgnoreCase(TAREA_ORDEN)) {
				Long idSolicitudCompra = (Long) getSpiritBPMConnector()
						.getValue(getUserParams(), tarea,
								SOLICITUD_COMPRA_ID_PARAM);
				OrdenCompraModel ordenCompraModel = null;
				if (idSolicitudCompra != null) {

					SolicitudCompraIf solicitudCompraIf = SessionServiceLocator
							.getSolicitudCompraSessionService()
							.getSolicitudCompra(idSolicitudCompra);
					ordenCompraModel = new OrdenCompraModel(solicitudCompraIf,
							0l);

				} else {
					ordenCompraModel = new OrdenCompraModel();
				}
				ordenCompraModel.setTarea(tarea);
				model = ordenCompraModel;
			} else if (tarea.getNombre().equalsIgnoreCase(TAREA_COMPRA)) {
				Long idOrdenCompra = (Long) getSpiritBPMConnector().getValue(
						getUserParams(), tarea, ORDEN_COMPRA_ID_PARAM);
				CompraModel compraModel = null;
				if (idOrdenCompra != null) {
					OrdenCompraIf ordenCompraIf = SessionServiceLocator
							.getOrdenCompraSessionService().getOrdenCompra(
									idOrdenCompra);
					compraModel = new CompraModel(ordenCompraIf, 0l);
				} else {
					compraModel = new CompraModel();
				}
				compraModel.setTarea(tarea);
				model = compraModel;
			}
			PanelHandler.showPanelModel(model);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void iniciarTarea(Tarea tarea) throws BPMException {
		getSpiritBPMConnector().iniciarTarea(getUserParams(), tarea);
		openTarea(tarea);
	}

	@Override
	public void continuarTarea(Tarea tarea) throws BPMException {
		// TODO Auto-generated method stub
		
	}

}
