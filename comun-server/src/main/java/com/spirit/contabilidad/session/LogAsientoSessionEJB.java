package com.spirit.contabilidad.session;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.LogAsientoDetalleEJB;
import com.spirit.contabilidad.entity.LogAsientoEJB;
import com.spirit.contabilidad.session.generated._LogAsientoSession;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 * The <code>LogAsientoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 *
 */
@Stateless
public class LogAsientoSessionEJB extends _LogAsientoSession implements LogAsientoSessionRemote, LogAsientoSessionLocal {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;
   @EJB private UtilitariosSessionLocal utilitariosLocal;
   @EJB private AsientoDetalleSessionLocal asientoDetalleLocal;
   @Resource private SessionContext ctx; 

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

   public void procesarLogAsiento(AsientoIf asiento, List<AsientoDetalleIf> asientoDetalleList, String log, UsuarioIf usuario) throws ParseException {
	   LogAsientoEJB logAsiento = null;
	   try {
		   log += log + (", REALIZADO POR: " + usuario.getUsuario() + ", FECHA: " + utilitariosLocal.getFechaUppercase(utilitariosLocal.dateHoy()) );
		   logAsiento = registrarLogAsiento(asiento,log);
		   manager.persist(logAsiento);
		   //manager.flush();
		   
		   for (AsientoDetalleIf modelDetalle : asientoDetalleList) {
			   if (logAsiento != null) {
				   LogAsientoDetalleEJB logAsientoDetalle = registrarLogAsientoDetalle(modelDetalle, asiento.getId(), log);
				   logAsientoDetalle.setLogAsientoId(logAsiento.getPrimaryKey());
				   manager.merge(logAsientoDetalle);
			   }
		   }
	   } catch (ParseException pe) {
		   pe.printStackTrace();
		   ctx.setRollbackOnly();
		   throw new ParseException("Se ha producido un error al actualizar el asiento", pe.getErrorOffset());
	   }
   }
   
   private LogAsientoEJB registrarLogAsiento(AsientoIf model, String log) {
		LogAsientoEJB logAsiento = new LogAsientoEJB();
		logAsiento.setNumero(model.getNumero());
		logAsiento.setEmpresaId(model.getEmpresaId());
		logAsiento.setPeriodoId(model.getPeriodoId());
		logAsiento.setPlancuentaId(model.getPlancuentaId());
		logAsiento.setFecha(new java.sql.Date(model.getFecha().getTime()));
		logAsiento.setStatus(model.getStatus());
		logAsiento.setEfectivo(model.getEfectivo());
		logAsiento.setSubtipoasientoId(model.getSubtipoasientoId());
		logAsiento.setObservacion(model.getObservacion());
		logAsiento.setOficinaId(model.getOficinaId());
		logAsiento.setTipoDocumentoId(model.getTipoDocumentoId());
		logAsiento.setTransaccionId(model.getTransaccionId());
		//logAsiento.setElaboradoPorId(model.getElaboradoPorId());
		//logAsiento.setAutorizadoPorId(model.getAutorizadoPorId());
		logAsiento.setLog(log);
		
		return logAsiento;
	}
   
   private LogAsientoDetalleEJB registrarLogAsientoDetalle(AsientoDetalleIf modelDetalle, Long asientoId, String log) {
		LogAsientoDetalleEJB logAsientoDetalle = new LogAsientoDetalleEJB();
		logAsientoDetalle.setCuentaId(modelDetalle.getCuentaId());
		logAsientoDetalle.setLogAsientoId(modelDetalle.getAsientoId());
		logAsientoDetalle.setReferencia(modelDetalle.getReferencia());
		logAsientoDetalle.setGlosa(modelDetalle.getGlosa());
		logAsientoDetalle.setCentrogastoId(modelDetalle.getCentrogastoId());
		logAsientoDetalle.setEmpleadoId(modelDetalle.getEmpleadoId());
		logAsientoDetalle.setDepartamentoId(modelDetalle.getDepartamentoId());
		logAsientoDetalle.setLineaId(modelDetalle.getLineaId());
		logAsientoDetalle.setClienteId(modelDetalle.getClienteId());
		logAsientoDetalle.setDebe(modelDetalle.getDebe());
		logAsientoDetalle.setHaber(modelDetalle.getHaber());
		logAsientoDetalle.setLog("DETALLE DEL " + log );
		
		return logAsientoDetalle;
	}

}
