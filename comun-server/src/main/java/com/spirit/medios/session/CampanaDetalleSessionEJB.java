package com.spirit.medios.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.medios.entity.CampanaDetalleEJB;
import com.spirit.medios.entity.CampanaDetalleIf;
import com.spirit.medios.session.generated._CampanaDetalleSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>CampanaDetalleSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class CampanaDetalleSessionEJB extends _CampanaDetalleSession implements CampanaDetalleSessionRemote, CampanaDetalleSessionLocal {

	@PersistenceContext(unitName="spirit")
	private EntityManager manager;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CampanaDetalleEJB registrarCampanaDetalle(CampanaDetalleIf modelDetalle) {
		CampanaDetalleEJB campanaDetalle = new CampanaDetalleEJB();
		
		campanaDetalle.setId(modelDetalle.getId());
		campanaDetalle.setClienteZonaId(modelDetalle.getClienteZonaId());
		campanaDetalle.setCampanaId(modelDetalle.getCampanaId());
		campanaDetalle.setParticipacion(modelDetalle.getParticipacion());
		campanaDetalle.setDescripcion(modelDetalle.getDescripcion());
		
		return campanaDetalle;
	}

}
