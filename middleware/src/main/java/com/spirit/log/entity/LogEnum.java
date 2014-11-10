package com.spirit.log.entity;

public class LogEnum {
	public enum TipoMensaje {
		AUTOMATIC_CALL, MAIL, JMS_MESSAGE
	}

	public enum TipoRegistro {
		IN, OUT, PROCESS
	}

	public enum LogStatus {
		START, ERROR, LOG_PROCESS, END
	}

	public enum Modulos {
		INVENTARIO, CRM, NOMINA, FACTURACION, MENSAJERIA
	}
}
