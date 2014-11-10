package com.spirit.client;

/**
 * Clase estatica para convertir cantidades a letras
 * Permite especificar la cantidad de renglones que se desee con un largo especifico para cada uno
 * separando en silabas las palabras que no caben en el renglon
 * Permite incluir hasta 9 decimales traducirlos a letras siempre que sean hasta tres decimales
 * Permite expresar el valor 1 de las unidades enteras como "UNO" , "UNA" o "UN"
 *  segun se fije el parametro genero_unidad en 0,1 o 2
 * Retorna un array de String igual a la cantidad de renglones especificados
 * permite agregar texto : a) al principio ( prefijo_inicio )
 *                         b) despues de la parte entera ( sufijo_enteros )
 *                         c) antes de la parte decimal ( prefijo_decimal )
 *                         d) despues de la parte decimal ( sufijo_decimal )
 *                         e) al final  ( sufijo_final )
 *   ( dentro de cada uno de estos textos , el caracter '|' se usa para separar en silabas)
 *   ( si el texto comienza con un caracter '|' indica que no se debe dejar espacio entre la
 *     palabra anterior y este texto . Por ejemplo para agregar "/100.-" a continuacion de los
 *     decimales expresados en numeros ( 50/100.-  , 950/1000 , 567899/1000000 , etc. )
 * El valor maximo a traducir : usando el metodo getTexto(String cantidad) = 999999999999999.999999999
 * usando el metodo getTexto(double cantidad) = 9999999999999999.899999 ( este metodo puede variar la
 * cantidad de decimales si el valor es grande , no asi el metodo anterior.
 * Tiene metodos set... para cada uno de los parametros
 *
 * Fecha de creación: (20/05/2003 18:30:15)
 *
 
 * @author: Ruben D. Mori
 *
 */



public class RMCantidadEnLetras {
	
	public RMCantidadEnLetras() {
	}
	
	private static String[] xDos  = new String[]{"U|NO","UN","DOS","TRES","CUA|TRO","CIN|CO",
		"SEIS","SIE|TE","O|CHO","NUE|VE","DIEZ","ON|CE","DO|CE","TRE|CE",
		"CA|TOR|CE","QUIN|CE","DIE|CI|SEIS","DIE|CI|SIE|TE","DIE|CI|O|CHO",
		"DIE|CI|NUE|VE","VEIN|TE","VEIN|TI|UN","VEIN|TI|DOS","VEIN|TI|TRES",
		"VEIN|TI|CUA|TRO","VEIN|TI|CIN|CO","VEIN|TI|SEIS","VEINTI|SIE|TE",
		"VEIN|TI|O|CHO","VEIN|TI|NUE|VE"};
	
	private static String[] xUno  = new String[]{"CIEN","CIEN|TO","DOS|CIEN|TOS","TRES|CIEN|TOS",
		"CUA|TRO|CIEN|TOS","QUI|NIEN|TOS","SEIS|CIEN|TOS","SE|TE|CIEN|TOS",
		"O|CHO|CIEN|TOS","NO|VE|CIEN|TOS"};
	
	private static String[] xDiez = new String[]{"VEIN|TI|U|NO","","","TREIN|TA","CUA|REN|TA","CIN|CUEN|TA",
		"SE|SEN|TA","SE|TEN|TA","O|CHEN|TA","NO|VEN|TA" };
	
	private static String[] xEsp = new String[]{"Y","MIL","MI|LLON","MI|LLO|NES","BI|LLON","BI|LLO|NES"};
	private static String prefijo_inicio = "(", sufijo_enteros = "PE|SOS",
	prefijo_decimales = "CON", sufijo_decimales = "|/100.-",
	sufijo_final = ")", palabra_cero = "CE|RO";
	
	private static char   caracter_proteccion = 42;
	
	private static int[]  renglones = new int[] { 300 };
	
	private static char[][] datos;
	
	private static double importe , divisor , xtres, cantidad;
	
	private static int    tres, dos , uno, paso, decena, unidad, haymillones,cant_decimales = 2,
	genero_unidad = 0 , renglon , ajuste , posicion, posicion_corte,
	silaba , decimales ;
	
	private static boolean traduce_decimales = false;
	
	public static void setRenglones(int[] xrenglones )    {
		renglones = xrenglones;
	}
	
	public static void setGenero_unidad(int xgenero_unidad )    {
		genero_unidad = xgenero_unidad;
	}
	
	public static void setCantidad_decimales(int xcant_decimales)    {
		cant_decimales = xcant_decimales;
	}
	
	public static void setPrefijo_inicio(String xprefijo_inicio)    {
		prefijo_inicio = xprefijo_inicio;
	}
	
