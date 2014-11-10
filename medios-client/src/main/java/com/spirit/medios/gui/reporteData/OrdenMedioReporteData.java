package com.spirit.medios.gui.reporteData;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.GregorianCalendar;

public class OrdenMedioReporteData implements Serializable {

	private static final long serialVersionUID = 1021461991846073750L;
	private String fechaEmision;
	private String proveedorOficina;
	private String proveedor;
	private String rucProveedor;
	private String mes;
	private String cliente;
	private String ordenTrabajo;
	private String planMedio;
	private String elaboradoPor;
	private String ordenCanje;
	private String nota2;
	private String notaPersonalizada;
	private String campania;
	private String numeroOrden;
	private String producto;
	private String versiones;
	private String porcentajeIVA;
	private BigDecimal suma;
	private BigDecimal descuento;
	private BigDecimal bonificacion;
	private BigDecimal iva;
	private String porcentajeDescuento;
	private String porcentajeBonificacion;
	private BigDecimal totalOrden;
	
	private String porcentajeRetencionRenta;
	private String porcentajeRetencionIva;
	private BigDecimal retencionRenta;
	private BigDecimal retencionIva;
	private BigDecimal totalPagar;
	
	private String hora;
	private String programa;
	private String version;
	private String dia01;
	private String dia02;
	private String dia03;
	private String dia04;
	private String dia05;
	private String dia06;
	private String dia07;
	private String dia08;
	private String dia09;
	private String dia10;
	private String dia11;
	private String dia12;
	private String dia13;
	private String dia14;
	private String dia15;
	private String dia16;
	private String dia17;
	private String dia18;
	private String dia19;
	private String dia20;
	private String dia21;
	private String dia22;
	private String dia23;
	private String dia24;
	private String dia25;
	private String dia26;
	private String dia27;
	private String dia28;
	private String dia29;
	private String dia30;
	private String dia31;
	//ADD 25 MAYO
	private String diaLetra01;
	private String diaLetra02;
	private String diaLetra03;
	private String diaLetra04;
	private String diaLetra05;
	private String diaLetra06;
	private String diaLetra07;
	private String diaLetra08;
	private String diaLetra09;
	private String diaLetra10;
	private String diaLetra11;
	private String diaLetra12;
	private String diaLetra13;
	private String diaLetra14;
	private String diaLetra15;
	private String diaLetra16;
	private String diaLetra17;
	private String diaLetra18;
	private String diaLetra19;
	private String diaLetra20;
	private String diaLetra21;
	private String diaLetra22;
	private String diaLetra23;
	private String diaLetra24;
	private String diaLetra25;
	private String diaLetra26;
	private String diaLetra27;
	private String diaLetra28;
	private String diaLetra29;
	private String diaLetra30;
	private String diaLetra31;
	//END 25 MAYO
	private String cunias;
	private String valor;
	private String total;
	private int dia;
	//ADD 24 MAYO
	private String diaLetra;
	//END 24 MAYO
	private Date fechaPrograma;
	//ADD 26 
	//ONLY TO ORDENES DE MEDIO AGRUPADAS X CANAL
	private BigDecimal valorBig;
	//private ArrayList<String> productosVersion;
	//END 26 MAYO
	
	
	public OrdenMedioReporteData() {
		fechaEmision = "";
		proveedorOficina = "";
		proveedor = "";
		rucProveedor = "";
		mes = "";
		cliente = "";
		ordenTrabajo = "";
		planMedio = "";
		elaboradoPor = "";
		ordenCanje = "";
		nota2 = "";
		notaPersonalizada = "";
		campania = "";
		numeroOrden = "";
		producto = "";
		versiones = "";
		porcentajeIVA = "";
		suma = BigDecimal.ZERO;
		descuento = BigDecimal.ZERO;
		bonificacion = BigDecimal.ZERO;
		iva = BigDecimal.ZERO;
		porcentajeDescuento = "";
		porcentajeBonificacion = "";
		totalOrden = BigDecimal.ZERO;
		hora = "";
		programa = "";
		version = "";
		dia01 = "";
		dia02 = "";
		dia03 = "";
		dia04 = "";
		dia05 = "";
		dia06 = "";
		dia07 = "";
		dia08 = "";
		dia09 = "";
		dia10 = "";
		dia11 = "";
		dia12 = "";
		dia13 = "";
		dia14 = "";
		dia15 = "";
		dia16 = "";
		dia17 = "";
		dia18 = "";
		dia19 = "";
		dia20 = "";
		dia21 = "";
		dia22 = "";
		dia23 = "";
		dia24 = "";
		dia25 = "";
		dia26 = "";
		dia27 = "";
		dia28 = "";
		dia29 = "";
		dia30 = "";
		dia31 = "";
		//ADD 25 MAYO
		diaLetra01 = "";
		diaLetra02 = "";
		diaLetra03 = "";
		diaLetra04 = "";
		diaLetra05 = "";
		diaLetra06 = "";
		diaLetra07 = "";
		diaLetra08 = "";
		diaLetra09 = "";
		diaLetra10 = "";
		diaLetra11 = "";
		diaLetra12 = "";
		diaLetra13 = "";
		diaLetra14 = "";
		diaLetra15 = "";
		diaLetra16 = "";
		diaLetra17 = "";
		diaLetra18 = "";
		diaLetra19 = "";
		diaLetra20 = "";
		diaLetra21 = "";
		diaLetra22 = "";
		diaLetra23 = "";
		diaLetra24 = "";
		diaLetra25 = "";
		diaLetra26 = "";
		diaLetra27 = "";
		diaLetra28 = "";
		diaLetra29 = "";
		diaLetra30 = "";
		diaLetra31 = "";
		//END 25 MAYO
		cunias = "";
		valor = "";
		total = "";		
		dia = 0;
		//ADD 24 MAYO
		diaLetra = "";
		//END 24 MAYO
		//ADD 26 MAYO
		valorBig = new BigDecimal(0);
		//END 26 MAYO
		fechaPrograma = new java.sql.Date((new GregorianCalendar()).getTimeInMillis());
		
		porcentajeRetencionRenta = "";
		porcentajeRetencionIva = "";
		retencionRenta = new BigDecimal(0);
		retencionIva = new BigDecimal(0);
		totalPagar = new BigDecimal(0);
	}

