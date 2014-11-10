package com.spirit.client;

import java.io.Serializable;

import com.spirit.client.model.SpiritModel;
import com.spirit.exception.GenericBusinessException;

public abstract class CriteriaTarea implements Serializable {
	
	public abstract String getNombreCriteria();
	public abstract String getNombrePanel();
	public abstract void realizarAccion() throws GenericBusinessException;
	public abstract SpiritModel getPanel() ;

}
