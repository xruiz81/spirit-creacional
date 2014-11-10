package com.spirit.pos.gui.poshwimpl;

import jpos.JposConst;
import jpos.JposException;
import jpos.LineDisplay;
import jpos.LineDisplayConst;
import jpos.events.StatusUpdateEvent;

public class Mensajero extends ComponenteBasico {

	private LineDisplay lineDisplay = null;
	public static int NUMERO_CARACTERES_POR_FILA = 19;
	public static int NUMERO_FILAS = 2;

	public Mensajero(String nombreLogico) {
		lineDisplay = LineDisplaySingleton.getInstance();
		this.nombreLogico = nombreLogico;
	}

	//
	public void statusUpdateOccurred(StatusUpdateEvent e) {
		estado = Integer.valueOf(e.getStatus());
		mensajeEstado = "";
		switch (estado.intValue()) {
		case JposConst.JPOS_SUE_POWER_ONLINE:
			mensajeEstado += "Encendido";
			break;

		case JposConst.JPOS_SUE_POWER_OFF_OFFLINE:
			mensajeEstado += "";
			break;
		case JposConst.JPOS_SUE_POWER_OFFLINE:
			mensajeEstado += "Apagado";
			break;
		}
		System.out.println(mensajeEstado);

	}

	public void iniciar() throws Exception {

		// lineDisplay.addStatusUpdateListener(this);
		lineDisplay.open(nombreLogico);

		try {
			if (lineDisplay.getCapPowerReporting() != JposConst.JPOS_PR_NONE) {
				lineDisplay.setPowerNotify(JposConst.JPOS_PN_ENABLED);

			}
		} catch (JposException e) {
			e.printStackTrace();
			throw new Exception("Error al establecer modo de impresion !!");
		}
		try {
			lineDisplay.claim(1000);
		} catch (Exception e) {
			throw new Exception("Error al obtener control !!");
		}

		lineDisplay.setDeviceEnabled(true);

		activo = true;
		limpiar();
		System.out.println("Mensajero iniciado !!");
	}

	public void limpiar() throws Exception {
		if (lineDisplay != null && activo)
			try {
				lineDisplay.clearText();
			} catch (Exception e) {
				throw new Exception("Error al limpiar pantalla !!");
			}
	}

	public int getNumeroLineasDisponibles() throws Exception {
		if (lineDisplay != null)
			try {
				return lineDisplay.getDeviceRows();
			} catch (JposException e) {
				e.printStackTrace();
				throw new Exception(
						"Error al obtener Numero de Lineas Disponibles !!");
			} catch (Exception e) {
				throw new Exception(
						"Error general al obtener Numero de Lineas Disponibles !!");
			}
		return 0;
	}

	private String ajustarLinea(String linea){
		StringBuffer sLinea = new StringBuffer(linea);
		if ( linea != null ){
			if ( linea.length()==0 ){
				return linea;
			} else if ( sLinea.length() < NUMERO_CARACTERES_POR_FILA+1 ){
				for ( int i = sLinea.length() ; i<=NUMERO_CARACTERES_POR_FILA  ; i++)
					sLinea.append(" ");
			} else {
				return linea.substring(0,NUMERO_CARACTERES_POR_FILA+1);
			}
			return sLinea.toString();
		}
		return "";
	}
	
	public void mostrarTexto(String[] lineas) throws Exception {
		int numeroFilasDisponibles = getNumeroLineasDisponibles();
		//int numeroFilas = Math.min(numeroFilasDisponibles, lineas.length);
		try {
			if (numeroFilasDisponibles == 1 && lineas.length >= 1) {
				lineDisplay.displayText(lineas[0],
						jpos.LineDisplayConst.DISP_DT_NORMAL);
			} else if (numeroFilasDisponibles > 1 && lineas.length >= 1) {
				if ( lineas.length == 1 ){
					lineDisplay.displayText(ajustarLinea(lineas[0]),
						jpos.LineDisplayConst.DISP_DT_NORMAL);
				} else if ( lineas.length == 2 ){
					lineDisplay.displayText(ajustarLinea(lineas[0]),
							jpos.LineDisplayConst.DISP_DT_NORMAL);
					lineDisplay.displayText(ajustarLinea(lineas[1]),
							jpos.LineDisplayConst.DISP_DT_NORMAL);
				} else {
					lineDisplay.displayText(ajustarLinea("SOLO HASTA 2 LINEAS SOPORTADAS"),
							jpos.LineDisplayConst.DISP_DT_NORMAL);
				}

			}
			if (lineDisplay.getCapDescriptors() == true) {
				if (lineDisplay.getCapBlink() != LineDisplayConst.DISP_CB_NOBLINK) {
					lineDisplay.setDescriptor(0, LineDisplayConst.DISP_SD_BLINK);
					System.out.println("setDescriptors(0,DISP_SD_ON)");
				} else {
					lineDisplay.setDescriptor(0, LineDisplayConst.DISP_SD_ON);
					System.out.println("setDescriptors(0,DISP_SD_ON)");
				}
			}

		} catch (JposException e) {
			e.printStackTrace();
			throw new Exception("Error al imprimir Linea !!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error general al imprimir Linea !!");
		}

		
	}

	public void cerrar() throws Exception {
		if (lineDisplay != null && activo) {
			// limpiar();
			lineDisplay.removeStatusUpdateListener(this);
			try {
				if (lineDisplay.getClaimed())
					lineDisplay.release();
			} catch (JposException e) {
				e.printStackTrace();
				throw new Exception("Error al liberar control de Mensajero!!");
			}

			try {
				lineDisplay.close();
			} catch (JposException e) {
				e.printStackTrace();
				throw new Exception("Error al cerrar Mensajero!!");
			}
			activo = false;
			System.out.println("Mensajero cerrado !!");
		}
	}
	
	public void mostrarTextoExpress(String[] lineas) throws Exception{
		iniciar();
		mostrarTexto(lineas);
		cerrar();
	}
	
	/*public static void main(String[] args) {
		Mensajero m = new Mensajero("LineDisplay1");

		try {
			m.iniciar();
			m.mostrarTexto(new String[] { "" });
			m.cerrar();
			
			//m.mostrarTextoExpress(new String[]{"SANCOCHO ","CABEZA DE V..."});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}*/

}
