package com.spirit.compras.gui.criteria;

import java.util.HashMap;
import java.util.Map;

import com.spirit.client.Criteria;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;

public abstract class CompraCriteriaBase extends Criteria {

	protected Map<Long, ClienteIf> mapaCliente = new HashMap<Long, ClienteIf>();
	protected Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	protected Map<Long, TipoDocumentoIf> mapaTipoDocumento = new HashMap<Long, TipoDocumentoIf>();
	
}
