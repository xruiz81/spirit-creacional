package com.spirit.medios.session;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.medios.entity.ReunionArchivoIf;
import com.spirit.medios.entity.ReunionCompromisoIf;
import com.spirit.medios.entity.ReunionIf;
import com.spirit.medios.session.generated._ReunionSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ReunionSessionService extends _ReunionSessionService{

	java.util.Collection findReunionByQuery(int startIndex,int endIndex,Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int findReunionByQuerySize(Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public ReunionIf procesarReunion(ReunionIf model,List modelProductoList,List<EmpleadoIf> modelAsistenteAgenciaList,List modelAsistenteClienteList,List<ReunionCompromisoIf> modelCompromisoList,boolean esCliente, List<File> archivosList,List<String> archivosNombreList) throws GenericBusinessException, java.io.NotSerializableException;
    public ReunionIf actualizarReunion(ReunionIf model,List modelProductoList,List<EmpleadoIf> modelAsistenteAgenciaList,List modelAsistenteClienteList,List<ReunionCompromisoIf> modelCompromisoList,boolean esCliente,List<File> archivosList, List<ReunionCompromisoIf> compromisosEliminadosList)throws GenericBusinessException;
    public void actualizarArchivosReunion(ReunionIf model,List<ReunionArchivoIf> modelArchivoList,List<ReunionArchivoIf> archivosEliminadosList,String urlCarpetaSevidor)throws GenericBusinessException;
    public void eliminarReunion(Long reunionId) throws GenericBusinessException;
}
