package com.spirit.medios.gui.reporteData;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.GregorianCalendar;

public class PlanMedioReporteData implements Serializable {
	private static final long serialVersionUID = -52817150793215688L;
	private String proveedorOrden;
	private String hora;
	private String programa;
	private String version;
	private Integer dia01;
	private Integer dia02;
	private Integer dia03;
	private Integer dia04;
	private Integer dia05;
	private Integer dia06;
	private Integer dia07;
	private Integer dia08;
	private Integer dia09;
	private Integer dia10;
	private Integer dia11;
	private Integer dia12;
	private Integer dia13;
	private Integer dia14;
	private Integer dia15;
	private Integer dia16;
	private Integer dia17;
	private Integer dia18;
	private Integer dia19;
	private Integer dia20;
	private Integer dia21;
	private Integer dia22;
	private Integer dia23;
	private Integer dia24;
	private Integer dia25;
	private Integer dia26;
	private Integer dia27;
	private Integer dia28;
	private Integer dia29;
	private Integer dia30;
	private Integer dia31;
	//ADD 25 MAYO
	private Integer diaLetra01;
	private Integer diaLetra02;
	private Integer diaLetra03;
	private Integer diaLetra04;
	private Integer diaLetra05;
	private Integer diaLetra06;
	private Integer diaLetra07;
	private Integer diaLetra08;
	private Integer diaLetra09;
	private Integer diaLetra10;
	private Integer diaLetra11;
	private Integer diaLetra12;
	private Integer diaLetra13;
	private Integer diaLetra14;
	private Integer diaLetra15;
	private Integer diaLetra16;
	private Integer diaLetra17;
	private Integer diaLetra18;
	private Integer diaLetra19;
	private Integer diaLetra20;
	private Integer diaLetra21;
	private Integer diaLetra22;
	private Integer diaLetra23;
	private Integer diaLetra24;
	private Integer diaLetra25;
	private Integer diaLetra26;
	private Integer diaLetra27;
	private Integer diaLetra28;
	private Integer diaLetra29;
	private Integer diaLetra30;
	private Integer diaLetra31;
	private String diaSemana01;
	private String diaSemana02;
	private String diaSemana03;
	private String diaSemana04;
	private String diaSemana05;
	private String diaSemana06;
	private String diaSemana07;
	private String diaSemana08;
	private String diaSemana09;
	private String diaSemana10;
	private String diaSemana11;
	private String diaSemana12;
	private String diaSemana13;
	private String diaSemana14;
	private String diaSemana15;
	private String diaSemana16;
	private String diaSemana17;
	private String diaSemana18;
	private String diaSemana19;
	private String diaSemana20;
	private String diaSemana21;
	private String diaSemana22;
	private String diaSemana23;
	private String diaSemana24;
	private String diaSemana25;
	private String diaSemana26;
	private String diaSemana27;
	private String diaSemana28;
	private String diaSemana29;
	private String diaSemana30;
	private String diaSemana31;
	//END 25 MAYO
	private String cunias;
	private String gye;
	private String uio;
	private String pon;
	private String tgrpsgye;
	private String tgrpsuio;
	private String tgrps;
	private String tarifa;
	private String total;
	private int dia;
	//ADD 24 MAYO
	private String diaLetra;
	//END 24 MAYO
	private Date fechaPrograma;
	//ADD 26 
	//ONLY TO ORDENES DE MEDIO AGRUPADAS X CANAL
	private BigDecimal tarifaBig;
	//private ArrayList<String> productosVersion;
	//END 26 MAYO
	
	
	public PlanMedioReporteData() {
		proveedorOrden = "";
		hora = "";
		programa = "";
		version = "";
		
		dia01 = 0;
		dia02 = 0;
		dia03 = 0;
		dia04 = 0;
		dia05 = 0;
		dia06 = 0;
		dia07 = 0;
		dia08 = 0;
		dia09 = 0;
		dia10 = 0;
		dia11 = 0;
		dia12 = 0;
		dia13 = 0;
		dia14 = 0;
		dia15 = 0;
		dia16 = 0;
		dia17 = 0;
		dia18 = 0;
		dia19 = 0;
		dia20 = 0;
		dia21 = 0;
		dia22 = 0;
		dia23 = 0;
		dia24 = 0;
		dia25 = 0;
		dia26 = 0;
		dia27 = 0;
		dia28 = 0;
		dia29 = 0;
		dia30 = 0;
		dia31 = 0;
		
		diaLetra01 = 0;
		diaLetra02 = 0;
		diaLetra03 = 0;
		diaLetra04 = 0;
		diaLetra05 = 0;
		diaLetra06 = 0;
		diaLetra07 = 0;
		diaLetra08 = 0;
		diaLetra09 = 0;
		diaLetra10 = 0;
		diaLetra11 = 0;
		diaLetra12 = 0;
		diaLetra13 = 0;
		diaLetra14 = 0;
		diaLetra15 = 0;
		diaLetra16 = 0;
		diaLetra17 = 0;
		diaLetra18 = 0;
		diaLetra19 = 0;
		diaLetra20 = 0;
		diaLetra21 = 0;
		diaLetra22 = 0;
		diaLetra23 = 0;
		diaLetra24 = 0;
		diaLetra25 = 0;
		diaLetra26 = 0;
		diaLetra27 = 0;
		diaLetra28 = 0;
		diaLetra29 = 0;
		diaLetra30 = 0;
		diaLetra31 = 0;
		
		diaSemana01 = "";
		diaSemana02 = "";
		diaSemana03 = "";
		diaSemana04 = "";
		diaSemana05 = "";
		diaSemana06 = "";
		diaSemana07 = "";
		diaSemana08 = "";
		diaSemana09 = "";
		diaSemana10 = "";
		diaSemana11 = "";
		diaSemana12 = "";
		diaSemana13 = "";
		diaSemana14 = "";
		diaSemana15 = "";
		diaSemana16 = "";
		diaSemana17 = "";
		diaSemana18 = "";
		diaSemana19 = "";
		diaSemana20 = "";
		diaSemana21 = "";
		diaSemana22 = "";
		diaSemana23 = "";
		diaSemana24 = "";
		diaSemana25 = "";
		diaSemana26 = "";
		diaSemana27 = "";
		diaSemana28 = "";
		diaSemana29 = "";
		diaSemana30 = "";
		diaSemana31 = "";
		
		cunias = "";
		gye = "";
		uio = "";
		pon = "";
		tgrpsgye = "";
		tgrpsuio = "";
		tgrps = "";
		tarifa = "";
		total = "";		
		dia = 0;
		diaLetra = "";
		tarifaBig = new BigDecimal(0);
		fechaPrograma = new java.sql.Date((new GregorianCalendar()).getTimeInMillis());
	}

