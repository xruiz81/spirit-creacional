package com.spirit.general.gui.main;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.general.gui.controller.JDQuickSearchModel;

public class SpiritBackgroundModel extends SpiritBackground {
	public SpiritBackgroundModel() {
		new HotKeyComponent(this);
	}
	
	public void find() {
		// TODO Auto-generated method stub
		
	}

	public void save() {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void clean() {
		// TODO Auto-generated method stub
		
	}

	public void report() {
		// TODO Auto-generated method stub
		
	}

	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showSaveMode() {
		// TODO Auto-generated method stub
		
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
}
