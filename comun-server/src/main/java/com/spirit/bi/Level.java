package com.spirit.bi;

import java.util.List;

import com.spirit.server.SpiritIf;

public abstract class Level extends Dimension {
	@Override
	protected void persist(Object objectDimension) {
	}

	@Override
	public Object consultar(Object origenID) {
		return EJBDataSource.getJpaManagerLocal().find(getDataClass(),
				(Long) origenID);
	}

	@Override
	public void fillDimension() {
		List<SpiritIf> l = EJBDataSource.getJpaManagerLocal().singleClassList(
				getDataClass());
		for (SpiritIf o : l) {
			putInMap(o.getId(), o);
		}
	}

	@Override
	public Object construir(Object objetoConsulta) {
		return objetoConsulta;
	}
}
