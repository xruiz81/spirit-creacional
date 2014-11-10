package com.spirit.general.session;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Vector;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoParametroIf;

public interface UtilitariosSessionService {

	public Timestamp getTimeStamp(Date fecha);
	public Timestamp getTimeStamp(java.util.Date fecha);
	public Timestamp getServerDateTimeStamp();
	public Date getServerDateSql() throws GenericBusinessException;
	public java.util.Date getServerDateUtil() throws GenericBusinessException;
		
	public String getMonthFromDate(java.util.Date d);
	public String getYearFromDate(java.util.Date d);
	
	public BigDecimal redondeoValor(BigDecimal valor);
	public BigDecimal redondeoValorCuatroDecimales(BigDecimal valor);
	public double redondeoValor(double valor);
	public double redondeoValorCuatroDecimales(double valor);
	
	public String getDayFromDate(java.util.Date d);
	public String getMonthBeforeFromDate(java.util.Date d);
	public String getYearBeforeFromDate(java.util.Date d);
	public java.util.Date dateHoy() throws ParseException;
	public java.sql.Date fechaHoy() throws ParseException;
	
	public String getFechaMesAnioUppercase(java.util.Date date);
	public String getFechaUppercase(java.sql.Date date);
	public String getFechaUppercase(java.util.Date date);
	
	public String getFechaCortaUppercase(java.util.Date date);
	public String getFechaCortaUppercase(java.sql.Date date);
	
	public Vector<Object> collectionToVector(Collection<Object> coleccion);
	
	public Date getFechaFinMes(int mes, int anio) throws ParseException;
	public Calendar getCalendarFinMes(int mes, int anio);
	
	public String getNombreMes(int mes) throws GenericBusinessException;
	public String getNombreMes(String mesString) throws GenericBusinessException;
	
	public Vector<CarteraIf> removerCarterasRepetidas(Vector<CarteraIf> carterasColeccion);
	
	public String[] getMesesMayusculas();
	public java.sql.Timestamp resetTimestampStartDate(java.sql.Timestamp startDate);
	public java.sql.Timestamp resetTimestampEndDate(java.sql.Timestamp endDate);
	
	public Collection<ParametroEmpresaIf> getParametroEmpresa(Long idEmpresa,
			TipoParametroIf tp, Boolean esBase,String codigoParametro) throws GenericBusinessException;
	
	public TipoParametroIf getTipoParametro(Long empresaId,String codigo) throws GenericBusinessException;
	public long obtenerDiferenciaDias(java.sql.Date fecha1, java.sql.Date fecha2);	
	
	public ParametroEmpresaIf getParametroEmpresa (Long empresaId,String codigoTipoParametro,String codigoParametro,String mensajeDeError) 
	throws GenericBusinessException;
	
	public java.sql.Timestamp fromUtilDateToTimestamp(java.util.Date utilDate);
	public java.sql.Timestamp fromSqlDateToTimestamp(java.sql.Date sqlDate);
	public java.util.Date fromTimestampToUtilDate(java.sql.Timestamp timestamp);
	public java.sql.Date fromTimestampToSqlDate(java.sql.Timestamp timestamp);
	public java.sql.Date fromUtilDateToSqlDate(java.util.Date utilDate);
	public java.util.Date fromSqlDateToUtilDate(java.sql.Date sqlDate);
	
	public BigDecimal evaluadorExpresionJavaScript(String expresion) throws GenericBusinessException;
	
	public String[] amountToWords(double value);
	public String removeDecimalFormat(String str);
}
