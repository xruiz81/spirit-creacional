package com.spirit.schedule;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.interceptor.Interceptors;

import org.jboss.annotation.ejb.ResourceAdapter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.spirit.client.Parametros;
import com.spirit.exception.GenericBusinessException;
import com.spirit.pos.session.TarjetaSessionLocal;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "cronTrigger", propertyValue = "0 0 * * * ?") })
@ResourceAdapter("quartz-ra.rar")
public class ActualizarStatusTarjetaAfiliacion implements Job {
	@EJB private TarjetaSessionLocal tarjetaSessionLocal;
	@EJB private QuartzCheckLocal quartzCheckLocal;
	
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

		if (!quartzCheckLocal.validate(this))
			return;
		System.out.println("QUARTZ ACTUALIZACION STATUS TARJETAS DE AFILIACION");
		try {
			tarjetaSessionLocal.procesarActualizacionStatusTarjetas();			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			System.out.println("Se ha producido un error al actualizar status de tarjetas de afiliación");
		}
	}
}
