package com.spirit.pos.session;

import java.util.Collection;
import java.util.List;

import com.spirit.exception.GenericBusinessException;
import com.spirit.pos.entity.TarjetaIf;
import com.spirit.pos.session.generated._TarjetaSessionService;

public interface TarjetaSessionService extends _TarjetaSessionService {

	
	public TarjetaIf findTarjetaWebService(String identificacion,String codigoOficina,Long empresaId) throws GenericBusinessException;
	
	public Collection findTarjetaByIdentificacionByCodigoOficinaByEmpresaId(String identificacion,String codigoOficina,Long empresaId ) throws GenericBusinessException;
	
	public void procesarActualizacionStatusTarjetas() throws GenericBusinessException;
	
	public void registrarTarjetaList(List<TarjetaIf> tarjetaAfiliacionList) throws GenericBusinessException;
	
	public java.util.Collection findTarjetaByEmpresaIdByCodigoBarrasAndEstadoWebService(Long empresaId,String codigoBarras,String estado) throws GenericBusinessException;
	public TarjetaIf findTarjetaByIdWebService(Long empresaId, Long id) throws GenericBusinessException;
}
