package com.spirit.performance.session;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.performance.entity.PerformanceEJB;

public class PerformanceInterceptor {
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	@AroundInvoke
	public Object performaceCheck(InvocationContext ic) throws Exception {
		Date fechaInicio = new Date();
		long start = System.currentTimeMillis();
		Object ret = ic.proceed();
		Date fechaFin = new Date();
		long end = System.currentTimeMillis();
		long timeSegundos = (end - start) / 1000;
		long timeMinutos = timeSegundos / 60;

		if (timeSegundos > 0) {
			try {
				PerformanceEJB p = new PerformanceEJB();
				p.setEjb(ic.getTarget().getClass().getCanonicalName());
				p.setFechaInicio(fechaInicio);
				p.setFechaFin(fechaFin);
				p.setMethod(ic.getMethod().getName());
				p.setSignature(ic.getMethod().toGenericString());
				p.setTiempoMinutos(BigDecimal.valueOf(timeMinutos));
				p.setTiempoSegundos(BigDecimal.valueOf(timeSegundos));
				manager.persist(p);
				manager.flush();
			} catch (Exception e) {
				System.out
						.println("ERROR AL GUARDAR REGISTRO DE PerformanceEJB");
				e.printStackTrace();
			}
		}

		return ret;
	}
}
