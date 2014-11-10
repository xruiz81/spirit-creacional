package com.spirit.seguridad.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.seguridad.entity.UsuarioCuentaEJB;
import com.spirit.seguridad.entity.UsuarioCuentaIf;
import com.spirit.seguridad.session.generated._UsuarioCuentaSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>UsuarioCuentaSession</code> session bean, which acts as a facade
 * to the underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:18 $
 * 
 */
@Stateless
public class UsuarioCuentaSessionEJB extends _UsuarioCuentaSession implements UsuarioCuentaSessionRemote, UsuarioCuentaSessionLocal {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(UsuarioCuentaSessionEJB.class);
	
	@Resource private SessionContext ctx;

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void procesarUsuarioCuenta(Long idUsuario, List<UsuarioCuentaIf> modelUsuarioCuentaList) throws GenericBusinessException {
		try {
			for (UsuarioCuentaIf modelUsuarioCuenta : modelUsuarioCuentaList) {
				modelUsuarioCuenta.setUsuarioId(idUsuario);
				UsuarioCuentaEJB usuarioCuenta = registrarUsuarioCuenta(modelUsuarioCuenta);
				manager.persist(usuarioCuenta);
			}
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al guardar información en Usuario Cuenta.", e);
			throw new GenericBusinessException("Error al insertar información en Usuario Cuenta");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarUsuarioCuenta(Long idUsuario, List<UsuarioCuentaIf> modelUsuarioCuentaList) throws GenericBusinessException {
		try{
			eliminarCuentasUsuario(idUsuario);
			manager.flush();
			
			for (UsuarioCuentaIf modelUsuarioCuenta : modelUsuarioCuentaList) {
				modelUsuarioCuenta.setUsuarioId(idUsuario);
				UsuarioCuentaEJB usuarioCuenta = registrarUsuarioCuenta(modelUsuarioCuenta);
				manager.merge(usuarioCuenta);
			}
		} catch(Exception e){
			ctx.setRollbackOnly();
			e.printStackTrace();
			log.error("Error al actualizar información en Usuario Cuenta.", e);
			throw new GenericBusinessException("Se ha producido un error en la actualización de la Cuenta de Usuario");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
 	public UsuarioCuentaEJB registrarUsuarioCuenta(UsuarioCuentaIf modelUsuarioCuenta) {
		UsuarioCuentaEJB usuarioCuenta = new UsuarioCuentaEJB();
		usuarioCuenta.setId(modelUsuarioCuenta.getId());
		usuarioCuenta.setUsuarioId(modelUsuarioCuenta.getUsuarioId());
		usuarioCuenta.setCuentaId(modelUsuarioCuenta.getCuentaId());
		return usuarioCuenta;
 	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private void eliminarCuentasUsuario(Long idUsuario) {
		Iterator cuentasUsuarioIterator = findUsuarioCuentaByUsuarioId(idUsuario).iterator();

		while (cuentasUsuarioIterator.hasNext()) {
			UsuarioCuentaEJB usuarioCuenta = (UsuarioCuentaEJB) cuentasUsuarioIterator.next();
			manager.remove(usuarioCuenta);
		}

	}

}