	public String getDiaSemana01() {
		return diaSemana01;
	}

	public void setDiaSemana01(String diaSemana01) {
		this.diaSemana01 = diaSemana01;
	}

	public String getDiaSemana02() {
		return diaSemana02;
	}

	public void setDiaSemana02(String diaSemana02) {
		this.diaSemana02 = diaSemana02;
	}

	public String getDiaSemana03() {
		return diaSemana03;
	}

	public void setDiaSemana03(String diaSemana03) {
		this.diaSemana03 = diaSemana03;
	}

	public String getDiaSemana04() {
		return diaSemana04;
	}

	public void setDiaSemana04(String diaSemana04) {
		this.diaSemana04 = diaSemana04;
	}

	public String getDiaSemana05() {
		return diaSemana05;
	}

	public void setDiaSemana05(String diaSemana05) {
		this.diaSemana05 = diaSemana05;
	}

	public String getDiaSemana06() {
		return diaSemana06;
	}

	public void setDiaSemana06(String diaSemana06) {
		this.diaSemana06 = diaSemana06;
	}

	public String getDiaSemana07() {
		return diaSemana07;
	}

	public void setDiaSemana07(String diaSemana07) {
		this.diaSemana07 = diaSemana07;
	}

	public String getDiaSemana08() {
		return diaSemana08;
	}

