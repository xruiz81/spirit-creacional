package com.spirit.medios.session;

import java.util.Collection;
import java.util.Properties;
import java.util.Vector;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.session.EmpleadoSessionLocal;
import com.spirit.general.session.ParametroEmpresaSessionLocal;
import com.spirit.general.util.EnviarCorreo;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.TiempoParcialDotDetalleEJB;
import com.spirit.medios.entity.TiempoParcialDotDetalleIf;
import com.spirit.medios.entity.TiempoParcialDotEJB;
import com.spirit.medios.entity.TiempoParcialDotIf;
import com.spirit.medios.session.generated._TiempoParcialDotSession;

/**
 * The <code>TiempoParcialDotSession</code> session bean, which acts as a facade
 * to the underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:15 $
 * 
 */
@Stateless
public class TiempoParcialDotSessionEJB extends _TiempoParcialDotSession
		implements TiempoParcialDotSessionRemote {

	@EJB private TiempoParcialDotDetalleSessionLocal tiempoParcialDetalleLocal;
	@EJB private EmpleadoSessionLocal empleadoLocal;
	@EJB private ParametroEmpresaSessionLocal parametroEmpresaLocal;

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	@Resource
	private SessionContext ctx;

	/*******************************************************************************************************************
	 * B U S I N E S S M E T H O D S
	 *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public TiempoParcialDotIf procesarTiempoParcialDot(
			TiempoParcialDotIf model,
			Collection<TiempoParcialDotDetalleIf> detalleTiempoParcialVector, String codigoOT, boolean enviarCorreo)
			throws com.spirit.exception.GenericBusinessException {
		try {
			TiempoParcialDotIf tiempoParcial = registrarTiempoParcial(model);
			System.out.println("TiempoParcialDotIf "
					+ tiempoParcial.getDescripcion() + " "
					+ tiempoParcial.getTiempo());

			if (tiempoParcial == null)
				return null;

			//si es nuevo se envia un correo de aviso de tarea nueva
			if (model.getId() == null || model.getId().longValue() == 0L){
				//guardo tiempo
				tiempoParcial = addTiempoParcialDot(tiempoParcial);
				enviarCorreo(model, codigoOT, enviarCorreo, tiempoParcial);		
				
			}else{
				//actualizo tiempo
				saveTiempoParcialDot(tiempoParcial);
			}

			tiempoParcial = getTiempoParcialDot(tiempoParcial.getId());

			for (TiempoParcialDotDetalleIf modelDetalle : detalleTiempoParcialVector) {
				modelDetalle.setIdTiempoParcialDot(tiempoParcial.getId());
				TiempoParcialDotDetalleIf tiempoParcialDetalle = registrarTiempoParcialDetalle(modelDetalle);
				System.out.println("TiempoParcialDotDetalleIf "
						+ tiempoParcial.getDescripcion() + " "
						+ tiempoParcialDetalle.getTiempo());
				if (modelDetalle.getId() == null
						|| modelDetalle.getId().longValue() == 0L)
					tiempoParcialDetalle = tiempoParcialDetalleLocal
							.addTiempoParcialDotDetalle(tiempoParcialDetalle);
				else {
					tiempoParcialDetalleLocal
							.saveTiempoParcialDotDetalle(tiempoParcialDetalle);
				}
			}

			Long tiempoSubTarea = tiempoParcialDetalleLocal
					.findTiempoTotalByTiempoDotId(tiempoParcial.getId());
			tiempoParcial.setTiempo(tiempoSubTarea);

			return tiempoParcial;
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(
					"Error al guardar informaci\u00f3n");
		}
	}

	private void enviarCorreo(TiempoParcialDotIf model, String codigoOT,
			boolean enviarCorreo, TiempoParcialDotIf tiempoParcial)
			throws GenericBusinessException {
		
		//si la opcion de envio de correo esta seteada como "S"
		Collection pmEnviarCorreo = parametroEmpresaLocal.findParametroEmpresaByCodigo("ENVIAR_CORREO_TIMETRACKER");
		if(pmEnviarCorreo.size() > 0){
			ParametroEmpresaIf parametroEnvioCorreo = (ParametroEmpresaIf)pmEnviarCorreo.iterator().next();
			if(parametroEnvioCorreo.getValor().equals("S")){
				
				String mailEmpleado = empleadoLocal.getEmpleado(model.getUsuarioAsignadoId()).getEmailOficina();
				if(enviarCorreo && mailEmpleado != null){
					//formo el cuerpo del mensaje
					String mensaje = "Descripción: \n" + tiempoParcial.getDescripcion();
					
					//enviarNotificacion(empleado.getEmailOficina(), "spirit@umacreativa.com", "SPumacreativa2012", ordenTrabajo.getCodigo());
					Collection hosts = parametroEmpresaLocal.findParametroEmpresaByCodigo("SMTP_HOST_NAME");
					Collection puertos = parametroEmpresaLocal.findParametroEmpresaByCodigo("SMTP_HOST_PORT");
					Collection remitentes = parametroEmpresaLocal.findParametroEmpresaByCodigo("SMTP_SPIRIT_AUTH_USER");
					Collection claves = parametroEmpresaLocal.findParametroEmpresaByCodigo("SMTP_SPIRIT_AUTH_PWD");
					if(hosts.size() > 0 && puertos.size() > 0 && remitentes.size() > 0 && claves.size() > 0){
						ParametroEmpresaIf host = (ParametroEmpresaIf)hosts.iterator().next();
						ParametroEmpresaIf puerto = (ParametroEmpresaIf)puertos.iterator().next();
						ParametroEmpresaIf remitente = (ParametroEmpresaIf)remitentes.iterator().next();
						ParametroEmpresaIf clave = (ParametroEmpresaIf)claves.iterator().next();
						
						String cuerpoCorreo = "Se le asignó una tarea en la OT: ";												
						String asunto = "Nueva Tarea en el Timetracker, OT: ";
						
						EnviarCorreo correo = new EnviarCorreo(host.getValor(), puerto.getValor(), remitente.getValor(), clave.getValor(), mailEmpleado, codigoOT, mensaje, asunto, cuerpoCorreo);
						correo.start();
					}
				}
			}
		}
	}
	
	//no se usa pero se dejo como ejemplo, se usa ahora la clase EnviarCorreo	
	public void enviarNotificacion(String host, String port, String from, String clave, String emailTo, String codigoOT, String mensaje){
		try
		{
			// Propiedades de la conexión
            Properties props = new Properties();//mail
            props.clear();
            props.setProperty("mail.smtp.host", host); //"110.110.2.102"
            props.setProperty("mail.smtp.auth", "FALSE");          
            props.setProperty("mail.smtp.port", port); //25          
            props.setProperty("mail.smtp.user", from); 
            props.setProperty("mail.smtp.pass", clave); 
            props.setProperty("mail.smtps.password", clave); 
          
             // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);
        
            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setText("Se le asignó una tarea en la OT: " + codigoOT + ".\n\n" + mensaje + ".\n\nPor favor para revisarla ingrese al Timetracker.\n\n(Importante: Por favor no responder este e-mail).");
            
            // Se compone el adjunto con la imagen
            BodyPart adjunto = new MimeBodyPart();            
            //adjunto.setDataHandler(new DataHandler(new FileDataSource("c:\\rolesEnviadosMail\\"+pdfNombre+".pdf")));
            //adjunto.setFileName(pdfNombre+".pdf");
            adjunto.setHeader( "Content-Type", "application/pdf;" );
            //adjunto.setHeader( "Content-Transfer-Encoding", "base64;");
            
            
            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            //multiParte.addBodyPart(adjunto);
           

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from)); //"tecnico@creacional.com"
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailTo)); //"sistemas@creacional.com"
			message.setSubject("Nueva Tarea en el Timetracker, OT: " + codigoOT);
			message.setContent(multiParte);
			
			// Lo enviamos.
			//Transport t = session.getTransport("smtp");//t.connect("sistemas@creacional.com","sistemas");
			Transport t = session.getTransport("smtp");
			t.connect();
			t.sendMessage(message, message.getAllRecipients());
			// Cierre.
			t.close();
            
        }
        catch (Exception e){
            e.printStackTrace();            
        }
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarTiempoParcialDot(java.lang.Long id)
			throws com.spirit.exception.GenericBusinessException {
		try {
			TiempoParcialDotEJB data = manager.find(TiempoParcialDotEJB.class,
					id);

			if (data != null) {
				Collection<TiempoParcialDotDetalleIf> detalles = tiempoParcialDetalleLocal
						.findTiempoParcialDotDetalleByIdTiempoParcialDot(data
								.getId());
				for (TiempoParcialDotDetalleIf detalle : detalles) {
					manager.remove(detalle);
				}
			}
			manager.remove(data);
			manager.flush();

		} catch (Exception e) {
			throw new GenericBusinessException(
					"Error al eliminar información en tiempoParcialDot.");
		}
	}

	private TiempoParcialDotIf registrarTiempoParcial(TiempoParcialDotIf model) {
		TiempoParcialDotIf tiempoParcialEJB;

		if (model.getId() == null || model.getId().longValue() == 0L) {
			tiempoParcialEJB = new TiempoParcialDotEJB();
			tiempoParcialEJB.setId(model.getId());
			tiempoParcialEJB.setDescripcion(model.getDescripcion());
			tiempoParcialEJB.setFechaInicio(model.getFechaInicio());
			tiempoParcialEJB.setFechaFin(model.getFechaFin());
			tiempoParcialEJB.setUsuarioAsignadoId(model.getUsuarioAsignadoId());
			tiempoParcialEJB.setIdOrdenTrabajoDetalle(model
					.getIdOrdenTrabajoDetalle());
		} else {
			tiempoParcialEJB = getTiempoParcialDot(model.getId());
			tiempoParcialEJB.setDescripcion(model.getDescripcion());
			tiempoParcialEJB.setUsuarioAsignadoId(model.getUsuarioAsignadoId());
			tiempoParcialEJB.setTiempo(model.getTiempo());
		}
		// tiempoParcialEJB = new TiempoParcialDotEJB(model);

		return tiempoParcialEJB;
	}

	private TiempoParcialDotDetalleIf registrarTiempoParcialDetalle(
			TiempoParcialDotDetalleIf model) throws GenericBusinessException {
		TiempoParcialDotDetalleIf detalle;
		if (model.getId() == null || model.getId().longValue() == 0L) {
			detalle = new TiempoParcialDotDetalleEJB();
			detalle.setIdTiempoParcialDot(model.getIdTiempoParcialDot());
			detalle.setFecha(model.getFecha());
			detalle.setHoraInicio(model.getHoraInicio());
			detalle.setHoraFin(model.getHoraFin());
			detalle.setTiempo(model.getTiempo());
		} else {
			detalle = tiempoParcialDetalleLocal
					.getTiempoParcialDotDetalle(model.getId());
			//if (detalle.getHoraInicio().equals(0L)) {
				detalle.setFecha(model.getFecha());
				detalle.setHoraInicio(model.getHoraInicio());
				detalle.setHoraFin(model.getHoraFin());
				detalle.setTiempo(model.getTiempo());
			//}
			// detalle = new TiempoParcialDotDetalleEJB(model);
		}

		return detalle;
	}

}
