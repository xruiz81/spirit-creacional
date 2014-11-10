package com.spirit.cartera.session;

import java.sql.Date;
import java.util.Collection;

import javax.ejb.*;
import javax.persistence.Query;

import com.spirit.cartera.session.generated._LogCarteraSession;
import com.spirit.cartera.session.LogCarteraSessionLocal;
import com.spirit.cartera.session.LogCarteraSessionRemote;
import com.spirit.cartera.entity.*;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class LogCarteraSessionEJB extends _LogCarteraSession implements LogCarteraSessionRemote,LogCarteraSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<LogCarteraIf> getLogCarteraByDocumento_Cliente_FechaInicio_FechaFin(
			String codigoDocumento, Long clienteId, Date fechaInicio,
			Date fechaFin, java.lang.Long idEmpresa)
			throws GenericBusinessException {
		try {
			String queryString = "";
			if (clienteId != null){
				queryString = "select distinct e from LogCarteraEJB e,TipoDocumentoEJB td,ClienteOficinaEJB co, ClienteEJB c where ";
				queryString += "e.tipodocumentoId = td.id and e.clienteoficinaId=co.id and co.clienteId = c.id ";
				queryString += "and td.codigo='" + codigoDocumento + "' "+ "and td.empresaId=" + idEmpresa;
				queryString += "and c.id = :clienteId ";
			} else {
				queryString = "select distinct e from LogCarteraEJB e,TipoDocumentoEJB td ";
				queryString += "where e.tipodocumentoId = td.id ";
				queryString += " and td.codigo='" + codigoDocumento + "' "+ "and td.empresaId=" + idEmpresa;
			}
			
			if (fechaInicio != null && fechaFin != null) {
				queryString += " and e.fechaEmision >= :fechaInicio and e.fechaEmision <= :fechaFin";
			}
			Query query = manager.createQuery(queryString);
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);
			if ( clienteId != null )
				query.setParameter("clienteId", clienteId);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
					"Se ha producido un error al consultar Venta");
		}
	}

}
