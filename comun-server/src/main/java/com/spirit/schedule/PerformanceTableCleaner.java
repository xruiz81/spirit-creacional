package com.spirit.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.persistence.EntityManager;

import org.jboss.annotation.ejb.ResourceAdapter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.performance.entity.PerformanceEJB;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "cronTrigger", propertyValue = "0 0 1 * * ?") })
@ResourceAdapter("quartz-ra.rar")
public class PerformanceTableCleaner implements Job {
	@EJB
	private JPAManagerLocal jpaManagerLocal;

	@EJB
	private QuartzCheckLocal quartzCheckLocal;

	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		if (!quartzCheckLocal.validate(this))
			return;
		// SE EJECUTA EL PRIMERO DE CADA MES A LAS 8 DE LA MAÑANA
		System.out.println("LIMPIANDO TABLA DE PERFORMANCE");

		HashMap<String, Object> parametros = new HashMap<String, Object>();
		Date fechaHoy = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaHoy);
		cal.add(Calendar.MONTH, -1);
		Date fechaMesAnterior = cal.getTime();

		parametros.put("fechaInicio", fechaMesAnterior);
		List<PerformanceEJB> listaPerformanceEJB = jpaManagerLocal
				.executeQueryList(
						"SELECT p FROM PerformanceEJB p where fechaInicio <=:fechaInicio",
						parametros);
		EntityManager manager = jpaManagerLocal.getManager();
		for (PerformanceEJB performanceEJB : listaPerformanceEJB) {
			System.out.println(performanceEJB);
			manager.remove(performanceEJB);
		}
		manager.flush();

	}
}