	public static void setSufijo_final(String xsufijo_final)    {
		sufijo_final = xsufijo_final;
	}
	
	public static void setSufijo_enteros(String xsufijo_enteros)    {
		sufijo_enteros = xsufijo_enteros;
	}
	
	public static void setPrefijo_decimales(String xprefijo_decimales)  {
		prefijo_decimales = xprefijo_decimales;
	}
	
	public static void setSufijo_decimales(String xsufijo_decimales)    {
		sufijo_decimales = xsufijo_decimales;
	}
	
	public static void setCaracter_proteccion(char xcaracter_proteccion)    {
		caracter_proteccion = xcaracter_proteccion;
	}
	
	public static void setTraduce_decimales(boolean xtraduce_decimales) {
		traduce_decimales = xtraduce_decimales;
	}
	
	public static void setPalabra_cero(String xpalabra_cero) {
		palabra_cero = xpalabra_cero;
	}
	
	private static String[] getTexto(double importe, int qdecimales) {
		decimales = qdecimales;
		divisor   = 1.00E12;
		haymillones = 0;
		
		if ( cant_decimales > 3 )  traduce_decimales = false;
		
		ajuste = 10;
		
		for ( int i=1; i < cant_decimales; i++ )    {
			ajuste = ajuste * 10;
		}
		
		if ( cant_decimales == 0 ) ajuste = 0;
		
		datos = new char[renglones.length][renglones[0]];
		
		
		for ( int x=0; x < renglones.length; x++ )  {
			datos[x] = new char[renglones[x]];
			for ( int y=0; y < renglones[x]; y++ )  {
				datos[x][y] = caracter_proteccion;
			}
		}
		
		xDos[0] = "U|NO";
		xDiez[0] = "VEIN|TI|UNO";
		if ( genero_unidad == 2 )   {
			xDos[0] = "U|NO";
			xDiez[0] = "VEIN|TI|UN";
		}
		if ( genero_unidad == 1 )   {
			xDos[0] = "U|NA";
			xDiez[0] = "VEIN|TI|U|NA";
		}
		
		renglon = posicion = 0;
		
		
		for ( paso = 0; paso < 5; paso++ ) {
			xtres  = ( importe / divisor );
			tres   = (int) xtres;
			importe = importe - (double)(tres * divisor);
			divisor = divisor / 1000;
			if ( tres >= 0 ) traducir(tres);
		}
		
		if ( ( palabra_cero.length() > 0 ) && renglon == 0 && posicion == 0 )   {
			if ( prefijo_inicio.length() > 0 ) pasarTexto(prefijo_inicio);
			pasarTexto(palabra_cero);
		}
		
		if (( sufijo_enteros.length() > 0 ) && ( ( renglon > 0 ) || ( posicion > prefijo_inicio.length())))
			pasarTexto(sufijo_enteros);
		
		tres = (int) ( importe * ajuste );
		
		if ( decimales >= 0 ) tres = decimales;
		
		if ( tres >=0 && tres <= 9 && ( prefijo_decimales.length() > 0 ) && ( renglon != 0 || posicion != 0)) {
			pasarTexto(prefijo_decimales);
		}
		
		if ( tres >= 0 && traduce_decimales )    {
			paso = 5;
			traducir(tres);
		}
		
		if ( tres >= 0 && !traduce_decimales )  pasarTexto(("" + tres + ""));
		
		if ( tres >= 0 && ( sufijo_decimales.length() > 0 )) pasarTexto(sufijo_decimales);
		
		if ( sufijo_final.length() > 0 ) pasarTexto(sufijo_final);
		
		String[] texto = new String[datos.length];
		
		for ( int i=0; i < datos.length; i++ )  {
			texto[i] = new String(datos[i]);
		}
		
		return texto;
	}
	
	private static void iniciarSilaba() {
		posicion_corte = posicion;
		silaba = 0;
	}
	
	private static void paseCaracter(char caracter)  {
		datos[renglon][posicion] = caracter;
		silaba++;
		posicion++;
	}
	
