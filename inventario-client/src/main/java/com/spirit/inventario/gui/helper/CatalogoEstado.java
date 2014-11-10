package com.spirit.inventario.gui.helper;

public class CatalogoEstado extends CatalogoManager {
	public void fillCatalogo() {
		this.listaCatalogos.add(new CatalogoImpl("A", "ACTIVO"));
		this.listaCatalogos.add(new CatalogoImpl("I", "INACTIVO"));
		this.listaCatalogos.add(new CatalogoImpl("P", "PENDIENTE"));
	}
}
