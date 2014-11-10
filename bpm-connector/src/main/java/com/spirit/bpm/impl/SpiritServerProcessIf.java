package com.spirit.bpm.impl;

import com.spirit.bpm.process.SpiritBPMConnectorIf;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.ClientParams;
import com.spirit.bpm.process.elements.InstanciaProceso;

public interface SpiritServerProcessIf {
	public SpiritBPMConnectorIf getSpiritBPMConnector();

	public InstanciaProceso iniciarProceso(ClientParams clientParams,
			String descripcion) throws BPMException;
}