	public void setDiaSemana08(String diaSemana08) {
		this.diaSemana08 = diaSemana08;
	}

	public String getDiaSemana09() {
		return diaSemana09;
	}

	public void setDiaSemana09(String diaSemana09) {
		this.diaSemana09 = diaSemana09;
	}

	public String getDiaSemana10() {
		return diaSemana10;
	}

	public void setDiaSemana10(String diaSemana10) {
		this.diaSemana10 = diaSemana10;
	}

	public String getDiaSemana11() {
		return diaSemana11;
	}

	public void setDiaSemana11(String diaSemana11) {
		this.diaSemana11 = diaSemana11;
	}

	public String getDiaSemana12() {
		return diaSemana12;
	}

	public void setDiaSemana12(String diaSemana12) {
		this.diaSemana12 = diaSemana12;
	}

	public String getDiaSemana13() {
		return diaSemana13;
	}

	public void setDiaSemana13(String diaSemana13) {
		this.diaSemana13 = diaSemana13;
	}

	public String getDiaSemana14() {
		return diaSemana14;
	}

	public void setDiaSemana14(String diaSemana14) {
		this.diaSemana14 = diaSemana14;
	}

	public String getDiaSemana15() {
		return diaSemana15;
	}

	public void setDiaSemana15(String diaSemana15) {
		this.diaSemana15 = diaSemana15;
	}

	public String getDiaSemana16() {
		return diaSemana16;
	}

	public void setDiaSemana16(String diaSemana16) {
		this.diaSemana16 = diaSemana16;
	}

	public String getDiaSemana17() {
		return diaSemana17;
	}

	public void setDiaSemana17(String diaSemana17) {
		this.diaSemana17 = diaSemana17;
	}

	public String getDiaSemana18() {
		return diaSemana18;
	}

	public void setDiaSemana18(String diaSemana18) {
		this.diaSemana18 = diaSemana18;
	}

	public String getDiaSemana19() {
		return diaSemana19;
	}

	public void setDiaSemana19(String diaSemana19) {
		this.diaSemana19 = diaSemana19;
	}

	public String getDiaSemana20() {
		return diaSemana20;
	}

	public void setDiaSemana20(String diaSemana20) {
		this.diaSemana20 = diaSemana20;
	}

	public String getDiaSemana21() {
		return diaSemana21;
	}

	public void setDiaSemana21(String diaSemana21) {
		this.diaSemana21 = diaSemana21;
	}

	public String getDiaSemana22() {
		return diaSemana22;
	}

	public void setDiaSemana22(String diaSemana22) {
		this.diaSemana22 = diaSemana22;
	}

	public String getDiaSemana23() {
		return diaSemana23;
	}

	public void setDiaSemana23(String diaSemana23) {
		this.diaSemana23 = diaSemana23;
	}

	public String getDiaSemana24() {
		return diaSemana24;
	}

	public void setDiaSemana24(String diaSemana24) {
		this.diaSemana24 = diaSemana24;
	}

	public String getDiaSemana25() {
		return diaSemana25;
	}

	public void setDiaSemana25(String diaSemana25) {
		this.diaSemana25 = diaSemana25;
	}

	public String getDiaSemana26() {
		return diaSemana26;
	}

	public void setDiaSemana26(String diaSemana26) {
		this.diaSemana26 = diaSemana26;
	}

	public String getDiaSemana27() {
		return diaSemana27;
	}

	public void setDiaSemana27(String diaSemana27) {
		this.diaSemana27 = diaSemana27;
	}

	public String getDiaSemana28() {
		return diaSemana28;
	}

	public void setDiaSemana28(String diaSemana28) {
		this.diaSemana28 = diaSemana28;
	}

	public String getDiaSemana29() {
		return diaSemana29;
	}

	public void setDiaSemana29(String diaSemana29) {
		this.diaSemana29 = diaSemana29;
	}

