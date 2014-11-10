package com.spirit.schedule;

import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;

import org.jboss.annotation.ejb.ResourceAdapter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.spirit.client.Parametros;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.session.BodegaSessionLocal;
import com.spirit.inventario.session.StockSessionLocal;
import com.spirit.poscola.entity.PosColaIf;
import com.spirit.poscola.session.PosColaSessionLocal;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "cronTrigger", propertyValue = "15 0 1 * * ?") })
@ResourceAdapter("quartz-ra.rar")
public class CierreInventario implements Job {
	@EJB
	private BodegaSessionLocal bodegaSessionLocal;

	@EJB
	private StockSessionLocal stockSessionLocal;

	@EJB
	private PosColaSessionLocal posColaSessionLocal;
	
	@EJB private QuartzCheckLocal quartzCheckLocal;

	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		if (!quartzCheckLocal.validate(this))
			return;
		// SE EJECUTA EL PRIMERO DE CADA MES A LAS 8 DE LA MAÑANA
		System.out.println("EJECUCION DE CIERRE DE INVENTARIO");
		if (Parametros.standAlone) {
			System.out.println("Quartz desactivado");
			return;
		}
		try {
			PosColaIf posColaIf = posColaSessionLocal.obtenerInfoColaYO();
			if (posColaIf.getTipoServer().equalsIgnoreCase("P")) {
				List<BodegaIf> listaBodegas = (List<BodegaIf>) bodegaSessionLocal
						.getBodegaList();
				for (BodegaIf bodegaIf : listaBodegas) {
					System.out.println("CERRANDO STOCK EN BODEGA: "
							+ bodegaIf.getNombre());
					stockSessionLocal.cerrarStock(bodegaIf.getId());
					System.out.println("STOCK EN BODEGA: "
							+ bodegaIf.getNombre() + " CERRADA CON EXITO");
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();

		}
		System.out.println("CierreInventario");

	}
}
