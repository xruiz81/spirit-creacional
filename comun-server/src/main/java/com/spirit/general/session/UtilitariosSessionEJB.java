package com.spirit.general.session;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JFormattedTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.RMCantidadEnLetras;
import com.spirit.client.SpiritConstants;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoParametroIf;

@Stateless
public class UtilitariosSessionEJB implements UtilitariosSessionRemote, UtilitariosSessionLocal {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	@EJB
	private ParametroEmpresaSessionLocal parametroEmpresaLocal;

	private ParametroEmpresaIf parametroEmpresaRedondeo = null;
	
	@EJB private TipoParametroSessionLocal tipoParametroLocal;

	static Locale esLocale = new Locale("es");
	static Locale enLocale = new Locale("en");
	static DecimalFormat df = new DecimalFormat("00");

	static String[] mesesMayusculas = new String[] { "ENERO", "FEBRERO",
			"MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE",
			"OCTUBRE", "NOVIEMBRE", "DICIEMBRE" };

	public Timestamp getTimeStamp(java.util.Date fecha) {
		return new Timestamp(fecha.getTime());
	}
	
	public Timestamp getTimeStamp(Date fecha) {
		return new Timestamp(fecha.getTime());
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Timestamp getServerDateTimeStamp() {
		try {
			return new Timestamp(new java.util.Date().getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Date getServerDateSql() throws GenericBusinessException {
		try {
			return new Date(new java.util.Date().getTime());
		} catch (Exception e) {
			throw new GenericBusinessException(
					"Error al generar fecha del servidor");
		}
	}

	public java.util.Date getServerDateUtil() throws GenericBusinessException {
		try {
			return new java.util.Date();
		} catch (Exception e) {
			throw new GenericBusinessException(
					"Error al generar fecha del servidor");
		}
	}

	public String getMonthFromDate(java.util.Date d) {
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormatter.format(d).substring(3, 5);
	}

	public String getYearFromDate(java.util.Date d) {
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormatter.format(d).substring(6, 10);
	}

	/*
	 * public BigDecimal redondeoValor(BigDecimal valor) { if (valor != null) {
	 * Double decimales = 2D; try { if
	 * (!parametroEmpresaLocal.findParametroEmpresaByCodigo("REDONDEO").isEmpty()) {
	 * ParametroEmpresaIf parametroEmpresa = (ParametroEmpresaIf)
	 * parametroEmpresaLocal
	 * .findParametroEmpresaByCodigo("REDONDEO").iterator().next(); decimales =
	 * Double.valueOf(parametroEmpresa.getValor()); } } catch
	 * (NumberFormatException e) { e.printStackTrace(); } catch
	 * (GenericBusinessException e) { e.printStackTrace(); } return
	 * valor.setScale(decimales.intValue(), BigDecimal.ROUND_HALF_UP); } return
	 * null; }
	 */

	/*public BigDecimal redondeoValor(BigDecimal valor) {
		return BigDecimal.valueOf(redondeoValor(valor.doubleValue()));
	}*/

	private ParametroEmpresaIf getParametroEmpresaRedondeo()
			throws GenericBusinessException {
		if (parametroEmpresaRedondeo == null) {
			Collection<ParametroEmpresaIf> parametros = parametroEmpresaLocal
			.findParametroEmpresaByCodigo("REDONDEO");
			if ( parametros.size() == 1 )
				parametroEmpresaRedondeo =  parametros.iterator().next();
			else if ( parametros.size() > 1 )
				throw new GenericBusinessException("Existe mas de un Parametro de Empresa llamado REDONDEO");
			else
				throw new GenericBusinessException("No existe Parametro de Empresa llamado REDONDEO");
		}
		return parametroEmpresaRedondeo;
	}

	/*public double redondeoValor(double valor)  {
		double valorR = 0.0;
		Double decimales = 2D;
		try {
			ParametroEmpresaIf parametroEmpresa = getParametroEmpresaRedondeo();
			decimales = Double.valueOf(parametroEmpresa.getValor());
		} catch (GenericBusinessException e) {
			
		}
		valorR = BigDecimal.valueOf(valor).setScale(decimales.intValue(),
				BigDecimal.ROUND_HALF_UP).doubleValue();
		return valorR;
	}*/
	
	public double redondeoValor(double valor)  {
		double valorR = 0.0;
		Double decimales = 2D;
		valorR = BigDecimal.valueOf(valor).setScale(decimales.intValue(),BigDecimal.ROUND_HALF_UP).doubleValue();
		return valorR;
	}
	
	public BigDecimal redondeoValor(BigDecimal valor)  {
		BigDecimal valorR = BigDecimal.ZERO;
		Double decimales = 2D;
		valorR = valor.setScale(decimales.intValue(),BigDecimal.ROUND_HALF_UP);
		return valorR;
	}
	
	public static BigDecimal redondeoValor(BigDecimal valor, int escala) {
		if ( valor != null ){
			return valor.setScale(escala, BigDecimal.ROUND_HALF_UP);
		}
		return null;
	}
	
	public double redondeoValorCuatroDecimales(double valor)  {
		double valorR = 0.0;
		Double decimales = 4D;
		valorR = BigDecimal.valueOf(valor).setScale(decimales.intValue(),BigDecimal.ROUND_HALF_UP).doubleValue();
		return valorR;
	}
	
	public BigDecimal redondeoValorCuatroDecimales(BigDecimal valor)  {
		BigDecimal valorR = BigDecimal.ZERO;
		Double decimales = 4D;
		valorR = valor.setScale(decimales.intValue(),BigDecimal.ROUND_HALF_UP);
		return valorR;
	}

	public String getDayFromDate(java.util.Date d) {
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormatter.format(d).substring(0, 2);
	}

	public String getMonthBeforeFromDate(java.util.Date d) {
		Calendar cal = GregorianCalendar.getInstance();
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		cal.setTime(d);
		cal.add(Calendar.MONTH, -1);
		return dateFormatter.format(cal.getTime()).substring(3, 5);
	}

	public String getYearBeforeFromDate(java.util.Date d) {
		Calendar cal = GregorianCalendar.getInstance();
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		cal.setTime(d);
		cal.add(Calendar.MONTH, -1);
		String dateString = dateFormatter.format(cal.getTime());
		return dateString.substring(6, 10);
	}

	public java.util.Date dateHoy() throws ParseException {
		java.util.Date today = new java.util.Date();
		String dia = getDayFromDate(today);
		String mes = getMonthFromDate(today);
		String anio = getYearFromDate(today);
		String hoy = anio + "-" + mes + "-" + dia;
		java.util.Date dateHoy = new SimpleDateFormat("yyyy-MM-dd").parse(hoy);
		return dateHoy;
	}

	public java.sql.Date fechaHoy() throws ParseException {
		java.util.Date today = new java.util.Date();
		java.sql.Date fechaHoy = new java.sql.Date(today.getYear(), today
				.getMonth(), today.getDate());
		return fechaHoy;
	}

	public String getFechaMesAnioUppercase(java.util.Date date){
		DateFormat dateformat = new SimpleDateFormat("MMMM - yyyy",esLocale);
		return dateformat.format(date).toUpperCase();
	}
	
	public String getFechaUppercase(java.sql.Date date) {
		DateFormat dateformat = new SimpleDateFormat("dd - MMMM - yyyy",
				esLocale);
		return dateformat.format(date).toUpperCase();
	}

	public String getFechaUppercase(java.util.Date date) {
		DateFormat dateformat = new SimpleDateFormat("dd - MMMM - yyyy",
				esLocale);
		return dateformat.format(date).toUpperCase();
	}

	public String getFechaCortaUppercase(java.util.Date date){
		DateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy",esLocale);
		return dateformat.format(date).toUpperCase();	
	}
	
	public String getFechaCortaUppercase(java.sql.Date date){
		DateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy",esLocale);
		return dateformat.format(date).toUpperCase();	
	}
	
	public Vector<Object> collectionToVector(Collection<Object> coleccion) {
		// Iterator it = coleccion.iterator();
		Vector<Object> vector = new Vector<Object>(coleccion);
		/*
		 * while (it.hasNext()) { Object objeto = (Object) it.next(); if (objeto !=
		 * null) vector.add(objeto); }
		 */
		return vector;
	}

	public Date getFechaFinMes(int mes, int anio) throws ParseException {
		Date fechaHoy = null;

		if (mes == 0 || mes == 2 || mes == 4 || mes == 6 || mes == 7
				|| mes == 9 || mes == 11)
			fechaHoy = new java.sql.Date(anio, mes, 31);
		else if (mes == 3 || mes == 5 || mes == 8 || mes == 10)
			fechaHoy = new java.sql.Date(anio, mes, 30);
		else if (mes == 1 && anio % 4 == 0)
			fechaHoy = new java.sql.Date(anio, mes, 29);
		else if (mes == 1)
			fechaHoy = new java.sql.Date(anio, mes, 28);

		return fechaHoy;
	}

	public Calendar getCalendarFinMes(int mes, int anio) {
		Calendar cal = null;

		if (mes == 0 || mes == 2 || mes == 4 || mes == 6 || mes == 7
				|| mes == 9 || mes == 11)
			cal = new GregorianCalendar(anio, mes, 31);
		else if (mes == 3 || mes == 5 || mes == 8 || mes == 10)
			cal = new GregorianCalendar(anio, mes, 30);
		else if (mes == 1 && anio % 4 == 0)
			cal = new GregorianCalendar(anio, mes, 29);
		else if (mes == 1)
			cal = new GregorianCalendar(anio, mes, 28);

		return cal;
	}

	public String getNombreMes(int mes) throws GenericBusinessException {
		if (mes < 1)
			throw new GenericBusinessException("Numero del mes empieza en 1");
		else if (mes > 12)
			throw new GenericBusinessException("Numero del mes termina en 12");
		return mesesMayusculas[mes - 1];
	}

	public String getNombreMes(String mesString)
			throws GenericBusinessException {
		try {
			Integer mes = Integer.parseInt(mesString);
			if (mes < 1)
				throw new GenericBusinessException(
						"Numero del mes empieza en 1");
			else if (mes > 12)
				throw new GenericBusinessException(
						"Numero del mes termina en 12");
			return mesesMayusculas[mes - 1];
		} catch (NumberFormatException e) {
			throw new GenericBusinessException(
					"Error al convertir mes de String a entero");
		}
	}

	public Vector<CarteraIf> removerCarterasRepetidas(
			Vector<CarteraIf> carterasColeccion) {
		Map<Long, CarteraIf> carterasMapa = new HashMap<Long, CarteraIf>();
		for (CarteraIf carteraRepetida : carterasColeccion) {
			carterasMapa.put(carteraRepetida.getId(), carteraRepetida);
		}
		carterasColeccion.clear();
		Iterator carterasMapaIt = carterasMapa.keySet().iterator();
		while (carterasMapaIt.hasNext()) {
			Long key = (Long) carterasMapaIt.next();
			carterasColeccion.add(carterasMapa.get(key));
		}

		return carterasColeccion;
	}

	public String[] getMesesMayusculas() {
		return mesesMayusculas;
	}
	
	public java.sql.Timestamp resetTimestampStartDate(java.sql.Timestamp startDate) {
		startDate.setHours(0);
		startDate.setMinutes(0);
		startDate.setSeconds(0);
		return startDate;
	}
	
	public java.sql.Timestamp resetTimestampEndDate(java.sql.Timestamp endDate) {
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		return endDate;
	}
	
	public Collection<ParametroEmpresaIf> getParametroEmpresa(Long idEmpresa,
			TipoParametroIf tp, Boolean esBase,String codigoParametro) throws GenericBusinessException {
		Map<String,Object> mapaParametro = new HashMap<String,Object>();
		mapaParametro.put("empresaId",idEmpresa);
		mapaParametro.put("tipoparametroId",tp.getId());
		if ( esBase )
			mapaParametro.put("codigo",codigoParametro+"%");
		else
			mapaParametro.put("codigo",codigoParametro);
		Collection<ParametroEmpresaIf> parametros = parametroEmpresaLocal.findParametroEmpresaByQuery(mapaParametro);
		return parametros;
	}
	
	public TipoParametroIf getTipoParametro(Long empresaId,String codigo) throws GenericBusinessException{
		Map<String,Object> mapaTipo = new HashMap<String,Object>();
		mapaTipo.put("empresaId",empresaId);
		mapaTipo.put("codigo",codigo);
		Collection<TipoParametroIf> tipos = tipoParametroLocal.findTipoParametroByQuery(mapaTipo);
		if ( tipos == null || tipos.size() == 0  )
			throw new GenericBusinessException("No existe tipo de parametro con codigo "+codigo+"!!");
		return tipos.iterator().next();
	}
	
	public ParametroEmpresaIf getParametroEmpresa (Long empresaId,String codigoTipoParametro,String codigoParametro,String mensajeDeError) throws GenericBusinessException{
		TipoParametroIf tp = getTipoParametro(empresaId,codigoTipoParametro);
		//if ( tp == null )
		//	throw new GenericBusinessException("No existe Tipo de Parametro con codigo "+codigoTipoParametro);
		
		//BUSQUEDA DE RUBRO PARA SUELDO BASICO DEL EMPLEADO
		Collection<ParametroEmpresaIf> pes = getParametroEmpresa(empresaId, tp,false, codigoParametro);
		if ( pes.size() == 0 )
			throw new GenericBusinessException(
				"No existe Parametro de Empresa con codigo " + codigoParametro + 
				(mensajeDeError == null || "".equals(mensajeDeError) ? "." : (",\n"+mensajeDeError) ) );
		ParametroEmpresaIf pe = pes.iterator().next();
		return pe;
	}
	
	public long obtenerDiferenciaDias(java.sql.Date fecha1, java.sql.Date fecha2) {
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
		NumberFormat dispFormat = NumberFormat.getCurrencyInstance();
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
	
	public java.sql.Timestamp fromUtilDateToTimestamp(java.util.Date utilDate) {
		return new Timestamp(utilDate.getTime());
	}
	
	public java.sql.Timestamp fromSqlDateToTimestamp(java.sql.Date sqlDate) {
		return new Timestamp(sqlDate.getTime());
	}
	
	public java.util.Date fromTimestampToUtilDate(java.sql.Timestamp timestamp) {
		return new java.util.Date(timestamp.getTime());
	}
	
	public java.sql.Date fromTimestampToSqlDate(java.sql.Timestamp timestamp) {
		return new java.sql.Date(timestamp.getTime());
	}
	
	public java.sql.Date fromUtilDateToSqlDate(java.util.Date utilDate) {
		return new java.sql.Date(utilDate.getTime());
	}
	
	public java.util.Date fromSqlDateToUtilDate(java.sql.Date sqlDate) {
		return new java.util.Date(sqlDate.getTime());
	}
	
	public double fromStringToDouble(String value) {
		return Double.parseDouble(value.replaceAll(SpiritConstants.getCommaCharacter(), SpiritConstants.getBlankSpaceCharacter()));
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
	
	public String[] obtenerCantidadEnLetras(String cantidadEnNumeros, Double parteDecimal, int[] vectorRenglones, boolean caracterProteccion) {
		int[] renglones = vectorRenglones;
		RMCantidadEnLetras.setRenglones(renglones);
		RMCantidadEnLetras.setPalabra_cero("CE|RO");
		RMCantidadEnLetras.setGenero_unidad(2);
		RMCantidadEnLetras.setPrefijo_inicio("");
		RMCantidadEnLetras.setSufijo_final("DÓ|LA|RES");
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
		String[] texto = RMCantidadEnLetras.getTexto(removeDecimalFormat(cantidadEnNumeros));
		
		if (parteDecimal == 0D) {
			for (int i=0; i<texto.length; i++) {
				texto[i] = texto[i].replaceAll(" CON "," ");
			}
		}
		
		if (parteDecimal >= 0D && parteDecimal <= 9D) {
			for (int i=0; i<texto.length; i++) {
				texto[i] = texto[i].replaceAll("0 ","0");
				texto[i] = texto[i].replaceAll("0DÓLARES", "0 DÓLARES");
			}
		}
		
		return texto;
	}
	
	public String[] amountToWords(double value) {
		String checkValue = SpiritConstants.getDecimalFormatPattern().format(Double.valueOf(value));
		String decimalPart = checkValue.substring(checkValue.indexOf('.'), checkValue.length());			
		double dDecimalPart = 0D;
		if (!decimalPart.isEmpty())
			dDecimalPart = Double.valueOf(decimalPart);
		String[] amountInWords = obtenerCantidadEnLetras(checkValue, dDecimalPart, new int[] { 70, 90 }, false);
		return amountInWords;
	}
	
	public String removeDecimalFormat(String str) {
		//return str.replaceAll("\\.", "").replaceAll(",", "\\.");
		return str.replaceAll(",", "");
	}
}
