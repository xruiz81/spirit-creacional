package com.spirit.inventario.gui.helper;

public class CatalogoEstadoSolicitudTRF extends CatalogoManager {
	public void fillCatalogo() {
		this.listaCatalogos.add(new CatalogoImpl("P", "PENDIENTE"));
		this.listaCatalogos.add(new CatalogoImpl("E", "ENVIADA"));
		this.listaCatalogos.add(new CatalogoImpl("A", "ATENDIDA"));
	}
}
