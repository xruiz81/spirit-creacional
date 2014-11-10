package Varios;

import java.util.Scanner;

public class ahorcado {
	// Atributos de clase
	private static final int max_letras = 10;
	private static final int max_intentos = 7;
	private static int intentos = 0;
	private static boolean encontrada = false;
	public static char[] palabra_ori = new char[max_letras];
	public static char[] palabra_aux = new char[max_letras];
	public static Scanner sc = new Scanner(System.in);
	public static String palabra;

	// Limpiar aux
	public static void limpiarPalabraAux() {
		for (int i = 0; i < palabra_aux.length; i++)
			palabra_aux[i] = ' ';
		for (int i = 0; i < palabra.length(); i++)
			palabra_aux[i] = '_';
	}

	// Menu
	public static void menu() {
		int opcion = 0;
		do {
			System.out.println(" Menu : \n");
			System.out.println(" 1) Definir palabra.");
			System.out.println(" 2) Iniciar Juego.");
			System.out.println(" 3) Salir. ");
			System.out.print(" \n Opcion >> ");
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				definirPalabra();
				break;
			case 2:
				iniciarJuego();
				break;
			case 3:
				System.out.println("Ejecucion terminada!!!");
				break;
			}
		} while (opcion != 3);
	}

	// Definicion de la palabra en el juego
	public static void definirPalabra() {
		System.out.print(" Ingrese palabra : ");
		palabra = sc.next();
		for (int i = 0; i < palabra.length(); i++) {
			palabra_ori[i] = palabra.charAt(i);
			palabra_aux[i] = '_';
		}
		System.out.println(" Palabra definida exitosamente!!!");
	}

	// Iniciar juego
	public static void iniciarJuego() {
		limpiarPalabraAux();
		char letra;
		do {
			System.out.println("\n");
			// Muestro la palabra
			for (char l : palabra_aux) {
				if ((int) l != 0) {
					System.out.print(" " + l);
				}
			}
			System.out.print(" \n\n Ingrese una letra : ");
			letra = new String(sc.next()).charAt(0);
			buscaLetraEnPalabra(letra);
			compararPalabras();
			intentos++;
			System.out.println(" Intentos " + intentos + " Max intentos "
					+ max_intentos);
		} while (intentos < max_intentos && !encontrada);
		if (intentos < max_intentos && encontrada) {
			System.out.println(" Encontrada >> " + encontrada);
			System.out.println(" ----------------------------------------- ");
			System.out.println(" - G A N A S T E S U P E R ! ! - ");
			System.out.println(" ----------------------------------------- ");
			System.out.println(" Numero de intentos requeridos : " + intentos);
		} else {
			System.out.println(" ----------------------------------------- ");
			System.out.println(" - G A M E O V E R =) - ");
			System.out.println(" ----------------------------------------- ");
		}
	}

	public static void main(String[] args) {
		menu();
	}

	// Busca letra en la palabra original y la pinta en la palabra auxiliar
	public static void buscaLetraEnPalabra(char c) {
		System.out.println(" Letra ingresada " + c);
		for (int i = 0; i < palabra_ori.length; i++) {
			if (c == palabra_ori[i]) {
				palabra_aux[i] = c;
			}
		}
	}

	// Compara la palabra original con la auxiliar
	public static void compararPalabras() {
		for (int i = 0; i < palabra_ori.length; i++) {
			if ((int) palabra_ori[i] != 0) {
				if (palabra_ori[i] == palabra_aux[i]) {
					encontrada = true;
				} else {
					encontrada = false;
					break;
				}
			}
		}
	}
}
