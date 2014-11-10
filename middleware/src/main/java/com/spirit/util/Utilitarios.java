package com.spirit.util;

import java.awt.Rectangle;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import sun.misc.BASE64Encoder;

import com.spirit.client.RMCantidadEnLetras;
import com.spirit.client.SpiritConstants;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.MonedaIf;

public class Utilitarios {
	
	static DecimalFormat df = new DecimalFormat("00");
	
	//RFC 2822 token definitions for valid email - only used together to form a java Pattern object:
	private static final String sp = "\\!\\#\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\|\\}\\~";
	private static final String atext = "[a-zA-Z0-9" + sp + "]";
	private static final String atom = atext + "+"; //one or more atext chars
	private static final String dotAtom = "\\." + atom;
	private static final String localPart = atom + "(" + dotAtom + ")*"; //one atom followed by 0 or more dotAtoms.

	//RFC 1035 tokens for domain names:
	private static final String letter = "[a-zA-Z]";
	private static final String letDig = "[a-zA-Z0-9]";
	private static final String letDigHyp = "[a-zA-Z0-9-]";
	public static final String rfcLabel = letDig + "(" + letDigHyp + "{0,61}" + letDig + ")?";
	private static final String domain = rfcLabel + "(\\." + rfcLabel + ")*\\." + letter + "{2,6}";

	//Combined together, these form the allowed email regexp allowed by RFC 2822:
	private static final String addrSpec = "^" + localPart + "@" + domain + "$";
	
	//Validacion de correo electronico, retorna true si esta bien escrito y false si nolo esta
	public static boolean validarCorreoElectronico(String cadena) {
		return ((Pattern.compile(addrSpec)).matcher(cadena)).matches();
		//return ((Pattern.compile("[a-zA-Z0-9_]+[.[a-zA-Z0-9]+]*@[[a-zA-Z0-9_]+.[a-zA-Z0-9]+]+")).matcher(cadena)).matches();
	}	
	
	/**
	 * Devuelve el mes y el anio anterior a una fecha en formato mm-aaaa
	 *  Ejm: Si registro una fecha 12/01/2007 devuelve 12-2006 
	 *   (mes pasado el anio pasado por ser diciembre)
	 * @param d
	 * @return
	 */
	public static Locale esLocale = new Locale("es");
	public static Locale enLocale = new Locale("en");
	
	static String[] mesesMayusculas = {"ENERO","FEBRERO","MARZO","ABRIL","MAYO",
		"JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"
	};
	
	static String[] mesesCortosMayusculas = {"ENE","FEB","MAR","ABR","MAY",
		"JUN","JUL","AGO","SEP","OCT","NOV","DIC"
	};
	
	static String[] mesesCortosMinusculas = {"Ene","Feb","Mar","Abr","May",
		"Jun","Jul","Ago","Sep","Oct","Nov","Dic"
	};
	
	static String[] diasCortos = {"-","Do","Lu","Ma","Mi","Ju","Vi","Sa"};
	
	public static String getFechaEEEddMMM(java.util.Date date){
		DateFormatSymbols symbols = new DateFormatSymbols();
		symbols.setShortMonths(mesesCortosMinusculas);
		symbols.setShortWeekdays(diasCortos);
		Format dateFormat = new SimpleDateFormat("EEE-dd-MMM",symbols);
		return dateFormat.format(date);		
	}
	
	public static String getFechaEEEddMMM(java.sql.Date date){
		DateFormatSymbols symbols = new DateFormatSymbols();
		symbols.setShortMonths(mesesCortosMinusculas);
		symbols.setShortWeekdays(diasCortos);
		Format dateFormat = new SimpleDateFormat("EEE-dd-MMM",symbols);
		return dateFormat.format(date);		
	}
	
	//ADD 24 MAYO DIAS PARA REPORTES
	static String[] diasCortosToReportes = {"-","D","L","M","X","J","V","S"};
	
	public static String getFechaDiaSemanaDiaMesAnioUppercase(java.sql.Date date){
		DateFormat dateformat = new SimpleDateFormat("EEE, dd - MMMM - yyyy",esLocale);
		return dateformat.format(date).toUpperCase();
	}
	
	public static String getFechaDiaSemanaDiaMesAnioUppercase(java.util.Date date){
		DateFormat dateformat = new SimpleDateFormat("EEEE, dd - MMMM - yyyy",esLocale);
		return dateformat.format(date).toUpperCase();
	}
	
