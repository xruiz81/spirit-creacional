package com.spirit.bi;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.spirit.bi.entity.BiFacturacionFactEJB;

public abstract class FactTable {
	private List<Dimension> listaDimesiones = new ArrayList<Dimension>();

	protected void addDimension(Dimension d) {
		listaDimesiones.add(d);
	}

	protected List<Dimension> getListaDimesiones() {
		return listaDimesiones;
	}

	public void cleanDimensions() {
		for (Dimension d : listaDimesiones) {
			d.clean();
		}
	}

	private void fillDimensions() throws Exception {
		for (Dimension d : listaDimesiones) {
			d.fillDimension();
		}
	}

	public void clean() {
		List<Object> listaEJB = EJBDataSource.getJpaManagerLocal()
				.singleClassList(getDataClass());
		EntityManager manager = EJBDataSource.getJpaManagerLocal().getManager();
		for (Object object : listaEJB) {
			manager.remove(object);
		}
		manager.flush();
	}

	public void doFill() throws Exception {
		fillDimensions();
		fill();
	}

	public abstract Class getDataClass();

	protected abstract void fill() throws Exception;

	protected abstract void initDimentions();

	public FactTable() {
		listaDimesiones.clear();
		initDimentions();
	}
	
	public abstract void update() throws Exception;

}