	public String getPorcentajeRetencionRenta() {
		return porcentajeRetencionRenta;
	}

	public void setPorcentajeRetencionRenta(String porcentajeRetencionRenta) {
		this.porcentajeRetencionRenta = porcentajeRetencionRenta;
	}

	public String getPorcentajeRetencionIva() {
		return porcentajeRetencionIva;
	}

	public void setPorcentajeRetencionIva(String porcentajeRetencionIva) {
		this.porcentajeRetencionIva = porcentajeRetencionIva;
	}

	public BigDecimal getRetencionRenta() {
		return retencionRenta;
	}

	public void setRetencionRenta(BigDecimal retencionRenta) {
		this.retencionRenta = retencionRenta;
	}

	public BigDecimal getRetencionIva() {
		return retencionIva;
	}

	public void setRetencionIva(BigDecimal retencionIva) {
		this.retencionIva = retencionIva;
	}

	public BigDecimal getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(BigDecimal totalPagar) {
		this.totalPagar = totalPagar;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getProveedorOficina() {
		return proveedorOficina;
	}

	public void setProveedorOficina(String proveedorOficina) {
		this.proveedorOficina = proveedorOficina;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(String ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public String getPlanMedio() {
		return planMedio;
	}

	public void setPlanMedio(String planMedio) {
		this.planMedio = planMedio;
	}

	public String getElaboradoPor() {
		return elaboradoPor;
	}

	public void setElaboradoPor(String elaboradoPor) {
		this.elaboradoPor = elaboradoPor;
	}

	public String getOrdenCanje() {
		return ordenCanje;
	}

	public void setOrdenCanje(String ordenCanje) {
		this.ordenCanje = ordenCanje;
	}

	public String getNota2() {
		return nota2;
	}

	public void setNota2(String nota2) {
		this.nota2 = nota2;
	}

	public String getNotaPersonalizada() {
		return notaPersonalizada;
	}

	public void setNotaPersonalizada(String notaPersonalizada) {
		this.notaPersonalizada = notaPersonalizada;
	}

	public String getCampania() {
		return campania;
	}

	public void setCampania(String campania) {
		this.campania = campania;
	}

	public String getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getVersiones() {
		return versiones;
	}

	public void setVersiones(String versiones) {
		this.versiones = versiones;
	}

	public String getPorcentajeIVA() {
		return porcentajeIVA;
	}

	public void setPorcentajeIVA(String porcentajeIVA) {
		this.porcentajeIVA = porcentajeIVA;
	}

	public BigDecimal getSuma() {
		return suma;
	}

	public void setSuma(BigDecimal suma) {
		this.suma = suma;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(BigDecimal bonificacion) {
		this.bonificacion = bonificacion;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public String getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(String porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public String getPorcentajeBonificacion() {
		return porcentajeBonificacion;
	}

	public void setPorcentajeBonificacion(String porcentajeBonificacion) {
		this.porcentajeBonificacion = porcentajeBonificacion;
	}

	public BigDecimal getTotalOrden() {
		return totalOrden;
	}

	public void setTotalOrden(BigDecimal totalOrden) {
		this.totalOrden = totalOrden;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDia01() {
		return dia01;
	}

	public void setDia01(String dia01) {
		this.dia01 = dia01;
	}

	public String getDia02() {
		return dia02;
	}

	public void setDia02(String dia02) {
		this.dia02 = dia02;
	}

	public String getDia03() {
		return dia03;
	}

	public void setDia03(String dia03) {
		this.dia03 = dia03;
	}

	public String getDia04() {
		return dia04;
	}

	public void setDia04(String dia04) {
		this.dia04 = dia04;
	}

	public String getDia05() {
		return dia05;
	}

	public void setDia05(String dia05) {
		this.dia05 = dia05;
	}

	public String getDia06() {
		return dia06;
	}

	public void setDia06(String dia06) {
		this.dia06 = dia06;
	}

	public String getDia07() {
		return dia07;
	}

	public void setDia07(String dia07) {
		this.dia07 = dia07;
	}

	public String getDia08() {
		return dia08;
	}

	public void setDia08(String dia08) {
		this.dia08 = dia08;
	}

	public String getDia09() {
		return dia09;
	}

	public void setDia09(String dia09) {
		this.dia09 = dia09;
	}

	public String getDia10() {
		return dia10;
	}

	public void setDia10(String dia10) {
		this.dia10 = dia10;
	}

	public String getDia11() {
		return dia11;
	}

	public void setDia11(String dia11) {
		this.dia11 = dia11;
	}

	public String getDia12() {
		return dia12;
	}

	public void setDia12(String dia12) {
		this.dia12 = dia12;
	}

	public String getDia13() {
		return dia13;
	}

	public void setDia13(String dia13) {
		this.dia13 = dia13;
	}

	public String getDia14() {
		return dia14;
	}

	public void setDia14(String dia14) {
		this.dia14 = dia14;
	}

	public String getDia15() {
		return dia15;
	}

	public void setDia15(String dia15) {
		this.dia15 = dia15;
	}

	public String getDia16() {
		return dia16;
	}

	public void setDia16(String dia16) {
		this.dia16 = dia16;
	}

	public String getDia17() {
		return dia17;
	}

	public void setDia17(String dia17) {
		this.dia17 = dia17;
	}

	public String getDia18() {
		return dia18;
	}

	public void setDia18(String dia18) {
		this.dia18 = dia18;
	}

	public String getDia19() {
		return dia19;
	}

	public void setDia19(String dia19) {
		this.dia19 = dia19;
	}

	public String getDia20() {
		return dia20;
	}

	public void setDia20(String dia20) {
		this.dia20 = dia20;
	}

	public String getDia21() {
		return dia21;
	}

	public void setDia21(String dia21) {
		this.dia21 = dia21;
	}

	public String getDia22() {
		return dia22;
	}

	public void setDia22(String dia22) {
		this.dia22 = dia22;
	}

	public String getDia23() {
		return dia23;
	}

	public void setDia23(String dia23) {
		this.dia23 = dia23;
	}

	public String getDia24() {
		return dia24;
	}

	public void setDia24(String dia24) {
		this.dia24 = dia24;
	}

	public String getDia25() {
		return dia25;
	}

	public void setDia25(String dia25) {
		this.dia25 = dia25;
	}

	public String getDia26() {
		return dia26;
	}

	public void setDia26(String dia26) {
		this.dia26 = dia26;
	}

	public String getDia27() {
		return dia27;
	}

	public void setDia27(String dia27) {
		this.dia27 = dia27;
	}

	public String getDia28() {
		return dia28;
	}

	public void setDia28(String dia28) {
		this.dia28 = dia28;
	}

	public String getDia29() {
		return dia29;
	}

	public void setDia29(String dia29) {
		this.dia29 = dia29;
	}

	public String getDia30() {
		return dia30;
	}

	public void setDia30(String dia30) {
		this.dia30 = dia30;
	}

	public String getDia31() {
		return dia31;
	}

	public void setDia31(String dia31) {
		this.dia31 = dia31;
	}

	public String getDiaLetra01() {
		return diaLetra01;
	}

	public void setDiaLetra01(String diaLetra01) {
		this.diaLetra01 = diaLetra01;
	}

	public String getDiaLetra02() {
		return diaLetra02;
	}

	public void setDiaLetra02(String diaLetra02) {
		this.diaLetra02 = diaLetra02;
	}

	public String getDiaLetra03() {
		return diaLetra03;
	}

	public void setDiaLetra03(String diaLetra03) {
		this.diaLetra03 = diaLetra03;
	}

	public String getDiaLetra04() {
		return diaLetra04;
	}

	public void setDiaLetra04(String diaLetra04) {
		this.diaLetra04 = diaLetra04;
	}

	public String getDiaLetra05() {
		return diaLetra05;
	}

	public void setDiaLetra05(String diaLetra05) {
		this.diaLetra05 = diaLetra05;
	}

	public String getDiaLetra06() {
		return diaLetra06;
	}

	public void setDiaLetra06(String diaLetra06) {
		this.diaLetra06 = diaLetra06;
	}

	public String getDiaLetra07() {
		return diaLetra07;
	}

	public void setDiaLetra07(String diaLetra07) {
		this.diaLetra07 = diaLetra07;
	}

	public String getDiaLetra08() {
		return diaLetra08;
	}

	public void setDiaLetra08(String diaLetra08) {
		this.diaLetra08 = diaLetra08;
	}

	public String getDiaLetra09() {
		return diaLetra09;
	}

	public void setDiaLetra09(String diaLetra09) {
		this.diaLetra09 = diaLetra09;
	}

	public String getDiaLetra10() {
		return diaLetra10;
	}

	public void setDiaLetra10(String diaLetra10) {
		this.diaLetra10 = diaLetra10;
	}

	public String getDiaLetra11() {
		return diaLetra11;
	}

	public void setDiaLetra11(String diaLetra11) {
		this.diaLetra11 = diaLetra11;
	}

	public String getDiaLetra12() {
		return diaLetra12;
	}

	public void setDiaLetra12(String diaLetra12) {
		this.diaLetra12 = diaLetra12;
	}

	public String getDiaLetra13() {
		return diaLetra13;
	}

	public void setDiaLetra13(String diaLetra13) {
		this.diaLetra13 = diaLetra13;
	}

	public String getDiaLetra14() {
		return diaLetra14;
	}

	public void setDiaLetra14(String diaLetra14) {
		this.diaLetra14 = diaLetra14;
	}

	public String getDiaLetra15() {
		return diaLetra15;
	}

	public void setDiaLetra15(String diaLetra15) {
		this.diaLetra15 = diaLetra15;
	}

	public String getDiaLetra16() {
		return diaLetra16;
	}

	public void setDiaLetra16(String diaLetra16) {
		this.diaLetra16 = diaLetra16;
	}

	public String getDiaLetra17() {
		return diaLetra17;
	}

	public void setDiaLetra17(String diaLetra17) {
		this.diaLetra17 = diaLetra17;
	}

	public String getDiaLetra18() {
		return diaLetra18;
	}

	public void setDiaLetra18(String diaLetra18) {
		this.diaLetra18 = diaLetra18;
	}

	public String getDiaLetra19() {
		return diaLetra19;
	}

	public void setDiaLetra19(String diaLetra19) {
		this.diaLetra19 = diaLetra19;
	}

	public String getDiaLetra20() {
		return diaLetra20;
	}

	public void setDiaLetra20(String diaLetra20) {
		this.diaLetra20 = diaLetra20;
	}

	public String getDiaLetra21() {
		return diaLetra21;
	}

	public void setDiaLetra21(String diaLetra21) {
		this.diaLetra21 = diaLetra21;
	}

	public String getDiaLetra22() {
		return diaLetra22;
	}

	public void setDiaLetra22(String diaLetra22) {
		this.diaLetra22 = diaLetra22;
	}

	public String getDiaLetra23() {
		return diaLetra23;
	}

	public void setDiaLetra23(String diaLetra23) {
		this.diaLetra23 = diaLetra23;
	}

	public String getDiaLetra24() {
		return diaLetra24;
	}

	public void setDiaLetra24(String diaLetra24) {
		this.diaLetra24 = diaLetra24;
	}

	public String getDiaLetra25() {
		return diaLetra25;
	}

	public void setDiaLetra25(String diaLetra25) {
		this.diaLetra25 = diaLetra25;
	}

	public String getDiaLetra26() {
		return diaLetra26;
	}

	public void setDiaLetra26(String diaLetra26) {
		this.diaLetra26 = diaLetra26;
	}

	public String getDiaLetra27() {
		return diaLetra27;
	}

	public void setDiaLetra27(String diaLetra27) {
		this.diaLetra27 = diaLetra27;
	}

	public String getDiaLetra28() {
		return diaLetra28;
	}

	public void setDiaLetra28(String diaLetra28) {
		this.diaLetra28 = diaLetra28;
	}

	public String getDiaLetra29() {
		return diaLetra29;
	}

	public void setDiaLetra29(String diaLetra29) {
		this.diaLetra29 = diaLetra29;
	}

	public String getDiaLetra30() {
		return diaLetra30;
	}

	public void setDiaLetra30(String diaLetra30) {
		this.diaLetra30 = diaLetra30;
	}

	public String getDiaLetra31() {
		return diaLetra31;
	}

	public void setDiaLetra31(String diaLetra31) {
		this.diaLetra31 = diaLetra31;
	}

	public String getCunias() {
		return cunias;
	}

	public void setCunias(String cunias) {
		this.cunias = cunias;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public BigDecimal getValorBig() {
		return valorBig;
	}

	public void setValorBig(BigDecimal valorBig) {
		this.valorBig = valorBig;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getDiaLetra() {
		return diaLetra;
	}

	public void setDiaLetra(String diaLetra) {
		this.diaLetra = diaLetra;
	}

	public Date getFechaPrograma() {
		return fechaPrograma;
	}

	public void setFechaPrograma(Date fechaPrograma) {
		this.fechaPrograma = fechaPrograma;
	}

	public String getRucProveedor() {
		return rucProveedor;
	}

	public void setRucProveedor(String rucProveedor) {
		this.rucProveedor = rucProveedor;
	}

	/*public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}*/
}