	public String getDiaSemana30() {
		return diaSemana30;
	}

	public void setDiaSemana30(String diaSemana30) {
		this.diaSemana30 = diaSemana30;
	}

	public String getDiaSemana31() {
		return diaSemana31;
	}

	public void setDiaSemana31(String diaSemana31) {
		this.diaSemana31 = diaSemana31;
	}

	public String getProveedorOrden() {
		return proveedorOrden;
	}

	public void setProveedorOrden(String proveedorOrden) {
		this.proveedorOrden = proveedorOrden;
	}

	public String getGye() {
		return gye;
	}

	public void setGye(String gye) {
		this.gye = gye;
	}

	public String getUio() {
		return uio;
	}

	public void setUio(String uio) {
		this.uio = uio;
	}

	public String getPon() {
		return pon;
	}

	public void setPon(String pon) {
		this.pon = pon;
	}

	public String getTgrpsgye() {
		return tgrpsgye;
	}

	public void setTgrpsgye(String tgrpsgye) {
		this.tgrpsgye = tgrpsgye;
	}

	public String getTgrpsuio() {
		return tgrpsuio;
	}

	public void setTgrpsuio(String tgrpsuio) {
		this.tgrpsuio = tgrpsuio;
	}

	public String getTgrps() {
		return tgrps;
	}

	public void setTgrps(String tgrps) {
		this.tgrps = tgrps;
	}

	public String getTarifa() {
		return tarifa;
	}

	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
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

	public Integer getDia01() {
		return dia01;
	}

	public void setDia01(Integer dia01) {
		this.dia01 = dia01;
	}

	public Integer getDia02() {
		return dia02;
	}

	public void setDia02(Integer dia02) {
		this.dia02 = dia02;
	}

	public Integer getDia03() {
		return dia03;
	}

	public void setDia03(Integer dia03) {
		this.dia03 = dia03;
	}

	public Integer getDia04() {
		return dia04;
	}

	public void setDia04(Integer dia04) {
		this.dia04 = dia04;
	}

	public Integer getDia05() {
		return dia05;
	}

	public void setDia05(Integer dia05) {
		this.dia05 = dia05;
	}

	public Integer getDia06() {
		return dia06;
	}

	public void setDia06(Integer dia06) {
		this.dia06 = dia06;
	}

	public Integer getDia07() {
		return dia07;
	}

	public void setDia07(Integer dia07) {
		this.dia07 = dia07;
	}

	public Integer getDia08() {
		return dia08;
	}

	public void setDia08(Integer dia08) {
		this.dia08 = dia08;
	}

	public Integer getDia09() {
		return dia09;
	}

	public void setDia09(Integer dia09) {
		this.dia09 = dia09;
	}

	public Integer getDia10() {
		return dia10;
	}

	public void setDia10(Integer dia10) {
		this.dia10 = dia10;
	}

	public Integer getDia11() {
		return dia11;
	}

	public void setDia11(Integer dia11) {
		this.dia11 = dia11;
	}

	public Integer getDia12() {
		return dia12;
	}

	public void setDia12(Integer dia12) {
		this.dia12 = dia12;
	}

	public Integer getDia13() {
		return dia13;
	}

	public void setDia13(Integer dia13) {
		this.dia13 = dia13;
	}

	public Integer getDia14() {
		return dia14;
	}

	public void setDia14(Integer dia14) {
		this.dia14 = dia14;
	}

	public Integer getDia15() {
		return dia15;
	}

	public void setDia15(Integer dia15) {
		this.dia15 = dia15;
	}

	public Integer getDia16() {
		return dia16;
	}

	public void setDia16(Integer dia16) {
		this.dia16 = dia16;
	}

	public Integer getDia17() {
		return dia17;
	}

	public void setDia17(Integer dia17) {
		this.dia17 = dia17;
	}

	public Integer getDia18() {
		return dia18;
	}

	public void setDia18(Integer dia18) {
		this.dia18 = dia18;
	}

