package com.spirit.pos.client.medianet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.spirit.exception.GenericBusinessException;

public class Respuesta {

	int tiempoEsperaLecturaArchivo = 2; //segundos
	int numeroIntentos = 3;
	
	private Integer codigoRespuesta = null;
	private int nCodigoRespuesta = 2;
	private String mensajeRespuestaHost = null;
	private int nMensajeRespuestaHost = 16; 
	private String tipoCredito = null;
	private int nTipoCredito = 6;
	private Integer tipoCuenta = null;
	private int nTipoCuenta = 1;
	private String nombreTarjetaHabiente = null;
	private int nNombreTarjetaHabiente = 30;
	private Integer numeroAutorizacion = null;
	private int nNumeroAutorizacion = 6;
	private String emisorTarjeta = null;
	private int nEmisorTarjeta = 20;
	private Integer numeroReferencia = null;
	private int nNumeroReferencia = 6;
	private String numeroTarjeta = null;
	private int nNumeroTarjeta = 22;
	private String fechaExpiracion = null;
	private int nFechaExpiracion = 4;		//aamm
	private String codigoComercio = null;
	private int nCodigoComercio = 15;
	private Integer numeroTerminalIdPos = null;
	private int nNumeroTerminalIdPos = 8;
	private Integer numeroLote = null;
	private int nNumeroLote = 6;
	private String fechaTransaccion = null;	//ddmmaaaa
	private int nFechaTransaccion = 8;
	private String horaTransaccion = null;	//hhmmss
	private int nHoraTransaccion = 6;
	private Long valorInteres = null;
	private int nValorInteres = 12;
	private Long saldoCuenta = null;
	private int nSaldoCuenta = 12;
	private Long disponibleCuenta = null;
	private int nDisponibilidadCuenta = 12;
	private String filler = null;
	private int nFiller = 6;
	
	public void leerRespuesta(){
		
	}
	
