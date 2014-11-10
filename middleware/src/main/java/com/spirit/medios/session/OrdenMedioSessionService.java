package com.spirit.medios.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioDetalleMapaIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.session.generated._OrdenMedioSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OrdenMedioSessionService extends _OrdenMedioSessionService{

	public Collection findOrdenMedioByQuery(Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public int findOrdenMedioByQueryAndEmpresaIdListSize(Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public Collection findOrdenMedioByQueryAndEmpresaId(int startIndex, int endIndex, Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public Collection findOrdenMedioPlanMedioProductoClienteByPedidoIdByMedioIdByMedioOficinaId(Long idPedido, Long idMedio, Long idMedioOficina) throws GenericBusinessException;
	public Collection findOrdenMedioByQueryAndByClienteId(Map aMap, Long idCliente, Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	public Collection findOrdenMedioByQueryAndByClienteId(int startIndex,int endIndex,Map aMap, Long idCliente, Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	public int findOrdenMedioByQueryAndByClienteIdSize(Map aMap, Long idCliente, Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	public Collection findOrdenMedioByQueryClienteOficinaAndQueryProductoClienteAndByFechas(Map aMapOrdenMedio, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFinal, Long idEmpresa) throws GenericBusinessException;
	public Collection findOrdenMedioByQueryAndQueryGeneralByProductoClienteAndByFechas(Map aMapOrdenMedio, Map aMapGeneral, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFinal, Long idEmpresa) throws GenericBusinessException;
	public Collection findOrdenMedioDetalleByQueryAndQueryGeneralByProductoAndByFechas(Map aMapOrdenMedio, Map aMapGeneral, String pauta, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFinal, boolean fechaAprobacion, Long idEmpresa, String estado, boolean aprobadosFacturados, String estadoOrden, boolean noMostrarOrdenesEmitidas, boolean buscarPresupuestosPrepago) throws GenericBusinessException;
	public Collection findOrdenMedioByQueryAndQueryGeneralByCanalAndByFechas(Map aMapOrdenMedio, Map aMapGeneral, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFinal, Long idEmpresa) throws GenericBusinessException;	
	public Collection findOrdenMedioDetalleByQueryAndQueryGeneralByCanalAndByFechas(Map aMapOrdenMedio, Map aMapGeneral, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFinal, Long idEmpresa) throws GenericBusinessException;
	public Collection findOrdenMedioByQueryAndByFechas(Map aMap, String fechaInicio, String fechaFinal, Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	public Collection findOrdenMedioByQueryAndByFechas(int startIndex,int endIndex,Map aMap, String fechaInicio, String fechaFinal, Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	public int findOrdenMedioByQueryAndByFechasSize(Map aMap, String fechaInicio, String fechaFinal, Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	public Collection findOrdenMedioByQueryAndByFechasAndByClienteId(Map aMap, String fechaInicio, String fechaFinal, Long idCliente, Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	public Collection findOrdenMedioByQueryAndByFechasAndByClienteId(int startIndex,int endIndex,Map aMap, String fechaInicio, String fechaFinal, Long idCliente, Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	public int findOrdenMedioByQueryAndByFechasAndByClienteIdSize(Map aMap, String fechaInicio, String fechaFinal, Long idCliente, Long idEmpresa)throws com.spirit.exception.GenericBusinessException;
	public Map<OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> procesarOrdenMedio(PlanMedioIf planMedioIf, Map<String,Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaClienteOficinaOrdenes) throws GenericBusinessException;
	public Map<OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> procesarOrdenMedioXNuevaVersion(PlanMedioIf planMedioIf, Map<String,Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaClienteOficinaOrdenes,ArrayList<Map<Long,Map<String,String>>> listIdsCodigoEstadoOrdenesVerificarEstado) throws GenericBusinessException;
	public Map<OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>> actualizarOrdenMedio(PlanMedioIf planMedioIf, Map<String,Map <OrdenMedioIf,Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>>> mapaClienteOficinaOrdenes,boolean nuevoPlan, GenericoIf genericoPautaRegular) throws GenericBusinessException;
	public void actualizarOrdenMedio(OrdenMedioIf model,List<OrdenMedioDetalleIf> modelDetalleList) throws GenericBusinessException;
	public void eliminarOrdenMedio(Long ordenMedioId) throws GenericBusinessException;
	public Collection findOrdenMedioByQuery(int startIndex, int endIndex, Map aMap) throws com.spirit.exception.GenericBusinessException;
	public int findOrdenMedioByQuerySize(Map aMap) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findOrdenMedioByQueryToCompra(Map aMap, Long idEmpresa,Boolean medio) throws com.spirit.exception.GenericBusinessException;
	public int getOrdenMedioByQueryListSize(Map aMap, java.lang.Long idEmpresa, boolean medio) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection getOrdenMedioByQueryList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa, Boolean medio) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findMediaOrders(Long providerId) throws GenericBusinessException;
	public Collection findOrdenesClientesByOrdenesMedios(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin, Long idEmpresa, boolean ordenarPorFecha, Long creadoPorId, boolean ordenarPorCodigoOrden, boolean ordenarPorCodigoPresupuesto) throws com.spirit.exception.GenericBusinessException;
	
	public int findOrdenMedioByQueryAndByEstadosSize(Map aMap, String[] estados) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findOrdenMedioByQueryAndByEstados(int startIndex, int endIndex, Map aMap, String[] estados) throws com.spirit.exception.GenericBusinessException;
}
