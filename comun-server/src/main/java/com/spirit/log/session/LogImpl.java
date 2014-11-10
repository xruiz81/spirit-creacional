package com.spirit.log.session;

import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.log.entity.LogSistemaEJB;
import com.spirit.log.entity.LogEnum.LogStatus;
import com.spirit.log.entity.LogEnum.TipoMensaje;
import com.spirit.log.entity.LogEnum.TipoRegistro;

@Stateless
public class LogImpl implements LogLocal {
	private Logger log = Logger.getLogger(this.getClass());

	@EJB
	private JPAManagerLocal jpLocal;

	@Override
	public void saveLog(TipoMensaje tipoMensaje, TipoRegistro tipoRegistro,
			String descripcion, LogStatus logStatus, Object object) {
		try {
			log.debug("GUARDANDO EN TABLAS DE LOG");
			LogSistemaEJB logSistemaEJB = new LogSistemaEJB();
			logSistemaEJB.setDescripcion(descripcion);
			logSistemaEJB.setFechaTransaccion(new Timestamp(new Date()
					.getTime()));
			logSistemaEJB.setTipoMensaje(tipoMensaje.name());
			logSistemaEJB.setTipoRegistro(tipoRegistro.name());
			logSistemaEJB.setObject(null);
			logSistemaEJB.setError(logStatus.name());
			jpLocal.persist(logSistemaEJB);
		} catch (Exception e) {
			log.error("ERROR AL GUARDAR TABLA DE LOG", e);
		}

	}

	public void saveLog(TipoMensaje tipoMensaje, TipoRegistro tipoRegistro,
			String descripcion) {
		saveLog(tipoMensaje, tipoRegistro, descripcion, LogStatus.LOG_PROCESS, null);
	}

	@Override
	public void saveLog(TipoMensaje tipoMensaje, TipoRegistro tipoRegistro,
			Exception ex) {
		saveLog(tipoMensaje, tipoRegistro, ex.getMessage(),LogStatus.ERROR, null);

	}
}
