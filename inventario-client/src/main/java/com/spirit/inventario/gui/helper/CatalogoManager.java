package com.spirit.inventario.gui.helper;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

public abstract class CatalogoManager implements CatalogoIf {
	protected List<CatalogoImpl> listaCatalogos;

	private void llenarCatalogo() {
		if (listaCatalogos == null) {
			listaCatalogos = new ArrayList<CatalogoImpl>();
			fillCatalogo();
		}
	}

	public CatalogoImpl getCatalogoByCodigo(String codigo) {
		llenarCatalogo();
		for (CatalogoImpl catalogo : listaCatalogos) {
			if (catalogo.getCodigo().equalsIgnoreCase(codigo))
				return catalogo;
		}
		return null;
	}

	public List<CatalogoImpl> getListaCalalogos() {
		llenarCatalogo();
		return listaCatalogos;
	}

	public Object[] getArray() {
		llenarCatalogo();
		return listaCatalogos.toArray();
	}

	public String getCodigoSeleccionado(JComboBox jComboBox) {
		Object objetoSeleccionado = jComboBox.getSelectedItem();
		if (objetoSeleccionado == null)
			return null;
		if (objetoSeleccionado instanceof CatalogoImpl) {
			CatalogoImpl catalogoImpl = (CatalogoImpl) objetoSeleccionado;
			return catalogoImpl.getCodigo();
		} else {
			return objetoSeleccionado.toString();
		}
	}

}
