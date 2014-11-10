package chequesemitidos;

import java.sql.Date;

public class ChequeEmitidoDatos {

	Long id = null;
	Date fecha = null;
	Long cuentaBancariaId = null;
	String numero = null;
	String detalle = null;
	Double valor = 0D;
	Long tipoDocumentoId = null;
	Long transaccionId = null;
	String estado = "E";
	String beneficiario = null;
	Date fechaCobro = null;
	String origen = null;
	
	public ChequeEmitidoDatos(Long id,
			Date fecha,Long cuentaBancariaId,String numero,String detalle
			,Double valor,Long tipoDocumentoId,Long transaccionId,String estado,String beneficiario
			,Date fechaCobro,String origen){
		
		this.id = id;
		this.fecha=fecha ;
		this.cuentaBancariaId=cuentaBancariaId ;
		this.numero= numero;
		this.detalle= detalle;
		this.valor=valor ;
		this.tipoDocumentoId=tipoDocumentoId ;
		this.transaccionId=transaccionId ;
		this.estado = estado;
		this.beneficiario = beneficiario;
		this.fechaCobro = fechaCobro;
		this.origen = origen;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getCuentaBancariaId() {
		return cuentaBancariaId;
	}

	public void setCuentaBancariaId(Long cuentaBancariaId) {
		this.cuentaBancariaId = cuentaBancariaId;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getTipoDocumentoId() {
		return tipoDocumentoId;
	}

	public void setTipoDocumentoId(Long tipoDocumentoId) {
		this.tipoDocumentoId = tipoDocumentoId;
	}

	public Long getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Long transaccionId) {
		this.transaccionId = transaccionId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Date getFechaCobro() {
		return fechaCobro;
	}

	public void setFechaCobro(Date fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return fecha+" - "+detalle+" - "+numero+" - "+valor+" - "+beneficiario;
	}
	
}
