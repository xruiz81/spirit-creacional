package com.spirit.crm.session;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.crm.entity.ClienteOficinaEJB;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.generated._ClienteOficinaSessionService;
import com.spirit.exception.GenericBusinessException;

public interface ClienteOficinaSessionService extends _ClienteOficinaSessionService {

	Collection findClienteOficinaByEmpresaId(java.lang.Long idEmpresa)	throws com.spirit.exception.GenericBusinessException;
	Collection findClienteOficinaByIdentificacionAndEmpresaId(java.lang.String identificacion, java.lang.Long idEmpresa)	throws com.spirit.exception.GenericBusinessException;
	Collection findClienteOficinaByTipoProveedorIdAndEmpresaId(Long idTipoProveedor, Long idEmpresa)	throws com.spirit.exception.GenericBusinessException;
	Collection getClienteOficinaList(int startIndex, int endIndex, Map aMap, String tipoCliente, Long idEmpresa, String mmpg) throws com.spirit.exception.GenericBusinessException;
	//ADD 27 JULIO
	Collection getClienteOficinaByTipoProveedorIdList(int startIndex, int endIndex, Map aMap, String tipoCliente, Long tipoProveedorId, Long idEmpresa, String mmpg) throws com.spirit.exception.GenericBusinessException;
	
	int getClienteOficinaListSize(Map aMap, String tipoCliente, Long idEmpresa, String mmpg) throws com.spirit.exception.GenericBusinessException;
	//ADD 27 JULIO
	int getClienteOficinaByTipoProveedorIdListSize(Map aMap, String tipoCliente, Long tipoProveedorId, Long idEmpresa, String mmpg) throws com.spirit.exception.GenericBusinessException;
	
	Collection getClienteOficinaList(int startIndex, int endIndex, Map aMap, Long idCorporacion, String tipoCliente, Long idEmpresa, String mmpg) throws com.spirit.exception.GenericBusinessException;
	//ADD 27 JULIO
	Collection getClienteOficinaByTipoProveedorIdList(int startIndex, int endIndex, Map aMap, Long idCorporacion, String tipoCliente, Long tipoProveedorId, Long idEmpresa, String mmpg) throws com.spirit.exception.GenericBusinessException;
	
	int getClienteOficinaListSize(Map aMap, Long idCorporacion, String tipoCliente, Long idEmpresa, String mmpg) throws com.spirit.exception.GenericBusinessException;
	//ADD 27 JULIO
	int getClienteOficinaByTipoProveedorIdListSize(Map aMap, Long idCorporacion, String tipoCliente, Long tipoProveedorId, Long idEmpresa, String mmpg) throws com.spirit.exception.GenericBusinessException;
	
	public ClienteOficinaEJB registrarClienteOficina(ClienteOficinaIf modelClienteOficina) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findClienteOficinaByEmailByEmpresaId(String email,Long empresaId) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findProveedoresProductosVenta(Long empresaId) throws GenericBusinessException;
	java.util.Collection findVendedorByClienteOficinaId(java.lang.Long clienteOficinaId) throws com.spirit.exception.GenericBusinessException;
	public int findBusinessOperatorOfficeByQueryListSize(Map<String, Object> queryMap);
	public List<ClienteOficinaIf> findBusinessOperatorOfficeByQuery(int startIndex, int endIndex, Map<String, Object> queryMap);
	public List<ClienteOficinaIf> findBusinessOperatorOfficeByQuery(Map<String, Object> queryMap);
}
