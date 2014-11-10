package com.spirit.general.session;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioDocumentoEJB;
import com.spirit.general.entity.UsuarioDocumentoIf;
import com.spirit.general.session.generated._UsuarioDocumentoSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * The <code>UsuarioDocumentoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:16 $
 *
 */
@Stateless
public class UsuarioDocumentoSessionEJB extends _UsuarioDocumentoSession implements UsuarioDocumentoSessionRemote  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(UsuarioDocumentoSessionEJB.class);
   
   @Resource private SessionContext ctx;

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public java.util.Collection findUsuarioDocumentoByEmpresaId(java.lang.Long idEmpresa) throws GenericBusinessException {	 
	   String queryString = "select ud from UsuarioDocumentoEJB ud, UsuarioEJB u, EmpresaEJB e where  ud.usuarioId = u.id and u.empresaId = e.id and e.id = " + idEmpresa + " order by ud.id asc";
	   Query query = manager.createQuery(queryString);
	   return query.getResultList();
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void procesarUsuarioDocumento(Map<Long,Vector<UsuarioDocumentoIf>> mapaUsuarioDocumento) throws GenericBusinessException {
	   try{
		   Iterator itMapa = mapaUsuarioDocumento.keySet().iterator();
		   while( itMapa.hasNext() ){
			   Vector<UsuarioDocumentoIf> vectorUsuarioDocumento = mapaUsuarioDocumento.get( itMapa.next() );
			   for ( UsuarioDocumentoIf model : vectorUsuarioDocumento ){
				   UsuarioDocumentoIf usuarioDocumento = registrarUsuarioDocumento(model);
				   manager.persist(usuarioDocumento);
			   }
		   }
		   manager.flush();
	   } catch (Exception e) {
			ctx.setRollbackOnly();
			throw new GenericBusinessException("Error al guardar información en UsuarioDocumento");
	   }
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void actualizarUsuarioDocumento(Map<Long,Vector<UsuarioDocumentoIf>> mapaUsuarioDocumento,Map<Long,Vector<UsuarioDocumentoIf>> mapaEliminadosUsuarioDocumento) throws GenericBusinessException {
	   try{
		   Iterator itMapa = mapaUsuarioDocumento.keySet().iterator();
		   while( itMapa.hasNext() ){
			   Vector<UsuarioDocumentoIf> vectorUsuarioDocumento = mapaUsuarioDocumento.get( itMapa.next() );
			   for ( UsuarioDocumentoIf model : vectorUsuarioDocumento ){
				   UsuarioDocumentoIf usuarioDocumento = registrarUsuarioDocumento(model);
				   if ( model.getId() != null )
					   manager.merge(usuarioDocumento);
				   else
					   manager.persist(usuarioDocumento);
			   }
		   }
		   
		   itMapa = mapaEliminadosUsuarioDocumento.keySet().iterator();
		   while( itMapa.hasNext() ){
			   Vector<UsuarioDocumentoIf> vectorEliminadosUsuarioDocumento = mapaEliminadosUsuarioDocumento.get( itMapa.next() );
			   for ( UsuarioDocumentoIf model : vectorEliminadosUsuarioDocumento ){
				   if ( model.getId() != null ){
					   model = getUsuarioDocumento(model.getId());
					   manager.remove(model);
				   }
			   }
		   }
		   manager.flush();
		   
	   } catch (Exception e) {
		   e.printStackTrace();
		   ctx.setRollbackOnly();
		   throw new GenericBusinessException("Error general al actualizar en Usuario-Documento");
	   }
   }
   
   private UsuarioDocumentoEJB registrarUsuarioDocumento(UsuarioDocumentoIf model){
	   UsuarioDocumentoEJB usuarioDocumento = new UsuarioDocumentoEJB();
	   usuarioDocumento.setId(model.getId());
	   usuarioDocumento.setDocumentoId(model.getDocumentoId());
	   usuarioDocumento.setEstado(model.getEstado());
	   usuarioDocumento.setPermisoAutorizar(model.getPermisoAutorizar());
	   usuarioDocumento.setPermisoBorrado(model.getPermisoBorrado());
	   usuarioDocumento.setPermisoImpresion(model.getPermisoImpresion());
	   usuarioDocumento.setPermisoRegistro(model.getPermisoRegistro());
	   usuarioDocumento.setUsuarioId(model.getUsuarioId());
	   return usuarioDocumento;
   }

}
