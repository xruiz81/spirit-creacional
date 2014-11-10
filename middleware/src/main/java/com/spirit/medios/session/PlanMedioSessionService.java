package com.spirit.medios.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.MapaComercialIf;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioDetalleMapaIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PlanMedioDetalleIf;
import com.spirit.medios.entity.PlanMedioEJB;
import com.spirit.medios.entity.PlanMedioFacturacionIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PlanMedioMesIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.generated._PlanMedioSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PlanMedioSessionService extends _PlanMedioSessionService{
	
	Collection findPlanMedioByQuery(int startIndex,int endIndex,Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int findPlanMedioByQuerySize(Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	Collection findPlanMedioByOrdenTrabajoId(java.lang.Long idOrden) throws com.spirit.exception.GenericBusinessException;
	Collection findPlanMedioByCodigoAndEstadosAprobadoPedido(String codigo, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	Collection findPlanMedioByQueryAndByIdClienteOficina(int startIndex,int endIndex,Map aMap,Long idCliente, Long idEmpresa, String... estados) throws com.spirit.exception.GenericBusinessException;
	int findPlanMedioByQueryAndByIdClienteSize(Map aMap,Long idCliente, Long idEmpresa,String... estados) throws com.spirit.exception.GenericBusinessException;
	Collection findPlanMedioByQueryByProveedorIdByEmpresaIdAndByEstados(int startIndex,int endIndex, Map aMap, Long idProveedor, Long idEmpresa, String... estados) throws com.spirit.exception.GenericBusinessException;
	int findPlanMedioByQueryByProveedorIdByEmpresaIdAndByEstadosSize(Map aMap, Long idProveedor, Long idEmpresa, String... estados) throws com.spirit.exception.GenericBusinessException;
	
	Collection findPlanMedioByPedido(Map queryMap) throws com.spirit.exception.GenericBusinessException;
	
	//Collection findPlanMedioByQueryAndByIdClienteOficina(int startIndex,int endIndex,Map aMap,Long idCliente, Long idEmpresa, String... estados) throws com.spirit.exception.GenericBusinessException;
	//int findPlanMedioByQueryAndByIdClienteSize(Map aMap,Long idCliente, Long idEmpresa,String estados) throws com.spirit.exception.GenericBusinessException;
	
	//ADD 17 JUNIO
	int findPlanMedioOriginalByQueryAndByIdClienteAndIdCampanaSize(Long idCliente, Long idCampana, Long idEmpresa, String planMedioTipo) throws com.spirit.exception.GenericBusinessException;
	Collection findPlanMedioOriginalByQueryAndByIdClienteOficinaAndIdCampana(int startIndex,int endIndex,Long idCliente, Long idCampana, Long idEmpresa, String planMedioTipo) throws com.spirit.exception.GenericBusinessException;
	//END 17 JUNIO
	Collection findPlanMedioByFechas(String fechaInicio, String fechaFinal, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	//public void procesarPlanMedio(PlanMedioIf model, OrdenTrabajoDetalleIf ordenTrabajoDetalleTemp, List<PlanMedioMesIf> resumenPlanMedioColeccion, List<PlanMedioDetalleIf> detallePlanMedioColeccion, Map detallesToResumenMesMap, Map mapasComercialesToDetallesMap, Map mapasComercialesDetallesToResumenMesMap) throws GenericBusinessException;
	//public void autorizarPlanMedio(PlanMedioIf model, OrdenTrabajoDetalleIf ordenTrabajoDetalleTemp, List<PlanMedioMesIf> resumenPlanMedioColeccion, List<PlanMedioDetalleIf> detallePlanMedioColeccion, Map detallesToResumenMesMap, Map mapasComercialesToDetallesMap, Map mapasComercialesDetallesToResumenMesMap) throws GenericBusinessException;
	//public void procesarPlanMedioPreOrden(PlanMedioIf model, OrdenTrabajoDetalleIf ordenTrabajoDetalleTemp, List<PlanMedioMesIf> resumenPlanMedioColeccion, List<PlanMedioDetalleIf> detallePlanMedioColeccion, Map detallesToResumenMesMap, Map mapasComercialesToDetallesMap, Map mapasComercialesDetallesToResumenMesMap) throws GenericBusinessException;
	//public void actualizarPlanMedio(PlanMedioIf model, OrdenTrabajoDetalleIf ordenTrabajoDetalleTemp, List<PlanMedioMesIf> resumenPlanMedioColeccion, List<PlanMedioDetalleIf> detallePlanMedioColeccion, Map detallesToResumenMesMap, Map mapasComercialesToDetallesMap, Map mapasComercialesDetallesToResumenMesMap) throws GenericBusinessException;
	//public void eliminarPlanMedio(PlanMedioIf model, OrdenTrabajoDetalleIf ordenTrabajoDetalleTemp) throws GenericBusinessException;
	 
	public PlanMedioIf procesarPlanMedio(PlanMedioIf model, Vector<PlanMedioMesIf> planMedioMesVector, Collection<PlanMedioDetalleIf> detallesPlantilla, Map<PlanMedioDetalleIf, Collection<MapaComercialIf>> mapasComercialesPlantilla) throws GenericBusinessException;
	
	//ADD 22 JUNIO
	public PlanMedioIf procesarPlanMedioXTipo(PlanMedioIf model, Vector<PlanMedioMesIf> planMedioMesVector, Collection<PlanMedioDetalleIf> detallesPlantilla, Map<PlanMedioDetalleIf, Collection<MapaComercialIf>> mapasComercialesPlantilla,PlanMedioIf planMedioOriginal) throws GenericBusinessException;
	//END 22 JUNIO
	
	//ADD 24 JUNIO
	public Collection findPlanMedioByIdPlanMedioHermano(Long idPlanMedioHermano)throws GenericBusinessException;
	public void actualizarVersionPlanMedioHermano(Long planMedioOrigenId,Long idPlanMedioNuevaVersion) throws GenericBusinessException;
	//END 24 JUNIO
	
	public void actualizarPlanMedio(PlanMedioIf model, Vector<PlanMedioMesIf> planMedioMesVector, Vector<PlanMedioMesIf> planMedioMesRemovidoVector, Collection<PlanMedioDetalleIf> detallesPlantilla, Map<PlanMedioDetalleIf, Collection<MapaComercialIf>> mapasComercialesPlantilla, boolean nuevoPlan) throws GenericBusinessException;
	public void eliminarPlanMedio(PlanMedioIf model) throws GenericBusinessException;
	public PlanMedioEJB registrarPlanMedio(PlanMedioIf model) throws GenericBusinessException;

	//ADD 4 JULIO
	public Collection findPlanMedioByIdPlanMedioHermanoNuevaVersion(Long lessIdPlanMedio,Long idPlaMedioHermano)throws GenericBusinessException;
	public Collection findPlanMedioByIdPlanMedioHermanoNuevoMes(Long idPlaMedioHermano)throws GenericBusinessException;
	//END 4 JULIO
	
	public java.util.Collection findPlanMedioByCodigoAndByEstados(String codigo, String... estados) throws GenericBusinessException;
	
	public java.util.Map<ProductoClienteIf,Map<Long,Map<OrdenMedioIf,Map<OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>>> cargarMapaProductosClienteVersionesDetalle(PlanMedioIf planMedioIf) throws GenericBusinessException;
	public ArrayList<PlanMedioFacturacionIf> getPlanMedioFacturacionTotal(PlanMedioIf planMedioFacturado, boolean esFacturacionCompleta) throws GenericBusinessException;
	public Map<OrdenMedioIf,Map <OrdenMedioDetalleIf,ArrayList<OrdenMedioDetalleMapaIf>>>  getOrdenesMedioSegunPeriodoAFacturar(ArrayList<OrdenMedioIf> listaOrdenMedio,ArrayList<PlanMedioFacturacionIf> listPlanMedioFacturacion,Date periodoFechaInicio, Date periodoFechaFin ) throws GenericBusinessException;
}
