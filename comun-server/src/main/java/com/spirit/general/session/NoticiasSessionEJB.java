package com.spirit.general.session;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.general.session.generated._NoticiasSession;

/**
 * The <code>NoticiasSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class NoticiasSessionEJB extends _NoticiasSession implements NoticiasSessionRemote  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findNoticiasByUsuarioNoticiasId(Long idUsuario){
	   
	   java.util.Date hoy = new java.util.Date();
	   java.sql.Date fechaHoy = new java.sql.Date(hoy.getYear(), hoy.getMonth(), hoy.getDate());
	   
	   //select distinct * from noticias n, usuario_noticias un where n.ID = un.NOTICIAS_ID and un.USUARIO_ID = 98304
	   //and n.STATUS = 'A' and n.FECHA_INI <= TO_Date('2007-01-11', 'YYYY-MM-DD') and n.FECHA_FIN >= TO_Date('2007-01-11', 'YYYY-MM-DD')
	   String queryString = "select distinct n from NoticiasEJB n, UsuarioNoticiasEJB un where n.id = un.noticiasId and un.usuarioId = " + idUsuario + " and n.status = 'A' and n.fechaIni <= :fechaHoy and n.fechaFin >= :fechaHoy";
	   Query query = manager.createQuery(queryString);
	   query.setParameter("fechaHoy",fechaHoy);
	   return query.getResultList();
   }
  
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findNoticiaByAsuntoAndByUsuario(String asunto, Long idUsuario){
	   
	   //select * from noticias n where n.ASUNTO = 'hora' and n.FECHA_FIN >= TO_Date('2006-12-30', 'YYYY-MM-DD')
	   String queryString = "select n from NoticiasEJB n where n.asunto like '%" + asunto + "%' and n.usuarioId = " + idUsuario + "";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection findNoticiaByAsuntoByUsuarioAndByFechaInicioAndFechaFin(String asunto, java.lang.Long idUsuario, java.sql.Date fechaInicio, java.sql.Date fechaFin){
	   if(asunto.equals("") || asunto == null){
		   asunto = "%";
	   }else{
		   asunto = "%" + asunto + "%";
	   }
	   
	   String queryString = "select n from NoticiasEJB n where n.asunto like '%" + asunto + "%' and n.usuarioId = " + idUsuario;
	   	   
	   if((fechaInicio != null) && (fechaFin != null)){
			queryString = queryString
						+ " and n.fechaIni >= :fechaInicio and n.fechaFin <= :fechaFin";
		}
		else if((fechaInicio != null) && (fechaFin == null)){
			queryString = queryString
						+ " and n.fechaIni >= :fechaInicio";
		}
		else if((fechaInicio == null) && (fechaFin != null)){
			queryString = queryString
						+ " and n.fechaFin <= :fechaFin";
		}	   
	   
	   Query query = manager.createQuery(queryString);
	   query.setParameter("fechaInicio",fechaInicio);
	   query.setParameter("fechaFin",fechaFin);
	   return query.getResultList();
   }
   
}
