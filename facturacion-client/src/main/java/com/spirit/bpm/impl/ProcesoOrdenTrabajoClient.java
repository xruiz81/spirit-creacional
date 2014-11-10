package com.spirit.bpm.impl;

import com.spirit.bpm.campana.ProcesoOrdenTrabajoSessionService;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModel;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.gui.model.PedidoModel;
import com.spirit.general.gui.controller.PanelHandler;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.gui.model.PresupuestoModel;

public class ProcesoOrdenTrabajoClient extends SpiritClientProcess {
	private static final long serialVersionUID = 1L;

	@Override
	public ProcesoOrdenTrabajoSessionService getRemoteProcess() {
		return SessionServiceLocator.getProcesoOrdenTrabajoSessionService();
	}

	@Override
	public void iniciarTarea(Tarea tarea) throws BPMException {
		getSpiritBPMConnector().iniciarTarea(getUserParams(), tarea);
		openTarea(tarea);
	}

	@Override
	public void openTarea(Tarea tarea) throws BPMException {
		SpiritModel model = null;
		try {
			if (tarea.getNombre().equalsIgnoreCase(TAREA_PRESUPUESTO)) {
				model = abrirPrespuestoModel(tarea);
			} else if (tarea.getNombre().equalsIgnoreCase(TAREA_PEDIDO)) {
				model = abrirPedidoModel(tarea);
			}
			if ( model != null )
				PanelHandler.showPanelModel(model);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void continuarTarea(Tarea tarea) throws BPMException {
		SpiritModel model = null;
		try {
			if (tarea.getNombre().equalsIgnoreCase(TAREA_PRESUPUESTO)) {
				model = abrirPrespuestoModel(tarea);
			} else if (tarea.getNombre().equalsIgnoreCase(TAREA_PEDIDO)) {
				model = abrirPedidoModel(tarea);
			}
			PanelHandler.showPanelModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private SpiritModel abrirPrespuestoModel(Tarea tarea) throws BPMException,
	GenericBusinessException {
		SpiritModel model = null;
		Object presupuestoIdObjeto = getSpiritBPMConnector()
			.getValueFromInstance(getUserParams(), tarea, "presupuestoId");
		if ( presupuestoIdObjeto == null ){
			model = abrirPresupuestoModelDesdeOrdenDetalle(tarea);
		} else {
			Long presupuestoId = (Long) presupuestoIdObjeto;
			PresupuestoIf presupuesto = SessionServiceLocator.getPresupuestoSessionService()
				.getPresupuesto(presupuestoId);
			if ( presupuesto != null ){
				PresupuestoModel p = new PresupuestoModel(presupuesto);
				p.setTarea(tarea);
				model = p;
			} else {
				SpiritAlert.createAlert("Presupuesto guardado ha sido eliminado, se creará uno nuevo !!", SpiritAlert.WARNING);
				model = abrirPresupuestoModelDesdeOrdenDetalle(tarea);
			}
		}
		return model;
	}

	private SpiritModel abrirPresupuestoModelDesdeOrdenDetalle(Tarea tarea)
			throws BPMException, GenericBusinessException {
		SpiritModel model;
		String values = (String) getSpiritBPMConnector()
			.getValueFromInstance(getUserParams(), tarea, "values");
		Long idDetalleOrdenTrabajo = Long.valueOf(values.split(",")[0]);
		OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = SessionServiceLocator
			.getOrdenTrabajoDetalleSessionService()
				.getOrdenTrabajoDetalle(idDetalleOrdenTrabajo);
		PresupuestoModel p = new PresupuestoModel(ordenTrabajoDetalleIf);
		p.setTarea(tarea);
		model = p;
		return model;
	}
	
	private SpiritModel abrirPedidoModel(Tarea tarea)
	throws BPMException, GenericBusinessException {
		SpiritModel model = null;
		Object pedidoIdObjeto = getSpiritBPMConnector().getValueFromInstance(getUserParams(), tarea, "pedidoId");
		if (pedidoIdObjeto == null){
			model = abrirPedidoModelDesdePresupuesto(tarea, model);
		} else {
			Long pedidoId = (Long) pedidoIdObjeto;
			PedidoIf pedido = SessionServiceLocator.getPedidoSessionService().getPedido(pedidoId);
			if ( pedido != null ){
				PedidoModel pedidoModel = new PedidoModel(pedido);
				pedidoModel.setTarea(tarea);
				model = pedidoModel;
			} else {
				SpiritAlert.createAlert("Pedido guardado ha sido eliminado, se creará uno nuevo !!", SpiritAlert.WARNING);
				model = abrirPedidoModelDesdePresupuesto(tarea, model);
			}
		}
		return model;
	}

	private SpiritModel abrirPedidoModelDesdePresupuesto(Tarea tarea,
			SpiritModel model) throws BPMException, GenericBusinessException {
		Object objeto = getSpiritBPMConnector().getValueFromInstance(getUserParams(), tarea, "presupuestoId");
		if ( objeto != null && objeto instanceof Long ){
			Long presupuestoId = (Long) objeto;
			PresupuestoIf presupuesto = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoId);
			PedidoModel pedidoModel = new PedidoModel(presupuesto);
			pedidoModel.setTarea(tarea);
			model = pedidoModel;
		}
		return model;
	}

}
