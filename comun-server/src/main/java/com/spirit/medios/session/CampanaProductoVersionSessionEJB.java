package com.spirit.medios.session;

import java.util.Collection;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.session.generated._CampanaProductoVersionSession;
import com.spirit.medios.session.CampanaProductoVersionSessionLocal;
import com.spirit.medios.session.CampanaProductoVersionSessionRemote;
import com.spirit.medios.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class CampanaProductoVersionSessionEJB extends _CampanaProductoVersionSession implements CampanaProductoVersionSessionRemote, CampanaProductoVersionSessionLocal  {

	 @PersistenceContext(unitName="spirit")
	   private EntityManager manager;
	
	 /*******************************************************************************************************************
	  *                                  B U S I N E S S   M E T H O D S
	  *******************************************************************************************************************/
	 
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CampanaProductoVersionEJB registrarCampanaProductoVersion(CampanaProductoIf modelCampanaProducto,CampanaProductoVersionIf modelCampanaProductoVersion) throws GenericBusinessException {
				
		CampanaProductoVersionEJB campanaProductoVersion = new CampanaProductoVersionEJB();
		
		campanaProductoVersion.setCodigo(modelCampanaProductoVersion.getCodigo());
		campanaProductoVersion.setNombre(modelCampanaProductoVersion.getNombre());
		campanaProductoVersion.setEstado(modelCampanaProductoVersion.getEstado());
		campanaProductoVersion.setCampanaProductoId(modelCampanaProducto.getId());
				
		return campanaProductoVersion;		
	}

	public String getMaximoCodigoVersion(Long clienteId){
		String queryString = "select max(cpv.codigo) from CampanaProductoVersionEJB cpv, CampanaProductoEJB cp, CampanaEJB ca, ClienteEJB cl where " +
		        "cpv.campanaProductoId = cp.id and cp.campanaId = ca.id and " +
				"ca.clienteId = cl.id and cl.id = " + clienteId + "";
		Query query = manager.createQuery(queryString);
		String codigo = "";
		if(query.getResultList().get(0)!=null){
			codigo = query.getResultList().toString().substring(1).replaceAll("]","");
		}
		return codigo;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCampanaProductoVersionByIdCampana(Long idCampana, Long idEmpresa)throws GenericBusinessException {
					
		 String cadenaQuery = "select distinct cpv from CampanaProductoVersionEJB cpv, CampanaProductoEJB cp, CampanaEJB ca, "  +
		 				      "ClienteEJB c,TipoClienteEJB tc where " +
		 					  "cpv.campanaProductoId = cp.id and cp.campanaId = " + idCampana + " and " +
		 					  "ca.clienteId = c.id  and  c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + "";
				 							    		
		Query query = manager.createQuery(cadenaQuery);

		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findCampanaProductoVersionByEmpresaId(Long idEmpresa)throws GenericBusinessException {
					
		 String cadenaQuery = "select distinct cpv from CampanaProductoVersionEJB cpv, CampanaProductoEJB cp, CampanaEJB ca, "  +
		 				      "ClienteEJB c,TipoClienteEJB tc where " +
		 					  "cpv.campanaProductoId = cp.id and cp.campanaId = ca.id and " +
		 					  "ca.clienteId = c.id  and  c.tipoclienteId = tc.id and tc.empresaId = " + idEmpresa + "";
				 							    		
		Query query = manager.createQuery(cadenaQuery);

		return query.getResultList();
	}
}
