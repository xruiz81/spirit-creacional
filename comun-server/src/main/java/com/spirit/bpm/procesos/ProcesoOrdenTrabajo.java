package com.spirit.bpm.procesos;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.spirit.bpm.BPMInterceptor;
import com.spirit.bpm.campana.ProcesoOrdenTrabajoSessionService;
import com.spirit.general.entity.EmpresaIf;

@Stateless
@Interceptors(value = BPMInterceptor.class)
public class ProcesoOrdenTrabajo implements ProcesoOrdenTrabajoLocal {

	public ProcesoOrdenTrabajoSessionService getProcesoOrdenTrabajoSessionService(EmpresaIf empresaIf) throws NamingException
	{
		if(empresaIf.getNombre().contains("CREACIONAL"))
		{
			//remoteBean = context.lookup(JNDIName.earName + serviceName
			//		+ "/remote");
			return (ProcesoOrdenTrabajoSessionService) new InitialContext()
				.lookup("spirit/ProcesoOrdenTrabajoCreacionalSessionEJB/local");
		}
		return null;
	}
	
}