	public Integer getDia19() {
		return dia19;
	}

	public void setDia19(Integer dia19) {
		this.dia19 = dia19;
	}

	public Integer getDia20() {
		return dia20;
	}

	public void setDia20(Integer dia20) {
		this.dia20 = dia20;
	}

	public Integer getDia21() {
		return dia21;
	}

	public void setDia21(Integer dia21) {
		this.dia21 = dia21;
	}

	public Integer getDia22() {
		return dia22;
	}

	public void setDia22(Integer dia22) {
		this.dia22 = dia22;
	}

	public Integer getDia23() {
		return dia23;
	}

	public void setDia23(Integer dia23) {
		this.dia23 = dia23;
	}

	public Integer getDia24() {
		return dia24;
	}

	public void setDia24(Integer dia24) {
		this.dia24 = dia24;
	}

	public Integer getDia25() {
		return dia25;
	}

	public void setDia25(Integer dia25) {
		this.dia25 = dia25;
	}

	public Integer getDia26() {
		return dia26;
	}

	public void setDia26(Integer dia26) {
		this.dia26 = dia26;
	}

	public Integer getDia27() {
		return dia27;
	}

	public void setDia27(Integer dia27) {
		this.dia27 = dia27;
	}

	public Integer getDia28() {
		return dia28;
	}

	public void setDia28(Integer dia28) {
		this.dia28 = dia28;
	}

	public Integer getDia29() {
		return dia29;
	}

	public void setDia29(Integer dia29) {
		this.dia29 = dia29;
	}

	public Integer getDia30() {
		return dia30;
	}

	public void setDia30(Integer dia30) {
		this.dia30 = dia30;
	}

	public Integer getDia31() {
		return dia31;
	}

	public void setDia31(Integer dia31) {
		this.dia31 = dia31;
	}

	public Integer getDiaLetra01() {
		return diaLetra01;
	}

	public void setDiaLetra01(Integer diaLetra01) {
		this.diaLetra01 = diaLetra01;
	}

	public Integer getDiaLetra02() {
		return diaLetra02;
	}

	public void setDiaLetra02(Integer diaLetra02) {
		this.diaLetra02 = diaLetra02;
	}

	public Integer getDiaLetra03() {
		return diaLetra03;
	}

	public void setDiaLetra03(Integer diaLetra03) {
		this.diaLetra03 = diaLetra03;
	}

	public Integer getDiaLetra04() {
		return diaLetra04;
	}

	public void setDiaLetra04(Integer diaLetra04) {
		this.diaLetra04 = diaLetra04;
	}

	public Integer getDiaLetra05() {
		return diaLetra05;
	}

	public void setDiaLetra05(Integer diaLetra05) {
		this.diaLetra05 = diaLetra05;
	}

	public Integer getDiaLetra06() {
		return diaLetra06;
	}

	public void setDiaLetra06(Integer diaLetra06) {
		this.diaLetra06 = diaLetra06;
	}

	public Integer getDiaLetra07() {
		return diaLetra07;
	}

	public void setDiaLetra07(Integer diaLetra07) {
		this.diaLetra07 = diaLetra07;
	}

	public Integer getDiaLetra08() {
		return diaLetra08;
	}

	public void setDiaLetra08(Integer diaLetra08) {
		this.diaLetra08 = diaLetra08;
	}

	public Integer getDiaLetra09() {
		return diaLetra09;
	}

	public void setDiaLetra09(Integer diaLetra09) {
		this.diaLetra09 = diaLetra09;
	}

	public Integer getDiaLetra10() {
		return diaLetra10;
	}

	public void setDiaLetra10(Integer diaLetra10) {
		this.diaLetra10 = diaLetra10;
	}

	public Integer getDiaLetra11() {
		return diaLetra11;
	}

	public void setDiaLetra11(Integer diaLetra11) {
		this.diaLetra11 = diaLetra11;
	}

	public Integer getDiaLetra12() {
		return diaLetra12;
	}