	public static String getFechaEEEddMMMToReporte(java.util.Date date){
		DateFormatSymbols symbols = new DateFormatSymbols();
		symbols.setShortMonths(mesesCortosMinusculas);
		symbols.setShortWeekdays(diasCortosToReportes);
		//MODIFIED 30 JUNIO
		//Format dateFormat = new SimpleDateFormat("EEE-dd-MMM",symbols);
		Format dateFormat = new SimpleDateFormat("E-MMM-yy",symbols);
		return dateFormat.format(date);		
	}
	//END 24 MAYO

	public static String getYearBeforeFromDate(java.util.Date d) {
		
		Calendar cal = GregorianCalendar.getInstance();
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		cal.setTime(d);
		cal.add(Calendar.MONTH, -1);
		String dateString = dateFormatter.format(cal.getTime());
		return dateString.substring(6,10);
		
	}
	public static String getMonthBeforeFromDate(java.util.Date d) {
		
		Calendar cal = GregorianCalendar.getInstance();
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		cal.setTime(d);
		cal.add(Calendar.MONTH, -1);
		
		return dateFormatter.format(cal.getTime()).substring(3, 5);
		
	}
	
	public static java.util.Date getDateBeforeFromDate(java.util.Date d) {
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, -1);
		java.util.Date date = c.getTime();
		return date;
	}
    
	public static String getDayBeforeFromDate(java.util.Date d) {
		
		Calendar cal = GregorianCalendar.getInstance();
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		cal.setTime(d);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		
		return dateFormatter.format(cal.getTime()).substring(0, 2);
		
	}	
	
	//ADD 1 AGOSTO
    public static String getMonthUpperCase(java.util.Date d){
    	
    	//Calendar cal = GregorianCalendar.getInstance();
    	DateFormat dateFormatter = setFechaUppercase();
    	String[] fechaUpperCase = dateFormatter.format(d).split(" - ");
    	
		return fechaUpperCase[1];
	}
    //END AGOSTO
	
	public static String getMonthFromDate(java.util.Date d) {
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormatter.format(d).substring(3, 5);
	}

	public static String getYearFromDate(java.util.Date d) {
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormatter.format(d).substring(6, 10);
	}

	public static String getDayFromDate(java.util.Date d) {
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormatter.format(d).substring(0, 2);
	}
	
    public static  java.util.Date  getLastDateFromMonth(java.util.Date date){
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
    
    //ADD 22 JULIO
    public static  java.util.Date  getFirstDateFromYear(java.util.Date date){
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
		return calendar.getTime();
	}
    
    public static  java.util.Date  getLastDateFromYear(java.util.Date date){
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
		return calendar.getTime();
	}
    //END 22 JULIO
    
    public static  java.util.Date  getFirstDateFromMonth(java.util.Date date){
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
   	

	//TODO: Ver donde se esta utilizando ponerlo deprecated y cambiarlo por el nuevo metodo
	public static int getLastDayOfMonth(int month, int year) {
		int lastDay = 28;

		switch (month) {
		case 0:
			lastDay = 31;
			break;
		case 1:
			if ((year % 4) == 0)
				lastDay = 29;
			else
				lastDay = 28;
			break;
		case 2:
			lastDay = 31;
			break;
		case 3:
			lastDay = 30;
			break;
		case 4:
			lastDay = 31;
			break;
		case 5:
			lastDay = 30;
			break;
		case 6:
			lastDay = 31;
			break;
		case 7:
			lastDay = 31;
			break;
		case 8:
			lastDay = 30;
			break;
		case 9:
			lastDay = 31;
			break;
		case 10:
			lastDay = 30;
			break;
		case 11:
			lastDay = 31;
			break;
		}
		return lastDay;

	}
	
	public static String getStringDateFromDate(java.util.Date _date) {
		String year = getYearFromDate(_date);
		String month = getMonthFromDate(_date);
		String day = getDayFromDate(_date);
		String date = day + "-" + month + "-" + year;
		
		return date;
	}

	public static Date stringDate(String cadena) throws ParseException {

		Date fecha = null;
		SimpleDateFormat ts = new SimpleDateFormat("dd/mm/yyyy");

		try {
			fecha = new Date(ts.parse(cadena).getTime());

		} catch (ParseException e) {
			//SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			throw new ParseException(e.getMessage(), 0);
		}

		//System.out.println("Fecha:-" + fecha);
		return fecha;

	}
	
	
	public static String dateNow() {
		java.util.Date today;
		String dateOut;
		DateFormat dateFormatter;
		
		dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
		today = new java.util.Date();
		dateOut = dateFormatter.format(today);
		return dateOut;
	}
	
	public static Calendar calendarHoy() {
		java.util.Date today = new java.util.Date();
		int dia = Integer.parseInt(Utilitarios.getDayFromDate(today));
		int mes = Integer.parseInt(Utilitarios.getMonthFromDate(today));
		int anio = Integer.parseInt(Utilitarios.getYearFromDate(today));
		Calendar calendarInicio = new GregorianCalendar(anio, (mes-1), dia);
		
		return calendarInicio;
	}
	
	public static java.util.Date dateHoy() throws ParseException {

		java.util.Date today = new java.util.Date();
		String dia = Utilitarios.getDayFromDate(today);
		String mes = Utilitarios.getMonthFromDate(today);
		String anio = Utilitarios.getYearFromDate(today);
		String hoy = anio+"-"+mes+"-"+dia;
		java.util.Date dateHoy = new SimpleDateFormat("yyyy-MM-dd").parse(hoy);

		return dateHoy;
	}
	
	public static String dateHoraHoy() throws ParseException {

		java.util.Date today = new java.util.Date();
		String dia = Utilitarios.getDayFromDate(today);
		String mes = Utilitarios.getMonthFromDate(today);
		String anio = Utilitarios.getYearFromDate(today);
		int horas = today.getHours();
		int minutos = today.getMinutes();
		String hora;
		if(minutos < 10)
			hora = horas+":0"+minutos;
		else
			hora = horas+":"+minutos;
		
		String hoy = anio+"-"+mes+"-"+dia+" "+hora;
		
		return hoy;
	}
	
	public static String getHora(int hora, int minuto, int segundo) {

		String horas = hora<9?"0"+hora:String.valueOf(hora);
		String minutos = minuto<9?"0"+minuto:String.valueOf(minuto);
		String segundos = segundo<9?"0"+segundo:String.valueOf(segundo);
		
		return horas+":"+minutos+":"+segundos;
	}
	
	public static String fechaHoy() throws ParseException {

		java.util.Date today = new java.util.Date();
		String dia = Utilitarios.getDayFromDate(today);
		String mes = Utilitarios.getMonthFromDate(today);
		String anio = Utilitarios.getYearFromDate(today);		
		String fechaHoy = dia + " de " + getNombreMes(Integer.parseInt(mes)) + " del " + anio;
		
		return fechaHoy;
	}
	
	public static String fechaHoy(java.util.Date today) throws ParseException {

		String dia = Utilitarios.getDayFromDate(today);
		String mes = Utilitarios.getMonthFromDate(today);
		String anio = Utilitarios.getYearFromDate(today);		
		String fechaHoy = dia + " de " + getNombreMes(Integer.parseInt(mes)) + " del " + anio;
		
		return fechaHoy;
	}
	
	public static String getNombreMes(int mes) {
		//String[] meses = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
		
		return mesesMayusculas[mes - 1];
	}
	
	public static String getNombreCortoMes(int mes) {
		return mesesCortosMayusculas[mes - 1];
	}
	
		
	public static Date fechaHoy(int mes, int anio) throws ParseException {
		Date fechaHoy = null;
		
		if(mes == 0 || mes == 2 || mes == 4 || mes == 6 || mes == 7 || mes == 9 || mes == 11)
			fechaHoy = new java.sql.Date(anio, mes, 31);
		else if(mes == 3 || mes == 5 || mes == 8 || mes == 10)
			fechaHoy = new java.sql.Date(anio, mes, 30);
		else if(mes == 1 && anio%4 == 0)
			fechaHoy = new java.sql.Date(anio, mes, 29);
		else if(mes == 1)		
			fechaHoy = new java.sql.Date(anio, mes, 28);
		
		return fechaHoy;
	}
	
	public static String capitalise(String oldStr) {
		int len = oldStr.length();
		if (oldStr == null || len == 0) {
			return oldStr;
		}
		StringBuffer buff = new StringBuffer(len);
		boolean white = true;
		for (int i = 0; i < len; i++) {
			char c = oldStr.charAt(i);
			if (Character.isWhitespace(c)) {
				buff.append(c);
				white = true;
			} else if (white) {
				buff.append(Character.toTitleCase(c));
				white = false;
			} else {
				buff.append(c);
			}
		}
		return buff.toString();
	}

	public static String toTitleCase(String s) {
		char[] ca = s.toCharArray();
		boolean changed = false;
		boolean capitalise = true;
		boolean firstCap = true;
		for (int i = 0; i < ca.length; i++) {
			char oldLetter = ca[i];
			if (oldLetter <= '/' || ':' <= oldLetter && oldLetter <= '?'
					|| ']' <= oldLetter && oldLetter <= '`') {
				capitalise = true;
			} else {
				char newLetter = capitalise ? Character.toUpperCase(oldLetter)
						: Character.toLowerCase(oldLetter);
				ca[i] = newLetter;
				changed |= (newLetter != oldLetter);
				capitalise = false;
				firstCap = false;
			} // end if
		} // end for
		if (changed) {
			s = new String(ca);
		}
		s = s.replaceAll(" De ", " de ");
		s = s.replaceAll(" En ", " en ");
		s = s.replaceAll(" Y ", " y ");
		s = s.replaceAll(" Por ", " por ");
		return s;
	}

	public static void ValidarExcepciones(Exception e) {
		// TODO: Eliminar cuando se eliminan todas las referencias a este método
	}

	public static String removeFormatStringNumber(String oldStr) {
		int len = oldStr.length();

		if (oldStr == null || len == 0) {
			return "0.0";
		}

		StringBuffer buff = new StringBuffer(len);

		for (int i = 0; i < len; i++) {
			char c = oldStr.charAt(i);
			if (c != ',')
				buff.append(c);
		}

		return buff.toString();
	}
	
	public static String removeDecimalFormat(String str) {
		//return str.replaceAll("\\.", "").replaceAll(",", "\\.");
		return str.replaceAll(",", "");
	}

	public static String fechaAhora() {
		java.util.Date today;
		String dateOut;
		Format dateFormatter;
		
		dateFormatter = new SimpleDateFormat("dd - MMMM - yyyy",esLocale);
		today = new java.util.Date();
		dateOut = dateFormatter.format(today);
		return dateOut.toUpperCase();
	}
	
	public static DateFormat setFechaSinAnioUppercase(){
		
		DateFormatSymbols symbols = new DateFormatSymbols();
		
		symbols.setMonths(mesesMayusculas);
		//SE ESTABLECE EL FORMATO DE LA FECHA
		DateFormat dateformat = new SimpleDateFormat("dd - MMMM",symbols);
		
		return dateformat;
	}
	
	public static DateFormat setFechaUppercase(){
		
		DateFormatSymbols symbols = new DateFormatSymbols();
		
		symbols.setMonths(mesesMayusculas);
		//SE ESTABLECE EL FORMATO DE LA FECHA
		DateFormat dateformat = new SimpleDateFormat("dd - MMMM - yyyy",symbols);
		//DateFormat dateformat = new SimpleDateFormat("dd-MMMM-yyyy",symbols);
		return dateformat;
	}
	
	
	
	public static DateFormat setFechaCortaUppercase(){
		
		DateFormatSymbols symbols = new DateFormatSymbols();
		
		symbols.setMonths(mesesCortosMayusculas);
		//SE ESTABLECE EL FORMATO DE LA FECHA
		DateFormat dateformat = new SimpleDateFormat("dd-MMMM-yyyy",symbols);
		
		return dateformat;
	}
	
	public static String getEmissionDateUppercase(java.util.Date date) {
		DateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", esLocale);
		return dateFormat.format(date).toUpperCase();
	}
	
	public static String getFechaCortaUppercase(java.util.Date date){
		DateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy",esLocale);
		return dateformat.format(date).toUpperCase();	
	}
	
	public static String getFechaCortaUppercase(java.sql.Date date){
		DateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy",esLocale);
		return dateformat.format(date).toUpperCase();	
	}
	
	public static String getFechaUppercase(java.sql.Date date){
		DateFormat dateformat = new SimpleDateFormat("dd - MMMM - yyyy",esLocale);
		return dateformat.format(date).toUpperCase();		
	}
	
	public static String getFechaUppercase(java.util.Date date){
		DateFormat dateformat = new SimpleDateFormat("dd - MMMM - yyyy",esLocale);
		return dateformat.format(date).toUpperCase();		
	}
	
	public static String getFechaNuevosCheques(java.sql.Date date){
		DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd",esLocale);
		return dateformat.format(date).toUpperCase();		
	}
	
	public static String getFechaNuevosCheques(java.util.Date date){
		DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd",esLocale);
		return dateformat.format(date).toUpperCase();		
	}
	
	public static DateFormat setFechaMesAnioUppercase(){
		
		DateFormatSymbols symbols = new DateFormatSymbols();
		
		symbols.setMonths(mesesMayusculas);
		//SE ESTABLECE EL FORMATO DE LA FECHA
		DateFormat dateformat = new SimpleDateFormat("MMMM - yyyy",symbols);
		
		return dateformat;
	}
	
	public static DateFormat setFechaAnio(){
		DateFormatSymbols symbols = new DateFormatSymbols();
		//SE ESTABLECE EL FORMATO DE LA FECHA
		DateFormat dateformat = new SimpleDateFormat("yyyy",symbols);
		return dateformat;
	}
	
	public static String getFechaAnio(java.util.Date date){
		DateFormat dateformat = new SimpleDateFormat("yyyy",esLocale);
		return dateformat.format(date).toUpperCase();
	}
	
	public static String getFechaMesAnioUppercase(java.util.Date date){
		DateFormat dateformat = new SimpleDateFormat("MMMM - yyyy",esLocale);
		return dateformat.format(date).toUpperCase();
	}
	
	public static synchronized String encrypt(String plaintext, String type) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	    MessageDigest md = null;
	    
	    try {
			md = MessageDigest.getInstance(type);
		} catch (NoSuchAlgorithmException e) {
			//SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			throw new NoSuchAlgorithmException(e.getMessage());
		} 
	    try {
			md.update(plaintext.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			//SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			throw new UnsupportedEncodingException(e.getMessage());
		} 
	   
	    byte raw[] = md.digest(); 
	    String hash = (new BASE64Encoder()).encode(raw); 
	    return hash; 
	  }
	
	public static void scrollToCenter(JTable table, Collection rowIndexVector, int vColIndex) {
		int rowIndex = rowIndexVector.size();
		if(!rowIndexVector.isEmpty()){
			if (!(table.getParent() instanceof JViewport)) {
	            return;
	        }
	        JViewport viewport = (JViewport)table.getParent();
	    
	        // This rectangle is relative to the table where the
	        // northwest corner of cell (0,0) is always (0,0).
	        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
	    
	        // The location of the view relative to the table
	        Rectangle viewRect = viewport.getViewRect();
	    
	        // Translate the cell location so that it is relative
	        // to the view, assuming the northwest corner of the
	        // view is (0,0).
	        rect.setLocation(rect.x-viewRect.x, rect.y-viewRect.y);
	    
	        // Calculate location of rect if it were at the center of view
	        int centerX = (viewRect.width-rect.width)/2;
	        int centerY = (viewRect.height-rect.height)/2;
	    
	        // Fake the location of the cell so that scrollRectToVisible
	        // will move the cell to the center
	        if (rect.x < centerX) {
	            centerX = -centerX;
	        }
	        if (rect.y < centerY) {
	            centerY = -centerY;
	        }
	        rect.translate(centerX, centerY);
	    
	        // Scroll the area into view.
	        viewport.scrollRectToVisible(rect);
	        
	        table.setRowSelectionInterval(rowIndex-1, rowIndex-1);
		}
	}
	
	public static boolean dateBetween(java.sql.Date date, java.sql.Date dateBegin, Date dateEnd) {
		if (Utilitarios.dateAfter(date, dateBegin) && Utilitarios.dateBefore(date, dateEnd))
			return true;
		
		return false;
	}
	
	public static boolean dateBefore(java.sql.Date date, java.sql.Date dateBefore) {
		DecimalFormat formatoSerial = new DecimalFormat("00");
		int dateDay = date.getDate(); int dateMonth = date.getMonth() + 1; int dateYear = date.getYear() + 1900;
		int dateBeforeDay = dateBefore.getDate(); int dateBeforeMonth = dateBefore.getMonth() + 1; int dateBeforeYear = dateBefore.getYear() + 1900;
		String stringDate = String.valueOf(dateYear) + String.valueOf(formatoSerial.format((double) dateMonth)) + String.valueOf(formatoSerial.format((double) dateDay));
		String stringDateBefore = String.valueOf(dateBeforeYear) + String.valueOf(formatoSerial.format((double) dateBeforeMonth)) + String.valueOf(formatoSerial.format((double) dateBeforeDay));
		
		if (stringDate.compareTo(stringDateBefore) <= 0)
			return true;
			 
		return false;
	}
	
	public static boolean dateAfter(java.sql.Date date, java.sql.Date dateAfter) {
		DecimalFormat formatoSerial = new DecimalFormat("00");
		int dateDay = date.getDate(); int dateMonth = date.getMonth() + 1; int dateYear = date.getYear() + 1900;
		int dateAfterDay = dateAfter.getDate(); int dateAfterMonth = dateAfter.getMonth() + 1; int dateAfterYear = dateAfter.getYear() + 1900;
		String stringDate = String.valueOf(dateYear) + String.valueOf(formatoSerial.format((double) dateMonth)) + String.valueOf(formatoSerial.format((double) dateDay));
		String stringDateAfter = String.valueOf(dateAfterYear) + String.valueOf(formatoSerial.format((double) dateAfterMonth)) + String.valueOf(formatoSerial.format((double) dateAfterDay));
		
		if (stringDate.compareTo(stringDateAfter) >= 0)
			return true;
			 
		return false;
	}
	
	//compara fechas exactamente hasta los segundos
	public static int compararExactoFechas(java.sql.Date fecha1, java.sql.Date fecha2){
		Long anioFecha1 = fecha1.getTime();
		Long anioFecha2 = fecha2.getTime();		
		return anioFecha1.compareTo(anioFecha2);
	}
	
	//compara fechas solo año, mes y día
	public static int compararFechas(java.sql.Date fecha1, java.sql.Date fecha2){
		int comparacion = 0;		
		int anioFecha1 = fecha1.getYear();
		int mesFecha1 = fecha1.getMonth();
		int diaFecha1 = fecha1.getDate();
		int anioFecha2 = fecha2.getYear();
		int mesFecha2 = fecha2.getMonth();
		int diaFecha2 = fecha2.getDate();
		
		if(anioFecha1 > anioFecha2){
			comparacion = 1;
		}else if(anioFecha1 < anioFecha2){
			comparacion = -1;
		}else{
			if(mesFecha1 > mesFecha2){
				comparacion = 1;
			}else if(mesFecha1 < mesFecha2){
				comparacion = -1;
			}else{
				if(diaFecha1 > diaFecha2){
					comparacion = 1;
				}else if(diaFecha1 < diaFecha2){
					comparacion = -1;
				}else{
					comparacion = 0;
				}
			}
		}
		
		return comparacion;
	}
	
	public static int getMesInt(String mes) throws GenericBusinessException{
		if (mes.equalsIgnoreCase("ENERO")) return 0;
		else if (mes.equalsIgnoreCase("FEBRERO")) return 1;
		else if (mes.equalsIgnoreCase("MARZO")) return 2;
		else if (mes.equalsIgnoreCase("ABRIL")) return 3;
		else if (mes.equalsIgnoreCase("MAYO")) return 4;
		else if (mes.equalsIgnoreCase("JUNIO")) return 5;
		else if (mes.equalsIgnoreCase("JULIO")) return 6;
		else if (mes.equalsIgnoreCase("AGOSTO")) return 7;
		else if (mes.equalsIgnoreCase("SEPTIEMBRE")) return 8;
		else if (mes.equalsIgnoreCase("OCTUBRE")) return 9;
		else if (mes.equalsIgnoreCase("NOVIEMBRE")) return 10;
		else if (mes.equalsIgnoreCase("DICIEMBRE")) return 11;
		throw new GenericBusinessException("Mes seleccionado es incorrecto");
	}
	
	public static BigDecimal redondeoValor(BigDecimal valor){
		if ( valor != null ){
			return valor.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return null;
	}
	
	public static BigDecimal redondeoValor(BigDecimal valor, int escala) {
		if ( valor != null ){
			return valor.setScale(escala, BigDecimal.ROUND_HALF_UP);
		}
		return null;
	}
	
	public static double redondeoValor(double valor){
		double valorR = 0.0;
		Double decimales = 2D;
		valorR = BigDecimal.valueOf(valor).setScale(decimales.intValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
		return valorR;
	}
	
	/*public static double redondeoValor(double valor){
		double valorR = 0.0;
		int decimales = 2;
		valorR = Math.round(valor*Math.pow(10,decimales))/Math.pow(10,decimales);
		return valorR;
	}*/

	public static String[] getMesesMayusculas() {
		return mesesMayusculas;
	}
	
	public static String[] obtenerCantidadEnLetras(String cantidadEnNumeros, Double parteDecimal, int[] vectorRenglones, boolean caracterProteccion, MonedaIf moneda) {
		int[] renglones = vectorRenglones;
		RMCantidadEnLetras.setRenglones(renglones);
		RMCantidadEnLetras.setPalabra_cero("CE|RO");
		RMCantidadEnLetras.setGenero_unidad(2);
		RMCantidadEnLetras.setPrefijo_inicio("");
		RMCantidadEnLetras.setSufijo_final(moneda.getSufijoCantidadLetras());
		RMCantidadEnLetras.setCantidad_decimales(2);
		RMCantidadEnLetras.setTraduce_decimales(false);
		if (caracterProteccion)
			RMCantidadEnLetras.setCaracter_proteccion('x');
		else
			RMCantidadEnLetras.setCaracter_proteccion(' ');
		RMCantidadEnLetras.setSufijo_decimales("|/100");
		RMCantidadEnLetras.setSufijo_enteros("");
		if (parteDecimal >= 0D && parteDecimal <= 9D)
			RMCantidadEnLetras.setPrefijo_decimales("0");
		String[] texto = RMCantidadEnLetras.getTexto(Utilitarios.removeDecimalFormat(cantidadEnNumeros));
		
		if (parteDecimal == 0D) {
			for (int i=0; i<texto.length; i++) {
				texto[i] = texto[i].replaceAll(" CON "," ");
			}
		}
		
		if (parteDecimal >= 0D && parteDecimal <= 9D) {
			for (int i=0; i<texto.length; i++) {
				texto[i] = texto[i].replaceAll("0 ","0");
				texto[i] = texto[i].replaceAll("0" + moneda.getPlural(), "0" + SpiritConstants.getBlankSpaceCharacter() + moneda.getPlural());
			}
		}
		
		return texto;
	}
	
	public static long obtenerDiferenciaDias(java.sql.Date fecha1, java.sql.Date fecha2) {
		int year1 = fecha1.getYear() + 1900;
		int month1 = fecha1.getMonth() + 1;
		int day1 = fecha1.getDate();
		java.util.GregorianCalendar date1 = new java.util.GregorianCalendar(year1, month1, day1);
		int year2 = fecha2.getYear() + 1900;
		int month2 = fecha2.getMonth() + 1;
		int day2 = fecha2.getDate();
		java.util.GregorianCalendar date2 = new java.util.GregorianCalendar(year2, month2, day2);
		long diferenciaMilisegundos = (date1.before(date2))?date2.getTimeInMillis() - date1.getTimeInMillis():date1.getTimeInMillis() - date2.getTimeInMillis();
		long diferenciaDias = diferenciaMilisegundos / (1000 * 60 * 60 * 24);
		return diferenciaDias; 
	}
	
	public static long[] convertirSegundos(long totalSegundos){
        //[HORAS,MINUTOS,SEGUNDOS]
    	long[] tiempo = new long[3];
    	long residuo=0;
        if ( totalSegundos > 0 ){
            tiempo[0]= totalSegundos/3600;
            residuo = totalSegundos%3600;
            tiempo[1]= residuo/60;
            tiempo[2] = residuo%60;
        }
        return tiempo;
    }
	
	public static String getTiempoCompleto(long segundos){        
    	long[] tiempo=convertirSegundos(segundos);
        return df.format(tiempo[0])+":"+df.format(tiempo[1])+":"+df.format(tiempo[2]);
    };
	
	public static void setDateFormatter(JFormattedTextField dateField, String formatPattern) {
		DateFormatter df = new DateFormatter(new SimpleDateFormat(formatPattern));
		df.setAllowsInvalid(false);
		df.setOverwriteMode(true);
		DefaultFormatterFactory dff = new DefaultFormatterFactory(df);
		dateField.setFormatterFactory(dff);
	}
	
	public static void setMoneyFormatter(JFormattedTextField moneyField) {
		// Visualization format
		NumberFormat dispFormat = NumberFormat.getCurrencyInstance(new Locale("es", "EC"));
		//NumberFormat dispFormat = NumberFormat.getNumberInstance();
		// Edition format: English (decimal separator: point)
		NumberFormat editFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);
		// For edition, we don't want thousand separator
		editFormat.setGroupingUsed(false);
		editFormat.setMaximumFractionDigits(2);
		// Instancing number formatters
		NumberFormatter dnFormat = new NumberFormatter(dispFormat);
		NumberFormatter enFormat = new NumberFormatter(editFormat);
		// Instancing formatting factories
		// Setting default visualization and edition formatters
		DefaultFormatterFactory currFactory = new DefaultFormatterFactory(dnFormat, dnFormat, enFormat);
		// Edition formatter allows invalid characters
		enFormat.setAllowsInvalid(true);
		// Setting formatter factory
		moneyField.setFormatterFactory(currFactory);
	}
	
	public static BigDecimal moneyFormatterToBigDecimal(JFormattedTextField moneyField) {
		double value = (moneyField.getValue() != null)?Double.parseDouble(moneyField.getValue().toString()):0D;
		return BigDecimal.valueOf(value);
	}
	
	public static java.sql.Date fromUtilDateToSqlDate(java.util.Date utilDate) {
		if(utilDate != null){
			return new java.sql.Date(utilDate.getTime());
		}
		return null;
	}
	
	public static java.util.Date fromSqlDateToUtilDate(java.sql.Date sqlDate) {
		if(sqlDate != null){
			return new java.util.Date(sqlDate.getTime());
		}
		return null;
	}
	
	public static java.util.Date fromTimestampToUtilDate(java.sql.Timestamp timestamp) {
		if(timestamp != null){
			return new java.util.Date(timestamp.getTime());
		}
		return null;
	}
	
	public static java.sql.Date fromTimestampToSqlDate(java.sql.Timestamp timestamp) {
		if(timestamp != null){
			return new java.sql.Date(timestamp.getTime());
		}
		return null;
	}
	
	public static double fromStringToDouble(String value) {
		//return Double.parseDouble(value.replaceAll(SpiritConstants.getCommaCharacter(), SpiritConstants.getBlankSpaceCharacter()));
		return Double.parseDouble(value.replaceAll(SpiritConstants.getCommaCharacter(), SpiritConstants.getEmptyCharacter()));
	}
	
	public BigDecimal evaluadorExpresionJavaScript(String expresion) throws GenericBusinessException {
		int errorStep = 0;
		ScriptEngineManager manager = new ScriptEngineManager();
		List<ScriptEngineFactory> factories = manager.getEngineFactories();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("LISTING AVAILABLE JS ENGINES : ");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for (ScriptEngineFactory sef : factories) {
			System.out.println(sef.getNames() + " [OK]");
		}
		// errorStep = 1 > Listado de engines recuperado con éxito
		errorStep++;
		ScriptEngine engine = manager.getEngineByName("rhino");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>> LOAD JS ENGINE: " + engine.NAME + " [OK]" );
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		// errorStep = 2 > Carga engine js realizada con éxito
		errorStep++;
		try {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(">>> EXPRESION: " + expresion);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			Object result = engine.eval(expresion);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(">>> EVAL EXPRESION: [OK]");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			// errorStep = 3 > Evaluación realizada con éxito
			errorStep++;
			Double resultadoDouble = 0D;
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(">>> RESULT CLASS: " + result.getClass());
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			if (result.getClass().equals(Integer.class))
				resultadoDouble = Double.parseDouble(String.valueOf(result));
			else
				resultadoDouble = (Double) result;
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(">>> PARSING RESULT CLASS: [OK]");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			// errorStep = 4 > Parsing realizado con éxito
			errorStep++;
			BigDecimal valor = redondeoValor(new BigDecimal(resultadoDouble));
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(">>> REDONDEO VALOR: [OK]");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			// errorStep = 5 > Redondeo realizado con éxito
			errorStep++;
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(">>> Total Error Steps Completed: [" + errorStep + "]");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			return valor;
		} catch (ScriptException se) {
			se.printStackTrace();
			throw new GenericBusinessException("Error en la evaluación de la expresión. Error Step >> " + errorStep);
		} catch (Exception se) {
			se.printStackTrace();
			throw new GenericBusinessException("Error general en la evaluación de la expresión. Error Step >> " + errorStep);
		}
	}
	
	public static java.sql.Timestamp fromUtilDateToTimestamp(java.util.Date utilDate) {
		return new Timestamp(utilDate.getTime());
	}
	
	public static java.sql.Timestamp fromSqlDateToTimestamp(java.sql.Date sqlDate) {
		return new Timestamp(sqlDate.getTime());
	}
	
	public static java.sql.Timestamp resetTimestampStartDate(java.sql.Timestamp startDate) {
		startDate.setHours(0);
		startDate.setMinutes(0);
		startDate.setSeconds(0);
		return startDate;
	}
	
	public static java.sql.Timestamp resetTimestampEndDate(java.sql.Timestamp endDate) {
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		return endDate;
	}
	
	public static String[] amountToWords(double value, boolean twoRows, MonedaIf moneda) {
		String checkValue = SpiritConstants.getDecimalFormatPattern().format(Double.valueOf(value));
		String decimalPart = checkValue.substring(checkValue.indexOf('.'), checkValue.length());			
		double dDecimalPart = 0D;
		if (!decimalPart.isEmpty())
			dDecimalPart = Double.valueOf(decimalPart);
		String[] amountInWords = (twoRows)?obtenerCantidadEnLetras(checkValue, dDecimalPart, new int[] { 70, 90 }, false, moneda):obtenerCantidadEnLetras(checkValue, dDecimalPart, new int[] { 90 }, false, moneda);
		return amountInWords;
	}
}
