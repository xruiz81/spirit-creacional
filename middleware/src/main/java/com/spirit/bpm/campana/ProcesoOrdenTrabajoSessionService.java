package com.spirit.bpm.campana;

import com.spirit.bpm.impl.SpiritServerProcessIf;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PresupuestoIf;

public interface ProcesoOrdenTrabajoSessionService extends SpiritServerProcessIf{

	public void procesarOrdenTrabajo(OrdenTrabajoIf ordenTrabajoIf,boolean esActualizacion) throws GenericBusinessException,
			BPMException;
	
	public void procesarPresupuesto(PresupuestoIf presupuestoIf,ClienteOficinaIf clienteOficina,Tarea tarea) throws GenericBusinessException,
	BPMException, Exception;
	
	public void procesarPedido(PedidoIf pedidoIf,boolean generaFactura,Tarea tarea) throws GenericBusinessException,
			BPMException;
}
