package com.spirit.compras.session;

import java.sql.Date;
import java.util.Collection;

import javax.ejb.*;
import javax.persistence.Query;

import com.spirit.cartera.entity.LogCarteraIf;
import com.spirit.compras.session.generated._LogCompraRetencionSession;
import com.spirit.compras.session.LogCompraRetencionSessionLocal;
import com.spirit.compras.session.LogCompraRetencionSessionRemote;
import com.spirit.compras.entity.*;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class LogCompraRetencionSessionEJB extends _LogCompraRetencionSession implements LogCompraRetencionSessionRemote,LogCompraRetencionSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<LogCompraRetencionIf> getLogCompraRetencionByDocumento_Cliente_FechaInicio_FechaFin(
			String codigoDocumento, Long clienteId, Date fechaInicio,
			Date fechaFin, java.lang.Long idEmpresa)
			throws GenericBusinessException {
		try {
			if (fechaInicio == null ) {
				throw new GenericBusinessException("Fecha Inicio para consulta no ha sido establecida !!");
			}
			if (fechaFin == null) {
				throw new GenericBusinessException("Fecha Fin para consulta no ha sido establecida !!");
			}

			String queryString = "";
				queryString = "select distinct e from LogCompraRetencionEJB e "+
					" where e.fechaEmision >= :fechaInicio and e.fechaEmision <= :fechaFin";
			
			Query query = manager.createQuery(queryString);
			query.setParameter("fechaInicio", fechaInicio);
			query.setParameter("fechaFin", fechaFin);
			
			return query.getResultList();
		}catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
					"Se ha producido un error al consultar Retenciones anuladas !!");
			
		}
	}

}
