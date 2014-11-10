package ImportacionAsientos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class VerificaCuentas {
	
	
	static Connection conn = null;
	static Statement stmt = null;
	static FileInputStream fis;
	
	static Comparator<DetalleAsiento> comparadorNumeroAsiento = new Comparator<DetalleAsiento>(){
		public int compare(DetalleAsiento o1, DetalleAsiento o2) {
			return Integer.valueOf( o1.getNumero() ).compareTo(o2.getNumero());
		}
	};
	
	static List<DetalleAsiento> verificarCuentas(String driverBase,String urlBase,String usuario
			,String clave,String nombreArchivoExcel,String nombreHoja) throws Exception  {
			
		try{
				File file = new File(nombreArchivoExcel);
				fis = new FileInputStream(file);
				
				ArrayList<DetalleAsiento> detalles = ManejarExcel.procesarArchivoExcel(fis,nombreHoja );
				Collections.sort(detalles, comparadorNumeroAsiento);
				
				//		INSERCION EN LA TABLA
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				Class.forName(driverBase);
		
				//String url = "jdbc:oracle:thin:@//110.110.2.45:1521/des";
				
				Connection conn = DriverManager.getConnection(urlBase,usuario, clave);
		
				Statement stmt = conn.createStatement();
				Map<String,String> noExisten = new HashMap<String,String>();
				
				Long idCuenta = null;
				for ( DetalleAsiento detalleAsiento : detalles ){
					idCuenta = null;
					String query = "select id from CUENTA where codigo = '"+detalleAsiento.getCuenta()+"'";
					ResultSet rs = stmt.executeQuery(query);
					while ( rs.next() ){
						idCuenta = rs.getLong(1);
					}
					if ( idCuenta == null )
						noExisten.put(detalleAsiento.getCuenta(),detalleAsiento.getCuenta());
						//throw new Exception("No se encontro id de cuenta con codigo: "+detalleAsiento.getCuenta());
				}
				
				System.out.println("--- NO EXISTEN --");
				for ( Iterator itMapa = noExisten.keySet().iterator() ;  itMapa.hasNext() ; ){
					System.out.println("Cuenta: "+itMapa.next());
				}
	
				cerrar();
			
				if ( !noExisten.isEmpty() )
					throw new Exception("Hay cuentas que no existen");
	
				System.out.println("Todas las cuentas registradas....");
				
				int numero = 0;
				double debe = 0.0;
				double haber = 0.0;
				List<DetalleAsiento> listaNoCuadrados = new LinkedList<DetalleAsiento>();
				for ( DetalleAsiento detalleAsiento : detalles ){
					
					if ( numero != detalleAsiento.getNumero() ){
						
						debe = ImportarDatosAsientosExcel.dos(debe);
						haber = ImportarDatosAsientosExcel.dos(haber);
						
						if ( debe != haber ) {
							//throw new Exception(" Debe y haber son diferentes en asiento: "+detalleAsiento.getNumero());
							listaNoCuadrados.add(detalleAsiento);
						}
						
						debe = 0.0;
						haber = 0.0;
						numero = detalleAsiento.getNumero();
					}
					//if ( detalleAsiento.getHaber() == 0D )
						debe += detalleAsiento.getDebe();
					//if ( detalleAsiento.getDebe() == 0D )
						haber += detalleAsiento.getHaber();
				}
				
				System.out.println("---- No cuadrados ---- ");
				for ( int i = 0 ; i < listaNoCuadrados.size() ; i++ ){
					System.out.println("No cuadra asiento No.: "+listaNoCuadrados.get(i).getNumero());
				}
				
				if ( !listaNoCuadrados.isEmpty() )
					throw new Exception("Hay cuentas que no cuadran");
				
				System.out.println("Todas las cuentas cuadran....");
				
				return detalles;
		} catch( Exception e){
			cerrar();
			e.printStackTrace();
			throw new Exception("Error en la verificacion de cuentas...");
		}
		
	}
	
	static void cerrar(){
		try {
			if ( stmt != null )
				stmt.close();
			if ( conn != null )
				conn.close();
			if ( fis != null )
				fis.close();
		} catch (SQLException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
	/*public static void main(String[] a){
		
		try {
			verificarCuentas();
		} catch (Exception e) {
			cerrar();
		}
		
	}*/

}
