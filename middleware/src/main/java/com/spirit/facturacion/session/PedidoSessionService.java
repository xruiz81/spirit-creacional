package com.spirit.facturacion.session;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.bpm.process.elements.Tarea;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.PedidoDetalleEJB;
import com.spirit.facturacion.entity.PedidoDetalleIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.session.generated._PedidoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PedidoSessionService extends _PedidoSessionService{

	Collection getPedidoList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int getPedidoListSize(Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public Long procesarPedido(PedidoIf model,List<PedidoDetalleIf> modelDetalleList,Long idEmpresa,Tarea tarea) throws GenericBusinessException;
	public PedidoIf actualizarPedido(PedidoIf model, List<PedidoDetalleIf> pedidoDetalleList, PedidoIf pedidoAnterior, List<PedidoDetalleIf> pedidoDetalleEliminadosList,Tarea tarea) throws GenericBusinessException;
	public PedidoIf registrarPedido(PedidoIf pedido) throws GenericBusinessException;
	public PedidoDetalleEJB registrarPedidoDetalle(PedidoDetalleIf pedidoDetalle) throws GenericBusinessException;
}