	private static void sumeRenglon()   {
		renglon++;
		posicion = 0;
		iniciarSilaba();
	}
	
	
	private static void pasarTexto(String palabra) {
		char[] desglose = palabra.toCharArray();
		
		if (posicion > 0 && ( posicion < ( renglones[renglon] -1 )) && ( desglose[0] != 124 ))  {
			datos[renglon][posicion] = 32;
			posicion ++;
		} else if (posicion > 0 && ( posicion == ( renglones[renglon] -1 )) && ( desglose[0] != 124 ))  {
			datos[renglon][posicion] = 32;
			sumeRenglon();
		}
		
		iniciarSilaba();
		
		for ( int i=0; i < desglose.length; i++ ) {
			
			if ( desglose[i] == 124 && i > 0  && ( posicion == ( renglones[renglon] -1 ))) {
				datos[renglon][posicion] = 45;
				sumeRenglon();
			} else if ( desglose[i] == 124 && i > 0  && ( posicion < ( renglones[renglon] -1 ))) {
				iniciarSilaba();
			} else if ( desglose[i] != 124 && ( posicion < ( renglones[renglon] -1 ))) {
				paseCaracter(desglose[i]);
			} else if ( (desglose[i] != 124) && ( posicion == ( renglones[renglon] -1 )) && ( i == ( desglose.length - 1)) ) {
				paseCaracter(desglose[i]);
				sumeRenglon();
			} else if ( desglose[i] != 124 && ( posicion == ( renglones[renglon] -1 )) && ( i < ( desglose.length - 1))) {
				posicion = posicion_corte;
				datos[renglon][posicion] = 45;
				posicion++;
				
				if ( posicion <  renglones[renglon]  ) {
					for ( posicion=posicion; posicion < renglones[renglon]; posicion++ )   {
						datos[renglon][posicion] = 45;
					}
				}
			
				int xsilaba = silaba;
				sumeRenglon();
				
				for ( int z = (i - xsilaba); z < ( i + 1 ); z++ )  {
					datos[renglon][posicion] = desglose[z];
					posicion++;
				}
			}
		}
	}
	
	private static void traducir(int mil) {
		if ( renglon == 0 && posicion == 0 && prefijo_inicio.length() > 0 )
			pasarTexto(prefijo_inicio);
		uno  =  mil / 100;
		dos  =  mil - ( uno * 100 );
		decena = dos / 10;
		unidad = dos - ( decena * 10 );
		if ( mil == 100 )  pasarTexto(xUno[0]);
		else if ( uno > 0 ) pasarTexto(xUno[uno]);
		
		
		if ( (dos > 1 && dos < 30 ) && ( paso != 4 || dos != 21 ))  pasarTexto(xDos[dos]);
		if ( paso == 4 && dos == 21 )   pasarTexto(xDiez[0]);
		if ( paso == 4 && dos == 1 )    pasarTexto(xDos[0]);
		
		if ( dos > 29 ) {
			pasarTexto(xDiez[decena]);
			if ( unidad > 0 )   {
				pasarTexto(xEsp[0]);
				if ( paso != 4 || unidad != 1 ) pasarTexto(xDos[unidad]);
				if ( paso == 4 && unidad == 1 ) pasarTexto(xDos[0]);
			}
		}
		if ( paso == 0 && mil == 1 )    {
			pasarTexto(xEsp[4]);
		}
		if ( paso == 0 && mil > 1 )     {
			pasarTexto(xEsp[5]);
		}
		if (( paso == 2 && mil > 1 ) || haymillones == 1 )  {
			pasarTexto(xEsp[3]);
			haymillones = 0;
		}
		if ( paso == 1 && mil > 0 )     {
			pasarTexto(xEsp[1]);
			haymillones = 1;
		}
		if (( paso == 2 && mil == 1 && haymillones == 0 ))  {
			pasarTexto(xEsp[2]);
		}
		if ( paso == 3 && mil > 0 )     {
			pasarTexto(xEsp[1]);
		}
		
	}
	public static String[] getTexto(String cantidad) {
		String parte_entera = ((cantidad.substring(0,cantidad.indexOf('.'))) + ".0");
		cantidad = cantidad.substring(cantidad.indexOf('.') + 1 );
		if ( cantidad.length() > cant_decimales ) cantidad = cantidad.substring(0,cant_decimales);
		if ( cantidad.length() < cant_decimales )    {
			for ( int i = cantidad.length(); i < cant_decimales; i++ )   {
				cantidad = cantidad + "0";
			}
		}
		/*if (Integer.parseInt(cantidad) >= 0 && Integer.parseInt(cantidad) <= 9)
			cantidad = "0" + cantidad;*/
		
		return getTexto( Double.parseDouble(parte_entera),Integer.parseInt(cantidad) );
	}
	
	public static String[] getTexto(double cantidad) {
		return getTexto(cantidad,0);
	}
	