	private String leerArchivo(String directorioArchivo,String nombreArchivo) throws GenericBusinessException{
		String linea = null;
		File directorio = new File(directorioArchivo);
		if (directorio.isDirectory()){
			File archivo = new File(directorio,nombreArchivo);
			boolean leerArchivo = true;
			while( leerArchivo ){
				if (archivo.exists()){
					try {
						FileReader fr = new FileReader(archivo);
						BufferedReader br = new BufferedReader(fr);
						linea = br.readLine();
						br.close();
						fr.close();
						
						separarLineaRespuesta(linea);
						
						leerArchivo = false;
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						throw new GenericBusinessException("Archivo no Encontrado !!");
					} catch (IOException e) {
						e.printStackTrace();
						throw new GenericBusinessException("Error en la lectura del archivo de Respuesta !!");
					}
				} else {
					try {
						Thread.sleep(tiempoEsperaLecturaArchivo*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
		
		return linea;
	}
	
	private void separarLineaRespuesta(String linea){
		
		int contador = 0; 
		codigoRespuesta = Integer.parseInt( linea.substring(0,nCodigoRespuesta) );
		contador += nCodigoRespuesta;
		mensajeRespuestaHost = linea.substring(contador,contador+nMensajeRespuestaHost);
		contador += nMensajeRespuestaHost;
		tipoCredito = linea.substring(contador,contador+nTipoCredito);
		contador += nTipoCredito;
		tipoCuenta = Integer.parseInt( linea.substring(contador,contador+nTipoCuenta) );
		contador += nTipoCuenta;
		nombreTarjetaHabiente = linea.substring(contador,contador+nNombreTarjetaHabiente);
		contador += nNombreTarjetaHabiente;
		numeroAutorizacion = Integer.parseInt( linea.substring(contador,contador+nNumeroAutorizacion) );
		contador += nNumeroAutorizacion;
		emisorTarjeta = linea.substring(contador,contador+nEmisorTarjeta);
		contador += nEmisorTarjeta;
		numeroReferencia = Integer.parseInt( linea.substring(contador,contador+nNumeroReferencia) );
		contador += nNumeroReferencia;
		numeroTarjeta = linea.substring(contador,contador+nNumeroTarjeta);
		contador += nNumeroTarjeta;
		fechaExpiracion = linea.substring(contador,contador+nFechaExpiracion);
		contador += nFechaExpiracion;
		codigoComercio = linea.substring(contador,contador+nCodigoComercio);
		contador += nCodigoComercio;
		numeroTerminalIdPos = Integer.parseInt( linea.substring(contador,contador+nNumeroTerminalIdPos) );
		contador += nNumeroTerminalIdPos;
		numeroLote = Integer.parseInt( linea.substring(contador,contador+nNumeroLote) );
		contador += nNumeroLote;
		fechaTransaccion = linea.substring(contador,contador+nFechaTransaccion);
		contador += nFechaTransaccion;
		horaTransaccion = linea.substring(contador,contador+nHoraTransaccion);
		contador += nHoraTransaccion;
		valorInteres = Long.parseLong( linea.substring(contador,contador+nValorInteres) );
		contador += nValorInteres;
		saldoCuenta = Long.parseLong( linea.substring(contador,contador+nSaldoCuenta) );
		contador += nSaldoCuenta;
		disponibleCuenta = Long.parseLong( linea.substring(contador,contador+nDisponibilidadCuenta) );
		contador += nDisponibilidadCuenta;
		filler = linea.substring(contador,contador+nFiller);
		contador += nFiller;
		
	}
	
	public Integer getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(Integer codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getMensajeRespuestaHost() {
		return mensajeRespuestaHost;
	}

	public void setMensajeRespuestaHost(String mensajeRespuestaHost) {
		this.mensajeRespuestaHost = mensajeRespuestaHost;
	}

	public String getTipoCredito() {
		return tipoCredito;
	}

	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	public Integer getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(Integer tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getNombreTarjetaHabiente() {
		return nombreTarjetaHabiente;
	}

	public void setNombreTarjetaHabiente(String nombreTarjetaHabiente) {
		this.nombreTarjetaHabiente = nombreTarjetaHabiente;
	}

	public Integer getNumeroAutorizacion() {
		return numeroAutorizacion;
	}

	public void setNumeroAutorizacion(Integer numeroAutorizacion) {
		this.numeroAutorizacion = numeroAutorizacion;
	}

	public String getEmisorTarjeta() {
		return emisorTarjeta;
	}

	public void setEmisorTarjeta(String emisorTarjeta) {
		this.emisorTarjeta = emisorTarjeta;
	}

	public Integer getNumeroReferencia() {
		return numeroReferencia;
	}

	public void setNumeroReferencia(Integer numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public String getCodigoComercio() {
		return codigoComercio;
	}

	public void setCodigoComercio(String codigoComercio) {
		this.codigoComercio = codigoComercio;
	}

	public Integer getNumeroTerminalIdPos() {
		return numeroTerminalIdPos;
	}

	public void setNumeroTerminalIdPos(Integer numeroTerminalIdPos) {
		this.numeroTerminalIdPos = numeroTerminalIdPos;
	}

	public Integer getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(Integer numeroLote) {
		this.numeroLote = numeroLote;
	}

	public String getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getHoraTransaccion() {
		return horaTransaccion;
	}

	public void setHoraTransaccion(String horaTransaccion) {
		this.horaTransaccion = horaTransaccion;
	}

	public Long getValorInteres() {
		return valorInteres;
	}

	public void setValorInteres(Long valorInteres) {
		this.valorInteres = valorInteres;
	}

	public Long getSaldoCuenta() {
		return saldoCuenta;
	}

	public void setSaldoCuenta(Long saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}

	public Long getDisponibleCuenta() {
		return disponibleCuenta;
	}

	public void setDisponibleCuenta(Long disponibleCuenta) {
		this.disponibleCuenta = disponibleCuenta;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public static void main(String[] args) {
		Respuesta r = new Respuesta();
		try {
			r.leerArchivo("C:\\IPOS\\RES", "ME004810.RES");
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		System.out.println(
			r.getCodigoRespuesta()+"\n"+
			r.getMensajeRespuestaHost()+"\n"+
			r.getTipoCredito()+"\n"
		);
	}
	
}
