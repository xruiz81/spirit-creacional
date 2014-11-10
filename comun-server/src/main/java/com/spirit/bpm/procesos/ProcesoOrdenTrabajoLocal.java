package com.spirit.bpm.procesos;

import javax.ejb.Local;
import javax.naming.NamingException;

import com.spirit.bpm.campana.ProcesoOrdenTrabajoSessionService;
import com.spirit.general.entity.EmpresaIf;

@Local
public interface ProcesoOrdenTrabajoLocal {

	public ProcesoOrdenTrabajoSessionService getProcesoOrdenTrabajoSessionService(EmpresaIf empresaIf) throws NamingException;
	
}