	public static void main(String[] args) {
		// Fija la cantidad de renglones y longitud de cada uno .Valor por defecto { 300 }
		int[] renglones = new int[]{100,100};
		RMCantidadEnLetras.setRenglones(renglones);
		// Fija el texto a agregar antes de la traduccion de los decimales. Valor por defecto "CON"
		RMCantidadEnLetras.setPrefijo_decimales("CON");
		// Fija el texto a agregar al principio .Valor por defecto  ""
		RMCantidadEnLetras.setPrefijo_inicio("[");
		// Indica si los decimales deben ser expresados en letras (true) o en numeros (false)
		// Valor por defecto false
		RMCantidadEnLetras.setTraduce_decimales(true);
		// Si es distinta de "" indica que se debe incluir si la parte entera tiene valor = 0.
		// Valor por defecto "" . O sea que si la parte entera == 0 no se incluye nada
		RMCantidadEnLetras.setPalabra_cero("CE|RO");
		// fija la cantidad de decimales a incluir. ( 0 a 9 ). Si la cantidad pasada tiene mas decimales
		// se truncan y si tiene menos se agregan 0. Si este valor es > 3 el parametro traduce_decimales
		// se pasa a false
		RMCantidadEnLetras.setCantidad_decimales(2);
		// Indica como debe traducirse el valor 1 de las unidades ( el de las otras posiciones :
		// unidades de mil , de millones, etc siempre se traducen a "UN"
		// valor 0 , traduce a "UNO" (valor por defecto) , valor 1 traduce a "UNA" y valor 2 a "UN"
		RMCantidadEnLetras.setGenero_unidad(0);
		// Fija el texto a agregar detras de los decimales. Valor por defecto "|/100.-"
		// que indica no dejar espacio entre los numeros y /100.-
		// Este texto debe estar acorde con la cantidad de decimales y si estos son traducidos o no
		// Quedaria mal usar por ejemplo: "/100.-" si la cantidad de decimales no = 2 o si se traducen
		// seria mas logico " CEN|TA|VOS.-"
		RMCantidadEnLetras.setSufijo_decimales("CEN|TA|VOS.-");
		// Fija el texto a agregar despues de traducidos los enteros. Valor por defecto ""
		// Podria ser "LI|TROS" , "KI|LOS" , "PE|SOS" , "DO|LA|RES |ES|TA|DO|U|NI|DEN|SES"
		// los caracteres "|" indican los posibles cortes en silabas para cuando la palabra no quepa
		// completa en el renglon . SI NO SE INCLUYEN , LA PALABRA NO SERA SEPARADA Y SI NO CABE PASARA
		// COMPLETA AL RENGLON SIGUIENTE
		RMCantidadEnLetras.setSufijo_enteros("DÓ|LA|RES");
		// Fija el texto a agregar al final
		RMCantidadEnLetras.setSufijo_final("]");
		// Fija el caracter con el que seran completadas todas las posiciones de todos los renglones
		// que no sean utilizadas. Las posiciones que deban ser dejadas al final de cada renglon porque
		// haya que seguir en el proximo se completaran con el caracter "-"
		//RMCantidadEnLetras.setCaracter_proteccion('*');
		// Esta es la cifra que produce el texto mas largo (independientemente de los textos agregados)
		//
		//String importe = "999999999999999.999999999";
		String importe = "3397892.51";
		System.out.println(" String pasada : " + importe);
		// Pasando la cantidad como String se asegura que los decimales no varien , cosa que sucede
		// usando el metodo que recibe un double cuando la cantidad es muy grande
		RMCantidadEnLetras.setGenero_unidad(2);
		String[] texto = RMCantidadEnLetras.getTexto(importe);
		for ( int i = 0 ; i < texto.length; i++ )   {
			System.out.println(texto[i]);
		}
		double cantidad = 1.0;
		System.out.println("Valor pasado : " + cantidad );
		texto = RMCantidadEnLetras.getTexto(cantidad);
		for ( int i = 0 ; i < texto.length; i++ )   {
			System.out.println(texto[i]);
		}
		// Este es el mayor valor que puede traducirse pasando la cantidad como double
		// Si se usa String se puede traducir hasta 999 999 999 999 999.999 999 999
		// El valor traducido de los decimales difiere de los pasados
		//cantidad = 999999999999999.899999;
		cantidad = 135759.56;
		System.out.println("Valor pasado : " + cantidad );
		RMCantidadEnLetras.setPrefijo_inicio("(");
		RMCantidadEnLetras.setSufijo_final(")");
		RMCantidadEnLetras.setCantidad_decimales(6);
		//RMCantidadEnLetras.setCaracter_proteccion('-');
		RMCantidadEnLetras.setSufijo_decimales("CEN|TA|VOS.-");
		RMCantidadEnLetras.setSufijo_enteros("DÓ|LA|RES");
		texto = RMCantidadEnLetras.getTexto(cantidad);
		for ( int i = 0 ; i < texto.length; i++ )   {
			System.out.println(texto[i]);
		}
	}
}