	public void setDiaLetra12(Integer diaLetra12) {
		this.diaLetra12 = diaLetra12;
	}

	public Integer getDiaLetra13() {
		return diaLetra13;
	}

	public void setDiaLetra13(Integer diaLetra13) {
		this.diaLetra13 = diaLetra13;
	}

	public Integer getDiaLetra14() {
		return diaLetra14;
	}

	public void setDiaLetra14(Integer diaLetra14) {
		this.diaLetra14 = diaLetra14;
	}

	public Integer getDiaLetra15() {
		return diaLetra15;
	}

	public void setDiaLetra15(Integer diaLetra15) {
		this.diaLetra15 = diaLetra15;
	}

	public Integer getDiaLetra16() {
		return diaLetra16;
	}

	public void setDiaLetra16(Integer diaLetra16) {
		this.diaLetra16 = diaLetra16;
	}

	public Integer getDiaLetra17() {
		return diaLetra17;
	}

	public void setDiaLetra17(Integer diaLetra17) {
		this.diaLetra17 = diaLetra17;
	}

	public Integer getDiaLetra18() {
		return diaLetra18;
	}

	public void setDiaLetra18(Integer diaLetra18) {
		this.diaLetra18 = diaLetra18;
	}

	public Integer getDiaLetra19() {
		return diaLetra19;
	}

	public void setDiaLetra19(Integer diaLetra19) {
		this.diaLetra19 = diaLetra19;
	}

	public Integer getDiaLetra20() {
		return diaLetra20;
	}

	public void setDiaLetra20(Integer diaLetra20) {
		this.diaLetra20 = diaLetra20;
	}

	public Integer getDiaLetra21() {
		return diaLetra21;
	}

	public void setDiaLetra21(Integer diaLetra21) {
		this.diaLetra21 = diaLetra21;
	}

	public Integer getDiaLetra22() {
		return diaLetra22;
	}

	public void setDiaLetra22(Integer diaLetra22) {
		this.diaLetra22 = diaLetra22;
	}

	public Integer getDiaLetra23() {
		return diaLetra23;
	}

	public void setDiaLetra23(Integer diaLetra23) {
		this.diaLetra23 = diaLetra23;
	}

	public Integer getDiaLetra24() {
		return diaLetra24;
	}

	public void setDiaLetra24(Integer diaLetra24) {
		this.diaLetra24 = diaLetra24;
	}

	public Integer getDiaLetra25() {
		return diaLetra25;
	}

	public void setDiaLetra25(Integer diaLetra25) {
		this.diaLetra25 = diaLetra25;
	}

	public Integer getDiaLetra26() {
		return diaLetra26;
	}

	public void setDiaLetra26(Integer diaLetra26) {
		this.diaLetra26 = diaLetra26;
	}

	public Integer getDiaLetra27() {
		return diaLetra27;
	}

	public void setDiaLetra27(Integer diaLetra27) {
		this.diaLetra27 = diaLetra27;
	}

	public Integer getDiaLetra28() {
		return diaLetra28;
	}

	public void setDiaLetra28(Integer diaLetra28) {
		this.diaLetra28 = diaLetra28;
	}

	public Integer getDiaLetra29() {
		return diaLetra29;
	}

	public void setDiaLetra29(Integer diaLetra29) {
		this.diaLetra29 = diaLetra29;
	}

	public Integer getDiaLetra30() {
		return diaLetra30;
	}

	public void setDiaLetra30(Integer diaLetra30) {
		this.diaLetra30 = diaLetra30;
	}

	public Integer getDiaLetra31() {
		return diaLetra31;
	}

	public void setDiaLetra31(Integer diaLetra31) {
		this.diaLetra31 = diaLetra31;
	}

	public String getCunias() {
		return cunias;
	}

	public void setCunias(String cunias) {
		this.cunias = cunias;
	}

	public BigDecimal getTarifaBig() {
		return tarifaBig;
	}

	public void setTarifaBig(BigDecimal tarifaBig) {
		this.tarifaBig = tarifaBig;
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
}
