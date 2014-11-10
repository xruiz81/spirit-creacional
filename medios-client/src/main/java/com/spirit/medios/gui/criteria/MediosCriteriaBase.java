package com.spirit.medios.gui.criteria;

import java.util.HashMap;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.medios.entity.OrdenTrabajoIf;

public abstract class MediosCriteriaBase extends Criteria {

	protected Map<Long, ClienteIf> mapaCliente =  new HashMap<Long, ClienteIf>();
	protected Map<Long, ClienteOficinaIf> mapaClienteOficina =  new HashMap<Long, ClienteOficinaIf>();
	protected Map<Long, OrdenTrabajoIf> mapaOrdenTrabajo =  new HashMap<Long, OrdenTrabajoIf>();
}
