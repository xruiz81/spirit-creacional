package com.spirit.compras.session;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.compras.entity.OrdenCompraDetalleIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.session.generated._OrdenCompraSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OrdenCompraSessionService extends _OrdenCompraSessionService{

	Collection getOrdenCompraByQueryList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa, Boolean compra) throws com.spirit.exception.GenericBusinessException;
	//ADD 15 JULIO //FALTA X REALIZAR COMENTED 18 JULIO
	//Collection getOrdenMedioByQueryList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa, Boolean medio) throws com.spirit.exception.GenericBusinessException;
	
	int getOrdenCompraByQueryListSize(Map aMap, java.lang.Long idEmpresa, boolean ordenCompra) throws com.spirit.exception.GenericBusinessException;
		
	public java.util.Collection findOrdenCompraByQuery(Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	//ADD 15 JULIO COMENTED 18 JULIO
	//public java.util.Collection findOrdenMedioByQuery(Map aMap, Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	
	public java.util.Collection getOrdenCompraByMapList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa);
	public int getOrdenCompraByMapSize(Map aMap, java.lang.Long idEmpresa);
	public OrdenCompraIf procesarOrdenCompra(OrdenCompraIf model,List<OrdenCompraDetalleIf> modelDetalleList,Long idEmpresa,long idTarea) throws GenericBusinessException;
	public OrdenCompraIf procesarOrdenCompraRevision(OrdenCompraIf model,List<OrdenCompraDetalleIf> modelDetalleList) throws GenericBusinessException;
	public OrdenCompraIf actualizarOrdenCompra(OrdenCompraIf model,List<OrdenCompraDetalleIf> modelDetalleList,List<OrdenCompraDetalleIf> modelDetalleRemovidoList,long idTarea)throws GenericBusinessException;
	public void eliminarOrdenCompra(Long ordenCompraId) throws GenericBusinessException;
	//public void autorizarOrdenCompra(boolean autorizar,Long idTarea) throws GenericBusinessException;
	public Collection findOrdenCompraByFechaInicioByFechaFinByOficinaIdAndByProveedorId(Date fechaInicio, Date fechaFin, Long idOficina, Long idProveedor) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findOrdenCompraByPresupuestoId(Long presupuestoId) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findPurchaseOrders(Long providerId) throws GenericBusinessException;
	public int findOrdenCompraByQueryByPresupuestoIdAndByEstadosSize(Map aMap, Long presupuestoId, String[] estados) throws GenericBusinessException;
	public java.util.Collection findOrdenCompraByQueryByPresupuestoIdAndByEstados(int startIndex, int endIndex, Map aMap, Long presupuestoId, String[] estados) throws GenericBusinessException;
}
