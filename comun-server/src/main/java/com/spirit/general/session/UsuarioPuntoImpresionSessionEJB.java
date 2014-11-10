package com.spirit.general.session;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.session.generated._UsuarioPuntoImpresionSession;

/**
 * The <code>UsuarioPuntoImpresionSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:16 $
 *
 */
@Stateless
public class UsuarioPuntoImpresionSessionEJB extends _UsuarioPuntoImpresionSession implements UsuarioPuntoImpresionSessionRemote,UsuarioPuntoImpresionSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
   	
   public java.util.Collection findUsuarioPuntoImpresionByPuntoImpresionAndByUsuarioId(java.lang.Long idPuntoImpresion, java.lang.Long idUsuario) throws GenericBusinessException {
	   String objectName = "m";
	   String queryString = "select m from UsuarioPuntoImpresionEJB " + objectName + " where  m.puntoimpresionId = " + idPuntoImpresion + " and m.usuarioId = " + idUsuario + " order by m.id desc";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }
   
   public java.util.Collection findUsuarioPuntoImpresionByEmpresaId(java.lang.Long idEmpresa) throws GenericBusinessException {
		//select * from usuario_punto_impresion up, usuario u where up.USUARIO_ID = u.ID and u.EMPRESA_ID = 1
		String objectName = "up";
		String queryString = "select up from UsuarioPuntoImpresionEJB " + objectName + ", UsuarioEJB u where  up.usuarioId = u.id and u.empresaId = " + idEmpresa + "";
	 	Query query = manager.createQuery(queryString);
	 	return query.getResultList();
	}

}
