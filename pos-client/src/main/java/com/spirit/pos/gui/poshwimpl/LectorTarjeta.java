package com.spirit.pos.gui.poshwimpl;

import javax.swing.JTextField;

import jpos.JposConst;
import jpos.JposException;
import jpos.MSR;
import jpos.MSRConst;
import jpos.events.DataEvent;
import jpos.events.ErrorEvent;

import com.spirit.util.Utilitarios;

public class LectorTarjeta extends ComponenteBasico {

	private MSR msr = null;
	private DatosTarjetaMagnetica datosTarjetaMagnetica = null;
	private boolean lecturaExpress = false;

	private JTextField txtNumeroCuenta = null;
	private JTextField txtNombre = null;
	private JTextField txtFechaCaducidad = null;
	
	
	public LectorTarjeta(String nombreLogico) {
		this.nombreLogico = nombreLogico;
		msr = MSRSingleton.getMSRSingleton();
	}
	
	 
	//DataListener
	public void dataOccurred(DataEvent de) {
		
		try {
			leerDatos();
			if ( getTxtNumeroCuenta() != null )
				getTxtNumeroCuenta() .setText(datosTarjetaMagnetica.getNumeroCuenta());
			if ( getTxtFechaCaducidad() != null )
				getTxtFechaCaducidad().setText(
					Utilitarios.getFechaMesAnioUppercase(datosTarjetaMagnetica.getCalendarFechaExpiracion().getTime()) );
			if ( getTxtNombre() != null )
				getTxtNombre() .setText(datosTarjetaMagnetica.getNombreCompletoDesdeTrack1("\\^", 1));
			msr.setDataEventEnabled(true);
			if (lecturaExpress){
				lecturaExpress = false;
				cerrar();
			}
		} catch (JposException je) {
			System.out.println("**********************************************");
			System.out.println("* Error en Evento de Lector de Tarjeta *");
			System.out.println("***********************************************");
			je.printStackTrace();
		} catch (Exception e) {
			System.out.println("**********************************************");
			System.out.println("* Error general en Evento de Lector de Tarjeta *");
			System.out.println("***********************************************");
			e.printStackTrace();
		}
		
	}

	private void leerDatos() throws JposException {
		/*
		System.out.println("**************************************************");
		System.out.println(" 1: "+decodeTrack(msr.getTrack1Data()));
		System.out.println(" 2: "+decodeTrack(msr.getTrack2Data()));
		System.out.println(" 3: "+decodeTrack(msr.getTrack3Data()));
		System.out.println(" 4: "+decodeTrack(msr.getTrack4Data()));
		System.out.println(" Primer Nombre    : "+msr.getFirstName());
		System.out.println(" Segundo Nombre   : "+msr.getMiddleInitial());
		System.out.println(" SurName          : "+msr.getSurname());
		System.out.println(" Numero Cuenta    : "+msr.getAccountNumber());
		System.out.println(" Fecha Expiracion : "+msr.getExpirationDate());
		System.out.println(" Titulo           : "+msr.getTitle());
		System.out.println("**************************************************");
		*/
		if ( datosTarjetaMagnetica != null ){
			datosTarjetaMagnetica.setTrack1(decodeTrack(msr.getTrack1Data()) );
			datosTarjetaMagnetica.setTrack2(decodeTrack(msr.getTrack2Data()) );
			datosTarjetaMagnetica.setTrack3(decodeTrack(msr.getTrack3Data()) );
			datosTarjetaMagnetica.setTrack4(decodeTrack(msr.getTrack4Data()) );
			datosTarjetaMagnetica.setPrimerNombre(msr.getFirstName() );
			datosTarjetaMagnetica.setSegundoNombreInicial(msr.getMiddleInitial() );
			datosTarjetaMagnetica.setApellido(msr.getSurname() );
			datosTarjetaMagnetica.setNumeroCuenta(msr.getAccountNumber() );
			datosTarjetaMagnetica.setFechaExpiracion(msr.getExpirationDate() );
			datosTarjetaMagnetica.setTitulo(msr.getTitle() );
		}
	}

	protected String decodeTrack(byte[] byteArray) {
		StringBuffer track = new StringBuffer();
		for (int i = 0; i < byteArray.length; ++i) {
			//System.out.println("- "+(char) byteArray[i]);
			track.append((char) byteArray[i]);
		}
		return track.toString();
	}
	
	//ErrorListener
	public void errorOccurred(ErrorEvent ee) {
		codigoError = "" + ee.getErrorCode();
		switch (ee.getErrorCode()) {
		case JposConst.JPOS_E_EXTENDED:
			codigoError += "=JPOS_E_EXTENDED";
			break;
		case JposConst.JPOS_E_FAILURE:
			codigoError += "=JPOS_E_FAILURE";
			break;
		}

		codigoErrorExtendido = "" + ee.getErrorCodeExtended();
		switch (ee.getErrorCodeExtended()) {
		case MSRConst.JPOS_EMSR_START:
			codigoErrorExtendido += "=JPOS_EMSR_START";
			break;
		case MSRConst.JPOS_EMSR_END:
			codigoErrorExtendido += "=JPOS_EMSR_END";
			break;
		case MSRConst.JPOS_EMSR_PARITY:
			codigoErrorExtendido += "=JPOS_EMSR_PARITY";
			break;
		case MSRConst.JPOS_EMSR_LRC:
			codigoErrorExtendido += "=JPOS_EMSR_LRC";
			break;
		}

		try {
			leerDatos();
		} catch (JposException e) {
			e.printStackTrace();
		}
		
		try {
			msr.clearInput();
		} catch (JposException je) {
			je.printStackTrace();
		}
	}
	
