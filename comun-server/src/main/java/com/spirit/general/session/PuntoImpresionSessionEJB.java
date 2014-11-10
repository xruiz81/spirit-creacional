package com.spirit.general.session;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.generated._PuntoImpresionSession;

/**
 * The <code>PuntoImpresionSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class PuntoImpresionSessionEJB extends _PuntoImpresionSession implements PuntoImpresionSessionRemote, PuntoImpresionSessionLocal {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	
	public java.util.Collection findPuntoImpresionByTipoDocumentoAndByCajaId(java.lang.Long idTipoDocumento, java.lang.Long idCaja) throws GenericBusinessException {
		String objectName = "m";
	 	String queryString = "select m from PuntoImpresionEJB " + objectName + " where  m.tipodocumentoId = " + idTipoDocumento + " and m.cajaId = " + idCaja + " order by m.id desc";
	 	Query query = manager.createQuery(queryString);
	 	return query.getResultList();
	}
	
	public java.util.Collection findPuntoImpresionByEmpresaId(java.lang.Long idEmpresa) throws GenericBusinessException {
		//select * from punto_impresion pi, tipo_documento td where pi.TIPODOCUMENTO_ID = td.ID and td.EMPRESA_ID = 1
		String objectName = "pi";
		String queryString = "select pi from PuntoImpresionEJB " + objectName + ", TipoDocumentoEJB td where  pi.tipodocumentoId = td.id and td.empresaId = " + idEmpresa + "";
	 	Query query = manager.createQuery(queryString);
	 	return query.getResultList();
	}

}
