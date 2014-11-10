package com.spirit.bpm.compras;

import com.spirit.bpm.impl.SpiritServerProcessIf;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.ClientParams;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.exception.GenericBusinessException;

public interface ProcesoCompraSessionService extends SpiritServerProcessIf{

	public void procesarSolicitudCompra(ClientParams clientParams,
			SolicitudCompraIf solicitudCompraParamIf) throws BPMException,
			GenericBusinessException;

	public void procesarOrdenCompra(ClientParams clientParams, Tarea tarea,
			OrdenCompraIf ordenCompraIf) throws BPMException;

	public void procesarOrdenCompra(ClientParams clientParams,
			OrdenCompraIf ordenCompraIf) throws BPMException;
}
