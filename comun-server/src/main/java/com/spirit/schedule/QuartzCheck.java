package com.spirit.schedule;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.quartz.Job;

import com.spirit.client.Parametros;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.quartz.entity.QtzActivoEJB;

@Stateless
public class QuartzCheck implements QuartzCheckLocal {

	@EJB
	private JPAManagerLocal jpaManagerLocal;

	public boolean validate(Job quartzBean) {
		if (Parametros.standAlone) {
			System.out.println("Stand alone, quartz desactivado");
			return false;
		}
		String qtzClass = quartzBean.getClass().getCanonicalName();
		System.out.println("REVISANDO PERMISOS PARA EJECUTAR QUARTZ "
				+ qtzClass);

		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("activo", "S");
		parametros.put("qtzClass", quartzBean.getClass().getCanonicalName());
		List<QtzActivoEJB> listaQtzActivo = jpaManagerLocal
				.singleClassQueryList(QtzActivoEJB.class, parametros);
		if (listaQtzActivo.isEmpty()) {
			System.out.println("QUARTZ " + parametros.get("qtzClass")
					+ " NO TIENE PERMISOS");
			return false;
		} else {
			System.out.println(parametros.get("qtzClass")+" OK");
			return true;
		}
	}

}
