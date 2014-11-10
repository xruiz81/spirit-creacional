package com.spirit.crm.session;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.spirit.crm.entity.ClienteContactoIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteIndicadorIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.ClienteRetencionIf;
import com.spirit.crm.entity.ClienteZonaIf;
import com.spirit.crm.session.generated._ClienteSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 * The <code>ClienteSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:45 $
 */
public interface ClienteSessionService extends _ClienteSessionService{
	
	Collection findClienteByClienteOficinaId(Long idClienteOficina) throws com.spirit.exception.GenericBusinessException;
	
	public String findClienteByClienteOficinaIdNombreLegal(java.lang.Long idClienteOficina) throws com.spirit.exception.GenericBusinessException;
	
	Collection getClienteList(int startIndex, int endIndex, Map aMap, String tipoCliente, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	//ADD 27 JULIO
	public Collection getClienteByTipoProveedorList(int startIndex, int endIndex, Map aMap,	String tipoCliente, Long tipoProveedorId,java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	
	int getClienteListSize(Map aMap, String tipoCliente, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	//ADD 27 JULIO
	int getClienteByTipoProveedorListSize(Map aMap, String tipoCliente, Long id,java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	
	Collection findClienteByFilteredQuery(int startIndex,int endIndex,Map aMap, java.lang.Long idTipoCliente, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int findClienteByFilteredQuerySize(Map aMap, java.lang.Long idTipoCliente, java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public void procesarCliente(ClienteIf model,List<ClienteZonaIf> modelClienteZonaList,List<ClienteRetencionIf> modelClienteRetencinoList,List<ClienteOficinaIf> modelClienteOficinaList,Map modelClienteContactoMap,Map modelClienteIndicadorMap,boolean propagarMensaje) throws GenericBusinessException;
	public void procesarClienteWeb(ClienteIf model,
			List<ClienteZonaIf> modelClienteZonaList,
			List<ClienteOficinaIf> modelClienteOficinaList,
			Map modelClienteContactoMap, Map modelClienteIndicadorMap,
			boolean propagarMensaje) throws GenericBusinessException ;
	public void actualizarCliente(
			ClienteIf model,List<ClienteZonaIf> modelClienteZonaList,List<ClienteRetencionIf> modelClienteRetencinoList,List<ClienteOficinaIf> modelClienteOficinaList,
			Map<String,Vector<ClienteContactoIf>> modelClienteContactoMap,Map<String,Vector<ClienteIndicadorIf>> modelClienteIndicadorMap,
			List<ClienteZonaIf> detalleZonaRemovidaClienteList, List<ClienteRetencionIf> detalleRetencionRemovidaClienteList, List<ClienteOficinaIf> detalleOficinaRemovidaClienteList, Map<String,Vector<ClienteContactoIf>>  detalleContactoRemovidoClienteList, Map<String,Vector<ClienteIndicadorIf>>  detalleIndicadorRemovidoClienteList,boolean propagarMensaje) throws GenericBusinessException;
	public void eliminarCliente(String identificacionCliente, Long empresaId, boolean propagarMensaje) throws GenericBusinessException;
	public Collection findClienteByEmpresaId(Long idEmpresa) throws GenericBusinessException;
	public Collection findClienteByIdentificacionAndEmpresaId(String identificacion, Long idEmpresa) throws GenericBusinessException;
	//ADD 27 JULIO
	public Collection findClienteByEmpresaIdaAndTipoClienteId(Long idEmpresa,Long idTipoCliente)throws GenericBusinessException;
	public Collection findClienteByOrdenMedioId(Long idOrdenMedio) throws GenericBusinessException;
	public Collection findClienteByPresupuestoId(Long idPresupuesto) throws GenericBusinessException;
	public ClienteIf getClienteByIdentificacionAndEmpresaId(String identificacion, Long empresaId) throws GenericBusinessException;
	
	public ClienteOficinaIf getClienteOficina(String identificacionCliente,
			String codigoOficina) throws GenericBusinessException;
	
	public String generarCodigoProveedor(Long empresaId);
	public Collection findClienteByPlanMedioId(Long planMedioId) throws GenericBusinessException;
	
}