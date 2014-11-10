package com.spirit.medios.session;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.spirit.bpm.process.elements.Tarea;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PresupuestoArchivoIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.generated._PresupuestoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PresupuestoSessionService extends _PresupuestoSessionService{

	public java.util.Collection findPresupuestoByQuery(Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public int findPresupuestoByQueryAndEmpresaIdListSize(Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public Collection findPresupuestoByQueryAndEmpresaId(int startIndex, int endIndex, Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findPresupuestoByQueryAndProveedorId(Map aMap, java.lang.Long idProveedor, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public Collection findPresupuestoByOrdenTrabajoId(java.lang.Long idOrden) throws com.spirit.exception.GenericBusinessException;
	public Collection findPresupuestoByClienteOficinaId(java.lang.Long idClienteOficina) throws com.spirit.exception.GenericBusinessException;
	public Collection findPresupuestoByClienteOficinaIdAndEstado(java.lang.Long idClienteOficina) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findPresupuestoByEmpresaId(java.lang.Long idEmpresa) throws GenericBusinessException;
	public java.util.Collection findPresupuestoByCodigoAndEmpresaId(String codigo, java.lang.Long idEmpresa) throws GenericBusinessException;
	public java.util.Collection findPresupuestoPresupuestoDetalleProductoClienteByFacturaIdByProveedorIdByProveedorOficinaId(Long idFactura, Long idProveedor, Long idProveedorOficina) throws GenericBusinessException;
	public java.util.Collection findPresupuestoDetalleByFacturaIdByProveedorIdByProveedorOficinaId(Long idFactura, Long idProveedor, Long idProveedorOficina) throws GenericBusinessException ;
	public java.util.Collection findPresupuestoDetalleByPedidoIdByProveedorIdByProveedorOficinaId(Long idPedido, Long idProveedor, Long idProveedorOficina) throws GenericBusinessException ;
	public java.util.Collection findPresupuestoProductoClienteByPresupuestoId(Long idPresupuesto) throws GenericBusinessException;
	public Collection findPresupuestoByFilteredQuery(int startIndex,int endIndex, Map aMap, java.lang.Long idCliente, java.lang.Long idClienteOficina, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public int findPresupuestoByFilteredQuerySize(Map aMap, java.lang.Long idCliente, java.lang.Long idClienteOficina, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public Collection findPresupuestoByCodigoByClienteByEmpresaAndByEstados(int startIndex,int endIndex,String codigo, Long idCliente, Long idClienteOficina, Long idEmpresa, String[] estados) throws com.spirit.exception.GenericBusinessException;
	public int findPresupuestoByCodigoByClienteByEmpresaAndByEstadosSize(String codigo, Long idCliente, Long idClienteOficina, Long idEmpresa, String[] estados) throws com.spirit.exception.GenericBusinessException;
	public Collection findPresupuestoByEmpresaIdByProveedorIdAndByEstados(int startIndex,int endIndex, Long idProveedor, Long idEmpresa, String[] estados) throws com.spirit.exception.GenericBusinessException;
	public int findPresupuestoByEmpresaIdByProveedorIdAndByEstadosSize(Long idProveedor, Long idEmpresa, String[] estados) throws com.spirit.exception.GenericBusinessException;
	public PresupuestoIf procesarPresupuesto(PresupuestoIf model, List<PresupuestoDetalleIf> modelDetalleList, List<PresupuestoDetalleIf> modelDetalleListP, OrdenTrabajoIf ordenTrabajo, OrdenTrabajoDetalleIf ordenTrabajoDetalle, List<ProductoClienteIf> modelProductoList, Tarea tarea) throws GenericBusinessException;
	public PresupuestoIf actualizarPresupuesto(PresupuestoIf model, List<PresupuestoDetalleIf> modelDetalleList, List<PresupuestoDetalleIf> modelDetalleListP, OrdenTrabajoIf ordenTrabajo, OrdenTrabajoDetalleIf ordenTrabajoDetalle, List<PresupuestoDetalleIf> presupuestoDetalleEliminadosList, List<ProductoClienteIf> productoClienteColeccion, Tarea tarea) throws GenericBusinessException;
	public void eliminarPresupuesto(Long presupuestoId,OrdenTrabajoDetalleIf ordenTrabajoDetalle) throws GenericBusinessException;
	public void actualizarArchivosPresupuesto(PresupuestoIf model, List<PresupuestoArchivoIf> modelArchivoList, List<PresupuestoArchivoIf> archivosEliminadosList,String urlCarpetaSevidor) throws GenericBusinessException;
	public Collection<PresupuestoIf> findPresupuestosByQueryByFechaInicioAndByFechaFin(Map aMap, Date fechaInicio, Date fechaFin) throws GenericBusinessException;
	public Collection findPresupuestoByOrdenCompraId(Long ordenCompraId) throws GenericBusinessException;
	public Collection findPresupuestosDetalleByQueryByProductoAndByFechas(Map<String,Long> mapaPresupuestoDetalle, Timestamp timeIncio, Timestamp timeFin, boolean fechaAprobacion, Long empresaId, String agruparBy, String estado, String tipoProveedor, boolean aprobadosFacturados, boolean buscarPresupuestosPrepago, String estadoOrden, boolean noMostrarOrdenesEmitidas) throws com.spirit.exception.GenericBusinessException;
	public Collection findPresupuestosDetalleByQueryByProductoAndByFechasSinOrdenes(Map<String,Long> mapaPresupuestoDetalle, Timestamp timeIncio, Timestamp timeFin, boolean fechaAprobacion, Long empresaId, String agruparBy, String estado, String tipoProveedor, boolean aprobadosFacturados, boolean buscarPresupuestosPrepago) throws com.spirit.exception.GenericBusinessException;
	public Collection findPresupuestoByPedido(Map<String,Object> queryMap) throws com.spirit.exception.GenericBusinessException;
	public Collection findPresupuestoDetalleByClienteByCodigoProductoProveedorByFechaInicioFechaFinAndByEstado(Long clienteId, Long clienteOficinaId, String codigoProductoProveedor, Timestamp fechaInicio, Timestamp fechaFin, String estado, boolean estadosAprobadosFacturados) throws com.spirit.exception.GenericBusinessException;
	public Collection findPresupuestoDetalleByClienteOficinaByOrdenIdNullByFechaInicioFechaFinAndByEstado(Long clienteOficinaId, Timestamp fechaInicio, Timestamp fechaFin, String estado, boolean estadosAprobadosFacturados) throws com.spirit.exception.GenericBusinessException;
	
   	public Collection findOrdenesClientesByPresupuestos(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin,Long idEmpresa, boolean ordenarPorFecha, Long creadoPorId, boolean ordenarPorCodigoOrden, boolean ordenarPorCodigoPresupuesto) throws GenericBusinessException;
	
   	
	public Map<String,Object> procesarPresupuestoServer(boolean generarOrdenesCompra,
			boolean eliminarOrdenesComprasPrevias,PresupuestoIf presupuesto, 
			List<PresupuestoDetalleIf> modelDetalleList, List<PresupuestoDetalleIf> modelDetalleListP, 
			OrdenTrabajoIf ordenTrabajo, OrdenTrabajoDetalleIf ordenTrabajoDetalle, 
			List<ProductoClienteIf> modelProductoList,Set<Integer> ordenesCompraParaModificar, 
			UsuarioIf usuario,Double IVA,String codigoMoneda,Long oficinaId, Tarea tarea) throws GenericBusinessException;
	
	public Map<String,Object> actualizarPresupuestoServer(boolean generarOrdenesCompra,//boolean modificarOrdenesComprasPrevias,
			PresupuestoIf presupuestoActualizado, List<PresupuestoDetalleIf> modelDetalleList, 
			List<PresupuestoDetalleIf> modelDetalleListP, OrdenTrabajoDetalleIf ordenTrabajoDetalle, 
			List<PresupuestoDetalleIf> presupuestoDetalleEliminadosList, 
			List<ProductoClienteIf> productoClienteColeccion,Set<Integer> ordenesCompraParaModificar, 
			UsuarioIf usuario,Double IVA,String codigoMoneda,Long oficinaId,Tarea tarea, boolean esActualizacion) throws GenericBusinessException;
	
}
