package com.spirit.medios.session;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.CampanaArchivoIf;
import com.spirit.medios.entity.CampanaBriefIf;
import com.spirit.medios.entity.CampanaDetalleIf;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.CampanaProductoVersionIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.generated._CampanaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CampanaSessionService extends _CampanaSessionService{

	java.util.Collection findCampanaByQueryAndByResponsableOrdenTrabajoAndByFechaInicioAndFechaFin(Map aMap, Long idResponsableOrdenTrabajo, Date fechaInicio, Date fechaFin) throws com.spirit.exception.GenericBusinessException;
	Collection findCampanaByResponsableOrdenTrabajoAndByFechaInicioAndFechaFin(Long idResponsableOrdenTrabajo, Date fechaInicio, Date fechaFin) throws GenericBusinessException;
	Collection findCampanaByEmpresaId(Long idEmpresa) throws GenericBusinessException;
	Collection findCampanaByQueryAndByCorporacionId(Map aMap,Long idCorporacion,Long idEmpresa) throws GenericBusinessException;
	int findCampanaByQueryAndByCorporacionIdSize (Map aMap, Long idCorporacion, Long idEmpresa) throws GenericBusinessException;
	Collection getCampanaList(int startIndex, int endIndex,Map aMap,Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int getCampanaListSize(Map aMap,Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public CampanaIf procesarCampana(CampanaIf model,List<CampanaDetalleIf> modelDetalleList,List<ProductoClienteIf> modelProductoList) throws GenericBusinessException;
   
	//ADD 19 SEPTIEMBRE
	public CampanaIf procesarCampana(CampanaIf model,List<CampanaDetalleIf> modelDetalleList,Map<ProductoClienteIf,ArrayList<CampanaProductoVersionIf>> modelProductoVersionesMap) throws com.spirit.exception.GenericBusinessException;
	//ADD 28 SEPTIEMBRE
	public CampanaIf actualizarCampana(CampanaIf model, List<CampanaDetalleIf> modelDetalleList, Map<ProductoClienteIf,ArrayList<CampanaProductoVersionIf>> modelProductoVersionesMap,Vector<ProductoClienteIf> productoClienteIfEliminadosVector,Vector<CampanaProductoVersionIf> campanaProductoVersionIfEliminadosVector, List<CampanaDetalleIf> detallesCampanaEliminadosList)throws com.spirit.exception.GenericBusinessException;
	
	public CampanaIf actualizarCampana(CampanaIf model,List<CampanaDetalleIf> modelDetalleList,List<ProductoClienteIf> modelProductoList, List<CampanaDetalleIf> detallesCampanaEliminadosList)throws GenericBusinessException;
    public void actualizarArchivosBrief(CampanaIf model,List<CampanaBriefIf> modelBriefList, List<CampanaBriefIf> briefsEliminadosList,String urlCarpetaSevidor) throws GenericBusinessException;
    public void actualizarArchivosCampana(CampanaIf model,List<CampanaArchivoIf> modelArchivoList, List<CampanaArchivoIf> archivosEliminadosList,String urlCarpetaSevidor)throws GenericBusinessException;
    public void eliminarCampana(Long campanaId) throws GenericBusinessException;
    java.util.Collection findCampanaByClienteIdAndByFechaInicioAndFechaFin(java.lang.Long clienteId, Date fechaInicio, Date fechaFin) throws GenericBusinessException;
    java.util.Collection findCampanaByEstadoAndByFechaInicioAndFechaFin(java.lang.String estado, Date fechaInicio, Date fechaFin) throws com.spirit.exception.GenericBusinessException;
    java.util.Collection findCampanaByQueryAndByFechaInicioAndFechaFin(Map aMap, Date fechaInicio, Date fechaFin) throws com.spirit.exception.GenericBusinessException;
    Collection findCampanaByPlanMedioId(Long planMedioId) throws GenericBusinessException;
}
