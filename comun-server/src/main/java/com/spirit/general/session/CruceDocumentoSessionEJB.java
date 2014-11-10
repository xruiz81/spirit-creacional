package com.spirit.general.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.spirit.general.session.generated._CruceDocumentoSession;

/**
 * The <code>CruceDocumentoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class CruceDocumentoSessionEJB extends _CruceDocumentoSession implements CruceDocumentoSessionRemote  {

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findCruceDocumentoByEmpresaId(java.lang.Long idEmpresa) {
		//select distinct cd.* from cruce_documento cd, documento d, tipo_documento td where cd.DOCUMENTO_ID = d.ID and d.TIPODOCUMENTO_ID = td.ID and td.EMPRESA_ID = 1 
		String queryString = "select cd from CruceDocumentoEJB cd, DocumentoEJB d, TipoDocumentoEJB td where cd.documentoId = d.id and d.tipoDocumentoId = td.id and td.empresaId = " + idEmpresa + "";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
   }

}
