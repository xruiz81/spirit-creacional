package com.spirit.bi;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.beanutils.BeanUtils;

import com.spirit.bi.entity.BiClienteDimEJB;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.server.SpiritIf;

public abstract class Dimension {
	protected Long ID_SIN = 9999L;
	private HashMap<Long, Object> mapaDimension = new HashMap<Long, Object>();

	public Dimension() {
	}

	protected Object getFromMap(Long key) {
		return mapaDimension.get(key);
	}

	protected void putInMap(Long key, Object value) {
		mapaDimension.put(key, value);
	}

	protected void persist(Object objectDimension) {
		EJBDataSource.getJpaManagerLocal().persist(objectDimension);
	}

	public Object getObjetoDimension(Object origenID) {
		Object objectDimension = null;
		if (origenID == null) {
			objectDimension = mapaDimension.get(ID_SIN);
			if (objectDimension == null) {
				objectDimension = construirBlanco();
				persist(objectDimension);
				mapaDimension.put(ID_SIN, objectDimension);
			} else {
				Statistics.addCacheHits();
			}
			return objectDimension;
		}
		objectDimension = mapaDimension.get(origenID);
		if (objectDimension == null) {
			Statistics.addQuerys();
			// CONSULTAR
			Object objetoConsulta = consultar(origenID);
			// CONSTRUIR
			objectDimension = construir(objetoConsulta);
			persist(objectDimension);
			// GUARDAR EN MAPA
			mapaDimension.put((Long) origenID, objectDimension);
		} else {
			Statistics.addCacheHits();
		}
		return objectDimension;
	}

	public abstract Object construir(Object objetoConsulta);

	public abstract Object construirBlanco();

	public abstract Object consultar(Object origenID);

	public abstract Class getDataClass();

	public void clean() {
		List l = EJBDataSource.getJpaManagerLocal().singleClassList(
				getDataClass());
		EntityManager manager = EJBDataSource.getJpaManagerLocal().getManager();
		for (Object o : l) {
			manager.remove(o);
		}
		manager.flush();
		mapaDimension.clear();
	}

	public void fillDimension() throws Exception {
		List<Object> l = EJBDataSource.getJpaManagerLocal().singleClassList(
				getDataClass());
		for (Object o : l) {
			putInMap(Long.valueOf(BeanUtils.getProperty(o, "origenId")), o);
		}

	}

}
