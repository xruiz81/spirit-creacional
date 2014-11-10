package com.spirit.medios.session;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.generated._OrdenTrabajoSessionService;
import com.spirit.util.FileInputStreamSerializable;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OrdenTrabajoSessionService extends _OrdenTrabajoSessionService{

	java.util.Collection findOrdenTrabajoByClienteOficinaIdAndEstadoAndEmpresaId(java.lang.Long clienteoficinaId, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public Collection findOrdenTrabajoByQueryAndByAsignadoaByEstados(boolean otCreadasTimetracker, Long idAsignado,String tipoUsuarioTimeTracker, Long idEmpresa, Long filtroClienteOficinaId, String... estados) throws GenericBusinessException;
	public Collection findOrdenTrabajoByQueryAndByAsignadoa(int startIndex,int endIndex,Map aMap, Long idAsignado, Long idEmpresa) throws GenericBusinessException;
	java.util.Collection findOrdenTrabajoByQueryAndByClienteId(int startIndex,int endIndex,Map aMap, Long idCliente, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int findOrdenTrabajoByQueryAndByClienteIdSize(Map aMap, Long idCliente, Long idEmpresa) throws GenericBusinessException;
	java.util.Collection findOrdenTrabajoPendienteByClienteOficinaIdAndTipoOrden(java.lang.Long clienteOficinaId, java.lang.String tipoOrden, boolean saveMode) throws com.spirit.exception.GenericBusinessException;
    public OrdenTrabajoIf procesarOrdenTrabajo(OrdenTrabajoIf model,List<OrdenTrabajoDetalleIf> modelDetalleList,List<ProductoClienteIf> modelProductoList,Long idEmpresa,FileInputStreamSerializable archivoOrden,String rutaDestino,String rutaSistema, String nombreArchivo) throws GenericBusinessException;
    public OrdenTrabajoIf actualizarOrdenTrabajo(OrdenTrabajoIf model,List<ProductoClienteIf> modelProductoList,FileInputStreamSerializable archivoOrden,String rutaDestino, String nombreArchivo)throws GenericBusinessException;
    public void actualizarOrdenTrabajo(OrdenTrabajoIf model,OrdenTrabajoDetalleIf modelDetalle)throws GenericBusinessException;
    public void actualizarOrdenTrabajoDetalle(OrdenTrabajoIf model,Collection<OrdenTrabajoDetalleIf> modelDetalleList,Collection<OrdenTrabajoDetalleIf> ordenDetalleEliminadasList,Collection<FileInputStreamSerializable> archivosDescripcion,Collection<FileInputStreamSerializable> archivosPropuesta,String rutaDestino,String rutaSistema,boolean esActualizacion)throws GenericBusinessException;
    public void eliminarOrdenTrabajo(Long ordenTrabajoId) throws GenericBusinessException;
    java.util.Collection findOrdenTrabajoByQuery(int startIndex,int endIndex,Map aMap) throws GenericBusinessException;
    int findOrdenTrabajoByQuerySize(Map aMap) throws GenericBusinessException;
    public Collection findOrdenTrabajoByQueryByTipoFechaByFechaInicioByFechaFinByEmpresaIdByTipoOrdenByResponsableAndByEstados(Map aMap, String ordenporFecha, java.sql.Date fechaInicio, java.sql.Date fechaFin, Long idEmpresa, Long idTipoOrden, Long idEmpleadoResponsable, String... estados);
    public Collection findOrdenTrabajoByQueryByTipoFechaByFechaInicioByFechaFinByEmpresaIdByTipoOrdenAndByEstados(Map aMap, String ordenporFecha, java.sql.Date fechaInicio, java.sql.Date fechaFin, Long idEmpresa, Long idTipoOrden, boolean tipoTodos, String... estados);

}
