package com.spirit.client;

import com.spirit.client.model.SpiritModel;



public class ActivePanel {

	
	private static ActivePanel singleton;

	private SpiritModel activePanel;

	public static ActivePanel getSingleton() {

		if (singleton == null) {
			singleton = new ActivePanel();

		}
		return singleton;
	}

	public SpiritModel getActivePanel() {
		return activePanel;
	}

	public void setActivePanel(SpiritModel activePanel) {
		this.activePanel = activePanel;
	}
}