	public void iniciar() throws Exception {
		activo = false;
		if ( msr != null ){
			msr.addStatusUpdateListener(this);
			msr.addDataListener(this);
			msr.addErrorListener(this);
			try {
				msr.open(this.nombreLogico);
				System.out.println("abierto");
			} catch (JposException e) {
				e.printStackTrace();
				throw new Exception("Error al Iniciar Lector de Tarjeta !!\n"+e.getMessage());
			} catch(NoClassDefFoundError e){
				e.printStackTrace();
				throw new Exception("Clase no Encontrada: "+e.getMessage());
			} catch(Exception e){
				e.printStackTrace();
				throw new Exception("Error general: "+e.getMessage());
			}
			
			try {
				msr.claim(1000);
				System.out.println("control ON");
			} catch (JposException e) {
				e.printStackTrace();
				throw new Exception("Error al obtener control de Lector de Tarjeta !!\n"+e.getMessage());
			}
			
			if (msr.getCapPowerReporting() != JposConst.JPOS_PR_NONE) {
				msr.setPowerNotify(JposConst.JPOS_PN_ENABLED);
				System.out.println("PowerNotify = JPOS_PN_ENABLED");
			}
			msr.setDeviceEnabled(true);
			msr.setDataEventEnabled(true);
			msr.setErrorReportingType(MSRConst.MSR_ERT_TRACK);
			
			if (msr.getCapJISTwo() == true) {
				// This is a DBCS MSR, so only Tracks 2, 4 should be set
				msr.setTracksToRead(MSRConst.MSR_TR_2_4);
				System.out.println("setTracksToRead(MSR_TR_2_4)");
			} else {
				// This is an ISO MSR, all tracks can be read
				msr.setTracksToRead(MSRConst.MSR_TR_1_2_3_4);
				System.out.println("setTracksToRead(MSR_TR_1_2_3_4)");
			}
			System.out.println("Pasar Tarjeta !!");
			
			activo = true;
		} else {
			throw new Exception("No esta instanciado objeto MSR !!");
			
		}
	}

	public void cerrar() throws Exception{
		if ( msr!= null && activo ){
			
			msr.removeStatusUpdateListener(this);
			msr.removeDataListener(this);
			msr.removeErrorListener(this);
			
			try {
				if ( msr.getClaimed() ){
					msr.release();
					System.out.println("control OFF");
				}
			} catch (JposException e) {
				e.printStackTrace();
				throw new Exception("Error al liberar control de Lector de Tarjeta !!\n"+e.getMessage());
			}
			
			try {
				msr.close();
				System.out.println("apagado");
			} catch (JposException e) {
				e.printStackTrace();
				throw new Exception("Error al cerrar de Lector de Tarjeta !!\n"+e.getMessage());
			}
			
			activo = false;
		}
	}
	
	public DatosTarjetaMagnetica getDatosTarjetaMagnetica() {
		return datosTarjetaMagnetica;
	}
	
	public void leerTarjetaExpress(DatosTarjetaMagnetica datosTarjetaMagnetica) throws Exception{
		lecturaExpress = true;
		setDatosTarjetaMagnetica(datosTarjetaMagnetica);
		iniciar();
	}

	public void setDatosTarjetaMagnetica(DatosTarjetaMagnetica datosTarjetaMagnetica) {
		this.datosTarjetaMagnetica = datosTarjetaMagnetica;
	}


	public JTextField getTxtNumeroCuenta() {
		return txtNumeroCuenta;
	}


	public void setTxtNumeroCuenta(JTextField txtNumeroCuenta) {
		this.txtNumeroCuenta = txtNumeroCuenta;
	}


	public JTextField getTxtNombre() {
		return txtNombre;
	}


	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}


	public JTextField getTxtFechaCaducidad() {
		return txtFechaCaducidad;
	}


	public void setTxtFechaCaducidad(JTextField txtFechaCaducidad) {
		this.txtFechaCaducidad = txtFechaCaducidad;
	}

	/*public static void main(String[] args) {
		LectorTarjeta lt = new LectorTarjeta("MSR1");
		try {
			//lt.iniciar();
			//Thread.sleep(10000);
			//lt.cerrar();
			
			DatosTarjetaMagnetica dt = new DatosTarjetaMagnetica();
			lt.leerTarjetaExpress(dt);
			Thread.sleep(8000);
			
			//dt = new DatosTarjetaMagnetica();
			//lt.leerTarjetaExpress(dt);
			//Thread.sleep(10000);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lt = null;
		System.gc();
		System.exit(0);
	}*/
	
	
}
