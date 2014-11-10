package ArchivosTxt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

public class ManejoTxt {

	
	
	void cambiarEnTodoDocumento(String ruta, String antes, String despues) throws IOException{
		File entrada = new File(ruta);
		BufferedReader br = null;
		BufferedWriter bw = null;
		if ( entrada.exists() ){
			try{
				//URL u = new URL(ruta);
				//Reader r = new InputStreamReader(u.openStream());
				Reader r = new FileReader(entrada);
				br  = new BufferedReader(r);
				String linea = ""; 
				int numero = 1;
				
				File salida = new File(ruta+"_"+numero);
				while( salida.exists() )
					salida = new File(ruta+"_"+numero);
				Writer w = new FileWriter(salida);
				bw = new BufferedWriter(w);
				while ( (linea = br.readLine()) != null ){
					bw.write(linea.replaceAll(antes, despues));
				}
			} catch(IOException e){
				throw new IOException(e.getMessage(),e.getCause());
			} catch(Exception e){
				System.out.println("Error: "+e.getMessage());
			} finally{
				if ( br != null )
					br.close();
				if ( bw != null )
					bw.close();
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		ManejoTxt mt = new ManejoTxt();
		try {
			mt.cambiarEnTodoDocumento(
			 "C:\\Documents and Settings\\Tatiana de Reyes\\Mis documentos\\" +
			 "Khomo\\Programacion\\Datos Pruebas\\EXPORT\\crtab.txt"
			 , ","
			 , ".");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
