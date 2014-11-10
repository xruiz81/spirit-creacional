package com.spirit.pos.client.medianet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import com.spirit.client.SpiritAlert;
import com.spirit.exception.GenericBusinessException;

public class Requerimiento {

	private String dosDigitos = "00";
	private String seisDigitos = "000000";
	private String doceDigitos = seisDigitos+seisDigitos;
	private String treintaDigitos = doceDigitos+doceDigitos+seisDigitos;
	
	private DecimalFormat formatoSeisDogitos = new DecimalFormat(seisDigitos);
	
	private Integer tipoTransaccion = null;
	private Integer secuencialCaja = null;
	private Integer numeroReferenciaAnulacion = 0;
	private Long montoTotalTransaccion = null;
	private Long valorIva = null;
	private Integer codigoCajero = null;
	private String filler = treintaDigitos;
	
	private String formarRequerimiento() throws GenericBusinessException{
		String requerimiento = null;
		DecimalFormat formatoDosDogitos = new DecimalFormat(dosDigitos);
		DecimalFormat formatoSeisDogitos = new DecimalFormat(seisDigitos);
		DecimalFormat formatoDoceDogitos = new DecimalFormat(doceDigitos);
		DecimalFormat formatoTreintaDogitos = new DecimalFormat(treintaDigitos);
		try{
		requerimiento =
			formatoDosDogitos.format(tipoTransaccion) +
			formatoSeisDogitos.format(secuencialCaja) + 
			formatoSeisDogitos.format(numeroReferenciaAnulacion) +
			formatoDoceDogitos.format(montoTotalTransaccion) +
			formatoDoceDogitos.format(valorIva) +
			formatoSeisDogitos.format(codigoCajero) +
			filler;
		} catch(Exception e){
			e.printStackTrace();
			throw new GenericBusinessException("Error en la formacion de la linea de requerimiento !!");
		}
		
		return requerimiento;
	}
	
	private String obtenerNumeroSecuencialArchivo(String rutaDirectorioRespuestas){
		File directorioRespuestas = new File(rutaDirectorioRespuestas);
		FileFilter ff = new FileFilter(){
			public boolean accept(File pathname) {
				if ( pathname.getName().toLowerCase().endsWith(".req") )
					return true;
				return false;
			}
		};
		File[] archivos = directorioRespuestas.listFiles(ff);
		int nuevo = 1;
		if ( archivos != null ){
			ArrayList<Integer> lista = new ArrayList<Integer>();
			for ( File a : archivos ){
				lista.add(Integer.parseInt(a.getName().substring(2,8)));
			}
			if ( lista.size() > 0 ){
				Collections.sort(lista);
				int ultimo = lista.get(lista.size()-1);
				nuevo = ultimo + 1;
			}
		}
		secuencialCaja = nuevo;
		return formatoSeisDogitos.format(nuevo);
	}
	
	private String obtenerNombreArchivo(String secuencial){
		return "ME"+secuencial+".REQ";
	}
	
	public void crearArchivoRequerimiento(String directorioArchivo) throws GenericBusinessException{
		
		String sec = null;
		if ( secuencialCaja == null )
			sec = obtenerNumeroSecuencialArchivo(directorioArchivo);
		else 
			sec = formatoSeisDogitos.format(secuencialCaja);
		
		String linea = formarRequerimiento();
		File directorio = new File(directorioArchivo);
		if (directorio.isDirectory()){
			
			String nombreArchivo = obtenerNombreArchivo(sec); 
			File archivo = new File(directorio,nombreArchivo);
			if ( !archivo.exists() ){
				try {
					FileWriter fw = new FileWriter(archivo);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(linea);
					bw.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new GenericBusinessException("Error al crear Archivo de Requerimiento !!");
				}
			} else {
				SpiritAlert.createAlert("Archivo de Requerimiento con nombre "+nombreArchivo+" ya existe !!", SpiritAlert.ERROR);
			}
		} else {
			SpiritAlert.createAlert("Directorio "+directorioArchivo+" No existe !!", SpiritAlert.ERROR);
		}
	}
	
	public Integer getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(Integer tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public Integer getSecuencialCaja() {
		return secuencialCaja;
	}

	public void setSecuencialCaja(Integer secuencialCaja) {
		this.secuencialCaja = secuencialCaja;
	}

	public Integer getNumeroReferenciaAnulacion() {
		return numeroReferenciaAnulacion;
	}

	public void setNumeroReferenciaAnulacion(Integer numeroReferenciaAnulacion) {
		this.numeroReferenciaAnulacion = numeroReferenciaAnulacion;
	}

	public Long getMontoTotalTransaccion() {
		return montoTotalTransaccion;
	}

	public void setMontoTotalTransaccion(Long montoTotalTransaccion) {
		this.montoTotalTransaccion = montoTotalTransaccion;
	}

	public Long getValorIva() {
		return valorIva;
	}

	public void setValorIva(Long valorIva) {
		this.valorIva = valorIva;
	}

	public Integer getCodigoCajero() {
		return codigoCajero;
	}

	public void setCodigoCajero(Integer codigoCajero) {
		this.codigoCajero = codigoCajero;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public static void main(String[] args) {
		Requerimiento r = new Requerimiento();
		r.setTipoTransaccion(2);
		//System.out.println( r.obtenerNumeroSecuencialArchivo("C:\\IPOS\\REQ") );
		r.setMontoTotalTransaccion(112L);
		r.setValorIva(12L);
		r.setCodigoCajero(1);
		
		try {
			r.crearArchivoRequerimiento("C:\\IPOS\\REQ");
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			
		}
	}
	
}
