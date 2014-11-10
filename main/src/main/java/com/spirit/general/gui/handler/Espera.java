package com.spirit.general.gui.handler;

import com.spirit.client.model.SpiritModel;
import com.spirit.client.model.SpiritModelImpl;

public class Espera implements Runnable{
	SpiritModel panel = null;
	public Espera(SpiritModel panel ){
		this.panel = panel;
	}
	
	public void run() {
		if ( panel instanceof SpiritModelImpl ){
			((SpiritModelImpl)panel).setCursorEspera();
			panel.report();
			((SpiritModelImpl)panel).setCursorNormal();
		} else {
			panel.report();
		}
	}
};