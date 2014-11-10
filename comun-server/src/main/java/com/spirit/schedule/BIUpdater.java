package com.spirit.schedule;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.interceptor.Interceptors;

import org.jboss.annotation.ejb.ResourceAdapter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.spirit.bi.impl.BiSessionLocal;
import com.spirit.client.Parametros;
import com.spirit.performance.session.PerformanceInterceptor;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "cronTrigger", propertyValue = "0 0 * * * ?") })
@ResourceAdapter("quartz-ra.rar")
public class BIUpdater implements Job {
	@EJB
	private BiSessionLocal biSessionLocal;

	@EJB
	private QuartzCheckLocal quartzCheckLocal;

	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		if (!quartzCheckLocal.validate(this))
			return;
		System.out.println("ACTUALIZACION DE MODELO BI");
		biSessionLocal.updateBIModel();
	}
}
