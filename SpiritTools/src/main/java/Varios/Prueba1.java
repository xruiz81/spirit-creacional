package Varios;

public class Prueba1 {
	public static void main(String[] args) {

		String programa1 = "SEX AND CITY";
		String programa2 = "HAGA NEGOCIO CONMIGO(SOBREIMP.)";
		String programa3 = "HECHICERAS(D)";
		
		
		String texto = programa3;
		int abreParentesis = texto.indexOf("(");
		int cierraParentesis = texto.indexOf(")");
		int longNombre = abreParentesis>0 ? abreParentesis : texto.length();
		String nombrePrograma = texto.substring(0, longNombre);
		System.out.println("programa: "+nombrePrograma);
		
		if ( abreParentesis > 0 && cierraParentesis > 0 && cierraParentesis > abreParentesis ){
			String comercial = texto.substring(abreParentesis+1,cierraParentesis);
			System.out.println("comercial: "+comercial);
		}
		
		/*String[] aa = new String[]{"1"}; 
		StringBuilder sb = new StringBuilder();
		for ( String s : aa )
			sb.append(s+",");
		sb.replace(sb.length()-1, sb.length(), "");
		System.out.println("Sb: "+sb.toString());*/
	}
}
