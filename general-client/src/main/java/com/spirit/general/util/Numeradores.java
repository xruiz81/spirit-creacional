package com.spirit.general.util;

import com.spirit.client.SpiritAlert;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.NumeradoresIf;
import com.spirit.general.session.NumeradoresSessionService;
import com.spirit.servicelocator.ServiceLocator;

public class Numeradores {
	
	public static int getUltimoValor(String tabla, Long idEmpresa) {
		int num = 0;
		try {
			NumeradoresIf numeradores = (NumeradoresIf)getNumeradoresSessionService().findNumeradoresByNombreTablaAndByEmpresaId(tabla,idEmpresa).iterator().next();
			num = numeradores.getUltimoValor().intValue();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return num;
	}
	
	public static NumeradoresIf getNumeradorId(String tabla , Long idEmpresa) {
		NumeradoresIf numerador = null;
		try {
			NumeradoresIf numeradores = (NumeradoresIf)getNumeradoresSessionService().findNumeradoresByNombreTablaAndByEmpresaId(tabla,idEmpresa).iterator().next();
			numerador = numeradores;
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return numerador;
	}
	
	public static NumeradoresSessionService getNumeradoresSessionService() {
		try {
			return (NumeradoresSessionService) ServiceLocator
					.getService(ServiceLocator.NUMERADORESSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

}