package com.spirit.bpm.process;

import java.io.Serializable;

import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.Proceso;

public enum SpiritProcessDefinition implements Serializable {
	TEST("MiProceso", "1.0"), COMPRA("ProcesoCompra", "1.0"), ORDEN_TRABAJO(
			"Orden_de_Trabajo", "1.0"), PRESUPUESTO("Orden_de_Trabajo_Detalle",
			"1.0");

	private String nombreProceso;
	private String version;

	private SpiritProcessDefinition(String nombreProceso, String version) {
		this.nombreProceso = nombreProceso;
		this.version = version;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public String getVersion() {
		return version;
	}

	public static SpiritProcessDefinition getSpiritProcessDefinition(
			Proceso proceso) throws BPMException {
		String nombreProceso = proceso.getNombre();
		for (SpiritProcessDefinition spiritProcessDefinition : values()) {
			if (spiritProcessDefinition.getNombreProceso().equalsIgnoreCase(
					nombreProceso))
				return spiritProcessDefinition;
		}
		throw new BPMException("Proceso : " + nombreProceso + " no encontrado");
	}
}
