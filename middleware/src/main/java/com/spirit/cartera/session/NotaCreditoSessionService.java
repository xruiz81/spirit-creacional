package com.spirit.cartera.session;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.NotaCreditoDetalleIf;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.cartera.session.generated._NotaCreditoSessionService;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface NotaCreditoSessionService extends _NotaCreditoSessionService{

	public NotaCreditoIf procesarNotaCredito(NotaCreditoIf model,List<NotaCreditoDetalleIf> modelDetalleList,Long idEmpresa,Long idOficina) throws GenericBusinessException;
	public void actualizarNotaCredito(NotaCreditoIf model, List<NotaCreditoDetalleIf> modelDetalleList) throws GenericBusinessException;
	Collection getNotaCreditoByQueryList(int startIndex,int endIndex, Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int getNotaCreditoByQueryListSize(Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public Boolean verificarPreimpreso(Long operadorNegocioId, String preimpreso, String autorizacion, String tipoCartera) throws GenericBusinessException;
	public Collection findNotasCredito(Long operadorNegocioId, Long documentoId, Long empresaId) throws GenericBusinessException;
	public List<AsientoIf> cruzarNotasCredito(List<NotaCreditoIf> notaCreditoSeleccionadaColeccion, Map carteraNotaCreditoMap, Map valorAplicaMap, CarteraIf carteraAfectada, java.sql.Date fechaAplicacion) throws GenericBusinessException;
	public Collection findNotaCreditoByEmpresaId(Long idEmpresa) throws GenericBusinessException;
	Collection getNotaCreditoNoAnuladaList(int startIndex,int endIndex,Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int getNotaCreditoNoAnuladaListSize(Map aMap, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;	
	public Collection getNotaCreditoByQueryListFechas(Map aMap, java.lang.Long idEmpresa, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin) throws GenericBusinessException ;
	public java.util.Collection getNotaCreditoByMapFechaInicioFechaFin(Map aMap,Date fechaInicio,Date fechaFin, java.lang.Long idEmpresa) throws GenericBusinessException;
	public java.util.Collection findNotaCreditoNotaCreditoDetalleByClienteOficinaIdByTipoPresupuestoByPresupuestoIdByOrdenId(Long idClienteOficina, String tipoPresupuesto, Long idPresupuesto, Long idOrden) throws com.spirit.exception.GenericBusinessException;
}
