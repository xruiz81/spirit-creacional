package com.spirit.log.session;

import javax.ejb.Local;

import com.spirit.log.entity.LogEnum.LogStatus;
import com.spirit.log.entity.LogEnum.TipoMensaje;
import com.spirit.log.entity.LogEnum.TipoRegistro;

@Local
public interface LogLocal {

	public void saveLog(TipoMensaje tipoMensaje, TipoRegistro tipoRegistro,
			String descripcion, LogStatus logStatus, Object object);

	public void saveLog(TipoMensaje tipoMensaje, TipoRegistro tipoRegistro,
			Exception ex);

	public void saveLog(TipoMensaje tipoMensaje, TipoRegistro tipoRegistro,
			String descripcion);
}
