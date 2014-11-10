package com.spirit.client;

import java.util.EventObject;

public class ChangeModeEvent extends EventObject {

	private static final long serialVersionUID = -1308706190093085362L;
	private String mode;
	
	public ChangeModeEvent(Object source, String mode) {
		super(source);
		this.mode = mode;
		
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
