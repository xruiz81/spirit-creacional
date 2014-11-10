package com.spirit.servicelocator;
/**
 * Esta clase es creada para soportar el ambiente de ejecucion en modo stand alone y tambien que se pueda
 * instalar la aplicacion en un ear.
 * Cuando se genera un ear, el nombre JNDI debe incluir el nombre del archivo ear, pero en modo stand alone
 * no se tiene un ear instalado, por lo que no se necesita el nombre del ear.
 * @author lmunoz
 *
 */
public class JNDIName {
	
	public static String earName ="spirit/";

}
