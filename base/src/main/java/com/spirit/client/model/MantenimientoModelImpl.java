package com.spirit.client.model;

public abstract class MantenimientoModelImpl extends SpiritModelImpl {

	public abstract void save();
	public abstract void delete();
	public abstract void update();
	public abstract void clean();
	public abstract void showSaveMode();
	public abstract boolean validateFields();

	public void refresh(){
		//TODO
	}
	
	public void duplicate() {
		//TODO
	}

	public void report() {
		// Mantenimiento no usa report.
	}
	
	public void find() {
		// Mantenimiento no usa find.
	}

	public boolean isEmpty() {
    // Mantenimiento no usa isEmpty().
		return false;
	}

	public void showFindMode() {
		showSaveMode();
	}

	public void showUpdateMode() {
		setUpdateMode();
	}

	public void addDetail() {
		// Mantenimiento no usa addDetail
		
	}

	public void updateDetail() {
		// Mantenimiento no usa updateDetail
		
	}
	
	public void deleteDetail() {
		// Mantenimiento no usa deleteDetail
	}

	public void quickSearch() {
		System.out.println("Quick Search desde Mantenimiento Model");
	}
}
