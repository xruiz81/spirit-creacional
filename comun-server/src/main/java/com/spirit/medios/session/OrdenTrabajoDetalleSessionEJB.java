package com.spirit.medios.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.session.generated._OrdenTrabajoDetalleSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * The <code>OrdenTrabajoDetalleSession</code> session bean, which acts as a
 * facade to the underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 * 
 */
@Stateless
public class OrdenTrabajoDetalleSessionEJB extends _OrdenTrabajoDetalleSession
		implements OrdenTrabajoDetalleSessionRemote,
		OrdenTrabajoDetalleSessionLocal {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	/*******************************************************************************************************************
	 * B U S I N E S S M E T H O D S
	 *******************************************************************************************************************/
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenTrabajoDetalleByEquipo(
			OrdenTrabajoIf ordenTrabajoIf,Long idEmpleado,String... estados) {
		String queryString = "SELECT distinct otd FROM OrdenTrabajoDetalleEJB otd ,EquipoTrabajoEJB et,EquipoEmpleadoEJB ee ";
		queryString += "where otd.equipoId = et.id and et.id = ee.equipoId ";
		queryString += "and (";
		for ( String estado : estados ){
			queryString += (" otd.estado = '"+estado+"' or");
		}
		queryString = queryString.substring(0,queryString.length()-3);
		queryString += " )";
		queryString += " and otd.ordenId = " + ordenTrabajoIf.getId();
		queryString += " and et.id in ( "
				+ " select distinct ee1.equipoId from EquipoEmpleadoEJB ee1 "
				+ " where  ee1.empleadoId = " + idEmpleado
				+ " )";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajoAndTipoProveedorAndEstado(
			java.lang.Long tipoOrdenId, java.lang.Long ordenTrabajoId,
			java.lang.Long tipoProveedorId) {
		String objectName = "m";
		String queryString = "select distinct m from OrdenTrabajoDetalleEJB "
				+ objectName
				+ ", SubtipoOrdenEJB sod, TipoOrdenEJB tod where  m.subtipoId = sod.id and m.ordenId = "
				+ ordenTrabajoId
				+ " and sod.tipoordenId = tod.id and tod.id = " + tipoOrdenId
				+ " and sod.tipoproveedorId = " + tipoProveedorId
				+ " and m.estado != 'C' and m.estado != 'A' order by m.id desc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	/**
	 * me devuelve todos las ordenes detalles por tipo de orden
	 * */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajo(
			java.lang.Long tipoOrdenId, java.lang.Long ordenTrabajoId) {

		String objectName = "m";
		String queryString = "select distinct m from OrdenTrabajoDetalleEJB "
				+ objectName
				+ ", SubtipoOrdenEJB sod, TipoOrdenEJB tod where  m.subtipoId = sod.id and m.ordenId = "
				+ ordenTrabajoId + " and sod.tipoordenId = " + tipoOrdenId
				+ " and tod.id = " + tipoOrdenId + " order by m.id desc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenTrabajoDetalleByTipoOrdenAndOrdenTrabajoAndEstado(
			java.lang.Long tipoOrdenId, java.lang.Long ordenTrabajoId) {

		String objectName = "m";
		String queryString = "select distinct m from OrdenTrabajoDetalleEJB "
				+ objectName
				+ ", SubtipoOrdenEJB sod, TipoOrdenEJB tod where  m.subtipoId = sod.id and m.ordenId = "
				+ ordenTrabajoId + " and sod.tipoordenId = " + tipoOrdenId
				+ " and tod.id = " + tipoOrdenId
				+ " and m.estado != 'C' and m.estado != 'A' order by m.id desc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenTrabajoDetalleBySubTipoIdAndOrdenTrabajoId(
			java.lang.Long subTipoId, java.lang.Long ordenTrabajoId) {

		String objectName = "m";
		String queryString = "select distinct m from OrdenTrabajoDetalleEJB "
				+ objectName + " where  m.subtipoId = " + subTipoId
				+ " and m.ordenId = " + ordenTrabajoId + " order by m.id desc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findOrdenTrabajoDetalleByAsignado(
			OrdenTrabajoIf ordenTrabajoIf,Long idEmpleado,String... estados) {
		String queryString = "SELECT distinct otd FROM OrdenTrabajoDetalleEJB otd , TiempoParcialDotEJB tpd " +
				"where otd.ordenId = " + ordenTrabajoIf.getId();
		
		queryString += " and (";
		for ( String estado : estados ){
			queryString += (" otd.estado = '"+estado+"' or");
		}
		
		queryString = queryString.substring(0,queryString.length()-3);
		
		//Si al miembro del equipo fue asignada una tarea o directamente la orden detalle estaba dirigida a él.
		queryString += " ) and ((otd.id = tpd.idOrdenTrabajoDetalle and tpd.usuarioAsignadoId = " + idEmpleado + ") or otd.asignadoaId = " + idEmpleado + ")";
				
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

}
